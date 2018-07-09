-- -------------------------------------------------------------------
--
-- Table structure for table `Staff working arrangements`
--

CREATE TABLE `staff_postwbudget_particulars` (
  `spwp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `spwp_empid` int(11)  NOT NULL,
  `spwp_uitid` int(11)  NOT NULL,
  `spwp_ocampus` varchar(255)  NOT NULL,
  `spwp_ouo` varchar(255)  NOT NULL,
  `spwp_odept` varchar(255)  NOT NULL,
  `spwp_wcampus` varchar(255)  NOT NULL,
  `spwp_wuo` varchar(255)  NOT NULL,
  `spwp_wdept` varchar(255)  NOT NULL,
  `spwp_creatorid` varchar(255) NOT NULL,
  `spwp_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `spwp_modifierid` varchar(255) NOT NULL,
  `spwp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
