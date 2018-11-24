ALTER TABLE `courseannouncement` ADD `crsann_fexamdate` DATE NULL AFTER `crsann_crsend`;
ALTER TABLE `test` ADD `maxmarks` INT(4) NOT NULL DEFAULT '2' AFTER `totalquestions`;
