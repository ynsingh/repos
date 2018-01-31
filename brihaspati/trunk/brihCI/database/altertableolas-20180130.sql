use olas;

CREATE TABLE `student_examcenter` (
  `sec_id` int(11) NOT NULL,
  `sec_code` varchar(255) DEFAULT NULL,
  `sec_name` varchar(255) DEFAULT NULL,
  `sec_address` blob,
  `sec_city` varchar(255) DEFAULT NULL,
  `sec_incharge` varchar(255) DEFAULT NULL,
  `sec_noofroom` int(11) DEFAULT NULL,
  `sec_capacityinroom` int(11) DEFAULT NULL,
  `sec_totalcapacity` int(11) DEFAULT NULL,
  `sec_contactno` varchar(255) DEFAULT NULL,
  `sec_contactemail` varchar(255) DEFAULT NULL,
  `sec_state` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `student_examcenter`
  ADD PRIMARY KEY (`sec_id`);

ALTER TABLE `student_examcenter`
  MODIFY `sec_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `studentexam_schedule` (
  `exsc_id` int(11) NOT NULL,
  `exsc_centerid` int(11) DEFAULT NULL,
  `exsc_progcatid` varchar(255) DEFAULT NULL,
  `exsc_prgid` int(11) DEFAULT NULL,
  `exsc_deptid` int(11) DEFAULT NULL,
  `exsc_acadyear` varchar(50) DEFAULT NULL,
  `exsc_semester` int(30) DEFAULT NULL,
  `exsc_subjectid` int(11) DEFAULT NULL,
  `exsc_paperid` int(11) DEFAULT NULL,
  `exsc_examname` varchar(255) DEFAULT NULL,
  `exsc_examdatetime` datetime DEFAULT NULL,
  `exsc_creatorid` varchar(255) DEFAULT NULL,
  `exsc_creatordate` date DEFAULT NULL,
  `exsc_modifierid` varchar(255) DEFAULT NULL,
  `exsc_modifierdate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `studentexam_schedule`
  ADD PRIMARY KEY (`exsc_id`);

ALTER TABLE `studentexam_schedule`
 MODIFY `exsc_id` int(11) NOT NULL AUTO_INCREMENT;

