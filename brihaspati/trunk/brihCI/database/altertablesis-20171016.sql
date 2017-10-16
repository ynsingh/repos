use payroll;
ALTER TABLE  employee_master ADD emp_ddoid INT(11) NOT NULL AFTER emp_nhisidno;
ALTER TABLE  employee_master ADD emp_group  VARCHAR(10) default NULL AFTER emp_ddoid;
ALTER TABLE  employee_master ADD emp_apporderno  VARCHAR(255) default NULL AFTER emp_group;
ALTER TABLE  employee_master ADD emp_phstatus VARCHAR(10) default NULL AFTER emp_apporderno;
ALTER TABLE  employee_master ADD emp_phdetail VARCHAR(255) default NULL AFTER emp_phstatus;
ALTER TABLE  employee_master ADD emp_bloodgroup  VARCHAR(10) default NULL AFTER emp_phdetail;
ALTER TABLE  employee_master ADD emp_photoname  VARCHAR(255) default NULL AFTER emp_bloodgroup;

