<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Enterence.php
 * @author Sumit Saxena(sumitsesaxena@gmail.com)
 * @author Sharad Singh(Sharad23nov@gmail.com)
 */
class Enterence extends CI_Controller {

	
	function __construct() {
        	parent::__construct();
            	$this->load->model("User_model", "usrmodel");
		$this->load->model('Dependrop_model',"depmodel");
            	$this->load->model("Common_model", "commodel");
            	$this->load->model("Mailsend_model","mailmodel");
		$this->load->model('Login_model',"logmodel");
    	}

 	public function viewadmissionopen() {
        	$this->result = $this->commodel->get_list('admissionopen');
        	$this->logger->write_logmessage("view"," View Admission List", "Admission List details...");
        	$this->logger->write_dblogmessage("view"," View Admission List" , "Admission List record display successfully..." );
       		$this->load->view('enterence/viewadmissionopen',$this->result);
    	}

/*This function has been created for display the list of branch on the basis of program*/
	public function programlist(){
		$pgid = $this->input->post('programcategory');	
	    	$this->depmodel->get_programlist($pgid);
	}	
	public function addadmissionopen() {
         $this->prgcatresult= $this->commodel->get_listspfic2('programcategory','prgcat_id', 'prgcat_name');
        $this->prgresult = $this->commodel->get_listspfic2('program','prg_id', 'prg_name');
        $this->result = $this->commodel->get_list('admissionopen');

        if(isset($_POST['addadmissionopen'])) {
            $this->form_validation->set_rules('academicyear','Academic Year','xss_clean|required');
            $this->form_validation->set_rules('programcategory','Program Category','xss_clean|required');
            $this->form_validation->set_rules('programname','Program Name','trim|xss_clean|required');
	    $this->form_validation->set_rules('enterenceexamfees','Entrance Exam Fees','trim|xss_clean|required');

            $this->form_validation->set_rules('minimumqualification','Minimum Qualification ','trim|xss_clean|required');
            $this->form_validation->set_rules('entranceexampattern','Entrance Exam Pattern ','trim|xss_clean|required');
            $this->form_validation->set_rules('entranceexamdate','Entrance Exam Date','trim|xss_clean|required');
            $this->form_validation->set_rules('startdateofonlineapplication','Start Date Of Online Application','trim|xss_clean|required');
            $this->form_validation->set_rules('lastdateofonlineapplication','Last date Of Online Application','trim|xss_clean|required');
            $this->form_validation->set_rules('lastdateofapplicationreceived','Last date Of Application Received','trim|xss_clean|required');
         }

            if($this->form_validation->run()==TRUE){

             $data = array(
            'admop_acadyear'=>$_POST['academicyear'],
            'admop_prgcat'=>$_POST['programcategory'],
            'admop_prgname_branch'=>$_POST['programname'],
	    'admop_entexam_fees'=>$_POST['enterenceexamfees'],
            'admop_min_qual'=>$_POST['minimumqualification'],
            'admop_entexam_patt'=>$_POST['entranceexampattern'],
            'admop_entexam_date'=> $_POST['entranceexamdate'],
            'admop_startdate'=>$_POST['startdateofonlineapplication'],
            'admop_lastdate'=> $_POST['lastdateofonlineapplication'],
            'admop_app_received'=> $_POST['lastdateofapplicationreceived']
            );

            $addflag = $this->commodel->insertrec('admissionopen',$data);
                if(!$addflag )
                {
                    $this->logger->write_logmessage("error","error in add admission", $admop_id);
                    $this->logger->write_dblogmessage("error","error in add admission", $admop_id);
                    $this->session->set_flashdata('err_message','error in add admission',$admop_id );
                    redirect('enterence/addadmissionopen');
                }
                else{
                    $this->logger->write_logmessage("insert","Add Admission", "admission add  successfully.....".$admop_id);
                    $this->logger->write_dblogmessage("insert","Add Admission", "admission add  successfully.....".$admop_id);
                    $this->session->set_flashdata("success", "admission add successfully...");
                    redirect("enterence/viewadmissionopen");

            }
        }
                $this->load->view('enterence/addadmissionopen');
       }
	
	public function editadmissionopen($id) {
        //$this->prgcatresult= $this->commodel->get_listspfic2('programcategory','prgcat_id', 'prgcat_name');
        //$this->prgresult = $this->commodel->get_listspfic2('program','prg_id', 'prg_name');
        $admoprow=$this->commodel->get_listrow('admissionopen','admop_id', $id);
        if ($admoprow->num_rows() < 1)
        {
            redirect('enterence/viewadmissionopen');
        }
        $admop_data = $admoprow->row();
//print_r($admoprow);
        /* Form fields */

          $data['admop_acadyear'] = array(
              'value' => $admop_data->admop_acadyear,
          );

          $data['admop_prgcat'] = array(
            'name' => 'admop_prgcat',
            //'id' => 'prgcode',
            'maxlength' => '50',
            'size' => '40',
            'value' => $admop_data->admop_prgcat,
            'readonly' => 'readonly'
          );

           $data['admop_prgname_branch'] = array(
            'name' => 'admop_prgname_branch',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('program','prg_name','prg_id',$admop_data->admop_prgname_branch)->prg_name,
        //$this->programname= $this->commodel->get_listmore('program','prg_name,prg_id')
            'readonly' => 'readonly'
          );

            $data['admop_entexam_fees'] = array(
             'name' => 'admop_entexam_fees',
             'maxlength' => '50',
             'size' => '40',
             'value' => $admop_data->admop_entexam_fees,
             //'readonly' => 'readonly'

          );


          $data['admop_min_qual'] = array(
             'name' => 'admop_min_qual',
             'id' => 'admop_min_qual',
             'maxlength' => '50',
             'size' => '40',
             'value' => $admop_data->admop_min_qual,
             //'readonly' => 'readonly'

          );

           $data['admop_entexam_patt'] = array(
             'name' => 'admop_entexam_patt',
             'id' => 'admop_entexam_patt',
             'maxlength' => '50',
             'size' => '40',
             'value' => $admop_data->admop_entexam_patt,
             //'readonly' => 'readonly' 
          );

         $data['admop_entexam_date'] = array(
                'name' => 'admop_entexam_date',
                'id' => 'admop_entexam_date',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_entexam_date,
                );

         $data['admop_startdate'] = array(
                'name' => 'admop_startdate',
                'id' => 'admop_startdate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_startdate,
                );
         $data['admop_lastdate'] = array(
                'name' => 'admop_lastdate',
                'id' => 'admop_lastdate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_lastdate,
                );
         $data['admop_app_received'] = array(
                'name' => 'admop_app_received',
                'id' => 'admop_app_received',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_app_received,
                );
        $data['id'] = $id;
	
	 /*Form Validation*/
                $this->form_validation->set_rules('admop_acadyear','Academic Year','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_prgcat','Progarm Category','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_prgname_branch','Program Name','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_entexam_fees','Entrance Exam Fees','trim|xss_clean|required');
	        $this->form_validation->set_rules('admop_min_qual','Minimum Qualification ','trim|xss_clean|required');
        	$this->form_validation->set_rules('admop_entexam_patt','Entrance Exam Pattern ','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_entexam_date','Entrance Exam Date','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_startdate','Start Date Of Online Application','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_lastdate','Last date Of Online Application','trim|xss_clean|required');
               $this->form_validation->set_rules('admop_app_received','Last date Of Application Received','trim|xss_clean|required');
                
        /* Re-populating form */
	if ($_POST)
        {
            $data['admop_acadyear']['value'] = $this->input->post('admop_acadyear', TRUE);
            $data['admop_prgcat']['value'] = $this->input->post('admop_prgcat', TRUE);
            $data['admop_prgname_branch']['value'] = $this->input->post('admop_prgname_branch', TRUE);
            $data['admop_entexam_fees']['value'] = $this->input->post('admop_entexam_fees', TRUE);
            $data['admop_min_qual']['value'] = $this->input->post('admop_min_qual', TRUE);
            $data['admop_entexam_patt']['value'] = $this->input->post('admop_entexam_patt', TRUE);
            $data['admop_entexam_date']['value'] = $this->input->post('admop_entexam_date', TRUE);
            $data['admop_startdate']['value'] = $this->input->post('admop_startdate', TRUE);
            $data['admop_lastdate']['value'] = $this->input->post('admop_lastdate', TRUE);
	    $data['admop_app_received']['value'] = $this->input->post('admop_app_received', TRUE);

        }
	 if ($this->form_validation->run() ==FALSE )
        {
                $this->session->set_flashdata(validation_errors(), 'error');
                $this->load->view('enterence/editadmissionopen', $data);
        }
	else{
           $acadyear = $this->input->post('admop_acadyear', TRUE);
           $enterenceexamfees = $this->input->post('admop_entexam_fees', TRUE);
           $minimumqualification = $this->input->post('admop_min_qual', TRUE);
           $entranceexampattern = ucwords(strtolower($this->input->post('admop_entexam_patt', TRUE)));
           $entranceexamdate= $this->input->post('admop_entexam_date', TRUE);
           $startdateofonlineapplication = $this->input->post('admop_startdate',TRUE);
           $lastdateofonlineapplication = $this->input->post('admop_lastdate', TRUE);
           $lastdateofapplicationreceived = $this->input->post('admop_app_received', TRUE);
		
	$logmessage = "";
          //  if($admop_data->fm_programid != $programname)
            //    $logmessage = $logmessage ." update program name " .$fm_data->fm_programid. " changed by " .$programname;
            if($admop_data->admop_acadyear != $acadyear)
                $logmessage = $logmessage ." update academic year " .$admop_data->admop_acadyear. " changed by " .$acadyear;
            if($admop_data->admop_entexam_fees != $enterenceexamfees)
                $logmessage = $logmessage ." update enterenceexamfees " .$admop_data->admop_entexam_fees. " changed by " .$enterenceexamfees;
            if($admop_data->admop_min_qual != $minimumqualification)
                $logmessage = $logmessage ." update minimumqualification " .$admop_data->admop_min_qual. " changed by " .$minimumqualification;
            if($admop_data->admop_entexam_patt != $entranceexampattern)
                $logmessage = $logmessage ." update entranceexampattern " .$admop_data->admop_entexam_patt. " changed by " .$entranceexampattern;
            if($admop_data->admop_entexam_date != $entranceexamdate)
                $logmessage = $logmessage ." update entranceexamdate " .$admop_data->admop_entexam_date. " changed by " .$entranceexamdate;
            if($admop_data->admop_startdate != $startdateofonlineapplication)
                $logmessage = $logmessage ." update startdateofonlineapplication " .$admop_data->admop_startdate. " changed by " .$startdateofonlineapplication;
            if($admop_data->admop_lastdate != $lastdateofonlineapplication)
                $logmessage = $logmessage ." update lastdateofonlineapplication " .$admop_data->admop_lastdate. " changed by " .$lastdateofonlineapplication;
            if($admop_data->admop_app_received != $lastdateofapplicationreceived)
                $logmessage = $logmessage ." update lastdateofapplicationreceived " .$admop_data->admop_app_received. " changed by " .$lastdateofapplicationreceived;

	    $update_data = array(
               'admop_acadyear' => $acadyear,
               'admop_entexam_fees'  => $enterenceexamfees,
               'admop_min_qual'  => $minimumqualification,
               'admop_entexam_patt' => $entranceexampattern,
               'admop_entexam_date' => $entranceexamdate,
               'admop_startdate' => $startdateofonlineapplication,
               'admop_lastdate' => $lastdateofonlineapplication,
	       'admop_app_received' => $lastdateofapplicationreceived
             );
//		print_r($update_data);

           $admopflag=$this->commodel->updaterec('admissionopen', $update_data, 'admop_id', $id);
		//	'admissionopen','admop_id', $admop_id);
           if(!$admopflag)
              {
                $this->logger->write_logmessage("error","error in add admission", "Error in  add admission". $logmessage );
                $this->logger->write_dblogmessage("error","Error in add admission ", "Error in add admission update". $logmessage );
                $this->session->set_flashdata('err_message','Error add admission - ' . $logmessage . '.', 'error');
                $this->load->view('enterence/editadmissionopen', $data);
              }
            else{
                $this->logger->write_logmessage("update","Edit Admission", "Add Admission record updated successfully..". $logmessage );
                $this->logger->write_dblogmessage("update","Edit Admission", "Add Admission record updated successfully..". $logmessage );
                $this->session->set_flashdata('success', "Admission record updated successfully...");
                //$this->session->set_flashdata('success', "Admission record updated successfully...".$acadyear. $enterenceexamfees. $minimumqualification. $entranceexampattern. $entranceexamdate .$startdateofonlineapplication. $lastdateofonlineapplication. $lastdateofapplicationreceived. $id.",". print_r($update_data) );
                redirect('enterence/viewadmissionopen');
                }
          }
	}

////////////////////////////////////////////////////////////////////////////////////

