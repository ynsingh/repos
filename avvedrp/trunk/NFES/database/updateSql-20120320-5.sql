CREATE OR REPLACE VIEW staff_profile_personaldetails_v0_values AS 
SELECT
  staff_profile_report_v0_values.idf              AS idf,
  staff_profile_report_v0_values.office_room_num  AS office_room_num,
  staff_profile_report_v0_values.office_phone_ext AS office_phone_ext,
  staff_profile_report_v0_values.email_id         AS email_id,
  staff_profile_report_v0_values.email            AS email,
  staff_profile_report_v0_values.home_phone       AS home_phone,
  staff_profile_report_v0_values.fax              AS fax,
  staff_profile_report_v0_values.personal_date_of_birth AS DOB,
  staff_profile_report_v0_values.personal_gender AS gender,
  staff_profile_report_v0_values.personal_community AS community,
  staff_profile_report_v0_values.upload_photo AS photo
FROM staff_profile_report_v0_values;

DROP TABLE file_master;
DROP TABLE language_localisation;

