use olas;

CREATE TABLE `admissionstudent_pg` (
  `aspg_id` int(11) NOT NULL,
  `aspg_asmid` int(11) NOT NULL,
  `aspg_txnid` varchar(255) NOT NULL,
  `aspg_pinfo` varchar(255) NOT NULL,
  `aspg_amount` int(11) NOT NULL,
  `aspg_ftype` varchar(255) DEFAULT NULL,
  `aspg_date` datetime NOT NULL,
  `aspg_gw` varchar(255) DEFAULT NULL,
  `aspg_status` varchar(255) DEFAULT NULL,
  `aspg_txncode` varchar(255) DEFAULT NULL,
  `aspg_reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `admissionstudent_pg`   ADD PRIMARY KEY (`aspg_id`);
ALTER TABLE `admissionstudent_pg`   MODIFY `aspg_id` int(11) NOT NULL AUTO_INCREMENT;
