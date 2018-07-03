alter table employee_servicedetail add empsd_orderno varchar(100) default null after empsd_empid;
alter table employee_master add emp_maritalstatus varchar(100) default null after emp_vciregdate;
alter table employee_master add emp_seniortyid varchar(50) default null after emp_maritalstatus;

