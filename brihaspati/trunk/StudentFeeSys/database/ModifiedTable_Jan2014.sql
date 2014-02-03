
ALTER TABLE org_profile modify column org_email VARCHAR(100) NOT NULL DEFAULT '' UNIQUE;
ALTER TABLE college_pending_status modify column org_code VARCHAR(400) COLLATE latin1_swedish_ci DEFAULT NULL;

#
# Structure for the `user_group_master` table :
#

DROP TABLE IF EXISTS `user_group_master`;

CREATE TABLE `user_group_master` (
  `grp_id` int(11) NOT NULL auto_increment,
  `grp_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`grp_id`,`grp_name`),
  UNIQUE KEY `grp_name` (`grp_name`),
  KEY `grp_id` (`grp_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user_master` table :
#

DROP TABLE IF EXISTS `user_master`;

CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(100) NOT NULL default '',
  `user_pass` varchar(20) NOT NULL,
  `user_org_id` varchar(400) NOT NULL,
  `user_grp_id` int(11) NOT NULL,
  `create_time` TIME DEFAULT NULL,
  `edit_time` TIME DEFAULT NULL,
  `date` DATE DEFAULT NULL,
  `modifier_id` INTEGER(11) NOT NULL,
  `login_time` TIME NOT NULL,
  `logout_time` TIME NOT NULL,
  `user_profile_id` int(11) NOT NULL default '0',
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_org_id` (`user_org_id`),
  KEY `user_id` (`user_id`),
  KEY `user_grp_id` (`user_grp_id`),
  KEY `user_name_2` (`user_name`),
  CONSTRAINT `user_master_fk` FOREIGN KEY (`user_name`) REFERENCES `org_profile` (`org_email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_master_ibfk_1` FOREIGN KEY (`user_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_master_ibfk_2` FOREIGN KEY (`user_grp_id`) REFERENCES `user_group_master` (`grp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `user_group_master` table  (LIMIT 0,500)
#

INSERT INTO `user_group_master` (`grp_id`, `grp_name`) VALUES
  (1,'User'),
  (2,'Admin'),
  (3,'Super'),
  (4,'Master User'),
  (5,'Accounts');
COMMIT;
#
# Data for the `admin_records` table  (LIMIT 0,500)
#

INSERT INTO `admin_records` (`seq_id`, `user_id`, `admin_pass`, `flag`, `add_date`) VALUES
  (1,'admin','hjhjhj',0,'2012-12-18'),
  (2,'adminjk','jkljkl',0,'2017-11-17'),
  (3,'adminjk1','123123',0,'2018-11-18'),
  (4,'payadmin','admin123',1,'2025-11-25');
COMMIT;

