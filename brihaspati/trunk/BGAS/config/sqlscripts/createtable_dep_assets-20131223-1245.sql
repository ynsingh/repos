CREATE TABLE dep_assets
(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100) NOT NULL,
                        code INTEGER (25) NOT NULL,
                        name VARCHAR (100) NOT NULL,
                        percentage INTEGER (100) NOT NULL,
                        PRIMARY KEY(id),
                        UNIQUE (code)
);
