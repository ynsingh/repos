use olas;

ALTER TABLE `announcement` CHANGE `anou_description` `anou_description` BLOB NOT NULL;
ALTER TABLE `announcement` CHANGE `anou_remark` `anou_remark` BLOB NOT NULL;

CREATE TABLE `announcement_archive` (
  `anoua_id` int(11) NOT NULL,
  `annoa_anoid` int(11) NOT NULL,
  `anoua_cname` varchar(100) NOT NULL,
  `anoua_type` varchar(100) NOT NULL,
  `anoua_title` varchar(255) NOT NULL,
  `anoua_description` BLOB NOT NULL,
  `anoua_publishdate` date NOT NULL,
  `anoua_expdate` date NOT NULL,
  `anoua_remark` BLOB NOT NULL,
  `anoua_creatordate` date NOT NULL,
  `anoua_creatorid` varchar(255) NOT NULL,
  `anoua_archivename`  varchar(255) DEFAULT NULL,
  `anoua_archivedate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `announcement_archive`
  ADD PRIMARY KEY (`anoua_id`);

ALTER TABLE `announcement_archive`
  MODIFY `anoua_id` int(11) NOT NULL AUTO_INCREMENT;

