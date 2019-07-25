<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studentrecord.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author Manorama Pal(palseema30@gmail.com)  add pdf download code[Dompdf] 
 * @author kishore kr shukla(kishore.shukla@gmail.com)[Fees Receipt Generate]	
 * @author Sumit Saxena(sumitsesaxena@gmail.com)[Download Link(Admission Form & Exam Form)]  
 */

class Studentrecord extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "logmodel");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usrmodel");
        $this->load->model("Student_model", "stumodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
	    /* get role id and user id from session*/
	    $userid = $this->session->userdata('id_user');
	    $roleid = $this->session->userdata('id_role');
    }
    public function subjectrecord()
    {
	$userid = $this->session->userdata('id_user');
	$smid =$this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
	$whdata= array('sp_smid' => $smid);
	$this->result = $this->commodel->get_listarry('student_program','*',$whdata);
        $this->logger->write_logmessage("view"," View Student program and  subject", "View Student program and  subject");
        $this->logger->write_dblogmessage("view"," View Student program and  subject", "View Student program and  subject");
        $this->load->view('student/subjectrecord',$this->result);
     }

    public function feesrecord()
     {
		$userid = $this->session->userdata('id_user');
        	$smid =$this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
        	$whdata= array('sfee_smid' => $smid,'sfee_reconcilestatus' => 'Y');

        	$this->result = $this->commodel->  get_listarry('student_fees','*',$whdata);
                $this->logger->write_logmessage("view"," View map user with role setting", "user map setting details...");
                $this->logger->write_dblogmessage("view"," View map user with role setting", "Role setting details...");
                $this->load->view('student/feesrecord',$this->result);
     }

   /**
    * Get Download PDF File
    * @return Response
   */

   function feesreceiptdw(){
	$userid = $this->session->userdata('id_user');
	// get the smid
	$smid =$this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
	$sfee_id=$this->uri->segment(3);
	//get the latest sfeeid if $sfee_isd is null
	// add reconsillation check in future
	if (empty ($sfee_id)){
		$wharray = array('sfee_smid' => $smid, 'sfee_feeamount>' => 0,'sfee_reconcilestatus' => 'Y');
		$sfeeid=$this->commodel->get_listarry('student_fees','sfee_id',$wharray);
                foreach($sfeeid as $row1){
                	$sfee_id= $row1->sfee_id;
                }
	}
	if (empty ($sfee_id)){
		$this->session->set_flashdata('flash_data', 'Your fees is pending for reconcilation');
	}
	else{
	//get the value of spid,
        $spid=$this->commodel->get_listspfic1('student_fees','sfee_spid','sfee_id',$sfee_id)->sfee_spid;
	
	//get the record form student master basis of smid
        $this->sname =$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
        $this->semail =$this->commodel->get_listspfic1('student_master','sm_email','sm_id',$smid)->sm_email ;
        $this->smobile =$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$smid)->sm_mobile;
        $this->sgender =$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$smid)->sm_gender;
        $catid =$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$smid)->sm_category ;
        $this->scategoryn =$this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name ;

	//get the record for student entry exit on the basis of smid
	$this->srollno =$this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$smid)->senex_rollno;

	//get the record form student fees where sfees id feestype, payment mod reference number
	$this->fstatus=$this->commodel->get_listspfic1('student_fees','sfee_paymentmethod','sfee_id',$sfee_id)->sfee_paymentmethod;
        $this->refno=$this->commodel->get_listspfic1('student_fees','sfee_referenceno','sfee_id',$sfee_id)->sfee_referenceno;
        $this->ftype=$this->commodel->get_listspfic1('student_fees',' sfee_feename','sfee_id',$sfee_id)-> sfee_feename;
	$this->fdepositedate=$this->commodel->get_listspfic1('student_fees','sfee_depositdate','sfee_id',$sfee_id)->sfee_depositdate;
	$this->feeamount=$this->commodel->get_listspfic1('student_fees','sfee_feeamount','sfee_id',$sfee_id)->sfee_feeamount;

	//get the record form student program basis of spid
	$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_id',$spid)->sp_programid;
        $this->prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
        $this->branch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
       
	//get the record form fees master on the basis of prg id and semester gender category
	$this->semester=$this->commodel->get_listspfic1('student_program','sp_semester','sp_id',$spid)->sp_semester;
	$this->acadyear=$this->commodel->get_listspfic1('student_program','sp_acadyear','sp_id',$spid)->sp_acadyear;
	if($this->ftype == "semfee"){
	//put all the values in feesrec array
          $wharray = array('fm_programid' => $this->prgname, 'fm_semester' => $this->semester);
                $sarray = 'fm_head,fm_amount';
                $wgenr = array('All', $this->sgender);
                $wcateid = array('1', $catid);
                $this->db->select($sarray);
                $this->db->from('fees_master');
                $this->db->where_in('fm_gender',$wgenr);
                $this->db->where_in('fm_category',$wcateid);
                $this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
	}
        $this->load->library('pdf');
        $this->pdf->load_view('student/feesreceiptdw');
        $this->pdf->render();
        $this->pdf->stream("feesreceipt.pdf");
       // $this->pdf->stream("feesreceiptdw.pdf");
	}
   }

   // marks display to student
   public function marksrecord()
      {
        $userid = $this->session->userdata('id_user');
        $smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
        $whdata= array('smr_smid' => $smid);
        $stumarks = $this->commodel->get_listarry('student_marks','*',$whdata);
        $data['stumarks'] = $stumarks;  
             //print_r($this->result);
        $this->logger->write_logmessage("view"," View Student program and marks", "View Student program and marks");
        $this->logger->write_dblogmessage("view"," View Student program and marks", "View Student program and marks");
       $this->load->view('student/stu_semmarks',$data);
        //$this->load->view('student/studentmarks',$this->result);
       }

