<?php

 /* 
 * @name Report.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)
 * @author Manorama Pal (palseema30@gmail.com)
 * @author Sumit Saxena(sumitsesaxena@gmail.com)[view employee profile]
 * @author Om Prakas (omprakashkgp@gmail.com) Discipline Wise List 
 */

class Report  extends CI_Controller
{

   function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
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

    public function deptemployeelist(){
        $selectfield ="emp_uocid, emp_dept_code,emp_name, emp_post";
        $whorder = "emp_uocid asc, emp_dept_code  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,'',$whorder);
        $this->logger->write_logmessage("view"," view departmentt employee list" );
        $this->logger->write_dblogmessage("view"," view department employee list");
        $this->load->view('report/deptemployeelist',$data);
    }

    public function staffstrengthlist(){
        $selectfield ="sp_uo, sp_dept,sp_grppost, sp_sancstrenght , sp_position , sp_vacant";
        $whorder = "sp_uo asc, sp_dept  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,'',$whorder);
        $this->logger->write_logmessage("view"," view staff strength list" );
        $this->logger->write_dblogmessage("view"," view staff strength list");
        $this->load->view('report/staffstrengthlist',$data);
    }

    public function staffvacposition(){
        $selectfield ="sp_uo, sp_dept,sp_grppost,sp_schemecode, sp_emppost,sp_sancstrenght , sp_position , sp_vacant, sp_remarks";
        $whorder = "sp_grppost asc, sp_uo  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,'',$whorder);
        $this->logger->write_logmessage("view"," view staff vacancy position list" );
        $this->logger->write_dblogmessage("view"," view staff vacancy position list");
        $this->load->view('report/staffvacposition',$data);
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

############################## Discipline Wise List ##########################################

public function disciplinewiselist(){
        $selectfield ="emp_dept_code, emp_name, emp_desig_code,emp_specialisationid";
	$whdata = array ('emp_specialisationid >' => 0);
        $whorder = "emp_specialisationid";
        $this->result = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
//      $this->result = $this->sismodel->get_list('employee_master');
        $this->logger->write_logmessage("view"," view  Discipline Wise Report " );
        $this->logger->write_dblogmessage("view"," view  Discipline Wise Report ");
        $this->load->view('report/disciplinewiselist');
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

