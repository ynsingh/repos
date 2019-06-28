
<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Admin_studentresult.php
 * @author Sumit saxena(sumitsesaxena@gmail.com)
 */

   
class Admin_studentresult extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "login");
                $this->load->model('Dependrop_model',"depmodel");

        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usermodel");

        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
    public function tabulationchart(){

        $tabulate['degree']=$this->commodel->get_list("program");
        $this->load->view('result/tabulationchart',$tabulate);

    }

    public function branchlist(){
            //echo "hi";
            //$semprg = $this->input->post('programname');
            //echo $semprg;
            //return $data['h1'];
            //echo "<option> jhp </option> ";
           $semprg = $this->input->post('programname');
           //$brlist = $this->commodel->get_listrow('program','prg_name', $semprg);
           $brlist=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$semprg);
           foreach ($brlist as $br) {
               # code...
           
           echo "<option> $br</option>";
        }
       

    }

    public function studentmarks() {
       
	$array_items = array('success' => '', 'error' => '', 'warning' =>'');
       	$this->session->set_flashdata($array_items);
	$error =array();
        $username = $this->session->userdata('username');
	//get current semester
	$currsem = $this->usermodel->getcurrentSemester();
	$semsize = strlen($currsem);
        //get current academic year
        $acadyear = $this->usermodel->getcurrentAcadYear();
	$facultydata['academicyear']=$acadyear;
        
	//get program in student_program table
		$selectfield=array('sp_programid');
        	$data=array(
            		'sp_semester'   => $semsize,
            		'sp_acadyear' => $acadyear,
        	);
        	$facultyrec = $this->commodel->get_distinctrecord('student_program',$selectfield,$data);
        	$facultydata['facprgsublist']=$facultyrec;
        $stud_prg_rec ;
        $this->mst = 0;

	//get post data
        if(isset($_POST)){
            $prgid=$this->input->post('program_branch',TRUE);
	    $facultydata['prgid'] = $prgid;	
            $prgsem=$this->input->post('semester',TRUE);
	    $facultydata['prgsem'] = $prgsem;
            $subid=$this->input->post('subjectname',TRUE);
	    $facultydata['subid'] = $subid;	
	    $papid=$this->input->post('papername',TRUE);
	    $facultydata['papid'] = $papid;
           
            $search = $this->input->post('search');

	    //get student master id when click search button	
            if(isset($search)) 
            {

		$this->form_validation->set_rules('program_branch','Select Programme','trim|xss_clean|required');	
		$this->form_validation->set_rules('semester','Select Programme','trim|xss_clean|required');
		$this->form_validation->set_rules('subjectname','Select Programme','trim|xss_clean|required');
		$this->form_validation->set_rules('papername','Select Programme','trim|xss_clean|required');
		if($this->form_validation->run() == False){
			$this->session->set_flashdata("err_message",'Select Programme ,Semester ,Subject and Paper Name.' );	
			redirect('Admin_studentresult/studentmarks');
		 }
		else{
                 $whereau = "((sp_programid = $prgid) AND (sp_semester = $prgsem) AND (sp_acadyear = '$acadyear') AND ((sp_subid1 = $subid) OR (sp_subid2 = $subid) OR (sp_subid3 = $subid) OR (sp_subid4 = $subid) OR (sp_subid5 = $subid) OR (sp_subid6 = $subid) OR (sp_subid7 = $subid) OR (sp_subid8 = $subid) OR (sp_subid9 = $subid) OR (sp_subid10 = $subid)))";
		$selectdata=array('sp_smid');
		if(!empty($whereau)){
	        	$stud_prg_rec = $this->commodel->get_listspficemore('student_program',$selectdata,$whereau);
			$facultydata['studentdetail'] = $stud_prg_rec;
		}
                
                //select exam type .
                $exmtype = $this->commodel->get_list('examtype');
                $facultydata['exmtype'] = $exmtype;
            }//else search close
	}//if isset search close
            $wrongmarks = array();
            $insertmarks;
            $uploadmarks = $this->input->post('Submit');

            if (isset($uploadmarks))
            {

		 $ferror='';
		//select exam type .
                //$exmtype = $this->commodel->get_list('examtype');
		$typeexam = $this->input->post('examtype',TRUE);
                $facultydata['exmtype'] = $typeexam;
		$mmarks = $this->input->post('mmarks',TRUE);

                $prgid1=$this->input->post('prgid1',TRUE);
                $sem =  $this->input->post('semester1',TRUE);
                $subid = $this->input->post('subid',TRUE);
                $papid = $this->input->post('papid',TRUE);
                
                $sizesmid = $this->input->post('studsize',TRUE); 
  
                for ($i=0; $i<$sizesmid ;$i++)
                {		$studmid=  $this->input->post('stumid'.$i,TRUE);
                   		$studmarksobtain =  $this->input->post('marks'.$i,TRUE);
                    		$studgradesobtain = $this->input->post('grade'.$i,TRUE);
                    		$studgradesobtain = ucwords(strtolower($studgradesobtain));
			 
			 $datacheck = array(
				'smr_smid' => $studmid,
                        	'smr_prgid' => $prgid1,
                        	'smr_subid' => $subid,
                        	'smr_papid' => $papid,
                        	'smr_acadyear' => $acadyear,
                        	'smr_sem' => $sem,
                        	'smr_extyid' => $typeexam,
                        	'smr_mmmarks' => $mmarks,
                        	'smr_marks' => $studmarksobtain,
                        	'smr_grade' => $studgradesobtain,
                        );    
			$datavalue=  'smr_smid=>' . $studmid.'smr_prgid  =>'. $prgid1.
                        	'smr_subid =>' .$subid.
                        	'smr_papid =>'. $papid.
                        	'smr_acadyear =>'.$acadyear.
                        	'smr_sem =>'. $sem.
                        	'smr_extyid =>' . $typeexam ;     
			$pstdatadup = $this->commodel->isduplicatemore('student_marks',$datacheck);
			
        		if($pstdatadup){
				$this->logger->write_logmessage("insert", "Student marks already submitted ." .$datavalue);
                    		$this->logger->write_dblogmessage("insert", "Student marks already submitted ." .$datavalue);
				$this->session->set_flashdata("err_message",'Student marks already submitted.' );
				redirect('admin_studentresult/studentmarks');
			}
        		else{
                   		 
                    		$fullname = "";
                    		$wrmarks = $studmarksobtain;
                    		if($studmarksobtain > $mmarks)
                    		{
                       			$studmarksobtain = 0;
                    		}   
				$selectdata='sp_sccode,sp_deptid';
				$whereau=array('sp_smid' => $studmid , 'sp_programid' => $prgid1);
				$campuslist = $this->commodel->get_listspficemore('student_program',$selectdata,$whereau);
				foreach($campuslist as $row){
					$campusid = $row->sp_sccode; 
					$deptid = $row->sp_deptid;
				}
                   		 $insertmarksdata = array(
                       			 	'smr_smid' => $studmid,
                        			'smr_scid' => $campusid,
                       			 	'smr_deptid' => $deptid,
                        		 	'smr_prgid' => $prgid1,
                        		  	'smr_subid' => $subid,
                        		  	'smr_papid' => $papid,
                        		  	'smr_acadyear' => $acadyear,
                       			  	'smr_sem' => $sem,
                        		 	'smr_extyid' => $typeexam,
                        			'smr_mmmarks' => $mmarks,
                        			'smr_marks' => $studmarksobtain,
                        			'smr_grade' => $studgradesobtain,
                        			'smr_creatorid' => $username,
                        			'smr_createdate' => date('y-m-d'),
                        			'smr_modifierid' => $username,
                        			'smr_modifydate' => date('y-m-d')
                    			);           

                        		$this->mst = 0;
                        		$insertmarks = $this->commodel->insertrec('student_marks', $insertmarksdata); 
					$subname = $this->commodel->get_listspfic1('subject','sub_name ','sub_id',$subid)->sub_name;
					$papername = $this->commodel->get_listspfic1('subject_paper','subp_name ','subp_id',$papid)->subp_name;
                        		if(!$insertmarks)
                        	{
					//$error[] ="At row".$i."Marks can't not inserted in subject/paper".' '.$subname."/".$papername;
					$error[] = "Marks can't not inserted to this subject :- ".' '.$subname.' '."and paper :-".' '.$papername;
                            		$this->logger->write_logmessage("insert",$studmarksobtain."Marks can't not inserted in subject/paper - ".$subname."/".$papername, "of student (student id) ".$studmid." by ".$username);
                           		 $this->logger->write_dblogmessage("insert",$studmarksobtain."Marks can't inserted in subject/paper - ".$subname."/".$papername, "of student (student id)".$studmid." by ".$username);
                        	}
                        	else{
					//$error[] ="At row".$i."Marks inserted in subject/paper".' '.$subname."/".$papername;
					$error[] = "Marks inserted to this subject :- ".' '.$subname.' '."and paper :-".' '.$papername;
                            		$this->logger->write_logmessage("insert",$studmarksobtain."Marks inserted in subject/paper - ".$subname."/".$papername, "of student ".$studmid." by ".$username);
                            		$this->logger->write_dblogmessage("insert",$studmarksobtain."Marks inserted in subject/paper - ".$subname."/".$papername, "of student ".$studmid." by ".$username);
                            		$this->session->set_flashdata("success", "Marks uploaded succesfully Please verify it ->View Student Marks");
                        	}
                   	// }
                	}//close else
		}
		foreach ($error as $item => $value):
			    $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
			//display error of array
			//put ferror in log file.
			$this->session->set_flashdata('success', $ferror);

                //redirect('faculty/studentmarks');
            }//upload
        }//POST
        $this->load->view('admin_results/admin_studentmarks',$facultydata);
    }

    public function studentsviewmarks() {
	//get current academic year
        $acadyear = $this->usermodel->getcurrentAcadYear();
	$fdata['academicyear'] = $acadyear;

	//current semester
	$currsem = $this->usermodel->getcurrentSemester();
	$semsize = strlen($currsem);

	//get programme and semester
	$selectfield=array('sp_programid','sp_semester');
        	$data=array(
            		'sp_semester'   => $semsize,
            		'sp_acadyear' => $acadyear,
        	);
        $facultyrec = $this->commodel->get_distinctrecord('student_program',$selectfield,$data);
	$fdata['facprgsublist']=$facultyrec;	

	$exmtype = $this->commodel->get_list('examtype');
        $fdata['exmtype'] = $exmtype;

        //get subject name 
		$selectfield=array('smr_subid','smr_papid');
        	$data=array(
            		'smr_sem'  => $semsize,
			'smr_acadyear'  => $acadyear,
        	);
        	$subject = $this->commodel->get_distinctrecord('student_marks',$selectfield,$data);
		//print_r($subject);
		$fdata['subject'] = $subject;
  
	//select exam type
        $exmtype = $this->commodel->get_list('examtype');
        $fdata['exmtype'] = $exmtype;
       
        $stud_prg_rec ;
        if(isset($_POST)){
          
	    $prgid=$this->input->post('program_branch',TRUE);
            $prgsem=$this->input->post('semester',TRUE);
            $subid=$this->input->post('subjectname',TRUE);
	    $papid=$this->input->post('papername',TRUE);
	    $etype = $this->input->post('examtype',TRUE);

            $search = $this->input->post('search');
            $uploadmarks = $this->input->post('Submit');

            if (isset($search))
            {
		//search with combine data(programme , semester , subjectid , exam type)
                $subfields=array('sp_smid','sp_deptid','sp_subid1','sp_subid2','sp_subid3','sp_subid4','sp_subid5','sp_subid6','sp_subid7','sp_subid8','sp_subid9','sp_subid10');
            
		 $whereau = array('smr_prgid' => $prgid,'smr_sem' => $prgsem,'smr_acadyear' => $acadyear ,'smr_subid' => $subid,'smr_subid' => $subid , 'smr_papid' => $papid, 'smr_extyid' => $etype);
		$selectdata='smr_smid,smr_prgid,smr_mmmarks,smr_marks,smr_grade,smr_extyid';
		if(!empty($whereau)){
	        	$stud_prg_rec = $this->commodel->get_listspficemore('student_marks',$selectdata,$whereau);
			$fdata['studentdetail'] = $stud_prg_rec;
		}
		//search with programme , semester , subjectid , exam type
		$record=array( );	
			if(!empty($prgid)){
			$record['smr_prgid'] =  $prgid;}

			if(!empty($prgsem)){
			$record['smr_sem'] =  $prgsem;}

			if(!empty($subid)){
			$record['smr_subid'] =  $subid;}

			if(!empty($papid)){
			$record['smr_papid'] =  $papid;}

			if(!empty($etype)){
			$record['smr_extyid'] = $etype;}	

		$select='smr_smid,smr_prgid,smr_mmmarks,smr_marks,smr_grade,smr_extyid';
			if(!empty($record)){		
       				$stud_prg_rec = $this->commodel->get_listspficemore('student_marks',$select,$record);
				$fdata['studentdetail'] = $stud_prg_rec;
				
			}
            }
            $fdata['prgname'] = $prgid;
            $fdata['acadyear'] = $acadyear;
            $fdata['prgsem'] = $prgsem;
            $fdata['subpap'] = $subid;
	    $fdata['papid'] = $papid;
            $fdata['etype'] = $etype;
        }
        
	   $this->load->view('admin_results/admin_studentviewmarks',$fdata);
    }

	/*public function stu_tabulation(){
		$this->load->view('admin_results/admin_stutabulation');
	}


	public function stu_scrutiny(){
		$this->load->view('admin_results/admin_stuscrutiny');
	}

	public function stu_resultdecl(){
		$this->load->view('admin_results/admin_resultdeclare');
	}
	
	public function stu_resultstop(){
		$this->load->view('admin_results/admin_resultstop');
	}

	public function stu_stugradegenprint(){
		$this->load->view('admin_results/admin_stugrade_genprint');
	}

	public function stu_stugradissue(){
		$this->load->view('admin_results/admin_stugradeissue');
	}*/


	

