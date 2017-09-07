use brihaspati;

drop table if exists QUIZ_IPADDRESS;

CREATE TABLE QUIZ_IPADDRESS
(
                            ID INTEGER(11) AUTO_INCREMENT NOT NULL,
                            USER_ID INTEGER (20) NOT NULL,
                            QUIZ_ID VARCHAR (255) NOT NULL,
                            IP_ADDRESS VARCHAR (255) NOT NULL,
			    QUIZ_DATE DATE DEFAULT NULL,
    			    PRIMARY KEY(ID)
);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (172, 'QUIZ_IPADDRESS', 1000, 10);
