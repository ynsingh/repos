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
  `campusid` INT(11) NULL DEFAULT NULL,
  `ucoid` INT(11) NULL DEFAULT NULL , 
  `deptid` INT(11) NULL DEFAULT NULL, 
  `schemeid` INT(11) NULL DEFAULT NULL,
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
  `bpa_campusid` INT(11) NULL DEFAULT NULL,
  `bpa_ucoid` INT(11) NULL DEFAULT NULL , 
  `bpa_deptid` INT(11) NULL DEFAULT NULL,  
  `bpa_schemeid` INT(11) NULL DEFAULT NULL,
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
-- Table structure for table `cron`
--


CREATE TABLE `cron` (
`id` int(5) NOT NULL,
`name` varchar(100) DEFAULT NULL,
`command` varchar(255) NOT NULL,
`interval_sec` int(10) NOT NULL,
`last_run_at` datetime DEFAULT NULL,
`next_run_at` datetime DEFAULT NULL,
`is_active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
-- Table structure for table `saving_master`
--

CREATE TABLE `saving_master` (
  `sm_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sm_fyear` varchar(255)  NOT NULL,
  `sm_80C` DECIMAL (15, 2) NOT NULL,
  `sm_80CCD` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80D` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80DD` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80E` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80G` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80GGA` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80U` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_24B` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_other` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_creatorid` varchar(255) NOT NULL,
  `sm_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sm_modifierid` varchar(255) NOT NULL,
  `sm_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------------------
--
-- Table structure for table `user_saving_master`
--

CREATE TABLE `user_saving_master` (
  `usm_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `usm_fyear` varchar(10)  NOT NULL,
  `usm_empid` int(11)  NOT NULL,
  `usm_pfno` varchar(30)  NOT NULL,
  `usm_80C` DECIMAL (15, 2) NOT NULL,
  `usm_80CCD` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80D` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80DD` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80E` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80G` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80GGA` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80U` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_24B` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_other` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_rejres` varchar(255) DEFAULT NULL,
  `usm_status` int(11) default '0',
  `usm_creatorid` varchar(255) NOT NULL,
  `usm_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `usm_modifierid` varchar(255) NOT NULL,
  `usm_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------------------
--
-- Table structure for table `employee_servicedetail`
--

CREATE TABLE  employee_servicedetail(
        empsd_id INT(11) NOT NULL AUTO_INCREMENT ,
        empsd_empid  INT(11) NOT NULL,
	empsd_orderno varchar(100) default NULL,
	empsd_authority VARCHAR(255) NULL,
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
	empsd_filename varchar(500) default null,
	empsd_fsession varchar(100) default null,
	empsd_tsession varchar(100) default null,
	empsd_grade VARCHAR(255) NULL,
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
  `empt_tnt` varchar(255) default NULL,	
  `empt_shortname` varchar(255)  Default NULL,
  `empt_pfapplies` varchar(50)  Default NULL,
  `empt_maxpflimit` float  Default 0.0,
  `empt_creatorid` varchar(255) NOT NULL,
  `empt_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `empt_modifierid` varchar(255) NOT NULL,
  `empt_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`empt_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into employee_type values (1,'T01','Temporary','TEMP','Teaching','N',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
insert into employee_type values (2,'P01','Permanent','PER','Teaching','Y',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
insert into employee_type values (3,'R01','Regular','Teaching','REG','Y',0.0,'admin','2018-02-01 00:00:00','admin','2018-02-01 00:00:00');
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
  `emp_head` VARCHAR(55)  DEFAULT NULL,
  `emp_worktype` VARCHAR(255) default NULL,
  `emp_type_code` varchar(255) default NULL,
  `emp_phone` varchar(30) default NULL,
  `emp_email` varchar(30) default NULL,
  `emp_dob` date default NULL,
  `emp_doj` date default NULL,
  `emp_jsession` varchar(100) default null,
  `emp_paycomm` VARCHAR(255) NULL,
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
  `emp_father` varchar(255) default NULL,
  `emp_spousename` varchar(255) default null,
  `emp_basic` int(11) NOT NULL default 0,
  `emp_emolution` int(11) default 0, 
  `emp_title` varchar(50) default NULL,
  `emp_exp` int(11) default NULL,
  `emp_qual` varchar(100) default NULL,
  `emp_specialisationid` int(11) NOT NULL,
  `emp_splsuboth` VARCHAR(255) NULL ,
  `emp_phd_status` varchar(100) default NULL,
  `emp_dateofphd` Date default NULL,
  `emp_AssrExam_status` varchar(100) default NULL,
  `emp_dateofAssrExam` varchar(10) default NULL,
  `emp_dateofHGP` date default NULL,
  `emp_yop` int(11) default NULL,
  `emp_doprobation` date default NULL,
  `emp_doregular` date default NULL,
  `emp_prev_emp` varchar(200) default NULL,
  `emp_address` varchar(200) default NULL,
  `emp_active` tinyint(4) default '1',
  `emp_bank_ifsc_code` varchar(500) default NULL,
  `emp_bankname` VARCHAR(255) default NULL,
  `emp_bankbranch` VARCHAR(255) default NULL, 
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
  `emp_phdspecialisation` VARCHAR(255) NULL,
  `emp_phdcollege` VARCHAR(255) NULL,
  `emp_netqualified` VARCHAR(50) default NULL,
  `emp_netpassingyear` varchar(10)  default NULL,
  `emp_netdiscipline`  VARCHAR(255) default NULL,
  `emp_dojvc` date default NULL,
  `emp_salary_gradenew` int(11) default NULL ,
  `emp_maritalstatus` varchar(100) default NULL,
  `emp_seniortyid` int(10) default NULL,
   PRIMARY KEY  (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- ---------------------------------------------------------

--
-- Table structure for table `employee_master_support`
--

CREATE TABLE employee_master_support (
 ems_id int(11) NOT NULL auto_increment,
 ems_empid varchar(100) default null, 
 ems_code varchar (50) NOT NULL,
 ems_vci_status varchar(100) default null,
 ems_vci_statchapter varchar(100) default null,
 ems_vci_statregno varchar(100) default null,
 ems_vci_statregdate datetime default NULL,
 ems_vci_statvaliddate datetime default NULL,
 ems_vci_alliregno varchar(100) default null,
 ems_vci_alliregdate datetime default NULL,
 ems_vci_allivaliddate datetime default NULL,
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
 ems_pensioncontri varchar(100) default null,
 ems_upfno varchar(256) default null,
 ems_universityemp varchar(100) default null,
 ems_washingallowance varchar(100) default null, 
 ems_deductupf varchar(100) default null, 
 ems_hragrade varchar(256) default null,
 ems_ccagrade varchar(256) default null,
 ems_inclsummary varchar(256) default null,
 ems_lic1no varchar(256) default null,
 ems_lic1amount double  DEFAULT 0, 
 ems_lic2no varchar(256) default null,
 ems_lic2amount double  DEFAULT 0,
 ems_lic3no varchar(256) default null,
 ems_lic3amount double  DEFAULT 0,
 ems_lic4no varchar(256) default null,
 ems_lic4amount double  DEFAULT 0,
 ems_lic5no varchar(256) default null,
 ems_lic5amount double  DEFAULT 0,
 ems_prdno1 varchar(256) default null,
 ems_prdno2 varchar(256) default null,
 ems_prdno3 varchar(256) default null,
 ems_plino1 varchar(256) default null,
 ems_plino2 varchar(256) default null,
 ems_society varchar(256) default null,
 ems_societymember varchar(256) default null, 
 ems_pwplace1 INT(11) NULL, 
 ems_pwplace2 INT(11) NULL, 
 ems_pwplace3 INT(11) NULL,
 ems_erfq VARCHAR(50) default NULL , 
 ems_erfqhra VARCHAR(10)  default NULL, 
 ems_qoccupai VARCHAR(10) default NULL, 
 ems_rentgrade VARCHAR(10) default NULL, 
 ems_spfcgs VARCHAR(100) default NULL , 
 ems_spfcgs2000 VARCHAR(100) default NULL ,
 ems_fsfno VARCHAR(100) default NULL,
 PRIMARY KEY  (ems_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `hl_fromsession` VARCHAR(70) NULL,
  `hl_dateto` datetime NOT NULL,
  `hl_tosession` VARCHAR(70) NULL,
  `hl_status` varchar(50) NOT NULL,
  `hl_creatorid` varchar(255) NOT NULL,
  `hl_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hl_modifierid` varchar(255) NOT NULL,
  `hl_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`hl_userid`,`hl_deptid`,`hl_empcode`,`hl_datefrom`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------------
--
-- Table structure for table hra_grade_city`
--

CREATE TABLE `hra_grade_city` (
  `hgc_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `hgc_gradename` varchar(255)  Default NULL,
  `hgc_place` blob  DEFAULT NULL,
  `hgc_distancecover` varchar(255)  DEFAULT NULL,
  `hgc_description` blob  DEFAULT NULL,
  `hgc_creatorid` varchar(255) DEFAULT NULL,
  `hgc_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hgc_modifierid` varchar(255) NOT NULL,
  `hgc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into hra_grade_city values (1,'Grade-I(a)','Chennai City','32 Kms','Chennai city and palces around the city at a distance not exceeding 32 kms from city limits. If the radius of 32 Kms. Fall within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving house rent allowance (HRA) as a admissible to Grade-I(a) place.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into hra_grade_city values (2,'Grade-I(b)','Coimbatore(UA),Madurai(UA),Salem(UA),Tiruppur(UA),Tiruchirappali(UA),Erode(UA),','16 Kms','Given palces around then at a distance not exceeding 16 kms. from the city limits and if the radius of 16 Kms. falls within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving House rent Allownace (HRA) as a admissible to Grade-I(b) palces.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);

insert into hra_grade_city values (3,'Grade-II','Ambur,Arakkonam, Arani, Aruppukkottai, Attur / Bhavani (UA), Bodinayakkanur /,Chengalpattu, Chidambaram (UA), Coonoor (UA), Cuddalore / Dharapuram, Dharmapuri,Dindigul / Erode (UA) / Gobi-chettipalayam, Gudiyattam (UA) / Hosur / Kadaiyanallur, Kambam,Kanchipuram (UA), Karaikkudi (UA), Karur (UA), Kovilpatti, Krishnagiri, Kumbakonam (UA) /Mannargudi, Mayiladuthurai, Mettuppalaiyam, Mettur / Nagappattinam (UA), Nagercoil,Namakkal, Neyveli (UA) / Palani (UA), Panruti, Paramakkudi, Pattukkottai, Pollachi (UA),Pudukkottai, Puliyangudi / Rajapalayam, Ramanathapuram / Sankarankoil, Sivakasi (UA),Srivilliputtur / Theni-Allinagaram, Tenkasi, Thanjavur, Thiruvarur, Tindivanam, Tiruchengode,Tirunelveli (UA), Tiruppattur, Tiruppur (UA), Tiruvannamalai, Thoothukkudi (UA) /Udhagamandalam, Udumalaippettai / Valparai, Vanyambadi (UA), Vellore (UA), Villupuram,Virudhunagar, Virudhachalam','8 Kms','All other Muncipal Corporations and Special Grade Muncipalities and palces around 8 Kms from town limits. If the radius of 8 Kms. Falls within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving rent Allowance (HRA) as a admissible to Grade-II Palce.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into hra_grade_city values (4,'Grade-III','','','All other Muncipalities (expect Special Grade) and Taluka Headquarters irrespective of local body status.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into hra_grade_city values (5,'Grade-IV','Unclassified Places','','','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);

-- -------------------------------------------------------------------
--
-- Table structure for table hra_grade`
--

CREATE TABLE `hra_grade` (
  `hg_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `hg_workingtype` varchar(255)  Default NULL,
  `hg_paycomm` VARCHAR(255) NULL,
  `hg_payscaleid` int(11)  DEFAULT NULL,
  `hg_payrange` VARCHAR(255) NULL,
  `hg_gradeid` int(11)  DEFAULT NULL,
  `hg_amount` double  DEFAULT NULL,
  `hg_creatorid` varchar(255) DEFAULT NULL,
  `hg_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hg_modifierid` varchar(255) NOT NULL,
  `hg_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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


INSERT INTO `leave_type_master` (`lt_id`, `lt_name`, `lt_code`, `lt_short`, `lt_value`, `lt_remarks`) VALUES ('1', 'Extra Ordinary Leave', 'EOL ', 'EOL ', '20', 'Extra Ordinary Leave'), ('2', 'Earned Leave', 'EL ', 'EL ', '30', 'Earned Leave'), ('3', 'Meternity Leave', 'ML', 'ML', '30', 'Meternity Leave'), ('4', 'Unearned Leave On Medical Leave', 'UELML', 'UEL ON ML', '30', 'Unearned Leave On Medical Leave');
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
`la_upfile` VARCHAR(255) NULL,
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

-- -------------------------------------------------------------------
--
-- Table structure for table `paymatrix`
--

CREATE TABLE `paymatrix` ( `pm_id` INT(11) NOT NULL AUTO_INCREMENT , `pm_sgmid` INT(11) NULL , 
`pm_pc` VARCHAR(50) NULL , 
`pm_wt` VARCHAR(50) NULL ,
`pm_level` VARCHAR(255) NOT NULL , 
`pm_sublevel1` VARCHAR(255) NULL ,
`pm_sublevel2` VARCHAR(255) NULL ,
`pm_sublevel3` VARCHAR(255) NULL ,
`pm_sublevel4` VARCHAR(255) NULL ,
`pm_sublevel5` VARCHAR(255) NULL ,
`pm_sublevel6` VARCHAR(255) NULL ,
`pm_sublevel7` VARCHAR(255) NULL ,
`pm_sublevel8` VARCHAR(255) NULL ,
`pm_sublevel9` VARCHAR(255) NULL , 
`pm_sublevel10` VARCHAR(255) NULL ,
`pm_sublevel11` VARCHAR(255) NULL ,
`pm_sublevel12` VARCHAR(255) NULL ,
`pm_sublevel13` VARCHAR(255) NULL ,
`pm_sublevel14` VARCHAR(255) NULL ,
`pm_sublevel15` VARCHAR(255) NULL ,
`pm_sublevel16` VARCHAR(255) NULL ,
`pm_sublevel17` VARCHAR(255) NULL ,
`pm_sublevel18` VARCHAR(255) NULL ,
`pm_sublevel19` VARCHAR(255) NULL , 
`pm_sublevel20` VARCHAR(255) NULL ,
`pm_sublevel21` VARCHAR(255) NULL ,
`pm_sublevel22` VARCHAR(255) NULL ,
`pm_sublevel23` VARCHAR(255) NULL ,
`pm_sublevel24` VARCHAR(255) NULL ,
`pm_sublevel25` VARCHAR(255) NULL ,
`pm_sublevel26` VARCHAR(255) NULL ,
`pm_sublevel27` VARCHAR(255) NULL ,
`pm_sublevel28` VARCHAR(255) NULL ,
`pm_sublevel29` VARCHAR(255) NULL , 
`pm_sublevel30` VARCHAR(255) NULL ,
`pm_sublevel31` VARCHAR(255) NULL ,
`pm_sublevel32` VARCHAR(255) NULL ,
`pm_sublevel33` VARCHAR(255) NULL ,
`pm_sublevel34` VARCHAR(255) NULL ,
`pm_sublevel35` VARCHAR(255) NULL ,
`pm_sublevel36` VARCHAR(255) NULL ,
`pm_sublevel37` VARCHAR(255) NULL ,
`pm_sublevel38` VARCHAR(255) NULL ,
`pm_sublevel39` VARCHAR(255) NULL , 
`pm_sublevel40` VARCHAR(255) NULL ,
`pm_creatorid` VARCHAR(255) NOT NULL , `pm_createdate` DATE NOT NULL , `pm_modifierid` VARCHAR(255) NULL , `pm_modifierdate` DATE NULL , PRIMARY KEY (`pm_id`)) ENGINE = InnoDB;

-- -------------------------------------------------------------------
--
-- Table structure for table `Salary data`
--

CREATE TABLE `salary_data` (
  `sald_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sald_empid` int(11)  NOT NULL,
  `sald_sheadid`  varchar(250) NOT NULL,
  `sald_shamount` double  Default 0,
  `sald_month` varchar(100)  NOT NULL,
  `sald_year` varchar(100) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Salary`
--
CREATE TABLE `salary` (
  `sal_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sal_empid` int(11)  NOT NULL,
  `sal_scid` int(11)  NOT NULL,
  `sal_uoid` int(11)  NOT NULL,
  `sal_deptid` int(11)  NOT NULL,
  `sal_desigid` int(11)  NOT NULL,
  `sal_sapost` varchar(250)  NOT NULL,
  `sal_ddoid` int(11)  NOT NULL,
  `sal_schemeid` int(11)  NOT NULL,
  `sal_payscaleid` int(11)  NOT NULL,
  `sal_bankaccno` varchar(250)  NOT NULL,
  `sal_worktype` varchar(250) NOT NULL,
  `sal_emptype` varchar(250) NOT NULL,
  `sal_group` varchar(250) NOT NULL,
  `sal_month` varchar(5)  NOT NULL,
  `sal_year` varchar(50) NOT NULL,
  `sal_totalincome` double  Default 0,
  `sal_totaldeduction` double  Default 0,
  `sal_netsalary` double  Default 0,
  `sal_status` varchar(255) NOT NULL,
  `sal_paiddate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sal_creationdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sal_creatorid` varchar(255) NOT NULL,
  `sal_updatedate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sal_modifierid` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------------
--
-- Table structure for table `Salary Head`
--

CREATE TABLE `salary_head` (
  `sh_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sh_code` varchar(50)  NOT NULL,
  `sh_name` varchar(255)  NOT NULL,
  `sh_tnt` varchar(255) default NULL,
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
-- ----------------------------------------------------------
--
-- Table structure for table `salary_grade_master`
--

CREATE TABLE `salary_grade_master` (
  `sgm_id` int(11) NOT NULL auto_increment,
  `sgm_pc` VARCHAR(50) NULL  DEFAULT '6th',
  `sgm_wt` VARCHAR(100) NULL , 
  `sgm_group` VARCHAR(5) NULL ,
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
      `sgma_pc` VARCHAR(50) NULL,
  `sgma_wt` VARCHAR(100) NULL , 
  `sgma_group` VARCHAR(5) NULL ,
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

-- --------------------------------------------------------

--
-- Table structure for table `salary_leave_entry`
--

CREATE TABLE `salary_leave_entry` (
  `sle_id` int(11) NOT NULL,
  `sle_empid` int(11) NOT NULL,
  `sle_year` int(5) DEFAULT NULL,
  `sle_month` varchar(50) DEFAULT NULL,
  `sle_pal` int(4) DEFAULT NULL,
  `sle_eol` int(4) DEFAULT NULL,
  `sle_creatorid` varchar(255) NOT NULL,
  `sle_creationdate` datetime NOT NULL,
  `sle_modifierid` varchar(255) NOT NULL,
  `sle_modifidate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `salary_leave_entry`
--
ALTER TABLE `salary_leave_entry`
  ADD PRIMARY KEY (`sle_id`),
  ADD UNIQUE KEY `sle_empid` (`sle_empid`,`sle_year`,`sle_month`);

--
-- AUTO_INCREMENT for table `salary_leave_entry`
--
ALTER TABLE `salary_leave_entry`
  MODIFY `sle_id` int(11) NOT NULL AUTO_INCREMENT;


-- --------------------------------------------------------

--
-- Table structure for table `salary_leave_entry_archive`
--

CREATE TABLE `salary_leave_entry_archive` (
  `slea_id` int(11) NOT NULL,
  `slea_sleid` int(11) NOT NULL,
  `slea_empid` int(11) NOT NULL,
  `slea_year` int(5) DEFAULT NULL,
  `slea_month` varchar(50) DEFAULT NULL,
  `slea_pal` int(4) DEFAULT NULL,
  `slea_eol` int(4) DEFAULT NULL,
  `slea_creatorid` varchar(255) NOT NULL,
  `slea_creationdate` datetime NOT NULL,
  `slea_modifierid` varchar(255) NOT NULL,
  `slea_modifidate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `salary_leave_entry`
--
ALTER TABLE `salary_leave_entry_archive`
  ADD PRIMARY KEY (`slea_id`),
  ADD UNIQUE KEY `slea_empid` (`slea_empid`,`slea_year`,`slea_month`);

--
-- AUTO_INCREMENT for table `salary_leave_entry`
--
ALTER TABLE `salary_leave_entry_archive`
  MODIFY `slea_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `salary_transfer_entry`
--

CREATE TABLE `salary_transfer_entry` ( 
	`ste_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`ste_empid` INT(11) NOT NULL , 
	`ste_year` INT(4) NOT NULL , 
	`ste_month` VARCHAR(50) NOT NULL , 
	`ste_days` VARCHAR(4) NULL , 
	`ste_hrafrom` VARCHAR(255) NULL , 
	`ste_hrato` VARCHAR(255) NULL , 
	`ste_ccafrom` VARCHAR(255) NULL ,
	`ste_ccato` VARCHAR(255) NULL,
	`ste_transit` VARCHAR(255) NULL , 
	`ste_creatorid` VARCHAR(255) NULL , 
	`ste_createdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , 
	`ste_modifierid` VARCHAR(255) NULL , 
	`ste_modifydate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , 
	PRIMARY KEY (`ste_id`)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for table `salary_transfer_entry_archive`
--

CREATE TABLE `salary_transfer_entry_archive` ( 
	`stea_id` INT(11) NOT NULL AUTO_INCREMENT ,
	`stea_steid` INT(11) NOT NULL , 
	`stea_empid` INT(11) NOT NULL , 
	`stea_year` INT(4) NOT NULL , 
	`stea_month` VARCHAR(50) NOT NULL , 
	`stea_days` VARCHAR(4) NULL , 
	`stea_hrafrom` VARCHAR(255) NULL , 
	`stea_hrato` VARCHAR(255) NULL , 
	`stea_ccafrom` VARCHAR(255) NULL, 
	`stea_ccato` VARCHAR(255) NULL,
	`stea_transit` VARCHAR(255) NULL , 
	`stea_creatorid` VARCHAR(255) NULL , 
	`stea_createdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , 
	`stea_modifierid` VARCHAR(255) NULL , 
	`stea_modifydate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , 
	PRIMARY KEY (`stea_id`)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

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

-- -------------------------------------------------------
--
-- Table structure for society_master_list
--

CREATE TABLE `society_master_list` (
  `soc_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `soc_sname` varchar(255) NOT NULL,
  `soc_scode` varchar(100)  NOT NULL,
  `soc_address` VARCHAR(255) NOT NULL,
  `soc_regdate` DATETIME(6) NOT NULL,
  `soc_regno` VARCHAR(255)  NULL,
  `soc_phone` VARCHAR(13) NULL , 
  `soc_mobile` VARCHAR(13) NULL ,
  `soc_email` VARCHAR(255) NULL, 
  `soc_pan` VARCHAR(50) NULL , 
  `soc_tan` VARCHAR(50) NULL ,
  `soc_gst` VARCHAR(50) NULL , 
  `soc_bname` VARCHAR(255) NULL , 
  `soc_bacno` VARCHAR(255) NULL , 
  `soc_bifsc` VARCHAR(50) NULL , 
  `soc_bmicr` VARCHAR(50) NULL ,
  `soc_bbranch` VARCHAR(255) NULL ,
  `soc_bactype` VARCHAR(50) NULL ,
  `soc_remark` varchar(255)  NULL,
  `soc_creatorid` varchar(255) NOT NULL,
  `soc_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `soc_modifierid` varchar(255) NOT NULL,
  `soc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`soc_sname`,`soc_scode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------
--
-- Table structure for societies
--

CREATE TABLE `societies` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `society_id` varchar(100)  NOT NULL,
  `society_head` varchar(255) NOT NULL,
  `society_secretary` varchar(255) NOT NULL,
  `society_treasurer` varchar(255) NOT NULL,
  `society_members` varchar(255) NOT NULL, 
  `society_totalvalues` varchar(255) NOT NULL,
   UNIQUE (`society_head`,`society_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- -------------------------------------------------------------------
--
-- Table structure for table `staff_academic_qualification`
--

CREATE TABLE `staff_academic_qualification` (
  `saq_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `saq_empid` int(11)  NOT NULL,
  `saq_dgree` varchar(255)  DEFAULT NULL,
  `saq_board_univ` varchar(255)  DEFAULT NULL,
  `saq_result` varchar(255)  DEFAULT NULL,
  `saq_yopass` varchar(255)  DEFAULT NULL,
  `saq_discipline` varchar(255)  DEFAULT NULL,
  `saq_creatorid` varchar(255) DEFAULT NULL,
  `saq_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `saq_modifierid` varchar(255) NOT NULL,
  `saq_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------------
--
-- Table structure for table `staff_technical_qualification`
--


CREATE TABLE `staff_technical_qualification` (
  `stq_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `stq_empid` int(11)  NOT NULL,
  `stq_dgree` varchar(255)  DEFAULT NULL,
  `stq_board_univ` varchar(255)  DEFAULT NULL,
  `stq_result` varchar(255)  DEFAULT NULL,
  `stq_dopass` varchar(255)  DEFAULT NULL,
  `stq_discipline` varchar(255)  DEFAULT NULL,
  `stq_creatorid` varchar(255) DEFAULT NULL,
  `stq_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `stq_modifierid` varchar(255) NOT NULL,
  `stq_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- -------------------------------------------------------------------
--
-- Table structure for table `Staff deputation perticulars`
--

CREATE TABLE `staff_deputation_perticulars` (
  `sdp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sdp_userid` int(11)  NOT NULL,
  `sdp_empcode` varchar(55)  NOT NULL,
  `sdp_deputation` varchar(255)  NOT NULL,
  `sdp_specification` varchar(50)  Default NULL,
  `sdp_fromdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdp_todate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdp_creatorid` varchar(255) NOT NULL,
  `sdp_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdp_modifierid` varchar(255) NOT NULL,
  `sdp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff department Exam passed details`
--

CREATE TABLE `staff_department_exam_perticulars` (
  `sdep_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sdep_userid` int(11)  NOT NULL,
  `sdep_empcode` varchar(55)  NOT NULL,
  `sdep_examname` varchar(255)  NOT NULL, 
  `sdep_specification` varchar(50)  Default NULL,
  `sdep_passdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdep_creatorid` varchar(255) NOT NULL,
  `sdep_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdep_modifierid` varchar(255) NOT NULL,
  `sdep_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------------
--
-- Table structure for table `Staff working arrangements`
--

CREATE TABLE `staff_working_arrangements_perticulars` (
  `swap_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `swap_userid` int(11)  NOT NULL,
  `swap_empcode` varchar(55)  NOT NULL,
  `swap_ocampus` varchar(255)  NOT NULL,
  `swap_ouo` varchar(255)  NOT NULL,
  `swap_odept` varchar(255)  NOT NULL,
  `swap_wcampus` varchar(255)  NOT NULL,
  `swap_wuo` varchar(255)  NOT NULL,
  `swap_wdept` varchar(255)  NOT NULL,
  `swap_fromdate` DATE NULL,
  `swap_todate` DATE NULL,
  `swap_creatorid` varchar(255) NOT NULL,
  `swap_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `swap_modifierid` varchar(255) NOT NULL,
  `swap_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff recruitment perticulars`
--

CREATE TABLE `staff_recruitment_perticulars` (
  `srp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `srp_userid` int(11)  NOT NULL,
  `srp_empcode` varchar(55)  NOT NULL,
  `srp_methodrecrtmnt` varchar(255)  NOT NULL,
  `srp_subcategory` varchar(255)  Default NULL,
  `srp_detail` varchar(255)  Default NULL,
  `srp_compassionname` varchar(255)  Default NULL,
  `srp_compassiondesig` varchar(255)  Default NULL,
  `srp_compassiondept` varchar(255)  Default NULL,
  `srp_creatorid` varchar(255) NOT NULL,
  `srp_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `srp_modifierid` varchar(255) NOT NULL,
  `srp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff disciplinary actions perticulars`
--

CREATE TABLE `staff_disciplinary_actions_perticulars` (
  `sdap_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sdap_userid` int(11)  NOT NULL,
  `sdap_empcode` varchar(55)  NOT NULL,
  `sdap_punishnature` varchar(255)  NOT NULL,
  `sdap_punishreason` varchar(255)  NOT NULL,
  `sdap_punishstatus` varchar(255)  NOT NULL,
  `sdap_fromdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdap_todate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdap_creatorid` varchar(255) NOT NULL,
  `sdap_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sdap_modifierid` varchar(255) NOT NULL,
  `sdap_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `Staff PostwBudget arrangements`
--

CREATE TABLE `staff_postwbudget_particulars` (
  `spwp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `spwp_empid` int(11)  NOT NULL,
  `spwp_uitid` int(11)  NOT NULL,
  `spwp_ocampus` varchar(255)  NOT NULL,
  `spwp_ouo` varchar(255)  NOT NULL,
  `spwp_odept` varchar(255)  NOT NULL,
  `spwp_wcampus` varchar(255)  NOT NULL,
  `spwp_wuo` varchar(255)  NOT NULL,
  `spwp_wdept` varchar(255)  NOT NULL,
  `spwp_creatorid` varchar(255) NOT NULL,
  `spwp_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `spwp_modifierid` varchar(255) NOT NULL,
  `spwp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for table `staff_promotionals_details`
--

CREATE TABLE `staff_promotionals_details` (
  `spd_id` int(11) NOT NULL,
  `spd_empid` int(11) NOT NULL,
  `spd_wtype` varchar(255) NOT NULL,
  `spd_paycom` varchar(11) DEFAULT NULL,
  `spd_agp` varchar(255) DEFAULT NULL,
  `spd_agpdate` date DEFAULT NULL,
  `spd_level` varchar(255) DEFAULT NULL,
  `spd_leveldate` date DEFAULT NULL,
  `spd_group` varchar(255) DEFAULT NULL,
  `spd_designation` varchar(255) DEFAULT NULL,
  `spd_dojinpost` date DEFAULT NULL,
  `spd_selgradedate` date DEFAULT NULL,
  `spd_specialgrddate` date DEFAULT NULL,
  `spd_creatordate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spd_creatorid` varchar(255) NOT NULL,
  `spd_modifierid` varchar(255) NOT NULL,
  `spd_modifydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spd_grade` VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details`
  ADD PRIMARY KEY (`spd_id`);

--
-- AUTO_INCREMENT for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details`
  MODIFY `spd_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `staff_promotionals_details`
--

CREATE TABLE `staff_promotionals_details_archive` (
  `spda_id` int(11) NOT NULL,
  `spda_spdid` int(11) NOT NULL,
  `spda_empid` int(11) NOT NULL,
  `spda_wtype` varchar(255) NOT NULL,
  `spda_paycom` varchar(11) DEFAULT NULL,
  `spda_agp` varchar(255) DEFAULT NULL,
  `spda_agpdate` date DEFAULT NULL,
  `spda_level` varchar(255) DEFAULT NULL,
  `spda_leveldate` date DEFAULT NULL,
  `spda_group` varchar(255) DEFAULT NULL,
  `spda_designation` varchar(255) DEFAULT NULL,
  `spda_dojinpost` date DEFAULT NULL,
  `spda_selgradedate` date DEFAULT NULL,
  `spda_specialgrddate` date DEFAULT NULL,
  `spda_creatordate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spda_creatorid` varchar(255) NOT NULL,
  `spda_modifierid` varchar(255) NOT NULL,
  `spda_modifydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spda_grade` VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details_archive`
  ADD PRIMARY KEY (`spda_id`);

--
-- AUTO_INCREMENT for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details_archive`
  MODIFY `spda_id` int(11) NOT NULL AUTO_INCREMENT;
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
  `spd_int_medal` INT(11) NULL,
  `spd_nat_medal` INT(11) NULL, 
  `spd_uni_medal` INT(11) NULL, 
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
  `ul_fromsession` VARCHAR(70) NULL ,
  `ul_dateto` datetime NOT NULL,
  `ul_tosession` VARCHAR(70) NULL,
  `ul_status` varchar(50) NOT NULL,
  `ul_creatorid` varchar(255) NOT NULL,
  `ul_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ul_modifierid` varchar(255) NOT NULL,
  `ul_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`ul_userid`,`ul_empcode`,`ul_datefrom`)
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
        uit_uso_no  varchar(255) default  NULL ,
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
        uit_dateofrelief datetime default '1970-01-01 00:00:00',
        uit_dateofjoining datetime default '1970-01-01 00:00:00',
        uit_email_sentto blob default NULL,
	uit_transfertype varchar(255) default NULL,
        uit_vactype_from VARCHAR(255) NULL ,
        uit_ddoid_from INT(8) NULL,
        uit_group_from VARCHAR(5) NULL ,
        PRIMARY KEY (uit_id)
)ENGINE = InnoDB;

-- -------------------------------------------------------------------
--
-- Table structure for table rent_recovery`
--

CREATE TABLE `rent_recovery` (
  `rr_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `rr_payscaleid` int(11)  Default NULL,
  `rr_payrange` VARCHAR(255) NULL,
  `rr_gradeid` int(11)  DEFAULT NULL,
  `rr_workingtype` varchar(255)  DEFAULT NULL,
  `rr_paycomm` VARCHAR(255) NULL,
  `rr_percentage` double  DEFAULT NULL,
  `rr_description` varchar(255)  DEFAULT NULL,
  `rr_creatorid` varchar(255) DEFAULT NULL,
  `rr_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `rr_modifierid` varchar(255) NOT NULL,
  `rr_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rent_free_hra` ( 
	`rfh_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`rfh_paycomm` VARCHAR(50) NOT NULL , 
	`rfh_gradeid` VARCHAR(50) NOT NULL , 
	`rfh_payrange` VARCHAR(50) NOT NULL , 
	`rfh_amount` DOUBLE NOT NULL , 
	`rfh_createdate` DATE NULL , 
	`rfh_creator` VARCHAR(255) NULL , 
	`rfh_modifydate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , 
	`rfh_modifier` VARCHAR(255) NULL , 
	PRIMARY KEY (`rfh_id`)
) ENGINE = InnoDB;


CREATE TABLE `cca_grade_city` ( 
	`cgc_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`cgc_gradename` VARCHAR(255) NOT NULL , 
	`cgc_place` BLOB NOT NULL , 
	`cgc_distance` VARCHAR(50) NOT NULL , 
	`cgc_desc` BLOB NULL ,
	`cgc_creator` VARCHAR(255) NULL , 
	`cgc_createdate` DATE NULL , 
	`cgc_modifier` VARCHAR(255) NULL , 
	`cgc_modifydate` TIMESTAMP NOT NULL , 
	PRIMARY KEY (`cgc_id`)
) ENGINE = InnoDB;

insert into cca_grade_city values (1,'CCA-Type-I','Chennai City','32 Kms','Chennai city and palces around the city at a distance not exceeding 32 kms from city limits. If the radius of 32 Kms. Fall within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving house rent allowance (HRA) as a admissible to Grade-I(a) place.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into cca_grade_city values (2,'CCA-Type-II','Coimbatore(UA),Madurai(UA),Salem(UA),Tiruppur(UA),Tiruchirappali(UA),Erode(UA),','16 Kms','Given palces around then at a distance not exceeding 16 kms. from the city limits and if the radius of 16 Kms. falls within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving House rent Allownace (HRA) as a admissible to Grade-I(b) palces.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into cca_grade_city values (3,'CCA-Type-No','','','','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);

-- -------------------------------------------------------------------
--
-- Table structure for table ccaallowance_calculation`
--

CREATE TABLE `ccaallowance_calculation` (
  `cca_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `cca_payscaleid` int(11)  DEFAULT NULL,
  `cca_payrange` VARCHAR(255) NULL,
  `cca_gradeid` varchar(100)  DEFAULT NULL,
  `cca_workingtype` varchar(255)  Default NULL,
  `cca_paycomm` VARCHAR(255) NULL,
  `cca_amount` double  DEFAULT NULL,
  `cca_description` varchar(255) DEFAULT NULL,
  `cca_creatorid` varchar(255) DEFAULT NULL,
  `cca_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `cca_modifierid` varchar(255) NOT NULL,
  `cca_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

