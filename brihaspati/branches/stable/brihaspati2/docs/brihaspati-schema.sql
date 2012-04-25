

# -----------------------------------------------------------------------
# RDF
# -----------------------------------------------------------------------
drop table if exists RDF;

CREATE TABLE RDF
(
		            RDF_ID INTEGER NOT NULL,
		            TITLE VARCHAR (255),
		            BODY VARCHAR (255),
		            URL VARCHAR (255),
		            AUTHOR VARCHAR (255),
		            DEPT VARCHAR (255),
    PRIMARY KEY(RDF_ID)
);

# -----------------------------------------------------------------------
# COURSES
# -----------------------------------------------------------------------
drop table if exists COURSES;

CREATE TABLE COURSES
(
		            GROUP_NAME VARCHAR (99) NOT NULL,
		            CNAME VARCHAR (255),
		            GROUP_ALIAS VARCHAR (99) NOT NULL,
		            DEPT VARCHAR (255),
		            DESCRIPTION VARCHAR (255),
		            ACTIVE TINYINT default 0 NOT NULL,
		            CREATIONDATE DATETIME,
		            LASTMODIFIED DATETIME,
    PRIMARY KEY(GROUP_NAME)
);

# -----------------------------------------------------------------------
# CAL_INFORMATION
# -----------------------------------------------------------------------
drop table if exists CAL_INFORMATION;

CREATE TABLE CAL_INFORMATION
(
		            INFO_ID INTEGER NOT NULL,
		            USER_ID INTEGER NOT NULL,
		            GROUP_ID INTEGER NOT NULL,
		            P_DATE DATETIME NOT NULL,
		            DETAIL_INFORMATION LONGBLOB,
		            START_TIME TIME NOT NULL,
		            END_TIME TIME NOT NULL,
		            EXPIRY INTEGER NOT NULL,
		            EXPIRY_DATE DATETIME NOT NULL,
    PRIMARY KEY(INFO_ID)
);

# -----------------------------------------------------------------------
# DB_SEND
# -----------------------------------------------------------------------
drop table if exists DB_SEND;

CREATE TABLE DB_SEND
(
		            MSG_ID INTEGER NOT NULL,
		            REPLY_ID INTEGER NOT NULL,
		            MSG_SUBJECT VARCHAR (255),
		            USER_ID INTEGER,
		            GROUP_ID INTEGER,
		            POST_TIME DATETIME,
		            EXPIRY INTEGER NOT NULL,
		            EXPIRY_DATE DATETIME NOT NULL,
		            PERMISSION INTEGER (1),
    PRIMARY KEY(MSG_ID)
);

# -----------------------------------------------------------------------
# DB_RECEIVE
# -----------------------------------------------------------------------
drop table if exists DB_RECEIVE;

CREATE TABLE DB_RECEIVE
(
		            MSG_ID INTEGER NOT NULL,
		            RECEIVER_ID INTEGER NOT NULL,
		            GROUP_ID INTEGER NOT NULL,
		            READ_FLAG INTEGER,
    PRIMARY KEY(MSG_ID,RECEIVER_ID)
);

# -----------------------------------------------------------------------
# GLOSSARY
# -----------------------------------------------------------------------
drop table if exists GLOSSARY;

CREATE TABLE GLOSSARY
(
		            WORD_ID INTEGER NOT NULL,
		            WORD VARCHAR (40) NOT NULL,
		            USER_ID INTEGER (11),
		            DEFINITION LONGBLOB,
    PRIMARY KEY(WORD_ID,WORD)
);

# -----------------------------------------------------------------------
# GLOSSARY_ALIAS
# -----------------------------------------------------------------------
drop table if exists GLOSSARY_ALIAS;

CREATE TABLE GLOSSARY_ALIAS
(
		            ALIAS_ID INTEGER NOT NULL,
		            WORD_ALIAS VARCHAR (40),
		            WORD_ID INTEGER,
    PRIMARY KEY(ALIAS_ID),
    FOREIGN KEY (WORD_ID) REFERENCES GLOSSARY (WORD_ID)
    
);

# -----------------------------------------------------------------------
# HINT_QUESTION
# -----------------------------------------------------------------------
drop table if exists HINT_QUESTION;

CREATE TABLE HINT_QUESTION
(
		            QUESTION_ID INTEGER NOT NULL,
		            QUESTION_NAME VARCHAR (255),
    PRIMARY KEY(QUESTION_ID)
);

# -----------------------------------------------------------------------
# MAIL_SEND
# -----------------------------------------------------------------------
drop table if exists MAIL_SEND;

CREATE TABLE MAIL_SEND
(
		            MAIL_ID INTEGER NOT NULL,
		            SENDER_ID INTEGER,
		            MAIL_SUBJECT VARCHAR (255),
		            REPLY_STATUS INTEGER,
		            POST_TIME DATETIME,
    PRIMARY KEY(MAIL_ID)
);

# -----------------------------------------------------------------------
# MAIL_RECEIVE
# -----------------------------------------------------------------------
drop table if exists MAIL_RECEIVE;

