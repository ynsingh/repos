alter table cheque_print add paymentreceiptby VARCHAR(100) after name;
alter table cheque_print modify update_cheque_no VARCHAR(100);
alter table addsecondparty modify partyname VARCHAR(255);
