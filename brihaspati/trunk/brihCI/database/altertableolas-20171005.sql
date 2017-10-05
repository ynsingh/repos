rename table enterence_exam_center to admissionstudent_enterenceexamcenter;

ALTER TABLE `admissionstudent_enterencestep` CHANGE `application_no` `registration_id` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;


