-- --------------------------------------------------------
--
-- Table structure for table `staff_promotionals_details`
--

CREATE TABLE `staff_promotionals_details` (
  `spd_id` int(11) NOT NULL,
  `spd_empid` int(11) NOT NULL,
  `spd_wtype` varchar(255) NOT NULL,
  `spd_paycom` varchar(11) DEFAULT NULL,
  `spd_agp` varchar(255) DEFAULT NULL,
  `spd_agpdate` date DEFAULT NULL,
  `spd_level` varchar(255) DEFAULT NULL,
  `spd_leveldate` date DEFAULT NULL,
  `spd_group` varchar(255) DEFAULT NULL,
  `spd_designation` varchar(255) DEFAULT NULL,
  `spd_dojinpost` date DEFAULT NULL,
  `spd_selgradedate` date DEFAULT NULL,
  `spd_specialgrddate` date DEFAULT NULL,
  `spd_creatordate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spd_creatorid` varchar(255) NOT NULL,
  `spd_modifierid` varchar(255) NOT NULL,
  `spd_modifydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details`
  ADD PRIMARY KEY (`spd_id`);

--
-- AUTO_INCREMENT for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details`
  MODIFY `spd_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--
-- Table structure for table `staff_promotionals_details`
--

CREATE TABLE `staff_promotionals_details_archive` (
  `spda_id` int(11) NOT NULL,
  `spda_spdid` int(11) NOT NULL,
  `spda_empid` int(11) NOT NULL,
  `spda_wtype` varchar(255) NOT NULL,
  `spda_paycom` varchar(11) DEFAULT NULL,
  `spda_agp` varchar(255) DEFAULT NULL,
  `spda_agpdate` date DEFAULT NULL,
  `spda_level` varchar(255) DEFAULT NULL,
  `spda_leveldate` date DEFAULT NULL,
  `spda_group` varchar(255) DEFAULT NULL,
  `spda_designation` varchar(255) DEFAULT NULL,
  `spda_dojinpost` date DEFAULT NULL,
  `spda_selgradedate` date DEFAULT NULL,
  `spda_specialgrddate` date DEFAULT NULL,
  `spda_creatordate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spda_creatorid` varchar(255) NOT NULL,
  `spda_modifierid` varchar(255) NOT NULL,
  `spda_modifydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details_archive`
  ADD PRIMARY KEY (`spda_id`);

--
-- AUTO_INCREMENT for table `staff_promotionals_details`
--
ALTER TABLE `staff_promotionals_details_archive`
  MODIFY `spda_id` int(11) NOT NULL AUTO_INCREMENT;
