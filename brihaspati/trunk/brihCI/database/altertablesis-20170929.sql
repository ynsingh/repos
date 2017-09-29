use payroll;

CREATE TABLE `ddo` (
     `ddo_id` int(11) NOT NULL auto_increment,
     `ddo_scid` int(11) NOT NULL,
     `ddo_deptid` int(11) NOT NULL,
     `ddo_schid` int(11) NOT NULL,
     `ddo_code` varchar(255) NOT NULL,
     `ddo_name` varchar(255) NOT NULL,
     `ddo_remark` varchar(255) default NULL,
     PRIMARY KEY (ddo_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

