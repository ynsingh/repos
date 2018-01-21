
-- --------------------------------------------------------

-- DROP DATABASE IF EXISTS `bhrm`;
-- CREATE DATABASE `bhrm`;
-- USE `bhrm`;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_api`
--

DROP TABLE IF EXISTS `applicant_api`;

CREATE TABLE `applicant_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant_id` int(11) DEFAULT NULL,
  `app_category` varchar(255) DEFAULT NULL,
  `app_catname` varchar(255) DEFAULT NULL,
  `app_subcategory` varchar(255) DEFAULT NULL,
  `app_activities` varchar(255) DEFAULT NULL,
  `app_maximumscore` varchar(255) DEFAULT NULL,
  `app_noofarticle` varchar(255) DEFAULT NULL,
  `app_scoreclaimed` varchar(255) DEFAULT NULL,
  `app_scoreverified` varchar(255) DEFAULT NULL,
  `app_varifiername` varchar(255) DEFAULT NULL,
  `app_varifydate` varchar(255) DEFAULT NULL,
  `app_remark` varchar(255) DEFAULT NULL,
  `app_ext1` varchar(200) DEFAULT NULL,
  `app_ext2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_data`
--

DROP TABLE IF EXISTS `applicant_data`;

CREATE TABLE `applicant_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apd_no` varchar(11) DEFAULT NULL,
  `apd_masterid` int(11) DEFAULT NULL,
  `apd_regdate` datetime DEFAULT NULL,
  `apd_reg_complete_date` datetime DEFAULT NULL,
  `apd_advno` int(11) DEFAULT NULL,
  `apd_postcode` varchar(200) DEFAULT NULL,
  `apd_dateres` varchar(255) DEFAULT NULL,
  `apd_lastdatecompapp` datetime DEFAULT NULL,
  `apd_postapplifor` varchar(255) DEFAULT NULL,
  `apd_depart` varchar(255) DEFAULT NULL,
  `apd_fieldofspecial` varchar(255) DEFAULT NULL,
  `apd_categappli` varchar(255) DEFAULT NULL,
  `apd_fname` varchar(255) DEFAULT NULL,
  `apd_midname` varchar(255) DEFAULT NULL,
  `apd_lastname` varchar(255) DEFAULT NULL,
  `apd_father_husbandname` varchar(255) DEFAULT NULL,
  `apd_mothername` varchar(255) DEFAULT NULL,
  `apd_aadharno` int(50) DEFAULT NULL,
  `apd_correspondenceadd` varchar(500) DEFAULT NULL,
  `apd_permanantadd` varchar(500) DEFAULT NULL,
  `apd_dob` date DEFAULT NULL,
  `apd_dop` date DEFAULT NULL,
  `apd_agedate` date DEFAULT NULL,
  `apd_gender` varchar(200) DEFAULT NULL,
  `apd_category` varchar(200) DEFAULT NULL,
  `apd_disability` varchar(200) DEFAULT NULL,
  `apd_maritialstatus` varchar(200) DEFAULT NULL,
  `apd_nationality` varchar(200) DEFAULT NULL,
  `apd_religion` varchar(200) DEFAULT NULL,
  `apd_remark` varchar(255) DEFAULT NULL,
  `apd_ext1` varchar(200) DEFAULT NULL,
  `apd_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


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

-- ----------------------------------------------------------

--
-- Table structure for table `applicant_performance`
--


DROP TABLE IF EXISTS `applicant_performance`;

