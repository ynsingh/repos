use olas;

drop index `subp_degree` on `subject_paper`;
ALTER TABLE `subject_paper` CHANGE `subp_subtype` `subp_prgcat` VARCHAR(255) NOT NULL;
ALTER TABLE `subject_paper` CHANGE `subp_branch` `subp_dept` VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `subject_paper` CHANGE `subp_ext1` `subp_sem` VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `subject_paper` CHANGE `subp_ext2` `subp_subtype` VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `subject_paper` ADD UNIQUE KEY `subp_degree` (`subp_degree`,`subp_acadyear`,`subp_sub_id`,`subp_prgcat`,`subp_paperno`);


drop index `subpa_degree` on `subject_paper_archive`;
ALTER TABLE `subject_paper_archive` CHANGE `subpa_subtype` `subpa_prgcat` VARCHAR(255) NOT NULL;
ALTER TABLE `subject_paper_archive` CHANGE `subpa_branch` `subpa_dept` VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `subject_paper_archive` CHANGE `subpa_ext1` `subpa_sem` VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `subject_paper_archive` CHANGE `subpa_ext2` `subpa_subtype` VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `subject_paper_archive` ADD UNIQUE KEY `subpa_degree` (`subpa_degree`,`subpa_acadyear`,`subpa_sub_id`,`subpa_prgcat`,`subpa_paperno`);

