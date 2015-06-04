use login;

CREATE TABLE IF NOT EXISTS forgotPass
(
                        id INTEGER NOT NULL AUTO_INCREMENT,
                        username VARCHAR (255) NOT NULL,
                        rkey VARCHAR (255) NOT NULL,
                        passdate DATETIME,
                        expdate DATETIME NOT NULL,
                        PRIMARY KEY(ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
