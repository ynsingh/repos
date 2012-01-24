use brihaspati;

drop table if exists MODULE_TIME;

CREATE TABLE MODULE_TIME
(
                             MID INTEGER AUTO_INCREMENT,
			     USER_ID INTEGER NOT NULL,
			     COURSE_ID VARCHAR (255) NOT NULL,
                             MNAME VARCHAR (255)NOT NULL,
                             MLOGIN_DATETIME TIMESTAMP DEFAULT '0000-00-00 00:00:00',
                             MTIME INTEGER,
                            PRIMARY KEY(MID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (154, 'MODULE_TIME', 1000, 10);

