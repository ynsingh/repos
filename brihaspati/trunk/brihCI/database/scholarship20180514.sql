CREATE TABLE `scholarship` (
  `sr_id` int(11) NOT NULL AUTO_INCREMENT,
  `sch_code` varchar(100) DEFAULT NULL,
  `sch_type` varchar(255) DEFAULT NULL,
  `sch_name` varchar(255) DEFAULT NULL,
  `sch_des` varchar(255) DEFAULT NULL,
  `sch_provider` blob,
  `sch_startyear` varchar(255) NOT NULL,
  `sch_startdate` DATE,
  `sch_enddate` DATE,
 PRIMARY KEY (sr_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `schapply` (
  `sa_id` int(11) NOT NULL AUTO_INCREMENT,
  `sa_userid` varchar(100) DEFAULT NULL,
  `sa_rejres` varchar(255) DEFAULT NULL,
  `sa_lastyerres` varchar(255) DEFAULT NULL,
  `sa_name` varchar(255) DEFAULT NULL,
  `sa_bname` varchar(255) DEFAULT NULL,
  `sa_ifscno` varchar(255) NOT NULL,
  `sa_accno` varchar(255) DEFAULT NULL,
  `sa_branch` varchar(255) DEFAULT NULL,
  `sa_status` int(2) DEFAULT 0,
 PRIMARY KEY (sa_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



