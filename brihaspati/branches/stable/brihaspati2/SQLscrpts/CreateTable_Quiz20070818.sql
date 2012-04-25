use brihaspati;

drop table if exists QUIZ;

CREATE TABLE QUIZ
(
                            ID INTEGER NOT NULL,
                            QUIZ_ID VARCHAR (255) NOT NULL,
                            USER_ID VARCHAR (255) NOT NULL,
                            CID VARCHAR (255),
                            QUIZ_TITLE VARCHAR (255) NOT NULL,
                            START_TIME TIME NOT NULL,
                            END_TIME TIME NOT NULL,
                            POST_DATE DATETIME NOT NULL,
                            MAX_MARKS INTEGER (3),
                            EXPIRY_DATE DATETIME NOT NULL,
    PRIMARY KEY(ID)
);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (124, 'QUIZ', 1000, 10);
