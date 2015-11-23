CREATE TABLE forgot_password (
	fp_id int(11) NOT NULL auto_increment,
	user_name varchar(250) NOT NULL, 
	r_key varchar(250) NOT NULL,
	pass_date datetime NOT NULL,
        expiry_date datetime NOT NULL ,
        PRIMARY KEY  (`fp_id`)
	
);
