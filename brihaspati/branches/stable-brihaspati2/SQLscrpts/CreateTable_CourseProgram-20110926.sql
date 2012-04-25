use brihaspati;

drop table if exists COURSE_PROGRAM;

CREATE TABLE COURSE_PROGRAM
(
        ID INTEGER NOT NULL,
        STUDENT_ID INTEGER NOT NULL,
        COURSE_ID VARCHAR(255) NOT NULL,
        PROGRAM VARCHAR(255) NOT NULL,
        PRIMARY KEY(ID),
	UNIQUE(STUDENT_ID,COURSE_ID,PROGRAM),
	Foreign Key (STUDENT_ID) references STUDENT_ROLLNO(ID)
);	

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (157, 'COURSE_PROGRAM', 100, 1);
