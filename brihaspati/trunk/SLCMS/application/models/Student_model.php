<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Student Model
 * @author: Sharad Singh
 * @author: Sumit Saxena (sumitsesaxena@gmail.com)
 */

class Student_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
        $this->load->database();
    }

    public function get_student_program($stud_id)
    {
        $this->db->from('student_program')->order_by('sp_id', 'des');
        $this->db->where('sp_smid',$stud_id);
        return $this->db->get()->result();
    }

    public function get_student_fee($stud_spid)
    {
        $this->db->from('student_fees')->order_by('sfee_id', 'des');
        $this->db->where('sfee_spid',$stud_spid);
        $query = $this->db->get()->result();
        return $query;
/*        if ($query->num_rows() > 0) {
            return $query->result();
        } else {
            return false;
        }
*/            
    }

    public function get_semester_no($studid,$acadyear)
    {
        $this->db->from('student_program');
        $this->db->where('sp_smid',$studid);
        $this->db->where('sp_acadyear',$acadyear);
        $query = $this->db->get()->result();
        //print_r(sizeof($query));
        return $query;
    }

	
    function showCourse(){
			$this->db->select('course_name');
			$this->db->group_by('course_name');
			$this->db->from('admissionmeritlist');
			$query = $this->db->get();
			$results = $query->result();
			return $results; 
		}


    function __destruct() {
        $this->db->close();
    }
}

