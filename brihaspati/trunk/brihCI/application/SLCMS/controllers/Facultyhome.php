<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Facultyhome.php
 * @author Manorama Pal (palseema30@gmail.com)
 */

class Facultyhome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "login");
        $this->load->model("Common_model", "cmodel");
	  $this->load->model("user_model","usermodel");
       // $this->load->model("User_model", "usrmodel");
	 $this->load->model("student_model", "studentmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
        /* set role id in session*/
	$data = [ 'id_role' => 2 ];
        $this->session->set_userdata($data);

        /* get logged user detail from different tables (firstname, lastname, email, campus name, org name, department name)
         * using login model and common model
         */
        $this->name=$this->login->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'));
        $this->lastn=$this->login->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'));
        $this->email=$this->login->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'));
        //$this->campusid=$this->cmodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
       // $this->campusname=$this->cmodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid);
	$sdata=array('scid');
        $whdata=array(
            'userid'  => $this->session->userdata('id_user'),
           'roleid'  => 2
           
        );
        $this->scname = $this->cmodel->get_distinctrecord('user_role_type',$sdata,$whdata);

        //$this->orgcode=$this->cmodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid);
       // $this->orgname=$this->cmodel->get_listspfic1('org_profile','org_name','org_code',$this->orgcode->org_code);
        $this->dptid=$this->cmodel->get_depid('user_role_type',$this->session->userdata('id_user'),2);
        $this->deptname=$this->cmodel->get_listspfic1('Department','dept_name','dept_id',$this->dptid->deptid);
        /*get course Detail*/
	foreach($this->scname as $row){
		$scid = $row->scid;
		
        	$selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        	$this->admcyear=$this->usermodel->getcurrentAcadYear();
       		$data=array(
            		'pstp_scid' => $scid,
            		'pstp_teachid' => $this->session->userdata('id_user'),
            		'pstp_acadyear' => $this->admcyear
           
       	 	);
       	 	$this->cdetail=$this->cmodel->get_listspficemore('program_subject_teacher',$selectfield,$data);
		//print_r($this->cdetail);
	}
        $this->load->view('facultyhome');
    }

    /* get student list acording to program and campus*/
    
    public function studentlist() {
       	$uid = $this->session->userdata('id_user');
 	//get the value of current semester and academic year
        $acadyear = $this->usermodel->getcurrentAcadYear();
        $datarec['academicyear']=$acadyear;
        $semestertype = $this->usermodel->getcurrentSemester();
        $datarec['semester']=$semestertype;
       //get the campus id of current user(Teacher)
	/*$sdata=array('scid');
        $whdata=array(
            'userid'  => $uid,
            'roleid'  => 2
           
      /  );
       // $this->scname = $this->cmodel->get_distinctrecord('user_role_type',$sdata,$whdata);
	//foreach($this->scname as $row){
		$this->campusid = $row->scid;*/

        	$selectfield=array('pstp_prgid');
       	 	$data=array(
            		//'pstp_scid' =>$this->campusid,
            		'pstp_teachid' => $uid,
            		'pstp_acadyear' => $acadyear,
    //        		'pstp_sem'    => $semestertype
        		);
        	$this->prgsublist=$this->cmodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);
        	$datarec['prgsublist']=$this->prgsublist;
