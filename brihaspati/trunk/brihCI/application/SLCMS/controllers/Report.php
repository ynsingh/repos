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
	$this->load->model("Student_model","stumodel");
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
		//search through program category
		if(!empty($prgcat)){
			$selectdata = array('prg_id');
			$record=array('prg_category'  => $prgcat);
       			$this->getprgid = $this->commodel->get_listspficemore('program',$selectdata,$record);
		}

		//search through registration date
		/*if(!empty($regdate)){
				$regselect = array('admission_masterid','step4_date');
				$regdata = array('step4_date' => $regdate);
				$this->regdate = $this->commodel->get_listspficemore('admissionstudent_enterencestep',$regselect,$regdata);
			}
		//search through payment method
		if(!empty($payment)){
				$regselect = array('asfee_amid','asfee_referenceno');
				$paydata = array('asfee_paymentmethod' => $payment);
				$this->pay = $this->commodel->get_listspficemore('admissionstudent_fees',$regselect,$paydata);
		}*/	
			$record=array( );	     				
			if(!empty($exmceter)){
				$record ['asm_enterenceexamcenter'] =   $exmceter;}
			
			if(!empty($progid)){
				$record ['asm_coursename' ] = $progid;}
			
			if(!empty($name)){
				$record ['asm_fname like '] =	'%'.$name.'%';}
			
			if(!empty($email)){	
				$record ['asm_email like '] =	'%'.$email.'%';}
			
			if(!empty($mobile)){
				$record ['asm_mobile like '] = '%'.$mobile.'%';}
			
			if(!empty($gender)){	
				$record ['asm_gender'] = $gender;}
			
			if(!empty($religion)){
				$record ['asm_religion'] = $religion;}
			
			if(!empty($appno)){
				$record ['asm_applicationno like '] = '%'.$appno.'%';}
			


			$selectdata=array('asm_id','asm_userid','asm_fname','asm_email','asm_mobile','asm_coursename');	
			if(!empty($record)){		
       				$this->getstudata = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
			}
		 }//if isset search close
		
		
		

        $this->load->view('report/listapplicationstu');
   } 

/************************************************** student filter record download *****************************************************/

	public function filter_datadw(){
			
		$id = $this->uri->segment(3);	
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
			$exid = $stud_admission->asm_enterenceexamcenter;
			$exname =  $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$exid)->eec_name;	
			$data['exname'] = $exname;
			$excode =  $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_code','eec_id',$exid)->eec_code;
			$data['excode'] = $excode; 

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
       		$this->pdf->load_view('report/filter_recordpdfdw',$data);
        	$this->pdf->render();
        	$this->pdf->stream("Student Record.pdf");
	}

	/*public function admission_studentlist(){
		$data['admissionstu_data'] = $this->commodel->get_list('student_master');
				
		$this->load->view('report/admission_studentlist',$data);
	}*/


	public function admission_student(){
		$date1 = $this->input->post('stadate');
		$date2 = $this->input->post('enddate');
		$admrecrd='';
		$data = array(
			'date1' => $date1,
			'date2' => $date2
			);
		if ($date1 == "" || $date2 == "") {
			$data['err_message'] = "Both date fields are required";
		} else {
			$result = $this->stumodel->show_data_by_date_range($data);
			//print_r($result);die;
			$data['student_data']=$result;
		}
			$this->load->view('report/admission_studentlist', $data);
	}

	

}


