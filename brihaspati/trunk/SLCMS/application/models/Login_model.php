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
    public function getpassword($datau) {
 	$this->db1->select('password');
        $this->db1->where('username', $datau);
        return $this->db1->get('edrpuser')->row();
    }

    //update the complete record from specific table
    public function updaterec($tbname, $datar,$fieldname,$fieldvalue){
         $this->db1->trans_start();
         if(! $this->db1->where($fieldname, $fieldvalue)->update($tbname, $datar))
         {
            $this->db1->trans_rollback();
            return false;
         }
         else {
            $this->db1->trans_complete();
            return true;
         }
    }
    
    //get the list of all/specific  records with  one specific fields for specific values
    public function get_listspfic1($tbname,$selfield1,$fieldname='',$fieldvalue=''){
	$this->db1->flush_cache();
	$this->db1->select($selfield1);
	if (($fieldname != '') && ($fieldvalue !='')){
		$this->db1->where($fieldname, $fieldvalue);
	}
        return $this->db1->get($tbname)->row();
    }

    // check the record is already exist
    public function isduplicate($tbname,$fieldname,$fieldvalue) {
        $this->db1->from($tbname);
        $this->db1->where($fieldname, $fieldvalue);
        $query = $this->db1->get();
        if ($query->num_rows() > 0) {
                return true;
        } else {
                return false;
        }
    }


    function __destruct() {
        $this->db1->close();
    }
}


