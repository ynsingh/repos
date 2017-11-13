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
	$this->load->model('login_model','logmodel');
	$this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

   
    public function index () {
          $this->grademaster();
    }

    /** This function display the grade master
     * @return type
    */
    public function grademaster() {
	$this->result = $this->commodel->get_list('grade_master');
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
	$gm_data_q=$this->commodel->get_listrow('grade_master','gm_id', $id);
        if ($gm_data_q->num_rows() < 1)
        {
            redirect('setup2/grademaster');
        }

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
	$gm_data_q=$this->commodel->get_listrow('grade_master','gm_id', $id);
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
                $this->logger->write_logmessage("update","Edit grade Setting by".$this->session->userdata('username') , "Edit grade Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit grade Setting by".$this->session->userdata('username') , "Edit grade Setting details. $logmessage ");
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

                $semcr_data_q=$this->commodel->get_listrow('semester_rule','semcr_id', $id);
            if ($semcr_data_q->num_rows()  < 1)
        {
            redirect('setup2/semestersrules');
        }
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
                	$this->form_validation->set_rules('semcr_prgid','Program Branch','trim|xss_clean|required');
                 	$this->form_validation->set_rules('semcr_semester','Semester','trim|xss_clean|required|is_natural');
                 	$this->form_validation->set_rules('semcr_mincredit','Minimum Credit','trim|xss_clean|is_natural|required');
                 	$this->form_validation->set_rules('semcr_maxcredit','Maximum Credit','trim|xss_clean|is_natural|required');
                 	$this->form_validation->set_rules('semcr_semcpi','CPI','trim|xss_clean|required');
                 	if($this->form_validation->run()==TRUE){
				//echo 'form-validated';
				$whdata = array('semcr_prgid' => $_POST['semcr_prgid'], 'semcr_semester' => $_POST['semcr_semester']);
				$is_exist = $this->commodel->isduplicatemore('semester_rule',$whdata);
				if(!$is_exist){
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
				}
                                else{
                                        $this->session->set_flashdata("success", "Semester rule for this program already exist.");
                                       redirect('setup2/addsemrule');
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
        $semcr_data_q=$this->commodel->get_listrow('semester_rule','semcr_id', $id);
        if ($semcr_data_q->num_rows() < 1)
        {
            redirect('setup2/editsemrule');
        }
        $editsemrule_data = $semcr_data_q->row();

	/* Form fields */
        $prgname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$editsemrule_data->semcr_prgid)->prg_name;
        $prgbranchm=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$editsemrule_data->semcr_prgid)->prg_branch;
        $data['semcr_programname'] = array(
                'name' => 'semcr_programname',
                'id' => 'semcr_programname',
                'maxlength' => '50',
                'size' => '40',
		'value' => $prgname,
            'readonly' => 'readonly'
	);

	$data['semcr_branchname'] = array(
                'name' => 'semcr_branchname',
                'id' => 'semcr_branchname',
                'maxlength' => '50',
                'size' => '40',
		'value' => $prgbranchm,
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
	//insert data into semester rule archive table    
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
                $this->logger->write_logmessage("update","Edit semester rule Setting by".$this->session->userdata('username') , "Edit semster rule Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit semester rule Setting by".$this->session->userdata('username') , "Edit semester rule Setting details. $logmessage ");
                $this->session->set_flashdata('success',$prgname .' and ' . $prgbranchm .' Semester rule  detail updated successfully..');
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
/** This function display the degree rules
  * @param type  
  * @return type
  */
    
     public function degreerules() {
        $this->result = $this->commodel->get_list('degree_rule');
        $this->logger->write_logmessage("view"," View Degree rules", "Degree rules details...");
        $this->logger->write_dblogmessage("view"," View Degree rules" , "Degree rules record display successfully..." );
        $this->load->view('setup2/degreerules',$this->result);
       }

   /** This function add the degree rules
     * @return type
     */

    public function adddegreerules()
    {       
                 $this->prgresult = $this->commodel->get_listspfic2('program','prg_name', '','','','prg_name');
               
        if(isset($_POST['adddegreerules'])) {
                 $this->form_validation->set_rules('dr_prgid','Choose Branch','trim|xss_clean|required|callback_isbranchExist');
                 $this->form_validation->set_rules('dr_mincredit','minimum credit','trim|xss_clean|required|is_natural');
                 $this->form_validation->set_rules('dr_minsubcredit','minimum subject credit','trim|xss_clean|required|is_natural');
                 $this->form_validation->set_rules('dr_minthesiscredit','minimum thesis credit','trim|xss_clean|required|is_natural');
                 $this->form_validation->set_rules('dr_minsub','minimum subject','trim|xss_clean|required|integer');
                 $this->form_validation->set_rules('dr_minsemester','minimum semester','trim|xss_clean|required|integer');
                 $this->form_validation->set_rules('dr_mincpi','minimum cpi','trim|xss_clean|required|integer');
                 $this->form_validation->set_rules('dr_maxcredit','maximum credit','trim|xss_clean|required|is_natural');
                 $this->form_validation->set_rules('dr_maxsemeter','maximum semeter','trim|xss_clean|required|integer');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                        $data = array(
                                'dr_prgid'=>$_POST['dr_prgid'],
                                'dr_mincredit'=>$_POST['dr_mincredit'],
                                'dr_minsubcredit'=>$_POST['dr_minsubcredit'],
                                'dr_minthesiscredit'=>$_POST['dr_minthesiscredit'],
                                'dr_minsub'=>$_POST['dr_minsub'],
                                'dr_minsemester'=>$_POST['dr_minsemester'],
                                'dr_mincpi'=>$_POST['dr_mincpi'],
                                'dr_maxcredit'=>$_POST['dr_maxcredit'],
                                'dr_maxsemeter'=>$_POST['dr_maxsemeter'],
                                'creatorid'=> $this->session->userdata('username'),
                                'createdate'=>date('y-m-d'),
                                'modifierid'=>$this->session->userdata('username'),
                                'modifydate'=>date('y-m-d')
                        );
			$dr_prgid=$_POST['dr_prgid'];
		       $prgname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$dr_prgid)->prg_name;
                       $prgbranchn= $this->commodel->get_listspfic1('program','prg_branch','prg_id',$dr_prgid)->prg_branch;
                       $rflag=$this->commodel->insertrec('degree_rule', $data);
                       if (!$rflag)
                       {
                                $this->logger->write_logmessage("insert","Trying to add programme " . $prgname .'('. $prgbranchn .')', "programme " . $prgname .'('. $prgbranchn .") is not added ".$_POST['dr_prgid']);
                                $this->logger->write_dblogmessage("insert","Trying to add programme". $prgname .'('. $prgbranchn .')', "programme " . $prgname .'('. $prgbranchn .") is not added ".$_POST['dr_prgid']);
                                $this->session->set_flashdata('err_message','Error in adding ' . $prgname .'('. $prgbranchn .')  setting - '  , 'error');
                                redirect('setup2/adddegreerules');
                       }
                        else{
                                $this->logger->write_logmessage("insert","Add programme Setting", "progrmme ". $prgname .'('. $prgbranchn .") added  successfully...");
                                $this->logger->write_dblogmessage("insert","Add programme Setting", "programme ". $prgname .'('. $prgbranchn .") added  successfully...");
                                $this->session->set_flashdata("success","Degree rule for " . $prgname .'('. $prgbranchn .")  added successfully...");
                                redirect("setup2/degreerules");
                        }
                }//close if vallidation
        }//
        $this->load->view('setup2/adddegreerules');
       
         }

   /** This function check for duplicate degree rule
     * @return type
     */

    public function isbranchExist($dr_prgid) {
        $is_exist = $this->commodel->isduplicate('degree_rule','dr_prgid',$dr_prgid);
        if ($is_exist)
        {
            $prgname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$dr_prgid)->prg_name;
            $prgbranchn= $this->commodel->get_listspfic1('program','prg_branch','prg_id',$dr_prgid)->prg_branch;
            $this->form_validation->set_message('isbranchExist','degree rule for this '.$prgname .'  program and '.$prgbranchn. ' branch is already exist.');
    	        return false;
            }
            else {
                 return true;
            }
        }


    /**This function Delete the degree rule records
     * @param type $id
     * @return type
     */

    public function deletedegreerule($id) {
          $dr_data_q=$this->commodel->get_listrow('degree_rule','dr_id', $id);
        if ($dr_data_q->num_rows() < 1)
        {
            redirect('setup2/degreerules');
        }
        $gradedflag=$this->commodel->deleterow('degree_rule','dr_id', $id);
        if(!$gradedflag)
          {
            $this->logger->write_message("error", "Error  in deleting degree rule " ."[dr_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting degree rule "." [dr_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in Deleting degree rule - ', 'error');
            redirect('setup2/degreerules');
           return;
          }
        else{
            $this->logger->write_logmessage("delete", "Deleted   degree rule " . "[dr_prgid:" . $id . "] deleted successfully.. " );
            $this->logger->write_dblogmessage("delete", "Deleted degree rule" ." [dr_prgid:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'degree rule Deleted successfully...' );
            redirect('setup2/degreerules');
        }
        $this->load->view('setup2/degreerules',$data);

    }

     /**This function is used for update degreerules records
       * @param type $id degree rule id
       * @return type
       */

    public function editdegreerule($id) {
        $dr_data_q=$this->commodel->get_listrow('degree_rule','dr_id', $id);
        if ($dr_data_q->num_rows() < 1)
        {
            redirect('setup2/degreerules');
        }
        $editdegreerule_data = $dr_data_q->row();

               /* Form fields */
        $prgname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$editdegreerule_data->dr_prgid)->prg_name;
        $prgbranchn= $this->commodel->get_listspfic1('program','prg_branch','prg_id',$editdegreerule_data->dr_prgid)->prg_branch;
        $data['dr_programname'] = array(
                'name' => 'dr_programname',
                'id' => 'dr_programname',
                'maxlength' => '50',
                'size' => '40',
                'value' => $prgname,
            'readonly' => 'readonly'
        );


         $data['dr_branchname'] = array(
               'name' => 'dr_branchname',
               'id' => 'dr_branchname',
               'maxlength' => '50',
               'size' => '40',
               'value' => $prgbranchn,
               'readonly' => 'readonly'
        );

                
        $data['dr_mincredit'] = array(
                'name' => 'dr_mincredit',
                'id'=>'dr_mincredit',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_mincredit,

                );

        $data['dr_minsubcredit'] = array(
                'name' => 'dr_minsubcredit',
                'id' => 'dr_minsubcredit',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_minsubcredit,

                );

        $data['dr_minthesiscredit'] = array(
                'name' => 'dr_minthesiscredit',
                'id' => 'dr_minthesiscredit',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_minthesiscredit,
               
                 );

        $data['dr_minsub'] = array(
                'name' => 'dr_minsub',
                'id' => 'dr_minsub',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_minsub,

                );

        $data['dr_minsemester'] = array(
                'name' => 'dr_minsemester',
                'id' => 'dr_minsemester',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_minsemester,

                );

        $data['dr_mincpi'] = array(
                'name' => 'dr_mincpi',
                'id' => 'dr_mincpi',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_mincpi,

                );

       $data['dr_maxcredit'] = array(
                'name' => 'dr_maxcredit',
                'id' => 'dr_maxcredit',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_maxcredit,
               
                );

       $data['dr_maxsemeter'] = array(
                'name' => 'dr_maxsemeter',
                'id' => 'dr_maxsemeter',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdegreerule_data->dr_maxsemeter,

                );


        $data['id'] = $id;
        /*Form Validation*/
        $this->form_validation->set_rules('dr_mincredit','minimum credit','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('dr_minsubcredit','minimum subject credit','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('dr_minthesiscredit','minimum thesis credit','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('dr_minsub','minimum subject','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('dr_minsemester','minimum semester','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('dr_mincpi','minimum cpi','trim|xss_clean|required|decimal');
        $this->form_validation->set_rules('dr_maxcredit','maximum credit','trim|xss_clean|required|is_natural');
        $this->form_validation->set_rules('dr_maxsemeter','maximum semeter','trim|xss_clean|required|is_natural');


       /* Re-populating form */

        if ($_POST)
        {
            $data['dr_prgid']['value'] = $editdegreerule_data->dr_prgid;
            $data['dr_mincredit']['value'] = $this->input->post('dr_mincredit', TRUE);
            $data['dr_minsubcredit']['value'] = $this->input->post('dr_minsubcredit', TRUE);
            $data['dr_minthesiscredit']['value'] = $this->input->post('dr_minthesiscredit', TRUE);
            $data['dr_minsub']['value'] = $this->input->post('dr_minsub', TRUE);
            $data['dr_minsemester']['value'] = $this->input->post('dr_minsemester', TRUE);
            $data['dr_mincpi']['value'] = $this->input->post('dr_mincpi', TRUE);
            $data['dr_maxcredit']['value'] = $this->input->post('dr_maxcredit', TRUE);
            $data['dr_maxsemeter']['value'] = $this->input->post('dr_maxsemeter', TRUE);


        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup2/editdegreerules', $data);
            return;

       }
        else{

            $data_eminimumcredit = $this->input->post('dr_mincredit', TRUE);
            $data_eminimumsubjectcredit = $this->input->post('dr_minsubcredit', TRUE);
            $data_eminimumthesiscredit = $this->input->post('dr_minthesiscredit', TRUE);
            $data_eminimumsubject = $this->input->post('dr_minsub', TRUE);
            $data_eminimumsemester = $this->input->post('dr_minsemester', TRUE);
            $data_eminimumcpi = $this->input->post('dr_mincpi', TRUE);
            $data_emaximumcredit = $this->input->post('dr_maxcredit', TRUE);
            $data_emaximumsemeter = $this->input->post('dr_maxsemeter', TRUE);
            $data_eid = $id;
            $logmessage = $prgname ."and ". $prgbranchn ."changed values";
            if($editdegreerule_data->dr_mincredit != $data_eminimumcredit)
                $logmessage = "Edit minimum credit  " .$editdegreerule_data->dr_mincredit. " changed by " .$data_eminimumcredit;
            if($editdegreerule_data->dr_minsubcredit != $data_eminimumsubjectcredit)
                $logmessage = "Edit minimum subject credit " .$editdegreerule_data->dr_minsubcredit. " changed by " .$data_eminimumsubjectcredit;
            if($editdegreerule_data->dr_minthesiscredit != $data_eminimumthesiscredit)
                $logmessage = "Edit minimum thesis credit  " .$editdegreerule_data->dr_minthesiscredit. " changed by " .$data_eminimumthesiscredit;
            if($editdegreerule_data->dr_minsub != $data_eminimumsubject)
                $logmessage = "Edit minimum subject  " .$editdegreerule_data->dr_minsub. " changed by " .$data_eminimumsubject;           
            if($editdegreerule_data->dr_minsemester != $data_eminimumsemester)
                $logmessage = "Edit minimum semester  " .$editdegreerule_data->dr_minsemester. " changed by " .$data_eminimumsemester;
            if($editdegreerule_data->dr_mincpi != $data_eminimumcpi)
                $logmessage = "Edit minimum cpi  " .$editdegreerule_data->dr_mincpi. " changed by " .$data_eminimumcpi;
            if($editdegreerule_data->dr_maxcredit != $data_emaximumcredit)
                $logmessage = "Edit maximum credit  " .$editdegreerule_data->dr_maxcredit. " changed by " .$data_emaximumcredit;
             if($editdegreerule_data->dr_maxsemeter != $data_emaximumsemeter)
                $logmessage = "Edit maximum semeter  " .$editdegreerule_data->dr_maxsemeter. " changed by " .$data_emaximumsemeter;        
            // insert data into degree rule archive table    
  //              $insertdata= array(
    //             'dr_prgid'=>$editdegreerule_data->dr_prgid,
      //           'dr_mincredit'=>$editdegreerule_data->dr_mincredit,
        //         'dr_minsubcredit'=>$editdegreerule_data->dr_minsubcredit,
          //       'dr_minsub'=>$editdegreerule_data->dr_minsub,
            //     'dr_mincpi'=>$editdegreerule_data->dr_mincpi,
              //   'dr_maxcredit'=>$editdegreerule_data->dr_maxcredit,
                // 'dr_maxsemeter'=>$editdegreerule_data->dr_maxsemeter,
       //          'creatorid'=>$this->session->userdata('username'),
         //        'createdate'=>date('y-m-d'),
           //      'modifierid'=>$this->session->userdata('username'),
            //     'modifydate'=>date('y-m-d'),
             //   );
               // $fmaflag=$this->commodel->insertrec('degree_rule', $insertdata);
               // if(!$fmflag)
      //          {
        //              $this->logger->write_dblogmessage("error","Error in insert in  degree rule archive ", "Error in degree rule archive record insert". $logmessage );
          //      }else{
            //         $this->logger->write_dblogmessage("insert","Insert Fees master archive", "degree rule record inserted in degree rule archive successfully..". $logmessage );
              //  } 
 
            $update_data = array(  
               'dr_mincredit' => $data_eminimumcredit,
               'dr_minsubcredit' => $data_eminimumsubjectcredit,
               'dr_minthesiscredit' => $data_eminimumthesiscredit,
               'dr_minsub' => $data_eminimumsubject,
               'dr_minsemester' => $data_eminimumsemester,
               'dr_mincpi' => $data_eminimumcpi,
               'dr_maxcredit' => $data_emaximumcredit,
               'dr_maxsemeter' => $data_emaximumsemeter,
               'modifierid'=>$this->session->userdata('username'),
               'modifydate'=>date('y-m-d')
            );

        $degreeruledflag=$this->commodel->updaterec('degree_rule', $update_data,'dr_id', $data_eid);
        if(!$degreeruledflag)
            {
                $this->logger->write_logmessage("error","Edit degree rule  Setting error", "Edit degree rule  Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit degree rule Setting error", "Edit degree rule Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating degree rule for  '.$prgname .' and '.$prgbranchn . $logmessage . '.', 'error');
                $this->load->view('setup2/editdegreerules', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit degree rule  Setting by".$this->session->userdata('username'), "Edit degree rule Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit degree rule Setting by".$this->session->userdata('username'), "Edit degree rule Setting details. $logmessage ");
                $this->session->set_flashdata('success',$prgname .' and '.$prgbranchn.' degree rule  detail updated successfully... ');
                redirect('setup2/degreerules');
                }
 }//else
 redirect('setup2/editdegreerules');

 }// edit degree rule function end

               /*   -------------***********DESIGNATION STARTS****************--------------------   */

/** This function display the designation                                                                                                                                                        
  * @param type  
  * @return type
  */

     public function designation() {
        $this->result = $this->commodel->get_list('designation');
        $this->logger->write_logmessage("view"," View Designation ", "Designation details...");
        $this->logger->write_dblogmessage("view"," View Designation" , "Designation record display successfully..." );
        $this->load->view('setup2/designation',$this->result);
       }

   /** This function add the designation
     * @return type
     */                 

    public function adddesignation()
    {
	    $this->payresult=$this->sismodel->get_list('salary_grade_master');

         if(isset($_POST['adddesignation'])) {
                 $this->form_validation->set_rules('desig_code','Designation Code','trim|xss_clean|callback_isCodeExist');
                 $this->form_validation->set_rules('tnt','Designation Type','trim|xss_clean|required');
                 $this->form_validation->set_rules('grouppost','Designation Subtype','trim|xss_clean|required');
                 $this->form_validation->set_rules('desig_payscale','Designation Payscale','trim|xss_clean|required');
                 $this->form_validation->set_rules('desig_name','Designation Name','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('desig_group','Designation Group','trim|xss_clean|required');
                 $this->form_validation->set_rules('desig_short','Designation Short','trim|xss_clean|required');
                 $this->form_validation->set_rules('desig_desc','Designation Description','trim|xss_clean');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                        $data = array(
                                'desig_code'=>$_POST['desig_code'],
                                'desig_type'=>$_POST['tnt'],
                                'desig_subtype'=>$_POST['grouppost'],
                                'desig_payscale'=>$_POST['desig_payscale'],
                                'desig_name'=>ucfirst(strtolower($_POST['desig_name'])),
                                'desig_group'=>$_POST['desig_group'],
                                'desig_short'=>$_POST['desig_short'],
                                'desig_desc'=>$_POST['desig_desc'],
                           );
                           $rflag=$this->commodel->insertrec('designation', $data);
                           if (!$rflag)
                        {
                                $this->logger->write_logmessage("insert","Trying to designation", "designation is not added ".$_POST['desig_name']);
                                $this->logger->write_dblogmessage("insert","Trying to designation", "designation is not added ".$_POST['desig_name']);
                                $this->session->set_flashdata('err_message','Error in adding designation setting - '  , 'error');
                                redirect('setup2/adddesignation');
                        }
                        else{
				$this->logger->write_logmessage("insert","Add designation Setting", "Designation".$_POST['desig_name']." added  successfully...");
				 $this->logger->write_dblogmessage("insert","Add designation Setting", "Designation ".$_POST['desig_name']."added  successfully...");
                                $this->session->set_flashdata("success", "Designation add successfully...");
                                redirect("setup2/designation");
                        }
                }//close if vallidation
        }//

        $this->load->view('setup2/adddesignation');
    }

   /** This function check for duplicate designation
     * @return type
     */
    public function isnameExist($desig_name) {
        $is_exist = $this->commodel->isduplicate('designation','desig_name',$desig_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isnameExist', 'Designation is already exist.');
            return false;
        }
        else {
            return true;
        }
    }

    public function isCodeExist($desig_code) {

        $is_exist = $this->commodel->isduplicate('designation','desig_code',$desig_code);
        if ($is_exist)
        {
            $this->form_validation->set_message('isCodeExist', 'Code is already exist.');
            return false;
        }
        else {
            return true;
        }
    }

    /**This function Delete the designation records
     * @param type $id
     * @return type
     */
     public function deletedesignation($id) {
        $desig_data_q=$this->commodel->get_listrow('designation','desig_id ', $id);
        if ($desig_data_q->num_rows() < 1)
        {
            redirect('setup2/designation');
        }

          $gradedflag=$this->commodel->deleterow('designation','desig_id', $id);
          if(!$gradedflag)
          {
                $this->logger->write_message("error", "Error  in deleting designation " ."[desig_id:" . $id . "]");
                $this->logger->write_dbmessage("error", "Error  in deleting designation "." [desig_id:" . $id . "]");
                $this->session->set_flashdata('err_message', 'Error in Deleting designation - ', 'error');
                redirect('setup2/designation');
                return;
          }
          else{
                $this->logger->write_logmessage("delete", "Deleted   designation " . "[desig_id:" . $id . "] deleted successfully.. " );
                $this->logger->write_dblogmessage("delete", "Deleted designation" ." [desig_id:" . $id . "] deleted successfully.. " );
                $this->session->set_flashdata("success", 'Designation Deleted successfully...' );
                redirect('setup2/designation');
        }
        $this->load->view('setup2/designation',$data);
    }


    /**This function is used for update designation records
     * @param type $id designation id
     * @return type
     */
    public function editdesignation($desig_id) {
	$this->payresult=$this->sismodel->get_list('salary_grade_master');
        $desig_data_q=$this->commodel->get_listrow('designation','desig_id', $desig_id);
        if ($desig_data_q->num_rows() < 1)
        {
            redirect('setup2/editdesignation');
        }
        $editdesig_data = $desig_data_q->row();

        /* Form fields */
        
         $data['desig_code'] = array(
                'name' => 'desig_code',
                'id' => 'desig_code',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_code,
               'readonly' => 'readonly'

                );
	$data['desig_type'] = array(
                'name' => 'desig_type',
                'id' => 'desig_type',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_type,

                );
        $data['desig_subtype'] = array(
                'name' => 'desig_subtype',
                'id' => 'desig_subtype',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_subtype,

                );
        $data['desig_payscale'] = array(
                'name' => 'desig_payscale',
                'id' => 'desig_payscale',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_payscale,
                );

        $data['desig_name'] = array(
                'name' => 'desig_name',
                'id' => 'desig_name',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_name,
        	'readonly' => 'readonly'
        );

         $data['desig_group'] = array(
                'name' => 'desig_group',
                'id' => 'desig_group',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_group,
                );

        $data['desig_short'] = array(
                'name' => 'desig_short',
                'id' => 'desig_short',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_short,

                );

        $data['desig_desc'] = array(
                'name' => 'desig_desc',
                'id' => 'desig_desc',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editdesig_data->desig_desc,
            
                );
        $data['desig_id'] = $desig_id;
        /*Form Validation*/
	$this->form_validation->set_rules('desig_code','Designation Code','trim|xss_clean|required');
        $this->form_validation->set_rules('tnt','Designation Type','trim|xss_clean|required');
        $this->form_validation->set_rules('grouppost','Designation Subtype','trim|xss_clean|required');
        $this->form_validation->set_rules('desig_payscale','Designation payscale','trim|xss_clean|required');
        $this->form_validation->set_rules('desig_name','Designation Name','trim|xss_clean|required');
        $this->form_validation->set_rules('desig_group','Designation Group','trim|xss_clean|required');
        $this->form_validation->set_rules('desig_short','Designation Short','trim|xss_clean');
        $this->form_validation->set_rules('desig_desc','Designation Description','trim|xss_clean');

        /* Re-populating form */
        if ($_POST)
        {
	    $data['desig_code']['value'] = $this->input->post('desig_code', TRUE);
            $data['desig_type']['value'] = $this->input->post('tnt', TRUE);
            $data['desig_subtype']['value'] = $this->input->post('grouppost', TRUE);
            $data['desig_payscale']['value'] = $this->input->post('desig_payscale', TRUE);
            $data['desig_name']['value'] = $this->input->post('desig_name', TRUE);
            $data['desig_group']['value'] = $this->input->post('desig_group', TRUE);
            $data['desig_short']['value'] = $this->input->post('desig_short', TRUE);
            $data['desig_desc']['value'] = $this->input->post('desig_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup2/editdesignation', $data);
            return;
        }
        else{

	    
	    $desig_code= strtoupper($this->input->post('desig_code', TRUE));
            $desig_type = $this->input->post('tnt', TRUE);
            $desig_subtype= $this->input->post('grouppost', TRUE);
            $desig_payscale= $this->input->post('desig_payscale', TRUE);
            $desig_name = $this->input->post('desig_name', TRUE);
            $desig_group = $this->input->post('desig_group', TRUE);
            $desig_short = $this->input->post('desig_short', TRUE);
            $desig_desc= $this->input->post('desig_desc', TRUE);
          //  $data_edesig_id = $desg_id;
           echo $desig_type;
            $logmessage = "";
            if($editdesig_data->desig_code != $desig_code)
                $logmessage = "Edit Designation Code " .$editdesig_data->desig_code. " changed by " .$desig_code;
            if($editdesig_data->desig_type != $desig_type)
                $logmessage = "Edit Designation Type " .$editdesig_data->desig_type. " changed by " .$desig_type;
            if($editdesig_data->desig_subtype != $desig_subtype)
                $logmessage = "Edit Designation Subtype " .$editdesig_data->desig_subtype. " changed by " .$desig_subtype;
           if($editdesig_data->desig_payscale != $desig_payscale)
               $logmessage = "Edit Designation Payscale " .$editdesig_data->desig_payscale. " changed by " .$desig_payscale;
           if($editdesig_data->desig_group != $desig_group)
                $logmessage = "Edit  Designation group " .$editdesig_data->desig_group. " changed by " .$desig_group;
            if($editdesig_data->desig_short !=  $desig_short)
                $logmessage = "Edit Designation short  " .$editdesig_data->desig_short. " changed by " . $desig_short;
            if($editdesig_data->desig_desc != $desig_desc)
                $logmessage = "Edit  Designation desc " .$editdesig_data->desig_desc. " changed by " .$desig_desc;

	    //'desig_name' => $data_edesignationname,
	    $update_data = array(

              'desig_code' => $desig_code,
              'desig_type' => $desig_type,
              'desig_subtype'=> $desig_subtype,
              'desig_payscale'=> $desig_payscale,
              'desig_name'  => $desig_name,
              'desig_group' => $desig_group,
              'desig_short' => $desig_short,
              'desig_desc' => $desig_desc,
            );
               //'modifierid'=>$this->session->userdata('username'),
               //'modifydate'=>date('y-m-d')

        $gradedflag=$this->commodel->updaterec('designation', $update_data,'desig_id', $desig_id);
        if(!$gradedflag)
            {
                $this->logger->write_logmessage("error","Edit designation Setting error", "Edit designation Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit designation Setting error", "Edit designation Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating designation - ' . $logmessage . '.', 'error');
                $this->load->view('setup2/editdesignation', $data);
           }
            else{
                $this->logger->write_logmessage("update","Edit designation Setting by".$this->session->userdata('username') , "Edit designation Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit designation Setting by".$this->session->userdata('username') , "Edit designation Setting details. $logmessage ");
                $this->session->set_flashdata('success','Designation  detail updated successfully..');
                redirect('setup2/designation');
                }
        }//else
        redirect('setup2/editdesignation');
    }//Edit Designation function end

                                                                                                                                                                                
                           /* --------****Authority Starts****------------ */
     

/** This function display the Authorities                                                                                                                                                        
  * @param type  
  * @return type
  */

     public function authority() {
        $this->result = $this->logmodel->get_list('authorities');
        $this->logger->write_logmessage("view"," View Authorities ", "Authorities details...");
        $this->logger->write_dblogmessage("view"," View Authorities " , "Authorities record display successfully..." );
        $this->load->view('setup2/authority',$this->result);
       }
   
 /** This function add the authority
     * @return type
     */

   public function addauthority()
    {
	    if(isset($_POST['addauthority'])) {
                 $this->form_validation->set_rules('code','Authority Code','trim|xss_clean|required');
                 $this->form_validation->set_rules('name','Authority Name','trim|xss_clean|required');
                 $this->form_validation->set_rules('nickname','Authority Nickname','trim|xss_clean|required');
                 $this->form_validation->set_rules('authority_email','Authority Email','trim|xss_clean|valid_email|callback_isemailExist');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                        $data = array(
                                'code'=>strtoupper($_POST['code']),
                                'name'=>ucfirst(strtolower($_POST['name'])),
                                'nickname'=>($_POST['nickname']),
                                'authority_email'=>($_POST['authority_email']),
                                );
                           $rflag=$this->logmodel->insertrec('authorities', $data);
                           if (!$rflag)
                        {
                                $this->logger->write_logmessage("insert","Trying to add authority ", "authority is not added ".$_POST['name']);
                                $this->logger->write_dblogmessage("insert","Trying to add authority", "authority is not added ".$_POST['name']);
                                $this->session->set_flashdata('err_message','Error in adding authority setting - '  , 'error');
                                redirect('setup2/addauthority');
                        }
                        else{
                                $this->logger->write_logmessage("insert","Add authority Setting", "Authority".$_POST['name']." added  successfully...");
                                $this->logger->write_dblogmessage("insert","Add  authority  Setting", "Authority  ".$_POST['name']."added  successfully...");
                                $this->session->set_flashdata("success", " Authority  add successfully...");
                                redirect("setup2/authority");
                        }
                }//close if vallidation
        }//
        $this->load->view('setup2/addauthority');
    }


   /** This function check for duplicate authority
     * @return type
     */
    public function isEmailExist($authority_email) {
        $is_exist = $this->logmodel->isduplicate('authorities','authority_email',$authority_email);
        if ($is_exist)
        {
            $this->form_validation->set_message('isemailExist', 'Authority is already exist.');
            return false;
        }
        else {
            return true;
        }
    }  
 

  /**This function Delete the authority records
     * @param type $id
     * @return type
     */

   public function deleteauthority($id) {
        $_data_q=$this->logmodel->get_listrow('authorities','id ', $id);
        if ($_data_q->num_rows() < 1)
        {
            redirect('setup2/authority');
        }

          $gradedflag=$this->logmodel->deleterow('authorities','id', $id);
          if(!$gradedflag)
          {
                $this->logger->write_message("error", "Error  in deleting authorities " ."[id:" . $id . "]");
                $this->logger->write_dbmessage("error", "Error  in deleting authorities "." [id:" . $id . "]");
                $this->session->set_flashdata('err_message', 'Error in Deleting authorities - ', 'error');
                redirect('setup2/authority');
                return;
          }
          else{
                $this->logger->write_logmessage("delete", "Deleted   authorities " . "[id:" . $id . "] deleted successfully.. " );
                $this->logger->write_dblogmessage("delete", "Deleted authorities" ." [id:" . $id . "] deleted successfully.. " );
                $this->session->set_flashdata("success", 'Authority Deleted successfully...' );
                redirect('setup2/authority');
        }
        $this->load->view('setup2/authority',$data);
    } 

  /**This function is used for update authorities records
     * @param type $id authorities id
     * @return type
     */

    public function editauthority($id) {
        $data_q=$this->logmodel->get_listrow('authorities','id', $id);
        if ($data_q->num_rows() < 1)
        {
            redirect('setup2/editauthority');
        }
        $edit_data = $data_q->row();

        /* Form fields */

        $data['code'] = array(
                'name' => 'code',
                'id' => 'code',
                'maxlength' => '50',
                'size' => '40',
                'value' => $edit_data-> code,
                );
        $data['name'] = array(
                'name' => 'name',
                'id' => 'name',
                'maxlength' => '50',
                'size' => '40',
                'value' => $edit_data-> name,
                );
        $data['nickname'] = array(
                'name' => 'nickname',
                'id' => 'nickname',
                'maxlength' => '50',
                'size' => '40',
                'value' => $edit_data->nickname,

                );
        $data['authority_email'] = array(
                'name' => 'authority_email',
                'id' => 'authority_email',
                'maxlength' => '50',
                'size' => '40',
                'value' => $edit_data->authority_email,
                'readonly' => 'readonly'
                );                      
        $data['id'] = $id;
        /*Form Validation*/

        $this->form_validation->set_rules('code','Authorities Code','trim|xss_clean');
        $this->form_validation->set_rules('name','Authorities Name','trim|xss_clean');
        $this->form_validation->set_rules('nickname ','Authorities Nickname ','trim|xss_clean');
        
        /* Re-populating form */

        if ($_POST)
        {
            $data['code']['value'] = $this->input->post('code', TRUE);
            $data['name']['value'] = $this->input->post('name', TRUE);
            $data['nickname']['value'] = $this->input->post('nickname', TRUE);
            
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup2/editauthority', $data);
            return;
        }
        else{
	    $code = strtoupper($this->input->post('code', TRUE));
            $name = ucfirst(strtolower($this->input->post('name', TRUE)));
            $nickname = ($this->input->post('nickname', TRUE));
            
            $logmessage = "";
            if($edit_data-> code != $code)
                     $logmessage = "Edit Authorities Code " .$edit_data-> code. " changed by " .$code;
            if($edit_data-> name != $name)
                     $logmessage = "Edit Authorities Name " .$edit_data-> name. " changed by " .$name;
            if($edit_data->nickname != $nickname)
                $logmessage = "Edit Authorities Nickname   " .$edit_data->nickname. " changed by " .$nickname;
            //'desig_name' => $data_edesignationname,
            $update_data = array(
               'code' => $code,
               'name' => $name,
               'nickname' => $nickname,
               );
               //'modifierid'=>$this->session->userdata('username'),
               //'modifydate'=>date('y-m-d')
            

       $gradedflag=$this->logmodel->updaterec('authorities', $update_data,'id', $id);
        if(!$gradedflag)
            {
                $this->logger->write_logmessage("error","Edit authorities Setting error", "Edit authorities Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit authorities Setting error", "Edit authorities Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating authorities - ' . $logmessage . '.', 'error');
                $this->load->view('setup2/editauthority', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit authorities Setting by".$this->session->userdata('username') , "Edit authorities Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit authorities Setting by".$this->session->userdata('username') , "Edit authorities Setting details. $logmessage ");
                $this->session->set_flashdata('success','Authority detail updated successfully..');
                redirect('setup2/authority');
               }                
        }//else
        redirect('setup2/editauthority');
    }//Edit Authority function end
}//end class
