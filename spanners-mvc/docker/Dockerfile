FROM java:openjdk-8-jdk-alpine
MAINTAINER Stuart 'Stevie' Leitch <hotblack@disasterarea.org.uk>

ADD spanners-mvc-4.1-SNAPSHOT.jar app.jar
ADD application-docker.properties application.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
