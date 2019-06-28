
ALTER TABLE `staff_promotionals_details` ADD `spd_agpsession` VARCHAR(50) NULL AFTER `spd_agpdate`;
ALTER TABLE `staff_promotionals_details` ADD `spd_levelsession` VARCHAR(50) NULL AFTER `spd_leveldate`;
ALTER TABLE `staff_promotionals_details` ADD `spd_dojinsession` VARCHAR(50) NULL AFTER `spd_dojinpost`;

ALTER TABLE `staff_promotionals_details_archive` ADD `spda_agpsession` VARCHAR(50) NULL AFTER `spda_agpdate`;
ALTER TABLE `staff_promotionals_details_archive` ADD `spda_levelsession` VARCHAR(50) NULL AFTER `spda_leveldate`;
ALTER TABLE `staff_promotionals_details_archive` ADD `spda_dojinsession` VARCHAR(50) NULL AFTER `spda_dojinpost`;

ALTER TABLE `staff_working_arrangements_perticulars` ADD `swap_fsession` VARCHAR(50) NULL AFTER `swap_fromdate`;
ALTER TABLE `staff_working_arrangements_perticulars` ADD `swap_tsession` VARCHAR(50) NULL AFTER `swap_todate`;

