FROM java:openjdk-8-jdk-alpine
MAINTAINER Stuart 'Stevie' Leitch <hotblack@disasterarea.org.uk>

ADD spanners-api.jar /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spanners-api.jar"]
