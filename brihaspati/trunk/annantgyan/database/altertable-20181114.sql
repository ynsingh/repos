--
-- Table structure for table `feedbackquestion`
--

CREATE TABLE `feedbackquestion` (
  `fq_id` int(11) NOT NULL,
  `fq_crsid` int(11) DEFAULT NULL,
  `fq_question` varchar(255) NOT NULL,
  `optiona` varchar(100) DEFAULT NULL,
  `optionb` varchar(100) DEFAULT NULL,
  `optionc` varchar(100) DEFAULT NULL,
  `optiond` varchar(100) DEFAULT NULL,
  `fq_quesdes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `feedbackquestion`
  ADD PRIMARY KEY (`fq_id`);

ALTER TABLE `feedbackquestion`
  MODIFY `fq_id` int(11) NOT NULL AUTO_INCREMENT;
