<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Login model
 * @author: Nagendra Kumar Singh
 */
class User_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
//	$this->db1=$this->load->database('login', TRUE);
       $this->load->database();
    }
  
    public function get_roleid($userid){
	$this->db->select ('roleid,type');
	$this->db->where('userid', $userid);
	return $this->db->get('user_role_type')->row();
    }

   public function get_role($roleid){
	$this->db->select ('role_name');
	$this->db->where('role_id', $roleid);
	return $this->db->get('role')->row();
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


    function __destruct() {
        $this->db->close();
    }
}


