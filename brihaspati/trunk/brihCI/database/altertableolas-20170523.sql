

CREATE TABLE `admissionmeritlist` ( 
	`id` INT(11) NOT NULL AUTO_INCREMENT , 
	`application_no` VARCHAR(255) NOT NULL , 
	`course_name` VARCHAR(255) NOT NULL , 
	`student_name` VARCHAR(255) NOT NULL , 
	`father_name` VARCHAR(255) NOT NULL , 
	`marks` INT(5) NOT NULL , 
	`admission_quota` VARCHAR(255) NOT NULL , 
	`category` VARCHAR(255) NOT NULL , 
	`meritlist_no` VARCHAR(255) NOT NULL , 
	`lastdate_admission` VARCHAR(255) NOT NULL , 
	`admission_taken` VARCHAR(255) NOT NULL ,
        `admission_date` VARCHAR(255) NOT NULL ,
	`ext1` VARCHAR(255) NULL , 
	PRIMARY KEY (`id`), 
	UNIQUE (`application_no`, `course_name`, `student_name`)
) ENGINE = InnoDB;

INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (1, 'Administrator', 'Responsible for Admin job');
INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (2, 'Faculty', 'Responsible for Teacher job');
INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (3, 'Student', 'Responsible for Student job');

