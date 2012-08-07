alter table voter_registration modify image varchar(50);
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