CREATE TABLE `applicant_performance` (
  `aperfor_id` int(11) NOT NULL AUTO_INCREMENT,
  `aperfor_amid` int(11) DEFAULT NULL,
  `aperfor_teachexp` varchar(255) DEFAULT NULL,
  `aperfor_from` varchar(255) DEFAULT NULL,
  `aperfor_to` varchar(255) DEFAULT NULL,
  `aperfor_totalyearmonth` varchar(255) DEFAULT NULL,
  `aperfor_remark` varchar(255) DEFAULT NULL,
  `aperfor_ext1` varchar(255) DEFAULT NULL,
  `aperfor_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aperfor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_research`
--

DROP TABLE IF EXISTS `applicant_research`;

CREATE TABLE `applicant_research` (
  `ares_id` int(11) NOT NULL AUTO_INCREMENT,
  `ares_amid` int(11) DEFAULT NULL,
  `ares_researchproj` int(11) DEFAULT NULL,
  `ares_phd_thesisaward` varchar(255) DEFAULT NULL,
  `ares_phd_submitted` varchar(255) DEFAULT NULL,
  `ares_phd_inprogress` varchar(255) DEFAULT NULL,
  `ares_mphill_thesisaward` varchar(255) DEFAULT NULL,
  `ares_mphill_submitted` varchar(255) DEFAULT NULL,
  `ares_mphill_inprogress` varchar(255) DEFAULT NULL,
  `ares_pmah` varchar(255) DEFAULT NULL,
  `ares_remark` varchar(255) DEFAULT NULL,
  `ares_ext1` varchar(255) DEFAULT NULL,
  `ares_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ares_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_research_degree`
--

DROP TABLE IF EXISTS `applicant_research_degree`;

CREATE TABLE `applicant_research_degree` (
  `aresdeg_id` int(11) NOT NULL AUTO_INCREMENT,
  `aresdeg_amid` int(11) DEFAULT NULL,
  `aresdeg_degreename` varchar(255) DEFAULT NULL,
  `aresdeg_universityname` varchar(500) DEFAULT NULL,
  `aresdeg_dateofsubmission` datetime DEFAULT NULL,
  `aresdeg_dateofaward` datetime DEFAULT NULL,
  `aresdeg_titleofthesis` varchar(255) DEFAULT NULL,
  `aresdeg_asper_ugc_reg_2009` varchar(255) DEFAULT NULL,
  `aresdeg_remark` varchar(255) DEFAULT NULL,
  `aresdeg_ext1` varchar(255) DEFAULT NULL,
  `aresdeg_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aresdeg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_supportdata`
--

DROP TABLE IF EXISTS `applicant_supportdata`;

CREATE TABLE `applicant_supportdata` (
  `asuppd_id` int(11) NOT NULL AUTO_INCREMENT,
  `asuppd_amid` int(11) DEFAULT NULL,
  `aperfor_8a` varchar(255) DEFAULT NULL,
  `aperfor_8b` varchar(255) DEFAULT NULL,
  `aperfor_8c` varchar(255) DEFAULT NULL,
  `aperfor_8d` varchar(255) DEFAULT NULL,
  `aperfor_8e` varchar(255) DEFAULT NULL,
  `aperfor_9a` varchar(255) DEFAULT NULL,
  `aperfor_9b` varchar(255) DEFAULT NULL,
  `aperfor_9c` varchar(255) DEFAULT NULL,
  `aperfor_9d` varchar(255) DEFAULT NULL,
  `aperfor_9e` varchar(255) DEFAULT NULL,
  `aperfor_10i` varchar(255) DEFAULT NULL,
  `aperfor_10ii` varchar(255) DEFAULT NULL,
  `aperfor_10iii` varchar(255) DEFAULT NULL,
  `aperfor_11` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`asuppd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_total_api`
--

DROP TABLE IF EXISTS `applicant_total_api`;

CREATE TABLE `applicant_total_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ata_applicantid` int(11) DEFAULT NULL,
  `ata_apicategory` varchar(255) DEFAULT NULL,
  `ata_scoreclaimed` varchar(200) DEFAULT NULL,
  `ata_scoreverified` varchar(255) DEFAULT NULL,
  `ata_verifiername` varchar(255) DEFAULT NULL,
  `ata_verifierdate` varchar(255) DEFAULT NULL,
  `ata_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `applicant_uploaddata`
--

DROP TABLE IF EXISTS `applicant_uploaddata`;

CREATE TABLE `applicant_uploaddata` (
  `aupd_id` int(11) NOT NULL AUTO_INCREMENT,
  `aupd_amid` int(11) DEFAULT NULL,
  `aupd_enclosure1` varchar(255) DEFAULT NULL,
  `aupd_enclosure2` varchar(255) DEFAULT NULL,
  `aupd_enclosure3` varchar(255) DEFAULT NULL,
  `aupd_enclosure4` varchar(255) DEFAULT NULL,
  `aupd_enclosure5` varchar(255) DEFAULT NULL,
  `aupd_enclosure6` varchar(255) DEFAULT NULL,
  `aupd_enclosure7` varchar(255) DEFAULT NULL,
  `aupd_enclosure8` varchar(255) DEFAULT NULL,
  `aupd_enclosure9` varchar(255) DEFAULT NULL,
  `aupd_enclosure10` varchar(255) DEFAULT NULL,
  `aupd_enclosure11` varchar(255) DEFAULT NULL,
  `aupd_enclosure12` varchar(255) DEFAULT NULL,
  `aupd_enclosure13` varchar(255) DEFAULT NULL,
  `aupd_enclosure14` varchar(255) DEFAULT NULL,
  `aupd_enclosure15` varchar(255) DEFAULT NULL,
  `aupd_uploaddeclaration` varchar(255) DEFAULT NULL,
  `aupd_uploadnoc` varchar(255) DEFAULT NULL,
  `aupd_photo` varchar(500) DEFAULT NULL,
  `aupd_signature` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`aupd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Table structure for table `form_shortname`
--

DROP TABLE IF EXISTS `form_shortname`;

CREATE TABLE `form_shortname` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_advno` varchar(255) DEFAULT NULL,
  `form_code` varchar(255) DEFAULT NULL,
  `form_shortname` varchar(255) DEFAULT NULL,
  `form_name` varchar(255) DEFAULT NULL,
  `form_posttype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `job_open`
--

DROP TABLE IF EXISTS `job_open`;

CREATE TABLE `job_open` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_vacnacycode` varchar(255) DEFAULT NULL,
  `job_adverno` varchar(255) NOT NULL,
  `job_postcode` varchar(50) DEFAULT NULL,
  `job_nameofpost` varchar(255) NOT NULL,
  `job_department` varchar(255) NOT NULL,
  `job_vacancytotal` int(5) NOT NULL,
  `job_vacancysc` int(3) NOT NULL,
  `job_vacancyst` int(3) NOT NULL,
  `job_vacancyobc` int(3) NOT NULL,
  `job_vacancyur` int(3) NOT NULL,
  `job_vacancypwd` int(3) NOT NULL,
  `job_gradepay` varchar(255) NOT NULL,
  `job_emoluments` varchar(255) NOT NULL,
  `job_agelimit` varchar(255) NOT NULL,
  `job_group` varchar(100) NOT NULL,
  `job_essential` blob NOT NULL,
  `job_experience` blob NOT NULL,
  `job_desirable` blob NOT NULL,
  `job_responsibles` blob NOT NULL,
  `job_startdateonlineform` datetime NOT NULL,
  `job_lastdateonlineform` datetime NOT NULL,
  `job_lastdateformreach` datetime NOT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  level int(1) NOT NULL,
  host_ip varchar(25) NOT NULL,
  user varchar(255) default NULL,
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
