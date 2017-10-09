use olas;
CREATE TABLE `admissionstudent_centerallocation` (
  `ca_id` int(11) NOT NULL,
  `ca_asmid` int(11) DEFAULT NULL,
  `ca_rollno` int(11) DEFAULT NULL,
  `ca_centerlocation` varchar(255) DEFAULT NULL,
  `ca_centername` varchar(255) DEFAULT NULL,
  `ca_hallticketstatus` varchar(50) DEFAULT NULL,
  `ca_stickerstatus` varchar(50) DEFAULT NULL,
  `ca_attendencesheetstatus` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `admissionstudent_centerallocation`
  ADD PRIMARY KEY (`ca_id`);


ALTER TABLE `admissionstudent_centerallocation`
  MODIFY `ca_id` int(11) NOT NULL AUTO_INCREMENT;
