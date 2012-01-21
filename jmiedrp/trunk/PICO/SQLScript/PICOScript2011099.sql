CREATE DATABASE  IF NOT EXISTS `pico` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pico`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: pico
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

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `erpm_purchasechallan_detail`
--

DROP TABLE IF EXISTS `erpm_purchasechallan_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_purchasechallan_detail` (
  `PCD_PCD_ID` int(10) unsigned NOT NULL auto_increment,
  `PCD_PCMaster_ID` int(11) unsigned NOT NULL,
  `PCD_ItemMaster_ID` int(10) unsigned default NULL,
  `PCD_Recv_Quantity` decimal(8,2) unsigned default '0.00',
  PRIMARY KEY  (`PCD_PCD_ID`),
  UNIQUE KEY `PCD_PCMID_ITEMID_UNIQUE` (`PCD_PCMaster_ID`,`PCD_ItemMaster_ID`),
  KEY `FK_PCD_PCMaster_ID` (`PCD_PCMaster_ID`),
  KEY `FK_PCD_ItemMaster_ID` (`PCD_ItemMaster_ID`),
  CONSTRAINT `FK_PCD_ItemMaster_ID` FOREIGN KEY (`PCD_ItemMaster_ID`) REFERENCES `erpm_item_master` (`ERPMIM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PCD_PCMaster_ID` FOREIGN KEY (`PCD_PCMaster_ID`) REFERENCES `erpm_purchasechallan_master` (`PCM_PCM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_purchasechallan_detail`
--

LOCK TABLES `erpm_purchasechallan_detail` WRITE;
/*!40000 ALTER TABLE `erpm_purchasechallan_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_purchasechallan_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_gen_master`
--

DROP TABLE IF EXISTS `erpm_gen_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_gen_master` (
  `ERPMGM_EGM_ID` int(10) unsigned NOT NULL auto_increment,
  `ERPMGM_EGM_TYPE` smallint(5) unsigned NOT NULL,
  `ERPMGM_EGM_DESC` varchar(50) NOT NULL,
  PRIMARY KEY  (`ERPMGM_EGM_ID`),
  UNIQUE KEY `UNIQUE_ERPMGM_EGM_DESC_ERPMGM_EGM_TYPE` (`ERPMGM_EGM_DESC`,`ERPMGM_EGM_TYPE`),
  KEY `ERPMGM_EGM_TYPE_ERPMC_GEN_TYPE` (`ERPMGM_EGM_TYPE`),
  CONSTRAINT `FK_ERPMGM_EGM_TYPE_ERPMGC_ERPMGC_ID` FOREIGN KEY (`ERPMGM_EGM_TYPE`) REFERENCES `erpm_gen_ctrl` (`ERPMGC_GEN_TYPE`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_gen_master`
--

