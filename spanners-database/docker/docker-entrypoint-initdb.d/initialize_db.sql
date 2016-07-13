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

/*
 * Spring Security users / authorities schema.
 * Adapted from https://docs.spring.io/spring-security/site/docs/4.0.1.RELEASE/reference/html/appendix-schema.html
 */
create table `user` (
    username varchar(50) not null primary key,
    password varchar(60) not null,
    enabled boolean not null
) engine = InnoDb DEFAULT CHARSET=latin1$$

create table `authority` (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references `user` (username),
    unique index authorities_idx_1 (username, authority)
) engine = InnoDb DEFAULT CHARSET=latin1$$
