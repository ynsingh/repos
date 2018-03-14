use payroll;

ALTER TABLE `employee_servicedetail` ADD `empsd_ucoid` INT(11)  NOT NULL AFTER `empsd_campuscode`;
ALTER TABLE `employee_servicedetail` ADD `empsd_deptid` INT(11) NOT NULL AFTER `empsd_ucoid`;
