<?php

/* 
 * @name Adminstuexam.php
 * @author Sumit Saxena (sumitsesaxena@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Adminstuexam extends CI_Controller
    {
    function __construct() {
        parent::__construct();
		$this->load->model("user_model","usermodel");
                $this->load->model('Common_model',"commodel");
		$this->load->model('dependrop_model','depmodel');
		$this->load->model("DateSem_model","datesemmodel");
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }

	/** This function add the student_addexamcenter
     * @return type
     */
	public function stu_addexamcenter(){
		$data = array('country_id' => 101);
		 $this->cresult = $this->commodel->get_listspficemore('states','id,name',$data);

		 if(isset($_POST['addexamcenter'])) {
                 	$this->form_validation->set_rules('eec_code','Exam Center Code','trim|xss_clean|required');
	                 $this->form_validation->set_rules('eec_name','Exam Center Name','trim|xss_clean|required|callback_isECExist');
                	 $this->form_validation->set_rules('eec_address','Exam Center Address','trim|xss_clean|required');
        	         $this->form_validation->set_rules('eec_state','Exam Center State','trim|xss_clean|required');
	                 $this->form_validation->set_rules('eec_city','Exam Center City','trim|xss_clean|required');
        	         $this->form_validation->set_rules('eec_incharge','Exam Center Incharge','trim|xss_clean');
                	 $this->form_validation->set_rules('eec_noofroom','Exam Center Number of Room','trim|xss_clean|integer');
	                 $this->form_validation->set_rules('eec_capacityinroom','Exam Center Capacity in Room','trim|xss_clean|integer');
        	         $this->form_validation->set_rules('eec_totalcapacity','Exam Center Total Capacity','trim|xss_clean');
                	 $this->form_validation->set_rules('eec_contactno','Exam Center Contact No','trim|xss_clean|numeric');
	                 $this->form_validation->set_rules('eec_contactemail','Exam Center Contact Email','trim|xss_clean|valid_email');

        	         if($this->form_validation->run()==TRUE){
                	        $data = array(
                        	        'sec_code'=>strtoupper($_POST['eec_code']),
                                	'sec_name'=>$_POST['eec_name'],
	                                'sec_address'=>ucwords(strtolower($_POST['eec_address'])),
					'sec_state'=>$_POST['eec_state'],
                	                'sec_city'=>$_POST['eec_city'],
					'sec_incharge'=>$_POST['eec_incharge'],
                                	'sec_noofroom'=>$_POST['eec_noofroom'],
	                                'sec_capacityinroom'=>$_POST['eec_capacityinroom'],
        	                        'sec_totalcapacity'=>$_POST['eec_totalcapacity'],
					'sec_contactno'=>$_POST['eec_contactno'],
                        	        'sec_contactemail'=>$_POST['eec_contactemail'],
                           		);
	                           $examcflag=$this->commodel->insertrec('student_examcenter', $data);
        	                   if (!$examcflag)
                	           {
                                	$this->logger->write_logmessage("insert","Trying to Exam Center", "Exam Center is not added ".$_POST['eec_name']);
                                	$this->logger->write_dblogmessage("insert","Trying to Exam Center", "Exam Center is not added".$_POST['eec_name']);
                                	$this->session->set_flashdata('err_message','Error in adding exam center setting - '  , 'error');
                                	redirect('adminstuexam/stu_addexamcenter');
                        	}
                        	else{
                                	$this->logger->write_logmessage("insert","Add Exam Center Setting", "Exam Center".$_POST['eec_name']." added  successfully...");
	                                $this->logger->write_dblogmessage("insert","Add Exam Center Setting", "Exam Center".$_POST['eec_name']."added  successfully...");
        	                        $this->session->set_flashdata("success", "Exam Center add successfully...");
                	                redirect("adminstuexam/stu_examcenter");
                        	}
                	}
        	}

    	$this->load->view('admin_exam/admin_stuaddexamcenter');
    }
/** This function check for duplicate exam center
     * @return type
     */
    public function isECExist($eec_name) {
        $is_exist = $this->commodel->isduplicate('student_examcenter','sec_name',$eec_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isECExist', 'Exam Center is already exist.');
            return false;
        }
        else {
            return true;
        }
    }

