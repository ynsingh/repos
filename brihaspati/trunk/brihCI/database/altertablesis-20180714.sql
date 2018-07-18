 alter table employee_servicedetail add empsd_filename varchar(500) default null;
 alter table employee_servicedetail add empsd_fsession varchar(100) default null;
 alter table employee_servicedetail add empsd_tsession varchar(100) default null;
 alter table employee_master add emp_jsession varchar(100) default null after emp_doj;
 alter table employee_master add emp_spousename varchar(255) default null after emp_father;
 alter table employee_master change emp_father emp_father varchar(255) null default null ;
ALTER TABLE `employee_master` CHANGE `emp_seniortyid` `emp_seniortyid` INT(10) NULL DEFAULT NULL;
