<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name Attendencereport.php
 * @author Sharad Singh (sharad23nov@yahoo.com)  
 */
class Attendencereport extends CI_Controller
{

    function __construct() 
    {
        parent::__construct();
        $this->load->model("common_model", "commodel");
        $this->load->model("user_model","usermodel");
        $this->load->model("student_model", "studentmodel");
        $this->load->model("map_model", "mapmodel");
        $this->load->model("login_model", "loginmodel");
        if(empty($this->session->userdata('id_user'))) 
        {
            $this->session->set_flashdata('err_message', 'You don\'t have access!');
            redirect('welcome');
        }
    }
    
    //consolidated attendance report of student in a program

    public function attendencereport()
    {
        $uid = $this->session->userdata('id_user');
        $uname = $this->session->userdata('username');
        //get current academic year
        $acadyear = $this->usermodel->getcurrentAcadYear(); 
        $data['academicyear']=$acadyear;

        //get program list of program,subject,paper and semester in program_subject_teacher
        $selectfield=array('prg_id','prg_name','prg_branch');
        $prgsublist = $this->commodel->get_distinctrecord('program',$selectfield,'');
        $data['prgsublist']=$prgsublist;

        if(isset($_POST)){
            $sem = $this->input->post('semester',TRUE);
            $prgid = $this->input->post('program_branch',TRUE);
            
            //echo "PrgId --->".$prgid. "\n";
            //echo "Sem --->".$sem. "\n";
            //echo "<br>";
            //$subjectid = $this->input->post('subjectname',TRUE);
            //$paperid = $this->input->post('papername',TRUE);
            $search = $this->input->post('search');

            //get all subject/paper in a program and semester in a academic year.

            $selectsubjectpaper = array('pstp_subid','pstp_papid');
            $wheresubjectpaper = array('pstp_prgid' => $prgid,'pstp_sem' => $sem,'pstp_acadyear' => $acadyear);
            $prgsubpap = $this->commodel->get_listspficemore('program_subject_teacher',$selectsubjectpaper,$wheresubjectpaper);
            $data['prgsubpap'] = $prgsubpap;
            //print_r($prgsubpap);
            
            $selectfield=array('satd_adate');
            $data1 = array(
                'satd_prgid' => $prgid,
                'satd_sem' => $sem,
                //'satd_subid' => $subjectid,
                //'satd_papid' => $paperid
            );
            $data['sem'] = $sem;
            $data['prgid'] = $prgid;
            
            //$data['subjectid'] = $subjectid;
            //$data['paperid'] = $paperid;
            $attendencedate = $this->commodel->get_distinctrecord('student_attendance',$selectfield,$data1);
            //get academic session information
            $selectsessiondata = array('sed_sessionsdate','sed_sessionedate');
            $wheresession = array('sed_acadyear' => $acadyear, 'sed_sem' => $sem);
            $sessiondata = $this->commodel->get_distinctrecord('set_date',$selectsessiondata,$wheresession);
            $sdate = "";
            $edate = "";
            foreach($sessiondata as $row)
            {
                $sdate = $row->sed_sessionsdate;
                $edate = $row->sed_sessionedate;
            }
            
            $startdate = date('Y-m-d',strtotime($sdate));
            $enddate = date('Y-m-d',strtotime($edate));

/*
            $startdate = date('Y-m-d',strtotime('2017-07-01'));
            $enddate = date('Y-m-d',strtotime('2017-09-29'));
*/
            $data['startdate'] = $startdate;
            $data['enddate'] = $enddate;
            
            //print_r($rec);

            $attenddate = array();
/*            foreach($attendencedate as $row)
            {
                $attend_date = $row->satd_adate;
                if ($attend_date >= $startdate && $attend_date <= $enddate)
                {
                    $attenddate[] = $attend_date;
                }
            }
            //print_r($attenddate);
            $data['attenddate'] =$attenddate;
            
            //get list of all student according to program,semester,subject,paper and academic year  
            if(!empty($whereau)){ 
                $wheredata = "((sp_programid = $prgid) AND (sp_semester = $sem) AND (sp_acadyear = '$acadyear') AND ((sp_subid1 = $subjectid) OR (sp_subid2 = $subjectid) OR (sp_subid3 = $subjectid) OR (sp_subid4 = $subjectid) OR (sp_subid5 = $subjectid) OR (sp_subid6 = $subjectid) OR (sp_subid7 = $subjectid) OR (sp_subid8 = $subjectid) OR (sp_subid9 = $subjectid) OR (sp_subid10 = $subjectid)))";
                $students_smid = array('sp_smid');
                $stud_prg_data = $this->commodel->get_listspficemore('student_program',$students_smid,$wheredata);
                $data['stud_prg_data'] = $stud_prg_data;
            }
*/
            //print_r(sizeof($stud_prg_data));
        }
        $this->load->view('attendencereport',$data);
    }    

    //get all semester in selected program
    
    public function semester_get(){

        $prgid = $this->input->post('program_branch');

        //$uid=$this->session->userdata('id_user');
        //get program list of program,subject,paper and semester in program_subject_teacher
        $selectfield=array('pstp_sem');
        $data=array(
            'pstp_prgid'    =>$prgid
        );
        $sem = $this->commodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);

