# Car Crashes

Work in Progress Readme on "car crash" project.

## Goal
Big Data Evaluation of car crashes in New York City. 

## Tools
- MongoDB
- Hadoop
- Python

# Steps

## Add Raw Data

Add a folder in `/namenode/raw_data` and put the persons.csv file in it.

## Start MongoDB, Hadoop and Jupyter
- docker-compose build --no-cache
- docker-compose up -d

## Stop MongoDB, Hadoop and Jupyter
- docker-compose stop/down

## Mongo Shell
`docker ps` -> Get mongo container id (e.g. e88256aaf0df)

`docker exec -it e88256aaf0df bash` 

`mongo mongodb://localhost:27017 -u root -p letmein`

## Start Dockerfile 

The Dockerfile will start hadoop and execute the initial map reduce job using the python mapper.

> docker build -t hadoop-job .
> 
> docker run -it hadoop-job


### Testing mapper and reducer

Open up a console and check the following commands:

>`cd main`
> 
> `cat sample.csv | python mapper.py > mapper_output.json`
> 
> `cat mapper_output.json | python reducer.py > reducer_output.json`

It should generate the reducer_output.json in the following style:

``[{"COLLISION_ID": "1", "EMOTIONAL_STATUS": "calm", "BODILY_INJURY": "none", "PERSON_TYPE": "driver", "PERSON_AGE": "30", "PERSON_SEX": "M", "SAFETY_EQUIPMENT": "seatbelt"}, {"COLLISION_ID": "2", "EMOTIONAL_STATUS": "angry", "BODILY_INJURY": "minor", "PERSON_TYPE": "passenger", "PERSON_AGE": "25", "PERSON_SEX": "F", "SAFETY_EQUIPMENT": "airbag"}]
``

