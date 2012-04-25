use brihaspati;
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (121, 'SURVEY_QUESTION', 1000, 10);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (122, 'SURVEY_STUDENT', 1000, 10);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (123, 'SURVEY_RESULT', 1000, 10);


drop table if exists SURVEY_QUESTION;

CREATE TABLE SURVEY_QUESTION
(
                            SURVEY_ID INTEGER NOT NULL,
                            INST_ID INTEGER NOT NULL,
                            CID VARCHAR (25),
                            QUES1 VARCHAR (50),
                            QUES2 VARCHAR (50),
                            QUES3 VARCHAR (50),
                            QUES4 VARCHAR (50),
                            QUES5 VARCHAR (50),
                            PDATE DATETIME NOT NULL,
    PRIMARY KEY(SURVEY_ID)
);

drop table if exists SURVEY_STUDENT;

CREATE TABLE SURVEY_STUDENT
(
                            SURVEYST_ID INTEGER NOT NULL,
                            SURVEY_ID INTEGER NOT NULL,
			    INST_ID INTEGER NOT NULL,
                            CID VARCHAR (25),
                            STU_ID VARCHAR (25),
                            QUES1 INTEGER (2),
                            QUES2 INTEGER (2),
                            QUES3 INTEGER (2),
                            QUES4 INTEGER (2),
                            QUES5 INTEGER (2),
                            TOTALMARKS INTEGER,
    PRIMARY KEY(SURVEYST_ID)
);

drop table if exists SURVEY_RESULT;

CREATE TABLE SURVEY_RESULT
(
                            RESULT_ID INTEGER NOT NULL,
                            SURVEY_ID INTEGER NOT NULL,
                            CID VARCHAR (25),
                            NUM_STU_ATTD INTEGER,
                            TOTALMARKS_PER INTEGER,
                            GRADE VARCHAR (25),
    PRIMARY KEY(RESULT_ID)
);

