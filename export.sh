#!bin/bash
BASE_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

DOCKER_DIR="pipeline-docker"

DOCKER_FULL_DIR="$BASE_DIR/$DOCKER_DIR"

DOCKER_COMPOSE_FILE="docker-compose.yml"

RANCHER_COMPOSE_FILE="rancher-compose.yml"

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

RUBY_PROJECTS=(
	"pipeline-ms-log"
)

ERROR_MISSING_FILES="Required files does not exist"

RANCHER_URL="home.thomaskint.com"

RANCHER_PORT="8086"

RANCHER_ACCESS_KEY="1FD3E962C8A011454F0F"

RANCHER_SECRET_KEY="8G8MucFc6QzqgYsg9iopoXEjyXLaTWodxNUqMDWV"

RANCHER_STACK_NAME="pipeline"

RANCHER_PROJECT_ID="1a5"

shopt -s extglob

function install_dependencies() {
	echo "------------------------------"
	echo "Installing dependencies"
	echo "------------------------------"
	echo "Installing pipreqs"
	pip install pipreqs
	
	echo
}

function go_to_base_folder() {
	cd "$BASE_DIR/$1"
}

function create_docker_folder() {
	if [ ! -d "$DOCKER_FULL_DIR/$1" ]; then
		echo " -> Creating docker folder $1"
		mkdir -p "$DOCKER_FULL_DIR/$1"
	fi
}

