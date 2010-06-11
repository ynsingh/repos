/*
SQLyog Community Edition- MySQL GUI v8.18 
MySQL - 5.0.37-community-nt : Database - gms_iitk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


/*Table structure for table `account_heads` */

DROP TABLE IF EXISTS `account_heads`;

CREATE TABLE `account_heads` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE001C601C4F4CD56` (`parent_id`),
  CONSTRAINT `FKE001C601C4F4CD56` FOREIGN KEY (`parent_id`) REFERENCES `account_heads` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `account_heads` */

/*Table structure for table `grant_allocation` */

DROP TABLE IF EXISTS `grant_allocation`;

CREATE TABLE `grant_allocation` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `allocation_type` varchar(255) NOT NULL,
  `amount_allocated` double NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `date_of_allocation` datetime NOT NULL,
  `granter_id` bigint(20) default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `party_id` bigint(20) NOT NULL,
  `projects_id` bigint(20) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `sanction_order_no` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK221B5BE337914517` (`granter_id`),
  KEY `FK221B5BE34B78CFDA` (`party_id`),
  KEY `FK221B5BE3905C4B9A` (`projects_id`),
  CONSTRAINT `FK221B5BE3905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK221B5BE337914517` FOREIGN KEY (`granter_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FK221B5BE34B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grant_allocation` */

/*Table structure for table `grant_allocation_split` */

DROP TABLE IF EXISTS `grant_allocation_split`;

CREATE TABLE `grant_allocation_split` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `account_head_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_period_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK49B723BE161A26D3` (`grant_period_id`),
  KEY `FK49B723BE3036284E` (`account_head_id`),
  KEY `FK49B723BEB8D959F3` (`grant_allocation_id`),
  KEY `FK49B723BE905C4B9A` (`projects_id`),
  CONSTRAINT `FK49B723BE905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK49B723BE161A26D3` FOREIGN KEY (`grant_period_id`) REFERENCES `grant_period` (`id`),
  CONSTRAINT `FK49B723BE3036284E` FOREIGN KEY (`account_head_id`) REFERENCES `account_heads` (`id`),
  CONSTRAINT `FK49B723BEB8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grant_allocation_split` */

/*Table structure for table `grant_allocation_tracking` */

DROP TABLE IF EXISTS `grant_allocation_tracking`;

CREATE TABLE `grant_allocation_tracking` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_tracking` datetime NOT NULL,
  `grant_allocation_id` bigint(20) NOT NULL,
  `grant_allocation_status` varchar(255) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK15B9A393B8D959F3` (`grant_allocation_id`),
  CONSTRAINT `FK15B9A393B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grant_allocation_tracking` */

/*Table structure for table `grant_expense` */

DROP TABLE IF EXISTS `grant_expense`;

CREATE TABLE `grant_expense` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_expense` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `expense_amount` double NOT NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_allocation_split_id` bigint(20) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK33905435B8D959F3` (`grant_allocation_id`),
  KEY `FK33905435905C4B9A` (`projects_id`),
  KEY `FK3390543533CE82BA` (`grant_allocation_split_id`),
  CONSTRAINT `FK3390543533CE82BA` FOREIGN KEY (`grant_allocation_split_id`) REFERENCES `grant_allocation_split` (`id`),
  CONSTRAINT `FK33905435905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK33905435B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grant_expense` */

/*Table structure for table `grant_period` */

DROP TABLE IF EXISTS `grant_period`;

CREATE TABLE `grant_period` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grant_period` */

/*Table structure for table `grant_receipt` */

DROP TABLE IF EXISTS `grant_receipt`;

CREATE TABLE `grant_receipt` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_receipt` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_allocation_split_id` bigint(20) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `reference_id` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC21DFE15B8D959F3` (`grant_allocation_id`),
  KEY `FKC21DFE15905C4B9A` (`projects_id`),
  KEY `FKC21DFE1533CE82BA` (`grant_allocation_split_id`),
  CONSTRAINT `FKC21DFE1533CE82BA` FOREIGN KEY (`grant_allocation_split_id`) REFERENCES `grant_allocation_split` (`id`),
  CONSTRAINT `FKC21DFE15905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKC21DFE15B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grant_receipt` */

/*Table structure for table `party` */

DROP TABLE IF EXISTS `party`;

CREATE TABLE `party` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `address` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `email` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name_of_the_institution` varchar(255) NOT NULL,
  `party_type` varchar(255) default NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `party` */

insert  into `party`(`id`,`version`,`active_yes_no`,`address`,`code`,`created_by`,`created_date`,`email`,`modified_by`,`modified_date`,`name_of_the_institution`,`party_type`,`phone`) values (1,1,'Y','IITK','IITK','',NULL,'',NULL,NULL,'',NULL,'');

/*Table structure for table `party_department` */

DROP TABLE IF EXISTS `party_department`;

CREATE TABLE `party_department` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `department_code` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7A7AD24B4B78CFDA` (`party_id`),
  CONSTRAINT `FK7A7AD24B4B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `party_department` */

/*Table structure for table `project_department_map` */

DROP TABLE IF EXISTS `project_department_map`;

CREATE TABLE `project_department_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `party_department_id` bigint(20) NOT NULL,
  `projects_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4C808CD580CA8247` (`party_department_id`),
  KEY `FK4C808CD5905C4B9A` (`projects_id`),
  CONSTRAINT `FK4C808CD5905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK4C808CD580CA8247` FOREIGN KEY (`party_department_id`) REFERENCES `party_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `project_department_map` */

/*Table structure for table `project_tracking` */

DROP TABLE IF EXISTS `project_tracking`;

CREATE TABLE `project_tracking` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_tracking` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `perc_of_completion` double NOT NULL,
  `project_status` varchar(255) NOT NULL,
  `projects_id` bigint(20) NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4DF711D905C4B9A` (`projects_id`),
  CONSTRAINT `FK4DF711D905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `project_tracking` */

/*Table structure for table `project_type` */

DROP TABLE IF EXISTS `project_type`;

CREATE TABLE `project_type` (
  `id` int(11) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `project_type` */

/*Table structure for table `projects` */

DROP TABLE IF EXISTS `projects`;

CREATE TABLE `projects` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) default NULL,
  `principal_investigator_name` varchar(255) NOT NULL,
  `project_duration` varchar(255) default NULL,
  `project_end_date` datetime default NULL,
  `project_start_date` datetime default NULL,
  `project_type_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC479187A69DF6AED` (`project_type_id`),
  KEY `FKC479187A43BA2D6A` (`parent_id`),
  CONSTRAINT `FKC479187A43BA2D6A` FOREIGN KEY (`parent_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKC479187A69DF6AED` FOREIGN KEY (`project_type_id`) REFERENCES `project_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `projects` */

/*Table structure for table `requestmap` */

DROP TABLE IF EXISTS `requestmap`;

CREATE TABLE `requestmap` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `config_attribute` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `requestmap` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`id`,`version`,`authority`,`description`) values (1,3,'ROLE_ADMIN','admin'),(2,28,'ROLE_USER','Grant Manager Role'),(3,1,'ROLE_SITEADMIN','ROLE_SITEADMIN');