LOCK TABLES `erpm_gen_master` WRITE;
/*!40000 ALTER TABLE `erpm_gen_master` DISABLE KEYS */;
INSERT INTO `erpm_gen_master` VALUES (34,5,'Administrative Offices'),(10,2,'Agents/Distributors'),(43,8,'Apporved'),(24,4,'Central University'),(32,5,'Centre'),(33,5,'College'),(5,1,'Cooperative Sopciety:Society Rules and Byelaws'),(48,9,'Date of Delivery'),(49,9,'Date of installation'),(40,3,'dd'),(27,4,'Deemed University'),(45,6,'Dollar'),(31,5,'Faculty'),(11,2,'Foreign Manufacturer'),(55,11,'Form 16'),(54,11,'Form 31'),(52,11,'Form C'),(50,9,'Given Date'),(1,1,'Govt. of India Undertakings'),(41,7,'HOD'),(29,4,'Indian Institute of Management'),(28,4,'Indian Institute of Technology'),(51,10,'LC'),(16,3,'Licences'),(21,3,'Licences+Media'),(53,10,'Live Wire'),(3,1,'Ltd Company:Memorandum & Atrticles of Association'),(9,2,'Manufacturers'),(15,3,'Meters'),(39,4,'Other Institution'),(35,1,'Others'),(6,1,'Partnership Firm'),(42,8,'Pending'),(14,3,'Pieces'),(58,12,'PO Delivery Terms'),(56,12,'PO Payment Terms'),(59,12,'PO Testing Terms'),(57,12,'PO Warranty Clause'),(7,1,'Proprietoryship Firm'),(4,1,'Pvt. Company:Memorandum & Atrticles of Association'),(38,7,'Reader'),(44,8,'Rejected'),(47,6,'Riyal'),(46,6,'Rupees'),(30,5,'School'),(2,1,'State Govt. Undertaking'),(26,4,'State University'),(12,2,'Stockists'),(13,2,'Supplier of imported goods'),(37,1,'yyyy');
/*!40000 ALTER TABLE `erpm_gen_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_item_rate`
--

DROP TABLE IF EXISTS `erpm_item_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_item_rate` (
  `IR_Item_Rate_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IR_IM_ID` smallint(5) unsigned NOT NULL,
  `IR_Supplier_ID` int(10) unsigned NOT NULL,
  `IR_Item_ID` int(10) unsigned NOT NULL,
  `IR_Currency_ID` int(10) unsigned NOT NULL,
  `IR_Warranty_Months` int(3) unsigned DEFAULT NULL,
  `IR_Warranty_Starts_From_ID` int(10) unsigned DEFAULT NULL,
  `IR_Warranty_Clause` varchar(100) DEFAULT NULL,
  `IRD_WEF_Date` date NOT NULL,
  `IRD_WET_Date` date NOT NULL,
  `IRD_Rate` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`IR_Item_Rate_Id`),
  UNIQUE KEY `UNIQUE_ITEM_ID_AND_VALIDITY_PERIOD` (`IR_Item_ID`,`IR_Supplier_ID`,`IRD_WEF_Date`,`IRD_WET_Date`),
  KEY `FK_IR_IM_ID` (`IR_IM_ID`),
  KEY `FK_IR_Supplier_ID` (`IR_Supplier_ID`),
  KEY `FK_IR_Item_ID` (`IR_Item_ID`),
  KEY `FK_IR_Currency_ID` (`IR_Currency_ID`),
  KEY `FK_IR_WarrantyStartsfrom_ID` (`IR_Warranty_Starts_From_ID`),
  CONSTRAINT `FK_IR_Item_ID_ERPM_Item_Master` FOREIGN KEY (`IR_Item_ID`) REFERENCES `erpm_item_master` (`ERPMIM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Institution_ImID` FOREIGN KEY (`IR_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_IR_Currency_ID_GeneralMaster` FOREIGN KEY (`IR_Currency_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_IR_WarrantyStartsfrom_ID` FOREIGN KEY (`IR_Warranty_Starts_From_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Supplier_ID` FOREIGN KEY (`IR_Supplier_ID`) REFERENCES `suppliermaster` (`SM_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_item_rate`
--

LOCK TABLES `erpm_item_rate` WRITE;
/*!40000 ALTER TABLE `erpm_item_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_item_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_general_terms`
--

DROP TABLE IF EXISTS `erpm_general_terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_general_terms` (
  `GT_GTID` int(10) unsigned NOT NULL auto_increment,
  `GT_IM_ID` smallint(5) unsigned NOT NULL,
  `GT_EGM_ID` int(10) unsigned NOT NULL,
  `GT_TERMS_DESCRIPTION` varchar(500) default NULL,
  PRIMARY KEY  (`GT_GTID`),
  UNIQUE KEY `GT_IMT_ID_UNIQUE` (`GT_IM_ID`,`GT_EGM_ID`),
  KEY `FK_GT_IM` (`GT_IM_ID`),
  KEY `FK_GT_EGM` (`GT_EGM_ID`),
  CONSTRAINT `FK_GT_EGM` FOREIGN KEY (`GT_EGM_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`),
  CONSTRAINT `FK_GT_IM` FOREIGN KEY (`GT_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_general_terms`
--

LOCK TABLES `erpm_general_terms` WRITE;
/*!40000 ALTER TABLE `erpm_general_terms` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_general_terms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_master`
--

DROP TABLE IF EXISTS `language_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language_master` (
  `LANG_ID` int(10) unsigned NOT NULL auto_increment,
  `LANG_NAME` varchar(100) default NULL,
  PRIMARY KEY  (`LANG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_master`
--

LOCK TABLES `language_master` WRITE;
/*!40000 ALTER TABLE `language_master` DISABLE KEYS */;
INSERT INTO `language_master` VALUES (1,'English'),(2,'German'),(3,'France'),(4,'Chinese');
/*!40000 ALTER TABLE `language_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_capital_category`
--

DROP TABLE IF EXISTS `erpm_capital_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_capital_category` (
  `ERPMCC_ID` int(11) unsigned NOT NULL auto_increment COMMENT '	',
  `ERMCC_Desc` varchar(50) NOT NULL,
  `ERPM_IM_ID` smallint(5) unsigned default NULL,
  PRIMARY KEY  (`ERPMCC_ID`),
  UNIQUE KEY `UNIQUE_ERPMCC_ERPM_IM_ID_ERPMCC_Desc` (`ERPM_IM_ID`,`ERMCC_Desc`),
  CONSTRAINT `FK_ERPM_IM_ID_IM_ID` FOREIGN KEY (`ERPM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_capital_category`
--

LOCK TABLES `erpm_capital_category` WRITE;
/*!40000 ALTER TABLE `erpm_capital_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_capital_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statemaster`
--

DROP TABLE IF EXISTS `statemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statemaster` (
  `State_ID` tinyint(4) unsigned NOT NULL auto_increment COMMENT 'Field to Store State ID',
  `State_Name` varchar(100) NOT NULL,
  `State_Type` set('Union Territory','State') default 'State',
  `state_country_id` tinyint(4) default NULL,
  PRIMARY KEY  (`State_ID`),
  KEY `FK_SM_state_country_id_CM_country_id` (`state_country_id`),
  CONSTRAINT `FK_SM_state_country_id_CM_country_id` FOREIGN KEY (`state_country_id`) REFERENCES `countrymaster` (`Country_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statemaster`
--

LOCK TABLES `statemaster` WRITE;
/*!40000 ALTER TABLE `statemaster` DISABLE KEYS */;
INSERT INTO `statemaster` VALUES (1,'Delhi','State',1),(2,'Uttar Pradesh','State',1),(3,'Madhya Pradesh','State',1),(4,'Bihar','State',1),(5,'Andhra Pradesh','State',1),(6,'Tamilnadu','State',1),(7,'Maharashtra','State',1),(8,'Gujrat','State',1),(9,'Jammu Kashmir','State',1),(10,'Haryana','State',1),(11,'Asam','State',1),(12,'West Bengal','State',1),(13,'Uttranchal','State',1),(14,'Arunachal Pradesh','State',1),(15,'Punjab','State',1),(16,'Rajasthan','State',1),(17,'Kerala','State',1),(18,'Karnataka','State',1),(19,'Manipur','State',1),(20,'Meghalaya','State',1),(21,'Tokyo','State',3),(22,'Beijing','State',2);
/*!40000 ALTER TABLE `statemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_item_category_master`
--

DROP TABLE IF EXISTS `erpm_item_category_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_item_category_master` (
  `ERPMICM_Item_ID` int(11) unsigned NOT NULL auto_increment COMMENT '		',
  `ERPMICM_Item_Level` smallint(6) NOT NULL COMMENT '						',
  `ERPMICM_Parent_Item_ID` int(11) unsigned default NULL,
  `ERPMICM_Cat_Desc` varchar(50) NOT NULL,
  `ERPMICM_IM_ID` smallint(5) unsigned default NULL,
  PRIMARY KEY  (`ERPMICM_Item_ID`),
  UNIQUE KEY `UNIQUE_ERPMICM_Desc_ERPMICM_IM_ID` (`ERPMICM_Cat_Desc`,`ERPMICM_IM_ID`),
  KEY `ERPMICM_Parent_Item_ID_ERPMICM_Item_ID` (`ERPMICM_Parent_Item_ID`),
  KEY `FK_ERPMICM_IM_ID_Institution_Master_IM_ID` (`ERPMICM_IM_ID`),
  CONSTRAINT `ERPMICM_Parent_Item_ID_ERPMICM_Item_ID` FOREIGN KEY (`ERPMICM_Parent_Item_ID`) REFERENCES `erpm_item_category_master` (`ERPMICM_Item_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMICM_IM_ID_Institution_Master_IM_ID` FOREIGN KEY (`ERPMICM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_item_category_master`
--

LOCK TABLES `erpm_item_category_master` WRITE;
/*!40000 ALTER TABLE `erpm_item_category_master` DISABLE KEYS */;
INSERT INTO `erpm_item_category_master` VALUES (1,1,NULL,'Non-Consumable',1),(2,1,NULL,'Consumable',1),(3,1,NULL,'Services',1),(4,2,1,'Computers ',1),(5,2,1,'Furniture',1),(6,2,1,'Machinery',1),(7,2,1,'Office Equipment',1),(8,2,1,'Stationary',1),(9,2,2,'Cartridges',1),(10,2,2,'Storage Media (CD/DVD/DAT)',1),(11,2,3,'Annual Maintenance Contract',1),(12,2,3,'Printing',1),(13,2,3,'Repair',1),(14,3,4,'Server',1),(16,3,11,'AMC of PCs',1),(17,3,4,'PC',1),(18,3,4,'Printer',1);
/*!40000 ALTER TABLE `erpm_item_category_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_message`
--

DROP TABLE IF EXISTS `user_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_message` (
  `um_id` int(10) unsigned NOT NULL auto_increment,
  `UM_FROM_ERPMU_ID` mediumint(9) unsigned default NULL,
  `UM_TO_ERPMU_ID` mediumint(9) unsigned default NULL,
  `UM_MESSAGE` varchar(100) default NULL,
  `um_action_name` varchar(200) default NULL,
  `UM_REQUEST_SUBMISSION_DATE` date default NULL,
  `um_status` int(10) unsigned default '42',
  `UM_REQ_TYPE` varchar(100) default NULL,
  `UM_REQ_TYPE_ID` smallint(5) unsigned default NULL,
  `UM_MSG_ID` int(10) unsigned default NULL,
  PRIMARY KEY  (`um_id`),
  KEY `FK_UM_FROM_ERPMU_IDERPMU_ID` (`UM_FROM_ERPMU_ID`),
  KEY `FK_UM_TO_ERPMU_IDERPMU_ID` (`UM_TO_ERPMU_ID`),
  KEY `FK_UM_STATUS_ERPMGM_EGM_ID` (`um_status`),
  CONSTRAINT `FK_UM_FROM_ERPMU_IDERPMU_ID` FOREIGN KEY (`UM_FROM_ERPMU_ID`) REFERENCES `erpmusers` (`ERPMU_ID`),
  CONSTRAINT `FK_UM_STATUS_ERPMGM_EGM_ID` FOREIGN KEY (`um_status`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_UM_TO_ERPMU_IDERPMU_ID` FOREIGN KEY (`UM_TO_ERPMU_ID`) REFERENCES `erpmusers` (`ERPMU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_message`
--

LOCK TABLES `user_message` WRITE;
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departmental_budget_allocation`
--

DROP TABLE IF EXISTS `departmental_budget_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departmental_budget_allocation` (
  `DBA_ID` mediumint(5) unsigned NOT NULL auto_increment,
  `DBA_IM_ID` smallint(3) unsigned NOT NULL,
  `DBA_SIM_ID` mediumint(3) unsigned NOT NULL,
  `DBA_DM_ID` mediumint(5) unsigned default NULL,
  `DBA_From_Date` date default NULL,
  `DBA_To_Date` date default NULL,
  `DBA_BHM_ID` smallint(4) unsigned default NULL,
  `DBA_Amount` int(11) default NULL,
  PRIMARY KEY  (`DBA_ID`),
  UNIQUE KEY `UNIQUE_DBA_DM_ID_DBA_From_Date_DBA_To_Date_DBA_BHM_ID` (`DBA_DM_ID`,`DBA_From_Date`,`DBA_To_Date`,`DBA_BHM_ID`),
  KEY `fk_DBA_IM_ID_IM_ID` (`DBA_IM_ID`),
  KEY `fk_DBA_SIM_ID_SIM_ID` (`DBA_SIM_ID`),
  KEY `fk_DBA_BHM_ID_BHM_ID` (`DBA_BHM_ID`),
  CONSTRAINT `fk_DBA_BHM_ID_BHM_ID` FOREIGN KEY (`DBA_BHM_ID`) REFERENCES `budgetheadmaster` (`BHM_ID`),
  CONSTRAINT `fk_DBA_DM_ID_DM_ID` FOREIGN KEY (`DBA_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`),
  CONSTRAINT `fk_DBA_IM_ID_IM_ID` FOREIGN KEY (`DBA_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`),
  CONSTRAINT `fk_DBA_SIM_ID_SIM_ID` FOREIGN KEY (`DBA_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmental_budget_allocation`
--

LOCK TABLES `departmental_budget_allocation` WRITE;
/*!40000 ALTER TABLE `departmental_budget_allocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `departmental_budget_allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmteam`
--

DROP TABLE IF EXISTS `erpmteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmteam` (
  `ERPMT_ID` tinyint(4) unsigned NOT NULL auto_increment,
  `ERPMT_Name` varchar(100) NOT NULL,
  `ERMT_PI_Name` varchar(50) NOT NULL,
  `ERPMT_COPI_Name` varchar(50) default NULL,
  `ERPMT_PI_EMail` varchar(50) NOT NULL,
  `ERPMT_COPI_EMail` varchar(50) default NULL,
  PRIMARY KEY  (`ERPMT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmteam`
--

LOCK TABLES `erpmteam` WRITE;
/*!40000 ALTER TABLE `erpmteam` DISABLE KEYS */;
INSERT INTO `erpmteam` VALUES (1,'Aligah Muslim University','Prof. Nisar Ahmad','Dr. Asim Zafar','nisar@amu',NULL),(2,'Amrita Vishwa Vidya Peetham','Mr. Raghu ','Ms. Prema','raghu@',NULL),(3,'IIT Kanpur','Dr. Y.N. Singh',NULL,'ynsungh@',NULL),(4,'IIT Roorkee','Prof. Padam Singh',NULL,'padamsingh@',NULL),(5,'Dayalbagh Educational Institute','Mr. Prem Sudhish',NULL,'prem@',NULL),(6,'Jamia Millia Islamia','Dr. S. Kazim Naqvi','Mr. Muzaffar Azim','sknaqvi@jmi.ac.in','mazim@jmi.ac.in'),(7,'SMVDU, Jammu','Ms. Sonika Gupta',NULL,'sonika@',NULL),(8,'IGNOU','Prof. Uma Kanjilal',NULL,'umak@',NULL);
/*!40000 ALTER TABLE `erpmteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplierregistrationcategory`
--

DROP TABLE IF EXISTS `supplierregistrationcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplierregistrationcategory` (
  `SRC_ID` tinyint(3) unsigned NOT NULL auto_increment,
  `SRC_Name` varchar(200) NOT NULL,
  `SRC_Remarks` varchar(200) default NULL,
  PRIMARY KEY  (`SRC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplierregistrationcategory`
--

LOCK TABLES `supplierregistrationcategory` WRITE;
/*!40000 ALTER TABLE `supplierregistrationcategory` DISABLE KEYS */;
INSERT INTO `supplierregistrationcategory` VALUES (1,'Manufacturers, who supply indigenous items','MANUAL ON POLICIES AND PROCEDURES FOR PURCHASE OF GOODS, 2006 Section 5.5'),(2,'Agents/ Distributors of such manufacturers, who desire to market their production only through their agents','MANUAL ON POLICIES AND PROCEDURES FOR PURCHASE OF GOODS, 2006 Section 5.5'),(3,'Foreign manufacturer with / without their accredited agents agent in India','MANUAL ON POLICIES AND PROCEDURES FOR PURCHASE OF GOODS, 2006 Section 5.5'),(4,'Stockiest of imported spares or other specified items','MANUAL ON POLICIES AND PROCEDURES FOR PURCHASE OF GOODS, 2006 Section 5.5'),(5,'Supplier of imported goods as are having regular arrangement with foreign manufacturers','MANUAL ON POLICIES AND PROCEDURES FOR PURCHASE OF GOODS, 2006 Section 5.5');
/*!40000 ALTER TABLE `supplierregistrationcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderingoptions`
--

DROP TABLE IF EXISTS `orderingoptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderingoptions` (
  `OO_ID` smallint(5) NOT NULL auto_increment,
  `OO_Base_Table` varchar(50) default NULL,
  `OO_Order_By_Clause` varchar(100) default NULL,
  `OO_Order_By_LOV` varchar(150) default NULL,
  PRIMARY KEY  (`OO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderingoptions`
--

LOCK TABLES `orderingoptions` WRITE;
/*!40000 ALTER TABLE `orderingoptions` DISABLE KEYS */;
INSERT INTO `orderingoptions` VALUES (1,'BudgetMaster','BM_IM_ID, BM_SIM_ID, BM_DM_ID, BM_Budget_Name Asc','Institution, SubInstitution,, Department, Budget Name (Ascending)'),(2,'BudgetMaster','BM_FYM_ID, BM_IM_ID, BM_SIM_ID, BM_DM_ID, BM_Budget_Name Asc','Financial Year, Institution, SubInstitution,, Department, Budget Name (Ascending)');
/*!40000 ALTER TABLE `orderingoptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budgetheadmaster`
--

DROP TABLE IF EXISTS `budgetheadmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgetheadmaster` (
  `BHM_ID` smallint(4) unsigned NOT NULL auto_increment,
  `BHM_Name` varchar(100) default NULL,
  `BHM_IM_Id` smallint(5) unsigned default NULL,
  PRIMARY KEY  (`BHM_ID`),
  UNIQUE KEY `UNIQUE_BHM_IM_Id_BHM_Name` (`BHM_IM_Id`,`BHM_Name`),
  CONSTRAINT `fk_BHM_IM_Id` FOREIGN KEY (`BHM_IM_Id`) REFERENCES `institutionmaster` (`IM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgetheadmaster`
--

LOCK TABLES `budgetheadmaster` WRITE;
/*!40000 ALTER TABLE `budgetheadmaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `budgetheadmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmmodule`
--

DROP TABLE IF EXISTS `erpmmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmmodule` (
  `ERPMM_ID` tinyint(3) unsigned NOT NULL auto_increment,
  `ERPMM_Short_Name` char(15) NOT NULL,
  `ERPMM_Name` varchar(100) NOT NULL,
  `ERPMM_ERPMT_ID` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY  (`ERPMM_ID`),
  UNIQUE KEY `UN_EM_ERPMM_Short_Name` (`ERPMM_Short_Name`),
  KEY `FK_EM_ERPMTeam_ERPMT_ID` (`ERPMM_ERPMT_ID`),
  CONSTRAINT `FK_EM_ERPMTeam_ERPMT_ID` FOREIGN KEY (`ERPMM_ERPMT_ID`) REFERENCES `erpmteam` (`ERPMT_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmmodule`
--

LOCK TABLES `erpmmodule` WRITE;
/*!40000 ALTER TABLE `erpmmodule` DISABLE KEYS */;
INSERT INTO `erpmmodule` VALUES (1,'PICO','Purchase and Inventory Control Module',6);
/*!40000 ALTER TABLE `erpmmodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_po_taxes`
--

DROP TABLE IF EXISTS `erpm_po_taxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_po_taxes` (
  `POT_PO_Taxes_ID` int(10) unsigned NOT NULL auto_increment,
  `POT_PODetail_ID` int(10) unsigned NOT NULL,
  `POT_Tax_Name` varchar(30) default NULL,
  `POT_Tax_Percent` decimal(4,2) default '0.00',
  `POT_Tax_on_Value_Percent` decimal(4,2) default '0.00',
  `POT_Surcharge_Percent` decimal(4,2) default '0.00',
  PRIMARY KEY  (`POT_PO_Taxes_ID`),
  KEY `FK_POT_PODetail` (`POT_PODetail_ID`),
  CONSTRAINT `FK_POT_PODetail` FOREIGN KEY (`POT_PODetail_ID`) REFERENCES `erpm_po_details` (`POD_PODetails_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_po_taxes`
--

LOCK TABLES `erpm_po_taxes` WRITE;
/*!40000 ALTER TABLE `erpm_po_taxes` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_po_taxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storetype`
--

DROP TABLE IF EXISTS `storetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storetype` (
  `ST_Type_ID` tinyint(3) unsigned NOT NULL auto_increment,
  `ST_Name` varchar(100) NOT NULL,
  PRIMARY KEY  (`ST_Type_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storetype`
--

LOCK TABLES `storetype` WRITE;
/*!40000 ALTER TABLE `storetype` DISABLE KEYS */;
INSERT INTO `storetype` VALUES (1,'Office'),(2,'Lab'),(3,'Library'),(4,'Seminar Room'),(5,'Auditorium'),(6,'Classroom'),(7,'Store');
/*!40000 ALTER TABLE `storetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmuserdepartments`
--

DROP TABLE IF EXISTS `erpmuserdepartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmuserdepartments` (
  `ERPMUD_ID` mediumint(8) unsigned NOT NULL auto_increment,
  `ERPMUD_DM_ID` mediumint(5) unsigned NOT NULL,
  `ERPMUD_IUP_ID` smallint(6) unsigned NOT NULL,
  PRIMARY KEY  (`ERPMUD_ID`),
  KEY `FK_ERPMUD_DepartmentMaster_DM_ID` (`ERPMUD_DM_ID`),
  KEY `FK_ERPMUD_InstitutionUserPrivileges_IUP_ID` (`ERPMUD_IUP_ID`),
  CONSTRAINT `FK_ERPMUD_DepartmentMaster_DM_ID` FOREIGN KEY (`ERPMUD_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMUD_InstitutionUserPrivileges_IUP_ID` FOREIGN KEY (`ERPMUD_IUP_ID`) REFERENCES `institutionroleprivileges` (`IUP_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmuserdepartments`
--

LOCK TABLES `erpmuserdepartments` WRITE;
/*!40000 ALTER TABLE `erpmuserdepartments` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpmuserdepartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_po_details`
--

DROP TABLE IF EXISTS `erpm_po_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_po_details` (
  `POD_PODetails_ID` int(10) unsigned NOT NULL auto_increment,
  `POD_POMaster_ID` int(10) unsigned NOT NULL,
  `POD_Item_ID` int(10) unsigned NOT NULL,
  `POD_Quantity` decimal(8,2) unsigned default NULL,
  `POD_Rate` decimal(10,2) unsigned default NULL,
  `POD_Discount` decimal(4,2) default NULL,
  `POD_Warranty_Terms` varchar(200) default NULL,
  `POD_Schedule` enum('0','1') default NULL,
  PRIMARY KEY  (`POD_PODetails_ID`),
  UNIQUE KEY `POD_POID_ITEM_UNIQUE` (`POD_POMaster_ID`,`POD_Item_ID`),
  KEY `FK_POD_Item` (`POD_Item_ID`),
  KEY `FK_POD_POMaster` (`POD_POMaster_ID`),
  CONSTRAINT `FK_POD_Item` FOREIGN KEY (`POD_Item_ID`) REFERENCES `erpm_item_master` (`ERPMIM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POD_POMaster` FOREIGN KEY (`POD_POMaster_ID`) REFERENCES `erpm_po_master` (`POM_PO_Master_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_po_details`
--

LOCK TABLES `erpm_po_details` WRITE;
/*!40000 ALTER TABLE `erpm_po_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_po_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmusers`
--

DROP TABLE IF EXISTS `erpmusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmusers` (
  `ERPMU_ID` mediumint(9) unsigned NOT NULL auto_increment,
  `ERPMU_Name` varchar(50) NOT NULL,
  `ERPMU_Password` varchar(50) NOT NULL,
  `ERPMU_DOB` date NOT NULL,
  `ERPMU_Active` enum('Y','N') NOT NULL default 'Y',
  `ERPMU_Verified_By` varchar(50) default NULL,
  `ERPMU_Secret_Question` varchar(100) default NULL,
  `ERPMU_Secret_Answer` varchar(100) default NULL,
  `ERPMU_Full_Name` varchar(50) NOT NULL,
  PRIMARY KEY  (`ERPMU_ID`),
  UNIQUE KEY `UNIQUE_ERPMU_Name` (`ERPMU_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmusers`
--

LOCK TABLES `erpmusers` WRITE;
/*!40000 ALTER TABLE `erpmusers` DISABLE KEYS */;
INSERT INTO `erpmusers` VALUES (1,'admin@jmi.ac.in','admin','1969-12-07','Y',NULL,'What is the first name of your father?','shane','Dr. S. Kazim Naqvi'),(2,'DefaultApplicationUser','erpm123','2010-09-06','Y',NULL,NULL,NULL,'User for whom default parameters will be defined'),(3,'kazim.jmi@gmail.com','skn123','1970-03-20','N',NULL,'Official DOB?','7-12-1969','S. Kazim Naqvi'),(4,'nksingh@iitk.ac.in','nksingh','2011-01-08','N',NULL,'NK Singh','NK Singh','NK Singh'),(5,'nks@iitk.ac.in','nks','2011-01-08','N',NULL,'nks','nks','nks'),(6,'mazim@jmi.ac.in','mazim','2011-01-13','N',NULL,'What is my designation?','Sytsten Analyst','Muzafffar Azim'),(7,'qasim@jmi.ac.in','qasim','2011-01-16','N',NULL,'qasim','qasim','qasim'),(8,'tanvir@jmi.ac.in','tanvir','1974-11-02','N',NULL,'What is my DOB?','02-11-1974','Tanvir Ahmed'),(9,'sajidaziz00@gmail.com','sajid','2011-02-09','N',NULL,'how r u','fine','sajid'),(10,'afreen.mca@gmail.com','afreen','2011-02-11','N',NULL,'hello ','hi','afreen khan');
/*!40000 ALTER TABLE `erpmusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_item_master`
--

DROP TABLE IF EXISTS `erpm_item_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_item_master` (
  `ERPMIM_ID` int(10) unsigned NOT NULL auto_increment,
  `ERPMIM_IM_ID` smallint(5) unsigned default NULL COMMENT 'This filed stores Institution fotr which Item has been created',
  `ERPMIM_Item_Brief_Desc` varchar(500) NOT NULL,
  `ERPMIM_Item_Cat1` int(11) unsigned NOT NULL,
  `ERPMIM_Item_Cat2` int(11) unsigned NOT NULL,
  `ERPMIM_Item_Cat3` int(11) unsigned NOT NULL,
  `ERPMIM_UOP` int(10) unsigned NOT NULL,
  `ERPMIM_Make` varchar(20) default NULL,
  `ERPMIM_Model` varchar(20) default NULL,
  `ERPMIM_Capital_Cat` int(10) unsigned default NULL,
  `ERPMIM_Detailed_Desc` varchar(2000) NOT NULL,
  `ERPMIM_Remarks` varchar(100) default NULL,
  PRIMARY KEY  (`ERPMIM_ID`),
  KEY `FK_ERPMIM_Item_Cat2_ERPMICM_Item_Id` (`ERPMIM_Item_Cat2`),
  KEY `FK_ERPMIM_Item_Cat1_ERPMICM_Item_Id` (`ERPMIM_Item_Cat1`),
  KEY `FK_ERPMIM_IM_ID_IM_ID` (`ERPMIM_IM_ID`),
  KEY `FK_ERPMIM_Capital_Cat_ERPMCC_ID` (`ERPMIM_Capital_Cat`),
  KEY `FK_ERPMIM_Item_Cat3_ERPMICM_Item_Id` (`ERPMIM_Item_Cat3`),
  KEY `FK_ERPMIM_Item_UOP_ERPMGM_EGM_ID` (`ERPMIM_UOP`),
  CONSTRAINT `FK_ERPMIM_Capital_Cat_ERPMCC_ID` FOREIGN KEY (`ERPMIM_Capital_Cat`) REFERENCES `erpm_capital_category` (`ERPMCC_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMIM_IM_ID_IM_ID` FOREIGN KEY (`ERPMIM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMIM_Item_Cat1_ERPMICM_Item_Id` FOREIGN KEY (`ERPMIM_Item_Cat1`) REFERENCES `erpm_item_category_master` (`ERPMICM_Item_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMIM_Item_Cat2_ERPMICM_Item_Id` FOREIGN KEY (`ERPMIM_Item_Cat2`) REFERENCES `erpm_item_category_master` (`ERPMICM_Item_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMIM_Item_Cat3_ERPMICM_Item_Id` FOREIGN KEY (`ERPMIM_Item_Cat3`) REFERENCES `erpm_item_category_master` (`ERPMICM_Item_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMIM_Item_UOP_ERPMGM_EGM_ID` FOREIGN KEY (`ERPMIM_UOP`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_item_master`
--

LOCK TABLES `erpm_item_master` WRITE;
/*!40000 ALTER TABLE `erpm_item_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_item_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemtype`
--

DROP TABLE IF EXISTS `itemtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemtype` (
  `IT_ID` tinyint(4) unsigned NOT NULL auto_increment COMMENT 'This autoincrement column holds ID for each Item Type',
  `IT_Name` varchar(50) NOT NULL COMMENT 'Holds Item Name',
  PRIMARY KEY  (`IT_ID`),
  UNIQUE KEY `IT_Name_UNIQUE` (`IT_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='This table stores Items Types (Non-Consumable, Consumable,, ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemtype`
--

LOCK TABLES `itemtype` WRITE;
/*!40000 ALTER TABLE `itemtype` DISABLE KEYS */;
INSERT INTO `itemtype` VALUES (1,'Cosumable'),(2,'Non-Cosumable'),(3,'Services');
/*!40000 ALTER TABLE `itemtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storemaster`
--

DROP TABLE IF EXISTS `storemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storemaster` (
  `SM_ID` mediumint(9) unsigned NOT NULL auto_increment,
  `SM_Dep_ID` mediumint(6) unsigned NOT NULL,
  `SM_Name` varchar(100) NOT NULL,
  `SM_Type` tinyint(3) unsigned NOT NULL,
  `SM_Address_Line1` varchar(50) default NULL,
  `SM_Address_Line2` varchar(50) default NULL,
  `SM_District` varchar(50) default NULL,
  `SM_State_ID` tinyint(4) unsigned default NULL,
  `SM_Pin_No` char(6) default NULL,
  `SM_EMail_ID` varchar(50) default NULL,
  PRIMARY KEY  (`SM_ID`),
  KEY `FK_SM_DepartmentMaster_DM_ID` (`SM_Dep_ID`),
  KEY `FK_SM_StateMaster_State_ID` (`SM_State_ID`),
  KEY `FK_SM_StoreType_ST_Type_ID` (`SM_Type`),
  CONSTRAINT `FK_SM_DepartmentMaster_DM_ID` FOREIGN KEY (`SM_Dep_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SM_StateMaster_State_ID` FOREIGN KEY (`SM_State_ID`) REFERENCES `statemaster` (`State_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SM_StoreType_ST_Type_ID` FOREIGN KEY (`SM_Type`) REFERENCES `storetype` (`ST_Type_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storemaster`
--

LOCK TABLES `storemaster` WRITE;
/*!40000 ALTER TABLE `storemaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `storemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_po_terms`
--

DROP TABLE IF EXISTS `erpm_po_terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_po_terms` (
  `POT_POT_ID` int(10) unsigned NOT NULL auto_increment,
  `POT_POM_ID` int(10) unsigned NOT NULL,
  `POT_EGM_ID` int(10) unsigned NOT NULL,
  `POT_TERMS_DESCRIPTION` varchar(500) character set latin1 default NULL,
  PRIMARY KEY  (`POT_POT_ID`),
  UNIQUE KEY `POT_POM_ID_UNIQUE` (`POT_POM_ID`,`POT_EGM_ID`),
  KEY `FK_POT_PO` (`POT_POM_ID`),
  KEY `FK_POT_EGM` (`POT_EGM_ID`),
  CONSTRAINT `FK_POT_EGM` FOREIGN KEY (`POT_EGM_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POT_PO` FOREIGN KEY (`POT_POM_ID`) REFERENCES `erpm_po_master` (`POM_PO_Master_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_po_terms`
--

LOCK TABLES `erpm_po_terms` WRITE;
/*!40000 ALTER TABLE `erpm_po_terms` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_po_terms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_item_rate_taxes`
--

DROP TABLE IF EXISTS `erpm_item_rate_taxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_item_rate_taxes` (
  `IRT_Item_Rate_Taxes_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IRT_Item_Rate_ID` int(10) unsigned NOT NULL,
  `IRT_Tax_Name` varchar(30) DEFAULT NULL,
  `IRT_Tax_Percent` decimal(5,2) DEFAULT '0.00',
  `IRT_Tax_on_Value_Percent` decimal(5,2) DEFAULT '0.00',
  `IRT_Surcharge_Percent` decimal(5,2) DEFAULT '0.00',
  PRIMARY KEY (`IRT_Item_Rate_Taxes_Id`),
  KEY `FK_IRT_Item_Rate_Id` (`IRT_Item_Rate_ID`),
  KEY `FK_IRT_Item_Rate_Details` (`IRT_Item_Rate_ID`),
  CONSTRAINT `FK_Erpm_Item_Rate` FOREIGN KEY (`IRT_Item_Rate_ID`) REFERENCES `erpm_item_rate` (`IR_Item_Rate_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_item_rate_taxes`
--

LOCK TABLES `erpm_item_rate_taxes` WRITE;
/*!40000 ALTER TABLE `erpm_item_rate_taxes` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_item_rate_taxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_indent_detail`
--

DROP TABLE IF EXISTS `erpm_indent_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_indent_detail` (
  `indt_Detail_Id` smallint(5) unsigned NOT NULL auto_increment,
  `Indt_indt_mst_Indent_Id` smallint(5) unsigned NOT NULL,
  `Indt_Item_Id` int(10) unsigned NOT NULL,
  `Indt_quantity` smallint(5) NOT NULL,
  `indt_Approxcost` int(10) NOT NULL,
  `Indt_Purpose` varchar(100) default NULL,
  PRIMARY KEY  (`indt_Detail_Id`),
  KEY `fk_INDENTDET_IND_master` (`Indt_indt_mst_Indent_Id`),
  KEY `fk_INDENTDET_erpm_item_master` (`Indt_Item_Id`),
  CONSTRAINT `fk_INDENTDET_erpm_item_master` FOREIGN KEY (`Indt_Item_Id`) REFERENCES `erpm_item_master` (`ERPMIM_ID`),
  CONSTRAINT `fk_INDENTDET_IND_master` FOREIGN KEY (`Indt_indt_mst_Indent_Id`) REFERENCES `erpm_indent_master` (`Indt_Indent_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_indent_detail`
--

LOCK TABLES `erpm_indent_detail` WRITE;
/*!40000 ALTER TABLE `erpm_indent_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_indent_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financialyearmaster`
--

DROP TABLE IF EXISTS `financialyearmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `financialyearmaster` (
  `FYM_FY_ID` tinyint(3) unsigned NOT NULL auto_increment,
  `FYM_From_Year` smallint(4) NOT NULL,
  `FYM_To_Year` smallint(4) NOT NULL,
  PRIMARY KEY  (`FYM_FY_ID`),
  UNIQUE KEY `Unique_FYM_From_Year_FYM_To_Year` (`FYM_From_Year`,`FYM_To_Year`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financialyearmaster`
--

LOCK TABLES `financialyearmaster` WRITE;
/*!40000 ALTER TABLE `financialyearmaster` DISABLE KEYS */;
INSERT INTO `financialyearmaster` VALUES (1,2010,2011),(2,2011,2012);
/*!40000 ALTER TABLE `financialyearmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countrymaster`
--

DROP TABLE IF EXISTS `countrymaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countrymaster` (
  `Country_ID` tinyint(4) NOT NULL auto_increment,
  `Country_Name` varchar(100) NOT NULL,
  PRIMARY KEY  (`Country_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countrymaster`
--

LOCK TABLES `countrymaster` WRITE;
/*!40000 ALTER TABLE `countrymaster` DISABLE KEYS */;
INSERT INTO `countrymaster` VALUES (1,'India'),(2,'China'),(3,'Japan'),(4,'Germany');
/*!40000 ALTER TABLE `countrymaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_address`
--

DROP TABLE IF EXISTS `supplier_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_address` (
  `sup_ad_id` int(11) unsigned NOT NULL auto_increment,
  `ad_Line1` varchar(50) NOT NULL,
  `ad_Line2` varchar(50) default NULL,
  `ad_country_id` tinyint(4) default NULL,
  `ad_state_id` tinyint(4) unsigned default NULL,
  `ad_city` varchar(50) default NULL,
  `ad_Phn` int(12) default NULL,
  `ad_Mob` int(12) default NULL,
  `ad_Faxn` int(12) default NULL,
  `ad_email` varchar(50) default NULL,
  `ad_sm_id` int(10) unsigned default NULL,
  `ad_desc` char(6) default NULL,
  PRIMARY KEY  (`sup_ad_id`),
  KEY `fk_sup_add_country_id` (`ad_country_id`),
  KEY `fk_sup_add_state_id` (`ad_state_id`),
  KEY `fk_sup_add_sup_id` (`ad_sm_id`),
  CONSTRAINT `fk_sup_add_country_id` FOREIGN KEY (`ad_country_id`) REFERENCES `countrymaster` (`Country_ID`),
  CONSTRAINT `fk_sup_add_state_id` FOREIGN KEY (`ad_state_id`) REFERENCES `statemaster` (`State_ID`),
  CONSTRAINT `fk_sup_add_sup_id` FOREIGN KEY (`ad_sm_id`) REFERENCES `suppliermaster` (`SM_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_address`
--

LOCK TABLES `supplier_address` WRITE;
/*!40000 ALTER TABLE `supplier_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genericroleprivileges`
--

DROP TABLE IF EXISTS `genericroleprivileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genericroleprivileges` (
  `GUP_ID` smallint(6) NOT NULL auto_increment,
  `GUP_ERPMM_ID` tinyint(3) unsigned default NULL,
  `GUP_ERPMP_ID` smallint(6) NOT NULL,
  `GUP_GUR_ID` tinyint(3) unsigned NOT NULL,
  `GUP_Can_Add` enum('0','1') NOT NULL default '0',
  `GUP_Can_Delete` enum('1','0') NOT NULL default '0',
  `GUP_Can_Edit` enum('0','1') NOT NULL default '0',
  `GUP_Can_View` enum('0','1') default '0',
  PRIMARY KEY  (`GUP_ID`),
  KEY `FK_UP_ERPMProgram_ERPMP_ID` (`GUP_ERPMP_ID`),
  KEY `FK_GUP_GenericUserRole_GUR_ID` (`GUP_GUR_ID`),
  KEY `FK_GUP_ERPMM_ERPMM_ID` (`GUP_ERPMM_ID`),
  CONSTRAINT `FK_GUP_ERPMM_ERPMM_ID` FOREIGN KEY (`GUP_ERPMM_ID`) REFERENCES `erpmmodule` (`ERPMM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_GUP_ERPMP_ERPMP_ID` FOREIGN KEY (`GUP_ERPMP_ID`) REFERENCES `erpmprogram` (`ERPMP_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_GUP_GenericUserRole_GUR_ID` FOREIGN KEY (`GUP_GUR_ID`) REFERENCES `genericuserroles` (`GUR_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genericroleprivileges`
--

LOCK TABLES `genericroleprivileges` WRITE;
/*!40000 ALTER TABLE `genericroleprivileges` DISABLE KEYS */;
INSERT INTO `genericroleprivileges` VALUES (1,1,1,1,'0','0','0','0'),(2,1,2,1,'0','0','0','0'),(3,1,3,1,'0','0','0','0');
/*!40000 ALTER TABLE `genericroleprivileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budgettypemaster`
--

DROP TABLE IF EXISTS `budgettypemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgettypemaster` (
  `BTM_ID` tinyint(3) unsigned NOT NULL auto_increment,
  `BTM_Name` varchar(100) NOT NULL,
  PRIMARY KEY  (`BTM_ID`),
  UNIQUE KEY `UNIQUE_BTM_Name` (`BTM_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgettypemaster`
--

LOCK TABLES `budgettypemaster` WRITE;
/*!40000 ALTER TABLE `budgettypemaster` DISABLE KEYS */;
INSERT INTO `budgettypemaster` VALUES (2,'Deposit'),(3,'Earmark'),(4,'Equipment Plan'),(5,'ERP Mission Project Budget'),(1,'Revenue');
/*!40000 ALTER TABLE `budgettypemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_po_master`
--

DROP TABLE IF EXISTS `erpm_po_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_po_master` (
  `POM_PO_Master_ID` int(10) unsigned NOT NULL auto_increment,
  `POM_IM_ID` smallint(5) unsigned NOT NULL,
  `POM_DM_ID` mediumint(5) unsigned NOT NULL,
  `POM_PO_No` int(5) NOT NULL,
  `POM_PO_Date` date NOT NULL,
  `POM_Purchase_Mode` varchar(10) default NULL,
  `POM_Payment_Mode_ID` int(10) unsigned NOT NULL,
  `POM_Supplier_ID` int(10) unsigned NOT NULL,
  `POM_Form_ID` int(10) unsigned default NULL,
  `POM_Form_No` varchar(20) default NULL,
  `POM_Terms_Days` int(2) default NULL,
  `POM_Delivery_Date` date default NULL,
  `POM_Remarks` varchar(200) default NULL,
  `POM_Discount` decimal(4,2) default '0.00',
  `POM_Currency_ID` int(10) unsigned NOT NULL,
  `POM_Approved_By_ID` mediumint(9) unsigned default '0',
  `POM_Approved_By_Designation` varchar(30) default NULL,
  `POM_Against_Reference_ID` int(10) unsigned default NULL,
  `POM_User_ID` mediumint(9) unsigned default NULL,
  `POM_Accomplished` char(3) default 'NO',
  `POM_Cancelled` char(3) default 'NO',
  `POM_SIM_ID` mediumint(5) unsigned NOT NULL,
  `POM_Purchase_Purpose` varchar(50) default NULL,
  PRIMARY KEY  (`POM_PO_Master_ID`),
  KEY `FK_POM_Approved_By` (`POM_User_ID`),
  KEY `FK_POM_Currency` (`POM_Currency_ID`),
  KEY `FK_POM_DM` (`POM_DM_ID`),
  KEY `FK_POM_Form` (`POM_Form_ID`),
  KEY `FK_POM_IM` (`POM_IM_ID`),
  KEY `FK_POM_Payment_Mode` (`POM_Payment_Mode_ID`),
  KEY `FK_POM_Supplier` (`POM_Supplier_ID`),
  KEY `FK_POM_User` (`POM_User_ID`),
  KEY `FK_POM_SIM` (`POM_SIM_ID`),
  KEY `FK_POM_Approved` (`POM_Approved_By_ID`),
  CONSTRAINT `FK_POM_Approved` FOREIGN KEY (`POM_Approved_By_ID`) REFERENCES `erpmusers` (`ERPMU_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_Currency` FOREIGN KEY (`POM_Currency_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_DM` FOREIGN KEY (`POM_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_Form` FOREIGN KEY (`POM_Form_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_IM` FOREIGN KEY (`POM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_Payment_Mode` FOREIGN KEY (`POM_Payment_Mode_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_SIM` FOREIGN KEY (`POM_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_Supplier` FOREIGN KEY (`POM_Supplier_ID`) REFERENCES `suppliermaster` (`SM_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POM_User` FOREIGN KEY (`POM_User_ID`) REFERENCES `erpmusers` (`ERPMU_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_po_master`
--

LOCK TABLES `erpm_po_master` WRITE;
/*!40000 ALTER TABLE `erpm_po_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_po_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeemaster`
--

DROP TABLE IF EXISTS `employeemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeemaster` (
  `EMP_ID` int(5) unsigned NOT NULL auto_increment,
  `EMP_IM_ID` smallint(3) unsigned NOT NULL,
  `EMP_SIM_ID` mediumint(3) unsigned NOT NULL,
  `EMP_DM_ID` mediumint(5) unsigned NOT NULL,
  `EMP_FName` varchar(20) NOT NULL,
  `EMP_MName` varchar(30) NOT NULL,
  `EMP_LName` varchar(30) NOT NULL,
  `EMP_DESIGNATION_ID` int(10) unsigned NOT NULL,
  `EMP_EMail` varchar(50) default NULL,
  `EMP_Mobile` varchar(15) default NULL,
  `EMP_LandLine` varchar(15) default NULL,
  `EMP_DOB` date default NULL,
  `EMP_DOJ` date default NULL,
  `EMP_Gender` varchar(2) default NULL,
  PRIMARY KEY  (`EMP_ID`),
  KEY `FK_EMP_IM_ID_FROM_IM_ID` (`EMP_IM_ID`),
  KEY `FK_EMP_SIM_ID_FROM_IM_ID` (`EMP_SIM_ID`),
  KEY `FK_EMP_DM_ID_FROM_DM_ID` (`EMP_DM_ID`),
  KEY `FK_EMP_DESIGNATION_ID_FROM_gen_master` (`EMP_DESIGNATION_ID`),
  CONSTRAINT `FK_EMP_DESIGNATION_ID_FROM_gen_master` FOREIGN KEY (`EMP_DESIGNATION_ID`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`),
  CONSTRAINT `FK_EMP_DM_ID_FROM_DM_ID` FOREIGN KEY (`EMP_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`),
  CONSTRAINT `FK_EMP_IM_ID_FROM_IM_ID` FOREIGN KEY (`EMP_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`),
  CONSTRAINT `FK_EMP_SIM_ID_FROM_IM_ID` FOREIGN KEY (`EMP_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeemaster`
--

LOCK TABLES `employeemaster` WRITE;
/*!40000 ALTER TABLE `employeemaster` DISABLE KEYS */;
INSERT INTO `employeemaster` VALUES (1,1,1,3,'Abdul','Haq','Khan',41,'h@jmi.ac.in','9899435055','011236990','1970-09-12','1999-10-13','M'),(2,1,1,3,'Abdul','Shahid','Khan',41,'h@jmi.ac.in','9899435055','011236990','1970-09-12','1999-10-13','M'),(3,1,1,3,'Mohd','Gaffar','Azim',38,'G@jmi.ac.in','9899435122','05262237996','1940-11-03','1960-10-07','M'),(4,1,4,3,'Ali','Gaffar','Mustafa',38,'G@jmi.ac.in','9899435122','05262237996','1940-11-03','1960-10-07','M');
/*!40000 ALTER TABLE `employeemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_purchasechallan_serial`
--

DROP TABLE IF EXISTS `erpm_purchasechallan_serial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_purchasechallan_serial` (
  `PCS_PCS_ID` int(10) unsigned NOT NULL auto_increment,
  `PCS_PCDetail_ID` int(10) unsigned NOT NULL,
  `PCS_Equipment_Serial_No` varchar(50) default NULL,
  `PCS_Stock_Serial_No` varchar(50) default NULL,
  PRIMARY KEY  (`PCS_PCS_ID`),
  UNIQUE KEY `PCS_PCDID_EQSL_UNIQUE` (`PCS_PCDetail_ID`,`PCS_Equipment_Serial_No`),
  KEY `FK_PCS_PCDetail_ID` (`PCS_PCDetail_ID`),
  CONSTRAINT `FK_PCS_PCDetail_ID` FOREIGN KEY (`PCS_PCDetail_ID`) REFERENCES `erpm_purchasechallan_detail` (`PCD_PCD_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_purchasechallan_serial`
--

LOCK TABLES `erpm_purchasechallan_serial` WRITE;
/*!40000 ALTER TABLE `erpm_purchasechallan_serial` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_purchasechallan_serial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliermaster`
--

DROP TABLE IF EXISTS `suppliermaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliermaster` (
  `SM_Id` int(10) unsigned NOT NULL auto_increment,
  `SM_IM_Id` smallint(5) unsigned NOT NULL,
  `SM_Name` varchar(50) NOT NULL,
  `SM_Supplier_Type` int(10) unsigned default NULL,
  `SM_Ownership_Type` int(10) unsigned default NULL,
  `SM_Deals_With` varchar(200) default NULL,
  `SM_Year_Establishment` date default NULL,
  `SM_PAN_No` varchar(10) default NULL,
  `SM_TAN_No` varchar(10) default NULL,
  `SM_STATE_ST_VAT_RGN_NO` varchar(10) default NULL,
  `SM_CEN_ST_VAT_RGN_NO` varchar(10) default NULL,
  `SM_ED_RGN_NO` varchar(10) default NULL,
  `SM_ECC_CODE` varchar(10) default NULL,
  `SM_ENTREPRENURE_MEM_NO` varchar(10) default NULL,
  `SM_REG_DATE` date NOT NULL,
  `SM_Remarks` varchar(100) default NULL,
  `SM_CEO_OR_PROPRIETOR_NAME` varchar(100) default NULL,
  PRIMARY KEY  (`SM_Id`),
  UNIQUE KEY `SM_IM_ID_SM_Name_Unique` (`SM_IM_Id`,`SM_Name`),
  UNIQUE KEY `Unique_SM_IM_ID_SM_PAN_No` (`SM_IM_Id`,`SM_PAN_No`),
  UNIQUE KEY `Unique_SM_IM_ID_SM_TAN_No` (`SM_IM_Id`,`SM_TAN_No`),
  KEY `FK_SMST_ERPMGM_EGM_ID` (`SM_Supplier_Type`),
  KEY `FK_SMOT_ERPMGM_EGM_ID` (`SM_Ownership_Type`),
  CONSTRAINT `FK_IMID_IM_IM_ID` FOREIGN KEY (`SM_IM_Id`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SMOT_ERPMGM_EGM_ID` FOREIGN KEY (`SM_Ownership_Type`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SMST_ERPMGM_EGM_ID` FOREIGN KEY (`SM_Supplier_Type`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliermaster`
--

LOCK TABLES `suppliermaster` WRITE;
/*!40000 ALTER TABLE `suppliermaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliermaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departmentmaster`
--

DROP TABLE IF EXISTS `departmentmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departmentmaster` (
  `DM_ID` mediumint(5) unsigned NOT NULL auto_increment,
  `DM_IM_ID` smallint(3) unsigned default NULL,
  `DM_SIM_ID` mediumint(3) unsigned default NULL,
  `DM_Name` varchar(100) NOT NULL,
  `DM_Short_Name` varchar(10) default NULL,
  `DM_Address_Line1` varchar(50) default NULL,
  `DM_Address_Line2` varchar(50) default NULL,
  `DM_District` varchar(50) default NULL,
  `DM_State_ID` tinyint(4) unsigned default NULL,
  `DM_Pin_No` char(6) default NULL,
  `DM_EMail_ID` varchar(50) default NULL,
  `DM_Country_ID` tinyint(4) default NULL,
  `DM_EMP_ID` int(5) unsigned default NULL,
  PRIMARY KEY  (`DM_ID`),
  KEY `FK_DM_StateMaster_State_ID` (`DM_State_ID`),
  KEY `FK_DM_SubInstitutionMaster_SIM_ID` (`DM_SIM_ID`),
  KEY `FK_DM_InstitutionMaster_IM_ID` (`DM_IM_ID`),
  KEY `fk_Countrymaster_DM_Country_ID` (`DM_Country_ID`),
  KEY `FK_DM_EMP_ID_employeemaster` (`DM_EMP_ID`),
  CONSTRAINT `fk_Countrymaster_DM_Country_ID` FOREIGN KEY (`DM_Country_ID`) REFERENCES `countrymaster` (`Country_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_DM_EMP_ID_employeemaster` FOREIGN KEY (`DM_EMP_ID`) REFERENCES `employeemaster` (`EMP_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_DM_InstitutionMaster_IM_ID` FOREIGN KEY (`DM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_DM_StateMaster_State_ID` FOREIGN KEY (`DM_State_ID`) REFERENCES `statemaster` (`State_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_DM_SubInstitutionMaster_SIM_ID` FOREIGN KEY (`DM_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmentmaster`
--

LOCK TABLES `departmentmaster` WRITE;
/*!40000 ALTER TABLE `departmentmaster` DISABLE KEYS */;
INSERT INTO `departmentmaster` VALUES (2,1,1,'Computer Science','ACDCS','Main Campus, Jamia Millia Islamia','Jamia Nagar','New Delhi',1,'110025','hod.cs@jmi.ac.in',NULL,NULL),(3,1,1,'Mathematics','ACMAT','Main Campus, Jamia Millia Islamia','Jamia Nagar','New Delhi',1,'110025','hod.mt@jmi.ac.in',NULL,NULL),(4,1,1,'Chemistry','ACCHE','Main Campus, Jamia Millia Islamia','Jamia Nagar','New Delhi',1,'110025','hod.ch@jmi.ac.in',NULL,NULL),(5,1,3,'Registrar Office','Registrar','Administrative Buiding, Jamia Millia Islamia','jamai Nagar','New Dehi',1,'110025','registrar@jmi.ac.in',NULL,NULL),(7,1,1,'Geography','ACGEO','Department of Geography','Near Bagh-e-Nanak','New Delhi',1,'110002','rocket@jmi.ac.in',NULL,NULL),(11,1,1,'Bio-Sciences','ACBIO','Main Campus, Jamia Millia Islamia','Jamia Nagar','New Delhi',1,'110025','hod.bio@jmi.ac.in',NULL,NULL),(15,1,3,'Registrar Office, jmi','Registrar','Administrative Buiding, Jamia Millia Islamia','jamai Nagar','New Dehi',1,'110025','registrar@jmi.ac.in',NULL,NULL),(18,1,9,'Department of Computer Engineering','AECOE','Dept. of Computer Engineering','Faculty of Engineering and Technology','New Delhi',1,'110025','mdoja@jmi.ac.in',NULL,NULL),(22,1,6,'AJK-Mass Communication Research Centre','MCRC','MCRC','','',1,'110025','mcrc@mcrc.mcrc',NULL,NULL);
/*!40000 ALTER TABLE `departmentmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmuserrole`
--

DROP TABLE IF EXISTS `erpmuserrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmuserrole` (
  `ERPMUR_ID` mediumint(8) unsigned NOT NULL auto_increment,
  `ERPMUR_ERPMU_ID` mediumint(9) unsigned default NULL,
  `ERPMUR_IM_ID` smallint(5) unsigned default NULL,
  `ERPMUR_SIM_ID` mediumint(5) unsigned NOT NULL,
  `ERPMUR_DM_ID` mediumint(5) unsigned NOT NULL,
  `ERPMUR_IUR_ID` mediumint(8) unsigned NOT NULL,
  `ERPMUR_Default` char(1) default 'N',
  `ERPMUR_Active` char(1) default 'N',
  PRIMARY KEY  (`ERPMUR_ID`),
  KEY `FK_ERPMUR_DepartmentMaster_DM_ID` (`ERPMUR_DM_ID`),
  KEY `FK_ERPMUR_ERPMUsers_ERPMU_ID` (`ERPMUR_ERPMU_ID`),
  KEY `FK_ERPMUR_InstitutionMaster_IM_ID` (`ERPMUR_IM_ID`),
  KEY `FK_ERPMUR_IUR_IUR_ID` (`ERPMUR_IUR_ID`),
  KEY `FK_ERPMUR_SubInstitutionMsater_SIM_ID` (`ERPMUR_SIM_ID`),
  CONSTRAINT `FK_ERPMUR_DepartmentMaster_DM_ID` FOREIGN KEY (`ERPMUR_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ERPMUR_ERPMUsers_ERPMU_ID` FOREIGN KEY (`ERPMUR_ERPMU_ID`) REFERENCES `erpmusers` (`ERPMU_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMUR_InstitutionMaster_IM_ID` FOREIGN KEY (`ERPMUR_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMUR_IUR_IUR_ID` FOREIGN KEY (`ERPMUR_IUR_ID`) REFERENCES `institutionuserroles` (`IUR_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_ERPMUR_SubInstitutionMsater_SIM_ID` FOREIGN KEY (`ERPMUR_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmuserrole`
--

LOCK TABLES `erpmuserrole` WRITE;
/*!40000 ALTER TABLE `erpmuserrole` DISABLE KEYS */;
INSERT INTO `erpmuserrole` VALUES (1,1,1,3,5,1,'Y','Y'),(2,10,1,1,2,1,'0','Y'),(3,2,1,9,18,1,'1','Y'),(4,9,1,1,3,1,'f','Y'),(5,3,1,1,2,1,'f','Y'),(6,8,1,1,2,1,'f','Y'),(7,4,1,1,2,1,'0','Y'),(8,7,1,1,3,2,'0','Y'),(9,5,1,1,11,2,'0','Y'),(10,6,1,6,22,2,'0','Y'),(11,1,1,6,22,1,'0','N'),(12,10,1,9,18,1,'0','N');
/*!40000 ALTER TABLE `erpmuserrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutionmaster`
--

DROP TABLE IF EXISTS `institutionmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutionmaster` (
  `IM_ID` smallint(5) unsigned NOT NULL auto_increment,
  `IM_Name` varchar(100) NOT NULL,
  `IM_Short_Name` varchar(10) default NULL,
  `im_type` int(10) unsigned default NULL,
  `IM_Address_Line1` varchar(50) NOT NULL,
  `IM_Address_Line2` varchar(50) default NULL,
  `IM_District` varchar(50) default NULL,
  `IM_State_ID` tinyint(4) unsigned default NULL,
  `IM_Pin_No` char(6) default NULL,
  `IM_EMail_ID` varchar(50) default NULL,
  `IM_Country_ID` tinyint(4) default NULL,
  `IM_EMP_ID` int(5) unsigned default NULL,
  PRIMARY KEY  (`IM_ID`),
  UNIQUE KEY `Unique_IM_Name` (`IM_Name`),
  UNIQUE KEY `Unique_IM_Short_Name` (`IM_Short_Name`),
  KEY `FK_IM_StateMaster_SM_ID` (`IM_State_ID`),
  KEY `FK_IM_InstitutionType_IT_Type_ID` (`im_type`),
  KEY `fk_Countrymaster_IM_Country_ID` (`IM_Country_ID`),
  KEY `FK_IM_EMP_ID_employeemaster` (`IM_EMP_ID`),
  CONSTRAINT `fk_Countrymaster_IM_Country_ID` FOREIGN KEY (`IM_Country_ID`) REFERENCES `countrymaster` (`Country_ID`),
  CONSTRAINT `FK_IM_EMP_ID_employeemaster` FOREIGN KEY (`IM_EMP_ID`) REFERENCES `employeemaster` (`EMP_ID`),
  CONSTRAINT `FK_IM_InstitutionType_IT_Type_ID` FOREIGN KEY (`im_type`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`),
  CONSTRAINT `FK_IM_StateMaster_SM_ID` FOREIGN KEY (`IM_State_ID`) REFERENCES `statemaster` (`State_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutionmaster`
--

LOCK TABLES `institutionmaster` WRITE;
/*!40000 ALTER TABLE `institutionmaster` DISABLE KEYS */;
INSERT INTO `institutionmaster` VALUES (1,'Jamia Millia Islamia','JMI',24,'Maulana Mohammad Ali Johar Marg, ','Jamia Nagar','New Delhi',1,'110025','ssajid@jmi.ac.in',NULL,NULL);
/*!40000 ALTER TABLE `institutionmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemcategory`
--

DROP TABLE IF EXISTS `itemcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemcategory` (
  `IC_ID` smallint(5) unsigned NOT NULL auto_increment,
  `IC_Name` varchar(50) default NULL COMMENT '	',
  `IC_IM_ID` smallint(5) unsigned default NULL,
  `IC_SIM_ID` mediumint(5) unsigned default NULL,
  PRIMARY KEY  (`IC_ID`),
  KEY `FK_IC_InstititionMaster_IM_ID` (`IC_IM_ID`),
  KEY `FK_IC_SubInstitutionMaster_SIM_ID` (`IC_SIM_ID`),
  CONSTRAINT `FK_IC_InstititionMaster_IM_ID` FOREIGN KEY (`IC_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_IC_SubInstitutionMaster_SIM_ID` FOREIGN KEY (`IC_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='This table holds Item Categories such as (Computer, Lab Equi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemcategory`
--

LOCK TABLES `itemcategory` WRITE;
/*!40000 ALTER TABLE `itemcategory` DISABLE KEYS */;
INSERT INTO `itemcategory` VALUES (1,'Furniture',1,1),(2,'Machinery',1,1);
/*!40000 ALTER TABLE `itemcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guestbook`
--

DROP TABLE IF EXISTS `guestbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guestbook` (
  `VisitorNo` int(11) NOT NULL auto_increment,
  `VisitorName` varchar(100) default NULL,
  `Message` varchar(100) default NULL,
  `MessageDate` varchar(20) default NULL,
  PRIMARY KEY  (`VisitorNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guestbook`
--

LOCK TABLES `guestbook` WRITE;
/*!40000 ALTER TABLE `guestbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `guestbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmuserprofile`
--

DROP TABLE IF EXISTS `erpmuserprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmuserprofile` (
  `IM_Id` smallint(5) unsigned NOT NULL default '0',
  `IM_Short_name` varchar(10) default NULL,
  `SIM_ID` mediumint(5) unsigned NOT NULL default '0',
  `SIM_Short_name` varchar(10) default NULL,
  `DM_ID` mediumint(5) unsigned NOT NULL default '0',
  `DM_Short_Name` varchar(10) default NULL,
  `ERPMU_ID` mediumint(9) NOT NULL,
  PRIMARY KEY  (`ERPMU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmuserprofile`
--

LOCK TABLES `erpmuserprofile` WRITE;
/*!40000 ALTER TABLE `erpmuserprofile` DISABLE KEYS */;
INSERT INTO `erpmuserprofile` VALUES (1,'JMI',4,'FTK-CIT',6,'FTK-CIT',1);
/*!40000 ALTER TABLE `erpmuserprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprivileges`
--

DROP TABLE IF EXISTS `userprivileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userprivileges` (
  `RP_ID` smallint(6) NOT NULL auto_increment,
  `RP_ERPMP_ID` smallint(6) NOT NULL,
  `RP_UR_ID` tinyint(3) unsigned NOT NULL,
  `RP_Can_Add` enum('0','1') NOT NULL default '0',
  `RP_Can_Delete` enum('1','0') default '0',
  `RP_Can_Edit` enum('0','1') NOT NULL default '0',
  PRIMARY KEY  (`RP_ID`),
  KEY `FK_UP_ERPMProgram_ERPMP_ID` (`RP_ERPMP_ID`),
  CONSTRAINT `FK_UP_ERPMProgram_ERPMP_ID` FOREIGN KEY (`RP_ERPMP_ID`) REFERENCES `erpmprogram` (`ERPMP_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprivileges`
--

LOCK TABLES `userprivileges` WRITE;
/*!40000 ALTER TABLE `userprivileges` DISABLE KEYS */;
/*!40000 ALTER TABLE `userprivileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_indent_master`
--

DROP TABLE IF EXISTS `erpm_indent_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_indent_master` (
  `Indt_Indent_Id` smallint(5) unsigned NOT NULL auto_increment,
  `Indt_Indent_Date` date NOT NULL,
  `Indt_Institution_Id` smallint(5) unsigned NOT NULL,
  `Indt_Subinstitution_Id` mediumint(5) unsigned NOT NULL,
  `Indt_Department_Id` mediumint(5) unsigned NOT NULL,
  `Indt_BudgetHead_Id` smallint(4) unsigned default NULL,
  `Indt_Generated_By` varchar(50) default NULL,
  `Indt_User_Id` mediumint(9) unsigned NOT NULL,
  `Indt_status` int(10) unsigned default NULL,
  `Indt_Currency_Id` int(10) unsigned default NULL,
  `Indt_Remarks` varchar(100) default NULL,
  `Indt_Forwarded_To_User_Id` mediumint(9) unsigned default NULL,
  `Indt_DBA_ID` mediumint(5) unsigned default NULL,
  PRIMARY KEY  (`Indt_Indent_Id`),
  KEY `fk_INDENT_Insitution_master` (`Indt_Institution_Id`),
  KEY `fk_INDENT_subInsitution_master` (`Indt_Subinstitution_Id`),
  KEY `fk_INDENT_Department_master` (`Indt_Department_Id`),
  KEY `fk_INDENT_Budget_head_master` (`Indt_BudgetHead_Id`),
  KEY `fk_INDENT_erpmusers` (`Indt_User_Id`),
  KEY `fk_INDENT_Erpm_gen_master` (`Indt_Currency_Id`),
  KEY `FK_Indt_Forwarded_To_User_Id` (`Indt_Forwarded_To_User_Id`),
  KEY `fk_DBA_ID_Indt_DBA_ID` (`Indt_DBA_ID`),
  KEY `fk_erpm_gen_master_Indt_status` (`Indt_status`),
  CONSTRAINT `fk_DBA_ID_Indt_DBA_ID` FOREIGN KEY (`Indt_DBA_ID`) REFERENCES `departmental_budget_allocation` (`DBA_ID`),
  CONSTRAINT `fk_erpm_gen_master_Indt_status` FOREIGN KEY (`Indt_status`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`),
  CONSTRAINT `fk_INDENT_Budget_head_master` FOREIGN KEY (`Indt_BudgetHead_Id`) REFERENCES `budgetheadmaster` (`BHM_ID`),
  CONSTRAINT `fk_INDENT_Department_master` FOREIGN KEY (`Indt_Department_Id`) REFERENCES `departmentmaster` (`DM_ID`),
  CONSTRAINT `fk_INDENT_erpmusers` FOREIGN KEY (`Indt_User_Id`) REFERENCES `erpmusers` (`ERPMU_ID`),
  CONSTRAINT `fk_INDENT_Erpm_gen_master` FOREIGN KEY (`Indt_Currency_Id`) REFERENCES `erpm_gen_master` (`ERPMGM_EGM_ID`),
  CONSTRAINT `fk_INDENT_Insitution_master` FOREIGN KEY (`Indt_Institution_Id`) REFERENCES `institutionmaster` (`IM_ID`),
  CONSTRAINT `fk_INDENT_subInsitution_master` FOREIGN KEY (`Indt_Subinstitution_Id`) REFERENCES `subinstitutionmaster` (`sim_id`),
  CONSTRAINT `FK_Indt_Forwarded_To_User_Id` FOREIGN KEY (`Indt_Forwarded_To_User_Id`) REFERENCES `erpmusers` (`ERPMU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_indent_master`
--

LOCK TABLES `erpm_indent_master` WRITE;
/*!40000 ALTER TABLE `erpm_indent_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_indent_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `message_id` smallint(5) NOT NULL auto_increment,
  `message_code` varchar(4) default NULL,
  `message_type` enum('W','E') default NULL,
  `message_text` varchar(100) default NULL,
  PRIMARY KEY  (`message_id`),
  UNIQUE KEY `message_code` (`message_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'E001','E','Sorry, you are not authorised for this operation'),(2,'W001','W','Your session has expired. Pease Login again');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutionuserroles`
--

DROP TABLE IF EXISTS `institutionuserroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutionuserroles` (
  `IUR_ID` mediumint(8) unsigned NOT NULL auto_increment,
  `IUR_IM_ID` smallint(5) unsigned default NULL,
  `IUR_Name` varchar(50) NOT NULL,
  `IUR_Remarks` varchar(100) default NULL,
  PRIMARY KEY  (`IUR_ID`),
  UNIQUE KEY `UNIQUE_IUR_Name_IUR_IM_ID` (`IUR_Name`,`IUR_IM_ID`),
  KEY `FK_IUR_IM_ID_IM_IM_ID` (`IUR_IM_ID`),
  CONSTRAINT `FK_IUR_IM_ID_IM_IM_ID` FOREIGN KEY (`IUR_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutionuserroles`
--

LOCK TABLES `institutionuserroles` WRITE;
/*!40000 ALTER TABLE `institutionuserroles` DISABLE KEYS */;
INSERT INTO `institutionuserroles` VALUES (1,1,'Purchase Managers','About Purchase manager'),(2,1,'Purchase Clerk','Role for purchase clerks');
/*!40000 ALTER TABLE `institutionuserroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_gen_ctrl`
--

DROP TABLE IF EXISTS `erpm_gen_ctrl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_gen_ctrl` (
  `ERPMGC_GEN_TYPE` smallint(5) unsigned NOT NULL auto_increment COMMENT 'Primary key',
  `ERPMGC_GEN_DESC` varchar(50) NOT NULL,
  `ERPMGC_Remarks` varchar(50) default NULL,
  PRIMARY KEY  (`ERPMGC_GEN_TYPE`),
  UNIQUE KEY `ERPMGC_GEN_DESC_UNIQUE` (`ERPMGC_GEN_DESC`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_gen_ctrl`
--

LOCK TABLES `erpm_gen_ctrl` WRITE;
/*!40000 ALTER TABLE `erpm_gen_ctrl` DISABLE KEYS */;
INSERT INTO `erpm_gen_ctrl` VALUES (1,'Company Ownership Type',NULL),(2,'Supplier Category',NULL),(3,'Unit of Measurement',NULL),(4,'Institution Type',NULL),(5,'Sub Institution Type',NULL),(6,'Currency',NULL),(7,'Employee Designation',NULL),(8,'Document Status',NULL),(9,'Warranty Starts From',NULL),(10,'Payment Mode',NULL),(11,'Tax Forms','NULL'),(12,'PO Terms & Conditions',NULL);
/*!40000 ALTER TABLE `erpm_gen_ctrl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budgetmaster`
--

DROP TABLE IF EXISTS `budgetmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgetmaster` (
  `bm_id` mediumint(8) unsigned NOT NULL auto_increment,
  `BM_IM_ID` smallint(5) unsigned NOT NULL,
  `BM_SIM_ID` mediumint(5) unsigned NOT NULL,
  `BM_DM_ID` mediumint(5) unsigned NOT NULL,
  `BM_BTM_ID` tinyint(3) unsigned NOT NULL COMMENT 'This fied stores Budget Type',
  `BM_Lapsable` enum('1','0') NOT NULL default '0',
  `BM_FYM_ID` tinyint(3) unsigned NOT NULL,
  `BM_Budget_Name` varchar(100) NOT NULL,
  `BM_Allocated_Amount` int(11) NOT NULL default '0',
  PRIMARY KEY  (`bm_id`),
  UNIQUE KEY `Unique_BM_DM_ID_Budget_Name` (`BM_DM_ID`,`BM_Budget_Name`),
  KEY `FK_BM_InstitutionMaster_IM_ID` (`BM_IM_ID`),
  KEY `FK_BM_SubInstitutionMaster_SIM_ID` (`BM_SIM_ID`),
  KEY `FK_BM_BudgetTypeMaster_BTM_ID` (`BM_BTM_ID`),
  KEY `FK_BM_FinancialYearMaster_FYM_ID` (`BM_FYM_ID`),
  CONSTRAINT `FK_BM_BudgetTypeMaster_BTM_ID` FOREIGN KEY (`BM_BTM_ID`) REFERENCES `budgettypemaster` (`BTM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_BM_DepartmentMaster_DM_ID` FOREIGN KEY (`BM_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_BM_FinancialYearMaster_FYM_ID` FOREIGN KEY (`BM_FYM_ID`) REFERENCES `financialyearmaster` (`FYM_FY_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_BM_InstitutionMaster_IM_ID` FOREIGN KEY (`BM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_BM_SubInstitutionMaster_SIM_ID` FOREIGN KEY (`BM_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgetmaster`
--

LOCK TABLES `budgetmaster` WRITE;
/*!40000 ALTER TABLE `budgetmaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `budgetmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplierregistrationauthority`
--

DROP TABLE IF EXISTS `supplierregistrationauthority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplierregistrationauthority` (
  `SRA_ID` mediumint(8) unsigned NOT NULL auto_increment,
  `SRA_IM_ID` smallint(3) unsigned NOT NULL,
  `SRA_SIM_ID` mediumint(3) unsigned NOT NULL,
  `SRA_DM_ID` mediumint(5) unsigned NOT NULL,
  `SRA_Name` varchar(50) NOT NULL,
  `SRA_Designation` varchar(50) NOT NULL,
  `SRA_EMail` varchar(100) NOT NULL,
  `SRA_Office_Number` varchar(10) NOT NULL,
  PRIMARY KEY  (`SRA_ID`),
  KEY `FK_SRA_InstitutionMaster_IM_ID` (`SRA_IM_ID`),
  KEY `FK_SRA_SubInstitutionMaster_SIM_ID` (`SRA_SIM_ID`),
  KEY `FK_SRA_DepartmentMaster_DM_ID` (`SRA_DM_ID`),
  CONSTRAINT `FK_SRA_DepartmentMaster_DM_ID` FOREIGN KEY (`SRA_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SRA_InstitutionMaster_IM_ID` FOREIGN KEY (`SRA_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SRA_SubInstitutionMaster_SIM_ID` FOREIGN KEY (`SRA_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplierregistrationauthority`
--

LOCK TABLES `supplierregistrationauthority` WRITE;
/*!40000 ALTER TABLE `supplierregistrationauthority` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplierregistrationauthority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subinstitutionmaster`
--

DROP TABLE IF EXISTS `subinstitutionmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subinstitutionmaster` (
  `sim_id` mediumint(5) unsigned NOT NULL auto_increment,
  `SIM_IM_ID` smallint(5) unsigned NOT NULL,
  `SIM_Name` varchar(100) NOT NULL,
  `SIM_Short_Name` varchar(10) default NULL,
  `sim_type` int(10) unsigned default NULL,
  `SIM_Address_Line1` varchar(50) default NULL,
  `SIM_Address_Line2` varchar(50) default NULL,
  `SIM_District` varchar(50) default NULL,
  `SIM_State_ID` tinyint(4) unsigned default NULL,
  `SIM_Pin_No` char(6) default NULL,
  `SIM_EMail_ID` varchar(50) default NULL,
  `SIM_Country_ID` tinyint(4) default NULL,
  `SIM_EMP_ID` int(5) unsigned default NULL,
  PRIMARY KEY  (`sim_id`),
  UNIQUE KEY `UNIQUE_SIM_IM_ID_SIM_Name` (`SIM_IM_ID`,`SIM_Name`),
  KEY `FK_SIM_StateMaster_SM_ID` (`SIM_State_ID`),
  KEY `fk_Countrymaster_SIM_Country_ID` (`SIM_Country_ID`),
  KEY `FK_SIM_EMP_ID_employeemaster` (`SIM_EMP_ID`),
  CONSTRAINT `fk_Countrymaster_SIM_Country_ID` FOREIGN KEY (`SIM_Country_ID`) REFERENCES `countrymaster` (`Country_ID`),
  CONSTRAINT `FK_SIM_EMP_ID_employeemaster` FOREIGN KEY (`SIM_EMP_ID`) REFERENCES `employeemaster` (`EMP_ID`),
  CONSTRAINT `FK_SIM_InstitutionMaster_IM_ID` FOREIGN KEY (`SIM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SIM_StateMaster_SM_ID` FOREIGN KEY (`SIM_State_ID`) REFERENCES `statemaster` (`State_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subinstitutionmaster`
--

LOCK TABLES `subinstitutionmaster` WRITE;
/*!40000 ALTER TABLE `subinstitutionmaster` DISABLE KEYS */;
INSERT INTO `subinstitutionmaster` VALUES (1,1,'Faculty of Natural Sciences','FNS',31,'Old Campus, Jamia Millia Islamia','Jamia Nagar','New Delhi',1,'110025','smrashid@jmi.ac.in',NULL,NULL),(3,1,'Office of the Registrar','Registrar',34,'Administration Building, Jamia Millia Islamia','Jamia Nagar','New Delhi',1,'110025','ssajid@jmi.ac.in',NULL,NULL),(4,1,'FTK-Centre For Information Technology','FTK-CIT',32,'Near Ansari Auditorium, ','Jamia Nagar','New Delhi',1,'110025','zhkhan@jmi.ac.in',NULL,NULL),(5,1,'Faculty of Engineering & Technology','FET',31,'Engineering Campus','Maulana Mohammad Ali Johar Marg','Delhi',1,'110025','kmoin@jmi.ac.in',NULL,NULL),(6,1,'Faculty of Education','FED',31,'Facuty of Education Campus','Tikona Park','Delhi',1,'110025','kazim.cit@jmi.ac.in',NULL,NULL),(7,1,'Faculty of Architecture','FOA',31,'Engineering College Campus','JMI','Delhi',1,'110025','kazim.cit@jmi.ac.in',NULL,NULL),(8,1,'Faculty of Fine Arts','FOF',31,'Faculty of Fine Arts','','Delhi',1,'110025','k@j.c',NULL,NULL),(9,1,'AJK-MCRC','MCRC',31,'AJK-MCRC','','Delhi',1,'11025','kazim',NULL,NULL),(10,1,'Faculty of Dentistry','FOD',31,'Dean, Faculty of Dentistry','Dean, Faculty of Dentistry','New Delhi',1,'110025','sknaqvi@jmi.ac.in',NULL,NULL);
/*!40000 ALTER TABLE `subinstitutionmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpmprogram`
--

DROP TABLE IF EXISTS `erpmprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpmprogram` (
  `ERPMP_ID` smallint(6) NOT NULL auto_increment,
  `ERPMP_Display_Name` varchar(50) NOT NULL,
  `ERPMP_type` enum('T','R','W','M') NOT NULL default 'T' COMMENT 'T(ransaction), M(aster), R(eport), W(eb Page)',
  `ERPMP_View_Program` varchar(100) NOT NULL,
  `ERPMP_ERPMM_ID` tinyint(3) unsigned NOT NULL,
  `erpmp_purpose` varchar(100) default NULL,
  PRIMARY KEY  (`ERPMP_ID`),
  KEY `FK_ERPMP_ERPMM_ERPMM_ID` (`ERPMP_ERPMM_ID`),
  CONSTRAINT `FK_ERPMP_ERPMM_ERPMM_ID` FOREIGN KEY (`ERPMP_ERPMM_ID`) REFERENCES `erpmmodule` (`ERPMM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpmprogram`
--

LOCK TABLES `erpmprogram` WRITE;
/*!40000 ALTER TABLE `erpmprogram` DISABLE KEYS */;
INSERT INTO `erpmprogram` VALUES (1,'Welcome','W','welcome.jsp',1,'To display welcome screen for user'),(2,'Budget Allocation','M','Allocate_Budget.jsp',1,'To let user allocate budget'),(3,'Query Budget Allocation','T','Query_Budget.jsp',1,'To let user query budget allocation');
/*!40000 ALTER TABLE `erpmprogram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erpm_purchasechallan_master`
--

DROP TABLE IF EXISTS `erpm_purchasechallan_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `erpm_purchasechallan_master` (
  `PCM_PCM_ID` int(11) unsigned NOT NULL auto_increment,
  `PCM_IM_ID` smallint(5) unsigned NOT NULL,
  `PCM_SIM_ID` mediumint(5) unsigned NOT NULL,
  `PCM_DM_ID` mediumint(5) unsigned NOT NULL,
  `PCM_Recv_Date` date default NULL,
  `PCM_Challan_No` varchar(20) default NULL,
  `PCM_Challan_Date` date default NULL,
  `PCM_PO_Master_ID` int(10) unsigned NOT NULL,
  `PCM_ImportExchange_Rate` decimal(5,2) default NULL,
  `PCM_Remarks` varchar(500) default NULL,
  `PCM_BillMaster_ID` int(10) unsigned NOT NULL default '0',
  `PCM_PreparedBy_ID` mediumint(9) unsigned default NULL,
  `PCM_Checked_By` varchar(50) default NULL,
  PRIMARY KEY  (`PCM_PCM_ID`),
  UNIQUE KEY `PCM_ID_SIM_DM_PO_CHNO_UNIQUE` (`PCM_IM_ID`,`PCM_SIM_ID`,`PCM_DM_ID`,`PCM_Challan_No`,`PCM_PO_Master_ID`),
  KEY `FK_PCM_IM_ID` (`PCM_IM_ID`),
  KEY `FK_PCM_SIM_ID` (`PCM_SIM_ID`),
  KEY `FK_PCM_DM_ID` (`PCM_DM_ID`),
  KEY `FK_PCM_PO_Master_ID` (`PCM_PO_Master_ID`),
  KEY `FK_PCM_PreparedBy_ID` (`PCM_PreparedBy_ID`),
  CONSTRAINT `FK_PCM_DM_ID` FOREIGN KEY (`PCM_DM_ID`) REFERENCES `departmentmaster` (`DM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PCM_IM_ID` FOREIGN KEY (`PCM_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PCM_PO_Master_ID` FOREIGN KEY (`PCM_PO_Master_ID`) REFERENCES `erpm_po_master` (`POM_PO_Master_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PCM_PreparedBy_ID` FOREIGN KEY (`PCM_PreparedBy_ID`) REFERENCES `erpmusers` (`ERPMU_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PCM_SIM_ID` FOREIGN KEY (`PCM_SIM_ID`) REFERENCES `subinstitutionmaster` (`sim_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erpm_purchasechallan_master`
--

LOCK TABLES `erpm_purchasechallan_master` WRITE;
/*!40000 ALTER TABLE `erpm_purchasechallan_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `erpm_purchasechallan_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutionroleprivileges`
--

DROP TABLE IF EXISTS `institutionroleprivileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutionroleprivileges` (
  `IUP_ID` smallint(6) unsigned NOT NULL auto_increment,
  `IUP_ERPM_ID` tinyint(3) unsigned default NULL,
  `IUP_ERPMP_ID` smallint(6) NOT NULL,
  `IUP_IUR_ID` mediumint(8) unsigned NOT NULL,
  `IUP_Can_Add` tinyint(1) default NULL,
  `IUP_Can_Delete` tinyint(1) default NULL,
  `IUP_Can_Edit` tinyint(1) default NULL,
  `IUP_Can_View` tinyint(1) default NULL,
  `IUP_IM_ID` smallint(5) unsigned default NULL,
  PRIMARY KEY  (`IUP_ID`),
  KEY `FK_IUP_ERPMM_ERPMM_ID` (`IUP_ERPM_ID`),
  KEY `FK_IUP_ERPMP_ERPMP_ID` (`IUP_ERPMP_ID`),
  KEY `FK_IUP_IUR_IUR_ID` (`IUP_IUR_ID`),
  KEY `FK_IUP_IM_ID` (`IUP_IM_ID`),
  CONSTRAINT `FK_IUP_ERPMM_ERPMM_ID` FOREIGN KEY (`IUP_ERPM_ID`) REFERENCES `erpmmodule` (`ERPMM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_IUP_ERPMP_ERPMP_ID` FOREIGN KEY (`IUP_ERPMP_ID`) REFERENCES `erpmprogram` (`ERPMP_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_IUP_IM_ID` FOREIGN KEY (`IUP_IM_ID`) REFERENCES `institutionmaster` (`IM_ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_IUP_IUR_IUR_ID` FOREIGN KEY (`IUP_IUR_ID`) REFERENCES `institutionuserroles` (`IUR_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutionroleprivileges`
--

LOCK TABLES `institutionroleprivileges` WRITE;
/*!40000 ALTER TABLE `institutionroleprivileges` DISABLE KEYS */;
INSERT INTO `institutionroleprivileges` VALUES (1,1,1,2,1,0,1,0,1),(2,1,3,1,0,0,0,0,1);
/*!40000 ALTER TABLE `institutionroleprivileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genericuserroles`
--

DROP TABLE IF EXISTS `genericuserroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genericuserroles` (
  `GUR_ID` tinyint(3) unsigned NOT NULL auto_increment,
  `GUR_Role_Name` varchar(50) NOT NULL,
  `GUR_Description` varchar(100) default NULL,
  PRIMARY KEY  (`GUR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='This table holds user roles e.eg. Administrator, Manager etc';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genericuserroles`
--

LOCK TABLES `genericuserroles` WRITE;
/*!40000 ALTER TABLE `genericuserroles` DISABLE KEYS */;
INSERT INTO `genericuserroles` VALUES (1,'Administrator','Can do any opeartion including approving the user accounts and privileges'),(2,'Purchase Manager','Can approve items and their rates'),(3,'Purchase Staff','Allows for all clerical operations in te module'),(4,'Head of the Department','Can place purchase Order ');
/*!40000 ALTER TABLE `genericuserroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pico_basic'
--
/*!50003 DROP PROCEDURE IF EXISTS `copy_genericroleprivileges` */;
--
-- WARNING: old server version. The following dump may be incomplete.
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `copy_genericroleprivileges`(GUR_ID tinyint, IUR_ID mediumint, IM_ID smallint)
BEGIN

