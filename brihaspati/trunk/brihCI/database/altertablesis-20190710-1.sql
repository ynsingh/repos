--
-- Table structure for table `uploaddocuments`
--

CREATE TABLE `uploaddocuments` (
  `ud_id` int(11) NOT NULL,
  `ud_proflname` varchar(255) NOT NULL,
  `ud_subproflnme` varchar(255) NOT NULL,
  `ud_degreename` varchar(255) DEFAULT NULL,
  `ud_pfno` varchar(255) NOT NULL,
  `ud_filename` varchar(255) NOT NULL,
  `ud_filelocation` varchar(255) DEFAULT NULL,
  `ud_creator` varchar(255) NOT NULL,
  `ud_creationdate` date NOT NULL,
  `ud_modifier` varchar(255) NOT NULL,
  `ud_modifydate` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `uploaddocuments`
--
ALTER TABLE `uploaddocuments`
  ADD PRIMARY KEY (`ud_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `uploaddocuments`
--
ALTER TABLE `uploaddocuments`
  MODIFY `ud_id` int(11) NOT NULL AUTO_INCREMENT;
