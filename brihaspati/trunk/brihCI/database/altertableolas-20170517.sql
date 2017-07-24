
CREATE TABLE `program_subject_teacher` (
  `pstp_id` int(11) NOT NULL,
  `pstp_scid` int(11) NOT NULL,
  `pstp_prgid` int(11) NOT NULL,
  `pstp_subid` int(11) NOT NULL,
  `pstp_papid` int(11) NOT NULL,
  `pstp_teachid` int(11) NOT NULL,
  `pstp_acadyear` varchar(50) NOT NULL,
  `pstp_sem` varchar(10) NOT NULL,
  `pstp_ext1` varchar(255) DEFAULT NULL,
  `pstp_ext2` varchar(255) DEFAULT NULL,
  `pstp_creatorid` varchar(50) NOT NULL,
  `pstp_createdate` varchar(50) NOT NULL,
  `pstp_modifierid` varchar(50) NOT NULL,
  `pstp_modifydate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `program_subject_teacher`
  ADD PRIMARY KEY (`pstp_id`),
  ADD UNIQUE KEY `pstp_scid` (`pstp_scid`,`pstp_prgid`,`pstp_subid`,`pstp_papid`,`pstp_teachid`,`pstp_acadyear`,`pstp_sem`);

ALTER TABLE `program_subject_teacher`
  MODIFY `pstp_id` int(11) NOT NULL AUTO_INCREMENT;
