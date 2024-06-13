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

Add a folder in `/namenode/raw_data` and put the persons.csv and crashes.csv file in it.

## Build maven project

Go to ``namenode``. Here run `mvn clean install`. It generates a new JAR to be executed for the MapReduce Job.

## Start MongoDB, Hadoop and Jupyter
- `docker-compose build --no-cache`
- `docker-compose up -d`
- `docker-compose exec namenode sh`
- `sh run-hadoop-job.sh`
- See the reults by ``hdfs dfs -cat /output/part-00000``

### In case of "Hadoop is in Safe Mode" Error
`hdfs dfsadmin -safemode leave`

## Stop MongoDB, Hadoop and Jupyter
- `docker-compose stop/down -v`

## Mongo Shell
`docker ps` -> Get mongo container id (e.g. e88256aaf0df)

`docker exec -it e88256aaf0df bash` 

`mongo mongodb://localhost:27017 -u root -p letmein`


