<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/******************************************************
* @name carrier.php(controller)    		      *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/
class Carrier extends CI_Controller {

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
		$this->db3 = $this->load->database('bhrm', TRUE);
		$this->load->model('hrmgmt_model','hrmmodel');
		$this->load->model('Dependrop_model',"depmodel");

    	}
	
	public function adver(){
		$this->load->view('advertisement/adver_add_detail');
	}

	public function index() {
            if($_POST) {
                $result = $this->login->validate_user($_POST);
                /*get role by using model class and set templates according to role*/
                $roles=$this->commodel->get_listspficarry('user_role_type','roleid','userid',$result->id);
                if(!empty($result)) {
             		if(!empty($roles)){   
                    		if(count($roles) == 1){
                        		foreach($roles as $row):
                            			if($row->roleid == 1){
                                			$data = [
			                                'id_user' => $result->id,
                        			        'username' => $result->username,
			                                'id_role' => $row->roleid
                        			        ];
			                                $this->session->set_userdata($data);
                        			        redirect('home');
                           			} 
                            			if($row->roleid == 2){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('facultyhome'); 
                        			}
                            			if($row->roleid == 3){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('studenthome'); 
                        			}
                        		endforeach;   
                    		}else{
                        		foreach($roles as $row):
                            			$data = [
		                                'id_user' => $result->id,
                		                'username' => $result->username,                                    
                                		];
                            			$this->session->set_userdata($data);
                            			redirect('rolehome'); 
                        		endforeach;
                    		}
			}else{ //if close role empty
                    		$this->session->set_flashdata('flash_data', 'You do not have any role in this system!');
                    		redirect('welcome');
            		}
                }//if empty result validate close 
                else {
                    $this->session->set_flashdata('flash_data', 'Username or password is wrong!');
                    redirect('welcome');
                }
            }    
            $this->load->view("welcome_message");
        }//close index function


	public function important_data(){
		$this->dateres=$this->hrmmodel->get_listmore('job_open','job_adverno,job_startdateonlineform,job_lastdateonlineform,job_lastdateformreach');
		$this->load->view('carrier/imp_data');
	}

	public function important_info(){
		$this->load->view('carrier/imp_info');
	}

	public function how_apply(){
		$this->load->view('carrier/how_apply');
	}

	public function mail_address(){
		$this->load->view('carrier/mail_add');
	}

	public function contact_us(){
		$this->load->view('carrier/contact');
	}

	public function faq(){
		$this->load->view('carrier/faq');
	}

