USE `telephone`;

--
-- Table structure for table `billtable`
--

DROP TABLE IF EXISTS `billtable`;
CREATE TABLE `billtable` (
  `calldate` varchar(10) default NULL,
  `calltime` varchar(11) default NULL,
  `extn_number` varchar(12) default NULL,
  `dialedno` varchar(22) default NULL,
  `destination` varchar(66) default NULL,
  `calltype` varchar(55) default NULL,
  `duration` varchar(11) default NULL,
  `cost` varchar(11) default NULL,
  `trunk_group` varchar(55) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Table structure for table `paddress`
--

DROP TABLE IF EXISTS `paddress`;
CREATE TABLE `paddress` (
  `p_sn` int(11) NOT NULL auto_increment,
  `usrid` varchar(55) NOT NULL default '',
  `ph` varchar(5) NOT NULL default '',
  `phone_number` varchar(22) NOT NULL default '',
  PRIMARY KEY  (`phone_number`),
  UNIQUE KEY `p_sn` (`p_sn`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `sn` int(11) NOT NULL auto_increment,
  `userid` varchar(55) NOT NULL default '',
  `name` varchar(55) NOT NULL default '',
  `password` varchar(11) NOT NULL default '',
  `occupation` varchar(55) default NULL,
  `department` varchar(55) NOT NULL default '',
  `address` varchar(99) default NULL,
  `password_question` varchar(77) default NULL,
  `hint_answer` varchar(77) NOT NULL default '',
  `status` varchar(5) default 'y',
  PRIMARY KEY  (`userid`),
  UNIQUE KEY `sn` (`sn`),
  UNIQUE KEY `userid` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
