
CREATE TABLE dep_archive
(
                        id INTEGER (11)  NOT NULL AUTO_INCREMENT,
                        code VARCHAR (25) NOT NULL,
                        dep_amount INTEGER (255) NOT NULL,
                        creation_date DATETIME NOT NULL,
                        UNIQUE (code, id),
                        PRIMARY KEY(id)
);



