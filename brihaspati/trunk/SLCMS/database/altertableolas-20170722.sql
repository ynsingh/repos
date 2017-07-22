ALTER TABLE `student_marks` ADD `smr_smid` int(11) not NULL AFTER `smr_id`;
drop index smr_scid on student_marks; 
ALTER TABLE `student_marks` ADD UNIQUE KEY `smr_smid` (`smr_smid`,`smr_scid`,`smr_prgid`,`smr_subid`,`smr_papid`,`smr_acadyear`,`smr_sem`);

--
-- Table structure for table `student_exam`
--

CREATE TABLE `student_exam` (
  `sex_id` int(11) NOT NULL,
  `sex_smid` int(11) NOT NULL,
  `sex_prgid` int(11) NOT NULL,
  `sex_sem` int(11) NOT NULL,
  `sex_examtype` varchar(255) DEFAULT NULL,
  `sex_applydate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sex_examgiven` varchar(255) DEFAULT NULL,
  `sex_resultdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sex_resultstatus` varchar(50) DEFAULT NULL,
  `sex_remark` varchar(255) DEFAULT NULL,
  `sex_creatorid` varchar(50) NOT NULL,
  `sex_createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sex_modifierid` varchar(50) NOT NULL,
  `sex_modifydate`  DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_exam`
  ADD PRIMARY KEY (`sex_id`),
  ADD UNIQUE KEY `sex_smid` (`sex_smid`,`sex_prgid`,`sex_sem`,`sex_examtype`);

ALTER TABLE `student_exam`
  MODIFY `sex_id` int(11) NOT NULL AUTO_INCREMENT;


--
-- Table structure for table `student_exam_attendance`
--

CREATE TABLE `student_exam_attendance` (
  `sexat_id` int(11) NOT NULL,
  `sexat_smid` int(11) NOT NULL,
  `sexat_prgid` int(11) NOT NULL,
  `sexat_sem` int(11) NOT NULL,
  `sexat_examdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sexat_subid` int(11) DEFAULT NULL,
  `sexat_papid` int(11)  DEFAULT NULL,
  `sexat_acadyear` varchar(50) NOT NULL,
  `sexat_creatorid` varchar(50) NOT NULL,
  `sexat_createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sexat_modifierid` varchar(50) NOT NULL,
  `sexat_modifydate`  DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_exam_attendance`
  ADD PRIMARY KEY (`sexat_id`),
  ADD UNIQUE KEY `sexat_smid` (`sexat_smid`,`sexat_prgid`,`sexat_sem`,`sexat_examdate`,`sexat_subid`);

ALTER TABLE `student_exam_attendance`
  MODIFY `sexat_id` int(11) NOT NULL AUTO_INCREMENT;

