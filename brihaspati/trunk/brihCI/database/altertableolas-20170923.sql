CREATE TABLE `announcement` (
  `anou_id` int(11) NOT NULL,
  `anou_cname` int(11) NOT NULL,
  `anou_name` int(2) NOT NULL,
  `anou_description` varchar(255) NOT NULL,
  `anou_attachment` varchar(255) NOT NULL,
  `anou_publishdate` date NOT NULL,
  `anou_expdate` date NOT NULL,
  `anou_remark` varchar(255) NOT NULL,
  `anou_creatordate` date NOT NULL,
  `anou_creatorid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `announcement`
  ADD PRIMARY KEY (`anou_id`);

ALTER TABLE `announcement`
  MODIFY `anou_id` int(11) NOT NULL AUTO_INCREMENT;

