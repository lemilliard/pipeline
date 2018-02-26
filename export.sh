#!bin/bash
BASE_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

DOCKER_DIR="$BASE_DIR/pipeline-docker"

DOCKER_COMPOSE_FILE="docker-compose-generated.yml"

DOCKERIZE_FILE="dockerize.sh"

DOCKERFILE="dockerfile"

MAVEN_PROJECTS=(
	"pipeline-websocket"
	"pipeline-ms-dao/pipeline-ms-dao-event"
	"pipeline-ms-dao/pipeline-ms-dao-match"
	"pipeline-ms-dao/pipeline-ms-dao-tournament"
	"pipeline-ms-dao/pipeline-ms-dao-user"
)

NODE_PROJECTS=(
	"pipeline-ms-notification"
)

PYTHON_PROJECTS=(
	"pipeline-ms-resource"
)

ERROR_MISSING_FILES="Required files does not exist"

function install_dependencies() {
	echo "------------------------------"
	echo "Installing dependencies"
	echo "------------------------------"
	echo "Installing pipreqs"
	pip install pipreqs
	
	echo "Installing MiniDao"
	rm -rf MiniDao/
	git clone https://github.com/tkint/MiniDao.git MiniDao
	cd MiniDao
	mvn clean install
	go_to_base_folder
	rm -rf MiniDao/
}

function go_to_base_folder() {
	cd "$BASE_DIR/$1"
}

function create_docker_folder() {
	if [ ! -d "$DOCKER_DIR/$1" ]; then
		echo " -> Creating docker folder $1"
		mkdir -p "$DOCKER_DIR/$1"
	fi
}

function go_to_docker_folder() {
	create_docker_folder $i
	cd "$DOCKER_DIR/$1"
}

function build_projects() {
	echo "------------------------------"
	echo "Building projects"
	echo "------------------------------"
	
	echo "Building maven projects"
	for i in ${MAVEN_PROJECTS[@]}; do
		echo " -> Building $i"
		go_to_base_folder $i
		if [ ! -f "pom.xml" ]; then
			echo "   -> Not a maven project"
		else
			if [ "$i" = "pipeline-websocket" ]; then
				mvn clean compile assembly:single
			else
				mvn clean package
			fi
		fi
	done
	
	echo "Building node projects"
	for i in ${NODE_PROJECTS[@]}; do
		echo " -> Building $i"
		go_to_base_folder $i
		if [ ! -f "package.json" ]; then
			echo "   -> Not a node project"
		else
			npm install
		fi
	done
	
	echo "Building python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		echo " -> Building $i"
		go_to_base_folder $i
		pipreqs --force .
	done
	
	echo
}

function copy_projects() {
	echo "------------------------------"
	echo "Copying projects"
	echo "------------------------------"
	
	echo "Copying maven projects"
	for i in ${MAVEN_PROJECTS[@]}; do
		echo " -> Copying $i"
		go_to_base_folder $i
		files=("target/*.jar")
		jar=${files[0]}
		if [ ! -f $jar ]; then
			echo "   -> Jar does not exist"
		else
			create_docker_folder $i
			cp $jar "$DOCKER_DIR/${i}/"
		fi
	done
	
	echo "Copying node projects"
	for i in ${NODE_PROJECTS[@]}; do
		echo " -> Copying $i"
		go_to_base_folder $i
		files=("*.js")
		server=${files[0]}
		if [ ! -f $server ]; then
			echo "   -> Server does not exist"
		else
			create_docker_folder $i
			cp $server "$DOCKER_DIR/${i}/"
			cp "package.json" "$DOCKER_DIR/${i}/"
			cp -R "node_modules" "$DOCKER_DIR/${i}/"
		fi
	done
	
	echo "Copying python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		echo " -> Copying $i"
		go_to_base_folder $i
		if [ ! -f "main.py" ]; then
			echo "   -> Main does not exist"
		else
			create_docker_folder $i
			cp "main.py" "$DOCKER_DIR/${i}/"
			cp "requirements.txt" "$DOCKER_DIR/${i}/"
		fi
	done
	
	echo
}

function generate_dockerfiles() {
	echo "------------------------------"
	echo "Generating dockerfiles"
	echo "------------------------------"
	
	echo "Generating for maven projects"
	for i in ${MAVEN_PROJECTS[@]}; do
		echo " -> Generating $i"
		go_to_docker_folder $i
		files=("*.jar")
		jar=${files[0]}
		if [ ! -f $jar ]; then
			echo "   -> $ERROR_MISSING_FILES"
		else
			filename=$(basename $jar)
			echo "FROM openjdk:8-jdk-alpine" > $DOCKERFILE
			echo "ADD /$filename /$filename" >> $DOCKERFILE
			echo "CMD java -jar $filename" >> $DOCKERFILE
		fi
	done
	
	echo "Generating for node projects"
	for i in ${NODE_PROJECTS[@]}; do
		echo " -> Generating $i"
		go_to_docker_folder $i
		files=("*.js")
		server=${files[0]}
		if [ ! -f $server ]; then
			echo "   -> $ERROR_MISSING_FILES"
		else
			filename=$(basename $server)
			echo "FROM node:6" > $DOCKERFILE
			echo "ADD /$filename /$filename " >> $DOCKERFILE
			echo "ADD /package.json  /package.json " >> $DOCKERFILE
			echo "ADD /node_modules  /node_modules " >> $DOCKERFILE
			echo "CMD node $filename" >> $DOCKERFILE
		fi
	done
	
	echo "Generating for python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		echo " -> Generating $i"
		go_to_docker_folder $i
		if [ ! -f "main.py" ]; then
			echo "   -> $ERROR_MISSING_FILES"
		else
			echo "FROM python:3" > $DOCKERFILE
			echo "ADD /main.py /main.py " >> $DOCKERFILE
			echo "ADD /requirements.txt  /requirements.txt " >> $DOCKERFILE
			echo "RUN pip install --no-cache-dir -r requirements.txt" >> $DOCKERFILE
			echo "CMD python main.py" >> $DOCKERFILE
		fi
	done
	
	echo
}

