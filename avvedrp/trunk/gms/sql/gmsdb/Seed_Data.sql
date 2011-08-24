INSERT INTO `authority`(`id`,`version`,`active_yes_no`, `authority`,`description`)VALUES('1','0','Y','ROLE_SITEADMIN','ROLE_SITEADMIN');
INSERT INTO `authority`(`id`,`version`,`active_yes_no`, `authority`,`description`)VALUES('2','0','Y','ROLE_PI','Investigator_Role');
	
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (1,0,'Y',NULL,NULL,NULL,NULL,'Attachments','grails-app/views/appForm/');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (2,0,'Y',NULL,NULL,NULL,NULL,'ApplicationForm','grails-app/views/appForm/');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (3,0,'Y',NULL,NULL,NULL,NULL,'MailSubject','Welcome to Grant Management System (GMS) under NME ICT, MHRD');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (4,0,'Y',NULL,NULL,NULL,NULL,'MailContent','Thank you for registering for the Grant Management System (GMS).\n\n A GMS user account has been created with following details\n');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (5,0,'Y',NULL,NULL,NULL,NULL,'MailHost','192.168.36.10');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (6,0,'Y',NULL,NULL,NULL,NULL,'MailPort','25');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (7,0,'Y',NULL,NULL,NULL,NULL,'MailUserName','');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (8,0,'Y',NULL,NULL,NULL,NULL,'MailPassword','');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (9,0,'Y',NULL,NULL,NULL,NULL,'MailFrom','no-reply@yourdomain.com');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (10,0,'Y',NULL,NULL,NULL,NULL,'MailFooter','GMS is the first open source , enterprise-wide grants management system to be developed under the NME ICT initiative of MHRD . The application is web-based and follows a cloud deployment model and serves to connect all those involved in and responsible for sponsored research administration. \n\nPlease visit our website at http://sakshat.amrita.ac.in/erp/  where you will find an overview of everything you need to know about the National Mission project on ERP under NME ICT. If you have any questions you may write to us at amritaerp@gmail.com\n\nWelcome to the NME ICT Community!\n\nRegards,\nYour ERP Team at Amrita\n\ne-mail: amritaerp@gmail.com\nwebsite: http://sakshat.amrita.ac.in/erp/\n');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (11,0,'Y',NULL,NULL,NULL,NULL,'isSSL','false');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (12,0,'Y',NULL,NULL,NULL,NULL,'FullProposalForm','/usr/local/fullProposalForm/');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (13,0,'N',NULL,NULL,NULL,NULL,'ProposalForm','proposalApplicationForm.gsp');
insert  into `gms_settings`(`id`,`version`,`active_yes_no`,`created_by`,`created_date`,`modified_by`,`modified_date`,`name`,`value`) values (14,0,'Y',NULL,NULL,NULL,NULL,'ProposalApplicationPath','/usr/local/applicationForm/');

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


/*Seed data added for GMS release 2.4 - added on 31st May 2011 */
/*Seed data for Common Administrator Features in GMS */

/* Create a common administrator */
INSERT INTO person (email,user_real_name,username,PASSWORD,enabled) VALUES ('admin@admin.com','Admin','admin','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','');

/* Create a party for common administrator*/
INSERT INTO party (active_yes_no,code,email )VALUES ('Y','Admin','admin@admin.com');

/* Create a role for the common administrator*/
INSERT INTO authority (active_yes_no,authority,description) VALUES ('Y','ROLE_SUPERADMIN','Common Administrator');

/* Create  administrator to role map*/
INSERT INTO user_role(role_id,user_id)VALUES ((SELECT id  FROM authority WHERE authority = 'ROLE_SUPERADMIN'),(SELECT id  FROM person WHERE username = 'admin'));

/*Create  administrator to party map*/
INSERT INTO user_map(party_id,user_id)VALUES ((SELECT id  FROM party WHERE email = 'admin@admin.com'),(SELECT id  FROM person WHERE username = 'admin'));


/*Seed data added for GMS release 2.5 - added on 4th July 2011 */

/* Seed Data for new Table Menu */

INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (1, 0, 'Y', 'superAdmin', NOW(), 'default.Projects.label', NULL, 'superAdmin', NOW(), 'Projects',-1,1,'N');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (2, 0, 'Y', 'superAdmin', NOW(), 'default.ProjectList.label', 'projects/list', 'superAdmin', NOW(), 'Project List',1,1,'N');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (3, 0, 'Y', 'superAdmin', NOW(), 'default.AddProjects.label', 'projects/create', 'superAdmin', NOW(), 'Create Project',1,2,'N');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (4, 0, 'Y', 'superAdmin', NOW(), 'default.ProjectsDashBoard.label', 'projects/projectsDashBoard','superAdmin', NOW(), 'Projects DashBoard',1,3,'N');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (5, 0, 'Y', 'superAdmin', NOW(),'default.SearchProjects.label','projects/search', 'superAdmin', NOW(),'Search Projects',1,4,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (6, 0, 'Y', 'superAdmin', NOW(),'default.CallForProposal.label', 'notification/list', 'superAdmin',NOW(), 'Call For Proposal',1,5,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (7, 0, 'Y', 'superAdmin', NOW(),'default.ProposalManagement.label','notificationsEmails/partyNotificationsList', 'superAdmin', NOW(),'Proposal Management',1,6,'N');


INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (8, 0, 'Y', 'superAdmin', NOW(), 'default.Institution.label', NULL, 'superAdmin', NOW(), 'Institution',-1,2,'N');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (9, 0, 'Y', 'superAdmin', NOW(), 'default.InstitutionList.label', 'party/list', 'superAdmin', NOW(), 'List all Institutions',8,1,'N');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (10, 0, 'Y', 'superAdmin', NOW(), 'default.CreateInstitution.label', 'user/newUserBySuperAdmin', 'superAdmin', NOW(), 'Create New Institution',8,2,'Y');
INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (11, 0, 'Y', 'superAdmin', NOW(), 'default.AddDepartment.label', 'partyDepartment/create', 'superAdmin', NOW(), 'Add department to Institution',8,3,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (12, 0, 'Y', 'superAdmin', NOW(), 'default.Users.label', NULL,'superAdmin', NOW(), 'User Management',-1,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (13, 0, 'Y', 'superAdmin', NOW(), 'default.AddUser.label','user/create', 'superAdmin', NOW(), 'Create User',12,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (14, 0, 'Y', 'superAdmin', NOW(), 'default.UsersList.label','user/list', 'superAdmin', NOW(), 'Users List',12,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (15, 0, 'Y', 'superAdmin', NOW(),'default.ProjectPermission.label', 'user/accessControlEntry','superAdmin', NOW(), 'Project Permission',12,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (16, 0, 'Y', 'superAdmin', NOW(),'default.AccessPermission.label', 'rolePrivileges/create', 'superAdmin',NOW(), 'Access Permission',12,4,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (17, 0, 'Y', 'superAdmin', NOW(),'default.RolePrivileges.label', 'rolePrivileges/newRolePrivileges','superAdmin', NOW(), 'Role Privileges',12,5,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (18, 0, 'N', 'superAdmin', NOW(),'default.AssignRoleToUser.label', 'UserRole/create',  'superAdmin',NOW(), 'Assign Role',12,6,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (19, 0, 'Y', 'superAdmin', NOW(),'default.addMenu.label', 'menuRoleMap/create',  'superAdmin',NOW(), 'Map menu for Roles',12,7,'Y');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (20, 0, 'Y', 'superAdmin', NOW(), 'default.Masters.label', NULL,'superAdmin', NOW(), 'Settings',-1,4,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (21, 0, 'Y', 'superAdmin', NOW(), 'default.GrantAgency.label','partyGrantAgency/create',  'superAdmin', NOW(), 'Grant Agency',20,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (22, 0, 'Y', 'superAdmin', NOW(), 'default.PI.label','investigator/create',  'superAdmin', NOW(), 'Create PI',20,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (23, 0, 'Y', 'superAdmin', NOW(),'default.EmployeeDesignation.label', 'employeeDesignation/create','superAdmin', NOW(), 'Employee Designation',20,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (24, 0, 'Y', 'superAdmin', NOW(),'default.SalaryComponent.label', 'salaryComponent/create','superAdmin', NOW(), 'Salary Component',20,4,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (25, 0, 'Y', 'superAdmin', NOW(),'default.ProposalCategory.label', 'proposalCategory/create','superAdmin', NOW(), 'Proposal Category',20,5,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (26, 0, 'Y', 'superAdmin', NOW(), 'default.UploadProposalForm.label', 'proposal/uploadProposalForm',  'superAdmin', NOW(), 'Upload Proposal Form',20,6,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (27, 0, 'Y', 'superAdmin', NOW(),'default.AccountHead.label', 'accountHeads/create',  'superAdmin',NOW(), 'Account Heads',20,7,'Y');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (28, 0, 'Y', 'superAdmin', NOW(), 'default.GrantPeriod.label','grantPeriod/create',  'superAdmin', NOW(), 'Grant Period',20,8,'Y');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (29, 0, 'Y', 'superAdmin', NOW(), 'default.ProjectType.label','projectType/create',  'superAdmin', NOW(), 'Project Type',20,9,'Y');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (30, 0, 'Y', 'superAdmin', NOW(),'default.AttachmentType.label', 'attachmentType/create',  'superAdmin',NOW(), 'Attachment Type',20,10,'Y');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (31, 0, 'Y', 'superAdmin', NOW(), 'default.AddRole.label','authority/create',  'superAdmin', NOW(), 'Add Role',20,11,'Y');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (32, 0, 'Y', 'superAdmin', NOW(),'default.ApprovalAuthority.label', NULL, 'superAdmin', NOW(), 'ApprovalAuthority',-1,6,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (33, 0, 'Y', 'superAdmin', NOW(),'default.AddApprovalAuthority.label', 'approvalAuthority/create','superAdmin', NOW(), 'Create Approval Authority',32,1,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (34, 0, 'Y', 'superAdmin', NOW(),'default.AssignProposal.label', NULL, 'superAdmin', NOW(), 'AssignProposal',-1,7,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (35, 0, 'Y', 'superAdmin', NOW(),'default.AssignProposaltoApprovalAuthority.label','proposalApprovalAuthorityMap/create',  'superAdmin', NOW(), 'AssignProposal To ApprovalAuthority',34,1,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (36, 0, 'Y', 'superAdmin', NOW(), 'default.Budget.label', NULL,'superAdmin', NOW(), 'Budget',-1,8,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (37, 0, 'Y', 'superAdmin', NOW(),'default.FinancialYear.label', 'financialYear/create',  'superAdmin',NOW(), 'Financial Year',36,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (38, 0, 'Y', 'superAdmin', NOW(), 'default.BudgetMaster.label','budgetMaster/create',  'superAdmin', NOW(), 'Budget Master',36,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (39, 0, 'Y', 'superAdmin', NOW(),'default.AssignBudgettoModuleType.label', 'budgetModuleMap/create','superAdmin', NOW(), 'Assign Budget To ModuleType',36,3,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (40, 0, 'Y', 'superAdmin', NOW(), 'default.Reports.label', NULL,'superAdmin', NOW(), 'Reports',-1,13,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (41, 0, 'Y', 'superAdmin', NOW(),'default.ProjectReports.label', 'grantAllocation/reports','superAdmin', NOW(), 'Project Reports',40,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (42, 0, 'Y', 'superAdmin', NOW(),'default.ProjectStatusReports.label','grantAllocationTracking/grantAllocationTrackingReports',  'superAdmin',NOW(), 'Project Status Reports',39,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (43, 0, 'Y', 'superAdmin', NOW(),'default.UtilizationCertificates.label', 'utilization/list','superAdmin', NOW(), 'Utilization Certificates',40,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (44, 0, 'Y', 'superAdmin', NOW(),'default.StatisticalReports.label', 'grantAllocation/grantReports', 'superAdmin', NOW(), 'Statistical Reports',40,4,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (45, 0, 'Y', 'superAdmin', NOW(),'default.GrantAgencyReports.label', 'notification/granteeReports','superAdmin', NOW(), 'Grant Agency Reports',40,5,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (46, 0, 'Y', 'superAdmin', NOW(),'default.AuditLoggingReport.label','grantAllocation/auditLoggingReport',  'superAdmin', NOW(), 'AuditLogging Reports',40,6,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (47, 0, 'Y', 'superAdmin', NOW(),'default.ExpenseRequest.label', NULL, 'superAdmin', NOW(), 'Expense Request',-1,5,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (48, 0, 'Y', 'superAdmin', NOW(),'default.ExpenseRequestEntry.label', 'expenseRequestEntry/create', 'superAdmin', NOW(), 'EnterExpense Request',47,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (49, 0, 'Y', 'superAdmin', NOW(),'default.ExpenseRequestList.label', 'expenseRequestEntry/financeLogin', 'superAdmin', NOW(), 'ExpenseRequest List',47,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (50, 0, 'Y', 'superAdmin', NOW(),'default.ExpenseApprovalRequestList.label','expenseRequestEntry/expenseApprovalRequest',  'superAdmin', NOW(),'Evaluate Expense Request',47,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (51, 0, 'Y', 'superAdmin', NOW(), 'default.ExpenseRequestReviews.label','expenseRequestEntry/revieweStatus',  'superAdmin', NOW(), 'ViewReviews',47,4,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (52, 0, 'Y', 'superAdmin', NOW(), 'default.PreProposal.label',NULL, 'superAdmin', NOW(), 'PreProposal',-1,9,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (53, 0, 'Y', 'superAdmin', NOW(),'default.AddNewProposal.label', 'proposal/preProposalCreate','superAdmin', NOW(), 'Add New Proposal',52,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (54, 0, 'Y', 'superAdmin', NOW(),'default.PreProposalList.label', 'proposal/preProposalList','superAdmin', NOW(), 'PreProposal List',52,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (55, 0, 'Y', 'superAdmin', NOW(),'default.PreProposalApproval.label', 'proposalApproval/list','superAdmin', NOW(), 'PreProposal Approval',52,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (56, 0, 'Y', 'superAdmin', NOW(), 'default.PreProposalReviews.label','proposalApproval/reviewerStatus',  'superAdmin', NOW(), 'View Reviews',52,4,'N');



INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (57, 0, 'Y', 'superAdmin', NOW(), 'default.FullProposals.label',NULL, 'superAdmin', NOW(), 'FullProposal',-1,10,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (58, 0, 'Y', 'superAdmin', NOW(),'default.FullProposalList.label', 'proposal/fullProposalList', 'superAdmin', NOW(), 'FullProposal List',57,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (59, 0, 'Y', 'superAdmin', NOW(), 'default.FullProposalApproval.label', 'proposalApproval/fullProposalList',  'superAdmin', NOW(), 'FullProposal Approval',57,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (60, 0, 'Y', 'superAdmin', NOW(), 'default.FullProposalReviews.label', 'proposalApproval/fullProposalReview',  'superAdmin', NOW(), 'View Reviews',57,3,'N');




INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (61, 0, 'Y', 'superAdmin', NOW(), 'default.Proposals.label',NULL, 'superAdmin', NOW(), 'Proposal List',-1,11,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (62, 0, 'Y', 'superAdmin', NOW(), 'default.ProposalList.label','proposal/proposalApplicationList',  'superAdmin', NOW(),'ProposalList',61,1,'N');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (63, 0, 'Y', 'superAdmin', NOW(),'default.ProposalEvaluationPre-Settings.label', NULL, 'superAdmin',NOW(), 'Proposal Evaluation Pre-Settings',-1,12,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (64, 0, 'Y', 'superAdmin', NOW(), 'default.EvalScale.label','evalScale/create',  'superAdmin', NOW(), 'Evaluation Scale',63,1,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (65, 0, 'Y', 'superAdmin', NOW(),'default.EvalScaleOptions.label', 'evalScaleOptions/create','superAdmin', NOW(), 'Evaluation Scale Options',63,2,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (66, 0, 'Y', 'superAdmin', NOW(), 'default.EvalItem.label','evalItem/create', 'superAdmin', NOW(), 'Evaluation Question',63,3,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (67, 0, 'Y', 'superAdmin', NOW(),'default.QuestionNotificationMap.label','evalItemNotificationMap/create', 'superAdmin', NOW(), 'AssignEvaluation Questions for Notification',63,4,'N');


/* Seed Data for new Table MenuRoleMap */


INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (1, 0, 'Y', 'SuperAdmin', NOW(), 8, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (2, 0, 'Y', 'SuperAdmin', NOW(), 9, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (3, 0, 'Y', 'SuperAdmin', NOW(), 10, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (4, 0, 'Y', 'SuperAdmin', NOW(), 12, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (5, 0, 'Y', 'SuperAdmin', NOW(), 13, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (6, 0, 'Y', 'SuperAdmin', NOW(), 14, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (7, 0, 'N', 'SuperAdmin', NOW(), 17, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (8, 0, 'Y', 'SuperAdmin', NOW(), 19, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (9, 0, 'Y', 'SuperAdmin', NOW(), 20, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (10, 0, 'Y', 'SuperAdmin', NOW(), 27, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (11, 0, 'Y', 'SuperAdmin', NOW(), 28, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (12, 0, 'Y', 'SuperAdmin', NOW(), 29, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (13, 0, 'Y', 'SuperAdmin', NOW(), 30, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (14, 0, 'Y', 'SuperAdmin', NOW(), 31, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN'));
