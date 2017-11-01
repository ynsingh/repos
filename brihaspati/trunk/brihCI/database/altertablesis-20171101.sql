use payroll;

CREATE TABLE `user_role_type_archive` (
  `urta_id` INT(11) NOT NULL,
  `urta_urtid` INT(11) NOT NULL,
  `urta_userid` int(11) NOT NULL,
  `urta_roleida` int(11) NOT NULL,
  `urta_scida` int(10) NOT NULL,
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

CREATE TABLE `map_scheme_department_archive` (
  `msda_id` int(11) NOT NULL,
  `msda_msdid` int(11) NOT NULL,
  `msda_schmid` varchar(255) NOT NULL,   
  `msda_deptid` varchar(255)  NOT NULL,   
  `msda_scid` int(11) NOT NULL,
  `msda_org_id`  varchar(255)  NOT NULL,
  `msda_archuserid` varchar(255) NOT NULL,
  `msda_archdate`date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `map_scheme_department_archive`
  ADD PRIMARY KEY (`msda_id`);

ALTER TABLE `map_scheme_department_archive`
  MODIFY `msda_id` int(11) NOT NULL AUTO_INCREMENT;

