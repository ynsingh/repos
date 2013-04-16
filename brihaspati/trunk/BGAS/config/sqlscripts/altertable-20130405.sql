alter table groups add code varchar(100) after id;
alter table ledgers add code varchar(100) after id;
alter table groups add unique(code, name);
alter table ledgers add unique(code, name);

