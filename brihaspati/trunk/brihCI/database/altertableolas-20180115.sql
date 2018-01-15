use olas;
INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (8, 'EntranceAdmin', 'Responsible for Entrance related job');
INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES (9, 'AdmissionAdmin', 'Responsible for Admission related job');
insert into user_role_type values (2,3,8,1,1,'EntranceAdministrator','');
insert into user_role_type values (3,4,9,1,1,'AdmissionAdministrator','');
