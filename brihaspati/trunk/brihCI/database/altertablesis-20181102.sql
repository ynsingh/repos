ALTER TABLE `uo_list` ADD `ul_fromsession` VARCHAR(70) NULL AFTER `ul_datefrom`;
ALTER TABLE `uo_list` ADD `ul_tosession` VARCHAR(70) NULL AFTER `ul_dateto`; 
ALTER TABLE `hod_list` ADD `hl_fromsession` VARCHAR(70) NULL AFTER `hl_datefrom`;
ALTER TABLE `hod_list` ADD `hl_tosession` VARCHAR(70) NULL AFTER `hl_dateto`;
ALTER TABLE `employee_master` ADD `emp_bankname` VARCHAR(255) NULL AFTER `emp_bank_ifsc_code`, ADD `emp_bankbranch` VARCHAR(255) NULL AFTER `emp_bankname`;
