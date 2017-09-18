use payroll;

CREATE TABLE `cudsdmap` (
  `cudsd_id` int(11) NOT NULL,
  `cudsd_scid` int(11) NOT NULL,
  `cudsd_auoid` int(11) NOT NULL,
  `cudsd_deptid` int(11) default NULL,
  `cudsd_schid` int(11) default NULL,
  `cudsd_ddoid` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `cudsdmap`
  ADD PRIMARY KEY (`cudsd_id`);

ALTER TABLE `cudsdmap`
  MODIFY `cudsd_id` int(11) NOT NULL AUTO_INCREMENT;
