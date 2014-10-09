use pl;

ALTER TABLE department_master ADD dept_dcode varchar(80) NOT NULL UNIQUE after dept_code;
ALTER TABLE department_master ADD dept_nickname varchar(80) NOT NULL after dept_name;
