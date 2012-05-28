use brihaspati;

drop table if exists DB_SEND;

CREATE TABLE DB_SEND
(
                            MSG_ID INTEGER AUTO_INCREMENT,
                            REPLY_ID INTEGER,
                            MSG_SUBJECT VARCHAR(255),
                            USER_ID INTEGER,
                            GROUP_ID INTEGER,
                            POST_TIME DATE,
                            EXPIRY INTEGER,
                            EXPIRY_DATE DATE,
                            PERMISSION INTEGER(1),
    PRIMARY KEY(MSG_ID)
);

drop table if exists DB_RECEIVE;

CREATE TABLE DB_RECEIVE
(
                            MSG_ID INTEGER AUTO_INCREMENT,
                            RECEIVER_ID INTEGER,
                            GROUP_ID INTEGER,
                            READ_FLAG INTEGER,
    PRIMARY KEY(MSG_ID,RECEIVER_ID)
);


