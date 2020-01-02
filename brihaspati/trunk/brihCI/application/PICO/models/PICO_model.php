
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: PICO_model
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Manorama pal (palseema30@gmail.com)
 * @author: Om Prakash (omprakashkgp@gmail.com) check the record is already exist 
 */
class PICO_model extends CI_Model
{
 
    function __construct() {
    parent::__construct();
	$this->db4=$this->load->database('pico', TRUE);
    }
    //insert the complete record from specific table
    public function insertrec($tbname, $datar){
         $this->db4->trans_start();
         if(! $this->db4->insert($tbname, $datar))
         {
            $this->db4->trans_rollback();
            return false;
         }
         else {
            $this->db4->trans_complete();
            return true;
         }
    }
    //update the complete record from specific table
    public function updaterec($tbname, $datar,$fieldname,$fieldvalue){
         $this->db4->trans_start();
         if(! $this->db4->where($fieldname, $fieldvalue)->update($tbname, $datar))
         {
            $this->db4->trans_rollback();
            return false;
         }
         else {
            $this->db4->trans_complete();
            return true;
         }
    }
	//update the complete record from specific table with multiple where condition
    //$datawh = array('name' => $name, 'title' => $title, 'status' => $status);
    public function updaterecarry($tbname, $datar,$datawh){
        $this->db4->trans_start();
        if(! $this->db4->where($datawh)->update($tbname, $datar))
                {
            $this->db4->trans_rollback();
            return false;
                }
	else {
            $this->db4->trans_complete();
            return true;
                }
    }

    // check the record is already exist
    public function isduplicate($tbname,$fieldname,$fieldvalue) {
        $this->db4->from($tbname);
        $this->db4->where($fieldname, $fieldvalue);
        $query = $this->db4->get();
        if ($query->num_rows() > 0) {
                return true;
        } else {
                return false;
        }
    }
    
    // get the number of rows in table where record is already exist with as many field you want
//$data = array('name' => $name, 'title' => $title, 'status' => $status);
    public function getnoofrows($tbname,$data='') {
        $this->db4->flush_cache();
        $this->db4->from($tbname);
        if($data != ''){
            $this->db4->where($data);
        }
        $query = $this->db4->get();
        return $query->num_rows();
    }

    //get the list of one/specific  records with  one specific fields for specific values
    public function get_listspfic1($tbname,$selfield1,$fieldname='',$fieldvalue=''){
	$this->db4->flush_cache();
	$this->db4->select($selfield1);
	$this->db4->from($tbname);
	$this->db4->limit(1);
	if (($fieldname != '') && ($fieldvalue != '')){
            $this->db4->where($fieldname, $fieldvalue);
	}
        return $this->db4->get()->row();
    }
    
    // get the sum of values
    public function get_sumofvalue($tbname,$selectfield,$whdata){
            $this->db4->flush_cache();
            $this->db4->select_sum($selectfield);
            $this->db4->from($tbname);
            $this->db4->where($whdata);
            return $this->db4->get()->result();
    }

    // get the max values of selected field
    public function get_maxvalue($tbname,$selectfield,$whdata){
            $this->db4->flush_cache();
            $this->db4->select_max($selectfield);
            $this->db4->from($tbname);
            $this->db4->where($whdata);
            return $this->db4->get()->result();
    }

     // get the max values of selected field
    public function get_minvalue($tbname,$selectfield,$whdata){
            $this->db4->flush_cache();
            $this->db4->select_min($selectfield);
            $this->db4->from($tbname);
            $this->db4->where($whdata);
            return $this->db4->get()->result();
    }

    //get the list of all records with  two specific fields for specific values
    public function get_listspfic2($tbname,$selfield1,$selfield2,$fieldname='',$fieldvalue='',$grpby=''){
                $this->db4->flush_cache();
                $this->db4->from($tbname);
                $this->db4->select($selfield1);
                $this->db4->select($selfield2);
                if($grpby != ''){
                        $this->db4->group_by($grpby);
                }
                if (($fieldname != '') && ($fieldvalue !='')){
                        $this->db4->where($fieldname, $fieldvalue);
                }
       // print_r($this->db->get()->result());
        return $this->db4->get()->result();
    }
	// $spl = "empid NOT IN";
	public function get_rundualquery($sel1,$tb1,$sel2, $tb2,$spl,$whdata,$whorder){
		$this->db4->select($sel1)->from($tb1);
		$subQuery =  $this->db4->get_compiled_select();
 
		// Main Query
		$this->db4->select($sel2)
	         ->from($tb2)
        	 ->where("$spl ($subQuery)", NULL, FALSE);
		if($whdata != ''){
                	$this->db4->where($whdata);
	        }
        	if($whorder != ''){
                	$this->db4->order_by($whorder);
        	}

	        return $this->db4->get()->result();
	}

    //    getting different field from table - $selectfield ('a,b,c');
    public function get_listspficemore($tbname,$selectfield,$data){
	$this->db4->flush_cache();
	$this->db4->from($tbname);
        $this->db4->select($selectfield);
        $this->db4->where($data);
        return $this->db4->get()->result();
    }

    //    getting different field from table - $selectfield ('a,b,c');
    //    $whdata = array('name' => $name, 'title' => $title, 'status' => $status);
    //    $whorder = ("column1 asc,column2 desc");
    public function get_orderlistspficemore($tbname,$selectfield,$whdata,$whorder){
        $this->db4->flush_cache();
        $this->db4->from($tbname);
        $this->db4->select($selectfield);
        if($whdata != ''){
                $this->db4->where($whdata);
        }
        if($whorder != ''){
                $this->db4->order_by($whorder);
        }
        return $this->db4->get()->result();
    }

