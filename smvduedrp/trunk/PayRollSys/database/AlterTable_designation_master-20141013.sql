use pl;

ALTER TABLE designation_master ADD desig_dcode varchar(80) NOT NULL after desig_code;
ALTER TABLE designation_master ADD desig_nickname varchar(80) NOT NULL after desig_name;
