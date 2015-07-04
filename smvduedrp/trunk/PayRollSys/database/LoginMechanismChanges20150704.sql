use pl;

ALTER TABLE user_master DROP FOREIGN KEY user_master_fk;
ALTER TABLE org_profile DROP INDEX org_email;
ALTER TABLE user_master DROP FOREIGN KEY user_master_ibfk_1;
ALTER TABLE user_master DROP FOREIGN KEY user_master_ibfk_2;
ALTER TABLE user_master DROP KEY user_name_2;
ALTER TABLE user_master DROP COLUMN user_org_id;
ALTER TABLE user_master DROP COLUMN user_grp_id;


ALTER TABLE user_master ADD COLUMN login_uid int(11) AFTER user_profile_id;
INSERT INTO user_master (user_name, user_pass, user_profile_id, flag) VALUES("payadmin", "admin123", 0, 1);

ALTER TABLE college_pending_status DROP PRIMARY KEY;
ALTER TABLE college_pending_status ADD COLUMN id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT FIRST;

INSERT INTO user_group_master (grp_id, grp_name) VALUES(6, "Employee");

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `org_id` int(11),
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `user_id_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id_fk2` FOREIGN KEY (`role_id`) REFERENCES `user_group_master` (`grp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `org_id_fk3` FOREIGN KEY (`org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO user_roles (user_id, role_id) VALUES(1,3);

ALTER TABLE session_master DROP INDEX ss_name;
ALTER TABLE session_master ADD CONSTRAINT `ss_name` UNIQUE (ss_name, ss_org_id);


