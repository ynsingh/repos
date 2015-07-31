

insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('1',0, 200101,'Tangible Assets', 0, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('2',0, 200102,'Intengible assets', 0, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('3',1, 20010101,'Land', 0, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('4',1, 20010113,'Site Development', 0, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('5',1, 20010104,'Buildings', 2, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('6',1, 20010113,'Raods & Bridges', 2, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('7',1, 20010112,'Tube wells & water supply', 2, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('8',1, 20010113,'Sewerage & Drainage', 2, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('9',1, 20010110,'Electrical Installation and Equipment', 5, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('10',1, 20010105, 'Plant & Machinery', 5, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('11',1, 20010113,'Scientific $ Laboratory Equipment', 8, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('12',1, 20010108, 'Office Equipment', 7.5, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('13',1, 20010113,'Audio Visual Equipment', 7.5, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('14',1, 20010109, 'Computers & Peripherals ', 20, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('15',1, 20010107,'Furniture, Fixtures & fittings', 7.5, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('16',1, 20010106,'Vehicles', 10, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('17',1, 20010111, 'Lib. Books & Scientific Journals', 10, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('18',2, 000000,'E-Journals', 40,  10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('19',2, 000000,'Computer Software', 40, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('20',2, 000000,'Patents and Copyrights', 0, 10);



CREATE TABLE IF NOT EXISTS old_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        asset_name VARCHAR (255) NOT NULL,
                        cost decimal(15,2) NOT NULL DEFAULT '0.00',
                        depreciated_value decimal(15,2) NOT NULL DEFAULT '0.00',
                        current_value decimal(15,2) NOT NULL DEFAULT '0.00',
                        Financial_year VARCHAR(100) NOT NULL,
                        narration text NOT NULL,
                        asset_status int(1) NOT NULL DEFAULT 0,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS new_asset_register(
                        id INTEGER (100)  NOT NULL AUTO_INCREMENT,
                        date_of_purchase DATETIME NOT NULL,
                        code varchar(100) NOT NULL,
                        asset_name VARCHAR (255) NOT NULL,
                        cost decimal(15,2) NOT NULL DEFAULT '0.00',
                        depreciated_value decimal(15,2) NOT NULL DEFAULT '0.00',
                        current_value decimal(15,2) NOT NULL DEFAULT '0.00',
                        Financial_year VARCHAR(100) NOT NULL,
                        narration text NOT NULL,
                        asset_status int(1) NOT NULL DEFAULT 0,
                        PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
