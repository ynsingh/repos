use olas;

CREATE TABLE `student_marks` (
  `smr_id` int(11) NOT NULL,
  `smr_scid` int(11) NOT NULL,
  `smr_deptid` int(11) NOT NULL,
  `smr_prgid` int(11) NOT NULL,
  `smr_subid` int(11) NOT NULL,
  `smr_papid` int(11) NOT NULL,
  `smr_acadyear` varchar(50) NOT NULL,
  `smr_sem` varchar(10) NOT NULL,
  `smr_mmmarks` int(10) NOT NULL,
  `smr_marks` int(11) NOT NULL,
  `smr_creatorid` varchar(50) NOT NULL,
  `smr_createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  `smr_modifierid` varchar(50) NOT NULL,
  `smr_modifydate`  DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_marks`
  ADD PRIMARY KEY (`smr_id`),
  ADD UNIQUE KEY `smr_scid` (`smr_scid`,`smr_prgid`,`smr_subid`,`smr_papid`,`smr_acadyear`,`smr_sem`);

ALTER TABLE `student_marks`
  MODIFY `smr_id` int(11) NOT NULL AUTO_INCREMENT;


drop index sfee_smid on student_fees;
alter table student_parent drop spar_fatherlname;
alter table student_parent drop spar_fathermname;
alter table student_parent drop spar_mothermname;
alter table student_parent drop spar_motherlname;
alter table student_parent drop spar_garmname;
alter table student_parent drop spar_garlname;
alter table student_parent change spar_fatherfname spar_fathername varchar(255);
alter table student_parent change spar_motherfname spar_mothername varchar(255);
alter table student_parent change spar_garfname spar_garname varchar(255);
alter table student_program change sp_sub1 sp_subid1 int(11);
alter table student_program change sp_sub2 sp_subid2 int(11);
alter table student_program change sp_sub3 sp_subid3 int(11);
alter table student_program change sp_sub4 sp_subid4 int(11);
alter table student_program change sp_sub5 sp_subid5 int(11);
alter table student_program change sp_sub6 sp_subid6 int(11);
alter table student_program change sp_sub7 sp_subid7 int(11);
alter table student_program change sp_sub8 sp_subid8 int(11);
alter table student_program change sp_sub9 sp_subid9 int(11);
alter table student_program change sp_sub10 sp_subid10 int(11);
ALTER TABLE `student_program` ADD `sp_deptid` INT(11) NOT NULL AFTER `sp_sccode`;
alter table `program` add `prg_pattern` varchar(255) NOT NULL after `prg_desc`;
