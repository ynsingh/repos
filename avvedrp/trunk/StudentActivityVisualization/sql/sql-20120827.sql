ALTER TABLE email_settings ADD COLUMN isSSL VARCHAR(255) NULL AFTER smtp_username;

UPDATE email_settings SET isSSL='true' ;
