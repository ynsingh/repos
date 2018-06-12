-- Nagendra Kumar Singh (nksinghiitk@gmail.com)

-- Manorama Pal(palseema30@gmail.com)
-- Om Prakash (omprakashkgp@gmail.com)
-- Database: `payroll`
--

-- --------------------------------------------------------
-- DROP DATABASE IF EXISTS `payroll`;
-- CREATE DATABASE `payroll`;
-- USE `payroll`;

-- --------------------------------------------------------
--
-- Table structure for table `additional_assignments`
--


CREATE TABLE `additional_assignments` (
  `aa_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `aa_empid` int(11)  NOT NULL,
  `aa_asigname` varchar(255)  Default NULL,
  `aa_asigperiodfrom`  DATETIME  Default NULL,
  `aa_asigperiodto`  DATETIME  Default NULL,
  `aa_place` varchar(255)  Default NULL,
  `aa_creatorid` varchar(255) NOT NULL,
  `aa_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `aa_modifierid` varchar(255) NOT NULL,
  `aa_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;


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
  `account_type` varchar(40) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `pan_number` varchar(20) default NULL,
  `tan_number` varchar(10) NOT NULL,
  `gst_number` varchar(100) NOT NULL,
  `aadhar_number` varchar(100) NOT NULL,
  `org_id` varchar(40) NOT NULL,
  `remark` varchar(100) NOT NULL,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for table `bank_profile_details_archive`
--

CREATE TABLE `bankprofile_archive` ( 
  `bpa_id` int(11) NOT NULL auto_increment,
  `bpa_bpid` int(11) NOT NULL,
  `bpa_bank_name` varchar(250) NOT NULL,
  `bpa_branch_name` varchar(255) NOT NULL,
  `bpa_bank_address` varchar(500) NOT NULL,
  `bpa_ifsc_code` varchar(500) default NULL,
  `bpa_account_number` varchar(50) default NULL,
  `bpa_account_type` varchar(40) NOT NULL,
  `bpa_account_name` varchar(255) NOT NULL,
  `bpa_pan_number` varchar(20) default NULL,
  `bpa_tan_number` varchar(10) NOT NULL,
  `bpa_gst_number` varchar(100) NOT NULL,
  `bpa_aadhar_number` varchar(100) NOT NULL,
  `bpa_org_id` varchar(40) NOT NULL,
  `bpa_remark` varchar(100) NOT NULL,
  `bpa_creatorid` int(11) NOT NULL,
  `bpa_date` date NOT NULL,
   PRIMARY KEY  (`bpa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Table structure for ddo`
--

CREATE TABLE `ddo` (
     `ddo_id` int(11) NOT NULL auto_increment,
     `ddo_scid` int(11) NOT NULL,
     `ddo_deptid` int(11) NOT NULL,
     `ddo_schid` int(11) NOT NULL,
     `ddo_code` varchar(255) NOT NULL,
     `ddo_name` varchar(255) NOT NULL,
     `ddo_remark` varchar(255) default NULL,
     PRIMARY KEY (ddo_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for ddo_archive`
--

CREATE TABLE `ddo_archive` (
     `ddoa_id` int(11) NOT NULL auto_increment,
     `ddoa_ddoid` int(11) NOT NULL,
     `ddoa_scid` int(11) NOT NULL,
     `ddoa_deptid` int(11) NOT NULL,
     `ddoa_schid` int(11) NOT NULL,
     `ddoa_code` varchar(255) NOT NULL,
     `ddoa_name` varchar(255) NOT NULL,
     `ddoa_remark` varchar(255) default NULL,
     `ddoa_archuserid` int(11) NOT NULL,
     `ddoa_archdate`  date NOT NULL,
     PRIMARY KEY (ddoa_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- -----------------------------------------------------------------
--
-- Table structure for table `employee_servicedetail`
--
CREATE TABLE  employee_servicedetail(
        empsd_id INT(11) NOT NULL AUTO_INCREMENT ,
        empsd_empid  INT(11) NOT NULL ,
        empsd_campuscode  varchar(255) NOT NULL ,
        empsd_ucoid INT(11) NOT NULL,
        empsd_deptid INT(11) NOT NULL,
	empsd_schemeid int(9) DEFAULT NULL,
	empsd_ddoid int(9) DEFAULT NULL,
	empsd_group varchar(9) DEFAULT NULL,
	empsd_worktype VARCHAR(255) NOT NULL,
	empsd_level VARCHAR(255) NOT NULL,
	empsd_shagpstid varchar(9) DEFAULT NULL, 
        empsd_desigcode  varchar(255) NOT NULL ,
        empsd_pbid INT(11) NOT NULL,
	empsd_gradepay varchar(100) DEFAULT NULL,
        empsd_pbdate date NOT NULL,
        empsd_dojoin date NOT NULL,
        empsd_dorelev date NOT NULL,
        PRIMARY KEY (empsd_id)
)ENGINE = InnoDB;
-- ---------------------------------------------------------
--
-- Table structure for table `employee_type`
--
CREATE TABLE `employee_type` (
  `empt_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `empt_code` varchar(50)  NOT NULL,
  `empt_name` varchar(255)  NOT NULL,
  `empt_shortname` varchar(255)  Default NULL,
  `empt_pfapplies` varchar(50)  Default NULL,
  `empt_maxpflimit` float  Default 0.0,
  `empt_creatorid` varchar(255) NOT NULL,
  `empt_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `empt_modifierid` varchar(255) NOT NULL,
  `empt_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`empt_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into employee_type values (1,'T01','Temporary','TEMP','N',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
insert into employee_type values (2,'P01','Permanent','PER','Y',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
insert into employee_type values (3,'R01','Regular','REG','Y',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
-- ---------------------------------------------------------
--
-- Table structure for table `employee_master`
--

CREATE TABLE `employee_master` (
  `emp_id` int(11) NOT NULL auto_increment,
  `emp_userid` int(11) default 0,
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
  `emp_doprobation` date default NULL,
  `emp_doregular` date default NULL,
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
  `emp_nhisidno` varchar(255) default NULL,
  `emp_ddoid` int(11) NOT NULL,
  `emp_group` varchar(10) default NULL,
  `emp_apporderno` varchar(255) default NULL,
  `emp_phstatus` varchar(10) default NULL,
  `emp_phdetail` varchar(255) default NULL,
  `emp_bloodgroup` varchar(10) default NULL,
  `emp_photoname` varchar(255) default NULL,
  `emp_scid` int(11) NOT NULL,
  `emp_org_code` int(11) NOT NULL,
  `emp_remarks` blob default NULL,
  `emp_grade` varchar(255) Default NULL,
  `emp_secndemail` VARCHAR(255) default NULL,
  `emp_phddiscipline` VARCHAR(255) default NULL,
  `emp_phdtype` VARCHAR(50) default NULL,
  `emp_phdinstname` VARCHAR(255) default NULL,
  `emp_phdunivdeput` VARCHAR(255) default NULL,
  `emp_netqualified` VARCHAR(50) default NULL,
  `emp_netpassingyear` date  default NULL,
  `emp_netdiscipline`  VARCHAR(255) default NULL,
  `emp_vciregno` VARCHAR(255) default NULL,
  `emp_vciregdate` datetime default NULL ,
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
-- Table structure for table `leave_type_master`
--

CREATE TABLE `leave_type_master` (
  `lt_id` int(11) NOT NULL auto_increment,
  `lt_name` varchar(50) NOT NULL,
  `lt_code` varchar(100) NOT NULL,
  `lt_short` varchar(100) NOT NULL,
  `lt_value` int(11) default '0',
  `lt_remarks` varchar(255) default NULL,
   PRIMARY KEY  (`lt_id`),
   UNIQUE KEY `lt_name` (`lt_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Table structure for table `leave_apply`
--

CREATE TABLE `leave_apply` (
  `la_id` int(11) NOT NULL auto_increment,
  `la_userid` varchar(50) NOT NULL,
  `la_deptid` varchar(50) NOT NULL,
  `la_type` varchar(50) NOT NULL,
  `la_year` varchar(10) NOT NULL,
  `applied_la_from_date` DATETIME DEFAULT null,
  `applied_la_to_date` DATETIME DEFAULT null,
  `la_days` decimal(5,2) NOT NULL default 0,
  `granted_la_from_date` DATETIME DEFAULT null,
  `granted_la_to_date` DATETIME DEFAULT null,
  `la_taken` decimal(5,2)NOT NULL default 0,
  `la_status` int(11) default '0',
  `la_desc` varchar(100) NOT NULL,
  `la_rejres` varchar(100) DEFAULT NULL,
   PRIMARY KEY  (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Table structure for table `leave_earned`
--

CREATE TABLE `leave_earned` (
  `le_id` int(11) NOT NULL auto_increment,
  `le_userid` varchar(50) NOT NULL,
  `le_type` varchar(50) NOT NULL,
  `le_year` varchar(10) NOT NULL,
  `le_earned` decimal(5,2)NOT NULL,
   PRIMARY KEY  (`le_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  level int(1) NOT NULL,
  host_ip varchar(25) NOT NULL,
  user varchar(255) default NULL,
  url varchar(255) NOT NULL,
  user_agent varchar(100) NOT NULL,
  message_title varchar(255) NOT NULL,
  message_desc mediumtext NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

-- --------------------------------------------------------
--
-- Table structure for map_sc_uo 
--

CREATE TABLE `map_sc_uo` (
     `scuo_id` int(11) NOT NULL auto_increment,
     `scuo_scid` int(11) NOT NULL,
     `scuo_uoid` int(11) NOT NULL,
     PRIMARY KEY (scuo_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------
--
-- Table structure for map_sc_uo_archive 
--

CREATE TABLE `map_sc_uo_archive` (
     `scuoa_id` int(11) NOT NULL auto_increment,
     `scuoa_scuoid` int(11) NOT NULL,
     `scuoa_scid` int(11) NOT NULL,
     `scuoa_uoid` int(11) NOT NULL,
     `scuoa_archuserid` int(11) NOT NULL,
     `scuoa_archdate`  date NOT NULL,
      PRIMARY KEY (scuoa_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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


-- -------------------------------------------------------------------
--
-- Table structure for table `map_scheme_department_archive`
--

CREATE TABLE `map_scheme_department_archive` (
  `msda_id` int(11) NOT NULL,
  `msda_msdid` int(11) NOT NULL,
  `msda_schmid` varchar(255) NOT NULL,   
  `msda_deptid` varchar(255)  NOT NULL,   
  `msda_scid` int(11) NOT NULL,
  `msda_org_id` varchar(255)  NOT NULL,
  `msda_archuserid` varchar(255) NOT NULL,
  `msda_archdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `map_scheme_department_archive`
  ADD PRIMARY KEY (`msda_id`);

ALTER TABLE `map_scheme_department_archive`
  MODIFY `msda_id` int(11) NOT NULL AUTO_INCREMENT;

-- ----------------------------------------------------------

--
-- Table structure for table `salary_grade_master`
--

CREATE TABLE `salary_grade_master` (
  `sgm_id` int(11) NOT NULL auto_increment,
  `sgm_name` varchar(20) NOT NULL,
  `sgm_max` int(11) NOT NULL default '0',
  `sgm_min` int(11) NOT NULL default '0',
  `sgm_gradepay` int(11) NOT NULL default '500',
  `sgm_level` varchar(255) NOT NULL  default '0',
  `sgm_org_id` int(11) NOT NULL default '1',
   PRIMARY KEY  (`sgm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------
--
-- Table structure for  `salary_grade_master_archive`
--

CREATE TABLE `salary_grade_master_archive` (
     `sgma_id` int(11) NOT NULL auto_increment,
     `sgma_sgmid` int(11) NOT NULL,
     `sgma_name` varchar(20) NOT NULL,
     `sgma_max` int(11) NOT NULL default '0',
     `sgma_min` int(11) NOT NULL default '0',
     `sgma_gradepay` int(11) NOT NULL default '500',
     `sgma_level` varchar (255) NOT NULL default '0',
     `sgma_org_id` int(11) NOT NULL default '1',
     `sgma_archuserid` int(11) NOT NULL,
     `sgma_archdate`  date NOT NULL,
      PRIMARY KEY (sgma_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- -----------------------------------------------------------------------
--
-- Table structure for table `scheme_department`
--

CREATE TABLE `scheme_department` (
  `sd_id` int(11) NOT NULL,
  `sd_deptid` int(11)  NULL,
  `sd_code` varchar(255)  NULL,
  `sd_name` varchar(255) NOT NULL,
  `sd_short` varchar(255) default NULL,
  `sd_desc` varchar(255)  default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `scheme_department`
  ADD PRIMARY KEY (`sd_id`),
  ADD UNIQUE KEY `sd_name` (`sd_name`,`sd_deptid`);


ALTER TABLE `scheme_department`
  MODIFY `sd_id` int(11) NOT NULL AUTO_INCREMENT;

-- -------------------------------------------------------
--
-- Table structure for scheme_department_archive 
--

CREATE TABLE `scheme_department_archive` (
     `sda_id` int(11) NOT NULL auto_increment,
     `sda_sdid` int(11) NOT NULL,
     `sda_deptid` int(11) NOT NULL,
     `sda_code` varchar(255)  NULL,
     `sda_name` varchar(255) NOT NULL,
     `sda_short` varchar(255) default NULL,
     `sda_desc` varchar(255)  default NULL,
     `sda_archuserid` int(11) NOT NULL,
     `sda_archdate`  date NOT NULL,
      PRIMARY KEY (sda_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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

-- -------------------------------------------------------------------
--
-- Table structure for table `staff_retirement`
--

CREATE TABLE `staff_retirement` (
  `sre_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sre_empid` int(11)  NOT NULL,
  `sre_empcode` varchar(100)  Default NULL,
  `sre_empemailid` varchar(255)  NOT NULL,
  `sre_doj` datetime NOT NULL,
  `sre_dor` datetime NOT NULL,
  `sre_reason` varchar(255) NOT NULL,
  `sre_reasondate` datetime NOT NULL,
  `sre_remark` blob NULL,
  `sre_creatorid` varchar(255) NOT NULL,
  `sre_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sre_modifierid` varchar(255) NOT NULL,
  `sre_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
   /*UNIQUE (`sre_empid`,`sre_empcode`,`sre_empemailid`)*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff_Performance_Data`
--

CREATE TABLE `Staff_Performance_Data` (
  `spd_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `spd_empid` int(11) NOT NULL,
  `spd_int_award` int(11)  default NULL,
  `spd_nat_award` int(11)  default NULL,
  `spd_uni_award` int(11)  default NULL,
  `spd_res_pub_int` int(11)  default NULL,
  `spd_res_pub_nat` int(11)  default NULL,
  `spd_pop_pub_int` int(11)  default NULL,
  `spd_pop_pub_nat` int(11)  default NULL,
  `spd_pre_pub_int` int(11)  default NULL,
  `spd_pre_pub_nat` int(11)  default NULL,
  `spd_noof_project` int(11) default NULL,
  `spd_fund_outly_ofproject` double not null default 0,
  `spd_tr_att_int` int(11)  default NULL,
  `spd_tr_att_nat` int(11)  default NULL,
  `spd_tr_con_int` int(11)  default NULL,
  `spd_tr_con_nat` int(11)  default NULL,
  `spd_mvsc_stu-guided` int(11)  default NULL,
  `spd_phd_stu-guided` int(11)  default NULL,
  `spd_others_stu-guided` int(11)  default NULL,
  `spd_no_ofguestlecture` int(11)  default NULL,
  `spd_per_filename` varchar(500) default NULL,
  `spd_creatorid` varchar(255) NOT NULL,
  `spd_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `spd_modifierid` varchar(255) NOT NULL,
  `spd_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   UNIQUE (`spd_empid`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Table structure for table `salaryhead_configuration
--

CREATE TABLE `salaryhead_configuration` (
  `shc_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `shc_emptypeid` int(11)  NOT NULL,
  `shc_salheadid` varchar(255)  NOT NULL,
  `shc_scid` int(11)  Default NULL,
  `shc_creatorid` varchar(255) NOT NULL,
  `shc_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `shc_modifierid` varchar(255) NOT NULL,
  `shc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------

--
-- Table structure for table `salaryhead_defaultvalue
--

CREATE TABLE `salaryhead_defaultvalue` (
  `shdv_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `shdv_paybandid` int(11)  NOT NULL,
  `shdv_salheadid` int(11)  NOT NULL,
  `shdv_defaultvalue` double  Default 0,
  `shdv_remarks` varchar(255)  Default NULL,
  `shdv_creatorid` varchar(255) NOT NULL,
  `shdv_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `shdv_modifierid` varchar(255) NOT NULL,
  `shdv_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------

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
-- Table structure for table `user_role_type`
--

CREATE TABLE `user_role_type` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `scid` int(10) DEFAULT NULL,
  `deptid` int(10) DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into user_role_type values (1,1,1,1,NULL,'Administrator','');

-- --------------------------------------------------------
--
-- Table structure for table `user_role_type_archive`
--

CREATE TABLE `user_role_type_archive` (
  `urta_id` INT(11) NOT NULL,
  `urta_urtid` INT(11) NOT NULL,
  `urta_userid` int(11) NOT NULL,
  `urta_roleida` int(11) NOT NULL,
  `urta_scida` int(10) DEFAULT NULL,
  `urta_deptida` int(10) DEFAULT NULL,
  `urta_usertypea` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `creatordate` date NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `user_role_type_archive`
  ADD PRIMARY KEY (`urta_id`);

ALTER TABLE `user_role_type_archive`
  MODIFY `urta_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `user_input_transfer`
--
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
        uit_emptypeto varchar(255) NOT NULL,
        uit_uoc_from varchar(255) NOT NULL,
        uit_workdept_from varchar(255) NOT NULL,
        uit_desig_from varchar(255) NOT NULL,
        uit_staffname varchar(255) NOT NULL,
        uit_workingpost_from varchar(255) NOT NULL,
	uit_scid_from  int(11) Default null,
	uit_scid_to  int(11) default NULL,
        uit_uoc_to varchar(255) NOT NULL,
        uit_dept_to varchar(255) NOT NULL,
	uit_desig_to  varchar(255) NOT NULL,
        uit_post_to varchar(255) NOT NULL,
        uit_schm_from INT(11) default NULL,
        uit_schm_to INT(11) default NULL,
	uit_ddoid_to int(11) default NULL,
	uit_group_to  varchar(50) default NULL,
	uit_paybandid_to int(11) default NULL,
	uit_vacanttype_to  varchar(255) default NULL,
        uit_tta_detail blob NOT NULL,
        uit_dateofrelief datetime NOT NULL,
        uit_dateofjoining datetime NOT NULL,
        uit_email_sentto blob default NULL,
        PRIMARY KEY (uit_id)
)ENGINE = InnoDB;

-- -------------------------------------------------------------------

--
-- Table structure for table `Salary Head`
--

CREATE TABLE `salary_head` (
  `sh_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sh_code` varchar(50)  NOT NULL,
  `sh_name` varchar(255)  NOT NULL,
  `sh_shortname` varchar(50)  Default NULL,
  `sh_type` varchar(50)  NOT NULL,
  `sh_calc_type` varchar(50)  NOT NULL,
  `sh_taxable` varchar(50)  NOT NULL,
  `sh_category` varchar(50)  Default 'GS',
  `sh_ledgercode` varchar(255)  Default NULL,
  `sh_description` varchar(255)  Default NULL,
  `sh_creatorid` varchar(255) NOT NULL,
  `sh_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sh_modifierid` varchar(255) NOT NULL,
  `sh_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`sh_code`)


) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------

--
-- Table structure for table `salary_formula `
--

CREATE TABLE `salary_formula` (
  `sf_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sf_salhead_id` INT(11)  NOT NULL,
  `sf_formula` varchar(255)  NOT NULL,
  `sf_description` varchar(255)  Default NULL,
  `sf_creatorid` varchar(255) NOT NULL,
  `sf_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sf_modifierid` varchar(255) NOT NULL,
  `sf_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
   /*UNIQUE (`sf_id`)*/


) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `hod_list`
--

CREATE TABLE `hod_list` (
  `hl_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `hl_userid` int(11)  NOT NULL,
  `hl_empcode` varchar(100)  NOT NULL,
  `hl_deptid` int(10)  NOT NULL,
  `hl_scid` int(10) NOT NULL,
  `hl_uopid` int(3) NULL,
  `hl_datefrom` datetime NOT NULL,
  `hl_dateto` datetime NOT NULL,
  `hl_status` varchar(50) NOT NULL,
  `hl_creatorid` varchar(255) NOT NULL,
  `hl_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hl_modifierid` varchar(255) NOT NULL,
  `hl_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`hl_userid`,`hl_deptid`,`hl_scid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `uo_list`
--

CREATE TABLE `uo_list` (
  `ul_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `ul_userid` int(11)  NOT NULL,
  `ul_empcode` varchar(100)  NOT NULL,
  `ul_authuoid` int(11)  NOT NULL,
  `ul_uocode` varchar(100)  NOT NULL,
  `ul_uoname` varchar(255) NOT NULL,
  `ul_datefrom` datetime NOT NULL,
  `ul_dateto` datetime NOT NULL,
  `ul_status` varchar(50) NOT NULL,
  `ul_creatorid` varchar(255) NOT NULL,
  `ul_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ul_modifierid` varchar(255) NOT NULL,
  `ul_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`ul_userid`,`ul_uoname`,`ul_uocode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------


