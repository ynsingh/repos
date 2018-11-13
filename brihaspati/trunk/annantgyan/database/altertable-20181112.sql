CREATE TABLE `userdetails` (
  `ud_id` int(11) NOT NULL,
  `ud_userid` int(11) NOT NULL,
  `ud_fname` varchar(255) DEFAULT NULL,
  `ud_mname` varchar(255) DEFAULT NULL,
  `ud_fdesig` varchar(255) DEFAULT NULL,
  `ud_mdesig` varchar(255) DEFAULT NULL,
  `ud_address` varchar(255) DEFAULT NULL,
  `ud_fadcategory` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

ALTER TABLE `userdetails` ADD PRIMARY KEY (`ud_id`);
ALTER TABLE `userdetails` MODIFY `ud_id` int(11) NOT NULL AUTO_INCREMENT;
