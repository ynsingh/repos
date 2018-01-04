use olas;

CREATE TABLE `set_date` (
  `sed_id` int(11) NOT NULL,
  `sed_acadyear` varchar(255) NOT NULL,
  `sed_sem` varchar(255) NOT NULL,
  `sed_campuscode` varchar(255) NOT NULL,
  `sed_sessionsdate` date NOT NULL,
  `sed_sessionedate` date NOT NULL,
  `sed_examsdate` date NOT NULL,
  `sed_examedate` date NOT NULL,
  `sed_formsubmitsdate` date NOT NULL,
  `sed_formsubmitedate` date NOT NULL,
  `sed_creatorid` varchar(255) NOT NULL,
  `sed_createdate` date NOT NULL,
  `sed_modifierid` varchar(255) NOT NULL,
  `sed_modifiedate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `set_date`
  ADD PRIMARY KEY (`sed_id`);

ALTER TABLE `set_date`
  MODIFY `sed_id` int(11) NOT NULL AUTO_INCREMENT;
