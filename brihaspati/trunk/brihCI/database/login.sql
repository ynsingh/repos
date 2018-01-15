-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 19, 2017 at 07:08 PM
-- Server version: 5.6.27
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `login`
--

-- --------------------------------------------------------

--
-- Table structure for table `aggregateaccounts`
--

CREATE TABLE `aggregateaccounts` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `accounts` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL,
  `priority` INT(11) default NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `authority_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`id`, `code`,`name`, `nickname`, `authority_email`) VALUES
(1, 'D','Director', 'Dir', 'dir@iitk.ac.in'),
(2, 'FO','Finance Officer', 'FO', 'fo@iitk.ac.in'),
(3, 'DRA','Deputy Registar Account', 'DRAcc', 'dracc@iitk.ac.in'),
(4, 'ARA','Assistant Registar Account', 'ARAcc', 'aracc@iitk.ac.in'),
(5, 'ACC','Accountant', 'acc', 'acc@iitk.ac.in');

-- --------------------------------------------------------

--
-- Table structure for table `authority_archive`
--

CREATE TABLE `authority_archive` (
  `id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `map_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `till_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `authority_type` varchar(10) NOT NULL,
  `creatorid` int(11) NOT NULL,
  `createdate` DATETIME  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `authority_map`
--

CREATE TABLE `authority_map` (
  `id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `map_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `till_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `authority_type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bgasAccData`
--

CREATE TABLE `bgasAccData` (
  `id` int(11) NOT NULL,
  `organization` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `databasename` varchar(255) NOT NULL,
  `fyear` varchar(14) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `dbpass` varchar(255) NOT NULL,
  `hostname` varchar(255) NOT NULL DEFAULT 'localhost',
  `port` int(9) NOT NULL DEFAULT '3306',
  `dbtype` varchar(255) NOT NULL DEFAULT 'mysql',
  `dblable` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `bgasuserrolegroup`
--

CREATE TABLE `bgasuserrolegroup` (
  `id` int(20) NOT NULL,
  `userid` int(20) NOT NULL,
  `role` varchar(200) NOT NULL,
  `accounts` varchar(200) NOT NULL,
  `aggtype` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bgasuserrolegroup`
--

insert into bgasuserrolegroup values (1,1,'administrator','*','');
insert into bgasuserrolegroup values (2,2,'guest','*','');

--
-- Table structure for table `edrpuser`
--

CREATE TABLE `edrpuser` (
  `id` int(20) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `componentreg` varchar(200) NOT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `status` varchar(200) NOT NULL,
  `category_type` varchar(200) NOT NULL,
  `verification_code` varchar(32) NOT NULL,
  `is_verified` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `edrpuser`
--

INSERT INTO `edrpuser` (`id`, `username`, `password`, `email`, `componentreg`, `mobile`, `status`, `category_type`, `verification_code`, `is_verified`) VALUES
(1, 'admin', md5('admin'), 'ynsingh@iitk.ac.in', '*', NULL, '1', '', '', 1),
(2, 'guest', md5('guest'), 'ynsingh@iitk.ac.in', '*', NULL, '1', '', '', 1),
(3, 'entranceadmin', md5('admin'), 'ynsingh@iitk.ac.in', '*', NULL, '1', '', '', 1),
(4, 'admissionadmin', md5('admin'), 'ynsingh@iitk.ac.in', '*', NULL, '1', '', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `emailSetting`
--

CREATE TABLE `emailSetting` (
  `id` int(11) NOT NULL,
  `email_protocol` varchar(9) NOT NULL,
  `email_host` varchar(255) NOT NULL,
  `email_port` int(5) NOT NULL,
  `email_username` varchar(255) NOT NULL,
  `email_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emailSetting`
--

INSERT INTO emailSetting VALUES (1,'','','','','');

--
-- Table structure for table `forgotPass`
--

CREATE TABLE `forgotPass` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `rkey` varchar(255) NOT NULL,
  `passdate` datetime DEFAULT NULL,
  `expdate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userlaststatus`
--

CREATE TABLE `userlaststatus` (
  `id` int(20) NOT NULL,
  `userid` int(20) NOT NULL,
  `lastusedlang` varchar(255) DEFAULT NULL,
  `lastlogincomponent` varchar(255) DEFAULT NULL,
  `lastloginstatus` varchar(255) DEFAULT NULL,
  `lastvisitedcomponent` varchar(255) DEFAULT NULL,
  `lastlogindate` datetime DEFAULT NULL,
  `lastpasswordchangedate` datetime DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userlaststatus`
--

insert into userlaststatus values (1,1,'English','',1,'','','',1);
insert into userlaststatus values (2,2,'English','',1,'','','',1);
insert into userlaststatus values (3,3,'English','',1,'','','',1);
insert into userlaststatus values (4,4,'English','',1,'','','',1);


--
-- Table structure for table `userprofile`
--

CREATE TABLE `userprofile` (
  `id` int(20) NOT NULL,
  `userid` int(20) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `secmail` varchar(255) DEFAULT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `lang` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userprofile`
--

insert into userprofile values (1,1,'Y.N.','Singh','','','','English',1);
insert into userprofile values (2,2,'Guest','','','','','English',1);
insert into userprofile values (3,3,'Y.N.','Singh','','','','English',1);
insert into userprofile values (4,4,'Y.N.','Singh','','','','English',1);



--
-- Indexes for table `aggregateaccounts`
--
ALTER TABLE `aggregateaccounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `authority_archive`
--
ALTER TABLE `authority_archive`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `authority_map`
--
ALTER TABLE `authority_map`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bgasAccData`
--
ALTER TABLE `bgasAccData`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `databasename` (`databasename`),
  ADD UNIQUE KEY `dblable` (`dblable`);

--
-- Indexes for table `bgasuserrolegroup`
--
ALTER TABLE `bgasuserrolegroup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userid` (`userid`,`role`,`accounts`);

--
-- Indexes for table `edrpuser`
--
ALTER TABLE `edrpuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `emailSetting`
--
ALTER TABLE `emailSetting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `forgotPass`
--
ALTER TABLE `forgotPass`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userlaststatus`
--
ALTER TABLE `userlaststatus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userid` (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aggregateaccounts`
--
ALTER TABLE `aggregateaccounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `authority_archive`
--
ALTER TABLE `authority_archive`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `authority_map`
--
ALTER TABLE `authority_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `bgasAccData`
--
ALTER TABLE `bgasAccData`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `bgasuserrolegroup`
--
ALTER TABLE `bgasuserrolegroup`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `edrpuser`
--
ALTER TABLE `edrpuser`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `emailSetting`
--
ALTER TABLE `emailSetting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `forgotPass`
--
ALTER TABLE `forgotPass`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `userlaststatus`
--
ALTER TABLE `userlaststatus`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
