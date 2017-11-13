use login;
ALTER TABLE  authority_archive ADD creatorid int(11) NOT NULL AFTER authority_type;
ALTER TABLE  authority_archive ADD createdate datetime NOT NULL AFTER creatorid;