CREATE TABLE MAIL_RECEIVE
(
		            MAIL_ID INTEGER NOT NULL,
		            RECEIVER_ID INTEGER NOT NULL,
		            MAIL_READFLAG INTEGER,
    PRIMARY KEY(MAIL_ID,RECEIVER_ID)
);

# -----------------------------------------------------------------------
# NEWS
# -----------------------------------------------------------------------
drop table if exists NEWS;

CREATE TABLE NEWS
(
		            NEWS_ID INTEGER NOT NULL,
		            GROUP_ID INTEGER,
		            USER_ID INTEGER,
		            NEWS_TITLE VARCHAR (255),
		            NEWS_DESCRIPTION LONGBLOB,
		            PUBLISH_DATE DATETIME NOT NULL,
		            EXPIRY INTEGER NOT NULL,
		            EXPIRY_DATE DATETIME NOT NULL,
    PRIMARY KEY(NEWS_ID)
);

# -----------------------------------------------------------------------
# NOTICE_SEND
# -----------------------------------------------------------------------
drop table if exists NOTICE_SEND;

CREATE TABLE NOTICE_SEND
(
		            NOTICE_ID INTEGER NOT NULL,
		            NOTICE_SUBJECT VARCHAR (255),
		            USER_ID INTEGER,
		            GROUP_ID INTEGER,
		            ROLE_ID INTEGER,
		            POST_TIME DATETIME,
		            EXPIRY INTEGER NOT NULL,
		            EXPIRY_DATE DATETIME NOT NULL,
    PRIMARY KEY(NOTICE_ID)
);

# -----------------------------------------------------------------------
# NOTICE_RECEIVE
# -----------------------------------------------------------------------
drop table if exists NOTICE_RECEIVE;

CREATE TABLE NOTICE_RECEIVE
(
		            NOTICE_ID INTEGER NOT NULL,
		            RECEIVER_ID INTEGER NOT NULL,
		            GROUP_ID INTEGER NOT NULL,
		            READ_FLAG INTEGER,
    PRIMARY KEY(NOTICE_ID,RECEIVER_ID)
);

# -----------------------------------------------------------------------
# SYSTEM
# -----------------------------------------------------------------------
drop table if exists SYSTEM;

CREATE TABLE SYSTEM
(
		            ID INTEGER NOT NULL,
		            CLEAN_DATE DATETIME,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# TASK
# -----------------------------------------------------------------------
drop table if exists TASK;

CREATE TABLE TASK
(
		            TASK_ID INTEGER NOT NULL,
		            USER_ID INTEGER NOT NULL,
		            TITLE VARCHAR (100),
		            STATUS INTEGER (1),
		            START_DATE DATETIME NOT NULL,
		            END_DATE DATETIME NOT NULL,
		            EXPIRY INTEGER NOT NULL,
		            EXPIRY_DATE DATETIME NOT NULL,
    PRIMARY KEY(TASK_ID,START_DATE)
);

# -----------------------------------------------------------------------
# USAGE_DETAILS
# -----------------------------------------------------------------------
drop table if exists USAGE_DETAILS;

CREATE TABLE USAGE_DETAILS
(
		            ENTRY_ID INTEGER NOT NULL,
		            USER_ID INTEGER NOT NULL,
		            LOGIN_TIME DATETIME,
    PRIMARY KEY(ENTRY_ID)
);

# -----------------------------------------------------------------------
# USER_CONFIGURATION
# -----------------------------------------------------------------------
drop table if exists USER_CONFIGURATION;

CREATE TABLE USER_CONFIGURATION
(
		            USER_ID INTEGER NOT NULL,
		            QUESTION_ID INTEGER (2) default 0,
		            ANSWER VARCHAR (50),
		            LIST_CONFIGURATION INTEGER (2) default 10,
		            PHOTO VARCHAR (50),
    PRIMARY KEY(USER_ID)
);

# -----------------------------------------------------------------------
# REMOTE_COURSES
# -----------------------------------------------------------------------
drop table if exists REMOTE_COURSES;

CREATE TABLE REMOTE_COURSES
(
		            SR_NO INTEGER NOT NULL,
		            LOCAL_COURSE_ID VARCHAR (50),
		            REMOTE_COURSE_ID VARCHAR (50),
		            COURSE_SELLER VARCHAR (50),
		            COURSE_PURCHASER VARCHAR (50),
		            INSTITUTE_IP VARCHAR (15),
		            INSTITUTE_NAME VARCHAR (50),
		            EXPIRY_DATE DATETIME,
		            STATUS INTEGER (1),
		            SECRET_KEY VARCHAR (100),
    PRIMARY KEY(SR_NO)
);

# -----------------------------------------------------------------------
# PROXY_USER
# -----------------------------------------------------------------------
drop table if exists PROXY_USER;

CREATE TABLE PROXY_USER
(
		            USERNAME VARCHAR (32) NOT NULL,
		            PASSWORD VARCHAR (32) NOT NULL,
		            IPADDRESS VARCHAR (32) NOT NULL,
		            LECTURE_ID INTEGER (32) NOT NULL,
		            PORT_NO INTEGER NOT NULL,
    PRIMARY KEY(USERNAME)
);
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
