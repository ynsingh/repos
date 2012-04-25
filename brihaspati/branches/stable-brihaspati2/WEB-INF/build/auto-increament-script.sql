use brihaspati;
 ALTER TABLE RDF DROP RDF_ID;
 ALTER TABLE RDF ADD RDF_ID INT(11) PRIMARY KEY AUTO_INCREMENT;
 
 ALTER TABLE USAGE_DETAILS DROP ENTRY_ID;
 ALTER TABLE USAGE_DETAILS ADD ENTRY_ID INT(11) PRIMARY KEY AUTO_INCREMENT;

 ALTER TABLE NOTICE_SEND DROP NOTICE_ID;
 ALTER TABLE NOTICE_SEND ADD NOTICE_ID INT(11) PRIMARY KEY AUTO_INCREMENT;
  

 ALTER TABLE DB_SEND DROP MSG_ID;
 ALTER TABLE DB_SEND ADD MSG_ID INT(11) PRIMARY KEY AUTO_INCREMENT;
 
 ALTER TABLE GLOSSARY DROP WORD_ID;
 ALTER TABLE GLOSSARY DROP WORD;
 ALTER TABLE GLOSSARY ADD WORD_ID INT(11) UNIQUE AUTO_INCREMENT;
 ALTER TABLE GLOSSARY ADD WORD VARCHAR(40) NOT NULL;
 ALTER TABLE GLOSSARY ADD PRIMARY KEY(WORD_ID,WORD);

 ALTER TABLE GLOSSARY_ALIAS DROP ALIAS_ID;
 ALTER TABLE GLOSSARY_ALIAS ADD ALIAS_ID INT(11) PRIMARY KEY AUTO_INCREMENT;
 
 ALTER TABLE CAL_INFORMATION DROP INFO_ID;
 ALTER TABLE CAL_INFORMATION DROP START_TIME;
 ALTER TABLE CAL_INFORMATION DROP USER_ID;
 ALTER TABLE CAL_INFORMATION ADD INFO_ID INT(11) UNIQUE AUTO_INCREMENT;
 ALTER TABLE CAL_INFORMATION ADD USER_ID INT(11) NOT NULL;
 ALTER TABLE CAL_INFORMATION ADD START_TIME TIME NOT NULL;
 ALTER TABLE CAL_INFORMATION ADD PRIMARY KEY(INFO_ID,START_TIME,USER_ID);

 ALTER TABLE TASK DROP TASK_ID;
 ALTER TABLE TASK DROP START_DATE;
 ALTER TABLE TASK ADD TASK_ID INT(11) UNIQUE AUTO_INCREMENT;
 ALTER TABLE TASK ADD START_DATE DATE NOT NULL;
 ALTER TABLE TASK ADD PRIMARY KEY(TASK_ID,START_DATE);

ALTER TABLE SYSTEM DROP ID;
ALTER TABLE SYSTEM ADD ID INT(11) PRIMARY KEY AUTO_INCREMENT;
