
/*Script for GMS release 1.8 - added on 2nd Feb 2011 */

ALTER TABLE grant_expense ADD COLUMN expense_request_code VARCHAR(255) NULL;

ALTER TABLE grant_period DROP KEY NAME, ADD INDEX NAME (NAME);

ALTER TABLE authority DROP KEY authority, ADD INDEX authority (authority);

ALTER TABLE investigator DROP KEY NAME, ADD INDEX NAME (NAME);

ALTER TABLE proposal_application_ext ADD COLUMN active_yes_no CHAR(1) DEFAULT '' NOT NULL ,ADD COLUMN label VARCHAR(255) NULL , ADD COLUMN order_no INT(11) NULL , ADD COLUMN page INT(11) NULL ;

UPDATE proposal_application_ext SET active_yes_no='Y';

ALTER TABLE proposal CHANGE notification_id notification_id BIGINT(20) NULL, CHANGE party_id party_id BIGINT(20) NULL;

ALTER TABLE approval_authority  ADD COLUMN default_yes_no CHAR(1) NOT NULL;

UPDATE approval_authority SET default_yes_no='Y';

INSERT INTO attachment_type (document_type,TYPE,active_yes_no) VALUES ('Proposal','DPR','Y'); 

INSERT INTO attachment_type (document_type,TYPE,active_yes_no) VALUES ('Proposal','CV','Y'); 


/*Script for GMS release 1.9 - added on 1nd March 2011 */

ALTER TABLE `notification` CHANGE `description` `description` TEXT NULL ;

ALTER TABLE proposal  ADD COLUMN proposal_version INT(11); 

ALTER TABLE proposal_application_ext  CHANGE `value` `value` TEXT NOT NULL;

ALTER TABLE `notification` DROP FOREIGN KEY  `FK237A88EB52E8209B` ;

ALTER TABLE `notification` DROP `project_id`;

ALTER TABLE `notification` ADD COLUMN `notification_title` VARCHAR(255) NULL AFTER `proposal_submission_last_date`, ADD COLUMN `public_yes_no` CHAR(1) NULL AFTER `notification_title`, ADD COLUMN `publish_yes_no` CHAR(1) NULL AFTER `public_yes_no`;

ALTER TABLE `notification` ADD COLUMN `party_id` BIGINT(20) NULL ;

ALTER TABLE `notification` ADD CONSTRAINT `FK_notification` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`);

/*forieghn key grantor_id removed from proposal*/

ALTER TABLE `proposal` DROP FOREIGN KEY  `FKC4D26AF2381E3021` ;

ALTER TABLE `proposal` DROP COLUMN `grantor_id`;

/*update the value of publish and public in notification*/

UPDATE notification SET public_yes_no='Y';

UPDATE notification SET publish_yes_no='Y';

/*New fields added in proposal application*/

ALTER TABLE `proposal_application`     

ADD COLUMN `name` VARCHAR(255) NULL,     

ADD COLUMN `designation` VARCHAR(255) NULL,

ADD COLUMN `organisation` VARCHAR(255) NULL,

ADD COLUMN `city` VARCHAR(255) NULL,

ADD COLUMN `email` VARCHAR(255) NULL,

ADD COLUMN `fax` VARCHAR(255) NULL,

ADD COLUMN `mobile` VARCHAR(255) NULL,

ADD COLUMN `phone` VARCHAR(255) NULL,

ADD COLUMN `postal_address` VARCHAR(255) NULL,

ADD COLUMN `state` VARCHAR(255) NULL,

ADD COLUMN `proposal_category_id` BIGINT(20) NULL,

ADD CONSTRAINT `FK_proposal_application` FOREIGN KEY (`proposal_category_id`) REFERENCES `proposal_category` (`id`);

ALTER TABLE expense_request_entry CHANGE `invoice_amount` `invoice_amount` DOUBLE NULL ,CHANGE `invoice_date` `invoice_date` DATETIME NULL , CHANGE `invoice_no` `invoice_no` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT '' NULL ;

UPDATE proposal_approval_authority_map SET active_yes_no='Y'; 

INSERT INTO `authority`(`active_yes_no`, `authority`,`description`)VALUES('Y','ROLE_PROPOSALADMIN','Proposal Admin');

