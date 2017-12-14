use olas;

CREATE TABLE IF NOT EXISTS `student_admissionstatus` ( 
	`sas_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`sas_hallticketno` INT(11) NOT NULL , 
	`sas_studentmasterid` INT(11) NOT NULL , 
	`sas_prgid` INT(11) NOT NULL , 
	`sas_admissiondate` DATE NOT NULL , 
	`sas_admissionstatus` VARCHAR(255) NOT NULL DEFAULT 'Provisional' , 
	`sas_confirmdate` DATE NULL , 
	`sas_remark` VARCHAR(255) NULL , 
	PRIMARY KEY (`sas_id`), 
	UNIQUE (`sas_hallticketno`)
) ENGINE = InnoDB;

RENAME TABLE `admissionstep` TO `student_admissionstep`;

CREATE TABLE `student_transfer` (
    `st_id` INT(11) NOT NULL AUTO_INCREMENT , 
    `st_hallticketno` INT(11) NULL , 
    `st_smid` INT(11) NULL , 
    `st_progid` INT(11) NULL ,
    `st_newprogid` INT(11) NULL ,
    `st_deptid` INT(11) NULL ,
    `st_newdeptid` INT(11) NULL ,
    `st_creatorid` VARCHAR(255) NULL ,
    `st_createdate` DATE NOT NULL , 
     PRIMARY KEY (`st_id`),
     UNIQUE (`st_hallticketno`)
) ENGINE = InnoDB;

CREATE TABLE `student_admission_cancel` ( 
        `sac_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`sac_hallticketno` INT(21) NOT NULL , 
	`sac_smid` INT(11) NOT NULL , 
	`sac_progid` INT(11) NOT NULL , 
	`sac_reson` VARCHAR(500) NOT NULL , 
	`sac_feesrefundamount` VARCHAR(255) NOT NULL , 
	`sac_feesrefundstatus` VARCHAR(255) NOT NULL ,
	 `sac_canceldate` DATE NOT NULL , 
	`sac_creatorid` VARCHAR(255) NOT NULL , 
	`sac_createdate` DATE NOT NULL , 
	PRIMARY KEY (`sac_id`), 
	UNIQUE (`sac_hallticketno`)
) ENGINE = InnoDB;
