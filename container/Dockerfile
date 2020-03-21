FROM openjdk:11-jre

RUN apt-get update && apt-get install -y \
    procps \
    less \
    nano \
    unzip

WORKDIR /app
COPY *.jar app.jar
COPY startup.sh startup.sh
RUN chmod a+x startup.sh

CMD [ "bash", "-c", "/app/startup.sh"]
