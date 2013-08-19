# SQL Manager 2007 for MySQL 4.5.0.7
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : pl


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `pl`;

CREATE DATABASE `pl`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `pl`;

#
# Structure for the `admin_records` table : 
#

CREATE TABLE `admin_records` (
  `seq_id` int(11) NOT NULL auto_increment,
  `user_id` varchar(300) NOT NULL,
  `admin_pass` varchar(40) default NULL,
  `flag` tinyint(4) default NULL,
  `add_date` date default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `seq_id` (`seq_id`),
  UNIQUE KEY `seq_id_2` (`seq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `admin_email_config` table : 
#

CREATE TABLE `admin_email_config` (
  `admin_id` int(11) default NULL,
  `admin_emailid` varchar(300) NOT NULL,
  `admin_config_pass` varchar(100) default NULL,
  `admin_email_status` tinyint(4) default NULL,
  PRIMARY KEY  (`admin_emailid`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `admin_email_config_fk` FOREIGN KEY (`admin_id`) REFERENCES `admin_records` (`seq_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `admin_smtp_details` table : 
#

CREATE TABLE `admin_smtp_details` (
  `seq_id` int(11) NOT NULL auto_increment,
  `smtp_name` varchar(300) NOT NULL,
  `smtp_port` int(11) default NULL,
  `auth_emailid` varchar(300) default NULL,
  `auth_password` varchar(100) default NULL,
  `smtp_status` tinyint(4) default NULL,
  `smtp_host_name` varchar(200) default NULL,
  PRIMARY KEY  (`smtp_name`),
  UNIQUE KEY `seq_id` (`seq_id`)
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
  `org_logo` mediumblob,
  `org_master_password` varchar(200) default NULL,
  `org_recovery_id` varchar(200) default NULL,
  `org_tanno` varchar(20) default NULL,
  `org_city` varchar(40) default NULL,
  `org_pincode` int(11) NOT NULL,
  `org_state` varchar(40) default NULL,
  `org_ll` int(20) NOT NULL,
  `org_countrycode` int(11) default NULL,
  `org_regioncode` int(11) NOT NULL,
  `org_institutedomain` varchar(20) default NULL,
  `org_toi` varchar(20) default NULL,
  `org_affiliation` varchar(40) default NULL,
  `org_adminfn` varchar(30) default NULL,
  `org_adminln` varchar(40) default NULL,
  `org_admindesig` varchar(40) default NULL,
  `org_reg_noti` int(11) default NULL,
  `org_status` tinyint(4) default NULL,
  `org_reg_date` date default NULL,
  PRIMARY KEY  (`org_id`),
  UNIQUE KEY `org_name` (`org_name`),
  UNIQUE KEY `org_name_2` (`org_name`),
  UNIQUE KEY `org_email` (`org_email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `department_master` table : 
#

CREATE TABLE `department_master` (
  `dept_code` int(11) NOT NULL auto_increment,
  `dept_name` varchar(100) NOT NULL,
  `org_code` int(11) default NULL,
  PRIMARY KEY  (`dept_code`),
  UNIQUE KEY `dept_name` (`dept_name`),
  KEY `org_code` (`org_code`),
  CONSTRAINT `department_master_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `designation_master` table : 
#

CREATE TABLE `designation_master` (
  `desig_code` int(11) NOT NULL auto_increment,
  `desig_name` varchar(100) NOT NULL,
  `d_org_id` int(11) NOT NULL default '1',
  PRIMARY KEY  (`desig_code`),
  KEY `d_org_id` (`d_org_id`),
  CONSTRAINT `designation_master_ibfk_1` FOREIGN KEY (`d_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_grade_master` table : 
#

CREATE TABLE `salary_grade_master` (
  `grd_code` int(11) NOT NULL auto_increment,
  `grd_name` varchar(20) NOT NULL,
  `grd_max` int(11) NOT NULL default '0',
  `grd_min` int(11) NOT NULL default '0',
  `grd_gp` int(11) NOT NULL default '5000',
  `grd_org_id` int(11) NOT NULL default '1',
  PRIMARY KEY  (`grd_code`,`grd_name`),
  KEY `grd_code` (`grd_code`),
  KEY `grd_org_id` (`grd_org_id`),
  CONSTRAINT `salary_grade_master_fk1` FOREIGN KEY (`grd_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_master` table : 
#

CREATE TABLE `employee_master` (
  `emp_code` varchar(30) NOT NULL,
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
  `emp_org_code` int(11) NOT NULL default '0',
  `emp_father` varchar(100) default NULL,
  `emp_basic` int(11) NOT NULL default '0',
  `emp_title` varchar(50) NOT NULL default 'Prof.',
  `emp_exp` int(11) NOT NULL,
  `emp_qual` varchar(100) NOT NULL,
  `emp_yop` int(11) NOT NULL,
  `emp_prev_emp` varchar(200) NOT NULL,
  `emp_address` varchar(200) NOT NULL,
  `emp_active` tinyint(4) default '1',
  `bank_ifsc_code` varchar(500) default NULL,
  `emp_bank_status` tinyint(1) default '0',
  `dor` varchar(100) default NULL,
  `emp_leaving` varchar(100) default NULL,
  `emp_noti_day` int(11) default NULL,
  `citizen` tinyint(4) default NULL,
  PRIMARY KEY  (`emp_code`),
  UNIQUE KEY `emp_id` (`emp_id`),
  UNIQUE KEY `emp_code_org` (`emp_code`,`emp_org_code`),
  KEY `emp_dept_code` (`emp_dept_code`),
  KEY `emp_desig_code` (`emp_desig_code`),
  KEY `emp_type_code` (`emp_type_code`),
  KEY `emp_salary_grade` (`emp_salary_grade`),
  KEY `mp_org_code` (`emp_org_code`),
  KEY `bank_ifsc_code` (`bank_ifsc_code`),
  KEY `emp_email` (`emp_email`),
  CONSTRAINT `employee_master_ibfk_1` FOREIGN KEY (`emp_dept_code`) REFERENCES `department_master` (`dept_code`) ON UPDATE CASCADE,
  CONSTRAINT `employee_master_ibfk_2` FOREIGN KEY (`emp_desig_code`) REFERENCES `designation_master` (`desig_code`) ON UPDATE CASCADE,
  CONSTRAINT `employee_master_ibfk_4` FOREIGN KEY (`emp_salary_grade`) REFERENCES `salary_grade_master` (`grd_code`) ON UPDATE CASCADE,
  CONSTRAINT `employee_master_ibfk_5` FOREIGN KEY (`emp_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `session_master` table : 
#

CREATE TABLE `session_master` (
  `ss_id` int(11) NOT NULL auto_increment,
  `ss_name` varchar(100) NOT NULL,
  `ss_start_from` date NOT NULL,
  `ss_end_to` date NOT NULL,
  `ss_current` tinyint(4) NOT NULL default '0',
  `ss_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`ss_id`),
  UNIQUE KEY `ss_name` (`ss_name`),
  KEY `ss_org_id` (`ss_org_id`),
  CONSTRAINT `session_master_fk` FOREIGN KEY (`ss_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `annual_pf_master` table : 
#

CREATE TABLE `annual_pf_master` (
  `apf_id` int(11) NOT NULL auto_increment,
  `apf_emp_id` int(11) NOT NULL,
  `apf_op_bal` int(11) NOT NULL default '0',
  `apf_interest` float(9,3) default '0.000',
  `apf_closing_bal` float(9,3) default '0.000',
  `apf_sess_id` int(11) NOT NULL,
  `apf_recovery` int(11) NOT NULL default '0',
  `apf_emp_contribution` float(9,3) default '0.000',
  `apf_empl_contribution` float(9,3) default '0.000',
  `apf_withdrawal` int(11) default '0',
  `apf_interest_opbal` int(11) default '0',
  `apf_cum_amount_empl` int(11) default NULL,
  `apf_cum_amount_emp` int(11) default NULL,
  `apf_interest_deposite` int(11) default '0',
  `apf_int_opbal_emp` int(11) NOT NULL default '0',
  `apf_int_opbal_empr` int(11) NOT NULL default '0',
  `apf_int_dep_emp` int(11) default NULL,
  `apf_int_dep_empr` int(11) default NULL,
  `apf_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`apf_id`),
  KEY `apf_emp_id` (`apf_emp_id`),
  KEY `apf_sess_id` (`apf_sess_id`),
  KEY `apf_org_id` (`apf_org_id`),
  CONSTRAINT `annual_pf_master_fk` FOREIGN KEY (`apf_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `annual_pf_master_ibfk_1` FOREIGN KEY (`apf_emp_id`) REFERENCES `employee_master` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `annual_pf_master_ibfk_2` FOREIGN KEY (`apf_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `attendance_master` table : 
#

CREATE TABLE `attendance_master` (
  `att_id` int(11) NOT NULL auto_increment,
  `att_emp_id` varchar(40) NOT NULL,
  `att_date` date NOT NULL,
  `att_time_in` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `att_time_out` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`att_id`),
  KEY `att_emp_id` (`att_emp_id`),
  CONSTRAINT `attendance_master_ibfk_1` FOREIGN KEY (`att_emp_id`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `bankprofile` table : 
#

CREATE TABLE `bankprofile` (
  `seq_no` int(11) NOT NULL auto_increment,
  `bank_name` varchar(130) NOT NULL,
  `bank_address` varchar(1000) NOT NULL,
  `bank_ifsc_code` varchar(100) NOT NULL,
  `branch_name` varchar(100) NOT NULL,
  `org_code` int(11) NOT NULL,
  PRIMARY KEY  (`bank_ifsc_code`),
  UNIQUE KEY `seq_no` (`seq_no`),
  UNIQUE KEY `seq_no_2` (`seq_no`),
  KEY `org_code` (`org_code`),
  CONSTRAINT `bankprofile_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `client_details` table : 
#

CREATE TABLE `client_details` (
  `seq_no` int(11) NOT NULL auto_increment,
  `ip_address` varchar(20) default NULL,
  `port` varchar(20) default NULL,
  `client_org_code` int(11) default NULL,
  `flag` tinyint(4) default NULL,
  UNIQUE KEY `seq_no` (`seq_no`),
  KEY `client_org_code` (`client_org_code`),
  CONSTRAINT `client_details_fk` FOREIGN KEY (`client_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `college_pending_status` table : 
#

CREATE TABLE `college_pending_status` (
  `org_code` int(11) default NULL,
  `org_request_status` tinyint(4) default NULL,
  `org_pen_email` varchar(300) NOT NULL,
  PRIMARY KEY  (`org_pen_email`),
  KEY `org_code` (`org_code`),
  KEY `org_email` (`org_pen_email`),
  CONSTRAINT `college_pending_status_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `date_master` table : 
#

CREATE TABLE `date_master` (
  `t_date` date NOT NULL,
  `t_month` int(11) NOT NULL,
  `t_year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `default_salary_master` table : 
#

CREATE TABLE `default_salary_master` (
  `ds_emp_type` int(11) NOT NULL,
  `ds_sal_head` int(11) default NULL,
  `ds_amount` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `emp_hirarchy_master` table : 
#

CREATE TABLE `emp_hirarchy_master` (
  `hr_id` int(11) NOT NULL auto_increment,
  `hr_parent_id` varchar(50) NOT NULL,
  `hr_chield_id` int(11) NOT NULL,
  PRIMARY KEY  (`hr_id`),
  KEY `hr_parent_id` (`hr_parent_id`),
  KEY `hr_chield_id` (`hr_chield_id`),
  CONSTRAINT `emp_hirarchy_master_ibfk_1` FOREIGN KEY (`hr_parent_id`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emp_hirarchy_master_ibfk_2` FOREIGN KEY (`hr_chield_id`) REFERENCES `employee_master` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `emp_salary_head_master` table : 
#

CREATE TABLE `emp_salary_head_master` (
  `st_code` int(11) NOT NULL,
  `st_sal_code` int(11) NOT NULL,
  `st_org_code` int(11) default NULL,
  UNIQUE KEY `st_code` (`st_code`,`st_sal_code`),
  KEY `st_code_2` (`st_code`),
  KEY `st_sal_code` (`st_sal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `emp_slab_head_master` table : 
#

CREATE TABLE `emp_slab_head_master` (
  `emp_gen_code` int(11) default NULL,
  `emp_slab_code` int(11) NOT NULL,
  `emp_slab_orgCode` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `emp_tax_master` table : 
#

CREATE TABLE `emp_tax_master` (
  `et_emp_id` varchar(50) NOT NULL,
  `et_year` int(11) NOT NULL,
  `et_amount` float(9,3) NOT NULL,
  `et_id` int(11) NOT NULL auto_increment,
  `et_effective` int(11) NOT NULL,
  `et_percent` double(15,2) NOT NULL,
  `et_educess` float(9,2) NOT NULL,
  `et_sess_id` int(11) NOT NULL,
  `et_org_code` int(11) NOT NULL,
  PRIMARY KEY  (`et_id`),
  KEY `et_emp_id` (`et_emp_id`),
  KEY `et_sess_id` (`et_sess_id`),
  KEY `et_org_code` (`et_org_code`),
  CONSTRAINT `emp_tax_master_fk2` FOREIGN KEY (`et_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emp_tax_master_fk4` FOREIGN KEY (`et_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emp_tax_master_ibfk_1` FOREIGN KEY (`et_emp_id`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_basic_master` table : 
#

CREATE TABLE `employee_basic_master` (
  `eb_id` int(11) NOT NULL,
  `eb_basic` int(11) NOT NULL,
  PRIMARY KEY  (`eb_id`),
  UNIQUE KEY `eb_id` (`eb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_leave_master` table : 
#

CREATE TABLE `employee_leave_master` (
  `el_id` int(11) NOT NULL auto_increment,
  `el_emp_code` int(11) NOT NULL,
  `el_date_from` date NOT NULL,
  `el_date_to` date NOT NULL,
  `el_count` int(11) NOT NULL default '0',
  `el_quota_type` int(11) NOT NULL,
  PRIMARY KEY  (`el_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_login_master` table : 
#

CREATE TABLE `employee_login_master` (
  `el_id` varchar(100) NOT NULL,
  `el_login_name` varchar(100) NOT NULL,
  `el_password` varchar(30) NOT NULL,
  `el_org_id` int(11) NOT NULL default '1',
  PRIMARY KEY  (`el_login_name`),
  UNIQUE KEY `el_login_name` (`el_login_name`),
  KEY `el_org_id` (`el_org_id`),
  CONSTRAINT `employee_login_master_ibfk_1` FOREIGN KEY (`el_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_login_master_ibfk_2` FOREIGN KEY (`el_login_name`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_salary_summery` table : 
#

CREATE TABLE `employee_salary_summery` (
  `es_code` varchar(30) NOT NULL,
  `es_month` int(11) NOT NULL,
  `es_year` int(11) NOT NULL,
  `es_total_income` int(11) NOT NULL,
  `es_total_deduct` int(11) NOT NULL,
  `es_gross` int(11) NOT NULL,
  `es_last_update_date` date NOT NULL,
  `es_org_id` int(11) NOT NULL,
  `es_sess_id` int(11) NOT NULL default '1',
  KEY `es_code` (`es_code`),
  KEY `es_org_id` (`es_org_id`),
  KEY `es_sess_id` (`es_sess_id`),
  CONSTRAINT `employee_salary_summery_fk` FOREIGN KEY (`es_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_salary_summery_fk1` FOREIGN KEY (`es_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_salary_summery_ibfk_1` FOREIGN KEY (`es_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_type_master` table : 
#

CREATE TABLE `employee_type_master` (
  `emp_type_id` int(11) NOT NULL auto_increment,
  `emp_type_name` varchar(100) NOT NULL,
  `emp_pf_applies` tinyint(4) NOT NULL default '1',
  `emp_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`emp_type_id`,`emp_type_name`),
  KEY `emp_type_id` (`emp_type_id`),
  KEY `emp_org_id` (`emp_org_id`),
  KEY `emp_type_name` (`emp_type_name`),
  CONSTRAINT `employee_type_master_fk1` FOREIGN KEY (`emp_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `empoyee_team_master` table : 
#

CREATE TABLE `empoyee_team_master` (
  `et_id` int(11) NOT NULL auto_increment,
  `et_name` varchar(1) NOT NULL,
  `et_lead` int(11) NOT NULL,
  PRIMARY KEY  (`et_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `investment_category_master` table : 
#

CREATE TABLE `investment_category_master` (
  `ic_id` int(11) NOT NULL auto_increment,
  `ic_name` varchar(100) NOT NULL,
  `ic_max_limit` int(11) NOT NULL default '100000',
  `ic_deduction` float(4,2) NOT NULL,
  `ic_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`ic_id`),
  UNIQUE KEY `ic_name` (`ic_name`),
  KEY `ic_org_id` (`ic_org_id`),
  CONSTRAINT `investment_category_master_fk1` FOREIGN KEY (`ic_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `investment_heads` table : 
#

CREATE TABLE `investment_heads` (
  `ih_id` int(11) NOT NULL auto_increment,
  `ih_name` varchar(100) NOT NULL,
  `ih_benefit` tinyint(4) default '0',
  `ih_details` varchar(250) default NULL,
  `ih_under` int(11) NOT NULL,
  `ih_org_id` int(11) default NULL,
  PRIMARY KEY  (`ih_id`),
  KEY `ih_under` (`ih_under`),
  KEY `ih_org_id` (`ih_org_id`),
  CONSTRAINT `investment_heads_fk` FOREIGN KEY (`ih_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `investment_heads_ibfk_1` FOREIGN KEY (`ih_under`) REFERENCES `investment_category_master` (`ic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `investment_limit_mster` table : 
#

CREATE TABLE `investment_limit_mster` (
  `ihl_id` int(11) NOT NULL,
  `ihl_amount` int(11) NOT NULL,
  `ihl_gender` tinyint(4) NOT NULL default '1',
  KEY `ihl_id` (`ihl_id`),
  CONSTRAINT `investment_limit_mster_ibfk_1` FOREIGN KEY (`ihl_id`) REFERENCES `investment_category_master` (`ic_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `investment_plan_master` table : 
#

CREATE TABLE `investment_plan_master` (
  `ip_id` int(11) NOT NULL auto_increment,
  `ip_emp_id` varchar(30) NOT NULL,
  `ip_ins_id` int(11) NOT NULL,
  `ip_amount` int(11) NOT NULL default '0',
  `ip_year` int(11) NOT NULL,
  `ip_sess_id` int(11) default NULL,
  `ip_org_id` int(11) default NULL,
  PRIMARY KEY  (`ip_id`),
  KEY `ip_emp_id` (`ip_emp_id`),
  KEY `ip_sess_id` (`ip_sess_id`),
  KEY `ip_org_id` (`ip_org_id`),
  CONSTRAINT `investment_plan_master_fk` FOREIGN KEY (`ip_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `investment_plan_master_fk2` FOREIGN KEY (`ip_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `investment_plan_master_ibfk_1` FOREIGN KEY (`ip_emp_id`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `leave_type_master` table : 
#

CREATE TABLE `leave_type_master` (
  `lt_id` int(11) NOT NULL auto_increment,
  `lt_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`lt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `leave_quota_master` table : 
#

CREATE TABLE `leave_quota_master` (
  `lq_emp_type` int(11) NOT NULL,
  `lq_leave_type` int(11) NOT NULL,
  `lq_count` int(11) NOT NULL,
  KEY `lq_emp_type` (`lq_emp_type`),
  KEY `lq_leave_type` (`lq_leave_type`),
  CONSTRAINT `leave_quota_master_ibfk_1` FOREIGN KEY (`lq_emp_type`) REFERENCES `employee_type_master` (`emp_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leave_quota_master_ibfk_2` FOREIGN KEY (`lq_leave_type`) REFERENCES `leave_type_master` (`lt_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `leave_value_master` table : 
#

CREATE TABLE `leave_value_master` (
  `lv_id` int(11) NOT NULL auto_increment,
  `lv_name` varchar(50) NOT NULL,
  `lv_value` float(2,1) default NULL,
  PRIMARY KEY  (`lv_id`,`lv_name`),
  UNIQUE KEY `lt_name` (`lv_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `loan_master` table : 
#

CREATE TABLE `loan_master` (
  `ln_id` int(11) NOT NULL auto_increment,
  `ln_name` varchar(100) NOT NULL,
  `ln_max_amount` int(11) NOT NULL,
  `ln_interest` float(4,2) NOT NULL,
  PRIMARY KEY  (`ln_id`),
  UNIQUE KEY `ln_name` (`ln_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `loan_period_master` table : 
#

CREATE TABLE `loan_period_master` (
  `lp_id` int(11) NOT NULL auto_increment,
  `lp_name` varchar(100) NOT NULL,
  `lp_value` int(11) NOT NULL,
  PRIMARY KEY  (`lp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `loan_request_master` table : 
#

CREATE TABLE `loan_request_master` (
  `lr_id` int(11) NOT NULL auto_increment,
  `lr_emp_id` int(11) NOT NULL,
  `lr_date` date NOT NULL,
  `lr_amount` int(11) NOT NULL,
  `lr_approved` tinyint(4) NOT NULL,
  `lr_approve_date` date NOT NULL,
  `lr_installment` int(11) NOT NULL,
  `lr_duration` int(11) NOT NULL,
  PRIMARY KEY  (`lr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `month_master` table : 
#

CREATE TABLE `month_master` (
  `mm_month` date NOT NULL,
  `mm_year` int(11) NOT NULL,
  `factor` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `month_master_2` table : 
#

CREATE TABLE `month_master_2` (
  `mm_month` date NOT NULL,
  `mm_year` int(11) NOT NULL,
  `factor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `month_master_3` table : 
#

CREATE TABLE `month_master_3` (
  `mm_month` date default NULL,
  `mm_year` int(11) default NULL,
  `factor` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `pf_account_master` table : 
#

CREATE TABLE `pf_account_master` (
  `pf_id` int(11) NOT NULL auto_increment,
  `pf_ac_id` varchar(50) NOT NULL,
  `pf_amount` int(11) NOT NULL,
  `pf_sess_id` int(11) NOT NULL,
  `pf_type` tinyint(4) NOT NULL,
  `pf_ac_date` date NOT NULL,
  `pf_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`pf_id`),
  KEY `pf_org_id` (`pf_org_id`),
  CONSTRAINT `pf_account_master_fk1` FOREIGN KEY (`pf_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
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
# Structure for the `pf_data_master` table : 
#

CREATE TABLE `pf_data_master` (
  `pf_emp_id` int(11) NOT NULL,
  `pf_op_balance` int(11) NOT NULL,
  `pf_op_balance_empl` int(11) NOT NULL default '0',
  `pf_sess` int(11) NOT NULL,
  `pf_org_id` int(11) NOT NULL,
  KEY `pf_emp_id` (`pf_emp_id`),
  KEY `pf_org_id` (`pf_org_id`),
  CONSTRAINT `pf_data_master_fk1` FOREIGN KEY (`pf_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pf_data_master_ibfk_1` FOREIGN KEY (`pf_emp_id`) REFERENCES `employee_master` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `pf_setting_master` table : 
#

CREATE TABLE `pf_setting_master` (
  `pf_empr_share` float(9,2) NOT NULL default '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `report_field_master` table : 
#

CREATE TABLE `report_field_master` (
  `rf_rm_id` int(11) NOT NULL,
  `rf_field_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `report_master` table : 
#

CREATE TABLE `report_master` (
  `rm_id` int(11) NOT NULL auto_increment,
  `rm_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`rm_id`)
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
  `sh_scalable` tinyint(4) NOT NULL default '0',
  `sh_special` tinyint(4) NOT NULL default '0',
  `sh_cat` int(11) NOT NULL default '1',
  `sh_display` tinyint(4) NOT NULL default '1',
  `sh_type_code` int(11) NOT NULL default '2',
  `sh_process_type` tinyint(4) NOT NULL default '0',
  `sh_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`sh_id`),
  UNIQUE KEY `sh_name` (`sh_name`),
  KEY `sh_org_id` (`sh_org_id`),
  CONSTRAINT `salary_head_master_fk` FOREIGN KEY (`sh_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_data` table : 
#

CREATE TABLE `salary_data` (
  `sd_emp_code` varchar(30) NOT NULL,
  `sd_head_code` int(11) NOT NULL,
  `sd_date` date NOT NULL,
  `sd_amount` int(11) NOT NULL default '0',
  `sd_sess_id` int(11) NOT NULL default '1',
  `org_code` int(11) default NULL,
  UNIQUE KEY `sd_date` (`sd_date`,`sd_head_code`,`sd_emp_code`),
  KEY `sd_emp_code` (`sd_emp_code`),
  KEY `sd_head_code` (`sd_head_code`),
  KEY `sd_sess_id` (`sd_sess_id`),
  KEY `org_code` (`org_code`),
  CONSTRAINT `salary_data_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `salary_data_fk1` FOREIGN KEY (`sd_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `salary_data_ibfk_2` FOREIGN KEY (`sd_head_code`) REFERENCES `salary_head_master` (`sh_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `salary_data_ibfk_3` FOREIGN KEY (`sd_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_formula` table : 
#

CREATE TABLE `salary_formula` (
  `sf_sal_id` int(11) NOT NULL,
  `sf_sal_formula` varchar(100) NOT NULL,
  `sf_org_id` int(11) default NULL,
  KEY `sf_sal_id` (`sf_sal_id`),
  KEY `sf_org_id` (`sf_org_id`),
  CONSTRAINT `salary_formula_fk` FOREIGN KEY (`sf_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `salary_formula_ibfk_1` FOREIGN KEY (`sf_sal_id`) REFERENCES `salary_head_master` (`sh_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_lock_master` table : 
#

CREATE TABLE `salary_lock_master` (
  `sl_lock_month` int(11) NOT NULL,
  `sl_lock_year` int(11) NOT NULL,
  `sl_status` tinyint(4) NOT NULL default '0'
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
# Structure for the `salary_schedule_master` table : 
#

CREATE TABLE `salary_schedule_master` (
  `shs_id` int(11) NOT NULL,
  `shs_sess_id` int(11) NOT NULL,
  `shs_month` int(11) NOT NULL,
  KEY `shs_id` (`shs_id`),
  CONSTRAINT `salary_schedule_master_ibfk_1` FOREIGN KEY (`shs_id`) REFERENCES `salary_head_master` (`sh_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `salary_type` table : 
#

CREATE TABLE `salary_type` (
  `st_id` int(11) NOT NULL auto_increment,
  `st_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`st_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `session_employee_master` table : 
#

CREATE TABLE `session_employee_master` (
  `se_sess_id` int(11) NOT NULL,
  `se_emp_id` int(11) NOT NULL,
  `se_org_id` int(11) NOT NULL default '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `slab_head` table : 
#

CREATE TABLE `slab_head` (
  `sl_head_code` int(11) NOT NULL auto_increment,
  `slab_head_name` varchar(100) default NULL,
  `sl_start_value` int(29) default NULL,
  `sl_end_value` int(11) default NULL,
  `sl_percent` float(9,3) NOT NULL,
  `sl_orgCode` int(11) default NULL,
  PRIMARY KEY  (`sl_head_code`),
  UNIQUE KEY `slab_head_name` (`slab_head_name`),
  KEY `sl_orgCode` (`sl_orgCode`),
  CONSTRAINT `slab_head_fk` FOREIGN KEY (`sl_orgCode`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `sms_profile` table : 
#

CREATE TABLE `sms_profile` (
  `SMP_ID` int(11) NOT NULL auto_increment,
  `SMP_NAME` varchar(100) NOT NULL,
  `SMP_USER` varchar(100) NOT NULL,
  `SMP_PASSWORD` varchar(25) NOT NULL,
  `SMP_SENDER` varchar(25) default NULL,
  `sms_current` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`SMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `system_master` table : 
#

CREATE TABLE `system_master` (
  `ms_id` int(11) NOT NULL auto_increment,
  `ms_password` varchar(200) NOT NULL,
  PRIMARY KEY  (`ms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `system_private_data` table : 
#

CREATE TABLE `system_private_data` (
  `it_percent` float(4,2) NOT NULL,
  `pf_interest` float(9,3) NOT NULL,
  PRIMARY KEY  (`it_percent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `task_list` table : 
#

CREATE TABLE `task_list` (
  `seq_no` int(11) NOT NULL auto_increment,
  `task_id` varchar(20) default NULL,
  `task_name` varchar(100) default NULL,
  `task_menu_name` varchar(1000) default NULL,
  UNIQUE KEY `seq_no` (`seq_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tax_plan_master` table : 
#

CREATE TABLE `tax_plan_master` (
  `tp_id` int(11) NOT NULL auto_increment,
  `tp_emp_id` varchar(40) NOT NULL,
  `tp_installment` float(9,3) NOT NULL,
  PRIMARY KEY  (`tp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tax_rep_master` table : 
#

CREATE TABLE `tax_rep_master` (
  `tr_id` int(11) NOT NULL auto_increment,
  `tr_month_code` int(11) NOT NULL,
  `tr_emp_code` varchar(40) NOT NULL,
  `tr_amount` float(9,2) NOT NULL default '0.00',
  `tr_sess_id` int(11) NOT NULL default '1',
  PRIMARY KEY  (`tr_id`),
  KEY `tr_id` (`tr_id`),
  KEY `tr_sess_id` (`tr_sess_id`),
  CONSTRAINT `tax_rep_master_fk` FOREIGN KEY (`tr_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `taxslabs_master` table : 
#

CREATE TABLE `taxslabs_master` (
  `ts_id` int(11) NOT NULL auto_increment,
  `ts_start` varchar(20) NOT NULL,
  `ts_end` varchar(20) NOT NULL,
  `ts_percent` int(11) NOT NULL,
  `ts_gender` int(11) NOT NULL,
  `ts_name` varchar(20) NOT NULL,
  PRIMARY KEY  (`ts_name`),
  UNIQUE KEY `ts_id` (`ts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tds_installment_master` table : 
#

CREATE TABLE `tds_installment_master` (
  `ti_emp_id` varchar(40) NOT NULL,
  `ti_year` int(11) NOT NULL,
  `ti_amount` float(9,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `temp` table : 
#

CREATE TABLE `temp` (
  `ids` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `temp_cum_data` table : 
#

CREATE TABLE `temp_cum_data` (
  `temp_code` varchar(100) NOT NULL,
  `temp_amount` int(11) NOT NULL,
  `temp_camount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `temp_sal_data` table : 
#

CREATE TABLE `temp_sal_data` (
  `temp_code` varchar(40) default NULL,
  `basic` float default NULL,
  `gp` float default NULL,
  `npa` float default NULL,
  `da` float default NULL,
  `hra` float default NULL,
  `arr` float default NULL,
  `cpfe` float default NULL,
  `cpf` float default NULL,
  `tds` float default NULL,
  `pfr` float default NULL,
  `gsli` float default NULL,
  `trans` float default NULL,
  `mhd` float default NULL,
  `ttn` float default NULL,
  `hk` float default NULL,
  `wtr` float default NULL,
  `fur` float default NULL,
  `ma` float default NULL,
  `elctr` float default NULL,
  `pfrecovery` float default NULL,
  `DAarr` float default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `ts_gender` table : 
#

CREATE TABLE `ts_gender` (
  `ts_seq` int(11) NOT NULL auto_increment,
  `gender_name` varchar(50) NOT NULL,
  `orgCode` int(11) default '1',
  PRIMARY KEY  (`gender_name`),
  UNIQUE KEY `ts_seq` (`ts_seq`),
  KEY `orgCode` (`orgCode`),
  CONSTRAINT `ts_gender_fk` FOREIGN KEY (`orgCode`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_group` table : 
#

CREATE TABLE `user_group` (
  `user_id` int(11) default '1',
  `user_name` varchar(130) default NULL,
  `password` varchar(500) default NULL,
  `pass_noti_day` int(11) default NULL,
  `orgCode` int(11) default NULL,
  `user_status` tinyint(4) default NULL,
  `user_grp_id` int(11) default NULL,
  `user_profile_id` int(11) default NULL,
  KEY `user_id` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_group_master` table : 
#

CREATE TABLE `user_group_master` (
  `grp_id` int(11) NOT NULL auto_increment,
  `grp_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`grp_id`,`grp_name`),
  UNIQUE KEY `grp_name` (`grp_name`),
  KEY `grp_id` (`grp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_history` table : 
#

CREATE TABLE `user_history` (
  `user_id` varchar(150) default NULL,
  `in_time` time default NULL,
  `out_time` time default NULL,
  `activity_id` varchar(100) default NULL,
  `user_ip` varchar(100) default NULL,
  `visited_date` date default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_history_master` table : 
#

CREATE TABLE `user_history_master` (
  `hs_id` int(11) NOT NULL auto_increment,
  `hs_user_id` int(11) NOT NULL,
  `hs_date` datetime NOT NULL,
  `hs_status` tinyint(4) NOT NULL,
  PRIMARY KEY  (`hs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_master` table : 
#

CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(100) NOT NULL,
  `user_pass` varchar(20) NOT NULL,
  `user_org_id` int(11) NOT NULL,
  `user_grp_id` int(11) NOT NULL,
  `user_profile_id` int(11) NOT NULL default '0',
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_org_id` (`user_org_id`),
  KEY `user_id` (`user_id`),
  KEY `user_grp_id` (`user_grp_id`),
  KEY `user_name_2` (`user_name`),
  CONSTRAINT `user_master_fk` FOREIGN KEY (`user_name`) REFERENCES `org_profile` (`org_email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_master_ibfk_1` FOREIGN KEY (`user_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_master_ibfk_2` FOREIGN KEY (`user_grp_id`) REFERENCES `user_group_master` (`grp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_registration` table : 
#

CREATE TABLE `user_registration` (
  `seq_no` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `father_name` varchar(150) default NULL,
  `gender` varchar(20) default NULL,
  `email_id` varchar(300) NOT NULL,
  `dob` date default NULL,
  `ph_number` varchar(20) default NULL,
  `orgCode` int(11) NOT NULL,
  `regCode` varchar(150) default NULL,
  PRIMARY KEY  (`email_id`,`orgCode`),
  UNIQUE KEY `seq_no` (`seq_no`),
  KEY `orgCode` (`orgCode`),
  CONSTRAINT `user_registration_fk` FOREIGN KEY (`orgCode`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_task_group` table : 
#

CREATE TABLE `user_task_group` (
  `seq_id` int(11) NOT NULL auto_increment,
  `user_task_id` varchar(120) NOT NULL,
  `user_org_code` int(11) default NULL,
  `user_task_password` varchar(50) default NULL,
  `user_grp_id` int(11) default NULL,
  `user_task_flag` tinyint(4) default NULL,
  PRIMARY KEY  (`user_task_id`),
  UNIQUE KEY `seq_id` (`seq_id`),
  KEY `user_org_code` (`user_org_code`),
  CONSTRAINT `user_task_group_fk` FOREIGN KEY (`user_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_task_list` table : 
#

CREATE TABLE `user_task_list` (
  `seq_no` int(11) NOT NULL auto_increment,
  `user_id` varchar(300) default NULL,
  `task_id` varchar(24) default NULL,
  `org_code` int(11) default NULL,
  `menu_item_name` varchar(300) default NULL,
  `flag` tinyint(4) default NULL,
  UNIQUE KEY `seq_no` (`seq_no`),
  KEY `user_task_list_fk` (`user_id`),
  CONSTRAINT `user_task_list_fk` FOREIGN KEY (`user_id`) REFERENCES `user_task_group` (`user_task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_task_menu_name` table : 
#

CREATE TABLE `user_task_menu_name` (
  `menu_seq_no` int(11) NOT NULL auto_increment,
  `user_id_menu` varchar(30) default NULL,
  `user_menu_id` varchar(10) default NULL,
  `user_menu_name` varchar(300) default NULL,
  `user_menu_value` varchar(300) default NULL,
  `menu_flag` tinyint(4) default NULL,
  UNIQUE KEY `menu_seq_no` (`menu_seq_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `admin_records` table  (LIMIT 0,500)
#

INSERT INTO `admin_records` (`seq_id`, `user_id`, `admin_pass`, `flag`, `add_date`) VALUES 
  (1,'admin','hjhjhj',0,'2012-12-18'),
  (2,'adminjk','jkljkl',0,'2017-11-17'),
  (3,'adminjk1','123123',0,'2018-11-18'),
  (4,'payadmin','admin123',1,'2025-11-25');
COMMIT;

#
# Data for the `admin_email_config` table  (LIMIT 0,500)
#

INSERT INTO `admin_email_config` (`admin_id`, `admin_emailid`, `admin_config_pass`, `admin_email_status`) VALUES 
  (NULL,'akash@gmail.com','qweqwe',0),
  (NULL,'dhruvmahajan9@gmail.com','123123',0),
  (NULL,'erpmission.smvdu@gmail.com','workshop2011',1),
  (NULL,'sumit.polite@hotmail.com','!!!@Dedicated',0),
  (NULL,'sumit@gmail.com','123123',0),
  (NULL,'sumitkl@gmail.com','123123',0),
  (NULL,'sumitpinku@gmail.com','123123',0);
COMMIT;

#
# Data for the `admin_smtp_details` table  (LIMIT 0,500)
#

INSERT INTO `admin_smtp_details` (`seq_id`, `smtp_name`, `smtp_port`, `auth_emailid`, `auth_password`, `smtp_status`, `smtp_host_name`) VALUES 
  (9,'GMAIL',25,'p2pcommunicationsumit@gmail.com','!!!@Dedicated',1,'smtp.gmail.com'),
  (8,'HOTMAIL',25,'sumit.polite@hotmail.com','!!!@Dedicated',0,'smtp.live.com'),
  (7,'rediffmail',25,'dhruvmahajan9@rediffmail.com','mahajandrv',0,'smtp.rediffmail.com'),
  (4,'yahoo.co.in',23,'sumit@yahoo.co.in','dedicated',0,'smtp.mail.yahoo.com');
COMMIT;

#
# Data for the `org_profile` table  (LIMIT 0,500)
#

INSERT INTO `org_profile` (`org_id`, `org_name`, `org_tagline`, `org_email`, `org_web`, `org_phone`, `org_address1`, `org_address2`, `org_logo`, `org_master_password`, `org_recovery_id`, `org_tanno`, `org_city`, `org_pincode`, `org_state`, `org_ll`, `org_countrycode`, `org_regioncode`, `org_institutedomain`, `org_toi`, `org_affiliation`, `org_adminfn`, `org_adminln`, `org_admindesig`, `org_reg_noti`, `org_status`, `org_reg_date`) VALUES 
  (1,'SMVDU','kjhkjh','admin@gmail.com','www.smvdu.ac.in','0987654321','kjhkjh','kjhkjh',0xFFD8FFE000104A46494600010200006400640000FFEC00114475636B79000100040000003C0000FFEE000E41646F62650064C000000001FFDB0084000604040405040605050609060506090B080606080B0C0A0A0B0A0A0C100C0C0C0C0C0C100C0E0F100F0E0C1313141413131C1B1B1B1C1F1F1F1F1F1F1F1F1F1F010707070D0C0D181010181A1511151A1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1F1FFFC0001108016C02FA03011100021101031101FFC400B0000100010501010000000000000000000000060203040507080101010101010101010100000000000000000001020304050607100002010303010406070602060904000701020300110412050621314113075161712232148191A142522308B1C1627282153324D1E192A24316B2C253738393B33425F063A344F1C354649426171101010002020201020405030500000000000111022112310341510461221305F07191A1B1D1E132426282B206FFDA000C03010002110311003F00DDDA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0AAD40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B506526D7B84982D9C98EED88874BCC07BA08FFF008D062DA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A82AB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D41D6F89A60A71DC3C6BA4A5D34CE83AD9E51E2156FE8717A0E6BC8B6C1B6EF395889FE1A3DE3FE461A97EA06835D6A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A82AB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B505491B3BAA28BB310AA3D67A5074DE25B7CF8F801B201121CACA95149F8637948887FE5AAD6A45AD1F2ED8B2370DE279B0E449665895DF16F6974A929751F7BAAD4BE510CB7AAA05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A82AB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B5066ECC106EB88CFD51255761EA43A8FECA0E86796ECB0E337E65DD535C6A47F88BDECBFC37F76FD84F415ABC0E7997C9F7BC48DF370E7719726563A302AB2A186494AB2B2B0D76BB803411DB5322CE6B23E64EEA6EAD239523B2C589A82C5A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A82BB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B506EF86E34791C8B11241A93512C2D7BF4B506F76FE2DC7FFE5B7C6DAD1173771FCF59247D72B98D888C6B6B1D0ABEEA8E805EADB9102E458B9BB7E14DF3113C32C3263BE97047C19311E9E9A8B22E91636A216A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0AED40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4124E00ABFDFBC46EA61824917DAB6B5051BC6CDB64783B76EB9DE347BA18D478504A6307580CE2C41D2ABDD615AB463F9872E765707C5972044B018D4E39058C815326116919BA316500DC01D6B235122DA461EB3FB6829B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B50576A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0CFD8F73FED99DF3050C88D1BC6E80E92438B74363DF4146EBB9E46E595E3CA02A81A638C762A8EE14122DFE1C57E0D80D910BE4C3162C92C9021D2CC1248D88BFD1D68231B841E0E7E4C5D9E1CAEBF53114162D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4155A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0B595938F898F264E4C8228221AA491BB00ECEEEBEA02835DB272AE3FBC65B61E3E498726C4C693A950F6EDD25758BFA8DA8373910C91C45E2F0E77B5D235963527ADBB58F4EBDB4115DDBCD3DCF091B64DD222B83A5B1E59A27D32C31C8406F0FA69B74EA48352474DF797C4C2DF983CD62C6E3F3E6E367BFF709BC231CE5535B932286EC5D37D173D82AB9A0FC2F976F79BCA3023C8DCB2658A666431995B41D48D6F76FA7B7D5458EC6BEF4519ED36218FA4EA3E8F5510B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B50576A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A8355CA3689F75D8F270A060B3B80D1DCD812AC1B493EBB5BDB45CB98C1C4F976DB94BB82EDEEA3198286F1223D4DC0FBE7A75A16A9DC392EF504AC26235302D60EA6E18DFA5BD62888BEF7BAE6EE4E5E4E87E1507B58924FEFA0F9B8EF92E471E8F16C1A5C6B09352860557E1604DC5E8259C6B896F18F81B5EFF1B606417027C789E7104F1A8B8BB0442081D7EED0756E2BF35BA6DB8E208A79E490B146700BBF5B16D2A16C01E9D83B28B5B7CEC5C0DB411B96E3043902D7C48DBC6956FF008C27BA9FD4C2861891CBB7E4B30C2C8391A7BCAE807F96E49345BAD2D464B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4172D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D41633F70DB36EC2972F3A7F0D51498E355D4F230B741D401DA3A9A186B9397ED02142B04934CEA1B45ECA2E2F6E9D4FD74308B731E6D3CD8D1E0C588E8996AFE03428CDD57E2D4C3B7D1EAA2D8E5F9FBBE746D2E463788B1B442391D7E24B127AA9EE6BF5A2371C5782EFDCAB0F2B76DA2044DB3014BE7E5E4909146635F10C6DD6EDAAD6F77D3DD41A71883333F206240D2298FC6C88523D0A18F5758C2EAB22DBA504BB856D9878FBAE2666E314F2118CF2E1438F189E3914022F2682DD0116371D08EB46B0DEC3E6363C1B5B36E199931ECAF97319B0F01FC2F17C576708D22A897C306E3B6DEAACC5B8F2AB17CD9E1B2EF5B76D78BC6227C39E64563044D952942D62B0A3328676ECB81DB5A4CC74CDB76FE2F2F24927C49F46107917FB7B2B46F0BC442D8DC9BAFAFD371569DAE129CBDC38BC00875819BBC94427EB22A32946DB83B3E5ED18F930E340D1491875FCB5B751D7BA831373E19B466140235C672DF1C002F4F411623ECA0C61E5E6CA83DE7998FF0032FEE5A086F21C2DAF0F706C6DBE479163E92B310407EF0A401D941ACB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D415DA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A830F71D8F68DD63316E492BA68655F09C21F7ADDB707B2D7145CB0368E2D060C2B0CF2AE5A20010F85E137BBF0B310ED7603BC5A8BD9B75C748D5C40A217752A64451ABA8B5C9FBDF4D13281CBE51C7367E4CC7710B8D96A56587C2D4D76EDB5D9401F49A16BE6EFC8E4E21E5F65710C48275C7CABB9DC248BC169B49FCD322F6C77023445FC3D4F5A2572AD9B95EF5B4A4ABB6A3C934B20309545655765B16B90DEFE95F77D1DA2D416E4DBF98E74D93E0CB22E3E536B9CC8E630E5BA9D7E9EA7ADE81B6C38B8D24D0EECD21C58B59C9F95742E4C4C1AC8FEFAF5ECA0DA3ECD839198BBBF12CE388B8A04C88642EF118CDC80E150DD7E2EA3D77A0DC6D7CA39343BAB46D14B399D8B4F9F27466909376623DDEA68B96FA6DD37195FDF76BFACD11E8EF2C5A79BCBDDA998DD843229FE991C516A4592644812607ADE33F5B0BFEDA12239CB7916461C43171C913CEA6F37E14EC3A7F88FD94440AD40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D415E9A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A0DA6D9C7B2B3A332EA5821EC47707DE3FC200341B37E03B888CB473C6ED6BAAD985FE923A5069770D9B70DBC8F9A8B406360C08209FA2830B4D034D0407CECC6FEEDB0CB94E890E5E13C4CC81CB19D046B119FAA811B1002B286F7B4EAB5070C8313745DBB33261593E5F17478D281204469180E8CBD0161606F41F31B7C6485E3C94D408B0643A5AC7A5BD74191B22E2CD8F3C50EA5562C8C64B74F122603B3F9683EF13DDA6DAF775C798FE53B1468CFC3A8F4FA9874A0DE72ACC97136C8E38EED24190A6196F63E1B21313FB7BBDB41199796F2294A96CE71A40034D8741D3B875A0F5A790BBEEEB95E566D534D94F2C9AB255998F5F76771FB2B520E8D8F9B3CF8D0891F523A2DC74B760A5834FCDF1D4E3634FDEAE53E8617FF00AB59110D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D34172D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40D3419F8BB06EB93631E3B043F7DFDC1FEF5A837583C3DE271365491CA52E4402FA58DBA5D8DBF650373C1F30F2239A3DB773C0DAA378F4C2E23796446BF683A55474E9DF46AE1186F2F7CD495C3E5799190BE94861651F6325196E57846E0DB8367677209B33542B08C5285600469BC801773ADB4F5346ADCB17278F6E892B085616887C2ED310C7FA44640FF6A8CB4BBA66C5B4693BAE9C347368DDA48D958F67BA1199BEB5141ABDC7038CF30DA9F15A58F331C1BACB0480BC520E81948BE961EBFA450723E4320E171E5EDD85BDE26F38BB907832F6C6F7DD0303A9DE38D8E971DCFA81FE1E941CD67C504DE371E137C01CD8FAFA902833B8FF00F969E4576521F4358107E16B1FB1A83033AF60FD7C5818C12DBB7DC27437D42DF450499E75DE78E2CA7ACD8C3C39C7F093D1BFA5EC7D84D046A1DB32645BD80B1208EF0474341EB2FD3E43245E5760C327C4B9195F51949FDF5B9E074BDAC11898A0F688D01F6E914BE062F352060E3A779949FA948FDF5810FB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B50576A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0A922791C2229676E8AAA2E49F60A0DE627172AA25DCA618D1F6F86082E7F70A0CCFEEDC6B6A1F9091AB8FF008AFD5BEB3D6831A1E7DB7E66E10E041329C8C87F0E30C74AEA3D97352DC4593293438196F7F126171D1844B7B1F46A722A66DF0B8642ED49F7D9DBD21A4B7D88298A660768C33F146847AF5B7ED6A75FC4CC599B64C061FE1A8FE56743F631A75FC4CC45B9271DDC638249768CB75C800F878F39D51BB5BA28900BADFD758BB59F8B73595E5CE6B3EF7BCEEF96BCAB264DBB70C25D18DB68491E38D88162CD6B156EDBAFD75D35DA5998E7B6B65C5405B3B74C0C8C8844D2C123868725159975A30B32B5BB55855463CF9501C38D52365CB463AE707A346474047E206FD7D141868C3501213A3AF67A6D4191B74BA3296FD035D7FDAE9419FBA78433BF08CA8C78C3B83773FD7DBF4D057C6F70FEDDBA7832FBD8F37E54E87B086E9D7EBB1A0DE4F8F1EDF2CF1B9FCB5B48B21FBD1B7C2DFB8FAEAC1E91F21F3525E0F1630864410C92B89D97F2A412487E06EC256DD6AEB55D0766C98A4C1C29430D32A4650DFB752DC0156F82B5FCCA7D73E3C20F5452EDED636FFAB584472D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D41734D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D065E16D92E48F1188871D7E299BB3D8A3BCD05ADC79A6C5B1234382754FD8F28B3C87E9E8AB4105DDFCD1C8919880AB7FBD2B6B3F57BABF65045F3B98E54E8EB90524471D0AAAAB2FA0A9500D05EC20318E36E4D902358D93212463A40D243837352CE165C57A472F946D3B5E0B67E64AC31E4680031AB4875CFF9718016FF00132DAB96BBC9AE6BD1A7DBEDECDBAEBE79BFD39AC11E687086C759D77252AD1F8BA4472960A2F7D4029B1500923B40EBD9D6AFEB6B8CE5DB6FDB3EE26DD6EBCE71F1FC7FBF1E54B7999C4FC39DD325E438F8477278C46E1CE28729E22290350E97FE5EBD957F575FED9667EDDEEE3F2F9DBA7FE5E70DFC797148A1CDECC0301EA22FD6BA3C762A6F064054A8B1162A7B08A24AE4BE7971BC793661BCC283E6B6F75499ADF1E3C86C357A74B1FDB5CE71B7F376B731E5DE49B5C7F9D2C4349842CB18F4C0C74B2FFE1BDADEA3EAAE8E28E2FC2C2DD0F69A0CDE37B1AEF3B89C233FCB9D0CEAC10C858ADBDD007B7B4F65677DB132D6BAE58FBB6DD26D9B94D86CDA9B1DEC1ED6B8ED06DD6DD0D5973329662B2B38AE66045940FBF102B25BB6C7FD07F6D5461693340F2F5F12123591DEADD87EB14134D99937FD99A0737CFC446507F14647BC0FFD21F4D05BE33CDB7EDB62C2DB22C974C68A7002AB1162CE351F455C8F4E712D9B75963E39BC656598F62DBB6A85E2C5BFC794F1B299180F423F4BF7D2785ACDDC329F332E4C86FBE7DD1E851D00FAAA231F4D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D05CB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B50579136DDB5E31CCDD64091817482FEF37B6839972EF35B70DC59B1F6D42988BEEAE93A52C3B8B0EDF62D040B2B333727FF007192F63F7222117EC37FB6835EF0628EA63D47D2C6E7ED341419C471E85E8BDA05EF6A08667E44F9197246D23C8BACAC6ACC58017B0001A0F69CDB22722E2BFDA669CC11CB162788E1439B432349A6C4AFC5D2BCBEBD3BFAF15F47D1F77B7DB7B67B24CD99FEF30D3EE3C43FE5CDBB76DE3132132970B0656C18726372D1E884C7A58A4888F68498C314D7A7BEADF57496CFA3D7EBFBFBF71ECD3D7B4EBDB79DB17CE76CE7C71F9BF36338CFC3491E4E2E063ED316571D8F709B728C2BCB32B43263E2B9871543A2BCFAE1F0DC6921BB3B4769A9E319D7CBB5CEFB7B3AFB7AF4B9C673DB69DB6E2E2636CFE1E7E5B7DB79B6FD85B52B626C58F8D88D2672E2C31F8F21638B1BBEA2002478B22E917F4F4AD4F6593C7D5E7F67D9FAB6F662FB337F266F1FF559FF00ACAD9ED9E626FB2E2C8D9382B1E5FC8B4F0451E3E5B2B652C8E0C05ADD823406FE935AFD4BFD9CB6FB1D3B71B71DF1E75FF8F1CFF55BF307799737CB3DD333321F969648446622194122565575570180716601BAF5A67363CBECF5CD2D92E67F1FE1E5EE432C2DB5166621FA885D3AF68B329FE122BB3C887A778BD81EDB7D9417E39A2C375C9C3CB9A3CE8C82A5174017BEAB481B57D9419591B716D923DD269DA4CBCDC865456172E00F7D8926FD1BBE82D09971D045A6E197DEEE37F4D066F1ADA779DE3754C7C0C597324CB1E17848A589502C3A283D96A0EE3C37F4DDC8B053FB8EE98A638910CB2C4D3A2390BD7484F780E9F8FEAACEDB6265BD35EDB49E32D0727CAE2F8DBDCE72B8AA26E386238F1A3932A46320604ACACD169590153D05BDDB7D59F57B66FACDA78AD7BBD3B7AB6BAEDE63A2EC1E64EDBBAA606D319F965489523C127DE8740D1A1C585B4916B9EEEB5D1C928D340B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B505CB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D41AFDD37EDBF6C7292C6D3B01EF6870B63DE3A8EBF5D1B9A35F1F989C48B69C84C8C63E962ACBF5814670DC626F7C572D03C5B96853F79D2EA3DACA6D446CE1C0C7C801B173B1E653D96720FECA0BDFD833CFC3E1B7B1C7EFA0F8760DCC7FC207FAD7FD3414FF62DCFFEC7FDE5FF004D07D1B0E7FDE08BED71FBAF41449B6785FE36442807AC93FB28303273F62C5BF8D99AADDCA00FB49A0D7C9CCB8B427AEA93DADFE8028293E607150086C6D2BF8B5BFF00A682DE5F38E3387B649B863E5264CCB6D11037F0CB2EA01BB8BF5ECEEA0E39C8F95E76F794F3E5CA4C449D10EA205BD741A2972D4FE103D0283124C9F58B7B282DB4E3B2F7FE9A0C7C894DBDDEB65E83D27B681C0B8767EF9CBF6CC3D37569D669ADD6C919D6DF5DADF4D73F6DC6B70DFAE66BDA1B46098B16E3B1CFBA477AA288D4FD3A6FF4D6B4D7130BEDDB35919BB5E3E6E24D8794825C6C8431CD11B80C8C2C412083D6AD99674DEEBB4DB5B8B3C3176AE37B56D09226DB8AB8CB29532692C49D0BA57AB163603A01535D64F0E9EEF7EFECB9DEE6B60217BDEE6B4E2AF5085753B1F4003A927D00779A2CD72E23FA81E5524987171FC1759331E45CACD5B82A157E08C9F5F4FA07AEB96BCDCBAEFC6B879F377C5DDD63C78277431E438555B82DE231EA7B3A0EBDDD2BAB8B5DB9EDD2ED9B964E0CAC1E481B4348B70A4D8374BD06B4F53EDA0CE872184682462C22052141D80769B7B49268243C6785EE9BFEFF00B5ED6117C6DE0C6D8ABAC7F86CECBADF4F55501198DFADA83DB3C0B85706E05B52E0ED09189CA81979EFD6799876966EE17EC51D0516448F2378DB258248BC50DE2294B771D42D52CCAC965CBC63CEB2251B960E4C9EEB4A8F0922E34BC325C58F6FDFAF97FB5EFC6DAFD2BEDFEFBEBC6FA6DF5D7F8FF2C1DAF77CACDE63F3DB8113EE399202B3B1281A660143B6907533DBAFA5ABEABE13D1F8432DF0A29726178A520070EACB736ED1714176D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D415DA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0E65CEA430E6CE09B7BC7F6D1D76BC39CE4E4BEB24351C98677ACCC56D70B98D877A9D27ECA0CCC3E6D9C1BAC8D0C9DD347700FF3A8FDA2824981E676F98E02FCCB861E96B823D46837B8FE6FEF2059A6BD0641F38774EC327D3418D379ADB9CB7BCD6F458DA835399CEF3A7BF8990C47A2F41A2CDE5C49219EE6834D93CAA46274D06167F23C87C5363626E0FB2835BB36E99D2A1C28CF4791A766624F50B6B1141B369F307C505CFA5187EFB505A39339F8A2907DBFB2AE28A0CB21EC493EA3507CD529EC8DFEAFF4D5C5176388F5693A13DDDB6AB20E83E5572DE1DC5A4CCCDDE1A54CEC81E0C32225D522B5EE185CEA2DD7B3B8571DE5CF1E1D74DA48EAD8FE79700112A8DD1D11400A5E16B0005876462AF6BF45BD6FCFF95C1E79F97F7B7F7B41FCD8F2DBEBE953BDFA275D7EACA8FCE6E04E350DF31ADE928C3F6B55EF7E8758FB3F9C9C1234D477B8187E1890B31FA8BFECA77BF43AEA84F2BF3F313C27838F44F34EC0AFCF648B2A83F813A13F528F4DEA62DF3C17693C38DE6EF4AD3CB9BB8641927998BCB231D4EEC7B6BB492473B72D0CF959F973A6F568D31319ED86929F89D4DC6941D5CEAEADDDE9ACA301F26192579F717699E6666763F13B376B7B2906365AED7E1938A5CCBDCA7B2D57814280B61F8475FDB504D786EFD2EC3BCE1720C38C31DB97DC864BA196364D33AEA1D3B0B153E9341E93E3BCC762E4714476ECB072648C4A70252132156DD6F19EA40F4ADC56E58B9ADE2C522B0D48C0DFA020D691E6DF38F2F1173E0C38DC78F06464B4880F55566005C7AED5F13F6ED2CF67B6E38EDFEAFD07EF575FD3F4CCFE6EB9BFD22FF9449B16E1BFC637585279235021592C544AC468720F46ECB7A988AFACFCFBD0CD24ACBA59895BDEDDD7A0A2D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4172D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B5041F9DF9799FC866336166A63122E564D43DEEFBE90D46F3C2238DE42EF3627237C8D4FA121671F5964FD946117E5DC0B72E3F92904B9F8997AC13EF9188D6E86E04AD67EDEBA2F6EFA0869D3E23221BB2DEE075040E9707BC5065C3393105BDF4FC3FBC505F49CDAD7E94173C66B76D052D90D6BDE82C4992C7A6AA0C295D3A927DEA0B21D000CCC00F59A098797D85C793711BE723CA871768DB49611CC6ED3CFA6E889175770A0EA361E8A0836E79BB7E2EF99B271E69536C6764C433D8CA623DED6F4F750538DBEE7A7E5DD64B9ED92F71F4DE82F3722C8536648DBD6A4D054BBEE4B29611A281F798903AD5945A937ACA001F1D2E7AE9553FBC54C8B827DC0A23CF2B44922868FDD3EF03F5505A9267D0CEF29622C3A05240FA7AFD5414620C9CBC94C5C32F3CD2901517E227B07C5A6832A0D8F7C9F731B684639DE2344D8C43788A513C42CCA01B0D3D6F417C71FCE6215B2F10A8F4E4A8FDD57033F0F8AF22CA93C1C37C49CF62AAE4C64FDAB7A606E1BCACF31B171A5CD970A1682043232EBD44AA8B9B69D2C6A0D7C9C67031761C6E43BA6E3889267C465C0C121F26773720698414555B8EACF715702239192C5CCD92C1F21BB50587D76E83D82A0C0632CAE5882C7D43ECA0FA91B0B33022FD9716040EDA090F08D89F7EE4B89B6AB6819122AB3DB569527A9B1EDB28341B0DCB8E6E98ED96998ECB3E34AD0C897370C8C57A5BA1EB4135C5F27B90E4711DBF79DAC4D83C8916713C52BB4724909D423D0000CACC9EEE96ED0683473729DDA5C1C5C98B72CEC0CB565832CC791298BC68BAAF8915FEFDAE2DDF716A0D3F23C15CA53BA62CBF310E40392925D98F6DA78EEF66FCA93A8BF5D24506BF8EEEF26D7B9C196A4DA336940362636F8ADEB1DA3D741EAFE31BC26EFB443941C3C800594AF616B02187A9D486FA6836B6A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0B9A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A682A489DCD954B1F550533362638BE4E4C50DBB8B027FDDBD06AF2B95718C6BEBCE536F60FDF45EB5AC9BCC8E1F176E583F48FF450C2D0F34B8577E51FFEBE8A23260F32782C9DB9DA7DB45EB5B5C4E57C2B2AC23DCE304F602C07ED1446DE1876DC900E2E62497EC00A9FD86831F3462E1384C9CCC689D85D5249A38D88ECB8572A4D07C0B75571D51BAAB0EA08F511DB40D341CEBCCF244DE1F8ED119203A106564445CDFEEC51C136AB5BEEB0FE2A2C732E1B8E8FCB76CC7CC469F13366314C934D982398A1D415F527E6E923E022DF8BA511D4392F92BC6F712D91B513B4661B9D318D78CC7D709234FF00415F61A0E5DBDF96DCDF669007DAA4CF889D293E003900FF00428F117E95B50683370B79C28649B2F6ACEC68E21AA479B1A58D545ED72594002F411E9B7E04D907B2F41872EEB92FF7ADEC1418CD93237C4E493E9EEA0DBECFCD37DD9F0A5C4DB648A0F15B51C91042D902E2C55667567507D47D941AD9B28E44CF91993493CF21D523B12EEC7D2CEC6836106CDBA4F01C98F6E75C556C7124D25D02AE51610B1636F76428D6602DD2A5AB2657A0E3BB964EE78DB5624065CFCE6F0F1E347460DDED62C540B0EBD48A4B92CC3A4CFF00A79CDC3E3197B965EE51C7B8E342F9031523D6968D4B6969350EB61D6CA40F5F6D6B288AEFFE566E1B3F29D978FE6E7C7265EEFE08699159A388CD3187A5CA9702DABBBD1506CF95F91D95C6B8C65EF7B9EF10EBC76D10E2C5133789A9F4A0F10B2E92C3AFC26D41CE649F2A6112CB2BCA21411C41D8B0441D88A0DEC3D428251C6F856E19DB34DB8B620681B2E1C3819D8A334B223BE941A1EE2C05CFB3D3411B6C3CEC4C90C0490CB0BDD245BAB2B2B746523B083D941378E1DFDB91F2DCADCD82EF1FDAE6927921F71499C436D3A7B3544FD94110DA362CECBDC71E15C72C1E5446D4085EAC07BC6C6C3E8AB81BBE49C2B3F161C0CCC5C4F1717311C2C900690178DCAB024227514C0DB700E49C9389F23C2C6C859BE4331D31F336C941D2E9290A19636B59C6A041EFECEC35074ECEFD38F1C6CA9C8DC32A1BB308E1F75963406C91827DF6541D3AB75A0B38BFA72D821249DD72AE47DC0A9D7D3DAD4106F39788E0F0E1B3C1B6656448D98276C913485BFC331E8D205AD7D4D7A0E61364CD3B99263A9F485D5F78DBA0B9EFE9E9A0EBFF00A74D93C7DEF277375BAE2444A9F43C978D3FDDF1283B94DC5F609B755DDA5C18DF7042184C6FF128B2B95BE82CA3A0622E28367A68389798FC2E0C7E5B29897461721466603B17217A961EB0F66FEAA08071F478A6CED932C057670D8A1FA05CC1EE1437EC59D7DD3EBD27BA8237B8E19C2CC280111B7BF16AE8749EE3EB53D0D0756F24B990C6C8FED794FF009760809FFB263EE1FF00C376B7F2B7AA83BD69A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A0B9A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A68289648A18CC92BAC71AF6BB1007DB42442B9279B5C67670C9138CB9C74014FBB7FDF46FAFD5CFB72F37798EF25A3DB6030C07B348D2A2866444F72DFF0030B13BBEFE9137DE862632BFD51EAFB68976AD24FC9B8E2B597E7339FD2C56307ED63F6512A94DEF2A517C3E3B248BDCCC2693FE8AA8A228793964BD63D81907A063CDFBCD05963CA94FBFB24BF463CFFBA8319F7FCAC76D39384F030ED04BC67EA61419F81CE67C760D0E564E330EC20EA1F61FDD42D55C87769F94C90E464672E4E7C11F851BBB599A304908757A0B1B506AB6BE4FCA78E651FEDD9F95B74CA46A8D2465536FC49F030F682283A9714FD4AEE7032C1CA3057361EC39B881629C7ADA32444FFD3A2827795CC78872D11BED3BD0124B095383F35998D39235120E345D1AC3D02E7D24504278B6240796ED6D0C823C9832EF220198AF2464E96B300EEF6BF5160BF88DA8B97A048B75EEA2298DA3950491B2BA37C2EA4107D845070CF353CE7DCF0F72DDB8BE26DD8B261AAC98795364891D9C48966D2AAD185B6A23ADEFDB41C19E5919021B051D6C001DD6A0CBD9B66CADD73171A01663D5988E8AA3B589A098433797DB00F0A4862DDB357A48EEA72141F50BAC43EDA0C98F957979B85E1CDD9F0E056E824F9738E47F5E3B5C7B48A0D59D8B8D6CFB88CBDC31E7DD7679983612E34E88ADD4931C92857F602B63EC341B1CDDDB806E18734781B36E3B547F9625583740E8E509F0D5A2C9D41B41EA2DD9532DFE9DC65AF09B54BBAC13C2D98D1322C64E68899D64463AB47827495ECB1A44DA5F94C4ED3BBCD8578E69531E453A975BAAB023A8201EB7AACA25BD4D9272F13333339B2B37C561E2CD2C92C8A108EDB9660077507A160971371E3D89F2631B96A438BE0E99A688402664553349ABDED4AA1ADA9757BC6820BE5CC5C63806E1326F7B33EF1BDE43236264411419B8B8E9765D26532AE86BFC575BDAD4129E47C8F79DD32A1C85CA93FCAC866C4817E592385C8B031E93DC3A5EB59835BCA3906F79FC632F1779DB70B2D5A1611EE13A4232633A7A68950AFBDE8E86A647109F926664F269E759BFF7D1458D93E0B3047548638CDF585BD8C7DE3B6A0EF70F30DEB0E28E1DBB121C4C18F4A9C9C1F09249074EA59EF7EBEBA456FF0061E63B86D51B22432CF8D21691E29A6C708AEC753385523DE27D756D4477733072FE65B37278F1C6DF9FB4CD1F89899A618FC7589B52B29591CBF4FD82A2E1D573268F2641908BA44806A170C2E3A7423A511634D079BBF5299424E67818C1AE31F010B2FA19E590FEC02839661EDF9B9D9706161C2F3E5E4B048204176663D000283D45E4871D6DAB8709A55D33E6CACC41ED0911F0D47FB4ACDF4D0742D340D34108F37B15C715FEE70FBB91B5CF1CE8E3B42B3043F6B03F450732F36B61817131F95E07E5C1BD410CA42F62CFEEBDC7D74109DE5CEEF8716E61409B2751942F76546078CB6EEF156D20F5D069B67DCA6C0CE872E2EAD135F4F7329E8CA7D4C283D61C1390C3BE6C30CCB2789246AA198F52C847B8E7D640B1FE2068247A681A68282F10362EA0FA0917A0F8658476B81EDA0A0E661A9B34F1A9F5B01FB4D05D468DC5D1830F4A907F65055A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A682E69A069A069A069A069A069A069A069A069A069A069A069A069A069A0C3CADD303172F1F0E590FCD6493E142A2EDA5412CEDE8516EDF4F4A186409F18F6483E9047ED145B158287B1D4FA830FF4D130ABC36B5EC6DE9B507CD340084F65043B98F997B0F1D8DA357193996E88A7DD07F7D1AEB8F2E21C9FCC6E45BFBBBCB91F2B843BC9D2A07A28B76FA21F1EEA1F23C2DB711F71CB3D7C49012A3D7A075B7AD88A316AF4F06E9989FF00CB6E85615EDC5C5B322FA8B02902FF00B4683144DC5B0C5A3C65C8717F7A5679CFD4BE0C5FB683E8E5F34434E142611D96884708FF00F1206FF7AA8C69F946ED27C67A7A249256FF00A4F505504DC832FAE3E189FD71C264FD97AB919AB8BCDE01E27F67C98D47DE18F9118FAC69A99157FCD1C9B0C14C84CA8D5858A3492107FA26F157ECAA375C236BC6E79BD9DA32531310B46D21CF38A3C75110D4555318C3E23376016A8235CB761C0D8F7AC8DBC4832A1537872623A6EBA8ADF412E07C3D97A2D8D5EB9CC56593E6B1D3AF8720B951EA07DE5FE934458031981ED8CF703D47D7FE9A0CFE3B87B9646F10C5B74E71725354A328332F84B18D4CF74BBF4B74D3D49B0A5A48EE3C57CC3CAE3F2EDBB5F29820C78B18BBAEF10F86BE24451A3659D62322DFC5642EC0F68F785FAD635DE6DE1BDBD775F2D97991E67EDF95C6F3F638F17231F70CCD110472A4340F676955A06944913A0B7460195BA1AD5A927197373E6D6E1B762EE985B463E3EC906E32C73262E013AB18A46B1B88C8D31A993402CDDBEAAA9947377E5E770CAC9DCB37160C8DDB2C832653E976B850A0E940912FBAA0745BD111C1E2E5649666F16693A91D9727A05141B0CFCA7DB70BFB463B5A59007DC655ED627AAC57FC2A3B7D341A6A0506C369DC9718C98D93A9F6FC81A67841E80F738FE25A0C993170E1C878A78A495C74578E40A1B57F86F62ADF10EDA963536C255B06DBBA666C5B8CF0632B26D660BEB51A923925B6B0E348D4BD7E2EE3530BDFCB7CBC8333201C4C1DC112396431263180C9A185FDDF13A5EE16FEAAC4DF6ED8C71F574DB4D3AE65E516C9871217967CB95E4C9C39FC00523E8CAED7E8A48B9F8BBFBABAD718C2CFCEC02CD8D02C8E519ADAE2285947A515DADF5F4A166197B0C2CF970E8C1B96752B27F6E7C90A6E2CC184AA081E9B51129DD793F21C72B785915D880F3EDCB8E4DBBD4B33EB3F5505FC2CCDF771C7CC8678B25EF031894EDA988A5AE05BC4D6FDDDD6EB41CFF0B8872B5DD118ED590AAB2162E52C2D7F4D0740E43BCF24C443143F351E3471C6EF7C28DE2D4146ABE4F89F093D4FB94189B5F22E439091B1F1DA390DBC48701268EDD9F12B29B7A7A50530F33E55B1E54B8788123C7C98CAB433EDD1E3A10EB63A559A53F7BDEB1145CBAE7945BDC32EC31ED73430626523C8F0C38E0AC6C80296D2AEEED71DA7BA85AE83A688F2AF9E3BCE3EE9CFF3D12CF1EDE898CB20B8378D41643EC9598507CF2AF07206ED93BC400FCD626208B0091703373DFE571AFF004C8CE7D01683D43B5EDB8FB6EDB8BB7E38B4189124115FB74C6A145FEAA0CAD340D341A5E6982333896F38DA751930E7D23F88464AFF00BC05073ED936B8F947967C5F6A9CD965CA961727A8FC88B248FF00A2050723876E9B68E4597C67736F023CA711C733F4F0B2909F9698FA016F71FD44FA2823DBC6249899EEA50C4FA889213D0C72A9B4887D8D41D07C9EE70FB3EE1F2B29D58ED76097ED8C906551EB4F8D7E91DF41EB2C5E338F24293CB95E243228747421119585C107B6C47AE82E491F13C11794C6E477B7BE7EB6A0D7E57983C3307A17896DEB5141AA9BCEDE1111D26687A7F15E82C8F3BFCBD949591E161DF7B1FDB4152F30F28B76369171D59BEFAE907EB1634192BC7F8DE68D7B1721971643F0C6F209A3F6699755BE8A0C3CDDBB9A6D2A649B0E2DE308753918274CA07A4C4C486FA08A0B1B6F23D9F7093C0866F0F2C7C58930F0E61FD0DDBF45E8369A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A681A682E69A069A069A069A069A069A069A069A069A069A069A069A069A08F6EDCC3136FCF7C411198C60095876063D74F4F4579BD9EFC5C47E9FF006EFF00E76FBFD53D9B5C76F13F070AF34F9C7275E5526EDB40971F0F1E35C66C9542D1A9637085ED604917B5EBBEB731F07EEFD53D7ECDB49CCD6E11DC5F3CB9D63DAF911CF6FC6A7FD35A79E5C37787FA8CE411D865604328FBC54DBF70A196F70FF52783FF00ED6D7227A4A11FE9A256FE1FD45719F927C964CA509D027E26FC22F6141A3DFBCE5DDB2B6A31E2F891CF9B79648D8DFC243F0442DE85B5FD746F88E51B96EECD945B249CACC63D23B92013D81ADDFF00C228CDB95276F773E3EF1290105FE51485D1EA91BAAC5FCA017F551163277F4588E2E044A21FC0AA562F695F8A4F6C84FB2A8C2831B76DDE72918932A4417217AA463F898DA38D7DA6D506CA1D8F64C720EE7B98771F162EDC9F3525BD73398B1C7F497A0D863EEBC6F0FF00F63C763CA954FBB3EEB3CB9371EB821F9687EB53419D8DE60F2CC672769385B420E9E1E060624407D26277FF007A82997CC3F30E47B3720CEB8EA7449E18EBEA40B415C5E6579898AC0A6FF985BB009184BF64818504DFCB1E79E6472AE63B6F1D973A3C987324272249F1A17F0E2452EED6511DFA2FA6AE063F9F3B3E1EC7CE71F071B7BC0C1DEA3C65729063B60C652666B191C78B1EB23B75B81A6D50733DE78C6F98B91B8AF2094606E78B1473C58B97712652B951680F546B236B02FD57B2823B1EB0C19095606E1874B506DB627DA5377C3C8DCF1CE4618947CD40B7EA3D4A2D707BC03536CE3858DC6ECD8677C3B96D8B16D1B75951C03E096651EF18E1259C6A3D8054D65C72B6CCF0D5E7EFCD9B92D3E406CE98FC05FF002D14137234A5B50FAA9AE935F0BBEF76F2BF89B2724DD4048E3F0B1587C2A3C2880F5DBE2FA6F5A61B3D8787C7B8E6C98BB5E38DEB231D4BE5B3CCB8B8512290096998ADFA9B5EE2E7A0BD063F2FE39898390D00C17D9F74C708F938265F99C7789C7B93C1302FA91BDA7E8A0D16D5245839A93E503608CF1102E0B7503F7D0604B23CB23C8E6EEE4B31F59EB414D028141B8F18C98387393FE1EA81CF7FBA752FD94130E35C9B176EE19C912676326E620C6C74BF6E862CF71DE34B566F95C33F8360CB2E2CBB9295F083394887C5AD940245FF0084DAB48C06739B991EDFAF49F9F8606C816E9AA4B9603D2B536B88BACE5ABE513E24FCB3739E4CA6CA84644AA72140592402E8180E8BD6D5349F9635B59DAB0F1617C7CB804D8EB21902CA8AD1BCCE54F5B00AF1DAB4C24FCA307106C58BB8C30C303CF26A78931E58A401C1FF0010BC920ED1DD419D85C7F1DB6479A383166796253E18DBE5773EF2B5ACD322B7D7EBA08FAEDD20DDCE31C050592E317E400FEAF9633DBE9F128363BFED298D86927C9A22AC1182FF00DB962D2C476788325FC323B3A29B507CD8F0655C1192B8864744BC321DBD323DE6360564F9842F617E854506371EDBFE7B7A924448E28E26B6A4C5312EAFE2895A40A7FAAAC83A947E5A6F5CB3718DF077E8B8F4784B0C4D9492CD065152A0BF85E1B040BD7B1BBFEBAB46E371F2BBCF9E2B03E5F11E591F31C48892FB76E2A0E41E9F75E4772D6F478ABECAC8E19E60ED3C9A1E5994BC9715B1F7CCD23227C3008211C5F547DA1A3503B4136B75A09F790F8BF3BBC34071984182FF00DC25C9FBAEC2338D8B1FA2CBAE671EBF6507A074D034D034D059CCC733E24F0A8B99636403D6CA450738F2AF1258760E2B8F28B49FFCA64B27782B37843FF5A824DC8BC8CD8795721C5DEF7B95E2C4892D918317B8D3B0371AE4ED45F4DBAFAC50705F3B769C2C3F30B70DBF14050E2392100922CE80C66E7BEDEE37B2FDF41CEF072A7C4CB8E588949A170F1DFB994F61FD8683B6E679E7B960716DAE1C54675687443213D17474319F5A765073ADE3CCDE59BA48DE266B46A7EE25C50685F2F72CA7F7E696563DD724FD941F46D7BA38B8C699BD7A1E83E1DAF745EA71661FD2D41432E7426E564423D3A97F6D04BB8DEDFCA65E2DBA725C3DDA3C687697559319E561348480C7428057A03F788BF60EB4C986DF8E79F7CE76665FF3272A316BC6FDFF005D048B9779DDB2729D81E2CCE32E37ED3FE537485D6130C83B1EEB72D6F477D058E21E73EF5B724706EBFF00C8630E8DACDA651EA7FBDEC6FAE83B371DE57B0F21C513EDB92AEDFF00131DACB321F4325EFF0048E9EBA0DC69A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A0B9A681A681A681A681A681A681A681A681A681A681A681A683E1E809F475A56B599B238FF196CADD798E42E448ED8A65F760074896691ECBA8F6D80B935F9FF77BB6978F9BFE5FD6BF70BA7DBFDA6BD6636C79FA493E1D7B7DF21BCB5DEB0A6C6C9DBA4C7393A5A7971679622CEA3A395D45091EB5AFBF261FC9B7F65DADB7E5E75F34BF4BFC878AC336E9B04FFDE3638C17975D9326051D6EE07BAEA3F10B7AC0AAC389CD89910B5A4423D62CC3EB17141F60853419A72442A6D61DAEDF857F79A090ED3B44933265E5A05B5BE5F1EDEEC6BDC48F4FFF00468AFB9D9F2CF3B6DFB57BD20B99F289B2A01F11D47B00EF6FAA88B3E2E06CB1FE4B19731C75C8F8646BFE0BFF008487F17C6DEAAA34B95973E430F14F41FE1C09D00BFABBBDA7AD41771214F8A68FC4B7C30825509FE361EF37B07D741B59066CD8E2273A7181BA6320D11027BC20E97F59EB4196367D10963DA074EEA0C88F0F192203A936F474BFD9415431451A5B4063DB727F70A0FA8888588504B1BF5BD050638FC432141722C0770FB283A6FE9FF90F0BE3BCC33374DFF2C60118661C391D19A30CEE0C8CCEA0E8F7540EBE9ABF020DB8453F98DE7BE56EB9B1B9D865CE6C8C9C8B6A8D36EC3171761751AA28801EB34822FCE79064722E63BA6F529FFDDE4C8F18EE48C1D28A3D4AA00A83485E353EE8B9F49ECFAA82F6DF0EE5979889811C92E48EABE18EABEBBF62FB6825B81E5BE5E4033EE399794F59121FCCD23B4EB99885FAAF41B8876EE23B1C2266032197B242CBA2E3FF00BD2698FF00D804D66ED1A9AD6A372E5DFDE75ED1879071D27490471E2210AEEA8592379A5D323EB61A6CA8A3AD4BB5932D6BACBC373C055F6BE3EF972624B93B66EDEE3430BA2E52FCBBB7872A6BD28CA4BBA95247A6B6C4B8607319E4CBE4386BF28D850BE1B438B8F2C8B24DE0A963AE564F754BC8E4E95E800A239EC85B5956ED5B83F41A0A68140A0506C71086DB1A36BFF8E0803B6C10EAB506F38AEDD87BCE764C022019227971D0B1631A409AA46D22FF00745CB1141D17CBAC245E3B08B098CB248DAA3EA8BD480A49EC3D2AC82730F97DC54EDF0EEDCA275C48669A3971E391920D2ED7011DC960FAC75E96B54B175B62499DE5179719CDAE5D8B1E37BDF541AA0FFD26414472AF333CB29E2DDB286D90EBC61147361C1E1C845AC5590C976566BA9B2D85876F6D15CFF23040DB5B11F0C45B963369F046124522AB7BC4788F2EA5506FD89D6FD944676D5B4633ED6CD3E029C9163A4EDD8F2B30F4892499037B5AD41B84F2FF006C6C58371937BDAA2C59E30E7116084B8727FC338A2431971DFEF76D060F26E35161AAC389868F2AFC5345838B8E3493DBE224CECA4FA2D418D171FCFCEDB63C4DB768399965F56505C086429D34AEB96095DBD24332DCD04FF87705E51858112C7839113B3787324978B4B004EA172BAD0F67A47A3AD6A55B137C2F2E0E7E72CFC86249B1842627C6D6DEF9FBA494616B0BF7D2D445B9170EDCFCB3DCF1F93F97BBB4F8D2492159F8DE5CAD2E365222176452C47DD5B287EF234B0360723A6B6D5C57CEDE0789BFE286DB779504E2E6466D3E0E7C008018DBDE504D994FC4A7D36B06A3CB1E28DB1ED1932644490EE59935B3B1E3164825C7510BC29FC0255774F53504CB4D034D034D07D546660AA09626C00EDBD04376B85F61F32B3E09A60F8D8DB7BE4C18C07BB0366E4296507D7E16AA0C7E75E71E3EDB03C50BEA98F628A0F33F36E4F95BD6FA77494DA6911636FF00C33753EDEB41ADCE866CB9532B1E3676C8FF001511492251F1741F8BB68271B5796DC9771F2EF78DCC18CA6018F35304DCCC13AACD2A91D0585B52FD3410BC51144BA921576EDF165EA3FDEE9F65066479DB94C3463CAEDFC18CAC6DFF00962D4143616EEE75483248BF5BC6CBFF004AD4169B13718EC409835FB4A311F65E829F9CDCE17B0C8D27F0BDE3FB182D04AB76C0DDF63D970723753B7E541BBC6B31C046232D232354724CA812CAD7F74EA3411F116C797FFB791F0E4FFB397F3109F5300187D4683276ED837DCB93FCA423E5D58ABE64AEB163295EDBCCE5507D77A0DBC1FF00206CF9692EEFBC4BBD64C654BE06D1168C75F4EBCCC800B5BFFB717B0D07A3386EDBC366C4877AE3D1C0D0CF1DA39A1561606D7B872D26A36EBACDFE8A093E9A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A0B9A681A681A681A681A681A681A681A681A681A681A681A681A684AE67B56C19FB3F2EC7D6BA524CD69A073D75247665FDB5F03EE3D37F5FD73FEE8FE83F7BFB9E9F71F6BDB5E6EBEAB9FC2DE1D763E5F98059E0463E9048FF4D7E87ABF9F32179862B2309F1CAAD8EA3A815B5BADEF6E9E9A751E0EDCF77C39774DC6485521844F2BC6908530053212163E97D3F87D55322DECB84B992B6E3942E886D8F1FDDB8ED36EFB7EDA82E6EBB9CD9529DBB05828B139591D8A8BDE2FFB7EAA2DB960E566C1B74030B096F2120B5FB4B0EC793D7F853B17DB4469B54924A4825E5637790F5EA7F7D06C31706DA45BAB10093DA4D06E63DB0A22391D350BDFD5D6833DB418C46A802DEF73DB41F6DDFDA7D2682DB4883AB38F60A0B473F1549EA0B7A2F4148CE2C3DC85DC7F0AB1FD8283E1DC231FE246C9E9D4A47ED0283A4794F8999F2D91BA622CE1A53A564C1DD7170F21635ED7F969439992FD194D51A6F3193231B6E9B2971CE364644A4A1CBDAC41295F4A66C2EAAF7F5AD41CA74CB2C8BA575CF2B694451D4B1EFB0F6D04BF64F2ECCB287CE93C62ABAE6823E8A96EDD725FB077FEDA96C8B265B7C9E51C376087E5B15D73255EDC7C35062B8FC525C21FADAB3DADF117127944B7AF31779CFBA40898907DD51F98C07F50D03DA1053A7D4EDF4467232B2726532E44AF34A7B64918B37D66B52619B72A232EACAE8C55D4865606C411D410455128C0E7BB86341E1C9107372C591FC3058F52C50ABA824F53A40BD626B8F15BBB4BE6298F3A5CADEF177090B17CC8B536B62E41566420136E9EE7655D53693E1A7DC5426E390A3B3C46B7D75A65656390F7505C18EDDF41F7E5CD03E5643D9D6837DC7B6EF125F0671648E09E67FE6917C241FEF504AFC99821C4F3A76EC68EFE169C841737EDC376FDB413B927E33E5D6F3998C9A3792901F9348C22C31CAD33DE3CB3ADEF244816C42DFD428B2215BC724E57CD777484097372A52571F16153A541EE8E3EC45F4B1FEA345CBD13E5EEDDC8B6FE278585BF9539F8E0A0D2DAC8881FCB5761D0B2AF4E94650EF3D392726D8A2D964DAF35F0307265962CA9E2D0ADE269531067756017487E83A9A0F356F5B9EE7B86E93656E793265E649EECD34CFA9C94F74024A93600741418BA88882DCDAC7A6AF5FF002D05C7C78976F59420F10B7C77F59F57EEA0A7AB47D589171DA49EDFE9FDD41B7E2FBD6F1B666336DFB84F8484879BC294C6AC220594B8D3A4E927DD2C0D8D07AD7CB6C8DEF2F83ED397BD4A67CFC987C632B001CC723168B5E90A0B78656E4017A1526D341C5BF531288B68D9943E979A59A30A0D8E9B46EEDF404B7D34120FD266EB3640E4903F449C616E057BBC59D1D26603BB5BC5AA83A76F9F2F07279F194DA4CA823CC28077826166FF0071682D69A069A069A0DBE34506D982D9F93612B2FE583F747A7DA683CDBE6073BF96E5BBF65C2F797263C7C7888FC31A927ED341C7378DEA7C899A495CBCAE7BCF65065EE7B4F1C978EED59DB6CF29DC991D7788A62DA1660C7498CE951D45ACAA5BD248A2A61E4AEE987B7EEF3ECDBDB20DB77B8CE34AC40BA31F824527BD1806A23A5718DC72B8A729C8DA7704D4913B419309EAB244C34BA8F4ABA1BAFD141A0DCBC8BCB5E45971ED5E026D61BC6C3CB68DB2A7922946B4D2252204F75AC3A8A0DDED3E49ED597870E4E4676567C72A5D7C5C86850775BC28905ADFCF419981E49F0C7967825DBE03240E03891B265B8650CACA4CCBD0FAC76834C8C7DE7CA7E01818F9D2CFB7470C5818E732492379D0181436AD24484EB1A0DC7B3AF5A088ED1E5A797FCCA169363CCCF8D175117D522AB201A94ACAA0FDE1F7ADEBA0D1721E3FBA723E5FBBE36D29F31FD9A0C5C28A2F152272904623010BFBA4DC1F7682179BB26E38B9A70B2F19F1F341FF00DBCEBE04DED0AF646F6834189910E6448D04CEE110F58240C2DFD27B2831B176F8649C1BE904AEA56EE03D141D37CB7E57B8F17F08ACF27C8AB169710852860F12CD716D5A829D4BD683D23B4EEFB66ED8DF3181911E4462C18C4E1C0245ED753E8A0CED340D340D340D340D340D340D340D340D340D340D340D340D340D340D340D3415E9A069A069A069A069A069A069A069A069A069A069A069A069A088F999C970F8AECB8BC872F0A5CC8F132923FCA655282656EA7576862817A75EB7AF3FBBD137DB5DBE74B97B7ED7EE7A6BBE97FE3BCC5731C9FD516C020BE36C596D91DC924D1AA7D2CA18FF00BB5EAECF1203CDBCE2E69CA70E4C38DE2DB36B97A49898BAB548BF86499BDE61EA1606B3DB2D6DAE2B9C393D224EBD7ADBBDBFD5465BA937971838F818A9F9E1447E20E8B73DBA56DDB7EFA0B594CDB562A428A44F2DD8311F130362E6FE83D107D341A605D98A83776F8DFBE83698383A51588EF17A0DF7CB448A9D6CE0823E8A0BED2B39058963DDE81416E4CB86151A8FBC7A003A924F70A0B7226E124A90B2B43249FE1E3AA99721BD90AF51FD56AB2646FB13CBCDEE68D1E7C64C146E9E26E126A94DFF000E3C761F4375AD620DDEDBE596E99ECB1E1CD99329611B36363A63401BD72016B0EFB9A9982E6DFE4DF20CD97242E365918B2B4123493AC6752806D62F7EC20836B588EB49B0BB8BE4DEEF912E4C6B8B9C8F8EC12468F2E3011C806DD43EAE8C09029D848D3629F69DAA08D71F62CBC8D3242B89341F2D9DF3C8078A83298233DD5AED6247A2D4F91CEF9FE344FE06DD1471604B898FE2BE3433CD9D1B122E48724AAF4F6FB6A632217B1C5E1EF37B1D58F1B69F53107AD4197CCB71DCD36FC3C18CBA604F1F8F92149D32C85D946BB76840B600D6679B5ABE221CB62EA0B05048058DEC3AF69B770AD32EBD87FA6FDD32E08268F95ECAF1E4AABE3B4524926B57EA857A02755FA0EDACF66BAB6D83FA56DE25CA862979062B077456486197590CC14D8BE951DBDF52EEBD13C93F4F1E5165C3FD8F04E7C5BD90F1C79A5C91E2C61AE482C57B53B0AF7F77767BDCE1AE91E61E45B1E56C5BEE7ECD964364604CD048EBF0B693D187A996C6BA4AE4C9DAEECFB65BAD84CA3D81C9FF00AD5279AD5F118FBCA32EE590C07BA24209EEB9AD32CFDBD239F115AC0B27B8DED1D9F655F81903153F08A6057F2AA00B28EB564152E3AA82CDEEA8EAC4FA2AC82FEDD90A566983D8CCC1523EFF00093EF7B0B8B7D158A377E56E4887CE9DAA6245834F727B3AE1483AD04D737CB0DCF977299B33638C61F1CCC9CCAF9121D5F2ECEA256B464AB396D60D97DD0C74F40B7A0ED1C43836C1C5307E5B6C87F35C0F98CC92CD34A477B35874F428E82837F6A087F999C40F31E34DB660E5450EE58F3479587233101648EEA4314BBA86466171D941E66E7BE5EEFDC7B7E836FCB30C99798A268C40F23463592A4177D1DE3BE8B5AA5E1DCAE4855E2DAB32457170CB0CA41EBDB7FF005D1179B87F3238AB08D9732C2C757812F6DCF7D07C1C2795F847C5DAF22117035CB1C8AA3BAE4DFA504CBCBCF26B79E49B7C99706E58D8B186F026563902550C4191940001216D6F7803D868AF51E2E2458B8D0E342A121811638D40000541A4000741D0511734D07977F513BCAE5F3C386931961DBF1228C460DD1259EEEFA7ADAE534DC8F677504CFCB0DDE5E0FE4FF25E598640DCB37271767DA19806D590968AE14F68592676B7F0D0768DDE5393CEB254A8276DDBB1E169BF1499324923AFF4AC687E9AA2FE9A81A6833B6BC4492469E6FF000201A9EFDE7B850722F3A7CD56835E061BFBE6EBD0F650708C88717729F5CFB890EC972563D4C4FA6DABB2823F3ECD1C32867C949E3BF5118218DBDBD941969BA63C18D3A4D843294A08E3B34918C706E75298C8EA7D0DDB4125F2EB61DBF79334D93259B04AB48EA6F332599B58BF45503F0F5B8A0EB7CA76EC9CDD87177704BEE9B32450674A2C4CF86F71899575241B58C6C47AA825FE5DF205DD3685819AF93863A0EF3096EA3FF0DDBEA6F5504970B1A2C7C648621A638EE1476FDE34153A04C847B5BC406363DE48BB2FD5EF5040BCF3C8CDC6E039630A32F919CD1E07BBDBA679149FAF469FA68337CBBD8B1B8DF977891ABEB6F97334B3F7333DDEEBFC376B8A916A23E426CED9EBBD728C93A932F7294E321EB7297F7CF7103C4B0F5D544F393F0ED8F78862DBF3B1A39E0C86758E29175E8708CFAA337568FE1B12ACBFBA839072EF29B3F68477C3126EFB2460993198FF00F2186BDED1301F9A83D9D9DABF7A8398676D2F8BF9F8EE27C26365980B7D0EBD7437ABEAA0BD819E14F85237E53583376903D1ECA0E97C039F4FB26E48AD0364E2E4218DE18CC685417BC67DF28BD3A8EDBF5A0EFB83978F9D870E5E336A86650C87BC7A411DC41E8682FE9A069A069A069A069A069A069A069A069A069A069A069A069A069A069A0B96A05A816A05A816A05A816A05A816A05A816A05A816A0E49FA9EC98E1F2D6184FF8993B94013AFF00D9C7239345F8794D23664761D88013F49B511712592380D9CD9FA2AF70F49A0CC4C1116DCB34BEEF8BEFB7A740E8AA0FF11EB46B1C2C467C2FF32E3A9FF0D3D47B05196D30F79C2C848B1371C58DD40D309D26EABFCC9675FB6832D78EED92832EDF93E15FAE89BF323FFCC4171FD4B5703264C09F160BCF09D07A0952CF1FB75ADC545B142B6AB106E0F7D11F57C6999A2848508354D2B9B246BE963FB00EA7BA837DB171869A38F359A4C5C176D1FDC0A5F2A727EEE347D742FAFB7DB5BC60768E1BE58E1BEDAB3228DB703294EA111D59B329B8BCD3B03A2FF856FF004566D12FE15C6F036DD8704FCBDF31A3579B22601E62CC2FD5CDCD8770ACC2B2776CBDAF8CE2E76F5925A2C06D32662A2EA0B27440EA805EEFEEAB7AEC7D26922E72D06DDE6B797D939D2345BA2C53CF1832E3CEAF0B078C5940D602966536E87B855C237BB9E461E3F1E699D0E4E2649032E6C57EA04ED6795194EA6B33740BD6839F7341913E6481E0C2DEB0F65DB98217638B9EDB8CC3DE0347CBF86922B76FC67DB5608CF95FE59E06F704934F24B06D132CBFDB131E4B344D8F2225CEB17623591EF2FB2A081F9B3C3F27857327F0E46C8C6CA8C64C13B58332B9B36AB74D4B2037B775BD341ABC1DDF6DDC31570F3014B12D138B33465BE2B036D687BC771ECF5CEBCE6352FD58F9FC6228D7C631A4B8EE6CB93175427D04F42ADEA600D25CD4B1AAFED0F87950E6E0B783958F22CD0C82C4AC91B065617F4115BBAA27DC5FF501CF365DEBE6B7F3FDE710E96F0B4C503C72236A592278D00FE656E87D55CAE91A9B7D5D525FD4179418701DF70DB3A5DE8EB9570D849AFC5903020EA5D16F7BA5DEC2C3B7BF3D39CB5DDE5FE45BE65EFF00BF6E1BCE5285C8DC2779DD17B1757C2A3D4AB615D639B67C7F1588C177161A65D27F9A4B7FD5A93CD6AF88927966DC1372E4F99B3730C6FF002FBBB08F0372591A26C79C3B6917040D32EAB5DAE0103B8922B2DA79A3E579F2F376C7C8C2926CAE3BB8828B24A0178A75EA6372A141B8F794D874BFA2F565115866C695418A4047B6B72C15BCB0C6B77703E9A660D664E57CDE5438B1CFA6295D51EC3B149EAC4D62D5891F29DAF8DF1FCBDA7220924CDC75644CBC5F1343C90AFBD22AB81EEFA074B035CF5DADF2DEFAC9E1DB78CF1FF25F6CDA24E71B1A7F71952078E3D5334B9065993C318CB093659E4D6230BA6FD7D75B736FBCAECADC70C66F1ADD6210676195C958836A50B93767453DE166125BF834FA6827F6A0F3C7989E65F269790E6E06DF93918F818C906498F01996799652440358EB1A05FCC6D3D4DFD556411CC1E53C915B2A49B76CA29892397F9FCA792062AC06875999ADA83000A59C5AE0D5A2BE77C9937BE45B3E748ECB8397B762E6248E1B20AA953746451A8D98157F491ABA5ED595CB6599CCA14DA171A418AFF95AB0E45C631ADEE469D2C2FDDDE6AC88D4B7981BEC98298129C56C3017442621A4683716B1BF6D5C41BF8B92ED99BB33C6AD143E1A2B4EE90195493704141D6E0D4C2F0DFF00927910626E4F145927222C8668CB18248140705D7E3FBDAA303E9A8B6F0EDB6A32F8FA1519A42150025D8F6003B4D078672668B2F7E9268D49C7F15E5441DBE0C5D235F6944B507A17376351C93CABF2A5C066DB90721DFD07DEC925A4B30E9D8FE21F6551D4F6F2D91B86F3B8BFFF00B99F308FFEEF142E227D07C0D5F4D419F6A0FAB1B3305517663603D668307CC5E4B8DC678CC91870252A75107A962283C6BC937B9F71CF9B2A662CCEC74DFB8506932D64925C7D04FBD12F51DBD68337170D4B18A3222455F1322722EB1C62C0B9F4F6D94779A0E97C038A71CCDCEDADB924CB858399294D876797DE69E56040C9CBB5AE643EEADEC2E428E960435FCA360CBF2A7CC3824C6D72EC9957970D9BAEB8091E340DDC5A26ECF569F4D0757E359F850667CB05F99DAB2B19B331141D427DBB205B3205F4B456F11477327F1506A76E397C2F9A49865BC686290343203EECF0482EA41F4491B50765C61036345263BF898D2A092092F72D1BF5527D7DC7D60D032118C2DA45DD6CCA077953A80FA6D6A0E6FE6F654F2C9B56DF8B92629F31278F063001F132A550213D7B9155FE92B52FD167D5BAF3072A0E3BE5D66C70FBA98D866183D4238F4AFEC15516BC95DACEDDE586C3130B3CD01CA63E9F9876957FDD714131F0617CA129553240A511FBD7C4B161F52AD062EEBB6C79FE1C2469601D84E09052EA5411A4AF5D441EDEEA2B93F987E5A4811F73DB214F9E7F733F09069872EE09E8A3E094D89523E2EEB38B1238866E2AE3C8248096C792FE1BB0EA083EF23FA1D7BFEBA0C9C0CF30B017B2DD5D4817274750BF5D0768F2939C6E0331767DC658E482573A2E1964469017172495600823B05AF41D9AD40B502D40B502D40B502D40B502D40B502D40B502D40B502D41734D034D034D034D034D034D034D034D034D034D034D034D034D079FBF569B8018BC6F6D07AB3E4E532FF2848D4FDA683CEA9AC9D2A7E2E86832B0718666747075F087C56EE55EDFAE8361BB4C32B3571874C7806A940F40EE1FB0506A72E6F167627E08FB8765FD1FBA83330706458A3C8907BF957317A446A7496B7F11040F61A0DB61468B726E1D7A5C7436A0D8E3E5E542D78DC827B483627DB6EDFA455C8BAD93B7E41659E10B2DB534B091135AF6B9FF0086DD4F7814C0D86CD83B1BE4C31E5CC5F122B326211A0BCC7FE2CE189F12DE85EEE805A92E074CE39CA64E2D226649B68E48CCA527CDC172D998F1A9EC8B1E454FCBD3DBA7493DF7EECCBCAD8E8FC339D70EDEF1A28B6ACE8D1E72F2E3614D68672ACEDAAD13589D2FA94DAFD955126C440B8EA80DC2164FF6588B7D941CBBF50FBBB62F1CDB36E8DECD9F981E68BBDA0C642EDF4091A3A2CB87992404E54F9AEE922BDC4214DEE4F4028575EF2C3166D9B39F1B71DC27C19B1E519073221E3E381044B365E3B6A5280FCB202CEC08048EC22AA2FF0029DD659F62C59F70930669775C99335F73C0660A9023018933C7A83497724333214E9DA0D51D8784F1F6DA31B0A2963C5195F28464E5619BC790DAD6D2FC2BD580BF4FAEB231F9D707D8B97E0666267ADB38304DB72E25D73445101E809B142EEC2404807D2085203CA3CD380722E21BA0C4DCF18C6CDEFC4F192D14AA3B5A192DD6DDE3E25EF14187B4F24CEC69D8425DE33705080CC53D0EBF0B8FA2964BE565B1BC8B2769DC97542CB8939ED1726127E9BB47F68F653367E2B6CAC4CBDBDD18C7325AE2E3B0AB0F4822E08F58AB317C2596796A7236588F54BAFB3B2AF54584D9ACDD5AE3BC016A9812099570F12316D3F2F8C3A0E9667BC9FF005E9AF8AB5129722399C3497042AA5940B5945BBEA224F9DE6A73BCCE3ADC7B27767CCDA59046D06443048FA1482BF9CC8D2DD48166D571E9A08FEDD0C394D378F9D1E118A26923695646F15D6D6897C2573ADAFD0B74F49A0A70B033B3E78E1855A59A56091C7183248CE7B1511416663E80282F67E3E4ED7912EDF3624B899B11D13A64A18E653DB6D0C015A0C1D5ADAF212C7B2E49341958594D8791164E348D0E4C2C1E29A362AE8EBD432B0EA08EE2283ADF02E5BB99DD932B3B2720B648559B22495C4A60C9090BBF8C496FCB710C80F72AB53233F27CCCDFA2C2DEF6CC1DF370872D65221C2CD09364C6FE268961336B89D1545DB508D81B76D061E04183C870F1F75DBF24AF20C7C14C7DE311D1892306EB1CB1DAE6F246EA07AC56A78116458B6E924DB7772658B2247965F9846576566054D98825BA1EFA7818DCC391EDB93C9F18ECB0CD8DB46D58B0E061AA4BE0CB6404B378B63A753BB1EBDD52D1B9383FDDB6F8A5C732E56746A11A1791723A5CF4F145AFDA4DEACAB851FF0027F26688E9DBB429F88978947DAF4CC591B2DB364936EC49D32249A0CB91089228A448C01A811A6504ADFA7A6994B120E1593162652E663E44F94F0B7E624D92D29568D8483A6903B52A19E1E8B42AE8AE8752B00548EC20F654443BCE0DFBFB17975BCE5A3699E787E531FD3AF24F85D3D6AAC5BE8A0F3D7913C44722E7FB724A9AB071E759B249F84438604CC1BD4CEB1A1FE6A0EB3E596EADC879DF98BE6BAA78B1C046CBC7011D1DBDD8E3B7F3DA21FD5568EABB6E0AE0E063E1AB171046B1EB3DAC5458B1F5B1EA6A0C9D3419BB722C625CB93E0816E3F98D0798FCF4E6D26E7BBBE146FF0093193AA838A65CC59ADE9FD94196013042DD4B040802F53DBD83D67B283A17977C25B7DDF31F6A203E2E3BACBBAC87AAC990A0B08BA76A42B71EB37F4D048FCF6D8F0937D860DA2097FBA6060AE63449F0BE20628E630BF0B46C81BED1539CAF186E76EC8C6F393CAD9B6DC8910F2ED9ACF0CC7DD2D32022294FA1675F764EE0DD6DF0D54437CA4DF7364997629E47C5DE763964CCD991D7DE0F19FF003588E87A9BDAFA7D4450751E63B745BDF188B77DB54789B5443231F41D5AF6F90832460F7FCA4AD6FE4228369E5D7279F33609B02201F3318364E244DF7D548391083DC4FC6BED6A0BB8FE644B97F2D0616DCB91BA4F2CF1C9B709C23C69082DE2177454B328BFD3E9BD0739DCF2F1F92F99BB4EE99996D0ECDB4C72CCB1A29B451E3072CC240403F9A147502E2C3BEA45ADBF9F3CAB6FCEF2F447B6E52641CE9A38645437657B83A197B54D81E86AA3AE6CFB747B66CF83B7A748F0B1E2817D1689027EEA0C8814F841DBA17F7CDFA5AFD403EC1D283EC443AF883AEBEABDDEEF77D9F6D059C9DB71B232572255D4CB1B43E1927C32AE416BADFB7DD041BDC1ECA0E09E6CF18DBF6FDC32B320606292CFBB40A3DE4D4DA63CA0077EAF764B77FF0035072ECADBDB11ED7D68F668A45EC21BB08F5506660674D14D1CD14AF0CD03ABC7246D690CB7E87A823A0A0F53F00DF86FBC5F1334C825997543338B5CB466D720002E4589F5D048B4D034D034D034D034D034D034D034D034D034D034D034D034D034D05CB502D40B502D40B502D40B502D40B502D40B502D40B50798BF52BB8EDCFE636DD8BB9C724B8387B6AFB911B36A9A576241B8EE5142388B1B6A70349ECB5AC2E7D1F4506D36655C6C1C8CD6ED6F753D8BDBF59A0C232B458AD331FCDC825CFB2E42FDB73F450365DA64DD374C5DB51847E335E698F6471A82F2487D51C6A58FB2837795911646E2F3429E162D8458919FF870C634C6A7D7A475F5DE82F80AA7D7E9A0FACECA2CA0B3B7BAAA3A924F4000F49A09370EE3106EDB8012C5F318581228C9D36272F318D931A327A685240F5F6F61E9BF03BEEDFE556DEF0BCDBC478D9B939810E5C1342248E3B75D104819248CA83A7583D7B48AE772B1A3DE3C8E8627391C77719F6E753A93167BE5E383DCA8C34CE9FCC75554736E77C3B9246A8BC876A6C9F00398379DB1BC728058BB315B3800B027C50282BD8FCD6E75B6EE37D9B73C7DCF6A6D16DA770B89230A8AA7435965EA413EE922FDD41A2F35FCC2C9E51BD0CE9B1DB023DBA05C48F10BEBFCC762F2BDEC3E2E83D805073C9726483C23091132B2CA8C00043DFDD6FA283B7718C1DCB66E29B8471E54F366EF2165C90123CEC38C4843317898ACA5A48974B80C47ECAAB843BCCBE46B16F4E76A8E1863831A3C2C69221246EB028D417C170BE0957662A1147AF552D44676EF32B99E02A243BD6E28B12F871A265BA2AA5EFA42D98017A83293CD9E73007F93DEB3E0695CC9296C8D7A9DBE263751D4D0606F7E63738DF307E4377DE27CDC3D6B28865D04074F8581D3A8117EE341A359D646566262C8520ACEBD3AF7136FDA282F61BFF9CD39192718DDBFCC05D5663D9A80FBA4F6D06C3166DD30E3926C49933F0C1D5958FD59558F6B321B3A7F3AFF00AAA59F3F2D4DAC4821C6C5CD8D5F0E4025600FCB48C3A9FF00EDC9D15BD86C7DB5AED679319F045B5C8B26BCC8DE0C743F9ACE0A923F0A83DAC7B05A977978863EAD4F21CC6951C1E8F90F7207601E8F60A6313096A3124611B4DC13EAEB510B58FC57FA283EEAB0B0E941BAE33C9B7CE3D91FDC363CD383B8A86884C151FF002E4B1616915D7AE8F4505FCBCAE47C8172B24BE46E794B1B65EE13B5E69161805DE4676BB2A26AEEE96A08E91F55066ECF8706567C5164B98B1C9FCC93AF416F50341D7F65E3225DA36CC9DAC4996925DB336F84ACDE343E1B40CBA231AD2411B32DDBB4D4EB3395E7C2E67F969BB72ECE9F3B113E672A2057738957548D938CA15E54006AB644663980FE3AA8DAF973E59E566C9BA4BB560E4E4EDD2631C2CE79DD61086421D911E6D1AB4F86350B7BB45B1F7956271E976DDA71F70C0693036532E3C2D34EA81D4B92EBF30ACABF17C26AE088BEE3C7B89646E0B2EDBB4CA2052131E5C69CCF14920B7BCE5CCABDA7BC8161504D32BF4E9CA73E1872A0CCC131320F0B4CD1D98753705540A230B72F2679371FD8679F7541FDBA1D3F319904A922C6A5EDA9914F602DD7EBA0C7E29C1A6CF69936D944EAE8D261CD2AAC514FA1B4B2A17BAB376F4BFD3416F0F07FE5C69A0C8568729650B363BF4296ED1F6D0CBAD6CBE66F0BDB761C18778DE71F0F2122D08B23105E38CE9471D3A82A075F4D4972B661CBFCFCE7FB472BC5D9F62E2D963751E349939298EAEC75A2698940B027A3B9E955247DE26CDC03C8CE4FCA24BC3BAEE110D9B6E045984D926F3117B755561FEC50754F2D78AFFCB5E5EF10E30C9A725E23BDEEE08FF8B259A346F587916DFF00774139B502D4187CE3754D9386CF293A5DD0B7D63A7D941E25E45B93E5E6CF90E6ED2B93D7D141A351A9EE7ADA8375B7EB3E18810BE4A11E0201FF0011BA213EC37341EA7F2BB81E3ECFC470C9FCBDC641E3A652D8B82DF7BD624ED23F0903BA8303331F226F3F36F79F18F81FD8E5899985E3621D8B007B08F7C76FA6839973AD8B77F28BCC1C6E4FC7919F67CE90938C18E8D2C6F2E2C83AFBADDA8C474363DA28351E71E5E2E2F35C5E5FC691B1933E18727235A9468F334EAD4C83E12C856FE960C7D741D2BCA7E718D2E36D9364347341B924B8D2406DD3203B0F074F601223327D28682D64C193C279A08715EF87232666D390DF0BC0F731EAFA098DE824D9BC63033F79F9DC26F0B1B3F19F3305C9B153F0CD17407DF8D89FA8D04772B8A418F810ED9B5C88AC938C99DA60184922B78AB1B7A503F520F427D56A085F378962DEB8E60BC2F2CF9DBB1DC371C68801A99194324216FEEDBE1A0EEFB5F257C995B67DD4458FBAC8C55218E4496F111AAF2683646D3EEFACF654CAE1249119B4C67EFF00681D0691DBEDBF41551734D051245AD7496603F84E93F58EB4113E61C4F167DB2618AD06378BEECB14A81BE6359B3426420BFE62DC753DB6B50798B946DB3EC5B83ED523F8B8D0CB6C49FF0014128D5137EC141AD8C80C42BE8722C491D57D6283AEF901C862C4DFE6D94B18E2DC6332450B31235C562ADA4F404AEABFA7A507A06D40B502D40B502D40B502D40B502D40B502D40B502D40B5057A681A681A681A681A681A681A681A681A681A681A681A681A683CF7E726C31EE9E6C63631E3F979E1F0236973F15FDE172C232AAC522F73491666EA4FD145932E15CB1234DF32313184A6282578E213E9F16CAE557568F7755BB6C4D11F7755F96DB60C4ECEC0D6FADBEDA0D7E678724A3C26D70201620100002C075F6504978C620C2E27BCEF6E6D919CEBB36DE6E3EF813E6358FF00F69523B8FC6682DE2A44F093D2C0755F5D05E31B05EA283EE2C190ECF2C22F3295871BD534BD357FE1A5DBD46D5AD60EEBE5A701C2863846364A0CED97C36861793DD7CDEB249E2C40EA0AA182DEDDA4FE1A968ED31DD90165D2D6F797B6C7BC541569A0E53E6D79BFB1F1823176F74C8DF2162B238B94841520A3142A5DEE41D00F4B5DADD2E1E64CADEB277CDCE5CA9DA3852476972269007924773A99B4A800B13DCA0014165F78700E3CEC3271D7A08B2078C83D16D475A7F4BD053341B5E648B259F1DF50255499A220768B10255FF7A827FC739F67C1E060E6C4B93B5632698F2A1600C60825FC403DE3E800806B52882733DFA4DD774660EAF8B17B98ECA2C4A8B0EA7BFD5528C5C7E31CA33310E663ED39B918CAAAC7223C795D34392A8DA82DACC4100D41912701E71119449C7B724308065071271A437505BDCE9416A5E19CC22B89363DC12C2E7562CE3A7A7AA506BA7C0CDC699A2CAC7971E54BEA8E5468D85BA763007B682D44AB73E20363F7AC4DBD7D282E983330E559A1764703524887B47A558768A0CDC7DD653776D0B2FDED2342BFF32FC37F58B559467FF784D24BA30641D41208FAEF5AED069F3F2A49A72DAC5ED650A3BBDB58A3E4581A7AE412A6D7F085B59F6DFE11EDA0F99518F8D42AAADACA86F607A753DF418D41771C5CB0BD8DAE0FB0FF00A2837B3ECBBDEDB062C99825DBE3DC221262CC6F1F8F8F2A83AA3EA0346CA7BBA5062626D67364C7C0C384E4664D28481635BBC8CC74AA0B76DCD4C2D7AA3CABFD32EC783045B97360372DD58065DAC37F9687A7457D36F11C77FDDF6D546DF9879E3C7783EEB271ED8B6785E3C31A26F074C31AC807C21117EEF79ADCD329D9CF78BF346939536F786BF20775F10F831F558B33155A44001EE9711A541FF76B4B157F7AF3C375CBDBF76D977CC98A1C9C4CA8523897F2BE65158F88BABD04696A6D848E0BCFF79CCDDB916419432E3E33B458F0924A205637D20FA4D4C2AFF975B86760721C78E052F1E53AC6F1B6A29EF1B062A08BDAF56413DE4BE606FD0E5E3E08C1F9B976F42916BD5E1C45DB5B828846AB377936ABA9646971BCD5E6DB866C1818F9C207CA9131E348912341E2304B1D23B3AD6B8670EAD93CFE5510E260933E26D884C7B8E4328479617D0CCCAC2C359B903B85AB18699FCE3658392F199F7DC14B6562C45F29FDDB04563EE8B12C485F785C7AAB1479EF97720CDCF6C6DBF2A2443805AD206662FAC28BFBC4851A547BAB5CF4F57574DFD9D8E11B9E460EFB0BC081E5981857A0362DD41EBDDD3DEF556B7D7313D7BF5B9772E79B39DF39E796DE53C62F8F80ABBC72141F89AF2387F5E8461FD75A61DA30E5F9EDCF73DD3A18E598E26211FF0061864C43E83378AC3D44506769A0AA38CBBAA0ED6207D741CAFF0052BC84E36DD16DB1B58BF423D541E53CE92F2DBD03FD741462C65E455F40D468271C436ECA9B73DB530F15B2648633B964C708065923F12E634BF7FCBA165BD07AEF6983696C1C7C8DB027C9CF123C0F11215A32B7422DEA341CE799EFF26D3E646887C15906CC7259A59DE26912199AF0A0EA9AD83369E973D84FA0397F29DF367C8DE73B70DCA6DC66DBB73B23BA781998AF1AF4118219258E584FDDEB623BC5163220F2F537CDA65326F0990B978F68D8234715C9D714A16CCA197A03D7A75A929869F72CDE0F8591B76D7C7D1712775F0B2E38DE6765CA882E9965697DD12B3861EE74ECF554D6DF96B6C7C3B0674639DF9729990203BD6D1AE75451EF1D36F9B847B6E2651EBB0ECAD30C5E13BAC9BC6C52ED6B214DC202D97B6B824309916D347FF8B18D5ED0DE9A0D14FC97363DD9BE77026580EAD220952F620AEB54648C5EFDFD6D6A0D1F19971F9E79B18F9300923DB769C5C825CB0590BA7BAC56C750EB22F5E94137CDCCC5DAF7BD9F030364C195DE58E149F29CC4D3045BAC92CCAA55641210D7D0C7A7AEA359E13CE11C961DF31B2D1E649773DBE5F95DC3C2378F5A0F8A327B509D566EF20D56524D340D341666C6698825F4E8B34361F0C82FA5CF6DF49EA0765079FF00CFCE3F8820C1DE628C40A646C6CB8D7B15A5BB006DD9A24D7F650722467575F116CDF7C7A197A30FAE8367C7F778F69DE70F3EC03433A4A92B7560E08B106E2CAA3B683D9B0C8934292A1BA48A1D4FA430B8A0AF4D034D034D034D034D034D034D034D034D034D034D034D034D05CB502D40B502D40B502D40B502D40B502D40B502D40B5070CF3079745B779C38E99ED932EDF850450AC10ABCA89E3C52B492685BF5B3ADFA5EC2A3AC9C4729E518DB265EE92EF38B8B1C473279B231B42945581E46F97023F856D105EEAACF5E1B4E1DB8797385812CBCA388EE7CA335F21BE57E4519A18E3544BAC855D3A9637EC34612FC3E77C46152368F22B3B2149254CD8EC4127DB04B5702A939FF0037C80B0EDDE4463A4018948E7C27700B5AE7FC088026C2F53028CDE5BCAA0C766DD7C8380C7F7DA0C774E9FD1039A08E0F303C89CD9FE539370DDDB88E49363261CCF2AA9F4B472E861F4466826DB7F935C7F7ED8F0333CBCDF7FBBEDD999C62C9CD95743E244C979DAE0C67C50A15554C7DF573C0EB989C4BC94C980ECD8F8BB364BE1B18248D5E17C95914D9849206F1BC4D5DA49BDEA0BD27967361317E3BBF66EDE3FF00E93288CFC6E9D834CFF98A3F9641411FE5FB579B0DB24F838B1C2B3482C776DA4DE509DE171F2191919BF123B11DDD7AD0795794796DCCB0F389CF88CA16FF009643C3281DB610CC11CDFD2BAA82379F26463968CA18F2146831B218C463D0AA7F7D06B21825935B2D808C5D8B76506C7669E15F19A72BA021211ADD5ADDD7A0C65CBC88C2CADD189254F50C00EE0C2CD4190D3E1E4461B2046CE7BEFA641FD4A3AFF529A098714F30F95F1F8A2C6DBF78963C15B018F923C78046A7524601D5A5035C9D0CA4DC8A0ED9C6FF00517B63F870F26861C77603FCEE0B48D0927D314CA8E9F43351709DC1E6570DDC9628F6CDDE179721B424843A85F77516F7D40242F60F4FAA88E37E7D6FFB42728C6C7C30B97938B83F2E918BC9A5E5667727B6ED629EB25A822D8BC5B04F93273B2258A1DE1735D5308B049462B2AC57F08FBC5BC5B48CDF868397E33B233444DC313AE33D9A874B8F41F58A0B393145ACD8E86EEBF4141F31B065C842E96D2A429EF3D6E7BBD941D2FCA3F2FB87EFC93EEBC8B90636D5B6E1CDE0B62BCD1C395390AAE48690811C6755AEA0B1EBF0DAF41DE71FCC3F2278CEDC98385B8EDF0E24170916346D3924F692C8B21763DEC492683CEBE71F2AD8394736CDCED8183EDCD8F8F1C2E22316A78C59FDD60ADD2F6EA2831371E3ABC732E1D9E4D8DB79DE1B121CDCED523C490F8EA1D6245500B3056009D5D5AE00E9D6E0462593072656CBC181B1719C146C767F10C6E13DE1AB4ADC1ED1D3F65419DBB725CFDDF6DD936ECB6BC7B1E3C9898C4DFE0690C82FEC042FB00A0E95E5BF969CFF003365C1E53C580872BE6258B1F204C90BA14281653AC1D710F7D5957A9F5D164CBD1591CD67CCDFBFE5AD8E3FEE5BCC48A7719518C58D8BD002D34966B127B11413ECAB818595E4EF04C0867DE39064409103E2664BA2CB763D7DF95A57EA4F753351CDF3B66E092499D070CDC533776531E5E0B22347199E07324513AB002EE57C3D4BD0AB11EDD628E5DE60E2EDCD9F06F52997FB6EE71C43C7834DC432A878B5AB0373A41462083AD5A98C8EBDB66FBC20EC185FDE3628F728A4B4387B8E1AC532CC8000359254AC9DC43531165AB5CC798F05D8387E6646D7B6A6DBB8C913478C72228C4A646B80B08058B1EF2DD8A3D74E1735E5B8F2B27C43278ADACDEE6E7BFB6B3965B5DA37F8F0B2D279F123728E8EB346A16446460C197EE777516FA456E51379961DF30F357679D64C36FCE8630DA6647321731BC67DE3D0F75C1AA3B16CF26F90F17DC06CC9FDCF23C190BE1C76912F6BAF880756655EC406E6B9D5932E05E616D98187BB1184ED24322A480B2B2D99946B51A951ACA7B2E2A224DE4270F9B7DE698595222FF6CDBE65973A46F85628D5A5627FD80BFD541D37C9CDC3237EE4FE62F9B0575CD9337F69E3A5FA8D5232A44A2FEAF017EBA0ED5B7604581818D8511263C6892242DD4908A16E4FA4DBAD064DA82F6128F9A8C9EC06FF00575A0F30FEA23776CCE566106EB174028387CEDAA47F6FEFA0BF8CA4ACB203D5469503D36A0EF1E40EC2B99CC778CE766F036C58F1A2D24ADCC31AC40123BADA8D0757DB625E27B836CD2073B1673B49B249AD82C3337BCD82C6FEE866BB424FA4A768170E41E64F981BD64C3266BECD2F1FCFC2D38F17CD223CB33C8E4DF5CD193A163525429EDBD16B9CE1798DC920CB0F933B4B89202B3C312C71B0B8B7891B2AFB920EE3D9DC7A54C23A54795B82F088A1FF00984C5B9EE6D7DB73667303CA0B8744207612BEEB5BD34C2E5C9F9B71FDDF6E5877ACB99A7C89E42999393A8ACEA6E87577DD476FA455475AF2439ACD1EE38B329FCADC0AC5909F75332304213E85903143EA7BF7506FB956D72F0FE6B166E0130ED7B932E5EDF2DAC2362DAB411DC637E847E1A0B5E63ED61F1977FDB2F163E740C638C02C1727E06C7B83EEB78BD14FAE8396F11E23C878872B9A3DCD862EE1E00F96C8C799268C3BBFBC19A325587B855D4D4972B75B1DC62DCB6BE49B36367261C09BEE14E5B2305B52C2B3443DD27D11B28B8FF555459F2D36C7DAB79CBDC5F09E2FEF19722F8B8B24476F485817545504B6B4946916EE341D5C1524AA9048ED03BAFE9A0FBA7BE8289C4E63231CA2C9D2CF202C83AF5F741527EB141C6BCEAD8FC6E3FC9A16BB18B122CF86C48BBA3169188EC04F84C3A507053224D1A4E84113471CD71D9A9D06AFF781A0B266484ACD601ADA6FDAC45EFA147AE83D83E58672E7F00D8F24306BE2AA123BBC3BA69FA34DA82516A05A816A05A816A05A816A05A816A05A816A05A816A0B96A05A816A05A816A05A816A05A816A05A816A05A816A083EFF00E58636E3BAEF1BBE3E618771DD6058164953C4580AC260D718568CF543DE7B68DF7E1CEF78FD3DF269E3861C2CDC2D3122462691E542422851EE08E4B741E9A25DB2E8FE4BF05DD780ED7B8E26E19F167367CC93AAC11B2AC6553430D4EC59AFD3B85565D14EE87D07EA14E0722E5BFA84CEDA77FDC366C1DA526976F9444F34D3901AEA1B50454BFDEF4D32D4D72D1A7EA837C8323465EC58F225813E14EEADD7F991C55984B30DE637EA0BCB6DF8261F24D9A488CD68C8C8823CD86EE74DAEB76EB7FC14C23A4F09C3E1D0EC6BFF00258C587656691A25C2B7842690DDC95EE606D707B2A51E4CE53FA4AF36A0CFCBCDC2930F7AF12579BC58A6F06672EC58B1494280C49FC66A08FA721FD44F96442CF2EEFB66245F732D4E561FB01904B17D468279C57F5ABC931CA45C9B64C6DC621D1F2309DB1E5F6E87F111BE8D341D7761FD4AF929CAA15C4DC335701E4035626EF0854B9E96F13F321FADA8369BC7933E52F33C31938B144637B98F2F6D995A3EBE800C91FD428397727FD20E7244DFF2DEF293464DDB1B2D0C6C47A048BAC5FDB6A0E712791DBEF1D39ADCAF67CD68235FF2F3E259E1D57EACCF1F89D00EEA0E6D95B698735A27D6B8A841D441D5A48BD8023B7BBB284533E161891DE099FE589FCB0EA0C96FE2B103EAAB35B8E56AF6C79B06DFB94191918EB9D8B1386970E562892A0ED42C9EF2EA1DE3B2922364FB9ED191BC0C9C3DB462622CAB226D4D319814046A4F15D54B7F50FAE981972FFF002DBE4E62C43898D34AA3176E26F1A10B72D60156FEEDFA002E6B1BEDD665D34D7B6D85FDF62CDCA124DBA4EF3E696D265763E28D2BEEDDFBFA2DAB1A7B3B55F6698578FCE37BC7E392EC5E32498991FF00B89A48E2333AD805532B895C05B74D214D7572470BE1172E60F118F536F11AE4FAC151F6505B392435F1F11633F8982B1FF7EF41F21973524F119994F5EA8C148BF78B014171B1A2CC1249E188A745D5E304B44E7D0E83E063E95E9EA14121C6F2BF99E447B7FF006FC55CCCADC2233418D8ED1AB8500312C5C28E8AC0F46ACCDA56AEBC2C728F2BB9BF0EC0C7DD77FC35C3832A430C204D1C8FE2692F62119ADD14F5AD32EF3C5F9B7915CBF69C4DCF96A62E2F24860C78370F99127E63618B45223282A7D36F887656E51C97CE2DF3CB7C8DD23DBB80EDF1E1ED906B93372E30EA326723A36972582C63A0ECB927A76566888F0EDB769DCB7FDBB177699F176BCACB8E0CACB8F4EA8D65F74302C0AFBAD6BFAAA2C7A1139E49E5370FC3DA31309377962DC33A17C8C99BC281608584B168650C35CF1480A0EF37A095712E59B7716C275DE3066C0FEE8E7701972A28691720788A1D9402CCA1AC41F781BF4B55911BAE40FC279AECC71B2B71FF0024AC2566825D0C0806DABB7EA22B5260725CDD8FCBBD9F2F1E7E2F99947316655C99656D416357560E145AD62BDE2FEAEDAA219CFF001F2DB132B1B6EC46C988EF59D84B851C4F2DD0F8198A8BA3AA68C8C89954F75C8ACE46E337CA89F82C31EEE7912E13CB1863B5C91F8AEFE22D9A368F500FA6F6D5D2DDB7AD6563430F1FC5E6FBCE0626E39B9AF38031B19D228CAA82D7EAA0963D4DC93539384DE3FD276287B9DD5D96FD7DD03A56714885F27F2CBCBFE3FBACFB5E5EEF96D978C409A38A1D562C2E06A6217B0D6A657312BE21FA6CD8F97ECB16F3B1EF12C786ECD19F1E3B38743671607F655CE19776E07E5EED7E5B70ECA86397C791165C8C9C961D59F4D8587B00158BCD1E4AF346145930A5BEA9272EF21FE6B35BE8BD2C137D9731F827E9DF7FDDD5D5772E48E9B66169E8EAD90A4CBFECC1D7DB50765F2D3868E33C0389F1C9134E44511DDF741FFF007130BAAB7B1A5E9FF774139B502D41544DA097F42B7EC341E40F386732F2EC9627B18D072C24DC93E9A0CFC37298ED28009570C01ECE84507A4FF4DFB738D87333E296D9996DE3CCAE2F1B0964702E058DFF002FA1F5D0764DC36FC3DC30A5C2CF8167C59D74CB130D4A47EDE9E9A0E05E7EF1CE438D83B6A64EEED9BC7C4FA311A5B7CCC52693F972482C26F76FA598EAEE3D7A90E1671331324421194B908BD2FA89E941DF376E0B92F2E1CD91911A6CA31228370C09F507060B98E6C623E09416B5FA7AEE3A51647CC9CDD9F126C619291E4E24A92C9119F1D3291ECA02CAD1B80BAC7BC3B3A5FE9ACED2E385D719E5A3E19B4ED5B4CB9B99B6C826C2CCCB691142E8F097AE98F41EAA74FF00AAAC4AEE3BBE3E0731E0D262CED1FCE246D3E2CF21B059E2176EBDDAD4EAFAFD15510B9760CC9F88FF006ACA955E1934CA62D4436B000126917D17B0B6A20F4BDA839F7255DC65DC163CB6092632AA8902A47F11D5AAC8145EE6E4D4930B6E523E0393E07298E369448BB863BC72B8370D241660DF531AA893479DB2E46FB83B4450432EEFB717CC963F9931C01A37019D5546869C860742AF4EF6A83A8E85165F08155B58802C3E83FBAA8B80AB11D6EC3ADBA83F5507D0A477DFDB6FDD41CD3CD1C49259B295E540995B7C98CF0A820947D6149B93DECC283CBDB3A0FED3823BFE5ECDED59641FB2813948D11CB05B1215AD76EBDC83D2683D51E41CED3F96F86EC2C44D32E8EF5B3761F5F79A0E8B6A05A816A05A816A05A816A05A816A05A816A05A816A0AF4D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D034D0795B9F628CAE59BAEE18EE1723E73222707A8611CACB66F58B5AA3B4F08666193E61BC5015EC2E07656A572BE54E0832EEB869F8A78FEC707F753E51DE7F4EDCC5B038E47B4C98D264459934D344F00567492303C52EAC56EA469B69B9F556F61DB31791ED9912784990866EDF05EF1CA3DB14815C7D553033D9F167468A5557461664700820F7106A62883729F223CA8E4E0B67EC18F0CE7FFD9C21F2B2DFD3787483FD40D41C77957E8A7198BCBC5B90345DA5713718F58F50F1A2B1FAD0D0730CFF00257CFCE0392D9BB5E2E6A88FDE39BB24ED20207A5222B27D6953033F60FD54F9C7C6E7189BC3C5BAAC46D263EE5018A71DD6D71F84F7FE60683AD718FD6870CCD0B1724DA32F6A908B3CD015CB82FDF7B787201FD268266A7F4F7E6643A71E7DB3332E51EEF84CB8B98A48ED087C392FFD3410AE4BFA49864595F62DDC30B13163E64614DEDD14CB10EB7F494A6470FE5FE4E73AE2CEEFBA6D13C78CBFFEDC03C7C7B7A7C48F505FEAB5043A4C29E29991E360076F4BDBDB6AB449B85F18E53BF65AC3B36DD959AAAE099201648C8EFF00158AC687DAC2971672B2E2E5D917F4E1C8F3A333676E712E43A00A9212FA0FA1FC3550DF41FA6B96BAE3C37BEFDBCAE627E94496D59DC9D8DFAB263E1AA7D4CD237FD1AE8E6F3DEF7872E0EE59184ACEED03682CC413706C4741DD418898F331F7E423D43DE3F674A0BCB188DBA2B13F89D8FEC141B8D831A6DC973B6F8A267DC648D0E0CAA5563422453219435EEBE1DC0B77D4B70B265B4C9C4E5B8DC5B0F92AC3918A366CC3B7FCFC6CC8BAEC5D1A32A7A7694623F87BEACBC1585CEFCC8E53CBF6EDAF1B7ACA5C94DBC49F2EEB1F86EE64D3A9A620E9675D3A7A28E9D7BE888629653EDA0B8BD549B771B814139E35B7F116F2EF799B75CB6C6E43F3310DA7180763246ABA9B5228B283A88D67B0814121F2EFCD4DE38E6EAF36F0EFB9E064A44934590DE36948CDD08D7724AEAE9DF5656ACB1DFD399708E69B7AE3A4D1CAD25B4C7215D449EE01EC1BD9DB5AA92E1CF379C3E2987919B950ACD043B60123C464788E459F4C8B1042C5748EFB5AB3392E112DAB71D9B3363DDF70E16B3CFBD604EDAA1DD2416830A43A61CB8614E9348AC74B072429B1D356444831F9A60F975C3F6DC28B1C6E3CC3271E67972A4955FE584EECED2314D5F9923B17B76FE2357381C933B7DDCB72CB6CADC3266C8C87377964919D8FFB553B0F487909B0ECB0ECBFDEA3C9872B3F26F1B04B6A8554D8A904060C7BEB59136E45CF369DAB206DB8F7CDDE241EE6143EF32DFB0C847C37F5D51C5FCC5E59BA714E598B9D97B76365EEF9D8DE24AD3C0B2C48BA8848E3F106962A2FA8F6DCD676AB18B85FA8AF33822626CFB443A6FEE438F8648EBDB64885AB0BC3B4F3BE5A73F6187020050CF1452EE4FD9A0B58F823AF57D5DC3BFE9ADC8CBCEFCF7681BB67E0E3AC63C485DDE6EBFF00097ABA8F4924855ACED7C3535CE532DFB89C9BEF9A1C03CAE2BFFC771BC41BD6FDD855E695BC6954817EF0A82FDCD51977D8EF3E766669EAB2C9E141EA8A1F717E82FAD87F3505FD340D3416F27DDC790FF09FD941E39F359AFCA3289F49A0E6B7EDA0CEC5B362303F887EEA0F4F7E9D1B38F1FD51786F0F87E1C91B92ADAA39E50AC0856EEED16EFA0ECC4B0FB84FB2DFE9A0D7EF90F1D9F1063EF898CF8B3368099813C32D6B81793A5E861C8B3B60E1183BB6E01F8DDB6B7257033B19E62C6522E1A141FE12C7F883283EBE958ECDF533B826ED958F14FB1EF595950B2EA18F99FE602AB779240907D26B6C22BBCF0CE67A164CEC148B0F1948932BC410A69B8BD84A41245AF65A0E76BE6141B6664A36E85A784868E4F14F868E01BA3D86A6BA917A0EEDB34CF88F8E8331CED5991C53F89214F0C315B864B33131B6B201AC6BB65BDB5927960F26D8B3A6CBC779F77F95C0C621DD9640540622F20894EA2DD3F09FF4CBADCACDA61A9E47260EEF891E6628FCE77F01AF7D6C1012A585ECABA7A9F5D6D8E30CAE198CD1721C09A588A4583893CD368B6A5D44441BBBB594FEDAA8C93BA21E47B46CDC6032459F951CDBF4AF03FCCC33BBFBD19C96B2E975BF4400587AE83BC69A0F8514F6807DA282968DB49D0D66B7BBABDE17F5F5BFDB41CE7CCB02F234F1EA9D715F5786EB6D11AB48B215275A8D5AAE0F7761341E59D98DF6EC65FC311B8F5B48D41F725D52306E75162142AEA63D3BA83D49FA7B8E41E5BE3CAE2CD3644CDA3F085212C4F79F73AD074AD340D340D340D340D340D340D340D340D340D340D340D340D34172D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D41E54E758193B6F2CDED914B63E46764CBE193D519E4662CBFC2D7BD4769E109DC1F5E4339045D54DBECAD472DBCA9DAE50BBC60381D172611F5C807EFA4474FF0025737765CFDBD30A389BC0CDC98B2239095044916A31A91F0B592E09F456B71E8768A0CDC655DC70875F8A09556600FB46A15818E9B262ADCEDF973E191F761975C63D5E14BE246BF428AD4DA8F8B3729C462127C6DC238C5E43207C571DF6D4BE32136F52D59B0A87395C5556DDB0B27011AD699D3C587AF67E6C25C2FF0055AAE251BADBF90ED59E9AF17263997BCC6E1ADF554EA31F7DE2BC4B924060DEB6BC4DCA33D2D93123B0F6311A87D06A60727E51FA44F2C375D726D4D97B14EC4902093C686FFF007736A36F630A6071FE51FA3BF3136D669762CCC4DEE15EA8A18E2E47ABDD92E97F63D4C08C43CB7F507E594AB0E4646EDB663C47DD83391B2310FA97C512456FE53504D38E7EB439B62B84E43B4616EB8C4D9DB1F5E2CBA7BFBE543FECD0489E3C5E39E6C8DCB77DA5A6C3DD70BE633E1DAE174870A4CA6F1218FDC61E3148C857B00589B85EC148B6A7D1F9C5E5EEC30C5B7C39BF3D13213B7E3E0C0ED9002D87832C5A5023F5F74B69B8F8AC45D88CA5E59E676F83FF0080E311ECF8CDF0E76F927BF63DFF002B01BFFF0096831F33CB0E67BE053C8F9A65BC26FE3606DE8312020FDDBC451D97F9F55163C8FBE411E3EF3362ADB4C2114817035D975743DFE9A22D3384E808B0EEA0A1E78B51B9BFB283A4F9239FE5F60EE5BB64732648B1CE3A8C3695252C1C35DFC36881756B74E847A2A2C95D5B7DF357CBEDEB8C7FCB1B46C5B96E5B7E7AB61AE3E162E8F074F5468C374670D67007B4D55BAD40B7BF22F6D3B6E447B54F9EDB8A8D512E4E2AC1133A8E81B53865BF655C32E2395B7E5E3CF2E2E444D0E540C639A07166561DA2C6A0B715E38DDFEF0ECE97EBD9D7EBA0F54FE9BB866C199C09774DD36CC5CECE9731A48723221491D16344D015981D366B9E941C8BCE7D936BD97CC7DDB6FDBA010E1466268F1C3315433C2923E9B9361A9C903B0760A0D76CF0EF5851439FB5B491104159909044B1FA48EFEFAB2AE12B9F94E5E432726CA64C768898B3F6FF0078873A4ABC7E19EBE14DAAFD3A03EB1572356DC9B896299E6E36B3ED6B9814CF952C27C65B02B342B246C0F84F7171EAAB9468B75CC933B325CB604C539BC7332786AE14050556E6CBD2DD6B37CAACEDDB7E566658C78C80F21F76FA45AC2E49D56157091D7785EE9B8F18DA26C3861CD12663BE4E565C1F24C031000D1ADDCE90ABEAAB20A387737DB36A932B35FF00B846D37F993959B8D04B24CB2BE80C8DA9198B390A145CF653391DB30D20C1DAE3DFF967851E4C896C6C5C908BE102350521FDD590F7FA3BFD4F2217BAF28CBDE60CE871B715C1692264487165392A8CCA550E88C451FAFDE26A8D06DDB5626CFB7E9F99745721D9F3262EAB295D2D26A73DBE81524C0CAC7DA361E3824F3279821C2D9F6D882ED5852DD7233B215BC489BC26EBEF3A82A08EE04FBA2E65E46EFCA3D8F7D806EDCFF91C5F2FCCF9D497C3C320EAC3C0403C30437501534B1BFA107C46B23A8C50A45124518B246A1547A00161415DA816A0B5931EAC7917D2A7F65078EFCDEC67879364DC7C572283980EBEC3419B820B63CEBE83FB47FAA83D07E43F24C3D87699E4DCE554C706595D5583BA4122A3AC8C89ADEC5D5940B5EE7B28367CB7CC4F34B7F32C3C4B6A9768D981B0DDF302E2B3AFE2F1320AAA0FE504FB2820BB3ED5C5E3DCCEE1CF7937FCC7B8C43FCBED5B678DB910E3B3C69583060A7EE03A7D371D2824B93C88A631C9DB38CE7344ECA8375DE7364C75D5A8B2AAC58DF0827B1001D3A0E9530B9AD8C793E686E39B91B5E46F51EC2F8C8A7230F060103846171F9D2F8B23743DB6AA995D9BC9D136DA377DCF226DD1DD0B093324932590F5D2EC8E4AE9FE5141CB7378D6DD83B864B3E146B982416F7410ADEA06EB63DA2C2D41BAC2E6DBAE3C48B3E2C32C706910B470C7130646074BD96C5481D7A54B95D6CF949374F3176BDEB15845B69C6CF6661163A697C7FCC6B91F74F4EA40B5546BB6E8721A439933C9018ECB12A0F798BAF444D247537B5BD741D0369E3D2626D8C7232E1C2DCB710AD24EE468C68A35FCB8AC482748E87F889A186CF84E2CE99867C3D1F251C4AF978D0CB1CFE2642B32F42BAEC143028BA85BA7D1255B1D0A29A296312464953D86C41B8E841045C107B45545600A0A656F0C03A19EE6DEEF5B7ACD0713F35B788E1C3E439E432492E3498F1C6C2CCA405C500917ED6949A0F3D6D1A60C7712137620016FE1BFED6A0AB2E4902AAA23BB35CE94E83E963D941EBBF2636D930BCB6D996521A59E26C87B7403C572C00F62D8504DED40B502D40B502D40B502D40B502D40B502D40B502D415E9A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A069A0F3679CF8D9985CDB3D9EC70728452C4C07C0C6301837A8917068EB3C397EE3A5E44607A1523EA3FEBAAE76305D9A2F7E23665B329FE253A87DA2A23A1F97BBEC3B372BDD081AA38E48377C54274978DBE3553D9D629C56F61EA3C4CE3930ACBF2D34418060B20506C45FB98D607C9DA194E8F0DB50E8D2686BA7F2903E2EBDD418C5B15C98526298D19FCC1203A59AF72A4B598F5F8FAFABD341ABE6BB52F21E319DB6A64289593C4826C6942B89A2F7E3E87BB52F5F7BB2A558F337199B9EC3C9323027CAC82D8EA1A56C98E495A025BA862A0CCA3D1D7BEAF66AE1218FF0050DBFEC5BBBEDF950CB2458FA16425BC46B9176F7260AFDFFF00695B9BB0E8BC77F527C6B3742CF3471BB7DC76F01EFF00CB3698CFF4C86B5994744DAFCC7E359E102E5246EFF0A4BF944FF2EAB06FE9A60483E670B2A231B8596371D51806520FA8DC54EA229B9F949E5466E4FCFE6718DB9E78C87F1042A972BD46A09A437D353023DCC78AC9E61EDDAF64DF5B8F6EFB66E1E3E2E44215C34B8E34A099548365205BD16ECA5838B798DE57FEA1FE71F74DD71B1F9500AAAF93B788D99822E90E71F4C4DAEC3A954AC58B2B238379B5B66CFB7E36D7BF724DFF008B6FF092B949B9E2FF0070C06EB6402397564C6156D7D256889AE672DE75C920923E33CC38E6EB1CA8635C7C0C85DBF2CFF118B2C4EE0FA6CE283906FF00E4BF9A9F3F2654FC633249242A4BE218B251AC0024344CDDB6A0D5647955E60C4AC5F88EEFD48D24E2CAC3B7D41A8B982F969E62C9748F896E835777C9CDDDEB2A2865D57C9CE2795B1A664FCAB816E79DB8F8D1BE049F209208D514DC833346012CDF60A176AED1B6720CC31F858DC3B77C48C12551A1C3856E4F536F98EFA08F6F1C7770CCCF7C9DE79EE171F9A4259B6A8E3C55922424F86924B2CA599C2583102D7ECE957288CEE7E52F93B959A373DEF99E0EE194AA4319F331E0121BDEF23452AB1B541811F94DFA79CA859B33976DE9230B118DB8C4156DE83248D7FA56836BB3C9E49711DBBFB56DDE6964E2E187320820CCC598063D0D8AE3C845EDDD4187999FFA5CC9CD7DC37AE58DBD66B8556C8CC79667D2BD8A3C3816C3E8A0ABFE7AFD25E147A22CA8DD41D568F1B2CFBC3A5FFC31D68B96B737CCEFD2AB48B220CA32C60846871A702C7B7DC7011BFA9689968B7593CADF305E383874A766C7DBED26FBC8F77231E05561A31A1B754D4EC1B4F45F86822FC8F8EF07D83299F179AC1BC67243AA2831211918F25C1B44D2C6F65BB76DFB2A8D4F1F938B64C30A6EFBBB6D191248565D58A92C2AB7003993C443DF73EED209F61F12F28E52B02798B8B9596D7D30E261BCAF21EE544524B31EE1DF57B0E97E5B795B06C291F2BE60ECF918777D9703274FF944FBB34CAA593E608EC50488FBAEDD68355E68731F2CB3B72872376E5C72638C5A1DB707E588848ED2FE348A751F65322191F993E5261868B6B5DF7779D8FBB0E2F849A8F601AA285BAFD353225FC77379065AE3E570DF2BF261DD1D6E77DE5B92C2381CF7A2CA5A5703F802FB29C8976CBE534B91BDC3C9FCC3DD0F2CE458E7560E268F0B6CC237BFE463F6330FC6FEDB5FAD5C09FB461E76C8701A761A75FA16F7D23D552D1569A81A681A683E15B8B507973CF8D99E0E42485BEABE9F5D070E5C47F9D5C566119690461DCD9406360CC7D02FD6824BCBF8AE4711E56FB1C7991E7BE885DA78D2C0871A88D04B5ADEDEA3AD4956CC27FB4F339629B0F6ADBF75C6D922988843ED78291C84A8B9D7912788E5BBFA0AA8EBBB5791BC6F7011EE3BE6E199BF9955648A4CC91DB5061704A485C0F60028273B5708E29B522A60ED704417E13A0311ECD57A0CBDEF63C2DE369C8DB3297F2274D2081D51875565F5A9EB41CA1765C9C5E619C876FCE511A2C514AD21CA7C89F4596569653FE19E9717F55BBA8B84CF69DFE4FED230639626C8853C27087C5646B5AC187B87F75494C20DBBF0CC69E6C4C5935C93CACE23CA62ABA16E19BA80DAC58FC24DA86385BDE3C91DDF123926C6C8C7CA8940F88B42F6EC1EEB074FA6F551AFD97CB0CE189F3061C3C740D689A691DDDEC48BA225830EB7EDECB1ECED0DE1D9303628973269CE5EE7132344ECA15628D5C349E1C4BD10151D4F6D161FDD30773DE1B6BDB628F7CDD4CB0BE1E62C65317C0E8D30986BD565EA3B08352AE5D476BD871B6CC41061247003234EE112C3C57F8AD622CBF740EE14465224BAFC78D0066E93C77ED61D0F75B50F4F78FA2D51763983A06D0CA0FA45C83E83A6F418D9BBB606286596748A6E8B1ACB74059FA28B91D84F69EEA18798FCD8CF7FEDD8F84F26B9F3F209949373E16212F2B9B77364487FD8A0E79180B8EB1B2FBEDEF91E82E6F6F55A828870F333775C6C2C68BDF9648E38E466BADD980B88FBED7EFA0F71ECDB645B66D185B745D531218E1527B4E850B73EB36BD066E9A069A069A069A069A069A069A069A069A069A069A069A069A0AED40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D41C57F503812413E06E5A0B6364A1C69241D91CB19D49ABD4E1C8FA28E9A3846E29EE5ED628D722DDC7A1A31635B22F4A237DB1E5E363CBB2EF32AEA871246DA7751E882407C273FF0086F61FF775B9E07A8F817296DEF69C7C5C60649701463EE196C0AA0280AA18EE3DF670A1BD03BFD15CC4ADC15031E1F75C8B96EDD2BDEC6FDE7BAFDA7E9AA2EC712C681105957A0EFA0B59B123E348ACA1B52951700F56E83F6D072BF36B0176473C8B64071374C9960C1CA9230A11E111C92755B7C5A82F5F40AE7BE1A92DE1E70E69C779549C833377DDF0B230C6E333C89264C2F1092D6174240BF4B1AD6BE0BAA32D8A6360A71247637D25BD5EA5AD275ACFDB372E4F84447B74B361A3DCF841AD11B0EBAA23EEB7F50357275AEA3C2799798488B062E1656E52AC65E6F905911E3B9F758AA2CD0E9F5F842AF76BABAC61CFE6E6E18C7164C19B18BC7E206CA38D1965B7C3E246EF66BFA6116ABDD9C462ED9E55F3BCD6C5DC67E458FB6A989244C7C3C6D405C5F43DCC65CF5F7A42C589ACE6ADC3A3E06C9BE60E244916F72CD908A048F911AC91B30ED2141575BFFDE1ABD9963EEBF339E0E0722E3B89BD63B2DD4AF852861D87F2F242588F4063EAABC0E6BBDF94DFA6ECFCE7C7CEC06E3FB8902F0ACD361952DD8551C98BAF7585A9D45AC7FD36F1248FFF00F5BE7BBDEDD19EAA91652320FF00CBF0BF6D4E9464C7E4172C8A456C7F3777C40BD3AB484DBFFF00229D68BC7C87E4EFFE379B9BFB0F548E3FFE79A7516DFF004F795215F99F34B90C814587F98616FF00F21A75A3E1FD3D6DD6B4DE637249077FF9D007DB7ABD47DE49FA68F2D791EED26EDB8EF7B91CB963863959668486F022584312D1B1BB04B9EBDB53A518307E943C9B8CFE6E76E9301E99E2507FD98AAF41B2C7FD37F90588A7C6C4C8C8B0B969B2E61FFA65053A8D4637957FA5ACD133E1E3CB2AC13363CBA72B2940953E241E232DC8F554EA3362F2D3F4C78166936D8AE7A81959731B8F506969D45CFF00977F4B91B59767DB5DC9B2A99CB5C9F5194D3A8CCC7D9FF4EAACA91F19DAC863A4B346B258FB4934C45C2EEF3BF792383B64DB549B26147B46498DF260871E28E198C67F2FC43EE6AD0DD973DB56599C1D6F9681770FD39036878BED07B2C645840FA6E5A9D5120C9E1DC6463C3261F04D8F1D3240F01FE561C891830BA948C282DD3AD381B2E05E50ECFB0EFF003727DC6081B789014DBB121821862C388F6E94855575B779EBA477F69A9813ACE853363683336E8732026FE1E404913A767B8C1855CC185171FDA22378360DB21FE58211FB23A9C0D963472C02D1450638EE11201FB02D3305EBCA7E3727D9D29D834D642D40B502D40B502D41C3BF525B7BC58D83B9C6BD86CE6DDEBFEAA0F3DF2EDA524C78B76C55F70802703BAFD8DFBA834DB6E2060324B1528DEE0EE3D0DE837DB74472F172218BA6E18A467611EF66C7179907B62F7FFA283D65E4FF0035C7E45C6716321FE6A08C073A495B5C8B6AECF748B7B2D4C89ED9BB85BDB4144A5238DA495AC8BD4FFA2C3B68349BDF1DC2DE61820CC80094967492E4347EE58DB495F77E153D68B973FDE23E45C737C9D26C04C3E2997E1C3E362A365478D120019D2C1591D8F5375FAED44CAA5DC19718CB9D2B410E839DB564CC9A8C90EAD30B1084B2F88DA2F7ECBD15BA9F90CD043045BBCA9898F22C4FD58C8A124B5988527DCF7AD529186BB8CFB9C904787918F92B973CB8D8A226547FC905B5B46E47B8556E3AFB3D6459C2DAF377BC4DCE2C6DB5711E70AB165B132C69EE8BB4325AEEAEBD4F4BFB4D5589D71BD8369C08E4CFC5C78A3CCDC02C9972C482305B4805557EE2823E1F4F6F5A23776A0A1974BEB1D87E3FDC7FFAFDD41449132319621773F1A5EDAADFF5BD74A219CF37B6F910F8DEFE3C44AA037024CD24A4719F54366924F60A0F34F21CE1B9E665EE521F121727036E1DDE0407564CC07F1BB681FCCDE8A08F975D4D212005EA4B1B0BF775A09EF907C4A5DF39DC19B9023185B629CB9045761248A4045673E8721ADEAA0F595A816A05A816A05A816A05A816A05A816A05A816A05A816A0AED40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4118F323664DD3886746535BE3A7CC20B5CFE58BB74EFF0072F46B5BCBCA3B861E3AB1F0C831B820153EED8F78AA6D1A16520146F897DD3F455F2CB378EE4632E64DB76732A606EB1FCACD23FC31484DE098FA0249D18FE166A6B475BF2A7CC7CFD80E4ECFB862BC92E137CB6E18F70244957DCC796E7EEBF48DFD763EDBB41E82C083223C75F9A759329FDE9DD459751EE5FE15EC1581936A0C4CAC98CE88A3BCB21914593ADB41D4413F08E8BDE68312586299F21B3446B12CA851195646122A02A575023575E96527D145539BB447B8C2AB3632345138963190A2591997B3E3D423B8E9E9EBDD44CA3CFE57F978D94FE36C78ED06E2C1E396CCAC92803546082190385D561DFAAFDA28D76ACAC8E01C16049A58760C25D20471848914BCA48550180F77DEB0BFA7B7B2866B718FB4B60E2C38FE10CB8A15B2C8B68B2013D58820A83726FD0AFD3447D49243988B8B9025658DCBE3E4021D402B606C15D6FE960682ADBB2DA18E4832A1689A195D35ADE48EC4EB5F7945C008C3E20288D8C52452A092275910F63290C0FD22834FCCCE1A71BCD9B2A5307829E2634CA4875C81FE068B75D464B003BFB283C97CB775DFB78CFCCDE370C81909B7642A4F3A58472EE2EB64892DD0AC4B1F77601EBAD788225F39938D6F0B2A489AFA9996464249EFE8476D4C8D96373AE610C417137BCF09D802CF21007B58D322F8F31B9FA2B6ADF335BA1B0391D7ECBD3346CB1BCF0F31F13664C0C7C8499C97BE5CEAB2648D46FD5D8775FA53346966F3379F4A922C998DAA5BDDDF226D409FBCA1645507E8A668C28F90F2F99F443B9E7CEE4F7644CC4FD0A58D3346EB078C79C3BA01F2B89BCC8ADD43DB282F5FE39085A5A255E5DC7E72EDBCBA5E29ADBE760C73992EDDB9CA1B5C3D3FC27BB7BCDAFB355BD34ED5647504E35899EE31373D8372C7CB7D44E562C4C601AAC4F88AE9E03F5FC4B7F5D675C7F26B6FE6BC7CA692095A4C4CD861792DAE4C8DA216636E82E230D174FE515AC30BE9E5F6FD1B027926D11A778FEC78AAC7E92A3F65302D6FBE536DBC8B6E8B077BE431BE343289FF00C9E245885996FF001B40884AF5BDAF6BF5A755CAE3792BC3B331462E46ED91938BEE930A46741D26EBF0FA0D667AE4B95BBDBC56D367F26BCB3DBE40FF00DBE5CD71F089E26D23E8D2BF69AD329DE2E2E36346130B15314050A1AC2E140B0000F47B69C0BC9105B924B31ED63DA6968AED502D40B502D40B502D40B502D40B502D410BF3738F0DE78566461354B8E3C64F4D8746FB283CA98992B8B833634E825652638E16FBEC7B16C7BBD3EAA08B0D43204605922BAAA2F405BEF37B05066E1E4E461E6C39B8AE127C77596193B4065375B8EF07BC77D0757F2FB940E35BDE367E0291B0EEC4BC10DFA432DC0C9C463E9427DDF4AE96EFA0F4B6D7BA41BA61C7978AAE21901F7A41A6C41B116BF5FA3A7AE8324C41A400FBC16CCC4FA7B40FDFF5507C55D592CD6E91A8407D6DEF37D9A682E9504588E868349BAF12E3D9DA84B811F8B2A889E441A0F865C3104A15EF171EBA1960C7C236099772C2952438D94D09921D64031440784A083A828915BB08F5D4916DCB376DE0BC4F6D60F89B642245EAB23832303EA2E58D546E9A2EC64003AF417E82DE836FFE85058C48AC8FA4786E2492E08B76B13D47B0F6D05F56B9D2C34BFA3B8FB0D0576A0D76E7B91C4827589449971A078E2BFDD63A43377D81EDF65079E3CC9DE33B273A1D831325A39962927CF99AE13036F90EB9B2A651D936493F971F6D88EF6141CDF77C989A50B14661C74458B13189BB45025FC346B74D46E5DCF7B31A0D7C90CA34C68ED1BB1BB308F5A9BF41D4D80B507AAFC8DE0E38D70D8B232753EE9BB05C9CB91FE20847E5463B2CAAA6F6F49341D16D40B502D40B502D40B502D40B502D40B502D40B502D40B50576A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0F85410410083DA283CC3E69F078361E413C58CBE162E4933E3469D17C363D4006FF0B74E9563A66D8E65B8E308D83ADF491A5EFE9EE356C736B644BDC1171D847AAA09AEC99793BAE3A6EB80827E51B26398F70C263D374DA9574B1F4B4B0A595FBEC15FB9AB528EE3E5BF99F91BDC78BB3E108F217C2071775CD94AB30172609220BA9A7897A37BC355B55636CFC2E1D4BE5B50BCEFE27A57E14FF67BFE9BD1161A532CD1478AAA5543378A6FA05869F76DF17C5DDD3D7416F6EC445CCCE95FF3271305F1980D5A7C18CD8580B0EBDD41B1B50606544859B0D87BB95EFC47F0BA9D4CC3D054D9D7F8A8B1630E43939C3164B0936CB1980E81A491488DC7A8A6A36F49F48A0DB5A88C3CCC78A4C8C62E3AEA655604AB0BA93D1858FDDA0B117CD62E7CE963931C8A92DFDD592E3DC6B0B2AB582ADFB3E9A04B1ED19D1CEC937832005669E17304C9D08F788D2C2DFC553C8E0BCE3976F9BEEF2FC576BCD79D90B49066651444C4C3453E2E7E4B22A01A50B787ABADBDEED22B5ACC791CAF916E3B7E4363EDBB3065D836A568F0358B493C8E419B3251F8E761D07DD40ABE9A5A23936DE93386763D3A002A0CB68B15016F961E18ED51238B0AB91DAFC98F25B8DF23D966DEF906148D8D24863C0856799032C770F212AC091ABDD1EC3515D570BC90F2AF10831F1EC7908EF9DA59FF00F55DE865BCC3E07C27088389B06DD0B0EC64C5841FAF4D11B8870F160168218E21E8450A3EC14176D41AEFF97363FEFDFF00307C945FDE7C0F94F9EB7E6781AB568BFA2F41B1B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4144D0C7344F148A1A3914ABA9EF0C2C450736E1FE4AEC9B66EDB86EBBA4499934E66870A1701963824BAB391D9ADD7A74EC5F59341E74F343CBD9B887247DB55A46C79353E33B5ACF01375606DDA3B1BD62822B110C02016205F481D02F703EBA0DDF1ADE31B11A6C1DC03BECD9C47CD08C5E58254B88F2A11F8E3BD997EFA5D7D160ECDE5DF98795C6F711B0EEB289E39A3136DF901AF066C2DF0C98F2766AFF5822E3A0773DA3320CCDBA1C98A4122C8A19D81EC622EC0FA2DE8A0C8C6B344240411212E0FA43755FF0076D415B1D23D24F403D268292B60149F7E53A6E3A1B91DA3D83AD05B9504534328E8A3F25BD166B69FF78003DB41916A05BA1A0C70B6C99428F7B4A393DC41BADBFDDA0BB649148B5C7611DE08A0D765EEA9B7E5C70E4C9198240CE6463A5A18D16E5E4ED1A2FD3574EA40EB515C9398735CD3B913B18F9BDFA617DBCBFE546B1A827E67201FF0B1A2177456377F898DAAA39167E4450E34B8F1653E72CF37CCEE5BAC97F1371CBB93E37BD66104649F094F6FC67EE8011E9E58F589A565566BF841CE90C4504F7C90F2DF1B95F24FEE59B199B66DB1FC49D4BB490C93DC18E2EB653F89875B0EDED141EAFD341F6D40B502D40B502D40B502D40B502D40B502D40B502D40B50556A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A8211E6CF06C7E4FC74C8B193B96DBAA6C3912E24B5BF31148FC4076779146B5B8797370DB3C36BBC86453D9D9620FB2B783668E680A31427AFDC6F48AC32A70B2F3F6ECD833F0667C6CDC5712E3E4466CC8EBDE3F78EC23A5251D1F64DCD77791F7CE2D8889BE8024E4BC463631A662C6757CF6DA7B52443EF597DE43E95ADF91D5B81F98D89C8729A4DCF72924DBF1551535A8C730CC49531EE28BD8FE86FF0CFA8D8562EBCABA660E74597B8E74710BA61F8709901054BBA78A40B7A15D6A4A58BB86BF9F9A7D338FB218C55464B95452EC42AA82598F4000EFA0C4917FCBC99727B8EA3C48EFDA8A9D40B7AFEF7B6D418F0E3C831A2CF8D2F986F264463B5839BBC5D6DD52D65BFE1B7A683651BA4B1AC9190C8E032B0EF07A8A0C6CE9523C8C0D6C17C4C8282E6D726192C3ECA2B53CB37C83619B6EDCB20FF0096D7263CE8835485644D4BE1ADC163E246A3A7A6A0E25CE79664F3ADF120D831628B276DBC9939B3E95C7C0805EF2E7E47C25876AC4A6C3D66B72239DEFF00BE6DF1E24FB0F1C9659F6F9DC4BBD6F9382327759D4DF5303D63C656FF000E3EFED6EE152D11737EB7F87BAD5052C85D6C3A7A2D4129E03C2771E65BA43B5E35D2373FE6F22D71144A4091FE81D9E93614591ECADAF6BC2DAF6EC6DBB06210E1E246B0C118EE44161D7BFDB4464DA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A821BE68797983CCB63F09E28DB72C4D4F80EE3A12C2CD1B1F43FD86C683C73BAED591B4664F8B911342D04EF1BC4C1836A8D880A491DD41663BFC400D761AC0ECB9A0DE6CDBEE3C38876BDD617CDD95DCC8B1C642E4624CDDB3E239F85BF1464E97F537BD41D2763E59C8B8FEDE7331B34EE9B0CEA615DE71C1651A85BC3CC84FBD0CCA3BD803ED1D683B170FF3478EEF58FE0BB1C4CA8A23210CBF9724718F79E3652C2C07522FD3D76BD04C712469E259D97487EB1ADEFEE1F849B77B0EB4081E39DCCD1B6A442D1A9F5AB59FFDE5B7D1415C88B342CA0FBAE3A32F75FB08A0F98F209620FD35755700DC0753661F4114159650E2327DE60481EA1DBFB683072725A1DDB1A3D378E586632B0EA57414D2481DDEF1145606E5BCE3E165FCD33B2471A31C8C6BA867E9EE3386E90AAFE272B44729E43C91B3F1A69B1B2238F6C4903666F79776C657FBA221A7565CE3B23555D23EEA9A0E6BBD72185F1E4C4C55923DBA662F932E41073339EF7D794C09D297EAB0827D2E58F6044B2B2DA672F21B275D209D3A8FE104D06D784710DEB99EFD1EDB838D208C59B32690218208FF00116EA7D8075341EC3E35C736CE3BB262ECFB6C423C5C540AB600166FBCED6ED663D4D06CED40B502D40B502D40B502D40B502D40B502D40B502D40B502D415DA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A838379CDE55438B34DC8B69D516264BEACFC64B18E395CFF8814FC21D8F5B7DEF6D58DEB72E219582232C8F707B751EE357AB35AC950DFAD89EE23B0D6516E19B2717262CAC595F1B2E071263CF1314911D7B19596C411413CDB79BECFBDE54791BFCEFC779520D10F2FDBE3FCB9C1E9A771C5416607EF3A8B1EF5AD4A273C7396724E01B7C926560473EDB98448BC836FD59FB6E415E819846C2484E9E9D0903F0F4A7513BE05E65ED5991E76564689573B23E65B276F639912131A47A5D1078F1D8463E38C567AE16D49B079AEC3BE6FB26D5B666433C582036E126AD3F987FC3852FA751FBCD6E9DDDF446D73F3436EF83B522EB3307C99DAF6D31C0574FD2D232F4F45E82DCFBC616DBBE47B7E64D1E3A6E2864C269182EB990DA58C5FBC82AC077FBD41A8DC79D6C7B36FC76E9275C88654695E3C6BCD2412822E1D101B2BDC9F6F75A9C8E6FE61F9D5165CD87B7F1D62372C7C959E3C685066E5C8CA0AAA88612E918BB75F11AFEAA6043791656F2E5B339FEEF2EC892ADC6CB8F22E4EFB928DD74311A63C289BD0028F6D5820FBEF2F933F023D976CC48F65E310B6B8768C725BC471D92E54A6CD3CBEB6E83B852D1A48DDC2BB5BA7A6F6B5FA541F52332B5AE001D0D067F1DD9F2370DC0E06344F93905C243120BB396EC028B23D6DE547975070AE3498B2699376CAFCEDC675EA35B1244487F0477B7AFB6854DAD442D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B5072FF393CA54E55B6CD9FB422A6F7180ED17455C9096B027B9ECB60DF41EEB0795376DB3376ADCA4C5CC84C6F096729EF5F503A48218290548B58D059570EC4124325831EE2C4760A0D9EC7C8775D9735B276EC96C49DD744A2CAF14C9F82689C32489EA6068265B56F5C5B727BCE9271ADC6427C49B0D5B2B6B98B0B1F1314969A0D5DFA0B0F65074CE2DB9F23DB225381026EDB6259649B66C9391181FC508759626FE6D468263B77991B2C38F1C4F29C2541A4439B1BC6C2DFC72886FEDB50666D7CE701208E132C12205169965402E49F76C8D3767A7A7B2829C4E57B5626466C8B991AC795289961D09A51B48573A9A4889D456F418DFFF00D03026C892487C5C8C8405205811A504122E08804DA7B2FD5BEAA0D46EDC877EC78FE6329E2D8F1A4F8B23709570F5ADBB3486972E6FE5571ECA080EF7CB36A653E08977B2A7523E62B616D68DF893114ACF39F5C9A7DA68205BE727CCDC32924C99DB332231A31D42848A153F72085008E25FE5173DE4D047F2670245F9860247BE8526CA0FF11EEF6506EF84F04E43CD377183B59F0E016399912012430A0FBDD2DDBF757BE83D67C1B82ECFC3B655DB76E05D9ACF9594E007964B5B510A0003D0A3B3DB734122B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4155A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A82DE4E2E3E563CB8D9312CD8F3298E689C0656461665607A1045079CBCCEF2666D8A49772DA4CB3EC6D76642DADB1BF85AE0B14F435FD47D26C74ED98E49950E3AC760415ED1A483D7D56AD718736BDE162A1883A4F61AC0C731FF00AA8367C7B94724E393B4DB1EE3360193FC6890EA8641E89217D51BFF0052D5C893C5E62F1CCF944BC9F87E1E4E5816FEE9B3C8FB6657F35A3BC64FB2D5723790721F29F258C90F21E45B3CF200245DC71B1F748CE9ECBB32CCE6DEDA0C6DCA4E36FF009D83CFB6B7643F9667DA771C597E9F957D3F52D172C27DC3602F14F99CEA26C982FA1F0765C89C8BFE139647BDEB34C432D6E66F1C10BEBCB97907276FFB1CB9A1DAF109FE28F1FC7723D5714CA2D49E64EF78D8AD85C731B0F8B60302AD16D31E89D87A1F2E42F39FA1854C88917795DDC92D239D4F23125893DA598F526A0AC2FA7A9A0C8C478F538FBEB6EA7D07D14197B3EDF979DBC8C1C281F272320A8871E252CCCCC6D6007D741EABF293CA3C3E1F8CDB8E75A7E41980199FA32C008B7871FAEDD19BEAE9DA5CBA3DA885A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0817993E4FF1FE68AB96C3E5379855963CB4B812023E0982D8B0E82C7B47D941E57E5FE5E726E25B9CD879D832A223F898D3A8D714A96ED493B0E93DA3B7D341198F2AD8E3C405AC85883DA4DFD2683360C911B7BB269616B0ECEA7A817A0D9E26F33C13A4F664C85F87222668E516F448855BEDA097E0F9ADC9E28FC3FEF3912C6058459AB0E6AFD7908EFF00EF506C97CD1DC9D7DF5DAA66BDEF26DE07FE9CA83ECA0BF1F9ABB8C67C457DAB1A402CAD0EDC85BE8F11DC506BB73F35B91E4A321E4398B11ED8B1162C407FF21636FB6822CFBE9695A68A069325FE2CA998BC87DAED76FACD06BF233A694EA9E7D448244686E4DBD1DD418A32C48B6C5055DED675EAE08ED0C3D07D541D1381F933B96F5226672465DB36D92CCF8EAD79E406C7E022C97F49EBEAA0F4871EC5E3BB16DD1EDDB362C587871F6471002E7BD98F6B31F49A0DC26744DD8683212456ECA0AED40B502D40B502D40B502D40B502D40B502D40B502D40B502D415DA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0A58802830F23356307AD06AB2F7C895591ECCA410CA7A820F71A0E1DCFBCB7D8A5925DC38FE3C704CC754B84A02A927B4C44D80FE53FEAAB2B59726CF8A7859A168248E55E8CB2A14B7D75ABB258D778602FBCDEF0ED3594590AADD50F4F5F4A82928C0F5A0F940ECA016BFA282920B0B5004761D4DA83EABA5BDDEB6EFA0A6262D23826E0116F5504B780F96DCA798EE122ED38DA70D184736E135D60423E2F7ADEF11E85B9A2E1EA6F2DFCA7E3DC2314C902FCDEF33ADB2F739146B23F0463AF871FA876F793444DED40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B507CB5060EEF81B7EE186F899F8F1E56349F1C52A8653EBB1EF1E9A0E09CDFF004EDB06409E6E3D92D84EE4BA624ECCF0AB1EBEE30F7945FAD8EAA0E37BFF00969CD76542F3EDEF308C8667C6BE42B69EF5F0C123FA80A08E1CA971A442FA9515CF89D08B6AEE6F458D057FDD992444765F7810750172C3B282B4DE14385223D4EB75BDC5D8768EDA0A9F7887C4D0123B95D51DEFD4F78EDEDA0FA77C6490A21452D631D940B8EF527BA82D8C9CC9A5D369245D5757F8829FC26DDA2833F036799EC24934444EA115AF63E9B822D4138E378F81B6B2BE363AF8FDA676019C9F4DCF67D1413CDBF7BCD6B5DCD04976FDCF25AD7634126DBB36636B934125C199980BD06D93A8A0AED40B502D40B502D40B502D40B502D40B502D40B502D40B50556A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A82CCE0E9E941A1DC524EB6A08BEE51CDD7B6822DB8C591D7B6821FBDEDA7250ACD1EAF4123A8F61A2E5CFF74E319D1C8C63FCC8CF6281A4FD34386832966C56D1246C8DDC0A9FDBD944632B917663D4F53D68032199FB7DC1406C921828B13DFD3BA83E4B9442F42013D074A0A1F21C21B9EEFB6828596D1804F5FB68251C67CB5E6DBF69387B7491C121BFCCE40F06300F7DDBAB7F48341DB381FE9CF64C164C9E4539DCF26FA8E3C778F1C1F5FDF7FAC7B283BA6D98189838716261C11E362C2A1628225088A077055B0141996A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A831F2222CA6D41A1CFC191AF61411CDC3689DAF614115DDF888CBFF1F1A3980ECF11037ED0682319BE5E426FA76F807B224FF451735A2CBF2DE5EBA30E31EC8D47EEA235CDE5AE716FFDB2FF00B23FD1432B91F96BB8F74007D1419517965BA1B7E5F4A0D9627961B8022E94120C1F2E7312D75A09060F06C84B5D6824185C5254B74A0DF616C6C96B8A0DDE2E1F8605066AAD850556A05A816A05A816A05A816A05A816A05A816A05A816A05A82AB502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D40B502D4149506831E5C257EEA0C19B618E4FBA3ECA0C09B87C527DD1F650604DE5F4127DC1F6506BE6F2B31E4FF863FDDFF4D060CFE4CE14C087851D4F736923ED341A6CAFD37F1BC824B620463DF1C853EC5603ECA0C17FD2D71E6EC7C98FD4B32FEFBD052BFA55E377B99B32E7B4F8EBFE8A2E57E3FD2C71104190E5BDBD391FE8B50CB361FD31703560D262CD2D8DECF932D8FD4C288966CBE51713D9C86C0DAB1A1917B2508AD27FB6DA9BEDA09341B1471F77ECA0CE8B0D10765064050283EDA816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A05A816A0F8545050D023768A0B2D810B768A0B4DB4E3376A8A0B6DB1E21FB83EA14141E3D827FE18FA8507CFF0097303FECC7D4282A1C7B047FC35FA8505C1B1E10FF00863EA1415AED38A3B107D4282E2EDD8E3EE8A0AC61C23B14505431E31D8282B11A8EEA0FA14507DB502D40B502D40B502D40B502D40B502D40B502D40B502D40B507DE940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E940E941FFFD9,'654321','\bqJ????????5R A???q\n%??_','876876','',0,'',0,0,0,'','0','','','','',3,1,'2012-12-12'),
  (2,'Stanford University','kjhkjh','stan@stan.ac.in','www.stan.ac.in','0987654321','kjhkjh','kjhkjh',0x3F3F3F3F00104A464946000102000064006400003F3F00114475636B79000100040000005000003F3F002641646F626500643F000000010300150403060A0D0000053F0000083F00000D3F0000151A3F3F003F000202020202020202020203020202030403020203040504040404040506050505050505060607070807070609090A0A09090C0C0C0C0C0C0C0C0C0C0C0C0C0C0C01030303050405090606090D0B090B0D0F0E0E0E0E0F0F0C0C0C0C0C0F0F0C0C0C0C0C0C0F0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C3F3F001108005A0061030111000211010311013F3F003F0000020203010100000000000000000000000405060102030708010001050101010000000000000000000000010203040506080710000104020005040202030000000000000100020304111210202113054031412232143034233324110001020205070808070000000000000001110200032131415112206171223242043F3F3F3F523F132310403F3F62723F33303F3F3F3F142412000102030505070501000000000000000100113102121020216103304151713F403F3F3F227213503F3F3F423213010002010303030403010100000000000100112131415161713F103F3F3F3F3F3F203F3F30403F3F000C030100021103110000013F3F3B3F400006430006430000000129043F6446553F2C3F183F7809483F65513F140024237E413F6E3F29232A1A3F3F50313F143F393F4975001B633F583F3F3F473F1B3F3F3F3F3F753F49503F2B743F3F3F3F3F5C0A3F133F675C323F3F3F01283F753F3F3F431B3F613F723F3F163F1B523F3F3F3F3F1C3F57373F3F3F3F3F3F3F5C3F3F3F4C453F3F793F19373F3F3F3F313F483F576E52092D5822363F3F1E3A463F79473F3F193E3F53663F3F603F3F3F3C3F3F6C53673F233F513F6B3F6F216E3F5A703F3F533F3F3F3F3F613F773F2C5F515E3F3F3A3F3F5B136A3F1470483F3F3F3F3F5E1A3F3F3F3F2B093F3F76593F49303F363F0E3F35266B723F3F363F3F3F27363F7B0A453F3F1400393F30003F37100D43603F3417000018463F00000000000000007F3F3F00080101000105023F683F5A2E3F3F683F6B453F3F683F5A3F563F3F263F3F6634234C3F643F3F5A3F563F553F6459503F743F3F233F3F433F420F3F27603F133F3F3F6A3F584C3F2A1A3F3F3F7F3F163F3F393F5D5E6C3F3F3F3F3F351F33233F4C3949161616135B3F1B0A3F603F76283F1B3F313F367B3F4F664D3F2B3F3F1E3F703F753F3F3F393F3F4B79663F3F3F3F3F3B1A3F043F3F3F460F763FC4273F313F383F6A3F5B3F6F1A3F273F22043F543F5A3F43393F3F3F2A3F3F1C27563F3F083F3F3F3F273F783F3F3F7A3F283F163F353F3F3F3F3F303F3F3F273F17006C6C3F3F3F3F713F3F3F3F2A5B3F707C3F593F050765033F3F643B00163F133F5D3F3F3F6C3F593F5F3F3F00080102000105023F3B753F5F3F3F123F183F042C3F723F6E3F1B193C3F3F3F5144423F3F7B354D0F7541603F3F593B6D3F3F3F3F08703F5176153F766D493F3F3F3F753F3F7B3F353F381C2D3F403F573A4D3F3F3F3F393F5664FE7E3B3F4B283F571B4B3F3F3D3F5A453F023F11733F7E2ED42C3F11253F3F1A723F3F726D683F175E1D533F3F3F3F3F733F3F5B22563F5F3F3F00080103000105023F3B3F3F673F394E76543F59553F3F363A563F0066182E32723F29443F6E483F3F6C3F3F3A3F3D3F75483F3F3F3F353F363F3F3F243F3F403F293F3F3F3F7F3F3F073F044F7B0757463F3F3F0B3F3F7B3F710B1A3F3F3F213F3F37693F3F7B3F3F221F310B3F3F3F2227683F273F112A3F7F623F3F3F3F12783F3F0F3F3F3F3F3F38193F2C3F3F4E3F1B2F3F3F3F0B533F023F3F2F3F3F3F0C303F3F3F3F4E3F712B0A3F3F3F6F4C3F4D3F783F3F304910113F1C3F3F1C3F223F033F3F04340B55787B6D673F3F3F3F673F3F3F003F3F0008010202063F023F2D6B6C3F683F3F3F003F3F3F73463F133F3F6B1E543F2A3F13183F010B303F3F3F5B3F3F6D17333F2A3F3F3F283F3F3771503F3F580F3F3F003F3F0008010302063F023F2D3F613F554D04673F0D4E2A6A3F3F3F3F093F3F763F3F197B3F3F3F3F0F2A1C5B3F793F3F695818724E21763F3F093F3F3F1C573F793F3F4E103F173F01013F533F213F3F3F3F283F3F3F4D3F3F59593F3F79473F3F0D4D3F390E3F694D3F3F693F1B4F253F3F3D423F3F140D3F3F3F6D216B3F4F503F3F14396C71513F166C633F6A7D2130217A663F0E603F293F3F3F3F3F0C3F3F3F3F3F67723C3F3F3F3F3F3F003F3F0008010101063F023F193F3B3F27294F4C3F3F2E3F3F3F3F083F293F3F1F603F613F3B0973716B3F3F3F3F023F7E213F0C27782C783F1E5876153F44783F3F613F053F62543F3F3F3F3F3F3F3F3F201C3F3F3F1A3F7A5F183F341F3F3F710D603F2C3F2D3F3F3F635B3F313F6B523F333F3F4B3F3F406B773F6E3F3F21073F544B6E3F3F313F193F47564B5A3F3F1B3F79204B3F3F31553F3F3F3F167C3F6D3F65233F0A3F3F650F3F3A4E3F3F3F3F735C3F3F2E3F01050B1B3F3F120F133F113F1258763F7D3F093F27563F3F3D313F3F103F083F5A2B3F3F3E443F3F00603F3D503F2C334B703F3F6D663F3F3F3F3F3F3F0F3F3F3F67683F01193F53133F521B273F3F083F7039663F5A003F613F323F3F3F223F3F0C0F3F4D4B313F3F613F3F303F3F4E3F3F3F3F3F3F3B3F0754075A3F3F483F03413F63704D3F3F473F383F3F433F7E386F3F6F3F1C533F3F3F3F43455C3F3F3F256B4F3F3F173F2D1B2C62732642383F3F3F3F103F1C403F3F680C263F334D6E313F3F3F3F513F7C3F7E3F703F3F293F2F0D711D113F5303563F3F2D3F493F77564A07513F343F083F3F71403F153F3F3079686B14462C0D3F500D093F363F783F3F3F0C262A2E14653F3F423F3F3F3F3F3F683F3F2B64263F723F3F3F713F573F5A3F3F3F3F3F3F003F3F0008010103013F213F003F3F3F27643F683F3F6C3F3F17403F3F76623F173F3F066E3F3F3F5A47363F363F3F3F767A0B3F143F3F7679763E3F6028783F3F4B3F3F3F3F5A3F56503F6F3F3A663E1A3F563F3F3F3F793F3F1D3F3F3F3F3F3B3F393A7D3F12353F063F3F3F063F3F3463753F3F3F5162172978083F5D3F3F3F373F3F3F3F3F6B3F27723F210B1D433F103F3F293F3A701D3F3F153F3F3F553F7C3F3F01413F3F3F3F1A463F3F3F3F723F6E3F033F6F322A3F3F493F79777D3A3342153F7D2F3F3F053F5D133F3F593F3A3F3F2C3F3F3F3F3F3F3F4A6D3F3F375E3F5D66623F003F3F3F3F3F3F6A3F3F3F3D3F44185A2C3F3F793F3F3F0A3F3F3F3F3F123F3F483F3F3F573F753F3F063F3F5C723F3F103F3F08173F1D603F263F3F12596D643F1B766E3F3F16463F163F534B343D483F6A3F3F183F2E3F3F3F3F28763F3F7E3F3F4004013F3F3D213F3F0F6D433F793F3F39693F3F3F3F3F3F3F0F683F3F003F3F3F07313F6A01633F3F3F6B3F573D0A3F6C3F3F3F0C3F3B1440543F62620031FC79683F3F3D3F3F3F3F3F3F153F113F3F3F713F3F3F756C3F3F730C5E3F573A222B3F3F6F71137C10065E3F3366293F513F3F397D3F3F5C3F3F587E3F3F1F03643F3F3F0B5E3F3F3F3F3F393F33493F563F3F3F3F053F0C3F3F3F3F743F6B3F3F3F3F176E743F3F3F3F073F6B1B7C6D2E5C3F3F3F173B7C383F3F393F3F023F3F3F3F3F1C3F063F2B533F7B3F3F6F3F6121615B43463F75323F23455021583F6779723F3F00023F3F773F3B3F7F3F5F3F3F0008010203013F213F723F3F2E5C3F723F3F2E5C3F3F723F023F5C3F723F3F3F0F2B3F053F783F323F363F3F3C3B443F073F4B3F2F3F05195A13757E3F515B3F1D5A0A2D3F6F443F3F3F6D0F3F3F3F3F3F0D1C543F3F6B3F3F4F3F264D373F77743F3F3F3F3F3F3F083F3F4B3F3F69153F3F0F3F3F313F3F3F1A6A3F66033F473F073F103F793D141D5F3F3F3F3F3F1A3F523F3F3F2F3F1F4B3F563F25213F003F3F3F6F3F3F4D3F263F3F2D633F3F3F3F3F343F3F3716343F3F3F4A3F6F3F3F3F3F6E573F623F597C3F3F723F3F2F3F00173F3F0008010303013F213F523F4A3F2A543F523F4A3F2A54633F3F3F3D5657684A3F2A543F45156E3F3F3F3F3F3F3F5F7D3F513F5B3F2A3A3F1A3F433F3F4F0D3F4D0B3F3F473F3F5E3F2B3F456C013F3F3F146F0E5C3F3F433F1D3F3F3F3F003F3F3F021D3E3F3F3F3F3F523F3F163F186E3F7F11013F0F3F1F3F306A3E0D3F43643F3F7D62203F164A3F06353F343D4D3F3F3F2A3F513F7E3F1D6E7347573F775F743F4A3F373F3F3F3F3F383F7D633F514B3F233F3F3F453F653F3F0535073F3F3F42563F3F633F4D3F5B3F593F3F3F573F3F3F7D3F3E284531693F623F6B3D7F073F67366526673F3F3F593F1E3F3F743F0A3F5D3F3E0E663F3F3F163F5F5D3F70433F223F3F3F3F3F3F453F2B3F3F3F3F3F3F3F3F3F3F173F3F7B1A7A2F3F5C3F3F3F5E3F193F3F603F003F753F67093F3A3F293F653F7E660D5A3F717B3F7E3F4C603F3F7130043F6E533F513F723F14233F3F5F3F0E35485B1C5F553F3F3F055B623F7E3F623F773D3F3F3F1C3F3F3F69362E753A3D3F452C18563F3F123F3F2E5F3F3F723F3F003F3F3F000C03010002110311000010243F693F3F3F26013E3F3F003F3F3F3F38633F3E3F3F1F3C5E2C3F323F3F07360B5F0F4D3F3F31743F3F472347553F3F323F3F6A49243F492F3F3F0008010103013F103F1E0F3F1E0F3F1E0F3F3F3F3F4E3F1F3F493F3F3F4A3F3F3F4E3F1F3F493F3F3F4A3F3F3F4A3F3F3F4A3F3F3F4A3F3F3F4E3F3F413F123F3F3F3F76143F3F35152F271C323F6C435F6C3B683F2C3F263F3F3F6B74503F6F683F3E133F3B533F293F41191B204C303F093F153F353F053F174662093F3F3F5C793F11063F3F234E3F21413F773F443F3F3F3F0576553F623F6B3F3F643F153F3F3F433F443F3F49293F103F553F453F3F4F3F203F3F3F5A233F2B3F160C3F3F303F0B3F54343F033566563F3F3F07213F6A3F3F3F00763F3F603F3F3F3F323F763F323F056E3F623F213F123F453F3F3F403F327B3F3F463F3F3F3F7C705C7E123F543F113F3F3F3F583F1B1077102E3F606A3F34270B1538583F293F723F3F0C720E3F0A3F1C3F3F3F091B3F3F103F50130903193F0D1B3F173F6F2E7835313F406E3F2A3F3F293F6A3F3F3F1B3F5055633F3F4D3F3F605E513F1F003F123F3F3F173F713F453F0021623F3F34474111571E253F7E213F353F1D3F733F036C3F413F6B5D5E3F3F3F503F3A3F3F3F787D3F17283F433F3F293F3F753F23703F0B0D4D3F3F7A3F3F3F4D003F65403200503F28423F3F6B3F3F0A3F3F3F073F3F3F215C023F003F6B393F2634163F3F2B3F3F77253F3F3F0E043F3F3F40633F384B4F3F033F0D3F3F3F22733F25613F3F3F613F4750423F3F3F6C33275D5468385B3F3F3F703F3F4B2B213F3F713F3F5E55043F053F194F763F79403F3F3F3F2D3F0F61263F3F3F3F773F353F40111B24033F16073F3F5F2F4071550F3F2E463F733F67293F64163F77503F3F3F3F296E3F1E3F146F3F01063F3F3F42553F3F3F2B513C3F57553F613F13763F60504D143F3F3F3F393F5B3F21115D663F3F042D0B5D3F3F0E177B7B3F3F0B572E20583F2D47523F6E3F3F5B583F773904653740503F3F5A6E3F3F491B515D643F69714C3F163F2D3F59562D3F3F333F6C3F303F7137273F583F3F533F3F1D2A163F0B53201B3F6E62133F3F33523F3F283F18513F433F3F3F3F3F123F483F3F4B493F0F5E3F3F3F3F3F42373F3F3F3F3F3F023F653F103F3F65393F3F593F3F67333F06423F3F3F2D1C3F3F393F67323F653F3F003F3F3F0008010203013F103F1F3F3F005F3F100D517B073F3F3F3F3F6D3F3276633F3F0C22703F553F3F676B663F3F3F0038652B3F480C693F3F3E3F3F03493F3F3F7D4E053F0E3F064117296F3F3F3F3F3F3F2C740F3F3F665A713F717F3F3F753F3F3F476D675F3F3F3F3F3F3F3F1F3F663F3F053F7A3F3F3F3F043F3F633F693F243F3F394F3F3F3F69613F347D3F0C7213263F1D4F3F5D30613F3F3F3F3F3F3F3F3F4056073F3F4B76733F3F4C1B5D3F5F3F633F2B7A3F4F3F3F3F223F2F463F73473F3F273F76103F3F041C287C3F3F0C3F0F6945473F093F30273F223F3F3F323F3F293F040A3F3F11564D3A4A3F3F3F3F1E6B3F713F3F533F624743473F3F183F78013F0D3F3F351F2A3F3F3F3F663561716A3E625D63173F603F763F2E3F3F3F3F723F183F4757273F3F3F073F4A6D3F5A7C3F3F543F2F3F3F3F3F3F243F3F3F2B3F3F403F673F7E3F3F2D3F3F3F3F3F357B3F3F505055703F3F3E7D0B3F0A41687B3F3F3F5207193F263F603F553F3F6811635E3F3F3F3F17723F3F0B3F3F5C3F3F3F3F3F3F3F5B486B613F3F3F3F3F1B3F3F3F3F3F5C3F723F3F3F5A5B3F6F453F2F3F3F3F3F0008010303013F103F003F3F003F3F2A4D3F3F1C3B3F6A3F113F3F3F3F3F3F3F3F6F153F00173F420914514D4D0E7A3F3F3F393F2A3F3F3F753F063F153F435B023F3F643F8B3F3F713F3B663F4A3F693F133F3F3F7D3F3F3F302C013F3F3F3F267E3F3F393F3B3F3F3F653F59773F3F053F5D163F3F09523F3F175E3F76430D3F013F3F3B3F3F3F3F3F3F3F663F3F765B3F2F3F353F50073F3F3F3F3F3F76343F123F04666A3F3F7F3F3F4B0D3F216F3F3F4D223F2A3F3F3F3F713F0A3F3F6C743F3F633F3F3F3F403F4D3F3F3F5C62160D64167C6F3F3F3F3F603F6B147E3F3F22053F713F733F252C621A3F3F3F6E3F3F3F3F3F412C3F3F423F3F2D3F3F3F6D3F3F1A4C423F6A3F150D3F3F3F693F7A3F08003F18483F1970793F1F3F353F343F4E6E6C3F303F3F3F7F6D653F433F7D3F09153F38563F4E3F3F3F3E403F0B2E25483F71573F233F3F3F3F3F3F17513F3F6A4034213F1C5F783D3F3F3F4F7F493F4C3F6A3F003D3F3F7B3F3F3F0E073F3F3F143F007D3F3F3F3F590A16443F3F673F553F1A3F3F5F543F3F2F3F78033F433F30606F5A07183F753F095F3F783F3F3F493F3F3D3F3F693F0F3F1E25043F0F3F3F6879793F3F3F3F624B4B3F0A6D233F113F5A3F0A3F5A3F723F0B2D38133F3F54213F3F0F3F3F3F422974503F0A3F3F1B3F473F335F3F25113F05002154BA3F312E3F216050743F6A3F3F3F3F7810323F47303F17173F203F363F3F284C28541D623F703F3F173F3F023B603F3F3F3F270D3F153F6D723F27393F604D3F3F7D455D1A3F545D32053F3F2D1B3F3F09433F3F3F3F3F3F3F203F3F,'123123','?)?L??KTt????','876876','',0,'',0,0,0,'','0','','','','',3,1,'2013-12-03'),
  (7,'IIT-K','','nksinghiitk@gmail.com','www.iitop.ac.in','2134234','lkkljklj','',0x89504E470D0A1A0A0000000D49484452000000F6000000730802000000240D68F4000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000206348524D00007A26000080840000FA00000080E8000075300000EA6000003A98000017709CBA513C0000525F49444154785EED7D07585357FF7F6CADADB66FD5CEB7BB7639AA5D566BDD7BD7BD50714F0444F696BDF7DE2B8C90B0431881304220104602011220EC4D42264998F2FFC6584A7161A5BFB7FF9AFBE4E1B9DC7BEEB9677CCEF77EBEE39C336B7C7C1CA138FE9E16181D1D696BAB6F69A91D181860B118B3662166C98E97DE7CF3CDD75FFFCFFBEF7FB468D1F20F3FFC7CF6EC57FE9EF72B72BDDF020071C5F137B5804824C0E3311111F6D1D12E3636375252423232A21313834342ACDCDCEE787BEB93C9594343837FD3DB15D9CA5B4001F199440287D3DBD4C4ECEBEB181C9440BE22113F22C23D3CDC362B2B2A29C9B7B5B55A2A158150C7628342432DEDEC6E62307E020117528E8E8E0E0C888686A4F01B1E1E9EC932BDF07929203E3310181A1A6A6B6B2C2AC21389A9647246717116FCDBDC5C1711E18AC7479794A4C5C77B07075B5655156666465959DD888A728A8BF30C0CB42A2A22F0789C9E9E8EF6F6268944D4DFDFDBD9D9323CAC10ED33D32F0A293E63ED08543B2ACA3327275E22E1D368447777DD8808E7E868EFC848170A059F95853237BFB56FDF4F188C5774B4EBE9D31BECEC342B2BF351280F5B5B8DB8B8003219DFD3D37E6F7CBCA5A5A1AC8CC8E1748D8FDFBB370E1714C7F3B680428A3F6F0B0210F9FC7E02213128C83A292988C92CCFCF4F0A0FB7494B0B7372D236325236323AEDE07067EBD6EFBFFDF6DDB838B7C444DF73E7B600DCB5B44EBBB9E9989A5EB0B2BA0A2381CDEEEAEEEE8C8B0BCECFC78A443C3EBF0FB27DDEC2299E5770F1E7C74057579B9F9F557A3A924ACDBB7BF79A8D8D6A4C8C9B9DDD2D24D2CEC545D3D9F98EAAEA6F20B677EDFA4E4F4F495FFFB4BFBF6960E05D15957DAEAE7A20BC8382AC4C4DCF5959DD2493D36262BCD4D50F656444B5B531904897CCCC84E72F9E220785147F2E0CDC1B1FABA929F6F333251062592C9ABFBFA587872EA0564BEB8493D36D3D3D90DFEA3E3E46BABAA76EDCD8A3A373CAC9E98E9D9DEA9D3B47ADACAE0504DCF5F23251573F02292D2DAF5958DC30323A6F6171854ACDA9A9297472D2CAC8403D57E1140F2B2C2ACF8F016017586CA887871E90EFACAC580201EDE6A66D6A7A11144A80F2F1E3ABAF5DDB9B9212A8A272F0D2A51D494901345A464646B0B5B5EAA64D8BCDCD6FD8D8DC5456DE646272D1C0E08CB1F179F8C1180808B03435BD616878DEDFFF6E5959DEF317F205CF4121C59F0B005C6E5F626288AAEAE17DFBBEF3F63625935340246B6B1F03CB77727280A9E995CD9B170707DBC06FCF9EEF4D4DAF5B5B6B19195D5656DEB366CD17DEDE268181E68686E76EDF3E72F3E65E73F32BE6E6579D9C3400DCBB777F77ECD85A0B8B5BE5E585CF553EC5C30A2EFE9C180093078B55EBEE7EF7C489B58686177C7C8C6D6D6FBABBEBC4C4B8E4E6A2C07E72E8D09A0B17760607DBDDB973F2F8F1B5DF7CF3F6C68D4B0E1F5E676878514FEF82A6E6196B6B1513930BC064EE93997D807817172D1D9DD3DADAE73332921436F2E7EC2085D1F0F91B50968350C8C7E1908E8E1A1A1AC7410603BE63625C819483D2A9A9797CD3A6AF76EFFEC9DBDBD0C54567D7AEE5AAAA87808D989A5EFAF2CB3756AFFE4457F7340C898000337DFDB3FBF77F77FBF6090CC63B2707555F4F9D99C2BDF0B92888CA0C4000EC8662B1B0A6A62C36D607A43240163CF6403CAE5CD979F8F0AAEBD7F702AC0F1E5C057CE6E2C5EDE7CE6DBA7061FB9123AB8F1C59A5ACBCD9D8F802A8A4616176F6F61A274EAC717535A05249CDCD4CB08BCF40C914592888CA0C6200AC87E090079388B5F54D2F2F03E01BC0C5376E5C74ECD82F6008F7F3334A4EF6B7B0B80ECA250AE50A161573F38B5A5AC7EF13F1FD5A5AA782822CCDCCAE5CBEBC3B252546E1F499C17E5148F19969CCD1D1B1D2D282A828772F2F7D57576D24D21E200EFC7BC3864520AA2F5EDCAAAF7FC6DBDBD8C808F8BA11D0182DAD63161697B5B54F9C3EBD5E4969C36FBFFDA8A9792238D85C43E38887873944AACC4CB114B928A4F84C61402219201271A1A1B67E7EC67E7E26F6F6EAE7CF6F3D766CCD860D5F5CBDBAFBC2852D9A9AC780BAD8D8A84002F0DB6B691D852B376FEE03BAB267CF8A3B778E8044D7D63E09D23D212110C2B966AA608A7C14527C6630303828CDCD4DB2B0B86A6FAF0642DAD1F1F6B163BFAE59F3D9D1A3AB4E9D5A77F6EC16C0AE86C6516BEB1B20DD7D7C4C00D03A3A272E5FDEBE6DDBD2438756ABAA1E50533B74EEDCD693277FF5F5B5E2F33933532C452E0A293E531800F65C549479F5EAAE6BD7760321F1F4D403B3E0CA951F1E3FFE2BC0F7D2A55D2626E7AF5FDF73FDFA3E23A3738686CAD7AEED0167FED5AB3B7FFDF53330819F3FBFEDE0C1D5070FAEDCBF7FB9AFAFEDC888229E76A67A46112F3E632D395E575769647415506B6878061CF2107602447CE7CEE5E6E6976FDCD80B4129E7CF6F011F279C835D058C2D404EC0B5B977EF77A74F6F0493CB91236BF7EE5D0EE82F2F2F98B942297252407CE630C0E77361160F88706D6DB09380C77EDB81033F01CF3E7366139096DBB70F03630147FD952B3B9494D601D021366BC78E25C0D401DF20D4610CC079549417D81F67AE508A9C14109F510CDC1B1F898F0F3D7F7E3BD01570FA807E09A692D5AB3FFAF5D7CF41B483720962FBFAF5DD40BBF7ECF96EDDBA2FC1F503121D08CCE6CD5F7FF7DD423DBD4B306968464BA4C84C01F199C600C47C9B9ADE3E74E86798EC535545DAB5EBC703077ED6D23A79E4C84A2027EAEA876FDC38F0FDF7EF29296D83702B1595C31089A5A97972EDDA4FB76EFD1A89F4168BC5335DA2173D3F854565861100D38DA3A27C6D6C340607851D1D0D666657D3D3A3BBBB5B131303C15EBE66CDC79F7FFEDA962DDF4068B850081329D06565993939095BB77EBB7DFB8AFCFC7485D36786FB43615199F1061D1B1F2D2ECE4C4C0C181E96C25C354B4B9584043F784B777723CC0382696C464657607E507B7BDDE8E8506969269D5EC0E375FBF959DAD8A8331815335E1E45860A293EC3180031CC6094C6C5F9F4F5B5F3785C53D36BA6A6574746867272E25028B7AA2A224C77282EC6F2F93D4D4DD5B1B1AE2452F2E0A018B09E9E1EDED5D534C3A55164A790E27F0706AAAA4AFDFD6D3A3B59FDFD3DCECE5AB76E1D118BF910676B6474B6A1A1A2B595E1E969585696535292696B7B8340C088C50238279152F87CF6DF519E173C4F85149F79005457536362FC3A3AEA99CCE28000135D5D6589441819E97EE0C0B2CACA82FAFAAA63C756C3D44C58364859796B5252180C80D2D26C2A355F2A1D98F9D2BCF0392A203EF310E8E96903BC02DB861582404EBBB9E90E0E0EE070A8356BDEA3D3CBFAFA3A55550F8687DB87873BFFFCF3C7AEAE263C5E178B55D9DACA1C1D55383567BE3B14109FF936EDEA6A66322B0482FEA0201B65E50D10B2020A259D5EACA67600965B81F5801213FDE022906F1393CBE6E6D740843737577775B114018633DF190A2EFE77B46977777373734D7777F7AD5BCAE7CEED484B0B2691923233638C8D957B7B3B20263121C11726EDE3F19171711E6A6A0743439D6B6B8BABAA0A40FCFF1DE579C1F35448F199074066663CC8EF82829453A7769E38B1C1CD4D0B96077273D3BB70616B7777BB48C4B5B05001E80705DDF5F5D587696F1A1ACA4545194141F6898961C3C343335FA0173B4705C467B2FFEF8D8F9697E78484D8A4A686C2ACFB5DBB96696A1E090FB76232C991910E20B061D10891A81FE6F8C004E4E8685B47C79B7BF67CBB63C7B2D4D40898CE0C6988C4849E9E4630AECF64B15EECBC14109FB1FE1F1B1F2391F0D7AFEFB7B4BC0E2B5A2929EDFCECB3794A4A1B7575CF595BEB1D3DBA7DD5AA45BABA67C02B74F0E08F7BF6ACBA7061FFDEBD3FFDF4D3279F7FFEC6A54B077C7C4C0D0CCE1F3EFC8BBEFEE586869A192BD60B9F9102E2330681BEBE2E7DFD6B2B56CCDFBF7FD5C183DBDE7F7FDE6BAF213EF9E43F8B162DF8E493D76129F7152B3E85F9F94141165A5A6757AEFC72DE3CC4FCF9B320D9DB6FBF327F3EE29B6FDE5DBCF8FD975F46FCE73F2F9B9868B1D93D3356B2173B2305C467A6FF213405A6151F3BB6FED0A11F366D5AF2CA2B88850B677FF9E5C2458BDEFCE5972F77EC0036F2634242504D0DB9B4145F5B4B8E89F15EB76ED99225EFEEDAF5C3E6CDCBBEFDF60348FFEAAB88B9735F82C1B078F17FD3D26267A6642F7C2E0A88CF0C0460D1640D8DB37BF72E3333BB0013E961EDABD5AB3FDEB56BE9C9936B41DD84696CA9A9219D9D4C98B5098BA8C4C7FBF5F535C6C7071C3DBA16964FF1F2D2B6B1B97EE2C4FAEFBEFBEFA79FFEE7C30FE7AC5FFF3912E906EBEACF4CE15EEC5C14109F99FEAFAD2DB5B151BB3FA9671FACD0098BA51C39F2D39933EB61F16543C3B3AAAA477B7AEA0302CC95953742D4F8952BDB91480726B3C4D6161ED90E2B0A6130EEF1F15E2A2A07BEF8E2D5F5EB81B29FF4F7B760322B67A6702F762E0A88CF40FFC384792231D1C101C2C457ABAB9F484CF4C7E1C2C10E989A1A0E4B64C134086B6BB5A6262A2C7C058046A19C35340EC3122B0C464954948B9ADA6F77EF9ECFCC4492C9A9E1E18E172FEE028515A60E79791912088AC59767A07714109F814604225E5F5FEEEAAA05B404E20A0B0A30F5F52552295F28EC03C97DE9D216588AB6BBBB1EC6C0F1E3AB34348E9D3DBB0126E1D7D595868458DFBCB907E6E4C3E4093219979F1F1F1FEF6361018B94AB60303E2C96C2AE3203BDA380F80C342264D1DCCCB0B353BF756B2FCCEBB97871CF9D3B4AF6F6DA61614EB0B2212C9972E1C28EB2323C58C76199665844DCD7D7ACA181929F9F0093DF60CEB281C1B9E4E4400F0F232BAB5B30180E1DFA096679128958C59A9D33D2370A88CF48338EF7F5757B78981E3EFCC3D2A50BE6CC412C5800E6C2D92B56BC03D405D8F9FAF55F80159C484C81E5F101D9E01ECACEC65CBDFADB2FBF7C0CEBA89C3DBB0902B3F6EEFD61DBB6255BB77EB978F13C1595930A67FECC748C224665A6DA11F2A1D18A6D6DF59494F6AF5CB9F89B6FDE5BBC78E1D2A5EF2C5FFEEEA143AB366CF8EAF3CF5F87559861453898E106EEFD3367B62E5932FFDB6FDF8699CBB042D0D9B3DB4E9EDC7AEDDA615891D9C4E4565151EE0C16EC05CF4A21C5671800B03B212C6E1812023AE5695DDD8B66662A77EFDE0077BD8ACAA1A347572B2BAFBF7C1900BD16D6F204F6022B07E9E99D8B8DF5ABA82892970356D592EFD9A93866AA0514109FA996FC533E104DD5D2D228BF74E8D0210C06595E9E9B9686044B0BAC449E9E1E09412CB0C16C464604ECF0F6D74A70F4E8510A85F2D79E7DA19E5240FC6FEFEE828282F9F3E70706064E5E327C747404E6493C4FB8D5A46CFFF62AFC7FFD0205C4A7D57DE054FF9B8E29AFFFCB6F9956355EC8440A883F43B703FEE4A9274EA63C4CA552172D5A3425C1E39E7A5C2693F39C48F3C89C9F5C9867A8D8BF3AA902E2D3ED5E88957DF9E5979F8CAAB6B6B68F3FFEF8C9103734347CFDF5D79F304EE4B7260EF91B1F99B302E2D3E93C05C4A7D34AE34A4A4A3131315390A7A7A7370F22627F3F20A3DDBB77272626565454C8A5AFAAAA2A8C8A294FC1235555558D8D8D8F94E25BB76E0D0F0FAFAFAFFFE28B2F260F8329391B1919BD0A7189935E3DAD6ABC908914109F56B77775752D5FBEDCD8D878B2E0042512B038F9F9EAEAEA4F3FFD74F5EAD572F8029A6B6B6B67CF9E3DF9A9A0A0A037DE7863FBF6ED8F84388D465BB66CD9DCB9736115E7C9107F38670683A190E2D3E93C05C4A7D34AB2345C2E77EDDAB54E4E4E13C80323C9850B17FAFBFB1FCE420E5F0B0B0B90B5B071DB23B1284F03FCE70925787818C8AF6CD9B2A5A6E64104CB7438FD742BF96F4CA780F833F4EAC0C0406A6AEAE4078844627B7BFB3364314349592CD6E5CB976728B37F79360A88FFCB3B58513D05C41518F897B7C00B0D7104C24FFED3D5254FF4F3C4C5293D3F711D4E0E1DCA98B86B6D5D3EF9D6E4A7265F7FF8FC09F943E22977BBBA066EDF262D5D8A9A372FE8B5D702B76FC79694F49E3D4B98E6EBFEE5287E62F55E6888E7E777CA91376B961F1EFF8052C7C434C095909007F68A89D6E3F106E1FAECD9FEF0F7E597FD5B5A1EECC8B37A75FCE346C57DC5F4C1281A1BBF07395028BDC6C694D75F0F7A18C493134FE9B28E8E810F3F8C8047CACBFB8687475B5B85E1E1758B178311F34F2301FE5DB932AEBABA7FE2A5700E571EF9AE1707F42F34C4E5A8027CC3DFF7DF0FEFE9916D42D2DB2B817F85C247AC4A05D74F9CC0CB01A4AD2D8B0D64B1F8BB77E3A60371482C100CC9D14622753F13C42F5FCE95BF62322E6934CEC310AFAC946DD839B93C704501F117673C3FA2A6D0FD7A7AC5724CECDC990A0BE0C356DF8FC3045C2F2C94A1137E0B178688C5C3B6B6158181B5D381F8C0C0B08646E193D1F6B87C3EF84026C2A73C3B3838FA84723E9CFE85ED668514F71B1919FBE597043926ECEC645B913C013A70F7E79F1F30135FDFEA356B12D86C99D47F1CA4266E3D21CD53D500393B9ABE309ECEBB5E1CC42B202EFBFA373509DE7C33189001602A2EEE7932C48107CB31F4F1C74850FB9EC0A127DF82811414C4F86B525C4EC41510FF6BC3F28586B8443202B8812F3EB41D0A25D332E1B76851D423C104EC1CAE0339191A1A7DEFBD3079621F9FEA69421C92C1B3F29CE3E2204065AACDE409F95CBB962F7FDDC8C8F8BDDFFB19D400500C1ED9EB0A293EB9595E68884747CB609D98F86013A909ADEE91F8930BEFC4C466683E13130A9C839EDADD2DD3501F072970CD4FDC1A1A1AEBEC1CC0625B9595097205171E04BC4E40F609F980C5502EC81313DB209FE6662110AAB7DE0AC5E15A15107FAA687FA1213E059A2065972C413D8E124C4E0C987BE595808D1B9327E3F2E107271E79E4C994BE999266CA5D184B60170743E1DCB98173E60480A572C2CAF94CF93C1510FFBE042F34C4FF7DDDA9A8D1C32DA080B80215FFF2165040FC5FDEC18AEA2920AEC0C0BFBC051410FF9777B0A27AFF7B884B5E7AF9A9BF29FDF4C4F4B325EFBE3B78E8D058EB230C6A4F79F0830F86AE5CB937F00C5B183F9CE118953A51DA5122F1E104D3A9CBC3B81CABAC844A49162E94BC364FBA76ED687CFC686EEEE0962D7F39E5239BE291E3E15E47C7B09696F4DB6F25735F97CC7B43BA6CD990A6E6BD474D0479384FE98A15F7C432BB2A1C53EEFE9F8DBDFF3DC41FAEFCBD91917BDDDDA34949839B36C9DBE5E1E678B8BDC6E8F4C9170777EF7E64233EE2410A65F245E8BFE9B7FE5853D3940C872E5C98787CE8F4E9E9F4EB53D38C9594C8E0F5D2CB80EC7B60182F2F1F52567E64CB4C3FE53431379A932379EB2DF9BBC64A4B61A43D28EDC285A3D9D90F37D44858D8D406F97D7612C88EC12347E0AE74CD1AB0ED4FBF919F33E53F11E21355BA373E3674F2E434213EB5CF5E9B3B4D8843E8D59F7AE5934F9EA94D657DF6F9E77FE4F0DADC7B3DB29DA8EEF5F5497FFEF9A9F09D0ED406376F96E73359760E1B1B3FDC32D34F399DF7C2EB26F03DF1AE3F6AB470E13465F9485494BC49652310062A91F84C2DFC9C89FFA110BF076B57DE17DEC037FE22C4EFAF67321DF13FB5B35F79F599DA148A37ACAF3F19CAC36666900344218E787BCF08C425AFCD7D90CF279F8C8486C2C8970D2188FF7AE8FB36FD94D381F8D09D3B93CB2F6F96A77EF120C1D4CFD79B6F8ED5D5C9CA2C12C906EAEFD4E599DAF92F27FE27421CE2594790C847227BA29E53A003BD3E85F88E7879FD35884B7FF8E1995A53F6059FF87CCBF58A0F3EB82791C868A840303310FFE493C9F940CEA3E9E98FAEDDB4534E07E2D2A54B9F0C7120E88F1422F78442E992257F2AF38F3FDE934AE51FCC676ADEE74FFC4F84F844D33CA17A53A033E5DF6123A3C73DFBD0D8B827A39B93545E185DCFD4ACF23E03D8FD49BC9D3C3974EDDA7460349D3423EEEE0FD71724250CA429459D7ECAE9BC57AE004CE98E3F9564DE1B8F84385C1CA3D14033FE539BDCBC297FE93335EFF327FE27421CD4CD91E0E06794E2F74019FA13522322A623C57F7F64B6E49D7706376E9C608DD36F59793981964C41212885D381D134D30C9B9A4A5E9A3DE51543376E3C5CCEE9A77CEA17E6E9109F2B5BB96ECA31D17123FEFE535E318AC12820FEC07E0274ED99202E6FE53F7D19BFFC723A109F3E949FF0599009ADE6E63FBD7DCD9A878BF4B81A3D156AF2ACC62894C11D3BFE94F89D771E59AA69A67CEA7BA77C9A1ED1C8CB973F01E2706B4849E94F6F59B04001F1A9264230333FB2511ED93D92575E9D74FDC1326B0FCB98873FBE7F19E872B558AE3F0DAE5F3F91332885330E717986A3F9F9D2EFBF7FF0A2D7E63DA1E44F4DF954884F51A31FAED1B08ECE94023C6810A9547E5D46CA172F7EEA8BFE72FB4FE7C17F2251992837CC5997FEF2CB3340FC830F9E0ADF996D6EB98E0B5213CA3CE2E3F320F3B7DE9E60C9D379DD53D3C85EC162FDD12C3D3DF24706B76E7D78004F33253CF8D4F782F553F2DE7B13C9A6421CAAD9D535A5006364F24483C86FC984D49F49F97470398369FE8910977937EAEA46DCDC249F7E2A6FDF872BFCC8EE193A77EEFF12E2F73A3B07D7AD9341EDC8917B7CBECC8A377B0EFC3BA4A13151E0A7C2683A50834CA45F7C31121171AFB71706CF88A7A72CDB850B277B5227F037CD94D379AFECA351542479FF7D792DC66A6A644AA45C015DB000D4F4A9221C1A64EB5659836CDF2E770EC88F113FBFA7F6CB0C627A4A56FF7B884F01C123FF7D58563D9C0CD2DCE37287CE9E05C551A699BDF9A6DC3E3DF9984EE6D36FEB87E13BB87FBF0C0ABFAF193B9DD74D27CDE0891300AF211515C9871F4A5E7B0D6C7943B76F3F324261FA29A7F35E7953C0B81A363494B1A3796FC8C207162F1E52579F4E7CC4E4969C4CCAA7DFC23392F27F0FF119A9862213450B3CAE059E13E263E07399F8FD7922E233B7392C433C323A22FF8D8E8F829BE099B3F8FFEA01A82054197EA3A3FFFECAFE0F7BE6AF431CACD743A08E343549DBDA065B5AA4ADADA322D15FAEC9E0F0606B6F4B79432985492EAF2BAD6AACEEE6F440DFFFE50CFFF90FB2D9ECD6D6D6EEEEEEBABABABE3E58C96D78101603F8BFADF2C430939FFC2BC5CA5F81B8B4B3939B4DE060B19CD4544E7A1A2723B52F311ECEB9D97876064E5056061EF8E9238C27E4628B936DE36C7423B4AFFA5FBAE07DE68ACFF9DB211AA631661E58775C699A48F20CD1ADF05E5805BCABADBDBFAB7BFA65F8BF4C093886AD23607B89A2A2222412099B46C4C5C5151717934824B8021BA440F9FFCFCA23FF86DC5FC6FF7FF6CD1C191F4DAC2EB4CE8D72C88F7522629C8868E73CD9CFB100E35480919DE4A11C65B7621D8928F8C912E4C7D9E5A12D73A2D39914582CF2C9CDF56C101FE1F2FAB3B3D8A9D87E7C664F4C643732AC3736A62B22884FCA17D7D4F42660FA73F01C7C7A5F72E24055D574FA89D1C2B447DBA906DCB08EB74417A1D32B7029650949A5F1382A0E5D14EF81F3B2497674C57A9737944D2737A150D8D6D0D0C9ACEEA0539805F8267C7A0F83CEEDEDE0B7360FB258E296A6CE7A66776BCBC8B38C40F97B8747469A5A3ACAA87503127131A59A4CA64310CA748A34390D2009762F01C95D78FFC0E170783C1E854261B1D8ECECECB2B232D824084E6059FE47EE3CF1B8D78DB15BC759045E49340369D3978F9454660FD781ADA36D3AC503B13D0110086126102A8283F3C9A4A9A6C0E964F5B834F7C6879FFCF8C0B0F45C8CCD07467BDE33D9F7BEC9FEF74D7F7BDFECC07B777F7BD7044E0EBE6B7E7081F1BEB7E0BAF981F74CF7CA7E770FBE6F7EEC5DA303FFD5DB7535CE65F869BDF90C1097D43139D8E4EE68644F34B22B24A837365A58563C40ABE0934943DD1D236C369700521CDB97848631D01B8BE6E6E48E8A1E2CDFFA70258747470855048B6853BB58DBF8E2F8E27A724B4F9B543A08E204DA1DEE76F577535B6984DA3C3F7CA059AC31A6305628792C111A1C1AE4767476D7D454E7E209019E358951AD446CA19369599003230B55E2E75C686D92EB645E868E6A2313DB6A2B057CEE53BB0D8A21164BE15746ABC7A4E4E5159479FA270F88259EFE09D1187C634B0721BF8259DFD2CFE58B2583D39181C046C864726E6E2E0683017CFBFAFA46444400BEFDFCFCACADAD1D1C1C40A203F461BB1FD83616B6BCE2F1784F2EA4B8BEBABF38A9DAF76ABDE3911697530C836D6CAF136D8E672B5D8F35449CE4E06CC784BD4FAD2624E86A1BF7F3CE55BB6EB07FE7B1F5EB4EEFDEA2AD75D3B528AF763ACF3E21CD505BB5C4FFE690E56FE2FC30E9D09FC269A0B9C4BD8D1276073C2E1A92EE0F3140A8AD46DCFE15A1B116A1FA33E2C67708D51F11AAAB1137BF4368AFFDC6EECC47770FCACE6F7D8F50FF19A1F21342750DE2D61AC48D9F8F23AD86C79F4219A60B71712D839D94C4CBCBE39149FD99A97D0918A025223A4D40290264F38B0A8415E5826232372F874BC8E293486C6C4A2F06CD2F2C181D78042E45D2015C59BA19C6DC39C92E8392466F6194308B530AB0CCB67AC18080CBEDEFE3F45199E5252C725629BEA4B638B220C224DC38202580C3673FDCA6D05E7DCDCDF4B8B81A5478350E45F0706444867597122AE2BD28763AE5E63A240B2D82B556AAB946A18F434590073922805552C867F73D997AF205034594EA8E4E76554D63786C7A657543594503BC9D466F6C6AEB6CEFEC2DA4D0AB6A1B6213F1C969055CDE93F410785153531300372727273434D4D1D131242404201E1C1C9C9E9EEEEFEF6F6363A3A5A5A5A6A616161656525292979707D761AFCDA1A1472C900BF51D14F5F566067519EEE8B25B9BE77C24D5459B8A742EF3372279AB917DEF14441B963BED63EA2D694E36E6B63087248F0501B7672823B9E0B6AAE98A65BFECDBF9AA96CAECCB4A0BF6AE5FB06AF93B470F1F0F0FCBEAEE784401A6037D31AFAF0B63C7555D26388AE0586F92F6B260AB68715B99B8A564ACBDF45E51707FE84D4183CC65261E1A3C86349FA3B37996FE7684FEF6570C772E34DCBBD2E9D2175627E768AF5F6C73420FEB7329C666BECE8EFFE8EEFCCAE6CC87C687111A9B115A9B5FD5D8740EE53C331097B634F5A2A2FBE213877AD9D2CE0E3E290F24B7B0ACAC3F0BDF138BEC4686F620C3FA92E2FBB3B245207B88057C629EB8A14E482DEF8E091FA8AF1D1BF9D3A70A246E5625C138D634881052C6A0E4D2B2130A1383D382EC63ED5124546A313621370E5B988CCA898ECC8F7089752AA828E009051842DC15A74B31F948F1E0543009B9FDF569E92596E6F9B6069418EF9A006FBA83537552740B2DB548F346DAEAB5642DB576624279A81BD5D3AAD0C628C7DCA82C38A0A5842CE4F53F6137A97E9E30BF88D6DC267361B47576D5D4B7F005C2A6E6CE3E4EBF5028120DC89CF63C81202C261589CEE8653F56E202BE41A704690DFB0401BEDDDDDDDDDCDC4060832007999D9F9F0FB80772929C9C0C7B65C14687F6F6F670ABB4B414860468A29287C20987253D2D913AD52A5FD6DCFAA2CC766F6E6A48466125B5A6ADA0A82C0E974EA6975535D049B85072F0759AF7A15A67E56E3CFA91A0ECEF1B76B548DCB47AF39A9F10A78F2232E211F525085C1022D11FE16485D8B60DB1F2FB55E6FA116D2DFCE9607A4A1A4E2BB33D3D589413DEEF7DA2EBEE4F83F4CCB1DA3CAEC536BEE9BA11B3CD92F30B7A8C7E9176CB5CB60343D26348B339DA9B107A5B11DA9BD6F9DDD64DF10B2B4CBB8976D9E1A7A59BEC8DA4A4BBE4A04E049A1EF031304C0AB44C0DDFEA71E715ADADAFA8AF558E719A01888FF038BDA931BD380C272D550872A5A383974BE8894172737278C4FCFEEC74617929BFB8004E7AD1686E1EA1373E76A09A36CCE30A4A4BD8A9F1FD84D4C13ED0FCFED897ACA28E6A8C320B2C08AC64D1B22984D8C21874012A8A108D21A39D531CCDA24DAD632D1D12EC128AE3D2CA71E8C29884C2F8DA861A0A8D12460C560FBE45AA264D363B4825E286226299B373B9A929D1DFAE20CCA1F4C279CC0FAB522E9EAD703146AD58EE8940442F5BD11AE18BBF75B544E526C3C339DFCAACC4D5899512DF584EE6F3FA27CB721090C0D47FCFFF9E7468A8BB8FDBC7E17338FCE0185C2032D13330C6D13B321099524E63F005221E5FC4130C0C0E8D3C8EA840E6028100C849545414401CE437486E4030814000E512544CB805F806C90DCCA4BCBC1CA88BBABABA999919501AA0E6191919CD7F9E033622E67188AE3567DFAC33DB9042C078C5E6A6A764D1C994A6EA461044B5759D8D4D3D75E565E88C84A8120A3D3B92A5F309CB64A5B4FF4F7B6E41698502B18B45E68A456B76AF47049B23AA53119C0A04356E4E9AC7ABF4D4971B89B3E27D112777213E7FFB03D52B217DBD2056A6C3C5FEC039F48B5CFEF7A73BB29CF7710901A3FE5725FB678F1E7A6364EFBCEE135F75E10246EEA3E277886F067CBF69B4DB04E75FD3D6201489B155147B7C6C460D25A3BA38203FD1331BED9F9B4469AEE308453145F8CF4D0ECF525BA58C727C5E88DF1B19E592B2FB29193D2931DC821C6119A51F8F078AC22564F3F078E021C2CAF281FA3A4953BDA88AC6CF2570D2B08262D27DEA522828A3F06139EEE8305E097154FA608EEAF0F0881FCEF72ECA22BF22B7B4A6D00DE76189B64D294E2EAD2FC59560357C6E03C43DD33D5CB12E31B9A8FC4A6262515C0C3122322B34168F223714076687D984DB367534C971097F3B19D545FE1E4556A615417E34E03C4981841F7F0C40207CE6BCEA3B674E18627604E255BF575F8FFDE29B90D7DFF199F3DFC41B97A869512D84A4326FA7F298E056065D3AF8C7B7582A1D6A6AED6AEFEC033D1DECF37D7D7C5673272629A7A4A2DACE231289494FCEC88F4FCDB5728E72704755D534B477F5B5B4F7F0F9222809E4C3E5092187C9F20C4C81603F0170C7C6C6DEB9730778083010C07765652588704F4F4F341A0DD4DCCBCB2B25250594514036EC61EBEDED9D9090008807F403C42704F9BDD16176517CF5F54F7A30C6949A1A3F02358DD1C9A86BAC2EC92F2DCAA150A9C4723AA98CD6D15CD3CDEE8EC92CF5424632ABFD7A124F95B95C96F6FFA18042D59081D94B3F5A7FF3F86B280B446B1C623413D1494060ED372275B695FB2F182B44484888643BC4A15F109FBFBBCA501F231D7AD083CF24D1EF4904FDC94E2DE1063DF82076E0751EC6748C143144F017D4E40EFDCECE41DD3C16653E4777334273ED8FEE57CE475BBAE7C6814595D65C7738C8F81CD23A9196EF90177B3CCCDC8710DFD1D353DDC4F2CD4DD8E4A73E4B6BFDD95887E785B898C5ECC3277249047E0959DCC0E09272D8E9D8BE8438A0DD43DD5D03743ABFB848504A1694140B4A289286FA811A3A1B9BCCC1A5B0339244B53460383030BAD161D29646F974ACDCF24CFD903BD1B9E80412D631C1D637CDC73BCDDF016597454CE181F631D0954BCD2A65960E8D8EB675B72516A6A8785F4311234BEB4ABCB30243B3421388C9BAE14671C5B1E221195D1914081A33D3291EF61541AEF4487F46A43FD948D371C1FC808F3F293F7C2C75E9125F0402501E3C6B4E14E2B554C4DCC839F31BFC9DEBE838725A6092CAA55C0D75163E5D20E04F7C623ABB39D8CC627259CD00584CA4127A354BF76EA0B55B4C4F1F0706A7582285136029434323D2C1E19E3EAE7F78A2AD7B683553C6D1F30AA8A151A9C0D32783004C84743A3D292909C00A541B2838486BA0DA8075D8A8D6DCDCDCD5D515A88BB3B333501438A75028901E365006590E7C067694051B0B3C22CF734C2AECCDF6AF77BD908445076766B7747613CB6A09D4AACEAEFA61719768942B1E170F8838A59579A422626F7B636E79B62326BB309F50E9B69381BC3E5130585C57572DFAB70D1F319267493211BDD1885A6744840D22330D15E79194A8B5A4CA0BD19B8C60F9238ADC103A1717ECDF65545DF558CBC113402F649555239D5AB362C495C96C72BC583224100D0C888460AA9C784A06F118F339065B11FA9B5F31DA7E25C6065F4B492ECF7723604E86995F8CB1B323447B1213EC33A2DCB23079B5B41206DD0A17BED44E7996E64665CCF312957BBC12525F66627F164ECC6472B2D23839693C628EA8A652D2522F2829125654489B1AC58C6A69136B80C1E41189C2D2D20166159F4CE412733939295C6226580FBB4203F814D2F8E8087CD11D135D5C53EDDB7A9A73A804CBD8BBBE19BEE19991E9F9A9AD6D8CD4F2A4C0A2107F823710EED2E6929074AF607C4846657A09BDB4824E4F2D4B4FA3C43576D4B8257B98451BB5B15BA08D38CC9A6A5FCF627B13A29745C6A5B3E88FBE4AF87A69CC6F5B8A3C4CD91931F5CEC6E835DF03CA512FBF1AF7D2BC38C49CE0975EC93F71A8C85CABC84E9F6A6E9070FC7091A32DBFB569F4771EC5170E3434757476F501ED2EA3D6583A8664E595B0FB610C8C0D0C8891319921E18951A83456B3CC14004FF5F6F50747253AFB22A9554C1E4F40ABAA2F2CAEECE97DB0D9AC542A05499C9999093CDBC0C0203E3E1E84342018EC27B0C337E018184B565616D071C031EC2C0E5BD1028D01A90F5720818E8E0EA4871C00F7B24FD6C890A8369F1E7C2B26D62F28D0BB929C5C4C29A437B6F1C48360D31C1E02E30F5F2AE50F8F4B070625958C6678AAA39586C5670484C7B269D12C9FFDBD14CCFD4FDF785B8B50E9A889FDE5394349089E3782618BF0398840591DA86CACA968E0F85A5FF4D5985DE286E0FA22C65088F8BBB376AD3B121C2A1BC6CF740C0AFA59B9A8F6AA4231AB948D75E43556760947ABAAAADB3ABA4627D11EF1A0E404EAEE1CA3CD883BBFBE66B0635790CEB930ABE3C12647C38D377BA9FEE878F9B3BBC7969A2BEDF7D3530EB7BA11E9A49FE87719E3F2B6E18197EF6C508E77792E293EC2EFE7E664F20B4992C64661258D9B9B0D3C64A09226696E10D24AC58D0DA3A281E17EB6884E1DECED18934A87BBBB45953421BD6CA0B1969B43E84D44F761132575A07752D8B8B8718190D65EA7116D1890EDDBD8C9C097A57962DDDC706EE9659903A281BACE1A2394E16FBE875739ADFBC569ED2EAFDD3BEDB6DC0CBCC9ECAE492767A496656657E57AA67A67576487E0233523F54A9B4BA1B95B0B89D83B3793B5AF135C8C0337FD628340A47EBFA22FC4B105ED4B0B70690E71697434C9BC78B44CFD62C2378B0311B3FCE6CC895B303FF2BFEF862F5F5A66AA97AE7E1577F57C1B1EE641825419ABAA61E173CBBA7A38C0C569F40623CBE084D47C10E6F27E2D2CA625A4906A6A1B8905B4C0B02456539B9C9CF6B239419129811129C0CB01DCC191A999B925727F84DC100E380670C33ECBE0DF01E3B7ADAD2D4019F00D10040750C3FD034E4060033901160EA4059201B7B97DFB36C875B8594426F77138C3FCCE4A97D3A46BEB63B00959D9E995344A4965954002FC41CC13F70AC16124191F1549FB078583A3F7E08343AFADAB2A67D43269B8DC547A2E96E577BEC8EBB46C648E8E9796769D3A702EF9D6AC314B44B33A22F63C62DBBB883BDB77B4D4763339F7F42E5FBCF3DDAC142544F51D04DF1441D544286D5B69609CF94CF886C4DC065A378326E86CEE461BB7045E1309B86012E6F378C0BB26EB3FD241A952AC05426FEDB7F6674136CF37DAFB83C3E5EF9C2E6DF1535BED7A6981EE2E84FA46C49D4D4B6CCEECF4BEB3C15565A3BBFAB7B6975FB9B303A1BEE12CDA013C474F2ED8938C86224635372F6BA847665BE5930BD9A929DC349CB88E21AEAD1233AB01D3C03D06BBDBF9857992B6074B740FF5758B6AA92266153727175C9EBD2971D2B61660907DA8E8F12E6E7A6D8E164A2F181FE49F1E10961D8621A1BD33BDF2E8D914668929DAFC46D8CDED1E3B5ED17B03A13DEB0D8337B7B96ED589D6ACA9AB28A8CA754972CEA1E6D92679A414A7D29854F34487B8F254119FD7535152E4654BF171AC8E0D8A3DB6CF6DFE1B394776D6D868E55E385B6A6ED210E9D38AF4A9F6B3A90B75C8397702BD7871C8DCB9B089096C0711F0E5378536772BDDEDD24F1C2A71B31F160B06C7C7A2E2F0960E48565327D4B7ABA73F3E8534610A04F29A989ADFC711C02D60DBD60E117905E5203BE58DDBD8D25552CE040D09D84D6E41792EA96CF03EBF0743B81CAC60240158836609E2D9D4D4147616078309584BE02F7074803BC872180CB0E73730164B4B4B180C300080C6686A6A565555E2D2D26A18CC31516395E3E7E506FBC985E4CABAFA82DAB60E2E94473CDC4FEEA025B5B1CAEADA188D2C7A27A35CC2064B8598271215D35A694DCD95F515B8D08036AC45A1F7CE414113B0322ABD43F3F209FCE5973A5510890710D796227621107BE6BD1961E2EDE716AABC64B9D26C84D61708EF5D880A6504FD1242EFE8525B87B867833887D51F67DC1267DA16A6DA7EFD2DCE8DF784611A230D850F67221D1E3C1A79F70DC39DBFF9EB7D66716C81F1EE0321FA9F5A1C5B62A3B4C95B65BEC11E840A48F73D9B7DD5B7FAA87D72F7F02FCE373638DF5EE77AFB0B2BA59B71CE4F75E43D09E2BC0222B82A41ED1A1B1EE217117BD031C29262098B390044A5A97EB0B35DC2AAE781092526B23F3B53DAD52E01C5A8852569AE1BA0D3C07AC8494D018BCA60AF8C9BF293B1E39DDC186A9C79BC29A620DE0AE51098169445CD8A2D4295300B0BAA881E197EAE59AEFB3CF6BCA403AD8B784B7FBE72A0721821A8B599C515B2FD52BCB32919510531D9E5199CFE4EFFEC50FFFCC8F6FA1A0E298716EB579F8C6CF0F348DBBE25F1E715F9374FC79FDA1FBB7727C3CFA92B0B430D7567C606D5A203EB637C4A35AEA2DF5C8842BC14F1D2AB44D5EBB539090C7468C18D8B2433035E0B7095F1122A230D5FDCDED1DBDDCB01212D1DFAC3D6094E6E7C0E855C52D3C7E657D11B9DDCD155D5ACC9861D763FAFAEB14520147675F7E13249A5150CA964B0BCBC0C9CF300F1F3E7CF835A09108F8E8E06B883C6096C04D44D38FFEDB7DFB66DDB06021BAC8AB0ED37FC058803EE01EE0101016A6AAAF0381A1D5F4A218EB563E9D69F32939C80B895377514B70227191BE495B0F22DCB300619A9F63E58F368AC0329DEB13EDF492A90F9836B3ABB0B18ED7426232EC8BA31D7831E7291996A0BDF175229EBE6F17DF9C75E6A3B86B05886383C1BE18D986D8B98ABF5D932FD2FBFB19CF71F5DC4CB475E46A87D89C8DD81201F41E81CFED6DD3FE919203E3A3C9CE9C9B1DBDE1278A63DECAAC866D398CE6752DB4DD262CCF830D8C7FF2477C52383BF8519BE6772E07A94DD460F95A3A1FAAAF18E0B8CF6809B7377A0E61290EB3A3B16DB289F43596DF0BD394767D3566FF53BF15E1723EDF7F86A6A26BA3D17C40585A4661BEB5E4C1CBF8CC2CB27B05393C5F575525603E88E038C6A7E199947CA6BF7F468D4D36BB1B366A72671F309FC42A2A4B15ECCAC05DDB43F23037210312B3AD1915C2472B8ABDB9DE4E784B5AB68A8286352530BB0C8ACE8983C74635733B45D3BAF0B551873D1EFE20F76DFBF73F7ED2D0EEB7423F4F1944C883B84BBB9B49C38222AAB222D363B9AD64C8B2A88F5CD096D2A27B3027DB1A6EA4457AB8C7DFB435F9D97BD6B4381C1AD3CF5EB24DD9B7986D7ABFD9DEA71B1CDD5252C46794F5D711DD2336DC35AD833331C81A069DD64E4604A51414463AD2273D35E5A154728EEBC6FDBE6F2048E5EE19AA64EED5D935D83F71A58ED2E9EE894345240486A6A3A79B21D069E4A4A23983B0650A8002A36129D99882B100AC5949212B07F83FD04A00C6C1B88B58787076899729D129C9A7BF7EE5552523A77EEDC8E1D3B0C0D0DC14A08CC1B5838F013A02E302ADCDC5C333232D33308C4DC3821C198A4BCA2BA38A79844A0B0BA1BFA87C786BA5B73AD73C2CE86465C0EC1A8C461F523534C42625592027737E53ADD1BEC69177692993DD5D5CCF40487A244AF8E48AB4A776580383693B973E5EEDC4D88BEF508F3B7107AB310D457DEEDFECFB694F95FE7BEFE75D35B1B735EFBC0108130988B285C86C85B8F38BBF63B5B77D9AE46530E6077E09C9A601DB0478CDCCF20E96D6B0D316263EC863B2AC74724E3BDF5E33589E31DE4711E7B9C37003BC24C36418A87C1F5633C4F678B6E829B0B1EE94BC05C8DB679D368D762C7D3FB03EE6C70BFF1AD83F246B71B27420D57B95F9EA7BF65BBAF8A6D66F08960E33D1E1A6A18677084FF75A222A828AF3C7A88FADBDEAE68243B25194C25A2CA4A31A37698C31EEAE9E61793FA0919EDDE9E75376E365B597423C37B13E284959543BD3D82728A805C0086458843E4E565976CFEA5DBD858D0D26C9E6EEF93E6C6E1C99C2939B45CB3082B1F6C604B5F477B7F8763929335DA3AB314EF4BF0BE18AE1C56101C474AAA68AEE00D70874786338BD3033342D2A959E8EC84AAC6DA9882588F4C7F460991EA6097A179B3585F0FB9E45B770402BBEA877C13B52A5F9B1AB5EBA19F7EE2B166750F2987D3DFD12E66F7D454556131E5618EE11FBE8F42CC0A7BEB2DF4CE9D95CEB6B9663A596686DDB44A32AD213625BF97CDA5D26A7EDA70EACB9F0F53E9B2D56D261FA06562D3491555F50FB5E998B943C08F5BCF85A1D200FA604CAC63B542C7579457F8F9FA0170452211F87140EF84B82B4036E018983728A0BFFEFA2B401F04F6A953A7949595C10704B77C7C7CC0F002E720EC3B3B3B399CFE9C7C22212B894FF06BB8BD95510F05A454B1FA38E231716D3223E22C0EA36297A09E4A7112B0F3682C2C8AEC9886395F1174965F1B2F18E654D4B53536D45795E7E7A42548F3DD2469BA80C11A26EFC43E55F3C5AFA1D6BEA1FDFE5CADB973D1EF7CC75CA832F6C5B9F12F55F8FFD5CD5AB8CAE8B5795A0BDE88F9FEF5D01F5E535EB9312AF28F25AC00C78352297CC49A5B5AA05E4D8D8DF06F7B47475E6E6E57571754BC383B2D1585ECEE63C3BB868647A5C003E49E9181C1F17E2E6865400D26DA503C24BD88B646DC5A7D21D4CC352D2CAD2C4F35C6FE2DC3DD3F3B5F3CE8ABB9D6E9F2B7D6A7D6385CD8EDADBED2E9FC3B063B767B5DF7C90EBB1E6DBDD7FB8E1AFAF920DE4F207447860F76B583E51B1CF2F003FA3150533D0AD3E31BEA1B8D0D5A1DEDC1522E04A32185DC19E0473F79A23F37636C7C080C88027221373757402996363709AAE9C325C5C29E0E2B9C4340A657493D259F498C27C5A4576069ADD4CAC6AAE0CC50E308E392BA22E9A0045D116F80358EA5C407E223C37291A8EC989AE61A5C19D617E7954A4A120A659EB68442B433CEBB8A9453E5689FAFAF41B5B7C16DDA1A306B56C85BF3B3B42EB30891ED9686B96BD6052CFBB6D2C9B68E90D25E4F6367136961A182AEB2FC5FD7C35E27E00F0AFFFC2BA68345737C48556C78574D755B27A7ADA3BF97DDEFE815F2D6679B7EDD75A9A95566369938408394879B4E9C4CBE6BE71AFEED3A2557DFD89E5E4E4A1A31239B021698341C0E8CDC3D3D3D20B6010A20CEC156082642A02820C5E164EBD6ADBABABA40D3419C83031F9C41E00372717101B20E8F00CAC1280E9EFFC0E0E03C52FE783793EF78AC5FDCD0C5E333985D0360C14CB761461C4C48334111FD50F93E0EE92E6E59819979A1D5851E4521FB9AD3F5254392CAC686B68E2A8160B0825AD38CD6EEF6BF08C5061B8C969AEB7B73567DB1F4F4A75F1FFCE8933DEFBFB7FBD8DC3D850B8ED1DE3FA43D6FCDF24F7EFEE4E79D5FEC38F7E3E90BCBD76D3E7D48AB9129D343E40748EBF636886168077FD6CD9B371D1D1CA076A0391C3F750A5CB8FD6CB691A181BEB1715E01B181D55058549C9E9141AFADE6F2B8E352C928BB5FD2D727E4B1614A803C37C990F432CA729EF6966301069782CCF3AA8AECD303FF6BBC67B9ED99EDEEB77EB6555E61756AABDB8D336146EB5C2FBF6FB0E34C8876485EC49570934B11A606C9DECF25C501A39CCC34984939C4E9E2E6A6F761503229CEAC1E66F70C306AFA92E2FAB332F8258532A70FB9889D18D719EAC723E70DB1FBC0EA22A497F6A527F288B9A09E8E4AA443D4F2315E7F404158707EC0DD38B37DB6FB6CE32D420961D1F998BC9AFC40BC2F96920AB5150D8AC28BC2AE855D734C70F0CBF4754EB30D210492598551A430FB04AB8C8A547215B9A6B5D613EFED9D1BDA505244B7B72318A8B7E0E35A02DC92BF58E43F6B56E2FEAD6D19486E7C3055ED0AF5D8FECAF3C70AB4AFE0B4AEE3F61FCA3C7EBC2E2E88BC665D280211FFCDD7E9A78F939CCD0A1C4D287E6EDD8CDAA68E3E564B6F5737DBCE2360C1E71B7EDA72964A674E742A18E552F1C5CE3ED1964E614E9E91C1E1E9B5CC96092E3E363A62E312B46AC745BFB0C4D191610E87D7D6D60B8301E2A840A1040F25D01550284160DFA7D76A6023078883500767D0BA75EB7EFAE9A793274F82E4663299107B08A007F4839E0A3C1E1CFEB44A7A21A51C9FE02B083F5561BCB68BDDD25AD3C5ACAE950E8CB511DC09961FF5E4598AAA536FDB9E9EB76FC117D7BEC7E6D888F0C6649D452DB81BEC1149768DB0BEA155DCDFD6DE56D91EA7D5EDA70C950281EAE79332EFD53D88376EBEB4C8F1A54FAFCFFAE8E7395FFEF7FBF79557BEABF5F64FDFBCBC6DD6ACE5EFBD7454EFE51B312F2FBAAC76D975F27886756E406D00975542622254303020200A890C85714824C6C4C68283162C4839B9B91EBEDE8E4E8E485F7F6F67E718746C1F043948B80179015E04F7FACE3FDA563C0C10377F4D73BD52A8E161CFDB581A1E5B95FD8DF9B1C516C70F05681DF4BBB3DDE3D67E2F8D73C1066B1C2F7C6676C83CD533819C72D0E3F6F97013C3246FF8C8FF75A222AAACE84DC2F4C56384B4327E31919396C22B228AAA69E27AC66067F300A3121C3DA312C9186CCBCEE74BEAEB04656431AB0E202EA0957072D3BAD1E110CD02A6431E85C48E448EF7F112CA937DF19E71A45874613C81860F4AF3B6C73814D752B045295A218689A5389E981D5D1CAE1676DB1BE7A31F79E7B4CF49CD70EDE8FC28F764D7D08C605C45E25E8BFD37FDAF5BC799A6D6E07B1BEAAADD9CF20D6F37A6A1EBFC5D308BBFF4065FFD7F3F29BC7CB1DCD620FDF4C1ACCDABF19B5726AF5B11B96C51C07FE6FBCE7F2761CB26FC8AE5010BDE60FA5837152497857A13F5D4A9DECE106240A96E76F24CCCC8A244C7277DB572F7572B0F26E11E7C9AC19C929D57744D5D434D5BC5C5F5B68EE1AD5B9A2AC6960E72EB381C10CDA2A263BFF497C3A1D1F160F4C5661442E4165C07012C775282F0060D12300DC66FB90F1F200E07A89220B3C14C1E1919097C06AE009301A30A5014780ACE81BB97502884BC02766B2D17AD42D4FCB2A8342D2CAB22ABA1BE833736D44A215B2ECE3A399FA7FD83A3FA978883881FD5DEA5187D577F602E4973B9B839B357CA253119B43A1A1E872625A1F8C99602AC014732DED6DA26E04B8DEE46BFF2FA0EC4ACC388399B105FBD8FB88A98B5E3D397BEDEF1D2BEE5B3CE7C3A7BCB1AC4C62B8865970E1F3065D574F1046C2057E0AC857AC1D806884318703E91081C2C263A1A1D1B4B2612FBD86C4C7CBC87BB3BD8865A5A5B4DADAC55AEDF0A72F3F076757273776DAC6FEC92F6FE167BEA4B8F25B8AAAC095C82EBE70ADAF2358D5F4E846A1DF5B8159C8BECED6FBA14667ACA4FD73CD9F36694E5B1405D35949D0DCEEF8097C6360F157C754E4A59FA117FEDB361863A71AECF25C507DB9A3919299D6101C046C085D94FCC02EF0F087211A352DC0C8CBC57DAD42C01EDB3BD15F02D663200DC40D3855565328867E1FAF3327805845E4C148F42EC0E0F1CEFE92FA82F328BB72A6416C2B7BE9FD787CE8BB0415B15D614B5B1DB40A207A78757B4520DB1265743AE37F43594D693134B934A9A287915B9EEC93EF18589CD3DF5706295606B9B685ED35D3B0C6E9AC8E0541565BCA9669ECA65DF056F7A2010098879C8856F876EFAA5DAD6B8DED79962A24FD3B95DA5AD9ABE697DE89C3792162FA66C5A877CF72D82DE354A4A1835C0335FFB162DCC6F7440D8D8C949C9A034B5F492CBCAD7EC3AF9D6A22D771D82E4DD0010C765E72A5D397EEAF22917976B9755CE1C3D775055F7766DBD0CC770D437B66F39A8FADE375BC2A25110B0158F2352A80CB80ED1570062F888037CDBDBDB41E001F780582B1313133096432021E894E0B984BB6040847310E1700BECE210BB021E50202D603D846459D904D0CF06EB339BBC5753739DA28A0BB39A48D4D69671E908BF2284E9B79A19BCC31DA3FE2B4AE316DEAC38F2769DF7A1366A08108ABE8E5A5A7542262DD3D6D9A11C856C74BBDE146F416FE678FA7AF1256CF1E0B88549C8C2B77E45207E40BCBE13F1EB12C4D27D88EFAC661D76429CD543AC3E8A98BFEED07EADB6FAAEE19181C0205F7F7FD94ED332888F8C34B258F0D98101CC6A6808080C8C4422292412680E302CBD3D3D0B4924665D5D540CE6FCA51B4A4AA7AF5CBA686365DDDFC3699774AF8F3A38D7FEE382C692C910BF116BF5C6ED35BB5CAF6AA36DF451B6226127B1B6E0B88FD6C9001D9528EB2B61A677A26C2F041AEE765589206278BC36AFECB043DE1A7B3C54D462ED9F0BE2232221979CD79B14CBCDCA149497B033B19CCC74411179A0BA5254592E61D60D36B780639F579427A4550CB5B54B3B3AC5B53520EF81BD40902DAF3C9F939DC2CD4B87BF9CCCE47181A88BDF6B88318F2E4489C4A20A4679644E6800C1AFA0A6086A3B3824CE2ECF348831596DBB7EA7C3CEB4321C8FCF93B7029541754BF64516C48A07C5F0514AA9C05B27D9B6DCF76EB66565A4E9ABA7996B1075D5A2567CE73F775E0C62166C50E93BFF4DB2AE6A475961675D0DBBA1B2111B9BB27963DCABAF87BFB730E1EBCF225E9913BDE6C7227F07062684EC6649CF481E94488646C6DABBD86D103ACBA8BBA86E34F7BFEB36EEB9C66A92BD65402CF608422A5D5552BA72DADEF1C265D53387CF1E53BE7E2D2D3B5FEEE2F147262FF862D7CA6DA7715984C6E68EF2AABA1EB62C1E1DA000D20E80CBE170E05F80385094B4B434300B82E7120C26602687601510DE8066E033701DEC2D302A40DE03B101E7289FCFCFCBCB05D232327A6FB08F5965F323C9FE4876466A555D6A198BD4CF8520ECA1B14112BE29CE9146F06B6D0D6731522A09EDDC529888D02F1A6AAB2075B20A63F30BECDDDD07CA4258DECABD8C125A2D4BE9FC993B06AAD43252536DA35760CC2FBF1E45C02700B11281388478E726E22335C40797BE5A7FF1CE6D0B527A61359D6E78577BFFFE9D1EEE1EC3BF431C88380C424C5C1C7C9D808BA7A6A45453A92D6D6DA9586C2E81101814949218DF5C5BEBEDE3BB6BFF81534A67E2E313EE8D8C35095B37230FAD70DFD2CEF963D60548711594D5823BEBB6395EB04A715B677B16559C3032C4F7CB8DBE106A72D85B73ABE3D5B301FAE7FDF56D937D85228851A9B811667229D8F027AB93B7626D9FCBBB298B92AB2CED4D8CED46860CD4D2F925A4FE7C3C5851F8F9F9C262D0324B41F5140211A79006EA1883ADED608101F3392F3B939B95054382574CE015E7F14B0B3A42BD84D46208E1836E0EC80AB0C0DC4DAB48F74F0B70497680705922BDB0A5A7A5BEADAEB2BDC221C5C130DED801E7E097EA57CA2C1B1A1D663454A3B2A2FC3382EC12DDE9ADD5D211914F86BF77A62F5B280B1CEFA9AAA98A8F6D2C4861656228B6E639E74F477FF1790C02118B981DF0E947382DCD3E7633779C93735707F9FA1B710844E87FE6A377AD8BF97E59EEA963FD25199C067275665C0B9D2677E23434B5E3F34A20163C3E3573C9EA430B3EDA74C7D0191C96A06546627001E161FE211E9636B7DC7DED42A2423DFC424A4A2BC746C70A8A2B7FD9710DF1D62A6D3387A6B6F66A4633A3FE81230CF284EF3860176C26007460D8005F70F100CAE5012AC052E080C071B865656505221C582C0875403F9019403FA89B586C4A4F4F2F44C848D82D656E27F3B437A704A4C1DC207A1799CE204B05B2D91834764F7E734B9B70BCAEB58FD6481F18958A8687CA18552C5A455B4D6F747C159190CC25DC6C08578122353636191818EEDAB9434DE352723A922F151614A4DDB9EAB261DDE50F3FDEF1EEC28DDF7F79F8C43E831434BEB7A711620BEE6868EFDAB5EDDAF58B09F149120938286593E1068442285B4A6AAA9BA7A783BD3D93C110F221F252D4D1DE0E73AF7273F37049B135C549651911481FF758544C4FAF6C9E61515BD9BAC05DB7E3F5A493E64C01175789B5FAAFF6A60DF6A7B5622D575B2BED76B95AD55C3E286117320A2D133C6E859AB9A507E5D0B2B99C964E769376B4ED217795EB11269F1BEDD140DB3F977753265C3BDAFB5230E07E1FA8AA94B637734BB2FB89191C5C2A84D1C21C081E314F505E0A912A8292127E01915B900B649D9D10C7C36741542D8C0A696B339790D913172DED0477B74CE0D19B2BACD02666496676A9F6E66853FF74FFD412AC5FBA8F15CA3AAF36AFB1A79E2DE8EBE4F7E02BB3C372234B5B2BE272507E89EE9822B40DC625A782905D85D509BB4D6214C805BC88C3EBAAA6F7D5505A8A3399F9C94DF838ECB1C33E08041A31CB0BCC26B35FABF2B2EDA167C5AFFA311981C02010053F6F6CCA8E22F959D6FABA08A905BCD6AA667A09B7F7C1A236E0F4A9A8628263922F14E85B397DBC7CC7DB9F6F33B509ECE8620F83C3024CE6FDFD4EEE11E5B407D361C0E950585CB5FBF09D596FAEFE61D3E1E48CF4D1F11190DF2D6D9D13412F62B11858073874C034AEAAAA0AC0051083D8868BC0B301DFD7AE5DBB75EB16E01B080C0C06A02540C401F1A089C25F90E5202CE59505C12C6DCF6BC7A8A621B19E61B5446A398D1ADF4EAD167688C60720CA004C3D40AAC4E3836CB1445CD3040150C57466737C42393E99246D4C2459FC5C156D0F4938FD1C5A198D9096EDE4ED7C5DEFAA4F807B0393DE52D3999D576068E9A679C52BD63BBD99D1D6DBD31B1317755DEDA6B5B57D7E6E1EB5A2ACB2B26A029AD09B10770075F1F2F5057F16F7A1094A1D1D2C14DA3A2558879515D9CB281E19E0415C8A27297C83D7FEFC863F2DA12F191E5447592FD2DBB1C478EF013F552D94F56EFB4B46D1564D9D35E3128E90D3C2E96A18E86B1997F4737A1B1DE2DDB7DA9E570AB8B3C5E9C27B5A9B74E29D262C3313CC67CAC9D367FD4074A1A0BA84939EC22364437C158F520031B4DC2C3C0787E5E66489A865424A31BF205F44831912697D71186159A98052024F0DF3D8D2A686AE88007E05053C04132F46E5476B456B5AC55B99A3CC3DB3BC22F2C37DD3BD3DD37DEC139CDD535CBCB06E8EC90E6A61AA5702AE87152033291945350591D99199347C756B8D39CAD40663DDCDFD63EA715733AB28D29FE87C971AE347450726282BB9BFFE76C4C2F7C23EFE2878F6CBC88F3E2955BD11B76655D8EBF3425F7E39F3B723B442343D3FB6DCC329C7C4841A1BD3CEAA83269ED2288343C3E4B2CAF36AFA1F7FBBE3836FF65C51B3CD2596B3FB0510A047AF6EEDECEE870810C0717054F2C6BDD7E6BDBFE1AB957BCD9DDC5ADADB1F19520D7C433E2313E20D81BFCAA7FC004501ED0DAC87A080C2B71E4EC09C02CC1B0603D8C5C16F2F9F1801521FE6B94D146F6C80D3976DD988BE969A44F3734F6D66E4318AC1DC52D5C0EEE00C0CF079433CA9A843DA5352C9A217D575B57644122AAC23623A6A237B53CF93DD8E48D8320D61E28079D076760E6B7E5DB77BCF1E3D43ED70644872723C2E2D09979EECE9E97EE6CC99CD9B376BEB68C38C69F923F2B9CC7F146664A4B8B0105838689613435A7E1718DA00840F34D05D904E7E7657C85136FCB6BA5A7EAB52D4CD4B21AAE2FBB349260EE9B05433DAE66BFDDD8BF4777CAEBB532FCA12824FAF04EA1B46D99456E5F07B1AFB7B9B7B3A1BCA198577631C76DB5C540F37B91569FCA9EEB6773537E8C43BCF00C44745823E5C223B3DB133D0A73F0327A257B19393396938511515E6FE004DE7E6E07944024C4C065E2E2CA5F425A021A045505A2CAEAB86996FBC829C614EDF646F165BC076C038984499846585B926B8B9E15D7C73BCBC937D928B53FC53FDCC234C75C2B4EC936DC30B2212C9584A434566193E261753D7D1E84A7454763F4D65D12607F18C0C8F34E667E38DD4F026AA7598A086009F8C53CAA566266DF1C1A9EB57392110EE0BDFAD74B44A39771CB5644586963A191B48F6B7CBD7BF93636552959E36C09B3AA505E2AEEA592DCCBA568834BCA563BC6CCDDEF7BFDAB278D591E3972C482574E898AAEA863B26AE6BF75CFAE09B9DEF7FB571CD8E234E3E017406ABA3AB171CF80FCB1230BA826C06EC8278066DECE2C58B100E0ED8850071602C404B80AF03C4C1D60681E3700B9C9D308F136CE4F2F9107F9AB1313CC82D0C69745CDA901B1E1D99138B8AADA6D796B677E11B69D4AA8AAEB2EA4A5A01BE3E0B585F37BDD1DD33F6865B5C495B597FAA7289FA37925670E94F1D8330FC605CDDBD7B171C4FFB816EEFDCB97DFBF64D9B36EEDBB70F3E2330C6401978C2F43F08527ACCD2140FA2F97903FC726655636B5BBF44649069BBCD6D4F7923794A13815DFC0ECA76B1F1EE65C67B971AED5F66F0DBA5006D379CCF0187CB1B2C946E8799B925F9DAA25C0E595FDE627EDA20D6CE186DBBC1EAF8C7DA9B3FD2DDAA372310877619EAEBEB898EE941C70AAAA8805A30B04044212F3F8F9D98000A289F42866896DEE8684E7A3AF8F645D5156064149490619650776C94B88935D99575FF837BAFBA996E1865A01BA98B266182D3838D828D425383732A72120A130855B91416A5BAAD8A5E5799569459DC521A931383ABC808C80CBBE0771147C542D4CE943612F4F656E393CB901032EE46F176A27B7974A42774D3F0542BED849D9BA28E1D6CC849A98F43B605063253A31A532368E6FA456E160C321E62DF1EEE3F900A5CBE1002B040627576F7042063F628DDF8FCC7ED1F2CDD76534B372D3BC3D0CAF2E36FB7BDFBF5DAEF37ECBB72DB302115CFE670812B83AFE731532D61B9D6C1B6B63690E2E00902FA01FE79B09403E8E15FA028F290718863D1D6D68649128075E027C0DDC1FBC3E54E99463D36CCA964BAFD44BCFC595D3ECA2D056F854C2665E5D54348445D1D84ADB77476B25A5B93B1A9FA56F646CE2E45A4C8FE22DB1A8FB3CD29A140621EF729972DCBD1D505D45F6EC9814003582600C00DC57EDC23CF749D271158621D77381F4BAE481B199D6AC69649718CEDD2BBBB97DFDDFB9DD9FEAFF4777D6F72E06280967AF4DD8B613A473D6F5EF4D7D48D36BF156E702348E746A0CE561BA5C5067B161BECFE5A7F8771BCEBF372F1899A0C7776C9E6D5C7A185E525E246A6905A0AEC1C9CF98066711353545325282E012D939D920873200415A59C146C675010ACA902AEDE879B03D6BBA236514D5126D782AEB8A7B967956555B22A7085A9C199E199D41C7C293EA93081549317901C10578C4617459AC59AA887DDC695A70D0C3E628D11F84AB2FBBAC1675A9D8CA446FA95F93A665B6A97785B56FB3BD0835C6A22FDEB63230AAC2C299E2E75D989F529A8DAE8085671BE48C47B5C3FDDF75FDE03BA5252511B8F25D6D6B322E392D5F4EFDED050D135D1BEA1A97AF6C61D5B77DF74422E915C412AA6F7DD0F6EB9BF2AD863677F8145192C83801E802F905730B7819C06590E1129E02084BF204A81A983E4865B70005F07E43D6AE52009BF0E5BEB75A8CE756D51C8AD3C4274616936C42A4684C5262666C6617051C8F8A080D814422E931A5E6DF733D5647D1F93343C691527500FC0600F7160408DA048700254443E38C1F2035807D10E4E2BD01AE10A04BE02DC211968CC70C8FDF3D3C7F7207CC1EA6BFCD2C2ED139DF26AF240603FFC2C5CD440D97C63B473A9F1AE6F8DF62C35D8F38DDECE15C67B76382A5FF4BB7DD94FE38CCFAD2B219A1A512617FC6EAF313EB8486BDB12A3BD00F12F75B71942A4E1F304D34E29CD5057173F3FAF27369A8D4B11904983B03849472B045D0DF574098A0AC04F046EFC5E4C0C3B25BE37269A9D9038000B99FE1E6CFDC84661B6331CB18E37426EB9E33DD2E9B8586254303630B930213C23D41FE783224783D5DC3052DF00A9E792EC985B4B8015B31ED7B82360FCE1729B68A535F814665204D1D934C3E816C946B72ADCA51587CC33D5CED651A386FBD666E368F8B4DEA6469859F8D47E02C6525EC9A88510D5BAB6A8F86C2ABD865858E81B1081CFC9CD2B2C8988CD84396F107858CB6C06B7FF743A1E500E10018320484AA0E670024C00883818C2C18D02BC451E8D089672801400F1F1251C93F45677269A3669FED8E1B28D8631C08538E447BBD370810568D7E2446F4A4A3829409F1573AD27CDAC93183779311DF92C24781DE803F025814801F8B6C0BF4096E07540A8608C41A001DC85BF4042C0B40FF4098610A481C4F0E57926D1DED9D39D5756945D51D0D4FD8795694ABD6002876A84C5E73ADBBED6DBB9446FE752BD9DDF0029D7D9FC95EEB61F4D0FFC6A7164A5D9C195770F6CB43CFE8BD9E145DA5B3FD3DEBAD468CF177ADB3FD6DAA819633B931097A9112211086676469A2C769C90CDCDCF85196EA24A2A273DB53F2B5DB61A162C8B85C5825551D2F8D82A4DAE6117B71B591065166F6E823202516D853635431BEA456A9A614CCDD1660651BA77A38DC3B2C21B3AA735E544241474373776332B9B8A72A8F1483AD287810E68CAC29404BBD3D1211DE524582AA8A7BD6D8A6EF4382441320800876E2EA53290E82CA02E22D1102E93D2C716F6F4727D8352E9B5CD103B0E013B80DDE9401C5E04C2120C112017C1100E2E21F9B22A40C4E15C7E80640521FAD4E1070986612D98D4E0FE38937A8F4B5D01D73AC32EB521CFB7069E66A3AE73D2B4E93EA758498EF746A78E130028F89800C730B400DC30CC00F1E0906A6969819A02D0C1060F4582CF08C01DAE805C87BBC09AA0A8F269D40FAF08F084D272F9BC5EF653D672191A198E26630D312EA6F1EE66091E6609EEA649B21F9010DD38275D8C23FCF4D08E06B18E46F1AE46702BD1C32CC9C328C1550FE388A6A43F610D0579C19E6E5179B802431084412E8265B1B8841C6E5E2ECC61E31515808007640BC8C503358C69F6F7EF39DFA33652C332435D929DEDD2EC0D138CEF2699BA66BA7BA7FB441391E45AD2F0C8332CE501120B1680E0F57384EC5E412BAB9759D905EBE9B435F160EA0647B66EE074D033250D4C4BE9EEE10804228160A0AC92C1E6F0787C21A594C1E9FF2BAB2F40E6D02BA047027A64937069B48E8E0EB0AE00B88199C817F29C7E218134B04969E20CE78E389346B45127DAA42BC9825FEC31DA419045B13E74C8D72E049E0DBAC1842A096317A03FF15E38872814F9A3C09460C8C1314199266E4DBF904F4B790FD6D5E1C05221D06F227EFF80ECC719E073C14474FFC715F379E2FB17E1AF58765D762EE271445CF1A368F094D7FD15883FADC48AFB8A16F807B58002E2FFA0CE5014E5EF680105C4FF8E5655E4F90F6A0105C4FF419DA128CADFD102FF0F4A11F3CD0FAAC9DE0000000049454E44AE426082,'12341234',NULL,NULL,'lkjlkjlkj',213213,'lkjlkjlk',2134234,21,987,'iitop','Govt.','sdfgh','klkjklj','lkjlkj','lkjlkj',NULL,1,'2012-12-13');
COMMIT;

#
# Data for the `department_master` table  (LIMIT 0,500)
#

INSERT INTO `department_master` (`dept_code`, `dept_name`, `org_code`) VALUES 
  (1,'SDF',1),
  (2,'COMP',2),
  (3,'NNN',NULL),
  (5,'JKKKK',NULL),
  (6,'COMPW',NULL),
  (7,'QWE',1),
  (8,'ASDFG',7),
  (9,'MBA',7),
  (10,'MSC',7);
COMMIT;

#
# Data for the `designation_master` table  (LIMIT 0,500)
#

INSERT INTO `designation_master` (`desig_code`, `desig_name`, `d_org_id`) VALUES 
  (1,'PROFESSOR',1),
  (2,'ASSISTANT',2),
  (3,'JJJJJJ',2),
  (4,'JALOP',2),
  (5,'JALOPQ',2),
  (6,'WER',1),
  (7,'JKLOP',7),
  (8,'PROFESSOR',7),
  (9,'LECTURER',7);
COMMIT;

#
# Data for the `salary_grade_master` table  (LIMIT 0,500)
#

INSERT INTO `salary_grade_master` (`grd_code`, `grd_name`, `grd_max`, `grd_min`, `grd_gp`, `grd_org_id`) VALUES 
  (1,'S1',12345,1234,2134,1),
  (2,'STAN!',21345,2134,3214,2),
  (3,'S2',123,12,500,1),
  (4,'S3',1000,100,500,1),
  (5,'S1',7890,6789,12345,7),
  (6,'S2',8900,7890,23456,7),
  (7,'S2',8900,7890,23456,7),
  (8,'S3',9890,8900,32145,7);
COMMIT;

#
# Data for the `employee_master` table  (LIMIT 0,500)
#

INSERT INTO `employee_master` (`emp_code`, `emp_name`, `emp_dept_code`, `emp_desig_code`, `emp_type_code`, `emp_phone`, `emp_email`, `emp_dob`, `emp_doj`, `emp_id`, `emp_salary_grade`, `emp_bank_accno`, `emp_pf_accno`, `emp_pan_no`, `emp_gender`, `emp_org_code`, `emp_father`, `emp_basic`, `emp_title`, `emp_exp`, `emp_qual`, `emp_yop`, `emp_prev_emp`, `emp_address`, `emp_active`, `bank_ifsc_code`, `emp_bank_status`, `dor`, `emp_leaving`, `emp_noti_day`, `citizen`) VALUES 
  ('0191','Vikram',2,1,1,'0123456789','ab@gma.com','1973-09-05','2010-09-30',4,1,'000','123','123ab',1,1,'',10000,'Mr.',0,'',0,'','',1,'1234',1,NULL,NULL,NULL,NULL),
  ('098','SHHHH',2,1,1,'0987654321','ab@ab.com','1968-09-25','2011-09-28',5,1,'89','cv16','78',0,1,'',20000,'Mr.',0,'',0,'','',1,'1234',1,NULL,NULL,NULL,NULL),
  ('110','DHRY',1,1,1,'0123456789','ab@ab.con','1948-09-30','2010-09-30',3,1,'11','bn78','567',1,1,'',10000,'Prof.',0,'',0,'0','',1,'1234',1,'','',0,2),
  ('1234','TARJAN',1,1,1,'0987654321','sumit@smvdu.ac.in','1959-11-09','2012-09-14',1,1,'12345','12345','1234',1,1,'Tarjank',1234,'Prof.',12,'Phd',2101,'2101','asdasd',1,'1234',1,'','',0,NULL),
  ('231434','DHRUV',9,8,7,'0987654321','asdtef@as.co.in','1954-02-16','2013-02-04',11,8,'90878','8931','809809233',1,7,'jlkjlkj',24592,'Mr.',0,'',0,'','',1,'123ert',1,NULL,NULL,NULL,NULL),
  ('231455','AKASH',9,8,7,'0987654321','asetef@as.co.in','1954-02-16','2013-02-04',13,5,'90800','8956','809809276',1,7,'jlkjlkj',24592,'Prof.',0,'',0,'0','',1,'123ert',1,'','',0,0),
  ('231456','ARVIND',9,8,7,'0987654321','asdf@as.co.in','1954-02-16','2013-02-04',7,6,'90876','8900','809809808',1,7,'jlkjlkj',23000,'Mr.',0,'',0,'','',1,'123ert',1,NULL,NULL,NULL,NULL),
  ('231478','MAMTA',9,8,7,'0987654321','asdeef@as.co.in','1954-02-16','2013-02-04',10,8,'90821','8932','809809221',1,7,'jlkjlkj',24592,'Mr.',0,'',0,'','',1,'123ert',1,NULL,NULL,NULL,NULL),
  ('231488','AKASH',9,8,7,'0987654321','asetef@as.co.in','1954-02-16','2013-02-04',12,8,'90800','8956','809809276',1,7,'jlkjlkj',24592,'Mr.',0,'',0,'','',1,'123ert',1,NULL,NULL,NULL,NULL),
  ('231490','GOVIND',9,8,7,'0987654321','asdwef@as.co.in','1954-02-16','2013-02-04',8,8,'90874','8909','809809809',1,7,'jlkjlkj',25000,'Mr.',0,'',0,'','',1,'123ert',1,NULL,NULL,NULL,NULL),
  ('231499','SAKTI',9,8,7,'0987654321','asdaef@as.co.in','1954-02-16','2013-02-04',9,6,'90889','8901','809809234',1,7,'jlkjlkj',24592,'Mr.',0,'',0,'','',1,'123ert',1,NULL,NULL,NULL,NULL),
  ('4321','NATSAN',2,2,2,'0987654321','sumit@stam.co.in','1949-09-13','2012-09-04',2,2,'0','21345','1234',1,2,'TarjanK',3214,'Prof.',2,'',0,'0','asdasd',1,'1234321',1,'','',0,NULL);
COMMIT;

#
# Data for the `session_master` table  (LIMIT 0,500)
#

INSERT INTO `session_master` (`ss_id`, `ss_name`, `ss_start_from`, `ss_end_to`, `ss_current`, `ss_org_id`) VALUES 
  (1,'2010-11','2010-04-01','2011-03-31',0,1),
  (2,'2011-12','2011-04-01','2012-03-31',1,1),
  (3,'2012-13','2012-04-02','2013-03-15',0,1),
  (4,'2013-2014','2013-04-01','2014-03-31',0,2);
COMMIT;

#
# Data for the `annual_pf_master` table  (LIMIT 0,500)
#

INSERT INTO `annual_pf_master` (`apf_id`, `apf_emp_id`, `apf_op_bal`, `apf_interest`, `apf_closing_bal`, `apf_sess_id`, `apf_recovery`, `apf_emp_contribution`, `apf_empl_contribution`, `apf_withdrawal`, `apf_interest_opbal`, `apf_cum_amount_empl`, `apf_cum_amount_emp`, `apf_interest_deposite`, `apf_int_opbal_emp`, `apf_int_opbal_empr`, `apf_int_dep_emp`, `apf_int_dep_empr`, `apf_org_id`) VALUES 
  (1,11,1234,0.000,0.000,1,0,40410.000,40410.000,3214,0,202050,115272,0,0,0,NULL,NULL,7),
  (2,13,0,0.000,0.000,1,0,26305.000,26305.000,3214,0,131525,131147,0,0,0,NULL,NULL,7),
  (3,7,0,0.000,0.000,1,0,33085.000,33085.000,3214,0,165425,109497,0,0,0,NULL,NULL,7),
  (4,10,999,0.000,0.000,1,0,40410.000,40410.000,3214,0,202050,146122,0,0,0,NULL,NULL,7),
  (5,12,0,0.000,0.000,1,0,40410.000,40410.000,3214,0,202050,146122,0,0,0,NULL,NULL,7),
  (6,8,234,0.000,0.000,1,0,40700.000,40700.000,3214,0,203500,197072,0,0,0,NULL,NULL,7),
  (7,9,0,0.000,0.000,1,0,34220.000,34220.000,0,0,171100,257500,0,0,0,NULL,NULL,7);
COMMIT;

#
# Data for the `bankprofile` table  (LIMIT 0,500)
#

INSERT INTO `bankprofile` (`seq_no`, `bank_name`, `bank_address`, `bank_ifsc_code`, `branch_name`, `org_code`) VALUES 
  (3,'jk','smvdu','1232','kakryal',1),
  (1,'SMVDUSBI','kjhkjh','1234','SMVDU',1),
  (2,'STANSBIS','jhgjhg','1234321','SMVDU',2),
  (4,'SBIIIT','kjkjhkj','123ert','IITK',7),
  (5,'HDFC','KLJKLJ','123ertyu','NAK',7);
COMMIT;

#
# Data for the `client_details` table  (LIMIT 0,500)
#

INSERT INTO `client_details` (`seq_no`, `ip_address`, `port`, `client_org_code`, `flag`) VALUES 
  (1,'172.17.167.201','8080',1,1),
  (2,'172.17.167.202','8080',2,1),
  (5,'0:0:0:0:0:0:0:1','8080',7,1);
COMMIT;

#
# Data for the `date_master` table  (LIMIT 0,500)
#

INSERT INTO `date_master` (`t_date`, `t_month`, `t_year`) VALUES 
  ('2011-04-01',4,2011),
  ('2011-04-02',4,2011),
  ('2011-04-03',4,2011),
  ('2011-04-04',4,2011),
  ('2011-04-05',4,2011),
  ('2011-04-06',4,2011),
  ('2011-04-07',4,2011),
  ('2011-04-08',4,2011),
  ('2011-04-09',4,2011),
  ('2011-04-10',4,2011),
  ('2011-04-11',4,2011),
  ('2011-04-12',4,2011),
  ('2011-04-13',4,2011),
  ('2011-04-14',4,2011),
  ('2011-04-15',4,2011),
  ('2011-04-16',4,2011),
  ('2011-04-17',4,2011),
  ('2011-04-18',4,2011),
  ('2011-04-19',4,2011),
  ('2011-04-20',4,2011),
  ('2011-04-21',4,2011),
  ('2011-04-22',4,2011),
  ('2011-04-23',4,2011),
  ('2011-04-24',4,2011),
  ('2011-04-25',4,2011),
  ('2011-04-26',4,2011),
  ('2011-04-27',4,2011),
  ('2011-04-28',4,2011),
  ('2011-04-29',4,2011),
  ('2011-04-30',4,2011),
  ('2011-05-01',5,2011),
  ('2011-05-02',5,2011),
  ('2011-05-03',5,2011),
  ('2011-05-04',5,2011),
  ('2011-05-05',5,2011),
  ('2011-05-06',5,2011),
  ('2011-05-07',5,2011),
  ('2011-05-08',5,2011),
  ('2011-05-09',5,2011),
  ('2011-05-10',5,2011),
  ('2011-05-11',5,2011),
  ('2011-05-12',5,2011),
  ('2011-05-13',5,2011),
  ('2011-05-14',5,2011),
  ('2011-05-15',5,2011),
  ('2011-05-16',5,2011),
  ('2011-05-17',5,2011),
  ('2011-05-18',5,2011),
  ('2011-05-19',5,2011),
  ('2011-05-20',5,2011),
  ('2011-05-21',5,2011),
  ('2011-05-22',5,2011),
  ('2011-05-23',5,2011),
  ('2011-05-24',5,2011),
  ('2011-05-25',5,2011),
  ('2011-05-26',5,2011),
  ('2011-05-27',5,2011),
  ('2011-05-28',5,2011),
  ('2011-05-29',5,2011),
  ('2011-05-30',5,2011),
  ('2011-05-31',5,2011),
  ('2011-07-01',7,2011),
  ('2011-07-02',7,2011),
  ('2011-07-03',7,2011),
  ('2011-07-04',7,2011),
  ('2011-07-05',7,2011),
  ('2011-07-06',7,2011),
  ('2011-07-07',7,2011),
  ('2011-07-08',7,2011),
  ('2011-07-09',7,2011),
  ('2011-07-10',7,2011),
  ('2011-07-11',7,2011),
  ('2011-07-12',7,2011),
  ('2011-07-13',7,2011),
  ('2011-07-14',7,2011),
  ('2011-07-15',7,2011),
  ('2011-07-16',7,2011),
  ('2011-07-17',7,2011),
  ('2011-07-18',7,2011),
  ('2011-07-19',7,2011),
  ('2011-07-20',7,2011),
  ('2011-07-21',7,2011),
  ('2011-07-22',7,2011),
  ('2011-07-23',7,2011),
  ('2011-07-24',7,2011),
  ('2011-07-25',7,2011),
  ('2011-07-26',7,2011),
  ('2011-07-27',7,2011),
  ('2011-07-28',7,2011),
  ('2011-07-29',7,2011),
  ('2011-07-30',7,2011),
  ('2011-07-31',7,2011),
  ('2011-06-01',6,2011),
  ('2011-06-02',6,2011),
  ('2011-06-03',6,2011),
  ('2011-06-04',6,2011),
  ('2011-06-05',6,2011),
  ('2011-06-06',6,2011),
  ('2011-06-07',6,2011),
  ('2011-06-08',6,2011),
  ('2011-06-09',6,2011),
  ('2011-06-10',6,2011),
  ('2011-06-11',6,2011),
  ('2011-06-12',6,2011),
  ('2011-06-13',6,2011),
  ('2011-06-14',6,2011),
  ('2011-06-15',6,2011),
  ('2011-06-16',6,2011),
  ('2011-06-17',6,2011),
  ('2011-06-18',6,2011),
  ('2011-06-19',6,2011),
  ('2011-06-20',6,2011),
  ('2011-06-21',6,2011),
  ('2011-06-22',6,2011),
  ('2011-06-23',6,2011),
  ('2011-06-24',6,2011),
  ('2011-06-25',6,2011),
  ('2011-06-26',6,2011),
  ('2011-06-27',6,2011),
  ('2011-06-28',6,2011),
  ('2011-06-29',6,2011),
  ('2011-06-30',6,2011);
COMMIT;

#
# Data for the `default_salary_master` table  (LIMIT 0,500)
#

INSERT INTO `default_salary_master` (`ds_emp_type`, `ds_sal_head`, `ds_amount`) VALUES 
  (212,1,300),
  (212,4,1200),
  (212,6,350),
  (212,9,4800),
  (212,10,240),
  (212,11,600),
  (212,102,1000),
  (212,103,1000),
  (212,104,65),
  (212,105,80),
  (209,1,1200),
  (209,4,700),
  (209,6,4200),
  (209,9,5600),
  (209,10,450),
  (209,11,430),
  (209,102,2300),
  (209,103,1500),
  (209,104,135),
  (209,105,270),
  (215,1,68000),
  (215,4,2300),
  (215,6,1800),
  (215,9,4500),
  (215,10,4000),
  (215,11,7000),
  (215,102,2400),
  (215,103,5600),
  (215,104,1260),
  (215,105,5000),
  (216,1,0),
  (216,2,0),
  (216,3,0),
  (216,6,0),
  (216,13,0),
  (216,18,0),
  (216,19,0),
  (216,24,0),
  (216,25,0),
  (216,27,0),
  (216,32,0),
  (216,33,0),
  (216,34,0),
  (216,35,0),
  (1,1,8000),
  (1,2,0),
  (1,6,6000),
  (1,13,1230),
  (1,18,0),
  (1,19,123),
  (1,24,0),
  (1,25,0),
  (1,27,0),
  (1,31,0),
  (1,32,0),
  (1,33,0),
  (1,34,0),
  (1,35,0),
  (1,36,0),
  (0,1,12340),
  (0,2,2130),
  (0,6,12340),
  (0,13,0),
  (0,18,0),
  (0,19,1230),
  (0,24,0),
  (0,25,0),
  (0,27,0),
  (0,31,1230),
  (0,32,0),
  (0,33,0),
  (0,34,1230),
  (0,35,0),
  (0,36,12340),
  (2,1,15600),
  (2,2,18000),
  (2,6,0),
  (2,13,0),
  (2,18,0),
  (2,19,12280),
  (2,24,0),
  (2,25,0),
  (2,27,0),
  (2,31,50),
  (2,32,30),
  (2,33,0),
  (2,34,300),
  (2,35,0),
  (2,36,0);
COMMIT;

#
# Data for the `emp_salary_head_master` table  (LIMIT 0,500)
#

INSERT INTO `emp_salary_head_master` (`st_code`, `st_sal_code`, `st_org_code`) VALUES 
  (1,1,1),
  (1,2,1),
  (1,4,1),
  (1,5,1),
  (1,12,1),
  (1,13,1),
  (1,19,1),
  (1,34,1),
  (6,1,1),
  (6,2,1),
  (6,3,1),
  (6,4,1),
  (6,5,1),
  (7,1,7),
  (7,2,7),
  (7,4,7),
  (7,5,7),
  (7,6,7),
  (7,12,7),
  (7,13,7),
  (7,18,7),
  (7,19,7),
  (7,24,7),
  (7,25,7),
  (7,27,7),
  (7,34,7),
  (7,36,7);
COMMIT;

#
# Data for the `emp_slab_head_master` table  (LIMIT 0,500)
#

INSERT INTO `emp_slab_head_master` (`emp_gen_code`, `emp_slab_code`, `emp_slab_orgCode`) VALUES 
  (2,0,1),
  (2,0,1);
COMMIT;

#
# Data for the `emp_tax_master` table  (LIMIT 0,500)
#

INSERT INTO `emp_tax_master` (`et_emp_id`, `et_year`, `et_amount`, `et_id`, `et_effective`, `et_percent`, `et_educess`, `et_sess_id`, `et_org_code`) VALUES 
  ('231434',2013,0.000,1,27890,0.00,0.00,1,7),
  ('0191',2011,2969.200,2,0,10.00,59.38,2,1),
  ('0191',2013,3473.200,3,0,10.00,69.46,2,1);
COMMIT;

#
# Data for the `employee_leave_master` table  (LIMIT 0,500)
#

INSERT INTO `employee_leave_master` (`el_id`, `el_emp_code`, `el_date_from`, `el_date_to`, `el_count`, `el_quota_type`) VALUES 
  (1,1,'2011-01-03','2011-01-13',8,1),
  (2,2,'2011-01-03','2011-01-08',5,1),
  (3,4,'2011-01-01','2011-01-04',3,1),
  (4,10,'2011-01-03','2011-01-06',5,1),
  (5,75,'2011-02-16','2011-02-18',0,1),
  (6,36,'2011-04-19','2011-04-15',4,1);
COMMIT;

#
# Data for the `employee_login_master` table  (LIMIT 0,500)
#

INSERT INTO `employee_login_master` (`el_id`, `el_login_name`, `el_password`, `el_org_id`) VALUES 
  ('0191','0191','0191',1),
  ('098','098','098',1),
  ('110','110','110',1),
  ('1234','1234','1234',1),
  ('231434','231434','231434',7),
  ('231455','231455','231455',7),
  ('231456','231456','231456',7),
  ('231478','231478','231478',7),
  ('231488','231488','231488',7),
  ('231490','231490','231490',7),
  ('231499','231499','231499',7),
  ('432156','4321','432156',2);
COMMIT;

#
# Data for the `employee_salary_summery` table  (LIMIT 0,500)
#

INSERT INTO `employee_salary_summery` (`es_code`, `es_month`, `es_year`, `es_total_income`, `es_total_deduct`, `es_gross`, `es_last_update_date`, `es_org_id`, `es_sess_id`) VALUES 
  ('231456',12,2010,86639,8208,78431,'2013-02-28',1,1),
  ('231434',2,2011,105531,8082,97449,'2013-03-04',1,1),
  ('231455',2,2011,68703,5261,63442,'2013-03-04',1,1),
  ('231456',2,2011,86408,6617,79791,'2013-03-04',1,1),
  ('231478',2,2011,105531,8082,97449,'2013-03-04',1,1),
  ('231488',2,2011,105531,8082,97449,'2013-03-04',1,1),
  ('0191',6,2012,22869,2308,20561,'2013-03-12',1,1),
  ('0191',3,2012,23169,5008,18161,'2013-03-30',1,1),
  ('098',4,2012,41400,3276,38124,'2013-03-31',1,1),
  ('110',4,2012,22890,1851,21039,'2013-03-31',1,1),
  ('1234',4,2012,6387,801,5586,'2013-03-31',1,1),
  ('0191 ',5,2012,22692,2049,20643,'2013-03-31',1,1),
  ('098',5,2012,41292,3474,37818,'2013-03-31',1,1),
  ('110',5,2012,22890,1851,21039,'2013-03-31',1,1),
  ('1234',5,2012,6387,801,5586,'2013-03-31',1,1),
  ('0191',4,2012,23169,1728,21441,'2013-03-31',1,2),
  ('098',4,2012,41400,3276,38124,'2013-03-31',1,3),
  ('0191',5,2012,22692,2049,20643,'2013-03-31',1,2),
  ('0191',6,2012,22869,2308,20561,'2013-03-31',1,2),
  ('0191',7,2012,22869,1928,20941,'2013-04-06',1,2);
COMMIT;

#
# Data for the `employee_type_master` table  (LIMIT 0,500)
#

INSERT INTO `employee_type_master` (`emp_type_id`, `emp_type_name`, `emp_pf_applies`, `emp_org_id`) VALUES 
  (1,'REGULAR',1,1),
  (2,'REGULARSTAN',1,2),
  (3,'AKLOP',0,2),
  (4,'SUMIT',0,2),
  (5,'KLOP',0,2),
  (6,'NON REGULAR',0,1),
  (7,'REGULAR',1,7),
  (8,'CONTRACTUAL',0,7);
COMMIT;

#
# Data for the `investment_category_master` table  (LIMIT 0,500)
#

INSERT INTO `investment_category_master` (`ic_id`, `ic_name`, `ic_max_limit`, `ic_deduction`, `ic_org_id`) VALUES 
  (11,'INVESTMENT',2000,0.00,1),
  (12,'INTEREST',1000,0.00,1),
  (13,'MED',2500,0.00,1),
  (14,'CC',1500,0.00,2),
  (15,'80 C',100000,0.00,7),
  (16,'80 CCF',20000,0.00,7),
  (17,'80 D',40000,0.00,7),
  (18,'80 DD',50000,0.00,7),
  (19,'80 U',50000,0.00,7),
  (20,'80 G',0,0.00,7),
  (21,'80D',1200,0.00,2);
COMMIT;

#
# Data for the `investment_heads` table  (LIMIT 0,500)
#

INSERT INTO `investment_heads` (`ih_id`, `ih_name`, `ih_benefit`, `ih_details`, `ih_under`, `ih_org_id`) VALUES 
  (5,'CPF',1,'ok',11,1),
  (6,'MED HEALTH',1,'okw',12,1),
  (7,'80C',1,'hj',13,1),
  (8,'VIOP',1,'no benefits',14,2),
  (9,'PF',0,'',15,7),
  (10,'PPF',0,'',15,7),
  (11,'EDUCATION LOAN',0,'',16,7),
  (12,'MEDICAL BENEFIT',0,'',16,7),
  (13,'WARD EDUCATION',0,'',15,7),
  (14,'SPECIAL FUND',0,'',15,7),
  (15,'LIC',0,'',15,7),
  (16,'TC',0,'',19,7),
  (17,'HD',1,'benefits',21,2);
COMMIT;

#
# Data for the `investment_plan_master` table  (LIMIT 0,500)
#

INSERT INTO `investment_plan_master` (`ip_id`, `ip_emp_id`, `ip_ins_id`, `ip_amount`, `ip_year`, `ip_sess_id`, `ip_org_id`) VALUES 
  (6,'110',5,1200,2012,2,1),
  (7,'110',6,1500,2012,2,1),
  (8,'110',5,1000,2012,1,1),
  (9,'110',6,2000,2012,1,1),
  (10,'110',7,9808,2012,1,1),
  (24,'4321',8,1000,2012,1,2),
  (25,'4321',8,1200,2012,2,2),
  (26,'231434',9,7890,2013,1,7),
  (27,'231434',10,0,2013,1,7),
  (28,'231434',11,45678,2013,1,7),
  (29,'231434',12,0,2013,1,7),
  (30,'231434',13,789,2013,1,7),
  (31,'231434',14,0,2013,1,7),
  (32,'231434',15,0,2013,1,7),
  (33,'231434',16,0,2013,1,7),
  (34,'231455',9,6789,2013,1,7),
  (35,'231455',10,0,2013,1,7),
  (36,'231455',11,34256,2013,1,7),
  (37,'231455',12,0,2013,1,7),
  (38,'231455',13,456,2013,1,7),
  (39,'231455',14,0,2013,1,7),
  (40,'231455',15,2345,2013,1,7),
  (41,'231455',16,8907,2013,1,7),
  (42,'231456',9,6745,2013,1,7),
  (43,'231456',10,0,2013,1,7),
  (44,'231456',11,2000,2013,1,7),
  (45,'231456',12,0,2013,1,7),
  (46,'231456',13,1234,2013,1,7),
  (47,'231456',14,1234,2013,1,7),
  (48,'231456',15,0,2013,1,7),
  (49,'231456',16,8769,2013,1,7),
  (50,'231488',9,12345,2013,1,7),
  (51,'231488',10,0,2013,1,7),
  (52,'231488',11,2345,2013,1,7),
  (53,'231488',12,0,2013,1,7),
  (54,'231488',13,456,2013,1,7),
  (55,'231488',14,0,2013,1,7),
  (56,'231488',15,1234,2013,1,7),
  (57,'231488',16,980,2013,1,7),
  (58,'231490',9,12345,2013,1,7),
  (59,'231490',10,1234,2013,1,7),
  (60,'231490',11,34567,2013,1,7),
  (61,'231490',12,0,2013,1,7),
  (62,'231490',13,678,2013,1,7),
  (63,'231490',14,0,2013,1,7),
  (64,'231490',15,2345,2013,1,7),
  (65,'231490',16,0,2013,1,7),
  (66,'231455',9,50000,2010,1,7),
  (67,'231455',10,0,2010,1,7),
  (68,'231455',11,0,2010,1,7),
  (69,'231455',12,0,2010,1,7),
  (70,'231455',13,0,2010,1,7),
  (71,'231455',14,0,2010,1,7),
  (72,'231455',15,10000,2010,1,7),
  (73,'231455',16,0,2010,1,7),
  (74,'0191',5,1000,2013,1,1),
  (75,'0191',6,0,2013,1,1),
  (76,'0191',7,0,2013,1,1),
  (77,'4321',8,0,2013,1,2),
  (78,'4321',17,2000,2013,1,2),
  (79,'0191',5,2000,2012,2,1),
  (80,'0191',6,0,2012,2,1),
  (81,'0191',7,1000,2012,2,1);
COMMIT;

#
# Data for the `leave_type_master` table  (LIMIT 0,500)
#

INSERT INTO `leave_type_master` (`lt_id`, `lt_name`) VALUES 
  (1,'EL'),
  (2,'CL');
COMMIT;

#
# Data for the `leave_value_master` table  (LIMIT 0,500)
#

INSERT INTO `leave_value_master` (`lv_id`, `lv_name`, `lv_value`) VALUES 
  (1,'Full Day',1.0),
  (2,'Half Day',0.5),
  (3,'Just attended',0.3);
COMMIT;

#
# Data for the `loan_master` table  (LIMIT 0,500)
#

INSERT INTO `loan_master` (`ln_id`, `ln_name`, `ln_max_amount`, `ln_interest`) VALUES 
  (1,'Common',100000,8.33);
COMMIT;

#
# Data for the `loan_period_master` table  (LIMIT 0,500)
#

INSERT INTO `loan_period_master` (`lp_id`, `lp_name`, `lp_value`) VALUES 
  (1,'Monthly',1),
  (2,'Quarterly',3),
  (3,'Half yearly',6),
  (4,'Yearly',12);
COMMIT;

#
# Data for the `loan_request_master` table  (LIMIT 0,500)
#

INSERT INTO `loan_request_master` (`lr_id`, `lr_emp_id`, `lr_date`, `lr_amount`, `lr_approved`, `lr_approve_date`, `lr_installment`, `lr_duration`) VALUES 
  (1,1,'2011-04-26',60000,0,'2011-04-26',1500,1),
  (2,1,'2011-04-26',70000,0,'2011-04-26',2000,1),
  (3,36,'2011-04-26',80000,0,'2011-04-26',2000,1);
COMMIT;

#
# Data for the `month_master` table  (LIMIT 0,500)
#

INSERT INTO `month_master` (`mm_month`, `mm_year`, `factor`) VALUES 
  ('2011-03-01',2011,12),
  ('2011-04-01',2011,11),
  ('2011-05-01',2011,10),
  ('2011-06-01',2011,9),
  ('2011-07-01',2011,8),
  ('2011-08-01',2011,7),
  ('2011-09-01',2011,6),
  ('2011-10-01',2011,5),
  ('2011-11-01',2011,4),
  ('2011-12-01',2011,3),
  ('2012-01-01',2011,2),
  ('2012-02-01',2011,1);
COMMIT;

#
# Data for the `month_master_2` table  (LIMIT 0,500)
#

INSERT INTO `month_master_2` (`mm_month`, `mm_year`, `factor`) VALUES 
  ('2011-03-01',2011,12),
  ('2011-04-01',2011,11),
  ('2011-05-01',2011,10),
  ('2011-06-01',2011,9),
  ('2011-07-01',2011,8),
  ('2011-08-01',2011,7),
  ('2011-09-01',2011,6),
  ('2011-10-01',2011,5),
  ('2011-11-01',2011,4),
  ('2011-12-01',2011,3),
  ('2012-01-01',2011,2),
  ('2012-02-01',2011,1);
COMMIT;

#
# Data for the `month_master_3` table  (LIMIT 0,500)
#

INSERT INTO `month_master_3` (`mm_month`, `mm_year`, `factor`) VALUES 
  ('2010-03-01',2010,12),
  ('2010-04-01',2010,11),
  ('2010-05-01',2010,10),
  ('2010-06-01',2010,9),
  ('2010-07-01',2010,8),
  ('2010-08-01',2010,7);
COMMIT;

#
# Data for the `pf_account_master` table  (LIMIT 0,500)
#

INSERT INTO `pf_account_master` (`pf_id`, `pf_ac_id`, `pf_amount`, `pf_sess_id`, `pf_type`, `pf_ac_date`, `pf_org_id`) VALUES 
  (3,'1',10,2,1,'2012-09-04',1),
  (4,'2',454,2,1,'2012-09-05',2),
  (7,'11',3214,1,1,'2010-12-08',7),
  (8,'13',3214,1,1,'2010-12-08',7),
  (9,'7',3214,1,1,'2010-12-08',7),
  (10,'10',3214,1,1,'2010-12-08',7),
  (11,'12',3214,1,1,'2010-12-08',7),
  (12,'8',3214,1,1,'2010-12-08',7),
  (13,'9',0,1,1,'2010-12-15',7);
COMMIT;

#
# Data for the `pf_data_master` table  (LIMIT 0,500)
#

INSERT INTO `pf_data_master` (`pf_emp_id`, `pf_op_balance`, `pf_op_balance_empl`, `pf_sess`, `pf_org_id`) VALUES 
  (11,1234,0,1,7),
  (13,0,0,1,7),
  (7,0,0,1,7),
  (10,999,0,1,7),
  (12,0,0,1,7),
  (8,234,0,1,7),
  (9,0,0,1,7);
COMMIT;

#
# Data for the `pf_setting_master` table  (LIMIT 0,500)
#

INSERT INTO `pf_setting_master` (`pf_empr_share`) VALUES 
  (100.00);
COMMIT;

#
# Data for the `report_master` table  (LIMIT 0,500)
#

INSERT INTO `report_master` (`rm_id`, `rm_name`) VALUES 
  (1,'PF Report'),
  (2,'ESI Report'),
  (3,'Overtime Report');
COMMIT;

#
# Data for the `salary_head_master` table  (LIMIT 0,500)
#

INSERT INTO `salary_head_master` (`sh_id`, `sh_name`, `sh_type`, `sh_alias`, `sh_calc_type`, `sh_formula`, `sh_scalable`, `sh_special`, `sh_cat`, `sh_display`, `sh_type_code`, `sh_process_type`, `sh_org_id`) VALUES 
  (1,'PAY',1,'basic',0,NULL,1,0,1,1,3,0,7),
  (2,'GRADE PAY',1,'gp',0,NULL,0,0,1,0,3,0,7),
  (3,'NPA',1,'npa',1,NULL,0,0,1,1,3,0,7),
  (4,'DA',1,'da',1,NULL,0,0,1,1,3,0,7),
  (5,'HRA',1,'hra',1,'base*0.15',0,0,1,1,3,0,7),
  (6,'AREARS',1,'arr',0,NULL,0,0,1,1,3,0,7),
  (9,'CPF(EMPLOYER''S)',1,'cpfe',1,NULL,0,1,1,1,3,0,7),
  (12,'CPF / GPF',0,'cpf',1,NULL,0,1,1,1,2,0,7),
  (13,'TDS',0,'tds',0,NULL,0,1,1,1,1,0,7),
  (18,'PAY RECOVERY',1,'pfr',0,NULL,0,0,1,1,2,0,7),
  (19,'GSLI',0,'gsli',0,NULL,0,0,1,1,3,0,7),
  (24,'TRANSPORTATION',0,'trans',0,NULL,0,0,1,1,3,0,7),
  (25,'OTHER DEDUCTION',0,'mhd',0,NULL,0,0,1,0,3,0,7),
  (27,'OTHER ALLOWANCES',1,'ttn',0,NULL,0,0,1,0,3,0,7),
  (31,'HOUSE KEEPING',0,'hk',0,NULL,0,0,1,1,3,0,7),
  (32,'WATER',0,'wtr',0,NULL,1,0,1,0,3,0,7),
  (33,'FURNITURE RENT',0,'fur',0,NULL,1,0,1,0,3,0,7),
  (34,'MEDICAL ALLOWANCES',1,'ma',0,NULL,1,0,1,0,3,0,7),
  (35,'ELECTRICITY',0,'elctr',0,NULL,1,0,1,0,3,0,7),
  (36,'PF RECOVERY',0,'pfrecovery',0,NULL,0,0,1,0,2,0,7),
  (37,'DA ARREARS',1,'DAarr',1,NULL,1,0,1,0,1,0,7);
COMMIT;

#
# Data for the `salary_data` table  (LIMIT 0,500)
#

INSERT INTO `salary_data` (`sd_emp_code`, `sd_head_code`, `sd_date`, `sd_amount`, `sd_sess_id`, `org_code`) VALUES 
  ('231434',1,'2010-08-10',24592,1,7),
  ('231455',1,'2010-08-10',24592,1,7),
  ('231456',1,'2010-08-10',23000,1,7),
  ('231478',1,'2010-08-10',24592,1,7),
  ('231488',1,'2010-08-10',24592,1,7),
  ('231490',1,'2010-08-10',25000,1,7),
  ('231499',1,'2010-08-10',24592,1,7),
  ('231434',2,'2010-08-10',32145,1,7),
  ('231455',2,'2010-08-10',12345,1,7),
  ('231456',2,'2010-08-10',23456,1,7),
  ('231478',2,'2010-08-10',32145,1,7),
  ('231488',2,'2010-08-10',32145,1,7),
  ('231490',2,'2010-08-10',32145,1,7),
  ('231499',2,'2010-08-10',23456,1,7),
  ('231434',4,'2010-08-10',40283,1,7),
  ('231455',4,'2010-08-10',26225,1,7),
  ('231456',4,'2010-08-10',32984,1,7),
  ('231478',4,'2010-08-10',40283,1,7),
  ('231488',4,'2010-08-10',40283,1,7),
  ('231490',4,'2010-08-10',40573,1,7),
  ('231499',4,'2010-08-10',34114,1,7),
  ('231434',5,'2010-08-10',8511,1,7),
  ('231455',5,'2010-08-10',5541,1,7),
  ('231456',5,'2010-08-10',6968,1,7),
  ('231478',5,'2010-08-10',8511,1,7),
  ('231488',5,'2010-08-10',8511,1,7),
  ('231490',5,'2010-08-10',8572,1,7),
  ('231499',5,'2010-08-10',7207,1,7),
  ('231434',6,'2010-08-10',0,1,7),
  ('231455',6,'2010-08-10',0,1,7),
  ('231456',6,'2010-08-10',0,1,7),
  ('231478',6,'2010-08-10',0,1,7),
  ('231488',6,'2010-08-10',0,1,7),
  ('231490',6,'2010-08-10',0,1,7),
  ('231499',6,'2010-08-10',0,1,7),
  ('231434',12,'2010-08-10',8082,1,7),
  ('231455',12,'2010-08-10',5261,1,7),
  ('231456',12,'2010-08-10',6617,1,7),
  ('231478',12,'2010-08-10',8082,1,7),
  ('231488',12,'2010-08-10',8082,1,7),
  ('231490',12,'2010-08-10',8140,1,7),
  ('231499',12,'2010-08-10',6844,1,7),
  ('231434',13,'2010-08-10',0,1,7),
  ('231455',13,'2010-08-10',0,1,7),
  ('231456',13,'2010-08-10',234,1,7),
  ('231478',13,'2010-08-10',234,1,7),
  ('231488',13,'2010-08-10',5678,1,7),
  ('231490',13,'2010-08-10',456,1,7),
  ('231499',13,'2010-08-10',3456,1,7),
  ('231434',18,'2010-08-10',234,1,7),
  ('231455',18,'2010-08-10',100,1,7),
  ('231456',18,'2010-08-10',0,1,7),
  ('231478',18,'2010-08-10',213,1,7),
  ('231488',18,'2010-08-10',0,1,7),
  ('231490',18,'2010-08-10',0,1,7),
  ('231499',18,'2010-08-10',0,1,7),
  ('231434',19,'2010-08-10',123,1,7),
  ('231455',19,'2010-08-10',0,1,7),
  ('231456',19,'2010-08-10',123,1,7),
  ('231478',19,'2010-08-10',0,1,7),
  ('231488',19,'2010-08-10',123,1,7),
  ('231490',19,'2010-08-10',213,1,7),
  ('231499',19,'2010-08-10',213,1,7),
  ('231434',24,'2010-08-10',0,1,7),
  ('231455',24,'2010-08-10',0,1,7),
  ('231456',24,'2010-08-10',0,1,7),
  ('231478',24,'2010-08-10',0,1,7),
  ('231488',24,'2010-08-10',0,1,7),
  ('231490',24,'2010-08-10',0,1,7),
  ('231499',24,'2010-08-10',0,1,7),
  ('231434',25,'2010-08-10',123,1,7),
  ('231455',25,'2010-08-10',0,1,7),
  ('231456',25,'2010-08-10',0,1,7),
  ('231478',25,'2010-08-10',0,1,7),
  ('231488',25,'2010-08-10',0,1,7),
  ('231490',25,'2010-08-10',0,1,7),
  ('231499',25,'2010-08-10',0,1,7),
  ('231434',27,'2010-08-10',0,1,7),
  ('231455',27,'2010-08-10',0,1,7),
  ('231456',27,'2010-08-10',0,1,7),
  ('231478',27,'2010-08-10',123,1,7),
  ('231488',27,'2010-08-10',123,1,7),
  ('231490',27,'2010-08-10',0,1,7),
  ('231499',27,'2010-08-10',0,1,7),
  ('231434',34,'2010-08-10',234,1,7),
  ('231455',34,'2010-08-10',234,1,7),
  ('231456',34,'2010-08-10',231,1,7),
  ('231478',34,'2010-08-10',123,1,7),
  ('231488',34,'2010-08-10',234,1,7),
  ('231490',34,'2010-08-10',1234,1,7),
  ('231499',34,'2010-08-10',1234,1,7),
  ('231434',36,'2010-08-10',0,1,7),
  ('231455',36,'2010-08-10',3456,1,7),
  ('231456',36,'2010-08-10',1234,1,7),
  ('231478',36,'2010-08-10',1234,1,7),
  ('231488',36,'2010-08-10',1234,1,7),
  ('231490',36,'2010-08-10',3214,1,7),
  ('231499',36,'2010-08-10',3456,1,7),
  ('231434',1,'2010-09-08',24592,1,7),
  ('231455',1,'2010-09-08',24592,1,7),
  ('231456',1,'2010-09-08',23000,1,7),
  ('231478',1,'2010-09-08',24592,1,7),
  ('231488',1,'2010-09-08',24592,1,7),
  ('231490',1,'2010-09-08',25000,1,7),
  ('231499',1,'2010-09-08',24592,1,7),
  ('231434',2,'2010-09-08',32145,1,7),
  ('231455',2,'2010-09-08',12345,1,7),
  ('231456',2,'2010-09-08',23456,1,7),
  ('231478',2,'2010-09-08',32145,1,7),
  ('231488',2,'2010-09-08',32145,1,7),
  ('231490',2,'2010-09-08',32145,1,7),
  ('231499',2,'2010-09-08',23456,1,7),
  ('231434',4,'2010-09-08',40283,1,7),
  ('231455',4,'2010-09-08',26225,1,7),
  ('231456',4,'2010-09-08',32984,1,7),
  ('231478',4,'2010-09-08',40283,1,7),
  ('231488',4,'2010-09-08',40283,1,7),
  ('231490',4,'2010-09-08',40573,1,7),
  ('231499',4,'2010-09-08',34114,1,7),
  ('231434',5,'2010-09-08',8511,1,7),
  ('231455',5,'2010-09-08',5541,1,7),
  ('231456',5,'2010-09-08',6968,1,7),
  ('231478',5,'2010-09-08',8511,1,7),
  ('231488',5,'2010-09-08',8511,1,7),
  ('231490',5,'2010-09-08',8572,1,7),
  ('231499',5,'2010-09-08',7207,1,7),
  ('231434',6,'2010-09-08',0,1,7),
  ('231455',6,'2010-09-08',0,1,7),
  ('231456',6,'2010-09-08',0,1,7),
  ('231478',6,'2010-09-08',0,1,7),
  ('231488',6,'2010-09-08',0,1,7),
  ('231490',6,'2010-09-08',0,1,7),
  ('231499',6,'2010-09-08',0,1,7),
  ('231434',12,'2010-09-08',8082,1,7),
  ('231455',12,'2010-09-08',5261,1,7),
  ('231456',12,'2010-09-08',6617,1,7),
  ('231478',12,'2010-09-08',8082,1,7),
  ('231488',12,'2010-09-08',8082,1,7),
  ('231490',12,'2010-09-08',8140,1,7),
  ('231499',12,'2010-09-08',6844,1,7),
  ('231434',13,'2010-09-08',0,1,7),
  ('231455',13,'2010-09-08',0,1,7),
  ('231456',13,'2010-09-08',234,1,7),
  ('231478',13,'2010-09-08',234,1,7),
  ('231488',13,'2010-09-08',5678,1,7),
  ('231490',13,'2010-09-08',456,1,7),
  ('231499',13,'2010-09-08',3456,1,7),
  ('231434',18,'2010-09-08',234,1,7),
  ('231455',18,'2010-09-08',100,1,7),
  ('231456',18,'2010-09-08',0,1,7),
  ('231478',18,'2010-09-08',213,1,7),
  ('231488',18,'2010-09-08',0,1,7),
  ('231490',18,'2010-09-08',0,1,7),
  ('231499',18,'2010-09-08',0,1,7),
  ('231434',19,'2010-09-08',123,1,7),
  ('231455',19,'2010-09-08',0,1,7),
  ('231456',19,'2010-09-08',123,1,7),
  ('231478',19,'2010-09-08',0,1,7),
  ('231488',19,'2010-09-08',123,1,7),
  ('231490',19,'2010-09-08',213,1,7),
  ('231499',19,'2010-09-08',213,1,7),
  ('231434',24,'2010-09-08',0,1,7),
  ('231455',24,'2010-09-08',0,1,7),
  ('231456',24,'2010-09-08',0,1,7),
  ('231478',24,'2010-09-08',0,1,7),
  ('231488',24,'2010-09-08',0,1,7),
  ('231490',24,'2010-09-08',0,1,7),
  ('231499',24,'2010-09-08',0,1,7),
  ('231434',25,'2010-09-08',123,1,7),
  ('231455',25,'2010-09-08',0,1,7),
  ('231456',25,'2010-09-08',0,1,7),
  ('231478',25,'2010-09-08',0,1,7),
  ('231488',25,'2010-09-08',0,1,7),
  ('231490',25,'2010-09-08',0,1,7),
  ('231499',25,'2010-09-08',0,1,7),
  ('231434',27,'2010-09-08',0,1,7),
  ('231455',27,'2010-09-08',0,1,7),
  ('231456',27,'2010-09-08',0,1,7),
  ('231478',27,'2010-09-08',123,1,7),
  ('231488',27,'2010-09-08',123,1,7),
  ('231490',27,'2010-09-08',0,1,7),
  ('231499',27,'2010-09-08',0,1,7),
  ('231434',34,'2010-09-08',234,1,7),
  ('231455',34,'2010-09-08',234,1,7),
  ('231456',34,'2010-09-08',231,1,7),
  ('231478',34,'2010-09-08',123,1,7),
  ('231488',34,'2010-09-08',234,1,7),
  ('231490',34,'2010-09-08',1234,1,7),
  ('231499',34,'2010-09-08',1234,1,7),
  ('231434',36,'2010-09-08',0,1,7),
  ('231455',36,'2010-09-08',3456,1,7),
  ('231456',36,'2010-09-08',1234,1,7),
  ('231478',36,'2010-09-08',1234,1,7),
  ('231488',36,'2010-09-08',1234,1,7),
  ('231490',36,'2010-09-08',3214,1,7),
  ('231499',36,'2010-09-08',3456,1,7),
  ('231455',1,'2010-10-05',24592,1,7),
  ('231456',1,'2010-10-05',23000,1,7),
  ('231478',1,'2010-10-05',24592,1,7),
  ('231488',1,'2010-10-05',24592,1,7),
  ('231490',1,'2010-10-05',25000,1,7),
  ('231499',1,'2010-10-05',24592,1,7),
  ('231455',2,'2010-10-05',12345,1,7),
  ('231456',2,'2010-10-05',23456,1,7),
  ('231478',2,'2010-10-05',32145,1,7),
  ('231488',2,'2010-10-05',32145,1,7),
  ('231490',2,'2010-10-05',32145,1,7),
  ('231499',2,'2010-10-05',23456,1,7),
  ('231455',4,'2010-10-05',26225,1,7),
  ('231456',4,'2010-10-05',32984,1,7),
  ('231478',4,'2010-10-05',40283,1,7),
  ('231488',4,'2010-10-05',40283,1,7),
  ('231490',4,'2010-10-05',40573,1,7),
  ('231499',4,'2010-10-05',34114,1,7),
  ('231455',5,'2010-10-05',5541,1,7),
  ('231456',5,'2010-10-05',6968,1,7),
  ('231478',5,'2010-10-05',8511,1,7),
  ('231488',5,'2010-10-05',8511,1,7),
  ('231490',5,'2010-10-05',8572,1,7),
  ('231499',5,'2010-10-05',7207,1,7),
  ('231455',6,'2010-10-05',0,1,7),
  ('231456',6,'2010-10-05',0,1,7),
  ('231478',6,'2010-10-05',0,1,7),
  ('231488',6,'2010-10-05',0,1,7),
  ('231490',6,'2010-10-05',0,1,7),
  ('231499',6,'2010-10-05',0,1,7),
  ('231455',12,'2010-10-05',5261,1,7),
  ('231456',12,'2010-10-05',6617,1,7),
  ('231478',12,'2010-10-05',8082,1,7),
  ('231488',12,'2010-10-05',8082,1,7),
  ('231490',12,'2010-10-05',8140,1,7),
  ('231499',12,'2010-10-05',6844,1,7),
  ('231455',13,'2010-10-05',0,1,7),
  ('231456',13,'2010-10-05',234,1,7),
  ('231478',13,'2010-10-05',234,1,7),
  ('231488',13,'2010-10-05',5678,1,7),
  ('231490',13,'2010-10-05',456,1,7),
  ('231499',13,'2010-10-05',3456,1,7),
  ('231455',18,'2010-10-05',100,1,7),
  ('231456',18,'2010-10-05',0,1,7),
  ('231478',18,'2010-10-05',213,1,7),
  ('231488',18,'2010-10-05',0,1,7),
  ('231490',18,'2010-10-05',0,1,7),
  ('231499',18,'2010-10-05',0,1,7),
  ('231455',19,'2010-10-05',0,1,7),
  ('231456',19,'2010-10-05',123,1,7),
  ('231478',19,'2010-10-05',0,1,7),
  ('231488',19,'2010-10-05',123,1,7),
  ('231490',19,'2010-10-05',213,1,7),
  ('231499',19,'2010-10-05',213,1,7),
  ('231455',24,'2010-10-05',0,1,7),
  ('231456',24,'2010-10-05',0,1,7),
  ('231478',24,'2010-10-05',0,1,7),
  ('231488',24,'2010-10-05',0,1,7),
  ('231490',24,'2010-10-05',0,1,7),
  ('231499',24,'2010-10-05',0,1,7),
  ('231455',25,'2010-10-05',0,1,7),
  ('231456',25,'2010-10-05',0,1,7),
  ('231478',25,'2010-10-05',0,1,7),
  ('231488',25,'2010-10-05',0,1,7),
  ('231490',25,'2010-10-05',0,1,7),
  ('231499',25,'2010-10-05',0,1,7),
  ('231455',27,'2010-10-05',0,1,7),
  ('231456',27,'2010-10-05',0,1,7),
  ('231478',27,'2010-10-05',123,1,7),
  ('231488',27,'2010-10-05',123,1,7),
  ('231490',27,'2010-10-05',0,1,7),
  ('231499',27,'2010-10-05',0,1,7),
  ('231455',34,'2010-10-05',234,1,7),
  ('231456',34,'2010-10-05',231,1,7),
  ('231478',34,'2010-10-05',123,1,7),
  ('231488',34,'2010-10-05',234,1,7),
  ('231490',34,'2010-10-05',1234,1,7),
  ('231499',34,'2010-10-05',1234,1,7),
  ('231455',36,'2010-10-05',3456,1,7),
  ('231456',36,'2010-10-05',1234,1,7),
  ('231478',36,'2010-10-05',1234,1,7),
  ('231488',36,'2010-10-05',1234,1,7),
  ('231490',36,'2010-10-05',3214,1,7),
  ('231499',36,'2010-10-05',3456,1,7),
  ('231434',1,'2010-10-13',24592,1,7),
  ('231434',2,'2010-10-13',32145,1,7),
  ('231434',4,'2010-10-13',40283,1,7),
  ('231434',5,'2010-10-13',8511,1,7),
  ('231434',6,'2010-10-13',0,1,7),
  ('231434',12,'2010-10-13',8082,1,7),
  ('231434',13,'2010-10-13',0,1,7),
  ('231434',18,'2010-10-13',234,1,7),
  ('231434',19,'2010-10-13',123,1,7),
  ('231434',24,'2010-10-13',0,1,7),
  ('231434',25,'2010-10-13',123,1,7),
  ('231434',27,'2010-10-13',0,1,7),
  ('231434',34,'2010-10-13',234,1,7),
  ('231434',36,'2010-10-13',0,1,7),
  ('231434',1,'2010-11-02',24592,1,7),
  ('231455',1,'2010-11-02',24592,1,7),
  ('231456',1,'2010-11-02',23000,1,7),
  ('231478',1,'2010-11-02',24592,1,7),
  ('231488',1,'2010-11-02',24592,1,7),
  ('231490',1,'2010-11-02',25000,1,7),
  ('231499',1,'2010-11-02',24592,1,7),
  ('231434',2,'2010-11-02',32145,1,7),
  ('231455',2,'2010-11-02',12345,1,7),
  ('231456',2,'2010-11-02',23456,1,7),
  ('231478',2,'2010-11-02',32145,1,7),
  ('231488',2,'2010-11-02',32145,1,7),
  ('231490',2,'2010-11-02',32145,1,7),
  ('231499',2,'2010-11-02',23456,1,7),
  ('231434',4,'2010-11-02',40283,1,7),
  ('231455',4,'2010-11-02',26225,1,7),
  ('231456',4,'2010-11-02',32984,1,7),
  ('231478',4,'2010-11-02',40283,1,7),
  ('231488',4,'2010-11-02',40283,1,7),
  ('231490',4,'2010-11-02',40573,1,7),
  ('231499',4,'2010-11-02',34114,1,7),
  ('231434',5,'2010-11-02',8511,1,7),
  ('231455',5,'2010-11-02',5541,1,7),
  ('231456',5,'2010-11-02',6968,1,7),
  ('231478',5,'2010-11-02',8511,1,7),
  ('231488',5,'2010-11-02',8511,1,7),
  ('231490',5,'2010-11-02',8572,1,7),
  ('231499',5,'2010-11-02',7207,1,7),
  ('231434',6,'2010-11-02',0,1,7),
  ('231455',6,'2010-11-02',0,1,7),
  ('231456',6,'2010-11-02',0,1,7),
  ('231478',6,'2010-11-02',0,1,7),
  ('231488',6,'2010-11-02',0,1,7),
  ('231490',6,'2010-11-02',0,1,7),
  ('231499',6,'2010-11-02',0,1,7),
  ('231434',12,'2010-11-02',8082,1,7),
  ('231455',12,'2010-11-02',5261,1,7),
  ('231456',12,'2010-11-02',6617,1,7),
  ('231478',12,'2010-11-02',8082,1,7),
  ('231488',12,'2010-11-02',8082,1,7),
  ('231490',12,'2010-11-02',8140,1,7),
  ('231499',12,'2010-11-02',6844,1,7),
  ('231434',13,'2010-11-02',0,1,7),
  ('231455',13,'2010-11-02',0,1,7),
  ('231456',13,'2010-11-02',234,1,7),
  ('231478',13,'2010-11-02',234,1,7),
  ('231488',13,'2010-11-02',5678,1,7),
  ('231490',13,'2010-11-02',456,1,7),
  ('231499',13,'2010-11-02',3456,1,7),
  ('231434',18,'2010-11-02',234,1,7),
  ('231455',18,'2010-11-02',100,1,7),
  ('231456',18,'2010-11-02',0,1,7),
  ('231478',18,'2010-11-02',213,1,7),
  ('231488',18,'2010-11-02',0,1,7),
  ('231490',18,'2010-11-02',0,1,7),
  ('231499',18,'2010-11-02',0,1,7),
  ('231434',19,'2010-11-02',123,1,7),
  ('231455',19,'2010-11-02',0,1,7),
  ('231456',19,'2010-11-02',123,1,7),
  ('231478',19,'2010-11-02',0,1,7),
  ('231488',19,'2010-11-02',123,1,7),
  ('231490',19,'2010-11-02',213,1,7),
  ('231499',19,'2010-11-02',213,1,7),
  ('231434',24,'2010-11-02',0,1,7),
  ('231455',24,'2010-11-02',0,1,7),
  ('231456',24,'2010-11-02',0,1,7),
  ('231478',24,'2010-11-02',0,1,7),
  ('231488',24,'2010-11-02',0,1,7),
  ('231490',24,'2010-11-02',0,1,7),
  ('231499',24,'2010-11-02',0,1,7),
  ('231434',25,'2010-11-02',123,1,7),
  ('231455',25,'2010-11-02',0,1,7),
  ('231456',25,'2010-11-02',0,1,7),
  ('231478',25,'2010-11-02',0,1,7),
  ('231488',25,'2010-11-02',0,1,7),
  ('231490',25,'2010-11-02',0,1,7),
  ('231499',25,'2010-11-02',0,1,7),
  ('231434',27,'2010-11-02',0,1,7),
  ('231455',27,'2010-11-02',0,1,7),
  ('231456',27,'2010-11-02',0,1,7),
  ('231478',27,'2010-11-02',123,1,7),
  ('231488',27,'2010-11-02',123,1,7),
  ('231490',27,'2010-11-02',0,1,7),
  ('231499',27,'2010-11-02',0,1,7),
  ('231434',34,'2010-11-02',234,1,7),
  ('231455',34,'2010-11-02',234,1,7),
  ('231456',34,'2010-11-02',231,1,7),
  ('231478',34,'2010-11-02',123,1,7),
  ('231488',34,'2010-11-02',234,1,7),
  ('231490',34,'2010-11-02',1234,1,7),
  ('231499',34,'2010-11-02',1234,1,7),
  ('231434',36,'2010-11-02',0,1,7),
  ('231455',36,'2010-11-02',3456,1,7),
  ('231456',36,'2010-11-02',1234,1,7),
  ('231478',36,'2010-11-02',1234,1,7),
  ('231488',36,'2010-11-02',1234,1,7),
  ('231490',36,'2010-11-02',3214,1,7),
  ('231499',36,'2010-11-02',3456,1,7),
  ('231456',1,'2010-12-02',23000,1,7),
  ('231456',2,'2010-12-02',23456,1,7),
  ('231456',4,'2010-12-02',32984,1,7),
  ('231456',5,'2010-12-02',6968,1,7),
  ('231456',6,'2010-12-02',0,1,7),
  ('231456',12,'2010-12-02',6617,1,7),
  ('231456',13,'2010-12-02',234,1,7),
  ('231456',18,'2010-12-02',0,1,7),
  ('231456',19,'2010-12-02',123,1,7),
  ('231456',24,'2010-12-02',0,1,7),
  ('231456',25,'2010-12-02',0,1,7),
  ('231456',27,'2010-12-02',0,1,7),
  ('231456',34,'2010-12-02',231,1,7),
  ('231456',36,'2010-12-02',1234,1,7),
  ('231434',1,'2010-12-08',24592,1,7),
  ('231455',1,'2010-12-08',24592,1,7),
  ('231478',1,'2010-12-08',24592,1,7),
  ('231488',1,'2010-12-08',24592,1,7),
  ('231490',1,'2010-12-08',25000,1,7),
  ('231499',1,'2010-12-08',24592,1,7),
  ('231434',2,'2010-12-08',32145,1,7),
  ('231455',2,'2010-12-08',12345,1,7),
  ('231478',2,'2010-12-08',32145,1,7),
  ('231488',2,'2010-12-08',32145,1,7),
  ('231490',2,'2010-12-08',32145,1,7),
  ('231499',2,'2010-12-08',23456,1,7),
  ('231434',4,'2010-12-08',40283,1,7),
  ('231455',4,'2010-12-08',26225,1,7),
  ('231478',4,'2010-12-08',40283,1,7),
  ('231488',4,'2010-12-08',40283,1,7),
  ('231490',4,'2010-12-08',40573,1,7),
  ('231499',4,'2010-12-08',34114,1,7),
  ('231434',5,'2010-12-08',8511,1,7),
  ('231455',5,'2010-12-08',5541,1,7),
  ('231478',5,'2010-12-08',8511,1,7),
  ('231488',5,'2010-12-08',8511,1,7),
  ('231490',5,'2010-12-08',8572,1,7),
  ('231499',5,'2010-12-08',7207,1,7),
  ('231434',6,'2010-12-08',0,1,7),
  ('231455',6,'2010-12-08',0,1,7),
  ('231478',6,'2010-12-08',0,1,7),
  ('231488',6,'2010-12-08',0,1,7),
  ('231490',6,'2010-12-08',0,1,7),
  ('231499',6,'2010-12-08',0,1,7),
  ('231434',12,'2010-12-08',8082,1,7),
  ('231455',12,'2010-12-08',5261,1,7),
  ('231478',12,'2010-12-08',8082,1,7),
  ('231488',12,'2010-12-08',8082,1,7),
  ('231490',12,'2010-12-08',8140,1,7),
  ('231499',12,'2010-12-08',6844,1,7),
  ('231434',13,'2010-12-08',0,1,7),
  ('231455',13,'2010-12-08',0,1,7),
  ('231478',13,'2010-12-08',234,1,7),
  ('231488',13,'2010-12-08',5678,1,7),
  ('231490',13,'2010-12-08',456,1,7),
  ('231499',13,'2010-12-08',3456,1,7),
  ('231434',18,'2010-12-08',234,1,7),
  ('231455',18,'2010-12-08',100,1,7),
  ('231478',18,'2010-12-08',213,1,7),
  ('231488',18,'2010-12-08',0,1,7),
  ('231490',18,'2010-12-08',0,1,7),
  ('231499',18,'2010-12-08',0,1,7),
  ('231434',19,'2010-12-08',123,1,7),
  ('231455',19,'2010-12-08',0,1,7),
  ('231478',19,'2010-12-08',0,1,7),
  ('231488',19,'2010-12-08',123,1,7),
  ('231490',19,'2010-12-08',213,1,7),
  ('231499',19,'2010-12-08',213,1,7),
  ('231434',24,'2010-12-08',0,1,7),
  ('231455',24,'2010-12-08',0,1,7),
  ('231478',24,'2010-12-08',0,1,7),
  ('231488',24,'2010-12-08',0,1,7),
  ('231490',24,'2010-12-08',0,1,7),
  ('231499',24,'2010-12-08',0,1,7),
  ('231434',25,'2010-12-08',123,1,7),
  ('231455',25,'2010-12-08',0,1,7),
  ('231478',25,'2010-12-08',0,1,7),
  ('231488',25,'2010-12-08',0,1,7),
  ('231490',25,'2010-12-08',0,1,7),
  ('231499',25,'2010-12-08',0,1,7),
  ('231434',27,'2010-12-08',0,1,7),
  ('231455',27,'2010-12-08',0,1,7),
  ('231478',27,'2010-12-08',123,1,7),
  ('231488',27,'2010-12-08',123,1,7),
  ('231490',27,'2010-12-08',0,1,7),
  ('231499',27,'2010-12-08',0,1,7),
  ('231434',34,'2010-12-08',234,1,7),
  ('231455',34,'2010-12-08',234,1,7),
  ('231478',34,'2010-12-08',123,1,7),
  ('231488',34,'2010-12-08',234,1,7),
  ('231490',34,'2010-12-08',1234,1,7),
  ('231499',34,'2010-12-08',1234,1,7),
  ('231434',36,'2010-12-08',0,1,7),
  ('231455',36,'2010-12-08',3456,1,7),
  ('231478',36,'2010-12-08',1234,1,7),
  ('231488',36,'2010-12-08',1234,1,7),
  ('231490',36,'2010-12-08',3214,1,7),
  ('231499',36,'2010-12-08',3456,1,7),
  ('231434',1,'2011-02-02',24592,1,7),
  ('231455',1,'2011-02-02',24592,1,7),
  ('231456',1,'2011-02-02',23000,1,7),
  ('231478',1,'2011-02-02',24592,1,7),
  ('231488',1,'2011-02-02',24592,1,7),
  ('231434',2,'2011-02-02',32145,1,7),
  ('231455',2,'2011-02-02',12345,1,7),
  ('231456',2,'2011-02-02',23456,1,7),
  ('231478',2,'2011-02-02',32145,1,7),
  ('231488',2,'2011-02-02',32145,1,7);
COMMIT;

#
# Data for the `salary_data` table  (LIMIT 500,500)
#

INSERT INTO `salary_data` (`sd_emp_code`, `sd_head_code`, `sd_date`, `sd_amount`, `sd_sess_id`, `org_code`) VALUES 
  ('231434',4,'2011-02-02',40283,1,7),
  ('231455',4,'2011-02-02',26225,1,7),
  ('231456',4,'2011-02-02',32984,1,7),
  ('231478',4,'2011-02-02',40283,1,7),
  ('231488',4,'2011-02-02',40283,1,7),
  ('231434',5,'2011-02-02',8511,1,7),
  ('231455',5,'2011-02-02',5541,1,7),
  ('231456',5,'2011-02-02',6968,1,7),
  ('231478',5,'2011-02-02',8511,1,7),
  ('231488',5,'2011-02-02',8511,1,7),
  ('231434',6,'2011-02-02',0,1,7),
  ('231455',6,'2011-02-02',0,1,7),
  ('231456',6,'2011-02-02',0,1,7),
  ('231478',6,'2011-02-02',0,1,7),
  ('231488',6,'2011-02-02',0,1,7),
  ('231434',12,'2011-02-02',8082,1,7),
  ('231455',12,'2011-02-02',5261,1,7),
  ('231456',12,'2011-02-02',6617,1,7),
  ('231478',12,'2011-02-02',8082,1,7),
  ('231488',12,'2011-02-02',8082,1,7),
  ('231434',13,'2011-02-02',0,1,7),
  ('231455',13,'2011-02-02',0,1,7),
  ('231456',13,'2011-02-02',0,1,7),
  ('231478',13,'2011-02-02',0,1,7),
  ('231488',13,'2011-02-02',0,1,7),
  ('231434',18,'2011-02-02',0,1,7),
  ('231455',18,'2011-02-02',0,1,7),
  ('231456',18,'2011-02-02',0,1,7),
  ('231478',18,'2011-02-02',0,1,7),
  ('231488',18,'2011-02-02',0,1,7),
  ('231434',19,'2011-02-02',0,1,7),
  ('231455',19,'2011-02-02',0,1,7),
  ('231456',19,'2011-02-02',0,1,7),
  ('231478',19,'2011-02-02',0,1,7),
  ('231488',19,'2011-02-02',0,1,7),
  ('231434',24,'2011-02-02',0,1,7),
  ('231455',24,'2011-02-02',0,1,7),
  ('231456',24,'2011-02-02',0,1,7),
  ('231478',24,'2011-02-02',0,1,7),
  ('231488',24,'2011-02-02',0,1,7),
  ('231434',25,'2011-02-02',0,1,7),
  ('231455',25,'2011-02-02',0,1,7),
  ('231456',25,'2011-02-02',0,1,7),
  ('231478',25,'2011-02-02',0,1,7),
  ('231488',25,'2011-02-02',0,1,7),
  ('231434',27,'2011-02-02',0,1,7),
  ('231455',27,'2011-02-02',0,1,7),
  ('231456',27,'2011-02-02',0,1,7),
  ('231478',27,'2011-02-02',0,1,7),
  ('231488',27,'2011-02-02',0,1,7),
  ('231434',34,'2011-02-02',0,1,7),
  ('231455',34,'2011-02-02',0,1,7),
  ('231456',34,'2011-02-02',0,1,7),
  ('231478',34,'2011-02-02',0,1,7),
  ('231488',34,'2011-02-02',0,1,7),
  ('231434',36,'2011-02-02',0,1,7),
  ('231455',36,'2011-02-02',0,1,7),
  ('231456',36,'2011-02-02',0,1,7),
  ('231478',36,'2011-02-02',0,1,7),
  ('231488',36,'2011-02-02',0,1,7),
  ('0191',1,'2011-03-08',10000,1,1),
  ('0191',2,'2011-03-08',2134,1,1),
  ('0191',4,'2011-03-08',8615,1,1),
  ('0191',5,'2011-03-08',1820,1,1),
  ('0191',12,'2011-03-08',1728,1,1),
  ('0191',13,'2011-03-08',2000,1,1),
  ('0191',19,'2011-03-08',0,1,1),
  ('0191',34,'2011-03-08',300,1,1),
  ('0191',1,'2012-03-06',10000,2,1),
  ('0191',2,'2012-03-06',2134,2,1),
  ('0191',4,'2012-03-06',8615,2,1),
  ('0191',5,'2012-03-06',1820,2,1),
  ('0191',12,'2012-03-06',1728,2,1),
  ('0191',13,'2012-03-06',3000,2,1),
  ('0191',19,'2012-03-06',280,2,1),
  ('0191',34,'2012-03-06',600,2,1),
  ('0191',1,'2012-04-05',10000,3,1),
  ('098',1,'2012-04-05',20000,3,1),
  ('0191',2,'2012-04-05',2134,3,1),
  ('098',2,'2012-04-05',2134,3,1),
  ('0191',4,'2012-04-05',8615,3,1),
  ('098',4,'2012-04-05',15715,3,1),
  ('0191',5,'2012-04-05',1820,3,1),
  ('098',5,'2012-04-05',3320,3,1),
  ('0191',12,'2012-04-05',1728,3,1),
  ('098',12,'2012-04-05',3153,3,1),
  ('0191',13,'2012-04-05',0,3,1),
  ('098',13,'2012-04-05',0,3,1),
  ('0191',19,'2012-04-05',0,3,1),
  ('098',19,'2012-04-05',123,3,1),
  ('0191',34,'2012-04-05',600,3,1),
  ('098',34,'2012-04-05',231,3,1),
  ('110',1,'2012-04-12',10000,3,1),
  ('1234',1,'2012-04-12',1234,3,1),
  ('110',2,'2012-04-12',2134,3,1),
  ('1234',2,'2012-04-12',2134,3,1),
  ('110',4,'2012-04-12',8615,3,1),
  ('1234',4,'2012-04-12',2391,3,1),
  ('110',5,'2012-04-12',1820,3,1),
  ('1234',5,'2012-04-12',505,3,1),
  ('110',12,'2012-04-12',1728,3,1),
  ('1234',12,'2012-04-12',480,3,1),
  ('110',13,'2012-04-12',0,3,1),
  ('1234',13,'2012-04-12',0,3,1),
  ('110',19,'2012-04-12',123,3,1),
  ('1234',19,'2012-04-12',321,3,1),
  ('110',34,'2012-04-12',321,3,1),
  ('1234',34,'2012-04-12',123,3,1),
  ('0191',1,'2012-05-16',10000,3,1),
  ('098',1,'2012-05-16',20000,3,1),
  ('110',1,'2012-05-16',10000,3,1),
  ('1234',1,'2012-05-16',1234,3,1),
  ('0191',2,'2012-05-16',2134,3,1),
  ('098',2,'2012-05-16',2134,3,1),
  ('110',2,'2012-05-16',2134,3,1),
  ('1234',2,'2012-05-16',2134,3,1),
  ('0191',4,'2012-05-16',8615,3,1),
  ('098',4,'2012-05-16',15715,3,1),
  ('110',4,'2012-05-16',8615,3,1),
  ('1234',4,'2012-05-16',2391,3,1),
  ('0191',5,'2012-05-16',1820,3,1),
  ('098',5,'2012-05-16',3320,3,1),
  ('110',5,'2012-05-16',1820,3,1),
  ('1234',5,'2012-05-16',505,3,1),
  ('0191',12,'2012-05-16',1728,3,1),
  ('098',12,'2012-05-16',3153,3,1),
  ('110',12,'2012-05-16',1728,3,1),
  ('1234',12,'2012-05-16',480,3,1),
  ('0191',13,'2012-05-16',0,3,1),
  ('098',13,'2012-05-16',0,3,1),
  ('110',13,'2012-05-16',0,3,1),
  ('1234',13,'2012-05-16',0,3,1),
  ('0191',19,'2012-05-16',321,3,1),
  ('098',19,'2012-05-16',321,3,1),
  ('110',19,'2012-05-16',123,3,1),
  ('1234',19,'2012-05-16',321,3,1),
  ('0191',34,'2012-05-16',123,3,1),
  ('098',34,'2012-05-16',123,3,1),
  ('110',34,'2012-05-16',321,3,1),
  ('1234',34,'2012-05-16',123,3,1),
  ('0191',1,'2012-06-06',10000,3,1),
  ('0191',2,'2012-06-06',2134,3,1),
  ('0191',4,'2012-06-06',8615,3,1),
  ('0191',5,'2012-06-06',1820,3,1),
  ('0191',12,'2012-06-06',1728,3,1),
  ('0191',13,'2012-06-06',300,3,1),
  ('0191',19,'2012-06-06',280,3,1),
  ('0191',34,'2012-06-06',300,3,1),
  ('0191',1,'2012-07-04',10000,2,1),
  ('0191',2,'2012-07-04',2134,2,1),
  ('0191',4,'2012-07-04',8615,2,1),
  ('0191',5,'2012-07-04',1820,2,1),
  ('0191',12,'2012-07-04',1728,2,1),
  ('0191',13,'2012-07-04',0,2,1),
  ('0191',19,'2012-07-04',200,2,1),
  ('0191',34,'2012-07-04',300,2,1),
  ('0191',1,'2012-08-30',10000,2,1),
  ('0191',2,'2012-08-30',2134,2,1),
  ('0191',4,'2012-08-30',8615,2,1),
  ('0191',5,'2012-08-30',1820,2,1),
  ('0191',12,'2012-08-30',1728,2,1),
  ('0191',13,'2012-08-30',1200,2,1),
  ('0191',19,'2012-08-30',500,2,1),
  ('0191',34,'2012-08-30',1000,2,1);
COMMIT;

#
# Data for the `salary_formula` table  (LIMIT 0,500)
#

INSERT INTO `salary_formula` (`sf_sal_id`, `sf_sal_formula`, `sf_org_id`) VALUES 
  (3,'(basic+gp)*.25',7),
  (4,'(gp+basic)*.71',7),
  (5,'(basic+gp)*.15',7),
  (9,'(basic+da+gp)*.0833',7),
  (12,'(basic+da+gp+arr+npa)*.0833',7),
  (37,'',7);
COMMIT;

#
# Data for the `salary_profile_master` table  (LIMIT 0,500)
#

INSERT INTO `salary_profile_master` (`sp_id`, `sp_name`) VALUES 
  (1,'Regular'),
  (2,'Contractual'),
  (3,'Weekly'),
  (4,'BI WEEKLY'),
  (5,'TRIWEEKLY'),
  (8,'DAILY WAGES');
COMMIT;

#
# Data for the `salary_type` table  (LIMIT 0,500)
#

INSERT INTO `salary_type` (`st_id`, `st_name`) VALUES 
  (1,'TDS'),
  (2,'PF'),
  (3,'General Salary');
COMMIT;

#
# Data for the `slab_head` table  (LIMIT 0,500)
#

INSERT INTO `slab_head` (`sl_head_code`, `slab_head_name`, `sl_start_value`, `sl_end_value`, `sl_percent`, `sl_orgCode`) VALUES 
  (1,'s1',0,200000,0.000,1),
  (2,'s2',200001,500000,10.000,1);
COMMIT;

#
# Data for the `sms_profile` table  (LIMIT 0,500)
#

INSERT INTO `sms_profile` (`SMP_ID`, `SMP_NAME`, `SMP_USER`, `SMP_PASSWORD`, `SMP_SENDER`, `sms_current`) VALUES 
  (1,'SMS Mantra','sushant001','8734627','ALGOX',1);
COMMIT;

#
# Data for the `system_master` table  (LIMIT 0,500)
#

INSERT INTO `system_master` (`ms_id`, `ms_password`) VALUES 
  (2,'masteradmin');
COMMIT;

#
# Data for the `system_private_data` table  (LIMIT 0,500)
#

INSERT INTO `system_private_data` (`it_percent`, `pf_interest`) VALUES 
  (14.00,6.400);
COMMIT;

#
# Data for the `task_list` table  (LIMIT 0,500)
#

INSERT INTO `task_list` (`seq_no`, `task_id`, `task_name`, `task_menu_name`) VALUES 
  (45,'ad','Administration','Administration'),
  (46,'ad23','Manage Task','return loadIframe(''ifrm'',''userOperation/NewUserTask.jsf'')'),
  (47,'ad22','Assign User Task','return loadIframe(''ifrm'',''userOperation/NewUserTask.jsf'')'),
  (48,'ad21','Add Registered User','return loadIframe(''ifrm'',''userOperation/createUser.jsf'')'),
  (49,'ad2','Module Management','Module Management'),
  (50,'ad1','Salary Lock','return loadIframe(''ifrm'',''adm/Salary.jsf'')'),
  (51,'pf3','PF Account','return loadIframe(''ifrm'', ''pf/PFAccount.jsf'')'),
  (52,'pf2','PF Withdrawal','return loadIframe(''ifrm'', ''pf/PFWithdrawal.jsf'')'),
  (53,'pf1','Opening Balance','return loadIframe(''ifrm'', ''pf/OpeningBalance.jsf'')'),
  (54,'pf','PF Management','PF Management'),
  (55,'se9','System User Account','return loadIframe(''ifrm'', ''account/AddUser.jsf'')'),
  (56,'se8','Tax Slab Setup','Tax Slab Setup'),
  (57,'se7','Salary Head','return loadIframe(''ifrm'', ''setup/SalaryHead.jsf'')'),
  (58,'se6','Pay Scales','return loadIframe(''ifrm'', ''setup/SalaryGrade.jsf'')'),
  (59,'se5','Employee Types','return loadIframe(''ifrm'', ''EmployeeType.jsf'')'),
  (60,'se4','Add Bank Profile','return loadIframe(''ifrm'', ''setup/bankProfile.jsf'')'),
  (61,'se3','Designation','return loadIframe(''ifrm'', ''setup/Designation.jsf'')'),
  (62,'se2','Department','return loadIframe(''ifrm'', ''setup/Departments.jsf'')'),
  (63,'se1','Financial Year','return loadIframe(''ifrm'', ''setup/SessionSetup.jsf'')'),
  (64,'se','Setup','Setup'),
  (65,'se83','Employee Slab Details','return loadIframe(''ifrm'', ''TaxSlabSetup/EmployeeSlabValue.jsf'')'),
  (66,'se82','Add Slab Details','return loadIframe(''ifrm'', ''TaxSlabSetup/TaxSlabName.jsf'')'),
  (67,'se81','Add Gender','return loadIframe(''ifrm'', ''TaxSlabSetup/Gender.jsf'')'),
  (68,'sa','Salary','Salary'),
  (69,'sa4','Type Wise Salary Head Setting','return loadIframe(''ifrm'',''salary/SalarySettings.jsf'')'),
  (70,'sa3','Default Salary Values','return loadIframe(''ifrm'',''salary/DefaultSalaryData.jsf'')'),
  (71,'sa2','Salary Formula','return loadIframe(''ifrm'',''salary/SalaryFormula.jsf'')'),
  (72,'sa1','Salary Processing','return loadIframe(''ifrm'',''salary/MonthlySalaryProcessing.jsf'')'),
  (73,'re6','Concise PF Report','return loadIframe(''ifrm'',''report/ReportExporterConcise.jsf?fwdLink=APF_Compact.jsf'')'),
  (74,'re5','Annual PF Report','return loadIframe(''ifrm'',''report/ReportExporter.jsf?fwdLink=APF.jsf'')'),
  (75,'re4','Monthly Salary Roll','return loadIframe(''ifrm'',''report/ReportExporterMonthlyRoll.jsf?fwdLink=MonthlyPayroll.jsf'')'),
  (76,'re3','Individual Gross Salary','return loadIframe(''ifrm'',''salary/MonthlyIndividualGrossSalary.jsf'')'),
  (77,'re2','Monthly Salary Slip','return loadIframe(''ifrm'', ''report/ReportExporterMonthlySlip.jsf?fwdLink=MonthlySalarySingle.jsf'')'),
  (78,'re1','Bank Statement','return loadIframe(''ifrm'', ''report/ReportExporterBankStatement.jsf?fwdLink=BankStatement.jsf'')'),
  (79,'re','Report','Report'),
  (80,'em3','Search Profile','return loadIframe(''ifrm'', ''employee/SearchEmployee.jsf'')'),
  (81,'em2','Edit Profile','return loadIframe(''ifrm'', ''employee/EditEmployeeProfile.jsf'')'),
  (82,'em1','Add Profile','return loadIframe(''ifrm'', ''employee/EmployeeProfile.jsf'')'),
  (83,'em','Employee','Employee'),
  (84,'tm4','Annual Tax Calculator','return loadIframe(''ifrm'', ''investment/TaxCalculator.jsf'')'),
  (85,'tm3','Investment Form','return loadIframe(''ifrm'', ''investment/EmployeeInvestment.jsf'')'),
  (86,'tm2','Investment Heads','return loadIframe(''ifrm'', ''investment/InvestmentHead.jsf'')'),
  (87,'tm1','Investment Categories','return loadIframe(''ifrm'', ''investment/InvestmentCategory.jsf'')'),
  (88,'tm','Tax Management','Tax Management');
COMMIT;

#
# Data for the `tax_rep_master` table  (LIMIT 0,500)
#

INSERT INTO `tax_rep_master` (`tr_id`, `tr_month_code`, `tr_emp_code`, `tr_amount`, `tr_sess_id`) VALUES 
  (235,1,'0191',289.00,1),
  (236,1,'0191',289.00,1),
  (237,1,'0191',289.00,1),
  (238,1,'0191',289.00,1),
  (239,1,'0191',289.00,1),
  (240,1,'0191',289.00,1),
  (241,1,'0191',289.00,1),
  (242,1,'0191',289.00,1),
  (243,1,'0191',289.00,1),
  (244,1,'0191',289.00,1),
  (245,1,'0191',289.00,1),
  (246,1,'0191',289.00,1),
  (247,1,'0191',289.00,1),
  (248,1,'0191',289.00,1),
  (249,1,'0191',289.00,1),
  (250,1,'0191',289.00,1),
  (251,1,'0191',289.00,1),
  (252,1,'0191',289.00,1),
  (253,1,'0191',289.00,1),
  (254,1,'0191',289.00,1),
  (255,1,'0191',289.00,1),
  (256,1,'0191',289.00,1),
  (257,1,'0191',289.00,1),
  (258,1,'0191',289.00,1),
  (259,1,'0191',289.00,1),
  (260,1,'0191',289.00,1),
  (261,1,'0191',289.00,1),
  (262,1,'0191',289.00,1),
  (263,1,'0191',289.00,1),
  (264,1,'0191',289.00,1),
  (265,1,'0191',289.00,1),
  (266,1,'0191',289.00,1),
  (267,1,'0191',289.00,1),
  (268,1,'0191',289.00,1),
  (269,1,'0191',289.00,1),
  (270,1,'0191',289.00,1),
  (271,1,'0191',289.00,1),
  (272,1,'0191',289.00,1),
  (273,1,'0191',289.00,1),
  (274,1,'0191',289.00,1),
  (275,1,'0191',289.00,1),
  (276,1,'0191',289.00,1),
  (277,1,'0191',289.00,1),
  (278,1,'0191',289.00,1),
  (279,1,'0191',289.00,1),
  (280,1,'0191',289.00,1),
  (281,1,'0191',289.00,1),
  (282,1,'0191',289.00,1),
  (283,1,'0191',289.00,1),
  (284,1,'0191',289.00,1),
  (285,1,'0191',289.00,1),
  (286,1,'0191',289.00,1),
  (287,1,'0191',289.00,1),
  (288,1,'0191',289.00,1),
  (289,1,'0191',289.00,1),
  (290,1,'0191',289.00,1),
  (291,1,'0191',289.00,1),
  (292,1,'0191',289.00,1),
  (293,1,'0191',289.00,1),
  (294,1,'0191',289.00,1),
  (295,1,'0191',289.00,1),
  (296,1,'0191',289.00,1),
  (297,1,'0191',289.00,1),
  (298,1,'0191',289.00,1),
  (299,1,'0191',289.00,1),
  (300,1,'0191',289.00,1),
  (301,1,'0191',289.00,1),
  (302,1,'0191',289.00,1),
  (303,1,'0191',289.00,1),
  (304,1,'0191',289.00,1),
  (305,1,'0191',289.00,1),
  (306,1,'0191',289.00,1),
  (307,1,'0191',289.00,1),
  (308,1,'0191',289.00,1),
  (309,1,'0191',289.00,1),
  (310,1,'0191',289.00,1),
  (311,1,'0191',289.00,1),
  (312,1,'0191',289.00,1),
  (313,1,'0191',289.00,1),
  (314,1,'0191',289.00,1),
  (315,1,'0191',289.00,1),
  (316,1,'0191',289.00,1),
  (317,1,'0191',289.00,1),
  (318,1,'0191',289.00,1),
  (319,1,'0191',289.00,1),
  (320,1,'0191',289.00,1),
  (321,1,'0191',289.00,1),
  (322,1,'0191',289.00,1),
  (323,1,'0191',289.00,1),
  (324,1,'0191',289.00,1),
  (325,1,'0191',289.00,1),
  (326,1,'0191',289.00,1),
  (327,1,'0191',289.00,1),
  (328,1,'0191',289.00,1),
  (329,1,'0191',289.00,1),
  (330,1,'0191',289.00,1),
  (331,1,'0191',289.00,1),
  (332,1,'0191',289.00,1),
  (333,1,'0191',289.00,1),
  (334,1,'0191',289.00,1),
  (335,1,'0191',289.00,1),
  (336,1,'0191',289.00,1),
  (337,1,'0191',289.00,1),
  (338,1,'0191',289.00,1),
  (339,1,'0191',289.00,1),
  (340,1,'0191',289.00,1),
  (341,1,'0191',289.00,1),
  (342,1,'0191',289.00,1),
  (343,2,'0191',289.00,1),
  (344,3,'0191',289.00,1),
  (345,4,'0191',289.00,1),
  (346,5,'0191',289.00,1),
  (347,6,'0191',289.00,1),
  (348,7,'0191',289.00,1),
  (349,8,'0191',289.00,1),
  (350,9,'0191',289.00,1),
  (351,10,'0191',289.00,1),
  (352,11,'0191',289.00,1);
COMMIT;

#
# Data for the `taxslabs_master` table  (LIMIT 0,500)
#

INSERT INTO `taxslabs_master` (`ts_id`, `ts_start`, `ts_end`, `ts_percent`, `ts_gender`, `ts_name`) VALUES 
  (1,'0','200000',0,0,'s1'),
  (2,'200000','500000',10,0,'s2'),
  (3,'500001','1000000',20,0,'s3'),
  (4,'1000000','2000000',31,2,'s4');
COMMIT;

#
# Data for the `tds_installment_master` table  (LIMIT 0,500)
#

INSERT INTO `tds_installment_master` (`ti_emp_id`, `ti_year`, `ti_amount`) VALUES 
  ('EMP001',2011,5014.124);
COMMIT;

#
# Data for the `temp_sal_data` table  (LIMIT 0,500)
#

INSERT INTO `temp_sal_data` (`temp_code`, `basic`, `gp`, `npa`, `da`, `hra`, `arr`, `cpfe`, `cpf`, `tds`, `pfr`, `gsli`, `trans`, `mhd`, `ttn`, `hk`, `wtr`, `fur`, `ma`, `elctr`, `pfrecovery`, `DAarr`) VALUES 
  ('231434',24592,32145,NULL,40283,8511,0,NULL,8082,0,0,0,0,0,0,NULL,NULL,NULL,0,NULL,0,NULL),
  ('231455',24592,12345,NULL,26225,5541,0,NULL,5261,0,0,0,0,0,0,NULL,NULL,NULL,0,NULL,0,NULL),
  ('231456',23000,23456,NULL,32984,6968,0,NULL,6617,0,0,0,0,0,0,NULL,NULL,NULL,0,NULL,0,NULL),
  ('231478',24592,32145,NULL,40283,8511,0,NULL,8082,0,0,0,0,0,0,NULL,NULL,NULL,0,NULL,0,NULL),
  ('231488',24592,32145,NULL,40283,8511,0,NULL,8082,0,0,0,0,0,0,NULL,NULL,NULL,0,NULL,0,NULL);
COMMIT;

#
# Data for the `ts_gender` table  (LIMIT 0,500)
#

INSERT INTO `ts_gender` (`ts_seq`, `gender_name`, `orgCode`) VALUES 
  (1,'kkk',2),
  (2,'senior citizen',1),
  (3,'Super Senior',1);
COMMIT;

#
# Data for the `user_group_master` table  (LIMIT 0,500)
#

INSERT INTO `user_group_master` (`grp_id`, `grp_name`) VALUES 
  (1,'User'),
  (2,'Admin'),
  (3,'Super'),
  (4,'Master User'),
  (5,'Accounts');
COMMIT;

#
# Data for the `user_history_master` table  (LIMIT 0,500)
#

INSERT INTO `user_history_master` (`hs_id`, `hs_user_id`, `hs_date`, `hs_status`) VALUES 
  (1,307,'2011-05-29 11:14:50',1),
  (2,307,'2011-05-29 11:20:30',1),
  (3,307,'2011-05-29 12:01:54',1),
  (4,307,'2011-05-29 12:02:52',1),
  (5,307,'2011-05-29 12:04:31',1),
  (6,307,'2011-05-29 12:04:52',1),
  (7,307,'2011-05-29 12:06:58',1),
  (8,307,'2011-05-29 12:08:24',1),
  (9,307,'2011-05-29 12:09:53',1),
  (10,307,'2011-05-29 12:10:29',1),
  (11,307,'2011-05-29 12:10:43',1),
  (12,307,'2011-05-29 12:11:22',1),
  (13,307,'2011-05-29 12:11:40',1),
  (14,307,'2011-05-29 12:17:22',1),
  (15,307,'2011-05-29 12:18:13',1),
  (16,307,'2011-05-29 12:20:12',1),
  (17,307,'2011-05-29 13:06:38',1),
  (18,307,'2011-05-29 13:07:04',1),
  (19,307,'2011-05-29 13:10:52',1),
  (20,307,'2011-05-29 13:14:58',1),
  (21,307,'2011-05-29 13:27:51',1),
  (22,307,'2011-05-29 13:36:21',1),
  (23,307,'2011-05-29 13:36:57',1),
  (24,307,'2011-05-29 13:38:27',1),
  (25,307,'2011-05-29 13:39:18',1),
  (26,307,'2011-05-29 13:46:46',1),
  (27,307,'2011-05-29 13:47:12',1),
  (28,307,'2011-05-29 13:49:04',1),
  (29,307,'2011-05-29 13:49:50',1),
  (30,307,'2011-05-29 13:55:50',1),
  (31,307,'2011-05-29 13:58:24',1),
  (32,307,'2011-05-29 14:05:11',1),
  (33,307,'2011-05-29 14:05:44',1),
  (34,307,'2011-05-29 14:40:38',1),
  (35,307,'2011-05-29 14:44:09',1),
  (36,307,'2011-05-29 14:45:47',1),
  (37,307,'2011-05-29 14:46:57',1),
  (38,307,'2011-05-29 14:47:52',1),
  (39,307,'2011-05-29 14:48:33',1),
  (40,307,'2011-05-30 00:03:54',1),
  (41,307,'2011-05-30 00:10:52',1),
  (42,307,'2011-05-30 00:12:46',1),
  (43,307,'2011-05-30 01:33:38',1),
  (44,307,'2011-05-30 01:36:16',1),
  (45,307,'2011-05-30 01:40:02',1),
  (46,307,'2011-05-30 10:14:40',1),
  (47,307,'2011-05-30 10:17:49',1),
  (48,307,'2011-05-30 10:20:09',1),
  (49,307,'2011-05-30 10:22:25',1),
  (50,307,'2011-05-30 10:41:00',1),
  (51,307,'2011-05-30 10:45:13',1),
  (52,307,'2011-05-30 10:46:14',1),
  (53,307,'2011-05-30 10:56:19',1),
  (54,307,'2011-05-30 10:58:52',1),
  (55,307,'2011-05-30 11:00:41',1),
  (56,307,'2011-05-30 14:45:59',1),
  (57,307,'2011-05-30 14:47:03',1),
  (58,307,'2011-05-30 15:00:38',1),
  (59,307,'2011-05-30 15:02:10',1),
  (60,307,'2011-05-30 16:34:45',1),
  (61,307,'2011-05-30 17:00:23',1),
  (62,307,'2011-05-30 17:01:16',1),
  (63,307,'2011-05-30 17:07:57',1),
  (64,307,'2011-05-30 17:10:41',1),
  (65,307,'2011-05-31 00:51:42',1),
  (66,307,'2011-05-31 06:21:35',1),
  (67,307,'2011-05-31 06:42:08',1),
  (68,307,'2011-05-31 06:45:34',1),
  (69,307,'2011-05-31 07:20:01',1),
  (70,307,'2011-05-31 07:35:17',1),
  (71,307,'2011-05-31 07:48:19',1),
  (72,307,'2011-05-31 07:51:02',1),
  (73,307,'2011-05-31 08:01:41',1),
  (74,307,'2011-05-31 08:06:43',1),
  (75,307,'2011-05-31 08:48:16',1),
  (76,307,'2011-05-31 08:55:10',1),
  (77,307,'2011-05-31 08:57:05',1),
  (78,307,'2011-05-31 09:03:22',1),
  (79,307,'2011-05-31 09:05:16',1),
  (80,307,'2011-05-31 09:15:09',1),
  (81,307,'2011-05-31 09:16:34',1),
  (82,307,'2011-05-31 09:24:50',1),
  (83,307,'2011-05-31 09:28:35',1),
  (84,307,'2011-05-31 09:51:50',1),
  (85,307,'2011-05-31 09:55:56',1),
  (86,307,'2011-05-31 09:58:55',1),
  (87,307,'2011-05-31 10:15:56',1),
  (88,307,'2011-05-31 10:35:37',1),
  (89,307,'2011-05-31 11:06:17',1),
  (90,307,'2011-05-31 11:37:45',1),
  (91,307,'2011-05-31 11:48:05',1),
  (92,307,'2011-05-31 11:52:46',1),
  (93,307,'2011-05-31 11:55:57',1),
  (94,307,'2011-05-31 12:01:09',1),
  (95,307,'2011-05-31 12:03:46',1),
  (96,307,'2011-05-31 12:14:15',1),
  (97,307,'2011-05-31 12:22:19',1),
  (98,307,'2011-05-31 12:37:59',1),
  (99,312,'2011-05-31 13:16:07',1),
  (100,307,'2011-05-31 13:16:43',1),
  (101,312,'2011-05-31 13:17:20',1),
  (102,312,'2011-05-31 13:33:56',1),
  (103,312,'2011-05-31 13:34:56',1),
  (104,312,'2011-05-31 13:37:33',1),
  (105,312,'2011-05-31 13:40:24',1),
  (106,312,'2011-05-31 13:57:03',1),
  (107,312,'2011-05-31 13:59:33',1),
  (108,307,'2011-05-31 18:04:39',1),
  (109,315,'2011-05-31 22:57:40',1),
  (110,315,'2011-05-31 23:01:53',1),
  (111,315,'2011-05-31 23:06:09',1),
  (112,315,'2011-05-31 23:07:10',1),
  (113,315,'2011-05-31 23:17:27',1),
  (114,315,'2011-05-31 23:21:17',1),
  (115,315,'2011-05-31 23:35:10',1),
  (116,315,'2011-05-31 23:37:50',1),
  (117,315,'2011-05-31 23:40:54',1),
  (118,315,'2011-05-31 23:47:32',1),
  (119,315,'2011-05-31 23:50:56',1),
  (120,315,'2011-05-31 23:54:47',1),
  (121,315,'2011-06-01 00:00:29',1),
  (122,315,'2011-06-01 00:06:29',1),
  (123,315,'2011-06-01 00:09:45',1),
  (124,315,'2011-06-01 00:41:27',1),
  (125,315,'2011-06-01 01:33:38',1),
  (126,315,'2011-06-01 02:26:28',1),
  (127,315,'2011-06-01 02:40:33',1),
  (128,315,'2011-06-01 02:56:40',1),
  (129,315,'2011-06-01 03:06:27',1),
  (130,315,'2011-06-01 10:25:03',1),
  (131,315,'2011-06-01 10:29:01',1),
  (132,315,'2011-06-01 10:30:00',1),
  (133,315,'2011-06-01 10:30:38',1),
  (134,315,'2011-06-01 10:42:45',1),
  (135,315,'2011-06-01 10:46:08',1),
  (136,315,'2011-06-01 10:49:17',1),
  (137,315,'2011-06-01 10:56:38',1),
  (138,315,'2011-06-01 11:03:43',1),
  (139,315,'2011-06-01 11:05:50',1),
  (140,315,'2011-06-01 11:08:26',1),
  (141,315,'2011-06-01 11:10:36',1),
  (142,315,'2011-06-01 11:12:22',1),
  (143,315,'2011-06-01 11:16:19',1),
  (144,315,'2011-06-01 11:27:33',1),
  (145,315,'2011-06-01 11:29:14',1),
  (146,315,'2011-06-01 11:31:04',1),
  (147,2,'2011-06-01 17:47:01',1),
  (148,1,'2011-06-01 18:00:04',1),
  (149,1,'2011-06-01 18:01:14',1),
  (150,317,'2011-06-01 18:26:43',1),
  (151,317,'2011-06-01 18:33:04',1),
  (152,317,'2011-06-01 18:36:20',1),
  (153,317,'2011-06-01 18:37:49',1),
  (154,318,'2011-06-01 18:39:55',1),
  (155,317,'2011-06-01 18:42:41',1),
  (156,317,'2011-06-01 18:43:21',1),
  (157,317,'2011-06-01 18:57:16',1),
  (158,317,'2011-06-01 18:58:02',1),
  (159,317,'2011-06-01 18:58:39',1),
  (160,317,'2011-06-01 18:59:11',1),
  (161,317,'2011-06-01 19:00:21',1),
  (162,317,'2011-06-01 19:02:58',1),
  (163,317,'2011-06-01 19:35:43',1),
  (164,317,'2011-06-01 19:37:04',1),
  (165,317,'2011-06-01 19:37:15',1),
  (166,317,'2011-06-01 19:38:01',1),
  (167,317,'2011-06-01 19:39:00',1),
  (168,2,'2011-06-01 20:21:45',1),
  (169,319,'2011-06-01 20:23:46',1),
  (170,2,'2011-06-01 20:24:13',1),
  (171,2,'2011-06-01 20:24:39',1),
  (172,319,'2011-06-01 20:29:24',1),
  (173,2,'2011-06-01 20:29:53',1),
  (174,2,'2011-06-01 20:30:08',1),
  (175,2,'2011-06-01 20:30:27',1),
  (176,319,'2011-06-01 20:32:23',1),
  (177,2,'2011-06-01 20:33:54',1),
  (178,2,'2011-06-01 20:34:52',1),
  (179,2,'2011-06-01 20:44:43',1),
  (180,2,'2011-06-01 20:55:41',1),
  (181,2,'2011-06-01 21:00:14',1),
  (182,2,'2011-06-01 21:12:58',1),
  (183,2,'2011-06-01 21:15:16',1),
  (184,2,'2011-06-01 21:24:11',1),
  (185,2,'2011-06-01 21:28:24',1),
  (186,319,'2011-06-01 21:43:20',1),
  (187,2,'2011-06-01 23:34:59',1),
  (188,319,'2011-06-01 23:36:02',1),
  (189,319,'2011-06-01 23:42:07',1),
  (190,319,'2011-06-01 23:43:31',1),
  (191,319,'2011-06-01 23:45:13',1),
  (192,319,'2011-06-01 23:47:04',1),
  (193,319,'2011-06-02 01:17:10',1),
  (194,319,'2011-06-02 01:18:10',1),
  (195,319,'2011-06-02 01:31:02',1),
  (196,319,'2011-06-02 01:51:11',1),
  (197,319,'2011-06-02 01:53:16',1),
  (198,319,'2011-06-02 01:54:42',1),
  (199,319,'2011-06-02 02:01:04',1),
  (200,2,'2011-06-02 02:44:41',1),
  (201,319,'2011-06-02 02:53:10',1),
  (202,2,'2011-06-02 02:53:43',1),
  (203,2,'2011-06-02 02:59:47',1),
  (204,2,'2011-06-02 03:34:12',1),
  (205,2,'2011-06-01 03:37:30',1),
  (206,2,'2011-06-01 03:43:51',1),
  (207,2,'2011-06-01 03:46:34',1),
  (208,2,'2011-06-01 03:59:05',1),
  (209,2,'2011-06-01 04:22:21',1),
  (210,2,'2011-06-01 04:25:51',1),
  (211,2,'2011-06-01 04:30:45',1),
  (212,2,'2011-06-01 04:35:00',1),
  (213,319,'2011-06-01 04:40:32',1),
  (214,2,'2011-06-01 04:47:24',1),
  (215,319,'2011-06-01 04:54:12',1),
  (216,319,'2011-06-01 04:58:43',1),
  (217,319,'2011-06-01 05:01:33',1),
  (218,319,'2011-06-01 05:02:32',1),
  (219,2,'2011-06-01 05:02:58',1),
  (220,319,'2011-06-01 05:04:05',1),
  (221,2,'2011-06-01 05:04:51',1),
  (222,319,'2011-06-01 05:06:13',1),
  (223,319,'2011-06-01 05:12:43',1),
  (224,2,'2011-06-01 05:16:17',1),
  (225,2,'2011-06-01 05:16:32',1),
  (226,2,'2011-06-01 05:16:52',1),
  (227,2,'2011-06-01 05:17:15',1),
  (228,2,'2011-06-01 05:18:30',1),
  (229,2,'2011-06-01 05:19:51',1),
  (230,2,'2011-06-01 05:23:06',1),
  (231,319,'2011-06-01 05:25:09',1),
  (232,2,'2011-06-01 05:25:42',1),
  (233,2,'2011-06-01 05:26:10',1),
  (234,2,'2011-06-01 05:26:59',1),
  (235,319,'2011-06-01 05:30:09',1),
  (236,2,'2011-06-01 05:33:41',1),
  (237,2,'2011-06-01 05:35:27',1),
  (238,2,'2011-06-01 05:36:53',1),
  (239,319,'2011-06-01 05:46:21',1),
  (240,2,'2011-06-01 05:48:31',1),
  (241,2,'2011-06-01 05:48:59',1),
  (242,319,'2011-06-01 05:51:32',1),
  (243,2,'2011-06-01 05:52:24',1),
  (244,2,'2011-06-01 05:53:10',1),
  (245,2,'2011-06-01 05:54:54',1),
  (246,2,'2011-06-01 05:58:44',1),
  (247,317,'2011-06-01 07:29:05',1),
  (248,2,'2011-06-01 09:47:37',1),
  (249,319,'2011-06-01 09:49:58',1),
  (250,2,'2011-06-01 09:51:06',1),
  (251,2,'2011-06-01 09:53:33',1),
  (252,2,'2011-06-01 09:59:02',1),
  (253,2,'2011-06-01 10:02:58',1),
  (254,2,'2011-06-01 10:06:17',1),
  (255,2,'2011-06-01 10:09:32',1),
  (256,2,'2011-06-01 10:12:22',1),
  (257,2,'2011-06-01 10:23:53',1),
  (258,2,'2011-06-01 10:24:36',1),
  (259,319,'2011-06-01 10:32:47',1),
  (260,2,'2011-06-01 10:34:21',1),
  (261,2,'2011-06-01 10:34:53',1),
  (262,319,'2011-06-01 10:35:27',1),
  (263,2,'2011-06-01 11:15:02',1),
  (264,2,'2011-06-01 11:22:27',1),
  (265,2,'2011-06-01 11:27:13',1),
  (266,2,'2011-06-01 11:36:16',1),
  (267,2,'2011-06-01 12:20:50',1),
  (268,319,'2011-06-01 12:58:11',1),
  (269,2,'2011-06-01 12:59:12',1),
  (270,2,'2011-06-01 13:04:18',1),
  (271,2,'2011-06-01 13:06:22',1),
  (272,2,'2011-06-01 13:19:45',1),
  (273,2,'2011-06-01 13:21:37',1),
  (274,2,'2011-06-01 13:57:22',1),
  (275,2,'2011-06-02 09:30:51',1),
  (276,2,'2011-06-02 10:41:10',1),
  (277,2,'2011-06-02 10:43:05',1),
  (278,319,'2011-06-02 10:58:16',1),
  (279,2,'2011-06-02 14:12:26',1),
  (280,317,'2011-06-02 21:51:13',1),
  (281,317,'2011-06-02 23:00:51',1),
  (282,2,'2011-06-02 23:10:05',1),
  (283,2,'2011-06-03 10:05:02',1),
  (284,2,'2011-06-03 10:11:37',1),
  (285,2,'2011-06-03 10:15:37',1),
  (286,2,'2011-06-03 12:26:37',1),
  (287,2,'2011-06-03 13:25:26',1),
  (288,2,'2011-06-03 13:27:12',1),
  (289,2,'2011-06-03 13:36:47',1),
  (290,2,'2011-06-03 13:57:00',1),
  (291,2,'2011-06-03 14:20:04',1),
  (292,2,'2011-06-03 14:42:39',1),
  (293,2,'2011-06-03 14:43:49',1),
  (294,2,'2011-06-03 14:45:12',1),
  (295,2,'2011-06-03 14:47:46',1),
  (296,2,'2011-06-03 18:24:52',1),
  (297,2,'2011-06-03 18:41:42',1),
  (298,2,'2011-06-03 18:43:41',1),
  (299,2,'2011-06-03 18:46:35',1),
  (300,2,'2011-06-03 19:29:17',1),
  (301,2,'2011-06-03 20:10:51',1),
  (302,2,'2011-06-03 20:12:55',1),
  (303,2,'2011-06-03 22:48:12',1),
  (304,2,'2011-06-03 23:03:38',1),
  (305,2,'2011-06-03 23:56:52',1),
  (306,2,'2011-06-04 00:00:36',1),
  (307,317,'2011-06-04 00:09:46',1),
  (308,2,'2011-06-04 00:20:38',1),
  (309,319,'2011-06-04 00:32:05',1),
  (310,319,'2011-06-04 00:33:26',1),
  (311,319,'2011-06-04 00:33:38',1),
  (312,317,'2011-06-04 00:38:15',1),
  (313,317,'2011-06-04 00:57:43',1),
  (314,317,'2011-06-04 08:40:32',1),
  (315,2,'2011-06-04 08:46:44',1),
  (316,319,'2011-06-04 08:53:04',1),
  (317,2,'2011-06-04 21:27:34',1),
  (318,2,'2011-06-04 21:31:45',1),
  (319,317,'2011-06-04 22:21:31',1),
  (320,2,'2011-06-04 22:22:03',1),
  (321,2,'2011-06-04 22:43:42',1),
  (322,2,'2011-06-04 22:50:12',1),
  (323,2,'2011-06-04 23:18:35',1),
  (324,2,'2011-06-04 23:22:39',1),
  (325,2,'2011-06-04 23:30:12',1),
  (326,2,'2011-06-04 23:33:35',1),
  (327,2,'2011-06-04 23:51:53',1),
  (328,2,'2011-06-05 00:29:47',1),
  (329,2,'2011-06-05 00:37:38',1),
  (330,2,'2011-06-05 00:41:41',1),
  (331,2,'2011-06-05 01:06:23',1),
  (332,317,'2011-06-05 01:18:50',1),
  (333,317,'2011-06-05 01:28:26',1),
  (334,2,'2011-06-05 03:06:40',1),
  (335,2,'2011-06-05 03:12:31',1),
  (336,2,'2011-06-05 03:14:39',1),
  (337,2,'2011-06-05 03:29:12',1),
  (338,2,'2011-06-05 03:30:45',1),
  (339,319,'2011-06-05 03:43:32',1),
  (340,2,'2011-06-05 03:45:08',1),
  (341,2,'2011-06-05 03:45:33',1),
  (342,2,'2011-06-05 03:46:21',1),
  (343,2,'2011-06-05 04:39:04',1),
  (344,2,'2011-06-05 04:52:31',1),
  (345,2,'2011-06-05 06:03:24',1),
  (346,2,'2011-06-05 06:45:13',1),
  (347,2,'2011-06-05 07:14:28',1),
  (348,2,'2011-06-05 08:24:15',1),
  (349,2,'2011-06-05 09:24:33',1),
  (350,2,'2011-06-05 09:55:10',1),
  (351,2,'2011-06-05 09:57:50',1),
  (352,2,'2011-06-05 10:30:30',1),
  (353,2,'2011-06-05 19:19:09',1),
  (354,2,'2011-06-05 19:41:03',1),
  (355,2,'2011-06-05 20:00:11',1),
  (356,2,'2011-06-05 20:12:20',1),
  (357,2,'2011-06-05 20:27:46',1),
  (358,2,'2011-06-06 20:39:31',1),
  (359,2,'2011-06-06 21:14:31',1),
  (360,319,'2011-06-06 21:14:53',1),
  (361,319,'2011-06-06 21:21:45',1),
  (362,319,'2011-06-06 21:33:53',1),
  (363,319,'2011-06-06 21:36:32',1),
  (364,319,'2011-06-06 21:39:16',1),
  (365,2,'2011-06-06 21:39:46',1),
  (366,2,'2011-06-06 21:41:42',1),
  (367,2,'2011-06-06 21:52:01',1),
  (368,2,'2011-06-06 21:55:45',1),
  (369,2,'2011-06-06 21:57:52',1),
  (370,2,'2011-06-06 22:01:13',1),
  (371,2,'2011-06-06 22:09:31',1),
  (372,2,'2011-06-06 22:10:18',1),
  (373,2,'2011-06-06 22:11:39',1),
  (374,2,'2011-06-06 22:18:22',1);
COMMIT;

#
# Data for the `user_master` table  (LIMIT 0,500)
#

INSERT INTO `user_master` (`user_id`, `user_name`, `user_pass`, `user_org_id`, `user_grp_id`, `user_profile_id`, `flag`) VALUES 
  (1,'admin@gmail.com','654321',1,4,0,1),
  (2,'stan@stan.ac.in','123123',2,4,0,1),
  (3,'nksinghiitk@gmail.com','1212',7,4,0,1);
COMMIT;

#
# Data for the `user_task_group` table  (LIMIT 0,500)
#

INSERT INTO `user_task_group` (`seq_id`, `user_task_id`, `user_org_code`, `user_task_password`, `user_grp_id`, `user_task_flag`) VALUES 
  (1,'21345',7,'12341234',4,1);
COMMIT;

#
# Data for the `user_task_list` table  (LIMIT 0,500)
#

INSERT INTO `user_task_list` (`seq_no`, `user_id`, `task_id`, `org_code`, `menu_item_name`, `flag`) VALUES 
  (1,'21345','ad',7,NULL,NULL),
  (2,'21345','ad23',7,NULL,NULL),
  (3,'21345','ad21',7,NULL,NULL),
  (4,'21345','pf3',7,NULL,NULL),
  (5,'21345','pf2',7,NULL,NULL),
  (6,'21345','pf1',7,NULL,NULL),
  (7,'21345','pf',7,NULL,NULL),
  (8,'21345','ad22',7,NULL,NULL),
  (9,'21345','ad1',7,NULL,1),
  (10,'21345','se9',7,NULL,1),
  (11,'21345','se7',7,NULL,1);
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;