# Car Crashes

Work in Progress Readme on "car crash" project.

## Goal
Big Data Evaluation of car crashes in New York City. 

## Tools
- MongoDB
- Hadoop


## How to start runner:

1. Start hadoop (docker or start-all.sh/stop-all.sh script)
2. mvn clean install
3. hadoop jar target/CarCrashes-1.0-SNAPSHOT.jar org.cthimm.CC_Runner /user/c.thimm/Motor_Vehicle_Collisions_-_Person.csv /output-crashes


## Start MongoDB
- docker-compose build --no-cache
- docker-compose up -d

## Stop MongoDB
- docker-compose stop/down
