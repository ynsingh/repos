/*
SQLyog Community v8.82 
MySQL - 5.0.41-log : Database - nfes
Database Version - 1.7 
Date - 06-02-2012
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values ('admin','ROLE_ADMIN');

/*Table structure for table `country_master` */

DROP TABLE IF EXISTS `country_master`;

CREATE TABLE `country_master` (
  `country_Id` int(11) NOT NULL auto_increment,
  `country_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `country_Id` (`country_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `country_master` */

/*Table structure for table `course_taught` */

DROP TABLE IF EXISTS `course_taught`;

CREATE TABLE `course_taught` (
  `idf` int(11) NOT NULL,
  `academic_term` varchar(255) NOT NULL default '',
  `class_name` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `students_registered` varchar(255) NOT NULL,
  `percent_of_pass` varchar(255) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `id` bigint(20) NOT NULL auto_increment,
  PRIMARY KEY  (`idf`,`academic_term`,`class_name`,`course_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `course_taught` */

/*Table structure for table `department_master` */

DROP TABLE IF EXISTS `department_master`;

CREATE TABLE `department_master` (
  `institution_id` int(11) NOT NULL default '0',
  `department_id` int(11) NOT NULL default '0',
  `active_yes_no` char(1) default NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`institution_id`,`department_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `department_master` */

/*Table structure for table `entity_document_master` */

DROP TABLE IF EXISTS `entity_document_master`;

CREATE TABLE `entity_document_master` (
  `id` int(11) NOT NULL default '0',
  `entity_id` int(11) default NULL,
  `entity_type` varchar(50) default NULL,
  `form_id` int(11) default NULL,
  `document_id` int(11) default NULL,
  `number` int(11) default NULL,
  `amended_yesno` smallint(1) default '0',
  `amended_document_id` int(11) default NULL,
  `addendum_yesno` smallint(1) default '0',
  `addendum_document_id` int(11) default NULL,
  `approved_yesno` char(1) default NULL,
  `active_yesno` smallint(1) default '1',
  `status_id` int(11) default '0',
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date_time` datetime default NULL,
  `created_by` varchar(50) default NULL,
  `created_date_time` datetime default NULL,
  `approved_by` varchar(50) default NULL,
  `approved_date` datetime default NULL,
  `public_record` varchar(1) default 'Y',
  PRIMARY KEY  (`id`),
  KEY `idx_entity_document_mastervalues_doc_id` (`document_id`),
  KEY `idx_entity_document_masterentity_id` (`entity_id`),
  KEY `idx_entity_document_masterform_master_id` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `entity_document_master` */

/*Table structure for table `event_scheduler` */

DROP TABLE IF EXISTS `event_scheduler`;

CREATE TABLE `event_scheduler` (
  `id` int(11) NOT NULL auto_increment,
  `idf` int(11) default NULL,
  `event_date` varchar(255) default NULL,
  `event_start_time` varchar(255) default NULL,
  `event_end_time` varchar(255) default NULL,
  `event_description` varchar(765) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `event_scheduler` */

/*Table structure for table `general_master` */

DROP TABLE IF EXISTS `general_master`;

CREATE TABLE `general_master` (
  `category` varchar(25) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `fld_value` varchar(100) NOT NULL default '',
  `active_yes_no` varchar(1) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`category`,`fld_value`),
  UNIQUE KEY `language_Id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `general_master` */

insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Academic_Term',82,'2007-2008','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Academic_Term',81,'2008-2009','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Academic_Term',69,'2009-2010','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Academic_Term',70,'2010-2011','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Affiliation',29,'Amrita','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Affiliation',32,'Others','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Attending_Purpose',62,'Participation','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Attending_Purpose',59,'Presented Paper','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Attending_Purpose',60,'Session Chair','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Attending_Purpose',61,'Trainer','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Committee_Type',49,'Conference','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Committee_Type',42,'Journal','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Committee_Type',102,'Others','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Committee_Type',50,'Project','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',34,' National Conference on Computational Neuroscience NCCNS-2011','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',33,'11th Anual Conference on Secure Communication','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',95,'12th Asia Pacific Management Conference','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',96,'2007 International Conference on Enterprise Information Systems and Web Technologies','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',99,'Academic Affairs Advisory Committee','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',101,'Administration Evaluation Committee','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',100,'Budjet Study Committee','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',94,'International Conference on Knowledge Management and Intellectual Capital-2009','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Conference_Name',103,'Tenure Committee ','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Consultancy_Facilities',40,'CDAC Lab','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Consultancy_Facilities',41,'IIT Kanpur Lab','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Country',5,'India','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Country',6,'Pakistan','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Country',9,'UK','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Country',7,'USA','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',127,'Administration','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',120,'BioEngineering','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',119,'BioTechnology','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',118,'Cardiology','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',63,'Computer Science','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',98,'Economics ','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',65,'Electronics','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',97,'General Management','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',116,'Micro Biology','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',117,'Neurology','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Department',64,'Physics','0',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Designation',128,'Administrator','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Designation',125,'Assistant Professor','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Designation',114,'HOD','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Designation',115,'Lecturer','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Designation',126,'Vice Chairperson (IT)','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Funding_Source',47,'Amrita','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Funding_Source',48,'Others','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',25,'ACST','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',28,'IJCIR','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',27,'IJICIT','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',90,'INDERSCINECE JOURNALS','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',93,'INDIAN ECONOMIC REVIEW','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',112,'Indian Heart Journal','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',111,'Indian Journal of Extra-Corporeal Technology','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',26,'JCSA','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',113,'Journal of Pharmacy Research','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',123,'Journal of Physical Chemistry ','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',88,'REVIEW OF BUSINESS RESEARCH','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Journal',92,'VISHLESHAN','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Language',1,'English','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Language',2,'Hindi','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Language',3,'Malayalam','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Language',4,'Tamil','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Language',8,'Telungu','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Media',10,'Magazines','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Media',11,'Newspapers','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Media',12,'Websites ','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Membership',14,'Associate','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Membership',15,'Fellow','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Membership',16,'Life','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Membership',18,'Others','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Membership',13,'Regular','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Participants_Level',45,'Faculty','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Participants_Level',46,'Industry Employees ','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Participants_Level',44,'UG/PG Students','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Professional_Body_Name',122,'IBSE','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Professional_Body_Name',37,'IIST','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Professional_Body_Name',38,'ISRO','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Professional_Body_Name',36,'NCERT','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Professional_Body_Name',39,'SCERT','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Project_Type',68,'External','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Project_Type',67,'In-house','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Project_Type',66,'Industry','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role',55,'Author','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role',56,'Co-Author','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role',58,'Compiled and Edited a book but not an author','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role',57,'Contributed chapter(s)','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role_Played',54,'Chairperson','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role_Played',43,'Director','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role_Played',52,'Editor','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role_Played',51,'Member','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Role_Played',53,'Reviewer','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',124,'Biotechnology','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',89,'Economics','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',109,'Embeded Systems','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',104,'Human Resource Development','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',105,'Human Resource Planning','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',91,'Marketing','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',108,'Networking','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',19,'Operating System','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',107,'Organisational Behaviour','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',87,'Quantitative Techniques and Operations Management','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',110,'Software Developement','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',20,'System Software','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Specialization',106,'Technology Management','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Title',129,'Mr.','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Title',130,'Mrs.','1',NULL);
insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Title',131,'Prof.','1',NULL);

/*Table structure for table `institution_master` */

DROP TABLE IF EXISTS `institution_master`;

CREATE TABLE `institution_master` (
  `name` varchar(255) NOT NULL default '',
  `address` varchar(255) default NULL,
  `short_name` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `university_id` int(11) NOT NULL default '0',
  `active_yes_no` char(1) default NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`name`,`university_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `institution_master` */

/*Table structure for table `intercept_url` */

DROP TABLE IF EXISTS `intercept_url`;

CREATE TABLE `intercept_url` (
  `id` int(11) NOT NULL auto_increment,
  `pattern` varchar(255) default NULL,
  `access` varchar(255) default NULL,
  `sequence` int(11) default NULL,
  `enabled` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `intercept_url` */

insert  into `intercept_url`(`id`,`pattern`,`access`,`sequence`,`enabled`) values (1,'/**','ROLE_ADMIN,ROLE_ADMIN_UNIVERSITY,ROLE_PROFILE_CREATION',1,'1');

/*Table structure for table `investigator_master` */

DROP TABLE IF EXISTS `investigator_master`;

CREATE TABLE `investigator_master` (
  `name` varchar(255) NOT NULL,
  `department_id` bigint(20) NOT NULL default '0',
  `designation` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `id` bigint(20) NOT NULL auto_increment,
  PRIMARY KEY  (`name`,`department_id`,`designation`,`email`,`address`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `investigator_master` */

/*Table structure for table `language_master` */

DROP TABLE IF EXISTS `language_master`;

CREATE TABLE `language_master` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `code` varchar(255) default NULL,
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `language_master` */

insert  into `language_master`(`id`,`name`,`code`,`active_yes_no`) values (1,'English','en','1');
insert  into `language_master`(`id`,`name`,`code`,`active_yes_no`) values (2,'Malayalam','ml','1');
insert  into `language_master`(`id`,`name`,`code`,`active_yes_no`) values (3,'Hindi','hi','1');

/*Table structure for table `media_types` */

DROP TABLE IF EXISTS `media_types`;

CREATE TABLE `media_types` (
  `media_types_Id` int(11) NOT NULL auto_increment,
  `media_types_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `media_types_Id` (`media_types_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `media_types` */

/*Table structure for table `membership_types` */

DROP TABLE IF EXISTS `membership_types`;

CREATE TABLE `membership_types` (
  `membership_type_Id` int(11) NOT NULL auto_increment,
  `membership_type_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `membership_type_Id` (`membership_type_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `membership_types` */

/*Table structure for table `profile_master` */

DROP TABLE IF EXISTS `profile_master`;

CREATE TABLE `profile_master` (
  `id` int(11) NOT NULL default '0',
  `form_name` varchar(255) default NULL,
  `version` varchar(50) default NULL,
  `title` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `document_type_id` int(11) default NULL,
  `form_type` smallint(1) default NULL,
  `active_yesno` smallint(1) default NULL,
  `show_draft_copy_yesno` smallint(1) NOT NULL default '1',
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date_time` datetime default NULL,
  `iso_document_number` varchar(150) default NULL,
  `scannable_yesno` smallint(1) default '0',
  `child_yesno` smallint(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*Data for the table `profile_master` */

insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (1,'staff_profile_report','v0','Staff Profile','Staff Profile',1,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (2,'staff_profile_qualification','v0','Staff Profile Qualification Details','Staff Profile Qualification Details',2,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (3,'staff_profile_awards','v0','Staff Profile Awards Details','Staff Profile Awards Details',3,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (4,'staff_profile_community_service','v0','Staff Profile Community Service Details','Staff Profile Community Service Details',4,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (5,'staff_profile_review_committees','v0','Staff Profile Review Committees Details','Staff Profile Review Committees Details',5,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (6,'staff_profile_governance','v0','Staff Profile Governance Details','Staff Profile Governance Details',6,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (7,'staff_profile_consultancy_offered','v0','Staff Profile Consultancy Details','Staff Profile Consultancy Details',7,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (8,'staff_profile_professional_body','v0','Staff Profile Professional Body Details','Staff Profile Professional Body Details',8,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (9,'staff_profile_faculty_exchange','v0','Staff Profile Faculty Exchange Details','Staff Profile Faculty Exchange Details',9,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (10,'staff_profile_thesis','v0','Staff Profile Thesis Details','Staff Profile Thesis Details',10,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (11,'staff_profile_media_publication','v0','Staff Profile Media Publication Details','Staff Profile Media Publication Details',11,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (12,'staff_profile_patents','v0','Staff Profile Patents Details','Staff Profile Patents Details',12,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (13,'staff_profile_projects','v0','Staff Profile Projects Details','Staff Profile Projects Details',13,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (14,'staff_profile_training','v0','Staff Profile Training Details','Staff Profile Training Details',14,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (15,'staff_profile_talks_lectures','v0','Staff Profile Talks Lectures Details','Staff Profile Talks Lectures Details',15,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (16,'staff_profile_books_chapter','v0','Staff Profile Books Chapter Details','Staff Profile Books Chapter Details',16,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (17,'staff_profile_conference_papers','v0','Staff Profile Conference Papers Details','Staff Profile Conference Papers Details',17,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (18,'staff_profile_journal_papers','v0','Staff Profile Journal Papers Details','Staff Profile Journal Papers Details',18,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (19,'staff_profile_personal_details','v0','Staff Profile Personal Details','Staff Profile Personal Details',19,0,1,1,'admin',NULL,NULL,0,NULL);
insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (20,'staff_profile_e_publication_links','v0','Staff Profile E Publication Links','Staff Profile E Publication Links',20,0,1,1,NULL,NULL,NULL,0,NULL);

/*Table structure for table `profile_series` */

DROP TABLE IF EXISTS `profile_series`;

CREATE TABLE `profile_series` (
  `series` varchar(255) NOT NULL default '',
  `max_number` int(11) default NULL,
  `last_modified_by` varchar(20) default NULL,
  `last_modified_date_time` datetime default NULL,
  `edition` int(11) default '0',
  PRIMARY KEY  (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile_series` */

insert  into `profile_series`(`series`,`max_number`,`last_modified_by`,`last_modified_date_time`,`edition`) values ('EntityDocument',1,NULL,NULL,0);
insert  into `profile_series`(`series`,`max_number`,`last_modified_by`,`last_modified_date_time`,`edition`) values ('ENTITY_DOCUMENT_MASTER',1,NULL,NULL,0);

/*Table structure for table `profile_type_master` */

DROP TABLE IF EXISTS `profile_type_master`;

CREATE TABLE `profile_type_master` (
  `id` int(11) default NULL,
  `document_type` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `active_yesno` smallint(1) default NULL,
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date_time` datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile_type_master` */

insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (1,'SPF','Staff Profile',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (2,'SP_QUALIFICATION','Staff Profile Qualification',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (3,'SP_AWARDS','Staff Profile Awards',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (4,'SP_COMMUNITY_SERVICE','Staff Profile Community Service',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (5,'SP_REVIEW_COMMITTEES','Staff Profile Review Committees',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (6,'SP_GOVERNANCE','Staff Profile Governance',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (7,'SP_CONSULTANCY_OFFERED','Staff Profile Consultancy Offered',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (8,'SP_PROFESSIONAL_BODY','Staff Profile Professional Body',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (9,'SP_FACULTY_EXCHANGE','Staff Profile Faculty Exchange',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (10,'SP_THESIS','Staff Profile Thesis',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (11,'SP_MEDIA_PUBLICATION','Staff Profile Media Publication',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (12,'SP_PATENTS','Staff Profile Patents',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (13,'SP_PROJECTS','Staff Profile Projects',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (14,'SP_TRAINING','Staff Profile Training',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (15,'SP_TALKS_LECTURES','Staff Profile Talks Lectures',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (16,'SP_BOOKS_CHAPTER','Staff Profile Books Chapter',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (17,'SP_CONFERENCE_PAPERS','Staff Profile Conference Papers',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (18,'SP_JOURNAL_PAPERS','Staff Profile Journal Papers',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (19,'SP_PERSONAL_DETAILS','Staff Profile Personal Details',1,'admin',NULL);
insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (20,'SP_E_PUBLICATION_LINKS','Staff Profile E Publication Links',1,'admin',NULL);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(50) default NULL,
  `active_yesno` varchar(1) default NULL,
  PRIMARY KEY  (`role_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`role_id`,`role_name`,`active_yesno`) values (1,'ROLE_ADMIN','1');
insert  into `roles`(`role_id`,`role_name`,`active_yesno`) values (2,'ROLE_PROFILE_CREATION','1');
insert  into `roles`(`role_id`,`role_name`,`active_yesno`) values (3,'ROLE_ADMIN_UNIVERSITY','1');

/*Table structure for table `specialization_master` */

DROP TABLE IF EXISTS `specialization_master`;

CREATE TABLE `specialization_master` (
  `specialization_Id` int(11) NOT NULL auto_increment,
  `specialization_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `specialization_Id` (`specialization_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `specialization_master` */

/*Table structure for table `staff_master` */

DROP TABLE IF EXISTS `staff_master`;

CREATE TABLE `staff_master` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) default NULL,
  `department_id` int(11) default NULL,
  `designation_id` int(11) default NULL,
  `join_date` varchar(10) default NULL,
  `relieve_date` varchar(10) default NULL,
  `active_yesno` smallint(1) default NULL,
  `record_staus` varchar(1) default NULL COMMENT 'Keep T for transfer related record',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_master` */

/*Table structure for table `staff_profile_awards_v0_items` */

DROP TABLE IF EXISTS `staff_profile_awards_v0_items`;

CREATE TABLE `staff_profile_awards_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_awards_v0_items` */

insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'award_name','Name_of_Award',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Award','blank',NULL,NULL,NULL);
insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'agency_name','Name_of_Agency',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Agency','blank',NULL,NULL,NULL);
insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'receiving_month_year','Month_Year_of_Receiving',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Month and Year of Receiving','calendar',NULL,NULL,NULL);
insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL);
insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'remarks','Remarks',NULL,'2011-01-29 16:17:06',NULL,'0000-00-00 00:00:00','Remarks','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'upload_image','Upload File',NULL,'2012-02-04 13:08:56',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_awards_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_awards_v0_itemtypes`;

CREATE TABLE `staff_profile_awards_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_awards_v0_itemtypes` */

insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:12:33','except','30 ',' ',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_awards_v0_values` */

DROP TABLE IF EXISTS `staff_profile_awards_v0_values`;

CREATE TABLE `staff_profile_awards_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `award_name` varchar(255) default NULL,
  `agency_name` varchar(255) default NULL,
  `receiving_month_year` varchar(100) default NULL,
  `url` varchar(255) default NULL,
  `remarks` text,
  `upload_image` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_awards_v0_values` */

/*Table structure for table `staff_profile_books_chapter_v0_items` */

DROP TABLE IF EXISTS `staff_profile_books_chapter_v0_items`;

CREATE TABLE `staff_profile_books_chapter_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_books_chapter_v0_items` */

insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'book_chapter','\"Book\"/\"Chapter\"',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Book/\"Chapter\"','radio_book',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'role','Role',NULL,'2011-01-28 12:02:03',NULL,'0000-00-00 00:00:00','Role','combo_box_role',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'title','Title',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Title','blank',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'author1','Author1',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Author','blank',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'author2','Author2',NULL,'2011-03-23 13:53:22',NULL,'0000-00-00 00:00:00','Authors','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'publisher','Publisher',NULL,'2011-01-22 09:46:13',NULL,'0000-00-00 00:00:00','Publisher','blank',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'affiliation','Affiliation',NULL,'2011-03-11 10:42:08',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (60,'url','URL if any',NULL,'2011-01-21 13:05:02',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'other_details','Other Details',NULL,'2011-01-22 14:02:18',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (70,'upload','Upload File',NULL,'2011-01-21 13:05:11',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'isbn','ISBN',NULL,'2011-01-21 13:07:13',NULL,'0000-00-00 00:00:00','ISBN','blank',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'publication_month_year','Month and Year of Publication',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Month and Year of Publication','calendar',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'no_of_pages','No of Pages',NULL,'2011-01-21 13:09:34',NULL,'0000-00-00 00:00:00','No of Pages','blank',NULL,NULL,NULL);
insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'language','Language',NULL,'2011-01-25 17:22:11',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);

/*Table structure for table `staff_profile_books_chapter_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_books_chapter_v0_itemtypes`;

CREATE TABLE `staff_profile_books_chapter_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_books_chapter_v0_itemtypes` */

insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_affiliation','None','oio','2011-03-11 10:46:31','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:49:52','except','30',' ',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_book','None','oio','2011-01-21 12:57:57','pickone_radio','Book,Chapter','Book,Chapter',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_language','None','oio','2011-03-07 14:06:58','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_role','None','oio','2011-03-14 14:50:54','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Role\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Role\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_books_chapter_v0_values` */

DROP TABLE IF EXISTS `staff_profile_books_chapter_v0_values`;

CREATE TABLE `staff_profile_books_chapter_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `book_chapter` varchar(255) default NULL,
  `role` text,
  `title` varchar(255) default NULL,
  `author1` varchar(255) default NULL,
  `author2` varchar(255) default NULL,
  `publisher` varchar(255) default NULL,
  `isbn` varchar(255) default NULL,
  `publication_month_year` varchar(255) default NULL,
  `no_of_pages` varchar(255) default NULL,
  `language` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_books_chapter_v0_values` */

/*Table structure for table `staff_profile_community_service_v0_items` */

DROP TABLE IF EXISTS `staff_profile_community_service_v0_items`;

CREATE TABLE `staff_profile_community_service_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_community_service_v0_items` */

insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'event_name','Name_of_Event',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Event','blank',NULL,NULL,NULL);
insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'location','Location',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Location','blank',NULL,NULL,NULL);
insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'activity_month_year','Month_Year_of_Activity',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Month Year of Activity','calendar',NULL,NULL,NULL);
insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL);
insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'other_details','Other_Details',NULL,'2011-01-22 14:08:01',NULL,'0000-00-00 00:00:00','Other_Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'upload_file','Upload_File',NULL,'2011-01-14 18:43:20',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_community_service_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_community_service_v0_itemtypes`;

CREATE TABLE `staff_profile_community_service_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_community_service_v0_itemtypes` */

insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:44:24','except','30 ',' ',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_community_service_v0_values` */

DROP TABLE IF EXISTS `staff_profile_community_service_v0_values`;

CREATE TABLE `staff_profile_community_service_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `event_name` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `activity_month_year` varchar(100) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload_file` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_community_service_v0_values` */

/*Table structure for table `staff_profile_conference_papers_v0_items` */

DROP TABLE IF EXISTS `staff_profile_conference_papers_v0_items`;

CREATE TABLE `staff_profile_conference_papers_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_conference_papers_v0_items` */

insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'conference_type','Conference Type',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Conference Type','radio_conference',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'research_expertise_area','Research Expertise Area',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Research Expertise Area','combo_box_specialization',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'paper_associated_project','Is the paper associated with a project',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Is the paper associated with a project','radio_associated_project',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'project_name','If Yes, Choose Project Name',NULL,'2011-02-03 14:15:23',NULL,'0000-00-00 00:00:00','If Yes, Choose Project Name','combo_box_projects',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'paper_title','Title of paper',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Title of paper','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'author1','Author1',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Author','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (85,'affiliation','Affiliation',NULL,'2011-03-10 21:19:57',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (90,'url','URL if any',NULL,'2011-01-21 14:59:42',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (100,'other_details','Other Details',NULL,'2011-01-22 14:05:13',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (105,'upload_paper','Upload File',NULL,'2011-01-21 15:48:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (95,'keyword','Keyword',NULL,'2011-01-21 15:01:33',NULL,'0000-00-00 00:00:00','Keyword','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'author2','Author2',NULL,'2011-03-23 13:49:54',NULL,'0000-00-00 00:00:00','Authors','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'month_year','Month and Year',NULL,'2011-01-21 15:22:33',NULL,'0000-00-00 00:00:00','Month and Year','calendar',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'abstract','Abstract',NULL,'2011-03-22 13:07:15',NULL,'0000-00-00 00:00:00','Abstract','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'pages','Pages',NULL,'2011-01-21 15:24:27',NULL,'0000-00-00 00:00:00','Pages','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'conference_name','Name of Conference',NULL,'2011-03-11 11:49:56',NULL,'0000-00-00 00:00:00','Name of Conference','combo_box_conference_name',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (60,'date_from','Conference Dates From',NULL,'2011-01-21 15:26:43',NULL,'0000-00-00 00:00:00','Conference Dates From','calendar',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'date_to','Conference Dates To',NULL,'2011-01-21 15:27:20',NULL,'0000-00-00 00:00:00','Conference Dates To','calendar',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (70,'conference_venue','Conference Venue',NULL,'2011-01-21 15:28:23',NULL,'0000-00-00 00:00:00','Conference Venue','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (75,'organizer_name','Name of Organizer',NULL,'2011-01-21 15:29:38',NULL,'0000-00-00 00:00:00','Name of Organizer','blank',NULL,NULL,NULL);
insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (80,'language','Language',NULL,'2011-01-25 17:25:11',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);

/*Table structure for table `staff_profile_conference_papers_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_conference_papers_v0_itemtypes`;

CREATE TABLE `staff_profile_conference_papers_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_conference_papers_v0_itemtypes` */

insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_conference_name','None','oio','2011-03-11 11:49:56','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Conference_Name\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Conference_Name\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:43:42','except','30 ',' ',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_conference','None','oio','2011-01-21 15:08:37','pickone_radio','National,International','National,International',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_associated_project','None','oio','2011-01-21 15:13:14','pickone_radio','Yes,No','Yes,No',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_language','None','oio','2011-03-07 13:49:23','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_projects','None','oio','2011-03-15 18:36:31','pickone_table','project_title','staff_profile_projects_v0_values',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_affiliation','None','oio','2011-03-10 21:10:44','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_specialization','None','oio','2011-03-10 17:55:10','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_conference_papers_v0_values` */

DROP TABLE IF EXISTS `staff_profile_conference_papers_v0_values`;

CREATE TABLE `staff_profile_conference_papers_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `conference_type` varchar(255) default NULL,
  `research_expertise_area` text,
  `paper_associated_project` varchar(255) default NULL,
  `project_name` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author1` varchar(255) default NULL,
  `author2` varchar(255) default NULL,
  `month_year` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `pages` varchar(255) default NULL,
  `conference_name` varchar(255) default NULL,
  `date_from` varchar(255) default NULL,
  `date_to` varchar(255) default NULL,
  `conference_venue` varchar(255) default NULL,
  `organizer_name` varchar(255) default NULL,
  `language` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `keyword` varchar(255) default NULL,
  `other_details` text,
  `upload_paper` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_conference_papers_v0_values` */

/*Table structure for table `staff_profile_consultancy_offered_v0_items` */

DROP TABLE IF EXISTS `staff_profile_consultancy_offered_v0_items`;

CREATE TABLE `staff_profile_consultancy_offered_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_consultancy_offered_v0_items` */

insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'client_name','Name_of_Client',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Client','blank',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'duration_from','Duration_From',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Duration From','calendar',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'duration_to','Duration_To',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Duration To','calendar',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'other_faculty_name','If other faculty involved,mention name(s) ',NULL,'2011-01-22 09:47:28',NULL,'0000-00-00 00:00:00','If other faculty involved,mention name(s) ','blank',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'revenue_generated','Revenue_Generated',NULL,'2011-01-17 13:07:22',NULL,'0000-00-00 00:00:00','Revenue Generated','blank',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'summary_work_undertaken','Brief_Summary_of_Work_Undertaken',NULL,'2011-01-17 13:01:25',NULL,'0000-00-00 00:00:00','Brief Summary of Work Undertaken','textarea',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'labs_used','Amrita_Labs/Facilities_used_for_Consultancy',NULL,'2011-03-11 13:04:04',NULL,'0000-00-00 00:00:00','Facilities used for Consultancy','combo_box_facilities_used',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'url','URL_if_any',NULL,'2011-01-17 13:08:57',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'other_details','Other_Details',NULL,'2011-01-22 14:10:00',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'upload','Upload_File',NULL,'2011-01-17 13:12:16',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_consultancy_offered_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_consultancy_offered_v0_itemtypes`;

CREATE TABLE `staff_profile_consultancy_offered_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_consultancy_offered_v0_itemtypes` */

insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,35','',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_facilities_used','None','oio','2011-03-11 13:04:04','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Consultancy_Facilities\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Consultancy_Facilities\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:44:55','except','30 ',' ',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_consultancy_offered_v0_values` */

DROP TABLE IF EXISTS `staff_profile_consultancy_offered_v0_values`;

CREATE TABLE `staff_profile_consultancy_offered_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `client_name` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(100) default NULL,
  `other_faculty_name` varchar(100) default NULL,
  `revenue_generated` varchar(255) default NULL,
  `summary_work_undertaken` varchar(255) default NULL,
  `labs_used` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_consultancy_offered_v0_values` */

/*Table structure for table `staff_profile_e_publication_links_v0_items` */

DROP TABLE IF EXISTS `staff_profile_e_publication_links_v0_items`;

CREATE TABLE `staff_profile_e_publication_links_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_e_publication_links_v0_items` */

insert  into `staff_profile_e_publication_links_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'journal_name','Journal Name',NULL,'2011-11-09 12:02:21',NULL,'0000-00-00 00:00:00','Journal Name','blank',NULL,NULL,NULL);
insert  into `staff_profile_e_publication_links_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'url','URL',NULL,'2011-11-09 12:02:21',NULL,'0000-00-00 00:00:00','URL','URL',NULL,NULL,NULL);
insert  into `staff_profile_e_publication_links_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'remarks','Remarks',NULL,'2011-11-09 12:02:21',NULL,'0000-00-00 00:00:00','Remarks','textarea',NULL,NULL,NULL);

/*Table structure for table `staff_profile_e_publication_links_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_e_publication_links_v0_itemtypes`;

CREATE TABLE `staff_profile_e_publication_links_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_e_publication_links_v0_itemtypes` */

insert  into `staff_profile_e_publication_links_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('URL','None','oio','2010-12-04 16:56:17','text_area','5,35','',NULL);
insert  into `staff_profile_e_publication_links_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:12:33','except','65 ',' ',NULL);
insert  into `staff_profile_e_publication_links_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','textarea','oio','2011-01-29 16:12:33','text_area','5,35','',NULL);

/*Table structure for table `staff_profile_e_publication_links_v0_values` */

DROP TABLE IF EXISTS `staff_profile_e_publication_links_v0_values`;

CREATE TABLE `staff_profile_e_publication_links_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `journal_name` varchar(255) default NULL,
  `url` text,
  `remarks` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_e_publication_links_v0_values` */

/*Table structure for table `staff_profile_faculty_exchange_v0_items` */

DROP TABLE IF EXISTS `staff_profile_faculty_exchange_v0_items`;

CREATE TABLE `staff_profile_faculty_exchange_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_faculty_exchange_v0_items` */

insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'institution_name','Name_of_Institutions_Involved',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Institutions Involved','blank',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'subjects_taught','Name_of_Subjects_Taught',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Subjects Taught','blank',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'duration_from','Duration_From',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Duration From','calendar',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'duration_to','Duration_To',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Duration To','calendar',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'ug_or_pg','UG_or_PG',NULL,'2011-01-17 18:58:34',NULL,'0000-00-00 00:00:00','UG or PG','radio_button',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'research_collaborations','Research_Collaborations_if_any',NULL,'2011-01-17 19:00:10',NULL,'0000-00-00 00:00:00','Research Collaborations if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'url','URL_if_any',NULL,'2011-01-17 19:02:46',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'other_details','Other_Details',NULL,'2011-01-22 14:10:58',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'upload','File_Upload',NULL,'2011-01-17 19:04:39',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_faculty_exchange_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_faculty_exchange_v0_itemtypes`;

CREATE TABLE `staff_profile_faculty_exchange_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_faculty_exchange_v0_itemtypes` */

insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:45:05','except','30 ',' ',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_button','None','oio','2011-01-17 18:51:51','pickone_radio','UG,PG','UG,PG',NULL);

/*Table structure for table `staff_profile_faculty_exchange_v0_values` */

DROP TABLE IF EXISTS `staff_profile_faculty_exchange_v0_values`;

CREATE TABLE `staff_profile_faculty_exchange_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `institution_name` varchar(255) default NULL,
  `subjects_taught` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(255) default NULL,
  `ug_or_pg` varchar(255) default NULL,
  `research_collaborations` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_faculty_exchange_v0_values` */

/*Table structure for table `staff_profile_governance_v0_items` */

DROP TABLE IF EXISTS `staff_profile_governance_v0_items`;

CREATE TABLE `staff_profile_governance_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_governance_v0_items` */

insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'committee_name','Name_of_Committee/Panel',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Committee/Panel','blank',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'school_name','Name_of_School/University',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of School/University','combo_box',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'service_dates_from','Service_Dates_From',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Service Dates From ','calendar',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'service_dates_to','Service_Dates_To',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Service Dates To ','calendar',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'responsibilities','Responsibilities',NULL,'2011-01-17 10:59:33',NULL,'0000-00-00 00:00:00','Responsibilities','blank',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'url','URL',NULL,'2011-01-17 10:59:13',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'upload','Upload',NULL,'2011-01-17 11:00:45',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'other_details','Other_Details',NULL,'2011-01-22 14:09:11',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_governance_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_governance_v0_itemtypes`;

CREATE TABLE `staff_profile_governance_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_governance_v0_itemtypes` */

insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2011-01-17 11:42:13','pickone','Amrita,Others','Amrita,Others',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-02-01 09:37:31','except','30 ',' ',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_governance_v0_values` */

DROP TABLE IF EXISTS `staff_profile_governance_v0_values`;

CREATE TABLE `staff_profile_governance_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `committee_name` varchar(255) default NULL,
  `school_name` varchar(255) default NULL,
  `service_dates_from` varchar(100) default NULL,
  `service_dates_to` varchar(100) default NULL,
  `responsibilities` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_governance_v0_values` */

/*Table structure for table `staff_profile_journal_papers_v0_items` */

DROP TABLE IF EXISTS `staff_profile_journal_papers_v0_items`;

CREATE TABLE `staff_profile_journal_papers_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_journal_papers_v0_items` */

insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'journal_type','Journal Type',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Publication Type','radio_journal',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'research_expertise_area','Research Expertise Area',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Research Expertise Area','combo_box_specialization',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'paper_associated_project','Is the paper associated with a project',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Is the paper associated with a project','radio_associated_project',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'project_name','If Yes, Choose Project Name',NULL,'2011-02-03 12:37:47',NULL,'0000-00-00 00:00:00','If Yes, Choose Project Name','combo_box_projects',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'paper_title','Title of paper',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Title of paper','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'author1','Author1',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Author','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (85,'affiliation','Affiliation',NULL,'2011-03-10 18:31:22',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (90,'url','URL if any',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','URL if any','URL',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (100,'other_details','Other Details',NULL,'2011-01-22 14:06:24',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (105,'upload_paper','Upload File',NULL,'2011-01-21 15:48:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (95,'keyword','Keyword',NULL,'2011-01-21 15:01:33',NULL,'0000-00-00 00:00:00','Keyword','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'author2','Author2',NULL,'2011-03-23 13:51:25',NULL,'0000-00-00 00:00:00','Authors','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'journal_name','Journal Name',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Publication Name','combo_box_journals',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'volume','Volume',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Volume','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'issue_no','Issue No',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Issue No','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'month_year','Month and Year',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Month and Year','calendar',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (60,'abstract','Abstract',NULL,'2011-03-22 13:03:31',NULL,'0000-00-00 00:00:00','Abstract','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'pages','Pages',NULL,'2011-01-21 18:00:50',NULL,'0000-00-00 00:00:00','Pages','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (70,'impact_factor','Impact Factor',NULL,'2011-01-21 18:01:40',NULL,'0000-00-00 00:00:00','Impact Factor','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (75,'average_citation_index','Average Citation Index',NULL,'2011-01-21 18:02:46',NULL,'0000-00-00 00:00:00','Average Citation Index','blank',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (80,'language','Language',NULL,'2011-01-25 09:30:36',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);
insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (110,'indexed_in_scopus','Whether Indexed in Scopus?(Y/N)',NULL,'2011-01-21 18:05:33',NULL,'0000-00-00 00:00:00','Whether Indexed in Scopus?(Y/N)','radio_associated_project',NULL,NULL,NULL);

/*Table structure for table `staff_profile_journal_papers_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_journal_papers_v0_itemtypes`;

CREATE TABLE `staff_profile_journal_papers_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_journal_papers_v0_itemtypes` */

insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_affiliation','None','oio','2011-03-10 18:33:57','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:17:34','except','30',' ',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_journal','None','oio','2011-01-21 17:40:21','pickone_radio','National,International','National,International',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_associated_project','None','oio','2011-01-21 15:13:14','pickone_radio','Yes,No','Yes,No',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_language','None','oio','2011-03-07 13:42:45','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_specialization','None','oio','2011-03-09 20:50:14','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_journals','None','oio','2011-04-02 15:20:54','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Journal\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Journal\' \r\nAND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_projects','None','oio','2011-03-15 18:03:42','pickone_table','project_title','staff_profile_projects_v0_values',NULL);
insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('URL','None','oio','2010-12-04 16:56:17','except','50','',NULL);

/*Table structure for table `staff_profile_journal_papers_v0_values` */

DROP TABLE IF EXISTS `staff_profile_journal_papers_v0_values`;

CREATE TABLE `staff_profile_journal_papers_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `journal_type` varchar(255) default NULL,
  `research_expertise_area` text,
  `paper_associated_project` varchar(255) default NULL,
  `project_name` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author1` varchar(255) default NULL,
  `author2` varchar(255) default NULL,
  `journal_name` varchar(255) default NULL,
  `volume` varchar(255) default NULL,
  `issue_no` varchar(255) default NULL,
  `month_year` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `pages` varchar(255) default NULL,
  `impact_factor` varchar(255) default NULL,
  `average_citation_index` varchar(255) default NULL,
  `indexed_in_scopus` varchar(255) default NULL,
  `language` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `keyword` varchar(255) default NULL,
  `other_details` text,
  `upload_paper` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_journal_papers_v0_values` */

/*Table structure for table `staff_profile_media_publication_v0_items` */

DROP TABLE IF EXISTS `staff_profile_media_publication_v0_items`;

CREATE TABLE `staff_profile_media_publication_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_media_publication_v0_items` */

insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'media_type','Type of Media',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Type of Media','combo_box_media_types',NULL,NULL,NULL);
insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'title','Title',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Title','blank',NULL,NULL,NULL);
insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'magazine_name','Name of Magazine/Media',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name of Magazine/Media<span \nstyle=\"color:red\">*</span>','blank',NULL,NULL,NULL);
insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'article_month_year','Month and Year of appearance of article',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Month and Year of appearance of article','calendar',NULL,NULL,NULL);
insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'url','URL if any',NULL,'2011-01-19 16:39:08',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'other_details','Other Details',NULL,'2011-01-22 14:12:42',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'upload','Upload File',NULL,'2011-01-19 16:43:25',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_media_publication_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_media_publication_v0_itemtypes`;

CREATE TABLE `staff_profile_media_publication_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_media_publication_v0_itemtypes` */

insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:45:39','except','30 ',' ',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_media_types','None','oio','2011-03-09 18:24:28','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Media\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Media\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_media_publication_v0_values` */

DROP TABLE IF EXISTS `staff_profile_media_publication_v0_values`;

CREATE TABLE `staff_profile_media_publication_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `media_type` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `magazine_name` varchar(255) default NULL,
  `article_month_year` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_media_publication_v0_values` */

/*Table structure for table `staff_profile_patents_v0_items` */

DROP TABLE IF EXISTS `staff_profile_patents_v0_items`;

CREATE TABLE `staff_profile_patents_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_patents_v0_items` */

insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'choose_patent','Choose if patent is \"National\" or \"International\"',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Choose if patent is \"National\" or \"International\"','radio_choose',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'country','if International, pick country',NULL,'2011-01-28 15:40:34',NULL,'0000-00-00 00:00:00','if International, pick country','combo_box_country',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'patent_title','Title of patent',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Title of patent','blank',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'registration_month_year','Month and Year of patent registration',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Month and Year of patent registration','calendar',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'name_co_applicant','Co-Applicant(s) Name(if any)',NULL,'2011-01-22 09:49:18',NULL,'0000-00-00 00:00:00','Co-Applicant(s) Name(if any)','blank',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'patent_details','Patent Details',NULL,'2011-01-22 13:51:18',NULL,'0000-00-00 00:00:00','Patent Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'affiliation','Affiliation',NULL,'2011-03-11 10:51:51',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'url','URL if any',NULL,'2011-01-20 11:11:04',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'other_details','Other Details',NULL,'2011-01-22 13:54:16',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'upload','Upload File',NULL,'2011-01-20 11:58:33',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_patents_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_patents_v0_itemtypes`;

CREATE TABLE `staff_profile_patents_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_patents_v0_itemtypes` */

insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_affiliation','None','oio','2011-03-11 10:51:51','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:45:49','except','30 ',' ',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_choose','None','oio','2011-01-20 11:41:18','pickone_radio','National,International','National,International',NULL);
insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_country','None','oio','2011-06-29 14:49:19','pickone_master','SELECT \'-Select-\' `fld_value` FROM DUAL UNION\nSELECT fld_value FROM(SELECT fld_value FROM general_master WHERE category=\'Country\' AND active_yes_no=1 ORDER BY fld_value)A','SELECT \'\' `id` FROM DUAL UNION\nSELECT id FROM(SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value)A',NULL);

/*Table structure for table `staff_profile_patents_v0_values` */

DROP TABLE IF EXISTS `staff_profile_patents_v0_values`;

CREATE TABLE `staff_profile_patents_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `choose_patent` varchar(255) default NULL,
  `country` varchar(255) default NULL,
  `patent_title` varchar(255) default NULL,
  `registration_month_year` varchar(255) default NULL,
  `name_co_applicant` varchar(255) default NULL,
  `patent_details` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_patents_v0_values` */

/*Table structure for table `staff_profile_personal_details_v0_items` */

DROP TABLE IF EXISTS `staff_profile_personal_details_v0_items`;

CREATE TABLE `staff_profile_personal_details_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_personal_details_v0_items` */

insert  into `staff_profile_personal_details_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'focus_area','Area of Study / Focus Area',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Area of Study / Focus Area','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_personal_details_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'research_interests','Research Interests',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Research Interests','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_personal_details_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'teaching_interests','Teaching Interests',NULL,'2011-02-05 17:04:23',NULL,'0000-00-00 00:00:00','Teaching Interests','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_personal_details_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'positions_held','Positions Held',NULL,'2011-02-05 17:04:26',NULL,'0000-00-00 00:00:00','Positions Held','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_personal_details_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_personal_details_v0_itemtypes`;

CREATE TABLE `staff_profile_personal_details_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_personal_details_v0_itemtypes` */

insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2011-01-22 16:39:40','text_area','5,50',NULL,NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2011-01-22 16:40:58','pickone','1,2,3','1,2,3',NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('tableColHeader','None','oio','2011-01-22 16:39:40','show_text',' ',NULL,NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:11:55','except','30',NULL,NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2011-01-22 16:39:40','calendar','',NULL,NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2011-01-29 16:04:26','text_area','3,30','',NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2011-01-22 16:41:30','pickone','1,2,3,4,5,6,7,8,9,10,11,12','1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2011-01-22 16:41:41','pickone','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2011-01-22 16:39:40','text_area','15,70',NULL,NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank_small','None','oio','2011-01-22 16:39:40','except','7',NULL,NULL);
insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-22 16:39:40','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_personal_details_v0_values` */

DROP TABLE IF EXISTS `staff_profile_personal_details_v0_values`;

CREATE TABLE `staff_profile_personal_details_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `focus_area` varchar(255) default NULL,
  `research_interests` text,
  `teaching_interests` text,
  `positions_held` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_personal_details_v0_values` */

/*Table structure for table `staff_profile_professional_body_v0_items` */

DROP TABLE IF EXISTS `staff_profile_professional_body_v0_items`;

CREATE TABLE `staff_profile_professional_body_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_professional_body_v0_items` */

insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'professional_body_name','Name/Details_of_Professional_Body/Society ',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Name/Details of The Professional Body/Society','combo_box_professional_body',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'membership_from','Membership_From',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Membership From','calendar',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'membership_to','Membership_To',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Membership To','calendar',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'membership_type','Membership_Type',NULL,'2011-01-28 15:24:06',NULL,'0000-00-00 00:00:00','Membership Type','combo_box_membership_types',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'membership_number','Membership_Number',NULL,'2011-01-22 09:49:38',NULL,'0000-00-00 00:00:00','Membership Number','blank',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'other_details','Other_Details',NULL,'2011-01-22 14:10:47',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'upload','Upload_File',NULL,'2011-01-17 16:04:04',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'url','URL_if_any',NULL,'2011-01-17 16:05:31',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);

/*Table structure for table `staff_profile_professional_body_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_professional_body_v0_itemtypes`;

CREATE TABLE `staff_profile_professional_body_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_professional_body_v0_itemtypes` */

insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:45:59','except','30 ',' ',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_membership_types','None','oio','2011-03-09 18:53:19','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Membership\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Membership\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_professional_body','None','oio','2011-03-11 12:29:11','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Professional_Body_Name\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Professional_Body_Name\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_professional_body_v0_values` */

DROP TABLE IF EXISTS `staff_profile_professional_body_v0_values`;

CREATE TABLE `staff_profile_professional_body_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `professional_body_name` varchar(255) default NULL,
  `membership_from` varchar(255) default NULL,
  `membership_to` varchar(255) default NULL,
  `membership_type` varchar(255) default NULL,
  `membership_number` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_professional_body_v0_values` */

/*Table structure for table `staff_profile_projects_v0_items` */

DROP TABLE IF EXISTS `staff_profile_projects_v0_items`;

CREATE TABLE `staff_profile_projects_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_projects_v0_items` */

insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'project_status','Project Status',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Project Status','radio_status',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'project_title','Project Title',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Project Title','blank',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'principal_investigator','Principal Investigator(PI)',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Principal Investigator(PI)','combo_box_PI',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'start_date','Project Start Date',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Project Start Date','calendar',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'duration','Duration',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Duration','blank',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'amount','Amount',NULL,'2012-02-04 13:08:57',NULL,'0000-00-00 00:00:00','Amount','blank',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'project_center_department','Which Center/Department the project is associated with',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Which Center/Department the project is associated with','combo_box_dept',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'abstract','Abstract',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Abstract','blank',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'funding_source','Source of Funding',NULL,'2011-03-11 15:08:42',NULL,'0000-00-00 00:00:00','Source of Funding','combo_box_funding_source',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'url','URL if any',NULL,'2011-01-20 13:30:17',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'other_details','Other Details',NULL,'2011-01-22 13:55:12',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (60,'upload','Upload File',NULL,'2011-03-26 12:10:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (16,'co_pi','Co-PI(s)',NULL,'2011-03-26 12:12:00',NULL,'0000-00-00 00:00:00','Co-PI(s)','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_projects_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_projects_v0_itemtypes`;

CREATE TABLE `staff_profile_projects_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_projects_v0_itemtypes` */

insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_funding_source','None','oio','2011-03-11 15:08:42','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:46:07','except','30 ',' ',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_status','None','oio','2011-01-20 13:37:02','pickone_radio','Ongoing,Completed','Ongoing,Completed',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_PI','None','oio','2011-03-14 21:32:21','pickone_master','SELECT name FROM investigator_master WHERE active_yes_no=1 ORDER BY name','SELECT id FROM investigator_master WHERE active_yes_no=1 ORDER BY name',NULL);
insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_dept','None','oio','2011-03-14 17:40:38','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Department\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Department\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_projects_v0_values` */

DROP TABLE IF EXISTS `staff_profile_projects_v0_values`;

CREATE TABLE `staff_profile_projects_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `project_status` varchar(255) default NULL,
  `project_title` varchar(255) default NULL,
  `principal_investigator` varchar(255) default NULL,
  `start_date` varchar(255) default NULL,
  `duration` varchar(255) default NULL,
  `amount` varchar(255) default NULL,
  `project_center_department` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `funding_source` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL,
  `co_pi` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author` varchar(255) default NULL,
  `research_expertise_area` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_projects_v0_values` */

/*Table structure for table `staff_profile_qualification_v0_items` */

DROP TABLE IF EXISTS `staff_profile_qualification_v0_items`;

CREATE TABLE `staff_profile_qualification_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_qualification_v0_items` */

insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'course_name','Course Name',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Qualification','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'institution','Institution',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Institution','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'location','Location',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Location','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'board','Board',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Board','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'join_month','Month',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Month','month_combo_box',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'join_year','Year',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Year','year_combo_box',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'passing_year','Passing Year',NULL,'2011-01-29 11:26:35',NULL,'0000-00-00 00:00:00','Passing Year','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'percentage','Percentage',NULL,'2011-01-29 11:27:06',NULL,'0000-00-00 00:00:00','Percentage','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'class','Class',NULL,'2011-01-29 11:26:39',NULL,'0000-00-00 00:00:00','Class','blank',NULL,NULL,NULL);
insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'upload','Upload File','admin','2011-08-27 14:08:04',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_qualification_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_qualification_v0_itemtypes`;

CREATE TABLE `staff_profile_qualification_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_qualification_v0_itemtypes` */

insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:46:17','except','30 ',' ',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2011-01-24 17:00:38','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2011-06-29 14:49:19','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank_small','None','oio','2010-12-06 10:02:44','except','7','',NULL);
insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-08-27 14:08:04','file_upload','','',NULL);

/*Table structure for table `staff_profile_qualification_v0_values` */

DROP TABLE IF EXISTS `staff_profile_qualification_v0_values`;

CREATE TABLE `staff_profile_qualification_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `course_name` varchar(255) default NULL,
  `institution` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `board` varchar(255) default NULL,
  `join_month` varchar(255) default NULL,
  `join_year` varchar(255) default NULL,
  `passing_year` varchar(255) default NULL,
  `percentage` varchar(255) default NULL,
  `class` varchar(255) default NULL,
  `mainSubj` varchar(255) default NULL,
  `co_pi` varchar(255) default NULL,
  `upload` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_qualification_v0_values` */

/*Table structure for table `staff_profile_report_v0_items` */

DROP TABLE IF EXISTS `staff_profile_report_v0_items`;

CREATE TABLE `staff_profile_report_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_items` */

insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'upload_resume','Upload Resume :',NULL,'2011-02-09 18:21:23',NULL,'0000-00-00 00:00:00','Upload Resume','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'focus_area','Area of Study / Focus Area',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Area of Study / Focus Area','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'qualification','Qualification',NULL,'2011-01-13 16:46:59',NULL,'0000-00-00 00:00:00','Qualification','qualification_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'research_interests','Research Interests',NULL,'2011-01-29 16:04:49',NULL,'0000-00-00 00:00:00','Research Interests','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'teaching_interests','Teaching Interests',NULL,'2011-01-29 16:05:20',NULL,'0000-00-00 00:00:00','Teaching Interests','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'positions_held','Positions Held',NULL,'2011-01-29 16:05:22',NULL,'0000-00-00 00:00:00','Positions Held','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'office_room_num','Office Room Number',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Office Room Number','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'office_phone_ext','Office Phone Ext.',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Office Phone Ext.','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'email_id','Amrita Email ID',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Email ID','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'email','Personal Email ID',NULL,'2011-01-13 11:30:47',NULL,'0000-00-00 00:00:00','Personal Email ID','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'home_phone','Home Phone',NULL,'2011-01-13 11:31:00',NULL,'0000-00-00 00:00:00','Home Phone','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'fax','Fax',NULL,'2011-01-13 11:31:06',NULL,'0000-00-00 00:00:00','Fax','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (70,'awards','Award Details',NULL,'2011-01-22 12:46:39',NULL,'0000-00-00 00:00:00','Awards Details','awards_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (75,'community_service','Community Service',NULL,'2011-01-22 12:46:40',NULL,'0000-00-00 00:00:00','Community Service Details','community_service_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (80,'review_committees','Review Committees',NULL,'2011-01-22 12:46:41',NULL,'0000-00-00 00:00:00','Review Committees Details','review_committees_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (85,'governance','Governance',NULL,'2011-01-22 12:46:44',NULL,'0000-00-00 00:00:00','Governance Details','governance_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (90,'consultancy_offered','Consultancy Offered',NULL,'2011-01-22 12:46:45',NULL,'0000-00-00 00:00:00','Consultancy Offered Details','consultancy_offered_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (95,'professional_body','Professional Bodies',NULL,'2011-01-22 12:46:47',NULL,'0000-00-00 00:00:00','Professional Bodies/Societies Details','professional_body_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (100,'faculty_exchange','Faculty_Exchange',NULL,'2011-01-22 12:46:48',NULL,'0000-00-00 00:00:00','Faculty Exchange Program Details','faculty_exchange_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (105,'thesis','Thesis/Student Project',NULL,'2011-01-22 12:46:49',NULL,'0000-00-00 00:00:00','Thesis/Student Project Details','thesis_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (110,'media_publication','Media Publication',NULL,'2011-01-22 12:46:51',NULL,'0000-00-00 00:00:00','Media Publication Details','media_publication_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (115,'patents','Patents',NULL,'2011-01-22 12:46:54',NULL,'0000-00-00 00:00:00','Patents Details','patents_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (120,'projects','Projects',NULL,'2011-01-22 12:46:56',NULL,'0000-00-00 00:00:00','Projects Details','projects_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (125,'training','Training',NULL,'2011-01-22 12:46:58',NULL,'0000-00-00 00:00:00','Seminars/Conferences/Workshops/Training Attended Details','training_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (130,'talks_lectures','Talks Lectures',NULL,'2011-01-22 12:47:00',NULL,'0000-00-00 00:00:00','Invited Talks/Guest Lectures Details','talks_lectures_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (140,'books_chapter','Books/Chapter',NULL,'2011-01-22 12:47:02',NULL,'0000-00-00 00:00:00','Books/Chapter Details','books_chapter_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (145,'conference_papers','Conference Papers',NULL,'2011-01-22 12:47:04',NULL,'0000-00-00 00:00:00','Conference Papers Details','conference_papers_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (150,'journal_papers','Journal Papers',NULL,'2011-01-22 12:47:07',NULL,'0000-00-00 00:00:00','Journal Papers Details','journal_papers_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (155,'personal_details_tab_capion','Personal Details',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Personal Details','tableColHeader',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (160,'upload_photo','Upload Photo',NULL,'2011-02-09 18:03:27',NULL,'0000-00-00 00:00:00','Upload Photo','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (170,'academic_term','Choose Academic term',NULL,'2011-03-16 15:16:55',NULL,'0000-00-00 00:00:00','Choose Academic term','combo_box_academic_term',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (175,'pmt_Address1','Permanent Address1',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Address1','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (180,'pmt_Address2','Permanent Address2',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Address2','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (185,'pmt_City','Permanent Address City',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','City','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (190,'pmt_District','Permanent Address District',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','District','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (195,'pmt_state','Permanent Address State',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','State','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (200,'pmt_country','Permanent Address country',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Country','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (205,'pmt_pincode','Permanent Address Pincode',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Pincode','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (210,'pmt_phone','Permanent Address Phone',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Phone','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (215,'comm_Address1','Communication Address1',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Address1','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (220,'comm_Address2','Communication Address2',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Address2','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (225,'comm_City','Communication Address City',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','City','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (230,'comm_District','Communication Address District',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','District','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (235,'comm_state','Communication Address State',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','State','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (240,'comm_country','Communication Address country',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Country','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (245,'comm_pincode','Communication Address Pincode',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Pincode','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (250,'comm_phone','Communication Address Phone',NULL,'2011-05-23 15:41:24',NULL,'0000-00-00 00:00:00','Phone','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (260,'personal_gender','Personal Gender',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Gender','radio_gender',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (265,'personal_date_of_birth','Personal Date Of Birth',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Date Of Birth','calendar',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (270,'personal_joining_date','Personal Joining Date',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Joining Date','calendar',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (275,'personal_nationality','Personal Nationality',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Nationality','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (280,'personal_native_place','Personal Native Place',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Native Place','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (285,'personal_native_country','Personal Native Country',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Native Country','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (290,'personal_native_state','Personal Native State',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Native State','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (295,'personal_native_district','Personal Native District',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Native District','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (300,'personal_community','Personal Community',NULL,'2012-02-04 13:08:54',NULL,'0000-00-00 00:00:00','Community','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (305,'personal_religion','Personal Religion',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','Religion','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (310,'personal_caste','Personal Caste',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','Caste','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (315,'personal_mother_tongue','Personal Mother Tongue',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','Mother Tongue','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (320,'personal_marital_status','Personal Marital Status',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','Marital Status','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (325,'dept_pan_card_no','Department PAN Card No',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','PAN Card No','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (330,'dept_passport_no','Department Passport No',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','Passport No','blank',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (335,'account_passport_valid_upto','Account Passport Valid Upto',NULL,'2012-02-04 13:08:55',NULL,'0000-00-00 00:00:00','Passport Valid Upto','calendar',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (340,'health_blood_group','Health Blood Group',NULL,'2012-02-04 13:08:56',NULL,'0000-00-00 00:00:00','Blood Group','bloodgroup_combo_box',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (345,'personal_details','Professional Details',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Professional Details','personal_details_childform',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (355,'contact_details','Qualification',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','Contact Details','tableColHeader',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (360,'file_upload','Qualification',NULL,'2012-02-04 13:36:46',NULL,'0000-00-00 00:00:00','File Upload','tableColHeader',NULL,NULL,NULL);
insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (361,'e_publication_links','E Publication Links',NULL,'2011-11-09 11:51:00',NULL,'0000-00-00 00:00:00','E-Publication Links','e_publication_links_childform',NULL,NULL,NULL);

/*Table structure for table `staff_profile_report_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_report_v0_itemtypes`;

CREATE TABLE `staff_profile_report_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_itemtypes` */

insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2011-01-22 16:39:40','text_area','5,50',NULL,NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2011-01-22 16:40:58','pickone','1,2,3','1,2,3',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('tableColHeader','None','oio','2011-01-22 16:39:40','show_text',' ',NULL,NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:11:55','except','30',NULL,NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2012-02-04 13:08:54','calendar','','',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2011-01-29 16:04:26','text_area','3,30','',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2011-01-22 16:41:30','pickone','1,2,3,4,5,6,7,8,9,10,11,12','1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2011-01-22 16:41:41','pickone','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('paper_status_combo_box','None','oio','2011-01-22 16:41:59','pickone','Select,Communicated,Accepted,Published','Select,Communicated,Accepted,Published',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('paper_type_combo_box','None','oio','2011-01-22 16:42:11','pickone','Select,International,National','Select,International,National',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('jouranltype_combo_box','None','oio','2011-01-22 16:42:22','pickone','Select,Journal,Conference Proceedings','Select,Journal,Conference Proceedings',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('seminar_purpose_combo_box','None','oio','2011-01-22 16:42:29','pickone','Select,Presented Paper,Participation','Select,Presented Paper,Participation',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2011-01-22 16:39:40','text_area','15,70',NULL,NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank_small','None','oio','2011-01-22 16:39:40','except','7',NULL,NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-02-09 18:15:56','file_upload','','',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('qualification_childform','Qualification','oio','2011-03-28 13:42:42','detail_form','staff_profile_qualification_v0','course_name,institution,location,board,join_month,join_year,passing_year,percentage,class',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('awards_childform','Awards','oio','2011-03-28 13:23:48','detail_form','staff_profile_awards_v0','award_name,agency_name,receiving_month_year',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('community_service_childform','Community Service','oio','2011-03-28 13:23:55','detail_form','staff_profile_community_service_v0','event_name,location,activity_month_year',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('review_committees_childform','Review Committees','oio','2011-04-08 13:52:19','detail_form','staff_profile_review_committees_v0','title,appointed_month_year',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('governance_childform','Governance','oio','2011-03-28 13:24:51','detail_form','staff_profile_governance_v0','committee_name,school_name,service_dates_from,service_dates_to',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('consultancy_offered_childform','Consultancy Offered','oio','2011-03-28 13:24:58','detail_form','staff_profile_consultancy_offered_v0','client_name,duration_from,duration_to',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('professional_body_childform','Professional Bodies','oio','2011-04-13 12:54:17','detail_form','staff_profile_professional_body_v0','membership_from,membership_to',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('faculty_exchange_childform','Faculty Exchange','oio','2011-03-28 13:25:09','detail_form','staff_profile_faculty_exchange_v0','institution_name,subjects_taught,duration_from,duration_to',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('thesis_childform','Student Project','oio','2011-03-28 13:25:14','detail_form','staff_profile_thesis_v0','choose,thesis_title,project_title',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('media_publication_childform','Media Publication','oio','2011-04-13 12:51:55','detail_form','staff_profile_media_publication_v0','title,magazine_name,article_month_year',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('patents_childform','Patents','oio','2011-04-13 12:51:11','detail_form','staff_profile_patents_v0','choose_patent,patent_title,registration_month_year',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('projects_childform','Projects','oio','2011-04-13 12:51:17','detail_form','staff_profile_projects_v0','project_status,project_title,start_date,duration,amount',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('training_childform','Seminar','oio','2011-03-28 15:47:26','detail_form','staff_profile_training_v0','training_name,duration_from,duration_to,location',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('talks_lectures_childform','Invited Talks','oio','2011-03-28 13:26:19','detail_form','staff_profile_talks_lectures_v0','institution_address,event_name,lecture_topic,talk_date_time',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('books_chapter_childform','Books/Chapters','oio','2011-03-28 13:26:30','detail_form','staff_profile_books_chapter_v0','book_chapter,title,author1,publication_month_year',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('conference_papers_childform','Conference Papers','oio','2011-04-05 16:50:01','detail_form','staff_profile_conference_papers_v0','conference_type,paper_title,author1',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('journal_papers_childform','Publications','oio','2012-02-04 13:36:47','detail_form','staff_profile_journal_papers_v0','journal_type,paper_title,author1,month_year,url',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('personal_details_childform','Profile','oio','2011-03-28 15:34:44','detail_form','staff_profile_personal_details_v0','focus_area,research_interests,teaching_interests,positions_held',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_academic_term','Academic Term','oio','2011-06-29 14:49:19','pickone_master','\nSELECT \'-Select-\' `fld_value` FROM DUAL UNION \nSELECT * FROM (\nSELECT fld_value FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value\n)A','SELECT 0 `id` FROM DUAL UNION  SELECT * FROM \n(SELECT id FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 \nORDER BY fld_value)A',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_gender','None','oio','2012-02-04 13:08:53','pickone_radio','Male,Female','Male,Female',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('bloodgroup_combo_box','None','oio','2012-02-04 13:36:46','pickone','-Select-,O+,O-,A+,A-,B+,B-,AB+,AB-','-Select-,O+,O-,A+,A-,B+,B-,AB+,AB-',NULL);
insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('e_publication_links_childform','E Publication Links','oio','2011-11-09 11:55:00','detail_form','staff_profile_e_publication_links_v0','journal_name,url,remarks',NULL);

/*Table structure for table `staff_profile_report_v0_values` */

DROP TABLE IF EXISTS `staff_profile_report_v0_values`;

CREATE TABLE `staff_profile_report_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `RoomNoQuartersNo` varchar(255) default NULL,
  `PhoneResidenceHostel` varchar(255) default NULL,
  `upload_resume` varchar(255) default NULL,
  `focus_area` varchar(255) default NULL,
  `qualification` varchar(255) default NULL,
  `research_interests` varchar(255) default NULL,
  `teaching_interests` varchar(255) default NULL,
  `positions_held` varchar(255) default NULL,
  `office_room_num` varchar(255) default NULL,
  `office_phone_ext` varchar(255) default NULL,
  `email_id` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `home_phone` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `awards` varchar(255) default NULL,
  `community_service` varchar(255) default NULL,
  `review_committees` varchar(255) default NULL,
  `governance` varchar(255) default NULL,
  `consultancy_offered` varchar(255) default NULL,
  `professional_body` varchar(255) default NULL,
  `faculty_exchange` varchar(255) default NULL,
  `thesis` varchar(255) default NULL,
  `media_publication` varchar(255) default NULL,
  `patents` varchar(255) default NULL,
  `projects` varchar(255) default NULL,
  `training` varchar(255) default NULL,
  `talks_lectures` varchar(255) default NULL,
  `books_chapter` varchar(255) default NULL,
  `conference_papers` varchar(255) default NULL,
  `journal_papers` varchar(255) default NULL,
  `personal_details_tab_capion` varchar(255) default NULL,
  `upload_photo` varchar(255) default NULL,
  `academic_term` varchar(255) default NULL,
  `pmt_Address1` varchar(255) default NULL,
  `pmt_Address2` varchar(255) default NULL,
  `pmt_City` varchar(255) default NULL,
  `pmt_District` varchar(255) default NULL,
  `pmt_state` varchar(255) default NULL,
  `pmt_country` varchar(255) default NULL,
  `pmt_pincode` varchar(255) default NULL,
  `pmt_phone` varchar(255) default NULL,
  `comm_Address1` varchar(255) default NULL,
  `comm_Address2` varchar(255) default NULL,
  `comm_City` varchar(255) default NULL,
  `comm_District` varchar(255) default NULL,
  `comm_state` varchar(255) default NULL,
  `comm_country` varchar(255) default NULL,
  `comm_pincode` varchar(255) default NULL,
  `comm_phone` varchar(255) default NULL,
  `personal_title` varchar(255) default NULL,
  `personal_gender` varchar(255) default NULL,
  `personal_date_of_birth` varchar(255) default NULL,
  `personal_joining_date` varchar(255) default NULL,
  `personal_nationality` varchar(255) default NULL,
  `personal_native_place` varchar(255) default NULL,
  `personal_native_country` varchar(255) default NULL,
  `personal_native_state` varchar(255) default NULL,
  `personal_native_district` varchar(255) default NULL,
  `personal_community` varchar(255) default NULL,
  `personal_religion` varchar(255) default NULL,
  `personal_caste` varchar(255) default NULL,
  `personal_mother_tongue` varchar(255) default NULL,
  `personal_marital_status` varchar(255) default NULL,
  `dept_pan_card_no` varchar(255) default NULL,
  `dept_passport_no` varchar(255) default NULL,
  `account_passport_valid_upto` varchar(255) default NULL,
  `health_blood_group` varchar(255) default NULL,
  `personal_details` varchar(255) default NULL,
  `contact_details` varchar(255) default NULL,
  `file_upload` varchar(255) default NULL,
  `e_publication_links` text,
  KEY `idx_Rad_val` (`idf`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_values` */

/*Table structure for table `staff_profile_review_committees_v0_items` */

DROP TABLE IF EXISTS `staff_profile_review_committees_v0_items`;

CREATE TABLE `staff_profile_review_committees_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_review_committees_v0_items` */

insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'committee_type','Committee_Type',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Commitee Type','committee_type_combo_box',NULL,NULL,NULL);
insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'role_played','Role_Played',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Role Played','role_played_combo_box',NULL,NULL,NULL);
insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'title','Title',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Title','blank',NULL,NULL,NULL);
insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL);
insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'other_details','Other_Details',NULL,'2011-01-22 14:08:41',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'file_upload','File_Upload',NULL,'2011-01-15 15:08:44',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'appointed_month_year','Month and Year Appointed',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Appointed Month and Year','calendar',NULL,NULL,NULL);

/*Table structure for table `staff_profile_review_committees_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_review_committees_v0_itemtypes`;

CREATE TABLE `staff_profile_review_committees_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_review_committees_v0_itemtypes` */

insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:46:37','except','30 ',' ',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('committee_type_combo_box','None','oio','2011-03-11 13:26:47','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Committee_Type\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Committee_Type\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('role_played_combo_box','None','oio','2011-03-11 13:26:47','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Role_Played\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Role_Played\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_review_committees_v0_values` */

DROP TABLE IF EXISTS `staff_profile_review_committees_v0_values`;

CREATE TABLE `staff_profile_review_committees_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `committee_type` varchar(255) default NULL,
  `role_played` varchar(255) default NULL,
  `title` varchar(100) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `file_upload` varchar(255) default NULL,
  `appointed_month_year` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_review_committees_v0_values` */

/*Table structure for table `staff_profile_talks_lectures_v0_items` */

DROP TABLE IF EXISTS `staff_profile_talks_lectures_v0_items`;

CREATE TABLE `staff_profile_talks_lectures_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_talks_lectures_v0_items` */

insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'event','Was the event :\"National\" or \"International\"',NULL,'2011-01-22 09:51:14',NULL,'0000-00-00 00:00:00','Was the event :\"National\" or \"International\"','radio_event',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'institution_address','Name and Address of the Institution',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Name and Address of the Institution','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'event_name','Name of the event',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Name of the event','blank',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'lecture_topic','Lecture Topic',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Lecture Topic','blank',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'talk_date_time','Date and Time of Talk',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Date and Time of Talk','datetimepicker',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'participants_level','Participants Level',NULL,'2011-03-11 14:29:07',NULL,'0000-00-00 00:00:00','Participants Level','combo_box_participants_level',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'related_research_group','Related Research Group and Centres',NULL,'2011-01-20 18:04:11',NULL,'0000-00-00 00:00:00','Related Research Group and Centres','blank',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'url','URL if any',NULL,'2011-01-20 18:04:37',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'other_details','Other Details',NULL,'2011-01-22 13:58:15',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (60,'upload','Upload File',NULL,'2011-01-20 18:04:49',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'duration_from','Duration From',NULL,'2011-01-20 18:05:44',NULL,'0000-00-00 00:00:00','Duration From','calendar',NULL,NULL,NULL);
insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'duration_to','Duration To',NULL,'2011-01-20 18:06:27',NULL,'0000-00-00 00:00:00','Duration To','calendar',NULL,NULL,NULL);

/*Table structure for table `staff_profile_talks_lectures_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_talks_lectures_v0_itemtypes`;

CREATE TABLE `staff_profile_talks_lectures_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_talks_lectures_v0_itemtypes` */

insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_participants_level','None','oio','2011-03-11 14:29:07','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Participants_Level\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Participants_Level\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:46:47','except','30 ',' ',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_event','None','oio','2011-01-20 17:54:28','pickone_radio','National,International','National,International',NULL);
insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('datetimepicker','Date And Time Picker Control','oio','2011-06-29 14:49:19','datetimepicker','','',NULL);

/*Table structure for table `staff_profile_talks_lectures_v0_values` */

DROP TABLE IF EXISTS `staff_profile_talks_lectures_v0_values`;

CREATE TABLE `staff_profile_talks_lectures_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `event` varchar(255) default NULL,
  `institution_address` text,
  `event_name` varchar(255) default NULL,
  `lecture_topic` varchar(255) default NULL,
  `talk_date_time` varchar(255) default NULL,
  `participants_level` varchar(255) default NULL,
  `related_research_group` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_talks_lectures_v0_values` */

/*Table structure for table `staff_profile_thesis_v0_items` */

DROP TABLE IF EXISTS `staff_profile_thesis_v0_items`;

CREATE TABLE `staff_profile_thesis_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_thesis_v0_items` */

insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'choose','Choose \"Thesis\" or \"Student Project\"',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Choose \"Thesis\" or \"Student Project\"','radio_choose',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'thesis_title','Title of Thesis',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Title of Thesis','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'thesis_author','Author',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Author','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'thesis_department_institution','Department and Institution',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Department and Institution','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'thesis_course_programe','Course/Programe',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Course/Programe','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'thesis_external_internal','External or Internal',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','External or Internal','radio_thesis_external_internal',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'thesis_registration_month_year','Month and Year of Registration',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Month and Year of Registration','calendar',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'thesis_outcome','Outcome',NULL,'2011-01-19 11:59:34',NULL,'0000-00-00 00:00:00','Outcome','radio_thesis_outcome',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'thesis_completion_month_year','Month and Year of Completion',NULL,'2011-01-18 19:00:32',NULL,'0000-00-00 00:00:00','Month and Year of Completion','calendar',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'thesis_url','URL if any',NULL,'2011-01-18 19:01:52',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (55,'thesis_other_details','Other Details',NULL,'2011-01-22 14:11:38',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (60,'thesis_upload','Upload File',NULL,'2011-01-18 19:04:40',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'project_title','Title of Student Project',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Title of Student Project','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (70,'project_student_group','Number of Student/Group',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Number of Student/Group','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (75,'project_student_name','Name of Students',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Name of Students','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (80,'project_type','Project Type',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Project Type','combo_box_project_type',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (85,'project_academic_term','Choose Academic Term when Project Started',NULL,'2011-03-16 18:28:05',NULL,'0000-00-00 00:00:00','Choose Academic Term when Project Started','combo_box_project_academic_term',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (90,'project_start_month_year','Month and Year of Start',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Month and Year of Start','calendar',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (95,'project_outcome','Outcome',NULL,'2011-01-19 12:00:07',NULL,'0000-00-00 00:00:00','Outcome','radio_project_outcome',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (100,'project_completion_month_year','Month and Year of Completion',NULL,'2011-01-19 09:54:34',NULL,'0000-00-00 00:00:00','Month and Year of Completion','calendar',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (105,'project_url','URL if any',NULL,'2011-01-19 09:55:49',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (110,'project_other_details','Other Details',NULL,'2011-01-22 14:11:54',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (115,'project_upload','Upload File',NULL,'2011-01-19 09:59:25',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_thesis_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_thesis_v0_itemtypes`;

CREATE TABLE `staff_profile_thesis_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_thesis_v0_itemtypes` */

insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_project_academic_term','None','oio','2011-03-16 18:28:05','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:46:57','except','30 ',' ',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_choose','None','oio','2011-01-24 09:32:19','pickone_radio_choose','Thesis,Student Project','Thesis,Student Project',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_thesis_external_internal','None','oio','2011-01-19 12:04:31','pickone_radio','External,Internal','External,Internal',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_thesis_outcome','None','oio','2011-01-19 12:06:19','pickone_radio','In Progress,Completed','In Progress,Completed',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_project_outcome','None','oio','2011-01-19 12:07:58','pickone_radio','In Progress,Completed','In Progress,Completed',NULL);
insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_project_type','None','oio','2011-03-22 19:30:28','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Project_Type\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Project_Type\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_thesis_v0_values` */

DROP TABLE IF EXISTS `staff_profile_thesis_v0_values`;

CREATE TABLE `staff_profile_thesis_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',

  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `choose` varchar(255) default NULL,
  `thesis_title` varchar(255) default NULL,
  `thesis_author` varchar(255) default NULL,
  `thesis_department_institution` varchar(255) default NULL,
  `thesis_course_programe` varchar(255) default NULL,
  `thesis_external_internal` varchar(255) default NULL,
  `thesis_registration_month_year` varchar(255) default NULL,
  `thesis_outcome` varchar(255) default NULL,
  `thesis_completion_month_year` varchar(255) default NULL,
  `thesis_url` varchar(255) default NULL,
  `thesis_other_details` text,
  `thesis_upload` varchar(255) default NULL,
  `project_title` varchar(255) default NULL,
  `project_student_group` varchar(255) default NULL,
  `project_student_name` varchar(255) default NULL,
  `project_type` varchar(255) default NULL,
  `project_academic_term` varchar(255) default NULL,
  `project_start_month_year` varchar(255) default NULL,
  `project_outcome` varchar(255) default NULL,
  `project_completion_month_year` varchar(255) default NULL,
  `project_url` varchar(255) default NULL,
  `project_other_details` text,
  `project_upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_thesis_v0_values` */

/*Table structure for table `staff_profile_training_v0_items` */

DROP TABLE IF EXISTS `staff_profile_training_v0_items`;

CREATE TABLE `staff_profile_training_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_items` */

insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'training_name','Name of Seminar/Conference/Workshop/Training',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Name of Seminar/Conference/Workshop/Training','blank',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (10,'duration_from','Duration From',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Duration From','calendar',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (15,'duration_to','Duration To',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Duration To','calendar',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (20,'location','Location',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Location','blank',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (25,'purpose','Purpose of attending the Seminar / Conference',NULL,'2011-03-14 17:20:26',NULL,'0000-00-00 00:00:00','Purpose of attending the Seminar / Conference','combo_box_purpose',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (30,'funding_source','Source of Funding',NULL,'2011-03-11 14:51:33',NULL,'0000-00-00 00:00:00','Source of Funding','combo_box_funding_source',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (35,'funded_amount','Amount Funded',NULL,'2011-01-20 16:12:47',NULL,'0000-00-00 00:00:00','Amount Funded','blank',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (40,'url','URL if any',NULL,'2011-01-20 11:11:04',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (45,'other_details','Other Details',NULL,'2011-01-22 13:56:52',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (50,'upload','Upload File',NULL,'2011-01-20 11:58:33',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (26,'paper_title','Title of paper',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Title of paper','blank',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (27,'author','Author',NULL,'2011-03-23 13:49:57',NULL,'0000-00-00 00:00:00','Author','textarea_small',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (28,'research_expertise_area','Research Expertise Area',NULL,'2012-02-04 13:36:47',NULL,'0000-00-00 00:00:00','Research Expertise Area','combo_box_specialization',NULL,NULL,NULL);
insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (29,'abstract','Abstract',NULL,'2011-03-22 13:07:15',NULL,'0000-00-00 00:00:00','Abstract','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_training_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_training_v0_itemtypes`;

CREATE TABLE `staff_profile_training_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_itemtypes` */

insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_funding_source','None','oio','2011-03-11 14:52:54','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('blank','None','oio','2011-01-29 16:47:09','except','30 ',' ',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('radio_choose','None','oio','2011-01-20 11:41:18','pickone_radio','National,International','National,International',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_purpose','None','oio','2011-03-10 21:10:44','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Attending_Purpose\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Attending_Purpose\' AND active_yes_no=1 ORDER BY fld_value',NULL);
insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('combo_box_specialization','None','oio','2011-03-10 17:55:10','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_training_v0_values` */

DROP TABLE IF EXISTS `staff_profile_training_v0_values`;

CREATE TABLE `staff_profile_training_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `training_name` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `purpose` varchar(255) default NULL,
  `funding_source` varchar(255) default NULL,
  `funded_amount` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author` varchar(255) default NULL,
  `research_expertise_area` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_values` */

/*Table structure for table `staff_profile_xml` */

DROP TABLE IF EXISTS `staff_profile_xml`;

CREATE TABLE `staff_profile_xml` (
  `id` int(11) NOT NULL auto_increment,
  `table_name` varchar(255) default NULL,
  `table_fields` text,
  `active_yesno` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_xml` */

insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (1,'staff_profile_report_v0_values','office_room_num,office_phone_ext,email_id,personal_native_place,personal_native_country,personal_native_state,personal_native_district,personal_community,personal_religion,personal_caste,personal_mother_tongue,personal_marital_status,dept_pan_card_no,dept_passport_no,account_passport_valid_upto,health_blood_group,email,home_phone,fax,pmt_Address1,pmt_Address2,pmt_City,pmt_District,pmt_state,pmt_country,pmt_pincode,pmt_phone,comm_Address1,comm_Address2,comm_City,comm_District,comm_state,comm_country,comm_pincode,comm_phone,upload_resume,upload_photo','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (2,'staff_profile_personal_details_v0_values','focus_area,research_interests,teaching_interests,positions_held','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (3,'staff_profile_qualification_v0_values','course_name,institution,location,board,join_month,join_year,passing_year,percentage,class','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (4,'staff_profile_awards_v0_values','award_name,receiving_month_year,agency_name,url,remarks,upload_image','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (5,'staff_profile_journal_papers_v0_values','journal_type,research_expertise_area,paper_associated_project,paper_title,author1,journal_name,volume,issue_no,month_year,project_name,author2,abstract,pages,impact_factor,average_citation_index,indexed_in_scopus,language,affiliation,url,keyword,other_details,upload_paper','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (6,'staff_profile_conference_papers_v0_values','conference_type,research_expertise_area,paper_associated_project,paper_title,author1,project_name,author2,month_year,abstract,pages,conference_name,date_from,date_to,conference_venue,organizer_name,language,affiliation,url,keyword,other_details,upload_paper','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (7,'staff_profile_books_chapter_v0_values','book_chapter,title,author1,publication_month_year,role,author2,publisher,isbn,no_of_pages,language,affiliation,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (8,'staff_profile_talks_lectures_v0_values','institution_address,event_name,lecture_topic,talk_date_time,event,participants_level,related_research_group,duration_from,duration_to,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (9,'staff_profile_training_v0_values','training_name,duration_from,duration_to,location,purpose,paper_title,author,research_expertise_area,abstract,funding_source,funded_amount,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (10,'staff_profile_projects_v0_values','project_status,project_title,principal_investigator,start_date,duration,amount,project_center_department,abstract,co_pi,funding_source,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (11,'staff_profile_patents_v0_values','choose_patent,patent_title,registration_month_year,country,name_co_applicant,patent_details,affiliation,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (12,'staff_profile_media_publication_v0_values','media_type,title,magazine_name,article_month_year,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (13,'staff_profile_thesis_v0_values','choose,thesis_title,thesis_author,thesis_department_institution,thesis_course_programe,thesis_external_internal,thesis_registration_month_year,thesis_outcome,thesis_completion_month_year,thesis_url,thesis_other_details,thesis_upload,project_title,project_student_group,project_student_name,project_type,project_start_month_year,project_academic_term,project_outcome,project_completion_month_year,project_url,project_other_details,project_upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (14,'staff_profile_faculty_exchange_v0_values','institution_name,subjects_taught,duration_from,duration_to,ug_or_pg,research_collaborations,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (15,'staff_profile_professional_body_v0_values','professional_body_name,membership_from,membership_to,membership_type,membership_number,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (16,'staff_profile_consultancy_offered_v0_values','client_name,duration_from,duration_to,other_faculty_name,revenue_generated,summary_work_undertaken,labs_used,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (17,'staff_profile_governance_v0_values','committee_name,school_name,service_dates_from,service_dates_to,responsibilities,url,other_details,upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (18,'staff_profile_review_committees_v0_values','committee_type,role_played,title,appointed_month_year,url,other_details,file_upload','1');
insert  into `staff_profile_xml`(`id`,`table_name`,`table_fields`,`active_yesno`) values (19,'staff_profile_community_service_v0_values','event_name,location,activity_month_year,url,other_details,upload_file','1');

/*Table structure for table `university_master` */

DROP TABLE IF EXISTS `university_master`;

CREATE TABLE `university_master` (
  `name` varchar(255) NOT NULL default '',
  `short_name` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `active_yes_no` char(1) default NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `university_master` */

/*Table structure for table `user_openID_map` */

DROP TABLE IF EXISTS `user_openID_map`;

CREATE TABLE `user_openID_map` (
  `user_id` varchar(50) default NULL,
  `openid` varchar(255) NOT NULL default '',
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`openid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user_openID_map` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL default '',
  `email` varchar(100) default NULL,
  `enabled` tinyint(1) NOT NULL default '0',
  `active_yesno` smallint(1) default '0',
  `id` int(11) default NULL,
  `user_full_name` varchar(255) default NULL,
  `institution_id` int(4) default NULL,
  `department_id` int(11) default NULL COMMENT 'department_master.id',
  `designation_id` int(11) default NULL COMMENT 'general_master.id of designation category',
  `title` varchar(15) default NULL,
  `last_name` varchar(255) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`email`,`enabled`,`active_yesno`,`id`,`user_full_name`,`institution_id`,`department_id`,`designation_id`,`title`,`last_name`) values ('admin','1e7b14af87817daafc0532d7db5307f671890a748e885d3bc37f2a3efcd8d5e4','admin@gmail.com',1,0,1,'ADMINISTRATOR',1,NULL,NULL,'','');

/*Table structure for table `staff_profile_masterdetails_v0_values` */

DROP TABLE IF EXISTS `staff_profile_masterdetails_v0_values`;

/*!50001 DROP VIEW IF EXISTS `staff_profile_masterdetails_v0_values` */;
/*!50001 DROP TABLE IF EXISTS `staff_profile_masterdetails_v0_values` */;

/*!50001 CREATE TABLE  `staff_profile_masterdetails_v0_values`(
 `user_full_name` varchar(255) ,
 `last_name` varchar(255) ,
 `user_title` varchar(15) ,
 `full_name` longtext ,
 `username` varchar(50) ,
 `email` varchar(100) ,
 `userid` int(11) ,
 `idf` int(11) ,
 `University` varchar(255) ,
 `University_id` int(11) ,
 `Institution` varchar(255) ,
 `Institution_id` int(11) ,
 `Department` varchar(100) ,
 `Designation` varchar(100) ,
 `department_id` int(11) ,
 `join_Date` varchar(10) 
)*/;

/*Table structure for table `staff_profile_personaldetails_v0_values` */

DROP TABLE IF EXISTS `staff_profile_personaldetails_v0_values`;

/*!50001 DROP VIEW IF EXISTS `staff_profile_personaldetails_v0_values` */;
/*!50001 DROP TABLE IF EXISTS `staff_profile_personaldetails_v0_values` */;

/*!50001 CREATE TABLE  `staff_profile_personaldetails_v0_values`(
 `idf` int(11) ,
 `office_room_num` varchar(255) ,
 `office_phone_ext` varchar(255) ,
 `email_id` varchar(255) ,
 `email` varchar(255) ,
 `home_phone` varchar(255) ,
 `fax` varchar(255) ,
 `DOB` varchar(255) ,
 `gender` varchar(255) ,
 `community` varchar(255) ,
 `photo` varchar(255) 
)*/;

/*View structure for view staff_profile_masterdetails_v0_values */

/*!50001 DROP TABLE IF EXISTS `staff_profile_masterdetails_v0_values` */;
/*!50001 DROP VIEW IF EXISTS `staff_profile_masterdetails_v0_values` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `staff_profile_masterdetails_v0_values` AS select `users`.`user_full_name` AS `user_full_name`,`users`.`last_name` AS `last_name`,`users`.`title` AS `user_title`,trim(concat(ifnull(`users`.`title`,_latin1''),_latin1' ',`users`.`user_full_name`,_latin1' ',ifnull(`users`.`last_name`,_latin1''))) AS `full_name`,`users`.`username` AS `username`,`users`.`email` AS `email`,`users`.`id` AS `userid`,`users`.`id` AS `idf`,`university_master`.`name` AS `University`,`university_master`.`id` AS `University_id`,`institution_master`.`name` AS `Institution`,`institution_master`.`id` AS `Institution_id`,`general_master`.`fld_value` AS `Department`,`A`.`fld_value` AS `Designation`,`staff_master`.`department_id` AS `department_id`,`staff_master`.`join_date` AS `join_Date` from ((((((`staff_master` join `department_master` on(((`department_master`.`id` = `staff_master`.`department_id`) and (`staff_master`.`active_yesno` = 1)))) join `institution_master` on((`institution_master`.`id` = `department_master`.`institution_id`))) join `university_master` on((`university_master`.`id` = `institution_master`.`university_id`))) join `general_master` on(((`general_master`.`id` = `department_master`.`department_id`) and (`general_master`.`category` = _latin1'Department')))) join `general_master` `A` on(((`A`.`id` = `staff_master`.`designation_id`) and (`A`.`category` = _latin1'Designation')))) join `users` on(((`staff_master`.`userid` = `users`.`id`) and (`users`.`enabled` = 1) and (`users`.`username` <> _latin1'admin')))) */;

/*View structure for view staff_profile_personaldetails_v0_values */

/*!50001 DROP TABLE IF EXISTS `staff_profile_personaldetails_v0_values` */;
/*!50001 DROP VIEW IF EXISTS `staff_profile_personaldetails_v0_values` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `staff_profile_personaldetails_v0_values` AS select `staff_profile_report_v0_values`.`idf` AS `idf`,`staff_profile_report_v0_values`.`office_room_num` AS `office_room_num`,`staff_profile_report_v0_values`.`office_phone_ext` AS `office_phone_ext`,`staff_profile_report_v0_values`.`email_id` AS `email_id`,`staff_profile_report_v0_values`.`email` AS `email`,`staff_profile_report_v0_values`.`home_phone` AS `home_phone`,`staff_profile_report_v0_values`.`fax` AS `fax`,`staff_profile_report_v0_values`.`personal_date_of_birth` AS `DOB`,`staff_profile_report_v0_values`.`personal_gender` AS `gender`,`staff_profile_report_v0_values`.`personal_community` AS `community`,`staff_profile_report_v0_values`.`upload_photo` AS `photo` from `staff_profile_report_v0_values` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
