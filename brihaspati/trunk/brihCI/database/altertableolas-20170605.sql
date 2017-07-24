use olas;
alter table `email_setting` change creatorid creatorid  varchar(255);
alter table `email_setting` change `createdate` `createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE `user_role_type` ADD `id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL FIRST;
