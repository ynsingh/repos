<?php

 /* 
 * @name Report.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)
 * @author Sumit Saxena(sumitsesaxena@gmail.com)[View Admission merit list]	
 */

class Report  extends CI_Controller
{

   function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "logmodel");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usrmodel");
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

// view students list 

    public function liststu() {
        $datawh = array('roleid' => '3');
        $this->tresult=$this->commodel->get_listspficarry('user_role_type','userid,scid,deptid','roleid',3);
        $this->load->view('report/liststu');
   } 

// view admission merit list 

    public function admission_meritlist() {
      //  $datawh = array('roleid' => '3');
        $this->admission=$this->commodel->get_list('admissionmeritlist');
        $this->load->view('report/admission_meritlist');
   } 

    // view admission application student
    public function list_application() {
	$this->examcenter = $this->commodel->get_listmore('admissionstudent_enterenceexamcenter','eec_name,eec_city,eec_id');
	$this->prgname  = $this->commodel->get_listmore('program','prg_name,prg_id,prg_branch');
	$this->prgcatname  = $this->commodel->get_listmore('programcategory','prgcat_name,prgcat_id');

		//get all record search
		$progid = $this->input->post('appstubranch',TRUE);
		$exmceter = $this->input->post('appstuexamcenter',TRUE);
		$name = $this->input->post('appstuname',TRUE);
		$mobile = $this->input->post('appstumobile',TRUE);
		$email = $this->input->post('appstuemail',TRUE);
		$gender = $this->input->post('appstugender',TRUE);
		$religion = $this->input->post('appstureligion',TRUE);
		$appno = $this->input->post('appstuapplino',TRUE);
		$regdate = $this->input->post('appsturegistration',TRUE);
		$payment = $this->input->post('appstupaytype',TRUE);
		$prgcat = $this->input->post('progcat',TRUE);
       		if(isset($_POST['search'])) 
      		 {
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
   				'asm_enterenceexamcenter'  => $exmceter,
				'asm_coursename'  	=> $progid,
				'asm_fname'		=> $name,
				'asm_email'		=> $email,
				'asm_mobile'		=> $mobile,
				'asm_gender'		=> $gender,
				'asm_religion'		=> $religion,
				'asm_applicationno'	=> $appno
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);

			$regselect = array('admission_masterid','step4_date');
			$regdata = array('step4_date' =>$regdate);
			$this->regdate = $this->commodel->get_listspficemore('admissionstudent_enterencestep',$regselect,$regdata);
			
			$regselect = array('asfee_amid','asfee_referenceno');
			$paydata = array('asfee_paymentmethod' => $payment);
			$this->pay = $this->commodel->get_listspficemore('admissionstudent_fees',$regselect,$paydata);
		
			//when select prgoram category then data get
			$selectdata=array('prg_id');
			$record=array(
   					'prg_category'  => $prgcat,
				);
       			$this->getprgid = $this->commodel->get_distinctrecord('program',$selectdata,$record);
			
		 }//if isset search close
		//prgoram and branch search
		if($progid == TRUE){
			$progid = $this->input->post('appstubranch',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_coursename'  	=> $progid,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//exam center search
		elseif($exmceter == TRUE){
			$exmceter = $this->input->post('appstuexamcenter',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_enterenceexamcenter'  => $exmceter,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//name search	
		elseif($name == TRUE){
			$name = $this->input->post('appstuname',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_fname'  => $name,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//mobile number search
		elseif($mobile == TRUE){
			$mobile = $this->input->post('appstumobile',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_mobile'  => $mobile,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//email search
		elseif($email == TRUE){
			$email = $this->input->post('appstuemail',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_email'  => $email,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//gender search
		elseif($gender == TRUE){
			$gender = $this->input->post('appstugender',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_gender'  => $gender,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//search through religion
		elseif($religion == TRUE){
			$religion = $this->input->post('appstureligion',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_religion'  => $religion,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		//search through application no(Roll no.)
		elseif($appno == TRUE){
			$appno = $this->input->post('appstuapplino',TRUE);
			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');
			$record=array(
				'asm_applicationno'  => $appno,
      				);
       			$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
		}
		
		//search through registration date
		
		elseif($regdate == TRUE){
				$regdate = $this->input->post('appsturegistration',TRUE);
				$regselect = array('admission_masterid','step4_date');
				$regdata = array('step4_date' =>$regdate);
				$this->regdate = $this->commodel->get_listspficemore('admissionstudent_enterencestep',$regselect,$regdata);
			}

		elseif($payment == TRUE){
			$payment = $this->input->post('appstupaytype',TRUE);
				$regselect = array('asfee_amid','asfee_referenceno');
				$paydata = array('asfee_paymentmethod' => $payment);
				$this->pay = $this->commodel->get_listspficemore('admissionstudent_fees',$regselect,$paydata);
			
		}	
		//search through program category
		elseif($prgcat == TRUE){
			$prgcat = $this->input->post('progcat',TRUE);
			$selectdata=array('prg_id');
				$record=array(
   					'prg_category'  => $prgcat,
				);
       			$this->getprgid = $this->commodel->get_listspficemore('program',$selectdata,$record);	
		}
		
		

        $this->load->view('report/listapplicationstu');
   } 

}


