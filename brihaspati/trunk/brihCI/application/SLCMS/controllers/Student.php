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
		//$this->db2 = $this->load->database('login', TRUE);
		$this->load->model('user_model');
		$this->load->model('Common_model',"commodel");
		$this->load->model("Student_model","stumodel");
		$this->load->model('Login_model',"logmodel"); 
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->model("DateSem_model","datmodel");
	}

	//This function has been created for display the list of degree on the basis of program category
	public function degreelist(){
			$pgcatname = $this->input->post('stu_prgcate');
			$list = $this->commodel->get_listspfic2('program','','prg_name,prg_id,prg_branch','prg_category',$pgcatname,'prg_name,prg_branch');
		echo "<select>";
		echo "<option disabled selcted>Select Programe</option>";
		foreach($list as $datas): 
      		  	echo "<option  id='degree' value='$datas->prg_id'> " ."$datas->prg_name".'('.$datas->prg_branch.')'."</option>";
  		endforeach;
		echo "</select>";
	 }

	//This function has been created for display the list of branch on the basis of program
	/*public function branchlist(){
			$pgid = $this->input->post('Sprogramname');
			$list = $this->commodel->get_listspfic2('admissionmeritlist','','branchname','course_name',$pgid,'branchname');
			foreach($list as $datas): ?>   
      		  		<option  id='branchname' value="<?php echo $datas->branchname;?>"><?php echo $datas->branchname; ?></option>
<?php   		endforeach;
	 }*/
	
	// Check for user admission login process
	public function student_step0() {
		
		 $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       		 $this->session->set_flashdata($array_items);
		 //$this->prgbranch = $this->commodel->get_list('program');
		//$this->pnresult = $this->commodel->get_listspfic2('program','prg_name', '','','','prg_name');

		if(isset($_POST['login'])){
			$this->form_validation->set_rules('Sanumber', 'JEE Main Number', 'required');
			$this->form_validation->set_rules('Sjeeanumber', 'JEE Application Number', 'required');
			$this->form_validation->set_rules('Sdateofbirth', 'Your Date of Birth', 'required');
			//$this->form_validation->set_rules('Sanumber', 'Application Number', 'required');
			//$this->form_validation->set_rules('Sprogramname', 'Program/Course', 'required');
			//$this->form_validation->set_rules('Sbranchname', 'Branch', 'required');
			//$this->form_validation->set_rules('Semail', 'Student Email-id', 'required');
		
				if ($this->form_validation->run() == FALSE) {	
					//$this->load->view('student/student_step0');
					}else{	
											
						$data = array(
							//'entexamrollno' => $this->input->post('Sanumber'),
							//'course_name'    => $this->input->post('Sprogramname'),
							//'branchname'     => $this->input->post('Sbranchname'),
							//'student_email'  => $this->input->post('Semail')
							'jee_mainno' => $this->input->post('Sanumber'),
							'jeeapplication_no'    => $this->input->post('Sjeeanumber'),
							'student_dob'  => $this->input->post('Sdateofbirth')						
						);
						
						//verify the data existance filled by user 
						$result = $this->commodel->isduplicatemore("admissionmeritlist",$data);
						//	print_r($result);
						//$result = $this->stumodel->login($data);
						if ($result == true) {
							//$number = $this->input->post('Sanumber');
							$number = $this->input->post('Sjeeanumber');
							$this->studentstep($number);
						}
						else {
							
							$this->session->set_flashdata("err_message",'Your details are invalid');
							redirect('student/student_step0');
							}
						
					}
	        }
		$this->load->view('student/student_step0');
	}


	//check for other form completed admission step
	public function studentstep($applicationno){
	
		//check the existence of entry in student_admissionstep table 
		//$this->resstep=$this->commodel->get_listrow('student_admissionstep','sm_application',$applicationno)->result();
		$this->restep=$this->commodel->get_listrow('student_admissionstep','application_no',$applicationno)->result();
		
		//print_r($this->restep);die;
		if(!empty($this->restep)) {
		
		// if exist then check which step is completed and redirect to incmplete step with appropriate data
			foreach($this->restep as $rslt){
				$stp1= $rslt->step1_status;
				$stp2= $rslt->step2_status;
				$stp3= $rslt->step3_status;
				$stp4= $rslt->step4_status;
				$stp5= $rslt->step5_status;
				$appno = $rslt->application_no;
			}
			if($stp1 == 0 || $stp1 == NULL){
				
			// set the student role and application number in session
 				$data = [
                    'username' => $applicationno,        
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
				
				$this->session->set_userdata($data);
                                //verify the data existance filled by user 
				redirect('Student/student_step1');
				}
			
			else if($stp2 == 0 || $stp2 == NULL){
				//check the value set in session if not then
				//get the student master id
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
				
				// set the student master and application number in session
				$data = [
					'username' => $applicationno,
                     'sm_id' => $smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
				
                               $this->session->set_userdata($data);
				// redirect to step2 for completion
				//redirect('Student/student_step2');
                  redirect('Student/student_step3');
			}
			else if($stp3 == 0 || $stp3 == NULL){
				//check the value set in session if not then
				// get the student master id
					//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
					$smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
				// set the student master and application number in session
					$data = [
					'username' => $applicationno,
                        	        'sm_id' => $smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                                $this->session->set_userdata($data);
				// redirect to step3 for completion
				//redirect('Student/student_step3');
                  redirect('Student/student_checklist');
			}
			else if($stp4 == 0 || $stp4 == NULL){
				//check the value set in session if not then
				// get t'username' => $applicationno,he student master id
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
				// set the student master and application number in session
				$data = [
					'username' => $applicationno,
                        	        'sm_id' => $smid,
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
				//$this->smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_applicationno',$applicationno)->sm_id;
				$smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
				// set the student master and application number in session
				$data = [
					'username' => $applicationno,
                        	        'sm_id' => $smid,
					'app_no' => $applicationno,
			                'id_role' => 3
			                ];
                               $this->session->set_userdata($data);
				// redirect to step5 for completion
				redirect('Student/student_step5');
			}
			else{   //bracess for the else is added again by nks
				// if step 5 is completed then redirect to student home page else redirect to correct step page
				//get the userid from student master
				//$smres=$this->commodel->get_listspfic1('student_master','sm_userid','sm_applicationno',$applicationno);
				$smid= $this->commodel->get_listspfic1('student_entry_exit','senex_smid','senex_entexamapplicationno',$applicationno)->senex_smid;
				$smres= $this->commodel->get_listspfic1('student_master','sm_userid','sm_id',$smid);
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

			$result1 = $this->commodel->isduplicate('admissionstudent_master','asm_applicationno',$applicationno);
		 	if ($result1 == true) {
                                //verify the data existance filled by user 
				redirect('Student/student_step1');
			}
			else if($result1 == false){
                                //verify the data existance filled by user 
				redirect('Student/student_admissionform');
			}
		}
	}



    //This function check for duplicate mobile 		   
    public function mobileexist($mobile) {
        $is_exist = $this->commodel->isduplicate('student_master','sm_mobile',$mobile);
	//print_r($is_exist);
        if ($is_exist)
        {
            $this->form_validation->set_message('mobileexist','Mobile number'." " .$mobile. " ".'is already exist check your number again.');
            return false;
        }
        else {
            return true;
        }
    }

	//This function check for duplicate aadhar card 		   
    public function aadharexist($smuid) {
        $is_exist = $this->commodel->isduplicate('student_master','sm_uid',$smuid);
	//print_r($is_exist);
        if ($is_exist)
        {
		$this->form_validation->set_message('aadharexist','Aadhar number' ." ".$smuid. " ".'is already exist check your aadhar number again.');
            return false;
        }
        else {
            return true;
        }
    }

	public function student_admissionform(){
		$number = $this->session->userdata['app_no'];
		$data['number']	= $number;
		$data['stu_prgcat'] = $this->commodel->get_list('programcategory');
		//$data['stu_examcenter'] = $this->commodel->get_list('student_examcenter');
		$data['stu_studycenter'] = $this->commodel->get_list('study_center');
		//$data['stu_studycenter'] = $this->commodel->get_list('org_profile');	
		$data['stu_categorylist'] = $this->commodel->get_list('category');
		$data['stu_depresult'] = $this->commodel->get_list('Department');
		$data['stu_program'] = $this->commodel->get_list('program');
		//$stu_addmisdata = $this->commodel->get_listrow('admissionmeritlist','entexamrollno',$number)->row();
		$stu_addmisdata = $this->commodel->get_listrow('admissionmeritlist','jeeapplication_no',$number)->row();
		if(!empty($stu_addmisdata)) {
			$prgname = $stu_addmisdata->course_name;	
			$data['email'] = $stu_addmisdata->student_email;
			$data['name'] = $stu_addmisdata->student_name;
			$data['prgname'] = $stu_addmisdata->course_name;
			$data['branchname'] = $stu_addmisdata->branchname;
			$prgname = $data['prgname'];
			$branchname = $data['branchname'];
			$whdata = array('prg_name' => $prgname,'prg_branch' => $branchname);
			$data['prgcat'] = $this->commodel->get_distinctrecord('program','prg_category',$whdata);
		//$data['prgcat'] = $this->commodel->get_distinctrecord('program','prg_category,prg_id',$whdata);
			$data['fathername'] = $stu_addmisdata->father_name;
		}
	//check admission last date for student
	//$ldate = $this->commodel->get_listspfic1('admissionmeritlist','lastdate_admission','entexamrollno',$number)->lastdate_admission;
	$ldate = $this->commodel->get_listspfic1('admissionmeritlist','lastdate_admission','jeeapplication_no',$number)->lastdate_admission;

	$admidate = $this->datmodel->comparedate($ldate);
	if($admidate)
	{
		if(isset($_POST['stu_addmission'])){
				$this->form_validation->set_rules('stu_addrollno','Application no.','trim|xss_clean|numeric');
            			$this->form_validation->set_rules('stu_addname','Applicant name','trim|xss_clean');
            			$this->form_validation->set_rules('stu_addgender','Gender','trim|xss_clean|required');
            			$this->form_validation->set_rules('stu_adddob','Date of birth','trim|xss_clean|required');
	    			$this->form_validation->set_rules('stu_addaadhar','Aadhar number','trim|xss_clean|is_numeric|max_length[12]|callback_aadharexist');
            			$this->form_validation->set_rules('stu_addabgroup','Blood group','trim|xss_clean');
           			$this->form_validation->set_rules('stu_addreligion','Religion','trim|xss_clean|required');
            			$this->form_validation->set_rules('stu_addmobile','Mobile number','trim|xss_clean|is_numeric|max_length[12]|required');
	    			$this->form_validation->set_rules('stu_addemail','Email-id','trim|xss_clean|required|valid_email');
				$this->form_validation->set_rules('stu_addcourse','Program/Course name','trim|xss_clean');
				$this->form_validation->set_rules('stu_addcenter','Study center','trim|xss_clean|required');
          			//$this->form_validation->set_rules('','Program/Course type','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_adddepart','Departmnet not select','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_addprgcate','Program Category not select','trim|xss_clean|required');

				$this->form_validation->set_rules('stu_addmothername','Mother name','trim|xss_clean|required');
          			$this->form_validation->set_rules('stu_addfathername','Father name','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_addfathermono','Father mobile number','trim|xss_clean|required|numeric');
          			$this->form_validation->set_rules('stu_addmothermono','Mother mobile number','trim|xss_clean|required|numeric');
				$this->form_validation->set_rules('stu_addfatheroccu','Father occupation','trim|xss_clean|required');
          			$this->form_validation->set_rules('stu_addmotheroccu','Mother occupation','trim|xss_clean|required');

           			$this->form_validation->set_rules('stu_addcate','Category','trim|xss_clean|required');
           			$this->form_validation->set_rules('stu_addpstreet','Postal address','trim|xss_clean|required');
				//$this->form_validation->set_rules('Sdist','District','trim|xss_clean|required');
				//$this->form_validation->set_rules('Spost','Post office','trim|xss_clean|required');
	   			$this->form_validation->set_rules('stu_addpcity','City','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_addpstate','State','trim|xss_clean|required');
          			$this->form_validation->set_rules('stu_addpcountry','Country','trim|xss_clean|required');
           			$this->form_validation->set_rules('stu_addpcode','Postal Code','trim|xss_clean|required|numeric');

				$this->form_validation->set_rules('stu_hcname','High School Class Name','trim|xss_clean|required');
          			$this->form_validation->set_rules('stu_hinstitute','High School Institute Name','trim|xss_clean|required');
           			$this->form_validation->set_rules('stu_hboard','High School Board','trim|xss_clean|required');
           			$this->form_validation->set_rules('stu_hsubject','High School Subject','trim|xss_clean|required');
	   			$this->form_validation->set_rules('stu_hpassed','High School Passed/Appeared','trim|xss_clean|required');
	   			$this->form_validation->set_rules('stu_hmmarks','High School Max Marks','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_hmobtain','High School Marks Obtained','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_hpercentage','High School Percentage','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_hyear','High School Passing Year','trim|xss_clean|required');
	
	    			$this->form_validation->set_rules('stu_icname','Intermediate School Class Name','trim|xss_clean|required');
          			$this->form_validation->set_rules('stu_iinstitute','Intermediate School Institute Name','trim|xss_clean|required');
           			$this->form_validation->set_rules('stu_iboard','Intermediate School Board','trim|xss_clean|required');
           			$this->form_validation->set_rules('stu_isubject','Intermediate School Subject','trim|xss_clean|required');
	   			$this->form_validation->set_rules('stu_ipassed','Intermediate School Passed/Appeared','trim|xss_clean|required');
	   			$this->form_validation->set_rules('stu_immarks','Intermediate School Max Marks','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_imobtain','Intermediate School Marks Obtained','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_ipercentage','Intermediate School Percentage','trim|xss_clean|required');
				$this->form_validation->set_rules('stu_iyear','Intermediate School Passing Year','trim|xss_clean|required');

	    			if($this->form_validation->run() == TRUE){
					$stu_reserve = '';
						if(!empty($_POST['basic']))
						{
						foreach($_POST['basic'] as $row){
							$stu_reserve .= $row . ', ';
						}}
					$stu_personnel = array(
						'sm_sccode'			=>	$this->input->post('stu_addcenter'),
                		'sm_fname'  		=>	$this->input->post('stu_addname'),
                		'sm_dob'   			=>	$this->input->post('stu_adddob'),
                		'sm_email'   		=>	$this->input->post('stu_addemail'),
                		'sm_mobile'   		=>	$this->input->post('stu_addmobile'),
                		'sm_category'		=>	$this->input->post('stu_addcate'),
                		'sm_gender'  		=>	$this->input->post('stu_addgender'),
                		'sm_mstatus'  		=>  $this->input->post('stu_addmaritailst'),
						'sm_bloodgroup'   	=>	$this->input->post('stu_addabgroup'),
						'sm_nationality'    =>  $this->input->post('stu_addnationality'),
						'sm_reservationtype'=>	$stu_reserve,
						'sm_phyhandicaped'	=>	$this->input->post('stu_adddisability'),
						'sm_uid'   			=>	$this->input->post('stu_addaadhar'),
                  		'sm_religion'  		=>	$this->input->post('stu_addreligion'),
         				//'sm_enrollmentno'	=>	$enroollno'
                			);
					//$this->db->insert('student_master', $data);	
					$this->db->insert('student_master', $stu_personnel);
					$insertid = $this->db->insert_id();

					//insert into student parent
					$stu_parent = array(
						'spar_smid'				=>	$insertid,
						'spar_mothername'		=>	$this->input->post('stu_addmothername'),
						'spar_motheroccupation'	=>	$this->input->post('stu_addmotheroccu'),
						'spar_motherphoneno'   	=>	$this->input->post('stu_addmothermono'),
						'spar_fathername'   	=>	$this->input->post('stu_addfathername'),
						'spar_fatheroccupation'	=>	$this->input->post('stu_addfatheroccu'),
						'spar_fatherphoneno'   	=>	$this->input->post('stu_addfathermono'),

                		'spar_paddress'  	=>	$this->input->post('stu_addpstreet'),
						//'spar_pdistrict'  	=>	$_POST['Sdist'],
						//'spar_ppostoffice'  	=>	$_POST['Spost'],
                		'spar_pcity'  		=>	$this->input->post('stu_addpcity'),
                		'spar_pstate'   	=>	$this->input->post('stu_addpstate'),
						'spar_pcountry'   	=>	$this->input->post('stu_addpcountry'),
						'spar_ppincode'   	=>	$this->input->post('stu_addpcode'),
						'spar_caddress'  	=>	$this->input->post('stu_addcostreet'),
						//'spar_cdistrict'  	=>	$this->input->post['Sdist'),
						//'spar_cpostoffice'  	=>	$this->input->post['Spost'),
                		'spar_ccity'  		=>	$this->input->post('stu_addcocity'),
                		'spar_cstate'   	=>	$this->input->post('stu_addcostate'),
						'spar_ccountry'   	=>	$this->input->post('stu_addcocountry'),
						'spar_cpincode'   	=>	$this->input->post('stu_addcocode')
                	
                			);
					//print_r($parent);		
					$parent = $this->commodel->insertrec('student_parent', $stu_parent);
					
					//insert record in student education table
					$stu_Hedu = array(
						'sedu_smid'				=>	$insertid,
                		'sedu_class'   			=>	$this->input->post('stu_hcname'),
                		'sedu_subject'  		=>	$this->input->post('stu_hsubject'),
                		'sedu_board'  			=>	$this->input->post('stu_hboard'),
                		'sedu_passingyear'   	=>	$this->input->post('stu_hyear'),
						'sedu_resultstatus'   	=>	$this->input->post('stu_hpassed'),
						'sedu_institution'   	=>	$this->input->post('stu_hinstitute'),
						'sedu_marksobtained'   	=>	$this->input->post('stu_hmobtain'),
						'sedu_maxmarks'  	 	=>	$this->input->post('stu_hmmarks'),
						'sedu_percentage'   	=>	$this->input->post('stu_hpercentage'),
						//'asedu_percentage'   	=>	$this->input->post('eduugc_net')
                			);	
					//print_r($Hedu);
					$this->commodel->insertrec('student_education', $stu_Hedu);
				
					$stu_intn =$this->input->post('stu_icname');
					$stu_Iedu = array(
						'sedu_smid'				=>	$insertid,
						'sedu_class'   			=>	$this->input->post('stu_icname'),
                		'sedu_subject'  		=>	$this->input->post('stu_isubject'),
                		'sedu_board'  			=>	$this->input->post('stu_iboard'),
                		'sedu_passingyear'   	=>	$this->input->post('stu_iyear'),
						'sedu_resultstatus'   	=>	$this->input->post('stu_ipassed'),
						'sedu_institution'   	=>	$this->input->post('stu_iinstitute'),
						'sedu_marksobtained'   	=>	$this->input->post('stu_imobtain'),
						'sedu_maxmarks'  	 	=>	$this->input->post('stu_immarks'),
						'sedu_percentage'   	=>	$this->input->post('stu_ipercentage')
						//'asedu_percentage'   	=>	$this->input->post('eduugc_net')
             		  	 	);
					//print_r($Iedu);
					if(!empty($stu_intn)){
						$this->commodel->insertrec('student_education', $stu_Iedu);
					}

					$stu_gran =$this->input->post('stu_gsubject');
					$stu_Gedu = array(
						'sedu_smid'				=>	$insertid,
						'sedu_class'   			=>	$this->input->post('stu_gcname'),
                		'sedu_subject'  		=>	$this->input->post('stu_gsubject'),
                		'sedu_board'  			=>	$this->input->post('stu_gboard'),
                		'sedu_passingyear'   	=>	$this->input->post('stu_gyear'),
						'sedu_resultstatus'   	=>	$this->input->post('stu_gpassed'),
						'sedu_institution'   	=>	$this->input->post('stu_ginstitute'),
						'sedu_marksobtained'   	=>	$this->input->post('stu_gmobtain'),
						'sedu_maxmarks'   		=>	$this->input->post('stu_gmmarks'),
						'sedu_percentage'   	=>	$this->input->post('stu_gpercentage')
						//'sedu_percentage'   	=>	$this->input->post['eduugc_net')
               				 );
					//print_r($Gedu);
					if(!empty($stu_gran)){
						$this->commodel->insertrec('student_education', $stu_Gedu);
					}

					$stu_pron =$this->input->post('stu_psubject');
					$stu_Pgedu = array(
						'sedu_smid'				=>	$insertid,
						'sedu_class'   			=>	$this->input->post('stu_gcname'),
                		'sedu_subject'  		=>	$this->input->post('stu_gsubject'),
                		'sedu_board'  			=>	$this->input->post('stu_gboard'),
                		'sedu_passingyear'   	=>	$this->input->post('stu_gyear'),
						'sedu_resultstatus'   	=>	$this->input->post('stu_gpassed'),
						'sedu_institution'   	=>	$this->input->post('stu_ginstitute'),
						'sedu_marksobtained'    =>	$this->input->post('stu_gmobtain'),
						'sedu_maxmarks'   		=>	$this->input->post('stu_gmmarks'),
						'sedu_percentage'   	=>	$this->input->post('stu_gpercentage')
						//'asedu_percentage'   	=>	$this->input->post('eduugc_net')
                			);
					//print_r($Pgedu);
					if(!empty($stu_pron)){
						$this->commodel->insertrec('student_education', $stu_Pgedu);
					}

					$stu_anyn =$this->input->post('stu_asubject');
					$stu_Aedu = array(
						'sedu_smid'				=>	$insertid,
						'sedu_class'   			=>	$this->input->post('stu_gcname'),
                		'sedu_subject'  		=>	$this->input->post('stu_gsubject'),
                		'sedu_board'  			=>	$this->input->post('stu_gboard'),
                		'sedu_passingyear'   	=>	$this->input->post('stu_gyear'),
						'sedu_resultstatus'   	=>	$this->input->post('stu_gpassed'),
						'sedu_institution'   	=>	$this->input->post('stu_ginstitute'),
						'sedu_marksobtained' 	=>	$this->input->post('stu_gmobtain'),
						'sedu_maxmarks'   		=>	$this->input->post('stu_gmmarks'),
						'sedu_percentage'   	=>	$this->input->post('stu_gpercentage')
						//'asedu_percentage'   	=>	$this->input->post('eduugc_net')
              			  	);
					//print_r($Aedu);
					if(!empty($stu_anyn)){
						$this->commodel->insertrec('student_education', $stu_Aedu);
					}
	
					//insert into student enterence exam
					$stu_enterence = array(
						'seex_smid'		=>	$insertid,
						'seex_rollno'		=>	$this->input->post('stu_addrollno'),
						//'seex_examname'   	=>	$_POST[''],
                				//'spar_paddress'  	=>	$_POST['Spaddress'],
                				//'spar_pcity'  	=>	$_POST['Scity'],
                				//'spar_pstate'   	=>	$_POST['Sstate'],
						//'spar_pcountry'   	=>	$_POST['Scountry'],
						//'spar_ppincode'   	=>	$_POST['Spincode']
			
                				);
					$this->commodel->insertrec('student_entrance_exam',$stu_enterence);

					$cacadyer = $this->user_model->getcurrentAcadYear();
					//insert into student program
					$cdate = date("Y-m-d");
					$sem=1;
					//$stu_prgcatname = $this->input->post['stu_addprgcate'];
					//$stu_prgcatid = $this->commodel->get_listspfic1("programcategory","prgcat_id","prgcat_name",$stu_prgcatname)->prgcat_id;
					
					$stu_stuprog = array(
						'sp_smid'			=>	$insertid,
						'sp_sccode'			=>	$this->input->post('stu_addcenter'),
						//'sp_pcategory'   	=>	$stu_prgcatid,
						'sp_pcategory'   	=>	$this->input->post('stu_addprgcate'),
			            'sp_programid'  	=>	$this->input->post('stu_addcourse'),
			            'sp_acadyear'  		=>	$cacadyer,
						'sp_semester'  		=>	$sem,
						'sp_semregdate'		=>	$cdate,
						'sp_deptid'			=> 	$this->input->post('stu_adddepart'),
						'sp_branch'			=> 	$this->input->post('stu_addcourse')
			                );
					
					$this->db->insert('student_program', $stu_stuprog);
					$getprogid = $this->db->insert_id();
					
					//insert into student fees
					$stu_fees = array(
						'sfee_smid'		=>	$insertid,
						'sfee_spid'		=>	$getprogid
                				);	
					$this->commodel->insertrec('student_fees',$stu_fees);
	
					//insert into student_admissionstatus
					$stud_status = array(
						'sas_hallticketno'	=>	$number,
						'sas_studentmasterid'	=>	$insertid,
                				);
					$this->commodel->insertrec('student_admissionstatus',$stud_status);
					
					/*$ameritid = $this->commodel->get_listspfic1("admissionmeritlist","id","entexamrollno",$number)->id;
					$ameritexname = $this->commodel->get_listspfic1("admissionmeritlist","entexamname","entexamrollno",$number)->entexamname;
					$ameritrollno = $this->commodel->get_listspfic1("admissionmeritlist","entexamrollno","entexamrollno",$number)->entexamrollno;*/
					$ameritid = $this->commodel->get_listspfic1("admissionmeritlist","id","jeeapplication_no",$number)->id;
					$ameritexname = $this->commodel->get_listspfic1("admissionmeritlist","entexamname","jeeapplication_no",$number)->entexamname;
					$ameritrollno = $this->commodel->get_listspfic1("admissionmeritlist","entexamrollno","jeeapplication_no",$number)->entexamrollno;
					$ameritno = $this->commodel->get_listspfic1("admissionmeritlist","meritlist_no","jeeapplication_no",$number)->meritlist_no;
				//	$ameritno = $this->commodel->get_listspfic1("admissionmeritlist","meritlist_no","entexamrollno",$number)->meritlist_no;	
					//print_r($ameritid.' '.$ameritexname.' '.$ameritrollno.' '.$ameritno);die;
					//insert  into student entry exit
						$stu_entry = array(
							'senex_prgid'				=>	$this->input->post('stu_addcourse'),
							'senex_smid'				=>	$insertid,
							'senex_entexamapplicationno'=>	$this->input->post('stu_addrollno'),
							'senex_entexamname'			=>	$ameritexname,
							'senex_entexamrollno'		=>	$ameritrollno,
							'senex_entexamrank'			=>  $ameritid,
							'senex_entexammeritno'		=>	$ameritno
						 );
					$this->commodel->insertrec('student_entry_exit',$stu_entry);
					$this->logger->write_logmessage("insert", "New student record insert in student entry exit table.".$_POST['stu_addrollno']);
                    			$this->logger->write_dblogmessage("insert", "New student record insert in student entry exit table.".$_POST['stu_addrollno']);				

					//insert into admission step
					$no=1;
					$cdate = date('Y-m-d H:i:s');
					$stu_admission = array(
						'application_no'	=>	$this->input->post('stu_addrollno'),
						'student_masterid'	=>	$insertid,
						'step1_status'   	=>	$no,
                				'step1_date'  		=>	$cdate
                	
                			);
					$stu_step1 = $this->commodel->insertrec('student_admissionstep', $stu_admission);
					$session_studata = array(
						'sm_id' => $insertid,
					);
					$this->session->set_userdata($session_studata);
					if(!$stu_step1)
					{
                   				$this->logger->write_logmessage("insert", "New Student admission personnel , family & education record not add successfully.".$_POST['stu_addrollno']);
                    				$this->logger->write_dblogmessage("insert", "New Student admission personnel , family & education record not add successfully.".$_POST['stu_addrollno'] );
                   	 			$this->session->set_flashdata("err_message",'Error in adding admission details');
						redirect('student/student_admissionform');
                			}
                			else{
                    				$this->logger->write_logmessage("insert","New Student admission personnel , family & education record add successfully.".$_POST['stu_addrollno']);
                    				$this->logger->write_dblogmessage("insert", "Student admission personnel , family & education record add successfully.".$_POST['stu_addrollno']);
                   				$this->session->set_flashdata("success", "Your admission details has been  added successfully.");
						redirect('student/student_step2');
                   		// redirect('student/student_step3');
                			}
				}//validation if close
				
		}//post if close


	$this->load->view('student/student_step1form',$data);
	}//check admission last date if close
	else
	{	$this->session->set_flashdata("err_message",'Your last date of admission is over so contact your administration department');
		redirect('welcome');
	}
	}

 public function student_step1(){
		$array_items = array('success' => '', 'error' => '', 'warning' =>'');
       		$this->session->set_flashdata($array_items);
		
		$number = $this->session->userdata['app_no'];
		$data['number']	= $number; 
		 //print_r($number);echo "step 1";die;
		//$whdata = array( 'asm_applicationno' => $number );
                $resultap = $this->commodel->isduplicate('admissionstudent_master','asm_applicationno', $number);
		
                if (!$resultap) {
			//print_r($resultap.','.$number);die;
                        $this->session->set_flashdata('err_message', 'Your data does not exist, So you contact to administrator or department.');
                        redirect('welcome');
                }

		$data1 = array('step1_status' => '1', 'application_no' => $number);
		$recheckstustep_exist1 = $this->commodel->isduplicatemore('student_admissionstep',$data1);
		if($recheckstustep_exist1){
			redirect('student/student_step2');
		}

		$smid = $this->commodel->get_listspfic1("admissionstudent_master","asm_id","asm_applicationno",$number)->asm_id;
		$stu_data = $this->commodel->get_listrow('admissionstudent_master','asm_id',$smid)->row();
		if(!empty($stu_data)) {
			$name = $stu_data->asm_fname;
			$data['name']=$name;
			$dob = $stu_data->asm_dob;
			$data['dob'] = $dob;
			$progid = $stu_data->asm_coursename;
			$data['progid'] = $progid;
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$progid)->prg_name;
			$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$progid)->prg_branch;
			$prgcat = $this->commodel->get_listspfic1('program','prg_category','prg_id',$progid)->prg_category;
			$data['prgcat'] = $prgcat;
			$prgcatid = $this->commodel->get_listspfic1('programcategory','prgcat_id','prgcat_name',$prgcat)->prgcat_id;
			$data['prgcatid'] = $prgcatid;
			$data['prgname'] = $prgname;
			$data['prgbranch'] = $prgbranch;
			$gender = $stu_data->asm_gender;
			$data['gender'] = $gender;
			$mobile = $stu_data->asm_mobile;
			$data['mobile'] = $mobile;
			$email = $stu_data->asm_email;
			$data['email'] = $email;
			$categoryid = $stu_data->asm_caste;
			$data['categoryid'] = $categoryid;
			$category = $this->commodel->get_listspfic1('category','cat_name','cat_id',$categoryid)->cat_name;
			$data['category'] = $category;
			$rollno = $stu_data->asm_applicationno;
			$data['rollno'] = $rollno;
			$sccode = $stu_data->asm_sccode;
			$data['sccode'] = $sccode;
			$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$sccode)->sc_name;
			$data['scname'] = $scname;
			$excode = $stu_data->asm_enterenceexamcenter;
			$exname =  $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$excode)->eec_name;	
			$data['exname'] = $exname;
			
			$age = $stu_data->asm_age;
			$data['age'] = $age;
			$mastatus = $stu_data->asm_mstatus;
			$data['mastatus'] = $mastatus;
			$nationality = $stu_data->asm_nationality;
			$data['nationality'] = $nationality;
			$phyhandi = $stu_data->asm_phyhandicaped;
			$data['phyhandi'] = $phyhandi;
			$religion = $stu_data->asm_religion;
			$data['religion'] = $religion;
			$reservation = $stu_data->asm_reservationtype;
			$data['reservation'] = $reservation;
			$aadhar = $stu_data->asm_aadharno;
			$data['aadhar'] = $aadhar;
			

		}
		$studparent_admission = $this->commodel->get_listrow('admissionstudent_parent','aspar_asmid',$smid)->row();
		
		if(!empty($studparent_admission)){
			$mname = $studparent_admission->aspar_mothername;
			$data['mname'] = $mname;
			$fname =  $studparent_admission->aspar_fathername;
			$data['fname'] = $fname;
			
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
			
		}

		$studedu = $this->commodel->get_listrow('admissionstudent_education','asedu_asmid',$smid)->result();
		$data['studedu'] = $studedu;
		$depresult = $this->commodel->get_list('Department');
		$data['depresult'] = $depresult;	
	//	print_r($studedu);
		/*if(!empty($studedu_admission)){
			$class = $studedu_admission->asedu_class;
			$data['class'] = $class;
			$institute =  $studedu_admission->asedu_institution;
			$data['institute'] = $institute;
			
			$board = $studedu_admission->asedu_board;
			$data['board'] = $board;
			$subject = $studedu_admission->asedu_subject;
			$data['subject']=$subject;
			$pyear = $studedu_admission->asedu_passingyear;
			$data['pyear'] = $pyear;
			$restatus = $studedu_admission->asedu_resultstatus;
			$data['restatus'] = $restatus;
			
		}*/
		
	
		/*$this->name=$this->commodel->get_listspfic1("admissionmeritlist","student_name","application_no",$this->number)->student_name;
		$this->scresult = $this->commodel->get_list('study_center');
		$this->prgname=$this->commodel->get_list("programcategory");
		$this->couname=$this->commodel->get_listspfic1("admissionmeritlist","course_name","application_no",$this->number)->course_name;
		$this->progname=$this->commodel->get_listspfic1("admissionmeritlist","branchname","application_no",$this->number)->branchname;
		$sarray='prg_id';	
		$wharray = array('prg_name' => $this->couname, 'prg_branch' => $this->progname);
    		$resultprg=$this->commodel->get_listarry("program",$sarray,$wharray);
		foreach($resultprg as $dataprg){
			$this->categid=$dataprg->prg_id;
		}
		$this->fathname=$this->commodel->get_listspfic1("admissionmeritlist","father_name","application_no",$this->number)->father_name;
		$this->scatresult = $this->commodel->get_list('category');
		
		$this->semail=$this->commodel->get_listspfic1("admissionmeritlist","student_email","application_no",$this->number)->student_email;*/


		//$ldate = $this->commodel->get_listspfic1('admissionmeritlist','lastdate_admission','entexamrollno',$number)->lastdate_admission;
		$ldate = $this->commodel->get_listspfic1('admissionmeritlist','lastdate_admission','application_no',$number)->lastdate_admission;
		//print_r($ldate);die;
		$admidate = $this->datmodel->comparedate($ldate);
	//	if($admidate == $cdate)
		if($admidate)
		{
         	 if(isset($_POST['addstudent'])) {

            $this->form_validation->set_rules('Sanumber','Application no.','trim|xss_clean|numeric');
            $this->form_validation->set_rules('Sname','Applicant name','trim|xss_clean');
            $this->form_validation->set_rules('Sgender','gender','trim|xss_clean|required');
            $this->form_validation->set_rules('Sdob','date of birth','trim|xss_clean|required');
	    	$this->form_validation->set_rules('Saadharnumber','aadhar number','trim|xss_clean|is_numeric|max_length[12]|callback_aadharexist');
            $this->form_validation->set_rules('Sabgroup','blood group','trim|xss_clean');
           	$this->form_validation->set_rules('Sreligion','religion','trim|xss_clean|required');
            $this->form_validation->set_rules('Smobile','mobile number','trim|xss_clean|is_numeric|max_length[12]|required');
	    	$this->form_validation->set_rules('Semail','email-id','trim|xss_clean|required|valid_email');
			$this->form_validation->set_rules('Snameprogramme','Program/Course name','trim|xss_clean');
			$this->form_validation->set_rules('Scenters','Study center','trim|xss_clean|required');
          	$this->form_validation->set_rules('Stypeprogramme','Program/Course type','trim|xss_clean|required');
			$this->form_validation->set_rules('Sdepart','Departmnet not select','trim|xss_clean');

			$this->form_validation->set_rules('Smothername','Mother name','trim|xss_clean|required');
          	$this->form_validation->set_rules('Sfathername','Father name','trim|xss_clean|required');
           	$this->form_validation->set_rules('Scategory','Category','trim|xss_clean|required');
           	$this->form_validation->set_rules('Saddress','Postal address','trim|xss_clean|required');
			//$this->form_validation->set_rules('Sdist','District','trim|xss_clean|required');
			//$this->form_validation->set_rules('Spost','Post office','trim|xss_clean|required');
	   		$this->form_validation->set_rules('Scity','City','trim|xss_clean|required');
			$this->form_validation->set_rules('Sstate','State','trim|xss_clean|required');
          	$this->form_validation->set_rules('Scountry','Country','trim|xss_clean|required');
           	$this->form_validation->set_rules('Spincode','Pincode','trim|xss_clean|required|is_numeric');

			/*$this->form_validation->set_rules('classname','Class Name','trim|xss_clean|required');
          		$this->form_validation->set_rules('institutename','Institute Name','trim|xss_clean|required');
           		$this->form_validation->set_rules('board','Board','trim|xss_clean|required');
           		$this->form_validation->set_rules('subject','Subject','trim|xss_clean|required');
	   		$this->form_validation->set_rules('passingyear','Passed/Appeared','trim|xss_clean|required');
			$this->form_validation->set_rules('status','Status','trim|xss_clean|required');
	   		$this->form_validation->set_rules('maxmarks','Max Marks','trim|xss_clean|required');
			$this->form_validation->set_rules('marksobtained','Marks Obtained','trim|xss_clean|required');

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
	   		$this->form_validation->set_rules('Apassed','Any other qualificatin passed/appeared','trim|xss_clean');*/
		
		//For Repopulating data
		
           	if($this->form_validation->run() == FALSE){
				//$message = '<h3>Your some detail is incorrect .</h3>';
	  			//$this->session->set_flashdata('msg',$message);
				//$this->load->view('student/student_step1');
		}
		else{
	    	// get the current academic year
			$cacadyer = $this->user_model->getcurrentAcadYear();
		//	 $protype = $this->input->post['Snameprogramme'];
			//print_r($protype);
		// get the program id
		//	$prgid = $this->commodel->get_listspfic1('program','prg_id','prg_category',$protype);
			//print_r($prgid);
		// get the study code 
//			$studycentr = $this->input->post['Scenters'];
//			$sccode = $this->commodel->get_listspfic1('study_center','sc_code','sc_name',$studycentr);
			//print_r($sccodie);
		//get the number of rows in student master table
		$seqno = $this->commodel->getnoofrows('student_master');
		//get the code of studyceneter
		$scno = $_POST['Scenters'];
		// generate the enrollnomber
		//$enroollno = date("Y").$scno.$seqno;
			
		//insert into student master

		$data = array(
					'sm_sccode'			=>	$this->input->post('Scenters'),
                	'sm_fname'  		=>	$this->input->post('Sname'),
                	'sm_dob'   			=>	$this->input->post('Sdob'),
                	'sm_email'   		=>	$this->input->post('Semail'),
                	'sm_mobile'   		=>	$this->input->post('Smobile'),
                	'sm_category'		=>	$this->input->post('Scategory'),
                	'sm_gender'  		=>	$this->input->post('Sgender'),
                	'sm_bloodgroup'   	=>	$this->input->post('Sabgroup'),
					'sm_uid'   			=>	$this->input->post('Saadharnumber'),
                	'sm_religion'  		=>	$this->input->post('Sreligion'),
					//'sm_enrollmentno'	=>	$enroollno
                );
	 	//start the transaction
       	 	$this->db->trans_begin();
		$this->db->insert('student_master', $data);	
		$insertid = $this->db->insert_id();
		$this->logger->write_logmessage("insert", "Aadhar changed by :".$_POST['Semail']."student and aadhar number is".$_POST['Saadharnumber']);
                $this->logger->write_logmessage("insert", "Aadhar changed by :".$_POST['Semail']."student and aadhar number is".$_POST['Saadharnumber']);
		//print_r($insertid);			
	
		//insert into student parent
		$parent = array(
			'spar_smid'				=>	$insertid,
			'spar_mothername'		=>	$this->input->post('Smothername'),
			'spar_fathername'   	=>	$this->input->post('Sfathername'),
            'spar_paddress'  		=>	$this->input->post('Saddress'),
			//'spar_pdistrict'  	=>	$this->input->post('Sdist'),
			//'spar_ppostoffice'  	=>	$this->input->post('Spost'),
            'spar_pcity'  		=>	$this->input->post('Scity'),
           	'spar_pstate'   	=>	$this->input->post('Sstate'),
			'spar_pcountry'   	=>	$this->input->post('Scountry'),
			'spar_ppincode'   	=>	$this->input->post('Spincode'),
			'spar_caddress'  	=>	$this->input->post('Saddress'),
			//'spar_cdistrict'  	=>	$this->input->post('Sdist'),
			//'spar_cpostoffice'  	=>	$this->input->post('Spost'),
          	'spar_ccity'  		=>	$this->input->post('Scity'),
           	'spar_cstate'   	=>	$this->input->post('Sstate'),
			'spar_ccountry'   	=>	$this->input->post('Scountry'),
			'spar_cpincode'   	=>	$this->input->post('Spincode')
                	
                );
		//print_r($parent);		
		$this->db->insert('student_parent', $parent);
		
		//insert into student education
		
		for($i=1; $i<=5; $i++){
			if(!empty($_POST['classname'.$i.'1'])){
			$edu = array(
				'sedu_smid'		=>	$insertid,
                'sedu_class'   		=>	$this->input->post('classname'.$i.'1'),
				'sedu_institution'   	=>	$this->input->post('institutename'.$i.'2'),
				'sedu_board'  		=>	$this->input->post('board'.$i.'3'),
                'sedu_subject'  	=>	$this->input->post('subject'.$i.'4'),
                'sedu_passingyear'   	=>	$this->input->post('passingyear'.$i.'5'),
				'sedu_resultstatus'   	=>	$this->input->post('status'.$i.'6'),
				'sedu_maxmarks'   	=>	$this->input->post('maxmarks'.$i.'7'),
				'sedu_marksobtained'   	=>	$this->input->post('marksobtained'.$i.'8'),
				'sedu_percentage'   	=>	$this->input->post('percentage'.$i.'9'),
               	 	);
		
			
				$this->db->insert('student_education', $edu);
			}
		 
		} 
	/*	$intn =$_POST['Icname'];
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
		}*/

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
		$cdate = date("Y-m-d");
		$sem=1;
		$dept = $_POST['Sdepart'];
		$stuprog = array(
			'sp_smid'			=>	$insertid,
			'sp_sccode'			=>	$_POST['Scenters'],
			'sp_pcategory'   	=>	$_POST['Stypeprogramme'],
            'sp_programid'  	=>	$_POST['Snameprogramme'],
            'sp_acadyear'  		=>	$cacadyer,
			'sp_semester'  		=>	$sem,
			'sp_semregdate'		=>	$cdate,
			'sp_deptid'			=> 	$dept,
			'sp_branch'			=> 	$_POST['Snameprogramme']
                );
		
			$this->db->insert('student_program', $stuprog);
			$getprogid = $this->db->insert_id();
		
		//insert into student fees
		$stufees = array(
			'sfee_smid'		=>	$insertid,
			'sfee_spid'		=>	$getprogid
                );	
		$this->db->insert('student_fees',$stufees);

		//insert into student_admissionstatus
		$stu_status = array(
			'sas_hallticketno'	=>	$number,
			'sas_studentmasterid'	=>	$insertid,
                );
		$this->db->insert('student_admissionstatus',$stu_status);

		//insert into admission step
		$no=1;
		$cdate = date('Y-m-d H:i:s');
		$stuadmission = array(
			'application_no'	=>	$_POST['Sanumber'],
			'student_masterid'	=>	$insertid,
			'step1_status'   	=>	$no,
                	'step1_date'  		=>	$cdate
                	
                );
		$this->db->insert('student_admissionstep', $stuadmission);
		
		/*$ameritid = $this->commodel->get_listspfic1("admissionmeritlist","id","entexamrollno",$number)->id;
		$ameritexname = $this->commodel->get_listspfic1("admissionmeritlist","entexamname","entexamrollno",$number)->entexamname;
		$ameritrollno = $this->commodel->get_listspfic1("admissionmeritlist","entexamrollno","entexamrollno",$number)->entexamrollno;
		$ameritno = $this->commodel->get_listspfic1("admissionmeritlist","meritlist_no","entexamrollno",$number)->meritlist_no;	*/
		$ameritid = $this->commodel->get_listspfic1("admissionmeritlist","id","jeeapplication_no",$number)->id;
		$ameritexname = $this->commodel->get_listspfic1("admissionmeritlist","entexamname","application_no",$number)->entexamname;
		$ameritrollno = $this->commodel->get_listspfic1("admissionmeritlist","entexamrollno","application_no",$number)->entexamrollno;
		$ameritno = $this->commodel->get_listspfic1("admissionmeritlist","meritlist_no","jeeapplication_no",$number)->meritlist_no;
		//print_r($ameritid.' '.$ameritexname.' '.$ameritrollno.' '.$ameritno);die;
		//insert  into student entry exit
		$stuentry = array(
				'senex_prgid'			=>	$_POST['Snameprogramme'],
				'senex_smid'			=>	$insertid,
				'senex_entexamapplicationno'   	=>	$_POST['Sanumber'],
				'senex_entexamname'		=>	$ameritexname,
				'senex_entexamrollno'		=>	$ameritrollno,
				'senex_entexamrank'		=>      $ameritid,
				'senex_entexammeritno'		=>	$ameritno
				 );
		$this->db->insert('student_entry_exit',$stuentry);

			//$result1 = $this->stumodel->studentId($insertid);
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
              //redirect('student/student_step3');
           		 //return TRUE;
       			 }

		}/*close else*/

	}/*Close if post */
	$this->load->view('student/student_step1',$data);
	}//check admission last date
		else
		{	$this->session->set_flashdata("err_message",'Your last date of admission is over so contact your administration department');
			//$this->session->set_flashdata('flash_data', 'Your admission last date is over so contact your administrator department');
			redirect('welcome');
		}
   }/*close function step1*/

	public function student_step2(){
		
		//$array_items = array('success' => '', 'error' => '', 'warning' =>'');
        	//$this->session->set_flashdata($array_items);
		$insertid=$this->session->userdata['sm_id'];
		//print_r($insertid);die;
		$email = $this->commodel->get_listspfic1("student_master","sm_email","sm_id",$insertid)->sm_email;
		$data['email'] = $email;

		$rsdata = array('step1_status' => '1', 'student_masterid' => $insertid);
		$recheckstep_exist = $this->commodel->isduplicatemore('student_admissionstep',$rsdata);
		//print_r($recheckstep_exist);die;
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('welcome');	
		}

		$rsdata1 = array('step2_status' => '1', 'student_masterid' => $insertid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('student_admissionstep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('student/student_step3');
		}
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
				//if yes then update student_admissionstep table
				//start the transaction
       	// 			$this->db->trans_begin();
				$cdate = date('Y-m-d H:i:s');
				//$id= $this->input->get_post('Sid');	
				$step2 = array(
					'step2_status'	       =>		 1,
					'step2_date'	       =>		 $cdate,
				);
				$updatst2 = $this->commodel->updaterec('student_admissionstep', $step2,'student_masterid',$insertid);
				//$this->db->where('student_masterid',$insertid);
				//$this->db->update('student_admissionstep', $step2);		
				//set flag for each step, if any step fails revert all steps and return to same step
				//make transaction complete
        //			$this->db->trans_complete();
			
	 			//check if transaction status TRUE or FALSE
        			//if ($this->db->trans_status() === FALSE) {
        			if ($updatst2 === FALSE) {
            				//if something went wrong, rollback everything
            		//		$this->db->trans_rollback();
					//else stay with step2 or redirect to login page
					$this->logger->write_logmessage("update", "Error in step2 to update admission step");
                    			$this->logger->write_dblogmessage("update", "Error in step2 to update admission step" );
					redirect('student/student_step2');
           				//return FALSE;
      				} else {
            				//if everything went right, commit the data to the database
           		 //		$this->db->trans_commit();
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
		$this->load->view('student/student_step2',$data);	
	}// close function syudent_step2

	public function student_step3(){
		$id = $this->session->userdata['sm_id'];
		$email = $this->commodel->get_listspfic1("student_master","sm_email","sm_id",$id)->sm_email;
		$data['email'] = $email;
		//upload photo and signature in dir which is not accessble directly with size limit photo 200kb and signature 20kb
		// for clearing the previous sucess/error flashdata
		//$array_items = array('success' => '', 'error' => '', 'warning' =>'');
        	//$this->session->set_flashdata($array_items);
		/*$rsdata = array('step2_status' => '1', 'student_masterid' => $id);
		$recheckstep_exist = $this->commodel->isduplicatemore('student_admissionstep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('welcome');	
		}*/

		$rsdata1 = array('step3_status' => '1', 'student_masterid' => $id);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('student_admissionstep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'Your back step missed.');
			redirect('student/student_step4');
		}
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
				
				if($_FILES['userfile']['size'] > 2000) {
         				$errors[]='Photo size must be excately 2 MB';
					
      				}

				if($_FILES['userfile2']['size'] > 1000) {
         				$errors[]='Signature size must be excately 1 MB';
      				}

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
				//print_r($name2.' '.$name);die;	
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
				
        	     		//$this->db->where('sm_id', $id);
		    		//$update = $this->db->update('student_master',$userData);
           		$updst = $this->commodel->updaterec('student_master',$userData,'sm_id', $id);
				$this->logger->write_logmessage("update", "Step 3 student_master table upload file updated.");
                $this->logger->write_dblogmessage("update", "Step 3 student_master table upload file updated." );
				
        	    		//Storing insertion status message. update student_admissionstep table
				
				//$cdate = date('Y-m-d H:i:s');				
				//$step3 = array(
				//	'step3_status'	       =>		 1,
					//'step3_date'	       =>		 $cdate
				//);
				//$this->db->where('student_masterid',$id);
				//$this->db->update('student_admissionstep', $step3);
				//$updst3 = $this->commodel->updaterec('student_admissionstep', $step3,'student_masterid',$id);

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
					$error =  array('err_message' => $this->upload->display_errors());
					//print_r($error);die;
					foreach ($error as $item => $value):
						$ferror = $ferror.$value;
					endforeach;
					$ferror=str_replace("\r\n","",$ferror);
					$simsg = "The permitted size of Photo is 200kb and Signature is 20kb";
					$ferror = $simsg.$ferror;
					$this->logger->write_logmessage("upload","step 3 File upload error", $ferror);
					$this->logger->write_dblogmessage("upload","step 3 File upload error", $ferror);
					$this->session->set_flashdata('err_message', $ferror);
					redirect('student/student_step3');
                		}
            		

				//Load upload library and initialize configuration code for sign
        	       	 	$this->load->library('upload',$config2);
        	       		$this->upload->initialize($config2);
                
        	        	if($this->upload->do_upload('userfile2')){
        	           		$uploadData = $this->upload->data();
        	           		$picture = $uploadData['file_name'];
				//	$this->logger->write_logmessage("update","File updated", "Step 3 file update");
				//	$this->logger->write_dblogmessage("update","File updated", "Step 3 file update");
				//	$this->session->set_flashdata('success', 'Your photo and signature successfully uploaded.');
				//	redirect('student/student_step4');
        	        	}else{
        	            		$picture = '';
					$error =  array('err_message' => $this->upload->display_errors());
					foreach ($error as $item => $value):
						$ferror = $ferror.$value;
					endforeach;
					$simsg = "The permitted size of Photo is 200kb and signature is 20kb";
					$ferror = $simsg.$ferror;
					$this->logger->write_logmessage("upload","step 3 File upload error", $ferror);
					$this->logger->write_dblogmessage("upload"," step 3 File upload error", $ferror);
					$this->session->set_flashdata('err_message', $ferror);
					redirect('student/student_step3');
        	        	}
            	
       			 //update student master
        	       $cdate = date('Y-m-d H:i:s');				
				$step3 = array(
					'step3_status'	       =>		 1,
					'step3_date'	       =>		 $cdate
				);
								$this->db->where('student_masterid',$id);
				$stp3update = $this->db->update('student_admissionstep', $step3);
				if ($stp3update)
                	        {
                                	$this->logger->write_logmessage("update","File updated", "Step 3 file update");
					$this->logger->write_dblogmessage("update","File updated", "Step 3 file update");
					$this->session->set_flashdata('success', 'Your photo and signature successfully uploaded.');
					//redirect('student/student_step4');
					redirect('student/student_checklist');
                        	}
                        	else{
                                	$this->logger->write_logmessage("upload","step 3 File upload error");
					$this->logger->write_dblogmessage("upload"," step 3 File upload error");
					$this->session->set_flashdata('err_message', 'File Not Uploaded Successfully');
					redirect('student/student_step3');
                        	}
            	
       			 	//Form for adding user data
			   }
     		    }
			$this->load->view('student/student_step3',$data);	
    	}

    public function student_checklist(){
		$Sid = $this->session->userdata['sm_id'];
		$mailid = $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Sid)->sm_email;
		$data['email'] = $mailid;
		$name=$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		$data['name'] = $name;

		if(isset($_POST['stu_doclist'])) {
			// add insert code	
			$this->form_validation->set_rules('cb1o',"JEE main Score Card",'trim|xss_clean'); 
			if($this->form_validation->run()==TRUE){
				$dcb1=$this->input->post('cb1o',TRUE);
			//	if checked then execute insert query

			}
			redirect('student/student_step4');
		}		
		
		$this->load->view('student/student_checklist',$data);
	}
	
	public function student_step4(){
		$Sid = $this->session->userdata['sm_id'];
		$mailid = $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Sid)->sm_email;
		
		$rsdata = array('step3_status' => '1', 'student_masterid' => $Sid);
		$recheckstep_exist = $this->commodel->isduplicatemore('student_admissionstep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('welcome');	
		}

		$rsdata1 = array('step5_status' => '1', 'student_masterid' => $Sid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('student_admissionstep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'Your back step missed.');
			redirect('student/student_step5');
		}

		//$ano = $this->session->userdata['app_no'];
		//$this->coursename=$this->commodel->get_listspfic1('admissionmeritlist','course_name','application_no',$ano)->course_name;
		$this->appno=$this->commodel->get_listspfic1('student_entry_exit','senex_entexamapplicationno','senex_smid',$Sid)->senex_entexamapplicationno;
		$name=$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		$this->fname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Sid)->spar_fathername;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Sid)->sm_gender;
		//$this->acadyear = $this->user_model->getcurrentAcadYearfadm();
		$this->acadyear = $this->commodel->get_listspfic1('student_program','sp_acadyear','sp_smid',$Sid)->sp_acadyear;
		//print_r($semesterrec);
		//$this->prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;
		$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;
		//print_r($prgid);die;
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
		$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
		$pinfo = $prgname.'('.$prgbranch.')';

		$sarray='prg_name,prg_branch';	
		$wharray = array('prg_id' => $prgid);
    		$this->resultprg=$this->commodel->get_listarry("program",$sarray,$wharray);

		$this->catid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Sid)->sm_category;
		//print_r($this->catid);
		// in future we add acdamic year
		//$wharray = array('fm_programid' => $this->prgid,('fm_gender' => (All)||($this->gender))&&('fm_category'=>(All)||($this->catid)));
		// display fees detail on the basis of gender, category and program with semester
		//$this->feesresult =  $this->commodel->get_listspficemore('fees_master','fm_programid','fm_amount')->;
		//print_r($this->feesresult);
		//die();
		// in future this will be replaced by the model.
		$wharray = array('fm_programid' => $prgid, 'fm_semester' => 1);
		$sarray = 'fm_head,fm_amount';
		$wgenr = array('All', $this->gender);
		$wcateid = array('1', $this->catid);
		$this->db->select($sarray);
		$this->db->from('fees_master');
		$this->db->where_in('fm_gender',$wgenr);
		$this->db->where_in('fm_category',$wcateid);
		$this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
		$amount = 0;
		//print_r($this->feesresult);
		
			foreach($this->feesresult as $row){
		 		$row->fm_head;
		 		$row->fm_amount;
				$amount = $amount+$row->fm_amount;
			}
			//echo $amount;
			//die();
		$phoneno = $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Sid)->sm_mobile;
		$ftype = 'Semester Fees';

		$MERCHANT_KEY	= MERCHANT_KEY;
		$SALT =	SALT;
		$txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
		
       		//optional udf values 
        	$udf1 = '';
        	$udf2 = '';
        	$udf3 = '';
        	$udf4 = '';
        	$udf5 = '';

		$hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		
		$hashstring = $MERCHANT_KEY . '|' . $txnid . '|' . $amount . '|' . $pinfo . '|' . $name . '|' . $mailid . '|' . $udf1 . '|' . $udf2 . '|' . $udf3 . '|' . $udf4 . '|' . $udf5 . '|'.''.'|'.''.'|'.''.'|'.''.'|'.''.'|' . $SALT;
         	$hash = strtolower(hash('sha512', $hashstring));
		$success = site_url() .'/student/payustatus'; //return to payustatus function   
        	$fail    = site_url() . '/student/payustatus'; //return to payustatus function
       	 	//$cancel  = base_url() . 'payumoney/payustatus';//return to payustatus function
		//for live change action  https://secure.payu.in)
				
		$data = array(
            		'mkey' => $MERCHANT_KEY,
            		'tid' => $txnid,
            		'hash' => $hash,
            		'amount' => $amount,  
					'productinfo' => $pinfo,         
            		'name' => $name,
            		'mailid' => $mailid,
           			'phoneno' => $phoneno,
           			'address' => $ftype,
			//'action' => "https://test.payu.in", //for live change action  https://secure.payu.in
				'action' => PAYU_BASE_URL,
           		'surl' => $success,
           		'furl' => $fail,
            		   
        	);
		//insert record in pg table
		$pgdata = array(
			'aspg_asmid' 	=> $Sid,	
			'aspg_txnid' 	=> $txnid,	
			'aspg_pinfo' 	=> $pinfo,	
			'aspg_amount' 	=> $amount,	
			'aspg_ftype'	=> $ftype,	
			//'aspg_date' 	=> ,	
			//'aspg_gw'	=> ,	
			//'aspg_status'	=> ,	
			//'aspg_txncode' 	=> ,
			//'aspg_reason' 	=>	
		);
		$pginsert = $this->db->insert('admissionstudent_pg', $pgdata);
		$this->logger->write_logmessage("insert", "New student record insert in admissionstudent_pg table.");
                $this->logger->write_dblogmessage("insert", "New student insert in admissionstudent_pg table" );

		//print_r($wharray);
		//fees paid by online /offline
		//if online then redirect to payment gateway
		//else open a fees payment entry details page		
		//set flag for each step, if any step fails revert all steps and return to same step
		$this->load->view('student/student_step4',$data);
	}


	/*  public function student_step4(){
		
		$Sid = $this->session->userdata['sm_id'];
		$rsdata = array('step3_status' => '1', 'student_masterid' => $Sid);
		$recheckstep_exist = $this->commodel->isduplicatemore('student_admissionstep',$rsdata);
		if(!$recheckstep_exist){
			$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('welcome');	
		}

		$rsdata1 = array('step4_status' => '1', 'student_masterid' => $Sid);
		$recheckstep_exist1 = $this->commodel->isduplicatemore('student_admissionstep',$rsdata1);
		if($recheckstep_exist1){
			//$this->session->set_flashdata('err_message', 'You are not following proper process.');
			redirect('student/student_step5');
		}
		

		//online payment student enterence record get
		
		//get category name
		$catid = $this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Sid)->sm_category;
		$catname = $this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;
		$this->catname = $catname;
		if($this->catname == "General" || $this->catname == "OBC"){
			$amount='300.00';
		}
		if($this->catname == "SC" || $this->catname == "ST"){
			$amount='100.00';
		}
		$name = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		$mailid = $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Sid)->sm_email;
		$phoneno = $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Sid)->sm_mobile;
		
		$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
		$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
		$pinfo = $prgname.'('.$prgbranch.')';//.'('.$rollno.')';
		$gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Sid)->sm_gender;			
		$ftype = 'Semester Fees';

		 // display fees detail on the basis of gender, category and program with semester
  /*              $wharray = array('fm_programid' => $prgname, 'fm_semester' => 1);
                $sarray = 'fm_head,fm_amount';
                $wgenr = array('All', $gender);
                $wcateid = array('1', $catid);

		$this->db->select($sarray);
                $this->db->from('fees_master');
                $this->db->where_in('fm_gender',$wgenr);
                $this->db->where_in('fm_category',$wcateid);
                $this->db->where($wharray);
                $this->feesresult =  $this->db->get()->result();
		

		
		$MERCHANT_KEY	= MERCHANT_KEY;
		
		$SALT =	SALT;
		$txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
		
       		//optional udf values 
        	$udf1 = '';
        	$udf2 = '';
        	$udf3 = '';
        	$udf4 = '';
        	$udf5 = '';

		$hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		
		$hashstring = $MERCHANT_KEY . '|' . $txnid . '|' . $amount . '|' . $pinfo . '|' . $name . '|' . $mailid . '|' . $udf1 . '|' . $udf2 . '|' . $udf3 . '|' . $udf4 . '|' . $udf5 . '|'.''.'|'.''.'|'.''.'|'.''.'|'.''.'|' . $SALT;
         	$hash = strtolower(hash('sha512', $hashstring));
		$success = site_url() .'/student/payustatus'; //return to payustatus function   
        	$fail    = site_url() . '/student/payustatus'; //return to payustatus function
       	 	//$cancel  = base_url() . 'payumoney/payustatus';//return to payustatus function
		//for live change action  https://secure.payu.in)
				
		$data = array(
            		'mkey' => $MERCHANT_KEY,
            		'tid' => $txnid,
            		'hash' => $hash,
            		'amount' => $amount,  
			'productinfo' => $pinfo,         
            		'name' => $name,
            		'mailid' => $mailid,
           		'phoneno' => $phoneno,
           		'address' => $ftype,
			//'action' => "https://test.payu.in", //for live change action  https://secure.payu.in
			'action' => PAYU_BASE_URL,
           		'surl' => $success,
           		'furl' => $fail,
            		   
        	);
		//insert record in pg table
		$pgdata = array(
			'aspg_asmid' 	=> $Sid,	
			'aspg_txnid' 	=> $txnid,	
			'aspg_pinfo' 	=> $pinfo,	
			'aspg_amount' 	=> $amount,	
			'aspg_ftype'	=> $ftype,	
			//'aspg_date' 	=> ,	
			//'aspg_gw'	=> ,	
			//'aspg_status'	=> ,	
			//'aspg_txncode' 	=> ,
			//'aspg_reason' 	=>	
		);
		$pginsert = $this->db->insert('admissionstudent_pg', $pgdata);
		$this->logger->write_logmessage("insert", "New student record insert in admissionstudent_pg table.");
                $this->logger->write_dblogmessage("insert", "New student insert in admissionstudent_pg table" );
		
		$this->load->view('student/student_step4',$data);
	}*/

	public function payustatus() {
		$Sid = $this->session->userdata['sm_id'];
       		$status = $this->input->post('status');
      		if (empty($status)) {
            		redirect('student/student_step4');
        	}
       
        	$firstname = $this->input->post('firstname');
        	$amount = $this->input->post('amount');
        	$txnid = $this->input->post('txnid');
        	$posted_hash = $this->input->post('hash');
        	$key = $this->input->post('key');
        	$productinfo = $this->input->post('productinfo');
       		$email = $this->input->post('email');
		$ftype = $this->input->post('address1');
		$cdate = date('Y-m-d');	
        	//$SALT = "eCwWELxi";	 //  Your salt
		$SALT =	SALT;
        	$add = $this->input->post('additionalCharges');
        	if(isset($add)) {
            		$additionalCharges = $this->input->post('additionalCharges');
            		$retHashSeq = $additionalCharges . '|' . $SALT . '|' . $status . '|||||||||||' . $email . '|' . $firstname . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
        	} else {

            		$retHashSeq = $SALT . '|' . $status . '|||||||||||' . $email . '|' . $firstname . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
        	}
          	$data['hash'] = hash("sha512", $retHashSeq);
          	$data['amount'] = $amount;
          	$data['txnid'] = $txnid;
          	$data['posted_hash'] = $posted_hash;
          	$data['status'] = $status;
          	if($status == 'success'){
	 		// update pg table
			$pgdata = array(
				'aspg_asmid' 	=> $Sid,	
				'aspg_txnid' 	=> $txnid,	
				'aspg_pinfo' 	=> $productinfo,	
				'aspg_amount' 	=> $amount,	
				'aspg_ftype'	=> $ftype,	
				'aspg_date' 	=> $cdate,	
				//'aspg_gw'	=> ,	
				'aspg_status'	=> $status,	
				//'aspg_txncode' 	=> ,
				//'aspg_reason' 	=>	
			);
			$pgupdate=$this->commodel->insertrec('admissionstudent_pg', $pgdata,'aspg_asmid',$Sid);
			$this->logger->write_logmessage("update", "Update in admissionstudent_pg table.");
                	$this->logger->write_dblogmessage("update", "Update in admissionstudent_pg table." );
	 		//call payment function for update record
		 	$pmathod = 'Online';
		 	$bank='PayuMoney';
		 		
		 	$this->payment($txnid,$bank,$amount,$ftype,$pmathod);
			//set the proper message
			//$message = '<h3>Online fees submit successfully.</h3>';
                	//$this->session->set_flashdata('msg',$message);
                	//$this->load->view('enterence/step_five', $data);   //this should go to step five for printing application page  with suitable message
         	}
         	else{
			// update pg table
			$pgdata = array(
				'aspg_asmid' 	=> $Sid,	
				'aspg_txnid' 	=> $txnid,	
				'aspg_pinfo' 	=> $productinfo,	
				'aspg_amount' 	=> $amount,	
				'aspg_ftype'	=> $ftype,	
				'aspg_date' 	=> $cdate,	
				//'aspg_gw'	=> ,	
				'aspg_status'	=> $status,	
				//'aspg_txncode' 	=> ,
				//'aspg_reason' 	=>	
			);
			$pgupdate=$this->commodel->insertrec('admissionstudent_pg', $pgdata);
			$this->logger->write_logmessage("insert", "failed record insert in admissionstudent_pg table.");
                	$this->logger->write_dblogmessage("insert", "failed record insert in admissionstudent_pg table.");

			// set the status message
                	$this->load->view('student/student_step4', $data); //this should go to confirmation page for retry to make payment with suitable message
         	}
     
   	 }
