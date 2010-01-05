use brihaspati;

drop table if exists RESEARCH_REPOSITORY;

CREATE TABLE RESEARCH_REPOSITORY
(
                            SUBJECT_ID INTEGER AUTO_INCREMENT,
                            REPLY_ID INTEGER,
                            SUBJECT VARCHAR(255),
                            REPLIES INTEGER,
                            USER_ID INTEGER,
                            REPO_NAME VARCHAR(255),
                            POST_TIME DATE,
                            EXPIRY INTEGER,
                            EXPIRY_DATE DATE,
    PRIMARY KEY(SUBJECT_ID)
);


insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (129, 'RESEARCH_REPOSITORY', 1000, 10);

