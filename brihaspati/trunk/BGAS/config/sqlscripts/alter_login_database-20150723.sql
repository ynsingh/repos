use login;

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

insert into bgasuserrolegroup
	(`userid`,`role`,`accounts`,`aggtype`)
	SELECT `id`, `role`, `accounts`,`aggtype` 
  	FROM bgasuser;

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

insert into userprofile
	(`userid`)
	SELECT `id` 
  	FROM bgasuser;

update userprofile SET `status`=1,`lang`='English';


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

insert into userlaststatus
	(`userid`)
	SELECT `id` 
  	FROM bgasuser;

update userlaststatus SET `lastusedlang`='English',`lastloginstatus`=1,`status`=1;


RENAME TABLE bgasuser TO user;

ALTER TABLE user DROP role, DROP accounts, DROP aggtype;
ALTER TABLE user
    ADD COLUMN `componentreg` VARCHAR (200) NOT NULL AFTER `email`,
    ADD COLUMN `mobile` VARCHAR (13) DEFAULT NULL AFTER `componentreg`;

update user set componentreg = '*' where id <3;
update user set componentreg = 'BGAS' where id >2;
   