	public function important_date(){
		$cdate = date('Y-m-d H:i:s');
		$whdata = array('admop_lastdate >=' => $cdate);
		//print_r($whdata);
		//$this->result = $this->commodel->get_listarry('admissionopen','admop_prgcat,admop_entexam_date','');
		$this->result = $this->commodel->get_listarry('admissionopen','admop_prgcat,admop_entexam_date',$whdata);
                $this->logger->write_logmessage("view"," View Admission List", "Admission List details...");
                $this->logger->write_dblogmessage("view"," View Admission List" , "Admission List record display successfully..." );
		$this->load->view('enterence/imp_date');
	}

	public function important_information(){
		$this->load->view('enterence/imp_information');
	}

	public function apply_online(){
		$this->load->view('enterence/apply_online');
	}

	public function email_address(){
		$this->load->view('enterence/mail_address');
	}

	public function contactus(){
		$this->load->view('enterence/contact_us');
	}

	public function faqs(){
		$this->load->view('enterence/faq_s');
	}

	
	//check for completed admission step
	public function admission_studentstep($applicationno){
		//check the existence of entry in admissionstep table 
		//$this->resstep=$this->commodel->get_listrow('admissionstep','sm_application',$applicationno)->result();
		$this->resstep=$this->commodel->get_listrow('admissionstudent_enterencestep','registration_id',$applicationno)->result();
		//print_r($this->resstep);
		if(!empty($this->resstep)) {

		// if exist then check which step is completed and redirect to incmplete step with appropriate data
			foreach($this->resstep as $rslt){
				$stp1= $rslt->step1_status;
				$stp2= $rslt->step2_status;
				$stp3= $rslt->step3_status;
				$stp4= $rslt->step4_status;
				$stp5= $rslt->step5_status;
			}

			if($stp1 == 0 || $stp1 == NULL){
				
			// set the student role and application number in session
 				$data = [
                        		//'username' => $applicationno,        
					'asreg_id' => $applicationno,
			                //'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
			
			//redirect to step1 for completion
			//$this->logger->write_logmessage("insert", "I am  in data in student step inside step 1 check  -3" . $stp1 );
			redirect('enterence/step_one');
				
			}
			else if($stp2 == 0 || $stp2 == NULL){
				//check the value set in session if not then
				// get the student master id
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				
				// set the student master and application number in session
				$data = [
					//'username' => $applicationno,
                        	       // 'asm_id' => $asmid,
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
			              //  'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step2 for completion
				redirect('enterence/step_two');
			}
			else if($stp3 == 0 || $stp3 == NULL){
				//check the value set in session if not then
				// get the student master id
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				// set the student master and application number in session
					$data = [
					//'username' => $applicationno,
                        	       // 'sm_id' => $this->smid,
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
			                //'id_role' => 3
			                ];
                                $this->session->set_userdata($data);
				// redirect to step3 for completion
				redirect('enterence/step_three');
			}
			else if($stp4 == 0 || $stp4 == NULL){
				//check the value set in session if not then
				// get t'username' => $applicationno,he student master id
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				// set the student master and application number in session
				$data = [
					//'username' => $applicationno,
                        	        //'sm_id' => $this->smid,
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
			               // 'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step4 for completion
				redirect('enterence/step_four');
			}
			else if($stp5 == 0 || $stp5 == NULL){
				//check the value set in session if not then
				// get the student master id
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				// set the student master and application number in session
				$data = [
					//'username' => $applicationno,
                        	        //'sm_id' => $this->smid,
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
			                //'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step5 for completion
				redirect('enterence/step_five');
			}
			else {
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				$data = [
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
			               ];
                               $this->session->set_userdata($data);
				// redirect to step5 for completion
				redirect('enterence/step_five');
			}
			
		}// if empty
		else{ //complete block added by nks
			 
			redirect('enterence/prtadmission_form');
		}
	}
    /*
     *   Method is responsible to fill or verify email,mobile no,date of birth and selected program name 
     *   for filling further information to complete registration process on the basis of verification 
     *   genetrated code.
     */
    public function step_zero() {
	
        $data = array();
        //get program name
        $prg_name=$this->uri->segment(3);
        $data['prg_name'] = $prg_name;
        $msgflag = 0;
        $data['msgflag'] = $msgflag;

        if(isset($_POST['login1']))
        {
            //echo "Test1";
            $applicant_de = $this->input->get_post('prg_name',TRUE);
            $prg_name = $applicant_de;
            $data['prg_name'] = $prg_name;
            $this->form_validation->set_rules('applicantemail','Email Id','trim|required|valid_email');
            $this->form_validation->set_rules('applicantmobile','Mobile No','trim|required|xss_clean|numeric');
            $this->form_validation->set_rules('dateofbirth','Date Of Birth','trim|required|xss_clean');
            $this->form_validation->set_rules('applicantprogram','Program Name','trim|required|xss_clean');
            $this->form_validation->set_rules('applicantvercode','Verification Code','trim|xss_clean|numeric');

            if($this->form_validation->run() == FALSE){
                $this->load->view('enterence/step_zero',$data);
            }
            else
            {
                //echo "True";echo "<br>";
                $applicant_email = $this->input->get_post('applicantemail',TRUE);
                $applicant_mobile = $this->input->get_post('applicantmobile',TRUE);
                $applicant_dob = $this->input->get_post('dateofbirth',TRUE);
                $applicant_program = $this->input->get_post('applicantprogram',TRUE);
                $applicant_vercode = $this->input->get_post('applicantvercode',TRUE);
                $applicant_prgid = $this->input->get_post('prg_name',TRUE);
                $forget_verified = $this->input->get_post('accept',TRUE);
                
                $apply_date = date("Y-m-d h:i:sa");
                $prg_name = $applicant_prgid;
                //echo "<br>";
                //$this->prg_name = $prg_name;
                $data['prg_name'] = $prg_name;
                $generate_code =    $this->commodel->randNum(8);
                $applicant_field = array (
                                'asreg_emailid' => $applicant_email,
                                'asreg_mobile' => $applicant_mobile,
                                'asreg_dob' => $applicant_dob,
                                'asreg_program' => $prg_name);
                //print_r($applicant_field);
                $applicant_where = array('asreg_emailid' => $applicant_email);
                $selected_applicant = $this->commodel->isduplicatemore('admissionstudent_registration',$applicant_field);
                //echo "selected_applicant-->" ;print_r($selected_applicant);echo "<br>";

                //if(($selected_applicant == true) || ($forget_verified == "accept"))
                if(($selected_applicant == true) || ($forget_verified == "accept"))
                {
                    //echo "Test11";echo "<br>";
                    if(!empty($applicant_vercode))
                    {
                        //echo "Test111";echo "<br>";
                        $getvericodedata = $this->commodel->get_listspficemore('admissionstudent_registration','asreg_verificationcode',$applicant_field);
                        foreach ($getvericodedata as $row)
                        {
                            $getvericode = $row->asreg_verificationcode;
                        }
                        if($forget_verified == "accept")
                        {
                            $msg = "You verification code has been resend to you over email.Please fill it to proceed further step.";
                            $this->session->set_flashdata("message",$msg );
                            $this->load->view('enterence/step_zero',$data);
                        }
                        else
                        {
                            
                            if($getvericode == $applicant_vercode)
                            {
			    	$asid= $this->commodel->get_listarry("admissionstudent_registration","asreg_id",$applicant_field);
				foreach($asid as $asregid){
					$asegid= $asregid->asreg_id;
				}
				//put entry in step table
                            	$reginsert = array('registration_id' => $asegid);
				$rinsert = $this->commodel->insertrec('admissionstudent_enterencestep',$reginsert);
                            	//got to next step
				$this->admission_studentstep($asegid);
                                //$this->load->view('enterence/step_one',$applicant_field);
				//redirect('enterence/step_one',$applicant_field);
                            }
                            else
                            {   
                                $msg = "Please fill up correct code sent to your registered email.If forget click on <b>Forget Verification Code .</b> ";
                                $msgflag = 1;
                                $data['msgflag'] = $msgflag;
                                $this->session->set_flashdata("message",$msg );
                                $this->load->view('enterence/step_zero',$data);

                            }
                        }
                    }
                    else
                    {
                        //echo "Test112";echo "<br>";
                        $data['applicant_email'] = $applicant_email;
                        $msg = "Emailid and Mobile no already registered,Please write the verification code sent to you over email for further step. ";
                        $this->session->set_flashdata("message",$msg );
                        $this->load->view('enterence/step_zero',$data);
                    }
                }
                else
                {
                    //echo "Test12";
                    $generate_code = $this->commodel->randNum(8);
                    $currentdate = date("Y-m-d h:i:sa");
                    $applicant_field = array (
                                'asreg_emailid' => $applicant_email,
                                'asreg_mobile' => $applicant_mobile,
                                'asreg_dob' => $applicant_dob,
                                'asreg_program' => $prg_name,
                                'asreg_verificationcode' => $generate_code,
                                'asreg_verificationdate' => $currentdate
                    );
                   // $applicantdetail_insert = $this->commodel->insertrec('admissionstudent_registration',$applicant_field);
			$applicantdetail_insert = $this->db->insert('admissionstudent_registration',$applicant_field);
			$regid = $this->db->insert_id();
			$session_id = array(
					'asreg_id'=> $regid,
					);
			$this->session->set_userdata($session_id);
                    if(!$applicantdetail_insert)
                    {
                        $notconfmes = "There might be some issue related to addmission system,Please contact to the examination controller over email and phone number";
                        $this->session->set_flashdata('message',$notconfmes);
                        $this->load->view('enterence/step_zero',$data);
                        //write message
                    }
                    else
                    {
                        $confmes = "Your email and mobile no. registered successfully,Verification code has been sent to you over email.Please enter verification code to proceed for further step.";
                        $this->session->set_flashdata('message',$confmes);
                        $this->load->model("Mailsend_model","mailmodel");
                        $subject = "Registered Successfully.";
                        $message = "Your are registered successfully,Verification code for further step is ".$generate_code.".";
                        $mails=$this->mailmodel->mailsnd($applicant_email,$subject,$message,'');
                        $this->load->view('enterence/step_zero',$data);

                    }
                }

            }
        }
        else
        {
            //echo "Test2";
            $this->load->view('enterence/step_zero',$data);
        }

    }

	public function step_one(){
		  $data = array();
        //get program name
        $prg_name=$this->uri->segment(3);
        $data['prg_name'] = $prg_name;
        $msgflag = 0;
        $data['msgflag'] = $msgflag;

		$regisid = $this->session->userdata['asreg_id'];
		
		$rsdata1 = array('step1_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('enterence/step_two');
		}

		$email = $this->commodel->get_listspfic1('admissionstudent_registration','asreg_emailid','asreg_id',$regisid)->asreg_emailid;		
		$data['email'] = $email;
		$mobile = $this->commodel->get_listspfic1('admissionstudent_registration','asreg_mobile','asreg_id',$regisid)->asreg_mobile;		
		$data['mobile'] = $mobile;
		$dob = $this->commodel->get_listspfic1('admissionstudent_registration','asreg_dob','asreg_id',$regisid)->asreg_dob;		
		$data['dob'] = $dob;
		$prgid = $this->commodel->get_listspfic1('admissionstudent_registration','asreg_program','asreg_id',$regisid)->asreg_program;	
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';	
		$data['prgname'] = $prgname;
		$prgcat = $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgid)->prg_category;
		$data['prgcat'] = $prgcat;
		$sccode = $this->commodel->get_listspfic1('program','prg_scid','prg_id',$prgid)->prg_scid;
		$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$sccode)->sc_name;
		$data['scname'] = $scname;
		$cdate = date('Y-m-d');
		$age = $cdate-$dob;
		$data['age'] = $age;

		$this->scresult = $this->commodel->get_list('study_center');
		$this->prgname  = $this->commodel->get_listmore('program','prg_name,prg_id,prg_branch');		
		$this->examcenter = $this->commodel->get_listmore('admissionstudent_enterenceexamcenter','eec_name,eec_city,eec_id');

		//send student enetrance data in table
		if(isset($_POST['addstudent'])) {
			//personnel detail validation code
			$this->form_validation->set_rules('entcouname','Course name','trim|xss_clean|required');
          		$this->form_validation->set_rules('entcenter','Study Center','trim|xss_clean|required');
           		$this->form_validation->set_rules('entexamcenter','Enterance exam center','trim|xss_clean|required');
			$this->form_validation->set_rules('entappliname','Applicant name','trim|xss_clean|required');
           		$this->form_validation->set_rules('entemail','Email','trim|xss_clean|required');
			$this->form_validation->set_rules('entmobile','Mobile Number','trim|xss_clean|required|numeric');
			$this->form_validation->set_rules('entgender','Gender','trim|xss_clean|required');
	   		$this->form_validation->set_rules('entdob','Date of birth','trim|xss_clean|required');
			//$this->form_validation->set_rules('entage','Age','trim|xss_clean|required');
          		$this->form_validation->set_rules('entcate','Category','trim|xss_clean|required');	
			$this->form_validation->set_rules('entnationality','Nationality','trim|xss_clean|required');
          		$this->form_validation->set_rules('entdisability','Disability','trim|xss_clean|required');
           		$this->form_validation->set_rules('entreligion','Religion','trim|xss_clean|required');
			$this->form_validation->set_rules('basic[]','Reservation type','trim|required');

			//address detail validation code
           		$this->form_validation->set_rules('entpstreet','Parmanant address street','trim|xss_clean|required');
			$this->form_validation->set_rules('entpcity','Parmanant address city','trim|xss_clean|required');
			$this->form_validation->set_rules('entpstate','Parmanant address state','trim|xss_clean|required');
	   		$this->form_validation->set_rules('entpcode','Parmanant address postal code','trim|xss_clean|required|numeric');
			$this->form_validation->set_rules('entpcountry','Parmanant address country','trim|xss_clean|required');
          		
			$this->form_validation->set_rules('entcostreet','Correspondence address street','trim|xss_clean');
			$this->form_validation->set_rules('entcocity','Correspondence address city','trim|xss_clean');
			$this->form_validation->set_rules('entcostate','Correspondence address state','trim|xss_clean');
	   		$this->form_validation->set_rules('entcocode','Correspondence address postal code','trim|xss_clean|numeric');
			$this->form_validation->set_rules('entcocountry','Correspondence address country','trim|xss_clean');

			//family detail validation code	
			$this->form_validation->set_rules('entfathername','Father name','trim|xss_clean|required');
			$this->form_validation->set_rules('entmothername','Mother name','trim|xss_clean|required');
			$this->form_validation->set_rules('entfathermono','Father mobile no.','trim|xss_clean|required');
	   		$this->form_validation->set_rules('entmothermono','Mother mobile no.','trim|xss_clean|required');
			$this->form_validation->set_rules('entfatheroccu','Father occupation','trim|xss_clean|required');
			$this->form_validation->set_rules('entmotheroccu','Mother occupation','trim|xss_clean|required');

			if($this->form_validation->run() == TRUE)
			{
				$stu_reserve = '';
				//$reserve = $_POST['basic'];
				foreach($_POST['basic'] as $row){
					$stu_reserve .= $row . ', ';
				}
				//$stu_reserve = substr($stu_reserve, 0, -2);
				//add personnel detail in admissionstudent_master
		 		$pdetail = array(
					'asm_coursename'		=>      $prgid,
					'asm_userid'			=>	$regisid,	
                			'asm_sccode'  		    	=>	$_POST['entcenter'],
                			'asm_enterenceexamcenter'  	=>	$_POST['entexamcenter'],
                			'asm_fname'   			=>	$_POST['entappliname'],
					'asm_dob'   			=>	$_POST['entdob'],
					'asm_age'			=>	$_POST['entage'],
                			'asm_mobile'   			=>	$_POST['entmobile'],
                			'asm_email'  			=>	$_POST['entemail'],
                			'asm_gender'   			=>	$_POST['entgender'],
					'asm_mstatus'   		=>	$_POST['entmaritial'],
					'asm_caste'			=>	$_POST['entcate'],
					'asm_nationality'		=>	$_POST['entnationality'],
					'asm_phyhandicaped'		=>	$_POST['entdisability'],
					'asm_reservationtype'		=>	$stu_reserve,
					'asm_religion'			=>	$_POST['entreligion']
               		 	);
	 			
				$detail=$this->db->insert('admissionstudent_master', $pdetail);	
				$insertid = $this->db->insert_id();
				$this->logger->write_dblogmessage("insert", "Student admission personnel record add successfully.");
                   		$this->session->set_flashdata("success", "Student admission personnel record add successfully.");
				$session_data = array(
					'asm_id'=> $insertid,
					);
				$this->session->set_userdata($session_data);
			
				//insert asmid into student fees
				$stufees = array(
					'asfee_amid'		=>	$insertid,
					'asfee_aprgid'		=>	$prgid
                		);	
				$this->db->insert('admissionstudent_fees',$stufees);

				//insert asmid into student upload data
				$stuupload = array(
					'asupd_asmid'		=>	$insertid,
                		);	
				$this->db->insert('admissionstudent_uploaddata',$stuupload);
				
				$centerasmid = array(
					'ca_asmid'		=>	$insertid,
                		);	
				$this->db->insert('admissionstudent_centerallocation',$centerasmid);
				/*//insert asmid into student student education
				$stuedu = array(
					'asedu_asmid'		=>	$insertid,
                		);	
				$this->db->insert('admissionstudent_education',$stuedu);
		
				//insert asmid into student student enterence exam
				$stuentex = array(
					'aseex_asmid'		=>	$insertid,
                		);	
				$this->db->insert('admissionstudent_entrance_exam',$stuentex);
				
				//insert asmid into student student student employement
				$stuemp = array(
					'asemp_asmid'		=>	$insertid,
                		);	
				$this->db->insert('admissionstudent_employment',$stuemp);*/
			
		
				//add address detail in admissionstudent_master
		 		$addetail = array(
					'aspar_asmid'		=>      $insertid,
                			'aspar_paddress'  	=>	$_POST['entpstreet'],
                			'aspar_pcity'  		=>	$_POST['entpcity'],
                			'aspar_pstate'   	=>	$_POST['entpstate'],
					'aspar_pcountry'   	=>	$_POST['entpcountry'],
                			'aspar_ppincode'   	=>	$_POST['entpcode'],

                			'aspar_caddress'  	=>	$_POST['entcostreet'],
                			'aspar_ccity'   	=>	$_POST['entcocity'],
					'aspar_cstate'   	=>	$_POST['entcostate'],
					'aspar_ccountry'	=>	$_POST['entcocountry'],
					'aspar_cpincode'	=>	$_POST['entcocode'],

					'aspar_fathername'  	=>	$_POST['entfathername'],
                			'aspar_fatherphoneno'   =>	$_POST['entfathermono'],
					'aspar_fatheroccupation'=>	$_POST['entfatheroccu'],
					'aspar_mothername'	=>	$_POST['entmothername'],
					'aspar_motherphoneno'	=>	$_POST['entmothermono'],
					'aspar_motheroccupation'=>	$_POST['entmotheroccu']
					
               		 	);
	 		
				$record = $this->commodel->insertrec('admissionstudent_parent', $addetail);
				$cdate = date('Y-m-d H:i');
				$step1 = array(
					//'registration_id'	=>	$regisid,
					'admission_masterid'	=>	$insertid,
					'step1_status'   	=>	1,
                			'step1_date'  		=>	$cdate
               			 );
				//$this->db->insert('admissionstudent_enterencestep',$step1);
				//$this->logger->write_logmessage("update", "Admisssion Step_one update.");
                    		//$this->logger->write_dblogmessage("update", "Admission Step_one update.");
				$updatst1 = $this->commodel->updaterec('admissionstudent_enterencestep', $step1,'registration_id',$regisid);
				$this->logger->write_logmessage("update", "Admisssion Step_one update.");
                    		$this->logger->write_dblogmessage("update", "Admission Step_one update.");				
				if(!$detail)
				{
                   			$this->logger->write_logmessage("insert", "Student admission address or parent record not add successfully." );
                    			$this->logger->write_dblogmessage("insert", "Student admission address or parent record not add successfully." );
                   	 		$this->session->set_flashdata("err_message",'Error to add admission detail' );
                		}
                		else{
                    			$this->logger->write_logmessage("insert","Student admission address or parent record add successfully.");
                    			$this->logger->write_dblogmessage("insert", "Student admission address or parent record add successfully.");
                   			$this->session->set_flashdata("success", "Your admission personal detail add successfully.");
					redirect('enterence/step_two');
                		}
					
			}//if valdation close

		
		}//if post close

		$this->load->view('enterence/step_one',$data);
	}
    
	//This function check duplicate roll number  		   
    public function rollnoexist($rollno) {
        $is_exist = $this->commodel->isduplicate('admissionstudent_entrance_exam','aseex_rollno',$rollno);
	//print_r($is_exist);
        if ($is_exist)
        {
            $this->form_validation->set_message('rollnoexist','Roll number'." " .$rollno. " ".'is already exist check your roll number again.');
            return false;
        }
        else {
            return true;
        }
    }

	public function step_two(){
		if(empty($this->session->userdata('asm_id'))) {
	        	$this->session->set_flashdata('err_message', 'You don\'t have access!');
			redirect('welcome');
        	}
		$asmid = $this->session->userdata['asm_id'];

		$regisid = $this->session->userdata['asreg_id'];
		$rsdata = array('step1_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('welcome');	
		}

		$rsdata1 = array('step2_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('enterence/step_three');
		}

		if(isset($_POST['addeducation'])){

			$this->form_validation->set_rules('Hcname','High school name','trim|xss_clean|required');
          		$this->form_validation->set_rules('Hsubject','High school subject','trim|xss_clean|required');
           		$this->form_validation->set_rules('Hboard','High school board','trim|xss_clean|required');
           		$this->form_validation->set_rules('Hyear','High school year','trim|xss_clean|numeric|required');
	   		$this->form_validation->set_rules('Hpassed','High school passed/appeared','trim|xss_clean|required');
			$this->form_validation->set_rules('Hmobtain','High school obtained marks','trim|xss_clean|required');
           		$this->form_validation->set_rules('Hmmarks','High school  maximum marks','trim|xss_clean|required|numeric');
           		$this->form_validation->set_rules('Hpercentage','High school percentage','trim|xss_clean|required');
	   		$this->form_validation->set_rules('Hinstitute','High school institute/university','trim|xss_clean|required');

	    		$this->form_validation->set_rules('Icname','Intermediate school name','trim|xss_clean|required');
           		$this->form_validation->set_rules('Isubject','Intermediate school subject','trim|xss_clean|required');
           		$this->form_validation->set_rules('Iboard','Intermediate school board','trim|xss_clean|required');
          		$this->form_validation->set_rules('Iyear','Intermediate school year','trim|xss_clean|numeric|required');
	   		$this->form_validation->set_rules('Ipassed','Intermediate school passed/appeared','trim|xss_clean|required');
			$this->form_validation->set_rules('Imobtain','Intermediate school obtained marks','trim|xss_clean|required|numeric');
           		$this->form_validation->set_rules('Immarks','Intermediate school  maximum marks','trim|xss_clean|required|numeric');
           		$this->form_validation->set_rules('Ipercentage','Intermediate school percentage','trim|xss_clean|required');
	   		$this->form_validation->set_rules('Iinstitute','Intermediate school institute/university','trim|xss_clean|required');

	    		$this->form_validation->set_rules('Gcname','Graduation college name','trim|xss_clean');
           		$this->form_validation->set_rules('Gsubject','Graduation  subject','trim|xss_clean');
           		$this->form_validation->set_rules('Gboard','Graduation  board','trim|xss_clean');
           		$this->form_validation->set_rules('Gyear','Graduation year','trim|xss_clean|numeric');
	   		$this->form_validation->set_rules('Gpassed','Graduation passed/appeared','trim|xss_clean');
			$this->form_validation->set_rules('Gmobtain','Graduation obtained marks','trim|xss_clean|numeric');
           		$this->form_validation->set_rules('Gmmarks','Graduation  maximum marks','trim|xss_clean|numeric');
           		$this->form_validation->set_rules('Gpercentage','Graduation percentage','trim|xss_clean');
	   		$this->form_validation->set_rules('Ginstitute','Graduation institute/university','trim|xss_clean');


	    		$this->form_validation->set_rules('Pcname','Post graduation college name','trim|xss_clean');
           		$this->form_validation->set_rules('Psubject','Post graduation subject','trim|xss_clean');
           		$this->form_validation->set_rules('Pboard','Post graduation board','trim|xss_clean');
           		$this->form_validation->set_rules('Pyear','Post graduation year','trim|xss_clean|numeric');
	   		$this->form_validation->set_rules('Ppassed','Post graduation passed/appeared','trim|xss_clean');
			$this->form_validation->set_rules('Pmobtain','Post graduation obtained marks','trim|xss_clean|numeric');
           		$this->form_validation->set_rules('Pmmarks','Post graduation  maximum marks','trim|xss_clean|numeric');
           		$this->form_validation->set_rules('Ppercentage','Post graduation percentage','trim|xss_clean');
	   		$this->form_validation->set_rules('Pinstitute','Post graduation institute/university','trim|xss_clean');

	   		$this->form_validation->set_rules('Acname','Any other qualificatin name','trim|xss_clean');
           		$this->form_validation->set_rules('Asubject','Any other qualificatin subject','trim|xss_clean');
           		$this->form_validation->set_rules('Aboard','Any other qualificatin board','trim|xss_clean');
           		$this->form_validation->set_rules('Ayear','Any other qualificatin year','trim|xss_clean|numeric');
	   		$this->form_validation->set_rules('Apassed','Any other qualificatin passed/appeared','trim|xss_clean');
			$this->form_validation->set_rules('Amobtain','Any other qualificatin obtained marks','trim|xss_clean|numeric');
           		$this->form_validation->set_rules('Ammarks','Any other qualificatin  maximum marks','trim|xss_clean|numeric');
           		$this->form_validation->set_rules('Apercentage','Any other qualificatin percentage','trim|xss_clean');
	   		$this->form_validation->set_rules('Ainstitute','Any other qualificatin institute/university','trim|xss_clean');

			//$this->form_validation->set_rules('eduexname1','Enterance Exam Detail Not Selected','trim|xss_clean|required');
			/*for($j=1; $j<=9; $j++){
				$this->form_validation->set_rules('eduexname'.$j,'Enterance exam name','trim|xss_clean');
           			$this->form_validation->set_rules('edurollno'.$j.'1','Enterance exam roll no','trim|xss_clean|numeric');
           			$this->form_validation->set_rules('edurank'.$j.'2','Enterance exam rank','trim|xss_clean');
           			$this->form_validation->set_rules('eduscore'.$j.'3','Enterance exam score','trim|xss_clean|numeric');
	   			$this->form_validation->set_rules('edustate'.$j.'4','Enterance exam state','trim|xss_clean');
			}

			for($i=1; $i<=2; $i++){
				$this->form_validation->set_rules('eduuni'.$i.'1','Employee institute/university','trim|xss_clean');
           			$this->form_validation->set_rules('edupost'.$i.'2','Employee post name','trim|xss_clean');
           			$this->form_validation->set_rules('edupay'.$i.'3','Employee grade pay','trim|xss_clean|numeric');
           			$this->form_validation->set_rules('eduappoint'.$i.'4','Employee nature of appointment','trim|xss_clean|numeric');
	   			$this->form_validation->set_rules('edujoin'.$i.'5','Employee date of joining','trim|xss_clean');
				$this->form_validation->set_rules('eduremark'.$i.'6','Employee remark','trim|xss_clean|numeric');
           			$this->form_validation->set_rules('eduexpie'.$i.'7','Employee previous expierience','trim|xss_clean');

			}*/

			if($this->form_validation->run() == TRUE){

				$Hedu = array(
					'asedu_asmid'		=>	$asmid,
                			'asedu_class'   	=>	$_POST['Hcname'],
                			'asedu_subject'  	=>	$_POST['Hsubject'],
                			'asedu_board'  		=>	$_POST['Hboard'],
                			'asedu_passingyear'   	=>	$_POST['Hyear'],
					'asedu_resultstatus'   	=>	$_POST['Hpassed'],
					'asedu_institution'   	=>	$_POST['Hinstitute'],
					'asedu_marksobtained'   =>	$_POST['Hmobtain'],
					'asedu_maxmarks'   	=>	$_POST['Hmmarks'],
					'asedu_percentage'   	=>	$_POST['Hpercentage'],
					//'asedu_percentage'   	=>	$_POST['eduugc_net']
                		);	
				//print_r($Hedu);
				$this->commodel->insertrec('admissionstudent_education', $Hedu);
				
				$intn =$_POST['Icname'];
				$Iedu = array(
					'asedu_asmid'		=>	$asmid,
					'asedu_class'   	=>	$_POST['Icname'],
                			'asedu_subject'  	=>	$_POST['Isubject'],
                			'asedu_board'  		=>	$_POST['Iboard'],
                			'asedu_passingyear'   	=>	$_POST['Iyear'],
					'asedu_resultstatus'   	=>	$_POST['Ipassed'],
					'asedu_institution'   	=>	$_POST['Iinstitute'],
					'asedu_marksobtained'   =>	$_POST['Imobtain'],
					'asedu_maxmarks'   	=>	$_POST['Immarks'],
					'asedu_percentage'   	=>	$_POST['Ipercentage']
					//'asedu_percentage'   	=>	$_POST['eduugc_net']
             		  	 );
				//print_r($Iedu);
				if(!empty($intn)){
					$this->commodel->insertrec('admissionstudent_education', $Iedu);
				}

				$gran =$_POST['Gcname'];
				$Gedu = array(
					'asedu_asmid'		=>	$asmid,
					'asedu_class'   	=>	$_POST['Gcname'],
                			'asedu_subject'  	=>	$_POST['Gsubject'],
                			'asedu_board'  		=>	$_POST['Gboard'],
                			'asedu_passingyear'   	=>	$_POST['Gyear'],
					'asedu_resultstatus'   	=>	$_POST['Gpassed'],
					'asedu_institution'   	=>	$_POST['Ginstitute'],
					'asedu_marksobtained'   =>	$_POST['Gmobtain'],
					'asedu_maxmarks'   	=>	$_POST['Gmmarks'],
					'asedu_percentage'   	=>	$_POST['Gpercentage']
					//'asedu_percentage'   	=>	$_POST['eduugc_net']
               				 );
				//print_r($Gedu);
				if(!empty($gran)){
					$this->commodel->insertrec('admissionstudent_education', $Gedu);
				}

				$pron =$_POST['Pcname'];
					$Pgedu = array(
					'asedu_asmid'		=>	$asmid,
					'asedu_class'   	=>	$_POST['Pcname'],
                			'asedu_subject'  	=>	$_POST['Psubject'],
                			'asedu_board'  		=>	$_POST['Pboard'],
                			'asedu_passingyear'   	=>	$_POST['Pyear'],
					'asedu_resultstatus'   	=>	$_POST['Ppassed'],
					'asedu_institution'   	=>	$_POST['Pinstitute'],

					'asedu_marksobtained'   =>	$_POST['Pmobtain'],
					'asedu_maxmarks'   	=>	$_POST['Pmmarks'],
					'asedu_percentage'   	=>	$_POST['Ppercentage']
					//'asedu_percentage'   	=>	$_POST['eduugc_net']
                		);
				//print_r($Pgedu);
				if(!empty($pron)){
					$this->commodel->insertrec('admissionstudent_education', $Pgedu);
				}

				$anyn =$_POST['Acname'];
				$Aedu = array(
					'asedu_asmid'		=>	$asmid,
					'asedu_class'   	=>	$_POST['Acname'],
                			'asedu_subject'  	=>	$_POST['Asubject'],
                			'asedu_board'  		=>	$_POST['Aboard'],
                			'asedu_passingyear'   	=>	$_POST['Ayear'],
					'asedu_resultstatus'   	=>	$_POST['Apassed'],
					'asedu_institution'   	=>	$_POST['Ainstitute'],

					'asedu_marksobtained'   =>	$_POST['Amobtain'],
					'asedu_maxmarks'   	=>	$_POST['Ammarks'],
					'asedu_percentage'   	=>	$_POST['Apercentage']
					//'asedu_percentage'   	=>	$_POST['eduugc_net']
              			  );
				//print_r($Aedu);
				if(!empty($anyn)){
					$this->commodel->insertrec('admissionstudent_education', $Aedu);
				}
			
				//add student exam enterance detail 
				
					for($j=1; $j<=9; $j++){
							//$exname = $_POST['eduexname'.$i.$j];
							$entx = array(
								'aseex_asmid' 		=> $asmid,
								'aseex_examname' 	=> $_POST['eduexname'.$j],
								'aseex_rollno' 		=> $_POST['edurollno'.$j.'1'],
								'aseex_rank' 		=> $_POST['edurank'.$j.'2'],
								'aseex_score' 		=> $_POST['eduscore'.$j.'3'],
								'aseex_state' 		=> $_POST['edustate'.$j.'4']
								//'aseex_subject' 	=> $_POST['d'.$i.$j.'3'],
								//'aseex_passingyear' 	=> $_POST['d'.$i.$j.'4'],
							);
							if(!empty($_POST['eduexname'.$j])){
								$this->commodel->insertrec('admissionstudent_entrance_exam',$entx);
								$this->logger->write_logmessage("insert", "Student enterance exam detail add.");
                    						$this->logger->write_dblogmessage("insert", "Student enterance exam detail add.");
							}
							else{break;}
					}//for close
				
				
				//add student employment detail
				for($i=1; $i<=2; $i++){
				
					$emp = array(
						'asemp_asmid'		  =>	$asmid,
						'asemp_officename'   	  =>	$_POST['eduuni'.$i.'1'],
                				'asemp_post'  		  =>	$_POST['edupost'.$i.'2'],
                				'asemp_pay'  		  =>	$_POST['edupay'.$i.'3'],
						//'asemp_grade'		  =>	$_POST['Pboard'],
                				'asemp_appoinmentnature ' =>	$_POST['eduappoint'.$i.'4'],
						'asemp_doj'   		  =>	$_POST['edujoin'.$i.'5'],
						//'asemp_dol'   	  =>	$_POST['Pmobtain'],
						'asemp_remarks'   	  =>	$_POST['eduremark'.$i.'6'],
						'asemp_experience'   	  =>	$_POST['eduexpie'.$i.'7']
					
                			);
				
					if(!empty($_POST['eduuni'.$i.'1'])){
						$employe = $this->commodel->insertrec('admissionstudent_employment',$emp);
					}
					else{break;}
				
				}//for close
				$cdate = date('Y-m-d H:i');
				$step2 = array(
					'step2_status'   	=>	1,
                			'step2_date'  		=>	$cdate
               			 );
				$updatst2 = $this->commodel->updaterec('admissionstudent_enterencestep', $step2,'admission_masterid',$asmid);
				$this->logger->write_logmessage("update", "Admisssion Step_two update.");
                    		$this->logger->write_dblogmessage("update", "Admission Step_two update.");				
				if(!$updatst2)
				{
                   			$this->logger->write_logmessage("insert", "Student admission education and employment record not add successfully." );
                    			$this->logger->write_dblogmessage("insert", "Student admission education and employment record not add successfully." );
                   	 		$this->session->set_flashdata("err_message",'Error to add admission education detail');
					redirect('enterence/step_two');
                		}
                		else{
                    			$this->logger->write_logmessage("insert","Student admission education and employment record add successfully.");
                    			$this->logger->write_dblogmessage("insert", "Student admission education and employment record add successfully.");
                   			$this->session->set_flashdata("success", "Your admission education detail add successfully.");
					redirect('enterence/step_three');
                		}	
			}//if validation close
					
		}//if post close

	$this->load->view('enterence/step_two');
	}

	public function step_three(){
		if(empty($this->session->userdata('asm_id'))) {
	        	$this->session->set_flashdata('err_message', 'You don\'t have access!');
			redirect('welcome');
        	}	
		$id = $this->session->userdata['asm_id'];

		$regisid = $this->session->userdata['asreg_id'];
		$rsdata = array('step2_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process');
			redirect('welcome');	
		}

		$rsdata1 = array('step3_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('enterence/step_four');
		}

		if(isset($_POST['fileSubmit'])){
			$filerrors = array();
			$filesuccess = array();
				$j=0;$k=0;
				$extflag=false;
				$sizeflag=false;
				// put code for photo, sign, noc, decla
				if((isset($_FILES['photo'])) && (isset($_FILES['sign'])) && (isset($_FILES['files'])))	
				{

					// get the files for photo
					$file_photo = $_FILES['photo']['name'];
					$file_size_photo =$_FILES['photo']['size'];
					$file_tmp_photo = $_FILES['photo']['tmp_name'];
					$file_type_photo =$_FILES['photo']['type'];

					//get the files for sign
					$file_sign = $_FILES['sign']['name'];
					$file_size_sign =$_FILES['sign']['size'];
					$file_tmp_sign =$_FILES['sign']['tmp_name'];
					$file_type_sign =$_FILES['sign']['type'];

					//check files extension 	
					$file_ext1 = strtolower(end((explode('.',$_FILES['photo']['name']))));
					$file_ext2 = strtolower(end((explode('.',$_FILES['sign']['name']))));
      					$extensions= array("jpeg","jpg","png","gif","pdf");
      					if(in_array($file_ext1,$extensions) === false){
						$filerrors[$j] = " Photo is required and extension should be jpeg or jpg or png. ";
						$extflag=true;
						$j++;
					}

					if(in_array($file_ext2,$extensions) === false){
						$filerrors[$j] = " Signature required and extension should be jpeg or jpg or png. ";
						$extflag=true;
						$j++;
					}

					//check file sizes
					if($file_size_photo > 102400){
						$filerrors[$j] = "Photo is required and size should be less than 100kb. ";//.$file_size_photo;
						$sizeflag = true;
						$j++;
					}

					if($file_size_sign > 102400){
						$filerrors[$j] = "Signature required and size should be less than 100kb. ";
						$sizeflag = true;
						$j++;
					}

					if ((!$extflag) && (!$sizeflag)){

						//move file to directory code for photo
	                                   	$desired_dir = 'uploads/SLCMS/enterence/'.$id;
						// Create directory if it does not exist
						if(is_dir($desired_dir)==false){
                                                        mkdir("$desired_dir", 0700);    
                                                }
						move_uploaded_file($file_tmp_photo,"$desired_dir/".$id."Photo");
						move_uploaded_file($file_tmp_sign,"$desired_dir/".$id."Signature");
                                                $insertPhoto = array(
                                                            'asupd_asmid'           => $id,
                                                            'asupd_photo'           => $id."Photo",      
                                                            'asupd_signature'       => $id."Signature"  
                                                 );
                                                //$query = $this->db->insert('admissionstudent_uploaddata',$insertPhoto);
						$this->commodel->updaterec('admissionstudent_uploaddata',$insertPhoto,'asupd_asmid',$id);
						$filesuccess[$k] = " Photo has been upload successfully. ";
						$k++;
						$filesuccess[$k] = " Signature has been uploaded successfully. ";
						$k++;
                                                $this->logger->write_logmessage("update", "Admission step 3 photo insert in admissionstudent_uploaddata table.");
                                                $this->logger->write_dblogmessage("update", "Admissiom step 3 photo insert in admissionstudent_uploaddata table." );

					}
					else{

						// for failure case
						//set the error
						$ferror="";
						foreach($filerrors as $error){
							$ferror = $ferror ."</br>". $error;
							
						}

                        			//display error of array
                        			//put ferror in log file.
                        			$this->session->set_flashdata('err_message', $ferror);
						//redirect the upload page
						redirect('enterence/step_three');
					}
				$i = 0;$l=0;
				foreach($_FILES['files']['tmp_name'] as $key => $tmp_name){
				//if(isset($_FILES['files'])){
					$file_name = $_FILES['files']['name'][$key];
					$file_size =$_FILES['files']['size'][$key];
					$file_tmp =$_FILES['files']['tmp_name'][$key];
					$file_type=$_FILES['files']['type'][$key];
					//$ferror="";
					if((empty($file_name) )&& ($l<1)){
						$filerrors[$j] = " At least one enclosures file is required. ";
						$l++; $j++;
					}
					//check enclosure extension	
					$file_ext = strtolower(end((explode('.',$file_name))));
      					$extensions= array("jpeg","jpg","png","pdf");
      					if(in_array($file_ext,$extensions) === false){
						$filerrors[$j] = " Enclosures file ".$file_name." extension should be jpeg or jpg or png or pdf. ";
						$extflag=true;
						$j++;
						//foreach($filerrors as $error){
						//	$ferror = $ferror ."</br>". $error;
						//}
                        			//$this->session->set_flashdata('error', $ferror);
						//redirect('enterence/step_three');*/
						//$this->session->set_flashdata('error', " Enclosures file ".$file_name." extension should be jpeg or jpg or png. ");
						//redirect('enterence/step_three');
						//$this->load->view('enterence/step_three');
					}
					//check enclosure upload size	
       				 	if($file_size >512000){
						$filerrors[$j] = " Enclosure file ". $file_name." size should be less than 500kb.";
						$sizeflag = true;
						$j++;
						//foreach($filerrors as $error){
						//	$ferror = $ferror ."</br>". $error;
						//}
                        			//$this->session->set_flashdata('error', $ferror);
						//redirect('enterence/step_three');*/
						//$this->session->set_flashdata('error', " Enclosure file ". $file_name." size should be less than 500kb.");
						//redirect('enterence/step_three');
						//$this->load->view('enterence/step_three');
       					 }
					
					$ii = $i + 1;
						if ((!$extflag) && (!$sizeflag)){

							//move file to directory code for photo
	                                        	$desired_dir = 'uploads/SLCMS/enterence/'.$id;
							// Create directory if it does not exist
							if(is_dir($desired_dir)==false){
                                                       	 	mkdir("$desired_dir", 0700);    
                                              		 }
							move_uploaded_file($file_tmp,"$desired_dir/".$id."enclosure".$ii);
							$update = array(
		                					'asupd_enclosure'.$ii      => $id."enclosure".$ii,
	           	     					);
							
								$this->commodel->updaterec('admissionstudent_uploaddata',$update,'asupd_asmid',$id);
								$filesuccess[$k] = " Enclosure file ". $file_name." has been uploaded successfully.";
								$k++;
								$this->logger->write_logmessage("update", "Admission step 3 data update in admissionstudent_uploaddata table.");
                    						$this->logger->write_dblogmessage("update", "Admission step 3 data update in admissionstudent_uploaddata table.");
						 }else{
							// for failure case
						}
							
					$i++;
		    		 }//foreach close
				//set the error
					$ferror='';
					if(!empty($filerrors)){
						$filerrors[$j] = " Kindly upload correct files again.";
						$j++;
						$ferror = $ferror . "<br> The error in following files.";
						foreach($filerrors as $error){
							$ferror = $ferror ."</br>". $error;	
						}
						$this->session->set_flashdata('err_message', $ferror);
						redirect('enterence/step_three');
					}	
				//update admissionstep step3 table
					$cdate = date('Y-m-d H:i');
 					$step3 = array(
						'step3_status'   	=>	1,
                				'step3_date'  		=>	$cdate
					);
					$updst3 = $this->commodel->updaterec('admissionstudent_enterencestep', $step3,'admission_masterid',$id);
					$this->logger->write_logmessage("update", "Admisssion Step_three update.");
                    			$this->logger->write_dblogmessage("update", "Admission Step_three update.");
					
					$fsuccess ='';
					if(!empty($filesuccess)){
						$fsuccess = $fsuccess . "<br> The uploaded files are.";
						foreach($filesuccess as $success){
							$fsuccess = $fsuccess ."</br>". $success;	
						}
						$this->session->set_flashdata('success', $fsuccess);
					}
				
				
				redirect('enterence/step_four');
					
			}//if isset file close
			
		}
		$this->load->view('enterence/step_three');
	}

	  public function step_four(){
		if(empty($this->session->userdata('asm_id'))) {
	       	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
			redirect('welcome');
        	}
		$Sid = $this->session->userdata['asm_id'];

		$regisid = $this->session->userdata['asreg_id'];
		$rsdata = array('step3_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process');
			redirect('welcome');	
		}
		
		$rsdata1 = array('step4_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('enterence/step_five');
		}

		//online payment student enterence record get
		
		//get category name
		$catname = $this->commodel->get_listspfic1('admissionstudent_master','asm_caste','asm_id',$Sid)->asm_caste;
		$this->catname = $catname;
		if($this->catname == "General" || $this->catname == "OBC"){
			$amount='300.00';
		}
		if($this->catname == "SC" || $this->catname == "ST"){
			$amount='100.00';
		}
		$name = $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$Sid)->asm_fname;
		//$data['name']=$name;
		$mailid = $this->commodel->get_listspfic1('admissionstudent_master','asm_email','asm_id',$Sid)->asm_email;
		//$data['mailid']=$mailid;
		$phoneno = $this->commodel->get_listspfic1('admissionstudent_master','asm_mobile','asm_id',$Sid)->asm_mobile;
		//$data['phoneno']=$phoneno;
		//$rollno = $this->commodel->get_listspfic1('admissionstudent_master','asm_applicationno','asm_id',$Sid)->asm_applicationno;
		$prgid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$Sid)->asm_coursename;
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
		$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
		$pinfo = $prgname.'('.$prgbranch.')';//.'('.$rollno.')';
		//$data['pinfo']=$pinfo;
		$add = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_paddress','aspar_asmid',$Sid)->aspar_paddress;
		$post = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_ppostoffice','aspar_asmid',$Sid)->aspar_ppostoffice;
		$city = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcity','aspar_asmid',$Sid)->aspar_pcity;
		$state = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$Sid)->aspar_pstate;
		$country = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcountry','aspar_asmid',$Sid)->aspar_pcountry;
		$pin =  $this->commodel->get_listspfic1('admissionstudent_parent','aspar_ppincode','aspar_asmid',$Sid)->aspar_ppincode;
		$address = $add.','.$post.','.$city.','.$state.','.$country.'('.$pin.')';
		//$data['address']=$address;	
		$ftype = 'Entrance Exam fees';
		//online payment gateway code
		// all values are required
    		
		//$MERCHANT_KEY = "SYMBk2HQ"; //change  merchant with yours 
		//$MERCHANT_KEY = "gtKFFx";
		$MERCHANT_KEY	= MERCHANT_KEY;
		//print_r()
       		// $SALT = "dxmk9SZZ9y";  //change salt with yours 
		//$SALT = "eCwWELxi";
		$SALT =	SALT;
		$txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
		
       		//optional udf values 
        	$udf1 = '';
        	$udf2 = '';
        	$udf3 = '';
        	$udf4 = '';
        	$udf5 = '';

		$hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		/*$hashVarsSeq = explode('|', $hashSequence);
	    	$hash_string = '';	
		foreach($hashVarsSeq as $hash_var) {
	      		$hash_string .= isset($posted[$hash_var]) ? $posted[$hash_var] : '';
	     		$hash_string .= '|';
		}
		$hash_string .= $SALT;
	    	$hash = strtolower(hash('sha512', $hash_string));
		*/
 		//$hashstring = $MERCHANT_KEY . '|' . $txnid . '|' . $amount . '|' . $product_info . '|' . $customer_name . '|' . $customer_emial . '||||||' . $SALT;
		$hashstring = $MERCHANT_KEY . '|' . $txnid . '|' . $amount . '|' . $pinfo . '|' . $name . '|' . $mailid . '|' . $udf1 . '|' . $udf2 . '|' . $udf3 . '|' . $udf4 . '|' . $udf5 . '|'.''.'|'.''.'|'.''.'|'.''.'|'.''.'|' . $SALT;
         	$hash = strtolower(hash('sha512', $hashstring));

		//print_r($hash);
		//die;	
		//$hash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||SALT);
		
		$success = site_url() .'/enterence/payustatus'; //return to payustatus function   
		//print_r($success);
		//die;
        	$fail    = site_url() . '/enterence/payustatus'; //return to payustatus function
       	 	//$cancel  = base_url() . 'payumoney/payustatus';//return to payustatus function
		//for live change action  https://secure.payu.in)
				
		$data = array(
            		'mkey' => $MERCHANT_KEY,
            		'tid' => $txnid,
            		'hash' => $hash,
            		'amount' => $amount,  
			'productinfo' => $pinfo,         
            		'name' => $name,
            		'mailid' => $mailid,
           		'phoneno' => $phoneno,
           		'address' => $ftype,
			//'action' => "https://test.payu.in", //for live change action  https://secure.payu.in
			'action' => PAYU_BASE_URL,
           		'surl' => $success,
           		'furl' => $fail,
            		   
        	);
		//insert record in pg table
		$pgdata = array(
			'aspg_asmid' 	=> $Sid,	
			'aspg_txnid' 	=> $txnid,	
			'aspg_pinfo' 	=> $pinfo,	
			'aspg_amount' 	=> $amount,	
			'aspg_ftype'	=> $ftype,	
			//'aspg_date' 	=> ,	
			//'aspg_gw'	=> ,	
			//'aspg_status'	=> ,	
			//'aspg_txncode' 	=> ,
			//'aspg_reason' 	=>	
		);
		$pginsert = $this->db->insert('admissionstudent_pg', $pgdata);
		$this->logger->write_logmessage("insert", "Online data insert in admissionstudent_pg table.");
                $this->logger->write_dblogmessage("insert", "Online data insert in admissionstudent_pg table" );
		
		$this->load->view('enterence/step_four',$data);
	}
		
	public function payustatus() {
		$Sid = $this->session->userdata['asm_id'];
       		$status = $this->input->post('status');
      		if (empty($status)) {
            		redirect('enterence/step_four');
        	}
       
        	$firstname = $this->input->post('firstname');
        	$amount = $this->input->post('amount');
        	$txnid = $this->input->post('txnid');
        	$posted_hash = $this->input->post('hash');
        	$key = $this->input->post('key');
        	$productinfo = $this->input->post('productinfo');
       		$email = $this->input->post('email');
		$ftype = $this->input->post('address1');
		$cdate = date('Y-m-d');	
        	//$SALT = "eCwWELxi";	 //  Your salt
		$SALT =	SALT;
        	$add = $this->input->post('additionalCharges');
        	if(isset($add)) {
            		$additionalCharges = $this->input->post('additionalCharges');
            		$retHashSeq = $additionalCharges . '|' . $SALT . '|' . $status . '|||||||||||' . $email . '|' . $firstname . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
        	} else {

            		$retHashSeq = $SALT . '|' . $status . '|||||||||||' . $email . '|' . $firstname . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
        	}
          	$data['hash'] = hash("sha512", $retHashSeq);
          	$data['amount'] = $amount;
          	$data['txnid'] = $txnid;
          	$data['posted_hash'] = $posted_hash;
          	$data['status'] = $status;
          	if($status == 'success'){
	 		// update pg table
			$pgdata = array(
				'aspg_asmid' 	=> $Sid,	
				'aspg_txnid' 	=> $txnid,	
				'aspg_pinfo' 	=> $productinfo,	
				'aspg_amount' 	=> $amount,	
				'aspg_ftype'	=> $ftype,	
				'aspg_date' 	=> $cdate,	
				//'aspg_gw'	=> ,	
				'aspg_status'	=> $status,	
				//'aspg_txncode' 	=> ,
				//'aspg_reason' 	=>	
			);
			$pgupdate=$this->commodel->insertrec('admissionstudent_pg', $pgdata,'aspg_asmid',$Sid);
			$this->logger->write_logmessage("update", "Update in admissionstudent_pg table.");
                	$this->logger->write_dblogmessage("update", "Update in admissionstudent_pg table." );
	 		//call payment function for update record
		 	$pmathod = 'Online';
		 	$bank='PayuMoney';
		 		
		 	$this->payment($txnid,$bank,$amount,$ftype,$pmathod);
			//set the proper message
			//$message = '<h3>Online fees submit successfully.</h3>';
                	//$this->session->set_flashdata('msg',$message);
                	//$this->load->view('enterence/step_five', $data);   //this should go to step five for printing application page  with suitable message
         	}
         	else{
			// update pg table
			$pgdata = array(
				'aspg_asmid' 	=> $Sid,	
				'aspg_txnid' 	=> $txnid,	
				'aspg_pinfo' 	=> $productinfo,	
				'aspg_amount' 	=> $amount,	
				'aspg_ftype'	=> $ftype,	
				'aspg_date' 	=> $cdate,	
				//'aspg_gw'	=> ,	
				'aspg_status'	=> $status,	
				//'aspg_txncode' 	=> ,
				//'aspg_reason' 	=>	
			);
			$pgupdate=$this->commodel->insertrec('admissionstudent_pg', $pgdata);
			$this->logger->write_logmessage("insert", "failed record insert in admissionstudent_pg table.");
                	$this->logger->write_dblogmessage("insert", "failed record insert in admissionstudent_pg table.");

			// set the status message
                	$this->load->view('enterence/step_four', $data); //this should go to confirmation page for retry to make payment with suitable message
         	}
     
   	 }

	/******************************************Offline payment code start**********************************************************/
