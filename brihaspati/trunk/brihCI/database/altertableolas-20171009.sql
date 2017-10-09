use olas;

alter table Department modify column dept_schoolname varchar(255) default NULL;
alter table Department modify column dept_schoolcode varchar(255) default NULL;

alter table study_center modify column sc_startdate varchar(25) default NULL;
alter table study_center modify column sc_closedate varchar(25) default NULL;
alter table study_center modify column sc_mobile varchar(25) default NULL;
alter table study_center modify column sc_status varchar(25) default NULL;
alter table study_center modify column sc_address varchar(255) default NULL;
alter table study_center modify column sc_country varchar(255) default NULL;
alter table study_center modify column sc_state varchar(255) default NULL;
alter table study_center modify column sc_city varchar(255) default NULL;
alter table study_center modify column sc_district varchar(255) default NULL;
alter table study_center modify column sc_pincode varchar(10) default NULL;
alter table study_center modify column sc_phone varchar(255) default NULL;
alter table study_center modify column sc_fax varchar(255) default NULL;
alter table study_center modify column sc_website varchar(255) default NULL;
alter table study_center modify column sc_incharge varchar(255) default NULL;

