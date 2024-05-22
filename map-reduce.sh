# Copy the scripts to the Docker container
docker cp ./main/mapper.ipynb namenode:/mapper.py
docker cp ./main/reducer.ipynb namenode:/reducer.py

# Check if the current operating system is Windows
if [[ "$OSTYPE" == "msys" ]]; then
    # Running on Windows, prefix the docker exec command with winpty
    EXEC_CMD="winpty docker exec -it namenode bash -c"
else
    # Running on other platforms, use the regular docker exec command
    EXEC_CMD="docker exec -it namenode bash -c"
fi

echo $EXEC_CMD

# Execute all commands inside the Docker container
$EXEC_CMD '
  set -e  # Exit immediately if any command exits with a non-zero status
  set -x  # Print each command before executing it for debugging

  if [ -d "/input" ]; then
    rm /input
  fi
  mkdir /input

  persons.csv > /input/persons.csv

  # Create HDFS directory and put files into it
  hadoop fs -mkdir -p /input
  hdfs dfs -put /input/* /input

  # Run the Hadoop streaming job with the correct paths
  hadoop jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar \
    -file mapper.py \
    -mapper mapper.py \
    -file reducer.py \
    -reducer reducer.py \
    -input ./raw_data/persons.csv \
    -output /output

  exit
' > debug/script_output.log 2>&1  # Redirect both stdout and stderr to a log file

# Print the output log for review
cat debug/script_output.log

# Keep bash open
/bin/bash