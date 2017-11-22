use olas;
alter table subject add sub_program varchar(255) null after sub_id;
alter table subject add sub_semester int(2) null after sub_program;
alter table subject add sub_subtype varchar(50) null after sub_semester;
