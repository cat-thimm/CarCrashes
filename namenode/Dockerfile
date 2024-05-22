# Use an appropriate base image for Hadoop
FROM bde2020/hadoop-namenode:2.0.0-hadoop3.2.1-java8

RUN apt-get update

# Install Python
RUN apt-get install python -y


# Create necessary directories
RUN mkdir -p /input /raw_data

# Copy the mapper and reducer scripts into the container
COPY ../../map-reduce/mapper.py /mapper.py
COPY ../../map-reduce/reducer.py /reducer.py

# Copy the persons.csv file into the container
COPY ../../raw_data/persons.csv /raw_data/persons.csv

# Copy the run-hadoop-job script into the container
COPY run-hadoop-job.sh /run-hadoop-job.sh

# Make the script executable
# RUN chmod +x /run-hadoop-job.sh

# Set the entrypoint to run the Hadoop job when the container starts
ENTRYPOINT ["/bin/sh", "/run-hadoop-job.sh"]
