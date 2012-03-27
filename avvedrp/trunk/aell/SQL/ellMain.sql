
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";



--
-- Database: `ell`
--

-- --------------------------------------------------------

--
-- Table structure for table `aell_dict_image`
--

CREATE TABLE IF NOT EXISTS `aell_dict_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `aell_dict_list_id` bigint(20) NOT NULL,
  `image_link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK44D75B6CF31E0CFC` (`aell_dict_list_id`),
  KEY `FK44D75B6C6A7479C9` (`aell_dict_list_id`),
  KEY `FK50FA9E0D7A29B3F0` (`aell_dict_list_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `aell_dict_image`
--


-- --------------------------------------------------------

--
-- Table structure for table `aell_dict_list`
--

CREATE TABLE IF NOT EXISTS `aell_dict_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `definition` text NOT NULL,
  `aell_dict_type_master_id` bigint(20) NOT NULL,
  `level` varchar(255) NOT NULL,
  `word` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK96DEF86DD063EE0F` (`aell_dict_type_master_id`),
  KEY `FK96DEF86D6C96CD1C` (`aell_dict_type_master_id`),
  KEY `FK2BE85DACB8D79EE1` (`aell_dict_type_master_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Dumping data for table `aell_dict_list`
--

INSERT INTO `aell_dict_list` (`id`, `version`, `definition`, `aell_dict_type_master_id`, `level`, `word`) VALUES
(2, 0, 'A Dutch and German measure of liquids, varying in different cities, being at Amsterdam about 41 wine gallons, at Antwerp 36&frac12;, at Hamburg 38&frac14;.', 1, '3', 'a'),
(3, 0, 'An edentate mammal, of the genus <i>Orycteropus</i>, somewhat resembling a pig, common in some parts of Southern Africa. It burrows in the ground, and feeds entirely on ants, which it catches with its long, slimy tongue.', 2, '3', 'the'),
(4, 0, 'A carnivorous quadruped (<i>Proteles Lalandii</i>), of South Africa, resembling the fox and hyena. See <u>Proteles</u>.', 2, '3', 'in'),
(5, 0, 'Pertaining to Aaron, the first high priest of the Jews.', 1, '3', 'on'),
(6, 0, 'The fifth month of the Jewish year according to the ecclesiastical reckoning, the eleventh by the civil computation, coinciding nearly with August.', 2, '3', 'or'),
(7, 0, 'The Manila-hemp plant <i>Musa textilis</i>; also, its fiber. See <i>Manila hemp</i> under <u>Manila</u>', 2, '3', 'it'),
(8, 0, 'To blind by a red-hot metal plate held before the eyes.', 3, '3', 'to'),
(9, 0, 'The act of abacinating.', 2, '3', 'by'),
(10, 0, 'One of the tiles or squares of a tessaellated pavement; an abaculus.', 2, '3', 'one'),
(11, 0, 'One who uses an abacus in casting accounts; a calculator.', 2, '3', 'has'),
(12, 0, 'Toward the back or rear; backward.', 4, '3', 'and'),
(13, 0, 'An abacus.', 2, '3', 'is'),
(14, 0, 'Pertaining to the surface or end opposite to the mouth in a radiate animal; -- opposed to <i>actinal</i>.', 1, '3', 'you'),
(15, 0, 'Stealing cattle on a large scale.', 2, '3', 'your'),
(16, 0, 'One who steals and drives away cattle or beasts by herds or droves.', 2, '3', 'at'),
(17, 0, 'A small tile of glass, marble, or other substance, of various colors, used in making ornamental patterns in mosaic pavements.', 2, '3', 'can'),
(18, 0, 'A table or tray strewn with sand, anciently used for drawing, calculating, etc.', 2, '3', 'be'),
(19, 0, 'The rhinoceros.', 2, '3', 'for'),
(20, 0, 'The destroyer, or angel of the bottomless pit; -- the same as Apollyon and Asmodeus.', 2, '3', 'fine'),
(21, 0, 'Behind; toward the stern from; as, <i>abaft</i> the wheelhouse.', 5, '3', 'resume'),
(22, 0, 'Toward the stern; aft; as, to go <i>abaft</i>.', 4, '3', 'people'),
(23, 0, 'Obeisance.', 2, '3', 'with'),
(24, 0, 'Ivory black or animal charcoal.', 2, '3', 'most'),
(25, 0, 'Abashed; confounded; discomfited.', 6, '3', 'have'),
(26, 0, 'To transfer the title of from one to another; to alienate.', 3, '3', 'was'),
(27, 0, 'The act of abalienating; alienation; estrangement.', 2, '3', 'such'),
(28, 0, 'A univalve mollusk of the genus <i>Haliotis</i>. The shaell is lined with mother-of-pearl, and used for ornamental purposes; the sea-ear. Several large species are found on the coast of California, clinging closely to the rocks.', 2, '3', 'up'),
(29, 0, 'To abandon.', 3, '3', 'back'),
(30, 0, 'To cast or drive out; to banish; to expel; to reject.', 3, '3', 'should'),
(31, 0, 'Abandonment; relinquishment.', 2, '3', 'any'),
(32, 0, 'A complete giving up to natural impulses; freedom from artificial constraint; careless freedom or ease.', 2, '3', 'of'),
(33, 0, 'Forsaken, deserted.', 1, '3', 'but'),
(34, 0, 'Unrestrainedly.', 4, '3', 'there'),
(35, 0, 'One to whom anything is legally abandoned.', 2, '3', 'style'),
(36, 0, 'One who abandons.', 2, '3', 'format'),
(37, 0, 'The act of abandoning, or the state of being abandoned; total desertion; relinquishment.', 2, '3', 'time'),
(38, 0, 'Anything forfeited or confiscated.', 2, '3', 'search'),
(39, 0, 'See <u>Abnet</u>.', 2, '3', 'if'),
(40, 0, 'A West Indian palm; also the fruit of this palm, the seeds of which are used as a remedy for diseases of the chest.', 2, '3', 'Abanga');

-- --------------------------------------------------------

--
-- Table structure for table `aell_dict_type_master`
--

CREATE TABLE IF NOT EXISTS `aell_dict_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=196 ;

--
-- Dumping data for table `aell_dict_type_master`
--

INSERT INTO `aell_dict_type_master` (`id`, `version`, `type`) VALUES
(1, 0, '<i>adjective.</i>'),
(2, 0, '<i>noun.</i>'),
(3, 0, '<i>transitive verb.</i>'),
(4, 0, '<i>adverb.</i>'),
(5, 0, '<i>preposition.</i>'),
(6, 0, '<i>past participle.</i>'),
(7, 0, '<i>intransitive verb.</i>'),
(8, 0, '<i>Noun. plural.</i>'),
(9, 0, '<i>adverb and adjective</i>'),
(10, 0, '<i>verb  transitive & intransitive.</i>'),
(11, 0, '<i>verb</i>'),
(12, 0, '<i>participial adjective.</i>'),
(13, 0, '<i>past participle  & a.</i>'),
(14, 0, '<i>adjective. & noun.</i>'),
(15, 0, '<i>adjective. & adverbe.</i>'),
(16, 0, '<i>interjection and adverb</i>'),
(17, 0, '<i>interjection.</i>'),
(18, 0, '<i>v. inf.</i>'),
(19, 0, '<i>noun . & adjective.</i>'),
(20, 0, '<i>superlative.</i>'),
(21, 0, '<i>adverb and preposition.</i>'),
(22, 0, '<i>adjective & past participle</i>'),
(23, 0, '<i>noun. & verb.</i>'),
(24, 0, '<i>Noun .singular. & plural.</i>'),
(25, 0, '<i>A prefix.</i>'),
(26, 0, '<i>conjunction. .</i>'),
(27, 0, '<i>transitive verb. or intransitive.</i>'),
(28, 0, '<i>preposition. & adverb.</i>'),
(29, 0, '<i>adv. & conj.</i>'),
(30, 0, '<i>n. fem.</i>'),
(31, 0, '<i>transitive verb. & intransitive.</i>'),
(32, 0, '<i>interjunction.</i>'),
(33, 0, '<i>pronoun and adjective</i>'),
(34, 0, '<i>adjuctive. & noun.</i>'),
(35, 0, '<i>noun. plural</i>'),
(36, 0, '<i>adjective. & pronoun.</i>'),
(37, 0, '<i>past participle. </i>'),
(38, 0, '<i>adverb. or preposition.</i>'),
(39, 0, '<i>intransitive verb. & t.</i>'),
(40, 0, '<i>transitive verb </i>'),
(41, 0, '<i>noun</i>'),
(42, 0, '<i>imperative</i>'),
(43, 0, '<i>noun. & transitive verb.</i>'),
(44, 0, '<i>noun.</i>'),
(45, 0, '<i>present participle.</i>'),
(46, 0, '<i>imperative. & past participle </i>'),
(47, 0, '<i>noun. &  transitive verb. & ntransitive.</i>'),
(48, 0, '<i>prefix.</i>'),
(49, 0, '<i>3rd singular. person</i>'),
(50, 0, '<i>adjective. or pronoun.</i>'),
(51, 0, '<i>past participle & adjective.</i>'),
(52, 0, '<i>participial adjective.</i>'),
(53, 0, '<i>plural noun.</i>'),
(54, 0, '<i>ads.</i>'),
(55, 0, '<i>3rd person singular.</i>'),
(56, 0, '<i>n.masc.</i>'),
(57, 0, '<i>verb. & noun.</i>'),
(58, 0, '<i> intransitive verb. &  intransitive.</i>'),
(59, 0, '<i> transitive verb. or intransitive verb.</i>'),
(60, 0, '<i>singular noun & plural.</i>'),
(61, 0, '<i> transitive verb. & noun.</i>'),
(62, 0, '<i>Pronoun</i>'),
(63, 0, '<i>past participle or adjective.</i>'),
(64, 0, '<i>female.</i>'),
(65, 0, '<i>3rd person singular</i>'),
(66, 0, '<i> singular noun</i>'),
(67, 0, '<i>verb</i>'),
(68, 0, '<i>intransitive verb.</i>'),
(69, 0, '<i>participle. & a.</i>'),
(70, 0, '<i>past participle & adjective</i>'),
(71, 0, '<i>singular noun & plural.</i>'),
(72, 0, '<i>n. masc.</i>'),
(73, 0, '<i>adjuctive. </i>'),
(74, 0, '<i>adjective</i>'),
(75, 0, '<i>present participle & vb. n.</i>'),
(76, 0, '<i>participial adjective. & vb. n.</i>'),
(77, 0, '<i>noun</i>'),
(78, 0, '<i>present participle. & a.</i>'),
(79, 0, '<i>imperative singular.</i>'),
(80, 0, '<i>&?;.</i>'),
(81, 0, '<i>noun. or interjunction.</i>'),
(82, 0, '<i>intransitive verb. & noun.</i>'),
(83, 0, '<i>variant</i>'),
(84, 0, '<i>transitive verb. or auxiliary</i>'),
(85, 0, '<i>2d person. singular. present.</i>'),
(86, 0, '<i>3d person. singular. present.</i>'),
(87, 0, '<i> verbal. . n.</i>'),
(88, 0, '<i>compar.</i>'),
(89, 0, '<i>participle.</i>'),
(90, 0, '<i>a. or a. pronoun.</i>'),
(91, 0, '<i>obs. imperative.</i>'),
(92, 0, '<i>adverb. or interjunction.</i>'),
(93, 0, '<i>transitive verb.</i>'),
(94, 0, '<i> conjunction. or adverb.</i>'),
(95, 0, '<i>noun. & adverb.</i>'),
(96, 0, '<i>a</i>'),
(97, 0, '<i>a. & a. pron.</i>'),
(98, 0, '<i>preposition. & conjunction.</i>'),
(99, 0, '<i>noun. & intransitive verb.</i>'),
(100, 0, '<i>adjuctive. or adverb.</i>'),
(101, 0, '<i>collective noun. & plural.</i>'),
(102, 0, '<i>plural.</i>'),
(103, 0, '<i>singular noun & plural.</i>'),
(104, 0, '<i>noun</i>'),
(105, 0, '<i>a. compar.</i>'),
(106, 0, '<i>a. or n.</i>'),
(107, 0, '<i>p. pr.</i>'),
(108, 0, '<i>prefix.</i>'),
(109, 0, '<i>n. & interjunction.</i>'),
(110, 0, '<i>inf. & plural present.</i>'),
(111, 0, '<i>suffix.</i>'),
(112, 0, '<i>a & adv.</i>'),
(113, 0, '<i>interjunction. & noun.</i>'),
(114, 0, '<i> plural pronoun.</i>'),
(115, 0, '<i>plural pronoun..</i>'),
(116, 0, '<i>adjuctive. & conjunction.</i>'),
(117, 0, '<i>pronoun. or adjuctive.</i>'),
(118, 0, '<i>adverb. or a.</i>'),
(119, 0, '<i>n & v.</i>'),
(120, 0, '<i>q.</i>'),
(121, 0, '<i>a & n.</i>'),
(122, 0, '<i>adv. & n.</i>'),
(123, 0, '<i>n. f.</i>'),
(124, 0, '<i>strong imp.</i>'),
(125, 0, '<i>a.</i>'),
(126, 0, '<i>transitive noun.</i>'),
(127, 0, '<i>v. n.</i>'),
(128, 0, '<i>prop. a.</i>'),
(129, 0, '<i>a. m.</i>'),
(130, 0, '<i>n. m.</i>'),
(131, 0, '<i>pers. pron.</i>'),
(132, 0, '<i>transitive verb.& intransitive.</i>'),
(133, 0, '<i>v. impers.</i>'),
(134, 0, '<i>adjective.</i>'),
(135, 0, '<i> intransitive verb or auxiliary</i>'),
(136, 0, '<i>a. & poss. pron.</i>'),
(137, 0, '<i>past participle</i>'),
(138, 0, '<i>adverb. or conjunction.</i>'),
(139, 0, '<i>imperative.</i>'),
(140, 0, '<i>indef. pronoun.</i>'),
(141, 0, '<i>imperative</i>'),
(142, 0, '<i>possessive pron.</i>'),
(143, 0, '<i>imp.</i>'),
(144, 0, '<i>masc.</i>'),
(145, 0, '<i>plural noun.</i>'),
(146, 0, '<i>&aelig;.</i>'),
(147, 0, '<i>female adjective</i>'),
(148, 0, '<i>a. & v. t.</i>'),
(149, 0, '<i>v. imperative.</i>'),
(150, 0, '<i>noun.</i>'),
(151, 0, '<i>transitive verb</i>'),
(152, 0, '<i>noun.& verb.</i>'),
(153, 0, '<i>noun.</i>'),
(154, 0, '<i>adjective. masculine .</i>'),
(155, 0, '<i>verb.& noun.</i>'),
(156, 0, '<i>observation. imperative.plural.</i>'),
(157, 0, '<i>plural noun & singular.</i>'),
(158, 0, '<i>conjunction. & preposition.</i>'),
(159, 0, '<i>preposition. or conjunction.</i>'),
(160, 0, '<i>itransitive verb  & intransitive verb </i>'),
(161, 0, '<i>conj. (but originally a present participle)</i>'),
(162, 0, '<i>adverb.</i>'),
(163, 0, '<i> intransitive verb  & auxiliary.</i>'),
(164, 0, '<i>preposition.</i>'),
(165, 0, '<i>masculine . adjective.</i>'),
(166, 0, '<i>ajective also adverb</i>'),
(167, 0, '<i>subject. 3rd person. singular.</i>'),
(168, 0, '<i>adjective.</i>'),
(169, 0, '<i>propositon. noun. plural.</i>'),
(170, 0, '<i>preposition.</i>'),
(171, 0, '<i>verb. impersonal</i>'),
(172, 0, '<i>pronoun.</i>'),
(173, 0, '<i>definite article.</i>'),
(174, 0, '<i>conjunction. & adverbe.</i>'),
(175, 0, '<i>definite. article.</i>'),
(176, 0, '<i>preposition.</i>'),
(177, 0, '<i>preposition. & conjunction.</i>'),
(178, 0, '<i>interjunction.</i>'),
(179, 0, '<i>transitive verb & i.</i>'),
(180, 0, '<i>noun.</i>'),
(181, 0, '<i>transitive verb.</i>'),
(182, 0, '<i>superl.</i>'),
(183, 0, '<i>imperative.</i>'),
(184, 0, '<i>noun. plural</i>'),
(185, 0, '<i>pronoun</i>'),
(186, 0, '<i>interrogation. adverb.</i>'),
(187, 0, '<i>pronoun. & conjunction.</i>'),
(188, 0, '<i>transitive verb & auxiliary.</i>'),
(189, 0, '<i>adverb. & verb.</i>'),
(190, 0, '<i>adjective , adverb , & noun</i>'),
(191, 0, '<i>past participle , fem</i>'),
(192, 0, '<i>imperfect , past participle , or auxiliary</i>'),
(193, 0, '<i>imperfect , past participle , & adjective</i>'),
(194, 0, '<i>adverb , preposition , & conj.</i>'),
(195, 0, '<i>impersonal, present</i>');

-- --------------------------------------------------------

--
-- Table structure for table `aell_dict_voice`
--

CREATE TABLE IF NOT EXISTS `aell_dict_voice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `aell_dict_list_id` bigint(20) NOT NULL,
  `voice_link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK458F9343F31E0CFC` (`aell_dict_list_id`),
  KEY `FK458F93436A7479C9` (`aell_dict_list_id`),
  KEY `FK51B2D5E47A29B3F0` (`aell_dict_list_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `aell_dict_voice`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_answer_sheet`
--

CREATE TABLE IF NOT EXISTS `avl_answer_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `ans_id` bigint(20) DEFAULT NULL,
  `qn_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_answer_sheet`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_content_description_version`
--

CREATE TABLE IF NOT EXISTS `avl_content_description_version` (
  `version_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `content_id` int(5) unsigned NOT NULL,
  `content_mode` enum('L','T','Q','FB','RT') NOT NULL,
  `content_description` mediumtext,
  `user_id` int(5) unsigned NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version_status` enum('S','SR','P','D','R') DEFAULT NULL,
  PRIMARY KEY (`version_id`),
  KEY `content_id_index` (`content_id`),
  KEY `FKE76575676262EDF7` (`content_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_content_description_version`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_content_details`
--

CREATE TABLE IF NOT EXISTS `avl_content_details` (
  `content_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `content_type_id` int(5) unsigned NOT NULL,
  `experiment_id` int(5) unsigned NOT NULL,
  `content_type_sequence` int(5) unsigned NOT NULL,
  `version_id` int(5) unsigned DEFAULT NULL,
  `authentication_needed` enum('Y','N') NOT NULL DEFAULT 'N',
  `content_status` enum('A','D','E') DEFAULT 'A',
  PRIMARY KEY (`content_id`),
  UNIQUE KEY `content_id` (`content_id`),
  KEY `content_details_index` (`experiment_id`),
  KEY `content_details_index1` (`content_type_id`,`experiment_id`),
  KEY `FKBB9C87141A1BBF22` (`content_type_id`),
  KEY `FKBB9C87145CEB5DE9` (`experiment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_content_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_content_type_master`
--

CREATE TABLE IF NOT EXISTS `avl_content_type_master` (
  `content_type_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `content_type_name` varchar(100) NOT NULL,
  `content_type_icon` varchar(100) NOT NULL,
  `type_mandatory` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`content_type_id`),
  UNIQUE KEY `content_type_id` (`content_type_id`),
  KEY `content_typeIndex1` (`content_type_name`),
  KEY `content_type_index` (`type_mandatory`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `avl_content_type_master`
--

INSERT INTO `avl_content_type_master` (`content_type_id`, `content_type_name`, `content_type_icon`, `type_mandatory`) VALUES
(1, 'Lesson', 'content_1.png', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `avl_error_master`
--

CREATE TABLE IF NOT EXISTS `avl_error_master` (
  `error_sl_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `error_id` varchar(50) NOT NULL,
  `error_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`error_sl_no`),
  UNIQUE KEY `error_sl_no` (`error_sl_no`,`error_id`),
  KEY `error_id` (`error_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_error_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_experiment_branch_rel`
--

CREATE TABLE IF NOT EXISTS `avl_experiment_branch_rel` (
  `experiment_branch_rel_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `experiment_id` int(5) unsigned NOT NULL,
  `branch_id` int(5) unsigned NOT NULL,
  `academic_year_from` date DEFAULT NULL,
  `academic_year_to` date DEFAULT NULL,
  `experiment_branch_status` enum('A','D') NOT NULL DEFAULT 'A',
  PRIMARY KEY (`experiment_branch_rel_id`),
  UNIQUE KEY `experiment_branch_rel_id` (`experiment_branch_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_experiment_branch_rel`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_experiment_master`
--

CREATE TABLE IF NOT EXISTS `avl_experiment_master` (
  `experiment_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `experiment_name` varchar(100) NOT NULL,
  `experiment_description` varchar(255) DEFAULT NULL,
  `experiment_type_id` int(5) unsigned NOT NULL,
  `experiment_status` enum('A','D') DEFAULT 'A',
  `owner` int(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`experiment_id`),
  UNIQUE KEY `experiment_id` (`experiment_id`),
  KEY `experiment_index` (`experiment_name`),
  KEY `FK8972417C609BDE24` (`experiment_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_experiment_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_experiment_subtopic_rel`
--

CREATE TABLE IF NOT EXISTS `avl_experiment_subtopic_rel` (
  `experiment_id` int(5) unsigned NOT NULL,
  `sub_topic_id` int(5) unsigned NOT NULL,
  `experiment_sequence` int(5) unsigned DEFAULT NULL,
  KEY `experiment_rel_index` (`sub_topic_id`),
  KEY `subtopic_rel_index` (`experiment_id`),
  KEY `FK3575AAE35CEB5DE9` (`experiment_id`),
  KEY `FK3575AAE35B0D0728` (`sub_topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_experiment_subtopic_rel`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_experiment_type_master`
--

CREATE TABLE IF NOT EXISTS `avl_experiment_type_master` (
  `experiment_typeid` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `experiment_type_name` varchar(100) NOT NULL,
  PRIMARY KEY (`experiment_typeid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `avl_experiment_type_master`
--

INSERT INTO `avl_experiment_type_master` (`experiment_typeid`, `experiment_type_name`) VALUES
(1, 'ELL Lab');

-- --------------------------------------------------------

--
-- Table structure for table `avl_forgot_password`
--

CREATE TABLE IF NOT EXISTS `avl_forgot_password` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `request_email` varchar(255) DEFAULT NULL,
  `request_date` datetime DEFAULT NULL,
  `request_code` varchar(500) DEFAULT NULL,
  `status` enum('Yes','No') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_forgot_password`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_log_details`
--

CREATE TABLE IF NOT EXISTS `avl_log_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_time` datetime NOT NULL,
  `content_type_id` varchar(255) NOT NULL,
  `duration` int(11) NOT NULL,
  `experiment_id` varchar(255) NOT NULL,
  `session_count` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `subject_id` varchar(255) NOT NULL,
  `topic_id` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_log_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_match_ans1`
--

CREATE TABLE IF NOT EXISTS `avl_match_ans1` (
  `id` int(11) unsigned NOT NULL DEFAULT '0',
  `left_choice_text` text,
  `qn_id` int(11) unsigned DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_match_ans1`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_match_ans2`
--

CREATE TABLE IF NOT EXISTS `avl_match_ans2` (
  `id` int(11) unsigned NOT NULL DEFAULT '0',
  `right_choice_text` text,
  `qn_id` int(11) unsigned DEFAULT NULL,
  `left_choice_match_id` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_match_ans2`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_mchoice_ans`
--

CREATE TABLE IF NOT EXISTS `avl_mchoice_ans` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `choices` text,
  `qn_id` int(11) unsigned DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_mchoice_ans`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_mchoice_match_qn`
--

CREATE TABLE IF NOT EXISTS `avl_mchoice_match_qn` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `qn_text` text,
  `qn_image` varchar(255) DEFAULT NULL,
  `qn_type_id` int(11) unsigned DEFAULT NULL,
  `qn_difficulty_level` varchar(25) DEFAULT NULL,
  `ans_type` varchar(25) DEFAULT NULL,
  `ans_correct` text,
  `ans_display_order` varchar(25) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_mchoice_match_qn`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_oauth_provider_master`
--

CREATE TABLE IF NOT EXISTS `avl_oauth_provider_master` (
  `provider_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `provider_name` varchar(50) NOT NULL,
  UNIQUE KEY `provider_id` (`provider_id`,`provider_name`),
  KEY `provider_index` (`provider_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_oauth_provider_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_oauth_user_rel`
--

CREATE TABLE IF NOT EXISTS `avl_oauth_user_rel` (
  `token_id` varchar(255) NOT NULL,
  `user_id` int(5) unsigned NOT NULL,
  `provider_id` int(5) unsigned NOT NULL,
  KEY `oauth_user_index` (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_oauth_user_rel`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_privilege_master`
--

CREATE TABLE IF NOT EXISTS `avl_privilege_master` (
  `privilege_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `privilege_name` varchar(50) NOT NULL,
  PRIMARY KEY (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_privilege_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_qn_content`
--

CREATE TABLE IF NOT EXISTS `avl_qn_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `difficulty_lvl` int(11) DEFAULT NULL,
  `disp_order` varchar(255) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  `parent_qn_id` bigint(20) NOT NULL,
  `qn_id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `text` text NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_qn_content`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_qn_hints`
--

CREATE TABLE IF NOT EXISTS `avl_qn_hints` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `qn_id` int(11) unsigned DEFAULT NULL,
  `hint_type` char(10) DEFAULT NULL,
  `hint_text` text,
  `status` char(1) DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_qn_hints`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_qn_paper`
--

CREATE TABLE IF NOT EXISTS `avl_qn_paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `content_id` int(11) NOT NULL,
  `create_dt` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  `qn_id` bigint(20) NOT NULL,
  `qn_paper_id` int(11) NOT NULL,
  `qn_type` varchar(255) NOT NULL,
  `qn_sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_qn_paper`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_qn_paper_master`
--

CREATE TABLE IF NOT EXISTS `avl_qn_paper_master` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_qn_paper_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_qn_paper_qn_relation`
--

CREATE TABLE IF NOT EXISTS `avl_qn_paper_qn_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `qn_paper_id` int(11) unsigned DEFAULT NULL,
  `qn_id` int(11) unsigned DEFAULT NULL,
  `qn_sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_qn_paper_qn_relation`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_qn_type_master`
--

CREATE TABLE IF NOT EXISTS `avl_qn_type_master` (
  `id` int(11) unsigned NOT NULL DEFAULT '0',
  `type_name` varchar(255) DEFAULT NULL,
  `qn_table_params` varchar(255) DEFAULT NULL,
  `ans_table_params` varchar(255) DEFAULT NULL,
  `qn_hints_params` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_qn_type_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_quiz_log_qp_session`
--

CREATE TABLE IF NOT EXISTS `avl_quiz_log_qp_session` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `question_paper_id` int(11) DEFAULT NULL,
  `date_time_presented` datetime DEFAULT NULL,
  `time_spend` int(11) unsigned DEFAULT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `attempt_number` int(11) DEFAULT NULL,
  `quiz_type` varchar(255) DEFAULT NULL,
  `session_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_quiz_log_qp_session`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_quiz_log_question`
--

CREATE TABLE IF NOT EXISTS `avl_quiz_log_question` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int(11) unsigned DEFAULT NULL,
  `hint_used` enum('Y','N') DEFAULT NULL,
  `ans_selected_text` text,
  `ans_correct` enum('Y','N') DEFAULT NULL,
  `quiz_log_qp_id` int(11) DEFAULT NULL,
  `question_type` varchar(255) DEFAULT NULL,
  `ans_expected_text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_quiz_log_question`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_role_master`
--

CREATE TABLE IF NOT EXISTS `avl_role_master` (
  `role_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id` (`role_id`),
  KEY `role_index` (`role_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1005 ;

--
-- Dumping data for table `avl_role_master`
--

INSERT INTO `avl_role_master` (`role_id`, `role_name`, `status`) VALUES
(1000, 'Administrator', 'A'),
(1001, 'Faculty', 'A'),
(1002, 'Guest', 'A'),
(1003, 'Student', 'A'),
(1004, 'ROLE_OPENID', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `avl_role_privilege_rel`
--

CREATE TABLE IF NOT EXISTS `avl_role_privilege_rel` (
  `role_id` int(5) unsigned NOT NULL,
  `privilege_id` int(5) unsigned NOT NULL,
  KEY `role_privilege_index1` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_role_privilege_rel`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_role_user_rel`
--

CREATE TABLE IF NOT EXISTS `avl_role_user_rel` (
  `user_id` int(5) unsigned NOT NULL,
  `role_id` int(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKF6D4D466F7EC8E49` (`role_id`),
  KEY `FKF6D4D466A38CBE69` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_role_user_rel`
--

INSERT INTO `avl_role_user_rel` (`user_id`, `role_id`) VALUES
(1, 1000),
(2, 1001),
(3, 1001);

-- --------------------------------------------------------

--
-- Table structure for table `avl_sequence`
--

CREATE TABLE IF NOT EXISTS `avl_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `val` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_sequence`
--


--
-- Table structure for table `avl_subject_master`
--

CREATE TABLE IF NOT EXISTS `avl_subject_master` (
  `subject_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) NOT NULL,
  `subject_description` varchar(500) DEFAULT NULL,
  `university_id` int(5) unsigned NOT NULL,
  `subject_status` enum('A','D') NOT NULL DEFAULT 'A',
  `subject_sequence` int(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_id` (`subject_id`),
  KEY `subject_index` (`university_id`,`subject_status`),
  KEY `subject_master_index` (`subject_name`,`university_id`,`subject_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `avl_subject_master`
--

INSERT INTO `avl_subject_master` (`subject_id`, `subject_name`, `subject_description`, `university_id`, `subject_status`, `subject_sequence`) VALUES
(1, 'Sample Career Lab 1', 'This lab is focused on developing the skills needed to land a job and on improving potential for upward mobility in the workplace. It trains the job seeker to perform well in a variety of interview formats, as well as hones English skills useful in an interview.', 1, 'A', 1),
(2, 'Sample Career Lab 2', 'This lab focuses on improving potential for upward mobility in the workplace.', 1, 'A', 2),
(3, 'Sample English Conversation', 'The user can listen to the audio or view the text along with the audio. The user has the chance to record his or her speech and compare it to a fluent speaker.', 1, 'A', 3),
(4, 'Sample Vocabulary - Part 1', 'This module is designed to help you improve your vocabulary and further explore the English language.', 1, 'A', 4),
(5, 'Sample Vocabulary - Part 2', 'This module is designed to help you improve your vocabulary and further explore the English language.', 1, 'A', 5);

-- --------------------------------------------------------

--
-- Table structure for table `avl_sub_topic_master`
--

CREATE TABLE IF NOT EXISTS `avl_sub_topic_master` (
  `sub_topic_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `sub_topic_name` varchar(100) NOT NULL,
  `sub_topic_description` varchar(250) DEFAULT NULL,
  `topic_id` int(5) unsigned NOT NULL,
  `sub_topic_status` enum('A','D') NOT NULL DEFAULT 'A',
  `sub_topic_sequence` int(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`sub_topic_id`),
  UNIQUE KEY `sub_topic_id` (`sub_topic_id`),
  KEY `subtopic_index` (`topic_id`,`sub_topic_name`,`sub_topic_status`),
  KEY `FK15B1DB795F7F1DEF` (`topic_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_sub_topic_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_theme_master`
--

CREATE TABLE IF NOT EXISTS `avl_theme_master` (
  `theme_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `theme_name` varchar(255) NOT NULL,
  PRIMARY KEY (`theme_id`),
  UNIQUE KEY `theme_id` (`theme_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `avl_theme_master`
--

INSERT INTO `avl_theme_master` (`theme_id`, `theme_name`) VALUES
(1, 'ell');

-- --------------------------------------------------------

--
-- Table structure for table `avl_topic_master`
--

CREATE TABLE IF NOT EXISTS `avl_topic_master` (
  `topic_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `topic_name` varchar(100) NOT NULL,
  `topic_description` varchar(500) DEFAULT NULL,
  `subject_id` int(5) unsigned NOT NULL,
  `topic_status` enum('A','D') NOT NULL DEFAULT 'A',
  `topic_sequence` int(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  UNIQUE KEY `topic_id` (`topic_id`),
  KEY `topic_index1` (`subject_id`,`topic_status`),
  KEY `topic_index` (`subject_id`,`topic_name`),
  KEY `FKF8857BDA5C47864F` (`subject_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_topic_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_university_branch_master`
--

CREATE TABLE IF NOT EXISTS `avl_university_branch_master` (
  `branch_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(100) NOT NULL,
  `course_id` int(5) unsigned NOT NULL,
  PRIMARY KEY (`branch_id`),
  UNIQUE KEY `branch_id` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_university_branch_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_university_course_master`
--

CREATE TABLE IF NOT EXISTS `avl_university_course_master` (
  `course_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) NOT NULL,
  `university_id` int(5) unsigned NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_university_course_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_university_master`
--

CREATE TABLE IF NOT EXISTS `avl_university_master` (
  `university_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `university_name` varchar(100) NOT NULL,
  `university_place` varchar(100) DEFAULT NULL,
  `theme_id` int(5) unsigned NOT NULL,
  `url_of_site` varchar(255) NOT NULL,
  PRIMARY KEY (`university_id`),
  UNIQUE KEY `university_id` (`university_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `avl_university_master`
--

INSERT INTO `avl_university_master` (`university_id`, `university_name`, `university_place`, `theme_id`, `url_of_site`) VALUES
(1, 'IIT', 'Kanpur', 1, 'www.iit.ac.in');

-- --------------------------------------------------------

--
-- Table structure for table `avl_university_page_details`
--

CREATE TABLE IF NOT EXISTS `avl_university_page_details` (
  `page_id` int(5) unsigned NOT NULL,
  `page_details` mediumtext,
  KEY `page_details_index` (`page_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_university_page_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_university_page_master`
--

CREATE TABLE IF NOT EXISTS `avl_university_page_master` (
  `page_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `page_name` varchar(50) DEFAULT NULL,
  `page_order` int(2) unsigned DEFAULT NULL,
  `university_id` int(5) unsigned NOT NULL,
  `parent_page` varchar(5) NOT NULL DEFAULT '0',
  `page_status` enum('A','D') DEFAULT NULL,
  `created_by` int(11) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` int(11) unsigned DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`page_id`),
  KEY `page_master_index` (`university_id`,`page_status`),
  KEY `page_master_index1` (`page_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_university_page_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_user_details`
--

CREATE TABLE IF NOT EXISTS `avl_user_details` (
  `user_id` int(5) unsigned NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `age` varchar(10) DEFAULT NULL,
  `phone_number` bigint(20) unsigned DEFAULT NULL,
  `profession` varchar(25) DEFAULT NULL,
  `college` varchar(50) DEFAULT NULL,
  `university` varchar(50) DEFAULT NULL,
  `specialization` varchar(50) DEFAULT NULL,
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `country` varchar(25) DEFAULT NULL,
  `state` varchar(25) DEFAULT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  `news_letter_status` enum('Y','N') DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK67610E36A38CBE69` (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4;

--
-- Dumping data for table `avl_user_details`
--

INSERT INTO `avl_user_details` (`user_id`, `first_name`, `last_name`, `age`, `phone_number`, `profession`, `college`, `university`, `specialization`, `date_of_registration`, `country`, `state`, `gender`, `news_letter_status`, `id`) VALUES
(1, 'ell', NULL, '0-15', NULL, 'Student', NULL, 'IIT', NULL, '2012-03-26 17:17:11', 'India', NULL, 'F', 'N', 1),
(2, 'iit', NULL, '26-30', NULL, 'Student', NULL, 'IIT', NULL, '2012-03-26 17:20:28', 'India', NULL, 'F', NULL, 2),
(3, NULL, NULL, '0-15', NULL, 'Student', NULL, 'IIT', NULL, '2012-03-26 12:57:18', 'India', NULL, 'F', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `avl_user_login_details`
--

CREATE TABLE IF NOT EXISTS `avl_user_login_details` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(5) unsigned NOT NULL,
  `session_id` varchar(100) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_duration` int(5) unsigned DEFAULT NULL,
  `login_ip` varchar(50) NOT NULL,
  `logout_status` enum('E','L') NOT NULL,
  `session_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_login_index` (`session_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_user_login_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_user_master`
--

CREATE TABLE IF NOT EXISTS `avl_user_master` (
  `user_id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `university_id` int(5) unsigned NOT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `user_status` enum('A','D') DEFAULT NULL,
  `account_expired` char(1) NOT NULL DEFAULT '0',
  `account_locked` char(1) NOT NULL DEFAULT '0',
  `enabled` char(1) NOT NULL DEFAULT '1',
  `password_expired` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `user_index` (`user_name`),
  KEY `user_index1` (`university_id`),
  KEY `user_index2` (`email_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `avl_user_master`
--

INSERT INTO `avl_user_master` (`user_id`, `user_name`, `password`, `university_id`, `email_id`, `user_status`, `account_expired`, `account_locked`, `enabled`, `password_expired`) VALUES
(1, 'elladmin@ell.edu', '3123059c1c816471780539f6b6b738dc', 1, 'elladmin@amritapuri.amrita.edu', 'A', '0', '0', '1', '0'),
(2, 'iitadmin@ell.edu', '3123059c1c816471780539f6b6b738dc', 1, 'iitadmin@iit.co.in', 'A', '0', '0', '1', '0'),
(3, 'santhosh@amritapuri.amrita.edu', 'bc66ddb75de3ed7224f08737fe13299f', 1, 'santhosh@amritapuri.amrita.edu', 'A', '0', '0', '1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `avl_user_master_openid`
--

CREATE TABLE IF NOT EXISTS `avl_user_master_openid` (
  `avl_user_master_open_ids_id` bigint(20) DEFAULT NULL,
  `openid_id` bigint(20) DEFAULT NULL,
  KEY `FKEACEDE5696B73844` (`openid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avl_user_master_openid`
--


-- --------------------------------------------------------

--
-- Table structure for table `avl_user_subject_access_rel`
--

CREATE TABLE IF NOT EXISTS `avl_user_subject_access_rel` (
  `user_id` int(5) unsigned NOT NULL,
  `subject_id` int(5) unsigned NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subject_access_index` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `avl_user_subject_access_rel`
--

INSERT INTO `avl_user_subject_access_rel` (`user_id`, `subject_id`, `id`, `version`, `role_id`) VALUES
(2, 1, 1, 0, 1002),
(2, 3, 2, 0, 1002),
(2, 4, 3, 0, 1002),
(3, 2, 4, 0, 1004),
(3, 4, 5, 0, 1004),
(3, 5, 6, 0, 1004),
(1, 1, 7, 0, 1000),
(1, 2, 8, 0, 1000),
(1, 3, 9, 0, 1000),
(1, 4, 10, 0, 1000),
(1, 5, 11, 0, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `avl_version_comments`
--

CREATE TABLE IF NOT EXISTS `avl_version_comments` (
  `version_id` int(5) unsigned NOT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `version_id` (`version_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `avl_version_comments`
--


-- --------------------------------------------------------

--
-- Table structure for table `ell_settings`
--

CREATE TABLE IF NOT EXISTS `ell_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ell_settings`
--


-- --------------------------------------------------------

--
-- Table structure for table `openid`
--

CREATE TABLE IF NOT EXISTS `openid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `url` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `openid`
--


-- --------------------------------------------------------

--
-- Table structure for table `persistent_logins`
--

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `last_used` datetime NOT NULL,
  `token` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `persistent_logins`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `avl_content_description_version`
--
ALTER TABLE `avl_content_description_version`
  ADD CONSTRAINT `FKE76575676262EDF7` FOREIGN KEY (`content_id`) REFERENCES `avl_content_details` (`content_id`);

--
-- Constraints for table `avl_content_details`
--
ALTER TABLE `avl_content_details`
  ADD CONSTRAINT `FKBB9C87141A1BBF22` FOREIGN KEY (`content_type_id`) REFERENCES `avl_content_type_master` (`content_type_id`),
  ADD CONSTRAINT `FKBB9C87145CEB5DE9` FOREIGN KEY (`experiment_id`) REFERENCES `avl_experiment_master` (`experiment_id`);

--
-- Constraints for table `avl_experiment_master`
--
ALTER TABLE `avl_experiment_master`
  ADD CONSTRAINT `FK8972417C609BDE24` FOREIGN KEY (`experiment_type_id`) REFERENCES `avl_experiment_type_master` (`experiment_typeid`);

--
-- Constraints for table `avl_experiment_subtopic_rel`
--
ALTER TABLE `avl_experiment_subtopic_rel`
  ADD CONSTRAINT `FK3575AAE35B0D0728` FOREIGN KEY (`sub_topic_id`) REFERENCES `avl_sub_topic_master` (`sub_topic_id`),
  ADD CONSTRAINT `FK3575AAE35CEB5DE9` FOREIGN KEY (`experiment_id`) REFERENCES `avl_experiment_master` (`experiment_id`);

--
-- Constraints for table `avl_role_user_rel`
--
ALTER TABLE `avl_role_user_rel`
  ADD CONSTRAINT `FKF6D4D466A38CBE69` FOREIGN KEY (`user_id`) REFERENCES `avl_user_master` (`user_id`),
  ADD CONSTRAINT `FKF6D4D466F7EC8E49` FOREIGN KEY (`role_id`) REFERENCES `avl_role_master` (`role_id`);

--
-- Constraints for table `avl_sub_topic_master`
--
ALTER TABLE `avl_sub_topic_master`
  ADD CONSTRAINT `FK15B1DB795F7F1DEF` FOREIGN KEY (`topic_id`) REFERENCES `avl_topic_master` (`topic_id`);

--
-- Constraints for table `avl_topic_master`
--
ALTER TABLE `avl_topic_master`
  ADD CONSTRAINT `FKF8857BDA5C47864F` FOREIGN KEY (`subject_id`) REFERENCES `avl_subject_master` (`subject_id`);

--
-- Constraints for table `avl_user_master_openid`
--
ALTER TABLE `avl_user_master_openid`
  ADD CONSTRAINT `FKEACEDE5696B73844` FOREIGN KEY (`openid_id`) REFERENCES `openid` (`id`);
