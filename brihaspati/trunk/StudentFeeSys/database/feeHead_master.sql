DROP TABLE IF EXISTS `feeHead_master`;
CREATE TABLE `feeHead_master` (
  `seq_id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `fee_head_name` VARCHAR(300) COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `fee_head_amt`  DOUBLE(15,2) NOT NULL,
  `fee_head_id` INTEGER(11) COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `fee_head_name` (`fee_head_name`)

)ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='InnoDB free: 11264 kB';

#
# Data for the `feeHead_master` table  (LIMIT 0,500)
#

INSERT INTO `feeHead_master` (`seq_id`, `fee_head_name`, `fee_head_amt`, `fee_head_id`) VALUES
  (1,'Admission Fees ',0.00,677),
  (2,'Enrolment Fees ',0.00,678),
  (3,'Tuition Fees',0.00,679),
  (4,'Library Fees',0.00,680),
  (5,'Annual/Semester Examination Fees',0.00,681),
  (6,' Marks Sheet Fees',0.00,682),
  (7,'Sale of Question Papers',0.00,683),
  (8,'Medical Fees',0.00,684),
  (9,'Miscellaneous Fees',0.00,685),
  (10,'Library Card',0.00,686),
  (11,'Student Identity Card',0.00,687),
  (12,'Hostel Fees',0.00,688);
  COMMIT;
# Adding a field of fee Id which is in BGAS

alter table other_fee_head_master add fee_head_id INTEGER(11) NOT NULL  Default '0' after fee_head_code;

alter table student_master add fee_Id INTEGER(11) NOT NULL default 0 after fee_head_code;
alter table student_master add DR_of_liability DOUBLE(15,2) DEFAULT NULL after student_opbal_amount;
alter table student_master add CR_of_liability DOUBLE(15,2) DEFAULT NULL after DR_of_liability;
alter table student_master add fee_submission_date DATE DEFAULT NULL after CR_of_liability;
alter table student_master add student_opbal_amount` DOUBLE(15,2) DEFAULT 0;

