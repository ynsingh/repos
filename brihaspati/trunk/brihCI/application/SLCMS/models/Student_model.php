<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Student Model
 * @author: Sharad Singh
 * @author: Sumit Saxena (sumitsesaxena@gmail.com)
 * @author: Manorama Pal (palseema30@gmail.com)
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
			$this->db->select('DISTINCT(course_name)');
			$this->db->from('admissionmeritlist');
			$query = $this->db->get();
			$results = $query->result();
			return $results; 
		}

    function __destruct() {
        $this->db->close();
    }
    
    public function getStudentsWhereLike($field, $search,$data)
    {
        $this->db->select('student_program.sp_smid','student_program.sp_deptid','student_master.sm_fname','student_master.sm_mname','student_master.sm_lname','student_master.sm_rollno','student_master.sm_enrollmentno','student_master.sm_uid','student_master.sm_email','student_master.sm_mobile');
        //$this->db->select('student_program.sp_smid','student_program.sp_deptid','student_program.sp_subid1','student_program.sp_subid2','student_program.sp_subid3','student_program.sp_subid4','student_program.sp_subid5','student_program.sp_subid6','student_program.sp_subid7','student_program.sp_subid8','student_program.sp_subid9','student_program.sp_subid10','student_master.sm_fname','student_master.sm_mname','student_master.sm_lname','student_master.sm_rollno','student_master.sm_enrollmentno','student_master.sm_uid','student_master.sm_email','student_master.sm_mobile');       
        $this->db->from('student_program')->where($data)->where($field.' LIKE', '%' . $search . '%')->order_by('student_master.sm_fname','asc');
        $this->db->join('student_master','student_master.sm_id = student_program.sp_smid','LEFT');
        $query=$this->db->get();
        return $query->result();
    }
}

