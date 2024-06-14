#!/usr/bin/env bash

docker cp namenode:/data.json ./data.json

docker cp ./data.json mongo:/tmp/data.json

docker exec -it mongo bash -c ''
