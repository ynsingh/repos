use pl;
ALTER TABLE user_master  modify user_pass VARCHAR(200);
UPDATE user_master set user_pass=MD5(user_pass);
