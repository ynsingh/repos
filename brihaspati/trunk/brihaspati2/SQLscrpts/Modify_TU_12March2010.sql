use brihaspati;
ALTER TABLE TURBINE_USER modify USER_LANG VARCHAR(255) Default 'english';
update TURBINE_USER set USER_LANG='english' where USER_LANG='';

