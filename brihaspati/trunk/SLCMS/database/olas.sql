-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 24, 2017 at 07:09 PM
-- Server version: 5.6.27
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `olas`
--


-- --------------------------------------------------------

--
-- Table structure for table `AdmissionEnterneceData`
--






-- --------------------------------------------------------

--
-- Table structure for table `AdmissionMeritList`
--

CREATE TABLE `admissionmeritlist` (
        `id` INT(11) NOT NULL AUTO_INCREMENT ,
        `application_no` VARCHAR(255) NOT NULL ,
        `course_name` VARCHAR(255) NOT NULL ,
        `student_name` VARCHAR(255) NOT NULL ,
        `father_name` VARCHAR(255) NOT NULL ,
        `marks` INT(5) NOT NULL ,
        `admission_quota` VARCHAR(255) NOT NULL ,
        `category` VARCHAR(255) NOT NULL ,
        `meritlist_no` VARCHAR(255) NOT NULL ,
        `lastdate_admission` DATE DEFAULT NULL ,
        `admission_taken` VARCHAR(255) NOT NULL ,
        `admission_date` DATE DEFAULT NULL ,
        `ext1` VARCHAR(255) NULL ,
        PRIMARY KEY (`id`),
        UNIQUE (`application_no`, `course_name`, `student_name`)
) ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `AdmissionStep`
--

