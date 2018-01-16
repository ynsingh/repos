use olas;
drop index `userid` on `user_role_type`;
ALTER TABLE `user_role_type` ADD `prgid` int(10) Default NULL after `roleid`;
ALTER TABLE `user_role_type` ADD UNIQUE KEY `userid` (`userid`,`roleid`,`prgid`,`usertype`);


