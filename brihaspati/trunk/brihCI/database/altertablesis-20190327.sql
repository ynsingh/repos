ALTER TABLE `employee_master_support` 
ADD `ems_erfq` VARCHAR(50) NULL AFTER `ems_pwplace3`, 
ADD `ems_erfqhra` VARCHAR(10) NULL AFTER `ems_erfq`, 
ADD `ems_qoccupai` VARCHAR(10) NULL AFTER `ems_erfqhra`, 
ADD `ems_rentgrade` VARCHAR(10) NULL AFTER `ems_qoccupai`, 
ADD `ems_spfcgs` VARCHAR(100) NULL AFTER `ems_rentgrade`, 
ADD `ems_spfcgs2000` VARCHAR(100) NULL AFTER `ems_spfcgs`, 
ADD `ems_fsfno` VARCHAR(100) NULL AFTER `ems_spfcgs2000`;
