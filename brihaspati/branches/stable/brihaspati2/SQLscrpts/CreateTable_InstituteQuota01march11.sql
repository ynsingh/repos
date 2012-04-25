use brihaspati;

drop table if exists INSTITUTE_QUOTA;

CREATE TABLE INSTITUTE_QUOTA
(
	INSTITUTE_ID INTEGER NOT NULL,
        INSTITUTE_AQUOTA NUMERIC NOT NULL,
    	PRIMARY KEY(INSTITUTE_ID)
); 

insert into ID_TABLE (id_table_id,table_name,next_id,quantity) VALUES (146,'INSTITUTE_QUOTA',1000,10);	
