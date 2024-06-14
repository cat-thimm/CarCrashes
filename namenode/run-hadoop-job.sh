#!/bin/bash

# Leave safe mode
hdfs dfsadmin -safemode leave

# delete output directory
hdfs dfs -rm -r /output

# Check if the /raw_data directory exists in HDFS and create it if not
hadoop fs -mkdir -p /raw_data

# Check if persons.csv exists in HDFS and only put it if it does not exist
if hadoop fs -test -e /raw_data/persons.csv; then
  echo "persons.csv already exists in HDFS, skipping upload."
else
  echo "persons.csv does not exist in HDFS, uploading."
  hdfs dfs -put /raw_data/persons.csv /raw_data
fi

if hadoop fs -test -e /raw_data/crashes.csv; then
  echo "crashes.csv already exists in HDFS, skipping upload."
else
  echo "crashes.csv does not exist in HDFS, uploading."
  hdfs dfs -put /raw_data/crashes.csv /raw_data
fi

hadoop jar /snapshot.jar org.cthimm.CC_Runner /raw_data/crashes.csv /raw_data/persons.csv /output

# Check if the job completed successfully and the output directory exists
if hadoop fs -test -e /output; then
  echo "Hadoop streaming job completed successfully."
  hadoop fs -get /output /output # safe on container for debugging
else
  echo "Hadoop streaming job failed."
fi

hdfs dfs -cat /output/part-00000 | sed '$s/.$/]/' > data.json
