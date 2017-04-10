use pl;

ALTER TABLE employee_leave_master MODIFY el_emp_code varchar(30);
ALTER TABLE employee_leave_master ADD el_Reasonforleave varchar(255) NOT NULL;
ALTER TABLE employee_leave_master ADD el_ContractNo varchar(255) default NULL;
ALTER TABLE employee_leave_master ADD el_Reporting_offcr varchar(255) default NULL;
ALTER TABLE employee_leave_master ADD el_Covering_offcr varchar(255) default NULL;
ALTER TABLE employee_leave_master ADD el_Commnts varchar(255) default NULL;

ALTER TABLE leave_quota_master ADD lq_org_id int(11) NOT NULL;



CREATE TABLE emp_leave_record (
        elr_id int(11) NOT NULL auto_increment,
        elr_emp_code varchar(30) NOT NULL,
        elr_leave_id int(11) NOT NULL,
        elr_count int(11) NOT NULL,
        elr_f_year varchar(100) NOT NULL ,
        PRIMARY KEY  (`elr_id`),
        UNIQUE KEY  (`elr_emp_code`, `elr_leave_id`, `elr_f_year` )

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

