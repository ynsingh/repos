/*Script for GMS release 1.8 - added on 2nd Feb 2011 */



/* The foll: query is to assign a new role - for the finance person who will be receiving all the expense requests for the institution.*/

INSERT INTO `authority`(`authority`,`description`,`active_yes_no`) VALUES ('ROLE_FINANCE','ROLE_FINANCE','Y');


/* The foll: query is to assign a default value for expense_request_code field for the old expense entries as per the new feature of expense approval request. */

ALTER TABLE grant_expense ADD COLUMN expense_request_code VARCHAR(255) NULL;




/*The foll: query is to facilitate creating a new grant period name which is same as one that was deleted.The newly created grant period wil not be connected in anyway to the deleted one.*/

ALTER TABLE grant_period DROP KEY NAME, ADD INDEX NAME (NAME);



/*The foll: query is to facilitate creating a new authority name which is same as one that was deleted.The newly created authority wil not be connected in anyway to the deleted one.*/

ALTER TABLE authority DROP KEY authority, ADD INDEX authority (authority);




/*The foll: query is to facilitate creating a new investigator name which is same as one that was deleted.The newly created investigator wil not be connected in anyway to the deleted one.*/

ALTER TABLE investigator DROP KEY NAME, ADD INDEX NAME (NAME);



/*The foll: query is for storing the labels etc of the application form.*/

ALTER TABLE proposal_application_ext ADD COLUMN active_yes_no CHAR(1) DEFAULT '' NOT NULL ,ADD COLUMN label VARCHAR(255) NULL , ADD COLUMN order_no INT(11) NULL , ADD COLUMN page INT(11) NULL ;
UPDATE proposal_application_ext SET active_yes_no='Y';



/*The foll: query is to support the new scenario of unsolicited proposal submission i.e. independent of notification id.*/

ALTER TABLE proposal CHANGE notification_id notification_id BIGINT(20) NULL, CHANGE party_id party_id BIGINT(20) NULL;




/*The foll: query is for setting a default approval authority, whose members can be intimated by e-mail, of any unsolicited proposal submitted to the respective institute.*/


ALTER TABLE approval_authority  ADD COLUMN default_yes_no CHAR(1) NOT NULL;
UPDATE approval_authority SET default_yes_no='Y';

/*Query to insert new attachment types for unsolicited proposal submission to IGNOU */

INSERT INTO attachment_type (document_type,TYPE,active_yes_no) VALUES ('Proposal','DPR','Y'); 
INSERT INTO attachment_type (document_type,TYPE,active_yes_no) VALUES ('Proposal','CV','Y'); 
