CREATE TABLE employee_working_days_master (
	id int(11) NOT NULL auto_increment,
        att_emp_code varchar(55) NOT NULL,
        working_days int(11) NOT NULL default '0',
        month int(11) NOT NULL,
        year int(11) NOT NULL,
        org_code int(11) NOT NULL,
	PRIMARY KEY  (`id`),
	UNIQUE KEY (`att_emp_code`,`month`),
        KEY `org_code` (`org_code`),
        CONSTRAINT `employee_working_days_master_fk1` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_working_days_master_ibfk_1` FOREIGN KEY (`att_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);
