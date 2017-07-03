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

    public function update_subject($updatedata,$acadyear,$semester,$studentid,$rid)
    {

        print_r($updatedata);
        echo $acadyear;
        echo $semester;
        echo $studentid;
        $this->db->trans_start();
        $this->db->where('sp_id',$rid);
//        $this->db->where('sp_acadyear',$acadyear);
//        $this->db->where('sp_semester',$semester);
        $this->db->update('student_program',$updatedata);

//        print_r($this->db->where('sp_smid',$studentid)->where('sp_acadyear',$acadyear)->where('sp_semester',$semester)->update('student_program',$updatedata));
/*        if(!$this->db->where('sp_smid',$studentid)->where('sp_acadyear',$acadyear)->where('sp_semester',$semester)->update('student_program',$updatedata)){
        //if(!$this->db->update('student_program',$updatedata))
        //{
            $this->db->trans_rollback();
            return false;
        }
        else 
        {
            $this->db->trans_complete();
            return true;
        }
*/
    }
    
    public function stud_sem_sub($studentid,$acadyear,$semester)
    {
        $this->db->from('student_program');
        $this->db->select('*');
        $this->db->where('sp_smid', $studentid);
        $this->db->where('sp_acadyear',$acadyear );
        $this->db->where('sp_semester',$semester );
        return $this->db->get();
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

