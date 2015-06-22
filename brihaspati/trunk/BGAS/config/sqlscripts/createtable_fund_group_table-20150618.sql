CREATE TABLE IF NOT EXISTS  fund_group_table
(
    id int(11)  NOT NULL AUTO_INCREMENT,
    code VARCHAR (255) NOT NULL,
    group_id int (11) NOT NULL,
    name VARCHAR (255) NOT NULL,
    short_name VARCHAR (11) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
