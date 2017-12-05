use payroll;
CREATE TABLE  employee_servicedetail(
        empsd_id INT(11) NOT NULL AUTO_INCREMENT ,
        empsd_empid  INT(11) NOT NULL ,
        empsd_campuscode  varchar(255) NOT NULL ,
        empsd_desigcode  varchar(255) NOT NULL ,
        empsd_pbid INT(11) NOT NULL,
        empsd_pbdate date NOT NULL,
        empsd_dojoin date NOT NULL,
        empsd_dorelev date NOT NULL,
        PRIMARY KEY (empsd_id)
)ENGINE = InnoDB;

