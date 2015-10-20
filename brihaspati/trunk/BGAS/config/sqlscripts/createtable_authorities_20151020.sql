use login;

CREATE TABLE IF NOT EXISTS authorities
(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	nickname varchar(255) NOT NULL,
	authority_email varchar(255),
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS authority_map
(
	id int(11) NOT NULL AUTO_INCREMENT,
	authority_id int(11) NOT NULL,
	user_id int(11) NOT NULL,
	map_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	till_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	authority_type varchar(10) NOT NULL, 
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS authority_archive
(
	id int(11) NOT NULL AUTO_INCREMENT,
	authority_id int(11) NOT NULL,
	user_id int(11) NOT NULL,
	map_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	till_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	authority_type varchar(10) NOT NULL, 
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


