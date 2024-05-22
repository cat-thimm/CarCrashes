#!/bin/bash

# Ensure the raw_data folder and persons.csv file exist locally
if [ ! -f ./raw_data/persons.csv ]; then
    echo "Error: ./raw_data/persons.csv does not exist."
    exit 1
fi

# Copy the scripts to the Docker container
docker cp ./main/mapper.ipynb namenode:/mapper.py
docker cp ./main/reducer.ipynb namenode:/reducer.py

# Check if the persons.csv file already exists in the container
docker exec namenode bash -c '[ -f /input/persons.csv ]'
FILE_EXISTS=$?

if [ $FILE_EXISTS -eq 0 ]; then
    echo "/input/persons.csv already exists in the container."
else
    echo "Copying persons.csv to the container."
    docker cp ./raw_data/persons.csv namenode:/input/persons.csv
fi

# Check if the current operating system is Windows
if [[ "$OSTYPE" == "msys" ]]; then
    # Running on Windows, prefix the docker exec command with winpty
    EXEC_CMD="winpty docker exec -it namenode"
else
    # Running on other platforms, use the regular docker exec command
    EXEC_CMD="docker exec -it namenode"
fi

# echo $EXEC_CMD

# Execute all commands inside the Docker container
$EXEC_CMD bash -c '
  set -e  # Exit immediately if any command exits with a non-zero status
  set -x  # Print each command before executing it for debugging

  if [ ! -d "/input" ]; then
    mkdir /input
  fi

  # Create HDFS directory and put files into it
  hadoop fs -mkdir -p /input
  hdfs dfs -put /input/persons.csv /input

  # Run the Hadoop streaming job with the correct paths
  hadoop jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar \
    -file /mapper.py \
    -mapper /mapper.py \
    -file /reducer.py \
    -reducer /reducer.py \
    -input /input/persons.csv \
    -output /output

  exit
' > debug/script_output.log 2>&1  # Redirect both stdout and stderr to a log file

# Print the output log for review
cat debug/script_output.log

# Keep bash open
/bin/bash
