use brihaspati;

alter table SURVEY_QUESTION modify CID varchar(255);
alter table SURVEY_QUESTION modify QUES1 varchar(255);
alter table SURVEY_QUESTION modify QUES2 varchar(255);
alter table SURVEY_QUESTION modify QUES3 varchar(255);
alter table SURVEY_QUESTION modify QUES4 varchar(255);
alter table SURVEY_QUESTION modify QUES5 varchar(255);

alter table SURVEY_RESULT modify CID varchar(255);

alter table SURVEY_STUDENT modify CID varchar(255);

alter table Learner_sco modify course_id varchar(255);

alter table REMOTE_COURSES modify LOCAL_COURSE_ID varchar(255);
alter table REMOTE_COURSES modify REMOTE_COURSE_ID varchar(255);
