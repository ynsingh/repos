
--
-- Table structure for table `nodues`
--

CREATE TABLE `nodues` (
  `id` int(11) NOT NULL,
  `sacunitno` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `creatorid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `nodues`
--
ALTER TABLE `nodues`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `nodues`
--
ALTER TABLE `nodues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
