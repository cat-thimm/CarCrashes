#!/bin/bash
set -e
set -x

if [ ! -d "/input" ]; then
  mkdir /input
fi

cp /raw_data/persons.csv /input/persons.csv
hadoop fs -mkdir -p /input
hdfs dfs -put /input/persons.csv /input

hadoop jar /opt/hadoop/share/hadoop/tools/lib/hadoop-streaming-${HADOOP_VERSION}.jar \
  -file /mapper.py \
  -mapper /mapper.py \
  -file /reducer.py \
  -reducer /reducer.py \
  -input /input/persons.csv \
  -output /output
