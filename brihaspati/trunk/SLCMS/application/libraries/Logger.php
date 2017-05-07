<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/**
 * @name Logger.php
 * @author Nagendra Kumar Singh
 */

class Logger
{
	function Logger()
	{
		return;
	}

	/*
	 * Write message to file log  Levels defined are :
	 * 0 - Disables logging
	 * 1 - insert 
	 * 2 - update
	 * 3 - view 
	 */
	function write_logmessage($level = "view", $title = "", $desc = "") 	
	{
		$CI =& get_instance();

		/* Check if logging is enabled. Skip if it is not enabled */
		if ($CI->config->item('log') == "0")
			return;


		$filepath="";
		//$config =& get_config();
		
//echo		$this->log_path = ($CI->config->item('log_path') != '') ? $CI->config->item('log_path') : BASEPATH.'logs/';
echo		$this->log_path = ($CI->config->item('log_path') != '') ? $CI->config->item('log_path') : APPPATH.'logs/';

		
		$date = date("Y-m-d H:i:s");
	//	$level = 3;
		switch ($level)
		{
			case "insert": {$level = 1;$filepath = $this->log_path.'insertlog-'.date('Y-m-d').'.log';
					break;}
			case "update": {$level = 2;$filepath = $this->log_path.'updatelog-'.date('Y-m-d').'.log';
					break;}
			case "view": {$level = 3;$filepath = $this->log_path.'viewlog-'.date('Y-m-d').'.log';
		 			break;}
			default: {$level = 3;$filepath = $this->log_path.'viewlog-'.date('Y-m-d').'.log';
		 			break;}
		}

		//$filepath = $this->log_path.'log-'.date('Y-m-d').EXT;
		$message  = '';

		$host_ip = $CI->input->ip_address();
		$user = $CI->session->userdata('username');
		$url = uri_string();
		$user_agent = $CI->input->user_agent();
		$message_title = $title;
		$message_desc = $desc;

		if ( ! file_exists($filepath))
        	{
            		$message .= "<"."?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed'); ?".">\n\n";
        	}

        	if ( ! $fp = @fopen($filepath, FOPEN_WRITE_CREATE))
        	{
            		return FALSE;
        	}
		$message  =  "\n".$message_title .' '.$message_desc.' '. $user_agent .' '. $url.' by '. $user.' from '. $host_ip;
		
		flock($fp, LOCK_EX);	
		fwrite($fp, $message);
		flock($fp, LOCK_UN);
		fclose($fp);
	
		@chmod($filepath, FILE_WRITE_MODE); 		
		return TRUE;
	}

        /*
         * Write message to database log  Levels defined are :
         * 0 - Disables logging
         * 1 - insert 
         * 2 - update
         * 3 - view 
         */

	function write_dblogmessage($level = "view", $title = "", $desc = "")
        {
                $CI =& get_instance();

                /* Check if logging is enabled. Skip if it is not enabled */
                if ($CI->config->item('log') == "0")
                        return;

                $data['date'] = date("Y-m-d H:i:s");
                switch ($level)
                {
                        case "insert": $data['level'] = 1; break;
                        case "update": $data['level'] = 2; break;
                        case "view": $data['level'] = 3; break;
                        default: $data['level'] = 3; break;
                }
                $data['host_ip'] = $CI->input->ip_address();
                $data['user'] = $CI->session->userdata('username');
                $data['url'] = uri_string();
                $data['user_agent'] = $CI->input->user_agent();
                $data['message_title'] = $title;
                $data['message_desc'] = $desc;
                $CI->db->insert('logs', $data);
                return;
        }

        function read_recent_messages()
        {
                $CI =& get_instance();
                $CI->db->from('logs')->order_by('id', 'desc')->limit(20);
                $logs_q = $CI->db->get();
                if ($logs_q->num_rows() > 0)
                {
                        return $logs_q;
                } else {
                        return FALSE;
                }
        }

}

