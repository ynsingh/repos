/*
SQLyog Community v8.63 
MySQL - 5.0.37-community-nt : Database - nfes_01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nfes_01` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nfes_01`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values ('admin','ROLE_TELLER'),('rajeshkumarkp@amrita.edu','ROLE_TELLER');

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
  PRIMARY KEY  (`id`),
  KEY `idx_entity_document_mastervalues_doc_id` (`document_id`),
  KEY `idx_entity_document_masterentity_id` (`entity_id`),
  KEY `idx_entity_document_masterform_master_id` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `entity_document_master` */

insert  into `entity_document_master`(`id`,`entity_id`,`entity_type`,`form_id`,`document_id`,`number`,`amended_yesno`,`amended_document_id`,`addendum_yesno`,`addendum_document_id`,`approved_yesno`,`active_yesno`,`status_id`,`last_modified_by`,`last_modified_date_time`,`created_by`,`created_date_time`) values (1,1,'staff',1,9,1,0,0,0,0,'0',1,1,'admin','2010-12-17 13:47:17','admin','2010-12-17 13:47:17'),(2,1,'staff',1,10,2,0,0,0,0,'0',1,1,'admin','2010-12-17 14:19:43','admin','2010-12-17 14:19:43'),(3,1,'staff',1,11,3,0,0,0,0,'0',1,1,'admin','2010-12-17 15:59:17','admin','2010-12-17 15:59:17'),(4,1,'staff',1,12,4,0,0,0,0,'0',1,1,'admin','2010-12-17 16:00:27','admin','2010-12-17 15:59:52'),(5,1,'staff',1,13,5,0,0,0,0,'0',1,1,'admin','2010-12-17 16:00:59','admin','2010-12-17 16:00:59'),(6,1,'staff',1,14,6,0,0,0,0,'0',1,1,'admin','2010-12-17 16:01:46','admin','2010-12-17 16:01:30'),(7,1,'staff',1,15,7,0,0,0,0,'0',1,1,'admin','2010-12-17 16:13:51','admin','2010-12-17 16:13:51'),(8,1,'staff',1,16,8,0,0,0,0,'0',1,1,'admin','2010-12-23 09:31:43','admin','2010-12-17 16:16:25'),(9,4,'staff',1,17,1,0,0,0,0,'0',1,1,'admin','2010-12-30 16:10:35','admin','2010-12-17 16:44:31'),(10,8,'staff',1,18,1,0,0,0,0,'0',1,1,'admin','2010-12-28 11:10:20','admin','2010-12-28 11:10:20');

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

insert  into `profile_series`(`series`,`max_number`,`last_modified_by`,`last_modified_date_time`,`edition`) values ('EntityDocument',19,NULL,NULL,0),('ENTITY_DOCUMENT_MASTER',11,NULL,NULL,0);

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

insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'DirectPhoneNumber','Direct Phone Number :',NULL,'2010-12-04 15:18:15',NULL,'0000-00-00 00:00:00','Direct Phone Number :','blank',NULL,NULL,NULL),(10,'PhoneInternalOffice','Phone Internal Office :',NULL,'2010-12-04 15:18:15',NULL,'0000-00-00 00:00:00','Phone Internal Office :','blank',NULL,NULL,NULL),(15,'MobileNumber','Mobile Number :',NULL,'2010-12-04 15:18:15',NULL,'0000-00-00 00:00:00','Mobile Number :','blank',NULL,NULL,NULL),(20,'FacultyRoomNo','Faculty Room No :',NULL,'2010-12-04 15:18:16',NULL,'0000-00-00 00:00:00','Faculty Room No :','blank',NULL,NULL,NULL),(25,'HostelResidence','Hostel/Residence :',NULL,'2010-12-04 15:18:16',NULL,'0000-00-00 00:00:00','Hostel/Residence :','blank',NULL,NULL,NULL),(30,'RoomNoQuartersNo','RoomNo/QuartersNo :',NULL,'2010-12-04 15:18:16',NULL,'0000-00-00 00:00:00','RoomNo/QuartersNo :','blank',NULL,NULL,NULL),(35,'PhoneResidenceHostel','Phone-Residence/Hostel :',NULL,'2010-12-04 15:18:16',NULL,'0000-00-00 00:00:00','Phone-Residence/Hostel :','blank',NULL,NULL,NULL),(45,'SBAccountNo','SB Account No :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','SB Account No :','blank',NULL,NULL,NULL),(50,'Bank','Bank :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Bank :','blank',NULL,NULL,NULL),(55,'PanCardNo','Pan Card No :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Pan Card No :','blank',NULL,NULL,NULL),(60,'PFNo','PF No :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PF No :','blank',NULL,NULL,NULL),(65,'PassportNo','Passport No :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Passport No :','blank',NULL,NULL,NULL),(70,'PassportValidUpto','Passport Valid Upto :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Passport Valid Upto :','blank',NULL,NULL,NULL),(75,'OtherAccomplishments','Other Accomplishments :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Other Accomplishments :','textarea_large',NULL,NULL,NULL),(80,'CourseHead','Course',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Course','tableColHeader',NULL,NULL,NULL),(85,'InstitutionHeader','Institution',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Institution','tableColHeader',NULL,NULL,NULL),(90,'LocationHeader','Location',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Location','tableColHeader',NULL,NULL,NULL),(95,'BoardHeader','Board',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Board','tableColHeader',NULL,NULL,NULL),(100,'YearHeader','Year',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Year','tableColHeader',NULL,NULL,NULL),(105,'MonthHeader','Month',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Month','tableColHeader',NULL,NULL,NULL),(110,'PassingYearHeader','PassingYear',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Passing Year','tableColHeader',NULL,NULL,NULL),(115,'Percentage','%',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','%','tableColHeader',NULL,NULL,NULL),(120,'ClassHeader','Class',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Class','tableColHeader',NULL,NULL,NULL),(125,'MainSubjHeader','Main Sub',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','MainSubj','tableColHeader',NULL,NULL,NULL),(130,'CourseDetails','CourseDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(135,'InstitutionDetails','InstitutionDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(140,'LocationDetails','LocationDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(145,'BoardDetails','BoardDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(150,'YearDetails','YearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','year_combo_box',NULL,NULL,NULL),(155,'MonthDetails','MonthDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','month_combo_box',NULL,NULL,NULL),(160,'PassingYearDetails','PassingYearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(165,'PercentageDetails','PercentageDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(170,'ClassDetails','ClassDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(175,'MainSubjDetails','MainSubjDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(180,'CourseDetails1','CourseDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(185,'InstitutionDetails1','InstitutionDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(190,'LocationDetails1','LocationDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(195,'BoardDetails1','BoardDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(200,'YearDetails1','YearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','year_combo_box',NULL,NULL,NULL),(205,'MonthDetails1','MonthDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','month_combo_box',NULL,NULL,NULL),(210,'PassingYearDetails1','PassingYearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(215,'PercentageDetails1','PercentageDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(220,'ClassDetails1','ClassDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(225,'MainSubjDetails1','MainSubjDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(230,'CourseDetails2','CourseDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(235,'InstitutionDetails2','InstitutionDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(240,'LocationDetails2','LocationDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(245,'BoardDetails2','BoardDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(250,'YearDetails2','YearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','year_combo_box',NULL,NULL,NULL),(255,'MonthDetails2','MonthDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','month_combo_box',NULL,NULL,NULL),(260,'PassingYearDetails2','PassingYearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(265,'PercentageDetails2','PercentageDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(270,'ClassDetails2','ClassDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(275,'MainSubjDetails2','MainSubjDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(280,'CourseDetails3','CourseDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(285,'InstitutionDetails3','InstitutionDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(290,'LocationDetails3','LocationDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(295,'BoardDetails3','BoardDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(300,'YearDetails3','YearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','year_combo_box',NULL,NULL,NULL),(305,'MonthDetails3','MonthDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','month_combo_box',NULL,NULL,NULL),(310,'PassingYearDetails3','PassingYearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(315,'PercentageDetails3','PercentageDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(320,'ClassDetails3','ClassDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(325,'MainSubjDetails3','MainSubjDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(330,'CourseDetails4','CourseDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(335,'InstitutionDetails4','InstitutionDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(340,'LocationDetails4','LocationDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(345,'BoardDetails4','BoardDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(350,'YearDetails4','YearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','year_combo_box',NULL,NULL,NULL),(355,'MonthDetails4','MonthDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','month_combo_box',NULL,NULL,NULL),(360,'PassingYearDetails4','PassingYearDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(365,'PercentageDetails4','PercentageDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(370,'ClassDetails4','ClassDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(375,'MainSubjDetails4','MainSubjDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank_small',NULL,NULL,NULL),(380,'EperienceDetailsHead','EperienceDetailsHead',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Prior Experience','blank',NULL,NULL,NULL),(385,'EperienceDetails','EperienceDetails',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_large',NULL,NULL,NULL),(390,'MemberNameHead','Member Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Member Name','tableColHeader',NULL,NULL,NULL),(395,'RelationHead','Relation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Relation','tableColHeader',NULL,NULL,NULL),(400,'ProfessionHead','Profession',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Profession','tableColHeader',NULL,NULL,NULL),(405,'MemberName','Member Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Member Name','blank',NULL,NULL,NULL),(410,'Relation','Relation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Relation','blank',NULL,NULL,NULL),(415,'Profession','Profession',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Profession','blank',NULL,NULL,NULL),(420,'MemberName1','Member Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Member Name','blank',NULL,NULL,NULL),(425,'Relation1','Relation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Relation','blank',NULL,NULL,NULL),(430,'Profession1','Profession',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Profession','blank',NULL,NULL,NULL),(435,'MemberName2','Member Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Member Name','blank',NULL,NULL,NULL),(440,'Relation2','Relation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Relation','blank',NULL,NULL,NULL),(445,'Profession2','Profession',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Profession','blank',NULL,NULL,NULL),(450,'MemberName3','Member Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Member Name','blank',NULL,NULL,NULL),(455,'Relation3','Relation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Relation','blank',NULL,NULL,NULL),(460,'Profession3','Profession',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Profession','blank',NULL,NULL,NULL),(465,'MemberName4','Member Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Member Name','blank',NULL,NULL,NULL),(470,'Relation4','Relation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Relation','blank',NULL,NULL,NULL),(475,'Profession4','Profession',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Profession','blank',NULL,NULL,NULL),(480,'PRStatusofpaper','PRStatusofpaper',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRStatusofpaper','paper_status_combo_box',NULL,NULL,NULL),(485,'PRPaperType','PRPaperType',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRPaperType','paper_type_combo_box',NULL,NULL,NULL),(490,'RTitleofPaper','RTitleofPaper',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','RTitleofPaper','blank',NULL,NULL,NULL),(495,'PRAuthors','PRAuthors',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRAuthors','blank',NULL,NULL,NULL),(500,'PRJournalName','PRJournalName',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRJournalName','blank',NULL,NULL,NULL),(505,'PRVolume','PRVolume',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRVolume','blank',NULL,NULL,NULL),(510,'PRIssueNo','PRIssueNo',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRIssueNo','blank',NULL,NULL,NULL),(515,'PRYear','PRYear',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRYear','year_combo_box',NULL,NULL,NULL),(520,'PRPages','PRPages',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRPages','blank',NULL,NULL,NULL),(525,'PRImpactFactor','PRImpactFactor',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRImpactFactor','blank',NULL,NULL,NULL),(530,'PRConferenceProceedings','PRConferenceProceedings',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PRConferenceProceedings','tableColHeader',NULL,NULL,NULL),(535,'AANameofConference','AANameofConference',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','AANameofConference','blank',NULL,NULL,NULL),(540,'AAConferenceLocation','AAConferenceLocation',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','AAConferenceLocation','blank',NULL,NULL,NULL),(545,'AAConferenceDates','AAConferenceDates',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','AAConferenceDates','calendar',NULL,NULL,NULL),(550,'AAPublisherandCity','AAPublisherandCity',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','AAPublisherandCity','blank',NULL,NULL,NULL),(555,'AAYear','AAYear',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','AAYear','year_combo_box',NULL,NULL,NULL),(560,'AAPages','AAPages',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','AAPages','blank',NULL,NULL,NULL),(565,'BookDetails','Books Written',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Book Details','tableColHeader',NULL,NULL,NULL),(570,'BookDetails1','Books Written',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_large',NULL,NULL,NULL),(575,'ProjectDetailsHead','Research Projects',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Project Details','tableColHeader',NULL,NULL,NULL),(580,'ProjectDetails1','Project Details',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea',NULL,NULL,NULL),(585,'FundedResearchProjects','Funded Research Projects :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Funded Research Projects :','tableColHeader',NULL,NULL,NULL),(590,'TitleofProject','Title of Project :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Title of Project :','blank',NULL,NULL,NULL),(595,'Abstract','Abstract :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Abstract :','textarea_small',NULL,NULL,NULL),(600,'PrincipalInvestigator','Principal Investigator(PI) :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Principal Investigator(PI) :','blank',NULL,NULL,NULL),(605,'CoPI','Co-PI(s) :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Co-PI(s) :','blank',NULL,NULL,NULL),(610,'URL','URL :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','URL :','blank',NULL,NULL,NULL),(615,'DurationofProject','Duration of Project   :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Duration of Project   :','calendar',NULL,NULL,NULL),(620,'FundingAgency','Funding Agency :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Funding Agency :','blank',NULL,NULL,NULL),(625,'SummaryofProject','Summary of Project :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Summary of Project :','textarea_small',NULL,NULL,NULL),(630,'ProjectGrantAssistance','Project Grant/Assistance :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Project Grant/Assistance :','blank',NULL,NULL,NULL),(635,'Otherdetails','Other Details :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Other Details :','textarea_small',NULL,NULL,NULL),(640,'ConsultancyTitleHead','Consultancy Details',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Consultancy Details','tableColHeader',NULL,NULL,NULL),(645,'ConsultancyDetails1','Consultancy Details',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_large',NULL,NULL,NULL),(650,'Name','Name :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Name :','blank',NULL,NULL,NULL),(655,'Designation','Designation :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Designation :','blank',NULL,NULL,NULL),(660,'AreasofInterest','Areas of Interest  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Areas of Interest  :','textarea_small',NULL,NULL,NULL),(665,'Biography','Biography :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Biography :','textarea',NULL,NULL,NULL),(670,'Email','Email :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Email :','blank',NULL,NULL,NULL),(675,'Phone','Phone :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Phone :','blank',NULL,NULL,NULL),(680,'Fax','Fax :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Fax :','blank',NULL,NULL,NULL),(685,'MMDetailsHead','Prof: memberships',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Membership Details','tableColHeader',NULL,NULL,NULL),(690,'MMDetails1','Membership Details',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_large',NULL,NULL,NULL),(695,'PhDTitleHead','PhDTitle',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PhDTitle','tableColHeader',NULL,NULL,NULL),(700,'AreaHead','Area',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Area','tableColHeader',NULL,NULL,NULL),(705,'UniversityHead','University',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','University','tableColHeader',NULL,NULL,NULL),(710,'ProjGuided1','ProjGuided(PG)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PG)','tableColHeader',NULL,NULL,NULL),(715,'ProjGuided2','ProjGuided(PhD)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PhD)','tableColHeader',NULL,NULL,NULL),(720,'ProjGuided3','ProjGuided(PostDoctoral)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PostDoctoral)','tableColHeader',NULL,NULL,NULL),(725,'PhDTitle1','PhDTitle',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PhDTitle','blank',NULL,NULL,NULL),(730,'Area1','Area',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Area','blank',NULL,NULL,NULL),(735,'University1','University',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','University','blank',NULL,NULL,NULL),(740,'ProjGuided11','ProjGuided(PG)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PG)','blank',NULL,NULL,NULL),(745,'ProjGuided12','ProjGuided(PhD)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PhD)','blank',NULL,NULL,NULL),(750,'ProjGuided13','ProjGuided(PostDoctoral)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PostDoctoral)','blank',NULL,NULL,NULL),(755,'PhDTitle2','PhDTitle',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PhDTitle','blank',NULL,NULL,NULL),(760,'Area2','Area',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Area','blank',NULL,NULL,NULL),(765,'University2','University',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','University','blank',NULL,NULL,NULL),(770,'ProjGuided21','ProjGuided(PG)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PG)','blank',NULL,NULL,NULL),(775,'ProjGuided22','ProjGuided(PhD)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PhD)','blank',NULL,NULL,NULL),(780,'ProjGuided23','ProjGuided(PostDoctoral)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PostDoctoral)','blank',NULL,NULL,NULL),(785,'PhDTitle3','PhDTitle',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','PhDTitle','blank',NULL,NULL,NULL),(790,'Area3','Area',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Area','blank',NULL,NULL,NULL),(795,'University3','University',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','University','blank',NULL,NULL,NULL),(800,'ProjGuided31','ProjGuided(PG)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PG)','blank',NULL,NULL,NULL),(805,'ProjGuided32','ProjGuided(PhD)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PhD)','blank',NULL,NULL,NULL),(810,'ProjGuided33','ProjGuided(PostDoctoral)',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','ProjGuided(PostDoctoral)','blank',NULL,NULL,NULL),(815,'Papertype','Paper Type :',NULL,'2010-12-08 12:10:49',NULL,'0000-00-00 00:00:00','Paper Type :','Jouranltype_combo_box',NULL,NULL,NULL),(820,'Journal','Journal  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Journal  :','paper_type_combo_box',NULL,NULL,NULL),(825,'TitleofPaper','Title of Paper :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Title of Paper :','blank',NULL,NULL,NULL),(830,'Authors','Authors :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Authors :','blank',NULL,NULL,NULL),(835,'JournalName','Journal Name  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Journal Name  :','blank',NULL,NULL,NULL),(840,'JAbstract','Abstract :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Abstract :','blank',NULL,NULL,NULL),(845,'JVolume','Volume :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Volume :','blank',NULL,NULL,NULL),(850,'JIssueNo','Issue No :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Issue No :','blank',NULL,NULL,NULL),(855,'JMonth','Month :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Month :','blank',NULL,NULL,NULL),(860,'JYear','Year :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Year :','year_combo_box',NULL,NULL,NULL),(865,'JPages','Pages :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Pages :','blank',NULL,NULL,NULL),(870,'Projectassociatedwiththepaper','Project associated with the paper  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Project associated with the paper  :','blank',NULL,NULL,NULL),(875,'Language','Language   :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Language   :','blank',NULL,NULL,NULL),(880,'PPURL','URL :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','URL :','blank',NULL,NULL,NULL),(885,'PPOtherdetails','Other Details :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Other Details :','textarea_small',NULL,NULL,NULL),(890,'ConferenceProceedings','Conference Proceedings  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Conference Proceedings  :','paper_type_combo_box',NULL,NULL,NULL),(895,'PPTitleofPaper','Title of Paper :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Title of Paper :','blank',NULL,NULL,NULL),(900,'PPAbstract','Abstract :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Abstract :','blank',NULL,NULL,NULL),(905,'PPYear','Year :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Year :','year_combo_box',NULL,NULL,NULL),(910,'PPges','Pages :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Pages :','blank',NULL,NULL,NULL),(915,'PPNameofConference','Name of Conference  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Name of Conference  :','blank',NULL,NULL,NULL),(920,'PPConferenceDates','Conference Dates :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Conference Dates :','calendar',NULL,NULL,NULL),(925,'ConferenceVenue','Conference Venue :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Conference Venue :','blank',NULL,NULL,NULL),(930,'NameoftheOrganizer','Name of the Organizer :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Name of the Organizer :','blank',NULL,NULL,NULL),(935,'PLanguage','Language :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Language :','blank',NULL,NULL,NULL),(940,'PURL','URL :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','URL :','blank',NULL,NULL,NULL),(945,'pOtherdetails','Other Details :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Other Details :','textarea_small',NULL,NULL,NULL),(950,'SeminarType','Seminar Type :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Seminar Type :','paper_type_combo_box',NULL,NULL,NULL),(955,'NameofConferenceSeminarattendedWorkshopsTrainingattended','Name of Conference/Seminar/Workshops/Training attended  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Name of Conference/Seminar/Workshops/Training attended  :','blank',NULL,NULL,NULL),(960,'NIDurationofConference','Duration of Conference  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Duration of Conference  :','calendar',NULL,NULL,NULL),(965,'NILocation','Location  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Location  :','tableColHeader',NULL,NULL,NULL),(970,'NICity','City :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','City :','blank',NULL,NULL,NULL),(975,'NIState','State :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','State :','blank',NULL,NULL,NULL),(980,'NCountry','Country :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Country :','blank',NULL,NULL,NULL),(985,'SourceofFunding','Source of Funding  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Source of Funding  :','blank',NULL,NULL,NULL),(990,'NNPurposeofattendingtheConferenceSeminar','Purpose of attending the Conference/Seminar  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Purpose of attending the Conference/Seminar  :','seminar_purpose_combo_box',NULL,NULL,NULL),(995,'NNDescription','Description :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Description :','textarea_small',NULL,NULL,NULL),(1000,'SPresentedPaper','Presented Paper :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Presented Paper :','tableColHeader',NULL,NULL,NULL),(1005,'STitleofPaper','Title of Paper :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Title of Paper :','blank',NULL,NULL,NULL),(1010,'SAuthors','Author(s) :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Author(s) :','blank',NULL,NULL,NULL),(1015,'SAbstract','Abstract :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Abstract :','blank',NULL,NULL,NULL),(1020,'SProjectassociatedwiththepaper','Project associated with the paper  :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Project associated with the paper  :','blank',NULL,NULL,NULL),(1025,'SLanguage','Language :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Language :','blank',NULL,NULL,NULL),(1030,'SURL','URL :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','URL :','blank',NULL,NULL,NULL),(1035,'SOtherdetails','Other Details :',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Other Details :','textarea_small',NULL,NULL,NULL),(1040,'AwardNameHead','Award Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Award Name','tableColHeader',NULL,NULL,NULL),(1045,'YearHead','Year',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Year','tableColHeader',NULL,NULL,NULL),(1050,'DescriptionHead','Description',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Description','tableColHeader',NULL,NULL,NULL),(1055,'AwardName','Award Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank',NULL,NULL,NULL),(1060,'AwardYear','Year',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank',NULL,NULL,NULL),(1065,'AwardDescription','Description',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_small',NULL,NULL,NULL),(1070,'AwardName2','Award Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank',NULL,NULL,NULL),(1075,'AwardYear2','Year',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank',NULL,NULL,NULL),(1080,'AwardDescription2','Description',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_small',NULL,NULL,NULL),(1085,'AwardName3','Award Name',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank',NULL,NULL,NULL),(1090,'AwardYear3','Year',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','blank',NULL,NULL,NULL),(1095,'AwardDescription3','Description',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_small',NULL,NULL,NULL),(1100,'ProgramDetails','Training Prog',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00','Program Details','tableColHeader',NULL,NULL,NULL),(1105,'ProgramDetails1','Program Details',NULL,'2010-12-07 15:22:28',NULL,'0000-00-00 00:00:00',' ','textarea_large',NULL,NULL,NULL),(40,'UploadResume','Upload Resume :',NULL,'2010-12-21 12:17:17',NULL,'0000-00-00 00:00:00','Upload Resume :','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('tableColHeader','None','oio','2010-12-04 16:05:41','show_text',' ',' ',NULL),('blank','None','oio','2010-12-06 11:43:06','except','15 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('paper_status_combo_box','None','oio','2010-12-04 16:29:31','pickone','Select,Communicated,Accepted,Published','Select,Communicated,Accepted,Published',NULL),('paper_type_combo_box','None','oio','2010-12-04 16:24:33','pickone','Select,International,National','Select,International,National',NULL),('jouranltype_combo_box','None','oio','2010-12-04 16:28:45','pickone','Select,Journal,Conference Proceedings','Select,Journal,Conference Proceedings',NULL),('seminar_purpose_combo_box','None','oio','2010-12-04 16:42:35','pickone','Select,Presented Paper,Participation','Select,Presented Paper,Participation',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('blank_small','None','oio','2010-12-06 10:02:44','except','7','',NULL),('file_upload','None','oio','2010-12-23 10:12:00','file_upload',NULL,'',NULL);

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
  `DirectPhoneNumber` varchar(255) default NULL,
  `PhoneInternalOffice` varchar(255) default NULL,
  `MobileNumber` varchar(255) default NULL,
  `FacultyRoomNo` varchar(255) default NULL,
  `HostelResidence` varchar(255) default NULL,
  `RoomNoQuartersNo` varchar(255) default NULL,
  `PhoneResidenceHostel` varchar(255) default NULL,
  `SBAccountNo` varchar(255) default NULL,
  `Bank` varchar(255) default NULL,
  `PanCardNo` varchar(255) default NULL,
  `PFNo` varchar(255) default NULL,
  `PassportNo` varchar(255) default NULL,
  `PassportValidUpto` varchar(255) default NULL,
  `OtherAccomplishments` text,
  `CourseHead` varchar(200) default NULL,
  `InstitutionHeader` varchar(200) default NULL,
  `LocationHeader` varchar(200) default NULL,
  `BoardHeader` varchar(200) default NULL,
  `YearHeader` varchar(200) default NULL,
  `MonthHeader` varchar(200) default NULL,
  `PassingYearHeader` varchar(200) default NULL,
  `Percentage` varchar(200) default NULL,
  `ClassHeader` varchar(200) default NULL,
  `MainSubjHeader` varchar(200) default NULL,
  `CourseDetails` varchar(255) default NULL,
  `InstitutionDetails` varchar(255) default NULL,
  `LocationDetails` varchar(255) default NULL,
  `BoardDetails` varchar(255) default NULL,
  `YearDetails` varchar(255) default NULL,
  `MonthDetails` varchar(255) default NULL,
  `PassingYearDetails` varchar(255) default NULL,
  `PercentageDetails` varchar(255) default NULL,
  `ClassDetails` varchar(255) default NULL,
  `MainSubjDetails` varchar(255) default NULL,
  `CourseDetails1` varchar(255) default NULL,
  `InstitutionDetails1` varchar(255) default NULL,
  `LocationDetails1` varchar(255) default NULL,
  `BoardDetails1` varchar(255) default NULL,
  `YearDetails1` varchar(255) default NULL,
  `MonthDetails1` varchar(255) default NULL,
  `PassingYearDetails1` varchar(255) default NULL,
  `PercentageDetails1` varchar(255) default NULL,
  `ClassDetails1` varchar(255) default NULL,
  `MainSubjDetails1` varchar(255) default NULL,
  `CourseDetails2` varchar(255) default NULL,
  `InstitutionDetails2` varchar(255) default NULL,
  `LocationDetails2` varchar(255) default NULL,
  `BoardDetails2` varchar(255) default NULL,
  `YearDetails2` varchar(255) default NULL,
  `MonthDetails2` varchar(255) default NULL,
  `PassingYearDetails2` varchar(255) default NULL,
  `PercentageDetails2` varchar(255) default NULL,
  `ClassDetails2` varchar(255) default NULL,
  `MainSubjDetails2` varchar(255) default NULL,
  `CourseDetails3` varchar(355) default NULL,
  `InstitutionDetails3` varchar(355) default NULL,
  `LocationDetails3` varchar(355) default NULL,
  `BoardDetails3` varchar(355) default NULL,
  `YearDetails3` varchar(355) default NULL,
  `MonthDetails3` varchar(355) default NULL,
  `PassingYearDetails3` varchar(355) default NULL,
  `PercentageDetails3` varchar(355) default NULL,
  `ClassDetails3` varchar(355) default NULL,
  `MainSubjDetails3` varchar(355) default NULL,
  `CourseDetails4` varchar(455) default NULL,
  `InstitutionDetails4` varchar(455) default NULL,
  `LocationDetails4` varchar(455) default NULL,
  `BoardDetails4` varchar(455) default NULL,
  `YearDetails4` varchar(455) default NULL,
  `MonthDetails4` varchar(455) default NULL,
  `PassingYearDetails4` varchar(455) default NULL,
  `PercentageDetails4` varchar(455) default NULL,
  `ClassDetails4` varchar(455) default NULL,
  `MainSubjDetails4` varchar(455) default NULL,
  `EperienceDetailsHead` varchar(200) default NULL,
  `EperienceDetails` text,
  `MemberNameHead` varchar(200) default NULL,
  `RelationHead` varchar(200) default NULL,
  `ProfessionHead` varchar(200) default NULL,
  `MemberName` varchar(255) default NULL,
  `Relation` varchar(255) default NULL,
  `Profession` varchar(255) default NULL,
  `MemberName1` varchar(255) default NULL,
  `Relation1` varchar(255) default NULL,
  `Profession1` varchar(255) default NULL,
  `MemberName2` varchar(255) default NULL,
  `Relation2` varchar(255) default NULL,
  `Profession2` varchar(255) default NULL,
  `MemberName3` varchar(255) default NULL,
  `Relation3` varchar(255) default NULL,
  `Profession3` varchar(255) default NULL,
  `MemberName4` varchar(255) default NULL,
  `Relation4` varchar(255) default NULL,
  `Profession4` varchar(255) default NULL,
  `PRStatusofpaper` varchar(255) default NULL,
  `PRPaperType` varchar(255) default NULL,
  `RTitleofPaper` varchar(255) default NULL,
  `PRAuthors` varchar(255) default NULL,
  `PRJournalName` varchar(255) default NULL,
  `PRVolume` varchar(255) default NULL,
  `PRIssueNo` varchar(255) default NULL,
  `PRYear` varchar(255) default NULL,
  `PRPages` varchar(255) default NULL,
  `PRImpactFactor` varchar(255) default NULL,
  `PRConferenceProceedings` varchar(255) default NULL,
  `AANameofConference` varchar(255) default NULL,
  `AAConferenceLocation` varchar(255) default NULL,
  `AAConferenceDates` varchar(255) default NULL,
  `AAPublisherandCity` varchar(255) default NULL,
  `AAYear` varchar(255) default NULL,
  `AAPages` varchar(255) default NULL,
  `BookDetails` varchar(200) default NULL,
  `BookDetails1` text,
  `ProjectDetailsHead` varchar(200) default NULL,
  `ProjectDetails1` text,
  `FundedResearchProjects` varchar(255) default NULL,
  `TitleofProject` varchar(255) default NULL,
  `Abstract` text,
  `PrincipalInvestigator` varchar(255) default NULL,
  `CoPI` varchar(255) default NULL,
  `URL` varchar(255) default NULL,
  `DurationofProject` varchar(255) default NULL,
  `FundingAgency` varchar(255) default NULL,
  `SummaryofProject` text,
  `ProjectGrantAssistance` varchar(255) default NULL,
  `Otherdetails` text,
  `ConsultancyTitleHead` varchar(200) default NULL,
  `ConsultancyDetails1` text,
  `Name` varchar(255) default NULL,
  `Designation` varchar(255) default NULL,
  `AreasofInterest` text,
  `Biography` text,
  `Email` varchar(255) default NULL,
  `Phone` varchar(255) default NULL,
  `Fax` varchar(255) default NULL,
  `MMDetailsHead` varchar(200) default NULL,
  `MMDetails1` text,
  `PhDTitleHead` varchar(200) default NULL,
  `AreaHead` varchar(200) default NULL,
  `UniversityHead` varchar(200) default NULL,
  `ProjGuided1` varchar(200) default NULL,
  `ProjGuided2` varchar(200) default NULL,
  `ProjGuided3` varchar(200) default NULL,
  `PhDTitle1` varchar(255) default NULL,
  `Area1` varchar(255) default NULL,
  `University1` varchar(255) default NULL,
  `ProjGuided11` varchar(255) default NULL,
  `ProjGuided12` varchar(255) default NULL,
  `ProjGuided13` varchar(255) default NULL,
  `PhDTitle2` varchar(255) default NULL,
  `Area2` varchar(255) default NULL,
  `University2` varchar(255) default NULL,
  `ProjGuided21` varchar(255) default NULL,
  `ProjGuided22` varchar(255) default NULL,
  `ProjGuided23` varchar(255) default NULL,
  `PhDTitle3` varchar(255) default NULL,
  `Area3` varchar(255) default NULL,
  `University3` varchar(255) default NULL,
  `ProjGuided31` varchar(255) default NULL,
  `ProjGuided32` varchar(255) default NULL,
  `ProjGuided33` varchar(255) default NULL,
  `AwardNameHead` varchar(200) default NULL,
  `YearHead` varchar(200) default NULL,
  `DescriptionHead` varchar(200) default NULL,
  `AwardName` varchar(255) default NULL,
  `AwardYear` varchar(255) default NULL,
  `AwardDescription` text,
  `AwardName2` varchar(255) default NULL,
  `AwardYear2` varchar(255) default NULL,
  `AwardDescription2` text,
  `AwardName3` varchar(255) default NULL,
  `AwardYear3` varchar(255) default NULL,
  `AwardDescription3` text,
  `Papertype` varchar(255) default NULL,
  `Journal` varchar(255) default NULL,
  `TitleofPaper` varchar(255) default NULL,
  `Authors` varchar(255) default NULL,
  `JournalName` varchar(255) default NULL,
  `JAbstract` varchar(255) default NULL,
  `JVolume` varchar(255) default NULL,
  `JIssueNo` varchar(255) default NULL,
  `JMonth` varchar(255) default NULL,
  `JYear` varchar(255) default NULL,
  `JPages` varchar(255) default NULL,
  `Projectassociatedwiththepaper` varchar(255) default NULL,
  `Language` varchar(255) default NULL,
  `PPURL` varchar(255) default NULL,
  `PPOtherdetails` text,
  `ConferenceProceedings` varchar(255) default NULL,
  `PPTitleofPaper` varchar(255) default NULL,
  `PPAbstract` varchar(255) default NULL,
  `PPYear` varchar(255) default NULL,
  `PPges` varchar(255) default NULL,
  `PPNameofConference` varchar(255) default NULL,
  `PPConferenceDates` varchar(255) default NULL,
  `ConferenceVenue` varchar(255) default NULL,
  `NameoftheOrganizer` varchar(255) default NULL,
  `PLanguage` varchar(255) default NULL,
  `PURL` varchar(255) default NULL,
  `pOtherdetails` text,
  `SeminarType` varchar(255) default NULL,
  `NameofConferenceSeminarattendedWorkshopsTrainingattended` varchar(255) default NULL,
  `NIDurationofConference` varchar(255) default NULL,
  `NILocation` varchar(255) default NULL,
  `NICity` varchar(255) default NULL,
  `NIState` varchar(255) default NULL,
  `NCountry` varchar(255) default NULL,
  `SourceofFunding` varchar(255) default NULL,
  `NNPurposeofattendingtheConferenceSeminar` varchar(255) default NULL,
  `NNDescription` text,
  `SPresentedPaper` varchar(255) default NULL,
  `STitleofPaper` varchar(255) default NULL,
  `SAuthors` varchar(255) default NULL,
  `SAbstract` varchar(255) default NULL,
  `SProjectassociatedwiththepaper` varchar(255) default NULL,
  `SLanguage` varchar(255) default NULL,
  `SURL` varchar(255) default NULL,
  `SOtherdetails` text,
  `ProgramDetails` varchar(200) default NULL,
  `ProgramDetails1` text,
  `UploadResume` varchar(255) default NULL,
  KEY `idx_Rad_val` (`idf`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_values` */

insert  into `staff_profile_report_v0_values`(`idf`,`number`,`form`,`creator`,`time`,`last_time`,`last_filler`,`data_quality`,`change_group`,`execute_group`,`DirectPhoneNumber`,`PhoneInternalOffice`,`MobileNumber`,`FacultyRoomNo`,`HostelResidence`,`RoomNoQuartersNo`,`PhoneResidenceHostel`,`SBAccountNo`,`Bank`,`PanCardNo`,`PFNo`,`PassportNo`,`PassportValidUpto`,`OtherAccomplishments`,`CourseHead`,`InstitutionHeader`,`LocationHeader`,`BoardHeader`,`YearHeader`,`MonthHeader`,`PassingYearHeader`,`Percentage`,`ClassHeader`,`MainSubjHeader`,`CourseDetails`,`InstitutionDetails`,`LocationDetails`,`BoardDetails`,`YearDetails`,`MonthDetails`,`PassingYearDetails`,`PercentageDetails`,`ClassDetails`,`MainSubjDetails`,`CourseDetails1`,`InstitutionDetails1`,`LocationDetails1`,`BoardDetails1`,`YearDetails1`,`MonthDetails1`,`PassingYearDetails1`,`PercentageDetails1`,`ClassDetails1`,`MainSubjDetails1`,`CourseDetails2`,`InstitutionDetails2`,`LocationDetails2`,`BoardDetails2`,`YearDetails2`,`MonthDetails2`,`PassingYearDetails2`,`PercentageDetails2`,`ClassDetails2`,`MainSubjDetails2`,`CourseDetails3`,`InstitutionDetails3`,`LocationDetails3`,`BoardDetails3`,`YearDetails3`,`MonthDetails3`,`PassingYearDetails3`,`PercentageDetails3`,`ClassDetails3`,`MainSubjDetails3`,`CourseDetails4`,`InstitutionDetails4`,`LocationDetails4`,`BoardDetails4`,`YearDetails4`,`MonthDetails4`,`PassingYearDetails4`,`PercentageDetails4`,`ClassDetails4`,`MainSubjDetails4`,`EperienceDetailsHead`,`EperienceDetails`,`MemberNameHead`,`RelationHead`,`ProfessionHead`,`MemberName`,`Relation`,`Profession`,`MemberName1`,`Relation1`,`Profession1`,`MemberName2`,`Relation2`,`Profession2`,`MemberName3`,`Relation3`,`Profession3`,`MemberName4`,`Relation4`,`Profession4`,`PRStatusofpaper`,`PRPaperType`,`RTitleofPaper`,`PRAuthors`,`PRJournalName`,`PRVolume`,`PRIssueNo`,`PRYear`,`PRPages`,`PRImpactFactor`,`PRConferenceProceedings`,`AANameofConference`,`AAConferenceLocation`,`AAConferenceDates`,`AAPublisherandCity`,`AAYear`,`AAPages`,`BookDetails`,`BookDetails1`,`ProjectDetailsHead`,`ProjectDetails1`,`FundedResearchProjects`,`TitleofProject`,`Abstract`,`PrincipalInvestigator`,`CoPI`,`URL`,`DurationofProject`,`FundingAgency`,`SummaryofProject`,`ProjectGrantAssistance`,`Otherdetails`,`ConsultancyTitleHead`,`ConsultancyDetails1`,`Name`,`Designation`,`AreasofInterest`,`Biography`,`Email`,`Phone`,`Fax`,`MMDetailsHead`,`MMDetails1`,`PhDTitleHead`,`AreaHead`,`UniversityHead`,`ProjGuided1`,`ProjGuided2`,`ProjGuided3`,`PhDTitle1`,`Area1`,`University1`,`ProjGuided11`,`ProjGuided12`,`ProjGuided13`,`PhDTitle2`,`Area2`,`University2`,`ProjGuided21`,`ProjGuided22`,`ProjGuided23`,`PhDTitle3`,`Area3`,`University3`,`ProjGuided31`,`ProjGuided32`,`ProjGuided33`,`AwardNameHead`,`YearHead`,`DescriptionHead`,`AwardName`,`AwardYear`,`AwardDescription`,`AwardName2`,`AwardYear2`,`AwardDescription2`,`AwardName3`,`AwardYear3`,`AwardDescription3`,`Papertype`,`Journal`,`TitleofPaper`,`Authors`,`JournalName`,`JAbstract`,`JVolume`,`JIssueNo`,`JMonth`,`JYear`,`JPages`,`Projectassociatedwiththepaper`,`Language`,`PPURL`,`PPOtherdetails`,`ConferenceProceedings`,`PPTitleofPaper`,`PPAbstract`,`PPYear`,`PPges`,`PPNameofConference`,`PPConferenceDates`,`ConferenceVenue`,`NameoftheOrganizer`,`PLanguage`,`PURL`,`pOtherdetails`,`SeminarType`,`NameofConferenceSeminarattendedWorkshopsTrainingattended`,`NIDurationofConference`,`NILocation`,`NICity`,`NIState`,`NCountry`,`SourceofFunding`,`NNPurposeofattendingtheConferenceSeminar`,`NNDescription`,`SPresentedPaper`,`STitleofPaper`,`SAuthors`,`SAbstract`,`SProjectassociatedwiththepaper`,`SLanguage`,`SURL`,`SOtherdetails`,`ProgramDetails`,`ProgramDetails1`,`UploadResume`) values (1,1,NULL,NULL,'2010-12-17 13:47:17','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'wew','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','',''),(1,2,NULL,NULL,'2010-12-17 14:19:43','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'fwe','edtgs','','','','sgdgd','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','',''),(1,3,NULL,NULL,'2010-12-17 15:59:17','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','readme.txt'),(1,4,NULL,NULL,'2010-12-17 16:00:27','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','readme.txt'),(1,5,NULL,NULL,'2010-12-17 16:00:59','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','license.txt'),(1,6,NULL,NULL,'2010-12-17 16:01:46','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','readme.txt'),(1,7,NULL,NULL,'2010-12-17 16:13:51','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'cvn','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','Patient Portal Technical Details V2.doc'),(1,8,NULL,NULL,'2010-12-23 09:33:07','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'cvn','dfgh','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','Patient Portal Technical Details V2.doc'),(4,1,NULL,NULL,'2010-12-30 16:10:35','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'04791256987','3967','9845764532','b11','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','17-12-2010','','Select','','','','','','','','','','','','17-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','17-12-2010','','','','','','Select','','17-12-2010','','','','','','Select','','','','','','','','','','','','build.xml'),(8,1,NULL,NULL,'2010-12-28 11:10:20','0000-00-00 00:00:00',NULL,NULL,NULL,NULL,'6666','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','Select','Select','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','Select','','','','','','28-12-2010','','Select','','','','','','','','','','','','28-12-2010','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','Select','Select','','','','','','','','Select','','','','','','Select','','','Select','','','28-12-2010','','','','','','Select','','28-12-2010','','','','','','Select','','','','','','','','','','','','\r\n	40\r\n	Upload Resume :\r\n	Upload Resume :\r\n	file_upload\r\n	\r\n\r\n\r\n\r\n\r\n');

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
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`email`,`enabled`,`active_yesno`,`id`,`user_full_name`) values ('admin','ec4455636479481fde628b5673d627a44bbe992081c916387ee5a4e8fbdb4e9e','admin@amritatech.com',1,0,1,'admin'),('rajeshkumarkp@amrita.edu','3a3e57647bd2a0fbea4517f38e4d58a7a84b2cb7326496592d4ad923880e1ab1','rajeshkumarkp@amrita.edu',1,0,4,'Rajesh Kumar K P');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
