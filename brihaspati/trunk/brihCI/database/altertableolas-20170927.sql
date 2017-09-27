use olas;
alter table  `admissionopen`  add  `admop_entexam_fees` int(6) DEFAULT NULL after `admop_prgname_branch`;

CREATE TABLE `enterence_exam_center` (
  `eec_id` int(11) NOT NULL,
  `eec_name` varchar(255) DEFAULT NULL,
  `eec_address` blob,
  `eec_city` varchar(255) DEFAULT NULL,
  `eec_incharge` varchar(255) DEFAULT NULL,
  `eec_noofroom` int(11) DEFAULT NULL,
  `eec_capacityinroom` int(11) DEFAULT NULL,
  `eec_totalcapacity` int(11) DEFAULT NULL,
  `eec_contactno` varchar(255) DEFAULT NULL,
  `eec_contactemail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `enterence_exam_center`
  ADD PRIMARY KEY (`eec_id`);

ALTER TABLE `enterence_exam_center`
  MODIFY `eec_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `admissionstudent_master` ADD `asm_enterenceexamcenter` VARCHAR(255) NULL AFTER `asm_universitycode`;
