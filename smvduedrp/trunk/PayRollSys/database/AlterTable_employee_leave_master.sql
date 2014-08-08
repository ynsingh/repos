use pl;

ALTER TABLE employee_leave_master ADD el_applied_date  date NOT NULL;
ALTER TABLE employee_leave_master ADD el_approval_date  date default NULL;
ALTER TABLE employee_leave_master ADD el_approval_status  int(11) NOT NULL;
ALTER TABLE employee_leave_master ADD el_org_id  int(11) NOT NULL;
