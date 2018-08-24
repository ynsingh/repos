-- -------------------------------------------------------------------
--
-- Table structure for table rent_recovery`
--

CREATE TABLE `rent_recovery` (
  `rr_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `rr_payscaleid` int(11)  Default NULL,
  `rr_gradeid` int(11)  DEFAULT NULL,
  `rr_workingtype` varchar(255)  DEFAULT NULL,
  `rr_percentage` double  DEFAULT NULL,
  `rr_description` varchar(255)  DEFAULT NULL,
  `rr_creatorid` varchar(255) DEFAULT NULL,
  `rr_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `rr_modifierid` varchar(255) NOT NULL,
  `rr_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*insert into rent_recovery values (1,,1,'',1.5,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (2,,2,'',1.5,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (3,,3,'',1.5,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (3,,4,'',1,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (3,,5,'',1,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (6,,1,'',3,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (7,,2,'',3,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (8,,3,'',3,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (9,,4,'',3,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (10,,5,'',2,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (11,,1,'',4,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (12,,2,'',4,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (13,,3,'',4,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (14,,4,'',4,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into rent_recovery values (15,,5,'',3,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);*/


-- -------------------------------------------------------------------
--
-- Table structure for table ccaallowance_calculation`
--

CREATE TABLE `ccaallowance_calculation` (
  `cca_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `cca_payscaleid` int(11)  DEFAULT NULL,
  `cca_gradeid` int(11)  DEFAULT NULL,
  `cca_workingtype` varchar(255)  Default NULL,
  `cca_amount` double  DEFAULT NULL,
  `cca_description` varchar(255) DEFAULT NULL,
  `cca_creatorid` varchar(255) DEFAULT NULL,
  `cca_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `cca_modifierid` varchar(255) NOT NULL,
  `cca_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*insert into ccaallowance_calculation values(1,,1,'',360,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(2,,2,'',180,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(3,,1,'',500,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(4,,2,'',260,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(5,,1,'',800,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(6,,2,'',400,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(7,,1,'',1200,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into ccaallowance_calculation values(8,,2,'',720,'','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);*/
