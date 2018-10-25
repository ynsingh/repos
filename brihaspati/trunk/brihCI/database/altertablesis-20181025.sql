ALTER TABLE `salary_grade_master` ADD `sgm_pc` VARCHAR(50) NULL   DEFAULT '6th' AFTER `sgm_id`;
ALTER TABLE `salary_grade_master_archive` ADD `sgma_pc` VARCHAR(50) NULL AFTER `sgma_sgmid`;
ALTER TABLE `employee_master` CHANGE `emp_dateofAssrExam` `emp_dateofAssrExam` VARCHAR(10) NULL DEFAULT NULL;
ALTER TABLE `employee_master` CHANGE `emp_netpassingyear` `emp_netpassingyear` VARCHAR(10) NULL DEFAULT NULL;
drop index hl_userid on hod_list ;
ALTER TABLE `hod_list`ADD UNIQUE KEY `hl_userid` (`hl_userid`,`hl_deptid`,`hl_empcode`,`hl_datefrom`);