//This function check for duplicate reference number 		   
    public function renoexist($reno) {
        $is_exist = $this->commodel->isduplicate('admissionstudent_fees','asfee_referenceno',$reno);
	//print_r($is_exist);
        if ($is_exist)
        {
            $this->form_validation->set_message('renoexist','Reference number'." " .$reno. " ".'is already exist check your refernce number again.');
            return false;
        }
        else {
            return true;
        }
    }

	public function offlinePayment(){
		if(empty($this->session->userdata('asm_id'))) {
	        	$this->session->set_flashdata('err_message', 'You don\'t have access!');
			redirect('welcome');
        	}	
		$getmail = $this->commodel->get_elist('email_setting');
		//print_r($getmail);
		$Sid = $this->session->userdata['asm_id'];
		$data['Sid'] = $Sid;
		//get category name 	
		$this->catname = $this->commodel->get_listspfic1('admissionstudent_master','asm_caste','asm_id',$Sid)->asm_caste;
		
		//fees paid by offline
					
		if(isset($_POST['offline'])) {	
						
			$this->form_validation->set_rules('refno','Reference number','trim|xss_clean|numeric|required|callback_renoexist');
            		$this->form_validation->set_rules('bank','Bank detail','trim|xss_clean|required');
            		$this->form_validation->set_rules('amount','Amount','trim|xss_clean|required|numeric');
            		$this->form_validation->set_rules('ftype','fees type field','trim|xss_clean|required');

			if($_POST){
				$this->data['refNo'] = $this->input->get_post('refno');
				$this->data['bank']  = $this->input->get_post('bank');
				$this->data['amount']= $this->input->get_post('amount');
				$this->data['ftype']=$this->input->get_post('ftype');
			}

			if($this->form_validation->run() == FALSE){			
				$this->load->view('enterence/step_four');
				return;
			}else{	

				$refno = $_POST['refno'];
				$bank =  $_POST['bank'];
				$feeamount = $_POST['amount'];
				$feetype = $_POST['ftype'];
				$pmathod = 'Offline';

				$this->payment($refno,$bank,$feeamount,$feetype,$pmathod);

			}/*close else validation*/
			
		}/*close post submit*/
		
		//set flag for each step, if any step fails revert all steps and return to same step
		$this->load->view('enterence/step_four',$data);
	}	

	public function payment($post1,$post2,$post3,$post4,$post5){
		//print_r($post1.$post2.$post3.$post4.$post5);die;
		$Sid = $this->session->userdata['asm_id'];
		//$isdupl = $this->commodel->isduplicate('admissionstudent_centerallocation','ca_asmid',$Sid);
		//print_r($post1.$post2.$post3.$post4.$post5.' '.$isdupl);die;
               // if(!$isdupl){
			
			$step4 = array(
		 		'asfee_referenceno'   	=>	$post1,
                		'asfee_bankname'  	=>	$post2,
                		'asfee_feeamount'  	=>	$post3,
                		'asfee_feename'   	=>	$post4,
				'asfee_paymentmethod'   =>	$post5
                	);
			$update = $this->commodel->updaterec('admissionstudent_fees', $step4,'asfee_amid',$Sid);
			$this->logger->write_logmessage("update", "Step 4 admissionstudent_fees table update.".$Sid);
                    	$this->logger->write_dblogmessage("update", "Step 4 admissionstudent_fees table update." .$Sid);
			
			$cid = $this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$Sid)->asm_enterenceexamcenter;
			$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$cid)->eec_name;
			$clocation = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$cid)->eec_city;
			$pegid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$Sid)->asm_coursename;

			
			$center = array(
		  		'ca_centerlocation'  => $clocation,
				'ca_centername'	     => $cname,
				'ca_prgid'	     => $pegid
		      	 );
		
			$this->commodel->updaterec('admissionstudent_centerallocation',$center,'ca_asmid',$Sid);
			$this->logger->write_logmessage("update", "Admission Step 4 update detail in centerallocation table.");
               		$this->logger->write_dblogmessage("update", "Admission Step 4 update  detail in centerallocation table." );

			//update admissionstep step4 table
			$cdate = date('Y-m-d H:i');
			$step4 = array(
				'step4_status'   	=>	1,
       				'step4_date'  		=>	$cdate
			);
			$updst4 = $this->commodel->updaterec('admissionstudent_enterencestep', $step4,'admission_masterid',$Sid);
			$this->logger->write_logmessage("update", "Admission Step_four update.".$Sid);
      			$this->logger->write_dblogmessage("update", "Admission Step_four update.".$Sid);

			if(!$update)
			{
               			$this->logger->write_logmessage("update", "Fees Paid but Error to update admission fees status".$Sid );
               			$this->logger->write_dblogmessage("update", "Fees Paid but Error to update admission fees status".$Sid );
               	 		$this->session->set_flashdata("err_message",'Fees Paid but Error to update admission fees status');
				redirect('enterence/step_five');
               		}
               		else{
            			$this->logger->write_logmessage("update","Student enterance admission fees add.".$Sid);
               			$this->logger->write_dblogmessage("update", "Student enterance admission fees add.".$Sid);
				$this->session->set_flashdata("success", "Your".' ' .$post5 .' '."enterance fees submitted successfully.");
				redirect('enterence/step_five');
               		}
		//}//if duplicate close
		//else{
			//$message = '<h3>Your fees submission has been failed.</h3>';
                     //   $this->session->set_flashdata('msg',$message);
			//redirect('welcome');	
			//redirect('enterence/step_four');
		//}	
	$this->load->view('enterence/step_four',$data);

	}



