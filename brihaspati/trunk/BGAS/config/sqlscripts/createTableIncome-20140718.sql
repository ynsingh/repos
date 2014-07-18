CREATE TABLE income_from_investment
(
                        id int(11)  NOT NULL AUTO_INCREMENT,
                        fund_id int(11) NOT NULL,
			fund_name varchar(100) NOT NULL,
                        amount decimal(15, 2) NOT NULL DEFAULT 0.00,
                        date DATETIME NOT NULL,
			type varchar(100) NOT NULL,
			entry_id int(11) NOT NULL,
                        PRIMARY KEY(id)
);
