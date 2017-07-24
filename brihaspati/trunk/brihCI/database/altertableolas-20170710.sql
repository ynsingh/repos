use olas;

CREATE TABLE `subject_semester_archive` (
  `subsema_id` int(11) NOT NULL,
  `subsema_subsemid` int(11) NOT NULL,
  `subsema_subid` int(11) NOT NULL,
  `subsema_prgid` int(11) NOT NULL,
  `subsema_semester` int(5) NOT NULL,
  `subsema_subtype` varchar(255) NOT NULL,
  `subsema_ext1` varchar(255) NOT NULL,
  `subsema_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_semester_archive`
  ADD PRIMARY KEY (`subsema_id`);

ALTER TABLE `subject_semester_archive`
  MODIFY `subsema_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `semester_rule_archive` (
  `semcra_id` int(11) NOT NULL,
  `semcra_semcrid` int(11) NOT NULL,
  `semcra_prgid` int(11) NOT NULL,
  `semcra_semester` int(5) NOT NULL,
  `semcra_mincredit` int(5) NOT NULL,
  `semcra_maxcredit` int(5) NOT NULL,
  `semcra_semcpi` DOUBLE(6,2) NOT NULL,
  `semcra_ext1` varchar(255) NOT NULL,
  `semcra_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `semester_rule_archive`
  ADD PRIMARY KEY (`semcra_id`);

ALTER TABLE `semester_rule_archive`
  MODIFY `semcra_id` int(11) NOT NULL AUTO_INCREMENT;