function go_to_docker_folder() {
	create_docker_folder $i
	cd "$DOCKER_FULL_DIR/$1"
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
			cp $jar "$DOCKER_FULL_DIR/${i}"
		fi
	done
	
	echo "Copying node projects"
	for i in ${NODE_PROJECTS[@]}; do
		echo " -> Copying $i"
		go_to_base_folder $i
		files=("package.json")
		package=${files[0]}
		if [ ! -f $package ]; then
			echo "   -> Package.json does not exist"
		else
			create_docker_folder $i
			cp -R !(node_modules|.idea|.|..|.gitignore) "$DOCKER_FULL_DIR/${i}"
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
			cp "main.py" "$DOCKER_FULL_DIR/${i}"
			cp "requirements.txt" "$DOCKER_FULL_DIR/${i}"
		fi
	done
	
	echo "Copying ruby projects"
	for i in ${RUBY_PROJECTS[@]}; do
		echo " -> Copying $i"
		go_to_base_folder $i
		create_docker_folder $i
		cp -r "src/" "$DOCKER_FULL_DIR/${i}"
		cp "Gemfile" "$DOCKER_FULL_DIR/${i}"
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
		echo "FROM node:6" > $DOCKERFILE
		echo "ADD . /app" >> $DOCKERFILE
		echo "RUN cd /app && npm install" >> $DOCKERFILE
		echo "CMD cd /app && npm start" >> $DOCKERFILE
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
	
	echo "Generating for ruby projects"
	for i in ${RUBY_PROJECTS[@]}; do
		echo " -> Generating $i"
		go_to_docker_folder $i
		echo "FROM ruby:2.5" > $DOCKERFILE
		echo "ADD . /app " >> $DOCKERFILE
		echo "RUN cd /app && bundle install" >> $DOCKERFILE
		echo "CMD cd /app && ruby src/main.rb -p 8080" >> $DOCKERFILE
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
      - \"$2\"
    labels:
      - \"traefik.frontend.rule=PathPrefixStrip:/$path_without_prefix\"
      - \"traefik.enable=true\"
      - \"traefik.port=$2\"
      - \"traefik.tags=pipeline\"
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
      - \"80:80\"
      - \"8081:8080\"
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
		files=("$i/package.json")
		package=${files[0]}
		if [ ! -f $package ]; then
			echo "   -> Package.json does not exist"
		else
			IFS='/' read -r -a array <<< "${i}"
			last_dir=${array[-1]}
			add_to_docker_compose $last_dir "8080"
		fi
	done
	
	echo "Adding python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		echo " -> Adding $i"
		add_to_docker_compose $i "8080"
	done
	
	echo "Adding ruby projects"
	for i in ${RUBY_PROJECTS[@]}; do
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

function add_to_rancher_compose() {
	echo "  $1:" >> $RANCHER_COMPOSE_FILE
	echo "    scale: $2" >> $RANCHER_COMPOSE_FILE
	echo "    start_on_create: true" >> $RANCHER_COMPOSE_FILE
}

function generate_rancher_compose() {
	echo "------------------------------"
	echo "Generating $RANCHER_COMPOSE_FILE"
	echo "------------------------------"
	
	go_to_docker_folder
	
	echo "Adding header and traefik"
	cat > $RANCHER_COMPOSE_FILE << EOF
version: '2'
services:
  proxy:
    scale: $1
    start_on_create: true
EOF
	
	echo "Adding maven projects"
	for i in ${MAVEN_PROJECTS[@]}; do
		echo " -> Adding $i"
		IFS='/' read -r -a array <<< "${i}"
		last_dir=${array[-1]}
		add_to_rancher_compose $last_dir $1
	done
	
	echo "Adding node projects"
	for i in ${NODE_PROJECTS[@]}; do
		echo " -> Adding $i"
		IFS='/' read -r -a array <<< "${i}"
		last_dir=${array[-1]}
		add_to_rancher_compose $last_dir $1
	done
	
	echo "Adding python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		echo " -> Adding $i"
		add_to_rancher_compose $i $1
	done
	
	echo "Adding ruby projects"
	for i in ${RUBY_PROJECTS[@]}; do
		echo " -> Adding $i"
		add_to_rancher_compose $i $1
	done
	
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
		IFS='/' read -r -a array <<< "${i}"
		last_dir=${array[-1]}
		add_to_dockerize $last_dir $i
	done
	
	echo "Adding python projects"
	for i in ${PYTHON_PROJECTS[@]}; do
		add_to_dockerize $i $i
	done
	
	echo "Adding ruby projects"
	for i in ${RUBY_PROJECTS[@]}; do
		add_to_dockerize $i $i
	done
	
	echo
}

function export_to_remote_host() {
	go_to_base_folder
	scp -P 2222 -r $DOCKER_FULL_DIR root@home.thomaskint.com:/opt/
}

function execute_dockerize() {
	ssh -p 2222 root@home.thomaskint.com "cd /opt/${DOCKER_DIR} && sh ${DOCKERIZE_FILE}"
}

function get_stack_id() {
	req=$(curl -u "${RANCHER_ACCESS_KEY}:${RANCHER_SECRET_KEY}" \
		"http://${RANCHER_URL}:${RANCHER_PORT}/v2-beta/stacks?name=${RANCHER_STACK_NAME}")
	data=(`echo $req| sed -e 's/[{}]/''/g'| awk -v k="text" '{n=split($0,a,","); for (i=1; i<=n; i++) print a[i]}' | sed 's/:/ /1'| awk -F" " '{ print $2 }'`)
	self=${data[5]}
	url=${self:8:-1}
	id=${url:52}
	echo $id
	
}

function delete_stack() {
	curl -u "${RANCHER_ACCESS_KEY}:${RANCHER_SECRET_KEY}" \
		-X DELETE \
		"http://${RANCHER_URL}:${RANCHER_PORT}/v2-beta/projects/${RANCHER_PROJECT_ID}/stacks/${1}"
}

function create_stack() {
	echo "Rancher Compose"
	ranchercompose=$(sed "y/\"/\\\"/" "${DOCKER_FULL_DIR}/${RANCHER_COMPOSE_FILE}")
	ranchercompose=$(sed "y/\'/\\\'/" <<< $ranchercompose)
	echo "Docker Compose"
	dockercompose=$(cat "${DOCKER_FULL_DIR}/${DOCKER_COMPOSE_FILE}")
	dockercompose=$(sed -E ':a;N;$!ba;s/\r{0,1}\n/\\n/g' <<< $dockercompose)
	curl -u "${RANCHER_ACCESS_KEY}:${RANCHER_SECRET_KEY}" \
		-X POST \
		-H "Content-Type: application/json" \
		-d "{
			\"name\": \"pipeline\",
			\"rancherCompose\": \"${ranchercompose}\",
			\"dockerCompose\": \"${dockercompose}\",
			\"startOnCreate\": \"true\"
		}" \
		"http://${RANCHER_URL}:${RANCHER_PORT}/v2-beta/projects/${RANCHER_PROJECT_ID}/stacks/"
}

function main() {
	install_dependencies

	build_projects

	copy_projects
	
	generate_dockerfiles

	generate_docker_compose
	
	generate_rancher_compose 1

	generate_dockerize
	
	export_to_remote_host
	
	delete_stack $(get_stack_id)
	
	execute_dockerize
	
	create_stack
}

main
