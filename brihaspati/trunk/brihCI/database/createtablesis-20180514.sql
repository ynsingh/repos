use payroll;

ALTER TABLE  user_input_transfer ADD uit_scid_to  int(11)  AFTER  uit_workingpost_from;
ALTER TABLE  user_input_transfer ADD uit_ddoid_to  int(11)  AFTER  uit_schm_to;
ALTER TABLE  user_input_transfer ADD uit_group_to  VARCHAR(50)  AFTER  uit_ddoid_to;
ALTER TABLE  user_input_transfer ADD uit_paybandid_to  int(11)  AFTER  uit_group_to;
ALTER TABLE  user_input_transfer ADD uit_vacanttype_to  VARCHAR(255)  AFTER  uit_paybandid_to;

CREATE TABLE `salaryhead_defaultvalue` (
  `shdv_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `shdv_paybandid` int(11)  NOT NULL,
  `shdv_salheadid` int(11)  NOT NULL,
  `shdv_defaultvalue` double  Default 0,
  `shdv_remarks` varchar(255)  Default NULL,
  `shdv_creatorid` varchar(255) NOT NULL, 
  `shdv_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `shdv_modifierid` varchar(255) NOT NULL,
  `shdv_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP 

) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `salaryhead_configuration` (
  `shc_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `shc_emptypeid` int(11)  NOT NULL,
  `shc_salheadid` varchar(255)  NOT NULL,
  `shc_scid` int(11)  Default NULL,
  `shc_creatorid` varchar(255) NOT NULL,
  `shc_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `shc_modifierid` varchar(255) NOT NULL,
  `shc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
