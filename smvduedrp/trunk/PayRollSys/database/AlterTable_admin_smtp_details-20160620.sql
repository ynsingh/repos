use pl;

ALTER TABLE admin_smtp_details ADD mail_from varchar(100) default NULL after smtp_host_name;

