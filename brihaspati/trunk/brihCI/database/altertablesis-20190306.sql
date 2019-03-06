 ALTER TABLE `employee_servicedetail` ADD `empsd_grade` VARCHAR(255) NULL AFTER `empsd_tsession`;
 ALTER TABLE `staff_promotionals_details` ADD `spd_grade` VARCHAR(255) NULL AFTER `spd_modifydate`;
ALTER TABLE `staff_promotionals_details_archive` ADD `spda_grade` VARCHAR(255) NULL AFTER `spda_modifydate`;
ALTER TABLE `employee_master` ADD `emp_splsuboth` VARCHAR(255) NULL AFTER `emp_specialisationid`;
ALTER TABLE `society_master_list` CHANGE `soc_sremark` `soc_regno` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;
ALTER TABLE `society_master_list` CHANGE `soc_purpose` `soc_remarks` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;
ALTER TABLE `society_master_list` ADD `soc_phone` VARCHAR(13) NULL AFTER `soc_regdate`, ADD `soc_mobile` VARCHAR(13) NULL AFTER `soc_phone`, ADD `soc_email` VARCHAR(255) NULL AFTER `soc_mobile`, ADD `soc_pan` VARCHAR(50) NULL AFTER `soc_email`, ADD `soc_tan` VARCHAR(50) NULL AFTER `soc_pan`, ADD `soc_gst` VARCHAR(50) NULL AFTER `soc_tan`, ADD `soc_bname` VARCHAR(255) NULL AFTER `soc_gst`, ADD `soc_bacno` VARCHAR(255) NULL AFTER `soc_bname`, ADD `soc_bifsc` VARCHAR(50) NULL AFTER `soc_bacno`, ADD `soc_bmicr` VARCHAR(50) NULL AFTER `soc_bifsc`;
ALTER TABLE `society_master_list` ADD `soc_bbranch` VARCHAR(255) NULL AFTER `soc_bmicr`, ADD `soc_bactype` VARCHAR(50) NULL AFTER `soc_bbranch`;
