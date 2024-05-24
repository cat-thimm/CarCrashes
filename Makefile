DOCKER_NETWORK = docker-hadoop_default
ENV_FILE = hadoop.env
current_branch := $(shell git rev-parse --abbrev-ref HEAD)
build:
	docker build -t bde2020/hadoop-namenode:$(current_branch) ./namenode
	docker build -t bde2020/hadoop-datanode:$(current_branch) ./datanode
	docker build -t bde2020/hadoop-nodemanager:$(current_branch) ./nodemanager
	docker build -t bde2020/hadoop-resourcemanager:$(current_branch) ./resourcemanager

carcrashes:
	docker -t run -it hadoop-job

