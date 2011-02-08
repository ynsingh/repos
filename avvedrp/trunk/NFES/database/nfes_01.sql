/*
SQLyog Community v8.55 
MySQL - 5.0.19-nt : Database - nfes_01
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

insert  into `authorities`(`username`,`authority`) values ('admin','ROLE_TELLER'),('admin','STAFF_REGISTRATION'),('rajesh.sinha@yahoo.com','ROLE_TELLER'),('rajesh.sinha@yahoo.com','STAFF_REGISTRATION');

/*Table structure for table `country_master` */

DROP TABLE IF EXISTS `country_master`;

CREATE TABLE `country_master` (
  `country_Id` int(11) NOT NULL auto_increment,
  `country_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `country_Id` (`country_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `country_master` */

insert  into `country_master`(`country_Id`,`country_name`,`active_yes_no`) values (1,'India','1'),(2,'Pakistan','1');

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

/*Table structure for table `language_master` */

DROP TABLE IF EXISTS `language_master`;

CREATE TABLE `language_master` (
  `language_Id` int(11) NOT NULL auto_increment,
  `language_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `language_Id` (`language_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `language_master` */

insert  into `language_master`(`language_Id`,`language_name`,`active_yes_no`) values (1,'English','1'),(2,'Hindi','1'),(3,'Malayalam','1'),(4,'Tamil','1');

/*Table structure for table `media_types` */

DROP TABLE IF EXISTS `media_types`;

CREATE TABLE `media_types` (
  `media_types_Id` int(11) NOT NULL auto_increment,
  `media_types_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `media_types_Id` (`media_types_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `media_types` */

insert  into `media_types`(`media_types_Id`,`media_types_name`,`active_yes_no`) values (1,'Magazines','1'),(2,'Newspapers','1'),(3,'University newsletters','1'),(4,'Newsletters of ISTE','1'),(5,'Websites ','1');

/*Table structure for table `membership_types` */

DROP TABLE IF EXISTS `membership_types`;

CREATE TABLE `membership_types` (
  `membership_type_Id` int(11) NOT NULL auto_increment,
  `membership_type_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `membership_type_Id` (`membership_type_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `membership_types` */

insert  into `membership_types`(`membership_type_Id`,`membership_type_name`,`active_yes_no`) values (1,'Regular','1'),(2,'Associate','1'),(3,'Fellow','1'),(4,'Life','1');

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

insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (1,'staff_profile_report','v0','Staff Profile','Staff Profile',1,0,1,1,'admin',NULL,NULL,0,NULL),(2,'staff_profile_qualification','v0','Staff Profile Qualification Details','Staff Profile Qualification Details',2,0,1,1,'admin',NULL,NULL,0,NULL),(3,'staff_profile_awards','v0','Staff Profile Awards Details','Staff Profile Awards Details',3,0,1,1,'admin',NULL,NULL,0,NULL),(4,'staff_profile_community_service','v0','Staff Profile Community Service Details','Staff Profile Community Service Details',4,0,1,1,'admin',NULL,NULL,0,NULL),(5,'staff_profile_review_committees','v0','Staff Profile Review Committees Details','Staff Profile Review Committees Details',5,0,1,1,'admin',NULL,NULL,0,NULL),(6,'staff_profile_governance','v0','Staff Profile Governance Details','Staff Profile Governance Details',6,0,1,1,'admin',NULL,NULL,0,NULL),(7,'staff_profile_consultancy_offered','v0','Staff Profile Consultancy Details','Staff Profile Consultancy Details',7,0,1,1,'admin',NULL,NULL,0,NULL),(8,'staff_profile_professional_body','v0','Staff Profile Professional Body Details','Staff Profile Professional Body Details',8,0,1,1,'admin',NULL,NULL,0,NULL),(9,'staff_profile_faculty_exchange','v0','Staff Profile Faculty Exchange Details','Staff Profile Faculty Exchange Details',9,0,1,1,'admin',NULL,NULL,0,NULL),(10,'staff_profile_thesis','v0','Staff Profile Thesis Details','Staff Profile Thesis Details',10,0,1,1,'admin',NULL,NULL,0,NULL),(11,'staff_profile_media_publication','v0','Staff Profile Media Publication Details','Staff Profile Media Publication Details',11,0,1,1,'admin',NULL,NULL,0,NULL),(12,'staff_profile_patents','v0','Staff Profile Patents Details','Staff Profile Patents Details',12,0,1,1,'admin',NULL,NULL,0,NULL),(13,'staff_profile_projects','v0','Staff Profile Projects Details','Staff Profile Projects Details',13,0,1,1,'admin',NULL,NULL,0,NULL),(14,'staff_profile_training','v0','Staff Profile Training Details','Staff Profile Training Details',14,0,1,1,'admin',NULL,NULL,0,NULL),(15,'staff_profile_talks_lectures','v0','Staff Profile Talks Lectures Details','Staff Profile Talks Lectures Details',15,0,1,1,'admin',NULL,NULL,0,NULL),(16,'staff_profile_books_chapter','v0','Staff Profile Books Chapter Details','Staff Profile Books Chapter Details',16,0,1,1,'admin',NULL,NULL,0,NULL),(17,'staff_profile_conference_papers','v0','Staff Profile Conference Papers Details','Staff Profile Conference Papers Details',17,0,1,1,'admin',NULL,NULL,0,NULL),(18,'staff_profile_journal_papers','v0','Staff Profile Journal Papers Details','Staff Profile Journal Papers Details',18,0,1,1,'admin',NULL,NULL,0,NULL);

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

insert  into `profile_series`(`series`,`max_number`,`last_modified_by`,`last_modified_date_time`,`edition`) values ('EntityDocument',1,NULL,NULL,0),('ENTITY_DOCUMENT_MASTER',1,NULL,NULL,0);

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

insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (1,'SPF','Staff Profile',1,'admin',NULL),(2,'SP_QUALIFICATION','Staff Profile Qualification',1,'admin',NULL),(3,'SP_AWARDS','Staff Profile Awards',1,'admin',NULL),(4,'SP_COMMUNITY_SERVICE','Staff Profile Community Service',1,'admin',NULL),(5,'SP_REVIEW_COMMITTEES','Staff Profile Review Committees',1,'admin',NULL),(6,'SP_GOVERNANCE','Staff Profile Governance',1,'admin',NULL),(7,'SP_CONSULTANCY_OFFERED','Staff Profile Consultancy Offered',1,'admin',NULL),(8,'SP_PROFESSIONAL_BODY','Staff Profile Professional Body',1,'admin',NULL),(9,'SP_FACULTY_EXCHANGE','Staff Profile Faculty Exchange',1,'admin',NULL),(10,'SP_THESIS','Staff Profile Thesis',1,'admin',NULL),(11,'SP_MEDIA_PUBLICATION','Staff Profile Media Publication',1,'admin',NULL),(12,'SP_PATENTS','Staff Profile Patents',1,'admin',NULL),(13,'SP_PROJECTS','Staff Profile Projects',1,'admin',NULL),(14,'SP_TRAINING','Staff Profile Training',1,'admin',NULL),(15,'SP_TALKS_LECTURES','Staff Profile Talks Lectures',1,'admin',NULL),(16,'SP_BOOKS_CHAPTER','Staff Profile Books Chapter',1,'admin',NULL),(17,'SP_CONFERENCE_PAPERS','Staff Profile Conference Papers',1,'admin',NULL),(18,'SP_JOURNAL_PAPERS','Staff Profile Journal Papers',1,'admin',NULL);

/*Table structure for table `specialization_master` */

DROP TABLE IF EXISTS `specialization_master`;

CREATE TABLE `specialization_master` (
  `specialization_Id` int(11) NOT NULL auto_increment,
  `specialization_name` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  UNIQUE KEY `specialization_Id` (`specialization_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `specialization_master` */

insert  into `specialization_master`(`specialization_Id`,`specialization_name`,`active_yes_no`) values (1,'Operating System','1'),(2,'System Software','1');

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

insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'award_name','Name_of_Award',NULL,'2011-01-22 09:44:12',NULL,'0000-00-00 00:00:00','Name of Award<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'agency_name','Name_of_Agency',NULL,'2011-01-22 09:44:14',NULL,'0000-00-00 00:00:00','Name of Agency','blank',NULL,NULL,NULL),(15,'receiving_month_year','Month_Year_of_Receiving',NULL,'2011-01-28 11:58:43',NULL,'0000-00-00 00:00:00','Month and Year of Receiving<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL),(25,'remarks','Remarks',NULL,'2011-01-29 16:17:06',NULL,'0000-00-00 00:00:00','Remarks','textarea_small',NULL,NULL,NULL),(30,'upload_image','Upload_Image',NULL,'2011-01-15 11:48:59',NULL,'0000-00-00 00:00:00','Upload Image','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:12:33','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

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

insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'book_chapter','\"Book\"/\"Chapter\"',NULL,'2011-01-27 17:51:50',NULL,'0000-00-00 00:00:00','\"Book\"/\"Chapter\"<span style=\"color:red\">*</span>','radio_book',NULL,NULL,NULL),(10,'role','Role',NULL,'2011-01-28 12:02:03',NULL,'0000-00-00 00:00:00','Role','combo_box_role',NULL,NULL,NULL),(15,'title','Title',NULL,'2011-01-27 17:52:02',NULL,'0000-00-00 00:00:00','Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'author1','Author1',NULL,'2011-01-27 17:52:08',NULL,'0000-00-00 00:00:00','Author1<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'author2','Author2',NULL,'2011-01-22 09:46:10',NULL,'0000-00-00 00:00:00','Author2','blank',NULL,NULL,NULL),(30,'publisher','Publisher',NULL,'2011-01-22 09:46:13',NULL,'0000-00-00 00:00:00','Publisher','blank',NULL,NULL,NULL),(55,'affiliation','Affiliation',NULL,'2011-01-21 13:04:59',NULL,'0000-00-00 00:00:00','Affiliation','combo_box',NULL,NULL,NULL),(60,'url','URL if any',NULL,'2011-01-21 13:05:02',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(65,'other_details','Other Details',NULL,'2011-01-22 14:02:18',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(70,'upload','Upload File',NULL,'2011-01-21 13:05:11',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(35,'isbn','ISBN',NULL,'2011-01-21 13:07:13',NULL,'0000-00-00 00:00:00','ISBN','blank',NULL,NULL,NULL),(40,'publication_month_year','Month and Year of Publication',NULL,'2011-01-27 17:52:22',NULL,'0000-00-00 00:00:00','Month and Year of Publication<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(45,'no_of_pages','No of Pages',NULL,'2011-01-21 13:09:34',NULL,'0000-00-00 00:00:00','No of Pages','blank',NULL,NULL,NULL),(50,'language','Language',NULL,'2011-01-25 17:22:11',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);

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

insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:49:52','except','30',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_book','None','oio','2011-01-21 12:57:57','pickone_radio','Book,Chapter','Book,Chapter',NULL),('combo_box_language','None','oio','2011-01-25 17:23:48','pickone_master','SELECT language_name FROM language_master WHERE active_yes_no=1 ORDER BY language_name ','SELECT language_Id FROM language_master WHERE active_yes_no=1 ORDER BY language_name',NULL),('combo_box_role','None','oio','2011-01-28 12:05:16','pickone','Author,Co-Author,Contributed chapter(s),Compiled and Edited a book but not an author','Author,Co-Author,Contributed chapter(s),Compiled and Edited a book but not an author',NULL);

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

insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'event_name','Name_of_Event',NULL,'2011-01-22 09:46:22',NULL,'0000-00-00 00:00:00','Name of Event<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'location','Location',NULL,'2011-01-29 10:58:36',NULL,'0000-00-00 00:00:00','Location<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'activity_month_year','Month_Year_of_Activity',NULL,'2011-01-22 09:46:27',NULL,'0000-00-00 00:00:00','Month Year of Activity<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL),(25,'other_details','Other_Details',NULL,'2011-01-22 14:08:01',NULL,'0000-00-00 00:00:00','Other_Details','textarea_small',NULL,NULL,NULL),(30,'upload_file','Upload_File',NULL,'2011-01-14 18:43:20',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:44:24','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

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

insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'conference_type','Conference Type',NULL,'2011-01-27 17:22:20',NULL,'0000-00-00 00:00:00','Conference Type<span style=\"color:red\">*</span>','radio_conference',NULL,NULL,NULL),(10,'research_expertise_area','Research Expertise Area',NULL,'2011-01-27 17:22:45',NULL,'0000-00-00 00:00:00','Research Expertise Area<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'paper_associated_project','Is the paper associated with a project',NULL,'2011-01-27 17:23:04',NULL,'0000-00-00 00:00:00','Is the paper associated with a project<span style=\"color:red\">*</span>','radio_associated_project',NULL,NULL,NULL),(20,'project_name','If Yes, Choose Project Name',NULL,'2011-02-03 14:15:23',NULL,'0000-00-00 00:00:00','If Yes, Choose Project Name','combo_box_projects',NULL,NULL,NULL),(25,'paper_title','Title of paper',NULL,'2011-01-27 17:23:18',NULL,'0000-00-00 00:00:00','Title of paper<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'author1','Author1',NULL,'2011-01-27 17:23:26',NULL,'0000-00-00 00:00:00','Author1<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(85,'affiliation','Affiliation',NULL,'2011-01-21 14:59:46',NULL,'0000-00-00 00:00:00','Affiliation','combo_box',NULL,NULL,NULL),(90,'url','URL if any',NULL,'2011-01-21 14:59:42',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(100,'other_details','Other Details',NULL,'2011-01-22 14:05:13',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(105,'upload_paper','Upload File',NULL,'2011-01-21 15:48:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(95,'keyword','Keyword',NULL,'2011-01-21 15:01:33',NULL,'0000-00-00 00:00:00','Keyword','blank',NULL,NULL,NULL),(35,'author2','Author2',NULL,'2011-01-21 15:21:36',NULL,'0000-00-00 00:00:00','Author2','blank',NULL,NULL,NULL),(40,'month_year','Month and Year',NULL,'2011-01-21 15:22:33',NULL,'0000-00-00 00:00:00','Month and Year','calendar',NULL,NULL,NULL),(45,'abstract','Abstract',NULL,'2011-01-21 15:23:41',NULL,'0000-00-00 00:00:00','Abstract','blank',NULL,NULL,NULL),(50,'pages','Pages',NULL,'2011-01-21 15:24:27',NULL,'0000-00-00 00:00:00','Pages','blank',NULL,NULL,NULL),(55,'conference_name','Name of Conference',NULL,'2011-01-21 15:25:35',NULL,'0000-00-00 00:00:00','Name of Conference','combo_box',NULL,NULL,NULL),(60,'date_from','Conference Dates From',NULL,'2011-01-21 15:26:43',NULL,'0000-00-00 00:00:00','Conference Dates From','calendar',NULL,NULL,NULL),(65,'date_to','Conference Dates To',NULL,'2011-01-21 15:27:20',NULL,'0000-00-00 00:00:00','Conference Dates To','calendar',NULL,NULL,NULL),(70,'conference_venue','Conference Venue',NULL,'2011-01-21 15:28:23',NULL,'0000-00-00 00:00:00','Conference Venue','blank',NULL,NULL,NULL),(75,'organizer_name','Name of Organizer',NULL,'2011-01-21 15:29:38',NULL,'0000-00-00 00:00:00','Name of Organizer','blank',NULL,NULL,NULL),(80,'language','Language',NULL,'2011-01-25 17:25:11',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);

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

insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:43:42','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_conference','None','oio','2011-01-21 15:08:37','pickone_radio','National,International','National,International',NULL),('radio_associated_project','None','oio','2011-01-21 15:13:14','pickone_radio','Yes,No','Yes,No',NULL),('combo_box_language','None','oio','2011-01-25 17:26:43','pickone_master','SELECT language_name FROM language_master WHERE active_yes_no=1 ORDER BY language_name ','SELECT language_Id FROM language_master WHERE active_yes_no=1 ORDER BY language_name',NULL),('combo_box_projects','None','oio','2011-02-03 14:16:21','pickone','Datagrid in HTML,Dynamic Allocation','Datagrid in HTML,Dynamic Allocation',NULL);

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

insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'client_name','Name_of_Client',NULL,'2011-01-28 14:15:26',NULL,'0000-00-00 00:00:00','Name of Client<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'duration_from','Duration_From',NULL,'2011-01-28 14:15:31',NULL,'0000-00-00 00:00:00','Duration From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(15,'duration_to','Duration_To',NULL,'2011-01-28 14:15:35',NULL,'0000-00-00 00:00:00','Duration To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'other_faculty_name','If other faculty involved,mention name(s) ',NULL,'2011-01-22 09:47:28',NULL,'0000-00-00 00:00:00','If other faculty involved,mention name(s) ','blank',NULL,NULL,NULL),(25,'revenue_generated','Revenue_Generated',NULL,'2011-01-17 13:07:22',NULL,'0000-00-00 00:00:00','Revenue Generated','blank',NULL,NULL,NULL),(30,'summary_work_undertaken','Brief_Summary_of_Work_Undertaken',NULL,'2011-01-17 13:01:25',NULL,'0000-00-00 00:00:00','Brief Summary of Work Undertaken','textarea',NULL,NULL,NULL),(35,'labs_used','Amrita_Labs/Facilities_used_for_Consultancy',NULL,'2011-01-22 13:34:35',NULL,'0000-00-00 00:00:00','Facilities used for Consultancy','combo_box',NULL,NULL,NULL),(40,'url','URL_if_any',NULL,'2011-01-17 13:08:57',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(45,'other_details','Other_Details',NULL,'2011-01-22 14:10:00',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(50,'upload','Upload_File',NULL,'2011-01-17 13:12:16',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:44:55','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

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

insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'institution_name','Name_of_Institutions_Involved',NULL,'2011-01-28 12:37:57',NULL,'0000-00-00 00:00:00','Name of Institutions Involved<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'subjects_taught','Name_of_Subjects_Taught',NULL,'2011-01-28 12:38:01',NULL,'0000-00-00 00:00:00','Name of Subjects Taught<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'duration_from','Duration_From',NULL,'2011-01-28 12:38:04',NULL,'0000-00-00 00:00:00','Duration From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'duration_to','Duration_To',NULL,'2011-01-28 12:38:07',NULL,'0000-00-00 00:00:00','Duration To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'ug_or_pg','UG_or_PG',NULL,'2011-01-17 18:58:34',NULL,'0000-00-00 00:00:00','UG or PG','radio_button',NULL,NULL,NULL),(30,'research_collaborations','Research_Collaborations_if_any',NULL,'2011-01-17 19:00:10',NULL,'0000-00-00 00:00:00','Research Collaborations if any','blank',NULL,NULL,NULL),(35,'url','URL_if_any',NULL,'2011-01-17 19:02:46',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(40,'other_details','Other_Details',NULL,'2011-01-22 14:10:58',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(45,'upload','File_Upload',NULL,'2011-01-17 19:04:39',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:05','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_button','None','oio','2011-01-17 18:51:51','pickone_radio','UG,PG','UG,PG',NULL);

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

insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'committee_name','Name_of_Committee/Panel',NULL,'2011-01-29 11:10:49',NULL,'0000-00-00 00:00:00','Name of Committee/Panel<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'school_name','Name_of_School/University',NULL,'2011-01-29 11:10:57',NULL,'0000-00-00 00:00:00','Name of School/University<span style=\"color:red\">*</span>','combo_box',NULL,NULL,NULL),(15,'service_dates_from','Service_Dates_From',NULL,'2011-01-29 11:11:14',NULL,'0000-00-00 00:00:00','Service Dates From <span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'service_dates_to','Service_Dates_To',NULL,'2011-01-29 11:11:46',NULL,'0000-00-00 00:00:00','Service Dates To <span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'responsibilities','Responsibilities',NULL,'2011-01-17 10:59:33',NULL,'0000-00-00 00:00:00','Responsibilities','blank',NULL,NULL,NULL),(30,'url','URL',NULL,'2011-01-17 10:59:13',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(35,'upload','Upload',NULL,'2011-01-17 11:00:45',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(40,'other_details','Other_Details',NULL,'2011-01-22 14:09:11',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);

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

insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2011-01-17 11:42:13','pickone','Amrita,Others','Amrita,Others',NULL),('blank','None','oio','2011-02-01 09:37:31','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

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

insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'journal_type','Journal Type',NULL,'2011-01-27 14:58:54',NULL,'0000-00-00 00:00:00','Journal Type<span style=\"color:red\">*</span>','radio_journal',NULL,NULL,NULL),(10,'research_expertise_area','Research Expertise Area',NULL,'2011-01-27 14:59:05',NULL,'0000-00-00 00:00:00','Research Expertise Area<span style=\"color:red\">*</span>','combo_box_specialization',NULL,NULL,NULL),(15,'paper_associated_project','Is the paper associated with a project',NULL,'2011-01-27 14:59:23',NULL,'0000-00-00 00:00:00','Is the paper associated with a project<span style=\"color:red\">*</span>','radio_associated_project',NULL,NULL,NULL),(20,'project_name','If Yes, Choose Project Name',NULL,'2011-02-03 12:37:47',NULL,'0000-00-00 00:00:00','If Yes, Choose Project Name','combo_box_projects',NULL,NULL,NULL),(25,'paper_title','Title of paper',NULL,'2011-01-27 14:59:40',NULL,'0000-00-00 00:00:00','Title of paper<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'author1','Author1',NULL,'2011-01-27 14:59:50',NULL,'0000-00-00 00:00:00','Author1<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(85,'affiliation','Affiliation',NULL,'2011-01-21 14:59:46',NULL,'0000-00-00 00:00:00','Affiliation','combo_box',NULL,NULL,NULL),(90,'url','URL if any',NULL,'2011-01-21 14:59:42',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(100,'other_details','Other Details',NULL,'2011-01-22 14:06:24',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(105,'upload_paper','Upload File',NULL,'2011-01-21 15:48:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(95,'keyword','Keyword',NULL,'2011-01-21 15:01:33',NULL,'0000-00-00 00:00:00','Keyword','blank',NULL,NULL,NULL),(35,'author2','Author2',NULL,'2011-01-21 15:21:36',NULL,'0000-00-00 00:00:00','Author2','blank',NULL,NULL,NULL),(40,'journal_name','Journal Name',NULL,'2011-02-03 12:38:12',NULL,'0000-00-00 00:00:00','Journal Name<span style=\"color:red\">*</span>','combo_box_journals',NULL,NULL,NULL),(45,'volume','Volume',NULL,'2011-01-27 15:01:12',NULL,'0000-00-00 00:00:00','Volume<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(50,'issue_no','Issue No',NULL,'2011-01-27 15:01:30',NULL,'0000-00-00 00:00:00','Issue No<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(55,'month_year','Month and Year',NULL,'2011-01-27 15:01:40',NULL,'0000-00-00 00:00:00','Month and Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(60,'abstract','Abstract',NULL,'2011-01-21 18:00:15',NULL,'0000-00-00 00:00:00','Abstract','blank',NULL,NULL,NULL),(65,'pages','Pages',NULL,'2011-01-21 18:00:50',NULL,'0000-00-00 00:00:00','Pages','blank',NULL,NULL,NULL),(70,'impact_factor','Impact Factor',NULL,'2011-01-21 18:01:40',NULL,'0000-00-00 00:00:00','Impact Factor','blank',NULL,NULL,NULL),(75,'average_citation_index','Average Citation Index',NULL,'2011-01-21 18:02:46',NULL,'0000-00-00 00:00:00','Average Citation Index','blank',NULL,NULL,NULL),(80,'language','Language',NULL,'2011-01-25 09:30:36',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL),(110,'indexed_in_scopus','Whether Indexed in Scopus?(Y/N)',NULL,'2011-01-21 18:05:33',NULL,'0000-00-00 00:00:00','Whether Indexed in Scopus?(Y/N)','radio_associated_project',NULL,NULL,NULL);

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

insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:17:34','except','30',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_journal','None','oio','2011-01-21 17:40:21','pickone_radio','National,International','National,International',NULL),('radio_associated_project','None','oio','2011-01-21 15:13:14','pickone_radio','Yes,No','Yes,No',NULL),('combo_box_language','None','oio','2011-01-25 09:35:33','pickone_master','SELECT language_name FROM language_master WHERE active_yes_no=1 ORDER BY language_name ','SELECT language_Id FROM language_master WHERE active_yes_no=1 ORDER BY language_name',NULL),('combo_box_specialization','None','oio','2011-01-25 17:08:55','pickone_master','SELECT specialization_name FROM specialization_master WHERE active_yes_no=1 ORDER BY specialization_name','SELECT specialization_Id FROM specialization_master WHERE active_yes_no=1 ORDER BY specialization_name',NULL),('combo_box_journals','None','oio','2011-02-03 12:39:47','pickone','ACST,IJCIR,IJICIT,JCSA','ACST,IJCIR,IJICIT,JCSA',NULL),('combo_box_projects','None','oio','2011-02-03 12:43:26','pickone','Datagrid in HTML,Dynamic Allocation','Datagrid in HTML,Dynamic Allocation',NULL);

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

insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'media_type','Type of Media',NULL,'2011-01-28 15:25:45',NULL,'0000-00-00 00:00:00','Type of Media<span style=\"color:red\">*</span>','combo_box_media_types',NULL,NULL,NULL),(10,'title','Title',NULL,'2011-01-28 11:42:20',NULL,'0000-00-00 00:00:00','Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'magazine_name','Name of Magazine/Media',NULL,'2011-01-28 11:42:32',NULL,'0000-00-00 00:00:00','Name of Magazine/Media<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'article_month_year','Month and Year of appearance of article',NULL,'2011-01-28 11:42:42',NULL,'0000-00-00 00:00:00','Month and Year of appearance of article<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'url','URL if any',NULL,'2011-01-19 16:39:08',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(30,'other_details','Other Details',NULL,'2011-01-22 14:12:42',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(35,'upload','Upload File',NULL,'2011-01-19 16:43:25',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:39','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('combo_box_media_types','None','oio','2011-01-28 14:23:06','pickone_master','SELECT media_types_name FROM media_types WHERE active_yes_no=1 ORDER BY media_types_name','SELECT media_types_Id FROM media_types WHERE active_yes_no=1 ORDER BY media_types_name',NULL);

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

insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'choose_patent','Choose if patent is \"National\" or \"International\"',NULL,'2011-01-28 11:17:00',NULL,'0000-00-00 00:00:00','Choose if patent is \"National\" or \"International\"<span style=\"color:red\">*</span>','radio_choose',NULL,NULL,NULL),(10,'country','if International, pick country',NULL,'2011-01-28 15:40:34',NULL,'0000-00-00 00:00:00','if International, pick country','combo_box_country',NULL,NULL,NULL),(15,'patent_title','Title of patent',NULL,'2011-01-28 11:17:13',NULL,'0000-00-00 00:00:00','Title of patent<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'registration_month_year','Month and Year of patent registration',NULL,'2011-01-28 11:17:26',NULL,'0000-00-00 00:00:00','Month and Year of patent registration<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'name_co_applicant','Co-Applicant(s) Name(if any)',NULL,'2011-01-22 09:49:18',NULL,'0000-00-00 00:00:00','Co-Applicant(s) Name(if any)','blank',NULL,NULL,NULL),(30,'patent_details','Patent Details',NULL,'2011-01-22 13:51:18',NULL,'0000-00-00 00:00:00','Patent Details','textarea_small',NULL,NULL,NULL),(35,'affiliation','Affiliation',NULL,'2011-01-20 11:10:14',NULL,'0000-00-00 00:00:00','Affiliation','combo_box',NULL,NULL,NULL),(40,'url','URL if any',NULL,'2011-01-20 11:11:04',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(45,'other_details','Other Details',NULL,'2011-01-22 13:54:16',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(50,'upload','Upload File',NULL,'2011-01-20 11:58:33',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:49','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_choose','None','oio','2011-01-20 11:41:18','pickone_radio','National,International','National,International',NULL),('combo_box_country','None','oio','2011-01-28 15:39:58','pickone_master','SELECT country_name FROM country_master WHERE active_yes_no=1 ORDER BY country_name ','SELECT country_Id FROM country_master WHERE active_yes_no=1 ORDER BY country_name',NULL);

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

insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'professional_body_name','Name/Details_of_Professional_Body/Society ',NULL,'2011-02-03 14:04:56',NULL,'0000-00-00 00:00:00','Name/Details of The Professional Body/Society<span style=\"color:red\">*</span>','combo_box_professional_body',NULL,NULL,NULL),(10,'membership_from','Membership_From',NULL,'2011-01-28 12:58:58',NULL,'0000-00-00 00:00:00','Membership From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(15,'membership_to','Membership_To',NULL,'2011-01-28 12:59:09',NULL,'0000-00-00 00:00:00','Membership To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'membership_type','Membership_Type',NULL,'2011-01-28 15:24:06',NULL,'0000-00-00 00:00:00','Membership Type','combo_box_membership_types',NULL,NULL,NULL),(25,'membership_number','Membership_Number',NULL,'2011-01-22 09:49:38',NULL,'0000-00-00 00:00:00','Membership Number','blank',NULL,NULL,NULL),(30,'other_details','Other_Details',NULL,'2011-01-22 14:10:47',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(35,'upload','Upload_File',NULL,'2011-01-17 16:04:04',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(40,'url','URL_if_any',NULL,'2011-01-17 16:05:31',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);

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

insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:59','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('combo_box_membership_types','None','oio','2011-01-28 14:45:09','pickone_master','SELECT membership_type_name FROM membership_types WHERE active_yes_no=1 ORDER BY membership_type_name','SELECT membership_type_Id FROM membership_types WHERE active_yes_no=1 ORDER BY membership_type_name',NULL),('combo_box_professional_body','None','oio','2011-02-03 14:08:06','pickone','NCERT,AICTE,SCERT','NCERT,AICTE,SCERT',NULL);

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

insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'project_status','Project Status',NULL,'2011-01-28 10:28:35',NULL,'0000-00-00 00:00:00','Project Status<span style=\"color:red\">*</span>','radio_status',NULL,NULL,NULL),(10,'project_title','Project Title',NULL,'2011-01-28 10:28:45',NULL,'0000-00-00 00:00:00','Project Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'principal_investigator','Principal Investigator(PI)',NULL,'2011-02-03 12:26:49',NULL,'0000-00-00 00:00:00','Principal Investigator(PI)<span style=\"color:red\">*</span>','combo_box_PI',NULL,NULL,NULL),(20,'start_date','Project Start Date',NULL,'2011-01-28 10:29:06',NULL,'0000-00-00 00:00:00','Project Start Date<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'duration','Duration',NULL,'2011-01-28 10:29:16',NULL,'0000-00-00 00:00:00','Duration<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'amount','Amount',NULL,'2011-01-28 10:29:29',NULL,'0000-00-00 00:00:00','Amount<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(35,'project_center_department','Which Center/Department the project is associated with',NULL,'2011-02-03 12:30:59',NULL,'0000-00-00 00:00:00','Which Center/Department the project is associated with<span style=\"color:red\">*</span>','combo_box_dept',NULL,NULL,NULL),(40,'abstract','Abstract',NULL,'2011-01-28 10:29:46',NULL,'0000-00-00 00:00:00','Abstract<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(45,'funding_source','Source of Funding',NULL,'2011-01-20 13:29:35',NULL,'0000-00-00 00:00:00','Source of Funding','combo_box',NULL,NULL,NULL),(50,'url','URL if any',NULL,'2011-01-20 13:30:17',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(55,'other_details','Other Details',NULL,'2011-01-22 13:55:12',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(60,'upload','Upload File',NULL,'2011-01-20 13:33:37',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:07','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_status','None','oio','2011-01-20 13:37:02','pickone_radio','Ongoing,Completed','Ongoing,Completed',NULL),('combo_box_PI','None','oio','2011-02-03 12:28:24','pickone','Anil Sarma,Neetha Varma','Anil Sarma,Neetha Varma',NULL),('combo_box_dept','None','oio','2011-02-03 12:31:51','pickone','Computer Science,Electronis','Computer Science,Electronis',NULL);

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
  `upload` varchar(255) default NULL
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

insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'course_name','Course Name',NULL,'2011-01-22 16:43:43',NULL,'0000-00-00 00:00:00','Qualification<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'institution','Institution',NULL,'2011-01-22 09:50:09',NULL,'0000-00-00 00:00:00','Institution<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'location','Location',NULL,'2011-01-29 11:25:55',NULL,'0000-00-00 00:00:00','Location<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'board','Board',NULL,'2011-01-29 11:25:57',NULL,'0000-00-00 00:00:00','Board<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'join_month','Month',NULL,'2011-01-29 11:25:58',NULL,'0000-00-00 00:00:00','Month<span style=\"color:red\">*</span>','month_combo_box',NULL,NULL,NULL),(30,'join_year','Year',NULL,'2011-01-29 11:26:05',NULL,'0000-00-00 00:00:00','Year<span style=\"color:red\">*</span>','year_combo_box',NULL,NULL,NULL),(35,'passing_year','Passing Year',NULL,'2011-01-29 11:26:35',NULL,'0000-00-00 00:00:00','Passing Year','blank',NULL,NULL,NULL),(40,'percentage','Percentage',NULL,'2011-01-29 11:27:06',NULL,'0000-00-00 00:00:00','Percentage','blank',NULL,NULL,NULL),(45,'class','Class',NULL,'2011-01-29 11:26:39',NULL,'0000-00-00 00:00:00','Class','blank',NULL,NULL,NULL);

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

insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:17','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2011-01-24 17:00:38','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('blank_small','None','oio','2010-12-06 10:02:44','except','7','',NULL);

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
  `mainSubj` varchar(255) default NULL
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

insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'upload_resume','Upload Resume :',NULL,'2011-01-13 11:28:21',NULL,'0000-00-00 00:00:00','Upload Resume :','file_upload',NULL,NULL,NULL),(5,'focus_area','Area of Study / Focus Area',NULL,'2011-01-29 16:01:23',NULL,'0000-00-00 00:00:00','Area of Study / Focus Area<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'qualification','Qualification',NULL,'2011-01-13 16:46:59',NULL,'0000-00-00 00:00:00','Qualification','qualification_childform',NULL,NULL,NULL),(15,'research_interests','Research Interests',NULL,'2011-01-29 16:04:49',NULL,'0000-00-00 00:00:00','Research Interests','textarea_small',NULL,NULL,NULL),(20,'teaching_interests','Teaching Interests',NULL,'2011-01-29 16:05:20',NULL,'0000-00-00 00:00:00','Teaching Interests','textarea_small',NULL,NULL,NULL),(25,'positions_held','Positions Held',NULL,'2011-01-29 16:05:22',NULL,'0000-00-00 00:00:00','Positions Held','textarea_small',NULL,NULL,NULL),(30,'office_room_num','Office Room Number',NULL,'2011-01-25 12:07:32',NULL,'0000-00-00 00:00:00','Office Room Number<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(35,'office_phone_ext','Office Phone Ext.',NULL,'2011-01-25 12:08:22',NULL,'0000-00-00 00:00:00','Office Phone Ext.<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(40,'email_id','Amrita Email ID',NULL,'2011-01-25 12:08:25',NULL,'0000-00-00 00:00:00','Email ID<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(45,'email','Personal Email ID',NULL,'2011-01-13 11:30:47',NULL,'0000-00-00 00:00:00','Personal Email ID','blank',NULL,NULL,NULL),(50,'home_phone','Home Phone',NULL,'2011-01-13 11:31:00',NULL,'0000-00-00 00:00:00','Home Phone','blank',NULL,NULL,NULL),(55,'fax','Fax',NULL,'2011-01-13 11:31:06',NULL,'0000-00-00 00:00:00','Fax','blank',NULL,NULL,NULL),(70,'awards','Award Details',NULL,'2011-01-22 12:46:39',NULL,'0000-00-00 00:00:00','Awards Details','awards_childform',NULL,NULL,NULL),(75,'community_service','Community Service',NULL,'2011-01-22 12:46:40',NULL,'0000-00-00 00:00:00','Community Service Details','community_service_childform',NULL,NULL,NULL),(80,'review_committees','Review Committees',NULL,'2011-01-22 12:46:41',NULL,'0000-00-00 00:00:00','Review Committees Details','review_committees_childform',NULL,NULL,NULL),(85,'governance','Governance',NULL,'2011-01-22 12:46:44',NULL,'0000-00-00 00:00:00','Governance Details','governance_childform',NULL,NULL,NULL),(90,'consultancy_offered','Consultancy Offered',NULL,'2011-01-22 12:46:45',NULL,'0000-00-00 00:00:00','Consultancy Offered Details','consultancy_offered_childform',NULL,NULL,NULL),(95,'professional_body','Professional Bodies',NULL,'2011-01-22 12:46:47',NULL,'0000-00-00 00:00:00','Professional Bodies/Societies Details','professional_body_childform',NULL,NULL,NULL),(100,'faculty_exchange','Faculty_Exchange',NULL,'2011-01-22 12:46:48',NULL,'0000-00-00 00:00:00','Faculty Exchange Program Details','faculty_exchange_childform',NULL,NULL,NULL),(105,'thesis','Thesis/Student Project',NULL,'2011-01-22 12:46:49',NULL,'0000-00-00 00:00:00','Thesis/Student Project Details','thesis_childform',NULL,NULL,NULL),(110,'media_publication','Media Publication',NULL,'2011-01-22 12:46:51',NULL,'0000-00-00 00:00:00','Media Publication Details','media_publication_childform',NULL,NULL,NULL),(115,'patents','Patents',NULL,'2011-01-22 12:46:54',NULL,'0000-00-00 00:00:00','Patents Details','patents_childform',NULL,NULL,NULL),(120,'projects','Projects',NULL,'2011-01-22 12:46:56',NULL,'0000-00-00 00:00:00','Projects Details','projects_childform',NULL,NULL,NULL),(125,'training','Training',NULL,'2011-01-22 12:46:58',NULL,'0000-00-00 00:00:00','Seminars/Conferences/Workshops/Training Attended Details','training_childform',NULL,NULL,NULL),(130,'talks_lectures','Talks Lectures',NULL,'2011-01-22 12:47:00',NULL,'0000-00-00 00:00:00','Invited Talks/Guest Lectures Details','talks_lectures_childform',NULL,NULL,NULL),(140,'books_chapter','Books/Chapter',NULL,'2011-01-22 12:47:02',NULL,'0000-00-00 00:00:00','Books/Chapter Details','books_chapter_childform',NULL,NULL,NULL),(145,'conference_papers','Conference Papers',NULL,'2011-01-22 12:47:04',NULL,'0000-00-00 00:00:00','Conference Papers Details','conference_papers_childform',NULL,NULL,NULL),(150,'journal_papers','Journal Papers',NULL,'2011-01-22 12:47:07',NULL,'0000-00-00 00:00:00','Journal Papers Details','journal_papers_childform',NULL,NULL,NULL);

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

insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2011-01-22 16:39:40','text_area','5,50',NULL,NULL),('combo_box','None','oio','2011-01-22 16:40:58','pickone','1,2,3','1,2,3',NULL),('tableColHeader','None','oio','2011-01-22 16:39:40','show_text',' ',NULL,NULL),('blank','None','oio','2011-01-29 16:11:55','except','30',NULL,NULL),('calendar','None','oio','2011-01-22 16:39:40','calendar','',NULL,NULL),('textarea_small','None','oio','2011-01-29 16:04:26','text_area','3,30','',NULL),('month_combo_box','None','oio','2011-01-22 16:41:30','pickone','1,2,3,4,5,6,7,8,9,10,11,12','1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2011-01-22 16:41:41','pickone','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('paper_status_combo_box','None','oio','2011-01-22 16:41:59','pickone','Select,Communicated,Accepted,Published','Select,Communicated,Accepted,Published',NULL),('paper_type_combo_box','None','oio','2011-01-22 16:42:11','pickone','Select,International,National','Select,International,National',NULL),('jouranltype_combo_box','None','oio','2011-01-22 16:42:22','pickone','Select,Journal,Conference Proceedings','Select,Journal,Conference Proceedings',NULL),('seminar_purpose_combo_box','None','oio','2011-01-22 16:42:29','pickone','Select,Presented Paper,Participation','Select,Presented Paper,Participation',NULL),('textarea_large','None','oio','2011-01-22 16:39:40','text_area','15,70',NULL,NULL),('blank_small','None','oio','2011-01-22 16:39:40','except','7',NULL,NULL),('file_upload','None','oio','2011-01-22 16:39:40','file_upload',NULL,NULL,NULL),('qualification_childform','None','oio','2011-01-29 11:28:35','detail_form','staff_profile_qualification_v0','course_name,institution,location,board,join_month,join_year,passing_year,percentage,class',NULL),('awards_childform','None','oio','2011-01-25 16:33:51','detail_form','staff_profile_awards_v0','award_name,agency_name,receiving_month_year',NULL),('community_service_childform','None','oio','2011-01-29 10:59:46','detail_form','staff_profile_community_service_v0','event_name,location,activity_month_year',NULL),('review_committees_childform','None','oio','2011-01-29 11:07:34','detail_form','staff_profile_review_committees_v0','committee_type,role_played,title,appointed_month_year',NULL),('governance_childform','None','oio','2011-01-25 11:27:05','detail_form','staff_profile_governance_v0','committee_name,school_name,service_dates_from,service_dates_to',NULL),('consultancy_offered_childform','None','oio','2011-01-25 11:28:00','detail_form','staff_profile_consultancy_offered_v0','client_name,duration_from,duration_to',NULL),('professional_body_childform','None','oio','2011-01-28 12:55:41','detail_form','staff_profile_professional_body_v0','professional_body_name,membership_from,membership_to',NULL),('faculty_exchange_childform','None','oio','2011-01-25 11:29:47','detail_form','staff_profile_faculty_exchange_v0','institution_name,subjects_taught,duration_from,duration_to',NULL),('thesis_childform','None','oio','2011-01-25 11:30:44','detail_form','staff_profile_thesis_v0','choose,thesis_title,project_title',NULL),('media_publication_childform','None','oio','2011-01-28 11:44:55','detail_form','staff_profile_media_publication_v0','media_type,title,magazine_name,article_month_year',NULL),('patents_childform','None','oio','2011-01-28 11:23:41','detail_form','staff_profile_patents_v0','choose_patent,patent_title,registration_month_year',NULL),('projects_childform','None','oio','2011-01-28 10:31:20','detail_form','staff_profile_projects_v0','project_status,project_title,principal_investigator,start_date,duration,amount,project_center_department,abstract',NULL),('training_childform','None','oio','2011-01-28 10:18:06','detail_form','staff_profile_training_v0','training_name,duration_from,duration_to,location',NULL),('talks_lectures_childform','None','oio','2011-01-28 10:17:21','detail_form','staff_profile_talks_lectures_v0','institution_address,event_name,lecture_topic,talk_date_time',NULL),('books_chapter_childform','None','oio','2011-01-27 17:57:50','detail_form','staff_profile_books_chapter_v0','book_chapter,title,author1,publication_month_year',NULL),('conference_papers_childform','None','oio','2011-01-27 17:39:13','detail_form','staff_profile_conference_papers_v0','conference_type,research_expertise_area,paper_associated_project,paper_title,author1',NULL),('journal_papers_childform','None','oio','2011-01-27 17:37:47','detail_form','staff_profile_journal_papers_v0','journal_type,research_expertise_area,paper_associated_project,paper_title,author1,journal_name,volume,issue_no,month_year',NULL);

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

insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'committee_type','Committee_Type',NULL,'2011-01-29 11:03:44',NULL,'0000-00-00 00:00:00','Commitee Type<span style=\"color:red\">*</span>','committee_type_combo_box',NULL,NULL,NULL),(10,'role_played','Role_Played',NULL,'2011-01-29 11:03:46',NULL,'0000-00-00 00:00:00','Role Played<span style=\"color:red\">*</span>','role_played_combo_box',NULL,NULL,NULL),(15,'title','Title',NULL,'2011-01-29 11:06:21',NULL,'0000-00-00 00:00:00','Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL),(25,'other_details','Other_Details',NULL,'2011-01-22 14:08:41',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(30,'file_upload','File_Upload',NULL,'2011-01-15 15:08:44',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(35,'appointed_month_year','Month and Year Appointed',NULL,'2011-01-29 11:05:44',NULL,'0000-00-00 00:00:00','Appointed Month and Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL);

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

insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:37','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('committee_type_combo_box','None','oio','2011-01-15 15:40:40','pickone','journal,conference,project,others','journal,conference,project,others',NULL),('role_played_combo_box','None','oio','2011-01-15 15:42:59','pickone','member,editor,reviewer,chairperson','member,editor,reviewer,chairperson',NULL);

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

insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'event','Was the event :\"National\" or \"International\"',NULL,'2011-01-22 09:51:14',NULL,'0000-00-00 00:00:00','Was the event :\"National\" or \"International\"','radio_event',NULL,NULL,NULL),(10,'institution_address','Name and Address of the Institution',NULL,'2011-01-28 09:41:57',NULL,'0000-00-00 00:00:00','Name and Address of the Institution<span style=\"color:red\">*</span>','textarea_small',NULL,NULL,NULL),(15,'event_name','Name of the event',NULL,'2011-01-28 09:42:14',NULL,'0000-00-00 00:00:00','Name of the event<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'lecture_topic','Lecture Topic',NULL,'2011-01-28 09:42:29',NULL,'0000-00-00 00:00:00','Lecture Topic<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'talk_date_time','Date and Time of Talk',NULL,'2011-01-28 09:42:40',NULL,'0000-00-00 00:00:00','Date and Time of Talk<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(30,'participants_level','Participants Level',NULL,'2011-01-20 18:02:14',NULL,'0000-00-00 00:00:00','Participants Level','combo_box',NULL,NULL,NULL),(35,'related_research_group','Related Research Group and Centres',NULL,'2011-01-20 18:04:11',NULL,'0000-00-00 00:00:00','Related Research Group and Centres','blank',NULL,NULL,NULL),(50,'url','URL if any',NULL,'2011-01-20 18:04:37',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(55,'other_details','Other Details',NULL,'2011-01-22 13:58:15',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(60,'upload','Upload File',NULL,'2011-01-20 18:04:49',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(40,'duration_from','Duration From',NULL,'2011-01-20 18:05:44',NULL,'0000-00-00 00:00:00','Duration From','calendar',NULL,NULL,NULL),(45,'duration_to','Duration To',NULL,'2011-01-20 18:06:27',NULL,'0000-00-00 00:00:00','Duration To','calendar',NULL,NULL,NULL);

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

insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:47','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_event','None','oio','2011-01-20 17:54:28','pickone_radio','National,International','National,International',NULL);

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

insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'choose','Choose \"Thesis\" or \"Student Project\"',NULL,'2011-01-22 09:52:13',NULL,'0000-00-00 00:00:00','Choose \"Thesis\" or \"Student Project\"','radio_choose',NULL,NULL,NULL),(10,'thesis_title','Title of Thesis',NULL,'2011-01-22 09:52:01',NULL,'0000-00-00 00:00:00','Title of Thesis','blank',NULL,NULL,NULL),(15,'thesis_author','Author',NULL,'2011-01-22 09:52:05',NULL,'0000-00-00 00:00:00','Author','blank',NULL,NULL,NULL),(20,'thesis_department_institution','Department and Institution',NULL,'2011-01-22 09:52:35',NULL,'0000-00-00 00:00:00','Department and Institution','blank',NULL,NULL,NULL),(25,'thesis_course_programe','Course/Programe',NULL,'2011-01-18 18:51:11',NULL,'0000-00-00 00:00:00','Course/Programe','blank',NULL,NULL,NULL),(30,'thesis_external_internal','External or Internal',NULL,'2011-01-19 11:59:00',NULL,'0000-00-00 00:00:00','External or Internal','radio_thesis_external_internal',NULL,NULL,NULL),(35,'thesis_registration_month_year','Month and Year of Registration',NULL,'2011-01-18 18:56:48',NULL,'0000-00-00 00:00:00','Month and Year of Registration','calendar',NULL,NULL,NULL),(40,'thesis_outcome','Outcome',NULL,'2011-01-19 11:59:34',NULL,'0000-00-00 00:00:00','Outcome','radio_thesis_outcome',NULL,NULL,NULL),(45,'thesis_completion_month_year','Month and Year of Completion',NULL,'2011-01-18 19:00:32',NULL,'0000-00-00 00:00:00','Month and Year of Completion','calendar',NULL,NULL,NULL),(50,'thesis_url','URL if any',NULL,'2011-01-18 19:01:52',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(55,'thesis_other_details','Other Details',NULL,'2011-01-22 14:11:38',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(60,'thesis_upload','Upload File',NULL,'2011-01-18 19:04:40',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(65,'project_title','Title of Student Project',NULL,'2011-01-22 09:52:20',NULL,'0000-00-00 00:00:00','Title of Student Project','blank',NULL,NULL,NULL),(70,'project_student_group','Number of Student/Group',NULL,'2011-01-22 09:52:22',NULL,'0000-00-00 00:00:00','Number of Student/Group','blank',NULL,NULL,NULL),(75,'project_student_name','Name of Students',NULL,'2011-01-22 09:52:27',NULL,'0000-00-00 00:00:00','Name of Students','blank',NULL,NULL,NULL),(80,'project_type','Project Type',NULL,'2011-01-22 09:52:31',NULL,'0000-00-00 00:00:00','Project Type','combo_box',NULL,NULL,NULL),(85,'project_academic_term','Choose Academic Term when Project Started',NULL,'2011-01-19 09:49:50',NULL,'0000-00-00 00:00:00','Choose Academic Term when Project Started','combo_box',NULL,NULL,NULL),(90,'project_start_month_year','Month and Year of Start',NULL,'2011-01-19 09:51:40',NULL,'0000-00-00 00:00:00','Month and Year of Start','calendar',NULL,NULL,NULL),(95,'project_outcome','Outcome',NULL,'2011-01-19 12:00:07',NULL,'0000-00-00 00:00:00','Outcome','radio_project_outcome',NULL,NULL,NULL),(100,'project_completion_month_year','Month and Year of Completion',NULL,'2011-01-19 09:54:34',NULL,'0000-00-00 00:00:00','Month and Year of Completion','calendar',NULL,NULL,NULL),(105,'project_url','URL if any',NULL,'2011-01-19 09:55:49',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(110,'project_other_details','Other Details',NULL,'2011-01-22 14:11:54',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(115,'project_upload','Upload File',NULL,'2011-01-19 09:59:25',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:57','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_choose','None','oio','2011-01-24 09:32:19','pickone_radio_choose','Thesis,Student Project','Thesis,Student Project',NULL),('radio_thesis_external_internal','None','oio','2011-01-19 12:04:31','pickone_radio','External,Internal','External,Internal',NULL),('radio_thesis_outcome','None','oio','2011-01-19 12:06:19','pickone_radio','In Progress,Completed','In Progress,Completed',NULL),('radio_project_outcome','None','oio','2011-01-19 12:07:58','pickone_radio','In Progress,Completed','In Progress,Completed',NULL);

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

insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'training_name','Name of Seminar/Conference/Workshop/Training',NULL,'2011-01-28 10:05:32',NULL,'0000-00-00 00:00:00','Name of Seminar/Conference/Workshop/Training<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'duration_from','Duration From',NULL,'2011-01-28 10:05:40',NULL,'0000-00-00 00:00:00','Duration From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(15,'duration_to','Duration To',NULL,'2011-01-28 10:05:47',NULL,'0000-00-00 00:00:00','Duration To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'location','Location',NULL,'2011-01-28 10:05:55',NULL,'0000-00-00 00:00:00','Location<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'purpose','Co-Applicant(s) Name(if any)',NULL,'2011-01-20 16:09:12',NULL,'0000-00-00 00:00:00','Co-Applicant(s) Name(if any)','blank',NULL,NULL,NULL),(30,'funding_source','Source of Funding',NULL,'2011-01-20 16:11:35',NULL,'0000-00-00 00:00:00','Source of Funding','combo_box',NULL,NULL,NULL),(35,'funded_amount','Amount Funded',NULL,'2011-01-20 16:12:47',NULL,'0000-00-00 00:00:00','Amount Funded','blank',NULL,NULL,NULL),(40,'url','URL if any',NULL,'2011-01-20 11:11:04',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(45,'other_details','Other Details',NULL,'2011-01-22 13:56:52',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(50,'upload','Upload File',NULL,'2011-01-20 11:58:33',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

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

insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:47:09','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_choose','None','oio','2011-01-20 11:41:18','pickone_radio','National,International','National,International',NULL);

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
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_values` */

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

insert  into `users`(`username`,`password`,`email`,`enabled`,`active_yesno`,`id`,`user_full_name`) values ('admin','ec4455636479481fde628b5673d627a44bbe992081c916387ee5a4e8fbdb4e9e','admin@gmail.com',1,0,1,'ADMINISTRATOR'),('rajesh.sinha@yahoo.com','8ad9b2a507a5327f855e4af2c26bfc89d173e6cc824543aa5910bebdd03510ad','rajesh.sinha@yahoo.com',1,0,2,'Rajesh Sinha');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
