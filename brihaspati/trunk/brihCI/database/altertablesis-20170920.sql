CREATE TABLE `staff_position_archive` (
  `spa_id` int(11) NOT NULL,
  `spa_spid` int(11) NOT NULL,
  `spa_tnt` varchar(255)  NOT NULL,
  `spa_type` varchar(255)  NOT NULL,
  `spa_emppost` varchar(255)  NOT NULL,
  `spa_grppost` varchar(255) NOT  NULL,
  `spa_scale` varchar(255) NOT NULL,
  `spa_methodRect` varchar(255) NOT NULL,
  `spa_group` varchar(255) NOT NULL,
  `spa_uo` varchar(255) NOT NULL,
  `spa_dept` varchar(255) NOT NULL,
  `spa_address1` varchar(255) default NULL,
  `spa_address2` varchar(255) default NULL,
  `spa_address3` varchar(255) default NULL,
  `spa_campusid` int(11)  NOT NULL,
  `spa_per_temporary` varchar(255) NOT NULL,
  `spa_plan_nonplan` varchar(255)  NOT NULL,
  `spa_schemecode` int(11)  NOT NULL,
  `spa_sancstrenght` int(11)  NOT NULL,
  `spa_position` int(11)  NOT NULL,
  `spa_vacant` int(11)  NOT NULL,
  `spa_remarks` varchar(255)  default NULL,
  `spa_ssdetail` varchar(255) default NULL,
  `spa_sspermanent` int(11) default '0',
  `spa_sstemporary` int(11) default '0',
  `spa_pospermanent` int(11) default '0',
  `spa_postemporary` int(11) default '0',
  `spa_vpermanenet` int(11) default '0',
  `spa_vtemporary` int(11) default '0',
  `spa_org_id` int(11) NOT NULL,
  `spa_archuserid` int(11) NOT NULL,
  `spa_archdate`  date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `staff_position_archive`
  ADD PRIMARY KEY (`spa_id`);

ALTER TABLE `staff_position_archive`
  MODIFY `spa_id` int(11) NOT NULL AUTO_INCREMENT;

