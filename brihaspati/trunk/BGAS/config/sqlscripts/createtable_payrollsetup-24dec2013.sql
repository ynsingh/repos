CREATE TABLE payrollsetup 
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
			budgetname varchar(100) NOT NULL,
			type INT(2) NOT NULL DEFAULT 0,
			UNIQUE (code, budgetname),
                        PRIMARY KEY(id)
);

