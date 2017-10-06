use olas;
ALTER TABLE `admissionstudent_enterenceexamcenter` ADD `eec_state` VARCHAR(255) DEFAULT NULL AFTER `eec_address`;
CREATE TABLE `admissionstudent_enterenceexamcentera` (
  `eeca_id` int(11) NOT NULL,
  `eeca_eecid` int(11) NOT NULL,
  `eeca_code` varchar(255) DEFAULT NULL,
  `eeca_name` varchar(255) DEFAULT NULL,
  `eeca_address` blob,
  `eeca_state` varchar(255) DEFAULT NULL,
  `eeca_city` varchar(255) DEFAULT NULL,
  `eeca_incharge` varchar(255) DEFAULT NULL,
  `eeca_noofroom` int(11) DEFAULT NULL,
  `eeca_capacityinroom` int(11) DEFAULT NULL,
  `eeca_totalcapacity` int(11) DEFAULT NULL,
  `eeca_contactno` varchar(255) DEFAULT NULL,
  `eeca_contactemail` varchar(255) DEFAULT NULL,
  `eeca_archivename` varchar(255) DEFAULT NULL,
  `eeca_archivedate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_enterenceexamcentera`
  ADD PRIMARY KEY (`eeca_id`);

ALTER TABLE `admissionstudent_enterenceexamcentera`
  MODIFY `eeca_id` int(11) NOT NULL AUTO_INCREMENT;

