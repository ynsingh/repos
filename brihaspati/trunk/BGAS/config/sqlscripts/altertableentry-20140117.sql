alter table entries add forward_refrence_id int(11) DEFAULT NULL;
alter table entries add backward_refrence_id int(11) DEFAULT NULL;
alter table entry_items add forward_refrence_id int(11) DEFAULT NULL;
alter table entry_items add backward_refrence_id int(11) DEFAULT NULL;
