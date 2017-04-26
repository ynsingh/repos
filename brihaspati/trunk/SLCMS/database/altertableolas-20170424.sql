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

