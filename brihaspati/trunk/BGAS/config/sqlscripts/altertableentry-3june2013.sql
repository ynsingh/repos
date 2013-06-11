alter table entries ADD update_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00';
alter table entry_items ADD update_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00';
