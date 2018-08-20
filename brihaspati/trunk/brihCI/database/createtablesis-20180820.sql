-- -------------------------------------------------------------------
--
-- Table structure for table hra_grade_city`
--

CREATE TABLE `hra_grade_city` (
  `hgc_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `hgc_gradename` varchar(255)  Default NULL,
  `hgc_place` blob  DEFAULT NULL,
  `hgc_distancecover` varchar(255)  DEFAULT NULL,
  `hgc_description` blob  DEFAULT NULL,
  `hgc_creatorid` varchar(255) DEFAULT NULL,
  `hgc_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hgc_modifierid` varchar(255) NOT NULL,
  `hgc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into hra_grade_city values (1,'Grade-I(a)','Chennai City','32 Kms','Chennai city and palces around the city at a distance not exceeding 32 kms from city limits. If the radius of 32 Kms. Fall within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving house rent allowance (HRA) as a admissible to Grade-I(a) place.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into hra_grade_city values (2,'Grade-I(b)','Coimbatore(UA),Madurai(UA),Salem(UA),Tiruppur(UA),Tiruchirappali(UA),Erode(UA),','16 Kms','Given palces around then at a distance not exceeding 16 kms. from the city limits and if the radius of 16 Kms. falls within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving House rent Allownace (HRA) as a admissible to Grade-I(b) palces.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);

insert into hra_grade_city values (3,'Grade-II','Ambur,Arakkonam, Arani, Aruppukkottai, Attur / Bhavani (UA), Bodinayakkanur /,Chengalpattu, Chidambaram (UA), Coonoor (UA), Cuddalore / Dharapuram, Dharmapuri,Dindigul / Erode (UA) / Gobi-chettipalayam, Gudiyattam (UA) / Hosur / Kadaiyanallur, Kambam,Kanchipuram (UA), Karaikkudi (UA), Karur (UA), Kovilpatti, Krishnagiri, Kumbakonam (UA) /Mannargudi, Mayiladuthurai, Mettuppalaiyam, Mettur / Nagappattinam (UA), Nagercoil,Namakkal, Neyveli (UA) / Palani (UA), Panruti, Paramakkudi, Pattukkottai, Pollachi (UA),Pudukkottai, Puliyangudi / Rajapalayam, Ramanathapuram / Sankarankoil, Sivakasi (UA),Srivilliputtur / Theni-Allinagaram, Tenkasi, Thanjavur, Thiruvarur, Tindivanam, Tiruchengode,Tirunelveli (UA), Tiruppattur, Tiruppur (UA), Tiruvannamalai, Thoothukkudi (UA) /Udhagamandalam, Udumalaippettai / Valparai, Vanyambadi (UA), Vellore (UA), Villupuram,Virudhunagar, Virudhachalam','8 Kms','All other Muncipal Corporations and Special Grade Muncipalities and palces around 8 Kms from town limits. If the radius of 8 Kms. Falls within a part of a Panchayat Union, the entire Panchayat Union shall be taken for the purpose of giving rent Allowance (HRA) as a admissible to Grade-II Palce.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into hra_grade_city values (4,'Grade-III','','','All other Muncipalities (expect Special Grade) and Taluka Headquarters irrespective of local body status.','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);
insert into hra_grade_city values (5,'Grade-IV','Unclassified Places','','','admin',CURRENT_TIMESTAMP,'admin',CURRENT_TIMESTAMP);


-- -------------------------------------------------------------------
--
-- Table structure for table hra_grade`
--

CREATE TABLE `hra_grade` (
  `hg_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `hg_workingtype` varchar(255)  Default NULL,
  `hg_payscaleid` int(11)  DEFAULT NULL,
  `hg_gradeid` int(11)  DEFAULT NULL,
  `hg_amount` double  DEFAULT NULL,
  `hg_creatorid` varchar(255) DEFAULT NULL,
  `hg_creatordate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hg_modifierid` varchar(255) NOT NULL,
  `hg_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

