CREATE TABLE employee_training_detail (
        etd_emp_id int(11) NOT NULL auto_increment,
        etd_emp_code varchar(30) NOT NULL,
        etd_trainingtype varchar(80) NOT NULL,
        etd_topicname varchar(80) NOT NULL,
        etd_institutename varchar(100) default NULL,
        etd_sponsoredby varchar(80) default NULL,
        etd_datefrom date default NULL,
        etd_dateto date default NULL,
        etd_org_id int(11) NOT NULL,
	PRIMARY KEY  (etd_emp_id),
        KEY etd_emp_code (etd_emp_code),
        KEY etd_org_id (etd_org_id),
        CONSTRAINT `employee_training_detail_fk1` FOREIGN KEY (`etd_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_training_detail_ibfk_1` FOREIGN KEY (`etd_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);

