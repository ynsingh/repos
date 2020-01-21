-- -------------------------------------------------------------------
--
-- Table structure for table `Salary data`
--

CREATE TABLE `salary_data_archive` (
  `salda_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `salda_saldid` int(11) NOT NULL,
  `salda_empid` int(11)  NOT NULL,
  `salda_sheadid`  varchar(250) NOT NULL,
  `salda_shamount` double  Default 0,
  `salda_installment` VARCHAR(50) NULL,
  `salda_month` varchar(100)  NOT NULL,
  `salda_year` varchar(100) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Salary`
--

CREATE TABLE `salary_archive` (
  `sala_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sala_salid` int(11) NOT NULL,
  `sala_empid` int(11)  NOT NULL,
  `sala_scid` int(11)  NOT NULL,
  `sala_uoid` int(11)  NOT NULL,
  `sala_deptid` int(11)  NOT NULL,
  `sala_desigid` int(11)  NOT NULL,
  `sala_sapost` varchar(250)  NOT NULL,
  `sala_ddoid` int(11)  NOT NULL,
  `sala_schemeid` int(11)  NOT NULL,
  `sala_payscaleid` int(11)  NOT NULL,
  `sala_bankaccno` varchar(250)  NOT NULL,
  `sala_worktype` varchar(250) NOT NULL,
  `sala_emptype` varchar(250) NOT NULL,
  `sala_group` varchar(250) NOT NULL,
  `sala_month` varchar(5)  NOT NULL,
  `sala_year` varchar(50) NOT NULL,
  `sala_totalincome` double  Default 0,
  `sala_totaldeduction` double  Default 0,
  `sala_netsalary` double  Default 0,
  `sala_status` varchar(255) NOT NULL,
  `sala_paiddate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sala_creationdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sala_creatorid` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table salarydata_lt`
--

CREATE TABLE `salarydata_lt_archive` (
  `saldlta_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `saldlta_saldltid` int(11)  NOT NULL,
  `saldlta_empid` int(11)  NOT NULL,
  `saldlta_sheadid`  varchar(250) NOT NULL,
  `saldlta_shamount` double  Default 0,
  `saldlta_installment` VARCHAR(50) NULL,
  `saldlta_month` varchar(100)  NOT NULL,
  `saldlta_year` varchar(100) NOT NULL,
  `saldlta_type` varchar(250) Default NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table salary_lt`
--

CREATE TABLE `salary_lt_archive` (
  `sallta_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sallta_salltid` int(11) NOT NULL,
  `sallta_empid` int(11)  NOT NULL,
  `sallta_scid` int(11)  NOT NULL,
  `sallta_uoid` int(11)  NOT NULL,
  `sallta_deptid` int(11)  NOT NULL,
  `sallta_desigid` int(11)  NOT NULL,
  `sallta_sapost` varchar(250)  NOT NULL,
  `sallta_ddoid` int(11)  NOT NULL,
  `sallta_schemeid` int(11)  NOT NULL,
  `sallta_payscaleid` int(11)  NOT NULL,
  `sallta_bankaccno` varchar(250)  NOT NULL,
  `sallta_worktype` varchar(250) NOT NULL,
  `sallta_emptype` varchar(250) NOT NULL,
  `sallta_group` varchar(250) NOT NULL,
  `sallta_month` varchar(5)  NOT NULL,
  `sallta_year` varchar(50) NOT NULL,
  `sallta_totalincome` double  Default 0,
  `sallta_totaldeduction` double  Default 0,
  `sallta_netsalary` double  Default 0,
  `sallta_status` varchar(255) NOT NULL,
  `sallta_type` varchar(255) Default NULL,
  `sallta_paiddate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sallta_creationdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sallta_creatorid` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

