<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

if ( ! function_exists('date_php_to_mysql'))
{
	function date_php_to_mysql($dt)
	{
		$CI =& get_instance();
		$current_date_format = $CI->config->item('account_date_format');
		//		list($d, $m, $y) = array(0, 0, 0);
		$date="1970-01-02";
//		echo $dt;
		if((!empty($dt)) && (isset($dt))){ 
		$tim="00:00:00";
		$dtt = explode(" ",$dt);
		$date_array = explode("/",$dtt[0]);
//		print_r($date_array);
		switch ($current_date_format)
		{
		case 'dd/mm/yyyy':
			$var_day = $date_array[0]; //day seqment
			$var_month = $date_array[1]; //month segment
			$var_year = $date_array[2]; //year segment
			if (!empty($var_year)){
				$date = "$var_year-$var_month-$var_day $tim";
			}
			//	list($d, $m, $y) = explode('/', $dt);
		//	$date = preg_replace('#(\d{2})/(\d{2})/(\d{4})\s(.*)#', '$3-$2-$1 $tim', $dt);
			break;
		case 'mm/dd/yyyy':
			$var_day = $date_array[1]; //day seqment
			$var_month = $date_array[0]; //month segment
			$var_year = $date_array[2]; //year segment
			$date = "$var_year-$var_month-$var_day $tim";
	//		list($m, $d, $y) = explode('/', $dt);
		//	$date = preg_replace('#(\d{2})/(\d{2})/(\d{4})\s(.*)#', '$3-$1-$2 $tim', $dt);
			break;
		case 'yyyy/mm/dd':
			$var_day = $date_array[2]; //day seqment
			$var_month = $date_array[1]; //month segment
			$var_year = $date_array[0]; //year segment
			$date = "$var_year-$var_month-$var_day $tim";
	//		list($y, $m, $d) = explode('/', $dt);
			//$date = preg_replace('#(\d{4})/(\d{2})/(\d{2})\s(.*)#', '$1-$2-$3 $tim', $dt);
			break;
		default:
			$CI->messages->add('Invalid date format. Check your account settings.', 'error');
			return $date;
		}//end switch
		}//end if empty and set
	//	$ts = mktime(0, 0, 0, $m, $d, $y);
	//	return date('Y-m-d H:i:s', $ts);
		return $date;
	}
}

if ( ! function_exists('date_php_to_mysql_end_time'))
{
	function date_php_to_mysql_end_time($dt)
	{
		$CI =& get_instance();
		$current_date_format = $CI->config->item('account_date_format');
		//list($d, $m, $y) = array(0, 0, 0);
		$date="1970-01-02";
//		if(!empty($dt)){ 
		if((!empty($dt)) && (isset($dt))){
		$tim="23:59:59";
		$dtt = explode(" ",$dt);
                $date_array = explode("/",$dtt[0]);
		switch ($current_date_format)
		{
		case 'dd/mm/yyyy':
			$var_day = $date_array[0]; //day seqment
			$var_month = $date_array[1]; //month segment
			$var_year = $date_array[2]; //year segment
			$date = "$var_year-$var_month-$var_day $tim";
		//	list($d, $m, $y) = explode('/', $dt);
	//		$date = preg_replace('#(\d{2})/(\d{2})/(\d{4})\s(.*)#', '$3-$2-$1 $tim', $dt);
			break;
		case 'mm/dd/yyyy':
			$var_day = $date_array[1]; //day seqment
			$var_month = $date_array[0]; //month segment
			$var_year = $date_array[2]; //year segment
			$date = "$var_year-$var_month-$var_day $tim";
		//	list($m, $d, $y) = explode('/', $dt);
	//		$date = preg_replace('#(\d{2})/(\d{2})/(\d{4})\s(.*)#', '$3-$1-$2 $tim', $dt);
			break;
		case 'yyyy/mm/dd':
			$var_day = $date_array[2]; //day seqment
			$var_month = $date_array[1]; //month segment
			$var_year = $date_array[0]; //year segment
			$date = "$var_year-$var_month-$var_day $tim";
		//	list($y, $m, $d) = explode('/', $dt);
			//$date = preg_replace('#(\d{4})/(\d{2})/(\d{2})\s(.*)#', '$1-$2-$3 $tim', $dt);
			break;
		default:
			$CI->messages->add('Invalid date format. Check your account settings.', 'error');
			return "";
		}
		}
	//	$ts = mktime("23", "59", "59", $m, $d, $y);
	//	return date('Y-m-d H:i:s', $ts);
		return $date;
	}
}

if ( ! function_exists('date_mysql_to_php'))
{
	function date_mysql_to_php($dt)
	{
		$ts = human_to_unix($dt);
		$CI =& get_instance();
		$current_date_format = $CI->config->item('account_date_format');
		if (date('Y', $ts) != 1970 ){
		switch ($current_date_format)
		{
		case 'dd/mm/yyyy':
			return date('d/m/Y', $ts);
			break;
		case 'mm/dd/yyyy':
			return date('m/d/Y', $ts);
			break;
		case 'yyyy/mm/dd':
			return date('Y/m/d', $ts);
			break;
		default:
			$CI->messages->add('Invalid date format. Check your account settings.', 'error');
			return "";
		}
		}
		return "";
	}
}

if ( ! function_exists('date_mysql_to_timestamp'))
{
	function date_mysql_to_timestamp($dt)
	{
		return strtotime($dt);
	}
}

if ( ! function_exists('date_mysql_to_php_display'))
{
	function date_mysql_to_php_display($dt)
	{
		$ts = human_to_unix($dt);
		$CI =& get_instance();
		$current_date_format = $CI->config->item('account_date_format');
		if (date('Y', $ts) != 1970 ){
		switch ($current_date_format)
		{
		case 'dd/mm/yyyy':
			return date('d M Y', $ts);
			break;
		case 'mm/dd/yyyy':
			return date('M d Y', $ts);
			break;
		case 'yyyy/mm/dd':
			return date('Y M d', $ts);
			break;
		default:
			$CI->messages->add('Invalid date format. Check your account settings.', 'error');
			return "";
		}
		}
		return "";
	}
}

if ( ! function_exists('date_today_php'))
{
	function date_today_php()
	{
		$CI =& get_instance();

		/* Check for date beyond the current financial year range */
		$todays_date = date('Y-m-d 00:00:00');
		$fy_start = $CI->config->item('account_fy_start');
		$fy_end = $CI->config->item('account_fy_end');
		if ($CI->config->item('account_fy_start') > $todays_date)
			return date_mysql_to_php($fy_start);
		if ($CI->config->item('account_fy_end') < $todays_date)
			return date_mysql_to_php($fy_end);

		$current_date_format = $CI->config->item('account_date_format');
		switch ($current_date_format)
		{
		case 'dd/mm/yyyy':
			return date('d/m/Y');
			break;
		case 'mm/dd/yyyy':
			return date('m/d/Y');
			break;
		case 'yyyy/mm/dd':
			return date('Y/m/d');
			break;
		default:
			$CI->messages->add('Invalid date format. Check your account settings.', 'error');
			return "";
		}
		return;
	}
}

/* End of file date_helper.php */
/* Location: ./system/application/helpers/date_helper.php */