/*********************************************************student attendence dependent dropdown start**************************************************/
	//get semester record
	public function semester_get(){
		$acadyear = $this->usermodel->getcurrentAcadYear();
		$prgid = $this->input->post('program_branch');
		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield=array('sp_semester');
        	$data=array(
            		
			'sp_acadyear'   => $acadyear,
			'sp_programid'	=> $prgid
        	);
        	$sem = $this->commodel->get_distinctrecord('student_program',$selectfield,$data);

		echo "<select name='semester' id='semester'>";	
			echo "<option selected='true' disabled>"."Semester"."</option>";
		foreach($sem as $datas):   
			 echo "<option  value='$datas->sp_semester'>"."$datas->sp_semester"."</option>";
   		endforeach;
		echo "</select>";
			
	}
	//get all teacher subjects
	public function subject_get(){
		$acadyear = $this->usermodel->getcurrentAcadYear();
		$semprgid = $this->input->post('semprg');
		$subpart = explode(',',$semprgid);

		//get program list of program,subject,paper and semester in program_subject_teacher
       		$selectfield1=array('sp_semester','sp_subid1','sp_programid');
        	$data1=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject1 = $this->commodel->get_distinctrecord('student_program',$selectfield1,$data1);

		$selectfield2=array('sp_semester','sp_subid2','sp_programid');
        	$data2=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject2 = $this->commodel->get_distinctrecord('student_program',$selectfield2,$data2);

		$selectfield3=array('sp_semester','sp_subid3','sp_programid');
        	$data3=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject3 = $this->commodel->get_distinctrecord('student_program',$selectfield3,$data3);

		$selectfield4=array('sp_semester','sp_subid4','sp_programid');
        	$data4=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject4 = $this->commodel->get_distinctrecord('student_program',$selectfield4,$data4);

		$selectfield5=array('sp_semester','sp_subid5','sp_programid');
        	$data5=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject5 = $this->commodel->get_distinctrecord('student_program',$selectfield5,$data5);

		$selectfield6=array('sp_semester','sp_subid6','sp_programid');
        	$data6=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject4 = $this->commodel->get_distinctrecord('student_program',$selectfield6,$data6);

		$selectfield7=array('sp_semester','sp_subid7','sp_programid');
        	$data7=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject7 = $this->commodel->get_distinctrecord('student_program',$selectfield7,$data7);

		$selectfield8=array('sp_semester','sp_subid8','sp_programid');
        	$data8=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject8 = $this->commodel->get_distinctrecord('student_program',$selectfield8,$data8);

		$selectfield9=array('sp_semester','sp_subid9','sp_programid');
        	$data9=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject9 = $this->commodel->get_distinctrecord('student_program',$selectfield9,$data9);

		$selectfield10=array('sp_semester','sp_subid10','sp_programid');
        	$data10=array(
            		
			'sp_acadyear' 	=> $acadyear,
			'sp_programid'	=> $subpart[0],
			'sp_semester'	=> $subpart[1]
        	);
        	$subject10 = $this->commodel->get_distinctrecord('student_program',$selectfield10,$data10);

		echo "<select name='subjectname' >";	
		echo "<option selected='true' disabled>"."Select Subject"."</option>";	
		foreach($subject1 as $data1){
			
			$whdata1 = array('sub_id'  =>  $data1->sp_subid1,'sub_program' => $data1->sp_programid , 'sub_semester' => $data1->sp_semester);
			$sdata1 = array('sub_name','sub_id');;
			$subname1 = $this->commodel->get_distinctrecord('subject',$sdata1,$whdata1);
		}foreach($subject2 as $data2){
			$whdata2 = array('sub_id'  =>  $data2->sp_subid2,'sub_program' => $data2->sp_programid , 'sub_semester' => $data2->sp_semester);
			$sdata2 = array('sub_name','sub_id');;
			$subname2 = $this->commodel->get_distinctrecord('subject',$sdata2,$whdata2);
		}foreach($subject3 as $data3){
			$whdata3 = array('sub_id'  =>  $data3->sp_subid3,'sub_program' => $data3->sp_programid , 'sub_semester' => $data3->sp_semester);
			$sdata3 = array('sub_name','sub_id');;
			$subname3 = $this->commodel->get_distinctrecord('subject',$sdata3,$whdata3);
		}foreach($subject4 as $data4){
			$whdata4 = array('sub_id'  =>  $data4->sp_subid4,'sub_program' => $data4->sp_programid , 'sub_semester' => $data4->sp_semester);
			$sdata4 = array('sub_name','sub_id');
			$subname4 = $this->commodel->get_distinctrecord('subject',$sdata4,$whdata4);
		}foreach($subject5 as $data5){
			$whdata5 = array('sub_id'  =>  $data5->sp_subid5,'sub_program' => $data5->sp_programid , 'sub_semester' => $data5->sp_semester);
			$sdata5 = array('sub_name','sub_id');
			$subname5 = $this->commodel->get_distinctrecord('subject',$sdata5,$whdata5);
		}foreach($subject6 as $data6){
			$whdata6 = array('sub_id'  =>  $data6->sp_subid6,'sub_program' => $data6->sp_programid , 'sub_semester' => $data6->sp_semester);
			$sdata6 = array('sub_name','sub_id');
			$subname6 = $this->commodel->get_distinctrecord('subject',$sdata6,$whdata6);
		}foreach($subject7 as $data7){
			$whdata7 = array('sub_id'  =>  $data7->sp_subid7,'sub_program' => $data7->sp_programid , 'sub_semester' => $data7->sp_semester);
			$sdata7 = array('sub_name','sub_id');
			$subname7 = $this->commodel->get_distinctrecord('subject',$sdata7,$whdata7);
		}foreach($subject8 as $data8){
			$whdata8 = array('sub_id'  =>  $data8->sp_subid8,'sub_program' => $data8->sp_programid , 'sub_semester' => $data8->sp_semester);
			$sdata8 = array('sub_name','sub_id');
			$subname8 = $this->commodel->get_distinctrecord('subject',$sdata8,$whdata8);
		}foreach($subject9 as $data9){
			$whdata9 = array('sub_id'  =>  $data9->sp_subid9,'sub_program' => $data9->sp_programid , 'sub_semester' => $data9->sp_semester);
			$sdata9 = array('sub_name','sub_id');
			$subname9 = $this->commodel->get_distinctrecord('subject',$sdata9,$whdata9);
		}foreach($subject10 as $data10){
			$whdata10 = array('sub_id'  =>  $data10->sp_subid10,'sub_program' => $data10->sp_programid , 'sub_semester' => $data10->sp_semester);
			$sdata10 = array('sub_name','sub_id');
			$subname10 = $this->commodel->get_distinctrecord('subject',$sdata10,$whdata10);
		}
		foreach($subname1 as $datas1):   
			 echo "<option  value='$datas1->sub_id' id='subjectname'>"."$datas1->sub_name"."</option>";
   		endforeach;
		
		foreach($subname2 as $datas2):   
			 echo "<option  value='$datas2->sub_id'>"."$datas2->sub_name"."</option>";
   		endforeach;

		foreach($subname3 as $datas3):   
			 echo "<option  value='$datas3->sub_id' id='subjectname'>"."$datas3->sub_name"."</option>";
   		endforeach;

		
		foreach($subname4 as $datas4):   
			 echo "<option id='subjectname' value='$datas4->sub_id'>"."$datas4->sub_name"."</option>";
   		endforeach;

		foreach($subname5 as $datas5):   
			 echo "<option id='subjectname' value='$datas5->sub_id'>"."$datas5->sub_name"."</option>";
   		endforeach;

		foreach($subname6 as $datas6):   
			 echo "<option id='subjectname' value='$datas6->sub_id'>"."$datas6->sub_name"."</option>";
   		endforeach;

		foreach($subname7 as $datas7):   
			 echo "<option id='subjectname' value='$datas7->sub_id'>"."$datas7->sub_name"."</option>";
   		endforeach;

		foreach($subname8 as $datas8):   
			 echo "<option id='subjectname' value='$datas8->sub_id'>"."$datas8->sub_name"."</option>";
   		endforeach;

		foreach($subname9 as $datas9):   
			 echo "<option id='subjectname' value='$datas9->sub_id'>"."$datas9->sub_name"."</option>";
   		endforeach;

		foreach($subname10 as $datas10):   
			 echo "<option id='subjectname' value='$datas10->sub_id'>"."$datas10->sub_name"."</option>";
   		endforeach;	
		//}
		echo "</select>";	
	}

	//get all teacher papers
	public function papers_get(){
		$acadyear = $this->usermodel->getcurrentAcadYear();
		$prg_sem_subid = $this->input->post('prg_sem_sub');
		$subparts = explode(',',$prg_sem_subid);
		
		$selectfield='subp_name,subp_id';
		$paperdata=array(
			'subp_acadyear' => $acadyear,
			'subp_degree'	=> $subparts[0],
			'subp_sem'	=> $subparts[1],
			'subp_sub_id'   => $subparts[2],
			//'subp_id'	=> $subparts[2],
	        	);
		
		$papername = $this->commodel->get_listspficemore('subject_paper',$selectfield,$paperdata);
	
		echo "<select name='papername' id='papername'>";	
			echo "<option selected='true' disabled>"."Paper Name"."</option>";
			
		foreach($papername as $row){
			echo "<option  value='$row->subp_id'>"."$row->subp_name"."</option>";
   		}
		echo "</select>";
			
	}

/*********************************************************student attendence dependent dropdown end**************************************************/



}
?>
