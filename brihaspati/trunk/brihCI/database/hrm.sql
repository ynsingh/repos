
-- --------------------------------------------------------

DROP DATABASE IF EXISTS `bhrm`;
CREATE DATABASE `bhrm`;
USE `bhrm`;

-- --------------------------------------------------------

--
-- Table structure for table `Applicantdata`
--

CREATE TABLE `applicantdata` ( 
	`id` INT(11) NOT NULL AUTO_INCREMENT , 
	`application_no` VARCHAR(255) NOT NULL , 
	`applicant_masterid` VARCHAR(255) NOT NULL ,  
	`reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP , 
	`reg_complete_date` DATETIME DEFAULT CURRENT_TIMESTAMP ,
	`ext1` VARCHAR(255) NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `ApplicationStep`
--

CREATE TABLE `applicationstep` ( 
	`id` INT(11) NOT NULL AUTO_INCREMENT , 
	`application_no` VARCHAR(255) NOT NULL , 
	`applicant_masterid` VARCHAR(255) NOT NULL , 
	`step1_status` VARCHAR(255) NOT NULL , 
	`step1_date` DATETIME DEFAULT CURRENT_TIMESTAMP ,
	`step2_status` VARCHAR(255) NOT NULL , 
	`step2_date` DATETIME DEFAULT CURRENT_TIMESTAMP ,
	`step3_status` VARCHAR(255) NOT NULL , 
	`step3_date` DATETIME DEFAULT CURRENT_TIMESTAMP ,
	`step4_status` VARCHAR(255) NOT NULL , 
	`step4_date` DATETIME DEFAULT CURRENT_TIMESTAMP ,
	`step5_status` VARCHAR(255) NOT NULL , 
	`step5_date` DATETIME DEFAULT CURRENT_TIMESTAMP , 
	`ext1` VARCHAR(255) NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_education`
--

CREATE TABLE `applicant_education` (
  `aedu_id` int(11) NOT NULL,
  `aedu_amid` int(11) NOT NULL,
  `aedu_class` varchar(255) NOT NULL,
  `aedu_institution` varchar(255) NOT NULL,
  `aedu_board` varchar(255) NOT NULL,
  `aedu_subject` varchar(255) NOT NULL,
  `aedu_passingyear` varchar(255) NOT NULL,
  `aedu_resultstatus` varchar(255) NOT NULL,
  `aedu_maxmarks` varchar(255) NOT NULL,
  `aedu_marksobtained` varchar(255) NOT NULL,
  `aedu_percentage` varchar(255) NOT NULL,
  `aedu_ext1` varchar(255) DEFAULT NULL,
  `aedu_ext2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `applicant_education`
  ADD PRIMARY KEY (`aedu_id`);
ALTER TABLE `applicant_education`
  MODIFY `aedu_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `applicant_employment`
--

CREATE TABLE `applicant_employment` (
  `aemp_id` int(11) NOT NULL,
  `aemp_amid` int(11) NOT NULL,
  `aemp_officename` varchar(255) NOT NULL,
  `aemp_post` varchar(255) NOT NULL,
  `aemp_pay` varchar(255) NOT NULL,
  `aemp_grade` varchar(255) NOT NULL,
  `aemp_appoinmentnature` varchar(255) NOT NULL,
  `aemp_doj` varchar(255) NOT NULL,
  `aemp_dol` varchar(255) NOT NULL,
  `aemp_remarks` varchar(255) NOT NULL,
  `aemp_experience` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `applicant_employment`
  ADD PRIMARY KEY (`aemp_id`);
ALTER TABLE `applicant_employment`
  MODIFY `aemp_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `applicant_entrance_exam`
--

CREATE TABLE `applicant_entrance_exam` (
  `aeex_id` int(11) NOT NULL,
  `aeex_smid` int(11) NOT NULL,
  `aeex_examname` varchar(255) NOT NULL,
  `aeex_rollno` varchar(255) NOT NULL,
  `aeex_rank` int(11) NOT NULL,
  `aeex_score` int(11) NOT NULL,
  `aeex_state` varchar(255) NOT NULL,
  `aeex_subject` varchar(255) NOT NULL,
  `aeex_passingyear` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `applicant_entrance_exam`
  ADD PRIMARY KEY (`aeex_id`);
ALTER TABLE `applicant_entrance_exam`
  MODIFY `aeex_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  level int(1) NOT NULL,
  host_ip varchar(25) NOT NULL,
  user varchar(25) NOT NULL,
  url varchar(255) NOT NULL,
  user_agent varchar(100) NOT NULL,
  message_title varchar(255) NOT NULL,
  message_desc mediumtext NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

-- --------------------------------------------------------

--
-- Table structure for table `user_role_type`
--

CREATE TABLE IF NOT EXISTS `user_role_type` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `scid` int(10) NOT NULL,
  `deptid` int(10) DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into user_role_type values (1,1,1,1,1,'Administrator','');
