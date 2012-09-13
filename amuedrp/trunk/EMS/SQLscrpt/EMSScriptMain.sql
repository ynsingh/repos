-- MySQL dump 10.13  Distrib 5.5.14, for Linux (x86_64)
--
-- Host: localhost    Database: emsiitk
-- ------------------------------------------------------
-- Server version	5.5.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_registration`
--

DROP TABLE IF EXISTS `admin_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_registration` (
  `registration_id` int(11) NOT NULL DEFAULT '0',
  `institute_name` varchar(100) DEFAULT NULL,
  `abbreviated_name` varchar(20) DEFAULT NULL,
  `Institute_address` varchar(100) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `Country` varchar(30) DEFAULT NULL,
  `pin` varchar(20) DEFAULT NULL,
  `land_line_no` varchar(20) DEFAULT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `domain` varchar(20) DEFAULT NULL,
  `type_of_institute` varchar(20) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `admin_fname` varchar(50) DEFAULT NULL,
  `admin_lname` varchar(50) DEFAULT NULL,
  `admin_designation` varchar(50) DEFAULT NULL,
  `admin_email` varchar(100) DEFAULT NULL,
  `admin_password` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `courtesy` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `working_status` varchar(50) NOT NULL DEFAULT 'OK',
  `user_id` varchar(200) DEFAULT NULL,
  `enrollment` varchar(20) DEFAULT NULL,
  `institute_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`registration_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_registration`
--