/******************************************Offline payment code start**********************************************************/
//This function check for duplicate reference number 		   
    public function renoexist($reno) {
        $is_exist = $this->commodel->isduplicate('student_fees','sfee_referenceno',$reno);
	//print_r($is_exist);
        if ($is_exist)
        {
            $this->form_validation->set_message('renoexist','Reference number'." " .$reno. " ".'is already exist check your refernce number again.');
            return false;
        }
        else {
            return true;
        }
    }
	public function payment($post1,$post2,$post3,$post4,$post5){
		$Sid = $this->session->userdata['sm_id'];
		
			$step4 = array(
		 		'sfee_referenceno'   	=>	$post1,
                		'sfee_bankname'  	=>	$post2,
                		'sfee_feeamount'  	=>	$post3,
                		'sfee_feename'   	=>	$post4,
				'sfee_paymentmethod'   =>	$post5
                	);
			$update = $this->commodel->updaterec('student_fees', $step4,'sfee_smid',$Sid);
			$this->logger->write_logmessage("update", "Step 4 admissionstudent_fees table update.".$Sid);
                    	$this->logger->write_dblogmessage("update", "Step 4 admissionstudent_fees table update." .$Sid);
			
			$email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Sid)->sm_email;
				$mobile= $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Sid)->sm_mobile;
				$name = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;	
				
					$ano = $this->session->userdata['app_no'];
					//update data in admissionmeritlist 
					$sdate = date('Y-m-d H:i:s');
					
					$stuentmerit = array(
		                		'admission_taken'           =>	$sdate,
						'admission_date'	    =>	$sdate,
	           	     		);
		             		$this->db->where('entexamrollno',$ano);
    					$this->db->update('admissionmeritlist',$stuentmerit);


					// update into student entry exit
					$sdate = date('Y-m-d H:i:s');
					$ydate = date('Y');
					$rollno = '';
					$maxrollno = $this->db->query('SELECT MAX(senex_rollno) AS `maxsenex_rollno` FROM `student_entry_exit`')->row()->maxsenex_rollno;
					//print_r($maxrollno);
					if(!empty($maxrollno))
					{
						$rollno = $maxrollno+1;
					}
					else{$rollno = $ydate.'0001';}
					$stuentpdate = array(
		                		'senex_rollno'           =>	$rollno,
						'senex_dateofadmission'	 =>	$sdate,
						'modifierid'		 =>	$Sid  	
	           	     		);
					
		             		$this->db->where('senex_smid',$Sid);
    					$this->db->update('student_entry_exit',$stuentpdate);
					$this->logger->write_logmessage("update", "Step 4 user id update in student_entry_exit table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 user id update in student_entry_exit table." );

				//update student admission status table
				$prgid =  $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;		
				$stu_stupdate = array(
		                		'sas_prgid'           =>	$prgid,
						'sas_admissiondate'   =>	$sdate,
						//'modifierid'	      =>	$Sid  	
	           	     		);
					
		             	$this->db->where('sas_studentmasterid',$Sid);
    				$this->db->update('student_admissionstatus',$stu_stupdate);
				$this->logger->write_logmessage("update", "Step 4 student admission status update.");
                    		$this->logger->write_dblogmessage("update", "Step 4 student admission status update." );

					
                            	$isdupl= $this->logmodel->isduplicate('edrpuser','username',$email);
			
                            	if(!$isdupl){
                                	//generate 10 digit random password
				    	$passwd=$this->commodel->randNum(10);	
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
					//$this->db2->insert('edrpuser',$dataeu);
					$this->logmodel->insertrec('edrpuser',$dataeu);
					$this->logger->write_logmessage("insert", "Step 4 data insert in edrpuser table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in edrpuser table." );
					
					//get the insert id of edrp user
					
					$getid= $this->logmodel->get_listspfic1('edrpuser','id','username',$email);
					$insid=$getid->id;
					// get user id from  edrp table  in login database
					//insert into user profile and user last login status in login database
		 			$dataup = array(
				  		'userid'=> $insid,
						'firstname'=> $name,
						'lang'=> 'english',
						'mobile'=>$mobile,
						'status'=>1
                                    	);
					//$this->db2->insert('userprofile',$dataup);
					$this->logmodel->insertrec('userprofile', $dataup);
					$this->logger->write_logmessage("insert", "Step 4 data insert in userprofile table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in userprofile table." );

					// update into student master userid
					$Supdate = array(
		                		'sm_userid'          	 =>		$insid,
						
	           	     		);
					$this->commodel->updaterec('student_master',$Supdate,'sm_id',$Sid);
					$this->logger->write_logmessage("update", "Step 4 user id update in student_master table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 user id update in student_master table." );
					}else{
						//get user id
						 $insid	= $this->logmodel->get_listspfic1('edrpuser','id','username',$email)->id;
					}
					//insert into userroletype group
					$roleid = $this->commodel->get_listspfic1('role','role_id','role_name','Student')->role_id;
					$sccode = $this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$Sid)->sm_sccode;
					//$scid = $this->commodel->get_listspfic1('study_center','sc_id','sc_id',$sccode)->sc_id;
					//$scid = $this->commodel->get_listspfic1('org_profile','org_id','org_id',$sccode)->org_id;
					//print_r($scid);die;
					$deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Sid)->sp_deptid;
					$dataurt = array(
				        	'userid'=> $insid,
				           	'roleid'=> $roleid,
				           	'prgid'=> $prgid,
				           	'deptid'=> $deptid,
				           //'scid'  => $scid,
				           	'scid'  => $sccode,
				           'usertype'=>"Student"
        	    			);
					//$this->db->insert('user_role_type',$dataurt);
					$this->commodel->insertrec('user_role_type',$dataurt);
					$this->logger->write_logmessage("insert", "Step 4 data insert in user_role_type table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in user_role_type table." );

					//update student_admissionstep table
					$cdate = date('Y-m-d H:i:s');
 					$step4 = array(
						'step4_status'	       =>		 1,
						'step4_date'	       =>		 $cdate
					);
					//print_r($step3);
					//$this->db->where('student_masterid',$Sid);
					//$this->db->update('student_admissionstep', $step4);
					$updast4 = $this->commodel->updaterec('student_admissionstep', $step4,'student_masterid',$Sid);
					$this->logger->write_logmessage("update", "Step 4 update student_admissionstep table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 update student_admissionstep table.");

					//make transaction complete
					
					$this->db->trans_complete();
					//$this->load->view('student/offlinePayment');		
 
	 				//check if transaction status TRUE or FALSE
        				if ($this->db->trans_status() === FALSE) {
            					//if something went wrong, rollback everything
            					$this->db->trans_rollback();
						//else stay with step4
						$msg = '<h3>You are already exist.</h3>';
  						$this->session->set_flashdata('err_message',$msg);	
						$this->logger->write_logmessage("insert", "Step 4 error update and insert");
                    				$this->logger->write_dblogmessage("insert", "Step 4 error update and insert" );
						redirect('student/payment');
						
           					//return FALSE;
      		  			} else {
						
            					//if everything went right, commit the data to the database
           					$this->db->trans_commit();
						$rowsno=$this->commodel->getnoofrows('email_setting');		
						if($rowsno >0){
							//if sucess send mail to user with login details 
		 					$sub='Student Registration' ;
							$mess = "<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
							<tr><td></td></tr>
							<tr><td colspan=2><b>Your registration is complete. </td></tr>
							<tr height=15><td colspan=2></td></tr>
							<tr><td width=370><b>The user id : </b></td><td align=left><span  style='text-decoration:none;'>".$email."</span></td></tr> 
							<tr><td><b>password:</b> </td><td align=left>".$passwd. "</td><tr>
							<tr><td colspan=2>Your fees( ".$post4." ) amount ".$post3." has been paid.The referrence number is ".$post1."</td></tr>
					
							</table> " ;
                        				//$mess="Your registration is complete. \nThe user id ".$email." and password is ".$passwd ."\n Your fees( ".$post4." ) amount ".$post3." has been paid.The referrence number is ".$post1;
                	       				$mails = $this->mailmodel->mailsnd($email, $sub, $mess);
							 //  mail flag check 			
							if($mails){
                        					$error[] ="sufficient data and mail sent sucessfully";
                        					$this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email);
		      						$this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email);
				    			}
							else{
        		       					$error[] ="sufficient data and mail does not sent";
		                				$this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email ." and mail does sent");
								$this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does sent");
			   				}
						}//mail setting check end
						else{
							$error[] ="sufficient data and mail does not sent because mail setting does not exist";
                                                        $this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does not sent because mail setting does not exist" );
                                                        $this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." mail does not sent because  mail setting does not exist");
						}
						$message = "Your".' ' .$post5 .' '."fees is successfully submitted.";
	  					$this->session->set_flashdata('success',$message);			
						//$this->load->view('student/student_step5');
						$this->logger->write_logmessage("insert", "Step 4 detail update and insert successfully.");
                    				$this->logger->write_dblogmessage("insert", "Step 4 detail update and insert successfully." );
						redirect('student/student_step5');
       					}// close else transcation failure and else is missing
				//}// close of if is duplication
				//else{
				//	$message = 'Your login-id and email are already exsist.';
                                //        $this->session->set_flashdata('err_message',$message);
				//}

	//$this->load->view('student/student_step4');
	$this->student_step4($Sid);	

	}
		
	/*public function payment($post1,$post2,$post3,$post4,$post5){
		$ano = $this->session->userdata['app_no'];
		//print_r($ano);
		$getmail = $this->commodel->get_elist('email_setting');
		//print_r($getmail);
		$Sid = $this->session->userdata['sm_id'];
		//$totalfees = $_POST['totalfees'];
		$email = $this->commodel->get_listspfic1("student_master","sm_email","sm_id",$Sid)->sm_email;
		$data['email'] = $email;
		//$this->appno=$this->commodel->get_listspfic1('student_master','sm_applicationno','sm_id',$Sid)->sm_applicationno;
		//$this->sname=$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;
		//$this->fname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Sid)->spar_fathername;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Sid)->sm_gender;
		$this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Sid)->sp_programid;
		//get the program name;
		$this->prgname =$this->commodel->get_listspfic1('program','prg_name','prg_id',$this->prgid)->prg_name;
		$this->catid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Sid)->sm_category;
		//calculate total payble fees
		// in future we add acdamic year
		//$wharray = array('fm_programid' => $this->prgid,('fm_gender' => (All)||($this->gender))&&('fm_category'=>(All)||($this->catid)));
		//
		$wharray = array('fm_programid' => $this->prgname, 'fm_semester' => 1);
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
						
			$this->form_validation->set_rules('refNo','Reference number','trim|xss_clean|numeric|required|callback_renoexist');
            		$this->form_validation->set_rules('bank','Bank detail','trim|xss_clean|required|alpha');
            		$this->form_validation->set_rules('amount','Amount','trim|xss_clean|required|numeric');
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
				if(($_POST['amount']) == $this->totalfees)	
				{			
					$step4 = array(
		 				'asfee_referenceno'   	=>	$post1,
                				'asfee_bankname'  	=>	$post2,
                				'asfee_feeamount'  	=>	$post3,
                				'asfee_feename'   	=>	$post4,
						'asfee_paymentmethod'   =>	$post5
                			);
					//print_r($data);
					$this->db->where('sfee_smid',$Sid);
					$this->db->update('student_fees', $step4); 
				}
				else {	//print_r($getmail);				
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
				$email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Sid)->sm_email;
				$mobile= $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Sid)->sm_mobile;
				$name= $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Sid)->sm_fname;	
				
				//print_r($sccode);		
				//insert into edrpuser in login database
				// check for duplicate
                            	$isdupl= $this->logmodel->isduplicate('edrpuser','username',$email);
			
                            	if(!$isdupl){
                                	//generate 10 digit random password
				    	$passwd=$this->commodel->randNum(10);	
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
					//$this->db2->insert('edrpuser',$dataeu);
					$this->logmodel->insertrec('edrpuser',$dataeu);
					$this->logger->write_logmessage("insert", "Step 4 data insert in edrpuser table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in edrpuser table." );
					
					//get the insert id of edrp user
					
					$getid= $this->logmodel->get_listspfic1('edrpuser','id','username',$email);
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
					//$this->db2->insert('userprofile',$dataup);
					$this->logmodel->insertrec('userprofile', $dataup);
					$this->logger->write_logmessage("insert", "Step 4 data insert in userprofile table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in userprofile table." );
					//insert into userroletype group
					$roleid = $this->commodel->get_listspfic1('role','role_id','role_name','Student')->role_id;
					$sccode = $this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$Sid)->sm_sccode;
					$scid = $this->commodel->get_listspfic1('study_center','sc_id','sc_code',$sccode)->sc_id;
					$deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Sid)->sp_deptid;
					$dataurt = array(
				        	'userid'=> $insid,
				           	'roleid'=> $roleid,
				           	'deptid'=> $deptid,
				           	'scid'  => $scid,
				           	'usertype'=>"Student"
        	    			);
					//$this->db->insert('user_role_type',$dataurt);
					$this->commodel->insertrec('user_role_type',$dataurt);
					$this->logger->write_logmessage("insert", "Step 4 data insert in user_role_type table.");
                    			$this->logger->write_dblogmessage("insert", "Step 4 data insert in user_role_type table." );

					//update data in admissionmeritlist 
					$sdate = date('Y-m-d H:i:s');
					
					$stuentmerit = array(
		                		'admission_taken'           =>	$sdate,
						'admission_date'	    =>	$sdate,
	           	     		);
		             		$this->db->where('application_no',$ano);
    					$this->db->update('admissionmeritlist',$stuentmerit);


					// update into student entry exit
					$sdate = date('Y-m-d H:i:s');
					$ydate = date('Y');
					$$rollno = '';
					$maxrollno = $this->db->query('SELECT MAX(senex_rollno) AS `maxsenex_rollno` FROM `student_entry_exit`')->row()->maxsenex_rollno;
					//print_r($maxrollno);
					if(!empty($maxrollno))
					{
						$rollno = $maxrollno+1;
					}
					else{$rollno = $ydate.'0001';}
					$stuentpdate = array(
		                		'senex_rollno'           =>	$rollno,
						'senex_dateofadmission'	 =>	$sdate,
						'modifierid'		 =>	$Sid  	
	           	     		);
					
		             		$this->db->where('senex_smid',$Sid);
    					$this->db->update('student_entry_exit',$stuentpdate);
					$this->logger->write_logmessage("update", "Step 4 user id update in student_entry_exit table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 user id update in student_entry_exit table." );

					// update into student master userid	
					$Ydate = date('Y');
					$enrollno = '';
					$maxeno = $this->db->query('SELECT MAX(sm_enrollmentno) AS `maxsm_enrollmentno` FROM `student_master`')->row()->maxsm_enrollmentno;
					//print_r($maxid);
					if(!empty($maxeno))
					{
						$enrollno=$maxeno+1;
					}
					else{$enrollno = $Ydate.'0001';}
					$Supdate = array(
		                		'sm_userid'          	 =>		$insid,
						'sm_enrollmentno'	 =>		$enrollno
	           	     		);
					//print_r($Supdate);
		             		$this->db->where('sm_id',$Sid);
	    				$insertUserData = $this->db->update('student_master',$Supdate);
					$this->logger->write_logmessage("update", "Step 4 user id update in student_master table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 user id update in student_master table." );

					//update student_admissionstep table
					$cdate = date('Y-m-d H:i:s');
 					$step4 = array(
						'step4_status'	       =>		 1,
						'step4_date'	       =>		 $cdate
					);
					//print_r($step3);
					//$this->db->where('student_masterid',$Sid);
					//$this->db->update('student_admissionstep', $step4);
					$updast4 = $this->commodel->updaterec('student_admissionstep', $step4,'student_masterid',$Sid);
					$this->logger->write_logmessage("update", "Step 4 update student_admissionstep table.");
                    			$this->logger->write_dblogmessage("update", "Step 4 update student_admissionstep table.");

					//make transaction complete
					
					$this->db->trans_complete();
					//$this->load->view('student/offlinePayment');		
 
	 				//check if transaction status TRUE or FALSE
        				if ($this->db->trans_status() === FALSE) {
            					//if something went wrong, rollback everything
            					$this->db->trans_rollback();
						//else stay with step4
						$msg = '<h3>You are already exist.</h3>';
  						$this->session->set_flashdata('err_message',$msg);	
						$this->logger->write_logmessage("insert", "Step 4 error update and insert");
                    				$this->logger->write_dblogmessage("insert", "Step 4 error update and insert" );
						redirect('student/payment');
						
           					//return FALSE;
      		  			} else {
						
            					//if everything went right, commit the data to the database
           					$this->db->trans_commit();
						$rowsno=$this->commodel->getnoofrows('email_setting');		
						if($rowsno >0){
							//if sucess send mail to user with login details 
		 					$sub='Student Registration' ;
                        				$mess="Your registration is complete. The user id ".$email." and password is ".$passwd ;
                	       				$mails = $this->mailmodel->mailsnd($email, $sub, $mess);
							 //  mail flag check 			
							if($mails){
                        					$error[] ="At row".$i."sufficient data and mail sent sucessfully";
                        					$this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email);
		      						$this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email);
				    			}
							else{
        		       					$error[] ="At row".$i."sufficient data and mail does not sent";
		                				$this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email ." and mail does sent");
								$this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does sent");
			   				}
						}//mail setting check end
						else{
							$error[] ="At row".$i."sufficient data and mail does not sent because mail setting does not exist";
                                                        $this->logger->write_logmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does not sent because mail setting does not exist" );
                                                        $this->logger->write_dblogmessage("insert"," add student edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." mail does not sent because  mail setting does not exist");
						}
						$message = '<h3>Your registration is successfully completed.</h3>';
	  					$this->session->set_flashdata('success',$message);			
						//$this->load->view('student/student_step5');
						$this->logger->write_logmessage("insert", "Step 4 detail update and insert successfully.");
                    				$this->logger->write_dblogmessage("insert", "Step 4 detail update and insert successfully." );
						redirect('student/student_step5');
       					}// close else transcation failure and else is missing
				}// close of if is duplication
				else{
					$message = '<h3>Your registration is failure due to email id is already exist in system.Contact to authority</h3>';
                                        $this->session->set_flashdata('success',$message);
				}
			}/*close else validation
		}/*close post submit
		//set flag for each step, if any step fails revert all steps and return to same step
		$this->load->view('student/student_step4',$data);
	}*/	

