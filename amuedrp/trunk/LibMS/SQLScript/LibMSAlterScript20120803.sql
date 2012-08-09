alter table library modify library_name varchar(100);
ALTER TABLE admin_registration drop column insti_logo;
alter table admin_registration add insti_logo varchar(200);
ALTER TABLE cir_requestfrom_opac drop column image;
alter table cir_requestfrom_opac add image varchar(200);
alter table bibliographic_details add column (image varchar(200),digital_data varchar(200),digital_comment varchar(300));
alter table bibliographic_details_lang add column (image varchar(200),digital_data varchar(200),digital_comment varchar(300));
drop table notice;
alter table demandlist add demand_id int;
ALTER TABLE demandlist DROP PRIMARY KEY;
ALTER TABLE demandlist ADD PRIMARY KEY (demand_id,library_id,memId,sublibrary_id,title);
alter table demandlist add column (member_type varchar(100),
        sub_member_type varchar(100),
        mem_name varchar(100),
            sub_author varchar(50),
        sub_author0  varchar(50),
        sub_author1  varchar(50),
        sub_author2  varchar(50),
        publication_place  varchar(50),
        lcc_no  varchar(50));
alter table bibliographic_details_lang modify publishing_year int;
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

alter table acq_budget_allocation add column expense_amount varchar(11);
alter table acq_budget_transaction add column expense_amount varchar(11);

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
  PRIMARY KEY (`invoice_no`,`library_id`,`sub_library_id`,`recieving_no`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `acq_invoice_detail_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

alter table acq_recieving_details add column(  `vendor_id` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);

CREATE TABLE acq_invoice_header (library_id VARCHAR(20) NOT NULL, sublibrary_id VARCHAR(20) NOT NULL, invoice_no VARCHAR(20) NOT NULL, vendor_id VARCHAR(20) NOT NULL, order_no VARCHAR(20) NOT NULL, `date` VARCHAR(20), recieved_by VARCHAR(20), discount VARCHAR(20), net_total VARCHAR(20), status VARCHAR(20), overall_discount VARCHAR(20), total_net_amount VARCHAR(20), misc_charges VARCHAR(20), grand_total VARCHAR(15), invoice_date VARCHAR(20), PRIMARY KEY (invoice_no, library_id, order_no, sublibrary_id, vendor_id));


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







