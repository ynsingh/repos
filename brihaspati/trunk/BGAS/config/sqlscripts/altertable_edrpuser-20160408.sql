alter table edrpuser ADD verification_code  varchar(32) NOT NULL;
alter table edrpuser ADD is_verified int(1) NOT NULL;
update edrpuser set verification_code=NULL,is_verified=1;