CREATE TABLE `admissionstep` ( 
	`id` INT(11) NOT NULL AUTO_INCREMENT , 
	`application_no` VARCHAR(255) NOT NULL , 
	`student_masterid` VARCHAR(255) NOT NULL , 
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
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(255) NOT NULL,
  `cat_code` varchar(255) NOT NULL,
  `cat_short` varchar(255) NOT NULL,
  `cat_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into category values (1,'All','01','All','All Category');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `city_code` varchar(255) NOT NULL,
  `city_short` varchar(255) NOT NULL,
  `city_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `country_id` int(11) NOT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `country_short` varchar(255) DEFAULT NULL,
  `country_desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`country_id`, `country_name`, `country_code`, `country_short`, `country_desc`) VALUES
(1, 'India', '91', 'IN', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `degree_rule`
--

CREATE TABLE `degree_rule` (
  `dr_id` int(11) NOT NULL,
  `dr_prgid` int(11) NOT NULL,
  `dr_mincredit` int(5) NOT NULL,
  `dr_minsubcredit` int(5) NOT NULL,
  `dr_minthesiscredit` int(5) NOT NULL,
  `dr_minsub` int(4) NOT NULL,
  `dr_minsemester` int(3) NOT NULL,
  `dr_mincpi` DOUBLE(6,2) NOT NULL,
  `dr_maxcredit` int(5) NOT NULL,
  `dr_maxsemeter` int(3) DEFAULT NULL,
  `dr_ext` varchar(255) DEFAULT  NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `degree_rule`
  ADD PRIMARY KEY (`dr_id`);

ALTER TABLE `degree_rule`
  MODIFY `dr_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `Department`
--

CREATE TABLE `Department` (
  `dept_id` int(11) NOT NULL,
  `dept_name` varchar(255) NOT NULL,
  `dept_code` varchar(255) NOT NULL,
  `dept_short` varchar(255) NOT NULL,
  `dept_description` varchar(255) NOT NULL,
  `dept_schoolname` varchar(255) NOT NULL,
  `dept_schoolcode` varchar(255) NOT NULL,
  `dept_sccode` varchar(255) NOT NULL,
  `dept_orgcode` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `designation`
--

CREATE TABLE `designation` (
  `desig_id` int(11) NOT NULL,
  `desig_name` varchar(255) NOT NULL,
  `desig_code` varchar(255) NOT NULL,
  `desig_short` varchar(255) NOT NULL,
  `desig_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `email_setting`
--

CREATE TABLE `email_setting` (
  `id` int(10) NOT NULL,
  `emailprotocol` varchar(50) NOT NULL,
  `emailhost` varchar(50) NOT NULL,
  `emailport` int(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sendername` varchar(255) NOT NULL,
  `senderemail` VARCHAR(255) NULL,
  `modulename` VARCHAR(255) NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` varchar(255) NOT NULL,
  `modifidate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fees_master`
--

CREATE TABLE `fees_master` (
  `fm_id` int(11) NOT NULL,
  `fm_head` varchar(255) NOT NULL,
  `fm_programid` int(11) NOT NULL,
  `fm_acadyear` varchar(50) NOT NULL,
  `fm_semester` varchar(50) NOT NULL,
  `fm_gender` varchar(50) NOT NULL,
  `fm_category` varchar(100) NOT NULL,
  `fm_amount` int(11) NOT NULL,
  `fm_frmdate` DATE DEFAULT NULL,
  `fm_todate` DATE DEFAULT NULL,
  `fm_desc` varchar(255) NOT NULL,
  `fm_ext1` varchar(255) NOT NULL,
  `fm_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fees_master_archive`
--

CREATE TABLE `fees_master_archive` (
  `fma_id` int(11) NOT NULL,
  `fma_fmid` int(11) NOT NULL,
  `fma_head` varchar(255) NOT NULL,
  `fma_programid` int(11) NOT NULL,
  `fma_acadyear` varchar(50) NOT NULL,
  `fma_semester` varchar(50) NOT NULL,
  `fma_gender` varchar(50) NOT NULL,
  `fma_category` varchar(100) NOT NULL,
  `fma_amount` int(11) NOT NULL,
  `fma_frmdate` DATE DEFAULT NULL,
  `fma_todate` DATE DEFAULT NULL,
  `fma_desc` varchar(255) NOT NULL,
  `fma_ext1` varchar(255) NOT NULL,
  `fma_ext2` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `fees_master_archive`
  ADD PRIMARY KEY (`fma_id`);

ALTER TABLE `fees_master_archive`
  MODIFY `fma_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `grade master`
--

CREATE TABLE `grade_master` (
  `gm_id` int(11) NOT NULL,
  `gm_gradename` varchar(255) NOT NULL,
  `gm_gradepoint` varchar(255) NOT NULL,
  `gm_short` varchar(255)NULL,
  `gm_desc` varchar(255)  NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `grade_master`
  ADD PRIMARY KEY (`gm_id`),
  ADD UNIQUE KEY `gm_gradename` (`gm_gradename`);

ALTER TABLE `grade_master`
  MODIFY `gm_id` int(11) NOT NULL AUTO_INCREMENT;

insert into `grade_master` values (1,'A','10','','','admin','2017-07-04','admin','2017-07-04'),(2,'B','8','','','admin','2017-07-04','admin','2017-07-04'),(3,'C','6','','','admin','2017-07-04','admin','2017-07-04'),(4,'D','4','','','admin','2017-07-04','admin','2017-07-04'),(5,'E','2','','','admin','2017-07-04','admin','2017-07-04'),(6,'F','0','','','admin','2017-07-04','admin','2017-07-04');

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
-- Table structure for table `org_profile`
--

CREATE TABLE `org_profile` (
  `org_id` int(11) NOT NULL,
  `org_code` varchar(255) NOT NULL,
  `org_name` varchar(255) NOT NULL,
  `org_nickname` varchar(255) NOT NULL,
  `org_type` varchar(255) NOT NULL,
  `org_tagline` varchar(255) NOT NULL,
  `org_address1` varchar(255) NOT NULL,
  `org_address2` varchar(255) NOT NULL,
  `org_city` varchar(255) NOT NULL,
  `org_district` varchar(255) NOT NULL,
  `org_state` varchar(255) NOT NULL,
  `org_countrycode` varchar(255) NOT NULL,
  `org_pincode` varchar(255) NOT NULL,
  `org_phone` varchar(255) NOT NULL,
  `org_fax` varchar(255) NOT NULL,
  `org_email` varchar(255) NOT NULL,
  `org_web` varchar(255) NOT NULL,
  `org_institutedomain` varchar(255) NOT NULL,
  `org_tanno` varchar(255) NOT NULL,
  `org_logo` varchar(255) NOT NULL,
  `org_affiliation` varchar(255) NOT NULL,
  `org_status` varchar(255) NOT NULL,
  `org_reg_date` DATE DEFAULT NULL,
  `org_close_date` DATE DEFAULT NULL,
  `org_adminfn` varchar(255) NOT NULL,
  `org_adminln` varchar(255) NOT NULL,
  `org_admindesig` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` varchar(255) NOT NULL,
  `modify_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `org_profile`
--

INSERT INTO `org_profile` (`org_id`, `org_code`, `org_name`, `org_nickname`, `org_type`, `org_tagline`, `org_address1`, `org_address2`, `org_city`, `org_district`, `org_state`, `org_countrycode`, `org_pincode`, `org_phone`, `org_fax`, `org_email`, `org_web`, `org_institutedomain`, `org_tanno`, `org_logo`, `org_affiliation`, `org_status`, `org_reg_date`, `org_close_date`, `org_adminfn`, `org_adminln`, `org_admindesig`, `creatorid`, `create_date`, `modifierid`, `modify_date`) VALUES
(1, 'CU001', 'Indira Gandhi National Tribal University', 'IGNTU', 'Central Govt', '', 'Lal Pur, Amarkantak', '', '', '', 'Madhya Pradesh', '91', '484887', '07629 269 701', '07629 269 701', 'admission@igntu.ac.in', 'www.igntu.ac.in', 'igntu.ac.in', '', '', 'MHRD', 'Active', '2008-07-01', '', 'Prof T V', 'Kattimani', 'Vice Chancellor', '1', '2017-04-21', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

CREATE TABLE `program` (
  `prg_id` int(11) NOT NULL,
  `prg_scid`  INT(11) NOT NULL,
  `prg_deptid`  INT(11) NOT NULL,
  `prg_category` varchar(255) NOT NULL,
  `prg_name` varchar(255) NOT NULL,
  `prg_branch` varchar(255) NOT NULL,
  `prg_code` varchar(255) NOT NULL,
  `prg_short` varchar(255) NOT NULL,
  `prg_desc` varchar(255) NOT NULL,
  `prg_pattern` varchar(255) NOT NULL,
  `prg_seat` int(5) NOT NULL,
  `prg_credit` VARCHAR(255) DEFAULT NULL, 
  `prg_mintime` varchar(255) NOT NULL,
  `prg_maxtime` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `program_subject_teacher`
--

CREATE TABLE `program_subject_teacher` (
  `pstp_id` int(11) NOT NULL,
  `pstp_scid` int(11) NOT NULL,
  `pstp_prgid` int(11) NOT NULL,
  `pstp_subid` int(11) NOT NULL,
  `pstp_papid` int(11) NOT NULL,
  `pstp_teachid` int(11) NOT NULL,
  `pstp_acadyear` varchar(50) NOT NULL,
  `pstp_sem` varchar(10) NOT NULL,
  `pstp_ext1` varchar(255) DEFAULT NULL,
  `pstp_ext2` varchar(255) DEFAULT NULL,
  `pstp_creatorid` varchar(50) NOT NULL,
  `pstp_createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `pstp_modifierid` varchar(50) NOT NULL,
  `pstp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table ` program category`
--

CREATE TABLE `programcategory` (
  `prgcat_id` int(11) NOT NULL,
  `prgcat_name` varchar(255) NOT NULL,
  `prgcat_code` varchar(255)  NULL,
  `prgcat_short` varchar(255)NULL,
  `prgcat_desc` varchar(255)  NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `programcategory`
  ADD PRIMARY KEY (`prgcat_id`),
  ADD UNIQUE KEY `prgcat_name` (`prgcat_name`);

ALTER TABLE `programcategory`
  MODIFY `prgcat_id` int(11) NOT NULL AUTO_INCREMENT;

---------------------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (1, 'Administrator', 'Responsible for Admin job');
INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (2, 'Faculty', 'Responsible for Teacher job');
INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (3, 'Student', 'Responsible for Student job');


-- --------------------------------------------------------

--
-- Table structure for table `seat_program_studycenter`
--

CREATE TABLE `seat_program_studycenter` (
  `spsc_id` int(11) NOT NULL,
  `spsc_prg_id` int(11) NOT NULL,
  `spsc_sc_code` varchar(255) NOT NULL,
  `spsc_gender` varchar(255) NOT NULL,
  `spsc_totalseat` varchar(255) NOT NULL,
  `spsc_acadyear` varchar(255) NOT NULL,
  `spsc_sem` varchar(255) NOT NULL,
  `spsc_creatorid` varchar(255) NOT NULL,
  `spsc_createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `spsc_modifierid` varchar(255) NOT NULL,
  `spsc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `seat_reservation`
--

CREATE TABLE `seat_reservation` (
  `id` int(11) NOT NULL,
  `org_code` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  `percentage` varchar(10) NOT NULL,
  `noofseat` int(4) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `semester rule`
--

CREATE TABLE `semester_rule` (
  `semcr_id` int(11) NOT NULL,
  `semcr_prgid` int(11) NOT NULL,
  `semcr_semester` int(5) NOT NULL,
  `semcr_mincredit` int(5) NOT NULL,
  `semcr_maxcredit` int(5) NOT NULL,
  `semcr_semcpi` DOUBLE(6,2) NOT NULL,
  `semcr_ext1` varchar(255) NOT NULL,
  `semcr_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `semester_rule`
  ADD PRIMARY KEY (`semcr_id`);

ALTER TABLE `semester_rule`
  MODIFY `semcr_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `state_id` int(11) NOT NULL,
  `state_name` varchar(255) NOT NULL,
  `state_code` varchar(255) NOT NULL,
  `state_short` varchar(255) NOT NULL,
  `state_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_education`
--

CREATE TABLE `student_education` (
  `sedu_id` int(11) NOT NULL,
  `sedu_smid` int(11) NOT NULL,
  `sedu_class` varchar(255) NOT NULL,
  `sedu_institution` varchar(255) NOT NULL,
  `sedu_board` varchar(255) NOT NULL,
  `sedu_subject` varchar(255) NOT NULL,
  `sedu_passingyear` varchar(255) NOT NULL,
  `sedu_resultstatus` varchar(255) NOT NULL,
  `sedu_maxmarks` varchar(255) NOT NULL,
  `sedu_marksobtained` varchar(255) NOT NULL,
  `sedu_percentage` varchar(255) NOT NULL,
  `sedu_ext1` varchar(255) DEFAULT NULL,
  `sedu_ext2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_employment`
--

CREATE TABLE `student_employment` (
  `semp_id` int(11) NOT NULL,
  `semp_smid` int(11) NOT NULL,
  `semp_officename` varchar(255) NOT NULL,
  `semp_post` varchar(255) NOT NULL,
  `semp_pay` varchar(255) NOT NULL,
  `semp_grade` varchar(255) NOT NULL,
  `semp_appoinmentnature` varchar(255) NOT NULL,
  `semp_doj` varchar(255) NOT NULL,
  `semp_dol` varchar(255) NOT NULL,
  `semp_remarks` varchar(255) NOT NULL,
  `semp_experience` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_entrance_exam`
--

CREATE TABLE `student_entrance_exam` (
  `seex_id` int(11) NOT NULL,
  `seex_smid` int(11) NOT NULL,
  `seex_examname` varchar(255) NOT NULL,
  `seex_rollno` varchar(255) NOT NULL,
  `seex_rank` int(11) NOT NULL,
  `seex_score` int(11) NOT NULL,
  `seex_state` varchar(255) NOT NULL,
  `seex_subject` varchar(255) NOT NULL,
  `seex_passingyear` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_entry_exit`
--

CREATE TABLE `student_entry_exit` (
  `senex_id` int(11) NOT NULL,
  `senex_smid` int(11) NOT NULL,
  `senex_prgid` int(11) NOT NULL,
  `senex_rollno` varchar(255) NOT NULL,
  `senex_dateofadmission` DATETIME DEFAULT NULL,
  `senex_grade` varchar(15) DEFAULT NULL,
  `senex_gradedate` DATETIME DEFAULT NULL,
  `senex_idcardvalidity` DATETIME DEFAULT NULL,
  `senex_idcardissuedate` DATETIME DEFAULT NULL,
  `senex_entexamname` varchar(255) DEFAULT NULL,
  `senex_entexamrollno` varchar(255) DEFAULT  NULL,
  `senex_entexamrank` varchar(255) DEFAULT  NULL,
  `senex_entexamapplicationno` varchar(255) DEFAULT  NULL,
  `senex_entexammeritno` varchar(255) DEFAULT  NULL,
  `senex_cpi` varchar(255) DEFAULT  NULL,
  `senex_noduesgiven` varchar(255) DEFAULT  NULL,
  `senex_noduesdate` DATETIME DEFAULT NULL,
  `senex_ext` varchar(255) DEFAULT  NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_entry_exit`
  ADD PRIMARY KEY (`senex_id`),
  ADD UNIQUE KEY `senex_smid` (`senex_smid`,`senex_prgid`,`senex_rollno`);

ALTER TABLE `student_entry_exit`
  MODIFY `senex_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `student_fees`
--

CREATE TABLE `student_fees` (
  `sfee_id` int(11) NOT NULL,
  `sfee_smid` int(11) NOT NULL,
  `sfee_spid` int(11) NOT NULL,
  `sfee_feename` varchar(255) NOT NULL,
  `sfee_feeamount` varchar(255) NOT NULL,
  `sfee_installment` varchar(55) NOT NULL,
  `sfee_installment_date` DATE DEFAULT NULL,
  `sfee_duedate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sfee_paymentmethod` varchar(255) NOT NULL,
  `sfee_depositdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sfee_referenceno` varchar(255) NOT NULL,
  `sfee_bankname` VARCHAR(255) NULL,
  `sfee_paymentgateway` varchar(255) NOT NULL,
  `sfee_paymentstatus` varchar(255) NOT NULL,
  `sfee_feespaidstatus` varchar(255) NOT NULL,
  `sfee_remarks` varchar(255) NOT NULL,
  `sfee_ext1` varchar(255) NOT NULL,
  `sfee_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_marks`
--

CREATE TABLE `student_marks` (
  `smr_id` int(11) NOT NULL,
  `smr_scid` int(11) NOT NULL,
  `smr_deptid` int(11) NOT NULL,
  `smr_prgid` int(11) NOT NULL,
  `smr_subid` int(11) NOT NULL,
  `smr_papid` int(11) NOT NULL,
  `smr_acadyear` varchar(50) NOT NULL,
  `smr_sem` varchar(10) NOT NULL,
  `smr_mmmarks` int(10) NOT NULL,
  `smr_marks` int(11) NOT NULL,
  `smr_grade` varchar(55) DEFAULT NULL,
  `smr_creatorid` varchar(50) NOT NULL,
  `smr_createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `smr_modifierid` varchar(50) NOT NULL,
  `smr_modifydate`  DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_marks`
  ADD PRIMARY KEY (`smr_id`),
  ADD UNIQUE KEY `smr_scid` (`smr_scid`,`smr_prgid`,`smr_subid`,`smr_papid`,`smr_acadyear`,`smr_sem`);

ALTER TABLE `student_marks`
  MODIFY `smr_id` int(11) NOT NULL AUTO_INCREMENT;

------------------------------------------------------

--
-- Table structure for table `student_master`
--

CREATE TABLE `student_master` (
  `sm_id` int(11) NOT NULL,
  `sm_userid` int(11) NOT NULL,
  `sm_applicationno` varchar(255) DEFAULT NULL,
  `sm_meritno` varchar(255) DEFAULT NULL,
  `sm_testname` varchar(255) DEFAULT NULL,
  `sm_universitycode` varchar(255) DEFAULT NULL,
  `sm_sccode` varchar(255) DEFAULT NULL,
  `sm_rollno` varchar(255) DEFAULT NULL,
  `sm_enrollmentno` varchar(255) DEFAULT NULL,
  `sm_fname` varchar(255) DEFAULT NULL,
  `sm_mname` varchar(255) DEFAULT NULL,
  `sm_lname` varchar(255) DEFAULT NULL,
  `sm_nameinhindi` varchar(255) DEFAULT NULL,
  `sm_dob` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sm_pob` varchar(255) DEFAULT NULL,
  `sm_email` varchar(255) DEFAULT NULL,
  `sm_secemail` varchar(255) DEFAULT NULL,
  `sm_mobile` varchar(12) DEFAULT NULL,
  `sm_category` varchar(255) DEFAULT NULL,
  `sm_caste` varchar(255) DEFAULT NULL,
  `sm_gender` varchar(255) DEFAULT NULL,
  `sm_mstatus` varchar(255) DEFAULT NULL,
  `sm_bloodgroup` varchar(255) DEFAULT NULL,
  `sm_nationality` varchar(255) DEFAULT NULL,
  `sm_minority` varchar(255) DEFAULT NULL,
  `sm_reservationtype` varchar(255) DEFAULT NULL,
  `sm_phyhandicaped` varchar(255) DEFAULT NULL,
  `sm_uid` varchar(255) DEFAULT NULL,
  `sm_panno` varchar(255) DEFAULT NULL,
  `sm_passportno` varchar(255) DEFAULT NULL,
  `sm_religion` varchar(255) DEFAULT NULL,
  `sm_photo` varchar(255) DEFAULT NULL,
  `sm_signature` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_parent`
--

CREATE TABLE `student_parent` (
  `spar_id` int(11) NOT NULL,
  `spar_smid` int(11) NOT NULL,
  `spar_fathername` varchar(255) DEFAULT NULL,
  `spar_fatheremail` varchar(255) DEFAULT NULL,
  `spar_fatherphoneno` varchar(255) DEFAULT NULL,
  `spar_fatheroccupation` varchar(255) DEFAULT NULL,
  `spar_fatherincome` varchar(255) DEFAULT NULL,
  `spar_mothername` varchar(255) DEFAULT NULL,
  `spar_motheremail` varchar(255) DEFAULT NULL,
  `spar_motherphoneno` varchar(255) DEFAULT NULL,
  `spar_motheroccupation` varchar(255) DEFAULT NULL,
  `spar_motherincome` varchar(255) DEFAULT NULL,
  `spar_paddress` varchar(255) DEFAULT NULL,
  `spar_ppostoffice` VARCHAR(255) DEFAULT NULL,
  `spar_pcity` varchar(255) DEFAULT NULL,
  `spar_pdistrict` varchar(255) DEFAULT NULL,
  `spar_pstate` varchar(255) DEFAULT NULL,
  `spar_pcountry` varchar(255) DEFAULT NULL,
  `spar_ppincode` varchar(255) DEFAULT NULL,
  `spar_caddress` varchar(255) DEFAULT NULL,
  `spar_cpostoffice` VARCHAR(255) DEFAULT NULL,
  `spar_ccity` varchar(255) DEFAULT NULL,
  `spar_cdistrict` varchar(255) DEFAULT NULL,
  `spar_cstate` varchar(255) DEFAULT NULL,
  `spar_ccountry` varchar(255) DEFAULT NULL,
  `spar_cpincode` varchar(255) DEFAULT NULL,
  `spar_garname` varchar(255) DEFAULT NULL,
  `spar_garemail` varchar(255) DEFAULT NULL,
  `spar_garphoneno` varchar(255) DEFAULT NULL,
  `spar_garoccupation` varchar(255) DEFAULT NULL,
  `spar_garincome` varchar(255) DEFAULT NULL,
  `spar_garaddress` varchar(255) DEFAULT NULL,
  `spar_garcity` varchar(255) DEFAULT NULL,
  `spar_gardistrict` varchar(255) DEFAULT NULL,
  `spar_garstate` varchar(255) DEFAULT NULL,
  `spar_garcountry` varchar(255) DEFAULT NULL,
  `spar_garpincode` varchar(255) DEFAULT NULL,
  `spar_fathernamehindi` varchar(255) DEFAULT NULL,
  `spar_mothernamehindi` varchar(255) DEFAULT NULL,
  `spar_garnamehindi` varchar(255) DEFAULT NULL,
  `spar_ext` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_program`
--

CREATE TABLE `student_program` (
  `sp_id` int(11) NOT NULL,
  `sp_smid` int(11) NOT NULL,
  `sp_sccode` varchar(255) DEFAULT NULL,
  `sp_deptid` varchar(255) DEFAULT NULL,
  `sp_pcategory` varchar(255) DEFAULT NULL,
  `sp_programid` varchar(255) DEFAULT NULL,
  `sp_branch` varchar(255) DEFAULT NULL,
  `sp_acadyear` varchar(255) DEFAULT NULL,
  `sp_semester` varchar(255) DEFAULT NULL,
  `sp_subid1` int(11) DEFAULT NULL,
  `sp_subid2` int(11) DEFAULT NULL,
  `sp_subid3` int(11) DEFAULT NULL,
  `sp_subid4` int(11) DEFAULT NULL,
  `sp_subid5` int(11) DEFAULT NULL,
  `sp_subid6` int(11) DEFAULT NULL,
  `sp_subid7` int(11) DEFAULT NULL,
  `sp_subid8` int(11) DEFAULT NULL,
  `sp_subid9` int(11) DEFAULT NULL,
  `sp_subid10` int(11) DEFAULT NULL,
  `sp_ext1` varchar(255) DEFAULT NULL,
  `sp_ext2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `study_center`
--

CREATE TABLE `study_center` (
  `sc_id` int(11) NOT NULL,
  `sc_code` varchar(255) NOT NULL,
  `org_code` varchar(255) NOT NULL,
  `sc_name` varchar(255) NOT NULL,
  `sc_nickname` varchar(255) NOT NULL,
  `sc_address` varchar(255) NOT NULL,
  `sc_city` varchar(255) NOT NULL,
  `sc_district` varchar(255) NOT NULL,
  `sc_state` varchar(255) NOT NULL,
  `sc_country` varchar(255) NOT NULL,
  `sc_pincode` varchar(10) NOT NULL,
  `sc_phone` varchar(255) NOT NULL,
  `sc_fax` varchar(255) NOT NULL,
  `sc_status` varchar(255) NOT NULL,
  `sc_startdate` DATE DEFAULT NULL,
  `sc_closedate` DATE DEFAULT NULL,
  `sc_website` varchar(255) NOT NULL,
  `sc_incharge` varchar(255) NOT NULL,
  `sc_mobile` varchar(25) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `sub_id` int(11) NOT NULL,
  `sub_name` varchar(255) NOT NULL,
  `sub_code` varchar(255) NOT NULL,
  `sub_short` varchar(255) NOT NULL,
  `sub_desc` varchar(255) NOT NULL,
  `sub_ext1` varchar(255) NOT NULL,
  `sub_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject_paper`
--

CREATE TABLE `subject_paper` (
  `subp_id` int(11) NOT NULL,
  `subp_sub_id` int(11) NOT NULL,
  `subp_subtype` VARCHAR(255) NOT NULL,
  `subp_paperno` VARCHAR(50) NOT NULL,
  `subp_name` varchar(255) NOT NULL,
  `subp_code` varchar(100) NOT NULL,
  `subp_short` varchar(255) NOT NULL,
  `subp_desp` varchar(255) NOT NULL,
  `subp_degree` VARCHAR(255) NOT NULL,
  `subp_branch` VARCHAR(255) DEFAULT NULL,
  `subp_acadyear` VARCHAR(20) NOT NULL,
  `subp_ext1` varchar(255) NOT NULL,
  `subp_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE (`subp_degree`, `subp_acadyear`, `subp_sub_id`,`subp_subtype`,`subp_paperno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject_paper_archive`
--

CREATE TABLE `subject_paper_archive` (
  `subpa_id` int(11) NOT NULL,
  `subpa_subpid` int(11) NOT NULL,
  `subpa_sub_id` int(11) NOT NULL,
  `subpa_subtype` VARCHAR(255) NOT NULL,
  `subpa_paperno` VARCHAR(50) NOT NULL,
  `subpa_name` varchar(255) NOT NULL,
  `subpa_code` varchar(100) NOT NULL,
  `subpa_short` varchar(255) NOT NULL,
  `subpa_desp` varchar(255) NOT NULL,
  `subpa_degree` VARCHAR(255) NOT NULL,
  `subpa_branch` VARCHAR(255) DEFAULT NULL,
  `subpa_acadyear` VARCHAR(20) NOT NULL,
  `subpa_ext1` varchar(255) NOT NULL,
  `subpa_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE (`subpa_degree`, `subpa_acadyear`, `subpa_sub_id`,`subpa_subtype`,`subpa_paperno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_paper_archive`
  ADD PRIMARY KEY (`subpa_id`);

ALTER TABLE `subject_paper_archive`
  MODIFY `subpa_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `subject_prerequisite`
--
CREATE TABLE `subject_prerequisite` (
  `spreq_id` int(11) NOT NULL,
  `spreq_subid` int(11) NOT NULL,
  `spreq_depsubid` int(11) NOT NULL,
  `spreq_subpid` int(11) NOT NULL,
  `spreq_depsubpid` int(11) NOT NULL,
  `spreq_ext1` varchar(255) NOT NULL,
  `spreq_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_prerequisite`
  ADD PRIMARY KEY (`spreq_id`);

ALTER TABLE `subject_prerequisite`
  MODIFY `spreq_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `subject semester`
--

CREATE TABLE `subject_semester` (
  `subsem_id` int(11) NOT NULL,
  `subsem_subid` int(11) NOT NULL,
  `subsem_prgid` int(11) NOT NULL,
  `subsem_semester` int(5) NOT NULL,
  `subsem_subtype` varchar(255) NOT NULL,
  `subsem_ext1` varchar(255) NOT NULL,
  `subsem_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_semester`
  ADD PRIMARY KEY (`subsem_id`);

ALTER TABLE `subject_semester`
  MODIFY `subsem_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `user_role_type`
--


CREATE TABLE `user_role_type` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `scid` int(10) NOT NULL,
  `deptid` int(10) DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into user_role_type values (1,1,1,1,1,'Administrator','');

--
-- Indexes for dumped tables
--


--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`country_id`);

--
-- Indexes for table `Department`
--
ALTER TABLE `Department`
  ADD PRIMARY KEY (`dept_id`),
  ADD UNIQUE KEY `dept_code` (`dept_code`,`dept_schoolcode`);

--
-- Indexes for table `designation`
--
ALTER TABLE `designation`
  ADD PRIMARY KEY (`desig_id`);

--
-- Indexes for table `email_setting`
--
ALTER TABLE `email_setting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fees_master`
--
ALTER TABLE `fees_master`
  ADD PRIMARY KEY (`fm_id`);

--
-- Indexes for table `org_profile`
--
ALTER TABLE `org_profile`
  ADD PRIMARY KEY (`org_id`),
  ADD UNIQUE KEY `org_code` (`org_code`);

--
-- Indexes for table `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`prg_id`),
  ADD UNIQUE KEY `prg_category` (`prg_category`,`prg_name`,`prg_branch`);

--
-- Indexes for table `program_subject_teacher`
--
ALTER TABLE `program_subject_teacher`
  ADD PRIMARY KEY (`pstp_id`),
  ADD UNIQUE KEY `pstp_scid` (`pstp_scid`,`pstp_prgid`,`pstp_subid`,`pstp_papid`,`pstp_teachid`,`pstp_acadyear`,`pstp_sem`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `seat_program_studycenter`
--
ALTER TABLE `seat_program_studycenter`
  ADD PRIMARY KEY (`spsc_id`);

--
-- Indexes for table `seat_reservation`
--
ALTER TABLE `seat_reservation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `org_code` (`org_code`,`category_id`,`percentage`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`state_id`);

--
-- Indexes for table `student_education`
--
ALTER TABLE `student_education`
  ADD PRIMARY KEY (`sedu_id`);

--
-- Indexes for table `student_employment`
--
ALTER TABLE `student_employment`
  ADD PRIMARY KEY (`semp_id`);

--
-- Indexes for table `student_entrance_exam`
--
ALTER TABLE `student_entrance_exam`
  ADD PRIMARY KEY (`seex_id`);

--
-- Indexes for table `student_fees`
--
ALTER TABLE `student_fees`
  ADD PRIMARY KEY (`sfee_id`);

--
-- Indexes for table `student_master`
--
ALTER TABLE `student_master`
  ADD PRIMARY KEY (`sm_id`);

--
-- Indexes for table `student_parent`
--
ALTER TABLE `student_parent`
  ADD PRIMARY KEY (`spar_id`);

--
-- Indexes for table `student_program`
--
ALTER TABLE `student_program`
  ADD PRIMARY KEY (`sp_id`);

--
-- Indexes for table `study_center`
--
ALTER TABLE `study_center`
  ADD PRIMARY KEY (`sc_id`),
  ADD UNIQUE KEY `sc_code` (`sc_code`,`org_code`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sub_id`),
  ADD UNIQUE KEY `sub_name` (`sub_name`,`sub_code`);

--
-- Indexes for table `subject_paper`
--
ALTER TABLE `subject_paper`
  ADD PRIMARY KEY (`subp_id`);
--
-- Indexes for table `user_role_type`
--
ALTER TABLE `user_role_type`
  ADD UNIQUE KEY `userid` (`userid`,`roleid`,`usertype`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `country_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Department`
--
ALTER TABLE `Department`
  MODIFY `dept_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `designation`
--
ALTER TABLE `designation`
  MODIFY `desig_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `email_setting`
--
ALTER TABLE `email_setting`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `fees_master`
--
ALTER TABLE `fees_master`
  MODIFY `fm_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `org_profile`
--
ALTER TABLE `org_profile`
  MODIFY `org_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `program`
--
ALTER TABLE `program`
  MODIFY `prg_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `program_subject_teacher`
--
ALTER TABLE `program_subject_teacher`
  MODIFY `pstp_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `seat_program_studycenter`
--
ALTER TABLE `seat_program_studycenter`
  MODIFY `spsc_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `seat_reservation`
--
ALTER TABLE `seat_reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `state_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_education`
--
ALTER TABLE `student_education`
  MODIFY `sedu_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_employment`
--
ALTER TABLE `student_employment`
  MODIFY `semp_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_entrance_exam`
--
ALTER TABLE `student_entrance_exam`
  MODIFY `seex_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_fees`
--
ALTER TABLE `student_fees`
  MODIFY `sfee_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_master`
--
ALTER TABLE `student_master`
  MODIFY `sm_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_parent`
--
ALTER TABLE `student_parent`
  MODIFY `spar_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_program`
--
ALTER TABLE `student_program`
  MODIFY `sp_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `study_center`
--
ALTER TABLE `study_center`
  MODIFY `sc_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `sub_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subject_paper`
--
ALTER TABLE `subject_paper`
  MODIFY `subp_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
