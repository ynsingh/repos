use payroll;
alter table employee_servicedetail add empsd_gradepay varchar(100) DEFAULT NULL after empsd_pbid;
alter table employee_servicedetail add empsd_schemeid int(9) DEFAULT NULL after empsd_deptid;
alter table employee_servicedetail add empsd_ddoid int(9) DEFAULT NULL after empsd_schemeid;
alter table employee_servicedetail add empsd_group varchar(9) DEFAULT NULL after empsd_ddoid;
alter table employee_servicedetail add empsd_shagpstid varchar(9) DEFAULT NULL after empsd_group;
