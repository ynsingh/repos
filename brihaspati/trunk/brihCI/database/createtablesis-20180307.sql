use payroll;

--
-- Table structure for table `hod_list`
--

CREATE TABLE `hod_list` (
  `hl_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `hl_userid` int(11)  NOT NULL,
  `hl_empcode` varchar(100)  Default NULL,
  `hl_deptid` int(10)  NOT NULL,
  `hl_scid` int(10) NOT NULL,
  `hl_datefrom` datetime NOT NULL,
  `hl_dateto` datetime NOT NULL,
  `hl_status` varchar(50) NOT NULL,
  `hl_creatorid` varchar(255) NOT NULL,
  `hl_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hl_modifierid` varchar(255) NOT NULL,
  `hl_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`hl_userid`,`hl_deptid`,`hl_scid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
