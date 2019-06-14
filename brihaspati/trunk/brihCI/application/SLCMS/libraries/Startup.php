<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

/**
 * Startup:: a class that is loaded everytime the application is accessed
 *
 * Setup all the initialization routines that the application uses in this
 * class. It is autoloaded evertime the application is accessed.
 *
 */

class Startup
{
	function __construct()
	{
		$CI =& get_instance();
//		$CI->db->trans_strict(FALSE);
                $logndb = $CI->load->database('login', TRUE);
		$logndb->select('*')->from('createsession');
//		$logndb->select('*')->from('createsession')->where('fstatus','final');
		$acc_data = $logndb->get();
//		print_r($acc_data->result()); die;
		if (!empty($acc_data->result())){
			foreach($acc_data->result() as $row)
        	        {
				$db_name = $row->dbname;
				$batch = $row ->batchyear;
				$fstat = $row->fstatus;
				$db_hostname = $row->dbhostname;
				$db_username = $row->dbuname;
				$db_password = $row->dbpass; 
			}
			/* Preparing database settings */
                       // $db_config['hostname'] = $active_account['db_hostname'];
                     //   $db_config['hostname'] .= ":" . $active_account['db_port'];
                        //$db_config['database'] = $active_account['db_name'];
                        //$db_config['username'] = $active_account['db_username'];
                        //$db_config['password'] = $active_account['db_password'];
                        $db_config['hostname'] = $db_hostname;
                     //   $db_config['hostname'] .= ":" . $active_account['db_port'];
                        $db_config['database'] = $db_name;
                        $db_config['username'] = $db_username;
                        $db_config['password'] = $db_password;
                        $db_config['dbdriver'] = "mysqli";
                        $db_config['dbprefix'] = "";
                        $db_config['pconnect'] = FALSE;
                        $db_config['db_debug'] = FALSE;
                        $db_config['cache_on'] = FALSE;
                        $db_config['cachedir'] = "";
                        $db_config['char_set'] = "utf8";
                        $db_config['dbcollat'] = "utf8_general_ci";
                        $CI->load->database($db_config, FALSE, TRUE);
			return;
		}
		else{
			echo "One database entry is missing in create session table in login database";
			die;
			return;
		}
	}

}

/* End of file startup.php */
/* Location: ./application/libraries/startup.php */
