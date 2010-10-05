use brihaspati;

drop table if exists FAQ;

CREATE TABLE FAQ
(
                            FAQ_ID INTEGER AUTO_INCREMENT,
                            CATEGORY VARCHAR (255), 
                            USER_ID INTEGER,
                            GROUP_ID INTEGER,
                            QUES_ID INTEGER,
                            POST_TIME DATE,
                            EXPIRY_DATE DATE,
                            VOTE VARCHAR (255),
    			    PRIMARY KEY(FAQ_ID)
);

drop table if exists FAQ_VOTE;

CREATE TABLE FAQ_VOTE
(
                            FAQ_ID INTEGER,
                            USER_ID INTEGER,
                            QUES_ID INTEGER,
                            GOOD INTEGER,
                            OK INTEGER ,
                            WORST INTEGER,
    			    PRIMARY KEY(FAQ_ID,USER_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (133, 'FAQ_VOTE', 1000, 10);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (132, 'FAQ', 1000, 10);
