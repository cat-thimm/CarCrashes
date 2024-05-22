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

Add a folder "raw_data" and put the persons.csv file in it.

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

