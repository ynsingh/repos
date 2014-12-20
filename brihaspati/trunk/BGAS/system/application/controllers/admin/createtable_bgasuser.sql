use login;

CREATE TABLE IF NOT EXISTS bgasuser
(
                        id INTEGER (20)  NOT NULL AUTO_INCREMENT,
                        username VARCHAR (200) NOT NULL,
			password VARCHAR (200) NOT NULL,
			email VARCHAR (200) NOT NULL,
			role VARCHAR (200) NOT NULL,
			status VARCHAR (200) NOT NULL,
			accounts VARCHAR (200) NOT NULL,
			aggtype VARCHAR (100) DEFAULT NULL,
			PRIMARY KEY(id),
			UNIQUE (username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
			
insert into bgasuser values (1,'admin', md5('admin'),'ynsingh@iitk.ac.in','administrator',1,'*'); 
insert into bgasuser values (2,'guest', md5('guest'),'ynsingh@iitk.ac.in','guest',1,'*'); 

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
                        PRIMARY KEY(id),
			UNIQUE(databasename),
			UNIQUE (dblable)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS aggregateaccounts
(
			id INTEGER (11) NOT NULL AUTO_INCREMENT,
			username varchar (100) NOT NULL,
			accounts varchar (200) NOT NULL,
			PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

