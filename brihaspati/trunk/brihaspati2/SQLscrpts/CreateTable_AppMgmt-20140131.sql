use brihaspati;

drop table if exists APPLIST;

CREATE TABLE APPLIST
(
			ID INTEGER NOT NULL,
                        APPNAME VARCHAR (255) NOT NULL,
			ACRONYM VARCHAR (255),
			APPURL VARCHAR (255) NOT NULL,
			APPSTATUS INTEGER(1) default 0,
			APPSECRETKEY VARCHAR (255) NOT NULL,
			APPSERVERLOC VARCHAR (255), 
                	PRIMARY KEY(ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (170, 'APPLIST', 100, 1);

