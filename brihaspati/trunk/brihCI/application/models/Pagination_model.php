
<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/**
 * @name Pagination_Model.php
 * @author Manorama Pal (palseema30@gmail.com)
 */

class Pagination_Model extends CI_Model {
    function __construct() {
    parent::__construct();
    }
    // Count all record of table in database.
    public function totalnum_rows($fieldname,$acamyear,$table) {
        $this->db->select($fieldname)->from($table)->where('pstp_acadyear',$acamyear);
        $query=$this->db->get()->num_rows();
       // echo "in model-".$query;
        return $query;
    }
    /* get all records of table*/
    public function get_listspficemore2($tbname,$selectfield,$data,$limit,$offset){
	$this->db->flush_cache();
	$this->db->from($tbname);
        $this->db->select($selectfield);
        $this->db->where($data)->limit($limit,$offset);
        return $this->db->get()->result();
    
    }

}
?>

