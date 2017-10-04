<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Enterence.php
 * @author Sumit Saxena(sumitsesaxena@gmail.com)
 * @author Sharad Singh(Sharad23nov@gmail.com)
 */
class Enterence extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */
	function __construct() {
        	parent::__construct();
        	$this->load->model("login_model", "login");
            $this->load->model("User_model", "usrmodel");
            $this->load->model("Common_model", "commodel");
            $this->load->model("Mailsend_model","mailmodel");
    }

 	public function viewadmissionopen() {
        	$this->result = $this->commodel->get_list('admissionopen');
        	$this->logger->write_logmessage("view"," View Admission List", "Admission List details...");
        	$this->logger->write_dblogmessage("view"," View Admission List" , "Admission List record display successfully..." );
       		$this->load->view('enterence/viewadmissionopen',$this->result);
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
	
	public function editadmissionopen($admop_id) {
        //$this->prgcatresult= $this->commodel->get_listspfic2('programcategory','prgcat_id', 'prgcat_name');
        //$this->prgresult = $this->commodel->get_listspfic2('program','prg_id', 'prg_name');
        $admoprow=$this->commodel->get_listrow('admissionopen','admop_id', $admop_id);
        if ($admoprow->num_rows() < 1)
        {
            redirect('enterence/viewadmissionopen');
        }
        $admop_data = $admoprow->row();

        /* Form fields */

          $data['admop_acadyear'] = array(
              'value' => $admop_data->admop_acadyear,
          );

          $data['admop_prgcat'] = array(
            'name' => 'admop_prgcat',
            //'id' => 'prgcode',
            'maxlength' => '50',
            'size' => '40',
           // 'value' => $this->commodel->get_listspfic2('programcategory','prgcat_id','prgcat_name',$admop_data->admop_prgcat,'prg_name')->prgcat_name,
           $this->prgramcategory=$this->commodel->get_listmore('programcategory','prgcat_id,prgcat_name')

            //'readonly' => 'readonly'
          );

           $data['admop_prgname_branch'] = array(
            'name' => 'admop_prgname_branch',
            //'id' => 'prgcode',
            'maxlength' => '50',
            'size' => '40',
            //'value' => $this->commodel->get_listspfic2('program','prg_name','prg_id',$admop_data->admop_prgname)->prg_name,
        $this->programname= $this->commodel->get_listmore('program','prg_name,prg_id')
            //'readonly' => 'readonly'
          );

            $data['admop_entexam_fees'] = array(
             'name' => 'admop_entexam_fees',
             'maxlength' => '50',
             'size' => '40',
             'value' => $admop_data->admop_entexam_fee,
             //'readonly' => 'readonly'

          );


          $data['admop_min_qual'] = array(
             'name' => 'admop_min_qual',
             //'id' => 'admop_min_qual',
             'maxlength' => '50',
             'size' => '40',
             'value' => $admop_data->admop_min_qual,
             //'readonly' => 'readonly'

          );

           $data['admop_entexam_patt'] = array(
             'name' => 'admop_entexam_patt',
            // 'id' => 'admop_entexam_patt',
             'maxlength' => '50',
             'size' => '40',
             'value' => $admop_data->admop_entexam_patt,
             //'readonly' => 'readonly' 
          );

         $data['admop_entexam_date'] = array(
                'name' => 'admop_entexam_date',
               // 'id' => 'ExamDate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_entexam_date ,
                );

         $data['admop_startdate'] = array(
                'name' => 'admop_startdate',
               // 'id' => 'StartDate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_startdate ,
                );
         $data['admop_lastdate'] = array(
                'name' => 'admop_lastdate',
                //'id' => 'LastDate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_lastdate ,
                );
         $data['admop_app_received'] = array(
                'name' => 'admop_app_received',
               // 'id' => 'EndDate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $admop_data->admop_app_received ,
                );
        $data['admop_id'] = $admop_id;
	
	 /*Form Validation*/
                $this->form_validation->set_rules('admop_acadyear','Academic Year','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_prgcat','Progarm Category','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_prgname_branch','Program Name','trim|xss_clean|required');
                $this->form_validation->set_rules('admop_entexam_fee','Entrance Exam Fees','trim|xss_clean|required');
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
            $enterenceexamfees = strtoupper($this->input->post('admop_entexam_fees', TRUE));
            $minimumqualification = $this->input->post('admop_min_qual', TRUE);
            $entranceexampattern = ucwords(strtolower($this->input->post('admop_entexam_patt', TRUE)));
            $entranceexamdate= ucwords(strtolower($this->input->post('admop_entexam_date', TRUE)));
            $startdateofonlineapplication = $this->input->post('admop_startdate',TRUE);
            $lastdateofonlineapplication = $this->input->post('admop_lastdate', TRUE);
            $lastdateofapplicationreceived = $this->input->post('admop_app_received', TRUE);
		
	$logmessage = "";
          //  if($admop_data->fm_programid != $programname)
            //    $logmessage = $logmessage ." update program name " .$fm_data->fm_programid. " changed by " .$programname;
            if($admop_data->admop_acadyear != $acadyear)
                $logmessage = $logmessage ." update academic year " .$admop_data->admop_acadyear. " changed by " .$acadyear;
            if($admop_data->admop_entexam_fee != $enterenceexamfees)
                $logmessage = $logmessage ." update enterenceexamfees " .$admop_data->admop_entexam_fee. " changed by " .$enterenceexamfees;
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
                $logmessage = $logmessage ." update lastdateofapplicationreceived " .$fm_data->admop_app_received. " changed by " .$lastdateofapplicationreceived;

	    $update_data = array(
               'admop_acadyear' => $acadyear,
             //  'admop_prgcat' => $,
               //'admop_prgname_branch' => $semester,
               'admop_entexam_fee'  => $enterenceexamfees,
               'admop_min_qual'  => $minimumqualification,
               'admop_entexam_patt' => $entranceexampattern,
               'admop_entexam_date' => $entranceexamdate,
               'admop_startdate' => $startdateofonlineapplication,
               'admop_lastdate' => $lastdateofonlineapplication,
		'admop_app_received' => $lastdateofapplicationreceived,
             );
           $admopflag=$this->common_model->updaterec('admissionopen', $update_data, 'admop_id', $admop_id);
		//	'admissionopen','admop_id', $admop_id);
           if(!$admopflag)
              {
                $this->logger->write_logmessage("error","Error in update Fees ", "Error in Fees record update". $logmessage );
                $this->logger->write_dblogmessage("error","Error in update Fees ", "Error in Fees record update". $logmessage );
                $this->session->set_flashdata('err_message','Error updating Fees - ' . $logmessage . '.', 'error');
                $this->load->view('enterence/editadmissionopen', $data);
              }
            else{
                $this->logger->write_logmessage("update","Edit Fees", "Fees headwise record updated successfully..". $logmessage );
                $this->logger->write_dblogmessage("update","Edit Fees", "Fees headwise record updated successfully..". $logmessage );
                $this->session->set_flashdata('success', "Program fees record updated successfully..." );
                redirect('enterence/viewadmissionopen');
                }
          }
	}

