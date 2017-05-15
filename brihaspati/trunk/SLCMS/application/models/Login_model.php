<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Login model
 * @author: Nagendra Kumar Singh
 */
class Login_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
	$this->db1=$this->load->database('login', TRUE);
//        $this->load->database();
    }
 
    public function validate_user($data) {
        $this->db1->where('username', $data['username']);
        $this->db1->where('password', md5($data['password']));
        return $this->db1->get('edrpuser')->row();
    }
 
    public function get_authority($username){
	$this->db1->select ('authority_id');
	$this->db1->where('user_id', $username);
	$this->db1->where('till_date >=', now());
	return $this->db1->get('authority_map')->row();
    }
//insert the complete record from specific table
    public function insertrec($tbname, $datar){
         $this->db1->trans_start();
         if(! $this->db1->insert($tbname, $datar))
         {
            $this->db1->trans_rollback();
            return false;
         }
         else {
            $this->db1->trans_complete();
            return true;
         }
    }

    function __destruct() {
        $this->db1->close();
    }
}


