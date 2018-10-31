drop index ul_userid on uo_list;
alter table uo_list ADD UNIQUE KEY `ul_userid` (`ul_userid`,`ul_empcode`,`ul_datefrom`);
