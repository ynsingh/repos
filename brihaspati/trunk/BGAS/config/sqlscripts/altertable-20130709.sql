alter table settings add ins_name varchar(255)default null;
alter table budgets drop column op_balance_dc;
alter table budgets modify column allowedover varchar(10) NOT NULL default'0';
