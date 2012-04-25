use brihaspati;

drop table if exists STUDENT_ROLLNO;

CREATE TABLE STUDENT_ROLLNO
(
	ID INTEGER NOT NULL,
        EMAIL_ID VARCHAR (255) NOT NULL,
        ROLL_NO VARCHAR (255) NOT NULL UNIQUE,
        PROGRAM VARCHAR (255),
    	PRIMARY KEY(ID),
    	UNIQUE(ROLL_NO)
); 

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (142, 'STUDENT_ROLLNO', 1000, 10);	
