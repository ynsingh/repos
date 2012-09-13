use brihaspati;

drop table if exists COURSE_MODULE;

CREATE TABLE COURSE_MODULE
(
			MODULE_ID INTEGER NOT NULL,
			MODULE_NAME VARCHAR (255) NOT NULL,
                	PRIMARY KEY(MODULE_ID)
) ENGINE=MyISAM;
insert into COURSE_MODULE (module_id, module_name) VALUES (100, 'Assignment');
insert into COURSE_MODULE (module_id, module_name) VALUES (101, 'Backup');
insert into COURSE_MODULE (module_id, module_name) VALUES (102, 'Communication');
insert into COURSE_MODULE (module_id, module_name) VALUES (103, 'CourseCalendar');
insert into COURSE_MODULE (module_id, module_name) VALUES (104, 'CourseManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (105, 'GroupManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (106, 'OnlineExaminationSystem');
insert into COURSE_MODULE (module_id, module_name) VALUES (107, 'InstructorManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (108, 'StudentManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (109, 'MarksUpload');
insert into COURSE_MODULE (module_id, module_name) VALUES (110, 'Wiki');
insert into COURSE_MODULE (module_id, module_name) VALUES (111, 'TrackingReport');
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (161, 'COURSE_MODULE', 100, 1);

