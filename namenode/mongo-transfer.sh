#!/bin/bash

rm -f ./data.json

echo "Start data transfer from namenode"

docker cp namenode:/data.json ./data.json

echo "Start copying data to mongodb"

docker cp ./data.json mongo:/tmp/data.json

echo "Uploading to MongoDB"

# ON WINDOWS USE:
# winpty docker exec -it mongo bash -c 'mongoimport --db=car_crashes --collection=crashes --jsonArray --file="/tmp/data.json"'

# ON LINUX/MACOS USE:
docker exec -it mongo bash -c 'mongoimport --db=car_crashes --collection=crashes --jsonArray --file="/tmp/data.json"'
