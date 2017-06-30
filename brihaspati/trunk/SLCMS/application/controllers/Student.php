<?php
/******************************************************
*						      *
* @name Student.php(controller)    		      *
* @author Nagendra Kumar Singh(nksinghiitk@gmail.com) *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Student extends CI_Controller {
/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->db2 = $this->load->database('login', TRUE);
		$this->load->model('user_model');
		$this->load->model("Common_model");
		$this->load->model("Student_model");
		$this->load->model("login_model");
		$this->load->model("mailsend_model");
	}
/******************************************************************************/	
//$this->load->helper('url');

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
	
	
	/*************************************************************************************************************/
	// Check for user admission login process
	public function student_step0() {
	//	$this->result=$this->Student_model->verification();
		 $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       		 $this->session->set_flashdata($array_items);
		if(isset($_POST['login'])){
			$this->form_validation->set_rules('Sanumber', 'Application Number', 'required');
			$this->form_validation->set_rules('Sprogram', 'Program/Course', 'required');
			$this->form_validation->set_rules('Sname', 'Student Name', 'required');
		
				if ($this->form_validation->run() == FALSE) {	
					//$this->load->view('student/student_step0');
					}else{						
						$data = array(
						'application_no' => $this->input->post('Sanumber'),
						'course_name' => $this->input->post('Sprogram'),
						'student_name' => $this->input->post('Sname')				
							      );
						//for verification use the dupliacatemore in common model
						$result = $this->Common_model->isduplicatemore("admissionmeritlist",$data);
						//$result = $this->Student_model->login($data);
						if ($result == true) {
							$number = $this->input->post('Sanumber');
							//call studentstep function and that function will decide in which page you are landing after putting the correct crediential
							$this->studentstep($number);
					
						}	
						 else {
							$message= '<h4>Your application number or name is inavalid.</h4>';
							$this->session->set_flashdata('msg',$message);
							//	$this->load->view('student/student_step0');
							redirect('student/student_step0');
							}
					}
	        }
		$this->load->view('student/student_step0');
	}

	//check for completed admission step
	public function studentstep($applicationno){

		//check the existence of entry in admissionstep table 
		$this->resstep=$this->Common_model->get_listrow('admissionstep','application_no',$applicationno)->result();
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
                        	        
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
			//redirect to step1 for completion
			//$this->logger->write_logmessage("error", "I am  in data in student step inside step 1 check  -3" . $stp1 );
				redirect('Student/student_step1');
				
			}
			else if($stp2 == 0 || $stp2 == NULL){
				//check the value set in session if not then
				// get the student master id
				$this->smid= $this->Common_model->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				// set the student master and application number in session
				$data = [
                        	        'sm_id' => $this->smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step2 for completion
				redirect('Student/student_step2');
			}
			else if($stp3 == 0 || $stp3 == NULL){
				//check the value set in session if not then
				// get the student master id
					$this->smid= $this->Common_model->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				// set the student master and application number in session
				$data = [
                        	        'sm_id' => $this->smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                                $this->session->set_userdata($data);
				// redirect to step3 for completion
				redirect('Student/student_step3');
			}
			else if($stp4 == 0 || $stp4 == NULL){
				//check the value set in session if not then
				// get the student master id
				$this->smid= $this->Common_model->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				// set the student master and application number in session
				$data = [
                        	        'sm_id' => $this->smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step4 for completion
				redirect('Student/student_step4');
			}
			else if($stp5 == 0 || $stp5 == NULL){
				//check the value set in session if not then
				// get the student master id
				$this->smid= $this->Common_model->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				// set the student master and application number in session
				$data = [
                        	        'sm_id' => $this->smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step5 for completion
				redirect('Student/student_step5');
			}
			else{ //bracess for the else is added again by nks
				// if step 5 is completed then redirect to student home page else redirect to correct step page
				//get the userid from student master
				$smres=$this->Common_model->get_listspfic1('student_master','sm_userid','sm_applicationno',$applicationno);
				$suid=$smres->sm_userid;
				//print_r($suid);				
				//get the username from edrpuser
				$usernme=$this->login_model->get_listspfic1('edrpuser','username','id',$suid)->username;
				//check the value set in session if not then
				// set the student master and application number in session
				$data = [
                        	        'id_user' => $suid,
			                'username' => $usernme,
                        	        'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
			       redirect('studenthome'); 
			}
		}// 
		else{ //complete block added by nks
			 // set the student role and application number in session
                         $data = [
                                'app_no' => $applicationno,
                                'id_role' => 3
                        ];
                        $this->session->set_userdata($data);

			// if not then redirect to step 1 page with aapropriate data 
			//$this->load->view('student/student_step1');
			redirect('student/student_step1');
		}
	}

	 public function student_step1(){
		$array_items = array('success' => '', 'error' => '', 'warning' =>'');
       		$this->session->set_flashdata($array_items);
		$this->scresult = $this->Common_model->get_list('study_center');
		$this->progresult = $this->Common_model->get_listspfic2('program','prg_code', 'prg_category');
		$this->programesult1 = $this->Common_model->get_list('program');
		$this->scatresult = $this->Common_model->get_list('category');
		$this->depresult = $this->Common_model->get_list('Department');

		$this->number = $this->session->userdata['app_no'];	
		//print_r($this->number);
		$this->name=$this->Common_model->get_listspfic1("admissionmeritlist","student_name","application_no",$this->number)->student_name;
		$this->fathname=$this->Common_model->get_listspfic1("admissionmeritlist","father_name","application_no",$this->number)->father_name;
		$this->couname=$this->Common_model->get_listspfic1("admissionmeritlist","course_name","application_no",$this->number)->course_name;
		$this->categid=$this->Common_model->get_listspfic1("program","prg_id","prg_name",$this->couname)->prg_id;
		//print_r($this->name);
		//$result1 = $this->Common_model->get_listrow("admissionmeritlist","application_no",$number);
		//print_r($result1);
         	 if(isset($_POST['addstudent'])) {

            		$this->form_validation->set_rules('Sanumber','Application no.','trim|xss_clean|numeric|required');
            		$this->form_validation->set_rules('Sname','Applicant name','trim|xss_clean|required');
            		$this->form_validation->set_rules('Sgender','gender','trim|xss_clean|required');
            		$this->form_validation->set_rules('Sdob','date of birth','trim|xss_clean|required');
	    		$this->form_validation->set_rules('Saadharnumber','aadhar number','trim|xss_clean|numeric|required|max_length[12]');
            		$this->form_validation->set_rules('Sabgroup','blood group','trim|xss_clean|required');
           		$this->form_validation->set_rules('Sreligion','religion','trim|xss_clean|required');
            		$this->form_validation->set_rules('Smobile','mobile number','trim|xss_clean|numeric|max_length[10]|required');
	    		$this->form_validation->set_rules('Semail','email-id','trim|xss_clean|required|valid_email');
			$this->form_validation->set_rules('Snameprogramme','Program/Course name','trim|xss_clean|required');
			$this->form_validation->set_rules('Scenters','Study center','trim|xss_clean|required');
          		$this->form_validation->set_rules('Stypeprogramme','Program/Course type','trim|xss_clean|required');
			$this->form_validation->set_rules('Sdepart','Departmnet not select','trim|xss_clean|required');

			$this->form_validation->set_rules('Smothername','Mother name','trim|xss_clean|required');
          		$this->form_validation->set_rules('Sfathername','Father name','trim|xss_clean|required');
           		$this->form_validation->set_rules('Scategory','Category','trim|xss_clean|required');
           		$this->form_validation->set_rules('Spaddress','Postal address','trim|xss_clean|required');
			$this->form_validation->set_rules('Sdist','District','trim|xss_clean|required');
			$this->form_validation->set_rules('Spost','Post office','trim|xss_clean|required');
	   		$this->form_validation->set_rules('Scity','City','trim|xss_clean|required');
			$this->form_validation->set_rules('Sstate','State','trim|xss_clean|required');
          		$this->form_validation->set_rules('Scountry','Country','trim|xss_clean|required');
           		$this->form_validation->set_rules('Spincode','Pincode','trim|xss_clean|max_length[6]|required');

			$this->form_validation->set_rules('Hcname','High school name','trim|xss_clean|required');
          		$this->form_validation->set_rules('Hsubject','High  subject','trim|xss_clean|required');
           		$this->form_validation->set_rules('Hboard','High  board','trim|xss_clean|required');
           		$this->form_validation->set_rules('Hyear','High  year','trim|xss_clean|numeric|required');
	   		$this->form_validation->set_rules('Hpassed','High  passed/appeared','trim|xss_clean|required');

	    		$this->form_validation->set_rules('Icname','Intermediate school name','trim|xss_clean|required');
           		$this->form_validation->set_rules('Isubject','Intermediate  subject','trim|xss_clean|required');
           		$this->form_validation->set_rules('Iboard','Intermediate  board','trim|xss_clean|required');
          		$this->form_validation->set_rules('Iyear','Intermediate  year','trim|xss_clean|numeric|required');
	   		$this->form_validation->set_rules('Ipassed','Intermediate  passed/appeared','trim|xss_clean|required');

	    		$this->form_validation->set_rules('Gcname','Graduation college name','trim|xss_clean');
           		$this->form_validation->set_rules('Gsubject','Graduation  subject','trim|xss_clean');
           		$this->form_validation->set_rules('Gboard','Graduation  board','trim|xss_clean');
           		$this->form_validation->set_rules('Gyear','Graduation year','trim|xss_clean|numeric');
	   		$this->form_validation->set_rules('Gpassed','Graduation passed/appeared','trim|xss_clean');


	    		$this->form_validation->set_rules('Pcname','Post graduation college name','trim|xss_clean');
           		$this->form_validation->set_rules('Psubject','Post graduation subject','trim|xss_clean');
           		$this->form_validation->set_rules('Pboard','Post graduation board','trim|xss_clean');
           		$this->form_validation->set_rules('Pyear','Post graduation year','trim|xss_clean|numeric');
	   		$this->form_validation->set_rules('Ppassed','Post graduation passed/appeared','trim|xss_clean');

	   		$this->form_validation->set_rules('Acname','Any other qualificatin name','trim|xss_clean');
           		$this->form_validation->set_rules('Asubject','Any other qualificatin subject','trim|xss_clean');
           		$this->form_validation->set_rules('Aboard','Any other qualificatin board','trim|xss_clean');
           		$this->form_validation->set_rules('Ayear','Any other qualificatin year','trim|xss_clean|numeric');
	   		$this->form_validation->set_rules('Apassed','Any other qualificatin passed/appeared','trim|xss_clean');
		
		//For Repopulating data
		
		if($_POST){
			$this->data['Sdepart']=$this->input->get_post('Sdepart');
			$this->data['Scenters']=$this->input->get_post('Scenters');
			$this->data['Stypeprogramme']=$this->input->get_post('Stypeprogramme');
			$this->data['Snameprogramme']=$this->input->get_post('Snameprogramme');
			$this->data['Smothername']=$this->input->get_post('Smothername');
			$this->data['Sfathername']=$this->input->get_post('Sfathername');
			$this->data['Scategory']=$this->input->get_post('Scategory');
			$this->data['Sgender']=$this->input->get_post('Sgender');
			$this->data['Sdob']=$this->input->get_post('Sdob');
			$this->data['Saadharnumber']=$this->input->get_post('Saadharnumber');
			$this->data['Sabgroup']=$this->input->get_post('Sabgroup');
			$this->data['Sreligion']=$this->input->get_post('Sreligion');
			$this->data['Spaddress']=$this->input->get_post('Spaddress');
			$this->data['Sdist']=$this->input->get_post('Sdist');
			$this->data['Spost']=$this->input->get_post('Spost');
			$this->data['Scity']=$this->input->get_post('Scity');
			$this->data['Sstate']=$this->input->get_post('Sstate');
			$this->data['Scountry']=$this->input->get_post('Scountry');
			$this->data['Spincode']=$this->input->get_post('Spincode');
			$this->data['Smobile']=$this->input->get_post('Smobile');
			$this->data['Semail']=$this->input->get_post('Semail');
			$this->data['Icity1']=$this->input->get_post('Icity1');

			$this->data['Hcname']=$this->input->get_post('Hcname');
			$this->data['Hsubject']=$this->input->get_post('Hsubject');
			$this->data['Hboard']=$this->input->get_post('Hboard');
			$this->data['Hyear']=$this->input->get_post('Hyear');
			$this->data['Hpassed']=$this->input->get_post('Hpassed');

			$this->data['Icname']=$this->input->get_post('Icname');
			$this->data['Isubject']=$this->input->get_post('Isubject');
			$this->data['Iboard']=$this->input->get_post('Iboard');
			$this->data['Iyear']=$this->input->get_post('Iyear');
			$this->data['Ipassed']=$this->input->get_post('Ipassed');

			$this->data['Gcname']=$this->input->get_post('Gcname');
			$this->data['Gsubject']=$this->input->get_post('Gsubject');
			$this->data['Gboard']=$this->input->get_post('Gboard');
			$this->data['Gyear']=$this->input->get_post('Gyear');
			$this->data['Gpassed']=$this->input->get_post('Gpassed');

			$this->data['Pcname']=$this->input->get_post('Pcname');
			$this->data['Psubject']=$this->input->get_post('Psubject');
			$this->data['Pboard']=$this->input->get_post('Pboard');
			$this->data['Pyear']=$this->input->get_post('Pyear');
			$this->data['Ppassed']=$this->input->get_post('Ppassed');

			$this->data['Acname']=$this->input->get_post('Acname');
			$this->data['Asubject']=$this->input->get_post('Asubject');
			$this->data['Aboard']=$this->input->get_post('Aboard');
			$this->data['Ayear']=$this->input->get_post('Ayear');
			$this->data['Apassed']=$this->input->get_post('Apassed');
			}

		
           	if($this->form_validation->run() == FALSE){
				//$message = '<h3>Your some detail is incorrect .</h3>';
	  			//$this->session->set_flashdata('msg',$message);
				//$this->load->view('student/student_step1');
		}
		else{
	    	// get the current academic year
			$cacadyer = $this->user_model->getcurrentAcadYear();
			 $protype = $this->input->post['Snameprogramme'];
			//print_r($protype);
		// get the program id
			$prgid = $this->Common_model->get_listspfic1('program','prg_id','prg_category',$protype);
			//print_r($prgid);
		// get the study code 
			$studycentr = $this->input->post['Scenters'];
			$sccode = $this->Common_model->get_listspfic1('study_center','sc_code','sc_name',$studycentr);
			//print_r($sccode);
		
		//insert into student master
		 $data = array(
			'sm_applicationno'   	=>	$_POST['Sanumber'],
                	'sm_fname'  		=>	ucwords($_POST['Sname']),
                	'sm_gender'  		=>	$_POST['Sgender'],
                	'sm_dob'   		=>	$_POST['Sdob'],
			'sm_uid'   		=>	$_POST['Saadharnumber'],
                	'sm_bloodgroup'   	=>	strtoupper($_POST['Sabgroup']),
                	'sm_religion'  		=>	strtoupper($_POST['Sreligion']),
                	'sm_mobile'   		=>	$_POST['Smobile'],
			'sm_email'   		=>	strtoupper($_POST['Semail']),
			'sm_category'		=>	$_POST['Scategory'],
			'sm_sccode'		=>	$_POST['Scenters']
                );
	 	//start the transaction
       	 	$this->db->trans_begin();
		$this->db->insert('student_master', $data);	
		$insertid = $this->db->insert_id();
		print_r($insertid);			
	
		//insert into student parent
		$parent = array(
			'spar_smid'		=>	$insertid,
			'spar_mothername'	=>	$_POST['Smothername'],
			'spar_fathername'   	=>	$_POST['Sfathername'],
                	'spar_paddress'  	=>	$_POST['Spaddress'],
			'spar_pdistrict'  	=>	$_POST['Sdist'],
			'spar_ppostoffice'  	=>	$_POST['Spost'],
                	'spar_pcity'  		=>	$_POST['Scity'],
                	'spar_pstate'   	=>	$_POST['Sstate'],
			'spar_pcountry'   	=>	$_POST['Scountry'],
			'spar_ppincode'   	=>	$_POST['Spincode'],
			'spar_caddress'  	=>	$_POST['Spaddress'],
			'spar_cdistrict'  	=>	$_POST['Sdist'],
			'spar_cpostoffice'  	=>	$_POST['Spost'],
                	'spar_ccity'  		=>	$_POST['Scity'],
                	'spar_cstate'   	=>	$_POST['Sstate'],
			'spar_ccountry'   	=>	$_POST['Scountry'],
			'spar_cpincode'   	=>	$_POST['Spincode']
                	
                );
		//print_r($parent);		
		$this->db->insert('student_parent', $parent);
		
		//insert into student education
		
		$Hedu = array(
			'sedu_smid'		=>	$insertid,
                	'sedu_class'   		=>	$_POST['Hcname'],
                	'sedu_subject'  	=>	$_POST['Hsubject'],
                	'sedu_board'  		=>	$_POST['Hboard'],
                	'sedu_passingyear'   	=>	$_POST['Hyear'],
			'sedu_resultstatus'   	=>	$_POST['Hpassed'],
			'sedu_institution'   	=>	$_POST['Scenters']

                );	
		//print_r($Hedu);
		$this->db->insert('student_education', $Hedu);
		
		$intn =$_POST['Icname'];
		$Iedu = array(
			'sedu_smid'		=>	$insertid,
			'sedu_class'   		=>	$_POST['Icname'],
                	'sedu_subject'  	=>	$_POST['Isubject'],
                	'sedu_board'  		=>	$_POST['Iboard'],
                	'sedu_passingyear'   	=>	$_POST['Iyear'],
			'sedu_resultstatus'   	=>	$_POST['Ipassed'],
			'sedu_institution'   	=>	$_POST['Scenters']
                );
		//print_r($Iedu);
		if(!empty($intn)){
		$this->db->insert('student_education', $Iedu);
		}

		$gran =$_POST['Gcname'];
		$Gedu = array(
			'sedu_smid'		=>	$insertid,
			'sedu_class'   		=>	$_POST['Gcname'],
                	'sedu_subject'  	=>	$_POST['Gsubject'],
                	'sedu_board'  		=>	$_POST['Gboard'],
                	'sedu_passingyear'   	=>	$_POST['Gyear'],
			'sedu_resultstatus'   	=>	$_POST['Gpassed'],
			'sedu_institution'   	=>	$_POST['Scenters']
                );
		//print_r($Gedu);
		if(!empty($gran)){
		$this->db->insert('student_education', $Gedu);
		}

		$pron =$_POST['Pcname'];
		$Pgedu = array(
			'sedu_smid'		=>	$insertid,
			'sedu_class'   		=>	$_POST['Pcname'],
                	'sedu_subject'  	=>	$_POST['Psubject'],
                	'sedu_board'  		=>	$_POST['Pboard'],
                	'sedu_passingyear'   	=>	$_POST['Pyear'],
			'sedu_resultstatus'   	=>	$_POST['Ppassed'],
			'sedu_institution'   	=>	$_POST['Scenters']
                );
		//print_r($Pgedu);
		if(!empty($pron)){
		$this->db->insert('student_education', $Pgedu);
		}

		$anyn =$_POST['Acname'];
		$Aedu = array(
			'sedu_smid'		=>	$insertid,
			'sedu_class'   		=>	$_POST['Acname'],
                	'sedu_subject'  	=>	$_POST['Asubject'],
                	'sedu_board'  		=>	$_POST['Aboard'],
                	'sedu_passingyear'   	=>	$_POST['Ayear'],
			'sedu_resultstatus'   	=>	$_POST['Apassed'],
			'sedu_institution'   	=>	$_POST['Scenters']
                );
		//print_r($Aedu);
		if(!empty($anyn)){
		$this->db->insert('student_education', $Aedu);
		}

		//insert into student enterence exam
		$enterence = array(
			'seex_smid'		=>	$insertid,
			'seex_rollno'		=>	$_POST['Sanumber'],
			//'seex_examname'   	=>	$_POST[''],
                	//'spar_paddress'  	=>	$_POST['Spaddress'],
                	//'spar_pcity'  	=>	$_POST['Scity'],
                	//'spar_pstate'   	=>	$_POST['Sstate'],
			//'spar_pcountry'   	=>	$_POST['Scountry'],
			//'spar_ppincode'   	=>	$_POST['Spincode']
			
                );
		$this->db->insert('student_entrance_exam',$enterence);

		//insert into student program
		$sem=1;
		$stuprog = array(
			'sp_smid'		=>	$insertid,
			'sp_sccode'		=>	$_POST['Scenters'],
			'sp_pcategory'   	=>	$_POST['Stypeprogramme'],
                	'sp_programid'  	=>	$_POST['Snameprogramme'],
                	'sp_acadyear'  		=>	$cacadyer,
			'sp_semester'  		=>	$sem,
			'sp_deptid'		=> 	$_POST['Sdepart']
                );
		$this->db->insert('student_program', $stuprog);
		$getprogid = $this->db->insert_id();

		//insert into student fees
		$stufees = array(
			'sfee_smid'		=>	$insertid,
			'sfee_spid'		=>	$getprogid
                );	
		$this->db->insert('student_fees',$stufees);

		//insert into admission step
		$no=1;
		$cdate = date('Y-m-d H:i:s');
		$stuadmission = array(
			'application_no'	=>	$_POST['Sanumber'],
			'student_masterid'	=>	$insertid,
			'step1_status'   	=>	$no,
                	'step1_date'  		=>	$cdate
                	
                );
		$this->db->insert('admissionstep', $stuadmission);

			//$result1 = $this->Student_model->studentId($insertid);
			//print_r($result1);
			$session_data = array(
					'sm_id'=> $insertid,
					//'sm_fname' => $result1[0]->sm_fname,
					//'sm_applicationno' => $result1[0]->sm_applicationno,
					//'logged_in' => true
			);
		
		$this->session->set_userdata($session_data);
			//$message = '<h3>You are registered successfully .</h3>';
			//$this->session->set_flashdata('msg',$message);
			//$this->load->view('student/student_step2',$insertid);
			
	
	  	//make transaction complete
        	$this->db->trans_complete();
			
	 	//check if transaction status TRUE or FALSE
        	if ($this->db->trans_status() === FALSE) {
            	//if something went wrong, rollback everything
            		$this->db->trans_rollback();
			$this->logger->write_logmessage("insert", "Step1 error in insert student detail ");
                    	$this->logger->write_dblogmessage("insert", "Step1 error in insert student detail ");
			$this->load->view('student/student_step1');
			//redirect('student/student_step1');
           		//return FALSE;
      		  } else {
            		//if everything went right, commit the data to the database
           		 $this->db->trans_commit();
			 $this->logger->write_logmessage("insert", "Step1 insert detail in student_master table.");
               		 $this->logger->write_dblogmessage("insert", "Step1 insert detail in student_master table.");
			 redirect('student/student_step2');
           		 //return TRUE;
       			 }

		}/*close else*/

	}/*Close if post */
	$this->load->view('student/student_step1');
   }/*close function step1*/

	public function student_step2(){		
		//$array_items = array('success' => '', 'error' => '', 'warning' =>'');
        	//$this->session->set_flashdata($array_items);
		$insertid=$this->session->userdata['sm_id'];
		// dispaly the term and condition
		//check the acceptance		
		if(isset($_POST['criteria'])){
			$this->form_validation->set_rules('crieteria[]', 'Checked', 'trim|required|xss_clean');
			if ($this->form_validation->run() == FALSE) {
				if(!$this->input->post('crieteria[]')){
    		 	    		echo "Please read and accept our terms and conditions.";
					// send to same page for acceptance of our term and condition
					$this->load->view('student/student_step2');
				} 
			}else{
				//if yes then update admissionstep table
				//start the transaction
       	 			$this->db->trans_begin();
				$cdate = date('Y-m-d H:i:s');
				//$id= $this->input->get_post('Sid');	
				$step2 = array(
					'step2_status'	       =>		 1,
					'step2_date'	       =>		 $cdate,
				);
				$updatst2 = $this->Common_model->updaterec('admissionstep', $step2,'student_masterid',$insertid);
				//$this->db->where('student_masterid',$insertid);
				//$this->db->update('admissionstep', $step2);		
				//set flag for each step, if any step fails revert all steps and return to same step
				//make transaction complete
        			$this->db->trans_complete();
			
	 			//check if transaction status TRUE or FALSE
        			if ($this->db->trans_status() === FALSE) {
            				//if something went wrong, rollback everything
            				$this->db->trans_rollback();
					//else stay with step2 or redirect to login page
					$this->logger->write_logmessage("update", "Error in step2 to update admission step");
                    			$this->logger->write_dblogmessage("update", "Error in step2 to update admission step" );
					redirect('student/student_step2');
           				//return FALSE;
      				} else {
            				//if everything went right, commit the data to the database
           		 		$this->db->trans_commit();
           				// return TRUE;
					//$message = '<h3>Upload your sign and photo .</h3>';
					//$this->session->set_flashdata('msg',$message);
					//redirect to step 3
					//$this->load->view('student/student_step3');
					 $this->logger->write_logmessage("update", "Step2 admission step table upadetd");
               		 		 $this->logger->write_dblogmessage("update", "Step2 admission step table upadetd");
					redirect('student/student_step3');
				}							
			}//close of else validation
		}// close for check submit button		
		$this->load->view('student/student_step2');	
	}

	public function student_step3(){
		$id = $this->session->userdata['sm_id'];
		
		//upload photo and signature in dir which is not accessble directly with size limit photo 200kb and signature 20kb
		// for clearing the previous sucess/error flashdata
		//$array_items = array('success' => '', 'error' => '', 'warning' =>'');
        	//$this->session->set_flashdata($array_items);

        	if(isset($_POST['submitStep3'])) {
			$this->form_validation->set_rules('userfile', 'Upload Photo', 'trim|required|xss_clean');
			$this->form_validation->set_rules('userfile2', 'Upload Signature', 'trim|required|xss_clean');

			//if ($this->form_validation->run() == FALSE) {
			//$message = '<h3>Try again upload your photo and signature.</h3>';
			//$this->session->set_flashdata('msg',$message);
			//$this->load->view('student/student_step3');
			//}
			//else{
            		//Check whether user upload picture
			

            		if((isset($_FILES['userfile']))&&(isset($_FILES['userfile2']))){
	 	 		//upload photo and signature in dir which is not accessble directly with size limit photo 200kb and signature 20kb
				$_FILES['userFile']['name'] = $_FILES['userfile']['name'];
               	 		$_FILES['userFile']['type'] = $_FILES['userfile']['type'];
                		$_FILES['userFile']['tmp_name'] = $_FILES['userfile']['tmp_name'];
                		//$_FILES['userFile']['error'] = $_FILES['userfile']['error'];
                		$_FILES['userFile']['size'] = $_FILES['userfile']['size'];

				$_FILES['userFile2']['name'] = $_FILES['userfile2']['name'];
                		$_FILES['userFile2']['type'] = $_FILES['userfile2']['type'];
                		$_FILES['userFile2']['tmp_name'] = $_FILES['userfile2']['tmp_name'];
                		//$_FILES['userFile2']['error'] = $_FILES['userfile2']['error'];
                		$_FILES['userFile2']['size'] = $_FILES['userfile2']['size'];
				
				/*if($_FILES['userfile']['size'] > 2000) {
         				$errors[]='Photo size must be excately 2 MB';
					
      				}

				if($_FILES['userfile2']['size'] > 1000) {
         				$errors[]='Signature size must be excately 1 MB';
      				}*/

				$name2 = $_FILES['userfile2']['name'];
				$name = $_FILES['userfile']['name'];
				//$upload = 'application/student_sign_photo/student_photo/';
				//$upload2 = 'application/student_sign_photo/student_sign/';

                		$config['upload_path'] = 'uploads/student_sign_photo/student_photo/';
				$config2['upload_path'] = 'uploads/student_sign_photo/student_sign/';
				$config2['max_size'] = '20';
				$config['max_size'] = '200';
              	 		$config['allowed_types'] = 'jpg|jpeg|png|gif';
				$config2['allowed_types'] = 'jpg|jpeg|png|gif';
               			$config['file_name'] = $id.$name;
				$config2['file_name'] = $id.$name2;
				$config['overwrite'] = TRUE;
				$config2['overwrite'] = TRUE;
				
				//Image resize
				/*$config['image_library'] = 'gd2';
				$config['source_image'] = 'uploads/student_sign_photo/student_photo/';
				$config['create_thumb'] = TRUE;
				$config['maintain_ratio'] = TRUE;
				$config['width'] = 75;
				$config['height'] = 50;

				$this->load->library('image_lib',$config); 

				$config2['image_library'] = 'gd2';
				$config2['source_image'] = 'uploads/student_sign_photo/student_sign/';
				$config2['create_thumb'] = TRUE;
				$config2['maintain_ratio'] = TRUE;
				$config2['width'] = 75;
				$config2['height'] = 50;

				$this->load->library('image_lib',$config2); 

				$this->image_lib->resize();*/

				//remove original file
				//@unlink( $config['source_image'] );

        	    		//Prepare array of user data
     	       			$userData = array(
        	        		'sm_photo'          	 =>		$id.$name,
					'sm_signature'         	 =>		$id.$name2 
           	     		);
				
        	     		$this->db->where('sm_id', $id);
		    		$update = $this->db->update('student_master',$userData);
				$this->logger->write_logmessage("update", "Step 3 student_master table upload file updated.");
                    		$this->logger->write_dblogmessage("update", "Step 3 student_master table upload file updated." );
           			//$updst = $this->Common_model->updaterec('student_master',$userData,'sm_id', $id);
				
        	    		//Storing insertion status message. update admissionstep table
				//update student master
				$cdate = date('Y-m-d H:i:s');				
				$step3 = array(
					'step3_status'	       =>		 1,
					'step3_date'	       =>		 $cdate
				);
				//$this->db->where('student_masterid',$id);
				//$this->db->update('admissionstep', $step3);
				$updst3 = $this->Common_model->updaterec('admissionstep', $step3,'student_masterid',$id);

				$this->logger->write_logmessage("update", "Step 3 admission step table updated.");
                    		$this->logger->write_dblogmessage("update", "Step 3 admission step table updated." );

        			//set flag for each step, if any step fails revert all steps and return to same step
				//Load upload library and initialize configuration code for photo
               	 						
               	 		$this->load->library('upload',$config);
               		 	$this->upload->initialize($config);

                		if($this->upload->do_upload('userfile')){
                   	 		$uploadData = $this->upload->data();
                   	 		$picture = $uploadData['file_name'];
					
                		}else{
                    			$picture = '';
					$error =  array('error' => $this->upload->display_errors());
					//print_r($error);
					foreach ($error as $item => $value):
						$ferror = $ferror.$value;
					endforeach;
					$ferror=str_replace("\r\n","",$ferror);
					$simsg = "The permitted size of Photo is 200kb and Signature is 20kb";
					$ferror = $simsg.$ferror;
					$this->logger->write_logmessage("upload","step 3 File upload error", $ferror);
					$this->logger->write_dblogmessage("upload","step 3 File upload error", $ferror);
					$this->session->set_flashdata('error', $ferror);
					redirect('student/student_step3');
                		}
            		

				//Load upload library and initialize configuration code for sign
        	       	 	$this->load->library('upload',$config2);
        	       		$this->upload->initialize($config2);
                
        	        	if($this->upload->do_upload('userfile2')){
        	           		$uploadData = $this->upload->data();
        	           		$picture = $uploadData['file_name'];
					$this->logger->write_logmessage("update","File updated", "Step 3 file update");
					$this->logger->write_dblogmessage("update","File updated", "Step 3 file update");
					$this->session->set_flashdata('success', 'Your photo and signature successfully uploaded.');
					redirect('student/student_step4');
        	        	}else{
        	            		$picture = '';
					$error =  array('error' => $this->upload->display_errors());
					foreach ($error as $item => $value):
						$ferror = $ferror.$value;
					endforeach;
					$simsg = "The permitted size of Photo is 200kb and signature is 20kb";
					$ferror = $simsg.$ferror;
					$this->logger->write_logmessage("upload","step 3 File upload error", $ferror);
					$this->logger->write_dblogmessage("upload"," step 3 File upload error", $ferror);
					$this->session->set_flashdata('error', $ferror);
					redirect('student/student_step3');
        	        	}
            	
       			 	//Form for adding user data
			   }
     		    }
			$this->load->view('student/student_step3');	
    	}
	
	public function student_step4(){
		$Sid = $this->session->userdata['sm_id'];
		$this->appno=$this->Common_model->get_listspfic1('student_master','sm_applicationno','sm_id',$Sid)->sm_applicationno;
		$this->sname=$this->Common_model->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		$this->fname=$this->Common_model->get_listspfic1('student_parent','spar_fathername','spar_smid',$Sid)->spar_fathername;
		$this->gender=$this->Common_model->get_listspfic1('student_master','sm_gender','sm_id',$Sid)->sm_gender;
		$this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->Common_model->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;
		$this->catid=$this->Common_model->get_listspfic1('student_master','sm_category','sm_id',$Sid)->sm_category;
		// in future we add acdamic year
		//$wharray = array('fm_programid' => $this->prgid,('fm_gender' => (All)||($this->gender))&&('fm_category'=>(All)||($this->catid)));
		// display fees detail on the basis of gender, category and program with semester
		$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => 1);
		$sarray = 'fm_head,fm_amount';
		$wgenr = array('All', $this->gender);
		$wcateid = array('1', $this->catid);
		$this->db->select($sarray);
		$this->db->from('fees_master');
		$this->db->where_in('fm_gender',$wgenr);
		$this->db->where_in('fm_category',$wcateid);
		$this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
		
		//fees paid by online /offline
		//if online then redirect to payment gateway
		//else open a fees payment entry details page		
		//set flag for each step, if any step fails revert all steps and return to same step
		$this->load->view('student/student_step4');
	}

/******************************************Offline payment code start**********************************************************/

	public function offlinePayment(){
		$Sid = $this->session->userdata['sm_id'];
		//$totalfees = $_POST['totalfees'];
		
		//$this->appno=$this->Common_model->get_listspfic1('student_master','sm_applicationno','sm_id',$Sid)->sm_applicationno;
		//$this->sname=$this->Common_model->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		//$this->fname=$this->Common_model->get_listspfic1('student_parent','spar_fathername','spar_smid',$Sid)->spar_fathername;
		$this->gender=$this->Common_model->get_listspfic1('student_master','sm_gender','sm_id',$Sid)->sm_gender;
		$this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->Common_model->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;
		$this->catid=$this->Common_model->get_listspfic1('student_master','sm_category','sm_id',$Sid)->sm_category;
		//calculate total payble fees
		// in future we add acdamic year
		//$wharray = array('fm_programid' => $this->prgid,('fm_gender' => (All)||($this->gender))&&('fm_category'=>(All)||($this->catid)));
		//
		$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => 1);
		$sarray = 'fm_head,fm_amount';
		$wgenr = array('All', $this->gender);
		$wcateid = array('1', $this->catid);

		$this->db->select($sarray);
		$this->db->from('fees_master');
		$this->db->where_in('fm_gender',$wgenr);
		$this->db->where_in('fm_category',$wcateid);
		$this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
		//print_r($this->feesresult);
			$this->totalfees = '';
			foreach($this->feesresult as $d2){
		 		$d2->fm_head;$d2->fm_amount;
				$this->totalfees = $this->totalfees+$d2->fm_amount;
			//print_r($totalfees);
			}
		
		//fees paid by offline
				
		if(isset($_POST['addFees'])) {			
			$this->form_validation->set_rules('refNo','Reference number','trim|xss_clean|numeric|required');
            		$this->form_validation->set_rules('bank','Bank detail','trim|xss_clean|required');
            		$this->form_validation->set_rules('amount','Amount','trim|xss_clean|required');
            		$this->form_validation->set_rules('ftype','fees type field','trim|xss_clean|required');

			if($_POST){
				$this->data['refNo'] = $this->input->get_post('refNo');
				$this->data['bank']  = $this->input->get_post('bank');
				$this->data['amount']= $this->input->get_post('amount');
				$this->data['ftype']=$this->input->get_post('ftype');
			}

			if($this->form_validation->run() == FALSE){				
				$this->load->view('student/offlinePayment');
				return;
			}else{
				//start the transaction
       	 			$this->db->trans_begin();		
				//update student fees table
				if(($_POST['amount']) == $this->totalfees)	{			
				$step4 = array(
					'sfee_referenceno'   	=>	$_POST['refNo'],
                			'sfee_bankname'  	=>	$_POST['bank'],
                			'sfee_feeamount'  	=>	$_POST['amount'],
                			'sfee_feename'   	=>	$_POST['ftype'],
					'sfee_paymentmethod'    =>	'Offline'
                				);
				//print_r($data);
				$this->db->where('sfee_smid',$Sid);
				$this->db->update('student_fees', $step4); 
				}
				else {
					$message = '<h3>The payble fees and fees deposit in bank should be equal.</h3>';
	  				$this->session->set_flashdata('msg',$message);
					$this->logger->write_logmessage("view", "Offline payment  fees match error.");
	                    		$this->logger->write_dblogmessage("view", "Offline payment fees match error." );
					$this->load->view('student/offlinePayment');
					return;
				 }
				$this->logger->write_logmessage("update", "Step 4 fees_master table update.");
                    		$this->logger->write_dblogmessage("update", "Step 4 fees_master table update." );
				//get the email,fname and mobile from student master
				//$id = $this->input->get_post('fid');
				$email= $this->Common_model->get_listspfic1('student_master','sm_email','sm_id',$Sid)->sm_email;
				$mobile= $this->Common_model->get_listspfic1('student_master','sm_mobile','sm_id',$Sid)->sm_mobile;
				$name= $this->Common_model->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;	
					
				//print_r($sccode);		
				//insert into edrpuser in login database
				// check for duplicate
                            	$isdupl= $this->login_model->isduplicate('edrpuser','username',$email );
			
                            	if(!$isdupl){
                                	//generate 10 digit random password
				    	$passwd=$this->Common_model->randNum(10);	
					// generate the hash of password
				    	$password=md5($passwd);
					// insert data into edrp user db1
					$dataeu = array(
                                    		'username'=> $email,
                                    		'password'=> $password,
				    		'email'=> $email,
				    		'componentreg'=> '*',
				    		'mobile'=>$mobile,
				    		'status'=>1,
                                   		 'category_type'=>'Student',
                                   		 'is_verified'=>1
            				);
					$this->db2->insert('edrpuser',$dataeu);
					$this->logger->write_logmessage("insert", "Step 4 data insert in edrpuser table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in edrpuser table." );
					//$getid = $this->db2->insert_id();
					//print_r($dataeu);
					//get the insert id of edrp user
					$getid= $this->login_model->get_listspfic1('edrpuser','id','username',$email );
					$insid=$getid->id;
					//print_r($insid);
					// get user id from  edrp table  in login database
					//insert into user profile and user last login status in login database
		 			$dataup = array(
				  		'userid'=> $insid,
						'firstname'=> $name,
						'lang'=> 'english',
						'mobile'=>$mobile,
						'status'=>1
                                    	);
					$this->db2->insert('userprofile',$dataup);
					$this->logger->write_logmessage("insert", "Step 4 data insert in userprofile table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in userprofile table." );
					//insert into userroletype group
					$roleid = $this->Common_model->get_listspfic1('role','role_id','role_name','Student')->role_id;
					$sccode = $this->Common_model->get_listspfic1('student_master','sm_sccode','sm_id',$Sid)->sm_sccode;
					$scid = $this->Common_model->get_listspfic1('study_center','sc_id','sc_code',$sccode)->sc_id;
					$deptid = $this->Common_model->get_listspfic1('student_program','sp_deptid','sp_smid',$Sid)->sp_deptid;
					$dataurt = array(
				        	'userid'=> $insid,
				           	'roleid'=> $roleid,
				           	'deptid'=> $deptid,
				           	'scid'  => $scid,
				           	'usertype'=>"Student"
        	    			);
					$this->db->insert('user_role_type',$dataurt);
					$this->logger->write_logmessage("insert", "Step 4 data insert in user_role_type table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in user_role_type table." );
					// update into student master userid
					$Supdate = array(
		                		'sm_userid'          	 =>		$insid,
	           	     		);
					//print_r($Supdate);
		             		$this->db->where('sm_id',$Sid);
	    				$insertUserData = $this->db->update('student_master',$Supdate);
					$this->logger->write_logmessage("update", "Step 4 user id update in student_master table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 user id update in student_master table." );

					//update admissionstep table
					$cdate = date('Y-m-d H:i:s');
 					$step4 = array(
						'step4_status'	       =>		 1,
						'step4_date'	       =>		 $cdate
					);
					//print_r($step3);
					//$this->db->where('student_masterid',$Sid);
					//$this->db->update('admissionstep', $step4);
					$updast4 = $this->Common_model->updaterec('admissionstep', $step4,'student_masterid',$Sid);
					$this->logger->write_logmessage("update", "Step 4 update admissionstep table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 update admissionstep table.");

					//make transaction complete
		
					$this->db->trans_complete();
					//$this->load->view('student/offlinePayment');		
 
	 				//check if transaction status TRUE or FALSE
        				if ($this->db->trans_status() === FALSE) {
            					//if something went wrong, rollback everything
            					$this->db->trans_rollback();
						//else stay with step4
						$message = '<h3>Your some field are incorrect.</h3>';
	  					$this->session->set_flashdata('msg',$message);
						$this->logger->write_logmessage("insert", "Step 4 error update and insert");
                    				$this->logger->write_dblogmessage("insert", "Step 4 error update and insert" );
						redirect('student/offlinePayment');
						
           					//return FALSE;
      		  			} else {
            					//if everything went right, commit the data to the database
           					 $this->db->trans_commit();
           					// return TRUE;

						//if sucess send mail to user with login details 
		 				$sub='Student Registration' ;
                        			$mess="You are registration is complete. The user id ".$email ." and password is ".$passwd ;
                       				$mails = $this->mailsend_model->mailsnd($email, $sub, $mess);
						 //  mail flag check 			
						if($mails){
                        				$error[] ="At row".$i."sufficient data and mail sent sucessfully";
                        				$this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email);
		      					$this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email);
			    			}
						else{
        	       					$error[] ="At row".$i."sufficient data and mail does sent";
	                				$this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email ." and mail does sent");
							$this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does sent" );
			   			}
						//$message = '<h3>Your fees submit.</h3>';
	  					//$this->session->set_flashdata('msg',$message);			
						//$this->load->view('student/student_step5');
						$this->logger->write_logmessage("insert", "Step 4 detail update and insert successfully.");
                    				$this->logger->write_dblogmessage("insert", "Step 4 detail update and insert successfully." );
						redirect('student/student_step5');	
       					}// close else transcation failure and else is missing
				}// close of if is duplication
			
			}/*close else validation*/
				
		}/*close post submit*/

		//set flag for each step, if any step fails revert all steps and return to same step
		$this->load->view('student/offlinePayment');
	}	

