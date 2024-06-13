FROM bde2020/hadoop-namenode:2.0.0-hadoop3.2.1-java8

# Create necessary directories
RUN mkdir -p /input /raw_data

# Copy jar file
COPY namenode/target/CarCrashes-1.0-SNAPSHOT.jar /snapshot.jar

# Copy the persons.csv and crashes.csv
COPY namenode/raw_data/persons.csv /raw_data/persons.csv
COPY namenode/raw_data/crashes.csv /raw_data/crashes.csv

# Copy the run-hadoop-job script
COPY namenode/run-hadoop-job.sh /run-hadoop-job.sh

