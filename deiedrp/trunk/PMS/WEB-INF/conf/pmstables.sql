-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pmstables
--

CREATE DATABASE IF NOT EXISTS pms;
USE pms;

--
-- Definition of table `file`
--

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `File_Name` varchar(255) NOT NULL,
  `File_Type` varchar(255) NOT NULL,
  `Owner` varchar(255) NOT NULL,
  `Size` int(10) unsigned NOT NULL,
  `Project_Name` varchar(255) NOT NULL,
  `File_Description` text NOT NULL,
  `Location` text NOT NULL,
  PRIMARY KEY (`File_Name`,`Owner`,`Project_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `file`
--

/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `User_ID` varchar(255) NOT NULL,
  `Authority` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`User_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`User_ID`,`Authority`,`Password`) VALUES 
 ('admin','Admin','d033e22ae348aeb5660fc2140aec35850c4da997');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
CREATE TABLE `organisation` (
  `Org_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Org_Name` varchar(255) NOT NULL,
  `Org_Address` varchar(455) NOT NULL,
  `Org_City` varchar(255) NOT NULL,
  `Org_State` varchar(255) NOT NULL,
  `Pincode` mediumint(6) unsigned DEFAULT NULL,
  `Org_Fax` varchar(255) DEFAULT NULL,
  `Org_URL` varchar(255) NOT NULL,
  `Org_Head` varchar(255) NOT NULL,
  `Org_Email_Id` varchar(255) NOT NULL,
  `Org_Phone` bigint(20) unsigned NOT NULL,
  `Org_Description` text,
  PRIMARY KEY (`Org_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;


--
-- Definition of table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `Project_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Project_Name` varchar(255) NOT NULL,
  `Start_Date` date NOT NULL,
  `Finish_Date` date NOT NULL,
  `Target_Budget` int(10) unsigned NOT NULL,
  `Priority` char(255) NOT NULL,
  `Status` varchar(255) NOT NULL,
  `View_Permission` varchar(255) NOT NULL COMMENT 'view permission of project',
  `GChart_Color` varchar(255) NOT NULL COMMENT 'gantt chart color',
  `Description` text NOT NULL,
  `Assigned` tinyint(1) unsigned DEFAULT '0',
  `Enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`Project_ID`,`Project_Name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

--
-- Definition of table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `Task_Name` varchar(225) NOT NULL,
  `Start_Date` date NOT NULL,
  `Finished_Date` date NOT NULL,
  `Gchart_Color` varchar(8) NOT NULL,
  `Per_Completed` smallint(3) unsigned DEFAULT NULL,
  `Dependency` varchar(255) DEFAULT NULL,
  `Description` text,
  `Task_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Valid_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Task_ID`),
  KEY `Valid_ID` (`Valid_ID`),
  CONSTRAINT `Valid_ID` FOREIGN KEY (`Valid_ID`) REFERENCES `validatetab` (`Valid_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
--
-- Definition of table `validatetab`
--

DROP TABLE IF EXISTS `validatetab`;
CREATE TABLE `validatetab` (
  `User_ID` varchar(255) NOT NULL,
  `PermittedBy` varchar(255) NOT NULL,
  `Project_ID` int(10) unsigned NOT NULL,
  `Org_ID` int(10) unsigned NOT NULL,
  `Valid_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Valid_ID`),
  KEY `User_ID` (`User_ID`),
  KEY `Project_ID` (`Project_ID`),
  KEY `Org_ID` (`Org_ID`),
  CONSTRAINT `Org_ID` FOREIGN KEY (`Org_ID`) REFERENCES `organisation` (`Org_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Project_ID` FOREIGN KEY (`Project_ID`) REFERENCES `project` (`Project_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `User_ID` FOREIGN KEY (`User_ID`) REFERENCES `login` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
