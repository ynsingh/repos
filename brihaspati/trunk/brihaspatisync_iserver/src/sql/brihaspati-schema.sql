
# -----------------------------------------------------------------------
# NOTE
# -----------------------------------------------------------------------
drop table if exists NOTE;

CREATE TABLE NOTE
(
        NOTE_ID INTEGER NOT NULL,
        POSTED_BY VARCHAR (255),
        DATE_POSTED DATETIME,
        NOTE VARCHAR (255),
    PRIMARY KEY(NOTE_ID)
);

# -----------------------------------------------------------------------
# RDF
# -----------------------------------------------------------------------
drop table if exists RDF;

CREATE TABLE RDF
(
        RDF_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        LASTACCESS DATETIME,
    PRIMARY KEY(GROUP_NAME)
);

# -----------------------------------------------------------------------
# CAL_INFORMATION
# -----------------------------------------------------------------------
drop table if exists CAL_INFORMATION;

CREATE TABLE CAL_INFORMATION
(
        INFO_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        MSG_ID INTEGER NOT NULL AUTO_INCREMENT,
        REPLY_ID INTEGER NOT NULL,
        MSG_SUBJECT VARCHAR (255),
        USER_ID INTEGER,
        GROUP_ID INTEGER,
        POST_TIME DATETIME,
        EXPIRY INTEGER NOT NULL,
        EXPIRY_DATE DATETIME NOT NULL,
        PERMISSION INTEGER (1),
        GRPMGMT_TYPE VARCHAR (255),
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
        WORD_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        ALIAS_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        QUESTION_ID INTEGER NOT NULL AUTO_INCREMENT,
        QUESTION_NAME VARCHAR (255),
    PRIMARY KEY(QUESTION_ID)
);

# -----------------------------------------------------------------------
# MAIL_SEND
# -----------------------------------------------------------------------
drop table if exists MAIL_SEND;

CREATE TABLE MAIL_SEND
(
        MAIL_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        MAIL_TYPE INTEGER,
    PRIMARY KEY(MAIL_ID,RECEIVER_ID)
);

# -----------------------------------------------------------------------
# NEWS
# -----------------------------------------------------------------------
drop table if exists NEWS;

