use login;
INSERT INTO `edrpuser` (`id`, `username`, `password`, `email`, `componentreg`, `mobile`, `status`, `category_type`, `verification_code`, `is_verified`) VALUES
(3, 'entranceadmin', md5('admin'), 'ynsingh@iitk.ac.in', '*', NULL, '1', '', '', 1),
(4, 'admissionadmin', md5('admin'), 'ynsingh@iitk.ac.in', '*', NULL, '1', '', '', 1);
insert into userlaststatus values (3,3,'English','',1,'','','',1);
insert into userlaststatus values (4,4,'English','',1,'','','',1);
insert into userprofile values (3,3,'Y.N.','Singh','','','','English',1);
insert into userprofile values (4,4,'Y.N.','Singh','','','','English',1);


