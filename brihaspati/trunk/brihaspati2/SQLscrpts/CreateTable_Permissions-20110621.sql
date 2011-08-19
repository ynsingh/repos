use brihaspati;

drop table if exists INSTRUCTOR_PERMISSIONS;

CREATE TABLE INSTRUCTOR_PERMISSIONS
(
			ID INTEGER NOT NULL,
                        USER_ID VARCHAR (255) NOT NULL,
			GROUP_NAME VARCHAR (255) NOT NULL,
			INSTITUTE_ID VARCHAR (255) NOT NULL,
			PERMISSION_STATUS INTEGER NOT NULL,
                	PRIMARY KEY(ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (150, 'INSTRUCTOR_PERMISSIONS', 100, 1);

