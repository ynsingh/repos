
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: BGAS_model
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */
class BGAS_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
//	$this->db=$this->load->database('', TRUE);
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
    
    //get the list of one/specific  records with  one specific fields for specific values
    public function get_listspfic1($tbname,$selfield1,$fieldname='',$fieldvalue=''){
	$this->db->flush_cache();
	$this->db->select($selfield1);
	$this->db->from($tbname);
	$this->db->limit(1);
	if (($fieldname != '') && ($fieldvalue != '')){
            $this->db->where($fieldname, $fieldvalue);
	}
        return $this->db->get()->row();
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
	$getd= $this->db->get();
	if(!empty($getd))
        	return $getd->result();
	else
		return;
    }

    public function get_orderlistspficemoreorwh($tbname,$selectfield,$whdata,$orfield,$orwhin,$whorder){
        $this->db->flush_cache();
        $this->db->from($tbname);
        $this->db->select($selectfield);
        if($whdata != ''){
                $this->db->where($whdata);
        }
	if($orwhin != ''){
		$this->db->where_in($orfield, $orwhin);
//		$this->db2->where_in($orwhin);
	}
        if($whorder != ''){
                $this->db->order_by($whorder);
        }
        return $this->db->get()->result();
    }

	

    // get the join  table result value
    public function get_jointbrecord($tbname,$selectfield,$jointbname,$joincond,$jtype,$whdata){
            $this->db->flush_cache();
            $this->db->select($selectfield);
            $this->db->from($tbname);
            $this->db->join($jointbname,$joincond,$jtype);
            if($whdata != ''){
                        $this->db->where($whdata);
            }
            return $this->db->get()->result();
    }

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

    public function get_orderdistinctrecord($tbname,$selectfield,$whdata,$whorder){
            $this->db->flush_cache();
            $this->db->distinct();
            $this->db->select($selectfield);
            $this->db->from($tbname);
            if($whdata != ''){
                $this->db->where($whdata);
            }
	    if($whorder != ''){
                $this->db->order_by($whorder);
            }
        return $this->db->get()->result();
    }
    

// check the record is already exist with as many field you want
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
    public function getprtype($prid){
	    $name = "Journal";
	if($prid == 1)
		$name="Cheque";
	if($prid == 2)
		$name="Cash";
	if($prid == 3)
		$name="Bank Transfer";
	if($prid == 4)
		$name="Credit Card";
	if($prid == 5)
		$name="Debit Card";
	if($prid == 6)
		$name="Demand Draft";
	if($prid == 7)
		$name="IPO";
	if($prid == 8)
		$name="Others";
	return $name;
    }

    function __destruct() {
        $this->db->close();
    }
    
}    
