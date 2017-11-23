<?php

 /* 
 * @name Report.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)
 * @author Manorama Pal (palseema30@gmail.com)
 * @author Sumit Saxena(sumitsesaxena@gmail.com)[view employee profile]
 */

class Report  extends CI_Controller
{

   function __construct() {
        parent::__construct();
         $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
	$this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }

// View faculty list
    public function listfac() {
        $datawh = array('roleid' => '2');
        $this->tresult=$this->commodel->get_listspficarry('user_role_type','userid,scid,deptid','roleid',2);
        $this->load->view('report/listfac');
        return;
	}

// View staff list
    public function liststaff() {
        $datawh = array('roleid' => '4');
        $this->tresult=$this->commodel->get_listspficarry('user_role_type','userid,scid,deptid','roleid',4);
        $this->load->view('report/liststaff');
        return;
	}
        /***************************************View Employee List******************************************************/
    public function viewprofile($id=0) {
        $worktype=$this->input->post('workingtype',TRUE);
        $empdata['filter']=$id;
        if(!empty($worktype) && ($id!== 0)){
            $filter=$this->input->post('filter',TRUE);
            $empdata['wtype']=$worktype; 
            $datawh=array('emp_worktype' => $worktype);
            $empdata['emprecord'] = $this->sismodel->searchemp_profile('employee_master',$worktype,$filter);
        }
        else{
            $empdata1=array();
            $empdata['emprecord']=$empdata1;
        }
        $this->load->view('report/viewprofile',$empdata);
        return;
	}
    
   public function viewfull_profile() {
	  
	  //get id for employee to show data	
	   $emp_id = $this->uri->segment(3);
	  //get all profile data
	  $emp_data = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
	  if(!empty( $emp_data)) {
		$emp_no 		=  $emp_data->emp_code;
		$doappintment 		= $emp_data->emp_doj;
		$emp_name 		= $emp_data->emp_name;
		$department 		= $emp_data->emp_dept_code;
		$designation 		= $emp_data->emp_desig_code;	
		//$doregular 		= $emp_data->;
		$community 		= $emp_data->emp_community;
		//$doprobe 		= $emp_data->;
		$caste 			= $emp_data->emp_caste;
		$religion 		= $emp_data->emp_religion;
		$native 		= $emp_data->emp_citizen;
		$specialization 	= $emp_data->emp_specialisationid;
		$present_post 		= $emp_data->emp_post;
		$qualification 		= $emp_data->emp_phd_status;
		$dob 			= $emp_data->emp_dob;
		$doretirement 		= $emp_data->emp_dor;
		$asrr 			= $emp_data->emp_AssrExam_status;
		$placeofwork 		= $emp_data->emp_scid;	
		$photo 			= $emp_data->emp_photoname;
		$email 			= $emp_data->emp_email;
		$phone			= $emp_data->emp_phone;

		$data = array('emp_no' 	=> $emp_no,
			'emp_name' 	=> $emp_name,
			'dop' 		=> $doappintment,
			'dep' 		=> $department,
			'desig' 	=> $designation,
			//'dor' 	=> $doregular,
			'caste' 	=> $caste,
			'religion' 	=> $religion,
			'commu' 	=> $community, 
			//'probe' 	=> $doprobe,
			'native' 	=> $native,
			'specialize' 	=> $specialization,
			'post' 		=> $present_post,
			'quali' 	=> $qualification,
			'dob' 		=> $dob,
			'dor' 		=> $doretirement,
			'asrr' 		=> $asrr,
			'scid' 		=> $placeofwork,
			'photo'	    	=> $photo,
			'email'		=> $email,
			'phone'		=> $phone 
				
			);
	  }		

      	  $this->load->view('report/viewfull_profile',$data);
  }

// view students list 
/*
    public function liststu() {
        $datawh = array('roleid' => '3');
        $this->tresult=$this->commodel->get_listspficarry('user_role_type','userid,scid,deptid','roleid',3);
        $this->load->view('report/liststu');
   } 
*/ 
}

