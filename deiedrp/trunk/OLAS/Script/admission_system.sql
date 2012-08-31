-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema admission_system
--

CREATE DATABASE IF NOT EXISTS admission_system;
USE admission_system;

--
-- Definition of table `admission_addresses_master`
--

DROP TABLE IF EXISTS `admission_addresses_master`;
CREATE TABLE `admission_addresses_master` (
  `user_type` char(3) DEFAULT NULL,
  `user_id` char(18) DEFAULT NULL,
  `address_key` char(3) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `pincode` int(10) DEFAULT NULL,
  `office_phone` varchar(20) DEFAULT NULL,
  `home_phone` varchar(20) DEFAULT NULL,
  `other_phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission_addresses_master`
--

/*!40000 ALTER TABLE `admission_addresses_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `admission_addresses_master` ENABLE KEYS */;


--
-- Definition of table `applicant_account_info`
--

DROP TABLE IF EXISTS `applicant_account_info`;
CREATE TABLE `applicant_account_info` (
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email_id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `father_name` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(6) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `state` varchar(100) NOT NULL,
  `creator_id` varchar(45) NOT NULL,
  `insert_time` datetime NOT NULL,
  `modifier_id` varchar(45) DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  PRIMARY KEY (`email_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `applicant_account_info`
--

/*!40000 ALTER TABLE `applicant_account_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicant_account_info` ENABLE KEYS */;


--
-- Definition of table `applicant_program_registration`
--

DROP TABLE IF EXISTS `applicant_program_registration`;
CREATE TABLE `applicant_program_registration` (
  `user_name` varchar(100) NOT NULL,
  `registration_number` char(12) NOT NULL,
  `program_id` char(7) NOT NULL,
  PRIMARY KEY (`user_name`,`registration_number`,`program_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `applicant_program_registration`
--

/*!40000 ALTER TABLE `applicant_program_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicant_program_registration` ENABLE KEYS */;


--
-- Definition of table `board_master`
--

DROP TABLE IF EXISTS `board_master`;
CREATE TABLE `board_master` (
  `board_id` char(2) DEFAULT NULL,
  `board_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `board_master`
--

/*!40000 ALTER TABLE `board_master` DISABLE KEYS */;
INSERT INTO `board_master` (`board_id`,`board_name`) VALUES 
 ('01','I.C.S.E'),
 ('02','C.B.S.E'),
 ('03','U.P Board'),
 ('04','Others');
/*!40000 ALTER TABLE `board_master` ENABLE KEYS */;


--
-- Definition of table `call_cut_off`
--

DROP TABLE IF EXISTS `call_cut_off`;
CREATE TABLE `call_cut_off` (
  `program_id` char(7) NOT NULL DEFAULT '',
  `branch_code` char(3) NOT NULL DEFAULT '',
  `offered_by` char(8) NOT NULL DEFAULT '',
  `cos_value` char(4) NOT NULL DEFAULT '',
  `number_of_times` int(2) DEFAULT NULL,
  `number_of_times_active` char(1) DEFAULT NULL,
  `cos_seats` int(3) DEFAULT NULL,
  `cut_off_number` decimal(4,2) DEFAULT NULL,
  `cut_off_number_active` char(1) DEFAULT NULL,
  `cut_off_percentage` decimal(5,2) DEFAULT NULL,
  `cut_off_percentage_active` char(1) DEFAULT NULL,
  `session_start_date` date DEFAULT NULL,
  `session_end_date` date DEFAULT NULL,
  `settings` char(1) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `specialization_code` char(3) NOT NULL DEFAULT '',
  PRIMARY KEY (`offered_by`,`program_id`,`branch_code`,`specialization_code`,`cos_value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `call_cut_off`
--

/*!40000 ALTER TABLE `call_cut_off` DISABLE KEYS */;
/*!40000 ALTER TABLE `call_cut_off` ENABLE KEYS */;


--
-- Definition of table `city_look_up`
--

DROP TABLE IF EXISTS `city_look_up`;
CREATE TABLE `city_look_up` (
  `city_name` varchar(45) DEFAULT NULL,
  `near_city` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city_look_up`
--

/*!40000 ALTER TABLE `city_look_up` DISABLE KEYS */;
/*!40000 ALTER TABLE `city_look_up` ENABLE KEYS */;


--
-- Definition of table `component_description`
--

DROP TABLE IF EXISTS `component_description`;
CREATE TABLE `component_description` (
  `university_id` char(4) DEFAULT NULL,
  `component_id` char(2) DEFAULT NULL,
  `description` varchar(35) DEFAULT NULL,
  `ug_pg` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `component_description`
--

/*!40000 ALTER TABLE `component_description` DISABLE KEYS */;
INSERT INTO `component_description` (`university_id`,`component_id`,`description`,`ug_pg`) VALUES 
 ('0001','HS','High School','XX'),
 ('0001','IN','Intermediate','XX'),
 ('0001','MA','MAT','XX'),
 ('0001','GT','GATE','XX'),
 ('0001','XT','XAT','XX'),
 ('0001','CT','CAT','XX'),
 ('0001','JE','IIT-JEE','XX'),
 ('0001','MT','Mathematics','XX'),
 ('0001','PH','Physics','XX'),
 ('0001','CH','Chemistry','XX'),
 ('0001','BO','Botany','XX'),
 ('0001','ZO','Zoology','XX'),
 ('0001','PM','PCM','XX'),
 ('0001','AM','AIEEE-MATHS','XX'),
 ('0001','AP','AIEEE-PHYSICS + CHEMISTRY','XX'),
 ('0001','UG','Under Graduate','UG'),
 ('0001','PG','Post Graduate','PG'),
 ('0001','P1','Post Graduate-Degree 1','PG'),
 ('0001','U1','Under Graduate-Degree 1','UG'),
 ('0001','P2','Post Graduate-Degree 2','PG'),
 ('0001','U2','Under Graduate-Degree 2','UG');
/*!40000 ALTER TABLE `component_description` ENABLE KEYS */;


--
-- Definition of table `component_rules`
--

DROP TABLE IF EXISTS `component_rules`;
CREATE TABLE `component_rules` (
  `rule_number` int(2) DEFAULT NULL,
  `description` varchar(35) DEFAULT NULL,
  `experssion` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `component_rules`
--

/*!40000 ALTER TABLE `component_rules` DISABLE KEYS */;
INSERT INTO `component_rules` (`rule_number`,`description`,`experssion`) VALUES 
 (0,'None',NULL),
 (1,'Boss is always right',NULL),
 (2,'Respect your Elders',NULL),
 (3,'Help poor people',NULL);
/*!40000 ALTER TABLE `component_rules` ENABLE KEYS */;


--
-- Definition of table `control_report`
--

DROP TABLE IF EXISTS `control_report`;
CREATE TABLE `control_report` (
  `entity_id` char(8) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `flag_status` char(1) DEFAULT NULL,
  `user_id` char(18) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `control_report`
--

/*!40000 ALTER TABLE `control_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_report` ENABLE KEYS */;


--
-- Definition of table `entity_master`
--

DROP TABLE IF EXISTS `entity_master`;
CREATE TABLE `entity_master` (
  `university_id` char(4) NOT NULL,
  `entity_id` char(8) NOT NULL,
  `entity_type` char(3) NOT NULL,
  `entity_name` varchar(100) NOT NULL,
  `entity_address` varchar(255) NOT NULL,
  `entity_city` varchar(50) NOT NULL,
  `entity_state` varchar(50) NOT NULL,
  `entity_phone` varchar(50) NOT NULL,
  `entity_fax` varchar(50) DEFAULT NULL,
  `employee_code` char(6) DEFAULT NULL,
  `knownby` varchar(20) DEFAULT NULL,
  `parent_entity_id` char(8) DEFAULT NULL,
  `level` int(2) unsigned NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `location_id` char(3) DEFAULT NULL,
  `entity_code` char(3) NOT NULL,
  `entity_pincode` int(6) unsigned NOT NULL,
  PRIMARY KEY (`entity_id`),
  UNIQUE KEY `Index_3` (`university_id`,`entity_type`,`entity_id`),
  CONSTRAINT `FK_entity_master_1` FOREIGN KEY (`university_id`) REFERENCES `university_master` (`university_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entity_master`
--

/*!40000 ALTER TABLE `entity_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `entity_master` ENABLE KEYS */;


--
-- Definition of table `entity_student`
--

DROP TABLE IF EXISTS `entity_student`;
CREATE TABLE `entity_student` (
  `university_id` char(4) DEFAULT NULL,
  `student_id` char(18) DEFAULT NULL,
  `enrollment_number` varchar(100) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `middle_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `primary_email_id` varchar(100) DEFAULT NULL,
  `secondary_email_id` varchar(100) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `category_code` varchar(20) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `father_first_name` varchar(100) DEFAULT NULL,
  `father_middle_name` varchar(100) DEFAULT NULL,
  `father_last_name` varchar(100) DEFAULT NULL,
  `mother_first_name` varchar(100) DEFAULT NULL,
  `mother_middle_name` varchar(100) DEFAULT NULL,
  `mother_last_name` varchar(100) DEFAULT NULL,
  `registered_in_session` char(7) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `religion` varchar(50) DEFAULT NULL,
  `guardian_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entity_student`
--

/*!40000 ALTER TABLE `entity_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `entity_student` ENABLE KEYS */;


--
-- Definition of table `excel_codes`
--

DROP TABLE IF EXISTS `excel_codes`;
CREATE TABLE `excel_codes` (
  `university_id` char(4) NOT NULL,
  `program_id` char(7) NOT NULL,
  `code` char(3) NOT NULL,
  PRIMARY KEY (`program_id`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `excel_codes`
--

/*!40000 ALTER TABLE `excel_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `excel_codes` ENABLE KEYS */;


--
-- Definition of table `final_merit_components`
--

DROP TABLE IF EXISTS `final_merit_components`;
CREATE TABLE `final_merit_components` (
  `program_id` char(7) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `offered_by` char(8) DEFAULT NULL,
  `component_id` char(2) DEFAULT NULL,
  `attendance_flag` char(1) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `total_marks` int(3) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL,
  `weightage_percentage` float DEFAULT NULL,
  `academic_impact` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_merit_components`
--

/*!40000 ALTER TABLE `final_merit_components` DISABLE KEYS */;
/*!40000 ALTER TABLE `final_merit_components` ENABLE KEYS */;


--
-- Definition of table `menu_items_list`
--

DROP TABLE IF EXISTS `menu_items_list`;
CREATE TABLE `menu_items_list` (
  `menu_item_id` int(3) NOT NULL,
  `menu_item_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_items_list`
--

/*!40000 ALTER TABLE `menu_items_list` DISABLE KEYS */;
INSERT INTO `menu_items_list` (`menu_item_id`,`menu_item_name`) VALUES 
 (1,'Program Components'),
 (2,'Program Pre Requiste Examinations(First Degree)'),
 (3,'Program Paper Codes'),
 (4,'Program COS'),
 (5,'Program Age Eligibility'),
 (6,'Program Component Eligibility'),
 (7,'Program Board'),
 (8,'Final Merit Components'),
 (9,'Special Weightage'),
 (10,'Reset Admission Cycle'),
 (11,'Apply for Admission'),
 (12,'Compute Application Marks'),
 (13,'Cut Off'),
 (14,'Enter Admission Test Marks'),
 (15,'Generate Test Numbers'),
 (16,'Call List without Test Number'),
 (17,'Call List with Test Number'),
 (18,'Final Merit List'),
 (19,'Entity Master'),
 (20,'Employee Master'),
 (21,'Program Master'),
 (22,'Entity Programs'),
 (23,'Program Term Details'),
 (24,'upload/download file for adding Test Marks'),
 (25,'Program Application Details'),
 (26,'Define Excel Components'),
 (27,'Execute Database Backup'),
 (28,'Upload File for adding Component\'s Marks'),
 (29,'Final Merit List Process'),
 (30,'Import Students Marks'),
 (31,'Subject Setup'),
 (32,'Manage Subject');
/*!40000 ALTER TABLE `menu_items_list` ENABLE KEYS */;


--
-- Definition of table `paper_codes`
--

DROP TABLE IF EXISTS `paper_codes`;
CREATE TABLE `paper_codes` (
  `paper_description` varchar(35) DEFAULT NULL,
  `paper_code` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paper_codes`
--

/*!40000 ALTER TABLE `paper_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper_codes` ENABLE KEYS */;


--
-- Definition of table `password_policy`
--

DROP TABLE IF EXISTS `password_policy`;
CREATE TABLE `password_policy` (
  `minimum_length` int(2) DEFAULT NULL,
  `maximum_length` int(3) DEFAULT NULL,
  `expiry_period` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `password_policy`
--

/*!40000 ALTER TABLE `password_policy` DISABLE KEYS */;
INSERT INTO `password_policy` (`minimum_length`,`maximum_length`,`expiry_period`) VALUES 
 (6,20,30);
/*!40000 ALTER TABLE `password_policy` ENABLE KEYS */;


--
-- Definition of table `program_age_eligibility`
--

DROP TABLE IF EXISTS `program_age_eligibility`;
CREATE TABLE `program_age_eligibility` (
  `entity_id` char(8) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `category` char(2) DEFAULT NULL,
  `age_limit` int(2) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `modifier_id` varchar(255) DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_age_eligibility`
--

/*!40000 ALTER TABLE `program_age_eligibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_age_eligibility` ENABLE KEYS */;


--
-- Definition of table `program_board`
--

DROP TABLE IF EXISTS `program_board`;
CREATE TABLE `program_board` (
  `program_id` char(7) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `component_id` char(2) DEFAULT NULL,
  `board` char(2) DEFAULT NULL,
  `normalization_factor` decimal(5,4) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_board`
--

/*!40000 ALTER TABLE `program_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_board` ENABLE KEYS */;


--
-- Definition of table `program_branch_specialization`
--

DROP TABLE IF EXISTS `program_branch_specialization`;
CREATE TABLE `program_branch_specialization` (
  `program_id` char(7) NOT NULL,
  `branch_id` char(3) NOT NULL,
  `specialization_id` char(3) NOT NULL,
  `active_specialization_semester` char(3) DEFAULT NULL,
  `insert_time` date NOT NULL,
  `modification_time` date DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`program_id`,`branch_id`,`specialization_id`),
  KEY `FK_program_branch_specialization_1` (`program_id`),
  CONSTRAINT `FK_program_branch_specialization_1` FOREIGN KEY (`program_id`) REFERENCES `program_master` (`program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_branch_specialization`
--

/*!40000 ALTER TABLE `program_branch_specialization` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_branch_specialization` ENABLE KEYS */;


--
-- Definition of table `program_component_eligibility`
--

DROP TABLE IF EXISTS `program_component_eligibility`;
CREATE TABLE `program_component_eligibility` (
  `entity_id` char(8) NOT NULL,
  `program_id` char(7) NOT NULL,
  `branch_code` char(3) NOT NULL,
  `component_id` char(2) NOT NULL,
  `category` char(2) NOT NULL,
  `cut_off_percentage` float(5,2) DEFAULT NULL,
  `specialization_code` char(3) NOT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `modifier_id` varchar(255) DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_component_eligibility`
--

/*!40000 ALTER TABLE `program_component_eligibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_component_eligibility` ENABLE KEYS */;


--
-- Definition of table `program_components`
--

DROP TABLE IF EXISTS `program_components`;
CREATE TABLE `program_components` (
  `program_id` char(7) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `offered_by` char(8) DEFAULT NULL,
  `component_id` char(7) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `component_weightage` decimal(5,2) DEFAULT NULL,
  `weightage_flag` char(1) DEFAULT NULL,
  `component_criteria_flag` char(1) DEFAULT NULL,
  `special_weightage_flag` char(1) DEFAULT NULL,
  `sequence_number` int(2) DEFAULT NULL,
  `board_flag` char(1) DEFAULT NULL,
  `rule_number` int(2) DEFAULT NULL,
  `ug_pg` char(2) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_components`
--

/*!40000 ALTER TABLE `program_components` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_components` ENABLE KEYS */;


--
-- Definition of table `program_cos_code`
--

DROP TABLE IF EXISTS `program_cos_code`;
CREATE TABLE `program_cos_code` (
  `university_id` char(4) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `cos_code` char(1) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_cos_code`
--

/*!40000 ALTER TABLE `program_cos_code` DISABLE KEYS */;
INSERT INTO `program_cos_code` (`university_id`,`program_id`,`branch_code`,`cos_code`,`specialization_code`) VALUES 
 ('0001','0001001','001','A','001'),
 ('0001','0001001','001','A','XXX'),
 ('0001','0001002','001','B','001'),
 ('0001','0001003','001','B','001'),
 ('0001','0001004','001','B','001'),
 ('0001','0001005','001','B','001'),
 ('0001','0001006','001','B','001'),
 ('0001','0001008','001','B','001'),
 ('0001','0001009','019','A','001'),
 ('0001','0001009','013','B','001'),
 ('0001','0001009','004','C','001'),
 ('0001','0001009','014','H','001'),
 ('0001','0001009','015','M','001');
/*!40000 ALTER TABLE `program_cos_code` ENABLE KEYS */;


--
-- Definition of table `program_degree`
--

DROP TABLE IF EXISTS `program_degree`;
CREATE TABLE `program_degree` (
  `program_id` char(7) DEFAULT NULL,
  `degree_code` char(7) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_degree`
--

/*!40000 ALTER TABLE `program_degree` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_degree` ENABLE KEYS */;


--
-- Definition of table `program_document_list`
--

DROP TABLE IF EXISTS `program_document_list`;
CREATE TABLE `program_document_list` (
  `program_id` char(7) NOT NULL,
  `university_id` char(4) NOT NULL,
  `doc_id` decimal(10,0) NOT NULL,
  `insert_time` datetime NOT NULL,
  `creator_id` varchar(45) NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modifier_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`program_id`,`doc_id`,`university_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_document_list`
--

/*!40000 ALTER TABLE `program_document_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_document_list` ENABLE KEYS */;


--
-- Definition of table `program_form`
--

DROP TABLE IF EXISTS `program_form`;
CREATE TABLE `program_form` (
  `program_id` char(7) NOT NULL,
  `form_number` char(4) NOT NULL,
  `form_name` varchar(45) NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(45) NOT NULL,
  `modifier_id` varchar(45) DEFAULT NULL,
  `university_id` char(4) NOT NULL,
  `form_desc` varchar(100) DEFAULT NULL,
  `offered_by` char(8) DEFAULT NULL,
  `location_entity_id` char(8) DEFAULT NULL,
  PRIMARY KEY (`program_id`,`form_number`,`university_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_form`
--

/*!40000 ALTER TABLE `program_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_form` ENABLE KEYS */;


--
-- Definition of table `program_master`
--

DROP TABLE IF EXISTS `program_master`;
CREATE TABLE `program_master` (
  `program_id` char(7) NOT NULL,
  `program_code` char(3) NOT NULL,
  `program_name` varchar(100) NOT NULL,
  `program_type` char(1) NOT NULL,
  `program_mode` char(1) NOT NULL,
  `no_of_terms` int(2) unsigned NOT NULL,
  `total_credits` smallint(5) unsigned NOT NULL,
  `number_of_attempt_allowed` int(10) unsigned NOT NULL,
  `max_number_of_fail_subject` int(10) unsigned NOT NULL,
  `ug_pg` char(2) NOT NULL,
  `ten_codes` char(2) NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `print_aggregate` char(3) NOT NULL COMMENT 'TAP=theory and practical and SEM means semester aggregate',
  `branch_exists` tinyint(1) NOT NULL,
  `specialization_exists` tinyint(1) NOT NULL,
  `fixed_duration` char(1) NOT NULL,
  `program_description` varchar(100) NOT NULL,
  `dgpa` decimal(5,3) NOT NULL,
  `max_reg_semester` smallint(5) unsigned NOT NULL,
  `credit_required` int(3) unsigned NOT NULL,
  `fixed_or_variable_credit` char(1) NOT NULL COMMENT 'F=Fixed credit system, V=Variable credit system',
  PRIMARY KEY (`program_id`),
  UNIQUE KEY `Index_2` (`program_code`,`program_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_master`
--

/*!40000 ALTER TABLE `program_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_master` ENABLE KEYS */;


--
-- Definition of table `program_offered_by`
--

DROP TABLE IF EXISTS `program_offered_by`;
CREATE TABLE `program_offered_by` (
  `program_id` char(7) NOT NULL,
  `branch_id` char(3) NOT NULL,
  `specialization_id` char(3) NOT NULL,
  `offered_by` char(8) NOT NULL,
  `mentor` char(6) NOT NULL,
  `seats` int(10) unsigned NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `reg_or_distance` char(1) DEFAULT NULL,
  UNIQUE KEY `Index_1` (`program_id`,`branch_id`,`specialization_id`,`offered_by`),
  KEY `FK_program_offered_by_2` (`offered_by`),
  CONSTRAINT `FK_program_offered_by_1` FOREIGN KEY (`program_id`, `branch_id`, `specialization_id`) REFERENCES `program_branch_specialization` (`program_id`, `branch_id`, `specialization_id`),
  CONSTRAINT `FK_program_offered_by_2` FOREIGN KEY (`offered_by`) REFERENCES `entity_master` (`entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_offered_by`
--

/*!40000 ALTER TABLE `program_offered_by` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_offered_by` ENABLE KEYS */;


--
-- Definition of table `program_paper_code`
--

DROP TABLE IF EXISTS `program_paper_code`;
CREATE TABLE `program_paper_code` (
  `university_id` char(4) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `paper_code` char(2) DEFAULT NULL,
  `grouping` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_paper_code`
--

/*!40000 ALTER TABLE `program_paper_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_paper_code` ENABLE KEYS */;


--
-- Definition of table `program_registration_number`
--

DROP TABLE IF EXISTS `program_registration_number`;
CREATE TABLE `program_registration_number` (
  `university_id` char(4) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL,
  `value` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_registration_number`
--

/*!40000 ALTER TABLE `program_registration_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_registration_number` ENABLE KEYS */;


--
-- Definition of table `special_weightage`
--

DROP TABLE IF EXISTS `special_weightage`;
CREATE TABLE `special_weightage` (
  `university_id` char(4) DEFAULT NULL,
  `weightage_id` char(2) DEFAULT NULL,
  `group_no` char(2) DEFAULT NULL,
  `weightage_percentage` decimal(5,2) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `special_weightage`
--

/*!40000 ALTER TABLE `special_weightage` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_weightage` ENABLE KEYS */;


--
-- Definition of table `special_weightage_group`
--

DROP TABLE IF EXISTS `special_weightage_group`;
CREATE TABLE `special_weightage_group` (
  `grouping` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `special_weightage_group`
--

/*!40000 ALTER TABLE `special_weightage_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_weightage_group` ENABLE KEYS */;


--
-- Definition of table `student_call_list`
--

DROP TABLE IF EXISTS `student_call_list`;
CREATE TABLE `student_call_list` (
  `program_id` char(7) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `component_id` char(2) DEFAULT NULL,
  `marks_percentage` float(5,2) DEFAULT NULL,
  `marks_obtained` int(4) DEFAULT NULL,
  `total_marks` int(4) DEFAULT NULL,
  `computed_marks` decimal(5,2) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `actual_computed_marks` decimal(5,2) DEFAULT NULL,
  `board_id` char(2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_call_list`
--

/*!40000 ALTER TABLE `student_call_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_call_list` ENABLE KEYS */;


--
-- Definition of table `student_docs`
--

DROP TABLE IF EXISTS `student_docs`;
CREATE TABLE `student_docs` (
  `registration_number` char(14) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `path` blob NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(45) NOT NULL,
  `modifier_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`registration_number`,`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_docs`
--

/*!40000 ALTER TABLE `student_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_docs` ENABLE KEYS */;


--
-- Definition of table `student_fee`
--

DROP TABLE IF EXISTS `student_fee`;
CREATE TABLE `student_fee` (
  `registration_number` char(14) NOT NULL,
  `draft_number` varchar(15) NOT NULL,
  `bank` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `amount` int(4) NOT NULL,
  `creator_id` varchar(45) NOT NULL,
  `modifier_id` varchar(45) DEFAULT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  PRIMARY KEY (`registration_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_fee`
--

/*!40000 ALTER TABLE `student_fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_fee` ENABLE KEYS */;


--
-- Definition of table `student_final_marks`
--

DROP TABLE IF EXISTS `student_final_marks`;
CREATE TABLE `student_final_marks` (
  `program_id` char(7) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `registration_number` char(15) DEFAULT NULL,
  `evaluation_component` char(8) DEFAULT NULL,
  `marks` decimal(5,2) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `attended` char(1) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_final_marks`
--

/*!40000 ALTER TABLE `student_final_marks` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_final_marks` ENABLE KEYS */;


--
-- Definition of table `student_final_merit_list`
--

DROP TABLE IF EXISTS `student_final_merit_list`;
CREATE TABLE `student_final_merit_list` (
  `program_id` char(7) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `test_number` varchar(8) DEFAULT NULL,
  `total_marks` decimal(5,2) DEFAULT NULL,
  `cos_value` char(4) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `reason_code` varchar(50) DEFAULT NULL,
  `status` varchar(12) DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL,
  `sum_actual_computed_marks` decimal(5,2) DEFAULT NULL,
  `weighted_total_marks` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_final_merit_list`
--

/*!40000 ALTER TABLE `student_final_merit_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_final_merit_list` ENABLE KEYS */;


--
-- Definition of table `student_final_subcomponent_marks`
--

DROP TABLE IF EXISTS `student_final_subcomponent_marks`;
CREATE TABLE `student_final_subcomponent_marks` (
  `program_id` char(7) NOT NULL,
  `entity_id` char(8) NOT NULL,
  `registration_number` char(15) NOT NULL,
  `test_id` int(11) NOT NULL,
  `evaluation_component` char(8) NOT NULL,
  `sub_component` char(8) NOT NULL,
  `marks` decimal(5,2) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`program_id`,`entity_id`,`registration_number`,`test_id`,`evaluation_component`,`sub_component`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_final_subcomponent_marks`
--

/*!40000 ALTER TABLE `student_final_subcomponent_marks` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_final_subcomponent_marks` ENABLE KEYS */;


--
-- Definition of table `student_first_degree`
--

DROP TABLE IF EXISTS `student_first_degree`;
CREATE TABLE `student_first_degree` (
  `program_id` char(7) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `form_number` char(8) DEFAULT NULL,
  `first_degree_code` char(7) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_first_degree`
--

/*!40000 ALTER TABLE `student_first_degree` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_first_degree` ENABLE KEYS */;


--
-- Definition of table `student_paper`
--

DROP TABLE IF EXISTS `student_paper`;
CREATE TABLE `student_paper` (
  `program_id` char(7) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `form_number` char(8) DEFAULT NULL,
  `paper_code` char(2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `grouping` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_paper`
--

/*!40000 ALTER TABLE `student_paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_paper` ENABLE KEYS */;


--
-- Definition of table `student_registration`
--

DROP TABLE IF EXISTS `student_registration`;
CREATE TABLE `student_registration` (
  `student_id` char(18) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `form_number` char(8) DEFAULT NULL,
  `cos_value` char(4) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `exam_center_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_registration`
--

/*!40000 ALTER TABLE `student_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_registration` ENABLE KEYS */;


--
-- Definition of table `student_special_weightage`
--

DROP TABLE IF EXISTS `student_special_weightage`;
CREATE TABLE `student_special_weightage` (
  `university_id` char(4) DEFAULT NULL,
  `program_id` char(7) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `weightage_id` char(2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_special_weightage`
--

/*!40000 ALTER TABLE `student_special_weightage` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_special_weightage` ENABLE KEYS */;


--
-- Definition of table `student_test_number`
--

DROP TABLE IF EXISTS `student_test_number`;
CREATE TABLE `student_test_number` (
  `program_id` char(7) DEFAULT NULL,
  `entity_id` char(8) DEFAULT NULL,
  `branch_code` char(3) DEFAULT NULL,
  `registration_number` char(12) DEFAULT NULL,
  `test_number` varchar(8) DEFAULT NULL,
  `called` char(1) DEFAULT NULL,
  `cos_value` char(4) DEFAULT NULL,
  `sum_computed_marks` decimal(5,2) DEFAULT NULL,
  `sum_actual_computed_marks` decimal(5,2) DEFAULT NULL,
  `reason_code` varchar(50) DEFAULT NULL,
  `status` varchar(12) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `specialization_code` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_test_number`
--

/*!40000 ALTER TABLE `student_test_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_test_number` ENABLE KEYS */;


--
-- Definition of table `system_table_two`
--

DROP TABLE IF EXISTS `system_table_two`;
CREATE TABLE `system_table_two` (
  `university_code` char(4) NOT NULL,
  `component_code` varchar(6) NOT NULL,
  `component_description` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `verification_required` tinyint(1) DEFAULT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `group_code` varchar(6) NOT NULL DEFAULT '' COMMENT 'system table for group code',
  `dummy_flag_one` smallint(5) unsigned DEFAULT NULL,
  `dummy_flag_two` tinyint(1) unsigned DEFAULT NULL,
  `dummy_flag_three` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`component_code`,`group_code`),
  KEY `FK_system_table_two_1` (`university_code`),
  CONSTRAINT `FK_system_table_two_1` FOREIGN KEY (`university_code`) REFERENCES `university_master` (`university_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `system_table_two`
--

/*!40000 ALTER TABLE `system_table_two` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_table_two` ENABLE KEYS */;


--
-- Definition of table `system_values`
--

DROP TABLE IF EXISTS `system_values`;
CREATE TABLE `system_values` (
  `university_id` char(4) DEFAULT NULL,
  `code` char(6) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `system_values`
--

/*!40000 ALTER TABLE `system_values` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_values` ENABLE KEYS */;


--
-- Definition of table `university_master`
--

DROP TABLE IF EXISTS `university_master`;
CREATE TABLE `university_master` (
  `university_code` char(4) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `current_status` tinyint(1) NOT NULL COMMENT '1=Active, 0=Inactive',
  `university_name` varchar(100) NOT NULL,
  `university_address` varchar(255) NOT NULL,
  `university_city` varchar(50) NOT NULL,
  `university_pincode` int(6) NOT NULL,
  `university_phone_number` varchar(20) NOT NULL,
  `university_other_phone` varchar(20) DEFAULT NULL,
  `university_fax` varchar(20) DEFAULT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `nick_name` char(3) NOT NULL COMMENT 'DEI=Dayalbagh educational Institute',
  PRIMARY KEY (`university_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `university_master`
--

/*!40000 ALTER TABLE `university_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `university_master` ENABLE KEYS */;


--
-- Definition of table `university_seat_reservation`
--

DROP TABLE IF EXISTS `university_seat_reservation`;
CREATE TABLE `university_seat_reservation` (
  `university_code` char(4) NOT NULL,
  `category` char(2) NOT NULL,
  `percentage_seat` decimal(4,2) NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`university_code`,`category`),
  KEY `FK_university_seat_1` (`university_code`),
  CONSTRAINT `FK_university_seat_1` FOREIGN KEY (`university_code`) REFERENCES `university_master` (`university_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `university_seat_reservation`
--

/*!40000 ALTER TABLE `university_seat_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `university_seat_reservation` ENABLE KEYS */;


--
-- Definition of table `user_function_authority`
--

DROP TABLE IF EXISTS `user_function_authority`;
CREATE TABLE `user_function_authority` (
  `function_user_id` char(18) NOT NULL,
  `menu_item_id` char(3) NOT NULL,
  `authority` char(4) NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`function_user_id`,`menu_item_id`),
  KEY `FK_user_function_authority_1` (`menu_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_function_authority`
--

/*!40000 ALTER TABLE `user_function_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_function_authority` ENABLE KEYS */;


--
-- Definition of table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `university_code` char(4) NOT NULL,
  `user_group_id` char(3) NOT NULL,
  `menu_item_id` char(3) NOT NULL,
  `authority` tinyint(1) NOT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`university_code`,`menu_item_id`,`user_group_id`),
  KEY `FK_user_group_1` (`menu_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group`
--

/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` (`university_code`,`user_group_id`,`menu_item_id`,`authority`,`insert_time`,`modification_time`,`creator_id`,`modifier_id`) VALUES 
 ('0001','01','1',1,'2011-03-31 10:46:46',NULL,'ashish',NULL),
 ('0001','01','10',1,'2011-04-05 14:46:14',NULL,'ashish',NULL),
 ('0001','01','11',1,'2011-04-05 14:46:17',NULL,'ashish',NULL),
 ('0001','02','11',1,'2011-04-05 14:46:09',NULL,'ashish',NULL),
 ('0001','01','12',1,'2011-04-05 14:46:20',NULL,'ashish',NULL),
 ('0001','01','13',1,'2011-04-05 14:46:23',NULL,'ashish',NULL),
 ('0001','01','14',1,'2011-04-05 14:46:30',NULL,'ashish',NULL),
 ('0001','01','15',1,'2011-04-05 14:46:33',NULL,'ashish',NULL),
 ('0001','01','16',1,'2011-04-05 14:46:36',NULL,'ashish',NULL),
 ('0001','01','17',1,'2011-04-05 14:46:40',NULL,'ashish',NULL),
 ('0001','01','18',1,'2011-04-05 14:46:43',NULL,'ashish',NULL),
 ('0001','01','19',1,'2011-04-05 14:46:46',NULL,'ashish',NULL),
 ('0001','01','2',1,'2011-03-31 10:46:53',NULL,'ashish',NULL),
 ('0001','01','20',1,'2011-04-05 14:47:23',NULL,'ashish',NULL),
 ('0001','01','21',1,'2011-04-05 14:47:30',NULL,'ashish',NULL),
 ('0001','01','22',1,'2011-04-05 14:47:27',NULL,'ashish',NULL),
 ('0001','01','23',1,'2011-04-06 09:56:36',NULL,'ashish',NULL),
 ('0001','01','24',1,'2011-04-06 09:56:41',NULL,'ashish',NULL),
 ('0001','01','25',1,'2011-04-05 14:46:09',NULL,'ashish',NULL),
 ('0001','01','26',1,'2011-04-05 14:46:09',NULL,'ashish',NULL),
 ('0001','01','27',1,'2011-04-05 14:46:09',NULL,'ashish',NULL),
 ('0001','01','28',1,'2012-05-08 11:51:47',NULL,'devendra',NULL),
 ('0001','01','29',1,'2012-05-10 14:58:42',NULL,'devendra',NULL),
 ('0001','01','3',1,'2011-03-31 10:46:56',NULL,'ashish',NULL),
 ('0001','01','30',1,'2012-05-14 16:43:17',NULL,'devendra',NULL),
 ('0001','01','31',1,'2012-05-19 12:57:57',NULL,'devendra',NULL),
 ('0001','01','32',1,'2012-05-19 12:58:27',NULL,'devendra',NULL),
 ('0001','01','4',1,'2011-04-05 14:45:52',NULL,'ashish',NULL),
 ('0001','01','5',1,'2011-04-05 14:45:56',NULL,'ashish',NULL),
 ('0001','01','6',1,'2011-04-05 14:45:59',NULL,'ashish',NULL),
 ('0001','01','7',1,'2011-04-05 14:46:02',NULL,'ashish',NULL),
 ('0001','01','8',1,'2011-04-05 14:46:06',NULL,'ashish',NULL),
 ('0001','01','9',1,'2011-04-05 14:46:09',NULL,'ashish',NULL);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;


--
-- Definition of table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` char(18) NOT NULL,
  `user_name` char(14) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` datetime NOT NULL,
  `status` char(3) NOT NULL,
  `registered_timestamp` datetime NOT NULL,
  `modified_timestamp` datetime NOT NULL,
  `university_code` char(4) NOT NULL,
  `user_group_id` char(3) NOT NULL,
  PRIMARY KEY (`user_id`,`user_group_id`,`university_code`),
  KEY `FK_user_info` (`university_code`),
  KEY `FK_user_info_2` (`user_group_id`),
  CONSTRAINT `FK_user_info_1` FOREIGN KEY (`university_code`) REFERENCES `university_master` (`university_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


--
-- Definition of table `weightage_description`
--

DROP TABLE IF EXISTS `weightage_description`;
CREATE TABLE `weightage_description` (
  `university_id` char(4) DEFAULT NULL,
  `weightage_id` char(2) DEFAULT NULL,
  `description` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `weightage_description`
--

/*!40000 ALTER TABLE `weightage_description` DISABLE KEYS */;
INSERT INTO `weightage_description` (`university_id`,`weightage_id`,`description`) VALUES 
 ('0001','HS','High School'),
 ('0001','SW','DEI Ward'),
 ('0001','IN','Intermidiate'),
 ('0001','UG','Undergraduate'),
 ('0001','PG','Post Graduate');
/*!40000 ALTER TABLE `weightage_description` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;