use olas;

CREATE TABLE `admissionstudent_entranceexamfeeconf` ( 
`aseefc_id` INT NOT NULL AUTO_INCREMENT , 
`aseefc_feename` VARCHAR(255) NOT NULL , 
`aseefc_category` VARCHAR(255) NOT NULL DEFAULT 'All' , 
`aseefc_gender` VARCHAR(255) NOT NULL DEFAULT 'All' , 
`aseefc_amount` INT NOT NULL , 
PRIMARY KEY (`aseefc_id`)) ENGINE = InnoDB;


CREATE TABLE `admissionstudent_contactus` (
  `ascu_id` int(11) NOT NULL,
  `ascu_name` varchar(255) NOT NULL,
  `ascu_emailid` varchar(255) DEFAULT NULL,
  `ascu_phoneno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_contactus`   ADD PRIMARY KEY (`ascu_id`);
ALTER TABLE `admissionstudent_contactus`  MODIFY `ascu_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `admissionmeritlist` ADD `admission_status` VARCHAR(255) NULL DEFAULT 'Provisional' AFTER `admission_date`, ADD `admission_confirmdate` DATE NULL AFTER `admission_status`;

ALTER TABLE `admissionstudent_master` ADD `asm_aadharno` INT(15) NULL AFTER `asm_nationality`;
