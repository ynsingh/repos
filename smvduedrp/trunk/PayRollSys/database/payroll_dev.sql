# EMS MySQL Manager Lite 3.3.0.4
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : payroll_dev


SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `payroll_dev`;

CREATE DATABASE `payroll_dev`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `payroll_dev`;

#
# Structure for the `default_salary_master` table : 
#

CREATE TABLE `default_salary_master` (
  `ds_emp_type` int(11) NOT NULL,
  `ds_sal_head` int(11) default NULL,
  `ds_amount` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `department_master` table : 
#

CREATE TABLE `department_master` (
  `dept_code` int(11) NOT NULL auto_increment,
  `dept_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`dept_code`),
  UNIQUE KEY `dept_name` (`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `designation_master` table : 
#

CREATE TABLE `designation_master` (
  `desig_code` int(11) NOT NULL auto_increment,
  `desig_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`desig_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_type_master` table : 
#

CREATE TABLE `employee_type_master` (
  `emp_type_id` int(11) NOT NULL auto_increment,
  `emp_type_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`emp_type_id`,`emp_type_name`),
  UNIQUE KEY `emp_type_name` (`emp_type_name`),
  KEY `emp_type_id` (`emp_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_head_master` table : 
#

CREATE TABLE `salary_head_master` (
  `sh_id` int(11) NOT NULL auto_increment,
  `sh_name` varchar(100) NOT NULL,
  `sh_type` tinyint(4) NOT NULL default '1',
  `sh_alias` varchar(10) default NULL,
  `sh_calc_type` tinyint(4) NOT NULL default '1',
  `sh_formula` varchar(100) default NULL,
  PRIMARY KEY  (`sh_id`),
  UNIQUE KEY `sh_name` (`sh_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `emp_salary_head_master` table : 
#

CREATE TABLE `emp_salary_head_master` (
  `st_code` int(11) NOT NULL,
  `st_sal_code` int(11) NOT NULL,
  UNIQUE KEY `st_code` (`st_code`,`st_sal_code`),
  KEY `st_code_2` (`st_code`),
  KEY `st_sal_code` (`st_sal_code`),
  CONSTRAINT `emp_salary_head_master_ibfk_1` FOREIGN KEY (`st_code`) REFERENCES `employee_type_master` (`emp_type_id`),
  CONSTRAINT `emp_salary_head_master_ibfk_2` FOREIGN KEY (`st_sal_code`) REFERENCES `salary_head_master` (`sh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_grade_master` table : 
#

CREATE TABLE `salary_grade_master` (
  `grd_code` int(11) NOT NULL auto_increment,
  `grd_name` varchar(20) NOT NULL,
  `grd_max` int(11) NOT NULL default '0',
  `grd_min` int(11) NOT NULL default '0',
  PRIMARY KEY  (`grd_code`,`grd_name`),
  KEY `grd_code` (`grd_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_master` table : 
#

CREATE TABLE `employee_master` (
  `emp_code` varchar(50) NOT NULL,
  `emp_name` varchar(70) NOT NULL,
  `emp_dept_code` int(11) NOT NULL,
  `emp_desig_code` int(11) NOT NULL,
  `emp_type_code` int(11) NOT NULL,
  `emp_phone` varchar(30) default NULL,
  `emp_email` varchar(30) default NULL,
  `emp_dob` date default NULL,
  `emp_doj` date default NULL,
  `emp_id` int(11) NOT NULL auto_increment,
  `emp_salary_grade` int(11) NOT NULL,
  `emp_bank_accno` varchar(20) default NULL,
  `emp_pf_accno` varchar(20) default NULL,
  `emp_pan_no` varchar(20) default NULL,
  `emp_gender` tinyint(4) NOT NULL default '1',
  PRIMARY KEY  (`emp_code`),
  UNIQUE KEY `emp_code` (`emp_code`),
  UNIQUE KEY `emp_id` (`emp_id`),
  KEY `emp_dept_code` (`emp_dept_code`),
  KEY `emp_desig_code` (`emp_desig_code`),
  KEY `emp_type_code` (`emp_type_code`),
  KEY `emp_salary_grade` (`emp_salary_grade`),
  CONSTRAINT `employee_master_ibfk_1` FOREIGN KEY (`emp_dept_code`) REFERENCES `department_master` (`dept_code`) ON UPDATE CASCADE,
  CONSTRAINT `employee_master_ibfk_2` FOREIGN KEY (`emp_desig_code`) REFERENCES `designation_master` (`desig_code`) ON UPDATE CASCADE,
  CONSTRAINT `employee_master_ibfk_3` FOREIGN KEY (`emp_type_code`) REFERENCES `employee_type_master` (`emp_type_id`) ON UPDATE CASCADE,
  CONSTRAINT `employee_master_ibfk_4` FOREIGN KEY (`emp_salary_grade`) REFERENCES `salary_grade_master` (`grd_code`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `investment_heads` table : 
#

CREATE TABLE `investment_heads` (
  `ih_id` int(11) NOT NULL auto_increment,
  `ih_name` varchar(100) NOT NULL,
  `ih_benefit` tinyint(4) default '0',
  `ih_details` varchar(250) default NULL,
  PRIMARY KEY  (`ih_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `investment_plan_master` table : 
#

CREATE TABLE `investment_plan_master` (
  `ip_id` int(11) NOT NULL auto_increment,
  `ip_emp_id` int(11) NOT NULL,
  `ip_ins_id` int(11) NOT NULL,
  `ip_amount` int(11) NOT NULL default '0',
  PRIMARY KEY  (`ip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `org_profile` table : 
#

CREATE TABLE `org_profile` (
  `org_id` int(11) NOT NULL auto_increment,
  `org_name` varchar(100) NOT NULL,
  `org_tagline` varchar(100) default NULL,
  `org_email` varchar(100) default NULL,
  `org_web` varchar(100) default NULL,
  `org_phone` varchar(35) default NULL,
  `org_address1` varchar(100) default NULL,
  `org_address2` varchar(100) default NULL,
  PRIMARY KEY  (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `pf_contribution_master` table : 
#

CREATE TABLE `pf_contribution_master` (
  `pc_id` int(11) NOT NULL auto_increment,
  `pc_name` varchar(100) NOT NULL,
  `pc_amount` int(11) NOT NULL,
  `pf_type` tinyint(4) NOT NULL,
  PRIMARY KEY  (`pc_id`),
  UNIQUE KEY `pc_name` (`pc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_data` table : 
#

CREATE TABLE `salary_data` (
  `sd_emp_code` varchar(30) NOT NULL,
  `sd_head_code` int(11) NOT NULL,
  `sd_date` date NOT NULL,
  `sd_amount` int(11) NOT NULL default '0',
  KEY `sd_emp_code` (`sd_emp_code`),
  KEY `sd_head_code` (`sd_head_code`),
  CONSTRAINT `salary_data_ibfk_2` FOREIGN KEY (`sd_head_code`) REFERENCES `salary_head_master` (`sh_id`) ON DELETE CASCADE,
  CONSTRAINT `salary_data_ibfk_3` FOREIGN KEY (`sd_emp_code`) REFERENCES `employee_master` (`emp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_formula` table : 
#

CREATE TABLE `salary_formula` (
  `sf_sal_id` int(11) NOT NULL,
  `sf_sal_formula` varchar(100) NOT NULL,
  KEY `sf_sal_id` (`sf_sal_id`),
  CONSTRAINT `salary_formula_ibfk_1` FOREIGN KEY (`sf_sal_id`) REFERENCES `salary_head_master` (`sh_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_profile_master` table : 
#

CREATE TABLE `salary_profile_master` (
  `sp_id` int(11) NOT NULL auto_increment,
  `sp_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`sp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_profile_data` table : 
#

CREATE TABLE `salary_profile_data` (
  `sd_sp_d` int(11) NOT NULL,
  `sd_sal_id` int(11) NOT NULL,
  KEY `sd_sp_d` (`sd_sp_d`),
  KEY `sd_sal_id` (`sd_sal_id`),
  CONSTRAINT `salary_profile_data_ibfk_1` FOREIGN KEY (`sd_sp_d`) REFERENCES `salary_profile_master` (`sp_id`) ON DELETE CASCADE,
  CONSTRAINT `salary_profile_data_ibfk_2` FOREIGN KEY (`sd_sal_id`) REFERENCES `salary_head_master` (`sh_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `temp` table : 
#

CREATE TABLE `temp` (
  `ids` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_master` table : 
#

CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(100) NOT NULL,
  `user_pass` varchar(20) NOT NULL,
  `user_admin` tinyint(4) default '0',
  PRIMARY KEY  (`user_id`,`user_name`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `default_salary_master` table  (LIMIT 0,500)
#

INSERT INTO `default_salary_master` (`ds_emp_type`, `ds_sal_head`, `ds_amount`) VALUES 
  (1,1,8000),
  (1,2,4500),
  (1,3,2000),
  (1,5,1200),
  (1,6,6000);

COMMIT;

#
# Data for the `department_master` table  (LIMIT 0,500)
#

INSERT INTO `department_master` (`dept_code`, `dept_name`) VALUES 
  (10,'ABC'),
  (8,'Abcde'),
  (2,'Academic'),
  (3,'Administration'),
  (6,'Asst. Professor'),
  (5,'cxvbxcfg'),
  (4,'Estate'),
  (1,'Finance'),
  (9,'kbkjjh'),
  (7,'Library'),
  (13,'PQR'),
  (12,'sadvasd'),
  (11,'XYZ');

COMMIT;

#
# Data for the `designation_master` table  (LIMIT 0,500)
#

INSERT INTO `designation_master` (`desig_code`, `desig_name`) VALUES 
  (1,'Asst. Professor'),
  (2,'Lecturer'),
  (3,'Reader'),
  (4,'Registrar'),
  (5,'Vice Chancellor'),
  (6,'Accountant'),
  (7,'Sushant'),
  (8,'Ranjeet');

COMMIT;

#
# Data for the `employee_type_master` table  (LIMIT 0,500)
#

INSERT INTO `employee_type_master` (`emp_type_id`, `emp_type_name`) VALUES 
  (1,'Regular'),
  (2,'Contractual'),
  (3,'Abcde');

COMMIT;

#
# Data for the `salary_head_master` table  (LIMIT 0,500)
#

INSERT INTO `salary_head_master` (`sh_id`, `sh_name`, `sh_type`, `sh_alias`, `sh_calc_type`, `sh_formula`) VALUES 
  (1,'Basic',1,'base',0,NULL),
  (2,'DA',1,'da',1,NULL),
  (3,'TA',1,'ta',1,NULL),
  (4,'Transport',0,'tpt',0,NULL),
  (5,'HRA',1,'hra',1,'base*0.15'),
  (6,'Arears',0,'ARR',0,NULL),
  (7,'Test 12345',0,'test',1,NULL),
  (8,'Basic settings etc',0,'basic abc',1,NULL),
  (9,'Grade Pay',0,'gp',0,NULL),
  (10,'NPA',0,'npa',0,NULL),
  (11,'XPA',0,'xpa',0,NULL),
  (101,'Gross',1,'Gross Sala',1,NULL),
  (102,'Loans',0,'loan',0,NULL),
  (103,'Advances',0,'adv',0,NULL);

COMMIT;

#
# Data for the `emp_salary_head_master` table  (LIMIT 0,500)
#

INSERT INTO `emp_salary_head_master` (`st_code`, `st_sal_code`) VALUES 
  (1,1),
  (1,2),
  (1,3),
  (1,5),
  (1,6);

COMMIT;

#
# Data for the `salary_grade_master` table  (LIMIT 0,500)
#

INSERT INTO `salary_grade_master` (`grd_code`, `grd_name`, `grd_max`, `grd_min`) VALUES 
  (1,'S1',0,0),
  (2,'S2',0,0),
  (208,'S3',0,0),
  (209,'S4',0,0),
  (210,'S5',0,0),
  (211,'S6',0,0),
  (212,'S7',0,0),
  (213,'S8',0,0),
  (214,'S9',0,0),
  (215,'S-10',23000,20000),
  (216,'S-11',28000,24000);

COMMIT;

#
# Data for the `employee_master` table  (LIMIT 0,500)
#

INSERT INTO `employee_master` (`emp_code`, `emp_name`, `emp_dept_code`, `emp_desig_code`, `emp_type_code`, `emp_phone`, `emp_email`, `emp_dob`, `emp_doj`, `emp_id`, `emp_salary_grade`, `emp_bank_accno`, `emp_pf_accno`, `emp_pan_no`, `emp_gender`) VALUES 
  ('EMP001','Sushant Kumar',1,2,1,'9906135075','sushant001@gmail.com','1985-02-13','2010-02-06',1,1,'2161','EPF901','13770834582QWR',1),
  ('EMP002','Ranjeet Kumar Jha',1,2,1,'9906135075','jhajba@gmail.com','1985-02-13','2010-02-06',2,1,'8322','EPF1982','543543523452DDR',1),
  ('EMP003','Chandan Kumar',1,2,1,'89696869',NULL,NULL,NULL,3,1,'5634','438573','58348543HHH',1),
  ('EMP006','NAVINDU KUMAR',10,1,1,'342523','345234523','1980-03-03','2010-04-04',4,1,'325','242','52345',1),
  ('EMP010','AMIT JHA',3,4,1,'9906135075','sushant001@gmail.com','1967-04-04','2009-04-04',5,215,'7865','7220','QEA898989988',1);

COMMIT;

#
# Data for the `investment_heads` table  (LIMIT 0,500)
#

INSERT INTO `investment_heads` (`ih_id`, `ih_name`, `ih_benefit`, `ih_details`) VALUES 
  (1,'HOUSING LOANS',1,'Allows IT Benefit under Sec 80 upto 15 % on the overall amount'),
  (2,'INSURANCE',1,'Allows IT Benefit under Sec 80 upto 12 % on the overall amount'),
  (3,'EDUCATION LOAN',1,'Education loan is rebated under Sec 80(c) max upto Rs 10 lakh');

COMMIT;

#
# Data for the `investment_plan_master` table  (LIMIT 0,500)
#

INSERT INTO `investment_plan_master` (`ip_id`, `ip_emp_id`, `ip_ins_id`, `ip_amount`) VALUES 
  (1,1,1,50000),
  (2,1,2,70000);

COMMIT;

#
# Data for the `org_profile` table  (LIMIT 0,500)
#

INSERT INTO `org_profile` (`org_id`, `org_name`, `org_tagline`, `org_email`, `org_web`, `org_phone`, `org_address1`, `org_address2`) VALUES 
  (1,'IGNOU','Vigyanam Brahm','info@smvdu.net.in','smvdu.net.in','8947689234769','Katra, ','Jammu & Kashmir');

COMMIT;

#
# Data for the `salary_data` table  (LIMIT 0,500)
#

INSERT INTO `salary_data` (`sd_emp_code`, `sd_head_code`, `sd_date`, `sd_amount`) VALUES 
  ('EMP006',1,'2010-07-09',8000),
  ('EMP006',2,'2010-07-09',4500),
  ('EMP006',3,'2010-07-09',2000),
  ('EMP006',5,'2010-07-09',1200),
  ('EMP006',6,'2010-07-09',1800),
  ('EMP006',101,'2010-07-09',17500),
  ('EMP001',1,'2010-07-03',8000),
  ('EMP001',2,'2010-07-03',4800),
  ('EMP001',3,'2010-07-03',4000),
  ('EMP001',5,'2010-07-03',1200),
  ('EMP001',6,'2010-07-03',3000),
  ('EMP001',101,'2010-07-03',21000),
  ('EMP001',1,'2010-09-15',8002),
  ('EMP001',2,'2010-09-15',4500),
  ('EMP001',3,'2010-09-15',2000),
  ('EMP001',5,'2010-09-15',1200),
  ('EMP001',6,'2010-09-15',6000),
  ('EMP001',101,'2010-09-15',21702),
  ('EMP001',1,'2010-11-10',8000),
  ('EMP001',2,'2010-11-10',6500),
  ('EMP001',3,'2010-11-10',2000),
  ('EMP001',5,'2010-11-10',1200),
  ('EMP001',6,'2010-11-10',6000),
  ('EMP001',101,'2010-11-10',23700),
  ('EMP006',1,'2010-11-18',8000),
  ('EMP006',6,'2010-11-18',6000),
  ('EMP006',101,'2010-11-18',14000);

COMMIT;

#
# Data for the `salary_formula` table  (LIMIT 0,500)
#

INSERT INTO `salary_formula` (`sf_sal_id`, `sf_sal_formula`) VALUES 
  (4,'(basic+npa)*.45'),
  (2,'(basic+npa)*.47'),
  (6,'(basic+npa)*.45'),
  (5,'(basic+npa)*0.47');

COMMIT;

#
# Data for the `salary_profile_master` table  (LIMIT 0,500)
#

INSERT INTO `salary_profile_master` (`sp_id`, `sp_name`) VALUES 
  (1,'Regular'),
  (2,'Contractual'),
  (3,'Weekly'),
  (4,'BI WEEKLY'),
  (5,'TRIWEEKLY');

COMMIT;

#
# Data for the `user_master` table  (LIMIT 0,500)
#

INSERT INTO `user_master` (`user_id`, `user_name`, `user_pass`, `user_admin`) VALUES 
  (1,'admin','admin',1),
  (2,'test','test',0),
  (3,'System','system',0),
  (4,'System123','123456',1);

COMMIT;

