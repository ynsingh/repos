
 alter table user_input_transfer alter  uit_dateofrelief set default '1970-01-01 00:00:00';
 alter table user_input_transfer alter  uit_dateofjoining set default '1970-01-01 00:00:00';
 alter table user_input_transfer modify uit_uso_no  varchar(255) default null;
