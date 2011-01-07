-- Auto Backup for MySQL Professional Edition 3.2
--
-- Host: localhost
--
-- MySQL Server Version: 5.1.32-community
--
-- 2011-01-06 17:02:53
--
-- ------------------------------------------------------

SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_CHARACTER_SET_CONNECTION=@@CHARACTER_SET_CONNECTION;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS;
SET UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES=@@SQL_NOTES;
SET SQL_NOTES=0;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `acq_privilege`;
CREATE TABLE IF NOT EXISTS `acq_privilege` (  `staff_id` varchar(100) NOT NULL DEFAULT '',  `library_id` varchar(20) NOT NULL DEFAULT '',  `acq_101` varchar(5) DEFAULT 'true',  `acq_102` varchar(5) DEFAULT 'true',  `acq_103` varchar(5) DEFAULT 'true',  `acq_104` varchar(5) DEFAULT 'true',  `acq_105` varchar(5) DEFAULT 'true',  `acq_106` varchar(5) DEFAULT 'true',  `acq_107` varchar(5) DEFAULT 'true',  `acq_108` varchar(5) DEFAULT 'true',  `acq_109` varchar(5) DEFAULT 'true',  `acq_110` varchar(5) DEFAULT 'true',  `acq_111` varchar(5) DEFAULT 'true',  `acq_112` varchar(5) DEFAULT 'true',  `acq_113` varchar(5) DEFAULT 'true',  `acq_114` varchar(5) DEFAULT 'true',  `acq_115` varchar(5) DEFAULT 'true',  `acq_116` varchar(5) DEFAULT 'true',  `acq_117` varchar(5) DEFAULT 'true',  `acq_118` varchar(5) DEFAULT 'true',  `acq_119` varchar(5) DEFAULT 'true',  `acq_120` varchar(5) DEFAULT 'true',  `acq_121` varchar(5) DEFAULT 'true',  `acq_122` varchar(5) DEFAULT 'true',  `acq_123` varchar(5) DEFAULT 'true',  `acq_124` varchar(5) DEFAULT 'true',  `acq_125` varchar(5) DEFAULT 'true',  `acq_126` varchar(5) DEFAULT 'true',  `acq_127` varchar(5) DEFAULT 'true',  `acq_128` varchar(5) DEFAULT 'true',  `acq_129` varchar(5) DEFAULT 'true',  `acq_130` varchar(5) DEFAULT 'true',  `acq_131` varchar(5) DEFAULT 'true',  `acq_132` varchar(5) DEFAULT 'true',  `acq_133` varchar(5) DEFAULT 'true',  `acq_134` varchar(5) DEFAULT 'true',  `acq_135` varchar(5) DEFAULT 'true',  `acq_136` varchar(5) DEFAULT 'true',  `acq_137` varchar(5) DEFAULT 'true',  `acq_138` varchar(5) DEFAULT 'true',  `acq_139` varchar(5) DEFAULT 'true',  `acq_140` varchar(5) DEFAULT 'true',  `acq_141` varchar(5) DEFAULT 'true',  `acq_142` varchar(5) DEFAULT 'true',  `acq_143` varchar(5) DEFAULT 'true',  `acq_144` varchar(5) DEFAULT 'true',  `acq_145` varchar(5) DEFAULT 'true',  `acq_146` varchar(5) DEFAULT 'true',  `acq_147` varchar(5) DEFAULT 'true',  `acq_148` varchar(5) DEFAULT 'true',  `acq_149` varchar(5) DEFAULT 'true',  `acq_150` varchar(5) DEFAULT 'true',  `acq_151` varchar(5) DEFAULT 'true',  `acq_152` varchar(5) DEFAULT 'true',  `acq_153` varchar(5) DEFAULT 'true',  `acq_154` varchar(5) DEFAULT 'true',  `acq_155` varchar(5) DEFAULT 'true',  `acq_156` varchar(5) DEFAULT 'true',  `acq_157` varchar(5) DEFAULT 'true',  `acq_158` varchar(5) DEFAULT 'true',  `acq_159` varchar(5) DEFAULT 'true',  `acq_160` varchar(5) DEFAULT 'true',  `acq_161` varchar(5) DEFAULT 'true',  `acq_162` varchar(5) DEFAULT 'true',  `acq_163` varchar(5) DEFAULT 'true',  `acq_164` varchar(5) DEFAULT 'true',  `acq_165` varchar(5) DEFAULT 'true',  `acq_166` varchar(5) DEFAULT 'true',  `acq_167` varchar(5) DEFAULT 'true',  `acq_168` varchar(5) DEFAULT 'true',  `acq_169` varchar(5) DEFAULT 'true',  `acq_170` varchar(5) DEFAULT 'true',  `acq_171` varchar(5) DEFAULT 'true',  `acq_172` varchar(5) DEFAULT 'true',  `acq_173` varchar(5) DEFAULT 'true',  `acq_174` varchar(5) DEFAULT 'true',  `acq_175` varchar(5) DEFAULT 'true',  `acq_176` varchar(5) DEFAULT 'true',  `acq_177` varchar(5) DEFAULT 'true',  `acq_178` varchar(5) DEFAULT 'true',  `acq_179` varchar(5) DEFAULT 'true',  `acq_180` varchar(5) DEFAULT 'true',  `acq_181` varchar(5) DEFAULT 'true',  `acq_182` varchar(5) DEFAULT 'true',  `acq_183` varchar(5) DEFAULT 'true',  `acq_184` varchar(5) DEFAULT 'true',  `acq_185` varchar(5) DEFAULT 'true',  `acq_186` varchar(5) DEFAULT 'true',  `acq_187` varchar(5) DEFAULT 'true',  `acq_188` varchar(5) DEFAULT 'true',  `acq_189` varchar(5) DEFAULT 'true',  `acq_190` varchar(5) DEFAULT 'true',  `acq_191` varchar(5) DEFAULT 'true',  `acq_192` varchar(5) DEFAULT 'true',  `acq_193` varchar(5) DEFAULT 'true',  `acq_194` varchar(5) DEFAULT 'true',  `acq_195` varchar(5) DEFAULT 'true',  `acq_196` varchar(5) DEFAULT 'true',  `acq_197` varchar(5) DEFAULT 'true',  `acq_198` varchar(5) DEFAULT 'true',  `acq_199` varchar(5) DEFAULT 'true',  PRIMARY KEY (`staff_id`,`library_id`),  KEY `staff_id` (`staff_id`,`library_id`),  CONSTRAINT `acq_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `acq_privilege` DISABLE KEYS;
LOCK TABLES `acq_privilege` WRITE;
UNLOCK TABLES;
ALTER TABLE `acq_privilege` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `admin_registration`;
CREATE TABLE IF NOT EXISTS `admin_registration` (  `registration_id` int(11) NOT NULL AUTO_INCREMENT,  `institute_name` varchar(100) DEFAULT NULL,  `abbreviated_name` varchar(20) DEFAULT NULL,  `Institute_address` varchar(100) DEFAULT NULL,  `city` varchar(40) DEFAULT NULL,  `state` varchar(50) DEFAULT NULL,  `Country` varchar(30) DEFAULT NULL,  `pin` varchar(20) DEFAULT NULL,  `land_line_no` varchar(20) DEFAULT NULL,  `mobile_no` varchar(20) DEFAULT NULL,  `domain` varchar(20) DEFAULT NULL,  `type_of_institute` varchar(20) DEFAULT NULL,  `website` varchar(50) DEFAULT NULL,  `admin_fname` varchar(50) DEFAULT NULL,  `admin_lname` varchar(50) DEFAULT NULL,  `admin_designation` varchar(50) DEFAULT NULL,  `admin_email` varchar(100) DEFAULT NULL,  `admin_password` varchar(20) DEFAULT NULL,  `status` varchar(20) DEFAULT NULL,  `library_id` varchar(50) DEFAULT NULL,  `library_name` varchar(500) DEFAULT NULL,  `courtesy` varchar(10) DEFAULT NULL,  `gender` varchar(10) DEFAULT NULL,  `staff_id` varchar(100) DEFAULT NULL,  PRIMARY KEY (`registration_id`),  UNIQUE KEY `library_id` (`library_id`)) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
ALTER TABLE `admin_registration` DISABLE KEYS;
LOCK TABLES `admin_registration` WRITE;
UNLOCK TABLES;
ALTER TABLE `admin_registration` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `cat_privilege`;
CREATE TABLE IF NOT EXISTS `cat_privilege` (  `staff_id` varchar(100) NOT NULL DEFAULT '',  `library_id` varchar(20) NOT NULL DEFAULT '',  `cat_201` varchar(5) DEFAULT 'true',  `cat_202` varchar(5) DEFAULT 'true',  `cat_203` varchar(5) DEFAULT 'true',  `cat_204` varchar(5) DEFAULT 'true',  `cat_205` varchar(5) DEFAULT 'true',  `cat_206` varchar(5) DEFAULT 'true',  `cat_207` varchar(5) DEFAULT 'true',  `cat_208` varchar(5) DEFAULT 'true',  `cat_209` varchar(5) DEFAULT 'true',  `cat_210` varchar(5) DEFAULT 'true',  `cat_211` varchar(5) DEFAULT 'true',  `cat_212` varchar(5) DEFAULT 'true',  `cat_213` varchar(5) DEFAULT 'true',  `cat_214` varchar(5) DEFAULT 'true',  `cat_215` varchar(5) DEFAULT 'true',  `cat_216` varchar(5) DEFAULT 'true',  `cat_217` varchar(5) DEFAULT 'true',  `cat_218` varchar(5) DEFAULT 'true',  `cat_219` varchar(5) DEFAULT 'true',  `cat_220` varchar(5) DEFAULT 'true',  `cat_221` varchar(5) DEFAULT 'true',  `cat_222` varchar(5) DEFAULT 'true',  `cat_223` varchar(5) DEFAULT 'true',  `cat_224` varchar(5) DEFAULT 'true',  `cat_225` varchar(5) DEFAULT 'true',  `cat_226` varchar(5) DEFAULT 'true',  `cat_227` varchar(5) DEFAULT 'true',  `cat_228` varchar(5) DEFAULT 'true',  `cat_229` varchar(5) DEFAULT 'true',  `cat_230` varchar(5) DEFAULT 'true',  `cat_231` varchar(5) DEFAULT 'true',  `cat_232` varchar(5) DEFAULT 'true',  `cat_233` varchar(5) DEFAULT 'true',  `cat_234` varchar(5) DEFAULT 'true',  `cat_235` varchar(5) DEFAULT 'true',  `cat_236` varchar(5) DEFAULT 'true',  `cat_237` varchar(5) DEFAULT 'true',  `cat_238` varchar(5) DEFAULT 'true',  `cat_239` varchar(5) DEFAULT 'true',  `cat_240` varchar(5) DEFAULT 'true',  `cat_241` varchar(5) DEFAULT 'true',  `cat_242` varchar(5) DEFAULT 'true',  `cat_243` varchar(5) DEFAULT 'true',  `cat_244` varchar(5) DEFAULT 'true',  `cat_245` varchar(5) DEFAULT 'true',  `cat_246` varchar(5) DEFAULT 'true',  `cat_247` varchar(5) DEFAULT 'true',  `cat_248` varchar(5) DEFAULT 'true',  `cat_249` varchar(5) DEFAULT 'true',  `cat_250` varchar(5) DEFAULT 'true',  `cat_251` varchar(5) DEFAULT 'true',  `cat_252` varchar(5) DEFAULT 'true',  `cat_253` varchar(5) DEFAULT 'true',  `cat_254` varchar(5) DEFAULT 'true',  `cat_255` varchar(5) DEFAULT 'true',  `cat_256` varchar(5) DEFAULT 'true',  `cat_257` varchar(5) DEFAULT 'true',  `cat_258` varchar(5) DEFAULT 'true',  `cat_259` varchar(5) DEFAULT 'true',  `cat_260` varchar(5) DEFAULT 'true',  `cat_261` varchar(5) DEFAULT 'true',  `cat_262` varchar(5) DEFAULT 'true',  `cat_263` varchar(5) DEFAULT 'true',  `cat_264` varchar(5) DEFAULT 'true',  `cat_265` varchar(5) DEFAULT 'true',  `cat_266` varchar(5) DEFAULT 'true',  `cat_267` varchar(5) DEFAULT 'true',  `cat_268` varchar(5) DEFAULT 'true',  `cat_269` varchar(5) DEFAULT 'true',  `cat_270` varchar(5) DEFAULT 'true',  `cat_271` varchar(5) DEFAULT 'true',  `cat_272` varchar(5) DEFAULT 'true',  `cat_273` varchar(5) DEFAULT 'true',  `cat_274` varchar(5) DEFAULT 'true',  `cat_275` varchar(5) DEFAULT 'true',  `cat_276` varchar(5) DEFAULT 'true',  `cat_277` varchar(5) DEFAULT 'true',  `cat_278` varchar(5) DEFAULT 'true',  `cat_279` varchar(5) DEFAULT 'true',  `cat_280` varchar(5) DEFAULT 'true',  `cat_281` varchar(5) DEFAULT 'true',  `cat_282` varchar(5) DEFAULT 'true',  `cat_283` varchar(5) DEFAULT 'true',  `cat_284` varchar(5) DEFAULT 'true',  `cat_285` varchar(5) DEFAULT 'true',  `cat_286` varchar(5) DEFAULT 'true',  `cat_287` varchar(5) DEFAULT 'true',  `cat_288` varchar(5) DEFAULT 'true',  `cat_289` varchar(5) DEFAULT 'true',  `cat_290` varchar(5) DEFAULT 'true',  `cat_291` varchar(5) DEFAULT 'true',  `cat_292` varchar(5) DEFAULT 'true',  `cat_293` varchar(5) DEFAULT 'true',  `cat_294` varchar(5) DEFAULT 'true',  `cat_295` varchar(5) DEFAULT 'true',  `cat_296` varchar(5) DEFAULT 'true',  `cat_297` varchar(5) DEFAULT 'true',  `cat_298` varchar(5) DEFAULT 'true',  `cat_299` varchar(5) DEFAULT 'true',  PRIMARY KEY (`staff_id`,`library_id`),  KEY `staff_id` (`staff_id`,`library_id`),  CONSTRAINT `cat_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `cat_privilege` DISABLE KEYS;
LOCK TABLES `cat_privilege` WRITE;
UNLOCK TABLES;
ALTER TABLE `cat_privilege` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `cir_privilege`;
CREATE TABLE IF NOT EXISTS `cir_privilege` (  `staff_id` varchar(100) NOT NULL DEFAULT '',  `library_id` varchar(20) NOT NULL DEFAULT '',  `cir_301` varchar(5) DEFAULT 'true',  `cir_302` varchar(5) DEFAULT 'true',  `cir_303` varchar(5) DEFAULT 'true',  `cir_304` varchar(5) DEFAULT 'true',  `cir_305` varchar(5) DEFAULT 'true',  `cir_306` varchar(5) DEFAULT 'true',  `cir_307` varchar(5) DEFAULT 'true',  `cir_308` varchar(5) DEFAULT 'true',  `cir_309` varchar(5) DEFAULT 'true',  `cir_310` varchar(5) DEFAULT 'true',  `cir_311` varchar(5) DEFAULT 'true',  `cir_312` varchar(5) DEFAULT 'true',  `cir_313` varchar(5) DEFAULT 'true',  `cir_314` varchar(5) DEFAULT 'true',  `cir_315` varchar(5) DEFAULT 'true',  `cir_316` varchar(5) DEFAULT 'true',  `cir_317` varchar(5) DEFAULT 'true',  `cir_318` varchar(5) DEFAULT 'true',  `cir_319` varchar(5) DEFAULT 'true',  `cir_320` varchar(5) DEFAULT 'true',  `cir_321` varchar(5) DEFAULT 'true',  `cir_322` varchar(5) DEFAULT 'true',  `cir_323` varchar(5) DEFAULT 'true',  `cir_324` varchar(5) DEFAULT 'true',  `cir_325` varchar(5) DEFAULT 'true',  `cir_326` varchar(5) DEFAULT 'true',  `cir_327` varchar(5) DEFAULT 'true',  `cir_328` varchar(5) DEFAULT 'true',  `cir_329` varchar(5) DEFAULT 'true',  `cir_330` varchar(5) DEFAULT 'true',  `cir_331` varchar(5) DEFAULT 'true',  `cir_332` varchar(5) DEFAULT 'true',  `cir_333` varchar(5) DEFAULT 'true',  `cir_334` varchar(5) DEFAULT 'true',  `cir_335` varchar(5) DEFAULT 'true',  `cir_336` varchar(5) DEFAULT 'true',  `cir_337` varchar(5) DEFAULT 'true',  `cir_338` varchar(5) DEFAULT 'true',  `cir_339` varchar(5) DEFAULT 'true',  `cir_340` varchar(5) DEFAULT 'true',  `cir_341` varchar(5) DEFAULT 'true',  `cir_342` varchar(5) DEFAULT 'true',  `cir_343` varchar(5) DEFAULT 'true',  `cir_344` varchar(5) DEFAULT 'true',  `cir_345` varchar(5) DEFAULT 'true',  `cir_346` varchar(5) DEFAULT 'true',  `cir_347` varchar(5) DEFAULT 'true',  `cir_348` varchar(5) DEFAULT 'true',  `cir_349` varchar(5) DEFAULT 'true',  `cir_350` varchar(5) DEFAULT 'true',  `cir_351` varchar(5) DEFAULT 'true',  `cir_352` varchar(5) DEFAULT 'true',  `cir_353` varchar(5) DEFAULT 'true',  `cir_354` varchar(5) DEFAULT 'true',  `cir_355` varchar(5) DEFAULT 'true',  `cir_356` varchar(5) DEFAULT 'true',  `cir_357` varchar(5) DEFAULT 'true',  `cir_358` varchar(5) DEFAULT 'true',  `cir_359` varchar(5) DEFAULT 'true',  `cir_360` varchar(5) DEFAULT 'true',  `cir_361` varchar(5) DEFAULT 'true',  `cir_362` varchar(5) DEFAULT 'true',  `cir_363` varchar(5) DEFAULT 'true',  `cir_364` varchar(5) DEFAULT 'true',  `cir_365` varchar(5) DEFAULT 'true',  `cir_366` varchar(5) DEFAULT 'true',  `cir_367` varchar(5) DEFAULT 'true',  `cir_368` varchar(5) DEFAULT 'true',  `cir_369` varchar(5) DEFAULT 'true',  `cir_370` varchar(5) DEFAULT 'true',  `cir_371` varchar(5) DEFAULT 'true',  `cir_372` varchar(5) DEFAULT 'true',  `cir_373` varchar(5) DEFAULT 'true',  `cir_374` varchar(5) DEFAULT 'true',  `cir_375` varchar(5) DEFAULT 'true',  `cir_376` varchar(5) DEFAULT 'true',  `cir_377` varchar(5) DEFAULT 'true',  `cir_378` varchar(5) DEFAULT 'true',  `cir_379` varchar(5) DEFAULT 'true',  `cir_380` varchar(5) DEFAULT 'true',  `cir_381` varchar(5) DEFAULT 'true',  `cir_382` varchar(5) DEFAULT 'true',  `cir_383` varchar(5) DEFAULT 'true',  `cir_384` varchar(5) DEFAULT 'true',  `cir_385` varchar(5) DEFAULT 'true',  `cir_386` varchar(5) DEFAULT 'true',  `cir_387` varchar(5) DEFAULT 'true',  `cir_388` varchar(5) DEFAULT 'true',  `cir_389` varchar(5) DEFAULT 'true',  `cir_390` varchar(5) DEFAULT 'true',  `cir_391` varchar(5) DEFAULT 'true',  `cir_392` varchar(5) DEFAULT 'true',  `cir_393` varchar(5) DEFAULT 'true',  `cir_394` varchar(5) DEFAULT 'true',  `cir_395` varchar(5) DEFAULT 'true',  `cir_396` varchar(5) DEFAULT 'true',  `cir_397` varchar(5) DEFAULT 'true',  `cir_398` varchar(5) DEFAULT 'true',  `cir_399` varchar(5) DEFAULT 'true',  PRIMARY KEY (`staff_id`,`library_id`),  KEY `staff_id` (`staff_id`,`library_id`),  CONSTRAINT `cir_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `cir_privilege` DISABLE KEYS;
LOCK TABLES `cir_privilege` WRITE;
UNLOCK TABLES;
ALTER TABLE `cir_privilege` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `demandlist`;
CREATE TABLE IF NOT EXISTS `demandlist` (  `library_name` varchar(50) DEFAULT NULL,  `id` varchar(50) DEFAULT NULL,  `title` varchar(50) DEFAULT NULL,  `category` varchar(50) DEFAULT NULL,  `author` varchar(50) DEFAULT NULL,  `publication` varchar(50) DEFAULT NULL,  `publish_yr` int(11) DEFAULT NULL,  `isbn` varchar(50) DEFAULT NULL,  `copy` int(11) DEFAULT NULL,  `volume` varchar(50) DEFAULT NULL,  `edition` varchar(50) DEFAULT NULL,  `remark` varchar(50) DEFAULT NULL,  `demand_date` date DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `demandlist` DISABLE KEYS;
LOCK TABLES `demandlist` WRITE;
UNLOCK TABLES;
ALTER TABLE `demandlist` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (  `title` varchar(100) DEFAULT NULL,  `author` varchar(100) DEFAULT NULL,  `accessionno` varchar(50) DEFAULT NULL,  `callno` varchar(50) DEFAULT NULL,  `subject` varchar(100) DEFAULT NULL,  `location` varchar(50) DEFAULT NULL,  `noofcopy` int(11) DEFAULT NULL,  `price` double(6,2) DEFAULT NULL,  `isbn` varchar(50) DEFAULT NULL,  `publisher` varchar(50) DEFAULT NULL,  `pubplace` varchar(50) DEFAULT NULL,  `subtitle` varchar(100) DEFAULT NULL,  `category` varchar(50) DEFAULT NULL,  `db_category` varchar(50) DEFAULT NULL,  `pub_yr` varchar(5) DEFAULT NULL,  `library_id` varchar(20) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `document` DISABLE KEYS;
LOCK TABLES `document` WRITE;
UNLOCK TABLES;
ALTER TABLE `document` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (  `cardno` varchar(60) DEFAULT NULL,  `name` varchar(100) DEFAULT NULL,  `email` varchar(100) DEFAULT NULL,  `comments` text,  `date` varchar(15) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `feedback` DISABLE KEYS;
LOCK TABLES `feedback` WRITE;
UNLOCK TABLES;
ALTER TABLE `feedback` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `fine_details`;
CREATE TABLE IF NOT EXISTS `fine_details` (  `memid` varchar(50) NOT NULL DEFAULT '',  `accessionno` varchar(50) DEFAULT NULL,  `callno` varchar(50) DEFAULT NULL,  `isbn` varchar(50) DEFAULT NULL,  `date` varchar(15) NOT NULL DEFAULT '',  `fine` float NOT NULL DEFAULT '0') ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `fine_details` DISABLE KEYS;
LOCK TABLES `fine_details` WRITE;
UNLOCK TABLES;
ALTER TABLE `fine_details` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `library`;
CREATE TABLE IF NOT EXISTS `library` (  `registration_id` int(11) DEFAULT NULL,  `library_id` varchar(20) NOT NULL,  `library_name` varchar(50) DEFAULT NULL,  `staff_id` varchar(100) DEFAULT NULL,  PRIMARY KEY (`library_id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `library` DISABLE KEYS;
LOCK TABLES `library` WRITE;
UNLOCK TABLES;
ALTER TABLE `library` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (  `user_id` varchar(100) NOT NULL,  `user_name` varchar(50) DEFAULT NULL,  `password` varchar(20) DEFAULT NULL,  `library_id` varchar(50) DEFAULT NULL,  `staff_id` varchar(100) DEFAULT NULL,  `question` varchar(1000) DEFAULT NULL,  `ans` varchar(500) DEFAULT NULL,  PRIMARY KEY (`user_id`),  KEY `login_ibfk_1` (`staff_id`,`library_id`),  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE,  CONSTRAINT `login_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `staff_detail` (`emai_id`) ON UPDATE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `login` DISABLE KEYS;
LOCK TABLES `login` WRITE;
INSERT INTO `login` VALUES ('superadmin','superadmin','superadmin','libms','admin.libms',NULL,NULL);
UNLOCK TABLES;
ALTER TABLE `login` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (  `library_name` varchar(100) DEFAULT NULL,  `library_id` varchar(50) DEFAULT NULL,  `category` varchar(25) DEFAULT NULL,  `fname` varchar(50) DEFAULT NULL,  `mname` varchar(40) DEFAULT NULL,  `lname` varchar(40) DEFAULT NULL,  `subject` varchar(100) DEFAULT NULL,  `address1` varchar(200) DEFAULT NULL,  `address2` varchar(200) DEFAULT NULL,  `city` varchar(50) DEFAULT NULL,  `state` varchar(50) DEFAULT NULL,  `pin` varchar(15) DEFAULT NULL,  `country` varchar(60) DEFAULT NULL,  `email` varchar(100) DEFAULT NULL,  `fax` varchar(100) DEFAULT NULL,  `phone1` varchar(15) DEFAULT NULL,  `phone2` varchar(15) DEFAULT NULL,  `requestdate` varchar(15) DEFAULT NULL,  `faculty` varchar(100) DEFAULT NULL,  `dept` varchar(100) DEFAULT NULL,  `memId` varchar(70) DEFAULT NULL,  `rollno` varchar(40) DEFAULT NULL,  `course` varchar(60) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `member` DISABLE KEYS;
LOCK TABLES `member` WRITE;
UNLOCK TABLES;
ALTER TABLE `member` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `member_accounthistory`;
CREATE TABLE IF NOT EXISTS `member_accounthistory` (  `memid` varchar(50) NOT NULL DEFAULT '',  `memberName` varchar(100) DEFAULT NULL,  `fine` float DEFAULT NULL,  `no_of_chkout` int(11) DEFAULT NULL,  `reservation_made` int(11) DEFAULT NULL,  `lastchkoutdate` varchar(15) DEFAULT NULL,  `alive_status` char(2) DEFAULT NULL,  PRIMARY KEY (`memid`),  UNIQUE KEY `memid` (`memid`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `member_accounthistory` DISABLE KEYS;
LOCK TABLES `member_accounthistory` WRITE;
UNLOCK TABLES;
ALTER TABLE `member_accounthistory` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `newarrivals`;
CREATE TABLE IF NOT EXISTS `newarrivals` (  `title` varchar(100) DEFAULT NULL,  `author` varchar(100) DEFAULT NULL,  `accessionno` varchar(50) NOT NULL DEFAULT '',  `callno` varchar(50) DEFAULT NULL,  `subject` varchar(100) DEFAULT NULL,  `location` varchar(50) DEFAULT NULL,  `noofcopy` int(11) DEFAULT NULL,  `price` double(6,2) DEFAULT NULL,  `isbn` varchar(50) DEFAULT NULL,  `publisher` varchar(50) DEFAULT NULL,  `pubplace` varchar(50) DEFAULT NULL,  `subtitle` varchar(100) DEFAULT NULL,  `category` varchar(50) DEFAULT NULL,  `pub_yr` varchar(5) DEFAULT NULL,  `arrival_date` date NOT NULL DEFAULT '0000-00-00',  `library_id` varchar(50) DEFAULT NULL,  PRIMARY KEY (`accessionno`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `newarrivals` DISABLE KEYS;
LOCK TABLES `newarrivals` WRITE;
UNLOCK TABLES;
ALTER TABLE `newarrivals` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (  `notice_id` int(11) DEFAULT NULL,  `subject` varchar(59) DEFAULT NULL,  `message` varchar(198) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `notice` DISABLE KEYS;
LOCK TABLES `notice` WRITE;
UNLOCK TABLES;
ALTER TABLE `notice` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `privilege`;
CREATE TABLE IF NOT EXISTS `privilege` (  `staff_id` varchar(100) NOT NULL DEFAULT '',  `library_id` varchar(50) NOT NULL DEFAULT '',  `acquisition` varchar(5) DEFAULT 'true',  `cataloguing` varchar(5) DEFAULT 'true',  `circulation` varchar(5) DEFAULT 'true',  `serial` varchar(5) DEFAULT 'true',  `administrator` varchar(5) DEFAULT 'true',  `system_setup` varchar(5) DEFAULT 'true',  `utilities` varchar(5) DEFAULT 'true',  `opac` varchar(5) DEFAULT 'true',  PRIMARY KEY (`staff_id`,`library_id`),  KEY `staff_id` (`staff_id`,`library_id`),  CONSTRAINT `privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `login` (`staff_id`, `library_id`) ON DELETE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `privilege` DISABLE KEYS;
LOCK TABLES `privilege` WRITE;
UNLOCK TABLES;
ALTER TABLE `privilege` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `reservationlist`;
CREATE TABLE IF NOT EXISTS `reservationlist` (  `library_name` varchar(100) DEFAULT NULL,  `card_id` varchar(50) DEFAULT NULL,  `title` varchar(100) DEFAULT NULL,  `category` varchar(25) DEFAULT NULL,  `author` varchar(100) DEFAULT NULL,  `isbn` varchar(200) DEFAULT NULL,  `callno` varchar(50) DEFAULT NULL,  `edition` varchar(25) DEFAULT NULL,  `volume` varchar(25) DEFAULT NULL,  `publication` varchar(50) DEFAULT NULL,  `remark` varchar(100) DEFAULT NULL,  `request_date` varchar(15) DEFAULT NULL,  `status` varchar(10) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `reservationlist` DISABLE KEYS;
LOCK TABLES `reservationlist` WRITE;
UNLOCK TABLES;
ALTER TABLE `reservationlist` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `ser_privilege`;
CREATE TABLE IF NOT EXISTS `ser_privilege` (  `staff_id` varchar(100) NOT NULL DEFAULT '',  `library_id` varchar(20) NOT NULL DEFAULT '',  `ser_401` varchar(5) DEFAULT 'true',  `ser_402` varchar(5) DEFAULT 'true',  `ser_403` varchar(5) DEFAULT 'true',  `ser_404` varchar(5) DEFAULT 'true',  `ser_405` varchar(5) DEFAULT 'true',  `ser_406` varchar(5) DEFAULT 'true',  `ser_407` varchar(5) DEFAULT 'true',  `ser_408` varchar(5) DEFAULT 'true',  `ser_409` varchar(5) DEFAULT 'true',  `ser_410` varchar(5) DEFAULT 'true',  `ser_411` varchar(5) DEFAULT 'true',  `ser_412` varchar(5) DEFAULT 'true',  `ser_413` varchar(5) DEFAULT 'true',  `ser_414` varchar(5) DEFAULT 'true',  `ser_415` varchar(5) DEFAULT 'true',  `ser_416` varchar(5) DEFAULT 'true',  `ser_417` varchar(5) DEFAULT 'true',  `ser_418` varchar(5) DEFAULT 'true',  `ser_419` varchar(5) DEFAULT 'true',  `ser_420` varchar(5) DEFAULT 'true',  `ser_421` varchar(5) DEFAULT 'true',  `ser_422` varchar(5) DEFAULT 'true',  `ser_423` varchar(5) DEFAULT 'true',  `ser_424` varchar(5) DEFAULT 'true',  `ser_425` varchar(5) DEFAULT 'true',  `ser_426` varchar(5) DEFAULT 'true',  `ser_427` varchar(5) DEFAULT 'true',  `ser_428` varchar(5) DEFAULT 'true',  `ser_429` varchar(5) DEFAULT 'true',  `ser_430` varchar(5) DEFAULT 'true',  `ser_431` varchar(5) DEFAULT 'true',  `ser_432` varchar(5) DEFAULT 'true',  `ser_433` varchar(5) DEFAULT 'true',  `ser_434` varchar(5) DEFAULT 'true',  `ser_435` varchar(5) DEFAULT 'true',  `ser_436` varchar(5) DEFAULT 'true',  `ser_437` varchar(5) DEFAULT 'true',  `ser_438` varchar(5) DEFAULT 'true',  `ser_439` varchar(5) DEFAULT 'true',  `ser_440` varchar(5) DEFAULT 'true',  `ser_441` varchar(5) DEFAULT 'true',  `ser_442` varchar(5) DEFAULT 'true',  `ser_443` varchar(5) DEFAULT 'true',  `ser_444` varchar(5) DEFAULT 'true',  `ser_445` varchar(5) DEFAULT 'true',  `ser_446` varchar(5) DEFAULT 'true',  `ser_447` varchar(5) DEFAULT 'true',  `ser_448` varchar(5) DEFAULT 'true',  `ser_449` varchar(5) DEFAULT 'true',  `ser_450` varchar(5) DEFAULT 'true',  `ser_451` varchar(5) DEFAULT 'true',  `ser_452` varchar(5) DEFAULT 'true',  `ser_453` varchar(5) DEFAULT 'true',  `ser_454` varchar(5) DEFAULT 'true',  `ser_455` varchar(5) DEFAULT 'true',  `ser_456` varchar(5) DEFAULT 'true',  `ser_457` varchar(5) DEFAULT 'true',  `ser_458` varchar(5) DEFAULT 'true',  `ser_459` varchar(5) DEFAULT 'true',  `ser_460` varchar(5) DEFAULT 'true',  `ser_461` varchar(5) DEFAULT 'true',  `ser_462` varchar(5) DEFAULT 'true',  `ser_463` varchar(5) DEFAULT 'true',  `ser_464` varchar(5) DEFAULT 'true',  `ser_465` varchar(5) DEFAULT 'true',  `ser_466` varchar(5) DEFAULT 'true',  `ser_467` varchar(5) DEFAULT 'true',  `ser_468` varchar(5) DEFAULT 'true',  `ser_469` varchar(5) DEFAULT 'true',  `ser_470` varchar(5) DEFAULT 'true',  `ser_471` varchar(5) DEFAULT 'true',  `ser_472` varchar(5) DEFAULT 'true',  `ser_473` varchar(5) DEFAULT 'true',  `ser_474` varchar(5) DEFAULT 'true',  `ser_475` varchar(5) DEFAULT 'true',  `ser_476` varchar(5) DEFAULT 'true',  `ser_477` varchar(5) DEFAULT 'true',  `ser_478` varchar(5) DEFAULT 'true',  `ser_479` varchar(5) DEFAULT 'true',  `ser_480` varchar(5) DEFAULT 'true',  `ser_481` varchar(5) DEFAULT 'true',  `ser_482` varchar(5) DEFAULT 'true',  `ser_483` varchar(5) DEFAULT 'true',  `ser_484` varchar(5) DEFAULT 'true',  `ser_485` varchar(5) DEFAULT 'true',  `ser_486` varchar(5) DEFAULT 'true',  `ser_487` varchar(5) DEFAULT 'true',  `ser_488` varchar(5) DEFAULT 'true',  `ser_489` varchar(5) DEFAULT 'true',  `ser_490` varchar(5) DEFAULT 'true',  `ser_491` varchar(5) DEFAULT 'true',  `ser_492` varchar(5) DEFAULT 'true',  `ser_493` varchar(5) DEFAULT 'true',  `ser_494` varchar(5) DEFAULT 'true',  `ser_495` varchar(5) DEFAULT 'true',  `ser_496` varchar(5) DEFAULT 'true',  `ser_497` varchar(5) DEFAULT 'true',  `ser_498` varchar(5) DEFAULT 'true',  `ser_499` varchar(5) DEFAULT 'true',  PRIMARY KEY (`staff_id`,`library_id`),  KEY `staff_id` (`staff_id`,`library_id`),  CONSTRAINT `ser_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `ser_privilege` DISABLE KEYS;
LOCK TABLES `ser_privilege` WRITE;
UNLOCK TABLES;
ALTER TABLE `ser_privilege` ENABLE KEYS;
SET CHARACTER_SET_CLIENT='latin1';
SET CHARACTER_SET_RESULTS='latin1';
SET CHARACTER_SET_CONNECTION='latin1';
SET NAMES 'latin1';
CREATE DATABASE IF NOT EXISTS `libms` DEFAULT CHARACTER SET latin1;
USE `libms`;

DROP TABLE IF EXISTS `staff_detail`;
CREATE TABLE IF NOT EXISTS `staff_detail` (  `staff_id` varchar(100) NOT NULL,  `title` varchar(5) DEFAULT NULL,  `first_name` varchar(20) DEFAULT NULL,  `last_name` varchar(20) DEFAULT NULL,  `contact_no` varchar(20) DEFAULT NULL,  `mobile_no` varchar(20) DEFAULT NULL,  `emai_id` varchar(100) DEFAULT NULL,  `date_joining` date DEFAULT NULL,  `date_releaving` date DEFAULT NULL,  `father_name` varchar(30) DEFAULT NULL,  `date_of_birth` date DEFAULT NULL,  `gender` varchar(8) DEFAULT NULL,  `address1` varchar(50) DEFAULT NULL,  `city1` varchar(20) DEFAULT NULL,  `state1` varchar(20) DEFAULT NULL,  `country1` varchar(20) DEFAULT NULL,  `zip1` varchar(20) DEFAULT NULL,  `address2` varchar(50) DEFAULT NULL,  `city2` varchar(20) DEFAULT NULL,  `state2` varchar(20) DEFAULT NULL,  `country2` varchar(20) DEFAULT NULL,  `zip2` varchar(20) DEFAULT NULL,  `library_id` varchar(20) NOT NULL DEFAULT '',  PRIMARY KEY (`staff_id`,`library_id`),  UNIQUE KEY `emai_id` (`emai_id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `staff_detail` DISABLE KEYS;
LOCK TABLES `staff_detail` WRITE;
INSERT INTO `staff_detail` VALUES ('admin.libms','mr','SuperAdministrator','',NULL,'9897160191','superadmin@gmail.com','2011-01-06','2011-01-06','Mr.','2011-01-06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'libms');
UNLOCK TABLES;
ALTER TABLE `staff_detail` ENABLE KEYS;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET SQL_NOTES=@OLD_SQL_NOTES;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET CHARACTER_SET_CONNECTION=@OLD_CHARACTER_SET_CONNECTION;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
