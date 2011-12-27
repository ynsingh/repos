-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 11, 2011 at 03:43 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `erp_gis`
--

-- --------------------------------------------------------

--
-- Table structure for table `affiliations`
--

CREATE TABLE IF NOT EXISTS `affiliations` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Affiliation ID',
  `name` text NOT NULL COMMENT 'Text used by the affiliation system',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `degrees`
--

CREATE TABLE IF NOT EXISTS `degrees` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Course ID',
  `name` tinytext COMMENT 'Course Name (btech etc)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `disciplines`
--

CREATE TABLE IF NOT EXISTS `disciplines` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Discipline ID',
  `name` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `districts`
--

CREATE TABLE IF NOT EXISTS `districts` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'District ID',
  `name` tinytext COMMENT 'Name of the disctrict',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `institutes`
--

CREATE TABLE IF NOT EXISTS `institutes` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Institute ID',
  `registration` int(1) DEFAULT '0' COMMENT 'Registration Status',
  `verification` int(1) DEFAULT '0',
  `user_id` int(10) NOT NULL COMMENT 'The user id of the contact person for institute',
  PRIMARY KEY (`id`),
  KEY `FK_user_id_in_institute` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `institute_courses`
--

CREATE TABLE IF NOT EXISTS `institute_courses` (
  `institute_id` int(10) NOT NULL COMMENT 'The institute ID',
  `degree_id` int(10) NOT NULL COMMENT 'Degree ID',
  `discipline_id` int(10) NOT NULL COMMENT 'Discipline ID',
  `seats` int(5) NOT NULL COMMENT 'No. Of seats for this course',
  PRIMARY KEY (`institute_id`),
  KEY `FK_discpline_id` (`discipline_id`),
  KEY `FK_degree_id` (`degree_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `institute_data`
--

CREATE TABLE IF NOT EXISTS `institute_data` (
  `institute_id` int(10) NOT NULL COMMENT 'Institute Id',
  `name` tinytext NOT NULL COMMENT 'Name of the institute',
  `address` text COMMENT 'Address of the institute',
  `district_id` int(10) NOT NULL COMMENT 'District ID of the institute''s location',
  `state_id` int(10) NOT NULL COMMENT 'State ID of the insti',
  `established` int(10) NOT NULL COMMENT 'Date in unix epoch',
  `phone` tinytext NOT NULL COMMENT 'Contact Phone number',
  `url` tinytext COMMENT 'Institute''s home page',
  `fax` tinytext COMMENT 'Fax number',
  `affiliation` int(10) NOT NULL COMMENT 'Affiliation ID',
  `latitude` float NOT NULL COMMENT 'Latitude Of the place',
  `longitude` float NOT NULL COMMENT 'Longitude',
  PRIMARY KEY (`institute_id`),
  KEY `FK_state_id` (`state_id`),
  KEY `FK_district` (`district_id`),
  KEY `FK_affiliation` (`affiliation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

CREATE TABLE IF NOT EXISTS `states` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'State ID',
  `name` tinytext COMMENT 'State name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL DEFAULT '0',
  `name` text,
  `email` tinytext,
  `userid` tinytext,
  `password` tinytext COMMENT 'sha1 hash',
  `role` enum('USER','ADMIN','VIEWER') DEFAULT NULL COMMENT 'The role / privilege of the user',
  `salt` tinytext NOT NULL COMMENT 'Salt used for the hashing',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `institutes`
--
ALTER TABLE `institutes`
  ADD CONSTRAINT `FK_user_id_in_institute` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `institute_courses`
--
ALTER TABLE `institute_courses`
  ADD CONSTRAINT `FK_degree_id` FOREIGN KEY (`degree_id`) REFERENCES `degrees` (`id`),
  ADD CONSTRAINT `FK_discpline_id` FOREIGN KEY (`discipline_id`) REFERENCES `disciplines` (`id`),
  ADD CONSTRAINT `institute_courses_ibfk_1` FOREIGN KEY (`institute_id`) REFERENCES `institutes` (`id`);

--
-- Constraints for table `institute_data`
--
ALTER TABLE `institute_data`
  ADD CONSTRAINT `FK_affiliation` FOREIGN KEY (`affiliation`) REFERENCES `affiliations` (`id`),
  ADD CONSTRAINT `FK_district` FOREIGN KEY (`district_id`) REFERENCES `districts` (`id`),
  ADD CONSTRAINT `FK_state_id` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`),
  ADD CONSTRAINT `institute_data_ibfk_1` FOREIGN KEY (`institute_id`) REFERENCES `institutes` (`id`);
