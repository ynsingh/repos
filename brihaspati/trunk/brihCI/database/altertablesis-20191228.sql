ALTER TABLE `employee_servicedetail` ADD `empsd_wcampid` INT(4) NULL AFTER `empsd_deptid`, ADD `empsd_wuoid` INT(4) NULL AFTER `empsd_wcampid`, ADD `empsd_wdeptid` INT(4) NULL AFTER `empsd_wuoid`;

ALTER TABLE `employee_servicedetail` ADD `empsd_creatorid` VARCHAR(255) NULL AFTER `empsd_grade`, ADD `empsd_creatordate` DATE NULL AFTER `empsd_creatorid`, ADD `empsd_modifierid` VARCHAR(255) NULL AFTER `empsd_creatordate`, ADD `empsd_modifierdate` DATETIME NULL AFTER `empsd_modifierid`; 

ALTER TABLE `employee_servicedetail` CHANGE `empsd_modifierdate` `empsd_modifierdate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP;  