//		print_r($this->prgsublist);
        if(isset($_POST)){
            /* without search get all student list according to selected program*/
            
            $prgname=$this->input->post('programname',TRUE);
	    //$sem=$this->input->post('stu_sem',TRUE);	
            if(!empty($prgname)){
                $datarec['selprg_name']=$prgname;
		$datarec['filprg_name']=$prgname;
            }
            else{
		$datarec['filprg_name']= "";	
                $datarec['selprg_name']= "";
            }
	   /* if(!empty($sem)){
                $datarec['sem']=$sem;
            }
            else{
                $datarec['sem']= "";
            }*/
            $sfield=array('sp_smid','sp_deptid','sp_subid1','sp_subid2','sp_subid3','sp_subid4','sp_subid5','sp_subid6','sp_subid7','sp_subid8','sp_subid9','sp_subid10');
            //$this->campucode=$this->cmodel->get_listspfic1('study_center','sc_id','sc_id',$this->campusid)->sc_id;
           // $datarec['ccode']=$this->campucode;
	
            $stdntdata=array(
          //      'sp_sccode'   =>$this->campusid,
                'sp_programid' =>$prgname,
                'sp_acadyear' =>$acadyear,
     //         'sp_semester'  =>  $semestertype
            );
       
            $filter = $this->input->post('filter');
           
            $search = $this->input->post('search');
            
            if (isset($filter) && !empty($search)) {
	   	 $field  = $this->input->post('field');
	   	 $prgname2=$this->input->post('prgname',TRUE);
            	if(!empty($prgname2)){
                	$datarec['filprg_name']=$prgname2;
                	$datarec['selprg_name']=$prgname2;
            	}
           	else{
                	$datarec['filprg_name']= "";
			$datarec['selprg_name']="";
           	}
                $stdntdata2=array(
                	//'sp_sccode'    => $this->campusid,
                	'sp_programid' => $prgname2,
                	'sp_acadyear'  => $acadyear,
  	    //          'sp_semester'  =>$semestertype
                );
                $datarec['studentdetail'] = $this->studentmodel->getStudentsWhereLike($field, $search,$stdntdata2);
		//print_r($datarec['studentdetail']);die;
            }
            else{
                /* without search*/ 
                $stud_prg_rec = $this->cmodel->get_listspficemore('student_program',$sfield,$stdntdata);
		
                $datarec['studentdetail'] = $stud_prg_rec;
            }
        //}//foreach close
        }//ifpost
        $this->load->view('faculty/studentlist',$datarec);
    }

   //This function check for duplicate student attendence		   
    public function stuexist($smuid) {
        $is_exist = $this->commodel->isduplicate('student_attendance','satd_smid',$smuid);
	//$stuname = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smuid)->sm_fname;
	//print_r($is_exist);
        if ($is_exist)
        {
		$this->form_validation->set_message('stuexist','That students' ." ".$smuid. " ".'attendence is already submited.');
            return false;
        }
        else {
            return true;
        }
    }


