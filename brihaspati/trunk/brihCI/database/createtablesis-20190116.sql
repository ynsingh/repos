
CREATE TABLE `salarydata_lt` (
  `saldlt_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `saldlt_empid` int(11)  NOT NULL,
  `saldlt_sheadid`  varchar(250) NOT NULL,
  `saldlt_shamount` double  Default 0,
  `saldlt_month` varchar(100)  NOT NULL,
  `saldlt_year` varchar(100) NOT NULL, 
  `saldlt_type` varchar(250) Default NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `salary_lt` (
  `sallt_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sallt_empid` int(11)  NOT NULL,
  `sallt_scid` int(11)  NOT NULL,
  `sallt_uoid` int(11)  NOT NULL,
  `sallt_deptid` int(11)  NOT NULL,
  `sallt_desigid` int(11)  NOT NULL,
  `sallt_sapost` varchar(250)  NOT NULL,
  `sallt_ddoid` int(11)  NOT NULL,
  `sallt_schemeid` int(11)  NOT NULL,
  `sallt_payscaleid` int(11)  NOT NULL,
  `sallt_bankaccno` varchar(250)  NOT NULL,
  `sallt_worktype` varchar(250) NOT NULL,
  `sallt_emptype` varchar(250) NOT NULL,
  `sallt_group` varchar(250) NOT NULL,
  `sallt_month` varchar(5)  NOT NULL,
  `sallt_year` varchar(50) NOT NULL,     
  `sallt_totalincome` double  Default 0,     
  `sallt_totaldeduction` double  Default 0,     
  `sallt_netsalary` double  Default 0,     
  `sallt_status` varchar(255) NOT NULL,    
  `sallt_type` varchar(255) Default NULL,    
  `sallt_paiddate` DATETIME DEFAULT CURRENT_TIMESTAMP,     
  `sallt_creationdate` DATETIME DEFAULT CURRENT_TIMESTAMP,     
  `sallt_creatorid` varchar(255) NOT NULL,
  `sallt_updatedate` DATETIME DEFAULT CURRENT_TIMESTAMP,     
  `sallt_modifierid` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

