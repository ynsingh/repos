<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studenthome.php
 * @author Sharad Singh
 * @author Nagendra Kumar Singh
 */
class Studenthome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
 	$this->load->model("common_model", "commodel");
    $this->load->model("user_model","usermodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }

    public function index() {
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

            //echo "Dpart name-->".$dept_name;

            //get study center code.
            $sc_code = $stud_master1->sm_sccode;  
            $sc_name = $this->commodel->get_listrow('study_center','sc_code',$sc_code)->row()->sc_name;
            $data['sc_name'] = $sc_name;

            //echo "  Study cent-->".$sc_name;

            //get degree

            $stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$stid);
            $degree_id = $stud_prg_rec->row()->sp_programid;
            $degree_name = $this->commodel->get_listrow('program','prg_id',$degree_id)->row()->prg_name;
            $this->load->model("student_model", "studentmodel");           
            $data['degree_name'] = $degree_name;
             //get the value of current semester and academic year
/*            $currentyear =  date('Y');
            $nextyear = $currentyear + 1;
            $acadyear = $currentyear."-".$nextyear;
            $semester = $stud_prg_rec->row()->sp_semester;
            $data['semester'] = $semester;
*/
            $acadyear = $this->usermodel->getcurrentAcadYear();
            $semestertype = $this->usermodel->getcurrentSemester();
            //echo $semestertype;
            //select student semester detail in an acdemic year
            $semesterrec = $this->studentmodel->get_semester_no($stid,$acadyear);


            //here is the logic of semester count will,if semester is even and entry is student program is one ...then semester registration will be done.
            //print_r(sizeof($semesterrec));
            if(sizeof($semesterrec) == 2)
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
            $data['acadyear'] = $acadyear;
            $data['semester'] = $semester; 
            $data['stid'] = $stid;       

            //check the registration in current academic session with semester ---
            //if not then ask for the semester registeration---
            //if yes then open registration form---
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

            //get subject id
            
            $subjectid1 = $stud_prg_rec->row()->sp_subid1;
            if($subjectid1 == 0)
            {
                $data['submsg'] = "Please fillup the subject of current semester.";
            }
            else{
    
            $subjectid2 = $stud_prg_rec->row()->sp_subid2;
            $subjectid3 = $stud_prg_rec->row()->sp_subid3;
            $subjectid4 = $stud_prg_rec->row()->sp_subid4;
            $subjectid5 = $stud_prg_rec->row()->sp_subid5;
            $subjectid6 = $stud_prg_rec->row()->sp_subid6;
            $subjectid7 = $stud_prg_rec->row()->sp_subid7;
            $subjectid8 = $stud_prg_rec->row()->sp_subid8;
            $subjectid9 = $stud_prg_rec->row()->sp_subid9;
            $subjectid10 = $stud_prg_rec->row()->sp_subid10;
            
            //get subject name 
            //$subject1 =  $this->commodel->get_subjectname($subjectid1)->sub_name;
            $subject1 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid1)->sub_name; 
            $subject2 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid2)->sub_name;
            $subject3 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid3)->sub_name;
            if($subjectid4 != 0)
            $subject4 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid4)->sub_name;;
            if($subjectid5 != 0)
            $subject5 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid5)->sub_name;;
            if($subjectid6 != 0)
            $subject6 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid6)->sub_name;;
            if($subjectid7 != 0)
            $subject7 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid7)->sub_name;;
            if($subjectid8 != 0)
            $subject8 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid8)->sub_name;;
            if($subjectid9 != 0)
            $subject9 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid9)->sub_name;
            if($subjectid10 != 0)
            $subject10 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subjectid10)->sub_name;;
            
            $subject = $subject1;
            if(!empty($subject2))
                $subject = $subject.",".$subject2;
            if(!empty($subject3))
                $subject = $subject.",".$subject3;
            if(!empty($subject4))
                $subject = $subject.",".$subject4;
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

    public function studentsubject($studparam)
    {
        
        $stid = $this->uri->segment(3);
        $acadyear = $this->uri->segment(4);
        $semester = $this->uri->segment(5);

        //get student record 
        $stud_mast_rec = $this->commodel->get_listrow('student_master','sm_id',$stid);        
        $fname = $stud_mast_rec->row()->sm_fname;
        $mname = $stud_mast_rec->row()->sm_mname;
        $lname = $stud_mast_rec->row()->sm_lname;
        $enrollno = $stud_mast_rec->row()->sm_enrollmentno;       
        $rollno = $stud_mast_rec->row()->sm_rollno;       
        $compname = $fname." ".$mname." ".$lname;

        $data['compname'] = $compname;
        $data['enrollno'] = $enrollno;
        $data['rollno'] = $rollno;
        
 
        //echo $studparam;
        //echo "I m here";
        $student_id   ; 
        //redirect('student/studentsubject');
        $this->load->view('student/studentsubject',$data);    
    } 
}
