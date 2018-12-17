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

ALTER TABLE `salary_leave_entry`
  ADD PRIMARY KEY (`sle_id`),
  ADD UNIQUE KEY `sle_empid` (`sle_empid`,`sle_year`,`sle_month`);

ALTER TABLE `salary_leave_entry`
  MODIFY `sle_id` int(11) NOT NULL AUTO_INCREMENT;

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

ALTER TABLE `salary_leave_entry_archive`
  ADD PRIMARY KEY (`slea_id`),
  ADD UNIQUE KEY `slea_empid` (`slea_empid`,`slea_year`,`slea_month`);

ALTER TABLE `salary_leave_entry_archive`
  MODIFY `slea_id` int(11) NOT NULL AUTO_INCREMENT;


CREATE TABLE `salary_transfer_entry` ( 
        `ste_id` INT(11) NOT NULL AUTO_INCREMENT , 
        `ste_empid` INT(11) NOT NULL , 
        `ste_year` INT(4) NOT NULL , 
        `ste_month` VARCHAR(50) NOT NULL , 
        `ste_days` VARCHAR(4) NULL , 
        `ste_hrafrom` VARCHAR(255) NULL , 
        `ste_hrato` VARCHAR(255) NULL , 
        `ste_transit` VARCHAR(255) NULL , 
        `ste_creatorid` VARCHAR(255) NULL , 
        `ste_createdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , 
        `ste_modifierid` VARCHAR(255) NULL ,
        `ste_modifydate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
        PRIMARY KEY (`ste_id`)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `salary_transfer_entry_archive` (
        `stea_id` INT(11) NOT NULL AUTO_INCREMENT ,
        `stea_steid` INT(11) NOT NULL ,
        `stea_empid` INT(11) NOT NULL ,
        `stea_year` INT(4) NOT NULL ,
        `stea_month` VARCHAR(50) NOT NULL ,
        `stea_days` VARCHAR(4) NULL ,
        `stea_hrafrom` VARCHAR(255) NULL ,
        `stea_hrato` VARCHAR(255) NULL ,
        `stea_transit` VARCHAR(255) NULL ,
        `stea_creatorid` VARCHAR(255) NULL ,
        `stea_createdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
        `stea_modifierid` VARCHAR(255) NULL ,
        `stea_modifydate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
        PRIMARY KEY (`stea_id`)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

