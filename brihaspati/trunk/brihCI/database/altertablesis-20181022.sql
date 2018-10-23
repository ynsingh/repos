ALTER TABLE `employee_master_support` ADD `ems_pwplace1` INT(11) NULL AFTER `ems_societymember`, ADD `ems_pwplace2` INT(11) NULL AFTER `ems_pwplace1`, ADD `ems_pwplace3` INT(11) NULL AFTER `ems_pwplace2`;
ALTER TABLE `employee_master` ADD `emp_paycomm` VARCHAR(255) NULL AFTER `emp_jsession`;
ALTER TABLE `employee_master` ADD `emp_phdspecialisation` VARCHAR(255) NULL AFTER `emp_phdunivdeput`, ADD `emp_phdcollege` VARCHAR(255) NULL AFTER `emp_phpspecialisation`;
