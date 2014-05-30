CREATE TABLE bgas_acl
(
id int(11) NOT NULL AUTO_INCREMENT,
username varchar(100) NOT NULL,
headid varchar(100) NOT NULL,
roleid int(11) NOT NULL,
ptype int(1) NOT NULL,
atype varchar(20) NOT NULL, 
PRIMARY KEY(id),
UNIQUE (username, headid, roleid)
);

