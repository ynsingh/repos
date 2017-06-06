<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Common model
 * @author: Nagendra Kumar Singh
 */

class Common_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
        $this->load->database();
    }

// check the record is already exist
    public function isduplicate($tbname,$fieldname,$fieldvalue) {
    	$this->db->from($tbname);
	$this->db->where($fieldname, $fieldvalue);
    	$query = $this->db->get();
     	if ($query->num_rows() > 0) {
        	return true;
    	} else {
        	return false;
    	}
    }

// check the record is already exist with as many field you want
//$data = array('name' => $name, 'title' => $title, 'status' => $status);
    public function isduplicatemore($tbname,$data) {
	$this->db->flush_cache();
	$this->db->from($tbname);
	$this->db->where($data);
   	$query = $this->db->get();
     	if ($query->num_rows() > 0) {
        	return true;
    	} else {
        	return false;
    	}
    }


//insert the complete record from specific table
    public function insertrec($tbname, $datar){
	 $this->db->trans_start();
	 if(! $this->db->insert($tbname, $datar))
         {
            $this->db->trans_rollback();
            return false;
	 }
	 else {
            $this->db->trans_complete();
            return true;
	 }
    }


//update the complete record from specific table
    public function updaterec($tbname, $datar,$fieldname,$fieldvalue){
	 $this->db->trans_start();
	 if(! $this->db->where($fieldname, $fieldvalue)->update($tbname, $datar))
	 {
            $this->db->trans_rollback();
            return false;
	 }
         else {
            $this->db->trans_complete();
            return true;
	 }
    }

// delete the specific record form specific table
    public function deleterow($tbname,$fieldname,$fieldvalue){
	$this->db->trans_start();
        if ( ! $this->db->delete($tbname, array($fieldname => $fieldvalue)))
        {
            $this->db->trans_rollback();
            return false;
	}
        else {
            $this->db->trans_complete();
            return true;
	 }
    }

//get the latest one record from specific table
    public function get_elist($tbname){
         $this->db->from($tbname);
         return $this->db->get()->row();
    }


//get the complete record from specific table
    public function get_list($tbname){
         $this->db->from($tbname);
         return $this->db->get()->result();
    }

//get the complete of record for specific value
    public function get_listrow($tbname,$fieldname,$fieldvalue){
         $this->db->from($tbname);
	 $this->db->where($fieldname, $fieldvalue);
         return $this->db->get();
    }


//get the list of one/specific  records with  one specific fields for specific values
    public function get_listspfic1($tbname,$selfield1,$fieldname='',$fieldvalue=''){
	$this->db->flush_cache();
	$this->db->from($tbname);
	$this->db->select($selfield1);
	$this->db->limit(1);
	if (($fieldname != '') && ($fieldvalue !='')){
		$this->db->where($fieldname, $fieldvalue);
	}
        return $this->db->get()->row();
    }
//get the list of all/specific  records with  one specific fields for specific values
    public function get_listspficarry($tbname,$selfield1,$fieldname='',$fieldvalue=''){
	$this->db->flush_cache();
	$this->db->from($tbname);
	$this->db->select($selfield1);
	if (($fieldname != '') && ($fieldvalue !='')){
		$this->db->where($fieldname, $fieldvalue);
	}
        return $this->db->get()->result();
    }


//get the list of all records with  two specific fields for specific values
    public function get_listspfic2($tbname,$selfield1,$selfield2,$fieldname='',$fieldvalue=''){
	$this->db->flush_cache();
	$this->db->from($tbname);
	$this->db->select($selfield1);
	$this->db->select($selfield2);
	if (($fieldname != '') && ($fieldvalue !='')){
		$this->db->where($fieldname, $fieldvalue);
	}
       // print_r($this->db->get()->result());
        return $this->db->get()->result();
    }

//get the list of all records with  five specific fields for specific values
    public function get_listspfic($tbname,$selfield1='',$selfield2='',$selfield3='',$selfield4='',$selfield5='',$fieldname='',$fieldvalue=''){
	$this->db->flush_cache();
	$this->db->from($tbname);
	if (($selfield1 != '') && ($selfield2 != '') && ($selfield3 != '') && ($selfield4 != '') && ($selfield5 != '')){
		$this->db->select($selfield1);
		$this->db->select($selfield2);
		$this->db->select($selfield3);
		$this->db->select($selfield4);
		$this->db->select($selfield5);
	}
	else if (($selfield1 != '') && ($selfield2 != '') && ($selfield3 != '') && ($selfield4 != '')){
		$this->db->select($selfield1);
		$this->db->select($selfield2);
		$this->db->select($selfield3);
		$this->db->select($selfield4);
	}
	else if (($selfield1 != '') && ($selfield2 != '') && ($selfield3 != '') ){
		$this->db->select($selfield1);
		$this->db->select($selfield2);
		$this->db->select($selfield3);
	}
	else if (($selfield1 != '') && ($selfield2 != '') ){
		$this->db->select($selfield1);
		$this->db->select($selfield2);
	}
	else if (($selfield1 != '')  ){
		$this->db->select($selfield1);
	}

	if (($fieldname != '') && ($fieldvalue !='')){
		$this->db->where($fieldname, $fieldvalue);
	}
        return $this->db->get()->result();
    }

// Generate random number any length
    function randNum($length)
    {
    	$str = mt_rand(1, 9); // first number (0 not allowed)
    	for ($i = 1; $i < $length; $i++)
        	$str .= mt_rand(0, 9);

    	return $str;
    }

// Check the number is positive integer
    function pinteger($value){
	if ((is_int($value) || ctype_digit($value)) && ((int)$value > 0 )) { 
		return true;// int 
	}
	else {
		return false;
	}

    }
    
    //get the list of one/specific  records with  one specific fields for specific values
    public function get_depid($tbname,$userid,$roleid){
        $this->db->select('deptid')->from('user_role_type')->where('userid',$userid)->where('roleid',$roleid);
	return $this->db->get()->row();
    }

    function __destruct() {
        $this->db->close();
    }
}

