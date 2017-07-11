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
	$this->load->model('common_model','commodel'); 
	$this->load->model('dependrop_model','depmodel'); 
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
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
                 $this->form_validation->set_rules('gm_gradepoint','Grade Point','trim|xss_clean|required|is_natural');
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
        	        $rflag=$this->commodel->insertrec('grade_master', $data);
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
        $is_exist = $this->commodel->isduplicate('grade_master','gm_gradename',$gm_gradename);
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
          $gradedflag=$this->commodel->deleterow('grade_master','gm_id', $id);
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
        $this->form_validation->set_rules('gm_gradepoint','Grade Point','trim|xss_clean|required|is_natural');
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

        $gradedflag=$this->commodel->updaterec('grade_master', $update_data,'gm_id', $data_eid);
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

     	/** This function display the semestere Rule
     	* @return type
     	*/
    	public function semesterrules() {
        	$this->result = $this->commodel->get_list('semester_rule');
        	$this->logger->write_logmessage("view"," View Semester Rule", "Semester Rule details...");
        	$this->logger->write_dblogmessage("view"," View Semester Rule", "Semester Rule record display successfully..." );
        	$this->load->view('setup2/semesterrules',$this->result);
    	}

	/**This function Delete the semester rule records
     	* @param type $id
     	* @return type
     	*/
        public function deletesemrule($id) {

        	$gradedflag=$this->commodel->deleterow('semester_rule','semcr_id', $id);
          	if(!$gradedflag)
          	{
            		$this->logger->write_message("error", "Error  in deleting semester rule " ."[semcr_id:" . $id . "]");
            		$this->logger->write_dbmessage("error", "Error  in deleting semester rule "." [semcr_id:" . $id . "]");
            		$this->session->set_flashdata('err_message', 'Error in Deleting semester rule - ', 'error');
            		redirect('setup2/semesterrules');
           		return;
          	}
        	else{
            		$this->logger->write_logmessage("delete", "Deleted semester rule " . "[semcr_id:" . $id . "] deleted successfully.. " );
            		$this->logger->write_dblogmessage("delete", "Deleted semseter rule" ." [semcr_id:" . $id . "] deleted successfully.. " );
            		$this->session->set_flashdata("success", 'Semester Rule Deleted successfully...' );
            		redirect('setup2/semesterrules');
        	}
        	$this->load->view('setup2/semesterrules',$data);
	}

	/** This function add the semester rule
     	* @return type
     	*/
	public function addsemrule()
	{
		$this->prgresult = $this->commodel->get_listspfic2('program','prg_name', '','','','prg_name');
        	if(isset($_POST['addsemrule'])) {
                	$this->form_validation->set_rules('semcr_prgid','Program Branch','trim|xss_clean|required|callback_issemruleExist');
                 	$this->form_validation->set_rules('semcr_semester','Semester','trim|xss_clean|required|is_natural');
                 	$this->form_validation->set_rules('semcr_mincredit','Minimum Credit','trim|xss_clean|is_natural|required');
                 	$this->form_validation->set_rules('semcr_maxcredit','Maximum Credit','trim|xss_clean|is_natural|required');
                 	$this->form_validation->set_rules('semcr_semcpi','CPI','trim|xss_clean|required');
                 	if($this->form_validation->run()==TRUE){
                 	//echo 'form-validated';
                        	$data = array(
                                	'semcr_prgid'=>$_POST['semcr_prgid'],
                                	'semcr_semester'=>$_POST['semcr_semester'],
                                	'semcr_mincredit'=>$_POST['semcr_mincredit'],
                                	'semcr_maxcredit'=>$_POST['semcr_maxcredit'],
                                	'semcr_semcpi'=>$_POST['semcr_semcpi'],
                                	'creatorid'=> $this->session->userdata('username'),
                                	'createdate'=>date('y-m-d'),
                                	'modifierid'=>$this->session->userdata('username'),
                                	'modifydate'=>date('y-m-d')
                         	);
                        	$rflag=$this->commodel->insertrec('semester_rule', $data);
                        	if (!$rflag)
                        	{
                                	$this->logger->write_logmessage("insert","Trying to add Semester rule", "Semester rule is not added ".$_POST['gm_gradename']);
                                	$this->logger->write_dblogmessage("insert","Trying to add Semester rule", "Semester rule is not added ".$_POST['gm_gradename']);
                                	$this->session->set_flashdata('err_message','Error in adding Semester rule setting - '  , 'error');
                                	redirect('setup2/addsemrule');
                        	}
                        	else{
                                	$this->logger->write_logmessage("insert","Add Semester rule Setting", "Semester rule for this".$_POST['gm_gradename']." added  successfully...");
                                	$this->logger->write_dblogmessage("insert","Add Semester rule Setting", "Semester rule for this ".$_POST['gm_gradename']."added  successfully...");
                                	$this->session->set_flashdata("success", "Semester rule for this program and branch added successfully...");
                                	redirect("setup2/semesterrules");
                        	}
                	}//close if vallidation
        	}//
        	$this->load->view('setup2/addsemrule');
    	}

    	/** This function check for duplicate semester rule
     	* @return type
    	*/
    	public function issemruleExist($semcr_prgid) {
        	$is_exist = $this->commodel->isduplicate('semester_rule','semcr_prgid',$semcr_prgid);
        	if ($is_exist)
        	{
            		$this->form_validation->set_message('issemruleExist', 'Semester rule for this program and branch is already exist.');
            		return false;
        	}
        	else {
            		return true;
        	}
    	}

    /**This function is used for update semester rule records
     * @param type $id semester rule id
     * @return type
     */
    public function editsemrule($id) {
        $this->db->from('semester_rule')->where('semcr_id', $id);
        $semrule_data_q = $this->db->get();
        if ($semrule_data_q->num_rows() < 1)
        {
            redirect('setup2/editsemrule');
        }
        $editsemrule_data = $semrule_data_q->row();

	/* Form fields */
        $data['semcr_programname'] = array(
                'name' => 'semcr_programname',
                'id' => 'semcr_programname',
                'maxlength' => '50',
                'size' => '40',
		'value' => $this->commodel->get_listspfic1('program','prg_name','prg_id',$editsemrule_data->semcr_prgid)->prg_name,
            'readonly' => 'readonly'
	);

	$data['semcr_branchname'] = array(
                'name' => 'semcr_branchname',
                'id' => 'semcr_branchname',
                'maxlength' => '50',
                'size' => '40',
		'value' => $this->commodel->get_listspfic1('program','prg_branch','prg_id',$editsemrule_data->semcr_prgid)->prg_branch,
		'readonly' => 'readonly'
	);

	$data['semcr_semester'] = array(
                'name' => 'semcr_semester',
                'id' => 'semcr_semester',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editsemrule_data->semcr_semester,
	);

	$data['semcr_mincredit'] = array(
                'name' => 'semcr_mincredit',
                'id' => 'semcr_mincredit',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editsemrule_data->semcr_mincredit,
	);

	$data['semcr_maxcredit'] = array(
                'name' => 'semcr_maxcredit',
                'id' => 'semcr_maxcredit',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editsemrule_data->semcr_maxcredit,
	);

        $data['semcr_semcpi'] = array(
           'name' => 'semcr_semcpi',
            'id' => 'semcr_semcpi',
           'maxlength' => '50',
           'size' => '40',
           'value' => $editsemrule_data->semcr_semcpi,
   	);

        $data['id'] = $id;
        /*Form Validation*/
        $this->form_validation->set_rules('semcr_semester','Semester','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('semcr_mincredit','Semester Minimum Credit','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('semcr_maxcredit','Semester Maximum credit','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('semcr_semcpi','Semester CPI','trim|xss_clean|required');

	/* Re-populating form */
        if ($_POST)
        {
            $data['semcr_prgid']['value'] = $editsemrule_data->semcr_prgid;
            $data['semcr_semester']['value'] = $this->input->post('semcr_semester', TRUE);
            $data['semcr_mincredit']['value'] = $this->input->post('semcr_mincredit', TRUE);
            $data['semcr_maxcredit']['value'] = $this->input->post('semcr_maxcredit', TRUE);
            $data['semcr_semcpi']['value'] = $this->input->post('semcr_semcpi', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup2/editsemrule', $data);
            return;
        }
        else{

            $data_esemester = $this->input->post('semcr_semester', TRUE);
            $data_emincredit = $this->input->post('semcr_mincredit', TRUE);
            $data_emaxcredit = $this->input->post('semcr_maxcredit', TRUE);
            $data_esemcpi = $this->input->post('semcr_semcpi', TRUE);
            $data_eid = $id;
            $logmessage = "";
            if($editsemrule_data->semcr_semester != $data_esemester)
                $logmessage = "Edit Semester " .$editsemrule_data->semcr_semester. " changed by " .$data_esemester;
            if($editsemrule_data->semcr_mincredit != $data_emincredit)
                $logmessage = "Edit Minimum Credit " .$editsemrule_data->semcr_mincredit. " changed by " .$data_emincredit;
            if($editsemrule_data->semcr_maxcredit != $data_emaxcredit)
                $logmessage = "Edit Maximum Credit  " .$editsemrule_data->semcr_maxcredit. " changed by " .$data_emaxcredit;
            if($editsemrule_data->semcr_semcpi != $data_esemcpi)
                $logmessage = "Edit Semester CPI " .$editsemrule_data->semcr_semcpi. " changed by " .$data_esemcpi;
		// insert data into semester rule archive table    
        	$insertdata= array(
                 'semcra_semcrid'=>$editsemrule_data->semcr_id,
                 'semcra_prgid'=>$editsemrule_data->semcr_prgid,
                 'semcra_semester'=>$editsemrule_data->semcr_semester,
                 'semcra_mincredit'=>$editsemrule_data->semcr_mincredit,
                 'semcra_maxcredit'=>$editsemrule_data->semcr_maxcredit,
                 'semcra_semcpi'=>$editsemrule_data->semcr_semcpi,
                 'semcra_ext1'=>$editsemrule_data->semcr_ext1,
                 'semcra_ext2'=>$editsemrule_data->semcr_ext2,
                 'creatorid'=>$this->session->userdata('username'),
                 'createdate'=>date('y-m-d'),
                 'modifierid'=>$this->session->userdata('username'),
                 'modifydate'=>date('y-m-d'),
        	);
            	$fmaflag=$this->commodel->insertrec('semester_rule_archive', $insertdata);
            	if(!$fmflag)
            	{
                      $this->logger->write_dblogmessage("error","Error in insert in  semester rule archive ", "Error in semester rule archive record insert". $logmessage );
            	}else{
                     $this->logger->write_dblogmessage("insert","Insert Fees master archive", "semester rule record inserted in semester rule archive successfully..". $logmessage );
            	}

            $update_data = array(
               'semcr_semester' => $data_esemester,
               'semcr_mincredit' => $data_emincredit,
	       'semcr_maxcredit' => $data_emaxcredit,
	       'semcr_semcpi' => $data_esemcpi,
	       'modifierid'=>$this->session->userdata('username'),
               'modifydate'=>date('y-m-d')
            );

        $semruledflag=$this->commodel->updaterec('semester_rule', $update_data,'semcr_id', $data_eid);
        if(!$semruledflag)
            {
                $this->logger->write_logmessage("error","Edit semester rule Setting error", "Edit semester rule Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit semester rule Setting error", "Edit semester rule Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating semester rule - ' . $logmessage . '.', 'error');
                $this->load->view('setup2/editsemrule', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit semester rule Setting", "Edit semster rule Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit semester rule Setting", "Edit semester rule Setting details. $logmessage ");
                $this->session->set_flashdata('success','Semester rule  detail updated successfully..');
                redirect('setup2/semesterrules');
                }
        }//else
        redirect('setup2/editsemrule');

    }//Edit Semster rule function end

	/*This function has been created for display the list of branch on the basis of program*/
        public function branchlist(){
		$pgid = $this->input->post('programname');   
		$this->depmodel->get_branchlist($pgid);
        } 
}


