use brihaspati;

drop table if exists FAQMOVE;

CREATE TABLE FAQMOVE
(
                            FAQ_ID INTEGER AUTO_INCREMENT,
                            USER_ID INTEGER,
                            INST_ID INTEGER,
    			    PRIMARY KEY(FAQ_ID)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (152, 'FAQMOVE', 1000, 10);

use brihaspati;

ALTER TABLE DB_SEND ADD STATUS INTEGER NOT NULL;
