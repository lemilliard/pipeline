#!bin/bash
BASE_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

DOCKER_DIR="$BASE_DIR/pipeline-docker"

MVN_DIRS=(
	"pipeline-websocket"
	"pipeline-ms-dao/pipeline-ms-dao-user"
)

NODE_DIRS=(
	"pipeline-ms-notification"
)

cd "$DOCKER_DIR"

echo "Creating dockerize"
echo "#!bin/bash" > dockerize.sh

for i in ${MVN_DIRS[@]}; do
	cd "$BASE_DIR/${i}"
	
	echo "Building ${i}"
	if [ "$i" = "pipeline-websocket" ]; then
		mvn clean compile assembly:single
	else
		mvn clean package
	fi
	
	echo "Creating docker folder"
	mkdir -p "$DOCKER_DIR/${i}"
	
	echo "Copying ${i}"
	files=("target/*.jar")
	jar=${files[0]}
	filename=$(basename $jar)
	cp $jar "$DOCKER_DIR/${i}/"
	
	echo "Creating dockerfile"
	cd "$DOCKER_DIR/${i}"
	echo "FROM openjdk:8-jdk-alpine" > dockerfile
	echo "ADD /$filename /$filename" >> dockerfile
	echo "CMD java -jar $filename" >> dockerfile
	
	IFS='/' read -r -a array <<< "${i}"
	last_dir=${array[-1]}
	
	echo "Adding to dockerize"
	echo "docker build -t ${last_dir} ./${i}" >> "$DOCKER_DIR/dockerize.sh"
done

for i in ${NODE_DIRS[@]}; do
	cd "$BASE_DIR/${i}"
	
	echo "Building ${i}"
	npm install
	
	echo "Creating docker folder"
	mkdir -p "$DOCKER_DIR/${i}"
	
	echo "Copying ${i}"
	files=("*.js")
	server=${files[0]}
	filename=$(basename $server)
	cp $server "$DOCKER_DIR/${i}/"
	cp "package.json" "$DOCKER_DIR/${i}/"
	cp -R "node_modules" "$DOCKER_DIR/${i}/"
	
	echo "Creating dockerfile"
	cd "$DOCKER_DIR/${i}"
	echo "FROM node:6" > dockerfile
	echo "ADD /$filename /$filename " >> dockerfile
	echo "ADD /package.json  /package.json " >> dockerfile
	echo "ADD /node_modules  /node_modules " >> dockerfile
	echo "CMD node $filename" >> dockerfile
	
	echo "Adding to dockerize"
	echo "docker build -t ${i} ./${i}" >> "$DOCKER_DIR/dockerize.sh"
done
