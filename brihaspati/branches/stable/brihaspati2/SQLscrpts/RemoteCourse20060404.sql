use brihaspati;

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

