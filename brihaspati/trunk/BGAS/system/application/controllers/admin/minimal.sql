INSERT INTO `groups` VALUES (1,'10',0,'Liabilities and Owners Equity',0,0);
INSERT INTO `groups` VALUES (2,'20',0,'Assets',0,0);
INSERT INTO `groups` VALUES(3,'30',0,'Incomes',0,0);
INSERT INTO `groups` VALUES(4,'40',0,'Expenses',0,0);
INSERT INTO budgets VALUES (1, '50', 0, 'Main Budget', 0.00, 'Yearly', 0,0);
INSERT INTO projection VALUES(1, '60', 0, 'Target Projection', 0.00, 'Yearly', 0.00);

INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (1, 'receipt', 'Receipt', 'Received in Bank account or Cash account', 1, 1, '', '', 0, 2);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (2, 'payment', 'Payment', 'Payment made from Bank account or Cash account', 1, 1, '', '', 0, 3);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (3, 'contra', 'Contra', 'Transfer between Bank account and Cash account', 1, 1, '', '', 0, 4);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (4, 'journal', 'Journal', 'Transfer between Non Bank account and Cash account', 1, 1, '', '', 0, 5);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (5, 'dpayment', 'Deferred Payment', 'For immediate booking of payment in foreign currency against letter of credot in INR as per applicable exchange rate', 1, 1, '', '', 0, 6);
