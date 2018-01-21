use login;

CREATE TABLE `createsession` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `batchyear` varchar(200) NOT NULL,
  `dbname` varchar(200) NOT NULL,
  `fstatus` varchar(200) DEFAULT NULL,
  `setupdate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fsetupdate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
   PRIMARY KEY  (`id`),
   UNIQUE KEY `dbname` (`dbname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

CREATE TABLE `createstudentbatch` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userid` int(20) NOT NULL,
  `batchyear` varchar(200) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
    PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
