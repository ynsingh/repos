-- Nagendra Kumar Singh (nksinghiitk@gmail.com)
-- Manorama Pal(palseema30@gmail.com)
-- Database: `payroll`
--

-- --------------------------------------------------------
 DROP DATABASE IF EXISTS `payroll`;
 CREATE DATABASE `payroll`;
 USE `payroll`;


-- ---------------------------------------------------------
--
-- Table structure for table `cudsdmap (Campus id, UO id, Dept id, Scheme id and DDO id)`
--

CREATE TABLE `cudsdmap` (
  `cudsd_id` int(11) NOT NULL,
  `cudsd_scid` int(11) NOT NULL,
  `cudsd_auoid` int(11) NOT NULL,
  `cudsd_deptid` int(11) default NULL,
  `cudsd_schid` int(11) default NULL,
  `cudsd_ddoid` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `cudsdmap`
  ADD PRIMARY KEY (`cudsd_id`);

ALTER TABLE `cudsdmap`
  MODIFY `cudsd_id` int(11) NOT NULL AUTO_INCREMENT;

-- ---------------------------------------------------------
--
-- Table structure for table `employee_master`
--

CREATE TABLE `employee_master` (
  `emp_id` int(11) NOT NULL auto_increment,
  `emp_code` varchar(30) NOT NULL,
  `emp_name` varchar(70) NOT NULL,
  `emp_dept_code` int(11) NOT NULL,
  `emp_desig_code` int(11) NOT NULL,
  `emp_post` VARCHAR(255) default NULL,
  `emp_worktype` VARCHAR(255) default NULL,
  `emp_type_code` varchar(255) default NULL,
  `emp_phone` varchar(30) default NULL,
  `emp_email` varchar(30) default NULL,
  `emp_dob` date default NULL,
  `emp_doj` date default NULL,
  `emp_salary_grade` int(11) NOT NULL,
  `emp_pnp` VARCHAR(255) default NULL,
  `emp_bank_accno` varchar(20) default NULL,
  `emp_pf_accno` varchar(20) default NULL,
  `emp_pan_no` varchar(20) default NULL,
  `emp_gender` varchar(255) default NULL ,
  `emp_community` VARCHAR(255) default NULL,
  `emp_religion` VARCHAR(255) default NULL ,
  `emp_caste` VARCHAR(255) default NULL,
  `emp_mothertongue` VARCHAR(255) default NULL,
  `emp_father` varchar(100) default NULL,
  `emp_basic` int(11) NOT NULL default 0,
  `emp_emolution` int(11) default 0, 
  `emp_title` varchar(50) default NULL,
  `emp_exp` int(11) default NULL,
  `emp_qual` varchar(100) default NULL,
  `emp_specialisationid` int(11) NOT NULL,
  `emp_phd_status` varchar(100) default NULL,
  `emp_dateofphd` Date default NULL,
  `emp_AssrExam_status` varchar(100) default NULL,
  `emp_dateofAssrExam` Date default NULL,
  `emp_dateofHGP` date default NULL,
  `emp_yop` int(11) default NULL,
  `emp_prev_emp` varchar(200) default NULL,
  `emp_address` varchar(200) default NULL,
  `emp_active` tinyint(4) default '1',
  `emp_bank_ifsc_code` varchar(500) default NULL,
  `emp_bank_status` tinyint(1) default '0',
  `emp_dor` varchar(100) default NULL,
  `emp_leaving` varchar(100) default NULL,
  `emp_noti_day` int(11) default NULL,
  `emp_citizen` varchar(255) default NULL,
  `emp_aadhaar_no` varchar(100) default NULL,
  `emp_uocid` int(11) NOT NULL,
  `emp_uocuserid` int(11) NOT NULL,
  `emp_ddouserid` int(11) NOT NULL,
  `emp_schemeid` int(11) NOT NULL,
  `emp_nhisidno` VARCHAR(255) default NULL,
  `emp_scid` int(11) NOT NULL,
  `emp_org_code` int(11) NOT NULL,
   PRIMARY KEY  (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ---------------------------------------------------------

--
-- Table structure for table `employee_master_support`
--

CREATE TABLE employee_master_support (
 ems_id int(11) NOT NULL auto_increment,
 ems_code varchar (50) NOT NULL,
 ems_entitled_cat varchar (50) default NULL,
 ems_status_emp varchar (50) default NULL,
 ems_working_type varchar (100) default NULL,
 ems_sal_dept_code int (11) default NULL,
 ems_joining_dept int (11)  default NULL,
 ems_joined_desig int (11)  default NULL,
 ems_gpf_no varchar (50)  default NULL,
 ems_nps_no varchar (100)  default NULL,
 ems_dps_no varchar (100)  default NULL,
 ems_house_type varchar (100)  default NULL,
 ems_house_no varchar (100)  default NULL,
 ems_ecr_no varchar (100)  default NULL,
 ems_page_no varchar (100)  default NULL,
 ems_posting_id varchar (100)  default NULL,
 ems_lic_policy_no varchar (100) default NULL,
 ems_lic_doa date default NULL,
 ems_lic_dom date default NULL, 
 ems_gi_policy_no varchar (100) default NULL,
 ems_gi_doa date  default NULL,
 ems_gi_dom date default NULL,
 ems_nextincrement_date date  default NULL,
 ems_probation_date date default  NULL,
 ems_confirmation_date date  default NULL,
 ems_extention_date date default  NULL,
 PRIMARY KEY  (ems_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  level int(1) NOT NULL,
  host_ip varchar(25) NOT NULL,
  user varchar(25) NOT NULL,
  url varchar(255) NOT NULL,
  user_agent varchar(100) NOT NULL,
  message_title varchar(255) NOT NULL,
  message_desc mediumtext NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

-- -------------------------------------------------------------------

--
-- Table structure for table `map_scheme_department`
--

CREATE TABLE `map_scheme_department` (
  `msd_id` int(11) NOT NULL,
  `msd_schmid` varchar(255) NOT NULL,   /*this field contain value of sd_id(schecme_department table) */
  `msd_deptid` varchar(255)  NOT NULL,   /*this field contain value of dept_id(Department table) */
  `msd_scid` int(11) NOT NULL,	
  `msd_org_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `map_scheme_department`
  ADD PRIMARY KEY (`msd_id`);

ALTER TABLE `map_scheme_department`
  MODIFY `msd_id` int(11) NOT NULL AUTO_INCREMENT;

-- ----------------------------------------------------------

--
-- Table structure for table `salary_grade_master`
--

CREATE TABLE `salary_grade_master` (
  `sgm_id` int(11) NOT NULL auto_increment,
  `sgm_name` varchar(20) NOT NULL,
  `sgm_max` int(11) NOT NULL default '0',
  `sgm_min` int(11) NOT NULL default '0',
  `sgm_gradepay` int(11) NOT NULL default '5000',
  `sgm_org_id` int(11) NOT NULL default '1',
   PRIMARY KEY  (`sgm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------------------------
--
-- Table structure for table `scheme_department`
--

CREATE TABLE `scheme_department` (
  `sd_id` int(11) NOT NULL,
  `sd_code` varchar(255)  NULL,
  `sd_name` varchar(255) NOT NULL,
  `sd_short` varchar(255) default NULL,
  `sd_desc` varchar(255)  default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `scheme_department`
  ADD PRIMARY KEY (`sd_id`),
  ADD UNIQUE KEY `sd_name` (`sd_name`);

ALTER TABLE `scheme_department`
  MODIFY `sd_id` int(11) NOT NULL AUTO_INCREMENT;

-- -------------------------------------------------------------------

--
-- Table structure for table `staff_position`
--

CREATE TABLE `staff_position` (
  `sp_id` int(11) NOT NULL,
  `sp_tnt` varchar(255)  NOT NULL,
  `sp_type` varchar(255)  NOT NULL,
  `sp_emppost` varchar(255)  NOT NULL,
  `sp_grppost` varchar(255) NOT  NULL,
  `sp_scale` varchar(255) NOT NULL,
  `sp_methodRect` varchar(255) NOT NULL,
  `sp_group` varchar(255) NOT NULL,
  `sp_uo` varchar(255) NOT NULL,
  `sp_dept` varchar(255) NOT NULL,
  `sp_address1` varchar(255) default NULL,
  `sp_address2` varchar(255) default NULL,
  `sp_address3` varchar(255) default NULL,
  `sp_campusid` int(11)  NOT NULL,
  `sp_per_temporary` varchar(255) NOT NULL,
  `sp_plan_nonplan` varchar(255)  NOT NULL,
  `sp_schemecode` int(11)  NOT NULL,
  `sp_sancstrenght` int(11)  NOT NULL,
  `sp_position` int(11)  NOT NULL,
  `sp_vacant` int(11)  NOT NULL,
  `sp_remarks` varchar(255)  default NULL,
  `sp_ssdetail` varchar(255) default NULL,
  `sp_sspermanent` int(11) default '0',
  `sp_sstemporary` int(11) default '0',
  `sp_pospermanent` int(11) default '0',
  `sp_postemporary` int(11) default '0',
  `sp_vpermanenet` int(11) default '0',
  `sp_vtemporary` int(11) default '0',
  `sp_org_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `staff_position`
  ADD PRIMARY KEY (`sp_id`);

ALTER TABLE `staff_position`
  MODIFY `sp_id` int(11) NOT NULL AUTO_INCREMENT;

-- -------------------------------------------------------------------

--
-- Table structure for table `staff_position_archive`
--

CREATE TABLE `staff_position_archive` (
  `spa_id` int(11) NOT NULL,
  `spa_spid` int(11) NOT NULL,
  `spa_tnt` varchar(255)  NOT NULL,
  `spa_type` varchar(255)  NOT NULL,
  `spa_emppost` varchar(255)  NOT NULL,
  `spa_grppost` varchar(255) NOT  NULL,
  `spa_scale` varchar(255) NOT NULL,
  `spa_methodRect` varchar(255) NOT NULL,
  `spa_group` varchar(255) NOT NULL,
  `spa_uo` varchar(255) NOT NULL,
  `spa_dept` varchar(255) NOT NULL,
  `spa_address1` varchar(255) default NULL,
  `spa_address2` varchar(255) default NULL,
  `spa_address3` varchar(255) default NULL,
  `spa_campusid` int(11)  NOT NULL,
  `spa_per_temporary` varchar(255) NOT NULL,
  `spa_plan_nonplan` varchar(255)  NOT NULL,
  `spa_schemecode` int(11)  NOT NULL,
  `spa_sancstrenght` int(11)  NOT NULL,
  `spa_position` int(11)  NOT NULL,
  `spa_vacant` int(11)  NOT NULL,
  `spa_remarks` varchar(255)  default NULL,
  `spa_ssdetail` varchar(255) default NULL,
  `spa_sspermanent` int(11) default '0',
  `spa_sstemporary` int(11) default '0',
  `spa_pospermanent` int(11) default '0',
  `spa_postemporary` int(11) default '0',
  `spa_vpermanenet` int(11) default '0',
  `spa_vtemporary` int(11) default '0',
  `spa_org_id` int(11) NOT NULL,
  `spa_archuserid` int(11) NOT NULL,
  `spa_archdate`  date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `staff_position_archive`
  ADD PRIMARY KEY (`spa_id`);

ALTER TABLE `staff_position_archive`
  MODIFY `spa_id` int(11) NOT NULL AUTO_INCREMENT;

-- -------------------------------------------------------------------
-- -------------------------------------------------------------------

--
-- Table structure for table `staff_promotion`
--

CREATE TABLE `staff_promotion` (
   `spro_id` INT(11) NOT NULL AUTO_INCREMENT ,
   `spro_empid` INT(11) NOT NULL ,
   `spro_desgfrom` INT(11) NOT NULL ,
   `spro_desgto` INT(11) NOT NULL ,
   `spro_scalefrom` INT(11) NOT NULL ,
   `spro_scaleto` INT(11) NOT NULL ,
   `spro_incrementamount` varchar(255) NOT NULL ,
   `spro_bp_after_increment` varchar(255) NOT NULL ,
   `spro_vide_orderno` INT(11) NOT NULL ,
   `spro_vide_orderdate` Date NOT NULL ,
   `spro_transfered_or_not` varchar(255) NOT NULL , /* if transfer status is yes then also insert the tranfer detail in tarnsfer table*/
   `spro_remarks` varchar(255) default NULL,
    `spro_scid` int(11) NOT NULL,	
    `spro_org_id` int(11) NOT NULL,	
     PRIMARY KEY (`spro_id`)
)ENGINE = InnoDB;

-- -----------------------------------------------------------------
--
-- Table structure for table ` staff_promotion_policy`
--


CREATE TABLE `staff_promotion_policy` (
   `spp_id` INT(11) NOT NULL AUTO_INCREMENT ,
   `spp_payband` varchar(255) NOT NULL ,
   `spp_requirement1` varchar(255) default NULL ,
   `spp_requirement2` varchar(255) default NULL ,
   `spp_remarks` varchar(255) default NULL ,
   `spp_scid` int(11) NOT NULL,        
    PRIMARY KEY (`spp_id`)
)ENGINE = InnoDB;
-- ----------------------------------------------------------------------------------
--
-- Table structure for table ` staff_transfer_position`
--


CREATE TABLE `staff_transfer_position` (
   `stp_id` INT(11) NOT NULL AUTO_INCREMENT ,
   `stp_empid` INT(11) NOT NULL ,
   `stp_empdesig` INT(11) NOT NULL ,
   `stp_empscid_from` INT(11) NOT NULL ,
   `stp_empscid_to` INT(11) NOT NULL ,
   `stp_empdept_from` INT(11) NOT NULL ,
   `stp_empdept_to` INT(11) NOT NULL ,
   `stp_date` Date NOT NULL ,
   `stp_remarks` varchar(255) default NULL,
   `stp_scid` int(11) NOT NULL,	
   `stp_org_id` int(11) NOT NULL,	
    PRIMARY KEY (`stp_id`)
)ENGINE = InnoDB;

-- -----------------------------------------------------------------
--
-- Table structure for table ` staff_transfer_order`
--

CREATE TABLE `staff_transfer_order` (
   `sto_id` INT(11) NOT NULL AUTO_INCREMENT ,
   `sto_empid` INT(11) NOT NULL ,
   `sto_orderno` INT(11) NOT NULL ,
   `sto_orderdate` Date NOT NULL ,
   `sto_ordermode` varchar(255) NOT NULL ,/*online/offline*/
   `sto_ordersingedby` varchar(255) NOT NULL ,
   `sto_orderdetail` varchar(255) NOT NULL ,
   `sto_remarks` varchar(255) default NULL,
   `sto_scid` int(11) NOT NULL,        
   `sto_org_id` int(11) NOT NULL,      
    PRIMARY KEY (`sto_id`)
)ENGINE = InnoDB;

-- -----------------------------------------------------------------
--
-- Table structure for table ` staff_transfer_order`
--

CREATE TABLE `staff_transfer_detail` (
   `std_id` INT(11) NOT NULL AUTO_INCREMENT ,
   `std_empid` INT(11) NOT NULL ,
   `std_dues_stauts` varchar(255) NOT NULL ,
   `std_due_date` Date NOT NULL ,
   `std_chargehandover` varchar(255) NOT NULL ,/*whom*/
   `std_chargehandover_date` Date NOT NULL ,
   `std_dues_clearedby` varchar(255) NOT NULL ,
   `std_remarks` varchar(255) default NULL,
   `std_scid` int(11) NOT NULL,        
    PRIMARY KEY (`std_id`)
)ENGINE = InnoDB;

-- --------------------------------------------------------
--
-- Table structure for table `tax_slab_master`
--


CREATE TABLE `tax_slab_master` (
  `tsm_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `tsm_fy` varchar(255) NOT NULL,
  `tsm_name` varchar(255) NOT NULL,
  `tsm_startvalue` varchar(255) NOT NULL,
  `tsm_endvalue` varchar(255) NOT NULL,
  `tsm_type` varchar(20) NOT NULL,
  `tsm_gender` varchar(20) NOT NULL,
  `tsm_percent` varchar(10) NOT NULL,
  `tsm_remarks` varchar(255) default NULL,
   PRIMARY KEY  (`tsm_id`),
   UNIQUE KEY `tsm_fy` (`tsm_fy`,`tsm_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Table structure for table `leave_type_master`
--

CREATE TABLE `leave_type_master` (
  `lt_id` int(11) NOT NULL auto_increment,
  `lt_name` varchar(50) NOT NULL,
  `lt_value` int(11) default '0',
  `lt_remarks` varchar(255) default NULL,
   PRIMARY KEY  (`lt_id`),
   UNIQUE KEY `lt_name` (`lt_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Table structure for table `user_role_type`
--

CREATE TABLE `user_role_type` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `scid` int(10) NOT NULL,
  `deptid` int(10) DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into user_role_type values (1,1,1,1,1,'Administrator','');

CREATE TABLE user_input_transfer (
        uit_id INT(11) NOT NULL AUTO_INCREMENT ,
        uit_registrarname  varchar(255) NOT NULL ,
        uit_desig  varchar(255) NOT NULL ,
        uit_uso_no  INT(11) NOT NULL ,
        uit_date datetime NOT NULL,
        uit_rc_no varchar(255) NOT NULL,
        uit_subject blob NOT NULL,
        uit_referenceno varchar(255) NOT NULL,
        uit_ordercontent blob default NULL,
        uit_emptype varchar(255) NOT NULL,
        uit_uoc_from varchar(255) NOT NULL,
        uit_workdept_from varchar(255) NOT NULL,
        uit_desig_from varchar(255) NOT NULL,
        uit_staffname varchar(255) NOT NULL,
        uit_workingpost_from varchar(255) NOT NULL,
        uit_uoc_to varchar(255) NOT NULL,
        uit_dept_to varchar(255) NOT NULL,
        uit_post_to varchar(255) NOT NULL,
        uit_tta_detail blob NOT NULL,
        uit_dateofrelief datetime NOT NULL,
        uit_dateofjoining datetime NOT NULL,
        uit_email_sentto blob default NULL,
        PRIMARY KEY (uit_id)
)ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `bank_profile_details`
--

CREATE TABLE `bankprofile` ( 
  `id` int(11) NOT NULL auto_increment,
  `bank_name` varchar(250) NOT NULL,
  `branch_name` varchar(255) NOT NULL,
  `bank_address` varchar(500) NOT NULL,
  `ifsc_code` varchar(500) default NULL,
  `account_number` varchar(50) default NULL,
  `account_type` varchar(40) NOT NULL,`
  `account_name` varchar(255) NOT NULL,
  `pan_number` varchar(20) default NULL,
  `tan_number` varchar(10) NOT NULL,
  `gst_number` varchar(100) NOT NULL,
  `aadhar_number` varchar(100) NOT NULL,
  `org_id` varchar(40) NOT NULL,
  `remark` varchar(100) NOT NULL,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ---------------------------------------------------------


