# Use an appropriate base image for Hadoop
FROM bde2020/hadoop-base:2.0.0-hadoop2.7.4-java8

# Set environment variables
ENV HADOOP_VERSION 3.2.1

# Create necessary directories
RUN mkdir -p /input /raw_data

# Copy the mapper and reducer scripts into the container
COPY ./main/mapper.ipynb /mapper.py
COPY ./main/reducer.ipynb /reducer.py

# Copy the persons.csv file into the container
COPY ./raw_data/persons.csv /raw_data/persons.csv

# Define a script to run the Hadoop job
RUN echo '#!/bin/bash \n\
set -e \n\
set -x \n\
if [ ! -d "/input" ]; then \n\
  mkdir /input \n\
fi \n\
cp /raw_data/persons.csv /input/persons.csv \n\
hadoop fs -mkdir -p /input \n\
hdfs dfs -put /input/persons.csv /input \n\
hadoop jar /opt/hadoop/share/hadoop/tools/lib/hadoop-streaming-${HADOOP_VERSION}.jar \n\
  -file /mapper.py \n\
  -mapper /mapper.py \n\
  -file /reducer.py \n\
  -reducer /reducer.py \n\
  -input /input/persons.csv \n\
  -output /output \n\
' > /run-hadoop-job.sh

# Make the script executable
RUN chmod +x /run-hadoop-job.sh

# Run the Hadoop job when the container starts
CMD ["/bin/bash", "/run-hadoop-job.sh"]
