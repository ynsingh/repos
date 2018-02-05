use payroll;
--
-- Table structure for table `Salary Head`
--

CREATE TABLE `employee_type` (
  `empt_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `empt_code` varchar(50)  NOT NULL,
  `empt_name` varchar(255)  NOT NULL,
  `empt_shortname` varchar(255)  Default NULL,
  `empt_pfapplies` varchar(50)  Default NULL,
  `empt_maxpflimit` float  Default 0.0,
  `empt_creatorid` varchar(255) NOT NULL,
  `empt_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `empt_modifierid` varchar(255) NOT NULL,
  `empt_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`empt_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into employee_type values (1,'T01','Temporary','TEMP','N',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
insert into employee_type values (2,'P01','Permanent','PER','Y',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
insert into employee_type values (3,'R01','Regular','REG','Y',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');

-- -------------------------------------------------------------------
