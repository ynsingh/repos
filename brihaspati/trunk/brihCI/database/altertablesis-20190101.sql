ALTER TABLE `salary_transfer_entry` ADD `ste_ccafrom` VARCHAR(255) NULL AFTER `ste_hrato`, ADD `ste_ccato` VARCHAR(255) NULL AFTER `ste_ccafrom`;
ALTER TABLE `salary_transfer_entry_archive` ADD `stea_ccafrom` VARCHAR(255) NULL AFTER `stea_hrato`, ADD `stea_ccato` VARCHAR(255) NULL AFTER `stea_ccafrom`;
