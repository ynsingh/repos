use brihaspati;


drop table if exists ROOM;

CREATE TABLE ROOM
(
                            ROOM_ID INTEGER NOT NULL,
                            ROOM_CODE VARCHAR (10) NOT NULL,
                            INSTITUTE_ID INTEGER NOT NULL,
                            DEPARTMENT_ID INTEGER NOT NULL,
                            CAPACITY INTEGER NOT NULL,
                            PROJECTOR TINYINT,
                            LAB TINYINT,
                            DEPTONLY TINYINT,
                            FLOOR INTEGER,
    PRIMARY KEY(ROOM_ID)
);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (147, 'ROOM', 100, 1);

drop table if exists DEPARTMENT;

CREATE TABLE DEPARTMENT
(
                            DEPARTMENT_ID INTEGER NOT NULL,
                            NAME VARCHAR (100) NOT NULL,
                            DEPARTMENT_CODE VARCHAR (10) NOT NULL,
                            FLOORS_COUNT INTEGER (10) NOT NULL,
                            INSTITUTE_ID INTEGER NOT NULL,
    PRIMARY KEY(DEPARTMENT_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (148, 'DEPARTMENT', 100, 1);

drop table if exists TIMETABLE_COURSE_INFO;

CREATE TABLE TIMETABLE_COURSE_INFO
(
                            COURSE_ID INTEGER NOT NULL,
                            COURSE_CODE VARCHAR (10) NOT NULL,
                            VENUE_LIST VARCHAR (255) NOT NULL,
                            COURSE_TYPE VARCHAR (10) NOT NULL,
                            PROJECTOR TINYINT NOT NULL,
    PRIMARY KEY(COURSE_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (149, 'TIMETABLE_COURSE_INFO', 100, 1);

