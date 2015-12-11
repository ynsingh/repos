use pl;

ALTER TABLE admin_smtp_details DROP COLUMN smtp_name;

ALTER TABLE admin_smtp_details ADD UNIQUE KEY (smtp_host_name); 
