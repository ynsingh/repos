use brihaspati;

drop table if exists TWEETS;

CREATE TABLE TWEETS
(
                        ID INTEGER NOT NULL AUTO_INCREMENT,
                        USER_NAME VARCHAR (255) NOT NULL,
                        TWEET VARCHAR (255) NOT NULL,
                        TWEET_DATE VARCHAR (255) NOT NULL,
                        PRIMARY KEY(ID)
                        );

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (165, 'TWEETS', 001, 1);

