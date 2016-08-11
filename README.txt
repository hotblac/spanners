Spanners Demo application
    
    
This application demonstrates various features and techniques described in Stuart 'Stevie' Leitch's blog: http://www.disasterarea.co.uk/blog/     
   
This demo contains:
    - Spring MVC web application
    - Spring Data REST API
	- Spanners Users REST microservice
	
Running the latest build in Docker Containers
	The latest version of the application can be started in Docker containers (with Docker Compose) by following instructions in this post:
	http://www.disasterarea.co.uk/blog/docker-part-4-composing-an-environment-stack/
	1. Download the latest Docker Compose file https://raw.githubusercontent.com/hotblac/spanners-docker/master/docker-compose.yml
	2. In the download directory, run docker-compose up -d
	3. The application will download and start and can be accessed at http://localhost:8080/spanners-mvc/ (sign in as smith / password)

	It is recommended that the application is run from these Docker containers. See below if you wish to manually set up a database and webserver.
    	
Building the demo
	The whole demo can be built and redeployed to the Docker container using Maven 3:
	mvn clean install tomcat7:redeploy


THE FOLLOWING APPLIES ONLY IF YOU WANT TO DEPLOY TO A LOCAL WEBSERVER / DATABASE INSTEAD OF THE SUPPLIED DOCKER CONTAINERS

Installing the database 
The DAO layer requires a MySQL database. Run the 'create database.sql' to create the schema and empty tables.

      
Starting the applications
The application components are all packaged as Spring Boot executable jars. After building, they can be started using 'java -jar <component name>.jar'. Alternatively they can be run from Maven using 'mvn spring-boot:run'.


