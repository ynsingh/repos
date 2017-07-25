<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Hrmgmt model
 * @author: Nagendra Kumar Singh
 */

class Hrmgmt_model extends CI_Model
{
 
    function __construct() {
	    parent::__construct();
	    $this->db3=$this->load->database('bhrm', TRUE);
 //       $this->load->database();
    }

// check the record is already exist
// check the record is already exist with as many field you want
//$data = array('name' => $name, 'title' => $title, 'status' => $status);
    public function isduplicatemore($tbname,$data) {
		$this->db3->flush_cache();
		$this->db3->from($tbname);
		$this->db3->where($data);
   	$query = $this->db3->get();
     	if ($query->num_rows() > 0) {
        	return true;
    	} else {
        	return false;
    	}
    }

// get the number of rows in table where record is already exist with as many field you want
//$data = array('name' => $name, 'title' => $title, 'status' => $status);
    public function getnoofrows($tbname,$data='') {
        $this->db3->flush_cache();
        $this->db3->from($tbname);
        if($data != ''){
            $this->db3->where($data);
        }
        $query = $this->db3->get();
        return $query->num_rows();
    }


//insert the complete record from specific table
    public function insertrec($tbname, $datar){
	 	$this->db3->trans_start();
	 	if(! $this->db3->insert($tbname, $datar))
         {
            $this->db3->trans_rollback();
            return false;
	 	}
	 	else {
            $this->db3->trans_complete();
            return true;
	 	}
    }

//update the complete record from specific table
//update the complete record from specific table with multiple where condition
//$datawh = array('name' => $name, 'title' => $title, 'status' => $status);
    public function updaterecarry($tbname, $datar,$datawh){
	 	$this->db3->trans_start();
	 	if(! $this->db3->where($datawh)->update($tbname, $datar))
	 	{
            $this->db3->trans_rollback();
            return false;
	 	}
      else {
            $this->db3->trans_complete();
            return true;
	 	}
    }


// delete the specific record form specific table
    public function deleterow($tbname,$fieldname,$fieldvalue){
		$this->db3->trans_start();
        if ( ! $this->db3->delete($tbname, array($fieldname => $fieldvalue)))
        {
            $this->db3->trans_rollback();
            return false;
		  }
        else {
            $this->db3->trans_complete();
            return true;
	 	  }
    }

//get the latest one record from specific table
    public function get_elist($tbname){
         $this->db3->from($tbname);
         return $this->db3->get()->row();
    }


//get the complete record from specific table
    public function get_list($tbname){
         $this->db3->from($tbname);
         return $this->db3->get()->result();
    }

//get the list of one/specific  records with  one specific fields for specific values
    public function get_listspfic1($tbname,$selfield1,$fieldname='',$fieldvalue=''){
		$this->db3->flush_cache();
		$this->db3->select($selfield1);
		$this->db3->from($tbname);
		$this->db3->limit(1);
		if (($fieldname != '') && ($fieldvalue != '')){
			$this->db3->where($fieldname, $fieldvalue);
		}
//		print_r($this->db->get()->row());
      return $this->db3->get()->row();
    }

//get the list of all/specific  records with  one specific fields for specific values
//get the list of all records with  two specific fields for specific values
    public function get_listspfic2($tbname,$selfield1,$selfield2,$fieldname='',$fieldvalue='',$grpby=''){
		$this->db3->flush_cache();
		$this->db3->from($tbname);
		$this->db3->select($selfield1);
		$this->db3->select($selfield2);
		if($grpby != ''){
			$this->db3->group_by($grpby);
		}
		if (($fieldname != '') && ($fieldvalue !='')){
			$this->db3->where($fieldname, $fieldvalue);
		}
       // print_r($this->db->get()->result());
        return $this->db3->get()->result();
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
    
//get the list of all records with  five specific fields for specific values
//get the list of all/specific  records with  one or many specific fields for specific values
    //$data = array('name' => $name, 'title' => $title, 'status' => $status);
    //    getting different field from table - $selectfield ('a,b,c');
    public function get_listspficemore($tbname,$selectfield,$data){
	$this->db3->flush_cache();
	$this->db3->from($tbname);
        $this->db3->select($selectfield);
        $this->db3->where($data);
        return $this->db3->get()->result();
    }

//    getting different field from table - $selectfield ('a,b,c');
    public function get_listmore($tbname,$selectfield){
        $this->db3->flush_cache();
        $this->db3->from($tbname);
        $this->db3->select($selectfield);
        return $this->db3->get()->result();
    }

    function __destruct() {
        $this->db->close();
    }

}