        //$sem  = $this->commodel->get_listspfic2('program_subject_teacher','','pstp_sem','pstp_prgid',$prgid,'pstp_sem');
        echo "<select name='semester' id='semester'>";
            echo "<option selected='true' disabled>"."Semester"."</option>";
        foreach($sem as $datas):
             echo "<option  value='$datas->pstp_sem'>"."$datas->pstp_sem"."</option>";
        endforeach;
        echo "</select>";
    }

    //get all subjects in a semeter of a program
    
    public function subject_get(){
        $prgid = $this->input->post('prgbrnch');
        $parts = explode(',',$prgid);
        $prg_id = $parts[0]; 
        $prg_sem = $parts[1];
    
        $selectfield = array('pstp_subid');
        $data = array(
            'pstp_prgid' => $prgid,
            'pstp_sem'  =>$prg_sem
        );
        $subject = $this->commodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);
        foreach($subject as $data){
            $data->pstp_subid;
            $subname = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$data->pstp_subid)->sub_name;
        }
        echo "<select name='subjectname' class='subjectname'>";
        echo "<option selected='true' disabled>"."Subject Name"."</option>";
        foreach($subject as $datas){
            $datas->pstp_subid;    
            $subname1 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$datas->pstp_subid)->sub_name;
            echo "<br>";
             echo "<option id='subjectname' value='$datas->pstp_subid'>"."$subname1"."</option>";
        }
        echo "</select>";
    }

    //get all papers alongwith with subject and program.

    public function paper_get(){
        $sub = $this->input->post('subjectname');
        $parts = explode(',',$sub);
        $prg_id = $parts[0]; 
        $prg_sem = $parts[1];
        $sub_id = $parts[2];
        
        //get program list of program,subject,paper and semester in program_subject_teacher

            $selectfield=array('pstp_papid');
            $data = array(
            'pstp_sem' => $prg_sem,
            'pstp_subid' => $sub_id,
            'pstp_prgid' => $prg_id
            );
        $paper = $this->commodel->get_distinctrecord('program_subject_teacher',$selectfield,$data);

        $selectfield=array('subp_name','subp_id');
        echo "<select name='papername'>";
        echo "<option selected='true' disabled>"."Paper Name"."</option>";
        foreach($paper as $datas):
            $datas->pstp_papid;
            $papname1 = $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$datas->pstp_papid)->subp_name;
            echo "<option id='papername' value='$datas->pstp_papid'>"."$papname1"."</option>";
        endforeach;
                
        echo "</select>";

    }
    public function facultyattendance()
    {
        $uid = $this->session->userdata('id_user');
        $uname = $this->session->userdata('username');

        //get current academic year

        $acadyear = $this->usermodel->getcurrentAcadYear();
        $data['academicyear']=$acadyear;

        //get program list of program,subject,paper and semester in program_subject_teacher

        $selectfield=array('prg_id','prg_name','prg_branch');
        $prgsublist = $this->commodel->get_distinctrecord('program',$selectfield,'');
        $data['prgsublist']=$prgsublist;

        if(isset($_POST)){
            $sem = $this->input->post('semester',TRUE);
            $prgid = $this->input->post('program_branch',TRUE);

            //get all subject/paper along with teacher in a program and semester in a academic year.

            $selectsubjectpaper = array('pstp_subid','pstp_papid','pstp_teachid');
            $wheresubjectpaper = array('pstp_prgid' => $prgid,'pstp_sem' => $sem,'pstp_acadyear' => $acadyear);
            $prgsubpap = $this->commodel->get_listspficemore('program_subject_teacher',$selectsubjectpaper,$wheresubjectpaper);
            $data['prgsubpap'] = $prgsubpap;
            //print_r($prgsubpap);
            $selectfield=array('satd_adate');
            $data1 = array(
                'satd_prgid' => $prgid,
                'satd_sem' => $sem,
                //'satd_subid' => $subjectid,
                //'satd_papid' => $paperid
            );
            $data['sem'] = $sem;
            $data['prgid'] = $prgid;
            $attendencedate = $this->commodel->get_distinctrecord('student_attendance',$selectfield,$data1);
            //get academic session information
            $selectsessiondata = array('sed_sessionsdate','sed_sessionedate');
            $wheresession = array('sed_acadyear' => $acadyear, 'sed_sem' => $sem);
            $sessiondata = $this->commodel->get_distinctrecord('set_date',$selectsessiondata,$wheresession);
            $sdate = "";
            $edate = "";
            foreach($sessiondata as $row)
            {
                $sdate = $row->sed_sessionsdate;
                $edate = $row->sed_sessionedate;
            }

            $startdate = date('Y-m-d',strtotime($sdate));
            $enddate = date('Y-m-d',strtotime($edate));
            $data['startdate'] = $startdate;
            $data['enddate'] = $enddate;

            //print_r($rec);

            $attenddate = array();
        }
        $this->load->view('attendance/facultyattendance',$data);
    }

}
