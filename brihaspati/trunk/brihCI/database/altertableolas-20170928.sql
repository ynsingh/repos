use olas;

CREATE TABLE `admissionstudent_uploaddata` (
  `asupd_id` int(11) NOT NULL,
  `asupd_smid` int(11) NOT NULL,
  `asupd_enclosure1` varchar(255) DEFAULT NULL,
  `asupd_enclosure2` varchar(255) DEFAULT NULL,
  `asupd_enclosure3` varchar(255) DEFAULT NULL,
  `asupd_enclosure4` varchar(255) DEFAULT NULL,
  `asupd_enclosure5` varchar(255) DEFAULT NULL,
  `asupd_enclosure6` varchar(255) DEFAULT NULL,
  `asupd_enclosure7` varchar(255) DEFAULT NULL,
  `asupd_enclosure8` varchar(255) DEFAULT NULL,
  `asupd_enclosure9` varchar(255) DEFAULT NULL,
  `asupd_enclosure10` varchar(255) DEFAULT NULL,
  `asupd_enclosure11` varchar(255) DEFAULT NULL,
  `asupd_enclosure12` varchar(255) DEFAULT NULL,
  `asupd_enclosure13` varchar(255) DEFAULT NULL,
  `asupd_enclosure14` varchar(255) DEFAULT NULL,
  `asupd_enclosure15` varchar(255) DEFAULT NULL,
  `asupd_photo` varchar(500) DEFAULT NULL,
  `asupd_signature` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `admissionstudent_uploaddata`
  ADD PRIMARY KEY (`asupd_id`);

ALTER TABLE `admissionstudent_uploaddata`
  MODIFY `asupd_id` int(11) NOT NULL AUTO_INCREMENT;


CREATE TABLE `admissionstudent_enterencestep` (
  `id` int(11) NOT NULL,
  `application_no` varchar(255) NOT NULL,
  `admission_masterid` varchar(255) NOT NULL,
  `step1_status` varchar(255) NOT NULL,
  `step1_date` datetime DEFAULT NULL,
  `step2_status` varchar(255) NOT NULL,
  `step2_date` datetime DEFAULT NULL,
  `step3_status` varchar(255) NOT NULL,
  `step3_date` datetime DEFAULT NULL,
  `step4_status` varchar(255) NOT NULL,
  `step4_date` datetime DEFAULT NULL,
  `step5_status` varchar(255) NOT NULL,
  `step5_date` datetime DEFAULT NULL,
  `ext1` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `admissionstudent_enterencestep`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `admissionstudent_enterencestep`
  ADD PRIMARY KEY (`id`);

alter table `enterence_exam_center`  add `eec_code` varchar(255) DEFAULT NULL after  `eec_id`;
ALTER TABLE `admissionstudent_master` ADD `asm_coursename` VARCHAR(255) NULL AFTER `asm_testname`;



