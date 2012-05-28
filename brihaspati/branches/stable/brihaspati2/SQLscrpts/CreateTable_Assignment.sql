use brihaspati;

drop table if exists ASSIGNMENT;

CREATE TABLE ASSIGNMENT
(
                        ASSIGN_ID VARCHAR (255)  NOT NULL,
                        GROUP_NAME VARCHAR (255) NOT NULL,
                        TOPIC_NAME VARCHAR (255) NOT NULL,
                        CUR_DATE DATETIME NOT NULL,
                        DUE_DATE DATETIME NOT NULL,
                        PER_DATE DATETIME NOT NULL,
                        GRADE INTEGER (3) NOT NULL ,
                	PRIMARY KEY(ASSIGN_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (126, 'ASSIGNMENT', 1000, 10);

