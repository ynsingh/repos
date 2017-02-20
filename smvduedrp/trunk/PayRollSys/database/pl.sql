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
 /* `smtp_name` varchar(300) NOT NULL,*/
  `smtp_port` int(11) default NULL,
  `auth_emailid` varchar(300) default NULL,
  `auth_password` varchar(100) default NULL,
  `smtp_status` tinyint(4) default NULL,
  `smtp_host_name` varchar(200) default NULL,
  `mail_from` varchar(100) default NULL,
  PRIMARY KEY  (`seq_id`),
  UNIQUE KEY `smtp_host_name` (`smtp_host_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `org_profile` table : 
#
/*
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
*/

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
 /* `org_ll` int(20) NOT NULL,*/
  `org_countrycode` int(11) default NULL,
 /*`org_regioncode` int(11) NOT NULL,*/
  `org_institutedomain` varchar(100) default NULL,
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
  UNIQUE KEY `org_name_2` (`org_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


#
# Structure for the `department_master` table : 
#

CREATE TABLE `department_master` (
  `dept_code` int(11) NOT NULL auto_increment,
  `dept_dcode` varchar(80) NOT NULL,
  `dept_name` varchar(100) NOT NULL,
  `dept_nickname` varchar(80) NOT NULL,
  `org_code` int(11) default NULL,
  PRIMARY KEY  (`dept_code`),
  UNIQUE KEY `dept_dcode` (`dept_dcode`,`org_code`),
  UNIQUE KEY `dept_name` (`dept_name`,`org_code`),	
  KEY `org_code` (`org_code`),
  CONSTRAINT `department_master_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
CREATE TABLE `department_master` (
  `dept_code` int(11) NOT NULL auto_increment,
  `dept_dcode` varchar(80) NOT NULL,
  `dept_name` varchar(100) NOT NULL,
  `dept_nickname` varchar(80) NOT NULL,
  `org_code` int(11) default NULL,
  PRIMARY KEY  (`dept_code`),
  UNIQUE KEY `dept_name` (`dept_name`),
  UNIQUE KEY `dept_dcode` (`dept_dcode`),
  KEY `org_code` (`org_code`),
  CONSTRAINT `department_master_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

#
# Structure for the `designation_master` table : 
#

CREATE TABLE `designation_master` (
  `desig_code` int(11) NOT NULL auto_increment,
  `desig_dcode` varchar(80) NOT NULL,
  `desig_name` varchar(100) NOT NULL,
  `desig_nickname` varchar(80) NOT NULL,
/* `d_org_id` int(11) NOT NULL default '1',	*/				
  `d_org_id` int(11) default NULL, 
  PRIMARY KEY  (`desig_code`),
  UNIQUE KEY `desig_dcode` (`desig_dcode`,`d_org_id`),  
  UNIQUE KEY `desig_name` (`desig_name`, `d_org_id`),	
  KEY `d_org_id` (`d_org_id`),
  CONSTRAINT `designation_master_ibfk_1` FOREIGN KEY (`d_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*
CREATE TABLE `designation_master` (
  `desig_code` int(11) NOT NULL auto_increment,
  `desig_dcode` varchar(80) NOT NULL,
  `desig_name` varchar(100) NOT NULL,
  `desig_nickname` varchar(80) NOT NULL,
 `d_org_id` int(11) NOT NULL default '1',				// this line is commented
  `d_org_id` int(11) default NULL, 
  PRIMARY KEY  (`desig_code`),
  UNIQUE KEY `desig_name` (`desig_name`),
  UNIQUE KEY `desig_dcode` (`desig_dcode`),
  KEY `d_org_id` (`d_org_id`),
  CONSTRAINT `designation_master_ibfk_1` FOREIGN KEY (`d_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

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
  PRIMARY KEY  (`grd_code`),
  KEY `grd_code` (`grd_code`),
  UNIQUE KEY `grd_name` (`grd_name`,`grd_org_id`),	
  KEY `grd_org_id` (`grd_org_id`),
  CONSTRAINT `salary_grade_master_fk1` FOREIGN KEY (`grd_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*
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
*/

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
  `emp_aadhaar_no` varchar(100) default NULL,	
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
/*
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
*/

CREATE TABLE `session_master` (
  `ss_id` int(11) NOT NULL auto_increment,
  `ss_name` varchar(100) NOT NULL,
  `ss_start_from` date NOT NULL,
  `ss_end_to` date NOT NULL,
  `ss_current` tinyint(4) NOT NULL default '0',
  `ss_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`ss_id`),
  UNIQUE KEY `ss_name` (`ss_name`, `ss_org_id`),
  KEY `ss_org_id` (`ss_org_id`),
  CONSTRAINT `session_master_fk` FOREIGN KEY (`ss_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for employee_attendance_master Table 
#

CREATE TABLE employee_attendance_master (
        id int(11) NOT NULL auto_increment,
        att_emp_code varchar(35) NOT NULL,
        emp_present int(11) NOT NULL default '0',
        emp_absent int(11) NOT NULL default '0',
        emp_leave int(11) NOT NULL default '0',
        month int(11) NOT NULL ,
        year int(11) NOT NULL ,
        org_code int(11) NOT NULL,
        PRIMARY KEY  (`id`),
        UNIQUE KEY (att_emp_code, month),
        KEY `org_code` (`org_code`),
        CONSTRAINT `employee_attendance_master_ibfk_1` FOREIGN KEY (`att_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_attendance_master_fk1` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `employee_working_days_master` table :
#

CREATE TABLE employee_working_days_master (
        id int(11) NOT NULL auto_increment,
        att_emp_code varchar(55) NOT NULL,
        working_days int(11) NOT NULL default '0',
        month int(11) NOT NULL,
        year int(11) NOT NULL,
        org_code int(11) NOT NULL,
        PRIMARY KEY  (`id`),
        UNIQUE KEY (`att_emp_code`,`month`),
        KEY `org_code` (`org_code`),
        CONSTRAINT `employee_working_days_master_fk1` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_working_days_master_ibfk_1` FOREIGN KEY (`att_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB DEFAULT CHARSET=latin1;


#
#Structure for the `forgot_password` table :
#

CREATE TABLE forgot_password (
        fp_id int(11) NOT NULL auto_increment,
        user_name varchar(250) NOT NULL,
        r_key varchar(250) NOT NULL,
        pass_date datetime NOT NULL,
        expiry_date datetime NOT NULL ,
        PRIMARY KEY  (`fp_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `bank_name` varchar(250) NOT NULL,
  `bank_address` varchar(1000) NOT NULL,
  `bank_ifsc_code` varchar(100) NOT NULL,
  `branch_name` varchar(100) NOT NULL,
   account_number bigint(20) NOT NULL,
   account_type varchar(40) NOT NULL,
   pan_number varchar(10) NOT NULL,
   tan_number varchar(10) NOT NULL,
   account_name varchar(250) NOT NULL,
  `org_code` int(11) NOT NULL,
   PRIMARY KEY  (`seq_no`),
/*  PRIMARY KEY  (`bank_ifsc_code`),*/
  /*UNIQUE KEY `seq_no` (`seq_no`),*/
  /*UNIQUE KEY `seq_no_2` (`seq_no`),*/
  UNIQUE KEY (account_number,bank_ifsc_code),
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
/*
CREATE TABLE `college_pending_status` (
  `org_code` int(11) default NULL,
  `org_request_status` tinyint(4) default NULL,
  `org_pen_email` varchar(300) NOT NULL,
  PRIMARY KEY  (`org_pen_email`),
  KEY `org_code` (`org_code`),
  KEY `org_email` (`org_pen_email`),
  CONSTRAINT `college_pending_status_fk` FOREIGN KEY (`org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

CREATE TABLE `college_pending_status` (
  `id` int(11) NOT NULL auto_increment,
  `org_code` int(11) default NULL,
  `org_request_status` tinyint(4) default NULL,
  `org_pen_email` varchar(300) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `org_code` (`org_code`),
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
/*
CREATE TABLE `default_salary_master` (
  `ds_emp_type` int(11) NOT NULL,
  `ds_sal_head` int(11) default NULL,
  `ds_amount` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

CREATE TABLE `default_salary_master` (
  `ds_id` int(11) NOT NULL auto_increment,
  `ds_emp_type` int(11) NOT NULL,
  `ds_sal_head` int(11) default NULL,
  `ds_amount` int(11) default NULL,
  PRIMARY KEY  (`ds_id`)
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
/*
CREATE TABLE `emp_salary_head_master` (
  `st_code` int(11) NOT NULL,
  `st_sal_code` int(11) NOT NULL,
  `st_org_code` int(11) default NULL,
  UNIQUE KEY `st_code` (`st_code`,`st_sal_code`),
  KEY `st_code_2` (`st_code`),
  KEY `st_sal_code` (`st_sal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

CREATE TABLE `emp_salary_head_master` (
  `id` int(11) NOT NULL auto_increment,
  `st_code` int(11) NOT NULL,
  `st_sal_code` int(11) NOT NULL,
  `st_org_code` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `st_code` (`st_code`,`st_sal_code`),
  KEY `st_code_2` (`st_code`),
  KEY `st_sal_code` (`st_sal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


#
# Structure for the `emp_slab_head_master` table : 
#

/*
CREATE TABLE `emp_slab_head_master` (
  `emp_gen_code` int(11) default NULL,
  `emp_slab_code` int(11) NOT NULL,
  `emp_slab_orgCode` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

CREATE TABLE `emp_slab_head_master` (
  `id` int(11) NOT NULL auto_increment,
  `emp_gen_code` int(11) default NULL,
  `emp_slab_code` int(11) NOT NULL,
  `emp_slab_orgCode` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `emp_gen_code` (`emp_gen_code`),
  KEY `emp_slab_code` (`emp_slab_code`),
  CONSTRAINT `gender_code_fk1` FOREIGN KEY (`emp_gen_code`) REFERENCES `ts_gender` (`ts_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `slab_code_fk2` FOREIGN KEY (`emp_slab_code`) REFERENCES `slab_head` (`sl_head_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


#
# Structure for the `emp_tax_master` table : 
#

CREATE TABLE `emp_tax_master` (
  `et_emp_id` varchar(50) NOT NULL,
  `et_year` int(11) NOT NULL,
  `et_quater` int(3) NOT NULL DEFAULT 1,	
  `et_amount` float(9,3) NOT NULL DEFAULT 0,
  `et_id` int(11) NOT NULL auto_increment,
  `et_effective` int(11) NOT NULL,
  `et_percent` double(15,2) NOT NULL,
  `et_educess` float(9,2) NOT NULL DEFAULT 0,
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
  `el_applied_date` date NOT NULL,
   el_approval_date  date default NULL,
   el_approval_status  int(11) NOT NULL,
   el_org_id  int(11) NOT NULL,	
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
  `emp_tcode` varchar(80) NOT NULL,
  `emp_type_name` varchar(100) NOT NULL,
  `emp_type_nickname` varchar(80),
  `emp_pf_applies` tinyint(4) NOT NULL default '1',
  `emp_maxpf_applies` bigint(100),
  `emp_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`emp_type_id`),
  KEY `emp_type_id` (`emp_type_id`),
  UNIQUE KEY `emp_tcode` (`emp_tcode`,`emp_org_id`),
  UNIQUE KEY `emp_type_name` (`emp_type_name`,`emp_org_id`),	
  KEY `emp_org_id` (`emp_org_id`),
  CONSTRAINT `employee_type_master_fk1` FOREIGN KEY (`emp_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*
CREATE TABLE `employee_type_master` (
  `emp_type_id` int(11) NOT NULL auto_increment,
  `emp_tcode` varchar(80) NOT NULL,
  `emp_type_name` varchar(100) NOT NULL,
  `emp_type_nickname` varchar(80),
  `emp_pf_applies` tinyint(4) NOT NULL default '1',
  `emp_maxpf_applies` bigint(100),
  `emp_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`emp_type_id`),
  KEY `emp_type_id` (`emp_type_id`),
  UNIQUE KEY `emp_tcode` (`emp_tcode`,`emp_org_id`),
  KEY `emp_org_id` (`emp_org_id`),
  CONSTRAINT `employee_type_master_fk1` FOREIGN KEY (`emp_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

/*
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
*/

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
  `lt_value` int(11) default '0',	
   PRIMARY KEY  (`lt_id`),
   UNIQUE KEY `lt_name` (`lt_name`)
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
  `sh_code` varchar(80) NOT NULL,
  `sh_name` varchar(100) NOT NULL,
  `sh_type` tinyint(4) NOT NULL default '1',
  `sh_alias` varchar(10) default NULL,
  `sh_calc_type` tinyint(4) NOT NULL default '1',
  `sh_formula` varchar(100) default NULL,
  `sh_scalable` tinyint(4) NOT NULL default '0',
  `sh_special` tinyint(4) NOT NULL default '0',
/*  `sh_cat` int(11) NOT NULL default '1',	*/
  `sh_cat` tinyint(4) NOT NULL default'1',
  `sh_display` tinyint(4) NOT NULL default '1',
  `sh_type_code` int(11) NOT NULL default '2',
  `sh_process_type` tinyint(4) NOT NULL default '0',
  `sh_ledger_code` varchar(100) default NULL,
  `sh_org_id` int(11) NOT NULL,
  PRIMARY KEY  (`sh_id`),
  UNIQUE KEY `sh_code` (`sh_code`,`sh_org_id`),
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
  `sf_id` int(11) NOT NULL auto_increment,
  `sf_sal_id` int(11) NOT NULL,
  `sf_sal_formula` varchar(100) NOT NULL,
  `sf_org_id` int(11) default NULL,
  PRIMARY KEY  (`sf_id`),
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

/*
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
*/

CREATE TABLE `slab_head` (
  `sl_head_code` int(11) NOT NULL auto_increment,
  `sl_session_id` int(11) NOT NULL,
  `slab_head_name` varchar(100) NOT NULL,
  `sl_start_value` int(29) NOT NULL,
  `sl_end_value` int(11) NOT NULL,
  `sl_percent` float(9,3) NOT NULL,
  `sl_surcharge` float(9,3) NOT NULL,
  `sl_edu_cess` float(9,3) NOT NULL,
  `sl_hedu_cess` float(9,3) NOT NULL,
  `sl_orgCode` int(11) NOT NULL,
  PRIMARY KEY  (`sl_head_code`),
  UNIQUE KEY `slab_head_name_uq` (`sl_session_id`,`slab_head_name`),
  KEY `sl_orgCode` (`sl_orgCode`),
  CONSTRAINT `slab_session_fk1` FOREIGN KEY (`sl_session_id`)       REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
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

/*
CREATE TABLE `ts_gender` (
  `ts_seq` int(11) NOT NULL auto_increment,
  `gender_name` varchar(50) NOT NULL,
  `orgCode` int(11) default '1',
  PRIMARY KEY  (`gender_name`),
  UNIQUE KEY `ts_seq` (`ts_seq`),
  KEY `orgCode` (`orgCode`),
  CONSTRAINT `ts_gender_fk` FOREIGN KEY (`orgCode`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

CREATE TABLE `ts_gender` (
  `ts_seq` int(11) NOT NULL auto_increment,
  `gender_name` varchar(50) NOT NULL,
  `orgCode` int(11) default '1',
  PRIMARY KEY  (`ts_seq`),
  UNIQUE KEY `gender_name` (`gender_name`),
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
/*
CREATE TABLE `user_group_master` (
  `grp_id` int(11) NOT NULL auto_increment,
  `grp_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`grp_id`,`grp_name`),
  UNIQUE KEY `grp_name` (`grp_name`),
  KEY `grp_id` (`grp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/

CREATE TABLE `user_group_master` (
  `grp_id` int(11) NOT NULL auto_increment,
  `grp_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`grp_id`),
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

/*CREATE TABLE `user_master` (
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
*/

CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(100) NOT NULL,
  `user_pass` varchar(200) NOT NULL,
  `user_profile_id` int(11) NOT NULL default '0',
  `login_uid` int(11),
  `flag` tinyint(4) NOT NULL,
  `verification_code` varchar(36) NOT NULL,
  `is_verified` int(1) NOT NULL,	
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_id` (`user_id`)
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
  (1,'admin','admin123',1,'2025-11-25');
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
# Data for the `ts_gender` table  (LIMIT 0,500)
#

INSERT INTO `ts_gender` (`ts_seq`, `gender_name`, `orgCode`) VALUES 
  (1,'senior citizen',1),
  (2,'Super Senior',1);
COMMIT;

#
# Data for the `user_group_master` table  (LIMIT 0,500)
#

INSERT INTO `user_group_master` (`grp_id`, `grp_name`) VALUES 
  (1,'User'),
  (2,'Admin'),
  (3,'Super'),
  (4,'Master User'),
  (5,'Accounts'),
  (6, 'Employee');
COMMIT;

#
# Structure for the `employee_salary_Libality`
#

CREATE TABLE `employee_salary_liability` (

        esl_emp_code varchar(40) NOT NULL,
        esl_month int(11) NOT NULL,
        esl_year int(11) NOT NULL,
        esl_totalsalary_amount int(11) NOT NULL,
	esl_type varchar(30) NOT NULL,
	esl_transaction_date date default NULL,
        esl_org_id int(11) NOT NULL,
        KEY `esl_emp_code` (`esl_emp_code`),
        KEY `esl_org_id` (`esl_org_id`),
        CONSTRAINT `employee_salary_liability_fk1` FOREIGN KEY (`esl_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_salary_liability_ibfk_1` FOREIGN KEY (`esl_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=InnoDB DEFAULT CHARSET=latin1;

COMMIT;

#
# Structure for the `Salary_processing_setup` table :
#

CREATE TABLE Salary_processing_setup
(
  seq_id int(11) NOT NULL auto_increment,
  salary_process_mode varchar(300) NOT NULL,
  flag tinyint(4) default NULL,
  org_id int(11) NOT NULL,
  UNIQUE KEY  (salary_process_mode, org_id),
  PRIMARY KEY  (seq_id)

);
INSERT INTO `Salary_processing_setup` (`seq_id`, `salary_process_mode`, `flag`,`org_id`) VALUES
  (1,'Salary Processing with Budget',1,1),
  (2,'Salary Processing',0,1);
COMMIT;

#
# Structure for the `employee_family_record`
#


CREATE TABLE employee_family_record (
        efr_id int(11) NOT NULL auto_increment,
        efr_emp_code varchar(30) NOT NULL,
        efr_membername varchar(100) NOT NULL,
        efr_relation varchar(30) NOT NULL,
        efr_dob date default NULL,
        efr_dependent varchar(10) NOT NULL,
        efr_whetheremployed varchar(20) NOT NULL,
        efr_department varchar(70) NULL,
        efr_org_id int(11) NOT NULL,
        PRIMARY KEY  (`efr_id`),
        KEY efr_emp_code (efr_emp_code),
        KEY efr_org_id (efr_org_id),
        CONSTRAINT `employee_family_record_fk1` FOREIGN KEY (`efr_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_family_record_ibfk_1` FOREIGN KEY (`efr_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);


#
#Structure for the `employee_service_history`
#


CREATE TABLE employee_service_history (
        esh_emp_id int(11) NOT NULL auto_increment,
        esh_emp_code varchar(30) NOT NULL,
        esh_transactiontype varchar(100) NOT NULL,
        esh_tooffice varchar(70) NOT NULL,
        esh_towhichpost varchar(30) NOT NULL,
        esh_class varchar(30) NOT NULL,
        esh_ordernumber varchar(30) NOT NULL,
        esh_orderdate date default Null,
        esh_dofincrement date default Null,
        esh_payscale int(11) NOT NULL,
        esh_dept_deputation varchar(20) NOT NULL,
        esh_areatype varchar(20) NOT NULL,
        esh_org_id int(11) NOT NULL,
        PRIMARY KEY  (`esh_emp_id`),
        KEY esh_emp_code (esh_emp_code),
        KEY esh_org_id (esh_org_id),
        CONSTRAINT `employee_service_history_fk1` FOREIGN KEY (`esh_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_service_history_bfk_1` FOREIGN KEY (`esh_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);

#
#Structure for the employee_training_detail
#


CREATE TABLE employee_training_detail (
        etd_emp_id int(11) NOT NULL auto_increment,
        etd_emp_code varchar(30) NOT NULL,
        etd_trainingtype varchar(80) NOT NULL,
        etd_topicname varchar(80) NOT NULL,
        etd_institutename varchar(100) default NULL,
        etd_sponsoredby varchar(80) default NULL,
        etd_datefrom date default NULL,
        etd_dateto date default NULL,
        etd_org_id int(11) NOT NULL,
        PRIMARY KEY  (etd_emp_id),
        KEY etd_emp_code (etd_emp_code),
        KEY etd_org_id (etd_org_id),
        CONSTRAINT `employee_training_detail_fk1` FOREIGN KEY (`etd_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_training_detail_ibfk_1` FOREIGN KEY (`etd_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);

#
#Structure for the employee_education_detail
#


CREATE TABLE employee_education_detail (
        edu_emp_id int(11) NOT NULL auto_increment,
        edu_emp_code varchar(30) NOT NULL,
        edu_exam_passed varchar(40) NOT NULL,
        edu_board_university varchar(100) NOT NULL,
        edu_marksobtained int(100) NOT NULL,
        edu_passingYear int(30) NOT NULL,
        edu_grade varchar(70) default NULL,
        edu_org_id int(11) NOT NULL,
        PRIMARY KEY  (edu_emp_id),
        KEY edu_emp_code (edu_emp_code),
        KEY edu_org_id (edu_org_id),
        CONSTRAINT `employee_education_detail_fk1` FOREIGN KEY (`edu_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `employee_education_detail_ibfk_1` FOREIGN KEY (`edu_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);

#
#Structure for the leavetype_org_record
#

CREATE TABLE leavetype_org_record (
	id int(11) NOT NULL AUTO_INCREMENT,
        ltr_leave_id int(11) NOT NULL,
        ltr_org_id int(11) NOT NULL,
	PRIMARY KEY (id),
        KEY ltr_leave_id (ltr_leave_id),
        KEY ltr_or_gid (ltr_org_id),
        CONSTRAINT `leavetype_org_record_fk1` FOREIGN KEY (`ltr_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `leavetype_org_record_ibfk_1` FOREIGN KEY (`ltr_leave_id`) REFERENCES `leave_type_master` (`lt_id`) ON DELETE CASCADE ON UPDATE CASCADE

);


#
# Data for the `leave_type_master` table  
#

INSERT INTO leave_type_master(lt_id, lt_name, lt_value) VALUES
(1, 'Earned Leave', 0),
(2, 'Half Pay Leave', 20),
(3, 'Commuted Leave', 0),
(4, 'Leave Not Due', 0),
(5, 'Maternity Leave', 180),
(6, 'Paternity Leave', 0),
(7, 'Study Leave', 0),
(8, 'Extra Ordinary Leave', 0),
(9, 'Casual Leave', 0),
(10, 'Child Care Leave', 0),
(11, 'Hospital Leave', 850),
(12, 'Vocational Department Staff Leave', 0),
(13, 'Special Disability Leave', 0),
(14, 'Child Adoption Leave', 135),
(15, 'Leave to Probationers', 0),
(16, 'Leave to Apprentices', 30);



#
#Structure for the Salary_processing_dbsetup
#


CREATE TABLE Salary_processing_dbsetup
(
        id int(11) NOT NULL auto_increment,
        fyear varchar(14) NOT NULL,
        dbname varchar(255) NOT NULL,
        dbuname varchar(255) NOT NULL,
        dbpass varchar(255) NOT NULL,
        dbunit varchar(255) NOT NULL,
        dblable varchar(255) NOT NULL,
        port int(7) NOT NULL,
        org_id int(11) NOT NULL,
        UNIQUE KEY dbname(dbname),
        UNIQUE KEY dblable(dblable),
        PRIMARY KEY  (id)

);

#
# Structure for the `employee_master_support` table :
#

CREATE TABLE employee_master_support (
 id int(11) NOT NULL auto_increment,
 code varchar (50) NOT NULL,
 entitled_cat varchar (50) NOT NULL,
 status_emp varchar (50) NOT NULL,
 working_type varchar (100) NOT NULL,
 sal_dept_code int (11) NOT NULL,
 joining_dept int (11)  NOT NULL,
 joined_desig int (11)  NOT NULL,
 gpf_no varchar (50)  NULL,
 nps_no varchar (100)  NULL,
 dps_no varchar (100)  NULL,
 house_type varchar (100)  NULL,
 house_no varchar (100)  NULL,
 ecr_no varchar (100)  NULL,
 page_no varchar (100)  NULL,
 posting_id varchar (100)  NULL,
 lic_policy_no varchar (100) default NULL,
 lic_doa date default NULL,
 lic_dom date default NULL,
 gi_policy_no varchar (100) default NULL,
 gi_doa date  default NULL,
 gi_dom date default NULL,
 nextincrement_date date  default NULL,
 probation_date date default  NULL,
 confirmation_date date  default NULL,
 extention_date date default  NULL,
 PRIMARY KEY  (id),
 UNIQUE KEY  (code),
 CONSTRAINT `employee_master_support_emsfk_1` FOREIGN KEY (`code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `user_id_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id_fk2` FOREIGN KEY (`role_id`) REFERENCES `user_group_master` (`grp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `org_id_fk3` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO user_master (user_id, user_name, user_pass, flag) VALUES (1, 'admin', '0192023a7bbd73250516f069df18b500', 1);

INSERT INTO user_roles(user_id, role_id) VALUES(1, 3);

#
# Structure for the `org_tax_calc_type` table : 
#

CREATE TABLE `org_tax_calc_type` ( 
  `ct_id` int(11) NOT NULL auto_increment,
  `ct_calctype` varchar(50) DEFAULT NULL,
  `ct_sess_id` int(11) NOT NULL,
  `ct_org_code` int(11) NOT NULL,
   PRIMARY KEY  (`ct_id`), 
   KEY `ct_sess_id` (`ct_sess_id`), 
   KEY `ct_org_code` (`ct_org_code`),
   CONSTRAINT `org_tax_calc_type_fk2` FOREIGN KEY (`ct_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `org_tax_calc_type_fk4` FOREIGN KEY (`ct_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=latin1; 

