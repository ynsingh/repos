
CREATE TABLE `admissionstudent_registration` (
  `asreg_id` int(11) NOT NULL, 
  `asreg_emailid` varchar(255) NOT NULL,
  `asreg_mobile` varchar(255) NOT NULL,
  `asreg_dob` date NOT NULL,
  `asreg_program` varchar(255) NOT NULL,
  `asreg_verificationcode` varchar(255) NOT NULL,
  `asreg_verificationdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_registration`
  ADD PRIMARY KEY (`asreg_id`);
ALTER TABLE `admissionstudent_registration`
  MODIFY `asreg_id` int(11) NOT NULL AUTO_INCREMENT;


CREATE TABLE `result_announcement` (
  `rean_id` int(11) NOT NULL,
  `rean_prgid` int(11) NOT NULL,
  `rean_sem` int(2) NOT NULL,
  `rean_acadyear` varchar(255) NOT NULL,
  `rean_exty` varchar(255) NOT NULL,
  `rean_helddate` date NOT NULL,
  `rean_anncdate` date NOT NULL,
  `rean_remark` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `result_announcement`
  ADD PRIMARY KEY (`rean_id`);

ALTER TABLE `result_announcement`
  MODIFY `rean_id` int(11) NOT NULL AUTO_INCREMENT;


CREATE TABLE `result_stopped` (
  `rest_id` int(11) NOT NULL,
  `rest_smid` int(11) NOT NULL,
  `rest_prgid` int(11) NOT NULL,
  `rest_sem` int(2) NOT NULL,
  `rest_acadyear` varchar(255) NOT NULL,
  `rest_exty` varchar(255) NOT NULL,
  `rest_helddate` date NOT NULL,
  `rest_stopdate` date NOT NULL,
  `rest_remark` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `result_stopped`
  ADD PRIMARY KEY (`rest_id`);

ALTER TABLE `result_stopped`
  MODIFY `rest_id` int(11) NOT NULL AUTO_INCREMENT;

