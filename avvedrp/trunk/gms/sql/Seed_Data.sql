INSERT INTO `authority`(`id`,`version`,`active_yes_no`, `authority`,`description`)VALUES('1','0','Y','ROLE_SITEADMIN','ROLE_SITEADMIN');
INSERT INTO `authority`(`id`,`version`,`active_yes_no`, `authority`,`description`)VALUES('2','0','Y','ROLE_PI','Investigator_Role');
	
INSERT INTO gms_settings (NAME,VALUE) VALUES ("Attachments","grails-app/views/appForm/");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("ApplicationForm","grails-app/views/appForm/");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailSubject","Confirmation");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailContent","You have successfully created a site admin");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailHost","192.168.36.10");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailPort","25");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailUserName","");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailPassword","");
INSERT INTO gms_settings (NAME,VALUE) VALUES ("MailFrom","no-reply@yourdomain.com");

/*new Sql added on 14 th Jan 2011 */

INSERT INTO `authority`(`authority`,`description`,`active_yes_no`) VALUES ('ROLE_REVIEWER','ROLE_REVIEWER','Y');
INSERT INTO `authority`(`authority`,`description`,`active_yes_no`) VALUES ('ROLE_STAFF','ROLE_STAFF','Y');


ALTER TABLE `gms_settings`  CHANGE `value` `value` VARCHAR(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT '' NOT NULL;
UPDATE gms_settings SET VALUE='Welcome to Grant Management System (GMS) under NME ICT, MHRD' WHERE NAME='MailSubject';
UPDATE gms_settings SET VALUE='Thank you for registering for the Grant Management System (GMS).\n\n A GMS user account has been created with following details\n' WHERE NAME='MailContent';
INSERT INTO gms_settings(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`,`active_yes_no`) VALUES ( NULL,'',NULL,NULL,NULL,NULL,'MailFooter','GMS is the first open source , enterprise-wide grants management system to be developed under the NME ICT initiative of MHRD . The application is web-based and follows a cloud deployment model and serves to connect all those involved in and responsible for sponsored research administration. \n\nPlease visit our website at http://sakshat.amrita.ac.in/erp/  where you will find an overview of everything you need to know about the National Mission project on ERP under NME ICT. If you have any questions you may write to us at amritaerp@gmail.com\n\nWelcome to the NME ICT Community!\n\nRegards,\nYour ERP Team at Amrita\n\ne-mail: amritaerp@gmail.com\nwebsite: http://sakshat.amrita.ac.in/erp/\n','Y');
INSERT INTO gms_settings(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`,`active_yes_no`) VALUES ( NULL,'',NULL,NULL,NULL,NULL,'isSSL','false','Y');



INSERT INTO gms_settings(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`,`active_yes_no`) VALUES ( NULL,'',NULL,NULL,NULL,NULL,'FullProposalForm','/usr/local/fullProposalForm/','Y');
INSERT INTO gms_settings(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`,`active_yes_no`) VALUES ( NULL,'',NULL,NULL,NULL,NULL,'ProposalForm','proposalApplicationForm.gsp','N');
INSERT INTO gms_settings(`id`,`version`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`,`active_yes_no`) VALUES ( NULL,'',NULL,NULL,NULL,NULL,'ProposalApplicationPath','/usr/local/applicationForm/','Y');
UPDATE `gms_settings` SET `active_yes_no`='Y' WHERE NAME <> 'ProposalForm';

/*new Sql added on 2nd Feb 2011 */

INSERT INTO `authority`(`authority`,`description`,`active_yes_no`) VALUES ('ROLE_FINANCE','ROLE_FINANCE','Y');

/*****Seed Data Added for ProosalCategory table on march 1 2011 by Gurupriya****/

insert  into `proposal_category`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`remarks`) values (1,8,'Y',NULL,NULL,NULL,NULL,'Infrastructure','Ongoing research in emerging technologies');
insert  into `proposal_category`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`remarks`) values (2,2,'Y',NULL,NULL,NULL,NULL,'Research','Procurement, installation and maintenance of equipments');
insert  into `proposal_category`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`remarks`) values (3,2,'Y',NULL,NULL,NULL,NULL,'eContent','Data repositories');
insert  into `proposal_category`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`remarks`) values (4,1,'Y',NULL,NULL,NULL,NULL,'Network Facility','Centralization of data , control flow etc');