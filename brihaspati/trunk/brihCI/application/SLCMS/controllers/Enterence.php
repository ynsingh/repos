<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Enterence.php
 * @author Sumit Saxena(sumitsesaxena@gmail.com)
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
		
        	//if(!empty($_SESSION['id_user'])){
                    
                //}
            	//	redirect('home');
    	}


	public function important_date(){
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

	
    }//close class