CREATE TABLE NEWS
(
        NEWS_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        NOTICE_ID INTEGER NOT NULL AUTO_INCREMENT,
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
# SYSTEM_CLEANTIME
# -----------------------------------------------------------------------
drop table if exists SYSTEM_CLEANTIME;

CREATE TABLE SYSTEM_CLEANTIME
(
        ID INTEGER NOT NULL AUTO_INCREMENT,
        CLEAN_DATE DATETIME,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# USAGE_DETAILS
# -----------------------------------------------------------------------
drop table if exists USAGE_DETAILS;

CREATE TABLE USAGE_DETAILS
(
        ENTRY_ID INTEGER NOT NULL AUTO_INCREMENT,
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
        TASK_CONFIGURATION INTEGER (2) default 7,
    PRIMARY KEY(USER_ID)
);

# -----------------------------------------------------------------------
# REMOTE_COURSES
# -----------------------------------------------------------------------
drop table if exists REMOTE_COURSES;

CREATE TABLE REMOTE_COURSES
(
        SR_NO INTEGER NOT NULL AUTO_INCREMENT,
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

# -----------------------------------------------------------------------
# SURVEY_QUESTION
# -----------------------------------------------------------------------
drop table if exists SURVEY_QUESTION;

CREATE TABLE SURVEY_QUESTION
(
        SURVEY_ID INTEGER NOT NULL AUTO_INCREMENT,
        INST_ID INTEGER NOT NULL,
        CID VARCHAR (25),
        QUES1 VARCHAR (50),
        QUES2 VARCHAR (50),
        QUES3 VARCHAR (50),
        QUES4 VARCHAR (50),
        QUES5 VARCHAR (50),
        PDATE DATETIME NOT NULL,
    PRIMARY KEY(SURVEY_ID)
);

# -----------------------------------------------------------------------
# SURVEY_STUDENT
# -----------------------------------------------------------------------
drop table if exists SURVEY_STUDENT;

CREATE TABLE SURVEY_STUDENT
(
        SURVEYST_ID INTEGER NOT NULL AUTO_INCREMENT,
        SURVEY_ID INTEGER NOT NULL,
        INST_ID INTEGER NOT NULL,
        CID VARCHAR (25),
        STU_ID VARCHAR (25),
        QUES1 INTEGER (2),
        QUES2 INTEGER (2),
        QUES3 INTEGER (2),
        QUES4 INTEGER (2),
        QUES5 INTEGER (2),
        TOTALMARKS INTEGER,
    PRIMARY KEY(SURVEYST_ID)
);

# -----------------------------------------------------------------------
# SURVEY_RESULT
# -----------------------------------------------------------------------
drop table if exists SURVEY_RESULT;

CREATE TABLE SURVEY_RESULT
(
        RESULT_ID INTEGER NOT NULL AUTO_INCREMENT,
        SURVEY_ID INTEGER NOT NULL,
        CID VARCHAR (25),
        NUM_STU_ATTD INTEGER,
        TOTALMARKS_PER INTEGER,
        GRADE VARCHAR (25),
    PRIMARY KEY(RESULT_ID)
);

# -----------------------------------------------------------------------
# QUIZ
# -----------------------------------------------------------------------
drop table if exists QUIZ;

CREATE TABLE QUIZ
(
        ID INTEGER NOT NULL AUTO_INCREMENT,
        QUIZ_ID VARCHAR (255) NOT NULL,
        USER_ID VARCHAR (255) NOT NULL,
        CID VARCHAR (255),
        QUIZ_TITLE VARCHAR (255) NOT NULL,
        START_TIME TIME NOT NULL,
        END_TIME TIME NOT NULL,
        POST_DATE DATETIME NOT NULL,
        MAX_MARKS INTEGER (3),
        EXPIRY_DATE DATETIME NOT NULL,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# ATTENDENCE_SEET
# -----------------------------------------------------------------------
drop table if exists ATTENDENCE_SEET;

CREATE TABLE ATTENDENCE_SEET
(
        ID INTEGER NOT NULL AUTO_INCREMENT,
        USER_ID INTEGER (11) NOT NULL,
        LOGIN_DATE DATETIME NOT NULL,
        IPADDRESS VARCHAR (16),
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# ASSIGNMENT
# -----------------------------------------------------------------------
drop table if exists ASSIGNMENT;

CREATE TABLE ASSIGNMENT
(
        ASSIGN_ID VARCHAR (32) NOT NULL,
        GROUP_NAME VARCHAR (99) NOT NULL,
        TOPIC_NAME VARCHAR (255) NOT NULL,
        CUR_DATE DATETIME NOT NULL,
        DUE_DATE DATETIME NOT NULL,
        PER_DATE DATETIME NOT NULL,
        GRADE INTEGER (3) NOT NULL,
    PRIMARY KEY(ASSIGN_ID)
);

# -----------------------------------------------------------------------
# LECTURE
# -----------------------------------------------------------------------
drop table if exists LECTURE;

CREATE TABLE LECTURE
(
        LECTUREID INTEGER NOT NULL AUTO_INCREMENT,
        GROUP_NAME VARCHAR (255) NOT NULL,
        LECTURENAME VARCHAR (255) NOT NULL,
        LECTUREINFO VARCHAR (255) NOT NULL,
        URLNAME VARCHAR (255),
        PHONENO VARCHAR (255),
        FORVIDEO VARCHAR (255),
        FORAUDIO VARCHAR (255),
        FORWHITEBOARD VARCHAR (255),
        SESSIONDATE DATETIME NOT NULL,
        SESSIONTIME VARCHAR (255) NOT NULL,
        DURATION VARCHAR (255),
        REPEATLEC VARCHAR (255),
        FORTIME VARCHAR (255),
    PRIMARY KEY(LECTUREID)
);

# -----------------------------------------------------------------------
# SessionAddress
# -----------------------------------------------------------------------
drop table if exists SessionAddress;

CREATE TABLE SessionAddress
(
        SerialNo INTEGER NOT NULL AUTO_INCREMENT,
        MulticastAddress VARCHAR (255),
        Flag INTEGER,
    PRIMARY KEY(SerialNo)
);
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
