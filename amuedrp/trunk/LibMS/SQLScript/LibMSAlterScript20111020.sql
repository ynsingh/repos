CREATE TABLE cancellation_reason (library_id VARCHAR(20) NOT NULL, sublibrary_id VARCHAR(20) NOT NULL, id VARCHAR(50) NOT NULL, details VARCHAR(200), PRIMARY KEY (id, library_id, sublibrary_id));

CREATE TABLE deliquency_reason (library_id VARCHAR(20) NOT NULL, sublibrary_id VARCHAR(20) NOT NULL, id VARCHAR(50) NOT NULL, details VARCHAR(200), PRIMARY KEY (id, library_id, sublibrary_id));

alter  TABLE cir_member_account add column (card_status VARCHAR(20));
alter  TABLE cir_member_account add column (cardIssueDate VARCHAR(15));
alter  TABLE cir_member_account add column (LostDate VARCHAR(15));
alter  TABLE cir_member_account add column (DuplicateIssueDate VARCHAR(15));

alter table cir_member_detail add column(college VARCHAR(200));
alter table cir_member_detail add column(coll_address VARCHAR(150));
alter table cir_member_detail add column(temp_status VARCHAR(1));
