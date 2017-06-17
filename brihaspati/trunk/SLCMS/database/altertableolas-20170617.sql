use olas;
ALTER TABLE `student_parent` ADD `spar_ppostoffice` VARCHAR(255) NULL AFTER `spar_paddress`;
ALTER TABLE `student_parent` ADD `spar_cpostoffice` VARCHAR(255) NULL AFTER `spar_caddress`;
ALTER TABLE `student_fees` CHANGE `sp_smid` `sfee_smid` INT(11) NOT NULL;
ALTER TABLE `student_fees` ADD `sfee_bankname` VARCHAR(255) NULL AFTER `sfee_referenceno`;
