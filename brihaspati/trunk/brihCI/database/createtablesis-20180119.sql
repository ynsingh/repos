use payroll;
--
-- Table structure for table `Salary Head`
--

CREATE TABLE `salary_head` (
  `sh_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sh_code` varchar(50)  NOT NULL,
  `sh_name` varchar(255)  NOT NULL,
  `sh_shortname` varchar(50)  Default NULL,
  `sh_type` varchar(50)  NOT NULL,
  `sh_calc_type` varchar(50)  NOT NULL,
  `sh_taxable` varchar(50)  NOT NULL,
  `sh_category` varchar(50)  Default 'GS',
  `sh_ledgercode` varchar(255)  Default NULL,
  `sh_description` varchar(255)  Default NULL,
  `sh_creatorid` varchar(255) NOT NULL,
  `sh_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sh_modifierid` varchar(255) NOT NULL,
  `sh_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP,
   UNIQUE (`sh_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
--
-- Table structure for table `salary_formula `
--

CREATE TABLE `salary_formula` (
  `sf_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sf_salhead_id` INT(11)  NOT NULL,
  `sf_formula` varchar(255)  NOT NULL,
  `sf_description` varchar(255)  Default NULL,
  `sf_creatorid` varchar(255) NOT NULL,
  `sf_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sf_modifierid` varchar(255) NOT NULL,
  `sf_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------------
