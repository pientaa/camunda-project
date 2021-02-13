#!/bin/bash

echo "Building wars ..."
cd register
mvn clean install

cd ../book
mvn clean install

echo "Removing all existing docker containers ..."
result=$(docker ps -aq)

if [[ -n "$result" ]]; then
  docker rm -f $(docker ps -aq)
else
  echo "No containers found ..."
fi

echo "Removing old image ..."
docker rmi -f pienta/camunda-project:1.0

docker-compose up