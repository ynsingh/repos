CREATE TABLE budget_allocate
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        allocation_amount DECIMAL (15, 2) DEFAULT 0.00,
                        creation_date DATETIME NOT NULL,
			UNIQUE (code, allocation_amount),
                        PRIMARY KEY(id)
);

