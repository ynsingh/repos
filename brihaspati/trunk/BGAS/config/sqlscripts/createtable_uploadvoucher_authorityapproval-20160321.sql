drop table  if exists bill_approval_status;
CREATE TABLE IF NOT EXISTS bill_approval_status
(
        id int(11) NOT NULL AUTO_INCREMENT,
        bill_no VARCHAR(255) NOT NULL ,
        forward_from varchar(255) NOT NULL,
        forward_to varchar(255) NOT NULL,
        forward_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
        approval_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
        approval_amount decimal(15,2) NOT NULL DEFAULT '0.00',
        approved_by varchar(255),
        authority_name varchar(255),
        status VARCHAR (25),
        comments text,
        PRIMARY KEY(id) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

drop table if exists bill_voucher_create;
CREATE TABLE IF NOT EXISTS bill_voucher_create
(
        id int(11) NOT NULL AUTO_INCREMENT,
        purchase_order_no varchar (100) NOT NULL ,
        supplier_bill_no VARCHAR (100) NOT NULL,
        submit_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
        submitted_by VARCHAR (100) NOT NULL ,
        submitter_id VARCHAR (100) NOT NULL,
        bill_name VARCHAR (100) NOT NULL,
        expense_type VARCHAR (100) NOT NULL,
        total_amount decimal(15,2) NOT NULL DEFAULT '0.00',
        narration text,
        decision VARCHAR (25) NOT NULL DEFAULT 'HOLD',
        entry_id    INTEGER (11) NOT NULL DEFAULT '0',
        vc_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
        bank_cash_account VARCHAR (100) NOT NULL DEFAULT 'NO',
        mode_of_payment VARCHAR (50) NOT NULL DEFAULT 'NO',
        payment_status VARCHAR (10) NOT NULL DEFAULT 'HOLD',
        payment_date  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
        party_id VARCHAR(10) DEFAULT NULL,
	fund_id  INTEGER(11) DEFAULT NULL,
        expenditure_type VARCHAR(100) DEFAULT NULL,
        sanc_type VARCHAR(255) DEFAULT NULL,
        sanc_value VARCHAR(255) DEFAULT NULL,
        current_location VARCHAR(255) DEFAULT NULL,
        UNIQUE (purchase_order_no, supplier_bill_no),
        PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER TABLE budgets ADD approval_amount DECIMAL (15,2) NOT NULL DEFAULT '0.00' after bd_balance;

ALTER TABLE entries ADD purchase_order_no VARCHAR (100) DEFAULT NULL after vendor_voucher_number;

