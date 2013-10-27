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

Installing the database
The DAO layer requires a MySQL database. The following will create the necessary table:

delimiter $$

CREATE TABLE `spanner` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `size` int(11) default NULL,
  `owner` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


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
Each of the web applications can be deployed to Tomcat from the Tomcat Manager Console. Alternatively they can be deployed using maven:
mvn tomcat7:deploy


