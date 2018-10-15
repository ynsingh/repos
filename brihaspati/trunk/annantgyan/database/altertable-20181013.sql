
--
-- Table structure for table `studenttest`
--

CREATE TABLE `studenttest` (
  `st_stid` int(11) NOT NULL,
  `st_subid` int(11) DEFAULT NULL,
  `st_stdid` int(11) NOT NULL DEFAULT '0',
  `st_testid` bigint(20) NOT NULL DEFAULT '0',
  `st_starttime` time DEFAULT NULL,
  `st_endtime` time DEFAULT NULL,
  `st_correctlyanswered` int(11) DEFAULT NULL,
  `st_marks` int(11) DEFAULT '0',
  `st_status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `studenttest`
  ADD PRIMARY KEY (`st_stid`),
  ADD UNIQUE KEY `st_stdid` (`st_stdid`,`st_subid`,`st_testid`),
  ADD KEY `studenttest_ibfk_2` (`st_testid`);


ALTER TABLE `studenttest`
  MODIFY `st_stid` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `studenttest`
  ADD CONSTRAINT `studenttest_ibfk_1` FOREIGN KEY (`st_stdid`) REFERENCES `sign_up` (`su_id`),
  ADD CONSTRAINT `studenttest_ibfk_2` FOREIGN KEY (`st_testid`) REFERENCES `test` (`testid`);
