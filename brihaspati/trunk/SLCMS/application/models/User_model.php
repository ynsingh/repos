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

    function __destruct() {
        $this->db->close();
    }
}


