/*

SQLyog Community v8.55 
MySQL - 5.0.19-nt : Database - gms_new

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



/*Table structure for table `approval_authority` */



DROP TABLE IF EXISTS `approval_authority`;



CREATE TABLE `approval_authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `approve_all` char(1) NOT NULL,
  `approve_level` varchar(255) default NULL,
  `approve_mandatory` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `default_yes_no` char(1) NOT NULL,
  `email` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  `remarks` varchar(255) default NULL,
  `view_all` char(1) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK12ADB0474B78CFDA` (`party_id`),
  CONSTRAINT `FK12ADB0474B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `approval_authority_detail` */



DROP TABLE IF EXISTS `approval_authority_detail`;



CREATE TABLE `approval_authority_detail` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `approval_authority_id` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `person_id` bigint(20) NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF0D9D749F1C66433` (`approval_authority_id`),
  KEY `FKF0D9D749C12321BA` (`person_id`),
  CONSTRAINT `FKF0D9D749C12321BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKF0D9D749F1C66433` FOREIGN KEY (`approval_authority_id`) REFERENCES `approval_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `attachment_type` */



DROP TABLE IF EXISTS `attachment_type`;



CREATE TABLE `attachment_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
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
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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



/*Table structure for table `award` */



DROP TABLE IF EXISTS `award`;



CREATE TABLE `award` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `awarded_date` datetime NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `proposal_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK58E7A5D2B39989A` (`proposal_id`),
  KEY `FK58E7A5D905C4B9A` (`projects_id`),
  CONSTRAINT `FK58E7A5D905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK58E7A5D2B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `budget_details` */



DROP TABLE IF EXISTS `budget_details`;



CREATE TABLE `budget_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `account_heads_id` bigint(20) default NULL,
  `active_yes_no` char(1) NOT NULL,
  `allocated_amount` double NOT NULL,
  `budget_master_id` bigint(20) default NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKDB96334837F07C9F` (`account_heads_id`),
  KEY `FKDB9633486AB07DC5` (`budget_master_id`),
  CONSTRAINT `FKDB9633486AB07DC5` FOREIGN KEY (`budget_master_id`) REFERENCES `budget_master` (`id`),
  CONSTRAINT `FKDB96334837F07C9F` FOREIGN KEY (`account_heads_id`) REFERENCES `account_heads` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `budget_master` */



DROP TABLE IF EXISTS `budget_master`;



CREATE TABLE `budget_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `budget_code` varchar(255) NOT NULL,
  `budget_description` varchar(255) NOT NULL,
  `financial_year_id` bigint(20) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `total_budget_amount` double NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3740B09CE6F02AED` (`financial_year_id`),
  KEY `FK3740B09C4B78CFDA` (`party_id`),
  CONSTRAINT `FK3740B09C4B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FK3740B09CE6F02AED` FOREIGN KEY (`financial_year_id`) REFERENCES `financial_year` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `budget_module_map` */



DROP TABLE IF EXISTS `budget_module_map`;



CREATE TABLE `budget_module_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `budget_master_id` bigint(20) default NULL,
  `module_type` varchar(255) default NULL,
  `module_type_id` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK95D93E436AB07DC5` (`budget_master_id`),
  CONSTRAINT `FK95D93E436AB07DC5` FOREIGN KEY (`budget_master_id`) REFERENCES `budget_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eligibility_check` */



DROP TABLE IF EXISTS `eligibility_check`;



CREATE TABLE `eligibility_check` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `eligibility_criteria_id` bigint(20) NOT NULL,
  `proposal_id` bigint(20) NOT NULL,
  `qualified_yes_no` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK28FD0DD62B39989A` (`proposal_id`),
  KEY `FK28FD0DD6D5BBCA35` (`eligibility_criteria_id`),
  CONSTRAINT `FK28FD0DD6D5BBCA35` FOREIGN KEY (`eligibility_criteria_id`) REFERENCES `eligibility_criteria` (`id`),
  CONSTRAINT `FK28FD0DD62B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eligibility_criteria` */



DROP TABLE IF EXISTS `eligibility_criteria`;



CREATE TABLE `eligibility_criteria` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `eligibility_criteria` varchar(255) NOT NULL,
  `mandatory_yes_no` char(1) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eligibility_status` */



DROP TABLE IF EXISTS `eligibility_status`;



CREATE TABLE `eligibility_status` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `eligibilitys_status` varchar(255) NOT NULL,
  `proposal_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1299C0E42B39989A` (`proposal_id`),
  CONSTRAINT `FK1299C0E42B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `employee_designation` */



