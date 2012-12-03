
                                       
INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   (108, 0, 'Y', 'superAdmin', NOW(), 'default.checklist.label','checklist/create',  'superAdmin', NOW(), 'CheckList',20,12,'N','V');                                       

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 108, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map;    

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   (109, 0, 'Y', 'superAdmin', NOW(), 'default.checklistmap.label','checklist/checklistmap',  'superAdmin', NOW(), 'User Management',12,8,'N','V');                                       

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 109, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map;      



INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   (110, 0, 'Y', 'superAdmin', NOW(),'default.ProposalDetailsReport.label','grantAllocation/proposalDetails','superAdmin', NOW(),'Proposal Details Report',40,9,'N','V');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   (111, 0, 'Y', 'superAdmin', NOW(),'default.ProjectClosureReport.label','projectTracking/projectClosureReport','superAdmin', NOW(),'Project Closure Report',40,10,'N','V');

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   (112, 0, 'Y', 'superAdmin', NOW(),'default.UploadForm.label','proposal/uploadForm','superAdmin', NOW(),'Proposal Form',20,13,'N','V');


INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   (113, 0, 'Y', 'superAdmin', NOW(),'default.ProjectDeactivation.label','projects/deactivate','superAdmin', NOW(),'Project Deactivation',12,9,'Y','V');

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 113, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SUPERADMIN') FROM menu_role_map; 

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES   
(114, 0, 'Y', 'superAdmin', NOW(),'default.UploadMessageDocs.label','projects/messageAttach','superAdmin', NOW(),'UploadMessageDocs',83,6,'N','H');


INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 114, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map; 

INSERT INTO menu (id,VERSION,active_yes_no,created_by,created_date,menu_name,menu_path,modified_by,modified_date,menu_description,parent_id,menu_order,lockedyn,menu_alignment)VALUES  
(115, 0, 'Y', 'superAdmin', NOW(),'default.UploadedDocsList.label','projects/messagesList','superAdmin', NOW(),'Uploaded Docs List ',40,11,'N','V');

INSERT INTO menu_role_map (`id`,`version`, `active_yes_no`, `created_by`, `created_date`, `menu_id`, `modified_by`, `modified_date`, `role_id`)
SELECT IFNULL(MAX(id), 0)+1, 0, 'Y', 'SuperAdmin', NOW(), 115, 'SuperAdmin', NOW(), (SELECT a.id FROM authority a WHERE authority='ROLE_SITEADMIN') FROM menu_role_map;





/*---------Release Script------------*/



ALTER TABLE grant_expense  CHANGE `bank_name` `bank_name` VARCHAR(255)  DEFAULT '' NULL , CHANGE `date_of_expense` `date_of_expense` DATETIME NOT NULL, CHANGE `dd_branch` `dd_branch` VARCHAR(255) DEFAULT '' NULL , CHANGE `dd_date` `dd_date` DATETIME NULL ;

ALTER TABLE person ADD COLUMN `aadhaar_no` VARCHAR(255) NULL AFTER `user_sur_name`;

ALTER TABLE investigator ADD COLUMN `aadhaar_no` VARCHAR(255) NULL AFTER `user_sur_name`;

UPDATE  menu SET menu_name='default.AccountHead.label', menu_path='accountHeads/create', menu_description='Account Heads' WHERE id='81';

UPDATE menu SET menu_path='projectTracking/closureDetails' WHERE id='97';
                            
UPDATE `menu` SET `menu_path`='testpage.jsp?query=projectExpense' WHERE `id`='74';

UPDATE `menu_role_map` SET `active_yes_no`='N' WHERE `menu_id`='73';

UPDATE `menu_role_map` SET `active_yes_no`='N' WHERE `menu_id`='74';

ALTER TABLE grant_expense  ADD COLUMN utilization_submitted CHAR(1) NULL AFTER fund_advance_code;
UPDATE grant_expense SET `utilization_submitted`='Y';

UPDATE `menu` SET `menu_path`='asset/create' WHERE `id`='99';


