-- MySQL dump 10.13  Distrib 5.1.55, for redhat-linux-gnu (i386)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	5.1.55

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
  `user_id` varchar(20) DEFAULT NULL,
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
  `user_id` varchar(100) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `institute_id` varchar(50) DEFAULT NULL,
  `staff_id` varchar(100) DEFAULT NULL,
  `question` varchar(1000) DEFAULT NULL,
  `ans` varchar(500) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `login_ibfk_1` (`staff_id`,`institute_id`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`staff_id`, `institute_id`) REFERENCES `staff_detail` (`staff_id`, `institute_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('superadmin','superadmin','17c4520f6cfd1ab53d8745e84681eb49','ems','admin.ems',NULL,NULL,'superadmin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
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
  PRIMARY KEY (`staff_id`,`institute_id`),
  KEY `institute_id` (`institute_id`),
  CONSTRAINT `staff_detail_ibfk_1` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_detail`
--

LOCK TABLES `staff_detail` WRITE;
/*!40000 ALTER TABLE `staff_detail` DISABLE KEYS */;
INSERT INTO `staff_detail` VALUES ('admin.ems',NULL,'super','admin',NULL,NULL,NULL,'2011-02-12','2011-02-12',NULL,'2011-02-12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ems');
/*!40000 ALTER TABLE `staff_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-03-09  0:00:32
