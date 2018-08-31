CREATE TABLE `saving_master` (
  `sm_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sm_fyear` varchar(255)  NOT NULL,
  `sm_80C` DECIMAL (15, 2) NOT NULL,
  `sm_80CCD` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80D` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80DD` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80E` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80G` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80GGA` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_80U` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_24B` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_other` DECIMAL (15, 2) DEFAULT 0.00,
  `sm_creatorid` varchar(255) NOT NULL,
  `sm_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sm_modifierid` varchar(255) NOT NULL,
  `sm_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_saving_master` (
  `usm_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `usm_fyear` varchar(10)  NOT NULL,
  `usm_empid` int(11)  NOT NULL,
  `usm_pfno` varchar(30)  NOT NULL,
  `usm_80C` DECIMAL (15, 2) NOT NULL,
  `usm_80CCD` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80D` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80DD` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80E` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80G` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80GGA` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_80U` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_24B` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_other` DECIMAL (15, 2) DEFAULT 0.00,
  `usm_rejres` varchar(255) DEFAULT NULL,
  `usm_status` int(11) default '0',
  `usm_creatorid` varchar(255) NOT NULL,
  `usm_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `usm_modifierid` varchar(255) NOT NULL,
  `usm_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
