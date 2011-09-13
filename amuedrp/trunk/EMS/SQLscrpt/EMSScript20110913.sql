CREATE TABLE `election_manager` (
  `manager_id` varchar(100) NOT NULL default '',
  `institute_id` varchar(20) NOT NULL default '',
  `department` varchar(50) default NULL,
  `staff_id` varchar(100) default NULL,
  `user_id` varchar(100) default NULL,
  `status` varchar(50) default NULL,
  PRIMARY KEY  (`manager_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
