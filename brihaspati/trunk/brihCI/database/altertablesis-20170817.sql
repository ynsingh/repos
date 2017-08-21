use payroll;
ALTER TABLE  employee_master ADD emp_worktype VARCHAR(255) default NULL AFTER emp_post;
ALTER TABLE  employee_master change emp_type_code emp_type_code VARCHAR(255) default NULL AFTER emp_worktype;
ALTER TABLE  employee_master change emp_gender emp_gender VARCHAR(255) default NULL AFTER  emp_pan_no;
ALTER TABLE  employee_master change emp_citizen emp_citizen VARCHAR(255) default NULL AFTER  emp_noti_day;

