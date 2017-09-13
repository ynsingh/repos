CREATE TABLE `examtype` (
  `exty_id` int(11) NOT NULL,
  `exty_name` varchar(255) NOT NULL,
  `exty_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `examtype`
  ADD PRIMARY KEY (`exty_id`);

ALTER TABLE `examtype`
  MODIFY `exty_id` int(11) NOT NULL AUTO_INCREMENT;

insert into `examtype` values (1,'Quiz','Class Test');
insert into `examtype` values (2,'Internal Exam','Exam');
insert into `examtype` values (3,'External Exam','University Exam');
insert into `examtype` values (4,'Practical','Practical Exam');
insert into `examtype` values (5,'Viva-Voce','Oral Exam');
