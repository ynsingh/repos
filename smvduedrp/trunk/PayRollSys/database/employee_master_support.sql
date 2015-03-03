use pl;
CREATE TABLE employee_master_support (
 id int(11) NOT NULL auto_increment,
 code varchar (50) NOT NULL,
 entitled_cat varchar (50) NOT NULL,
 status_emp varchar (50) NOT NULL,
 working_type varchar (100) NOT NULL,
 sal_dept_code int (11) NOT NULL,
 joining_dept int (11)  NOT NULL,
 joined_desig int (11)  NOT NULL,
 gpf_no varchar (50)  NULL,
 nps_no varchar (100)  NULL,
 dps_no varchar (100)  NULL,
 house_type varchar (100)  NULL,
 house_no varchar (100)  NULL,
 ecr_no varchar (100)  NULL,
 page_no varchar (100)  NULL,
 posting_id varchar (100)  NULL,
 lic_policy_no varchar (100) default NULL,
 lic_doa date default NULL,
 lic_dom date default NULL,
 gi_policy_no varchar (100) default NULL,
 gi_doa date  default NULL,
 gi_dom date default NULL,
 nextincrement_date date  default NULL,
 probation_date date default  NULL,
 confirmation_date date  default NULL,
 extention_date date default  NULL,
 PRIMARY KEY  (id),
 UNIQUE KEY  (code),
 CONSTRAINT `employee_master_support_emsfk_1` FOREIGN KEY (`code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB DEFAULT CHARSET=latin1;