DROP TABLE IF EXISTS `employee_designation`;



CREATE TABLE `employee_designation` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `designation` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eval_answer` */



DROP TABLE IF EXISTS `eval_answer`;



CREATE TABLE `eval_answer` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `eval_item_id` bigint(20) NOT NULL,
  `eval_scale_options_id` bigint(20) default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `person_id` bigint(20) NOT NULL,
  `proposal_id` bigint(20) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA63BE4C12B39989A` (`proposal_id`),
  KEY `FKA63BE4C11BBE2284` (`eval_scale_options_id`),
  KEY `FKA63BE4C1C12321BA` (`person_id`),
  KEY `FKA63BE4C1DF510B13` (`eval_item_id`),
  CONSTRAINT `FKA63BE4C1DF510B13` FOREIGN KEY (`eval_item_id`) REFERENCES `eval_item` (`id`),
  CONSTRAINT `FKA63BE4C11BBE2284` FOREIGN KEY (`eval_scale_options_id`) REFERENCES `eval_scale_options` (`id`),
  CONSTRAINT `FKA63BE4C12B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`),
  CONSTRAINT `FKA63BE4C1C12321BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eval_item` */



DROP TABLE IF EXISTS `eval_item`;



CREATE TABLE `eval_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `eval_scale_id` bigint(20) NOT NULL,
  `item` varchar(255) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK29BE84D68FF86661` (`eval_scale_id`),
  CONSTRAINT `FK29BE84D68FF86661` FOREIGN KEY (`eval_scale_id`) REFERENCES `eval_scale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eval_item_notification_map` */



DROP TABLE IF EXISTS `eval_item_notification_map`;



CREATE TABLE `eval_item_notification_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `eval_item_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `notification_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA3A95451DDCA99FA` (`notification_id`),
  KEY `FKA3A95451DF510B13` (`eval_item_id`),
  CONSTRAINT `FKA3A95451DF510B13` FOREIGN KEY (`eval_item_id`) REFERENCES `eval_item` (`id`),
  CONSTRAINT `FKA3A95451DDCA99FA` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eval_scale` */



DROP TABLE IF EXISTS `eval_scale`;



CREATE TABLE `eval_scale` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `party_id` bigint(20) NOT NULL,
  `scale_title` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE9737E74B78CFDA` (`party_id`),
  CONSTRAINT `FKE9737E74B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eval_scale_options` */



DROP TABLE IF EXISTS `eval_scale_options`;



CREATE TABLE `eval_scale_options` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `eval_scale_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `scale_option` varchar(255) NOT NULL,
  `scale_option_index` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK47468E468FF86661` (`eval_scale_id`),
  CONSTRAINT `FK47468E468FF86661` FOREIGN KEY (`eval_scale_id`) REFERENCES `eval_scale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `eval_score` */



DROP TABLE IF EXISTS `eval_score`;



CREATE TABLE `eval_score` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime default NULL,
  `no_of_reviewers` int(11) NOT NULL,
  `proposal_id` bigint(20) NOT NULL,
  `total_score` double NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE976D2F2B39989A` (`proposal_id`),
  CONSTRAINT `FKE976D2F2B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`)
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



/*Table structure for table `expense_request_entry` */



DROP TABLE IF EXISTS `expense_request_entry`;



CREATE TABLE `expense_request_entry` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_expense` datetime NOT NULL,
  `expense_amount` double NOT NULL,
  `expense_description` varchar(255) NOT NULL,
  `expense_request_code` varchar(255) NOT NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_allocation_split_id` bigint(20) default NULL,
  `investigator_id` bigint(20) NOT NULL,
  `invoice_amount` double NOT NULL,
  `invoice_date` datetime NOT NULL,
  `invoice_no` varchar(255) default NULL,
  `level` int(11) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `purchase_order_date` datetime NOT NULL,
  `purchase_order_no` varchar(255) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBDFD171BB8D959F3` (`grant_allocation_id`),
  KEY `FKBDFD171B905C4B9A` (`projects_id`),
  KEY `FKBDFD171B893BB93A` (`investigator_id`),
  KEY `FKBDFD171B33CE82BA` (`grant_allocation_split_id`),
  CONSTRAINT `FKBDFD171B33CE82BA` FOREIGN KEY (`grant_allocation_split_id`) REFERENCES `grant_allocation_split` (`id`),
  CONSTRAINT `FKBDFD171B893BB93A` FOREIGN KEY (`investigator_id`) REFERENCES `investigator` (`id`),
  CONSTRAINT `FKBDFD171B905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKBDFD171BB8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `financial_year` */



