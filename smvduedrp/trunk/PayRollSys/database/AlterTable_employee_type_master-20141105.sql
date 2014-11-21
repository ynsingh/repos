use pl;

ALTER TABLE employee_type_master ADD emp_tcode varchar(80) NOT NULL AFTER emp_type_id;
UPDATE employee_type_master SET emp_tcode=(select SUBSTR(emp_type_name, 1, 4));
UPDATE employee_type_master SET emp_tcode=(select CONCAT(emp_tcode, emp_type_id));
ALTER TABLE employee_type_master ADD emp_type_nickname varchar(80) AFTER emp_type_name;
ALTER TABLE employee_type_master ADD emp_maxpf_applies bigint(100) AFTER emp_pf_applies;
ALTER TABLE employee_type_master ADD CONSTRAINT uq_employee_type_master UNIQUE(emp_tcode,emp_org_id);

