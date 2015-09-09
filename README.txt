Spanners Demo application
    
    
This application demonstrates various features and techniques described in Stuart 'Stevie' Leitch's blog: http://www.disasterarea.co.uk/blog/     
   
This demo contains:
    - Spring MVC web application
    - DAO component
    
Building the demo
The whole demo can be built using Maven 3:
mvn clean install

Installing the database
The DAO layer requires a MySQL database. Run the 'create database.sql' to create the schema and an empty table.

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


