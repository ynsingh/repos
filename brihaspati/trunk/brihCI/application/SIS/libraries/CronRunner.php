<?php
defined('BASEPATH') or exit('No direct script access allowed');

/**
 * @name CronRunner.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class CronRunner
{
 private $CI;

 public function __construct()
 {
	 $this->CI =& get_instance();
	 $this->CI->db2=$this->CI->load->database('payroll', TRUE);
 }

 private function calculateNextRun($obj)
 {
    return (time() + $obj->interval_sec);
 }

 public function run()
 {
   $query = $this->CI->db2->where('is_active', 1)->where('now() >= next_run_at OR next_run_at IS NULL', '', false)->from('cron')->get();
   if ($query->num_rows() > 0) {
     $env = getenv('CI_ENV');
       foreach ($query->result() as $row) {
         $cmd = "export CI_ENV={$env} && {$row->command}";
	 $this->CI->db2->set('next_run_at', 'FROM_UNIXTIME('.$this->calculateNextRun($row).')', false)->where('id', $row->id)->update('cron');
//	 $output = shell_exec(“php /{fullpath}/index.php “$cmd);
         $output = shell_exec($cmd);
         $this->CI->db2->set('last_run_at', 'now()', false)->where('id', $row->id)->update('cron');
       }
   } 
   else{
	   	print_r(" I am in else loop of cron runner lib");
	}
  }
}
