 phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 24, 2017 at 07:09 PM
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
-- Table structure for table `country`
--



ALTER TABLE `country` CHANGE `contry_id` `country_id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `country` CHANGE `county_name` `country_name` VARCHAR(255);

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`country_id`, `country_name`, `country_code`, `country_short`, `country_desc`) VALUES
(1, 'India', '91', 'IN', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
