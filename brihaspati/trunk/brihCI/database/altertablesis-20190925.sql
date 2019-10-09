ALTER TABLE `salaryhead_configuration` ADD `shc_paycom` VARCHAR(50) NULL AFTER `shc_emptypeid`, ADD `shc_wtype` VARCHAR(50) NULL AFTER `shc_paycom`;
ALTER TABLE `salaryhead_configuration` CHANGE `shc_emptypeid` `shc_emptypeid` INT(11) NULL;
