-- MySQL dump 10.11
--
-- Host: localhost    Database: ell
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt

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



DROP TABLE IF EXISTS `bv_dict_image`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bv_dict_image` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `bv_dict_list_id` bigint(20) NOT NULL,
  `image_link` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2429045D5EDC81CF` (`bv_dict_list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=229 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bv_dict_image`
--

LOCK TABLES `bv_dict_image` WRITE;
/*!40000 ALTER TABLE `bv_dict_image` DISABLE KEYS */;
INSERT INTO `bv_dict_image` VALUES (1,0,2816,'<img  height=\"200\"  width=\"250\" src=\"/ell/images/DictImages/alligator.jpg\" />'),(2,0,4847,NULL),(3,0,5206,NULL),(4,0,5357,NULL),(5,0,5836,NULL),(154,0,69190,NULL),(179,0,83610,NULL),(180,0,84166,NULL),(181,0,85643,NULL),(182,0,85826,NULL),(183,0,88480,NULL),(184,0,88534,NULL),(185,0,88568,NULL),(186,0,89345,NULL),(187,0,89713,NULL),(188,0,90006,NULL),(189,0,90362,NULL),(190,0,90446,NULL),(191,0,90619,NULL),(192,0,92800,NULL),(193,0,92985,NULL),(194,0,93067,NULL),(195,0,93621,NULL),(196,0,93966,NULL),(197,0,93988,NULL),(198,0,94043,NULL),(199,0,94227,NULL),(200,0,95546,NULL),(201,0,95577,NULL),(202,0,96421,NULL),(203,0,96543,NULL),(204,0,97017,NULL),(205,0,97163,NULL),(206,0,97764,NULL),(207,0,97894,NULL),(208,0,97977,NULL),(209,0,98814,NULL),(210,0,99467,NULL),(211,0,100175,NULL),(212,0,100311,NULL),(213,0,100700,NULL),(214,0,100795,NULL),(215,0,100796,NULL),(216,0,100865,NULL),(217,0,101315,NULL),(218,0,101558,NULL),(219,0,102609,NULL),(220,0,103088,NULL),(221,0,105837,NULL),(222,0,107360,NULL),(223,0,107458,NULL),(224,0,107762,NULL),(225,0,107775,NULL),(226,0,108975,NULL),(227,0,109270,NULL),(228,0,109324,NULL);
/*!40000 ALTER TABLE `bv_dict_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bv_dict_list`
--

DROP TABLE IF EXISTS `bv_dict_list`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bv_dict_list` (
  `id` int(11) NOT NULL auto_increment,
  `word` varchar(255) NOT NULL default '',
  `bv_dict_type_master_id` varchar(50) NOT NULL,
  `definition` text NOT NULL,
  `level` varchar(10) NOT NULL,
  `version` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `id` (`id`),
  KEY `word` (`word`),
  KEY `FK12BED5C5DD6E6E0` (`bv_dict_type_master_id`)
) ENGINE=MyISAM AUTO_INCREMENT=110555 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bv_dict_list`
--

LOCK TABLES `bv_dict_list` WRITE;
/*!40000 ALTER TABLE `bv_dict_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `bv_dict_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bv_dict_type_master`
--

DROP TABLE IF EXISTS `bv_dict_type_master`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bv_dict_type_master` (
  `id` mediumint(10) unsigned NOT NULL default '0',
  `type` varchar(60) default NULL,
  `version` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bv_dict_type_master`
--

LOCK TABLES `bv_dict_type_master` WRITE;
/*!40000 ALTER TABLE `bv_dict_type_master` DISABLE KEYS */;
INSERT INTO `bv_dict_type_master` VALUES (1,'<i>adjective.</i>',0),(2,'<i>noun.</i>',0),(3,'<i>transitive verb.</i>',0),(4,'<i>adverb.</i>',0),(5,'<i>preposition.</i>',0),(6,'<i>past participle.</i>',0),(7,'<i>intransitive verb.</i>',0),(8,'<i>Noun. plural.</i>',0),(9,'<i>adverb and adjective</i>',0),(10,'<i>verb  transitive & intransitive.</i>',0),(11,'<i>verb</i>',0),(12,'<i>participial adjective.</i>',0),(13,'<i>past participle  & a.</i>',0),(14,'<i>adjective. & noun.</i>',0),(15,'<i>adjective. & adverbe.</i>',0),(16,'<i>interjection and adverb</i>',0),(17,'<i>interjection.</i>',0),(18,'<i>v. inf.</i>',0),(19,'<i>noun . & adjective.</i>',0),(20,'<i>superlative.</i>',0),(21,'<i>adverb and preposition.</i>',0),(22,'<i>adjective & past participle</i>',0),(23,'<i>noun. & verb.</i>',0),(24,'<i>Noun .singular. & plural.</i>',0),(25,'<i>A prefix.</i>',0),(26,'<i>conjunction. .</i>',0),(27,'<i>transitive verb. or intransitive.</i>',0),(28,'<i>preposition. & adverb.</i>',0),(29,'<i>adv. & conj.</i>',0),(30,'<i>n. fem.</i>',0),(31,'<i>transitive verb. & intransitive.</i>',0),(32,'<i>interjunction.</i>',0),(33,'<i>pronoun and adjective</i>',0),(34,'<i>adjuctive. & noun.</i>',0),(35,'<i>noun. plural</i>',0),(36,'<i>adjective. & pronoun.</i>',0),(37,'<i>past participle. </i>',0),(38,'<i>adverb. or preposition.</i>',0),(39,'<i>intransitive verb. & t.</i>',0),(40,'<i>transitive verb </i>',0),(41,'<i>noun</i>',0),(42,'<i>imperative</i>',0),(43,'<i>noun. & transitive verb.</i>',0),(44,'<i>noun.</i>',0),(45,'<i>present participle.</i>',0),(46,'<i>imperative. & past participle </i>',0),(47,'<i>noun. &  transitive verb. & ntransitive.</i>',0),(48,'<i>prefix.</i>',0),(49,'<i>3rd singular. person</i>',0),(50,'<i>adjective. or pronoun.</i>',0),(51,'<i>past participle & adjective.</i>',0),(52,'<i>participial adjective.</i>',0),(53,'<i>plural noun.</i>',0),(54,'<i>ads.</i>',0),(55,'<i>3rd person singular.</i>',0),(56,'<i>n.masc.</i>',0),(57,'<i>verb. & noun.</i>',0),(58,'<i> intransitive verb. &  intransitive.</i>',0),(59,'<i> transitive verb. or intransitive verb.</i>',0),(60,'<i>singular noun & plural.</i>',0),(61,'<i> transitive verb. & noun.</i>',0),(62,'<i>Pronoun</i>',0),(63,'<i>past participle or adjective.</i>',0),(64,'<i>female.</i>',0),(65,'<i>3rd person singular</i>',0),(66,'<i> singular noun</i>',0),(67,'<i>verb</i>',0),(68,'<i>intransitive verb.</i>',0),(69,'<i>participle. & a.</i>',0),(70,'<i>past participle & adjective</i>',0),(71,'<i>singular noun & plural.</i>',0),(72,'<i>n. masc.</i>',0),(73,'<i>adjuctive. </i>',0),(74,'<i>adjective</i>',0),(75,'<i>present participle & vb. n.</i>',0),(76,'<i>participial adjective. & vb. n.</i>',0),(77,'<i>noun</i>',0),(78,'<i>present participle. & a.</i>',0),(79,'<i>imperative singular.</i>',0),(80,'<i>&?;.</i>',0),(81,'<i>noun. or interjunction.</i>',0),(82,'<i>intransitive verb. & noun.</i>',0),(83,'<i>variant</i>',0),(84,'<i>transitive verb. or auxiliary</i>',0),(85,'<i>2d person. singular. present.</i>',0),(86,'<i>3d person. singular. present.</i>',0),(87,'<i> verbal. . n.</i>',0),(88,'<i>compar.</i>',0),(89,'<i>participle.</i>',0),(90,'<i>a. or a. pronoun.</i>',0),(91,'<i>obs. imperative.</i>',0),(92,'<i>adverb. or interjunction.</i>',0),(93,'<i>transitive verb.</i>',0),(94,'<i> conjunction. or adverb.</i>',0),(95,'<i>noun. & adverb.</i>',0),(96,'<i>a</i>',0),(97,'<i>a. & a. pron.</i>',0),(98,'<i>preposition. & conjunction.</i>',0),(99,'<i>noun. & intransitive verb.</i>',0),(100,'<i>adjuctive. or adverb.</i>',0),(101,'<i>collective noun. & plural.</i>',0),(102,'<i>plural.</i>',0),(103,'<i>singular noun & plural.</i>',0),(104,'<i>noun</i>',0),(105,'<i>a. compar.</i>',0),(106,'<i>a. or n.</i>',0),(107,'<i>p. pr.</i>',0),(108,'<i>prefix.</i>',0),(109,'<i>n. & interjunction.</i>',0),(110,'<i>inf. & plural present.</i>',0),(111,'<i>suffix.</i>',0),(112,'<i>a & adv.</i>',0),(113,'<i>interjunction. & noun.</i>',0),(114,'<i> plural pronoun.</i>',0),(115,'<i>plural pronoun..</i>',0),(116,'<i>adjuctive. & conjunction.</i>',0),(117,'<i>pronoun. or adjuctive.</i>',0),(118,'<i>adverb. or a.</i>',0),(119,'<i>n & v.</i>',0),(120,'<i>q.</i>',0),(121,'<i>a & n.</i>',0),(122,'<i>adv. & n.</i>',0),(123,'<i>n. f.</i>',0),(124,'<i>strong imp.</i>',0),(125,'<i>a.</i>',0),(126,'<i>transitive noun.</i>',0),(127,'<i>v. n.</i>',0),(128,'<i>prop. a.</i>',0),(129,'<i>a. m.</i>',0),(130,'<i>n. m.</i>',0),(131,'<i>pers. pron.</i>',0),(132,'<i>transitive verb.& intransitive.</i>',0),(133,'<i>v. impers.</i>',0),(134,'<i>adjective.</i>',0),(135,'<i> intransitive verb or auxiliary</i>',0),(136,'<i>a. & poss. pron.</i>',0),(137,'<i>past participle</i>',0),(138,'<i>adverb. or conjunction.</i>',0),(139,'<i>imperative.</i>',0),(140,'<i>indef. pronoun.</i>',0),(141,'<i>imperative</i>',0),(142,'<i>possessive pron.</i>',0),(143,'<i>imp.</i>',0),(144,'<i>masc.</i>',0),(145,'<i>plural noun.</i>',0),(146,'<i>&aelig;.</i>',0),(147,'<i>female adjective</i>',0),(148,'<i>a. & v. t.</i>',0),(149,'<i>v. imperative.</i>',0),(150,'<i>noun.</i>',0),(151,'<i>transitive verb</i>',0),(152,'<i>noun.& verb.</i>',0),(153,'<i>noun.</i>',0),(154,'<i>adjective. masculine .</i>',0),(155,'<i>verb.& noun.</i>',0),(156,'<i>observation. imperative.plural.</i>',0),(157,'<i>plural noun & singular.</i>',0),(158,'<i>conjunction. & preposition.</i>',0),(159,'<i>preposition. or conjunction.</i>',0),(160,'<i>itransitive verb  & intransitive verb </i>',0),(161,'<i>conj. (but originally a present participle)</i>',0),(162,'<i>adverb.</i>',0),(163,'<i> intransitive verb  & auxiliary.</i>',0),(164,'<i>preposition.</i>',0),(165,'<i>masculine . adjective.</i>',0),(166,'<i>ajective also adverb</i>',0),(167,'<i>subject. 3rd person. singular.</i>',0),(168,'<i>adjective.</i>',0),(169,'<i>propositon. noun. plural.</i>',0),(170,'<i>preposition.</i>',0),(171,'<i>verb. impersonal</i>',0),(172,'<i>pronoun.</i>',0),(173,'<i>definite article.</i>',0),(174,'<i>conjunction. & adverbe.</i>',0),(175,'<i>definite. article.</i>',0),(176,'<i>preposition.</i>',0),(177,'<i>preposition. & conjunction.</i>',0),(178,'<i>interjunction.</i>',0),(179,'<i>transitive verb & i.</i>',0),(180,'<i>noun.</i>',0),(181,'<i>transitive verb.</i>',0),(182,'<i>superl.</i>',0),(183,'<i>imperative.</i>',0),(184,'<i>noun. plural</i>',0),(185,'<i>pronoun</i>',0),(186,'<i>interrogation. adverb.</i>',0),(187,'<i>pronoun. & conjunction.</i>',0),(188,'<i>transitive verb & auxiliary.</i>',0),(189,'<i>adverb. & verb.</i>',0),(190,'<i>adjective , adverb , & noun</i>',0),(191,'<i>past participle , fem</i>',0),(192,'<i>imperfect , past participle , or auxiliary</i>',0),(193,'<i>imperfect , past participle , & adjective</i>',0),(194,'<i>adverb , preposition , & conj.</i>',0),(195,'<i>impersonal, present</i>',0);
/*!40000 ALTER TABLE `bv_dict_type_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bv_dict_voice`
--

DROP TABLE IF EXISTS `bv_dict_voice`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bv_dict_voice` (
  `id` bigint(20) NOT NULL auto_increment,
  `version` bigint(20) NOT NULL,
  `bv_dict_list_id` bigint(20) NOT NULL,
  `voice_link` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK24E13C345EDC81CF` (`bv_dict_list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4523 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bv_dict_voice`
--

LOCK TABLES `bv_dict_voice` WRITE;
/*!40000 ALTER TABLE `bv_dict_voice` DISABLE KEYS */;
/*!40000 ALTER TABLE `bv_dict_voice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-01-03 10:04:31
