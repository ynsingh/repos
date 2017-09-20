use payroll;

CREATE TABLE `bankprofile` (
  `id` int(11) NOT NULL auto_increment,
  `bank_name` varchar(250) NOT NULL,
  `branch_name` varchar(100) NOT NULL,
  `bank_address` varchar(500) NOT NULL,
  `ifsc_code` varchar(500) default NULL,
  `account_number` varchar(20) default NULL,
  `account_type` varchar(40) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `pan_number` varchar(20) default NULL,
  `tan_number` varchar(10) NOT NULL,
  `gst_number` varchar(100) NOT NULL,
  `aadhar_number` varchar(100) NOT NULL,
  `org_id` varchar(40) NOT NULL,
  `remark` varchar(100) NOT NULL,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

 

