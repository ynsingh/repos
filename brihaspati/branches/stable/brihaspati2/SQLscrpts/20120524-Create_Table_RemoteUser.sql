use brihaspati;

drop table if exists REMOTE_USERS;

CREATE TABLE REMOTE_USERS
(
                            SR_NO INTEGER NOT NULL,
                            USERID VARCHAR (255),
                            RANDOMKEY VARCHAR (255),
                            APPLICATION VARCHAR (255),
                            SOURCEID VARCHAR (255),
    PRIMARY KEY(SR_NO),
    UNIQUE (USERID)
);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (160, 'REMOTE_USERS', 100, 1);
