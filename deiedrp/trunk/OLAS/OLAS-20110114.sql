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
-- Create schema mhrd1
-- 

CREATE DATABASE IF NOT EXISTS mhrd1; 
USE mhrd1; 

-- 
-- Definition of table `ADDRESSES_MASTER` 
-- 

DROP TABLE IF EXISTS `ADDRESSES_MASTER`; 
CREATE TABLE `ADDRESSES_MASTER` ( 
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `ADDRESSES_MASTER` 
-- 

/*!40000 ALTER TABLE `ADDRESSES_MASTER` DISABLE KEYS */; 
/*!40000 ALTER TABLE `ADDRESSES_MASTER` ENABLE KEYS */; 


-- 
-- Definition of table `BOARD_MASTER` 
-- 

DROP TABLE IF EXISTS `BOARD_MASTER`; 
CREATE TABLE `BOARD_MASTER` ( 
  `board_id` char(2) DEFAULT NULL, 
  `board_name` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `BOARD_MASTER` 
-- 

/*!40000 ALTER TABLE `BOARD_MASTER` DISABLE KEYS */; 
INSERT INTO `BOARD_MASTER` (`board_id`,`board_name`) VALUES 
 ('01','I.C.S.E'), 
 ('02','C.B.S.E'), 
 ('03','U.P Board'), 
 ('04','Others'); 
/*!40000 ALTER TABLE `BOARD_MASTER` ENABLE KEYS */; 


-- 
-- Definition of table `CALL_CUT_OFF` 
-- 

DROP TABLE IF EXISTS `CALL_CUT_OFF`; 
CREATE TABLE `CALL_CUT_OFF` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `offered_by` char(8) DEFAULT NULL, 
  `cos_value` char(4) DEFAULT NULL, 
  `number_of_times` int(2) DEFAULT NULL, 
  `number_of_times_active` char(1) DEFAULT NULL, 
  `cos_seats` int(3) DEFAULT NULL, 
  `cut_off_number` decimal(5,2) DEFAULT NULL, 
  `cut_off_number_active` char(1) DEFAULT NULL, 
  `cut_off_percentage` decimal(5,2) DEFAULT NULL, 
  `cut_off_percentage_active` char(1) DEFAULT NULL, 
  `session_start_date` date DEFAULT NULL, 
  `session_end_date` date DEFAULT NULL, 
  `settings` char(1) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `CALL_CUT_OFF` 
-- 

/*!40000 ALTER TABLE `CALL_CUT_OFF` DISABLE KEYS */; 
/*!40000 ALTER TABLE `CALL_CUT_OFF` ENABLE KEYS */; 


-- 
-- Definition of table `CATEGORY` 
-- 

DROP TABLE IF EXISTS `CATEGORY`; 
CREATE TABLE `CATEGORY` ( 
  `code` char(2) DEFAULT NULL, 
  `CATEGORY` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `CATEGORY` 
-- 

/*!40000 ALTER TABLE `CATEGORY` DISABLE KEYS */; 
INSERT INTO `CATEGORY` (`code`,`CATEGORY`) VALUES 
 ('GN','General'), 
 ('BC','OBC'), 
 ('SC','SC'), 
 ('ST','ST'); 
/*!40000 ALTER TABLE `CATEGORY` ENABLE KEYS */; 


-- 
-- Definition of table `CITY_LOOK_UP` 
-- 

DROP TABLE IF EXISTS `CITY_LOOK_UP`; 
CREATE TABLE `CITY_LOOK_UP` ( 
  `city_name` varchar(45) DEFAULT NULL, 
  `near_city` varchar(45) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

--
-- Definition of table `COMPONENT_DESCRIPTION` 
-- 

DROP TABLE IF EXISTS `COMPONENT_DESCRIPTION`; 
CREATE TABLE `COMPONENT_DESCRIPTION` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `component_id` char(2) DEFAULT NULL, 
  `description` varchar(35) DEFAULT NULL, 
  `ug_pg` char(2) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 


-- 
-- Definition of table `COMPONENT_RULES` 
-- 

DROP TABLE IF EXISTS `COMPONENT_RULES`; 
CREATE TABLE `COMPONENT_RULES` ( 
  `rule_number` int(2) DEFAULT NULL, 
  `description` varchar(35) DEFAULT NULL, 
  `experssion` varchar(35) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `COMPONENT_RULES` 
-- 

/*!40000 ALTER TABLE `COMPONENT_RULES` DISABLE KEYS */; 
INSERT INTO `COMPONENT_RULES` (`rule_number`,`description`,`experssion`) VALUES 
 (0,'None',NULL); 
/*!40000 ALTER TABLE `COMPONENT_RULES` ENABLE KEYS */; 


-- 
-- Definition of table `CONTROL_REPORT` 
-- 

DROP TABLE IF EXISTS `CONTROL_REPORT`; 
CREATE TABLE `CONTROL_REPORT` ( 
  `entity_id` char(8) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `flag_status` char(1) DEFAULT NULL, 
  `user_id` char(18) DEFAULT NULL, 
  `start_date` date DEFAULT NULL, 
  `end_date` date DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `CONTROL_REPORT` 
-- 

/*!40000 ALTER TABLE `CONTROL_REPORT` DISABLE KEYS */; 
/*!40000 ALTER TABLE `CONTROL_REPORT` ENABLE KEYS */; 


-- 
-- Definition of table `ENTITY_EMPLOYEE` 
-- 

DROP TABLE IF EXISTS `ENTITY_EMPLOYEE`; 
CREATE TABLE `ENTITY_EMPLOYEE` ( 
  `ENTITY_EMPLOYEE_id` char(20) DEFAULT NULL, 
  `parent_entity` char(8) DEFAULT NULL, 
  `first_name` varchar(100) DEFAULT NULL, 
  `middle_name` varchar(100) DEFAULT NULL, 
  `last_name` varchar(100) DEFAULT NULL, 
  `primary_email_id` varchar(100) DEFAULT NULL, 
  `secondary_email_id` varchar(100) DEFAULT NULL, 
  `qualification` varchar(255) DEFAULT NULL, 
  `designation` char(3) DEFAULT NULL, 
  `date_of_birth` date DEFAULT NULL, 
  `date_of_joining` date DEFAULT NULL, 
  `status` char(3) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 



-- 
-- Definition of table `ENTITY_LOCATION` 
-- 

DROP TABLE IF EXISTS `ENTITY_LOCATION`; 
CREATE TABLE `ENTITY_LOCATION` ( 
  `location_id` char(3) DEFAULT NULL, 
  `location_name` char(50) DEFAULT NULL, 
  `location_address` char(100) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Definition of table `ENTITY_MASTER` 
-- 

DROP TABLE IF EXISTS `ENTITY_MASTER`; 
CREATE TABLE `ENTITY_MASTER` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `entity_type` char(3) DEFAULT NULL, 
  `entity_name` varchar(100) DEFAULT NULL, 
  `entity_address` varchar(255) DEFAULT NULL, 
  `entity_city` varchar(50) DEFAULT NULL, 
  `entity_state` varchar(50) DEFAULT NULL, 
  `entity_phone` varchar(50) DEFAULT NULL, 
  `fax` varchar(50) DEFAULT NULL, 
  `user_id` char(18) DEFAULT NULL, 
  `known_by` varchar(20) DEFAULT NULL, 
  `parent_entity_id` char(8) DEFAULT NULL, 
  `level` int(2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 



-- 
-- Definition of table `ENTITY_PROGRAM_BRANCH_DETAIL` 
-- 

DROP TABLE IF EXISTS `ENTITY_PROGRAM_BRANCH_DETAIL`; 
CREATE TABLE `ENTITY_PROGRAM_BRANCH_DETAIL` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch` char(3) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `ENTITY_PROGRAM_BRANCH_DETAIL` 
-- 

/*!40000 ALTER TABLE `ENTITY_PROGRAM_BRANCH_DETAIL` DISABLE KEYS */; 
/*!40000 ALTER TABLE `ENTITY_PROGRAM_BRANCH_DETAIL` ENABLE KEYS */; 


-- 
-- Definition of table `ADDRESSES_MASTER` 
-- 

DROP TABLE IF EXISTS `ENTITY_PROGRAM_MASTER`; 
CREATE TABLE `ENTITY_PROGRAM_MASTER` ( 
  `program_id` char(7) DEFAULT NULL, 
  `program_code` varchar(20) DEFAULT NULL, 
  `program_name` varchar(100) DEFAULT NULL, 
  `program_type` char(1) DEFAULT NULL, 
  `program_mode` char(1) DEFAULT NULL, 
  `branch` tinyint(1) DEFAULT NULL, 
  `specialization` tinyint(1) DEFAULT NULL, 
  `number_of_terms` int(2) DEFAULT NULL, 
  `total_credits` decimal(5,2) DEFAULT NULL, 
  `number_of_attempt_allowed` int(3) DEFAULT NULL, 
  `max_number_of_fail_subjects` int(3) DEFAULT NULL, 
  `fixed_duration` char(1) DEFAULT NULL, 
  `ug_pg` char(2) DEFAULT NULL, 
  `ten_codes` char(2) DEFAULT NULL, 
  `status` tinyint(1) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(50) DEFAULT NULL, 
  `modifier_id` varchar(50) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `ENTITY_PROGRAM_MASTER` 
-- 

/*!40000 ALTER TABLE `ENTITY_PROGRAM_MASTER` DISABLE KEYS */; 
/*!40000 ALTER TABLE `ENTITY_PROGRAM_MASTER` ENABLE KEYS */; 


-- 
-- Definition of table `ENTITY_PROGRAM_SPECIALIZATION_DETAIL` 
-- 

DROP TABLE IF EXISTS `ENTITY_PROGRAM_SPECIALIZATION_DETAIL`; 
CREATE TABLE `ENTITY_PROGRAM_SPECIALIZATION_DETAIL` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch` char(3) DEFAULT NULL, 
  `specialization` char(3) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `ENTITY_PROGRAM_SPECIALIZATION_DETAIL` 
-- 

/*!40000 ALTER TABLE `ENTITY_PROGRAM_SPECIALIZATION_DETAIL` DISABLE KEYS */; 
/*!40000 ALTER TABLE `ENTITY_PROGRAM_SPECIALIZATION_DETAIL` ENABLE KEYS */; 


-- 
-- Definition of table `ENTITY_PROGRAM_TERM_DETAILS` 
-- 

DROP TABLE IF EXISTS `ENTITY_PROGRAM_TERM_DETAILS`; 
CREATE TABLE `ENTITY_PROGRAM_TERM_DETAILS` ( 
  `entity_program_term_id` char(5) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `minimum_sgpa` decimal(4,2) DEFAULT NULL, 
  `term_name` varchar(100) DEFAULT NULL, 
  `number_of_teaching_days` int(5) DEFAULT NULL, 
  `duration_in_weeks` int(5) DEFAULT NULL, 
  `term_start_date` date DEFAULT NULL, 
  `term_end_date` date DEFAULT NULL, 
  `total_credits` decimal(10,3) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `ENTITY_PROGRAM_TERM_DETAILS` 
-- 

/*!40000 ALTER TABLE `ENTITY_PROGRAM_TERM_DETAILS` DISABLE KEYS */; 
/*!40000 ALTER TABLE `ENTITY_PROGRAM_TERM_DETAILS` ENABLE KEYS */; 


-- 
-- Definition of table `ENTITY_STUDENT` 
-- 

DROP TABLE IF EXISTS `ENTITY_STUDENT`; 
CREATE TABLE `ENTITY_STUDENT` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `student_id` char(18) DEFAULT NULL, 
  `enrollment_number` varchar(100) DEFAULT NULL, 
  `first_name` varchar(100) DEFAULT NULL, 
  `middle_name` varchar(100) DEFAULT NULL, 
  `last_name` varchar(100) DEFAULT NULL, 
  `primary_email_id` varchar(100) DEFAULT NULL, 
  `secondary_email_id` varchar(100) DEFAULT NULL, 
  `date_of_birth` date DEFAULT NULL, 
  `CATEGORY_code` char(2) DEFAULT NULL, 
  `gender` varchar(6) DEFAULT NULL, 
  `father_first_name` varchar(100) DEFAULT NULL, 
  `father_middle_name` varchar(100) DEFAULT NULL, 
  `father_last_name` varchar(100) DEFAULT NULL, 
  `mother_first_name` varchar(100) DEFAULT NULL, 
  `mother_middle_name` varchar(100) DEFAULT NULL, 
  `mother_last_name` varchar(100) DEFAULT NULL, 
  `registered_in_session` date DEFAULT NULL, 
  `status` tinyint(1) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `ENTITY_STUDENT` 
-- 

/*!40000 ALTER TABLE `ENTITY_STUDENT` DISABLE KEYS */; 
/*!40000 ALTER TABLE `ENTITY_STUDENT` ENABLE KEYS */; 


-- 
-- Definition of table `FINAL_MERIT_COMPONENTS` 
-- 

DROP TABLE IF EXISTS `FINAL_MERIT_COMPONENTS`; 
CREATE TABLE `FINAL_MERIT_COMPONENTS` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `offered_by` char(8) DEFAULT NULL, 
  `component_id` char(2) DEFAULT NULL, 
  `attendance_flag` char(1) DEFAULT NULL, 
  `description` varchar(20) DEFAULT NULL, 
  `total_marks` int(3) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `FINAL_MERIT_COMPONENTS` 
-- 

/*!40000 ALTER TABLE `FINAL_MERIT_COMPONENTS` DISABLE KEYS */; 
/*!40000 ALTER TABLE `FINAL_MERIT_COMPONENTS` ENABLE KEYS */; 


-- 
-- Definition of table `PAPER_CODES` 
-- 

DROP TABLE IF EXISTS `PAPER_CODES`; 
CREATE TABLE `PAPER_CODES` ( 
  `paper_description` varchar(35) DEFAULT NULL, 
  `paper_code` char(2) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 


-- 
-- Definition of table `PROGRAM_AGE_ELIGIBILITY` 
-- 

DROP TABLE IF EXISTS `PASSWORD_POLICY`; 
CREATE TABLE `PASSWORD_POLICY` ( 
  `minimum_length` int(2) DEFAULT NULL, 
  `maximum_length` int(3) DEFAULT NULL, 
  `expiry_period` int(3) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PASSWORD_POLICY` 
-- 

/*!40000 ALTER TABLE `PASSWORD_POLICY` DISABLE KEYS */; 
INSERT INTO `PASSWORD_POLICY` (`minimum_length`,`maximum_length`,`expiry_period`) VALUES 
 (4,20,30); 
/*!40000 ALTER TABLE `PASSWORD_POLICY` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_AGE_ELIGIBILITY` 
-- 

DROP TABLE IF EXISTS `PROGRAM_AGE_ELIGIBILITY`; 
CREATE TABLE `PROGRAM_AGE_ELIGIBILITY` ( 
  `entity_id` char(8) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `CATEGORY` char(2) DEFAULT NULL, 
  `age_limit` int(2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_AGE_ELIGIBILITY` 
-- 

/*!40000 ALTER TABLE `PROGRAM_AGE_ELIGIBILITY` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_AGE_ELIGIBILITY` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_BOARD` 
-- 

DROP TABLE IF EXISTS `PROGRAM_BOARD`; 
CREATE TABLE `PROGRAM_BOARD` ( 
  `program_id` char(7) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `component_id` char(2) DEFAULT NULL, 
  `board` char(2) DEFAULT NULL, 
  `normalization_factor` decimal(5,4) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_BOARD` 
-- 

/*!40000 ALTER TABLE `PROGRAM_BOARD` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_BOARD` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_BRANCHES` 
-- 

DROP TABLE IF EXISTS `PROGRAM_BRANCHES`; 
CREATE TABLE `PROGRAM_BRANCHES` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `branch_name` varchar(100) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 
 

-- 
-- Definition of table `PROGRAM_COMPONENT_ELIGIBILITY` 
-- 

DROP TABLE IF EXISTS `PROGRAM_COMPONENT_ELIGIBILITY`; 
CREATE TABLE `PROGRAM_COMPONENT_ELIGIBILITY` ( 
  `entity_id` char(8) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `component_id` char(2) DEFAULT NULL, 
  `CATEGORY` char(2) DEFAULT NULL, 
  `cut_off_percentage` float(5,2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_COMPONENT_ELIGIBILITY` 
-- 

/*!40000 ALTER TABLE `PROGRAM_COMPONENT_ELIGIBILITY` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_COMPONENT_ELIGIBILITY` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_COMPONENTS` 
-- 

DROP TABLE IF EXISTS `PROGRAM_COMPONENTS`; 
CREATE TABLE `PROGRAM_COMPONENTS` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `offered_by` char(8) DEFAULT NULL, 
  `component_id` char(7) DEFAULT NULL, 
  `type` char(1) DEFAULT NULL, 
  `component_weightage` decimal(5,2) DEFAULT NULL, 
  `weightage_flag` char(1) DEFAULT NULL, 
  `component_criteria_flag` char(1) DEFAULT NULL, 
  `SPECIAL_WEIGHTAGE_flag` char(1) DEFAULT NULL, 
  `sequence_number` int(2) DEFAULT NULL, 
  `board_flag` char(1) DEFAULT NULL, 
  `rule_number` int(2) DEFAULT NULL, 
  `ug_pg` char(2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_COMPONENTS` 
-- 

/*!40000 ALTER TABLE `PROGRAM_COMPONENTS` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_COMPONENTS` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_COS_CODE` 
-- 

DROP TABLE IF EXISTS `PROGRAM_COS_CODE`; 
CREATE TABLE `PROGRAM_COS_CODE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `cos_code` char(1) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 



-- 
-- Definition of table `PROGRAM_DEGREE` 
-- 

DROP TABLE IF EXISTS `PROGRAM_DEGREE`; 
CREATE TABLE `PROGRAM_DEGREE` ( 
  `program_id` char(7) DEFAULT NULL, 
  `degree_code` char(7) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_DEGREE` 
-- 

/*!40000 ALTER TABLE `PROGRAM_DEGREE` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_DEGREE` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_DURATION` 
-- 

DROP TABLE IF EXISTS `PROGRAM_DURATION`; 
CREATE TABLE `PROGRAM_DURATION` ( 
  `program_id` char(7) DEFAULT NULL, 
  `minimum_duration` int(3) DEFAULT NULL, 
  `maximum_duration` int(3) DEFAULT NULL, 
  `start_day_month` char(4) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_DURATION` 
-- 

/*!40000 ALTER TABLE `PROGRAM_DURATION` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_DURATION` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_OFFERED_BY` 
-- 

DROP TABLE IF EXISTS `PROGRAM_OFFERED_BY`; 
CREATE TABLE `PROGRAM_OFFERED_BY` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch` char(3) DEFAULT NULL, 
  `specialization` char(3) DEFAULT NULL, 
  `offered_by` char(8) DEFAULT NULL, 
  `mentor` char(20) DEFAULT NULL, 
  `seats` int(2) DEFAULT NULL, 
  `location_id` char(3) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_OFFERED_BY` 
-- 

/*!40000 ALTER TABLE `PROGRAM_OFFERED_BY` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_OFFERED_BY` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_PAPER_CODE` 
-- 

DROP TABLE IF EXISTS `PROGRAM_PAPER_CODE`; 
CREATE TABLE `PROGRAM_PAPER_CODE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `paper_code` char(2) DEFAULT NULL, 
  `grouping` char(2) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_PAPER_CODE` 
-- 

/*!40000 ALTER TABLE `PROGRAM_PAPER_CODE` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_PAPER_CODE` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_REGISTRATION` 
-- 

DROP TABLE IF EXISTS `PROGRAM_REGISTRATION`; 
CREATE TABLE `PROGRAM_REGISTRATION` ( 
  `program_id` char(7) DEFAULT NULL, 
  `entity_program_term_id` int(5) DEFAULT NULL, 
  `registration_start_date` datetime DEFAULT NULL, 
  `last_date` datetime DEFAULT NULL, 
  `add_drop_period` int(2) DEFAULT NULL, 
  `UNIVERSITY_session_start_date` datetime DEFAULT NULL, 
  `UNIVERSITY_session_end_date` datetime DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_REGISTRATION` 
-- 

/*!40000 ALTER TABLE `PROGRAM_REGISTRATION` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_REGISTRATION` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_REGISTRATION_NUMBER` 
-- 

DROP TABLE IF EXISTS `PROGRAM_REGISTRATION_NUMBER`; 
CREATE TABLE `PROGRAM_REGISTRATION_NUMBER` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `value` varchar(10) DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_REGISTRATION_NUMBER` 
-- 

/*!40000 ALTER TABLE `PROGRAM_REGISTRATION_NUMBER` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_REGISTRATION_NUMBER` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_SEAT_RESERVATION` 
-- 

DROP TABLE IF EXISTS `PROGRAM_SEAT_RESERVATION`; 
CREATE TABLE `PROGRAM_SEAT_RESERVATION` ( 
  `program_id` char(7) DEFAULT NULL, 
  `CATEGORY_code` char(2) DEFAULT NULL, 
  `percentage_seats` decimal(5,2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `PROGRAM_SEAT_RESERVATION` 
-- 

/*!40000 ALTER TABLE `PROGRAM_SEAT_RESERVATION` DISABLE KEYS */; 
/*!40000 ALTER TABLE `PROGRAM_SEAT_RESERVATION` ENABLE KEYS */; 


-- 
-- Definition of table `PROGRAM_SPECIALIZATIONS` 
-- 

DROP TABLE IF EXISTS `PROGRAM_SPECIALIZATIONS`; 
CREATE TABLE `PROGRAM_SPECIALIZATIONS` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `specialization_code` char(3) DEFAULT NULL, 
  `specialization_name` varchar(100) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Definition of table `SEAT_RESERVATION_CATEGORY` 
-- 

DROP TABLE IF EXISTS `SEAT_RESERVATION_CATEGORY`; 
CREATE TABLE `SEAT_RESERVATION_CATEGORY` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `CATEGORY_code` char(2) DEFAULT NULL, 
  `CATEGORY_name` varchar(25) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `SEAT_RESERVATION_CATEGORY` 
-- 

/*!40000 ALTER TABLE `SEAT_RESERVATION_CATEGORY` DISABLE KEYS */; 
INSERT INTO `SEAT_RESERVATION_CATEGORY` (`UNIVERSITY_id`,`CATEGORY_code`,`CATEGORY_name`) VALUES 
 ('0001','GN','General'), 
 ('0001','SC','SC'), 
 ('0001','ST','ST'), 
 ('0001','BC','OBC'); 
/*!40000 ALTER TABLE `SEAT_RESERVATION_CATEGORY` ENABLE KEYS */; 


-- 
-- Definition of table `SPECIAL_WEIGHTAGE` 
-- 

DROP TABLE IF EXISTS `SPECIAL_WEIGHTAGE`; 
CREATE TABLE `SPECIAL_WEIGHTAGE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `weightage_id` char(2) DEFAULT NULL, 
  `group_no` char(2) DEFAULT NULL, 
  `weightage_percentage` decimal(5,2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `SPECIAL_WEIGHTAGE` 
-- 
 
/*!40000 ALTER TABLE `SPECIAL_WEIGHTAGE` DISABLE KEYS */;  
/*!40000 ALTER TABLE `SPECIAL_WEIGHTAGE` ENABLE KEYS */; 


-- 
-- Definition of table `SPECIAL_WEIGHTAGE_GROUP` 
-- 

DROP TABLE IF EXISTS `SPECIAL_WEIGHTAGE_GROUP`; 
CREATE TABLE `SPECIAL_WEIGHTAGE_GROUP` ( 
  `grouping` char(2) DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 



-- 
-- Definition of table `STUDENT_CALL_LIST` 
-- 

DROP TABLE IF EXISTS `STUDENT_CALL_LIST`; 
CREATE TABLE `STUDENT_CALL_LIST` ( 
  `program_id` char(7) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `component_id` char(2) DEFAULT NULL, 
  `marks_percentage` float(4,2) DEFAULT NULL, 
  `marks_obtained` int(3) DEFAULT NULL, 
  `total_marks` int(3) DEFAULT NULL, 
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
  `form_number` char(8) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_CALL_LIST` 
-- 

/*!40000 ALTER TABLE `STUDENT_CALL_LIST` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_CALL_LIST` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_FINAL_MARKS` 
-- 

DROP TABLE IF EXISTS `STUDENT_FINAL_MARKS`; 
CREATE TABLE `STUDENT_FINAL_MARKS` ( 
  `program_id` char(7) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `evaluation_component` char(8) DEFAULT NULL, 
  `marks` int(3) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `attended` char(1) DEFAULT NULL, 
  `start_date` date DEFAULT NULL, 
  `end_date` date DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_FINAL_MARKS` 
-- 

/*!40000 ALTER TABLE `STUDENT_FINAL_MARKS` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_FINAL_MARKS` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_FINAL_MERIT_LIST` 
-- 

DROP TABLE IF EXISTS `STUDENT_FINAL_MERIT_LIST`; 
CREATE TABLE `STUDENT_FINAL_MERIT_LIST` ( 
  `program_id` char(7) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `branch_code` char(3) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `test_number` varchar(8) DEFAULT NULL, 
  `total_marks` int(3) DEFAULT NULL, 
  `cos_value` char(4) DEFAULT NULL, 
  `start_date` date DEFAULT NULL, 
  `end_date` date DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL, 
  `reason_code` varchar(50) DEFAULT NULL, 
  `status` varchar(12) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_FINAL_MERIT_LIST` 
-- 

/*!40000 ALTER TABLE `STUDENT_FINAL_MERIT_LIST` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_FINAL_MERIT_LIST` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_FIRST_DEGREE` 
-- 

DROP TABLE IF EXISTS `STUDENT_FIRST_DEGREE`; 
CREATE TABLE `STUDENT_FIRST_DEGREE` ( 
  `program_id` char(7) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `form_number` char(8) DEFAULT NULL, 
  `first_degree_code` char(7) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_FIRST_DEGREE` 
-- 

/*!40000 ALTER TABLE `STUDENT_FIRST_DEGREE` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_FIRST_DEGREE` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_MARKS` 
-- 

DROP TABLE IF EXISTS `STUDENT_MARKS`; 
CREATE TABLE `STUDENT_MARKS` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `roll_number` varchar(20) DEFAULT NULL, 
  `enrollment_number` varchar(100) DEFAULT NULL, 
  `course_code` char(8) DEFAULT NULL, 
  `attempt_number` int(2) DEFAULT NULL, 
  `attempt_type` char(3) DEFAULT NULL, 
  `semester_or_term` int(2) DEFAULT NULL, 
  `grouping` char(2) DEFAULT NULL, 
  `evaluation_id` char(3) DEFAULT NULL, 
  `marks` decimal(5,2) DEFAULT NULL, 
  `grades` char(2) DEFAULT NULL, 
  `pass_fail` char(2) DEFAULT NULL, 
  `status` char(3) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_MARKS` 
-- 

/*!40000 ALTER TABLE `STUDENT_MARKS` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_MARKS` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_MERIT_LIST` 
-- 

DROP TABLE IF EXISTS `STUDENT_MERIT_LIST`; 
CREATE TABLE `STUDENT_MERIT_LIST` ( 
  `program_id` char(7) DEFAULT NULL, 
  `entity_id` char(8) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `student_name` varchar(255) DEFAULT NULL, 
  `cos_value` char(4) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_MERIT_LIST` 
-- 

/*!40000 ALTER TABLE `STUDENT_MERIT_LIST` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_MERIT_LIST` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_PAPER` 
-- 

DROP TABLE IF EXISTS `STUDENT_PAPER`; 
CREATE TABLE `STUDENT_PAPER` ( 
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
  `grouping` varchar(2) DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_PAPER` 
-- 

/*!40000 ALTER TABLE `STUDENT_PAPER` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_PAPER` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_PROGRAM` 
-- 

DROP TABLE IF EXISTS `STUDENT_PROGRAM`; 
CREATE TABLE `STUDENT_PROGRAM` ( 
  `program_id` char(7) DEFAULT NULL, 
  `branch` char(3) DEFAULT NULL, 
  `specialization` char(3) DEFAULT NULL, 
  `cgpa` decimal(4,2) DEFAULT NULL, 
  `enrollment_number` varchar(100) DEFAULT NULL, 
  `roll_number` varchar(20) DEFAULT NULL, 
  `offered_by` char(8) DEFAULT NULL, 
  `program_type` char(1) DEFAULT NULL, 
  `program_mode` char(1) DEFAULT NULL, 
  `register_date` date DEFAULT NULL, 
  `program_completion_date` date DEFAULT NULL, 
  `current_term` int(5) DEFAULT NULL, 
  `status` char(3) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_PROGRAM` 
-- 

/*!40000 ALTER TABLE `STUDENT_PROGRAM` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_PROGRAM` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_REGISTRATION` 
-- 

DROP TABLE IF EXISTS `STUDENT_REGISTRATION`; 
CREATE TABLE `STUDENT_REGISTRATION` ( 
  `student_id` char(18) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `form_number` char(8) DEFAULT NULL, 
  `cos_value` char(4) DEFAULT NULL, 
  `start_date` date DEFAULT NULL, 
  `end_date` date DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_REGISTRATION` 
-- 

/*!40000 ALTER TABLE `STUDENT_REGISTRATION` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_REGISTRATION` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_SPECIAL_WEIGHTAGE` 
-- 

DROP TABLE IF EXISTS `STUDENT_SPECIAL_WEIGHTAGE`; 
CREATE TABLE `STUDENT_SPECIAL_WEIGHTAGE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `program_id` char(7) DEFAULT NULL, 
  `registration_number` char(12) DEFAULT NULL, 
  `weightage_id` char(2) DEFAULT NULL, 
  `start_date` date DEFAULT NULL, 
  `end_date` date DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_SPECIAL_WEIGHTAGE` 
-- 

/*!40000 ALTER TABLE `STUDENT_SPECIAL_WEIGHTAGE` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_SPECIAL_WEIGHTAGE` ENABLE KEYS */; 


-- 
-- Definition of table `STUDENT_TEST_NUMBER` 
-- 

DROP TABLE IF EXISTS `STUDENT_TEST_NUMBER`; 
CREATE TABLE `STUDENT_TEST_NUMBER` ( 
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
  `start_date` date DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `STUDENT_TEST_NUMBER` 
-- 

/*!40000 ALTER TABLE `STUDENT_TEST_NUMBER` DISABLE KEYS */; 
/*!40000 ALTER TABLE `STUDENT_TEST_NUMBER` ENABLE KEYS */; 


-- 
-- Definition of table `UNIVERSITY ` 
-- 

DROP TABLE IF EXISTS `SYSTEM_VALUES`; 
CREATE TABLE `SYSTEM_VALUES` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `code` char(6) DEFAULT NULL, 
  `value` varchar(255) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

/*!40000 ALTER TABLE `SYSTEM_VALUES` DISABLE KEYS */; 
INSERT INTO `SYSTEM_VALUES` (`UNIVERSITY_id`,`code`,`value`) VALUES 
 ('0001','PRGMID','000'),
 ('0001','ENTYID','0001'),
 ('0001','WGHTID','31'),
 ('0001','STUDID','000000038'),
 ('0002','PRGMID','002'),
 ('0002','ENTYID','0002'),
 ('0002','WGHTID','00'),
 ('0002','STUDID','000000038'),
 ('0001','DEFENT','N00010001');
/*!40000 ALTER TABLE `SYSTEM_VALUES` ENABLE KEYS */; 


-- 
-- Definition of table `UNIVERSITY` 
-- 

DROP TABLE IF EXISTS `UNIVERSITY`; 
CREATE TABLE `UNIVERSITY` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `start_date` date DEFAULT NULL, 
  `end_date` date DEFAULT NULL, 
  `current_status` tinyint(1) DEFAULT NULL, 
  `UNIVERSITY_name` varchar(100) DEFAULT NULL, 
  `UNIVERSITY_address` varchar(255) DEFAULT NULL, 
  `UNIVERSITY_city` varchar(50) DEFAULT NULL, 
  `UNIVERSITY_state` varchar(50) DEFAULT NULL, 
  `UNIVERSITY_pincode` varchar(10) DEFAULT NULL, 
  `phone_number` varchar(20) DEFAULT NULL, 
  `other_phone` varchar(20) DEFAULT NULL, 
  `fax` varchar(50) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `UNIVERSITY` 
-- 

/*!40000 ALTER TABLE `UNIVERSITY` DISABLE KEYS */; 
INSERT INTO `UNIVERSITY` (`UNIVERSITY_id`,`start_date`,`end_date`,`current_status`,`UNIVERSITY_name`,`UNIVERSITY_address`,`UNIVERSITY_city`,`UNIVERSITY_state`,`UNIVERSITY_pincode`,`phone_number`,`other_phone`,`fax`,`insert_time`,`modification_time`,`creator_id`,`modifier_id`) VALUES 
 ('0001','2011-01-14','',NULL,'Indian Institute of Technology Kanpur',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL); 
INSERT INTO `UNIVERSITY` (`UNIVERSITY_id`,`start_date`,`end_date`,`current_status`,`UNIVERSITY_name`,`UNIVERSITY_address`,`UNIVERSITY_city`,`UNIVERSITY_state`,`UNIVERSITY_pincode`,`phone_number`,`other_phone`,`fax`,`insert_time`,`modification_time`,`creator_id`,`modifier_id`) VALUES 
 ('0002','2011-01-14','',NULL,'Dayalbagh Educational Institute',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL); 
/*!40000 ALTER TABLE `UNIVERSITY` ENABLE KEYS */; 


-- 
-- Definition of table `UNIVERSITY_ENTITY_TYPE` 
-- 

DROP TABLE IF EXISTS `UNIVERSITY_ENTITY_TYPE`; 
CREATE TABLE `UNIVERSITY_ENTITY_TYPE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `entity_type` char(3) DEFAULT NULL, 
  `entity_description` varchar(255) DEFAULT NULL, 
  `level` int(2) DEFAULT NULL, 
  `insert_time` datetime DEFAULT NULL, 
  `modification_time` datetime DEFAULT NULL, 
  `creator_id` varchar(20) DEFAULT NULL, 
  `modifier_id` varchar(20) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `UNIVERSITY_ENTITY_TYPE` 
-- 

/*!40000 ALTER TABLE `UNIVERSITY_ENTITY_TYPE` DISABLE KEYS */; 
INSERT INTO `UNIVERSITY_ENTITY_TYPE` (`UNIVERSITY_id`,`entity_type`,`entity_description`,`level`,`insert_time`,`modification_time`,`creator_id`,`modifier_id`) VALUES 
 ('0001','DEP','Department',3,NULL,NULL,NULL,NULL), 
 ('0001','FAC','Faculty',2,NULL,NULL,NULL,NULL), 
 ('0001','INS','Institute',1,NULL,NULL,NULL,NULL); 
/*!40000 ALTER TABLE `UNIVERSITY_ENTITY_TYPE` ENABLE KEYS */; 


-- 
-- Definition of table `UNIVERSITY_PROGRAM_MODE` 
-- 

DROP TABLE IF EXISTS `UNIVERSITY_PROGRAM_MODE`; 
CREATE TABLE `UNIVERSITY_PROGRAM_MODE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `mode_name` varchar(50) DEFAULT NULL, 
  `program_mode` char(1) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 



-- 
-- Definition of table `UNIVERSITY_PROGRAM_TYPE` 
-- 

DROP TABLE IF EXISTS `UNIVERSITY_PROGRAM_TYPE`; 
CREATE TABLE `UNIVERSITY_PROGRAM_TYPE` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `type_name` varchar(50) DEFAULT NULL, 
  `program_type` char(1) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `UNIVERSITY_PROGRAM_TYPE` 
-- 

/*!40000 ALTER TABLE `UNIVERSITY_PROGRAM_TYPE` DISABLE KEYS */; 
INSERT INTO `UNIVERSITY_PROGRAM_TYPE` (`UNIVERSITY_id`,`type_name`,`program_type`) VALUES 
 ('0001','Part Time','p'), 
 ('0001','Full Time','f'); 
/*!40000 ALTER TABLE `UNIVERSITY_PROGRAM_TYPE` ENABLE KEYS */; 



--
-- Table structure for table `USER_FUNCTION_AUTHORITY`
--

DROP TABLE IF EXISTS `USER_FUNCTION_AUTHORITY`;
CREATE TABLE `USER_FUNCTION_AUTHORITY` (
  `function_user_id` char(18) default NULL,
  `menu_item_id` int(2) default NULL,
  `authority` char(4) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


INSERT INTO `USER_FUNCTION_AUTHORITY` (`function_user_id`,`menu_item_id`,`authority`) VALUES   ('E00010010000000001',1,'1111'),  ('E00010010000000001',2,'1111'),  ('E00010010000000001',3,'1111'),  ('E00010010000000001',4,'1111'),  ('E00010010000000001',5,'1111'),  ('E00010010000000001',6,'1111'),  ('E00010010000000001',7,'1111'),  ('E00010010000000001',8,'1111'),  ('E00010010000000001',9,'1111'),  ('E00010010000000001',10,'1111'),  ('E00010010000000001',11,'1111'),  ('E00010010000000001',12,'1111'),  ('E00010010000000001',13,'1111'),  ('E00010010000000001',14,'1111'),  ('E00010010000000001',15,'1111'),  ('E00010010000000001',16,'1111'),  ('E00010010000000001',17,'1111'),  ('E00010010000000001',18,'1111'),  ('E00010010000000001',19,'1111'),  ('E00010010000000001',20,'1111'),  ('E00010010000000001',21,'1111'),  ('E00010010000000001',22,'1111'),  ('E00010010000000001',23,'1111'),  ('E00010010000000001',24,'1111');
--
-- Table structure for table `USER_GROUP`
--

DROP TABLE IF EXISTS `USER_GROUP`;
CREATE TABLE `USER_GROUP` (
  `user_group_id` int(2) NOT NULL default '0',
  `menu_item_id` int(3) NOT NULL default '0',
  `authority` tinyint(1) NOT NULL,
  `creator_id` varchar(20) default NULL,
  `modifier_id` varchar(20) default NULL,
  `insert_time` datetime default NULL,
  `modification_time` datetime default NULL,
  `university_id` char(4) default NULL,
  PRIMARY KEY  (`user_group_id`,`menu_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `USER_GROUP` WRITE;
/*!40000 ALTER TABLE `USER_GROUP` DISABLE KEYS */;
INSERT INTO `USER_GROUP` VALUES (2,1,1,NULL,NULL,NULL,NULL,NULL),(2,2,1,NULL,NULL,NULL,NULL,NULL),(2,3,1,NULL,NULL,NULL,NULL,NULL),(2,4,1,NULL,NULL,NULL,NULL,NULL),(2,5,1,NULL,NULL,NULL,NULL,NULL),(2,6,1,NULL,NULL,NULL,NULL,NULL),(2,7,1,NULL,NULL,NULL,NULL,NULL),(2,8,1,NULL,NULL,NULL,NULL,NULL),(2,9,1,NULL,NULL,NULL,NULL,NULL),(2,10,1,NULL,NULL,NULL,NULL,NULL),(2,11,1,NULL,NULL,NULL,NULL,NULL),(2,12,1,NULL,NULL,NULL,NULL,NULL),(2,13,1,NULL,NULL,NULL,NULL,NULL),(2,14,1,NULL,NULL,NULL,NULL,NULL),(2,15,1,NULL,NULL,NULL,NULL,NULL),(2,16,1,NULL,NULL,NULL,NULL,NULL),(2,17,1,NULL,NULL,NULL,NULL,NULL),(2,18,1,NULL,NULL,NULL,NULL,NULL),(2,19,1,NULL,NULL,NULL,NULL,NULL),(2,20,1,NULL,NULL,NULL,NULL,NULL),(2,21,1,NULL,NULL,NULL,NULL,NULL),(2,22,1,NULL,NULL,NULL,NULL,NULL),(2,23,1,NULL,NULL,NULL,NULL,NULL),(2,24,1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `USER_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_INFO`
--

DROP TABLE IF EXISTS `USER_INFO`;
CREATE TABLE `USER_INFO` (
  `user_id` char(18) NOT NULL,
  `group_name` varchar(50) default NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `registered_time` datetime NOT NULL,
  `modified_time` datetime default NULL,
  `last_login_time` datetime default NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY  (`user_id`,`user_name`),
  KEY `group_name` (`group_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER_INFO`
--

LOCK TABLES `USER_INFO` WRITE;
/*!40000 ALTER TABLE `USER_INFO` DISABLE KEYS */;
INSERT INTO `USER_INFO` VALUES ('E00010010000000001','02','admin','d033e22ae348aeb5660fc2140aec35850c4da997','2010-09-04 14:03:02','2010-09-04 14:03:02','2010-09-04 14:03:02','active');
/*!40000 ALTER TABLE `USER_INFO` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `MENU_ITEMS_LIST`
--

DROP TABLE IF EXISTS `MENU_ITEMS_LIST`;
CREATE TABLE `MENU_ITEMS_LIST` (
  `menu_item_id` int(3) default NULL,
  `menu_item_name` varchar(255) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `MENU_ITEMS_LIST`
--

LOCK TABLES `MENU_ITEMS_LIST` WRITE;
/*!40000 ALTER TABLE `MENU_ITEMS_LIST` DISABLE KEYS */;
INSERT INTO `MENU_ITEMS_LIST` VALUES (1,'Program Components'),(2,'Program Pre Requiste Examinations(First Degree)'),(3,'Program Paper Codes'),(4,'Program COS'),(5,'Program Age Eligibility'),(6,'Program Component Eligibility'),(7,'Program Board'),(8,'Final Merit Components'),(9,'Special Weightage'),(10,'Reset Admission Cycle'),(11,'Apply for Admission'),(12,'Compute Application Marks'),(13,'Cut Off'),(14,'Enter Admission Test Marks'),(15,'Generate Test Numbers'),(16,'Call List without Test Number'),(17,'Call List with Test Number'),(18,'Final Merit List'),(19,'Entity Master'),(20,'Employee Master'),(21,'Program Master'),(22,'Entity Programs'),(23,'Program Term Details'),(24,'upload/download file for adding Test Marks');
/*!40000 ALTER TABLE `MENU_ITEMS_LIST` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `UNIVERSITY_ROLES`
--

DROP TABLE IF EXISTS `UNIVERSITY_ROLES`;
CREATE TABLE `UNIVERSITY_ROLES` (
  `university_id` char(4) default NULL,
  `role_id` int(2) default NULL,
  `description` varchar(255) default NULL,
  `insert_time` datetime default NULL,
  `modification_time` datetime default NULL,
  `creator_id` char(18) default NULL,
  `modifier_id` char(18) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UNIVERSITY_ROLES`
--

LOCK TABLES `UNIVERSITY_ROLES` WRITE;
/*!40000 ALTER TABLE `UNIVERSITY_ROLES` DISABLE KEYS */;
INSERT INTO `UNIVERSITY_ROLES` VALUES ('0001',1,'superadmin','2010-10-25 09:51:06',NULL,'E00010001000001',NULL),('0001',5,'student','2010-10-25 12:12:34',NULL,'E00010001000001',NULL),('0001',2,'instituteadmin','2010-10-27 10:58:35',NULL,'E00010001000001',NULL),('0001',20,'std','2010-10-25 13:02:20',NULL,'E00010001000001',NULL);
/*!40000 ALTER TABLE `UNIVERSITY_ROLES` ENABLE KEYS */;
UNLOCK TABLES;


-- 
-- Definition of table `WEIGHTAGE_DESCRIPTION` 
-- 
 
DROP TABLE IF EXISTS `WEIGHTAGE_DESCRIPTION`; 
CREATE TABLE `WEIGHTAGE_DESCRIPTION` ( 
  `UNIVERSITY_id` char(4) DEFAULT NULL, 
  `weightage_id` char(2) DEFAULT NULL, 
  `description` varchar(35) DEFAULT NULL 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

-- 
-- Dumping data for table `WEIGHTAGE_DESCRIPTION` 
-- 

/*!40000 ALTER TABLE `WEIGHTAGE_DESCRIPTION` DISABLE KEYS */; 
INSERT INTO `WEIGHTAGE_DESCRIPTION` (`UNIVERSITY_id`,`weightage_id`,`description`) VALUES 
 ('0001','HS','High School'), 
 ('0001','IN','Intermediate'), 
 ('0001','U1','Under Graduate-Degree 1'), 
 ('0001','SW','DEI Staff'); 
/*!40000 ALTER TABLE `WEIGHTAGE_DESCRIPTION` ENABLE KEYS */; 




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */; 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */; 
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */; 
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */; 
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */; 
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */; 
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */; 


CREATE DATABASE IF NOT EXISTS courseregistration;
USE courseregistration;

--
-- Definition of table `student_master`
--

DROP TABLE IF EXISTS `student_master`;
CREATE TABLE `student_master` (
  `university_code` char(4) NOT NULL,
  `student_id` char(18) NOT NULL,
  `enrollment_number` char(13) NOT NULL,
  `student_first_name` varchar(100) NOT NULL,
  `student_middle_name` varchar(100) DEFAULT NULL,
  `student_last_name` varchar(100) DEFAULT NULL,
  `primary_email_id` varchar(100) NOT NULL,
  `secondary_email_id` varchar(100) DEFAULT NULL,
  `date_of_birth` date NOT NULL,
  `category_code` char(2) NOT NULL,
  `gender` char(1) NOT NULL,
  `father_first_name` varchar(100) NOT NULL,
  `father_middle_name` varchar(100) DEFAULT NULL,
  `father_last_name` varchar(100) DEFAULT NULL,
  `mother_first_name` varchar(100) NOT NULL,
  `mother_middle_name` varchar(100) DEFAULT NULL,
  `mother_last_name` varchar(100) DEFAULT NULL,
  `registered_in_session` varchar(7) NOT NULL COMMENT '2006-07',
  `status` char(3) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) DEFAULT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `parent_entity` char(8) NOT NULL,
  PRIMARY KEY (`enrollment_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Definition of table `addresses_master`
--

DROP TABLE IF EXISTS `addresses_master`;
CREATE TABLE `addresses_master` (
  `user_type` char(3) NOT NULL,
  `address_key` char(3) NOT NULL,
  `address_line_one` varchar(100) NOT NULL,
  `address_line_two` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `office_phone` varchar(20) NOT NULL,
  `home_phone` varchar(20) DEFAULT NULL,
  `other_phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `insert_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `creator_id` varchar(20) NOT NULL,
  `modifier_id` varchar(20) DEFAULT NULL,
  `user_id` char(18) NOT NULL,
  PRIMARY KEY (`user_id`,`address_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



