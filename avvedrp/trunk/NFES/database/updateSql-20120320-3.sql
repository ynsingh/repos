INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (265,"personal_date_of_birth","Personal Date Of Birth","Date Of Birth","calendar");

ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_date_of_birth` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_date_of_birth`="";
UPDATE `staff_profile_report_v0_itemtypes` SET `code`='' WHERE `name`='calendar' AND `description`='None' AND `creator`='oio' AND `action`='calendar' AND `responsetype` IS NULL;

INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (270,"personal_joining_date","Personal Joining Date","Joining Date","calendar");

