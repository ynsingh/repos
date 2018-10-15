
ALTER TABLE `society_master_list` ADD `soc_address` VARCHAR(255) NOT NULL AFTER `soc_sremark`;
ALTER TABLE `society_master_list` ADD `soc_regdate` DATETIME(6) NOT NULL AFTER `soc_address`;
ALTER TABLE `society_master_list` ADD `soc_purpose` VARCHAR(100) NOT NULL AFTER `soc_regdate`;

CREATE TABLE `societies` ( 
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `society_id` varchar(100)  NOT NULL,
  `society_head` varchar(255) NOT NULL,
  `society_secretary` varchar(255) NOT NULL,
  `society_treasurer` varchar(255) NOT NULL, 
  `society_members` varchar(255) NOT NULL, 
  `society_totalvalues` varchar(255) NOT NULL,
   UNIQUE (`society_head`,`society_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

