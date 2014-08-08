CREATE TABLE employee_service_history (
	esh_emp_id int(11) NOT NULL auto_increment,
        esh_emp_code varchar(30) NOT NULL,
        esh_transactiontype varchar(100) NOT NULL,
        esh_tooffice varchar(70) NOT NULL,
        esh_towhichpost varchar(30) NOT NULL,
        esh_class varchar(30) NOT NULL,
        esh_ordernumber varchar(30) NOT NULL,
        esh_orderdate date default Null,
        esh_dofincrement date default Null,
        esh_payscale int(11) NOT NULL,
        esh_dept_deputation varchar(20) NOT NULL,
        esh_areatype varchar(20) NOT NULL,
        esh_org_id int(11) NOT NULL,
	PRIMARY KEY  (`esh_emp_id`),
        KEY esh_emp_code (esh_emp_code),
        KEY esh_org_id (esh_org_id),
        CONSTRAINT `employee_service_history_fk1` FOREIGN KEY (`esh_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_service_history_bfk_1` FOREIGN KEY (`esh_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);
