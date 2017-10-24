use payroll;

CREATE TABLE `map_sc_uo` (
     `scuo_id` int(11) NOT NULL auto_increment,
     `scuo_scid` int(11) NOT NULL,
     `scuo_uoid` int(11) NOT NULL,
     PRIMARY KEY (scuo_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `map_sc_uo_archive` (
     `scuoa_id` int(11) NOT NULL auto_increment,
     `scuoa_scuoid` int(11) NOT NULL,
     `scuoa_scid` int(11) NOT NULL,
     `scuoa_uoid` int(11) NOT NULL,
     `scuoa_archuserid` int(11) NOT NULL,
     `scuoa_archdate`  date NOT NULL,
      PRIMARY KEY (scuoa_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

