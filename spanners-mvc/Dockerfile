FROM java:openjdk-8-jdk-alpine
MAINTAINER Stuart 'Stevie' Leitch <hotblack@disasterarea.org.uk>

ADD target/spanners-mvc-4.0-SNAPSHOT.jar app.jar
ADD src/config/application-docker.properties application.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
