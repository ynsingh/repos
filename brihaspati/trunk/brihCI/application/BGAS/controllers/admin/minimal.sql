INSERT INTO `groups` VALUES (1,'20',0,'Application of Funds',0,0,0,'');
INSERT INTO `groups` VALUES (2,'10',0,'Sources of Funds',0,0,0,'');
INSERT INTO `groups` VALUES(3,'30',0,'Incomes',0,0,0,'');
INSERT INTO `groups` VALUES(4,'40',0,'Expenditure',0,0,0,'');
INSERT INTO `budgets` VALUES (1, '50', 0, 'Main Budget', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `projection` VALUES(1, '60', 0, 'Target Projection', 0.00, 0.00, 'Yearly');

INSERT INTO `groups` VALUES(5,'1001',2,'Capital Funds',0,0,0,'Money set aside for the purchase of capital or fixed assets, such as land, factories or manufacturing equipment.');
INSERT INTO `groups` VALUES(6,'1002',2,'Corpus',0,0,0,'corpus fund denotes a permanent fund kept for the basic expenditures needed for the administration and survival of the organization.');
INSERT INTO `groups` VALUES(7,'1003',2,'Designated-Earmarked/Endowment Funds',0,0,0,'Funds (or capital) that are set aside to pay for a specific project or event.');
INSERT INTO `groups` VALUES(8,'1004',2,'Current Liabilities & Provisions',0,0,0,'It is companys debts or obligations that are due within one year.');
INSERT INTO `groups` VALUES(9,'1005',2,'Loan/Borrowings',0,0,0,'It is a debt provided by one entity (organization or individual) to another entity at an interest rate.');
INSERT INTO `groups` VALUES(10,'1006',2,'Committed Fund',0,0,0,'A contractual agreement between an investor and a venture-capital fund that obligates the investor to contribute money to the fund.');

INSERT INTO `groups` VALUES(11,'2001',1,'Fixed Assets',0,0,0,'assets which are purchased for long-term use and are not likely to be converted quickly into cash, such as land, buildings, and equipment. ');
INSERT INTO `groups` VALUES(12,'2002',1,'Investments',0,0,0,'An investment is the purchase of goods that are not consumed today but are used in the future to create wealth.');
INSERT INTO `groups` VALUES(13,'2003',1,'Current Assets',0,0,0,'Cash and other assets that are expected to be converted to cash within a year.');
INSERT INTO `groups` VALUES(14,'2004',1,'Loans Advances and Deposits',0,0,0,'It includes loans , advances or deposits given to any party or employee.');

INSERT INTO `groups` VALUES(15,'3001',3,'Academic Receipts',0,0,0,'Incomes which are earned from academic sources such as tution fee, admission fee.');
INSERT INTO `groups` VALUES(16,'3002',3,'Grant and Donations',0,0,0,'These are externally-funded activities in which a formal written agreement,is entered into by the sponsor.');
INSERT INTO `groups` VALUES(17,'3003',3,'Income from Royalty & Publications',0,0,0,'');
INSERT INTO `groups` VALUES(18,'3004',3,'Interest Earned',0,0,0,'');
INSERT INTO `groups` VALUES(19,'3005',3,'Other Incomes',0,0,0,'');
INSERT INTO `groups` VALUES(20,'3006',3,'Prior Period Income',0,0,0,'');

INSERT INTO `groups` VALUES(21,'4001',4,'Staff Payments and Benefits(Establishment Expenses)',0,0,0,'Expenses related to payments and other benefits of staff.');
INSERT INTO `groups` VALUES(22,'4002',4,'Academic Expenses',0,0,0,'Expenses related to Academic activities such as seminar expenses etc.');
INSERT INTO `groups` VALUES(23,'4003',4,'Administrative and General expenses',0,0,0,'');
INSERT INTO `groups` VALUES(24,'4004',4,'Transportations Expenses',0,0,0,'');
INSERT INTO `groups` VALUES(25,'4005',4,'Repairs and Maintenance',0,0,0,'');
INSERT INTO `groups` VALUES(26,'4006',4,'Finance Costs',0,0,0,'It consist cost and interest and other charges involved in the borrowing of money to build or purchase assets.');
INSERT INTO `groups` VALUES(27,'4007',4,'Depreciation',0,0,0,'Expentidure as  decrease in an assets value caused by unfavorable market conditions.');
INSERT INTO `groups` VALUES(28,'4008',4,'Other expenses',0,0,0,'');
INSERT INTO `groups` VALUES(29,'4009',4,'Prior Period Expenses',0,0,0,'');

INSERT INTO `groups` VALUES(30,'200101',11,'Tangible Assets',0,0,0,'It include both fixed assets, such as machinery, buildings and land, and current assets, such as inventory.');
INSERT INTO `groups` VALUES(31,'200102',11,'Intangible Assets',0,0,0,'An asset that is not physical in nature. Corporate intellectual property (items such as patents, trademarks, copyrights, business methodologies), goodwill and brand recognition are all common intangible assets.');
INSERT INTO `groups` VALUES(32,'200103',11,'Capital Work-In-Progress',0,0,0,'');

INSERT INTO `ledgers` VALUES (1,'100101',5,'Contributions towards Capital Fund','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (2,'100201',6,'Balance of net income/expenditure transferred from I/E Account','0.00','D',0,0,0,' It includes differnce of income and expenditure which will be transferred at the time of carry forward to next financial year.');
INSERT INTO `ledgers` VALUES (3,'100202',6,'Contributions towards Corpus Fund','0.00','D',0,0,0,'It contains a permanent fund kept for the basic expenditures needed for the administration and survival of the organization.');

INSERT INTO `budgets` VALUES (2, '4001', 1, 'Staff Payments and Benefits(Establishment Expenses)', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (3, '4002', 1, 'Academic Expenses', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (4, '4003', 1, 'Administrative and General expenses', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (5, '4004', 1, 'Transportations Expenses', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (6, '4005', 1, 'Repairs and Maintenance', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (7, '4006', 1, 'Finance Costs', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (8, '4007', 1, 'Depreciation', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (9, '4008', 1, 'Other expenses', 0.00, 0.00,'Yearly', 0,0);
INSERT INTO `budgets` VALUES (10, '4009', 1, 'Prior Period Expenses', 0.00, 0.00,'Yearly', 0,0);

INSERT INTO `projection` VALUES(2, '3001', 1, 'Academic Receipts', 0.00, 0.00, 'Yearly');
INSERT INTO `projection` VALUES(3, '3002', 1, 'Grant/Subsidies and Donations', 0.00, 0.00, 'Yearly');
INSERT INTO `projection` VALUES(4, '3003', 1, 'Income from Royalty & Publications', 0.00, 0.00, 'Yearly');
INSERT INTO `projection` VALUES(5, '3004', 1, 'Interest Earned', 0.00, 0.00, 'Yearly');
INSERT INTO `projection` VALUES(6, '3005', 1, 'Other Incomes', 0.00, 0.00, 'Yearly');
INSERT INTO `projection` VALUES(7, '3006', 1, 'Prior Period Income', 0.00, 0.00, 'Yearly');

INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (1, 'receipt', 'Receipt', 'Received in Bank account or Cash account', 1, 1, '', '', 0, 2);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (2, 'payment', 'Payment', 'Payment made from Bank account or Cash account', 1, 1, '', '', 0, 3);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (3, 'contra', 'Contra', 'Transfer between Bank account and Cash account', 1, 1, '', '', 0, 4);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (4, 'journal', 'Journal', 'Transfer between Non Bank account and Cash account', 1, 1, '', '', 0, 5);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (5, 'dpayment', 'Expance (Deferred Payment)', 'For immediate booking of payment in foreign currency against letter of credot in INR as per applicable exchange rate', 1, 1, '', '', 0, 6);
INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES (6, 'dreceipt', 'Income (Deferred Receipt)', 'For immediate booking of receipt in foreign currency against letter of debet in INR as per applicable exchange rate', 1, 1, '', '', 0, 7);

insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('1',0, 200101,'Tangible Assets', 0, 10);
insert into depreciation_master (id, parent_id, code, name, percentage, life_time) values ('2',0, 200102,'Intengible assets', 0, 10);

INSERT INTO `tags` VALUES (1,'Royalty','000000','E5F1F4');
INSERT INTO `tags` VALUES (2,'SALARIES','000000','E5F1F4');
INSERT INTO `tags` VALUES (3,'WAGES','000000','E5F1F4');
INSERT INTO `tags` VALUES (4,'OVERTIME ALLOWANCE','000000','E5F1F4');
INSERT INTO `tags` VALUES (5,'PENSIONARY CHARGES','000000','E5F1F4');
INSERT INTO `tags` VALUES (6,'REWARDS','000000','E5F1F4');
INSERT INTO `tags` VALUES (7,'MEDICAL TREATMENT','000000','E5F1F4');
INSERT INTO `tags` VALUES (8,'DOMESTIC TRAVEL EXPENSES','000000','E5F1F4');
INSERT INTO `tags` VALUES (9,'FOREIGN TRAVEL EXPENSES','000000','E5F1F4');
INSERT INTO `tags` VALUES (10,'OFFICE EXPENSES','000000','E5F1F4');
INSERT INTO `tags` VALUES (11,'RENTS, RATES AND TAXES','000000','E5F1F4');
INSERT INTO `tags` VALUES (12,'PUBLICATIONS','000000','E5F1F4');
INSERT INTO `tags` VALUES (13,'BANKING CASH TRANSACTION TAX','000000','E5F1F4');
INSERT INTO `tags` VALUES (14,'OTHER ADMINISTRATIVE EXPENSES','000000','E5F1F4');
INSERT INTO `tags` VALUES (15,'SUPPLIES AND MATERIALS','000000','E5F1F4');
INSERT INTO `tags` VALUES (16,'ARMS AND AMMUNITION','000000','E5F1F4');
INSERT INTO `tags` VALUES (17,'COST OF RATION','000000','E5F1F4');
INSERT INTO `tags` VALUES (18,'P.O.L','000000','E5F1F4');
INSERT INTO `tags` VALUES (19,'CLOTHING AND TENTAGE','000000','E5F1F4');
INSERT INTO `tags` VALUES (20,'ADVERTISING AND PUBLICITY','000000','E5F1F4');
INSERT INTO `tags` VALUES (21,'MINOR WORKS','000000','E5F1F4');
INSERT INTO `tags` VALUES (22,'PROFESSIONAL SERVICES','000000','E5F1F4');
INSERT INTO `tags` VALUES (23,'OTHER CONTRACTUAL SERVICES','000000','E5F1F4');
INSERT INTO `tags` VALUES (24,'GRANTS-IN-AID','000000','E5F1F4');
INSERT INTO `tags` VALUES (25,'CONTRIBUTIONS','000000','E5F1F4');
INSERT INTO `tags` VALUES (26,'SUBSIDIES','000000','E5F1F4');
INSERT INTO `tags` VALUES (27,'SCHOLARSHIPS/STIPEND','000000','E5F1F4');
INSERT INTO `tags` VALUES (28,'GRANTS FOR CREATION OF CAPITAL ASSETS','000000','E5F1F4');
INSERT INTO `tags` VALUES (29,'SECRET SERVICE EXPENDITURE','000000','E5F1F4');
INSERT INTO `tags` VALUES (30,'LUMP SUM PROVISION','000000','E5F1F4');
INSERT INTO `tags` VALUES (31,'SUSPENSES','000000','E5F1F4');
INSERT INTO `tags` VALUES (32,'EXCHANGE VARIATIONS','000000','E5F1F4');
INSERT INTO `tags` VALUES (33,'INTEREST','000000','E5F1F4');
INSERT INTO `tags` VALUES (34,'SHARE OF TAXES/DUTIES','000000','E5F1F4');
INSERT INTO `tags` VALUES (35,'OTHER CHARGES','000000','E5F1F4');
INSERT INTO `tags` VALUES (36,'MOTOR VECHICLES','000000','E5F1F4');
INSERT INTO `tags` VALUES (37,'MACHINARY AND EQUIPMENT','000000','E5F1F4');
INSERT INTO `tags` VALUES (38,'MAJOR WORKS','000000','E5F1F4');
INSERT INTO `tags` VALUES (39,'INVESTMENTS','000000','E5F1F4');
INSERT INTO `tags` VALUES (40,'LOANS AND ADVANCES','000000','E5F1F4');
INSERT INTO `tags` VALUES (41,'REPAYMENT OF BORROWINGS','000000','E5F1F4');
INSERT INTO `tags` VALUES (42,'OTHER CAPITAL EXPENDITURE','000000','E5F1F4');
INSERT INTO `tags` VALUES (43,'DEPRECIATION','000000','E5F1F4');
INSERT INTO `tags` VALUES (44,'RESERVES','000000','E5F1F4');
INSERT INTO `tags` VALUES (45,'INTER ACCOUNT TRANSFER','000000','E5F1F4');
INSERT INTO `tags` VALUES (46,'WRITES OFF/LOSSES','000000','E5F1F4');
INSERT INTO `tags` VALUES (47,'DEDUCT RECOVERIES','000000','E5F1F4');