LOCK TABLES `admin_registration` WRITE;
/*!40000 ALTER TABLE `admin_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ballot`
--

DROP TABLE IF EXISTS `ballot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ballot` (
  `ballot_id` varchar(100) NOT NULL DEFAULT '',
  `election_id` varchar(100) NOT NULL DEFAULT '',
  `institute_id` varchar(100) NOT NULL DEFAULT '',
  `ballot_name` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ballot_id`,`institute_id`,`election_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ballot`
--

LOCK TABLES `ballot` WRITE;
/*!40000 ALTER TABLE `ballot` DISABLE KEYS */;
/*!40000 ALTER TABLE `ballot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate1`
--

DROP TABLE IF EXISTS `candidate1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate1` (
  `candidate_id` int(11) NOT NULL DEFAULT '0',
  `position_id` int(11) NOT NULL DEFAULT '0',
  `election_id` varchar(100) NOT NULL DEFAULT '',
  `institute_id` varchar(100) NOT NULL DEFAULT '',
  `candidateName` varchar(50) DEFAULT NULL,
  `enrolment` varchar(20) DEFAULT NULL,
  `menifesto` longblob,
  `offline_vote` int(11) DEFAULT '0',
  `agm` int(11) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`,`position_id`,`election_id`,`institute_id`),
  KEY `position_id` (`position_id`,`election_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate1`
--

LOCK TABLES `candidate1` WRITE;
/*!40000 ALTER TABLE `candidate1` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_registration`
--

DROP TABLE IF EXISTS `candidate_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `position` varchar(50) NOT NULL DEFAULT '',
  `status1` varchar(50) DEFAULT NULL,
  `rejected_reason` varchar(255) DEFAULT NULL,
  `request_date` varchar(20) DEFAULT NULL,
  `accepted_date` varchar(20) DEFAULT NULL,
  `proposed_by` varchar(200) DEFAULT NULL,
  `seconded_by` varchar(200) DEFAULT NULL,
  `position_accepted` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`enrollment`,`institute_id`,`election_id`,`position`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_registration`
--

LOCK TABLES `candidate_registration` WRITE;
/*!40000 ALTER TABLE `candidate_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `election`
--

DROP TABLE IF EXISTS `election`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `election` (
  `election_id` varchar(20) NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `election_name` varchar(300) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `nstart` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nend` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `scrutnyDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `scrutnyEndDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawlDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawlEndDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `result_declaration_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` varchar(200) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`election_id`,`institute_id`),
  KEY `institute_id` (`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `election`
--

LOCK TABLES `election` WRITE;
/*!40000 ALTER TABLE `election` DISABLE KEYS */;
/*!40000 ALTER TABLE `election` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `election_manager`
--

DROP TABLE IF EXISTS `election_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `election_manager` (
  `manager_id` varchar(100) NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `department` varchar(50) DEFAULT NULL,
  `staff_id` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `user_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`manager_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `election_manager`
--

LOCK TABLES `election_manager` WRITE;
/*!40000 ALTER TABLE `election_manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `election_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `election_manager_migrate`
--

DROP TABLE IF EXISTS `election_manager_migrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `election_manager_migrate` (
  `election_id` varchar(20) NOT NULL,
  `institute_id` varchar(20) NOT NULL,
  `election_name` varchar(300) DEFAULT NULL,
  `old_em` varchar(200) DEFAULT NULL,
  `new_em` varchar(200) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`election_id`,`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `election_manager_migrate`
--

LOCK TABLES `election_manager_migrate` WRITE;
/*!40000 ALTER TABLE `election_manager_migrate` DISABLE KEYS */;
/*!40000 ALTER TABLE `election_manager_migrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electionrule`
--

DROP TABLE IF EXISTS `electionrule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electionrule` (
  `rule_id` varchar(20) NOT NULL DEFAULT '',
  `election_id` varchar(20) NOT NULL DEFAULT '',
   `position_id` int NOT NULL DEFAULT '',
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `criteria` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`rule_id`,`election_id`,`institute_id`,`position_id`),
  KEY `election_id` (`election_id`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electionrule`
--

LOCK TABLES `electionrule` WRITE;
/*!40000 ALTER TABLE `electionrule` DISABLE KEYS */;
/*!40000 ALTER TABLE `electionrule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eligibility`
--

DROP TABLE IF EXISTS `eligibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eligibility`
--

LOCK TABLES `eligibility` WRITE;
/*!40000 ALTER TABLE `eligibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `eligibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institute`
--

DROP TABLE IF EXISTS `institute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institute` (
  `registration_id` int(11) DEFAULT NULL,
  `institute_id` varchar(20) NOT NULL,
  `institute_name` varchar(50) DEFAULT NULL,
  `working_status` varchar(50) NOT NULL DEFAULT 'OK',
  PRIMARY KEY (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institute`
--

LOCK TABLES `institute` WRITE;
/*!40000 ALTER TABLE `institute` DISABLE KEYS */;
INSERT INTO `institute` VALUES (0,'ems','ems','OK');
/*!40000 ALTER TABLE `institute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `password` varchar(100) DEFAULT NULL,
  `institute_id` varchar(50) DEFAULT NULL,
  `staff_id` varchar(100) DEFAULT NULL,
  `question` varchar(1000) DEFAULT NULL,
  `ans` varchar(500) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `user_id` varchar(200) DEFAULT NULL,
  KEY `login_ibfk_1` (`staff_id`,`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('17c4520f6cfd1ab53d8745e84681eb49','ems','admin.ems',NULL,NULL,'superadmin','superadmin','superadmin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position1`
--

DROP TABLE IF EXISTS `position1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position1`
--

LOCK TABLES `position1` WRITE;
/*!40000 ALTER TABLE `position1` DISABLE KEYS */;
/*!40000 ALTER TABLE `position1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_voter`
--

DROP TABLE IF EXISTS `set_voter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `set_voter` (
  `election_id` varchar(20) NOT NULL,
  `institute_id` varchar(20) NOT NULL,
  `enrollment` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`election_id`,`institute_id`,`enrollment`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `set_voter`
--

LOCK TABLES `set_voter` WRITE;
/*!40000 ALTER TABLE `set_voter` DISABLE KEYS */;
/*!40000 ALTER TABLE `set_voter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_detail`
--

DROP TABLE IF EXISTS `staff_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_detail` (
  `staff_id` varchar(100) NOT NULL,
  `title` varchar(5) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `email_id` varchar(100) DEFAULT NULL,
  `date_joining` date DEFAULT NULL,
  `date_releaving` date DEFAULT NULL,
  `father_name` varchar(30) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `address1` varchar(50) DEFAULT NULL,
  `city1` varchar(20) DEFAULT NULL,
  `state1` varchar(20) DEFAULT NULL,
  `country1` varchar(20) DEFAULT NULL,
  `zip1` varchar(20) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `city2` varchar(20) DEFAULT NULL,
  `state2` varchar(20) DEFAULT NULL,
  `country2` varchar(20) DEFAULT NULL,
  `zip2` varchar(20) DEFAULT NULL,
  `institute_id` varchar(20) NOT NULL,
  `enrollment` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`staff_id`,`institute_id`),
  KEY `institute_id` (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_detail`
--

LOCK TABLES `staff_detail` WRITE;
/*!40000 ALTER TABLE `staff_detail` DISABLE KEYS */;
INSERT INTO `staff_detail` VALUES ('admin.ems','','super','admin',NULL,NULL,'ems','2012-02-06','2012-02-06',NULL,'2012-02-06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ems','ems');
/*!40000 ALTER TABLE `staff_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voter_registration`
--

DROP TABLE IF EXISTS `voter_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `mobile_number` varchar(50) DEFAULT NULL,
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
  `image` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `f_name` varchar(200) DEFAULT NULL,
  `m_name` varchar(200) DEFAULT NULL,
  `setVoter` varchar(1) DEFAULT NULL,
  `alternate_mail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enrollment`,`institute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voter_registration`
--

LOCK TABLES `voter_registration` WRITE;
/*!40000 ALTER TABLE `voter_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `voter_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting`
--

DROP TABLE IF EXISTS `voting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voting` (
  `election_id` varchar(20) NOT NULL,
  `institute_id` varchar(20) NOT NULL,
  `voter_ballot_id` varchar(20) NOT NULL,
  PRIMARY KEY (`election_id`,`institute_id`,`voter_ballot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting`
--

LOCK TABLES `voting` WRITE;
/*!40000 ALTER TABLE `voting` DISABLE KEYS */;
/*!40000 ALTER TABLE `voting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting_ballot`
--

DROP TABLE IF EXISTS `voting_ballot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voting_ballot` (
  `voter_ballot_id` varchar(20) NOT NULL,
  `position_id` varchar(20) NOT NULL,
  `candidate_id` varchar(20) NOT NULL,
  PRIMARY KEY (`voter_ballot_id`,`position_id`,`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting_ballot`
--

LOCK TABLES `voting_ballot` WRITE;
/*!40000 ALTER TABLE `voting_ballot` DISABLE KEYS */;
/*!40000 ALTER TABLE `voting_ballot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votingprocess`
--

DROP TABLE IF EXISTS `votingprocess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votingprocess` (
  `institute_id` varchar(20) NOT NULL DEFAULT '',
  `election_id` varchar(20) NOT NULL DEFAULT '',
  `voter_id` varchar(255) NOT NULL DEFAULT '',
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`institute_id`,`election_id`,`voter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votingprocess`
--

LOCK TABLES `votingprocess` WRITE;
/*!40000 ALTER TABLE `votingprocess` DISABLE KEYS */;
/*!40000 ALTER TABLE `votingprocess` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `ruleanswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruleanswer` (
  `rule_id` varchar(20) NOT NULL,
  `election_id` varchar(20) NOT NULL,
  `institute_id` varchar(20) NOT NULL,
  `enrollment` varchar(30) NOT NULL,
  `answer` varchar(500) DEFAULT NULL,
  `enclosure` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`election_id`,`institute_id`,`rule_id`,`enrollment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votingprocess`
--

LOCK TABLES `ruleanswer` WRITE;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-02-08 11:07:33
