CREATE TABLE `admissionstudent_fees` (
  `asfee_id` int(11) NOT NULL,
  `asfee_amid` int(11) NOT NULL,
  `asfee_aprgid` int(11) NOT NULL,
  `asfee_feename` varchar(255) NOT NULL,
  `asfee_feeamount` varchar(255) NOT NULL,
  `asfee_installment` int(2) NOT NULL,
  `asfee_installment_date` date DEFAULT NULL,
  `asfee_duedate` datetime DEFAULT NULL,
  `asfee_paymentmethod` varchar(255) NOT NULL,
  `asfee_depositdate` datetime DEFAULT NULL,
  `asfee_referenceno` varchar(255) NOT NULL,
  `asfee_bankname` varchar(255) DEFAULT NULL,
  `asfee_paymentgateway` varchar(255) NOT NULL,
  `asfee_paymentstatus` varchar(255) NOT NULL,
  `asfee_feespaidstatus` varchar(255) NOT NULL,
  `asfee_reconcilestatus` varchar(10) NOT NULL DEFAULT 'N',
  `asfee_whoreconcile` varchar(255) DEFAULT NULL,
  `asfee_reconciledate` datetime DEFAULT NULL,
  `asfee_reconcileremark` int(255) DEFAULT NULL,
  `asfee_remarks` varchar(255) NOT NULL,
  `asfee_ext1` varchar(255) NOT NULL,
  `asfee_ext2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_fees`
  ADD PRIMARY KEY (`asfee_id`);

ALTER TABLE `admissionstudent_fees`
  MODIFY `asfee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


ALTER TABLE `admissionstudent_uploaddata` CHANGE `asupd_smid` `asupd_asmid` INT(11) NULL DEFAULT NULL;