/***************************************************Student Attendence*********************************************************/
	public function student_attendence(){
		$cdate = date('Y-m-d');
		$uid = $this->session->userdata('id_user');
		$uname = $this->session->userdata('username');
		//$this->uname = $this->session->userdata('username');
		//get the value of current semester and academic year
        	$acadyear = $this->usermodel->getcurrentAcadYear();
        	$datarec['academicyear']=$acadyear;

		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield=array('pstp_prgid');
		//$selectfield=array('pstp_prgid');
        	$data=array(
            		'pstp_teachid' => $uid,
            		'pstp_acadyear' => $acadyear,
        	);
        	$prgsublist = $this->cmodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);
		//print_r($this->prgsublist);
        	$datarec['prgsublist']=$prgsublist;

		if(isset($_POST)){
			$this->sem = $this->input->post('semester',TRUE);
			$this->prgid = $this->input->post('program_branch',TRUE);
			$this->subjectid = $this->input->post('subjectname',TRUE);
			$this->paperid = $this->input->post('papername',TRUE);
			$date = $this->input->post('adate',TRUE);
			$search = $this->input->post('search');

           		 if(isset($search)) 
            		 {
			 $this->form_validation->set_rules('program_branch','Select Programme','trim|xss_clean|required');
			 $this->form_validation->set_rules('subjectname','Select Subject','trim|xss_clean|required');
			 $this->form_validation->set_rules('papername','Select Papername','trim|xss_clean|required');
			 $this->form_validation->set_rules('semester','Select Semester','trim|xss_clean|required');	
			 if($this->form_validation->run() == False){
				$this->session->set_flashdata("err_message",'Select Programme , Semester , Subject and Paper Name.' );	
				redirect('facultyhome/student_attendence');
			 }
			else{
			 $getdata = '';	
					 $whereau = "((sp_programid = $this->prgid) AND (sp_semester = $this->sem) AND (sp_acadyear = '$acadyear') AND ((sp_subid1 = $this->subjectid) OR (sp_subid2 = $this->subjectid) OR (sp_subid3 = $this->subjectid) OR (sp_subid4 = $this->subjectid) OR (sp_subid5 = $this->subjectid) OR (sp_subid6 = $this->subjectid) OR (sp_subid7 = $this->subjectid) OR (sp_subid8 = $this->subjectid) OR (sp_subid9 = $this->subjectid) OR (sp_subid10 = $this->subjectid)))";

				$selectdata=array('sp_smid');
				if(!empty($whereau)){
	        			//$getdata = $this->commodel->get_listspficemore2('student_program',$selectdata,$whdata,$where_au);
	        			$getdata = $this->cmodel->get_listspficemore('student_program',$selectdata,$whereau);
					$datarec['getdata']=$getdata;	
					//print_r($whereau);print_r($getdata);die;
				}
				
			 }//else search close
			}//if isset search close
			 $submit = $this->input->post('Submit');
           		 if (isset($submit))
			 {
				
			$this->form_validation->set_rules('classtype','Select Class Type','trim|xss_clean|required');
			if($this->form_validation->run() == False){
				$this->session->set_flashdata("err_message",'Select Class Type.' );	
				redirect('facultyhome/student_attendence');
			}
			else{
				//$scenter = $this->input->post('studycenter',TRUE);
				//$department = $this->input->post('department',TRUE);
				//$countr = $this->input->post('count',TRUE);
				//for($i=0; $i<$countr ; $i++)
					for($i=0; ; $i++)
					{
						$smid = $_POST['stu_master_id'.$i];
						$attendence = $_POST['attendence'.$i];
						
						if(!empty($smid))
						{

				$datacheck = array('satd_smid'=>$_POST['stu_master_id'.$i], 'satd_scid'=>$_POST['studycenter'], 'satd_deptid'=>$_POST['department'], 'satd_acadyear'=>$acadyear, 'satd_prgid'=>$_POST['program_branch'], 'satd_sem'=>$_POST['semester'],'satd_papid'=>$_POST['papername'],'satd_subid'=>$_POST['subjectname'],'satd_adate'=>$date,'satd_creatorid'=>$uname,'satd_createdate'=>$date,'satd_classtype'=>$_POST['classtype']);
				//print_r($datacheck);die;
							$pstdatadup = $this->cmodel->isduplicatemore('student_attendance',$datacheck);
							//print_r($pstdatadup);die;
        						if($pstdatadup){
								//echo "<span style=' color: #D8000C;background-color: #FFBABA;'>";
								$this->logger->write_logmessage("insert", "Student attendence already submitted ." .$datacheck);
                    						$this->logger->write_dblogmessage("insert", "Student attendence already submitted." .$datacheck);
								$this->session->set_flashdata("err_message",'Student attendence already submitted.');
								//echo "</span>";
                						redirect('facultyhome/student_attendence');
								//return;

							}
        						else{
							
								$attendence = array(
									'satd_smid'   		=>	$smid,
                							'satd_scid'  		=>	$_POST['studycenter'],
                							'satd_deptid'   	=>	$_POST['department'],
                							'satd_acadyear'   	=>	$acadyear,
									'satd_prgid'     	=>	$_POST['program_branch'],
									'satd_sem'   	 	=>	$_POST['semester'],
                							'satd_papid'  	 	=>	$_POST['papername'],
									'satd_subid'  	 	=>	$_POST['subjectname'],
									'satd_classtype' 	=>	$_POST['classtype'],
									'satd_astatus'   	=>	$attendence,
									'satd_adate'      	=>	$date,
									'satd_creatorid'    	=>	$uname,
									'satd_createdate'      	=>	$cdate
                						);
									
								$insertatt = $this->cmodel->insertrec('student_attendance',$attendence);
								
							}//close else	
							
						}//if not empty close
						else {break;}
				   	}//for close
					 if(!$insertatt)
					{
                   				$this->logger->write_logmessage("insert", "Error in student attendence . " );
                    				$this->logger->write_dblogmessage("insert", "Error in student attendence . " );
                   	 			$this->session->set_flashdata("err_message",'Error in student attendence .' );
						redirect('facultyhome/student_attendence');
                			}	
                			else{
                    				$this->logger->write_logmessage("insert","Student attendence send successfully .");
                    				$this->logger->write_dblogmessage("insert", "Student attendence send successfully .");
                   				$this->session->set_flashdata("success", "Today Attendence is Submitted.");
						redirect('facultyhome/student_attendence_view');
                			 }	
			  }//else submit close
			
			}//if isset submit close
		}//if isset post close
		$this->load->view('faculty/student_attendence',$datarec);
	}

	public function student_attendence_view(){
		$uname = $this->session->userdata('username');
		
		$uid = $this->session->userdata('id_user');
		$acadyear = $this->usermodel->getcurrentAcadYear();
        	$datarec['academicyear']=$acadyear;
		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield=array('pstp_prgid');
		//$selectfield=array('pstp_prgid');
        	$data=array(
            		'pstp_teachid' => $uid,
            		'pstp_acadyear' => $acadyear,
        	);
        	$prgsublist = $this->cmodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);
		//print_r($this->prgsublist);
        	$datarec['prgsublist']=$prgsublist;

		$selectfield=array('pstp_sem','pstp_subid','pstp_prgid','pstp_papid','pstp_acadyear');
        	$data=array(
            		'pstp_teachid'  => $uid,
			'pstp_acadyear' => $acadyear,
			//'pstp_prgid'	=> $this->prgid,
			//'pstp_sem'	=> $this->sem
        	);
        	$subject = $this->cmodel->get_listspficemore('program_subject_teacher',$selectfield,$data);
		//print_r($subject);
		$datarec['subject'] = $subject;
		
		$search = $this->input->post('search');
		if(isset($_POST['search'])){
			$this->sem = $this->input->post('semester',TRUE);
			$this->prgid = $this->input->post('program_branch',TRUE);
			$this->subjectid = $this->input->post('subjectname',TRUE);
			$this->paperid = $this->input->post('papername',TRUE);
			$date = $this->input->post('adate',TRUE);
 			$whereau = array('satd_prgid' => $this->prgid ,'satd_sem' => $this->sem , 'satd_acadyear' => $acadyear ,'satd_subid' => $this->subjectid ,'satd_papid' => $this->paperid);
				$selectdata='satd_smid,satd_subid,satd_papid,satd_classtype,satd_astatus,satd_sem,satd_acadyear,satd_scid,satd_deptid,satd_prgid,satd_adate';
			if(!empty($whereau)){
	        		$getdata = $this->cmodel->get_listspficemore('student_attendance',$selectdata,$whereau);
				$datarec['getdata']=$getdata;	
			}

			$record=array( );	
			if(!empty($this->prgid)){
			if(!empty($uname)){   
			$record['satd_creatorid'] = $uname;
			$record ['satd_prgid'] =  $this->prgid;}}

			if(!empty($this->sem)){
			if(!empty($uname)){   
			$record['satd_creatorid'] = $uname;
			$record ['satd_sem'] =  $this->sem;}}

			if(!empty($this->subjectid)){
			if(!empty($uname)){   
			$record['satd_creatorid'] = $uname;
			$record ['satd_subid'] =  $this->subjectid;}}

			if(!empty($this->paperid)){
			if(!empty($uname)){   
			$record['satd_creatorid'] = $uname;
			$record ['satd_papid'] =  $this->paperid;}}

			$selectdata1='satd_smid,satd_subid,satd_papid,satd_classtype,satd_astatus,satd_sem,satd_acadyear,satd_scid,satd_deptid,satd_prgid,satd_adate';
			if(!empty($record)){		
       				$getdata = $this->cmodel->get_listspficemore('student_attendance',$selectdata1,$record);
				//print_r($getdata);
				$datarec['getdata']=$getdata;	
			}
			
		}//post close
		$uname = $this->session->userdata('username');
		//get student attendence record
		$studatt = $this->cmodel->get_listrow('student_attendance','satd_creatorid',$uname)->result();
		
		$datarec['studatt'] =$studatt;
		$this->load->view('faculty/student_attendence_view',$datarec);
	}


