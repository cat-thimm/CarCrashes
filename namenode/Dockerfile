FROM apache/hadoop:3

USER root

ENV HADOOP_HOME=/opt/hadoop

# Create necessary directories
RUN mkdir -p usr/input usr/raw_data

# Copy the mapper and reducer scripts into the container
COPY namenode/map-reduce/mapper.py usr/mapper.py
COPY namenode/map-reduce/reducer.py usr/reducer.py

COPY namenode/target/CarCrashes-1.0-SNAPSHOT.jar snapshot

# Copy the persons.csv file into the container
COPY namenode/raw_data/persons.csv usr/raw_data/persons.csv

# Copy the run-hadoop-job script into the container
COPY namenode/run-hadoop-job.sh usr/run-hadoop-job.sh

# Make the script executable
# RUN chmod +x /run-hadoop-job.sh

# Set the entrypoint to run the Hadoop job when the container starts
# CMD ["/bin/sh", "/run-hadoop-job.sh"]
