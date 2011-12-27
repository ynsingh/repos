<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	The configuration file for the entire project
*/

$config = array(
	'db_name'	=>'gis_erp',
	'db_pass'	=>'roorkee',
	'db_user'	=>'gis_erp',
	'db_host'	=>'localhost',
);

//The default configuration below should work
define('SITE_ROOT',$_SERVER['SCRIPT_NAME']);
define('BRIHASPATI_OPEN_ID_URL','http://202.141.40.216:8081/openid/');
define('HTTP_ROOT_DIR',substr(SITE_ROOT,0,strpos($_SERVER['SCRIPT_NAME'],basename($_SERVER['SCRIPT_FILENAME']))));

//The following is to be changed
define('SITE_DOMAIN','192.168.124.26');
define('DEBUG',true);