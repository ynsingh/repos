CREATE OR REPLACE VIEW staff_profile_masterdetails_v0_values AS 
SELECT
  users.user_full_name     AS user_full_name,
  users.last_name          AS last_name,
  users.title              AS user_title,
  TRIM(CONCAT(IFNULL(title,''),' ',user_full_name,' ',IFNULL(LAST_NAME,''))) AS full_name ,     
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

UPDATE staff_profile_report_v0_itemtypes SET choice='-Select-,O+,O-,A+,A-,B+,B-,AB+,AB-',code='-Select-,O+,O-,A+,A-,B+,B-,AB+,AB-' WHERE name='bloodgroup_combo_box';
DELETE FROM staff_profile_report_v0_items WHERE number=255;

/*======================= Remmove mandatory symbol from table============================*/
UPDATE staff_profile_awards_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_books_chapter_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_community_service_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_conference_papers_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_consultancy_offered_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_faculty_exchange_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_governance_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_journal_papers_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_media_publication_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_patents_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_personal_details_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_professional_body_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_projects_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_qualification_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_report_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_review_committees_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_talks_lectures_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_thesis_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);
UPDATE staff_profile_training_v0_items SET prompt = SUBSTRING_INDEX(REPLACE(prompt,'<span style="color:red">*</span>',''),'|',1);

/*---------- New Fiedls in staff_master --------------*/
ALTER TABLE staff_master
ADD(join_date VARCHAR(10),relieve_date VARCHAR(10),active_yesno SMALLINT(1));