//This function check for duplicate aadhar card 		   
   /* public function aadharexist($amid) {
        $is_exist = $this->commodel->isduplicate('applicant_data','amid',$amid);
	//print_r($is_exist);
        if ($is_exist)
        {
		$this->form_validation->set_message('aadharexist','Aadhar number' ." ".$amid. " ".'is already exist check your aadhar number again.');
            return false;
        }
        else {
            return true;
        }
    }*/

	 //check for completed admission step
   /*     public function studentstep($applicationno){

                //check the existence of entry in applicationstep table 
                $this->resstep=$this->hrmmodel->get_listrow('applicationstep','application_no',$applicationno)->result();
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
                                redirect('carrier/applicant_step1');

                        }
                        else if($stp2 == 0 || $stp2 == NULL){
                                //check the value set in session if not then
                                // get the student master id
                                $this->smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;

                                // set the student master and application number in session
                                $data = [
                                        'sm_id' => $this->smid,
                                        'app_no' => $applicationno,
                                        'id_role' => 3
                                        ];
                               $this->session->set_userdata($data);
                                // redirect to step2 for completion
                                redirect('carrier/applicant_step2');
                        }
			else if($stp3 == 0 || $stp3 == NULL){
                                //check the value set in session if not then
                                // get the student master id
                                        $this->smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
                                // set the student master and application number in session
                                $data = [
                                        'sm_id' => $this->smid,
                                        'app_no' => $applicationno,
                                        'id_role' => 3
                                        ];
                                $this->session->set_userdata($data);
                                // redirect to step3 for completion
                                redirect('carrier/applicant_step3');
                        }
                        else if($stp4 == 0 || $stp4 == NULL){
                                //check the value set in session if not then
                                // get the student master id
                                $this->smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
                                // set the student master and application number in session
                                $data = [
                                        'sm_id' => $this->smid,
                                        'app_no' => $applicationno,
                                        'id_role' => 3
                                        ];
                               $this->session->set_userdata($data);
                                // redirect to step4 for completion
                                redirect('carrier/applicant_step4');
                        }
			else if($stp5 == 0 || $stp5 == NULL){
                                //check the value set in session if not then
                                // get the student master id
                                $this->smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
                                // set the student master and application number in session
                                $data = [
                                        'sm_id' => $this->smid,
                                        'app_no' => $applicationno,
                                        'id_role' => 3
                                        ];
                               $this->session->set_userdata($data);
                                // redirect to step5 for completion
                                redirect('carrier/applicant_step5');
                        }
                        else{   //bracess for the else is added again by nks
                                // if step 5 is completed then redirect to student home page else redirect to correct step page
                                //get the userid from student master
                                $this->smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
                                $smres= $this->commodel->get_listspfic1('student_master','sm_userid','sm_id',$this->smid);
                                $suid=$smres->sm_userid;
                                //print_r($suid.$smres);                                
                                //get the username from edrpuser
                                $usernme=$this->logmodel->get_listspfic1('edrpuser','username','id',$suid)->username;
                                //check the value set in session if not then
                                // set the student master and application number in session
                                $data = [
                                        'id_user' => $suid,
                                        'username' => $usernme,
                                        'id_role' => 3
                                        ];
                               $this->session->set_userdata($data);
                               redirect('carrier/applicant_step5');
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
                        redirect('carrier/applicant_step1');
                }
        }*/

	public function applicant_step1(){
		$array_items = array('success' => '', 'error' => '', 'warning' =>'');
       		$this->session->set_flashdata($array_items);
		//check the date current date is less than the last date
		$cdate = date('Y-m-d H:i:s');
		$this->advtno = $this->hrmmodel->get_listspfic2('job_open', 'job_adverno','','job_lastdateonlineform>',$cdate,'job_adverno');

		if(isset($_POST['register'])){
			$this->form_validation->set_rules('aadvt','Advertisement number','trim|xss_clean|required');
			$this->form_validation->set_rules('apost','Post applied','trim|xss_clean|required');
			$this->form_validation->set_rules('adept','Department','trim|xss_clean|required');
			$this->form_validation->set_rules('apcode','Post code','trim|xss_clean|required');
			$this->form_validation->set_rules('afieldspecia','Specialization','trim|xss_clean|required');

			$this->form_validation->set_rules('aucate','Under category','trim|xss_clean|required');
			$this->form_validation->set_rules('afname','First name','trim|xss_clean|required');
			$this->form_validation->set_rules('amname','Middle name','trim|xss_clean|required');
			$this->form_validation->set_rules('alname','Last name','trim|xss_clean|required');
			$this->form_validation->set_rules('afathername','Father name','trim|xss_clean|required');

			$this->form_validation->set_rules('amothername','Mother name','trim|xss_clean|required');
			$this->form_validation->set_rules('aadharno','Aadhar number','trim|xss_clean|is_numeric|required|max_length[12]');//|callback_aadharexist');
			$this->form_validation->set_rules('acadd','Correspondence address','trim|xss_clean|required');
			$this->form_validation->set_rules('apadd','permanant address','trim|xss_clean|required');
			$this->form_validation->set_rules('adob','Date of Birth','trim|xss_clean|required');

			$this->form_validation->set_rules('adobplace','Birth place','trim|xss_clean|required');
			$this->form_validation->set_rules('aagecutoff','Age cut','trim|xss_clean|required');
			$this->form_validation->set_rules('agender','Gender','trim|xss_clean|required');
			$this->form_validation->set_rules('acate','Category','trim|xss_clean|required');
			$this->form_validation->set_rules('adisability','Disability','trim|xss_clean|required');
			
			$this->form_validation->set_rules('amaritalstatus','Maritial Status','trim|xss_clean|required');
			$this->form_validation->set_rules('anationality','Nationality','trim|xss_clean|required');
			$this->form_validation->set_rules('areligion','Religion','trim|xss_clean|required');
			//repopulate data
			if($_POST){
				$this->data['aadvt']=$this->input->get_post('aadvt');
				$this->data['apost']=$this->input->get_post('apost');
				$this->data['adept']=$this->input->get_post('adept');
				$this->data['apcode']=$this->input->get_post('apcode');
				$this->data['afieldspecia']=$this->input->get_post('afieldspecia');

				$this->data['aucate']=$this->input->get_post('aucate');
				$this->data['afname']=$this->input->get_post('afname');
				$this->data['amname']=$this->input->get_post('amname');
				$this->data['alname']=$this->input->get_post('alname');
				$this->data['afathername']=$this->input->get_post('afathername');

				$this->data['amothername']=$this->input->get_post('amothername');
				$this->data['aadharno']=$this->input->get_post('aadharno');
				$this->data['acadd']=$this->input->get_post('acadd');
				$this->data['apadd']=$this->input->get_post('apadd');
				$this->data['adob']=$this->input->get_post('adob');

				$this->data['adobplace']=$this->input->get_post('adobplace');
				$this->data['aagecutoff']=$this->input->get_post('aagecutoff');
				$this->data['agender']=$this->input->get_post('agender');
				$this->data['acate']=$this->input->get_post('acate');
				$this->data['adisability']=$this->input->get_post('adisability');

				$this->data['amaritalstatus']=$this->input->get_post('amaritalstatus');
				$this->data['anationality']=$this->input->get_post('anationality');
				$this->data['areligion']=$this->input->get_post('areligion');
			}
			
			if($this->form_validation->run() == TRUE){

				//check for existance and switch to step
				//if
				//else
				$applicant = array(
					'apd_advno' 		=> $_POST['aadvt'],
					'apd_postapplifor' 	=> $_POST['apost'],
					'apd_depart' 		=> $_POST['adept'],
					'apd_postcode' 		=> $_POST['apcode'],
					'apd_fieldofspecial' 	=> $_POST['afieldspecia'],

					'apd_categappli' 	=> $_POST['aucate'],
					'apd_fname' 		=> $_POST['afname'],
					'apd_midname' 		=> $_POST['amname'],
					'apd_lastname' 		=> $_POST['alname'],
					'apd_father_husbandname'=> $_POST['afathername'],

					'apd_mothername' 	=> $_POST['amothername'],
					'apd_aadharno' 		=> $_POST['aadharno'],
					'apd_correspondenceadd' => $_POST['acadd'],
					'apd_permanantadd' 	=> $_POST['apadd'],
					'apd_dob' 		=> $_POST['adob'],

					'apd_dop' 		=> $_POST['adobplace'],
					'apd_agedate' 		=> $_POST['aagecutoff'],
					'apd_gender' 		=> $_POST['agender'],
					'apd_category' 		=> $_POST['acate'],
					'apd_disability' 	=> $_POST['adisability'],
			
					'apd_maritialstatus' 	=> $_POST['amaritalstatus'],
					'apd_nationality' 	=> $_POST['anationality'],
					'apd_religion' 		=> $_POST['areligion']

					);
				//start the transaction
		       	 	$this->db3->trans_begin();
				$this->db3->insert('applicant_data', $applicant);	
				$insertid = $this->db3->insert_id();
				
				$step1 = array(
						'application_no'	=>	$_POST['aadvt'],
						'applicant_masterid'	=>	$insertid,
						'step1_status'   	=>	1,
                				'step1_date'  		=>	$cdate
                	
               			 );
				$this->db3->insert('applicationstep', $step1);
				
				 $data = [
                                        'amid' => $insertid,
                                        //'apd_fname' => $usernme,
                                       // 'id_role' => 3
                                        ];
                                  $this->session->set_userdata($data);


				//make transaction complete
        			$this->db3->trans_complete();
			
	 			//check if transaction status TRUE or FALSE
        			if ($this->db3->trans_status() === FALSE) {
            			//if something went wrong, rollback everything
            				$this->db3->trans_rollback();
					$message = '<h3>Your some data is incorrect.</h3>';
					$this->session->set_flashdata('msg',$message);
					$this->logger->write_logmessage("insert", "Step1 error in insert applicant detail ");
                    			$this->logger->write_dblogmessage("insert", "Step1 error in insert applicant detail ");
					$this->load->view('carrier/applicant_step1');
					//redirect('student/student_step1');
           				//return FALSE;
      		  			} else {
            					//if everything went right, commit the data to the database
           		 			$this->db3->trans_commit();
						$message = '<h3>Your information successfully registered.</h3>';
						$this->session->set_flashdata('msg',$message);
			 			$this->logger->write_logmessage("insert", "Step1 insert detail in applicant_data table.");
               		 			$this->logger->write_dblogmessage("insert", "Step1 insert detail in applicant_data table.");
						redirect('carrier/applicant_step2');
           		 			//return TRUE;
       			 		    }	
				
				}//close validation if
		}//post if close
		
		$this->load->view('carrier/applicant_step1');
	}

	public function applicant_step2(){
		$amid = $this->session->userdata['amid'];
		$appno = $this->hrmmodel->get_listspfic1("applicant_data","apd_advno","amid",$amid)->apd_advno;	
		
		//print_r($appno);
		if(isset($_POST['education']))
		{
			//1 qualification
			$this->form_validation->set_rules('Hexam','1 step Examination Degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Hsubject','1 step High school subject','trim|xss_clean|required');
			$this->form_validation->set_rules('Hyear','1 step year','trim|xss_clean|required|numeric');
			$this->form_validation->set_rules('HDivision','1 step Divison','trim|xss_clean');
			$this->form_validation->set_rules('HMarks','1 step Marks','trim|xss_clean|required');
			$this->form_validation->set_rules('HUniverity','1 step University','trim|xss_clean|required');
			$this->form_validation->set_rules('Hscholar','1 step Scholar','trim|xss_clean');

			$this->form_validation->set_rules('Inexam','1 step Examination Degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Isubject','1 step Intermediate subjects','trim|xss_clean|required');
			$this->form_validation->set_rules('IYear','1 step Year','trim|xss_clean|required|numeric');
			$this->form_validation->set_rules('IDivision','1 step Division','trim|xss_clean');
			$this->form_validation->set_rules('IMarks','1 step Marks','trim|xss_clean|required');
			$this->form_validation->set_rules('IUniverity','1 step Univeristy','trim|xss_clean|required');
			$this->form_validation->set_rules('Ischolar','1 step Scholor','trim|xss_clean');

			//2 research degree
			$this->form_validation->set_rules('Mpexam','2 step mphill degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Mpuniv','2 step mphill university','trim|xss_clean|required');
			$this->form_validation->set_rules('Mpsubmit','2 step mphill date of submission','trim|xss_clean|required');
			$this->form_validation->set_rules('Mpaward','2 step mphill date of award ','trim|xss_clean|required');
			$this->form_validation->set_rules('Mpthesis','2 step mphill Marks','trim|xss_clean|required');
			$this->form_validation->set_rules('PHDyorno','2 step phd yes or no','trim|xss_clean|required');
			$this->form_validation->set_rules('NETyorno','2 step net/slet yes or no','trim|xss_clean|required');

			$this->form_validation->set_rules('Phexam','2 step phd degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Phuniv','2 step phd university','trim|xss_clean|required');
			$this->form_validation->set_rules('Phsubmit','2 step phd date of submission','trim|xss_clean|required');
			$this->form_validation->set_rules('Phaward','2 step phd date of award','trim|xss_clean|required');
			$this->form_validation->set_rules('Phthesis','2 step phd marks','trim|xss_clean|required');
			$this->form_validation->set_rules('PHDyorno','2 step phd yes or no','trim|xss_clean|required');
			$this->form_validation->set_rules('NETyorno','2 step net/slet yes or no','trim|xss_clean|required');
			
			$this->form_validation->set_rules('Dluniv','2 step d.sc /d.litt degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Dlsubmit','2 step d.sc /d.litt university','trim|xss_clean|required');
			$this->form_validation->set_rules('Dlaward','2 step d.sc /d.litt date of submission','trim|xss_clean|required');
			$this->form_validation->set_rules('Dlexam','2 step d.sc /d.litt date of award','trim|xss_clean|required');
			$this->form_validation->set_rules('Dlthesis','2 step d.sc /d.litt marks','trim|xss_clean|required');
			$this->form_validation->set_rules('PHDyorno','2 step phd yes or no','trim|xss_clean|required');
			$this->form_validation->set_rules('NETyorno','2 step net/slet yes or no','trim|xss_clean|required');
				
			
			//3 Name/ professional/ research employee
			$this->form_validation->set_rules('Eaddress','3 step name/address/contact','trim|xss_clean|required');
			$this->form_validation->set_rules('Epost','3 step post','trim|xss_clean|required');
			$this->form_validation->set_rules('Epresentpay','3 step present pay','trim|xss_clean|required');
			$this->form_validation->set_rules('Ebasicpay','3 step basic pay','trim|xss_clean|required');
			$this->form_validation->set_rules('Egrosspay','3 step total gross pay','trim|xss_clean|required');
			$this->form_validation->set_rules('Eperiodfrom','3 step peroid of employee from','trim|xss_clean|required');
			$this->form_validation->set_rules('Eperiodto','3 step peroid of employee to','trim|xss_clean|required');
			$this->form_validation->set_rules('Enature','3 step nature of duties','trim|xss_clean|required');

			/*$this->form_validation->set_rules('Eadd','3 step name/address/contact','trim|xss_clean');
			$this->form_validation->set_rules('Epos','3 step Any other degree subjects','trim|xss_clean');
			$this->form_validation->set_rules('Epresent','3 step step present pay','trim|xss_clean');
			$this->form_validation->set_rules('Ebasic','3 step basic pay','trim|xss_clean');
			$this->form_validation->set_rules('Egross','3 step total gross pay','trim|xss_clean');
			$this->form_validation->set_rules('Eperifrom','3 step peroid of employee from','trim|xss_clean');
			$this->form_validation->set_rules('Eperito','3 step peroid of employee to','trim|xss_clean');
			$this->form_validation->set_rules('Eduties','3 step nature of duties','trim|xss_clean');*/

			//4 reserch projects
			//$this->form_validation->set_rules('Rproject','4 step research projrct','trim|xss_clean');

			//5 research guidence
			$this->form_validation->set_rules('Resproject','5 step number of thesis','trim|xss_clean|required');
			$this->form_validation->set_rules('Resexam','5 step degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Resaward','5 step phd awarded','trim|xss_clean|required');
			$this->form_validation->set_rules('Resubmit','5 step phd submitted','trim|xss_clean|required');
			$this->form_validation->set_rules('Resprogress','5 step phd in progress','trim|xss_clean|required');

			$this->form_validation->set_rules('Mexam','5 step m.phill degree','trim|xss_clean|required');
			$this->form_validation->set_rules('Maward','5 step m.phill awarded','trim|xss_clean|required');
			$this->form_validation->set_rules('Msubmit','5 step m.phill submitted','trim|xss_clean|required');
			$this->form_validation->set_rules('Mprogress','5 step m.phill in progress','trim|xss_clean|required');

			//6 prizes / medal awards honours
			//$this->form_validation->set_rules('Prizes','6 step prizes / medal/ awards/ honours','trim|xss_clean');
		
			//7. Summary of Experience / Performance	
			$this->form_validation->set_rules('Ugfrom','7 step ug experience from','trim|xss_clean|required');
			$this->form_validation->set_rules('Ugto','7 step ug experience to','trim|xss_clean|required');
			$this->form_validation->set_rules('Ugyear','7 step ug total year or month','trim|xss_clean|required');

			$this->form_validation->set_rules('Pgfrom','7 step pg experience from','trim|xss_clean|required');
			$this->form_validation->set_rules('Pgto','7 step pg experience to','trim|xss_clean|required');
			$this->form_validation->set_rules('Pgyear','7 step pg total year or month','trim|xss_clean|required');

			$this->form_validation->set_rules('Tfrom','7 step teaching experience from','trim|xss_clean|required');
			$this->form_validation->set_rules('Tto','7 step teaching experience to','trim|xss_clean|required');
			$this->form_validation->set_rules('Tyear','7 step teaching total year or month','trim|xss_clean|required');

			//8 Training courses and conference 
			$this->form_validation->set_rules('8a','8a step membership','trim|xss_clean|required');
			$this->form_validation->set_rules('8b','8b step activities','trim|xss_clean|required');
			$this->form_validation->set_rules('8c','8c step accept initial salary','trim|xss_clean|required');
			$this->form_validation->set_rules('8d','8d step perioud of join post','trim|xss_clean|required');
			$this->form_validation->set_rules('8e','8e step relevant information','trim|xss_clean|required');

			//9 
			$this->form_validation->set_rules('9a','9a step academic career','trim|xss_clean');
			$this->form_validation->set_rules('9b','9b step punished in college/university','trim|xss_clean');
			$this->form_validation->set_rules('9c','9c step punished during your service','trim|xss_clean');
			$this->form_validation->set_rules('9d','9d step pending any case','trim|xss_clean|required');
			$this->form_validation->set_rules('9e','9e step relevent information','trim|xss_clean');

			//10 Give Names designation and address 
			$this->form_validation->set_rules('10ainame','10 step name and address','trim|xss_clean');
			$this->form_validation->set_rules('10amobile','10 step mobile','trim|xss_clean|numeric');
			$this->form_validation->set_rules('10aemail','10 step email','trim|xss_clean|valid_email');

			//11 Publication if any, 
			$this->form_validation->set_rules('Publication','11 step Publication','trim|xss_clean');

			//repopulate data
			if($_POST){
				//1
				$this->data['Hexam']=$this->input->get_post('Hexam');
				$this->data['Hsubject']=$this->input->get_post('Hsubject');
				$this->data['HDivision']=$this->input->get_post('HDivision');
				$this->data['Hyear']=$this->input->get_post('Hyear');
				$this->data['HMarks']=$this->input->get_post('HMarks');
				$this->data['HUniverity']=$this->input->get_post('HUniverity');
				$this->data['Hscholar']=$this->input->get_post('Hscholar');
				
				$this->data['Iexam']=$this->input->get_post('Inexam');
				$this->data['Isubject']=$this->input->get_post('Isubject');
				$this->data['IYear']=$this->input->get_post('IYear');
				$this->data['IDivision']=$this->input->get_post('IDivision');
				$this->data['IMarks']=$this->input->get_post('IMarks');
				$this->data['IUniverity']=$this->input->get_post('IUniverity');
				$this->data['Ischolar']=$this->input->get_post('Ischolar');

				//2
				$this->data['Mpuniv']=$this->input->get_post('Mpuniv');
				$this->data['Mpsubmit']=$this->input->get_post('Mpsubmit');
				$this->data['Mpaward']=$this->input->get_post('Mpaward');
				$this->data['Mpthesis']=$this->input->get_post('Mpthesis');
								
				$this->data['Phuniv']=$this->input->get_post('Phuniv');
				$this->data['Phsubmit']=$this->input->get_post('Phsubmit');
				$this->data['Phaward']=$this->input->get_post('Phaward');
				$this->data['Phthesis']=$this->input->get_post('Phthesis');
				
				$this->data['Dluniv']=$this->input->get_post('Dluniv');
				$this->data['Dlsubmit']=$this->input->get_post('Dlsubmit');
				$this->data['Dlaward']=$this->input->get_post('Dlaward');
				$this->data['Dlthesis']=$this->input->get_post('Dlthesis');
				
				//3
				$this->data['Eaddress']=$this->input->get_post('Eaddress');
				$this->data['Epost']=$this->input->get_post('Epost');
				$this->data['Epresentpay']=$this->input->get_post('Epresentpay');
				$this->data['Ebasicpay']=$this->input->get_post('Ebasicpay');
				$this->data['Egrosspay']=$this->input->get_post('Egrosspay');
				$this->data['Eperiodfrom']=$this->input->get_post('Eperiodfrom');
				$this->data['Eperiodto']=$this->input->get_post('Eperiodto');
				$this->data['Iexam']=$this->input->get_post('Enature');

				$this->data['Eadd']=$this->input->get_post('Eadd');
				$this->data['Epos']=$this->input->get_post('Epos');
				$this->data['Epresent']=$this->input->get_post('Epresent');
				$this->data['Ebasic']=$this->input->get_post('Ebasic');
				$this->data['Egross']=$this->input->get_post('Egross');
				$this->data['Eperifrom']=$this->input->get_post('Eperifrom');
				$this->data['Eperito']=$this->input->get_post('Eperito');
				$this->data['Eduties']=$this->input->get_post('Eduties');
				
				//4
				$this->data['Resproject']=$this->input->get_post('Resproject');
		
				//5
				$this->data['Resproject']=$this->input->get_post('Resproject');

				$this->data['Resexam']=$this->input->get_post('Resexam');
				$this->data['Resaward']=$this->input->get_post('Resaward');
				$this->data['Resubmit']=$this->input->get_post('Resubmit');
				$this->data['Resprogress']=$this->input->get_post('Resprogress');

				$this->data['Mexam']=$this->input->get_post('Mexam');
				$this->data['Maward']=$this->input->get_post('Maward');
				$this->data['Msubmit']=$this->input->get_post('Msubmit');
				$this->data['Mprogress']=$this->input->get_post('Mprogress');

				//6
				$this->data['Prizes']=$this->input->get_post('Prizes');

				//7
				$this->data['Ugfrom']=$this->input->get_post('Ugfrom');
				$this->data['Ugto']=$this->input->get_post('Ugto');
				$this->data['Ugyear']=$this->input->get_post('Ugyear');

				$this->data['Pgfrom']=$this->input->get_post('Pgfrom');
				$this->data['Pgto']=$this->input->get_post('Pgto');
				$this->data['Pgyear']=$this->input->get_post('Pgyear');

				$this->data['Tfrom']=$this->input->get_post('Tfrom');
				$this->data['Tto']=$this->input->get_post('Tto');
				$this->data['Tyear']=$this->input->get_post('Tyear');

				$this->data['Tvprogyear']=$this->input->get_post('Tvprogyear');
				$this->data['Maward']=$this->input->get_post('Stprogyear');

				//8
				$this->data['8a']=$this->input->get_post('8a');
				$this->data['8b']=$this->input->get_post('8b');
				$this->data['8c']=$this->input->get_post('8c');
				$this->data['8d']=$this->input->get_post('8d');
				$this->data['8e']=$this->input->get_post('8e');

				//9
				$this->data['9a']=$this->input->get_post('9a');
				$this->data['9b']=$this->input->get_post('9b');
				$this->data['9c']=$this->input->get_post('9c');
				$this->data['9d']=$this->input->get_post('9d');
				$this->data['9e']=$this->input->get_post('9e');

				//10
				$this->data['10ainame']=$this->input->get_post('10ainame');
				$this->data['10amobile']=$this->input->get_post('10amobile');
				$this->data['10aemail']=$this->input->get_post('10aemail');
				
				//11
				$this->data['Publication']=$this->input->get_post('Publication');
			}
			

			if($this->form_validation->run() == TRUE){

				//check for existance and switch to step
				//if
				//else
				$cdate = date('Y-m-d H:i:s');
				//start the transaction
				$this->db3->trans_begin();
				
				//1. qualification
				$highschool = array(
					'aedu_amid'		  => $amid,
					'aedu_class' 		  => $_POST['Hexam'],
					'aedu_subject' 		  => $_POST['Hsubject'],
					'aedu_passingyear' 	  => $_POST['Hyear'],
					//'' 	=> $_POST['HDivision'],
					'aedu_percentage' 	   => $_POST['HMarks'],
					'aedu_board' 		   => $_POST['HUniverity'],
					//''			   => $_POST['Hscholar'],
					);
				
				$this->db3->insert('applicant_education', $highschool);
				
				$intn =$_POST['Inexam'];
				$inter = array(
					'aedu_amid'		  => $amid,
					'aedu_class' 		  => $_POST['Inexam'],
					'aedu_subject' 		  => $_POST['Isubject'],
					'aedu_passingyear' 	  => $_POST['IYear'],
					//'' 	=> $_POST['IDivision'],
					'aedu_percentage' 	   => $_POST['IMarks'],
					'aedu_board' 		   => $_POST['IUniverity'],
					//''			   => $_POST['Ischolar'],
						);
				if(!empty($intn)){
					$this->db3->insert('applicant_education', $inter);	
				}

				$gradute =$_POST['Gexam'];
				$graduation = array(
					'aedu_amid'		  => $amid,
					'aedu_class' 		  => $_POST['Gexam'],
					'aedu_subject' 		  => $_POST['Gsubject'],
					'aedu_passingyear' 	  => $_POST['GYear'],
					//'' 	=> $_POST['GDivision'],
					'aedu_percentage' 	   => $_POST['GMarks'],
					'aedu_board' 		   => $_POST['GUniverity'],
					//''			   => $_POST['Gscholar'],
					);
				if(!empty($gradute)){
					$this->db3->insert('applicant_education',$graduation);
				}

				$hon =$_POST['Hoexam'];
				$honours = array(
					'aedu_amid'		  => $amid,
					'aedu_class' 		  => $_POST['Hoexam'],
					'aedu_subject' 		  => $_POST['Hosubject'],
					'aedu_passingyear' 	  => $_POST['HoYear'],
					//'' 	=> $_POST['HDivision'],
					'aedu_percentage' 	   => $_POST['HoMarks'],
					'aedu_board' 		   => $_POST['HoUniverity'],
					//''			   => $_POST['Hoscholar'],
					);
				if(!empty($hon)){
					$this->db3->insert('applicant_education',$honours);
				}

				$pg =$_POST['Pexam'];
				$postg = array(
					'aedu_amid'		  => $amid,
					'aedu_class' 		  => $_POST['Pexam'],
					'aedu_subject' 		  => $_POST['Psubject'],
					'aedu_passingyear' 	  => $_POST['PYear'],
					//'' 	=> $_POST['HDivision'],
					'aedu_percentage' 	   => $_POST['PMarks'],
					'aedu_board' 		   => $_POST['PUniverity']
					//''			   => $_POST['Pscholar'],
					);
				if(!empty($pg)){
					$this->db3->insert('applicant_education', $postg);
				}

				$any =$_POST['Aexam'];
				$other = array(
					'aedu_amid'		  => $amid,
					'aedu_class' 		  => $_POST['Aexam'],
					'aedu_subject' 		  => $_POST['Asubject'],
					'aedu_passingyear' 	  => $_POST['AYear'],
					//'' 	=> $_POST['ADivision'],
					'aedu_percentage' 	   => $_POST['AMarks'],
					'aedu_board' 		   => $_POST['AUniverity'],
					//''			   => $_POST['Ascholar']	
					
					);
				if(!empty($any)){
					$this->db3->insert('applicant_education', $other);
				}

				//2. research degree
				$mphill = array(
					'aresdeg_amid'		  		=> $amid,
					'aresdeg_degreename' 		  	=> $_POST['Mpexam'],
					'aresdeg_universityname' 		=> $_POST['Mpuniv'],
					'aresdeg_dateofsubmission' 	 	=> $_POST['Mpsubmit'],
					'aresdeg_dateofaward' 		  	=> $_POST['Mpaward'],
					'aresdeg_titleofthesis' 		=> $_POST['Mpthesis'],
					'aresdeg_asper_ugc_reg_2009' 		=> $_POST['PHDyorno'],
					'aresdeg_asper_ugc_reg_2009' 		=> $_POST['NETyorno']
					
					);
				
				$this->db3->insert('applicant_research_degree', $mphill);

				$phd = array(
					'aresdeg_amid'		  		=> $amid,
					'aresdeg_degreename' 		  	=> $_POST['Phexam'],
					'aresdeg_universityname' 		=> $_POST['Phuniv'],
					'aresdeg_dateofsubmission' 	  	=> $_POST['Phsubmit'],
					'aresdeg_dateofaward' 		  	=> $_POST['Phaward'],
					'aresdeg_titleofthesis' 		=> $_POST['Phthesis'],
					'aresdeg_asper_ugc_reg_2009' 		=> $_POST['PHDyorno'],
					'aresdeg_asper_ugc_reg_2009' 		=> $_POST['NETyorno']
					
					);
				
				$this->db3->insert('applicant_research_degree', $phd);

				$phd = array(
					'aresdeg_amid'		  		=> $amid,
					'aresdeg_degreename' 		  	=> $_POST['Dlexam'],
					'aresdeg_universityname' 		=> $_POST['Dluniv'],
					'aresdeg_dateofsubmission' 	  	=> $_POST['Dlsubmit'],
					'aresdeg_dateofaward' 		  	=> $_POST['Dlaward'],
					'aresdeg_titleofthesis' 		=> $_POST['Dlthesis'],
					'aresdeg_asper_ugc_reg_2009' 		=> $_POST['PHDyorno'],
					'aresdeg_asper_ugc_reg_2009' 		=> $_POST['NETyorno']
					
					);
				
				$this->db3->insert('applicant_research_degree', $phd);
				
				//3. Name / Professional / Research Employment
				$name1 = array(
					'aemp_amid'		  => $amid,
					'aemp_officename' 	  => $_POST['Eaddress'],
					'aemp_post' 		  => $_POST['Epost'],
					'aemp_presentpay' 	  => $_POST['Epresentpay'],
					'aemp_basicpay' 	  => $_POST['Ebasicpay'],
					'aemp_grosspay' 	  => $_POST['Egrosspay'],
					'aemp_dojfrom' 		  => $_POST['Eperiodfrom'],
					'aemp_dojto' 		  => $_POST['Eperiodto'],
					'aemp_appoinmentnature'   => $_POST['Enature']
					);
				
				$this->db3->insert('applicant_employment',$name1);	
				
				$name2 = array(
					'aemp_amid'		  => $amid,
					'aemp_officename' 	  => $_POST['Eadd'],
					'aemp_post' 		  => $_POST['Epos'],
					'aemp_presentpay' 	  => $_POST['Epresent'],
					'aemp_basicpay' 	  => $_POST['Ebasic'],
					'aemp_grosspay' 	  => $_POST['Egross'],
					'aemp_dojfrom' 		  => $_POST['Eperifrom'],
					'aemp_dojto' 		  => $_POST['Eperito'],
					'aemp_appoinmentnature'   => $_POST['Eduties']
					);
				
				$this->db3->insert('applicant_employment', $name2);

				//4/5/6. Research
				$phdaward = array(
					'ares_amid'		  => $amid,
					'ares_researchproj' 	  => $_POST['Rproject'],
					'ares_numberofthesis' 	  => $_POST['Resproject'],
					'ares_researchproj' 	  => $_POST['Resexam'],
					'ares_phd_thesisaward' 	  => $_POST['Resaward'],
					'ares_phd_submitted' 	  => $_POST['Resubmit'],
					'ares_phd_inprogress' 	  => $_POST['Resprogress'],
					'ares_pmah' 	  	  => $_POST['Prizes']
					
					);
				
				$this->db3->insert('applicant_research', $phdaward);	
			
				$mphill = array(
					'ares_amid'		  	=> $amid,
					'ares_numberofthesis' 	  	=> $_POST['Resproject'],
					'ares_researchproj' 		=> $_POST['Mexam'],
					'ares_mphill_thesisaward' 	  => $_POST['Maward'],
					'ares_mphill_submitted' 	  => $_POST['Msubmit'],
					'ares_mphill_inprogress' 	  => $_POST['Mprogress']
					
					);
				
				$this->db3->insert('applicant_research', $mphill);		

				//7. Summary of Experience / Performance
				$ug = array(
					'aperfor_amid'		  	=> $amid,
					'aperfor_teachexp' 	  	=> $_POST['Ugradute'],
					'aperfor_from' 			=> $_POST['Ugfrom'],
					'aperfor_to' 	  		=> $_POST['Ugto'],
					'aperfor_totalyearmonth' 	=> $_POST['Ugyear'],
					'aperfor_participatetotalyear' 	=> $_POST['Tvprogyear'],
					'aperfor_shorttermtotalyear' 	=> $_POST['Stprogyear']
					);
				
				$this->db3->insert('applicant_performance', $ug);
			
				$pg = array(
					'aperfor_amid'		  	=> $amid,
					'aperfor_teachexp' 	  	=> $_POST['Pgraduate'],
					'aperfor_from' 			=> $_POST['Pgfrom'],
					'aperfor_to' 	  		=> $_POST['Pgto'],
					'aperfor_totalyearmonth' 	=> $_POST['Pgyear'],
					'aperfor_participatetotalyear' 	=> $_POST['Tvprogyear'],
					'aperfor_shorttermtotalyear' 	=> $_POST['Stprogyear']
					);
				
				$this->db3->insert('applicant_performance', $pg);	

				$teach = array(
					'aperfor_amid'		  	=> $amid,
					'aperfor_teachexp' 	  	=> $_POST['Tteach'],
					'aperfor_from' 			=> $_POST['Tfrom'],
					'aperfor_to' 	 		=> $_POST['Tto'],
					'aperfor_totalyearmonth' 	=> $_POST['Tyear'],
					'aperfor_participatetotalyear' 	=> $_POST['Tvprogyear'],
					'aperfor_shorttermtotalyear' 	=> $_POST['Stprogyear']
					);
				
				$this->db3->insert('applicant_performance', $teach);	
	
				//8.Training courses and conference
				$training = array(
					'asupd_amid'		  	=> $amid,
					'asupd_8a' 	  		=> $_POST['8a'],
					'asupd_8b' 			=> $_POST['8b'],
					'asupd_8c' 	 		=> $_POST['8c'],
					'asupd_8d' 			=> $_POST['8d'],
					'asupd_8e' 			=> $_POST['8e'],

					'asupd_9a' 	  		=> $_POST['9a'],
					'asupd_9b' 			=> $_POST['9b'],
					'asupd_9c' 	 		=> $_POST['9c'],
					'asupd_9d' 			=> $_POST['9d'],
					'asupd_9e' 			=> $_POST['9e'],

					'asupd_10name' 	  		=> $_POST['10ainame'],
					'asupd_10mobile' 		=> $_POST['10amobile'],
					'asupd_10address' 		=> $_POST['10aemail'],
					
					'asupd_11' 			=> $_POST['Publication']
				
					);
				
				$this->db3->insert('applicant_supportdata', $training);
							

				//applicant step
				//$step2 = array(
						//'application_no'	=>	$appno,
						//'applicant_masterid'	=>	$amid,
						//'step2_status'   	=>	1,
                				//'step2_date'  	=>	$cdate
                	
               			 //);
				//$this->db3->insert('applicationstep', $step2);
				
				//make transaction complete
        			$this->db3->trans_complete();
			
	 			//check if transaction status TRUE or FALSE
        			if ($this->db3->trans_status() === FALSE) {
            			//if something went wrong, rollback everything
            				$this->db3->trans_rollback();
					$message = '<h3>Some data is incorrect.</h3>';
					$this->session->set_flashdata('msg',$message);
					$this->logger->write_logmessage("insert", "Step2 error in insert applicant education detail ");
                    			$this->logger->write_dblogmessage("insert", "Step2 error in insert applicant education detail");
					$this->load->view('student/student_step2');
					//redirect('student/student_step1');
           				//return FALSE;
      		  			} else {
            					//if everything went right, commit the data to the database
           		 			$this->db3->trans_commit();
						$message = '<h3>Your eduction detail successfully added.</h3>';
						$this->session->set_flashdata('msg',$message);
			 			$this->logger->write_logmessage("insert", "Step2 insert detail in applicant_education table.");
               		 			$this->logger->write_dblogmessage("insert", "Step2 insert detail in applicant_education table.");
						redirect('carrier/applicant_step2');
           		 			//return TRUE;
       			 		    }	

				}//close validation if

		}//close if post
		
	
		$this->load->view('carrier/applicant_step2');
	}

	public function applicant_step3(){
		$this->load->view('carrier/applicant_step3');
	}

	public function applicant_step4(){
		$this->load->view('carrier/applicant_step4');
	}