DECLARE done INT DEFAULT 0;

DECLARE var_GUP_ID smallint(6);

DECLARE var_GUP_ERPMM_ID tinyint(3);

DECLARE var_GUP_ERPMP_ID smallint(6);

DECLARE var_GUP_GUR_ID tinyint(3);

DECLARE var_GUP_Can_Add enum('0','1');

DECLARE var_GUP_Can_Delete enum('1','0');

DECLARE var_GUP_Can_Edit enum('0','1');

DECLARE var_GUP_Can_View enum('0','1');



 

DECLARE GRP CURSOR FOR Select * from genericroleprivileges where gup_gur_id = GUR_ID and

            gup_erpmp_id not in (select iup_erpmp_id from institutionroleprivileges where iup_iur_id = IUR_ID);



DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;



  OPEN GRP ;

    REPEAT

        FETCH GRP INTO  var_GUP_ID, var_GUP_ERPMM_ID, var_GUP_ERPMP_ID, var_GUP_GUR_ID, var_GUP_Can_Add,

                    var_GUP_Can_Delete, var_GUP_Can_Edit, var_GUP_Can_View;

        IF NOT done THEN

          insert into institutionroleprivileges (IUP_ERPM_ID, IUP_ERPMP_ID, IUP_IUR_ID, IUP_Can_Add, IUP_Can_Delete, IUP_Can_Edit,

            IUP_Can_View, IUP_IM_ID)

            values

        (var_GUP_ERPMM_ID, var_GUP_ERPMP_ID, IUR_ID, var_GUP_Can_Add, var_GUP_Can_Delete, var_GUP_Can_Edit, var_GUP_Can_View, IM_ID);

        END IF;

  UNTIL done END REPEAT;

  CLOSE GRP ;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-09-03 11:17:07
