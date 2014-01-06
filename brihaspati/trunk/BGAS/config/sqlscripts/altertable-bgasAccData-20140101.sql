use login;
alter table bgasAccData change year fyear VARCHAR(14);
alter table bgasAccData add uname VARCHAR(255) NOT NULL  Default 'bgas' after fyear;
alter table bgasAccData add dbpass VARCHAR(255) NOT NULL  Default 'bgas' after uname;
alter table bgasAccData add hostname VARCHAR(255) NOT NULL  Default 'localhost' after dbpass;
alter table bgasAccData add port int(7) NOT NULL  Default '3306' after hostname;
alter table bgasAccData add dbtype VARCHAR(255) NOT NULL  Default 'mysql' after port;
alter table bgasAccData add dblable VARCHAR(255) NOT NULL UNIQUE  after dbtype;