/****************************get applied post list dependent dropdown*****************************************************/
	public function getposlist(){
	    $postlist = $this->input->post('aadvt');
	    $list = $this->hrmmodel->get_listspfic2('job_open','job_id','job_nameofpost','job_adverno',$postlist,'');
	echo "<select>";	
			echo "<option selected='true' disabled>"."Select post list"."</option>";
		foreach($list as $datas):   
			 echo "<option id='apost' value='$datas->job_id;'>"."$datas->job_nameofpost"."</option>";
   		endforeach;
	echo "</select>";
			
	}

	public function getplastdate(){
	    $postdate = $this->input->post('aadvt');
	    $list1 = $this->hrmmodel->get_listspfic2('job_open','','job_lastdateonlineform','job_adverno',$postdate,'job_lastdateonlineform');
		foreach($list1 as $datas1):   
			$job_id =  $this->hrmmodel->get_listspfic1('job_open','job_id','job_adverno',$postdate)->job_id;	?>
      			<option  id='actoffdate' value="<?php echo $job_id;?>"><?php echo $datas1->job_lastdateonlineform; ?></option>
<?php   	endforeach;
	}
/****************************get department list dependent dropdown*****************************************************/
	public function getdepartment(){
	    $deptname = $this->input->post('apost');
            $postname =	$this->hrmmodel->get_listspfic1('job_open','job_nameofpost','job_id',$deptname)->job_nameofpost;	
            $postadno =	$this->hrmmodel->get_listspfic1('job_open','job_adverno','job_id',$deptname)->job_adverno;	
	    $selectfield='job_id,job_department';
	    $datawh=array('job_nameofpost'=>$postname, 'job_adverno'=>$postadno);
	    $dept=$this->hrmmodel->get_listspficemore('job_open',$selectfield,$datawh);		
	   // $dept = $this->hrmmodel->get_listspfic2('job_open','job_id','job_department','job_nameofpost',$postname,'job_department');
		foreach($dept as $deptn): ?>
      			<option  id='adept' value="<?php echo $deptn->job_id;?>"><?php echo $deptn->job_department; ?></option>
<?php   	endforeach;
	}

	public function getpost_code(){
	    $deptname = $this->input->post('apost');
            $postname =	$this->hrmmodel->get_listspfic1('job_open','job_nameofpost','job_id',$deptname)->job_nameofpost;	
	    $postadno = $this->hrmmodel->get_listspfic1('job_open','job_adverno','job_id',$deptname)->job_adverno;
            $selectfield='job_id,job_postcode';
            $datawh=array('job_nameofpost'=>$postname, 'job_adverno'=>$postadno);
            $pcode=$this->hrmmodel->get_listspficemore('job_open',$selectfield,$datawh);
	  //  $pcode = $this->hrmmodel->get_listspfic2('job_open','job_id','job_postcode','job_nameofpost',$postcode,'job_postcode');
		foreach($pcode as $code): ?>   
      			<option  id='apcode' value="<?php echo $code->job_id;?>"><?php echo $code->job_postcode; ?></option>
<?php   	endforeach;
	}
	
    }?>
