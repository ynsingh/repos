use brihaspati;

drop table if exists OPENID;

CREATE TABLE OPENID
(
	NONCE VARCHAR (255) NOT NULL,
	PROVIDER VARCHAR (32) NOT NULL,
	TO_DATE BIGINT NOT NULL,

  PRIMARY KEY(NONCE,PROVIDER),
  UNIQUE(NONCE)
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (163, 'OPENID', 1000, 10);
