use edrp_portal;

drop table if exists member;

CREATE TABLE member
(
        mem_id INTEGER NOT NULL AUTO_INCREMENT,
        username VARCHAR (30),
        password VARCHAR (255),
        fname VARCHAR (30),
        lname VARCHAR (30),
	PRIMARY KEY(mem_id)
);