/** This function display the Exam Center                                                                                                                                                 
  * @param type  
  * @return type
  */

     public function stu_examcenter() {
        $this->result = $this->commodel->get_list('student_examcenter');
        $this->logger->write_logmessage("view"," View Exam Center", "Exam Center details...");
        $this->logger->write_dblogmessage("view"," View Exam Center" , "Exam Center record display successfully..." );
        $this->load->view('admin_exam/admin_stuexamcenter',$this->result);
       }

 /**This function is used for update exam center records
     */
	
   public function stu_editexamcenter($id){
	$data = array('country_id' => 101);
	$this->cresult = $this->commodel->get_listspficemore('states','id,name',$data);
	$examcenterrow=$this->commodel->get_listrow('student_examcenter','sec_id', $id);
        if ($examcenterrow->num_rows() < 1)
        {
            redirect('adminstuexam/stu_editexamcenter');
        }
        $exam_data_q = $examcenterrow->row();

/* Form fields */

               $data['eec_code'] = array(
               'name' => 'eec_code',
               'id' => 'eec_code',
               'size' => '40',
               'value' => $exam_data_q->sec_code,
               );
		$data['eec_name'] = array(
               'name' => 'eec_name',
               'id' => 'eec_name',
               'size' => '40',
               'value' => $exam_data_q->sec_name,
               );
		$data['eec_address'] = array(
               'name' => 'eec_address',
               'id' => 'eec_address',
               'size' => '40',
               'value' => $exam_data_q->sec_address,
               );
		$data['eec_state'] = array(
               'name' => 'eec_state',
               'id' => 'eec_state',
               'size' => '40',
               'value' => $exam_data_q->sec_state,
	       'readonly' => 'readonly'
               );

		$data['eec_city'] = array(
               'name' => 'eec_city',
               'id' => 'eec_city',
               'size' => '40',
               'value' => $exam_data_q->sec_city,
		'readonly' => 'readonly'
               );
		$data['eec_incharge'] = array(
               'name' => 'eec_incharge',
               'id' => 'eec_incharge',
               'size' => '40',
               'value' => $exam_data_q->sec_incharge,
               );
	       $data['eec_noofroom'] = array(
               'name' => 'eec_noofroom',
               'id' => 'eec_noofroom',
               'size' => '40',
               'value' => $exam_data_q->sec_noofroom,
               );
		$data['eec_capacityinroom'] = array(
               'name' => 'eec_capacityinroom',
               'id' => 'eec_capacityinroom',
               'size' => '40',
               'value' => $exam_data_q->sec_capacityinroom,
               );
	       $data['eec_totalcapacity'] = array(
               'name' => 'eec_totalcapacity',
               'id' => 'eec_totalcapacity',
               'size' => '40',
               'value' => $exam_data_q->sec_totalcapacity,
               );
	       $data['eec_contactno'] = array(
               'name' => 'eec_contactno',
               'id' => 'eec_contactno',
               'size' => '40',
               'value' => $exam_data_q->sec_contactno,
               );
	      $data['eec_contactemail'] = array(
               'name' => 'eec_contactemail',
               'id' => 'eec_contactemail',
               'size' => '40',
               'value' => $exam_data_q->sec_contactemail,
               );
	       $data['id'] = $id;
/*Form Validation*/
		 $this->form_validation->set_rules('eec_code','Exam Center Code','trim|xss_clean|required');
                 $this->form_validation->set_rules('eec_name','Exam Center Name','trim|xss_clean|required');
                 $this->form_validation->set_rules('eec_address','Exam Center Address','trim|xss_clean|required');
		 //$this->form_validation->set_rules('eec_state','Exam Center State','trim|xss_clean');
                 //$this->form_validation->set_rules('eec_city','Exam Center City','trim|xss_clean');
                 $this->form_validation->set_rules('eec_incharge','Exam Center Incharge','trim|xss_clean');
                 $this->form_validation->set_rules('eec_noofroom','Exam Center Number of Room','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_capacityinroom','Exam Center Capacity in Room','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_totalcapacity','Exam Center Total Capacity','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_contactno','Exam Center Contact No','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_contactemail','Exam Center Contact Email','trim|xss_clean|valid_email');
/* Re-populating form */
   	         if ($_POST){
            	 $data['eec_code']['value'] = $this->input->post('eec_code', TRUE);
            	 $data['eec_name']['value'] = $this->input->post('eec_name', TRUE);
		 $data['eec_address']['value'] = $this->input->post('eec_address', TRUE);
                 //$data['eec_city']['value'] = $this->input->post('eec_city', TRUE);
		 $data['eec_incharge']['value'] = $this->input->post('eec_incharge', TRUE);
                 $data['eec_noofroom']['value'] = $this->input->post('eec_noofroom', TRUE);
		 $data['eec_capacityinroom']['value'] = $this->input->post('eec_capacityinroom', TRUE);
                 $data['eec_totalcapacity']['value'] = $this->input->post('eec_totalcapacity', TRUE);
		 $data['eec_contactno']['value'] = $this->input->post('eec_contactno', TRUE);
                 $data['eec_contactemail']['value'] = $this->input->post('eec_contactemail', TRUE);
       		 }

 	         if ($this->form_validation->run() == FALSE){
                 	$this->load->view('admin_exam/admin_stueditexamcenter', $data);
                 	return;
                 }
		else{
	            	$eec_code = $this->input->post('eec_code', TRUE);
        	    	$eec_name = $this->input->post('eec_name', TRUE);
			$eec_address = ucwords(strtolower($this->input->post('eec_address', TRUE)));
			//$eec_city = ucwords(strtolower($this->input->post('eec_city', TRUE)));
			$eec_incharge = ucwords(strtolower($this->input->post('eec_incharge', TRUE)));
			$eec_noofroom = $this->input->post('eec_noofroom', TRUE);
	                $eec_capacityinroom = $this->input->post('eec_capacityinroom', TRUE);
			$eec_totalcapacity = $this->input->post('eec_totalcapacity', TRUE);
                	$eec_contactno =$this->input->post('eec_contactno', TRUE);
			$eec_contactemail =$this->input->post('eec_contactemail', TRUE);
        	        $logmessage = "";
	
			if($exam_data_q->sec_code != $eec_code)
                		$logmessage = "Add Exam Center" .$exam_data_q->sec_code. " changed by " .$eec_code;
			if($exam_data_q->sec_name != $eec_name)
        		        $logmessage = "Add Exam Center" .$exam_data_q->sec_name. " changed by " .$eec_name;
			if($exam_data_q->sec_address != $eec_address)
        		        $logmessage = "Add Exam Center" .$exam_data_q->sec_address. " changed by " .$eec_address;
			//if($exam_data_q->eec_city != $eec_city)
        		        //$logmessage = "Add Exam Center" .$exam_data_q->eec_city. " changed by " .$eec_city;
			if($exam_data_q->sec_incharge != $eec_incharge)
        	        	$logmessage = "Add Exam Center" .$exam_data_q->sec_incharge. " changed by " .$eec_incharge;
			if($exam_data_q->sec_noofroom != $eec_noofroom)
		                $logmessage = "Add Exam Center" .$exam_data_q->sec_noofroom. " changed by " .$eec_noofroom;
			if($exam_data_q->sec_capacityinroom != $eec_capacityinroom)
		                $logmessage = "Add Exam Center" .$exam_data_q->sec_capacityinroom. " changed by " .$eec_capacityinroom;
			if($exam_data_q->sec_totalcapacity != $eec_totalcapacity)
		                $logmessage = "Add Exam Center" .$exam_data_q->sec_totalcapacity. " changed by " .$eec_totalcapacity;
			if($exam_data_q->sec_contactno != $eec_contactno)
		                $logmessage = "Add Exam Center" .$exam_data_q->sec_contactno. " changed by " .$eec_contactno;
			if($exam_data_q->sec_contactemail != $eec_contactemail)
		                $logmessage = "Add Exam Center" .$exam_data_q->sec_contactemail. " changed by " .$eec_contactemail;
			/*$arins_data = array(
		                'eeca_eecid' => $id,
		                'eeca_code' => $exam_data_q->sec_code,
               			'eeca_name' => $exam_data_q->sec_name,
		                'eeca_address' => $exam_data_q->sec_address,
               			'eeca_city' => $exam_data_q->sec_city,
               			'eeca_state' => $exam_data_q->sec_state,
		                'eeca_incharge' => $exam_data_q->sec_incharge,
               			'eeca_noofroom' => $exam_data_q->sec_noofroom,
		                'eeca_capacityinroom' => $exam_data_q->sec_capacityinroom,
               			'eeca_totalcapacity' => $exam_data_q->sec_totalcapacity,
		               	'eeca_contactno' => $exam_data_q->sec_contactno,
               			'eeca_contactemail' =>$exam_data_q->sec_contactemail,
               			'eeca_archivename' => $this->session->userdata('username'),
               			'eeca_archivedate' => date('y-m-d')
            		);
	                $examcenterflaga=$this->commodel->insertrec('admissionstudent_enterenceexamcentera', $arins_data);*/


			$update_data = array(
               			'sec_code' => $eec_code,
		               	'sec_name' => $eec_name,
	       			'sec_address' => $eec_address,
	       			'sec_incharge' => $eec_incharge,
               			'sec_noofroom' => $eec_noofroom,
	       			'sec_capacityinroom' => $eec_capacityinroom,
               			'sec_totalcapacity' => $eec_totalcapacity,
	       			'sec_contactno' => $eec_contactno,
               			'sec_contactemail' => $eec_contactemail,
            		);
            		$examcenterflag=$this->commodel->updaterec('student_examcenter', $update_data,'sec_id', $id);
            		if(!$examcenterflag)
            		{
                		$this->logger->write_logmessage("error","Edit Exam Center Setting error", "Edit Exam Center Setting details. $logmessage ");
		                $this->logger->write_dblogmessage("error","Edit Exam Center Setting error", "Edit Exam Center Setting details. $logmessage ");
                		$this->session->set_flashdata('err_message','Error updating Exam Center- ' . $logmessage . '.', 'error');
		                $this->load->view('adminstuexam/stu_editexamcenter', $data);
            		}
            		else{
		                $this->logger->write_logmessage("update","Edit Exam Center Setting", "Edit Exam Center Setting details. $logmessage ");
                		$this->logger->write_dblogmessage("update","Edit Exam Center Setting", "Edit Exam Center Setting details. $logmessage ");
		                $this->session->set_flashdata('success','Exam Center detail updated successfully..');
                		redirect('adminstuexam/stu_examcenter');
                	}
        	}//else
   }//end edit exam center function