    //    getting different field from table - $selectfield ('a,b,c');
    //    $whdata = array('name' => $name, 'title' => $title, 'status' => $status);
    //    $whorder = ("column1 asc,column2 desc");
    public function get_orderlistspficemoreorwhnotin($tbname,$selectfield,$whdata,$orfield,$orwhin,$whorder){
        $this->db4->flush_cache();
        $this->db4->from($tbname);
        $this->db4->select($selectfield);
        if($whdata != ''){
                $this->db4->where($whdata);
        }
	if($orwhin != ''){
                $this->db4->where_not_in($orfield, $orwhin);
        }
        if($whorder != ''){
                $this->db4->order_by($whorder);
        }
//	print_r($this->db4); die();
        return $this->db4->get()->result();
    }



    public function get_orderlistspficemoreorwh($tbname,$selectfield,$whdata,$orfield,$orwhin,$whorder){
        $this->db4->flush_cache();
        $this->db4->from($tbname);
        $this->db4->select($selectfield);
        if($whdata != ''){
                $this->db4->where($whdata);
        }
	if($orwhin != ''){
		$this->db4->where_in($orfield, $orwhin);
//		$this->db4->where_in($orwhin);
	}
        if($whorder != ''){
                $this->db4->order_by($whorder);
        }
        return $this->db4->get()->result();
    }


    // get the join  table result value
    public function get_jointbrecord($tbname,$selectfield,$jointbname,$joincond,$jtype,$whdata,$whorder=''){
            $this->db4->flush_cache();
            $this->db4->select($selectfield);
            $this->db4->from($tbname);
            $this->db4->join($jointbname,$joincond,$jtype);
            if($whdata != ''){
                        $this->db4->where($whdata);
            }
            if($whorder != ''){
                $this->db4->order_by($whorder);
        		}
            return $this->db4->get()->result();
    }
    
  

    public function get_distinctrecord($tbname,$selectfield,$whdata){
            $this->db4->flush_cache();
            $this->db4->distinct();
            $this->db4->select($selectfield);
            $this->db4->from($tbname);
            if($whdata != ''){
                        $this->db4->where($whdata);
            }
        return $this->db4->get()->result();
    }

    public function get_orderdistinctrecord($tbname,$selectfield,$whdata,$whorder){
            $this->db4->flush_cache();
            $this->db4->distinct();
            $this->db4->select($selectfield);
            $this->db4->from($tbname);
            if($whdata != ''){
                $this->db4->where($whdata);
            }
	        if($whorder != ''){
                $this->db4->order_by($whorder);
            }
        return $this->db4->get()->result();
    }
	public function get_orderdistinctrecordgrpby($tbname,$selectfield,$whdata,$whorder,$grpby){
            $this->db4->flush_cache();
            $this->db4->distinct();
            $this->db4->select($selectfield);
            $this->db4->from($tbname);
	    $this->db4->group_by($grpby);
            if($whdata != ''){
                $this->db4->where($whdata);
            }
            if($whorder != ''){
                $this->db4->order_by($whorder);
            }
        return $this->db4->get()->result();
    }
	// echo mask($string,null,strlen($string)-4); // *************5678
	function mask ( $str, $start = 0, $length = null ) {
	    	$mask = preg_replace ( "/\S/", "*", $str );
    		if( is_null ( $length )) {
        		$mask = substr ( $mask, $start );
		        $str = substr_replace ( $str, $mask, $start );
    		}else{
        		$mask = substr ( $mask, $start, $length );
        		$str = substr_replace ( $str, $mask, $start, $length );
    		}
    		return $str;
	}	

    /** this function for get hod user list according to study center************************/
    //get the complete record from specific table
    public function get_list($tbname){
         $this->db4->from($tbname);
         return $this->db4->get()->result();
    }
    
    //get the complete of record for specific value
    public function get_listrow($tbname,$fieldname,$fieldvalue,$selectfield='*'){
         $this->db4->from($tbname);
	 $this->db4->select($selectfield);
	 $this->db4->where($fieldname, $fieldvalue);
         return $this->db4->get();
    }

	// check the record is already exist with as many field you want
    public function isduplicatemore($tbname,$data) {
                $this->db4->flush_cache();
                $this->db4->from($tbname);
                $this->db4->where($data);
        $query = $this->db4->get();
        if ($query->num_rows() > 0) {
                return true;
        } else {
                return false;
        }
    }
    
    
    
    /*************************************Start transfer order pdf *****************************************************************************/
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
    
    public function insertdata($tbname, $datar){
        $this->db4->trans_start();
        if(! $this->db4->insert($tbname, $datar))
        {
            $this->db4->trans_rollback();
            return false;
        }
        else {
            $entry_id = $this->db4->insert_id();
            $this->db4->trans_complete();
            return $entry_id;
        }     
    }
    
    // delete the specific record form specific table
    public function deleterow($tbname,$fieldname,$fieldvalue){
	$this->db4->trans_start();
        if ( ! $this->db4->delete($tbname, array($fieldname => $fieldvalue)))
        {
            $this->db4->trans_rollback();
            return false;
	}
        else {
            $this->db4->trans_complete();
            return true;
        }
    }
    
    function __destruct() {
        $this->db4->close();
    }
    
}    
