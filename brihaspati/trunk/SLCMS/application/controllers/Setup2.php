<?php

/* 
 * @name Setup2.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Setup2 extends CI_Controller
{
    function __construct() {
        parent::__construct();
	$this->load->model('common_model'); 
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
        
//        $this->load->helper('url');
        $this->grademaster();
        
    }
    /** This function display the grade master
     * @return type
     */
    
    public function grademaster() {
        
	$this->result = $this->common_model->get_list('grade_master');
	$this->logger->write_logmessage("view"," View Master Grade", "Master Grade details...");
	$this->logger->write_dblogmessage("view"," View Master Grade", "Master Grade record display successfully..." );
        $this->load->view('setup2/grademaster',$this->result);
    }

    /** This function add the grade master
     * @return type
     */
    public function addgrade()
    {
        if(isset($_POST['addgrade'])) {
                 $this->form_validation->set_rules('gm_gradename','Grade Name','trim|xss_clean|required|callback_isgradeExist');
                 $this->form_validation->set_rules('gm_gradepoint','Grade Point','trim|xss_clean|required');
                 $this->form_validation->set_rules('gm_short','Grade short','trim|xss_clean');
                 $this->form_validation->set_rules('gm_desc','Grade Desc','trim|xss_clean');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 	$data = array(
                		'gm_gradename'=>strtoupper($_POST['gm_gradename']),
	                	'gm_gradepoint'=>$_POST['gm_gradepoint'],
				'gm_short'=>ucwords(strtolower($_POST['gm_short'])),
				'gm_desc'=>$_POST['gm_desc'],
				'creatorid'=> $this->session->userdata('username'),
	                    	'createdate'=>date('y-m-d'),
        	            	'modifierid'=>$this->session->userdata('username'),
                	    	'modifydate'=>date('y-m-d')
	                 );
        	        $rflag=$this->common_model->insertrec('grade_master', $data);
                	if (!$rflag)
                	{
                    		$this->logger->write_logmessage("insert","Trying to add grade", "Grade is not added ".$_POST['gm_gradename']);
                    		$this->logger->write_dblogmessage("insert","Trying to add garde", "Grade is not added ".$_POST['gm_gradename']);
                    		$this->session->set_flashdata('err_message','Error in adding grade setting - '  , 'error');
                    		redirect('setup2/addgrade');
                	}
                	else{
                    		$this->logger->write_logmessage("insert","Add grade Setting", "Grade".$_POST['gm_gradename']." added  successfully...");
                    		$this->logger->write_dblogmessage("insert","Add grade Setting", "Grade ".$_POST['gm_gradename']."added  successfully...");
                    		$this->session->set_flashdata("success", "Grade add successfully...");
                    		redirect("setup2/grademaster");
                	}
            	}//close if vallidation
        }//
        $this->load->view('setup2/addgrade');
    }
 
    /** This function check for duplicate grade
     * @return type
    */

    public function isgradeExist($gm_gradename) {

        $is_exist = $this->common_model->isduplicate('grade_master','gm_gradename',$gm_gradename);
        if ($is_exist)
        {
            $this->form_validation->set_message('isgradeExist', 'Grade is already exist.');
            return false;
        }
        else {
            return true;
        }
    }
 

    /**This function Delete the grade records
     * @param type $id
     * @return type
     */

         public function deletegrade($id) {

          $gradedflag=$this->common_model->deleterow('grade_master','gm_id', $id);
          if(!$gradedflag)
          {
            $this->logger->write_message("error", "Error  in deleting grade " ."[gm_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting grade "." [gm_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in Deleting grade - ', 'error');
            redirect('setup2/grademaster');
           return;
          }
        else{
            $this->logger->write_logmessage("delete", "Deleted   grade " . "[gm_id:" . $id . "] deleted successfully.. " );
            $this->logger->write_dblogmessage("delete", "Deleted grade" ." [gm_id:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'Grade Deleted successfully...' );
            redirect('setup2/grademaster');
        }
        $this->load->view('setup2/grademaster',$data);

}
 
    /**This function is used for update grade records
     * @param type $id grade master id
     * @return type
     */

    public function editgrade($id) {

        $this->db->from('grade_master')->where('gm_id', $id);
        $gm_data_q = $this->db->get();
        if ($gm_data_q->num_rows() < 1)
        {
            redirect('setup2/editgrade');
        }
        $editgm_data = $gm_data_q->row();

	/* Form fields */

        $data['gm_gradename'] = array(
                'name' => 'gm_gradename',
                'id' => 'gm_gradename',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editgm_data->gm_gradename,

	);
	$data['gm_gradepoint'] = array(
                'name' => 'gm_gradepoint',
                'id' => 'gm_gradepoint',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editgm_data->gm_gradepoint,

                );
	$data['gm_short'] = array(
                'name' => 'gm_short',
                'id' => 'gm_short',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editgm_data->gm_short,

                );

        $data['gm_desc'] = array(
           'name' => 'gm_desc',
            'id' => 'gm_desc',
           'maxlength' => '50',
           'size' => '40',
           'value' => $editgm_data->gm_desc,

        );
        $data['id'] = $id;
        /*Form Validation*/
        $this->form_validation->set_rules('gm_gradename','Grade name','trim|xss_clean|required|callback_isgradeExist');
        $this->form_validation->set_rules('gm_gradepoint','Grade Point','trim|xss_clean|required');
        $this->form_validation->set_rules('gm_short','Grade Short','trim|xss_clean');
        $this->form_validation->set_rules('gm_desc','Grade Description','trim|xss_clean');

	/* Re-populating form */
        if ($_POST)
        {
            $data['gm_gradename']['value'] = $this->input->post('gm_gradename', TRUE);
            $data['gm_gradepoint']['value'] = $this->input->post('gm_gradepoint', TRUE);
            $data['gm_short']['value'] = $this->input->post('gm_short', TRUE);
            $data['gm_desc']['value'] = $this->input->post('gm_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup2/editgrade', $data);
            return;
        }
        else{

            $data_egrade = strtoupper($this->input->post('gm_gradename', TRUE));
            $data_egradepoint = $this->input->post('gm_gradepoint', TRUE);
            $data_egradeshort = $this->input->post('gm_short', TRUE);
            $data_egradedesc = $this->input->post('gm_desc', TRUE);
            $data_eid = $id;
            $logmessage = "";
            if($editgm_data->gm_gradename != $data_egrade)
                $logmessage = "Edit Grade Name " .$editgm_data->gm_gradename. " changed by " .$data_egrade;
            if($editgm_data->gm_gradepoint != $data_egradepoint)
                $logmessage = "Edit grade point " .$editgm_data->gm_gradepoint. " changed by " .$data_egradepoint;
            if($editgm_data->gm_short != $data_egradeshort)
                $logmessage = "Edit grade short  " .$editgm_data->gm_short. " changed by " .$data_egradeshort;
            if($editgm_data->gm_desc != $data_egradedesc)
                $logmessage = "Edit grade desc " .$editgm_data->gm_desc. " changed by " .$data_egradedesc;

            $update_data = array(
               'gm_gradename' => $data_egrade,
               'gm_gradepoint' => $data_egradepoint,
               'gm_short' => $data_egradeshort,
	       'gm_desc' => $data_egradedesc,
	       'modifierid'=>$this->session->userdata('username'),
               'modifydate'=>date('y-m-d')
            );

        $gradedflag=$this->common_model->updaterec('grade_master', $update_data,'gm_id', $data_eid);
        if(!$gradedflag)
            {
                $this->logger->write_logmessage("error","Edit grade Setting error", "Edit grade Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit grade Setting error", "Edit grade Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating garde - ' . $logmessage . '.', 'error');
                $this->load->view('setup2/editgrade', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit grade Setting", "Edit grade Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit grade Setting", "Edit grade Setting details. $logmessage ");
                $this->session->set_flashdata('success','Grade  detail updated successfully..');
                redirect('setup2/grademaster');
                }
        }//else
        redirect('setup2/editgrade');

    }//Edit Grade function end
 
}


