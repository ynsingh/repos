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