// grade display to student
   public function graderecord()
      {
        $userid = $this->session->userdata('id_user');
        $smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
        $whdata= array('smr_smid' => $smid);
        $stumarks = $this->commodel->get_listarry('student_marks','*',$whdata);
    $data['stumarks'] = $stumarks;  
      //  print_r($data['stumarks']);
        $this->logger->write_logmessage("view"," View Student program and marks", "View Student program and marks");
        $this->logger->write_dblogmessage("view"," View Student program and marks", "View Student program and marks");
        $this->load->view('student/stu_semgradecard',$data);
        //$this->load->view('student/studentmarks',$this->result);
       }


    public function marksrecorddw(){
        $userid = $this->session->userdata('id_user');
            $smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
            $whdata= array('smr_smid' => $smid);
            $stumarks = $this->commodel->get_listarry('student_marks','*',$whdata);
        $data['stumarks'] = $stumarks;  
            //  print_r($data['stumarks']);
            $this->logger->write_logmessage("view"," View Student program and marks", "View Student program and marks");
            $this->logger->write_dblogmessage("view"," View Student program and marks", "View Student program and marks");


        $this->load->libraries('pdf');
            $this->pdf->load_view('student/stu_semmarksdw',$data);
            $this->pdf->render();
            $this->pdf->stream("feesreceipt.pdf");
    }

    public function gradecarddw(){
        $userid = $this->session->userdata('id_user');
            $smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
            $whdata= array('smr_smid' => $smid);
            $stumarks = $this->commodel->get_listarry('student_marks','*',$whdata);
        $data['stumarks'] = $stumarks;  
            //  print_r($data['stumarks']);
            $this->logger->write_logmessage("view"," View Student program and marks", "View Student program and marks");
            $this->logger->write_dblogmessage("view"," View Student program and marks", "View Student program and marks");


        $this->load->library('pdf');
            $this->pdf->load_view('student/stu_semgradecarddw',$data);
            $this->pdf->render();
            $this->pdf->stream("feesreceipt.pdf");
    }


    public function subjectrecorddw(){
        $userid = $this->session->userdata('id_user');
        $smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
        //$sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$smid)->sp_semester;
        //$whdata= array('sp_smid' => $smid );//, 'sp_semester' => $sem);
        //$data['subres'] = $this->commodel->get_listarry('student_program','sp_id',$whdata);
        $stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$smid);
            $degree_id = $stud_prg_rec->row()->sp_programid;
            $noofsemester = sizeof($stud_prg_rec->result());
        $sp_id=$this->uri->segment(3);
        $whdata= array('sp_id' => $sp_id , 'sp_smid' => $smid );
        $data['subres'] = $this->commodel->get_listarry('student_program','*',$whdata);
        
        //print_r($data['subres']);die;
            $this->logger->write_logmessage("view"," View Student program and  subject", "View Student program and  subject");
            $this->logger->write_dblogmessage("view"," View Student program and  subject", "View Student program and  subject");
            
        $this->load->library('pdf');
            $this->pdf->load_view('student/subjectrecorddw',$data);
            $this->pdf->render();
            $this->pdf->stream("feesreceipt.pdf");
    }



    public function admitcard(){
        $suid=$this->session->userdata('id_user');
        //print_r($suid);
        $Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
        
        $acadyer = $this->usrmodel->getcurrentAcadYear();
        $data['name']=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
        
        //$this->enrono=$this->commodel->get_listspfic1("student_master","sm_enrollmentno","sm_userid",$suid)->sm_enrollmentno;
        //$this->rollno=$this->commodel->get_listspfic1("student_entry_exit","senex_rollno","senex_smid",$Stuid)->senex_rollno;
        
        $catid = $this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
        $data['caste'] = $this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;
        $data['rollno'] = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_id',$Stuid)->senex_rollno;
        
                $semestertype = $this->usrmodel->getcurrentSemester();
                $semesterrec = $this->stumodel->get_semester_no($Stuid,$acadyer);
            $semsize = sizeof($semesterrec);
        //$this->curresem = $semsize;
        //$getspid = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
            //$spstid = $this->commodel->get_listspficemore('student_program','sp_id',$getspid);
        $deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
        $prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
        $data['prgname'] = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
        $data['brname'] = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;

        $getspid = array('exsc_prgid' => $prgid, 'exsc_semester' => $semsize);
        $select = 'exsc_centerid,exsc_examname,exsc_examdatetime';
            $data['exam'] = $this->commodel->get_listspficemore('studentexam_schedule',$select,$getspid);
        
    
        $data['mname'] = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;       
        $data['fname'] = $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
        $data['gender'] = $this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
        
        //postal address detail
        $stupadd = $this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$Stuid)->spar_paddress;
        $stupcity=$this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$Stuid)->spar_pcity;
        //$this->ppost=$this->commodel->get_listspfic1('student_parent','spar_ppostoffice','spar_smid',$id)->spar_ppostoffice;
        //$this->pdist=$this->commodel->get_listspfic1('student_parent','spar_pdistrict','spar_smid',$id)->spar_pdistrict;
        $stupstat=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$Stuid)->spar_pstate;
        $stuppin=$this->commodel->get_listspfic1('student_parent','spar_ppincode','spar_smid',$Stuid)->spar_ppincode;

        $data['paddress'] = $stupadd.' , '.$stupcity.' , '.$stupstat.' , '.$stuppin;

        //get photo or sign
        $data['phresult'] = $this->commodel->get_listspfic1('student_master','sm_photo','sm_id',$Stuid)->sm_photo;
        $data['signresult'] = $this->commodel->get_listspfic1('student_master','sm_signature','sm_id',$Stuid)->sm_signature;
        $data['deptid'] = $deptid;
        $data['acadyer'] = $acadyer;    
        $data['Stuid'] = $Stuid;
        $data['prgid'] = $prgid;
        //$this->load->library('pdf');
            //$this->pdf->load_view('request/stu_admitcarddw',$data);
            //$this->pdf->render();
            //$this->pdf->stream("Admit Card.pdf"); 
            ///$temp = $this->load->view('request/stu_admitcarddw',$data, TRUE);
                //$dirpath = 'uploads/SLCMS/adminstudent_exam/'.$acadyer.'/admit_card/'.$deptid.'/'.$Stuid.'.pdf';

            //if (file_exists($dirpath)) {
                //$this->commodel->genpdf($temp,$dirpath);

            //}
            //else{
                //$this->session->set_flashdata('err_message','Your Admit Card Is Not Updated By Administrator.');
                //redirect('studenthome');
                //}
    
        $this->load->view('request/stu_admitcarddw',$data);
    }
