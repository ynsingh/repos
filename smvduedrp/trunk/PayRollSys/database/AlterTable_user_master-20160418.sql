use pl;

ALTER TABLE user_master ADD verification_code VARCHAR(36) NOT NULL after flag;
 
ALTER TABLE user_master ADD is_verified INT(1) NOT NULL;

