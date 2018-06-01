use payroll,
ALTER TABLE `salary_grade_master` ADD `sgm_level` VARCHAR(255) NOT NULL AFTER `sgm_gradepay`;
ALTER TABLE `salary_grade_master_archive` ADD `sgma_level` VARCHAR(255) NOT NULL AFTER `sgma_gradepay`;
