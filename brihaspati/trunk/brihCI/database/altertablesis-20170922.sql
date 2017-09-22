use payroll;

CREATE TABLE `bankprofile_archive` (
  `bpa_id` int(11) NOT NULL auto_increment,
  `bpa_bpid` int(11) NOT NULL,
  `bpa_bank_name` varchar(250) NOT NULL,
  `bpa_branch_name` varchar(255) NOT NULL,
  `bpa_bank_address` varchar(500) NOT NULL,
  `bpa_ifsc_code` varchar(500) default NULL,
  `bpa_account_number` varchar(50) default NULL,
  `bpa_account_type` varchar(40) NOT NULL,
  `bpa_account_name` varchar(255) NOT NULL,
  `bpa_pan_number` varchar(20) default NULL,
  `bpa_tan_number` varchar(10) NOT NULL,
  `bpa_gst_number` varchar(100) NOT NULL,
  `bpa_aadhar_number` varchar(100) NOT NULL,
  `bpa_org_id` varchar(40) NOT NULL,
  `bpa_remark` varchar(100) NOT NULL,
  `bpa_creatorid` int(11) NOT NULL,
  `bpa_date` date NOT NULL,
   PRIMARY KEY  (`bpa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

