alter table staff_detail modify column date_of_birth varchar(12);
alter table staff_detail modify column date_joining varchar(12);
alter table staff_detail modify column date_releaving varchar(12);



alter table temp_excell_import add column (title1 VARCHAR(200), subtitle1 VARCHAR(200), alt_title1 VARCHAR(200), statement_responsibility1 VARCHAR(200), main_entry1 VARCHAR(200), added_entrymli VARCHAR(200), added_entry11 VARCHAR(200), added_entry21 VARCHAR(200), added_entry31 VARCHAR(200), publisher_name1 VARCHAR(200), publication_place1 VARCHAR(200), publishing_year1 VARCHAR(20), subject1 VARCHAR(200), edition1 VARCHAR(20), collation11 VARCHAR(20), notes1 VARCHAR(2000), abstract1 VARCHAR(2000), series1 VARCHAR(1000), volume_no1 VARCHAR(20), shelving_location1 VARCHAR(200), index_no1 VARCHAR(20), no_of_pages1 VARCHAR(20), bind_type1 VARCHAR(20));

ALTER TABLE acq_invoice_detail DROP PRIMARY KEY;

ALTER TABLE acq_invoice_detail ADD PRIMARY KEY (invoice_no, library_id, recieving_item_id, recieving_no, sub_library_id);

alter table  bibliographic_details add column rating varchar(5);
alter table  bibliographic_details_lang add column rating varchar(5);

alter table  bibliographic_details add column submitted_by varchar(200);
alter table  bibliographic_details add column last_modified varchar(200);


alter table  bibliographic_details_lang add column submitted_by varchar(200);

CREATE TABLE memberexport (sno INT NOT NULL AUTO_INCREMENT, library_id VARCHAR(20), sublibrary_id VARCHAR(20), memId VARCHAR(70), fname VARCHAR(50), mname VARCHAR(40), lname VARCHAR(40), address1 VARCHAR(200), address2 VARCHAR(200), city1 VARCHAR(50), state1 VARCHAR(50), pin1 VARCHAR(15), country1 VARCHAR(60), city2 VARCHAR(50), state2 VARCHAR(50), pin2 VARCHAR(15), country2 VARCHAR(60), email VARCHAR(100), fax VARCHAR(100), phone1 VARCHAR(15), phone2 VARCHAR(15), no_of_issueable_book VARCHAR(10), current_issued_book VARCHAR(10), total_issued_book VARCHAR(10), fine VARCHAR(10), no_of_chkout VARCHAR(10), reservation_made VARCHAR(10), lastchkoutdate VARCHAR(15), status CHAR(100), password VARCHAR(50), card_id VARCHAR(20), req_date VARCHAR(15), expiry_date VARCHAR(15), mem_type VARCHAR(20), sub_member_type VARCHAR(25), desg VARCHAR(100), office VARCHAR(100), faculty_id VARCHAR(20), dept_id VARCHAR(20), course_id VARCHAR(20), semester VARCHAR(10), ApprovedBy VARCHAR(50), remark VARCHAR(1000), card_status VARCHAR(20), cardIssueDate VARCHAR(15), LostDate VARCHAR(15), DuplicateIssueDate VARCHAR(15), PRIMARY KEY (sno));


alter table bibliographic_details_lang add column last_modified VARCHAR(200);
alter table bibliographic_details add column thesis_status VARCHAR(100);
alter table bibliographic_details_lang add column thesis_status VARCHAR(100);





