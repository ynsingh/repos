use brihaspati;
drop table if exists USER_PREF;

CREATE TABLE USER_PREF
(
                            USER_ID INTEGER NOT NULL,
                            USER_LANG VARCHAR (32) default 'english' NOT NULL,
    PRIMARY KEY(USER_ID),
    UNIQUE (USER_ID)
);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (158, 'USER_PREF', 100, 1);
INSERT INTO USER_PREF (USER_ID, USER_LANG) SELECT USER_ID, USER_LANG FROM TURBINE_USER;
