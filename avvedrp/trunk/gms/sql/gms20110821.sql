/*Table structure for table `menu` */



DROP TABLE IF EXISTS `menu`;



CREATE TABLE `menu` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version` BIGINT(20) NOT NULL,
  `active_yes_no` CHAR(1) NOT NULL,
  `created_by` VARCHAR(255) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `lockedyn` VARCHAR(255) NOT NULL,
  `menu_description` VARCHAR(255) NOT NULL,
  `menu_name` VARCHAR(255) NOT NULL,
  `menu_order` INT(11) NOT NULL,
  `menu_path` VARCHAR(255) DEFAULT NULL,
  `modified_by` VARCHAR(255) NOT NULL,
  `modified_date` DATETIME NOT NULL,
  `parent_id` INT(11) DEFAULT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



/*Table structure for table `menu_role_map` */



DROP TABLE IF EXISTS `menu_role_map`;



CREATE TABLE `menu_role_map` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version` BIGINT(20) NOT NULL,
  `active_yes_no` CHAR(1) NOT NULL,
  `created_by` VARCHAR(255) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `menu_id` BIGINT(20) NOT NULL,
  `modified_by` VARCHAR(255) NOT NULL,
  `modified_date` DATETIME NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2C3BE0F32177BCA7` (`role_id`),
  KEY `FK2C3BE0F338C9F57A` (`menu_id`),
  CONSTRAINT `FK2C3BE0F338C9F57A` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK2C3BE0F32177BCA7` FOREIGN KEY (`role_id`) REFERENCES `authority` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Seed data added for GMS release 2.4 - added on 31st May 2011 */
/*Seed data for Common Administrator Features in GMS */

/* Create a common administrator */
INSERT INTO person (email,user_real_name,username,PASSWORD,enabled) VALUES ('admin@admin.com','Admin','admin','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','');

/* Create a party for common administrator*/
INSERT INTO party (active_yes_no,CODE,email )VALUES ('Y','Admin','admin@admin.com');

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


/*Script for GMS release 2.5 - added on 23th June 2011 */

ALTER TABLE proposal_application ADD COLUMN zip_code VARCHAR(255) NULL;
ALTER TABLE investigator ADD COLUMN user_sur_name VARCHAR(255) NULL;
ALTER TABLE grant_expense CHANGE dd_no dd_no VARCHAR(255)  DEFAULT NULL ;



