<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: University model
 * @author: Nagendra Kumar Singh
 */
class University_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
        $this->load->database();
    }

 /*
    public function validate_user($data) {
        $this->db->where('username', $data['username']);
        $this->db->where('password', md5($data['password']));
        return $this->db->get('edrpuser')->row();
    }
 */
    public function get_countryname($contcode){
	$this->db->select("country_name");
	$this->db->where('country_code',$contcode);
	return $this->db->get('country')->row();
    }	

    public function get_udetails(){
	$this->db->select ('*');
	return $this->db->get('org_profile')->row();
    }

    function __destruct() {
        $this->db->close();
    }
}


