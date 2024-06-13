FROM bde2020/hadoop-namenode:2.0.0-hadoop3.2.1-java8

# Create necessary directories
RUN mkdir -p /input /raw_data

# Update package lists and install necessary dependencies
#RUN apt-get update && \
#    apt-get install -y openjdk-8-jdk git wget maven && \
#    apt-get clean

# Copy your Java source code and pom.xml file
#COPY namenode/src /src
#COPY namenode/pom.xml /

# Install Apache Commons CSV library using Maven
#RUN mvn dependency:resolve

# Copy jar file
COPY namenode/target/CarCrashes-1.0-SNAPSHOT.jar /snapshot.jar

# Copy the persons.csv and crashes.csv file into the container
COPY namenode/raw_data/persons.csv /raw_data/persons.csv
COPY namenode/raw_data/crashes.csv /raw_data/crashes.csv

# Copy the run-hadoop-job script into the container
COPY namenode/run-hadoop-job.sh /run-hadoop-job.sh

# Make the script executable
# RUN chmod +x /run-hadoop-job.sh

# Set the entrypoint to run the Hadoop job when the container starts
# CMD ["/bin/sh", "/run-hadoop-job.sh"]
