CREATE SCHEMA `spanners` ;

USE `spanners`;

delimiter $$

CREATE TABLE `spanner` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `size` int(11) default NULL,
  `owner` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

GRANT ALL PRIVILEGES ON spanners.* TO "spanners"@"localhost" IDENTIFIED BY "password";