/*Table structure for table `role_user` */

DROP TABLE IF EXISTS `role_user`;

CREATE TABLE `role_user` (
  `authorities_id` bigint(20) NOT NULL,
  `people_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`people_id`,`authorities_id`),
  KEY `FK1407FDF48F01F561` (`people_id`),
  KEY `FK1407FDF4CF6CDEE4` (`authorities_id`),
  CONSTRAINT `FK1407FDF4CF6CDEE4` FOREIGN KEY (`authorities_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK1407FDF48F01F561` FOREIGN KEY (`people_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role_user` */

insert  into `role_user`(`authorities_id`,`people_id`) values (1,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_show` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `user_real_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`version`,`description`,`email`,`email_show`,`enabled`,`passwd`,`user_real_name`,`username`) values (1,0,'admin','','\0','','86f7e437faa5a7fce15d1ddcb9eaeaea377667b8','admin','admin');

/*Table structure for table `user_map` */

DROP TABLE IF EXISTS `user_map`;

CREATE TABLE `user_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `party_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF022E2E8F7634DFA` (`user_id`),
  KEY `FKF022E2E84B78CFDA` (`party_id`),
  CONSTRAINT `FKF022E2E84B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FKF022E2E8F7634DFA` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_map` */

insert  into `user_map`(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`party_id`,`user_id`) values (1,1,'',NULL,'',NULL,1,1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` int(11) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


/*new Sql added on 6 th April 2010 */;

update project_type set active_yes_no='Y';
insert into `role` (`id`,`version`,`authority`,`description`) values (4,9,'ROLE_PI','ROLE_PI');



/*new Sql added on 13 th May 2010 */

UPDATE investigator SET active_yes_no='Y';

/*new Sql added on 11 th June 2010 */

UPDATE grant_period SET active_yes_no='Y',default_yes_no='Y';


