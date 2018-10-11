--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `testid` bigint(20) NOT NULL AUTO_INCREMENT,
  `testname` varchar(30) NOT NULL,
  `testdesc` varchar(100) DEFAULT NULL,
  `testdate` date DEFAULT NULL,
  `testtime` time DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `testfrom` time  DEFAULT NULL,
  `testto` time  DEFAULT NULL ,
  `duration` int(11) DEFAULT NULL,
  `totalquestions` int(11) DEFAULT NULL,
  `attemptedstudents` bigint(20) DEFAULT NULL,
  `testcode` varchar(40) NOT NULL,
  PRIMARY KEY (`testid`),
  UNIQUE KEY `testname` (`testname`),
  CONSTRAINT `test_fk1` FOREIGN KEY (`subid`) REFERENCES `courses` (`cou_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `testid` bigint(20) NOT NULL DEFAULT '0',
  `subid` int(11) NOT NULL DEFAULT '0',
  `question` varchar(500) DEFAULT NULL,
  `optiona` varchar(100) DEFAULT NULL,
  `optionb` varchar(100) DEFAULT NULL,
  `optionc` varchar(100) DEFAULT NULL,
  `optiond` varchar(100) DEFAULT NULL,
  `correctanswer` enum('optiona','optionb','optionc','optiond') DEFAULT NULL,
  `marks` int(11) DEFAULT NULL,
  PRIMARY KEY (`qid`),
  UNIQUE KEY `qid` (`qid`,`testid`,`subid`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`testid`) REFERENCES `test` (`testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `studentquestion`
--

DROP TABLE IF EXISTS `studentquestion`;
CREATE TABLE `studentquestion` (
  `sqid` int(11) NOT NULL AUTO_INCREMENT,
  `su_id` int(11) NOT NULL DEFAULT '0',
  `testid` bigint(20) NOT NULL DEFAULT '0',
  `subid` int(11) NOT NULL DEFAULT '0',
  `quid` int(11) NOT NULL DEFAULT '0',
  `answered` enum('answered','unanswered','review') DEFAULT NULL,
  `stdanswer` enum('optiona','optionb','optionc','optiond') DEFAULT NULL,
   PRIMARY KEY (`sqid`),
  UNIQUE KEY (`quid`,`su_id`,`testid`,`subid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `studenttest`
--

DROP TABLE IF EXISTS `studenttest`;
CREATE TABLE `studenttest` (
  `stid` int(11) NOT NULL AUTO_INCREMENT,
  `stdid` int(11) NOT NULL DEFAULT '0',
  `testid` bigint(20) NOT NULL DEFAULT '0',
  `starttime` time DEFAULT NULL,
  `endtime` time NULL ,
  `correctlyanswered` int(11) DEFAULT NULL,
  `status` enum('over','inprogress') DEFAULT NULL,
  PRIMARY KEY (`stid`),
  UNIQUE KEY (`stdid`,`testid`),
  KEY `testid` (`testid`),
  CONSTRAINT `studenttest_ibfk_1` FOREIGN KEY (`stdid`) REFERENCES `sign_up` (`su_id`),
  CONSTRAINT `studenttest_ibfk_2` FOREIGN KEY (`testid`) REFERENCES `test` (`testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



