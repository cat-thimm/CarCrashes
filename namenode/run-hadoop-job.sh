#!/bin/bash
set -e
set -x

# Debugging: List contents of /raw_data to verify the presence of persons.csv
# hadoop dfs -ls

# Check if the /raw_data directory exists in HDFS and create it if not
yarn fs -mkdir -p /raw_data

# Check if persons.csv exists in HDFS and only put it if it does not exist
if yarn fs -test -e /raw_data/persons.csv; then
  echo "persons.csv already exists in HDFS, skipping upload."
else
  echo "persons.csv does not exist in HDFS, uploading."
  yarn dfs -put /raw_data/persons.csv /raw_data
fi

# Run the Hadoop streaming job
#hadoop jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar \
#  -files /mapper.py,/reducer.py \
#  -mapper mapper.py \
#  -reducer reducer.py \
#  -input /raw_data/persons.csv \
#  -output /output

yarn jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar \
  -files /mapper.py,/reducer.py \
  -mapper "python mapper.py" \
  -reducer "python reducer.py" \
  -input /raw_data/persons.csv \
  -output /output



# Check if the job completed successfully and the output directory exists
if yarn fs -test -e /output; then
  echo "Hadoop streaming job completed successfully."
else
  echo "Hadoop streaming job failed."
fi