DROP TABLE IF EXISTS `financial_year`;



CREATE TABLE `financial_year` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `financial_end_date` datetime default NULL,
  `financial_period` varchar(255) default NULL,
  `financial_start_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `full_proposal` */



DROP TABLE IF EXISTS `full_proposal`;



CREATE TABLE `full_proposal` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `pre_proposal_id` bigint(20) NOT NULL,
  `pre_proposal_level` int(11) NOT NULL,
  `proposal_status` varchar(255) default NULL,
  `university_submissionid` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3613EAA2D3C5D481` (`pre_proposal_id`),
  CONSTRAINT `FK3613EAA2D3C5D481` FOREIGN KEY (`pre_proposal_id`) REFERENCES `pre_proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `full_proposal_detail` */



DROP TABLE IF EXISTS `full_proposal_detail`;



CREATE TABLE `full_proposal_detail` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `comments` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `file_name` varchar(255) NOT NULL,
  `full_proposal_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `proposal_submitted_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3FE9508EA3573299` (`full_proposal_id`),
  CONSTRAINT `FK3FE9508EA3573299` FOREIGN KEY (`full_proposal_id`) REFERENCES `full_proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `full_proposal_grant` */



DROP TABLE IF EXISTS `full_proposal_grant`;



CREATE TABLE `full_proposal_grant` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `applied_date` datetime NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `full_proposal_id` bigint(20) NOT NULL,
  `grant_agency` varchar(255) NOT NULL,
  `grant_name` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKAFAB0D3FA3573299` (`full_proposal_id`),
  CONSTRAINT `FKAFAB0D3FA3573299` FOREIGN KEY (`full_proposal_id`) REFERENCES `full_proposal` (`id`)
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
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
  `dd_no` varchar(255) default NULL,
  `description` varchar(255) NOT NULL,
  `expense_amount` double NOT NULL,
  `expense_request_code` varchar(255) default NULL,
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
  PRIMARY KEY  (`id`)
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
  `fund_transfer_id` bigint(20) default NULL,
  `grant_allocation_id` bigint(20) default NULL,
  `grant_allocation_split_id` bigint(20) default NULL,
  `mode_of_payment` varchar(255) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `reference_id` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC21DFE15E6A7AD65` (`fund_transfer_id`),
  KEY `FKC21DFE15B8D959F3` (`grant_allocation_id`),
  KEY `FKC21DFE15905C4B9A` (`projects_id`),
  KEY `FKC21DFE1533CE82BA` (`grant_allocation_split_id`),
  CONSTRAINT `FKC21DFE1533CE82BA` FOREIGN KEY (`grant_allocation_split_id`) REFERENCES `grant_allocation_split` (`id`),
  CONSTRAINT `FKC21DFE15905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKC21DFE15B8D959F3` FOREIGN KEY (`grant_allocation_id`) REFERENCES `grant_allocation` (`id`),
  CONSTRAINT `FKC21DFE15E6A7AD65` FOREIGN KEY (`fund_transfer_id`) REFERENCES `fund_transfer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `graphs` */



DROP TABLE IF EXISTS `graphs`;



CREATE TABLE `graphs` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `graph_type` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `investigator` */



DROP TABLE IF EXISTS `investigator`;



CREATE TABLE `investigator` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `address` varchar(255) NOT NULL,
  `department_id` bigint(20) default NULL,
  `designation` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  `user_sur_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`),
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
  `projects_id` bigint(20) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7F0A80AD905C4B9A` (`projects_id`),
  CONSTRAINT `FK7F0A80AD905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `menu` */



DROP TABLE IF EXISTS `menu`;



CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `lockedyn` varchar(255) NOT NULL,
  `menu_description` varchar(255) NOT NULL,
  `menu_name` varchar(255) NOT NULL,
  `menu_order` int(11) NOT NULL,
  `menu_path` varchar(255) default NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `parent_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `menu_role_map` */



DROP TABLE IF EXISTS `menu_role_map`;



CREATE TABLE `menu_role_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2C3BE0F32177BCA7` (`role_id`),
  KEY `FK2C3BE0F338C9F57A` (`menu_id`),
  CONSTRAINT `FK2C3BE0F338C9F57A` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK2C3BE0F32177BCA7` FOREIGN KEY (`role_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `notification` */



DROP TABLE IF EXISTS `notification`;



CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `application_form` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `description` varchar(255) default NULL,
  `eligibilitydocument` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `notification_code` varchar(255) NOT NULL,
  `notification_date` datetime NOT NULL,
  `notification_title` varchar(255) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  `proposal_submission_last_date` datetime NOT NULL,
  `public_yes_no` char(1) NOT NULL,
  `publish_yes_no` char(1) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `notification_code` (`notification_code`),
  KEY `FK237A88EB4B78CFDA` (`party_id`),
  CONSTRAINT `FK237A88EB4B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
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
  `user_sur_name` varchar(255) default NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `pre_proposal` */



DROP TABLE IF EXISTS `pre_proposal`;



CREATE TABLE `pre_proposal` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `party_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `pre_proposal_cancel_date` datetime default NULL,
  `pre_proposal_form` varchar(255) NOT NULL,
  `pre_proposal_level` int(11) NOT NULL,
  `pre_proposal_saved_date` datetime default NULL,
  `pre_proposal_status` varchar(255) default NULL,
  `project_title` varchar(255) default NULL,
  `proposal_category_id` bigint(20) NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD6C1310E2F0F585F` (`proposal_category_id`),
  KEY `FKD6C1310E4B78CFDA` (`party_id`),
  KEY `FKD6C1310EC12321BA` (`person_id`),
  CONSTRAINT `FKD6C1310EC12321BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKD6C1310E2F0F585F` FOREIGN KEY (`proposal_category_id`) REFERENCES `proposal_category` (`id`),
  CONSTRAINT `FKD6C1310E4B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `pre_proposal_copi` */



DROP TABLE IF EXISTS `pre_proposal_copi`;



CREATE TABLE `pre_proposal_copi` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `co_pi_commitments` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `investigator_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `pre_proposal_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF4A2AFF6D3C5D481` (`pre_proposal_id`),
  KEY `FKF4A2AFF6893BB93A` (`investigator_id`),
  CONSTRAINT `FKF4A2AFF6893BB93A` FOREIGN KEY (`investigator_id`) REFERENCES `investigator` (`id`),
  CONSTRAINT `FKF4A2AFF6D3C5D481` FOREIGN KEY (`pre_proposal_id`) REFERENCES `pre_proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `pre_proposal_detail` */



DROP TABLE IF EXISTS `pre_proposal_detail`;



CREATE TABLE `pre_proposal_detail` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `field` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `pre_proposal_id` bigint(20) NOT NULL,
  `pre_proposal_submitted_date` datetime default NULL,
  `value` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK57E038A2D3C5D481` (`pre_proposal_id`),
  CONSTRAINT `FK57E038A2D3C5D481` FOREIGN KEY (`pre_proposal_id`) REFERENCES `pre_proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `project_department_map` */



DROP TABLE IF EXISTS `project_department_map`;



CREATE TABLE `project_department_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
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
  `projects_id` bigint(20) NOT NULL,
  `relieving_date` datetime default NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `emp_no` (`emp_no`),
  KEY `FK483914905C4B9A` (`projects_id`),
  KEY `FK4839148CE4205D` (`employee_designation_id`),
  CONSTRAINT `FK4839148CE4205D` FOREIGN KEY (`employee_designation_id`) REFERENCES `employee_designation` (`id`),
  CONSTRAINT `FK483914905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
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
  `active_yes_no` char(1) NOT NULL,
  `investigator_id` bigint(20) NOT NULL,
  `projects_id` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL,
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
  `notification_id` bigint(20) default NULL,
  `parent_id` bigint(20) default NULL,
  `party_id` bigint(20) default NULL,
  `person_id` bigint(20) default NULL,
  `proposal_documentation_path` varchar(255) default NULL,
  `proposal_level` int(11) default NULL,
  `proposal_status` varchar(255) default NULL,
  `proposal_submitteddate` datetime NOT NULL,
  `proposal_type` varchar(255) NOT NULL,
  `proposal_version` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC4D26AF24B78CFDA` (`party_id`),
  KEY `FKC4D26AF2DDCA99FA` (`notification_id`),
  KEY `FKC4D26AF2C12321BA` (`person_id`),
  KEY `FKC4D26AF244137FE2` (`parent_id`),
  CONSTRAINT `FKC4D26AF244137FE2` FOREIGN KEY (`parent_id`) REFERENCES `proposal` (`id`),
  CONSTRAINT `FKC4D26AF24B78CFDA` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  CONSTRAINT `FKC4D26AF2C12321BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKC4D26AF2DDCA99FA` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `proposal_application` */



DROP TABLE IF EXISTS `proposal_application`;



CREATE TABLE `proposal_application` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `application_submit_date` datetime NOT NULL,
  `city` varchar(255) default NULL,
  `controller_id` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `designation` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) default NULL,
  `organisation` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `postal_address` varchar(255) default NULL,
  `project_title` varchar(255) default NULL,
  `proposal_id` bigint(20) NOT NULL,
  `proposal_category_id` bigint(20) default NULL,
  `state` varchar(255) default NULL,
  `zip_code` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9EFF3832B39989A` (`proposal_id`),
  KEY `FK9EFF3832F0F585F` (`proposal_category_id`),
  CONSTRAINT `FK9EFF3832F0F585F` FOREIGN KEY (`proposal_category_id`) REFERENCES `proposal_category` (`id`),
  CONSTRAINT `FK9EFF3832B39989A` FOREIGN KEY (`proposal_id`) REFERENCES `proposal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `proposal_application_ext` */