/*******************************************************Download Link Code Start*************************************************************************/
	public function admissionformdw(){
		$userid = $this->session->userdata('id_user');
		$sm_id=$this->uri->segment(1);
		//get the latest smid if $sm_id is null
		if (empty ($sm_id)){
			$smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
               	 foreach($smid as $row1){
                	$sm_id= $row1->sm_id;
                	}
		}
		$smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
		//personnel detail get
		$this->pdetail = $this->commodel->get_listrow('student_master','sm_id',$smid)->result();
		
		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$smid)->spar_mothername;		
		$this->fname = $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;
		$this->ncid  = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		
		//address detail
		$this->adetail = $this->commodel->get_listrow('student_parent','spar_smid',$smid)->result();
		
		$cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$smid)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$cateid)->cat_name;

		//education detail
		$this->edetail = $this->commodel->get_listrow('student_education','sedu_smid',$smid)->result();

		//fees detail
		$this->fdetail = $this->commodel->get_listrow('student_fees','sfee_smid',$smid)->result();
		
		$this->load->library('pdf');
       		$this->pdf->load_view('student/admissionformdw');
        	$this->pdf->render();
        	$this->pdf->stream("admissionformdw.pdf");
	}

	public function examformdw(){
		$userid = $this->session->userdata('id_user');
		$sm_id=$this->uri->segment(2);
		//get the latest smid if $sm_id is null
		if (empty ($sm_id)){
			$smid = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;
               	 foreach($smid as $row1){
                	$sm_id= $row1->sm_id;
                	}
		}
		$sm_id = $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$userid)->sm_id;

		$spprgid  = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$sm_id)->sp_programid;
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$spprgid)->prg_name;

		$cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$sm_id)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$cateid)->cat_name;

		$stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$sm_id);
           	$degree_id = $stud_prg_rec->row()->sp_programid;
           	$noofsemester = sizeof($stud_prg_rec->result());
		$cacadyer = $this->usrmodel->getcurrentAcadYear();
		$data['cacadyer'] = $cacadyer;
        	$data['noofsemester'] = $noofsemester;

		//student parent and course detail
		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$sm_id)->spar_mothername;		
		$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$sm_id)->spar_fathername;

		//personnel detail get
		$this->pdetail = $this->commodel->get_listrow('student_master','sm_id',$sm_id)->result();
			
		//education detail
		$this->edetail = $this->commodel->get_listrow('student_education','sedu_smid',$sm_id)->result();

		//get semester rule, semester min credit max credit of a program
       	 	$wheredata = array('semcr_prgid' => $spprgid,'semcr_semester' =>  $noofsemester);
        	$selectfield = 'semcr_mincredit,semcr_maxcredit,semcr_semcpi';
        	$semrule = $this->commodel->get_listspficemore('semester_rule',$selectfield,$wheredata);

		//get subject/papers in a semester of a program from subject_semester
        	$wheredata1 = array('subsem_prgid' => $spprgid,'subsem_semester' => $noofsemester);
        	$selectfield1 = 'subsem_subid,subsem_subtype';
       	 	$semsubject =  $this->commodel->get_listspficemore('subject_semester',$selectfield1,$wheredata1);    
        
        	$subjectsem = array();
        	$compcr = 0;
        	$upsubdata = array();
        	$incrid = 1;
        	foreach($semsubject as $row)
        	{
            		$subid = $row->subsem_subid;
            		$subtype = $row->subsem_subtype; 
           		$substring = $subid."#".$subtype;
           		$subjectsem[] = $substring;
           		 if($subtype == "Compulsory")
            		{	
                		//$incrid = 1;
                		$subcr = $this->commodel->get_listrow('subject','sub_id',$subid)->row()->sub_ext1;
               	 		$compcr = $compcr + $subcr;
                
               	 		$upsubdata[] =  $subid;
                		$incrid = $incrid + 1;
            		}
        	}
        	$data['subjectsem']  = $subjectsem;

        	//get subject from subject table
        	$this->load->model("map_model", "mapmodel");
        	$data['subject_list'] = $this->mapmodel->getsubject();		

		$this->load->library('pdf');
       		$this->pdf->load_view('student/examformdw',$data);
        	$this->pdf->render();
        	$this->pdf->stream("examformdw.pdf");
	}
/*******************************************************Download Link Code END*************************************************************************/
 
}

