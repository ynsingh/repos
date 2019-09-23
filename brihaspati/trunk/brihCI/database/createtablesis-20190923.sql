/********************table structure  salary_earnings_head******************************/
CREATE TABLE `salary_earnings_head` (
        `seh_id` INT(11) NOT NULL AUTO_INCREMENT ,
        `seh_empid` INT(11) NOT NULL ,
        `seh_headid`  int(11)  NOT NULL,
        `seh_headname` VARCHAR(255) NOT NULL ,
        `seh_headamount` DECIMAL (15, 2) DEFAULT 0.00,
        `seh_month` VARCHAR(50) NOT NULL ,
        `seh_year` VARCHAR(100) NOT NULL ,
        `seh_creator` VARCHAR(255) NULL ,
        `seh_createdate` DATE NULL ,
        `seh_modifier` VARCHAR(255) NULL ,
        `seh_modifydate` TIMESTAMP NOT NULL ,
        PRIMARY KEY (`seh_id`)
) ENGINE = InnoDB;

/********************table structure salary_subsdeduction_head******************************/

CREATE TABLE `salary_subsdeduction_head` (
        `ssdh_id` INT(11) NOT NULL AUTO_INCREMENT ,
	`ssdh_empid` INT(11) NOT NULL ,
        `ssdh_headid`  int(11)  NOT NULL,
        `ssdh_headname` VARCHAR(255) NOT NULL ,
        `ssdh_headno` VARCHAR(255)  NULL ,
        `ssdh_headamount` DECIMAL (15, 2) DEFAULT 0.00,
	`ssdh_totalintall`  int(11)  NULL,
        `ssdh_intallmentno`  int(11)  NULL,
        `ssdh_installamount` DECIMAL (15, 2) DEFAULT 0.00,
	`ssdh_month` VARCHAR(50) NOT NULL ,
        `ssdh_year` VARCHAR(100) NOT NULL ,
        `ssdh_creator` VARCHAR(255) NULL ,
        `ssdh_createdate` DATE NULL ,
        `ssdh_modifier` VARCHAR(255) NULL ,
        `ssdh_modifydate` TIMESTAMP NOT NULL ,
        PRIMARY KEY (`ssdh_id`)
) ENGINE = InnoDB;

/********************table structure salary_loan_head******************************/

CREATE TABLE `salary_loan_head` (
        `slh_id` INT(11) NOT NULL AUTO_INCREMENT ,
	`slh_empid` INT(11) NOT NULL ,
        `slh_headid`  int(11)  NOT NULL,
        `slh_headname` VARCHAR(255) NOT NULL ,
        `slh_headno` VARCHAR(255)  NULL ,
        `slh_headamount` DECIMAL (15, 2) DEFAULT 0.00,
        `slh_totalintall`  int(11)   NULL,
        `slh_intallmentno`  int(11)   NULL,
        `slh_installamount` DECIMAL (15, 2) DEFAULT 0.00,
	`slh_month` VARCHAR(50) NOT NULL ,
        `slh_year` VARCHAR(100) NOT NULL ,
        `slh_creator` VARCHAR(255) NULL ,
        `slh_createdate` DATE NULL ,
        `slh_modifier` VARCHAR(255) NULL ,
        `slh_modifydate` TIMESTAMP NOT NULL ,
        PRIMARY KEY (`slh_id`)
) ENGINE = InnoDB;

/********************table structure salary_increment******************************/
CREATE TABLE `employee_salary_increment` (
	`esi_id` INT(11) NOT NULL AUTO_INCREMENT ,
        `esi_empid` INT(11) NOT NULL ,
	`esi_bpamount` DECIMAL (15, 2) DEFAULT 0.00,
	`esi_increment` DECIMAL (15, 2) DEFAULT 0.00,
	`esi_month` VARCHAR(50) NOT NULL ,
        `esi_year` VARCHAR(100) NOT NULL ,
	`esi_creator` VARCHAR(255) NULL ,
        `esi_createdate` DATE NULL ,
        `esi_modifier` VARCHAR(255) NULL ,
        `esi_modifydate` TIMESTAMP NOT NULL ,
	PRIMARY KEY (`esi_id`)
) ENGINE = InnoDB;