DROP TABLE IF EXISTS `proposal_application_ext`;



CREATE TABLE `proposal_application_ext` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `field` varchar(255) NOT NULL,
  `label` varchar(255) default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `order_no` int(11) default NULL,
  `page` int(11) default NULL,
  `proposal_application_id` bigint(20) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE2213365AD7B4A35` (`proposal_application_id`),
  CONSTRAINT `FKE2213365AD7B4A35` FOREIGN KEY (`proposal_application_id`) REFERENCES `proposal_application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `proposal_approval` */



DROP TABLE IF EXISTS `proposal_approval`;



CREATE TABLE `proposal_approval` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `approval_authority_detail_id` bigint(20) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `proposal_approval_authority_map_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK29BC5CF0E5B73262` (`approval_authority_detail_id`),
  KEY `FK29BC5CF0A1E745F7` (`proposal_approval_authority_map_id`),
  CONSTRAINT `FK29BC5CF0A1E745F7` FOREIGN KEY (`proposal_approval_authority_map_id`) REFERENCES `proposal_approval_authority_map` (`id`),
  CONSTRAINT `FK29BC5CF0E5B73262` FOREIGN KEY (`approval_authority_detail_id`) REFERENCES `approval_authority_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `proposal_approval_authority_map` */



DROP TABLE IF EXISTS `proposal_approval_authority_map`;



CREATE TABLE `proposal_approval_authority_map` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `approval_authority_id` bigint(20) NOT NULL,
  `approve_order` int(11) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `process_restart_order` int(11) NOT NULL,
  `proposal_id` int(11) NOT NULL,
  `proposal_type` varchar(255) NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFE624D51F1C66433` (`approval_authority_id`),
  CONSTRAINT `FKFE624D51F1C66433` FOREIGN KEY (`approval_authority_id`) REFERENCES `approval_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `proposal_approval_detail` */



DROP TABLE IF EXISTS `proposal_approval_detail`;



CREATE TABLE `proposal_approval_detail` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `approval_date` datetime NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `more_comments` varchar(255) default NULL,
  `proposal_approval_id` bigint(20) NOT NULL,
  `proposal_detail_id` double NOT NULL,
  `proposal_status` varchar(255) NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4BD84A8040C2163F` (`proposal_approval_id`),
  CONSTRAINT `FK4BD84A8040C2163F` FOREIGN KEY (`proposal_approval_id`) REFERENCES `proposal_approval` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*Table structure for table `proposal_category` */



DROP TABLE IF EXISTS `proposal_category`;



