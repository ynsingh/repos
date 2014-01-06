use login;

CREATE TABLE IF NOT EXISTS bgasAccData
(
                        id int(11)  NOT NULL AUTO_INCREMENT,
                        organization VARCHAR (255) NOT NULL,
                        unit varchar (255) NOT NULL,
                        databasename VARCHAR (255) NOT NULL,
                        year VARCHAR (4) NOT NULL,
                        PRIMARY KEY(id),
                        UNIQUE(databasename)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

