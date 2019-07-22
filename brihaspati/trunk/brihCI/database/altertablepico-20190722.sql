CREATE TABLE `user_role_type` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `scid` int(10) DEFAULT NULL,
  `deptid` int(10) DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into user_role_type values (1,1,1,1,NULL,'Administrator','');
insert into user_role_type values (2,6,11,NULL,NULL,'PICOAdministrator','');

CREATE TABLE `user_role_type_archive` (
  `urta_id` INT(11) NOT NULL,
  `urta_urtid` INT(11) NOT NULL,
  `urta_userid` int(11) NOT NULL,
  `urta_roleida` int(11) NOT NULL,
  `urta_scida` int(10) DEFAULT NULL,
  `urta_deptida` int(10) DEFAULT NULL,
  `urta_usertypea` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `creatordate` date NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `user_role_type_archive`
  ADD PRIMARY KEY (`urta_id`);

ALTER TABLE `user_role_type_archive`
  MODIFY `urta_id` int(11) NOT NULL AUTO_INCREMENT;


