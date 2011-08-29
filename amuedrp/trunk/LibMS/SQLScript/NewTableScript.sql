--
-- Table structure for table `acq_approval`
--

CREATE TABLE `acq_approval` (
  `approval_item_id` int(11) NOT NULL DEFAULT '0',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `control_no` int(11) DEFAULT NULL,
  `approval_no` varchar(20) DEFAULT NULL,
  `no_of_copies` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `order_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`approval_item_id`),
  KEY `library_id` (`library_id`,`sub_library_id`,`approval_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `acq_approval_header`
--

CREATE TABLE `acq_approval_header` (
  `approval_no` varchar(20) NOT NULL DEFAULT '',
  `vendor_id` varchar(50) DEFAULT NULL,
  `approved_by` varchar(50) DEFAULT NULL,
  `approval_date` varchar(20) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `acq_mode` varchar(20) DEFAULT NULL,
  `recommended_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`approval_no`),
  KEY `library_id` (`library_id`,`sub_library_id`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `acq_bibliography`
--

CREATE TABLE `acq_bibliography` (
  `title_id` int(11) NOT NULL DEFAULT '0',
  `doc_type` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `publisher_name` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `lcc_no` varchar(20) DEFAULT NULL,
  `series` varchar(20) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_author` varchar(50) DEFAULT NULL,
  `publishing_yr` varchar(20) DEFAULT NULL,
  `publishing_place` varchar(20) DEFAULT NULL,
  `edition` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `volume_no` varchar(13) DEFAULT NULL,
  `sub_author0` varchar(100) DEFAULT NULL,
  `sub_author1` varchar(100) DEFAULT NULL,
  `sub_author2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`title_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Table structure for table `acq_bibliography_details`
--

CREATE TABLE `acq_bibliography_details` (
  `control_no` int(11) NOT NULL DEFAULT '0',
  `title_id` int(11) DEFAULT NULL,
  `no_of_copies` int(11) DEFAULT NULL,
  `volume` varchar(50) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `currency` varchar(20) DEFAULT NULL,
  `primary_budget` varchar(50) DEFAULT NULL,
  `requested_by` varchar(50) DEFAULT NULL,
  `requested_date` varchar(20) DEFAULT NULL,
  `acq_mode` varchar(20) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `vendor` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `no_of_volume` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`control_no`),
  KEY `library_id` (`library_id`,`sub_library_id`,`title_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `acq_budget`
--
CREATE TABLE `acq_budget` (
  `budgethead_id` varchar(10) NOT NULL,
  `budgethead_name` varchar(20) DEFAULT NULL,
  `library_id` varchar(20) NOT NULL,
  `budget_desc` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`budgethead_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



--
-- Table structure for table `acq_budget_allocation`
--

CREATE TABLE `acq_budget_allocation` (
  `transaction_id` int(11) NOT NULL DEFAULT '0',
  `budgethead_id` varchar(10) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `opening_balance` varchar(50) DEFAULT NULL,
  `recieved_amount` varchar(50) DEFAULT NULL,
  `total_amount` varchar(20) DEFAULT NULL,
  `financial_yr1` varchar(20) DEFAULT NULL,
  `financial_yr2` varchar(20) DEFAULT NULL,
  `remarks` varchar(20) DEFAULT NULL,
  `reqdate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`transaction_id`),
  KEY `budgethead_id` (`budgethead_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Table structure for table `acq_budget_transaction`
--

CREATE TABLE `acq_budget_transaction` (
  `transaction_id` int(11) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `acq_budget_head_id` varchar(20) DEFAULT NULL,
  `control_no` varchar(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transaction_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`,`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Table structure for table `acq_currency`
--

CREATE TABLE `acq_currency` (
  `conversion_id` int(11) NOT NULL,
  `library_id` varchar(20) NOT NULL,
  `source_currency` varchar(20) DEFAULT NULL,
  `target_currency` varchar(20) DEFAULT NULL,
  `conversion_rate` float DEFAULT NULL,
  `system_date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`conversion_id`,`library_id`),
  KEY `library_id` (`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `acq_order_header`
--

CREATE TABLE `acq_order_header` (
  `order_no` varchar(20) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `vendor_id` varchar(50) DEFAULT NULL,
  `discount` varchar(20) DEFAULT NULL,
  `order_date` varchar(20) DEFAULT NULL,
  `due_date` varchar(20) DEFAULT NULL,
  `order_status` varchar(10) DEFAULT NULL,
  `cancel_reason` varchar(20) DEFAULT NULL,
  `deliviery_date` varchar(10) DEFAULT NULL,
  `ship_contact_name` varchar(100) DEFAULT NULL,
  `ship_company_name` varchar(500) DEFAULT NULL,
  `ship_address` varchar(500) DEFAULT NULL,
  `ship_pin` varchar(8) DEFAULT NULL,
  `ship_fax` varchar(16) DEFAULT NULL,
  `ship_email` varchar(100) DEFAULT NULL,
  `ship_cost` varchar(10) DEFAULT NULL,
  `other_cost` varchar(10) DEFAULT NULL,
  `tax_rate` varchar(10) DEFAULT NULL,
  `tax_amount` varchar(10) DEFAULT NULL,
  `grand_total` varchar(15) DEFAULT NULL,
  `shipping_method` varchar(100) DEFAULT NULL,
  `shipping_terms` varchar(500) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `acq_order1`
--

CREATE TABLE `acq_order1` (
  `order_item_id` varchar(10) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '',
  `control_no` int(11) DEFAULT NULL,
  `approval_item_id` int(11) DEFAULT NULL,
  `recieving_status` varchar(20) DEFAULT NULL,
  `recieving_date` varchar(10) DEFAULT NULL,
  `recieving_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`order_no`,`order_item_id`),
  CONSTRAINT `acq_order1_ibfk_1` FOREIGN KEY (`library_id`, `sub_library_id`, `order_no`) REFERENCES `acq_order_header` (`library_id`, `sub_library_id`, `order_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `acq_recieving_details`
--

CREATE TABLE `acq_recieving_details` (
  `recieving_item_id` int(11) NOT NULL DEFAULT '0',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `recieving_no` varchar(20) NOT NULL DEFAULT '',
  `title_id` int(11) DEFAULT NULL,
  `unit_price` varchar(10) DEFAULT NULL,
  `recieved_copies` int(11) DEFAULT NULL,
  `pending_copies` int(11) DEFAULT NULL,
  `approval_type` varchar(20) DEFAULT NULL,
  `control_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`recieving_no`,`recieving_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `acq_recieving_header`
--

CREATE TABLE `acq_recieving_header` (
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `recieving_no` varchar(20) NOT NULL DEFAULT '',
  `vendor_id` varchar(20) DEFAULT NULL,
  `order_no` varchar(20) DEFAULT NULL,
  `recieved_date` varchar(20) DEFAULT NULL,
  `recieved_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`recieving_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `acq_vendor`
--

CREATE TABLE `acq_vendor` (
  `vendor_id` varchar(50) NOT NULL DEFAULT '',
  `library_id` varchar(20) NOT NULL DEFAULT '',
  `address` varchar(50) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `fax` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `entry_date` varchar(20) DEFAULT NULL,
  `sub_library_id` varchar(20) NOT NULL DEFAULT '',
  `vendor` varchar(100) DEFAULT NULL,
  `contact_person` varchar(50) DEFAULT NULL,
  `vendor_currency` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`sub_library_id`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Alter Table structure for table `admin_registration`
--

alter TABLE `admin_registration` change `courtesy`  `courtesy` varchar(30) DEFAULT NULL  ;

--
-- Table structure for table `base_currency`
--

CREATE TABLE `base_currency` (
  `library_id` varchar(20) NOT NULL,
  `base_currency_symbol` varchar(20) NOT NULL DEFAULT '',
  `Formal_Name` varchar(20) DEFAULT NULL,
  `direction` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`base_currency_symbol`,`library_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `temp_excell_import` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `document_type` varchar(20) DEFAULT NULL,
  `book_type` varchar(20) DEFAULT NULL,
  `accession_type` varchar(20) DEFAULT NULL,
  `date_acquired` varchar(20) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `subtitle` varchar(200) DEFAULT NULL,
  `alt_title` varchar(200) DEFAULT NULL,
  `statement_responsibility` varchar(200) DEFAULT NULL,
  `main_entry` varchar(200) DEFAULT NULL,
  `added_entry` varchar(200) DEFAULT NULL,
  `added_entry1` varchar(200) DEFAULT NULL,
  `added_entry2` varchar(200) DEFAULT NULL,
  `added_entry3` varchar(200) DEFAULT NULL,
  `publisher_name` varchar(200) DEFAULT NULL,
  `publication_place` varchar(200) DEFAULT NULL,
  `publishing_year` varchar(20) DEFAULT NULL,
  `call_no` varchar(30) DEFAULT NULL,
  `parts_no` varchar(20) DEFAULT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `entry_language` varchar(200) DEFAULT NULL,
  `isbn10` varchar(20) DEFAULT NULL,
  `isbn13` varchar(20) DEFAULT NULL,
  `LCC_no` varchar(30) DEFAULT NULL,
  `edition` varchar(20) DEFAULT NULL,
  `no_of_copies` varchar(20) DEFAULT NULL,
  `author_name` varchar(200) DEFAULT NULL,
  `guide_name` varchar(200) DEFAULT NULL,
  `university_faculty` varchar(200) DEFAULT NULL,
  `degree` varchar(200) DEFAULT NULL,
  `submitted_on` varchar(20) DEFAULT NULL,
  `acceptance_year` varchar(20) DEFAULT NULL,
  `collation1` varchar(20) DEFAULT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `abstract` varchar(2000) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `state1` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `frmr_frq` varchar(20) DEFAULT NULL,
  `frq_date` varchar(20) DEFAULT NULL,
  `issn` varchar(20) DEFAULT NULL,
  `volume_location` varchar(20) DEFAULT NULL,
  `production_year` varchar(20) DEFAULT NULL,
  `source1` varchar(20) DEFAULT NULL,
  `duration` varchar(20) DEFAULT NULL,
  `series` varchar(1000) DEFAULT NULL,
  `type_of_disc` varchar(20) DEFAULT NULL,
  `file_type` varchar(20) DEFAULT NULL,
  `accession_no` varchar(40) DEFAULT NULL,
  `record_no` varchar(20) DEFAULT NULL,
  `volume_no` varchar(20) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `shelving_location` varchar(200) DEFAULT NULL,
  `index_no` varchar(20) DEFAULT NULL,
  `no_of_pages` varchar(20) DEFAULT NULL,
  `physical_width` varchar(20) DEFAULT NULL,
  `physical_form` varchar(200) DEFAULT NULL,
  `physical_description` varchar(200) DEFAULT NULL,
  `colour` varchar(20) DEFAULT NULL,
  `bind_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `logs` (
  `sno` int(5) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `classname` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `action_message` varchar(100) DEFAULT NULL,
  `action_result` varchar(100) DEFAULT NULL,
  `library_id` varchar(20) DEFAULT NULL,
  `sublibrary_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=latin1;


CREATE TABLE `logsetting` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `p1` varchar(100) DEFAULT NULL,
  `p2` varchar(100) DEFAULT NULL,
  `p3` varchar(100) DEFAULT NULL,
  `p4` varchar(100) DEFAULT NULL,
  `p5` varchar(100) DEFAULT NULL,
  `p6` varchar(100) DEFAULT NULL,
  `p7` varchar(100) DEFAULT NULL,
  `p8` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `bibliographic_details_lang` (
  `biblio_id` int(11) NOT NULL DEFAULT '0',
  `library_id` varchar(20) CHARACTER SET latin1 NOT NULL,
  `sublibrary_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `document_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `book_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `accession_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `date_acquired` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `subtitle` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `alt_title` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `statement_responsibility` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `main_entry` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `added_entry` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `added_entry1` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `added_entry2` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `added_entry3` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `publisher_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `publication_place` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `publishing_year` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `call_no` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `parts_no` int(11) DEFAULT NULL,
  `subject` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `entry_language` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `isbn10` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `isbn13` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `LCC_no` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `edition` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `no_of_copies` int(11) DEFAULT NULL,
  `author_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `guide_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `university_faculty` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `degree` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `submitted_on` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `acceptance_year` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `collation1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `notes` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `abstract` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `state1` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `frmr_frq` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `frq_date` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `issn` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `volume_location` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `production_year` int(11) DEFAULT NULL,
  `source1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `duration` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `series` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `type_of_disc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `file_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`biblio_id`,`library_id`,`sublibrary_id`),
  KEY `library_id` (`library_id`),
  CONSTRAINT `bibliographic_details_lang_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

