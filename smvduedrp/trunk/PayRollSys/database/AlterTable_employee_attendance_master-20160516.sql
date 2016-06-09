use pl;

ALTER TABLE employee_attendance_master ADD emp_absent int(11) NOT NULL default '0' after emp_present;
 
