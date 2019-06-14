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

// get the number of rows in table where record is already exist with as many field you want
//$data = array('name' => $name, 'title' => $title, 'status' => $status);
    public function getnoofrows($tbname,$data='') {
        $this->db->flush_cache();
        $this->db->from($tbname);
        if($data != ''){
            $this->db->where($data);
        }
        $query = $this->db->get();
        return $query->num_rows();
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

//update the complete record from specific table with multiple where condition
//$datawh = array('name' => $name, 'title' => $title, 'status' => $status);
    public function updaterecarry($tbname, $datar,$datawh){
	 	$this->db->trans_start();
	 	if(! $this->db->where($datawh)->update($tbname, $datar))
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
		$this->db->select($selfield1);
		$this->db->from($tbname);
 //		print_r($tbname);
//		print_r($selfield1);
		$this->db->limit(1);
		if (($fieldname != '') && ($fieldvalue != '')){
			$this->db->where($fieldname, $fieldvalue);
		}
//		print_r($this->db->get()->row());
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
    public function get_listspfic2($tbname,$selfield1,$selfield2,$fieldname='',$fieldvalue='',$grpby=''){
		$this->db->flush_cache();
		$this->db->from($tbname);
		$this->db->select($selfield1);
		$this->db->select($selfield2);
		if($grpby != ''){
			$this->db->group_by($grpby);
		}
		if (($fieldname != '') && ($fieldvalue !='')){
			$this->db->where($fieldname, $fieldvalue);
		}
       // print_r($this->db->get()->result());
        return $this->db->get()->result();
    }

	//get the list of all/specific  records with  one or many specific fields for specific values
	//$sarray='name,age';	
	//$wharray = array('name' => $name, 'title' => $title, 'status' => $status);
    public function get_listarry($tbname,$sarray,$wharray){
		$this->db->flush_cache();
		$this->db->from($tbname);
		$this->db->select($sarray);
		if($wharray != ''){
			$this->db->where($wharray);
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

    //$data = array('name' => $name, 'title' => $title, 'status' => $status);
    //    getting different field from table - $selectfield ('a,b,c');
    public function get_listspficemore($tbname,$selectfield,$data){
	
	    $this->db->flush_cache();
	    $this->db->from($tbname);
		$this->db->select($selectfield);
		if($data != ''){
        	$this->db->where($data);
		}
       
        return $this->db->get()->result();
    }
    //    getting different field from table - $selectfield ('a,b,c');
    //    $whdata = array('name' => $name, 'title' => $title, 'status' => $status);
    //    $whorder = ("column1 asc,column2 desc");
    public function get_orderlistspficemore($tbname,$selectfield,$whdata,$whorder){
        $this->db->flush_cache();
        $this->db->from($tbname);
        $this->db->select($selectfield);
        if($whdata != ''){
                $this->db->where($whdata);
        }
        if($whorder != ''){
                $this->db->order_by($whorder);
        }
        return $this->db->get()->result();
    }

//    getting different field from table - $selectfield ('a,b,c');
    public function get_listmore($tbname,$selectfield){
        $this->db->flush_cache();
        $this->db->from($tbname);
        $this->db->select($selectfield);
        return $this->db->get()->result();
    }

   //$data = array('name' => $name, 'title' => $title, 'status' => $status);
    public function get_listspficemore1($tbname,$data){
        $this->db->flush_cache();
        $this->db->from($tbname);
        $this->db->where($data);
        return $this->db->get()->result();
    }
    // get the distict value
    public function get_distinctrecord($tbname,$selectfield,$whdata){
	    $this->db->flush_cache();
	    $this->db->distinct();
	    $this->db->select($selectfield);
	    $this->db->from($tbname);
	    if($whdata != ''){
                        $this->db->where($whdata);
            }
        return $this->db->get()->result();
    }

    // get the join  table result value
    public function get_jointbrecord($tbname,$selectfield,$jointbname,$joincond,$whdata){
	    $this->db->flush_cache();
	    $this->db->select($selectfield);
	    $this->db->from($tbname);
	    $this->db->join($jointbname,$joincond);
	    if($whdata != ''){
                        $this->db->where($whdata);
	    }
	    return $this->db->get()->result();
    }
    // get the sum of values
    public function get_sumofvalue($tbname,$selectfield,$whdata){
	    $this->db->flush_cache();
	    $this->db->select_sum($selectfield);
            $this->db->from($tbname);
            $this->db->where($whdata);
            return $this->db->get()->result();
    }

   function array_multi_subsort($array, $subkey)
   {
   	$b = array(); $c = array();
	foreach ($array as $k => $v)
	{
        	$b[$k] = strtolower($v->$subkey);
   	 }

    	asort($b);
    	foreach ($b as $key => $val)
    	{
        	$c[] = $array[$key];
   	 }

    return $c;
     }   	

    //PDF Generate Code
    public function genpdf($content,$path){
	$this->load->library('pdf');
	$this->pdf = new DOMPDF();	
     	// pass html to dompdf object
    	$this->pdf->load_html($content);
	$this->pdf->set_paper("A4", "portrait");
        $this->pdf->render();
	//set paper size
        $pdf = $this->pdf->output();
	file_put_contents($path, $pdf); 
   }

    function __destruct() {
        $this->db->close();
    }

}

