/*---------- Update values from  staff_profile_report_v0_values--------------*/
ALTER TABLE `staff_profile_report_v0_values` ADD COLUMN `personal_joining_date` VARCHAR(255) ;

UPDATE `staff_profile_report_v0_values` SET `personal_joining_date`="";
UPDATE staff_master,staff_profile_report_v0_values SET staff_master.join_date= staff_profile_report_v0_values.personal_joining_date WHERE staff_profile_report_v0_values.idf=staff_master.userid;

UPDATE staff_master SET active_yesno=1;

/*--------- View Modified with the part of institution tranfer----------------*/
CREATE OR REPLACE VIEW staff_profile_masterdetails_v0_values AS 
SELECT
  users.user_full_name     AS user_full_name,
  users.last_name          AS last_name,
  users.title              AS user_title,
  TRIM(CONCAT(IFNULL(title,''),' ',user_full_name,' ',
IFNULL(LAST_NAME,''))) AS full_name ,   
  users.username           AS username,
  users.email              AS email,
  users.id                 AS userid,
  users.id                 AS idf,
  university_master.name   AS University,
  university_master.id     AS University_id,
  institution_master.name  AS Institution,
  institution_master.id    AS Institution_id,
  general_master.fld_value AS Department,
  A.fld_value              AS Designation,
  staff_master.department_id AS department_id,
  staff_master.join_Date AS join_Date
FROM staff_master
        INNER JOIN department_master
          ON department_master.id = staff_master.department_id AND staff_master.active_yesno=1
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

ALTER TABLE staff_master
   ADD COLUMN record_staus VARCHAR(1) NULL 
COMMENT 'Keep T for transfer related record';

insert into staff_profile_report_v0_items (number, name, description, creator, time, last_filler, last_time, prompt, itemtype, archived_form_name, prioritem, nextitem) values('361','e_publication_links','E Publication Links',NULL,

'2011-11-09 11:51:00',NULL,'0000-00-00 00:00:00',
'E-Publication Links','e_publication_links_childform',
NULL,NULL,NULL);

ALTER TABLE staff_profile_report_v0_values 
ADD COLUMN(e_publication_links TEXT);


insert into staff_profile_report_v0_itemtypes (name, description, creator, time, action, choice, code, responsetype) values('e_publication_links_childform',
'E Publication Links','oio',
'2011-11-09 11:55:00','detail_form',
'staff_profile_e_publication_links_v0',
'journal_name,url,remarks',NULL);

CREATE TABLE staff_profile_e_publication_links_v0_items (
  number double default NULL,
  name varchar(765) default NULL,
  description varchar(765) default NULL,
  creator varchar(765) default NULL,
  time timestamp NOT NULL default '0000-00-00 00:00:00',
  last_filler varchar(765) default NULL,
  last_time timestamp NOT NULL default '0000-00-00 00:00:00',
  prompt varchar(765) default NULL,
  itemtype varchar(765) default NULL,
  archived_form_name varchar(765) default NULL,
  prioritem varchar(765) default NULL,
  nextitem varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into staff_profile_e_publication_links_v0_items(number,name,description,creator,time,last_filler,last_time,prompt,itemtype,
archived_form_name,prioritem,nextitem) values (5,'journal_name','Journal Name',NULL,
'2011-11-09 12:02:21',NULL,'0000-00-00 00:00:00','Journal Name','blank',NULL,NULL,NULL);

insert  into staff_profile_e_publication_links_v0_items(number,name,description,creator,time,last_filler,last_time,prompt,
itemtype,archived_form_name,prioritem,nextitem) values (10,'url','URL',NULL,
'2011-11-09 12:02:21',NULL,'0000-00-00 00:00:00','URL','URL',NULL,NULL,NULL);

insert  into staff_profile_e_publication_links_v0_items(number,name,description,creator,time,last_filler,last_time,prompt,
itemtype,archived_form_name,prioritem,nextitem) values (15,'remarks','Remarks',
NULL,'2011-11-09 12:02:21',NULL,'0000-00-00 00:00:00','Remarks','textarea',NULL,NULL,NULL);

CREATE TABLE staff_profile_e_publication_links_v0_values (
  idf int(11) default NULL,
  number int(11) default NULL,
  form varchar(255) default NULL,
  creator varchar(255) default NULL,
  time timestamp NOT NULL default '0000-00-00 00:00:00',
  last_time timestamp NOT NULL default '0000-00-00 00:00:00',
  last_filler varchar(255) default NULL,
  data_quality varchar(255) default NULL,
  change_group text,
  execute_group text,
  journal_name varchar(255) default NULL,
  url text,
  remarks text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into profile_type_master (id, document_type, description, active_yesno, last_modified_by, last_modified_date_time) values('20','SP_E_PUBLICATION_LINKS',
'Staff Profile E Publication Links','1','admin',NULL);

insert into profile_master (id, form_name, version, title, description, document_type_id, form_type, active_yesno, show_draft_copy_yesno, last_modified_by, last_modified_date_time, iso_document_number, scannable_yesno, child_yesno) values('20','staff_profile_e_publication_links','v0',
'Staff Profile E Publication Links',
'Staff Profile E Publication Links','20','0','1','1',NULL,NULL,NULL,'0',NULL);


UPDATE staff_profile_report_v0_values SET e_publication_links="";

CREATE TABLE staff_profile_e_publication_links_v0_itemtypes (
  name varchar(765) default NULL,
  description varchar(765) default NULL,
  creator varchar(765) default NULL,
  time timestamp NOT NULL default '0000-00-00 00:00:00',
  action varchar(765) default NULL,
  choice blob,
  code blob,
  responsetype varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into staff_profile_e_publication_links_v0_itemtypes(name,description,creator,time,action,choice,code,responsetype) values 
('URL','None','oio','2010-12-04 16:56:17','text_area','3,50','',NULL);

insert  into staff_profile_e_publication_links_v0_itemtypes
(name,description,creator,time,action,choice,code,responsetype) values 
('blank','None','oio','2011-01-29 16:12:33','except','65 ',' ',NULL);

insert  into staff_profile_e_publication_links_v0_itemtypes
(name,description,creator,time,action,choice,code,responsetype) values 
('textarea','textarea','oio','2011-01-29 16:12:33','text_area','3,50','',NULL);


UPDATE staff_profile_report_v0_itemtypes 
SET CODE='journal_type,paper_title,author1,month_year,url'
WHERE NAME='journal_papers_childform';

INSERT  INTO staff_profile_journal_papers_v0_itemtypes
(name,description,creator,time,action,choice,code,responsetype)
 VALUES ('URL','None','oio','2010-12-04 16:56:17','except','50','',NULL);

UPDATE staff_profile_journal_papers_v0_items SET itemtype='URL'
WHERE NAME='url';

