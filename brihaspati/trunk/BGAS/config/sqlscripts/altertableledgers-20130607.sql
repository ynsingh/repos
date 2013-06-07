alter table ledgers add status int(1) NOT NULL DEFAULT 0 after reconciliation;

CREATE TABLE IF NOT EXISTS budgets (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(25) NOT NULL,
  group_id int(11) NOT NULL,
  budgetname varchar(100) NOT NULL,
  bd_balance decimal(15,2) DEFAULT '0.00',
  op_balance_dc char(1) DEFAULT NULL,
  type varchar(50) NOT NULL,
  allowedover int(1) NOT NULL DEFAULT 0,
  UNIQUE(code,budgetname),
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
