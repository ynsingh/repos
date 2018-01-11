use payroll;
--
-- Table structure for table `Staff_Performance_Data`
--

CREATE TABLE `Staff_Performance_Data` (
  `spd_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `spd_empid` int(11)  NOT NULL,
  `spd_int_award` int (11)  default NULL,
  `spd_nat_award` int (11)  default NULL,
  `spd_uni_award`int (11)  default NULL,
  `spd_res_pub_int` int (11)  default NULL,
  `spd_res_pub_nat` int (11)  default NULL,
  `spd_pop_pub_int` int (11)  default NULL,
  `spd_pop_pub_nat` int (11)  default NULL,
  `spd_pre_pub_int` int (11)  default NULL,
  `spd_pre_pub_nat` int (11)  default NULL,
  `spd_noof_project` int (11)  default NULL,
  `spd_fund_outly_ofproject` DOUBLE default NULL,
  `spd_tr_att_int` int (11)  default NULL,
  `spd_tr_att_nat` int (11)  default NULL,
  `spd_tr_con_int` int (11)  default NULL,
  `spd_tr_con_nat` int (11)  default NULL,
  `spd_mvsc_stu_guided` int (11)  default NULL,
  `spd_phd_stu_guided` int (11)  default NULL,
  `spd_others_stu_guided` int (11)  default NULL,
  `spd_no_ofguestlecture` int (11)  default NULL,
  `spd_per_filename` varchar(500) default NULL,
  `spd_creatorid` varchar(255) NOT NULL, 
  `spd_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `spd_modifierid` varchar(255) NOT NULL,
  `spd_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP, 
   UNIQUE (`spd_empid`)


) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