function add_to_docker_compose() {
	echo >> $DOCKER_COMPOSE_FILE
	echo "  $1:" >> $DOCKER_COMPOSE_FILE
	echo "    image: $1" >> $DOCKER_COMPOSE_FILE
	
	prefix="pipeline-"
	path_without_prefix=${1#$prefix}
	
	cat >> $DOCKER_COMPOSE_FILE << EOF
    networks:
      - web
    expose:
      - "$2"
    labels:
      - "traefik.frontend.rule=PathPrefixStrip:/$path_without_prefix"
      - "traefik.enable=true"
      - "traefik.port=$2"
      - "traefik.tags=pipeline"
EOF
}

function generate_docker_compose() {
	echo "------------------------------"
	echo "Generating $DOCKER_COMPOSE_FILE"
	echo "------------------------------"
	
	go_to_docker_folder
	
	echo "Adding header and traefik"
	cat > $DOCKER_COMPOSE_FILE << EOF
version: '2'

services:
  proxy:
    image: traefik
    command: --web --rancher --rancher.exposedbydefault=false --rancher.metadata=true --constraints=tag==pipeline --logLevel=DEBUG
    networks:
      - webgateway
    ports:
      - "80:80"
      - "8081:8080"
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
      - /dev/null:/traefik.toml
EOF
	
	echo "Adding maven projects"
	for i in ${MAVEN_PROJECTS[@]}; do
		echo " -> Adding $i"
		files=("$i/*.jar")
		jar=${files[0]}
		if [ ! -f $jar ]; then
			echo "   -> $ERROR_MISSING_FILES"
		else
			IFS='/' read -r -a array <<< "${i}"
			last_dir=${array[-1]}
			add_to_docker_compose $last_dir "8080"
		fi
	done
	
	echo "Adding node projects"
	for i in ${NODE_PROJECTS[@]}; do
		echo " -> Adding $i"
		files=("$i/*.js")
		server=${files[0]}
		if [ ! -f $server ]; then
			echo "   -> $ERROR_MISSING_FILES"
		else
			add_to_docker_compose $i "3000"
		fi
	done
	
	echo "Adding python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		echo " -> Adding $i"
		add_to_docker_compose $i "8080"
	done
	
	echo "Adding footer"
	cat >> $DOCKER_COMPOSE_FILE << EOF

networks:
  webgateway:
    driver: bridge
  web:
    external:
      name: traefik_webgateway
EOF
	echo
}

function add_to_dockerize() {
	echo " -> Adding $1"
	echo "echo \"Building $1\"" >> $DOCKERIZE_FILE
	echo "docker build -t ${1} ./${2}" >> $DOCKERIZE_FILE
}

function generate_dockerize() {
	echo "------------------------------"
	echo "Generating $DOCKERIZE_FILE"
	echo "------------------------------"
	
	go_to_docker_folder
	
	echo "Adding header"
	echo "#!bin/bash" > $DOCKERIZE_FILE
	echo "echo \"------------------------------\"" >> $DOCKERIZE_FILE
	echo "echo \"Removing old images\"" >> $DOCKERIZE_FILE
	echo "echo \"------------------------------\"" >> $DOCKERIZE_FILE
	echo "docker rmi \$(docker images --format '{{.Repository}}' | grep 'pipeline')" >> $DOCKERIZE_FILE
	
	echo "echo \"------------------------------\"" >> $DOCKERIZE_FILE
	echo "echo \"Generating new images\"" >> $DOCKERIZE_FILE
	echo "echo \"------------------------------\"" >> $DOCKERIZE_FILE
	
	echo "Adding maven projects"
	for i in ${MAVEN_PROJECTS[@]}; do
		IFS='/' read -r -a array <<< "${i}"
		last_dir=${array[-1]}
		add_to_dockerize $last_dir $i
	done
	
	echo "Adding node projects"
	for i in ${NODE_PROJECTS[@]}; do
		add_to_dockerize $i $i
	done
	
	echo "Adding python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		add_to_dockerize $i $i
	done
	
	echo
}

function main(){
	install_dependencies

	build_projects

	copy_projects
	
	generate_dockerfiles

	generate_docker_compose

	generate_dockerize
}

main
