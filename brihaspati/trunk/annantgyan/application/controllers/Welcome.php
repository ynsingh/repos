<?php
/******************************************************
* @name Welcome.php(controller)    		      	  *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Welcome extends CI_Controller {
	public function __construct(){
		parent::__construct();
		
		$this->load->model('Common_model',"commodel");
		
		$this->load->model("Mailsend_model","mailmodel");
		
	}

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
	public function index()
	{
		$this->load->helper('url'); 
		$this->logger->write_sitevisit("view", "Visiting Sites. " );
		$this->load->view('welcome_message');
	}


	public function referalenroll(){

		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('name','Name','trim|required|xss_clean');
       		$this->form_validation->set_rules('email','Email','trim|required|xss_clean');
            $this->form_validation->set_rules('contact','Contact','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('place','Place','trim|xss_clean');
            $this->form_validation->set_rules('org_name','Organisation Name','trim|xss_clean');
            $this->form_validation->set_rules('bname','Account No.','trim|xss_clean|numeric');
            $this->form_validation->set_rules('ifsc_code','IFSC Code','trim|xss_clean');
			
            if($this->form_validation->run() == FALSE){
            	//redirect('welcome/referalenroll');
            	$this->load->view('referalenroll');
            	return;
            }else{

				$name 		= $this->input->post('name');
				$email 	    = $this->input->post('email');
				$contact 	= $this->input->post('contact');
				$place      = $this->input->post('place');
				$orgname 	= $this->input->post('org_name');
				$bankac     = $this->input->post('bname');
				$ifsc 	    = $this->input->post('ifsc_code');
				
				$insdata = array(
						    'ref_name'			=> $name,
            				'ref_email' 		=> $email,  
							'ref_mobile' 		=> $contact,         
            				'ref_orgname' 		=> $orgname,
            				'ref_bank_acno' 	=> $bankac,   
            				'ref_bank_ifsc' 	=> $ifsc,
            				'ref_place' 		=> $place,          				
        			);
				//print_r($insdata);die();
        		$insflag = $this->db->insert('referrel',$insdata);
        		
        		$subject = "Registered Successfully with Annant Gyan";
			
							//$pawd = random_string('alnum',6);
						//	$erstring= $mailid.'---'.$rstring;
						//	$verifylink = base_url("login/verify/".$erstring);
        		//<tr><td><b>Password : </b> </td><td align=left>".$pawd. "</td><tr>
					//<tr><td><b>Course Name : </b> </td><td align=left>".$courname. "</td><tr>

					//<tr><td><b>Verification link : </b></td><td align=left> <a href=".$verifylink." style='color:white;'>Verify your registration.</a></td></tr>
				
					$message  = "<table width='50%'; style='border:1px solid #3A5896;color:black;font-size:18px;' align=center border=0>
					<tr><td></td></tr>
					<tr><td colspan=2><b>Your are registered successfully, Your details are given below</td></tr>
					<tr height=15><td colspan=2></td></tr>
					<tr><td width=370><b>Name: </b></td><td align=left>".$name."</td></tr> 
					<tr><td width=370><b>Email: </b></td><td align=left>".$email."</td></tr> 
					<tr><td width=370><b>Contact No.: </b></td><td align=left>".$contact."</td></tr> 
					<tr><td width=370><b>Organisation Name: </b></td><td align=left>".$orgname."</td></tr> 
					<tr><td width=370><b>Account Number: </b></td><td align=left>".$bankac."</td></tr> 
					<tr><td width=370><b>IFSC Code: </b></td><td align=left>".$ifsc."</td></tr> 
					</table> " ;

                             	$mails=$this->mailmodel->mailsnd($email,$subject,$message,'');
        		if($insflag){
        			redirect("welcome");
        		}
        		
			}//else close
		}			
		$this->load->view('referalenroll');
	}
}
