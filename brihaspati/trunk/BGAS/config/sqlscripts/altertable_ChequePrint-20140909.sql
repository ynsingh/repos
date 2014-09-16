alter table cheque_print add secunitid VARCHAR (10) NOT NULL Default 0 after update_cheque_no;
alter table cheque_bounce_record add secunitid VARCHAR (10) NOT NULL  Default 0 after amount;

