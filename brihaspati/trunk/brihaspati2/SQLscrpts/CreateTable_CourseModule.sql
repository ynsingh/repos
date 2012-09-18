use brihaspati;

drop table if exists COURSE_MODULE;

CREATE TABLE COURSE_MODULE
(
			MODULE_ID INTEGER NOT NULL,
			MODULE_NAME VARCHAR (255) NOT NULL,
                	PRIMARY KEY(MODULE_ID)
);
insert into COURSE_MODULE (module_id, module_name) VALUES (1, 'Assignment');
insert into COURSE_MODULE (module_id, module_name) VALUES (2, 'Backup');
insert into COURSE_MODULE (module_id, module_name) VALUES (3, 'Communication');
insert into COURSE_MODULE (module_id, module_name) VALUES (4, 'CourseCalendar');
insert into COURSE_MODULE (module_id, module_name) VALUES (5, 'CourseManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (6, 'GroupManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (7, 'OnlineExaminationSystem');
insert into COURSE_MODULE (module_id, module_name) VALUES (8, 'InstructorManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (9, 'StudentManagement');
insert into COURSE_MODULE (module_id, module_name) VALUES (10, 'MarksUpload');
insert into COURSE_MODULE (module_id, module_name) VALUES (11, 'Wiki');
insert into COURSE_MODULE (module_id, module_name) VALUES (12, 'TrackingReport');
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (161, 'COURSE_MODULE', 100, 1);

