use payroll;

CREATE TABLE user_input_transfer (
	uit_id INT(11) NOT NULL AUTO_INCREMENT ,	
	uit_registrarname  varchar(255) NOT NULL ,	
	uit_desig  varchar(255) NOT NULL ,	
	uit_uso_no  INT(11) NOT NULL ,	
	uit_date datetime NOT NULL,	
	uit_rc_no varchar(255) NOT NULL,	
	uit_subject blob NOT NULL,	
	uit_referenceno varchar(255) NOT NULL,	
	uit_ordercontent blob default NULL,	
	uit_emptype varchar(255) NOT NULL,	
	uit_uoc_from varchar(255) NOT NULL,	
	uit_workdept_from varchar(255) NOT NULL,	
	uit_desig_from varchar(255) NOT NULL,	
	uit_staffname varchar(255) NOT NULL,	
	uit_workingpost_from varchar(255) NOT NULL,	
	uit_uoc_to varchar(255) NOT NULL,	
	uit_dept_to varchar(255) NOT NULL,	
	uit_desig_to varchar(255) NOT NULL,	
	uit_post_to varchar(255) NOT NULL,	
	uit_tta_detail blob NOT NULL,	
	uit_dateofrelief datetime NOT NULL,	
	uit_dateofjoining datetime NOT NULL,	
	uit_email_sentto blob default NULL,	
    	PRIMARY KEY (uit_id)
)ENGINE = InnoDB;

