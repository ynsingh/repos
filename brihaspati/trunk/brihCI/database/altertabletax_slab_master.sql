use payroll;

CREATE TABLE `tax_slab_master` (
  `tsm_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `tsm_fy` varchar(255) NOT NULL,
  `tsm_name` varchar(255) NOT NULL,
  `tsm_startvalue` varchar(255) NOT NULL,
  `tsm_endvalue` varchar(255) NOT NULL,
  `tsm_type` varchar(20) NOT NULL,
  `tsm_gender` varchar(20) NOT NULL,
  `tsm_percent` varchar(10) NOT NULL,
  `tsm_remarks` varchar(255) default NULL,
   PRIMARY KEY  (`tsm_id`),
   UNIQUE KEY `tsm_fy` (`tsm_fy`,`tsm_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

