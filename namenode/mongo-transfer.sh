#!/bin/bash


rm -f ./data.json

echo "Start data transfer from namenode"

docker cp namenode:/data.json ./data.json

echo "Start copying data to mongodb"

docker cp ./data.json mongo:/tmp/data.json

echo "Uploading to MongoDB"

winpty docker exec -it mongo bash -c 'mongoimport --db=car_crashes --collection=crashes --jsonArray --file="/tmp/data.json"'
