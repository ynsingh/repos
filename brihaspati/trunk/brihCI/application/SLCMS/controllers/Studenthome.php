<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studenthome.php
 * @author Sharad Singh (sharad23nov@yahoo.com) Student dashboard,Student semester registration,Student subject registration 
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author Manorama Pal (palseema30@gmail.com) Student List of selected program
 */
class Studenthome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
 	$this->load->model("common_model", "commodel");
        $this->load->model("user_model","usermodel");
        $this->load->model("student_model", "studentmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }

    public function index() {
        //echo "I am here";
	    $suid=$this->session->userdata('id_user');
        //echo $suid;

	    //set the student role in session
        //done by nks
	    //$data = [ 'id_role' => 3 ];
        //modified me
        $id_role = 3;
        $data['id_role'] = $id_role;
	    $this->session->set_userdata($data);
	    //get the student masterid
        $studmaster = $this->commodel->get_listrow('student_master','sm_userid',$suid);
        
        $stud_master = $studmaster->result();
        $stud_master1 = $studmaster->row();
        
	    if(!empty($stud_master)) {
            $stid = $stud_master1->sm_id;
            $stud_email = $stud_master1->sm_email;
            $stud_phone = $stud_master1->sm_mobile;
            $smid = $stud_master1->sm_userid;
            $fname = $stud_master1->sm_fname;
            $mname = $stud_master1->sm_mname;
            $lname = $stud_master1->sm_lname;
            $compname = $fname." ".$mname." ".$lname;
            $data['compname'] = $compname;
            $data['stud_email'] = $stud_email;
            $data['stud_phone'] = $stud_phone;

		    // get the name of dept

            $deptid = $this->commodel->get_depid('user_role_type',$suid,$id_role);
            $dept_id = $deptid->deptid;            
            $deptname = $this->commodel->get_listrow('Department','dept_id',$dept_id);
            $dept_name = $deptname->row()->dept_name;
            $data['dept_name'] = $dept_name;

            //get study center code.
            $sc_code = $stud_master1->sm_sccode;  
            $sc_name = $this->commodel->get_listrow('study_center','sc_code',$sc_code)->row()->sc_name;
            $data['sc_name'] = $sc_name;

            //get degree

            //check the registration in current academic session with semester ---
            //if not then ask for the semester registeration---
            //if yes then open registration form---------->pending

            $stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$stid);
            $degree_id = $stud_prg_rec->row()->sp_programid;
            
            //get current academic year
            $acadyear = $this->usermodel->getcurrentAcadYear();

            /* get total entry of a student in student program to find out for total no of semester 
             * in which he is registered.
             */
            $noofsemester = sizeof($stud_prg_rec->result());
            $degree_name = $this->commodel->get_listrow('program','prg_id',$degree_id)->row()->prg_name;
            $this->load->model("student_model", "studentmodel");           
            $data['degree_name'] = $degree_name;
            //get the value of current semester and academic year,semestertype(odd or even)

            $semestertype = $this->usermodel->getcurrentSemester();
        
            /* get the total semester count in an academic year,if semester is even,there may be two 
             * records in an academic year ,if not so ask for current semester registraion.
             * if semester id Odd there will be 1 records in an academic year otherwise ask for 
             * semester registration.
             */
            $semesterrec = $this->studentmodel->get_semester_no($stid,$acadyear);
            $semsize = sizeof($semesterrec);

            if($semestertype == "Odd")
            {
                if($semsize == 1)
                {
                    $semester = $noofsemester;
                }
                else
                {
                    $semester = $noofsemester + 1;
                    $semester = "Please register in the semester ".$semester;
                    //echo message for semester registration
                    // redirect('studenthome/studentsubject/');
                    // return;
                }
            }
            if($semestertype == "Even")
            {
                if($semsize == 2)
                {
                    $semester = $noofsemester;
                }
                else
                {
                    $semester = $noofsemester + 1;
                    $semester = "Please register in the semester ".$semester;
                    // redirect('studenthome/studentsubject/');
                    // return;

                }
            }

            


            //here is the logic of semester count will,if semester is even and entry is student program is one ...then semester registration will be done.
            //print_r(sizeof($semesterrec));
/*            if(sizeof($semesterrec) == 2)
            {
                foreach($semesterrec as $row)
                {
                    $semester = $row->sp_semester;
                }
            }
            else
            {
                foreach($semesterrec as $row)
                {
                    $semester = $row->sp_semester; 
                    if($semester!=0){
                    if($semres !=0)
                    {
                        $semres = fmod($semester,2);
                     
                        if($semres != 0)
                        { 
                            if($semestertype == "ODD")
                            {
                                $semester = $row->sp_semester;     
                            }    
                            else
                            {
                                $semester = "Please do the registration in semester ".$semester + 1 ;            
                            } 
                        }
                    }
                    }
                }
            }
*/
            $data['acadyear'] = $acadyear;
            $data['semester'] = $semester; 
            $data['stid'] = $stid;       

            //after filling form redierect to fees payment---
            //after payment---
            //check the student program table for subject for current academic year and semester
            //if subject exist
            //go to dash board
            //else go to subject selection page
            //select the subject and paper
            //update the student program table
            //send mail to student
            //go to dashboard
            
            //redirect('student/studenthome');

            /* get subject in current semester */
            
            $studsemsubject = $this->studentmodel->stud_sem_sub($stid,$acadyear,$semester); 
            //print_r($studsemsubject);
             
            //get sfee_spid(student program id) from student_program
            $stud_prg_id = $studsemsubject->row()->sp_id; 
            $subjectid1 = $studsemsubject->row()->sp_subid1;
            if($subjectid1 == 0)
            {
                redirect('studenthome/studentsubject/');
            }
            else
            {
                $subjectid2 = $studsemsubject->row()->sp_subid2;
                $subjectid3 = $studsemsubject->row()->sp_subid3;
                $subjectid4 = $studsemsubject->row()->sp_subid4;
                $subjectid5 = $studsemsubject->row()->sp_subid5;
                $subjectid6 = $studsemsubject->row()->sp_subid6;
                $subjectid7 = $studsemsubject->row()->sp_subid7;
                $subjectid8 = $studsemsubject->row()->sp_subid8;
                $subjectid9 = $studsemsubject->row()->sp_subid9;
                $subjectid10 = $studsemsubject->row()->sp_subid10;
            
            //get subject name 
                if($subjectid1 != 0)
                    $subject1 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid1)->sub_name; 
                if($subjectid2 != 0)
                    $subject2 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid2)->sub_name;
                if($subjectid3 != 0)
                    $subject3 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid3)->sub_name;
                if($subjectid4 != 0)
                    $subject4 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid4)->sub_name;
                if($subjectid5 != 0)
                    $subject5 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid5)->sub_name;
                if($subjectid6 != 0)
                    $subject6 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid6)->sub_name;
                if($subjectid7 != 0)
                    $subject7 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid7)->sub_name;
                if($subjectid8 != 0)
                    $subject8 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid8)->sub_name;
                if($subjectid9 != 0)
                    $subject9 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid9)->sub_name;
                if($subjectid10 != 0)
                    $subject10 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid10)->sub_name;
            
                $subject = $subject1;
                    if(!empty($subject2))
                        $subject = $subject.",".$subject2;
                    if(!empty($subject3))
                        $subject = $subject.",".$subject3;
                    if(!empty($subject4))
                        $subject = $subject.",<br>".$subject4;
                    if(!empty($subject5))
                        $subject = $subject.",".$subject5;
                    if(!empty($subject6))
                        $subject = $subject.",".$subject6 ;
                    if(!empty($subject7))
                        $subject = $subject.",<br>".$subject7;
                    if(!empty($subject8))
                        $subject = $subject.",".$subject8;
                    if(!empty($subject9))
                        $subject = $subject.",".$subject9;
                    if(!empty($subject10))
                        $subject = $subject.",<br>".$subject10;
                    //$subject;
        
                $data['subject'] = $subject;
            }
            
            $stud_par_rec = $this->commodel->get_listrow('student_parent','spar_smid',$stid);
            if((!empty($stud_par_rec->row()->spar_paddress)) && (!empty($stud_par_rec->row()->spar_pcity)) && (!empty($stud_par_rec->row()->spar_pdistrict)) && (!empty($stud_par_rec->row()->spar_pstate)) && (!empty($stud_par_rec->row()->spar_pcountry)) && (!empty($stud_par_rec->row()->spar_ppincode)))
            {
            $stud_address = $stud_par_rec->row()->spar_paddress;
            $stud_city = $stud_par_rec->row()->spar_pcity;
            $stud_dist = $stud_par_rec->row()->spar_pdistrict;
            $stud_stat= $stud_par_rec->row()->spar_pstate;
            $stud_count= $stud_par_rec->row()->spar_pcountry;
            $stud_pin= $stud_par_rec->row()->spar_ppincode;
            $student_address = $stud_address.", ".$stud_city."<br>".$stud_dist.", ".$stud_stat."<br>".$stud_count." - ".$stud_pin;
            $data['student_address'] = $student_address;
            }
            else
                $data['student_address'] = "Please fill the Address properly";
        
            $studprogrec = $this->studentmodel->get_student_program($stid);
            $data['studprogrec'] = $studprogrec;
            
            /* fee details */
            //get fee details of student ,if fee not deposited in a semester, ask to him for deposit.
            
            //get fee record where sfee_smid = $stid and sfee_spid = $stud_prg_id;
            $feedata = array('sfee_smid' => $stid, 'sfee_spid' => $stud_prg_id);
            $stud_fee_rec = $this->commodel->get_listspficemore1('student_fees',$feedata);
            //print_r(sizeof($stud_fee_rec));
            if(sizeof($stud_fee_rec) == 0 )
            {
                redirect('request/stufeesdetail');
                
                 $fees = "Please deposit fee of ".$semester;
                $data['fees'] = $fees;
                $data['stud_fee_rec'] = $stud_fee_rec;
            }
            else
                $data['stud_fee_rec'] = $stud_fee_rec;
            
            $this->load->view('student/studenthome',$data);
	    }
        else
        {
		    $this->session->set_flashdata('flash_data', 'You do not have student role in this system!');
           	    redirect('welcome');
	    }

     // $this->load->view('student/studenthome');
    }
    
    /* Student subject registration in semester */    

    public function studentsubject()
    {
        $suid = $this->session->userdata('id_user');
        $username = $this->session->userdata('username');   
        $studmaster = $this->commodel->get_listrow('student_master','sm_userid',$suid);

        $smid= $this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$suid)->sm_id;
	$sturollno = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$smid);
       
	 $stud_master = $studmaster->result();
         $stud_master1 = $studmaster->row();
        $semtotalcr=0;
        //get student details        
        if(!empty($stud_master)) {
            $stid = $stud_master1->sm_id;
            $stud_email = $stud_master1->sm_email;
            $stud_phone = $stud_master1->sm_mobile;
            $smid = $stud_master1->sm_userid;
            $fname = $stud_master1->sm_fname;
            $mname = $stud_master1->sm_mname;
            $lname = $stud_master1->sm_lname;
            $compname = $fname." ".$mname." ".$lname;
            $enrollno = $stud_master1->sm_enrollmentno;       
            //$rollno = $sturollno->senex_rollno;       
            $data['compname'] = $compname;
            $data['enrollno'] = $enrollno;
            //$data['rollno'] = $rollno;
            $data['stud_email'] = $stud_email;
            $data['stud_phone'] = $stud_phone;
        }
        //get student academic year along with semester
        $studprogram = $this->commodel->get_listrow('student_program','sp_smid',$stid)->result();
        foreach($studprogram as $prgrec)
        {
            $semes = $prgrec->sp_semester;
            $acad = $prgrec->sp_acadyear;
            $rid = $prgrec->sp_id;
            $prg_id = $prgrec->sp_programid;
        }
        //get program name
        $prg_name = $this->commodel->get_listrow('program','prg_id',$prg_id)->row()->prg_name;
        $semester = $semes;
        $acadyear = $acad;    
        $prg_id = $prg_id;
        $data['acadyear'] = $acadyear;
        $data['semester'] = $semester;
        $data['rid'] = $rid;
        $data['prg_name'] = $prg_name;

        //get semester rule, semester min credit max credit of a program
        $wheredata = array('semcr_prgid' => $prg_id,'semcr_semester' => $semester);
        $selectfield = 'semcr_mincredit,semcr_maxcredit,semcr_semcpi';
        $semrule = $this->commodel->get_listspficemore('semester_rule',$selectfield,$wheredata);
        foreach($semrule as $row)
        {
            $semmincredit = $row->semcr_mincredit;
            $semmaxcredit = $row->semcr_maxcredit;
            $semcpi = $row->semcr_semcpi;
        }
        $data['semmincredit'] = $semmincredit;
        $data['semmaxcredit'] = $semmaxcredit;
        $data['semcpi'] = $semcpi;
        //get subject/papers in a semester of a program from subject_semester
        $wheredata1 = array('subsem_prgid' => $prg_id,'subsem_semester' => $semester);
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
            //$updatedata = array('sp_subid'.$incrid => $subid);
            //$incrid = $incrid + 1;
        }
        //print_r($upsubdata);
        //echo $sompcr;
        $data['subjectsem']  = $subjectsem;

        //get subject from subject table
        $this->load->model("map_model", "mapmodel");
        $data['subject_list'] = $this->mapmodel->getsubject();

        //update subject of student
        if($_POST)
        {   $elecsubjectcr_total = 0;
            if (!empty($this->input->post('elecsubject'))) 
            {
                foreach ($this->input->post('elecsubject') as $key => $val) 
                {
                    $elecdata[] = array('elecsubject' => $_POST['elecsubject'][$key]);
                }
//                $elecsubjectcr_total = 0;
//            }
            foreach ($elecdata as $item) 
            {    
                $elecsubid = $item['elecsubject'];
                $elecsubjectcr = $this->commodel->get_listrow('subject','sub_id',$elecsubid)->row()->sub_ext1;
                $elecsubjectcr_total = $elecsubjectcr_total + $elecsubjectcr;
                $upsubdata[] = $elecsubid;
            }
            }
            $updatesubject = array();
            for($j = 0;$j < sizeof($upsubdata); $j++)
            {
                $t = $j+1; 
                $updatesubject['sp_subid'.$t] =  $upsubdata[$j] ;
            }
//            print_r($updatesubject);

            //if total credit (compulsory + elective) in a semester should be lies bewtween min and max credit.
//            echo $semmincredit;
//            echo $semmaxcredit;
            echo $semtotalcr = $compcr + $elecsubjectcr_total;

            echo $rid;
            if(sizeof($updatesubject)<=10)
            {
                if(($semtotalcr >= $semmincredit) && ($semtotalcr <= $semmaxcredit)) 
                {
                    $updatesemsubject = $this->commodel->updaterec('student_program',$updatesubject,'sp_id',$rid);        
    
                    if(!$updatesemsubject)
                    {
                        $this->db->trans_rollback();
                        $this->session->set_flashdata("warning",'Error in adding subject in semester '.$semester." in acdemic year " .$acadyear);
                        $this->logger->write_dblogmessage("update","Error in adding subject in semester ".$semester." in acdemic year " .$acadyear1, ' by '.$username);
                        $this->logger->write_logmessage("update","Error in adding subject in semester ".$semester." in acdemic year " .$acadyear1, ' by '.$username);
                        redirect('studenthome/index');

                    }
                    else
                    {
                        $this->db->trans_complete();
                        $this->logger->write_logmessage("update","Subject added in semester ".$semester." in acdemic year " .$acadyear, ' by '.$username);
                        $this->logger->write_dblogmessage("update","Subject added in semester ".$semester." in acdemic year " .$acadyear, ' by '.$username);
                        $this->session->set_flashdata("success", " Subject updated successfully");
                        redirect('studenthome/index');
                    }
                }
                else
                {
                    //echo "NOk";
                    $this->session->set_flashdata("warning", "Total Credit must be between ".$semmincredit. "and". $semmaxcredit);
                }
            }
            else
            {
                $this->session->set_flashdata("warning", "Maximum 10 subject is allowed in a semester,contact to the department head");
            }
                
        } 
                
        $this->load->view('student/studentsubject',$data);    
    }
    
    /* get student list acording to program and campus*/
    
    public function studentlist() {
        //get the value of current semester and academic year
        
        $acadyear = $this->usermodel->getcurrentAcadYear();
        $datarec['academicyear']=$acadyear;
        $semestertype = $this->usermodel->getcurrentSemester();
        $datarec['semester']=$semestertype;
        
        //get program list of current academic year  and semester 
        
        $uid=$this->session->userdata('id_user');
        $this->campusid=$this->commodel->get_listspfic1('user_role_type','scid','userid',$uid)->scid;
        $selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        $data=array(
            'pstp_scid' =>$this->campusid,
            'pstp_teachid' => $uid,
            'pstp_acadyear' => $acadyear,
    //        'pstp_sem'    => $semestertype
        );
        $this->prgsublist=$this->commodel->get_listspficemore('program_subject_teacher',$selectfield,$data);
        $datarec['prgsublist']=$this->prgsublist;
        if(isset($_POST)){
            /* without search get all student list according to selected program*/
            
            $prgname=$this->input->post('programname',TRUE);
            if(!empty($prgname)){
                $datarec['selprg_name']=$prgname;
            }
            else{
                $datarec['selprg_name']= "";
            }
            $sfield=array('sp_smid','sp_deptid','sp_subid1','sp_subid2','sp_subid3','sp_subid4','sp_subid5','sp_subid6','sp_subid7','sp_subid8','sp_subid9','sp_subid10');
            $this->campucode=$this->commodel->get_listspfic1('study_center','sc_code','sc_id',$this->campusid)->sc_code;
            $datarec['ccode']=$this->campucode;
            $stdntdata=array(
                'sp_sccode'   =>$this->campucode,
                'sp_programid' =>$prgname,
                'sp_acadyear' =>$acadyear,
     //           'sp_semester'  =>  $semestertype
            );
       
            $filter = $this->input->post('filter');
            $field  = $this->input->post('field');
            $search = $this->input->post('search');
            $prgname2=$this->input->post('prgname',TRUE);
            if(!empty($prgname2)){
                $datarec['filprg_name']=$prgname2;
                
            }
            else{
                $datarec['filprg_name']= "";
            }
            if (isset($filter) && !empty($search)) {
                $stdntdata2=array(
                'sp_sccode'   =>$this->campucode,
                'sp_programid' =>$prgname2,
                'sp_acadyear' =>$acadyear,
      //          'sp_semester'  =>$semestertype
                );
                $datarec['studentdetail'] = $this->studentmodel->getStudentsWhereLike($field, $search,$stdntdata2);
            }
            else{
                /* without search*/ 
                $stud_prg_rec = $this->commodel->get_listspficemore('student_program',$sfield,$stdntdata);
                $datarec['studentdetail'] = $stud_prg_rec;
            }
        
        }//ifpost
        $this->load->view('student/studentlist',$datarec);
    }
		 
}
