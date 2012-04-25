use brihaspati;

drop table if exists INSTITUTE_PROGRAM;

CREATE TABLE INSTITUTE_PROGRAM
(
        PROGRAM_CODE VARCHAR(255) NOT NULL,
        INSTITUTE_ID INTEGER NOT NULL,
	UNIQUE(PROGRAM_CODE,INSTITUTE_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (144, 'INSTITUTE_PROGRAM', 1000, 10);
