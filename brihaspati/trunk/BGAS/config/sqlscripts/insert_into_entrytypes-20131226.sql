update entry_types set name='Expance (Deferred Payment)' where id=5;

INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (6, 'dreceipt', 'Income (Deferred Receipt)', 'For immediate booking of receipt in foreign currency against letter of debet in INR as per applicable exchange rate', 1, 1, '', '', 0, 7);
