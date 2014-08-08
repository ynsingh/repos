CREATE TABLE employee_education_detail (
        edu_emp_id int(11) NOT NULL auto_increment,
        edu_emp_code varchar(30) NOT NULL,
        edu_exam_passed varchar(40) NOT NULL,
        edu_board_university varchar(100) NOT NULL,
        edu_marksobtained int(100) NOT NULL,
        edu_passingYear int(30) NOT NULL,
        edu_grade varchar(70) default NULL,
        edu_org_id int(11) NOT NULL,
	PRIMARY KEY  (edu_emp_id),
        KEY edu_emp_code (edu_emp_code),
        KEY edu_org_id (edu_org_id),
        CONSTRAINT `employee_education_detail_fk1` FOREIGN KEY (`edu_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_education_detail_ibfk_1` FOREIGN KEY (`edu_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);

