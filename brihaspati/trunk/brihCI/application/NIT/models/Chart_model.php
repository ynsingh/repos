<?php
 
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * Description of Chart_Model
 *
 * @author sharad singh(sharad23nov@gmail.com) 
 */

class Chart_model extends CI_Model {
 
    private $tablename = 'admissionstudent_registration';
    private $barchart = 'barchart';
 
    function __construct()
    {
        parent::__construct();
        $this->load->database();
    }
 
    public function get_chart_data($havedate) {
        $this->db->from('admissionstudent_registration');
        $this->db->where('asreg_verificationdate', $havedate);
        $query = $this->db->get();
        $rowcount = $query->num_rows();
        return $rowcount;
    }

    public function get_chart_data1() {
        $query = $this->db->get($this->barchart);
        $results['chart_data1'] = $query->result();

        $this->db->select_min('pdate');
        $this->db->limit(1);
        $query = $this->db->get($this->barchart);
        $results['min_date'] = $query->row()->pdate;

        $this->db->select_max('pdate');
        $this->db->limit(1);
        $query = $this->db->get($this->barchart);
        $results['max_date'] = $query->row()->pdate;

        return $results;
    }
    public function truncatetable($tbname){
        $this->db->from($tbname);
        $this->db->truncate();
    }

    public function feesdata($tbname){
        $this->db->from($tbname);
        $this->db->where('asfee_reconcilestatus', "Y");
        $query = $this->db->get();
        $rowcount = $query->num_rows();
        return $rowcount;


    } 
}
