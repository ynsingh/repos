INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (1, 20, 0, 'Assets', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (2, 10, 0, 'Liabilities', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (3, 30, 0, 'Incomes', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (4, 40, 0, 'Expenses', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (5, 2001, 1, 'Fixed Assets', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (6, 1001, 2, 'Capital Account', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (7, 1006, 2, 'Current Liabilities', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (8, 3001, 3, 'Grant-in-Aid', 1);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (9, 4001, 4, 'Establishment Expencses Plan', 1);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (10, 3007, 3, 'Other Incomes', 0);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (11, 4002,  4, 'Establishment Expencses Non Plan', 1);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (12, 4003, 4, 'Academic Expences Plan', 1);
INSERT INTO groups (id, code, parent_id, name, affects_gross) VALUES (13, 4004, 4, 'Academic Expences Non Plan', 1);

INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (1, 'receipt', 'Receipt', 'Received in Bank account or Cash account', 1, 1, '', '', 0, 2);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (2, 'payment', 'Payment', 'Payment made from Bank account or Cash account', 1, 1, '', '', 0, 3);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (3, 'contra', 'Contra', 'Transfer between Bank account and Cash account', 1, 1, '', '', 0, 4);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (4, 'journal', 'Journal', 'Transfer between Non Bank account and Cash account', 1, 1, '', '', 0, 5);
