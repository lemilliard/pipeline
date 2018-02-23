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

for i in ${MVN_DIRS[@]}; do
	echo "Building ${i}"
	cd "$BASE_DIR/${i}"
	mvn clean package
	echo "Copying ${i}"
	mkdir -p "$DOCKER_DIR/${i}"
	files=("target/*.jar")
	jar=${files[0]}
	filename=$(basename $jar)
	echo $filename
	cp $jar "$DOCKER_DIR/${i}/"
	echo "Creating dockerfile"
	cd "$DOCKER_DIR/${i}"
	echo "FROM openjdk:8-jdk-alpine" > dockerfile
	echo "ADD /$filename /$filename" >> dockerfile
	echo "CMD java -jar $filename" >> dockerfile
done

for i in ${NODE_DIRS[@]}; do
	cd "$BASE_DIR/${i}"
	npm install
done
