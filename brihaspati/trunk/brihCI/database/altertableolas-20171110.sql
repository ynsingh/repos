use olas;
ALTER TABLE `designation` ADD `desig_type` VARCHAR(255)  NULL AFTER `desig_id`;
ALTER TABLE `designation` ADD `desig_subtype` VARCHAR(255)  NULL AFTER `desig_type`;
ALTER TABLE `designation` ADD `desig_payscale` VARCHAR(255)  NULL AFTER `desig_short`;
