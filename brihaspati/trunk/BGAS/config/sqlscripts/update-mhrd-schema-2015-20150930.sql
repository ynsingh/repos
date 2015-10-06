delete from `groups` where id between 10 and 82;
delete from `groups` where id between 94 and 96;
delete from `groups` where id between 101 and 102;
delete from `ledgers` where id between 120 and 145;

INSERT INTO `ledgers` VALUES (589,'100107',5,'Grants from Government of India to the extent utilized for capital expenditure','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (590,'100108',5,'Grants from State Government to the extent utilized for capital expenditure','0.00','D',0,0,0,'');

INSERT INTO `ledgers` VALUES (591,'10020214',8,'Grants from UGC for Revenue Expenditure','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (592,'10020215',8,'Grants from Government of India for Revenue Expenditure','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (593,'10020216',8,'Grants from State Government for Revenue Expenditure','0.00','D',0,0,0,'');

delete from  `ledgers` where id=2;
INSERT INTO `ledgers` VALUES (2,'100102',5,'Grants from UGC to the extent utilized for capital expenditure','0.00','D',0,0,0,'');

INSERT INTO `ledgers` VALUES (588,'300203',203,'State Government Grants','0.00','C',0,0,0,'');

delete from  `ledgers` where id=376;
INSERT INTO `ledgers` VALUES (376,'300601',215,'PPI Academic Receipts','0.00','C',0,0,0,'');
INSERT INTO `ledgers` VALUES (585,'300602',215,'PPI Income from Investments ','0.00','C',0,0,0,'');
INSERT INTO `ledgers` VALUES (586,'300603',215,'PPI Interest earned','0.00','C',0,0,0,'');
INSERT INTO `ledgers` VALUES (587,'300604',215,'PPI Other Income','0.00','C',0,0,0,'');

INSERT INTO `ledgers` VALUES (578,'40010508',221,'Payment of DCRG','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (579,'40010509',221,'Payment of Commutation','0.00','D',0,0,0,'');

INSERT INTO `groups` VALUES(273,'10040204',109,'Provision Received From Other Organisation For Retirement Benefits',0,0,0,'');

INSERT INTO `ledgers` VALUES (580,'1004020401',273,'Provision Received From Other Organisation For Gratuity','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (581,'1004020402',273,'Provision Received From Other Organisation For Leave Encashment','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (582,'1004020403',273,'Provision Received From Other Organisation For Pension/C.V. of Pension','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (583,'1004020404',273,'Provision Received From Other Organisation For DCRG','0.00','D',0,0,0,'');
INSERT INTO `ledgers` VALUES (584,'1004020405',273,'Provision Received From Other Organisation For Commutation','0.00','D',0,0,0,'');
