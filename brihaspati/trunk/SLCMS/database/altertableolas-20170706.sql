use olas;
alter table `student_master` drop `sm_applicationno`;
alter table `student_master` drop `sm_meritno`;
alter table `student_master` drop `sm_testname`; 
alter table `student_master` drop `sm_rollno`;

CREATE TABLE `student_entry_exit` (
  `senex_id` int(11) NOT NULL,
  `senex_smid` int(11) NOT NULL,
  `senex_prgid` int(11) NOT NULL,
  `senex_rollno` varchar(255) NOT NULL,
  `senex_dateofadmission` DATETIME DEFAULT NULL,
  `senex_grade` varchar(15) DEFAULT NULL,
  `senex_gradedate` DATETIME DEFAULT NULL,
  `senex_idcardvalidity` DATETIME DEFAULT NULL,
  `senex_idcardissuedate` DATETIME DEFAULT NULL,
  `senex_entexamname` varchar(255) DEFAULT NULL,
  `senex_entexamrollno` varchar(255) DEFAULT  NULL,
  `senex_entexamrank` varchar(255) DEFAULT  NULL,
  `senex_entexamapplicationno` varchar(255) DEFAULT  NULL,
  `senex_entexammeritno` varchar(255) DEFAULT  NULL,
  `senex_cpi` varchar(255) DEFAULT  NULL,
  `senex_noduesgiven` varchar(255) DEFAULT  NULL,
  `senex_noduesdate` DATETIME DEFAULT NULL,
  `senex_ext` varchar(255) DEFAULT  NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_entry_exit`
  ADD PRIMARY KEY (`senex_id`),
  ADD UNIQUE KEY `senex_smid` (`senex_smid`,`senex_prgid`,`senex_rollno`);

ALTER TABLE `student_entry_exit`
  MODIFY `senex_id` int(11) NOT NULL AUTO_INCREMENT;



