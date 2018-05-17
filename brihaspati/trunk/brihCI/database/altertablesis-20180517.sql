use payroll;
CREATE TABLE `leave_apply` (
  `la_id` int(11) NOT NULL auto_increment,
  `la_userid` varchar(50) NOT NULL,
  `la_deptid` varchar(50) NOT NULL,
  `la_type` varchar(50) NOT NULL,
  `la_year` varchar(10) NOT NULL,
  `applied_la_from_date` DATETIME DEFAULT 0,
  `applied_la_to_date` DATETIME DEFAULT 0,
  `la_days` decimal(5,2) NOT NULL default 0,
  `granted_la_from_date` DATETIME DEFAULT 0,
  `granted_la_to_date` DATETIME DEFAULT 0,
  `la_taken` decimal(5,2)NOT NULL default 0,
  `la_status` int(11) default '0',
  `la_desc` varchar(100) NOT NULL,
  `la_rejres` varchar(100) DEFAULT NULL,
   PRIMARY KEY  (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `leave_earned` (
  `le_id` int(11) NOT NULL auto_increment,
  `le_userid` varchar(50) NOT NULL,
  `le_type` varchar(50) NOT NULL,
  `le_year` varchar(10) NOT NULL,
  `le_earned` decimal(5,2)NOT NULL,
   PRIMARY KEY  (`le_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

