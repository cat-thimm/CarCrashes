# Car Crashes

Work in Progress Readme on "car crash" project.

## Goal
Big Data Evaluation of car crashes in New York City. This projects merges two CSV files into one with Hadoop MapReduce and uploads the result into a local MongoDB.

Afterwards, evaluations can be made by accessing the database.

## Tools
- MongoDB
- Hadoop
- Java 8

# Steps

## Add Raw Data

Add a folder `/namenode/raw_data` and put the [crashes.csv](https://dev.socrata.com/foundry/data.cityofnewyork.us/h9gi-nx95) and [persons.csv](https://dev.socrata.com/foundry/data.cityofnewyork.us/f55k-p6yu) file in it.

## Build maven project

Go to ``namenode`` (might need to open this folder as a maven project in Intellij). Here run `mvn clean install`. It generates a new JAR to be executed for the MapReduce Job.

## Start MongoDB and Hadoop
- `docker-compose build --no-cache` (Uploads Data and scripts to container)
- `docker-compose up -d` (Initializes MongoDB with empty DB and collection)
- `docker-compose exec namenode sh`
- `sh run-hadoop-job.sh` (Runs MapReduce Job and copies data to data.json)
  - *DEBUG*: See the reults by ``hdfs dfs -cat /output/part-00000``
- `exit`
- `cd namenode`
- ``./mongo-transfer.sh`` (Copies data.json to machine and uploads it to MongoDB collection)

## Stop MongoDB and Hadoop 
- `docker-compose stop/down -v`




