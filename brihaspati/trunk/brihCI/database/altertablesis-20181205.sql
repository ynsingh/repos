ALTER TABLE `employee_servicedetail` ADD `empsd_authority` VARCHAR(255) NULL AFTER `empsd_orderno`;
ALTER TABLE `staff_working_arrangements_perticulars` ADD `swap_fromdate` DATE NULL AFTER `swap_wdept`, ADD `swap_todate` DATE NULL AFTER `swap_fromdate`;
