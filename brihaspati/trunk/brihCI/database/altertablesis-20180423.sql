use payroll;

ALTER TABLE  employee_master ADD emp_secndemail VARCHAR(255) default NULL AFTER  emp_grade;
ALTER TABLE  employee_master ADD emp_phddiscipline VARCHAR(255) default NULL AFTER emp_secndemail;
ALTER TABLE  employee_master ADD emp_phdtype VARCHAR(50) default NULL AFTER  emp_phddiscipline;
ALTER TABLE  employee_master ADD emp_phdinstname VARCHAR(255) default NULL AFTER  emp_phdtype;
ALTER TABLE  employee_master ADD emp_phdunivdeput VARCHAR(255) default NULL AFTER emp_phdinstname;
ALTER TABLE  employee_master ADD emp_netqualified VARCHAR(50) default NULL AFTER emp_phdunivdeput;
ALTER TABLE  employee_master ADD emp_netpassingyear date  default NULL AFTER emp_netqualified;
ALTER TABLE  employee_master ADD emp_netdiscipline  VARCHAR(255) default NULL AFTER emp_netpassingyear;
ALTER TABLE  employee_master ADD emp_vciregno VARCHAR(255) default NULL AFTER emp_netdiscipline;
ALTER TABLE  employee_master ADD emp_vciregdate datetime default NULL AFTER emp_vciregno;


--
-- Table structure for table `additional_assignments`
--

CREATE TABLE `additional_assignments` (
  `aa_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `aa_empid` int(11)  NOT NULL,
  `aa_asigname` varchar(255)  Default NULL,
  `aa_asigperiodfrom`  DATETIME  Default NULL,
  `aa_asigperiodto`  DATETIME  Default NULL,
  `aa_place` varchar(255)  Default NULL,
  `aa_creatorid` varchar(255) NOT NULL,
  `aa_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `aa_modifierid` varchar(255) NOT NULL,
  `aa_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------

