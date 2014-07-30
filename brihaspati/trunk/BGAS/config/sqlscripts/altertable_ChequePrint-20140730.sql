alter table cheque_print add ledger_id int(11) NOT NULL after id;
alter table cheque_print add amount decimal(15,2) NOT NULL DEFAULT '0.00' after bank_name;
alter table cheque_print add update_cheque_no VARCHAR (25) NOT NULL after amount;
alter table cheque_print drop column cheque_no;

alter table cheque_bounce_record add ledger_id int(11) NOT NULL after id;
alter table cheque_bounce_record add amount decimal(15,2) NOT NULL DEFAULT '0.00' after bank_name;
alter table cheque_bounce_record  drop column old_cheque_no;
