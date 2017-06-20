use olas;
delete from `category` where cat_id=1;
insert into `category` values (1,'All','01','All','All Category');

alter table `email_setting` ADD `senderemail` VARCHAR(255) NULL after `sendername`;
alter table `email_setting` ADD `modulename` VARCHAR(255) NULL after `senderemail`;

CREATE TABLE `fees_master_archive` (
  `fma_id` int(11) NOT NULL,
  `fma_fmid` int(11) NOT NULL,
  `fma_head` varchar(255) NOT NULL,
  `fma_programid` int(11) NOT NULL,
  `fma_acadyear` varchar(50) NOT NULL,
  `fma_semester` varchar(50) NOT NULL,
  `fma_gender` varchar(50) NOT NULL,
  `fma_category` varchar(100) NOT NULL,
  `fma_amount` int(11) NOT NULL,
  `fma_frmdate` DATE DEFAULT NULL,
  `fma_todate` DATE DEFAULT NULL,
  `fma_desc` varchar(255) NOT NULL,
  `fma_ext1` varchar(255) NOT NULL,
  `fma_ext2` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `fees_master_archive`
  ADD PRIMARY KEY (`fma_id`);

ALTER TABLE `fees_master_archive`
  MODIFY `fma_id` int(11) NOT NULL AUTO_INCREMENT;

