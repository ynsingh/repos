use payroll;

CREATE TABLE `leave_type_master` (
  `lt_id` int(11) NOT NULL auto_increment,
  `lt_name` varchar(50) NOT NULL,
  `lt_value` int(11) default '0',
  `lt_remarks` varchar(255) default NULL,
   PRIMARY KEY  (`lt_id`),
   UNIQUE KEY `lt_name` (`lt_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

