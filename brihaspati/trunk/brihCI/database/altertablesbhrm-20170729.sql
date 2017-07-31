use bhrm; 

DROP TABLE IF EXISTS `applicant_data`;
CREATE TABLE `applicant_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apd_no` varchar(11) DEFAULT NULL,
  `apd_masterid` int(11) DEFAULT NULL,
  `apd_regdate` datetime DEFAULT NULL,
  `apd_reg_complete_date` datetime DEFAULT NULL,
  `apd_advno` int(11) DEFAULT NULL,
  `apd_postcode` varchar(200) DEFAULT NULL,
  `apd_dateres` varchar(255) DEFAULT NULL,
  `apd_lastdatecompapp` datetime DEFAULT NULL,
  `apd_postapplifor` varchar(255) DEFAULT NULL,
  `apd_depart` varchar(255) DEFAULT NULL,
  `apd_fieldofspecial` varchar(255) DEFAULT NULL,
  `apd_categappli` varchar(255) DEFAULT NULL,
  `apd_fname` varchar(255) DEFAULT NULL,
  `apd_midname` varchar(255) DEFAULT NULL,
  `apd_lastname` varchar(255) DEFAULT NULL,
  `apd_father_husbandname` varchar(255) DEFAULT NULL,
  `apd_mothername` varchar(255) DEFAULT NULL,
  `apd_aadharno` int(50) DEFAULT NULL,
  `apd_correspondenceadd` varchar(500) DEFAULT NULL,
  `apd_permanantadd` varchar(500) DEFAULT NULL,
  `apd_dob` date DEFAULT NULL,
  `apd_dop` date DEFAULT NULL,
  `apd_agedate` date DEFAULT NULL,
  `apd_gender` varchar(200) DEFAULT NULL,
  `apd_category` varchar(200) DEFAULT NULL,
  `apd_disability` varchar(200) DEFAULT NULL,
  `apd_maritialstatus` varchar(200) DEFAULT NULL,
  `apd_nationality` varchar(200) DEFAULT NULL,
  `apd_religion` varchar(200) DEFAULT NULL,
  `apd_remark` varchar(255) DEFAULT NULL,
  `apd_ext1` varchar(200) DEFAULT NULL,
  `apd_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant_id` int(11) DEFAULT NULL,
  `app_category` varchar(255) DEFAULT NULL,
  `app_catname` varchar(255) DEFAULT NULL,
  `app_subcategory` varchar(255) DEFAULT NULL,
  `app_activities` varchar(255) DEFAULT NULL,
  `app_maximumscore` varchar(255) DEFAULT NULL,
  `app_noofarticle` varchar(255) DEFAULT NULL,
  `app_scoreclaimed` varchar(255) DEFAULT NULL,
  `app_scoreverified` varchar(255) DEFAULT NULL,
  `app_varifiername` varchar(255) DEFAULT NULL,
  `app_varifydate` varchar(255) DEFAULT NULL,
  `app_remark` varchar(255) DEFAULT NULL,
  `app_ext1` varchar(200) DEFAULT NULL,
  `app_ext2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_performance` (
  `aperfor_id` int(11) NOT NULL AUTO_INCREMENT,
  `aperfor_amid` int(11) DEFAULT NULL,
  `aperfor_teachexp` varchar(255) DEFAULT NULL,
  `aperfor_from` varchar(255) DEFAULT NULL,
  `aperfor_to` varchar(255) DEFAULT NULL,
  `aperfor_totalyearmonth` varchar(255) DEFAULT NULL,
  `aperfor_remark` varchar(255) DEFAULT NULL,
  `aperfor_ext1` varchar(255) DEFAULT NULL,
  `aperfor_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aperfor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_research` (
  `ares_id` int(11) NOT NULL AUTO_INCREMENT,
  `ares_amid` int(11) DEFAULT NULL,
  `ares_researchproj` int(11) DEFAULT NULL,
  `ares_phd_thesisaward` varchar(255) DEFAULT NULL,
  `ares_phd_submitted` varchar(255) DEFAULT NULL,
  `ares_phd_inprogress` varchar(255) DEFAULT NULL,
  `ares_mphill_thesisaward` varchar(255) DEFAULT NULL,
  `ares_mphill_submitted` varchar(255) DEFAULT NULL,
  `ares_mphill_inprogress` varchar(255) DEFAULT NULL,
  `ares_pmah` varchar(255) DEFAULT NULL,
  `ares_remark` varchar(255) DEFAULT NULL,
  `ares_ext1` varchar(255) DEFAULT NULL,
  `ares_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ares_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_research_degree` (
  `aresdeg_id` int(11) NOT NULL AUTO_INCREMENT,
  `aresdeg_amid` int(11) DEFAULT NULL,
  `aresdeg_degreename` varchar(255) DEFAULT NULL,
  `aresdeg_universityname` varchar(500) DEFAULT NULL,
  `aresdeg_dateofsubmission` datetime DEFAULT NULL,
  `aresdeg_dateofaward` datetime DEFAULT NULL,
  `aresdeg_titleofthesis` varchar(255) DEFAULT NULL,
  `aresdeg_asper_ugc_reg_2009` varchar(255) DEFAULT NULL,
  `aresdeg_remark` varchar(255) DEFAULT NULL,
  `aresdeg_ext1` varchar(255) DEFAULT NULL,
  `aresdeg_ext2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aresdeg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_supportdata` (
  `asuppd_id` int(11) NOT NULL AUTO_INCREMENT,
  `asuppd_amid` int(11) DEFAULT NULL,
  `aperfor_8a` varchar(255) DEFAULT NULL,
  `aperfor_8b` varchar(255) DEFAULT NULL,
  `aperfor_8c` varchar(255) DEFAULT NULL,
  `aperfor_8d` varchar(255) DEFAULT NULL,
  `aperfor_8e` varchar(255) DEFAULT NULL,
  `aperfor_9a` varchar(255) DEFAULT NULL,
  `aperfor_9b` varchar(255) DEFAULT NULL,
  `aperfor_9c` varchar(255) DEFAULT NULL,
  `aperfor_9d` varchar(255) DEFAULT NULL,
  `aperfor_9e` varchar(255) DEFAULT NULL,
  `aperfor_10i` varchar(255) DEFAULT NULL,
  `aperfor_10ii` varchar(255) DEFAULT NULL,
  `aperfor_10iii` varchar(255) DEFAULT NULL,
  `aperfor_11` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`asuppd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_total_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ata_applicantid` int(11) DEFAULT NULL,
  `ata_apicategory` varchar(255) DEFAULT NULL,
  `ata_scoreclaimed` varchar(200) DEFAULT NULL,
  `ata_scoreverified` varchar(255) DEFAULT NULL,
  `ata_verifiername` varchar(255) DEFAULT NULL,
  `ata_verifierdate` varchar(255) DEFAULT NULL,
  `ata_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `applicant_uploaddata` (
  `aupd_id` int(11) NOT NULL AUTO_INCREMENT,
  `aupd_amid` int(11) DEFAULT NULL,
  `aupd_enclosure1` varchar(255) DEFAULT NULL,
  `aupd_enclosure2` varchar(255) DEFAULT NULL,
  `aupd_enclosure3` varchar(255) DEFAULT NULL,
  `aupd_enclosure4` varchar(255) DEFAULT NULL,
  `aupd_enclosure5` varchar(255) DEFAULT NULL,
  `aupd_enclosure6` varchar(255) DEFAULT NULL,
  `aupd_enclosure7` varchar(255) DEFAULT NULL,
  `aupd_enclosure8` varchar(255) DEFAULT NULL,
  `aupd_enclosure9` varchar(255) DEFAULT NULL,
  `aupd_enclosure10` varchar(255) DEFAULT NULL,
  `aupd_enclosure11` varchar(255) DEFAULT NULL,
  `aupd_enclosure12` varchar(255) DEFAULT NULL,
  `aupd_enclosure13` varchar(255) DEFAULT NULL,
  `aupd_enclosure14` varchar(255) DEFAULT NULL,
  `aupd_enclosure15` varchar(255) DEFAULT NULL,
  `aupd_uploaddeclaration` varchar(255) DEFAULT NULL,
  `aupd_uploadnoc` varchar(255) DEFAULT NULL,
  `aupd_photo` varchar(500) DEFAULT NULL,
  `aupd_signature` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`aupd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `form_shortname` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_advno` varchar(255) DEFAULT NULL,
  `form_code` varchar(255) DEFAULT NULL,
  `form_shortname` varchar(255) DEFAULT NULL,
  `form_name` varchar(255) DEFAULT NULL,
  `form_posttype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `job_open` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_vacnacycode` varchar(255) DEFAULT NULL,
  `job_adverno` varchar(255) NOT NULL,
  `job_postcode` varchar(50) DEFAULT NULL,
  `job_nameofpost` varchar(255) NOT NULL,
  `job_department` varchar(255) NOT NULL,
  `job_vacancytotal` int(5) NOT NULL,
  `job_vacancysc` int(3) NOT NULL,
  `job_vacancyst` int(3) NOT NULL,
  `job_vacancyobc` int(3) NOT NULL,
  `job_vacancyur` int(3) NOT NULL,
  `job_vacancypwd` int(3) NOT NULL,
  `job_gradepay` varchar(255) NOT NULL,
  `job_emoluments` varchar(255) NOT NULL,
  `job_agelimit` varchar(255) NOT NULL,
  `job_group` varchar(100) NOT NULL,
  `job_essential` blob NOT NULL,
  `job_experience` blob NOT NULL,
  `job_desirable` blob NOT NULL,
  `job_responsibles` blob NOT NULL,
  `job_startdateonlineform` datetime NOT NULL,
  `job_lastdateonlineform` datetime NOT NULL,
  `job_lastdateformreach` datetime NOT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;


