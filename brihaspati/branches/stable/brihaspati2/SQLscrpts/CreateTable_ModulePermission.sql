use brihaspati;

drop table if exists MODULE_PERMISSION;

CREATE TABLE MODULE_PERMISSION
(
			ID INTEGER NOT NULL,
                        USER_ID VARCHAR (255) NOT NULL,
			MODULE_ID VARCHAR (255) NOT NULL,
			GROUP_NAME VARCHAR (255) NOT NULL,
			INSTITUTE_ID VARCHAR (255) NOT NULL,
			MODULE_STATUS INTEGER NOT NULL default '0',
                	PRIMARY KEY(ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (162, 'MODULE_PERMISSION', 100, 1);

