alter table student_marks add smr_extyid int(11) after smr_sem;

CREATE TABLE `admissionstudent_master` (
  `asm_id` int(11) NOT NULL,
  `asm_userid` int(11) NOT NULL,
  `asm_applicationno` varchar(255) DEFAULT NULL,
  `asm_meritno` varchar(255) DEFAULT NULL,
  `asm_testname` varchar(255) DEFAULT NULL,
  `asm_universitycode` varchar(255) DEFAULT NULL,
  `asm_sccode` varchar(255) DEFAULT NULL,
  `asm_rollno` varchar(255) DEFAULT NULL,
  `asm_fname` varchar(255) DEFAULT NULL,
  `asm_mname` varchar(255) DEFAULT NULL,
  `asm_lname` varchar(255) DEFAULT NULL,
  `asm_nameinhindi` varchar(255) DEFAULT NULL,
  `asm_dob` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `asm_pob` varchar(255) DEFAULT NULL,
  `asm_email` varchar(255) DEFAULT NULL,
  `asm_secemail` varchar(255) DEFAULT NULL,
  `asm_mobile` varchar(12) DEFAULT NULL,
  `asm_category` varchar(255) DEFAULT NULL,
  `asm_caste` varchar(255) DEFAULT NULL,
  `asm_gender` varchar(255) DEFAULT NULL,
  `asm_mstatus` varchar(255) DEFAULT NULL,
  `asm_bloodgroup` varchar(255) DEFAULT NULL,
  `asm_nationality` varchar(255) DEFAULT NULL,
  `asm_minority` varchar(255) DEFAULT NULL,
  `asm_reservationtype` varchar(255) DEFAULT NULL,
  `asm_phyhandicaped` varchar(255) DEFAULT NULL,
  `asm_uid` varchar(255) DEFAULT NULL,
  `asm_panno` varchar(255) DEFAULT NULL,
  `asm_passportno` varchar(255) DEFAULT NULL,
  `asm_religion` varchar(255) DEFAULT NULL,
  `asm_photo` varchar(255) DEFAULT NULL,
  `asm_signature` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_master`
  ADD PRIMARY KEY (`asm_id`);
ALTER TABLE `admissionstudent_master`
  MODIFY `asm_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `admissionstudent_parent` (
  `aspar_id` int(11) NOT NULL,
  `aspar_asmid` int(11) NOT NULL,
  `aspar_fathername` varchar(255) DEFAULT NULL,
  `aspar_fatheremail` varchar(255) DEFAULT NULL,
  `aspar_fatherphoneno` varchar(255) DEFAULT NULL,
  `aspar_fatheroccupation` varchar(255) DEFAULT NULL,
  `aspar_fatherincome` varchar(255) DEFAULT NULL,
  `aspar_mothername` varchar(255) DEFAULT NULL,
  `aspar_motheremail` varchar(255) DEFAULT NULL,
  `aspar_motherphoneno` varchar(255) DEFAULT NULL,
  `aspar_motheroccupation` varchar(255) DEFAULT NULL,
  `aspar_motherincome` varchar(255) DEFAULT NULL,
  `aspar_paddress` varchar(255) DEFAULT NULL,
  `aspar_ppostoffice` VARCHAR(255) DEFAULT NULL,
  `aspar_pcity` varchar(255) DEFAULT NULL,
  `aspar_pdistrict` varchar(255) DEFAULT NULL,
  `aspar_pstate` varchar(255) DEFAULT NULL,
  `aspar_pcountry` varchar(255) DEFAULT NULL,
  `aspar_ppincode` varchar(255) DEFAULT NULL,
  `aspar_caddress` varchar(255) DEFAULT NULL,
  `aspar_cpostoffice` VARCHAR(255) DEFAULT NULL,
  `aspar_ccity` varchar(255) DEFAULT NULL,
  `aspar_cdistrict` varchar(255) DEFAULT NULL,
  `aspar_cstate` varchar(255) DEFAULT NULL,
  `aspar_ccountry` varchar(255) DEFAULT NULL,
  `aspar_cpincode` varchar(255) DEFAULT NULL,
  `aspar_garname` varchar(255) DEFAULT NULL,
  `aspar_garemail` varchar(255) DEFAULT NULL,
  `aspar_garphoneno` varchar(255) DEFAULT NULL,
  `aspar_garoccupation` varchar(255) DEFAULT NULL,
  `aspar_garincome` varchar(255) DEFAULT NULL,
  `aspar_garaddress` varchar(255) DEFAULT NULL,
  `aspar_garcity` varchar(255) DEFAULT NULL,
  `aspar_gardistrict` varchar(255) DEFAULT NULL,
  `aspar_garstate` varchar(255) DEFAULT NULL,
  `aspar_garcountry` varchar(255) DEFAULT NULL,
  `aspar_garpincode` varchar(255) DEFAULT NULL,
  `aspar_fathernamehindi` varchar(255) DEFAULT NULL,
  `aspar_mothernamehindi` varchar(255) DEFAULT NULL,
  `aspar_garnamehindi` varchar(255) DEFAULT NULL,
  `aspar_ext` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_parent`
  ADD PRIMARY KEY (`aspar_id`);
ALTER TABLE `admissionstudent_parent`
  MODIFY `aspar_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `admissionstudent_education` (
  `asedu_id` int(11) NOT NULL,
  `asedu_asmid` int(11) NOT NULL,
  `asedu_class` varchar(255) NOT NULL,
  `asedu_institution` varchar(255) NOT NULL,
  `asedu_board` varchar(255) NOT NULL,
  `asedu_subject` varchar(255) NOT NULL,
  `asedu_passingyear` varchar(255) NOT NULL,
  `asedu_resultstatus` varchar(255) NOT NULL,
  `asedu_maxmarks` varchar(255) NOT NULL,
  `asedu_marksobtained` varchar(255) NOT NULL,
  `asedu_percentage` varchar(255) NOT NULL,
  `asedu_ext1` varchar(255) DEFAULT NULL,
  `asedu_ext2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_education`
  ADD PRIMARY KEY (`asedu_id`);
ALTER TABLE `admissionstudent_education`
  MODIFY `asedu_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `admissionstudent_entrance_exam` (
  `aseex_id` int(11) NOT NULL,
  `aseex_asmid` int(11) NOT NULL,
  `aseex_examname` varchar(255) NOT NULL,
  `aseex_rollno` varchar(255) NOT NULL,
  `aseex_rank` int(11) NOT NULL,
  `aseex_score` int(11) NOT NULL,
  `aseex_state` varchar(255) NOT NULL,
  `aseex_subject` varchar(255) NOT NULL,
  `aseex_passingyear` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_entrance_exam`
  ADD PRIMARY KEY (`aseex_id`);
ALTER TABLE `admissionstudent_entrance_exam`
  MODIFY `aseex_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `admissionstudent_employment` (
  `asemp_id` int(11) NOT NULL,
  `asemp_asmid` int(11) NOT NULL,
  `asemp_officename` varchar(255) NOT NULL,
  `asemp_post` varchar(255) NOT NULL,
  `asemp_pay` varchar(255) NOT NULL,
  `asemp_grade` varchar(255) NOT NULL,
  `asemp_appoinmentnature` varchar(255) NOT NULL,
  `asemp_doj` varchar(255) NOT NULL,
  `asemp_dol` varchar(255) NOT NULL,
  `asemp_remarks` varchar(255) NOT NULL,
  `asemp_experience` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_employment`
  ADD PRIMARY KEY (`asemp_id`);
ALTER TABLE `admissionstudent_employment`
  MODIFY `asemp_id` int(11) NOT NULL AUTO_INCREMENT;
