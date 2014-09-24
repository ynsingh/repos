CREATE TABLE IF NOT EXISTS groups (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(100) NOT NULL,
  parent_id int(11) NOT NULL,
  name varchar(100) NOT NULL,
  affects_gross int(1) NOT NULL DEFAULT 0,
  status int(1) NOT NULL DEFAULT 0,
  schedule varchar(10),
  UNIQUE(code,name),
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS ledgers (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(100) NOT NULL,
  group_id int(11) NOT NULL,
  name varchar(100) NOT NULL,
  op_balance decimal(15,2) NOT NULL DEFAULT '0.00',
  op_balance_dc char(1) NOT NULL,
  type INT(2) NOT NULL DEFAULT 0,
  reconciliation int(1) NOT NULL,
  lstatus int(1) NOT NULL DEFAULT 0,
  UNIQUE(code,name),
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS budgets (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(25) NOT NULL,
  group_id int(11) NOT NULL,
  budgetname varchar(100) NOT NULL,
  bd_balance decimal(15,2) DEFAULT '0.00',
  type varchar(50) NOT NULL,
  allowedover varchar(10) NOT NULL DEFAULT 0,
  consume_amount decimal(15,2)NOT NULL DEFAULT '0.00',
  UNIQUE(code,budgetname),
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS budget_allocate(
  id INTEGER (11)  NOT NULL AUTO_INCREMENT,
  code VARCHAR (25) NOT NULL,
  allocation_amount DECIMAL (15, 2) DEFAULT 0.00,
  creation_date DATETIME NOT NULL,
  UNIQUE (code, allocation_amount),
  PRIMARY KEY(id)
)ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS entry_types (
  id int(5) NOT NULL,
  label varchar(15) NOT NULL,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL,
  base_type int(2) NOT NULL,
  numbering int(2) NOT NULL,
  prefix varchar(10) NOT NULL,
  suffix varchar(10) NOT NULL,
  zero_padding int(2) NOT NULL,
  bank_cash_ledger_restriction int(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS entries (
  id int(11) NOT NULL AUTO_INCREMENT,
  tag_id int(11) DEFAULT NULL,
  entry_type int(5) NOT NULL,
  number varchar(55) DEFAULT NULL,
  date datetime NOT NULL,
  dr_total decimal(15,2) NOT NULL DEFAULT '0.00',
  cr_total decimal(15,2) NOT NULL DEFAULT '0.00',
  narration text NOT NULL,
  update_date datetime NOT NULL,
  submitted_by VARCHAR(255) NOT NULL,
  verified_by VARCHAR(255) NOT NULL,
  status int(1) NOT NULL DEFAULT 0,
  forward_refrence_id VARCHAR(55) DEFAULT NULL,
  backward_refrence_id VARCHAR(55) DEFAULT NULL,
  modifiedvalue BLOB,	
  secunitid VARCHAR(10) DEFAULT NULL,
  sanc_letter_no VARCHAR(255) DEFAULT NULL,
  sanc_letter_date datetime DEFAULT NULL,
  sanc_type VARCHAR(255) DEFAULT NULL,
  sanc_value VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS entry_items (
  id int(11) NOT NULL AUTO_INCREMENT,
  entry_id int(11) NOT NULL,
  ledger_id int(11) NOT NULL,
  amount decimal(15,2) NOT NULL DEFAULT '0.00',
  dc char(1) NOT NULL,
  reconciliation_date datetime NULL,
  update_date datetime NOT NULL,
  forward_refrence_id int(11) DEFAULT NULL,
  backward_refrence_id int(11) DEFAULT NULL,
  secunitid VARCHAR(10) DEFAULT NULL,	
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS tags (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  color varchar(6) NOT NULL,
  background varchar(6) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  level int(1) NOT NULL,
  host_ip varchar(25) NOT NULL,
  user varchar(25) NOT NULL,
  url varchar(255) NOT NULL,
  user_agent varchar(100) NOT NULL,
  message_title varchar(255) NOT NULL,
  message_desc mediumtext NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS settings (
  id int(1) NOT NULL,
  name varchar(100) NOT NULL,
  address varchar(255) NOT NULL,
  email varchar(100) NOT NULL,
  fy_start datetime NOT NULL,
  fy_end datetime NOT NULL,
  currency_symbol varchar(10) NOT NULL,
  date_format varchar(30) NOT NULL,
  timezone varchar(255) NOT NULL,
  manage_inventory int(1) NOT NULL,
  account_locked int(1) NOT NULL,
  email_protocol varchar(9) NOT NULL,
  email_host varchar(255) NOT NULL,
  email_port int(5) NOT NULL,
  email_username varchar(255) NOT NULL,
  email_password varchar(255) NOT NULL,
  print_paper_height float NOT NULL,
  print_paper_width float NOT NULL,
  print_margin_top float NOT NULL,
  print_margin_bottom float NOT NULL,
  print_margin_left float NOT NULL,
  print_margin_right float NOT NULL,
  print_orientation varchar(1) NOT NULL,
  print_page_format varchar(1) NOT NULL,
  database_version int(10) NOT NULL,
  ins_name varchar(255) default NULL,
  dept_name varchar(255) default NULL,
  uni_name varchar(255) default NULL,
  ledger_name varchar(100) default NULL,
  liability_ledger_name varchar(100) default NULL,
  chart_account varchar(100) default 'minimal',
  account_flag varchar(20) default 'false'

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS studentmaster (
  PAR_seq_id int(11) NOT NULL auto_increment,
  reg_no varchar(20) NOT NULL, 
  entry_no varchar(20) default NULL,
  batch varchar(20) default NULL,
  program varchar(20) default NULL,
  student_opbal_amount double(15,2) default NULL,
  degree_code int(11) default NULL,
  org_id varchar(400) default NULL,
  department_id int(11) default NULL,
  branch_id int(11) default NULL,
  sem_code int(11) default NULL,
  fee_head_code int(11) default NULL,
  PRIMARY KEY  (PAR_seq_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS employeemaster (
  emp_id int(11) NOT NULL auto_increment,
  emp_name varchar(70) NOT NULL,
  emp_code varchar(30) NOT NULL,
  emp_dept_code int(11) NOT NULL,
  emp_desig_code int(11) NOT NULL,
  emp_type_code int(11) NOT NULL,
  emp_phone varchar(30) default NULL,
  emp_email varchar(30) default NULL,
  emp_dob date default NULL,
  emp_doj date default NULL,
  emp_salary_grade int(11) NOT NULL,
  emp_bank_accno varchar(20) default NULL,
  emp_pf_accno varchar(20) default NULL,
  emp_pan_no varchar(20) default NULL,
  emp_gender varchar(10) NOT NULL default '1',
  emp_org_code int(11) NOT NULL default '0',
  emp_father varchar(100) default NULL,
  emp_basic int(11) NOT NULL default '0',
  emp_title varchar(50) NOT NULL default 'Mr.',
  emp_exp int(11) NOT NULL,
  emp_qual varchar(100) NOT NULL,
  emp_yop int(11) NOT NULL,
  emp_prev_emp varchar(200) NOT NULL,
  emp_address varchar(200) NOT NULL,
  emp_active tinyint(4) default '1',
  bank_ifsc_code varchar(500) default NULL,
  emp_bank_status tinyint(1) default '0',
  dor varchar(100) default NULL,
  emp_leaving varchar(100) default NULL,
  emp_noti_day int(11) default NULL,
  citizen varchar(100) default NULL,
  PRIMARY KEY  (emp_code),
  UNIQUE KEY(emp_id),
  UNIQUE KEY(emp_code,emp_org_code)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS party (
  PAR_Id int(11) NOT NULL AUTO_INCREMENT,
  PAR_Name int(10) unsigned NOT NULL, 
  PAR_IM_Id int(5) unsigned NOT NULL,
  PAR_Supplier_Type int(10) unsigned DEFAULT NULL,
  PAR_Ownership_Type int(10) unsigned DEFAULT NULL,
  PAR_Deals_With varchar(200) DEFAULT NULL,
  PAR_Year_Establishment date DEFAULT NULL,
  PAR_PAN_No varchar(15) DEFAULT NULL,
  PAR_TAN_No varchar(15) DEFAULT NULL,
  PAR_STATE_ST_VAT_RGN_NO varchar(15) DEFAULT NULL,
  PAR_CEN_ST_VAT_RGN_NO varchar(15) DEFAULT NULL,
  PAR_ED_RGN_NO varchar(15) DEFAULT NULL,
  PAR_ECC_CODE varchar(15) DEFAULT NULL,
  PAR_ENTREPRENURE_MEM_NO varchar(15) DEFAULT NULL,
  PAR_REG_DATE date NOT NULL,
  PAR_Remarks varchar(100) DEFAULT NULL,
  PAR_CEO_OR_PROPRIETOR_NAME varchar(100) DEFAULT NULL,
  PRIMARY KEY (PAR_Id),
  UNIQUE KEY(PAR_IM_Id,PAR_Name),
  UNIQUE KEY(PAR_IM_Id,PAR_PAN_No),
  UNIQUE KEY(PAR_IM_Id,PAR_TAN_No)
) ENGINE=InnoDB DEFAULT CHARSET=latin1  AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS erpm_item_master (
  ERPMIM_ID int(11) NOT NULL auto_increment,
  ERPMIM_IM_ID int(11) unsigned default NULL COMMENT 'This filed stores Institution for which Item has been created',
  ERPMIM_Item_Brief_Desc varchar(500) NOT NULL,
  ERPMIM_Item_Cat1 int(11) unsigned NOT NULL,
  ERPMIM_Item_Cat2 int(11) unsigned NOT NULL,
  ERPMIM_Item_Cat3 int(11) unsigned NOT NULL,
  ERPMIM_UOP int(10) unsigned NOT NULL,
  ERPMIM_Make varchar(20) default NULL,
  ERPMIM_Model varchar(20) default NULL,
  ERPMIM_Capital_Cat int(10) unsigned default NULL,
  ERPMIM_Detailed_Desc varchar(2000) NOT NULL,
  ERPMIM_Remarks varchar(100) default NULL,
  PRIMARY KEY  (ERPMIM_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE projection
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        group_id INTEGER (11) NOT NULL,
                        projection_name VARCHAR (100) NOT NULL,
                        bd_balance DECIMAL (15, 2) DEFAULT 0.00,
                        earned_amount decimal(15,2) DEFAULT '0.00',
                        type VARCHAR (50) NOT NULL,
                        PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE projection_allocate
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        allocation_amount DECIMAL (15, 2) DEFAULT 0.00,
                        creation_date DATETIME NOT NULL,
                        PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS dep_assets(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100) NOT NULL,
                        code INTEGER (25) NOT NULL,
                        name VARCHAR (100) NOT NULL,
                        percentage INTEGER (100) NOT NULL,
                        PRIMARY KEY(id),
                        UNIQUE (code)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS dep_archive(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        dep_amount INTEGER (255) NOT NULL,
                        creation_date DATETIME NOT NULL,
                        UNIQUE (code, id),
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE payrollsetup
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        budgetname varchar(100) NOT NULL,
                        type INT(2) NOT NULL DEFAULT 0,
                        UNIQUE (code, budgetname),
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS reconcilation
(

                        id int(11) NOT NULL  AUTO_INCREMENT,
                        name VARCHAR (255) NOT NULL,
			bank_name VARCHAR (255) NOT NULL,
                        cheque_date DATETIME NOT NULL,
                        dc char(1) NOT NULL,
                        amount decimal(15,2) NOT NULL DEFAULT '0.00',
                        entry_no int(11) DEFAULT NULL,
                        cheque_no INTEGER (255) NOT NULL,
                        rec_status int(1) NOT NULL DEFAULT 0,
			cheque_print_status int(1) NOT NULL DEFAULT 0,
                        PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS addsecondparty (
        id int(11) NOT NULL AUTO_INCREMENT,
        sacunit VARCHAR(25) NOT NULL,
        partyname VARCHAR(100) NOT NULL,
        mobnum VARCHAR (25) DEFAULT NULL,
        email VARCHAR(100) DEFAULT NULL,
        address VARCHAR(255) DEFAULT NULL,
        permanentaddress varchar(255) DEFAULT NULL,
        bancacnum VARCHAR (25) DEFAULT NULL,
        bankname VARCHAR(255) DEFAULT NULL,
        branchname VARCHAR(255) DEFAULT NULL,
        ifsccode VARCHAR(25) DEFAULT NULL,
        bankaddress VARCHAR(255) DEFAULT NULL,
        pan VARCHAR(15) DEFAULT NULL,
        tan VARCHAR(15) DEFAULT NULL,
        staxnum VARCHAR(15) DEFAULT NULL,
        partyrole VARCHAR(100) NOT NULL,
        PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1  AUTO_INCREMENT=1;

CREATE TABLE bgas_acl
(
id int(11) NOT NULL AUTO_INCREMENT,
username varchar(100) NOT NULL,
headid varchar(100) NOT NULL,
roleid int(11) NOT NULL,
ptype int(1) NOT NULL,
atype varchar(20) NOT NULL,
PRIMARY KEY(id),
UNIQUE (username, headid, roleid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS cheque_print (
  id int(11) NOT NULL AUTO_INCREMENT,
  ledger_id int(11) NOT NULL,
  entry_no int(11) NOT NULL,
  name VARCHAR (255) NOT NULL,
  bank_name VARCHAR (255) NOT NULL,
  amount decimal(15,2) NOT NULL DEFAULT '0.00',
  update_cheque_no VARCHAR (25) NOT NULL,
  secunitid VARCHAR(10) NOT NULL DEFAULT 0,
  cheque_print_date datetime NOT NULL,
  cheque_bounce_date datetime NOT NULL,
  cheque_print_status int(1) NOT NULL DEFAULT 0,
  cheque_bounce_status int(1) NOT NULL DEFAULT 0,
  cheque_reprint_date datetime NOT NULL,
  No_of_bounce_cheque VARCHAR(15) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS cheque_bounce_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  ledger_id int(11) NOT NULL,
  entry_no int(11) NOT NULL,
  name VARCHAR (255) NOT NULL,
  bank_name VARCHAR (255) NOT NULL,
  amount decimal(15,2) NOT NULL DEFAULT '0.00',
  secunitid VARCHAR(10) NOT NULL DEFAULT 0,
  new_cheque_no VARCHAR (25) NOT NULL,
  cheque_bounce_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS fund_management
(
	id int(11)  NOT NULL AUTO_INCREMENT,
        fund_id int(11) NOT NULL,
        fund_name varchar(100) NOT NULL,
        amount decimal(15, 2) NOT NULL DEFAULT 0.00,
        date DATETIME NOT NULL,
        type varchar(100) NOT NULL,
	entry_items_id int(11) NOT NULL,
        PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

