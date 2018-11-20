
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

-- --------------------------------------------------------
--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `userEmail` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `firstName` varchar(200) DEFAULT NULL,
  `lastName` varchar(200) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `userType` varchar(100) DEFAULT NULL,
  `email_ID` varchar(200) DEFAULT NULL,
  `verification` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `userEmail`, `password`, `firstName`, `lastName`, `designation`, `userType`, `email_ID`, `verification`) VALUES
(1, 'admin', 'admin123', 'admin', 'admin', 'admin', 'admin', 'amar24@gmail.com', 'Verified');

-- --------------------------------------------------------

--
-- Table structure for table `admin_conteupload`
--

CREATE TABLE `admin_conteupload` (
  `acu_id` int(11) NOT NULL,
  `acu_courseid` int(11) DEFAULT NULL,
  `acu_weekname` varchar(255) DEFAULT NULL,
  `acu_seqno` int(11) DEFAULT NULL,
  `acu_weekcontname` varchar(255) DEFAULT NULL,
  `acu_contpath` varchar(255) DEFAULT NULL,
  `acu_filetype` varchar(255) DEFAULT NULL,
  `acu_filename` varchar(255) DEFAULT NULL,
  `acu_createdate` datetime DEFAULT NULL,
  `acu_creatorid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    ALTER TABLE `admin_conteupload`
      ADD PRIMARY KEY (`acu_id`);
    ALTER TABLE `admin_conteupload`
      MODIFY `acu_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `coursesannouncement`
--

CREATE TABLE `courseannouncement` (
        `crsann_id` INT(11) NOT NULL AUTO_INCREMENT ,
        `crsann_crsid` INT(11) NOT NULL ,
        `crsann_duration` VARCHAR(255) NOT NULL ,
        `crsann_regstart` DATE NULL ,
        `crsann_regend` DATE NULL ,
        `crsann_crsstart` DATE NULL ,
        `crsann_crsend` DATE NULL ,
        `crsann_feedbkdate` DATE NULL ,
        `crsann_certdate` DATE NULL ,
        `crsann_creatorid` VARCHAR(255) NOT NULL ,
        `crsann_createdate` DATE NOT NULL ,
        PRIMARY KEY (`crsann_id`)
) ENGINE = InnoDB;


-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `cou_id` int(11) NOT NULL,
  `cou_code` varchar(255) DEFAULT NULL,
  `cou_name` varchar(255) DEFAULT NULL,
  `cou_eligible` varchar(255) DEFAULT NULL,
  `cou_discipline` text,
  `cou_fees` varchar(155) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `courses`
  ADD PRIMARY KEY (`cou_id`);

ALTER TABLE `courses`
  MODIFY `cou_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`cou_id`, `cou_code`, `cou_name`, `cou_eligible`, `cou_discipline`, `cou_fees`) VALUES
(1, 'MLPVC001', 'Magical and Logical Power of Vedic Mathematics(In English)', 'For all School Teachers', 'Workshop fee for national teachers-300/- & for international teachers 100$', '300'),
(2, 'MLPVC002', 'Magical and Logical Power of Vedic Mathematics (Application based) (In English)', 'For all diploma, BE, BBA and Competitive Exam Students', 'Workshop fee for national students -300/- & for international students100$', '300'),
(3, 'LPVM003', 'Logical Power of Vedic Mathematics (In English)', 'For class 8th to 12th Standard Students', 'Workshop fee for national students -250/- & for international students 80 $', '250'),
(4, 'AVMK004', 'Logical Power of Vedic Mathematics (In Hindi)', 'For class 8th to 12th Standard Students', 'Workshop fee for national students -250/-', '250'),
(5, 'AVMK001', 'Application of Vedic Mathematics for Kids (In English)', 'for Class 4rd to 7th Standard Students', 'Workshop fee for national students -200/- & for international students 60$', '200'),
(6, 'AVMK002', 'Application of Vedic Mathematics for Kids (In Hindi)', 'for Class 4rd to 7th Standard Students', 'Workshop fee for national students -200/- ', '200');

-- --------------------------------------------------------

--
-- Table structure for table `discussions`,`topics`,`comments`
--


DROP TABLE IF EXISTS `discussions`;
CREATE TABLE `discussions` (
  `ds_id` int(11) NOT NULL AUTO_INCREMENT,
  `ds_crsid` int(11) NOT NULL,
  `ds_usrid` int(11) NOT NULL,
  `ds_title` varchar(255) NOT NULL,
  `ds_body` text NOT NULL,
  `ds_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ds_is_active` int(1) NOT NULL,
  PRIMARY KEY (`ds_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `topics` (
  `tp_id` int(11) NOT NULL AUTO_INCREMENT,
  `tp_title` varchar(255) NOT NULL DEFAULT '',
  `tp_body` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `tp_usrid` int(11) unsigned NOT NULL,
  `tp_dsid` int(11) unsigned NOT NULL,
  `is_sticky` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `cm_id` int(11) NOT NULL AUTO_INCREMENT,
  `cm_dsid` int(11) NOT NULL,
  `cm_tpid` int(11) NOT NULL,
  `cm_body` text NOT NULL,
  `cm_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cm_usrid` int(11) NOT NULL,
  `cm_is_active` int(1) NOT NULL,
  PRIMARY KEY (`cm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `senderemail` varchar(255) DEFAULT NULL,
  `modulename` varchar(255) DEFAULT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifierid` varchar(255) NOT NULL,
  `modifidate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `email_setting`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `email_setting`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Dumping data for table `email_setting`
--

INSERT INTO `email_setting` (`id`, `emailprotocol`, `emailhost`, `emailport`, `username`, `password`, `sendername`, `senderemail`, `modulename`, `creatorid`, `createdate`, `modifierid`, `modifidate`) VALUES
(1, 'smtp', 'smtp.gmail.com', 465, 'annantgyan@gmail.com', 'adubey321', 'Annantgyan', 'annantgyan@gmail.com', 'All', 'admin', '2018-01-30 00:00:00', 'admin', '2018-06-29 17:34:06');


CREATE TABLE `enquiry` (
  `en_id` int(11) NOT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  `en_mobile` varchar(50) DEFAULT NULL,
  `en_email` varchar(255) DEFAULT NULL,
  `en_interestin` varchar(255) DEFAULT NULL,
  `en_otherquery` varchar(255) DEFAULT NULL,
  `enquiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `enquiry`
  ADD PRIMARY KEY (`en_id`);

ALTER TABLE `enquiry`
  MODIFY `en_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `forgotPass`
--

CREATE TABLE `forgotPass` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `rkey` varchar(255) NOT NULL,
  `passdate` datetime DEFAULT NULL,
  `expdate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `forgotPass`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `forgotPass`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `loggedin_detail`
--

CREATE TABLE `loggedin_detail` (
  `lgd_id` int(11) NOT NULL,
  `lgd_signupid` int(11) DEFAULT NULL,
  `lgd_usersid` int(11) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `logout_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `loggedin_detail`
  ADD PRIMARY KEY (`lgd_id`);
ALTER TABLE `loggedin_detail`
  MODIFY `lgd_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `level` int(1) NOT NULL,
  `host_ip` varchar(25) NOT NULL,
  `user` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `user_agent` varchar(100) NOT NULL,
  `message_title` varchar(255) NOT NULL,
  `message_desc` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- --------------------------------------------------------

--
-- Table structure for table `mapcontentmaterial`
--

CREATE TABLE `mapcontentmaterial` (
        `mcm_id` INT(11) NOT NULL AUTO_INCREMENT ,
        `mcm_crsid` INT(11) NOT NULL ,
        `mcm_crsannid` INT(11) NOT NULL ,
        `mcm_weekno` VARCHAR(50) NOT NULL ,
        `mcm_seqno` INT(11) NULL ,
        `mcm_contentloc` VARCHAR(255) NULL ,
        `mcm_alias` VARCHAR(255) NULL ,
        `mcm_creatorid` VARCHAR(255) NOT NULL ,
        `mcm_createdate` DATE NOT NULL ,
        PRIMARY KEY (`mcm_id`),
        UNIQUE (`mcm_crsid`, `mcm_weekno`, `mcm_crsannid`, `mcm_seqno`)
) ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `ongoingworkshop`
--

CREATE TABLE `ongoingworkshop` (
  `ow_id` int(11) NOT NULL,
  `owpg_id` int(11) DEFAULT NULL,
  `ow_courseid` int(11) DEFAULT NULL,
  `ow_name` varchar(255) DEFAULT NULL,
  `ow_email` varchar(255) DEFAULT NULL,
  `ow_place` varchar(255) DEFAULT NULL,
  `ow_nationality` varchar(255) DEFAULT NULL,
  `ow_contact` varchar(100) DEFAULT NULL,
  `ow_school` varchar(500) DEFAULT NULL,
  `ow_rperson` varchar(255) DEFAULT NULL,
  `ow_amount` varchar(255) DEFAULT NULL,
  `ow_date` DATE NULL ,
  `ow_bankname` varchar(500) DEFAULT NULL,
  `ow_referenceno` varchar(255) DEFAULT NULL,
  `ow_paymentgateway` varchar(500) DEFAULT NULL,
  `ow_paymentstatus` varchar(255) DEFAULT NULL,
  `ow_remark` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `ongoingworkshop`
  ADD PRIMARY KEY (`ow_id`);
ALTER TABLE `ongoingworkshop`
  MODIFY `ow_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `ongoingworkshop_pg`
--

CREATE TABLE `ongoingworkshop_pg` (
  `owpg_id` int(11) NOT NULL,
  `ow_id` int(11) DEFAULT NULL,
  `owpg_txnid` varchar(255) NOT NULL,
  `owpg_pinfo` varchar(255) NOT NULL,
  `owpg_amount` int(11) NOT NULL,
  `owpg_ftype` varchar(255) DEFAULT NULL,
  `owpg_date` datetime NOT NULL,
  `owpg_gw` varchar(255) DEFAULT NULL,
  `owpg_status` varchar(255) DEFAULT NULL,
  `owpg_txncode` varchar(255) DEFAULT NULL,
  `owpg_reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `ongoingworkshop_pg`
  ADD PRIMARY KEY (`owpg_id`);
ALTER TABLE `ongoingworkshop_pg`
  MODIFY `owpg_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `testid` bigint(20) NOT NULL DEFAULT '0',
  `subid` int(11) NOT NULL DEFAULT '0',
  `question` varchar(500) DEFAULT NULL,
  `optiona` varchar(100) DEFAULT NULL,
  `optionb` varchar(100) DEFAULT NULL,
  `optionc` varchar(100) DEFAULT NULL,
  `optiond` varchar(100) DEFAULT NULL,
  `correctanswer` enum('optiona','optionb','optionc','optiond') DEFAULT NULL,
  `marks` int(11) DEFAULT NULL,
  PRIMARY KEY (`qid`),
  UNIQUE KEY `qid` (`qid`,`testid`,`subid`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`testid`) REFERENCES `test` (`testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `referrel`
--

CREATE TABLE `referrel` (
  `ref_id` int(11) NOT NULL,
  `ref_name` varchar(255) DEFAULT NULL,
  `ref_email` varchar(255) DEFAULT NULL,
  `ref_mobile` varchar(255) DEFAULT NULL,
  `ref_orgname` varchar(255) DEFAULT NULL,
  `ref_bank_acno` varchar(255) DEFAULT NULL,
  `ref_bank_ifsc` varchar(255) DEFAULT NULL,
  `ref_place` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `referrel`
  ADD PRIMARY KEY (`ref_id`);
ALTER TABLE `referrel`
  MODIFY `ref_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `sign_up`
--

CREATE TABLE `sign_up` (
  `su_id` int(11) NOT NULL,
  `su_userid` varchar(200) DEFAULT NULL,
  `su_name` varchar(255) DEFAULT NULL,
  `su_emailid` varchar(255) DEFAULT NULL,
  `su_password` varchar(255) DEFAULT NULL,
  `su_string` varchar(255) DEFAULT NULL,
  `su_status` varchar(255) DEFAULT NULL,
  `su_howtoknow` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `sign_up`
  ADD PRIMARY KEY (`su_id`);

ALTER TABLE `sign_up`
  MODIFY `su_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Table structure for table `studentquestion`
--

DROP TABLE IF EXISTS `studentquestion`;
CREATE TABLE `studentquestion` (
  `sqid` int(11) NOT NULL AUTO_INCREMENT,
  `su_id` int(11) NOT NULL DEFAULT '0',
  `testid` bigint(20) NOT NULL DEFAULT '0',
  `subid` int(11) NOT NULL DEFAULT '0',
  `quid` int(11) NOT NULL DEFAULT '0',
  `answered` enum('answered','unanswered','review') DEFAULT NULL,
  `stdanswer` enum('optiona','optionb','optionc','optiond') DEFAULT NULL,
   PRIMARY KEY (`sqid`),
  UNIQUE KEY (`quid`,`su_id`,`testid`,`subid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `studenttest`
--

DROP TABLE IF EXISTS `studenttest`;
CREATE TABLE `studenttest` (
  `st_stid` int(11) NOT NULL,
  `st_subid` int(11) DEFAULT NULL,
  `st_stdid` int(11) NOT NULL DEFAULT '0',
  `st_testid` bigint(20) NOT NULL DEFAULT '0',
  `st_starttime` time DEFAULT NULL,
  `st_endtime` time DEFAULT NULL,
  `st_correctlyanswered` int(11) DEFAULT NULL,
  `st_marks` int(11) DEFAULT '0',
  `st_status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `studenttest`
  ADD PRIMARY KEY (`st_stid`),
  ADD UNIQUE KEY `st_stdid` (`st_stdid`,`st_subid`,`st_testid`),
  ADD KEY `studenttest_ibfk_2` (`st_testid`);


ALTER TABLE `studenttest`
  MODIFY `st_stid` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `studenttest`
  ADD CONSTRAINT `studenttest_ibfk_1` FOREIGN KEY (`st_stdid`) REFERENCES `sign_up` (`su_id`),
  ADD CONSTRAINT `studenttest_ibfk_2` FOREIGN KEY (`st_testid`) REFERENCES `test` (`testid`);

-- --------------------------------------------------------

--
-- Table structure for table `feedbackquestion`
--

CREATE TABLE `feedbackquestion` (
  `fq_id` int(11) NOT NULL,
  `fq_crsid` int(11) DEFAULT NULL,
  `fq_question` varchar(255) NOT NULL,
  `fq_optiona` varchar(100) DEFAULT NULL,
  `fq_optionb` varchar(100) DEFAULT NULL,
  `fq_optionc` varchar(100) DEFAULT NULL,
  `fq_optiond` varchar(100) DEFAULT NULL,
  `fq_quesdes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `feedbackquestion`
  ADD PRIMARY KEY (`fq_id`);

ALTER TABLE `feedbackquestion`
  MODIFY `fq_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `stu_feedback`
--

CREATE TABLE `stu_feedback` (
  `stf_id` int(11) NOT NULL,
  `stf_studentid` int(11) DEFAULT NULL,
  `stf_courseid` int(11) DEFAULT NULL,
  `stf_ans1` varchar(255) DEFAULT NULL,
  `stf_ans2` varchar(255) DEFAULT NULL,
  `stf_ans3` varchar(255) DEFAULT NULL,
  `stf_ans4` varchar(255) DEFAULT NULL,
  `stf_ans5` varchar(255) DEFAULT NULL,
  `stf_ans6` varchar(255) DEFAULT NULL,
  `stf_ans7` varchar(255) DEFAULT NULL,
  `stf_ans8` varchar(255) DEFAULT NULL,
  `stf_ans9` varchar(255) DEFAULT NULL,
  `stf_ans10` varchar(255) DEFAULT NULL,
  `stf_ans11` varchar(255) DEFAULT NULL,
  `stf_suggestion` blob DEFAULT NULL,
  `stf_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `stu_feedback`
  ADD PRIMARY KEY (`stf_id`);
ALTER TABLE `stu_feedback`
  MODIFY `stf_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `testid` bigint(20) NOT NULL AUTO_INCREMENT,
  `testname` varchar(30) NOT NULL,
  `testdesc` varchar(100) DEFAULT NULL,
  `testdate` date DEFAULT NULL,
  `testtime` time DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `testfrom` time  DEFAULT NULL,
  `testto` time  DEFAULT NULL ,
  `duration` int(11) DEFAULT NULL,
  `totalquestions` int(11) DEFAULT NULL,
  `attemptedstudents` bigint(20) DEFAULT NULL,
  `testcode` varchar(40) NOT NULL,
  PRIMARY KEY (`testid`),
  UNIQUE `testname` ( `testname`, `subid`),
  CONSTRAINT `test_fk1` FOREIGN KEY (`subid`) REFERENCES `courses` (`cou_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `signupid` int(11) DEFAULT NULL,
  `oauth_provider` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `oauth_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `locale` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `cover` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
-- --------------------------------------------------------

--
-- Table structure for table `user_course_type`
--

CREATE TABLE `user_course_type` (
  `uct_id` int(11) NOT NULL,
  `uct_userid` int(11) DEFAULT NULL,
  `uct_courseid` int(11) DEFAULT NULL,
  `uct_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `user_course_type`
  ADD PRIMARY KEY (`uct_id`);

ALTER TABLE `user_course_type`
  MODIFY `uct_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `userdetails`
--

CREATE TABLE `userdetails` (
  `ud_id` int(11) NOT NULL,
  `ud_userid` int(11) NOT NULL,
  `ud_fname` varchar(255) DEFAULT NULL,
  `ud_mname` varchar(255) DEFAULT NULL,
  `ud_fdesig` varchar(255) DEFAULT NULL,
  `ud_mdesig` varchar(255) DEFAULT NULL,
  `ud_address` varchar(255) DEFAULT NULL,
  `ud_fadcategory` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

ALTER TABLE `userdetails` ADD PRIMARY KEY (`ud_id`);
ALTER TABLE `userdetails` MODIFY `ud_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `visitors`
--

CREATE TABLE `visitors` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `level` int(1) NOT NULL,
  `host_ip` varchar(25) NOT NULL,
  `user` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `user_agent` varchar(100) NOT NULL,
  `message_title` varchar(255) NOT NULL,
  `message_desc` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `visitors`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `visitors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


COMMIT;
