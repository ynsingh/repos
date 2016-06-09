#
# Structure for the `org_tax_calc_type` table : 
#

use pl;

CREATE TABLE `org_tax_calc_type` ( 
 `ct_id` int(11) NOT NULL auto_increment,
  `ct_calctype` varchar(50) NULL,
  `ct_sess_id` int(11) NOT NULL,
  `ct_org_code` int(11) NOT NULL,
  PRIMARY KEY  (`ct_id`), 
  KEY `ct_sess_id` (`ct_sess_id`), 
  KEY `ct_org_code` (`ct_org_code`),
  CONSTRAINT `org_tax_calc_type_fk2` FOREIGN KEY (`ct_sess_id`) REFERENCES `session_master` (`ss_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `org_tax_calc_type_fk4` FOREIGN KEY (`ct_org_code`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO org_tax_calc_type (ct_calctype,ct_sess_id,ct_org_code) VALUES('YEARLY',(select ss_id from session_master where ss_current='1'),(select ss_org_id from session_master where ss_current='1') ); 
