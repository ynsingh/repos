ALTER TABLE `employee_servicedetail` ADD `empsd_worktype` VARCHAR(255) NOT NULL AFTER `empsd_ddoid`;
ALTER TABLE `employee_servicedetail` ADD `empsd_level` VARCHAR(255) NOT NULL AFTER `empsd_pbid`;