/*************************************offline payment end***************************************************************/


	public function step_five(){
		if(empty($this->session->userdata('asm_id'))) {
	        	$this->session->set_flashdata('err_message', 'You don\'t have access!');
			redirect('welcome');
        	}
		//$regisid = $this->session->userdata['asreg_id'];
		//$id = $this->commodel->get_listspfic1("admissionstudent_master","asm_id","asm_userid",$regisid)->asm_id;		
		$id = $this->session->userdata['asm_id'];

		$regisid = $this->session->userdata['asreg_id'];
		$rsdata = array('step4_status' => '1', 'registration_id' => $regisid);
		$recheckstep_exist = $this->commodel->isduplicatemore('admissionstudent_enterencestep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('welcome');	
		}
		
	
		$data['id']=$id;
		$stud_admission = $this->commodel->get_listrow('admissionstudent_master','asm_id',$id)->row();
		if(!empty($stud_admission)) {
			$name = $stud_admission->asm_fname;
			$data['name']=$name;
			$dob = $stud_admission->asm_dob;
			$data['dob'] = $dob;
			$pid = $stud_admission->asm_coursename;
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$pid)->prg_name;
			$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$pid)->prg_branch;
			$data['prgname'] = $prgname;
			$data['prgbranch'] = $prgbranch;
			$gender = $stud_admission->asm_gender;
			$data['gender'] = $gender;
			$mobile = $stud_admission->asm_mobile;
			$data['mobile'] = $mobile;
			$email = $stud_admission->asm_email;
			$data['email'] = $email;
			$category = $stud_admission->asm_caste;
			$data['category'] = $category;
			$rollno = $stud_admission->asm_applicationno;
			$data['rollno'] = $rollno;
			$sccode = $stud_admission->asm_sccode;
			$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$sccode)->sc_name;
			$data['scname'] = $scname;
			$excode = $stud_admission->asm_enterenceexamcenter;
			$exname =  $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$excode)->eec_name;	
			$data['exname'] = $exname;
			$age = $stud_admission->asm_age;
			$data['age'] = $age;
			$mastatus = $stud_admission->asm_mstatus;
			$data['mastatus'] = $mastatus;
			$nationality = $stud_admission->asm_nationality;
			$data['nationality'] = $nationality;
			$phyhandi = $stud_admission->asm_phyhandicaped;
			$data['phyhandi'] = $phyhandi;
			$religion = $stud_admission->asm_religion;
			$data['religion'] = $religion;
			$reservation = $stud_admission->asm_reservationtype;
			$data['reservation'] = $reservation;

		}

		$studparent_admission = $this->commodel->get_listrow('admissionstudent_parent','aspar_asmid',$id)->row();
		
		if(!empty($studparent_admission)){
			$mname = $studparent_admission->aspar_mothername;
			$data['mname'] = $mname;
			$fname =  $studparent_admission->aspar_fathername;
			$data['fname'] = $fname;
			$mmo = $studparent_admission->aspar_fatherphoneno;
			$data['mmo'] = $mmo;
			$fmo = $studparent_admission->aspar_motherphoneno;	
			$data['fmo'] = $fmo;
			$foccupation = $studparent_admission->aspar_fatheroccupation;
			$data['foccupation'] = $foccupation;
			$moccupation = $studparent_admission->aspar_motheroccupation;
			$data['moccupation'] = $moccupation;
			//get permanant address detail
			$paddress = $studparent_admission->aspar_paddress;
			$data['paddress'] = $paddress;
			$pcity=$studparent_admission->aspar_pcity;
			$data['pcity']=$pcity;
			$pstate = $studparent_admission->aspar_pstate;
			$data['pstate'] = $pstate;
			$pcountry = $studparent_admission->aspar_pcountry;
			$data['pcountry'] = $pcountry;
			$ppincode = $studparent_admission->aspar_ppincode;	
			$data['ppincode'] = $ppincode;
			//get correspondence address detail
			$caddress = $studparent_admission->aspar_caddress;
			$data['caddress'] = $caddress;
			$ccity=$studparent_admission->aspar_ccity;
			$data['ccity']=$ccity;
			$cstate = $studparent_admission->aspar_cstate;
			$data['cstate'] = $cstate;
			$ccountry = $studparent_admission->aspar_ccountry;
			$data['ccountry'] = $ccountry;
			$cpincode = $studparent_admission->aspar_cpincode;	
			$data['cpincode'] = $cpincode;
		}
		
		//get student photo and signature
		$photo = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$id)->asupd_photo;
		$data['photo']=$photo;		
		$signature = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_signature','asupd_asmid',$id)->asupd_signature;
		$data['signature']=$signature;

		//get academic detail
		$admission_academic = $this->commodel->get_listrow('admissionstudent_education','asedu_asmid',$id)->result();
		$data['admission_academic'] = $admission_academic;
		//get enterance exam detail
		$admission_entexm = $this->commodel->get_listrow('admissionstudent_entrance_exam','aseex_asmid',$id)->result();
		$data['admission_entexm'] = $admission_entexm;
		//get student employment detail
		$admission_employment = $this->commodel->get_listrow('admissionstudent_employment','asemp_asmid',$id)->result();
		$data['admission_employment'] = $admission_employment;
		//get student fees detail
		$admission_fees = $this->commodel->get_listrow('admissionstudent_fees','asfee_amid',$id)->result();
		$data['admission_fees'] = $admission_fees;
		
		$cdate = date('Y-m-d H:i');
				$step5 = array(
					'step5_status'   	=>	1,
                			'step5_date'  		=>	$cdate
               			 );
				$updatst2 = $this->commodel->updaterec('admissionstudent_enterencestep', $step5,'admission_masterid',$id);
				$this->logger->write_logmessage("update", "Admisssion Step_five update.");
                    		$this->logger->write_dblogmessage("update", "Admission Step_five update.");

		$this->load->view('enterence/step_five',$data);

	}

	public function step_fivedw(){
			
		$id = $this->session->userdata['asm_id'];
		$data['id']=$id;
		$stud_admission = $this->commodel->get_listrow('admissionstudent_master','asm_id',$id)->row();
		if(!empty($stud_admission)) {
			$name = $stud_admission->asm_fname;
			$data['name']=$name;
			$dob = $stud_admission->asm_dob;
			$data['dob'] = $dob;
			$pid = $stud_admission->asm_coursename;
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$pid)->prg_name;
			$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$pid)->prg_branch;
			$data['prgname'] = $prgname;
			$data['prgbranch'] = $prgbranch;
			$gender = $stud_admission->asm_gender;
			$data['gender'] = $gender;
			$mobile = $stud_admission->asm_mobile;
			$data['mobile'] = $mobile;
			$email = $stud_admission->asm_email;
			$data['email'] = $email;
			$category = $stud_admission->asm_caste;
			$data['category'] = $category;
			$rollno = $stud_admission->asm_applicationno;
			$data['rollno'] = $rollno;
			$sccode = $stud_admission->asm_sccode;
			$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$sccode)->sc_name;
			$data['scname'] = $scname;
			$excode = $stud_admission->asm_enterenceexamcenter;
			$exname =  $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$excode)->eec_name;	
			$data['exname'] = $exname;
			$age = $stud_admission->asm_age;
			$data['age'] = $age;
			$mastatus = $stud_admission->asm_mstatus;
			$data['mastatus'] = $mastatus;
			$nationality = $stud_admission->asm_nationality;
			$data['nationality'] = $nationality;
			$phyhandi = $stud_admission->asm_phyhandicaped;
			$data['phyhandi'] = $phyhandi;
			$religion = $stud_admission->asm_religion;
			$data['religion'] = $religion;
			$reservation = $stud_admission->asm_reservationtype;
			$data['reservation'] = $reservation;

		}

		$studparent_admission = $this->commodel->get_listrow('admissionstudent_parent','aspar_asmid',$id)->row();
		
		if(!empty($studparent_admission)){
			$mname = $studparent_admission->aspar_mothername;
			$data['mname'] = $mname;
			$fname =  $studparent_admission->aspar_fathername;
			$data['fname'] = $fname;
			$mmo = $studparent_admission->aspar_fatherphoneno;
			$data['mmo'] = $mmo;
			$fmo = $studparent_admission->aspar_motherphoneno;	
			$data['fmo'] = $fmo;
			$foccupation = $studparent_admission->aspar_fatheroccupation;
			$data['foccupation'] = $foccupation;
			$moccupation = $studparent_admission->aspar_motheroccupation;
			$data['moccupation'] = $moccupation;
			//get permanant address detail
			$paddress = $studparent_admission->aspar_paddress;
			$data['paddress'] = $paddress;
			$pcity=$studparent_admission->aspar_pcity;
			$data['pcity']=$pcity;
			$pstate = $studparent_admission->aspar_pstate;
			$data['pstate'] = $pstate;
			$pcountry = $studparent_admission->aspar_pcountry;
			$data['pcountry'] = $pcountry;
			$ppincode = $studparent_admission->aspar_ppincode;	
			$data['ppincode'] = $ppincode;
			//get correspondence address detail
			$caddress = $studparent_admission->aspar_caddress;
			$data['caddress'] = $caddress;
			$ccity=$studparent_admission->aspar_ccity;
			$data['ccity']=$ccity;
			$cstate = $studparent_admission->aspar_cstate;
			$data['cstate'] = $cstate;
			$ccountry = $studparent_admission->aspar_ccountry;
			$data['ccountry'] = $ccountry;
			$cpincode = $studparent_admission->aspar_cpincode;	
			$data['cpincode'] = $cpincode;
		}
		
		//get student photo and signature
		$photo = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$id)->asupd_photo;
		$data['photo']=$photo;		
		$signature = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_signature','asupd_asmid',$id)->asupd_signature;
		$data['signature']=$signature;

		//get academic detail
		$admission_academic = $this->commodel->get_listrow('admissionstudent_education','asedu_asmid',$id)->result();
		$data['admission_academic'] = $admission_academic;
		//get enterance exam detail
		$admission_entexm = $this->commodel->get_listrow('admissionstudent_entrance_exam','aseex_asmid',$id)->result();
		$data['admission_entexm'] = $admission_entexm;
		//get student employment detail
		$admission_employment = $this->commodel->get_listrow('admissionstudent_employment','asemp_asmid',$id)->result();
		$data['admission_employment'] = $admission_employment;
		//get student fees detail
		$admission_fees = $this->commodel->get_listrow('admissionstudent_fees','asfee_amid',$id)->result();
		$data['admission_fees'] = $admission_fees;
		
		$this->load->library('pdf');
       		$this->pdf->load_view('enterence/step_fivedw',$data);
        	$this->pdf->render();
        	$this->pdf->stream("admissionform.pdf");		
	}

	public function prtadmission_form(){
		
		$this->prgname = $this->commodel->get_listmore('program','prg_name,prg_id,prg_branch');
		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('applicantemail','Email Id','trim|required|valid_email');
            		$this->form_validation->set_rules('applicantmobile','Mobile No','trim|required|xss_clean|numeric');
            		$this->form_validation->set_rules('dateofbirth','Date Of Birth','trim|required|xss_clean');
            		$this->form_validation->set_rules('applicantprogram','Program Name','trim|required|xss_clean');
            		$this->form_validation->set_rules('applicantvercode','Verification Code','trim|required|xss_clean|numeric');

            		if($this->form_validation->run() == FALSE){
			//echo "invalid value";
               	 		$this->load->view('enterence/admission_form');
				return;
           	 	}else{									
				$data = array(
					'asreg_emailid' => $this->input->post('applicantemail'),
					'asreg_mobile'    => $this->input->post('applicantmobile'),
					'asreg_dob'     => $this->input->post('dateofbirth'),
					'asreg_program'  => $this->input->post('applicantprogram'),
					'asreg_verificationcode'  => $this->input->post('applicantvercode')				
				);
						
				//verify the data existance filled by user 
				$result = $this->commodel->isduplicatemore("admissionstudent_registration",$data);
				if ($result == true){
					$asid = $this->commodel->get_listarry("admissionstudent_registration","asreg_id",$data);
							
					foreach($asid as $asregid){
						$asegid= $asregid->asreg_id;
					}
						
					$this->admission_studentstep($asegid);
				}	
				 else {
					$message= '<h4>Your some detail is invalid.</h4>';
					$this->session->set_flashdata('err_message',$message);
					redirect('enterence/prtadmission_form');
				}
			}
					
		}
		$this->load->view('enterence/admission_form');
	}

	public function stu_hallticket(){
		//$regisid = $this->session->userdata['asreg_id'];
		//$id = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$regisid)->asm_id;
		//$hstatus = $this->commodel->get_listspfic1('admissionstudent_centerallocation','ca_hallticketstatus','ca_asmid',$id)->ca_hallticketstatus;
		$this->prgname = $this->commodel->get_listmore('program','prg_name,prg_id,prg_branch');
		if(isset($_POST['download'])){
			$this->form_validation->set_rules('dwapplicantemail','Email Id','trim|required|valid_email');
            		$this->form_validation->set_rules('dwapplicantmobile','Mobile No','trim|required|xss_clean|numeric');
            		$this->form_validation->set_rules('dwdateofbirth','Date Of Birth','trim|required|xss_clean');
            		$this->form_validation->set_rules('dwapplicantprogram','Program Name','trim|required|xss_clean');
            		$this->form_validation->set_rules('dwapplicantvercode','Verification Code','trim|required|xss_clean|numeric');

            		if($this->form_validation->run() == FALSE){
               	 		$this->load->view('enterence/stu_hallticket');
				return;
           	 	}else{	
				$data = array(
					'asreg_emailid' 	 => $this->input->post('dwapplicantemail'),
					'asreg_mobile'    	 => $this->input->post('dwapplicantmobile'),
					'asreg_dob'     	 => $this->input->post('dwdateofbirth'),
					'asreg_program'  	 => $this->input->post('dwapplicantprogram'),
					'asreg_verificationcode' => $this->input->post('dwapplicantvercode')				
				);
						
				//verify the data existance filled by user 
				$result = $this->commodel->isduplicatemore("admissionstudent_registration",$data);
				//print_r($result);
				if ($result == true){
					$asid = $this->commodel->get_listarry("admissionstudent_registration","asreg_id",$data);
						foreach($asid as $asregid){
							$asegid= $asregid->asreg_id;
						}
					$this->hallticket_step($asegid);
					
					//redirect("enterence/stu_hallticketdw",$asegid);
				}	
				 else {
					$message= '<h4>Your some detail is invalid.</h4>';
					$this->session->set_flashdata('err_message',$message);
					redirect('enterence/stu_hallticket');
				}
			}
		}
		$this->load->view('enterence/stu_hallticket');
	}
	
	public function hallticket_step($applicationno){
		
		$this->resstep=$this->commodel->get_listrow('admissionstudent_enterencestep','registration_id',$applicationno)->result();
		if(!empty($this->resstep)) {
			// if exist then check which step is completed and redirect to incmplete step with appropriate data
			foreach($this->resstep as $rslt){
				$stp5= $rslt->step5_status;
			}
	
			if($stp5 == 0 || $stp5 == NULL){
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				$data = [
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
		               ];
                          	$this->session->set_userdata($data);
				// redirect to step5 for completion
				
				redirect('enterence/step_five');
			}
			else{
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_userid',$applicationno)->asm_id;
				$data = [
					'asm_id' => $asmid,
					'asreg_id' => $applicationno
		               ];
                        	$this->session->set_userdata($data);
				// redirect to step5 for completion
				redirect('enterence/stu_hallticketdw');
			}
		}

	}

	public function stu_hallticketdw(){
		if(empty($this->session->userdata('asm_id'))) {
			//$message= '<h4>Please login again to download hall ticket.</h4>';
			//$this->session->set_flashdata('err_message',$message);
	        	$this->session->set_flashdata('err_message', 'Login again for download hall ticket');
			redirect('welcome');
        	}
		 $uid = $this->session->userdata['asm_id'];
		 $data['uid'] = $uid;	
		 $selectdata = array('asm_gender','asm_caste','asm_coursename','asm_fname','asm_id','asm_applicationno');	
		 $wdata=array('asm_id' => $uid);
                 $stud_master = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$wdata);
		 $data['stud_master'] = $stud_master;
		 $acadyear = $this->usrmodel->getcurrentAcadYear();
		 $data['acadyear'] = $acadyear;
		 foreach($stud_master as $row){
			$asmid=$row->asm_id;
			$data['asmid'] = $asmid;
			$gender=$this->commodel->get_listspfic1('admissionstudent_master','asm_gender','asm_id',$asmid)->asm_gender;
			$data['gender'] = $gender;
			$caste=$this->commodel->get_listspfic1('admissionstudent_master','asm_caste','asm_id',$asmid)->asm_caste;
        		$data['caste'] = $caste;               
			$prgid  = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
			$data['prgid'] = $prgid;                        
			$progname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';
			$data['progname'] = $progname;
                        $rollno=$row->asm_applicationno;
			$data['rollno'] = $rollno;
                        $sname = $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;
			$data['sname'] = $sname;
                        $faname=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_fathername','aspar_asmid',$asmid)->aspar_fathername;
			$data['faname'] = $faname;
                        $moname=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_mothername','aspar_asmid',$asmid)->aspar_mothername;
			$data['moname'] = $moname;
                        $padd=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_paddress','aspar_asmid',$asmid)->aspar_paddress;
			$data['padd'] = $padd;
                        $pcity=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcity','aspar_asmid',$asmid)->aspar_pcity;
			$data['pcity'] = $pcity;
                        $pstate=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$asmid)->aspar_pstate;
			$data['pstate'] = $pstate;
                        $pcountry=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcountry','aspar_asmid',$asmid)->aspar_pcountry;
			$data['pcountry'] = $pcountry;
			$photo=$this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$asmid)->asupd_photo;
			$data['photo'] = $photo;
			$signature = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_signature','asupd_asmid',$asmid)->asupd_signature;
			$data['signature'] = $signature;
			$centerid=$this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$asmid)->asm_enterenceexamcenter;
			$venue=$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name.','.$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_address','eec_id',$centerid)->eec_address.','.$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$centerid)->eec_city;
			$data['venue'] = $venue;
			$exmdate = $this->commodel->get_listspfic1('admissionopen','admop_entexam_date','admop_prgname_branch',$prgid)->admop_entexam_date;
			$data['exmdate'] = $exmdate;
			
			
        	}	
		 $this->load->view('enterence/stu_hallticketdw',$data);
	}


	public function stu_hallticketdwpdf(){
		if(empty($this->session->userdata('asm_id'))) {
	        	$this->session->set_flashdata('err_message', 'Login again for download hall ticket');
			redirect('welcome');
        	}
		 $uid = $this->session->userdata['asm_id'];
		 $data['uid'] = $uid;	
		 $selectdata = array('asm_gender','asm_caste','asm_coursename','asm_fname','asm_id','asm_applicationno');	
		 $wdata=array('asm_id' => $uid);
                 $stud_master = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$wdata);
		 $data['stud_master'] = $stud_master;
		 $acadyear = $this->usrmodel->getcurrentAcadYear();
		 $data['acadyear'] = $acadyear;
		 foreach($stud_master as $row){
			$asmid=$row->asm_id;
			$data['asmid'] = $asmid;
			$gender=$this->commodel->get_listspfic1('admissionstudent_master','asm_gender','asm_id',$asmid)->asm_gender;
			$data['gender'] = $gender;
			$caste=$this->commodel->get_listspfic1('admissionstudent_master','asm_caste','asm_id',$asmid)->asm_caste;
        		$data['caste'] = $caste;               
			$prgid  = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
			$data['prgid'] = $prgid;                        
			$progname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';
			$data['progname'] = $progname;
                        $rollno=$row->asm_applicationno;
			$data['rollno'] = $rollno;
                        $sname = $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;
			$data['sname'] = $sname;
                        $faname=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_fathername','aspar_asmid',$asmid)->aspar_fathername;
			$data['faname'] = $faname;
                        $moname=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_mothername','aspar_asmid',$asmid)->aspar_mothername;
			$data['moname'] = $moname;
                        $padd=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_paddress','aspar_asmid',$asmid)->aspar_paddress;
			$data['padd'] = $padd;
                        $pcity=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcity','aspar_asmid',$asmid)->aspar_pcity;
			$data['pcity'] = $pcity;
                        $pstate=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$asmid)->aspar_pstate;
			$data['pstate'] = $pstate;
                        $pcountry=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcountry','aspar_asmid',$asmid)->aspar_pcountry;
			$data['pcountry'] = $pcountry;
			$photo=$this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$asmid)->asupd_photo;
			$data['photo'] = $photo;
			$signature = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_signature','asupd_asmid',$asmid)->asupd_signature;
			$data['signature'] = $signature;
			$centerid=$this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$asmid)->asm_enterenceexamcenter;
			$venue=$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name.','.$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_address','eec_id',$centerid)->eec_address.','.$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$centerid)->eec_city;
			$data['venue'] = $venue;
			$exmdate = $this->commodel->get_listspfic1('admissionopen','admop_entexam_date','admop_prgname_branch',$prgid)->admop_entexam_date;
			$data['exmdate'] = $exmdate;
			
			$this->load->library('pdf');
       			$this->pdf->load_view('enterence/stu_hallticketpdfdw',$data);
        		$this->pdf->render();
        		$this->pdf->stream("Hallticket.pdf");
			
       		 }	
		// $this->load->view('enterence/stu_hallticketdw',$data);
	}

	

	public function home() {
		$id = $this->session->userdata['asm_id'];
		//$uid = $this->session->userdata['asm_id'];
        	$data = ['asm_id'];
      		$this->session->unset_userdata($data);
       		redirect('welcome');
    	}
	
}//close class
