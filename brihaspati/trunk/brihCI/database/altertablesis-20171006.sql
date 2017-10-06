use payroll;

CREATE TABLE `ddo_archive` (
     `ddoa_id` int(11) NOT NULL auto_increment,
     `ddoa_ddoid` int(11) NOT NULL,
     `ddoa_scid` int(11) NOT NULL,
     `ddoa_deptid` int(11) NOT NULL,
     `ddoa_schid` int(11) NOT NULL,
     `ddoa_code` varchar(255) NOT NULL,
     `ddoa_name` varchar(255) NOT NULL,
     `ddoa_remark` varchar(255) default NULL,
     `ddoa_archuserid` int(11) NOT NULL,
     `ddoa_archdate`  date NOT NULL,
     PRIMARY KEY (ddoa_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE  scheme_department ADD sd_deptid INT(11) NOT NULL AFTER sd_id;
drop index sd_name on scheme_department;
create index sd_name on scheme_department (sd_name,sd_deptid);


