use payroll;

--
-- Table structure for table `staff_retirement`
--

CREATE TABLE `staff_retirement` (
  `sre_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sre_empid` int(11)  NOT NULL,
  `sre_empcode` varchar(100)  Default NULL,
  `sre_empemailid` varchar(255)  NOT NULL,
  `sre_doj` datetime NOT NULL,
  `sre_dor` datetime NOT NULL,
  `sre_reason` varchar(255) NOT NULL,
  `sre_reasondate` datetime NOT NULL,
  `sre_creatorid` varchar(255) NOT NULL,
  `sre_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sre_modifierid` varchar(255) NOT NULL,
  `sre_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
   /*UNIQUE (`sre_empid`,`sre_empcode`,`sre_empemailid`)*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
