-- MySQL dump 10.13  Distrib 5.1.52, for redhat-linux-gnu (i386)
--
-- Host: localhost    Database: libms
-- ------------------------------------------------------
-- Server version	5.1.52

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
-- Table structure for table `acq_final_demand_list`
--

DROP TABLE IF EXISTS `acq_final_demand_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acq_final_demand_list` (
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `control_no` varchar(10) NOT NULL DEFAULT '',
  `list_id` varchar(18) DEFAULT NULL,
  `isbn` varchar(18) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `total_amount` varchar(20) DEFAULT NULL,
  `volume` varchar(3) DEFAULT NULL,
  `edition` varchar(5) DEFAULT NULL,
  `publisher_id` varchar(20) DEFAULT NULL,
  `bind_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`control_no`),
  CONSTRAINT `acq_final_demand_list_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acq_final_demand_list`
--

LOCK TABLES `acq_final_demand_list` WRITE;
/*!40000 ALTER TABLE `acq_final_demand_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_final_demand_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acq_privilege`
--

DROP TABLE IF EXISTS `acq_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acq_privilege` (
  `staff_id` varchar(100) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `acq_101` varchar(5) DEFAULT 'true',
  `acq_102` varchar(5) DEFAULT 'true',
  `acq_103` varchar(5) DEFAULT 'true',
  `acq_104` varchar(5) DEFAULT 'true',
  `acq_105` varchar(5) DEFAULT 'true',
  `acq_106` varchar(5) DEFAULT 'true',
  `acq_107` varchar(5) DEFAULT 'true',
  `acq_108` varchar(5) DEFAULT 'true',
  `acq_109` varchar(5) DEFAULT 'true',
  `acq_110` varchar(5) DEFAULT 'true',
  `acq_111` varchar(5) DEFAULT 'true',
  `acq_112` varchar(5) DEFAULT 'true',
  `acq_113` varchar(5) DEFAULT 'true',
  `acq_114` varchar(5) DEFAULT 'true',
  `acq_115` varchar(5) DEFAULT 'true',
  `acq_116` varchar(5) DEFAULT 'true',
  `acq_117` varchar(5) DEFAULT 'true',
  `acq_118` varchar(5) DEFAULT 'true',
  `acq_119` varchar(5) DEFAULT 'true',
  `acq_120` varchar(5) DEFAULT 'true',
  `acq_121` varchar(5) DEFAULT 'true',
  `acq_122` varchar(5) DEFAULT 'true',
  `acq_123` varchar(5) DEFAULT 'true',
  `acq_124` varchar(5) DEFAULT 'true',
  `acq_125` varchar(5) DEFAULT 'true',
  `acq_126` varchar(5) DEFAULT 'true',
  `acq_127` varchar(5) DEFAULT 'true',
  `acq_128` varchar(5) DEFAULT 'true',
  `acq_129` varchar(5) DEFAULT 'true',
  `acq_130` varchar(5) DEFAULT 'true',
  `acq_131` varchar(5) DEFAULT 'true',
  `acq_132` varchar(5) DEFAULT 'true',
  `acq_133` varchar(5) DEFAULT 'true',
  `acq_134` varchar(5) DEFAULT 'true',
  `acq_135` varchar(5) DEFAULT 'true',
  `acq_136` varchar(5) DEFAULT 'true',
  `acq_137` varchar(5) DEFAULT 'true',
  `acq_138` varchar(5) DEFAULT 'true',
  `acq_139` varchar(5) DEFAULT 'true',
  `acq_140` varchar(5) DEFAULT 'true',
  `acq_141` varchar(5) DEFAULT 'true',
  `acq_142` varchar(5) DEFAULT 'true',
  `acq_143` varchar(5) DEFAULT 'true',
  `acq_144` varchar(5) DEFAULT 'true',
  `acq_145` varchar(5) DEFAULT 'true',
  `acq_146` varchar(5) DEFAULT 'true',
  `acq_147` varchar(5) DEFAULT 'true',
  `acq_148` varchar(5) DEFAULT 'true',
  `acq_149` varchar(5) DEFAULT 'true',
  `acq_150` varchar(5) DEFAULT 'true',
  `acq_151` varchar(5) DEFAULT 'true',
  `acq_152` varchar(5) DEFAULT 'true',
  `acq_153` varchar(5) DEFAULT 'true',
  `acq_154` varchar(5) DEFAULT 'true',
  `acq_155` varchar(5) DEFAULT 'true',
  `acq_156` varchar(5) DEFAULT 'true',
  `acq_157` varchar(5) DEFAULT 'true',
  `acq_158` varchar(5) DEFAULT 'true',
  `acq_159` varchar(5) DEFAULT 'true',
  `acq_160` varchar(5) DEFAULT 'true',
  `acq_161` varchar(5) DEFAULT 'true',
  `acq_162` varchar(5) DEFAULT 'true',
  `acq_163` varchar(5) DEFAULT 'true',
  `acq_164` varchar(5) DEFAULT 'true',
  `acq_165` varchar(5) DEFAULT 'true',
  `acq_166` varchar(5) DEFAULT 'true',
  `acq_167` varchar(5) DEFAULT 'true',
  `acq_168` varchar(5) DEFAULT 'true',
  `acq_169` varchar(5) DEFAULT 'true',
  `acq_170` varchar(5) DEFAULT 'true',
  `acq_171` varchar(5) DEFAULT 'true',
  `acq_172` varchar(5) DEFAULT 'true',
  `acq_173` varchar(5) DEFAULT 'true',
  `acq_174` varchar(5) DEFAULT 'true',
  `acq_175` varchar(5) DEFAULT 'true',
  `acq_176` varchar(5) DEFAULT 'true',
  `acq_177` varchar(5) DEFAULT 'true',
  `acq_178` varchar(5) DEFAULT 'true',
  `acq_179` varchar(5) DEFAULT 'true',
  `acq_180` varchar(5) DEFAULT 'true',
  `acq_181` varchar(5) DEFAULT 'true',
  `acq_182` varchar(5) DEFAULT 'true',
  `acq_183` varchar(5) DEFAULT 'true',
  `acq_184` varchar(5) DEFAULT 'true',
  `acq_185` varchar(5) DEFAULT 'true',
  `acq_186` varchar(5) DEFAULT 'true',
  `acq_187` varchar(5) DEFAULT 'true',
  `acq_188` varchar(5) DEFAULT 'true',
  `acq_189` varchar(5) DEFAULT 'true',
  `acq_190` varchar(5) DEFAULT 'true',
  `acq_191` varchar(5) DEFAULT 'true',
  `acq_192` varchar(5) DEFAULT 'true',
  `acq_193` varchar(5) DEFAULT 'true',
  `acq_194` varchar(5) DEFAULT 'true',
  `acq_195` varchar(5) DEFAULT 'true',
  `acq_196` varchar(5) DEFAULT 'true',
  `acq_197` varchar(5) DEFAULT 'true',
  `acq_198` varchar(5) DEFAULT 'true',
  `acq_199` varchar(5) DEFAULT 'true',
  PRIMARY KEY (`staff_id`,`library_id`),
  KEY `staff_id` (`staff_id`,`library_id`),
  CONSTRAINT `acq_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acq_privilege`
--

LOCK TABLES `acq_privilege` WRITE;
/*!40000 ALTER TABLE `acq_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_registration`
--

DROP TABLE IF EXISTS `admin_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_registration` (
  `registration_id` int(11) NOT NULL AUTO_INCREMENT,
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
  `admin_password` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `library_id` varchar(50) DEFAULT NULL,
  `library_name` varchar(500) DEFAULT NULL,
  `courtesy` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `staff_id` varchar(100) DEFAULT NULL,
  `working_status` varchar(50) NOT NULL DEFAULT 'OK',
  PRIMARY KEY (`registration_id`)
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
-- Table structure for table `bibliographic_details`
--

DROP TABLE IF EXISTS `bibliographic_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bibliographic_details` (
  `biblio_id` int(30) NOT NULL DEFAULT '0',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `document_type` varchar(20) DEFAULT NULL,
  `accession_type` varchar(30) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `subtitle` varchar(200) DEFAULT NULL,
  `author_main` varchar(200) DEFAULT NULL,
  `author_sub` varchar(200) DEFAULT NULL,
  `publisher_name` varchar(200) DEFAULT NULL,
  `publication_place` varchar(100) DEFAULT NULL,
  `publishing_year` int(10) DEFAULT NULL,
  `control_no` varchar(20) DEFAULT NULL,
  `call_no` varchar(30) NOT NULL DEFAULT '',
  `isbn10` varchar(20) NOT NULL DEFAULT '',
  `isbn13` varchar(20) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `edition` varchar(100) DEFAULT NULL,
  `index_no` varchar(100) DEFAULT NULL,
  `no_of_pages` varchar(100) DEFAULT NULL,
  `physical_width` varchar(100) DEFAULT NULL,
  `bind_type` varchar(200) DEFAULT NULL,
  `no_of_copy` varchar(10) DEFAULT NULL,
  `accessioned_copies` varchar(10) DEFAULT '0',
  PRIMARY KEY (`library_id`,`biblio_id`,`call_no`,`isbn10`),
  UNIQUE KEY `library_id` (`library_id`,`isbn10`),
  UNIQUE KEY `library_id_2` (`library_id`,`call_no`),
  CONSTRAINT `bibliographic_details_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliographic_details`
--

LOCK TABLES `bibliographic_details` WRITE;
/*!40000 ALTER TABLE `bibliographic_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `bibliographic_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER trigger2  AFTER DELETE ON bibliographic_details  FOR EACH ROW  DELETE FROM document_details  WHERE biblio_id = OLD.biblio_id and library_id = OLD.library_id */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cat_privilege`
--

DROP TABLE IF EXISTS `cat_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_privilege` (
  `staff_id` varchar(100) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `cat_201` varchar(5) DEFAULT 'true',
  `cat_202` varchar(5) DEFAULT 'true',
  `cat_203` varchar(5) DEFAULT 'true',
  `cat_204` varchar(5) DEFAULT 'true',
  `cat_205` varchar(5) DEFAULT 'true',
  `cat_206` varchar(5) DEFAULT 'true',
  `cat_207` varchar(5) DEFAULT 'true',
  `cat_208` varchar(5) DEFAULT 'true',
  `cat_209` varchar(5) DEFAULT 'true',
  `cat_210` varchar(5) DEFAULT 'true',
  `cat_211` varchar(5) DEFAULT 'true',
  `cat_212` varchar(5) DEFAULT 'true',
  `cat_213` varchar(5) DEFAULT 'true',
  `cat_214` varchar(5) DEFAULT 'true',
  `cat_215` varchar(5) DEFAULT 'true',
  `cat_216` varchar(5) DEFAULT 'true',
  `cat_217` varchar(5) DEFAULT 'true',
  `cat_218` varchar(5) DEFAULT 'true',
  `cat_219` varchar(5) DEFAULT 'true',
  `cat_220` varchar(5) DEFAULT 'true',
  `cat_221` varchar(5) DEFAULT 'true',
  `cat_222` varchar(5) DEFAULT 'true',
  `cat_223` varchar(5) DEFAULT 'true',
  `cat_224` varchar(5) DEFAULT 'true',
  `cat_225` varchar(5) DEFAULT 'true',
  `cat_226` varchar(5) DEFAULT 'true',
  `cat_227` varchar(5) DEFAULT 'true',
  `cat_228` varchar(5) DEFAULT 'true',
  `cat_229` varchar(5) DEFAULT 'true',
  `cat_230` varchar(5) DEFAULT 'true',
  `cat_231` varchar(5) DEFAULT 'true',
  `cat_232` varchar(5) DEFAULT 'true',
  `cat_233` varchar(5) DEFAULT 'true',
  `cat_234` varchar(5) DEFAULT 'true',
  `cat_235` varchar(5) DEFAULT 'true',
  `cat_236` varchar(5) DEFAULT 'true',
  `cat_237` varchar(5) DEFAULT 'true',
  `cat_238` varchar(5) DEFAULT 'true',
  `cat_239` varchar(5) DEFAULT 'true',
  `cat_240` varchar(5) DEFAULT 'true',
  `cat_241` varchar(5) DEFAULT 'true',
  `cat_242` varchar(5) DEFAULT 'true',
  `cat_243` varchar(5) DEFAULT 'true',
  `cat_244` varchar(5) DEFAULT 'true',
  `cat_245` varchar(5) DEFAULT 'true',
  `cat_246` varchar(5) DEFAULT 'true',
  `cat_247` varchar(5) DEFAULT 'true',
  `cat_248` varchar(5) DEFAULT 'true',
  `cat_249` varchar(5) DEFAULT 'true',
  `cat_250` varchar(5) DEFAULT 'true',
  `cat_251` varchar(5) DEFAULT 'true',
  `cat_252` varchar(5) DEFAULT 'true',
  `cat_253` varchar(5) DEFAULT 'true',
  `cat_254` varchar(5) DEFAULT 'true',
  `cat_255` varchar(5) DEFAULT 'true',
  `cat_256` varchar(5) DEFAULT 'true',
  `cat_257` varchar(5) DEFAULT 'true',
  `cat_258` varchar(5) DEFAULT 'true',
  `cat_259` varchar(5) DEFAULT 'true',
  `cat_260` varchar(5) DEFAULT 'true',
  `cat_261` varchar(5) DEFAULT 'true',
  `cat_262` varchar(5) DEFAULT 'true',
  `cat_263` varchar(5) DEFAULT 'true',
  `cat_264` varchar(5) DEFAULT 'true',
  `cat_265` varchar(5) DEFAULT 'true',
  `cat_266` varchar(5) DEFAULT 'true',
  `cat_267` varchar(5) DEFAULT 'true',
  `cat_268` varchar(5) DEFAULT 'true',
  `cat_269` varchar(5) DEFAULT 'true',
  `cat_270` varchar(5) DEFAULT 'true',
  `cat_271` varchar(5) DEFAULT 'true',
  `cat_272` varchar(5) DEFAULT 'true',
  `cat_273` varchar(5) DEFAULT 'true',
  `cat_274` varchar(5) DEFAULT 'true',
  `cat_275` varchar(5) DEFAULT 'true',
  `cat_276` varchar(5) DEFAULT 'true',
  `cat_277` varchar(5) DEFAULT 'true',
  `cat_278` varchar(5) DEFAULT 'true',
  `cat_279` varchar(5) DEFAULT 'true',
  `cat_280` varchar(5) DEFAULT 'true',
  `cat_281` varchar(5) DEFAULT 'true',
  `cat_282` varchar(5) DEFAULT 'true',
  `cat_283` varchar(5) DEFAULT 'true',
  `cat_284` varchar(5) DEFAULT 'true',
  `cat_285` varchar(5) DEFAULT 'true',
  `cat_286` varchar(5) DEFAULT 'true',
  `cat_287` varchar(5) DEFAULT 'true',
  `cat_288` varchar(5) DEFAULT 'true',
  `cat_289` varchar(5) DEFAULT 'true',
  `cat_290` varchar(5) DEFAULT 'true',
  `cat_291` varchar(5) DEFAULT 'true',
  `cat_292` varchar(5) DEFAULT 'true',
  `cat_293` varchar(5) DEFAULT 'true',
  `cat_294` varchar(5) DEFAULT 'true',
  `cat_295` varchar(5) DEFAULT 'true',
  `cat_296` varchar(5) DEFAULT 'true',
  `cat_297` varchar(5) DEFAULT 'true',
  `cat_298` varchar(5) DEFAULT 'true',
  `cat_299` varchar(5) DEFAULT 'true',
  PRIMARY KEY (`staff_id`,`library_id`),
  KEY `staff_id` (`staff_id`,`library_id`),
  CONSTRAINT `cat_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_privilege`
--

LOCK TABLES `cat_privilege` WRITE;
/*!40000 ALTER TABLE `cat_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `cat_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cir_privilege`
--

DROP TABLE IF EXISTS `cir_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cir_privilege` (
  `staff_id` varchar(100) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `cir_301` varchar(5) DEFAULT 'true',
  `cir_302` varchar(5) DEFAULT 'true',
  `cir_303` varchar(5) DEFAULT 'true',
  `cir_304` varchar(5) DEFAULT 'true',
  `cir_305` varchar(5) DEFAULT 'true',
  `cir_306` varchar(5) DEFAULT 'true',
  `cir_307` varchar(5) DEFAULT 'true',
  `cir_308` varchar(5) DEFAULT 'true',
  `cir_309` varchar(5) DEFAULT 'true',
  `cir_310` varchar(5) DEFAULT 'true',
  `cir_311` varchar(5) DEFAULT 'true',
  `cir_312` varchar(5) DEFAULT 'true',
  `cir_313` varchar(5) DEFAULT 'true',
  `cir_314` varchar(5) DEFAULT 'true',
  `cir_315` varchar(5) DEFAULT 'true',
  `cir_316` varchar(5) DEFAULT 'true',
  `cir_317` varchar(5) DEFAULT 'true',
  `cir_318` varchar(5) DEFAULT 'true',
  `cir_319` varchar(5) DEFAULT 'true',
  `cir_320` varchar(5) DEFAULT 'true',
  `cir_321` varchar(5) DEFAULT 'true',
  `cir_322` varchar(5) DEFAULT 'true',
  `cir_323` varchar(5) DEFAULT 'true',
  `cir_324` varchar(5) DEFAULT 'true',
  `cir_325` varchar(5) DEFAULT 'true',
  `cir_326` varchar(5) DEFAULT 'true',
  `cir_327` varchar(5) DEFAULT 'true',
  `cir_328` varchar(5) DEFAULT 'true',
  `cir_329` varchar(5) DEFAULT 'true',
  `cir_330` varchar(5) DEFAULT 'true',
  `cir_331` varchar(5) DEFAULT 'true',
  `cir_332` varchar(5) DEFAULT 'true',
  `cir_333` varchar(5) DEFAULT 'true',
  `cir_334` varchar(5) DEFAULT 'true',
  `cir_335` varchar(5) DEFAULT 'true',
  `cir_336` varchar(5) DEFAULT 'true',
  `cir_337` varchar(5) DEFAULT 'true',
  `cir_338` varchar(5) DEFAULT 'true',
  `cir_339` varchar(5) DEFAULT 'true',
  `cir_340` varchar(5) DEFAULT 'true',
  `cir_341` varchar(5) DEFAULT 'true',
  `cir_342` varchar(5) DEFAULT 'true',
  `cir_343` varchar(5) DEFAULT 'true',
  `cir_344` varchar(5) DEFAULT 'true',
  `cir_345` varchar(5) DEFAULT 'true',
  `cir_346` varchar(5) DEFAULT 'true',
  `cir_347` varchar(5) DEFAULT 'true',
  `cir_348` varchar(5) DEFAULT 'true',
  `cir_349` varchar(5) DEFAULT 'true',
  `cir_350` varchar(5) DEFAULT 'true',
  `cir_351` varchar(5) DEFAULT 'true',
  `cir_352` varchar(5) DEFAULT 'true',
  `cir_353` varchar(5) DEFAULT 'true',
  `cir_354` varchar(5) DEFAULT 'true',
  `cir_355` varchar(5) DEFAULT 'true',
  `cir_356` varchar(5) DEFAULT 'true',
  `cir_357` varchar(5) DEFAULT 'true',
  `cir_358` varchar(5) DEFAULT 'true',
  `cir_359` varchar(5) DEFAULT 'true',
  `cir_360` varchar(5) DEFAULT 'true',
  `cir_361` varchar(5) DEFAULT 'true',
  `cir_362` varchar(5) DEFAULT 'true',
  `cir_363` varchar(5) DEFAULT 'true',
  `cir_364` varchar(5) DEFAULT 'true',
  `cir_365` varchar(5) DEFAULT 'true',
  `cir_366` varchar(5) DEFAULT 'true',
  `cir_367` varchar(5) DEFAULT 'true',
  `cir_368` varchar(5) DEFAULT 'true',
  `cir_369` varchar(5) DEFAULT 'true',
  `cir_370` varchar(5) DEFAULT 'true',
  `cir_371` varchar(5) DEFAULT 'true',
  `cir_372` varchar(5) DEFAULT 'true',
  `cir_373` varchar(5) DEFAULT 'true',
  `cir_374` varchar(5) DEFAULT 'true',
  `cir_375` varchar(5) DEFAULT 'true',
  `cir_376` varchar(5) DEFAULT 'true',
  `cir_377` varchar(5) DEFAULT 'true',
  `cir_378` varchar(5) DEFAULT 'true',
  `cir_379` varchar(5) DEFAULT 'true',
  `cir_380` varchar(5) DEFAULT 'true',
  `cir_381` varchar(5) DEFAULT 'true',
  `cir_382` varchar(5) DEFAULT 'true',
  `cir_383` varchar(5) DEFAULT 'true',
  `cir_384` varchar(5) DEFAULT 'true',
  `cir_385` varchar(5) DEFAULT 'true',
  `cir_386` varchar(5) DEFAULT 'true',
  `cir_387` varchar(5) DEFAULT 'true',
  `cir_388` varchar(5) DEFAULT 'true',
  `cir_389` varchar(5) DEFAULT 'true',
  `cir_390` varchar(5) DEFAULT 'true',
  `cir_391` varchar(5) DEFAULT 'true',
  `cir_392` varchar(5) DEFAULT 'true',
  `cir_393` varchar(5) DEFAULT 'true',
  `cir_394` varchar(5) DEFAULT 'true',
  `cir_395` varchar(5) DEFAULT 'true',
  `cir_396` varchar(5) DEFAULT 'true',
  `cir_397` varchar(5) DEFAULT 'true',
  `cir_398` varchar(5) DEFAULT 'true',
  `cir_399` varchar(5) DEFAULT 'true',
  PRIMARY KEY (`staff_id`,`library_id`),
  KEY `staff_id` (`staff_id`,`library_id`),
  CONSTRAINT `cir_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cir_privilege`
--

LOCK TABLES `cir_privilege` WRITE;
/*!40000 ALTER TABLE `cir_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `library_id` varchar(20) NOT NULL,
  `course_id` varchar(20) NOT NULL,
  `course_name` varchar(50) DEFAULT NULL,
  `dept_id` varchar(20) NOT NULL,
  `faculty_id` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`course_id`,`dept_id`,`library_id`,`faculty_id`),
  KEY `library_id` (`library_id`,`faculty_id`,`dept_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`library_id`, `faculty_id`, `dept_id`) REFERENCES `department` (`library_id`, `faculty_id`, `dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandlist`
--

DROP TABLE IF EXISTS `demandlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demandlist` (
  `library_id` varchar(20) NOT NULL,
  `memid` varchar(70) NOT NULL,
  `title` varchar(50) NOT NULL DEFAULT '',
  `category` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `publish_yr` varchar(10) DEFAULT NULL,
  `isbn` varchar(50) DEFAULT NULL,
  `no_of_copy` varchar(10) DEFAULT NULL,
  `volume` varchar(50) DEFAULT NULL,
  `edition` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `demand_date` varchar(50) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `issn` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`memid`,`title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `library_id` varchar(20) NOT NULL,
  `dept_id` varchar(20) NOT NULL,
  `dept_name` varchar(50) DEFAULT NULL,
  `faculty_id` varchar(20) NOT NULL,
  PRIMARY KEY (`dept_id`,`faculty_id`,`library_id`),
  KEY `library_id` (`library_id`,`faculty_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`library_id`, `faculty_id`) REFERENCES `faculty` (`library_id`, `faculty_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `department_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `accessionno` varchar(50) DEFAULT NULL,
  `callno` varchar(50) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `noofcopy` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `isbn` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `pubplace` varchar(50) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `db_category` varchar(50) DEFAULT NULL,
  `pub_yr` varchar(5) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_details`
--

DROP TABLE IF EXISTS `document_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_details` (
  `document_id` int(30) NOT NULL DEFAULT '0',
  `biblio_id` int(30) DEFAULT NULL,
  `serial_id` varchar(20) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `document_type` varchar(20) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `subtitle` varchar(200) DEFAULT NULL,
  `author_main` varchar(200) DEFAULT NULL,
  `author_sub` varchar(200) DEFAULT NULL,
  `publisher_name` varchar(200) DEFAULT NULL,
  `publication_place` varchar(100) DEFAULT NULL,
  `publishing_year` int(11) DEFAULT NULL,
  `call_no` varchar(30) DEFAULT NULL,
  `isbn10` varchar(20) DEFAULT NULL,
  `issn` varchar(20) DEFAULT NULL,
  `isbn13` varchar(20) DEFAULT NULL,
  `edition` varchar(100) DEFAULT NULL,
  `index_no` varchar(100) DEFAULT NULL,
  `no_of_pages` varchar(100) DEFAULT NULL,
  `physical_width` varchar(100) DEFAULT NULL,
  `no_of_copy` varchar(10) DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `editor` varchar(200) DEFAULT NULL,
  `volume_no` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`document_id`),
  CONSTRAINT `document_details_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_details`
--

LOCK TABLES `document_details` WRITE;
/*!40000 ALTER TABLE `document_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `faculty_id` varchar(20) NOT NULL DEFAULT '',
  `faculty_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`faculty_id`),
  CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `cardno` varchar(60) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `comments` text,
  `date` varchar(15) DEFAULT NULL,
  `library_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fine_details`
--

DROP TABLE IF EXISTS `fine_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fine_details` (
  `library_id` varchar(50) DEFAULT NULL,
  `memid` varchar(70) DEFAULT NULL,
  `accessionno` varchar(50) DEFAULT NULL,
  `callno` varchar(50) DEFAULT NULL,
  `isbn` varchar(50) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `fine` int(11) DEFAULT NULL,
  KEY `fine_details_ibfk_2` (`library_id`),
  CONSTRAINT `fine_details_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fine_details`
--

LOCK TABLES `fine_details` WRITE;
/*!40000 ALTER TABLE `fine_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `fine_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library` (
  `registration_id` int(11) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL,
  `library_name` varchar(50) DEFAULT NULL,
  `staff_id` varchar(100) DEFAULT NULL,
  `working_status` varchar(50) NOT NULL DEFAULT 'OK',
  PRIMARY KEY (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
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
  `password` varchar(20) DEFAULT NULL,
  `library_id` varchar(50) DEFAULT NULL,
  `staff_id` varchar(100) DEFAULT NULL,
  `question` varchar(1000) DEFAULT NULL,
  `ans` varchar(500) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `login_ibfk_1` (`staff_id`,`library_id`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE,
  CONSTRAINT `login_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `staff_detail` (`emai_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('superadmin','superadmin','superadmin','libms','admin.libms',NULL,'','superadmin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `library_id` varchar(50) DEFAULT NULL,
  `memId` varchar(70) DEFAULT NULL,
  `category` varchar(25) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `mname` varchar(40) DEFAULT NULL,
  `lname` varchar(40) DEFAULT NULL,
  `address1` varchar(200) DEFAULT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `pin` varchar(15) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `phone1` varchar(15) DEFAULT NULL,
  `phone2` varchar(15) DEFAULT NULL,
  `requestdate` varchar(15) DEFAULT NULL,
  `faculty_id` varchar(20) DEFAULT NULL,
  `dept_id` varchar(20) DEFAULT NULL,
  `rollno` varchar(40) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `card_id` varchar(20) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  KEY `library_id` (`library_id`),
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_accounthistory`
--

DROP TABLE IF EXISTS `member_accounthistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_accounthistory` (
  `library_id` varchar(50) NOT NULL,
  `memid` varchar(70) NOT NULL,
  `memberName` varchar(100) DEFAULT NULL,
  `fine` varchar(10) DEFAULT NULL,
  `no_of_chkout` varchar(10) DEFAULT NULL,
  `reservation_made` varchar(10) DEFAULT NULL,
  `lastchkoutdate` varchar(15) DEFAULT NULL,
  `alive_status` char(2) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`memid`),
  CONSTRAINT `member_accounthistory_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_accounthistory`
--

LOCK TABLES `member_accounthistory` WRITE;
/*!40000 ALTER TABLE `member_accounthistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_accounthistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newarrivals`
--

DROP TABLE IF EXISTS `newarrivals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newarrivals` (
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `accessionno` varchar(50) NOT NULL DEFAULT '',
  `callno` varchar(50) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `noofcopy` int(11) DEFAULT NULL,
  `price` double(6,2) DEFAULT NULL,
  `isbn` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `pubplace` varchar(50) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `pub_yr` varchar(5) DEFAULT NULL,
  `arrival_date` date NOT NULL DEFAULT '0000-00-00',
  `library_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`accessionno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newarrivals`
--

LOCK TABLES `newarrivals` WRITE;
/*!40000 ALTER TABLE `newarrivals` DISABLE KEYS */;
/*!40000 ALTER TABLE `newarrivals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL DEFAULT '0',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `subject` varchar(59) DEFAULT NULL,
  `message` varchar(198) DEFAULT NULL,
  PRIMARY KEY (`notice_id`,`library_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `staff_id` varchar(100) NOT NULL DEFAULT '',
  `library_id` varchar(50) NOT NULL DEFAULT '',
  `acquisition` varchar(5) DEFAULT 'true',
  `cataloguing` varchar(5) DEFAULT 'true',
  `circulation` varchar(5) DEFAULT 'true',
  `serial` varchar(5) DEFAULT 'true',
  `administrator` varchar(5) DEFAULT 'true',
  `system_setup` varchar(5) DEFAULT 'true',
  `utilities` varchar(5) DEFAULT 'true',
  `opac` varchar(5) DEFAULT 'true',
  PRIMARY KEY (`staff_id`,`library_id`),
  KEY `staff_id` (`staff_id`,`library_id`),
  CONSTRAINT `privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `login` (`staff_id`, `library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationlist`
--

DROP TABLE IF EXISTS `reservationlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservationlist` (
  `library_id` varchar(100) DEFAULT NULL,
  `memid` varchar(70) DEFAULT NULL,
  `accessionno` varchar(50) DEFAULT NULL,
  `card_id` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `category` varchar(25) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `isbn` varchar(200) DEFAULT NULL,
  `callno` varchar(50) DEFAULT NULL,
  `edition` varchar(25) DEFAULT NULL,
  `volume` varchar(25) DEFAULT NULL,
  `publication` varchar(50) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `request_date` varchar(15) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `issn` varchar(20) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `pub_year` varchar(15) DEFAULT NULL,
  KEY `reservationlist_ibfk_2` (`library_id`),
  CONSTRAINT `reservationlist_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ser_privilege` (
  `staff_id` varchar(100) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `ser_401` varchar(5) DEFAULT 'true',
  `ser_402` varchar(5) DEFAULT 'true',
  `ser_403` varchar(5) DEFAULT 'true',
  `ser_404` varchar(5) DEFAULT 'true',
  `ser_405` varchar(5) DEFAULT 'true',
  `ser_406` varchar(5) DEFAULT 'true',
  `ser_407` varchar(5) DEFAULT 'true',
  `ser_408` varchar(5) DEFAULT 'true',
  `ser_409` varchar(5) DEFAULT 'true',
  `ser_410` varchar(5) DEFAULT 'true',
  `ser_411` varchar(5) DEFAULT 'true',
  `ser_412` varchar(5) DEFAULT 'true',
  `ser_413` varchar(5) DEFAULT 'true',
  `ser_414` varchar(5) DEFAULT 'true',
  `ser_415` varchar(5) DEFAULT 'true',
  `ser_416` varchar(5) DEFAULT 'true',
  `ser_417` varchar(5) DEFAULT 'true',
  `ser_418` varchar(5) DEFAULT 'true',
  `ser_419` varchar(5) DEFAULT 'true',
  `ser_420` varchar(5) DEFAULT 'true',
  `ser_421` varchar(5) DEFAULT 'true',
  `ser_422` varchar(5) DEFAULT 'true',
  `ser_423` varchar(5) DEFAULT 'true',
  `ser_424` varchar(5) DEFAULT 'true',
  `ser_425` varchar(5) DEFAULT 'true',
  `ser_426` varchar(5) DEFAULT 'true',
  `ser_427` varchar(5) DEFAULT 'true',
  `ser_428` varchar(5) DEFAULT 'true',
  `ser_429` varchar(5) DEFAULT 'true',
  `ser_430` varchar(5) DEFAULT 'true',
  `ser_431` varchar(5) DEFAULT 'true',
  `ser_432` varchar(5) DEFAULT 'true',
  `ser_433` varchar(5) DEFAULT 'true',
  `ser_434` varchar(5) DEFAULT 'true',
  `ser_435` varchar(5) DEFAULT 'true',
  `ser_436` varchar(5) DEFAULT 'true',
  `ser_437` varchar(5) DEFAULT 'true',
  `ser_438` varchar(5) DEFAULT 'true',
  `ser_439` varchar(5) DEFAULT 'true',
  `ser_440` varchar(5) DEFAULT 'true',
  `ser_441` varchar(5) DEFAULT 'true',
  `ser_442` varchar(5) DEFAULT 'true',
  `ser_443` varchar(5) DEFAULT 'true',
  `ser_444` varchar(5) DEFAULT 'true',
  `ser_445` varchar(5) DEFAULT 'true',
  `ser_446` varchar(5) DEFAULT 'true',
  `ser_447` varchar(5) DEFAULT 'true',
  `ser_448` varchar(5) DEFAULT 'true',
  `ser_449` varchar(5) DEFAULT 'true',
  `ser_450` varchar(5) DEFAULT 'true',
  `ser_451` varchar(5) DEFAULT 'true',
  `ser_452` varchar(5) DEFAULT 'true',
  `ser_453` varchar(5) DEFAULT 'true',
  `ser_454` varchar(5) DEFAULT 'true',
  `ser_455` varchar(5) DEFAULT 'true',
  `ser_456` varchar(5) DEFAULT 'true',
  `ser_457` varchar(5) DEFAULT 'true',
  `ser_458` varchar(5) DEFAULT 'true',
  `ser_459` varchar(5) DEFAULT 'true',
  `ser_460` varchar(5) DEFAULT 'true',
  `ser_461` varchar(5) DEFAULT 'true',
  `ser_462` varchar(5) DEFAULT 'true',
  `ser_463` varchar(5) DEFAULT 'true',
  `ser_464` varchar(5) DEFAULT 'true',
  `ser_465` varchar(5) DEFAULT 'true',
  `ser_466` varchar(5) DEFAULT 'true',
  `ser_467` varchar(5) DEFAULT 'true',
  `ser_468` varchar(5) DEFAULT 'true',
  `ser_469` varchar(5) DEFAULT 'true',
  `ser_470` varchar(5) DEFAULT 'true',
  `ser_471` varchar(5) DEFAULT 'true',
  `ser_472` varchar(5) DEFAULT 'true',
  `ser_473` varchar(5) DEFAULT 'true',
  `ser_474` varchar(5) DEFAULT 'true',
  `ser_475` varchar(5) DEFAULT 'true',
  `ser_476` varchar(5) DEFAULT 'true',
  `ser_477` varchar(5) DEFAULT 'true',
  `ser_478` varchar(5) DEFAULT 'true',
  `ser_479` varchar(5) DEFAULT 'true',
  `ser_480` varchar(5) DEFAULT 'true',
  `ser_481` varchar(5) DEFAULT 'true',
  `ser_482` varchar(5) DEFAULT 'true',
  `ser_483` varchar(5) DEFAULT 'true',
  `ser_484` varchar(5) DEFAULT 'true',
  `ser_485` varchar(5) DEFAULT 'true',
  `ser_486` varchar(5) DEFAULT 'true',
  `ser_487` varchar(5) DEFAULT 'true',
  `ser_488` varchar(5) DEFAULT 'true',
  `ser_489` varchar(5) DEFAULT 'true',
  `ser_490` varchar(5) DEFAULT 'true',
  `ser_491` varchar(5) DEFAULT 'true',
  `ser_492` varchar(5) DEFAULT 'true',
  `ser_493` varchar(5) DEFAULT 'true',
  `ser_494` varchar(5) DEFAULT 'true',
  `ser_495` varchar(5) DEFAULT 'true',
  `ser_496` varchar(5) DEFAULT 'true',
  `ser_497` varchar(5) DEFAULT 'true',
  `ser_498` varchar(5) DEFAULT 'true',
  `ser_499` varchar(5) DEFAULT 'true',
  PRIMARY KEY (`staff_id`,`library_id`),
  KEY `staff_id` (`staff_id`,`library_id`),
  CONSTRAINT `ser_privilege_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `privilege` (`staff_id`, `library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ser_privilege`
--

LOCK TABLES `ser_privilege` WRITE;
/*!40000 ALTER TABLE `ser_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `ser_privilege` ENABLE KEYS */;
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
  `emai_id` varchar(100) DEFAULT NULL,
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
  `library_id` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`staff_id`,`library_id`),
  UNIQUE KEY `emai_id` (`emai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_detail`
--

LOCK TABLES `staff_detail` WRITE;
/*!40000 ALTER TABLE `staff_detail` DISABLE KEYS */;
INSERT INTO `staff_detail` VALUES ('admin.libms',NULL,'SuperAdmin',NULL,'',NULL,'superadmin','2011-02-01','2011-02-01',NULL,'2011-02-01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'libms');
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

-- Dump completed on 2011-02-11 18:53:00
