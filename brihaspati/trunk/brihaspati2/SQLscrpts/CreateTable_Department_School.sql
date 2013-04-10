use brihaspati;

ALTER TABLE DEPARTMENT DROP INSTITUTE_ID;
ALTER TABLE DEPARTMENT ADD NICK_NAME VARCHAR(255) AFTER DEPARTMENT_CODE;
ALTER TABLE DEPARTMENT ADD UNIQUE (NAME, DEPARTMENT_CODE);

CREATE TABLE SCHOOL
(
                        SCHOOL_ID INTEGER NOT NULL,
                        NAME VARCHAR (255) NOT NULL,
                        SCHOOL_CODE VARCHAR (255) NOT NULL,
                        NICK_NAME VARCHAR (255),
                        DESCRIPTION VARCHAR (255),
			PRIMARY KEY(SCHOOL_ID),
                        UNIQUE (NAME, SCHOOL_CODE)
);

CREATE TABLE DEPT_SCHOOL_UNIV
(
                        ID INTEGER NOT NULL,
                        DEPT_ID VARCHAR (255),
                        SCHOOL_ID VARCHAR (255),
                        UNIVERSITY_ID VARCHAR (255),
			PRIMARY KEY(ID),
                        UNIQUE (DEPT_ID, SCHOOL_ID, UNIVERSITY_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (167, 'SCHOOL', 1, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (168, 'DEPT_SCHOOL_UNIV', 1, 1);
