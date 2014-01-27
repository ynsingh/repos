CREATE TABLE Salary_processing_setup
(
  seq_id int(11) NOT NULL auto_increment,
  salary_process_mode varchar(300) NOT NULL,
  flag tinyint(4) default NULL,
  org_id int(11) NOT NULL,
  UNIQUE KEY  (salary_process_mode, org_id),
  PRIMARY KEY  (seq_id)

);
INSERT INTO `Salary_processing_setup` (`seq_id`, `salary_process_mode`, `flag`,`org_id`) VALUES
  (1,'Salary Processing with Budget',1,1),
  (2,'Salary Processing',0,1);
COMMIT;



