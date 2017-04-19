-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 19, 2017 at 07:07 PM
-- Server version: 5.6.27
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `olas`
--

-- --------------------------------------------------------

--
-- Table structure for table `Department`
--

CREATE TABLE `Department` (
  `dept_id` int(11) NOT NULL,
  `dept_name` varchar(255) NOT NULL,
  `dept_code` varchar(255) NOT NULL,
  `dept_short` varchar(255) NOT NULL,
  `dept_description` varchar(255) NOT NULL,
  `dept_schoolname` varchar(255) NOT NULL,
  `dept_schoolcode` varchar(255) NOT NULL,
  `dept_sccode` varchar(255) NOT NULL,
  `dept_orgcode` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `email_setting`
--

CREATE TABLE `email_setting` (
  `id` int(10) NOT NULL,
  `emailprotocol` varchar(50) NOT NULL,
  `emailhost` varchar(50) NOT NULL,
  `emailport` int(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sendername` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` varchar(50) NOT NULL,
  `modifierid` varchar(255) NOT NULL,
  `modifidate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fees_master`
--

CREATE TABLE `fees_master` (
  `fm_id` int(11) NOT NULL,
  `fm_head` varchar(255) NOT NULL,
  `fm_programid` int(11) NOT NULL,
  `fm_acadyear` varchar(50) NOT NULL,
  `fm_semester` varchar(50) NOT NULL,
  `fm_gender` varchar(50) NOT NULL,
  `fm_category` varchar(100) NOT NULL,
  `fm_amount` int(11) NOT NULL,
  `fm_installment` varchar(55) NOT NULL,
  `fm_frmdate` varchar(55) NOT NULL,
  `fm_todate` varchar(255) NOT NULL,
  `fm_desc` varchar(255) NOT NULL,
  `fm_ext1` varchar(255) NOT NULL,
  `fm_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `org_profile`
--

CREATE TABLE `org_profile` (
  `org_id` int(11) NOT NULL,
  `org_code` varchar(255) NOT NULL,
  `org_name` varchar(255) NOT NULL,
  `org_nickname` varchar(255) NOT NULL,
  `org_type` varchar(255) NOT NULL,
  `org_tagline` varchar(255) NOT NULL,
  `org_address1` varchar(255) NOT NULL,
  `org_address2` varchar(255) NOT NULL,
  `org_city` varchar(255) NOT NULL,
  `org_district` varchar(255) NOT NULL,
  `org_state` varchar(255) NOT NULL,
  `org_countrycode` varchar(255) NOT NULL,
  `org_pincode` varchar(255) NOT NULL,
  `org_phone` varchar(255) NOT NULL,
  `org_fax` varchar(255) NOT NULL,
  `org_email` varchar(255) NOT NULL,
  `org_web` varchar(255) NOT NULL,
  `org_institutedomain` varchar(255) NOT NULL,
  `org_tanno` varchar(255) NOT NULL,
  `org_logo` varchar(255) NOT NULL,
  `org_affiliation` varchar(255) NOT NULL,
  `org_status` varchar(255) NOT NULL,
  `org_reg_date` varchar(255) NOT NULL,
  `org_close_date` varchar(255) NOT NULL,
  `org_adminfn` varchar(255) NOT NULL,
  `org_adminln` varchar(255) NOT NULL,
  `org_admindesig` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `create_date` varchar(255) NOT NULL,
  `modifierid` varchar(255) NOT NULL,
  `modify_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

CREATE TABLE `program` (
  `prg_id` int(11) NOT NULL,
  `prg_category` varchar(255) NOT NULL,
  `prg_name` varchar(255) NOT NULL,
  `prg_branch` varchar(255) NOT NULL,
  `prg_code` varchar(255) NOT NULL,
  `prg_short` varchar(255) NOT NULL,
  `prg_desc` varchar(255) NOT NULL,
  `prg_mintime` varchar(255) NOT NULL,
  `prg_maxtime` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `seat_program_studycenter`
--

CREATE TABLE `seat_program_studycenter` (
  `spsc_id` int(11) NOT NULL,
  `spsc_prg_id` int(11) NOT NULL,
  `spsc_sc_code` varchar(255) NOT NULL,
  `spsc_gender` varchar(255) NOT NULL,
  `spsc_totalseat` varchar(255) NOT NULL,
  `spsc_ext1` varchar(255) NOT NULL,
  `spsc_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `seat_reservation`
--

CREATE TABLE `seat_reservation` (
  `id` int(11) NOT NULL,
  `org_code` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  `percentage` varchar(10) NOT NULL,
  `noofseat` int(4) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `study_center`
--

CREATE TABLE `study_center` (
  `sc_id` int(11) NOT NULL,
  `sc_code` varchar(255) NOT NULL,
  `org_code` varchar(255) NOT NULL,
  `sc_name` varchar(255) NOT NULL,
  `sc_nickname` varchar(255) NOT NULL,
  `sc_address` varchar(255) NOT NULL,
  `sc_city` varchar(255) NOT NULL,
  `sc_district` varchar(255) NOT NULL,
  `sc_state` varchar(255) NOT NULL,
  `sc_country` varchar(255) NOT NULL,
  `sc_pincode` varchar(10) NOT NULL,
  `sc_phone` varchar(255) NOT NULL,
  `sc_fax` varchar(255) NOT NULL,
  `sc_status` varchar(255) NOT NULL,
  `sc_startdate` varchar(25) NOT NULL,
  `sc_closedate` varchar(25) NOT NULL,
  `sc_website` varchar(255) NOT NULL,
  `sc_incharge` varchar(255) NOT NULL,
  `sc_mobile` varchar(25) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `sub_id` int(11) NOT NULL,
  `sub_name` varchar(255) NOT NULL,
  `sub_code` varchar(255) NOT NULL,
  `sub_short` varchar(255) NOT NULL,
  `sub_desc` varchar(255) NOT NULL,
  `sub_ext1` varchar(255) NOT NULL,
  `sub_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject_paper`
--

CREATE TABLE `subject_paper` (
  `subp_id` int(11) NOT NULL,
  `subp_sub_id` int(11) NOT NULL,
  `subp_name` varchar(255) NOT NULL,
  `subp_code` varchar(100) NOT NULL,
  `subp_short` varchar(255) NOT NULL,
  `subp_desp` varchar(255) NOT NULL,
  `subp_ext1` varchar(255) NOT NULL,
  `subp_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_role_type`
--

CREATE TABLE `user_role_type` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Department`
--
ALTER TABLE `Department`
  ADD PRIMARY KEY (`dept_id`),
  ADD UNIQUE KEY `dept_code` (`dept_code`,`dept_schoolcode`);

--
-- Indexes for table `email_setting`
--
ALTER TABLE `email_setting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fees_master`
--
ALTER TABLE `fees_master`
  ADD PRIMARY KEY (`fm_id`);

--
-- Indexes for table `org_profile`
--
ALTER TABLE `org_profile`
  ADD PRIMARY KEY (`org_id`),
  ADD UNIQUE KEY `org_code` (`org_code`);

--
-- Indexes for table `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`prg_id`),
  ADD UNIQUE KEY `prg_category` (`prg_category`,`prg_name`,`prg_branch`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `seat_program_studycenter`
--
ALTER TABLE `seat_program_studycenter`
  ADD PRIMARY KEY (`spsc_id`);

--
-- Indexes for table `seat_reservation`
--
ALTER TABLE `seat_reservation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `org_code` (`org_code`,`category_id`,`percentage`);

--
-- Indexes for table `study_center`
--
ALTER TABLE `study_center`
  ADD PRIMARY KEY (`sc_id`),
  ADD UNIQUE KEY `sc_code` (`sc_code`,`org_code`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sub_id`),
  ADD UNIQUE KEY `sub_name` (`sub_name`),
  ADD UNIQUE KEY `sub_code` (`sub_code`),
  ADD UNIQUE KEY `sub_name_2` (`sub_name`,`sub_code`);

--
-- Indexes for table `subject_paper`
--
ALTER TABLE `subject_paper`
  ADD PRIMARY KEY (`subp_id`);

--
-- Indexes for table `user_role_type`
--
ALTER TABLE `user_role_type`
  ADD UNIQUE KEY `userid` (`userid`,`roleid`,`usertype`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Department`
--
ALTER TABLE `Department`
  MODIFY `dept_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `fees_master`
--
ALTER TABLE `fees_master`
  MODIFY `fm_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `org_profile`
--
ALTER TABLE `org_profile`
  MODIFY `org_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `program`
--
ALTER TABLE `program`
  MODIFY `prg_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `seat_program_studycenter`
--
ALTER TABLE `seat_program_studycenter`
  MODIFY `spsc_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `seat_reservation`
--
ALTER TABLE `seat_reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `study_center`
--
ALTER TABLE `study_center`
  MODIFY `sc_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `sub_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subject_paper`
--
ALTER TABLE `subject_paper`
  MODIFY `subp_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
