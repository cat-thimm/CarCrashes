# Use an appropriate base image for Hadoop
FROM amnrz/hadoop-namenode:v1

RUN apt update
RUN apt install -y --no-install-recommends \
  python3 python3-pip \
  nano

# Create necessary directories
RUN mkdir -p /input /raw_data

# Copy the mapper and reducer scripts into the container
COPY map-reduce/mapper.py /mapper.py
COPY map-reduce/reducer.py /reducer.py

# Copy the persons.csv file into the container
COPY raw_data/persons.csv /raw_data/persons.csv

# Copy the run-hadoop-job script into the container
COPY run-hadoop-job.sh /run-hadoop-job.sh

# Make the script executable
# RUN chmod +x /run-hadoop-job.sh

# Set the entrypoint to run the Hadoop job when the container starts
ENTRYPOINT ["/bin/sh", "/run-hadoop-job.sh"]
