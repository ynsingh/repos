-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community
-- ================================================================= -->
--@author Anil Kumar Tiwari mailto:aniltiwari08@gmail.com        -->
-- ================================================================= -->



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pmsdatabase123
--

CREATE DATABASE IF NOT EXISTS pms;
USE pms;

--
-- Definition of table `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `state_id` int(10) unsigned NOT NULL,
  `city_id` int(10) unsigned NOT NULL,
  `city_name` varchar(255) NOT NULL,
  PRIMARY KEY (`state_id`,`city_id`),
  CONSTRAINT `state_id` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city`
--

/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`state_id`,`city_id`,`city_name`) VALUES 
 (1,1,'Agra'),
 (1,2,'Kanpur'),
 (1,3,'Lucknow'),
 (1,4,'Aligarh'),
 (1,5,'Hamirpur'),
 (1,6,'Allahabad'),
 (2,1,'Dehradun'),
 (2,2,'Uttarkashi'),
 (2,3,'Haridwar'),
 (2,4,'Rudraprayag'),
 (2,5,'Nainital'),
 (2,6,'Roorkee'),
 (3,1,'Mysore'),
 (3,2,'Gulberga'),
 (3,3,'Chitradurga'),
 (3,4,'Kolar'),
 (3,5,'Bijapur'),
 (3,6,'Bangalore Rural'),
 (3,7,'Belgaum'),
 (5,1,'Bhopal'),
 (5,2,'Indore'),
 (5,3,'Jabalpur'),
 (6,1,'Chennai'),
 (6,2,'Coimbotore'),
 (6,3,'Cuddalorei'),
 (6,4,'Dharmapuri'),
 (6,5,'Kancheepuram'),
 (6,6,'Ramanathapuram'),
 (6,7,'Tiruchiorappalli'),
 (6,8,'Nagapattinam'),
 (7,1,'Kathua'),
 (7,2,'Badgan'),
 (7,3,'Poonch'),
 (7,4,'Rajauri'),
 (7,5,'Baramula'),
 (7,6,'Doda'),
 (7,7,'Udhampur'),
 (7,8,'Jammu'),
 (7,9,'Kupwara'),
 (7,10,'Pulwama'),
 (7,11,'Anantnag'),
 (7,12,'Srinagar'),
 (7,13,'Leh'),
 (7,14,'Kargil'),
 (8,1,'Hamirpur'),
 (8,2,'Sirmour'),
 (8,3,'Kullu'),
 (8,4,'Solan'),
 (8,5,'Mandi'),
 (8,6,'Chamba'),
 (8,7,'Bilaspur'),
 (8,8,'Kangra'),
 (8,9,'Kinnaur'),
 (8,10,'Shimla'),
 (9,1,'New Delhi'),
 (9,2,'Central Delhi'),
 (9,3,'North Delhi'),
 (9,4,'East Delhi'),
 (9,5,'South Delhi'),
 (9,6,'West Delhi');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


--
-- Definition of table `default_authority`
--

