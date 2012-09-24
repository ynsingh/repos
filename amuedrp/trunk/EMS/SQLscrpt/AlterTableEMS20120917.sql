ALTER TABLE ruleanswer ADD position_id int;

ALTER TABLE ruleanswer DROP PRIMARY KEY;

ALTER TABLE ruleanswer ADD PRIMARY KEY(rule_id,position_id,election_id,institute_id,enrollment);
