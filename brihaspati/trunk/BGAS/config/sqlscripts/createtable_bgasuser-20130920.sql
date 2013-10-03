use login;

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
			
insert into bgasuser values (1,'admin', md5('admin'),'ynsingh@iitk.ac.in','administrator',1,'*'); 
insert into bgasuser values (2,'guest', md5('guest'),'ynsingh@iitk.ac.in','guest',1,'*'); 
