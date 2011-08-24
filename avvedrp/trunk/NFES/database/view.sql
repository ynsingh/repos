DROP VIEW IF EXISTS `staff_profile_masterdetails_v0_values`;

CREATE  VIEW `staff_profile_masterdetails_v0_values` AS 
SELECT
  `users`.`user_full_name`     AS `user_full_name`,
  `users`.`username`           AS `username`,
  `users`.`email`              AS `email`,
  `users`.`id`                 AS `userid`,
  `users`.`id`                 AS `idf`,
  `university_master`.`name`   AS `University`,
  `university_master`.`id`     AS `University_id`,
  `institution_master`.`name`  AS `Institution`,
  `institution_master`.`id`    AS `Institution_id`,
  `general_master`.`fld_value` AS `Department`,
  `A`.`fld_value`              AS `Designation`
FROM ((((((`staff_master`
        JOIN `department_master`
          ON ((`department_master`.`id` = `staff_master`.`department_id`)))
       JOIN `institution_master`
         ON ((`institution_master`.`id` = `department_master`.`institution_id`)))
      JOIN `university_master`
        ON ((`university_master`.`id` = `institution_master`.`university_id`)))
     JOIN `general_master`
       ON (((`general_master`.`id` = `department_master`.`department_id`)
            AND (`general_master`.`category` = _latin1'Department'))))
    JOIN `general_master` `A`
      ON (((`A`.`id` = `staff_master`.`designation_id`)
           AND (`A`.`category` = _latin1'Designation'))))
   JOIN `users`
     ON (((`staff_master`.`userid` = `users`.`id`)
          AND (`users`.`enabled` = 1)
          AND (`users`.`username` <> _latin1'admin'))));

DROP VIEW IF EXISTS `staff_profile_personaldetails_v0_values`;

CREATE  VIEW `staff_profile_personaldetails_v0_values` AS 
SELECT
  `staff_profile_report_v0_values`.`idf`                    AS `idf`,
  `staff_profile_report_v0_values`.`office_room_num`        AS `office_room_num`,
  `staff_profile_report_v0_values`.`office_phone_ext`       AS `office_phone_ext`,
  `staff_profile_report_v0_values`.`email_id`               AS `email_id`,
  `staff_profile_report_v0_values`.`email`                  AS `email`,
  `staff_profile_report_v0_values`.`home_phone`             AS `home_phone`,
  `staff_profile_report_v0_values`.`fax`                    AS `fax`,
  `staff_profile_report_v0_values`.`personal_date_of_birth` AS `DOB`,
  `staff_profile_report_v0_values`.`personal_gender`        AS `gender`,
  `staff_profile_report_v0_values`.`personal_community`     AS `community`
FROM `staff_profile_report_v0_values`;
