<?php
/* 
 * @name Hodadmission.php
 * @author Sumit Saxena (sumitsesaxena@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');

class Hodadmissionstu extends CI_Controller
    {
    function __construct() {
        parent::__construct();
		$this->load->model("user_model","usermodel");
                $this->load->model('Common_model',"commodel");
		$this->load->model('dependrop_model','depmodel');
		$this->load->model("Mailsend_model","mailmodel");	
		$this->load->model("Login_model", "logmodel");
		$this->load->model("DateSem_model","datesemmodel");
		$this->load->model("University_model","univmodel");
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
          redirect('welcome');
         }
		
    }

	public function listenrolledstu(){
                $roleid = $this->session->userdata('id_role');
                $usrid = $this->session->userdata('id_user');
                $whdata = array ('userid' => $usrid,'roleid' => $roleid );
                $resdept = $this->commodel->get_listspficemore ('user_role_type','deptid',$whdata);
                foreach($resdept as $datas):
                                $deptid = $datas->deptid;
                endforeach;

                $currentacadyear =$this->datesemmodel->getcurrentAcadYear();
                $semester = 1;

                $selectdata = 'sp_smid,sp_programid,sp_semregdate';
                $whdata = array('sp_deptid' => $deptid,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);

                $getstuid = $this->commodel->get_listspficemore('student_program',$selectdata,$whdata);
                $data['getstuid'] = $getstuid;
                $this->load->view('hod_admissionstu/stu_enrollment',$data);
        }
	
	public function genenrollmentnumber(){
		$roleid = $this->session->userdata('id_role');
                $usrid = $this->session->userdata('id_user');
		$whdata = array ('userid' => $usrid,'roleid' => $roleid );
                $resdept = $this->commodel->get_listspficemore ('user_role_type','deptid',$whdata);
		
                foreach($resdept as $datas):
                                $deptid = $datas->deptid;
                endforeach;
		
                $currentacadyear =$this->datesemmodel->getcurrentAcadYear();
                $semester = 1;

		//get the list of student  from student program  where enrollment number is null in student master
			$wharray = array('sm_enrollmentno' => NULL,'sp_deptid' => $deptid);
			$this->db->select('sp_smid,sp_programid');
			$this->db->from('student_program');
			$this->db->join('student_master', 'student_master.sm_id = student_program.sp_smid');
			$this->db->where($wharray);
			$query = $this->db->get()->result();
			//print_r($query);die;

		//get one by one student
			foreach($query as $row){
				$Sid = $row->sp_smid;
				$prgid = $row->sp_programid;	
				//print_r($Sid .' '. $prgid);die;
			}
		
		// call the function from university model generat_rollnumber($tablename,$prgid,$field,$whfield,$Sid)
		$genenrollment = $this->univmodel->generat_rollnumber('student_master',$prgid,'sm_enrollmentno','sm_id',$Sid);
	//	print_r($genenrollment);die;
		// load the page 
		$stu_name = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		$stu_enroll = $this->commodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$Sid)->sm_enrollmentno;	
		$this->session->set_flashdata('success', 'Student' .' '.$stu_name.' '. 'Enrollment Number' .' '.$stu_enroll.' '. 'Successfully Generated .');
		redirect('hodadmissionstu/listenrolledstu');

		$selectdata = 'sp_smid,sp_programid,sp_semregdate';
                $whdata = array('sp_deptid' => $deptid,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);

                $getstuid = $this->commodel->get_listspficemore('student_program',$selectdata,$whdata);
                $data['getstuid'] = $getstuid;
                $this->load->view('hod_admissionstu/stu_enrollment',$data);
	}


	public function stu_nonverified(){
		$hodid = $this->session->userdata('id_user');
		//$hoddptid = $this->commodel->get_listspfic1('user_role_type','deptid','userid',$hodid)->deptid;
		$hoddptid = $this->commodel->get_depid('user_role_type',$this->session->userdata('id_user'),5);
		
		foreach($hoddptid as $hodid){
			$sarray='sp_smid';
			$wharray = array('sp_deptid' => $hodid);
			$data['stusmid'] = $this->commodel->get_listspficemore('student_program',$sarray,$wharray);
			
		}
		$this->load->view('hod_admissionstu/stu_nonverified',$data);
	}

	public function stu_verified(){
		$hodid = $this->session->userdata('id_user');
		//$hoddptid = $this->commodel->get_listspfic1('user_role_type','deptid','userid',$hodid)->deptid;
		$hoddptid = $this->commodel->get_depid('user_role_type',$this->session->userdata('id_user'),5);
		
		foreach($hoddptid as $hodid){
			$sarray='sp_smid';
			$wharray = array('sp_deptid' => $hodid);
			$data['stusmid'] = $this->commodel->get_listspficemore('student_program',$sarray,$wharray);
			
		}
		$this->load->view('hod_admissionstu/stu_verified',$data);
	}

	public function stu_verifidata(){
		$smid = $this->uri->segment(3);
		$data['smid']=$smid;
		$hlno = $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$smid)->sas_hallticketno;
		$data['hlno'] = $hlno;
		$progid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
		$data['progid']=$progid;
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$progid)->prg_name;
		$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$progid)->prg_branch;
		$data['prgname'] = $prgname;
		$data['prgbranch'] = $prgbranch;

		$stu_data = $this->commodel->get_listrow('student_master','sm_id',$smid)->row();
		if(!empty($stu_data)) {
			$name = $stu_data->sm_fname;
			$data['name']=$name;
			$dob = $stu_data->sm_dob;
			$data['dob'] = $dob;
			

			$prgcat = $this->commodel->get_listspfic1('program','prg_category','prg_id',$progid)->prg_category;
			$data['prgcat'] = $prgcat;
			$prgcatid = $this->commodel->get_listspfic1('programcategory','prgcat_id','prgcat_name',$prgcat)->prgcat_id;
			$data['prgcatid'] = $prgcatid;
			$bloodgroup = $stu_data->sm_bloodgroup;
			$data['blgroup'] =$bloodgroup;
			$gender = $stu_data->sm_gender;
			$data['gender'] = $gender;
			$mobile = $stu_data->sm_mobile;
			$data['mobile'] = $mobile;
			$email = $stu_data->sm_email;
			$data['email'] = $email;
			$categoryid = $stu_data->sm_category;
			$data['categoryid'] = $categoryid;
			$categoryname = $this->commodel->get_listspfic1('category','cat_name','cat_id',$categoryid)->cat_name;
			$data['categoryname'] = $categoryname;
			//$rollno = $stu_data->sm_applicationno;
			//$data['rollno'] = $rollno;
			$sccode = $stu_data->sm_sccode;
			$data['sccode'] = $sccode;
			$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$sccode)->sc_name;
			$data['scname'] = $scname;
			//$excode = $stu_data->sm_enterenceexamcenter;
			//$exname =  $this->commodel->get_listspfic1('adminadmissionstudent_enterenceexamcenter','eec_name','eec_id',$excode)->eec_name;	
			//$data['exname'] = $exname;
			
			//$age = $stu_data->sm_age;
			//$data['age'] = $age;
			$mastatus = $stu_data->sm_mstatus;
			$data['mastatus'] = $mastatus;
			$nationality = $stu_data->sm_nationality;
			$data['nationality'] = $nationality;
			$phyhandi = $stu_data->sm_phyhandicaped;
			$data['phyhandi'] = $phyhandi;
			$religion = $stu_data->sm_religion;
			$data['religion'] = $religion;
			$reservation = $stu_data->sm_reservationtype;
			$data['reservation'] = $reservation;
			$aadhar = $stu_data->sm_uid;
			$data['aadhar'] = $aadhar;
			

		}
		$studparent_admission = $this->commodel->get_listrow('student_parent','spar_smid',$smid)->row();
		
		if(!empty($studparent_admission)){
			$mname = $studparent_admission->spar_mothername;
			$data['mname'] = $mname;
			$fname =  $studparent_admission->spar_fathername;
			$data['fname'] = $fname;
			
			//get permanant address detail
			$paddress = $studparent_admission->spar_paddress;
			$data['paddress'] = $paddress;
			$pcity=$studparent_admission->spar_pcity;
			$data['pcity']=$pcity;
			$pstate = $studparent_admission->spar_pstate;
			$data['pstate'] = $pstate;
			$pcountry = $studparent_admission->spar_pcountry;
			$data['pcountry'] = $pcountry;
			$ppincode = $studparent_admission->spar_ppincode;	
			$data['ppincode'] = $ppincode;
			
		}

		$studedu = $this->commodel->get_listrow('student_education','sedu_smid',$smid)->result();
		$data['studedu'] = $studedu;
		
		if(isset($_POST['stuverify'])) {
		
		//student status update	
			$stusmid = $this->input->post('stu_smid');
			$stuhlno = $this->input->post('stu_hlno');

			$upstudata = array(
                 		'sas_admissionstatus'	=> 'Confirmed',
                		);
			//print_r($updata);die;
			//$upstatusconf = $this->commodel->updaterec('student_admissionstatus',$upstudata,'sas_studentmasterid',$smid);
			$sasid=$this->db->where('sas_studentmasterid',$stusmid);
			$upstatusconf = $this->db->update('student_admissionstatus', $upstudata);
		//	print_r($smid);die;
			$this->logger->write_logmessage("update","Update record in student admission status ", $data['hlno']);
               		$this->logger->write_dblogmessage("update","Update record in  student admission status ", $data['hlno']);
			
			if(!$upstatusconf)
               		{
                    		$this->logger->write_logmessage("error","Error  in student admission status".$stuhlno);
                    		$this->logger->write_dblogmessage("error","Error  in student admission status".$stuhlno);
                   		$this->session->set_flashdata('err_message','Some Data Is Incorrect - '.$stuhlno);
                    		redirect('hodadmissionstu/stu_verifiedata');
                	}
                	else{
				$this->logger->write_logmessage("insert","Insert in student admission cancel".$data['hlno']);
			        $this->logger->write_dblogmessage("insert","Insert in student admission cancel" .$stuhlno);
                    		$this->session->set_flashdata("success", "This hall ticket number ".$stuhlno." student verify successfully!");
                    		redirect('hodadmissionstu/stu_verified');
				}
			
		}
		
		$this->load->view('hod_admissionstu/stu_verifiedata',$data);
	}

}
