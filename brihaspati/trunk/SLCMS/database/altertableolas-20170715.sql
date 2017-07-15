use olas;
  
ALTER TABLE `admissionmeritlist` ADD `entexamname` VARCHAR(255) NULL AFTER `application_no`;
ALTER TABLE `admissionmeritlist` ADD `entexamrollno` VARCHAR(255) NULL AFTER `entexamname`;
ALTER TABLE `admissionmeritlist` ADD `branchname` VARCHAR(255) NULL AFTER `course_name`;
ALTER TABLE `admissionmeritlist` ADD `student_email` VARCHAR(255) NULL AFTER `student_name`;

ALTER TABLE `student_program` ADD `sp_semregdate` DATETIME NULL AFTER `sp_semester`;

ALTER TABLE `subject_prerequisite` ADD `spreq_prgid` int(11) NOT NULL AFTER `spreq_id`;
