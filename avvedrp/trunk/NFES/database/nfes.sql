/*
SQLyog Community v8.81 
MySQL - 5.0.37-community-nt : Database - nfes
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nfes` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nfes`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values ('admin','1');

/*Table structure for table `course_taught` */

DROP TABLE IF EXISTS `course_taught`;

CREATE TABLE `course_taught` (
  `idf` int(11) NOT NULL,
  `id` bigint(20) NOT NULL auto_increment,
  `academic_term` varchar(255) default NULL,
  `class_name` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `students_registered` varchar(255) NOT NULL,
  `percent_of_pass` varchar(255) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `course_taught` */

/*Table structure for table `entity_document_master` */

DROP TABLE IF EXISTS `entity_document_master`;

CREATE TABLE `entity_document_master` (
  `id` int(11) NOT NULL default '0',
  `entity_id` int(11) default NULL,
  `entity_type` varchar(50) default NULL,
  `form_id` int(11) default NULL,
  `document_id` int(11) default NULL,
  `number` int(11) default NULL,
  `amended_yesno` smallint(1) default '0',
  `amended_document_id` int(11) default NULL,
  `addendum_yesno` smallint(1) default '0',
  `addendum_document_id` int(11) default NULL,
  `approved_yesno` char(1) default NULL,
  `active_yesno` smallint(1) default '1',
  `status_id` int(11) default '0',
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date_time` datetime default NULL,
  `created_by` varchar(50) default NULL,
  `created_date_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_entity_document_mastervalues_doc_id` (`document_id`),
  KEY `idx_entity_document_masterentity_id` (`entity_id`),
  KEY `idx_entity_document_masterform_master_id` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `entity_document_master` */

/*Table structure for table `file_master` */

DROP TABLE IF EXISTS `file_master`;

CREATE TABLE `file_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `file_master` */

insert  into `file_master`(`id`,`name`,`active_yes_no`) values (1,'login.jsp','0'),(2,'staff_profile_awards_v0.xml','1'),(3,'staff_profile_books_chapter_v0.xml','1'),(4,'staff_profile_conference_papers_v0.xml','1'),(5,'staff_profile_community_service_v0.xml','1'),(6,'staff_profile_consultancy_offered_v0.xml','1'),(7,'staff_profile_faculty_exchange_v0.xml','1'),(8,'staff_profile_governance_v0.xml','1'),(9,'staff_profile_journal_papers_v0.xml','1'),(10,'staff_profile_media_publication_v0.xml','1'),(11,'staff_profile_patents_v0.xml','1'),(12,'staff_profile_personal_details_v0.xml','1'),(13,'staff_profile_professional_body_v0.xml','1'),(14,'staff_profile_projects_v0.xml','1'),(15,'staff_profile_qualification_v0.xml','1'),(16,'staff_profile_report_v0.xml','1'),(17,'staff_profile_review_committees_v0.xml','1'),(18,'staff_profile_talks_lectures_v0.xml','1'),(19,'staff_profile_thesis_v0.xml','1'),(20,'staff_profile_training_v0.xml','1');

/*Table structure for table `general_master` */

DROP TABLE IF EXISTS `general_master`;

CREATE TABLE `general_master` (
  `category` varchar(25) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `fld_value` varchar(100) default NULL,
  `active_yes_no` varchar(1) default NULL,
  `description` varchar(255) default NULL,
  UNIQUE KEY `language_Id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `general_master` */

insert  into `general_master`(`category`,`id`,`fld_value`,`active_yes_no`,`description`) values ('Language',1,'English','1',NULL),('Language',2,'Hindi','1',NULL),('Language',3,'Malayalam','1',NULL),('Language',4,'Tamil','1',NULL),('Country',5,'India','1',NULL),('Country',6,'Pakistan','1',NULL),('Country',7,'USA','1',NULL),('Language',8,'Telungu','1',NULL),('Country',9,'UK','1',NULL),('Media',10,'Magazines','1',NULL),('Media',11,'Newspapers','1',NULL),('Media',12,'Websites ','1',NULL),('Membership',13,'Regular','1',NULL),('Membership',14,'Associate','1',NULL),('Membership',15,'Fellow','1',NULL),('Membership',16,'Life','1',NULL),('Membership',18,'Others','1',NULL),('Specialization',19,'Operating System','1',NULL),('Specialization',20,'System Software','1',NULL),('Journal',25,'ACST','1',NULL),('Journal',26,'JCSA','1',NULL),('Journal',27,'IJICIT','1',NULL),('Journal',28,'IJCIR','1',NULL),('Affiliation',29,'Amrita','1',NULL),('Affiliation',32,'Others','1',NULL),('Conference_Name',33,'11th Anual Conference on Secure Communication','1',NULL),('Conference_Name',34,' National Conference on Computational Neuroscience NCCNS-2011','1',NULL),('Professional_Body_Name',36,'NCERT','1',NULL),('Professional_Body_Name',37,'IIST','1',NULL),('Professional_Body_Name',38,'ISRO','1',NULL),('Professional_Body_Name',39,'SCERT','1',NULL),('Consultancy_Facilities',40,'CDAC Lab','1',NULL),('Consultancy_Facilities',41,'IIT Kanpur Lab','1',NULL),('Committee_Type',42,'Journal','1',NULL),('Role_Played',43,'Director','1',NULL),('Participants_Level',44,'UG/PG Students','1',NULL),('Participants_Level',45,'Faculty','1',NULL),('Participants_Level',46,'Industry Employees ','1',NULL),('Funding_Source',47,'Amrita','1',NULL),('Funding_Source',48,'Others','1',NULL),('Committee_Type',49,'Conference','1',NULL),('Committee_Type',50,'Project','1',NULL),('Role_Played',51,'Member','1',NULL),('Role_Played',52,'Editor','1',NULL),('Role_Played',53,'Reviewer','1',NULL),('Role_Played',54,'Chairperson','1',NULL),('Role',55,'Author','1',NULL),('Role',56,'Co-Author','1',NULL),('Role',57,'Contributed chapter(s)','1',NULL),('Role',58,'Compiled and Edited a book but not an author','1',NULL),('Attending_Purpose',59,'Presented Paper','1',NULL),('Attending_Purpose',60,'Session Chair','1',NULL),('Attending_Purpose',61,'Trainer','1',NULL),('Attending_Purpose',62,'Participation','1',NULL),('Department',63,'Computer Science','1',NULL),('Department',64,'Physics','1',NULL),('Department',65,'Electronics','1',NULL),('Project_Type',66,'Industry','1',NULL),('Project_Type',67,'In-house','1',NULL),('Project_Type',68,'External','1',NULL),('Academic_Term',69,'2009-2010','1',NULL),('Academic_Term',70,'2010-2011','1',NULL),('Academic_Term',81,'2008-2009','1',NULL),('Academic_Term',82,'2007-2008','1',NULL),('Specialization',87,'Quantitative Techniques and Operations Management','1',NULL),('Journal',88,'REVIEW OF BUSINESS RESEARCH','1',NULL),('Specialization',89,'Economics','1',NULL),('Journal',90,'INDERSCINECE JOURNALS','1',NULL),('Specialization',91,'Marketing','1',NULL),('Journal',92,'VISHLESHAN','1',NULL),('Journal',93,'INDIAN ECONOMIC REVIEW','1',NULL),('Conference_Name',94,'International Conference on Knowledge Management and Intellectual Capital-2009','1',NULL),('Conference_Name',95,'12th Asia Pacific Management Conference','1',NULL),('Conference_Name',96,'2007 International Conference on Enterprise Information Systems and Web Technologies','1',NULL),('Department',97,'General Management','1',NULL),('Department',98,'Economics ','1',NULL),('Conference_Name',99,'Academic Affairs Advisory Committee','1',NULL),('Conference_Name',100,'Budjet Study Committee','1',NULL),('Conference_Name',101,'Administration Evaluation Committee','1',NULL),('Committee_Type',102,'Others','1',NULL),('Conference_Name',103,'Tenure Committee ','1',NULL),('Specialization',104,'Human Resource Development','1',NULL),('Specialization',105,'Human Resource Planning','1',NULL),('Specialization',106,'Technology Management','1',NULL),('Specialization',107,'Organisational Behaviour','1',NULL),('Specialization',108,'Networking','1',NULL),('Specialization',109,'Embeded Systems','1',NULL),('Specialization',110,'Software Developement','1',NULL);

/*Table structure for table `institution_master` */

DROP TABLE IF EXISTS `institution_master`;

CREATE TABLE `institution_master` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `short_name` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `university_id` int(11) default NULL,
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `institution_master` */

insert  into `institution_master`(`id`,`name`,`address`,`short_name`,`location`,`university_id`,`active_yes_no`) values (1,'Amrita Institute of Medical Science','Ponekkara','AIMS','Kochi',1,'1'),(2,'Model Engineering College','Thrikkakakra','MEC','Ernakulam-21 ',4,'1');

/*Table structure for table `investigator_master` */

DROP TABLE IF EXISTS `investigator_master`;

CREATE TABLE `investigator_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `department_id` bigint(20) default NULL,
  `designation` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `active_yes_no` char(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `investigator_master` */

insert  into `investigator_master`(`id`,`name`,`department_id`,`designation`,`email`,`address`,`active_yes_no`) values (1,'Anil Sharma',63,'Faculty','anil@gmail.com','IIT Kanpur','1'),(2,'Sathish Kumar',64,'Faculty','satish.k@yahoo.com','NIT Calicut','1'),(3,'Rajesh Sinha',63,'Faculty','raj@amrita.edu','CUSAT','1'),(5,'Manu Sharma',64,'Faculty','manu@amrita.edu','AVVP','1'),(6,'Saju',65,'Faculty','saju@amrita.edu','AVVP','1');

/*Table structure for table `language_localisation` */

DROP TABLE IF EXISTS `language_localisation`;

CREATE TABLE `language_localisation` (
  `id` bigint(20) NOT NULL auto_increment,
  `file_code` varchar(4) collate utf8_unicode_ci default NULL,
  `control_name` varchar(255) collate utf8_unicode_ci default NULL,
  `language_code` varchar(3) collate utf8_unicode_ci default NULL,
  `language_string` varchar(255) collate utf8_unicode_ci default NULL,
  `active_yes_no` char(1) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `language_localisation` */

insert  into `language_localisation`(`id`,`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) values (1,'1','login','ml','?????? ????????????','1'),(2,'1','login','en','Login','1'),(3,'1','username','ml','??????????:','1'),(4,'1','password','ml','???????????:','1'),(5,'1','username','en','User Name:','1'),(6,'1','password','en','Password:','1'),(7,'1','lost_password','ml','??????????? ?????? ?','1'),(8,'1','lost_password','en','Forgot Password?','1'),(9,'1','login_msg','ml','?????? ?????????????? ????????? ????? ??????? ! ','1'),(10,'1','login_msg','en','Please enter user information !','1'),(11,'1','language','ml','??? :','1'),(12,'1','language','en','Language :','1'),(13,'1','error_msg1','ml','?????? ?????????? ????????? !','1'),(14,'1','error_msg1','en','Please correct the errors !','1'),(15,'1','error_msg2','ml','??????? ?????????? !','1'),(16,'1','error_msg2','en','Sorry, could not find a user with this username and password.','1'),(17,'1','error_msg3','ml','??????? ??????????? !','1'),(18,'1','error_msg3','en','Wrong password !','1'),(19,'2','award_name','en','Name of Award<span style=\"color:red\">*</span>|Award<span style=\"color:red\">*</span>','1'),(20,'2','agency_name','en','Name of Agency|Agency','1'),(21,'2','receiving_month_year','en','Month and Year of Receiving<span style=\"color:red\">*</span>|Rec. Month And Year<span style=\"color:red\">*</span>','1'),(22,'2','url','en','URL','1'),(23,'2','remarks','en','Remarks','1'),(24,'2','upload_image','en','Upload Image','1'),(25,'3','book_chapter','en','Book/\"Chapter\"<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','1'),(26,'3','role','en','Role','1'),(27,'3','title','en','Title<span style=\"color:red\">*</span>','1'),(28,'3','author1','en','Author<span style=\"color:red\">*</span>','1'),(29,'3','author2','en','Authors','1'),(30,'3','publisher','en','Publisher','1'),(31,'3','affiliation','en','Affiliation','1'),(32,'3','url','en','URL if any','1'),(33,'3','other_details','en','Other Details','1'),(34,'3','upload','en','Upload File','1'),(35,'3','isbn','en','ISBN','1'),(36,'3','publication_month_year','en','Month and Year of Publication<span style=\"color:red\">*</span>|Month And Year<span style=\"color:red\">*</span>','1'),(37,'3','no_of_pages','en','No of Pages','1'),(38,'3','language','en','Language','1'),(39,'4','conference_type','en','Conference Type<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','1'),(40,'4','research_expertise_area','en','Research Expertise Area<span style=\"color:red\">*</span>|Research Area<span style=\"color:red\">*</span>','1'),(41,'4','paper_associated_project','en','Is the paper associated with a project<span style=\"color:red\">*</span>|Project Name<span style=\"color:red\">*</span>','1'),(42,'4','project_name','en','If Yes, Choose Project Name','1'),(43,'4','paper_title','en','Title of paper<span style=\"color:red\">*</span>|Paper Title<span style=\"color:red\">*</span>','1'),(44,'4','author1','en','Author<span style=\"color:red\">*</span>','1'),(45,'4','affiliation','en','Affiliation','1'),(46,'4','url','en','URL if any','1'),(47,'4','other_details','en','Other Details','1'),(48,'4','upload_paper','en','Upload File','1'),(49,'4','keyword','en','Keyword','1'),(50,'4','author2','en','Authors','1'),(51,'4','month_year','en','Month and Year','1'),(52,'4','abstract','en','Abstract','1'),(53,'4','pages','en','Pages','1'),(54,'4','conference_name','en','Name of Conference','1'),(55,'4','date_from','en','Conference Dates From','1'),(56,'4','date_to','en','Conference Dates To','1'),(57,'4','conference_venue','en','Conference Venue','1'),(58,'4','organizer_name','en','Name of Organizer','1'),(59,'4','language','en','Language','1'),(60,'5','event_name','en','Name of Event<span style=\"color:red\">*</span>|Event<span style=\"color:red\">*</span>','1'),(61,'5','location','en','Location<span style=\"color:red\">*</span>','1'),(62,'5','activity_month_year','en','Month Year of Activity<span style=\"color:red\">*</span>|Month And Year<span style=\"color:red\">*</span>','1'),(63,'5','url','en','URL','1'),(64,'5','other_details','en','Other_Details','1'),(65,'5','upload_file','en','Upload File','1'),(66,'6','client_name','en','Name of Client<span style=\"color:red\">*</span>|Client<span style=\"color:red\">*</span>','1'),(67,'6','duration_from','en','Duration From<span style=\"color:red\">*</span>','1'),(68,'6','duration_to','en','Duration To<span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','1'),(69,'6','other_faculty_name','en','If other faculty involved,mention name(s) ','1'),(70,'6','revenue_generated','en','Revenue Generated','1'),(71,'6','summary_work_undertaken','en','Brief Summary of Work Undertaken','1'),(72,'6','labs_used','en','Facilities used for Consultancy','1'),(73,'6','url','en','URL if any','1'),(74,'6','other_details','en','Other Details','1'),(75,'6','upload','en','Upload File','1'),(76,'7','institution_name','en','Name of Institutions Involved<span style=\"color:red\">*</span>|Institutions Involved<span style=\"color:red\">*</span>','1'),(77,'7','subjects_taught','en','Name of Subjects Taught<span style=\"color:red\">*</span>|Subjects Taught<span style=\"color:red\">*</span>','1'),(78,'7','duration_from','en','Duration From<span style=\"color:red\">*</span>','1'),(79,'7','duration_to','en','Duration To<span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','1'),(80,'7','ug_or_pg','en','UG or PG','1'),(81,'7','research_collaborations','en','Research Collaborations if any','1'),(82,'7','url','en','URL if any','1'),(83,'7','other_details','en','Other Details','1'),(84,'7','upload','en','Upload File','1'),(85,'8','committee_name','en','Name of Committee/Panel<span style=\"color:red\">*</span>|Committee/Panel<span style=\"color:red\">*</span>','1'),(86,'8','school_name','en','Name of School/University<span style=\"color:red\">*</span>|School/University<span style=\"color:red\">*</span>','1'),(87,'8','service_dates_from','en','Service Dates From <span style=\"color:red\">*</span>|Service Dates From<span style=\"color:red\">*</span>','1'),(88,'8','service_dates_to','en','Service Dates To <span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','1'),(89,'8','responsibilities','en','Responsibilities','1'),(90,'8','url','en','URL if any','1'),(91,'8','upload','en','Upload File','1'),(92,'8','other_details','en','Other Details','1'),(93,'9','journal_type','en','Publication Type<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','1'),(94,'9','research_expertise_area','en','Research Expertise Area<span style=\"color:red\">*</span>|Research Area<span style=\"color:red\">*</span>','1'),(95,'9','paper_associated_project','en','Is the paper associated with a project<span style=\"color:red\">*</span>|Assoc. with a prj<span style=\"color:red\">*</span>','1'),(96,'9','project_name','en','If Yes, Choose Project Name','1'),(97,'9','paper_title','en','Title of paper<span style=\"color:red\">*</span>|Title<span style=\"color:red\">*</span>','1'),(98,'9','author1','en','Author<span style=\"color:red\">*</span>','1'),(99,'9','affiliation','en','Affiliation','1'),(100,'9','url','en','URL if any','1'),(101,'9','other_details','en','Other Details','1'),(102,'9','upload_paper','en','Upload File','1'),(103,'9','keyword','en','Keyword','1'),(104,'9','author2','en','Authors','1'),(105,'9','journal_name','en','Publication Name<span style=\"color:red\">*</span>|Name<span style=\"color:red\">*</span>','1'),(106,'9','volume','en','Volume<span style=\"color:red\">*</span>','1'),(107,'9','issue_no','en','Issue No<span style=\"color:red\">*</span>','1'),(108,'9','month_year','en','Month and Year<span style=\"color:red\">*</span>','1'),(109,'9','abstract','en','Abstract','1'),(110,'9','pages','en','Pages','1'),(111,'9','impact_factor','en','Impact Factor','1'),(112,'9','average_citation_index','en','Average Citation Index','1'),(113,'9','language','en','Language','1'),(114,'9','indexed_in_scopus','en','Whether Indexed in Scopus?(Y/N)','1'),(115,'10','media_type','en','Type of Media<span style=\"color:red\">*</span>|Media Type<span style=\"color:red\">*</span>','1'),(116,'10','title','en','Title<span style=\"color:red\">*</span>','1'),(117,'10','magazine_name','en','Name of Magazine/Media<span \nstyle=\"color:red\">*</span>|Magazine Name<span style=\"color:red\">*</span>','1'),(118,'10','article_month_year','en','Month and Year of appearance of article<span style=\"color:red\">*</span>|Month and Year<span style=\"color:red\">*</span>','1'),(119,'10','url','en','URL if any','1'),(120,'10','other_details','en','Other Details','1'),(121,'10','upload','en','Upload File','1'),(122,'11','choose_patent','en','Choose if patent is \"National\" or \"International\"<span style=\"color:red\">*</span>|Patent<span style=\"color:red\">*</span>','1'),(123,'11','country','en','if International, pick country','1'),(124,'11','patent_title','en','Title of patent<span style=\"color:red\">*</span>|Patent Title<span style=\"color:red\">*</span>','1'),(125,'11','registration_month_year','en','Month and Year of patent registration<span style=\"color:red\">*</span>|Reg Month Year<span style=\"color:red\">*</span>','1'),(126,'11','name_co_applicant','en','Co-Applicant(s) Name(if any)','1'),(127,'11','patent_details','en','Patent Details','1'),(128,'11','affiliation','en','Affiliation','1'),(129,'11','url','en','URL if any','1'),(130,'11','other_details','en','Other Details','1'),(131,'11','upload','en','Upload File','1'),(132,'12','focus_area','en','Area of Study / Focus Area<span style=\"color:red\">*</span>|Focus Area<span style=\"color:red\">*</span>','1'),(133,'12','research_interests','en','Research Interests<span style=\"color:red\">*</span>|Research Interests<span style=\"color:red\">*</span>','1'),(134,'12','teaching_interests','en','Teaching Interests','1'),(135,'12','positions_held','en','Positions Held','1'),(136,'13','professional_body_name','en','Name/Details of The Professional Body/Society<span style=\"color:red\">*</span>|Name of Professional Body<span style=\"color:red\">*</span>','1'),(137,'13','membership_from','en','Membership From<span style=\"color:red\">*</span>','1'),(138,'13','membership_to','en','Membership To<span style=\"color:red\">*</span>','1'),(139,'13','membership_type','en','Membership Type','1'),(140,'13','membership_number','en','Membership Number','1'),(141,'13','other_details','en','Other Details','1'),(142,'13','upload','en','Upload File','1'),(143,'13','url','en','URL if any','1'),(144,'14','project_status','en','Project Status<span style=\"color:red\">*</span>|Status<span style=\"color:red\">*</span>','1'),(145,'14','project_title','en','Project Title<span style=\"color:red\">*</span>|Title<span style=\"color:red\">*</span>','1'),(146,'14','principal_investigator','en','Principal Investigator(PI)<span style=\"color:red\">*</span>|PI<span style=\"color:red\">*</span>','1'),(147,'14','start_date','en','Project Start Date<span style=\"color:red\">*</span>|Start Date<span style=\"color:red\">*</span>','1'),(148,'14','duration','en','Duration<span style=\"color:red\">*</span>','1'),(149,'14','amount','en','Amount<span style=\"color:red\">*</span>','1'),(150,'14','project_center_department','en','Which Center/Department the project is associated with<span style=\"color:red\">*</span>|Center/Dept.<span style=\"color:red\">*</span>','1'),(151,'14','abstract','en','Abstract<span style=\"color:red\">*</span>','1'),(152,'14','funding_source','en','Source of Funding','1'),(153,'14','url','en','URL if any','1'),(154,'14','other_details','en','Other Details','1'),(155,'14','upload','en','Upload File','1'),(156,'14','co_pi','en','Co-PI(s)','1'),(157,'15','course_name','en','Qualification<span style=\"color:red\">*</span>','1'),(158,'15','institution','en','Institution<span style=\"color:red\">*</span>','1'),(159,'15','location','en','Location<span style=\"color:red\">*</span>','1'),(160,'15','board','en','Board<span style=\"color:red\">*</span>','1'),(161,'15','join_month','en','Month<span style=\"color:red\">*</span>','1'),(162,'15','join_year','en','Year<span style=\"color:red\">*</span>','1'),(163,'15','passing_year','en','Passing Year','1'),(164,'15','percentage','en','Percentage','1'),(165,'15','class','en','Class','1'),(166,'16','upload_resume','en','Upload Resume','1'),(167,'16','focus_area','en','Area of Study / Focus Area<span style=\"color:red\">*</span>','1'),(168,'16','qualification','en','Qualification','1'),(169,'16','research_interests','en','Research Interests','1'),(170,'16','teaching_interests','en','Teaching Interests','1'),(171,'16','positions_held','en','Positions Held','1'),(172,'16','office_room_num','en','Office Room Number<span style=\"color:red\">*</span>','1'),(173,'16','office_phone_ext','en','Office Phone Ext.<span style=\"color:red\">*</span>','1'),(174,'16','email_id','en','Email ID<span style=\"color:red\">*</span>','1'),(175,'16','email','en','Personal Email ID','1'),(176,'16','home_phone','en','Home Phone','1'),(177,'16','fax','en','Fax','1'),(178,'16','awards','en','Awards Details','1'),(179,'16','community_service','en','Community Service Details','1'),(180,'16','review_committees','en','Review Committees Details','1'),(181,'16','governance','en','Governance Details','1'),(182,'16','consultancy_offered','en','Consultancy Offered Details','1'),(183,'16','professional_body','en','Professional Bodies/Societies Details','1'),(184,'16','faculty_exchange','en','Faculty Exchange Program Details','1'),(185,'16','thesis','en','Thesis/Student Project Details','1'),(186,'16','media_publication','en','Media Publication Details','1'),(187,'16','patents','en','Patents Details','1'),(188,'16','projects','en','Projects Details','1'),(189,'16','training','en','Seminars/Conferences/Workshops/Training Attended Details','1'),(190,'16','talks_lectures','en','Invited Talks/Guest Lectures Details','1'),(191,'16','books_chapter','en','Books/Chapter Details','1'),(192,'16','conference_papers','en','Conference Papers Details','1'),(193,'16','journal_papers','en','Journal Papers Details','1'),(194,'16','personal_details','en','Personal Details','1'),(195,'16','upload_photo','en','Upload Photo','1'),(196,'16','academic_term','en','Choose Academic term','1'),(197,'17','committee_type','en','Commitee Type<span style=\"color:red\">*</span>','1'),(198,'17','role_played','en','Role Played<span style=\"color:red\">*</span>','1'),(199,'17','title','en','Title<span style=\"color:red\">*</span>','1'),(200,'17','url','en','URL','1'),(201,'17','other_details','en','Other Details','1'),(202,'17','file_upload','en','Upload File','1'),(203,'17','appointed_month_year','en','Appointed Month and Year<span style=\"color:red\">*</span>','1'),(204,'18','event','en','Was the event :\"National\" or \"International\"','1'),(205,'18','institution_address','en','Name and Address of the Institution<span style=\"color:red\">*</span>','1'),(206,'18','event_name','en','Name of the event<span style=\"color:red\">*</span>','1'),(207,'18','lecture_topic','en','Lecture Topic<span style=\"color:red\">*</span>','1'),(208,'18','talk_date_time','en','Date and Time of Talk<span style=\"color:red\">*</span>','1'),(209,'18','participants_level','en','Participants Level','1'),(210,'18','related_research_group','en','Related Research Group and Centres','1'),(211,'18','url','en','URL if any','1'),(212,'18','other_details','en','Other Details','1'),(213,'18','upload','en','Upload File','1'),(214,'18','duration_from','en','Duration From','1'),(215,'18','duration_to','en','Duration To','1'),(216,'19','choose','en','Choose \"Thesis\" or \"Student Project\"<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','1'),(217,'19','thesis_title','en','Title of Thesis<span style=\"color:red\">*</span>','1'),(218,'19','thesis_author','en','Author<span style=\"color:red\">*</span>','1'),(219,'19','thesis_department_institution','en','Department and Institution<span style=\"color:red\">*</span>','1'),(220,'19','thesis_course_programe','en','Course/Programe<span style=\"color:red\">*</span>','1'),(221,'19','thesis_external_internal','en','External or Internal<span style=\"color:red\">*</span>','1'),(222,'19','thesis_registration_month_year','en','Month and Year of Registration<span style=\"color:red\">*</span>','1'),(223,'19','thesis_outcome','en','Outcome','1'),(224,'19','thesis_completion_month_year','en','Month and Year of Completion','1'),(225,'19','thesis_url','en','URL if any','1'),(226,'19','thesis_other_details','en','Other Details','1'),(227,'19','thesis_upload','en','Upload File','1'),(228,'19','project_title','en','Title of Student Project<span style=\"color:red\">*</span>','1'),(229,'19','project_student_group','en','Number of Student/Group<span style=\"color:red\">*</span>','1'),(230,'19','project_student_name','en','Name of Students<span style=\"color:red\">*</span>','1'),(231,'19','project_type','en','Project Type<span style=\"color:red\">*</span>','1'),(232,'19','project_academic_term','en','Choose Academic Term when Project Started','1'),(233,'19','project_start_month_year','en','Month and Year of Start<span style=\"color:red\">*</span>','1'),(234,'19','project_outcome','en','Outcome','1'),(235,'19','project_completion_month_year','en','Month and Year of Completion','1'),(236,'19','project_url','en','URL if any','1'),(237,'19','project_other_details','en','Other Details','1'),(238,'19','project_upload','en','Upload File','1'),(239,'20','training_name','en','Name of Seminar/Conference/Workshop/Training<span style=\"color:red\">*</span>|Name<span style=\"color:red\">*</span>','1'),(240,'20','duration_from','en','Duration From<span style=\"color:red\">*</span>','1'),(241,'20','duration_to','en','Duration To<span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','1'),(242,'20','location','en','Location<span style=\"color:red\">*</span>','1'),(243,'20','purpose','en','Purpose of attending the Seminar / Conference','1'),(244,'20','funding_source','en','Source of Funding','1'),(245,'20','funded_amount','en','Amount Funded','1'),(246,'20','url','en','URL if any','1'),(247,'20','other_details','en','Other Details','1'),(248,'20','upload','en','Upload File','1'),(249,'20','paper_title','en','Title of paper|Paper Title','1'),(250,'20','author','en','Author','1'),(251,'20','research_expertise_area','en','Research Expertise Area|Research Area','1'),(252,'20','abstract','en','Abstract','1'),(253,'1','language','hi','???? :','1'),(254,'1','login_msg','hi','????? ???? ??????? ???? ???? !','1'),(255,'1','error_msg1','hi','????????? ?? ??? ???? !','1'),(256,'1','error_msg3','hi','??? ??????? !','1'),(257,'1','username','hi','?????????? ???:','1'),(258,'1','lost_password','hi','???? ??????? ??? ?? ?','1'),(259,'1','login','hi','??? ??','1'),(260,'1','error_msg2','hi','??? ?????????? ??? !','1'),(261,'1','password','hi','???????:','1');

/*Table structure for table `language_master` */

DROP TABLE IF EXISTS `language_master`;

CREATE TABLE `language_master` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `code` varchar(255) default NULL,
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `language_master` */

insert  into `language_master`(`id`,`name`,`code`,`active_yes_no`) values (1,'English','en','1'),(2,'Malayalam','ml','1'),(3,'Hindi','hi','1');

/*Table structure for table `profile_master` */

DROP TABLE IF EXISTS `profile_master`;

CREATE TABLE `profile_master` (
  `id` int(11) NOT NULL default '0',
  `form_name` varchar(255) default NULL,
  `version` varchar(50) default NULL,
  `title` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `document_type_id` int(11) default NULL,
  `form_type` smallint(1) default NULL,
  `active_yesno` smallint(1) default NULL,
  `show_draft_copy_yesno` smallint(1) NOT NULL default '1',
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date_time` datetime default NULL,
  `iso_document_number` varchar(150) default NULL,
  `scannable_yesno` smallint(1) default '0',
  `child_yesno` smallint(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile_master` */

insert  into `profile_master`(`id`,`form_name`,`version`,`title`,`description`,`document_type_id`,`form_type`,`active_yesno`,`show_draft_copy_yesno`,`last_modified_by`,`last_modified_date_time`,`iso_document_number`,`scannable_yesno`,`child_yesno`) values (1,'staff_profile_report','v0','Staff Profile','Staff Profile',1,0,1,1,'admin',NULL,NULL,0,NULL),(2,'staff_profile_qualification','v0','Staff Profile Qualification Details','Staff Profile Qualification Details',2,0,1,1,'admin',NULL,NULL,0,NULL),(3,'staff_profile_awards','v0','Staff Profile Awards Details','Staff Profile Awards Details',3,0,1,1,'admin',NULL,NULL,0,NULL),(4,'staff_profile_community_service','v0','Staff Profile Community Service Details','Staff Profile Community Service Details',4,0,1,1,'admin',NULL,NULL,0,NULL),(5,'staff_profile_review_committees','v0','Staff Profile Review Committees Details','Staff Profile Review Committees Details',5,0,1,1,'admin',NULL,NULL,0,NULL),(6,'staff_profile_governance','v0','Staff Profile Governance Details','Staff Profile Governance Details',6,0,1,1,'admin',NULL,NULL,0,NULL),(7,'staff_profile_consultancy_offered','v0','Staff Profile Consultancy Details','Staff Profile Consultancy Details',7,0,1,1,'admin',NULL,NULL,0,NULL),(8,'staff_profile_professional_body','v0','Staff Profile Professional Body Details','Staff Profile Professional Body Details',8,0,1,1,'admin',NULL,NULL,0,NULL),(9,'staff_profile_faculty_exchange','v0','Staff Profile Faculty Exchange Details','Staff Profile Faculty Exchange Details',9,0,1,1,'admin',NULL,NULL,0,NULL),(10,'staff_profile_thesis','v0','Staff Profile Thesis Details','Staff Profile Thesis Details',10,0,1,1,'admin',NULL,NULL,0,NULL),(11,'staff_profile_media_publication','v0','Staff Profile Media Publication Details','Staff Profile Media Publication Details',11,0,1,1,'admin',NULL,NULL,0,NULL),(12,'staff_profile_patents','v0','Staff Profile Patents Details','Staff Profile Patents Details',12,0,1,1,'admin',NULL,NULL,0,NULL),(13,'staff_profile_projects','v0','Staff Profile Projects Details','Staff Profile Projects Details',13,0,1,1,'admin',NULL,NULL,0,NULL),(14,'staff_profile_training','v0','Staff Profile Training Details','Staff Profile Training Details',14,0,1,1,'admin',NULL,NULL,0,NULL),(15,'staff_profile_talks_lectures','v0','Staff Profile Talks Lectures Details','Staff Profile Talks Lectures Details',15,0,1,1,'admin',NULL,NULL,0,NULL),(16,'staff_profile_books_chapter','v0','Staff Profile Books Chapter Details','Staff Profile Books Chapter Details',16,0,1,1,'admin',NULL,NULL,0,NULL),(17,'staff_profile_conference_papers','v0','Staff Profile Conference Papers Details','Staff Profile Conference Papers Details',17,0,1,1,'admin',NULL,NULL,0,NULL),(18,'staff_profile_journal_papers','v0','Staff Profile Journal Papers Details','Staff Profile Journal Papers Details',18,0,1,1,'admin',NULL,NULL,0,NULL),(19,'staff_profile_personal_details','v0','Staff Profile Personal Details','Staff Profile Personal Details',19,0,1,1,'admin',NULL,NULL,0,NULL);

/*Table structure for table `profile_series` */

DROP TABLE IF EXISTS `profile_series`;

CREATE TABLE `profile_series` (
  `series` varchar(255) NOT NULL default '',
  `max_number` int(11) default NULL,
  `last_modified_by` varchar(20) default NULL,
  `last_modified_date_time` datetime default NULL,
  `edition` int(11) default '0',
  PRIMARY KEY  (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile_series` */

insert  into `profile_series`(`series`,`max_number`,`last_modified_by`,`last_modified_date_time`,`edition`) values ('EntityDocument',2,NULL,NULL,0),('ENTITY_DOCUMENT_MASTER',2,NULL,NULL,0);

/*Table structure for table `profile_type_master` */

DROP TABLE IF EXISTS `profile_type_master`;

CREATE TABLE `profile_type_master` (
  `id` int(11) default NULL,
  `document_type` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `active_yesno` smallint(1) default NULL,
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date_time` datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile_type_master` */

insert  into `profile_type_master`(`id`,`document_type`,`description`,`active_yesno`,`last_modified_by`,`last_modified_date_time`) values (1,'SPF','Staff Profile',1,'admin',NULL),(2,'SP_QUALIFICATION','Staff Profile Qualification',1,'admin',NULL),(3,'SP_AWARDS','Staff Profile Awards',1,'admin',NULL),(4,'SP_COMMUNITY_SERVICE','Staff Profile Community Service',1,'admin',NULL),(5,'SP_REVIEW_COMMITTEES','Staff Profile Review Committees',1,'admin',NULL),(6,'SP_GOVERNANCE','Staff Profile Governance',1,'admin',NULL),(7,'SP_CONSULTANCY_OFFERED','Staff Profile Consultancy Offered',1,'admin',NULL),(8,'SP_PROFESSIONAL_BODY','Staff Profile Professional Body',1,'admin',NULL),(9,'SP_FACULTY_EXCHANGE','Staff Profile Faculty Exchange',1,'admin',NULL),(10,'SP_THESIS','Staff Profile Thesis',1,'admin',NULL),(11,'SP_MEDIA_PUBLICATION','Staff Profile Media Publication',1,'admin',NULL),(12,'SP_PATENTS','Staff Profile Patents',1,'admin',NULL),(13,'SP_PROJECTS','Staff Profile Projects',1,'admin',NULL),(14,'SP_TRAINING','Staff Profile Training',1,'admin',NULL),(15,'SP_TALKS_LECTURES','Staff Profile Talks Lectures',1,'admin',NULL),(16,'SP_BOOKS_CHAPTER','Staff Profile Books Chapter',1,'admin',NULL),(17,'SP_CONFERENCE_PAPERS','Staff Profile Conference Papers',1,'admin',NULL),(18,'SP_JOURNAL_PAPERS','Staff Profile Journal Papers',1,'admin',NULL),(19,'SP_PERSONAL_DETAILS','Staff Profile Personal Details',1,'admin',NULL);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(50) default NULL,
  `active_yesno` varchar(1) default NULL,
  PRIMARY KEY  (`role_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`role_id`,`role_name`,`active_yesno`) values (1,'ROLE_ADMIN','1'),(2,'ROLE_PROFILE_CREATION','1');

/*Table structure for table `search_result_fields` */

DROP TABLE IF EXISTS `search_result_fields`;

CREATE TABLE `search_result_fields` (
  `result_fields` text,
  `description` varchar(150) default NULL,
  `sequence` int(11) default NULL COMMENT 'Result Field order ',
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `search_result_fields` */

insert  into `search_result_fields`(`result_fields`,`description`,`sequence`,`id`) values (' SELECT idf,`staff_profile_personaldetails_v0_values`.user_full_name, \r\nuniversity_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  ,\r\nconcat(\'<b>Office Room No:</b>\',office_room_num),Concat(\'<b>Phone Extension :</b>\',office_phone_ext)\r\n,concat(\'<b>E-mail :</b>\',email_id) FROM `staff_profile_personaldetails_v0_values`  INNER JOIN users ON `staff_profile_personaldetails_v0_values`.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id','Office Details',1,1),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,\r\n institution_master.id instId,institution_master.name instname  , \r\n course_name, institution,staff_profile_qualification_v0_values.location FROM staff_profile_qualification_v0_values \r\nINNER JOIN users ON staff_profile_qualification_v0_values.idf=users.id  \r\n INNER JOIN institution_master ON institution_master.ID=users.`institution_id`  \r\nINNER JOIN university_master ON institution_master.id=university_master.id ','Qualification Details',10,2),('SELECT idf,user_full_name, \r\nuniversity_master.id uniId,university_master.name uniname,  \r\ninstitution_master.id instId,institution_master.name instname  ,  \r\nCONCAT(\'<b>\',award_name,\'</b>\'),CONCAT(\'From \',agency_name),receiving_month_year FROM staff_profile_awards_v0_values \r\n INNER JOIN users ON staff_profile_awards_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id\r\n','Awards Details',15,3),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\n  concat(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1,\r\n  volume,issue_no,month_year \r\n  FROM staff_profile_journal_papers_v0_values \r\n   INNER JOIN users ON staff_profile_journal_papers_v0_values.idf=users.id  \r\n    INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\n    INNER JOIN university_master ON institution_master.id=university_master.id \r\n    INNER JOIN general_master ON  general_master.id=staff_profile_journal_papers_v0_values.journal_name AND category=\'Journal\'    \r\n    WHERE  journal_type=\'National\'','National Publications',20,5),('   SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\n  concat(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1 ,month_year\r\n  FROM staff_profile_conference_papers_v0_values \r\n  INNER JOIN users ON staff_profile_conference_papers_v0_values.idf=users.id   \r\n  INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\n  INNER JOIN university_master ON institution_master.id=university_master.id \r\n  INNER JOIN general_master ON  general_master.id=staff_profile_conference_papers_v0_values.conference_name AND category=\'Conference_Name\'\r\n  WHERE conference_type=\'National\' ','National Conference Papers',25,6),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\n book_chapter,\r\nconcat(\'<b>\',title,\'</b>\'),author1,publication_month_year FROM staff_profile_books_chapter_v0_values  INNER JOIN users ON staff_profile_books_chapter_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id \r\n','Books/Chapters Details',30,7),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,  \r\ninstitution_master.id instId,institution_master.name instname  ,  \r\nCONCAT(\'<b>\',lecture_topic,\'</b>\'),CONCAT(\' at \', institution_address),event_name,talk_date_time \r\nFROM staff_profile_talks_lectures_v0_values  \r\nINNER JOIN users ON staff_profile_talks_lectures_v0_values.idf=users.id   \r\nINNER JOIN institution_master ON institution_master.ID=users.`institution_id`  \r\n INNER JOIN university_master ON institution_master.id=university_master.id \r\n','Invited Talks Details',35,8),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,\r\n  institution_master.id instId,institution_master.name instname  ,  \r\nCONCAT(\'<b>\',training_name,\'</b>\'),CONCAT(\'Period :\',duration_from,\' to \',duration_to),\r\nCONCAT(\' at \',staff_profile_training_v0_values.location) FROM staff_profile_training_v0_values  INNER JOIN users ON staff_profile_training_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id ','Seminar/Training',40,9),('SELECT idf,user_full_name, \r\nuniversity_master.id uniId,university_master.name uniname,  \r\ninstitution_master.id instId,institution_master.name instname  ,  \r\nproject_status,CONCAT(\'<b>\',project_title,\'</b>\'),\r\nCONCAT(\'Start on \',start_date),\r\nCONCAT(\'for \',duration),amount,CONCAT(\'associated with \',general_master.fld_value),abstract \r\nFROM staff_profile_projects_v0_values  \r\nINNER JOIN users ON staff_profile_projects_v0_values.idf=users.id   \r\nINNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\nINNER JOIN university_master ON institution_master.id=university_master.id \r\nINNER JOIN `general_master` ON \r\nstaff_profile_projects_v0_values.project_center_department =`general_master`.id \r\nAND `general_master`.category=\'Department\'','Project Details',45,10),('SELECT idf,user_full_name, university_master.id uniId,\r\nuniversity_master.name uniname,  \r\ninstitution_master.id instId,institution_master.name instname  ,\r\n  choose_patent,\r\n  CONCAT(\'<b>\',patent_title,\'<b/>\'),\r\n  registration_month_year FROM staff_profile_patents_v0_values  INNER JOIN users ON staff_profile_patents_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id ','Patent Details',50,11),('SELECT idf,user_full_name, university_master.id uniId,\r\nuniversity_master.name uniname,  \r\ninstitution_master.id instId,institution_master.name instname  , \r\n general_master.fld_value,concat(\'<b>\',title,\'</b>\'),magazine_name,article_month_year \r\n FROM staff_profile_media_publication_v0_values  \r\n INNER JOIN users ON staff_profile_media_publication_v0_values.idf=users.id   \r\n INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\n INNER JOIN university_master ON institution_master.id=university_master.id\r\n INNER JOIN `general_master` ON `general_master`.id=staff_profile_media_publication_v0_values.media_type AND category=\'Media\'','Media Publication Details',55,12),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,\r\n  institution_master.id instId,institution_master.name instname  ,  \r\nchoose,concat(\'<b>\',thesis_title,\'</b>\',\'<b>\',project_title,\'</b>\') FROM staff_profile_thesis_v0_values  \r\nINNER JOIN users ON staff_profile_thesis_v0_values.idf=users.id  \r\n INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\nINNER JOIN university_master ON institution_master.id=university_master.id \r\n','Student Project Details',60,13),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,\r\n  institution_master.id instId,institution_master.name instname  ,\r\n  institution_name,\r\nconcat(\' taught <b>\',subjects_taught,\'</b>\'),concat(\' from \',duration_from),concat(\' to \',duration_to) \r\nfrom staff_profile_faculty_exchange_v0_values  INNER JOIN users ON staff_profile_faculty_exchange_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id \r\n','Faculty Exchange Details',65,14),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,\r\n  institution_master.id instId,institution_master.name instname  , \r\n CONCAT(\'<b>\',fld_value,\'</b>\'),membership_from,membership_to \r\nFROM staff_profile_professional_body_v0_values  \r\nINNER JOIN users ON staff_profile_professional_body_v0_values.idf=users.id   \r\nINNER JOIN institution_master ON institution_master.ID=users.`institution_id`  \r\n INNER JOIN university_master ON institution_master.id=university_master.id\r\nINNER JOIN general_master ON general_master.id= staff_profile_professional_body_v0_values.professional_body_name\r\nAND category=\'Professional_Body_Name\'','Professional Bodies Details',70,15),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\nconcat(\'<b>\',client_name,\'</b>\'),duration_from,duration_to FROM staff_profile_consultancy_offered_v0_values  INNER JOIN users ON staff_profile_consultancy_offered_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id','Consultancy Offered Details',75,16),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\n concat(\'<b>\',committee_name,\'</b>\'),school_name,\r\nconcat(\' from \',service_dates_from),concat(\' to \',service_dates_to)\r\n FROM staff_profile_governance_v0_values  INNER JOIN users ON staff_profile_governance_v0_values.idf=users.id   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   INNER JOIN university_master ON institution_master.id=university_master.id','Governance Details',80,17),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  ,  \r\n CONCAT(fld_value,\' of \'),CONCAT(\'<b>\',title,\'</b>\'), CONCAT(\' from \',appointed_month_year )\r\n FROM staff_profile_review_committees_v0_values \r\n  INNER JOIN users ON staff_profile_review_committees_v0_values.idf=users.id  \r\n   INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\nINNER JOIN university_master ON institution_master.id=university_master.id\r\nINNER JOIN `general_master` ON \r\nstaff_profile_review_committees_v0_values.role_played =`general_master`.id \r\nAND `general_master`.category=\'Role_Played\'','Review Committees Details',85,18),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,  \r\ninstitution_master.id instId,institution_master.name instname  ,  \r\nCONCAT(\'<b>\',event_name,\'</b>\'),CONCAT(\' at \',staff_profile_community_service_v0_values.location),\r\nCONCAT(\' on \',activity_month_year) \r\nFROM staff_profile_community_service_v0_values \r\nINNER JOIN users ON staff_profile_community_service_v0_values.idf=users.id   \r\nINNER JOIN institution_master ON institution_master.ID=users.`institution_id`  \r\n INNER JOIN university_master ON institution_master.id=university_master.id\r\n','Community Service Details',90,19),('SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\n  concat(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1,\r\n  volume,issue_no,month_year \r\n  FROM staff_profile_journal_papers_v0_values \r\n   INNER JOIN users ON staff_profile_journal_papers_v0_values.idf=users.id  \r\n    INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\n    INNER JOIN university_master ON institution_master.id=university_master.id \r\n    INNER JOIN general_master ON  general_master.id=staff_profile_journal_papers_v0_values.journal_name AND category=\'Journal\'    \r\n    WHERE  journal_type=\'International\'','International Publications',21,20),('   SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname, \r\n institution_master.id instId,institution_master.name instname  , \r\n  concat(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1 ,month_year\r\n  FROM staff_profile_conference_papers_v0_values \r\n  INNER JOIN users ON staff_profile_conference_papers_v0_values.idf=users.id   \r\n  INNER JOIN institution_master ON institution_master.ID=users.`institution_id`   \r\n  INNER JOIN university_master ON institution_master.id=university_master.id \r\n  INNER JOIN general_master ON  general_master.id=staff_profile_conference_papers_v0_values.conference_name AND category=\'Conference_Name\'\r\n  WHERE conference_type=\'International\' ','International Conference Papers',26,21),('   SELECT idf,user_full_name, university_master.id uniId,university_master.name uniname,\r\n     institution_master.id instId,institution_master.name instname  ,  \r\n     CONCAT(\'<b>Focus Area:</b> \',focus_area),\r\n     CONCAT(\'<b>Research Interests:</b> \',research_interests),\r\n     CONCAT(\'<b>Teaching Interest:</b> \',teaching_interests),\r\n     CONCAT(\'<b>Positions Held: </b>\',positions_held) \r\n     FROM staff_profile_personal_details_v0_values \r\n     INNER JOIN users ON staff_profile_personal_details_v0_values.idf=users.id   \r\n     INNER JOIN institution_master ON institution_master.ID=users.`institution_id`  \r\n      INNER JOIN university_master ON institution_master.id=university_master.id','Profile Details',5,23);

/*Table structure for table `staff_profile_awards_v0_items` */

DROP TABLE IF EXISTS `staff_profile_awards_v0_items`;

CREATE TABLE `staff_profile_awards_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_awards_v0_items` */

insert  into `staff_profile_awards_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'award_name','Name_of_Award',NULL,'2011-02-25 18:03:05',NULL,'0000-00-00 00:00:00','Name of Award<span style=\"color:red\">*</span>|Award<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'agency_name','Name_of_Agency',NULL,'2011-02-25 18:03:05',NULL,'0000-00-00 00:00:00','Name of Agency|Agency','blank',NULL,NULL,NULL),(15,'receiving_month_year','Month_Year_of_Receiving',NULL,'2011-02-25 18:03:05',NULL,'0000-00-00 00:00:00','Month and Year of Receiving<span style=\"color:red\">*</span>|Rec. Month And Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL),(25,'remarks','Remarks',NULL,'2011-01-29 16:17:06',NULL,'0000-00-00 00:00:00','Remarks','textarea_small',NULL,NULL,NULL),(30,'upload_image','Upload_Image',NULL,'2011-01-15 11:48:59',NULL,'0000-00-00 00:00:00','Upload Image','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_awards_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_awards_v0_itemtypes`;

CREATE TABLE `staff_profile_awards_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_awards_v0_itemtypes` */

insert  into `staff_profile_awards_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:12:33','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_awards_v0_values` */

DROP TABLE IF EXISTS `staff_profile_awards_v0_values`;

CREATE TABLE `staff_profile_awards_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `award_name` varchar(255) default NULL,
  `agency_name` varchar(255) default NULL,
  `receiving_month_year` varchar(100) default NULL,
  `url` varchar(255) default NULL,
  `remarks` text,
  `upload_image` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_awards_v0_values` */

/*Table structure for table `staff_profile_books_chapter_v0_items` */

DROP TABLE IF EXISTS `staff_profile_books_chapter_v0_items`;

CREATE TABLE `staff_profile_books_chapter_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_books_chapter_v0_items` */

insert  into `staff_profile_books_chapter_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'book_chapter','\"Book\"/\"Chapter\"',NULL,'2011-02-25 18:05:17',NULL,'0000-00-00 00:00:00','Book/\"Chapter\"<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','radio_book',NULL,NULL,NULL),(10,'role','Role',NULL,'2011-01-28 12:02:03',NULL,'0000-00-00 00:00:00','Role','combo_box_role',NULL,NULL,NULL),(15,'title','Title',NULL,'2011-01-27 17:52:02',NULL,'0000-00-00 00:00:00','Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'author1','Author1',NULL,'2011-03-23 13:53:22',NULL,'0000-00-00 00:00:00','Author<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'author2','Author2',NULL,'2011-03-23 13:53:22',NULL,'0000-00-00 00:00:00','Authors','textarea_small',NULL,NULL,NULL),(30,'publisher','Publisher',NULL,'2011-01-22 09:46:13',NULL,'0000-00-00 00:00:00','Publisher','blank',NULL,NULL,NULL),(55,'affiliation','Affiliation',NULL,'2011-03-11 10:42:08',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL),(60,'url','URL if any',NULL,'2011-01-21 13:05:02',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(65,'other_details','Other Details',NULL,'2011-01-22 14:02:18',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(70,'upload','Upload File',NULL,'2011-01-21 13:05:11',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(35,'isbn','ISBN',NULL,'2011-01-21 13:07:13',NULL,'0000-00-00 00:00:00','ISBN','blank',NULL,NULL,NULL),(40,'publication_month_year','Month and Year of Publication',NULL,'2011-02-25 18:05:17',NULL,'0000-00-00 00:00:00','Month and Year of Publication<span style=\"color:red\">*</span>|Month And Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(45,'no_of_pages','No of Pages',NULL,'2011-01-21 13:09:34',NULL,'0000-00-00 00:00:00','No of Pages','blank',NULL,NULL,NULL),(50,'language','Language',NULL,'2011-01-25 17:22:11',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);

/*Table structure for table `staff_profile_books_chapter_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_books_chapter_v0_itemtypes`;

CREATE TABLE `staff_profile_books_chapter_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_books_chapter_v0_itemtypes` */

insert  into `staff_profile_books_chapter_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_affiliation','None','oio','2011-03-11 10:46:31','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:49:52','except','30',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_book','None','oio','2011-01-21 12:57:57','pickone_radio','Book,Chapter','Book,Chapter',NULL),('combo_box_language','None','oio','2011-03-07 14:06:58','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_role','None','oio','2011-03-14 14:50:54','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Role\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Role\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_books_chapter_v0_values` */

DROP TABLE IF EXISTS `staff_profile_books_chapter_v0_values`;

CREATE TABLE `staff_profile_books_chapter_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `book_chapter` varchar(255) default NULL,
  `role` text,
  `title` varchar(255) default NULL,
  `author1` varchar(255) default NULL,
  `author2` varchar(255) default NULL,
  `publisher` varchar(255) default NULL,
  `isbn` varchar(255) default NULL,
  `publication_month_year` varchar(255) default NULL,
  `no_of_pages` varchar(255) default NULL,
  `language` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_books_chapter_v0_values` */

/*Table structure for table `staff_profile_community_service_v0_items` */

DROP TABLE IF EXISTS `staff_profile_community_service_v0_items`;

CREATE TABLE `staff_profile_community_service_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_community_service_v0_items` */

insert  into `staff_profile_community_service_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'event_name','Name_of_Event',NULL,'2011-02-25 18:10:21',NULL,'0000-00-00 00:00:00','Name of Event<span style=\"color:red\">*</span>|Event<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'location','Location',NULL,'2011-01-29 10:58:36',NULL,'0000-00-00 00:00:00','Location<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'activity_month_year','Month_Year_of_Activity',NULL,'2011-02-25 18:10:21',NULL,'0000-00-00 00:00:00','Month Year of Activity<span style=\"color:red\">*</span>|Month And Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL),(25,'other_details','Other_Details',NULL,'2011-01-22 14:08:01',NULL,'0000-00-00 00:00:00','Other_Details','textarea_small',NULL,NULL,NULL),(30,'upload_file','Upload_File',NULL,'2011-01-14 18:43:20',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_community_service_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_community_service_v0_itemtypes`;

CREATE TABLE `staff_profile_community_service_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_community_service_v0_itemtypes` */

insert  into `staff_profile_community_service_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:44:24','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_community_service_v0_values` */

DROP TABLE IF EXISTS `staff_profile_community_service_v0_values`;

CREATE TABLE `staff_profile_community_service_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `event_name` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `activity_month_year` varchar(100) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload_file` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_community_service_v0_values` */

/*Table structure for table `staff_profile_conference_papers_v0_items` */

DROP TABLE IF EXISTS `staff_profile_conference_papers_v0_items`;

CREATE TABLE `staff_profile_conference_papers_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_conference_papers_v0_items` */

insert  into `staff_profile_conference_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'conference_type','Conference Type',NULL,'2011-02-25 18:27:55',NULL,'0000-00-00 00:00:00','Conference Type<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','radio_conference',NULL,NULL,NULL),(10,'research_expertise_area','Research Expertise Area',NULL,'2011-03-10 17:51:43',NULL,'0000-00-00 00:00:00','Research Expertise Area<span style=\"color:red\">*</span>|Research Area<span style=\"color:red\">*</span>','combo_box_specialization',NULL,NULL,NULL),(15,'paper_associated_project','Is the paper associated with a project',NULL,'2011-02-25 18:27:55',NULL,'0000-00-00 00:00:00','Is the paper associated with a project<span style=\"color:red\">*</span>|Project Name<span style=\"color:red\">*</span>','radio_associated_project',NULL,NULL,NULL),(20,'project_name','If Yes, Choose Project Name',NULL,'2011-02-03 14:15:23',NULL,'0000-00-00 00:00:00','If Yes, Choose Project Name','combo_box_projects',NULL,NULL,NULL),(25,'paper_title','Title of paper',NULL,'2011-02-25 18:27:55',NULL,'0000-00-00 00:00:00','Title of paper<span style=\"color:red\">*</span>|Paper Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'author1','Author1',NULL,'2011-03-23 13:49:57',NULL,'0000-00-00 00:00:00','Author<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(85,'affiliation','Affiliation',NULL,'2011-03-10 21:19:57',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL),(90,'url','URL if any',NULL,'2011-01-21 14:59:42',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(100,'other_details','Other Details',NULL,'2011-01-22 14:05:13',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(105,'upload_paper','Upload File',NULL,'2011-01-21 15:48:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(95,'keyword','Keyword',NULL,'2011-01-21 15:01:33',NULL,'0000-00-00 00:00:00','Keyword','blank',NULL,NULL,NULL),(35,'author2','Author2',NULL,'2011-03-23 13:49:54',NULL,'0000-00-00 00:00:00','Authors','textarea_small',NULL,NULL,NULL),(40,'month_year','Month and Year',NULL,'2011-01-21 15:22:33',NULL,'0000-00-00 00:00:00','Month and Year','calendar',NULL,NULL,NULL),(45,'abstract','Abstract',NULL,'2011-03-22 13:07:15',NULL,'0000-00-00 00:00:00','Abstract','textarea_small',NULL,NULL,NULL),(50,'pages','Pages',NULL,'2011-01-21 15:24:27',NULL,'0000-00-00 00:00:00','Pages','blank',NULL,NULL,NULL),(55,'conference_name','Name of Conference',NULL,'2011-03-11 11:49:56',NULL,'0000-00-00 00:00:00','Name of Conference','combo_box_conference_name',NULL,NULL,NULL),(60,'date_from','Conference Dates From',NULL,'2011-01-21 15:26:43',NULL,'0000-00-00 00:00:00','Conference Dates From','calendar',NULL,NULL,NULL),(65,'date_to','Conference Dates To',NULL,'2011-01-21 15:27:20',NULL,'0000-00-00 00:00:00','Conference Dates To','calendar',NULL,NULL,NULL),(70,'conference_venue','Conference Venue',NULL,'2011-01-21 15:28:23',NULL,'0000-00-00 00:00:00','Conference Venue','blank',NULL,NULL,NULL),(75,'organizer_name','Name of Organizer',NULL,'2011-01-21 15:29:38',NULL,'0000-00-00 00:00:00','Name of Organizer','blank',NULL,NULL,NULL),(80,'language','Language',NULL,'2011-01-25 17:25:11',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL);

/*Table structure for table `staff_profile_conference_papers_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_conference_papers_v0_itemtypes`;

CREATE TABLE `staff_profile_conference_papers_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_conference_papers_v0_itemtypes` */

insert  into `staff_profile_conference_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_conference_name','None','oio','2011-03-11 11:49:56','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Conference_Name\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Conference_Name\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:43:42','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_conference','None','oio','2011-01-21 15:08:37','pickone_radio','National,International','National,International',NULL),('radio_associated_project','None','oio','2011-01-21 15:13:14','pickone_radio','Yes,No','Yes,No',NULL),('combo_box_language','None','oio','2011-03-07 13:49:23','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_projects','None','oio','2011-03-15 18:36:31','pickone_table','project_title','staff_profile_projects_v0_values',NULL),('combo_box_affiliation','None','oio','2011-03-10 21:10:44','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_specialization','None','oio','2011-03-10 17:55:10','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_conference_papers_v0_values` */

DROP TABLE IF EXISTS `staff_profile_conference_papers_v0_values`;

CREATE TABLE `staff_profile_conference_papers_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `conference_type` varchar(255) default NULL,
  `research_expertise_area` text,
  `paper_associated_project` varchar(255) default NULL,
  `project_name` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author1` varchar(255) default NULL,
  `author2` varchar(255) default NULL,
  `month_year` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `pages` varchar(255) default NULL,
  `conference_name` varchar(255) default NULL,
  `date_from` varchar(255) default NULL,
  `date_to` varchar(255) default NULL,
  `conference_venue` varchar(255) default NULL,
  `organizer_name` varchar(255) default NULL,
  `language` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `keyword` varchar(255) default NULL,
  `other_details` text,
  `upload_paper` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_conference_papers_v0_values` */

/*Table structure for table `staff_profile_consultancy_offered_v0_items` */

DROP TABLE IF EXISTS `staff_profile_consultancy_offered_v0_items`;

CREATE TABLE `staff_profile_consultancy_offered_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_consultancy_offered_v0_items` */

insert  into `staff_profile_consultancy_offered_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'client_name','Name_of_Client',NULL,'2011-02-25 18:31:24',NULL,'0000-00-00 00:00:00','Name of Client<span style=\"color:red\">*</span>|Client<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'duration_from','Duration_From',NULL,'2011-01-28 14:15:31',NULL,'0000-00-00 00:00:00','Duration From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(15,'duration_to','Duration_To',NULL,'2011-02-25 18:31:24',NULL,'0000-00-00 00:00:00','Duration To<span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'other_faculty_name','If other faculty involved,mention name(s) ',NULL,'2011-01-22 09:47:28',NULL,'0000-00-00 00:00:00','If other faculty involved,mention name(s) ','blank',NULL,NULL,NULL),(25,'revenue_generated','Revenue_Generated',NULL,'2011-01-17 13:07:22',NULL,'0000-00-00 00:00:00','Revenue Generated','blank',NULL,NULL,NULL),(30,'summary_work_undertaken','Brief_Summary_of_Work_Undertaken',NULL,'2011-01-17 13:01:25',NULL,'0000-00-00 00:00:00','Brief Summary of Work Undertaken','textarea',NULL,NULL,NULL),(35,'labs_used','Amrita_Labs/Facilities_used_for_Consultancy',NULL,'2011-03-11 13:04:04',NULL,'0000-00-00 00:00:00','Facilities used for Consultancy','combo_box_facilities_used',NULL,NULL,NULL),(40,'url','URL_if_any',NULL,'2011-01-17 13:08:57',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(45,'other_details','Other_Details',NULL,'2011-01-22 14:10:00',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(50,'upload','Upload_File',NULL,'2011-01-17 13:12:16',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_consultancy_offered_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_consultancy_offered_v0_itemtypes`;

CREATE TABLE `staff_profile_consultancy_offered_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_consultancy_offered_v0_itemtypes` */

insert  into `staff_profile_consultancy_offered_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_facilities_used','None','oio','2011-03-11 13:04:04','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Consultancy_Facilities\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Consultancy_Facilities\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:44:55','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_consultancy_offered_v0_values` */

DROP TABLE IF EXISTS `staff_profile_consultancy_offered_v0_values`;

CREATE TABLE `staff_profile_consultancy_offered_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `client_name` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(100) default NULL,
  `other_faculty_name` varchar(100) default NULL,
  `revenue_generated` varchar(255) default NULL,
  `summary_work_undertaken` varchar(255) default NULL,
  `labs_used` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_consultancy_offered_v0_values` */

/*Table structure for table `staff_profile_faculty_exchange_v0_items` */

DROP TABLE IF EXISTS `staff_profile_faculty_exchange_v0_items`;

CREATE TABLE `staff_profile_faculty_exchange_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_faculty_exchange_v0_items` */

insert  into `staff_profile_faculty_exchange_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'institution_name','Name_of_Institutions_Involved',NULL,'2011-02-25 18:36:36',NULL,'0000-00-00 00:00:00','Name of Institutions Involved<span style=\"color:red\">*</span>|Institutions Involved<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'subjects_taught','Name_of_Subjects_Taught',NULL,'2011-02-25 18:37:58',NULL,'0000-00-00 00:00:00','Name of Subjects Taught<span style=\"color:red\">*</span>|Subjects Taught<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'duration_from','Duration_From',NULL,'2011-01-28 12:38:04',NULL,'0000-00-00 00:00:00','Duration From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'duration_to','Duration_To',NULL,'2011-02-25 18:37:58',NULL,'0000-00-00 00:00:00','Duration To<span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'ug_or_pg','UG_or_PG',NULL,'2011-01-17 18:58:34',NULL,'0000-00-00 00:00:00','UG or PG','radio_button',NULL,NULL,NULL),(30,'research_collaborations','Research_Collaborations_if_any',NULL,'2011-01-17 19:00:10',NULL,'0000-00-00 00:00:00','Research Collaborations if any','blank',NULL,NULL,NULL),(35,'url','URL_if_any',NULL,'2011-01-17 19:02:46',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(40,'other_details','Other_Details',NULL,'2011-01-22 14:10:58',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(45,'upload','File_Upload',NULL,'2011-01-17 19:04:39',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_faculty_exchange_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_faculty_exchange_v0_itemtypes`;

CREATE TABLE `staff_profile_faculty_exchange_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_faculty_exchange_v0_itemtypes` */

insert  into `staff_profile_faculty_exchange_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:05','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_button','None','oio','2011-01-17 18:51:51','pickone_radio','UG,PG','UG,PG',NULL);

/*Table structure for table `staff_profile_faculty_exchange_v0_values` */

DROP TABLE IF EXISTS `staff_profile_faculty_exchange_v0_values`;

CREATE TABLE `staff_profile_faculty_exchange_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `institution_name` varchar(255) default NULL,
  `subjects_taught` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(255) default NULL,
  `ug_or_pg` varchar(255) default NULL,
  `research_collaborations` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_faculty_exchange_v0_values` */

/*Table structure for table `staff_profile_governance_v0_items` */

DROP TABLE IF EXISTS `staff_profile_governance_v0_items`;

CREATE TABLE `staff_profile_governance_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_governance_v0_items` */

insert  into `staff_profile_governance_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'committee_name','Name_of_Committee/Panel',NULL,'2011-02-25 18:40:19',NULL,'0000-00-00 00:00:00','Name of Committee/Panel<span style=\"color:red\">*</span>|Committee/Panel<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'school_name','Name_of_School/University',NULL,'2011-02-25 18:40:19',NULL,'0000-00-00 00:00:00','Name of School/University<span style=\"color:red\">*</span>|School/University<span style=\"color:red\">*</span>','combo_box',NULL,NULL,NULL),(15,'service_dates_from','Service_Dates_From',NULL,'2011-02-25 18:40:19',NULL,'0000-00-00 00:00:00','Service Dates From <span style=\"color:red\">*</span>|Service Dates From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'service_dates_to','Service_Dates_To',NULL,'2011-02-25 18:40:19',NULL,'0000-00-00 00:00:00','Service Dates To <span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'responsibilities','Responsibilities',NULL,'2011-01-17 10:59:33',NULL,'0000-00-00 00:00:00','Responsibilities','blank',NULL,NULL,NULL),(30,'url','URL',NULL,'2011-01-17 10:59:13',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(35,'upload','Upload',NULL,'2011-01-17 11:00:45',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(40,'other_details','Other_Details',NULL,'2011-01-22 14:09:11',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_governance_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_governance_v0_itemtypes`;

CREATE TABLE `staff_profile_governance_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_governance_v0_itemtypes` */

insert  into `staff_profile_governance_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2011-01-17 11:42:13','pickone','Amrita,Others','Amrita,Others',NULL),('blank','None','oio','2011-02-01 09:37:31','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL);

/*Table structure for table `staff_profile_governance_v0_values` */

DROP TABLE IF EXISTS `staff_profile_governance_v0_values`;

CREATE TABLE `staff_profile_governance_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `committee_name` varchar(255) default NULL,
  `school_name` varchar(255) default NULL,
  `service_dates_from` varchar(100) default NULL,
  `service_dates_to` varchar(100) default NULL,
  `responsibilities` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_governance_v0_values` */

/*Table structure for table `staff_profile_journal_papers_v0_items` */

DROP TABLE IF EXISTS `staff_profile_journal_papers_v0_items`;

CREATE TABLE `staff_profile_journal_papers_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_journal_papers_v0_items` */

insert  into `staff_profile_journal_papers_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'journal_type','Journal Type',NULL,'2011-03-22 12:53:48',NULL,'0000-00-00 00:00:00','Publication Type<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','radio_journal',NULL,NULL,NULL),(10,'research_expertise_area','Research Expertise Area',NULL,'2011-02-25 18:44:36',NULL,'0000-00-00 00:00:00','Research Expertise Area<span style=\"color:red\">*</span>|Research Area<span style=\"color:red\">*</span>','combo_box_specialization',NULL,NULL,NULL),(15,'paper_associated_project','Is the paper associated with a project',NULL,'2011-03-22 12:56:33',NULL,'0000-00-00 00:00:00','Is the paper associated with a project<span style=\"color:red\">*</span>|Assoc. with a prj<span style=\"color:red\">*</span>','radio_associated_project',NULL,NULL,NULL),(20,'project_name','If Yes, Choose Project Name',NULL,'2011-02-03 12:37:47',NULL,'0000-00-00 00:00:00','If Yes, Choose Project Name','combo_box_projects',NULL,NULL,NULL),(25,'paper_title','Title of paper',NULL,'2011-03-22 12:53:48',NULL,'0000-00-00 00:00:00','Title of paper<span style=\"color:red\">*</span>|Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'author1','Author1',NULL,'2011-03-23 13:45:50',NULL,'0000-00-00 00:00:00','Author<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(85,'affiliation','Affiliation',NULL,'2011-03-10 18:31:22',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL),(90,'url','URL if any',NULL,'2011-01-21 14:59:42',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(100,'other_details','Other Details',NULL,'2011-01-22 14:06:24',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(105,'upload_paper','Upload File',NULL,'2011-01-21 15:48:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(95,'keyword','Keyword',NULL,'2011-01-21 15:01:33',NULL,'0000-00-00 00:00:00','Keyword','blank',NULL,NULL,NULL),(35,'author2','Author2',NULL,'2011-03-23 13:51:25',NULL,'0000-00-00 00:00:00','Authors','textarea_small',NULL,NULL,NULL),(40,'journal_name','Journal Name',NULL,'2011-03-22 12:53:48',NULL,'0000-00-00 00:00:00','Publication Name<span style=\"color:red\">*</span>|Name<span style=\"color:red\">*</span>','combo_box_journals',NULL,NULL,NULL),(45,'volume','Volume',NULL,'2011-01-27 15:01:12',NULL,'0000-00-00 00:00:00','Volume<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(50,'issue_no','Issue No',NULL,'2011-01-27 15:01:30',NULL,'0000-00-00 00:00:00','Issue No<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(55,'month_year','Month and Year',NULL,'2011-01-27 15:01:40',NULL,'0000-00-00 00:00:00','Month and Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(60,'abstract','Abstract',NULL,'2011-03-22 13:03:31',NULL,'0000-00-00 00:00:00','Abstract','textarea_small',NULL,NULL,NULL),(65,'pages','Pages',NULL,'2011-01-21 18:00:50',NULL,'0000-00-00 00:00:00','Pages','blank',NULL,NULL,NULL),(70,'impact_factor','Impact Factor',NULL,'2011-01-21 18:01:40',NULL,'0000-00-00 00:00:00','Impact Factor','blank',NULL,NULL,NULL),(75,'average_citation_index','Average Citation Index',NULL,'2011-01-21 18:02:46',NULL,'0000-00-00 00:00:00','Average Citation Index','blank',NULL,NULL,NULL),(80,'language','Language',NULL,'2011-01-25 09:30:36',NULL,'0000-00-00 00:00:00','Language','combo_box_language',NULL,NULL,NULL),(110,'indexed_in_scopus','Whether Indexed in Scopus?(Y/N)',NULL,'2011-01-21 18:05:33',NULL,'0000-00-00 00:00:00','Whether Indexed in Scopus?(Y/N)','radio_associated_project',NULL,NULL,NULL);

/*Table structure for table `staff_profile_journal_papers_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_journal_papers_v0_itemtypes`;

CREATE TABLE `staff_profile_journal_papers_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_journal_papers_v0_itemtypes` */

insert  into `staff_profile_journal_papers_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_affiliation','None','oio','2011-03-10 18:33:57','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:17:34','except','30',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_journal','None','oio','2011-01-21 17:40:21','pickone_radio','National,International','National,International',NULL),('radio_associated_project','None','oio','2011-01-21 15:13:14','pickone_radio','Yes,No','Yes,No',NULL),('combo_box_language','None','oio','2011-03-07 13:42:45','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Language\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_specialization','None','oio','2011-03-09 20:50:14','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_journals','None','oio','2011-04-02 15:20:54','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Journal\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Journal\' \r\nAND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_projects','None','oio','2011-03-15 18:03:42','pickone_table','project_title','staff_profile_projects_v0_values',NULL);

/*Table structure for table `staff_profile_journal_papers_v0_values` */

DROP TABLE IF EXISTS `staff_profile_journal_papers_v0_values`;

CREATE TABLE `staff_profile_journal_papers_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `journal_type` varchar(255) default NULL,
  `research_expertise_area` text,
  `paper_associated_project` varchar(255) default NULL,
  `project_name` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author1` varchar(255) default NULL,
  `author2` varchar(255) default NULL,
  `journal_name` varchar(255) default NULL,
  `volume` varchar(255) default NULL,
  `issue_no` varchar(255) default NULL,
  `month_year` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `pages` varchar(255) default NULL,
  `impact_factor` varchar(255) default NULL,
  `average_citation_index` varchar(255) default NULL,
  `indexed_in_scopus` varchar(255) default NULL,
  `language` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `keyword` varchar(255) default NULL,
  `other_details` text,
  `upload_paper` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_journal_papers_v0_values` */

/*Table structure for table `staff_profile_media_publication_v0_items` */

DROP TABLE IF EXISTS `staff_profile_media_publication_v0_items`;

CREATE TABLE `staff_profile_media_publication_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_media_publication_v0_items` */

insert  into `staff_profile_media_publication_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'media_type','Type of Media',NULL,'2011-02-25 18:47:15',NULL,'0000-00-00 00:00:00','Type of Media<span style=\"color:red\">*</span>|Media Type<span style=\"color:red\">*</span>','combo_box_media_types',NULL,NULL,NULL),(10,'title','Title',NULL,'2011-01-28 11:42:20',NULL,'0000-00-00 00:00:00','Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'magazine_name','Name of Magazine/Media',NULL,'2011-03-21 20:01:51',NULL,'0000-00-00 00:00:00','Name of Magazine/Media<span \nstyle=\"color:red\">*</span>|Magazine Name<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'article_month_year','Month and Year of appearance of article',NULL,'2011-02-25 18:47:15',NULL,'0000-00-00 00:00:00','Month and Year of appearance of article<span style=\"color:red\">*</span>|Month and Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'url','URL if any',NULL,'2011-01-19 16:39:08',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(30,'other_details','Other Details',NULL,'2011-01-22 14:12:42',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(35,'upload','Upload File',NULL,'2011-01-19 16:43:25',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_media_publication_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_media_publication_v0_itemtypes`;

CREATE TABLE `staff_profile_media_publication_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_media_publication_v0_itemtypes` */

insert  into `staff_profile_media_publication_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:39','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('combo_box_media_types','None','oio','2011-03-09 18:24:28','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Media\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Media\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_media_publication_v0_values` */

DROP TABLE IF EXISTS `staff_profile_media_publication_v0_values`;

CREATE TABLE `staff_profile_media_publication_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `media_type` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `magazine_name` varchar(255) default NULL,
  `article_month_year` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_media_publication_v0_values` */

/*Table structure for table `staff_profile_patents_v0_items` */

DROP TABLE IF EXISTS `staff_profile_patents_v0_items`;

CREATE TABLE `staff_profile_patents_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_patents_v0_items` */

insert  into `staff_profile_patents_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'choose_patent','Choose if patent is \"National\" or \"International\"',NULL,'2011-02-28 15:26:01',NULL,'0000-00-00 00:00:00','Choose if patent is \"National\" or \"International\"<span style=\"color:red\">*</span>|Patent<span style=\"color:red\">*</span>','radio_choose',NULL,NULL,NULL),(10,'country','if International, pick country',NULL,'2011-01-28 15:40:34',NULL,'0000-00-00 00:00:00','if International, pick country','combo_box_country',NULL,NULL,NULL),(15,'patent_title','Title of patent',NULL,'2011-02-28 15:20:40',NULL,'0000-00-00 00:00:00','Title of patent<span style=\"color:red\">*</span>|Patent Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'registration_month_year','Month and Year of patent registration',NULL,'2011-02-28 15:26:02',NULL,'0000-00-00 00:00:00','Month and Year of patent registration<span style=\"color:red\">*</span>|Reg Month Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'name_co_applicant','Co-Applicant(s) Name(if any)',NULL,'2011-01-22 09:49:18',NULL,'0000-00-00 00:00:00','Co-Applicant(s) Name(if any)','blank',NULL,NULL,NULL),(30,'patent_details','Patent Details',NULL,'2011-01-22 13:51:18',NULL,'0000-00-00 00:00:00','Patent Details','textarea_small',NULL,NULL,NULL),(35,'affiliation','Affiliation',NULL,'2011-03-11 10:51:51',NULL,'0000-00-00 00:00:00','Affiliation','combo_box_affiliation',NULL,NULL,NULL),(40,'url','URL if any',NULL,'2011-01-20 11:11:04',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(45,'other_details','Other Details',NULL,'2011-01-22 13:54:16',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(50,'upload','Upload File',NULL,'2011-01-20 11:58:33',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_patents_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_patents_v0_itemtypes`;

CREATE TABLE `staff_profile_patents_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_patents_v0_itemtypes` */

insert  into `staff_profile_patents_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_affiliation','None','oio','2011-03-11 10:51:51','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Affiliation\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:45:49','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_choose','None','oio','2011-01-20 11:41:18','pickone_radio','National,International','National,International',NULL),('combo_box_country','None','oio','2011-03-09 16:03:16','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Country\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Country\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_patents_v0_values` */

DROP TABLE IF EXISTS `staff_profile_patents_v0_values`;

CREATE TABLE `staff_profile_patents_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `choose_patent` varchar(255) default NULL,
  `country` varchar(255) default NULL,
  `patent_title` varchar(255) default NULL,
  `registration_month_year` varchar(255) default NULL,
  `name_co_applicant` varchar(255) default NULL,
  `patent_details` varchar(255) default NULL,
  `affiliation` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_patents_v0_values` */

/*Table structure for table `staff_profile_personal_details_v0_items` */

DROP TABLE IF EXISTS `staff_profile_personal_details_v0_items`;

CREATE TABLE `staff_profile_personal_details_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_personal_details_v0_items` */

insert  into `staff_profile_personal_details_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'focus_area','Area of Study / Focus Area',NULL,'2011-02-28 16:53:44',NULL,'0000-00-00 00:00:00','Area of Study / Focus Area<span style=\"color:red\">*</span>|Focus Area<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'research_interests','Research Interests',NULL,'2011-02-28 16:53:44',NULL,'0000-00-00 00:00:00','Research Interests<span style=\"color:red\">*</span>|Research Interests<span style=\"color:red\">*</span>','textarea_small',NULL,NULL,NULL),(15,'teaching_interests','Teaching Interests',NULL,'2011-02-05 17:04:23',NULL,'0000-00-00 00:00:00','Teaching Interests','textarea_small',NULL,NULL,NULL),(20,'positions_held','Positions Held',NULL,'2011-02-05 17:04:26',NULL,'0000-00-00 00:00:00','Positions Held','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_personal_details_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_personal_details_v0_itemtypes`;

CREATE TABLE `staff_profile_personal_details_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_personal_details_v0_itemtypes` */

insert  into `staff_profile_personal_details_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2011-01-22 16:39:40','text_area','5,50',NULL,NULL),('combo_box','None','oio','2011-01-22 16:40:58','pickone','1,2,3','1,2,3',NULL),('tableColHeader','None','oio','2011-01-22 16:39:40','show_text',' ',NULL,NULL),('blank','None','oio','2011-01-29 16:11:55','except','30',NULL,NULL),('calendar','None','oio','2011-01-22 16:39:40','calendar','',NULL,NULL),('textarea_small','None','oio','2011-01-29 16:04:26','text_area','3,30','',NULL),('month_combo_box','None','oio','2011-01-22 16:41:30','pickone','1,2,3,4,5,6,7,8,9,10,11,12','1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2011-01-22 16:41:41','pickone','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2011-01-22 16:39:40','text_area','15,70',NULL,NULL),('blank_small','None','oio','2011-01-22 16:39:40','except','7',NULL,NULL),('file_upload','None','oio','2011-01-22 16:39:40','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_personal_details_v0_values` */

DROP TABLE IF EXISTS `staff_profile_personal_details_v0_values`;

CREATE TABLE `staff_profile_personal_details_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `focus_area` varchar(255) default NULL,
  `research_interests` text,
  `teaching_interests` text,
  `positions_held` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_personal_details_v0_values` */

/*Table structure for table `staff_profile_professional_body_v0_items` */

DROP TABLE IF EXISTS `staff_profile_professional_body_v0_items`;

CREATE TABLE `staff_profile_professional_body_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_professional_body_v0_items` */

insert  into `staff_profile_professional_body_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'professional_body_name','Name/Details_of_Professional_Body/Society ',NULL,'2011-03-21 19:17:35',NULL,'0000-00-00 00:00:00','Name/Details of The Professional Body/Society<span style=\"color:red\">*</span>|Name of Professional Body<span style=\"color:red\">*</span>','combo_box_professional_body',NULL,NULL,NULL),(10,'membership_from','Membership_From',NULL,'2011-01-28 12:58:58',NULL,'0000-00-00 00:00:00','Membership From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(15,'membership_to','Membership_To',NULL,'2011-01-28 12:59:09',NULL,'0000-00-00 00:00:00','Membership To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'membership_type','Membership_Type',NULL,'2011-01-28 15:24:06',NULL,'0000-00-00 00:00:00','Membership Type','combo_box_membership_types',NULL,NULL,NULL),(25,'membership_number','Membership_Number',NULL,'2011-01-22 09:49:38',NULL,'0000-00-00 00:00:00','Membership Number','blank',NULL,NULL,NULL),(30,'other_details','Other_Details',NULL,'2011-01-22 14:10:47',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(35,'upload','Upload_File',NULL,'2011-01-17 16:04:04',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(40,'url','URL_if_any',NULL,'2011-01-17 16:05:31',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL);

/*Table structure for table `staff_profile_professional_body_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_professional_body_v0_itemtypes`;

CREATE TABLE `staff_profile_professional_body_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_professional_body_v0_itemtypes` */

insert  into `staff_profile_professional_body_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:45:59','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('combo_box_membership_types','None','oio','2011-03-09 18:53:19','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Membership\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Membership\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_professional_body','None','oio','2011-03-11 12:29:11','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Professional_Body_Name\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Professional_Body_Name\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_professional_body_v0_values` */

DROP TABLE IF EXISTS `staff_profile_professional_body_v0_values`;

CREATE TABLE `staff_profile_professional_body_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `professional_body_name` varchar(255) default NULL,
  `membership_from` varchar(255) default NULL,
  `membership_to` varchar(255) default NULL,
  `membership_type` varchar(255) default NULL,
  `membership_number` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_professional_body_v0_values` */

/*Table structure for table `staff_profile_projects_v0_items` */

DROP TABLE IF EXISTS `staff_profile_projects_v0_items`;

CREATE TABLE `staff_profile_projects_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_projects_v0_items` */

insert  into `staff_profile_projects_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'project_status','Project Status',NULL,'2011-03-21 20:10:51',NULL,'0000-00-00 00:00:00','Project Status<span style=\"color:red\">*</span>|Status<span style=\"color:red\">*</span>','radio_status',NULL,NULL,NULL),(10,'project_title','Project Title',NULL,'2011-03-21 20:05:09',NULL,'0000-00-00 00:00:00','Project Title<span style=\"color:red\">*</span>|Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'principal_investigator','Principal Investigator(PI)',NULL,'2011-03-21 20:06:14',NULL,'0000-00-00 00:00:00','Principal Investigator(PI)<span style=\"color:red\">*</span>|PI<span style=\"color:red\">*</span>','combo_box_PI',NULL,NULL,NULL),(20,'start_date','Project Start Date',NULL,'2011-03-21 20:08:02',NULL,'0000-00-00 00:00:00','Project Start Date<span style=\"color:red\">*</span>|Start Date<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(25,'duration','Duration',NULL,'2011-01-28 10:29:16',NULL,'0000-00-00 00:00:00','Duration<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'amount','Amount',NULL,'2011-01-28 10:29:29',NULL,'0000-00-00 00:00:00','Amount<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(35,'project_center_department','Which Center/Department the project is associated with',NULL,'2011-03-21 20:09:34',NULL,'0000-00-00 00:00:00','Which Center/Department the project is associated with<span style=\"color:red\">*</span>|Center/Dept.<span style=\"color:red\">*</span>','combo_box_dept',NULL,NULL,NULL),(40,'abstract','Abstract',NULL,'2011-01-28 10:29:46',NULL,'0000-00-00 00:00:00','Abstract<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(45,'funding_source','Source of Funding',NULL,'2011-03-11 15:08:42',NULL,'0000-00-00 00:00:00','Source of Funding','combo_box_funding_source',NULL,NULL,NULL),(50,'url','URL if any',NULL,'2011-01-20 13:30:17',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(55,'other_details','Other Details',NULL,'2011-01-22 13:55:12',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(60,'upload','Upload File',NULL,'2011-03-26 12:10:07',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(16,'co_pi','Co-PI(s)',NULL,'2011-03-26 12:12:00',NULL,'0000-00-00 00:00:00','Co-PI(s)','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_projects_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_projects_v0_itemtypes`;

CREATE TABLE `staff_profile_projects_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_projects_v0_itemtypes` */

insert  into `staff_profile_projects_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_funding_source','None','oio','2011-03-11 15:08:42','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:46:07','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_status','None','oio','2011-01-20 13:37:02','pickone_radio','Ongoing,Completed','Ongoing,Completed',NULL),('combo_box_PI','None','oio','2011-03-14 21:32:21','pickone_master','SELECT name FROM investigator_master WHERE active_yes_no=1 ORDER BY name','SELECT id FROM investigator_master WHERE active_yes_no=1 ORDER BY name',NULL),('combo_box_dept','None','oio','2011-03-14 17:40:38','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Department\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Department\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_projects_v0_values` */

DROP TABLE IF EXISTS `staff_profile_projects_v0_values`;

CREATE TABLE `staff_profile_projects_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `project_status` varchar(255) default NULL,
  `project_title` varchar(255) default NULL,
  `principal_investigator` varchar(255) default NULL,
  `start_date` varchar(255) default NULL,
  `duration` varchar(255) default NULL,
  `amount` varchar(255) default NULL,
  `project_center_department` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `funding_source` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL,
  `co_pi` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author` varchar(255) default NULL,
  `research_expertise_area` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_projects_v0_values` */

/*Table structure for table `staff_profile_qualification_v0_items` */

DROP TABLE IF EXISTS `staff_profile_qualification_v0_items`;

CREATE TABLE `staff_profile_qualification_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_qualification_v0_items` */

insert  into `staff_profile_qualification_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'course_name','Course Name',NULL,'2011-01-22 16:43:43',NULL,'0000-00-00 00:00:00','Qualification<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'institution','Institution',NULL,'2011-01-22 09:50:09',NULL,'0000-00-00 00:00:00','Institution<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'location','Location',NULL,'2011-01-29 11:25:55',NULL,'0000-00-00 00:00:00','Location<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'board','Board',NULL,'2011-01-29 11:25:57',NULL,'0000-00-00 00:00:00','Board<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'join_month','Month',NULL,'2011-01-29 11:25:58',NULL,'0000-00-00 00:00:00','Month<span style=\"color:red\">*</span>','month_combo_box',NULL,NULL,NULL),(30,'join_year','Year',NULL,'2011-01-29 11:26:05',NULL,'0000-00-00 00:00:00','Year<span style=\"color:red\">*</span>','year_combo_box',NULL,NULL,NULL),(35,'passing_year','Passing Year',NULL,'2011-01-29 11:26:35',NULL,'0000-00-00 00:00:00','Passing Year','blank',NULL,NULL,NULL),(40,'percentage','Percentage',NULL,'2011-01-29 11:27:06',NULL,'0000-00-00 00:00:00','Percentage','blank',NULL,NULL,NULL),(45,'class','Class',NULL,'2011-01-29 11:26:39',NULL,'0000-00-00 00:00:00','Class','blank',NULL,NULL,NULL);

/*Table structure for table `staff_profile_qualification_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_qualification_v0_itemtypes`;

CREATE TABLE `staff_profile_qualification_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_qualification_v0_itemtypes` */

insert  into `staff_profile_qualification_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:17','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2011-01-24 17:00:38','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('blank_small','None','oio','2010-12-06 10:02:44','except','7','',NULL);

/*Table structure for table `staff_profile_qualification_v0_values` */

DROP TABLE IF EXISTS `staff_profile_qualification_v0_values`;

CREATE TABLE `staff_profile_qualification_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `course_name` varchar(255) default NULL,
  `institution` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `board` varchar(255) default NULL,
  `join_month` varchar(255) default NULL,
  `join_year` varchar(255) default NULL,
  `passing_year` varchar(255) default NULL,
  `percentage` varchar(255) default NULL,
  `class` varchar(255) default NULL,
  `mainSubj` varchar(255) default NULL,
  `co_pi` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_qualification_v0_values` */

/*Table structure for table `staff_profile_report_v0_items` */

DROP TABLE IF EXISTS `staff_profile_report_v0_items`;

CREATE TABLE `staff_profile_report_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_items` */

insert  into `staff_profile_report_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (65,'upload_resume','Upload Resume :',NULL,'2011-02-09 18:21:23',NULL,'0000-00-00 00:00:00','Upload Resume','file_upload',NULL,NULL,NULL),(5,'focus_area','Area of Study / Focus Area',NULL,'2011-01-29 16:01:23',NULL,'0000-00-00 00:00:00','Area of Study / Focus Area<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'qualification','Qualification',NULL,'2011-01-13 16:46:59',NULL,'0000-00-00 00:00:00','Qualification','qualification_childform',NULL,NULL,NULL),(15,'research_interests','Research Interests',NULL,'2011-01-29 16:04:49',NULL,'0000-00-00 00:00:00','Research Interests','textarea_small',NULL,NULL,NULL),(20,'teaching_interests','Teaching Interests',NULL,'2011-01-29 16:05:20',NULL,'0000-00-00 00:00:00','Teaching Interests','textarea_small',NULL,NULL,NULL),(25,'positions_held','Positions Held',NULL,'2011-01-29 16:05:22',NULL,'0000-00-00 00:00:00','Positions Held','textarea_small',NULL,NULL,NULL),(30,'office_room_num','Office Room Number',NULL,'2011-01-25 12:07:32',NULL,'0000-00-00 00:00:00','Office Room Number<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(35,'office_phone_ext','Office Phone Ext.',NULL,'2011-01-25 12:08:22',NULL,'0000-00-00 00:00:00','Office Phone Ext.<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(40,'email_id','Amrita Email ID',NULL,'2011-01-25 12:08:25',NULL,'0000-00-00 00:00:00','Email ID<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(45,'email','Personal Email ID',NULL,'2011-01-13 11:30:47',NULL,'0000-00-00 00:00:00','Personal Email ID','blank',NULL,NULL,NULL),(50,'home_phone','Home Phone',NULL,'2011-01-13 11:31:00',NULL,'0000-00-00 00:00:00','Home Phone','blank',NULL,NULL,NULL),(55,'fax','Fax',NULL,'2011-01-13 11:31:06',NULL,'0000-00-00 00:00:00','Fax','blank',NULL,NULL,NULL),(70,'awards','Award Details',NULL,'2011-01-22 12:46:39',NULL,'0000-00-00 00:00:00','Awards Details','awards_childform',NULL,NULL,NULL),(75,'community_service','Community Service',NULL,'2011-01-22 12:46:40',NULL,'0000-00-00 00:00:00','Community Service Details','community_service_childform',NULL,NULL,NULL),(80,'review_committees','Review Committees',NULL,'2011-01-22 12:46:41',NULL,'0000-00-00 00:00:00','Review Committees Details','review_committees_childform',NULL,NULL,NULL),(85,'governance','Governance',NULL,'2011-01-22 12:46:44',NULL,'0000-00-00 00:00:00','Governance Details','governance_childform',NULL,NULL,NULL),(90,'consultancy_offered','Consultancy Offered',NULL,'2011-01-22 12:46:45',NULL,'0000-00-00 00:00:00','Consultancy Offered Details','consultancy_offered_childform',NULL,NULL,NULL),(95,'professional_body','Professional Bodies',NULL,'2011-01-22 12:46:47',NULL,'0000-00-00 00:00:00','Professional Bodies/Societies Details','professional_body_childform',NULL,NULL,NULL),(100,'faculty_exchange','Faculty_Exchange',NULL,'2011-01-22 12:46:48',NULL,'0000-00-00 00:00:00','Faculty Exchange Program Details','faculty_exchange_childform',NULL,NULL,NULL),(105,'thesis','Thesis/Student Project',NULL,'2011-01-22 12:46:49',NULL,'0000-00-00 00:00:00','Thesis/Student Project Details','thesis_childform',NULL,NULL,NULL),(110,'media_publication','Media Publication',NULL,'2011-01-22 12:46:51',NULL,'0000-00-00 00:00:00','Media Publication Details','media_publication_childform',NULL,NULL,NULL),(115,'patents','Patents',NULL,'2011-01-22 12:46:54',NULL,'0000-00-00 00:00:00','Patents Details','patents_childform',NULL,NULL,NULL),(120,'projects','Projects',NULL,'2011-01-22 12:46:56',NULL,'0000-00-00 00:00:00','Projects Details','projects_childform',NULL,NULL,NULL),(125,'training','Training',NULL,'2011-01-22 12:46:58',NULL,'0000-00-00 00:00:00','Seminars/Conferences/Workshops/Training Attended Details','training_childform',NULL,NULL,NULL),(130,'talks_lectures','Talks Lectures',NULL,'2011-01-22 12:47:00',NULL,'0000-00-00 00:00:00','Invited Talks/Guest Lectures Details','talks_lectures_childform',NULL,NULL,NULL),(140,'books_chapter','Books/Chapter',NULL,'2011-01-22 12:47:02',NULL,'0000-00-00 00:00:00','Books/Chapter Details','books_chapter_childform',NULL,NULL,NULL),(145,'conference_papers','Conference Papers',NULL,'2011-01-22 12:47:04',NULL,'0000-00-00 00:00:00','Conference Papers Details','conference_papers_childform',NULL,NULL,NULL),(150,'journal_papers','Journal Papers',NULL,'2011-01-22 12:47:07',NULL,'0000-00-00 00:00:00','Journal Papers Details','journal_papers_childform',NULL,NULL,NULL),(155,'personal_details','Personal Details',NULL,'2011-02-05 17:18:00',NULL,'0000-00-00 00:00:00','Personal Details','personal_details_childform',NULL,NULL,NULL),(160,'upload_photo','Upload Photo',NULL,'2011-02-09 18:03:27',NULL,'0000-00-00 00:00:00','Upload Photo','file_upload',NULL,NULL,NULL),(170,'academic_term','Choose Academic term',NULL,'2011-03-16 15:16:55',NULL,'0000-00-00 00:00:00','Choose Academic term','combo_box_academic_term',NULL,NULL,NULL);

/*Table structure for table `staff_profile_report_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_report_v0_itemtypes`;

CREATE TABLE `staff_profile_report_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_itemtypes` */

insert  into `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2011-01-22 16:39:40','text_area','5,50',NULL,NULL),('combo_box','None','oio','2011-01-22 16:40:58','pickone','1,2,3','1,2,3',NULL),('tableColHeader','None','oio','2011-01-22 16:39:40','show_text',' ',NULL,NULL),('blank','None','oio','2011-01-29 16:11:55','except','30',NULL,NULL),('calendar','None','oio','2011-01-22 16:39:40','calendar','',NULL,NULL),('textarea_small','None','oio','2011-01-29 16:04:26','text_area','3,30','',NULL),('month_combo_box','None','oio','2011-01-22 16:41:30','pickone','1,2,3,4,5,6,7,8,9,10,11,12','1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2011-01-22 16:41:41','pickone','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('paper_status_combo_box','None','oio','2011-01-22 16:41:59','pickone','Select,Communicated,Accepted,Published','Select,Communicated,Accepted,Published',NULL),('paper_type_combo_box','None','oio','2011-01-22 16:42:11','pickone','Select,International,National','Select,International,National',NULL),('jouranltype_combo_box','None','oio','2011-01-22 16:42:22','pickone','Select,Journal,Conference Proceedings','Select,Journal,Conference Proceedings',NULL),('seminar_purpose_combo_box','None','oio','2011-01-22 16:42:29','pickone','Select,Presented Paper,Participation','Select,Presented Paper,Participation',NULL),('textarea_large','None','oio','2011-01-22 16:39:40','text_area','15,70',NULL,NULL),('blank_small','None','oio','2011-01-22 16:39:40','except','7',NULL,NULL),('file_upload','None','oio','2011-02-09 18:15:56','file_upload','','',NULL),('qualification_childform','Qualification','oio','2011-03-28 13:42:42','detail_form','staff_profile_qualification_v0','course_name,institution,location,board,join_month,join_year,passing_year,percentage,class',NULL),('awards_childform','Awards','oio','2011-03-28 13:23:48','detail_form','staff_profile_awards_v0','award_name,agency_name,receiving_month_year',NULL),('community_service_childform','Community Service','oio','2011-03-28 13:23:55','detail_form','staff_profile_community_service_v0','event_name,location,activity_month_year',NULL),('review_committees_childform','Review Committees','oio','2011-04-08 13:52:19','detail_form','staff_profile_review_committees_v0','title,appointed_month_year',NULL),('governance_childform','Governance','oio','2011-03-28 13:24:51','detail_form','staff_profile_governance_v0','committee_name,school_name,service_dates_from,service_dates_to',NULL),('consultancy_offered_childform','Consultancy Offered','oio','2011-03-28 13:24:58','detail_form','staff_profile_consultancy_offered_v0','client_name,duration_from,duration_to',NULL),('professional_body_childform','Professional Bodies','oio','2011-04-13 12:54:17','detail_form','staff_profile_professional_body_v0','membership_from,membership_to',NULL),('faculty_exchange_childform','Faculty Exchange','oio','2011-03-28 13:25:09','detail_form','staff_profile_faculty_exchange_v0','institution_name,subjects_taught,duration_from,duration_to',NULL),('thesis_childform','Student Project','oio','2011-03-28 13:25:14','detail_form','staff_profile_thesis_v0','choose,thesis_title,project_title',NULL),('media_publication_childform','Media Publication','oio','2011-04-13 12:51:55','detail_form','staff_profile_media_publication_v0','title,magazine_name,article_month_year',NULL),('patents_childform','Patents','oio','2011-04-13 12:51:11','detail_form','staff_profile_patents_v0','choose_patent,patent_title,registration_month_year',NULL),('projects_childform','Projects','oio','2011-04-13 12:51:17','detail_form','staff_profile_projects_v0','project_status,project_title,start_date,duration,amount',NULL),('training_childform','Seminar','oio','2011-03-28 15:47:26','detail_form','staff_profile_training_v0','training_name,duration_from,duration_to,location',NULL),('talks_lectures_childform','Invited Talks','oio','2011-03-28 13:26:19','detail_form','staff_profile_talks_lectures_v0','institution_address,event_name,lecture_topic,talk_date_time',NULL),('books_chapter_childform','Books/Chapters','oio','2011-03-28 13:26:30','detail_form','staff_profile_books_chapter_v0','book_chapter,title,author1,publication_month_year',NULL),('conference_papers_childform','Conference Papers','oio','2011-04-05 16:50:01','detail_form','staff_profile_conference_papers_v0','conference_type,paper_title,author1',NULL),('journal_papers_childform','Publications','oio','2011-04-05 14:21:31','detail_form','staff_profile_journal_papers_v0','journal_type,paper_title,author1,volume,issue_no,month_year',NULL),('personal_details_childform','Profile','oio','2011-03-28 15:34:44','detail_form','staff_profile_personal_details_v0','focus_area,research_interests,teaching_interests,positions_held',NULL),('combo_box_academic_term','Academic Term','oio','2011-03-28 13:42:53','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_report_v0_values` */

DROP TABLE IF EXISTS `staff_profile_report_v0_values`;

CREATE TABLE `staff_profile_report_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `RoomNoQuartersNo` varchar(255) default NULL,
  `PhoneResidenceHostel` varchar(255) default NULL,
  `upload_resume` varchar(255) default NULL,
  `focus_area` varchar(255) default NULL,
  `qualification` varchar(255) default NULL,
  `research_interests` varchar(255) default NULL,
  `teaching_interests` varchar(255) default NULL,
  `positions_held` varchar(255) default NULL,
  `office_room_num` varchar(255) default NULL,
  `office_phone_ext` varchar(255) default NULL,
  `email_id` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `home_phone` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `awards` varchar(255) default NULL,
  `community_service` varchar(255) default NULL,
  `review_committees` varchar(255) default NULL,
  `governance` varchar(255) default NULL,
  `consultancy_offered` varchar(255) default NULL,
  `professional_body` varchar(255) default NULL,
  `faculty_exchange` varchar(255) default NULL,
  `thesis` varchar(255) default NULL,
  `media_publication` varchar(255) default NULL,
  `patents` varchar(255) default NULL,
  `projects` varchar(255) default NULL,
  `training` varchar(255) default NULL,
  `talks_lectures` varchar(255) default NULL,
  `books_chapter` varchar(255) default NULL,
  `conference_papers` varchar(255) default NULL,
  `journal_papers` varchar(255) default NULL,
  `personal_details` varchar(255) default NULL,
  `upload_photo` varchar(255) default NULL,
  `academic_term` varchar(255) default NULL,
  KEY `idx_Rad_val` (`idf`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_report_v0_values` */

/*Table structure for table `staff_profile_review_committees_v0_items` */

DROP TABLE IF EXISTS `staff_profile_review_committees_v0_items`;

CREATE TABLE `staff_profile_review_committees_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_review_committees_v0_items` */

insert  into `staff_profile_review_committees_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'committee_type','Committee_Type',NULL,'2011-01-29 11:03:44',NULL,'0000-00-00 00:00:00','Commitee Type<span style=\"color:red\">*</span>','committee_type_combo_box',NULL,NULL,NULL),(10,'role_played','Role_Played',NULL,'2011-01-29 11:03:46',NULL,'0000-00-00 00:00:00','Role Played<span style=\"color:red\">*</span>','role_played_combo_box',NULL,NULL,NULL),(15,'title','Title',NULL,'2011-01-29 11:06:21',NULL,'0000-00-00 00:00:00','Title<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'url','URL',NULL,'2011-01-12 16:48:39',NULL,'0000-00-00 00:00:00','URL','blank',NULL,NULL,NULL),(25,'other_details','Other_Details',NULL,'2011-01-22 14:08:41',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(30,'file_upload','File_Upload',NULL,'2011-01-15 15:08:44',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(35,'appointed_month_year','Month and Year Appointed',NULL,'2011-01-29 11:05:44',NULL,'0000-00-00 00:00:00','Appointed Month and Year<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL);

/*Table structure for table `staff_profile_review_committees_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_review_committees_v0_itemtypes`;

CREATE TABLE `staff_profile_review_committees_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_review_committees_v0_itemtypes` */

insert  into `staff_profile_review_committees_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box','None','oio','2010-12-04 10:02:17','pickone','1,2,3','1,2',NULL),('blank','None','oio','2011-01-29 16:46:37','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('committee_type_combo_box','None','oio','2011-03-11 13:26:47','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Committee_Type\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Committee_Type\' AND active_yes_no=1 ORDER BY fld_value',NULL),('role_played_combo_box','None','oio','2011-03-11 13:26:47','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Role_Played\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Role_Played\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_review_committees_v0_values` */

DROP TABLE IF EXISTS `staff_profile_review_committees_v0_values`;

CREATE TABLE `staff_profile_review_committees_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `committee_type` varchar(255) default NULL,
  `role_played` varchar(255) default NULL,
  `title` varchar(100) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `file_upload` varchar(255) default NULL,
  `appointed_month_year` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_review_committees_v0_values` */

/*Table structure for table `staff_profile_talks_lectures_v0_items` */

DROP TABLE IF EXISTS `staff_profile_talks_lectures_v0_items`;

CREATE TABLE `staff_profile_talks_lectures_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_talks_lectures_v0_items` */

insert  into `staff_profile_talks_lectures_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'event','Was the event :\"National\" or \"International\"',NULL,'2011-01-22 09:51:14',NULL,'0000-00-00 00:00:00','Was the event :\"National\" or \"International\"','radio_event',NULL,NULL,NULL),(10,'institution_address','Name and Address of the Institution',NULL,'2011-01-28 09:41:57',NULL,'0000-00-00 00:00:00','Name and Address of the Institution<span style=\"color:red\">*</span>','textarea_small',NULL,NULL,NULL),(15,'event_name','Name of the event',NULL,'2011-01-28 09:42:14',NULL,'0000-00-00 00:00:00','Name of the event<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'lecture_topic','Lecture Topic',NULL,'2011-01-28 09:42:29',NULL,'0000-00-00 00:00:00','Lecture Topic<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'talk_date_time','Date and Time of Talk',NULL,'2011-01-28 09:42:40',NULL,'0000-00-00 00:00:00','Date and Time of Talk<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(30,'participants_level','Participants Level',NULL,'2011-03-11 14:29:07',NULL,'0000-00-00 00:00:00','Participants Level','combo_box_participants_level',NULL,NULL,NULL),(35,'related_research_group','Related Research Group and Centres',NULL,'2011-01-20 18:04:11',NULL,'0000-00-00 00:00:00','Related Research Group and Centres','blank',NULL,NULL,NULL),(50,'url','URL if any',NULL,'2011-01-20 18:04:37',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(55,'other_details','Other Details',NULL,'2011-01-22 13:58:15',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(60,'upload','Upload File',NULL,'2011-01-20 18:04:49',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(40,'duration_from','Duration From',NULL,'2011-01-20 18:05:44',NULL,'0000-00-00 00:00:00','Duration From','calendar',NULL,NULL,NULL),(45,'duration_to','Duration To',NULL,'2011-01-20 18:06:27',NULL,'0000-00-00 00:00:00','Duration To','calendar',NULL,NULL,NULL);

/*Table structure for table `staff_profile_talks_lectures_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_talks_lectures_v0_itemtypes`;

CREATE TABLE `staff_profile_talks_lectures_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_talks_lectures_v0_itemtypes` */

insert  into `staff_profile_talks_lectures_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_participants_level','None','oio','2011-03-11 14:29:07','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Participants_Level\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Participants_Level\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:46:47','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_event','None','oio','2011-01-20 17:54:28','pickone_radio','National,International','National,International',NULL);

/*Table structure for table `staff_profile_talks_lectures_v0_values` */

DROP TABLE IF EXISTS `staff_profile_talks_lectures_v0_values`;

CREATE TABLE `staff_profile_talks_lectures_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `event` varchar(255) default NULL,
  `institution_address` text,
  `event_name` varchar(255) default NULL,
  `lecture_topic` varchar(255) default NULL,
  `talk_date_time` varchar(255) default NULL,
  `participants_level` varchar(255) default NULL,
  `related_research_group` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_talks_lectures_v0_values` */

/*Table structure for table `staff_profile_thesis_v0_items` */

DROP TABLE IF EXISTS `staff_profile_thesis_v0_items`;

CREATE TABLE `staff_profile_thesis_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_thesis_v0_items` */

insert  into `staff_profile_thesis_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'choose','Choose \"Thesis\" or \"Student Project\"',NULL,'2011-03-21 19:58:14',NULL,'0000-00-00 00:00:00','Choose \"Thesis\" or \"Student Project\"<span style=\"color:red\">*</span>|Type<span style=\"color:red\">*</span>','radio_choose',NULL,NULL,NULL),(10,'thesis_title','Title of Thesis',NULL,'2011-02-08 15:19:17',NULL,'0000-00-00 00:00:00','Title of Thesis<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(15,'thesis_author','Author',NULL,'2011-02-08 15:19:36',NULL,'0000-00-00 00:00:00','Author<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(20,'thesis_department_institution','Department and Institution',NULL,'2011-02-08 15:20:07',NULL,'0000-00-00 00:00:00','Department and Institution<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'thesis_course_programe','Course/Programe',NULL,'2011-02-08 15:20:15',NULL,'0000-00-00 00:00:00','Course/Programe<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(30,'thesis_external_internal','External or Internal',NULL,'2011-02-08 15:20:25',NULL,'0000-00-00 00:00:00','External or Internal<span style=\"color:red\">*</span>','radio_thesis_external_internal',NULL,NULL,NULL),(35,'thesis_registration_month_year','Month and Year of Registration',NULL,'2011-02-08 16:34:26',NULL,'0000-00-00 00:00:00','Month and Year of Registration<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(40,'thesis_outcome','Outcome',NULL,'2011-01-19 11:59:34',NULL,'0000-00-00 00:00:00','Outcome','radio_thesis_outcome',NULL,NULL,NULL),(45,'thesis_completion_month_year','Month and Year of Completion',NULL,'2011-01-18 19:00:32',NULL,'0000-00-00 00:00:00','Month and Year of Completion','calendar',NULL,NULL,NULL),(50,'thesis_url','URL if any',NULL,'2011-01-18 19:01:52',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(55,'thesis_other_details','Other Details',NULL,'2011-01-22 14:11:38',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(60,'thesis_upload','Upload File',NULL,'2011-01-18 19:04:40',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(65,'project_title','Title of Student Project',NULL,'2011-02-08 15:20:47',NULL,'0000-00-00 00:00:00','Title of Student Project<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(70,'project_student_group','Number of Student/Group',NULL,'2011-02-08 15:21:03',NULL,'0000-00-00 00:00:00','Number of Student/Group<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(75,'project_student_name','Name of Students',NULL,'2011-02-08 15:21:10',NULL,'0000-00-00 00:00:00','Name of Students<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(80,'project_type','Project Type',NULL,'2011-03-22 18:01:54',NULL,'0000-00-00 00:00:00','Project Type<span style=\"color:red\">*</span>','combo_box_project_type',NULL,NULL,NULL),(85,'project_academic_term','Choose Academic Term when Project Started',NULL,'2011-03-16 18:28:05',NULL,'0000-00-00 00:00:00','Choose Academic Term when Project Started','combo_box_project_academic_term',NULL,NULL,NULL),(90,'project_start_month_year','Month and Year of Start',NULL,'2011-02-08 15:21:54',NULL,'0000-00-00 00:00:00','Month and Year of Start<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(95,'project_outcome','Outcome',NULL,'2011-01-19 12:00:07',NULL,'0000-00-00 00:00:00','Outcome','radio_project_outcome',NULL,NULL,NULL),(100,'project_completion_month_year','Month and Year of Completion',NULL,'2011-01-19 09:54:34',NULL,'0000-00-00 00:00:00','Month and Year of Completion','calendar',NULL,NULL,NULL),(105,'project_url','URL if any',NULL,'2011-01-19 09:55:49',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(110,'project_other_details','Other Details',NULL,'2011-01-22 14:11:54',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(115,'project_upload','Upload File',NULL,'2011-01-19 09:59:25',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL);

/*Table structure for table `staff_profile_thesis_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_thesis_v0_itemtypes`;

CREATE TABLE `staff_profile_thesis_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_thesis_v0_itemtypes` */

insert  into `staff_profile_thesis_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_project_academic_term','None','oio','2011-03-16 18:28:05','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Academic_Term\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:46:57','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_choose','None','oio','2011-01-24 09:32:19','pickone_radio_choose','Thesis,Student Project','Thesis,Student Project',NULL),('radio_thesis_external_internal','None','oio','2011-01-19 12:04:31','pickone_radio','External,Internal','External,Internal',NULL),('radio_thesis_outcome','None','oio','2011-01-19 12:06:19','pickone_radio','In Progress,Completed','In Progress,Completed',NULL),('radio_project_outcome','None','oio','2011-01-19 12:07:58','pickone_radio','In Progress,Completed','In Progress,Completed',NULL),('combo_box_project_type','None','oio','2011-03-22 19:30:28','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Project_Type\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Project_Type\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_thesis_v0_values` */

DROP TABLE IF EXISTS `staff_profile_thesis_v0_values`;

CREATE TABLE `staff_profile_thesis_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `choose` varchar(255) default NULL,
  `thesis_title` varchar(255) default NULL,
  `thesis_author` varchar(255) default NULL,
  `thesis_department_institution` varchar(255) default NULL,
  `thesis_course_programe` varchar(255) default NULL,
  `thesis_external_internal` varchar(255) default NULL,
  `thesis_registration_month_year` varchar(255) default NULL,
  `thesis_outcome` varchar(255) default NULL,
  `thesis_completion_month_year` varchar(255) default NULL,
  `thesis_url` varchar(255) default NULL,
  `thesis_other_details` text,
  `thesis_upload` varchar(255) default NULL,
  `project_title` varchar(255) default NULL,
  `project_student_group` varchar(255) default NULL,
  `project_student_name` varchar(255) default NULL,
  `project_type` varchar(255) default NULL,
  `project_academic_term` varchar(255) default NULL,
  `project_start_month_year` varchar(255) default NULL,
  `project_outcome` varchar(255) default NULL,
  `project_completion_month_year` varchar(255) default NULL,
  `project_url` varchar(255) default NULL,
  `project_other_details` text,
  `project_upload` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_thesis_v0_values` */

/*Table structure for table `staff_profile_training_v0_items` */

DROP TABLE IF EXISTS `staff_profile_training_v0_items`;

CREATE TABLE `staff_profile_training_v0_items` (
  `number` double default NULL,
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_filler` varchar(765) default NULL,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `prompt` varchar(765) default NULL,
  `itemtype` varchar(765) default NULL,
  `archived_form_name` varchar(765) default NULL,
  `prioritem` varchar(765) default NULL,
  `nextitem` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_items` */

insert  into `staff_profile_training_v0_items`(`number`,`name`,`description`,`creator`,`time`,`last_filler`,`last_time`,`prompt`,`itemtype`,`archived_form_name`,`prioritem`,`nextitem`) values (5,'training_name','Name of Seminar/Conference/Workshop/Training',NULL,'2011-03-21 20:12:49',NULL,'0000-00-00 00:00:00','Name of Seminar/Conference/Workshop/Training<span style=\"color:red\">*</span>|Name<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(10,'duration_from','Duration From',NULL,'2011-01-28 10:05:40',NULL,'0000-00-00 00:00:00','Duration From<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(15,'duration_to','Duration To',NULL,'2011-03-21 20:13:38',NULL,'0000-00-00 00:00:00','Duration To<span style=\"color:red\">*</span>|To<span style=\"color:red\">*</span>','calendar',NULL,NULL,NULL),(20,'location','Location',NULL,'2011-01-28 10:05:55',NULL,'0000-00-00 00:00:00','Location<span style=\"color:red\">*</span>','blank',NULL,NULL,NULL),(25,'purpose','Purpose of attending the Seminar / Conference',NULL,'2011-03-14 17:20:26',NULL,'0000-00-00 00:00:00','Purpose of attending the Seminar / Conference','combo_box_purpose',NULL,NULL,NULL),(30,'funding_source','Source of Funding',NULL,'2011-03-11 14:51:33',NULL,'0000-00-00 00:00:00','Source of Funding','combo_box_funding_source',NULL,NULL,NULL),(35,'funded_amount','Amount Funded',NULL,'2011-01-20 16:12:47',NULL,'0000-00-00 00:00:00','Amount Funded','blank',NULL,NULL,NULL),(40,'url','URL if any',NULL,'2011-01-20 11:11:04',NULL,'0000-00-00 00:00:00','URL if any','blank',NULL,NULL,NULL),(45,'other_details','Other Details',NULL,'2011-01-22 13:56:52',NULL,'0000-00-00 00:00:00','Other Details','textarea_small',NULL,NULL,NULL),(50,'upload','Upload File',NULL,'2011-01-20 11:58:33',NULL,'0000-00-00 00:00:00','Upload File','file_upload',NULL,NULL,NULL),(26,'paper_title','Title of paper',NULL,'2011-02-25 18:27:55',NULL,'0000-00-00 00:00:00','Title of paper|Paper Title','blank',NULL,NULL,NULL),(27,'author','Author',NULL,'2011-03-23 13:49:57',NULL,'0000-00-00 00:00:00','Author','textarea_small',NULL,NULL,NULL),(28,'research_expertise_area','Research Expertise Area',NULL,'2011-03-10 17:51:43',NULL,'0000-00-00 00:00:00','Research Expertise Area|Research Area','combo_box_specialization',NULL,NULL,NULL),(29,'abstract','Abstract',NULL,'2011-03-22 13:07:15',NULL,'0000-00-00 00:00:00','Abstract','textarea_small',NULL,NULL,NULL);

/*Table structure for table `staff_profile_training_v0_itemtypes` */

DROP TABLE IF EXISTS `staff_profile_training_v0_itemtypes`;

CREATE TABLE `staff_profile_training_v0_itemtypes` (
  `name` varchar(765) default NULL,
  `description` varchar(765) default NULL,
  `creator` varchar(765) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `action` varchar(765) default NULL,
  `choice` blob,
  `code` blob,
  `responsetype` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_itemtypes` */

insert  into `staff_profile_training_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`,`responsetype`) values ('textarea','None','oio','2010-12-04 16:56:17','text_area','5,50','',NULL),('combo_box_funding_source','None','oio','2011-03-11 14:52:54','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Funding_Source\' AND active_yes_no=1 ORDER BY fld_value',NULL),('blank','None','oio','2011-01-29 16:47:09','except','30 ',' ',NULL),('calendar','None','oio','2010-12-04 10:17:55','calendar','','',NULL),('textarea_small','None','oio','2010-12-04 09:28:45','text_area','3,30','',NULL),('month_combo_box','None','oio','2010-12-06 13:38:29','pickone','Select,1,2,3,4,5,6,7,8,9,10,11,12','Select,1,2,3,4,5,6,7,8,9,10,11,12',NULL),('year_combo_box','None','oio','2010-12-06 13:38:18','pickone','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011\r\n','Select,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011',NULL),('textarea_large','None','oio','2010-12-04 17:03:38','text_area','15,70',' ',NULL),('file_upload','None','oio','2011-01-12 16:59:45','file_upload','','',NULL),('radio_choose','None','oio','2011-01-20 11:41:18','pickone_radio','National,International','National,International',NULL),('combo_box_purpose','None','oio','2011-03-10 21:10:44','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Attending_Purpose\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Attending_Purpose\' AND active_yes_no=1 ORDER BY fld_value',NULL),('combo_box_specialization','None','oio','2011-03-10 17:55:10','pickone_master','SELECT fld_value FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value','SELECT id FROM general_master WHERE category=\'Specialization\' AND active_yes_no=1 ORDER BY fld_value',NULL);

/*Table structure for table `staff_profile_training_v0_values` */

DROP TABLE IF EXISTS `staff_profile_training_v0_values`;

CREATE TABLE `staff_profile_training_v0_values` (
  `idf` int(11) default NULL,
  `number` int(11) default NULL,
  `form` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `last_filler` varchar(255) default NULL,
  `data_quality` varchar(255) default NULL,
  `change_group` text,
  `execute_group` text,
  `training_name` varchar(255) default NULL,
  `duration_from` varchar(255) default NULL,
  `duration_to` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `purpose` varchar(255) default NULL,
  `funding_source` varchar(255) default NULL,
  `funded_amount` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `other_details` text,
  `upload` varchar(255) default NULL,
  `abstract` varchar(255) default NULL,
  `paper_title` varchar(255) default NULL,
  `author` varchar(255) default NULL,
  `research_expertise_area` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff_profile_training_v0_values` */

/*Table structure for table `university_master` */

DROP TABLE IF EXISTS `university_master`;

CREATE TABLE `university_master` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `short_name` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `active_yes_no` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `university_master` */

insert  into `university_master`(`id`,`name`,`short_name`,`address`,`active_yes_no`) values (1,'Amrita Viswa Vidhya Peetham','AVVP','Kollam','1'),(2,'Mahatma Gandhi University','MGU','Kottayam','1'),(3,'Indian Institute of Technology','IIT','Kanpur','1'),(4,'Cochin University of Science and Technology','CUSAT','Thrikkakara','1'),(5,'Michigan','Michigan','sss','1'),(6,'Massachusetts Institute of Technology','MIT','Cambridge','1'),(7,'Indira Gandhi National Open University','IGNOU',' Delhi','1'),(8,'Visvesvaraya Technological University','VTU','Belgaum','1'),(9,'Banaras Hindu University','BHU','Varanasi','1'),(10,'Birla Institute of Technology and Science','BITS','Pilani','1');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL default '',
  `email` varchar(100) default NULL,
  `enabled` tinyint(1) NOT NULL default '0',
  `active_yesno` smallint(1) default '0',
  `id` int(11) default NULL,
  `user_full_name` varchar(255) default NULL,
  `institution_id` int(4) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`email`,`enabled`,`active_yesno`,`id`,`user_full_name`,`institution_id`) values ('admin','ec4455636479481fde628b5673d627a44bbe992081c916387ee5a4e8fbdb4e9e','admin@gmail.com',1,0,1,'ADMINISTRATOR',1);

/* Procedure structure for procedure `remove_unused_entities` */

/*!50003 DROP PROCEDURE IF EXISTS  `remove_unused_entities` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `remove_unused_entities`(IN entityId INT)
BEGIN 
	 -- Declare variables used just for cursor and loop control
  DECLARE no_more_rows	BOOLEAN;
  DECLARE loop_cntr	INT DEFAULT 0;
  DECLARE num_rows	INT DEFAULT 0;
  DECLARE tempQry	VARCHAR(2000) DEFAULT '';
  DECLARE entity	VARCHAR(2000) DEFAULT '';
  DECLARE tempQry1	VARCHAR(2000) DEFAULT '';
  -- Declare '_val' variables to read in each record from the cursor
  DECLARE tab_name VARCHAR(255);  
  -- Declare the cursor
  DECLARE all_tabscur CURSOR FOR
	SELECT TABLE_NAME FROM information_schema.tables WHERE table_name LIKE 'staff_profile_%_items' AND TABLE_SCHEMA='nfes';	
  
    
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR NOT FOUND
  SET no_more_rows = TRUE;    
    
  -- 'open' the cursor and capture the number of rows returned
  -- (the 'select' gets invoked when the cursor is 'opened')
  OPEN all_tabscur;
  SELECT FOUND_ROWS() INTO num_rows;
  
  
  the_loop: LOOP
    FETCH  all_tabscur  INTO   tab_name;
    
    
    -- break out of the loop if
      -- 1) there were no records, or
      -- 2) we've processed them all
    IF no_more_rows THEN	
        CLOSE all_tabscur;
        LEAVE the_loop;
    END IF;       
    IF (tab_name!='staff_profile_report_v0_items')      THEN	
	SET tab_name=REPLACE(tab_name,'_items','_values');
	SET entity =REPLACE(REPLACE(tab_name,'staff_profile_',''),'_v0_values','');        	
	SET tempQry=CONCAT('DELETE FROM ',tab_name, ' WHERE  (idf,number) NOT IN ( SELECT `entity_id`,`number` FROM `entity_document_master` WHERE `document_id` IN (');
	SET tempQry=CONCAT(tempQry,'SELECT ',  entity ,' FROM staff_profile_report_v0_values WHERE idf=', entityId,')) AND idf=',entityId,'');                      		
	SELECT tempQry;
	SET @s = tempQry;   
	PREPARE stmt1 FROM @s;	
	-- EXECUTE stmt1;		
	DEALLOCATE PREPARE stmt1;  
	
	SET tempQry=CONCAT('DELETE FROM entity_document_master WHERE document_id IN (');
	SET tempQry=CONCAT(tempQry,'SELECT ',  entity ,' FROM staff_profile_report_v0_values  WHERE idf=', entityId,') AND entity_id=', entityId); 	
	SET @s = tempQry;   
	PREPARE stmt2 FROM @s;	
	-- EXECUTE stmt2;		
	DEALLOCATE PREPARE stmt2;
     END IF;         
    SET loop_cntr = loop_cntr + 1;
  END LOOP the_loop;   
	SELECT tempQry;	     
	SET @s = tempQry;   
	PREPARE stmt1 FROM @s;
	-- EXECUTE stmt1;	
	DEALLOCATE PREPARE stmt1;         
    END */$$
DELIMITER ;

/* Procedure structure for procedure `search_column` */

/*!50003 DROP PROCEDURE IF EXISTS  `search_column` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `search_column`()
BEGIN
  -- Declare variables used just for cursor and loop control
  DECLARE no_more_rows	BOOLEAN;
  DECLARE loop_cntr	INT DEFAULT 0;
  DECLARE num_rows	INT DEFAULT 0;
  DECLARE tempQry	VARCHAR(2000) DEFAULT '';
  -- Declare '_val' variables to read in each record from the cursor
  DECLARE tab_name VARCHAR(255);  
  -- Declare the cursor
  DECLARE all_tabscur CURSOR FOR
	SELECT TABLE_NAME FROM information_schema.tables WHERE table_name LIKE 'staff_profile_%_items'  AND table_name<>'staff_profile_report_v0_items' AND TABLE_SCHEMA='nfes';	
  
    
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR NOT FOUND
  SET no_more_rows = TRUE;    
    
  -- 'open' the cursor and capture the number of rows returned
  -- (the 'select' gets invoked when the cursor is 'opened')
  OPEN all_tabscur;
  SELECT FOUND_ROWS() INTO num_rows;
  
  
  the_loop: LOOP
    FETCH  all_tabscur  INTO   tab_name;
    
    
    -- break out of the loop if
      -- 1) there were no records, or
      -- 2) we've processed them all
    IF no_more_rows THEN	
        CLOSE all_tabscur;
        LEAVE the_loop;
    END IF; 
    IF loop_cntr<>0 THEN	
	SET tempQry = CONCAT(tempQry,' UNION ');	
    END IF;
    SET tempQry = CONCAT(tempQry , 'SELECT ''', tab_name , ''' tabl ,NAME FROM ', tab_name);
    -- SET tempQry = CONCAT(tempQry, tab_name) ;
      -- count the number of times looped
    SET loop_cntr = loop_cntr + 1;
  END LOOP the_loop;
	SET @s = tempQry;   
	PREPARE stmt1 FROM @s;
	EXECUTE stmt1;
	DEALLOCATE PREPARE stmt1;  
    END */$$
DELIMITER ;

/*Table structure for table `staff_profile_personaldetails_v0_values` */

DROP TABLE IF EXISTS `staff_profile_personaldetails_v0_values`;

/*!50001 DROP VIEW IF EXISTS `staff_profile_personaldetails_v0_values` */;
/*!50001 DROP TABLE IF EXISTS `staff_profile_personaldetails_v0_values` */;

/*!50001 CREATE TABLE  `staff_profile_personaldetails_v0_values`(
 `user_full_name` varchar(255) ,
 `idf` int(11) ,
 `office_room_num` varchar(255) ,
 `office_phone_ext` varchar(255) ,
 `email_id` varchar(255) ,
 `email` varchar(255) ,
 `home_phone` varchar(255) ,
 `fax` varchar(255) 
)*/;

/*View structure for view staff_profile_personaldetails_v0_values */

/*!50001 DROP TABLE IF EXISTS `staff_profile_personaldetails_v0_values` */;
/*!50001 DROP VIEW IF EXISTS `staff_profile_personaldetails_v0_values` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `staff_profile_personaldetails_v0_values` AS (select `users`.`user_full_name` AS `user_full_name`,`staff_profile_report_v0_values`.`idf` AS `idf`,`staff_profile_report_v0_values`.`office_room_num` AS `office_room_num`,`staff_profile_report_v0_values`.`office_phone_ext` AS `office_phone_ext`,`staff_profile_report_v0_values`.`email_id` AS `email_id`,`staff_profile_report_v0_values`.`email` AS `email`,`staff_profile_report_v0_values`.`home_phone` AS `home_phone`,`staff_profile_report_v0_values`.`fax` AS `fax` from (`staff_profile_report_v0_values` join `users` on((`users`.`id` = `staff_profile_report_v0_values`.`idf`)))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
