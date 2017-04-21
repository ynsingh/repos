-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 21, 2017 at 05:41 PM
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
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `city_code` varchar(255) NOT NULL,
  `city_short` varchar(255) NOT NULL,
  `city_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `contry_id` int(11) NOT NULL,
  `county_name` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `country_short` varchar(255) DEFAULT NULL,
  `country_desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Dumping data for table `org_profile`
--

INSERT INTO `org_profile` (`org_id`, `org_code`, `org_name`, `org_nickname`, `org_type`, `org_tagline`, `org_address1`, `org_address2`, `org_city`, `org_district`, `org_state`, `org_countrycode`, `org_pincode`, `org_phone`, `org_fax`, `org_email`, `org_web`, `org_institutedomain`, `org_tanno`, `org_logo`, `org_affiliation`, `org_status`, `org_reg_date`, `org_close_date`, `org_adminfn`, `org_adminln`, `org_admindesig`, `creatorid`, `create_date`, `modifierid`, `modify_date`) VALUES
(1, 'CU001', 'Indira Gandhi National Tribal University', 'IGNTU', 'Central Govt', '', 'Lal Pur, Amarkantak', '', '', '', 'Madhya Pradesh', '91', '484887', '07629 269 701', '07629 269 701', 'admission@igntu.ac.in', 'www.igntu.ac.in', 'igntu.ac.in', '', '', 'MHRD', 'Active', 'July 2008', '', '', '', '', '1', '2017-04-21', '', '');


-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `state_id` int(11) NOT NULL,
  `state_name` varchar(255) NOT NULL,
  `state_code` varchar(255) NOT NULL,
  `state_short` varchar(255) NOT NULL,
  `state_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `student_fees`
--

CREATE TABLE `student_fees` (
  `sfee_id` int(11) NOT NULL,
  `sfee_smid` int(11) NOT NULL,
  `sfee_spid` int(11) NOT NULL,
  `sfee_feeamount` varchar(255) NOT NULL,
  `sfee_duedate` varchar(255) NOT NULL,
  `sfee_paymentmethod` varchar(255) NOT NULL,
  `sfee_depositdate` varchar(255) NOT NULL,
  `sfee_referenceno` varchar(255) NOT NULL,
  `sfee_paymentgateway` varchar(255) NOT NULL,
  `sfee_paymentstatus` varchar(255) NOT NULL,
  `sfee_feespaidstatus` varchar(255) NOT NULL,
  `sfee_remarks` varchar(255) NOT NULL,
  `sfee_ext1` varchar(255) NOT NULL,
  `sfee_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `student_program`
--

CREATE TABLE `student_program` (
  `sp_id` int(11) NOT NULL,
  `sp_smid` int(11) NOT NULL,
  `sp_sccode` varchar(255) DEFAULT NULL,
  `sp_pcategory` varchar(255) DEFAULT NULL,
  `sp_programid` varchar(255) DEFAULT NULL,
  `sp_branch` varchar(255) DEFAULT NULL,
  `sp_acadyear` varchar(255) DEFAULT NULL,
  `sp_semester` varchar(255) DEFAULT NULL,
  `sp_sub1` varchar(255) DEFAULT NULL,
  `sp_sub2` varchar(255) DEFAULT NULL,
  `sp_sub3` varchar(255) DEFAULT NULL,
  `sp_sub4` varchar(255) DEFAULT NULL,
  `sp_sub5` varchar(255) DEFAULT NULL,
  `sp_sub6` varchar(255) DEFAULT NULL,
  `sp_sub7` varchar(255) DEFAULT NULL,
  `sp_sub8` varchar(255) DEFAULT NULL,
  `sp_sub9` varchar(255) DEFAULT NULL,
  `sp_sub10` varchar(255) DEFAULT NULL,
  `sp_ext1` varchar(255) DEFAULT NULL,
  `sp_ext2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Indexes for dumped tables
--


--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`contry_id`);


--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`state_id`);


--
-- Indexes for table `student_fees`
--
ALTER TABLE `student_fees`
  ADD PRIMARY KEY (`sfee_id`),
  ADD UNIQUE KEY `sfee_smid` (`sfee_smid`,`sfee_spid`);


--
-- Indexes for table `student_program`
--
ALTER TABLE `student_program`
  ADD PRIMARY KEY (`sp_id`);


--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `contry_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `designation`
--
ALTER TABLE `designation`
  MODIFY `desig_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `email_setting`
--
ALTER TABLE `email_setting`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `state_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student_fees`
--
ALTER TABLE `student_fees`
  MODIFY `sfee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student_program`
--
ALTER TABLE `student_program`
  MODIFY `sp_id` int(11) NOT NULL AUTO_INCREMENT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
