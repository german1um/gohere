#!/bin/bash

newVersion=0
oldVersion=$(cat version)
let "newVersion = oldVersion + 1"
echo ${newVersion} > version

cd ..

./gradlew bootJar

mv ./build/libs/gohere-0.0.1-SNAPSHOT.jar ./deploy/gohere.jar

cd ./deploy

docker build -t morlok1/gohere:master .
docker push morlok1/gohere:master

rm -rf *.jar