
ALTER TABLE `admissionstudent_centerallocation` CHANGE `ca_rollno` `ca_rollno` VARCHAR(50) NULL DEFAULT NULL;
ALTER TABLE `admissionstudent_centerallocation` ADD UNIQUE(`ca_asmid`);
ALTER TABLE `admissionopen` CHANGE `admop_app_received` `admop_app_received` DATETIME NULL DEFAULT NULL;
ALTER TABLE  Department ADD dept_uoid INT(11) NOT NULL AFTER dept_code;
