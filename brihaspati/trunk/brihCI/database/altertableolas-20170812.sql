use olas;

CREATE TABLE `student_attendance` (
  `satd_id` int(11) NOT NULL,
  `satd_smid` int(11) NOT NULL,
  `satd_scid` int(11) NOT NULL,
  `satd_deptid` int(11) NOT NULL,
  `satd_acadyear` varchar(50) NOT NULL,
  `satd_prgid` int(11) NOT NULL,
  `satd_sem` varchar(10) NOT NULL,
  `satd_subid` int(11) NOT NULL,
  `satd_papid` int(11) NOT NULL,
  `satd_astatus` varchar(55) DEFAULT NULL,
  `satd_classtype` varchar(55) DEFAULT 'R',
  `satd_adate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `satd_creatorid` varchar(50) NOT NULL,
  `satd_createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `satd_modifierid` varchar(50) NOT NULL,
  `satd_modifydate`  DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_attendance`
  ADD PRIMARY KEY (`satd_id`),
  ADD UNIQUE KEY `satd_smid` (`satd_smid`,`satd_scid`,`satd_prgid`,`satd_subid`,`satd_papid`,`satd_acadyear`,`satd_sem`,`satd_adate`);

ALTER TABLE `student_attendance`
  MODIFY `satd_id` int(11) NOT NULL AUTO_INCREMENT;

