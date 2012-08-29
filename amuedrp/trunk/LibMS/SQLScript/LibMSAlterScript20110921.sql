CREATE TABLE `biblio` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL DEFAULT '0',
  `marctag` varchar(20) NOT NULL,
  `indicator1` char(1) DEFAULT NULL,
  `indicator2` char(1) DEFAULT NULL,
  `$a` varchar(300) DEFAULT NULL,
  `$b` varchar(100) DEFAULT NULL,
  `$c` varchar(100) DEFAULT NULL,
  `$d` varchar(100) DEFAULT NULL,
  `$e` varchar(100) DEFAULT NULL,
  `$f` varchar(100) DEFAULT NULL,
  `$g` varchar(100) DEFAULT NULL,
  `$h` varchar(100) DEFAULT NULL,
  `$i` varchar(100) DEFAULT NULL,
  `$j` varchar(100) DEFAULT NULL,
  `$k` varchar(100) DEFAULT NULL,
  `$l` varchar(100) DEFAULT NULL,
  `$m` varchar(100) DEFAULT NULL,
  `$n` varchar(100) DEFAULT NULL,
  `$o` varchar(100) DEFAULT NULL,
  `$p` varchar(100) DEFAULT NULL,
  `$q` varchar(100) DEFAULT NULL,
  `$r` varchar(100) DEFAULT NULL,
  `$s` varchar(100) DEFAULT NULL,
  `$t` varchar(100) DEFAULT NULL,
  `$u` varchar(100) DEFAULT NULL,
  `$v` varchar(100) DEFAULT NULL,
  `$w` varchar(100) DEFAULT NULL,
  `$x` varchar(100) DEFAULT NULL,
  `$y` varchar(100) DEFAULT NULL,
  `$z` varchar(100) DEFAULT NULL,
  `$0` varchar(100) DEFAULT NULL,
  `$1` varchar(100) DEFAULT NULL,
  `$2` varchar(100) DEFAULT NULL,
  `$3` varchar(100) DEFAULT NULL,
  `$4` varchar(100) DEFAULT NULL,
  `$5` varchar(100) DEFAULT NULL,
  `$6` varchar(100) DEFAULT NULL,
  `$7` varchar(100) DEFAULT NULL,
  `$8` varchar(100) DEFAULT NULL,
  `$9` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`bib_id`,`marctag`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `biblio_temp` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL DEFAULT '0',
  `marctag` varchar(20) NOT NULL,
  `indicator1` char(1) DEFAULT NULL,
  `indicator2` char(1) DEFAULT NULL,
  `$a` varchar(300) DEFAULT NULL,
  `$b` varchar(100) DEFAULT NULL,
  `$c` varchar(100) DEFAULT NULL,
  `$d` varchar(100) DEFAULT NULL,
  `$e` varchar(100) DEFAULT NULL,
  `$f` varchar(100) DEFAULT NULL,
  `$g` varchar(100) DEFAULT NULL,
  `$h` varchar(100) DEFAULT NULL,
  `$i` varchar(100) DEFAULT NULL,
  `$j` varchar(100) DEFAULT NULL,
  `$k` varchar(100) DEFAULT NULL,
  `$l` varchar(100) DEFAULT NULL,
  `$m` varchar(100) DEFAULT NULL,
  `$n` varchar(100) DEFAULT NULL,
  `$o` varchar(100) DEFAULT NULL,
  `$p` varchar(100) DEFAULT NULL,
  `$q` varchar(100) DEFAULT NULL,
  `$r` varchar(100) DEFAULT NULL,
  `$s` varchar(100) DEFAULT NULL,
  `$t` varchar(100) DEFAULT NULL,
  `$u` varchar(100) DEFAULT NULL,
  `$v` varchar(100) DEFAULT NULL,
  `$w` varchar(100) DEFAULT NULL,
  `$x` varchar(100) DEFAULT NULL,
  `$y` varchar(100) DEFAULT NULL,
  `$z` varchar(100) DEFAULT NULL,
  `$0` varchar(100) DEFAULT NULL,
  `$1` varchar(100) DEFAULT NULL,
  `$2` varchar(100) DEFAULT NULL,
  `$3` varchar(100) DEFAULT NULL,
  `$4` varchar(100) DEFAULT NULL,
  `$5` varchar(100) DEFAULT NULL,
  `$6` varchar(100) DEFAULT NULL,
  `$7` varchar(100) DEFAULT NULL,
  `$8` varchar(100) DEFAULT NULL,
  `$9` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bib_id`,`library_id`,`marctag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `editmarc` (
  `tagnumber` bigint(20) NOT NULL DEFAULT '0',
  `tagname` varchar(100) DEFAULT NULL,
  `subsymbol` char(1) NOT NULL DEFAULT '',
  `subdescription` varchar(100) DEFAULT NULL,
  `repeatable1` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`tagnumber`,`subsymbol`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `customizedbiblio` (
  `library_id` varchar(20) NOT NULL,
  `sublibrary_id` varchar(20) NOT NULL,
  `bib_id` int(11) NOT NULL DEFAULT '0',
  `marctag` varchar(20) NOT NULL,
  `indicator1` char(1) DEFAULT NULL,
  `indicator2` char(1) DEFAULT NULL,
  `$a` varchar(500) DEFAULT NULL,
  `$b` varchar(100) DEFAULT NULL,
  `$c` varchar(100) DEFAULT NULL,
  `$d` varchar(100) DEFAULT NULL,
  `$e` varchar(100) DEFAULT NULL,
  `$f` varchar(100) DEFAULT NULL,
  `$g` varchar(100) DEFAULT NULL,
  `$h` varchar(100) DEFAULT NULL,
  `$i` varchar(100) DEFAULT NULL,
  `$j` varchar(100) DEFAULT NULL,
  `$k` varchar(100) DEFAULT NULL,
  `$l` varchar(100) DEFAULT NULL,
  `$m` varchar(100) DEFAULT NULL,
  `$n` varchar(100) DEFAULT NULL,
  `$o` varchar(100) DEFAULT NULL,
  `$p` varchar(100) DEFAULT NULL,
  `$q` varchar(100) DEFAULT NULL,
  `$r` varchar(100) DEFAULT NULL,
  `$s` varchar(100) DEFAULT NULL,
  `$t` varchar(100) DEFAULT NULL,
  `$u` varchar(100) DEFAULT NULL,
  `$v` varchar(100) DEFAULT NULL,
  `$w` varchar(100) DEFAULT NULL,
  `$x` varchar(100) DEFAULT NULL,
  `$y` varchar(100) DEFAULT NULL,
  `$z` varchar(100) DEFAULT NULL,
  `$0` varchar(100) DEFAULT NULL,
  `$1` varchar(100) DEFAULT NULL,
  `$2` varchar(100) DEFAULT NULL,
  `$3` varchar(100) DEFAULT NULL,
  `$4` varchar(100) DEFAULT NULL,
  `$5` varchar(100) DEFAULT NULL,
  `$6` varchar(100) DEFAULT NULL,
  `$7` varchar(100) DEFAULT NULL,
  `$8` varchar(100) DEFAULT NULL,
  `$9` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`library_id`,`bib_id`,`marctag`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


alter table temp_excell_import add column status varchar(50);