////////////////////////////////////////////////////////////////////////////////////

	public function important_date(){
		$this->result = $this->commodel->get_list('admissionopen');
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

	
	public function enterence_step1(){
		$this->scresult = $this->commodel->get_list('study_center');
		$this->prgname = $this->commodel->get_listmore('program','prg_name,prg_id');		

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
			$this->form_validation->set_rules('entage','Age','trim|xss_clean|required');
          		$this->form_validation->set_rules('entcate','Category','trim|xss_clean|required');	
			$this->form_validation->set_rules('entnationality','Nationality','trim|xss_clean|required');
          		$this->form_validation->set_rules('entdisability','Disability','trim|xss_clean|required');
           		$this->form_validation->set_rules('entreligion','Religion','trim|xss_clean|required');

			//address detail validation code
           		$this->form_validation->set_rules('entcostreet','Postal address street','trim|xss_clean|required');
			$this->form_validation->set_rules('entpcity','Postal address city','trim|xss_clean|required');
			$this->form_validation->set_rules('entpstate','Postal address state','trim|xss_clean|required');
	   		$this->form_validation->set_rules('entpcode','Postal address postal code','trim|xss_clean|required');
			$this->form_validation->set_rules('entpcountry','Postal address country','trim|xss_clean|required');
          		
			$this->form_validation->set_rules('entstreet','Correspondence address street','trim|xss_clean|required');
			$this->form_validation->set_rules('entcocity','Correspondence address city','trim|xss_clean|required');
			$this->form_validation->set_rules('entcostate','Correspondence address state','trim|xss_clean|required');
	   		$this->form_validation->set_rules('entcocode','Correspondence address postal code','trim|xss_clean|required|numeric');
			$this->form_validation->set_rules('entcocountry','Correspondence address country','trim|xss_clean|required');

			//family detail validation code	
			$this->form_validation->set_rules('entfathername','Father name','trim|xss_clean|required');
			$this->form_validation->set_rules('entmothername','Mother name','trim|xss_clean|required');
			$this->form_validation->set_rules('entfathermono','Father mobile no.','trim|xss_clean|required');
	   		$this->form_validation->set_rules('entmothermono','Mother mobile no.','trim|xss_clean|required');
			$this->form_validation->set_rules('entfatheroccu','Father occupation','trim|xss_clean|required');
			$this->form_validation->set_rules('entmotheroccu','Mother occupation','trim|xss_clean|required');

			if($this->form_validation->run() == TRUE)
			{
				//insert into student master
		 		$data = array(
                			'asm_sccode'  		=>	$_POST['Sname'],
                			'sm_gender'  		=>	$_POST['Sgender'],
                			'sm_dob'   		=>	$_POST['Sdob'],
					'sm_uid'   		=>	$_POST['Saadharnumber'],
                			'sm_bloodgroup'   	=>	$_POST['Sabgroup'],
                			'sm_religion'  		=>	$_POST['Sreligion'],
                			'sm_mobile'   		=>	$_POST['Smobile'],
					'sm_email'   		=>	$_POST['Semail'],
					'sm_category'		=>	$_POST['Scategory'],
					'sm_sccode'		=>	$_POST['Scenters'],
					'sm_enrollmentno'	=>	$enroollno
               		 );
	 		
				$this->db->insert('admissionstudent_master', $data);	
				$insertid = $this->db->insert_id();
								
			}//if valdation close

		
		}//if post close

		$this->load->view('enterence/enterence_step1');
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
                            //echo "Test1111";echo "<br>";
                            //got to next step
                                $this->load->view('enterence/step_one',$applicant_field);
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
                    $applicantdetail_insert = $this->commodel->insertrec('admissionstudent_registration',$applicant_field);
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
	
}//close class
