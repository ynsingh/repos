/* ---- Table for storing spring-security secure url expressions ---- */

CREATE  TABLE intercept_url (
  id INT NOT NULL AUTO_INCREMENT ,
  pattern VARCHAR(255) NULL ,
  access VARCHAR(255) NULL ,
  sequence INT NULL ,
  enabled VARCHAR(45) NULL ,
  PRIMARY KEY (id) );
  
  INSERT INTO intercept_url (id,pattern,access,sequence,enabled) VALUES (1,'/**','ROLE_ADMIN,ROLE_ADMIN_UNIVERSITY,ROLE_PROFILE_CREATION',1,'1');

/*======================= Release 1.7 ============================*/
CREATE TABLE user_openID_map (
  user_id varchar(50) DEFAULT NULL,
  openid varchar(255) DEFAULT NULL,
  id int(11) NOT NULL AUTO_INCREMENT,
  UNIQUE KEY id (id)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

ALTER TABLE user_openID_map  ADD PRIMARY KEY(openid);

UPDATE staff_profile_e_publication_links_v0_itemtypes SET choice='5,35' WHERE ACTION='text_area';
UPDATE staff_profile_consultancy_offered_v0_itemtypes SET choice='5,35' WHERE ACTION='text_area';

