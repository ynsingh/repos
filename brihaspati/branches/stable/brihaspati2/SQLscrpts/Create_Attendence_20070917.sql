use brihaspati;

drop table if exists ATTENDENCE_SEET;

CREATE TABLE ATTENDENCE_SEET
(
                            ID INTEGER NOT NULL,
                            USER_ID INTEGER (11) NOT NULL,
                            LOGIN_DATE DATETIME NOT NULL,
                            IPADDRESS VARCHAR (16),
    PRIMARY KEY(ID)
);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (125, 'ATTENDENCE_SEET', 1000, 10);

