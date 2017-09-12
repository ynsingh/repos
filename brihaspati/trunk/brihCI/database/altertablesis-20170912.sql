use payroll;
ALTER TABLE  employee_master ADD emp_uocuserid int(11) NOT NULL AFTER emp_uocid;
ALTER TABLE  employee_master ADD emp_ddouserid int(11) NOT NULL AFTER emp_uocuserid;
