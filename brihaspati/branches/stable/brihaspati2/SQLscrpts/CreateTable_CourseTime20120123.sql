use brihaspati;

drop table if exists COURSE_TIME;

CREATE TABLE COURSE_TIME
(
			    CT_ID INTEGER,
                            ENTRY_ID INTEGER NOT NULL,
                            USER_ID INTEGER NOT NULL,
                            COURSE_ID VARCHAR (255) NOT NULL,
                            CLOGIN_DATE TIMESTAMP DEFAULT '0000-00-00 00:00:00',
                            COURSE_TIME INTEGER,
			    COUNT_COURSELOGIN INTEGER NOT NULL,
			    STATUS INTEGER NOT NULL,
			    PRIMARY KEY(CT_ID)			    
);

drop table if exists COURSE_TIMEDAY;

CREATE TABLE COURSE_TIMEDAY
(
			     CTD_ID INTEGER,
                             USER_ID INTEGER NOT NULL,
                             COURSE_ID VARCHAR (255) NOT NULL,
                             PRIVIOUS_DATE DATE NOT NULL,
                             COURSE_TIMEDAY INTEGER,
			     COUNT_LOGINDAY INTEGER NOT NULL,
			    PRIMARY KEY(CTD_ID)
);

drop table if exists COURSE_MONTH;

CREATE TABLE COURSE_MONTH
(
			     ID INTEGER,
                             USER_ID INTEGER NOT NULL,
                            COURSE_ID VARCHAR (255) NOT NULL,
                            MONTH_YEAR VARCHAR (255) NOT NULL,
                            COURSE_TIMEMONTH INTEGER,
			    COUNT_LOGINMONTH INTEGER NOT NULL,
			     PRIMARY KEY(ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (153, 'COURSE_TIME', 100, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (154, 'COURSE_TIMEDAY', 100, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (155, 'COURSE_MONTH', 100, 1);
