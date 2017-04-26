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
	 * Write message to database log
	 * Levels defined are :
	 * 0 - Disables logging
	 * 1 - insert 
	 * 2 - update
	 * 3 - view 
	 */
	function write_logmessage($level = "view", $title = "", $desc = "") 	
	{
		$CI =& get_instance();

		/* Check if logging is enabled. Skip if it is not enabled */
		if ($CI->config->item('log') != "0")
			return;


		$filepath="";
		//$config =& get_config();
		
		$this->log_path = ($CI->config->item('log_path') != '') ? $CI->config->item('log_path') : BASEPATH.'logs/';

		
		$date = date("Y-m-d H:i:s");
		$level = 3;
		switch ($level)
		{
			case "insert": {$level = 1;$filepath = $this->log_path.'insertlog-'.date('Y-m-d').'log';
					break;}
			case "update": {$level = 2;$filepath = $this->log_path.'updatelog-'.date('Y-m-d').'log';
					break;}
			case "view": {$level = 3;$filepath = $this->log_path.'viewlog-'.date('Y-m-d').'log';
		 			break;}
			default: {$level = 3;$filepath = $this->log_path.'viewlog-'.date('Y-m-d').'log';
		 			break;}
		}

		//$filepath = $this->log_path.'log-'.date('Y-m-d').EXT;
		$message  = '';

		$host_ip = $CI->input->ip_address();
		$user = $CI->session->userdata('user_name');
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
		$message  =  $message_title .' '.$message_desc.' '. $user_agent .' '. $url.' by '. $user.' from '. $host_ip;
		
		flock($fp, LOCK_EX);	
		fwrite($fp, $message);
		flock($fp, LOCK_UN);
		fclose($fp);
	
		@chmod($filepath, FILE_WRITE_MODE); 		
		return TRUE;
	}
}

