<?php defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Setup model
 * @author: Sharad Singh
 */

class Setup_model extends CI_Model
{
 
    function __construct() 
    {
        parent::__construct();
        $this->load->database();
    }

    /** add subject **/

    public function addsubjectrecords()
    {
        $this->load->model('common_model');
        $subname = $this->input->post('subname',TRUE);
        $subcode = $this->input->post('subcode');
        $subshort = $this->input->post('subshort');
        $subdesc = $this->input->post('subdesc');
        $subext1 =  $this->input->post('subext1');
        $subext2 = $this->input->post('subext2');
        /* check for duplicate record*/
        $result = $this->common_model->isduplicate('subject','sub_name',$subname); 
        if($result == 1)
        {
            $this->session->set_flashdata('error', $subname . ' already exist' );
            redirect('setup/subject');
        }       

        $data_sub = array('sub_name'=>$subname,'sub_code'=>$subcode,'sub_short'=>$subshort,'sub_desc'=>$subdesc,'sub_ext1'=>$subext1,'sub_ext2'=>$subext2);
        $this->db->trans_start();
        if(!$this->db->insert('subject',$data_sub))
        {
            $this->db->trans_rollback();
            $this->session->set_flashdata('Error in adding Program - ' . $subname . '.', 'error');
            $this->logger->write_dblogmessage("insert","Subject records added successfully with - ".$subname, ' by '.$username);
            $this->logger->write_logmessage("insert","Subject records added successfully with - ".$subname, ' by '.$username);
            redirect('setup/subject');
        }
        else
        {
            $this->db->trans_complete();
            $this->logger->write_logmessage("insert","Subject records added successfully with - ".$subname, ' by '.$username);
            $this->logger->write_dblogmessage("insert","Subject records added successfully with - ".$subname, ' by '.$username);
            $this->session->set_flashdata("success", $subname." - Subject added successfully");

            //$this->load->view('setup/subject');
            redirect('setup/viewsubject');
        }
    }
    /* Get all subject records */
    public function viewsubject()
    {
        $this->db->select("*");
        $this->db->from('subject');
        $subjectlist = $this->db->get();
        return $subjectlist->result(); 
    }
    
    /* Get subject record */    

    public function getsubject_byid($sub_id)
    {
        $condition = "sub_id =" . "'" . $sub_id . "'";
        $this->db->select("*");
        $this->db->from('subject');
        $this->db->where($condition);
        $query = $this->db->get();
        
        if ($query->num_rows() == 1) 
        {
            return $query->result();
        } 
        else 
        {
            return false;
        }        
    }    
    
    /* Update subject record */

    public function update_subject_byid($subid,$update_subdata)
    {
        if(!$this->db->where('sub_id', $subid)->update('subject', $update_subdata))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
