ALTER TABLE `purchase_committee` CHANGE `pc_creation date` `pc_creation date` DATE NOT NULL;

ALTER TABLE `purchase_committee` ADD `pc_purchasethrough` VARCHAR(255) NOT NULL AFTER `pc_dept`;

ALTER TABLE `purchase_committee` CHANGE `pc_creation date` `pc_creationdate` DATE NOT NULL;

ALTER TABLE `purchase_committee` CHANGE `pc_rep2` `pc_rep2` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL, CHANGE `pc_rep3` `pc_rep3` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL, CHANGE `pc_rep4` `pc_rep4` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL, CHANGE `pc_rep5` `pc_rep5` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL;