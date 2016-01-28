alter table addsecondparty modify pan VARCHAR(10) DEFAULT NULL;
alter table addsecondparty modify vat VARCHAR(25) DEFAULT NULL;
alter table addsecondparty modify gst VARCHAR(25) DEFAULT NULL;
alter table addsecondparty modify staxnum VARCHAR(25) DEFAULT NULL;
alter table addsecondparty add u_id VARCHAR (12)  DEFAULT NULL after pan;
