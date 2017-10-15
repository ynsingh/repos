use olas;
ALTER TABLE `announcement` CHANGE `anou_name` `anou_title` VARCHAR(255) NOT NULL;
ALTER TABLE `announcement` ADD `anou_type` VARCHAR(100) NOT NULL AFTER `anou_cname`;
ALTER TABLE `announcement` CHANGE `anou_cname` `anou_cname` VARCHAR(100) NOT NULL;

CREATE TABLE `Department_archive` (
  `depta_id` int(11) NOT NULL,
  `depta_deptid` int(11) NOT NULL,
  `depta_name` varchar(255) NOT NULL,
  `depta_code` varchar(255) NOT NULL,
  `depta_uoid` int(11) NOT NULL,
  `depta_short` varchar(255) NOT NULL,
  `depta_description` varchar(255) NOT NULL,
  `depta_schoolname` varchar(255) DEFAULT NULL,
  `depta_schoolcode` varchar(255) DEFAULT NULL,
  `depta_sccode` varchar(255) NOT NULL,
  `depta_orgcode` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `Department_archive`
  ADD PRIMARY KEY (`depta_id`);

ALTER TABLE `Department_archive`
  MODIFY `depta_id` int(11) NOT NULL AUTO_INCREMENT;

