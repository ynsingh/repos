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

//get the complete record from specific table
    public function get_list($tbname){
         $this->db->from($tbname);
         return $this->db->get()->result();
    }

//get the complete of record for specific value
    public function get_listrow($tbname,$fieldname,$fieldvalue){
         $this->db->from($tbname);
	 $this->db->where($fieldname, $fieldvalue);
         return $this->db->get()->result();
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

//get the list of all records with  five specific fields for specific values
    public function get_listspfic($tbname,$selfield1='',$selfield2='',$selfield3='',$selfield4='',$selfield5='',$fieldname='',$fieldvalue=''){
	if (($selfield1 != '') && ($selfield2 != '') && ($selfield3 != '') && ($selfield4 != '') && ($selfield5 != ''))
		$this->db->select($selfield1,$selfield2,$selfield3,$selfield4,$selfield5);
	else if (($selfield1 != '') && ($selfield2 != '') && ($selfield3 != '') && ($selfield4 != ''))
		$this->db->select($selfield1,$selfield2,$selfield3,$selfield4);
	else if (($selfield1 != '') && ($selfield2 != '') && ($selfield3 != '') )
		$this->db->select($selfield1,$selfield2,$selfield3);
	else if (($selfield1 != '') && ($selfield2 != '') )
		$this->db->select($selfield1,$selfield2);
	else if (($selfield1 != '')  )
		$this->db->select($selfield1);
	if (($fieldname != '') && ($fieldvalue !=''))
		$this->db->where($fieldname, $fieldvalue);
        return $this->db->get($tbname)->result();
    }

    function __destruct() {
        $this->db->close();
    }
}

