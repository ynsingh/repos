DROP TABLE IF EXISTS `discussions`;
CREATE TABLE `discussions` (
  `ds_id` int(11) NOT NULL AUTO_INCREMENT,
  `ds_crsid` int(11) NOT NULL,
  `ds_usrid` int(11) NOT NULL,
  `ds_title` varchar(255) NOT NULL,
  `ds_body` text NOT NULL,
  `ds_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ds_is_active` int(1) NOT NULL,
  PRIMARY KEY (`ds_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `topics` (
  `tp_id` int(11) NOT NULL AUTO_INCREMENT,
  `tp_title` varchar(255) NOT NULL DEFAULT '',
  `tp_body` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `tp_usrid` int(11) unsigned NOT NULL,
  `tp_dsid` int(11) unsigned NOT NULL,
  `is_sticky` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `cm_id` int(11) NOT NULL AUTO_INCREMENT,
  `cm_dsid` int(11) NOT NULL,
  `cm_tpid` int(11) NOT NULL,
  `cm_body` text NOT NULL,
  `cm_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cm_usrid` int(11) NOT NULL,
  `cm_is_active` int(1) NOT NULL,
  PRIMARY KEY (`cm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

