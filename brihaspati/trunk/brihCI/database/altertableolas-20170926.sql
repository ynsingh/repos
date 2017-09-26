drop index `satd_smid` on `student_attendance`;
create index  `satd_smid` on `student_attendance` (`satd_smid`,`satd_scid`,`satd_acadyear`,`satd_prgid`,`satd_sem`,`satd_subid`,`satd_papid`,`satd_classtype`,`satd_adate`);


CREATE TABLE `admissionopen` (
  `admop_id` int(11) NOT NULL,
  `admop_acadyear` varchar(100) DEFAULT NULL,
  `admop_prgcat` varchar(255) DEFAULT NULL,
  `admop_prgname_branch` varchar(255) DEFAULT NULL,
  `admop_min_qual` blob,
  `admop_entexam_patt` blob,
  `admop_entexam_date` datetime DEFAULT NULL,
  `admop_startdate` datetime DEFAULT NULL,
  `admop_lastdate` datetime DEFAULT NULL,
  `admop_app_received` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionopen`
  ADD PRIMARY KEY (`admop_id`);
ALTER TABLE `admissionopen`
  MODIFY `admop_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
