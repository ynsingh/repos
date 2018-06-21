use payroll;

CREATE TABLE `society_master_list` (
  `soc_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `soc_scode` varchar(100)  NOT NULL,
  `soc_sname` varchar(255) NOT NULL,
  `soc_sremark` varchar(100) NOT NULL,
  `soc_creatorid` varchar(255) NOT NULL,
  `soc_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `soc_modifierid` varchar(255) NOT NULL,
  `soc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`soc_sname`,`soc_scode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


