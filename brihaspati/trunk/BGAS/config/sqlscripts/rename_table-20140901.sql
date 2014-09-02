RENAME TABLE income_from_investment TO fund_management;

ALTER TABLE fund_management CHANGE entry_id entry_items_id int(11) NOT NULL;