/* Script for new Table MenuRoleMap */

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (15, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (16, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (17, 0, 'Y', 'SuperAdmin', NOW(), 3, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (18, 0, 'Y', 'SuperAdmin', NOW(), 4, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (19, 0, 'Y', 'SuperAdmin', NOW(), 5, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (20, 0, 'Y', 'SuperAdmin', NOW(), 6, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (21, 0, 'Y', 'SuperAdmin', NOW(), 7, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (22, 0, 'Y', 'SuperAdmin', NOW(), 8, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (23, 0, 'Y', 'SuperAdmin', NOW(), 9, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (24, 0, 'Y', 'SuperAdmin', NOW(), 11, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (25, 0, 'Y', 'SuperAdmin', NOW(), 12, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (26, 0, 'Y', 'SuperAdmin', NOW(), 13, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (27, 0, 'Y', 'SuperAdmin', NOW(), 14, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (28, 0, 'Y', 'SuperAdmin', NOW(), 15, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (29, 0, 'Y', 'SuperAdmin', NOW(), 16, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (30, 0, 'Y', 'SuperAdmin', NOW(), 17, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (31, 0, 'N', 'SuperAdmin', NOW(), 18, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (33, 0, 'Y', 'SuperAdmin', NOW(), 20, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (34, 0, 'Y', 'SuperAdmin', NOW(), 21, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (35, 0, 'Y', 'SuperAdmin', NOW(), 22, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (36, 0, 'Y', 'SuperAdmin', NOW(), 23, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (37, 0, 'Y', 'SuperAdmin', NOW(), 24, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (38, 0, 'Y', 'SuperAdmin', NOW(), 25, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (39, 0, 'Y', 'SuperAdmin', NOW(), 26, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (40, 0, 'Y', 'SuperAdmin', NOW(), 32, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (41, 0, 'Y', 'SuperAdmin', NOW(), 33, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (42, 0, 'Y', 'SuperAdmin', NOW(), 34, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (43, 0, 'Y', 'SuperAdmin', NOW(), 35, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (44, 0, 'Y', 'SuperAdmin', NOW(), 36, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (45, 0, 'Y', 'SuperAdmin', NOW(), 37, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (46, 0, 'Y', 'SuperAdmin', NOW(), 38, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (47, 0, 'Y', 'SuperAdmin', NOW(), 39, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (48, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));
INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (49, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (50, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (51, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (52, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (53, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (54, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (55, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (56, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (57, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN'));






INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (58, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (59, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (60, 0, 'Y', 'SuperAdmin', NOW(), 4, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (61, 0, 'Y', 'SuperAdmin', NOW(), 5, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (62, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (63, 0, 'Y', 'SuperAdmin', NOW(), 48, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (64, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (65, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (66, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (67, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (68, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (69, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (70, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (71, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (72, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (73, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (74, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (75, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (76, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (77, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (78, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PI'));




INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (79, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (80, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (81, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (82, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (83, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (84, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (85, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (86, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (87, 0, 'Y', 'SuperAdmin', NOW(), 61, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (88, 0, 'Y', 'SuperAdmin', NOW(), 62, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (89, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (90, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (91, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_REVIEWER'));



INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (92, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (93, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (94, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (95, 0, 'Y', 'SuperAdmin', NOW(), 53, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (96, 0, 'Y', 'SuperAdmin', NOW(), 54, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (97, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (98, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (99, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (100, 0, 'Y', 'SuperAdmin', NOW(), 58, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (101, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (102, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (103, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (104, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (105, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_STAFF'));




INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (106, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (107, 0, 'Y', 'SuperAdmin', NOW(), 49, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (108, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (109, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (110, 0, 'Y', 'SuperAdmin', NOW(), 32, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (111, 0, 'Y', 'SuperAdmin', NOW(), 33, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (112, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (113, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (114, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (115, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (116, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (117, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (118, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (119, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (120, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (121, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (122, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (123, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (124, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_FINANCE'));





INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (125, 0, 'Y', 'SuperAdmin', NOW(), 1, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (126, 0, 'Y', 'SuperAdmin', NOW(), 2, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (127, 0, 'Y', 'SuperAdmin', NOW(), 6, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (128, 0, 'Y', 'SuperAdmin', NOW(), 7, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (129, 0, 'Y', 'SuperAdmin', NOW(), 63, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (130, 0, 'Y', 'SuperAdmin', NOW(), 64, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (131, 0, 'Y', 'SuperAdmin', NOW(), 65, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (132, 0, 'Y', 'SuperAdmin', NOW(), 66, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (133, 0, 'Y', 'SuperAdmin', NOW(), 67, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (134, 0, 'Y', 'SuperAdmin', NOW(), 32, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (135, 0, 'Y', 'SuperAdmin', NOW(), 33, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (136, 0, 'Y', 'SuperAdmin', NOW(), 52, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (137, 0, 'Y', 'SuperAdmin', NOW(), 55, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (138, 0, 'Y', 'SuperAdmin', NOW(), 56, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (139, 0, 'Y', 'SuperAdmin', NOW(), 57, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (140, 0, 'Y', 'SuperAdmin', NOW(), 59, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (141, 0, 'Y', 'SuperAdmin', NOW(), 60, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (142, 0, 'Y', 'SuperAdmin', NOW(), 47, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (143, 0, 'Y', 'SuperAdmin', NOW(), 50, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (144, 0, 'Y', 'SuperAdmin', NOW(), 51, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (145, 0, 'Y', 'SuperAdmin', NOW(), 40, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (146, 0, 'Y', 'SuperAdmin', NOW(), 41, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (147, 0, 'Y', 'SuperAdmin', NOW(), 42, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (148, 0, 'Y', 'SuperAdmin', NOW(), 43, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (149, 0, 'Y', 'SuperAdmin', NOW(), 44, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (150, 0, 'Y', 'SuperAdmin', NOW(), 45, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
VALUES (151, 0, 'Y', 'SuperAdmin', NOW(), 46, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_PROPOSALADMIN'));












