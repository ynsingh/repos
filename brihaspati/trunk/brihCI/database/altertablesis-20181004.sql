alter table employee_master_support add ems_pensioncontri varchar(100) default null after ems_extention_date;
alter table employee_master_support add ems_upfno varchar(256) default null after ems_pensioncontri;
alter table employee_master_support add ems_universityemp varchar(100) default null after ems_upfno;
alter table employee_master_support add ems_washingallowance varchar(100) default null after ems_universityemp;
alter table employee_master_support add ems_deductupf varchar(100) default null after ems_washingallowance;
alter table employee_master_support add ems_hragrade varchar(256) default null after ems_deductupf;
alter table employee_master_support add ems_ccagrade varchar(256) default null after ems_hragrade;
alter table employee_master_support add ems_inclsummary varchar(256) default null after ems_ccagrade;
alter table employee_master_support add ems_lic1no varchar(256) default null after ems_inclsummary;
alter table employee_master_support add ems_lic1amount double  DEFAULT 0 after ems_lic1no;
alter table employee_master_support add ems_lic2no varchar(256) default null after ems_lic1amount;
alter table employee_master_support add ems_lic2amount double  DEFAULT 0 after ems_lic2no;
alter table employee_master_support add ems_lic3no varchar(256) default null after ems_lic2amount;
alter table employee_master_support add ems_lic3amount double  DEFAULT 0 after ems_lic3no;
alter table employee_master_support add ems_lic4no varchar(256) default null after ems_lic3amount;
alter table employee_master_support add ems_lic4amount double  DEFAULT 0 after ems_lic4no;
alter table employee_master_support add ems_lic5no varchar(256) default null after ems_lic4amount;
alter table employee_master_support add ems_lic5amount double  DEFAULT 0 after ems_lic5no;
alter table employee_master_support add ems_prdno1 varchar(256) default null after ems_lic5amount;
alter table employee_master_support add ems_prdno2 varchar(256) default null after ems_prdno1;
alter table employee_master_support add ems_prdno3 varchar(256) default null after ems_prdno2;
alter table employee_master_support add ems_plino1 varchar(256) default null after ems_prdno3;
alter table employee_master_support add ems_plino2 varchar(256) default null after ems_plino1;
alter table employee_master_support add ems_society varchar(256) default null after ems_plino2;
alter table employee_master_support add ems_societymember varchar(256) default null after ems_society;

