# Car Crashes

Work in Progress Readme on "car crash" project.

## Goal
Big Data Evaluation of car crashes in New York City. 

## Tools
- MongoDB
- Hadoop
- Python

## Start MongoDB, Hadoop and Jupyter
- docker-compose build --no-cache
- docker-compose up -d

## Stop MongoDB, Hadoop and Jupyter
- docker-compose stop/down

## Mongo Shell
`docker ps` -> Get mongo container id (e.g. e88256aaf0df)

`docker exec -it e88256aaf0df bash` 

`mongo mongodb://localhost:27017 -u root -p letmein`
