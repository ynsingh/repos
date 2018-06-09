use login;

drop table if exists edrpuser;

CREATE TABLE IF NOT EXISTS edrpuser
(
	  id INTEGER (20)  NOT NULL AUTO_INCREMENT,
	  username VARCHAR (200) NOT NULL,
	  password VARCHAR (200) NOT NULL,
	  email VARCHAR (200) NOT NULL,
	  componentreg VARCHAR (200) NOT NULL,
	  category_type VARCHAR (200) NOT NULL,
	  mobile VARCHAR (13) DEFAULT NULL,
	  status VARCHAR (200) NOT NULL,
	  verification_code  varchar(32) NOT NULL,
	  is_verified int(1) NOT NULL, 
	  PRIMARY KEY(id),
	  UNIQUE (username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into edrpuser values (1,'admin', md5('admin'),'ynsingh@iitk.ac.in','*','','',1,'NULL',1);
insert into edrpuser values (2,'guest', md5('guest'),'ynsingh@iitk.ac.in','*','','',1,'NULL',1);

drop table if exists bgasAccData;

CREATE TABLE IF NOT EXISTS bgasAccData
(
	id INTEGER (11)  NOT NULL AUTO_INCREMENT,
	organization VARCHAR (255) NOT NULL,
	unit varchar (255) NOT NULL,
	databasename VARCHAR (255) NOT NULL,
	fyear VARCHAR (14) NOT NULL,
	uname VARCHAR(255) NOT NULL,
	dbpass VARCHAR (255) NOT NULL,
	hostname VARCHAR (255) NOT NULL default 'localhost',
	port int(9) NOT NULL default 3306,
	dbtype VARCHAR (255) NOT NULL default 'mysql',
	dblable VARCHAR (255) NOT NULL,
	prevyeardb VARCHAR(255) NOT NULL, 
	PRIMARY KEY(id),
	UNIQUE(databasename),
	UNIQUE (dblable)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists bgasuserrolegroup;

CREATE TABLE IF NOT EXISTS bgasuserrolegroup
(
	id INTEGER (20)  NOT NULL AUTO_INCREMENT,
	userid integer (20) NOT NULL,
	role VARCHAR (200) NOT NULL,
	accounts VARCHAR (200) NOT NULL,
	aggtype VARCHAR (100) DEFAULT NULL,
	PRIMARY KEY(id),
	UNIQUE (userid,role,accounts)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into bgasuserrolegroup values (1,1,'administrator','*','');
insert into bgasuserrolegroup values (2,2,'guest','*','');

drop table if exists userprofile;

CREATE TABLE IF NOT EXISTS userprofile
(
	id INTEGER (20)  NOT NULL AUTO_INCREMENT,
	userid integer (20) NOT NULL,
	firstname VARCHAR (255),
	lastname VARCHAR (255),
	address VARCHAR (255),
	secmail VARCHAR (255),
	mobile VARCHAR (13),
	lang VARCHAR (25),
	status VARCHAR (25),
	PRIMARY KEY(id),
	UNIQUE (userid)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into userprofile values (1,1,'Y.N.','Singh','','','','English',1);
insert into userprofile values (2,2,'Guest','','','','','English',1);

drop table if exists userlaststatus;

CREATE TABLE IF NOT EXISTS userlaststatus
(
	id INTEGER (20)  NOT NULL AUTO_INCREMENT,
	userid integer (20) NOT NULL,
	lastusedlang VARCHAR (255),
	lastlogincomponent VARCHAR (255),
	lastloginstatus VARCHAR (255),
	lastvisitedcomponent VARCHAR (255),
	lastlogindate DATETIME,
	lastpasswordchangedate DATETIME,
	status VARCHAR (25),
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into userlaststatus values (1,1,'English','',1,'','','',1);
insert into userlaststatus values (2,2,'English','',1,'','','',1);


drop table if exists aggregateaccounts;

CREATE TABLE IF NOT EXISTS aggregateaccounts
(
	id INTEGER (11) NOT NULL AUTO_INCREMENT,
	username varchar (100) NOT NULL,
	accounts varchar (200) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists emailSetting;

CREATE TABLE IF NOT EXISTS emailSetting
(
   	id INTEGER (11) NOT NULL AUTO_INCREMENT,
	email_protocol varchar(9) NOT NULL,
  	email_host varchar(255) NOT NULL,
  	email_port int(5) NOT NULL,
  	email_username varchar(255) NOT NULL,
	email_password varchar(255) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO emailSetting VALUES (1,'','','','','');

drop table if exists forgotPass;

CREATE TABLE IF NOT EXISTS forgotPass
(
 	id INTEGER NOT NULL AUTO_INCREMENT,
 	username VARCHAR (255) NOT NULL,
 	rkey VARCHAR (255) NOT NULL,
 	passdate DATETIME,
 	expdate DATETIME NOT NULL,
	PRIMARY KEY(ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
