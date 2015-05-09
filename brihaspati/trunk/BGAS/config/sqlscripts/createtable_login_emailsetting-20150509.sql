CREATE TABLE IF NOT EXISTS emailSetting
(
                        id INTEGER (11) NOT NULL AUTO_INCREMENT,
                        email_protocol varchar(9) NOT NULL,
                        email_host varchar(255) NOT NULL,
                        email_port int(5) NOT NULL,
                        email_username varchar(255) NOT NULL,
                        email_password varchar(255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO emailSetting VALUES (1,'','','','','');

