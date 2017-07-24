use olas;
drop index `cr_degree` on `credit_rule`;
drop table credit_rule;
alter table `student_marks` add `smr_grade` varchar(55) DEFAULT NULL after `smr_marks`;

CREATE TABLE `degree_rule` (
  `dr_id` int(11) NOT NULL,
  `dr_prgid` int(11) NOT NULL,
  `dr_mincredit` int(5) NOT NULL,
  `dr_minsubcredit` int(5) NOT NULL,
  `dr_minthesiscredit` int(5) NOT NULL,
  `dr_minsub` int(4) NOT NULL,
  `dr_minsemester` int(3) NOT NULL,
  `dr_mincpi` int(5) NOT NULL,
  `dr_maxcredit` int(5) NOT NULL,
  `dr_maxsemeter` int(3) DEFAULT NULL,
  `dr_ext` varchar(255) DEFAULT  NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `degree_rule`
  ADD PRIMARY KEY (`dr_id`);

ALTER TABLE `degree_rule`
  MODIFY `dr_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `grade_master` (
  `gm_id` int(11) NOT NULL,
  `gm_gradename` varchar(255) NOT NULL,
  `gm_gradepoint` varchar(255) NOT NULL,
  `gm_short` varchar(255)NULL,
  `gm_desc` varchar(255)  NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `grade_master`
  ADD PRIMARY KEY (`gm_id`),
  ADD UNIQUE KEY `gm_gradename` (`gm_gradename`);

ALTER TABLE `grade_master`
  MODIFY `gm_id` int(11) NOT NULL AUTO_INCREMENT;

insert into `grade_master` values (1,'A','10','','','admin','2017-07-04','admin','2017-07-04'),(2,'B','8','','','admin','2017-07-04','admin','2017-07-04'),(3,'C','6','','','admin','2017-07-04','admin','2017-07-04'),(4,'D','4','','','admin','2017-07-04','admin','2017-07-04'),(5,'E','2','','','admin','2017-07-04','admin','2017-07-04'),(6,'F','0','','','admin','2017-07-04','admin','2017-07-04');


CREATE TABLE `subject_semester` (
  `subsem_id` int(11) NOT NULL,
  `subsem_subid` int(11) NOT NULL,
  `subsem_prgid` int(11) NOT NULL,
  `subsem_semester` int(5) NOT NULL,
  `subsem_subtype` varchar(255) NOT NULL,
  `subsem_ext1` varchar(255) NOT NULL,
  `subsem_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_semester`
  ADD PRIMARY KEY (`subsem_id`);

ALTER TABLE `subject_semester`
  MODIFY `subsem_id` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `semester_rule` (
  `semcr_id` int(11) NOT NULL,
  `semcr_prgid` int(11) NOT NULL,
  `semcr_semester` int(5) NOT NULL,
  `semcr_mincredit` int(5) NOT NULL,
  `semcr_maxcredit` int(5) NOT NULL,
  `semcr_semcpi` DOUBLE(6,2) NOT NULL,
  `semcr_ext1` varchar(255) NOT NULL,
  `semcr_ext2` varchar(255) NOT NULL,
  `creatorid` VARCHAR(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modifierid` VARCHAR(255) NOT NULL,
  `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `subject_semester`
  ADD PRIMARY KEY (`subsem_id`);

ALTER TABLE `subject_semester`
  MODIFY `subsem_id` int(11) NOT NULL AUTO_INCREMENT;

