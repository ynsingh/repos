
/* Seed Data */

/*Seed data added for GMS release 2.8 */


INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (68, 0, 'Y', 'superAdmin', NOW(),'default.ProjectsSummaryReports.label','grantAllocation/projectsSummary','superAdmin', NOW(),'Projects Summary Reports',40,7,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (69, 0, 'Y', 'superAdmin', NOW(),'default.GrantedProjectsReports.label','grantAllocation/grantedProjects','superAdmin', NOW(),'Granted Projects Reports',40,8,'N');
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES   (70, 0, 'Y', 'superAdmin', NOW(),'default.AdHocReport.label','testpage.jsp?query=projects', 'superAdmin', NOW(), 'Mondrian report',1,7,'N');



INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 68, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map;

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 69, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map;

INSERT INTO menu  (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn)VALUES  (71, 0, 'Y', 'superAdmin', NOW(), 'default.GrantFund.label', 'externalFundAllocation/create', 'superAdmin', NOW(), 'Grant Fund',8,4,'N');

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 71, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map;

/* Release Scripts  for GMS release 2.8*/


ALTER TABLE grant_expense  ADD COLUMN `fund_advance_code` VARCHAR(255) NULL AFTER `expense_request_code`; 
ALTER TABLE person  ADD COLUMN `active_yes_no` CHAR(1) NOT NULL AFTER `username`;
UPDATE person SET active_yes_no='Y';
ALTER TABLE person DROP KEY `username`;
ALTER TABLE person ADD COLUMN `user_designation` VARCHAR(255) NULL AFTER `active_yes_no`;    
ALTER TABLE person ADD COLUMN `ph_number` VARCHAR(255) NULL AFTER `user_designation`;

