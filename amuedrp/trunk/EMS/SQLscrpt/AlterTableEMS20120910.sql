ALTER TABLE electionrule
ADD position_id int;

ALTER TABLE electionrule
DROP PRIMARY KEY;

ALTER TABLE electionrule
ADD PRIMARY KEY(rule_id,position_id,election_id,institute_id);
