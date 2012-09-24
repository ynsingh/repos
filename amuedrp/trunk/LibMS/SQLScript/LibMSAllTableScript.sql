
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
  `courtesy` varchar(30) default NULL,
  `gender` varchar(10) default NULL,
  `staff_id` varchar(100) default NULL,
  `working_status` varchar(50) NOT NULL default 'OK',
  `insti_logo` varchar(200) NOT NULL,
  PRIMARY KEY  (`registration_id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
--
-- Dumping data for table `admin_registration`
--

LOCK TABLES `admin_registration` WRITE;
/*!40000 ALTER TABLE `admin_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_registration` ENABLE KEYS */;
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
  `library_name` varchar(100) default NULL,
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
INSERT INTO `library` VALUES (0,'libms','libms','admin.libms','OK');
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
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
  KEY `FKE0F749BCFFE0695A` (`library_id`),
  CONSTRAINT `FKE0F749BCFFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `sub_library_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sub_library`
--

LOCK TABLES `sub_library` WRITE;
/*!40000 ALTER TABLE `sub_library` DISABLE KEYS */;
INSERT INTO `sub_library` VALUES ('libms','libms','libms',NULL,NULL);
/*!40000 ALTER TABLE `sub_library` ENABLE KEYS */;
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
  `date_joining` varchar(12) default NULL,
  `date_releaving` varchar(12) default NULL,
  `father_name` varchar(30) default NULL,
  `date_of_birth` varchar(12) default NULL,
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
  KEY `FK45185690FFE0695A` (`library_id`),
  CONSTRAINT `FK45185690FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `staff_detail_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `staff_detail`
--

LOCK TABLES `staff_detail` WRITE;
/*!40000 ALTER TABLE `staff_detail` DISABLE KEYS */;
INSERT INTO `staff_detail` VALUES ('libms','libms','admin.libms',NULL,'superadmin',NULL,NULL,NULL,NULL,'2011-04-16','2011-04-16',NULL,'2011-04-16',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `staff_detail` ENABLE KEYS */;
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
  KEY `FKA1FAF6B178C2E6CA` (`staff_id`,`library_id`),
  CONSTRAINT `FKA1FAF6B178C2E6CA` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`),
  CONSTRAINT `login_ibfk_3` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `acq_privilege` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `cat_privilege` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `cir_privilege` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `ser_privilege` ENABLE KEYS */;
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
  KEY `FK625EF6978C2E6CA` (`staff_id`,`library_id`),
  CONSTRAINT `FK625EF6978C2E6CA` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`staff_id`, `library_id`) REFERENCES `staff_detail` (`staff_id`, `library_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('libms','libms','superadmin','admin.libms','superadmin',NULL,NULL,'superadmin',MD5('superadmin'));
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
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
  `image` varchar(200) default NULL,
  `digital_data` varchar(200) default NULL,
  `digital_comment` varchar(300) default NULL,
  `rating` varchar(5) default NULL,
  `submitted_by` varchar(200) default NULL,
`last_modified` varchar(200) default NULL,
`thesis_status` VARCHAR(100) default NULL,

  PRIMARY KEY  (`biblio_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  KEY `FKC8EFDF54FFE0695A` (`library_id`),
  CONSTRAINT `bibliographic_details_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FKC8EFDF54FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bibliographic_details`
--

LOCK TABLES `bibliographic_details` WRITE;
/*!40000 ALTER TABLE `bibliographic_details` DISABLE KEYS */;
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
  `publishing_year` int collate utf8_bin default NULL,
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
  `image` varchar(200) default NULL,
  `digital_data` varchar(200) default NULL,
  `digital_comment` varchar(300) default NULL,
  `rating` varchar(5) default NULL,
  `submitted_by` varchar(200) default NULL,
`last_modified` varchar(200) default NULL,
`thesis_status` VARCHAR(100) default NULL,

  PRIMARY KEY  (`biblio_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  KEY `FK91E7DFF9FFE0695A` (`library_id`),
  CONSTRAINT `bibliographic_details_lang_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FK91E7DFF9FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bibliographic_details_lang`
--

LOCK TABLES `bibliographic_details_lang` WRITE;
/*!40000 ALTER TABLE `bibliographic_details_lang` DISABLE KEYS */;
/*!40000 ALTER TABLE `bibliographic_details_lang` ENABLE KEYS */;
UNLOCK TABLES;




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
  KEY `FK8245105E83D63F52` (`biblio_id`,`library_id`,`sublibrary_id`),
  CONSTRAINT `accession_register_ibfk_1` FOREIGN KEY (`biblio_id`, `library_id`, `sublibrary_id`) REFERENCES `bibliographic_details` (`biblio_id`, `library_id`, `sublibrary_id`),
  CONSTRAINT `FK8245105E83D63F52` FOREIGN KEY (`biblio_id`, `library_id`, `sublibrary_id`) REFERENCES `bibliographic_details` (`biblio_id`, `library_id`, `sublibrary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `accession_register`
--

LOCK TABLES `accession_register` WRITE;
/*!40000 ALTER TABLE `accession_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `accession_register` ENABLE KEYS */;
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
  KEY `FK829C1F1E83D63F52` (`biblio_id`,`library_id`,`sublibrary_id`),
  CONSTRAINT `document_details_ibfk_1` FOREIGN KEY (`biblio_id`, `library_id`, `sublibrary_id`) REFERENCES `bibliographic_details` (`biblio_id`, `library_id`, `sublibrary_id`),
  CONSTRAINT `FK829C1F1E83D63F52` FOREIGN KEY (`biblio_id`, `library_id`, `sublibrary_id`) REFERENCES `bibliographic_details` (`biblio_id`, `library_id`, `sublibrary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `document_details`
--

LOCK TABLES `document_details` WRITE;
/*!40000 ALTER TABLE `document_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_details` ENABLE KEYS */;
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
  `status` varchar(50) default NULL,
  `library_id` varchar(20) NOT NULL default '',
  `sub_library_id` varchar(20) NOT NULL default '',
  `vendor` varchar(100) default NULL,
  `author` varchar(100) default NULL,
  `no_of_volume` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`control_no`),
  KEY `library_id` (`library_id`,`sub_library_id`,`title_id`),
  KEY `FK65693C15995E8510` (`library_id`,`sub_library_id`,`title_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_bibliography_details`
--

LOCK TABLES `acq_bibliography_details` WRITE;
/*!40000 ALTER TABLE `acq_bibliography_details` DISABLE KEYS */;
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
   `expense_amount` varchar(11) default NULL,
  PRIMARY KEY  (`library_id`,`transaction_id`),
  KEY `budgethead_id` (`budgethead_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_budget_allocation`
--

LOCK TABLES `acq_budget_allocation` WRITE;
/*!40000 ALTER TABLE `acq_budget_allocation` DISABLE KEYS */;
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
`expense_amount` varchar(11) default NULL,
  PRIMARY KEY  (`transaction_id`,`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_budget_transaction`
--

LOCK TABLES `acq_budget_transaction` WRITE;
/*!40000 ALTER TABLE `acq_budget_transaction` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `acq_currency` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `acq_vendor` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `base_currency` ENABLE KEYS */;
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
  KEY `library_id` (`library_id`,`sub_library_id`,`vendor_id`),
  KEY `FK68454859314B6D46` (`library_id`,`sub_library_id`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_approval_header`
--

LOCK TABLES `acq_approval_header` WRITE;
/*!40000 ALTER TABLE `acq_approval_header` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_approval_header` ENABLE KEYS */;
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
  KEY `library_id` (`library_id`,`sub_library_id`,`approval_no`),
  KEY `FK9F364FF39F2DF979` (`library_id`,`sub_library_id`,`approval_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_approval`
--

LOCK TABLES `acq_approval` WRITE;
/*!40000 ALTER TABLE `acq_approval` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_approval` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `acq_order_header` ENABLE KEYS */;
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
  `recieving_no` varchar(20) default NULL,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`order_no`,`order_item_id`),
  KEY `FK25FEF8531111DBDB` (`library_id`,`sub_library_id`,`order_no`),
  CONSTRAINT `acq_order1_ibfk_1` FOREIGN KEY (`library_id`, `sub_library_id`, `order_no`) REFERENCES `acq_order_header` (`library_id`, `sub_library_id`, `order_no`) ON DELETE CASCADE,
  CONSTRAINT `FK25FEF8531111DBDB` FOREIGN KEY (`library_id`, `sub_library_id`, `order_no`) REFERENCES `acq_order_header` (`library_id`, `sub_library_id`, `order_no`)
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
/*!40000 ALTER TABLE `acq_recieving_header` ENABLE KEYS */;
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
 `vendor_id` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`library_id`,`sub_library_id`,`recieving_no`,`recieving_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `acq_recieving_details`
--

LOCK TABLES `acq_recieving_details` WRITE;
/*!40000 ALTER TABLE `acq_recieving_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `acq_recieving_details` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `acq_invoice_header` (`library_id` VARCHAR(20) NOT NULL, `sublibrary_id` VARCHAR(20) NOT NULL, `invoice_no` VARCHAR(20) NOT NULL, `vendor_id` VARCHAR(20) NOT NULL, `order_no` VARCHAR(20) NOT NULL, `date` VARCHAR(20), `recieved_by` VARCHAR(20), `discount` VARCHAR(20), `net_total` VARCHAR(20), `status` VARCHAR(20), `overall_discount` VARCHAR(20), `total_net_amount` VARCHAR(20), `misc_charges` VARCHAR(20), `grand_total` VARCHAR(15), `invoice_date` VARCHAR(20), PRIMARY KEY (`invoice_no`, `library_id`, `order_no`, `sublibrary_id`, `vendor_id`));



CREATE TABLE `acq_invoice_detail` (
  `invoice_no` varchar(20) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `recieving_no` varchar(20) NOT NULL DEFAULT '',
  `order_no` varchar(20) DEFAULT NULL,
  `vendor_id` varchar(50) DEFAULT NULL,
  `order_date` varchar(20) DEFAULT NULL,
  `total_amount` varchar(20) DEFAULT NULL,
  `discount` varchar(20) DEFAULT NULL,
  `net_total` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `recieving_item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoice_no`,`library_id`,`sub_library_id`,`recieving_no`,`recieving_item_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `acq_invoice_detail_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `acq_requestpayment_details` (
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `prn` varchar(20) NOT NULL DEFAULT '',
  `invoice_no` varchar(20) NOT NULL DEFAULT '',
  `recieving_no` varchar(20) NOT NULL DEFAULT '',
  `vendor_id` varchar(20) DEFAULT NULL,
  `total_amt` varchar(10) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `order_no` varchar(20) DEFAULT NULL,
  `payment_update_date` varchar(20) DEFAULT NULL,
  `accession_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`prn`,`recieving_no`,`invoice_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `acq_requestpayment_header`
--

DROP TABLE IF EXISTS `acq_requestpayment_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acq_requestpayment_header` (
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `prn` varchar(20) NOT NULL DEFAULT '',
  `prn_date` varchar(20) DEFAULT NULL,
  `vendor_id` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `total_amount` varchar(10) DEFAULT NULL,
  `no_of_invoices` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`prn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `biblio`
--

DROP TABLE IF EXISTS `biblio`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `biblio` (
  `library_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL,
  `marctag` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `indicator1` char(1) default NULL,
  `indicator2` char(1) default NULL,
  `$a` varchar(100) default NULL,
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
/*!40000 ALTER TABLE `biblio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biblio_temp`
--

DROP TABLE IF EXISTS `biblio_temp`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `biblio_temp` (
  `bib_id` int(11) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `marctag` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `indicator1` char(1) default NULL,
  `indicator2` char(1) default NULL,
  `$a` text,
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `biblio_temp`
--

LOCK TABLES `biblio_temp` WRITE;
/*!40000 ALTER TABLE `biblio_temp` DISABLE KEYS */;
/*!40000 ALTER TABLE `biblio_temp` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `cancellation_reason`
--

DROP TABLE IF EXISTS `cancellation_reason`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cancellation_reason` (
  `id` varchar(50) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `details` varchar(200) default NULL,
  PRIMARY KEY  (`id`,`library_id`,`sublibrary_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cancellation_reason`
--

LOCK TABLES `cancellation_reason` WRITE;
/*!40000 ALTER TABLE `cancellation_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancellation_reason` ENABLE KEYS */;
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
  KEY `FK35352359FFE0695A` (`library_id`),
  CONSTRAINT `cir_checkout_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FK35352359FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_checkout`
--

LOCK TABLES `cir_checkout` WRITE;
/*!40000 ALTER TABLE `cir_checkout` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_checkout` ENABLE KEYS */;
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
  KEY `FK85D86BBA884CBEE4` (`checkout_id`,`library_id`,`sublibrary_id`),
  CONSTRAINT `cir_checkin_ibfk_1` FOREIGN KEY (`checkout_id`, `library_id`, `sublibrary_id`) REFERENCES `cir_checkout` (`checkout_id`, `library_id`, `sublibrary_id`),
  CONSTRAINT `FK85D86BBA884CBEE4` FOREIGN KEY (`checkout_id`, `library_id`, `sublibrary_id`) REFERENCES `cir_checkout` (`checkout_id`, `library_id`, `sublibrary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_checkin`
--

LOCK TABLES `cir_checkin` WRITE;
/*!40000 ALTER TABLE `cir_checkin` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_checkin` ENABLE KEYS */;
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
  `college` varchar(200) default NULL,
  `coll_address` varchar(150) default NULL,
  `temp_status` varchar(1) default NULL,
  PRIMARY KEY  (`library_id`,`memId`),
  KEY `FK1F054763FFE0695A` (`library_id`),
  CONSTRAINT `cir_member_detail_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FK1F054763FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_member_detail`
--

LOCK TABLES `cir_member_detail` WRITE;
/*!40000 ALTER TABLE `cir_member_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_member_detail` ENABLE KEYS */;
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
  `card_status` varchar(20) default NULL,
  `cardIssueDate` varchar(15) default NULL,
  `LostDate` varchar(15) default NULL,
  `DuplicateIssueDate` varchar(15) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`,`memid`),
  KEY `library_id` (`library_id`,`memid`),
  KEY `FK1E9E3D5BEB920CC6` (`library_id`,`memid`),
  CONSTRAINT `cir_member_account_ibfk_1` FOREIGN KEY (`library_id`, `memid`) REFERENCES `cir_member_detail` (`library_id`, `memId`),
  CONSTRAINT `FK1E9E3D5BEB920CC6` FOREIGN KEY (`library_id`, `memid`) REFERENCES `cir_member_detail` (`library_id`, `memId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_member_account`
--

LOCK TABLES `cir_member_account` WRITE;
/*!40000 ALTER TABLE `cir_member_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_member_account` ENABLE KEYS */;
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
  KEY `FK5C08E0A6EB920CC6` (`library_id`,`memid`),
  CONSTRAINT `cir_opac_request_ibfk_1` FOREIGN KEY (`library_id`, `memid`) REFERENCES `cir_member_detail` (`library_id`, `memId`),
  CONSTRAINT `FK5C08E0A6EB920CC6` FOREIGN KEY (`library_id`, `memid`) REFERENCES `cir_member_detail` (`library_id`, `memId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_opac_request`
--

LOCK TABLES `cir_opac_request` WRITE;
/*!40000 ALTER TABLE `cir_opac_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_opac_request` ENABLE KEYS */;
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
  `image` varchar(200) default NULL,
  `Course_Year` varchar(10) default NULL,
  `semester` varchar(10) default NULL,
  `office` varchar(100) default NULL,
  `desg` varchar(100) default NULL,
  `sublibrary_id` varchar(20) default NULL,
  PRIMARY KEY  (`registration_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_requestfrom_opac`
--

LOCK TABLES `cir_requestfrom_opac` WRITE;
/*!40000 ALTER TABLE `cir_requestfrom_opac` DISABLE KEYS */;
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
  `issue_date` varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`transaction_id`,`sublibrary_id`),
  KEY `FKC90A5040FFE0695A` (`library_id`),
  CONSTRAINT `cir_transaction_history_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE,
  CONSTRAINT `FKC90A5040FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cir_transaction_history`
--

LOCK TABLES `cir_transaction_history` WRITE;
/*!40000 ALTER TABLE `cir_transaction_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `cir_transaction_history` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `customizedbiblio`
--

DROP TABLE IF EXISTS `customizedbiblio`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `customizedbiblio` (
  `library_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL,
  `marctag` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `indicator1` char(1) default NULL,
  `indicator2` char(1) default NULL,
  `$a` text,
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
-- Table structure for table `deliquency_reason`
--

DROP TABLE IF EXISTS `deliquency_reason`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `deliquency_reason` (
  `library_id` varchar(20) NOT NULL,
  `id` varchar(50) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `details` varchar(200) default NULL,
  PRIMARY KEY  (`library_id`,`id`,`sublibrary_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `deliquency_reason`
--

LOCK TABLES `deliquency_reason` WRITE;
/*!40000 ALTER TABLE `deliquency_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliquency_reason` ENABLE KEYS */;
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
  `demand_id` int NOT NULL,
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
`member_type` varchar(100) default NULL,
        `sub_member_type` varchar(100) default NULL,
        `mem_name` varchar(100) default NULL,
            `sub_author` varchar(50) default NULL,
        `sub_author0`  varchar(50) default NULL,
        `sub_author1`  varchar(50) default NULL,
        `sub_author2`  varchar(50) default NULL,
        `publication_place`  varchar(50) default NULL,
        `lcc_no`  varchar(50) default NULL,
  PRIMARY KEY  (`library_id`,`memId`,`sublibrary_id`,`title`,`demand_id`),
  KEY `library_id` (`library_id`,`sublibrary_id`,`memId`),
  KEY `FKF3357D69B2998975` (`library_id`,`sublibrary_id`,`memId`),
  CONSTRAINT `demandlist_ibfk_1` FOREIGN KEY (`library_id`, `sublibrary_id`, `memId`) REFERENCES `cir_member_account` (`library_id`, `sublibrary_id`, `memid`),
  CONSTRAINT `FKF3357D69B2998975` FOREIGN KEY (`library_id`, `sublibrary_id`, `memId`) REFERENCES `cir_member_account` (`library_id`, `sublibrary_id`, `memid`)
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
  KEY `FKBEEB9A64FFE0695A` (`library_id`),
  CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE,
  CONSTRAINT `FKBEEB9A64FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
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
  KEY `FK328E4352FFE0695A` (`library_id`),
  KEY `FK328E43525048EBD9` (`library_id`,`faculty_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`library_id`, `faculty_id`) REFERENCES `faculty` (`library_id`, `faculty_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `department_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FK328E43525048EBD9` FOREIGN KEY (`library_id`, `faculty_id`) REFERENCES `faculty` (`library_id`, `faculty_id`),
  CONSTRAINT `FK328E4352FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
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
  KEY `FK391923B8FFE0695A` (`library_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`library_id`, `faculty_id`, `dept_id`) REFERENCES `department` (`library_id`, `faculty_id`, `dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE,
  CONSTRAINT `FK391923B8FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
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
  KEY `FK95A52BA2FFE0695A` (`library_id`),
  CONSTRAINT `document_category_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE,
  CONSTRAINT `FK95A52BA2FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `document_category`
--

LOCK TABLES `document_category` WRITE;
/*!40000 ALTER TABLE `document_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editmarc`
--

DROP TABLE IF EXISTS `editmarc`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `editmarc` (
  `tagnumber` bigint(20) NOT NULL,
  `subsymbol` char(1) NOT NULL,
  `tagname` varchar(100) default NULL,
  `subdescription` varchar(100) default NULL,
  `repeatable1` bit(1) default NULL,
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
  KEY `FK87195E2BFFE0695A` (`library_id`),
  CONSTRAINT `employee_type_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FK87195E2BFFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `employee_type`
--

LOCK TABLES `employee_type` WRITE;
/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;
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
  KEY `FKF495EB85FFE0695A` (`library_id`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE,
  CONSTRAINT `FKF495EB85FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
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
  KEY `FK714F9FB5FFE0695A` (`library_id`),
  CONSTRAINT `FK714F9FB5FFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
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
  KEY `FK7EEB449BFFE0695A` (`library_id`),
  CONSTRAINT `FK7EEB449BFFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `notices_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `notices`
--

LOCK TABLES `notices` WRITE;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;
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
  KEY `FKEBE92E6AFFE0695A` (`library_id`),
  CONSTRAINT `FKEBE92E6AFFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
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
-- Table structure for table `ser_biolio_details`
--

DROP TABLE IF EXISTS `ser_biolio_details`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ser_biolio_details` (
  `ser_control_no` varchar(10) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `new_serial_id` varchar(10) NOT NULL,
  `no_of_copies` int(11) default NULL,
  `issue_no` varchar(10) default NULL,
  `volume_no` varchar(10) default NULL,
  `periodicity` varchar(10) default NULL,
  `issue_yr` varchar(10) default NULL,
  `issue_month` varchar(10) default NULL,
  `issue_details` varchar(200) default NULL,
  `subject` varchar(20) default NULL,
  `unit_price` int(11) default NULL,
  `currency` varchar(20) default NULL,
  `primary_budget` varchar(20) default NULL,
  `requested_by` varchar(20) default NULL,
  `requested_date` varchar(12) default NULL,
  `vendor` varchar(20) default NULL,
  `status` varchar(10) default NULL,
  PRIMARY KEY  (`ser_control_no`,`sublibrary_id`,`library_id`),
  KEY `FKC2497D4CDD9BD097` (`library_id`,`sublibrary_id`,`new_serial_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ser_biolio_details`
--

LOCK TABLES `ser_biolio_details` WRITE;
/*!40000 ALTER TABLE `ser_biolio_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `ser_biolio_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ser_language`
--

DROP TABLE IF EXISTS `ser_language`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ser_language` (
  `library_id` varchar(20) NOT NULL,
  `language_id` varchar(10) NOT NULL,
  `lan_name` varchar(15) default NULL,
  PRIMARY KEY  (`library_id`,`language_id`),
  KEY `FKA0F3F457FFE0695A` (`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ser_language`
--

LOCK TABLES `ser_language` WRITE;
/*!40000 ALTER TABLE `ser_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `ser_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ser_new_entry`
--

DROP TABLE IF EXISTS `ser_new_entry`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ser_new_entry` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `new_serial_id` varchar(10) NOT NULL,
  `serial_type` varchar(15) default NULL,
  `title` varchar(15) default NULL,
  `publisher` varchar(15) default NULL,
  `sub_publisher` varchar(15) default NULL,
  `address` varchar(30) default NULL,
  `place` varchar(15) default NULL,
  `country` varchar(15) default NULL,
  `sponsor` varchar(15) default NULL,
  `editor` varchar(15) default NULL,
  `language1` varchar(10) default NULL,
  `language2` varchar(10) default NULL,
  `language3` varchar(10) default NULL,
  `language4` varchar(10) default NULL,
  `type1` varchar(10) default NULL,
  `type2` varchar(10) default NULL,
  `type3` varchar(10) default NULL,
  `start_yr` varchar(10) default NULL,
  `volume` varchar(10) default NULL,
  `issue` varchar(10) default NULL,
  `coden` varchar(10) default NULL,
  `issn` varchar(15) default NULL,
  `alpha_code` varchar(10) default NULL,
  `recommended_by` varchar(15) default NULL,
  `date` varchar(10) default NULL,
  `short_title` varchar(15) default NULL,
  `approval_req` varchar(15) default NULL,
  `subscription_year` varchar(10) default NULL,
  `status` varchar(10) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`,`new_serial_id`),
  KEY `FK132E1BF4FFE0695A` (`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ser_new_entry`
--

LOCK TABLES `ser_new_entry` WRITE;
/*!40000 ALTER TABLE `ser_new_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `ser_new_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ser_publisher`
--

DROP TABLE IF EXISTS `ser_publisher`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ser_publisher` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `pub_id` varchar(20) NOT NULL,
  `pub-name` varchar(15) default NULL,
  `pub_add` varchar(30) default NULL,
  `city` varchar(15) default NULL,
  `state` varchar(15) default NULL,
  `country` varchar(15) default NULL,
  `pub_url` varchar(15) default NULL,
  `pub_phone` varchar(15) default NULL,
  `fax` varchar(15) default NULL,
  `email` varchar(15) default NULL,
  PRIMARY KEY  (`library_id`,`sublibrary_id`,`pub_id`),
  KEY `FK795060DDFFE0695A` (`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ser_publisher`
--

LOCK TABLES `ser_publisher` WRITE;
/*!40000 ALTER TABLE `ser_publisher` DISABLE KEYS */;
/*!40000 ALTER TABLE `ser_publisher` ENABLE KEYS */;
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
  KEY `FK125EFE6CFFE0695A` (`library_id`),
  KEY `FK125EFE6CDD50131F` (`library_id`,`emptype_id`),
  CONSTRAINT `FK125EFE6CDD50131F` FOREIGN KEY (`library_id`, `emptype_id`) REFERENCES `employee_type` (`library_id`, `emptype_id`),
  CONSTRAINT `FK125EFE6CFFE0695A` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `sub_employee_type_ibfk_1` FOREIGN KEY (`library_id`, `emptype_id`) REFERENCES `employee_type` (`library_id`, `emptype_id`),
  CONSTRAINT `sub_employee_type_ibfk_2` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sub_employee_type`
--

LOCK TABLES `sub_employee_type` WRITE;
/*!40000 ALTER TABLE `sub_employee_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_employee_type` ENABLE KEYS */;
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
  KEY `FK5672E9942ABA5656` (`library_id`,`emptype_id`,`sub_emptype_id`),
  CONSTRAINT `book_category_ibfk_1` FOREIGN KEY (`library_id`, `emptype_id`, `sub_emptype_id`) REFERENCES `sub_employee_type` (`library_id`, `emptype_id`, `sub_emptype_id`),
  CONSTRAINT `FK5672E9942ABA5656` FOREIGN KEY (`library_id`, `emptype_id`, `sub_emptype_id`) REFERENCES `sub_employee_type` (`library_id`, `emptype_id`, `sub_emptype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
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
  `bind_type` varchar(20) default NULL,
  `status` varchar(20) default NULL,
  `book_language` varchar(20) default NULL,
  `ref_no` varchar(20) default NULL,
`title1` VARCHAR(200), `subtitle1` VARCHAR(200), `alt_title1` VARCHAR(200), `statement_responsibility1` VARCHAR(200), `main_entry1` VARCHAR(200), `added_entrymli`  VARCHAR(200), `added_entry11` VARCHAR(200), `added_entry21` VARCHAR(200), `added_entry31` VARCHAR(200), `publisher_name1` VARCHAR(200), `publication_place1` VARCHAR(200), `publishing_year1` VARCHAR(20), `subject1` VARCHAR(200), `edition1` VARCHAR(20), `collation11` VARCHAR(20), `notes1` VARCHAR(2000), `abstract1` VARCHAR(2000), `series1` VARCHAR(1000), `volume_no1` VARCHAR(20), `shelving_location1` VARCHAR(200), `index_no1` VARCHAR(20), `no_of_pages1` VARCHAR(20), `bind_type1` VARCHAR(20)
  PRIMARY KEY  (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `temp_excell_import`
--

LOCK TABLES `temp_excell_import` WRITE;
/*!40000 ALTER TABLE `temp_excell_import` DISABLE KEYS */;
/*!40000 ALTER TABLE `temp_excell_import` ENABLE KEYS */;
UNLOCK TABLES;
CREATE TABLE `fine_details` (
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sublibrary_id` varchar(20) NOT NULL DEFAULT '',
  `memid` varchar(70) NOT NULL DEFAULT '',
  `slipno` varchar(255) NOT NULL DEFAULT '',
  `tfine` double DEFAULT NULL,
  `paid` double DEFAULT NULL,
  `remaining` double DEFAULT NULL,
  `paymod` varchar(50) DEFAULT NULL,
  `cheque_dd_no` varchar(50) DEFAULT NULL,
  `bankname` varchar(255) DEFAULT NULL,
  `issuedate` varchar(50) DEFAULT NULL,
  `paydate` varchar(50) DEFAULT NULL,
  `paid1` double DEFAULT NULL,
  `paid2` double DEFAULT NULL,
  `paid3` double DEFAULT NULL,
  `paid4` double DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sublibrary_id`,`memid`,`slipno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

