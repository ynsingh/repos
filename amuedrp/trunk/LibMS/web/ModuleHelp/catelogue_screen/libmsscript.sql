-- MySQL dump 10.11
--
-- Host: localhost    Database: libms
-- ------------------------------------------------------
-- Server version	5.0.77

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
-- Table structure for table `accession_register`
--

DROP TABLE IF EXISTS `accession_register`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `accession_register` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `biblio_id` int(11) default NULL,
  `accession_no` varchar(40) default NULL,
  `record_no` int(11) NOT NULL default '0',
  `volume_no` varchar(5) default NULL,
  `location` varchar(200) default NULL,
  `shelving_location` varchar(200) default NULL,
  `index_no` varchar(20) default NULL,
  `no_of_pages` varchar(20) default NULL,
  `physical_width` varchar(20) default NULL,
  `bind_type` varchar(20) default NULL,
  `physical_form` varchar(200) default NULL,
  `physical_description` varchar(200) default NULL,
  `colour` varchar(20) default NULL,
  `date_acquired` varchar(20) default NULL,
  PRIMARY KEY  (`library_id`,`record_no`,`sublibrary_id`),
  KEY `biblio_id` (`biblio_id`,`library_id`,`sublibrary_id`),
  CONSTRAINT `accession_register_ibfk_1` FOREIGN KEY (`biblio_id`, `library_id`, `sublibrary_id`) REFERENCES `bibliographic_details` (`biblio_id`, `library_id`, `sublibrary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `accession_register`
--

LOCK TABLES `accession_register` WRITE;
/*!40000 ALTER TABLE `accession_register` DISABLE KEYS */;
INSERT INTO `accession_register` VALUES ('jmi','jmi',1,'HI1',1,'2','RM','3rd stack','I-XI','200','5.4 inch','hard',NULL,NULL,NULL,'2011-09-13'),('jmi','jmi',1,'HI2',2,'2','RM','3rd stack','I-XI','200','5.4 inch','hard',NULL,NULL,NULL,'2011-09-13'),('jmi','jmi',2,'3',3,'1','RM','','i-xii','150','8.4 inch','hard',NULL,NULL,NULL,'2011-09-13'),('jmi','jmi',2,'4',4,'1','RM','','i-xii','150','8.4 inch','hard',NULL,NULL,NULL,'2011-09-13'),('jmi','jmi',3,'HI5',5,'2','RM','2nd reck','i-xi','200','5.4 inch','hard',NULL,NULL,NULL,'2011-09-13'),('jmi','jmi',3,'HI6',6,'2','RM','2nd reck','i-xi','200','5.4 inch','hard',NULL,NULL,NULL,'2011-09-13');
/*!40000 ALTER TABLE `accession_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_approval`
--

DROP TABLE IF EXISTS `acq_approval`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_approval` (
  `approval_item_id` int(11) NOT NULL default '0',
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `control_no` int(11) default NULL,
  `approval_no` varchar(20) default NULL,
  `no_of_copies` int(11) default NULL,
  `status` varchar(50) default NULL,
  `order_no` varchar(20) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`approval_item_id`),
  KEY `library_id` (`library_id`,`sub_library_id`,`approval_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_approval`
--

LOCK TABLES `acq_approval` WRITE;
/*!40000 ALTER TABLE `acq_approval` DISABLE KEYS */;
INSERT INTO `acq_approval` VALUES (1,'jmi','jmi',1,'2',10,'pending',NULL),(2,'jmi','jmi',1,'2',5,'pending',NULL),(3,'jmi','jmi',2,'44',2,'pending',NULL);
/*!40000 ALTER TABLE `acq_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_approval_header`
--

DROP TABLE IF EXISTS `acq_approval_header`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_approval_header` (
  `approval_no` varchar(20) NOT NULL default '',
  `vendor_id` varchar(50) default NULL,
  `approved_by` varchar(50) default NULL,
  `approval_date` varchar(20) default NULL,
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `acq_mode` varchar(20) default NULL,
  `recommended_by` varchar(20) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`approval_no`),
  KEY `library_id` (`library_id`,`sub_library_id`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_approval_header`
--

LOCK TABLES `acq_approval_header` WRITE;
/*!40000 ALTER TABLE `acq_approval_header` DISABLE KEYS */;
INSERT INTO `acq_approval_header` VALUES ('2','tmh','','2011-07-05','jmi','jmi','Approved',''),('78','tmh','kk','2011-09-13','jmi','jmi','Approved','kk'),('79','tmh','yy','2011-09-13','jmi','jmi','Approved','rr'),('80','tmh','yy','2011-09-13','jmi','jmi','Approved','uiu'),('67','tmh','uu','2011-09-13','jmi','jmi','Approved','uu'),('100','tmh','sunil kumar','2011-09-13','jmi','jmi','Approved','sunil kumar'),('101','tmh','sunil kr','2011-09-13','jmi','jmi','Approved','sunil kr'),('500','tmh','sunil','2011-09-13','jmi','jmi','Approved','sunil'),('50','tmh','sunil kr','2011-09-13','jmi','jmi','Approved','sunil kr'),('8','tmh','kedar','2011-09-13','jmi','jmi','Approved','kedar'),('5','tmh','k','2011-09-13','jmi','jmi','Approved','k'),('52','tmh','e','2011-09-13','jmi','jmi','Approved','e'),('44','tmh','g','2011-09-14','jmi','jmi','Approved','g');
/*!40000 ALTER TABLE `acq_approval_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_bibliography`
--

DROP TABLE IF EXISTS `acq_bibliography`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_bibliography` (
  `title_id` int(11) NOT NULL default '0',
  `doc_type` varchar(50) default NULL,
  `title` varchar(100) default NULL,
  `publisher_name` varchar(100) default NULL,
  `author` varchar(100) default NULL,
  `lcc_no` varchar(20) default NULL,
  `series` varchar(20) default NULL,
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `sub_author` varchar(50) default NULL,
  `publishing_yr` varchar(20) default NULL,
  `publishing_place` varchar(20) default NULL,
  `edition` varchar(100) default NULL,
  `isbn` varchar(20) default NULL,
  `volume_no` varchar(13) default NULL,
  `sub_author0` varchar(100) default NULL,
  `sub_author1` varchar(100) default NULL,
  `sub_author2` varchar(100) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`title_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_bibliography`
--

LOCK TABLES `acq_bibliography` WRITE;
/*!40000 ALTER TABLE `acq_bibliography` DISABLE KEYS */;
INSERT INTO `acq_bibliography` VALUES (1,'Book','computer network','','kanitkar','',NULL,'jmi','jmi','','','','','','','','',''),(2,'Book','c++','','R. Raman','',NULL,'jmi','jmi','','','','','','','','','');
/*!40000 ALTER TABLE `acq_bibliography` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_bibliography_details`
--

DROP TABLE IF EXISTS `acq_bibliography_details`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_bibliography_details` (
  `control_no` int(11) NOT NULL default '0',
  `title_id` int(11) default NULL,
  `no_of_copies` int(11) default NULL,
  `volume` varchar(50) default NULL,
  `subject` varchar(50) default NULL,
  `unit_price` int(11) default NULL,
  `currency` varchar(20) default NULL,
  `primary_budget` varchar(50) default NULL,
  `requested_by` varchar(50) default NULL,
  `requested_date` varchar(20) default NULL,
  `acq_mode` varchar(20) default NULL,
  `status` varchar(20) default NULL,
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `vendor` varchar(100) default NULL,
  `author` varchar(100) default NULL,
  `no_of_volume` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`control_no`),
  KEY `library_id` (`library_id`,`sub_library_id`,`title_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_bibliography_details`
--

LOCK TABLES `acq_bibliography_details` WRITE;
/*!40000 ALTER TABLE `acq_bibliography_details` DISABLE KEYS */;
INSERT INTO `acq_bibliography_details` VALUES (1,1,0,NULL,NULL,1,'INR','Cv',NULL,NULL,'Approved','Fully Approved','jmi','jmi','tmh',NULL,NULL),(2,1,0,NULL,NULL,1,'INR','Cv',NULL,NULL,'Approved','Fully Approved','jmi','jmi','tmh',NULL,NULL),(3,1,1,'',NULL,1,'CUP','Cv','','2011-07-05','Firm Order','','jmi','jmi','tmh',NULL,NULL),(4,2,10,NULL,NULL,150,'INR','Cv',NULL,NULL,'On Approval','','jmi','jmi','tmh',NULL,NULL),(5,2,50,'',NULL,10,'INR','Cv','sunil kumar','2011-09-13','Firm Order',NULL,'jmi','jmi','tmh',NULL,NULL),(6,2,20,'2',NULL,1,'INR','Cv','sunil','2011-09-13','On Approval','','jmi','jmi',NULL,NULL,NULL);
/*!40000 ALTER TABLE `acq_bibliography_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_budget`
--

DROP TABLE IF EXISTS `acq_budget`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_budget` (
  `budgethead_id` varchar(10) NOT NULL,
  `budgethead_name` varchar(20) default NULL,
  `library_id` varchar(20) NOT NULL,
  `budget_desc` varchar(2000) default NULL,
  PRIMARY KEY  (`library_id`,`budgethead_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_budget`
--

LOCK TABLES `acq_budget` WRITE;
/*!40000 ALTER TABLE `acq_budget` DISABLE KEYS */;
INSERT INTO `acq_budget` VALUES ('Cv','Central Budget','jmi','');
/*!40000 ALTER TABLE `acq_budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_budget_allocation`
--

DROP TABLE IF EXISTS `acq_budget_allocation`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_budget_allocation` (
  `transaction_id` int(11) NOT NULL default '0',
  `budgethead_id` varchar(10) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `opening_balance` varchar(50) default NULL,
  `recieved_amount` varchar(50) default NULL,
  `total_amount` varchar(20) default NULL,
  `financial_yr1` varchar(20) default NULL,
  `financial_yr2` varchar(20) default NULL,
  `remarks` varchar(20) default NULL,
  `reqdate` varchar(10) default NULL,
  PRIMARY KEY  (`library_id`,`transaction_id`),
  KEY `budgethead_id` (`budgethead_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_budget_allocation`
--

LOCK TABLES `acq_budget_allocation` WRITE;
/*!40000 ALTER TABLE `acq_budget_allocation` DISABLE KEYS */;
INSERT INTO `acq_budget_allocation` VALUES (1,'Cv','jmi','0','50000','50000','2011',NULL,'','2011-07-05');
/*!40000 ALTER TABLE `acq_budget_allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_budget_transaction`
--

DROP TABLE IF EXISTS `acq_budget_transaction`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_budget_transaction` (
  `transaction_id` int(11) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `acq_budget_head_id` varchar(20) default NULL,
  `control_no` varchar(20) default NULL,
  `amount` double default NULL,
  `transaction_date` varchar(20) default NULL,
  PRIMARY KEY  (`transaction_id`,`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_budget_transaction`
--

LOCK TABLES `acq_budget_transaction` WRITE;
/*!40000 ALTER TABLE `acq_budget_transaction` DISABLE KEYS */;
INSERT INTO `acq_budget_transaction` VALUES (1,'jmi','Cv','1',10,'2011-07-05'),(2,'jmi','Cv','2',2,'2011-07-05'),(3,'jmi','Cv','3',10,'2011-07-05'),(4,'jmi','Cv','4',1500,'2011-09-13'),(5,'jmi','Cv','5',500,'2011-09-13'),(6,'jmi','Cv','6',20,'2011-09-13');
/*!40000 ALTER TABLE `acq_budget_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_currency`
--

DROP TABLE IF EXISTS `acq_currency`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_currency` (
  `conversion_id` int(11) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `source_currency` varchar(20) default NULL,
  `target_currency` varchar(20) default NULL,
  `conversion_rate` float default NULL,
  `system_date` varchar(10) default NULL,
  PRIMARY KEY  (`conversion_id`,`library_id`),
  KEY `library_id` (`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_currency`
--

LOCK TABLES `acq_currency` WRITE;
/*!40000 ALTER TABLE `acq_currency` DISABLE KEYS */;
INSERT INTO `acq_currency` VALUES (1,'jmi','CUP','INR',10,'2011-07-05');
/*!40000 ALTER TABLE `acq_currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_final_demand_list`
--

DROP TABLE IF EXISTS `acq_final_demand_list`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_final_demand_list` (
  `control_no` varchar(10) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `list_id` varchar(18) default NULL,
  `isbn` varchar(18) default NULL,
  `title` varchar(100) default NULL,
  `subtitle` varchar(100) default NULL,
  `author` varchar(200) default NULL,
  `price` varchar(20) default NULL,
  `total_amount` varchar(20) default NULL,
  `volume` varchar(3) default NULL,
  `edition` varchar(5) default NULL,
  `publisher_id` varchar(20) default NULL,
  `bind_id` varchar(40) default NULL,
  PRIMARY KEY  (`control_no`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `acq_final_demand_list_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_final_demand_list`
--

LOCK TABLES `acq_final_demand_list` WRITE;
/*!40000 ALTER TABLE `acq_final_demand_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_final_demand_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_order1`
--

DROP TABLE IF EXISTS `acq_order1`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_order1` (
  `order_item_id` varchar(10) NOT NULL default '',
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `order_no` varchar(20) NOT NULL default '',
  `control_no` int(11) default NULL,
  `approval_item_id` int(11) default NULL,
  `recieving_status` varchar(20) default NULL,
  `recieving_date` varchar(10) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`order_no`,`order_item_id`),
  CONSTRAINT `acq_order1_ibfk_1` FOREIGN KEY (`library_id`, `sub_library_id`, `order_no`) REFERENCES `acq_order_header` (`library_id`, `sub_library_id`, `order_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_order1`
--

LOCK TABLES `acq_order1` WRITE;
/*!40000 ALTER TABLE `acq_order1` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_order1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_order_header`
--

DROP TABLE IF EXISTS `acq_order_header`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_order_header` (
  `order_no` varchar(20) NOT NULL default '',
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `vendor_id` varchar(50) default NULL,
  `discount` varchar(20) default NULL,
  `order_date` varchar(20) default NULL,
  `due_date` varchar(20) default NULL,
  `order_status` varchar(10) default NULL,
  `cancel_reason` varchar(20) default NULL,
  `deliviery_date` varchar(10) default NULL,
  `ship_contact_name` varchar(100) default NULL,
  `ship_company_name` varchar(500) default NULL,
  `ship_address` varchar(500) default NULL,
  `ship_pin` varchar(8) default NULL,
  `ship_fax` varchar(16) default NULL,
  `ship_email` varchar(100) default NULL,
  `ship_cost` varchar(10) default NULL,
  `other_cost` varchar(10) default NULL,
  `tax_rate` varchar(10) default NULL,
  `tax_amount` varchar(10) default NULL,
  `grand_total` varchar(15) default NULL,
  `shipping_method` varchar(100) default NULL,
  `shipping_terms` varchar(500) default NULL,
  `comments` varchar(500) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_order_header`
--

LOCK TABLES `acq_order_header` WRITE;
/*!40000 ALTER TABLE `acq_order_header` DISABLE KEYS */;
INSERT INTO `acq_order_header` VALUES ('200','jmi','jmi','tmh','0','2011-09-13','2011-09-30','Placed',NULL,NULL,'','','','','','','0','0','0','0','0','','','');
/*!40000 ALTER TABLE `acq_order_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_privilege`
--

DROP TABLE IF EXISTS `acq_privilege`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_privilege` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `staff_id` varchar(100) NOT NULL,
  `acq_101` varchar(5) default 'true',
  `acq_102` varchar(5) default 'true',
  `acq_103` varchar(5) default 'true',
  `acq_104` varchar(5) default 'true',
  `acq_105` varchar(5) default 'true',
  `acq_106` varchar(5) default 'true',
  `acq_107` varchar(5) default 'true',
  `acq_108` varchar(5) default 'true',
  `acq_109` varchar(5) default 'true',
  `acq_110` varchar(5) default 'true',
  `acq_111` varchar(5) default 'true',
  `acq_112` varchar(5) default 'true',
  `acq_113` varchar(5) default 'true',
  `acq_114` varchar(5) default 'true',
  `acq_115` varchar(5) default 'true',
  `acq_116` varchar(5) default 'true',
  `acq_117` varchar(5) default 'true',
  `acq_118` varchar(5) default 'true',
  `acq_119` varchar(5) default 'true',
  `acq_120` varchar(5) default 'true',
  `acq_121` varchar(5) default 'true',
  `acq_122` varchar(5) default 'true',
  `acq_123` varchar(5) default 'true',
  `acq_124` varchar(5) default 'true',
  `acq_125` varchar(5) default 'true',
  `acq_126` varchar(5) default 'true',
  `acq_127` varchar(5) default 'true',
  `acq_128` varchar(5) default 'true',
  `acq_129` varchar(5) default 'true',
  `acq_130` varchar(5) default 'true',
  `acq_131` varchar(5) default 'true',
  `acq_132` varchar(5) default 'true',
  `acq_133` varchar(5) default 'true',
  `acq_134` varchar(5) default 'true',
  `acq_135` varchar(5) default 'true',
  `acq_136` varchar(5) default 'true',
  `acq_137` varchar(5) default 'true',
  `acq_138` varchar(5) default 'true',
  `acq_139` varchar(5) default 'true',
  `acq_140` varchar(5) default 'true',
  `acq_141` varchar(5) default 'true',
  `acq_142` varchar(5) default 'true',
  `acq_143` varchar(5) default 'true',
  `acq_144` varchar(5) default 'true',
  `acq_145` varchar(5) default 'true',
  `acq_146` varchar(5) default 'true',
  `acq_147` varchar(5) default 'true',
  `acq_148` varchar(5) default 'true',
  `acq_149` varchar(5) default 'true',
  `acq_150` varchar(5) default 'true',
  `acq_151` varchar(5) default 'true',
  `acq_152` varchar(5) default 'true',
  `acq_153` varchar(5) default 'true',
  `acq_154` varchar(5) default 'true',
  `acq_155` varchar(5) default 'true',
  `acq_156` varchar(5) default 'true',
  `acq_157` varchar(5) default 'true',
  `acq_158` varchar(5) default 'true',
  `acq_159` varchar(5) default 'true',
  `acq_160` varchar(5) default 'true',
  `acq_161` varchar(5) default 'true',
  `acq_162` varchar(5) default 'true',
  `acq_163` varchar(5) default 'true',
  `acq_164` varchar(5) default 'true',
  `acq_165` varchar(5) default 'true',
  `acq_166` varchar(5) default 'true',
  `acq_167` varchar(5) default 'true',
  `acq_168` varchar(5) default 'true',
  `acq_169` varchar(5) default 'true',
  `acq_170` varchar(5) default 'true',
  `acq_171` varchar(5) default 'true',
  `acq_172` varchar(5) default 'true',
  `acq_173` varchar(5) default 'true',
  `acq_174` varchar(5) default 'true',
  `acq_175` varchar(5) default 'true',
  `acq_176` varchar(5) default 'true',
  `acq_177` varchar(5) default 'true',
  `acq_178` varchar(5) default 'true',
  `acq_179` varchar(5) default 'true',
  `acq_180` varchar(5) default 'true',
  `acq_181` varchar(5) default 'true',
  `acq_182` varchar(5) default 'true',
  `acq_183` varchar(5) default 'true',
  `acq_184` varchar(5) default 'true',
  `acq_185` varchar(5) default 'true',
  `acq_186` varchar(5) default 'true',
  `acq_187` varchar(5) default 'true',
  `acq_188` varchar(5) default 'true',
  `acq_189` varchar(5) default 'true',
  `acq_190` varchar(5) default 'true',
  `acq_191` varchar(5) default 'true',
  `acq_192` varchar(5) default 'true',
  `acq_193` varchar(5) default 'true',
  `acq_194` varchar(5) default 'true',
  `acq_195` varchar(5) default 'true',
  `acq_196` varchar(5) default 'true',
  `acq_197` varchar(5) default 'true',
  `acq_198` varchar(5) default 'true',
  `acq_199` varchar(5) default 'true',
  PRIMARY KEY  (`staff_id`,`library_id`),
  CONSTRAINT `login_ibfk_5` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_privilege`
--

LOCK TABLES `acq_privilege` WRITE;
/*!40000 ALTER TABLE `acq_privilege` DISABLE KEYS */;
INSERT INTO `acq_privilege` VALUES ('amu','amu','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('dei','dei','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','false','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','csamu','222','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('jmi','cs','6666','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','amu','admin.amu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('avlin','avlin','admin.avlin','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','admin.bbzhcet','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('dei','dei','admin.dei','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iitk','iitk','admin.iitk','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iu','iu','admin.iu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','admin.jmi','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('rvce','rvce','admin.rvce','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('scc','scc','admin.scc','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('tt','tt','admin.tt','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','admin.yy','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false');
/*!40000 ALTER TABLE `acq_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_recieving_details`
--

DROP TABLE IF EXISTS `acq_recieving_details`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_recieving_details` (
  `recieving_item_id` int(11) NOT NULL default '0',
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `recieving_no` varchar(20) NOT NULL default '',
  `title_id` int(11) default NULL,
  `unit_price` varchar(10) default NULL,
  `recieved_copies` int(11) default NULL,
  `pending_copies` int(11) default NULL,
  `approval_type` varchar(20) default NULL,
  `control_no` int(11) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`recieving_no`,`recieving_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_recieving_details`
--

LOCK TABLES `acq_recieving_details` WRITE;
/*!40000 ALTER TABLE `acq_recieving_details` DISABLE KEYS */;
INSERT INTO `acq_recieving_details` VALUES (1,'jmi','jmi','200',NULL,NULL,5,NULL,NULL,4),(2,'jmi','jmi','200',NULL,NULL,3,NULL,NULL,6),(3,'jmi','jmi','201',NULL,NULL,5,NULL,NULL,4),(4,'jmi','jmi','201',NULL,NULL,5,NULL,NULL,5),(5,'jmi','jmi','201',NULL,NULL,3,NULL,NULL,6),(6,'jmi','jmi','202',2,NULL,4,NULL,NULL,6),(7,'jmi','jmi','202',2,NULL,3,NULL,NULL,5),(8,'jmi','jmi','301',2,NULL,5,NULL,NULL,7),(9,'jmi','jmi','301',2,NULL,20,NULL,NULL,9),(10,'jmi','jmi','301',2,NULL,7,NULL,NULL,8),(11,'jmi','jmi','302',NULL,NULL,20,NULL,NULL,9),(12,'jmi','jmi','302',1,NULL,8,NULL,NULL,8),(13,'jmi','jmi','302',2,NULL,5,NULL,NULL,7);
/*!40000 ALTER TABLE `acq_recieving_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_recieving_header`
--

DROP TABLE IF EXISTS `acq_recieving_header`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_recieving_header` (
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `recieving_no` varchar(20) NOT NULL default '',
  `vendor_id` varchar(20) default NULL,
  `order_no` varchar(20) default NULL,
  `recieved_date` varchar(20) default NULL,
  `recieved_by` varchar(20) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`recieving_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_recieving_header`
--

LOCK TABLES `acq_recieving_header` WRITE;
/*!40000 ALTER TABLE `acq_recieving_header` DISABLE KEYS */;
INSERT INTO `acq_recieving_header` VALUES ('jmi','jmi','200','bpb','200','2011-08-08','kedar'),('jmi','jmi','201','bpb','200','2011-08-08',''),('jmi','jmi','202','bpb','200','2011-08-08',''),('jmi','jmi','301','TMH','201','2011-08-08','kedar'),('jmi','jmi','302','TMH','201','2011-08-08','');
/*!40000 ALTER TABLE `acq_recieving_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_vendor`
--

DROP TABLE IF EXISTS `acq_vendor`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `acq_vendor` (
  `vendor_id` varchar(50) NOT NULL default '',
  `library_id` varchar(20) NOT NULL default '',
  `address` varchar(50) default NULL,
  `city` varchar(10) default NULL,
  `state` varchar(20) default NULL,
  `country` varchar(20) default NULL,
  `pin` varchar(10) default NULL,
  `phone` varchar(10) default NULL,
  `fax` varchar(10) default NULL,
  `email` varchar(100) default NULL,
  `entry_date` varchar(20) default NULL,
  `sub_library_id` varchar(20) NOT NULL default '',
  `vendor` varchar(100) default NULL,
  `contact_person` varchar(50) default NULL,
  `vendor_currency` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_vendor`
--

LOCK TABLES `acq_vendor` WRITE;
/*!40000 ALTER TABLE `acq_vendor` DISABLE KEYS */;
INSERT INTO `acq_vendor` VALUES ('tmh','jmi','','','','','','64399','','kedar_muz@rediffmail.com',NULL,'jmi','Tata Mcgrawhill Ltd','Asif','INR');
/*!40000 ALTER TABLE `acq_vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_registration`
--

DROP TABLE IF EXISTS `admin_registration`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `admin_registration` (
  `registration_id` int(11) NOT NULL auto_increment,
  `institute_name` varchar(100) default NULL,
  `abbreviated_name` varchar(20) default NULL,
  `Institute_address` varchar(100) default NULL,
  `city` varchar(40) default NULL,
  `state` varchar(50) default NULL,
  `Country` varchar(30) default NULL,
  `pin` varchar(20) default NULL,
  `land_line_no` varchar(20) default NULL,
  `mobile_no` varchar(20) default NULL,
  `domain` varchar(20) default NULL,
  `login_id` varchar(20) default NULL,
  `type_of_institute` varchar(20) default NULL,
  `website` varchar(50) default NULL,
  `admin_fname` varchar(50) default NULL,
  `admin_lname` varchar(50) default NULL,
  `admin_designation` varchar(50) default NULL,
  `admin_email` varchar(100) default NULL,
  `admin_password` varchar(200) default NULL,
  `status` varchar(20) default NULL,
  `library_id` varchar(50) default NULL,
  `library_name` varchar(500) default NULL,
  `courtesy` varchar(10) default NULL,
  `gender` varchar(10) default NULL,
  `staff_id` varchar(100) default NULL,
  `working_status` varchar(50) NOT NULL default 'OK',
  `insti_logo` longblob,
  PRIMARY KEY  (`registration_id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `admin_registration`
--

LOCK TABLES `admin_registration` WRITE;
/*!40000 ALTER TABLE `admin_registration` DISABLE KEYS */;
INSERT INTO `admin_registration` VALUES (1,'Aligarh Muslim University','AMU','AMU, Aligarh','Aligarh','U.P.','India','202002','','9319098461',NULL,'aasim','govt','http://www.amu.ac.in','Aasim','Zafar','Administrator','aasimzafar@gmail.com','3f6ea67e781aa476e8638df6d5877447','Registered','amu','Maulana Azad Library1','Dr','male',NULL,'OK',NULL),(2,'IIT Kanpur','IITK','IIT Kanpur','Kanpur','U.P.','India','','','9223432451',NULL,'ynsingh','govt','http://www.iitk.ac.in','Yatindra Nath','Singh','','ynsingh69@gmail.com','8a9b1c308adbdefe4c47d62bf861f49d','Registered','iitk','IITK Library','Prof.','male',NULL,'OK',NULL),(3,'Dayal Bagh Educational Institute','DEI','Dayalbagh, Agra','Agra','U.P.','India','202002','','09927400888',NULL,'prem','semi_govt','www.dei.ac.in','Prem Sewak','Sudhish','Administrator','prem@gmail.com','f0c17eb77a937d973f1625f0441d02b6','Registered','dei','Dayalbagh Central Library','Mr.','male',NULL,'OK',NULL),(4,'Jamia Millia Islamia','','Jamia Nagar','New Delhi','Delhi1','India1','11','','098100',NULL,'azim','govt','','muzaffar','azim','','kedar9002@gmail.com',NULL,'Registered','jmi','Dr. Zakir Hussain Library','Select','male','admin.jmi','OK',NULL),(5,'Shivaji Science College','SCC','Amravati','Amravati','Maharashtra','India','','','09422155049',NULL,'murtaza','govt','','Murtaza','Ali','Administrator','murtaza@gmail.com','129e3011b32e8c12fd23475e3106ff6b','Registered','scc','Shivaji Library','','male','admin.scc','OK',NULL),(6,'R.V.College of ENgineering','R.V.C.E','Mysore Road, 8th Mile','bengaluru','Karnataka','India','560059','6717 8020','9901945674',NULL,'renukaprasadb','self_financed','http://www.rvce.edu.in','subramanya','kn','director admin','renukaprasadb@gmail.com','46eed70681a9a156462ae05b29a6914e','Registered','rvce','rvce-library','','male','admin.rvce','OK',NULL),(7,'AVINASHILINGAM DEEMED UNIVERSITY FOR WOMEN','','FACULTY OF ENGINEERING','Coimbatore','TmailNadu','India','-641 108','','9842057738',NULL,'auengg','self_financed','www.auengg.com','avi','admin','administrator','admin@auengg.com','0192023a7bbd73250516f069df18b500','Registered','avlin','avilib','','male','admin.avlin','OK',NULL),(8,'Book Bank','ZHCET','AMU','Aligarh','Uttar Pradesh','India','202002','0','9412509849',NULL,'bbzhcet','govt','','Asif fareed','Siddiqui','Assistant Librarian','asiffareedsiddiqui@yahoo.com','7e0deb8853363a84629ba98fbaa29c55','Registered','bbzhcet','Book Bank, ZHCET','Mr.','male','admin.bbzhcet','OK',NULL),(9,'hgfgh','fghfgh','fghfghfgh','fghfg','hfghfg','gfhfg','fghfg','','56754745',NULL,'kk','','','kk','kk','','kedar9002@gmail.com',NULL,'Registered','tt','ghfgjhfg','','male','admin.tt','OK',NULL),(10,'i','i','i','i','i','i','i','hdfh','645645',NULL,'u','','','i','i','hgdh','kedar9002@gmail.com',NULL,'Registered','iu','fhdfh','i','male','admin.iu','OK',NULL),(11,'k','h','jh','kh','khk','kkh','kk','kj','64564564',NULL,'k','','','k','kjkh','kk','kedar9002@gmail.com',NULL,'Registered','yy','fhdfh','hk','male','admin.yy','OK',NULL);
/*!40000 ALTER TABLE `admin_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_currency`
--

DROP TABLE IF EXISTS `base_currency`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `base_currency` (
  `library_id` varchar(20) NOT NULL,
  `base_currency_symbol` varchar(20) NOT NULL default '',
  `Formal_Name` varchar(20) default NULL,
  `direction` varchar(3) default NULL,
  PRIMARY KEY  (`base_currency_symbol`,`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `base_currency`
--

LOCK TABLES `base_currency` WRITE;
/*!40000 ALTER TABLE `base_currency` DISABLE KEYS */;
INSERT INTO `base_currency` VALUES ('jmi','INR','indian rupee','a');
/*!40000 ALTER TABLE `base_currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biblio`
--

DROP TABLE IF EXISTS `biblio`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `biblio` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL default '0',
  `marctag` varchar(20) NOT NULL,
  `indicator1` char(1) default NULL,
  `indicator2` char(1) default NULL,
  `$a` varchar(300) default NULL,
  `$b` varchar(100) default NULL,
  `$c` varchar(100) default NULL,
  `$d` varchar(100) default NULL,
  `$e` varchar(100) default NULL,
  `$f` varchar(100) default NULL,
  `$g` varchar(100) default NULL,
  `$h` varchar(100) default NULL,
  `$i` varchar(100) default NULL,
  `$j` varchar(100) default NULL,
  `$k` varchar(100) default NULL,
  `$l` varchar(100) default NULL,
  `$m` varchar(100) default NULL,
  `$n` varchar(100) default NULL,
  `$o` varchar(100) default NULL,
  `$p` varchar(100) default NULL,
  `$q` varchar(100) default NULL,
  `$r` varchar(100) default NULL,
  `$s` varchar(100) default NULL,
  `$t` varchar(100) default NULL,
  `$u` varchar(100) default NULL,
  `$v` varchar(100) default NULL,
  `$w` varchar(100) default NULL,
  `$x` varchar(100) default NULL,
  `$y` varchar(100) default NULL,
  `$z` varchar(100) default NULL,
  `$0` varchar(100) default NULL,
  `$1` varchar(100) default NULL,
  `$2` varchar(100) default NULL,
  `$3` varchar(100) default NULL,
  `$4` varchar(100) default NULL,
  `$5` varchar(100) default NULL,
  `$6` varchar(100) default NULL,
  `$7` varchar(100) default NULL,
  `$8` varchar(100) default NULL,
  `$9` varchar(100) default NULL,
  PRIMARY KEY  (`library_id`,`bib_id`,`marctag`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `biblio`
--

LOCK TABLES `biblio` WRITE;
/*!40000 ALTER TABLE `biblio` DISABLE KEYS */;
INSERT INTO `biblio` VALUES ('jmi','jmi',1,'010',NULL,NULL,'   99065414 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'020',NULL,NULL,'0764575244',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'035',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'(DLC)   99065414'),('jmi','jmi',1,'040',NULL,NULL,'DLC',NULL,'DLC','DLC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'042',NULL,NULL,'pcc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'050','0','0','QA76.73.C153','W326 1999',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'082','0','0','005.13/3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'21',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'100','1',NULL,'Walnum, Clayton.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'245','1','0','C++ master reference /',NULL,'Clayton Walnum.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'260',NULL,NULL,'Foster City, CA :','Idg Books Worldwide, Inc.,','1999.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'300',NULL,NULL,'xiii, 1517 p. :','ill. ;','25 cm. +',NULL,'1 computer optical disc (4 3/4 in.)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'650',NULL,'0','C++ (Computer program language)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'906',NULL,NULL,'7','cbc','orignew','2','opcn','19','y-gencatlg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'922',NULL,NULL,'ad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'925','0',NULL,'Acquire','2 shelf copies',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'policy default',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'955',NULL,NULL,'pn10/e-pcn 07-14-99; jf03 ibc 03-08-00; jf00 03-09-00; jf09 (desc) 6-9-2000 ; jf11 to sl 6-12-00; jf25 2 copies to Dewey 06-14-00; aa07 06-20-00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `biblio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biblio_temp`
--

DROP TABLE IF EXISTS `biblio_temp`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `biblio_temp` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL default '0',
  `marctag` varchar(20) NOT NULL,
  `indicator1` char(1) default NULL,
  `indicator2` char(1) default NULL,
  `$a` varchar(300) default NULL,
  `$b` varchar(100) default NULL,
  `$c` varchar(100) default NULL,
  `$d` varchar(100) default NULL,
  `$e` varchar(100) default NULL,
  `$f` varchar(100) default NULL,
  `$g` varchar(100) default NULL,
  `$h` varchar(100) default NULL,
  `$i` varchar(100) default NULL,
  `$j` varchar(100) default NULL,
  `$k` varchar(100) default NULL,
  `$l` varchar(100) default NULL,
  `$m` varchar(100) default NULL,
  `$n` varchar(100) default NULL,
  `$o` varchar(100) default NULL,
  `$p` varchar(100) default NULL,
  `$q` varchar(100) default NULL,
  `$r` varchar(100) default NULL,
  `$s` varchar(100) default NULL,
  `$t` varchar(100) default NULL,
  `$u` varchar(100) default NULL,
  `$v` varchar(100) default NULL,
  `$w` varchar(100) default NULL,
  `$x` varchar(100) default NULL,
  `$y` varchar(100) default NULL,
  `$z` varchar(100) default NULL,
  `$0` varchar(100) default NULL,
  `$1` varchar(100) default NULL,
  `$2` varchar(100) default NULL,
  `$3` varchar(100) default NULL,
  `$4` varchar(100) default NULL,
  `$5` varchar(100) default NULL,
  `$6` varchar(100) default NULL,
  `$7` varchar(100) default NULL,
  `$8` varchar(100) default NULL,
  `$9` varchar(100) default NULL,
  PRIMARY KEY  (`bib_id`,`library_id`,`marctag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `biblio_temp`
--

LOCK TABLES `biblio_temp` WRITE;
/*!40000 ALTER TABLE `biblio_temp` DISABLE KEYS */;
INSERT INTO `biblio_temp` VALUES ('jmi','jmi',1,'010',NULL,NULL,'   99065414 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'020',NULL,NULL,'0764575244',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'035',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'040',NULL,NULL,'DLC',NULL,'DLC','DLC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'042',NULL,NULL,'pcc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'050','0','0','QA76.73.C153','W326 1999',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'082','0','0','005.13/3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'100','1',NULL,'Walnum, Clayton.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'245','1','0','C++ master reference /',NULL,'Clayton Walnum.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'260',NULL,NULL,'Foster City, CA :','Idg Books Worldwide, Inc.,','1999.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'300',NULL,NULL,'xiii, 1517 p. :','ill. ;','25 cm. +',NULL,'1 computer optical disc (4 3/4 in.)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'650',NULL,'0','C++ (Computer program language)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'906',NULL,NULL,'7','cbc','orignew','2','opcn','19','y-gencatlg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'922',NULL,NULL,'ad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'925','0',NULL,'Acquire','2 shelf copies',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'policy default',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi',1,'955',NULL,NULL,'pn10/e-pcn 07-14-99; jf03 ibc 03-08-00; jf00 03-09-00; jf09 (desc) 6-9-2000 ; jf11 to sl 6-12-00; jf25 2 copies to Dewey 06-14-00; aa07 06-20-00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `biblio_temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibliographic_details`
--

DROP TABLE IF EXISTS `bibliographic_details`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bibliographic_details` (
  `biblio_id` int(11) NOT NULL default '0',
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `document_type` varchar(20) default NULL,
  `book_type` varchar(20) default NULL,
  `accession_type` varchar(20) default NULL,
  `title` varchar(200) default NULL,
  `subtitle` varchar(200) default NULL,
  `alt_title` varchar(200) default NULL,
  `statement_responsibility` varchar(200) default NULL,
  `main_entry` varchar(200) default NULL,
  `added_entry` varchar(200) default NULL,
  `added_entry1` varchar(200) default NULL,
  `added_entry2` varchar(200) default NULL,
  `added_entry3` varchar(200) default NULL,
  `publisher_name` varchar(200) default NULL,
  `publication_place` varchar(200) default NULL,
  `publishing_year` varchar(20) default NULL,
  `call_no` varchar(30) default NULL,
  `parts_no` int(11) default NULL,
  `subject` varchar(200) default NULL,
  `entry_language` varchar(200) default NULL,
  `isbn10` varchar(20) default NULL,
  `isbn13` varchar(20) default NULL,
  `LCC_no` varchar(30) default NULL,
  `edition` varchar(20) default NULL,
  `no_of_copies` int(11) default NULL,
  `author_name` varchar(200) default NULL,
  `guide_name` varchar(200) default NULL,
  `university_faculty` varchar(200) default NULL,
  `degree` varchar(200) default NULL,
  `submitted_on` varchar(20) default NULL,
  `acceptance_year` varchar(20) default NULL,
  `collation1` varchar(20) default NULL,
  `notes` varchar(2000) default NULL,
  `abstract` varchar(2000) default NULL,
  `address` varchar(200) default NULL,
  `state1` varchar(100) default NULL,
  `country` varchar(100) default NULL,
  `email` varchar(200) default NULL,
  `frmr_frq` varchar(20) default NULL,
  `frq_date` varchar(20) default NULL,
  `issn` varchar(20) default NULL,
  `volume_location` varchar(20) default NULL,
  `production_year` int(11) default NULL,
  `source1` varchar(20) default NULL,
  `duration` varchar(20) default NULL,
  `series` varchar(1000) default NULL,
  `type_of_disc` varchar(20) default NULL,
  `file_type` varchar(20) default NULL,
  `date_acquired` varchar(20) default NULL,
  PRIMARY KEY  (`biblio_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `bibliographic_details_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bibliographic_details`
--

LOCK TABLES `bibliographic_details` WRITE;
/*!40000 ALTER TABLE `bibliographic_details` DISABLE KEYS */;
INSERT INTO `bibliographic_details` VALUES (1,'jmi','jmi','Book','ref',NULL,'complete ref java','','','kedar kumar','kedar kumar','','','','','','','','100',NULL,'','HI','z100','','','',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'2011-09-13'),(2,'jmi','jmi','Book','ref',NULL,'Java Bible','','','Asif Iqubal','asif iqubal','','','','','TMH','','2000','200',NULL,'','',NULL,'','','',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'2011-09-13'),(3,'jmi','jmi','Book','txt',NULL,'prem chand','','','prem chand','prem chand','','','','','','','','300',NULL,'','HI',NULL,'','','',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'2011-09-13');
/*!40000 ALTER TABLE `bibliographic_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibliographic_details_lang`
--

DROP TABLE IF EXISTS `bibliographic_details_lang`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bibliographic_details_lang` (
  `biblio_id` int(11) NOT NULL default '0',
  `library_id` varchar(20) character set latin1 NOT NULL,
  `sublibrary_id` varchar(20) collate utf8_bin NOT NULL,
  `document_type` varchar(20) collate utf8_bin default NULL,
  `book_type` varchar(20) collate utf8_bin default NULL,
  `accession_type` varchar(20) collate utf8_bin default NULL,
  `date_acquired` varchar(20) collate utf8_bin default NULL,
  `title` varchar(200) collate utf8_bin default NULL,
  `subtitle` varchar(200) collate utf8_bin default NULL,
  `alt_title` varchar(200) collate utf8_bin default NULL,
  `statement_responsibility` varchar(200) collate utf8_bin default NULL,
  `main_entry` varchar(200) collate utf8_bin default NULL,
  `added_entry` varchar(200) collate utf8_bin default NULL,
  `added_entry1` varchar(200) collate utf8_bin default NULL,
  `added_entry2` varchar(200) collate utf8_bin default NULL,
  `added_entry3` varchar(200) collate utf8_bin default NULL,
  `publisher_name` varchar(200) collate utf8_bin default NULL,
  `publication_place` varchar(200) collate utf8_bin default NULL,
  `publishing_year` varchar(200) collate utf8_bin default NULL,
  `call_no` varchar(30) collate utf8_bin default NULL,
  `parts_no` int(11) default NULL,
  `subject` varchar(200) collate utf8_bin default NULL,
  `entry_language` varchar(200) collate utf8_bin default NULL,
  `isbn10` varchar(20) collate utf8_bin default NULL,
  `isbn13` varchar(20) collate utf8_bin default NULL,
  `LCC_no` varchar(30) collate utf8_bin default NULL,
  `edition` varchar(20) collate utf8_bin default NULL,
  `no_of_copies` int(11) default NULL,
  `author_name` varchar(200) collate utf8_bin default NULL,
  `guide_name` varchar(200) collate utf8_bin default NULL,
  `university_faculty` varchar(200) collate utf8_bin default NULL,
  `degree` varchar(200) collate utf8_bin default NULL,
  `submitted_on` varchar(20) collate utf8_bin default NULL,
  `acceptance_year` varchar(20) collate utf8_bin default NULL,
  `collation1` varchar(20) collate utf8_bin default NULL,
  `notes` varchar(2000) collate utf8_bin default NULL,
  `abstract` varchar(2000) collate utf8_bin default NULL,
  `address` varchar(200) collate utf8_bin default NULL,
  `state1` varchar(100) collate utf8_bin default NULL,
  `country` varchar(100) collate utf8_bin default NULL,
  `email` varchar(200) collate utf8_bin default NULL,
  `frmr_frq` varchar(20) collate utf8_bin default NULL,
  `frq_date` varchar(20) collate utf8_bin default NULL,
  `issn` varchar(20) collate utf8_bin default NULL,
  `volume_location` varchar(20) collate utf8_bin default NULL,
  `production_year` int(11) default NULL,
  `source1` varchar(20) collate utf8_bin default NULL,
  `duration` varchar(20) collate utf8_bin default NULL,
  `series` varchar(1000) collate utf8_bin default NULL,
  `type_of_disc` varchar(20) collate utf8_bin default NULL,
  `file_type` varchar(20) collate utf8_bin default NULL,
  PRIMARY KEY  (`biblio_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `bibliographic_details_lang_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bibliographic_details_lang`
--

LOCK TABLES `bibliographic_details_lang` WRITE;
/*!40000 ALTER TABLE `bibliographic_details_lang` DISABLE KEYS */;
INSERT INTO `bibliographic_details_lang` VALUES (1,'jmi','jmi','Book','ref',NULL,'2011-09-13','à¤•à¥‹à¤®à¥à¤ªà¥à¤²à¥‡à¤¤à¥‡  à¤°à¥‡à¤«  à¤œà¤¾à¤µà¤¾ ','','','à¤•à¥‡à¤¦à¤¾à¤° à¤•à¥à¤®à¤¾à¤° ','à¤•à¥‡à¤¦à¤¾à¤° à¤•à¥à¤®à¤¾à¤° ','','','','','','','','100',NULL,'','HI','z100','','','',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL),(3,'jmi','jmi','Book','txt',NULL,'2011-09-13','à¤ªà¥à¤°à¥‡à¤®  à¤šà¤¾à¤à¤¦ ','','','','à¤ªà¥à¤°à¥‡à¤®  à¤šà¤¾à¤à¤¦ ','','','','','','','','300',NULL,'','HI',NULL,'','','',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL);
/*!40000 ALTER TABLE `bibliographic_details_lang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `book_category` (
  `library_id` varchar(20) NOT NULL,
  `book_type` varchar(20) NOT NULL,
  `Detail` varchar(20) default NULL,
  `fine` float default NULL,
  `IssueDaysLimit` int(11) default NULL,
  `emptype_id` varchar(20) NOT NULL default '',
  `sub_emptype_id` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`book_type`,`library_id`,`emptype_id`,`sub_emptype_id`),
  KEY `library_id` (`library_id`,`emptype_id`,`sub_emptype_id`),
  CONSTRAINT `book_category_ibfk_1` FOREIGN KEY (`library_id`, `emptype_id`, `sub_emptype_id`) REFERENCES `sub_employee_type` (`library_id`, `emptype_id`, `sub_emptype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES ('dei','t','',1.5,5,'st','pg'),('iitk','t','',0,10,'t','prof'),('bbzhcet','TB','',0.5,15,'s','PG'),('jmi','txt','',2,2,'s','pg'),('jmi','txt','',1.5,2,'s','ug'),('scc','txt','',1.5,10,'s','ug');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_privilege`
--

DROP TABLE IF EXISTS `cat_privilege`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cat_privilege` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `staff_id` varchar(100) NOT NULL,
  `cat_201` varchar(5) default 'true',
  `cat_202` varchar(5) default 'true',
  `cat_203` varchar(5) default 'true',
  `cat_204` varchar(5) default 'true',
  `cat_205` varchar(5) default 'true',
  `cat_206` varchar(5) default 'true',
  `cat_207` varchar(5) default 'true',
  `cat_208` varchar(5) default 'true',
  `cat_209` varchar(5) default 'true',
  `cat_210` varchar(5) default 'true',
  `cat_211` varchar(5) default 'true',
  `cat_212` varchar(5) default 'true',
  `cat_213` varchar(5) default 'true',
  `cat_214` varchar(5) default 'true',
  `cat_215` varchar(5) default 'true',
  `cat_216` varchar(5) default 'true',
  `cat_217` varchar(5) default 'true',
  `cat_218` varchar(5) default 'true',
  `cat_219` varchar(5) default 'true',
  `cat_220` varchar(5) default 'true',
  `cat_221` varchar(5) default 'true',
  `cat_222` varchar(5) default 'true',
  `cat_223` varchar(5) default 'true',
  `cat_224` varchar(5) default 'true',
  `cat_225` varchar(5) default 'true',
  `cat_226` varchar(5) default 'true',
  `cat_227` varchar(5) default 'true',
  `cat_228` varchar(5) default 'true',
  `cat_229` varchar(5) default 'true',
  `cat_230` varchar(5) default 'true',
  `cat_231` varchar(5) default 'true',
  `cat_232` varchar(5) default 'true',
  `cat_233` varchar(5) default 'true',
  `cat_234` varchar(5) default 'true',
  `cat_235` varchar(5) default 'true',
  `cat_236` varchar(5) default 'true',
  `cat_237` varchar(5) default 'true',
  `cat_238` varchar(5) default 'true',
  `cat_239` varchar(5) default 'true',
  `cat_240` varchar(5) default 'true',
  `cat_241` varchar(5) default 'true',
  `cat_242` varchar(5) default 'true',
  `cat_243` varchar(5) default 'true',
  `cat_244` varchar(5) default 'true',
  `cat_245` varchar(5) default 'true',
  `cat_246` varchar(5) default 'true',
  `cat_247` varchar(5) default 'true',
  `cat_248` varchar(5) default 'true',
  `cat_249` varchar(5) default 'true',
  `cat_250` varchar(5) default 'true',
  `cat_251` varchar(5) default 'true',
  `cat_252` varchar(5) default 'true',
  `cat_253` varchar(5) default 'true',
  `cat_254` varchar(5) default 'true',
  `cat_255` varchar(5) default 'true',
  `cat_256` varchar(5) default 'true',
  `cat_257` varchar(5) default 'true',
  `cat_258` varchar(5) default 'true',
  `cat_259` varchar(5) default 'true',
  `cat_260` varchar(5) default 'true',
  `cat_261` varchar(5) default 'true',
  `cat_262` varchar(5) default 'true',
  `cat_263` varchar(5) default 'true',
  `cat_264` varchar(5) default 'true',
  `cat_265` varchar(5) default 'true',
  `cat_266` varchar(5) default 'true',
  `cat_267` varchar(5) default 'true',
  `cat_268` varchar(5) default 'true',
  `cat_269` varchar(5) default 'true',
  `cat_270` varchar(5) default 'true',
  `cat_271` varchar(5) default 'true',
  `cat_272` varchar(5) default 'true',
  `cat_273` varchar(5) default 'true',
  `cat_274` varchar(5) default 'true',
  `cat_275` varchar(5) default 'true',
  `cat_276` varchar(5) default 'true',
  `cat_277` varchar(5) default 'true',
  `cat_278` varchar(5) default 'true',
  `cat_279` varchar(5) default 'true',
  `cat_280` varchar(5) default 'true',
  `cat_281` varchar(5) default 'true',
  `cat_282` varchar(5) default 'true',
  `cat_283` varchar(5) default 'true',
  `cat_284` varchar(5) default 'true',
  `cat_285` varchar(5) default 'true',
  `cat_286` varchar(5) default 'true',
  `cat_287` varchar(5) default 'true',
  `cat_288` varchar(5) default 'true',
  `cat_289` varchar(5) default 'true',
  `cat_290` varchar(5) default 'true',
  `cat_291` varchar(5) default 'true',
  `cat_292` varchar(5) default 'true',
  `cat_293` varchar(5) default 'true',
  `cat_294` varchar(5) default 'true',
  `cat_295` varchar(5) default 'true',
  `cat_296` varchar(5) default 'true',
  `cat_297` varchar(5) default 'true',
  `cat_298` varchar(5) default 'true',
  `cat_299` varchar(5) default 'true',
  PRIMARY KEY  (`staff_id`,`library_id`),
  CONSTRAINT `login_ibfk_11` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cat_privilege`
--

LOCK TABLES `cat_privilege` WRITE;
/*!40000 ALTER TABLE `cat_privilege` DISABLE KEYS */;
INSERT INTO `cat_privilege` VALUES ('amu','amu','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('dei','dei','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','csamu','222','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','cs','222','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','cs','6666','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','amu','admin.amu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('avlin','avlin','admin.avlin','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','admin.bbzhcet','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('dei','dei','admin.dei','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iitk','iitk','admin.iitk','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iu','iu','admin.iu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','admin.jmi','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('rvce','rvce','admin.rvce','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('scc','scc','admin.scc','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('tt','tt','admin.tt','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','admin.yy','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false');
/*!40000 ALTER TABLE `cat_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_checkin`
--

DROP TABLE IF EXISTS `cir_checkin`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_checkin` (
  `library_id` varchar(20) NOT NULL,
  `checkin_id` varchar(10) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `member_id` varchar(100) default NULL,
  `returning_date` varchar(15) default NULL,
  `document_id` varchar(10) default NULL,
  `checkout_id` int(11) default NULL,
  `damaged_status` varchar(20) default NULL,
  `loss_status` varchar(20) default NULL,
  `reason` varchar(100) default NULL,
  PRIMARY KEY  (`checkin_id`,`library_id`,`sublibrary_id`),
  KEY `checkout_id` (`checkout_id`,`library_id`,`sublibrary_id`),
  CONSTRAINT `cir_checkin_ibfk_1` FOREIGN KEY (`checkout_id`, `library_id`, `sublibrary_id`) REFERENCES `cir_checkout` (`checkout_id`, `library_id`, `sublibrary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_checkin`
--

LOCK TABLES `cir_checkin` WRITE;
/*!40000 ALTER TABLE `cir_checkin` DISABLE KEYS */;
INSERT INTO `cir_checkin` VALUES ('jmi','1','jmi','111','2011-07-02','1',NULL,'No','No',''),('jmi','2','jmi','111','2011-07-02','1',NULL,'No','No',''),('jmi','3','jmi','111','2011-07-02','1',NULL,'No','No','');
/*!40000 ALTER TABLE `cir_checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_checkout`
--

DROP TABLE IF EXISTS `cir_checkout`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_checkout` (
  `library_id` varchar(20) NOT NULL,
  `memid` varchar(70) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `checkout_id` int(11) NOT NULL,
  `issue_date` varchar(50) default NULL,
  `due_date` varchar(50) default NULL,
  `document_id` varchar(25) NOT NULL,
  `status` varchar(20) default NULL,
  PRIMARY KEY  (`checkout_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `cir_checkout_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_checkout`
--

LOCK TABLES `cir_checkout` WRITE;
/*!40000 ALTER TABLE `cir_checkout` DISABLE KEYS */;
INSERT INTO `cir_checkout` VALUES ('jmi','111','jmi',1,'2011-07-02','2011-07-04','1','returned'),('jmi','111','jmi',2,'2011-07-02','2011-07-04','1','returned'),('jmi','111','jmi',3,'2011-07-02','2011-07-04','1','returned'),('jmi','111','jmi',4,'2011-07-05','2011-07-07','1','issued');
/*!40000 ALTER TABLE `cir_checkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_member_account`
--

DROP TABLE IF EXISTS `cir_member_account`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_member_account` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `memid` varchar(70) NOT NULL,
  `no_of_issueable_book` varchar(10) default NULL,
  `current_issued_book` varchar(10) default NULL,
  `total_issued_book` varchar(10) default NULL,
  `fine` varchar(10) default NULL,
  `no_of_chkout` varchar(10) default NULL,
  `reservation_made` varchar(10) default NULL,
  `lastchkoutdate` varchar(15) default NULL,
  `status` char(100) default NULL,
  `password` varchar(50) default NULL,
  `card_id` varchar(20) default NULL,
  `req_date` varchar(15) default NULL,
  `expiry_date` varchar(15) default NULL,
  `mem_type` varchar(20) default NULL,
  `sub_member_type` varchar(25) default NULL,
  `desg` varchar(100) default NULL,
  `office` varchar(100) default NULL,
  `faculty_id` varchar(20) default NULL,
  `dept_id` varchar(20) default NULL,
  `course_id` varchar(20) default NULL,
  `semester` varchar(10) default NULL,
  `ApprovedBy` varchar(50) default NULL,
  `remark` varchar(1000) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`,`memid`),
  KEY `library_id` (`library_id`,`memid`),
  CONSTRAINT `cir_member_account_ibfk_1` FOREIGN KEY (`library_id`, `memid`) REFERENCES `cir_member_detail` (`library_id`, `memId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_member_account`
--

LOCK TABLES `cir_member_account` WRITE;
/*!40000 ALTER TABLE `cir_member_account` DISABLE KEYS */;
INSERT INTO `cir_member_account` VALUES ('jmi','jmi','111','7','1','4','0.0','0','0','2011-07-05','Active','9bf5e118c4e830a0b0fc40a6aaf69ee5','111','2011-07-06','2011-07-29','s','pg','',NULL,'Select','Select',NULL,'',NULL,'ee'),('jmi','jmi','222','7','0','0','0','0','0','','Blocked','f676043e8394d5f175babfdd852bc1fa','222','2011-09-01','2011-09-30','s','pg','',NULL,'Select','Select',NULL,'',NULL,'ww');
/*!40000 ALTER TABLE `cir_member_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_member_detail`
--

DROP TABLE IF EXISTS `cir_member_detail`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_member_detail` (
  `library_id` varchar(20) NOT NULL,
  `memId` varchar(70) NOT NULL,
  `fname` varchar(50) default NULL,
  `mname` varchar(40) default NULL,
  `lname` varchar(40) default NULL,
  `address1` varchar(200) default NULL,
  `address2` varchar(200) default NULL,
  `city1` varchar(50) default NULL,
  `state1` varchar(50) default NULL,
  `pin1` varchar(15) default NULL,
  `country1` varchar(60) default NULL,
  `city2` varchar(50) default NULL,
  `state2` varchar(50) default NULL,
  `pin2` varchar(15) default NULL,
  `country2` varchar(60) default NULL,
  `email` varchar(100) default NULL,
  `fax` varchar(100) default NULL,
  `phone1` varchar(15) default NULL,
  `phone2` varchar(15) default NULL,
  `location` varchar(70) default NULL,
  `image` longblob,
  `CreatedBy` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`memId`),
  CONSTRAINT `cir_member_detail_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_member_detail`
--

LOCK TABLES `cir_member_detail` WRITE;
/*!40000 ALTER TABLE `cir_member_detail` DISABLE KEYS */;
INSERT INTO `cir_member_detail` VALUES ('jmi','111','kedar','','kumar','adm','','aligrah','UP',NULL,'India','','',NULL,'','kedar9002@gmail.com','','53463463','',NULL,'ÿØÿà\0JFIF\0,,\0\0ÿí	LPhotoshop 3.0\08BIMí\nResolution\0\0\0\0,\0\0\0\0,\0\0\0\08BIM\rFX Global Lighting Angle\0\0\0\0\0\0\0x8BIMFX Global Altitude\0\0\0\0\0\0\08BIMóPrint Flags\0\0\0	\0\0\0\0\0\0\0\0\08BIM\nCopyright Flag\0\0\0\0\0\08BIM\'Japanese Print Flags\0\0\0\0\n\0\0\0\0\0\0\0\08BIMõColor Halftone Settings\0\0\0H\0/ff\0\0lff\0\0\0\0\0\0\0/ff\0\0¡™š\0\0\0\0\0\0\02\0\0\0\0Z\0\0\0\0\0\0\0\0\05\0\0\0\0-\0\0\0\0\0\0\0\08BIMøColor Transfer Settings\0\0\0p\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\08BIMGuides\0\0\0\0\0\0\0\0\0@\0\0@\0\0\0\08BIM\rURL overrides\0\0\0\0\0\0\08BIM\ZSlices\0\0\0\0u\0\0\0\0\0\0\0\0\0\0\0\0\0X\0\0 \0\0\0\n\0U\0n\0t\0i\0t\0l\0e\0d\0-\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0X\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\08BIMICC Untagged Flag\0\0\0\08BIMLayer ID Generator Base\0\0\0\0\0\08BIMNew Windows Thumbnail\0\0¤\0\0\0\0\0\0p\0\0\0T\0\0P\0\0n@\0\0ˆ\0\0ÿØÿà\0JFIF\0\0H\0H\0\0ÿî\0Adobe\0d€\0\0\0ÿÛ\0„\0			\n\r\r\rÿÀ\0\0T\0p\"\0ÿİ\0\0ÿÄ?\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0	\n\03\0!1AQa\"q2‘¡±B#$RÁb34r‚ÑC%’Sğáñcs5¢²ƒ&D“TdEÂ£t6ÒUâeò³„ÃÓuãóF\'”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö7GWgw‡—§·Ç×ç÷\05\0!1AQaq\"2‘¡±B#ÁRÑğ3$bár‚’CScs4ñ%¢²ƒ&5ÂÒD“T£dEU6teâò³„ÃÓuãóF”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö\'7GWgw‡—§·ÇÿÚ\0\0\0?\0ô_µ7÷’ûHñT÷ù¥¼+~Ğr½éwo}§Ì%ö0©o	oKÚ	÷¤Şû@ñ	¾Ò<B¤˜½h\'ß“{í_¾Ò|•éoGÚ\n÷åİ½ö£ä›íNğT·$^—´ïË»{íNòLr{…GyOê½ ¯x÷-ÑyÓı£ÍPõ\n^¢^ĞW¼_ÿĞëE§Å?ªO’¥öº»OÂŒšüÇÄ-höyŞ?è¹ÉÅŞJ‰Ë`à“ş¾hnË´ıoÃSø¥ì“Ñ^åut½TşªÉõîıó÷©72æòC¾#û‘ö‚†WSÕKÕYÍÎ?œÙó?*(Ë¤‰Şäí\niÄGEÃ%õnz©zPv}\ráÅßü]\n´™®Ö8Ÿˆ„F…^èîéz¡>õŒî³[<Öß\"ıR?XzXĞØéş©#üä~ï?İ\'ËTŒ£»±½/Qc»†ïæÎãØ\0¢î«y‡7c[÷ÿ\0ÒqDrÓí^j9ƒÿÑ»ê)z§‰\\óú¾I»\Z>şRfvMŸN×àÑÿ\0EuÛÌûeé,Ì¢¯çmcƒˆŸóUk:æ#4fëOòDóŸµsÛ»øòRÜµŞÛĞ3¯c¸ÃØúüÌ8Ñ÷#³©âXa·6O\0Ëê×1¹>ô½¸«ë=VêI\Zs¯õjôîğn¿ô¾Šçğ?ûİÜıéao«oV½ßÍµµ?qÿ\0Èª¶åßn–Xç	Óî\n¡yL^JpˆÂ)½@<õÁW”òœåãÄ··ä«îKpI\\/ÿÒç	M+=™0cÍ¹n<ÂêF@âœ2\rÍÉÃ•_µ>4A×XîO=‘ãö€nX9pŸÅ\rÙ_º>õSqñJPâ+†!×Tæûç–‰Û“`äîU_qH”,§Ûƒsí` ùğ—®ó¨!SH;¸Gˆ£ÚˆØ6Åîîø)‹Z{Â¦<©LAñDIiÆs÷\'’ªnNæ—ŞÿÓá$ıÉÁ‚Œøj–‹¢·>™î*BÃßT/õ”â{£h %Ş)†tÿ\0ÅE3”ó¢€\'ïO»îFÑL»§•¢R¢™ÉRóCø&”­)¤\nSAÊAİ¹Ú)ÿÔàµK²ÇIt-g·’}V*H¡ÛñüüºBÄI;‚cNïà±I]]ÁÚyîœLy,$‘.÷}<Sê°NC¾œJçÒIÿÙ8BIM!\ZVersion compatibility info\0\0\0\0U\0\0\0\0\0\0\0A\0d\0o\0b\0e\0 \0P\0h\0o\0t\0o\0s\0h\0o\0p\0\0\0\0A\0d\0o\0b\0e\0 \0P\0h\0o\0t\0o\0s\0h\0o\0p\0 \06\0.\00\0\0\0\08BIMJPEG Quality\0\0\0\0\0\0\0\0\0ÿî\0Adobe\0d€\0\0\0ÿÛ\0„\0			\n\r\r\rÿÀ\0X \"\0ÿİ\0\02ÿÄ?\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0	\n\03\0!1AQa\"q2‘¡±B#$RÁb34r‚ÑC%’Sğáñcs5¢²ƒ&D“TdEÂ£t6ÒUâeò³„ÃÓuãóF\'”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö7GWgw‡—§·Ç×ç÷\05\0!1AQaq\"2‘¡±B#ÁRÑğ3$bár‚’CScs4ñ%¢²ƒ&5ÂÒD“T£dEU6teâò³„ÃÓuãóF”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö\'7GWgw‡—§·ÇÿÚ\0\0\0?\0ôÙJTwÛ¾õ-58™ÊR¡!<¥Jâe)JŒ¥)R¸™JR£)J®&Z¥*2”£Jâe)JŒ¥(R­”¥*2”¥Jâe)JiJRM²”¥FR”©L¥%JR¥[)JTe)J“l¥)Q”¥*U²”¥4¤’mt¥4¥)R¸™JiM)JT®%å$Ò”¤«])LšRU²”¥FR”©\\L’”Ò”¥Jµå)Q”ò¥q2””e)J•l¥)Q”¥Û$¥FR”•l’•JRU²”¥FS¤«^R•JRU²”¥FR”•l¥2iI[)JTRIVÊR•JRU²”¥4¦”l¥)L”¤«])QJRU²”¥FR”•l’Q”ò’­yJSJR’­t¥FSÊJµÒ•O)&×JSJR’­t¥4¥)*×”¥4¦”©¼¥)¥1w=Ñ¤q2”¥	Ö<\r©à ì½ÆK£É8G¹¥’Ë[[r”ª‚›Aú4JÃ‡.\'Í#ŞĞ2“¼HO)J€‘İ-Ú¦Òş&r”•JR¥[-ÉnQ”Ò•+‰ä¥BR”©\\Lå)P”¥*W9JT%)J“Æÿ\0ÿĞôyJJ‰% TôĞ¶SªR£?ïNR¤Zògò%)¥)ü¥ZòŸweJR¥[)JTyJ~ä©VËv©nPJtJ“l÷%»D9HR¥ZMßr}Ú!îĞ$	J•i7%¹Bt„¥*U³Ü–å	ù¥)R­œ¥¹A)=’¥[=ÉnP”)R­$¥¹uJP¥q$Ü–åé)Rx™Ê[”&Rÿ\0S)R­œ¥¸($e*W=ÉnPŸ’Z¥Jâ,÷%»ğC%$©\\I7%¹5I*WI¹-Ès§Å$©\\E&ä·!üÒ\'æ•+ˆ¤Kr¥%*WMÁ-Èsà–ä¸UÄ“r}È%Üx¥¸öK…\\I·· îKr\\*ãM¹6ô-ÉnïÙq¥ÜÜ·Û¡.ñ¦ŞŞ‚–â—\n¸Ón	o; è˜8ıü%Â4ûÒÜ8AŞwt¸SÆ›z[ĞwyüÒ.ì—\n¸ÓoKrä·r—\n¸Ón	nAŞJmÿ\0êRáG\Z}ÉnAÜRŞRáO\ZmÉnÕ†	o)p«>à–ä\rå6ò4K…\\mÉnì«îO¸¥Â®4û“İq)%Â4Ææé½vx¡@ù$!9x&õX)zÍĞL\'î‡W’zÍ•q íûÊŸŞ›O—‚4Å.ìÅ§ºU§ûĞÄBq	PWgê7„ş¤öC”éPOg¼%¼J„¥<¡J²ÏqKr„§ÿ\0YJ•l¥<•	”¥*M²ÜÊ„¥?Ü•*ÙÊi×UH”©VÈò¡#ä””©VÌ”¥FR	R­ÿÑô)×DüıF¥êk…ÌãÒ’İ4?Q©nzT®!İ$ø¥<(o	·ı‰R¸‡vsß²S®ªàRŞ8)R¸‚IMºwÁÃÅ*W9JT7·|Ò¤ñ3_Š\\¨nO)R­—)åCpH½\nU³’ï4·©6ËğOÂé	·kÊT«e)OÉGthŸpJ•kÎ©h£!-Ğ•*ÙOÈ\'•\0GÁ<„©VË²Sªé\Z¤\n«e:§Ü$±)	R­šiQ$îNà¥J¶R•	H8rR¥[)KEÕ)?$©VËM;¥*2<SL%J¶r”¨n	÷k¢4«dJEÊ‘3¢T«dN‰¢\niõJ•läi§É)\n%\"{R¥[9”Å4¦™J•l¥)ìšSJT«e)¹	‰şôº@¨ó JtñEVÊRQ”¤¦Iptğ…J|yJ”È¥ÛTÓ	’S/¿ä¦¥/¤%ØÓ¦‰kğIK¥>I¼R))yñI4¥\'²T¥å)„ÒšuIL‰MÛò&Rù£He%4ê›NãD´$¯ãùS‚˜¥ÉşôÊ~ä·vQJOğJ”ÎRû”e/Á*S)JyQ˜Ó²@ù!Id?Ü–Š3àrT¦sıÉş¾J;’İ4©VÊDJ_•F~ô·vJ“l»¥*2–èJ•lüÒìd&ŸR¥2ş)Î…FÚ–îR¥2”ÜÀ¥¸r•%‘&<Ó›wRÜ•)”§	„¥\nU¿ÿÒîH	¤p¥äW­Æ¥ JP¥	¾Z©o$§Dñ¢Q÷•K’R)*•Ù$éi)*–>!?oÊ™/â’YJR™/õ))}Æ—vQ„ÿ\0‘$¯\'€šy	¾|¤’™Iû»¦$ÿ\0rDê–¿ìAVW=Ò’›ñ	vÓºJ²Ëw	¤ÌJd§’U¯»¼¥¹0J5IVËqRÜ£)~D©VY’[”gºt©VWÜv²TyOßò¥J²¾ï¤•ì8)ôJ•e}çéMÊnñÛºJ²¼ÈòKq„’ÿ\0YI:«yKqMİ(KDj¾ä·ÑE:TWŞéM¼ø$›Oš*²¾÷%½6‰øJ•gºû¼Rß*>2Ÿ·ñB•e–ñòH?UÈ”ÊT®\"Ïr[¼T5ŞiR¸’n GÍCæ”R¤ña-Ú!îûÓî)R¸™ÈŸî”¨I”Ò•+‰\')	óC“ıéç_îF•ÄÏò¥*–ï9Õ\nW9ÓDò>ä0ãÄÂ}Ş\Z¥Jâe:ê”öQÜa)ì¥[):ògâ‡»„ä÷J“l’ÑD:™J•l¤r”ëÊ„ø„´ø%J¶s§ŠSª†œp–‰R­œø¥*0Ñ*U²é§šœöOàT«e>ic•\0¥\Z¥J¶DıÉOŠŠ@ «e!\"tMÂmRM²™ïÊReF|uJuÕ*U²’”ş*SÏÉ\ZU²”ÓªŒ÷JR¥[2Rİª„”‰ÓD©VÊJ[¹áF{¥0•*ÙOŞ–íTIM)R­èKŠ?¥*U³ßòN‡?zSà•+‰ÿÓî÷&™Hù%?ïW\\uôñH›²_’­y“ùHûÒLŠ—”ş_r‘-tIVÊRù¨„¿ÖRU²Ÿ)K¶‰§Ÿ$µIKñğKDÉOt¼ÇÍ-÷¤’—ğJtø&øjœ’”Rì–Ÿ4’Rò”ş)’Óı©)\\ü“ÉŸ$Éyx¤¥y”»%“wIK„¼Òä%#ä’•:¥)HLÍ%ZòSƒ\'ÁGıÉL”©VÊRùê£Ï)Oâ•*ÙL$šué’m4¥4ø¤•\"×Úÿ\0¹7ÇT§„“kÊSÙ7Ä¥>%Zÿ\0êM)‰J•l)wQ”¥\ZE²’Št•kÊZwå2S÷„kŸõ)Jdµ(Ò-Zr4óJa)ì›ˆü‰&×ì‘ü‰§åäå%ZòMÙ)ÿ\0zJµÊQä˜’œ¤«]%?4Ü’¬.8”Óä’JÑI~T¤%0’´^Ñ4„µIVù%§É6ï¹4¤›¤ûJ@¤«Á/‚bS÷IZ)/‚R”;$­¨O1§ŠiJgø¤«\\”§¿uO>HR­yŠSø&”¤%I¶Sª@¨ÈJØ•*×’‘M»ä”ù¥J^e$ß=R%%*RJtÑ.è©I¦©‰)$¥û%=Ó%¢J\\¥2S$`|’RóÇI6‰$¥ÿ\0‚R›ğğKÉ%?ÿÔî)¿Ö)Wœuæ>i&J|Bò”Ÿ‚iI%.RM>	ùRéN©¼Ò””¼„§DÄ¥§Ü•)y4¥2S¢Je3”÷<¨Ì¥\'„©Kéİ>œ¨êIL†¼%øÂŒ¤OšT¦R’i!)J”¼ø¤›áñKŸâ’—<r”¦)IIK’”Âo/¹1))”ü“HM)¨IKÈÿ\0bZ}é§T¿*JdTyN%%/)NŠ3iI	R™BSş¡4<S\"Sw’šR&Q¥/?îI6½’×²J_Rü‰Õ7—ŠI]?>iñJRRÿ\0‘\"G	¥-ÃïAŸŒ%ø”ÒRóIJI/õ„´EKù”¸×ºiî#ı©)S=ş	ç²iìše%2ş	’SOœ¤¦Zø¤šØše%/=Òóãºi‚”üÒS-S&”¥%2”Òå)IJÕ |$JJTê’]üÓyş))pRMªI)”JC•4JRS))¦R”ÒRRò’d¸IKÉÿ\0bSıé»$Š—”§]S¥%ZéNJ)Ğ¥ZòR”ÉvI6¿Å)Õ1J{B*e:hšSJH*×’‘ø¦/Å%/¯û”ÉHEKÉM2—t¸á%/0TSJT«e&îM)J\n·ÿÕíÉ×DÓ¢G-\nqm’iMñJÜ•*ÙIî™4ş	§ıéR­œ¥?zt‰4©V¼¥*:”ıÿ\0\"T«^R˜M=Ò9J•k‚”¦”¥J¶R—ğQJuJ•l¼ÒQ˜	Ğ¥ZòTgı©N°¥Zò~IÉÜ£!/$“l“½7	ÉIV¸Kƒ?rm#D¥$Zú&M<\'ü‰)\\%æ›ğHŸ”ËDÃÇº`tJ|U²ñğKä£)üJÿ\0ë	wM)¿ú„¿‚dİÒK.<üÓišJR’—ì—tß•7Á2)j5M&RÖ>()tÉ$Š—ø}É¼’Kä‚—KO’iI%/ù$á+ø%)vü‰’S)óL™ø¥ğIJ”¥7	å%+¶‰ÂnRÑ%(ÇÉ:iKÉ%.’ZGÁ4ëà‚—’iO%%)1ì—É)©D¦å/tå%)#Êo/÷IJé|”¿”¯‚mRŸÅ:JY(K°„é)_4İ“ù¦ò„”­>ä“öù¦IJK$šFRR´ù¤”ş	xù¤¥Oš]¿\"nÅ)EK÷I2H)rÔ¤šgT’RéN©¿‚A(ÊtÁ$”¿Á\"’Z ¥JDıéİÿ\0**ÿÖí	ù&@¸òr\nÒ§ˆ%M:¨ïÉ<ø%IµüR’›ò¥?ŠJ^|SJyÕZ‰Ñ:iJt‘%/)“y%ñù$«eù{¥æ™)IK„“JS	%}BI¼Ò’­”è—ÁFe?<$«_à—)»¥ªJµÂSşôÒ—(*×JxóM?‚Sİ%[$”e)ûÒ¥Zó¢SªoÙ$•k?’R£ªH©‘)wM)Od•kÈJS%ş¡/2Õ4üÒìŠ——åL‘:¤¥ù)&rI*×˜„óø¦”ßÁÚüŠ~8QK¿ñEL¾	OšŠ_Š‹^BR˜p’IeùL¦ï§Í<Á:üÒR’òL~ô¥+¾¼%ª]’IK¦”’IKöI7Ç²\\p’—”§ÓßïJRRó%\"OÜ˜D%?rJ_”¦“Âo©KÈû“ù(ÿ\0ü¤¥ôŸŠiM>z$’™O’Zñ)¸ù¤\nJ]\"S|ü©)t¤&á$”¼¥\'DÃÉ#åÊ\n_ÏïK„¿Ô¦ø¤¥çÍ ™$”ºI’”T®É|H	Å%/Ù0I/Ê’—×æ‘M)N‰)]“Êd¥%.—tŞiAá%/ßÁ)M)„$¦DÊRšRIKöI4ê—Á%[ÿ×ëN©)İÿ\0½j¼úò)Á=”tì’\n¶{üSîn¼¡¤•\'ˆ¤iĞ)J÷ğÔ¥=Çt)\\I€¥#MPµO)R¸’´y¨—¹E24\"Ës’wŞ£ãø\'J•e˜yàş	oe\r.Ğ•\'ˆ³ŞäÛÏÁD\'*E³ŞtğN	ÓD9JR¤ñÉƒ#²}Ç¹B“Ä–RPŞRŞ¤ñ}ü*;‡:¦Ş9J•läkà”¨î>áØê•*×å)ùù¦å)ïà’•?r\\¦Ü/	R­”÷M)·6\"R‘şä©6ËıJE2iIKÉN¢ÊJ_ºG„É’S$´ø„ÉwòIK¥¢a1pIV¹˜O§n7@N\'Í*U²NtáD§²\n¶SşäŞiµI%Zò”¦;¦‘Â*eªSİ0:%ù\nJµçñKÏ…Á$’¼§ïQü‰$†RŸ5»$«e)JŠR•%”¤JŒ„»%J¶I¥/4ÚÊJe>Ã”Éÿ\0”¿—ÃñLU²Mñå)Ñ%/ÊII.ê£:%))”¦òMş²”ê’™HM)§²S)*×‘İ$Ò”ëª*_OîKŸŠiğHj’—L—Í.É)tÉsğKÅ%*O÷¤™/õ	)yù§ı©IM:r’—ğHßêIKÌ¥Ù4¥¤$¥Ä¥)‚ZüÒU¯ ¥)¥)å*SÿĞë‚˜Ç_ïMÚ\nÖ§dxå/$Å\"Ø’-ìóQ“)J•ã¼\'óìšt”Ó%2MÆ‰%Ïd”¸Kø¦ŸÅ.ÿ\0•%/	y&Ô\'á%+ã÷%¯Á6¿$´î’•ÇÉ/šZÇŠR’•â–7~IÑR‡I’ü<PRòBRAòL>:¥İ%2‘şÔ¦TJt’¼¥)¤$’—”‰”ßë)$«]$†…)ì’”’_êy$¦[Š[ÊŒù}éÊ«,ƒÊ[”Sp•&Ë9\'^RtJ~ä©VÈ~D¥4ëğJtÕ%/İ$ÄÊI)t´MªI)_4ò|S%Î§îIV¾âš~i&	)y¾iOû$’—”¿*d»$¥ä¥¸¦JRU¯\'„¤Ç)¾)$«^O~‡xè£\"<’á%Yg!\"~İ@Røj•\'‰\"H`ÇÍ>ãÄ¡J¶d¦ÿ\0P£¸Ê[ŠT›gªZ¨n>)¿ŠT«g¢J)NˆÒ8™şPŸæ‡\'Å)(Rm\'. !ÉO¸ğ•+‰˜)(ù\'Ü¥[)”§IQäJq÷$•ÒM?z]´Õ%):dµã²J_ñJ|>äÒğIKÌ„§EÊJ]4¦×„é)Dø¥\"%))yÓÍ!ø¦”¿‚J\\™”ÂŒ¤•)>i(ù¥İ%/<%\"4ûÓ|©õ”¤üS$‚ŸÿÑê\'”ÇŸß®ó¬¾$¥GTé)~Ş)Jiú¥:¤¥Ï	ÔAJÜ’™¤¦”Óà/!)×NTtĞ\'ŸšJ¶R—Í@éæ8J”¼¥?ziù¤HIK¥)´ïÂ\\¤•å$Éx¤¥õHø÷JRüRµæ~)ç¿tÉ¼’S.ÿ\0P™.é)yğHx&×îKºJ_R–šB_Á%.’o’GñIKÏÜTgîH™î‚—”àè£Â_ê¥2yù$›ºH%t“OÍ?ŠJ]6‰y’Rò—:‚ià”’Rä¥)´\Z¥ªJ].SOÜ˜•)—ú„Ü¦RíñIKşDÉL¥2’—;„¥7šR’—òMã)ŞuI+Ê]ôQN’•ş°ŸP¢IKÏŠZ&”’Rò’]Ó$¥ÿ\0Ş—äM\'âŸÅ%/)“|ÒRà¤”öMİ%/Ï¤SwN’”—iKà—))R—’Zp˜RRóçIÁ?Şi’S=ÆRÜÍCãÂ_©6Rn)CòOù¥[.ò¡&|’Ü~IR­œé¢S§<(nRİ§åJ“l’á4¥»_©V¼¥)·JiÓÍ*S)üJZ|JJe)Jm;¦Óä’™L&”Òœ~(©Sâ”ë¢\\¦tûÒS)JtQO?‚\nÿÒéÌJná9ç^|SwÑl<âó÷¤˜¤RRéµ”µRR»%:&)$¥âN‰%ş¡%/ªCïL—)%yzuõ„ã@‚™Kãà˜ŸÒ’W<¤4M?ŠSâŠ™¢I¤”„ÊyIF{§*R»OŠ~Ú&™Õ)?íIK§PsÚĞK¸AvIá¢5åvE€ØJGr#ÅRuv¤üdòyNöüQÆÚvEcNS¡Ùª´ó®‰nNöÂ8‹gíLğ $rš?6UbIM»Á/l+Œ¶S¿tGšNÊwfñÕWî”£ÁÈâ)¾Ñgs\nlÊ<8’­¯Şòå.Ù<G»}–1Ü|îlÄ‚{ª?Ş”™ñî›íø§ÁĞü©wÑRmÖ4hï½e5l”ÓŒ§Œ6g²pPY‘[†º4@àFæG’inXè¼¤˜;„·´ğDüP¥/)æ?ÚšuÕ-!%.”éù\rD¥åóIJŸ4äÇÁ2I%y×ø¦I\"{ø¤¥N’”ëæšuÑ$”¼ÿ\0ry’£¨ø%ñJ”È|Ó>!)JJT„§îH|RIKÊJ)Ò¥*t’œÉ|’Rò”¨‚xå-AIKù$|’íÊd”¿:¥ä—‚\\¤¥ÁKä˜ò—”¼ø$tÉr’—ü©‚R{$’T>	Ó&IKöñKæ’R’—R”À¤’—Óâ›”¾	Lj’”—äKò¥Ù%+É)H¤JV©&NÖRÿ\04Ò~ô’IV¾äü¨ş^é’¥[4ÜüJ‹®c¼ñ)QM³ş	HU,ê5‚ãât¨\\á\0Ç=ÓÆ•æ´äˆënœ¦%eŒì‘2Aø…f ÇÛFÂx#„%‘/ÿÓéÎ’šR\'²oà¶q~tHè5)§MRÓ¾‰!pSw™ÕA×0äù(úâx)Ü\'²¬&Õ)ñù!zíï!8º¿8OeXIğKEc»„\'iï)Rm>ÉûÊiš\\z\nW	çÃ·0å)ç¿‚JRSò”¡)ü{¢¥ü¸KÍ7Ÿ)h\nWt†š(›X;¨; \0#Üˆ‰=a7Å3œ\Z5?%]×¾ h|PÜ÷IÔ÷)ÂªŒ“ºöÏ·Qâ rx!¨R›â ñEÄó©î›DÃîHÇŞ!yH”Ñşäé)RAM©KÍ%(\'LRü¨©\\{%òøø%¡”»¤…vóì”ù|RŸ÷§T’¯â\"?Ò”¤¥IŸ4¦)»IL’Ÿ—‚nÑÊ]ĞS-ÃÅ gâ£ş¤$•)#n±¼;ıÈ£,şpû´UÇÜRğƒÑ\"D7™‘[ø1ã*s+;¼§p	ø¦]Šá7@\'³Ûu‚ışh¬Ëwç\róOmOœ¿\"o­ñ¬OŠ˜ ÄqÙ2ˆ]k¤šSÌ¤¥OûSKºSşô’¿	¼Ò“	!$/Ù1)vø¥)%yM!!—^¥%+òè/È©¼¸x@CvmcF‚S„$z#ˆwmIJUCœ#èèù(Û4ĞB#»#=Û³şäıÕ›i=‚—ÛŸØ9KÚ’¸ÃwD´\Z¶]:GÁEÙ7<]Éj^ã\r÷=âƒòig.“Øœçn2âOÅ4†Ô ä=Ç=‚}¤¨ `†³ñT÷}İ’ÜíG²8ËiÙ·\"vHgZ9\rU¤J] £íÇ²8¥İ¼ÜÚô–‘<¢³\"‡ğá®‹2e?‚iÃ‘õu´ãò§:¬ÊòlaçsGb¬³9ú@·ÌjrÅ!â¼L×ğH!²ÚŞ=¯ôÿ\0]S)r¿‚yMªƒí­†@î«S4§Á\0fU0xñP~ubv|û\'K²8‡vÖ‰yş\n“ºˆk5¨;:éÒÉŠ}‘Ç@RY£:ğd‘	}¾àLÄjaf^÷¥İ1pL|ôYÏ¼èoÁ	Ö½ÃÜâèâuD`—SJ9GQùT³ó¦tÓU]ı@Ï±¿z¡¸¥¸©õZrÅ™w¼ÄÀ=‚	$òuQ”Ò¤aKI%”¥#…4‘¤2éüTe<¥JÿÔÕ.x&	¦Ì‹©;‡š¹å7Åt4Õåí9Éqµ1vã$Ì ñüRe.ÑVšOğH”0óñRÜ\n¦S÷¥#¶©’IKÿ\0§ÎüRùğ‚™‡¸L§õìñŸŠ”¾\Z¥Â;&Ê_^Ï-TÛ8wÏ¸Uÿ\0*Z¹Ù6[n¹‘¡”3gDyK”¸ŠCkˆçI•ây\'UOïKDêµå%áÙ-Çø#J_¿šyQİ¬Æ…6íuJ”Ë¿šI¤%¤$…õKø¥¯Í.é%S)’$-	ø	‡‚_”¾¿rA)>	$¥wJ|á9\'ä’VN›D¹Iè<§²n9ø%ùSşT’¢”øê˜¥Ù%(h—Ş”ÏÉ)IKü9ìO‡)O%%.Ÿ‘)ğJ~ô’RÚ€ŸB›NŸ½%/\'ı‰&Ó˜JBJQ>(µ\\æiù¾r	Ğè›sb\'à¥$ë/côãÂQ<â\nÍ.lÑK×sH‡$Ã‹²á>î†©qÊ¡ö·ø“æ¢ì‹:È=’ö¤0èoowÜ¤,¬_¨äÛ GÙñGƒ¦ëêh—8|µAvc@ö„ªRâ’†!×T”îË´ù|u®¸‘ÉB\'Á/Šx€ñìŠ\\h¡?íI\ZC2èL_ÛîQ‘ğL•%–é×²EÅE?ÄJ4†D˜Lyå/Ÿ	§ı…%.’GÇÁ A\Zkæ’•:ÇÜ‘Ÿ\Zìm€–™\0Áù!ı¦±[ßŞ¹ÓÅ*=”šRUNsÚÖğZ\\ãà@•]MÁ€¹ÜÏÍ8BIÕÒÿ\0XJcø¬·õ¹¬ÚaÛŒü8jgg»ôÃ»¾‰üöÊ©Ô/ˆæÅDdT\\@:ƒ07,™dÕ?F¸ùÂºÉ%¤‚IwßíGÚU:ÖçÓ[[İ€BgU´1Ä;h–€ßˆ÷,¹>:”Ä˜çD}¸ö´Ó¶z˜İ[¸KŒè4CoS©Ïvó\0º,{ü’KÚeS³^unÇ6¾´4f^ÇÍ:?Ï‚cË²“l~Ø€yHã\n§tŞÂÒá®ÙŸ’UÚÛÓ£µXğÀ}®úJLÈ±… í2^Ú)ÛÕ%˜Ş¤ÿ\0Mûµ|Ÿz€ê3&xìT]o$İÿ\0‚ÏDúnéÌ·ÂŠòØêË§Q È!ÂB*›ø\'CõY¸	çÍ&][Æ‡^#Ì!ELå$Ò&<¥#	)Sâœ¨ÏošSÛ²Jd›É1 \0Iç…\\ÖÛ±ÄnéJ”ÿ\0ÿÕÒ?íM!3¹\"R]<ºü§_â›àRŸ©L´#â”•ù%:%Hf)O(cğJtñB’”şäóR	nB”“ññN†áªEı¸ñJ”Ì¸yL^ÁB{%)R™n% Iî£ªSó”iKÊy3¢Œ¤•)”R•áà”ê•)‘?rsÊŒè”è•)y	şj3Ù!ÀJ”ÊIKwÏÍFRP¥2ƒâ–áÂŒ¤R¥3Rş\nIx¥Jf‰ì˜D÷Ja%/>	÷…H’•)”„Ä¨Ê_ë)R™IìyL7R¯åK²JQi|R™Ñ)Õ%+XJ{”Ò”„R¿äá<éüT\'º[¾IR“İ6à9P$÷JR¥2ÜRŞ~å¸)R™oğQ%/÷¥óF”¤¦Óä™%/:&å(îSø$¥p›Ì|Ò\'””ºo1÷¥¡H¤¥&)ÿ\0]QR¼ÒÒ%/Ê˜ÂJV©%)|;vEJî—‚‰{Yû´B¿+Ó5m ‡ŸÃ„¨•\'OİT·7Óm„H0ÑğÈê.õ=ß@ë>HˆªoúµìŞL4wøLëëi\r`Ÿ»•ŒûÜı;OŞ™÷=ñ\'€@ù™OöüSN‘ê,,ˆ$G€Q£.Ê_ ’ˆ(è”÷N4–¼‡±®cN2¡¼¸Déª‡%Ä\"•ÃˆÖbê)ô\0y\"¥	û“§M4ñJa/JB\0ŸÁ1)~T”¶’SÉ„Í¡<÷N 4¤¥8hÍ-bAü&$¥y&SÏtÚêRR»~)k‘:Oà—Á*a9#ïM¬ø§á*gã	‰®‰F¾IS1kã5à¤ËÃÎ„És Â‰<$¦Íy–7t™0~EX¯¨‚ñ%§Íg‚g’›B ¢ƒqù¯À­öù¥—–[icx>z*2&{w„¸¸“Ï&Q ªmÛX?r>d*î{d™*»\'ÊTšÿÖ¾ã¯Å4”Äê{„¦KO,¼¥:¦JRRòÜ£:¥>~iR™ÊR¡ªR¥3éwQJ|R¥2”¤(Ï)%JeÇ™ì”¨Îš%)R™)ûÔSÊT¦GÉ Ú£>ip•)”¥!E.{¥Je>)L(˜Ñ$ÊuHÊ2’T¦RR>IµñJuI+ÊyQI*C\"‘!Fa<è¤®8òJG)¥4é>)R™\'ç…	„§·d©Lå$=Ú¤]ÚR¥$å4Â)áªT¤’&“Éã·tiLËÓx=”AJ|8J”É\"{÷Q”¥Jeª\\e<¤¥å)ÕFSRRá)ÓÁ6í~	Æ©)_“Å8	§O4¦PRäıÉ÷¨ŸÇÁ)J”ºRštM(©’b|“|øQ/kH“À‚•)Ÿ—2›Ÿš«fu`X`1=û!_™¶×\0}›[÷§7ğÖ¸ó´kòC·ĞlÂÎ·-Ï6Áö¸ˆOÉsƒY:5»cò§i¦ıÙ²Úf%ğB¹nE\0l\n†ó!1s‰.=õO4ù.}B·ÚIrî{ÃA:0@Já.?*4ªQ$èu·Å)‰)wİ1(¥y:™MÈ)†¾ZÂpI0$¥r% ñIK~ô´M>\Z^=á%/:%Ó\0ÓºR\'Ëº\n^#”ˆš{\'i)nyŸ¹-\nnÿ\0‚J_„† &\'B\Z€BJVš}é8$}éÑ{v„”´©çûÒ‰ğM¯Ü’”t	j8ø¦w!<vğEKîĞ§ÍDóäÎšr’—JSãÁKN|tM\"’Je¬şDÑåİ!ùSI„”¼Dü4ñâ“ş¿Úkâ|S!§šbt“ß·Å4Ç‘:ğ	)nà! ›I€‘ü‰)ÿ×¸bSO	Î§É2éYyÕ$ß‘ ~r’—’—dÒ’J])M)JJ]<“ğQóJRS$§ÁFSÊJ^RMßà—É/¯ŠR›²I)’_¹J”¿Ïä—Á.Ğ™%2üIJTgä£eŒ­¥ï0T¤’R™T­êTVı³\"ªµİd‡Mº	N).°>(VåQIÇA15Šz–Iihw2Iø Y{îvëÉºáîõC\'x%¦6÷: ¯Fá´2|\nÅĞüSHÛzp„SÀCÖ\\lÙaì~	Ö,ßahÓÍfÄiÚS\r O˜G†=•ÂzúËïlxB3z9™0Ù†Ÿ†Úİà‡UÂ¹U<¸Ll!ºø›c»i¤‡y¹À÷‰Ô‰ Ÿ’%yWTÇ»”=¾È0z‚e‹_QÈ\02uìJ¼Î¥KœÖ$j|\ni+LHn•6u:_fÀ HÈÖeTÂÀLïŸÁ.ÙS$£ê°‰ú|¯Í>á»lëHR—-:$~ô”©ûÓüe)Ñ*S)JTe R¥2”¤¨Èù$•)”¤Te)Ğ¡JfL&Ü|Šb)àŸvˆ{€ xèlËª¾u ‘Ä%Â–Ìø|”-´W[Ÿ Àî³ÿ\0h¼X{&aV²ç>Gi&>&S†>ê¢ŞÊÍsn¤ƒ¸VÈËsîo\rˆü¨sœÖƒÃDÊ¢|ÓÄ@\\\'8¹ÄI×æ˜˜?‚Nçà˜2œ¥’#ÅKM©‰%oı¼ÔJ~é)s)§Ê“¾ôÀ™òIKÈ:ø&4çT»’RR“Ìj¡=¦R\'_.é%pgø%Î¿ÄÀ0œD$¥uÉ¤ÿ\0»²Bgâ˜7™î’—İ	Õ>ŸrRğIJ“\Zr”‘3Ç!0wd·Hø$¥Á<$åüS\0f{””;Ì\'o¾ij\\‚©7ÀüS~tù!A…) L¢¤ƒyOæÃüS‡ö ¦QãÙ\"\"Snø¤L¯’HW2€{%<wLáùQRîöë2š;qİ;€Û§úÂ@ë=¢J·H;¦&D¥\Z¦ì’—ÖtOÏÉ4ÓÁ3I‰IL¶™Ÿ’iŞxJa1Ÿ½%3hJDG=ÔAöøBpOtÿ\0ÿĞ´N©§_à‘å!¦«§yeå)Mş²šJJe)JnSp\"Je)(”ó¯:¤¥Êu~ò›v‰R™üÓª:¤¥3ÜmñÚT	ÿ\0z`æ>(Ò’o)·«;6¦±®\'Bâ>å[ö¨ÙôeñÏiG…\"%Ó*.°4Kœ\0åbşĞÈ>İÑ >ëIs‰$êîí¿¨c±Û\\ï|UÖ=š	tr|VIq$¤âH€n¿©Şí¤ò\Z[÷òä+3/µt€gæ«ëïNÔh.á?4Œ}êZqÂNãğI+n€¾*\']ÌI·’f[bLÇe{\'.0’—g_5&è‰DöíşÔçQİ%*H‚šxƒÏ)„ëùR\Z¤¦EÉLˆğM±á!1ğIL·jß’yO•	ÓÌ¦™)*™‚AøÈÏqÔ™PF½Ê}”ÜùäˆãTZón­ş qq>\n·ŠR¤Pt™Õ_8K¿Šórj{ZğD<Àø®~H<öNè`OâˆAƒÑèV32\ZÓµÚ¬ø«nê{lq\Z€7ãâ›ÂV˜—D9JDÇx˜òYWõ#c6ŸÈ™ÙÎhcšeÁ°â{™”¸\n¸K¯İ.Ğ\'ç4=»uĞ—	Ó‰	ò2¢ê]Î®EŠ-µ][A$ÆŞ|•lŒÀÒö4û‡FË]cÜò~—(ˆ÷H‹¨ü–€àß¤¸O9»Z#wŠ ë\\â	ìİ¿$ÅÒ’pˆU6,Ê}“\'è™iÙW™2u”ßÅ-\0âŠY9áG¸M\"<í¢IQ˜;ôwåL^ iÂbíÇÍ.O¸¥¬Oy€›p™ÿ\0].:öAL·@¹1 Ÿ5íbã(©™#„ˆÑCıBEÜ¤¦SşôÓ.øğ›â:“	%}6Ï‚AÓóQçÉ;Fi)SÏhLJGı‰nÕ%/É%!ù	¬$¦[†¾\n;¦BŒûŠrA2’WŸ	GŞ›DÓİ%2î”‰\n è˜vüRS.`àè2 çD÷©ß¿))]µøBF>iœ`„Œóâ’—$ƒ\'ºyü|<’ñIKòByçÅE¤~	ÇŠJTûuã”ä‰M»]Râ\'“ÊJ^L¥¸\'¾ª.:¥ıé)–ï2w¹DDJJ\\<ø©nñPî—d”ÌÙ\Zry\Z(k¯š‘ày$¦[´Õ)<ƒO’@è‚©œ!>á.ç¹Ñ)ŠJ§ÿÑ1HM%#@›Íu/,¾ã	ITI\rL9î¢m`üè‰rT¤›§â8KyCõk÷\rÃÙô¼»¡œº@wÒ\"¥SbIJJ§ûBŸw>Ó; ÛÔõ\"¶é§?ô’¤ˆ—GŸ4Î{\0˜•“gP¸–‘íÚ5ó(/Èµä9Î$áHv‘K[%À%UT­®x@Ñ§Åf9ÄÌ™ÑGHK…p€êİRµìÙÁ=Â¬Ü›[ :\'CùTê›™Ep\0.KÏ¶x3÷¦Âq )\r$¢•—%ãæ\0I%)’’–ñNxå)Õ):””­ x$HñMÙ&ÊJQ)ŞtL5å1””¸R#IûÒ5î›É(s=“}É\r0%%,y)JR>E\"I‰IKƒ¤’RS¤$…i?ãˆÔ&u	t$®O	p8Ñ.~ù„ BíîKI™ĞgT†°’”ºrœ2xM)NN¿”¹Ğ¤8I4kçİDL¤¦S‚AÒ ü“\'içÅ%/ÊSóÙ dëÏ2’™—ù/q‡å÷(ºQÂy‘rJVòd“;“—Ğ;(íÒÜ‘‘))!°§dŞ¡çº„Ç)H#À¤ªKêJmàùê†|“	ò¤ŠK ‰ğO ğPùRP%%S=AN wPŸÁ1>i)$Âo‰Qä%=»$¦DLA¢Š}ÚÌş))‘SóHø¦İíM?•²$sßÁ-ÚÄhŒêšuÉ)‘t›N|ÒJbe%(“	\0”HLAù¤¥ãD‡r˜ÏİÂZ¤¥4å8ï÷&æG	ÏÜRR¥#	ñO\Zr’•Ûò&í	ÁüJJYÚAìŸ¶‰$¥È4”£¯É9:|Ttá?&<RR¿:<’MÜ”çÅ%.ÓÂ`yL4Ÿ$óÌx$¥G!9‰ÁFJrxIJ\"|“˜ù¨Ÿö§ïğIJíŠDŸÒxNx1İ%+Àø§‘\"|;&\'îIK‘¤wî™Å\"|éÄ¤¥ˆRSÇÅD}é)RR%İüSøöÿ\0jŒëä’ŸÿÒ\r™ìÜCN ;ŸôUFõï÷IÓ·š¨çK§ÍD;²ë(<ßl[—e j@wÍÜ÷\\âwj‡2–²Šh$õ&	÷}%\rç‰á1<Ç)’M.e)å0Bv·C))S%.éÈî–’R% 5iOûSOŠJe#²iÓâ˜˜ø%?w‚JWÅ\"yLJGÀ$¥É˜Kæ£~	$¥ûAî—\ZÓ¬$RJòvÂZğšxO=û¤…Á„\0¦\"GÅ#ÇÁ%.<É)G~ÉxˆHJJQà%2c²o?ÆBJdxø¦óñMà%2Òe u$¨a?|ÒRñÉ L$8üSá%2óI0˜İÒâ’—ğ)L|µM\"è’”I‰î”ø¦1©vJRS)?ûÒ€’©“¼’×ğKw\0¤©!qÄ~)©ÿ\0Öç	)‹µ;L;¤L&î’™I?ÖR&½7r?rJ\\˜1÷¥\0h\04İÊJ\\qñ)Ïqà›ÃÈ¥:üxIJòKó€ìšu2uIJ$\r;zDù|$ƒãÊ^I)–‘æ5M0$ñâ˜h|“¤¥kÏ	øB`t>=’\"JJ^F²—oà›¸RøxÊ*^c_İ£Á)™§‚btIKèZj|”{\'””¡1¢Dÿ\0µ-F)h%~òN‰ú„»\"¦Dé§}SN©r%4¤¥\'ÍIÜyÂ‹†©ùRñ‚]¾	OŞ˜vIL¸oâ—ÚÂS I\nïªõ	Ş”é	%^)Ni“O	)‘îR?FOtÈx%ÏÀ&Ÿ’Rç|è˜p~0š{¤•øKˆM<\'Dx$…OÙ)ä¥ºJS%*düRM%9!%(şP—|S->\\|’Ú>(%x4ˆ}Éµ%!øöIOÿÓÁ1»É4x(™Ÿ”ó­yÚ_AªGÃæ˜‘ LOûĞS Bb<<Sk÷¥\"D$¥ôƒâ8H”Ò	)r|S&\Z§D’¸ˆJxLtH¤¥wHÄ€’iIKè”èSO)»BJe:&óK„É)xÖ|9OÚ<ì3’”|w„ış	´IK”äû~*2uOäRRıüyJ}ÒšN¾z$ªd8MÛÌ¤˜#Á%2JS\Z&4ê’©‘å#ô|ÓJ~tIJ$Bná)’™ğ„”¿÷¥Âa:¤{‘÷$¥Ì%àš`GtürJTø¥:„¾\Ztû’Rı¾=“é MÌ¤JH\\B~ÓÙDAR×lq	)sÏŠqôP5N%2Û F²š#åÊ[´„äÃÌ$…Œp:;È§Ğğ5ñMøÊJQÓOµ‚‘²˜ÿ\0½%/ )D¦Ÿ‚S¯‚J^>â–š}é{’@êdëÙ%.b\'ïMÌÎ°Õ±æ1ä’•Ï’ğûÒ“?Ç4”¯‰û’ƒ»ã¢`t)%p|Õ4—~tEK‰”İÓğJmAø ¥ü¿ÆRä´ûÒR’Hò>äÁ/İ#ÚR#XLã¯‚J\\HKø&HRRàÉ×ïH	4ÉN…%+Í9 j›ò&<¤¦R›ÂS;§0GÁ%/çÙ0HøJ\\|’R	»|\nC”r’—%\"IÅ0?íJRR¼’LyK²J^`%)ˆìœGÜ’T˜)$`üBäk	i	‰×ò¦””¾„§ï*$¥2PRú”æ&\n öğO&JT¤4ù&ïiÇtTÿ\0ÿÔç$÷JtLI×É/âºÇŸ¥I:¥:¦>)\ra%.tK^S8ê—\"Rç„ó£üS“ø$…qªC˜?š{$O	&—ş	r%4êAJL„•J$ü’=ÓLéæœ~’—\'_Å1î›¿àœw””©á8ü… {%:$¥O~Éæø(4JBJ^}ß‰KBuH&\Z”ËD<|Sv„Ã€’B~Ò˜İ:JY¦>)I2Rî<Ò=ÇnRJ¸˜J5Ÿ½\'~TŞ?zHd9”»›ŸšS¡IJ‘>IÉÔ&ß²GH))tÂu)N¿¿Ô¤¥|SOnÉÁ%.\'îI²”ëâ‘É)]ôJS¯À$8ü‰)p”˜Hp˜vILæuøJ`xLš¥))”H%<¡ï¢nh?(JÂ¨§Ü~IB¬ÙÇTæöŠÂ{&0FŸ˜êèì†,lsÏ	Ùk×‰„­Yóıé;Š@ÇıBvŸlèÚ˜ÌŸ$?4û`À¨„”¼‘Çdó!G^|Óé®©)CI	ÊBG3òIJ<ù¦>^)âH‘!$ÇŠJTÈ)hŠQªo.é)~á!Ù1‘¯‚\\\0RRæ;ÿ\0¬¦<‚F‚J^<üS¨èQRıÒ Ì¦ñ\nF#Å%(x¦¦S”’ ?Š@¦˜HS.É¹?Ò)!Nãà—dÅ9	%CBG”„vM¬¤…ùKºd‡)%A!Äø÷K±J4òIKIˆOÙ.Ä¦’’—íæ”öä”†šr–ˆ)]¼Ò?t¤OåEJä%Ü%Äy¦“2‚—Å.é´á%-Ü§ñL$¥ãæ’—íø¥ñKSÂZFº$§ÿÕç]SR=õî›M\në}4ÎÒ!>‘ğHj%%(ı šuH™	¤RRóÈHè%7ñO=’J´4†`¤¥w$%<&œ\rì’”L$Op‡äK‘ğIJS‰LRc€’?æÍGò§i%R”i)¼“¤¥ã„‡	vLŸ4®é’Ró¢ZIKYü‰‰$(A\Z¤u’˜Ì\'OÁ$¯Û^Éµá)‰	\nJ\\ğ‘ÔÎ\Zî–’#æ’”;%ŞHHÎä”®ÿ\0ıŠaÁJ		)CP—æ¤Ó\r…2Gt”É„Ä§”Ú)\Z’’—:kÜò˜µ0p#ïP7\r#ÃD,$RLà¢loÅ	Ö ğ†]<i¡@Ë²áéİw š¨u3ş¡BbŞ˜§ËºW†aÄS1ªbv¦.¦Dƒ¦¾I¤%&è}uI‚gÉ;à|ÔAşôÒI r’© s‡ÍM¹/aàp…©?ıü¼Q´:„íÉ÷	âLüK˜a øB¥\ZB“æ88ëÂBKLFál<¦‚¡‚\\	ñãÉI·\r¾2Šu¬á,Út„ã˜>\n;Û§‰¤ ™òåªâ<ÒB|ÔNœ%¿Ú|F©)˜Ôş%6ƒCª¸h|yQu¤‘Ø!i)£_\n#AğAõ]©û’õ	ó”¸“ÀR—¶yLllJtşT‘?zV»°,6öÏ(˜’\nV®\0œØ4<y\'$H3¡Ô*àëªy2V®àş)õÚJ]ì°ğxJĞbS‘‰Cõ\"Şˆ\\IÚ¥K¥³ãÂ–°B¥9)ˆÒBI)E)á.ê=á2M:§L<RS.Ú¦ír@è™.{¦ú)%Ù%,tiOÙ6‘ªI%yûÓN‰k—æüRRò˜×Å0:\'<	)G„´›¶¼¤’—#_‰gò$OŞ”˜””©˜HğÑ£Å8#ºHÿÖæçW&ü©Î’˜w]c€¡¨N4\0&N­òIK;ò¤„ñßÍ#©ü©)o4‰Ñ1Hğ’—”‰á4éóNH„”¯†¢O	ç²JTëğ	æT@Ò>IÆ‡à’©@¥ÛâRŸÅ\"$¥k%8ü‰·F‡îK´$¥ôÓÅ)™M:j—’J\\$İ>I‰ì€uî’—\'M;§ìL¤;$¦DÆ©LQBs$Ï—)\"•)õ‘<(É€Ó>i)}$•d‚—Á9„’¢t„£@éLyOù°’”;x)ÚOtĞ6èS\"C!;IQ!`h ò†,‰\'„- ‘©)nn2«›$Çb¢§T8—p&õ\n´•¡šLkÚ>ô,¯ A*$ÇDÛ â˜™h·(.ŸçBM\0•5îSI\rŸŠJ¥ûüv§î)ƒâ5H´‘>_‘%.é xèœ\r`ğ8Qv€¤×I×„•Ñiüï5 yóá4{G™ŸÁ9:I%~ä0B±>Ià\nHe<ü&ÂA<’ –‘à’)w:)çğ*#X\'“Ù?÷¤¥Úâ¼šîæ@û’ˆá%RV¸È>‘M¶¸@ğ@İ\0êœÏŠV´Å1y óªbOŠõ ~hÚ¸iŸ—\n$k»¥¸N‰ÉñÑ$)Ú”øpÔş)~pòIKrèI ‚G‚\\kØÿ\0ú><¢•µ:§Â`@Ğ$üRÚk!HnõMÈ#‰Nx1Çd”Ä>Iæ! \"Fáóá2~äâ`ê¢¹\0Ç’C HÒ~\n^¡Ú†šéôÚ<ÒQ+lö… àtAà\'Ğ;Ÿ‚V·…(KMÈ{È0§¼mùOùSHæa#çÊ6…pS›YNã¢HT&)Â]ÒJ	‘×B¢8))‚@á/»’RÚè¤ëŞSwÕ#æ’•:‰Hğ›Ïä’—’Ÿ¶¾	¦e „”©ñNïÄ¤GdÚ†€t=ÒSÿ×æN§âÑ1pİ¬§¯««pT?Ô¤4JtN;$¥‰ §x™=åDrŠ—)\rw2˜ğ\n\nd|”fS”€û’R’HÎ‘ªDB*_iÉ \'>i§T¹Ò<BE4ş©DxıôL§ñAJB`¢AøJ`$‘ø¤®ëF‰\'0“B*Z\n@p%6¤§o: ¥š5‚¥\"cÃø¤ H=Ó$ŸT¹Ç	´$yè¤DAãû”7((j¸i“àJ}í\r>!Õ|BD„€JrÑ¨òûŸi&G\n2{|Sx—wN-Hàé¢´Ì¹\ndü‚[¸BÊá\0ÌØIŸ»à£»Aó”ÓÈğÑ7ğIp‘\ZÁ?q\Zè˜“´¹GX‘ÊJ¦D’?¦Hğ\"TAĞk¢y÷yvI4§#È„†®ø¦.ĞÊDæà‚—Z;èÇR &à$¥4Ì‚”˜\'ñN>‘#Á7xì9IL^fÁíä“„ê;\'Ñû¤®Š”óáÙ)©\'ä’™-1ğHjš‹âŸóÇş¡RI<)ÅC‡ñROÁ%­AKB\Z¸ ” @\0y¥®Ğ£Ü§\'II4¹æ’\0„Ó&|ƒÄ¤¥4>IäÁòQ\"\ZGt§Û¢J¦[¤#b†“ğ	ÉI‰RA ‡üùRÜD­)A×âS~tš`ñ0tmY€$ÏdÚA<%2e0ğI€Ñ(íÙ3ŒJ{É)‘1§* ègÅ)$ÇŠ`xITÊa¿„èS;‰î”û‰*™h\'Á8 ’<5\n‰JH’©ZÀ>jNA5Õ0 èSÛÁ%$\"Z#âTxƒâ”˜$j\"b{p’Üvå ò9ã²f8™òLáî3ş²’«¡JKpˆòAhÚòœH\Zê­áÅ!|¤·æ‚`óü´p¦ÓÍçä£¿ñI®‰Ñ+ECS¾;&/“=’ñJÕEsÙ-Sodğœ¿Ú5ù%i¥%  À:J=Ñ´-oÅ #æ‘$@KtèR£¼¤dÁ„¼“’ $§ÿĞãw‘dŸO´{ qº~H {¤ù®˜ä˜ƒ¸m6Ö¹ÍÖ42÷Ãšß)Tƒ!M¶9¤I’á5‡fé:~P£Âv—I’¹¼ùèÄp’$ÂoŠˆµ¼“â–ñãæ„QeÁ„ã‰Q.”û„\0’‡	‰Ñ)J{$¥ûj£©Šy×ò¥ÂJd©&TÄ’’—\0x¤à„àm‰ÿ\0R“µp)#ª› ü‰4Æ¾)G=“N¡%.uÉâ‚c\0š`ƒ¤”É¢\'îQ5å3¬€P½IÚè…®%.àuLËŸã\'ñM\0‡î\0ŸÖÓÏÁ	Ï.w:Dxj¢P…®\'¹’Jh˜Ÿš`uòL\\eÔÈßzpyğP<yò:BJ¦ZÈø&î\'ºAÉ§Vù$ª_M<õLN§Í.ãæ™Ü”’JSÄ¦üRR£Hğ)İQ¤9ø$…È:ü¿†ŸÓ¨	Ï:v)%C™O¦á(ŸÅ%S\"I?‹†äÀêR)\"—ä‘Æ©&{ÿ\0Ò‘>áæ’—àALL~	æ]¯3¸‚J_’g]OÑ ©Ox	¸ş¼$¥LBZj›¸I‚’T	Ÿ’yä&o#à—ç‚JWñ)v„‡ÑÃ‘æ’—BrÍ1äG	!%/:|’oq4@€œ¤…u>Z\'Ón¼€”@„æ	-ìxEK˜=øM\0Ë”F„´ê¥1 ğI·1	šHi	Úa¿œxÂIğ\\¸éÔ÷!L€™$ëœ´G™Õº@òO&OÃD­YĞ¨ˆ€G2¥ ü“8h©y™Ha1Ã„¤H)*™8|SDw‰M<¥&gÉ$Ró§š}$ÍHH’™Çâ”ÌúpœH?RU.Éh#Å82c·	»JDÁ	!wÂr{(“ÇšB`”•KÌ€œt(É\rz§kıÒ’Š‹Á<Àçâ“ óİ3Ï1İ%2H”ÄÇÍ8×O.TDƒ©I\0/ù¤÷:§’	»‰û’\'_Å%(¸“\n[ÎŞ{è¢ã!D!%RAaƒ>:\'!èF¿zqÄ”@H^ŞcRuKxÜp‡\0x$Léâ£„?ÿÑâ3àa.ÒRw\'ã)‰Ô\0ºG1qÛÍ<è#•\0x²”ëø¢¢“ÊpD(wò\ZÂGPE$ïä–í\"„©~DC?PÁOêºG’t˜„óÛÁGì™¶Õ!yûĞ·i	LHì”p„âá\0œ\\\"OWHå#ôcÀ£ÄVğËliKÕ€ñ@Á=ÓnÖ|â*à\r“sdƒÙ#p1\n«’Söçò%ÄUí„Î¸ğ8ÕGÕ:	ãT1ÀTd~(YH€Kê\"x:&ŞcáÂ\'D“Â™æIO»Ç•Å;´)&”L–ïÇ„âIJİ˜ğHş]GÃ„”¸Ğ¦?Kâ—t‡İ$¯¡	‡)cÁ7”¸OÙDé	û‚JP2\nnÓâ”iäRpĞ$¥ÏdÒ‘(ÖRäwH}\"‘\Zh‘0Ô£Ì§î›ºb}É%sÄ%Êx‘ğQ\Z	IJºtš˜è>i)rtHÌd>%.Ó¤¤JoÇò¤†Nt„Äû‚_›ğLN¡$Ó\"eÒ”ˆM:¥$R»„Õ9ì˜L¤¥vHsğHÊ—r’T¹\"d‰L&gäŸó¡%/:¥®Š\'é|µ™I¦4ï)wŸ\rruñ”ĞLüRE.@Me9:ƒå	§Å<É„”££~s÷§Ğ|F©†¦SÌ€|4))fF¤$LüfS	kŠ~]#ä’•Ìù”‰:|ÒRçAÙ%+RwåJuñ\n ±Ù!¡òEÃ¤şTà\0Çã&Sï şT­ÈDüyLD8G&˜qSíÚ¶[‚u?‚F#à—šHXÈ˜~	Î x–ˆšIb\n}Ş)D\rÁ <RV‹ƒıÉ›o~ÉC9Ôx×âšHÓº^	*—òïÊs?woŠŒÇ*@ÏŞ’—˜pH‘¨Py×æ¥3ñ	\"™‰Q2R\'ÚL§li»””4Véw˜H	ä” ná\"b\nJPÑ¦©p³	çX<&íç:$¥¤ıéAìŸtœG\Z$§ÿÒâÉø¨Î¤ø$şOÅGQó]%¹ .¼ü@òLfOÃ”ú‘1Àå$²ŸwåHy¨kºO~a$S! ñJtÕDÈ€|Ó‚%%S):y§ñ*Ú|Á\'„‘LÇà§kä£:Ç€J¹S>>Iä\0†d:Û\Zü´¼	´™üi%ß$mHLN¾E.Á:¤¥vHgÇDİÇ’B	EJ2[)ø2˜#Á8#ø ¥‰û“;‰NtLuo’)TÈO¢P›]É)G;$Ş>)v)D”¼ê\'TÇŠZÈ))@êJI7¼¦	)wvN‰´™Iºh’—Ól÷Hù&2D‚iì’©s¡Ñ.çàœëÂiÔ¤¥Js¨…#DúBJW’grqİDƒ?’ƒ¢E7ñK’’o)\re3>‘HGd”¼êRLyIL¼pÒ9LRRÍú%>Ó\0üÓ3ˆR%=¾)Ó„”£Ø¤%#ÂcÇÅ%.\'²\\”Ü‡šJ\\&Ü’ˆæ{”Î5ÕHr¢|{¤ÓÄ$‹ÈÜœ NºwRíğID-!Iº;U)!Á%*DÇÅ8íø¨ë¸•&LüRQ_pù”€öüÒm)Ì÷à$†1.€@>IãÂS\0%%.áí´¦:kæSÈĞG	@#ñIJ™\0§Ç„Ğ\0#àŸN~A$2\rì9!\'yr9LÓ¤a;uÏ”¦“R4* I\'·e \0ßº6¢»Oo‚De0L|’& Ÿ¹/ËtáD™Å Ü›²J¦s¡Q ğv°œVÊp÷Ä¥©á;ˆ	¦Dø$¥İ¬ÇuÏu/ïP™#ò$ Ï™ññQ\'Ë”îĞ¤Í%,IÛ!;çB˜èŸnº$¦S\0”òI¢\0ÔéçAºHg>?’„|8NI\"\Z$…Ä{rŸy=ÆBœ“÷öITÿ\0ÿÓá^AŸ\"£BK‚]ŠèÜåÜ4Ñ3O´„ñÃ’’º)ÇFà€G’‹¸\"!%,şB\\~	DÁHèíRúÄy¦¶<“¨šfé=’S\"`óÊ`R@óR€Hòå.~inLm?ü‰É\"”×jŸw\"|ÔY İ8Ôİ%Ì:5ä\'D¨íÅ&´Ìtöå>£^Ê)nÒ|á+E2ä¦cÅ }ÓàDOtT $Áî”vğOÂnß’L¦)A.ĞŠUâí	pd&3 ñIK€Hä%0|Ó\nP¦äü“Î‰›İ(§QqÑ?(%ru	¼|’î>(©~Ò—Ÿt“Gâ’L”ç²gt$’{/:¦äê‘å<jRB!‘á7šJ\\å7t‡2’™!GÍ9ñL8Ñ%ÂSªaÂpRšS“îÕ3e#ô’WUwI7ç/ÍâRRÜÇ@~ß$öı=<RRÇ’˜iÊGRS™I+¦Ğ\'&\'„¹ïğMà˜\nS¨IK“î¥<¨¤»RQ|ÓTeK’BJ\\~i‡Ò	¤¦á%2²Sıé‚o$ÈŸ‚Pwã”Õ(#_$”£<)IpŞS\r¿8NÑ¬Ïò¤…Ûã¢GB@MpŠ‘ˆ.ïà’–pöˆì#æ&IK]²x”Âx?RS.<Ç	Ë¡Ê KOp\n‘ÒáÙ$.\"~Š™-v‡YÕ\n} ‘óNOãÊ(¦[\0jDy)	îœ’uIVXcT§^éÈä‘ñQÕ²qÓ·€ ¥$G’J¦M:\'hƒ!Eœ¦’L•[²wâ‘lAæSrJwX<We?Vè›]ŠGP!)Óà’•:$‰ƒ!0Öc„çR<ÒS\'Ü›o=:pŠJ¡I4\Z¨îQÂbIi\n0Hø$€ÿÔàÆ„§¦‰;éŸ2š`.‰ÏdOäLİcñM\"oŠ(T§‡	Üujç.ÿ\0’¡Â‹»y©´èBg	#²]z¯ÇÀ&n³¤xJ³ßºJè¼è–³¿uOœ©\r ø$¥ßô*CˆòK¾œ\rR˜¡y¤4ùÉM:BruIK5ŞÙNt‚i¦ˆITË°ŸÄ€?*nI	Æ¾Ô LüS—kğP]{$O¹$ÒBã¬wH?†øè‡&Å!ôóJÑÂï‘%FuòC˜ÓğSià Æ™iÂ\\{F x”àğQBî¯Ü—æOÍ\"AoÁ }º$…‰\ZøÂCò¤D¤ƒîI*Ñ4µ!¤‚œ˜$$•»„Äò¥ü?8ÊJİüL‘Õ4ê<’U21<&ïä‘:§IGÅ?Å#©IÜJIXŸÈÙNa4ûRR“·…ÑI¾’Š»ş)4h˜Êp`\"‹58å.óâ™$©©ÿ\090Ñ-w”Èó)~jbtOØy¤…»\'êâ{J÷§&A))ˆÔ§<&\0üÔ›é$±ìŸ˜òMIJìRà„şI»‚’•ùÒ¥üTByÖBˆîàıÉJ\\ë))qôS@ğNxwà”{@ñIK4òT‰ì;(´è’™˜¸µ4ñ)Î®Ú’)r>ˆ2œù(ñ¯‚ïIJ8JIÄ¦\'Àóß„”ÉĞ+/Å;Ìøh†LÄ§k¹óIU¢F\r›§YLci×CüS8è\nnZïÁ$WVoi#ÛÀ‘&	x	šfH0[#ÄHI)v˜|İÔƒ„üyP$–“ŞyJ ÷•A(vuM\0ëÙ@<ÀOØÁE+fŠ$hˆ	tvL[<$ {°­1™òìŸè“á);ıÈ®ê¿tÓ-„ßœBwD’–4HyN©ˆƒ¡%/ù§Á g¿	˜èãæ’™$ÊSÅD˜Ri˜””¿ŒSpG`›ƒş¼$Ä ŠÿÕàßôÂo¤è•]¸ÕHø(´ûS´¤¢­7ÿ\0ÅG¾œ\'à$¥\rääGó‰R?D˜EEgp“N‰ íâWEN¿4óùRîO™Kó¢…Éöºviw3Á	o”¢uO2e.Äé†©%–‡é8İ†Š&dy¤xIÌ\'Å0v©‡Ñù&<Ï’J¥Ï*F$(º)\re%¤D\'9Q\ZüÒBJ¦_EÄ÷O§á¢‰\'‘İHr<‘AY§QóN	\Z&ÛÉî8Oßä’‹)ö„ÁÚBˆ˜øMÚ~Ú),ø&>!D>IH#¸JÑL)È„àÉ%7„$ É¼4Ğ9<”>I¤\"¥:H—:ø)3Ãæ™Ú’¼üä†²—&÷òI+øÁ7d§T¿ğH¼S¨IK>jC”ÀJM™)$®î\0\'ìTJH§D	;HLt%2¹G¹R(uIA“¸”§@›Ro $¥ûş)MÁO=‘BÀèS´ò™¼$ŞPOu‰Õ\"R?JŠQ.!%.8”’2Á$” ãğNÑ§šMúgÌ\"®êıÉ~l÷)%2ØğI\nŸÍìSG’b Å\'Râ“5Õ#¨I‡”•Ñná9æ~ôäxrœ	D¤¦;IÚRJ˜Êİ%Z»Ÿ²fë÷¥ÈIJ<€œ)‰ü‰NŸ”È™Ÿ9H	¤t	‡‡š“¸°l>¼TÁß¡ø(¸ˆrÔ{¼JHİF[-ù¥©†ø”ˆş]Ó‘NgD”¢6¸|êĞ\n&	i)\'N))™$7”ó¦ˆcvÓä¥ı>)\"—k»sÊb4O*,:üSê5\ZÊJªYÓ3â“¤·åÂ&…ºO*$IùB*¿Á€Ò;§ì<ÓñÅ1í\Z„’¦TŒÄ¨´jÆ\nJê³†’ä\'å¥1’\'Á%+€R\ZÅ;[ÌòxLDIOÿÖàÏ$¦2O‰Høx®‰Ïb>)Ú$ÏŞœˆ‘ä¢É‘	\'£\'ÈPšTåâ›nyIP7|Bwi	»„úmù$¥‡!0Ñå ‡Å1>éILÏ:&Hø§iLR)î	$Ògà‚™¬$†SS¸şDP£Øù¤ï£)O¶äm	)fêÒ¥ŞTX \')(î£2G†©Áïà™±\'Í>›RQXGâuT¢RQXvNgpé€á-&RS)ãà˜èRéHó/ä”é)	LLµ%/:”‡Ò%Gˆó	ÛôŠJdÓ¢mĞ™§_ÆN‰Z©”ë>)N¿Ç‚r=Üø$ª\\:ÚÉQwr“Oâ•¢º³:&ì”ëà“uQR‡ÑMüSˆˆîâŠ—\Z‚£~JcºŠHXRf†e(‰|’Jó©PÖB—tÂxIAww¹	¦©)yIİ“®‰Äê’Ş7ø)0\n} 0Îî’­‰:¥:„‡Á1ì’—E&$¤x\0(S\"ÊMäŸ¹NNšhRÇQñHˆãÊm{j¤ï£ÊJXá&êgÀ&oØ©²?½%“â¢4éüBŒêR@\\† ş	:#O«%$ôµ¢MÒS´ë\n#’’»²ş)ıÉ“ğP u‚‘ÓNRĞ™	œRRà5ñO	¦R””¨ñLĞS‚ ¤¥§Tú=ÂbwÄ&|õIJ·}¢H.»$5!$è¼˜R.<Txú\'È¤†N ÅI±¶\'•«¼“‚78I2l‚Aïİ3ZHº[µø”Ç‘æII¹wbˆİºÇÅæL§k£NRQ\Z$€Ó)hN½”Aäø%¸áä’)—´è~E3šfG\n#Àkâ¦Ò~	+e3òPw?)‘Ó@İäŠBÄÆ‰Út½\'„ÃOà’z2_%‘?$œuHÄ$ªÿ×à‰N{Çøê¤á-¢sÊ¹Q`#RŸ|úé))gwO©ù%·t¤%:¤¥8è<»¥&8È”òtIJiĞJ‰Ö\'Á;{&	%:y¦á&$D8|BüÂç¥2¥	)S¯Å9á3¹Ñ\'jJì¯’qÉQN\'T”»yÕ\"xHò›C%.ŞT¿7æ¢4t\'c„BÔÈüŠ29RÖšJ*>	h\'Ï„§Ú¢O¸$¦@íú>Pš$;	L?”¶³L˜IßH>j.í•½,ã¨N43â¢upø©“)(¬ß¤S…ÈrŸæ¤¢Ç²”ëòQş*Dvå%Ñ?ÀÀ“´iø¤%%te!;O¸¨;Cæ§¤Åø¬L¸ÂwhÕ`üT«<ÒWeÊDÿ\0z‹Oº\nsşÄ•Kî n…Ï*c™òHLŒLÚ¨¬§~)FˆÚÕ\r¦à cá6œ\"¥£)Áö¤Ñ¬öğH÷	)pï5\"FĞ\0\'Ôh’©gp	Õ#ÛïIÜÀsä’C04Ÿ¢Ÿ’c©ù¤€ œÜhœhİRRí€Jgè4û’\0L¤u×Á%uPú0RiH¦™IKşwÇ”Æ{x§Ò”»¾â“œr˜ø) $®‹\rAjDkçÙ7t•k¸pGm\ZørœÃ„%-Ú{&#@|Ó™\0y¤@!%+‚£B“¹\Zy$8Ig)ÁÖR\ZOÁ!ùRI[¿É?æÊh<§ì’•Y†¦Ş“~„\'¯ŠHêTN©\Zxê£É™ù);‚JSF’“Nº§í\n3©I]×àÏšFtñ™‘ä”®>§1\"‘îğ)œ„x$¥Ã¹Kpå&˜ù¥¦¨©“[¡3òN	Ö4ò	šf|”H Hî’)™tR3\Zw…\0îÜOtä j’)N‰‰×ºg:‚r7D\rB™d¤‹¤NúRÔù\'4 üTZ™E èÿ\0ÿĞà§İä§·šg7R@ã²MÔO‚èšm7Êr`|Rp’áÊD$¥4ñÙ/Îør˜â))iĞxœja |«’A[(Hğœ>I8HÓ·d•Ù‹F’xS0H*2xá9ü‰)`œòš\"ÿ\0‚J*sx):šD¥İ%*\0)x&üäÙ%3˜”ó |RRÄû’\'s~ôÎÒ>	)iSÏ‚„p¦ß JAE_ÄÉ#ÉK–‚Š™4èHÔ¨ğRoÑ2”ø”»‰!\'dÇt™î#Á.ªè¨=û§ €;¦?KÁJtIL;éİLıÇB<’‚@¹„”V<\'Å\'\r@ù$4ºJèµš4wI€hÅ3õ\0sªŸç%Õ]K§ÁOI…Ş8	¤<ÂJdkãÙ?#EŠpe,>‘òO:JgÎéñHj’”@‘Ô‰ò(@í*P`ŠJbÒœ\'É@i>|\'ŞH$†›>!&L„§ÚÑä¢ÀŠ;³ŸrS\"<T\\Ït›¬JVŠdOd‰’ŠaîŸÊšÇ’6¥:a!ßïO”{ êtQj\'D„qà—æÇÜ‘&5çºJcÜ÷S\ruî˜JJ\\>I†©ŒàèJÄé)ÈŸ€QwaF!(!.\0Nu¡nHOùÉ€;£Å.İ%/$Hó)Òù\'lIT£\'„ˆì~	4Ç}RîRî\0ğI½Ò$Ì”Ñ¬WEä¨´ÊfŸqN>”ö””¹›¸S:Ç‚ú_zJˆL\'2FŠ™)(2ìHÎÔ‡))Màø”Ó)ø”Â`øò’—<¦ZOtü˜	7è¤¥\0`Û•/ÍÁD{G*_›¢H,1ñRÖjÁ\0ÊLú)$¯>éNAĞy&äŸ$äğRB„\r9î¤`ê;ê‡Ü}ÊmH:é¢H+8¨íÊrté\0I1Ç\'~ñIJ$’7k®¿Ş„ù‘¯!6¿IUiA@“¦#ºIïñ„IûğŠ§ÿÑá\\‚uŞu²r`˜ñH™$x®‰ÏYÑ¶B`LÂpG	¬÷á®@\"GÉDJq¯m)67ŠJî©Nº9Qà—ç$ªHví;yQ\Z<ï(¡‹¾ŸÅHƒ\'ò¨¤I\'¢ÇîHö):$&ÁI€GdÇ”§@“â~))CÅ>„4Ã@¤ØIE‹ÄŠCè§\'pò™Ğ„¤¦\\4Ï€G‚CPRiíÂJê®[*L\0ÖT5J½Q;}XğSke³Ç’‡o4BFİ4•¼T¤Ou#ôgÅ t’‚ŸôcÁ3&5î¤ıZ*zrUtdD™àwJ}¿4œOİÂiöÿ\0rHYİˆRk´Pî€$¢¢ït¤yøò˜|¥/ç	)“wî£ùÇÉ)ûÒ˜qòI]×f¤ÏÜ™üİL\rtuP°joº(n§Îà”¦³‘ğRlÀ3¬¤®Š{¸üo\Z}é¬:Çb¤€p—UtYÚ‘à¤*?‡(„¨*#$óQ×pA!#–GtŒú¥3ÀÊgüJ(\\L$	™¦¦#²yÜ’—ŠiàJÔy¨H©!%3aÒJ‰v©4@HèA•¥³ˆÕ?*§ø§$j­¥ü“A™„Í:ûˆ1å)Zi[wwÕ\'Ã”ì&%(0QBŠƒ†¡I(Ô4Sà\rÇp$x\'ŠÃyIäLø¨ë11	ÈøüUj£\0“tşäı“×Å%/gºG”£HM<~D”¢xğRiM¶Dwå&’QÙ@{Œ$„„¦Jy?Ü’˜Ï	çİğIÀ{HJ8IJa3‡»OÓHéªJdÎç•éÁH”•ÕA.Bˆ2İí’’©#?/u=Ä<Ó´ÀóQ™qì’‡Uv#æœ;@B`uKPc³RS&H\0’˜}È“O) kÙ%2g‰HÈø(ÌäÈ>I*µTOŞJCP–›¤ø$…4àÇ˜ˆĞ0ÔkÙ%x®ãô|Œ\'\ZT[3ùw„•]¬ùÄ):Ê‹dòæ8!$?ÿÒà_¬üSæ¤cq‰Lğ‰ñ¡hx,ï¥)Á2ç™LĞwBJdÓÙ0ĞŸàAâ<;ÇÅ%u\\\ru?ïLgzpcNR|~	+ªšJfò¤Ù\0Ït”t`D¤xóLyNHRWeù\"4LÎ\nAÚ%ô~h£ÁQ¤$9I¼~	æ;¤–@Lè[ôˆNÒ‘ĞŸRB”4•-	˜Ÿœ=ºê’»,Óİ9ƒİFtRhİ¬üI\\‰û@ü©‹„ÛÆRàùZ¶ >¦[	Ñ		„T±’	ñªa$ˆí(%‘ñQ\0ñSA\'Ÿü	E<ıémˆ<§q“§‚S$w#””Äó)öRp@ä\'¥%[Àò¢>’–°‚J†¢RäüS´¥·İ<ÏåEÕ0@ü:	zxøÂcô‚J]À<y¤9uLáØöNÁ¤øvKªº,÷i\'ä<\Z§²GÍE¿ÉuWFnÔ|8Lß	HT‰ğI,dT\\ƒà¤àGŠOo‡ÊŠ‚št“İ\"4O Gà™ÇXñK¢”4í÷(eÚ5 =º÷å%Z„@K3¢Pcà£¿„•VÈ“\0ÂD×¹JH‰üSÂJXL\'™0›JM\ZLëæ’”?\"w\\?G™NIûĞK&¯dŸåİGrDè†¨¢µ¶CŸŠs(°jJN:ıÉZ+VGÉ(IFFªM=‘J¸ù¤L¡>\nNí§d•Õx1ñL`AùB~DöQçDT7¿’Œuù)FºwLRC!T&e }©sÁ$¬¶û9Ô&3¢}SIwpß$ı‚ˆÔ)TN©+£kªCé%§	pJIP™€‘“ )k¸”æILH-â¥ºsİ6¤„æ\0ş)(•x¨´jOb“Û ƒ*SÛÁ%tÑ‹5R<Ÿ4Ìà%;Ÿ–AİiÕ\'!¨Hs¯	%C€8HiÕ;†švH”³H„‰‚>GTí\0êD„”¼Äy¤4€;&#PXIëÉø„ÏçwbÆ\0û’:€%.ĞgÈ%Ş|GäK]Ä}ÉšïâSÿÓàsİ<îg	ŒÉ„Ë¡h)¿FRÅ&ˆ£ÜÍ%w_v„$ˆ*.\ZŸ$íÒR»Àj“ø\nCB\'ÁDºRPİM©\Zèˆà6ˆĞ¨8¸$ó¡ó ëEB>åù9NAQ\'æ‚Bà@”î£É0úâœ<’WUì”Üû{”àè¢5=ÑBàOšgH&8	I#ò$%)¦I)äÇ*-âÒ‰)`ÙT´;&\">	7S$®à#Æ²\0t*m#ˆ×²H#EÈxvQç\'€œrA	›¨Eî;¼Ó0‘>i€å;fš	\\ÌÁÑ4ÀññO¶I×²ãİ%@™Ÿ¹)÷L&×Hù&D¤ªf	M¤˜:©1­ “¢Œm<êQGu‹ éóNdğ—qùŞi%iàè‘—){ü’q’š¤…„é„òNt)€#^İŠI]ı¼SƒîN[íº‡çBH\Z†oüÔ8‘Ü•)ãÁEÂL¤¡Ù—”|Òˆ13<¤FšÃéë))—˜H“•4Ç÷¤‚’)pJgNà{\'“ã÷¤5ĞıÉ)i5ÓYRtµŠ0´\rOf£æŠº…ÛïCN\ZT]ô‡ÜHêÈ\'Ã²oÙ?:q	Ì¯”•³é5ÜşDİÒğ8I,»É¦x)ÌÏğHêĞŠ;1™NtÍ9d\0F¢OÑALãkLéÚL\'\Z9O`Ğ»ïEXşlöNÏ¢Jhö|S¶BJ;$1¡\0y¨’÷&İ#â¢çd­\0$åª\r)Õ;\'ø\'t’{J*ÛE5Å9ÖF¡FuÅ>Ó&Ş’”`7ò¤ÈÚ‘óM_ªè©öéªCˆ)×MRi)puNÒ˜ğ\ZkÂJ+©NyÄk-ù„§T”ÉÄOÅ0‰IÚ°Ø§G’Hè°å9ñğLŞ\noÍóI,„m”¹Ñ0>İRiÕ$Vë¸êšt>iŸÊZíI5¢á°Ñ	àÇäL~¥&”È¨ø\'\Z‚a!à’”yÅ8ğQäü5Ju‘¦‰*•©\'É!Ì\'Â]ÒR7‚q¯	‰íæ“t%%te:€~	†ƒÈİÇ‘”í3Ï	!ÿÔàH÷|ÔgXYI.…¢ìsò)K„}ò±ÒI»†à`jÒBI+£¬ã§ÁJß?’J.Ã´yÄû|BI)ÙD•ĞA=Æ«!$Tˆ!±Ş*hÜ!d$’µvvÀÕ3yXé$îÌÈ„Œ@=–2I)×n)Ço%’I.Ñ>Ş*üiªÈI#¸@Ø»;@ÓÎSX©\"‡i¦]ã)VÓ~b¤’ZvGwL\0\rV:I%Úi‰Õ\"D‘ØêV*I#«´Øä¤îp*Ih­uvÉƒ§ºpŸ°ÒEÏtÎĞ’i\\¶‚S¼M\ZHô[ÕÛpÉÁÓà°ÒEG`î¿¿’çÊÆI˜ìíö˜îšCŒ;²ÅI%;d·HãñH{JÄI;G¼¤ŞšÅItv¿3U!¡ˆXi$§lÈÛğIÆYªÄIvvÀöiñLæğF¥b¤‚º»|\rJ}6øÊÃI;mh\'ÁB5ïğXé W²ìóª‘ˆ×HX‰$‚î»†ÂŒ9c$‰T]Ö¹\'q_„’]õvŞî\n[eºr°’I=;„{|Ô\nÆI‡n¿÷©Y¯	$z üÎãÁ?ê$wåsé\"-İÇGäHAş+\r$º§£¼5Ñ1HXI\"‡wY”»JÂI%;Úüuİ‚ÃI% ‰€™Â@üI!Şˆ\n#8Xi$ï£ñ)š`˜XI$®îóüR`öÄj°RI]â=“	7Qæ°RI]>®ûut•	¹a¤’‡W}‡Ş¢İ|–I+»½ÃˆğîŸ’\nÀI$;¤C´îTÎ‹ŸI%ëŒGš“JÀI$ôÿÙ','jmi'),('jmi','222','kedar','','kumar','adm','','aligrah','UP',NULL,'India','','',NULL,'','kedar9002@gmail.com','','53463463','',NULL,'ÿØÿà\0JFIF\0\0`\0`\0\0ÿí\rPhotoshop 3.0\08BIMí\nResolution\0\0\0\0\0`\0\0\0\0\0`\0\0\0\08BIM\rFX Global Lighting Angle\0\0\0\0\0\0\0x8BIMFX Global Altitude\0\0\0\0\0\0\08BIMóPrint Flags\0\0\0	\0\0\0\0\0\0\0\0\08BIM\nCopyright Flag\0\0\0\0\0\08BIM\'Japanese Print Flags\0\0\0\0\n\0\0\0\0\0\0\0\08BIMõColor Halftone Settings\0\0\0H\0/ff\0\0lff\0\0\0\0\0\0\0/ff\0\0¡™š\0\0\0\0\0\0\02\0\0\0\0Z\0\0\0\0\0\0\0\0\05\0\0\0\0-\0\0\0\0\0\0\0\08BIMøColor Transfer Settings\0\0\0p\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\08BIMGuides\0\0\0\0\0\0\0\0\0@\0\0@\0\0\0\08BIM\rURL overrides\0\0\0\0\0\0\08BIM\ZSlices\0\0\0\0u\0\0\0\0\0\0\0\0\0\0\0\0\0X\0\0 \0\0\0\n\0U\0n\0t\0i\0t\0l\0e\0d\0-\02\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0X\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\08BIMICC Untagged Flag\0\0\0\08BIMLayer ID Generator Base\0\0\0\0\0\08BIMNew Windows Thumbnail\0\0	o\0\0\0\0\0\0p\0\0\0T\0\0P\0\0n@\0\0	S\0\0ÿØÿà\0JFIF\0\0H\0H\0\0ÿî\0Adobe\0d€\0\0\0ÿÛ\0„\0			\n\r\r\rÿÀ\0\0T\0p\"\0ÿİ\0\0ÿÄ?\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0	\n\03\0!1AQa\"q2‘¡±B#$RÁb34r‚ÑC%’Sğáñcs5¢²ƒ&D“TdEÂ£t6ÒUâeò³„ÃÓuãóF\'”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö7GWgw‡—§·Ç×ç÷\05\0!1AQaq\"2‘¡±B#ÁRÑğ3$bár‚’CScs4ñ%¢²ƒ&5ÂÒD“T£dEU6teâò³„ÃÓuãóF”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö\'7GWgw‡—§·ÇÿÚ\0\0\0?\0ÀejÍl…1áû\\Ò<Ïcü ¬1 +B`»±ÅEµ‹¨…pW¢¬Ê¬¥Ä9¤m;Iä¶îú;¶«õûÚù¨å.£fŞ1¦½5’ß‚F­QšÈ1â¥µ%ô\ZŞ–ªF½¶êœµ%Ph¾­S\n¼•¢ÉH°\0—\Z8´mlh[Z¿cUK[)ÂLY\"çØÉU¬bĞ±Š³ëNâjÏÿĞ£•]ôeì%®tz€ÏÑßÏıí!¥¼I#h<Î¿É[—|ª9X,{\\Öé¼÷¨±æ5R/XqloÛûZÙ_XNNSK€m6ëY¬1ğk/ª>Ÿ³gÓZ™—½¬xxc¶»i‘>_ÉşZæòq[[ºÀƒÁúS*½O²«eN,{xpì¦\rG@ÁÇ<f¥o·èÏwàB+j6:\Z5\"uÓò¬Î““‘~ºñ>èDü˜şNÕ¥MÀ\ruÚ{ø¹½tÑ’BUa‹˜Zu™Â=¢ÃÓĞi øÇ¸5¡è†Ã±‘!˜uTeu»µBÏIÚ5ĞÅV¶ÎÉŞå²DQØ«XÜÿ\0™B~©âi”-¨öªö5[³ÉV²tá6¼ñ¿ÿÑ5V‚Á¿éw‰£¼åg]\"\0#¼˜?ÙOëå‚}?N;nÜı˜&{½´±ÂlìäĞk<´TŸ¢ãû®şRæì¦Ìk¶\\Ò×gI-]-y-ı İö’Gâu\rË Öãî\Z°ø,9Gc»°ÙPØ¹Tçeb·mW~‰¤¹­\"DŸ¤6Ÿúk_§u7ä_[ÇèèĞ™ù»jçòin=¾$¹£ŞL\r‘˜­ô–ºÌ–1²eÃAÏ?KE.I!Úít1êA\ZQñázwfQSl`ı5Áå…‡³bw~öıŞÔLçØÒákk2è°\rDz®ŸwùŠ#§·íç¶Dlk½äî}–úl·è;ÛR}U^,k›uìu[šøG9Ìk?7ş1Q÷Üù°V)z@&ëŠ7ø?ËlÜ’ÌG9 ¾íà0È‚÷~gõ²fMv¹×ëS8F ÿ\0%ÑúM¿å²³meÛ\"AxÚG}ÃnßÒ¿ş¡Vı¹”1ÅD6×’FÒ$@ıæ~rµŒÈì×»?·í|İŒëEM³sİ%°üÖÊg\\_Æ¾Af`Ñ[Ûë^Òç è;í=Êûo­¼$¦\"hY¤Â¹\no/Ë^yû”Ï—šwäµW³(x 2H¤ãˆİÿÒÃfq£79½×WQË¬@ypğ¹u|±û‡Ê?ÚªTø=D>=Ë8£0{pƒÿ\0tö\rÍaîˆ2š{®=½o rÆ†áüQ^°‚çîL<¤º3Gã<Şf?àOşæ/Xl©ä5®#‚@%›\ZÓ,¤é \0Æ¿ëYS\ZÏ3.?÷Ö »Õ‡$eŸùä²‘¼G™ÿ\0½cËñ¾His\'ŒcQÿ\0Ç%ôœgİÒÒDŠVE€¸‡¹ g@Àk?5yÿ\0üãëÀC:…õÿ\0Å¿gşzÚ“>±u€A³%×ù·CçûZYÿ\0M3ıšïŠüoûÖ¬>1Ëû—,rıê‰—øQz«\\º^Hvâu×İÏõ?²ªMaá›k.Ğ>Kü¯¥íjÂwÖÜ}õ0\0Hşô:¦òkñ%ßtmV1òÙ’-éüg‘«ŒŒ¤:Î\'şƒ½fcíİ¸\r¨Nê-ì?ŠÄÎ<´#ûÓ}³ùŠ8\0İ©“ãQ?!é/û×]ÙÒ‚ì©Y§/ù?Šc•=‚xÄZ2ı/ÁÿÓâÓ°ŒMgüò¥û7ıÿ\0<¤}c¢pğuİ\'‚8ü°«ú¿x½/şcÖ0Qéİ<	ØÎ*Àéã†Ÿó÷©—ƒ  ÊŒûÑ_¼~ÔÈrãn_ÿ\0?÷ªûá‡üâÒúu\r{å”0Q¨°\r{ÇÄÿ\0e		pš”¾ÕøaË‹ÁŠ¼ağè`Ögş1ßù%ô>ˆÙö6;ÿ\0$]Ñ¹Ût1Ìÿ\0ÕnP½ÇÓ%ä5£MºDø?kU@rqW»?ñ›ç”å\0\'îø¶¿æáø¹ötÎ˜Òè¬éÇ¼ªÿ\0`ÂıÃşqVìvİqğUË»e\\…×ÌOÕÏæ!ËƒC(ùB(†îóŠG~iÿ\08©î\n%ÑØÂ“^å¥(âä±ÿ\0ŠÄáâ~éÿ\08¨L_İ?çBíyÓ¿‚<şâˆ¾åŠ~×ù¸ ÿÔáÆí£ø±>°gåÏı%‚’ßéôw»i^\n¿ŠÄI‰íÓo£¶ß£íGÇãtñ¬Lyğ¹Ô’ÇùÜ¿Ïå‰ê©íô·nÓ1§ÒüıŠOß.Û¿|éÏı?Í\\šJßËüoêºCcôÛm¿Éÿ\0]èî™Òx1*îõ7ûb?9b$¬Cg3šùÍ¿è»~éÓéóË$æ©Øÿ\0/±Ø3\Z(ï%”’,RÿÙ\08BIM!\ZVersion compatibility info\0\0\0\0U\0\0\0\0\0\0\0A\0d\0o\0b\0e\0 \0P\0h\0o\0t\0o\0s\0h\0o\0p\0\0\0\0A\0d\0o\0b\0e\0 \0P\0h\0o\0t\0o\0s\0h\0o\0p\0 \06\0.\00\0\0\0\08BIMJPEG Quality\0\0\0\0\0\0\0\0\0ÿî\0Adobe\0d\0\0\0\0ÿÛ\0„\0\n\n\n\n\n\r\n\n\r\"\"ÿÀ\0X \0ÿİ\0\0dÿÄ¢\0\0\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0	\n\0s\0!1AQa\"q2‘¡±B#ÁRÑá3bğ$r‚ñ%C4S’¢²csÂ5D\'“£³6TdtÃÒâ&ƒ	\n„”EF¤´VÓU(\ZòãóÄÔäôeu…•¥µÅÕåõfv†–¦¶ÆÖæö7GWgw‡—§·Ç×ç÷8HXhxˆ˜¨¸ÈØèø)9IYiy‰™©¹ÉÙéù*:JZjzŠšªºÊÚêú\0m\0!1AQa\"q‘2¡±ğÁÑá#BRbrñ3$4C‚’S%¢c²ÂsÒ5âDƒT“	\n&6E\Z\'dtU7ò£³Ã()Óãó„”¤´ÄÔäôeu…•¥µÅÕåõFVfv†–¦¶ÆÖæöGWgw‡—§·Ç×ç÷8HXhxˆ˜¨¸ÈØèø9IYiy‰™©¹ÉÙéù*:JZjzŠšªºÊÚêúÿÚ\0\0\0?\0‰|ó:İ»t?Ç’·ÓWtß¶l/zB¶<qdº©ZØ¥º»o¶;*á^§çˆKj(OlUx‚z×ïÅ!ºmÓ¦®S]©şg\nB ëZş>8/½ºÈ*)ıMpjª7 F…TöÀÌ/âH¯sßå¶\nKj;½NøU²{xí€¥ÛõéøĞâ«€?wlJ¶ÿ\0kçŠ\\Fİp%º{S¾CT¨­7N#É]N‚µ\'l6® ÛÓ…ÃsCßb02^*(+_Û]NƒîÅZ\"£è©öÉ!ÛlGaşuÀ®\0t¦Ç¡ŞmÔøaİ\\AP·q×ïÄq\0Ö£jï…\rúÿ\0™ùâ‡Å:W©À–ÀØ\n}¯ôÃDì­ ×·ß^ØòKˆ»]6ü1VŠoC·c‰BÒ†ı¼0¢›¥zïøâ½È¥w÷À–ı»ñK¨\rwùŒnÔÃj\nïïÖ˜9¹vé×îÀ–Â€vè{\Zãi§P†´;chXTû×éÃº—{\rÈÄ˜´h6ïŠµÄĞíï÷ûaC_‡B¬;øüÿ\0X­dƒóØa£€¥()ãÛ\nº€\nòÅiÔ#¯AÜãÍ\rqøÓş#…\roSò¦6¤66#¯cÉoˆ´§w\Z\n\r«±ğ&›Û©:xVœ¤Ãj\n·€¥ÀšTôëLwCtéO\Zšë…-q~ıû`%i£Z\nvè:m‡š­¦Ô¸´ò¡ñ?Åknõÿ\0?%JİÆß«³áş\'$‚Õ\0¥ÿ\0Ã\0*Z Ÿóß‚´Ç®*ÕHØ·lB–‹x\rÏ\ZASmşş˜9±%aı]p¡£^•wl%ŠÓíş~,Vµ*+ş{â¶›xchZ{øx\\7²\nÖøPâP×ÑŠÓ_Ã–ˆü;|ğ+TëãŠ)i¯ùÿ\0T´ÕíÔwÂX´}‰ùb‡ÿĞŠPı?gS¸u\r6ÿ\0?§®~õñùâ­¿·LUÔÂ•ëNİ¶)pæ0*ñ·Ñß$—ßñüi€*àŸ\\JWÓn½)üp%wm¿Ìb•Ãç…WÈßä;bÍx=ÇCßßÇ\0H^+JPî0Ùª¨§ZW°ÀÉU~ÿ\0Ç#LÕıw¦6È.zÒ¿×\"\'}ÇzøaBá¿)p“Úµ-W\0:œ!-Ó­÷=ØÔüE>ü—@_~•Âí©A¹1%iÀ\nÒzaVéìOËîÁ{¦—¨ëµ}©€¥º\n\Zõ8¥ªoJw®Şø¡ÔoSòïˆÙZ§ˆùáWPS®ãÇøá´4EÀ®>a†ÊwqÓÛçi®ƒ¯jSÇ	\nà+Óû0¡±B+Ó·ã-ĞšB»Û úp%İÃ§Jd¬1¥Ş±VÀ¿FlPn›íÛ\"–öïßjcJêv­Pq>il\rº·4¸\rëZûâ—S¾ØÚ´Ep*Ò:_¿$‡R§~¿¦6†ˆ¯‡Â­ôù»\Z#­vùâ«¦İ)…ŠÒi½>_,ób]ş~=Ğùmşv<•Ôìz{â­mò¯‡¶E47=(|GğÆÖ›§~Ãüú`ä´İ(6ëÛûq	¦Àß¦ş8[§zS°÷À® ÷>ØU®›éÔöÁÍZ ^¤¥²(zn|pªÚu§Ê½öÅZ5#§İ¾+\Z´bUev¯êğ8y1-S¨§ßHéíĞaCT¡§‡ûXÚÒÓ½<kµ;cÍ\rR»xÔxíŠµCıW\nÍMO|z±YáóÄî…­şª¸„-\"„˜iŠÒ6¡ÁHhıÏİ_§->ÿ\0]°±ZG_âpªÒ\0¥z˜ÀPãÓl*×ÑLMøö8©[ş{äPÑß|’š}¼qE- í\\\nÿ\0ÿÑŠŠŸŸlÍå²:ÃèÄ·Ğ×¡é…[ í×¶4–À44Â­ÓÃ©Ûl\n¸n7=<p¥vÕÿ\0=ûà\nİ*FÛ{áKj7ı@`+‡€ ûğ«k¾ı°®èwúG¾€¾„‘×üı±²•Ã~ûdiš¢ö©Û®6È*é‘dÃ·ã‰dªµÛ‘È–ap¡6î:âY7ÓèÚ¹AÚ½z}8…lµè{b–À={»­ÓÛoÂ¸å[¦ÿ\0Çå. Q½rAZo½iZõÀ†¾©„*ï}Í6\'ñÄ•^¤\r¿·İ\\2]Ğ‘Üt÷Å.¥7í^˜­ \nƒÓ(qì?ÏèÅêZxíOÕ…iÀR„ºƒˆV€­\0é…\rÔù{`¹K»}ı<=±µ\rR­¿_ê1İ.ëĞu5úpÒi¾+QïJ*Õ	éğØÛV¢Ã$´ÑöÛÇ†‚üCç°ú0uVÅİ¾7in„“O§zà÷%¾ÛôëLUÀRë4¸\nn:b6VÈ¯úãÍ-¦§ïÀÕ;£üúä•ktıxÇÄá´-¡®Ô5UÆ‡§L,VÚ´¥OôÅ\nî<p±wÿ\0v*êo_ı}ñµ¦©Z×ğÂ†ˆì|Âß½v\'\0Ø«¨FıkÜø¥Ã¥i±À 7OÇ¦E+¼HíÂUÇq@}ñZhö\'¡Áiq¯û!ü1K[íNÛœPVŸ¼v8¡i=È­ÏlB\nÓN¾¯|y!aîéß$6BÚ“ï¾(qé¸ÆĞÖİ»t\ZU»×s_0+DÙ$)“OãôâÅiï^»áb°şºı8Û¤w¯ß…H¥)÷ãh[×o£úáOPz¾Ø: ­5­+Š\Zß¯*Ñb…§mº»\\h?ÓŠŸş¡£\\Ui¯Èı8X´k×­Â‡ÿÒ‹€5Ì×tßAí…[âGñÁÉ4Ø©éø{b´Ø®·M¿V![V)]¿_óğÅWŸİ…-øçaƒš[µˆUÔíN°¦›ùŠ	]CPh	À¼oóÂvHTQ¿±Ál‚ ìOMÉ#0¨¢Õÿ\03€AT\Zu=©ïO…ã¡ùöıYk€ ş;í-¹ éü;á*ØPi¶Õ®%!µ¯¼ïĞ`´6+Ümí4·ì|p$4Ôß(\\v>Şûüğ•[J\ZuöùaVé·¾\\Ø•]]·íâEšâç­0ƒHh›ûb®¦ãjªêS\n\nı8«Tüúb‡¿ÏµØmı0Ú\Z\"¿O£å…]JƒµW8\rÿ\0]0n•Àv$P÷ûğ«©Ò£æ=°rV»ï°ş8J­Ü0§ÜqCt~’1(¦ÇJ§®eN¥züñæ­Ó¥>ÑéóÄ«bµ¡ßÛ2lvŞ•ı~øÒºƒ¨?v*Õ6}Ø…hÄ×5î;÷Â­§®JããôøõÄÒî\0ğÿ\0=°¡i­?Úùd´‚GË®!‰j‡û;S®*â6üIı[âUm;ı°8\0:AÜvÉZ·M¼iÔwé€ Pì\0Ø÷ÁlMööú1Z]íZšci§•#•;xàWR«\\ÕÄzõ=+MPÔş¬B´Aú~ü6…´¥i·‰ñùbVJŸì¦İqµZßyèôÂÅ®”ÿ\0?˜P¶„Õ€¡Õ$ø}Å4´»Ã,4öïÛi·úÏ–I‰XGİ‚²€/ğvÄ­5ß§Ç·n¾8 ­;÷ß¾!m>ş˜G4,#Û×åß\r!®ÕÚÓ}©.ß§†ŞØÚ­ßnøóE-êEwö8hh×zwêq¶+Î¸ªÒ<M)×5·^‡?ÿÓŒSÇ–f»ÆÇëß¶\0ş5ş¹%\\Fâ½1*êp+t;¹8RİC×úb–Àßo¼âRºƒ¯}ğ+`~@Âºûôÿ\0:âRà;uÁJ¾”ùÿ\0˜Â®uû‡ûX/]·û°¦•µ§ûYi’¢ï¿¿A²¥E4¯Ò>ì‚¢ŠtÛ Y…AB7íJøà!˜U›ôÄ”Ót$mĞt9¥¿™5ğ=öÇâ®*wüÌâ[¡ÿ\0=ºï‰*Ø\0ïNûcv—\0~C(-{ŠıdB¸×û>y;C¨M)Û¯Ó‘K`míÛç…UÂ0%º.ìvÛ¦şØv¤-Ûï¥\ZRáJÒ”¯ÓÛ®¡§ñ¦¥[CĞR¿²qÜ+dxìÅ\rR´íÛoóªÒ<w=)…\r•\'±§C‰VèwûñK€ğÅ\\F*Õğ5ñÂ\Z\0|¿€Ä«íÓVÏQ^ıp%½‡jv©öÆÕ°<;˜òK_1í÷b–Íj|0Z¸¼t<0¡iSpRzšÛ`ê®Û¹Üu®- ¿@8Ú´Ãaú°Ú\ZNã$Å®#åÛ|Z ƒÓäp¡£âzÒ§ßWqèÇïûğ‚‚Ğï¿QÚÓaH4;Ó­;nÃŞ£Û¥°ûèqJâşÿ\0ÙóÅV‘òø-iÀB<{ÿ\0\nÕ6­\rq•ÄWméÛåøáµi¶ğ­?ºbVÃÇ¾6ŠhîiĞá’b§ABg!ªvÛÜ÷Â­P©ù{ãkNê6ÛÃèÄ)XÀüv®$±ZkPGmñ\n´Ójx}8X¬=<Oô÷Å\r\Zá¦+\r@¯ùøáBÓO™ÅMñß¶(+OAZ1bZ+MÏÒ0¡oëı]°&œ@íòÇd-`kOG5h½(Z|OùŒPµ¿ÌbB\Z o¾ŞØP· éÓ´iBzøPV‘Ôöñ¡ÿÔ^§3w®ë_¼ƒŠ;÷SK¨NÃoãŠ·òí¥°	éÛ¦ëµ+·|B[é„¤.”¡¥6ÆÕp ëO	u>G¾ÿ\0¯•Ôü?¥ÀQÖ»ûàVÆÀ‘óì•Â›ş¯–)\\)_ÜbYQãµ)¹ÀJBğOmı¾X`VS]ú{äm’¢š“ãĞœ˜T^Ã·]şœÀ]Æ»ŸÃÛ#i©áòñÿ\0[Ó|h+NÀ„!®;ø× öÅ-íøWüëŠ¶\0$¸{œy«TéŞ0ªŞÛ}?~4…İ@ïL!­Gß×èÄy%x{JWÓ~™K]?¦*ÖÕ¡éĞaC¶ú1	n•ş4Å\r¹©÷ùãjêtÛqß\n»Ø0ŠAj•¿€8ŞıGCóÅ\\zuùŒJ® ïO—ËµA¿†ûûáBÚlE6¦ÇßH-ÛïÇt¯éOŸL­šĞí_áL!Z§€¦·M·ëêáôW»`z{íŠ»k¾ÿ\0çß\nº•;ô;{øàKU=O_×\\AE5Ä“^Ã¶¤ Wó¦P´·ß([Mö?!şÖ%Ş§ı¬n–š oøıJFà×ÇÛ+MR€W¯†pî:ûôÀ–Â÷&ƒ¶n„ŸØ;Üu=)µ)¸\0Soo§\nÓˆsòüwÀ——ëé\\PÕ+¶ØªÂO\\m(}¼)Ól•!oùšàb°Ôû°”SOé†Öšèzvû`Ñ6\0ş8ôU§zW¯€öÿ\0k%H[µ;ŠÏÏ\Zb°øƒ÷â´÷p:}ie:S·ö±IZÃr{øâP´ş¯óíŠığ ­8‘Në¾*ÖÄ{õÀ´Õ)Ó®4­ª+÷}øQK7ÀÅª±ÜãkMáÓµ?³\nıÆ(ZÃ¿–4†©Ûªß|Pÿ\0ÿÕĞ|—2íß6~xóWôceW°ş$·Nß~®§Ï%lmíî³\n·şuÅi¾•Å+¨+ÜvÂ´à:‘µ2)¥ãílÚÂ–÷Ü¸V›¡íò,vïüN ¥p44íLR¨+P7¯ñğÈ2\nˆk¹Ä²\n‹J{˜URÇä2%˜VN´éßüşüY¯7ú0rJî=½è}ğ%°<|*ŞÂƒ¨ÛoàV©á½qµ§PøŸ–4®#ûN4«XP×üü2HpÃnØ¡Ãmñ)\\+P<z|°Ò…ãÛ¾ç\"¸€>cı¬}èhªp«©½AZbêxTàµu;¾B·…G…1\nZ Šo¾kzliÖ”öùáVøÓ¦\ZC€ğÇwQ·†ô8-.¥ûñVŠÿ\0]ğ”8}Ã\n¸\röí‚’¸Ö«ğÀ‡}}ğØK¶êzm-ÒŸÓ\ZC½ñ«U¤Şl=:‡¾ôÁIwº¸¡¦é¿Îƒ®`O^ßíaµXAŞ#-#¸ú)’(hƒà6éßõà(pÿ\0?—Ó„+U¦İÇCô`µ[Æ‚‡¯‡\\7Hl/^#¯Ñ€òWüO\\BKt¨¯¶$Ú\\Gzíß¾Ø-[=wÜ}Àªm¸÷Å]ñP{ĞûÓÕÔ Pîw¦!im)ZlkøÓ\ZAXÔZŸ£èíú²\\¹¡i;|¶öú1AZA­:Tîp¡®£o\Z\r¿ÚÄ!o~ß#í×†ˆ$öéMñµZFÛnqæPÑ­~uÂ³é_mo··\\,V7Oâp¡£ÓsZâ‚´ÿ\0\\(Y¿ßôbÅ¢k_Uª‡®<ÑçÓZ@üw\nÓ]O…zá´RÚvÜwÅKD”¯Ê˜­#ïíŠ\Züq*³zïôâ\n\Zßo»S]Gù×-#ÇÿÖÕáNÙ”³L(§n+O×øaVü*Ø>Õß·İ…-©¶ø\n[ úzã²®ºTâ–ÇOÖ)l°Ú¸UpŸ*W\\+½zSå€n‡cÓzïíãŠi½éï†’ßQAÛúb–è?†TZöÁl—Àé\\K UVµ¯*öñùdU>;W¶;³\nª:·¿\\‹b¢Ó©=;ô¦D„Ú§Jí×°öğÆ’İß×V»x\\®>;m¿ÏRßol!\rP\ZĞm…Z=)Ôw8Ú)£·M¿4—qÛÀ{aBà7ï×\\=¶Û„¯Û¿lÍ\rkCĞd–Ú,	lê1´;èØøôÆÕÛ×øâ). áCD¿o»\n¸\n·¦7jí¸ÓïûğsW\ZIèE>X•u=ºÿ\0wVˆ­_L>õuz‡\n)Â›~¼Š[Û ÛñÅ]Nâƒ~ÿ\0†$ì­ü¶¯LRØ¡®Ø„;¿Múâ­Jv§_U£Q^İÆ»õ˜iZïOóü1V‰ßØáBİûı8ïJÑÜ˜Ãh¦¼G\\6Šk~8ÓS¿LP¶„¾UvÔøm×\\>óà?V¦é×¿¿Ïç¦›#jˆñ®$«Dnwß·Ï¥Ço§s‡™Cev;öÅ+MOoó÷ÆĞ·zq||1(XÔ­kòÂX¬jı\'sÓúbJ\Z?zş9+ê†ºı¬à%i­Á>4ÿ\0?»,aS^ØJ¸Ÿ¢‚´ğN ±*{Ò¿/ë’Bß—¹§¾7H+HÓå÷â…¦µéò#-§ÈûabV‘à1Ak¶ûßçôãh[±éßzaZu\rOö`V©Mºšì0+Tú2H¥†µö©¦4Iú<1+~gçŠÏñÆĞCT¨¡,P´ş=R´ÿ\0^4GİŠ­í¶ø±/ÿ×ûn:æKĞÓ}éï‡t7Nş;Œ+KˆïŠ]½6ÿ\0olo±ïÓ¶…Â§¡ßn›W¯Ë¶Óc¹={{aµ¥Ô¥Ew?ç¾)vÃÜ‚÷Z^\0¯±¥i°Â­ìzmLJ[õùtÄ®\0Tó?	\\(:O¿l*ŸÃ)^½zl~[á´ª©Ú§éúrÂ¢Ò´ÄàÙ’¢ŸûÓÛ\"[²ìkßÇÇ\"ÍQh6æ=°%±×¯†*İ:õ­qV·=z÷ùaWWÀPıØß¯·|(h×é?¯W\Z÷é·_Ç5wøUÃ—ôÅÁÀJWtûºŒ#°çŠµÆ”ÛKm÷Éo—|Š¸ÅA>ù\"­|‡NÙkn”éÓ%jãP7ûÎ\'em¾ĞØÇ¶%]B+ôWçŠµÓå«©Oóş8U <~gåŠ¶+Ûç‚•±J€;ôÅZ¥GêÁv›n•ë¿| !½ÏğÚ¸÷¦Ş¢p•hîÕÀUİ:÷ë‰KD1ÛÃ%G¢´Ù[×§^çæpªÒ7©şÖ%\rööû·Ä!oÌm^¸©hÔ§†Zpí¶(¦é@h0’Ø6­78¦œÓÛøàVè;˜ÒµOmü!]Ğuéş{â—\04éÜœ…­¹ñíO¾ŒP°ZW$‚°ïM¶=;ì2V…»ølk„1¦jÖ¾26­ãÛ¦«[¥)÷tÿ\0=ğõE5RzuğÀ\n\nÓ×¾ığ…„»lJ\Z ôê‡I, õş8¡oa½ùôÀ¥g5LZ¥hióÃTÅª^ıÏL-P·ËîÄ•hÓèşÌ%Ñ¦ZÛÆ(Z}¾×SG~˜Õ”-ñ4ë¶,§¿·ßŠ\Z ßß¥pZ)ªñÇÉV%ZéÓ¶(-ãÛ®*ÿ\0ÿĞ’	¥:wÌ ô|-ÔïAÓE8µ)¶Ç\r«k×c¿„^~ıÆl\rÏôÅ4¸xÿ\0ŸËm@çˆVÀí÷áH\\>ãß\0M7O}Æ¥Ã¸\'¿Ï¦›í÷é¥wJSîÆÕ±ïôá»VÀ?GË÷%°|{~8¥}\0§±¦M.^Äï]¿7ÕVûşı°rf¡éNò)\nÊ·\"Y…e;m‘d¸ş”ºİ;\\Pî¤ÛUÔ¯m½ğ…väøûâU®£¦ßv6®\">g\n¸W~ã|UŞõ¯†\\:ı9ªñN½q´8¿‰À®èaì1V«áó®oı¡‰\nêoÓÆ¸…pë·ùıØ•İ¿Ï®*Ñ$b®\"˜IVÀÁÍZş=1Vˆ={xâ®Üv§¾JïÃß«»mß–*ê`VéQíßvÕ§ãŠºŸØN*Ñ¯~ƒjcº»©ÅZ=iZ–)hÓ¶hëá„ªÓó÷¯ÏÑÛaµq%.4SZPá(höğ÷ë‚’Õ)µ78X¸(ì}ÿ\0Ïù±Ktùô8‚´Ø{ùK¨)íEw†Õ÷Âm]Æ‚•é¾u;~8-ZìÛ®U§r|FôñÆV\ZÓÇÇ$\n\nÍéÓïï÷áb´ı	úqk©ÿ\0>ŸÃ\nµ·SMûà²…´Ş´ÂVä}>øªÚTmß Ä%kŠ‘Ë¿ßøa>hXjkØÿ\04†‰#ä1BÓßoó8Ú)kmµvè>ñb´ïÛŞğì´à1V©J×qˆ;©k¨4­qU­ø¸±ZÕúvÂJŸÇ¾V½~ŒP´ıØ±+hÜPâ†¾°´k_l(X}·Å‹GlUªPï×SÿÑªíË€zbÓ©5ßq&%D=Œ	ğùãlUcu\"”ß±Éq$W\0d­-µ0«`}8[ri¸ÿ\0kl¤ûaUÂ”ñÂ­­Õ `	^*=‰ëòÇ’¸\nü»x`	lS¯OöñJê\r»×üûâ®æ1Jğ(ü1½ÒÚ¨:¶*¨¢ƒ‘>ÿ\0çL—­FÇ¿ğÁtÈ/‚£îÀC ª½iM²$3\nªk¿Sï‘,‚ &›wÛ[Ûïˆ*İ=é!ŞØU¿n˜«[tï…Z¦ßçß®#ïÂÛuëï‰VÀÀ{ûà%W©×lPW/Mşœ|ZÂ­şgçŠ[§OljĞØ¼q÷«¼7®*ĞöÇªZÿ\0?|UÛxáWm¾jÑ«%ZéñÂ­ƒ¶İ6ë‚Õ³¹ıtÅ]½qW\nö{`İ[ÿ\0>¸’‡˜”µÜíZb®ïôáWSol\nĞ¡ë¿ëÇšVì}ğÚ´iÒ•ù{b­u¯â1W~\'ïÜb­môaC¾{J·¿¾Œ	uätî%©N¦‚2)j¨7ÿ\0=°òW»tüqV€§ô÷ÆÒŞçüÿ\0¦([¾©Ÿ|,V»mãôd…–+Ú€tùb´î¿O‡Ïß	E-ş›ôÀ´Ñ©4êFKv+ˆú+ã‚Õ£M¼>^v¦+i¸ßjâUi=‡S†Ğ·~ÛJ·L,VïJÓ¨ßøaC_çôü°Z\Z#onçÓT ¯C‚ÂÓG§€Ã\\ĞÑ¯öâ…»>ô-ë¿cŠ·;bŠ[·mÇ^X¡m6 ïÓ4TS¦¦»íÒÙ¡ièzáCT®(¦ˆÛèííŠ´~ñƒ‰iÿÒr\0üºe€½A\r–Z‘á’b¤}2HÀì¦U”56í÷â‚Ã4µâû×`rAbJ5H;øäf¸\r»€qW÷âRuŞ¿v4«\'û0ÙVÖ¿wlUpÃé¥Ûşšb´»å¿İÛW€)¾Õè~X”€¹‘•Š0£\r˜|±æš§SÇ©ßPí¾Ô?F¥J¶Ú›	\\´¨I§¾ª)=)¾ØãØ‘OoÌ+)=:öÀÈ*!ñé×+d¨+á¿ñÂ‡PŸ×Om]JîG¶u>ïi[ÿ\01Šµ·qó8…u>ÿ\0íÂ­PšøºWûqªà7?†/ùŒÅ£\\)\rn=GZâ—tÇ¶\'eoÇÇ8ŒJ´E}ü1Wş8y%Ä\Zÿ\0UÆŸF*í¾TëÈh~ìBZ\0\nôùûa[Ã¶VÇOÃéÅ#oIK{\nàCTëLJ¶q*×O§®Ø„µÛm»ıy+»S\"­S}±V¨~ü’Z¦ß†=ÀÕ¨éíí‚”µALz+UÓÛ¦øô¯õÂ­Óa_\rû\Z`Woßîö8’UŞø\n¸¨FÃ Ä¥İ÷éŠ@?¦*´ôß§¾)ZFÇÃ\rªÂ:íóñÂ•§ğ¯İŠ\Z?ç\\mio\Z÷oúñE4MËğÂ¥mjzõñë‡«µ¾ïqĞ`+KM¯ğÂ†¸Ò‹N¾ŞX¬;vÛ…­ãMºŸßŠ°Şƒs¾IMMGLmZ¦ÀÓ§nøêmø×´}¾M1´-4®ÿ\0wj,Vøç®+KH>Ø„Õ\r6;œQKh+Ól4´Ğƒ^ûS)¯zS\r µN½Æ(Z—l\n·‹Zâ†©Šº‚›uïóÄ!¢¾?N+OÿÓÚ¤×üÆ^´ÁcÆôä;Ù ×(ª$\0U©;ä©‹M6ÀãKKVİ«ŠQ1­:ïüNH2*ƒãòÉ‡Soáí«{×åÓ[oÚ¶Aï×éşmW}û÷ÇÉ-Şß,V—”ûM7Oïs9ÒTñ?\Z‘Oø–S“%lÌKş?àBÍKÇŠŒB·F4jväOío’„ı,Ìl´[høü”×z›üY.{£–Ë\0ßsO–Ù+`Øî}©ã‰Ù!p§Ñ¶ß-ñUêONçõàdŠ»mú°aU{mÓBª¶ıyÌ*†»×zàBîµ;{}87Vèß¦:kß®è—R¢½¼rV‡\Zöû°ZµAíßç¾)pù|°¡¾ôû±K©CããŠƒáÛ7ãíˆBÂ	#Ç¸ñÄ”»ïRØöí\ršn*pù 5ßüöÀRãÒpÚµCÔõíıq¤Ûcaü=ğ¡ß.£åZ?<6Õ=ñ*?~<•Û†ÿ\0×÷û±WS\Zb@VÇ‰ïáˆ\nãâ6±V©·Z~­Séq§_óëŠµJoş}qKF½QŠVÓzŒUÇñüqµu>ƒï…Zùü°+tÛ¾*İ; cjŞÃøãhqå·lH	¶vô¯|ÒîŸ?(Zzô®ÛâBVu7ş@Aiº6ıX¡aëıq´4E+‡eZG‡Oı1R´Ğ5)°ïˆ(h’OŸÏ\n«Ú½<1µh÷?~,VÓèöé’¤-\'aøŸ¯-=?ÏsáèX@5ßá#èÂ‡ZĞí_ö±²†ºŠwëáôbJi¢E~.§®ZhÔWzuÂÅiüqBÒ?ÛşJ\Z#cí÷àU¤İ=‡ù÷Â…´şÜh ´@ë]ñVP>ü+zî¸m\ro·ã†ÑKH¯ôê6ÀŠh¤÷=p+]ı±¾å+Opvî~œmÿÔ\rÏã_€õåá•JB¶æ÷0Š\Zî8ÄÎ\"¡CĞßF[“”\0THùÓ-¶…ÀSßÀäáM¶¦–é¶æ8B¡;v®´Øñ\' Ä+t>Äx`K`øı|KM€+¿mÏ·êÆ’¸ûõğÅ\rû\rÁ®ø²]^çm÷¯jâèvÒ›—·YyA0©â6n?fµÌ<³e”G‰ŞicúK‡µPâ”®ãˆ-?\"Z°v[­<©ORYÁñ=7Ëˆ\07ñ÷¦Ğù}¥ƒÖÌd±‚R¿µ”M\Zšg–7@q ¦Òu˜Æ^\nõ—âZ)ãÛìå‘Ì1(“Wêşb\rH=ı¶í—Ó`”Úµ5Å+Ñ=»ÿ\0Lª<+ÓÛ%XÎõ?çÛÓ0ª¤n›‚UF%‹tïı†ƒpŞ¸¡Ô;)qÛ‡S’KG¥i€l®ÜüğÙVè0*áÓsQü1÷±]NÆŸv%´İ°ÙKAGÜza$·ĞS½;ãt‡S}¿·’º‡·á…]Óq¾*ï\r©ã¶*êb—\Zvè|:aC_æ1	[CóÀ;ÒŞığî­Ó~¸êÃVëAüpZO¿Ç¶ «G±)uÄ÷«|~x«DšÓ¡ş¸m.®ŞŞ8ªÚnF\0í»áV¼;á\nîÔ=¿VpoÕ‚ÕÃ§·İEnéùb†ü|>ü\\zï‡ewÑÓ¶hÓc×ûqKTßÇU§s…+ëŠ­Ø–!\r¸=<Fhõöñê1´-4ûû|ğÚ•¤™ïŠ^$Äm°ÂÅi­?…Z`R¶¿Û^øAb¶›{ãHZ@ú‡Ï\rÚA:ş¼J´FãüöéŠÓG¤1U†»{â«Hz×¦,Za¿…GêÅ\rPtûéã’èŠZ´şÌ\nÑéO¿\r¡İ>°Z¬múâŠh÷ğşX´@ØwÅZØöùœi\rSğëŞ¸=Ê´ß‡NØ¡£òëˆU½éã×¶!ÿÕ\"Ã%‘ ‚Ôê73q\'¹<I‰Åˆ­Os×®l#ÉÅÈ(¬¥:dÚÛ§ûX¡ÛÔİ~œ6‚®ÀıÇw—ZáVéÜü±K«¿ËîÅ[*Ø¨î=ñ´ÒáÒµ¨íòÅ\\Ğ;üı±ä•EF)ÎŸË#{³e\ZeªµŒ7&‘J6\r;Ó5ù9ì×—!áúÕ5İ*6÷s()3­jÀ}•â¿´ß³‡Cƒ^› •Çı\"O¢ÚŞDò¿rL_ÌşjşÖ[<¶)ÌŒl\Zõ\'ó2Ëyo	`IeÜù§ÚÌPK…c	«ÌwÓËzöa\ZX>á®ÄÛ?ÍË34ğ}Yéà#úç“ü¢N7ñ÷ÌmÍĞÓ¿·Î´ÅipğñíôbªÆK”‘Oo»\"iVFF+)>çÛ\"‚Ú§oŸêÁ³âL*ïÕŠºŸqÂ®#mşŸU¯£óğÆÕ³±­>ü6®ğ®ÇlRáµ:}8Úûÿ\0µ‘`Ñÿ\0>Ù+HZEOá„î—×ú°%p¿,ZÛ¯Sà1KF”ß=>x«¯W×’U§aáß\"—w¦*à(:TuÅK{÷ïôaä†…Gm\0Rß¿\\PŞàm½qW¹ßJ´WîğÂ–÷¥<v®V€§ôíÓ\r*ÓJ{`K¨w¡Ûkj|»b—±§ÈãÍ]Mºm†öV¨?ÏÃ\ZUÔ;`(o¶ÿ\0u?à|p«ˆ÷Ä«DoüpwoëŠ­?‡õÂ•ŸÇ\"–ˆFJ•iùß=ñµ¦¿XÆÕª\n\nï^˜iŠÂCÓúü°n–›jĞîÏøa¶+O¸©ñ9+¤-c¿pkø`CDPÿ\01BÊ×¡÷?<BÖéì)¶b·ÇÃîßÛ\"®¥Ew#ß¾M8÷=‡Lmie6§m©„¡o]ÏZı>8Ñ|k¶b·pwúqBÚTĞ˜ÅZ§ğ¦¨qŞ•5 ØàU¤ëŠïÓ¶†¨Î¸Ú\Z¡úqZkHì{|ñ¥hï]É·Ï¶*ÖôØ{`+6ş˜P´ööéÿ\0ÿÖ\ropĞIZüË×+”AjzÅX÷prÈµÏšÚ\Zôü2vÖCıöÂ‚NÔúi„!İ¿|*ºŸu)×©Ú˜9+‡ˆ;~8ªê‚>_íbi[°ÿ\0‰m-”Ú¯DäÀ³‰ñ8	êÈ4‹¶tBb¸«[y0ìÎU#{©´6šÊ…¢†\nñŸî©¸ôæå»‹–QâÛê_ªÛ9Ódã ­>.ÛxWgv\'ûÎI\nú÷ÈVáÕ}Tèö~/³ ;°‰ú}I­ı‚F«$›Ûï@I5#à^_³ñe\\îâæÅ2M¹î.$šJ’ìNíRaVÍ†8ğŠmªØrŠ™õÜxäĞ»n„øS¥0­6\0é×ÛT®S¿öâB…@G_Âƒ\"Ízšoßå‰HWS_áãJª’ı}p1+êMşøÚı0¡Û«òÀ­ïòÉZ»¯öb«~]0%Â´ıtÂ¶»m‚Ôª/|y0.;íÓõchZAëøa¶mµÌa[Üº•«];n:â®§ûx¦İştÿ\0kvWPïáŠ´@¦İğ+]¼v¯ûXm-ÄÖø¡¾µ9«©¹®Øm\\ı1ê®ß·Ş0hõß¿q…]úúLR×M»xÿ\0ŸÏ®\'úmCF½=º×\ZK[LUÃ·|U®İ*køâ—zôÅ[ïZüË¨n†¿çÛ\"­ôŞ»\'`†º‡ß«¨O`M0q©ï†’Ó@OSßPö•¦»ÓVˆ;Ãmñ*¶ÿ\0çß\ZKG ıX¡iÛ¥zş8ªÚĞá…Z;÷Û§Ñ…Nàƒ ÅH©ñ¡nßHşícH-|~Œ!‰S¢OJÖ¸Ú)²\ráş,kt´}ã‚Õa’>HğÂ‚µ…GÃ¹=Â†_ãı˜¡i4¯C¾øÚ\Z#r~_«AZE=¼k… V”ÛÛ®[N‡éÿ\0o\n\Z8«]>]ñU§}ü:Ôãhh×ßæ1Zk¶ß3òñÆÑM§­§C…‹]ÜS¶=Ue)Aÿ×./OõOqíß\"ÚªÙÜ,K©¿°Å5Nş®JØÓBƒ`)’E8×\n)ºoOÕ¶*áÓc¿jâ­õ?Ç¾4´İiòû±Km±BáÛ»®6Ép\"Ÿ.ã”~qj	†çŠİe#j²¯”æ˜eÄkoád6óB,®¦(®üT—Û‡ù9ƒ(›qçâzGğ©kWê–ĞÃŠPßÜ\"€r<q²ËM‹Ôd~¨±İTq´áÄ¨ gbzrğÛ/‰İÌ6AJ,®RÎøé4xNµ?díÈ•–ÊÌvq…ÆUl‚ù´2ì\'(T`ˆ“]øŸ–W$¹QŒùO‡üÄ=ŸÕ®D’S-Ò¡˜tŒÿ\0­–Îd2£[%£Ç¡§İ–Š\\äƒ°Ä¥uw­;S¦*Şô¥7Û¿dT×¡úr)\n¨Àáß%U=\\‰AVZÖ¿æp°.¡ş˜	Cj{¼{â¥ÛPÓ¦º»Û¹ÁjÑl0•\rĞPx˜Jî¿<6«éúò$±+Àï_óùb­4şŸ<,–›tÁin\"Pã\\T5±Q¶ıü1	u(0…j=~ü¶ŞÃo˜ÚºŸwLmZ¡ùá.§NØÚ¸t è{cjên@é\\\nßëPã¸¯ÓãŠ´¯]±µu;‘CŠº†»§­5éŠZ#ûp¤5¾õë‚•Ût¦*êoï…]OóëïŠ·Jm÷`´6>{b«¸‡®º-ªt§_¿\nZÿ\0=»\nãí×X|;ûaK[“¶ş8)+	~¸RãS‡ªµôPWøà\nVú}¢6×\rªÒjkĞxâ·m½ğ…[O½ñA[AJİ<A9hĞSÓ¸É¢–×oa¾6Åh§]ü{×¾(qğ¥)µ¾$¦–vî:m´í±Â…­±=Gá‰CGı¡áŠº¼qV¨İúıF+w$vğÄ«Õ\'zwÂ«}»ş´~ìIWSom\rS¯oŸ¾*Ñ³øb†·éãş}ñR²›t÷Ä¡£OóßCGÃ¿n¸ªÓ¹ ®ÿ\0AÂŠhõù÷ßWÿĞ(¾‚RÔ_³Şƒ)ŒƒÛäÆH@7© &¤R½+–ÛŠbB´72ñ¿¹EõFE(”té¹î0³\\Õ\rF6¤,&†½\\6Åµ ~½=²H+€®ßHÅ[&½>Øm[\0¿NçÙ.ZxïĞıQK†Ûš~¼›ùíííLJ©ÏıÓM\0ÛßR©c¨İ4A–HúFßºäN1Í0Éh÷¾†{t¯y(#ˆ>ßµñe^Û£-í©a0Œ©@¾¢8S³Sö“1o†A„#Fñ°vm«Õ¿VlC…%¦g¯ h|{šd€k3(ë+ğ’F%^H§}éPz×òoÇ¶O9†øÁ¨=ú×ß•Íqè;xü†\\\r:ïı˜«cjöï¶*½zmØtğêi×j\näK%ejßç\nËÓzş¼1+ÅN˜»½FWWmÆÃ¦nÏßãŠEOŸõÄ*Ú£¯‡¿Ó¥İ7Üá÷*áÔÿ\0LPªĞ}ığZË˜{üğ¤,üüq›¡¨ÂJ@úqZØş¼nÕİ‡àõÂ–ˆşßíÅ[Ş§¾;«F½{\0¥ª\ZôùaK‡Êƒ;o\rûwÄl®Üâ­Ó·Sş}0«ˆÛm¾XÑvºƒ·_Ç´Nÿ\0< ÚZÿ\0>8ªŞş>²hu4üqVÉåíŠ»u\r7ëßéÅWmØPbX·MéÛ¶…€ÁÕ[¨è{uÅZ®Ûÿ\0Ÿ¶ «D\nôù`JÂi†ÙSD}8R·r?‡†7JÑ§OíÅZşÇÇ[ÓîÚ˜¥i§+ïß\n9­Øw¨ßï8Ú\Zë³|ÿ\0(Zk×¿CŠ­mË$­ ¨ÿ\0=ñ°Šküé.Ø{Ôü±µXÆ‚„Š¾AZ}úvß¨Çd- {\nwö8}èk—˜VĞ}°¡£JĞtìişµ…hÖ)Pi…I5¥7ÆĞîŞÃZkQâk¾4Eh{\nı­5øŸã­4wŞµ8«GÄt ­êHøÚ\ZèÏîÃhh€~GÇ+w=ûõÆĞ´ÔSm\nAÿÑd®†À)şŞkA!ô(ŠKïmƒ¹u¥\rH¶_•±k+S{*F8‹mÇ\\Éº%‡e2@Mz\ròñ \\I@Ä«%Àp|,;ä˜‚»ÕGøj*zl6­q~Æ´Ü±²ŠTş»uÉ*ğ)^¾#W\0>«ª(\Zµğ÷À®éáŠ·°ØWçí…Zuä¥HêãRè¥0iQĞøâ\ZãA‰%«³v\0¯Oõøä8»Ü¡l÷VeâæcŠzÕzU—®ÍŒ¤`”ÈC\nøuXGR½ÖˆGÜÉ]©Ò™ Öj–\rˆ>¾JØGg+É\n»(\n@öÈcŒ’×Äo€–Ê]]èzâ|Õº×Ü×¯LV—Aùà’¨¤Óï©ÀK ª¤ø×¾ø¥QHä®¦ WÖWWmò(qÃ$B‡«GÛcß»çß\n´<|0lP}ûŒP«=NØÉyÚ•ÆË3ã×$ÉÀíãW>àpßr-¶‰ÕU¸š‡¶@;,§oÃ&»ûôÅ.ÜwÂJ»ÀŒAİVĞwé‰K`î)ÓÛ©vÂ›ôÂ‡\0)ìqóVÅkï«]·Ävÿ\0O¾—té¹Äl…½EAş›cih×Û\nBŞÕí€%¯6–Åzÿ\00)hRŸ*¸=Ï|i\ràBïjuéˆ(jß,Rê+şÕ1[u(*MGcˆUáá„$-$ïßúàd¶ÏùÓ´Iïò\rªÒNçHhĞóí‚Õ®ıvè>XEªÓZ«‡ÍZ§ğ­qZvíLP·Û¸ÜühZI¯!êTì+ÜÓ¶šhßˆß#kKZµ=ºÙ.¨ZjNİûôÂÅm{×~ØÚò¯cş~m¯£§NØ€…´ß¯ÓZ=~Î->>Ø¡¯aàvğÛ­?í†Ğïáı0%i	ûabâ´W­Ú?,\rıp¡¦ùàV©Pjâ­|ÅkÔbKi]»\\4…§ïÛüÆ(/ÿÒ{Y·Z}=óR$÷Ã*„‘I«¶õû²q6Ø$\nÃ27ÂÉÅÇL•&”f·i¸îFô¦YÒ\'BS<l6\"”íï™BVvH‡äÈáÛ-Å „lLWâíÓèÊÑ|‡QÓ­=ğ6.ÀïŠ­äE	í±Ä¡Ss×¯€ùá*RH¨:ôÚŸçóÅVG+HÔ¯ü¾\'éÄ WŠ6yDH9J[Š¨5ÄÌsgH-F *µ¬µ`ÁwP+Ç¯ÑŒ¯ÜÇ$@h™\"´=¾Y\"-¢9[ë;ÔĞŠ×sOjä©‰öVË237¦œ“ğuÛÅŒòÈl±\nW&Z^å6	¾ÿ\0N\0ÈÒmeªGoc‰{ˆ©Ä0¾W0Oõ\\Ìs4YN­–³D¨Ì[€â¤(aöı®\\³wÍ\"b8â€×-¿DñúÜ€!OÂâ»|@fF<Ü]ãÉÇ*úR9õ©÷ñ5–ø¶³\"VÍÜ°9Ù[ŞÁgªºïE‘\0åBeùe1‘²Á³_[@—L³\nŠÃ¢š›à*ªwßc€ª´mÓß°T>ÃÛ.=7úp†½ûõß¸øŞøm\\NÛÿ\0\\mZÛ¶ëáŠ¶õßkJŠÃ§_»*{b¬#MÜôğÃºªÅ’ˆ*Ùk›	Êa %rE*Ê:W*–MöqN{;¶Ï¥)P:¶&Ü˜ä±jf7^4¥>{âHgÄ_¶ih\nuéş~m+HîzwÂá_¿lôıCm\rƒ¿Çª¸\ZoZ\'Ûl …uk¿ã‡tºŸwlUmwß÷¥¦¥:aäÖÔ5HÇ’´<Oá„WUnŸĞàW~?íÓl~?~(lOUÛÿ\0oÜí„«©Üÿ\0LiÛüûb•µÛn”o¹’ôßaí²wlUi>p¥£¿OÃªÑ=Fkø÷÷Â«Oãá­-?Ã|m\r„rA[@>XB´£ÃqÓø`µ§l@\0õè{ï‚öZhÿ\0ŸöáU´ØÛÃ\rîÅiÅV\ZWüÿ\0C½Í~xICDS¾ÿ\0çÛhĞWs¿L(¥”ì7¦Ø¡º_^»cjÑï^§ı¬P¶”$‘ôõ4ÅVĞøÓçŠ\Z\0×çŠµMöïãı¸¡ªŸo|U¯U®İwÀ†¿§êÅM\r~ƒŠ“½:\Z|úaR·~ıºŒX¿ÿÓ0í¶j¸v{~\ZsF¬¤íT	…6DšÓäpÙoñW›Cé^¹&>&év¡aTªü3\"¦dñ„‰ápH§Jæ`–Îñî¾X¶ÄvÛ\r¢0¤tAŠï„–`*­õ5êiãˆ+N l	ÛÃ\r¡¡\"Ö‡bFŞøÚ¡®G!×ÇéÀdrä€6ññöÿ\0+\'l\"\r¦p<–ĞÕb*ˆÀWæÿ\0c˜ù\r˜ÄU”f~eİ‹3T“óË¢(PpæI6T=ür~ö‚[¨m¿k ?†!<Â–ıÎıwÉ5ÁÅ\\Juñí…UànRS¿s—\'#æ“[\rEìO©*AƒCS˜ó‡%\Z—%+Ë¹®æy¥rìæ¤ıaL% E¢(\ZĞÓ°î>Y{ˆJqi®l–“¨\n¬\\M¹nœBSùr¯‡&A;ÿ\0551¬n³CP¼ã5#—ı¥lˆÈp6å­ñ-;j6ßÇ$<™e>ø	¥UB>_×$t§\nöÈ5k¨+P~“’C[üÈéÓom‡Şq¾ŠÑ§½vÁjÙ ß¦vı+¹¥qVÆÄ¹èk‰Uq¸ è0\ZÖ2íü0¤*–>úb¤£lål(ür¹œ|±´C]‘û;¬E¨bRbÅß®ûhÛ1²V\r²Šoñ“¶ÄRäôZ2ŒEz©îlMƒhi“ƒTn‡ìœ°IºÔ»]¼2A›‡Z`%. ?N[cÇ¯1Wq¿†sxõÃh\rÇK€÷À¶»\ZnÓD­=\rví¥¡×Ûß$—–ÿ\0áˆ(n›àVÅ>ì6†Àè;\nØéAÒ›œP]¶*İ*}Æ->=°n–‰ßç\\<ŠiM«Ô™ÃlÃ_†µ]©Ş˜:*ß\ZüÎµ¿Ñ÷`R´õû©….5éZSXvöïô}8îU£Ö‡}é¾(-lE:ƒ×$<´:uÁkMĞTŠ|^>-4×¯»BÓĞ°é’BÒ>X¡i¯qS÷{ãh[AR<Fã5ãAòùaR×_Æ¸±k¥(MÔÂ´¶€÷6é\\KF‡m©ï†ÑMu=w>?}qi£NûôÅÔ?†$¡£„Öš¦İ*k÷äUÛR£ïùo†Ğ´€:ÿ\0™ÅiizŠv¯Zâ…¤£¡®ØiißjÖG|P·µ?†(¦©¹ ëÛ?ÿÔ3àµ ?<ÔÑim•ZÒ¹!²Ûd•ÿ\0=¾XjĞŞV\"|Ÿ\0ÄTİQ…­F<4ÈØÄd,WÜí–À¶Ø!Cê \ne¢LLV”âw¿^L¢Øtã·µiL6Æ–2“ğ‡Rš™ú€éôá½˜Òôø›~‹ZcItÀª1Œ\0z¿ml–É{;\'¦ÍT—\ZíÈá®­)ªPv»õğ÷ÉS\\¥aH¶û÷ÂÔKjMp¨Zêy\r±eĞ­|O†4×ñÂ‚¸5:V¾ØV×µÁuÜ¿Û‘¦Ée°Ğ™Àë¹í’á´„;Õ\'®ã\ZxÊá ;7^˜Ó!>ôL·6ßÜÊPÙGBd‘”AæÛÒ“\r2òêKÍŞTøIÛŸòàà\0läãÈdSÁÛÄwï•—)Uwùà!\nèNã¶\0ÖU¾DîÅÕû¼phwöâ–úo–:¿ÛòÉ­\nu¦Ã¶··LP­ß-ñk’ò;÷ÿ\0=°!iØĞxoOá„2\\¬Cm‚B òeë•Õ4òPf4#¡«%Ñ´\"M=Ç\\“0Ri…sI·Ş EA·Ü’°5Ëqø6šol ë×\n®úÔâ†êğÅ+·Rï\nŸl!Vÿ\0¸Û%¤ÓØàµvı¶úpÚ¸ÓÃaôáK`}ÇéÄ!pêkôàAl}4ë‰(lÖş\'hv§Nÿ\0Û€ån¿V$!ooÛb,\'ÛHZzş?NÈ-;aÛ\nCGq\\R´Ÿ»Z¯övß\nÒÓMª>œUÄö÷ê1MOôñùaæ«\r½v® ¡À\r±KˆcĞuÀ—=ñ´-=ûü:aCFŸ=úcHXk^•?vCTØıõ8ªÒÆ¿OÏbµ~ı‡ÑˆCD\nS§v¥¦¶Ü}ÿ\0*{b´´µ¨*7é‚ÑN>$lŞŞªÚíO¸u Ãh¥¾½{àµ¦º\nO§éÂJ\Z\"›§øãkM¾6‚\ZØıô#ñÁkKH\0ş5>Ù$->¶,VÖ”ğêqä…¤ÿ\0Nhk¾\rĞVíòé…_ÿÕ0ÄÑz×ÓÛ®CÔñáGE‹i4¦zïÓÇÃ\'E’Òh+ZŒ4ÉM¾.¢¾Ù 2¿ùødº%EÀ©ZPw5Â	A\n,”¨ë½NL³¼j:üòL÷¨ÚŸÒ¸òR\ZZ(e®ÄıÛâ€@æÏÄÕwÿ\0k$Å(»P’2öìH8yEûS$Õmv’Åz•=:à-‘U(\nŸ–¢ÜcjE\ná\r&4°°±!¯óÛ\nhƒ÷ôúp”;Ãß8\ZmZŠ·\\*¸1í÷à¶Jğ\\ÉrF¡öÀE¶Âd\'¶šÜã=CPÀ¯Ï|®Q=ègS(¯-\\U%R>Šö9Uñ y#È~ylÄ…Dš&fJC·¾@&\'š <ºœ,ZÛ| «`ıÿ\0Ó:;àVˆÇª\\Ûo§\ncaZwşxÈ+TÓÀÓq‚ÚÖ§µZ|ñ*¯hEAØœ‰\rr³‚jj;|²Hñ£=·Ş›åœVÚ$Ğ*{ÿ\0•¦ÃL6ßñïŠAXhIùoşg\r÷²\rîq%m¦ÛSsâ7©ÄZÊTï_|6É°>ÿ\0Ãwë×T-¯Ïç…-Tt~6šZkô¾Ö¿<RİiÓ¨ïòÅ\\+Zvé_|!¾şÇõâ«…{ÓüúdmuvÃlZ Ğí.öëãáŠ¶zm·%V1#IM)…Z+ôáJÚı#Z\'ç†Ò’6¯Lm+OãøàV¶§¿¾Ù%j¾õÚ´M:vÛ\r­-ß½z`)\\?Ôqì;uÂ…‡Û~Àâ«ß‰CDŸíıXP³­Gã…\rV ¿øb¥¢>ñ±¥:b†‰ üq<Ñ§·)[NƒğÅ\r°íM±E4@ã|([_o à¥uw÷ñÂUm+ştıx-iªÒ›ìOİŠ­$÷ùá´5ØŒ4Ä¬a\r×’A}ü>á¾-\"½ıñ´¤Sø$«_µZíÓÃE?ÿÖÈøS0æ›®4†Áß|J»¯LQÉe)ÓsĞbZÒßHÚŞ&¤~YZÓ-ÏO)µÓ| ¯5ˆÎôşeµ¥\'ÂSOã†ÑJ%K‚ÔâOP0Û\ZYšîj12b#I]ìD³=:õ\'®J%ÇË^İòN7|Úˆª¤`\nûl$·R²BÒ(âjı…rUÍº8ìl¥\"ÑŠ‘¸48AkÉõ\"?•¸åai]©’bC[×o»\r¢N£¾(§}ûŸ\\QNùïòÆÖœ<1Hß§Ó¤/îÅ’´SRŸ¯\"Û	Òg©qƒÑ£Síü§)0`Éµ}½ëÉ:¹ 5­VcAÈ‰â	÷âŠVŸkjW¥kâêãXäæIb;díBÒNØI¤·_•Fˆj½wß\n]P\r:BÒğÆ£|iŒ)Z`º6ÒCN7ç×èVƒå¤¢a•Qzn{ødH²Ó8ÙUI€øº”=G…Š£EÉ}cE-Ò2\0Ó*4™X9å½~ìº\'g&$RÀ¥#§Y[~<X\ZÔïO|«‘Æ¥(E‘€=6ş¹dNÌãÉRXş0•múôÂwc1{.ˆÂÒëSCÓa\\w\"@²‡Ï¯Ñ¶LÆªğ#)ZOĞ1dÑ#çóÁkKMG°í†Ò·õŸ|m-öùí„«U¯·ùûaµlwêGã‚û¸\rG]°.ûú¿†¼RÙ¨ë¾(hŸ»ÇÍ+\r{a¤­c¾,ƒDâ•§\nV±ñú0ZZ¨Ã}UaëO£lBZ\'sØù%hï·¶øª‚vù{à*¸w=·ÅZ¯Ğ{oøâZ­{SÛ\nº½)·‡¾b²¢h1BÒiSÔÕí«F”;\Zößmw­;ïôaCTÛjĞmL*\Z¯Ol´×ì×è¯êÆÖš$ô\'Üûá´,;¨\0V½+Mw¨¾ÿ\0otS·¥6÷úqCDí_öÀ«HëN‡ï Â†˜ö§ÃØïÛXNûõéáŠ\Z®ı=ğ±Z~{J\ZŞ›í¾ôÅV÷?4C§¾*×ËïÂ¶”`Cÿ×)^´Ì0^é½Åp!p4ßüşx¡±áÛ­=ğR¶>şÿ\0†(h×¶!V52A˜[@>cõa	ZÀ|ñ´…¬»r[ª‹Æ9V•Í0‰RÒ‹&ô§_Ymd)H\Z†ÿ\0mi}ô*EH;œØµN;%¬•P@ØuÉ8æ-*EFŞIXÅP~YÚº7t`ñü\'¨=wÈÙDl#.`[–ça\\)ycAÛ*Œ¸y¹1‰Y+LTïP\rz×lÉ÷uÙaÂiIİÖ™&–‰ßåÛ.ïú±PØ£+M~³ßp4Å¯öŒB¶	®%^6#|YLdöÈ’ä@\"àsÉiÒ¹Tƒ•L§Oõ¯¢),”ØLzóÌ	ìv\\¤@ØËƒû÷û}‘N™“³L>¤+ş{d­›[öŞ˜Øÿ\0?–!rA+ü0!^íô`!®AU©J×\0¦°°\Za,ér5zmı˜‰…~¯åû?.ùÓ#º¥°i¥	Q¸¡ßèÈÈ±É°µfµâ@õÈqµŒ”…•Hµä2Ûn‰\'šÅ™ãCáĞdLm‘ˆ%j_}êrÒ\\‹ÖgX§­¦lA]€Ü˜Qoô`;\"Cı3¥di	A@iJöÂË@İ»›IDŠáĞĞ|>88ì®<–j¸Pÿ\0¬äÛ\\Næ†¢½F¥	©ñ-j šßÇ\r¦›8PÕûx…l\rª7÷À…ã¿c‡šMÿ\0\nà+nßèÅ\\)ôvÇeZv­qİ+	ßÀad··N˜m-7ùŒ¤-$øb•§ÇÇl)h’:a´­4‘U§ß¿O–UİÏloulS¯SŠ´h>xÚZ\'z‡†íüpÚ´OZıçoá$--Ô«‰ïá„!ièj>ußjá¾ä5½zS|AZu+ößÇh÷üq;*ÖùíŠ\Z=\0ïòé…X|[}ıú`C¶©4§ù×åˆ*CF‡¦ŞÂŸ†ZZAö¯ßøá*·~ŸÙ’b²µÜõéã¡­ü)ş}1æ†©ôí×\n\ZğÛs÷àµhûõ=6ŠZkZÇ[ßèÅ\r\nöÂP´|ö=pZÿĞM¶ú=óîSÒ˜ºû–\\ø–-Ö‡|mùõ=1VÈ¯ñÆÖÖ8C+Zz\r·ñÉ•öé„2hÒ”¥kı1èªN¼«’	QtëòÉ[f‹ŸÂço~ŸN4¤mã#‰\0W½>œ6Š\\šJHÓsµH5$šöıò™d îÏ‚	lI¬qqØøå±˜!¬ã¢Ğ@ÌÛ¦ømxm]‘kZ0¨øe|Û —Oï%	C±4è|3\"%×ç;¨¼øvÉSŒÓ\röÅ4)ôa§*O_ö°w(æêı>øJÓ»{}ØÚ®n)Å+·|\r‘DEÉwğèiß K|gcló·ÂµßÛ®Q’tæã\0)‡×>p\0aÆEèHÊxm´Ä\nmúÖ‘ÎÌ}P}9î@ªµÕÉDïN,…HÇø~¶şŒ°5óÀ–ë^ŸGÓ‡ÜŠvı0wj×éÂ•èÜXx“‚Ø‘²,FD´)5A>=°†aÀŠßD£„\nÔıªo¶’7EX]ÆµU<OÙ nS8’Ñ›;„d·©ÉHäFÜ†ãÛ!8ñ%)iYÜ(IÚ™}9â4¥©~çr†İœy¨Æ¸Pw¦\Z\r’4ÚÙÁõÛ!ÄXÈGOP,›†îG|}ÌeËe×0â”*vÛdŒsÙµÄO/Â~/zœCš „\'¦B×Şù`;·ñ)7z¹ Y…‡½®É­»ü½°Ò[|Píë¹ï½pZ®ùïï’´7Û\"†Æûô=°««_CDÇéÂ\n@XzÓÇc‹ ²½OêÅ“‰éáN˜Ú­\'·nø²XË©\"´¶ô®ıÎÒÚÔÓ¿L	ZMzıÛV¹}ãzaVëŞ»Óc«‰ñéß·áV“Ò»Ósôb¢ÛÓ¸ğë‡uZM7&›Ö˜PĞğïÛüşœBº½ÿ\0Ï|PĞ¯NÃ©òC€$|mi®õ®ø”º´ßç\\@C_GO(j‡¿\\\n´Š×ßÃ\n»µàøbŠkµ~ï¿VkNƒ·¶(SÜŸ•+„\"Ö‘í_§õaj•ş;öÀŠZEE|*ÑÇ§L<îÆ;`µ­Öö¾#$µíò©ùàV‰§ùí†Ğ³Ú°Zêo°ú0¡ÿÑ=úf½îÛğıX«¶ÂJ»èÄ¡Õ­wßåˆä®öÅ\rÖŸÙ…[Ä,ex{aSoÇ¯†dzøä­-v\"Ÿæ1	XEwÿ\0¦S16í×0´ ËOÙıúä­R¥›ğv;P¹uÛùW—’rQ¸\0)5<wŞ5Á-’\0­ÚŒ@$\nÖ‚¹Rk“–@(¯Q„…[$•ƒ¥w­Gjxâd>”­Oá–‡_\'tö#·Li\r\Zö<%]C×¥:ı8­;o â‚Û§RF6´¸V›uş˜Ò¢Áÿ\0kY€¯mBOÏı¼r ilH’–*€‚\Z¾Lêœ¬qëü)¼Öeâ’è–G Íş¾cÆTi ‹áúS;(t·r	æTàkˆ—©ÆË?Ş\0Ğ5ùõËÙr¦ãåŠÓ«O‘ëŠÓGËš\Z;š×«u¡ùà*Š…ª»à-2\r>ÇT:¢øQd¡v8–&*ü–œÍìvÈ\0Zè¬‘Ã^İHÄR’ü^ŞãgdLÑVÅ¶ 5÷È[Leêİ@EèDµs²á&Ëo¥ëp©Bwb7#¥{bCZnUÚ¥iÛlDh\'Ã!TÜ\ZŸLÑ@ ïúñ€‡zÇ¼#ÛjdhL†(JM\"•4Ú¸xYˆÒH}B)µq¹&;6Â¤Ôvü2Á;@qv íôáâV¨AïÚ¸R¿‘$±¥q¦4Øÿ\03€ ¶MwürV‡/·OB\\iÛéÀ…¤ô=1eK\r<h<>,!’Ñş,AÙ-7ü~Xmii\"»ôï‹ ²´¯JÙ-\'ßû0*İÏl6—¿~ü1C€={âBZ¯Rığ]+¡¨Û¾CU ÃîU¼=ÿ\0Ïå€o¸öÂ«v\0~¼PîÔ¯]¾œU±·‡n¿«ê®­{n{øü±µqğÿ\0=¶ÆĞ´÷¯~İ>œl•¦Úï†éZ¡>\0àU´šøchkíÔ1\n¶¿qÛéÂ‡x×¿ßš‘ß·cşŞQKZ´>ßFCGßïöÀ´´ô¯nÿ\0,(jµÛè¦6«NçßÃŞ˜Ú_oojabÕ6¥(<0[]útPÓPşUë‰*ãCŞ»ôñÀ—ÿÒ\Zâ0ôÿ\0‚§Oö9¬‰Ùî4ê)GL6VÚ§_£\r¦Û§á„”;ßüÆ*·£\n]ßcŠ[¿nøhu?³VÜ-@~õÄõH€ÛÉÛ5‡½;~¬ ¥i;m÷b•X¨8ïNkF¯àr6ÂV…–2)ëJdÁgÍo£\'‘W’(*Ã¥9¾	Ìr@Ø¤rÎIîVZa I*<†ûW&\Z-e{ƒÓP7ÃËˆñ>>y$n)BE¡é’qgÖoĞãl)£Jş±…ÚşØm!ÜOá©‹©]ºøàM6OÇé½Oö`*ñ°éL[\0V±êvÿ\0:dK~4úÅ#™5^»Û½3!!Ï2Ù<‡KA‰’­ÈÆ§Ç»ev\\)j7¿¥8)•§?ƒ·rOì`’àq“´ŒR›läfhvmõÛ\n\ZÂİp!ºÓøSuN(¥hš„Œ„‚«î¸-¬)r=ğ³¥D\'NşxbBğH÷Ä±-ã¶Aï]ºí÷aeJ‚wT\Z10 ]Ë€MMp€)²€\ní5å,½·\0nGˆôk=×8Èôä]éÚ˜‰_4‰Ì4’”`İiÖ¾ØII…²ÊÒ7&ùı8IŒ@pcÄ±ğÈÈÒÒŸ\0G G]ÆDÙ_E;˜ÍÓİ†íL²Ç.v»Ô\n”’¿ïˆ•#†ÎËÄ@\n± $n7,beÜ¬,gUÜS`sGª›-6=pƒa+Krof@S{Ô×îÄ”-$}øRôbÉi\'·|¥£×lJÒÛ‘÷×Ê”Øä‚Bß×…’Ó±öÅ-V†€ôÿ\0:b‡\n|om[®ôï«@ĞÓïÅV“½k…Z?/»\0*×ùí„”oNµöÚ]Ô}õıxÚØ\ZP¸ÚÓß†İëÓWv=7Ü÷ÅĞ§ß°Çª´}éÛóùâ–¶=zöÅ\rmòíA¡i¯ëßøáR°øR»ĞaZ¨ï°ÜcÈ!ªT”©ıX«^>?F\0U¯—S’´-4¡§q¿|m{\n\r¶Ûj»Qï„!mA§à1b·˜\rà;b«kQú°+UÚ§ç÷b®©ßü÷ÅZõ>4ïŠiÿÓkíp?†Su¯ËßÛC³íŠ^ üúaµhì:ä­.zuÀÃr;choa‚éZ=iá…!£M¾ã\\’V•~ìn“k(¼ºûaºd\nÆ4¯ã\\)X{»ÇJ¤´Jîö×Äs‘°ÆpGI¹É\nüĞt49OŠ<P6—Ô”ßhóZƒ]÷èO¾„˜ˆÆ_JXôSŞ ô’!MV‚µßøb &ë¸¯¿|!ªEM»İFI¨¸ø}ØX–¶¸tñRİwÜSkÔUvÕ€¶DZîŸçú°ZL\Z¦Ô{â¼*±í±Û-‘	Ş‰2G8çöj6wó04åÕÀ€Ì£·_”,%øƒ:Ö‡üšæî²S‘úR»»‰$Ã±ex×ßå™˜ÆÎv(KØÿ\0L™dBãÔS¦!‹_¯¶—r¡ğZÓ¼=»b†ëµ6«”Ó¦D „H\"•ß×J‰ 6ÿ\0F6Ø½\Z¦•ÃlH_Ènj2,iÅ«ß	Zjµß·† ¥°AC^½)ƒ™GUz\nĞ’l]_¶ØÚ€4m×¿ö`¥!²S‘áö{W®DÚõX{S	äÉ0€£\"ú‘]‡\"vÛ(˜²âNÁØª¾»ú}Ä{ìr¾*`3÷¡.-äU!-]ƒ/ôÄ6øLVå|ğÈ¡d2£¸Æè±œÈ6¦‚4e1·(¤*v\0øÛ\\fO?ª*—1É ÙiF®ŞÙ8Øã2õ%DšæH.m8cjÙ  ‡|¡MØ\r‡A†Ù\0§È“^m-ùŸ)h¶ÛõÃik÷aHıvdÖ\'èN)ıß¾>ä¸W¿Sß\n)uHè}ÁéˆU¤íïãZıy+V»TŠ¿h|÷ïòëˆ¤6H7ğû°uMzü¾œ*Ñ\"Ç¶ÛıÔÂ­R‡§ÓŠ¸Š×ç«]E>ñZ4#™?<mZfê:õé’b±˜SÇéÅ­$‘JvùáVcÛ®>ä4~ãÓn¸Ú[üşXªÒzøB­>>=qbV’>äb†·©Â«H=k‚ĞÖã½Iëã„RÒ\nÔ ~ÚÓTü{àZq§Ë|Vš¦õ¯LSMñ5ÿ\014ÿ\0ÿÔ\\–kñw/±(¦êG¿¶(\\n¸ ´zUu\r*Nû`\\tÅêWj×\n¸ôí_|BV÷ØÔcij§m·%o¦%\\@#çß\0B› ÿ\0k\r³L© ÊÖŸ¿¯IæJ\0ÇˆØÀ{`1‰ˆ*±] _Nâ?QIëQø×+–>æ2Ç½Äğ±İSN”O$È£Ñä8ĞÔo—ã\0\n\'ŒË~©k‡¬7§LÈ8Ò‰äTXÛğÉ4›[Zšíã„5—_ãôàµuHŞ8UÔß¾+M€;uï…’¬Lªwûÿ\0·!Mğ *°\0W®DÙ=»Œ“Z¢Q·î{tÀ[´rÚ Ğx·È‘a²¦Ek|\r“Êf¬ˆ (îA;ö9‡(*l”¡é“QM#IÀ‘Ä\nø|òÑ Ê\"•Ä‹Ê%µ€àWåk=U=Ú_»ò`àiïCÓZvİ?*ßÓÓ®\'un½6®Pîÿ\0<P¯ÔS¾\0× Óš½zø`H[ZP»»—ßííÈ§rìvÄ­.©#ÃÂ¸¡t|^EI	àÄú²$ìÆV„{ÅanÅ$Fæv¥*7Û–QÆ\\Q)ËpT%Şİ}>%ä‘sĞö_g–ØÊS7~”<Â&T•ÇÄ¿Êk–Æ]í°±é+ìà[‡`ÍÅoN§åŒåLrÌÄl®Ö‘\nªT¸&„ûÓ+ñ7¦±”õS´bA\röâjŠôşÜ°›e”&ÊZ~`u»SÛ1äâÎ4j—GWYVPÑĞÖ>Œ“Kj­×JÑÑˆÙ‰¡íRqäˆ‚±½\0x”W…7ùdøM²\\Ğ7×\\¦”\0oPrèFœœ8ërƒ®ÇßúeÈ¥ßíOlod\"e6Â¨+½<iï•m@.id…¸ÔmC¸ËxœÈ®\nzŠæ‡¾cmµz~m!L¸­ßÀcLéi;ĞŸ«$´œ,–Û¡ûñ\n·®ı»ºa´¶[Æµí‚Õ®D]LT7È×ğğıxIE-\'±?~(uv=«×¾*æ¯ÈvûğÒµ]÷ÅW£øïÓh6¯¿ûxœ~«‰VÉêz‘„««÷`µXJŠ{u¦!VÛæ0Ó…»ÿ\0fIŠÒprV¼*}…<}°Ú¸ã¶*Õ@Û¯†(Z)ã¶I5v÷È«]»\r»}İp¡®õ­<<0¡¯˜ùSÓDZ¸ÚiÅ~/—¶ØÚÓD\Z`´RÓÒ¿«¾Zq·ÓòÅiªQ¾Ÿö±Ùi­©â|q*ÿ\0ÿÕWÔPÔ&‡Ã5‡w¾á_³íCµFr,%ËÙhNÛ“]C^»b•ÁF,I^)N»xdm‹T÷É›[¶ø¥Ù ­\Zxøâ–zÓ§S«¼{a%-ı˜ã¹ş¨ZTMÒ‘Nı†gjt§^¸AdÑÃı¬6•ñŠ©Œ‘ñğ«àr¹†\'½/½Òù3pÜÇÑü¸!”†^™\rÒ”-|Äw®fƒnãJ½i¿@2vâáÓn»b\ZÛS»t§ù×E8n6=q¶@*°=¿^E´B	Ÿ*{ü²6İÃak\'o~ÕÉ[Y-A÷ùá,A^²\nSğÀC1%E™Ö¼NÄoî2$3(zÆ ¦¬Ôïãîr&\rÃ6ÊrÜ³ãQÇ =L€a,§¢gg­¢ÁS§÷c5ëOÙÿ\0Ê¥Œƒ²AtÅ5\'cÆZĞV§m°QM+++¨e5S¸aÜ`¥pâ$u8õOÊ«¾ÃîÀNÍe×ñB)\'±m¿â9\0InOU[kØ¥”FˆÊXÑ7®çîÇŠšòb ]¢¤›}İ:d†í1S­p–tº¤à¦4Â«À$7¦äö¦D–%µíâ0Z\ng4rKl†i>Bf¦éO–cgg$	l?ÌşzûT²tdêÔfaF§b¹Ã!˜7\\(ìŒ7Å$‰4=/›“,ÜP±õ\"–XÅÈ*‚¤­	\'ÇıNöi1<+/ØÅ\"È‡í0¨=+òÊÁe„X¢„’ïÒ¸\"(Ç¥GJ×©ÉÛãŠ;Ÿ¥5Ô¾¤Ê„•sĞö¦Jœ˜ã	ş~šäüq”&‹óÈH¸¹ÇZâLb‚U»H$Œo·c‘%Å”Á‚áRY˜şÖÊÃÃ2xv\rÇ€ƒ­O]ü~yc½i@;×ãRÛ}¯×õUU•ˆ1ë·¾BB˜hi¢u$0­wÆ2\rĞ<’ÜÇÂ#°Ç,¶øÂĞM4í!åû5ä½°7ˆ„,Ï(£n´ÜPå±¦3µkKáıÜÌI^|0Ö²÷QÂÃ‘ÜšV›äâÎT€KËµn^¡?=ÇñÉP‘FÚŞÈíé½nï•Ü\n<l?Ï¶E±¥e#¯Aøà+J¼V@4ñÈXİ)•ãĞ×Â™1\"••¦ÛŸé’N¥Olz¢›©ı?®6´Ñ\"¦‡ßßå‰VÅ;wé†Õ°}¾CÀàW\nõğÅV’Sf‘Òƒ$œ»õ8P¶»{x÷ÆÖœzƒMÏlU­ºø«\\¼6ßlPÑaBİNş#Û+Kß†\ZCDíà<0¡Üªj>ï´áRkßîÁi¦÷GÏM:‡å¾ÿ\0N+Muöë¿ËZkû¼-q ñş˜’šqçıqZ[M¿V(¥»oôŠá¶/ÿÖdjîKxô¨ıY¦2}DßSbÀ·ÂÀıñìÇÅê§ÄÀñØàâ.1;·Å~ûd®Ñe²Â”¦¦íã¶H«U=z)hõıXRÕ+·ëÆ•Ş8UÂ„øœUß¯	W}«Uâ1Wó¦)wãáLUc%E|:ä­ ©2HÂ‚³è®õÉÉÁÊ\nÈÙ=2¼‚Ö­\"Ôcˆ1ô«Ë©Ütÿ\0c—b%†xƒıd­”Ö‘n¸Åh®Àwß};õ>Ø²áhÿ\0¶1E8x±P¼·Q‰n„;Ö¿NV[â­$cêõ\"­û$ˆ>¦ÉÆâ`A ‚Cú²à\\)ho}»áb¸0§¿lÂàv¯ëÅ’ğ¥ªFHªÄªÄ+7Ğ´ÀKlcÑkÊ€Ö•¡çûX±!0µ¾¸2¤ĞšÜdvØôDw›‡š”ˆSùv\\¯`@W›S@U9$µ¡¡¨*{d(ß“ÂŠ7\'®ûííÛ	\rà«ÆX0+PkğädT“ãÌ$,ÿ\0Ş”é¹­j9Wö©‰°ë3_O>v²š~ ±~ oÓìåFD8Ğ‰p\"U…£BÒ¤\ZÓ.„­Ê…›õQäH–ÄÇK@Ë?1Te\n>ü§,©ÄÔ\Z\"‘ÚZÇF7ä(Ì{m×*2.4²H›@ßÏulãÓ`ÉÃˆ\ráÜ°Ä.VFcç!í¯Q¨D\\XÌ©¥>K“2mÉˆ«/u)¤–µãAğ7¡ı¬Ëœ\0—ÅpğÊYw=*}òd9r€D™$P\\š³´Œ`š¼•}e(EĞä¢œQÛu‘”b\ZƒßYJÓc¤DöÕ‰ÿ\0|G/bl®éÁ\Z¢%¸ô.Ómî-Œ7Ø==é‰6Xê\'Õ(NB³(q™!¶\nC¯Ñ“¾Œ×ô¨ÀÅRvz¨­*>y	˜L€ÑAjXÒ§qË¨ï”O\'\ZS%Mä†G‘ğx¨}± †b$H5N6“$w¯Z‘–Àì°KŠ;¥ëªÃÈúŠÀÒ„õıYw‚Y9/æ9 ø R´ß®F1 ¶DilCÉz{ûæ@\rS*-V$Ÿ£&\Zˆ·{÷ğÚG^I]€¥ON˜E(•j½2ƒ³5Ì¼k]±U£•M:ã….,I¥EÏ|(ltß§ÕˆC­8\ZÔÉïƒ’]Oj×\n»ŞŸøÚ¶õ÷ |ğR¬2RŸÓ¶H‚·–ÿ\0?!ŠŞCøıŞÙ$4O~ışx«@Jôş89%Øi4÷öñÂHcM\ZPmÛU¤ƒZî?¶˜P×}öşœPàxÓjv®>I[RvwÁkKªiş}±)¯q]ö­.­hß4Şûm¿Sı0Zi¡Ò|~X­4O~çïü1¥j‡BÓJ×ñŞ¸Ú­\"¾;í ÿ×kvÂ€›PöÍ4âú,¡hµiòîzqÈÓQ¢ª‚®>2GğÇÉ;rUä7ğí–0§Wç+ªk’VÉñíŠ-×Ç¦´·Û\n]^äãjâ~şş•u*íÇ¸=qWöğÚF»S»o»®¥qWÆU3M:’i¿¾$õeÄ­?‰ád¬EJõ9ÅE„9ñtcMlå«0!ëB)¸Ì±![9|7º\nê.R·¦ŸØÒ¹t%AÅËNÁÀ©ë½:dÜ~\nwa÷!VÖ»õ§\\X´*>ƒ.S¿ö,Â¢‘×õíôd[b‰I+AZõß+1rc%ÿ\0U2·Â­OêÀ\'Jq‰ Ş’2‘N=~Œ´JÃ‹,tTÀ=;“_\nà\Z½Éğ[0•©ĞPÒ£`<—q¶\"“!K u\rF	\"¢¸•‰İ8­‰@C«•Z…\'1É•¹t#N€x©ÜW%ÃŞ «\r«µzöÄÚ…]†àÿ\0ŸÏ#mˆ˜e(ÁºÓøwÊä‹	Ô34ñ	[í\Z‚}Å‡Q4Œ«p/Åêé±üJr™Üy\rÌğºıBh—å¶f—süH\n“¸ùåÅÊGÚO,p´Cáb~&§*•n.Hm1‰ÛÒ!7éS±ª÷ûò‚wq$7Ù#Õ/c•§÷‹^^ß,² »-6#oé@ÚŞIJîNûõ®LÆÜœ˜„–MrÒIÏ`ÄRƒÃ\02†0)ò,jzãÉR!eRM@8i1õZø!‘ú¡`MÀM\"sªso¡P¬€Ñ)R–Uâ:éë:uW”-¿F\'Ó4+^‹ï–\rÚãêØÿ\0B÷Ô“ŒfŠzWÇ\røT7CŞÇGVãÄ×}Èñ(NÛ1Kd7¶Û›åOìÄ-7†6ãñÀwD£{*-Á2ğr¹\r˜˜l†™ø\\µ<|0ßFè‹äƒ¿EŞŠü‚Ws„\Z-Øö;©‹N7aïÓ§LÎ‰\ryê–nõxÇFúT`Ÿzpw*˜dv Ùˆ!´À—H;“á€*EÖ¿NNÚHhT”¶P3M’A!¤õ§€9^P)·LdºYÄ6eÚ+\\¦ †Î\Z]g—£´?NLš4€¸Z®K·úq´6«Üÿ\0˜ÈÚ¶FçñÃjê\Zô©ûşü\n´Ğo_§\rÚTúññÛ&•2XÔ±ş¸X¸Jöı±Zqì?íÅ]^Õé¸Ú´w·¹éş¶(hş˜U¢E¦EZ5å¿nù$5Æ¤_§¦øşŠwÿ\0=°šw\ZnË\"šqv¯Ï\r­4Tıİÿ\0†6´ß\ZíßïÁi¦éE÷÷À–‰\'l6…„\Zõøchh–xïOS`~0Zi¿L<~ü.q©ñÿ\0=°Úÿ\0ÿĞD[R:÷9¨½ŸEãWD ¡§\\…[Q*„ø¤d¨0^o|m\rÔí¢›ğÃjêô§L6†ùmiÕéN£$®ìi„+¼D!ºTŒ*î¾ÕÃjêW[qûÎPĞNwCòÃÄ–©Û¡Â<ÕÛV½|1	E$ñ5\0JzŸ—R`Ih07ıºïM’WçÔ5	¯l—•à\n!.ı,qIÁ€ÜìË|[;¶‰A&™$VøÅ\0ú32$8¹bB>y7´)×üöÂÆœ+Q÷bŠküöÅT‘Jş‚½^”údi°Im\"‚1 ;¹ú+•Ì90’Ó‰›`k¿já€ Ç!÷÷ËH\nª6Øì\rpÛ»\nFGªmH¹¯ˆì2ÀÒK@×û=ğ±T„’iĞı†B\\›±óUz÷¯l3U\0‡}èİ26Ø´`Ö¤ì=ò¶JPŠR”Yd›hÿ\0´\röFùLå[¸Z­‡fG;‡Ùê=ò²mÃ»_Öi	ûL\'Ú˜Ld6	MôÑÆÅÑXaßsöÉË—;	QZ,+J¼WÄĞÓ!1M\Z¸ğ€Š¹NÔPÀËÚÛ!M8à,\ZH\\P•ï^ùh.Ì4Ç¯‡A’HR?ğÉ3ltéïŠ¯‰€a^˜ìd6Ol(a+Ëíu¦ûW(İÖfçh˜neY’&ø•^”ñÉğƒ™c$¡î¡–7y?aı°ã-¸äJ\0ZJ‚~öå’-“4õ©V«/ÚSÔöÌ~N  œ*ÊÜvû>‘\rÃ“J,v÷ñÉÀŠÒ¸Y;‘SU4È­)ÊÌÀïAN·ÁL¢))¹f@ÁZkL²&Ü¾‰CÇ#’{{æP!Æ1$¦6VW”ƒ©\Z}šo˜¹2Ñˆ½×WpC/¼zù(@“:æ†gY*è>C,ªA£¸S ’6éNù>Œ8S4ô–*8«v#øæ4²vfhsC}a,ÚH”W°=úåÜ<TÄÈE—.fQ‰4§Ï	!2Oí#HFİ•ä\ZÓéÌi›Lü–z‚0hìÕÃvÎ­\ryx¶à^LıG@2È‹A—@›ùZ1øĞ·Ã¦¿İËG2D)!YIş¸l/é±˜m¾Ş5ÊÀmP2wû¿†N˜’°‘_Ÿ†;–ûøb‡3\Zô ëï÷b®åÛÇn¸…¶‹ÿ\0iÂ­×ğÅ\rZâ®ê\0ÿ\020%u:Ôtí‚Âiµ4ş?<mW½:ï“[mø~¬QM¸?†M.\0”§§\nÓT§Ëß¥­@>[N¸-iÀÛšÒ¸¢–Ó·Jøa&Öœ¾õëô`M.ãN›ciÇ¥i±Şúdà@¨\'\r«ÿÑªĞScšrú\r.©?Û…ß/ìÂJÓa¼6ÀŠo–ÿ\0?Õ€„Rşuéí1¦ùí·Òp¦ùW®QNåµvã’Zlv®İğÚ)p5Áhv­Š—Zûb…ÂÏñÅ\r+·ÓŠµ‡É.=Ï¾*´ƒ¿ã†Ù[]=ÉÂRÕm\r`v=0&—*KìHûä0®ëKi‰fCº”Ë!3İ±¥7z{FªÑïRj½òèe²Ç&ä¦Ëğ«Ğ{eÂN?†B›)kC×Y‹T¥{í\\’©ß¾@·Ó\Z|±d¹]‡CO“!*mMw?qñÀÈQÀü=?—ç‘²¶!aÛjí„0XÒV´z×ß$TÏãá’kp\"¿<P©¿r}°a]IaP}ßr²lw4r²[\0VJ‡ç-¡»wíd›Z@ê©*°VmÉğí˜ò6áå˜6‚x0£©nµ=2xÍ	-ä±HÜ¨‚›7 Élvo! ‘ŞKë\\¼”\0€t§Lº\"ƒ±Å€©mpñ)àÜIê}±#td€—5Ş²™*ËÈWqÒ£ç‚¶cÁ²³:Î¥€¥ãÜ\0•-Í+ö6ÍkÆA?¯R$²”ÉÉm~îÙ$£-ïLL	\'µiáÓ+1qç‹‰1[òì’mD<€®\nÚœC†…\"¦•$‹Öz„ Tã+\r1‰„ Ôı^ã÷d´rĞŠøe”KGwú¢½¦™¥ªT0Ù©×&\"ˆD\rÖ1%iIâ¤`+¹ñÃÅL‡>µš\"ß¹”IµzÙ.$€z¸È‘R¿\\¶9,`ÔÌv8K0«x‰)Åˆ©\'zå úšñ)?<œÜ _Éˆv5²¥œlîª(¨ ß#3H‘ ™ê!´¢\0­J±§\\¢<ÜL\"åeŒ4m$ä\ZŠšÔôÌñ q‰%$g\0+A]²M¤5\'š†ÔÓKf\"4‹k™cB«@OÂ=ë&#š‹Ù´Ê&}«Pw­)„eáÙ ”­ÁO„Ğ©ëüs$náÈp‘Rê.ğÇ*Œz×çŒ(³9,!I/r+ã–lÃuÆ¿«¾®õŒáA?p8XHÒƒÈÆ€œ°\ZY-i¨â2HÍğŠĞ\0~y\\£½¹óPø“@M(zı=r§-i4ÇÑf×§\nº§Â´Üüò7ºµ_şVœ+QQS½>X¤ÜÓzø`´Ò¢ÆkS÷dx™¶•ÛmÎkMĞ÷5ğùâšp]ÿ\0Ìà´Savş\'ÓŠ÷k¿ôÅ4â	$“÷â\n)®Ÿ:oòÆÖœAşmih{Ø„5CáJu’šnŸO€ù`%iÔ\0oôœSMu\0ÿ\0ø.ÕÕ­;¿OÓ…VÖ Ÿ×÷âÿÒO–ôíš€ú7]ë\\m\\şø«|‡cŠ)Àœ\nØqß§CôaRò$>’0l)rÈú|°Z]Ê§Äá¦ùTÿ\0y¢—éüpÚ)±í\\EªêŠábİ~üJµ¿~ñ+¾ü*İ~ìm\r¿3…-ß ÷À\nZ¡ê?Ìd­Z&‚½0Úim}ûâ•èÜjv+Ş»ä¶$Z\ZkU˜‚¢à|;qÈY‹t2póAİéMÆ§¶Æ=vÉÃ)äÈN3Ù%º¶h¨*H\" ïüs.¶¬˜é@;S©ËƒˆCAºìRûäY8ıp«¹\r+nõ<{÷ú0\0‘\'4­;b›kcá\\(·Ss¶ßv=Ç­|{øâ´Øê}°$\"HêÃ¡È[Ú±¥zıñ!ED>\ZwIoŠ¼Lk·O‰d‹!<AİzR¹M5˜²[@„°!E	ÌiŸ0©(j%GP2…}¼rQmÀxNÌp‹Ä÷ÌvvíÓqĞ`(p¨ÛñÅW£<¹‰©Ï‘í¶D˜Õ#ø$ª`7#+;8¶bT¦³–5«.Ô¨nØ‰3PJ£¦Ù`-à¯6s¿ãˆ˜câÅIÃÆ	5\0wéß&\r³dú’ÍoÁ† €†öãÃŒ¬©Y\\‡<d}ÔÕÛÉH3Ë\nä›¥ô%ÒXXÂ„µÊÍÓ‚pÊˆ*¶ş«3İ/Â¬­U;“A€‘TÂt*%+‚	GïÈoŒ•v}ë’3œÙÌrQf”H§ÔåÄÔl6\0É;±T©4vì2©Jq&&­/ÖŞ?MØé^¢½²XÏ©ÉÓ}Wüæ0’\\â•-Ò¿íæyœÁi¥£KEâÇ­w=Ñ0VoRcÎVÙ|;û`Œh1‚¤vPI«0ëã€’Ì%–@ÿ\09³¦mŠ•f\"2(ÔZ“Y‚ñW‘f2ºûN¨ø¡<|rP0Ë{)­·(\0Ùòv	¶áV•ËP¹ŸìÌ˜É¦P½Ô\nĞå–ã˜®@û·8rSiE65®JšÌĞìÇ¹Ûè¦L8³$¬$×Üak%tmIªiá‰LöÈîÚUåvzÇ1hnßˆaÄ‚¥=úì0îÅ øÚÒêÓzv-4ßhiZo¾<IÛ‰3@kâr;6\0#Ò÷ßÃ+ã[[Àn$®ªÓ§M±VL(j•5üqV©ôöÂ®ñÀ®Ú”ÿ\01Šµ¸5è1Zoz\nôÅii¡#¿c†Öš4úzœmiªĞ{o¶*ÕiJï¾Ø«DŠûx\n´ıÆ-ÛzìGC…ÿÓN€Tøf™ô6·şÌ)p­?mW€+SôdNìK‹V´ùaM\nR¤ü±6–À\"”©^¿<X¸œ˜E6ôÅH\\+¹}ş,(¦Ã~}ÿ\0¦!ß*\rÿ\0miºƒì;aC«á×paãQã­7S×Zk|\nîõ®şıp«]wë¥ªS¦ôß\n\\Xñğ§LQM¹6ôéãZ`)§M #ü“¾ı2 ,b”êÚXKr¡Q¸¥\rÉ®[mÈ\0Öéˆ§ñùš‡8Ò‘©ï·á’j+€¨¡ë€²\r1 ’|:aAk–ûtv[[îFçm¿ÛÅiºıçîú02¥Õ¨ıg‡m·lUÃpIúqH…6ÿ\0:à,Â ¯Ù¡?çá‘f\nôcôÖ¾Ø„Tüür™9D-@øFİò³DFv§O®H)Ö›tÉ§ĞJ×±ë×(uÙñÙµ]NEtôr+¿.¦°ÓÆ””(åB)NµÌ‹v$¶Tˆ\n¯@+Nd¾0¬7ûG¦X\nÉk#ÁI=€ür&tÀäšgceubèG`Ç|ªrÂÍ–\'‘M§‘}#¯5ãö{ı\Z·7a »†HÙO\Z)èHß,Œ¦)‰YcŠ\Z»{úğsi1$ì•ê4o‰zR>YlÜ	[ÄfC‰H€Œ?!W ëã‘2Ş˜Şô­2“É7­ø	Ù„¤:§zmË!X[pÆ•èGl¢qÚİv|`ú‚_<—Vò2r%C¾!EÊ„c!nYK\0xSmÎ¦5ÕSë1Ã7%­+·ûˆXp)ï($«äå‡ÒäÔ`rGm¦ÇÉ”zùL‹@‘Ê¢’ÉªË$Ç€ \'|Ë0p®I€g*SC‘	¡kÓ‘e_Ù=~CäÄÕ&1² §¿1¤âHiˆGÆ  tÀn#Û•ÜïĞÔöÉQAPä!REözàa29RçN\r4 AZ\rêNLd!¶9lÑHnÂÔaCàs:‰r\0RHI \rq—\0âJMÄªÌõn¸	4œq³ºÙ¢àO³Ò¸D­\\uÉAˆ§·a“Ä!À±é×P–Š¢h„Ã÷d÷ØWµr©9ØcD_$Ü«r>Ô>4ÊƒC€®Ä{uÀJ@UOë‘%\rš“_¿d¼\\)\\M6ë‘*Ñ\'¸Øaä®­1W†½<q\nïO*ÕOÏ~˜¸ûR›œ*Ñ on¸UÛÿ\0Ø®ß<*´×éëŠ\Z¨ì7ë…Z>ëĞb…»×­VJÒ~ş›ıØ¡ª’j>Œ(ZÇ¨nŸ~(ÿÔF£ éšr7}_óùâP¸xuÀ‡r©ÈÒÓuş¹%¦«÷õ®%Wo¿l(o–ß.Ø­7^=q\'t:£ğëıq²­ƒ×çJb‚ØnõÆÑM†SñÀV›¯†6Š]ËzâŠn§ukß´7„¡Àí×WWoÕ$»•~ş˜ÚÓ\\¨j{öÂ´±véî1¾æAµVê¤îWeIm”Eh¤oòÁºJ®`•‘BüJÄ…&<rØL9`…-CO+_Qò”İòÌyI“Q©XH›¯ñÌç…¿(lÔ×õaZh\nŸnØ…°»ïĞ6Ë…ÄßÄaVÍ*ëáí\\¬7~)¦ş\"kŞ›`H\r‚OM»tñÄ¤/_§0½jGOóï‘-Z&ëÜœnŠ61µNùIm\n¨ùıùPS[+¹H\n¤+w=2‰H8¹2Ä-™e‚©½OÚÄQLH–ëìmŞY@+Ë—_–	\ZsL\0½µ³´„s‹ì«Üm¹ÈDÈ—IÌì“\Z›ŒÈ·``eV…vé‘‘Ù„Å†Elñ­ºÏ\Z&â‡ç”U—S.ÉYod§Å¿á¶HÁ¬á\rÃ<U>¦çöIÂbVp=\Z7º@Péà!D;6‘ÙË³§%ğ«€ØA”ãÉ-Õô£EŠòû;vÉC%9š]O{–Êa\'µ¡Ş™•‚˜È¶¤‚XÅ\rG¾OŒ‚\n’Ë\"|jxĞÔı™\"I¬Ú­ÅE	Ûj÷À1†®ˆ›_0<e–e)5€®BX-¦x#.^†¦×)/8À•à@ë‚8-#\0‰A	o5À¯Ã_…FÔùe¤ØÔGôY%”?T€¹S¯zœÁ”­ÁË.9$z´ÒİÏÄ—¨=)™XGîn,b1 —LoR+ü¾ç2íŸğİ¬LL¤Šì\0=¼&$òbeŞ˜E{lç‚ìÇu“¥^™T¢X×š1I…0=ë±Q¦~‹ÚñH#\rh=±ØŒJ<‰©ûò`6ÓAÊ½ÏL$¢œ/š$j°H6+S^ır  ã¤ÚÍÊË5|CÛ¦ea¼´\nRHîkíÛ2kg•Êæ£¿†FtºIÀ¯N»×\0\06JFNX™äUèÓ¨ÄËk`1\\…¦éĞ‘À€ÌGÛëÓÃ1bçŒ1ˆä‰‡Nâê(ßeºôÊå–Í…Ú©{F€T‘¶ı©ß$\r„Ø·lM¨\r‚Aé‰W·Q·ÇW‚†øÕïµ)Š[®C¶®İ½ñê®ßüşìB\\ÌüñZZŸÃij§qá…Z®õÛuiúıñVª:t?!I¯õÂ­BŞC¶ŞŞÇ…¤ĞÓ¥0…Z\\\Z%\rßÇñÅ´“óúk„!ÿÕ^İiŸGl7İ†‘KÛ}ñ*Ø>pS¹vÆÖSã¿¾pcòÃh§(zíáŠiD¼AÍ„dÃ:4ßÖàVâÒ\nâbXp­mVÙvZµ;¶Œ°¡ŞÒê°7f¦\'MÃS´âO#@>ÿ\0£Œ•¥ƒV‹™ø<>Z‘}oPŠZäxJ8Òd“t`~Y\rÃcliÜ¶ÛRî[u¯¶SUNÇ´Ôú)k¿ãL—×Û\r«GoA-<Jö=°H-,‘h´ØmÓ+d\nà„ÉYË\ZAudÉSJ~¹›Œ\'¨BÍw:{å¶ã˜*Esá^Ş9N›a‰tÆ+†9O½7x#’âUª×p>!–‰^ì%\06Pë¿İÓq–¸ÍÒ›‘··\\	§oß¿6´¸ıõÛ\'\n|ü)\\<G|•µ­0Àª‡ÃzvÊÛB&& ëÚ¹\\›B2\0ƒ_‡m²™1dv1Ê-Í$jq>ç1IİÔæ#‰Yl¢g2±¡Z6E¬å S•­í‡ÁVzPĞaá$©Ÿ4úF{—y\0vî[\0)Ùaˆ[´³(µ#J“<‚<Ó‹\rá•dºB@¯Áır©NÜÚ¡!Q(Ùf´EôÂˆ¨(s¹ùb-ÆŒfwúéèÂ©!¿€9a-§‹ù«]•j¨ŞêvıXAd=–Œ*=¶øñn§İ$e›4cŠ	‚@	î|µ!cøQQÈáŠ±\r©YQóÊHi”EXú0<rè«|{T‘’÷¹3}E\rªÃp‘8‚vS×\'¶í4¤eAŒÌ\"wŠî:ƒıs.7ÕÛÆÀİ45%–»å‘Ÿz‘j&\'SR(¹10´€rVe“úªµ¯SŞ;å9=J`FM© µ!Iä:“ZåQÄm¬b£eç5e¯ïBwí—œm¶\nIœô4t@iœŠ¤cÛ¾X)6µf\0uÜŠœJA%RŞ)U‰äªAàMW#(‚ÌHƒ¹O’ZKª¶ûv9‰¶-ÑÛe­*ÆœÜñAß~Øo JûY[ˆÄ\n+Wa·Î™d0mÇˆ;%Ò˜ÆÊjAëôåñ´d”\ZFbMjO]ÿ\0°Y,©×Äíã’j%È9¸sÓgf“ô´ŒÛ\"­µHÌ3ÅnØDK­´µ‰½YJîGa‚Y‰Ø0ˆåõ\"=_Š«Ğtí¶<,İê9êk^ØxB)­ÎÌjqä•¢¤m¸ì=°ì‡rß®øV›èğñÈ¥wjØ\n[ïã^˜”:´ùøâ®wb­ØàK‰ö¥0«E©ãôuûğ’´¶ëáŠ­-½zûâRâI4êzÔuÂÅªíAø{âaj‘úıñ*ÕOnıGûXiÄı?<(YÊ¿Ã‹h‘ÛzáU¥»v=…µ=ÑôïˆAÿÖ	Sı¹¦º}!Â›â\n®ß.˜pÓ!	ÉFº²­¶A„½-V$‘Ğÿ\0f]qb‘1I2Š•ú2²:ïDÇ?/¶)LLW°¯j“ã‘bÍnü¹Sæ2|a°H(ËdïZ(_ôäã”1 /Ñ³~;dÎ`Ã€w¶të…Q_ÃŒ„w¨›+šŸƒ»õäüX ãkês­~óFX£Ã-ú\r¸šxöÇŒ2à(‹c<F£càk•ÌØ#µÉnË¨ë”Àã\nÉ0o>ŒƒY*Uˆ©-‹ußo§Nèq=k×\nÓUöÅ]ôvÂ–«Û¥;á÷+ºŠœ*êq?¨àª”´©u÷`g\rëV‹ö©»‘Üe‘nˆÙ¯¨İ)Şºå‡%pe\r´KC!n|r‰L¦R=^Hlª¿\nÔ>Œ¬	[\rÚIpmäø”üuÌøX÷1È\"PL8·\\¸8„VÍ‚{ÿ\0f$¨lƒı¸óM4§ÄõğÄ¨]PÏÇaºøöÀªƒ§ğÈèª¢’+Û\"K`˜úŠ²¢Ø‘\0H©î2™d¶b$‰-\Z±êNÃ­6ÌGQ–É?Ä™ıZÎeõµûñük‘\\éÂñ\'ôñ!o´èìayä–9\n£Fû×·Ã’	Ù»sˆ(]GêI0§Ûfëîró°w2¡I§éŞ‚ây÷4<O|Å”İ>|üFšš[õ.ÓO»$)1Œ\rRem‹Tÿ\0”AË.ƒ“¦\0p³q56¦ôÆÕL°ÚŸNÙ•6¾¥@\0òìË4˜éó†KyÉ©İA=|@ÊrpóÃq(¢ö…xÃğZ<GbÙ2ÑõØÍÄò\\8ÅÂŸ²Oá™@\0Ì \"6AÍÁH P×ìå¢ÜˆÙS[¤`PÇ¸èr\\+Ãæ¡4•<BĞşœ²,”S Ÿ²{‘BªÌTPı“ĞäLRåæ\Zú±3bZ’Ñbp9×Üxá	u‹wP\Z©î6ßß-„ïf¼˜ú Ï]ºxeî!{}Ÿ¶®Š\'\0*ÃÄä¨[l\"	¤Áîf²U‰(Ñ•øM(kÔ×1„D÷ş&éPèº½–uU‘¾^û²øc“Dç²¸®ÙpqIXXöÉ©¤øtµÛ«ã¾İ0ªôRhrz‰mÆ;™¡Û¨aSï¹¯˜¹;jÙ¹&,iÛ`~G$\"\ZSãØS\r+|«òïôcIn»q=°8İ=†6­€vçL–úV¸Õ¦)[P~·ÛéÂ­r­Híß·Ê‡Çß%îCA°+¹>ØU®G§Zõ¦!Zf?OO»\0Uœ¾â6Éy!Äíòíˆ*¶µ¡¯ôÅKA«NÄP×!_F\ZV‹şşØ±X_ßúäÑnıO÷Å9V»×\n³mOÇ\nÿ×	øæ—›é-ƒ€! Ãšoo§7Oé‚ÕÔ^>õu<~ü*íúâ®v=°á^xïÛ1¥Áèr4‚¨ë„”S`±WtşÜ­Ğˆöß\n\Zâ‡ªñ[-Ñk°¯±vÇÚ˜”.h=°Ò)Àı«¾ËZp#å]Zm÷aVÅîC«_–®åQS×\0Z[@A÷ë’,­JxAMÅvÛ¾*g	ÔPG\Zn¸™wS\"Rë¹š…Û’öMıó#°ØÈ.Ø“ÀqSÕFã/6¹dCšÔøşhi+5­zöÉ5–Á¡éóúpMO¸ÆÕİ© ş}±dc·MğaU\0ù‘- \"#Ü¤í•àÒ¾FVY#¬ƒ™ äŞF]¹´åªİ‘°!T(pc_s©kf¯.AOµ}òQL÷AÚÆOL$Kà<pŞá·q‹kF´äâvØ!IO|$VZ!9’îê\r„Œ#;br°uñÇtõ*Yko+;Åê)Ö†¸;3ixÅÂ›V²¼c4(ç÷ŠINü\'•0xf<œhégDÄÉY4›)½[hc„-c‘–ÿ\0X¾@È†£ª˜;K$¥üŞ%×—ìf¶il%×­O5>+¶J9H;¹õÓŒ« c²¬P^ıZu`TL)ß/Å‡me(£¯kcK`}`Ù”¡îBõ\\;8ÿ\0•–CSôQsëk/’Ò“í	VªH÷„cî*4•`J|?Ív£;ÜÚ\n¨6?©#¸ß+`€„·õ$¶úCºzÏ \rZ²Wp2ó—£±¨Âh×Õ\"M”Ô–Ffœ˜ÈÖÈ	*½;u¦dÅ½F¬MrL]P\r=÷§†!%NIH;},˜\rr4ˆ´Ô\\¼E;7úr1u@\"Nò¸w=úÓ|‰˜6İ´Æ G|–:–ís•µˆ®ıs(8R+jµ5íÛå„±FØ++z«±@wÊ2—/z¨Ïê#E]ı÷ÉÆ©†[A˜wÿ\01–‡RR$óğÂÒKDšïÓÇÇ$À¸\0kMñcMñ S§Ï²á(‹@UÔîO†WXstâŠt^«Ò«ÔÓ±Ì@é-¯\nd˜/&ı²6šn„w 6éŠipéÖ§*êÅÅ+øãJ´¹ú€ÃJÑo\ZÓ¶*×-½ûáC¹WèğÆ•®]ª)^±[k™ñÜíştÆ•Ü«Ûoï\\OjÓÃ$Ui;|_«\0V‹~;SçŠ†™‡sÓúa¤ZÒÔ§Joı¸Ò´Æ½)òÉ!abw=Ol@cm\'Äş¼@AZIé]†4Z”¯ß…IßÀ§úah±¥:Ó¦!ÿĞ\0(iJo•}ı±­İI>ÿ\0v*Ø>ø¡º×c×¾*êíÓçLU½ñCºÿ\0\nî+ôâ¶îMNø­•ãnƒ¸¿®¯Ñ¿|i[®Şø×z]·é$·\\Pâ®\r–›\'\n]ü1K$!ÕÀ­ƒ\\m¿¶*Øo}½ğR»–ßK’ÓUû½°\0®Ø|ûb®Î¥ªlGğÆ­*SÀ’!.*\"(ÊŠCuIQAÔf|%²Î;¨6äÔ÷¦X\ZÊÃCúÎŒiªTü±µ¦éÓzT¨:t8Úiu> {~ÚDW/êÿ\0?á€–`*§á-¡^?ó=wÊ‹hD­jAè<r	L¬#¹ä²Äİé¶bää\\\\Ò\"ÄòG9ş×`\\¤.¶@Q[,Ü–€Õ\r+Nƒüœ˜	Œh«ÛFg‰åQSNÃşiÊå-ÚòKu_IØoZnDÃŠù©²Í*T!*NÄ‘Ôíß&(2¡\"K£©å_Ç\'m±<–Tã|,œesğ–=<|;cV‹Óuª¹?©ğrÀSıBq¶FŸŒs—¥½^ÚŞí’DhŒôøÄ$‘@6§ 2${—K’P>öÆ-vr¸S·¶f@»¬{€©i xı&bA_oò3Ù†XÑ´ÒİÚ0c‘¡Üî(vÊH·`×Á:¿#G\'v©\0|²$±œªz„Q³*+Ò¦ŒOğÉFEÈÁ\"7¤µ´Û†f\n…‚÷ñ®d¡Êñ£Ô©=œê¤úf£n™1”w³‰ê„pàV´ú2àAKFÛ™ÜÕO\\<t˜Z&HÖ?‰h@îzårÈWa°T†æ8Õ”ĞS ÈK%eJînÎY^Ù™òh™½”\n×§_é–[AŠÂ)ôn+†ØKâ¹xPíÜÕ‚Q›(e1_s*IFU§bNF!·$Ä·Búõ¥Ñ–‡A®½:u%mtà¤\rûm€•àµÈ*zWñÀÎ1\nª¥yTuéí¾D–ØÂ¹¢#´fˆ?0\Z(ÊAtäC\r‹GE$t.YÏ_l¤ÎÏ&ñ\ZU	MM.éòÁkKKxn{áU†B)ß\r!¯U‡ë§ˆ®\ZEµÌı#[aÎÛûõÆ•ÅÎçß¯‡Ó€+¹¶ÿ\0çî0Ò\'oUÅ«AßÛøâ–‹S}÷ïşgu~ããZï½>}wÂ†‹Ç¦4­sûºí†‘k9í¾â»ş¼(k•*äøÿ\0\\Uo-ÇÏ\n-o1ĞïÚ¸„‹\nĞ|·ÃlK\\¶ùâ«Cšÿ\04†¹½|1AZÎ:ş<4ÆßÿÑ-\r\\Ó—Ò×p­×¿]º{â†ë·êÈ«eªkOl(uM?¨À­×ß\r¡ÕñÈÒ·Ëæ´İ@ùwÅ]Sşßl(n½¼{bU®İwğÁJÙ8PØ\'úb®åóú0VËN¯İŠ·Ê¿ÃS`ş8°øJAã[õ;T‡Ø««Û·|\\O\\miÕéÛÀaCu­ÌaVºuÈ­“ÛÚ¸ªÇ^KÇ¶K RëÛnJ} )Ol¿÷¦ÁºRûvùfU´°ƒÓ¿L’)¾;xS¤øœm<-ñûı¼pZx[ãÓ¸Ál„Wq>ÿ\0<ÈEz¡úr$²DF½êÊÉm9´¶·™9?Û\"€×˜s‘g.IDì›[Ê-‘`·4¡ø‰é”‘{—\nqã<RE®­yl}8‚¬uÙëî*9b İÇ:hOsõYPk×!ÿ\0{,Ş±(ÁáŒŠ5±Éş\\ë|,–Ğ:S²ña_uÈ[sjüò–H•xµ\r:Ugn6®z\ZJûíö2<çµËHíıïú\\m}CJ¹«Ã|!vûKÈ­?•Äo¾KŠc§¥|l°ÚPãæq©ÛhWÑN’ÛÜCr¨ywö#ü¬<wµ2É­ÆcRL_æ¯¹ÒõË¶†F¤ ü·8¢?œŒzœQå)äGÚi¯Ä¥Å¤>Ò­(Twÿ\0[*œ‡N\'. ]Æy\"“¼–bÿ\0Ñ%}8á%§b¤}¥ËÅ‘ü×`#?ÇîøÕ\ZÒÙ+,VÏĞÓ›†;ıŸİÓ\"d{ØŒ’;F_æ±û½)ä&V*µ& töËã–¶× \r•ì¬ìR.3J´\"¡€§Åş¶Fs$µfË2n!\Z–WÕ\02ÓcÊ›¯‰Ç9º:k&*Pv\nÕ®Ì,3¯©-£õkuT^òR»˜•òr<C^•—’Ám\Z¤3FI©\"”§eÃ’YbŒ¦M‚:¢ñ*®Á‰ğ¨ Ë\"ä\r>è{€$£R®ÿ\0µJWèÉDiºmüÕŠ¯°s–™lÙÅ²\ZæéLì65é\\³TN†\\z„ñR{{a”øvI*wVŠŸ·jñ#ğqæ%€; [áú?†d‡\Z[)¾ır`µÖÆŸF8ãĞWs…£½((ı>ÏÖŸìƒĞ÷Ìl¹8\\¼ÅYDÏg\nŠîvT3äÊ!èÑ˜*“ÚŸÇ28œ²2dbCƒì®S9–ñ\r—Ë	€ŒUGsNùÌUÜ‰şÿ\0é‘\nî{áTËìkÓ§Ó†•¦pOãôdÓ<dÔTWÃh°°”\"ƒíx{aJÍëSµvÛ ÉXb¼TíÛ¦_¾ø¥¾_-ÅNk•6éî:b®-Z\nÆ˜©w/ó8«ES]ñAj¾øJ­-Cáâ>}ğÒ\Z.+OÇ¨ÅV–µúpÚó$W¿A!izt=zaCL{•û±ckIØßúá¦6Ùj\nW¯ê¦\Z[[Èõ§¶Ø¢Ú\r·]©¸Ä •¼ºß²0±·ÿÒ)\rQß~ù§}-p>ûœRß/|nµÀ«¹b­ƒ½Aí¡Êi×zm‚•°Ş=»áä´¸£|i‰\r4à~áŠÊ~üiÕÿ\0oº»{ö8V›¯†4­×©Å\r×\0ÙZ­zvÂV›®Ş8¡ÕñÅ[¨¦+Nåı¸›å½NõÅî^8V^ùº¿AÃ{!Á‡LVÈı>­6ôû±µj¸Bµ@G=ˆöÈÚPSi±ÈõŸç×-r6,ìaAô•ámü2qÏ|Ô¬\Zl€ƒß%ãT“Oj\rè<r³6¨ÓWz¿á‘ñÏ&<c¹¯Ñ±Síããñù9tõ§]°ÅxÂó§ŠÃŒWÅÖÀƒ»×‰Íä0¶HaPynŸFc™âä&H¡v»Q6=qiğÖ½Ã>äıÙ*	o#Z¸SK„œ‰,j{äQÃK‰ØxŒmƒ×\r±!|RJz,ÁÍ)Ä‰®¬doÉ7’ÿ\0Uú€ŒKÂí7N¡Góf9ŒxœáÅâ]~ïú)\"êší×8íË–;3 ¡ÛføxåÕÍÙ>Q•#4ı4Ú+]9Ş€ªïÄ¹İ¹ÿ\06Bs¿ê¸ùõ~šıÒ*áÉ–8–]ëÉŞµ¥?›\";Ú`6&˜õæ¥/Ö]h\n/ÃJÔR¹“mn×Â­¤0$§.{qßˆù„·Ù¯$IÙ%ÀGÈ1Ø’†$5p×5®Í£·3ìNØFé‹’Ñ¨\\£rI>Jwsa1Yx<Âù¥¾¼cdëÄç’?ÎnÂ?é$U\\€wjft7ymn$^ ·ÙİAÜW	±ÙõÒÑ³0£5@# ¯| ã£LF1hE³f«8×©ñğË]’@´o©\r¼AœPï°Ş¾ÙFò;16R¹îÌÒ\Zı‘ÑGLË„(12è²#ˆƒÆ»\Ze¢šeÍa\"»dÃRÖ\0ş¿»$.XÙ˜\0*zÓ®$Ç(åÓŒˆ	Ùç¶cô\\¯=y£ €Ä€6Ú£¸ÌiÎÛ¹\nS¾aE^§¸Éâè…çÅùCMˆş9w=‘{¢­‰CN]¾YTÏs ¨ì­Ê• 9ŠJÖr7¦L+}U¯ÅR;R™.µOÙ¯Óãí„H%h\'ÇaÜ{aC‰ñÅZ®ûôş˜”7]ˆÄ«‹\nŸaŠ´=¶©ÛõôÅ-×øW¾*êøôş8¡ªï^›şªÚíşÖ!Z-Ôø~¬(h±z}>QkIsÔõ¢Ú/¾Ûûá¤ZÂÕ Óøa¦<MrğÛ$ÆÚçüG¶·–[\\úo¶4ÆÚåáß§¾\ZA+y~i¸¶ôíÜøâ‹ZXÓß\nÿÓ$¨ÜÔ|†j_Kn»uÛÇ¯çááL	oæ§\ZUÜÅz~¼®æ)*î]<;`W÷ş¸*Õ¾Uë„\"œo–4´»‘ş˜)ß.õ÷8õVğïÓ­òî~ŒHZl0ûúâŠw*ïL\0-:£¨œUº“×|QN\rÛüé€•¦ÃWèÂ®\'u|7ùcJêøN­0n£\nº¸¢]¶ûğtW÷÷Æ–›¨8­:»xŒ­W{Ö›®*Ğ¦ŞçŠêøšb»n¿ç¶\rÕÃõ`òVşXi[»~9‹^#JP¶İ†D’z12-·‹¸ÎJªú+$ÕÏA‘<Ø\'ÉmpÚU–-Ç#·€ë‘»`d‰HbZ™7ªr$–“2y+Gõ§ @ñ5ËŒ¨FÖë!õp ¡Û\'¿FÙ	·4[¶—\'V@:•ï_‰Ç(ş’´sé‚IV„ì\\Ô6çù¿g@‰]µÊ8­É©ÁjŒ–è^Sşís]¿^æ§O)›‘ôÿ\01FMNb\0‹÷KOˆ(¥IêrÁÕ¶:xõõ©ÃsrÒ§í.ämO|eI8€¡z·Ìì–ÄZ\0‡Zd[p˜rú’¦Óîkû>5­rÑ9Ã<Q–qµ²IÊ\Z7 ß~ÔÈOsaÇË.2§sã¸ÃvZL’Tˆ÷ÁtšAî\"Vt$rëòß|›`Ø &º•Ë\0ÜFàá™0€É0Œ+¿ŞrĞišØâ.Ô¨\0ô­@)Jƒ\ZD‚ˆ¡\\À÷ÊMÂTûŠÒ%éß®ù`ÅfËB¸”İF}M€\nxedp’\0è–ÍFH`AğíLË„­¦q¥œæ‘BJø{œ\" nÆÉÙ§Õˆ`káˆ˜!µk[a#kN£Ã#<”ÃêŠÕbÜ\n¶ûûf9ÉÄÜ(*o—Ë\0KŒÂ/‰è\0Æ‰ä‚6Jç¸.ÌA­zf\\aAÆNåÁÌÃuÜ!î0	 ÌDe¤–k‡-Qğï¾İ11c3\"ÜV´ß(%ÉíÔ×®H •‡áïÓs’k-mıÆ*ß#ôt§ÑŠJŸìïŠºÀú}ñµ§(ğé‚Ö›õÜ*Ñ=ûÿ\0iZç×Â»b‡çJcKkïïß%Hµ¥û˜Ò-isµ<:×¦6ÑsòùaA+9wpÓ\\H¯\\i‰[ÈîGùüñâßÙ’ckKP\\Qmr;ûş¼(¶‹Tb†‹p ­-Ó¸Ä-µÈl:ÿ\0·…´[ß¾(·ÿÔ‡¦Ã¯¾jéô•ÂOı¸m† ÷û`Jàşı1¤¯×ñÿ\0k¶~¾ØØqò÷À­†şÚb­òúq*ß!ã×®*ß E\nî@í÷bŠo•v¯õÛ¦ùm¿\\JÓ«ï×Û%l6+NåŠÓaºoİiïŠ»—ÏN\rSïßèÂ›¯ö`(§T¸i\\Osí×ÃåˆZuM)ã‰Zp?v*ß-ëiÕñ§¶+N¯ÑˆÙZ\'¨ïƒu¦êw§S­:£§áŞ;ôW|ğŸ5ojÓ|‡»ıò7J»üÇ†6†ëµpZ·ƒt7ÔÓ¶(\\:×Ã¦…Ê{÷Á³¾»øà<ĞŞßN&H\\6®İ1Bê\n“×CxxßOã€\ZC`´>Ø¢­®ØR.+Â”ãÑkÖ­ã‘hÉ‹¯U9åY\n¼Šª»r^”ö\0¦pŒ@Ø)ıNÜı©é^ô #	‘eâËùªfÜ76ˆşì\Z+7Za2gÇURÍ\"û»ÌME›\'|Û!¿2‚k‹Â¼@(€§\\²£ŞäA\nĞÈI<M[Ä„ƒ_V”öşÌ> ^0ºKrªQˆÉevµ\"Œ±÷¯2’K®}. M?gwkPT…ˆ \n£,”ˆJ)¨Ø\n\rò“Í‰+^Ş[“ŠÃ¶29!¡o\05 Âe\"›^Ë5ã^õÀ/½A+U¢¬ûS	ìTî´jpÒ¥ÁñÉx aQT[€ê\n€AèFDÂ‹..¡GŒ±jvë–áØ¢CÓI|í$ \0ë÷fLçAÇ†+(ãÀÔua°­2ÜÍ”Òß…>Aê?®JSók¦Ê8@{ …!agSFŞŸç¾˜î¨»]¼0Ó|Fİü1´Ó|HjS~ı¾ümi¢E6\'§ñÅâFõëM½ñ´-,kJ|=‡L(%k8­Fûa¤ZŞ{Ó¿l 1%i’¤}øi×?¢¿†<KyPèAŠš/¹ğèN\ZGE·÷;ácmWøü°R-®{ş8VÚå^ŸF\ZcmW®4‚V—ğÓh°ßß\r\"Ö–ü(¶¹\n|ûQn¨®ØXñ5Q÷cHâj¾6Ğ8±²ÿ\0ÿÕ‹‡§‡¾kiô…Á¾şßæ0+jÛ‘ß\ZKaşîã#I^ k&ÜÀí¶ØW‰7­~g¦\nmó§…24•Üü~Œ4Áë×¾ã¶İĞbU°İ<1*Ø`wíÖ¾:%¾^İvûğ®®Õëï…[åß¶o‘ÇÇ¸6Ø¢›ä+¿LVËjc]ëMò;`!àqVÃ§®+M×ÃÓ¹\rëß¦!Ø;b´êıøôZo–øQ·‡†!Z©ÅiºûĞxûãKNäwğÀŠuOõÀ–ëŠ)ÕG\\mi°i€!°ÛÁJØ=şüm«°ßøâ\nµÈZ)pşÜJuéí×È_]¼pRá×AM¿V,[§Ï¡pÅŠáøcÍí¾6®§nµÆÕªµ¥aùâM2Xk‰)\rr lvğÉrM,%·ÜûàeK	÷ùád$*()óÇªğÚÒ@RÛ¯}†Hyı)ê„t‰©AËc 97ƒ´C R9\0Q¾KŒ2â~ª?i«AÛ$2w)’ô†4#Úë\\‰‘,I+ëâvïƒ’)cò¡ôÈğ\0ód<Ğ¬ÓF´i6íµs&<\'eHIer~ÓSß.ˆZäOE	ã–‚\ZH. 5\nk×q‰b {“8’\n)Ç1\'±sáOêm!%Ş£¶ óZ,Xt}ûañ¼”@–Í‘A\rñ>(+R³\Zíü2\03%HµO\\´­õ\0ê>×$´òr]ˆ\'ñÂ’¤Ò·‰ë±ğË)¨•­#1%‰\'Ç\ZLËFRzïà|0ˆ Ï½Ë;( ûŞ¸ošJ\r	iÛc¸ÃÂ‘rKÕH£Áë_F`ìVòÿ\0kA-×bk·†4‚°¾şı°ÓuHí¶!Ñqáı0¯Şté×Ã’×3†˜ÚÒşuÄ#‰®g í†‘Ä×.¾iE‡_¸à¦<næ>î¸Xñ5Ë·~¸PdĞmúS\ZG¹bÇo,4\'r=>ìi\\…1GÿÖ‰†ÛÀæ¾ŸF¶Á¯¿MÎ¾]şìÈëı¢¸ÒÛaªkòÅ6Ø&”üæ¬	µÛmíÛßÜºİ@ú=ğÚàÇú­‡\"§§õÆ“móğúq}ö={uÁIw©R7ÜáUÜè¿SÓ%¾~ş;àVùĞõÀ®ä<6ÃJß1Û·~¸*Õ°ıñ¤»ŸÓ!p~ãzà¥§rß­pVÊß/•p+¹ï×ß\r-7Ëµq+MòúpRÓ¹•ÀVË´ß/~¸Ò)Õ÷ÁÑ[äMqZuE+İk€­:µèq!]ËÜ×îÇ¢Ó`÷0rC`ûSBêé€î­öÀP¸°ÛÈ ®ôé‹àŸ§)x¯s‚Ø•@MpÁpé8\n\n _mû`akÂşÛl)ëNN\0Qm0éãï†Í¤¬7­E;ãvV\Zÿ\0C’»f¤q’ÃB>xC ³—Ğp‚Ê–œ!’Âp²ZIÉ%a#ñÂÉ¢rJ´·n˜²¦‰Üûá½ÓKI®—óiVr¯_»%I§T!+IíÓ·l*î]€Û¢ÿ\0Û„¥ÊıHë‚–”¹5w•+^¡>¦æ¾8xi\ngpiÔmü6É‚¤ÔùxŒ˜bB“6Û{î2a¦Eajı=²l	[ÈC„5’× iíÓ$´_Ûç¿†$­-àzaA+Ka¦6×-êË›i˜ìOÑÛ\0Še”–ú„¾ù*kñ„ŸÛCƒ…|gz¿ÛLi|`îTöÆ’r8¿}ñ¦<mşÑÓ\n<F¹İÆ˜™--ßïÒ8İËïï†˜™µQó¦4Ç‰Ü:ü±G\\‡m±cÄÑl4ƒ\'rÄ£‰ªöÒ8šåOë†dâÛ~¼âÿ×‡‚vkéô;p4ş¸Sk«ıŸv[:}ôıx¦Ûç_l›w-éşg¶ùvïúı±M®æ)¿ßïŠmÅ·;ïã×lmó?Ø{œ–ù“âGõÇ…[åĞ‘‚’ß2EOŞÏ¾4®\rSïß£&Û\rá†–Ü¿Üps[o™¯Óş}q¤ÛbM÷ù`¤®æ+¶4¶Ø}Í:à­Õ¾Uù\r·ÀRî[‘Z{ãJ»•j)óÆ’ß.İ¾ó‘WsÜï¿|iiw=ş¸•§sÿ\0o\ZZl7lŠÓaºxãH¦ù~›å×\ZZur%\rƒQãí-8±¾ª[­2(l6ûâV›¨¨ÁHlÄãH\\\nè†ÁÈ¡p¥r(\\Ô-Ç·|–ÌUn¿vF˜EÆØU§İ‘¶U{Ó#l	VT¯o6ÖJî:ûchµ¥Oø8“jR\nT•¶EE—ğÆÛSnş;d˜áfÙ¾Y€°ŸÒÂÛdÙ€°µ†LE•-,7ß%Ig.ø@M5ÎŸOùÓ\r2¥¼öëÓ%I¦¹ş¼$-4Ì7¯ß„€´¿Ó†––ú›õÂ\"«L•\'Ã¥0Ò­mJï’!Zækø4‹kÔûûœ4‹pšw8W‰kH‡Û®Øh È)4ƒÇ¿ëË\0k2XÓ}Ù!³‘Iä÷ØvÉ€Õ)¬2o×|‹QZd¯Ì÷Æ˜­çÛğ­0Ó5¼ûŒG÷ßÃkKş¼4ÀÍÜØ±ãhµOêÃLxÖò?v4ÄÍÜ…qcÄî]}°Ó&ªp£‰w.İ24Ú2[«÷õÀË‰Õ_zvÇtƒ­î)ˆA#§¶ğî]î,:S2ÍÓ ×ËïÂÖKU§ñÂ‹k¾;ÃÃj¸l1.ëŠÓÿĞ…ƒãÖóôo—n‡õâ›o—áü1Mº£¿L›n´®øĞ[o—ßÓ\Zemò¯Ïûp-¶é\'limÁ¾şç\Zdß,\"Ü®4[ç_óï&ÜÓéß-·Î£ÇÛ|«Jb›p}¿im¾[\Z}?,	lŞ8•w?»ÄøãI\\aü0Û|ëZ}-·Ës¾Ø)-óß\ZWß~¸¸9éáÔcIl6ÿ\0æ0R¶íí‚•¾]ë·¾·ËÄ{Š¶ÿ\0hÀU¾g¯áiw<i¾Tş˜¡Ü»¸—rÛç‚‘MòÈ­6¿İØ?|i»–ÿ\0¯\"QKğû°6E¼6F˜¨~ı²,UTïá€°*«\\ÖU£×¶FÚäQH‡#m$«¬_¯|‰“Q’¯ |=ğS5qâ„Î0[AC:ä­¸éN£¶HĞ¢æ™&À¢ÍOl“`\nE«¶L`)™7ğÉ†B+õ!t°±ï±.ÒŞtéøÿ\04Ê–ßo¤äéZ2xô§L<*·ÔÜäøUiõêÿ\0v<*V@Ø>gúd€¶&@,i{w÷Éˆ±2Yënj>Œ\",8Üf¡ÜıxQÇMzİ¶úpp/ˆ4ß*Ÿ¿$\"ÀäXÒï¹©É\0ÂSZeïZüòB-g\"Ö˜Óc¶\Z`r,2wí÷d€j”Ö—\'¾\Zk3[ËïÃLÚä>œ,S5™5Q×¶4[¾,8œNûíá1%ªÓ¾q5ÓçŠUÛ»\n-Õü{â‹j´ùbÄ—Û[ï†‘nä~ì6ù¸Ú½±eÆÕwöÅx]±mª“ş~¢İ\\iâÕvÅµ_öñE·_íÅmmF(·W·Tâ‹ÿÑç6Ú²=ãà~Î:SÜeÅÜôšnÖŒ¶Ëè—óÿ\00VV•ƒòMrªw1–àñ;µr,›wÅ6î]ûŒVÛ©Å6İqK|‡ZâZå‚–ÛvÅmÜ=ñM·È÷;˜­·Ëû1¤ÛªGôÀ›n¾;Ša[l0í½6È¦Ü•ïı1M·Pk-·ËrF\nenåı˜¶Ømıú}\nm°Ş$ÓïÄ×E¶Ã¼OêÆ“kƒTà¤Ûa‡Ó€¦ÛvñïŠ[ju\'­;äHM¶\Z£¯Ó‰Kuÿ\0k#Jº¢»wÜş¬kd¶\ZŸÇèÁJ¸µ|~Œ«¹\ZWüÎp>;Ø«uëóÈ¡ºĞo°È«`íøPWWï\nWïí‘(¥ÀŒ‰BúŸé€±¥ÃÀtí‘(+Ôÿ\0µ‘¦%Y7 ıùZÊºW Z¤‹‰I>ÙZ$Q±ÇAÈì nNÃ¥r.4¤ˆK5T.áE\" ³¨ÛïÈT Õ(dşlÿ\0Ò­“\\òäCãÔ ÁZ§ğF¤ı2kğ²ÿ\07ıÊœšÆ‚ÄÔ $×£Ûá²6m‘Å“ùªB[Yø˜&Bà—¡<;bMü2ˆÜIFKiß	úæšäì6ĞF–@©ûTÜÿ\0±ÉÙnî½U$5¡\"ƒ$6rEaFT~`şÙdKdO’„ˆã~¿,˜l)·1Z¡¦Ya˜¥-ú·c“f×Z–4jœ•…Xäªš×øä¢{Ò¦OC_\Zş¼˜òAZ1N[£¸8H%ÈË]œÔøo×Ã$A¥6AZó>;Š—“5\'•åô¹dKL€ïSçNç\'M\\Tæoo|DXË1Qç–SA“\\±cÄ×/Ã\r0âk•N\ZGl¾Ø)LÖ–Â&‹{á¦M÷bÀÉªøa`K¹o‹Z¯(%£Ö£®q:¿ícLIw-»â‹h“ôáA.ÿ\0:b‚Z¯öb‹v(%­ğÒÛçŠ,»õ`C°««ŠC«6êâ­b¶â|1C‰ßWVŸ<w¿L*K_<UÄŸ–(k?ÿÒä8¥r;¡ª1Sâ\r1,¡9Gxxoîbn\\Ëêæ äLrğë²ã7ÅÇÿ\0FÃ«¡ J¼<Xn>ì¨âîv¸{^\'iéÅñJ+†úwë•˜í1j!1é”d©¿öd[İËı¬Smòû±[kQV!GJ“AŠ™¹<.¤È1&”¨?†40z…Û÷Ûç²Ëuı{øb‹po¾˜­»ëß\ZM·\\›o–õ¦ÛåMÇ^ØÒÛ«Ö16î_†ø¦Ûå°ü0RÛ|«¹8¦Û0RmÅ»‡aŠm¾Güı°Û|è=±¤Úî]<0ÊÛ\rşÖ\nH-óÛß$Á‡Nş8M·Î»Ÿ§#IµÜ©¸ê>œi-òí¹ÿ\0:à¤®\r÷äHUÜÏ@>Œ-7Êµßo\Zø`¢…5(EdzP\ZŸø\\Å)tqòê±Cê”#şrüÁ¦/û±›ıU?Ç,ü¤ÏG]¯¦ÅÅşl”›Ìöì¤ôüpÿ\0¢ãÜÀ?Ÿş•£æ«1N0ÈwŞ´w¦Kò2ï`{sA76[)näw$Šàü„»ØÜÅüÙ®nµÿ\0Fzvø†Ùòïş[ÅüÙÿ\0±oüalûÊçÇâ?“åüä-ãşlÿ\0Ø­>r\0|6{şÌ?É¿Òk=·æËı4V:İÒÚ1ó$ä‡fÇ¬šl÷CıŸüuEüç¬ªã”-z|òc³±ÖüM2íyQ‡û%ón¼ä‘tR½”“\Z#£L»S)şgúDÚ¾¥=L×R½zÕÎ_>8òŒZhf?Å(ÿ\0SÑşá\ndfêIùœ´D8ç<Ï3&¹aaÆ]Ë\Z^2ÚÊêj¬V(HÀb6qÔN<Œ‘6ú¾¥nkÌ‰ÜüDƒôªz|rç·Ç_˜¿Ïıçû´rù·Z\0†•\\”€æ9Ğbîr£ÚùGLéãÈû8#mwlG‹Ä\r”dìóü2ÿ\0Nçbí¨®&ô±1¶×t›©8‰Ê0¥ƒv÷ÌYérC£³Ã¯Ã“hÊ<_ò¯ıÚ0µ~%5pGù·)§<nµØ²ö¯a„@R‘Fæe¶ZÆ\rÜüÅj2b’q#ezÔÉÖ•I g?Tå¢t6`an`xî\0êF Ù´ĞKˆw©Ë8š¸CL¼‡Ri„\ZYG‰I×–ÛŠtÉÕ(Z™@:ïødÁi0š™Ù;i1[Nç$Y‹DbÄ‡S|6Ñ§~±İ©áŠ\r|²VÀµ×ç‹·²Ó¸Û\Zk\n)ªch¦ıñbCX¢š&¸PïB)ªıø¡Õß\nUï®5ŠÛ§b¡Çî®»ßïÂ†«ı¸Äí…Z¨?Ó¶Hş¸QmWïÅm®X¡Şø¢ÚåßÿÓä«±VñK±W~œVÕóoûÆß®ç\Zmæ?Šé•şé\"Èx€ïO¿\"`á®ÍB^•OÒ·~#î-ßÊ™ûãş•=Ì³·)\Z§ ¾ì˜ˆœLú™å7\"¦Á¡öÂĞ\r*%Ìèj²0úNt5#¸”¢¬šâ\r¤¯úÀ‡ç&¥;qq]xÕ¯9WéJPS‡ÁÚ¹îî?éUY¸iàT`8ƒt{g ;ˆITkKMá#Æı™ÍÈ´:Ãı’ñ¬Á¸(ÃÃ¡Áà–áÛ8úÆj©ªY“»÷#®GÂ-ñí\\óáÿ\05xÔlÙyz ×îÀq–Øö/‹ş-×-©ËÕZÆø\ròm\ZÌ5|p¥A<F´‘M7ê:`á-£QüPÿ\0L¿5 ‚<A²$6‰àyS¿¿\ZH•·Z\Zt>)•·Ï~¸Òñ88Òx›ä;ğRm¾{ØÒx›>|[ä:tñÁL­w1üF4›m_·‰ßß Bml·PÂ¬â0vëã„@L2ê!Œ\\åh|ÃlŒU¥nU ş9pÓHî]F^ŞÅQÈ€¸×ïeŠòw?ğM—GOÏÔêsöæiíÜ¥ï<¯öÜŸ™9xˆTõ%ÎR’Ì“Mº¸«±VëŠ»up+«….éŠº¸­º¸­»uqWW·Wv*ìVİŠº¸««Š»D[ßİÛ0h&d§jÔmÛ‰Û+(Ë˜r°ësbúe/÷‰Å¿š¤Ø\\À‹Fi_ö-˜SĞá.óoªGşU¦0k\Z|ñú‚P†´(æŒ+˜òÓÎ&«‰İ`í#bQôrz&ë­VÆØUäIÙRŒq†	É–£´0á)¤õ¡†¿§7vSØ®]ùY¸±í½1ë/ô«×UÓä\07`À¾8<	L;OO.SŠ¨º‰èebz œ˜ê!/¦P—ùÍV´;ÜŒ˜wºÚší¶æÄå¥ÇT›c–‰-¨§Mığõa{)³Rz§&Ñ)¹BI~¢-G‰ËF>÷U—´€>‘Ä°_ø§Üqàj¥ßÿ\0^‡Á‡Ñm¡Œÿ\09Í{\rXıØğ¿zœ—p¾ße»W°Öã—ô4Ñ­üq¢Îyá­,Ñ7GÑb3Â\\¥õï›UX–¿V,mŞı=†5µqEº¿N%Õß[UğÅ\rÜb‹u®,]×øâ­ÃÇv(u@ëÛ­0%Õû°«Uß[«Š’âqE»Õ}ñ¥$4N[Dÿ\0ŸË\ZEº½ë5·W[ÿÔãø¡ØRØÀ®®*êâ®®*ìUÕÅ]Š»up«°+°«°+«Š·\\Rêâ®Å]\\UÕíŠ¶øœSÄW	\Z† öÜâÈdäd®š…â\0¦ƒ 4?¯ aÑË‡hçˆ¡%ß¥/ßëZ»-ŸÊ™ÿ\0şÅwékÏçıˆÁáE‘ílıñÿ\0H±µ;Ãşí#¾À˜|8Œ%ÚyÏñ.ı){şıü=Ì¿•uÎlj× šÈ\r|@ÇÂ‹!ÚÚü_ìW.±z…”û+€â‹8öÎp*ã/ó[ı5y¿Øìpx1eüµŸúéT›S¾n³0ùP~¬—…ç]§¨?Ç$3»9%‰f=Iß&8™‘²x–ábÕqCuÅ]Š»v)uqWb­×v*ìSnÅ]Š»v*ìUØ««Š»uqWb®ÅÅ]\\Vİ\\Rêâ®®(uq[j¸­¶\Z˜¤ñÜM¬r2ü‰Àb6ÜzŒúe(\"âÖ.Óg\"AşW_¼egK²ÅÛ9ãõ~ûúê«¬È~Ò/ãƒÀ\rñí¹õŒU—UŒı¥#İN…É‡l@ıC…yÔ-øÔiû9·ÓÅWşÁqtÓåNÊ2øÂ.§[,§ùşj…rn\r´N(·uÅmÕÅ\rW·aC°*å–Dû,q¦qË(ò*©xâœ€#ñÈğ¹0ÖHsõ/7‘Ó¡Ç„¶d{¤Ñ¼ZP)©ë\\<,N°tMáì£ïÇ…¬êÏs¾¸”cÂÍæ¾¶O¶:¢W}e|!˜-‰Û\r2ñË^³cHñËŒíí)ÌZ3ŸliZõÏˆÃH9Ë½sã‚”æ.õ›Äa¤x²oÖnôÀ/´fli1Zesß\r19\\‰ï‹\"ÕO(·W[ªq[j¸Vİ_VİË-ÿÕãø¡Ø«±WaW`K¶Å]Š»v*ì*ì\nìUØPì	v*ìU¼(v»v*ìUØ«±Wb­×º¸«±Wb®©Å]Šº¸­»uqWb®Å]Š»ov*êâ®Å.ÅÅ[Å-b­â®ÅZÅ]Š»v*ìUÕÅ]Š»v*ìPêâ¶êâ®®*ìU¬*ì\nìUÕÅ]\\U¾DwÂ¶¸H{ï‹.%áÔô8R±K«®º¸¡Ø«±WWv*Ö(uqK±Wb‡b®®*Øb1M»™Åmªûâ‹v*ìUØ««Š·Èâ¶îX¦İ_|UÕÂ®ßúb®Å\\Nv*êâ®®(ÿÖãø¡ØUØ«°+±WaWb®À®Â®Å]]…]]…]]…]-áWb­`Vğ«±W`WaW`WaWb®Å]¾*ì\nìUØUØØ«±VñV±Kx«±Wb®Å]\\U¼UØ«±W~«±Wb®Å]Š»v*ìUØ«±Wb­b®Â®À®Ûv*ìUØPìRì\nìPÖo»6ŒSk¹Œ)¶ê1M·Š»v*ìUÕïŠ»kv*ìUØ«±Wb®Å]\\Pêâ®Å]Š»j¸«‰ÅZäqCa±H-ò8«¹{â®®+n®*âqWTb¯ÿ×ãØPßl\nì*Öov*ìUØUØ«±W`WaW`Wb®Â®À®Â—b‡b®Å.À®Â®À­â®Â®À®Å]Š»v*ìUØ«±Wb®Â®Å][Å.Å]Š»v*ì*ì\nìUØ«x«»â®ÅZÅ]Š·¾*ìU¬UØ«¶Å[ÅZÅ]Š»\n»ŠZÂ­â†°+xU¬UØ«±Wb®Å]Š¶ŒSkÃ…6Ş*ìRìU¢qCÅm¼UØ«±Wb®Å]ŠµŠ»v*ïÕŠ´N(k»vvv*ìUØUÕÀ­ƒŠº£\n¿ÿĞãØ¡Ø«±Wb®Å]Š»v*ìUØUØØ«x«°«±V°+x«±WaWb—`C±K‰Å]\\Pá…-àV±VñWaWb®Å]]Ûv*ìUİqVñWb—b®Å]Š»v*êb®Å]Š»v*Ş*ìU¬UØ«±Wb®Å]Š\ZÅ[Å\\)v(kv*êáWb®®v*í°«ºâ®Å]Šº¾8«±Wb®ïŠ·ÈöÅmÜÎ+näk\\VÜX“ŠÛƒÓ¶ùœm6àş8­·ÏÛ\nÛ\\ÇÓŠÛaÆ·söÂ¶×=ı±[o˜®+nä1[k–k–*îX¢Û¨Å-oŠµË7\\Vİ\\UÄâ­Å\\*Ş*ÿ\0ÿÑä>”ŸÈ~ìxrî“½)ıØØO‡.é;Ò—ùî8Ø_\n}Òw£)ı†û6ÂŸtèÍü÷l/…>é;Ñ›ùî8ñøSşlèÍü÷caÿ\0›\'z2ÿ\0#}ÇáOù²w¥/ò»áKºNô¥şFû±°¾û¤ïJ_äo»áOºMúRÿ\0#}ÇáOºNôeşFû6áOù²w¥7ò7İG…>é;Ñ—ùî8ñôø3şlèÍü÷x‚ø3şlèÍü÷l/ƒ?æËı+½‘¾ã_Í“^Œ¿ÈßqÆÂø3şléKü÷l/…?æÉŞ”ŸÈßqÆÂ<)ÿ\06_é]éKÓû6ÂŸtéËü÷l/‡>é;Ó“ùÜq°¾»¤ïJOäo¸ãa>û¥ş•ŞœŸÈßqÆÂ<9÷IŞœŸÈ~ã…ğåİ\'zrÿ\0!û6ÃŸtéËü÷l/‡.é7éÉü‡î8Ø_}Òw§\'ò»áËºNôä§Ù?v6Ã—tšôßùOİ†×‚]Òoƒÿ\0)û±´pK¹®/ü§^ÜßşS¯	îwşS÷ckÂ{œÿ\0”ıØÚğçq÷cià=Íğå?v^	w;ƒÿ\0)û±´ørî“|ùOİ„ørî“¸?òŸ»áOºNôßùOİ„øSî“½7şS÷l#ÂŸódïNOå?qÆÂøSî“½7şS÷l/…>é¥wşS÷l/….é¥w¯Ù?v6Ã—t¿Ò»ƒÿ\0)û±°¾»¤×şS÷l/….é¥wşS÷ca|9wKı+¸?òŸ»_]ÒwşS÷ca»¤ãÿ\0)û6áOù²ÿ\0JïMÿ\0”ıØØ_\n]Òÿ\0J×¦ÿ\0Ê~ìl#Ã—t¿Ò·Áÿ\0”ıØØ_]Òwğ?v6áËºMpo†×‚]Îàÿ\0Ê~ìmx%İ\'på8ÚğK¸»ƒÿ\0)û°ZğKºNàÿ\0Ê~ì6¾»¤îü§îÁax%Ü]é¿òŸ»_]Òw§\'òŸ¸á´pKºNôäşS÷ciğåİ\'zoü§îÆÂørî“½9?”ıÇ_]Òw§\'òŸ¸ãkáËºNôßùOİ¯‡.é;Ó“ùOÜqµğåİ\'zR!û6¾»¤ïNAûî8Úørî“~”ŸÊ~ìl/‡.é5éIü§î8Úørî“½)?”ıÇG‡.âïNOåo¸ãa|9wIŞœŸÈ~ã§Ã—téIü‡îÆÑáË¸»Ò“ùÜq´ørî“½9?ıØÚ<9wIŞ”¿È~ã§Ã—téIü÷mîkÒ—ùİ§‚]Å¿J_ä?qÆÑáË¹Ş”¿ÈßqÆÓáË¸»Ò—ùî8Ú8%Ü×¥/ò»^	wı9?”ıØÚğK¹Ş”ŸÊßqÆ×‚]ÅŞœ¿ÊßqÆ×‚]ÍzR!û±µà—s½9?”ıØÚğK¹ŞœŸÊ~ìmx%Ü]éËü­÷ckÁ.çzR!û±´pçzr)û+Â{éÉü§îÅxOsÿÒ‹8«Õ©Ó0¸‹Øş^ú¬UvÇˆ²ü¼H¼Mïƒˆ²\"±V†¿~<E#\\-¢kó¯Ó¶6SáD.±\rêGÓ¾)¢ßÕbß¯€ÆÓáÅ¯«EÜ×éÃe]õX»>g|A@Å}Võùüğ[/-ıZ\n÷Ãº<8¸[B75ûğn¾[ú´5?>µÇv^]õhh:ï·\\w^€ß¡\0€Ğûøâ-<1wÕà®àí×|wHŒ]õxOìõè+ŠˆÇ¹Ş„§LwO]éAáô-E¯J\ZÒ”…|1¤z\\cŒ»cHÙhHÏJi6â‡ÂƒÙ ‰Ú˜ğ¤×ö÷®[D(~  ÈS¨)]©\n8šÚ›‡€(›{©¸Ç„/¶ğø8ÆÕq‡…Mq^àm\n8œQ|xQ~Nà¾-úiá\nhw;ÓSØ`áMæÄ)Ğ¨Ç…4;—z)Zq)•à¼AòŒ*ËÖŞ#¿‘¦ÁÜ¬–ñô(+Ûl\râ#¹½¹ÿ\0u\\¤6Àr!`·Üp_}²¾5^ „îÀú25æ‹lÛÀØZxPvÁñ[wÕáí\ZıØ9õ[oĞ‹`Q~ìæ¶»Ğ‹ùîÄ66ãTûáĞcI·zoD]¼\0Æ—‰Ş„`ì«÷VÖ˜##ìŠü†\ZM­ú¼}Så…6á*£\r·zIÓŠıØĞ[hÆ½J˜DBÚ™…j~^Ù`ˆM¨¼*OE÷4rÈ€Ä©4)Ü»m–†¹TºÒ„\n÷Ûl²ƒY\r~Èùü±ác»Fì]†4¢ŠN½Æ‹+\Z4Ú£çMğ€\ZÍ©Mê~g,¦“m^‡\Z7ZQv§4›oˆïı˜ĞM—p©Æ‚7wPq\0\"ÊÒ‹†ƒK\\Tm‹mq_\0\Z7w¯O–4¶{İÄf4¶\\TíÂ\0E—Så_|h\"Ë|Gİ-—qéYj‡û0R,¶Ta ¶ZãMÿ\0h#wkNß.ØĞ[“¿†\n»T>;xÓêuĞA\'½Ô?0{âÚ§İòÃA´Tı8Ğ]İÇï÷Æ‚7k‰ş8Òîê\Z{wÅµÄÿ\0íãAµCNûaFîâØÒ7k‰÷ùh#v¸ŸìÅZ§¾Ø¢‹ˆ¯s†•Ô8EÿÓ™ØPıù‡Íí.—‰+Ğoà=†`ß½°íÖ‚¸9;Õ¡ßı¼Wˆz\riôœ*¼>ÛqnÀ|ÆËeÄ’¡©°Ál¸Zõ>\Z/N˜•hHİ8×ä	ÂP2‘ANµÀN\0*H\0æ¸m7SĞkL	 îU\'Çõãi âÄ\ZlÕ®Mù:w¯ßŠ¶Nâ€W­0[*­MHææ˜¦¿ÎqcC¿Jõ¯a†Ğcn¥Á«±±%h6Ê(;šoƒ‰‘Ë	Pvëò8wa³e@Ø¦ñ´˜·D¡®ûlká¦œNµïJ–\"¢!o\ZŠvÆÓÃŞ´¨ñ \réÜá²ÄÄ:›R›t?¯¶Gîh\réO\rÁÃkÂ9;á­ßÛúàµ âENÃ¶6¦–ª;ô÷ÃlHk¿OóúpÚ)ÌO~¸-Hhë…[>Ûü±µÙ½ëóÆÙ\0ØüGÈÛ*\\¾ı½ñ%;7R2ØªöèhFEš&2J‚Z”ú2¢wnŠ&&Øñá÷uÊ¤2Š]»}ùA-e~ô§‡LHhôWWû6«—}ÎŞØ-‰\\7¦@É©µ<1²J\Z¯‡öd­Z \0\nì0ZV±÷0„¬cOàrlÃ…zvÀB»j{a[®IXÄV>y0È)1Ú½k–EJ\ZF¥yu=<i—Òwñ¯l›YæØ\"»õğñÅ[åQ°ùÿ\0LT,n›tÉ»¤ŞÔ¯·¶L4¢zŸ|›I[Z|°µ—\07ıxUºíO¢¸;zô¦(j£üüqCUßõä˜·¯N¸ZËUN,-ºö®(p\"•®Ş8§wWoëŠ-±Ö¿ˆÅ\\{‚1bØßúRï×‹#øb»ºµ4ëáŠµQ…mÕ5À®íZS\nº½\0ú+×9»\r ¾{×§|ÿ\0ÛÄğµşv,Øÿ\0\\WÜÕkı;âÆœi×îñÂVšş8«¾¸k\r±¦ºƒáŠ)£¾!ÕÅi£í±§ÿÔ€\njwñ5ÌG²¾nv ÷ß€B¢#SÃmé\\Û–ÀPO_j÷Å4\Z\0\0´îNºïLõ¥GˆÆÙğ®Gòœ\Ztqª×ˆøxxâwX\nä¹T~×Ú=ÛâÈÛ¹¯ìì1cÉo\nøü«¸Åy¯P#¯RàV¿vl†ËRµ$\nûwÉ ê7qÓrOİ#ÍÙ¡­IØî!m¾D‘ÛÆ€`¦V´ù1?*£làœMXÓùwğÆöZŞÊåP ñ]Íá˜ò[Ì»õ\'%L8­¦*jEmÓµq^ y5E`-›§€Ä„Xïm˜¨ÜŠô§öà‘•ù\n\Z5aLxTL-õFßj’§İ\nDÊ×z±¡éÔœ\",e=ÜeÛá®Ş–¹-æJõ¥:ƒ†˜ø–]j6ù£#6ù¤tü±á_h-ZÖ§®\ZcÄÕMAñÂ›?<iL­ÇÚ˜ğÚxº5íÒ›bwP[Øüú`$…Â•÷ï&×.æ›R½»HH’ğ¤ºö,‡{oáßAúğPeÄkdL-‰ÜóÊ&“\n&5Z’µ¸Èvehµ¨¹P[kJ×„CkE7S±ò&4†ö55¯¾Guo~ı	È¡º«°§|i\\I=^ç\rRï‰H5Ò´\r@¯^Ø)V1&¹ Zõ5ÉŠƒ`‚*v¦\nİ\r–î~Ç¥„ú‘ã’­’¦çnµ=NN(bM*Ãú¶Ì€Ú”„Pü»\n\nş\'\'˜Ku*ü>ık×l—2ÁËÄoÜü¾ü$-» ëSã‚”,j—%ğÉ†Rb¤l*rA¤î¦k“\r%mşƒ|,‚¦•íŠwÌüğ2Ñ4‹§\Z\\-TãO,H[JûvÉ4—wÛ§†Ñ }À–ê{õñÂ¶êıİq¤uMvÚ½°%±OìÅiº||F(uwÛ¦(% Mi×Ç\Z@Ù¾Õ¯ËîĞû‡í¶ıxDÿ\0·…†Ï†(»-V˜¤š.íóÅŞ4ÅC[øu§¶6­\Zí¾(=Öã_£¶*Ğùcl)ªĞ{tÂ®§SŠT4ıG4Nı>œ(âj§Çêƒ°F*Õp\"ßÿÕ‚*ÇåÓ0mí€° \n“µ}¶Â´$±\'¶ıVVº l(´éOíÆÙS|·Üî{Sß¸‘Ì…$Ó ñÃLxÅÓuÛ‹nONGş#Ÿ¬ˆ$\rºv~ÎÙ!a¬˜“ºğß	$tè>Y\ZlÙ¦ôÉøºx×zaİÇ«¨…V›S­GãŠ\rçw\0ñê£-2—p üN¾ØxX™YİuK³¿cÛS\"m¢Ì(¢N£¦4¤ŸêÅÁ‚µÓ¦5lx¨ÿ\09µ$±¡ !v=pS0wq=1\0’Ÿğ8¤÷rˆ¹ÔÒ”éï‡š‚ó–ÈI&«ÈxvÂ6DJS#Ä¨Ëúë’¶Š¯æÅÁ£§BËâ\r0QH”¥ÂÚ‘Js x^§8‘Ş·•#q¸â{Œ‘Aæ\\¬Ez\nŠŸ–D„ÆD4Y· Hïáá’bIæ´¶Ş t¦Ä›q_§ ß5â¢›Ò½ND¶\rTƒâ”n~œ(‘k–İööÂC6ëïCØâ¶áÕkĞuÅ×VÍkAµzœR\rµÓ¶ÍĞõ5Å}íôá‚Û+oç6:u;øâ»Ô!#Ç®Ø	d#º âOq^˜7m\0/E4øAñù‘<Û\"\rl«œZ´äÃù§#(ØeFÑÑLÒ\r×æA$x~(Ó\n%Uh;“÷íã”’RW ÒŸo–\"C¥¨ÂHöşÓ‡†ÕµcÄ\Z^ õü0p¥¤<ºÔxÁ![…+É ’j+¶@\0Ù4ê{ĞŒ•w!ÎH\nW•pEB™~[qbzø\näÄYS˜ü?ß \'\Z²¡®[\nõ=(0Ñ)uGÏÇ·ëÁÂ­G$+ƒN£%Dt@w%Ú¬)^½±áJƒÎ ²±¡QS¾[}QÄeˆ×‰ãOµ]†ı79p¯Ä‰*o$dš¾àV¹1×,‘ïS$\0\0…:ÔPWx©j…f$\ZxŒ4T^XFŞƒ¡¥Vbrp©»TrâG.€m¶LEË{¨&¤u=	ÃL	¾ù*h9\'Sj€~ì%\"ÕAØ“_ìÆ˜‰ÛqMğQdf:»µAØï„®Ôã÷àA;-%OL4Ãˆw´Ijd©„‰ZH¦ûbÄÉª¦ÛõÄ‚ÇÄ¶Ø½ñ¤ñßïÆfÚkCü0Ó=ù·Ë¶4-ÚW¥øÊËu©öÂ–EwÛNØÖéã-sğÃLAÕ½úõ†`uhĞ4Ä–ƒv§^ø@˜ui°¨5Æ“Öq\'ûp »âíô÷ÅlÒÒÀ_Ã\Za)¸°ÃïH^.®$×L\rÚ»âB–ò=O 1²´±í°÷Ã[0ã-ÔŸ*U®\nRofí½0¢ÛÛÆ8IñÛ\ZQïÿÖÕ’¶Ûu$ş‰OadnW]Îäï±ş¸kÈ4ä{Så‘m<¬­S¿Ø4¥IÂÖ“g¥@ FûÓ~ØB–¹‚I¸á¦\"}Ë‰V\"¤³Ó¥)L‹eÄÿ\0YÃ‹\0hÀõÅv=~ÕMiÔšÔW»¸&µªûÓ»6Yz·êiLi&T×8éö5?ß\r$+œ]ñ:Go@›ş‹Ô°rT·‡JŒ5ÜÀäè}.ªµ\n½wéÓîîÈLE²ëÆ¤’E@ÜŸW5¢EpCFrTC1.c°w+¿›Tàgc•z[âNÊõ o\\ğ»‘´”ƒÎæ¥¸”¨2\"ÒRyğ®PIâÄ¨[ã‘mˆ$Ñhñ!‹†â6\rŠúHõqT‰ª5{‘Ø¿†‘Ã!µúVš‚@$Ó¡ë‡£Q$Ëš|=»ş¾Ø\0g,›81#©µ}°Ğ\'¯\nÀÇz±#¶H†¡>dÈ¯iMéÜúd[AnV©ØüDŠSÍ1;\Z.zÖğ±£ÒK€a°£ı©Û|vYXş“}FàV½\ngÆ+úN¨=úœHX›ä;N\ZaÄài×|ï¹³QAµ|AíÌ¬ÖÍ‚»\nïØS®43\\Û<v¡ß­zW\ZGåmGBÛ‚7­zıÛáè±>¯âVf£ı|?V\"å•Õz‰äz“±\0üò&›G0Ü«G##¹uã·@Äbd9X2)rT÷§ëŒ#Gg&öi$vp€³(êIıûá0lovŞEFäT¨Ør\'cÿ\0qˆ´•‰”ñ?\r*Ğ°Ğ­×{\\Ò2ºUJƒĞš\nş9BImŒ †Sğ¾	>Gş5ÆÙMª,¡c³0îÄ~¾9U‚Ef.²€¨&4*–öTæ)UÆı(k•Ê nÔŒ¢P°;Ã°\r™ ™nÄ„Ğ$[râÄíøeàÆ¿¤ÔDïú*êUWŸ#ß”•5ür³·ñ6-²îÌ¦=»öÃR(3r¦÷Ä2´fŸ/:m²B%‡‰`Çı2Ó,Aƒ†RÍ¿ÚWå“,X±ÿ\0L¤Ò«\0]‡Zí^â™1	N$¨—¶‘ø Ò m“\0†™ÎÛÒÕ#€…Ø|¼0ók&º7¹R\"€\'¯ê¦>ô^•…ˆ!§õÇd\\†íÏ~İÙ©ü+…ÄÊü}~û=r`¸Æ$ËbÓ9.FÔìO\\h\"R•­%Áû÷¯|,	<›#p6Æ™	W0×Ä6;W·LXÈ¹oMê7ÛâŞ—TlSWÍkËe5ú0†©ÚæIèk‰\n&4…NËS„¹d®AÂF\'ìƒãCıq¤†ù6Wm©ÔS‚]_·}±Q\"[«Ö•ëóÀ¶OVÏÎ‡MyºÓÇÇ”î¿ÙŠ\Z­Wñ®_’ŞD\nƒO,8ˆi\\“özõ cH=ÍĞŸíÓƒJE?h\ZaêÆBÃqì(\Z£ñ®˜mÕÎiÛl™I Äôíá¶\ZcÅaÛûwë…Ú§\">/˜Å<úº§±­;hµP>x¢ë£‹l68B$JÒ[°ßî¦;1•ºµ×\n“Ğ¬æµ­<q¦`7Ua±ùšÓøb¢@µPOÚû‰8¢üÛrG‰Å6K[¯Mı»b‚Hÿ×çê®İh|	f1§©Gú_æ¯WEj÷;` M‚b#pZ7-;xR›â K¨Æ7ShÄ…C_§ïÛ%á–¿ÍÆ[®G\rÖ.;Ğ×ûN5æÊ3ø¿wÄŠuè·ÓİÉ¨‘ÉÉ&åH©ÛáöÄ†ò®i£õK05éÓa„DÒ%–WR]Í8íQOw®\n-œ@RŠÂêvU©ìO!ôıœ-dï÷Pÿ\0zÒHäîªt§øa ÄNw¸ùÍµI>®Û~A²\\\n±øˆZ\r«\\Ä?ám¨q 1î§û0ÚymÃÅı’GRO]ê$[ä4Ce‰$4dW©Ú˜\0ffIú[äÔ§@>Ñ§ùòÆ™ø„{¿ª°´„T\0Pt>Øh5JS;¡p‚>\0Æµ½1!È{–³J-ZÓğÃA®S˜;¬.õŞ¦›ƒC„\0ÄÎdÿ\0˜IµYiÖ•8‚C!æaÂ²OSÌ)â\rk’4äŒëiGıÛ‡­AÌÜuÄÒ–¨–Ï¨¨ÛW†rãÜS_2vşÜ>äWóŒ]ğƒøŒwG§ uI4~ÛtÄ1%uxí_‹¶\n¶á26¿S{Ğ‡ğÁLŒ-â:r#å’i<?ÒTÛ`kîNE¼ßşºÖ •,ëÍ¢İ)Ó½\0?¯Ì$\rós:ı’+^€\rëóé†šÎQÊÜ¨•;õñÀÈ@wÅÂ;mØÒ†¸\0ÍVCĞ-:n7ıx²ß—¥z³mQ¸ìôÀÈPØ¯ ¤u+Ôÿ\0ÀäKl@;*r (äB \nuù`ó-¶\0«ÙZİ­Âñ\rÕ·R>öï•LK7bàGXÂ}…EFÿ\0ËL¤ƒ.nM€¨Zƒ}é¹¶ñÈpÛ\"T¥¸ª†PMNÆ´ñ,Œ(µ™ìÑ´f5Y^#û#–ÕûññGtd‰b¾²\\œ`Rgo‰i¾äŸø–räÈº˜–6nmm-\r:vú0Cê‹;?LÕàãÉı2ÉS¸`vùe27Í´/šê?|Fõ,O–B3®Šw\n~ !•¥¯B¯ÒwËF^µÀ€6æ¤mÁaÎI›ÖŸHß,ÛøXø{ó’şõŒHE<FäóÈ	Ñ™‰ïi-b*Hv\n64è>Œe˜ƒÉ†Ù¶%8z¬W¦Ôé€eß’L6¤#éëêšV‡m‚€?Ù6dÔiiA(I,¦Ï €û\\ˆş™pÈİÃ––P7úYÂ¾·|~%E¨ÊN#…³÷•¸‹¸°èëS°ùŒš=ñÿ\0H²Q,¤G!û\" ~¼˜\r1J]C”ªŒ±~Û‘øÓ	+×3./óÜ^?fèA®ß†\nS8ÿ\0NN`z,d“½İ~Œ#Ş‰€)pàhW§¶<=’w\'aøáa#m\rOé’âk­Ü˜l7ÅN­|G½+\'wv¥OLPäÑ4ƒÓ¤w˜°Ûo§İ$wIªhÊ)÷áÙÌl·àñÃ»_¥º-jFıúZ.\0§nÇAäØ\"½	Äâqg¢ÔxãH3#£`×sôŒ+h’Àì6=wÂÇr\ZâzŠn˜Ø^ÍÀ·rµñÅA—ô[5\"œ¦óêÕ	Øp±¯ê¸’6\n=…}°\'~TÑf=€\'¯Zà>CÇ|•°ài•öØ‚Òc.åÔJT‹¶+B–qãĞo±á¦ÅzìOÊ˜Ú€êPÿ\0AŠ‹¹ƒïóÆ“Æ\nÃ\'¤–\Zk3¦ı@ztùS2ãîr¹5¨5ùvÂB‰5P:8)Í®¤ñx×\n/¹Ê\0ë¾Çb´…­xŸ¤œ,\rs¦‹(ß‰¯†4¦c¹ÿĞ€*~ÕñbAû³Ş°G¸ÏüõÍÎ‚Ÿ ¾\"–bFŠÀê>\',Ãöƒo†­«ˆGsÅ1şS”E*½;mıpp›nñ!ÃajÆ ƒNİÏ¦HìÓ	qn\Z1É#Ue ˜V6Ds‘±&ã…Ô…E{ìöâd,x¤?š¼•ztÈì[D¸Eÿ\0B¼A&•ë¾øz¨â\"Éáid4!ƒ0èÕLLZã’¶<rÿ\0=§y+ECÄô\'; Ï(Z/ <ŸÓ\0Œ§06ÿ\0bº1!|`w>Xšg2?Ïix#TƒÈ×â¥pU„Æ¡-Áâşz·QÅ¾/ö²œ³¸£ê[êTq?dl(k’¦¡>ŸÂ°î~ÑâOÙ#Yyÿ\0˜Ñõ(5ì(+_¢˜ì]\0ÎâJšÓn‡¡Å Xşkzõ\n;‘×\r™#Ìğ¹”Ó‘f$õÄH,ñÊ®æ°G#\Z\Zšt\'\'Ä0Ìÿ\0:š^jvCË Ä°éõ8úõ$ÄÄw5ş˜{\"2ó0?é–‚k¸ï_‹$Ô$GOôëƒ•=(vï2ñLK€&µú+DÖ#¶ıÈ4ıxZ½_‰.Rİw÷ş¬›#Åwêÿ\0LºŒÊOB?\r™\"9n´7&O\r°µñyIyjƒCºï¶ç2Zä*7ÜìvÅ˜¯ó—-JïÛÛ§ÃvÀAZ)Úƒ§L6§ÍZª‹ROµ1@€8¤¼şÍM<	4©º_Ê Q¨µùà;6W^À‚»±,—l€ÙÈæ(•ÀĞã¦ôÀK(Â¹zŠõgÜĞÆ=€=22Ş¦èñuıÚ¢ÄEZ¼şÔ€Sñ8¯n«B6oŠ?Ó]\Z*’Rê8‰ı’yoÚ¦FR5ôñ,DnÄãÇõ‘J(5õKtxßˆÿ\0‘eB;óô·ŸôëHK3Èÿ\01d¨ó¦\"q¬ñ+Ç\Z`—,Æµ£GË‹eï|-‘ˆÄˆ2¸ôÙ—mÔW)\"ù–Òã? sÎÀ2ô§õ$\rÔ¤‘¢<Ë™*?Èÿ\0‚Ëã\0E1”¸TbÔ¨?yM­§û”ôıÅª9Éæ%öjĞ½´â±ÑY|*ıY\\„¡înŒÄ¹*´[Š’Æ½‰Ê£“üĞÍH‹’Ü¹Pt*jkã–$P’p€\'.Ûš\n¢§/;è×,€uQ{èÄAT2€­ïşQÉŒ&÷h–¦ ùµ%¹³få˜•\0Šwö#	\\\rqÔc\'s–ÜnmKRÈ¬O†ßŠá—ôTçÆO9Dş? ÓMi±Wgu;|¾‘’_Õbg‹¡â’—5,\n€<GO§,áqüH“é\r³È7Ùv¥?\n¥’Q\Z7hx3ToM·üq1GFü2“I$ÌjAîi†ƒÏ$)RÖâcPíØcÃh–IG¤æµç.U ÙbpˆÓæ2RŸ© è»ørÉ\0ã™‘Óı“BgaÄ©¾5ÃÂÖ3K¹®LÙ\r_r:á(õépm¨Fı÷È¶‚9Rh¬GÔÂ\Z¤,ì\\!ÿ\0‹\Z§°8ñ(ÀyÜÜP‰&»\r«„¹@õâr†‘íÌ£r¦ÉQ¹vĞYWm‡Ol–Ì*Epo_p0[(·løn)Ó|	®‹HjW‘ûñ´÷“³pìÀñw¬á8’âpØkàÉĞµûõ\"¤±ùŒl#÷ƒúM©ÜlVA;ş¬*í–äh#ßlj—ŠÛâİW¶„eÑºHG^\'åƒfGˆÿ\0Ek4€t©ñ|4ÇŠAg)	ßaì1Ù‡5åê8Ñ©€6{Tšn@µNİğ°7ı%¢g­\nµ>C	‹æ<½N3·ëLi(î“b@Æ•5ñ¡Æ™Œ—³¨»ÏÏì´Å^#õõÃlN!ÜÑ¢ÓaNàâƒ³ªÔªNÕÅç+9û\\OÌ H|+†û\nSÏôªrS°İp†©Øw74 \r Ì¹™©½7ö®\0JF·ÿÑç£ë·¡^ÁXşüÆôÓÓVS/á—ùëÀ~İ{IÇfÀ\'øâs>Âó8=)”³Ê\nk-Ç#ÉƒÅéødŒG{Dråxãÿ\0Nˆ.ÁCp©ÿ\0$ÿ\0d)Êã _úOúI¢ò²|H8õÜ€„l%9È}?ôÊbÕxÓ~¢ŸÉ4‚8ÿ\0³n¨WáeZv&Ÿ¬àİ´‘·Îl:´Å©ï·âq¢8ê<_çÇ–¼ªíğ©Û§NŸNk”ã.B_ì[BiPh<Oıu‰\n%}vüMÑ…Ş¦£©45ıxM¢ óâüY±,)Ê•ÛqJà1‘m\\QëÂÜ’Æ@*j@èFß}0\0YeÍ6w¬…ıê7ß\Zæ‘˜Kéú•ÿ\0”TŸ<“yám)Ëù‰5öè1/!Æ±€ğ—`wÉ‚Õ!Gn?é5± °\ZíÓ/æDZi™¶R(7¿†\"  æ\'h˜¬fzŠ¢”\"»ä¾\rDËùÜM¯\ZŒOù5ÀO{dDOÒ]Å«Fzü6:10™;Ë‰§WŠÃÚ¬HßIÉ	´¿ÙIªJ}²@†¹G%Qàh‡­(=°Û\\éV’Ä\r?Êÿ\0o\r5JFàmIâŞ›Ta*$FÄÅq’ŸSÛÇ2#õ8(,ˆ{œÓh€‘Ûê]Èı¢¤{í‹fÜéµcZˆá`(–¨İyºm|1ß%Ô45 õÈ¶€94BuéÖ½;±\"Ü©B íÖ˜Qq½½M4¦¡Z& ö4À#æ™æéÁ/ö*Š9| :J˜ÃÕjÆŞ$4%WÔÜkãLÏÃ­îNµj!]ÁÆ—ŒçqY|m\"À6İhÕÿ\0‰m‘•rmÅwc‹ı:#ÒGPN(ÿ\0g•:¯Šº9¾#ypÆKD±‚XÚNÁŠøøoƒG˜“\\qâ€ÚXø¿ÌnŞâb³2©@)¿ËáÉNdaÏjFÃk`O¨Ä:ĞrÇ>TæxPçõ:X´úXâ2h¬ W!s½øé‘†3Ò\nv©±\'„l§ì+šoì™nJåõ5âÕ‡õdF*MzÈßç˜’PkHgİ˜øí×|ÎbN\0©<~‹†Feğà ’)ß–Yd7A\0,(h¿v4äBı0ğËú*$İNUÀC6ãâ)OÀd±ÆÆâ\ryg_Îÿ\0’k£ZCıì®­ö”Ğ·Ñµr²7å³ˆ¡ÎR@¼q«’-Z»9RkïñÊ‰?Îq%‚kâT‰‚J¯bSş7\\e-ög$İóVµµã·¨ˆµÇÄ€Ù„°d&Ä¸4*Ÿ^ãr+öğÈñ“Ê,Î0®|_ç)ò„­#®7Zò\0ş`ÚL¢FÃÕşr›G+#$TQÿ\0É‰¹a‘ÿ\0k[ÁÔó\'bpÄ>×™$cN%|)‘ |dÿ\09o6¡ùIVÓ\nÖµéôâÊ$¬àkZl?ÏÇ$Ò!º“HªÆ¬¡º5É€Ñ<‘œ8š`ä~Ï`®\"ƒV@OX¶ŠiÔ\0zíZâV\0»áGÛløGVƒ!èËş{a,AˆëP7 ƒ\ZIŸ{\\Ö†Œ+àvÆg6w ÔÚ‡ğÃLšİÁªŸ¢ƒ\'cÍ²({Ş˜iMı$~¼ÈÇÍ­÷©Ûå‹ß¿ZõÄ!¥juñÂÄmÍ¢TŸµ¹ñß\0S¿VÂ¿*šaµ+Yd-Pôö¦ ±1‘ê×§\'ûğƒâ1°¼şs‚ºäo–6¢dÕ%åö«óï†&3=W*‘½pÛ!ÍÕ\0×`ºlñmúRh­àw(kó8ß{Ğ©Çöè{Oé’j©w¬(ë¹•‡°ßs”—€³1ùcI™’ã\"·û±¦~ W¯CQ€…o=éã—›«µk_Ã\n“·6bwüqb	½Ö²oZœ6ÆQj›÷§¾+EÄ±îÅ¬Î¿d,	=ÿÒçÌ¦A1mêä|”Ş§¬Œ§Æ£&=Î4Á<å8;Ñ„/\'ääï]Î6odx8Àâ—OôÎHà\0µ({súâL–0Ä7Ÿøbº¾*q=øÓî¦@¹1­şŸê;õişVYcŞÖÙ\Z%j{m÷á2Õhˆ¥Z\"Œ×”qÓÇó“=c‰³BŠc	\\<EN\r¸x…Š:„“üg«8Æå?ê}.\\o öÕugâq¡¡#®Ë\rêA\0şğù°d6h³ƒR€·jŸ×¶»Ø	Háõ*z“Pƒ\ZíÖ„×ød8G{‘âdåÃŠÒ<zTŸl—6 Ètƒ\\§eè}©ˆ\01”òÈrÿ\0rºOR•S¹ë°¦L²qs2N V”ñØá\0\"RË·Ô¨ÜT{?4×·ı\'\ZÓu¨®şÿ\0N 2—-Ã‚²š×cĞTÕ‚í‰µÀH*}ßÓÍ‚3Às)¥(Ãry›)YGÕıe:º°\'aÒ¦‡\'³Å(›<œÒ¦à2îwß¦¢$UÃı2å™©@6?\"10g\rEmM1©ğÇ¾ \"yiÊ5=\0J#]KjÀ×—_M±Œ¡{•ß\r	ß¾Î.å§ ÿ\0Ÿ†L¹Rï•î6\0×¸\'ÌÅ°ÄˆoMşx=É5»Aë„”G{j6ßomF.æÃ°èAÚÆ-ĞÒ­ñ¶‘ÏÔÒ«ÉAöëú°‘h¡bTéã¸ÈÓiÈ%HrËÅºâí‰	†Xß%Uô‰Ø\0O^Cúd7otcÄ©µÜ<”·ìÓ‡D˜aª2áj;kG˜„ıâë¹éÔ\0pœ’¦¸é°“·©¯©Ø+VNu¯q·ĞN<‡ù¨ü®œÄ¯üätoiHTpõ&Ÿ†cÈH›<İŒ8@¨ı+Ö.N^&‰Ônñ•¨ñê~,Œ²mG‰D,Ú! ^A”\"€j(¾>9OŠyz¤ÙÂ%#¢*b•J×ì‘M¾c	8ú‰q01óŠò—’#+ñQ×à&¿«(i¢y­+OÒä£«@¯Ï&$yÛ\"ÙrÄ±EÀE¯í\nõï•™ñJıRQ›O¨¦«-v!:—\Zşc_4+F…ËPÄ¤’»Ç.…5‚oèUEc«‡f=	©¯Ó¶U+\'nØĞÄàÑ&à2VÏ\nıõÃGú?éHéşåNáBrFÔ@x¡FJº5äïÄƒj	H4Û}ÿ\0^eN¸‚%ü+Ó‡J’v#âïí¾FM±72±jú…Oj^Fü›L:ñ4ßf²nOÚ şA¾ŒHÔ¦A-Å[–Õ=¿†LrqådĞ<M p	f,úäI\r½Ì–½=ÏêÉG“VAiM™ÂşíwÍ_Ã&i‰¤!È™Ù\0w-œi‡¤UkğŠÈl@\'lßüïë,ä¯*Ÿs†˜	yÿ\0²lÉSJ\nNç¤W€ı¤û`İD8Ğn?Sb>õœêEWé®4Àe¾aqß~¿¯‹«Šš[¾AÄ\Zu§¿s„0=¯Ã^µÂƒ-– 5â\0é×À_&ÁzÔPWéÇf~²Û7´ßzà\nIÖ¼£¥vğÉµK#U’Ÿ	Ÿ<vEÊ¶Xe‘iU©9*\rg,‡0¸J¬:Ğ÷©Èğ³l/ZR€ï÷à!°Ç”¯…p`M©N™+a(´¸¥8±ÅŒ¥·)48€ƒï°ØòL…Nè[ßl4ÃŒaÜÁûI×ß‰_0ØUşM¼p[!Ü»Šÿ\0-~X7fD{š-ÓjW½0°&º:ƒ¯lIHˆiˆ>ÔÅ‰Ùc\0›a!ÎXTt¡?<“]RêPÓ§ÓS‹•Øî<k‚—Š¹µÃ‘\'™Ä•¾¯ÿÓçâHÂŸ‹s˜¦\'¹êÆhWÔß4$\Zr#Øh†>$OôÖÂŸ²Æ½6¦K…¨æ\0òÈ½dJô4?Ìr4\\’\'§úuÆV$(\r;u¦4Ÿö\rúª)Ê£Àc4FÇĞÑs Ù‰öè1äÀË;…YiSO™+\r&$/<ÂMÉ|Ae! (?ô£µ6`ZP\r˜æ{Úpzü[õ\0bY‰\rıæ4z\ZŸ˜¦\Za‘º¹9¦ı©ó8YÏ9Wû¦„ÌÍDô\rc9‘ ³éşÈä[‰XwÀÀ…P+×®¨FÁmrp8w,cÄïşæKÊ‚µMÉñëºæÚ`¸úã™naĞÓ.Ú®ÜY<<1oÒuãáQı¹&cÇŸf2Â‰ğÇ&^	#i¹tq0£SÄoø`2e€hªÖ«Çæ×èÃÄ²Å|¸}_ÎRdS@A\'Ç¶LáÏÄÖ}^\"MW¦\"Ô0@Ÿ¥wÕâQ²Ÿ£\"Ûùh“\\\Zu íZäL‹hÅ‹½ixT÷ğrBÜyœq;*SºR‡¥EøÙ:6Jò©¥zìM26Ø@¿á\\[’šqù¾)»Âê ¥k_\0+\'Œ\rœV¤Qˆñì´IØğ·F]ƒ:ÔmÌxH<æïPòâ{·JãMƒ/ORú|#`ûşü\r”{—FÕ}Ï¾\neÄ[¯E‡¹#Éº ‘Í±Mû×%œ@µHø²ñmN@`2-‘ÄUë\Z£Ud$Ÿ¾‰á?Wû&Ü8‰X*AU\"¿<vîL¡-ıSÿ\0Kkxê$8^ªT\röÿ\0#%âÄ\Z.,´ye¸œ•b‚N@<Šõê\\\nŠüÛ–Dä\rø´ÓÔxÿ\0®ˆkhÂÕY	x’îå•Œ–äœ¸x‘q‰Y~)@,Ä WïÊ\'C£’»Ôœ ‘îªôşm8œ \nk8I6e%I)K+c=>u9›ğÇúí’_RÏ§‡ªœ»¹¿ğÙ=>QéÄÔ¥Ö\"²¿7ëğ–ÿ\0pÄvD\rĞfêf¢ÀËï1?FùxÆåÄ–Y cÅı>%A¤«P!\0õÚµÈx¸ùz™øyºx-ªßT3\ZS§ì©îN$Á”c–·çşÁÔûeXÊA#1äääÄ÷–™ZRÊC(?g}¿VN2\'ú(I ™@åñZ–ZÓ2#1ÑÅ)uõ¨,kÆ„o(>ñ–’ââì­Õ\"²ÍãJŒ¨‰]ÿ\0•	c„®>˜Z3TøÓüür6m´\05Ó“µAí—mÑÃ äV4Ê	PÄ’-3ÏüåŒàƒÆV`zì€ÃÃäÓ,€¥6‚ƒÖB+Ğm„û‘µñ,«#\ZÊiÚ»ÿ\0hw5™~¯¥ÌÒM\0ëïˆ §fÁwËã¯^ÛaâîD±Qõ.jSáê;âƒä;¯Ú#å¾I¢WŞ½\\/Úf#å‚’\'Şxœ$Vj?*\\i\"@j”§Zd[¬-cCJ×Àâ¦ƒ@\\( 4i_„ï„4Èw:´êiòÅn†í\ZCß¡i‰•Š+H!zš|ñZ Úï¸m»øãI=(IÆ˜™×5«$U\"¿†ÃÆŠşIÕM0S3t\\Ò½<kŠv;©³@>ÑQøã»(|-,ğ‘J°ğ–4:7êÀ§2w8IQN$ŸÃ2väß_oli•ÛU¦ßÃ\Z@‘l°¦äb™©´Ã·á„E‰Îåûöï‚“â‚â«ÔıÚĞhŠÅF$:€V>xØ€Ak½~üQ]]ñu‹h“_ˆRJÒc­IÇv\'‡ª×€ãÔxä…†\\›ÎÜ@§Àµ*äÿ\0ÿÔçŒ8uæjsnôS.Y+]ÉşÌiœrë~?¨ºµ¡UŞ»’M?VØîşD“G\n:\rÀÀÜegêáYÀRyøPä­¨ãÿ\0¼ş£ŒÊ»2ì<NG„–GQ±DwZíîHÄ°¡Î<_é ÚÈŒÇ“}®ø˜ÒÇ(\'sşÊ\r·£×ıAíƒtÈÀôYÊ§rüôÃL|Kşoı,VVæRj?WÓ\"œ¼sÓ³±*ÀWçOÕ„¼“$Ñì–BÔÔƒá×#~¥†¬h ¨‘Èÿ\0L“L‰—ó¿ÓÍuYE\rNõ®\n˜2íÄ¨§­J¾ı>œD\'ßÃ…ã­O‰a,Ğä\nÙªƒöˆí„œ™zÖ«‚µÛn¿~\Z¤‘åÀ½ìÛøR¸òg	W\"Ñs½OÑMÿ\0V<)ñBÊƒşì£ûá¯&@ïÅêqb\rMißÊdJîKÁ\r·+á‘rK¤šD§Ä{li‰’aŠ·m™z’G† É(ƒ¹’Ú®ÀĞ‚:“İÇâ…ïêXhØ©ñöÂ-®b ïÕ]¹\nwÏCg‰=öSÅîKU;×ˆ)”bkêŠòäõ#LPIİÅˆjnzàI#zštZøï¶,£ y9y›S¶‰-p>ÑíL,c#Õ¾¡íÓı¼A¤JÑ±6üºuf8ƒjŒ>×Ü1÷2WêqZU¸³\níÄŠâ\ZæhmâSQŞÛ‘Âh½Ïşğp…0Õâ\"¦\'ş‘ZÛeMv^@ÿ\0„ñåaÖcIÿ\0Ÿ£Réx·N5ûUğ*3Â ú‹Ÿ@ôHQL¥¥d\r¿Âk°=~ĞÈd;lİÖT¬ìQ¾FSJï½~ìª1±Í²EIå¿áèä¶YÄîLš2K úDò\\ßV¯UîÊÛÄ[.‡+âi–| ïó¿ã«í®É<J2ƒßÔ_øØ.Fx¶ÿ\0³ÇÍÃşr7×bWƒZÔøæ8Æ*Èÿ\0vä¦EIi»7ÁQÿ\0\r“±ĞËı“¼šŠp	\rÆ½]ŸãŒ±ùúSoÉTò`8×â5ø€Û*m¨yí¦j\0å€éÊ»}Ç-Ç–#zjÉˆÈsSu¼…T¯‚Ôı=rÏD¹µ#n:K¹Uw`\rv¢Ğâ0–r9¯[˜)+DPòZ\\„±H™Œñ#uwnUjƒ©¦KÂš?3½A¤Œ\n­>YhŒºµË$:,gäàSpi¿á“h&ú©r­\0÷û2Î\Zqmê—<œY¼zvğÈ€{ÙÏ,@³)	Ñ€!}ßvÔ°üÀ«›lÂ¼ˆ¥|MVK…ÎnÈá[$±Ór8÷ßúcÂQ-L:ğ¬õbnúöûñá,F|rä\\(w\Z\r03‰êÙ5;uÉ5lO5¤Su Ó°ÅcÔ³Ô<¨Ê4ÇŒ5p+ö”Ôû`O;…¥÷ ëá„JBÜˆÆ˜ñ­}­ºa`\0+Hr	>=qR\0oŸÃ^$ş¼W‹ÉjÌXı‚)ß\ZcâYúKe¿Éo»%?)7Ív$0,iÈ.êM™@ìO½?®\nS”w8\0Ûô‰X€\\P‘¿O\nb ³ÓQ¹vkğÀæz1ÀÛß\rµœC T\0>È4öÀKdq×G\nt§ÑJci¯&ıéO–f »—½p2šÓÃ\n%K	®Ákï…¤›è·‘M¸íî0óbIÉ\r*OËqèu-ò_z}8Ò‰é,2§¸?HÃÂƒ”Ii™)J×éÆšÎ@Zç·M»o†˜ñ6$§Q_¦˜)\"mªÛ/ã/‰g“‹?hÇßŠxóVz’º¿2:.2¿ò¿#”÷?ÿÕç‚GbX}Aéc’]úUÀÈjNãÂŸ†;$J}\\ª¥ªc¥zŠ\\I)Œ#vcÃşjâ”†‡·L\0³–1Ğ¬umêò ş CT£1·ÑÕy}#“PÁµÊSl[òé#Så‡º$iø¿Šk\r§°osQú±ñ~DôáS0Ê\rx¯bwıy!0Ñ-4Ç1E…©ğ®ã­şjÀdc‚]û5E•’ŠÊXõødjÛDÌü_ìU]ªÄğğ \Zr#Ÿ¯ğÿ\0š°\\­~ÑùJ}Ù#‘©ÎM‰ìX‘ã\\6ø¢ùÿ\0²i¥¡Riû]N\"+“-t“AÑ¾#]¼FJ‹PÉ	n´H9QT0éRGôÆ2ÆèG‹üåÀI½BøşØgÃ;ş…\\~Õ|\0E5dïâm8 õé]°Ë‰æ$¼Å<ƒWÄl2\"E¸àÆ\rÛ|WöEG]û`³Õ»€VÍ|;‡¦ 02éSV$GoìÉÓÙ£ÉÄ¨nÛ| uDç.Ğ‰ÔÔâdV#Ìñ-*z‘AÚ„áâk8«r¼hÜ\"$=\"M{õğöÄI‰Á#ïw T¸¦€åõEuj£À}õÈ·nCBªi°¯ñÂXlïöhN)‘\'“jæ¼Hùá¦¸“Ê—sZí\\j¬ÏOjc³/Qäb¦ñ»ï:vÃÄ\Z¥§È‰¡uoˆ}9. ÃÀÜú•bá^Œã‘“v/’¯:î\0uØvÈ9|ú,/1j~Ïc·¿ÃÔÕYoo÷‹‘ïÔü2ß¿\Z~#\"DdN¤r<?éÉÒqfd:ƒ¯û¢Y#>8g#f^¯óı_R&‰p>T~¬.>äœúOı‹QË©!å)ZˆâiòÛg“,c8úŒ^E_ß²¡MOÖrœ˜Íz[ÆQüF0U7Ö\\HtZÿ\0+ cîØÿ\0<·öÇíƒ(¯Ú?dGº¾j¢úmí/\0©IU~XI”:¤e…ãK²UÚ1UÛ¸Ê†¦WÍ?•ÇüÕOB8E`XÑº|XøœGÔdØ1ˆHáQiu:ĞM¡éÄ~¹>g¤ÚxrßÕı/üyUZì±õÌe^)<ÃÅmğëÂ¨ƒT/ÃMò$–ÚP˜ øšN;\nŠ~9|$OF©ĞæxRé\'w\'“© Ú¤¾ƒ™±S“1ºâúe’3\';\'I§üİ–J\'¹ÆÇ$Ñœà¨lÜNÜ»efRîrcwfìÑ$r®Ô¦Ç|…È9?»—Xğ©<vÜ9%yW}ÈÛ&%&‰`ÅV?İ)(`R<I59&\Z÷í´sRŠèõÚ¸8ƒ#‹!%ê¨úS&â_‡ÙrÑ0\\	é²Cq-¿ª¸=Áª‚h•\0à (”ÿ\0­şjÆRµ©]ı«ú° Ğşj˜¡ıŸ•2[–ŸDš¨uµë‘²Ü#´ÕÁˆ>ù.m—*HÛç‚›Ú>¦ÄS®ã71¸pôÛ ÃKvw\r²©§CóÀ˜´PvğÃe¤ˆ‡#¶Ş$áFı4¥F`›åŠ%k«¶ÿ\0†,‡\'iEëáLvÙÛqÜŸ|,HÛuhMá%¬B¹øºôsLl$c—zàYz’~ym¢Ç6‹\Z÷ù†˜™••íÎƒ·L–ÍW.öˆ\'`ûøTbïr†îI¯@y»Šö;â¦!işû¯½pìÂ¥Ü¹A?³C‚ÙˆæÀqáú°b2\r;0\n0Š,\')Ah‘¸ò(pÄLé¢êNêGĞ1¤‚Õ\"ş_¼cºs¸ƒû8XğßE¾7+AØãl|?&ÊÃOZb=¨>Cbq•/D–Üşm¯Â%qŠƒÇğÆÙTÿÖæàïÒEú2“şk»ò°U\nî\\Äì^FÜˆˆ×ùI.ƒ¡÷®D–èÀ\\M\0ÄÖ” ÆÓD_ì—8m‡ÂO†ÿ\0~\0YÌUÛñ¦Ôïşg\rîÇ„W(¸HÛÛ}øÄN#bg\rSAÇ°?ón<*s_.K	r*xPV¹.ÔrL¸UŞ@qB2_®)^òÿ\0bº­Ø†¦Æ•ÀØ#oWûÂ2£©ç‘2m#ÿ\0St“­*{o’‰\r3Å3ÿ\0I´#–\0§ ×:pÅ®¾\"¥{’pØD±å½ÌxZyP”§µIÅ¬D_<j‹Gö¨}€Èñ*8 z®eC±RÛÇ¼¿P”ÿ\0Ó,(AØ\nø\ZôÉ[qï·\nğ>xV½ûSéÈur\0ôİºi—¿å&¸‚³wû¶…:Â¾H@¡ÌOüÖ·ıš¾{_ğñµûÿ\0ÙĞV¸}-wM¤ î›÷9 L³K™ŠàIm_»ˆlYt\r–•¶P`á‹?)ë…œ]ıÅr@ãÊyo˜víáƒ…—Š@æ·×P~\'\'Ú˜ğ§ó s—ûë*°ªò&4ßDd9®Vä{&Q”eüÕJÑiËzü±¶B -<‰ë_‹3ÄÙÃH6ßj¸	eà:ĞuñÆ™Ñ°¤šÓ¾Jš¸è¼#Å‡L‰\rÈ?à\\¬UèAíá\\‡Íş8€TCjºl=éLÁ8É$BB±RŞëOµ01oc=õ8DTP\Z±ø”~¼xÂ|\"?÷…’\ZÊÍ)ö(Oû‰ÉĞp¨Ó×¨œ¿é0œV7`?e…rQï‹^IeşÎQC6Ÿs-a\0ûªí“ñ :¸òÓe•Wõq®m6eZÊª¢´ØÀ3É™ÑHøcşdU-ì8º˜å4ö¿ì†	f½©-±)#^{ˆG\0=)Dş1†(Ëw8ÎAPIräRŸs÷œ§†1m¨I Öv5&şu9l#{\\\Zå’ºM+ÛÈG¨nöØ\r¿º0˜åá¸³œ%ÏÆ‹qTü\nÒ²õë÷6+Ba0v*¿¥­¤u¥·*2àäpDíSY$ZqİN šáŒ²Œ\'ú–,	&5]ºoÿ\0\ZÓ,ãŸV‘ƒÜDmgÕ…9©\n¾À7üÓ“âAÅÔz?ÍŠ‘)>¡©ê¤-6ùd·èÕéÕşá£è©¯\"”q¦>¢ŸİúAÆlİÁÛK@aiŒƒ]Ç¸öùä­‰kÒ Ò¤ËÄ#¯¥®}ˆ¯‰ø<-g=k9´\riÜŠe”âqß\"ÕM)Qò®I¤Ù?Â±Ëvñ8ìÆBcù«	›åì0Øk”r5ÂFû/Æ½zS@G‡3ËÒ½VUûL{Œ‰!º8ò~¥şÇo–6ÌÀ…¥:Pï‡‰¦XÅó[CZéÂÄD÷)”©¯…¤Àß%Åœ\0ƒá¾\ngÇ.ÂI+BŸ3\\h2ã—râ[j.!M÷:…‡q…Î‹ƒÜ“O|	ØuoÔ^Ä|±¤ñ÷T|°2%ªêq¦<N4ëı¸@A’Ó$jhÄü€ÉSYÈ5¾§-—§ú¸i¯Œ_î\\# }ª|†FÙÙ`6\'¸¢6ƒƒA±ùƒ…-§Ù*á¤qÉw-¾,˜—{ƒ6c‚™q>§qQJâÇˆòXÊ+ï’\rR´Ê)½~UÁi1wõ§ß…4ÑIî>x±%Æ5§^¸ÚeŒw­ÿ\0•_l6×Áæ»œ©òß;îªÿ\05qM÷TéŠ“Oÿ×çŒ¨(O^Ô®c[ÓÊäiÓlÄ+“‚’~TSÃşjÂV ŞŞ–ëC½Iï]ÿ\0VWßõ-’G…æ+’ÚrÌÀÿ\0bÒ¼ÔÓØ&\0k†IË™áş¦6Ü3ï}øàº3Égœ¥ó\Z^m·ªM=†\'n‹e°œ¥şb¡Û²Ÿß«¶J$2M¢¥(hIèGöà%²Ç^RßÜŠÀ‘áˆ%‰ğ·úV‡^•ıy>Ğ2Ç£fcPİƒ…—æ+`5áCã\\xYx‡Ÿ\nÕi\rk²Ÿqı0šcH›<›øË_ÀRD?‡‹ıƒa´\0x“‚ÂD%|Ö·2)ê^ b=È•ŸâjŒ{íÖ§\r…à‘ëé^e\0mµ:V¿qÈ’Ş!Ä)kEÃ •ş˜D­ ááşkš\"§â$ìp‰)Ã\\ÿ\0Ø-d¨9c¾6Äã7C£Ìlèìz)Œ£Ê\\-|d…j\Z4:5qJı\\2lñùÓÛ ê‚Ô>ğ•ñbGó\Zä:n=ò\\->!ªXQœü2ôaºèÒbd~¦…¹¼ª{W|x0n×c£tğÅ˜?Ä½zŠ|Uï‘-àÕ}V›şäDØæ·uNØZy.¤|GMö8³â4ĞJœ€=ÿ\0^(¹¤É¶õH%S¸ëÌ	ÈM‚Ò‹‚§ãë¸Æƒ8O4‡8¬s{·{cÅ2†§§\nå\Z‚Ğ—ûÀ88 ‘‹V7+Ñ¯9T²;?VbÛ¨\nDq2ŸØVíµûFéÈÌÿ\0™	6ÖÛQ—â=\nµëÄd	:SüCşUÍM\"\nhj¾åÿ\0£d‰¶¨â0çéÿ\0’¨˜ÚÔ‘ˆî¢AúRA<‡û6„E™ËşV*¬1Ì9Ä®GRÜÁ«‘ºæbÜ\"&.<rÿ\0=¹KB²Cúà2è³‘€ú¤ uûYSÜWş6ÉŒC§¥ ë?ä¤ÓT‹Sˆl‚NÔáÿ\0]de€e”5ğ<„¿Ò¯âİ¥ç)š3Ğ¿·Ù9\\ @ôğÉ¾6|L×_-Ô‰?5c>o•K`†0E×©²yxeWé[$Ñ¸«íS]·Ë!\Zç–$n$…(H2*¯\"£èËo¡qŒeÎ\"<.õ­M@~/NÕõãÃ%ñ±÷ğÉb¥¿xT’GñÉs\\#D.qx¨Â’ó>çz±#*0\'£’50ÅÄ©ëz´ÜŠõ#\"Fş.>ENHœP“ÏüıÎJ9i–œó¾%œiµHú2Î&“Š¹•ŒÌ^‡j°mRŠ‹\\•5<H>\'¯á’àqç¬¯æÒ˜’\'mÔ+Ğ×%Â\\o\Zî8ª¿’R }=0nÚ3Éi(Æ Ö¾øl©„/÷MŠxSÃ²¦Pz¯Ë\rµËO5§NT‚ÂP@0­#Æ˜l0qw7¯MüN4Ç>E¾+ÚŸMpÚøkJ\0v>\"¸Û%´ì	úNGõ—wê~üÏ„÷º´>ÇDQ]ÈÓ|Ë‰À°ıx±²ÕwëOqb|1I\'ú.øiQA­\nş=°#ˆ8²øÓI#½®KüØÓ_ïh7ê0„K}díAôãÂE¿T¹-q¤Œ·üÖÁVØñùãÉ\"¥Í¯€¸~;°¨ß7\ZÕ«íˆ) t.RzR˜•¦ä¾÷Å5o3Òƒä\Zbgä×&şP=ÉÆ˜ñåÜ˜ö°PmMQÏp>ìy qPš‡Ñ¯	ş‹L‡½1¶\'ÍZkÓa†ÚÄE¶h:.\Z!HØ\nøQ@­ 1bG{ÿĞæáå4vë”¾Œ§/áŠ¯¥.½ÎGˆ·Œ1­ÂïÜ¨Ú¿-ú`İ² Dï¸ìv9-ÚŒqıìÛDCĞ}$`$²„!Ğ8³-B°Àbe\"9pÿ\0¥pi55û‡ğÄÒ\"fñÕÜ\r>*SÛ®FÛ£Õ¢\n2B˜K‹£ŒlíÔ¿¦&GøW*¢í°=ÂŒm‘ˆÔ»‰åğíâ¦\0[vXÍA·Qìk’¢y\0¹igb\Z§j‘LL\rH;/ô«Í©©ì)°È‹5Qôda8“ÉiƒÛ¾`b6â–8¶*S¶@Èõ-‘Åb2]Ç¨ \'‚øãlü?.P,E:ó8¤ƒEirÆ¤VÅñÃ]\ZÎBM—0f<ü·³ÇˆØZõø9×ÇõáÈ*½<kHJ\Z!\\¶‰6PZª´©å„’Æ‡?Ş[uE4Üã‚‹18GcÅkÃ§zá§ JÆÍrP:Ğıù \\ç†=?$5FwÑ²ÌGZS\Z¥)‘KmÒ‡ç…€·lĞ†L¸AääzìG%œx›*{Gzâ)ã+ñØ.ŞÃúá ZÎY›,Y$àõxCê&Áxjîjõæ0p³ñø·¹AxhMKµ)Ğà6İ	cş))4°Ö±¯p?³\Z=ÌN\\}2IY%\n$f;œ‰‡“|5\0åxäØ)\0ö`F@‚ÁÉÇ’ï3ş•#‰…;Ò‚¦¿¬e|d9KiCIQè~ÉV\0}8œ»lÃò Äÿ\0Ó/ıtbÕìÛ‘ˆÎG5—gÂ]d¦úz«\ZOÀvñú0şbÇ&³Ùôv—ó§?ş–ù\'c÷ä 5—äOú¡XúQÿ\0uÈo‹`ÔÃHê>Ï¾Râÿ\0f¡.“qAéÓÜñ\0Pe‘Ô®6nÌ•zû¬‡KZ¯:™×%-@îjÃÙrå8£–ÉWí4r|€$~9G‹}%k0ˆÜÆæªIˆâ‘Ôû™Áä¾K“*ÜÉFXY]–Wâ=ÍwùW,Iw4áÒãÜ‰d¯ë/!ìï^†£®D™u\rñŒ›1ÁµCïß‰¦DJ_Ñe(C¨—úV–ÄKB°íJ¤bsğó”K®Èk‹$Fû{ÓµêË±æ·>Ší$¼wpO†øÊr¾L¡§Å\\×8÷epi€äf4‘éÄ´Ä€éÉ‰´=nfUØ¸ÛßIYF™\nOLyr=iL‘h–(K¬V|\0\0G&>yT$ÛP)ğûa„@Ø)+FÙ¡şaL&ËÆö\n”Ûá;uÀß¹YS^¹&¢iNFQÔ°ùd€.,çé8Nª(YÎ4£(‰š›8\'ö¾d\r¿â^6‘òÈß ê©È2-Ö\"´ZÏÏ\nóo¶[¬øº×õa¶íuEã\'nk}BU÷*k9O|]êÆv\'|h²bFëêz‚>ŒV‰äá·SQ€²ŞÙ_•q´pÛ@ç˜ŠæãÓ¤‡\ZwÅiiD=(q‰ˆ[ÀWr>Y+kğïù­ğUè£|KáÉ¢£ùpÛ&Â¨Ü\nbV íÔ`lt[±;íã… ·E=7öÆÓC£D/M«Š(uw}€¦+^M›ıø®ÍzŠ;ÿ\0h£!azV˜xZÎpß«ı¡\'ÄîõŸhcKÆ;Úæ•­F,n=íò_÷àl¸¿ÿÑæËQÔ¤×(4ô?Ù*4Šw§€Øä\0İ¶Y\0PHå¹¯Q„¬wÚò5Û–ÌMÆe^¤Wƒ¨ˆæVúñÖ»SÇ¾K€µşg–xI¡û<ˆÕÀ¶Ò(©S÷S9ÇBV,hKÓè1‰f\'™È¸×“SµMVÈß‘À?µôÔ`äÈzzÕƒ$kZîzı\rË’*#š“ÉoRK÷äÀ“9âêV+,‡à©÷­2FÇ6˜Ïéâÿ\0L©Äô§Äï‘-ñr´ƒ¹§Ñ‚ƒ.9e¾b´Zš{i—ˆ	Úåşj§\"FéÇŞ‡#MÃ ­ÇÖ+ûNM;\r°‹îk™‡YImy\n\0~y&²D¹q)²ƒµ7÷Éã˜[€p)P€®$†QŒ€¡Â¼/@Àøí%¾11»Š0o2;·7+CjoO4KXÊ\"j¥ÂÛğ?—‰À-œÄàz–éĞ}1E¢R”y¹.çç¾\Zjñc½†“`rL\"\"zIR…{×èÈ[1Ó½4=~ü<E{ =zá¾ö¸ÀBM¿=À`§¾ÕÅ2Š—ïTîû|²T\Zü\\ƒœ•£Šr#ßS#“‹ø”Y‘Â\\Ÿ	Â\Z%!³]3íêPÿ\0”0Û²?Ä¬mäØ×=ö##aÉåÎø–¼q­+@}©†6×–1W¡+Äñm¾G+”M¹¸²ÀŠôKş•¯j•h6ßc×\0!œ zzU#v®ò_³QÓ*w9ØI­åº»\\Ì)ÊJ¨ëO|€Ä:Ùf1æTÖú.Û°èê2G-?Ÿ„yz–Ëxò­\rKxırCˆØ´ËZfkUGÁÅmé^ ïıro|NTeQ¡V„—Kr\nü;~¼˜£µ¸ò2ñ‡”ÅåÜŸ±¿úd¸ 9ˆê³Kê„øTd•K|P°#ü‘øå‘n6\\»ıùŠÖòZ×ãotoàr¹ñtrôù1u¯ø\\‘«õw?»¸h‚ƒOÇ)”¤?‡‰Ï„¦R‚”É(©K°IëËd8µäÇ1ôäÿ\0Nµmär¼îP“í·êÉx€šÆgs8_õQ+k8Ép¤ø-?‰ÊNSÒ.\\pÿ\0:\\Jj…½&\0ô¨âvû²V5ŒúTãSê¿İAøe‘œo”\\Iéò›<yÒZ2Ñ¤‘‹xT×./prhÏ9N\\H«c(Z+\Z—ıNU:êì•P”¿ÏXÃ©/Å»dÁòjÉ72SÔjõ$×,qN¬ø‡N ø‹YÍlÌ½–¸%Ô9ÍÙ$£¦4šL¤‡˜±ªÓùKââîNÑş³jgéE8vPr÷6©ÈüBıqc÷‡<ãLD˜ËTZßjúò+i8‡|”1Wí·İ†ËQÅçKı+„JÂí_m‡†H…×«œ¹Æ{×^µñÈÛt`\\Uz‘S¤AÍÅEIÄ1‘šÏR#¶ß~J‹8¿t7®ÿ\0:ã»ˆw%®Ø)\"q]Ê¾šãLø—ªõ8Y+KÓ¾ÿ\0,4ÀÍprFä{à!”gm†¯|\rœ^n½şŒXİõjŸ~áS~]ª~ìh˜-‡~ÊGµF\r—ŠAÜük\'Ä=\\H¦õ§¶+`­.”¥OË\Z(ˆê[ä„ìh1İ7Ék	Ù;v¥0Š`xº,-=zíòÃA¬Êmz·ø@ÁA>,Ü%”Ÿ‰GÏ\r1’ãVêi÷`gD·éÇßO‡«Z4Œm„±ÄroŠÓzbË„STOlwP\"ïİŠô®ØúCÿÙ',NULL);
/*!40000 ALTER TABLE `cir_member_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_opac_request`
--

DROP TABLE IF EXISTS `cir_opac_request`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_opac_request` (
  `rid` int(11) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `memid` varchar(70) NOT NULL,
  `memname` varchar(100) NOT NULL,
  `accession_no` varchar(70) NOT NULL,
  `document_id` int(11) default NULL,
  `call_no` varchar(30) NOT NULL,
  `title` varchar(200) NOT NULL,
  `author` varchar(200) NOT NULL,
  `status` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`,`rid`),
  KEY `library_id` (`library_id`,`sublibrary_id`,`memid`),
  KEY `cir_opac_request_ibfk_1` (`library_id`,`memid`),
  CONSTRAINT `cir_opac_request_ibfk_1` FOREIGN KEY (`library_id`, `memid`) REFERENCES `cir_member_detail` (`library_id`, `memId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_opac_request`
--

LOCK TABLES `cir_opac_request` WRITE;
/*!40000 ALTER TABLE `cir_opac_request` DISABLE KEYS */;
INSERT INTO `cir_opac_request` VALUES (1,'jmi','jmi','111','kedar  kumar','1',1,'123','computer graphics','hearn and baker','processed');
/*!40000 ALTER TABLE `cir_opac_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_privilege`
--

DROP TABLE IF EXISTS `cir_privilege`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_privilege` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `staff_id` varchar(100) NOT NULL,
  `cir_301` varchar(5) default 'true',
  `cir_302` varchar(5) default 'true',
  `cir_303` varchar(5) default 'true',
  `cir_304` varchar(5) default 'true',
  `cir_305` varchar(5) default 'true',
  `cir_306` varchar(5) default 'true',
  `cir_307` varchar(5) default 'true',
  `cir_308` varchar(5) default 'true',
  `cir_309` varchar(5) default 'true',
  `cir_310` varchar(5) default 'true',
  `cir_311` varchar(5) default 'true',
  `cir_312` varchar(5) default 'true',
  `cir_313` varchar(5) default 'true',
  `cir_314` varchar(5) default 'true',
  `cir_315` varchar(5) default 'true',
  `cir_316` varchar(5) default 'true',
  `cir_317` varchar(5) default 'true',
  `cir_318` varchar(5) default 'true',
  `cir_319` varchar(5) default 'true',
  `cir_320` varchar(5) default 'true',
  `cir_321` varchar(5) default 'true',
  `cir_322` varchar(5) default 'true',
  `cir_323` varchar(5) default 'true',
  `cir_324` varchar(5) default 'true',
  `cir_325` varchar(5) default 'true',
  `cir_326` varchar(5) default 'true',
  `cir_327` varchar(5) default 'true',
  `cir_328` varchar(5) default 'true',
  `cir_329` varchar(5) default 'true',
  `cir_330` varchar(5) default 'true',
  `cir_331` varchar(5) default 'true',
  `cir_332` varchar(5) default 'true',
  `cir_333` varchar(5) default 'true',
  `cir_334` varchar(5) default 'true',
  `cir_335` varchar(5) default 'true',
  `cir_336` varchar(5) default 'true',
  `cir_337` varchar(5) default 'true',
  `cir_338` varchar(5) default 'true',
  `cir_339` varchar(5) default 'true',
  `cir_340` varchar(5) default 'true',
  `cir_341` varchar(5) default 'true',
  `cir_342` varchar(5) default 'true',
  `cir_343` varchar(5) default 'true',
  `cir_344` varchar(5) default 'true',
  `cir_345` varchar(5) default 'true',
  `cir_346` varchar(5) default 'true',
  `cir_347` varchar(5) default 'true',
  `cir_348` varchar(5) default 'true',
  `cir_349` varchar(5) default 'true',
  `cir_350` varchar(5) default 'true',
  `cir_351` varchar(5) default 'true',
  `cir_352` varchar(5) default 'true',
  `cir_353` varchar(5) default 'true',
  `cir_354` varchar(5) default 'true',
  `cir_355` varchar(5) default 'true',
  `cir_356` varchar(5) default 'true',
  `cir_357` varchar(5) default 'true',
  `cir_358` varchar(5) default 'true',
  `cir_359` varchar(5) default 'true',
  `cir_360` varchar(5) default 'true',
  `cir_361` varchar(5) default 'true',
  `cir_362` varchar(5) default 'true',
  `cir_363` varchar(5) default 'true',
  `cir_364` varchar(5) default 'true',
  `cir_365` varchar(5) default 'true',
  `cir_366` varchar(5) default 'true',
  `cir_367` varchar(5) default 'true',
  `cir_368` varchar(5) default 'true',
  `cir_369` varchar(5) default 'true',
  `cir_370` varchar(5) default 'true',
  `cir_371` varchar(5) default 'true',
  `cir_372` varchar(5) default 'true',
  `cir_373` varchar(5) default 'true',
  `cir_374` varchar(5) default 'true',
  `cir_375` varchar(5) default 'true',
  `cir_376` varchar(5) default 'true',
  `cir_377` varchar(5) default 'true',
  `cir_378` varchar(5) default 'true',
  `cir_379` varchar(5) default 'true',
  `cir_380` varchar(5) default 'true',
  `cir_381` varchar(5) default 'true',
  `cir_382` varchar(5) default 'true',
  `cir_383` varchar(5) default 'true',
  `cir_384` varchar(5) default 'true',
  `cir_385` varchar(5) default 'true',
  `cir_386` varchar(5) default 'true',
  `cir_387` varchar(5) default 'true',
  `cir_388` varchar(5) default 'true',
  `cir_389` varchar(5) default 'true',
  `cir_390` varchar(5) default 'true',
  `cir_391` varchar(5) default 'true',
  `cir_392` varchar(5) default 'true',
  `cir_393` varchar(5) default 'true',
  `cir_394` varchar(5) default 'true',
  `cir_395` varchar(5) default 'true',
  `cir_396` varchar(5) default 'true',
  `cir_397` varchar(5) default 'true',
  `cir_398` varchar(5) default 'true',
  `cir_399` varchar(5) default 'true',
  PRIMARY KEY  (`staff_id`,`library_id`),
  CONSTRAINT `login_ibfk_9` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_privilege`
--

LOCK TABLES `cir_privilege` WRITE;
/*!40000 ALTER TABLE `cir_privilege` DISABLE KEYS */;
INSERT INTO `cir_privilege` VALUES ('amu','amu','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','true','true','true','true','true','true','false','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('dei','dei','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','csamu','222','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','cs','6666','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','amu','admin.amu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('avlin','avlin','admin.avlin','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','admin.bbzhcet','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('dei','dei','admin.dei','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iitk','iitk','admin.iitk','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iu','iu','admin.iu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','admin.jmi','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('rvce','rvce','admin.rvce','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('scc','scc','admin.scc','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('tt','tt','admin.tt','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','admin.yy','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false');
/*!40000 ALTER TABLE `cir_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_requestfrom_opac`
--

DROP TABLE IF EXISTS `cir_requestfrom_opac`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_requestfrom_opac` (
  `registration_id` int(11) NOT NULL auto_increment,
  `library_id` varchar(50) NOT NULL,
  `memId` varchar(70) NOT NULL,
  `sub_member_type` varchar(25) default NULL,
  `fname` varchar(50) default NULL,
  `mname` varchar(40) default NULL,
  `lname` varchar(40) default NULL,
  `address1` varchar(200) default NULL,
  `address2` varchar(200) default NULL,
  `city1` varchar(50) default NULL,
  `state1` varchar(50) default NULL,
  `pin1` varchar(15) default NULL,
  `country1` varchar(60) default NULL,
  `city2` varchar(50) default NULL,
  `state2` varchar(50) default NULL,
  `pin2` varchar(15) default NULL,
  `country2` varchar(60) default NULL,
  `email` varchar(100) default NULL,
  `fax` varchar(100) default NULL,
  `phone1` varchar(15) default NULL,
  `phone2` varchar(15) default NULL,
  `mem_group` varchar(20) default NULL,
  `alt_group` varchar(20) default NULL,
  `access_level` varchar(20) default NULL,
  `supervisior` varchar(20) default NULL,
  `manager` varchar(20) default NULL,
  `remainder` varchar(20) default NULL,
  `location` varchar(70) default NULL,
  `mem_type` varchar(20) default NULL,
  `requestdate` varchar(15) default NULL,
  `faculty_id` varchar(20) default NULL,
  `dept_id` varchar(20) default NULL,
  `rollno` varchar(40) default NULL,
  `category` varchar(20) default NULL,
  `course` varchar(20) default NULL,
  `status` varchar(10) default NULL,
  `password` varchar(15) default NULL,
  `reg_date` varchar(20) default NULL,
  `exp_date` varchar(20) default NULL,
  `image` longblob,
  `Course_Year` varchar(10) default NULL,
  `semester` varchar(10) default NULL,
  `office` varchar(100) default NULL,
  `desg` varchar(100) default NULL,
  `sublibrary_id` varchar(20) default NULL,
  PRIMARY KEY  (`registration_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_requestfrom_opac`
--

LOCK TABLES `cir_requestfrom_opac` WRITE;
/*!40000 ALTER TABLE `cir_requestfrom_opac` DISABLE KEYS */;
INSERT INTO `cir_requestfrom_opac` VALUES (8,'jmi','222','pg','kedar','','kumar','adm','','aligrah','UP',NULL,'India','','',NULL,'','kedar9002@gmail.com','','53463463','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'s','2011-7-2','arts','his',NULL,NULL,'','Approved',NULL,NULL,NULL,'ÿØÿà\0JFIF\0\0`\0`\0\0ÿíJPhotoshop 3.0\08BIMí\nResolution\0\0\0\0\0`\0\0\0\0\0`\0\0\0\08BIM\rFX Global Lighting Angle\0\0\0\0\0\0\0x8BIMFX Global Altitude\0\0\0\0\0\0\08BIMóPrint Flags\0\0\0	\0\0\0\0\0\0\0\0\08BIM\nCopyright Flag\0\0\0\0\0\08BIM\'Japanese Print Flags\0\0\0\0\n\0\0\0\0\0\0\0\08BIMõColor Halftone Settings\0\0\0H\0/ff\0\0lff\0\0\0\0\0\0\0/ff\0\0¡™š\0\0\0\0\0\0\02\0\0\0\0Z\0\0\0\0\0\0\0\0\05\0\0\0\0-\0\0\0\0\0\0\0\08BIMøColor Transfer Settings\0\0\0p\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿè\0\08BIMGuides\0\0\0\0\0\0\0\0\0@\0\0@\0\0\0\08BIM\rURL overrides\0\0\0\0\0\0\08BIM\ZSlices\0\0\0\0u\0\0\0\0\0\0\0\0\0\0\0\0\0X\0\0 \0\0\0\n\0U\0n\0t\0i\0t\0l\0e\0d\0-\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0X\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\08BIMICC Untagged Flag\0\0\0\08BIMLayer ID Generator Base\0\0\0\0\0\08BIMNew Windows Thumbnail\0\0¢\0\0\0\0\0\0p\0\0\0T\0\0P\0\0n@\0\0†\0\0ÿØÿà\0JFIF\0\0H\0H\0\0ÿî\0Adobe\0d€\0\0\0ÿÛ\0„\0			\n\r\r\rÿÀ\0\0T\0p\"\0ÿİ\0\0ÿÄ?\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0	\n\03\0!1AQa\"q2‘¡±B#$RÁb34r‚ÑC%’Sğáñcs5¢²ƒ&D“TdEÂ£t6ÒUâeò³„ÃÓuãóF\'”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö7GWgw‡—§·Ç×ç÷\05\0!1AQaq\"2‘¡±B#ÁRÑğ3$bár‚’CScs4ñ%¢²ƒ&5ÂÒD“T£dEU6teâò³„ÃÓuãóF”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö\'7GWgw‡—§·ÇÿÚ\0\0\0?\0àÉúEÄ‡jc˜ø$÷7ÏŸˆÿ\0ÌTö;qîIúcR?yM”7o»S üuí®å°!#·âĞ2t>¦›ƒ¢<;ŸİO¹± –Æ“$ëÛº!¡¡àlÿ\06Ñ˜Me{X^,\0’8şôxe­Öjâ\ZS‡{¦OîLä“@¦¯â˜6âd	iÏ1ı”Ís	×óL\0~ªmı<Ó_È3$ï`tM¸ĞÁÿ\0œ¤Òt1?G·š[vû@Üæ™>rŠÜ¤–ò;˜)o;ÄgÂ~å!IÜ\\4×_‹Q	ä0Ii‰)Ñ‰ë¢	5E9°ı Ä÷şÊ‰†´Äé¯nü\'cl|¸u×ı\"a»!ÿ\0½Ú>ŠTH°>¥VçìF^éÚŞÄâ	$c^fSí\rkŞét\'”î®5<49B¥ü¿E6?—WÿĞâßc™ò™pÑÄ”ƒ‹¤0\rÀL;‚ŸòQH¬\r™3 \'û<¦õ7–íq\0èC†1+~ïôrïÁˆ†Á4±¯Œ¢‘\0]´ˆ‚~IHšŒbÁ£\\<f;!ZYéÁqiÑüIïşj¦	\0ş)¡€\0Ù\'ó@:O;š‡cö‰îÒ\"cé{ÂÒÑ\0d:xâ>Š^¡ôÜıDŞAÀoòR4ABÆÌN=q- wi:˜ø$ærí¡¯háü·\'°±µ‹\\	t‰Ÿ	néJ¶—m³i­„ç~ì{µº… Ñ:Õ’¡@vÀŸ{·óTöÔ\0Ÿ\'ÏDşÖ\0‡X6ÉĞ­îQğ]2ĞœÌø§Qè-m÷4§¹­nç¸êdÁ\ZÏçÍnä ;ih.eÇÇËÚ™Ì¶{Zı`5ÃSğR4’ß`!ÀÁÚOŞ¦ôş*ĞuX³³æâŸ6 4ûÈ°àuM<Áş²°qö\\âfèØCR{!°N…¼OùßKÚ‰:í]7P/ÿÑ5¿Sş¯äuS\ní´×¸åaï/ó˜öË}›Ï¤æoVŸĞ>­UÜ{ºummÜ\0}»¬.ıç5ÿ\0¬7óıÛ=$õÖS‰X«t8‘kÀ­®wGÔİë:Ù[ÿ\0F…\\ªö»!á­Ê­Í{vÌeE­cpwµî»Ûú[ùÅ£Y	¢eÂ4ß_6­ÄtäÔê_T°ñftç¹›ßŠâ.\r“±¿g÷÷>7úßÿ\0ªâıGêvÖmËs©.Ò‘U~¡{I\rõ\\Òêİîÿ\0Ï¦¥OT¸ä¶Û¬±¬£Ü÷V%í—~aÙ_»èûÖ³zæX­Î±Í9/Úï]­.&–ı±ÇÙ·İúoÏşZÊq öXI$†¥¿Súeğë¬¶ÂĞ[\\8—7Ú÷S½»¬m¯ÿ\0HßĞ«6}Kè5cşŸ}‘»Ôk\'®`Ûe¿éZÆlUéê¹v½§pkƒ¿u¥Ã“ú3î»ş.¤|ì¬‹ŞZ÷E ‡4\r ]!¾Û>ï~‘0™ĞGMWM9˜ŸR›qu–æŒŠ‰\r«Ò¦=Ïo¨Ë7?ó©ú7£eıQcÚÆôûÛëW­¹:\ZMƒcÜ÷~s?F£FNEz6Ñ.ƒ\ZOıR}EÁ·;İµ¤Ÿo¸?ªvÿ\0šäûé3İ±-¿V:ëlc†(±»€õZd»ÚçìŸSÓ×élD»êßU©­4;í¤’\rT¹ºò}EÑcõ{^ïOyh\r#]t{š×†ÿ\0aXÂÉ¦½Ö<~“s‹şw·o>×lÛÿ\0‘Ë8î—Ÿ£ê‡Xµ¶In=EºÖè²Âãô[íkÏõ{zOT~QÂ¯Ì›>™ªv¹€9[¾ÑşÚînêT>°Ñ¶Ñ/kÉ$÷q–{¶nCÿ\0œMª‚Ñl=²l?5¿»³ù(ùu;ë±Ù\'4<^GÕŞ¨Ü†àßI¤ÁØbZC<9£ÜÄª.ÀcY`q’@1\0Anÿ\0Í÷®ÆÌãs\Z,ı&Í¯sL‘25k£÷w#Û™s«kè®Ú«ªxsàï£íwşf„sä2î+DœP1¯ÿÒÁfuUİeÛH¬–0Fá¿çìOÓòicl²¸m”7ÓÃx2}G9¿»c7ôk/í]¨-$À:éû¥5VµeovÂââÉö“ıÛ=±û«PÑÓ¿ìi~ÇS7-Û¢÷Úóô^ı»‹ı¿¥õ?ËQvinMT‘º­¡À†ÃdÍo¹ÖÂ{½ŸõÅ”ü ÛÑ®ícux3Ã5ÚÆ¹Á/\\¾×;F=ÚÖ‘¾>†ã;¾}©ºqoÙ:Ó¯ÓÚ².}›Ÿ[ÌnÚ;Øön;½±µèÏÏß–ÖXí¬lâH0Oæ½»¿Iùå„ÛZKãóá\"$|›–æ´úR\\ó%únÚ<ô“t@%İÉ¶³˜ÏL\0à\'{&\0wæûvûö}& æX–Ã[¿EVÓ#úÌènY¹™x€N•D´ÏøG-ÉíÛDClÜ8ıİ±ÿ\0R†¢¼©=Ûøù„ZÍíH.şPüçnú-dı¤rÍouhÛfâÀ]©ıç¸·nİßğv.gÖ\0	ïÀ~®æí€Ö·è‘?/ä§H¶&Î¥ômaß¼mpp –—7k[´ÿ\0\'Ø«Ôm²Á ×¹Å Ì’[Ë[§î•Pßï2|KH×¢ÖŸõØ§NQxs\rså¦„÷Üæşoï5!\nxµvªsÆµîÄ5›»;]Üµßš¡}ÎÚÏq-¬¸@3ÁÜæÿ\0ßVM™¸–ƒºb ~—ÑFnK^e¾Ùâ;º#ó~‡ĞQğªş.ÿÓá]vÆ¸ƒ¤ıŸP[ ‘S¤jyPq%¥³(ÃZç2Aóğ>\nñ™±[5„BG>IŸqsNÑü£ô\\÷\"Úámº}73y1F–Hl~s}îb®ã,,™ƒÀ\ZkûÎşJŸÚM@5!ÆNà?èÈÛïşRBC^#¦ŠáÚ·Hl$î$Äóßûiıbà\\;uwj‰Øó½³µçƒãùã²ƒÈµ³ÁO)ä.ì-«Ñ3o¶µÃQÁÔy%ë2O-\"`\rtç9\0‚Öƒ©ÓAş¿ÉH™` É=ÊoûCíJ.y;O”;Çÿ\01Gmƒq>Î‡ğT‹ à÷=ÑZçnã&\0pàû“£={¢Qú6ì¸@{HÖ4&b?”£K¢@3´|tê½…†¶Á%¢Äxÿ\0›íEfĞÆÇ/L{´Õ¼nş¢x•Ÿ üV‘¢qi.:ğ[¬ñÆœ¢o—3À“Û¤Û¯„ò‡K‰İÎÒ<>˜”àoíZtÿÔó—ñ§ŠvnôûòcÃıw,ô•¡óı?GêèûõÛâ8ñÕ»×Ö~ƒ¦cÃù~Õš’wQ¾ãóè‡mq¾[ê}=ºÏ;¿á6{÷mşq\rû·ùÀæy…˜’’_(ßëó}VÇsüƒªÙ‘Ç~8àÿ\0ÑCÒá¯9$Ùl<Ò#»põ	ÎùnÉäLxJÌI‡}ÓÛgbÍ¾£öñèãŸÌüß¦§ìôíôãlˆõ#w-Ûéÿ\0%b$¦ë-ºÿ\0İlÇĞoÑÙnÏPÏĞß¬Ïî÷z?c²xÌBçÒO‡]·>keüÿÙ8BIM!\ZVersion compatibility info\0\0\0\0U\0\0\0\0\0\0\0A\0d\0o\0b\0e\0 \0P\0h\0o\0t\0o\0s\0h\0o\0p\0\0\0\0A\0d\0o\0b\0e\0 \0P\0h\0o\0t\0o\0s\0h\0o\0p\0 \06\0.\00\0\0\0\08BIMJPEG Quality\0\0\0\0\0\0\0\0\0\0ÿî\0Adobe\0d€\0\0\0ÿÛ\0„\0\rÿÀ\0X \"\0ÿİ\0\02ÿÄ?\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0	\n\03\0!1AQa\"q2‘¡±B#$RÁb34r‚ÑC%’Sğáñcs5¢²ƒ&D“TdEÂ£t6ÒUâeò³„ÃÓuãóF\'”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö7GWgw‡—§·Ç×ç÷\05\0!1AQaq\"2‘¡±B#ÁRÑğ3$bár‚’CScs4ñ%¢²ƒ&5ÂÒD“T£dEU6teâò³„ÃÓuãóF”¤…´•ÄÔäô¥µÅÕåõVfv†–¦¶ÆÖæö\'7GWgw‡—§·ÇÿÚ\0\0\0?\0åm±æ6’\'wˆL^bA#0“˜K‰Ÿi3çÿ\0\"€çHpÜÄ•¤I\Z­@/AÑ+ââàèAIÎ%Â	‚<TZaºqàGşbÎ¼Ã€8şª\0éÿ\095¯à«KÀI#Ä¦\r¸9¼˜×^!=mmŒú&%0\ri ‘&\"zô’¯§d¢×9Ä	éˆO¶Lîq-âxBİ¸“0GÑø\'a“RfGvÉO}VÑé£\'¹Ñ©’?4şê.Úù2Aàÿ\09+š×Vé«‡jàuú ò?´ùÆŞ	*G=çÜÑæPİk¤H<9wõ’f÷>\nÇé#‘Ştÿ\0ªBFê¿I o}ä´pÖy—işjª	‘;¸2ê?uÎÑ·Šœ\\ã\0üOúíN–±\\Ø÷A“§~ñü­¨{Áo¸íIÇşú£µÇFÄhZ4”áŒ/q†Ípàÿ\0Wó’\0«E˜×z»›;{|{©=ÖÔéq-×ò7&lFŸuüíLËv“ÚA‰˜?º•¶çÔ­zôÑanù\'H:º`ÿ\0ià\\îDÿ\0Õ Ljó$÷íşjp}›£]@=Š]ÒGdìè‡LNˆ&ÂAãßúÉ©qŸh×ºw›ZòLF’9”‰ Ë‰\0Q ©—=²ğçË>®“[é—Ö8É?ÉoõRnâÑí>í?Õ¨n©ÅÛD\rÙÓpşÒÀêN„ë£&¹ì³_£Ç$éûß¼™Ö?tÉÚã¡>I0¿dŸpğ1 ÿ\0¾§‰’=§†¸êÖ€z§®¾KØâ\Z$»–Åï{Ãº	Ô1ª\0kË·hCxi)ŞÇFòa‡éäíJÍ‚(h-7¨HçCôIOò”œIk$ò%CÓ0ZÒF ƒÈL6´4o0$Î’y¨’t° ¬Ç¨Ó°é\"5:ëû©İ¸VDèØŸıõç—\\8D©Ûœ\\L£»j£§Ğ(èÁ®$u.àÌ\"4¼\0âÒL@ ˜„Â¶	“\'HïÂ–İÃdnlò$@!‚XÄ´€[ûÇv‘ûÊeïy,i‚x>@~c”[\Zİ¯0îjÉÙÔGç¡±®éÜy$Şè€4*¼	p$™ÔÎ°¢ãúIxÓu\"%Á Gv‘ÿ\0Kv¨“CH¯Å–ã2Id\'ù*[Ëv€íu&L ¾ˆÔ¥ÿ\0˜ÿ\0iL3svê8.4şRW¸\n­Šîp-tÃ§@µZëšéÕ¤€Kg²%›‰‡i¿æÿ\0š¢óa™Ó¯Å	?\nH+1Ï ¸“á\'² İ-²{}P©­:¼\0“ñú*nâ	àÎ‘ˆÒÊ	Ö™9Î5uCkÌ‡I‘ óü¤‰Ú’LÉ‘Ûù)´ƒí™ióıÔOä ­Ä´O´ó:äµÉz†İ!¢9×Qÿ\0R™œkôIã¶ª$ìp&50[Æ‰OpÌn-$:@×C¯ÅJÂçW!Ğ9\Zø!—8‘´K‡úşj›î€4ˆ#¾¿ºˆ­GÑF÷ú£§Àú”@ç·Úç3ÿ\0˜•\0Í–È†‰‚QHö‡·G·C’ˆÓËùID±kœæL·ñPõ Ë¶´è9ÿ\0ÌS‡7vİâîşÊoç43µ£èÏtÚn¤Œ2İÂGÌê¥$÷\'pû”w²şÈóLïl¡\Zë=ÿ\0;”í‡E«{¸@S‹,sâ}â\"ŸÕıÕÆíÍ~àá?»ı”Ìp.Ôüá:Jmëõ]Z&%Ï“?DÏŠw8¸©Q®¤”6IĞÏÕRk6¼ëí\'¾©Ã^›­ÛèÅ–m—¹½ÄiıŸÏOµßHŸ2‘\rÚ	úF¤~êˆ0v»B?×²k¼”÷¸§Ú$NŠL¸øÉIÿ\0¾¨½®1K¹o\'lmò@”ñ\Z¥Ï¹’`\'‘ùÛ“—\ZÍZ]Ã%>ÍHs_«¼¿²œH%²L}!:–ÚÑ¾¸is\\’yÊÚ¡^âK¶ÿ\0\'¹H4µä@÷äù(–—Ø,à8ëâìk_”…Ã®¾)ûa¤’x)ÃÏ§ô´h×w[ÃÌ5ÇpñDq-‚\"#çùHƒ¹ ƒ*ì&²÷ıä™v¿œ™Ù \0–Ç\0ø$\Zó%ú1Şù¨oh­û¾”}&ÕÉ ?\"\0\'ö\'üĞA ?ŸüÎéƒôo¸¶ÉĞ ÿ\0_å¡±îi!À?]ºFŸ?¦ÈqŞ½‘UºícŸslsœI\";Iı×(î\'všpGš“šÒÆ§*6mÜHpÚ9øşji¶ñ’A7¿’7zÔH-ĞÁ)nvÈ;‰4LèQ2.ç	ú_ç Ûh/Ü\ZÈ×„É\0ŞëÅ“]™[d=IkF€ÎŸÉ÷$ãcÀ3r¤75Ìs½ÛÆ­<GæîQyÚKK·ÑÇö=oªGJe¹Å n-ÛÈ):Â\ZCÉ—p{€ ‚uSÿ\0‘ShkÉhî>‡ò”–è]–í®·Ú\'ò©Ğ÷Cœ\\C{ãä…HWL´ûA¯òš¦àâ×3vã®¤kü”è“BH j‹·$ö#^Èoy‘ÈıäÌö=Õ~L\'ôÇeÓÛÿ\0\"fAaµÏïy“*Áq&(l?\0B–à\0ğF1\0y¢DŸ£\rÄá³Ì\n#\\âu~‡À÷ş²\r-–Ì	*,$ûLíÖB£¨dFØ-—vñRµ¯\r}&óØê£[Ï@×²‹$’Û6`’bRÒ´7üÔë~I+s¶ó`k¢pâf|P÷êÓ-oÑNŸ¼“Œ>Dj%ºñ?¼ˆ:y\"µdâc}<eDØòÁ$íùBßšlIí?ÊN%Í#YM{&qjWV—©`i éå¢#Ü÷4: \rÄm1\r0]àˆK ’uìxJ%D+Ü‚I?ÅÏ|yÔÌ2dıÊ@r`pÎÈĞ#Elÿ\0ÿĞäËš	lè	\0?ûêk@H\'B@JN3yÚâãíÿ\0YFïSÛ¹²GhÑhŞ‡Á«Z…Ş$mÜrœ²Ñí\Z4:ê¦Æ5ÌÜ5|}-\Z%#’upÑÄ®Ôî§²ÛèÅ²Úà\'H–¦Õãi‚9$”åÃÔ$@<Bvƒ´·t7’ßüÅ!Ûè¯Nİ;‹¥Ç€9„OLµ­tû»‰ì¦cq‘¹§V‚% ]$<{-š{Õq=º(ŸiİÈ‘!!½ ù\\Å)~Í {¿zêR™Û‘İ¨¡Ocw´6[»Šf€u‡¤ˆ•2á·ãÇc§òš†Ø“ã÷ŒÇõR5b©BëUÚíDòJ!%¦I:ñ<êªî$¸@\0ù)şèO´j|4ğ@O}ÒbÍ¤6dÃN ¦}å§mb‹½ÄWré3¤„ş˜Ğh\0ÒI2ó4¬Öˆ¡zª¿kŒ€Gr8×÷“¢@‡Çó”›ìòìL¦{`5Àÿ\0·ïF´òÕW« im‰d¤@?G‡Ô%é—h= |`„îkkapI‘ş¿š…u­oí*h‚ñ=Æ’?”ÔIní-<öªáö\\è1¡ĞßÉÜ§º´OòGşM(ÏOAÚ›oæ°ş0?óšşœîiĞ?¨ @¯ÜÓÎÙüJ!¤6=¾©\'è=°¤µ´³Ø\\òÖ¯bÎLàí§qÛ\Z‡sÇæ©<;ha{F­òù&kKNÓ\rsÌí~(¯šAÑv¹¡Îw9pıäÍs7Nã¸Â;™[Zv\0ÔOÑUŸ[Á ‚`Æº®‰Ò†š®-n›yüïóTI\0¼“\0Y¤”VKĞ[¹ÎÔ<Çó«Üw<˜÷Ob”½1Ó¿Ëûª\ZŸÚ“s.>şÀqÚj‹I’Hhàr™H€N„©m„ˆpH=ÿ\0u©d$Š(l[ ÄÈà~ïÒM[Éi\'F“ßÅ-^ı>ÑÀşwõSï€Fà6jÈçè©/¯el¯\r$A/â‘ô“´0ğÒN¤?°&µ¥Ä é¬pØ«»è¸HÓóZÔÓ¡\'oı	#P¯t¡!3eÎ.h‘ÿ\0’RôÉn×#¼Ïù©Vét>	ƒ?ú-*$Ùê®št^Æ9ÍçS¡lÏ	˜\\Ò!¾Ó¬~t*RÓ|@\0Ï\n{[¶tFï©=]”5Ñ0xØY>Ø:?ú”ˆÛd“ÏÅ²Ù\0ı>b?ïÊ{·k;¹S°´Š,™\08ú‘â ÿ\0t™<”eä4%;[¦îHA1ÕBïJMV¬ƒFÍL¤¸ègóxCq6\0ÊàA=ãHS{€`sdÁñ”,MÇ@6“¨Ô>ç9+ØÑk‹¼|?²˜¿Ú7¸{¤Û@Ï\0L&4ú(hAOû”ê5`Â	Óé?ô”Ù\"\\Dcli÷QL’¨í$ZĞ u‰N:-2U n\ZÎš·²`È¯‰ Æœ‰ú*\r$3ÀjšV3Ü~ˆ¢oº\r>Cö¸n0 ğtşJví¬\ruÔüÑ÷¾½Å³´ı/\0PC¡åhÁÆ!BïlWo§fMÔÆ¬\'“Ç·ù$™fd 2·—ÜëÛù!µ€lhğø”¢M] ÖÖ½$Ë´x\Z ´ˆ3ã¯š0—¯ ÷1ôÌÌğúIfÿ\05Z,Öítük¤\"€ĞA0Iğçæ¼ Ğp<‘d87Ç8ˆF$tDaf³;ÃE¾Hû¼ÿ\0U‰qq:;°:(1†\\Ñ$·ÄM7apªdX×&|t%H\01ÜO‚,\ZÆÒxuFÜ¤G!Ü‚ŒHú ßÑEÎkG$4QsÚÏÎÔ‰ÔrRoª	hİ¸„õ‚] ’4‰ù£dÕZª–n €@×²™{ˆ 64c€ ğZı=ƒùNó\\¢AæIĞ@ä¸ˆÜª¯e“i s®îB›„¶#d$&´6f\ZC¼ÿ\0wj`p\\Fá«x\Z Jù’&RÇ0Cœç7‘\\‡ë¨úÉÛP 1ĞÂuŞ{š`aswO	hXúÿ\0[ü%\n½\nì\r{æèx…&¸G‰âT\ZKXAñĞrœ—ÑâÒ;Q\0j¾Ò¢7û7PA:~ñ>Hd\0	A®ãùßÙr#	N‡Ÿ\ræ ŞeÀÀ\r\Z\0<Á)Mª;Ó1µÆ\ZüÙ–õnİµÍ\0ñ ãú¨µD}ßüäBC‡ìœ!ctqQÙZZÒçNãÉì?wrVÙ\r 8‘ô‘Z$A×â˜44˜2O(ği_¢QÅ×ªÕkÏ;÷B%lm`\\ïİ×ŞÕMïkZK§^!¸@ƒ=ˆÿ\0¤šc’w’l‘]¹ÀÁ$i6vòï ã»è\"‚wwâ…\nÚåæ`L\0x	OQujf5¹ÍyÚ7ø)ÄxÈ|Bh¯q¬Æ’7øşïµHo#a>ÖÄÇo4\"H™\rm›ÆÈ2›v“ƒ6“0ñÈñCİ\rS‰¤lÅ.¹?K°?ÊJ#]¨aÃèXqñR\"\\wnóü¤Ñ+IèÓ¹ğàxÁ@\0m³-æOdMÁÀz†#*-\roÑäöÖ\"ÈÚ®ÿ\0® wïÿ\05—¬á58èAßòÕ¿I-Ãaiƒ ‘!ØAĞÉİ¸„V±¡ÓiHY\'[¥\Z\0*·Á-&t&{(±¬pq5’~!9=„İKxIÏ“İÄêGıõ:ôW—U¯\rkk®å;ugiiÔS<µÌHÚîª×%éº4\0˜ì—é@®‚Øê	ğO[bO‡æç÷’-Àn‰ï	{Cgx1¨\Z¡×ËÅ]<ßÿÑãå ¸¸î`?F`¹8°i\'o-ñD îØ™“\0óÿ\0˜¨½» o\0wÒ<–€mü¿ô­‚ª€±À84kLüÅ;Ëw€`ö h¢ÈhÜùO)zŒ-\0’<~hƒ¦µ{ Yˆ‡jI‰˜„àÀ\'‰Ğ“Øşoï$ÙÚèÂ&R±Ô†CšwwÖ?èıëÒìhµGVÜº·MuŸê¦–ºÀñ-¹×TÎÚá¸\'îşÊ›lišÚ\0n„O’g?ú—}-’vóÄËœ×HäåÍ!Áº3Är’“[d6\0\"Dõú)äİWà´\nµÚ€!ƒ˜\Zÿ\0iïB%ä–‚6“\"`~Oj0¤.xöŸŞÔ!e£@‚şt„´öLw®ìvVI!ÇkbL\'!0vDö>iêc,–ÎÇ 9BÏo°öî£Ø]5Ûš²É¡àl2õÓº!a\rxİ<©ÿ\0È ‡ûc“3%¶hd’{˜?è˜íü¿º‚\nÖ<nS\Zj¤d0€AIÖ>I‹ƒÚî[ÉìéI¦Ç‘ Ó˜N½Oõ¶GO%1Ä€à 7CÜ)‡8jİ@ÑŞ&Tæ&DF“Öæ–ËÈ\rE¾$$µh#­-iÀ;@†–èÌLÒúˆ»hsN#PƒË¬Ôè$?ÍRkõ-\"XL‘Ä‘ùÛ¿53ˆc¿¥\r\nûYØà\\á$Ú¦]\0»İ·B[Ä~kv¡44OÑãYåI®-’@sg°ÿ\0¿§o^¨#·F[ˆ$‰pÔÌëº£>â\0ãWGşII K~‹µú&´¿w\0	3\Z£®hÓVbÍ®k»t$Îw¨tvÑ »Nãè¨	‡ğ5híı¤ƒ0v“:ÿ\0Ñİ¹..Š¤¶Á!ÓôÚOı/jŒa°4k:‡?ÍRà<hĞ )ÖZã4Ó±J¸¨V*º×ª\"kl#sG Jˆ6—òA‰$ø#¹Ísušjî¨¶§\\Ş7P1ÔQP–š®Û=±3\ZÏc?˜Tu;Ÿ¸@“ü…\"!û6‘ léı·~rE­&Iø4Ì\"lŠV–Ì8íı&Ÿ›´ ¢ç¡’	vşÀŸh5-„àò	Ô7‘ªZ^\\ç7Ùæ!)^‚¿ôU\nÔ²Ü\Z=9Ñ:ï©\r\Zç\r7pÒŸš	ôÑAä™ŠÀZ	‚â>‘ÿ\0Î‰>Eü‹ËHk¾‰ÖŠ“ì\0A\Zv?Á\'Ë„À&$:GÍr»ü¼t	\"é VÌ8’¡ wÖ´\rĞÀäyüä*ƒİí˜=§…;9;¶¿ùAôÚˆÖ—±¾àí`òAÓjv6ĞG¨wy¨½Şßdlñà’¥U„3l|\n\"¸»uÑ\ZÓ‡6Â^%³¤)°‡È–´ÎèƒùÚgûÈ.g‡\Zh¦\\Âi¹\Z’É 5&ôñUè; ´Á‚<Áˆ¤ßt8Á3§\nvÖ&}Â	&#ù_E\r®tH‚ïúI¤Tµê›±¢G™°1†Gp8ÑL¸‹!§ˆPªk¤¼Ág\'¶¿š¥vÒĞI:ë¨ù?f‰ñ´V -dz…$âï)H2\0ï¢fGùŠg«¸ı Lí~r#‹_.ú$kÇò’\ZÙÓ}¿ª£Ğx1cŞİ>”ò×hİP§x,Î¿õ;”ÜCğw@÷k‘5Î°ÀÓ“¨:Y6Gaó~Ñ nvHÆm÷{ÿ\0ê¬“:ãŸì¤}æ	ùx&>Áéş(´Ù]uİ‘\0Nâ\0v¢4KÔi&	h=ù	˜K óôIQs>÷‚ï<%f¬l€Uº’G:)û4‰?ºÊj{wÎÑ·ˆì¦Ùh3vP‰ò\\C7D‘«ˆâ|Éw´\r\'·ıõÈeÚ‚d8ğ€¢{xç’¤u‡£ÁÔşkb\0ŸÎşRNÆ»A:8wŸßMcÜHnÑ ’\'jsìgªÖ4µÚ–‘;g÷šw—‚zÙei/xèˆÿ\0¿(3ÔôÜæÑ“?µJ\\Ïmn9:‰)Ím¦-s|o\Zôÿ\0ÎK_!ëÓÿ\0£«OµkK¶´€ûdÆ¥82Z@™÷OÉÚ‹í¸Ë,şN­Ÿ¿é!¾^@€\'Y>#ÛÊ»°˜öZkÖ7ØÓüäõçv€´i§oä©^Ã»Ü{2?ª„Ç{€’Ñ3Ì4FÚöNàîip!Îİ©-i™“ü´Ï©Î;€Ú#]gş¥Ò’\\Ù-<gùHíô½6±ÚöÃæevî–66×i£xrmüıŞCşü“«- \Z);Õ¬j7ã¨QcoØéÕf?ÎOy‰kEh´ñÛù]“2²èqHÔñÙD\r-‘\Zx\'Æ$ÆÅaÆáj GÒwÇDâ\'Q¨áI£ÜCtj.ˆ’{§P\0PEÙfw/\0}ş|9R.ÛÔşTÅ­iäOt¥ù(\"{·D’Šİ†~—x\'ùJ%¤µÛ€î:ÿ\0eF²O¸êgSÛçµ3ô¼×•  ê\'@|ÿ\0Fék½² ?Ä¡Ú¹º€{Â@é.$µ¼ññÕÔZ³ZÂH‚\Z‘É*t†ı(òBåÅàL˜ª8|³A(Uù*Wö±¶ß¶<»×ú2H òÁû©Ú5gÆ?vTnĞŞ7yşoµ#½÷š†ÕØ°ÔV5óûÒ£iî>:)ØØÈdŸënPhÜà7ßùŠi\ZÖô›ÒÙ6ºÎ»§çıê-q.\0Aì‘#ƒ\\q¯ nŞ6ˆÑá§uX·Şæë$¹Çß9’ƒµî\"\'€>)„»I Ãı©À<d´Ñ>J{I ‘ô§r`OÑúNå£‘§“TÈl8Dƒ§Á\n·{I †vó×M4šF£Évn\0‡iŞgE=Ío¹Üó·ÉC´°óÜ¢9±-˜?t‘ûÉDšÓ¢H×U¬sÉ“Üx)4­Çh?œ:¡»NÄi*lÒ%ç‚%+:~*ªÿÒæv–ˆtIÓRhscñJÃÛ$ødª¯äë§Œ­Iè)¥í›w¶ Ö¸Ö<P†ñï‰iÒ{)\0Ò}Î;jöĞí£~Éä8GıHQFŸ£â¼h|üú›}µÎÓßºGk˜\\öºAƒ¯å(²XîHşPòVvOé{†…Üÿ\0ÿ\0“J ÈÜ¨í@êßt\rDíÊv4×–ÁoÑN¿ÉDkœ²Á$ƒÏdÆ×0†6ğ5G†#]B,7c[Û$ó¨í¯şE5¥îì6’`|>’g–ÁL€yşR[ËÃ‹ƒ›´Kcş–ç Nœ$ı‰­n’8†ÁkÁº5ÿ\05\"·ynÔ—h?²Å Ñ_Ò>ÃáÈ)ÄØKHÜ§°ş³œÔN¢è\Z!ìhBe¯†Ø$ú*!å‡lïh?G´\"Y[]	ÿ\0©MÇ]?DzW_^¬¡…­š¸üR³GŸ¢8š°òğÀ:Ê3ñäH\0©ê€#Kô¨Ğ4z¢?¤ú\'Ãÿ\01Dh\rnİ‚G.wıõ	Ït£ÈÖT«l¹Ú“¤AÑuñQ\Z3qsĞŞÀCLHJr×Vù{\nMe—µ¦; İ#wâ¡UàÀ>©ú$3ÂuR$ÃX+§Ü5Ôÿ\0i\nÉFû3É069ïûÎjdxĞ¿ ¸ĞÜı¥pĞĞFÓ.üŞãÿ\0&“eŞÖ8üŠOôË>x\rĞÿ\0ÒRfÑ®£tåKÃ¨<^ŒZô#VòTÀ,Ç·„¬†÷¼À0;GõTä¹Ú[Á(‚5B;\\Ù	îˆ-İ§È‚›«—Á,ÆT\ZÒu-$H‘ÈğL•İ®T‘Æ[»o!şj“I\rØá\0òiLÇÇ\'à‘!ĞèˆçûÓÇu\n­Ÿ¼<ÁŸÍSiß¸AüˆqsÈìîgîMQ‰\ZOãı”ª\n\"ì³1»\ZI\"xN^Y´q:Ç:¡’\\íOÑ?Ê¥g¶uíÇŞõ°Um}YŸqPÙ×XÕDÃ`jc_¿â¥\"\Zu:jLşÊ›šC·\ZÀå-³0‹ÉÎºşTBâ1ô»Ìj¢`³y&9ã¿òœ—èà8I=ÿ\0¬š4ºëê]½_MÁÜİ­p—\r	Ÿë!ÚkŸpı ĞÏdä:$NœÂš´9ãoJGCûT7AÚt5”×mv‡ÇŸúIª/Õ£V$Ç	ì& ğ5iñ3N×Év·LØHl:\0Iÿ\09=ms@p[¬ù(Véîİµ¼q3şjs¼µ¤€XÍ$é ~näàv\'²_4óY.ú:‚g™P|À†F ”šV\"y‚Ò?5NÖÙ-ƒ Ö?wùp¸ò®‹v,lkEF³\rF“ı’å\n¤·‚F¤ø)\\L˜$hOtnß\Z÷C­í¥\'¥n¼0ÃµìØ:ÿ\0i7¥kıƒl÷$¢›Xa6Û5İ´ÿ\07jO£àIî‰\0¦À¢è±\r{\r°0ÙßÚú\n5¿Ô§\02L?œäœÑ³ó9å®\'üæî‡4\rN¬löL&ˆ¯³ú«€´Íl€È»@DIChn»IÖè)»Ô\r—5¡³·?süÔÀ³|‡jîvˆŸüÅ8×ûèbÒY3>îAÿ\0bdKIğ*N Ç Aÿ\0É$I‰n‹¼¿’šjˆì¶ÉvÓ1§hQy­¦½ÇîN÷w`ö€ ÄsûêO¬‘¹â]ËNšş)Ú‘CZFÆû¡{_Én“\0)ì¶%¤}!È*R4°ƒñJrAí2X8\Z \"5)$è{`Iİ·N;)6 ÔG„ÿ\0%FºçãÀF³ù»“?d€ÂwpBjÈU\r‚g´éw1!>Ó°ˆiŸ	‰üßjˆyd4ò€Ñ;©0=§NÊAWæ³Z`àÃC€êßò’Ùe®âDıi§i!ÄkŞS‘¸H>Şà¥í÷7_£ÿ\0|®?\nñD¤Kèä&¨‰`m¬Ö pïå$ğã§Ú5Ê\r;Úuá\Z¯M\\J®õ½Xú§ôd¹ÀH<Ô‹òÓÜ‘aòG/GnĞ“Z\Z=£SÜ%í‹têÆhÚ TÈn¡!X\"I&xòL7™|Ó‚î…?NÚv[¯v.c§Xòÿ\0É9M®µEÏk»Í<ÿ\0æHmy÷43Sx€:&‰\Z³c¹\'•\'\0OŠ\Zó2yR|ÏòDıå#I¡lwâN rg„A½íöÇ•Ö³i<|S¾Ïtâ4H\Z»*:Õa0yÕ0$º‰×g846gxÔ82Út’uÓAÂoÕ,´¶t òuİ÷}vƒ\\ÏÎiQk¡Í2Ó«|Gî¢Ó÷\r®<9ºónÎŸ2í·Ù‰!úºFš|S7pÒ=Àÿ\0È§\r{ZàxgùIVeÃ€CL`!Ôt%]<\0tãŸš#FéÎ¿•&Øâu3\Z¼şêQî\0êO`ŸäµA­gÜè‘ª€pÍÔj4ÿ\0´¤æhCşƒtñÿ\0“P2vÄİ6ZV›.íaÚ×Ïsš³@\0IÁÛ êI{…&ÏçpÕ{ ZÙqÔ“ 	8¥°Aï#Y•=À»o\\!9¤¼í‚fdv:Zm×Dæd¸AtRk^+-$ò1\">‚g=Çhà»Á3¥®>àLvçù[P4;öS17<G€çüåTúœ\r®ğÕ;6ÌÆ½ÿ\0‚¨çÉpÏ\rùÎHËA­ÎÑ n­ŒõcØ?(úMS{úÃ›ß´ÿ\0ß}AĞß|ã¨×¿ï§õ\Zç0@wcûßÉsPî+Æ¢®ÆşÔ2âğ9-ãåñEc\\ù{œC‡„!ÑßÍnÊàjG¸™ªd:“z/—JÿÓå@k°¼·RúGı…yx#İ\r<x©í­·¸<×L4’†C„Ç¸vÖt¾Õ¢o†¾Ö¨İzk/™£PHD ¼L~?ôœœ¸†5 Ÿ‡iLÃãS©“€‡øËI&ÏØ­¥°\Zb<<—’MoÔ;è¸˜…-¥’ã¡:ëİ	á…›Á‡şéîŒ´³úª\Z•\'yú-Lÿ\0ßT.~ïkZ\0¯ı$Õ—zƒf„xöF>™;\0F¤hcÕ6ÿ\0º\\t=ÑŠÆÍÛ¾^	K˜Xç[¬	ÿ\09¤)48&[àA‘ı•?k¥¼;ÇùHğŠÓÒ«ï«V^Ñ\Z¿’ÙùûQ][koÒ™ˆ†÷I„À#RŞé9ÅÍ‚ŞSÄcWúK,İtCéÃhãŸ½7“´X„Já³I<”f9± Üâ”qƒáä£2<Zí¥Í{êO}¸0ÿ\0\rÂaıÿ\0UÜıÎ1 $%\Z¤Ä“vÁÂ@0ÁÀÿ\0L˜p4ãû	ÚÇX`p9à)’Ö‚×KDÁÓóGıı4G~—¢L¶ğ@mqnŞ`è{¢\ZZ×ö‚ĞtæûT«­ÆxµÚ„V5¾Èhv¤‚düÇ§üÔJ`lâĞ[¹ÏĞğÏ\noc>“‡»ıqá Èñ\n¬D–ù§Œ`D¯šÓ2egDVÇÃ$§w¦<|ä•=Ís†í\0ğLG¸è×´òOı÷jaˆUªàN—z0$ívŸ£ù°Gî¨‹¥¤éÀ$ñıf¢XÁ?7°\Zš˜H 7ÜÓÈä A´‚)vV÷:`òF¼é¹FÆêKb5¾ÔÜˆÍÒ4€ß3°È1æQ1€M¬ÒuÕÄêdğ9 É™ÉH5ÛK£V‘Eç†´\0ÈÔëÊnÀ_ä×{]c§Aş¿EDĞû¶$û­SÜuvÁ|üùp5ºgM®2@šä%¸)Råà;v®‘ùÚŸú)Şñ´4òÈ~ı K¼¨a¥ÄIüÙÖ”$‚¢\0¥ÃÎÙ>×¿}ÉĞğ8Ÿ4œlhÓ^çÇş’g´“¸Â\'j«Pï²œÃêlh‰\ZÉSs¶€Á¦Ô)îbcI5Dµ°kÃ´üî”Ô†Ääw\0±¯i;Hàğ£`‰—H¿ôš¦Òİƒw\'â¢ú]ËL	 ˜è-B¯]×ôHé÷`Ï/İ	œ-\0êL\r†æ8Ê1§ÑR7¯ÕPÖ-dsÊrÆ<ï·âŸÜ]Ïy-ì™Ûdä;@©Õ¦Ş•·ãª×æë&B#‹K\\âú3Úª“«·8L™Ä5Í&u\ZS@#Cµ®ßP·§,—ik h‘<yÿ\0Y“À#B7G¸ÇQ1>¨²ˆì{„¿QíÛ\Zÿ\0œˆ\Z5CˆÏıê¢_m4¹§Å²@şNÕ1µÄnƒÀ:Šh;š±z®­…Ò½GTÿ\0æ[ıR$¨¾ÇD´“\0ûògŸÒoiÒdíEl¸îÓAâššé/•\'A}Ã?M£è‘¸¤uÿ\0¢ }i‰­÷<pT\\Â^ {cŸûê”ÄUö1ƒĞ£y‘Î¿õãÀğS9‡Yù	RlìÏ2š.õ\\v^ÊÀhˆ;æJ.ÚbI\Zÿ\0’jŸpG\'XşJ…µ¸‚ñ%Ü»]#ÉÈ–WwîÙOoæ¨ÖààyQ$ÃZk\rä7½Ò%½ı£M“ûÉ—­ö]ZWu¬Ü,$;káıV¨K§ptì¦öéôÀ’gúªe§kEGkš\"xĞı-Èğ’J¬\05Ík»¹DkZ^vêÒ;ûGö«²ÂC@4şÒ±¼ˆ#°á:Eöğ[+ ‡CNç…0är›¡àAù\'\r@~JA†K†—‚pàáKcmJ¡ûÓµĞ­è°ñ¤™”íœk®ŠC*.ä`s¢T *ÉÑpòcÁØM¼ˆ‘§´r!@4n2HüÓdN‹€\Z²yt\0Ø“Ïš\ZûLgƒıU3¤‘À=Ô$8øæeC3e’\"ƒ&†wJ,\0&æñä†ÂZKdË¸ˆ<\"5°İy>\'OóZQ’$ÇsˆÜ>Ğ”Ïm‘.¾SšıĞ\"œídwN«Ñ}‘¢ZAy<N‘ıU6˜±³AÚÊC]y”ˆ™à\0­RM²sŒ¸\0á #¸RÚ\Zh;Çd€xÓB‹ aªÂ~Ä6€@K´¦\rp½µİ?ÊDuUÀ:Àó*-h2I‘Ûºa‰âó\\¥04±Âctq*Ä”ƒœ×Î‡ÿ\09C·Gú<&ÈŠòH\Zù­{·4·÷\'­ÍÍ-‚@-wÿ\0È\'sÃj‚!äğDûQ(¨9„½³?‘4D™èuİ$|˜¸9ºLˆç´y&-úRc„W°4Àã²¤‘\'Aåÿ\0RFËAU3^öˆtêãÄy&{œÙ\'Yi$ŸüÅH4I×kt‰ÿ\0©D5±ÒÃ!Ì3çùR¢U`1m% >uüÑà¾¡!á»`4Óów)·©:;èÿ\0æ©X~h%ÄjÑí?æ~ziÙ@ëİ;LèÇËunª;Äkî3©\'Ÿå\"Td<}-\"gNşÔ\'Öè\ZAÓà{¦›\0Â¬„•>D¹àmì@#û?£U„n¬¬ sı¥:‹Ü$ö<\0>I\nƒ@}„5¯ÒuF¥é>wä‹\Z¡­„]-xÖ?ô‘`‡lfá/\Zü7&`—n?DhóOePœïh2Ö`şëĞ†$ÙÕÿÔä½–û\\ß¢L8ß“ún °Ö=À\r	•$\rd§ÃBÏÌ:´ïZèV;^40xò?ÚLÓcO¦éi\Z€uñIŒpî<p<“lñ/•:S7n:îäø$Òâ\'@Yãÿ\0T¢çn&FÔõÀ5 ùù£bÕZ2şqÅÅ£_¤|PÎÖ’5 xóşr²Öu$y!İK\'{‰ÛÄå8ÀÕüV‰¥©mx2[\Z?•ôÔGîºfGıNª-Ñ 4?÷äàÃŒkùxˆ­}WÔ “zh¸0ØQ¢}Û€™álçNáxhÓSÙ8}‹JÖÿ\0êëí)ìqö4ÀîèRsäüX\\4	PµYcéín×gÅ”—¼Æ€rU‡Ã˜wiP\rö~›(ƒW°H\'^ås\r‘¶tøJ\0 <9À†§Ï•6›7K]µÃA<IıÔKáÍKO¸˜²šuÔ~Šá¦Ñ9À6\0i¹™şÒpçj4‘ \"uş_õZ‚eÎŸŠ)o¦›Hˆl	ñ÷¦	\' ]B¼Ö®È78ÿ\0¥¢ïp‰ê~‘Î0]&Cƒ?%á¿lAîŒe@~#RØ.\ràsÈñBL’ÍA<\"†‡7ŸqàøU\r•Ü\\íÇAÇš|¯M<©h­uó\\©×²‹N²bx	Îƒ*ò;$àÊŒc™*%ñùüv@ØI‰\'âšf6)JZí»šÓ> è„[ÙÇßñ˜énPŞíDèîGŠ5 µ³OAğL±#K¾Ql\\Öm÷; |‘İ`t´8‰~™Üæ˜$vM7µ.‰º§f­0N†8„J¤µÁ¡»€İ¸ó§æµ	”¾ÇÈÓR	\0¢ÚÆ°q¨ü;wJ7©ªˆş^•J¶½J‹…¦=Ã¿	† ¶}§Ç…o†˜ ™F„\'kˆÇcàœ%g_ª+M¡º4\0>™ş²…¶\Z=£ƒ¡LliFˆQn®#éOd¥+Ğ~©üRh^A\'·Å=ÑïĞñ¡UÛ¤ó¢):Fš~TøJ‘ ·ç@?Hş*Oy™;Ì¬†îüígQÿ\0R‘y-Ş@×ï)k]¯TuïZ.·ÌhÉì¹°75¡Äˆ\"9J·4‚\\O„‰Óÿ\0$£Û÷	ıdzwµuò^Âá:@p\'±ğ¯ÚôŞ™ jp{GõTIk>“}íú ğGò”¹l0		)½uì•æ:¡öğSÚLúnhiïß÷P·ğZ!İá-ÓÂ`Ÿ¥:¥Å¡\náêÎÆ¼ŸV§iÚš²K×^ÿ\0¢b?uOÒ$Ã´¨\nÈxG„İë®ŸÕEŠ«ÙoHÍÛ§iÄ İVİÛ‡¹MÎ\0G¼\'Pìƒ+ñD\\$€Ø=¼J}Â#¸Ô”[ ÉtîÃÚâˆÓÇş““·ÙqŠÖÁ»q¢s­{€&G>i\n6Ã@lŠ[ŞAlH„/SåÓ÷“Z0‚Ø“¹º†’#^C\"tçú¨Zg_$VsÛ´í³SÆ‡ó‰Ö¿ŞT‡_åÍZ†9Îsô 7şšK«yiÜsX\0Úö¹ãSí éü¥6‘sFòãÿ\0‚wËCRá#Š†º‚„ZIG—ıù&9šs*Ë\Zâ\\A„x!»·Fİ4ş	hE5¼CbÀ×‚#İÄ)6IÁqEû+KD¤vì£èGyiäB<ÅÕ‘ÚDq¢A\'‘Â˜kc^ê†ğ9ïâœou¡AÇBJ‘?Å4Á1Êbøäê—š—.’‚^E›Ï†Ğ5ó‘9ïİA¬Üï u3ãû©’³Uİ|hZÏ~ÒdöÑDs¸ñùÇÁJÊKA-:Ç²´™\Zù¦Hu+…£ ï|AxöÑAå ÃD\0dğ˜9‘§Ôìàï©ØßP]$QÙ:\rJú­R7}ƒôB#RD	¼˜C\Zğ\Z{¸êBbÑi&t<\"0½Ì†µ¿ %<kß‰iÓµ-¹æ‰Ü8%KX’9î¡»s½ÃQÛ„``yv	ñ×ªÙiÑ†°€aÚÊ“„>åÌ=!¢«±üÒQšNÉpwu^¹`!ÂZ­?DÄwJ2¡ª¥tM1¯¼ø*Îâ`\0î\'ˆSc¤’á¡ûá=…® €R—¨Z£¡c£H-Ö#qğ•ÚğSk£éúü¾ŠwR@ßãä›D>¡6Õ¶=¥ÆtãÂ­£nƒQ \Z]¬H‰#57ïG’€“ÕRÔ€:%c€hq\ZúIYh\r×º…d´í=õŸë(¿¤ñÛ²y‘üğ$Œ$4hxòçóSnvà÷	ÑÎ#N>‹P˜æêtÀ\"\0]-­v²š\r€’)ƒØix,pÜíÇiû_š¤GÑak‡e±ùÉÛ²4÷C äËÛ@‘â|ÜØ‚Œ¼-7p;Äûoä·÷Ò±–×º6sÛOì©–\ráÑ-TœíÂ”D4 Ÿ ®-©‹vDú2|P­}­~ÂG¨nßQËƒ@“vƒî†‰‘ßTe|=Ğ\r«BC4Ô™’×üå7¯hıéËŒ¨ñòüäÂÆ‚v’<Î© \r¯Áv¯ÿÕæ\ZÊä¸êIû“¾;j ûâbø(¹æ	á§‚µ¸€\Z4mròD8OˆLm íÛ‹X²{÷H–€vÀ=şiQî«šÖí#^ÍŸâ¤ĞvÃ½³ù¢( ‡;p\0ÁS-‡<kàš!d›I•HÇ\0{ê¤4DüS9±«x<ù%X/:èŠxĞÒŞ–È\r™íƒ Ã|ƒLÄè{¦{@¦@ü¨Ö›#ªõÕ¦çÈ<ƒä¦ö»HĞ~@ ,—Od½B92?tZÚïalmÔ(0¸‘”æÈÌ¹;˜¿‘áà‡’¼ÙXˆ:LÒĞßårT¥ŞíQ›£F¤ó§Ş¶¦@î5ïãşrªûI~Ñ î8Ÿí«À	iã²-öÎ†G%	Ù\ncCpÖ{šĞvs¹ñÕEk¬{@·V@Û:ñô¢1kÙhÜ;ßT‹äq¯hñLÍ›—ø+ŒôÛê­Eluä Œ{=]äâgTVîc{‚‰{‹£XñòO¯§@´­&5€9ÕNÿ\0tƒà läL5…!ì‡A&5î—;tE‘ÕK\"@%øï™|‘Ä\\Fçxøí$ş˜Æ[¨H„n¤†@€|=A÷\rBºö–ğt< Ú$9Î{B$oQ§\nøKëmpÖíf£±âT(/cÑÔrãæ¹Hz…®öéóE\rëgÁ|µÓ£]\rĞ’xqGòœ™Œ7\0Z`Î£´ê`ºÆícI1ô† }íGencZ4ñÓ„x¥£H2áÖDj§i‰\rF½ÔE¤	w<¶DÏï}$K\Zw4ä;’“Za.;`kÿ\0’N1×ÓéZšêÀ¶Ûÿ\0¤O$Ïäú*!ò<‡*Ñyrïİ*R^À@—H(˜i¦ª×]YQÒ\0Ñ\rÍs]aÉ¿|4îB•­{6ë:}Ê­¦Q¥Ù5µ±Ãy.#Şòw\'/æ#åÂ‹µ¥Æ	ì™±$Æà8\'A)ÀĞ\ZUêŠ»êšA-Äpïó”Xæ‡m™ğyÿ\0©Ú¢]íÚ=¤kƒ(mdñÉ%#-GTèSïhp`†ƒ£‰ÔYCs›ôL°bS×Ñ7’¾\'óZ `@™‰ÜŞ#TI?À…\0¸n¯p#ÌşëRkÜ„Æ¿ÙLÙ$·Ûûİã]ª^£Iİ®ßà:İÒmØn$’ ÀDx ‰kk:H\\Oç!¹ís\0ì@ğQs¥Ü\0<ıõ¯SWàÙ­ÛN 9¼ŠNIghtÄ¸wƒ´x§i×_œ©c+dj‘®h\'ï„Ï;½¼\nMÚ\0{|<ĞŸ$ÄÆºx§HÔkò@\Z°0] 4Ô§İí%ÓÈ\ZGıVå\rİ¼	ô‚¬§6kN;œä¨ë¡d=1Î\r??tßù.@uƒvæ{Iì‰´Næ³]wÇöa2DB‰Ğ@Õf2Ç{ÉÀA	ë£ÜA@Ğëÿ\0~StğÓ´4NÂâÒC¤-L%Ø»4ƒ#]—u@€ÚÜğHÀ#Häx¤×x“à;¨\\ç1§HÂĞ	Ğ³s€\Zi<(´’Hh’y„Í¬–K½”«—b$‘ÑFƒ2ó ğä$JgîÓn§Å»_?ÉR\0´³¨r‹ÈĞJPwÑP;$n®$’8ì¦ÀçÉ\"@î ÆK†’ŞåYs`F“`	Ü®‘­¸uvS°\næL÷E%®‰2ÇsNƒ±Ná£cT]é²‰«¸\rúŞ]¿e¥îOBÀİÒ\Z\\Oqÿ\0R›8Ø½Gªì\0Æ¶\'óOş”BØúÜÑ\'Qâæ\0\r;Öág³O\"áİ4Ñ lGÊ@\'§é$n2íŸõSØÂ{Ìv„™YmS\'áİ&µàqÏŠ“„UVú¬½wÙL î&\ZxJ!ii€$»‚Š\ZÒ\'^ÅDË] —éP¥Ybk³W#”2LÄ{‡>HÁï»ºGW‚[óDÄt%@¨öûuã…Òæ@yV	ÒZx!¸¾ˆJ!1‘`Ùé\0é¢rİÄ:#ÅI•´€NƒÁH\0×i©<$#¦ª\']c—@şªN{†¤r¦ûîƒà˜†;§Öšh²û¢\"H\rïÙ1©Â×Íh\Z³ò‘~©´:®³Ñ$˜òQvÏL‡ëşiÚˆI&Gš­iA\Zö#ÅGĞ¿¦«ã©]£C\r;è•<A:;·şEßš«ƒ\Z¹4{`Ë`H@Oæ¨àv®›®î§VøiĞ÷õ(…ç¿Î{©\Zd‚]-€×üå\'4GÔ8Œ…±™\r†x˜*%¤Óâ¦<‰!»I\ZHk-½Ø„1fÒA×ÇE6C›¦„ê{(z%Äït54Ù’+[Z}I`“ÛDc[°ˆtH#Sı]ô˜×:$Ìğ²Ğ>ˆ¬÷L\0Ş¿2ãU¦ÏÿÖæXĞù6\rg²˜®¶˜ˆ‰PkıÅ€I“ğR{µ\0qñ[ªîç›¶60ÄÊ#k½³:kàvˆN¼ƒß#ßDxuµ^‹42I»¥~¼	ÿ\0È¤æ	\0jŞêDF‘hŞ\ZHä¢Â0\n#Ú\\İO:HCkCOåM#QÙpÛÅ#¢ š®m!ĞD„}Ú„îcK€<$A;\ZP n¨—wµ¾Ó€Òâµä˜³Q·P8’¦K k=ùN¿E¤öFÑ´È >Å1~¼Èğ„î­¯\'iS,hnÓÇo4Ú=6M†«ì%ÜÀ<J%NxçfµƒB¾_õIá‡è»Iª¤!ÌÒJvêKƒøÏdæ·HĞvBuy-’œh±Òÿ\0º(õûYn$GÑîQØÑÉt\nšæ7QÈEîNr‰x$s¼¾J.©®\Z·M)4é\'Gv×²‹Ÿ¬òÍuY¯DOkCå£lhd&mn.!¼r¦÷Iƒ¡”ûšÖÀ0<|S(Zë4±%šF©è$éâ¢ù{ctxÔ¦e{Y¸’OK®Ú+¢Ræ¾gİåÙV´V¶tÍ„FÇB\0:B‚®4\'ãÉBZŸUÑĞõG]B\r‚^>	˜ç‚\\ÒZÀaİÿ\0Îj°6±à}ÒTUcMÜò&gUiûßŞOöÍƒ]ÅÀW<7@èüäCh#QÀö´pt„·84<q RZ-\"Ù½Ä\rcâ|I‘OîŒÂ#ŞA@O(vµÂ@„¬‹Tw¦ušö‘Îİ¼Jt;ƒf{Éc†ÙÀ¢\r\rTE•Èß	-\Z3Y{L‚{aÍl3ç¢Nwru|Ò1kõP‘F¸Å%£s ÆšOùÈ?AÍkÚK3Õ¸Ş{ü»(XÇí˜ÊXÇèé]~n%ñ™êÖ>Øt<ÇsX‡·F­ÂË&]¨o‚™°ıd˜Q/¡\Zÿ\0Ux&º…H`Ù\Zşq5ıÄ›[^Aq\0èCfSY V=¼˜×ş’#Xê´smpäß’«;\\b«Ó}Kô`ï\'„á¬32×y¤ÇVĞà$=¿uN¦—KCA$Ë\\F£úßÖF\"ëb‚wè‚7<4D“É0ŞÚZv‡?¹GàÔíÆs`†Øö1ÃY3È@FTO]ÓÅùº,ĞòÂæ‰Ó_R¬ŸÍO‡*m¬CyAÑI’ÍrÊ”DŠµ†[°İ©Dk¢‰#PaY`—Ë£Á\'C‡¸Á\Za}Vñx4ÚÍbÉh@N\0ğ#ãªµé‚İ¯ç±Bû9iÑÒ™íT/ş’î0wa&yù\"5§fáÛ·t2ĞèœØî\nCMÔR;]x IP¨éîø\0TÚíH2GŠrÖ)Õz­ºÑ‹,`m3ª›œ\\Ş~(ohİ \0vH?‘HĞªÔ2`/’İ;JpİP™¶\0Ø:/ä§´¤Ÿ€GJ´kt¿Ò;·@ïçğH±¦ ÇÁA;`ÁÑ;ItÄ<5JÁúªŠì´\\{Qº·¸{‹·\r^é‚HñGh\0ƒñ€õ$Rt †³Zvƒ:cBŠĞ\\$ h¤\"O;O#¶©<–·ˆ±HF‚‰¶.t\0ò\'ÀuH=Äñ§úşjvnk¡î Âq@ ?ÖDÑ:£\\`eX\r \nª…csåÎáv¢\0„`4×KDº\"²°Yï:ÈÔÊ;XãáÁÕ‡	â‹^=6óçİ	DqWÿ\09 š#û‘³ äÏ	ˆ—n€2¹Ñ¯ Ş˜Ü^íŸDq!.!×NÁ\\\'¢B÷\rAàwLíÎ¨sO}Zèy\0|É…X¾\'Bš%Ä+uQHc€.€ySöÍ ‚ø$×ˆÑÑ¯qİGš,ıC0<xN_:öğB$íïÊyĞ÷K‰T±1Èì ×8 nª/7w>\'­–ÀLwQñ‚ú›WŸh˜î4áEáàÌRh€DşTÎˆsI•!:¬Ş~³ß…â\'|ˆE-°6¤¢¢ğ`nÕÃDÉãÿ\0r¸à @qlÇ’™¬kâÙ=÷\"4;R8<Ôœ\'i-¡ÕVz/5‘²;ˆP{L·ÚØÖ/#OH\nCôÓHĞù¡¡Óª¶òdCAì;„îœì€!DÖË»óS‰¯±\n3-‚yñşGõ”lw-’Çvò\\Õ\0âçn=Ä	\0™İSsƒ€¹ÜÕª>+uQVtŒv	9ä\'O£[\\A\0ä©XwİóHÂ’ª³ÜëØ(ƒ¶C>—pıùN¶È;Dæd”íÀìKy<e\Z4z—ÿ×ç#Rt’e°ˆãˆ*nw¿n¼ı\"¡sˆ1ğ¶\rUösÅß›)v¦$‚‰yÉøÂL®Â!Î;s?æ¤êšÁßwb–µštµ5óâœ;^PZã:\r(„ñÂh•„»_ù³ÂwFª\r§\'ÅH’DwDZ T<TûA?0N³Û‚œ¾¢\n\\<Æƒ‚–ùĞjObx\r=¼“{KuğåñWÑAÍaÚLøÂPÂê¾\'Å3ª\ZOÑ\'éœG¥Ü#­„¦Æ€;üƒa´ptQ®7\r£yDiyh\r‰îzıˆ4:}«5äÑ¯tv›AÓºpí ¼ƒæ£Xnó Â~ºQİ\Z1“ Òó\r²ÛéÛÅ=‡kt&8¯l%ä™9({ÖÉ×ŸôÖç\rÍ|1Æı/ê¢¹Å’\ZÑ\rüPÔ‹Ô\'c[¬[úßH¡šÀi;¤që¹JeÛ§âß‡u48´Â\'^ˆhıAÃ}ÇÀ¢›_¤w·ÿ\0$¡nş\Z!£°J§’àtwû`kö/««à÷)˜Ï[F¼’­zL{H:4Ï´x¤ĞÖ3h‚w\'Sé[Æ\0ÓæBú67BGï’R-6ı€c^šæ7¯bOtÄîö°kÜûò&éÙEM\Zu<“À%6¿€\'éwEôÚÑ%Ä8¨š‹ ÀæòIÄm¢ñ`Ö¸êØ;wSxi8ğD†´FíG\\*|îì5	VŠ½Q\r¿Í‚DxrPßSÁeÀq=Ñ‹€$;‚g8¸i¨ï	¦ À›[\Z@ÖTC†âAıØD‡\0€y\'üäÎ¢DÁÛàR ô@#ªû¤í`ĞrIwq3ÄvO^ĞŞÆDŸ1âµ’´¶]ô»\r\\ƒk}Fûíú#”æ½“îù\"5µ²\\ŞcÜ~?º£ ËC§ïj¾ÀÔ}TÆÖÎ<õñ(wÒ]¬ÆºO\nL{‹È\ZiáÙÓ$‚8”î(Ğ\Z}‹lƒv…­†í$i¡ÿ\0šâØÛ¡ìœÆ²€ÓÁ<¤ĞÀt÷2|¢4Ú“VA„4K·8qóì…k‰ [#ËüäPâyûĞıGHg·²2ª«¯ùÈh›kˆ3²TÜğâ!Äw!DÕu„º6¨”Jhku>âuòLÓZıâ¼˜ïøÅ¬ãS›\ZH×P `{Cb~HEÍŸÎiÔ¾\rO2¥‚6•ú8`D¦qnè#X‘	Œ:=Ô¾Â^¦`x Oâ\0\"fG~şH.ä‡à¬\Z¸	÷“YE„4‚$~w3ÿ\0œ¥(’6TdV$´¬¸óäŸi°û4e	Ğ\0\"v™O(´[á\0\r\0ïóB$E$P°½˜îF£û”Û\0\0ïã*AæPpg1%¨YdèUí‘¤OAÅû¶é§)-];LVÒf`÷@ÙĞ$WTo;›Ì~Dõš‹ƒ\\Ò ‚uşÊÕİÍG’f²÷°O‡ô“<[_Ó‰uŠßñ\\¸2ZØ-ä4öÿ\09·4€LIæRn=E¡Ï;TàTÏ¢$ü{§Äô¥„PÜå+\Z^Ù<»\'ur7\rÈ×R–Çƒ©{]â†²ÒòĞ$øp% «{üP[PsÉ$Ç‡\Z£F€Ç—’ºÕ2«YĞæé§ñşÊéµhù…79­tı/‡	Í€ øµä¡§Dcppi^Á·A¸˜¹L×lq:ğîœØt\'ï\Z$*¨İ­éÔ\0öÄéæØÀ\0o)Ã¤!§±SpããÂ4:YîÔ5Ëã]İˆ#}™ÌË¼\0díóî§04$ö$ø 1„™– —VÁÉîºd?à¬·kI0»”äµŞá©ğHcuWè€Rà4Ö‚›ˆÚıiF. h` 88Y.x·ŸöQ 6EŞì]X:ø©´ì\Zkà\0LñIÑßÂì|ºx¦’#­.\0É°lˆqI–k.3û²…T;Üñ¸ÆƒÉÍk„·‘Ø\"$N¿‚E=úË~”ëğI¡®ÖÃíàq„\'¿ıJ7¢\0$™thÔFª:2\"¶´´\Z*?›ß–Æ‰ÁÚfÉiì	4<¨Õ£l–º]ğ€£ê8hí\nw<ÁJÃ¸\r	¢a¡òé]ï¾¬ŒxëÌ¡º\\ÂIÚ9;ÓÀî†A1¸€‘¸¦Î[ŠLB›úY«·´ôÿ\0´ˆİ@væòÙÿ\0©r\rMİ`\0íĞO?ÚE¯oæ™”Èw+¤¶Ç‚#NêO¯stä);ÄjJp|t(ˆÔ,â;¬É	àÊœ–ÄF¦tQ:™|ÁÑ8h´êÿ\0ÿĞåì­­wº ˜Mas6ì÷4˜óNë=Ğu’yNÇ0N@-}:½uJ\rçá(n Îäö\0à] \ZÉFRZ­£Í9s‡÷\0›x¤üÔInÙñQİlÉ]Ù4ÈƒÁ\nzDñCÙ\\$ùJ—·wŸiHÌ{€ èR,ƒ?Æ“†ó¯šu¨£ÑdcR;Yğo*µo÷ÎéòFk‰v“ı©Ñ–ˆÕ•88½¡ @B{^ZIF„«®k‡ G}R-FÂİ\ZHU Çy‘Áì˜’â&;©\nƒ;{{¨ØÖı6¤vø\"\0x JîyO÷\'eLíùİÆˆÀŸtŸÑXcœ@%¤|¿½(Jˆ (0²\\uí<ÇõT\\æ“07vé=àè${éÿ\0I0¯p—öàD¢O@³m±ò\ZØ\\¤$øœX}ƒsL:GòR®Ç8–Ä\ZS/Z7k«K˜†°~ñ=¸\n/˜ö>	·tåMƒBò ê\0óOĞè·faƒh‘¸B[šÁ ÛÛD/Stb;¨—Ş\'àE®vññÜè Kµ\0èÊv»IñáM¤Iåä–êÙ¹Î\rhûø…&ÅN^GnˆÚ§£]ßºy­€¨üï©VÁ¯ÓyÄöğM¾Ù$4’uIöÁãnï-‹@Û¤ià…ø¦¼pp=P÷	æHä”ûµæHî†òŞdü4@•\0’eÄÈwƒcB†mZæ|»©0±“ ?Å\'Ô^á`0G¿Õ@ñVŸ5¤Uë²MÎDùÇ¦ß¡—Kûµ3öA\0{ùğP\Z=°u<Âq&ĞJƒcqwa:œ§êñòğBÓ.\"GÍAïˆŸ¸¨+†Êg†½¤$k\n¹!­‡G‡¤,¨8\'±ğ†æ8A Ç’i\"]@):UFÒNéó2¬t9”±Ş£‹ADë2¦ÿ\0U„7H<Æ€£ Ô&Z–v2½€î˜2Še„Ã@óKÜ@0Lˆm–ÀÕ£ó¼ÿ\0ïÉÇËìZm0~ç”òÖ»O¥ş¿œ§ Òwÿ\0¢¢úåÁÍ5òF¨h‹î±{œt8Q~æöæTÚZ%³:’&4p÷Ô÷K¦êk¹åÚ7éxüÃ^_¼´“\ZwV\ZÚË€#Ü9·öQgf­ĞL&p^¤¯â­\0CS>î4q÷¢³ÓÎh\r\'BFš¨=ìçå§ñQ.2ÌEÈ6{²qp0uş)¦5İÁ\"æ‚L¯ç(‡‡±$ú”¯^ª±ä\rDó?³¹N§k°	=tŸúA£ ~*Uín£ŸÅ0Å¾‹‰ÓÅ“ƒ˜ ˆY™üªqqîgI4S/2?\"Ö“¦ŸãàV Ó1Â-%Äû†š(\0Öˆ^TØïŒw\n=SLƒÚPßlv$x\r$)íK¼ ÛXÜ\03ñN‘5¢Øz²©Ğ\rn`g²D™Ğ€;–\0\\N½Ø5á íÄ§‰óMÕ‘­³$hAçO‚rG¥¢Á-ÃG÷\'¡\\»]R#]½Êï/÷$\'É\n¤€°j@\'Á3ı7‚èÔw‹µ1¡âBM$\0¿É÷ä¸ºR©k\ZG¸“··ÅA¬<¿Ï…`×¹²NŞÊ{ÆIˆä afÒ%£Wé¶b]ÙEÅÅÃÌ¢úƒïU{\\€e@ Y,ŞöÆ¢Hù&k‰çä%EÇ{d\0K{oÑäÏÁ2R ÚáÑ1~ıA€<Óî,\0ê\'õM°PH=û§ÚÉ€âgX”¸“Â–wjI ñ¬\'–¿ÚF hâxQ\r\0Aö·¹Nê\ZHpˆ§k¸·O$eÌ:—Ií®ŸrPÂd¶gÇÃ÷”Æ=r5.ñÑl\Z4(“½~i2kBm\r% Ïú–CDO.óRô\Z}Ó¯šAºÌ”hõEŒZKH&In€§õøo=Ï‚gÖ×ˆãºUTĞu\ZüxKÕuÑZUõa`ys‹ˆ‘Áñù(z®yˆˆèVUnÖOŠé¹®Œş×Šd„†×^¥Ñ ¯Á&>ip˜†<d)6‡ì|8öğR!à{¿7‰€ˆ¨!ì$qÚtUÜ}Ğ\ZÉ™Vø“ÄvUšæ—m.òà¦dè-|:è¯Ñ´\0á/ä¸pú,Sl‚\"Jk%ÃM­~€%Ke¬t;åà´™{xiÿ\0t¸İ&â;èŸqçŸeuçğRh.;tÊ°€N\\T @à¤\\e>ÖÓÿÑçXÍº´âcqğB¹®cƒÃ´\'XS9ó>à?Ù´h×1÷­ƒ\\=šíˆ/tû |uQ\r¬ËKœ]÷\'\"Ç: P–Öƒà~ôÊ¾Ÿã.úÿ\0ŠÆº\\\\\\thâ{£\\Z@#ˆQp0Gø¤ˆ<;¢Tz”.¶Àá¤ø\"€âÙ?K²mºé•Ÿv¨DÔ¦DVƒšÑ¤Ï#º}¾:Ê>ÏRgNú)\nëk6òÀ·¦XXw4Ê5o%Ä}ê.®ÍÛZÓ©Òë¡­$¸ó%ÄŞš©‘®«’\"9Óä†Kƒ´î®\0paL€byO—ò¥(y3´‚|ĞÉÚL7wiÿ\0Î”¶N‡ÃÅDÂ	Ôğ!#ã¢C&¼†€vÄhšÂLL\0îRqÓÜÙh;ªÏ²´@¼„ÄYH¬\rîaKÔnØgcßNpíŞ$ş\nq×\rÃ¿Š`ŸeÆ)@6ûc˜à©] Á÷Bccw´\'¼(¹Îx…\'ÜêVYØh‹kšğc‚¦òç2G>cş¥\'¹îp 4?Í·a$GnßÖCAc¡N¦–Øş\\Ò tp„=J°^à7\0Š\r–k·i5#”$\0ª$öb×>uÓX‚,4´ê@\ZOdvµíÈß$ís‹¶Ht¢\0ş?µ$~Ûi»M¼Â}déÇÿ\0$ ú\\íK¶µºÈÕJ¹`\0ÒO·áİÅ±ÙZn7bıÄ1¤G*GÚĞ	»øÂwYPb5Ğù¡ò ™ó(muª¼ôfÒÂ×í4‡\ra@²LuÇ„ö·Û\Z÷†§­›Æ÷\0\0ÔğşJšVÚ¦k¢íwLEfZ\0q\Z§um3°û†§šZç–Dß>JBkB´x3™¥ÀOp*Ïph$w<œ¤Ñ¹ÇŸ&Öë\0šI®†–u.YdöşÒ˜ö“ºMnïæŒ^fI0DÁÑ;\\Û±Äí<§	ƒ²!\0ÚÍ¦xüÑÎØR\0ãˆÖ ±ò=°\"}éŒkçŞuHE6Ä¸0û]\Z{tá/TOÒ×òÿ\0%7 Ç¿ÜîÚ`lÚÑ\rè#{D(‘§U½@[¸x˜Qsˆˆ\'e1c\ZHi€|9P{H\Z8àS‰4€ï|ÉñRmÍÚO\Zkæ…ca¼‡xö9`ã¹øMâ•®¡K±òá$	üÓßúÈ“·[“Û’ªûv“¤rQ«l²\0Ü<õ?ôŒ‰T€LNâ=Ş^eş÷Z5‚„×´8‰‚ÏõK‹ÎÒt\Z‚;|}+óG\r+sˆŞÍtãHBİcÙÙ<âšÏkôïÁ*¡$	’—˜+£«q•í`$Ëâ%;›_;Æ¿wá1 ø”¤†‡wåK§eš¢ÊO\ZL|›¹„ÈÚf@:¢²òI‡¢IA¡ƒÜ\'&)ŒjÖ÷ìR-&\n	_t„ì\'tµ³ğÖmD‰˜\nU×0tñD]„iªïs´éi?ùŠ°‚$Dë>ûÅÈ‡Ói%Ğ^tÛÈPÈ÷6»w‡d¥z›@®Ë±Ì:ÎáàL)3OnÙ@c-h—A*{:|>_„´ÔRHñHã¤ƒ>eÿ\0‚bş\'ƒİ6¤@ä¤ejv¸Éiò }ü}Ê\0ÓÛ²y?Hjx×„ĞtM.ĞßëîQd8ÈÇt½î#àxEÓFDj££).\ZÎ‰KŞRQ\'Cùíc\0ıÅÀ‰ìÒíƒ€’‹ç\'ÄÁõ}#<í*2w¯­ˆb[/#ˆI×6gC>Zì¨½î&A\Z„\0r£”ÈĞ~,‚ î˜½Ö¸7éÇhGk§³Ÿ<ÕjÄ8Aø„qc§ivÑä¹;Ìt5Hf`\rşÊgZA\'4!{ÿ\0:?zQXÖ·h<‘â9RNßjÂ\0İ“´xF‰4XŞtLl\0\r~‰M¸¸H”ëufI\n3ÊŒŸ¹)™ÿ\0Y@”Òàÿ\0¹9ya@IÔ\'Ü;˜)Z©vØu=´Sõ¤pP êCÛ$êîÉ	(Å8\r$p“ÍL\0P·K`Ot¾=´!:ÑK\ZZ÷Iqkºx§85«Œü´H8À|’™9Õ7†xÚx¤:ÓNékYWîhcº[Ÿ¶ ì¬\\Àı¦6Æ»‚‹¶¼ûdë{(Œ\'^Ô¼LPÓÍ]·P9S­ÁÎ$ˆü¨ße¬&Oƒ¨LÜw²@\"z„&+²¢m‹I$Ió”Ò‡éBw4Ì¸r{ ÌëÂl.ßÿÒåí¡Ì$“&e=. Î:lÄ|<>	‰Ü 7ëdB‡<ÊÅ»d“©v¿jÚíNß„Vmn\0w%#cçDDkrƒ+è™kÑk»è(Åävãâ°ö‰ğH€zªË\nêdê¨vÑFö!>±jF§ºc€İË§DvØ#}Ò5Û@1\0ÎœŸí(¹ì ÷!Eïpé;Ÿ¢81¯õP% %Şâ#Qİ@Û©ówd7Ãã]½ÛçıdîkHó	¼DìšR›éHL)4ƒ\Zïâ«Ú]#RÖüQv9Ì—ŸşI\"IğQ\ZR‰çÅF\Zç\0‘ŞœD\nt´‚@Óâ•ÙªUP»g`-\rZ	UÎ6ºÍ<÷ÿ\05Y\0™l|ÎêÉŞ	J î¨’6ac+Íèà4lİRÜ\\Ó\05Ñ!U$ºDŠaA‚àhQ\\—4\0ùÓHğÕQ±Å­?ºx\nOkYÄ8şD\'†\rdü’‘\"Â€º_G\0I÷#¹ nƒ1æ•¢w8€ØâáY®°ÃKÈÿ\0]©CUKE:‡€\'Ş?æ‘Ì¢:Ù\06Aã;¤#ÏÀ\'š£KEŞ¬.!Œ#v¢u‰MIh\'‰<ğ‰[65Î{5#YÔPôÅM{Ø?ª?ê¿²ÔÂ\r‰.±TÅ•ºÇ£š§âeaĞÓû½¿sÚ›phnÁ¬k¢^wdêÒßúHĞÄõE’Q½›œ7q‰äLÉk£qÚ;)‡K„ºjgT[k‘¹ Î:Èø5È]Å°(Xøa&5ó0£ş‰qò€Ö–“\rİÛıW5óÚÇ1	DšÕßDÍmdá4™NàÆËÄ`ÇÁ9Ù`ñ\"\'E\0éö¶<|šË=àû 5„‹§€4û‘›éì-©æUw´· xx lkºEl¿éNß~áßç%ê–Efwvÿ\0R‰SLCGõˆşõ\'07WÇ·}5v«R‹Z[îwsÿ\0‘B4¼ù–§S Òêœj]í€;Ê‰¢aBÖ9’à¢ûv†¯úîS.$$ªÌnâw|5\Zÿ\0)6F¨Gªèëd³{ìhqv½‡t\\§o’ºl\r€G€…1ŒÎîú¡(A½·µ	\nªİ\r 4íŞb5!l1Ï´3ı¥lĞÂĞ]3 ëÌ ¼:\\Æâx´¦ÎOÒ—F]±¤nÜO—\nË¶ÈnÖÎ¤ÂÛì±»|Ï\n`û4t»ó„i4è€?—ıò%e…áÆtPDGıíˆñ$vRsœà@ÓéNŸæ¤†ÖÈ$k¯oê¥Z’‹Ò‘]UPç4Ÿiø¨TL8§#ı¨Çsš†Î›OuÂÇ‡½À0~éÿ\0¢šcê\r¥Í®hnÀ	“Ää¨\0æØŸ«ŞëK¦\Z‘İNÙÕÌĞÌ\'n/²65İ—¨ı ‘Ø¡î.‡;æ7y`Ü}¾=åJD@æ•’Š¦0ü›ˆ\nU´¹Ş-\Z©Ù[lÚO·]a8F‘zêˆ:4íİ;¬08àŸ$÷V6ƒ^‘¤J‹M ìÙ»Ãÿ\0:CPhş\nÜXLY\\Ïpä;n†ñ\'·õ’}V–ƒ<ëÏşEDIlÉıè$ì\n€î„^aä—N¿Ş£Aöğ8Õ;ëÛ;ÎâOÒì¥C,32¡õ’#ùüÌš%S»]HûõNO¶\0îVK‡Ñ\r;B‰­®\0˜»~*^ÆØø¼\ZÌ.\r$\r\'R¤’Jwşğ[3¯’†ıÑù¤|ÿ\0\"eÖ—²ú½irç@\ZiÙàƒ\\‰ğO¸ëàZ¨#ğdLqÏe0à]Æ{ò§¿P<u)	jƒíÚ5J@U;¶\0N“Aÿ\04ûŒÁÑ>Æš-¢½ØÕ¼Ó´%ÎnÁµÃBã‘šç\0Ìk¢-ØA$Î¤ÈDëUÿ\0 ¤JCDB‚\ZH¸öI•&Áq=Ó’ÓğÕ¶·hãÍ¿ùÒB1µ&½¢ĞdøòŸx=âa–ì;›¸ÁI”I`ø8uĞı©âÓo±®Ğ_ÀØò˜[$iğFxãO\08Au€èíJi¹Õ ßEÃûkÙ8tÃxñQO„ìxƒáÚP¿×‚ît’ğO°~ì™ï	Ôp˜ÕB\'F>¡ÍB¶)icKôŸİUiØÔø+nq¸G “¯‚²n\0Ñ	hkˆyƒù©í‡¸\'²+Üİ¾ï¤5Õ…ñ%»¾H\r¨ÕwÛîDvóª%aÒAprXÌisœáÎ€r¬nÚ9ÖS¡}Dî‰4ÇîÔ{;¹0÷<øøAì¦çƒ\"5ññA±ì?JOàœhjÑe‘°ö:ùvOe†Ûq\r€=£º}¶©uƒÊ]“Uºî‡°€4<¡¶¦ÄFÉÒ<”½+&6ê\" ëV«­-ÿÓæíe›Œ‰3¡n£ûIÚÒ¶ eL9­s£]xˆLux?—²ÚÎ¾Œ·mhãNê2$kÛU74ˆ\0Lñ>jhkœ\\un„Cµ¥À~?ù¤\Z]³·*v6·’à$Rhôõˆ§R…kûSÑà<\'Wcì0ÒfJ{Â´$ö*$‚\0i Ş)x~Jf\0h tŸÎÇÌGÍ;^ÖÈ;Ÿ›`.çà–Q¯D‡mö™C‚!Íò¦ÅZ$`j$â€ö¸83Û¸L”\0Ùx‘;°~íöq:x+-°¸no~Ê³cˆ’xhNìh;‡µ¾Z£FÚŞÿ\0¼¢AßEÆÛ\\öÀg‡3ı_İS6ocù!Wayq-˜n‡ÁÏ†ns`(’ˆ–åtd@ÚÓ.	ÜCÛH<È!0g¨Á0	ÖG¹6ÒßÓÎ¨‹¯hª@c‹’53Ê+áÃNbSk% Ê¢ğ$wsâˆĞR76€–³FÀóñò@$¹ĞÑ©ñí÷+†½à‰Çş’f†Ô!¿2y*)@µA <d‚¦òã ‚Œ^È×ér5‰şªNkctÃ>ÖUíy\'é@¬$}_1mVí ´û‹@ƒÌÏç6?5;¬hv×k\"PïOØ_Ìj?7OßıÏä\"8¸—‰3¢1•Ù:Û\'Ù¹ÃO/ü’h.kF‘À:“ı¤öA\rL„»uº\0>5şKœ¨ÑÖÓ¥íI+~æm:„W€\0‰\"P@sDıŞj“\0î10’Q:QQ\ZØJCv†³ØáÆŠvò×<4\Z	şªNÑ£d¸ÎºŸó”vú­ ı!¬0‰íÿ\0  )Ö1Ğ±¤‰™ıÔÛnÜ	y\Zà£X±	IÙâê“.õ;®®1¸–ÁŞT=;ŞéxR®·C}Ìñ)­¥çíwoê§\".®»­uù1“¼xÿ\0ßRÚMåĞÓ;¤ÈQ¥µ—Føàmª‰`cxL‚€Ô_O“½3Ù¡0†5%;eä5º`÷Bk˜áé¸[Üéÿ\0\"³sD4mnš÷×ùIàÚÒ)œA ]Áÿ\0W$n †êİ5Â=¡»{µæ@U	Ûa$:ë&Jâw]\Z=‚ã¨‡êl>™˜cRaÍÍx,Ğßùv—\Zö–ÄˆÓ_¿÷R‰²F¼QI\ZÅwNãÇ0Óÿ\0Tˆ[dH2% (RÖ´±­$:eÓ(¯ÇİÉâ4µ‡tluºoóÃòIì//ñ jGşIH¼î“¡óCõ€œN°R°4%[êÁÁì˜€;’£ê´4ê;h“ÃH$öˆh@ØŞ^à#R%)Ë€;F÷–îç¿aü”R,kLÀIUE•í†·æQî>Çÿ\0÷Ô£!Ó_\"£§›9.t<Ãôß;6Vİ²Kµ:öJ°ç8³YÔ-ªÑ`/>ı‘5`_èÚbÑ5Õ%n›Œ¶tİâˆĞl:k,Ô†\0ò|Yíòƒ>kÑl·YôË@iï¬ø íÛáØ•kcŞñÕW,xq7GŠ2²\"|SAc#añsGıõÊ\'to>àb êŠvˆğâìiÜ\\İç¹N>°ÖD:(ÕU¡Ò4lè{OS†iã‚Üƒô5‰äè¡VT/ZlíÈóÊ¨ö5³°|;iIÏ÷døö7y‘>a#Ga²†ŸV»Øç<‰ñ\0Ïıe³]d\r?’ÓüÔ\0’<{r]5»N¤ğ|“\"¡ø¯‘ĞjÂ\\èIãU7~÷Ò D•,0æ3Ê‰´Ã\0şo?ô‘ºİm^ÌœÒğ>—yğMè\nÈ.2o5:ìq`à’.=È#îFÕVFŒ\\Æ–èvùN€j{«5×¤¼\0&ôk“Öt@ÆöÑBT×’$D%¸	ƒ#˜Op{= 	Bk,y€Ø\rTdÑ 5d\Z‹%˜x$éÇ	‹@üQ¾ÈÖ·k^KÜRm.\0j;òQáŸ]ÅŒ[iĞí4$ß“o`–¿ƒ¨TÓI’%ã“ıd\'9¶˜Ú\0ìä‰#B}]Š€^ŒÛ%§İ1À?ù%&Ä3óàSÖÊÚ$O&êR!¦Á´wÕ8¢ÒF©ƒ.\0v*$@\"$v„Sh\ZxvHY\Zı“èt+l¢s74/<é C}E¤nïßÿ\0$¬okÇ<•Zù’<kƒæ‘\"o=‚}Æ6É‘\'VIÒ$™ÕC\Z\Z=Äsâš\"MŞÁq5³\\PK€$AwRm,a–8Äf5Ä’Ş\']?êQ	kLm»‚#w¥¦gf®ÚÆ¤AãDZÚCI’o’“™X2\0:ÏŠ}Ï:\0f5^?98DìA6‰Òï¦ÓØ©5¬¬Ø\ZéiÓ:“¤0aûZ:ßÅ;ÉtÛÁ?êåËOÏûò˜f„¸|CíÖH\ZwD -îL€t2Ú·Ö@…3¼H?=‡¨Ô@J† õUÚMö84ÍÒò*Àqs„÷íğşRÙcn—ˆo5Du»a­ĞõÜ¢†—v(²KZ­tfÓ³poÒ\'Yíı¤áïhÖ5&P\ZFâY;¹×„J÷‰Ü8Lÿ\0¹<Kè´‡ÿÔç_\ry$Ë§M4üSY»‰Ó”Ï¤8Ä”ªpkI\"|‚Ù½{9Õ£\'ºÆ³÷~(Eá§™<îà£ø<uBum& ø”¥}+ª‹æ¬|TCÚï¢ı¤rıùN¶±q?HñÜrßæ÷s¬÷M<U{÷	7[vZİÀK`4ˆ±Ü}ÁV\r‘2u‰Pc[ö³ƒä› F¤Ğ]›³CfF§·åR$˜3Îš¨hO¼hGÍºÓÁ*İi„y7ÄvM¼Ï´J0s6Di-UgÒñi™Ø8ÛÆ¿šŒ‰^¤F‚f8™ÿ\0xø£’â\0h–Â«½º4¶`u:şò!–éÈŸ™Nú­!%’6–‘§>\n½Õ¹Àº$~÷ıø5IÎq‘3zŒ‡pI’F©¹$+eĞ»¶hŞÖ¼ÁãU¾±Útğ„˜7pbOñüïrÍØğKv˜Ñ+\"\"º*\'Å1±Ó\0*>©“ÁíÂƒlÚèï\ZÂOÚï/Â\\]AWt›ËL|<”m‘\Z’O#E–î\Z’;×µàî\r1Øù$dhŸÉ j‰d	:ÅÉ.“ ‰Õ&4—¼Hì	Q-C¾‘àûò‚R$\r?—õ™\"\0,öÔ\Z]ã¤	EkÆïhâ\0j‹*;[¼Edv\'Sãµ9`-\r–Èå: j¿ÆA éºÍs„É$Ş%;\\	ÚaÃnºD5\r¤ëåİ®`h“©à”c¯_µÑ–Æ5ƒi$®ª%£ùË%Ñ¸#ú¿¼¤¸·v£ŸÖOu,úNÓM$è¤#Ke€ëºíôÈ§N ×ûJ.w¥¬OqÊ†æ¿ØL–NÓåCzs½­’4=ù@KOÚšFLjã\'ğşR•lŞ7O\Z Z÷ÀhİÜ÷£RÂ‘«*³-´L´)kŠÁk9äOd<†1à:Í<ŠFÀ[$C¿öæ¹»6¸ŸŞòş¯µ>DpÖ‡M–Ä´ŞÖ3]»cNåÕmÑ‡ÛÚyPcŒvĞê7\r‰¶%Ò“Du\\T7Ç¹ø£7tmq“É(-27FŠEÒ&t	Ñ¡²Ógvd2ÉhĞ´và*–±Õ·R$>:£nÓMtÖÿ\0mš´êŞñşjlèëÍ]Á¶‚DpŞşjU¹ÏúN×Ãşü´ºv‰øµ–Ã‰‡;ÜYE	HÏÀ²H&ç-şMGq09°?÷äö‡ÈqÜÓòù¨4]c¾‘×Ê?ªÕdi€ìshq\'Äò^Â7ğ	z…\'mÚˆú$qıMß¾£k,s€cDw#™ş±PÈ“z}Œ‘\0(ú_ØAĞÂªçO};|Áqkƒ†¼Ÿûê\rIùÇğQd6~,v‘ {b4OI{lösªfÖ÷\0\0ç…m•ÒÀ	\0ÙâŠt Izxz¢RÇÍkÔ×VÀŞ]â52RÈ.-ˆÔ\rH×Ş µæKaÃ_üåz`h…g‡Ó@èÁÅ­¹Lqß`#ƒ\nıpZ7o;Gë%m5Xíä\rÃŸ—ŠpeÖéâ›İ.Ë§!*¡KºÉÒc]’…®l;Ú{v2†ö–Äd‰Õ8o®í#ó´”l›Š\Z5îw·†òL¦\rè’¾fTßX¬1 ƒ¬5ScÒÙ3Éÿ\0¥µ _µDY~‰øÿ\0i\"Ö:‰$\rUÇ‘\0˜ñT_k\ZKZ4ñS$F¤jº$‚W@3Vídm2îäªm±À€Ó\'·’¶İ¢@/æQ„®éºås·À×M@–‚ÖÄıê5<t‘ä—;tFï÷Õ(üÖ7íÛ\ZÁğB«ïa×ÛØÊÃÜÓ©ÑÛ´¥ë v:¢aˆ&É\\$@ ahô‰“üQ=&Ç¿oŒü’v’á:Obt!1¥ûO¸tÓTkÂÑF2ã;‰ˆÒ5CköËÜ4€*U°7Ê‰Úİ ì¢uÙV›µïÌ%ü¨RàÑ¸éà;#mk-şÒ˜Qp–»az Aâ´‚*”I NÙÛùÕFÊÈÒa3I?Aºå;!{µ¤4K¼¥Wo´‘&G>Íx÷kîíÛE0Æ9Òïqäÿ\0çM@\"İ ÕˆD¸€57wG#kt Ç†ª^•‘ j˜²¶|ôN!‚‰®ÜïÔ‘æ‰º§ˆ#İÿ\0ÎPpi´s#¼)×Péó„í¡Q¦¤’>c)ı\'&\'¼wşÊ3˜Ñ GeÉ0Â~~Hğ„Z	qy:Ã¿ıJ++tÉ>ÓÏdDhàu?ÚD-öû‰´ t“ÙN´´Ç÷(Ç¨I)ãüäğÒÑc]yRÔı\0~!97;pjÍ#²!°ƒàÑ¯Ş‰»Ê]àtBxw\05¢6*µÜXøpÌê¾Ò-&&g²´\Zÿ\0™!ÛakdÃ‡Š¬ôÕ ôWqkb\0œØ\"™î†Ë`8yƒÜ£H\0z`|yÿ\0¤ˆ7±A»è|{¨ÖÁË¾08\nvmwÓöùù¨¶··SÀ	ø%Z÷WD¯±¾›¾‰gã†¼4içÉIÎ{O¼<Rõ7j5ÅÂwİ\"Ã6SPÍãÌ¡³!ºµã¹\rø¢6À.y$l<Áà•GN•ûª¹kãİÿÕæ.–öu<B“	ÙÙ­:¾h÷c]ô6’òš4ìUBí²ÒÆ‹_c¦­\rÇfN/kµà¤A-ÜÒ#¿PËöH­¤Ü¨1Ïqön ¡Ä.µ)á;¦sÈh.oõG€JeºhtÍP.p†°9M]¬˜å³(‰jŠÑw¹£F™$}\"Û$ş&×DÒDŠÆÆØĞxê‘³±¥\n‹k7{·ññù)‘`lèçx«4aÙ{œúÚçzb_·÷;ûKP}Z{Z×µû÷íkXà\Zïwï»İ³ô£‘Ü•àlHxkK‡<üOaÀ&UŒş•“Š_ †6=@~˜ö·oÓ¯İìTõk¶Z×İ‘ıf§Æc¡Zb{*Ö‚é?$©jÚä8Lj&ùêõMceä6´Aôíıä_Bã½¦‡0K€i$	ÛîÚœA=\n„M­bæ“şşI1›âĞy­Åw¦ÛKK&HnÒó¿—ı…ÒÖ‡nÑË„À?/¤£ââ5}×ÕXí ˆ:	0ÎĞ¦´Vøt iÊz´²\ZdôÀ‘Çò‘Œ@’b4çù)ñ@è\nÒ@#R×®=¹Ó¦İ…°ĞˆÌM¤‡ÎçDGµ\08A\nta. –Ó$\"oq0uiŸâ¤èsb\"8×Á	®p\Zj`Æ©ûiİnú¢·ÖËÚÙo\'É\nmÇU¡¸¶g°\n¤,~êÄ8éá§ï(Î!Ä{ß\nñÑüdn|Kdòù¨îx‰.ho~ß‚³elo¸p8câª:}çpè‰”\'q;ª4R\n	w¨f±3İ;İ\0Íó(¬ÚíìæO\"P¤Vã¸ìN¤”ê\0i×r‹$ù/\\4=§şúšó´í|’Qkp{f5àO\nt³k à“?‘|º›f¨q¸»É&·¸İÇd©½æ—	/áúéüŸê¤üGµÚ6Gx×ED«Š>­h²£¢zš#ÃIñQsàe‚{B•m‘é‰n‡‘¢\n+™-F²eN VŒV6°{@á¤IT€÷C4#Çû*Ñ® ğAÚèĞiû¨oªÀá¯´säŒ¢H¯ú*‰\0ßæÖ-,úMÚ{øíwh€b	áøîs¸Úù(8<>*2è¾ÁêÉÍ!¦5oêûP÷<\'àI.Ûîˆq÷å\'QYkÜI>cÅ*\'nˆ\rĞ´@*Æ8¬0¹â\'R||½ª«ëÚe¦A\0™O]€8Îã§\'FU/P](ØÑµéR×\ZİøeFXy-$“Ä„=ÍuQñ(­~Æw÷è|gû)àÆöµXA­îÛ0Ñ#O\n½ÕØ^\ZÇ\09ÜLñH<‡F~ğÿ\0¿ \\ëğÃ¾ïåmÚ–IoJ¡{ı¬êš­‹±‡Xİ¤Çµé\ròõiˆî#÷’†ènÂt˜æ¬ˆÍ®q6~%Ä¨ÁéØ÷^{ø#Èel\0°\0O‚5M¶ßv­\Z‘Â–Nı7Aè™×û*Ö3K+İºGæ4îÔD²íQ´#o¬’?`hh\0 ü’U´Ï1>Ñİ\'i;I’4?r•L‘î:ö¶¬uÙ‡¢ğ\Zâî@\ZÌ¨¹Â$‰·dàn~Á.\'ÆPnvÉ© ‚9=õœ›)€\"JZ-;\\×ÎéÔ#û0î€Dˆû•j½Fº±©Ÿú•e®­ºjIğK®=¼Õ!E\0ªËŒ’\'é½µ0‡4cÁOÖö–’ÒAÒy…Ø³i\0Bp\0uõx ’zhÁ–8»’85>ç@\'5¸è	ÛÇ0PÛK½@ÙÉw—îûSOÔ‘[®÷¸Îí#YU²éæy2¯zv´Øğ×UDFj<· =Y1ÖµÑmqt4I„ZâHtıÙĞ&¤>©|=‡ï\"‘$»‰àŸûëQ„ŞDûéInÁ±ŞØïÂÛØ’…¤ŸhP®7ÁHe­,Kl–½Íˆh:ù¨š\Zâw¸ëÄLû¼G”“â™Ö‰i\ZrÔIš\0>IÃÙ[ jáãÊƒİ?Gîá9ÎÚ;‘ÆŠnµ m&8jÑT§¸:4Á¹ãÚ|x@©£q‡dAú¤ÿ\0hÄ“qŞ%+HEe­d¸wÔ¢Û$è&{¦7³Ü`y²“6D‰\'ºWÙUİrK]¬k¡\'ø$éÛ\'·\nÛ¶L@™.íô\\‰YifàA×F÷\0kµ²BkKkÚ\Zê{ù\'®Æ¶­ÚŸ5a»íÏlw[w÷p—	»\\B¨¢Ü\\}Æ?uIîtsâ‡c»Ùßæ×z~Ù;ÿ\08ö?ÚHo¯D´]˜îa.Ü#À¢\\C‰3á¢©$OğP}šûb	ø`º”³[N’‰Lß|JxÛ£î+°I&8˜MãIál±şí£Ÿ!Í<x¡—À\0‰ó@sË§Q§‚t#e=$[ êbv€Æ‚r?ê•lJŸtiÔvVİíd»ı|~Š0¾Nè•]‰io¼L÷î àĞDŞx×ñDeoq:ğ7îAºE°NØ3&¥#AQ\Z¬û\\îĞóš€çïiáE+€í:ëÁ&PwÂLv$*ó™º?ÊÙc,.ŞA“\'OÃé+³ÚÁXŠ›¤÷qüÕU Fç	{kù¨Œ GÒÿ\0È±f•1mÆØ%üâ‘ÜcÅWe’Î=€C‡òœˆ´øñÕX°H]Î‰ñQm\Zşë\"KJ–¯D·÷e/l€LÇdiLMuèN›b{ÿ\0œ‘°Æƒ€¦æ°É#îAØàé\ZµÚAå#§E?ÿÖÔL¤dÔĞÇY[÷8i1ùşO²­‹ÎèyOÉ{(xşp¹¬;#vÇúi©³ríÏ ×î´6·:!®ä}»[ÿ\0V“‹nÀvÎÀÁ±I­nö}%f9¤6üXLİòìÜ<œKßU¢@qná«IoÒÚõZ\\\0èÜ¯IÊÁéù·¯YkÇ1î ¿Ÿ§e>§èë±pÖôç3!í¬;Ğa$;h.Æûö[ô”Øò­Ç8ıZ%–Úæµ¡ú şRĞgG¿¿[ÁsÚæ‹vÁaiôÏµÖÿ\0ƒ©ùşŸøD*X*Ë5X\Záô\\,šÑ¹ŞŸò–û[Fi-u[l5š]Œ}»ö·Ò³ÔÙùŸé?ë©ù7D6[#§aÔßSiw¥\\îkÅôl÷şwó^ÿ\0ğh}?êİÙ›ó½ >\rD¸ËAk›eooï1hUC)mMÉkšê[\0€!Ûªô›c½Ûş‡¿şh;=fû©uU×íî’{nÏz„ÎCHŸªñ¹Z®•‹Q`ª \r}Ä´Áún»oè¯Ùú?Ñ)^Í­	|8CÆ¦±ş™Îó›[MmØİÌtòí 0mfï£ÿ\0V©æäÍ¸U‘H&¦ï\0<»pg¨Í¯Q\"uµú&¶‡–µ¬~Æ8‹	\ZÏ»ônwÑ÷~ÿ\0èÖ\rßW>Úæ–=Ã)Àç9¯% mİvÍŸCèzµ±k;©Ò^êïat0CÙ`-üË}»(õ¿á¬Vp¯pu¬{å­YÎæÄØœ£¨İíL^“E~™\"ËE[šÒ!ÌÚÇşÕõ=ëM˜~“Ahku:C]?Os7mŞÿ\0ı¢.6,nâdÖ\0qş£gş”z-ÇÒaÎ]âíÇúßš™#\"hW\0È,éÍ¸9…Æ–¾][C\\â¹–nßéïÿ\0‚T3:k[‚iî5+{áÛtkGóŞßÒ]\\ûX|°nh Ÿä3r•W¹Ï¶µÄ%Í½ıo{½ˆ o²Ïr0oÁµí.\rvÀKˆ¯Ğ÷~ú‰#éI81ªîs°èê^›md´9Õ½ÒC»9¯ÂôK™[?3ô‹©tCU\"ÖUm0Â] öz·fÏæÙZ±‹5\n–ŸôXgÍ‡	¶£]ôcÎTlÂCu\'Nú£ŒK\Z=®kÏ28ÔnúJ¹mÎqÜ%£]Àx+<@†\Z¢ÁÖ1ÅĞğ{|{5B­¯üÂ9İıß¢‰x¨;o<5şJ}h‡àIÚQËçcKÇÊ{¦¼{eºO*uK˜×7HĞcÉaÚtÿ\0• \\Yµ„à	\'?ŞNãõ%¼>Ÿ«7—šç¼Ë|eVŞ}I®$n„G¶ï~JÎ?I½öşzl-—f>‹ş‡ç(ælÂø\n\Zœ¶ãQæ ö»ÔşTvÕidô§6¦ä3Úâ´#÷½û~’ªÊ.^ÂĞ ?´ÅCÇDZ£¬Ÿhi“À®«Iİ+ìÍuÛ©³a~÷‰aÜĞ?GûŸËUiú­nEN5=íŞk;_¯æ·{}/Òşbë:wÙn¥ôÁ³i\ru.hcı1ş›Şúş›ğ5i‘ è¿AÔ¼G¤üw—¹\ZvÌ‘îÛå¶Zı[¹ÍyÑ¼\rJêlèÕgÕsœ9LvÖ¶ÀÇ½Õ³Ó-¯cÚış³::sñì`ªç’Èct?N¿ğŸO÷ëôÒÅ=…è©ÇW3ìÖ‚¬’\\ÖÀA?¼…“s.\r-ƒ:Aä·ó}»—V1\Z\0ƒ£È–FèÜ÷5¿›îC5±íuwa‡¸ûGò}=¿½ÿ\0¤9	Z Ê°¦„Éõj3XIİ\'^Äp¶ÓÌsC)d5Ğ\\Hú_Ë±güb½ö\n‹Xêšãº}Û!ŞŸò}7\' \r¯Éo¶OW—f=Îix£’aú ÿ\0[ó7+ÍéTÛQÈas¶·tÎïä5ÿ\0Éş¢èj®ŠXñH´÷Iİ¹ßÈ”-¶f?ÓÒ¶Xá.\'l4\rîwıóØ£–N tĞ/Œ(¼éék€`hx\09³.vz¾Ÿ¶Ïç?F´C·qûËZ,Ğ}-}Õ{=ÿ\0ÈõşoãtÁ[ÛvàĞí!»O´ûY¶Ïğ›şš%˜ÙqŒqôŸX;¶~k¯Ÿçìÿ\0	³ù•¾A¨Ÿ5şØÜ‡Îé=3Zß`Æ´µûwÒ\\ê75û6Ùìı#Ö6wH»ÆÎµ%í;šuöm{›şzí+M±Å¶5µzLes‰ıG9û^Ë{ğı|JÆnÚ	kìnas_f××üŞı¿A3vuî(hñ‚¶4Ä8	Ú|9jka­.iFœ\r~ks­ô¿IÌÓiôË¬.Ùí\rÓk\\ÖÓôÿ\01`äP}9ƒíúSÛMÍVÁÆd\rHXã»{w	Ü8İıV¤÷¸HF‘®ŸÚ\nãÚóêVŞ5óíjk\Zæ‘ê¤Á„Á)j+Ç¢âŠ DL\'T\\`C ?évî´úGJE—FÇh×9»†ç4úÜ×5ÿ\0£‡ôÆâæÕcËEDCw6ZÖ×ê?ù½öÿ\05üâ—_ªHèáİ‰q¸Ùù•¸¸ÁÉsOÑVÒÉ\ri\0I-`¦çm[™xTÕi°7ôÛ¶w8±¿™cªiı%^ßğŠWôç2ƒkËAÙQÜÒèÚûÖïÿ\0„ÿ\0ƒÿ\0ˆ˜Fó(1&‡î¸4Ucíc^HáÌò¹­ıÍÈï¤×o¤æ¹„¸09À·_êû´ëÄÇõ«Èk7Ğ÷±¶·Û¿Ò~ÆYş™i;¦½ÒEl¶Ifæ¹àìı\'Ò·ó}û?Gé¢3P\nöì—):Öd~–ê·6\Z×Ã{mİú=ŸÛU2:}İsˆ5m¿sœX=¾ßÍİıE±mE¿ÍÚÇ>Öl\"±µ²ãô÷o~FÊŸşı\ZG\ni£=ä&KZ÷ìú>ö¾¯¡½ê>+Ü®®Ï5mO¡îik¯¼m&}Íöı/ ¢»³á#Ä~ê·Õé »a·vSZÙä {·Ù¹ÍuŸÔYŒÒ	\nhÏm4c”wÕ“÷U%ÃQ¨\Zéıdøö5â,ytêà£}ŒİIî9i>>årÀ\"}ÆHè?•ûÈqş²Ù\\>›¦Á,$–@à%]‚\0_ùÎB{µ§¸ì?ª“^5“\ZÇ\Z/«8tfl»S>=åU,²Çî1Áä[j/¨×ÙtÑ ıNØÖDÂdª[»/Ç`¼³p#’@\"fGõÑ›¬Ä2u¶IĞx¢®\rpÓÌ4şR1; †.òÔF£²ÚÙs4æ¦litÛkšéiİåªlˆÓ_± ßjí³xÉ$¹*nhhÜAor?ïª.Üb\Zà>:§—Ø\'Ì¤ãæ*­ÁoWætKs\0<îá¢&šÆƒî2O‚²m®¦ÃNï8DDI\n$\r\0j×’ÒÀt\'º(tp€5‘Ê#Z/‹ÚZ#^ ¤CÇ´·pÜ#×ñZeÑ‹\Zòæ½°ÖF§ÿ\01D,nßc¹<Ÿ$ÅÅÏkKNÂ#OİÚ¦æ–ê4o`9N\0j´’Õ¶\\ÂÚZ;wRÆ{Ïh\r{xƒ©yı$+òLëÁ…SK½€jï£ñ\n¹NÆ»²ˆÜuo ÒÃÜ˜ƒşjc{¸\ZÇ\'Ç¢æ84	kâ5ç;ú‰­ÃÊ¯Úú¶i#ÏûJ^3^+8Eø,ç€àycÍI¤m$é?›Ì¡šÁ.‚#†^ÚÁ#”LëupŞÉCş\03¦‘[@—o:¸hí+{N€jL;]4FŞ†è>_ÙJõD¬l·Ù¶Èq;O`uSÇ–Í`9¼¢.8°8O&QFİÏ ¶=½“ÀJA\'«)\"$‘ÌB}u‡ghÂ²]¿P`ğDvşJŒ.æHÒ”‘ˆì OuÃÃ¶O—ıõD½Ä\0	}Àègúª‡Ôv£V·ıÊUmq0øh‡Ğlªj}®léÆ€Ü%¥÷;sÛ¼·S¬ù½‘nt·ii’$ş\n—Öh;œ\0u÷(²Gî¯€ÓÅƒ¬­¤ë›³‘¼ƒcd‰ûû+5µ¯k›éµ¯$ûíçúÊWĞj :=òNÓûÕPäºş¯ƒ$*ü|QÓO©-JÌj@húÚr‚&»[Â“]sHs‹ZÁäHİô[ü”Ç°³ÚH-ŸhñúIDŸ´¤…ëİöjÓ¨#ÅÚÖ¾¹3Ä|Ò4Ù^ßU…\"KF§_ôŠFÆ»Ød·ù<…f:\rô.&oü©“š\ZÒwr9\nŸ\0‚uŒÛ$cÅ3Æã!»ñO>Š9Ûˆìt%XÜ@Û¸xÈåÆà4ísô\0q)X[ï’ï.¯Á$]?ÿ×èŸÓñoÚç>×ÀÖíòİ}ÎÚée–ÿ\0ŠCêÛùî&á\0–ÇÓ³è1şÕv\Z¥ŒlÀ€©ÓÜû7·Øƒ›”m{`ØÓ.l‘¶v·üïôjadÖãf#A5ÖViqk‹[[4\"I“Ù»79U¿¦ãäÑUy¤–ÖíÜCøV:ïÒÿ\0Æ(ãeÒ=2\ZÉ<¶¸Ùı…jÎ¡YŞĞZmkA-1º}ßÍ§‘(\"‹w?¡:¬ ügÔÿ\0m¶ZÉÚáù´²­±¿û­8µ×`#éèu-Û¯éùÿ\0IûìDôïm×Mo.\ZâÙùû=Ùùêít2=µ–û\0ì?wİïşÚ2™\0n‚£v\nÊk$³kÆ¤7Wº=~ÕT7íë´XÑ;Æ×n÷@ØïnÇ±qmæ‹Ì—éKC€÷»ÜİßœèÒâë@q{ÛîvïÍo¿èûÓlıäÓ\\S‹C÷¹ñ±Ï.Ü!£Ûµ¾ÖlÜ [„]öƒkµÚ\\C¶ïú{X=ªoØâË\\M•4GóEeÚMOuNq.sŒs\ZàïûrÏE:\"û ”ÙvY]ÁÎÉ±À\0öTÈm¯éWOæ±­qŞúeÆ^I—îîç·ôo~ıê½[M@ÚÑd‚ÚçMÚÿ\0+ß¹»Ö…n%’Ç\r¬€kãt£íÿ\0È\'HP@+USqŸkìq¬  ~ïÑ}¯oòÑß•¼“[œúÜ[g&?µÿ\0¨Õ|Œª…`äTG©;YôµGÙîÛô}ˆtRÓifÑc}ÅÍ;˜û©ÃÚç13†õ>I¾Şên±õĞ@Ô‰`IíúI)Îí\ZÂ=ÛA#—§ïn÷-F–}¦òCjo·sá\rfÖşê?Mpµ9²ĞkÛÛ†çÆ?b“MÕk¡]À‚ÊÚ[a0×pHéû3ı\ZVu	è—»xï{ş†ô#Uâ¦±­a·Sû¬s¿œõ?Ò{Õ<Š‡§é?éØààÌmüÿ\0R[³ôhÄôI$ oOqeÖÒ\0e`š˜ÂÒ?IwòP¨ÁQ%®h ú•óÄşkOç­J,²Ç\nka5ÿ\0ƒ\rl\rŸOè}<œ6½»«-€5‚	t¾ö\'ñ‘¡ê¶ƒ†ŞˆÃCj&\\H‘¼vî~ïæÚÿ\0ôk5ı)¤Úà[	.tDnú3·Şº7dAæZßŞ$:î9ŸMEí¢ÇEõZH>şIí7ùiÀ”<ÖKİ¤:=“É\'èÿ\0œ“(y·Ó\rw¨=¢…ß¹¸}ë­®§ƒö‹œÒÙxee¤iùÏ¦ï§ûïW0hÈ~=o¹‚¬ªŞHqkàúTµÍö9¹\"$\0æôœ;«ÜëYÄÁÜÒ5¿Ïjßck%ïe{DÑŸ¢ûû_·èX•LÉ«u›[QÚ}¦>÷ZÇ½Ÿ˜ßği3!{\\ââãú7:\0‘·Ùÿ\0÷}4ß×,ìzî£Ÿ´·è4Û·İ·è?óÕ»êÀÌ¡´Zçn{ÚA²I%ÃßèıŸoÒüô;Zû,šÀ\r‰ÀÍöíöû=ª™¶êïÏ£iL\rÎ½²PáâÔ\ZÙWZnÙÎéş¾#p¯ªªEƒÓnKItWYõ˜ÿ\0ÒûêÛüİUØ§…„ê¨ôéª§ÖÙaµ£k÷sŞ÷ZïYşÇ+:ÿ\0R—·seÅ¤XâëH»˜Û~‡«üåuoF;ZÓyöû!ä5ÁÓºªëkş–õ¢Ádñ\rjé¬¶­cíàZ	Üè»è=şÆ\"ı™—0Xç;l‚ßD}Ís™ÿ\0¥?F¡U\0Ú\"?][e[Xë›Ó`¸Øİ¥§ÚmŞ\"Aİô¶9Õ[íÙéûµ«Dw¸W”=0„îÔƒÆÙ¬åL¶×dnŞZÁ.†íç;Ek#¨¸úšTÂòÑ€óoò¿Ñ*ùpúc€¿Úç8ßì)bGB°¦ºÖ=íôáÆ\0—:]ıŸøÏ šì†èÿ\00Ç·fåMù\rÙ¹°vøvƒîÿ\0=§ş¶\ZXë \ZÛ$™ıÉNáüiëÈuÙ!®°ìµŞàdKe­ÿ\0]‹KÌ+šâæ=Å¦\Zàßğ^£ÿ\0sÔÿ\0FˆqK¨e´çJÆı?w¨}?}ªã-õ×Ìòé“»‡×»è}/b¯<–4Ù’1îÂâÇqhØá»Y77Ô÷ Ûh© ×î\0í‚âA?M›lÿ\0F¤û«ºòÓTXÀ@s‡#OÍş«”é1î{w–0\0æ¸îôä±İş‡èÿ\0q4\r5D’„M­»%Á¥#Ó —{[êzv~gî}/BßÑ°´\Zå€´Hƒü[ôQkôâÒÖí›È\r‹vı£÷¿P~s[tÖ7D0¸DûZœ; Ò+p”\Z/­ör¹Äm“ôÚßo«³üÿ\0œTÑÜ+Ùƒ®}¥Íp{€±µ»klİúOè½ëPçÖZgdg÷·5û}ê­y{rls	&8|\0F»¿óQZÖŠ4ò®è9X™†E@<¹îÔ5£éWù¶oÛô-­hbôŸ“ú&o½Õ\\ßLq½Ô\\íÿ\0AüúÖûN>]MfÖ×p;ÃõgÏô{Û³÷Ô]œé«	Ì®»šçˆ\'MYmßŞFètEY·;êY_Ù½:ëm-sSÃŸ±Ú½îÙoÓÇöÿ\0àJUÔÌœ‡Ó‘]>“!#ŞÏÑÿ\0Q¾½Vş‹ÓZCêrÜøki-’éptÿ\0+óıu™Mvú¬¸0µ­k´3ñÛé½JÁºêªîƒ*Š›e´nk}®%Çıè;¿ùµ\Z1]~–à)±¾b>Ğ_şé³şşÛF½ßgÅ¶ÖÖFñ/ÙôFï§¾ŸğRÆ¼İHs‹MµËdAı¨Çû][˜Ûv=xU¥¢Fû[Cë²Ï}–î€Ø1_³ôßç6{á[çÍ~›@l8î\rt51,¬m¦ÆÖéÔ\ZX	ÿ\0Á{ps˜O¬ÇhÇ‰;w¿k}¿ç¦Q)Ù–û=7Y`i“´Hÿ\0\'k=ë+ ·ãê†U»qi}„5À{ö{÷·ô~Íõ«Ö5Ş«ë\rÒ\r€û†Øıÿ\0áv d;,4ÖÌF@˜Ê5²Ç{¶×cİKNÿ\0vï}©ÀR	p37äÛ]õ~‘ÎCª^€¹¬­ÿ\0MíÜË}èâ\\+²ÛÏJ€âÆ±Ûı¬ôŸ½tîÀ²ß@Óq8­¾L`i,Õö]í¯Ùôÿ\0šşmi·7Ö4ì,¬ûd{w5ÛÓRx£€o\rGK°}*ıÏ\rÎôšÇ[şèúHYcV-sCXG½–9Î<zm÷Xê¿rÏô‹¼¹˜¸Ì´Ãœ4kÉ-¿Ínc}Œôÿ\02µ•Evâ>šÚÆ—ßRíÎuRcÔı&æzm÷ûşùÄÑ\"M®1ót´†nlˆÔ¸9öÿ\0œ†ö1äY¶Nºp¶İ„Í¯ÚÏQàx>æUéUûŸñjéwäl±’+{Ã\Zí\0:ûÿ\09¿œ­	ƒ\rzt-s%æäîİ`Ûì#ºrÖƒ¸K‰[Mè7RñyiôÛd;s@%œ½¿¤ı«ÿ\0Ô0\r/¢ÖAis–¸õ»gòëÿ\0\nÏÑ(„† ²z9Ï\rt¹¥Àßîh‹K-»ØÖ—ôZ#Y(mh¯s®iq1uçwç~úÖÇé™{2ê?«DÈ!Îi®¦ÖÎõ~›ÁÜ÷ş^¥¤tCGJÉuBëZÚ˜ÒAÃ´üë4wÓÿ\0ôln–Èı!\0>K^tˆÛÿ\0’Ø¶òEoe­×MÍ ¬İél÷¾­®ÿ\0Ïø5kÍ«¡ïvˆÿ\0ƒgş|ıh™¯ªãæ3¤P×CÚö¢G6Gï†gøOø¯ôŠ›°lu¦·3Ós[º$\0dÿ\08Öÿ\0¡].=;±Ü]¹ï×lmúm},oó,ÿ\0Šı\"ì¥µË€\0µÀ@€}Õ±®÷ßoÑÿ\0H€‘’-åîÇ}Oun\Zƒ\rpÑ ZĞ>+_¨ÔàXÀá1¹Âa²eŞêÜë?7ù¡Yy5ŞjÖ`Zoí¾§Óú*A>ìf=˜±ï»KC{ƒÊ^±:¹ä4é	Uû.0À\'C©YÅé\'\"Ğ*h.“£Ü\Z#ó×{Ÿr«ÿ\0¥éG¦ÿ\0‚\"ı•Ã\\AçNRcüWØòîa–™Øèü×h´¨é\rµÎ`n@«Ôc`8}ì{~‡ı¶´±:%Ôû­\r¨´šë}nÒêö»ô›ı–Õ·ùÊ™ÿ\0\\QfÉD\0Wã†–^iİ:ıõ‡\rÄÀ-ĞG¶¿ô»¿®ãt’rZêÚæ½À¾¼s;Úæ’İÛ›ìÙê·ó×HzvL­Ïk¬È~»›aö·è{6ÿ\0ƒ±ˆ÷cÒZo\rØFÒ\\CC½ŸIß¡¹­Pq2p‡¼sìÈfè;vûƒÚ^=M¾•[«gú/Ñ‚V¬m/,¢¬{-­À“´4núM»ù¦~b±MS‹5XëË7Ah/îÏÒCÜ÷ÙS?Â1è7Ò÷dØ*6¼°vWSšÿ\0{½í÷§‰kº+ÁÍÉéí¥5¶-$ì0cÚİ›~†ïvõ“‘ŠÊlQ¾İ¢Cú^æ»è®–¶ÛsË¬kl¢bKqsˆfÿ\0Wó,÷lYİy¬p¯ªÃƒŸ5{´l`±Û~Äş: hè·„n,8-s_ 7ÃÜ4ˆú[ÑÅaäû€\0x“ÿ\0IÈ/¬4–0ĞdÏ iîç5I­oæ1Ï<“Ïıøú¬ú$tV\Z7@’HRfC> †‘Ä!úÖ}™@ğQ¹ÚØZ8 jŸÅÛüU¼=şÔÛà¼\0Oq0œİ¹°×|\"$e@VÀ8%£—#_ÎÚµ°z6-½=¹¯éc>‹™KêWênvú–N\Z¾©½º9÷±Ô±¥ÃW4[û(!Ó¸{y5î3¯î®‹ìX§§½Íß{}Â àc\\Óôw4º¯Ñ?ôñk\røwã½Åå¾«œ¦`¹ÍwÒs+ú{g-ı4]ÁMwú	İ ë¬ÿ\0ªRqi¤G¸Tğß6µÒïvïê+%¸ÛeòğuÜÜæşêƒ™İk€æÀúD9¡2dˆéÓUÑtDáhµ†âY.ö<baÍl£äĞ+cë.uoe’!ÁáÎfØöúoÛú%a†«,cîª[a,t;ÚĞ=¿Íÿ\0Â1ßA]f5Yöm5ëè+x`slô˜ç~“c?}G9t®/ë/Œ~\r,~ˆçÖë=„07Ógwg¸mØÖ×şù¥V›)¼Z]W¸ŠŸïônıßoş·ö{1™ÆíöêÑ ¸;èzÛ}¿ES9!ôÙ->½EÕŠŞ ~’ê\\íÏÿ\0¬³ôŞš\0ê5I\ZlÕ¹˜mŞçØçíåà€ÓìsÚÛş‘¿£ÿ\0‚R·\rµ³v=ìxÓ°Nƒô¤Ûê}4JYO¥Y\0cñû·ú³˜şB{ï©Í±şAĞCö¶@üßkïÑ[o¾¿øµ(‘ªñ„\rÍSŸsK,‡’ÇÍĞÇõŸ_±É2Ç\0È\ZËƒ–4š¾ˆ<+Ú“Ş\Z7Üê>JxÏK\'mØŒzW’Ö[ë¿Óh†Ÿ¤çáî?EX®Íõ†‡\0€tàUT\'|µ­\0\r!º’9=mµ±´–|DÒLŒÏüÜ[¦QÚŸÿĞé22ÚÇµákw=á› x{¿{jÄ³;sœæ±–í˜kãp;w=ë±oÌvO§¹ÚÉİíiäşs½şÿ\0ôjCºñkÃ•³}®tI/-k*©­üö5ŠØˆ0M¶™Ô«u&¢ç8=Ãh‚İ›GĞw©±ŸOz>kÚ=:ıÚØÜÓ?›?Iæµ™>xiÓiñëÚïå¦ª³e•–5ö5Ä˜¬AÎ»Ù¹>€Šİ^cÜYU\0‰!ÃA>æìÚí¯ßÿ\0üâpÊñÚûÃ,xyÔ0êÖ·wé=ßàÿ\0©á6†=K­½¡Ú\\\0v‡moúoÌŞ¯\Z…¯%î{L<í®cyXĞŸOø¬¢ší¹î°ÛŒú$Ëv¼Ï¾ßj(Ç\'i6¸RA.hÚ\'ÛúIß³wóhoÆy±¯vÚå¤8û\0\Z5Ík?¯ïR¨8TÖm×oiİ:·ùGè}b7ğRïÅ«Ğ·{å®ÕŒo³û\rõ?–İë\'+\ZƒSCjÚæ‡âØk7ı¯{]úOèÖ›İVC^Ûß½Õ{ÍD¾7>¦ógæ æ‹›Ey56š-6Í`¸¥¶=?Mÿ\0è­NŒˆ«=PE¸øÿ\0hİ5´Ø\\ß£Şïw³é+u[ö`ö8\Z n€Iö~ïıqVûEÕ\\ZëlwÓ,÷Iw»ô?È@ËºÏKfàIq:ñ¯ÑÚÅg«Î–]ï{E$o\0èè%¼\r»;”(È´1ÕµÍu›€,kwI¾¦á·{\Z›ÑIµ™ÚİÍ<î¤c½»D´rp°/§ô/ÜÀ\Zog¸À>æ½Œ÷ıÂ@ F‰\0PôÜ+6¾ÊoºÏnı°+Ú6»sµŞ«Tñr±Û`õ<†‡ºcé{›³cü„l¼›«dØi}/ >\Z}Xú{ªoÒßW½èÿ\0h¬º«\'i:WwæöìuŒşz«?ğ4Ãd›Ö×v¤×çÚÚœ÷µ®k	\rÄIügü\ZÊuâÖØH÷8î¿ú6+KÖZç½Íed4éîwóı½ŸõÚÖW®úIk|‘ çó¡KŠ#†ÇU“&İ,[ìÜ›£‹@†2=ıı5aÙzúMÚmÛ±­$|ş\reÖç<FòÛ6ïtêè,¯ß\n0ó[m¹õ—†Ã†ƒƒû­ÜÔã\0J´If‹Z©ÉÛïõö5Zly0á64’Zél8ûÿ\0·íı\"×ÃÆsÃ­µí‡háôw´Üû}/øEpàa5¸ºZ\Zö»İ,\'sw>?Ö´ÃK¸mÍéøÏ¾ıö5ó íÜáï¯Ôo¹ŸõÏçV›ñÚ\\ÒH6°¹À½¬fß¢ıŞŸ½FŒ¦Ù¯®Û%âØ\Z\rYkà?1Nüªhe€î\"=Ó:û¶ÿ\0Â(äde²à\0\n8­uÖ3sŸüáÒ[ü—m{•1ÓºnÁXi»Ó°‡HØds+ÿ\0ƒ÷~bWåŞêë‘¸,pŞ\\?Á¶¼Øîÿ\0·«ËµÛªmpÖØø1ièëıô*@]«B”ôº‹\\mvæ°L;Ù´~ë¾‹_bËÂÁ¦ÌË*m&†± ú¨çÚÍ—Y¿}ñ­:oaÄ\rõê -°ÒvìmNşoİüÚ(¾¦—Zj–´Cîp~›[şgüoÙT-ƒºN5–ú–:eÎ±Í.×Ûê~÷¡ş\rU}vñê	ŠÆ­l¢Í¿I?(µ†Í­4µ Ø\\ÿ\0d}?eMç=UÆÏïí†\r5	Ñ¿OİëlF\"uÄuQ#`ÊšY},s\ZG¹ãd	OÒŸ¢Ú¶¾´Q}CFÕ°Xí„löSıUPeÅ–Ôú\\Û7Úñ&+0êküïg¿ùÅo. ç1Ì ¹ÃZèİ#İ·oæ\"5>¯ğPtÙùW€â×ş††¿»~–í¿œÕXÒny¾û™m;Iµ®|\Zçpc¬ÙôÕöôöRæm´WTjâĞÿ\0sÇÓÜ÷z~Ïô¶1S6[u©´œš	Îc€–¸íúTzŒwıq8ÓOë*»µqºfM·Ç3c§èK«s}­Û[¾ŸşŒ[´âÓyÄk_T9»[.ÔïõıOìşz«Ò*ÆÅÈ·ö‚×mÁÂ¹nïÎıı·±i‘‘]­{\\‡4®ôloçúv~cÓg3¨ğÑQuò\\Ã[ŞDµ¿IÃé4Ÿä;wĞúiépşÑ$ˆ?ôGód»jùî-!Ä¹Ûcl/×ĞNç³Ñ&·{š@iA×÷¿ªïÒ(ÌtºÚùõ—=§Ô ûÃo»oîìö\'­Á•»m$ìö÷7ó½ÍD§‡\"·û}$ØwiØıÑô¾“>š—P>“½Vº\ZtA şvÖ{¿%¯\n+ª&äkZç\0\0$–Ì{¿9ßERÈp°7a\r°8Å\Zßúªİµ×º×ØÖºxÚïwÒwùêºÇínğÒ7›ìö×˜\0\Z£V6]¨°;ppû¿’Ö¤l!ĞĞĞ×·phğ—n¥·÷l¬]Ps_ú@%¤ÃIqú^ï¡ô›Q n}àkXÒĞO¸\0ı¾ÑúDàV¶k\'Ù½ò×’´ë¯·nßM_«1Ô=¬sC£kt}]¹óûß˜³1mu?Îl0Ù‡{v—ÕlbMkÜ´‡{¥Â=1ıoÒ@€wÙ »L\r²áê088ƒ¼NÙïÌıÅ_)¬°ÜíÂüÖÑ¬¶îwü”±­aÜ¶´\ZÁ q‡{v»ùyÕŒz¬³ZìÄ\Z4ö¿Ÿø/Ïşm0hi;†UğÒñïàùŞ\Z~‘Ûo·şÜDÈ··XÆƒ;IlD\0ßnßßA~cÙl¸k±Í!Á’âÏ¤Ïz‡SÈ$~’½¤m>ÂÚÆ{Yîö$A%A‹˜,uY–Ó¼nŞİÄm›}îm[G½üæ~–ªàZÙh0>/U½fïkŸh²Çº^	:@>ÿ\0ó=– dÖeö5ûƒÍ¯Ü-—9®ö~ê<#Kú*û6˜m±Ö9îhs\\=³ÃZ=®vİmV\"TğĞZı.k¦l>÷h5ŠÚšØ¬´¹s\rÒAıï{+÷©;\"£k`î`×îIé7oòQ­éºà_Yp‚jiC±â6ÿ\0Ûjn²—z’×î$Ï¸ÎßÌc6ª{ÜÉõì\Z\0kZIƒô]ù¬Qg§¾ÂíÖW\Z8ÓğöƒM0­Q-¡»¥Q-÷hãÆİ÷7÷ıÕ½=e¬-õ½­1ê€tÿ\0;ÿ\0r¦Ç¶°=¡ò\r|€nnßİS\r­µMÂºCE`=Û§nßæÙÿ\0Wb4Fˆ¶M¿Sd²³N§wó›ú\\Ö¿é)áàÓC·:Ñkİ­kv–ÿ\0Ws“š	ÚÑé£O2>—ªßôV\'¤ßèÖàë\"§?PàÓÿ\0Mé´4®«ä½í¦ÚZøH€æ{ÿ\0gï½Vû(ÈÇs*vÛ^mä½¥‡~Ö·muíc¾†ÿ\0Ñ«y˜ĞÆ’	¶Ç7q\r~ßg±ŒÚ–û¹³ê˜0d†ÇĞú-ÿ\0­¤š(îåæt¾M;_[¬µ­kˆis‡îûÏ±Ş£”:>N+#\'mµY«^Öé^ßnëŸûïş”Wk‰µõïlîn»‡´Î×ÿ\0+üŠå¶HÁÚí4Ñ¬?ç#¶ˆsîª»m{²™¶a ht{7úíÛôÂUé ;ìíı\0Õ³%§ó }QŸá73ôŸ¤V,8ZXÑ\'’NæŞsíŞ«ŠÚlqcöµ¬ñMßÈŞˆİIªÍyÜÆÔÖÕ´FÑ¿I»œÊÿ\0Kb‹İY«sç½îölAÅŸMßCØ˜R)!ØÁÔµí—‡jŸ}Ÿ×÷6•A­ipi,‰pi‚L»nıßğ\nóYø›ö=¤V[»iÜ6ˆ>ÏN?}©ÇIa¹åğààw“ìqÿ\0ÿ\0şÂâÚª¨†;m„ÄF›,Ûû¨Í46	ƒ¥Ğ»oµÇóŸïÿ\0–§Nªk7¢0ZI`\rkH\Zm\0ÿ\0ƒ·–şú:Õ\núMìê÷T}·7Ytÿ\08ÏSó+ÿ\0Ejè±]ê×î;Ép$ı- ~k¶íØõm›Å2Za“ï\Zî?á,`s¾‚,‡ıpDşn{÷µÌ·Óu£Ã/|zeñ5ÒíŒ³k½—±[«”T\0k<Oº}¿¢Ü¦ë,²¯v›|FÙÓÜYôš„bæ;{63fæ‘İ£ô~ÑôTvNëÚôĞ÷¸Zò\rŒ‘é´h½÷;é«¥Í¼Lšãh;@ öÛü—(cYUu5ûİ¹Ìúé$nvÆ¹Iö<W«€k	ŞH?àšÔµR\"ñ]Ì\0ÒHömúš©YeÏ{ÉcIt7qpü÷ûuşŸ·fÅg)ì-e¬s‹ëÔ¶7s?è¹ë™^¦æîk‰pc=Û[åüî÷§à‚ÔÎ©…„T×1Òç7\"Æ“À\rşmçãşe^¯øDN»°½™/.ß[«¨°‡“½ÓşÖ{ÒzjÆìÌ‹6cµ»\ZK«cÜéİ¹Ö¸ne{=Œ§ÿ\0=£×]Î®§o¦èŠâw?Úvÿ\0ç´ããÑÁÊê>Ê°šÇÙê\\L>··gº=G~²ÆØ÷ÖöYéûı\n}E‚ú,¦A“µ§Bç9vô¼›n`Y^- mk†÷¼·sjs­İú*ş–zŠÎgJm•ºÌfFÒÂÍLîuğ_ÈB98tñT¡oû9–ô×äRïhpã“±ûäûkoè–ïOéµ_VE”6§hÛ*»Øí»Lú«Üúı]Şÿ\0Ñÿ\0Úec¦;ì£ÀİÏ±Ö2ÚæZFÑ¾Ö¿oçıø?æÖ–N{qİVfÃcmßê°GªOĞµŞ§é*ÿ\0Eú42d$kø*1\05Gèù5+½q{-sIßüÛC+ÚÇşgø$°úK°ï®7bí!/ÜA#ùÍ•×ìoıwş-m9•íÛµµÈÁ£IÕúhv8U»Òh²Ho—Ğö¨ıÃT-¶“ñƒm|½Æ‡’ßH¶e¬}{¿y‹:ş‹]ïÁº¦ÿ\0„Ñ–VZF4nİ–-W¹å­sŒt\ZÇµŞéRµû6¸D¹ÒAğŸŞÿ\0¾!ÄFÊ ó™Xô·*ÏN\\ÛÜ—şÛ˜ÿ\0ıoó‹5´d¶qw2¦—{‹×Óë2½Õÿ\0èÕÔfâÓeh`%Şé‹Ï¿Õÿ\0_Ñ*Yx-9/¹–zPÓ6y†:Êÿ\07Ôöÿ\0èÄâMR©ÄÇ¯íZÃœÃ¸´9“º=¶[ë·óÑú‹Lôƒéš*¹îÍ$ÖF°°¶íÏôlgş\n‰“‡N;1í¡ä1ïİc[\'vïcúKônşvßÒ$ş³×äµ¸Û@\"Z@s67Ôu®ıÿ\0ôV\"I1öîŠ\0ù®1ú¦GNc1«ö´4°¼ívæS½Şêí{PÒò·\\Úl!ÃsßX“ôK¿œı%öı5jŒl«1@p¦Ê\\Ñ\Z’9Û>‡õìb6gÚq˜×İcœÆ´¹ïlÍ÷úæV€;ÇMÕ]\\¡E•<0ËŞ-ìpş÷¿©üÚ6?Lpi}/õK¡ÂÊÆ„O·Ùué=Šû0M„]xµÅÍ/t†í¡êÏ¤ßêX›\'=€‘.pö¹¯%Ã`ÜçÚ÷¹˜ìc¾‡óJHJ´\Z_Ì¶Bõ=6póËkºêå®ÚFÒ5Ûü–ªÇ\nëÚØîswVÚÈ\"¹Şçm÷ÖµDmö–ê,\0ÕM¡Õ¾Úì·—ÑüõşMlc±¬ôd“c,ÚFİ»ñİIs=\'îØ¤”ìpÕß,£nmÜLïÛ¨H­sš’à~º¤qœÍ`¸\0`6xs·ÿ\0UI´5•‹,0&°yÔno¶wí±>\'mwÙi?ÿÑ´Ç°XêÙK-÷Gº%¬¯k¿–Ån««u²÷4½ 3Y|=ßé=?Ñÿ\0afäš¨$WdÔæ‚ËA ×ƒ>×±7!Ù-ı	èâò÷\0\\ö²¿KÓõ_ùõv@×Ñ®­—áœì‚ÇÚ\ZZïJÂÆ8N›kw¿şİZx2Ì\ZË\rû¾‰€Ğ´œ…‰Û©ea®ë+.0ÒZÓ³o³ô›YşÓQ—¹Ãkâ°Kš+‹ÀöµÜyC#9it á\rŒ¬VYŠãP¤>ÖîİGvïz¾Ìz\Z\ZêFá­.÷ª9ù…îe%Íy‡~‘¤m¹›?7ôŠ¶7Q\"jêL—¹€zŒ\0–o.Ûº¿ğ¸÷Uş!	P¿WZı$qÓFÏ¯•öwÜ\rT¹¾àĞâğæÿ\07^öíöÿ\0m]¬¾İ/›A˜n×h>÷ı%“}tŒSnv:™.´¸Û~‹ı\r›«eé)şqn3 7Ya11MÌ«o±)xÙ(¤5lº®™[± Ó½­÷?Lë¿7Õüÿ\0{ìU­Èè—\Z«½å¶ƒ\"—’[»ùß´ØÍş—ü^õk©QöK¤®&ÇÃ‹GÒ}ÜïÏüÏø5ÎU„áxkqÆCÜAc­&¶4l>ÛhoÓõ=õûÿ\0DŒ\"â³v‚H4èçY„Ê=,ZÇ°êâ×Nö¾Ÿs¿;ùÏüö³\\2Z÷=ÍÚ·i{dK‡³s}®üå³WíúÚ–85‘ôZë\\ıŸğj—İËrªyõ÷´½»ÙkZwYO·ô•şÿ\0úE •P+ëkH½vs)%€†ÚÚ[%Å ×óùÛü\Z¬Ícƒƒ\\Ó°?ó[nçmØ„ïÙÙ~öÆ+^ZİI;_cjc½]îşm›ÿ\0ãê¹…•2Â%¥Œ`>àíşvßÌ·j}Ş€RÚ¤×PÖ–ÔË/-w¬}M®h!¿¥g§ômÜßøÊÕzì¾Ç’ëG¶úI¶–úİ»éú((²½5ÚH\0ÇÑùĞÏQîP¦Êë.É©â«§k	·M®vıŞä¸tñU·320ë%­kL\rÄ§Ãv!Uõa€â@ÛÌ\0çíüäs,¹Îq%Æ@À-N¶ÿ\0Y&Vçn\0;Ïş—µÊH\n\0-%+rlPİ#Bè€Aú,Üsö‚ât3¯}ŞÖ…]Ï™İ2é ~ò…o²Àv¾ rªÔí»XÙ¬m;ñ.q?šÑô—µjÕ—º¶¸;Õ‡kŒı·]­Ú°1)ÊyÛ\\8¹d1íy›³è5ÛÓR÷‡zd—4˜ötñıÇ¨eÈéÑŠ—æ:ÀHkÚCp\0Áè¾ÓûÊ¥ÖdWci¶ŠÃ“%®Ìú	©Ê®ššÜvÑ«ÅšDÇ¶§}7½G§Úr½vÙSèiÛ¬t€}‹ù÷!GUf´]wAÌ9ÖUk±k/}Í2*ÜZ=šmníÿ\0¿½o7¤1¯9MÎ#l˜d{}G[Ç3sÕ,f\nó7°°µşÇÙËÏò\ZúşİŞ’¿’È´½¯´¸íåÍhntÿ\0ƒõ6ÿ\08ô²HØ\0ğéjˆ\ZÙ»XÆ\nŸ´v­s‹wù*\0¾€òê·Öç\\_¡~k}ßŸ½gçdÜÆ‡V}J ´´?õÏø´ÏÌ¦Öµ—Yh‘¾Úêpk#ck±ßÎ{ÿ\0Ñ¦ˆŸ0SaÑ~U.cšÉk‡±­‚âßÒz-Íö1W½àÖ^ê«±¯Ç´ZFçí‡7g³÷uoÇÆ®¼€íï\0n®Ç‡cô{mşFÏb–&eù\"6U$TÖÀ\0Òmnß¡½;‚¶üQÅİ\r9†±ÂKZ÷†¸‰;ÚçÜW¬vö%®²C[µÍÚÖ€İÌuüõVáS²ö×.²¦µÖml³sÃšùk[³ôjc×ak°Øğæ¸‹(†—;é»›ı©Â QmœËnm{tî°X`-%¿¢gæşıiWË¿-×—à[X®Úëp­Âk÷l³yÜ×l»Ù¾ŸæìUÜÌ³Šÿ\0Ñ8[WÑÜlÄ9Û{vÙı´J±·\Z/sã:I\0šLşÛ²Û[ş†¿çª×OúJ´øwõ,zÙ{›ö¦–’l›\'këúÿ\0ê-Ÿs[Y±uÙ-–5sàß·ÒıÓõ«©‹kãí\r6p×:@—ı/R«6Tïb&¹M–gdâÍ´·h+Ùéÿ\0£õêL•îBGdö\Z¿K»ÔkHããûÚÿ\0‹Pu­}@ê 6 }Îsÿ\0=ö¡]}n¾_¸Öà\\×At´¶~‹èîö¡Õ—xªÈ-sbC\0~¨Ï¡ÿ\0m¥Z%#é­¶ŒšÛÈikœ>çVïOú¿õÄÙN¼Ğ= °6hK¿7óı¿ES9VdÈklfº¤ß¥ísïHeícZï¢×m6r4wşM¢-®üÇ6ĞÖ»}l\Z8. Í6ûı?  ëI-e\rÛ¼Zà¶»wöÜªöTæîÙî;K„—	öz»³ùiœçµçÚÇÀ\'i×OÍÚæí³ó‘¥1 ÖÒËÃ\\æ’àöË@üîÒ{ëU™u­©õ·‡CtĞínïóÑ†(Ş@˜AÃù.úlüô)õô{¶$’NíŒıÄíêÙk[emÓmUNÖ<Çó“ÿ\0±–@sµÎ\r-Ô\r³ü“ù¬U†M\'Úà\0Áhàşó’m¦ê¶ÙZ`5²\Z¹»ìüë©Şn[ÜZĞö\rxĞÇæ¹şÿ\0¤mÂà\ZAs nFæÏçïuŠ%¬qÓkt–øéí\nG!„³Ôñ%¤ItßkJx&Ûjã0Wv×††\"ª…s‹nhsÃ‰{u·iıŞŸĞÿ\0„A¨VöÒcx\ZÉæGĞşJ=•–\nı­qô¶\rûXïvÏ£é&ºÑQl1¢§&`IÙc[¿óµ›Ô]’FÌšÚ]H$\rÀd=Õ†ûlıÅŸ¼\0	1\0ñ>¦ÿ\0Ïı\'·Ş‡vFKË1è^ }°ßRÆëû¿¾‰SgÜæzk6¹ÅÚÛŞÚ½8ÿ\0ùêÅwn5µ¬¨6\\vş÷îîÛïTXÆºÀİÔ0€¶fO£ÿ\0µÿ\0á·ÒÖµ®\0	 \0#÷¹üÿ\0rUjºnºÊéö€HÒ÷nÃè[èC_¹\rÖS½Ípub`Àgï<mşÒgúAî>lhÉŸ§ÇĞşZ¾·;w¦Ñ[H,¤‘ô}¿Cş\r Š-1²¶¾€×nqİ·—4{]öş“ü\'Ğb&HmÀwúD\r±\'é6ãûª¯§`¼İµÌ.— NÖ½¿úÜğ\0&k‰‡#ªm´!ÁŒ#y&_sÎØÉş¯ï££ë±¬sd˜0d	kŸ·gĞb¬rÚúƒn%¯:lGĞ¯ß·Ş‚ÇÖ!íı:2>‡µŞïæĞR«ìß}–¹­ÜZæµÎq4ú?ÔşZ %ÁÕ\ZÚö“í1\"=Îÿ\0HßrÍ¶ûA©ÀúUFÖÄ;vßI¿š÷¹M™¯–ËM³¸NÖÿ\0Ô{¾ŸúD¸Um½•·2–íkdÖ7\rŸwï{?ãR²¢f‚æ¹ÒIÓFıÍüå\n²muNd€O¼8Lÿ\0(VÆûU;²+sƒ¤5„f\0úM{}D\0!Iòê€ÆËÚİZ!­6~÷¨ÕAö°¼ Aã’góv«.²Ğ×WĞma\"	ió›§çî÷ ?Ò­•Í^£6o×¶\\Ömİû›v\"4;)aa;\\ÇNÁºyóõ\Z­\\qË…€9îx„?Pçd ãâcßº–j	pß¸\0ÀéÙêÖ÷1ö3éıo7hf0q4ÕµĞ\Z\0?Kô³¿ô–7ü+âÔkI¦NÆ-Ú÷µÒí¤7MÕ™ÿ\0¶ı¿×OfüÆ=ÎÛ³ô Iÿ\0Voz‹:ƒÈö:_SÚf­t»vÿ\0¤õÒË}B,kCİ\0FĞ7í­ßŸü´Âd½’+£\ZÚê÷ŞéØâ]_ÒkÇæmvßgü\'èÑËÛjÎØ35nïßbß]ysìw·ØÀ	ØÂïÑöìõS—µ®›+údÓsílíH…-c·ô ™å C@ŸÏÜŸ³öp×n º*iCgèû¶¨·!Û›ênıVÈÚæ¿OÒ¾ÏM!Öş§Wëæ¸aö1¯gó›÷Æ HªÒã•ík]µñ´p€vµ›ÿ\0’œgíö¼l¤ÌÈú[”è­øík‹·×D\0İ~›˜æû\\Æ%fc˜Àùh¡ÒÖÀïù­µ+:Ò‘^Âç\"Ö¸9¤–xÓ~Öÿ\0:ÏÏ÷¬ï6Ñ[^\r¤XÙ€}?úªÿ\0ğE±w¦á^ıÛ °uqö·Ònï¥ô†hÏÇc÷9§cHh^ßé?I¾·ÿ\0èÄD€\Z È©s\\×V^Ú\ZZç´ÉÍÖíÛ6Û_õÕê˜YH`ÍqÜ·Hıß~ıÿ\0˜¡*®Í¢°+lÃe°Kß-÷oÿ\0=ÙAµ0È\0Ë[;Gæ1®oø6Å§k!¢6nÛn±tÓHÌöı œ?xvæ–Z	şÏùŠ¥S›¼i£ZdÎwùïö\"²«\ZêÙ©?H&‡·ş©\0Ób–Ã›SZ×zr%¤H?¼ˆË*/†9²u\r\Z—\r~Š«yÛY\0Ó°ûÆÆÜv‰ph‡F­ÕÌö7ØßÎM«	o°ÎIIÚªÜğ-asâ³Çç·û>Ôò*a¹ÒÖŞ	Cİ§è÷ 7!¡®{f	,kgè¥·Ôro	M¦¶ğÇ5€n{\0\0]ï÷Á±/RÇRÒæğ6òßkş›¾‚Ïı3ša¢â&d’ÿ\0göÕ—8µ»Ü5.âG´Oîşnÿ\0ä#Ã²­3êŞ]{]é±¾çL\rßèÛÿ\0¯oCõ}\\¦4\0Ú¬s´ÜIšÛé~nö?oúúˆ!®³fç 3&}¯úÉoóˆõ¹»\\XZúê\'M¹Íö·ş´‘\Z¢Üì¬lËîô7aÚ××py\r‡mlo¦7úµ2Ëì½øÅø¶åÖïÑ¹àp\\[nØüß¡ú?Q]s¶Ë}ÏiaqúcWo³ó_ÿ\0\\­E×ä¿¯¬ı™–×ï²=RY¹í¹ûıŒßìôÿ\0›ÿ\0…K§u6iÚ×9Æ±S¬|¼€Xøú§©›ßw§ûˆp²jm¥ïµÀ}ìÑÿ\0½;>ÌqÙ”ê÷=î/pgøG»nß¤ÔªÌ£Õ¸ƒ¶×íŞ%­oò?}ŸŸş’ÛSE¥ Ök)†¼A:‘ù›~‚0é•¿øÛZ+‰cØù%ÓôfÍÏöÿ\0¦Lë©°²§¸>Û\'c$Àı%–nüßOÔD¤m.¥¿I =¦:lvßåşâp¿.¨ÑX­ô«p!­k]=®ÛY™Ø$¶» 3Õ¶ˆísûÌŞë)gçû?ëŠó™U¶ZÆIã³LÀ>û}wÓÜ«Õ‹soY}Îa˜¢¿nÍêŞÜu½?¢5–ãVÜLZÏÚA0\\Zæ´4–ï¥Ù>¥ŸÍ¹dƒ‘‡’Ç=¶—ú•µ n3ôêİù»ÿ\0sÔ].-™n²–\Z‹Y^AÕ¿ê=–lÜËSìõG¸ÖØªÙ;·\rãùMöïMâ¢š·ÿÒ³£}m© \ZËƒ›´îvò?;{_ûŒÿ\0F¥†öŞ÷7aÖ\ZXù ;üµ’İ—6ÏûuVÚrk¬PïUáÄìˆt’,©›[±ïÛùöoZ¸uK®;I£ 6ÒÓYÜc[}owĞo½õìW\'*™¯gÁ£Ö²]wÙr7kÀ¬´®ïwÑözh˜yµe–ãR6Xÿ\0{Ú6!Û«õ}ÿ\0h³ü\"ÑÉÍ¥ô^@mƒGHsµú,Øæ=ª…¹Î—P×0úlsdN›ã»oªÏø¿Ñ¦DD•ß[L÷9€WiT÷9„Û-yÚöz.oĞöû6,ü¼†9¢üS³\"€^Ñ£›2?ôZ‰ê_£ê›eÏÙsšL£ïşŸSÓCÇ»ÊØÜ†\r¶fİ?>ÒçYí³ı\Zp¸Z],lŒA„Ëh°2Î·4AÑ¹¹\rÿ\0Œúÿ\0ë‰cêÊ,®\Zç´‘^ÉÜÏÒ~b¡Ôiô®s@&€ÙØX=ı\'òöûYeÅ–úwz•ïÇ.\'Sím¯úÿ\0¸é%Ô¿¨:¼s‘uA¢±ïa÷1ÀşnÇ9¾ê÷ïM…ÖúeE–dêÖ—L1?Gé=ÿ\0Ÿµd9†Ğ[mîµ‰Ë@iö»ôŸú-[~\r¶³K‹€?AÀn-#ùÖû?7şÚDã½$]³V!w«Œç¹ueÃc¤óê¿ù¯ITËéÌ~ô¶×]eÖ3Òçşõ¯÷6Ë¿óâ°¨¢(²×7kÀú ´Wè&.¯Ó\"¢Ê¬cKC^ÒKı—¿üôĞ—]òN•üÏ´åa4†6^w6·´ş_³ù¿ë¨9ög_¶·EƒÜâH-kGÓ÷şâ¯}«ı0òàæïõò~Ÿ»ÿ\0E£ba½õ5î\0cî–½Ò×olo¢Í®cıoç©tŞ…¬jõJ—Ú=OeÚ	.\'oï}{T]mLªÆ´—˜\"KŒÏµõíV³›N@ôö–3Zõ%£]Îô½=¬÷ƒU®¸V$4Ñ¹³3ÿ\0¢äêĞŸZäŒ@CÁ-Ÿl\rLŸú(fÛ_fç8ºeÀñ3ô^å\\k‹µ#óu‘$îú?›ìE\0Ã·4\0Ñ <iâˆÕfÛlÍ­!ÏÑÂI›ÿ\0EZcªÑ<2uşÏÒ@­í‚Ö’\"€ˆş®åg«ìº¶Sn÷nØxô½k~…LD’¡«s¦ä{¬a—C?FC÷ôksYWı5NüÏRÍ®~Œ!­\Z€ßÌİ¶ÇØö=jF;aÙ8©Íx`¶°æTÖ»wÑ²‡í¹Ÿğ›Õ<ÌìwYf8Â¥å… YS‹4#ùÏSs¶{ÿ\0ÒúŠ(ÌqJò4\Z ûI³s7Ş‚üÏì+³(ÓM¹Ö3i­%»	÷~Û^Ë?óâÇiuOp¹»Hö¸“}®Ø¬ıªÚÙSZğ\0îaÛ´fëzÄé ;9¿g,c¨c\\^=3SI2Í¾æ=¾¶ıªc¨€MÍ¬z DÍ%Û«Şßæ[fßøÅ\nYÓE,¬»íØĞíû‹wş¶îı_¿U¨\"œsı	ímû\rCyKGóÕU³Ùÿ\0rˆp“zıWêÇ*çXíğçî\\ó!¿šÿ\0Ñµ¿IÈ~;šÀ/®H%¡íwæíµ¾İŸEM”çzµ\\âĞçÙfú÷{·±¦¿óÔÙA\r-ÚÚ¬l‡˜;ù®÷í{ÿ\0âŞŸ¢İZ–Po·Îqô£v	¿KÓçÅïRs,Ç.sO¤ÂXXO$îi¯nßbV1´Øém®ƒ-ÚY\rOÛí±–Üæ½Ìo¦Ûqh÷oŸøG»ôÛùŸø\Z\'QİL®ÈÊvE¶C€|´\r§–şoé6]=7ä]snc=VW&Æµ¥ß›m»]ù­Qyİdá´È İË?Ò+—WXÈªÃ[š04€=•XÖı/Sü+6#@µIg—šûn°NwµÄ™;XÊÿ\0—îö£U²:œYµ>¹qf=Ãİ·ÿ\0&‹‘š}Z¨Ë­·c8Ò\0FİÃù¶5»Â ÖX´¶­í6³?ùg¦š½«²kÅ±Ó†ç—ØÙÈ2H³Q»óùŞï§ô=?ûujQÑ1õ9ÛJĞÃêlm~Ÿ»÷ÿ\0Ò=b\Zí¶ŸGy%§ucè²IşqÏÜßOzz)¸°9Ïû;Øùf²çûw6ºÅ{ıßéÁ @$êHuóí$€×4Õñôwéê=µ»ÛïıÅMì¥Õ†šÚÂâh3´˜İë?İµYÉ®Šıw2Û[¯ctï±»›í­Ïÿ\0¶Õk‹ıG^(u€èH!¤|æÿ\0à~ªh4“ª5ôÿ\086À‡-\r=İıe_e`Ú!ÔÚã¼8mk´Üö2Æ~~Ï ıŠ6ØÛ›Î\ZíÅÎÿ\0£¿ó·\'«&¦;mÔ—‹>›&?”úcè18“H²¶§—†¹•í{¥£ôuîÚÏÌwï©zcIêáµ¸>I3º¶»ó¿ÏLû¨|Ñ\\1÷²¸¬¶·\ZËKœŞÿ\0k›úoø4™sóE¡‚Çş¯vÃùÍ¯ü>Ÿı·üÚh$Rªr‡ªê®nàv–Ä~·é~ú¢âÇ=ÍkCœŞíú\'C»gõç×@İã°µ­qó*³ôNÿ\01Vqm–]kœécAh‰#ÚÖìúM÷„FõÑ\rW—9áÈĞ¸kÏî·Ú­;!c4-`ĞNš§ÇÒPt‹ ¸nÑÑ\0‘?ÔQÈ\00ØN÷cHüßì)¦6µÓÏ¿:æh¸ş<ÄpÙ‚\ZO»k‡º¿ëªcÓx­!¤i\'¸üç\'kÚ	ähs\'ó“ßÍnÑì‘Îßtn®ÁïüÕ¤×ÙéUÌ†\rñ\'poÒö]şcã›-²ÊXÚé\07€]¶ÁéÏúA]Æe5ĞúââKdm1ùø¿Éõ·}5ª¯²ğÂËMekZæîaİ5»ü-¿ÖzªÛŸö¡/Õ;]ğ-nÖ5ÿ\0àŞ¬älq/2ûAúq5ˆúMßş‚­ß˜ÏğK<šÙ’Ì±áñ©&Aöîú~äbtÇTÕÑfKkg§u7<´-1í{·»ó”mÙ‘`JµÏ	3¹ºıów§e÷z›¶’ØclIw·ÜÁı½Šók Tá[ƒ«†´´ƒ¡Eşßoç~úpİÜãêVà\'İ:ƒ¡úüß~ßÏIİ{ƒCÁ·ó±é?­µ>5V>¨ÀÓ;ˆúk?5JÀ\ZöVò	\0hO´È÷ûG@TÆÜ†°li&ÀCÎîğ~Öı\ZÚë}wTğ^ò\03³İïhşª…ßYáÒ\0n×8ğ=Í~í»}ˆ{ÆòæNç‰.1ï£±.Uºæ½áŒÔuy’á¯ıoûP9\"¦[\rs& É?½şè!=ÎuUÍØdÃHËÿ\0‚Pu°ÿ\0Ov¤\0@2cşùş‘*U§G¸˜€Â7÷­ÿ\0 ‰EaÍ6Ø¨5\0\0Lƒ-k?Ç*ÚïUûg|±¥…¤ñ/õö·÷´pÚ6²vİ\"èÿ\0©½4¥v½Ï/{.’ÚÈww·ÛSªŠÖÚ3ÇÚXAØààFàø{½®úÎ Û[Ÿ%ÄZ×ƒé¸8ö>ÏwïWôêÅ•lsšçZ×™;\\µßğ£Mı©E“K[y;œÚÆâCcóGï~sv»Ø¶¶½®ÚñÄ´mßìø6*÷[@ÇpcCKwVÖ’}Î?á=ßŸZ‹½6¹´z›lp‚ø.;75ùÛ_c¿óÒ	\\tü\\ª@¡íuAÓsv‘iq?Îñô}ŸÌ­ëÈªæ²¯k[©ˆl}?Mÿ\0ôÿ\0ëk9ã!µ;	Ø\\-±ó·~¯nï¥ìg·ÔúÍ«ÕPñçİ`šÚ	/3 Ûô½/Ìı*mõ;%%uÕ¶Ÿ^IÖØí¯.îó‹}?çöcÔçSîh\rÜâtiÚï{ÿ\0;Ú³™¹â‚mõEÎwZÏÒş’ÏIŞ§·ı9s+fĞY´m˜Ò=öÌM:T„•ä6·lnÛY{ ¶?1¬ıïr®¥ÖÕSœ\\×}¢=Õş÷õ÷şb¡•e€n2aşÂ\'ó÷¿fçìB~Fı•û	wÀn¶}¤×~‰:£ÁÚ­,sÜZLÌIßÍvÏsö¨\r®Â×1ÛYp‰€v¿éßU˜Çİ[*°¾`ÄìÙ¶¿u•úßËEÈÊs 5âÀ@—˜>›¶Ÿ¥·şÜBŠXeZÚœÆÒÂétºÂã¾ë³kXÕZãM–†a&·4	şÊ_E™îÇªÙs«›¬IäïşÏ±Fö¼9Ô¶ĞZ>›[Ü¸ş³hÿ\0 ô@éÕ\r–_KO¨òÖµƒ`hsÛí>ïÎúu¦ƒs‹lµÏª²}6™èÜ×µÌvû~ŸçªÎ±ÎÚ!÷	J#Ùô7}=­H\rì¬Tw\0í¥Ó ü×=úÿ\0ĞG„*ÒUcm¨ú/tìtk½Íıå[Òi$ÒıI–»S7ßµßø\"-¬n)v×<¡­vı5ŸÕÜ¥Êh69öWêİ¨d“·_Ïs?êöÙjÔŞ÷8Wµ­iÕ=À€ØúïßüõpeW@\r ‰h=Mßá}ÍU¨×°\\]UOvá[$‚úMlÿ\0 ¦,Ç¬îsµ±ÅÎx÷~g±›}ÿ\0éNû}‰i­x­í´—T}Îkıßğˆw=®&t²ôİ§ôn»¿ŞƒkÙ[£OpÛg\0ïßúOş¤Q†]ºö¿€×W\rvï¡ùˆ²µõYS}¡³¦×\r“cÔ7e²¶~œƒmeÓBuÙì÷Wb²ÃcÍu2Ò	 “µÛ?ó„<‡°Nñ»q\r÷jàí?6¿ÌDêlãÚçí°<<ËÖÀö†ú–ÖÛãØæêu62`í;\\Óô}¿ğ/üÅJš½:i›N¥dA?G÷ÿ\0›j;.6\0üè2é–îù˜ú¿Ó3ùÔ:i²’½Ö;u¡¤Y¸4\náÀÀúOşOµ\0»i¹Í;lkÚ×í2Zèú?¾ô|{lÂÆwMÖ&[oî=—*µ¼=ïÈµ›,6£‰-k~§é²¿ûqº“1ísÜË¤IôÃA\"Ô{]·Ùÿ\0‰’NKªº»!ä™h±ÍÛùÛ}Oø´7Um,ŸqšOßEÕû·núJ½¯¹Çc’ÆËƒÈ‚øØ}?ûq#Ò”éÕc˜æT×í©€L}£ú6~â%†ºÉ/¨9ÇvÒÖ‰.pı#÷*l¶¦cmyahÜŞÒËõ?qH¸±âÇï¶Ö¡\Z3OÑµÛvlØÇ{Òªú¦İàqë±¤X‰ÓµŸùñVÈ,­û¡ÍÚD:D?Fïwç¢6ÜkÎ\rìpÜğcù¶éíT²i¾çnvãY÷SY†úcÛïöm!¡óAÕ…v5æÇ9Ûl°€`xzŞ«}ßOé£cºÏQ¬}Íyhƒ»FÃÑÜ÷z¤U1šàĞıûÃÇ\Zn§æ¿÷ş‚‡êâv¼CvÕ:‰?¤kÿ\0qÉÔ‡]·¼4¸µ´‡4»qÎ;¾…mz²Ã¹­©’Â†÷¤ùıÏ{–;=WWîúßÉÛå_»÷¿œOMîcÜ*c®öí’ù0=Ö~kŸîüôŒ;*ßÿÓª2l¦ÍsƒI‡íå¤ıúİÌF¯!Õz{ò\\Æ¨gáô¶şcıª•õ2¦ú°8Ö@tæÿ\0²‹ë¸–µ®klpkIÛôZÿ\0åoZ¾Ö«q·²«Ôv¹®{†÷Øÿ\0¦Ï³í\re/@ËêyWß¸Zö\0\r.û®csè£`äPÜÿ\0ÒÒ)ÅÙ¹ú¸šıw¹Ş£Üïü\r[É§¥Î©²\\#Ü@2Ï¢ú}PÊëôü´Û[}Wk[¸‡¨ÚXÖ]µÎtîyĞ}®İìDa\"ğÂÍ•\rHİ½®unïc·µ=øÔú¾¡Î“,wº;~æ–ı/ôoNëMÅ´¸n‚hÜ\Z>“}¿Î{ëÁm¢È¸:§îéc	li´}eŸÈÜ¥&­õ¼Ôæı°\rüµ®õíæ¢<áVk\\Y!§Õ?Ovİµ½µ{}ŠècÃ«s}2Ljî{•Yú)–MN¼ï¾ñ»’òÓ %­M]v=ãc@.-ÖÑ›[·uh4@›$¸nqs@ÿ\0Ï{61ª½ÖÚl¹ãé42@‚>—¸ûØä½Ü©m?jÛXqyÛ\\Hı¾¶±µû\ZN6@²º¬±ÏkÜçÙ`hwoQ›«ıó1î®Çµ˜h¯İ«o©ûÿ\0ÔVi{òk.Âİg¤uq‡{œ6ş‹Õ÷µ\nDÛ½Á‚Ãµ¤Oª×5÷%Ìú(·:Ö\ZÍÍ’ãáôªc÷ş´Ù¶äb»Ò!à·miNØï~ú•g¹×Øó_0N­ş¯·ó·£¡ëö\"Úù¹á¤»¾Ö»iş_ÑÚ¤â÷RÚîp‚@ƒ3ÏÑöµ§ÖÆì³ÜÙ$ØÉş«ª±ßÈQÈôX#i6¹§éL;ó‘­ÍôB@ZÀÖ—´9±h‡‹‡òSos½˜\0óâ•ş¾¢ik€q\r(Ú?’¢ûw‰Ğ4\Z4%?e«‡¶’ÖÃ¤şoõşB¿OUÆ!Ø÷USîZ\rŒps[¹¾Öş—ô›ÿ\0Â,¦’©&F¯v¼èïoö”ğp/yvK-bµû\r®˜İ½×üåş¢z”{%Ñİ¹‘•Õv9ºÁ‘Qh,ƒô¶Øáïıõ¡nN¦Nm›Úæ4Øµ¯s³ÚË?á?›ÿ\0±²Ÿg®çú¥ÎkH¦!­d{›ú/æëc>š\r¶lªcç×nãcäÒ1ş§³İôØ¡ÿ\0u¢öÛhéLa¹Í¶ûga\"¶í~Êìsÿ\0ğU,vàd^j³Ç{‰`¡Ãt¥ú7ú­Øßä\"ĞìñÎÈc£Öï$ín÷;è?b…ı3¨c\rİ?Ô-¿éDn°Gºªß_ó›ë)üBµ5#ıdQúy%¢¬\Z^ûë.ªö¶[Sáñ¼z>v[·¥şôi?\r×TÆ>àÆ´ZÆIuŸ¹^G¢ËXÏOşeÕ\rWce‹m­o§c>œ5Îo§ú]Ö~—óÿ\0âÑpF+—†1à–¿óÛ§èìôüâ\0Hû$›læãÙ‡H²K´€ç¡éı1ÍÜ€ÜªXÖ¹Ï÷Ô¹…Õw÷2¶ÿ\0çÄ7\\s›cÅ¦ÇÌyv¯o¹Ş›§mos}M´Se‚Á “0àµ¿«şz\nßU«ägSs@}{ïĞKZcè3¿é†MIkëo¦Ø\r#kÌ´¸Ìv×ìŞ¢ê),Ü6;[-<~vßÍÿ\0ZÔ\r-~Éc¦Ÿk÷~ã™»üô|šÛm­ûËBæê\"gMÜÙìÿ\0ÑŠ«ìÈ6P_Ğ$îÓİô¾‚•AÙ6¿ckkØu’XÒgwÑşoûGû=\rÓ¹åÄ‰ ‘´‘îfÿ\0Üüÿ\0bÏÚ’¦_Ai96]¼³ai>Ÿî~wï©œ|<r,e€ØCCªq\rs\\9ì÷nO…Pi{^ï¢ßcÑ\'İº¿ôŠléû­ ¼\rßD–Áİíé^Â…QòOEë†9ÏĞ\ZæÛôÚáÃßÜ÷+˜÷Ó+­Õ6æ´~ÀÓ¹Ãèû¾Û68«‹,ºãUÌ&˜3@	şmŸË©¸öÕ5ÙS\\5Øövµ¿EŸ¿şgé“H²Aêš:M´ln4‘[=ä˜p2!¸ìsYÿ\0ªÖ}õÜ	.lÓè\\ô™ùû_±ÿ\0õÄlM­eeÍpØA’öîüæ7ó(şÚ6N8û9,ĞØ6ú¦Ã,?¸Æ9¾­i¤Q¤îĞÇª‡NÛk§Òî¨nßº6»Øí¾³Ó_‹C¨akÜòö{\0öOîï|ìV-cEE–Å…º6à4>í¾®æ=æ¥cjc\0·ÚÇv\'ÜÒ~—»nï¥üß±#­øÔ=µ\\óMúğKëÜG§üÛ¿HÍÿ\0÷!/G9ÏdÚÖ{™s 3Úüí‡g©eŸCgøE‹1iyº¶ˆÁ\0Í?›k6{¶ˆÖ\Zéõ]meáå²Aaƒµ¾“ÚÂÚ\Zÿ\0j+Ü÷–7/Òß»kK\\HÚüİÏWü¤«º†ÔË2¸¶À 9§ÚgkšïëVÉÉm\rk]é¶Æ´>…nÿ\0Gê¹ªÁs_Pµá¬­ÍõÇÈQëş•¯Ú‘Ò¼Õİ æ»Ó/ôö‡näék[ìg¿Ø‘ik«iôÈÁò7¤Ø­WèŞÙ¬—2\"¶Øğ=Ä{¶±\nêœÊ€qÅº\r~‹iwöÔ‘ÕijÔN£‚İUŠ™¹ícZíÃ]Íó^ö7İµAµ=\"7m?£h×pàØ‹c›shöÔÒï0H ı7±Û>–ôI¤iŞÚÙA¸V¢vÚ+.iiu79Ïoï{?&ÖÖêëÈÇ±£kµã_Îİú?fßğHÖú/²‘ikI2vîüßÓSùÌF¯<¼Lâ¸9Ìní°â6YêFçz{×ÔLïİp?“Z‘kocıZİ5Ì{$oº>‚‹ß!ÒâZtî-ÚÖÿ\0mÊå÷PÆlÅ¸—0}\"6°GÑn=÷³ş2× VïR½ö´–IÜXt?é?ë¨‹>,…†Ã[Áú-ó¡Ôûv+¼‡†9ÃF†êÿ\0aSc¹Å¶ncx:“ü–ÿ\0]X {¡ØD8Á~ãùÚ~“fßÌNš\nWXÿ\0T–6ã\'qnÔ;[k™¼ZbZu!Óü¯úµãXİIİ$¶f=ÇÜİÛ¿=êdLˆ’Hi$4\Z¿œz%Í6¼¹Áğİ®h0AçŞÏ£îSÅ´V\r\0Ä4>ö·éU>ÿ\0¥ûê¥Â¶¾ØfØ™ÔÚ|¥ŒÓ¶XI~ĞOy÷Oµ%7ü€}„ˆth\Zßì%[ÃícI\r$CL:cnë?2Õ\n,g¦éØu1\Z‰İîşÓPvX	kbdk¢*o°–k[ı“\"Dí<aJ“%ä>@\0:5ß}j•V9–¹ğ×¡&CHœö;rµ]§Ôõ61§vÓušãû»R:©#®†°ûM‡étçslÿ\0D~} :§\0İÍ;@Ÿ¤Ç}úíJ--°phÑ¿Hxû}5Ù^İãR4kHé±0ÄZëÑ×Kæ“ùÅ®nßóër3ÍÌ-Œ±öˆÚÓ\\û}kô[?ë‰î¬\nêp\r\0¸8	ú^îOõ}WõN8}Œ¬9›H4¾HO©ëíÚ™©+´µy¬2ûK·»w¦?–ûƒÿ\0=®ZUKki¸4<85Àëş\rßá¿®³pò)ôvâ¼âY£¬î-=+\\í¶úJí6Ü.ee°4KÀÜ]ûÕ4Ë)ş¢l¼ÓåHB]™Í«ó@q˜ ûZí­¹»7«×ıŠšİcÎï[èÖ9;†Ïæ¾—ÑY576²Ãê5­;Z$:e©aÛô?}Y9O{\\K4½ï‡Ã~×ıúÚ\Z“¥ĞS&æZÖºŸMşßa{€$°Îİ§ó­ÿ\0ÁP2\\æí™ †¹²I;v´µ¿½ÿ\0ŸfS½VYXö<íŞÁ\r³M¦®ı×9MÖzV¶¹® ’	oÑ–=¿F´tS7WçÇûZ\"68=ÛèÒlÛü×úEj§–Wëík€;ÑÎ¿Eıæ*,êç¿¼¤¸şó§wÒÿ\0_M¼2—Ù[«Úe±;w1Ÿé †nÆaÍ9.phôÆâÒdû};÷ØÖ1JãYkÜZ\Z×K˜ğ »é•—>·×qxm£W4‘·iÛ¿ç»kí©V³Ğ|IÜw€ÔhïÚ?ô_ø4Ğ–İ,5Ïi°Dß¤e\röîÿ\0ŒMm”5Ş ––Ÿe„lû›şµ*Û‰—éŠàúm:.Ü?òhN¯\"ĞëI/;6¸\r}¿KØ×ûÌO©EºŸh\"²-hlˆ!ÇËkv³vïûqR²Æ·c¤İCF²[íŞ?=$°Xà ‚78Çº=µ1»¿“ôÔ\\íÁ\'G<Dëôv5>!i-ÿ\0^ÂécÁ@3 ³ßù¾Õ’À\\ö°ğİüÆÿ\0!ŞßbË°‡‚é,¬LÆ»oòQ›]O,e–ûwAhÑÚşrè‘ö±êA÷VÇ }{¶ÿ\0)Wº÷¶æW´íd™s€ö5Û¾æ¡·1Õş„<9Âyÿ\0:·{>š½¤¼È{œd¡l|P¼’Km·>Kê-mŒÖ5ÄGø76ïıH™–³fç–µp4Üá7;b¦Ûî!ÛÆæ·äÏ³ÃèìBeì€Û7âAhÂ?{k¿;Ôc ¼ ŞÃi¢±]%í³q!Í:—o·Ñÿ\0NŒ÷6ÇÏÚö»u‡‡‡oĞ¿èÖ^‡Sİf+lkƒv	kˆ¬ïoEşïbö´¼\Z÷p`\nÃ†ßWkÑÙ[ÿ\0áˆ\\Ûxu õ˜Ø@üÍÎ÷û¿ö Öñ¸ÖLKìŠöÏ¿{ıßöÒq.õm½ïú50\0ÙfíÛw3èÅ)Rnf=V][…¯k\\ Æ¾ï~×7ô›½D4½­6\\öÃ]t–—	h‚ıT2YVm7C¬†>X@Ğ{\ZgÛ»Ù¹M›ßIõXLÁ\ZñúV·ùJhÜ-/\0Ñ[ÀÁ35VÖş}Ÿ¬$hn¥ËÛ_utÃwÄnÖìgıı1ÉPAÜíÎal2KÔÜßå±Cí\r¨mÚÒ@qqèıÏc¶Óÿ\0¤uy½Ì\rlm‰Ú&s÷S¨îQc¢z›k½<‡ê[hŸlK[t~j6NC#Ös£xÚXHmšÍ¿Oèµ	×7Ó@Ğ´c¾†ë½Í{¿Ò2”ˆs;Ü\Zvî3¸?ô{\ZÖÿ\05ıD¤±\'M´µ´1]Ü×	\0Ç½»àßüãĞ²ÆºÃ\r;Áhkf+níßË±J‹\\éu’İÑ?œï1ÿ\0¸„^C,ÅnĞÖ9ÒZ	æûôıˆõ×Í_pÚÊÜç4$Ïç:ÆğÈSÄ8õ±Ì~êßt;Âô?}ßŸÔ^_d4XĞÁSbGÑnÿ\0êÿ\0àˆ3Cr\r¤0»cA;Œ¸nÛô¿êÑˆSÿÔËstîqæ5™ö<îÿ\0F¢Ç\\Ì†Ö ¸¼8üïpßı…;vz¦¸É€\"?–¢âË×:fá¬kü•£MD™½ÎÙ¹ÖX;4sù®ÿ\0ÌªZÆú5fµáƒVG\ZÍÚÓî÷;ùÅA¬ôìæ·PIïôíş[Ô®°^×7h—I-ÎJ­Lƒ×¸HlÇ¼iÛ?¨[\\ö‹™^öÖaâ¿o°ÆíÛ5şõM¬zs\"íÇIŸÍ…z§±ÏpuL¶¸kd=Ãùæ±ÿ\0àŞû¿ÿ\0JD\0-£ŸCé®ÃP°Ô×9­{ôtKv8µßÍı/ †ÇI®wÓ\0Ø’ş¤Âóú#59 ³s€Ñ§k½¯Ùş§şgØç{µ-·˜å&Çë²¤ÛaÅu9.|q¡qüÊöÿ\06è½ÕTæY\rkœ‰nÆmwî¨2`;lˆ.t‘ı­¿ú-X²—º–z·´zntC\\\\ZC?5Ş•~äâ€„L¿Vçé‘Okw~r»ƒ•H¶.½Â¶O$hC¶ØÍÌş¢ S_ú7o&\0yn°?3fåo´\02ic]éîfe; ìwøj>Ê½\'~gó6¡\")1İ±—Ÿ¸5´TXÀImîvæÇÚíÎÙÿ\0©zßi{ª½Şc¦kl×2ïs«¯kÛïgø5 ü+7SŒĞm.Õ¿‡Vùv;ÿ\0Kú/øïô(Ø¸uÚê=;+YS6×êmô2(fçú7ƒÿ\0†ÿ\0™Æ€®á(††ÿ\0kE-º¢ûaôÚÆ¼~s>†ÿ\0R»?õbÆ»Ş=2C†í¡ÃA2×mİü•½Ôï¯3n>+ŞòöµÏid5¬vÛö›Ëıgş£Y}Gç¶‚ç}Œ5¯±£R÷ŸÎÚ”	áé¯EHkäÕvÖ¸V×j9<GªÚ˜w¾ƒ{@Ôl“?¼İŞÔØ4VçŸX¹µ¸¸¥Î,Øç×Xw²·ş“ùË&3*cº98î’Æ‹¶ÚÍÄı\Z,öúoÿ\0…ôSø‡îø-¯âÁØ6Úk¿ÛæícA¸ß·ôŸñw\"çŒ&âŸÖœıÁ­¡Œ\rkáìf2¯Cş3õ„:–{12Ù¸·y»ŞKHkkôÍı%Ÿ¤õKœÚ,¨>êì¥„\rÂe­ú^Ÿ½Ÿ›şbaí+§„ƒéO—ÕMLÃŒv<¾·êÚpùınr–>\rõ7m\r·Ôl ÆUkÙ_æÛ¹\"ê-¼ÛKŒêzo2ßúçµŞÏÌUo¼Yo´¹ØòÍí#Ú@?Í½¥ß™¹-½]º+}cªìkŸ{.¸À5Ô×¼»_¡ë{æşı•-lœûÙö{±šÒ[ê>æî1é9–¿u¿ğŸáMy‡&²\ZÛ\\Ö†Sh;Zk\'é{·3úëC+?)¯£5ÇÕ\r\0\nZ\"°=Û~ßÒİ»éÿ\0ƒÿ\0„JR£¨¾Ä(13s2Şÿ\0µT×¶‰#+JÍ`ŸI²ãîÛÿ\0ë*ÌÈæ›-i€Ç™Gw¤í¾ïôh˜ş¦=¶Ó[\\ödµ¢¿QçÇæPÏí)ı™¸ï|‰ìú´îwï~bQ‡4|@ı.WFğÍ{˜]ZÂ¹âºÚÆ8<M­ş]¯z¡›s®ÌwÙ+,\Z»{x£{·şb£–çQ§ensyÁasHúf´Z\\_punöè\\G ‘·w¦6©\0ˆ»\"Í‹B[’v›œY¸È.øís¨¢jsO½Î2L¹¢FÑùÌ¾æ+öZM\r$Öv€Ö²6º@şVíª—Úm`!ĞÖ7†Á\0OîÏéS&dS“O¢Şö–¾Ë?IÍş_ü\"¼úª¥àÓc\\Â6Š‰\0´ï}ßŸïXÖúµÚ×w„şê59!·XÚØç{Z{6~ÏS÷ÿ\0–€ÜŸû•:¥_gsë}2\Zçgİùígü.¼µÌs®ÚvºO½Â~éPÆ¶êŞğë\ZÊŞ£¬8ş‡ÔwĞıÏWı*·œ+¯qºÂ&·ÁÛ»÷ı¿Ëb\\^ªMhËnKhyx«ps	k ç=Ş¦íÿ\0ğŸèÕŒzKÜì‹O¨ös-sZ}¬Ù>ÏoĞ@Å±§¿—´{ŞÎ­İé¶æ6“.s˜H™-šø†Àõô·zŸ¤zi¿ØèVü{ví«kÁ†´h4;¬mælb¯vA®ÇÍ¡Àü‘í°Yéï¯oò†Û)­Û­cœÓ!—†±ùß£ÿ\0JËïb=•PÆ¸¶Êî©Í–—É0=ş×ı\'ûüÒi^¡-:±­}ìuî°ÖæM»¶–´Ï»ô•ı? ¥’ú›Qw¤\Zİº9Şà=ßMÔY»ô•ÿ\0„ôßÿ\0šÒëËª4ŠêkCšÀK\ZïÍõÿ\0Hªdÿ\0G²·ˆƒº?•=\ZÒõ(¶ÃmÆº·ËŞíCš=pEÿ\0FÍïÿ\0„b&9±Îu¶P.¦Éc«tŸçœÚıÿ\0Íìÿ\0®*ö=´ÛèK­‚çáûÑênoüêyÔÑU›7L€ZÍÎ?™µ4U¥ÑEÁ¥àÔö–“`¢ínÍß¢»ÛZ#*£)¸Y¶¶°‡ÓôÚ7e¬©ìoë-ÿ\0…ôĞ…ÍÏenÉkj-w±ÀÒH÷Ùemo¥é?úÿ\0Î(ı¸…íß¶Â	‡Ñ¿ÕúÍ‰¦öJ¯ÆhÆc¯c€e\r{‹¼¾“®µ›gÑ÷úÍªç\'{Ëé6²KXw\0\0öûûÊvW•“i¿ÖsÉ×tAnßQÎ¯ß[-ÿ\0š Æc=¦G»Ól´IsHµïİô¿;ßìO\Zi×ÁnívİkœÀwh2ï¥ıV£ãæ[aô[£¡¬.÷mû‘»éã¿ùjS6–9Òç§Cºßä©·1Íısš¾H>İÎ©ÍN–¶\no\"›ß/mQ}gnCX„}\r¿Ÿù»?íµFÁŠç:»Û¤°öşsìÛüËı¿­¿¨}¢ÂÖX@s«2\ZàË=gı=ÛÑ~ü*ƒ_‰ÒöşŸÁ­Çk.õk™ï¶ÜtÛ\"‡àš\Z–…ckÜïsœé\rú\"#é¹ZfcÉ\r©–9›C\\à¹õ~æçÄÎÅ·Öu¥ôÚ§`‰ó[«~›ĞìW²¶\nØí¸Öi.†·ôÿ\0º÷µşÿ\0z=TB ìgY%Ï.\\`5¿ÊoúMÈº	hĞD@ä‚•íMµÖX/»¹tD8–ù´”7Øé@h:ëÁúAK––Ãr_.ªÇkd‡y{{nkS\Zò Ä	Ãˆöµ»U_¾Dèw9ÃYıï¤š·´XCú7Ilè\'ı\\‘#²ÎÂ¨8=òĞò\rÔ£{÷ÿ\0“›ˆ×±à=.\'ÚÅMBßQ0ékšd™ Şú)œê­ci}…:î\"[>ÛïwöN‹›l§İµıÕÀ‰k€Î1ÿ\0÷ÅT¶¿´Ëk!üü¯£µNš/.kkş˜qaZænßúÚA®Mu¼ÚK@s¾‹\\æıM­şsûiõQ\\‹7¾}§Èé¿óUª÷Ã…ÚÓíş¹ô«cØáS½@aÒß=ÍüÏôŠC´¸ƒ_ÑÂS´¤6{Ya¨}¸4DmWŸn9ÒÈ‚Z@÷;OÌcıê»{²NÖ´í ÇîY»÷½ŸÎ#†_±ö²°ç°ï†·GG¶l©û™ôô¢aHol±»k[f;õõbbk>‡µŠ£í/a/h2aÁÚÿ\0;¸ÿ\0-NºİuUÙM†K%®fèÑ¾ïÑîÛúOä=U¶ë²‹¨u£í-Ôˆ\rÒ~…ƒ÷èQ$¼ì¦bÒÖ2ûÜÖ!¥ºC‡±–X?ß»ô{Ùú4ş£ñ€®‡:ºİ£_a|ğí«÷ìA¬_Qõ+Øã59ãÜFö±ûÿ\0;èXú•šóØtnİGï¶·m³z$‚Ñc«VÛË­¯cÍÖ†=¡ä\0Écé9¯ĞW0Sªm³y´Î±û–{[³ı\'±VÉ¥Ç.q«kk}F6¶Yì÷XÚ¿èzŠíAï§Ò±¹ŞÚ¿5¬0×íî!ú?TõSêxÈõ­{\"ZÊ˜Ó«„û_S!»\ZúÍíkÈ¬×½Ö9Ãvéö‚g©]ßø\"ƒú…˜·»0µÍ4·Û±Şİûÿ\0¨¬\Zğ^÷d0I1¹’Ce£ßôÿ\0—ÿ\0\\B“a%5ƒI-&×´H<Iüİ6»{??é³)ş‹wG¨enXÍ¾º™üígımHRÊ	smq}ƒV˜!‡é:–Ôı›ÍÊ–kí\r–CZ@!À5²7{›é‡\"\0?±²u¦+µ€†\ZÌ½Ğ]¸}\n¶»kw9Î@¥Ö«{Ã‹·ÇÑ0}Ôîoî5ßÎïOuuŞ\\^ĞÆ^Ém„´wî?è~zYeö±¢Í®©­kba­İ¿Ôõ]ôÿ\0âĞ£t«m;:ç¾ÇL\\Öò=¿C÷¿àĞÏP¦ç;ÑÓ½\'émØÇ»ÓVí5ÛY²ÂâXşh }oüÏğCb oåK$\0Òt€?á?¨œ5ú ¡ôésÉ2æ³M5/¥îşWò’úìÙYs€i>àØí·g¿İô2oİ‘{¶·™öè=¾ÑüÚMy\" nZ¨3ô½Ğ¤Z¾@%›7¢d5£÷·}#a`;AŞ[´‚u!ßší¿á,oÓ÷¡¸=¶m»ÜKÈğ¶¢w	$nyöí<Îoşfš”ş®ÒÀıd‡Il÷%\Z]€‘¤Í¯ÚÖºŸ¨{x&vúØChxİé»{ƒ¦Goå¤¦Õô±“µÄ5íİQàré½\Z¶:İ¥ÛKº$Ñû¬şsnï¦¤ÛËÁõ\\\0Ôû¸€“ônßüß±2«k®Ì†¸8ésH;¿7õ†ƒsĞéE-ê˜ÌvÛUd†‘cÚt#ÛêÖæ;ÓØ£lqm„\ZÚí®ii:ŸÎ©Íÿ\0	ÿ\0ş:ÒÖ·Ó­ÂC\\â\0™úU²CíVn4—1™Ç¸»pyˆ.ñŸ™íM Ú‘®­j›eo¸’^æ?s,“£teôÿ\0m¢â\ZöÖĞ}‡5¤’Ç7·Öİô®‡sísm¿së5¼êèßüæÆ¹»—mh˜®.Ùu›X}ítn{‹FßoæşjhKf“yÚúšIvljÖÆç5¾æ~ƒzª‡nKÉpea€\0İ¤¹ß™ıejºì¦æv6İi%ñÎúKÔ¯ı\Z­ùÌÊ6ise®$¸ˆüÍŸCfïÌK¨Wt³e~…`’â	wÓ–ı×¿Ùÿ\0A7Ú*6¹¯-\r\0°;o¸ÏÑgü’`ú€<‡5ƒt’Cw[sô,şB“Km{¶´î06£¿l»ßÿ\0ôá½êPµWÖÀú±[¼;R×NáôİíÛWÓRË{ßQa#pƒ^Áv¡íoõÿ\0õ:®È®ïÑ²çàĞ7GıW½Ê6QsÛ\rdıÁîØîw·éoÿ\0¶Ò>*EI¬Ö+µóc}ÎÛ:»ÜîOæ ŞòG¬Àæ‡ôt‚w7Øæû¿Hõlã\nËŞáêÿ\0„¬’íµÛ=¿£új¯y>€åÌÎB~–ßì 5?N©Ø6qıW±¶‚×5­»¬ÍkÑ«ô1”~š¿t,a€#ÛmV=ßG÷ÖmVı—mMs} \ru2}Ï.oĞbgåoèõÁ¤Ÿ¥#Üæ3nÏûq;R‡ÿÕÎ~5˜ö½ÁºîÙi`% Ÿc[¿ó½eZÒàæ=¬†IÇkNßÎ[yÎvAª«˜ê…~úèn®wÑı#¬cjÂ£÷÷ªD» zÕµ­mN\0\0$ı»Øü§şĞW£\"CX€ÔİsAxo°;Ù1ş¾ä[r\\÷£5;¶÷«7K Dî è5şCÓŒ‡0ï†¸ĞàyoĞr’÷ZÜÂª¬†:²Kí­®âí\Zÿ\0ç\ZÛ?5µİüÊ±`B»\'sZàZÖÇè_¿e”İ[ıõcd8Ï[ùµV×;\ZÖdÒO§x`\ZÇ¾§müü{½Ÿø*>ô?=¯½‚›Üæ¹ÄIcØâÖÙê6×:Ï_ÛüãúU]ôÿ\0	xì‹®×YÛ‘U“ŒÙ¦§“!Å‡ôÛıŠ ØæÉl$ˆÿ\0 µ2œ:CÜàíqk_mÖ´çz¹­g«ÿ\0n,ûh4İfâ˜0u‘íÛîkÌN…ƒT¶]Ğ¸TĞÙ9|’®^ü|š«¬òãíkÉÜ[´nuàœıÿ\0Ò#ôë2´eUVæíÕ­OóÙ\rúwıÏı\ZùWï±Œhj64oô}=ŸCcQÜÖˆØ9õÈÉÙ.,Î8k\r~ß ®ÑgÛZ[fæŞÍ¡–Õ^íí\'ÓıvÏô½Ÿ§«ôŸéql¢Ëƒ)o£æ¸	5¾³ù—5Ÿ¤c˜ïú×úD/M×4cÔ}ÊA›˜O¤àßs™oÑ}/÷z^·óV&Ëô®ôLzm«¹>ÏKq2ƒßî-­ ’ç:ÇÒêj{öÙÿ\0qGüâÊÊÆ§í6¼µä€ÆW«›´ú²Í¿CÙ³ÒbVV}uY[n°?BúËŒKmv~ë¿šôìúàÓã¹¶ãµ¶VY‘[w4É>°;œöíµŞïk·ş6 >²Ñq şÄìÌ7·Õ7—U[Û,<8?è6ú[³}·Ş…/ÉãÚ\r¥îsbGé^Ÿ¹ƒéÙş‰WHz[Fòw=º8ê?G^ÿ\0w§nßæı?úâÖ²ü–\nFEMõLY[œA—°†uÿ\0Iù¨é]<«Iõı¢½Â¿MŒÜÊ\"\\Iı µ¾ÏS\"ßä6«˜ÍØö¸YC\\ØÓ«¶“ôgùJØÂá·!4ØÇE,0ĞusAeŞ×Øç3úEY´}ÉËm© Õ´´îüÖZÛ\ZË=Ïúv³ÕNâEª³rXÇñÜúÜ7}Œ×è{¿q3)n~;³[Mlx­õ¸Ğç\rìôíßK÷?Á Ùm~“«Æ\r±Í\"]¸]íö5¾“}ßñˆVß‘ï©…¢·9®³p$n¬û-ÜïûmGÇ­‹áıÓúi­+ñıÖŞEOmÍnÆúLiÚÖ™‘;è#âĞÒ!Ï|¼Ó-l8;Ùı—ûıŒYâ÷d0Ö7:Æ~y \0ØŸ¥ı}èÍ²æ2°Æc~ğè.oç÷ıÔe­È_ñµ\r4-ÛcÙ*È¹ãcËÚÖ4\r„“ê×êY»Ô®íìözJÓëèÚ^ÍÍ-aZG»Şæ?ùÏwüZÅkj² 8{_¹­:Éw·s“ü„/Ú6ãZ}Ì‡oÛ£Ù¨Øÿ\0¥ûÿ\0àÒ·nª‹zöfô çÑ‘ëb´Ã€·‚Fæ?ßïú63ù«pï¤8¿Kí#ÜKcüÍÈ¹wbß¿ô^ƒÆ’w–¥îş·æzŠBŠİS]’Òà}®\r’~|!ê$I:]~¢öäWFE»ÀÇ3V‡Îçïüß¤¦-Åı%&ªØZ@kà•O¦ªŞĞãëœe×·èìs?}‰ƒıg²œˆ­í†ÄÖÿ\0¤¯óÔœ [ÄŸ.šœúÙAk}}\0#İ½Åµÿ\0ê»lYù¢{«‡\0Ùl> {~—ç­¢ê)ö›Cò\0hi\0ş¤9Û}Û~šÏ¼¼muÛ·ØĞöîà´ûwîoî%§O¢µ^A{42D|QGèÌ2şo¹3©s½7 ™ÑE0H\0ƒ¡ƒ_İD]’{ú(ì—\núœÃ‰nöSô˜é“]ŸÍû?ıÿ\0¦[¹:ï°=¶İ¶Í­Şğ\\à`»gë;â½›?Cüå‹°l8â¡i³@é%á¿K{[[¶?û¤[8svâµïps…6†“lôı¿CÓÿ\0„Q›ûšÔ›™ŠÖXYSÚ@C_cµ•9ÛJÊŞßVµ/BæVË_®>­§Ü´·İ¹şêı]éíÆõmOvÃÒµºı\nkıÍÿ\0Í?ü\" .ûµ2Fç0ÚÛµÀ»Õòş–Ï¦Ù\rwe>·Ë*ø3Üà?«ír{ÛŒÚ¢×Şíµê 5à[övïŞíìz®>Ìç\0×;|ı@{›ù¬ÿ\0Î…õ}ÔÖıÏxÜÉüùLÿ\0®% 4®ê‹lª¦ÈØ÷ncÜy­ÁÌóÍ Úı¥›¤VÃğék™s}şå7äºËûƒ¡qİšg§ùŠæo5\0IaÔ\0aí\'é{ÿ\0Á¥]\n›¸øÍ´Ö¹ö1£Rã\'_sÖ¶lÿ\0‚G[jÖØã6ip¬o§sóĞ«sÚ\ZÖ~¾™í>æ¿oıB32™e\Zıî`kH±Ä¹£_¥·sn­õÿ\0¤©éQV‰úeä‡êG±Í¸Á‚t«\"—5î¦÷o}AÛ`nkö:º¨õ‡¿Ş›9—Ùaµ—P9±¤<†°¬}á,öÿ\0„ı\"Õ¾ç6ìZ&öVË¸ûîp}ÕRíúq2FÎë†Ì1í}ÎÕJ·€ààí\'óı@çúÿ\0™Oø4ù[Í•_S=/ud5ÅÃ]Îs²=6·şÛÿ\0¬ØçÖãi>¥€eÕÙ£Gú&·ùïä6²î«9ÇÙ[«lhÖ´í&}¯»Ôÿ\0	ş‘ïNtOÇ ‘úÍ:¹Àµ³oõÑ+ª¼pÏM¡Íq—µÒwìşq›?D‡}6˜Öú„1¬s_Ã,3éØéwøÜMcä80–3I­Æ3ïÜçíú;T‚ˆÙfÌŸMUX=1L×¼3ş	ïÿ\0¨Vqi}´»Ñ§Öf÷>»-¯G\ZËkıœÆ~ÿ\0½ê¶@ÄÃ7¼KCâ6™\'İô^úÿ\0ë¿£ÿ\0F–/ Kßé†’öî µŞÖìö6İÿ\0™!gN\r[²,ªÖÓ}MiĞl—XZÀØö—‚İôZ×ÓM×–ƒŒ,lÖ~“ùõÑê5ß£üÿ\0Wı·“Óqì¾²æ›KhcLßUŒ»o©c6ƒ»şÜC¸d5¦Ç5›j˜Ù.çYGÙÛ½ÿ\0úFßQG\"4\\í\"j§ [g¨,qmr^IØï{=&UOî*VØâç9€dÃ˜W-qm·$,,ºCƒ}Û^È³ßoóŸé+Y÷Öš÷YkåÚû›îöÿ\0Ô)bVUØæ‡Á\ZÌ&hÖ²\\CÜÂLêÒ>Ç~r% ¸˜>ÃÁ\Zªœâ×5Úƒ\rg~7{vÿ\0%ªaÌh--cœ\\xä³ÿ\0jA­5}¶ƒ:êK]ìöoŸ±+¬o¡0=aí°?Áû?íİèfCÙ^CêÚu“,iö»ŞÏwü]h	wÕ$6q@ış˜À¸¼Íşsş¡3ql³c\rb¶–nkİ0Díõ[gĞüöoØ¥†ìw’vçUŞáélm¾ßÜÿ\0Õi²®ckNĞ~cZË]­•Yí÷×_úD¸½Z*´IaÍcöºĞ\ZÒ÷–‚Oı;>ŠKZñ¼Ó£‹ê¿=\n»Øuˆø(¶Ã]’`ø[ú©â–ÙodÒ_c‹ÚØ-]0’Ç2ÏøT|ƒöVlôÂhw-w¹Å’=˜÷±ØÅŞ™~Ñ[H;·\r}:wYc=îıõÑ]­m¬{Yk‡¦àá·Xú.İí§ş3ü\"g_â¥ù£ÕcÀ¯Ñ$‚æµÌyÍÛ[öíßî÷ïUÆmÏ©×åW»a-%kl{ZÏSo³Õú\nxõØÊësŸ%òÇĞx~Í»œÌ¦nejWÙòÑë1í-–;K}ÖÖÛôYúExoÙu¢ô+xûD~‘òàÃ«ÇîúıÍÿ\0£ÿ\0‚QûC6¶ºë˜]\'CôœÖ?ów)XØ;à‡‘»‰¬“¹§{[ô=/Ñ¨2±ˆCİíİ =®–ŸğO¯İVúØïz|{ZÓİ ²·Ùµ²öÔÒêë™>ò×[¾ÍÍvÆÚÏçŒ}7°Zé‡hÚÁ÷Éñ±ÿ\0AôıŸğ~š¬Ú(vic6=¥,°;V‡¹Û¹ÙúFµŸA^È²§Z^êÌØç\r€Géïşq»[ô)u²Æ¢ûKŞXĞKK„Ãœ$ûö6İî{Ú†İµawÍ¶°êkc¿GïP«0Ö+ÚKIÓ÷œßKO§ÿ\0n‚Aew:ĞûÄ8480	|8û}v½Ï¯óëiQÙHnkd—Ï.?œNæı=Û,r²ZáqeNóµíd;s¾›¶Tï¥ı}ˆvdCm¨¹ºŠš	ÚOóŒÛUÊÿ\0Šz•VVĞkû+tlÃ^7İí·cÿ\0Ò$o°P¤w_SZE-cI˜–´:?›b¯[Øû«w¤6l?º@†¹Ö~wç¢Ø×›G¢]v®ıÆök}Ö{=6-Vuµ±®e.­\0ØÆëêwkoüc‘âèŠtÒq˜c}&vã!€8ïvÏğŸğJ±¦‘û\Z\\I¬¼²CÜHs~ƒ­³ù¯ıHªŒ³T/·sD6€Á·kíF«0Ín ’Hf¼~şÔá¦º¢üÅÍ¯cXvdò]ûÛÿ\0Am¤êè˜Ïş`Õi÷ã\\çÖn,\ZÀş—9û¿w÷Õ}•¶·—ZëHú-dşS¾—ıíPÀ‡µÛÜ7i3¨Õ3÷<ú…¤Ú5˜…&¾7X¦Òx½¹3^áí€	ÿ\0É\'\Z=P»‰ÚÇ\r­;Ã¶şŒ?úé±î0ömÙ©’®#üÒnğ÷0ûgS ìCİ¹AÅÁíh\"€à¹3ªWw¦v²¿c®Óçü¤˜âÒúÛüä\0øsZ`}³ÔöÙşz–=u›-\Z0¶û}G*·7uÔÖ\Zà[ sIşuÿ\0ËgødÉ•À&ººƒCZ	h0\\wÒı>7æÖ¬ZM/Ò²!¡ãşQ¬µŸñK1®‹I¸9íú9‰\Zµ¿ÕW[k¤ØÃI|Òkˆún¯Ô«Ùù¿!öµı/%¯÷€	,w¹›Ç·õ£eÌ¿óÓâ²Ğk\riú$—´¶ÁüÃ7nÿ\0Ñj.e¯Ä»fë`İ±äXæ—û]é>¿¦Ï§ÿ\0[±;^Æ]._\\‡°	¿ŸQo»Ôÿ\0Á+M~‰=x«.Ë\\ùeº{šgnß¢Æ~wèÿ\0„İ·YnÂ}÷“í÷úlkv×şz·A£)Ì–8¼HvàÖƒ?›ú7;ÿ\0>!c6º›m´µ¤‡“´ØNí»Y_¡·şšDê4PDì+S™@sÂKÚZ›ï~Ë¿sş\rFŠ†õuw©¹Ú¬é?üıŸñhïÌ¼Û{^æ€k­:9Òîoé=¿¸÷úŠ/¾†ºÚŸ[5†‚í ƒîsÅôEŞ½”ê¶×»>±[˜ç¸´‚cİísOî*†Û,Êô[/hl™\'nŸI¿áVÏôi\ZëfÚ_>›µÖOò‡wÓb5öÛ{…øÛŸíô¬­§nÍ=ßÍzlj^JóC‘nÆ2¸6<ìsŒ¼º6ıô{şW¤zGÕ\"	É1\'óœßQ¿Ìíz™v4Zã¿Ô\r°ç@ÕÍ{ûûPŞ/Ê¤Ââí üÖÛàQë_jĞèÜ½³µÏìHıÆ+˜¯ŠÍ{ª/€ìüßŞNöS[½=Á®|æ;ôg¶Ç¡²ßx›\\Àe¤ZÙ%ª6ÿÖ£FkªyÉvÆ¸ÑK@.pwÒØÏulİù÷Yÿ\0[­V³%®\r{* ŸH-cqÿ\0Iü´7:·‡íÜN®\rˆ˜ú[°‡e°ÆÄ;‡3ô¿9hW[jßEÍî »éf¬¬k\\	’ašóü¿MOÔe“×L†yüæÖ˜€L<íçìœÛláå±¬²§³ó·ÇFº«jfç7ÓŞ×#f¶¬<š¯­ş¦+ëmNsãÔ†¶±­İØÏUg×éÂ±.\'p¬‚Ÿü¥g5µÚÊ˜ÃºÇ4ïkXdŞõ>ÿ\0o¦˜cÔ^\n¯ênósœ]Xk®xÍişq•1£ôNİìõ¿TÃéüáÊg\Zé¬5µ8Ä9âÂFÒ}¾í›YôÁ©×Y½Õ€Isâ4ĞÆ»Ÿû•¥¾H—íg‚ßNÇX,hw ë§öôÿ\0¶9•Yi´MÂ>”ğ6ïkİôœªå6Ænkàµ¥Ñ´Ëtöîö ¶û2MO?Íg2GÒFè€vş²ªÁ/CÒªûiåQë:°@$€æ‡\0æ¾ÿ\0¢û1®øjŸú+rñ®h}¡•?h\"\'oú;[ıOgæWÿ\0ªÑoYmmulw¤Â}+6É`®«œ®µy½Kí?j¥°-°NâĞ>Ÿ¡ü×¯½7Q\"HiÒ€anQ5»×`±­pkosê2G½»š?IìUeõYoÙÆkµĞíÛgó•úŸğJÓ¬¿{qlc#oÀNİÛÚÆ;Ùûô¨\\ÌòúØêíee®d‚&[½7{¿ãˆÒÀ]Ñ±£:êj¨b[ílş—)ûƒ_nÕvÚkm­i¸6öŸuCi±Îw·eVÛé²ŸúÛıeMƒ—Ã÷8–8ÉnòHíígø=¬ÿ\0·”/u:¬Òl­Ş›kw¶½Ä~ÿ\0¦ûè¤L ×‚àE¥Éê\r}†šZç±„µ¾¡\Zşnş?ÌöÂ#¯RªŸé–m%µ0íÛÿ\0yõH²í9Ã+\r­¤p-l’7;{Ùşùhi½µ9œ›4y[û»şŸ¦Ÿ(Š­üÿ\0I`%±’ü„±ö5²Vú?jgéÿ\0\\şm\rõd[KK*|IÜ›§îğnİ¿ÔUıGzO;Zİ«»ó?Ò¢â°z›½»ı¥ÛˆvÓío¹ŠbÀ“ú=èXd¶êë¨9Åà¹çËù[sj·‹S€ƒqqı^@é =µı¡ÂÍÅµ€	Ú\\ÒaÖo¯è\"Wc]’Û«\\Eu°hàóõ´cV	Ø€£tèÙÓ^YFĞËÅ{t:îçÚÿ\0¡ë \Z>ĞMwVú†·Üùûƒ~‡õÿ\0âÓÙF÷dŞCŞÂ÷l‡íÊ[ê7g©¹C\'Ğ½Ì}1Sö‹*`\'Ñ³iÿ\0Àö¹?ˆJø~š­ª«kßND¶‡ú7\0\\øÒà>†×û¶¨blôìo·PHñş°S9’ç°úÛ‰±Ç»§óš˜º¶¸XÈhp’\r×„Í	Ğó.Ô>ÇFÎt¼Šè\r›ÚxŸ¤÷;é6Å;]êÖ\\ïuõÈd4¸Gïm@ÅÌ¨Zkk7PGqô¿y¾å;~ÈËO¤%»ä8í1û¿ºö)ÅV†ÿ\0—è±õÙ³Ÿ–üÓµ€¸¶³íh|µ­ÿ\0öîe;ÿ\0ôbÌ·ù€Ò\\‚5ûßÕZ\rmmq­‘ê9²kIåñ»Û¿Üx°;Ò¡Û¬y‹\\	®í¿˜Ïë½Õ7eU±ì®nSİ´\r§Üáù¿›±3è§qÚ[Á´€¬šË@;›ù„;{N¾ïÒ7óÔêule’I7˜Ü#kX]ôâÒâÜxZ«fÖÇ×[ÀÂúÉmÓ«\\Ãù®gö¿ôkF¦Ñ›é—s\\íÏ[K›ş»\\ïÒÒ²k«&€×3ÔŞØ`¶=»÷ïú_ËHºĞúÚYw¤~‰âXïôhU÷	ºñwˆÚË\rµú–1¤8¼™}gnÏæşÇ²¿}jµ–³(±®²Æ×ô	.x;[Ñ·Ñ©œ×9í¢ KÄ‚òÖÇ¸ûY¹ÛíØïøÏø¤Ööl›­vèµ¿EµÛ»ôŸ£üôÚ)¶±ó¢êä¶ÀèÕ³í}÷f¿ç}z¬ªÊ^ñ²“»“-Üìfç~›ÿ\0KíUZãë÷´me;Iö³ı^ƒ’Í­uf@İ´“Ëˆ÷şoò Ú¯FmÊk!•Ôğ}¶C·İÙô+AºíÆ¶ã¸‡8ÉôÁx\'÷çı?ì Oxİ°‘¯ıjº21l Šı]-ûE`88ÂãîfÇşı´ÿ\0ÛhÕAxÖV×\\lÚØKLO©µ[éb»M®\0m%ÎköºíÛ-¯ØË7ÿ\0!Uv=Å Óm6·é¸×;é·Ñ·eÊÓ:iô[[ßV³×°O»ôY;~Ò×nJÁ\ZŠÔtv)ô}”bä‹,ÜKØçµ½ı¿A¶ı¥ïß¦ÅJì›¨š¬¶¶Øÿ\0k²\\Ç<0›X÷41»ÿ\0üÊÕgcVìv[e÷Hi,-} Fï]»ö¹]%÷¶œji©ÒâÚn2K@ko}Õ¹ÎöûıŸCÒL­‰×Íuµ…RÁu.7ÜıZÚµd§w¬[½şÿ\0ğ_öÚMö\0÷\\=€3ÕªI#÷mİ¹Ígîz¤ı\"N \\k©Ç42íÂ—€\\ïW\Z·9»ıŸÎàUªÙt9Ñe­kCÁ5c‚\Zá;Ü×û}&7ş:ö´2WÔjp¤mô\Z¿F³îŞÏ¡ê½UÈ°Úãë7ÜÑ1\08€=-ö¿éîö V×¹äznÖ‘Ã}®oü*¸Ìœ‡>\0õOÑ`±Áäi>›½@íûØ.¬­5lj{0©Ş/hsÚ%…›g÷ÒÇUéÿ\0!ÿ\0¤VÎ>lªÇî¥ÏÚÖ=„8n-³ôVõúé­z®-Ç±á¹Õ×±Ä¶vÃšïÍÜÊıÖ1WrK0*kªıb’Cl.®\Zæ²ËşíÙé¿ÑL7Å§P¸l0²·Ô÷Y]–1àîiİëY”÷{ıOĞªNvN·ßkl±†AÖ¢}Ng½Õ_ôjİ å°’},Â\r&Náá¶\r›ëj»şĞÌQêã´Ğw†½°Ctİ¸ı™ß¤©©åj(²-so\ru~£Œ½®wsÛm{ıª®Æ¿ßY\0-h:˜şss]îú.õr*ÈÚlô½6Ip²	?µŞÔa\Zr?Gcv‡<}9÷Zİ–»ş58Û¡«ÃÀxÑÿ\0F‡TRæ¶À\Z6N²gó¿ª]?jõ=£Aw¤ÑŸ¶wl§óĞ6ƒ_Ñ ğ#`şWÑ§¹8´›*û~Ï^ı¤ècŞ#_{¾—±T2I{ÚZ\0LÄı?gæ«e€Ò$;ôeÁÁ£G<û¿w³c?íÏø4!e{^,ãè´k@üÆ~âlGné\'º6:ÈsH!ÏĞ€~—ÑÛÿ\0¥lµîi}€\ràk6»Üä‰qqÇuš™:“;ÜIêâÏ?œH´Ôúo­Î¨:Â#sœö³è·ùiÚòæí™í#Ã÷^Ô\Zİèf„pè?”Î°º\\ù/ƒ ‰İïşR6tTÛı	õª&;Éc¿ê]¹İcvì¬Ò6Itƒw©íıßçÒ*ŒÉ²¶–¹Íçk€pÿ\0Ì–SYe–ÖĞæ—4˜’ßÍçoüjPlTÛ)ÄØæş•Î!ºŸoÒöû¿B÷êEW2¶6ÒÖqlÒó[ô>‚[PŒ‚Ö´6‡‘ông§¿gò,O–Ñ^Æ¾áé¼»m}²¶Á¿oş­Lº£{®«@×YK‹šòHúHÿ\0;j-ÒòAp²M­ñÚ7z­ÿ\0…­6¶]g°³†¸–‘d}µç¿Óú~ÄÕ\n‰.¨jÈíÛ\'ô~£}ßAª_Ï¬äZÇè\ZÖƒ:8¸=õÿ\0Ğ÷©ÙkœÜC˜Øöûyñ¥ìÿ\0·”)ÅØìÒê)°šÄÖNÖ{›êşg§ê%[ıP+©Äk-×Ù:ú~ıÕş“÷=D±aKz¶4;ß`Ún\rhqúoÚæ?ó=V)áİ’âú1ÚÓnÖ“m„L7wùŞ³˜eze±P¬Ë}»½Ã÷ôíÍc÷WgÓ2Z\ZÆ˜öÓë¿Ó÷¹\"¦ğRvÒçì`\r?ÒXïõÿ\0‹B8ÀÅ¶Ê‹§hw±úoüÿ\0¡ÿ\0ªı¡î%Ö3Ô—­\0ñ¹Æ½F§!—·Ñ¾°\"[@Ô}Mû˜’Xd›¬i©¶nÚâæ\r7Fßæjwæ,Öï\"¶ĞÒv‚\0“íö+¢Öÿ\04Iöı–h?1Ÿ¤©íÛ³ùÄ7: k`n÷—IÔ´}³éÔ†›5vÒ÷ı\";™÷>}¿E;+ik30	cş3ÚœMİ¥…³ØWÿ\0&ƒO¦Ú\ZÇ8ì¬j4 ‚wìwükÓŸb\rÕÄô¤–æ¢Òúëa%…ÎOÑÛı”œëÒ#i’:_íÿ\0¨Q{ö—Èsu.şüï²—©ó[œïk¤òd“>ßoî5©x~èhücó½ª5İî‡@pdîtrNïs\\×£ú{Ú]İº\r<7Ûÿ\0A°¤Í–¾\Zá´8<ı©ëe–¾Ç0\0^™­í!ÄÃÚy™iüån³EôÉôl©ñ]œúëÛô?®‘µPÚnkXçıŸ â	?›»fçÔô[Pª¶eÕÓä†î¬ëÿ\0k?‘k“²¯^óö¯eµévÈ-ÓôŞ›‹Ys6~z­}yt=ƒÂÌwíØàK›üİŸOjDÙp¶Õ”<;vMmß;æà	£ôöî¯Ò±Ÿø\"%A´¸µ¶5æŞlÖíÚÖ_Wı§Úÿ\0ğˆ•–UQ¯%¤?nÇ´5‚HŸğ4»Ôeú-[Eu9­q‰yÌØÃ¹•½ÛZ•’²×5ÌÇk«#n-c6Ÿ^§ooÓ³×nË=Ÿáªi«gªá±µQÅÂvïöŸEìÿ\0Ïgç¬ã`¶–ãÖ\\ê¯3S«’7nkïÒ·÷½ïı\"½U–:ÊÈi­§ÜgB›}›÷~‘íşmŒM”–_ŒipÅ-;µôËAíìôı7{?áX¨SaÊºÊ««xŞÖ—\rÎt9£ÙîúŸéû‡šî!¶PâDzn¬v\'ôôßélÛk~šä6üªqØ\\íÛ_é€×\0á\rô¬¬~“ÙşÔ¶şÕ3f.5Ï dE‘€öµ¾ïçßú¿übk-ûC[²ÒÖ¸–°ºqÿ\0„Ú~‡³ùÅíuu³ØCö\\7ƒìıß·ôŸ˜š«±ı]¸Ô—İ\'WÉkGçŠjÿ\0«±éõ*´ÇÛ™½øíq¬{ìú;ò[hcéÂ*U—¼‹Hk_ºêöÈ‚#÷›Sÿ\0®®]‘@µÄoÜè÷‰Øk6·ÜÏ£ú;v½•ú[j\"—3s˜éº·¹›?E^Ôà; £s1=f47Ó÷ÔàwK€ØêœÈsôşš™µÕ€Ì;±£f×Iô›6ÿ\0¥I…¬¸’	eR\\×ÈÚ]ôM¿ŸMŸø\"µÕ4[éT˜d	wîzßèÒR¤U¸—\rÜ[^Ù³ú_çïşº/¥X2ãŒëû½¾ŸîoMHvëæµ¤E’Aî?y»ì÷¢Xûl\Zm`Óo¦gK{†ï{¿ëh„?ÿ×çÉp\"İ¿D‘¯ï~nÖ¡\r‹} HÓ_İüå`Xİ…¤—0Iˆ2 qÚC¥ÇcDî#ºĞ®İš©7Ó; n\Z†Ã¦=®İ¶§ Úâ Úê>¿óÓs÷ƒ\"\ZHıßrcŞIº5ŸÍ?ç\'¬÷C`Ş›`é”Vä{%ÖAwm‰Ók·/ş·<:Z\0¶¼\rGõ¿y+‹˜Ö˜\0L´N‡îüßë¦¶C_a\ri“ù­\'Ü”¬´º¡.1v?ù•klÊƒ¸jHïÁŞã^´°~ààëô·{“AnÂĞ@l,t4m©ÇVƒÏö‘mÉa°zLmrØ~Ş\Z~‹Ç%ˆl{K€€L4ÍE¯æ7Ôs=®ú.ï	+¨ê7µiVwnÓ‘‘¼UnHkô!îÒÑík_ÓŞ¯UÖhy¬º€û„€âèlşı{ıŞ§ò7ª¸Ôúòîc‹\Zı­—8D~{œö3k¶2§›A©Å´Õ¾¦ŸÑ‡0N×nw»ùëê\'lo²†š¥Ëcìµ¾¥~œı7CD\'óUÛ¸Rñ/pºû¹ÿ\0	·÷¿áLŒçİm\"–†1¬-ŠØ×AİmvzõĞî\"Ç‹Ù|ì` 4HüÖ¥Rª\"•¥Ú°œYMÍ¸ìªCçÀÿ\07^íÎõ^Íşš6ÿ\0Ö\\æîmd@¥‚@›ê;élj­sî±û¦ÀÜùÛ%ÌœzÁ¢=Æ&6‚HöûvûÛZUùªÑ>Êì´PÇTãôƒ@\'ù[~’oGõŠëmö\\x;Ah?I­s»ó–¥G¤¥¾“`‡\ZõqèØâ­Uqu…õ<³\Z©\"H1Í³gş\nœbtõZÀj\rÒA‚f?•»{êE®§ÕnÒ^}65 û£÷·oõÔ2:…×P9î`†ó0wmw?GÚ¤[›·Õ°Ø[+` í#ù÷:>ŠŒÌíbCºà:Õ5y/­ƒBC\\x …e¶¿!™Y`qvæ¸=Íoî¨\n«~S-cöHœÑt}7=0sÚMncZöÎâF¤Îr`…K_¢ã-\r‹Şñn®‡W\\€×1Û~›¿›ÿ\0ƒB5ºšˆ.siiq-ĞIk\ZÏ¥íDfW§“]61ªÆíÍºû-İşÌÆÈi¶¹®¢Ç‡Ô÷nãóéú;7\'ˆz½$Pé²ÛÓPçåc\ZØ}?{N¯p—~“½ÿ\0àÕ6\0ã&>\Z«}?)õTAuNĞ´ö1íÛıdAŒ6€[:´¼‰vşó‡J>F)¾íUU]£ë€XAv¼Šá]Wz–°™,$ËÉp#c›[‹K^Òçs5ıäQ}q{FÛÌÏ·r”UÑcjìœ;,\rÔp÷‰üšZÏÜØßÒ*–ÛëÔXãµ’L?{vßÎMkqİ\\5û,iØKLÇù›”²¡L™ŸİwõRª*õbæ1¯ä¹œºx×÷v%øm´ÔáísœŸÌ\rõ=ooæl¨ÖbÛOé¦íCÉpùLwæ+Xy¶³TÒË+lvûÄÛ;-§Ùü´Ò,éZ…ÃFfÚìk[}›^FÖ@ö¸æş—º¿í©8V,¬«Ç´í~ó’‡sÉª¶U-h™ƒ:Ÿw»õ“VæVÇ6Ç—µº¹š‰\'÷œ\nAX×mVKDoÂ$é·ÓVwZ×=¶{kxÜâH&#ùïsş–ä\0Ú\Z7q:ƒ\'úÛqr=q1;4‘/ú?Oè9©/canæ¸¸ÃàKşC~ïb&]»Mı!-³l8·èW½ÌıÏ ƒvE¢¢æ>Œ8ÎÓ:únÜÏûúg]{Û¿Yp›3$Ïİ¿é&õOD%µ‹KÔIÔ˜:ûò`æ:Ö×Á³ñ¹Şßä9&¸>†çpdíşOï\',³oèÆÒ9Û\Z·÷›»şšG]¿$6	Úa¥ÁÛaÍ:tôÜœœ–Øÿ\0JÇîq;]\ZÏæµ»}ÿ\0ÔCõ\"\0¨ıÿ\0ÍPÜ,q,™qtƒô¶íN#ºWä~®ãe\'\"§–ú¯>ÂÂæXëYş®şGójx9a«\ZÃc§{n\r·}l\rw¡[Ùé?ÿ\0‚6³hÈÉÃ¦ËíİÍg¹›áïg©îı\Z3¯¹Ãx—ÆÖ¾ºÃ\ZæÈ,mï¯oç(¥Bğ]fş”×SjÇ×w¦âZ\0ÿ\0ê7}_Gù•,Ü¯³7Ğïp>£æ´~wèªŞŠl¿*Š]]úïõE{Òs7ş­ş¢ú{=Ms)Ü\r§_cY·óáQƒhšúÍ­“¼î’cn ù·Ú¤ÖÚË€±âš½îÃı&ÇV7ı?ğH§†¸1ûœÈ.‚×6?Áşúf>Ê\r¿£6Pöè\"?¶öû6!>E\0/écÙ[ÏulpùÓúOÑÿ\0:íÌüõ?^ú.–9®­¾ÒéÚ1íõ#ôûÙş“ÿ\0IV–kÄ.nÀNépúO®ûk,õ>—ú52şšè­Ø¦§·İ°=Å¯ş¶ç:ÄÍÉ°‚‹põ*ï÷Ğ7d1²Ğ[Ysw}\ZêÛ_­µßñvªv:Ç±––TûôÙØíA¶æz,sH÷»şÔşmÚp›úL{YNA–Š!Îlî›^ÏÑ1ÿ\0ğÈ9tÙ]{3*÷?p¤`úVÑ_Ğõşşô¨\0Ñ)%ö½ş¸Ø×êk{Á§çµ´»Ûé¨ÙXÇ‰½®-\\ç4Xüoóß×³ôjAï£×]eb×»e~Ñc_KĞöşûÿ\0>Ïæÿ\0ëŠ­9¹ ¶\ZçH\rwÏ©Şßßú	%Fƒi·İé±¬¾Htw\0ÿ\0-Ígõ6*×8ï`t¿´—à>ïä~b•&¿Ò1„ZµÀn7o¦æÖ”‹^2\rmq³iC¿ÁØÇÜ±éàé¢ÒÅö½ÕPç¾ÂŞ`óù››üÛ¿ãPƒkĞ4’¿D´şxt«_¤ÉqÄ0Rçÿ\06Øïıß¤„Fâê¬m¯àX= +ÓJ«òTµW°=Óï40ò·!Öææ8†÷İûz&\rs£@&4ƒ¼—¦×íÿ\0ï»Sú-fæ±„:±¨9>†ïüÍEõàÿ\0`yÕÇF‡~ûş—ç)ÛamuÃ½º:Oóé{Ğ\\ıNƒ¾š ¤¯u•\\j¾²íx\Z?_Îeşq]¤]^öÒÔ­å¡®?ğSôrjm%œ‰k§Îk7£C6a.Ô‡\rÀHü×!-·HİĞû+Iklõ\ZYî`±³íÛ_èîİZL¤5ÀR*nàM¯P¬ÛÛOş{ÿ\0‚\\Ü§1Øïk\\6Û`ş	şïWş¹úEcì™/Ì©Ôƒ¾Öµ®¬–ŸwÓw¶Û?Ñÿ\0¥L’ğ“Ñ©–CZÀÇA³{†Ó\röíÌs¥eô:».€âxi ™—ok7³ßôözˆlº«¬Ç°>–íıoæLûıŞÿ\0ı(ƒf<1ûƒ€€[¶%¤èæ»İşˆŠê‚§ı¬1¬n©Ïxaš{œßOÚêÿ\0ÌCg¨òjáäÅnA?¸éMI.©ôÌ¼5ÚègÜç{«ß[>š2\\Íõ…ÚÁtnÿ\0‹ŸzQQn}›%¯­Äî°8¡ÚoĞ­Û«¾›ÜáK„n†şd4û±›Ş÷»ùj7YX¬³Ó‰ú:4åîİìUê±õÙ¸6Í7‘‘\rÜ]ûèmW¢PÛœúê{PhÓQ¸}=ˆ¬Şû\\Ó:fo¥ùÊºÇÛ½ï~íåú—:Z}6nßşz%íµ˜äíÚó>ĞK‹Gø:¶·w½éx©Uc×`xµÛnw¸¹¢v†QŞÏÏú?àĞİ×4XıõØC†ßiÑ‡è¦nUÔØĞö9úşwoí}ï-¶öìkµ¨İô]·è¥BôU­’iu{+~ğA–íh\0û[ùÈ¶èĞ~ˆ†¶<¿œÓ÷“äº&Ş[\"Ş”ZK®mlÙ&ÈÚä{ÿ\0ïèĞ½wcíÛ%°4ş¤\"gUSj~ó²²æaÄ5Îfïë;è\'hõm0ĞÆƒ¤Ì~öß£ı…;ªX÷\0^ã¾5öı\Z¬÷~{ ‘¢…ukcã[kƒh¬¼%ÄvûŸÂÔ³Ò¥ûZæ¸ÆíC‹g]¾ÍŞÖ*¸ÓèXÏSoº$™ˆşmŒÿ\0È+˜ÏÅ©®ªßu–mĞhgôÿ\0£¹­ı\'¦€ÓT¹õY`­ºjNâ`GıõI”n49¤ºââÍÚGîÙıWíS­ífE–:Ê÷o.¼í›YíöŞÕ\\äYû-3½ÎvîÍó[şkQâÖ‘MÖ²ìoĞÙIk«æ¸’	?á(½®İ^ÿ\0ôu£UuyRêõXÓ]\ri±·}Óa•Ø–-î½Í¨¸µãÜĞ4dşw§ş×ÿ\0„ÿ\0jç}æ··ôÍ3\r£GçTæúoªÏø¿Ğİÿ\0£ÕxÙµn^-AÕd0·p\rqiëú/Ìg©éÿ\0\"Ä·P\Zú‹™ctÚ[¿wü&Û2ô,ÿ\0¨œƒYôÆı}¡óíŸ¦Ïwæ{~‚±¶‡C7´–îpú?I©PñU©—]öw;OSÚ4nïFöl{¶ŸüÒg=Ô´¶<¶LÉv–ı#ûÿ\0¾™ÖTY!h>Ğèl7é{¶×şb•wÛ[Øç¶È‚ ‘#÷İ¹µ¤4QSYmc~Ç;±cãcÓù½ŞäÂÜšİmfêˆ¯s^$ßJ¿£üŠßéªÖÙ’ns·Îw3¯»Ş}É`Y”×ßoªá]gİÉkÉú5YşŠ½›ÿ\0ë‰—©´Û®Ç¾ªlm¶È-s··|Ç«c6ş–¤w[UU;Ô®¦Ğ	\rÚÒK»zÖ>ŸÏ;Óÿ\0‰Bv[±ÛX©åÖM†\ZÆ“ô½+wÙbµŒ¨’.·]ÛkÈ?Kôm·bİ6’«±î.ci²Û›­aÖL‘ïsı¾®ÿ\0ô¤AvC/¶\n˜ylûÖ;é?Ùùõ¢Õ²¦–¶ákÆÀÇÒí¿ÍºÇ¿oóŸğiœ(ûO©`Ò­5é2=ÏØïÏs?=8nŠuUµ“a.Şİ´»t†m¶6ç}èqê6òĞiØH:Á÷µìoóÿ\0­¨ì¹…í÷h{iØ+ôë>Ïk*F±–ŞÂ-æÀiú%Ì;¶û~ƒ}?å¡İ(Èµ×úµêàî€İsïúÍŠ¹µòáô[ 4#…<—ãÙ÷1ÕŸ¦=ßàÛíö¹¦ÚâKå®î:ÏòáôBÿĞÁ²²,#huDş{?ª E¶qÚµ®=¿{wç!»Õk·-ÔuòÛû›S5î`‰Üó\Zåh^µİª¬c`|Ò?{úû“4éôâ4Ú@€â™Ï.—8!1{@ÚÀ\'¼òS¶Z™µ=µÉĞØ·ó\\×µ+šİ ¶¯<Â‹Ÿ1©†p1üÀvñswoŸÒö¥Óº˜ÛQ\r`y.Ğ_û)VlmaŒ€øw‡ï6Q¬ps6í.?¼HˆşSUwXã¤Nšı¡(€l$Ñ\ríp÷^ù’îÒ>\n×LÎ´=ì´Öêí>æÚ	Ç±¾ß}Lÿ\0‰TŸfçlåÀÄ4èN\0{æ;sÚ%Àk\Zı~ã”cˆÏÓõÕq¡]¬lŒ6ã2‡å3Ò][èõ	\'èä·Òõ?Gÿ\0	zM´‘fc.t‡´¹òßğŸ¤ÉkXßjÇma€’£¼ıªşzXÒÒ¾Pœ\"G^¨°]+,ØÃú\Z?¥sÃƒÚ}ßà›]_úQW{ªxdîc·€‚yú^×(½Ùj^çÚXa £}©Tki¬\\ÒvÌíd\r6£R­mVú—5ï ®ÙûŸš„ûãé°zbIpl8»ş5ÿ\0Kó‘œæL¸ö.óømŞ«Ùhl`-#Yâ?¬Œ¼ú 3`‚w8İLÏaä«ß”]\"¯h#kœİ7ô™ıUe¤4¬¼¿@A€úSûèàYm¦¶À2\'I\riÿ\0oî!>\"(mÕQ¡«Z¢šÇIlütüïjĞ¦Ë6N÷5Ï%¤¥´şfïİVßN+›V+}[I-õ—FıßºÆ=Q¹ö=Ï%ãßM#ów7ó.f‡EÜw Ñ&<·%µ5Ş˜¬¶]0é\'ØİCÑ³rÛvKŸïQ®Üµk€>Û=ßá}ßédÜ]iŞëç]?{ó•ë˜ÖUKEƒtº\0†o\rsš?;è}?ğˆÆ{p¦èy£s^ú‹C÷¶Ö–ƒg?É³ø?¢¨5¹U0†µá¯®\"HÚ~—òV‹©2-éïkÃe¦³Ì7İfÔÀ¶Ê{v³d‚Öë±Ñì{÷)=»ğ#ëÅœNCK˜ıÃCÙiã[@Ç68‡\\æîtÄëíôĞØÚoÅÑ­HlÄßKr«e¤ØwLÃÔ=Q!q©ht-Ë=\'ÖÃ»ßûšÀóö©û´é¶ ˜‚?q–±À8i¶$€5ÿ\0¤Šç´ØGÑ’N±§òT ‚7YÕ…ÃÓÌa58A3\"AC¹æİ¡Úí£Æ9üäZ¯p ’É?¼Ç]6cË+İQÑÜÏæÎ¿œ†œ$ş	ê:ÁsK‹tiñúIØÛ\ZıÎÕ­%£ıBv“a>á\0>h­c¯QÇB×´?ò(nVÄ§õK\\×Ü9ş¯î(8¶Á,ö†éS½µ5aÁ…í×€ÉÍNMaÇ‚@Ò;şó´Úœ†-h£çI?EM§B_¤D÷úH{·±Ğ\ZtˆƒûŞÔ_gé«¹ÄH0cHÜ‡T¦ı%u1îúÛÁ/ÜÓ\n»C€c¶û6‘ÜÎMg«æÍ ;iûª_«s{‰qá­×ÛË]¿÷)cQ´Uê\nåÀ½Ä¯±Ÿğ[ÕÊ±³«ykXÍÃ—‡1ÕÁıï~ú÷~ú®á]Nk­«Ô¬IpiGı&úÌEÇ=ÔYe“%€45ŸÍ¶ıÖ9ÿ\0šlHQHªÕ!¤‚KßWF<Cƒ`ımïóÑ²qšÆSP¥ÍnæØ]Ì{wmşoßıt¬~=)u4‹=ÓX\"İ¿÷ïçlc©¹qkÚÖºI˜q2~İÿ\0ú-è‚IíH ~¼œú7º¶ÒçY¡Š¸}/ Ã»İùèf®¡Ôê8\Zâ\0d¦ïÍo·ş¹b¬jIµ›xİ ş¢›(¹Øõş’¿t°Htıo±¿Ké¤b,l MuL0¾Ïg¨\\w49ÁÍ-ßOè»w½5lcîqkYáÆYºccŸ»Ùÿ\0ÿ\0úâ\rb°ßSè·i¬ı!™öúŸCşÜM‘o¦Óè·ÓsõãŞ?§îH~J-ÁMŸIí©î¬ÆÀàF§Ûík²ªÓ^âÆ˜sA»ıß¾³C¬€İÎ’Aiÿ\0œ®âzô—é\"I?½ÿ\0Fï¢™aÛïÚë¨È;	 }\r»vYµAøÙ-q9å„Ï²I#é}û•ˆ¤ÃìaŞî\\`“Ûİé·w»è1@\nlxt\ZmŸcÁ€ÓôZ×æ¡µ”µxn{ª5\ZÚ÷ú;Sï\r›ş\'6ÖÖ]ušK¶0µÓY¿›~ßgş{CõòŸ{è²ëkìıî†îCnÇ&ı5›«}Ìs„h\\7û¬wÑÿ\0·,M$®-”úÎ{ß“SÚ$¸ìö~»6¨YMuÒÓ¹ÖI’ 49¿›»sİ¹\"«	#ÜİK73Á¬±Ïßµ®ÿ\0Hª6ËSÚÆ04m$sº77ó½ÿ\0öÒv»šËhôı=Ö¶H¤ı7?h÷~j•Fq¬p4ƒ½‘\'Úçíwé}=ª°¶ú_é{š&vÇÓfæşúô9äÁi’ˆ>ä”Ë Üÿ\0asœÆ\rµ’@üİÊ\r\r\Z>K¾0\'û\n6\rÖ’×¼’6væ‘\ZhFš\'ÇËe¥$·Q\Z—jg€Şén¬¾\Z\0&DN¾(Mö´dù©À’ZÙÌ¹tZR²,¶ûd£gùH.€$F³¯î¤f ƒ;J}æ=º˜‘òì’VeïµÍ®ÂàÃ\rÃó}ÍR{ŸË‡·l´\"Ö}/¦œd‚eû“¶uŸäÚä®÷·Øà`»àwíL?*á»`c»&—\ZÀŞÁ¹Ûİ´;?âşŸ½Hcåµ»^êË7°‚#ó«s-öÿ\0†şu½µûƒy?KÜDwüíÊÍVYU~ê†ÙéşŒk>çÿ\0Y\"5µ.Ê³=/s×5ÒCKH!ÿ\0Kgó×£[éí÷¶ßL{ãÜßÒXİµ!VÚì;CÚÇºKØ÷Hû¶7Ù¹8¬º·ºğÖ2\ZòïÍ#èúŒÿ\0„ıÿ\0ôˆ]R\ZÔ1¨5ÁÇÔÕÎŞ’~ƒ×(†TÃ¹Üì®?²×Xÿ\0ÜG¡î«®l@¥Ãş“T-sÁ!“©\0\'üßİF#@‚wYÂ»CÃ\\k :Náÿ\0ç£ÿ\0Eÿ\0m¢9†Ú÷»BHÎşHÚæm@µÅÎemx÷º~ˆ\Z7İ»ûDnFúõ\"í’\\ùƒ#ôm¶£@IW¢Vl¦¦»q\0ÆfgÛ³ùh7Z+õ¨èYÎ¤ÇúìLæ:Æ µÍ™’#ú¬wøD1\r`a ¼è=Ü{ûÿ\0õ´ÓÕ*}u¹ÛŞ×¦Şü­÷±J¶º^\0s¶íö‰Yv×iõWŸP¸ØF›â	?›,ıÕ\0Ò=f×©/\Zí ÀüíÛ¿y*tZŸmµ9„O‘İ–ŠL@}dOƒNïê¡c\Zè¶	oˆ?šw;è&x/^Çiôc·ï7oòS«[­QõH\rNÊÚYùä˜lÈrL­ÕXçÆ$·iuìúJ5Ø*i5ËœZñø7éÜL-Û³x¢tPßä¥ö)g{­`§ÚN¢OçíşR9v;©sÙg§.k°4ı/nÛk´5İú7i\Zİ·üÅ:ØñXqô÷U sKÿ\0Âc]·bh‘ºø¬¯(zN-kØĞúÆİ†Ù\'ô¦ÿ\0_ø5\\1ûÀe{ ÚÉ×“ï©şèÔg_^A,{ƒZİ®Şœï¥ùŞš²ìÚ­?¤\r}\0‹ÜÒ×±ß˜Ç4mwµŸëé¦\'BÒe^¡²–vÒe±gé,úHc:æ¾º‡½¾Ò±¯l4G²ÏwöÖƒÆ÷{q¬p#m`¹®G¹´¸µÕWıKllM ÙFMV–;yªá}uKş\r	nšI{ƒ›\\	h\0Á&vúnömJËœğğÖ7lÖ}ãú?Oİ_ÒSp67s\rUôÜÂt3\rk›½û,cAhsçè“©ŸoóDl¦¨È}–c‚!¬ú\0™ \0ïcÜàÛ~Ÿï«°»cHãó§÷ãóz+\r°<×;htƒÇÓfÅlÙ±maÂÏÎ-:j~›ÚßŞ@\r\nŠ#Xôİê\ZÌ:	äŞ›§÷¿3ı\ZY,ı9q.Úãê6Éh&Eµ•{6ÿ\0¤J€I 8\rv‚Cß¹\n·V÷Ú+Ç> ¸É’ïÑµ\"6ú«»af]áÖ¶¦¸:`&Oïz¿C{›ßé«ln#i¦×¶ÆA-s´Çfíïú?ğU¿ôj§¤lµ¾³\0cZ\ZgÚ	“³ó½?c}ş¢±K*&E•·hÛcˆÔîıèmÛgóOş\r%52,kÈÔjé\rq]§ò=Ûÿ\0Ò\"¶‹^İíp»ihä˜ú\rAËÅ¥·±{Ácˆv…Ü<Í®k=ÿ\0ËE®ó°“]m1Qiİ0>­¾ûlıû—òµ(ºÊïo®ĞX7tGµÎô]ş»ıê\r½äXÆ4I\0º	;XÍw8›Ó¥îsX×z–ŸpwÑö³ÙìN\Z)ØÛ\0q†‘`;FĞ2ÇşëôØ—ñRƒˆ¬zU}€¹ÏÚ×\0>æÿ\0ƒú)ªÈ/|¸Ãˆp:ûÏÑëú7\'-1ö²@ÜK;ƒXÓ·oı/ÏQ¡®ci±»àÌm>½?êş\nÿÑÁÊç;tµºëÌ»Ÿë*\r­…ÁÀ;³~“áµYs‹´d‰=€\'ó”-ÇÇ –Ù“ÿ\02+@‚vj7GU²´@ƒà§m07nÃP\'ø nĞØq&5åööÀÔöì>š—‚×DÌ‡ÜGşI;¶4Ë¥»NçUVŞF“ÙH5Å±¾\"d4oJ­UIwÓ©î~GsJhxii\07PDëÌ¨¶·l/l>ïó¿1=®\"§zç2$Lè~õ’Yì£ºM•—9®’% ê•ıå\næ<âgá\'Û¹ÊU5ù ¸7X„,ppà•m!¤A]{ƒ¹\r“‘¹‚«YËH^ßE	ÛAi{c^$ŞF¦º² 0ìl†ğ5üä7Ô@pAƒ=Š‚EßÛÙz•–—:xÑ;Ëh$ÄiôS²Í¢§Ä“Ûú¿E©÷K@Ã:iÊÑJ6àío?ÖN×n~ç‘°G¶Oæû1®¹Í1<“ÿ\0KT6Mí‚C‰:´\Z}¿œº	êØ‡6	öµÜ:9şªC,õ1Çcş™l½W±Ä²Ã>è€Lî‰ÛíUÍq¡v§²G)\Z\0¡İ²×“g©¸›·jO)ìª×Ù’~(2aÓ±¿œO(¸Ì»*ï³ÔââíLŸl7Üç¿û?A0Ë@(úÏCó$\rnşVæØî\0NœiLƒ\0\0¼\'_Îr´ö°ŒÑ±Í‡ÿ\0í7ùÏê*îp\rÍ èG÷„8wÕ{2e–Õ«2\\æ·AwÒP»(ìô€Ùs@µŸÍ›ëˆfİãi÷ÃıJfÔh­ŸHè\Zïä½#\"tuİrâÖxáØ£ÅŒy{Z\Zy İùŞÍÛ®¦\ZDˆd‰’>’j+vÉ$ÀKÔ%U¡\nÒ¯Å¦Ñ p$q«otDêİ°áÄJoAâ€“:ÒHV‹Şál’!Ò=ÍNŒL~¨&ÒTìglh.i\0çSôš«çÔæàÍo:å\'ã?M¥¤ ‡{€ÿ\0„şJ•®¹Ìklpô™®±úß¾‘Ö&$VŸ0PĞ‚\n\Z4aKO½;¶µÀµÃ|L~wµM¥œ5Í$óù§OÍÚ¤\ZÖ»èxc‰–ˆV€‰½u]çBı²] Û¬~kÒ»Ó–ËÜ;w¹/SÓs´ÚİºÃTí³BƒĞD§èV°×Bí\ZHÓˆşRuµïÚ¸j–‰úbyğş·õTö\\`Á-xÿ\0¥íCºXÜ\rÚ$$ƒôší}®j‹ZÏ\\—?kÒĞ¿æşj0p{	s·ZÎñ©ºÿ\0å¨¼¹ä€±#Mßæı\'û>ši	oàáå<XkÈm­Jé-³s—MÏÇ{²l·\ZçÑ©÷m­®p#éVÿ\0nÏújµ,­¡ =Ì.üÂ{ŸÍö©š…bkp!„º@ş^×}-ˆQÒõM†&Êlvë\\û#Y`kCd{™²Ä×9¯!´ÖEu¬. ìÿ\0„zY½Îsİ½Ó¸Gºu÷O¹©murç€ÒI\rgó›¹!Ñ=n­•Ãvú‚gpnØÚæñÿ\0Mí5¶š]sŸIeDT\Z\'ó+nÏQVÆ¥ö8nÓ£çM>—¹\'¾°ãM­sËš8Óó«ıÄd…@¶²3NC˜âë*`´KÀ\'÷ÕCkí­Íı/b~Ÿ§ô“9µµÌv9/qEâ‡îoÜ¤Úİ\\¿Ñ{HwµÕÏow»é¡Ò’½mª\Z6ÿ\0(˜wùŸÊRôœw†\\Z È?•ÿ\0SÉ¥÷qé’L=²]»èûö»èa3cës\\ÂâÓ¨w`?qÛ˜äl]R).-64ÚZY´WÏ÷¹+r«{6°z{u™ìïô–Y¶Ïsn ‚îì\Z»»şüœ?Ô¬¹Ím†v»S?Èk7íôÜ•~)IFCœĞYtCK@:ı/O`Av3ßfã¦âÃ£›ıôßÜEõñÛ\rkÈ¶¾×~{\ZÌ}›˜Ïõ­í¶‘ú;\Zk.kK,òÖû›úOOw³ÔM&‚@MS›[Ëè¹Õ>I÷Ÿg?Èİı½ô¢Rù»yô’kküŠö×·sù’_Ó¬¿fHô^Òe¬•¸“íaoéë¿Ìÿ\0Á«NÇfêYn(k€şl=ÅÇ–¹öäş‘Ÿ¹ú;°tÙUZî×¾»mµ·:ĞöZ76L9 »k[eg÷ßVë^ÍK«2Ò;¶JÏìı4vÛ¼zwÜ×‡\0)p;Ë}k]ú/íÿ\0àŠ.ÒĞÖ0º—º\rîhÍıêw{ş—çïFôÔ¢šá®eÒ2`è?š¦ıÛ[´€ŞCâ}¿œ×î÷{nysƒí’ç‘?›»oòT\\67CÛ²}èk¢Ú×T¬ÜAsí jéûÛS·Öw{›&#RKdƒçşÔB}F‚d<@?ùšp!^¤\Z#Ç¼¨¬ò$´;˜á0ÑÒH\'¸ìŠmhİ´Ìr?é$ÂòøyĞêİ%ºxşêWCKv€x…-vÒØ$†õ™.%pêÚcl²¹\roç8Fæ~îíßIÜŸÑ©Äº^Ã\r\r#øŸô›¿Ñ½…Í÷îÓHóT½Zİ;4a\"D£éÖÖıŠ‚íİU³»i÷´C\\7{”·ØA-øsaÇp~›·?wõ¾ĞÇ%®y‚_Ã§óZç;ùÔ+î²¢Ğ\ZN¤ıXÜÔ%ò”Û-~+œZ÷:“¦¿Lå~öÏü7¦%ÃGµ„ØÃ¡Éß·ÒüÍèu´9Î6°¤û?«ô~’vd\Z^×’Ò8\0Gîúnö#Z*×RÒÖİ[-o.ØIxó}]ÉÚ*­±[Zêİ%üîwoÚ=Ÿ“ÚÛœÆí$ğÑ´o»İêÁ¡‹¦I\Zwâ<õR@úåÎk6ñ¿i–ëô¶ÿ\0/ı\Z.5£kíİ«£ó¿›Qİ¹¾Ò$v#ú¬wõSµÅ¾ãÈîbuø}€-†Q´¸ê\'óêv>?êĞÄ€ãÃwuºı¤7ºÇA’8×ƒÏÑDÇ‡±ş£dï\'S¤ûw§u®Œ]xÜA\0iÛ‡ìIûÚ`¸ó:è`)mÇkˆk^æÈ7ş¥Ç\0ÿ\0Ò:Ow\r\n6z¢ƒcÔsö¸³İ#ù>íÊ75Í$}\rº5ƒÎÄİ±²Ö’ÎI]=¿Gó~’UÚë_±¿R9÷¾’V.•­[a¬cÚÇ—DLíK÷6·è¦â²H&ºÿ\08È\0¸şb\r€{İğÑL[h1ênk£G{‡ö˜äTÍ¶1ì/s€ˆÜc#ßïşqêÀÈÃfÇXÆİ°€ıcÀÿ\0Cîgş{A\0‡‹Iq†m˜.üÛ)¯ó?¨§a¨{]µÏ>×À¡ì­û“+ñ]‚ö»!õ³ÓœÇ€í=”µÿ\0ÔşmEŒ¹·3ìí\r®÷‡8kjoµõ~úoµãM•\\ {†£÷œÍÏä1Š,²ËÉ{éÈv²#ùo¨ÄÚ	¶wœ¦½€?ÔKF„j~“ÜßğˆvN’áY#ÜK¿µ¶SâÍĞÖğyúMr\\ê\\}HpÔ{\01üRCØë2¿Fd–Ì%ÇwµZ\rck@qöšÚwOïn°5W`¬å™;Z‘¤O-ÕMÅå£höÁæ5\04\'Å\'pÈTøÀòí9­z+šmç\0é.?›£·»j[†Ñ°m˜İX\">œÇóˆu\\A¸†‚IIğÔö£¥…wnİK\Zùh[u€ú]Ê\rxcå‚MNùßGü?Â{šÓi/$Lnú5:®°8°;]b	\"?“»óÒ¤ZbnÈp£Ã›ºÂ	dîunİùÿ\0ØPhØòğ#€Â#ÿ\0&„×Ø×ú–4<Ç..lëûE·o~â#é°˜-#W1Í‡ú•şâ *Ø—¾»gs€îDÿ\0„wç}ÌVE”z›”æ‡¼¹Îk§ƒ±´¹¶5¿«¿ÙùˆXµ½Ş­µ€öØ\'ÕÜ\0d}½–ı7)ı’Ë\Z7Öñ;˜´{˜í7lÿ\0[z.ÕW;*ÖÍgmnŸ£;‹îvßğ›¿›ı\"`*~ç4MO;n©Í’ÓígÆ±ÿ\0áİöŠı­\"ºˆZÎ@Ş¥_Ôÿ\0Iş×Ù`uŒö‡l.ó¬ôıÿ\0¶Ò¤ÛÿÒæ½îC	L5D†¹Ó¹ÎŸŞçßoıB•–4[²¸ŞHÔ?wõT™.yÔ5:@¼åz…Ó[£Ç´‰†ö<ënR‡Ã@\'şú•„\0Ú#ÄŸŞr\\Kõ:¤§t¯¬Î×8.-ïÿ\0‘Rv”äü(mæ\"B‹éì3.2A\Z‚\'„‰ mJ±%’CcC ÕBÇâgä¢î|n\rß´ÿ\0+÷“4T÷xtıä+]4MıXU}”z›k´‚ ëòòóáİ=¯k½¬ÑßMSã€Ùv’tƒçû©–I¿Bz]z™z–Vú[½à{c÷gÜÄ[ìõ%û¶AÓC¯òP´ÜÏ1..hĞÏÍ>èw¤VŞÁt\0	tÁOö“Ùì¸Ã‘İr¬ö?\"‰ö¼¹³ Ğ‰\Z}Ulk}§sf{(ıÔÌ{‹Ã¹\rOÚZ$\0™û¿’„ØSğ@Ÿ—Í]Ò–ûA% ÷\n¸¥åÚˆ€tG–ògÏ¸Py®fIÓ¾¨L¿ED–$5Ó¹Å£°øÿ\0Õ\"²ÇS&³é’4-çù;•g¸¹ãhÒt%NÇ8\0iÓXMY=º¦,¬»Õ{Í[@.:ÉéÒG°û·’µ¥îòpúMr¤ÇM€i¶uí§ç+67k\ZæNÛkÉƒµ(xÚ¤*‘<1¯ÃéGıõ*Ëì´úz’é?ÉvÔ;Xöx<Å@Èä&™vó]B·u=Ş›™a÷nÒ\ZLïcş’®×:°aØb\ZÎÀWè ‹Æˆ2ÇA{;hT	÷ûIs{|òSÌöZ#»y¹qnÑ\0÷€>ùÈY!ä—0{YÉïîå* \0çÃ@&;öwç):İŒ!­Şá$j \r»=éäØÔ­\ZQy¡í}`èİ®à—~wõ³&\Zæ}â\0Ò ŸİA{Øæƒ°´tNwÙX\rsZOœHåßæ¦ñhcwµRêÔšö€×Oœ—1Ûe¤;]DıT2ãÑ:ø¢Ö@úPÑÂ?òHj|“{_í ™¦¼{›µ>ÙhƒÎšˆLĞZ`½ÆÓ\ZşöŠAÄ‘´hA‘:’¤ºÒµ€VC$H#lrœX}ÕË›xçıÖµĞIá±¤şò­÷m‰ÒR;•\r“>Ûl°ˆÄÿ\0WiL®Ââ$È\"ÿ\0¾”/P¸\rÇÜ¡îgóT½ÛLCšë&ƒ~=RE$2ç‚FÒ>‘ü>ÑCsZÓ´Æ{Iıß§ìS™Ó@yHN\0â7÷D‹Õ\0 \r-$€\'—:?ÍsRX´º\ZG0?’Šã¿RG3D-7<£\'ò&ÕU&Û²¯Ív×;ÚàZGóùš›	À8î3¦ŸÚÿ\0 ¢at0¡:ä«\rÎt±ísÖ§·vÄ‚vDÆW¶Y.0\\gFÏ‡¦ßûú³ˆXçí{+vn¿½¿ù½êÆ€nğd’$kû»vûT\\æ´¹Ä—?A©ÿ\0œœU¢ÛFáLŠì;†®\0koÑ@®íÀÒZ-d“ê»XŸÏfáìÿ\0‹CÜâÒÒ~\0 ÿ\0Òj$´Ì43	Ø ×½qm“³è\"GÑşZBöØªÍí\Zº?üæşú¯KôÉ\"@º‹¾šÚ\0~­îĞ	“îö9\0t²£ºZÙd·Ómo\\Cš[`ÿ\0‚cÛùÿ\0õÔÎ¬s©eeî–±$hvşóĞvWgĞ²¹ÄÍ2]ôô·§cíkÚÛôˆ—kÇî¦õ]Ñ{¡ŞË\ZöÚGĞ;_wÒÜå_Ó°¹Î#h&’@ÍÜ[ê‡¶È%£İÜ@ıİªL±õ¾C©?M 	-üÉk¾’&:ŞşH¾›#inÖ€À3Ì³èÔı¾“`°^Ó>ı®w¿eŸáÏØGÑn’ĞÖíÿ\07uÙØ˜úM»w´Ò_Ìÿ\0\"º¿ÌGp«YMFÍívòÙĞî\"}ÖÜÿ\0	üâØKÆ\r.ğ©ú	.ŞÖ4s\r×ômÕEÏqx/Ö5Èô>%B]¥ $í‚&a²íw ¸B›`h.íâ˜´{šÑ¼0óÿ\0I8ô(\\¹š‰™æ{©Ò6ñÜë?Új­lpcÚ\'‡IvÓ„­T³·4é¦°GqßÜ‰]€8¼ZkOÑ÷$ë\Zé$ÜÉ ÏBm.|€wî İ`Ä{¿q0î+Uİl}vI2ÇpO—ï)Ø\0i-‹5 WÜåV¶=ºI§’5{àÙ»Ü·HƒÆç\'‚JÔoûMŞĞ \0à4wö›ô”Ë¬s\\eÑ¬m÷~‘k„<~ŠÆë¹5ı÷lNoº·VİŞşK@#ü.ÄÙ­Ş«…2Üæ¹Çc6´Èi¿ÍP.6h\0ez»M#¾ÆOæ9#ìi˜/w-Ô4÷işé¡ÛdšDˆ&S–¯VúKXâK>lÖw·wşANßM¡­¯qg‰ƒ¯î>íØ†^æ5»Z]\"&ãé\"×>™ô¾–ŞÀ~r@&Ñ2Î/s‹Zİ	LIwĞşZ™Ìcd4±€ÎüşwÑ÷\'\0XÁ§¶‰ƒ?¿íC}AìÔnq?IÇáôP¢‰¾ë2ÌgÉk`}Ãék.k“Öæ†8¹¤÷9»xÛûÈ4úMgèµy’\'=©m±•ˆ¦K òAÛíj[\Zéú*#ÚØm®¯mµ½Û^HÔÎ×µÌA\'Ô%ÍldÈOæû“´84À™‰R-;Œê;ßÊN¥¬,Õ¬öb8üÕ*ß[7Hsˆ‚~‰ø©‡;k´m$¡Ş$î\Zî?rnBm˜5¸mÑ¬rxÿ\0ÎTK} “Æ€ëÙP02NƒyO­‘ÃKt>i_‚©±¹ÖÔ×É`Ğ=£İ,Ûµ3m;\0Û»{Näí ±±-ÛÂ%§ù[¾›Sš«0æºOnÄ~n÷~rpÙ\nm»Ì½Î‚I.>jxşˆvİÎlÏqp:ms›µ-µ<8>·U´H±°à`ÿ\0‡gıG¦¤Û*­µ=–º· CKGé7:½Û¾šmø&¼V·µâcÜ#Ón¤Oç¿j¡Î!Æ¢Ó -iißæÿ\0;úûÑŞY¼:»ÚÒNh-3òşmW¿é\ZÜé\0—dÿ\0WÜShä·%â6í`o·ˆşOõ”œâğZ]dDoıV¨Q»í6Æ€&.#ïDsY$vÂ`ÿ\0ÒB#O©Qb™î;qÖQ¥Ş¦âïpˆ“yOÖ4n<œ£‰65áÄ:í?œÔH5t%™áŞ› ôF„\"VÆ´Îç˜@€?ğG{k/’-ÒH£ˆN@2 À0#‰DU„‚wz[§·Rï7r#…“·ÓiŸ´îkßğ” Ò,Ù¹Œ.a&\\\Z`Ÿİ–\"²» } F°LOçÿ\0U¾Ô´ ­RÔe¥ĞÃXy-}m.-Ô;oÒ«ÿ\0VF÷‚ìw4¹±íı5Şæ5­m¿ñoU1è¶YêH°?hf¦Iÿ\0	¦Ï¢äÖ:ÒÑ©24kœfÚ˜#cè¸šnØÍº‰49¤½Î¯ŸŞû?ş‹Tìô¦ºŒ?I\"=ÛõĞwCv\0\\gék§ö‘k¹ÒólÙ@\\m\ZÉú5ÕÁ½Ö¤tİCWÿÓå,dÚ\\5<;Ì”Îk›S	óÏ‰ıßê±\"d<Zï†„¦k\\\0q] ËB»×O?ùÍnš¯Uqa“íş?œ¦úæÒC‡¸pìu5`î!À9¼Ÿš%a„¤íIætFÃÄ Ê®İ´OÉNñ½à5ÒÖ\0\Z|E\"²éï¬s Ÿ5›„G˜şh]µÚ<G\Z¦õ[cL³¤\0§cHwbÑÁÔ”£è—\Z~:h›R¿Ú\"\rl’N%*Ø\\<~iî$AÔTñÆï1J`\0Ê¼Òv´¬!Î!óí r?–¤ùc›ZF‘Â¬±Ïgï\0Gıõ»Ö[\04Iş»œ¥+}Ù>a @ĞåA§u/<\04âT\\ë\Z\\N£\n{‰ ÃŒHù NÛî7J{xßù$±ÂÉ×¼9¶§İÇ\n&vúÑ£O\rÇù)HG¶ªW°ØY!ÁÒ$qÇ\n q_Ócàƒ´p>\nN˜ h\n9\\µ\ZRáCCªÀ´6¶@ü>’‹H\06 LÏÁIğ`4h5Ü™ÍvÑû±#Ä¤{öRÌnçÀãÄ+/}¬jİIñşª®Û}ÀmØrŠëÊ™ŠÎ°dò?’ŒSş\n%Ğ”4ÛÅV\0j.¤LÎPumqq¢v‘ôIÖGî©ßM˜ï,p0x1í?ÚQ¡Ş›Ú^b°ïqğ	ú2éÿ\05C¸aXkµ$ÇŞÿ\0zN†Ÿd€{wF°zVØÖcIkgÀÌUš÷HÛô¢\'¹M44ë’Fº¥f‚gtHGó¥‹68µºï\Z‚ı\\1ûµHÑOnÓ£õğ\Zÿ\0Òú‚E\ZÙDÆ^=A{Cš´>Œ»ş¥¡Û6‚AüŠ±V^ÿ\0Ñ<C\\}ÒyŸnÕ\0,-Ñ°có§üÅ!\'ˆuô•¶F‡Íˆ- z„‡ƒØêGî½èV1Å €ÑÄwÖRsjğ=Ç¾­\Z~rrá¤òÓÊi×°Hbñ-kÛ\"9ÆŠm{XC`È\ZêB“mÔ:	ĞH\"ÊÎÛ¡M²{ÿ\0è´…E+BÎwx\Z;˜ıË. ^5ø¦k\\ã\r‰\Z´ú”×ì´€4‚5\'j†5ı0K¢Y§cü¨±²Cûh] 256İíÜ-i/FÒı\0jçˆDš¨×UÜK¤´†‘¡sˆ<¡>ÂÒáÍpå¨§ÀE„\0u¡B°ûIx‰$Äkü”¥uû¿Ë÷T+Í“\\-sAh\Z’´Œ×P=ÕËœÃôO1ş¿˜€6¼~îÑ03?ÊüÕ\Z-k¨–éêwê®…-¥îl¾DêÖŸİ\'üäF\\2,ôXÍ¯\Z5­	?¼ïôŸ×B7\ZçÛY.6A€’ïì¨}©ä±¡µƒ\"³Çò¾†ßwõĞ2¢<R–VDú¦ jDÿ\0ÛMAÂ²ğ\0Ûà|GıõIöTıÎ¯ôn‰-í{_íQ¯\"òÇV]-èvºqív×\'“°[[©Íyƒ—ûÉ8´0¸±Í¸6 ê¹\"wl1Ò	wûÚ¢}Jçi™\'IÒ7ØÔÓÔ¤3£kÛas‡µ¤ûü¶³è§©ŒÜZk	&	ş¶İ¾Å}®hsÈ0`ˆéıf¦Ş\Zàû=®\ZÒCœ#é~ãÿ\0ê%z‹üU]•mÅ±^øl’ZÍC¿¶ÍªB×Xöhw	s ÿ\0ÑC²†=¾¨~İÂ[L5ú;¿óì¢êÜ	nîñô£ü×&Ù¿š	¬²âĞ4ú.ah}»ô¿í´)eê=Q\r{^èĞıßë!®¬¸pšgÿ\0sûˆ`X,ÔÌjæüP”¯§@ú\'­î­Å­–=šA=ş	êk‹ÃÈ!ÁÁ¤»Äşòp÷=ÆAqdk\Z†ñı½¨ÕİMS±òò@o´éóùÉÿ\0±jÃwıÍwú­LíH$OıùJÇ—jç—#ûê‹œái-:´\0Hÿ\0IØ÷BGŠÃ^ÅºÑPõtİ3ª#ŞL$\r\0ÿ\0¤„L7ØDDï¡è³\"wÉ`ÚóÉ	‹È(ŸóJwkpÔÆ‚€LÏ\rÒc„|”³wÀ\0õÑJ»…¬sÉÑ¤}/Á&‚L<h€îä©·ÒÚ\Zà\Zß.uşZ`\Zİ®½:^Ğ5k€öÇúßºô7¹Ìi†™\Zkí)>éÎœ|”HŸ§¦İ&d‚~	Ä‘Lƒœ@&Z{áûº#TöŒ†¹¶‰\rsŒ	GkK¾Š­6°´;V\ZxûŠAÒç:IùOùÉ¤ş}RØKà=&—iŞ¬ç}5ÁvßIÍiA!²úRÿ\0ğˆeÎôá2HÑÚëı¥:Ã\\à\Z$Ú¢uÑ%}Ş›ˆÚdwh)š\\lf°;è¥¶‹×¸€>Gõ÷ƒP}p×5£Û×´ÿ\0i8Ş¨ÓDÍkt%¶@’8?ÊÚ æÇ¸ûš~”#ùLRanÒïó¤£ù© À\r\'R`ø·\nêÁÀ°+2N…ñÄ~÷ÑÙıtôµöVNø2e¤Ì“ü´Ífç’KÎ‘¦Ò?–œ83ƒ£‰-:i1í±¿Tô\\µÍw¸ÀŞ5ú¥ CÉ$lÔí#÷”]kÉ™ÜZ5w$şó“²çN¥°Qİ8U ì­wí‰€@Ÿ2‡°îÔòé	nç¸’%­‰Q­ÅÓ\'Ì‚-Z³˜#é&sŞAAã¿—¹©z›H‡|áEû`–Ÿq ½#×UËw´m\rjHIø§e€4´êÓ©k~Š®âæÙ$·º/­:mh@˜ÿ\0¿#ÕI¦‡æ´¶4>ã©şS½©z–‹ã_±³¸şişSv‡!KœÙšBMÑÒ$8ö\ZœäˆU§õ\\Ö\r€PIĞ´È?Ê³vë.­ÎÚÆ’l€	¤ûúˆsæ\\ši\0ÿ\0äsØïSvâcp n†­k¿‘ıD(l–­Ï´¸î \0Ğ%¿ÙFqí´	ğÛcí¹ÄtlöıÍQ7ìph“ä„+‡êé*[ı‰w€ØQ;´çùh¸4İi$wiüå0Ö–ËF½¾jSÂAƒîŸíÊGæÕCbÍµ†¶[F‚ÿ\0lnzrH\0mÇüÅHIÒ\Z`{díĞüOùê\'A¤Ÿ¤be†u¾æn‡–kÄ¤ù3y\0ÛàúÆ»	2â=­ı+}ÍMUÏd¹›`ˆ!Í–º­ïB¾Ë\\òt\0¸@ş\r­üÔ$4:lS‹«sœİÀÄ4\0~kûP{™cêw ’²?7Óoèÿ\0¶š‡5”C‹ƒˆÑ €=»œ›Óc+.ªv“¹Í:s÷ÜÄ+@›ShØNÙ; ÿ\0®(Ya°0Z71€\Z¹İ»÷TƒªÙ´»é°øÿ\0!ÿ\0I¿ÔPcƒ\ZC ¸i¨÷ú[¿Á Tÿ\0ÿÔå…›À\ZÈ×‰û¡EÌxswËÓÄ?’‹pcC9˜>j!ÁÍ;‰ĞÆãîşR»[ƒÛÿ\0Bk_PË~çr]äïª,$vxâ˜=­yÛ®‘¯1\nLs‹A€Gb@ä\']ŸQLİº#RPÜö²Á=€2™Î{qâ¢Ğ\r‰ éªDí]ÔmÆv4j—n#óá?¼¢çih—5ÎvâH‰<!)ÒDm•<ËÜA¥ä“T@Ö`ÁşÊfØIpd÷\'úÊ9ÆLëªeÇÌWk±Ù»h†–¸84p:ÉúMşÊ\0†™€<$\'÷T=ĞÑ®„‚<”·6[\"Hv	æW®ÕKj™9¦^ÉÒdú¨\râÑÆ€“\nÁ \0XglOÁC¸IqĞ~T¤„9ÎÛ§` \ZçØi(Ù™¸Ìò;JÜZFÆ™Ì$\0y#Á	Ä4ìÃU)‘Å\rÀ‡I„Ù&Hˆ\0\"² æ´ê\ZOµÇQıµUÎ`GÅ^e˜ã¢Ö÷Úæ‰şvôqI¨ÕR»]íuvåÄ£Up¦—=®‹\\v´Ïwı%^Æ1—–µÛ™¡a³“¸4¯Š@MiF”@ÑÕÅ½¹Ì}nní‚\01Ûó–;§sf «Ù±ŒÚ=Íté¡•uÎs†°IÏwµ©ó&qüãşwşŠ¶5{5Ü=YsÏÜ¡± \r­˜òæ~nKkˆ–v‚$é¡JÀölİ À\0ò4şPM5[m×ÿ\0BHßvVW¦<@ĞOî¹ªh\Z5»š?;ÿ\01•Ğ] ûŒÌ÷QÚ@wòJ‹Ğ$İ$¸4‘\0şkT…›Ü	:?½Zç:¯k˜ĞâÓÁA2	 \0àÿ\0ä‚> |?J´>iw6ZıM`\'€RpkÛ!Ğb&?é{SAÜ}ÃˆıTÎ­ÚºNİ>IÆûZİ;°mDë¸m$tşJAå§ÚGˆ:~óœ§[w4R@şø +pí§Œj\0 \n]cªI\0—0HtşWõQ)µ’CAsµÑİÿ\0†Ö€è.Ô$kæ©m¥îö\"ç?’Ôıt+tOicX\rm\rqçn±ü–ªç!¡àí&`¸NŸæ§ß¸lhšbgr‹œÇè,0 2‘\'¡¥\0´\\ââ`O´“»_’.˜à€Ñ3Fæ“ùÚZÇ‡=Îk§A¯ù©i-I[y!ikeÁ²<Ïrj÷:Z%ÇÉX\r¦·¯÷şìˆ\Z~rƒn°9çp! \r»c”j«^½İ…à€6ûšÒv\rLşûÔh°ş’¶Ã„N­\0¢ïì¨\\ëœé#ˆ=¿² ş7ïªŠRıg‘^¥»SêÜÖ€æó.\"@\'ùIÅf\Zé${b\n\r.Üö<ƒF‡’ŒÆ8šÀŞ tŸ¦ßjÕaXU;Ç.:pGŞ\ndíÛlëXu—:¿Ò¶$Ñ‚~“œÏÌAô¬€ÖŸP\ZLY\";ØÈ\0“:ÿ\0ßœ“½\0Iv»É-¹ş[Ñ=	>í#Q$üÉA¡›Lká1ÿ\0¢Ağú¨4b·XàKIúRF¾?ÙE\0cÄIlú!¯üå\'5€mh\ZçŸŞÜ×(P°nCˆdøBo\ry¦í˜½®®#óŒ¸OÑÚïÜP†´k/\Z–Î°î\'sÄ>½à˜ÿ\0Å´(Á%µØ=ğv¸huÑ#guiÑoÚ$¼ítüäF±­sDÉäÇ\n,±Õ[‰À‡gú§ó¶)V!íifĞ;Ÿ‚hş_ÕI]–ÎØ}Ç¿şCrƒH™ÇÏDœ\"bqÊmÄ4ÄLn1#Dëü\nÚdlÜIw\'ıZ¥\ZFĞ\\èñˆşJçdhHü?5Ûc@\r 	ˆäğˆ:›QRPK„·ùG¼(¹ÌìâAÖ\0úNQcŞöîqˆ˜üÔˆ‚‡dë±¢*˜’âç“®ƒ@9NØ\"ª‹‹@=äê‚”´°pÑØ<x¨Æû®;${öÀ!Â8\ZLJf]¶KÛ\'‡\0xókÓHôõ.Ú5\'ƒıMÉz%± Ây>(¯NqwÑ ´‘\"5rfnÇx`Ğo<?Gb~Hñ\Zy¨µ»ıŞ<Ÿ‚Gæ\nè™»]\"CgC\ZÏò÷FåÔx\0¶ OÑLç5\06[ÙÇïr“6¹ÇÜ@äûê;éâ…ÜC €ğ]Äûš?”‰´ÙQsCˆi‡=¢#Éÿ\0àÔvµŞà}Ñ<˜äµMÖ\Zêuo.Üa y}-S«C{R;)ĞèÉÜLÎŸÉj	k;Ûü $OÒ÷]Û‹\\A¬ğu?¼‡És\\î8<\rR5ÑACÛY{NÓŞ5Q\0™pt#äCTËd:kÁÕ\04·scÜO>PĞ…\rŠ¬ip‚gòå#U.k ğÚQtm\r\0í<5ş²ƒœIñ×úÉh\r•nôÉ–´CÀ0$j}º Tæı\" €ñ†ÆØü’™¢*pçEê<8ŠîŸbKÃÇ0d1ù©›®È÷ÒgÉF×Ìí#HşR)pÜÍ‰?‚v„Ÿ8£ ú²idn0âO#ÅLK4|ò(lnÖ‰äkÊ‹‹…£n½çÉ>è½[D4%¢~h\rõë²- ƒÀ‘üªu½ûÚÒ4*bC‹@%ß¼øûó’;ƒuI0\rß%ºÕ¾_¼œµäÃf4[ÿ\0˜\"¬#kešÄ;nÙ¿÷,|Dä÷ûÒS\ZF¶‘©s GbŒ@;Œ$ÑÑµçpÜè5ÇŸİr0Ğz„@v‘\Z&Ãå	–ë:Şó#^ú!1Ím¶o`³»}®S; –Çˆÿ\0IA,xäsÿ\0Y»R;…\rŠV‡\r¯Ü6xsşfå0âv´obH0yCqkˆ—C‘ )Ü\Zİ|€S‚ÕZgqˆæïªAt6œŸÍE\"[í\"DHw\"½‡ï{[½‚Ï‰İı”Ùìº;§´Y[CjYŸä¨0àâOnAÕ¬†kg¦9sY%+vÀ ûÂIşS÷µ?%ƒZ*pxíºÈÿ\0Fÿ\0í(¹µ†™söaä{tÛíÛÿ\0|Q±ÛC}­:q®ŸìPIsAkÉÇaı¤Ò5ú$ÿÕåòšÖ¸°ı2d¡Ş™a€I½+İ«‰Ô“#äˆ}2â7OnŠîò>Mn©Í¦\"4C@w1\Z¡’\\D@ûnĞ5Ni¢#ô¶HáD{¤	ç¶’¦ùk¦9Ğ™°C§áûÈuWFÛ$nçtCqh\Z	é=áÜQ‰ NŠ)K d»3YúGNğeÜÌâ¯cVM`ns¾—}¿èØåHƒ¼´ègÜ<N”* ÒDedËîq\0éá0Œ+txüpİcÀ)5î ´ó\Z%ŞÔGd„voq:4¹¼Ÿšhi3¨Ö±û[©€?lXíV~¬ ¶\"dp˜»qğ\Z&.‡\rİøQİîéS)-@2q‰pœ¡†×RS¾LwSÑ€Nš‚iuØj‡‹\r¡¼#àµ=Z~Él5à½‚t.nİÍşJÌ.kôâ?7Ş÷TÚC¤Hí\ZBt&!uZ„H] ×vš]®¬ÇçşiæDîø)x	uİ|€t¨{¯µş€\0\\áÉ?»¢®kco{LÖà&;h}Û’ÿ\0ğjx·¹®­•NĞ	mJY­šXZw{<†;Û·ûj²MÂşiGÕÿ\0¢°R­Ñµ?Û%€yù¿ÙOSƒ©-thd„ow¢*6’Zï#£Ø•A¤ã¶FŠ!!Å§VJ5¯Dû*s\\àíZİ@àÿ\0)ÈyúN†™\Zy‚™’\0\rĞq¬«6U’ù>ïi<ˆŸB`ÕZß”‹jcÚnã A#´şr-­cİëV@cÄ¹¾õz™éÙÀ<«ÓØ#P\0iù&‹\"^ª)4H#KSÚAÜ-wë{CI¹Ú!÷¦urÀáÜÌy¨–’ ‰\"lùhE†šyãûÈîyik\\`éø¡·Sô¼OeÚîLñâ”OA¶[¸@\'àŸkHĞN² ÇÍ€4’91ÚyÚå-\Z6?‰™™\"´\"Ñ¨d\ZI~²@ÿ\0;jÃôœIÒ\ZÜ\ZZXL‚u\Zr®a°Øøøqªeƒ¦ƒşŠê#]Y4Š„–\Z÷\ruŸ½;Û¡¿BC`)·!®¬ÖàdÀóÕ¶<:-Õ  æ?58Ğ 	áğôğÿ\0}\Zë¦ªõ-HÓ”MpÚ@òÓ“ü§\"\Z]µÀ\0~Œqš àÀò55#P„®·ëIì°q Éõüå&µ¡ÚŸ¢%Ñåÿ\0T Ó´éãD¶¼´íGß	€í¥¥+-²·8í‘g2?êv©6×°híG~wî¨5Í\"	ŸA€¤ÀØå±<\'ş­<x§É›¬pİccˆ#C´Ÿ¥ô“z®Ü=N¦œYÉ=¬k\\XíÏZ\0!2ÇI\0LÇüÔI €-B™mÑ$ÆS¸†ŸİüLÿ\0e3^\ZğÀ7–ø2¢gRÓ$\rz?y+U&{kÚã#èÄOùß˜‚ó¶HqË]É ç8Cu-ÖxŸä§	€æí:L!\"‚……ñrÜÏ¥.->ÒN ŸÎgòÙş\r\ZÛÏ©64İô‰ŸŞwæ¹Ws…rĞ`“¸wŸûê´Û`;ËOú„\"xhrËÔ“®ÃDÖÒ~@\"„~k·¹;˜w6^Ô5‚]>ç³şš¬}8ysÏ#^\0ıÔFû^İDë¬8?›ôS¬ö\"Š\0n¤H:kéqÑ¤™$÷SõK×\0èÒ¬û»OµÃ€pí?)Mëİ=Xà\ZĞ5ñ>HO³ÀÌò\n{Ò\'pÔÙÈú«¥ÑKeS¡Æ®ĞÈÃsD€\"tWkˆÆJ9.tÃ¸<£Œéâ‰Ug§°zck‰÷¶´}v÷S<5Ó ï¦²™¦Àéi<K¼Á0ë·ØŸÅ°À\ZÏ¥ïŸú)ğfÿ\0P.h˜h2ã#Îƒ†òàÑ\0i¡üB“Ã²Ï&Yµ§İ©‡sÿ\0R‘s€\"HÜ{˜ \'t°‚ïa:Ààè}ŞÕ\Zöíp\'™a´‰\Zı#ù\ZÉÔ¤ĞĞ$Hq×N`¦qdñ£„OŸºƒïÜÒ\"8xÿ\0¾§uB½A¸:\Z<Ìÿ\0e3­;É!Î?šãÜŸŞN]XäK¹û¿u\'Y¸†úóÇ¹±×ìPòmzbçîaÜÇ\0L\\Óû®kPm¯iÔº‚Gˆ(8ş£C˜AÔABtİµÍ\nÛ\\×3C¹Ã°òNˆ\r( èP¹…ì†º$w£\\‰\0Ai\rİ2`+7ı&?tÏ!B·@pñwğKô‚º®q‰30	å;Xİáô£˜şJ…²A™(üä†âF³Ç\Z&“ê>IıC^,p>Óğó‘=Pê]½\ZÑÁÿ\0 …¨k3äøBrÑ´éÂCÃ±J7âĞîÇB<Ê0ÑÆ=ÚG(mn´$).3$?\"üIQOºà÷\n\rkw’d£à˜:4‚%-c$mR;è¤Ü…ªİd‘1¡ƒæ†Ãéº&LF¼a\ZàÂÂ@Õ¦\"tƒÿ\0~C\0hyH@UÑ“M‡qh’<9Ô~êb-€v’5×„Û¶¸¹²	ˆ	ß}Ä\0à	ıí£úÛR¿5\"Åk¥ÓÄ—iŞ\\ë îÓİÎÚH$’`„YAo=ÿ\0ª›”x„ËrÉ„\0e¿$:ÀÏ.\"Ãù_½¹M²@ ™íá	‡í¦~Œë¼ßr2\Z„¬íh`ÚAÛ¯‰?¼•mtËÄ–ı40SµÎ0]«F’5\"RsÄi¬“\'ÈY;E/³İìvºÏ>à†ùs˜Àí§q;Äx)ºİ¬6`şwùÊ} °m\r{A÷\r\'ùM’C7Ù$4ÚxñşRD“F´$i\rÍ€$Äë¬ëı¥7ššgpi$i¨?ô›±€«!ÂD\r­’	¿ª”€]#_\"Hú[_Qs ¼‚ÇH‚!•Ÿ£¡\0ù4\0:¤ôÿÖämçAüé•0ªZ{ÌÂºn#Yr#a3ã¬+±Üù5ÃÍgXĞàDµŞ\\\"“İÄO:ífgà§©çÇ_ŠQ&ÊˆĞ0±ÆL˜q3§&+Üè#åôJkGsô¼>)œ\\@Áù&ìdÀBAøJPcÉH7Ü\'ÇïIÂ4QWVKè­ï\Z5Ä`x¦ÜíÛ‰Ôë)£”$ª‚I$N~!H9³ Ğëà…$HûÂ™ 48|ÂKğİiœ“§<¤v†I™Ö;…	&ü|R3ıÉù*—LÎZ£\nœv›Z£gBUv˜p=Çx¢ú…ÁöY/q0×OÄ¾h=ÔÁ¶¡ï3$ğÑá¹ÈIÔ÷à\'tŸqÔ¨kºHéL’(íÒ•ÕRIã¶©À™×ÁH5ÒCG)8lwˆ<¦‘¥”Şº1cŸr#›´qñğLË@–4.ä€Ö¶ßPìlms¸ û}éğŒH }H‘7®Èª°¶ÀéÚ[«Oš=·Ö÷¶ÇæšËloƒîÿ\0]È¬ƒ\Z¾D#×ê\Z@0ZA°?ÎNëğ¶Zk£Lé!¦¼ÂV5ív×r˜Çr¡?6ÔÉÑ=Ë›cÁÚï£3?ÊR²ç0zMvæÖí†ºûOÔ·!õ†<—5šÑ6Ç!æÓ=Å¼rÙ÷ìîfå?	Œ.6;±İÊŠ;KuáÍ€G‹Oµ=Å°Lx„Zö½¬­ÚµçıÔ?æË˜áÇSev%z:ŠÔ%Ü4úÁ6âßÎ İ”3|8%H@CØù):¢‚åÄë-áªƒœ÷»DÎ%H§R?¼&Üœµ½Ï\'û)Ö³C˜=ÏÛ:À×ò(:À·ñSnÃ2F²Tá-lä&JëÓÿ\0}$Š½Y´·&fáßr¸Ÿ4PhÔ	\"{ÿ\0Y3Ã¥ÌÓ·É	‡üÔƒ« 8e·\r®wpA#Ëó¶¤Æ5á¬iÔÉqø\"×OµÏA6yäŸËJÚ–È†SKÉk„DGùÍBw¦Ó±Ìİ§3Ïò½¨›Ûì/o\ZqËT\\*6Hn j\'ı5>Z—‚Ñ§taÜO·ÄKšÏäıÕ#³±ø$ĞÓÈ0ª-N–ø©µ<V,DİÚıäï%°7LÚTš\0qı×hgëlQÚè—Dƒa\Z¤]±Ü\\Âbà|·¸–Ük ¦.ê4ãDä·p “ÌÁ#Ó^ÉP6¹ÒØ&t*Bİ­Û·i\0k@\0[Cİ;Fæ–ï“ù³ØşêZı¡Z}‹ºÂL¸™&DqşoşA).vá©p:=’í¨‰àÛuu™\Zü6†¤¶@üîé)2²L\'³F¥A£}óı5M‡mƒ´k¯0w;].=‡kXH2Ã\Zòf[·–[Ã†³ı_¤ƒ5’	:÷ )3h2ÓÈ:v‘ü”FúR\nÏx#o=æSÖDÇˆˆC-‡OŠ–òÀv$sòHHñYMiAbÂ×ŞÒ5\"M²]3\"÷R«BI’ÃØk¯ç$ö³tD»4	BÆšÚ/Z=˜î-y€<HS\r±à–ëp >ÙƒØéüAqÃ‰D}%(¾Ñİ©ósØâaÍ Èp÷÷şªé7í#q=ˆ”W—Tæ=§VÄÊ1êO§DƒMTÒ>“\\\\5Öb?wÛô’õ.à÷Õ;Úãº·jÓäáôw(¾§é¤ù÷Óèû“µ­;£N¬K^×Ggkèê5-¡ÚL@óşº}@H#ót\Z©·h†»“=Ğı”vî‘Î=Äs©r‰{ÜG¤Dq÷şjoa\"NçG¹óJZĞK\Ztÿ\0¾§Ùş_2ÚJºÙ=·7BšM{è²k0©#Ä|Pˆß¹æK{Çò”¼´Øt:ğ<¤dm¤õäN–†ŸäÃ¿³gçşâz©qaÔÁƒîû>ßåªû‡±Ö	™õ.V@Ù[I%ãMá·ù)Dß]Et^ÇT]šµÄîv¡ß¿ìbUÖã½Áõƒ¬|ÎB5úmsƒ›2Òıf¹>ïF¶¸jdÔ¢áû¨ƒê³¥(4UfÙ¼ó¨ŸİJ: Æƒº·>İ«y?êSşhÚ@#@LÄBw\"®á­\r;È2xŒ€Ø‘ 40àí~“»ùÆĞîi?è¥ˆ%“K¦±ÄLè®ßcƒHPeí{|à\"0Ëì\'S#æ€•?­¯ø‰#¯‡ıÒCcöOæÊ“$·IƒÏÍ	ä9ÛÉçÀ#¸¶ŠÙ\\òdÏqìhÛıdğwì-­¼V~àdè€(µ¤<Ïe;]c@qÕ&;!ËK%Î”Nê]ÛZfGo>é%³­û¿é$wİTKœé$ƒ=Êe1Çİé˜:IàÂ!kˆ–È#ÅÎmd5ø£úÒ\ZÆ›FŸzl>Aä™|ÇÍLk†‡Útäh‡Sî`’çÄŞÖ£6,¬Å`€â	09üæ µÖûˆ#€[ŞıR&®*Í`¬‘¼†Ú>Š[ˆ$Ï>ECpášÎ£w?ÊO<¤qÙ8Ñi,\\deÚéØ{’$ğ\0Ïï$÷İ†é¸÷*;ªcá¸—C\\¶>Ši?ŸıÀ%‚x“ß]GştÍh~ºë«t=’8?8Q±À© ı(ç_ŠqĞZí®6F‡Rg¿Á¨’Y£Z7x÷å!‡¹ÅäÌ\0GˆÊö¢íº@\r×ƒ(\r´\nÿ×ãœíÀÉ$îDhö»°:	A<@çqSnñVàuú5ä?6`yÎ‡²0-\ròA\ZvQ\Zt”¡Vi2¾«<ë«uÔÇ÷(¿ip\0Ã`Ù=„G‰•ĞÑ¯$N;ŸÅC¢ÏsIho/ÍLçî:PîŸBˆ›ñ_AIÈ\'Í7u6ˆÔˆ‘¢\0Z‰¥\0LˆÓâœ í)\0ç\r©€>N”j‹6¯À¦w ¢\r¥¢ªgÍÓeİq`k#Tİ¹µ1³í>åmÖ™q2OG“<óÿ\0#uç¶ŸŞU[3$Hî^=lg©qÜÒv€¨cÖël\rÎœ•+.kÃ)hØÖ¦|Jxù¥åı)-7°ú­’\\İÖ‘-òVmqLyê¢ã`v×-‘¯dÛ*9NÉ°u;.ªX>(´—z‚¹€ò\Záä†|5O¸î‘ã!6&µñ\\ufæ€âİĞò”zKwl$–ˆ‚~’İêjy¯æû”H1¼¼TlYV7t‹áAk„6FéşO·óÔÓÜl!ÛC›Ìƒÿ\0\n½6†šÿ\0&÷šˆÌJ›ušn:“Ê—¢G‰Õg†€²É¾üw¶¦<ìfDƒ/ö¢Z\rÕ‡ÔİáÍÚñİ¤{½ß÷Ä*œÛ©?i]zú¿ŸàÖ7ó½\Z±s6—}œxh÷Ûôœ”u\'_LÇ¦\'æøÈ–€i¬w-vÑÚÃ[„Ü9\n6ã»¸;HËIÍ¯VÒ^íò7iÿ\0O÷–kœã¹2Ğa¦u×]®Jp ÿ\0(üªŒŒÿ\0-XËvò1©ãàœ8£¡iã€ ;ı~	ı® ´iñäxmQßˆ_Lİd´—;pj ·lÌ4RuŒ\Zmª†öKD\"N»„¦ÅNO=¥‡n\0\rOKqy“§dA\0obÓ¬¦‘Ät]tŒ5ì m÷6L\'eî ‡û‡y	çà`\r;$4†·sŒÉzV¡±Pc´Ûé½ÃÜx0Tm•8°há§—õßúMƒvç}:D(Ãt’\'ÇÄ)ÎÃOë¢=OØ’çzéâ>1û®KÜÑûÎú Çcôè¨·gÒ ˜3Çò“’Ñ k„/üáÚŸÄ4Ÿ	Tí¯!ÆÓÿ\0\"‡a;”šA!®Ö8#•—¨Weõ§›1Kµ%I®‰x0bš…¤µÀ	™ÔK„GnB\Zì¶¯^ìŒ»S¡<Â‰#°>)õİQÊS¯Ši¢ËWÄè<l¼wï÷¢80@M³¬ÜBæ éİ>_jSµíõ\Z|‹IÔôÌrƒK·6u\"u\n\"Ë7nGÜ¦6—4·¹ã²uæª¤aà¸nÚ]ÀD\'inƒÚÓÏÍGÓØy\0‚4:ò¦7¿Mó©ó„õİ&º#kš‘ßBD) ƒ^¼óğQ‘0~cø¤İÚÄÈ¢­ÚÁ\0‚y\ZpÛ&4±*çO$ÄL¥dxª¾‰šò\ZC#wæÎ“ísµP÷Ö\\4şt™„ˆù½0w(ñ[ı[ìÌnkàgSª•l˜;€·ó“8Ã`é\ZÈK{„4 ròœ(vh-Ô5œíÓ´™%GS“Ù&‡oöü$øy©Èhô]Øcóœ†övÕ;.Øl¡ÔJÜÑôæòHn~œ\rTÁ!†\0 ı-5$}Sâ{tZ|zªÀ[Ç¢A {GcıÉ<ÏÓöÀëùÉÅf÷©ÿ\0ÎÒÍš*Ö‚¥¬h’\\cî¥Tİ!­D‡cOë!\Zd9 ê¦o°ú0tBBC¿§°Q¶©Væ<‰øªÄ½­}Ná¢Dù¢†±¯iÄ;pĞ(’Òæ‰1sªS1;oÖ•FéiÅmk7–¼·t#÷T¬ûEMs»®GæìUÅÅ‘¶-,óƒô•º]¾’Çêæ4lvœjí»ù‰Ğ1:Iî+\ZQ½ öpö»À“ô^†Âudiù±ÎáîIöïp\r@áA‘¬	ÒÒ;ôP\'QIMX‘-–½”Â4İÉñNıº¸\0\Zèpÿ\0_ë(sx&!0€›\\@\rD’~*Nuqî0O¾J\r\'AætB›ëk£XŸ4á|&¾ÂƒV-H$\ryOPÜ\\5\"Q\ZÊí­¬s‹íÛù(ÕÑ[µ!¼^Oï#Ü‡î¨ËO ÃHÙ¨Rúoa{%†]$I÷~r#€mok\Z^ç„òCZ‡’ì†¸Xnú\'\0ôÛÿ\0Rœl×}ëş’Ñ®Ÿµ›-,s£p¤TËXâÂ8#ù)1²ĞÚ½ÛÆ­›)œëÖ¾d§hFöµRà’ã>	ÜÖDuã„À$ ÄR.nÉk§à”„T@a™Ôóª(C÷3ªE»H¦yá…ÀÁĞyò„>P™nVn³\0A>q	ll	>á¯sô˜“œ@tÌÇ\n5KÚCAÜ\0Ÿ™J…€­h¤ôÄ§¿ù)CHÑ£ò¨9®hìá÷¨<û@<\']}V–²C^IÛ:@Óù?E¨ml†’@PÓÜÏæ¦Üá\\séwĞ$àæ4˜£¶¿Šnš÷g;Iğ˜J]êÀ;u‚›MÄöQs¥Ö:b8MDŸÍ p nÆu#ÿ\0\"¦æïĞècCŞ¢ \0]© H\n{L8nÓ´Ïı$BŸÿĞãF¿¼TÙôTlîï*@Vá¡ú0Koª6‰w–¨Œ˜…‰y°*LˆÀîP†‡íT˜½Úx(m$nˆor‰ x\ZÆI÷VöÄGÈ @6dkÁ6EP´-h —Ò@N\Z6ÉQè³İÃxM\0³ü½I>%:Ì)9Ó	ŞÂÇl#XPˆCQcíN†Šj‚cAßä¦ê÷µ,\"O`èÿ\0¿*ûİ:)ÖçF:~E$f+†‰XbwVİï]Ìù©ItÉÔk*&{p›*é·D†DX·@I 	“ıd2#æ¥Ì>A3 ; uş_¥úI\rŠo4´5ì\n>* tĞ÷JNœ¢fH\Zh6@¥›„>I™)ä\"TşÏo$‰ï:Ïhg´ƒ¸§š7â€F”x‘\nl×Èø•×U&5ãÅ2®–Í…k„°µÀ¨1ÏõÔ]‹kß¨˜!¤8ÏÉA¼XÁÛ&è«UÙ[~ğË..!­ìGşMOŒøïzXÉÿ\0y¨ÀùÚ;øÿ\0U[8˜ïvÊì÷4n‡\r¬0~ï¤€ûöÚçÖá¦  :Ç=Òò]¤r›Å\n®=SR–¿+jú¬ô«Ókp¯Æ#ŸÎ@­Å­pk‹L}àı$)€ DH\'ÅM»KOïrg2°+N¥uPNû,;×X8Éª×Øn½­pÒ;ê¦ªÆµìu‚Y ıÊıBĞÂHv¤ò`şo¹L\'bRáºÿ\0Œ™Gatæµü	‰:¤\\ }-xVo£Àlô?4¹\01¡òD×TÙFCK¿‚¬\ZÍú\0Ôø©ˆf›yĞÿ\0çJ{ÚÖÀğ\0È*½ÜË“şr< m©UŞû,¹ ´ó¨ñş²#œ×¿ÜÒ\0ïÿ\0RÆµ@±Û´-it™™*l®×¹Çh0y½@\n5½£\r{ƒšYD¨×ó“šöLN¼ £´8Ï5gû)ë:ÈGû(ğ\n³º8µš×˜Şè¤ÿ\0Ô©0ËIíí*OôLXİ:ëÁC{Y%Íp$r\"uÃÚ_á|Ñ_wáôdÁXw¦ù\'Ãúª8´üŠ1ë.\\\'ã¢!»¹\Z%(€?/îşê²À‚çm\ZŸÂ¶ñ\0¸v=‰şJ“ŞÀÇ\0âèsµ?2·mH8–»ÓnÆÆ±ß÷“@¾©³LAÁ\0‚tlë!1ÔÀä%«-ğ¤.‰3©â«QÑ´Ó·xî2 ~*R¥š÷\ZOöSwOÈò•uñE£snÉ2L‡OˆóR$6 ÈçNÉ÷HÂ\\\"îş‰³HÜí }êuı&ÿ\0d’@Ó²LiiÉ:!FìªÅhÉ­$ğ\0äH9Ìâ9Ñ8–Ë¤ñOÅ?³Óö¥ÜƒßŸ¢Ÿ[t;­`âlj4Çõ”Aİ¡JÓó‡ b|ÇÃv¹[†éğû“·iä\0#Ç·ïj¢&|äüÃáâQvQ¦#y0`İOk]æy\rN[¡İ£[Çš¹ ÛŸGmıHßmX\0uv4°Ä4’tPË‰;¼åK}Úùy„,ODÕ\0¢k\Z5v„O‚‹¤¸7A»eDˆ`3¤Áõ)\rÎ:™|Âi—Jû]RX!»Ça§~ÿ\0œ§KÛ³V¤<|Ô\Z„=ãÿ\0$™ìváßéAì	HlwZ@ …µn‡Rx”F{‡Ÿî%@Lé:y•&jÒ_¡=Î‘ü¤£¡IÙrÈº5ÓñM·q’$vsyŠ5e„¾\\OÒ?›èÿ\0œ…‘-qLq<{¿” \0¾–‚n—umd‰ĞkÚ÷$ÌGà&=¡ÒÓÏçnB÷8ô”›»ÓsIÛ=ûêS.$üºt]¨êØmUú\"«4xšà4İá½F«‹ŸÚõ¼Ÿ¢§é<úv:A@€F¿÷ÕqÛQ²§3ğú%KF„£CJû>^%šYºgSU•*0tö™äşê¥îl¶‡B0m„—´‡1œÇàÅ\'Öí¬mŸ¤ÒFóıä£/U–¿ç(i¦èƒ70¡o»Xàı%;åÂvÆæÁñİwµ@´Z`ƒÊŒGØº·_‚g±)wb x„ìi;åMµú“.hÔN½Ò\0Ò‰J×cCK™7•ôv¦kİÜwê`ÃóRô¬à\0ñ;„ú”RöÕX²\\ï¥ğ*P5$úkÁgâ…„´¤·?7r.V@ß°je’9:|&ËµÌ©®kZİÓÈ—+üå[mvo\'|{æNÖğ…ÑöÁ£\\J­8ˆG2İkd¹£ÉM—<’á0{vşÊí\r©ÂGİ£ó½¿ÉQpcËöû^Ã¡ì5 6 ı{ØG-.&~3Î‰ÉÚÇ?Ëçª‹Z`ë>.<è“ôd\"%6ÍõU\0¹’_y>ÍÓ¼‡Í*ÕFÃ\"A:”`âft03éJ˜Ôù®n ;FÆ£Â?u3l±ÕÑ\"81¢åÁÎ<÷íà¥YöLîoŸüÉ\0I–úR¨WÕ Ÿq<ò<H‰ÕÄIÛ›$túz­º	ş	çjúŒ/K{ üJM\'Ö-‰ÅáµÌgX#^\'r=Bçr\nDkõPÛè»ŞÓ§Ä¡ƒìô??s•¿E¶Hl\'Ü®\rc£ÜÁ¦¾ã¹®hHƒJP:#`%¾=ÈòE|‡Gçü—òĞGÑ Ü©Õcš`éâöŸë§¯ÿÑã,Ö~\'U!ôB‹¸×Ä§iöÇ`­GsäÂvú±f¯*U.“\0k?İQ`$ÌhS·GŸ#Ğø•£ÁBwIî¡¬@IE:81İ9k™X Îéz<7ô»U¡\rptñSk…OòªGÒ¸ìiO‚#qÛlÛ[¶\0FHÓOÎJ8Íz~mÅüÈ3vcqtXdO„«¸9ÃA1Ûº•ÆßUÂí^;&/§ IÅiığ“@Gş‹*Uïk¤½JÂÇ°<çûò€ñïÙ2€£¤]v?Á ´¸@í\n\0IãM5ğM\\’cîOYúA>Ááú­ªµ8é´	wsä•uº†&?òIö‚ö‚v‚`”KMlVùÒ=¼š4,ÈşDYĞ½Q†€`Fœ¸ğU”Ôàæ€àß¤óÏö©—OšdÁ—„úBL/rèädWc%œ<é=Â¢ç½Â$…;\0cCH›;úûÚ¢È:\'dœ¤uìˆD\0Å£TMœmğQ® ’¤d£Ït \r”Èê¼0qˆäBˆcô±€íB\'HQÖ\n±‰<¶ÏE§G=ÇOc>’\"¤@#şùFÀ¶³É&b<”UŒ¦3GÖıÂ\0“‡åªòTyHõ]ay\'BTØ$ü\0ïä†òåš7Wæ’4l³ÑÙ\0`v ÄmåéŞáVãYö»èğUiÖ~jls\ZKˆİ7·ùÊQ“¦‘ñXcõmÑušìo©#F‘¬~r½2ó¡fš0Éû©ª²­¤¼{Éú]Ì¢Z\0i°wl|góT·Å¯ºşëT¶«a\0í‚#ÊÚáÃYãHúQÉQi™PuÈQ.—O\Z|SlRz²y	O\ZŸìíDsÜZ6·h=ÎŸæ¨´†Xt\'èîÕOÔ6ûA‚@:ãóS‡]hË¢–Ì7@\'Å9»c½7hØ$\"×‡’×tÖ8DuÀ¶à`í\Z?}Í@HÑıw—Ê’˜ìÉ;µiğÒPÚßÒ†·‚c]tLæ†XY:£»¥­˜\'Ìù(‰³¨øKê†šØ\\=¾¤×1ØœİC¼yhmCk£CÁ*è><wN ğë]ô@:è³ÚÀ[ ÉŠvµ¼IãÉDHøˆÚÜà“§Çÿ\03@6\0):\rJ˜$8ıİØÊr[„‰×_ÈŞ¡D nq	<Ã–™Ô÷)ş\0-ñ,w<é `r‰ $˜ñKôŒøpJ`\ZLÉ\Zj9„Ò~ßë$³Á‹İ¸4@<ŠLwnş)ÜÚÏó{´ñğñQÓSß¸Q×éT—sN¢tTí»šØPâPCŒxÇH8‘Sü‚w~!a‹`wh4“$Éş¶Ö¡ÿ\0ƒ£´ü‰Ln3î#·šw@aí®¾\'“aZ\"KÀğ‘`÷A‘È„œ\'M\0	Úb8ø(üŠõœ$øşj@Ä$`¦ò|µLZç%â7*ódO€‘ùñóğLÄÒàı\'MOÁ.½ÕLš\0%ÇF´Iï?ºÄ7X^eÜù)îN r{J\Zñ²d‰ª\Zw	» H2½¿œIĞÇuĞ“Ù7&{ª[n—}’th\0\0.MOÈşK”*;¨–ñKÔƒ¶6’t:ÈşRš4“ºÃ½\0¶ K¼c^ÉŞHnààéä¬ƒ¶`É3æx.4<¼ĞÚÅö¥xÒ‹‹@\r˜‰\'óÜ[í½‡·ıûr“ŞçK]«ÿ\0r‹«h`g„Ù]úzwH®½S×\r£Û:—ú\'û(¹-©ú2	´øîÚPÆÅ€ëO±†\"u$şja¶¹Ó ®5m¼å4A1\0/—÷½+	Ö÷¦«^KÆéÛà kÚòëI\0KˆúB~‹¬¡»·‚6¬8iü¥k¾ùfƒhËÑKH&\'º¤EXjX}c\rşhğ>ŞCÉ.i?Hğc²µ“_èİe	şpZö»üåZè:ÀİÏÃş©	‚\rßş„¨›\nkSˆ’;ñóO¸¶lq‘$Ü¢‚æ‘§cÛ„BÒêÎ°F»u?óÀNº|».¡§‹¾eäû¼N²¬TÜ`Öúh2f#Çé*ú\'IÕI¬%Ãf£O’P‘ÓN%Ho­7Ç‰q Ô§¯Òy‡:µÇ¸šœl÷ZØ˜ı×(×cÛúW¼&¶‰1â§ØÅ{axi£sàX=•·“¢l\nÚ¬ú 4q§ò’³%ÆĞĞC”DÈçvå*la›\0\0¸é¤’£\' –ŸJíDHïª{kcÆö{.a;™À#ó¿¶ª¼4€æèÓ5?P6Áù§w¸ùÈ,yÚD‚uo‡Á:R×t\0~Åû;YÔ’B.ôMÍödèyP´ˆ>aG?—~‹£º¨l?š/ ‘Ûò\n¶ì\ZCG=ä¤H\0>bQJ:Èù±vĞÇ$‰%8IÍ*“İd£“à5NÇÍ~fyá\05#ú½•z}W¬º\Z<¥3Iõ^gè€?ŠzƒŸ\rtLv1ü¥?Iìs‡Ò |4üõ \ZFºŞ­=Y¸µ²g`\"\0Îæú–5*ñ®p—¶[${½¦>—w5*ì{Øâ÷û\Z@ Àíík=ÈGu—™€6“ß_ÎKM4»µ\'`mvV	Û© êİ!HÙ¹¼—s©‰•T[†¸ºˆˆú_EK¶ÌşpãM‰AWéˆLÂræÄ¶;òö}©œLÂxBƒšv˜§îÓè§ÿÒã-ú_2œğšÃ\'_‰Ğ«7óy0öc1ÁM®-ypç±óQ\"\Z/;›à–ß‚·[~»Ï)Ûi™$èe¾J%²tMSnCí]@¤yõ¬’d€\'¹(Î²Êè£kt#¿õ•v]»]51¡şÊ•–o;š xx~ï¹:2¡)j%\"´ÇP:7\\ûŸ½úº!@ù\'ySA ë QÑ™$ú•ÖŒÓø§ÓBŠi»šFœÂf\rÍ2&<9Rpú-â\ZÔ)4‰)œÂÃ¯“·éH\n l¢l»ãhCtè¦GsòPv§A¢Õ1^±¨é5 ¾;ÀşA8¸vİ!…lPNë¹º<&´ã‚‰º|J„ƒğOôDMè³2Tƒ4OVÎ]ôG\'ı~’;˜ĞİÁÀwƒÌe(CÓ`ªR×PƒI˜ø5òt…1Ï÷&p\0$,ƒª3=Ó7¶Å&è8ÑGÃê¥×£×—\0KG&4	4Á•g÷½Íe_Í€Aú>ï¥ı· [Kª°×Ìiıá1,HÀ€%=?ôU	Y ±&O‚^Ò4çº·U8Õ;!ÅÖ8êÆş`#Ûgü#’Ìc=V7i\Z‘Ææ¡vßßÿ\0Hí\"Eşïé#ŒXÿ\0¼Õa2Ø‘¢»²!¼´ƒ w“íÙü¶*l:é¦‘*Ã¾˜î#nº„üZ×²Ùî¸\r@28”=KxR™ç”í\r0Ï™?ÍN\"Ğ4\\ZŒ—F02Âun ÒÌ\rÍÕî#k~	5ÂËbA{éùÊ@(Õa6e™×c]Á.À„X´92§%ƒXÔî!+k8e§±ğ)£!§ïIvœDoØ\"±­qaö#À…¤7Äø\"¹py¬FØ;|Gï!±ÄK¢|%G(-GÍÖ+ÁÓËºïy%­Ct!H¿vÒyÖ<£óT^Cµ#îJ;ù$I×[Všt\\í™‰ ¹ÚZt’¤vÍÓ°\'ÿ\0\"˜XÑ|y(š½iV•ÄqĞ4xÿ\0_ÎK†Ìn†ñ¨å3ØÄêL°iáÚ8ÎO°O÷–Ñûa…§érßê9‡x\"´è|4ö*kC€#±	’‘à¸K<vôãhÑßÚB{Kæ»‘§Ç÷Q¶¸3^ÇO4;šğâ]Áâ<½©N5+eDú·İ€\r]¬v\ndñ\Z	ù¡¸kà§ºY‚dNãm±fX\ZÉW-¾Î8ğBlóÎ²¬%¯\"dGËüÔğFõÒ–×ŠÏi×H‰ÑEƒÚ\'ŠdâCt<x&uºî»¥2\Z‚{w%6ø \rHÿ\0_j[¼¾)ƒªv½§U7é4º¢mÛï\Z?•ûÉ›ô„xÂKf~	À\0	ì´›(œ\02˜:F³·¸ñşJ[I¤~ôÑø¾Õ¢ñ^k‡‰ÑbS‚4%¢	1Ç^(‚6ùÉ ‘¢Q‹ûHµ5ĞàZ?½ ï|èÖ~j.Ü×‡´Áä|”ÚÂHsLÎ»!<·b´Öş	híÉJÂwHÔø\'aÖ|;÷Q/Ş6	ÔùüR¿O™ÿ\0¢®«DŸq\Z\'ôıF‡8ãí@i-\03¦¼l}n®º\ZÙku™wõ“€û?¬´©f¿a†´¸j\'ú»¹rmöKÜˆƒ®‡OÜıôÅÅ Ë3 ‰3÷“=–k`€x3jVz^«¿TF×>s¬ÿ\0%ÈÎ¹Áî \r³¤v¢æ ½ƒt™×€<Q4Û.@4ì™+\"×J´Ñ-bÇc†ãË|ACtšÜ6­~Ñâ$Ê[€lƒïGñr0Wm_áe£ÄT›‘¤InÖ|B™\"5ø§&`˜wÏÚäÎ BV\rtÃ¥Ë‡@¦j ©RÂë\0\Zkxå$ØÜO€1ÿ\0Uÿ\0I*ø€5\'æŒGËáÿ\0r‚wo]{}8¨ë0ÓçOû*«}GÔ?¯aî’ ÷ID€gwdVŞ\rnkı¡ÃCÉHd%-MWø«\0¡ aöf½µË´ƒ©Ò$»féR>“k¬;èµ®c ˜;¿9¾ô-u[D’×s©ÜÓùÃúR»Ôª±ûœ4ÄYF(Dx}\"åó/Ô.õ\\0@sK\\7ñü—$êàkßY\Z„ª½„\ràn’ò!™5½¡¶	$\0{	Ÿ¤Ÿ7\Z!Ñkt\0~Ft*.-içAå?ô\\Ãc‹5²³ò„Ù\Z‰®‰ÊvzD{´ö<$èĞ1ÿ\0EB£€xRoç´OÀAÜ¨;ôgÜ<‚53ıTòÒÉ\0sùPœZ\'¹¿´ˆ×~Œ©ü¨©²ˆÓê½PşúMí¢;[cZM.kÃ›%®‚D~k•]Æ ÷Zè¢¶÷4hŸĞëü¤bG^ƒp‚EØÆ‚\\]´™Ğs?rB–×¹ïòg‡50mN{ı¤À5ş±¹Æ=ÁÆcùMD->]U~h_[C€¤™y÷\"Væí 0¸wqĞÏıJNØãŒè>aN¦zd½®\rKAçù(ˆë¢	ÑvÒæ¼³ôuúÍ*ws$ëñD>ã©ˆ\"=\rík°™\Zÿ\0~N:¾‡ÿÓãïîQ‡r¥cHwÌ¦#Üš:ôÃlŒÄr£À*\\&w’qPä•#%F2\'èÈ!¦¼Ÿ4Ûˆ½ŠZ¤a#·e,­TàÖµ	tLŸäª©ÄwÓÍ,sá6‰FÂgØ¾¸ŸÄ!n=¹SmdÌè{y¦{j¤<G};-6g¿{6¸q¨*Ğ•!ØY%Å¼oU\r‹#¢D¸\0y\nE@ˆ#Å)]øò”¹:€›XåGPek¢©>òCdjÑÊ\rš¸”òN½”\\BS•Æ•E›Ö¶;ò“ˆv²J‹‰íâ“KAÕ\0OeV¤…Ù·i$û¿4ê&@×¿\nGo-<{¦$R;Wä¬L¥:sòLRì£½JæÎ+\ZoŞ\Z+ í=Ê±~1mÕ9Î60´†ó<mş®ôV9mu´îµÆNşïõrmp-5¼ıÈ¶¾ßë}5dT1ê.¨é/Òb7)hjü:0¾Âë4	ÅY©ÉØı¿£d‡ƒ¨€7{\'üÅE®Úf%M–=¢ˆdÈ¤(á“ÔLµâŞ?ôWJ\ZPéÕ¹}4rÍ¬$\r¡¼;÷«wÒÛjhû\0éİE­´±¶°HÜ|Ï’gçKG„©I^ÖQÚÖ€¶H	ëyk¤	$(Ooaµµ­w\'ò%IÓJT´\ZõAºÁcOçi÷\"ıäèİŞé$\r\nv·uf ê\0ÓÇÛ¹5Öhaˆ±¦#Àª6~*»;T±aôëh—\rÀ‘ÿ\0EBË7ì°×ã*UºÊÚĞK|{k¢Ox/h:	CS.´Ÿû¤éÅu×æ`XCƒ„¨$÷Lñ[ØüÛ\0\ZŸŒY5Ûeı/ºÛ¼¥?b9ğNXævu#U+,q\0oîÙ8Š÷ò[zŠVÇöŸÍóQ}`Æˆs~’M¶É÷ÃÏXşª-“>›@p#Päà# wúüÈÔŒaĞ53Êp;Á#€\'”-IW6Æ€çI>É@t@’<wX5±<xîQq%û»è„è7Šƒšï1¡BcM©ƒœÆjŞ	óÕÈVÇ´\0Gr<ÿ\09XC˜vÈh>ÿ\0$›(Íîƒ#B>iòÖ>DElwüPìO	à\0cæ‘í¢~É€\rWÙTÏÜ%\'l:ƒ1ªxãn¤ó*\'I|óì†#¹O¬|S6 ü†é\'Ã”Ğ¸¨ñ)õÿ\0]R:7îR€çÃºx\Zı‹m`yøD¦q\0ä§g°%4{€ğÕ#·š†ë“ÜÈrîJ2ó<\rIì8	’7­®3ô=¿HŸÈ£-?ÀCkˆ€ˆ6’5îœ WAÃªÒ(ŸlœZ@gP¡\0=¢½§æ°CÇ~ß$ê¾ÈºY»LÉ\"*;[:8qæ?*vÉİI;[ï@G…]h­AĞîyAÏ‘äyNò5$î$ë(d‚S\'\"4´ÄuOM¬ceÄ†N€r¬×E%®Ò$ƒ¬ûâ©W¹í\r<Ÿ ®±Â¼V¹Ü<Gc*|4~j\"#³M6êŠ×Ve€í‘2fPœæ²¢Ödr5…7E”5ö;Ü	h|Õ$ÒÍ¤’îZGó’6IªÖ:„¡C¾í`\\Í{wFÇŞ×z¬‰QäT\ZÀã6Œ?DT†KÛ¤5ì;EI46ıåò×@ÙÉ=­±º8CmEÒÁ ŞÂOmGşE;›5‚	Ğ¹§¿òÔ ‚\\İù)\'d“[­›ö¸c_4jS+Şğ\\\Z!£·ö’¬w4ˆ±Ü»ÆdÎØÙl\0\0Ä=ZTTH&Ø¾ñd4>áàî§¢§\\ØÚ	äè‚O\Zn	Ñ\\Æk*túÏpÕº|¬B)zõÊ€ô³m&—±ÌÜj ‡ú©eQëÔûĞŸİÑ;¯ÈUBâøp¬üş–‡óšÕ9á”kŠ$|¬@›cİ¨Üh:¸	Ğú©–@,Úwk3É!FßifÈ:ä”é:˜—~ğ<éíÜ¡Œbÿ\09“WvÁ¤–çû”_¬ü8FÙí%€€9€ò`8Ñ	Š½•NŒë V4˜ÔÎ­×æ†´ %A¯Å:;W‚ÿ\0TotmÔ‡\0~ÖS$£¿>\\(8€öC`i<«aÌs}G#B@R€³-{*F€Ñ«næğûÿ\0RäamdE;¤ó¤c[a®kb·Ç°]º¡é’ch}­?¼ŸÀA4wè·ˆ\ZÑs·–³dDşäâ^:	Ö#üäW6ĞKbbd?0A™ĞòAÔi§µ	nÉšçË¤ë><2}²ßÌl6u2~’˜i D|\ntA¥§v K„vù(†@ŸåIî$–ˆ\0ßç\'|ÁÜC`h±¯…§_µÿÔã¡æ9_‹¤ü$•‘Å^Âi»)J¤’v¨m\"‰U’QKb¼6%%]$ÓºæÂvB¬’Cpƒ³¢^×6>óÙ\09T’Vez][ëVŞÁA¼•Q$Éoñ\\:·SÁTÒHİ(6Á“OäDÇ\nšI½5İ=[…±Î²%EÆ4EU$¥TiCÅ¶Ó 	ã_5M$†ÂÔ[‘:&.\n¢IKÃØ¡âØNÖ¹Î\rh’L\0«$¢;ê½¼êÙSƒK·ôŞÓ#úµ«VÕKj–ïªÆ‡j}Ãó}¿×Xé+0à©WN/ı	†\\Z_õ©¿•Œ(xÚNÇ‰lò?íªÜÖísH\Zí=•4”g‡Š|t¯ùË…ÔxL|²Öl-7kñº êœ÷¹Ìo°ê#]rJmxcÇuú?â¬Óˆğı[à¯îòâˆ×ƒßÛÃ{ê³F=+ü%£¤÷È:ü8(.$€ıÊšI™/ø&5ü]:SI‚Şî¬Õ!¦k~£à¨$Œ¾Aº8+—×ş\n‡Ì|ı_÷-ÀFÒÓÁQ$Ä*©(×Ó§î¯m¶Úæ™äÄ\';œk\Zü•4õ×ªøUé½*ÛncšvÇËÉ3ÌD8òURKN=.¼ú:Óq ƒ¦û ë5%8âõnÄkFä’Or‰½¬¬ˆ›,\ZÏa>Õ’fº×Í®ëiÛGN‡2H—h\Zšä¿Ç‰T’N•ğFûô@®\"İ#„úÂ¢’ot·Áˆû´Õ1\"8•’_EhÜhü‰ãÚU$“EWĞ®;ı[Îi€;„€|J¢’SKzjİjA²çxp©$€¯M§»n:p³Gq\n¢I’ùJFáµ¶\0ñ*a²àxT’HpşJ6èßÅDañTRRyv’Ï>áº\Zç{Z$•2\ZÒã<Ğ¬ô_ÖI¿£k¼ê*ºJ	2íOØCšDÁ?İFºÇ\\Úè`ö·€;˜çrÌIOà?¹§vc•q[£¸+­•††Fª®İ <˜ÇO5š’Ÿ\'¦«oOò‹o]÷ÕÓsÜ€ÇŸ}?é!Øğt	àB¢’ŠÖâád…[§SöÒ¼¦p“<š’wè‹Û¢Ş¦ŒzÙ{ZaÜÚşî¨c\'QË¼JÃIK?’»ÕÙ;	ºÖÀûĞf–ï%²Hoi+1$ÉøpßŠèı]\"wZLGò^¥s@hsß©ˆ†Àıİ»–ZIŸ£+Şÿ\0­Â»¨®ßá7ìŒ°¬)ú®m•˜sµòÔûÚåš’øWèÿ\0ıTéBÿ\0­ş+­sı+ÃØ}¯ü¿œÕ^òÙ%¿EÆG’¢’9¯†]¸ıÚ!Wõÿ\0¢º,$1ƒ¿?/ÍS\'ÚHû–ZHÆø~ˆ;ı]ki‰DÊ;.Ô¡£´h$şrÈI(]š¯¢¥U«´ëÏºYR{¥í”âNæòOÒ\'ïX©)5Öÿ\0ô%™{¸ÂA€¥± ‚ÓÎ²\nÄI:©Ú.Şğ]Øq\Z\ZwX©$§WiõLs)Ÿ1\0k+-%Cæ»¨ÿÙ',NULL,'','','','jmi');
/*!40000 ALTER TABLE `cir_requestfrom_opac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_transaction_history`
--

DROP TABLE IF EXISTS `cir_transaction_history`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cir_transaction_history` (
  `library_id` varchar(100) NOT NULL,
  `memid` varchar(70) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `transaction_date` varchar(50) default NULL,
  `document_id` varchar(25) NOT NULL,
  `status` varchar(100) default NULL,
  `checkout_id` int(11) default NULL,
  `checkout_date` varchar(25) default NULL,
  `checkin_id` int(11) default NULL,
  `checkin_date` varchar(25) default NULL,
  `fine_amt` float default NULL,
  `lost_item_id` varchar(25) default NULL,
  PRIMARY KEY  (`library_id`,`transaction_id`,`sublibrary_id`),
  CONSTRAINT `cir_transaction_history_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_transaction_history`
--

LOCK TABLES `cir_transaction_history` WRITE;
/*!40000 ALTER TABLE `cir_transaction_history` DISABLE KEYS */;
INSERT INTO `cir_transaction_history` VALUES ('jmi','111','jmi',1,'2011-07-02','1','returned',1,'2011-07-02',1,'2011-07-02',0,NULL),('jmi','111','jmi',2,'2011-07-02','1','returned',2,'2011-07-02',2,'2011-07-02',0,NULL),('jmi','111','jmi',3,'2011-07-02','1','returned',3,'2011-07-02',3,'2011-07-02',0,NULL),('jmi','111','jmi',4,'2011-07-05','1','issued',4,'2011-07-05',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `cir_transaction_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `courses` (
  `library_id` varchar(20) NOT NULL,
  `course_id` varchar(20) NOT NULL,
  `course_name` varchar(50) default NULL,
  `dept_id` varchar(20) NOT NULL,
  `faculty_id` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`course_id`,`dept_id`,`library_id`,`faculty_id`),
  KEY `library_id` (`library_id`,`faculty_id`,`dept_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`library_id`, `faculty_id`, `dept_id`) REFERENCES `department` (`library_id`, `faculty_id`, `dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('jmi','bsc','bechelor of science','cs','science'),('CUL','MCA','Master of computer science','CS','Sc');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customizedbiblio`
--

DROP TABLE IF EXISTS `customizedbiblio`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `customizedbiblio` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL default '0',
  `marctag` varchar(20) NOT NULL,
  `indicator1` char(1) default NULL,
  `indicator2` char(1) default NULL,
  `$a` varchar(500) default NULL,
  `$b` varchar(100) default NULL,
  `$c` varchar(100) default NULL,
  `$d` varchar(100) default NULL,
  `$e` varchar(100) default NULL,
  `$f` varchar(100) default NULL,
  `$g` varchar(100) default NULL,
  `$h` varchar(100) default NULL,
  `$i` varchar(100) default NULL,
  `$j` varchar(100) default NULL,
  `$k` varchar(100) default NULL,
  `$l` varchar(100) default NULL,
  `$m` varchar(100) default NULL,
  `$n` varchar(100) default NULL,
  `$o` varchar(100) default NULL,
  `$p` varchar(100) default NULL,
  `$q` varchar(100) default NULL,
  `$r` varchar(100) default NULL,
  `$s` varchar(100) default NULL,
  `$t` varchar(100) default NULL,
  `$u` varchar(100) default NULL,
  `$v` varchar(100) default NULL,
  `$w` varchar(100) default NULL,
  `$x` varchar(100) default NULL,
  `$y` varchar(100) default NULL,
  `$z` varchar(100) default NULL,
  `$0` varchar(100) default NULL,
  `$1` varchar(100) default NULL,
  `$2` varchar(100) default NULL,
  `$3` varchar(100) default NULL,
  `$4` varchar(100) default NULL,
  `$5` varchar(100) default NULL,
  `$6` varchar(100) default NULL,
  `$7` varchar(100) default NULL,
  `$8` varchar(100) default NULL,
  `$9` varchar(100) default NULL,
  PRIMARY KEY  (`library_id`,`bib_id`,`marctag`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `customizedbiblio`
--

LOCK TABLES `customizedbiblio` WRITE;
/*!40000 ALTER TABLE `customizedbiblio` DISABLE KEYS */;
/*!40000 ALTER TABLE `customizedbiblio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandlist`
--

DROP TABLE IF EXISTS `demandlist`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `demandlist` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `memId` varchar(70) NOT NULL,
  `title` varchar(50) NOT NULL,
  `category` varchar(50) default NULL,
  `author` varchar(50) default NULL,
  `publisher` varchar(50) default NULL,
  `publish_yr` varchar(10) default NULL,
  `isbn` varchar(50) default NULL,
  `no_of_copy` varchar(10) default NULL,
  `volume` varchar(50) default NULL,
  `edition` varchar(50) default NULL,
  `remark` varchar(50) default NULL,
  `demand_date` varchar(50) default NULL,
  `language` varchar(20) default NULL,
  `issn` varchar(20) default NULL,
  `status` varchar(100) default NULL,
  PRIMARY KEY  (`library_id`,`memId`,`sublibrary_id`,`title`),
  KEY `library_id` (`library_id`,`sublibrary_id`,`memId`),
  CONSTRAINT `demandlist_ibfk_1` FOREIGN KEY (`library_id`, `sublibrary_id`, `memId`) REFERENCES `cir_member_account` (`library_id`, `sublibrary_id`, `memid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `demandlist`
--

LOCK TABLES `demandlist` WRITE;
/*!40000 ALTER TABLE `demandlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `demandlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `department` (
  `library_id` varchar(20) NOT NULL,
  `dept_id` varchar(20) NOT NULL,
  `dept_name` varchar(50) default NULL,
  `faculty_id` varchar(20) NOT NULL,
  PRIMARY KEY  (`dept_id`,`faculty_id`,`library_id`),
  KEY `library_id` (`library_id`,`faculty_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`library_id`, `faculty_id`) REFERENCES `faculty` (`library_id`, `faculty_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `department_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('bbzhcet','AP','Applied Physics','ENGG'),('amu','Arabic','Arabic','a'),('iitk','bed','Education','sc'),('iitk','cs','Computer Science','cs'),('amu','cs','Computer Science','sc'),('jmi','cs','Computer Science','science'),('jmi','fm','Forest Management','wl'),('amu','His','History','a'),('jmi','his','Dept of History','arts'),('iitk','phy','Physics','cs'),('amu','phy','Physics','sc'),('scc','Physics','Physics','sc');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_category`
--

DROP TABLE IF EXISTS `document_category`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `document_category` (
  `document_category_id` varchar(20) NOT NULL default '',
  `document_category_name` varchar(30) default NULL,
  `issue_check` varchar(20) default NULL,
  `library_id` varchar(20) NOT NULL default '',
  `sublibrary_id` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`document_category_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `document_category_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `document_category`
--

LOCK TABLES `document_category` WRITE;
/*!40000 ALTER TABLE `document_category` DISABLE KEYS */;
INSERT INTO `document_category` VALUES ('g','General Reading','Issuable','amu','amu'),('g','General Reading','NotIssuable','iitk','iitk'),('m','Manuscript','NotIssuable','amu','amu'),('man','manuscript','NotIssuable','scc','scc'),('r','Reference Book','Issuable','amu','csamu'),('r','Reference Book','NotIssuable','dei','dei'),('r','ref','NotIssuable','jmi','cs'),('ref','Reference Book','Issuable','iitk','iitk'),('ref','Reference Book','NotIssuable','jmi','jmi'),('t','Text Book','Issuable','amu','amu'),('t','Text Book','Issuable','amu','csamu'),('t','Text Book','Issuable','dei','dei'),('t','Text Book','Issuable','iitk','iitk'),('t','textbook','Issuable','jmi','cs'),('t','textual','Issuable','jmi','jmi'),('t','textual','Issuable','yy','yy'),('TB','Text Book','Issuable','bbzhcet','bbzhcet'),('txt','Text Book','Issuable','jmi','jmi'),('txt','Text Book','Issuable','scc','scc');
/*!40000 ALTER TABLE `document_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_details`
--

DROP TABLE IF EXISTS `document_details`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `document_details` (
  `document_id` int(11) NOT NULL default '0',
  `biblio_id` int(11) default NULL,
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `document_type` varchar(20) default NULL,
  `book_type` varchar(20) default NULL,
  `accession_type` varchar(20) default NULL,
  `date_acquired` varchar(20) default NULL,
  `title` varchar(200) default NULL,
  `subtitle` varchar(200) default NULL,
  `alt_title` varchar(200) default NULL,
  `statement_responsibility` varchar(200) default NULL,
  `main_entry` varchar(200) default NULL,
  `added_entry` varchar(200) default NULL,
  `added_entry1` varchar(200) default NULL,
  `added_entry2` varchar(200) default NULL,
  `added_entry3` varchar(200) default NULL,
  `publisher_name` varchar(200) default NULL,
  `publication_place` varchar(200) default NULL,
  `publishing_year` varchar(20) default NULL,
  `parts_no` int(11) default NULL,
  `subject` varchar(200) default NULL,
  `entry_language` varchar(200) default NULL,
  `isbn10` varchar(20) default NULL,
  `isbn13` varchar(20) default NULL,
  `LCC_no` varchar(30) default NULL,
  `edition` varchar(20) default NULL,
  `no_of_copies` int(11) default NULL,
  `author_name` varchar(200) default NULL,
  `guide_name` varchar(200) default NULL,
  `university_faculty` varchar(200) default NULL,
  `degree` varchar(200) default NULL,
  `submitted_on` varchar(20) default NULL,
  `acceptance_year` varchar(20) default NULL,
  `collation1` varchar(20) default NULL,
  `notes` varchar(2000) default NULL,
  `abstract` varchar(2000) default NULL,
  `address` varchar(20) default NULL,
  `state1` varchar(100) default NULL,
  `country` varchar(100) default NULL,
  `email` varchar(200) default NULL,
  `frmr_frq` varchar(20) default NULL,
  `frq_date` varchar(20) default NULL,
  `issn` varchar(20) default NULL,
  `volume_location` varchar(20) default NULL,
  `production_year` int(11) default NULL,
  `source1` varchar(20) default NULL,
  `duration` varchar(20) default NULL,
  `series` varchar(1000) default NULL,
  `physical_form` varchar(200) default NULL,
  `colour` varchar(20) default NULL,
  `type_of_disc` varchar(20) default NULL,
  `file_type` varchar(20) default NULL,
  `accession_no` varchar(40) default NULL,
  `record_no` int(11) default NULL,
  `call_no` varchar(30) default NULL,
  `volume_no` varchar(5) default NULL,
  `location` varchar(200) default NULL,
  `shelving_location` varchar(200) default NULL,
  `index_no` varchar(20) default NULL,
  `no_of_pages` varchar(20) default NULL,
  `physical_width` varchar(20) default NULL,
  `bind_type` varchar(20) default NULL,
  `status` varchar(50) default NULL,
  PRIMARY KEY  (`document_id`,`library_id`,`sublibrary_id`),
  KEY `biblio_id` (`biblio_id`,`library_id`,`sublibrary_id`),
  CONSTRAINT `document_details_ibfk_1` FOREIGN KEY (`biblio_id`, `library_id`, `sublibrary_id`) REFERENCES `bibliographic_details` (`biblio_id`, `library_id`, `sublibrary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `document_details`
--

LOCK TABLES `document_details` WRITE;
/*!40000 ALTER TABLE `document_details` DISABLE KEYS */;
INSERT INTO `document_details` VALUES (1,1,'jmi','jmi','Book','ref',NULL,'2011-09-13','complete ref java','','','kedar kumar','kedar kumar','','','','','','','',NULL,'','HI','z100','','','',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'HI1',1,'100','2','RM','3rd stack','I-XI','200','5.4 inch','hard','available'),(2,1,'jmi','jmi','Book','ref',NULL,'2011-09-13','complete ref java','','','kedar kumar','kedar kumar','','','','','','','',NULL,'','HI','z100','','','',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'HI2',2,'100','2','RM','3rd stack','I-XI','200','5.4 inch','hard','available'),(3,2,'jmi','jmi','Book','ref',NULL,'2011-09-13','Java Bible','','','Asif Iqubal','asif iqubal','','','','','TMH','','2000',NULL,'','','','','','',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'3',3,'200','1','RM','','i-xii','150','8.4 inch','hard','available'),(4,2,'jmi','jmi','Book','ref',NULL,'2011-09-13','Java Bible','','','Asif Iqubal','asif iqubal','','','','','TMH','','2000',NULL,'','','','','','',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'4',4,'200','1','RM','','i-xii','150','8.4 inch','hard','available'),(5,3,'jmi','jmi','Book','txt',NULL,'2011-09-13','prem chand','','','prem chand','prem chand','','','','','','','',NULL,'','HI','','','','',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'HI5',5,'300','2','RM','2nd reck','i-xi','200','5.4 inch','hard','available'),(6,3,'jmi','jmi','Book','txt',NULL,'2011-09-13','prem chand','','','prem chand','prem chand','','','','','','','',NULL,'','HI','','','','',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'HI6',6,'300','2','RM','2nd reck','i-xi','200','5.4 inch','hard','available');
/*!40000 ALTER TABLE `document_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editmarc`
--

DROP TABLE IF EXISTS `editmarc`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `editmarc` (
  `tagnumber` bigint(20) NOT NULL default '0',
  `tagname` varchar(100) default NULL,
  `subsymbol` char(1) NOT NULL default '',
  `subdescription` varchar(100) default NULL,
  `repeatable1` tinyint(1) default NULL,
  PRIMARY KEY  (`tagnumber`,`subsymbol`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `editmarc`
--

LOCK TABLES `editmarc` WRITE;
/*!40000 ALTER TABLE `editmarc` DISABLE KEYS */;
/*!40000 ALTER TABLE `editmarc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type`
--

DROP TABLE IF EXISTS `employee_type`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `employee_type` (
  `library_id` varchar(20) NOT NULL,
  `emptype_id` varchar(20) NOT NULL,
  `emptype_full_name` varchar(100) default NULL,
  PRIMARY KEY  (`library_id`,`emptype_id`),
  CONSTRAINT `employee_type_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `employee_type`
--

LOCK TABLES `employee_type` WRITE;
/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
INSERT INTO `employee_type` VALUES ('amu','nt','Non-Teaching Staff'),('amu','t','Teaching Staff'),('bbzhcet','s','Student'),('dei','st','Student'),('dei','t','Teaching Staff'),('iitk','nt','Non-Teaching Staff'),('iitk','rtd','Retired'),('iitk','s','Student'),('iitk','st','Student'),('iitk','t','Teaching Staff'),('jmi','nt','Non-Teaching Staff'),('jmi','s','Student'),('jmi','st','student1'),('jmi','t','Teaching Staff'),('scc','s','Student'),('yy','st','student');
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `faculty` (
  `library_id` varchar(20) NOT NULL default '',
  `faculty_id` varchar(20) NOT NULL default '',
  `faculty_name` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`faculty_id`),
  CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES ('amu','a','Arts'),('amu','sc','Science'),('bbzhcet','ENGG','Engineering & Tech'),('iitk','cs','Science'),('iitk','sc','Social Science'),('jmi','arts','Faculty of Arts'),('jmi','science','Science'),('jmi','wl','Faculty of Wild Life'),('scc','sc','Science');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `feedback` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `name` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `comments` text,
  `date` varchar(15) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `library` (
  `registration_id` int(11) default NULL,
  `library_id` varchar(20) NOT NULL,
  `library_name` varchar(50) default NULL,
  `staff_id` varchar(100) default NULL,
  `working_status` varchar(50) NOT NULL default 'OK',
  PRIMARY KEY  (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES (9,'A','A','admin.A','OK'),(1,'amu','Maulana Azad Library1','admin.amu','OK'),(7,'avlin','avilib','admin.avlin','OK'),(8,'bbzhcet','Book Bank, ZHCET','admin.bbzhcet','OK'),(3,'dei','Dayalbagh Central Library','admin.dei','OK'),(10,'e','A','admin.e','OK'),(12,'i','i','admin.i','OK'),(2,'iitk','IITK Library','admin.iitk','OK'),(10,'iu','fhdfh','admin.iu','OK'),(4,'jmi','Dr. Zakir Hussain Library','admin.jmi','OK'),(0,'libms','libms','admin.libms','OK'),(11,'o','o','admin.o','OK'),(13,'p','p','admin.p','OK'),(6,'rvce','rvce-library','admin.rvce','OK'),(5,'scc','Shivaji Library','admin.scc','OK'),(9,'tt','ghfgjhfg','admin.tt','OK'),(11,'yy','fhdfh','admin.yy','OK');
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `location` (
  `location_id` varchar(10) NOT NULL default '',
  `location_name` varchar(20) default NULL,
  `library_id` varchar(20) NOT NULL default '',
  `sublibrary_id` varchar(20) NOT NULL default '',
  `location_desc` varchar(2000) default NULL,
  PRIMARY KEY  (`location_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('Cir','GF','bbzhcet','bbzhcet',''),('gf','central','rvce','rvce','main library of rvce'),('Old','Old Building','amu','csamu',''),('Old','Old Building','iitk','iitk',''),('RM','Reading Room','amu','amu','2nd Floor'),('RM','Reading Room','jmi','jmi',''),('TSD','Textual Division','amu','amu','First Floor'),('tsd','Textual Division','dei','dei','First Floor'),('TSD','Textual Division','iitk','iitk','Ist Floor'),('tsd','kk','jmi','cs',''),('tsd','textual division','scc','scc',''),('tsd','text','yy','yy','');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `login` (
  `library_id` varchar(20) NOT NULL default '',
  `sublibrary_id` varchar(20) default NULL,
  `login_id` varchar(100) NOT NULL,
  `staff_id` varchar(100) NOT NULL default '',
  `user_name` varchar(50) default NULL,
  `question` varchar(1000) default NULL,
  `ans` varchar(500) default NULL,
  `role` varchar(50) default NULL,
  `password` varchar(200) default NULL,
  PRIMARY KEY  (`staff_id`,`library_id`),
  UNIQUE KEY `login_id` (`login_id`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('amu','amu','kedar','111','Kedar Kumar','@','null','admin','b454a6858eb34fee55ec859fa2634510'),('bbzhcet','bbzhcet','kamal','111','Syed Kamal Ahmad','What is your school name?','SST','staff','7f58341b9dceb1f1edca80dae10b92bc'),('dei','dei','111','111','Arush Kumar','What is your pet name?','arush','admin','6e45a1a9e36ef7f033b949131f2f8893'),('jmi','jmi','222','111','Kedar kumar','@','null','staff','2ee65f24663cc23f7fe6655d7abff80f'),('yy','yy','j','111','gsg dgsd','What is your pet name?','dd','staff','f089719c48d9f879edd104a7520d9eab'),('amu','csamu','iqubal','222','Iqubal Ahmad','What is your pet name?','iqubal','dept-admin','1ecc67aac81ca79635ff2a046303bb15'),('jmi','cs','6666','6666','Asif Iqubal','@',NULL,'dept-staff','17435531c34d44f623e3840c11f5970e'),('amu','amu','aasim','admin.amu','Aasim Zafar','What is your pet name?','kk','insti-admin','3f6ea67e781aa476e8638df6d5877447'),('avlin','avlin','auengg','admin.avlin','avi admin','What is your school name?','auengg','insti-admin','0192023a7bbd73250516f069df18b500'),('bbzhcet','bbzhcet','bbzhcet','admin.bbzhcet','Asif fareed Siddiqui','What is your school name?','GIC','insti-admin','7e0deb8853363a84629ba98fbaa29c55'),('dei','dei','prem','admin.dei','Prem Sewak Sudhish','What is your pet name?','prem','insti-admin','f0c17eb77a937d973f1625f0441d02b6'),('iitk','iitk','ynsingh','admin.iitk','Yatindra Nath Singh','What is your pet name?','ynsingh','insti-admin','8a9b1c308adbdefe4c47d62bf861f49d'),('iu','iu','u','admin.iu','i i','@',NULL,'insti-admin',''),('jmi','jmi','azim','admin.jmi','muzaffar azim','What is your school name?','jmi','insti-admin','38471924c9a7bd52a10d5e83756c9fc2'),('libms','libms','superadmin','admin.libms','superadmin',NULL,NULL,'superadmin','cb1410590d830d574e61fd941c4c43e1'),('rvce','rvce','renukaprasadb','admin.rvce','subramanya kn','What is your pet name?','renu','insti-admin','46eed70681a9a156462ae05b29a6914e'),('scc','scc','murtaza','admin.scc','Murtaza Ali','What is your pet name?','murtaza','insti-admin','129e3011b32e8c12fd23475e3106ff6b'),('tt','tt','kk','admin.tt','kk kk','@',NULL,'insti-admin',''),('yy','yy','k','admin.yy','k kjkh','What is your pet name?','kk','insti-admin','05f7d7a7d57cd9ee7934aff0af35105a');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `logs` (
  `sno` int(5) NOT NULL auto_increment,
  `user_id` varchar(100) default NULL,
  `date` varchar(100) default NULL,
  `time` varchar(100) default NULL,
  `classname` varchar(100) default NULL,
  `url` varchar(100) default NULL,
  `action_message` varchar(100) default NULL,
  `action_result` varchar(100) default NULL,
  `library_id` varchar(20) default NULL,
  `sublibrary_id` varchar(20) default NULL,
  `username` varchar(200) default NULL,
  `role` varchar(50) default NULL,
  PRIMARY KEY  (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,'superadmin','2011-09-13 14:32:38',NULL,NULL,'/LibMS/logout.do',NULL,NULL,'libms','libms','superadmin','superadmin'),(2,'superadmin','2011-09-13 14:32:48',NULL,NULL,'/LibMS/superadmin.do',NULL,NULL,'libms','libms','superadmin','superadmin'),(3,'superadmin','2011-09-13 14:32:50',NULL,NULL,'/LibMS/admin/search_log.jsp',NULL,NULL,'libms','libms','superadmin','superadmin'),(4,'superadmin','2011-09-13 14:32:50',NULL,NULL,'/LibMS/admin/search_log.do',NULL,NULL,'libms','libms','superadmin','superadmin'),(5,'superadmin','2011-09-13 14:33:02',NULL,NULL,'/LibMS/logout.do',NULL,NULL,'libms','libms','superadmin','superadmin'),(6,'','2011-09-13','1315905300420','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(7,'','2011-09-13','1315906237380','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(8,'','2011-09-13','1315906567834','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(9,'','2011-09-13','1315906645294','com.myapp.struts.LoginAction','/LibMS','','Invalid UserName or password',NULL,NULL,NULL,NULL),(10,'','2011-09-13','1315906651906','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(250,'','2011-09-14','1315958614762','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(251,'','2011-09-14','1315959088802','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(252,'','2011-09-14','1315959435241','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(253,'','2011-09-14','1315959468253','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(254,'','2011-09-14','1315962542861','com.myapp.struts.LoginAction','/LibMS','','Invalid UserName or password',NULL,NULL,NULL,NULL),(255,'','2011-09-14','1315962551192','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','libms','libms',NULL,NULL),(256,'','2011-09-14','1315962570491','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','libms','libms',NULL,NULL),(257,'','2011-09-14','1315962604255','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL),(258,'','2011-09-14','1315963015081','com.myapp.struts.LoginAction','/LibMS','','Successfully Login','jmi','jmi',NULL,NULL);
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logsetting`
--

DROP TABLE IF EXISTS `logsetting`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `logsetting` (
  `sno` int(11) NOT NULL auto_increment,
  `p1` varchar(100) default NULL,
  `p2` varchar(100) default NULL,
  `p3` varchar(100) default NULL,
  `p4` varchar(100) default NULL,
  `p5` varchar(100) default NULL,
  `p6` varchar(100) default NULL,
  `p7` varchar(100) default NULL,
  `p8` varchar(100) default NULL,
  PRIMARY KEY  (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `logsetting`
--

LOCK TABLES `logsetting` WRITE;
/*!40000 ALTER TABLE `logsetting` DISABLE KEYS */;
INSERT INTO `logsetting` VALUES (6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `logsetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL default '0',
  `library_id` varchar(20) NOT NULL default '',
  `subject` varchar(59) default NULL,
  `message` varchar(198) default NULL,
  PRIMARY KEY  (`notice_id`,`library_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `notices` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `notice_id` varchar(50) NOT NULL,
  `subject` varchar(200) default NULL,
  `detail` text,
  `date` varchar(15) default NULL,
  `sot` varchar(5) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`,`notice_id`),
  CONSTRAINT `notices_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `notices`
--

LOCK TABLES `notices` WRITE;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
INSERT INTO `notices` VALUES ('amu','amu','a','aaaa','dgdfgdzfdsfvdsfvdsfsdbds d ds d d ds d \r\n dd d','2011-4-16',NULL),('iitk','iitk','1','Submit Dues','All due should be submitted by 25th April 2011.','2011-4-18',NULL);
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `privilege` (
  `library_id` varchar(20) NOT NULL default '',
  `sublibrary_id` varchar(20) default NULL,
  `staff_id` varchar(100) NOT NULL,
  `acquisition` varchar(5) default 'true',
  `cataloguing` varchar(5) default 'true',
  `circulation` varchar(5) default 'true',
  `serial` varchar(5) default 'true',
  `administrator` varchar(5) default 'true',
  `system_setup` varchar(5) default 'true',
  `utilities` varchar(5) default 'true',
  `opac` varchar(5) default 'true',
  PRIMARY KEY  (`staff_id`,`library_id`),
  CONSTRAINT `login_ibfk_3` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES ('amu','amu','111','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','111','true','true','false','true','true','true','true','true'),('dei','dei','111','false','false','false','false','false','false','false','false'),('jmi','jmi','111','false','false','false','false','true','true','true','false'),('yy','yy','111','false','true','true','true','true','true','true','true'),('amu','csamu','222','true','false','false','true','false','false','false','false'),('jmi','cs','6666','true','false','true','true','true','true','true','true'),('amu','amu','admin.amu','false','false','false','false','false','false','false','false'),('avlin','avlin','admin.avlin','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','admin.bbzhcet','false','false','false','false','false','false','false','false'),('dei','dei','admin.dei','false','false','false','false','false','false','false','false'),('iitk','iitk','admin.iitk','false','false','false','false','false','false','false','false'),('iu','iu','admin.iu','false','false','false','false','false','false','false','false'),('jmi','jmi','admin.jmi','false','false','false','false','false','false','false','false'),('rvce','rvce','admin.rvce','false','false','false','false','false','false','false','false'),('scc','scc','admin.scc','false','false','false','false','false','false','false','false'),('tt','tt','admin.tt','false','false','false','false','false','false','false','false'),('yy','yy','admin.yy','false','false','false','false','false','false','false','false');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationlist`
--

DROP TABLE IF EXISTS `reservationlist`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `reservationlist` (
  `request_id` varchar(20) NOT NULL default '',
  `library_id` varchar(100) NOT NULL default '',
  `sublibrary_id` varchar(20) NOT NULL default '',
  `memid` varchar(70) NOT NULL default '',
  `accessionno` varchar(50) default NULL,
  `card_id` varchar(50) default NULL,
  `title` varchar(100) default NULL,
  `category` varchar(25) default NULL,
  `author` varchar(100) default NULL,
  `isbn` varchar(200) default NULL,
  `callno` varchar(50) default NULL,
  `edition` varchar(25) default NULL,
  `volume` varchar(25) default NULL,
  `publication` varchar(50) default NULL,
  `remark` varchar(100) default NULL,
  `request_date` varchar(15) default NULL,
  `status` varchar(10) default NULL,
  `issn` varchar(20) default NULL,
  `language` varchar(20) default NULL,
  `pub_year` varchar(15) default NULL,
  PRIMARY KEY  (`request_id`,`library_id`,`sublibrary_id`,`memid`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `reservationlist_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `reservationlist`
--

LOCK TABLES `reservationlist` WRITE;
/*!40000 ALTER TABLE `reservationlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservationlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ser_privilege`
--

DROP TABLE IF EXISTS `ser_privilege`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ser_privilege` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `staff_id` varchar(100) NOT NULL,
  `ser_401` varchar(5) default 'true',
  `ser_402` varchar(5) default 'true',
  `ser_403` varchar(5) default 'true',
  `ser_404` varchar(5) default 'true',
  `ser_405` varchar(5) default 'true',
  `ser_406` varchar(5) default 'true',
  `ser_407` varchar(5) default 'true',
  `ser_408` varchar(5) default 'true',
  `ser_409` varchar(5) default 'true',
  `ser_410` varchar(5) default 'true',
  `ser_411` varchar(5) default 'true',
  `ser_412` varchar(5) default 'true',
  `ser_413` varchar(5) default 'true',
  `ser_414` varchar(5) default 'true',
  `ser_415` varchar(5) default 'true',
  `ser_416` varchar(5) default 'true',
  `ser_417` varchar(5) default 'true',
  `ser_418` varchar(5) default 'true',
  `ser_419` varchar(5) default 'true',
  `ser_420` varchar(5) default 'true',
  `ser_421` varchar(5) default 'true',
  `ser_422` varchar(5) default 'true',
  `ser_423` varchar(5) default 'true',
  `ser_424` varchar(5) default 'true',
  `ser_425` varchar(5) default 'true',
  `ser_426` varchar(5) default 'true',
  `ser_427` varchar(5) default 'true',
  `ser_428` varchar(5) default 'true',
  `ser_429` varchar(5) default 'true',
  `ser_430` varchar(5) default 'true',
  `ser_431` varchar(5) default 'true',
  `ser_432` varchar(5) default 'true',
  `ser_433` varchar(5) default 'true',
  `ser_434` varchar(5) default 'true',
  `ser_435` varchar(5) default 'true',
  `ser_436` varchar(5) default 'true',
  `ser_437` varchar(5) default 'true',
  `ser_438` varchar(5) default 'true',
  `ser_439` varchar(5) default 'true',
  `ser_440` varchar(5) default 'true',
  `ser_441` varchar(5) default 'true',
  `ser_442` varchar(5) default 'true',
  `ser_443` varchar(5) default 'true',
  `ser_444` varchar(5) default 'true',
  `ser_445` varchar(5) default 'true',
  `ser_446` varchar(5) default 'true',
  `ser_447` varchar(5) default 'true',
  `ser_448` varchar(5) default 'true',
  `ser_449` varchar(5) default 'true',
  `ser_450` varchar(5) default 'true',
  `ser_451` varchar(5) default 'true',
  `ser_452` varchar(5) default 'true',
  `ser_453` varchar(5) default 'true',
  `ser_454` varchar(5) default 'true',
  `ser_455` varchar(5) default 'true',
  `ser_456` varchar(5) default 'true',
  `ser_457` varchar(5) default 'true',
  `ser_458` varchar(5) default 'true',
  `ser_459` varchar(5) default 'true',
  `ser_460` varchar(5) default 'true',
  `ser_461` varchar(5) default 'true',
  `ser_462` varchar(5) default 'true',
  `ser_463` varchar(5) default 'true',
  `ser_464` varchar(5) default 'true',
  `ser_465` varchar(5) default 'true',
  `ser_466` varchar(5) default 'true',
  `ser_467` varchar(5) default 'true',
  `ser_468` varchar(5) default 'true',
  `ser_469` varchar(5) default 'true',
  `ser_470` varchar(5) default 'true',
  `ser_471` varchar(5) default 'true',
  `ser_472` varchar(5) default 'true',
  `ser_473` varchar(5) default 'true',
  `ser_474` varchar(5) default 'true',
  `ser_475` varchar(5) default 'true',
  `ser_476` varchar(5) default 'true',
  `ser_477` varchar(5) default 'true',
  `ser_478` varchar(5) default 'true',
  `ser_479` varchar(5) default 'true',
  `ser_480` varchar(5) default 'true',
  `ser_481` varchar(5) default 'true',
  `ser_482` varchar(5) default 'true',
  `ser_483` varchar(5) default 'true',
  `ser_484` varchar(5) default 'true',
  `ser_485` varchar(5) default 'true',
  `ser_486` varchar(5) default 'true',
  `ser_487` varchar(5) default 'true',
  `ser_488` varchar(5) default 'true',
  `ser_489` varchar(5) default 'true',
  `ser_490` varchar(5) default 'true',
  `ser_491` varchar(5) default 'true',
  `ser_492` varchar(5) default 'true',
  `ser_493` varchar(5) default 'true',
  `ser_494` varchar(5) default 'true',
  `ser_495` varchar(5) default 'true',
  `ser_496` varchar(5) default 'true',
  `ser_497` varchar(5) default 'true',
  `ser_498` varchar(5) default 'true',
  `ser_499` varchar(5) default 'true',
  PRIMARY KEY  (`staff_id`,`library_id`),
  CONSTRAINT `login_ibfk_7` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ser_privilege`
--

LOCK TABLES `ser_privilege` WRITE;
/*!40000 ALTER TABLE `ser_privilege` DISABLE KEYS */;
INSERT INTO `ser_privilege` VALUES ('amu','amu','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('dei','dei','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','111','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','111','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','csamu','222','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('jmi','cs','6666','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true'),('amu','amu','admin.amu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('avlin','avlin','admin.avlin','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('bbzhcet','bbzhcet','admin.bbzhcet','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('dei','dei','admin.dei','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iitk','iitk','admin.iitk','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('iu','iu','admin.iu','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('jmi','jmi','admin.jmi','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('rvce','rvce','admin.rvce','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('scc','scc','admin.scc','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('tt','tt','admin.tt','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'),('yy','yy','admin.yy','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false');
/*!40000 ALTER TABLE `ser_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_detail`
--

DROP TABLE IF EXISTS `staff_detail`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `staff_detail` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `staff_id` varchar(100) NOT NULL,
  `title` varchar(5) default NULL,
  `first_name` varchar(20) default NULL,
  `last_name` varchar(20) default NULL,
  `contact_no` varchar(20) default NULL,
  `mobile_no` varchar(20) default NULL,
  `email_id` varchar(100) default NULL,
  `date_joining` date default NULL,
  `date_releaving` date default NULL,
  `father_name` varchar(30) default NULL,
  `date_of_birth` date default NULL,
  `gender` varchar(8) default NULL,
  `address1` varchar(50) default NULL,
  `city1` varchar(20) default NULL,
  `state1` varchar(20) default NULL,
  `country1` varchar(20) default NULL,
  `zip1` varchar(20) default NULL,
  `address2` varchar(50) default NULL,
  `city2` varchar(20) default NULL,
  `state2` varchar(20) default NULL,
  `country2` varchar(20) default NULL,
  `zip2` varchar(20) default NULL,
  `staff_image` longblob,
  PRIMARY KEY  (`staff_id`,`library_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `staff_detail_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `staff_detail`
--

LOCK TABLES `staff_detail` WRITE;
/*!40000 ALTER TABLE `staff_detail` DISABLE KEYS */;
INSERT INTO `staff_detail` VALUES ('amu','amu','111','mr','Kedar','Kumar','','','kedar@gmail.com',NULL,NULL,'','1970-04-10','male','ADM Compound','Aligarh','UP','India','','ADM Compound','Aligarh','UP','India','',NULL),('bbzhcet','bbzhcet','111','mr','Syed Kamal','Ahmad','','','skamal90@yahoo.co.in','2011-05-04','2011-07-04','',NULL,'male','4/919, Iqbal Street, Jamalpur','Aligarh','Uttar Pradesh','India','202002','4/919, Iqbal Street, Jamalpur','Aligarh','Uttar Pradesh','India','202002',NULL),('dei','dei','111','mr','Arush','Kumar','','','arush@gmail.com',NULL,NULL,'',NULL,'male','DayalBagh','Agra','UP','India','','','','','','',NULL),('jmi','jmi','111',NULL,'Kedar','kumar','','','kedar9002@gmail.com',NULL,NULL,'',NULL,'Select','ADM','Aliagrh','UP','India','','','','','','',NULL),('yy','yy','111',NULL,'gsg','dgsd','','','kedar9002@gmail.com',NULL,NULL,'',NULL,NULL,'fghdf','dfhdf','fhdf','dfhdf','','','','','','',NULL),('jmi','jmi','112','mr','kk','kk','','','kk@gmail.com',NULL,NULL,'',NULL,NULL,'ss','aa','bb','ii','','','','','','',NULL),('amu','csamu','222','mr','Iqubal','Ahmad','','','iqubal@gmail.com',NULL,NULL,'',NULL,NULL,'ADM Compound','Aligarh','U.P.','India','','ADM Compound','Aligarh','U.P.','India','',NULL),('jmi','cs','222',NULL,'Kedar','kumar','','','kedar9002@gmail.com',NULL,NULL,'',NULL,NULL,'ADM','Aliagrh','UP','India','','','','','','',NULL),('jmi','cs','6666','mr','Asif','Iqubal','','','asif633@gmail.com',NULL,NULL,'',NULL,'male','Asif','Aligarh','UP','India','','','','','','',NULL),('amu','amu','admin.amu','Dr','Aasim','Zafar','','9319098461','aasimzafar@gmail.com',NULL,NULL,'',NULL,'male','AMU','Aligarh','U.P.','India','202002','','','','','',NULL),('avlin','avlin','admin.avlin','','avi','admin','','9842057738','admin@auengg.com',NULL,NULL,NULL,NULL,'male',NULL,'Coimbatore','TmailNadu','India','-641 108',NULL,NULL,NULL,NULL,NULL,NULL),('bbzhcet','bbzhcet','admin.bbzhcet','Mr.','Asif fareed','Siddiqui','0','9412509849','asiffareedsiddiqui@yahoo.com',NULL,NULL,NULL,NULL,'male',NULL,'Aligarh','Uttar Pradesh','India','202002',NULL,NULL,NULL,NULL,NULL,NULL),('dei','dei','admin.dei','Mr.','Prem Sewak','Sudhish','','09927400888','prem@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'Agra','U.P.','India','202002',NULL,NULL,NULL,NULL,NULL,NULL),('iitk','iitk','admin.iitk','Prof.','Yatindra Nath','Singh','','9223432451','ynsingh69@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'Kanpur','U.P.','India','',NULL,NULL,NULL,NULL,NULL,NULL),('iu','iu','admin.iu','i','i','i','hdfh','645645','kedar9002@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'i','i','i','i',NULL,NULL,NULL,NULL,NULL,NULL),('jmi','jmi','admin.jmi','','muzaffar','azim','','098100','kedar9002@gmail.com',NULL,NULL,'',NULL,'male','ADM','New Delhi','Delhi','India','','','','','','',NULL),('libms','libms','admin.libms',NULL,'superadmin',NULL,NULL,NULL,NULL,'2011-04-16','2011-04-16',NULL,'2011-04-16',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('rvce','rvce','admin.rvce','','subramanya','kn','6717 8020','9901945674','renukaprasadb@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'bengaluru','Karnataka','India','560059',NULL,NULL,NULL,NULL,NULL,NULL),('scc','scc','admin.scc','','Murtaza','Ali','','09422155049','murtaza@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'Amravati','Maharashtra','India','',NULL,NULL,NULL,NULL,NULL,NULL),('tt','tt','admin.tt','','kk','kk','','56754745','kedar9002@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'fghfg','hfghfg','gfhfg','fghfg',NULL,NULL,NULL,NULL,NULL,NULL),('yy','yy','admin.yy','hk','k','kjkh','kj','64564564','kedar9002@gmail.com',NULL,NULL,NULL,NULL,'male',NULL,'kh','khk','kkh','kk',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `staff_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_employee_type`
--

DROP TABLE IF EXISTS `sub_employee_type`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `sub_employee_type` (
  `library_id` varchar(20) NOT NULL,
  `emptype_id` varchar(20) NOT NULL,
  `sub_emptype_id` varchar(20) NOT NULL,
  `sub_emptype_full_name` varchar(100) default NULL,
  `no_of_issueable_book` int(11) default NULL,
  PRIMARY KEY  (`library_id`,`emptype_id`,`sub_emptype_id`),
  CONSTRAINT `sub_employee_type_ibfk_1` FOREIGN KEY (`library_id`, `emptype_id`) REFERENCES `employee_type` (`library_id`, `emptype_id`),
  CONSTRAINT `sub_employee_type_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sub_employee_type`
--

LOCK TABLES `sub_employee_type` WRITE;
/*!40000 ALTER TABLE `sub_employee_type` DISABLE KEYS */;
INSERT INTO `sub_employee_type` VALUES ('amu','nt','l','Library Staff',5),('amu','nt','nl','Non-Library Staff',2),('amu','t','l','Lecturer',10),('amu','t','prof','Professor',12),('bbzhcet','s','PG','Post Graduate',3),('dei','st','pg','Post Graduate',10),('dei','st','ug','Under Graduate',2),('iitk','nt','cl','Clerk',2),('iitk','s','pg','Post Graduate',1),('iitk','s','ug','Under Graduate',2),('iitk','t','prof','Professor',1),('jmi','s','pg','Post Graduate',7),('jmi','s','ug','Under Graduate',5),('jmi','st','pg','post grad',1),('scc','s','ug','Undergraduate',4),('yy','st','pg','post grad',5);
/*!40000 ALTER TABLE `sub_employee_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_library`
--

DROP TABLE IF EXISTS `sub_library`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `sub_library` (
  `sublibrary_id` varchar(20) NOT NULL,
  `sublib_name` varchar(100) NOT NULL,
  `library_id` varchar(20) NOT NULL default '',
  `faculty_name` varchar(100) default NULL,
  `dept_address` varchar(200) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`),
  CONSTRAINT `sub_library_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sub_library`
--

LOCK TABLES `sub_library` WRITE;
/*!40000 ALTER TABLE `sub_library` DISABLE KEYS */;
INSERT INTO `sub_library` VALUES ('amu','Main Library','amu',NULL,NULL),('csamu','cs','amu','sc','AMU, Aligarh'),('avlin','Main Library','avlin',NULL,NULL),('bbzhcet','Main Library','bbzhcet',NULL,NULL),('dei','Main Library','dei',NULL,NULL),('cs','Computer Science','iitk','cs','Main Building'),('iitk','Main Library','iitk',NULL,NULL),('iu','Main Library','iu',NULL,NULL),('cqs','asas','jmi','arts','AMU, Aligarh2'),('cs','cs','jmi','science','computer science complex'),('cs1','his','jmi','arts','univer'),('jmi','Main Library','jmi',NULL,NULL),('seminar-cqs','Computer Science','jmi','arts','Lecture Building 2'),('wf1','Forest Convservation','jmi','wl','univer'),('libms','libms','libms',NULL,NULL),('rvce','Main Library','rvce',NULL,NULL),('phy','Physics','scc','sc','Main Building'),('scc','Main Library','scc',NULL,NULL),('tt','Main Library','tt',NULL,NULL),('yy','Main Library','yy',NULL,NULL);
/*!40000 ALTER TABLE `sub_library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp_excell_import`
--

DROP TABLE IF EXISTS `temp_excell_import`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `temp_excell_import` (
  `sno` int(11) NOT NULL auto_increment,
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `document_type` varchar(20) default NULL,
  `book_type` varchar(20) default NULL,
  `accession_type` varchar(20) default NULL,
  `date_acquired` varchar(20) default NULL,
  `title` varchar(200) default NULL,
  `subtitle` varchar(200) default NULL,
  `alt_title` varchar(200) default NULL,
  `statement_responsibility` varchar(200) default NULL,
  `main_entry` varchar(200) default NULL,
  `added_entry` varchar(200) default NULL,
  `added_entry1` varchar(200) default NULL,
  `added_entry2` varchar(200) default NULL,
  `added_entry3` varchar(200) default NULL,
  `publisher_name` varchar(200) default NULL,
  `publication_place` varchar(200) default NULL,
  `publishing_year` varchar(20) default NULL,
  `call_no` varchar(30) default NULL,
  `parts_no` varchar(20) default NULL,
  `subject` varchar(200) default NULL,
  `entry_language` varchar(200) default NULL,
  `isbn10` varchar(20) default NULL,
  `isbn13` varchar(20) default NULL,
  `LCC_no` varchar(30) default NULL,
  `edition` varchar(20) default NULL,
  `no_of_copies` varchar(20) default NULL,
  `author_name` varchar(200) default NULL,
  `guide_name` varchar(200) default NULL,
  `university_faculty` varchar(200) default NULL,
  `degree` varchar(200) default NULL,
  `submitted_on` varchar(20) default NULL,
  `acceptance_year` varchar(20) default NULL,
  `collation1` varchar(20) default NULL,
  `notes` varchar(2000) default NULL,
  `abstract` varchar(2000) default NULL,
  `address` varchar(200) default NULL,
  `state1` varchar(100) default NULL,
  `country` varchar(100) default NULL,
  `email` varchar(200) default NULL,
  `frmr_frq` varchar(20) default NULL,
  `frq_date` varchar(20) default NULL,
  `issn` varchar(20) default NULL,
  `volume_location` varchar(20) default NULL,
  `production_year` varchar(20) default NULL,
  `source1` varchar(20) default NULL,
  `duration` varchar(20) default NULL,
  `series` varchar(1000) default NULL,
  `type_of_disc` varchar(20) default NULL,
  `file_type` varchar(20) default NULL,
  `accession_no` varchar(40) default NULL,
  `record_no` varchar(20) default NULL,
  `volume_no` varchar(20) default NULL,
  `location` varchar(200) default NULL,
  `shelving_location` varchar(200) default NULL,
  `index_no` varchar(20) default NULL,
  `no_of_pages` varchar(20) default NULL,
  `physical_width` varchar(20) default NULL,
  `physical_form` varchar(200) default NULL,
  `physical_description` varchar(200) default NULL,
  `colour` varchar(20) default NULL,
  `status` varchar(50) default NULL,
  `bind_type` varchar(20) default NULL,
  PRIMARY KEY  (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `temp_excell_import`
--

LOCK TABLES `temp_excell_import` WRITE;
/*!40000 ALTER TABLE `temp_excell_import` DISABLE KEYS */;
INSERT INTO `temp_excell_import` VALUES (25,'jmi,','jmi,','Book,','t,','<NULL>,','<NULL>,','champak,','<NULL>,','<NULL>,','champak,','<NULL>,',NULL,'<NULL>,','<NULL>,','<NULL>,',NULL,'<NULL>,','2011,','1,','2,','<NULL>,','HI,','1088,','<NULL>,','<NULL>,','<NULL>,','2,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,',NULL,'<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','2011,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'avaliable',NULL),(26,'jmi,','jmi,','Book,','t,','<NULL>,','<NULL>,','c++,','<NULL>,','<NULL>,','kedar,','<NULL>,',NULL,'<NULL>,','<NULL>,','<NULL>,',NULL,'<NULL>,','2000,','2,','1,','<NULL>,','HI,','5600,','<NULL>,','<NULL>,','<NULL>,','2,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,',NULL,'<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','2000,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'avaliable',NULL),(27,'jmi,','jmi,','Book,','t,','<NULL>,','<NULL>,','c,','<NULL>,','<NULL>,','c,','<NULL>,',NULL,'<NULL>,','<NULL>,','<NULL>,',NULL,'<NULL>,','1999,','3,','3,','<NULL>,','HI,','5544,','<NULL>,','<NULL>,','<NULL>,','1,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,',NULL,'<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','1999,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','<NULL>,','3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'avaliable',NULL),(46,'jmi','jmi','Book','t',NULL,'2011-09-06','champak','','','champak','champak','','','','','','','2011','1','5','','HI','1088','','','','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2011',NULL,NULL,'',NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'avaliable',NULL),(47,'jmi','jmi','Book','t',NULL,'2011-09-06','c++','','','kedar','kedar','','','','','','','2000','2','8','','HI','5600','','','','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1900',NULL,NULL,'',NULL,NULL,'2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'avaliable',NULL),(48,'jmi','jmi','Book','t',NULL,'2011-09-06','c','','','c','c','','','','','','','1999','3','9','','HI','5544','','','','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1999',NULL,NULL,'',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'avaliable',NULL);
/*!40000 ALTER TABLE `temp_excell_import` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-09-14 17:34:53
