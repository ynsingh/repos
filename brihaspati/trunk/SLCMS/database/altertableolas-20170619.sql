CREATE TABLE `programcategory` (
  `prgcat_id` int(11) NOT NULL,
  `prgcat_name` varchar(255) NOT NULL,
  `prgcat_code` varchar(255)  NULL,
  `prgcat_short` varchar(255)NULL,
  `prgcat_desc` varchar(255)  NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `programcategory`
  ADD PRIMARY KEY (`prgcat_id`),
  ADD UNIQUE KEY `prgcat_name` (`prgcat_name`);

ALTER TABLE `programcategory`
  MODIFY `prgcat_id` int(11) NOT NULL AUTO_INCREMENT;

