use brihaspati;

drop table if exists FORGOTPASS;

CREATE TABLE FORGOTPASS
(
                        ID INTEGER NOT NULL AUTO_INCREMENT,
                        USER_NAME VARCHAR (255) NOT NULL,
                        RKEY VARCHAR (255) NOT NULL,
                        PASS_DATE DATETIME,
                        EXPIRY_DATE DATETIME NOT NULL,
                        PRIMARY KEY(ID)
                        );

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (171, 'FORGOTPASS', 1000, 10);

