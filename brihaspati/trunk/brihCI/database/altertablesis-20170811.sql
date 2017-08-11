use payroll;
ALTER TABLE  employee_master ADD emp_specialisationid int(11) NOT NULL AFTER emp_qual;
ALTER TABLE  employee_master ADD emp_uocid int(11) NOT NULL AFTER emp_aadhaar_no;
ALTER TABLE  employee_master ADD emp_schemeid int(11) NOT NULL AFTER emp_uocid;
ALTER TABLE  employee_master ADD emp_community VARCHAR(255) default NULL AFTER emp_gender;
ALTER TABLE  employee_master ADD emp_religion VARCHAR(255) default NULL AFTER emp_community;
ALTER TABLE  employee_master ADD emp_caste VARCHAR(255) default NULL AFTER emp_religion;
ALTER TABLE  employee_master ADD emp_mothertongue VARCHAR(255) default NULL AFTER emp_caste;
ALTER TABLE  employee_master ADD emp_emolution int(11) default 0 AFTER emp_basic;
ALTER TABLE  employee_master ADD emp_dateofHGP date default NULL AFTER emp_dateofAssrExam;
ALTER TABLE  employee_master ADD emp_pnp VARCHAR(255) default NULL AFTER emp_salary_grade;
ALTER TABLE  employee_master ADD emp_post VARCHAR(255) default NULL AFTER emp_desig_code;
ALTER TABLE  employee_master ADD emp_nhisidno VARCHAR(255) default NULL AFTER emp_schemeid;

