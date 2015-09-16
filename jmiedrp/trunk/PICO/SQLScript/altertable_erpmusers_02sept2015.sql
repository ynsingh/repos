alter table erpmusers modify ERPMU_Password varchar(155) NOT NULL;
alter table erpmusers DROP ERPMU_DOB;
alter table erpmusers DROP ERPMU_Secret_Question;
alter table erpmusers DROP ERPMU_Secret_Answer;
