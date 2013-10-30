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
);

CREATE TABLE projection_allocate
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        allocation_amount DECIMAL (15, 2) DEFAULT 0.00,
                        creation_date DATETIME NOT NULL,
                        PRIMARY KEY(id)
);
