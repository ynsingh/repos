-- -------------------------------------------------------------------
--
-- Table structure for table `staff_academic_qualification`
--

CREATE TABLE `staff_academic_qualification` (
  `saq_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `saq_empid` int(11)  NOT NULL,
  `saq_dgree` varchar(255)  DEFAULT NULL,
  `saq_board_univ` varchar(255)  DEFAULT NULL,
  `saq_result` varchar(255)  DEFAULT NULL,
  `saq_yopass` varchar(255)  DEFAULT NULL,
  `saq_discipline` varchar(255)  DEFAULT NULL,
  `saq_creatorid` varchar(255) DEFAULT NULL,
  `saq_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `saq_modifierid` varchar(255) NOT NULL,
  `saq_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------------
--
-- Table structure for table `staff_technical_qualification`
--


CREATE TABLE `staff_technical_qualification` (
  `stq_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `stq_empid` int(11)  NOT NULL,
  `stq_dgree` varchar(255)  DEFAULT NULL,
  `stq_board_univ` varchar(255)  DEFAULT NULL,
  `stq_result` varchar(255)  DEFAULT NULL,
  `stq_dopass` varchar(255)  DEFAULT NULL,
  `stq_discipline` varchar(255)  DEFAULT NULL,
  `stq_creatorid` varchar(255) DEFAULT NULL,
  `stq_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `stq_modifierid` varchar(255) NOT NULL,
  `stq_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

