use brihaspati;

 ALTER TABLE FAQMOVE DROP INST_ID;
 ALTER TABLE FAQMOVE DROP USER_ID;
 ALTER TABLE FAQMOVE ADD MSG_ID INTEGER(11) NOT NULL;
 ALTER TABLE FAQMOVE ADD SENDER_NAME VARCHAR(40);
 ALTER TABLE FAQMOVE ADD MSG_SUBJECT VARCHAR(40);
 ALTER TABLE FAQMOVE ADD PERMISSION INTEGER(11);
 ALTER TABLE FAQMOVE ADD STATUS INTEGER(11);
 ALTER TABLE FAQMOVE ADD PRIMARY KEY(MSG_ID);

