#!/bin/bash
#set -e
#set -x

# Leave safe mode
hdfs dfsadmin -safemode leave

# delete output directory
hdfs dfs -rm -r /output

# Debugging: List contents of /raw_data to verify the presence of persons.csv
# hadoop dfs -ls

# Check if the /raw_data directory exists in HDFS and create it if not
hadoop fs -mkdir -p /raw_data

# Check if persons.csv exists in HDFS and only put it if it does not exist
if hadoop fs -test -e usr/raw_data/persons.csv; then
  echo "persons.csv already exists in HDFS, skipping upload."
else
  echo "persons.csv does not exist in HDFS, uploading."
  hdfs dfs -put usr/raw_data/persons.csv /raw_data
fi

hadoop jar share/hadoop/tools/lib/hadoop-streaming-3.3.6.jar \
  -files usr/mapper.py,usr/reducer.py \
  -mapper  mapper.py \
  -reducer reducer.py \
  -input raw_data/persons.csv \
  -output usr/output

# Check if the job completed successfully and the output directory exists
if hadoop fs -test -e /output; then
  echo "Hadoop streaming job completed successfully."
  hadoop fs -get /output /output # safe on container for debugging
else
  echo "Hadoop streaming job failed."
fi

