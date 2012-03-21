INSERT INTO `staff_profile_report_v0_itemtypes` (`name`,`description`,`creator`,`action`,`choice`,`code`) VALUES ("radio_gender","None","oio","pickone_radio","Male,Female","Male,Female");
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (260,"personal_gender","Personal Gender","Gender","radio_gender");
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_gender` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_gender`="";

INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (300,"personal_community","Personal Community","Community","blank");
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_community` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_community`="";

