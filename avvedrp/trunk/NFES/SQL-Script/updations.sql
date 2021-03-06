UPDATE `staff_profile_patents_v0_itemtypes` 
SET `choice`='SELECT ''-Select-'' `fld_value` FROM DUAL UNION
SELECT fld_value FROM(SELECT fld_value FROM general_master WHERE category=''Country'' AND active_yes_no=1 ORDER BY fld_value)A'
WHERE NAME='combo_box_country';

UPDATE `staff_profile_patents_v0_itemtypes` 
SET `code`='SELECT '''' `id` FROM DUAL UNION
SELECT id FROM(SELECT id FROM general_master WHERE category=''Specialization'' AND active_yes_no=1 ORDER BY fld_value)A'
WHERE NAME='combo_box_country';

INSERT INTO 
staff_profile_talks_lectures_v0_itemtypes
(NAME,description,creator,ACTION,choice,CODE)VALUES(
'datetimepicker','Date And Time Picker Control',
'oio','datetimepicker','','');

UPDATE staff_profile_talks_lectures_v0_items
SET itemtype='datetimepicker' WHERE NAME='talk_date_time';



/*============== Multi language support in approval form*/

INSERT INTO `file_master`(`name`,`active_yes_no`)
VALUES('ApprovalServlet.java',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'loading_status','en','Loading in progress.....',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'university_starts_with','en','University starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'institution_starts_with','en','Institution starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'department_starts_with','en','Department starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'faculty_name_starts_with','en','Faculty Name starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'category','en','Category:',1);



INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'filter_by','en','Approval Status:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'search','en','Search',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_facultyName','en','Faculty Name',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_university','en','University',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_institution','en','Institution',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_department','en','Department',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_category','en','Category',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_details','en','Details',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_aproval_status','en','Approve',1);

/*====================Hindi==============*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'loading_status','hi','प्रतीक्षा करना.....',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'university_starts_with','hi','विश्वविद्यालय के साथ शुरू होता है :',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'institution_starts_with','hi','संस्था के साथ शुरू होता है : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'department_starts_with','hi','विभाग के साथ शुरू होता है :',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'faculty_name_starts_with','hi','उपयोगकर्ता नाम के साथ शुरू होता है :',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'category','hi','विभाग: ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'filter_by','hi','स्वीकृति:',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'search','hi','खोज',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_facultyName','hi','संकाय का नाम',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_university','hi','विश्वविद्यालय ',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_institution','hi','संस्था',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_department','hi','विभाग',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_category','hi','विभाग',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_details','hi','वृत्तांत',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_aproval_status','hi','स्वीकृति',1);

/*============================================Malayalam==============================*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'loading_status','ml','കൃപയോടെ കാക്കുക....',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'university_starts_with','ml','സര്‍വ്വകലാശാല നാമം തുടങ്ങുന്നത് : ',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'institution_starts_with','ml','സ്ഥാപനത്തിന്‍റെ നാമം തുടങ്ങുന്നത് :  ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'department_starts_with','ml','വകുപ്പ്‌ തുടങ്ങുന്നത് :',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'faculty_name_starts_with','ml','ഉപഭോക്തൃനാമം തുടങ്ങുന്നത് : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'category','ml','വിഭാഗം : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'filter_by','ml','അംഗീകാരം:',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'search','ml','അന്വേഷിക്കുക',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_facultyName','ml','ഫാക്കല്‍റ്റിയുടെ പേര് ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_university','ml','സര്‍വ്വകലാശാല',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_institution','ml','സ്ഥാപനം',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_department','ml','വകുപ്പ്‌',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_category','ml','വിഭാഗം',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_details','ml','വിവരങ്ങള്‍',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'col_aproval_status','ml','അംഗീകാരം',1);

/*============================Summary=========================*/

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'summary','en','Records:',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'summary','ml','എണ്ണം:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'summary','hi','गिनती:',1);



INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'approve_selected_records','en','Approve Selected Records',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'approve_selected_records','ml','തിരഞ്ഞെടുത്ത വിവരങ്ങള്‍ക്ക്  അംഗീകാരം കൊടുക്കുക',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ApprovalServlet.java'),
'स्वीकार करना ','hi','सामयिक बनाना',1);


/*=====================admin menu - approval sub menu==============================*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'approval','en','Faculty Records Approval',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'approval','ml','വിവരങ്ങള്‍ പരിശോധിച്ച് അംഗീകാരം നല്കുക',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'approval','hi','विवरण अङ्गीकार ',1);
/*===========================Added fields from AUMS======================================*/
/*-------------------------------------------Title------------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (255,"personal_title","Personal Title","Title","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_title","en","Title",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_title` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_title`="";

/*------------------------------------------Gender-----------------------------------------------------*/
INSERT INTO `staff_profile_report_v0_itemtypes` (`name`,`description`,`creator`,`action`,`choice`,`code`) VALUES ("radio_gender","None","oio","pickone_radio","Male,Female","Male,Female");
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (260,"personal_gender","Personal Gender","Gender","radio_gender");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_gender","en","Gender",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_gender` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_gender`="";

/*---------------------------------------Date Of Birth-------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (265,"personal_date_of_birth","Personal Date Of Birth","Date Of Birth","calendar");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_date_of_birth","en","Date Of Birth",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_date_of_birth` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_date_of_birth`="";
UPDATE `staff_profile_report_v0_itemtypes` SET `code`='' WHERE `name`='calendar' AND `description`='None' AND `creator`='oio' AND `action`='calendar' AND `responsetype` IS NULL;

/*--------------------------------------Joining Date---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (270,"personal_joining_date","Personal Joining Date","Joining Date","calendar");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_joining_date","en","Joining Date",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_joining_date` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_joining_date`="";

/*--------------------------------------Nationality---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (275,"personal_nationality","Personal Nationality","Nationality","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_nationality","en","Nationality",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_nationality` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_nationality`="";

/*--------------------------------------Native Place---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (280,"personal_native_place","Personal Native Place","Native Place","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_native_place","en","Native Place",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_native_place` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_native_place`="";

/*--------------------------------------Native Country---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (285,"personal_native_country","Personal Native Country","Native Country","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_native_country","en","Native Country",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_native_country` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_native_country`="";

/*--------------------------------------Native State---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (290,"personal_native_state","Personal Native State","Native State","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_native_state","en","Native State",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_native_state` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_native_state`="";

/*-----------------------------------------Native District---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (295,"personal_native_district","Personal Native District","Native District","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_native_district","en","Native District",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_native_district` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_native_district`="";

/*------------------------------------------------Community---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (300,"personal_community","Personal Community","Community","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_community","en","Community",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_community` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_community`="";

/*------------------------------------------------Religion---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (305,"personal_religion","Personal Religion","Religion","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_religion","en","Religion",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_religion` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_religion`="";

/*------------------------------------------------Caste---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (310,"personal_caste","Personal Caste","Caste","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_caste","en","Caste",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_caste` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_caste`="";

/*------------------------------------------------Mother Tongue---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (315,"personal_mother_tongue","Personal Mother Tongue","Mother Tongue","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_mother_tongue","en","Mother Tongue",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_mother_tongue` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_mother_tongue`="";

/*------------------------------------------------Marital Status---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (320,"personal_marital_status","Personal Marital Status","Marital Status","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"personal_marital_status","en","Marital Status",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_marital_status` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `personal_marital_status`="";

/*------------------------------------------------PAN Card No---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (325,"dept_pan_card_no","Department PAN Card No","PAN Card No","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"dept_pan_card_no","en","PAN Card No",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `dept_pan_card_no` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `dept_pan_card_no`="";

/*------------------------------------------------Passport No---------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (330,"dept_passport_no","Department Passport No","Passport No","blank");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"dept_passport_no","en","Passport No",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `dept_passport_no` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `dept_passport_no`="";

/*-------------------------------------------Passport Valid Upto------------------------------------------------*/
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (335,"account_passport_valid_upto","Account Passport Valid Upto","Passport Valid Upto","calendar");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"account_passport_valid_upto","en","Passport Valid Upto",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `account_passport_valid_upto` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `account_passport_valid_upto`="";

/*------------------------------------------------Blood Group---------------------------------------------------*/
INSERT INTO `staff_profile_report_v0_itemtypes`(`name`,`description`,`creator`,`time`,`action`,`choice`,`code`) VALUES ("bloodgroup_combo_box","None","oio",NOW(),"pickone","O+,O-,A+,A-,B+,B-,AB+,AB-","O+,O-,A+,A-,B+,B-,AB+,AB-");
INSERT INTO`staff_profile_report_v0_items` (`number`,`name`,`description`,`prompt`,`itemtype`) VALUES (340,"health_blood_group","Health Blood Group","Blood Group","bloodgroup_combo_box");
INSERT INTO `language_localisation` (`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`) VALUES (16,"health_blood_group","en","Blood Group",1);
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `health_blood_group` VARCHAR(255) ;
UPDATE `staff_profile_report_v0_values` SET `health_blood_group`="";

/*===================Correct the formating of Patents tab *=========================*/
 UPDATE `nfes`.`search_result_fields` 
 SET `result_fields`='SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\n  choose_patent,\r\n  CONCAT(\'<b>\',patent_title,\'</b>\'),\r\n  registration_month_year \r\n  FROM staff_profile_patents_v0_values  \r\n INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_patents_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_patents_v0_values.idf\r\nAND entity_document_master.number=staff_profile_patents_v0_values.number \r\nAND entity_document_master.`entity_type`=\'patents\'   \r\n  ' 
 WHERE `id`='11' AND entity='patents';

/*============== Rename the column caption Email as 'User Name' =================*/
UPDATE language_localisation SET language_string="User Name",active_yes_no=1 WHERE id=294;
UPDATE language_localisation SET language_string="उपयोगकर्ता नाम",active_yes_no=1 WHERE id=305;
UPDATE language_localisation SET language_string="ഉപഭോക്തൃനാമം",active_yes_no=1 WHERE id=316;

/*================== Deleting remove_unused_entities===============*/;
DROP PROCEDURE remove_unused_entities;


/*================ Print button localisation in staff list ===============*/



INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='staffList.jsp'),
'print','en','Print',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='staffList.jsp'),
'print','ml','പതിപ്പ്',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='staffList.jsp'),
'print','hi','छापा',1);


/*==========================Search Page :- Department localisation ==============*/

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'department','en','Department',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'department','ml','വകുപ്പ്‌',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'department','hi','विभाग',1);




/*==========================Search Page :- Search by Department localisation ==============*/

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'search_by_department','en','Department',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'search_by_department','ml','വകുപ്പ്‌',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'search_by_department','hi','विभाग',1);

/*==========================Search Page :- Search by faculty name localisation  ==============*/

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'search_by_faculty_name','en','Faculty Name',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'search_by_faculty_name','ml','ഫാക്കല്‍റ്റിയുടെ പേര്:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='search.java'),
'search_by_faculty_name','hi','संकाय का नाम',1);




/*==========================Search Page :-Photo localisation  ==============*/

INSERT INTO language_localisation(
file_code,control_name,language_code,language_string,active_yes_no)
VALUES(
(SELECT  id FROM file_master WHERE NAME='search.java'),
'photo','en','Photo',1);


INSERT INTO language_localisation(
file_code,control_name,language_code,language_string,active_yes_no)
VALUES(
(SELECT  id FROM file_master WHERE NAME='search.java'),
'photo','ml','ചിത്രം',1);

INSERT INTO language_localisation(
file_code,control_name,language_code,language_string,active_yes_no)
VALUES(
(SELECT  id FROM file_master WHERE NAME='search.java'),
'photo','hi','चित्र',1);


/*=====================set the malayam word of search as അന്വേഷിക്കു ===================*/

UPDATE language_localisation SET language_string='അന്വേഷിക്കുക' WHERE language_code='ml' AND control_name='search';


/*==================================deleting stored procedure===================*/
DROP PROCEDURE `search_column`;

/*===================== Approved details in entity_document master ================*/
ALTER TABLE entity_document_master 
ADD(approved_by varchar(50),
approved_date DATETIME);

UPDATE  `entity_document_master` SET approved_by=`last_modified_by`, `approved_date`=`last_modified_date_time`
WHERE `approved_yesno`=1;


/*====================Search Page Approved By Details ===================*/
INSERT INTO language_localisation(
file_code,control_name,language_code,language_string,active_yes_no)
VALUES(
(SELECT  id FROM file_master WHERE NAME='ApprovalServlet.java'),
'approved_by','en','Approved By',1);


INSERT INTO language_localisation(
file_code,control_name,language_code,language_string,active_yes_no)
VALUES(
(SELECT  id FROM file_master WHERE NAME='ApprovalServlet.java'),
'approved_by','ml','അംഗീകാരം കൊടുത്ത ആള്‍ ',1);

INSERT INTO language_localisation(
file_code,control_name,language_code,language_string,active_yes_no)
VALUES(
(SELECT  id FROM file_master WHERE NAME='ApprovalServlet.java'),
'approved_by','hi','स्वीकृति वृत्तांत',1);


/*================Faculty List Modification- user name wise search modified with faculty name search ====================*/
UPDATE language_localisation SET language_string='Faculty Name Starts with :' 
WHERE control_name='user_name_starts' AND language_code='en';

UPDATE language_localisation SET language_string='ഫാക്കല്‍റ്റിയുടെ പേര്  തുടങ്ങുന്നത് :' 
WHERE control_name='user_name_starts' AND language_code='ml';

UPDATE language_localisation SET language_string='संकाय नाम के साथ शुरू होता है :' 
WHERE control_name='user_name_starts' AND language_code='hi';


UPDATE language_localisation SET language_string='Department Starts with :' 
WHERE control_name='department1' AND language_code='en';

UPDATE language_localisation SET language_string='വകുപ്പ്‌ തുടങ്ങുന്നത് :'
WHERE control_name='department1' AND language_code='ml';

UPDATE language_localisation SET language_string='विभाग के साथ शुरू होता है :' 
WHERE control_name='department1' AND language_code='hi';

/*===============Changing the caption of upload image as Upload File ======*/
UPDATE `staff_profile_awards_v0_items` SET prompt='Upload File' ,
description='Upload File'
WHERE NAME='upload_image';

UPDATE language_localisation SET language_string="Upload File"  WHERE  file_code=2 AND control_name='upload_image' AND language_code='en';
UPDATE language_localisation SET language_string="फ़ाइल अपलोड करें" WHERE  file_code=2 AND control_name='upload_image' AND language_code='hi';
UPDATE language_localisation SET language_string="അപ്‌ലോഡ് ഫയല്‍" WHERE  file_code=2 AND control_name='upload_image' AND language_code='ml';


/*==============include URL in all tabs=============================*/

delete from search_result_fields;


insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values(' SELECT staff_profile_masterdetails_v0_values.idf,`staff_profile_masterdetails_v0_values`.user_full_name, \r\nstaff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\'<b>Office Room No:</b>\',office_room_num),\r\nCONCAT(\'<b>Phone Extension :</b>\',office_phone_ext)\r\n,CONCAT(\'<b>E-mail :</b>\',email_id) ,\r\nCONCAT(\'<b>Designation:</b>\',staff_profile_masterdetails_v0_values.Designation)\r\nFROM `staff_profile_report_v0_values`  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` \r\nON `staff_profile_report_v0_values`.idf=staff_profile_masterdetails_v0_values.userid  \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_report_v0_values.idf\r\nAND entity_document_master.number=staff_profile_report_v0_values.number \r\nAND entity_document_master.`entity_type`=\'staff\' ','Office Details','1','1','masterdetails');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,\r\nstaff_profile_masterdetails_v0_values.institution  `institute`  ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nentity_document_master.public_record,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\n course_name, staff_profile_qualification_v0_values.institution,staff_profile_qualification_v0_values.location \r\n FROM staff_profile_qualification_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` \r\nON `staff_profile_qualification_v0_values`.idf=staff_profile_masterdetails_v0_values.userid\r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_qualification_v0_values.idf\r\nAND entity_document_master.number=staff_profile_qualification_v0_values.number \r\nAND entity_document_master.`entity_type`=\'qualification\'   ','Qualification Details','10','2','qualification');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,entity_document_master.document_id,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\'<b>\',award_name,\'</b>\'),CONCAT(\'From \',agency_name),receiving_month_year ,\r\nIF(staff_profile_awards_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_awards_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_awards_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_awards_v0_values \r\n INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_awards_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_awards_v0_values.idf\r\nAND entity_document_master.number=staff_profile_awards_v0_values.number \r\nAND entity_document_master.`entity_type`=\'awards\'   ','Awards Details','15','3','awards');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,  \r\nstaff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.entity_type,\r\nentity_document_master.document_id,\r\nentity_document_master.public_record,\r\n  CONCAT(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1,\r\n  volume,issue_no,month_year,\r\n IF(staff_profile_journal_papers_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_journal_papers_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_journal_papers_v0_values.url,\'</a>\'),\"\") url\r\n  FROM staff_profile_journal_papers_v0_values \r\n    INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_journal_papers_v0_values`.idf=staff_profile_masterdetails_v0_values.userid    AND  journal_type=\'National\'\r\n    INNER JOIN general_master ON  general_master.id=staff_profile_journal_papers_v0_values.journal_name AND category=\'Journal\'        \r\n    INNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_journal_papers_v0_values.idf\r\nAND entity_document_master.number=staff_profile_journal_papers_v0_values.number \r\nAND entity_document_master.`entity_type`=\'journal_papers\'   ','National Publications','20','5','journal_papers');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n  CONCAT(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1 ,month_year,\r\nIF(staff_profile_conference_papers_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_conference_papers_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_conference_papers_v0_values.url,\'</a>\'),\"\") url\r\n  FROM staff_profile_conference_papers_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_conference_papers_v0_values`.idf=staff_profile_masterdetails_v0_values.userid  AND conference_type=\'National\'\r\n  INNER JOIN general_master ON  general_master.id=staff_profile_conference_papers_v0_values.conference_name AND category=\'Conference_Name\'  \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_conference_papers_v0_values.idf\r\nAND entity_document_master.number=staff_profile_conference_papers_v0_values.number \r\nAND entity_document_master.`entity_type`=\'conference_papers\'   ','National Conference Papers','25','6','conference_papers');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('  SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,\r\n  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,DATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n book_chapter,\r\nCONCAT(\'<b>\',title,\'</b>\'),author1,publication_month_year ,\r\nIF(staff_profile_books_chapter_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_books_chapter_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_books_chapter_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_books_chapter_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` \r\nON `staff_profile_books_chapter_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_books_chapter_v0_values.idf\r\nAND entity_document_master.number=staff_profile_books_chapter_v0_values.number \r\nAND entity_document_master.`entity_type`=\'books_chapter\'   \r\n\r\n','Books/Chapters Details','30','7','books_chapter');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,\r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\'<b>\',lecture_topic,\'</b>\'),CONCAT(\' at \', institution_address),event_name,talk_date_time ,\r\nIF(staff_profile_talks_lectures_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_talks_lectures_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_talks_lectures_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_talks_lectures_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_talks_lectures_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_talks_lectures_v0_values.idf\r\nAND entity_document_master.number=staff_profile_talks_lectures_v0_values.number \r\nAND entity_document_master.`entity_type`=\'talks_lectures\'   \r\n\r\n  ','Invited Talks Details','35','8','talks_lectures');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   , \r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\'<b>\',training_name,\'</b>\'),CONCAT(\'Period :\',duration_from,\' to \',duration_to),\r\nCONCAT(\' at \',staff_profile_training_v0_values.location) ,\r\nIF(staff_profile_training_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_training_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_training_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_training_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_training_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_training_v0_values.idf\r\nAND entity_document_master.number=staff_profile_training_v0_values.number \r\nAND entity_document_master.`entity_type`=\'training\'   \r\n  ','Seminar/Training','40','9','training');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution  ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nproject_status,CONCAT(\'<b>\',project_title,\'</b>\'),\r\nCONCAT(\'Start on \',start_date),\r\nCONCAT(\'for \',duration),amount,CONCAT(\'associated with \',general_master.fld_value),abstract ,\r\nIF(staff_profile_projects_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_projects_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_projects_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_projects_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_projects_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `general_master` ON \r\nstaff_profile_projects_v0_values.project_center_department =`general_master`.id \r\nAND `general_master`.category=\'Department\'\r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_projects_v0_values.idf\r\nAND entity_document_master.number=staff_profile_projects_v0_values.number \r\nAND entity_document_master.`entity_type`=\'projects\'   ','Project Details','45','10','projects');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,DATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n  choose_patent,\r\n  CONCAT(\'<b>\',patent_title,\'</b>\'),\r\n  registration_month_year ,\r\nIF(staff_profile_patents_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_patents_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_patents_v0_values.url,\'</a>\'),\"\") url\r\n  FROM staff_profile_patents_v0_values  \r\n INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_patents_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_patents_v0_values.idf\r\nAND entity_document_master.number=staff_profile_patents_v0_values.number \r\nAND entity_document_master.`entity_type`=\'patents\'   \r\n  ','Patent Details','50','11','patents');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n general_master.fld_value,CONCAT(\'<b>\',title,\'</b>\'),magazine_name,article_month_year ,\r\nIF(staff_profile_media_publication_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_media_publication_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_media_publication_v0_values.url,\'</a>\'),\"\") url\r\n FROM staff_profile_media_publication_v0_values  \r\n INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_media_publication_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\n INNER JOIN `general_master` ON `general_master`.id=staff_profile_media_publication_v0_values.media_type AND category=\'Media\'\r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_media_publication_v0_values.idf\r\nAND entity_document_master.number=staff_profile_media_publication_v0_values.number \r\nAND entity_document_master.`entity_type`=\'media_publication\'   ','Media Publication Details','55','12','media_publication');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution \r\n  ,staff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nchoose,CONCAT(\'<b>\',thesis_title,\'</b>\',\'<b>\',project_title,\'</b>\') ,\r\nCONCAT(IF(staff_profile_thesis_v0_values.thesis_url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_thesis_v0_values.thesis_url,\'\'\' target=\'\'blank\'\'>\',staff_profile_thesis_v0_values.thesis_url,\'</a>\'),\"\")\r\n,\' \',IF(staff_profile_thesis_v0_values.project_url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_thesis_v0_values.project_url,\'\'\' target=\'\'blank\'\'>\',staff_profile_thesis_v0_values.thesis_url,\'</a>\'),\"\"))  url\r\nFROM staff_profile_thesis_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_thesis_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_thesis_v0_values.idf\r\nAND entity_document_master.number=staff_profile_thesis_v0_values.number \r\nAND entity_document_master.`entity_type`=\'thesis\'   \r\n  ','Student Project Details','60','13','thesis');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,\r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   , \r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\' taught <b>\',subjects_taught,\'</b>\'),CONCAT(\' from \',duration_from),CONCAT(\' to \',duration_to) ,\r\nIF(staff_profile_faculty_exchange_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_faculty_exchange_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_faculty_exchange_v0_values.url,\'</a>\'),\"\") url\r\nFROM \r\nstaff_profile_faculty_exchange_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_faculty_exchange_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_faculty_exchange_v0_values.idf\r\nAND entity_document_master.number=staff_profile_faculty_exchange_v0_values.number \r\nAND entity_document_master.`entity_type`=\'faculty_exchange\'   \r\n  ','Faculty Exchange Details','65','14','faculty_exchange');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,\r\n  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n CONCAT(\'<b>\',fld_value,\'</b>\'),membership_from,membership_to ,\r\nIF(staff_profile_professional_body_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_professional_body_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_professional_body_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_professional_body_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_professional_body_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN general_master ON general_master.id= staff_profile_professional_body_v0_values.professional_body_name\r\nAND category=\'Professional_Body_Name\'\r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_professional_body_v0_values.idf\r\nAND entity_document_master.number=staff_profile_professional_body_v0_values.number \r\nAND entity_document_master.`entity_type`=\'professional_body\'   ','Professional Bodies Details','70','15','professional_body');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\'<b>\',client_name,\'</b>\'),duration_from,duration_to ,\r\nIF(staff_profile_consultancy_offered_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_consultancy_offered_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_consultancy_offered_v0_values.url,\'</a>\'),\"\") url\r\nFROM staff_profile_consultancy_offered_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_consultancy_offered_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_consultancy_offered_v0_values.idf\r\nAND entity_document_master.number=staff_profile_consultancy_offered_v0_values.number \r\nAND entity_document_master.`entity_type`=\'consultancy_offered\'     ','Consultancy Offered Details','75','16','consultancy_offered');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,\r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   , \r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n CONCAT(\'<b>\',committee_name,\'</b>\'),school_name,\r\nCONCAT(\' from \',service_dates_from),CONCAT(\' to \',service_dates_to),\r\nIF(staff_profile_governance_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_governance_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_governance_v0_values.url,\'</a>\'),\"\") url\r\n FROM staff_profile_governance_v0_values  \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_governance_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_governance_v0_values.idf\r\nAND entity_document_master.number=staff_profile_governance_v0_values.number \r\nAND entity_document_master.`entity_type`=\'governance\'   \r\n\r\n  ','Governance Details','80','17','governance');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,\r\n  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n CONCAT(fld_value,\' of \'),CONCAT(\'<b>\',title,\'</b>\'), CONCAT(\' from \',appointed_month_year ),\r\n IF(staff_profile_review_committees_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_review_committees_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_review_committees_v0_values.url,\'</a>\'),\"\") url\r\n FROM staff_profile_review_committees_v0_values \r\n  INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_review_committees_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `general_master` ON \r\nstaff_profile_review_committees_v0_values.role_played =`general_master`.id \r\nAND `general_master`.category=\'Role_Played\'\r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_review_committees_v0_values.idf\r\nAND entity_document_master.number=staff_profile_review_committees_v0_values.number \r\nAND entity_document_master.`entity_type`=\'review_committees\'   ','Review Committees Details','85','18','review_committees');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nCONCAT(\'<b>\',event_name,\'</b>\'),CONCAT(\' at \',staff_profile_community_service_v0_values.location),\r\nCONCAT(\' on \',activity_month_year) ,\r\nIF(staff_profile_community_service_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_community_service_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_community_service_v0_values.url,\'</a>\'),\"\") url\r\n\r\nFROM staff_profile_community_service_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_community_service_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_community_service_v0_values.idf\r\nAND entity_document_master.number=staff_profile_community_service_v0_values.number \r\nAND entity_document_master.`entity_type`=\'community_service\'   \r\n','Community Service Details','90','19','community_service');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,  \r\nstaff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.entity_type,\r\nentity_document_master.public_record,\r\nentity_document_master.document_id,\r\n  CONCAT(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1,\r\n  volume,issue_no,month_year ,\r\n  IF(staff_profile_journal_papers_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_journal_papers_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_journal_papers_v0_values.url,\'</a>\'),\"\") url \r\n  FROM staff_profile_journal_papers_v0_values \r\n    INNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_journal_papers_v0_values`.idf=staff_profile_masterdetails_v0_values.userid    \r\nAND  journal_type=\'International\'\r\n    INNER JOIN general_master ON  general_master.id=staff_profile_journal_papers_v0_values.journal_name AND category=\'Journal\'        \r\n    INNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_journal_papers_v0_values.idf\r\nAND entity_document_master.number=staff_profile_journal_papers_v0_values.number \r\nAND entity_document_master.`entity_type`=\'journal_papers\'   ','International Publications','21','20','journal_papers');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('    SELECT staff_profile_masterdetails_v0_values.idf,user_full_name,  staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n  CONCAT(\'<b>\',paper_title,\'</b>\'),general_master.fld_value,author1 ,month_year,\r\n  IF(staff_profile_conference_papers_v0_values.url!=\"\",CONCAT(\'<a href=\'\'\',staff_profile_conference_papers_v0_values.url,\'\'\' target=\'\'blank\'\'>\',staff_profile_conference_papers_v0_values.url,\'</a>\'),\"\") url\r\n  FROM staff_profile_conference_papers_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_conference_papers_v0_values`.idf=staff_profile_masterdetails_v0_values.userid AND  conference_type=\'International\' \r\n  INNER JOIN general_master ON  general_master.id=staff_profile_conference_papers_v0_values.conference_name AND category=\'Conference_Name\'  \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_conference_papers_v0_values.idf\r\nAND entity_document_master.number=staff_profile_conference_papers_v0_values.number \r\nAND entity_document_master.`entity_type`=\'conference_papers\'    ','International Conference Papers','26','21','conference_papers');
insert into `search_result_fields` (`result_fields`, `description`, `sequence`, `id`, `entity`) values('     SELECT staff_profile_masterdetails_v0_values.idf,user_full_name, \r\n staff_profile_masterdetails_v0_values.university_id,staff_profile_masterdetails_v0_values.university, \r\n staff_profile_masterdetails_v0_values.institution_id ,staff_profile_masterdetails_v0_values.institution   ,\r\nstaff_profile_masterdetails_v0_values.department,\r\nentity_document_master.number, `entity_document_master`.`approved_yesno`,\r\nentity_document_master.`approved_by`,\r\nDATE_FORMAT(entity_document_master.`approved_date`,\'%d-%m-%Y %H:%i\') approved_date,\r\nentity_document_master.document_id,entity_document_master.entity_type,\r\nentity_document_master.public_record,\r\n     CONCAT(\'<b>Focus Area:</b> \',focus_area),\r\n     CONCAT(\'<b>Research Interests:</b> \',research_interests),\r\n     CONCAT(\'<b>Teaching Interest:</b> \',teaching_interests),\r\n     CONCAT(\'<b>Positions Held: </b>\',positions_held) \r\n     FROM staff_profile_personal_details_v0_values \r\nINNER JOIN `staff_profile_masterdetails_v0_values` ON `staff_profile_personal_details_v0_values`.idf=staff_profile_masterdetails_v0_values.userid   \r\nINNER JOIN `entity_document_master` ON \r\nentity_document_master.`entity_id`=staff_profile_personal_details_v0_values.idf\r\nAND entity_document_master.number=staff_profile_personal_details_v0_values.number \r\nAND entity_document_master.`entity_type`=\'personal_details\'   \r\n  ','Profile Details','5','23','personal_details');




/*=============Removed the mandatory symbol of amount field in Projects Tab =====*/
UPDATE language_localisation SET language_string="Amount"  WHERE  file_code=14 AND control_name='amount' AND language_code='en';
UPDATE language_localisation SET language_string="राशि" WHERE  file_code=14 AND control_name='amount' AND language_code='hi';
UPDATE language_localisation SET language_string="സംഖ്യ" WHERE  file_code=14 AND control_name='amount' AND language_code='ml';

UPDATE `staff_profile_projects_v0_items` SET prompt='Amount' WHERE NAME='amount';


/*===============New For Identifying public and private records ==========*/
ALTER TABLE entity_document_master
   ADD COLUMN (public_record VARCHAR(1) DEFAULT 'Y'); 


/*================ Added New Fields ========================= for creating reports*/
CREATE OR REPLACE VIEW `staff_profile_personaldetails_v0_values` AS 
SELECT
  `staff_profile_report_v0_values`.`idf`              AS `idf`,
  `staff_profile_report_v0_values`.`office_room_num`  AS `office_room_num`,
  `staff_profile_report_v0_values`.`office_phone_ext` AS `office_phone_ext`,
  `staff_profile_report_v0_values`.`email_id`         AS `email_id`,
  `staff_profile_report_v0_values`.`email`            AS `email`,
  `staff_profile_report_v0_values`.`home_phone`       AS `home_phone`,
  `staff_profile_report_v0_values`.`fax`              AS `fax`,
  staff_profile_report_v0_values.personal_date_of_birth AS `DOB`,
  staff_profile_report_v0_values.personal_gender AS `gender`,
  staff_profile_report_v0_values.personal_community AS `community`
FROM `staff_profile_report_v0_values`;


CREATE OR REPLACE VIEW staff_profile_masterdetails_v0_values AS 
SELECT
  users.user_full_name     AS user_full_name,
  users.username           AS username,
  users.email              AS email,
  users.id                 AS userid,
  users.id                 AS idf,
  university_master.name   AS University,
  university_master.id     AS University_id,
  institution_master.name  AS Institution,
  institution_master.id    AS Institution_id,
  general_master.fld_value AS Department,
  A.fld_value              AS Designation
FROM staff_master
        INNER JOIN department_master
          ON department_master.id = staff_master.department_id
       INNER JOIN institution_master
         ON institution_master.id = department_master.institution_id
      INNER JOIN university_master
        ON university_master.id = institution_master.university_id
     INNER JOIN general_master
       ON general_master.id = department_master.department_id
            AND general_master.category = 'Department'
    INNER JOIN general_master AS A
      ON A.id = staff_master.designation_id
           AND A.category = 'Designation'
   INNER JOIN users
     ON staff_master.userid = users.id
          AND users.enabled = 1
          AND users.username <> 'admin';

/*==========================Localisation of public and private faculty records=============*/

INSERT INTO `file_master`(`name`,`active_yes_no`)
VALUES('RecordTypeSettingServlet.java',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'loading_status','en','Loading in progress.....',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'university_starts_with','en','University starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'institution_starts_with','en','Institution starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'department_starts_with','en','Department starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'faculty_name_starts_with','en','Faculty Name starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'category','en','Category:',1);



INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'filter_by','en','Approval Status:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'search','en','Search',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_facultyName','en','Faculty Name',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_university','en','University',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_institution','en','Institution',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_department','en','Department',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_category','en','Category',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_details','en','Details',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_aproval_status','en','Approve',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'record_type','en','Type',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_record_type','en','Public',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'summary','en','Records:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'update_records','en','Update Records',1);


/*===================Hindi=================================*/


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'loading_status','hi','प्रतीक्षा करना.....',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'university_starts_with','hi','विश्वविद्यालय के साथ शुरू होता है :',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'institution_starts_with','hi','संस्था के साथ शुरू होता है : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'department_starts_with','hi','विभाग के साथ शुरू होता है :',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'faculty_name_starts_with','hi','उपयोगकर्ता नाम के साथ शुरू होता है :',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'category','hi','विभाग: ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'filter_by','hi','स्वीकृति:',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'search','hi','खोज',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_facultyName','hi','संकाय का नाम',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_university','hi','विश्वविद्यालय ',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_institution','hi','संस्था',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_department','hi','विभाग',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_category','hi','विभाग',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_details','hi','वृत्तांत',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_aproval_status','hi','स्वीकृति',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'record_type','hi','वर्ग',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_record_type','ml','ടൈപ്പ്',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'summary','hi','गिनती:',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'update_records','hi','सामयिक बनाना',1);



/*============================Malayalam==============================*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'loading_status','ml','കൃപയോടെ കാക്കുക....',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'university_starts_with','ml','സര്‍വ്വകലാശാല നാമം തുടങ്ങുന്നത് : ',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'institution_starts_with','ml','സ്ഥാപനത്തിന്‍റെ നാമം തുടങ്ങുന്നത് :  ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'department_starts_with','ml','വകുപ്പ്‌ തുടങ്ങുന്നത് :',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'faculty_name_starts_with','ml','ഉപഭോക്തൃനാമം തുടങ്ങുന്നത് : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'category','ml','വിഭാഗം : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'filter_by','ml','അംഗീകാരം:',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'search','ml','അന്വേഷിക്കുക',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_facultyName','ml','ഫാക്കല്‍റ്റിയുടെ പേര് ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_university','ml','സര്‍വ്വകലാശാല',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_institution','ml','സ്ഥാപനം',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_department','ml','വകുപ്പ്‌',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_category','ml','വിഭാഗം',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_details','ml','വിവരങ്ങള്‍',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_aproval_status','ml','അംഗീകാരം',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'record_type','ml','ടൈപ്പ്  തിരഞ്ഞെടുക്കുക',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'col_record_type','hi','वर्ग',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'summary','ml','എണ്ണം:',1);



INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='RecordTypeSettingServlet.java'),
'update_records','ml','രേഖപ്പെടുത്തുക',1);

/********** Admin Menu Public and Private Records ================*/

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'public_private_records','en','Public And Private Records of Faculty ',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'public_private_records','ml','ഫേക്കല്‍ടിയുടെ സ്വകാര്യവും അല്ലാത്തതുമായ വിവരങ്ങള്‍',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'public_private_records','hi','व्यक्तिगत तथा साधारण निर्दिष्ट',1);

/*==============Multi langauge Support for admin menu - report=====*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'Reports','en','Reports',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'Reports','ml','റിപ്പോര്‍ട്ട്',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='adminmenu.jsp'),
'Reports','hi','समाचार',1);


INSERT INTO `file_master`(`name`,`active_yes_no`)
VALUES('ReportsMenu.jsp',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ReportsMenu.jsp'),
'faculty_list','en','Faculty List',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ReportsMenu.jsp'),
'faculty_list','hi','संकाय की सूची',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='ReportsMenu.jsp'),
'faculty_list','ml','ഫാക്കല്‍ടി ലിസ്റ്റ്',1);

/*=====================Localisation of StaffReport.jsp ==============*/

INSERT INTO `file_master`(`name`,`active_yes_no`)
VALUES('StaffReport.jsp',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'faculty_list','en','Faculty List',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'faculty_list','hi','संकाय की सूची',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'faculty_list','ml','ഫാക്കല്‍ടി ലിസ്റ്റ്',1);


/*=============english===================*/

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'university_starts_with','en','University starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'institution_starts_with','en','Institution starts with:',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'department_starts_with','en','Department starts with:',1);


/*===========hindi=======================*/


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'university_starts_with','hi','विश्वविद्यालय के साथ शुरू होता है :',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'institution_starts_with','hi','संस्था के साथ शुरू होता है : ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'department_starts_with','hi','विभाग के साथ शुरू होता है :',1);



/*===========malayalam===============*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'university_starts_with','ml','സര്‍വ്വകലാശാല നാമം തുടങ്ങുന്നത് : ',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'institution_starts_with','ml','സ്ഥാപനത്തിന്‍റെ നാമം തുടങ്ങുന്നത് :  ',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'department_starts_with','ml','വകുപ്പ്‌ തുടങ്ങുന്നത് :',1);


/*==================print================*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'print','ml','റിപ്പോര്‍ട്ട് കാണിക്കുക',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'print','en','Show Report',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='StaffReport.jsp'),
'print','hi','तमाशा ',1);

/*=========================store role name in place of role id===================================*/
UPDATE `authorities` SET authority='ROLE_PROFILE_CREATION' WHERE authority="2";
UPDATE `authorities` SET authority='ROLE_ADMIN' WHERE authority="1";


/*===============localization of Register link in login page==========================*/
INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='login.jsp'),
'register','ml','രജിസ്റ്റര്‍',1);


INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='login.jsp'),
'register','en','Register',1);

INSERT INTO `language_localisation`(
`file_code`,`control_name`,`language_code`,`language_string`,`active_yes_no`)
VALUES(
(SELECT  id FROM `file_master` WHERE NAME='login.jsp'),
'register','hi','रजिस्टर',1);

/*====INSERT NEW ROLE INTO roles table=====*/
INSERT INTO roles(role_name,active_yesno)VALUES('ROLE_ADMIN_UNIVERSITY','1');

/*==== INSERT NEW Department INTO general_master table for University admin registration =====*/
INSERT INTO general_master(category,fld_value,active_yes_no) VALUES('Department','Administration',1);

/*==== INSERT NEW Designation INTO general_master table for University admin registration =====*/
INSERT INTO general_master(category,fld_value,active_yes_no) VALUES('Designation','Administrator',1);

/*===============New table for Event Scheduler==========================*/
CREATE TABLE `event_scheduler` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idf` INT(11) DEFAULT NULL,
  `event_date` VARCHAR(255) DEFAULT NULL,
  `event_start_time` VARCHAR(255) DEFAULT NULL,
  `event_end_time` VARCHAR(255) DEFAULT NULL,
  `event_description` VARCHAR(765) DEFAULT NULL,
  PRIMARY KEY  (`id`)
); 


/*================== Removed master term from master tables =================*/

UPDATE `language_localisation` SET `language_string`="Principal Investigator" 
WHERE `file_code`=26 AND `control_name`="principal_investigator_master" 
AND `language_code`="en" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="प्रधान अन्वेषक" 
WHERE `file_code`=26 AND `control_name`="principal_investigator_master" 
AND `language_code`="hi" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="പ്രിന്‍സിപ്പല്‍ ഇന്‍വെസ്റ്റിഗേറ്റര്‍" 
WHERE `file_code`=26 AND `control_name`="principal_investigator_master" 
AND `language_code`="ml" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="Courses Taught‍" 
WHERE `file_code`=26 AND `control_name`="courses_taught_master" 
AND `language_code`="en" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="पाठ्यक्रम सिखाया‍" 
WHERE `file_code`=26 AND `control_name`="courses_taught_master" 
AND `language_code`="hi" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="കോഴ്സസ് തോട്ട്" 
WHERE `file_code`=26 AND `control_name`="courses_taught_master" 
AND `language_code`="ml" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="University‍" 
WHERE `file_code`=26 AND `control_name`="university_master" 
AND `language_code`="en" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="विश्वविद्यालय" 
WHERE `file_code`=26 AND `control_name`="university_master" 
AND `language_code`="hi" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="യൂനിവേഴ്സിറ്റി" 
WHERE `file_code`=26 AND `control_name`="university_master" 
AND `language_code`="ml" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="Institution" 
WHERE `file_code`=26 AND `control_name`="intitution_master" 
AND `language_code`="en" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="संस्था" 
WHERE `file_code`=26 AND `control_name`="intitution_master" 
AND `language_code`="hi" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="ഇന്‍സ്റ്റിറ്റ്യൂഷന്‍" 
WHERE `file_code`=26 AND `control_name`="intitution_master" 
AND `language_code`="ml" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="Institution Department‍" 
WHERE `file_code`=26 AND `control_name`="intitution_department_master" 
AND `language_code`="en" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="संस्था विभाग" 
WHERE `file_code`=26 AND `control_name`="intitution_department_master" 
AND `language_code`="hi" AND `active_yes_no`=1;

UPDATE `language_localisation` SET `language_string`="ഇന്‍സ്റ്റിറ്റ്യൂഷന്‍ ഡിപ്പാര്‍ട്ട്മെന്‍റ്" 
WHERE `file_code`=26 AND `control_name`="intitution_department_master" 
AND `language_code`="ml" AND `active_yes_no`=1;

/*====08-08-2011====*/
UPDATE `file_master` SET NAME='MasterTables.java' WHERE NAME='master_tables.java';


UPDATE `language_localisation` SET `language_string`='ടൈപ്പ്'
WHERE `file_code`=(SELECT  id FROM file_master WHERE NAME='RecordTypeSettingServlet.java')
 AND `control_name`='col_record_type'
AND `language_code`='ml' ;

UPDATE `language_localisation` SET `language_string`='वर्ग'
WHERE `file_code`=(SELECT  id FROM file_master WHERE NAME='RecordTypeSettingServlet.java')
 AND `control_name`='col_record_type'
AND `language_code`='hi' ;