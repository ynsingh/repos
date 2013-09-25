CREATE TABLE bgasuser
(
                        id INTEGER (20)  NOT NULL AUTO_INCREMENT,
                        username VARCHAR (200) NOT NULL,
			password VARCHAR (200) NOT NULL,
			email VARCHAR (200) NOT NULL,
			role VARCHAR (200) NOT NULL,
			status VARCHAR (200) NOT NULL,
			accounts VARCHAR (200) NOT NULL,
			PRIMARY KEY(id),
			UNIQUE (username)
);
			

