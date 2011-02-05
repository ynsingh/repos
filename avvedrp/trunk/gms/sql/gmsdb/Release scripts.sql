
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




