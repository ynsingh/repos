use olas;
alter table `program` ADD `prg_credit` VARCHAR(255) DEFAULT NULL after `prg_seat`;
alter table `subject_paper` ADD `subp_branch` VARCHAR(255) DEFAULT NULL after `subp_degree`;
alter table `subject_paper` change `subp_degree` `subp_degree` VARCHAR(255);

CREATE TABLE `subject_paper_archive` (
  `subpa_id` int(11) NOT NULL,
  `subpa_subpid` int(11) NOT NULL,
  `subpa_sub_id` int(11) NOT NULL,
  `subpa_subtype` VARCHAR(255) NOT NULL,
  `subpa_paperno` VARCHAR(50) NOT NULL,
  `subpa_name` varchar(255) NOT NULL,
  `subpa_code` varchar(100) NOT NULL,
  `subpa_short` varchar(255) NOT NULL,
  `subpa_desp` varchar(255) NOT NULL,
  `subpa_degree` VARCHAR(255) NOT NULL,
  `subpa_branch` VARCHAR(255) DEFAULT NULL,
  `subpa_acadyear` VARCHAR(20) NOT NULL,
  `subpa_ext1` varchar(255) NOT NULL,
  `subpa_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE (`subpa_degree`, `subpa_acadyear`, `subpa_sub_id`,`subpa_subtype`,`subpa_paperno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_paper_archive`
  ADD PRIMARY KEY (`subpa_id`);

ALTER TABLE `subject_paper_archive`
  MODIFY `subpa_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `credit_rule` (
  `cr_id` int(11) NOT NULL,
  `cr_subtype` VARCHAR(255) NOT NULL,
  `cr_degree` VARCHAR(255) NOT NULL,
  `cr_branch` VARCHAR(255),
  `cr_sub_id` int(11) NOT NULL,
  `cr_subpid` int(11) NOT NULL,
  `cr_credit` VARCHAR(50) NOT NULL,
  `cr_ext1` varchar(255) NOT NULL,
  `cr_ext2` varchar(255) NOT NULL,	
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE (`cr_degree`, `cr_branch`, `cr_sub_id`,`cr_subtype`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `credit_rule`
  ADD PRIMARY KEY (`cr_id`);

ALTER TABLE `credit_rule`
  MODIFY `cr_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `subject_prerequisite` (
  `spreq_id` int(11) NOT NULL,
  `spreq_subid` int(11) NOT NULL,
  `spreq_depsubid` int(11) NOT NULL,
  `spreq_subpid` int(11) NOT NULL,
  `spreq_depsubpid` int(11) NOT NULL,
  `spreq_ext1` varchar(255) NOT NULL,
  `spreq_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_prerequisite`
  ADD PRIMARY KEY (`spreq_id`);

ALTER TABLE `subject_prerequisite`
  MODIFY `spreq_id` int(11) NOT NULL AUTO_INCREMENT;