//This function get city
        public function get_city(){
               $statid = $this->input->post('sid');
               $this->depmodel->get_citylist($statid);
        }

	public function exam_scheduleview(){
		$data['exam_schedulelist'] = $this->commodel->get_list('studentexam_schedule');
		$this->load->view('admin_exam/adminstu_viewexamschedule',$data);
	}

	public function exam_scheduleadd(){
		$userid = $this->session->userdata('id_user');
		$data['pcategory'] = $this->commodel->get_list('programcategory');
		//$data['exam_center'] = $this->commodel->get_list('study_center');
		$data['exam_center'] = $this->commodel->get_list('org_profile');
		$data['prgname'] =  $this->commodel->get_list('program');
		$data['exmname'] =  $this->commodel->get_list('examtype');
		$data['deptname'] =  $this->result = $this->commodel->get_list('Department');
		 if(isset($_POST['addexamsch'])) {
                 	 $this->form_validation->set_rules('examsch_center','Exam Center Name','trim|xss_clean|required');
	                 $this->form_validation->set_rules('examsch_prgid','Exam Programme Name','trim|xss_clean|required');
                	 $this->form_validation->set_rules('examsch_deptid','Exam Department Name','trim|xss_clean|required');
        	         $this->form_validation->set_rules('examsch_sem','Exam Semester','trim|xss_clean|required');
	                 $this->form_validation->set_rules('examsch_session','Exam Session','trim|xss_clean|required');
        	         $this->form_validation->set_rules('examsch_name','Exam Name','trim|xss_clean|required');
                	 $this->form_validation->set_rules('examsch_datetime','Exam Date Time','trim|xss_clean|required');
	                
        	         if($this->form_validation->run()==TRUE){
			$cdate=date("Y-m-d");
				$acadyear = $this->input->post('examsch_session',TRUE);
                	        $examschdata = array(
                        	        'exsc_centerid' =>$_POST['examsch_center'],
					'exsc_progcatid' =>$_POST['prgcategoryid'],
                                	'exsc_prgid'=>$_POST['examsch_prgid'],
	                                'exsc_deptid'=>$_POST['examsch_deptid'],
					'exsc_semester'=> $_POST['examsch_sem'],
                	                'exsc_acadyear'=> $acadyear,
					'exsc_subjectid'=>$_POST['examsch_subid'],
					'exsc_paperid'=>$_POST['examsch_paperid'],
					'exsc_examname'=>$_POST['examsch_name'],
                                	'exsc_examdatetime'=>$_POST['examsch_datetime'],
					
					'exsc_creatorid'=>$userid,
                	                'exsc_creatordate'=>$cdate,
					'exsc_modifierid'=>$userid,
                                	'exsc_modifierdate'=>$cdate,	
	                                
                           	);
	                           $examsch = $this->commodel->insertrec('studentexam_schedule', $examschdata);
				   //print_r($examschdata);die;	
        	                   if (!$examsch)
                	           {
                                	$this->logger->write_logmessage("insert","Exam Schedule For Student Is Not Inserted.");
                                	$this->logger->write_dblogmessage("insert","Exam Schedule For Student Is Not Inserted.");
                                	$this->session->set_flashdata('err_message','Error in adding exam schedule.');
                                	redirect('adminstuexam/exam_scheduleadd');
                        	}
                        	else{
                                	$this->logger->write_logmessage("insert","Exam Schedule For Student Successfully Inserted.");
	                                $this->logger->write_dblogmessage("insert","Exam Schedule For Student Successfully Inserted.");
        	                        $this->session->set_flashdata("success", "Exam Schedule add successfully...");
                	                redirect("adminstuexam/exam_scheduleview");
                        	}
                	}
        	}
		$this->load->view('admin_exam/adminstu_examschedule',$data);
	}

 /**This function is used for update exam schedule
     */
	
   public function exam_scheduleedit($id){
	//$data['exam_center'] = $this->commodel->get_list('study_center');
	$data['exam_center'] = $this->commodel->get_list('org_profile');	
	$data['exmname'] =  $this->commodel->get_list('examtype');
	$examcenterrow=$this->commodel->get_listrow('studentexam_schedule','exsc_id', $id);
        if ($examcenterrow->num_rows() < 1)
        {
            redirect('adminstuexam/exam_scheduleedit');
        }
        $exam_schedule = $examcenterrow->row();

/* Form fields */
	
	//$prgcatname = $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$exam_schedule->exsc_progcatid)->prgcat_name;
       // $univname = $this->commodel->get_listspfic1('org_profile','org_name','org_id',$exam_schedule->exsc_centerid)->org_name;       
		$data['exmsch_center'] = array(
               'name' => 'exmsch_center',
               'id' => 'exmsch_center',
               'size' => '40',
               'value' => $exam_schedule->exsc_centerid,	
		//'value' => $univname,
	       'readonly' => 'readonly'
               );

		$data['exmsch_progcat'] = array(
               'name' => 'exmsch_progcat',
               'id' => 'exmsch_progcat',
               'size' => '40',
               'value' => $exam_schedule->exsc_progcatid,
	      // 'value' => $prgcatname,
	       'readonly' => 'readonly'
               );
		
		$data['exmsch_dept'] = array(
               'name' => 'exmsch_dept',
               'id' => 'exmsch_dept',
               'size' => '40',
               'value' => $exam_schedule->exsc_deptid,
               );

		$data['exmsch_prog'] = array(
               'name' => 'exmsch_prog',
               'id' => 'exmsch_prog',
               'size' => '40',
               'value' => $exam_schedule->exsc_prgid,
               );

	       $data['exmsch_sem'] = array(
               'name' => 'exmsch_sem',
               'id' => 'exmsch_sem',
               'size' => '40',
               'value' => $exam_schedule->exsc_semester,
               );
		$data['exmsch_subj'] = array(
               'name' => 'exmsch_subj',
               'id' => 'exmsch_subj',
               'size' => '40',
               'value' => $exam_schedule->exsc_subjectid,
               );

	       $data['exmsch_paper'] = array(
               'name' => 'exmsch_paper',
               'id' => 'exmsch_paper',
               'size' => '40',
               'value' => $exam_schedule->exsc_paperid,
               );

	       $data['exmsch_session'] = array(
               'name' => 'exmsch_session',
               'id' => 'exmsch_session',
               'size' => '40',
               'value' => $exam_schedule->exsc_acadyear,
               );

	      $data['exmsch_exname'] = array(
               'name' => 'exmsch_exname',
               'id' => 'exmsch_exname',
               'size' => '40',
               'value' => $exam_schedule->exsc_examname,
               );

	      $data['exmsch_date'] = array(
               'name' => 'exmsch_date',
               'id' => 'exmsch_date',
               'size' => '40',
               'value' => $exam_schedule->exsc_examdatetime,
               );
	       $data['id'] = $id;
/*Form Validation*/
		 $this->form_validation->set_rules('exmsch_center','Exam Center Code','trim|xss_clean|required');
                 $this->form_validation->set_rules('exmsch_progcat','Exam Program Category','trim|xss_clean|required');
		 $this->form_validation->set_rules('exmsch_dept','Exam Department','trim|xss_clean|required');
		 $this->form_validation->set_rules('exmsch_prog','Exam Programme','trim|xss_clean|required');
		 $this->form_validation->set_rules('exmsch_sem','Exam Semester','trim|xss_clean|required');
		 $this->form_validation->set_rules('exmsch_subj','Exam Subject','trim|xss_clean|required');
		 $this->form_validation->set_rules('exmsch_paper','Exam Paper','trim|xss_clean|required');	
		 $this->form_validation->set_rules('exmsch_session','Exam Session','trim|xss_clean|required');	
		 $this->form_validation->set_rules('exmsch_exname','Exam Name','trim|xss_clean|required');
		 $this->form_validation->set_rules('exmsch_date','Exam Date & Time','trim|xss_clean|required');	
/* Re-populating form */
   	         if ($_POST){
		
            	 	$data['exmsch_center']['value'] = $this->input->post('examsch_center', TRUE);
            		$data['exmsch_progcat']['value'] = $this->input->post('examsch_progcat', TRUE);
			$data['exmsch_dept']['value'] = $this->input->post('examsch_dept', TRUE);
			$data['exmsch_prog']['value'] = $this->input->post('examsch_prog', TRUE);
			$data['exmsch_sem']['value'] = $this->input->post('examsch_sem', TRUE);
			$data['exmsch_subj']['value'] = $this->input->post('examsch_subj', TRUE);
			$data['exmsch_paper']['value'] = $this->input->post('examsch_paper', TRUE);
       			$data['exmsch_session']['value'] = $this->input->post('examsch_session', TRUE);
			$data['exmsch_exname']['value'] = $this->input->post('examsch_exname', TRUE);	
			$data['exmsch_date']['value'] = $this->input->post('exmsch_date', TRUE);	
		}

 	         if ($this->form_validation->run() == FALSE){
                 	$this->load->view('admin_exam/adminstu_editexamschedule', $data);
                 	return;
                 }
		else{
	            	$exm_center = $this->input->post('exmsch_center', TRUE);
        	    	$exm_prgcat = $this->input->post('exmsch_progcat', TRUE);
			$exm_dept = $this->input->post('exmsch_dept', TRUE);
			$exm_prog = $this->input->post('exmsch_prog', TRUE);
			$exm_sem = $this->input->post('exmsch_sem', TRUE);
			$exm_subj = $this->input->post('exmsch_subj', TRUE);
			$exm_paper = $this->input->post('exmsch_paper', TRUE);
			$exm_session = $this->input->post('exmsch_session', TRUE);
			$exm_name = $this->input->post('exmsch_exname', TRUE);
			$exm_date = $this->input->post('exmsch_date', TRUE);
			
        	        $logmessage = "";
	
			if($exam_schedule->exsc_centerid != $exm_center)
                		$logmessage = "Add University Name" .$exam_schedule->exsc_centerid. " changed by " .$exm_center;
			if($exam_schedule->exsc_progcatid != $exm_prgcat)
                		$logmessage = "Add Program Category" .$exam_schedule->exsc_centerid. " changed by " .$exm_prgcat;
			if($exam_schedule->exsc_deptid != $exm_dept)
                		$logmessage = "Add Department" .$exam_schedule->exsc_deptid. " changed by " .$exm_dept;
			if($exam_schedule->exsc_prgid != $exm_prog)
                		$logmessage = "Add Program Name" .$exam_schedule->exsc_prgid. " changed by " .$exm_prog;
			if($exam_schedule->exsc_semester != $exm_sem)
                		$logmessage = "Add Semester" .$exam_schedule->exsc_semester. " changed by " .$exm_sem;

			if($exam_schedule->exsc_subjectid != $exm_subj)
                		$logmessage = "Add Subject" .$exam_schedule->exsc_subjectid. " changed by " .$exm_subj;
		
			if($exam_schedule->exsc_paperid != $exm_paper)
                		$logmessage = "Add Subject" .$exam_schedule->exsc_paperid. " changed by " .$exm_paper;

			if($exam_schedule->exsc_acadyear != $exm_session)
                		$logmessage = "Add Subject" .$exam_schedule->exsc_acadyear. " changed by " .$exm_session;

			if($exam_schedule->exsc_examname != $exm_name)
                		$logmessage = "Add Subject" .$exam_schedule->exsc_examname. " changed by " .$exm_name;

			if($exam_schedule->exsc_examdatetime != $exm_date)
                		$logmessage = "Add Subject" .$exam_schedule->exsc_examdatetime. " changed by " .$exm_date;
			


			$update_exschdata = array(
               			'exsc_centerid' => $exm_center,
		               	'exsc_progcatid' => $exm_prgcat,
	       			'exsc_deptid' => $exm_dept,
	       			'exsc_prgid' => $exm_prog,
               			'exsc_semester' => $exm_sem,
	       			'exsc_subjectid' => $exm_subj,
               			'exsc_paperid' => $exm_paper,
	       			'exsc_acadyear' => $exm_session,
               			'exsc_examname' => $exm_name,
				'exsc_examdatetime' => $exm_date,
            		);
            		$examschflag=$this->commodel->updaterec('studentexam_schedule', $update_exschdata,'exsc_id', $id);
            		if(!$examschflag)
            		{
                		$this->logger->write_logmessage("error","Edit Exam Schedule", "Edit Exam Schedule Setting details. $logmessage ");
		                $this->logger->write_dblogmessage("error","Edit Exam Schedule Setting error", "Edit Exam Schedule Setting details. $logmessage ");
                		$this->session->set_flashdata('err_message','Error updating Exam Schedule- ' . $logmessage . '.', 'error');
		                $this->load->view('adminstuexam/exam_scheduleedit', $data);
            		}
            		else{
		                $this->logger->write_logmessage("update","Edit Exam Schedule Setting", "Edit Exam Schedule Setting details. $logmessage ");
                		$this->logger->write_dblogmessage("update","Edit Exam Schedule Setting", "Edit Exam Schedule Setting details. $logmessage ");
		                $this->session->set_flashdata('success','Exam schedule detail updated successfully..');
                		redirect('adminstuexam/exam_scheduleview');
                	}
        	}//else
   }//end edit exam center function

	public function viewadmitcard(){
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
		$data['currentacadyear'] = $currentacadyear;
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);
		
		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem);
		$sdata = 'sp_smid,sp_deptid,sp_programid';		
                $stud_program = $this->commodel->get_listspficemore('student_program',$sdata,$wharray);
		
		$data['stud_program'] = $stud_program;

		$this->load->view('admin_exam/admin_stuadmitcardgen',$data);
	}	
    	public function admitcardgen(){

		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);
		
		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem);
		$sdata = 'sp_smid,sp_deptid,sp_programid';		
                $stud_program = $this->commodel->get_listspficemore('student_program',$sdata,$wharray);
		
		$data['stud_program'] = $stud_program;
		foreach($stud_program as $data){
			$smid = $data->sp_smid;
			$deptid = $data->sp_deptid;

		$wharray = array('sm_id' => $smid);
		$sdata = 'sm_fname,sm_sccode,sm_photo,sm_id,sm_enrollmentno';		
                $stud_mastre = $this->commodel->get_listspficemore('student_master',$sdata,$wharray);

		foreach($stud_mastre as $row){
			$year=date('Y');
			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/admit_card';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

                        $desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/admit_card/'.$deptid;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			$studata['smid'] = $row->sm_id;
			
			$sname=$row->sm_fname;
			$studata['sname'] = $sname;
			$studata['currentacadyear']=$currentacadyear;
			$scid = $row->sm_sccode;
			//$studata['scname'] = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
			$studata['scname'] = $this->commodel->get_listspfic1('org_profile','org_name','org_id',$scid)->org_name;
			$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
			$studata['coursename'] = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '. $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';
			$studata['sturollno'] = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$smid)->senex_rollno;
			$stuenrollno = $row->sm_enrollmentno;
			$studata['stuenrollno'] = $stuenrollno;
			$stuphoto = $row->sm_photo;
			$studata['stuphoto'] = $stuphoto;
			$stusign = $row->sm_signature;
			$studata['stusign'] = $stusign;
			//get paper code & paper name
			//$paper1 = $this->commodel->get_listspfic1('student_program','sp_subid1','sp_smid',$smid)->sp_subid1;
			$stu_sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$smid)->sp_semester;
			$studata['stu_sem']=$stu_sem;
			 $wheredata = array('subp_degree' => $prgid,'subp_sem' => $stu_sem);
       			 $selectfield = 'subp_name,subp_code';
        		 $paper =  $this->commodel->get_listspficemore('subject_paper',$selectfield,$wheredata);
			 $studata['paper'] = $paper;

			//print_r($studata['paper']);die;
			$path = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/admit_card/'.$deptid.'/'.$smid.'.pdf';
			$studata['path'] = $path;
			$content = $this->load->view('admin_exam/adminstuadmitcardpdf',$studata,TRUE);
			$this->commodel->genpdf($content,$path);
		}
			
	
		}
		$message = 'Student Admit Card  Generate Successfully.';
		$this->session->set_flashdata('success',$message);
	 	redirect('adminstuexam/viewadmitcard');
	}	

	public function viewattsheet(){
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
		$data['currentacadyear'] = $currentacadyear;
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);
		$data['getsem'] = $getsem;	
		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem);
		$sdata = 'sp_deptid';		
                $getattsheet = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
		$data['getattsheet'] = $getattsheet;

		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem);
		$sdata = 'sp_sccode';		
                $getscid = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
		$data['getscid'] = $getscid;

		//$data['sclist'] = $this->commodel->get_list('study_center');
		$data['sclist'] = $this->commodel->get_list('org_profile');

		$this->load->view('admin_exam/adminstu_attsheet',$data);
	}
	
	public function searchattsheet(){
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);
		
		$scenter = $this->input->post('attstudy_center',TRUE);
		
		if(!empty($scenter)){
			$sdata = 'sp_smid,sp_deptid,sp_programid,sp_sccode';
			$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_sccode' => $scenter);
       			$getattsheet = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
			$data['getattsheet'] = $getattsheet;
			$this->attsheetpdf($getattsheet);
		}
		
		else{
		//collect the distinct list of center
			$scenter = $this->input->post('attstudy_center',TRUE);
			//$stcenter = $row->sc_name;
				if(!empty($stcenter)){
					$sdata = 'sp_smid,sp_deptid,sp_programid,sp_sccode';
					$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_sccode' => $scenter);
       					$getattsheet1 = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
					$data['getattsheet1'] = $getattsheet1;
					$this->logger->write_logmessage("update", "Exam Attendence sheet data for each enter".$getattsheet1);
					$this->attsheetpdf($getattsheet1);
				}
		}	
			if($getattsheet){
				$message = 'Student Exam Attendence Sheet Generated Successfully .';
				$this->session->set_flashdata('success',$message);
				redirect('adminstuexam/searchattsheet');
			}
			else{
				$message = 'Student Exam Attendence Sheet  Not Generated Successfully .';
				$this->session->set_flashdata('err_message',$message);
				redirect('adminstuexam/searchattsheet');
				}
	 	
	 	//redirect('adminstuexam/attendancesheetgen',$data, TRUE);
		$this->load->view('admin_exam/adminstu_attsheet',$data);

	 }


	public function attsheetpdf($getattsheet){
		$data['getattsheet']=$getattsheet;	
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);

		foreach($getattsheet as $row){
			$deptid = $row->sp_deptid;
			$progid = $row->sp_programid;
			$scid   = $row->sp_sccode;
		$sdata = 'sp_smid';
		//$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_programid' => $progid, 'sp_sccode' => $scid,'sp_deptid' => $deptid);
		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_sccode' =>$scid, 'sp_deptid' => $deptid  );
       		$stugetsttsheet = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
		$studata['stugetsttsheet'] = $stugetsttsheet;

		//foreach($stugetsttsheet as $row1){
			//$smid  = $row1->sp_smid;
			$year=date('Y');

			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }
			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/attendence_sheet';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

                        $desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/attendence_sheet/'.$scid;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			/*$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$year.'/attendence_sheet/'.$scid.'/'.$deptid;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }*/
		
			
		$studata['currentacadyear'] = $this->datesemmodel->getcurrentAcadYear();

		$content = $this->load->view('admin_exam/adminstu_attsheetpdf',$studata,TRUE);
		
		$path = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/attendence_sheet/'.$scid.'/'.$deptid.'.pdf';
		$this->commodel->genpdf($content,$path);
		}
		//}
		redirect('adminstuexam/viewattsheet',$data,TRUE);
	}

	
	public function viewformverifie(){
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
		$data['currentacadyear'] = $currentacadyear;
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);
		
		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem);
		$sdata = 'sp_programid';		
                $getverifie = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
		$data['getverifie'] = $getverifie;

		//$data['sclist'] = $this->commodel->get_list('study_center');
		$data['sclist'] = $this->commodel->get_list('org_profile');
		
		$this->load->view('admin_exam/adminstu_formverifie',$data);
	}

	public function formverifie(){
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
		$data['currentacadyear'] = $data;
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);

		$scenter = $this->input->post('study_center',TRUE);
		
		if(!empty($scenter)){
			$sdata = 'sp_smid,sp_deptid,sp_programid,sp_sccode';
			$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_sccode' => $scenter);
       			$getverifie = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
			$data['getverifie'] = $getverifie;
			$this->formverifiepdf($getverifie);
		}
		
		else{
		//collect the distinct list of center
			
			$scenter = $this->input->post('study_center',TRUE);
				$stcenter = $row->sc_name;
				if(!empty($stcenter)){
					$sdata = 'sp_smid,sp_deptid,sp_programid,sp_sccode';
					$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_sccode' => $scenter);
       					$getverifie1 = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
					$data['getverifie1'] = $getverifie1;
					$this->logger->write_logmessage("update", "Exam Attendence sheet data for each enter".$getattsheet1);
					$this->formverifiepdf($getverifie1);
				}
		}	
			if(!$getverifie){
				$message = 'Student Exam Verification PDF Genrated Successfully .';
				$this->session->set_flashdata('success',$message);
				redirect('adminstuexam/viewformverifie');
			}
			else{
				$message = 'Student Exam Verification PDF Successfully Not Generated.';
				$this->session->set_flashdata('err_message',$message);
				redirect('adminstuexam/viewformverifie');
				}
	 	
	 	//redirect('adminstuexam/attendancesheetgen',$data, TRUE);
		$this->load->view('admin_exam/adminstu_formverifie',$data);

	 }
	
	public function formverifiepdf($getverifie){
		$data['getverifie']=$getverifie;	
		$currentacadyear = $this->datesemmodel->getcurrentAcadYear();
                $semester = $this->datesemmodel->getcurrentSemester();
		$getsem = sizeof($semester);

		foreach($getverifie as $row){
			$deptid = $row->sp_deptid;
			$progid = $row->sp_programid;
			$scid   = $row->sp_sccode;
		$sdata = 'sp_smid';
		$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $getsem,'sp_programid' => $progid, 'sp_sccode' => $scid,'sp_deptid' => $deptid);
       		$stuget = $this->commodel->get_distinctrecord('student_program',$sdata,$wharray);
		$stdata['stuget'] = $stuget;

		foreach($stuget as $row1){
			$smid  = $row1->sp_smid;
			$year=date('Y');
				
			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			// move file to directory code for exam admit card
			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/form_verifiesheet';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

                        $desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/form_verifiesheet/'.$scid;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

			$desired_dir = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/form_verifiesheet/'.$scid.'/'.$deptid;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }
		
		$stdata['currentacadyear'] = $this->datesemmodel->getcurrentAcadYear();

		$content = $this->load->view('admin_exam/adminstu_formverifiepdf',$stdata,TRUE);
		$path = 'uploads/SLCMS/adminstudent_exam/'.$currentacadyear.'/form_verifiesheet/'.$scid.'/'.$deptid.'/'.$progid.'.pdf';
		$this->commodel->genpdf($content,$path);
		}
	}
		redirect('adminstuexam/formverifie',$data,TRUE);
	}

	//This function has been created for display the list of department name on the basis of program category
	public function deptlist(){
			$pgcatname = $this->input->post('prgcategoryid');
			$list = $this->commodel->get_listspfic2('program','','prg_deptid','prg_category',$pgcatname,'prg_deptid');
			echo "<option disabled selected>".'Select Department'."</option>";
		foreach($list as $datas): 
			$deptid = $datas->prg_deptid;
			$sarray='dept_name';
			$wharray = array('dept_id' => $deptid);
			$departid = $this->commodel->get_listarry('Department',$sarray,$wharray);
			
			foreach($departid as $row){
      		  		echo "<option  id='exmsche_deptid' value='$deptid'> " .$row->dept_name."</option>";
			}
  		endforeach;
	 }

	//This function has been created for display the list of programme on the basis of program category & department
	public function prgcatdept(){
			$combid = $this->input->post('prgcat_dept');
			//$degree = $this->input->post('degree');
			$parts = explode(',',$combid);
			$sarray='prg_name,prg_id,prg_branch';
			$wharray = array('prg_category' => $parts[0],'prg_deptid' => $parts[1] );
			$proglist = $this->commodel->get_listspficemore('program',$sarray,$wharray);
			echo "<option disabled selected>".'Select Programme'."</option>";
		foreach($proglist as $datas1): 
				echo "<option  id='prog_id' value='$datas1->prg_id'>"."$datas1->prg_name"."( ".$datas1->prg_branch." )"."</option>";
  		endforeach;
	 }

	//This function has been created for display the list of subjects on the basis of program & semester
	public function subjlist(){
			$combid = $this->input->post('prog_sem');
			//$degree = $this->input->post('degree');
			$parts = explode(',',$combid);
			$sarray='sub_id,sub_name';
			$wharray = array('sub_program' => $parts[0],'sub_semester' => $parts[1] );
			$sublist = $this->commodel->get_listspficemore('subject',$sarray,$wharray);
			echo "<option disabled selected>".'Select Subjects'."</option>";
		foreach($sublist as $datas2): 
				echo "<option  id='exmsch_subid' value='$datas2->sub_id'>"."$datas2->sub_name"."</option>";
  		endforeach;
	 }

	//This function has been created for display the list of papers on the basis of program  & semseter & subjects
	public function paperlist(){
			$prgsemsubid = $this->input->post('prog_semsubj');
			$parts = explode(',',$prgsemsubid);
			$sarray='subp_id,subp_name';
			$wharray = array('subp_degree' => $parts[0],'subp_sem' => $parts[1],'subp_sub_id' => $parts[2] );
			$paperlist = $this->commodel->get_listspficemore('subject_paper',$sarray,$wharray);
			echo "<option disabled selected>".'Select Papers'."</option>";
		foreach($paperlist as $datas3): 
				echo "<option  id='exmsch_paperid' value='$datas3->subp_id'>"."$datas3->subp_name"."</option>";
  		endforeach;
	 }


}//end class
