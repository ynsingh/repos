alter table entries add submitted_by varchar(255) NOT null;
alter table entries add verified_by varchar(255) NOT NULL;
alter table entries add status int(1) NOT NULL DEFAULT 0;
alter table entries add modifiedvalue BLOB;
