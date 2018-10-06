ALTER TABLE `employee_master` CHANGE `emp_dateofphd` `emp_dateofphd` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_dateofAssrExam` `emp_dateofAssrExam` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_dateofHGP` `emp_dateofHGP` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_doprobation` `emp_doprobation` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_doregular` `emp_doregular` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_netpassingyear` `emp_netpassingyear` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_doj` `emp_doj` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_dob` `emp_dob` DATE NULL DEFAULT NULL;


ALTER TABLE `employee_master` CHANGE `emp_vciregno` `emp_dojvc` DATE NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_vciregdate` `emp_salary_gradenew` INT(11) NULL DEFAULT NULL;

