use olas;
ALTER TABLE `subject_paper` ADD `subp_subtype` VARCHAR(255) NOT NULL AFTER `subp_sub_id`, ADD `subp_paperno` VARCHAR(50) NOT NULL AFTER `subp_subtype`;
ALTER TABLE `subject_paper` ADD `subp_degree` VARCHAR(20) NOT NULL AFTER `subp_desp`, ADD `subp_acadyear` VARCHAR(20) NOT NULL AFTER `subp_degree`;
ALTER TABLE `subject_paper` ADD `creatorid` VARCHAR(255) NOT NULL AFTER `subp_ext2`, ADD `createdate` VARCHAR(255) NOT NULL AFTER `creatorid`, ADD `modifierid` VARCHAR(255) NOT NULL AFTER `createdate`, ADD `modifydate` VARCHAR(255) NOT NULL AFTER `modifierid`;

