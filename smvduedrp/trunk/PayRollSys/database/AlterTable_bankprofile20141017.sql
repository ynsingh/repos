use pl;

ALTER TABLE bankprofile ADD  account_number bigint(20) NOT NULL after branch_name;
ALTER TABLE bankprofile ADD  account_type varchar(40) NOT NULL after account_number;
ALTER TABLE bankprofile ADD  pan_number varchar(10) NOT NULL after account_type;
ALTER TABLE bankprofile ADD  tan_number varchar(10) NOT NULL after pan_number;
ALTER TABLE bankprofile ADD  account_name varchar(250) NOT NULL after tan_number;
