use payroll;
alter table user_role_type modify scid int(10)  DEFAULT NULL;
alter table user_role_type_archive modify urta_scid int(10)  DEFAULT NULL;
