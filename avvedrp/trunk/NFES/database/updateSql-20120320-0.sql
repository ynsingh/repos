INSERT INTO staff_profile_report_v0_items (number,name,description,prompt,itemtype)VALUES(345,'professional_details','Professional Details','Professional Details','personal_details_childform');
ALTER TABLE staff_profile_report_v0_values ADD COLUMN professional_details VARCHAR(255) ;
UPDATE staff_profile_report_v0_values SET professional_details="";

UPDATE staff_profile_report_v0_items SET itemtype='tableColHeader' WHERE name='personal_details';

INSERT INTO staff_profile_report_v0_items (number,name,description,prompt,itemtype)VALUES(355,'contact_details','Qualification','Contact Details','tableColHeader');


ALTER TABLE staff_profile_report_v0_values ADD COLUMN contact_details VARCHAR(255) ;
UPDATE staff_profile_report_v0_values SET contact_details="";
INSERT INTO staff_profile_report_v0_items (number,name,description,prompt,itemtype)VALUES(360,'file_upload','Qualification','File Upload','tableColHeader');


ALTER TABLE staff_profile_report_v0_values ADD COLUMN file_upload VARCHAR(255) ;
UPDATE staff_profile_report_v0_values SET file_upload="";
drop table search_result_fields;

INSERT INTO staff_profile_qualification_v0_items
(number, name, description, creator, time, prompt,
itemtype)
VALUES('50','upload','Upload File','admin',
'2011-08-27 14:08:04','Upload File','file_upload');

INSERT INTO staff_profile_qualification_v0_itemtypes
(name, description, creator, time, action, choice, code, responsetype)
VALUES('file_upload','None','oio','2011-08-27 14:08:04','file_upload','','',NULL);


ALTER TABLE staff_profile_qualification_v0_values
ADD(upload TEXT);

UPDATE staff_profile_qualification_v0_values
SET upload="";

UPDATE staff_profile_report_v0_items
SET NAME='personal_details_tab_capion' WHERE
NAME='personal_details';

ALTER TABLE staff_profile_report_v0_values     CHANGE
personal_details personal_details_tab_capion VARCHAR(255) ;

UPDATE staff_profile_report_v0_items
SET NAME='personal_details' WHERE
NAME='professional_details';

ALTER TABLE
staff_profile_report_v0_values     CHANGE
professional_details personal_details VARCHAR(255) ;

CREATE TABLE staff_profile_xml (
  id int(11) NOT NULL auto_increment,
  table_name varchar(255) default NULL,
  table_fields text,
  active_yesno char(1) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into staff_profile_xml(id,table_name,table_fields,active_yesno) values (1,'staff_profile_report_v0_values','office_room_num,office_phone_ext,email_id,personal_native_place,personal_native_country,personal_native_state,personal_native_district,personal_community,personal_religion,personal_caste,personal_mother_tongue,personal_marital_status,dept_pan_card_no,dept_passport_no,account_passport_valid_upto,health_blood_group,email,home_phone,fax,pmt_Address1,pmt_Address2,pmt_City,pmt_District,pmt_state,pmt_country,pmt_pincode,pmt_phone,comm_Address1,comm_Address2,comm_City,comm_District,comm_state,comm_country,comm_pincode,comm_phone,upload_resume,upload_photo','1'),(2,'staff_profile_personal_details_v0_values','focus_area,research_interests,teaching_interests,positions_held','1'),(3,'staff_profile_qualification_v0_values','course_name,institution,location,board,join_month,join_year,passing_year,percentage,class','1'),(4,'staff_profile_awards_v0_values','award_name,receiving_month_year,agency_name,url,remarks,upload_image','1'),(5,'staff_profile_journal_papers_v0_values','journal_type,research_expertise_area,paper_associated_project,paper_title,author1,journal_name,volume,issue_no,month_year,project_name,author2,abstract,pages,impact_factor,average_citation_index,indexed_in_scopus,language,affiliation,url,keyword,other_details,upload_paper','1'),(6,'staff_profile_conference_papers_v0_values','conference_type,research_expertise_area,paper_associated_project,paper_title,author1,project_name,author2,month_year,abstract,pages,conference_name,date_from,date_to,conference_venue,organizer_name,language,affiliation,url,keyword,other_details,upload_paper','1'),(7,'staff_profile_books_chapter_v0_values','book_chapter,title,author1,publication_month_year,role,author2,publisher,isbn,no_of_pages,language,affiliation,url,other_details,upload','1'),(8,'staff_profile_talks_lectures_v0_values','institution_address,event_name,lecture_topic,talk_date_time,event,participants_level,related_research_group,duration_from,duration_to,url,other_details,upload','1'),(9,'staff_profile_training_v0_values','training_name,duration_from,duration_to,location,purpose,paper_title,author,research_expertise_area,abstract,funding_source,funded_amount,url,other_details,upload','1'),(10,'staff_profile_projects_v0_values','project_status,project_title,principal_investigator,start_date,duration,amount,project_center_department,abstract,co_pi,funding_source,url,other_details,upload','1'),(11,'staff_profile_patents_v0_values','choose_patent,patent_title,registration_month_year,country,name_co_applicant,patent_details,affiliation,url,other_details,upload','1'),(12,'staff_profile_media_publication_v0_values','media_type,title,magazine_name,article_month_year,url,other_details,upload','1'),(13,'staff_profile_thesis_v0_values','choose,thesis_title,thesis_author,thesis_department_institution,thesis_course_programe,thesis_external_internal,thesis_registration_month_year,thesis_outcome,thesis_completion_month_year,thesis_url,thesis_other_details,thesis_upload,project_title,project_student_group,project_student_name,project_type,project_start_month_year,project_academic_term,project_outcome,project_completion_month_year,project_url,project_other_details,project_upload','1'),(14,'staff_profile_faculty_exchange_v0_values','institution_name,subjects_taught,duration_from,duration_to,ug_or_pg,research_collaborations,url,other_details,upload','1'),(15,'staff_profile_professional_body_v0_values','professional_body_name,membership_from,membership_to,membership_type,membership_number,url,other_details,upload','1'),(16,'staff_profile_consultancy_offered_v0_values','client_name,duration_from,duration_to,other_faculty_name,revenue_generated,summary_work_undertaken,labs_used,url,other_details,upload','1'),(17,'staff_profile_governance_v0_values','committee_name,school_name,service_dates_from,service_dates_to,responsibilities,url,other_details,upload','1'),(18,'staff_profile_review_committees_v0_values','committee_type,role_played,title,appointed_month_year,url,other_details,file_upload','1'),(19,'staff_profile_community_service_v0_values','event_name,location,activity_month_year,url,other_details,upload_file','1');

ALTER TABLE users ADD COLUMN(title VARCHAR(15),last_name VARCHAR(255));

/*======set the title values in general master ===========*/
INSERT INTO general_master (category,  fld_value, active_yes_no, description) VALUES('Title','Mr.','1',NULL);
INSERT INTO general_master (category,  fld_value, active_yes_no, description) VALUES('Title','Mrs.','1',NULL);
INSERT INTO general_master (category,  fld_value, active_yes_no, description) VALUES('Title','Prof.','1',NULL);

UPDATE users SET title="" , last_name="";

