Spanners Demo application
    
    
This application demonstrates various features and techniques described in Stuart 'Stevie' Leitch's blog: http://www.disasterarea.co.uk/blog/     
   
This demo contains:
    - Spring MVC web application
    - DAO component
	
Running the latest build in Docker Containers
	The latest version of the application can be started in Docker containers (with Docker Compose) by following instructions in this post:
	http://www.disasterarea.co.uk/blog/docker-part-4-composing-an-environment-stack/
	1. Download the latest Docker Compose file https://raw.githubusercontent.com/hotblac/spanners-docker/master/docker-compose.yml
	2. In the download directory, run sudo docker-compose up -d
	3. The application will download and start and can be accessed at http://localhost:8080/spanners-mvc/ (click Sign Up to create a user account, then sign in)

	It is recommended that the application is run from these Docker containers. See below if you wish to manually set up a database and webserver.
    	
Building the demo
	The whole demo can be built and redeployed to the Docker container using Maven 3:
	mvn clean install tomcat7:redeploy


THE FOLLOWING APPLIES ONLY IF YOU WANT TO DEPLOY TO A LOCAL WEBSERVER / DATABASE INSTEAD OF THE SUPPLIED DOCKER CONTAINERS

Installing the database 
The DAO layer requires a MySQL database. Run the 'create database.sql' to create the schema and empty tables.

Configuring Tomcat
The application has been tested against Tomcat 7. The context.xml must be configured to make the database available to the application using JNDI:

     <Resource name="jdbc/Spanners" 
           auth="Container"
           type="javax.sql.DataSource" 
           username="spanners" 
           password="password"
           driverClassName="com.mysql.jdbc.Driver"
           description="spanners"
           url="jdbc:mysql://localhost:3306/spanners"
           maxActive="15" 
       maxIdle="3"/>
       
In addition, the MySQL driver jar should be copied to Tomcat's lib directory. I used this jar:
http://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.21/mysql-connector-java-5.1.21.jar
      
Starting the applications
The web application can be deployed to Tomcat from the Tomcat Manager Console. Alternatively they can be deployed using maven:
mvn tomcat7:deploy


