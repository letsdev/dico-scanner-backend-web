FROM maven:3-jdk-11

ENV HOME="/home/jenkins"
RUN useradd -u 112 -d $HOME -m jenkins

RUN apt-get update -y \
        && apt-get install -y \
            ssh \
            git \
            unzip
