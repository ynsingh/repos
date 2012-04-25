use brihaspati;

DROP TABLE IF EXISTS Learner_sco;

CREATE TABLE Learner_sco (


				cmi_name varchar(50) default NULL,
                                cmi_value varchar(50) default NULL,
                                course_id varchar(50) default NULL,
                                sco_id varchar(255) default NULL,
                                member_id varchar(50) default NULL,
				cmi_value_text varchar(255) default NULL 	
			
);

insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (159, 'Learner_sco' ,1000, 10);

