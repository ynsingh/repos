CREATE TABLE employee_family_record (
	efr_id int(11) NOT NULL auto_increment,
        efr_emp_code varchar(30) NOT NULL,
        efr_membername varchar(100) NOT NULL,
        efr_relation varchar(30) NOT NULL,
        efr_dob date default NULL,
        efr_dependent varchar(10) NOT NULL,
        efr_whetheremployed varchar(20) NOT NULL,
        efr_department varchar(70) NULL,
        efr_org_id int(11) NOT NULL,
	PRIMARY KEY  (`efr_id`),
        KEY efr_emp_code (efr_emp_code),
        KEY efr_org_id (efr_org_id),
        CONSTRAINT `employee_family_record_fk1` FOREIGN KEY (`efr_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_family_record_ibfk_1` FOREIGN KEY (`efr_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);