CREATE TABLE `proposal_category` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `created_by` varchar(255) default NULL,
  `created_date` datetime default NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `name` varchar(255) NOT NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
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
  `active_yes_no` char(1) NOT NULL,
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
  `end_date` datetime NOT NULL,
  `grantee_id` bigint(20) NOT NULL,
  `modified_by` varchar(255) default NULL,
  `modified_date` datetime default NULL,
  `projects_id` bigint(20) NOT NULL,
  `start_date` datetime NOT NULL,
  `submitted_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE9082E22378B5C44` (`grantee_id`),
  KEY `FKE9082E22905C4B9A` (`projects_id`),
  CONSTRAINT `FKE9082E22905C4B9A` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKE9082E22378B5C44` FOREIGN KEY (`grantee_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;





--------------------------------------------------------------------------------------------------------------------------------------------

/*Script for GMS release 1.8 - added on 2nd Feb 2011 */

ALTER TABLE grant_expense ADD COLUMN expense_request_code VARCHAR(255) NULL;

ALTER TABLE grant_period DROP KEY NAME, ADD INDEX NAME (NAME);

ALTER TABLE authority DROP KEY authority, ADD INDEX authority (authority);

ALTER TABLE investigator DROP KEY NAME, ADD INDEX NAME (NAME);

ALTER TABLE proposal_application_ext ADD COLUMN active_yes_no CHAR(1) DEFAULT '' NOT NULL ,ADD COLUMN label VARCHAR(255) NULL , ADD COLUMN order_no INT(11) NULL , ADD COLUMN page INT(11) NULL ;

UPDATE proposal_application_ext SET active_yes_no='Y';

ALTER TABLE proposal CHANGE notification_id notification_id BIGINT(20) NULL, CHANGE party_id party_id BIGINT(20) NULL;

ALTER TABLE approval_authority  ADD COLUMN default_yes_no CHAR(1) NOT NULL;

UPDATE approval_authority SET default_yes_no='Y';

INSERT INTO attachment_type (document_type,TYPE,active_yes_no) VALUES ('Proposal','DPR','Y'); 

INSERT INTO attachment_type (document_type,TYPE,active_yes_no) VALUES ('Proposal','CV','Y'); 


/*Script for GMS release 1.9 - added on 1nd March 2011 */

ALTER TABLE `notification` CHANGE `description` `description` TEXT NULL ;

ALTER TABLE proposal  ADD COLUMN proposal_version INT(11); 

ALTER TABLE proposal_application_ext  CHANGE `value` `value` TEXT NOT NULL;

ALTER TABLE `notification` DROP FOREIGN KEY  `FK237A88EB52E8209B` ;

ALTER TABLE `notification` DROP `project_id`;

ALTER TABLE `notification` ADD COLUMN `notification_title` VARCHAR(255) NULL AFTER `proposal_submission_last_date`, ADD COLUMN `public_yes_no` CHAR(1) NULL AFTER `notification_title`, ADD COLUMN `publish_yes_no` CHAR(1) NULL AFTER `public_yes_no`;

ALTER TABLE `notification` ADD COLUMN `party_id` BIGINT(20) NULL ;