/*************************************offline payment end***************************************************************/

	public function student_step5(){
		   //get sm_id from session
			$id = $this->session->userdata['sm_id'];
			//print_r($id);
		$email = $this->commodel->get_listspfic1("student_master","sm_email","sm_id",$id)->sm_email;
		$data['email'] = $email;
		
		//get the education detail from student_education
		// availabe final form for printing
		$this->sname=$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$id)->sm_fname;
		$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$id)->sm_mobile;
		$this->email=$this->commodel->get_listspfic1('student_master','sm_email','sm_id',$id)->sm_email;
		$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$id)->sm_dob;
		$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$id)->sm_uid;
		$this->bgroup=$this->commodel->get_listspfic1('student_master','sm_bloodgroup','sm_id',$id)->sm_bloodgroup;

		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$id)->spar_mothername;		
		$this->fname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$id)->spar_fathername;
		$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$id)->sp_programid;
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$id)->sm_gender;
	
		//postal address detail
		$this->padd=$this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$id)->spar_paddress;
		$this->cadd=$this->commodel->get_listspfic1('student_parent','spar_caddress','spar_smid',$id)->spar_caddress;
		$this->pcity=$this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$id)->spar_pcity;
		$this->ccity=$this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$id)->spar_ccity;
		$this->ppost=$this->commodel->get_listspfic1('student_parent','spar_ppostoffice','spar_smid',$id)->spar_ppostoffice;
		$this->cpost=$this->commodel->get_listspfic1('student_parent','spar_cpostoffice','spar_smid',$id)->spar_cpostoffice;
		$this->pdist=$this->commodel->get_listspfic1('student_parent','spar_pdistrict','spar_smid',$id)->spar_pdistrict;
		$this->cdist=$this->commodel->get_listspfic1('student_parent','spar_cdistrict','spar_smid',$id)->spar_cdistrict;
		$this->pstat=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$id)->spar_pstate;
		$this->cstat=$this->commodel->get_listspfic1('student_parent','spar_cstate','spar_smid',$id)->spar_cstate;
		$this->ppin=$this->commodel->get_listspfic1('student_parent','spar_ppincode','spar_smid',$id)->spar_ppincode;
		$this->cpin= $this->commodel->get_listspfic1('student_parent','spar_cpincode','spar_smid',$id)->spar_cpincode;
		
		$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$id)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;

		//fees detail
		$this->prog=$this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->brnch=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$this->ncid)->prg_branch;
		$this->amnt=$this->commodel->get_listspfic1('student_fees','sfee_feeamount','sfee_smid',$id)->sfee_feeamount;
		$this->pmethod=$this->commodel->get_listspfic1('student_fees','sfee_paymentmethod','sfee_smid',$id)->sfee_paymentmethod;
		$this->rno=$this->commodel->get_listspfic1('student_fees','sfee_referenceno','sfee_smid',$id)->sfee_referenceno; 
		$this->fid=$this->commodel->get_listspfic1('student_fees','sfee_id','sfee_smid',$id)->sfee_id; 
		$this->bname=$this->commodel->get_listspfic1('student_fees','sfee_bankname','sfee_smid',$id)->sfee_bankname;

		$this->rollno = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$id)->senex_rollno;
		
		//education detail
		$this->seresult = $this->commodel->get_listrow('student_education','sedu_smid',$id)->result();
		//get photo or sign
		$this->phresult = $this->commodel->get_listspfic1('student_master','sm_photo','sm_id',$id)->sm_photo;
		$this->signresult = $this->commodel->get_listspfic1('student_master','sm_signature','sm_id',$id)->sm_signature;
		
		//set the new values in session(role,student user_id and status login)
		//redirect to student home page for subject selection after 5 minute/give the button move to student home page
		
		//update student_admissionstep table
			$cdate = date('Y-m-d H:i:s');
			$step5 = array(
				'step5_status'	       =>		 1,
				'step5_date'	       =>		 $cdate
			);
			$updst5 = $this->commodel->updaterec('student_admissionstep', $step5,'student_masterid',$id);
			//$this->db->where('student_masterid',$id);
			//$this->db->update('student_admissionstep', $step5);
			$this->logger->write_logmessage("update", "Step 5 admission step updated successfully.");
                    	$this->logger->write_dblogmessage("update", "Step 5 admission step updated successfully." );
			$this->load->view('student/student_step5');
	}


	public function student_step5dw(){
		   //get sm_id from session
			$id = $this->session->userdata['sm_id'];
			//print_r($id);
		$email = $this->commodel->get_listspfic1("student_master","sm_email","sm_id",$id)->sm_email;
		$data['email'] = $email;
		
		//get the education detail from student_education
		// availabe final form for printing
		$this->sname=$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$id)->sm_fname;
		$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$id)->sm_mobile;
		$this->email=$this->commodel->get_listspfic1('student_master','sm_email','sm_id',$id)->sm_email;
		$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$id)->sm_dob;
		$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$id)->sm_uid;
		$this->bgroup=$this->commodel->get_listspfic1('student_master','sm_bloodgroup','sm_id',$id)->sm_bloodgroup;

		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$id)->spar_mothername;		
		$this->fname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$id)->spar_fathername;
		$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$id)->sp_programid;
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$id)->sm_gender;
	
		//postal address detail
		$this->padd=$this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$id)->spar_paddress;
		$this->cadd=$this->commodel->get_listspfic1('student_parent','spar_caddress','spar_smid',$id)->spar_caddress;
		$this->pcity=$this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$id)->spar_pcity;
		$this->ccity=$this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$id)->spar_ccity;
		$this->ppost=$this->commodel->get_listspfic1('student_parent','spar_ppostoffice','spar_smid',$id)->spar_ppostoffice;
		$this->cpost=$this->commodel->get_listspfic1('student_parent','spar_cpostoffice','spar_smid',$id)->spar_cpostoffice;
		$this->pdist=$this->commodel->get_listspfic1('student_parent','spar_pdistrict','spar_smid',$id)->spar_pdistrict;
		$this->cdist=$this->commodel->get_listspfic1('student_parent','spar_cdistrict','spar_smid',$id)->spar_cdistrict;
		$this->pstat=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$id)->spar_pstate;
		$this->cstat=$this->commodel->get_listspfic1('student_parent','spar_cstate','spar_smid',$id)->spar_cstate;
		$this->ppin=$this->commodel->get_listspfic1('student_parent','spar_ppincode','spar_smid',$id)->spar_ppincode;
		$this->cpin= $this->commodel->get_listspfic1('student_parent','spar_cpincode','spar_smid',$id)->spar_cpincode;
		
		$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$id)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;

		//fees detail
		$this->prog=$this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->amnt=$this->commodel->get_listspfic1('student_fees','sfee_feeamount','sfee_smid',$id)->sfee_feeamount;
		$this->pmethod=$this->commodel->get_listspfic1('student_fees','sfee_paymentmethod','sfee_smid',$id)->sfee_paymentmethod;
		$this->rno=$this->commodel->get_listspfic1('student_fees','sfee_referenceno','sfee_smid',$id)->sfee_referenceno; 
		$this->fid=$this->commodel->get_listspfic1('student_fees','sfee_id','sfee_smid',$id)->sfee_id; 
		$this->bname=$this->commodel->get_listspfic1('student_fees','sfee_bankname','sfee_smid',$id)->sfee_bankname;

		$this->rollno = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$id)->senex_rollno;
		
		//education detail
		$this->seresult = $this->commodel->get_listrow('student_education','sedu_smid',$id)->result();
		//get photo or sign
		$this->phresult = $this->commodel->get_listspfic1('student_master','sm_photo','sm_id',$id)->sm_photo;
		$this->signresult = $this->commodel->get_listspfic1('student_master','sm_signature','sm_id',$id)->sm_signature;
		
		$this->load->library('pdf');
       		$this->pdf->load_view('student/student_step5dw');
        	$this->pdf->render();
        	$this->pdf->stream("Student_Admission.pdf");
		
		//$this->load->view('student/student_step5');
	}

	public function student_home($applicationno){
		$stuid = $this->session->userdata['sm_id'];
		//print_r($stuid);	
		 if(isset($_POST['studentHome'])) {
				//get the userid from student master
				$smres=$this->commodel->get_listspfic1('student_master','sm_userid','sm_id',$stuid);
				$suid=$smres->sm_userid;
				 //print_r($smres);				
				//get the username from edrpuser
				$usernme=$this->logmodel->get_listspfic1('edrpuser','username','id',$suid)->username;
				 //check the value set in session if not then
				//set the student master and application number in session
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
