

CREATE TABLE `admissionstep` ( 
	`id` INT(11) NOT NULL AUTO_INCREMENT , 
	`application_no` VARCHAR(255) NOT NULL , 
	`student_masterid` VARCHAR(255) NOT NULL , 
	`step1_status` VARCHAR(255) NOT NULL , 
	`step1_date` VARCHAR(255) NOT NULL ,
	`step2_status` VARCHAR(255) NOT NULL , 
	`step2_date` VARCHAR(255) NOT NULL ,
	`step3_status` VARCHAR(255) NOT NULL , 
	`step3_date` VARCHAR(255) NOT NULL ,
	`step4_status` VARCHAR(255) NOT NULL , 
	`step4_date` VARCHAR(255) NOT NULL ,
	`step5_status` VARCHAR(255) NOT NULL , 
	`step5_date` VARCHAR(255) NOT NULL , 
	`ext1` VARCHAR(255) NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;


insert into user_role_type values (1,1,1,1,'Administrator','');
alter table student_parent add  `spar_garincome` varchar(255) DEFAULT NULL after `spar_garoccupation`; 
alter table student_parent add  `spar_motherincome` varchar(255) DEFAULT NULL after `spar_motheroccupation`; 
alter table student_parent add  `spar_fatherincome` varchar(255) DEFAULT NULL after `spar_fatheroccupation`; 
 
alter table student_master add  `sm_mobile` varchar(12) DEFAULT NULL after `sm_secemail`;
alter table user_role_type change `deptid` `deptid` int(10) DEFAULT NULL; 
alter table seat_program_studycenter change  `psc_modifydate` `spsc_modifydate` varchar(255);

alter table subject_paper add UNIQUE (`subp_degree`, `subp_acadyear`, `subp_sub_id`,`subp_subtype`,`subp_paperno`);