ALTER TABLE `notification` ADD CONSTRAINT `FK_notification` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`);

/*forieghn key grantor_id removed from proposal*/

ALTER TABLE `proposal` DROP FOREIGN KEY  `FKC4D26AF2381E3021` ;

ALTER TABLE `proposal` DROP COLUMN `grantor_id`;

/*update the value of publish and public in notification*/

UPDATE notification SET public_yes_no='Y';

UPDATE notification SET publish_yes_no='Y';

/*New fields added in proposal application*/

ALTER TABLE `proposal_application`     

ADD COLUMN `name` VARCHAR(255) NULL,     

ADD COLUMN `designation` VARCHAR(255) NULL,

ADD COLUMN `organisation` VARCHAR(255) NULL,

ADD COLUMN `city` VARCHAR(255) NULL,

ADD COLUMN `email` VARCHAR(255) NULL,

ADD COLUMN `fax` VARCHAR(255) NULL,

ADD COLUMN `mobile` VARCHAR(255) NULL,

ADD COLUMN `phone` VARCHAR(255) NULL,

ADD COLUMN `postal_address` VARCHAR(255) NULL,

ADD COLUMN `state` VARCHAR(255) NULL,

ADD COLUMN `proposal_category_id` BIGINT(20) NULL,

ADD CONSTRAINT `FK_proposal_application` FOREIGN KEY (`proposal_category_id`) REFERENCES `proposal_category` (`id`);

ALTER TABLE expense_request_entry CHANGE `invoice_amount` `invoice_amount` DOUBLE NULL ,CHANGE `invoice_date` `invoice_date` DATETIME NULL , CHANGE `invoice_no` `invoice_no` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT '' NULL ;

UPDATE proposal_approval_authority_map SET active_yes_no='Y'; 

INSERT INTO `authority`(`active_yes_no`, `authority`,`description`)VALUES('Y','ROLE_PROPOSALADMIN','Proposal Admin');

/*Script for GMS release 2.0 - added on 25nd March 2011 */

ALTER TABLE eval_item DROP FOREIGN KEY  FK29BE84D6DDCA99FA ;
ALTER TABLE eval_item DROP notification_id;

/*Script for GMS release 2.1 - added on 19th April 2011 */

ALTER TABLE `notification` ADD COLUMN `amount` DOUBLE NULL;
UPDATE `notification` SET `amount`=0.00;

/*Script for GMS release 2.3 - added on 16th May 2011 */

ALTER TABLE proposal ADD COLUMN `person_id` INT(20) NULL AFTER `proposal_version`,     ADD COLUMN `proposal_status` VARCHAR(255) NULL AFTER `person_id`,     ADD COLUMN `proposal_level` INT(20) NULL AFTER `proposal_status`,     ADD COLUMN `parent_id` INT(20) NULL AFTER `proposal_level`,     ADD COLUMN `proposal_type` VARCHAR(255) NOT NULL AFTER `parent_id`;

ALTER TABLE proposal_application ADD COLUMN project_title VARCHAR(255) NULL;

ALTER TABLE proposal  ADD INDEX `FKC4D26AF244137FE2` (`parent_id`);

ALTER TABLE proposal ADD INDEX `FKC4D26AF2C12321BA` (`person_id`);

/*Removing old data from proposalapprovaldetail */

DELETE FROM proposal_approval_detail 
WHERE proposal_approval_id IN 
( SELECT pa.id FROM proposal_approval pa, proposal_approval_authority_map pm
WHERE pa.proposal_approval_authority_map_id = pm.id AND (pm.proposal_type = 'PreProposal' || pm.proposal_type = 
'FullProposal' ) );

/*Removing old data from proposalapproval*/

DELETE FROM proposal_approval 
WHERE proposal_approval_authority_map_id  IN 
( SELECT pm.id FROM  proposal_approval_authority_map pm
WHERE (pm.proposal_type = 'PreProposal' || pm.proposal_type ='FullProposal' ) );


/*Removing old data from proposalapprovalauthoritymap*/

DELETE FROM proposal_approval_authority_map
WHERE(proposal_type = 'PreProposal' || proposal_type = 'FullProposal' );

/*Script for GMS release 2.5 - added on 23th June 2011 */

ALTER TABLE proposal_application ADD COLUMN zip_code VARCHAR(255) NULL;
ALTER TABLE investigator ADD COLUMN user_sur_name VARCHAR(255) NULL;
ALTER TABLE grant_expense CHANGE dd_no dd_no VARCHAR(255)  DEFAULT NULL ;
DELETE FROM budget_module_map WHERE module_type = 'Proposal';


/* Script for new Table MenuRoleMap */

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (15, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (16, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (17, 0, 'Y', 'SuperAdmin', NOW(), 3, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (18, 0, 'Y', 'SuperAdmin', NOW(), 4, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (19, 0, 'Y', 'SuperAdmin', NOW(), 5, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (20, 0, 'Y', 'SuperAdmin', NOW(), 6, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (21, 0, 'Y', 'SuperAdmin', NOW(), 7, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (22, 0, 'Y', 'SuperAdmin', NOW(), 8, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (23, 0, 'Y', 'SuperAdmin', NOW(), 9, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (24, 0, 'Y', 'SuperAdmin', NOW(), 11, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (25, 0, 'Y', 'SuperAdmin', NOW(), 12, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (26, 0, 'Y', 'SuperAdmin', NOW(), 13, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (27, 0, 'Y', 'SuperAdmin', NOW(), 14, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (28, 0, 'Y', 'SuperAdmin', NOW(), 15, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (29, 0, 'Y', 'SuperAdmin', NOW(), 16, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (30, 0, 'Y', 'SuperAdmin', NOW(), 17, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (31, 0, 'N', 'SuperAdmin', NOW(), 18, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (33, 0, 'Y', 'SuperAdmin', NOW(), 20, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (34, 0, 'Y', 'SuperAdmin', NOW(), 21, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (35, 0, 'Y', 'SuperAdmin', NOW(), 22, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (36, 0, 'Y', 'SuperAdmin', NOW(), 23, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (37, 0, 'Y', 'SuperAdmin', NOW(), 24, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (38, 0, 'Y', 'SuperAdmin', NOW(), 25, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (39, 0, 'Y', 'SuperAdmin', NOW(), 26, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (40, 0, 'Y', 'SuperAdmin', NOW(), 32, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (41, 0, 'Y', 'SuperAdmin', NOW(), 33, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (42, 0, 'Y', 'SuperAdmin', NOW(), 34, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (43, 0, 'Y', 'SuperAdmin', NOW(), 35, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (44, 0, 'Y', 'SuperAdmin', NOW(), 36, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (45, 0, 'Y', 'SuperAdmin', NOW(), 37, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (46, 0, 'Y', 'SuperAdmin', NOW(), 38, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (47, 0, 'Y', 'SuperAdmin', NOW(), 39, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (48, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));
INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (49, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (50, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (51, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (52, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (53, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (54, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (55, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (56, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (57, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));






INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (58, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (59, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (60, 0, 'Y', 'SuperAdmin', NOW(), 4, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (61, 0, 'Y', 'SuperAdmin', NOW(), 5, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (62, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (63, 0, 'Y', 'SuperAdmin', NOW(), 48, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (64, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (65, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (66, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (67, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (68, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (69, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (70, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (71, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (72, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (73, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (74, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (75, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (76, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (77, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (78, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));




INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (79, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (80, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (81, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (82, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (83, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (84, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (85, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (86, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (87, 0, 'Y', 'SuperAdmin', NOW(), 61, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (88, 0, 'Y', 'SuperAdmin', NOW(), 62, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (89, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (90, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (91, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));



INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (92, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (93, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (94, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (95, 0, 'Y', 'SuperAdmin', NOW(), 53, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (96, 0, 'Y', 'SuperAdmin', NOW(), 54, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (97, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (98, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (99, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (100, 0, 'Y', 'SuperAdmin', NOW(), 58, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (101, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (102, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (103, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (104, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (105, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));




INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (106, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (107, 0, 'Y', 'SuperAdmin', NOW(), 49, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (108, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (109, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (110, 0, 'Y', 'SuperAdmin', NOW(), 32, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (111, 0, 'Y', 'SuperAdmin', NOW(), 33, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (112, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (113, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (114, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (115, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (116, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (117, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (118, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (119, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (120, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (121, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (122, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (123, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (124, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));





INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (125, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (126, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (127, 0, 'Y', 'SuperAdmin', NOW(), 6, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (128, 0, 'Y', 'SuperAdmin', NOW(), 7, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (129, 0, 'Y', 'SuperAdmin', NOW(), 63, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (130, 0, 'Y', 'SuperAdmin', NOW(), 64, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (131, 0, 'Y', 'SuperAdmin', NOW(), 65, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (132, 0, 'Y', 'SuperAdmin', NOW(), 66, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (133, 0, 'Y', 'SuperAdmin', NOW(), 67, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (134, 0, 'Y', 'SuperAdmin', NOW(), 32, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (135, 0, 'Y', 'SuperAdmin', NOW(), 33, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (136, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (137, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (138, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (139, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (140, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (141, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (142, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (143, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (144, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (145, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (146, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (147, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (148, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (149, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (150, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (151, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));



/*Script for GMS release 2.8 - added on 2nd Feb 2012 */

ALTER TABLE grant_expense  ADD COLUMN `fund_advance_code` VARCHAR(255) NULL AFTER `expense_request_code`; 

ALTER TABLE fund_advance ADD COLUMN `status` VARCHAR(255) NULL AFTER `modified_date`;

ALTER TABLE person  ADD COLUMN `active_yes_no` CHAR(1) NOT NULL AFTER `username`;
UPDATE person SET active_yes_no='Y';

ALTER TABLE person DROP KEY `username`;

ALTER TABLE person ADD COLUMN `user_designation` VARCHAR(255) NULL AFTER `active_yes_no`;    
ALTER TABLE person ADD COLUMN `ph_number` VARCHAR(255) NULL AFTER `user_designation`;


