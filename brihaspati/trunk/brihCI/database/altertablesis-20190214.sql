ALTER TABLE `salary_grade_master` ADD `sgm_wt` VARCHAR(100) NULL AFTER `sgm_pc`, ADD `sgm_group` VARCHAR(5) NULL AFTER `sgm_wt`;
ALTER TABLE `salary_grade_master_archive` ADD `sgma_wt` VARCHAR(100) NULL AFTER `sgma_pc`, ADD `sgma_group` VARCHAR(5) NULL AFTER `sgma_wt`;
ALTER TABLE `paymatrix` ADD `pm_pc` VARCHAR(50) NULL AFTER `pm_sgmid`, ADD `pm_wt` VARCHAR(50) NULL AFTER `pm_pc`;
ALTER TABLE `ccaallowance_calculation` CHANGE `cca_gradeid` `cca_gradeid` VARCHAR(100) NULL DEFAULT NULL;
ALTER TABLE `bankprofile` ADD `campusid` INT(11) NULL DEFAULT NULL AFTER `id`, ADD `ucoid` INT(11) NULL DEFAULT NULL AFTER `campusid`, ADD `deptid` INT(11) NULL DEFAULT NULL AFTER `ucoid`, ADD `schemeid` INT(11) NULL DEFAULT NULL AFTER `deptid`;
ALTER TABLE `bankprofile_archive` ADD `bpa_campusid` INT(11) NULL DEFAULT NULL AFTER `bpa_bpid`, ADD `bpa_ucoid` INT(11) NULL DEFAULT NULL AFTER `bpa_campusid`, ADD `bpa_deptid` INT(11) NULL DEFAULT NULL AFTER `bpa_ucoid`, ADD `bpa_schemeid` INT(11) NULL DEFAULT NULL AFTER `bpa_deptid`;

