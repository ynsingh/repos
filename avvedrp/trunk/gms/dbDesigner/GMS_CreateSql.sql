CREATE TABLE institution_master (
  id INT(11) NOT NULL AUTO_INCREMENT,
  code VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  address TEXT NULL,
  phone VARCHAR(100) NULL,
  active_yes_no CHAR(1) NULL DEFAULT 'Y',
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE grant_master (
  id INT(11) NOT NULL AUTO_INCREMENT,
  code VARCHAR(100) NOT NULL,
  title VARCHAR(100) NOT NULL,
  manager VARCHAR(100) NOT NULL,
  description TEXT NULL,
  active_yes_no CHAR(1) NULL,
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE users (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(100) NOT NULL,
  user_password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NULL,
  phone VARCHAR(100) NULL,
  role VARCHAR(100) NOT NULL,
  active_yes_no CHAR(1) NULL DEFAULT 'Y',
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE projects (
  id INT(11) NOT NULL AUTO_INCREMENT,
  project_name VARCHAR(100) NOT NULL,
  project_investigator_name VARCHAR(100) NOT NULL,
  project_submission_date DATE NOT NULL,
  description TEXT NULL,
  active_yes_no CHAR(1) NULL DEFAULT 'Y',
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE expense_head_master (
  id INT(11) NOT NULL AUTO_INCREMENT,
  expense_head_name VARCHAR(100) NOT NULL,
  remarks TEXT NULL,
  active_yes_no CHAR(1) NULL DEFAULT 'Y',
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE grant_details (
  id INT(11) NOT NULL AUTO_INCREMENT,
  grant_master_id INT(11) NOT NULL,
  financial_year INT(11) NOT NULL,
  budget_start_date DATE NOT NULL,
  budget_end_date DATE NOT NULL,
  grant_amount DOUBLE(10,2) NOT NULL,
  description TEXT NULL,
  version INT(11) NULL,
  created_by VARCHAR(20) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(20) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id),
  INDEX grant_details_FKIndex1(grant_master_id),
  FOREIGN KEY(grant_master_id)
    REFERENCES grant_master(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
TYPE=InnoDB;

CREATE TABLE project_modules (
  id INT(11) NOT NULL AUTO_INCREMENT,
  projects_id INT(11) NOT NULL,
  institution_master_id INT(11) NOT NULL,
  project_name VARCHAR(100) NOT NULL,
  principal_investigator_name VARCHAR(100) NOT NULL,
  project_submission_date DATE NOT NULL,
  description TEXT NULL,
  active_yes_no CHAR(1) NULL DEFAULT 'Y',
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id),
  INDEX project_modules_FKIndex1(projects_id),
  INDEX project_modules_FKIndex2(institution_master_id),
  FOREIGN KEY(projects_id)
    REFERENCES projects(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(institution_master_id)
    REFERENCES institution_master(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
TYPE=InnoDB;

CREATE TABLE grant_request (
  id INT(11) NOT NULL AUTO_INCREMENT,
  projects_id INT(11) NOT NULL,
  grant_details_id INT(11) NOT NULL,
  date_of_request DATE NOT NULL,
  request_status VARCHAR(100) NOT NULL,
  amount_requested DOUBLE(10,2) NOT NULL,
  description TEXT NULL,
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id),
  INDEX grant_request_FKIndex1(grant_details_id),
  INDEX grant_request_FKIndex2(projects_id),
  FOREIGN KEY(grant_details_id)
    REFERENCES grant_details(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(projects_id)
    REFERENCES projects(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
TYPE=InnoDB;

CREATE TABLE grant_allocation (
  id INT(11) NOT NULL AUTO_INCREMENT,
  project_modules_id INT(11) NOT NULL,
  installment_no INT(11) NOT NULL,
  installment_amount DOUBLE(10,2) NOT NULL,
  description TEXT NULL,
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id),
  INDEX grant_allocation_FKIndex1(project_modules_id),
  FOREIGN KEY(project_modules_id)
    REFERENCES project_modules(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
TYPE=InnoDB;

CREATE TABLE grant_approval (
  id INT(11) NOT NULL AUTO_INCREMENT,
  grant_request_id INT(11) NOT NULL,
  approval_status VARCHAR(100) NOT NULL,
  approved_date DATE NOT NULL,
  approved_amount DOUBLE(10,2) NOT NULL,
  approved_by VARCHAR(100) NOT NULL,
  description TEXT NULL,
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id),
  INDEX grant_approval_FKIndex1(grant_request_id),
  FOREIGN KEY(grant_request_id)
    REFERENCES grant_request(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
TYPE=InnoDB;

CREATE TABLE grant_allocation_details (
  id INT(11) NOT NULL AUTO_INCREMENT,
  expense_head_master_id INT(11) NOT NULL,
  grant_allocation_id INT(11) NOT NULL,
  expense_amount DOUBLE(10,2) NOT NULL,
  version INT(11) NULL,
  created_by VARCHAR(100) NULL,
  created_date DATETIME NULL,
  modified_by VARCHAR(100) NULL,
  modified_date DATETIME NULL,
  PRIMARY KEY(id),
  INDEX grant_allocation_details_FKIndex1(grant_allocation_id),
  INDEX grant_allocation_details_FKIndex2(expense_head_master_id),
  FOREIGN KEY(grant_allocation_id)
    REFERENCES grant_allocation(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(expense_head_master_id)
    REFERENCES expense_head_master(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
TYPE=InnoDB;


