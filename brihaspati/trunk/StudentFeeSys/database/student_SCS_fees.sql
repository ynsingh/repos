# SQL Manager 2007 Lite for MySQL 4.3.2.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : student_fees


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `student_fees`;

CREATE DATABASE `student_fees`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `student_fees`;

#
# Structure for the `admin_records` table : 
#

CREATE TABLE `admin_records` (
  `seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `admin_pass` VARCHAR(40) COLLATE latin1_swedish_ci DEFAULT NULL,
  `flag` TINYINT(4) DEFAULT NULL,
  `add_date` DATE DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `seq_id` (`seq_id`),
  UNIQUE KEY `seq_id_2` (`seq_id`)

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB';

#
# Structure for the `admin_email_config` table : 
#

CREATE TABLE `admin_email_config` (
  `admin_id` INTEGER(11) DEFAULT NULL,
  `admin_emailid` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `admin_config_pass` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `admin_email_status` TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`admin_emailid`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `admin_email_config_fk` FOREIGN KEY (`admin_id`) REFERENCES `admin_records` (`seq_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`admin_id`) REFER `student_fees/admin_records`(`seq_id`)';

#
# Structure for the `org_profile` table : 
#

CREATE TABLE `org_profile` (
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `org_name` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_city` VARCHAR(50) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_pincode` INTEGER(6) DEFAULT NULL,
  `org_state` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_ll` INTEGER(11) DEFAULT NULL,
  `org_countrycode` VARCHAR(5) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_regioncode` INTEGER(10) DEFAULT NULL,
  `org_institutedomain` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_toi` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_instituteweb` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_affiliation` VARCHAR(32) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_adminfn` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_adminln` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_admindesig` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_adminemailid` VARCHAR(31) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_master_password` VARCHAR(150) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_tagline` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL, 
  `org_email` VARCHAR(100) COLLATE latin1_swedish_ci  NOT NULL DEFAULT '',
  `org_web` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_phone` VARCHAR(35) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_address1` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_address2` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_logo` MEDIUMBLOB,
  `org_recovery_id` VARCHAR(200) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_tanno` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_reg_noti` INTEGER(11) DEFAULT NULL,
  `org_status` TINYINT(4) DEFAULT NULL,
  `org_reg_date` DATE DEFAULT NULL,
  `org_country_name` VARCHAR(50) COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `org_institutedomain` (`org_institutedomain`),
  UNIQUE KEY `org_name` (`org_name`),
  UNIQUE KEY `org_email` (`org_email`),
  UNIQUE KEY `org_web` (`org_web`),
  UNIQUE KEY `org_web_2` (`org_web`),
  UNIQUE KEY `org_name_2` (`org_name`),
  UNIQUE KEY `org_institutedomain_2` (`org_institutedomain`),
  KEY `org_id` (`org_id`),
  KEY `org_adminemailid` (`org_adminemailid`)

)ENGINE=InnoDB
CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB';

#
# Structure for the `org_department_type` table : 
#

CREATE TABLE `org_department_type` (
  `odt_seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `org_department_name` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_code` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `depatment_creater_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `depatment_modifier_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `depatment_creater_date` DATE DEFAULT NULL,
  `depatment_modifier_date` DATE DEFAULT NULL,
  PRIMARY KEY (`odt_seq_no`),
  KEY `org_code` (`org_code`),
  KEY `depatment_creater_id` (`depatment_creater_id`),
  KEY `depatment_modifier_id` (`depatment_modifier_id`),
  CONSTRAINT `org_department_type_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=23 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_code`) REFER `student_fees/org_profile`(`org_id`) ON';

#
# Structure for the `degree_type` table : 
#

CREATE TABLE `degree_type` (
  `seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT 'Degree Code',
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `degree_name` VARCHAR(100) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `degree_creator_id` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `degree_modifier_id` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `degree_created_date_time` DATETIME DEFAULT NULL,
  `degree_modifier_date_time` DATETIME DEFAULT NULL,
  `org_degree_dp_type` INTEGER(11) DEFAULT NULL,
  PRIMARY KEY (`seq_no`),
  KEY `org_id` (`org_id`),
  KEY `org_degree_dp_type` (`org_degree_dp_type`),
  KEY `degree_creator_id` (`degree_creator_id`),
  KEY `degree_modifier_id` (`degree_modifier_id`),
  CONSTRAINT `degree_type_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `degree_type_fk1` FOREIGN KEY (`org_degree_dp_type`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=31 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `exam_code_set_up` table : 
#

CREATE TABLE `exam_code_set_up` (
  `seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `semester_begning_date` DATE DEFAULT NULL,
  `semester_ending_date` DATE DEFAULT NULL,
  `semester_name` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `ecs_degree_type` INTEGER(11) DEFAULT NULL,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `ecs_department_type` INTEGER(11) DEFAULT NULL,
  PRIMARY KEY (`seq_no`),
  UNIQUE KEY `seq_no_2` (`seq_no`),
  UNIQUE KEY `seq_no` (`seq_no`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`ecs_degree_type`),
  KEY `ecs_department_type` (`ecs_department_type`),
  CONSTRAINT `exam_code_set_up_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exam_code_set_up_fk1` FOREIGN KEY (`ecs_degree_type`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exam_code_set_up_fk2` FOREIGN KEY (`ecs_department_type`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=7 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `admin_fee_admin_master` table : 
#

CREATE TABLE `admin_fee_admin_master` (
  `fee_head_code` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `fee_head_name` VARCHAR(60) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `fee_head_value` DOUBLE(15,2) NOT NULL,
  `degree_type` INTEGER(11) NOT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `branch_code` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `department_code` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `semester_code` INTEGER(11) DEFAULT NULL,
  `payable_status` TINYINT(4) DEFAULT NULL,
  `program_id` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`fee_head_code`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`degree_type`),
  KEY `semester_code` (`semester_code`),
  CONSTRAINT `admin_fee_admin_master_fk` FOREIGN KEY (`degree_type`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `admin_fee_admin_master_fk1` FOREIGN KEY (`semester_code`) REFERENCES `exam_code_set_up` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `admin_fee_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`degree_type`) REFER `student_fees/degree_type`(`seq_no`';

#
# Structure for the `admin_smtp_details` table : 
#

CREATE TABLE `admin_smtp_details` (
  `seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `smtp_name` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `smtp_port` INTEGER(11) DEFAULT NULL,
  `auth_emailid` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `auth_password` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `smtp_status` TINYINT(4) DEFAULT NULL,
  `smtp_host_name` VARCHAR(200) COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`smtp_name`),
  UNIQUE KEY `seq_id` (`seq_id`)

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB';

#
# Structure for the `bank_profile` table : 
#

CREATE TABLE `bank_profile` (
  `seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `bank_name` VARCHAR(60) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `bank_address` VARCHAR(60) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `bank_ifsc_code` VARCHAR(60) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `branch_name` VARCHAR(60) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`bank_ifsc_code`),
  UNIQUE KEY `seq_no` (`seq_no`),
  UNIQUE KEY `seq_no_2` (`seq_no`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `bank_profile_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `branch_master` table : 
#

CREATE TABLE `branch_master` (
  `bm_seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `branch_name` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `bm_department_code` INTEGER(11) DEFAULT NULL,
  `bm_degree_code` INTEGER(11) DEFAULT NULL,
  `bm_org_code` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `bm_degree_creater_id` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `bm_degree_modifier_id` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `bm_creater_date` DATE DEFAULT NULL,
  `bm_modifier_date` DATE DEFAULT NULL,
  PRIMARY KEY (`bm_seq_no`),
  KEY `bm_department_code` (`bm_department_code`),
  KEY `bm_degree_code` (`bm_degree_code`),
  KEY `bm_org_code` (`bm_org_code`),
  KEY `bm_degree_modifier_id` (`bm_degree_modifier_id`),
  KEY `bm_degree_creater_id` (`bm_degree_creater_id`),
  CONSTRAINT `branch_master_fk` FOREIGN KEY (`bm_department_code`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `branch_master_fk1` FOREIGN KEY (`bm_degree_code`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `branch_master_fk2` FOREIGN KEY (`bm_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=14 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`bm_department_code`) REFER `student_fees/org_department';

#
# Structure for the `challan_master` table : 
#

CREATE TABLE `challan_master` (
  `entry_no` VARCHAR(20) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `exam_code` VARCHAR(20) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `challan_no` INTEGER(11) NOT NULL,
  `challan_date` DATE NOT NULL,
  `amount` FLOAT(9,2) NOT NULL,
  `account_head` VARCHAR(50) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `balance` DOUBLE(15,2) NOT NULL,
  `degree_type` INTEGER(11) NOT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `cm_seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cm_seq_id`),
  UNIQUE KEY `challan_no` (`challan_no`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`degree_type`),
  CONSTRAINT `challan_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `college_pending_status` table : 
#

CREATE TABLE `college_pending_status` (
  `org_code` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_request_status` TINYINT(4) DEFAULT NULL,
  `org_pen_email` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`org_pen_email`),
  KEY `org_code` (`org_code`),
  KEY `org_email` (`org_pen_email`),
  CONSTRAINT `college_pending_status_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_code`) REFER `student_fees/org_profile`(`org_id`) O';

#
# Structure for the `course_group_master` table : 
#

CREATE TABLE `course_group_master` (
  `program_course_key` VARCHAR(22) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `course_code` VARCHAR(20) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `min_credit` INTEGER(11) NOT NULL,
  `max_credit` INTEGER(11) NOT NULL,
  `cgm_degree_type` INTEGER(11) DEFAULT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `course_group_seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`course_group_seq_id`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`cgm_degree_type`),
  CONSTRAINT `course_group_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_group_master_fk1` FOREIGN KEY (`cgm_degree_type`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `mode_of_payment` table : 
#

CREATE TABLE `mode_of_payment` (
  `seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `head_name` VARCHAR(39) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `head_creator_id` INTEGER(11) NOT NULL,
  `head_creator_date_time` DATETIME NOT NULL,
  `head_modifier_date_time` DATETIME NOT NULL,
  `mop_degree_type` INTEGER(11) DEFAULT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`seq_no`),
  UNIQUE KEY `head_name` (`head_name`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`mop_degree_type`),
  CONSTRAINT `mode_of_payment_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mode_of_payment_fk1` FOREIGN KEY (`mop_degree_type`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `mop_head_fields` table : 
#

CREATE TABLE `mop_head_fields` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `head_field_name` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `head_field_creator_id` INTEGER(11) NOT NULL,
  `head_field_creator_date_time` DATETIME NOT NULL,
  `head_field_modifier_date_time` DATETIME NOT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `mop_head_fields_fk` FOREIGN KEY (`id`) REFERENCES `mode_of_payment` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mop_head_fields_fk1` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`id`) REFER `student_fees/mode_of_payment`(`seq_no`) ON ';

#
# Structure for the `org_image_details` table : 
#

CREATE TABLE `org_image_details` (
  `img_seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `img_path` VARCHAR(500) COLLATE latin1_swedish_ci DEFAULT NULL,
  `img_name` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `img_profile_flag` TINYINT(4) DEFAULT NULL,
  `user_id` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `up_date` DATE DEFAULT NULL,
  PRIMARY KEY (`img_seq_no`),
  KEY `org_id` (`org_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `org_image_details_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `org_image_details_fk1` FOREIGN KEY (`user_id`) REFERENCES `org_profile` (`org_adminemailid`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=31 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `org_login_details` table : 
#

CREATE TABLE `org_login_details` (
  `admin_id` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `org_password` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `admin_id_2` (`admin_id`),
  KEY `admin_id` (`admin_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `org_login_details_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `org_logo_details` table : 
#

CREATE TABLE `org_logo_details` (
  `org_web` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `org_logo` MEDIUMBLOB,
  `logo_format` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `file_name` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `org_web_2` (`org_web`),
  UNIQUE KEY `org_web_3` (`org_web`),
  KEY `org_web` (`org_web`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `org_logo_details_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `semester_master` table : 
#

CREATE TABLE `semester_master` (
  `sem_seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `semester_name` VARCHAR(100) COLLATE latin1_swedish_ci DEFAULT NULL,
  `sem_exam_code` INTEGER(11) DEFAULT NULL,
  `sem_org_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `sem_creater_id` VARCHAR(300) COLLATE latin1_swedish_ci DEFAULT NULL,
  `sem_creater_date` DATE DEFAULT NULL,
  `depmnt_code` INTEGER(11) DEFAULT NULL,
  `degr_code` INTEGER(11) DEFAULT NULL,
  PRIMARY KEY (`sem_seq_no`),
  KEY `sem_exam_code` (`sem_exam_code`),
  KEY `sem_org_id` (`sem_org_id`),
  KEY `depmnt_code` (`depmnt_code`),
  KEY `degr_code` (`degr_code`),
  CONSTRAINT `semester_master_fk` FOREIGN KEY (`sem_exam_code`) REFERENCES `exam_code_set_up` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `semester_master_fk1` FOREIGN KEY (`sem_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `semester_master_fk2` FOREIGN KEY (`depmnt_code`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `semester_master_fk3` FOREIGN KEY (`degr_code`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=10 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`sem_exam_code`) REFER `student_fees/exam_code_set_up`(`seq_no`)';

#
# Structure for the `other_fee_head_master` table : 
#

CREATE TABLE `other_fee_head_master` (
  `fee_head_code` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `fee_head_name` VARCHAR(60) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `fee_head_value` DOUBLE(15,2) NOT NULL,
  `branch_code` INTEGER(11) DEFAULT NULL,
  `department_code` INTEGER(11) DEFAULT NULL,
  `program_id` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `semester_code` INTEGER(11) DEFAULT NULL,
  `payable_status` TINYINT(4) NOT NULL,
  `degree_code` INTEGER(11) NOT NULL,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`fee_head_code`),
  KEY `org_id` (`org_id`),
  KEY `semester_code` (`semester_code`),
  KEY `degree_type` (`degree_code`),
  KEY `branch_code` (`branch_code`),
  KEY `department_code` (`department_code`),
  CONSTRAINT `fee_head_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `other_fee_head_master_fk` FOREIGN KEY (`branch_code`) REFERENCES `branch_master` (`bm_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `other_fee_head_master_fk1` FOREIGN KEY (`department_code`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `other_fee_head_master_fk2` FOREIGN KEY (`degree_code`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `other_fee_head_master_fk3` FOREIGN KEY (`semester_code`) REFERENCES `semester_master` (`sem_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=4 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `scholarship_type` table : 
#

CREATE TABLE `scholarship_type` (
  `seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `degree_seq_no` INTEGER(11) NOT NULL,
  `scholarship_id` INTEGER(11) NOT NULL,
  `head_code_percentage` INTEGER(11) NOT NULL,
  `scholarship_name` VARCHAR(37) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `creator_id` INTEGER(11) NOT NULL,
  `modifier_id` INTEGER(11) NOT NULL,
  `created_date_time` DATETIME NOT NULL,
  `modifier_date_time` DATETIME NOT NULL,
  `degree_type` INTEGER(11) NOT NULL,
  `org_id` VARCHAR(35) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`seq_no`),
  KEY `degree_seq_no` (`degree_seq_no`),
  KEY `scholarship_id` (`scholarship_id`),
  KEY `degree_type` (`degree_type`),
  CONSTRAINT `scholaership_type_fk` FOREIGN KEY (`degree_seq_no`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `scholaership_type_fk1` FOREIGN KEY (`scholarship_id`) REFERENCES `other_fee_head_master` (`fee_head_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `scholarship_type_fk` FOREIGN KEY (`degree_type`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`degree_seq_no`) REFER `student_fees/degree_type`(`seq_n`) ON ';

#
# Structure for the `student_fees_master` table : 
#

CREATE TABLE `student_fees_master` (
  `reg_no` VARCHAR(20) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `entry_no` INTEGER(11) NOT NULL,
  `department_id` VARCHAR(20) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `branch_id` VARCHAR(28) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `program_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `fee_head_code` INTEGER(11) NOT NULL,
  `balance_value` DOUBLE(15,2) NOT NULL,
  `min_credit` INTEGER(11) NOT NULL,
  `max_credit` INTEGER(11) NOT NULL,
  `exam_code_setup` INTEGER(11) NOT NULL,
  `payment_status` TINYINT(4) NOT NULL,
  `degree_type` INTEGER(11) NOT NULL,
  `creator_id` INTEGER(11) NOT NULL,
  `modifier_id` INTEGER(11) NOT NULL,
  `insert_time` TIME NOT NULL,
  `modification_time` TIME NOT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `sfs_seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sfs_seq_id`),
  UNIQUE KEY `draft_challan_no` (`entry_no`),
  UNIQUE KEY `entry_no` (`entry_no`),
  UNIQUE KEY `branch_id` (`branch_id`),
  UNIQUE KEY `program_id` (`program_id`),
  UNIQUE KEY `fee_head_code` (`fee_head_code`),
  UNIQUE KEY `program_id_2` (`program_id`),
  UNIQUE KEY `fee_head_code_2` (`fee_head_code`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`degree_type`),
  KEY `exam_code_setup` (`exam_code_setup`),
  CONSTRAINT `student_fees_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `student_master` table : 
#

CREATE TABLE `student_master` (
  `entry_no` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `batch` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `program` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL,
  `student_opbal_amount` DOUBLE(15,2) DEFAULT NULL,
  `degree_code` INTEGER(11) DEFAULT NULL,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL,
  `department_id` INTEGER(11) DEFAULT NULL,
  `sm_seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `branch_id` INTEGER(11) DEFAULT NULL,
  `sem_code` INTEGER(11) DEFAULT NULL,
  `fee_head_code` INTEGER(11) DEFAULT NULL,
  PRIMARY KEY (`sm_seq_id`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`degree_code`),
  KEY `department_id` (`department_id`),
  KEY `branch_id` (`branch_id`),
  KEY `sem_code` (`sem_code`),
  KEY `fee_head_code` (`fee_head_code`),
  CONSTRAINT `student_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_master_fk1` FOREIGN KEY (`degree_code`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_master_fk2` FOREIGN KEY (`department_id`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_master_fk3` FOREIGN KEY (`branch_id`) REFERENCES `branch_master` (`bm_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_master_fk4` FOREIGN KEY (`sem_code`) REFERENCES `semester_master` (`sem_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_master_fk5` FOREIGN KEY (`fee_head_code`) REFERENCES `other_fee_head_master` (`fee_head_code`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=5 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `student_reg_master` table : 
#

CREATE TABLE `student_reg_master` (
  `entry_no` VARCHAR(100) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `reg_no` VARCHAR(15) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `form_no` INTEGER(11) NOT NULL,
  `degree_type` INTEGER(11) NOT NULL,
  `org_id` VARCHAR(400) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `srm_seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT 'Student Registration Seq No.',
  `department_code` INTEGER(11) DEFAULT NULL,
  `sem_code` INTEGER(11) DEFAULT NULL,
  `branch_code` INTEGER(11) DEFAULT NULL,
  PRIMARY KEY (`srm_seq_id`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`degree_type`),
  KEY `department_code` (`department_code`),
  KEY `sem_code` (`sem_code`),
  KEY `branch_code` (`branch_code`),
  CONSTRAINT `student_reg_master_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_reg_master_fk1` FOREIGN KEY (`degree_type`) REFERENCES `degree_type` (`seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_reg_master_fk2` FOREIGN KEY (`department_code`) REFERENCES `org_department_type` (`odt_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_reg_master_fk3` FOREIGN KEY (`sem_code`) REFERENCES `semester_master` (`sem_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_reg_master_fk4` FOREIGN KEY (`branch_code`) REFERENCES `branch_master` (`bm_seq_no`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=6 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `temp_final_report` table : 
#

CREATE TABLE `temp_final_report` (
  `seq_no` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `challan_no` INTEGER(11) NOT NULL,
  `challan_date` DATE NOT NULL,
  `student_opbal_amt` DOUBLE(15,2) NOT NULL,
  `balance` DOUBLE(15,2) NOT NULL,
  `degree_type` INTEGER(11) NOT NULL,
  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`seq_no`),
  KEY `org_id` (`org_id`),
  KEY `degree_type` (`degree_type`),
  CONSTRAINT `temp_final_report_fk` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB; (`org_id`) REFER `student_fees/org_profile`(`org_id`) ON ';

#
# Structure for the `user_master` table : 
#

#CREATE TABLE `user_master` (
#  `user_id` VARCHAR(100) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
#  `password` VARCHAR(50) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
#  `create_time` TIME DEFAULT NULL,
#  `edit_time` TIME NOT NULL,
#  `date` DATE DEFAULT NULL,
#  `modifier_id` INTEGER(11) NOT NULL,
#  `login_time` TIME NOT NULL,
#  `logout_time` TIME NOT NULL,
#  `org_id` VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
#  `um_seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
#  PRIMARY KEY (`um_seq_id`),
#  KEY `org_id` (`org_id`)
#
#)ENGINE=InnoDB
#AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
#COMMENT='InnoDB free: 11264 kB';

#
# Structure for the `user_master` table :
#

DROP TABLE IF EXISTS `user_master`;

CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(100) NOT NULL default '',
  `user_pass` varchar(150) NOT NULL,
  `user_org_id` varchar(400) NOT NULL,
  `user_grp_id` int(11) NOT NULL,
  `create_time` TIME DEFAULT NULL,
  `edit_time` TIME DEFAULT NULL,
  `date` DATE DEFAULT NULL,
  `modifier_id` INTEGER(11) NOT NULL,
  `login_time` TIME NOT NULL,
  `logout_time` TIME NOT NULL,
  `user_profile_id` int(11) NOT NULL default '0',
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_org_id` (`user_org_id`),
  KEY `user_id` (`user_id`),
  KEY `user_grp_id` (`user_grp_id`),
  KEY `user_name_2` (`user_name`),
  CONSTRAINT `user_master_fk` FOREIGN KEY (`user_name`) REFERENCES `org_profile` (`org_email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_master_ibfk_1` FOREIGN KEY (`user_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_master_ibfk_2` FOREIGN KEY (`user_grp_id`) REFERENCES `user_group_master` (`grp_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB';

#
# Structure for the `user_group_master` table :
#

CREATE TABLE `user_group_master` (
  `grp_id` int(11) NOT NULL auto_increment,
  `grp_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`grp_id`,`grp_name`),
  UNIQUE KEY `grp_name` (`grp_name`),
  KEY `grp_id` (`grp_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `user_group_master` table  (LIMIT 0,500)
#

INSERT INTO `user_group_master` (`grp_id`, `grp_name`) VALUES
  (1,'User'),
  (2,'Admin'),
  (3,'Super'),
  (4,'Master User'),
  (5,'Accounts');
COMMIT;
#
# Data for the `admin_records` table  (LIMIT 0,500)
#

INSERT INTO `admin_records` (`seq_id`, `user_id`, `admin_pass`, `flag`, `add_date`) VALUES
  (1,'admin','hjhjhj',0,'2012-12-18'),
  (2,'adminjk','jkljkl',0,'2017-11-17'),
  (3,'adminjk1','123123',0,'2018-11-18'),
  (4,'payadmin','admin123',1,'2025-11-25');
COMMIT;
