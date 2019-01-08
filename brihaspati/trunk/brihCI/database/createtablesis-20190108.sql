
CREATE TABLE `salary_data` (
  `sald_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sald_empid` int(11)  NOT NULL,
  `sald_sheadid`  varchar(250) NOT NULL,
  `sald_shamount` double  Default 0,
  `sald_month` varchar(100)  NOT NULL,
  `sald_year` varchar(100) NOT NULL 

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `salary` (
  `sal_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sal_empid` int(11)  NOT NULL,
  `sal_scid` int(11)  NOT NULL,
  `sal_uoid` int(11)  NOT NULL,
  `sal_deptid` int(11)  NOT NULL,
  `sal_desigid` int(11)  NOT NULL,
  `sal_sapost` varchar(250)  NOT NULL,
  `sal_ddoid` int(11)  NOT NULL,
  `sal_schemeid` int(11)  NOT NULL,
  `sal_payscaleid` int(11)  NOT NULL,
  `sal_bankaccno` varchar(250)  NOT NULL,
  `sal_worktype` varchar(250) NOT NULL,
  `sal_emptype` varchar(250) NOT NULL,
  `sal_group` varchar(250) NOT NULL,
  `sal_month` varchar(5)  NOT NULL,
  `sal_year` varchar(50) NOT NULL,     
  `sal_totalincome` double  Default 0,     
  `sal_totaldeduction` double  Default 0,     
  `sal_netsalary` double  Default 0,     
  `sal_status` varchar(255) NOT NULL,    
  `sal_paiddate` DATETIME DEFAULT CURRENT_TIMESTAMP,     
  `sal_creationdate` DATETIME DEFAULT CURRENT_TIMESTAMP,     
  `sal_creatorid` varchar(255) NOT NULL,
  `sal_updatedate` DATETIME DEFAULT CURRENT_TIMESTAMP,     
  `sal_modifierid` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

