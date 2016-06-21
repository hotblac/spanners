FROM java:openjdk-8-jdk-alpine
MAINTAINER Stuart 'Stevie' Leitch <hotblack@disasterarea.org.uk>

ADD spanners-mvc.jar /
ADD application.properties /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spanners-mvc.jar"]
