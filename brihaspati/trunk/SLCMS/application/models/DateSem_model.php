<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Date Sem model
 * @author: Nagendra Kumar Singh
 */
class DateSem_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
       $this->load->database();
    }
  
    public function getcurrentAcadYear(){
	// get the current year
	$cyear = date("Y");
	//  get the current month
	$cmonth = date('m');
	//  generate academic yaer
	if($cmonth>6){
		$current= $cyear.'-'.($cyear+1); 
	}
	if($cmonth<7){
		$current= ($cyear-1).'-'.$cyear; 
	}
	return $current;
    }

//this function is used for returning the next acdamic session for interence session
    public function getcurrentAcadYearfadm(){
	// get the current year
	$cyear = date("Y");
	//  get the current month
	$cmonth = date('m');
	//  generate academic yaer
	if($cmonth>6){
		$current= ($cyear+1).'-'.($cyear+2); 
	}
	if($cmonth<7){
		$current= ($cyear).'-'.($cyear+1); 
	}
	return $current;
    }

    public function getcurrentSemester(){
    	//  get the current month
	$cmonth = date('m');
	//  generate current semester(even-jan/odd-july)
	if($cmonth>6){
         	$current= "Odd" ;
        }
   	if($cmonth<7){
                $current= "Even";
        }
		return $current;
    }
    //this function is returns true if current date is less than stored date
    public function comparedate($strdate){
	    $today = date("Y-m-d");
	    $today_time = strtotime($today);
	    $expire_time = strtotime($strdate);
	    if ($expire_time < $today_time) {
		    return false;
	    }else{
		    return true;
	    }
    }

    //this function is returns diff days from  current date to stored date
    public function diffdays($exprdate){
	    $today = date("Y-m-d");
            $end = strtotime($today);
            $start = strtotime($exprdate);
	    $days_between = ceil(abs($end - $start) / 86400);
	    return $days_between;
    }
 
    function __destruct() {
        $this->db->close();
    }
}


