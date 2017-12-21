use payroll;

CREATE TABLE `scheme_department_archive` (
     `sda_id` int(11) NOT NULL auto_increment,
     `sda_sdid` int(11) NOT NULL,
     `sda_deptid` int(11) NOT NULL,
     `sda_code` varchar(255)  NULL,
     `sda_name` varchar(255) NOT NULL,
     `sda_short` varchar(255) default NULL,
     `sda_desc` varchar(255)  default NULL,
     `sda_archuserid` int(11) NOT NULL,
     `sda_archdate`  date NOT NULL,
      PRIMARY KEY (sda_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `salary_grade_master_archive` (
     `sgma_id` int(11) NOT NULL auto_increment,
     `sgma_sgmid` int(11) NOT NULL,
     `sgma_name` varchar(20) NOT NULL,
     `sgma_max` int(11) NOT NULL default '0',
     `sgma_min` int(11) NOT NULL default '0',
     `sgma_gradepay` int(11) NOT NULL default '500',
     `sgma_org_id` int(11) NOT NULL default '1',
     `sgma_archuserid` int(11) NOT NULL,
     `sgma_archdate`  date NOT NULL,
      PRIMARY KEY (sgma_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