/*************************************offline payment end***************************************************************/




	
	
	public function student_step5(){
		   //get sm_id from session
			$id = $this->session->userdata['sm_id'];
			//print_r($id);
		
		//get the education detail from student_education
		// availabe final form for printing
		$this->sname=$this->Common_model->get_listspfic1('student_master','sm_fname','sm_id',$id)->sm_fname;
		$this->mobile=$this->Common_model->get_listspfic1('student_master','sm_mobile','sm_id',$id)->sm_mobile;
		$this->email=$this->Common_model->get_listspfic1('student_master','sm_email','sm_id',$id)->sm_email;
		$this->dob=$this->Common_model->get_listspfic1('student_master','sm_dob','sm_id',$id)->sm_dob;
		$this->uid=$this->Common_model->get_listspfic1('student_master','sm_uid','sm_id',$id)->sm_uid;
		$this->bgroup=$this->Common_model->get_listspfic1('student_master','sm_bloodgroup','sm_id',$id)->sm_bloodgroup;

		$this->mname = $this->Common_model->get_listspfic1('student_parent','spar_mothername','spar_smid',$id)->spar_mothername;		
		$this->fname=$this->Common_model->get_listspfic1('student_parent','spar_fathername','spar_smid',$id)->spar_fathername;
		$this->ncid = $this->Common_model->get_listspfic1('student_program','sp_programid','sp_smid',$id)->sp_programid;
		$this->pname = $this->Common_model->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->gender=$this->Common_model->get_listspfic1('student_master','sm_gender','sm_id',$id)->sm_gender;
	
		//postal address detail
		$this->padd=$this->Common_model->get_listspfic1('student_parent','spar_paddress','spar_smid',$id)->spar_paddress;
		$this->cadd=$this->Common_model->get_listspfic1('student_parent','spar_caddress','spar_smid',$id)->spar_caddress;
		$this->pcity=$this->Common_model->get_listspfic1('student_parent','spar_pcity','spar_smid',$id)->spar_pcity;
		$this->ccity=$this->Common_model->get_listspfic1('student_parent','spar_ccity','spar_smid',$id)->spar_ccity;
		$this->ppost=$this->Common_model->get_listspfic1('student_parent','spar_ppostoffice','spar_smid',$id)->spar_ppostoffice;
		$this->cpost=$this->Common_model->get_listspfic1('student_parent','spar_cpostoffice','spar_smid',$id)->spar_cpostoffice;
		$this->pdist=$this->Common_model->get_listspfic1('student_parent','spar_pdistrict','spar_smid',$id)->spar_pdistrict;
		$this->cdist=$this->Common_model->get_listspfic1('student_parent','spar_cdistrict','spar_smid',$id)->spar_cdistrict;
		$this->pstat=$this->Common_model->get_listspfic1('student_parent','spar_pstate','spar_smid',$id)->spar_pstate;
		$this->cstat=$this->Common_model->get_listspfic1('student_parent','spar_cstate','spar_smid',$id)->spar_cstate;
		$this->ppin=$this->Common_model->get_listspfic1('student_parent','spar_ppincode','spar_smid',$id)->spar_ppincode;
		$this->cpin= $this->Common_model->get_listspfic1('student_parent','spar_cpincode','spar_smid',$id)->spar_cpincode;
		
		$this->cateid=$this->Common_model->get_listspfic1('student_master','sm_category','sm_id',$id)->sm_category;
		$this->catename=$this->Common_model->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;

		//fees detail
		$this->prog=$this->Common_model->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->amnt=$this->Common_model->get_listspfic1('student_fees','sfee_feeamount','sfee_smid',$id)->sfee_feeamount;
		$this->pmethod=$this->Common_model->get_listspfic1('student_fees','sfee_paymentmethod','sfee_smid',$id)->sfee_paymentmethod;
		$this->rno=$this->Common_model->get_listspfic1('student_fees','sfee_referenceno','sfee_smid',$id)->sfee_referenceno; 
		$this->fid=$this->Common_model->get_listspfic1('student_fees','sfee_id','sfee_smid',$id)->sfee_id; 
		$this->bname=$this->Common_model->get_listspfic1('student_fees','sfee_bankname','sfee_smid',$id)->sfee_bankname;
		
		//education detail
		$this->seresult = $this->Common_model->get_listrow('student_education','sedu_smid',$id)->result();
		
		
		//get photo or sign
		
		$this->phresult = $this->Common_model->get_listspfic1('student_master','sm_photo','sm_id',$id)->sm_photo;
		$this->signresult = $this->Common_model->get_listspfic1('student_master','sm_signature','sm_id',$id)->sm_signature;
				
		
		//set the new values in session(role,student user_id and status login)
		//redirect to student home page for subject selection after 5 minute/give the button move to student home page
		
		//update admissionstep table
			$cdate = date('Y-m-d H:i:s');
			$step5 = array(
				'step5_status'	       =>		 1,
				'step5_date'	       =>		 $cdate
			);
			$updst5 = $this->Common_model->updaterec('admissionstep', $step5,'student_masterid',$id);
			//$this->db->where('student_masterid',$id);
			//$this->db->update('admissionstep', $step5);
			$this->logger->write_logmessage("update", "Step 5 admission step updated successfully.");
                    	$this->logger->write_dblogmessage("update", "Step 5 admission step updated successfully." );
			$this->load->view('student/student_step5');
	}

	public function student_home($applicationno){
		$applicationno = $this->session->userdata['app_no'];	
		
		 if(isset($_POST['studentHome'])) {
			
				//get the userid from student master
				$smres=$this->Common_model->get_listspfic1('student_master','sm_userid','sm_applicationno',$applicationno);
				$suid=$smres->sm_userid;
				//print_r($smres);				
				//get the username from edrpuser
				$usernme=$this->login_model->get_listspfic1('edrpuser','username','id',$suid)->username;
				//print_r($usernme);
				//check the value set in session if not then
				// set the student master and application number in session
				$data = [
                        	        'id_user' => $suid,
			                'username' => $usernme,
                        	        'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				$this->logger->write_logmessage("view", "successfully show student home.");
                    		$this->logger->write_dblogmessage("view", "successfully show student home." );
			       redirect('studenthome'); 
			}
			
			else{
			$this->logger->write_logmessage("view", "Error in redirect to studrnt home in step5.");
                    	$this->logger->write_dblogmessage("view", "Error in redirect to studrnt home in step5." );
			$this->load->view('student/student_step5');
			
			}
	}


}

?>
