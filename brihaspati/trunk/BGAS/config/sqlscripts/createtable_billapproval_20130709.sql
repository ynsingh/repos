CREATE TABLE bill_approval
(
                        bill_no INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        submit_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
			submitted_by VARCHAR (100) NOT NULL ,

			expense_type VARCHAR (100) NOT NULL,
			bill_name VARCHAR (100) NOT NULL,
			total_amount decimal(15,2) NOT NULL DEFAULT '0.00',                                                    
                        submitter_id VARCHAR (100) NOT NULL,
			decision VARCHAR (10) NOT NULL DEFAULT 'HOLD',
                        approval_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
			approved_amount decimal(15,2) NOT NULL DEFAULT '0.00',
			being BLOB,
			approved_by VARCHAR (100) NOT NULL DEFAULT 'NO',
			entry_id    INTEGER (11) NOT NULL DEFAULT '0',
			vc_date DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
			bank_cash_account VARCHAR (100) NOT NULL DEFAULT 'NO',
			mode_of_payment VARCHAR (50) NOT NULL DEFAULT 'NO',
			payment_status VARCHAR (10) NOT NULL DEFAULT 'HOLD',
			payment_date  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
			secunitid  VARCHAR(10) DEFAULT NULL,
                        fund_name  INTEGER(11) DEFAULT NULL,
			expenditure_type VARCHAR(100) DEFAULT NULL, 
			sanc_type VARCHAR(255) DEFAULT NULL,
                        sanc_value VARCHAR(255) DEFAULT NULL, 
                        PRIMARY KEY(bill_no)
);

