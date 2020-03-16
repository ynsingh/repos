--
-- Table structure for table `admissionstudent_contactus`
--

CREATE TABLE `admissionstudent_contactus` (
  `ascu_id` int(11) NOT NULL,
  `ascu_name` varchar(255) NOT NULL,
  `ascu_emailid` varchar(255) DEFAULT NULL,
  `ascu_phoneno` varchar(20) NOT NULL,
  `ascu_regards` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admissionstudent_contactus`
--
ALTER TABLE `admissionstudent_contactus`
  ADD PRIMARY KEY (`ascu_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admissionstudent_contactus`
--
ALTER TABLE `admissionstudent_contactus`
  MODIFY `ascu_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;
