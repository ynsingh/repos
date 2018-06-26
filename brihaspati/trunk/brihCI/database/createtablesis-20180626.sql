-- -------------------------------------------------------------------
--
-- Table structure for table `Staff deputation perticulars`
--

CREATE TABLE `staff_deputation_perticulars` (
  `sdp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sdp_userid` int(11)  NOT NULL,
  `sdp_empcode` varchar(55)  NOT NULL,
  `sdp_deputation` varchar(255)  NOT NULL,
  `sdp_specification` varchar(50)  Default NULL,
  `sdp_fromdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdp_todate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdp_creatorid` varchar(255) NOT NULL,
  `sdp_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdp_modifierid` varchar(255) NOT NULL,
  `sdp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff department Exam passed details`
--

CREATE TABLE `staff_department_exam_perticulars` (
  `sdep_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sdep_userid` int(11)  NOT NULL,
  `sdep_empcode` varchar(55)  NOT NULL,
  `sdep_examname` varchar(255)  NOT NULL, 
  `sdep_specification` varchar(50)  Default NULL,
  `sdep_passdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdep_creatorid` varchar(255) NOT NULL,
  `sdep_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdep_modifierid` varchar(255) NOT NULL,
  `sdep_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------------
--
-- Table structure for table `Staff working arrangements`
--

CREATE TABLE `staff_working_arrangements_perticulars` (
  `swap_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `swap_userid` int(11)  NOT NULL,
  `swap_empcode` varchar(55)  NOT NULL,
  `swap_ocampus` varchar(255)  NOT NULL,
  `swap_ouo` varchar(255)  NOT NULL,
  `swap_odept` varchar(255)  NOT NULL,
  `swap_wcampus` varchar(255)  NOT NULL,
  `swap_wuo` varchar(255)  NOT NULL,
  `swap_wdept` varchar(255)  NOT NULL,
  `swap_creatorid` varchar(255) NOT NULL,
  `swap_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `swap_modifierid` varchar(255) NOT NULL,
  `swap_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff recruitment perticulars`
--

CREATE TABLE `staff_recruitment_perticulars` (
  `srp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `srp_userid` int(11)  NOT NULL,
  `srp_empcode` varchar(55)  NOT NULL,
  `srp_methodrecrtmnt` varchar(255)  NOT NULL,
  `srp_subcategory` varchar(255)  Default NULL,
  `srp_detail` varchar(255)  Default NULL,
  `srp_compassionname` varchar(255)  Default NULL,
  `srp_compassiondesig` varchar(255)  Default NULL,
  `srp_compassiondept` varchar(255)  Default NULL,
  `srp_creatorid` varchar(255) NOT NULL,
  `srp_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `srp_modifierid` varchar(255) NOT NULL,
  `srp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff disciplinary actions perticulars`
--

CREATE TABLE `staff_disciplinary_actions_perticulars` (
  `sdap_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sdap_userid` int(11)  NOT NULL,
  `sdap_empcode` varchar(55)  NOT NULL,
  `sdap_punishnature` varchar(255)  NOT NULL,
  `sdap_punishreason` varchar(255)  NOT NULL,
  `sdap_punishstatus` varchar(255)  NOT NULL,
  `sdap_fromdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdap_todate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdap_creatorid` varchar(255) NOT NULL,
  `sdap_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdap_modifierid` varchar(255) NOT NULL,
  `sdap_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
