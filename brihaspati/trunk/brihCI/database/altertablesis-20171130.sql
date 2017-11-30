use payroll;
ALTER TABLE  user_input_transfer ADD uit_emptypeto VARCHAR(255) default NULL AFTER uit_emptype;
ALTER TABLE  user_input_transfer ADD uit_schm_from INT(11) default NULL AFTER uit_post_to;
ALTER TABLE  user_input_transfer ADD uit_schm_to INT(11) default NULL AFTER uit_schm_from;
ALTER TABLE  employee_master ADD emp_doprobation date default NULL AFTER emp_yop;
ALTER TABLE  employee_master ADD emp_doregular date default NULL AFTER emp_doprobation;
ALTER TABLE  employee_master ADD emp_remarks blob default NULL AFTER emp_org_code;

