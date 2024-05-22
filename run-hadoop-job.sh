#!/bin/bash
set -e
set -x


# Check if the /input directory exists in HDFS and create it if not
# hadoop fs -mkdir -p /input

# Check if persons.csv exists in HDFS and remove it if it does
#hadoop fs -test -e /input/persons.csv && hadoop fs -rm /input/persons.csv

# Put the local persons.csv file into HDFS
# hdfs dfs -put /input/persons.csv /input

# Run the Hadoop streaming job
hadoop jar /opt/hadoop/share/hadoop/tools/lib/hadoop-streaming-3.2.0.jar \
  -file /mapper.py \
  -mapper /mapper.py \
  -file /reducer.py \
  -reducer /reducer.py \
  -input /raw_data/persons.csv \
  -output /output