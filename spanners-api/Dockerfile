FROM java:openjdk-8-jdk-alpine
MAINTAINER Stuart 'Stevie' Leitch <hotblack@disasterarea.org.uk>

ADD target/spanners-api-4.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
