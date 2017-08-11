use olas;
alter table `fees_master` change `fm_programid` `fm_programid` VARCHAR(255);
create index `fm_head`on `fees_master` (`fm_head`,`fm_programid`,`fm_semester`,`fm_gender`,`fm_category`);