/*********************************************************student attendence dependent dropdown start**************************************************/
	//get semester record
	public function semester_get(){
		$acadyear = $this->usermodel->getcurrentAcadYear();
		$prgid = $this->input->post('program_branch');
	
		$uid=$this->session->userdata('id_user');
		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield=array('pstp_prgid','pstp_sem');
        	$data=array(
            		'pstp_teachid'  => $uid,
			'pstp_acadyear' => $acadyear,
			'pstp_prgid'	=> $prgid
        	);
        	$sem = $this->cmodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);

		echo "<select name='semester' id='semester'>";	
			echo "<option selected='true' disabled>"."Semester"."</option>";
		foreach($sem as $datas):   
			 echo "<option  value='$datas->pstp_sem'>"."$datas->pstp_sem"."</option>";
   		endforeach;
		echo "</select>";
			
	}
	//get all teacher subjects
	public function subject_get(){
		$acadyear = $this->usermodel->getcurrentAcadYear();
		$semprgid = $this->input->post('semprg');
		$subpart = explode(',',$semprgid);

		$uid=$this->session->userdata('id_user');

		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield=array('pstp_sem','pstp_subid','pstp_prgid');
        	$data=array(
            		'pstp_teachid'  => $uid,
			'pstp_acadyear' => $acadyear,
			'pstp_prgid'	=> $subpart[0],
			'pstp_sem'	=> $subpart[1]
        	);
        	$subject = $this->cmodel->get_listspficemore('program_subject_teacher',$selectfield,$data);
		echo "<select name='subjectname'>";	
			echo "<option selected='true' disabled>"."Select Subject"."</option>";	
		foreach($subject as $data){
			$subname = $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$data->pstp_subid)->sub_name;
			$whdata = array('sub_id'  =>  $data->pstp_subid,'sub_program' => $data->pstp_prgid , 'sub_semester' => $data->pstp_sem);
			$sdata = array('sub_name','sub_id');
			$subname = $this->cmodel->get_listspficemore('subject',$sdata,$whdata);
		
		foreach($subname as $datas):   
			 echo "<option id='subjectname' value='$datas->sub_id'>"."$datas->sub_name"."</option>";
   		endforeach;
		
		}
		echo "</select>";	
	}

	//get all teacher papers
	public function paper_get(){
		$acadyear = $this->usermodel->getcurrentAcadYear();
		$prg_sem_subid = $this->input->post('prg_sem_sub');
		$subparts = explode(',',$prg_sem_subid);
		$uid=$this->session->userdata('id_user');
		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield=array('pstp_subid','pstp_papid','pstp_prgid','pstp_sem','pstp_acadyear');
        	$data=array(
            		'pstp_teachid'  => $uid,
			'pstp_subid'	=> $sub,
			'pstp_acadyear' => $acadyear,
			'pstp_prgid'	=> $subparts[0],
			'pstp_sem'	=> $subparts[1],
			'pstp_subid'	=> $subparts[2]

        	);
        	$paper = $this->cmodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);
		foreach($paper as $row){
			$selectfield=array('subp_name','subp_id');
			$data=array(
            			'subp_sub_id'   => $row->pstp_subid,
				'subp_id'	=> $row->pstp_papid,
				'subp_degree'	=> $row->pstp_prgid,
				'subp_acadyear' => $row->pstp_acadyear,
				'subp_sem'	=> $row->pstp_sem,
				
	        		);
		$papername = $this->cmodel->get_distinctrecord('subject_paper',$selectfield,$data);
			
		}
		echo "<select name='papername'>";	
			echo "<option selected='true' disabled>"."Paper Name"."</option>";
		foreach($papername as $row):   
			 echo "<option id='papername' value='$row->subp_id'>"."$row->subp_name"."</option>";
   		endforeach;
		echo "</select>";
			
	}

/*********************************************************student attendence dependent dropdown end**************************************************/

 
}

