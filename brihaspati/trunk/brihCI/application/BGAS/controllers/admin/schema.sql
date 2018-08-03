CREATE TABLE IF NOT EXISTS groups (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(100) NOT NULL,
  parent_id int(11) NOT NULL,
  name varchar(100) NOT NULL,
  affects_gross int(1) NOT NULL DEFAULT 0,
  status int(1) NOT NULL DEFAULT 0,
  schedule varchar(10),
  group_description text,
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
  ledger_description text,
  UNIQUE(code,name),
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS budgets (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(25) NOT NULL,
  group_id int(11) NOT NULL,
  budgetname varchar(100) NOT NULL,
  bd_balance decimal(15,2) DEFAULT '0.00',
  approval_amount decimal(15,2) NOT NULL DEFAULT '0.00', 
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
  verified_by VARCHAR(255) DEFAULT NULL, 
  status int(1) NOT NULL DEFAULT 0,
  forward_refrence_id VARCHAR(55) DEFAULT NULL,
  backward_refrence_id VARCHAR(55) DEFAULT NULL,
  modifiedvalue BLOB,	
  secunitid VARCHAR(10) DEFAULT NULL,
  sanc_letter_no VARCHAR(255) DEFAULT NULL,
  sanc_letter_date datetime DEFAULT NULL,
  sanc_type VARCHAR(255) DEFAULT NULL,
  sanc_value VARCHAR(255) DEFAULT NULL,
  vendor_voucher_number varchar(55) DEFAULT NULL,
  purchase_order_no varchar(100) DEFAULT NULL,
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
  forward_refrence_id VARCHAR(55) DEFAULT NULL,
  backward_refrence_id VARCHAR(55) DEFAULT NULL,
  secunitid VARCHAR(10) DEFAULT NULL,
  ledger_code VARCHAR(55) DEFAULT NULL,
  paymentby VARCHAR(100) DEFAULT NULL,
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
  account_flag varchar(20) default 'false',
  transcationemail_status boolean NOT NULL default '0'


) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE projection
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        group_id INTEGER (11) NOT NULL,
                        projection_name VARCHAR (100) NOT NULL,
                        bd_balance DECIMAL (15, 2) DEFAULT 0.00,
                        earned_amount decimal(15,2) DEFAULT 0.00,
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
        partyname VARCHAR(255) NOT NULL,
        pfnumber VARCHAR(55) DEFAULT NULL,
        mobnum VARCHAR (25) DEFAULT NULL,
        email VARCHAR(100) DEFAULT NULL,
        address VARCHAR(255) DEFAULT NULL,
        permanentaddress varchar(255) DEFAULT NULL,
        bancacnum VARCHAR (25) DEFAULT NULL,
        bankname VARCHAR(255) DEFAULT NULL,
        branchname VARCHAR(255) DEFAULT NULL,
        ifsccode VARCHAR(25) DEFAULT NULL,
        bankaddress VARCHAR(255) DEFAULT NULL,
        pan VARCHAR(10) DEFAULT NULL,
	u_id VARCHAR (12) DEFAULT NULL,
        tan VARCHAR(15) DEFAULT NULL,
        staxnum VARCHAR(25) DEFAULT NULL,
        vat VARCHAR(25) DEFAULT NULL,
        gst VARCHAR(25) DEFAULT NULL,
        partyrole VARCHAR(100) NOT NULL,
        opbal decimal(15,2) NOT NULL DEFAULT '0.00',
        dc VARCHAR(1)  NULL,
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
  name VARCHAR (255) DEFAULT  NULL,
  paymentreceiptby VARCHAR(100) DEFAULT NULL,
  bank_name VARCHAR (255) DEFAULT NULL,
  amount decimal(15,2) NOT NULL DEFAULT '0.00',
  update_cheque_no VARCHAR (100) DEFAULT NULL,
  secunitid VARCHAR(10) NOT NULL DEFAULT 0,
  cheque_print_date datetime DEFAULT NULL,
  cheque_bounce_date datetime DEFAULT NULL,
  cheque_print_status int(1) NOT NULL DEFAULT 0,
  cheque_bounce_status int(1) NOT NULL DEFAULT 0,
  cheque_reprint_date datetime DEFAULT NULL,
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


CREATE TABLE IF NOT EXISTS fund_group_table
(

  id int(11)  NOT NULL AUTO_INCREMENT,
  code VARCHAR (255) NOT NULL,
  group_id int (11) NOT NULL,
  name VARCHAR (255) NOT NULL,
  short_name VARCHAR (11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE bill_approval
(
         bill_no INTEGER (11)  NOT NULL AUTO_INCREMENT,
         submit_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
         submitted_by VARCHAR (100) NOT NULL ,
         expense_type VARCHAR (100) NOT NULL,
         bill_name VARCHAR (100) NOT NULL,
         total_amount decimal(15,2) NOT NULL DEFAULT '0.00',
         submitter_id VARCHAR (100) NOT NULL,
         decision VARCHAR (10) NOT NULL DEFAULT 'HOLD',
         approval_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
         approved_amount decimal(15,2) NOT NULL DEFAULT '0.00',
         being BLOB,
         approved_by VARCHAR (100) NOT NULL DEFAULT 'NO',
         entry_id    INTEGER (11) NOT NULL DEFAULT '0',
         vc_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
         bank_cash_account VARCHAR (100) NOT NULL DEFAULT 'NO',
         mode_of_payment VARCHAR (50) NOT NULL DEFAULT 'NO',
         payment_status VARCHAR (10) NOT NULL DEFAULT 'HOLD',
         payment_date  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
         secunitid  VARCHAR(10) DEFAULT NULL,
         fund_name  INTEGER(11) DEFAULT NULL,
         expenditure_type VARCHAR(100) DEFAULT NULL,
         sanc_type VARCHAR(255) DEFAULT NULL,
         sanc_value VARCHAR(255) DEFAULT NULL,
         PRIMARY KEY(bill_no)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS bill_approval_status
(
        id int(11) NOT NULL AUTO_INCREMENT,
        bill_no VARCHAR(255) NOT NULL ,
        forward_from varchar(255) NOT NULL,
        forward_to varchar(255) NOT NULL,
        forward_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        approval_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        approval_amount decimal(15,2) NOT NULL DEFAULT '0.00',
        approved_by varchar(255),
	authority_name varchar(255),
        status VARCHAR (25),
        comments text,
        PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS bill_voucher_create
(
        id int(11) NOT NULL AUTO_INCREMENT,
        purchase_order_no varchar (100) NOT NULL ,
        supplier_bill_no VARCHAR (100) NOT NULL,
        submit_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        submitted_by VARCHAR (100) NOT NULL ,
        submitter_id VARCHAR (100) NOT NULL,
        bill_name VARCHAR (100) NOT NULL,
        expense_type VARCHAR (100) NOT NULL,
        total_amount decimal(15,2) NOT NULL DEFAULT '0.00',
        narration text,
        decision VARCHAR (25) NOT NULL DEFAULT 'HOLD',
        entry_id    INTEGER (11) NOT NULL DEFAULT '0',
        vc_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        bank_cash_account VARCHAR (100) NOT NULL DEFAULT 'NO',
        mode_of_payment VARCHAR (50) NOT NULL DEFAULT 'NO',
        payment_status VARCHAR (10) NOT NULL DEFAULT 'HOLD',
        payment_date  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        party_id VARCHAR(10) DEFAULT NULL,
        fund_id  INTEGER(11) DEFAULT NULL,
        expenditure_type VARCHAR(100) DEFAULT NULL,
        sanc_type VARCHAR(255) DEFAULT NULL,
        sanc_value VARCHAR(255) DEFAULT NULL,
        current_location VARCHAR(255) DEFAULT NULL,
        UNIQUE (purchase_order_no, supplier_bill_no),
        PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS depreciation_master(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        parent_id INTEGER (100) NOT NULL,
			code varchar(100) NOT NULL,
                        name VARCHAR (255) NOT NULL,
                        percentage INTEGER (100) NOT NULL,
			life_time INTEGER (100) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS old_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        asset_name VARCHAR (255) NOT NULL,
			cost decimal(15,2) NOT NULL DEFAULT '0.00',
			depreciated_value decimal(15,2) NOT NULL DEFAULT '0.00',
			current_value decimal(15,2) NOT NULL DEFAULT '0.00',
			Financial_year VARCHAR(100) NOT NULL,
			narration text NOT NULL,
			asset_status int(1) NOT NULL DEFAULT 0,
			sanc_type VARCHAR (100) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS new_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        date_of_purchase DATETIME NOT NULL,
			code varchar(100) NOT NULL,
                        asset_name VARCHAR (255) NOT NULL,
                        cost decimal(15,2) NOT NULL DEFAULT '0.00',
                        depreciated_value decimal(15,2) NOT NULL DEFAULT '0.00',
                        current_value decimal(15,2) NOT NULL DEFAULT '0.00',
                        Financial_year VARCHAR(100) NOT NULL,
			narration text NOT NULL,
                        asset_status int(1) NOT NULL DEFAULT 0,
			sanc_type VARCHAR (100) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS new_fund_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        fund_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS new_sponsored_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        project_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS old_fund_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        fund_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS old_sponsored_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        project_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `nodues` (
  `id` int(11) NOT NULL,
  `sacunitno` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `creatorid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `nodues`
--
ALTER TABLE `nodues`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `nodues`
--
ALTER TABLE `nodues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

