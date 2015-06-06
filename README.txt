Spanners Demo application
    
    
This application demonstrates various features and techniques described in Stuart 'Stevie' Leitch's blog: http://www.disasterarea.co.uk/blog/     
   
This demo contains:
    - Spring MVC web application
    - Struts web application
    - Spring-WS SOAP server application
    - DAO component shared by all applications
    
Building the demo
The whole demo can be built using Maven 3:
mvn clean install
Note that the MySQL database is required at build time to support integration tests. See spanners-struts/src/test/resources/tomcat/context.xml for connection details.
I run maven with increased memory options: MAVEN_OPTS=-Xms256m -Xmx1024m -XX:MaxPermSize=256m

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

Federated users
As of version 2.5, login is federated to SSO Circle (http://www.ssocircle.com/en/). Any user created in SSO Circle will be able to access Spanners.
Note that the SAML metadata is currently set up with http://localhost:8080/spanners-mvc so the app must be deployed on that URL.
       
Starting the applications
Each of the web applications can be deployed to Tomcat from the Tomcat Manager Console. Alternatively they can be deployed using maven:
mvn tomcat7:deploy


