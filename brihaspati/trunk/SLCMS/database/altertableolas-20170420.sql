-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 20, 2017 at 05:47 PM
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
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(255) NOT NULL,
  `cat_code` varchar(255) NOT NULL,
  `cat_short` varchar(255) NOT NULL,
  `cat_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `designation`
--

CREATE TABLE `designation` (
  `desig_id` int(11) NOT NULL,
  `desig_name` varchar(255) NOT NULL,
  `desig_code` varchar(255) NOT NULL,
  `desig_short` varchar(255) NOT NULL,
  `desig_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `student_education`
--

CREATE TABLE `student_education` (
  `sedu_id` int(11) NOT NULL,
  `sedu_smid` int(11) NOT NULL,
  `sedu_class` varchar(255) NOT NULL,
  `sedu_institution` varchar(255) NOT NULL,
  `sedu_board` varchar(255) NOT NULL,
  `sedu_subject` varchar(255) NOT NULL,
  `sedu_passingyear` varchar(255) NOT NULL,
  `sedu_resultstatus` varchar(255) NOT NULL,
  `sedu_maxmarks` varchar(255) NOT NULL,
  `sedu_marksobtained` varchar(255) NOT NULL,
  `sedu_percentage` varchar(255) NOT NULL,
  `sedu_ext1` varchar(255) DEFAULT NULL,
  `sedu_ext2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_employment`
--

CREATE TABLE `student_employment` (
  `semp_id` int(11) NOT NULL,
  `semp_smid` int(11) NOT NULL,
  `semp_officename` varchar(255) NOT NULL,
  `semp_post` varchar(255) NOT NULL,
  `semp_pay` varchar(255) NOT NULL,
  `semp_grade` varchar(255) NOT NULL,
  `semp_appoinmentnature` varchar(255) NOT NULL,
  `semp_doj` varchar(255) NOT NULL,
  `semp_dol` varchar(255) NOT NULL,
  `semp_remarks` varchar(255) NOT NULL,
  `semp_experience` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_entrance_exam`
--

CREATE TABLE `student_entrance_exam` (
  `seex_id` int(11) NOT NULL,
  `seex_smid` int(11) NOT NULL,
  `seex_examname` varchar(255) NOT NULL,
  `seex_rollno` varchar(255) NOT NULL,
  `seex_rank` int(11) NOT NULL,
  `seex_score` int(11) NOT NULL,
  `seex_state` varchar(255) NOT NULL,
  `seex_subject` varchar(255) NOT NULL,
  `seex_passingyear` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_master`
--

CREATE TABLE `student_master` (
  `sm_id` int(11) NOT NULL,
  `sm_userid` int(11) NOT NULL,
  `sm_applicationno` varchar(255) DEFAULT NULL,
  `sm_meritno` varchar(255) DEFAULT NULL,
  `sm_testname` varchar(255) DEFAULT NULL,
  `sm_universitycode` varchar(255) DEFAULT NULL,
  `sm_sccode` varchar(255) DEFAULT NULL,
  `sm_rollno` varchar(255) DEFAULT NULL,
  `sm_enrollmentno` varchar(255) DEFAULT NULL,
  `sm_fname` varchar(255) DEFAULT NULL,
  `sm_mname` varchar(255) DEFAULT NULL,
  `sm_lname` varchar(255) DEFAULT NULL,
  `sm_nameinhindi` varchar(255) DEFAULT NULL,
  `sm_dob` varchar(255) DEFAULT NULL,
  `sm_pob` varchar(255) DEFAULT NULL,
  `sm_email` varchar(255) DEFAULT NULL,
  `sm_secemail` varchar(255) DEFAULT NULL,
  `sm_category` varchar(255) DEFAULT NULL,
  `sm_caste` varchar(255) DEFAULT NULL,
  `sm_gender` varchar(255) DEFAULT NULL,
  `sm_mstatus` varchar(255) DEFAULT NULL,
  `sm_bloodgroup` varchar(255) DEFAULT NULL,
  `sm_nationality` varchar(255) DEFAULT NULL,
  `sm_minority` varchar(255) DEFAULT NULL,
  `sm_reservationtype` varchar(255) DEFAULT NULL,
  `sm_phyhandicaped` varchar(255) DEFAULT NULL,
  `sm_uid` varchar(255) DEFAULT NULL,
  `sm_panno` varchar(255) DEFAULT NULL,
  `sm_passportno` varchar(255) DEFAULT NULL,
  `sm_religion` varchar(255) DEFAULT NULL,
  `sm_photo` varchar(255) DEFAULT NULL,
  `sm_signature` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_parent`
--

CREATE TABLE `student_parent` (
  `spar_id` int(11) NOT NULL,
  `spar_smid` int(11) NOT NULL,
  `spar_fatherfname` varchar(255) DEFAULT NULL,
  `spar_fathermname` varchar(255) DEFAULT NULL,
  `spar_fatherlname` varchar(255) DEFAULT NULL,
  `spar_fatheremail` varchar(255) DEFAULT NULL,
  `spar_fatherphoneno` varchar(255) DEFAULT NULL,
  `spar_fatheroccupation` varchar(255) DEFAULT NULL,
  `spar_motherfname` varchar(255) DEFAULT NULL,
  `spar_mothermname` varchar(255) DEFAULT NULL,
  `spar_motherlname` varchar(255) DEFAULT NULL,
  `spar_motheremail` varchar(255) DEFAULT NULL,
  `spar_motherphoneno` varchar(255) DEFAULT NULL,
  `spar_motheroccupation` varchar(255) DEFAULT NULL,
  `spar_paddress` varchar(255) DEFAULT NULL,
  `spar_pcity` varchar(255) DEFAULT NULL,
  `spar_pdistrict` varchar(255) DEFAULT NULL,
  `spar_pstate` varchar(255) DEFAULT NULL,
  `spar_pcountry` varchar(255) DEFAULT NULL,
  `spar_ppincode` varchar(255) DEFAULT NULL,
  `spar_caddress` varchar(255) DEFAULT NULL,
  `spar_ccity` varchar(255) DEFAULT NULL,
  `spar_cdistrict` varchar(255) DEFAULT NULL,
  `spar_cstate` varchar(255) DEFAULT NULL,
  `spar_ccountry` varchar(255) DEFAULT NULL,
  `spar_cpincode` varchar(255) DEFAULT NULL,
  `spar_garfname` varchar(255) DEFAULT NULL,
  `spar_garmname` varchar(255) DEFAULT NULL,
  `spar_garlname` varchar(255) DEFAULT NULL,
  `spar_garemail` varchar(255) DEFAULT NULL,
  `spar_garphoneno` varchar(255) DEFAULT NULL,
  `spar_garoccupation` varchar(255) DEFAULT NULL,
  `spar_garaddress` varchar(255) DEFAULT NULL,
  `spar_garcity` varchar(255) DEFAULT NULL,
  `spar_gardistrict` varchar(255) DEFAULT NULL,
  `spar_garstate` varchar(255) DEFAULT NULL,
  `spar_garcountry` varchar(255) DEFAULT NULL,
  `spar_garpincode` varchar(255) DEFAULT NULL,
  `spar_fathernamehindi` varchar(255) DEFAULT NULL,
  `spar_mothernamehindi` varchar(255) DEFAULT NULL,
  `spar_garnamehindi` varchar(255) DEFAULT NULL,
  `spar_ext` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `designation`
--
ALTER TABLE `designation`
  ADD PRIMARY KEY (`desig_id`);

--
-- Indexes for table `student_education`
--
ALTER TABLE `student_education`
  ADD PRIMARY KEY (`sedu_id`);

--
-- Indexes for table `student_employment`
--
ALTER TABLE `student_employment`
  ADD PRIMARY KEY (`semp_id`);

--
-- Indexes for table `student_entrance_exam`
--
ALTER TABLE `student_entrance_exam`
  ADD PRIMARY KEY (`seex_id`);

--
-- Indexes for table `student_master`
--
ALTER TABLE `student_master`
  ADD PRIMARY KEY (`sm_id`);

--
-- Indexes for table `student_parent`
--
ALTER TABLE `student_parent`
  ADD PRIMARY KEY (`spar_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student_education`
--
ALTER TABLE `student_education`
  MODIFY `sedu_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_employment`
--
ALTER TABLE `student_employment`
  MODIFY `semp_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_entrance_exam`
--
ALTER TABLE `student_entrance_exam`
  MODIFY `seex_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_master`
--
ALTER TABLE `student_master`
  MODIFY `sm_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_parent`
--
ALTER TABLE `student_parent`
  MODIFY `spar_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
