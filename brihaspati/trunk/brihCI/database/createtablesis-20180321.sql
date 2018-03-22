
CREATE TABLE `uo_list` (
  `ul_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `ul_userid` int(11)  NOT NULL,
  `ul_empcode` varchar(100)  NOT NULL,
  `ul_uocode` varchar(100)  NOT NULL,
  `ul_uoname` varchar(255) NOT NULL,
  `ul_datefrom` datetime NOT NULL,
  `ul_dateto` datetime NOT NULL,
  `ul_status` varchar(50) NOT NULL,
  `ul_creatorid` varchar(255) NOT NULL,
  `ul_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ul_modifierid` varchar(255) NOT NULL,
  `ul_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`ul_userid`,`ul_uoname`,`ul_uocode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

