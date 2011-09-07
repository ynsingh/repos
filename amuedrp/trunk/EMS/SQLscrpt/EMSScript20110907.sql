CREATE TABLE `email_setup` (
  `institute_id` varchar(20) NOT NULL,
  `email_id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE `election` (
  `election_id` varchar(20) NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `election_name` varchar(300) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `nstart` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nend` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `scrutnyDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `scrutnyEndDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawlDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawlEndDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`election_id`,`institute_id`),
  KEY `institute_id` (`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE `electionrule` (
  `rule_id` varchar(20) NOT NULL DEFAULT '',
  `election_id` varchar(20) NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `criteria` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`rule_id`,`election_id`,`institute_id`),
  KEY `election_id` (`election_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `eligibility` (
  `eligibility_id` varchar(20) NOT NULL DEFAULT '',
  `rule_id` varchar(20) NOT NULL DEFAULT '',
  `election_id` varchar(20) NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `eligibility` varchar(20) DEFAULT NULL,
  `department` varchar(300) DEFAULT NULL,
  `marks` varchar(20) DEFAULT '60',
  `attendence` varchar(20) DEFAULT '70',
  `backlog` varchar(20) DEFAULT 'no',
  `criminallog` varchar(20) DEFAULT 'no',
  `indiscipline` varchar(20) DEFAULT 'no',
  PRIMARY KEY (`eligibility_id`,`rule_id`,`election_id`,`institute_id`),
  KEY `rule_id` (`rule_id`,`election_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



CREATE TABLE `voter_registration` (
  `enrollment` varchar(20) NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `department` varchar(50) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `year` varchar(20) DEFAULT NULL,
  `course_duration` varchar(20) DEFAULT NULL,
  `current_session` varchar(40) DEFAULT NULL,
  `joining_date` varchar(50) DEFAULT NULL,
  `voter_name` varchar(50) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `birthdate` varchar(50) DEFAULT NULL,
  `f_name` varchar(30) DEFAULT NULL,
  `m_name` varchar(30) DEFAULT NULL,
  `mobile_number` int(20) DEFAULT NULL,
  `c_address` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `p_address` varchar(50) DEFAULT NULL,
  `city1` varchar(20) DEFAULT NULL,
  `state1` varchar(20) DEFAULT NULL,
  `zip_code1` varchar(20) DEFAULT NULL,
  `country1` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `image` longblob,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`enrollment`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE `ballot` (
  `ballot_id` varchar(100) NOT NULL DEFAULT '',
  `election_id` varchar(100) NOT NULL DEFAULT '',
  `institute_id` varchar(100) NOT NULL DEFAULT '',
  `ballot_name` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ballot_id`,`institute_id`,`election_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `position1` (
  `position_id` int(11) NOT NULL DEFAULT '0',
  `election_id` varchar(100) NOT NULL DEFAULT '',
  `ballot_id` varchar(100) DEFAULT NULL,
  `institute_id` varchar(100) NOT NULL DEFAULT '',
  `number_of_choice` varchar(20) DEFAULT NULL,
  `position_name` varchar(300) DEFAULT NULL,
  `instruction` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`position_id`,`election_id`,`institute_id`),
  KEY `ballot_id` (`ballot_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `candidate1` (
  `candidate_id` int(11) NOT NULL DEFAULT '0',
  `position_id` int(11) NOT NULL DEFAULT '0',
  `election_id` varchar(100) NOT NULL DEFAULT '',
  `institute_id` varchar(100) NOT NULL DEFAULT '',
  `candidateName` varchar(50) DEFAULT NULL,
  `enrolment` varchar(20) DEFAULT NULL,
  `menifesto` longblob,
  PRIMARY KEY (`candidate_id`,`position_id`,`election_id`,`institute_id`),
  KEY `position_id` (`position_id`,`election_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `candidate_registration` (
  `enrollment` varchar(30) NOT NULL,
  `institute_id` varchar(30) NOT NULL,
  `election_id` varchar(30) NOT NULL,
  `enrolled_in` varchar(30) DEFAULT NULL,
  `p_marks` varchar(30) DEFAULT NULL,
  `p_attendence` varchar(30) DEFAULT NULL,
  `backlog` varchar(50) DEFAULT NULL,
  `criminal` varchar(50) DEFAULT NULL,
  `indisc` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `status1` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`enrollment`,`institute_id`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `voting_ballot` (
  `voter_ballot_id` varchar(20) NOT NULL,
  `position_id` varchar(20) NOT NULL,
  `candidate_id` varchar(20) NOT NULL,
  PRIMARY KEY (`voter_ballot_id`,`position_id`,`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `voting` (
  `election_id` varchar(20) NOT NULL,
  `institute_id` varchar(20) NOT NULL,
  `voter_ballot_id` varchar(20) NOT NULL,
  PRIMARY KEY (`election_id`,`institute_id`,`voter_ballot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `votingprocess` (
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `election_id` varchar(20) NOT NULL DEFAULT '',
  `voter_id` varchar(20) NOT NULL DEFAULT '',
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`institute_id`,`election_id`,`voter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

