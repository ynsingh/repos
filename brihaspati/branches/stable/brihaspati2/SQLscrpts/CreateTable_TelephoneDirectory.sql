use brihaspati;

drop table if exists TELEPHONE_DIRECTORY;

CREATE TABLE TELEPHONE_DIRECTORY
(
			ID INTEGER NOT NULL AUTO_INCREMENT,
			USER_ID VARCHAR (255) NOT NULL,
                        MAIL_ID VARCHAR (255) NOT NULL,
                        NAME VARCHAR (255),
                        ADDRESS VARCHAR (255),
                        MOBILE_NO VARCHAR (255),
			OTHER_NO VARCHAR (255),
                        HOME_NO VARCHAR (255),
			OFFICE_NO VARCHAR (255),
			DESIGNATION VARCHAR (255),
                        DEPARTMENT VARCHAR (255),
			STATE VARCHAR (255),
			COUNTRY VARCHAR (255),
			PRIMARY KEY(ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (191, 'TELEPHONE_DIRECTORY', 1000, 10);
