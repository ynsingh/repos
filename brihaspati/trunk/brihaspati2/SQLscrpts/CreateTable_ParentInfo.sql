use brihaspati;

insert into TURBINE_ROLE (ROLE_ID,ROLE_NAME) values (9,'parent');

drop table if exists PARENT_INFO;

CREATE TABLE PARENT_INFO
(
			ID INTEGER NOT NULL AUTO_INCREMENT,
                        STUDENT_ID VARCHAR (255) NOT NULL,
			PARENT_ID VARCHAR (255) NOT NULL,
			PRIMARY KEY(ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (169, 'PARENT_INFO', 1000, 1);
