
CREATE TABLE `cca_grade_city` (
        `cgc_id` INT(11) NOT NULL AUTO_INCREMENT ,
        `cgc_gradename` VARCHAR(255) NOT NULL ,
        `cgc_place` BLOB NOT NULL ,
        `cgc_distance` VARCHAR(50) NOT NULL ,
        `cgc_desc` BLOB NULL ,
        `cgc_creator` VARCHAR(255) NULL ,
        `cgc_createdate` DATE NULL ,
        `cgc_modifier` VARCHAR(255) NULL ,
        `cgc_modifydate` TIMESTAMP NOT NULL ,
        PRIMARY KEY (`cgc_id`)
) ENGINE = InnoDB;

insert into cca_grade_city values (1,'CCA-Type-I','Chennai City','32 Kms','Chennai city and palces around the city at a distance not exceeding 32 kms from city limits. If the radius of 32 Kms. Fall within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving house rent allowance (HRA) as a admissible to Grade-I(a) place.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into cca_grade_city values (2,'CCA-Type-II','Coimbatore(UA),Madurai(UA),Salem(UA),Tiruppur(UA),Tiruchirappali(UA),Erode(UA),','16 Kms','Given palces around then at a distance not exceeding 16 kms. from the city limits and if the radius of 16 Kms. falls within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving House rent Allownace (HRA) as a admissible to Grade-I(b) palces.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);

CREATE TABLE `rent_free_hra` ( 
	`rfh_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`rfh_paycomm` VARCHAR(50) NOT NULL , 
	`rfh_gradeid` VARCHAR(50) NOT NULL , 
	`rfh_payrange` VARCHAR(50) NOT NULL , 
	`rfh_amount` DOUBLE NOT NULL , 
	`rfh_createdate` DATE NULL , 
	`rfh_creator` VARCHAR(255) NULL , 
	`rfh_modifydate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , 
	`rfh_modifier` VARCHAR(255) NULL , 
	PRIMARY KEY (`rfh_id`)
) ENGINE = InnoDB;
