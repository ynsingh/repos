/*
SQLyog Community Edition- MySQL GUI v8.18 
MySQL - 5.0.41-log : Database - gms_live
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

/*Table structure for table `acl_class` */

DROP TABLE IF EXISTS `acl_class`;

CREATE TABLE `acl_class` (
  `id` bigint(20) NOT NULL auto_increment,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `class` (`class`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `acl_entry` */

DROP TABLE IF EXISTS `acl_entry`;

CREATE TABLE `acl_entry` (
  `id` bigint(20) NOT NULL auto_increment,
  `ace_order` int(11) NOT NULL,
  `acl_object_identity` bigint(20) NOT NULL,
  `audit_failure` bit(1) NOT NULL,
  `audit_success` bit(1) NOT NULL,
  `granting` bit(1) NOT NULL,
  `mask` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `acl_object_identity` (`acl_object_identity`,`ace_order`),
  KEY `FK5302D47DB0D9DC4D` (`acl_object_identity`),
  KEY `FK5302D47D8FDB88D5` (`sid`),
  CONSTRAINT `FK5302D47D8FDB88D5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`),
  CONSTRAINT `FK5302D47DB0D9DC4D` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `acl_object_identity` */

DROP TABLE IF EXISTS `acl_object_identity`;

CREATE TABLE `acl_object_identity` (
  `id` bigint(20) NOT NULL auto_increment,
  `object_id_class` bigint(20) NOT NULL,
  `entries_inheriting` bit(1) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `owner_sid` bigint(20) default NULL,
  `parent_object` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `object_id_class` (`object_id_class`,`object_id_identity`),
  KEY `FK2A2BB00990EC1949` (`owner_sid`),
  KEY `FK2A2BB009A50290B8` (`parent_object`),
  KEY `FK2A2BB00970422CC5` (`object_id_class`),
  CONSTRAINT `FK2A2BB00970422CC5` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  CONSTRAINT `FK2A2BB00990EC1949` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`),
  CONSTRAINT `FK2A2BB009A50290B8` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `acl_sid` */

DROP TABLE IF EXISTS `acl_sid`;

CREATE TABLE `acl_sid` (
  `id` bigint(20) NOT NULL auto_increment,
  `principal` bit(1) NOT NULL,
  `sid` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `sid` (`sid`,`principal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `attachment_type` */

DROP TABLE IF EXISTS `attachment_type`;

CREATE TABLE `attachment_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `description` varchar(255) default NULL,
  `document_type` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `attachments` */

DROP TABLE IF EXISTS `attachments`;

CREATE TABLE `attachments` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `attachment_path` varchar(255) default NULL,
  `attachment_type_id` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `domain` varchar(255) NOT NULL,
  `domain_id` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `opened_yes_no` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD3F3CBB0F19EEC41` (`attachment_type_id`),
  CONSTRAINT `FKD3F3CBB0F19EEC41` FOREIGN KEY (`attachment_type_id`) REFERENCES `attachment_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `audit_log` */

DROP TABLE IF EXISTS `audit_log`;

CREATE TABLE `audit_log` (
  `id` bigint(20) NOT NULL auto_increment,
  `actor` varchar(255) default NULL,
  `class_name` varchar(255) default NULL,
  `date_created` datetime NOT NULL,
  `event_name` varchar(255) default NULL,
  `last_updated` datetime NOT NULL,
  `new_value` varchar(255) default NULL,
  `old_value` varchar(255) default NULL,
  `persisted_object_id` bigint(20) default NULL,
  `persisted_object_version` bigint(20) default NULL,
  `property_name` varchar(255) default NULL,
  `uri` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `authority` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authority` */

insert  into `authority`(`id`,`version`,`active_yes_no`,`authority`,`description`) values (1,4,'Y','ROLE_ADMIN','admin'),(2,43,'Y','ROLE_USER','Grant Manager Role'),(3,16,'Y','ROLE_SITEADMIN','ROLE_SITEADMIN'),(4,13,'Y','ROLE_PI','Investigator Role'),(5,2,'Y','ROLE_PROJECTADMIN','PROJECTADMIN'),(6,0,'Y','ROLE_PM','Project management'),(7,0,'Y','ROLE_ACCT_OFFICER','Accounts Officer'),(8,0,'Y','ROLE_SUPER','super');


/*Table structure for table `authority_person` */

DROP TABLE IF EXISTS `authority_person`;

CREATE TABLE `authority_person` (
  `authority_people_id` bigint(20) default NULL,
  `person_id` bigint(20) default NULL,
  KEY `FK4C086FD1C12321BA` (`person_id`),
  KEY `FK4C086FD1353431F2` (`authority_people_id`),
  CONSTRAINT `FK4C086FD1353431F2` FOREIGN KEY (`authority_people_id`) REFERENCES `authority` (`id`),
  CONSTRAINT `FK4C086FD1C12321BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `employee_designation` */

DROP TABLE IF EXISTS `employee_designation`;

CREATE TABLE `employee_designation` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `designation` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `expense_request` */

DROP TABLE IF EXISTS `expense_request`;

CREATE TABLE `expense_request` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `account_head_id` bigint(20) NOT NULL,
  `fund_available_yes_no` char(1) NOT NULL,
  `grant_allocation_id` bigint(20) NOT NULL,
  `requested_amount` double NOT NULL,
  `requested_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBA1189A83036284E` (`account_head_id`),
  KEY `FKBA1189A8B8D959F3` (`grant_allocation_id`),
  CONSTRAINT `FKBA1189A8B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`),
  CONSTRAINT `FKBA1189A83036284E` FOREIGN KEY (`account_head_id`) REFERENCES `account_heads` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `fund_transfer` */

DROP TABLE IF EXISTS `fund_transfer`;

CREATE TABLE `fund_transfer` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `date_of_transfer` datetime NOT NULL,
  `grant_allocation_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK45181425B8D959F3` (`grant_allocation_id`),
  CONSTRAINT `FK45181425B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `gms_settings` */

DROP TABLE IF EXISTS `gms_settings`;

CREATE TABLE `gms_settings` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `gms_settings` */

insert  into `gms_settings`(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (2,2,NULL,NULL,NULL,NULL,'ApplicationForm','/usr/local/MgmsSettings/Notification/'),(5,0,NULL,NULL,NULL,NULL,'MailSubject','Confirmation'),(6,0,NULL,NULL,NULL,NULL,'MailContent','You have successfully created a user'),(7,0,NULL,NULL,NULL,NULL,'MailHost','192.168.36.10'),(8,0,NULL,NULL,NULL,NULL,'MailPort','25'),(9,0,NULL,NULL,NULL,NULL,'MailUserName',''),(10,0,NULL,NULL,NULL,NULL,'MailPassword',''),(11,0,NULL,NULL,NULL,NULL,'MailFrom','no-reply@yourdomain.com'),(12,0,NULL,NULL,NULL,NULL,'Attachments','/usr/local/appform/'),(13,0,NULL,NULL,NULL,NULL,'','');


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

/*Table structure for table `grant_expense` */

DROP TABLE IF EXISTS `grant_expense`;

CREATE TABLE `grant_expense` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `bank_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_expense` datetime NOT NULL,
  `dd_branch` varchar(255) NOT NULL,
  `dd_date` datetime NOT NULL,
  `dd_no` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `expense_amount` double NOT NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_allocation_split_id` bigint(20) default NULL,
  `mode_of_payment` varchar(255) NOT NULL,
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

/*Table structure for table `grant_period` */

DROP TABLE IF EXISTS `grant_period`;

CREATE TABLE `grant_period` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `default_yes_no` char(1) NOT NULL,
  `end_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `grant_receipt` */

DROP TABLE IF EXISTS `grant_receipt`;

CREATE TABLE `grant_receipt` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `bank_name` varchar(255) default NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_receipt` datetime NOT NULL,
  `dd_branch` varchar(255) default NULL,
  `dd_date` datetime default NULL,
  `dd_no` varchar(255) default NULL,
  `description` varchar(255) NOT NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_allocation_split_id` bigint(20) default NULL,
  `mode_of_payment` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `reference_id` varchar(255) NOT NULL,
  `fund_transfer_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC21DFE15B8D959F3` (`grant_allocation_id`),
  KEY `FKC21DFE15905C4B9A` (`projects_id`),
  KEY `FKC21DFE1533CE82BA` (`grant_allocation_split_id`),
  KEY `FKC21DFE15E6A7AD65` (`fund_transfer_id`),
  CONSTRAINT `FKC21DFE15E6A7AD65` FOREIGN KEY (`fund_transfer_id`) REFERENCES `fund_transfer` (`id`),
  CONSTRAINT `FKC21DFE1533CE82BA` FOREIGN KEY (`grant_allocation_split_id`) REFERENCES `grant_allocation_split` (`id`),
  CONSTRAINT `FKC21DFE15905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKC21DFE15B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `investigator` */

DROP TABLE IF EXISTS `investigator`;

CREATE TABLE `investigator` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `address` varchar(255) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `name` (`name`),
  KEY `FK967BBBA99F389FE0` (`department_id`),
  KEY `FK967BBBA94B78CFDA` (`party_id`),
  CONSTRAINT `FK967BBBA94B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FK967BBBA99F389FE0` FOREIGN KEY (`department_id`) REFERENCES `party_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `item_purchase` */

DROP TABLE IF EXISTS `item_purchase`;

CREATE TABLE `item_purchase` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `asset_code` varchar(255) NOT NULL,
  `bill_no` varchar(255) NOT NULL,
  `cost` double NOT NULL,
  `date_received` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `order_no` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `projects_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7F0A80AD905C4B9A` (`projects_id`),
  CONSTRAINT `FK7F0A80AD905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `application_form` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `description` varchar(255) default NULL,
  `eligibilitydocument` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `notification_code` varchar(255) NOT NULL,
  `notification_date` datetime NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `proposal_submission_last_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `notification_code` (`notification_code`),
  KEY `FK237A88EB52E8209B` (`project_id`),
  CONSTRAINT `FK237A88EB52E8209B` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `notifications_attachments` */

DROP TABLE IF EXISTS `notifications_attachments`;

CREATE TABLE `notifications_attachments` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `attachment_path` varchar(255) default NULL,
  `attachment_type_id` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `notification_id` bigint(20) NOT NULL,
  `proposal_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD036A2D9F19EEC41` (`attachment_type_id`),
  KEY `FKD036A2D92B39989A` (`proposal_id`),
  KEY `FKD036A2D9DDCA99FA` (`notification_id`),
  CONSTRAINT `FKD036A2D9DDCA99FA` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FKD036A2D92B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`),
  CONSTRAINT `FKD036A2D9F19EEC41` FOREIGN KEY (`attachment_type_id`) REFERENCES `attachment_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `notifications_emails` */

DROP TABLE IF EXISTS `notifications_emails`;

CREATE TABLE `notifications_emails` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `notification_id` bigint(20) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7D62BB8E4B78CFDA` (`party_id`),
  KEY `FK7D62BB8EDDCA99FA` (`notification_id`),
  CONSTRAINT `FK7D62BB8EDDCA99FA` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FK7D62BB8E4B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

/*Table structure for table `party_department` */

DROP TABLE IF EXISTS `party_department`;

CREATE TABLE `party_department` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
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

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `user_real_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4C808CD580CA8247` (`party_department_id`),
  KEY `FK4C808CD5905C4B9A` (`projects_id`),
  CONSTRAINT `FK4C808CD580CA8247` FOREIGN KEY (`party_department_id`) REFERENCES `party_department` (`id`),
  CONSTRAINT `FK4C808CD5905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `project_employee` */

DROP TABLE IF EXISTS `project_employee`;

CREATE TABLE `project_employee` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `dob` datetime default NULL,
  `emp_name` varchar(255) NOT NULL,
  `emp_no` varchar(255) NOT NULL,
  `employee_designation_id` bigint(20) NOT NULL,
  `joining_date` datetime NOT NULL,
  `project_id` int(11) NOT NULL,
  `relieving_date` datetime default NULL,
  `status` varchar(255) NOT NULL,
  `projects_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `emp_no` (`emp_no`),
  KEY `FK4839148CE4205D` (`employee_designation_id`),
  KEY `FK483914905C4B9A` (`projects_id`),
  CONSTRAINT `FK483914905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK4839148CE4205D` FOREIGN KEY (`employee_designation_id`) REFERENCES `employee_designation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `project_employee_experience` */

DROP TABLE IF EXISTS `project_employee_experience`;

CREATE TABLE `project_employee_experience` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `joining_date` datetime NOT NULL,
  `organization_name` varchar(255) NOT NULL,
  `project_employee_id` bigint(20) NOT NULL,
  `relieving_date` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE62FC175C8FFD4ED` (`project_employee_id`),
  CONSTRAINT `FKE62FC175C8FFD4ED` FOREIGN KEY (`project_employee_id`) REFERENCES `project_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `project_employee_qualification` */

DROP TABLE IF EXISTS `project_employee_qualification`;

CREATE TABLE `project_employee_qualification` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `examname` varchar(255) NOT NULL,
  `passout_year` varchar(255) NOT NULL,
  `perc_mark` double NOT NULL,
  `project_employee_id` bigint(20) NOT NULL,
  `status` varchar(255) NOT NULL,
  `university` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK6DC13604C8FFD4ED` (`project_employee_id`),
  CONSTRAINT `FK6DC13604C8FFD4ED` FOREIGN KEY (`project_employee_id`) REFERENCES `project_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `project_employee_salary_details` */

DROP TABLE IF EXISTS `project_employee_salary_details`;

CREATE TABLE `project_employee_salary_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `end_date` datetime default NULL,
  `project_employee_id` bigint(20) NOT NULL,
  `salary_amount` double NOT NULL,
  `salary_component_id` bigint(20) NOT NULL,
  `status` varchar(255) NOT NULL,
  `with_effect_from` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1F466358B27ABE25` (`salary_component_id`),
  KEY `FK1F466358C8FFD4ED` (`project_employee_id`),
  CONSTRAINT `FK1F466358C8FFD4ED` FOREIGN KEY (`project_employee_id`) REFERENCES `project_employee` (`id`),
  CONSTRAINT `FK1F466358B27ABE25` FOREIGN KEY (`salary_component_id`) REFERENCES `salary_component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

/*Table structure for table `project_type` */

DROP TABLE IF EXISTS `project_type`;

CREATE TABLE `project_type` (
  `id` int(11) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

/*Table structure for table `projectspimap` */

DROP TABLE IF EXISTS `projectspimap`;

CREATE TABLE `projectspimap` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `investigator_id` bigint(20) NOT NULL,
  `projects_id` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL,
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKEA00CC89905C4B9A` (`projects_id`),
  KEY `FKEA00CC89893BB93A` (`investigator_id`),
  CONSTRAINT `FKEA00CC89893BB93A` FOREIGN KEY (`investigator_id`) REFERENCES `investigator` (`id`),
  CONSTRAINT `FKEA00CC89905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `proposal` */

DROP TABLE IF EXISTS `proposal`;

CREATE TABLE `proposal` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) default NULL,
  `lockedyn` varchar(255) default NULL,
  `notification_id` bigint(20) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  `proposal_documentation_path` varchar(255) default NULL,
  `proposal_submitteddate` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC4D26AF24B78CFDA` (`party_id`),
  KEY `FKC4D26AF2DDCA99FA` (`notification_id`),
  CONSTRAINT `FKC4D26AF2DDCA99FA` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FKC4D26AF24B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `proposal_application` */

DROP TABLE IF EXISTS `proposal_application`;

CREATE TABLE `proposal_application` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `application_submit_date` datetime NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `proposal_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9EFF3832B39989A` (`proposal_id`),
  CONSTRAINT `FK9EFF3832B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `proposal_application_ext` */

DROP TABLE IF EXISTS `proposal_application_ext`;

CREATE TABLE `proposal_application_ext` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `field` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `proposal_application_id` bigint(20) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE2213365AD7B4A35` (`proposal_application_id`),
  CONSTRAINT `FKE2213365AD7B4A35` FOREIGN KEY (`proposal_application_id`) REFERENCES `proposal_application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

/*Table structure for table `role_privileges` */

DROP TABLE IF EXISTS `role_privileges`;

CREATE TABLE `role_privileges` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `action_name` varchar(255) NOT NULL,
  `controller_name` varchar(255) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `description` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `party_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK797EEF4B2177BCA7` (`role_id`),
  KEY `FK797EEF4B4B78CFDA` (`party_id`),
  CONSTRAINT `FK797EEF4B4B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FK797EEF4B2177BCA7` FOREIGN KEY (`role_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `salary_component` */

DROP TABLE IF EXISTS `salary_component`;

CREATE TABLE `salary_component` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  KEY `FKF022E2E885837584` (`user_id`),
  KEY `FKF022E2E84B78CFDA` (`party_id`),
  CONSTRAINT `FKF022E2E84B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FKF022E2E885837584` FOREIGN KEY (`user_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`role_id`,`user_id`),
  KEY `FK143BF46A85837584` (`user_id`),
  KEY `FK143BF46A2177BCA7` (`role_id`),
  CONSTRAINT `FK143BF46A2177BCA7` FOREIGN KEY (`role_id`) REFERENCES `authority` (`id`),
  CONSTRAINT `FK143BF46A85837584` FOREIGN KEY (`user_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` int(11) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `email` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utilization` */

DROP TABLE IF EXISTS `utilization`;

CREATE TABLE `utilization` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `archive_yes_no` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `grant_period_id` bigint(20) NOT NULL,
  `grantee_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `submitted_date` datetime NOT NULL,
  `end_date` datetime default NULL,
  `start_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE9082E22378B5C44` (`grantee_id`),
  KEY `FKE9082E22161A26D3` (`grant_period_id`),
  KEY `FKE9082E22905C4B9A` (`projects_id`),
  CONSTRAINT `FKE9082E22161A26D3` FOREIGN KEY (`grant_period_id`) REFERENCES `grant_period` (`id`),
  CONSTRAINT `FKE9082E22378B5C44` FOREIGN KEY (`grantee_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FKE9082E22905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
