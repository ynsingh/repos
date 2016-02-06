delete from `depreciation_master` where id between 18 and 20;
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('18',1, 20010116, ' Miscellaneous Equipments', 20, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('19',1, 20010117,'Capital Work-In-Progress', 7.5, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('20',1, 20010118,'Small Value Assets', 10, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('21',1, 20010119, 'Others Fixed Assets', 10, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('22',2, 20010202,'E-Journals', 40,  10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('23',2, 20010201,'Computer Software', 40, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('24',2, 20010203,'Patents and Copyrights', 0, 10);


CREATE TABLE IF NOT EXISTS new_fund_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
			asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        fund_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS new_sponsored_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
			asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        project_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS old_fund_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        fund_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS old_sponsored_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        asset_id INTEGER (100)  NOT NULL,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        project_name VARCHAR (255) NOT NULL,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
