<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studenthome.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author Sharad Singh
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
             
            
            $subjectid1 = $studsemsubject->row()->sp_subid1;
            if($subjectid1 == 0)
            {
               // $this->load->view('student/studentsubject',$data);
                redirect('studenthome/studentsubject/');
                //$data['submsg'] = "Please fillup the subject of current semester.";
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
            //$subject1 =  $this->commodel->get_subjectname($subjectid1)->sub_name;
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
                        $subject = $subject.",".$subject10;
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
        
           // $this->load->model("student_model", "studentmodel");
            $studprogrec = $this->studentmodel->get_student_program($stid);
            //print_r($studprogrec);
            $data['studprogrec'] = $studprogrec;
            $feearray = array();
            $this->load->view('student/studenthome',$data);
	    }
	    	//check the registration in current academic session with semester
	    //if not then ask for the semester registeration
	    //if yes then open registration form
	    //after filling form redierect to fees payment
	    //after payment
	    //check the student program table for subject for current academic year and semester
	    //if subject exist
	    //go to dash board
	    //else go to subject selection page
	    //select the subject and paper
	    //update the student program table
	    //send mail to student
	    //go to dashboard
	  //  }else{
	//	$this->session->set_flashdata('flash_data', 'You do not have student role in this system!');
          //      redirect('welcome');
	   // }
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
        
        $stud_master = $studmaster->result();
        $stud_master1 = $studmaster->row();
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
            $rollno = $stud_master1->sm_rollno;       
            $data['compname'] = $compname;
            $data['enrollno'] = $enrollno;
            $data['rollno'] = $rollno;
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
        }
        //echo "Rid-->".$rid;
        $semester = $semes;
        $acadyear = $acad;    
        $data['acadyear'] = $acadyear;
        $data['semester'] = $semester;
        $data['rid'] = $rid;
    
        //get subject from subject table
        $this->load->model("map_model", "mapmodel");
        $data['subject_list'] = $this->mapmodel->getsubject();
        $this->form_validation->set_rules('subjectlist1','Subject1','trim|xss_clean|required');
        $this->form_validation->set_rules('subjectlist2','Subject2','trim|xss_clean|required');
        $this->form_validation->set_rules('subjectlist3','Subject3','trim|xss_clean|required');
        $this->form_validation->set_rules('subjectlist4','Subject4','trim|xss_clean');
        $this->form_validation->set_rules('subjectlist5','Subject5','trim|xss_clean');
        $this->form_validation->set_rules('subjectlist6','Subject6','trim|xss_clean');
        $this->form_validation->set_rules('subjectlist7','Subject7','trim|xss_clean');
        $this->form_validation->set_rules('subjectlist8','Subject8','trim|xss_clean');
        $this->form_validation->set_rules('subjectlist9','Subject9','trim|xss_clean');
        $this->form_validation->set_rules('subjectlist10','Subject10','trim|xss_clean');
        //update subject of student
        if($_POST)
        {
            $sub1 = $this->input->post('subjectlist1', TRUE);
            $sub2 = $this->input->post('subjectlist2', TRUE);
            $sub3 = $this->input->post('subjectlist3', TRUE);
            $sub4 = $this->input->post('subjectlist4', TRUE);
            $sub5 = $this->input->post('subjectlist5', TRUE);
            $sub6 = $this->input->post('subjectlist6', TRUE);
            $sub7 = $this->input->post('subjectlist7', TRUE);
            $sub8 = $this->input->post('subjectlist8', TRUE);
            $sub9 = $this->input->post('subjectlist9', TRUE);
            $sub10 = $this->input->post('subjectlist10', TRUE);
            $datasub1 = explode('#',$sub1);    
            if(sizeof($datasub1)==1)
                $subt1 = 0;
            else
                $subt1 = $datasub1[1];
            $datasub2 = explode('#',$sub2);    
            if(sizeof($datasub2)==1)
                $subt2 = 0;
            else
                $subt2 = $datasub2[1];
            $datasub3 = explode('#',$sub3);    
            if(sizeof($datasub3)==1)
                $subt3 = 0;
            else
                $subt3 = $datasub3[1];

            $datasub4 = explode('#',$sub4);    
            if(sizeof($datasub4)==1)
                $subt4 = 0;
            else
                $subt4 = $datasub4[1];

            $datasub5 = explode('#',$sub5);    
            if(sizeof($datasub5)==1)
                $subt5 = 0;
            else
                $subt5 = $datasub5[1];

            $datasub6 = explode('#',$sub6);    
            if(sizeof($datasub6)==1)
                $subt6 = 0;
            else
                $subt6 = $datasub6[1];

            $datasub7 = explode('#',$sub7);    
            if(sizeof($datasub7)==1)
                $subt7 = 0;
            else
                $subt7 = $datasub7[1];

            $datasub8 = explode('#',$sub8);    
            if(sizeof($datasub8)==1)
                $subt8 = 0;
            else
                $subt8 = $datasub8[1];

            $datasub9 = explode('#',$sub9);    
            if(sizeof($datasub9)==1)
                $subt9 = 0;
            else
                $subt9 = $datasub9[1];

            $datasub10 = explode('#',$sub10);    
            if(sizeof($datasub10)==1)
                $subt10 = 0;
            else
                $subt10 = $datasub10[1];

            if ($this->form_validation->run() == TRUE)
            {
                $update_subject = array(
                'sp_subid1' => $subt1,
                'sp_subid2' => $subt2,
                'sp_subid3' => $subt3,
                'sp_subid4' => $subt4,
                'sp_subid5' => $subt5,
                'sp_subid6' => $subt6,
                'sp_subid7' => $subt7,
                'sp_subid8' => $subt8,
                'sp_subid9' => $subt9,
                'sp_subid10' => $subt10,
                );
                //update subject in a semester
                $updatesubject = $this->commodel->updaterec('student_program', $update_subject,'sp_id',$rid);               

                if(!$updatesubject)
                {
                    $this->db->trans_rollback();
                    $this->session->set_flashdata('Error in adding subject in semester '.$semester." in acdemic year " .$acadyear, 'error');
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
            'pstp_sem'    => $semestertype
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
                'sp_semester'  =>  $semestertype
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
                'sp_semester'  =>$semestertype
                );
                $datarec['studentdetail'] = $this->studentmodel->getStudentsWhereLike($field, $search,$stdntdata2);
            }
            else{
                /* without search*/ 
                $stud_prg_rec = $this->commodel->get_listspficemore('student_program',$sfield,$stdntdata);
                $datarec['studentdetail']=$stud_prg_rec;
            }
        
        }//ifpost
        $this->load->view('student/studentlist',$datarec);
    }
		 
}