DROP TABLE IF EXISTS `default_authority`;
CREATE TABLE `default_authority` (
  `authority_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `add_org` varchar(10) NOT NULL,
  `edit_remove_org` varchar(10) NOT NULL,
  `add_project` varchar(10) NOT NULL,
  `edit_disable_project` varchar(10) NOT NULL,
  `add_member` varchar(10) NOT NULL,
  `edit_remove_member` varchar(10) NOT NULL,
  `assign_project` varchar(10) NOT NULL,
  `edit_member_authority` varchar(10) NOT NULL,
  `assign_task` varchar(10) NOT NULL,
  `edit_remove_task` varchar(10) NOT NULL,
  `upload_documents` varchar(10) NOT NULL,
  `dwnld_remove_doc` varchar(10) NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `add_role` varchar(10) NOT NULL,
  `edit_remove_role` varchar(10) NOT NULL,
  PRIMARY KEY (`authority_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`Role_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `default_authority`
--



--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `login_user_id` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`login_user_id`),
  CONSTRAINT `login_user_id` FOREIGN KEY (`login_user_id`) REFERENCES `user_info` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`login_user_id`,`authority`,`password`) VALUES 
 ('superadmin','Super Admin','889a3a791b3875cfae413574b53da4bb8a90d53e');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `member_authority`
--

DROP TABLE IF EXISTS `member_authority`;
CREATE TABLE `member_authority` (
  `authority_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `add_org` varchar(10) NOT NULL,
  `edit_remove_org` varchar(10) NOT NULL,
  `add_project` varchar(10) NOT NULL,
  `edit_disable_project` varchar(10) NOT NULL,
  `add_member` varchar(10) NOT NULL,
  `edit_remove_member` varchar(10) NOT NULL,
  `assign_project` varchar(10) NOT NULL,
  `edit_member_authority` varchar(10) NOT NULL,
  `assign_task` varchar(10) NOT NULL,
  `edit_remove_task` varchar(10) NOT NULL,
  `upload_documents` varchar(10) NOT NULL,
  `dwnld_remove_doc` varchar(10) NOT NULL,
  `member_role_id` int(10) unsigned NOT NULL,
  `valid_project_id` char(12) NOT NULL,
  `member_id` varchar(255) NOT NULL,
  `add_role` varchar(10) NOT NULL,
  `edit_remove_role` varchar(10) NOT NULL,
  PRIMARY KEY (`authority_id`),
  KEY `member_role_id` (`member_role_id`),
  KEY `valid_project_id` (`valid_project_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `member_id` FOREIGN KEY (`member_id`) REFERENCES `user_info` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `member_role_id` FOREIGN KEY (`member_role_id`) REFERENCES `role` (`Role_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `valid_project_id` FOREIGN KEY (`valid_project_id`) REFERENCES `project` (`Project_Code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member_authority`
--

/*!40000 ALTER TABLE `member_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_authority` ENABLE KEYS */;


--
-- Definition of table `org_into_portal`
--

DROP TABLE IF EXISTS `org_into_portal`;
CREATE TABLE `org_into_portal` (
  `Portal_Id` int(10) unsigned NOT NULL,
  `Org_Id` int(4) unsigned zerofill NOT NULL,
  `valid_org_inportal` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`valid_org_inportal`),
  KEY `Portal_Id` (`Portal_Id`),
  CONSTRAINT `Portal_Id` FOREIGN KEY (`Portal_Id`) REFERENCES `portal` (`Portal_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `org_into_portal`
--



--
-- Definition of table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
CREATE TABLE `organisation` (
  `Org_Id` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Org_Name` varchar(255) NOT NULL,
  `Org_Address` text NOT NULL,
  `Org_City` int(10) unsigned NOT NULL,
  `Org_State` int(10) unsigned NOT NULL,
  `Org_Phone` varchar(25) NOT NULL,
  `Org_Fax` varchar(25) DEFAULT NULL,
  `Org_URL` varchar(255) NOT NULL,
  PRIMARY KEY (`Org_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisation`
--


--
-- Definition of table `pms_file`
--

DROP TABLE IF EXISTS `pms_file`;
CREATE TABLE `pms_file` (
  `File_Name` varchar(255) NOT NULL,
  `File_Type` varchar(255) NOT NULL,
  `File_Size` int(10) unsigned NOT NULL,
  `File_Description` text NOT NULL,
  `Location` text NOT NULL,
  `OrgPortal` int(10) unsigned NOT NULL,
  `ProjectCode` char(12) NOT NULL,
  `File_Owner` varchar(255) NOT NULL,
  PRIMARY KEY (`File_Name`,`ProjectCode`,`File_Owner`,`OrgPortal`),
  KEY `OrgPortal` (`OrgPortal`),
  KEY `ProjectCode` (`ProjectCode`),
  KEY `File_Owner` (`File_Owner`),
  CONSTRAINT `File_Owner` FOREIGN KEY (`File_Owner`) REFERENCES `user_info` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OrgPortal` FOREIGN KEY (`OrgPortal`) REFERENCES `org_into_portal` (`valid_org_inportal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ProjectCode` FOREIGN KEY (`ProjectCode`) REFERENCES `project` (`Project_Code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pms_file`
--

/*!40000 ALTER TABLE `pms_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_file` ENABLE KEYS */;


--
-- Definition of table `portal`
--

DROP TABLE IF EXISTS `portal`;
CREATE TABLE `portal` (
  `Portal_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Portal_Name` varchar(255) NOT NULL,
  `Portal_Description` text,
  `Created_By` varchar(255) NOT NULL,
  `Created_On` datetime NOT NULL,
  PRIMARY KEY (`Portal_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `portal`
--


--
-- Definition of table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `Project_Code` char(12) NOT NULL,
  `Project_Name` varchar(255) NOT NULL,
  `Schedule_Start_Date` date NOT NULL,
  `Schedule_End_Date` date NOT NULL,
  `Actual_Start_Date` date DEFAULT NULL,
  `Actual_End_Date` date DEFAULT NULL,
  `Target_Budget` int(10) unsigned NOT NULL,
  `Priority` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL,
  `GChart_Color` varchar(7) NOT NULL,
  `Description` text,
  `Assigned` tinyint(3) unsigned NOT NULL,
  `Enable` tinyint(3) unsigned NOT NULL,
  `Valid_Org_Inportal` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Project_Code`),
  KEY `Valid_Org_Inportal` (`Valid_Org_Inportal`),
  CONSTRAINT `Valid_Org_Inportal` FOREIGN KEY (`Valid_Org_Inportal`) REFERENCES `org_into_portal` (`valid_org_inportal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `Role_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Role_Name` varchar(255) NOT NULL,
  `Description` text,
  `Created_By` varchar(255) NOT NULL,
  `Created_On` datetime NOT NULL,
  `Updated_On` datetime NOT NULL,
  `ValidOrgPortal` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Role_Id`),
  KEY `ValidOrgPortal` (`ValidOrgPortal`),
  CONSTRAINT `ValidOrgPortal` FOREIGN KEY (`ValidOrgPortal`) REFERENCES `org_into_portal` (`valid_org_inportal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--


--
-- Definition of table `secure_question`
--

DROP TABLE IF EXISTS `secure_question`;
CREATE TABLE `secure_question` (
  `Secure_Qid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Secure_Question` varchar(455) NOT NULL,
  PRIMARY KEY (`Secure_Qid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `secure_question`
--

/*!40000 ALTER TABLE `secure_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `secure_question` ENABLE KEYS */;


--
-- Definition of table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `state_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) NOT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state`
--

/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` (`state_id`,`state_name`) VALUES 
 (1,'Uttar Pradesh'),
 (2,'Uttaranchal'),
 (3,'Karnataka'),
 (4,'Bihar'),
 (5,'Madhya Pradesh'),
 (6,'Tamil Nadu'),
 (7,'Jammu & Kashmir'),
 (8,'Himachal Pradesh'),
 (9,'Delhi');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;


--
-- Definition of table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `Task_Id` char(12) NOT NULL,
  `Task_Name` varchar(255) NOT NULL,
  `No_of_Days` smallint(5) unsigned NOT NULL,
  `Schedule_Start_Date` date NOT NULL,
  `Schedule_End_Date` date NOT NULL,
  `Actual_Start_Date` date DEFAULT NULL,
  `Actual_End_Date` date DEFAULT NULL,
  `GChart_Color` varchar(7) DEFAULT NULL,
  `Per_Completed` tinyint(3) unsigned DEFAULT '0',
  `Task_Status` varchar(13) DEFAULT NULL,
  `Dependency` char(16) DEFAULT NULL,
  `Description` text,
  `VProject_Code` char(12) NOT NULL,
  PRIMARY KEY (`Task_Id`),
  KEY `VProject_Code` (`VProject_Code`),
  CONSTRAINT `VProject_Code` FOREIGN KEY (`VProject_Code`) REFERENCES `project` (`Project_Code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

--
-- Definition of table `task_with_user`
--

DROP TABLE IF EXISTS `task_with_user`;
CREATE TABLE `task_with_user` (
  `Task_Id` char(12) NOT NULL,
  `Valid_Id` char(12) NOT NULL,
  PRIMARY KEY (`Task_Id`,`Valid_Id`),
  KEY `Valid_Id` (`Valid_Id`),
  CONSTRAINT `Task_Id` FOREIGN KEY (`Task_Id`) REFERENCES `task` (`Task_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Valid_Id` FOREIGN KEY (`Valid_Id`) REFERENCES `validatetab` (`Valid_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_with_user`
--


--
-- Definition of table `user_in_org`
--

DROP TABLE IF EXISTS `user_in_org`;
CREATE TABLE `user_in_org` (
  `Valid_User_Id` varchar(255) NOT NULL,
  `Valid_OrgPortal` int(10) unsigned NOT NULL,
  `Valid_Key` char(10) NOT NULL,
  PRIMARY KEY (`Valid_Key`),
  KEY `Valid_User_Id` (`Valid_User_Id`),
  KEY `Valid_OrgPortal` (`Valid_OrgPortal`),
  CONSTRAINT `Valid_OrgPortal` FOREIGN KEY (`Valid_OrgPortal`) REFERENCES `org_into_portal` (`valid_org_inportal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Valid_User_Id` FOREIGN KEY (`Valid_User_Id`) REFERENCES `user_info` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_in_org`
--


--
-- Definition of table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `User_ID` varchar(255) NOT NULL,
  `First_Name` varchar(255) DEFAULT NULL,
  `Last_Name` varchar(255) DEFAULT NULL,
  `Phone_No` varchar(25) DEFAULT NULL,
  `Skills` varchar(455) DEFAULT NULL,
  `Experince` tinyint(3) unsigned DEFAULT NULL,
  `Secure_Qid` int(10) unsigned DEFAULT NULL,
  `Secure_Ans` varchar(255) DEFAULT NULL,
  `Created_On` date NOT NULL,
  `Updated_On` date NOT NULL,
  PRIMARY KEY (`User_ID`),
  KEY `Secure_Qid` (`Secure_Qid`),
  CONSTRAINT `Secure_Qid` FOREIGN KEY (`Secure_Qid`) REFERENCES `secure_question` (`Secure_Qid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`User_ID`,`First_Name`,`Last_Name`,`Phone_No`,`Skills`,`Experince`,`Secure_Qid`,`Secure_Ans`,`Created_On`,`Updated_On`) VALUES 
 ('superadmin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-23','2010-08-23');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


--
-- Definition of table `user_role_in_org`
--

DROP TABLE IF EXISTS `user_role_in_org`;
CREATE TABLE `user_role_in_org` (
  `Valid_Key` char(10) NOT NULL,
  `Valid_Role` int(10) unsigned NOT NULL,
  `PermittedBy` varchar(255) NOT NULL,
  `Authority` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL,
  PRIMARY KEY (`Valid_Key`,`Valid_Role`),
  KEY `Valid_Role` (`Valid_Role`),
  KEY `PermittedBy` (`PermittedBy`),
  CONSTRAINT `PermittedBy` FOREIGN KEY (`PermittedBy`) REFERENCES `user_info` (`User_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Valid_Key` FOREIGN KEY (`Valid_Key`) REFERENCES `user_in_org` (`Valid_Key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Valid_Role` FOREIGN KEY (`Valid_Role`) REFERENCES `role` (`Role_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role_in_org`
--


--
-- Definition of table `validatetab`
--

DROP TABLE IF EXISTS `validatetab`;
CREATE TABLE `validatetab` (
  `Valid_Id` char(12) NOT NULL,
  `Valid_User_Key` char(10) NOT NULL,
  `Permitted_By` varchar(255) NOT NULL,
  `Valid_Project_Code` char(12) NOT NULL,
  `Valid_Role_Id` int(10) unsigned NOT NULL,
  `Authority` varchar(255) NOT NULL,
  `Status` varchar(8) NOT NULL,
  PRIMARY KEY (`Valid_Id`),
  KEY `Valid_User_Key` (`Valid_User_Key`),
  KEY `Valid_Project_Code` (`Valid_Project_Code`),
  KEY `Valid_Role_Id` (`Valid_Role_Id`),
  KEY `Permitted_By` (`Permitted_By`),
  CONSTRAINT `Permitted_By` FOREIGN KEY (`Permitted_By`) REFERENCES `user_info` (`User_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Valid_Project_Code` FOREIGN KEY (`Valid_Project_Code`) REFERENCES `project` (`Project_Code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Valid_Role_Id` FOREIGN KEY (`Valid_Role_Id`) REFERENCES `role` (`Role_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Valid_User_Key` FOREIGN KEY (`Valid_User_Key`) REFERENCES `user_in_org` (`Valid_Key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `validatetab`
--



