<?php

/* 
 * @name Map.php
 * @author Nagendra Kumar Singh(Nksinghiitk@gmail.com)
 * @author Manorama Pal(palseema30@gmail.com)
 * @author Om Prakash (omprakashkgp@gmail.com) Map Subject and Paper with Teacher  
 * @author Kishore kr Shukla (kishore.shukla@gmail.com) Map user with Role.
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Map extends CI_Controller
{
    function __construct() {
        parent::__construct();
        /*Loading model calsses*/
        $this->load->model('Dependrop_model',"depmodel");
        $this->load->model('Map_model',"mapmodel");
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"loginmodel"); 
	$this->load->model('SIS_model'); 
        
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
        
    // $this->load->view('map/viewscprgseat');
        
    }
    
    /**This function is used for view details of study center, with program seats */
    
    public function viewscprgseat(){
       
        $data['records']= $this->mapmodel->get_seat_program_studycenter();
        $this->load->view('map/viewscprgseat',$data);
        $this->logger->write_logmessage("view"," Map study center program with seat", "view details...");
    
    }
    
    /** This function is for map study center with program seat */
     
	public function mapscprgseat(){
       
        $data['campus'] = $this->mapmodel->get_Campus();
        $data['program'] =$this->mapmodel->get_Programlist();
        if(isset($_POST['mapscprgseat'])) {

            /*Form Validation*/
            $this->form_validation->set_rules('campus','Campus','trim|required|callback_ischeck');
            $this->form_validation->set_rules('program','Program','trim|required|callback_ischeck');
            $this->form_validation->set_rules('seatno','Number of Seat','trim|required|is_numeric|callback_ischeck|callback_check_prgseat');
            $this->form_validation->set_rules('gender','Gender','trim|required|callback_ischeck');
            $this->form_validation->set_rules('academicyear','AcademicYear','trim|required|callback_ischeck');
            $this->form_validation->set_rules('semester','Semester','trim|required');
        
            if($this->form_validation->run() == TRUE)
            {  
                //echo "this is prgid============";
                $program = $this->input->post('program', TRUE);
                $campus = $this->input->post('campus', TRUE);
                $gender = $this->input->post('gender', TRUE);
                $seatno = $this->input->post('seatno', TRUE);
                $academicyear= $this->input->post('academicyear', TRUE);
                $semester = $this->input->post('semester', TRUE);
            
                $data_prg=explode('#',$program);
                $newcampus=explode('#',$campus);
                $data = array(
                    'spsc_prg_id'=>$data_prg[0],
                    'spsc_sc_code'=>$newcampus[0],                   
                    'spsc_gender'=>$gender,
                    'spsc_totalseat'=>$seatno,
                    'spsc_acadyear'=>$academicyear,
                    'spsc_sem'=>$semester,
                    'spsc_creatorid'=>$this->session->userdata('username'),
                    'spsc_createdate'=>date('y-m-d'),
                    'spsc_modifierid'=>$this->session->userdata('username'),
                    'spsc_modifydate'=>date('y-m-d')
                );
           
                $mapscprg=$this->commodel->insertrec('seat_program_studycenter', $data);
                if(! $mapscprg )
                {
                    $this->logger->write_logmessage("error","Error  in maping study center with program seat", $data_campus[1].$data_prg[1].$$data_prg[2]);
                    $this->logger->write_dblogmessage("error","Error  in maping study center with program seat", $data_campus[1].$data_prg[1].$$data_prg[2]);
                    log_message('debug', ' Problem in maping studycenter with program seat' . $data_campus[1].$data_prg[1].$$data_prg[2] );
                    $this->session->set_flashdata('err_message','Error in maping study center with program seat - ' .$data_campus[1].$data_prg[1].$$data_prg[2]);
                    redirect('map/mapscprgseat');

                }
                else{
                        
                    $this->logger->write_logmessage("insert","Map study center program seat", "Map Study center eith program seat successfully.....".$data_campus[1].$data_prg[1].$$data_prg[2]);
                    $this->logger->write_dblogmessage("insert","Map study center program seat", "Map Study center eith program seat successfully....." .$data_campus[1].$data_prg[1].$$data_prg[2]);
                    $this->session->set_flashdata("success", "Map Study center with program seat successfully...");
                    redirect("map/viewscprgseat");
               
                }
               
            }//if
        }//ifpost    
        $this->load->view('map/mapscprgseat',$data);
    
    }
    
    /** This function is used for delete record of  maped study center, with program  seats
    * @param type $id
    */
    
    public function deletescprgseat($id){
        $this->db->from('seat_program_studycenter')->where('spsc_id', $id);
        $eset_q = $this->db->get();
        $val_msg=$this->mapmodel->get_studycenername($eset_q->row()->spsc_sc_code);
        $val_msg1=$this->mapmodel->get_Programseat($eset_q->row()->spsc_prg_id);
        $result=$this->commodel->deleterow('seat_program_studycenter','spsc_id',$id);
        //if($var!="pass")
        if(! $result)
        {
            
            $this->logger->write_logmessage("error","Error  in deleting  studycenter with program seat - ", $val_msg.   "program is " .$val_msg1. " [id:" . $id . "]");
            $this->logger->write_dblogmessage("error","Error  in deleting  studycenter with program seat", $val_msg.   "program is " .$val_msg1. " [id:" . $id . "]" );
            log_message('debug', 'Problem in maping studycenter with program seat'. $val_msg.   "program is " .$val_msg1. " [id:" . $id . "]");
            $this->session->set_flashdata('err_message','Error in deleting study center with program seat - studycenter',$val_msg.   "program is " .$val_msg1. " [id:" . $id . "]" );
            redirect("map/viewscprgseat");
        }
        else {  
           
            $this->logger->write_logmessage("update", "study center with program seat "  . $val_msg.   "program is " .$val_msg1. " [id:" . $id . "]");
            $this->logger->write_dblogmessage("update", "study center with program seat " . $val_msg.   "program is " .$val_msg1. " [id:" . $id . "]");
            $this->session->set_flashdata("success", 'Deleted record successfully ...' ."studycenter: "  .$val_msg. " " . "program is: " .$val_msg1 ." [id:" . $id . "]" );
            redirect("map/viewscprgseat");
        }
        
        $this->load->view('map/viewscprgseat');
               
        
    }
    
    /**This function is used for update record of  maped study center, program with seats
    * @param type $id
    */
     public function editscprgseat($id){
       
        $this->db->from('seat_program_studycenter')->where('spsc_id', $id);
        $eset_data_q = $this->db->get();
        
        $editeset_data = $eset_data_q->row();
       
        
        /* Form fields */
       
        $data['campus']= array(
            'value' => $this->mapmodel->get_studycenername($editeset_data->spsc_sc_code),
            'size'  =>'35',
            'readonly'=>'true',
        );
               
        $data['program'] =array(
            'value' => $this->mapmodel->get_Programseat($editeset_data->spsc_prg_id),
            'size'  =>'35',
            'readonly'=>'true',
        );
       
        $data['seatno'] = array(
            'name' => 'seatno',
           'id' => 'seatno',
           'maxlength' => '255',
           'size' => '35',
            'value' => $editeset_data->spsc_totalseat,
        );
       
        $data['gender'] = array(
           'value' => $editeset_data->spsc_gender,
            
        );
         
        $data['academicyear'] = array(
            'value' => $editeset_data->spsc_acadyear,
        );
        
        $data['semester'] = array(
            'value' => $editeset_data->spsc_sem,
        );
        $data['id'] = $id;
        
        /*Form Validation*/
        $this->form_validation->set_rules('campus','Campus');
        $this->form_validation->set_rules('program','Program');
        $this->form_validation->set_rules('seatno','Number of Seat','trim|required|is_numeric');
        $this->form_validation->set_rules('gender','Gender','trim|required');
        $this->form_validation->set_rules('academicyear','AcademicYear','trim|required');
        $this->form_validation->set_rules('semester','Semester','trim|required');
        
         /* Re-populating form */
        
        if ($_POST)
        {
           // $data['campus']['value'] = $this->input->post('campus', TRUE);
            //echo $campus =$this->input->post('campus', TRUE);
            //$data['program']['value'] = $this->input->post('program', TRUE);
            $data['seatno']['value'] = $this->input->post('seatno', TRUE);
            $data['gender']['value'] = $this->input->post('gender', TRUE);
            $data['academicyear']['value'] = $this->input->post('academicyear', TRUE);
            $data['semester']['value'] = $this->input->post('semester', TRUE);
            
        }
       
        if ($this->form_validation->run() == FALSE)
        {
            //echo "this is testing...2";
            $this->session->set_flashdata(validation_errors(), 'error');
            $this->load->view('map/editscprgseat',$data);
        }
        else
        {    
            $data_campus = $this->input->post('campus', TRUE);
            $data_program = $this->input->post('program', TRUE);
            $data_seatno = $this->input->post('seatno', TRUE);
            $data_gender = $this->input->post('gender', TRUE);
            $data_academicyear = $this->input->post('academicyear', TRUE);
            $data_semester = $this->input->post('semester', TRUE);
            
            $data_eid = $id;
            $prgstno=$this->mapmodel->get_Programsno($editeset_data->spsc_prg_id);
            $stno=$this->mapmodel->totalnoofseat($editeset_data->spsc_prg_id,$data_academicyear);
            $val_msg=$this->mapmodel->get_studycenername($editeset_data->spsc_sc_code);
            $val_msg1=$this->mapmodel->get_Programseat($editeset_data->spsc_prg_id);
            $check=$this->mapmodel->isduplicate('seat_program_studycenter',$editeset_data->spsc_prg_id,$editeset_data->spsc_sc_code,$data_gender,$data_academicyear);
            if(! $check){
                $this->session->set_flashdata("err_message", 'Study center-->'.$val_msg.'-->Program -->'.$val_msg1.  '-->Gender-->'. $data_gender.'-->Academic Year-->'.$data_academicyear.'   is already exist with selected combintaion.');
                $this->load->view("map/editscprgseat",$data);
                return;
            }
            $newseat=$stno+$data_seatno-$editeset_data->spsc_totalseat;
            if(!($newseat <= $prgstno)){
                $availsno=$this->availableseat($prgstno,$stno);
               // print_r("availsno===>".$availsno);
                $this->session->set_flashdata("err_message", 'number of seat should be less then or equal to the program seat ->  available seat is '.$availsno);
                $this->load->view("map/editscprgseat",$data);
                //redirect("map/editscprgseat",$data);
                return;
           
            }
            $update_data = array(
               'spsc_prg_id' => $editeset_data->spsc_prg_id,
               'spsc_sc_code' => $editeset_data->spsc_sc_code,
               'spsc_gender'  => $data_gender,
               'spsc_totalseat' => $data_seatno,
               'spsc_acadyear'  => $data_academicyear,
               'spsc_sem' => $data_semester,
               'spsc_modifierid' => $this->session->userdata('username'), 
               'spsc_modifydate' => date('y-m-d')
            ); 
            $result=$this->commodel->updaterec('seat_program_studycenter', $update_data,'spsc_id',$id);
            if(! $result)
            {
                $this->logger->write_logmessage("error", "Error in update study center with program seat "   . $val_msg . "program--- ".$val_msg1);
                $this->logger->write_dblogmessage("error", "Error in update study center with program seat" . $val_msg . "program--- ".$val_msg1 );
                log_message('debug', 'Problem in maping studycenter with program seat'. $val_msg . "program--- ".$val_msg1);
                $this->session->set_flashdata('err_message','Error in update study center with program seat - ' . $val_msg . "program--- ".$val_msg1);
                redirect("map/editscprgseat");
            }
            else {  
                          
                $this->logger->write_logmessage("update", "updated study center with program seat " . $val_msg . " updated with" . $data_campus."program--- ".$val_msg1."changed by".$data_program);
                $this->logger->write_dblogmessage("update", "updated study center with program seat "  . $val_msg . " updated with" . $data_campus."program--- ".$val_msg1."changed by".$data_program );
                $this->session->set_flashdata("success", 'updated record successfully ...' . $val_msg . " updated with  " . $data_campus."program--- ".$val_msg1);
                redirect("map/viewscprgseat");
                                
           
            }
           
        }  
                        
     // $this->load->view('map/editscprgseat',$editeset_data);
              
    }
    
    /** This function is used to check the number of seat available in program
    * @param type $seatno
    * @return boolean
    */
    public function check_prgseat($seatno){
        
        $program = $this->input->post('program');
        $data_prgseatno=explode('#',$program);
        $academicyear = $this->input->post('academicyear', TRUE);
        $totalscsno=$this->mapmodel->totalnoofseat($data_prgseatno[0],$academicyear);
       // echo "in controller===1====>";
        //print_r($totalscsno);
        $newsno=$totalscsno+$seatno;
        if ($data_prgseatno[0]!= 0){
            //if( $seatno <= $data_prgseatno[3]){
            if($newsno <= $data_prgseatno[3]){
               
                return true;
            }
            else{
                $availsno=0;
                $availsno=$this->availableseat($data_prgseatno[3],$totalscsno);
               
                $this->form_validation->set_message('check_prgseat', ' number of seat should be less then or equal to the program seat -  available seat is ' .$availsno );
               // $this->form_validation->set_message('check_prgseat', ' number of seat should be less then or equal to the program seat - ' . $data_prgseatno[3] .'.');
                return false;
            }
        }
       
    }
    
    /** This function check for duplicate entry
    * @return type
    */

    public function ischeck($data_name){
       
        $data_name = $this->input->post('program', TRUE);
        $data_prg=explode('#',$data_name); 
        $data_cap = $this->input->post('campus', TRUE);
        $data_campus=explode('#',$data_cap);
        $gender = $this->input->post('gender', TRUE);
        $academicyear = $this->input->post('academicyear', TRUE);
        
        $is_exist = $this->mapmodel->isduplicate('seat_program_studycenter',$data_prg[0], $data_campus[0],$gender,$academicyear);
        
        if($is_exist) {
            $this->form_validation->set_message(
             'ischeck', 'Study center-->'.$data_campus[1].'-->Program -->'.$data_prg[1].$data_prg[2].  '-->Gender-->'. $gender.'-->Academic Year-->'.$academicyear.'   is already exist with selected combintaion.'
            );
            return false;
        } 
        else {
            return true;
        }
    }

    public function availableseat($prgseat,$allotedseat) {
        $availsno=0;
        if($prgseat > $allotedseat){
            $availsno=$prgseat-$allotedseat;
        
        }
        return $availsno;
    }


    /* add subject paper with program */

    public function addprogramsubject()
    {
        //get subject record from subject table
        $username = $this->session->userdata('username');
        $data['subject'] = $this->mapmodel->getsubject();
        $data['program'] = $this->mapmodel->getprogram();
        $data['prgbranch'] = array('name' => 'prgbranch','id' => 'prgbranch','maxlength' => '100','size' => '30','value' => '',);
        $data['papername'] = array('name' => 'papername','id' => 'papername','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectno'] = array('name' => 'subjectno','id' => 'subjectno','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectcode'] = array('name' => 'subjectcode','id' => 'subjectcode','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectshrname'] = array('name' => 'subjectshrname','id' => 'subjectshrname','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectdesc'] = array('name' => 'subjectdesc','id' => 'subjectdesc','maxlength' => '100','size' => '30','value' => '',);
        $this->form_validation->set_rules('subjectname','Subject Name','trim|required');
        $this->form_validation->set_rules('subjecttype','Paper Category','trim|required');
        $this->form_validation->set_rules('prgbranch','Branch','trim|xss_clean');
        $this->form_validation->set_rules('subjectno','Paper No','trim|required|numeric');
        $this->form_validation->set_rules('papername','Paper Name','trim|required');
        $this->form_validation->set_rules('subjectcode','Paper Code','trim|required');
        $this->form_validation->set_rules('subjectshrname','Paper Short Name','trim');
        $this->form_validation->set_rules('subjectdesc','Paper Description','trim');
        $this->form_validation->set_rules('acadyear','Academic Year','trim|required');
        $this->form_validation->set_rules('degree','Degree','trim|required');
        
        if($_POST)
        {
            $subject_name = $this->input->post('subjectname',TRUE);
            $subject_type = $this->input->post('subjecttype',TRUE);
            $paper_name = $this->input->post('papername',TRUE);
            $subject_no = $this->input->post('subjectno',TRUE);
            $prgbranch = $this->input->post('prgbranch',TRUE);
            $subject_code = $this->input->post('subjectcode',TRUE);
            $subject_shrname = $this->input->post('subjectshrname',TRUE);
            $subject_desc = $this->input->post('subjectdesc',TRUE);
            $acadyear = $this->input->post('acadyear',TRUE);
            $degree = $this->input->post('degree',TRUE);
        }
        
        if($this->form_validation->run() == TRUE)
        {
            $currdate = date("y-m-d");

            $data_sub = explode('#',$subject_name);
            $data_prg = explode('#',$degree);
            //check for combination of record exist.      
            $is_existst = $this->mapmodel->ispaper('subject_paper',$data_sub[1], $subject_type,$subject_no,$acadyear,$data_prg[1]);
            if($is_existst > 0) 
            {
                $this->session->set_flashdata('error', 'Paper no-<b>'.$subject_no.'</b> of Subject<b> '.$data_sub[0].  ' </b> for degree '.$data_prg[0] .' for academic year '.$acadyear.' is already exist.');
                $this->load->view("map/addprogramsubject",$data);
                return;
            }
            else
            {
                $insertdata_paper = array('subp_sub_id' => $data_sub[1],'subp_subtype' => $subject_type,'subp_paperno' => $subject_no,'subp_name' => ucwords(strtolower($paper_name)),'subp_code' => strtoupper($subject_code),'subp_short' => ucwords(strtolower($subject_shrname)),'subp_desp' => $subject_desc, 'subp_degree' => $data_prg[1],'subp_branch' => ucwords(strtolower($prgbranch)),'subp_acadyear' => $acadyear,'creatorid' => $username,'createdate' => $currdate,'modifierid' => $username,'modifydate' => $currdate);

                $res=$this->commodel->insertrec('subject_paper', $insertdata_paper);                 
                if ($res != 1)
                {
                    $this->session->set_flashdata("error","Error  in Adding Paper - ", $data_sub[0]);
                    $this->logger->write_logmessage("error","Error  in Adding Paper", $data_sub[0]." by ". $username);
                    $this->logger->write_dblogmessage("error","Error  in Adding Paper", $data_sub[0]." by ". $username);
                    redirect("map/addprogramsubject");
                }
                else
                {
                    $this->logger->write_logmessage("insert","Paper added successfully", $data_sub[0]." by ". $username);
                    $this->logger->write_dblogmessage("insert","Paper added successfully", $data_sub[0]." by ". $username);
                    //$this->session->set_flashdata("success", "Paper added successfully - ", $data_sub[0]);
                    $this->session->set_flashdata("success", "Paper added successfully  ",'' );
                    redirect("map/programsubject");
                }
            }
        }
        $this->load->view('map/addprogramsubject',$data);
    }

    public function ispaper()
    {
        $subject_name = $this->input->post('subjectname',TRUE);
        $subject_type = $this->input->post('subjecttype',TRUE);
        $subject_no = $this->input->post('subjectno',TRUE);
        $acadyear = $this->input->post('acadyear',TRUE);
        $degree = $this->input->post('degree',TRUE);
        $data_sub = explode('#',$subject_name);
        $data_prg = explode('#',$degree);
        if($is_exist) {
            return false;
        }
        else {
            return true;
        }
    }

    /* view paper subject */
    public function programsubject()
    {
        //$data['paperrecords'] = $this->mapmodel->get_program_subject_paper();
        $data['paperrecords'] = $this->commodel->get_list('subject_paper');
        $this->load->view('map/programsubject',$data);
    }
    /* delete  subject paper */
    public function deleteprogramsubject($paperid)
    {
        $username = $this->session->userdata('username');
        //$data['paperrecords'] = $this->mapmodel->get_program_subject_paper();
        $data['paperrecords'] = $this->commodel->get_list('subject_paper');
        /* get deleted record data */
        //$papersubjectrecord = $this->mapmodel->paper_subject_record($paperid);
        $papsubrec = $this->commodel->get_listrow('subject_paper','subp_id',$paperid);
        $papersubjectrecord = $papsubrec->result();
        foreach($papersubjectrecord as $row)
        {
            $subpaper_id = $row->subp_id;
            $subject_id = $row->subp_sub_id;
            $subject_type = $row->subp_subtype;
            $subpaper_no = $row->subp_paperno;
            $subpaper_code = $row->subp_code;
            $paper_name = $row->subp_name;    
        }
        /* delete record */ 
        $delres=$this->commodel->deleterow('subject_paper','subp_id',$paperid);        
        if(! $delres)
        {
            $this->logger->write_logmessage("error","Error  in deleting subject paper - ", $paperid.   "SubjectPaper id- " .$subpaper_id. " Type- ".$subject_type. " Paper no- ".$subpaper_no." Paper Name ".$paper_name );
            $this->logger->write_dblogmessage("error","Error  in deleting subject paper - ", $paperid.   "SubjectPaper id- " .$subpaper_id. " Type- ".$subject_type. " Paper no- ".$subpaper_no." Paper Name ".$paper_name );
            log_message('debug', "Error  in deleting subject paper - ", $paperid.   "SubjectPaper id- " .$subpaper_id. " Type- ".$subject_type. " Paper no- ".$subpaper_no." Paper Name ".$paper_name);
            $this->session->set_flashdata('err_message','Error in deleting Subject Paper',"SubjectPaper id- " .$subpaper_id. " Type- ".$subject_type. " Paper no- ".$subpaper_no." Paper Name ".$paper_name );
            redirect('map/programsubject',$data);
        }
        else {

            $this->logger->write_logmessage("update", "deleted subject paper - "  . "SubjectPaper id- " .$subpaper_id. " Type- ".$subject_type. " Paper no- ".$subpaper_no." Paper Name ".$paper_name);
            $this->logger->write_dblogmessage("update", "deleted subject paper - "  . "SubjectPaper id- " .$subpaper_id. " Type- ".$subject_type. " Paper no- ".$subpaper_no." Paper Name ".$paper_name);
            $this->session->set_flashdata("success", 'Deleted record successfully ...' ."SubjectPaper id- " .$subpaper_id." and Paper Name ".$paper_name);
            redirect('map/programsubject',$data);
        }

        $this->load->view('map/programsubject',$data);
    }

    /* update subject paper */

    public function editprogramsubject($paperid)
    {
        $username = $this->session->userdata('username');
        $data['subject'] = $this->mapmodel->getsubject();
        $data['program'] = $this->mapmodel->getprogram();

//       $papersubjectrecord = $this->mapmodel->paper_subject_record($paperid);
        $papsubrec = $this->commodel->get_listrow('subject_paper','subp_id',$paperid);
        $papersubjectrecord = $papsubrec->result();

        foreach($papersubjectrecord as $row)
        {
            $subpaper_id = $row->subp_id;
            $subject_id = $row->subp_sub_id;
            $subject_type = $row->subp_subtype;
            $subpaper_no = $row->subp_paperno;
            $paper_name = $row->subp_name;
            $subpaper_code = $row->subp_code;
            $subshort = $row->subp_short;  
            $subdesc = $row->subp_desp;
            $subdegree = $row->subp_degree;
            $prgbranch = $row->subp_branch;
            $degyear = $row->subp_acadyear;
            $moddate = date("y-m-d");
        }

        /*$this->db->from('program')->where('prg_id', $subdegree);
        $prgdata = $this->db->get();
        */
        $prgdata = $this->commodel->get_listrow('program','prg_id',$subdegree);
        $prg_data = $prgdata->row();
//        print_r($prg_data);

        /*$this->db->from('subject')->where('sub_id', $subject_id);
        $subdata = $this->db->get();*/

        $subdata = $this->commodel->get_listrow('subject','sub_id',$subject_id);
        $sub_data = $subdata->row();
        
        $data['degree'] = array('name' => 'degree','id' => 'degree','maxlength' => '100','size' => '30','readonly'=>'true','value' => $prg_data->prg_name,);
        $data['acadyear'] = array('name' => 'acadyear','id' => 'acadyear','maxlength' => '100','size' => '30','value' => $degyear,'readonly'=>'true',);
        $data['subjectname'] = array('name' => 'subjectname','id' => 'subjectname','maxlength' => '100','size' => '30','value' =>$sub_data->sub_name ,'readonly'=>'true',);
        $data['papercat'] = array('name' => 'papercat','id' => 'papercat','maxlength' => '100','size' => '30','value' => $subject_type ,'readonly'=>'true',);
        $data['prgbranch'] = array('name' => 'prgbranch','id' => 'prgbranch','maxlength' => '100','size' => '30','value' =>$prgbranch ,'readonly'=>'true',);
        $data['subjectno'] = array('name' => 'subjectno','id' => 'subjectno','maxlength' => '100','size' => '30','value' =>$subpaper_no ,'readonly'=>'true',);
        $data['papername'] = array('name' => 'papername','id' => 'papername','maxlength' => '100','size' => '30','value' => $paper_name,);
        $data['subjectcode'] = array('name' => 'subjectcode','id' => 'subjectcode','maxlength' => '100','size' => '30','value' =>$subpaper_code ,'readonly'=>'true',);
        $data['subjectshrname'] = array('name' => 'subjectshrname','id' => 'subjectshrname','maxlength' => '100','size' => '30','value' => $subshort,);
        $data['subjectdesc'] = array('name' => 'subjectdesc','id' => 'subjectdesc','maxlength' => '100','size' => '30','value' => $subdesc,);
        $data['paperid'] = $paperid;

        $this->form_validation->set_rules('subjectname','Subject Name','trim|required');
        $this->form_validation->set_rules('papercat','Paper Category','trim|required');
        $this->form_validation->set_rules('subjectno','Paper No','trim|required|numeric');
        $this->form_validation->set_rules('papername','Paper Name','trim|required');
        $this->form_validation->set_rules('subjectcode','Paper Code','trim|required');
        $this->form_validation->set_rules('subjectshrname','Paper Short Name','trim');
        $this->form_validation->set_rules('subjectdesc','Paper Description','trim');
        $this->form_validation->set_rules('acadyear','Academic Year','trim|required');
        $this->form_validation->set_rules('degree','Degree','trim|required');
        $this->form_validation->set_rules('prgbranch','Branch','trim|xss_clean');


        if($_POST)
        {
            $papname = $this->input->post('papername',TRUE);
            $subjshrname = $this->input->post('subjectshrname',TRUE);
            $subjdesc = $this->input->post('subjectdesc',TRUE);
            
        }
        if($this->form_validation->run() == TRUE)
	{
		$currdate = date("y-m-d");
		$subpaper_id = $row->subp_id;
            $subject_id = $row->subp_sub_id;
            $subject_type = $row->subp_subtype;
            $subpaper_no = $row->subp_paperno;
            $paper_name = $row->subp_name;
            $subpaper_code = $row->subp_code;
            $subshort = $row->subp_short;
            $subdesc = $row->subp_desp;
            $subdegree = $row->subp_degree;
            $prgbranch = $row->subp_branch;
            $degyear = $row->subp_acadyear;

		// insert data into subject paper archive table  
		$insertdata_paper = array('subpa_subpid' => $paperid,'subpa_sub_id' => $subject_id ,'subpa_subtype' => $subject_type,'subpa_paperno' => $subpaper_no,'subpa_name' => ucwords(strtolower($paper_name)),'subpa_code' => strtoupper($subpaper_code),'subpa_short' => ucwords(strtolower($subshort)),'subpa_desp' => $subdesc, 'subpa_degree' => $subdegree,'subpa_branch' => ucwords(strtolower($prgbranch)),'subpa_acadyear' => $degyear,'creatorid' => $username,'createdate' => $currdate,'modifierid' => $username,'modifydate' => $currdate);

                $res=$this->commodel->insertrec('subject_paper_archive', $insertdata_paper);
                if ($res != 1)
                {
                    $this->logger->write_logmessage("error","Error  in Adding subject_paper_archive", $prg_data->prg_name . $sub_data->sub_name ." by ". $username);
                    $this->logger->write_dblogmessage("error","Error  in Adding subject_paper_archive", $prg_data->prg_name . $sub_data->sub_name ." by ". $username);
                }
                else
                {
                    $this->logger->write_logmessage("insert","subject_paper_archive table data added successfully", $prg_data->prg_name . $sub_data->sub_name ." by ". $username);
                    $this->logger->write_dblogmessage("insert","subject_paper_archive archive table data added successfully", $prg_data->prg_name . $sub_data->sub_name ." by ". $username);
                }
		// update data in subject paper table
            $updatedata_paper = array('subp_name' => ucwords(strtolower($papname)),'subp_short' => $subjshrname,'subp_desp' => $subjdesc, 'modifierid' => $username,'modifydate' => $moddate);
            $updatepaper = $this->commodel->updaterec('subject_paper', $updatedata_paper,'subp_id',$paperid);
            if($updatepaper != 1)
            {
                $this->session->set_flashdata('error','Error in updating subject paper - ' . $prg_data->prg_name . $sub_data->sub_name . '.' );
                $this->logger->write_logmessage("update", "Error in updating subject paper " .  $prg_data->prg_name . $sub_data->sub_name, " by ".  $username);
                $this->logger->write_dblogmessage("update", "Error in updating subject paper " ,  $prg_data->prg_name . $sub_data->sub_name. " by ".  $username);
                redirect('map/programsubject');
            }
            else
            {
                $this->session->set_flashdata("success", "Subject paper updated of degree <b>".  $prg_data->prg_name ." of ". $sub_data->sub_name ."</b>  successfully");
                $this->logger->write_logmessage("update", "Subject paper updated <b> " . $prg_data->prg_name . $sub_data->sub_name , " by". $username);
                $this->logger->write_dblogmessage("update", "Subject paper updated <b> " , $prg_data->prg_name . $sub_data->sub_name . " by". $username);
                redirect('map/programsubject');
            }
                $this->deptresult = $this->commodel->get_listspfic2('Department','dept_id', 'dept_name');
}        
        $this->load->view('map/editprogramsubject',$data);    
    }

    /**This function is used for view details of subject semseter program and dept seats */

    public function subjectsemester(){
        $data['subsemrec']= $this->commodel->get_list('subject_semester');
        $this->logger->write_logmessage("view","Map subject semseter program with dept", "view details...");
        $this->logger->write_dblogmessage("view"," Map subject semester program with dept", "view details...");
        $this->load->view('map/subjectsemester',$data);
    }

    /** This function is used for map subject semester program and department */
    public function mapsubsem(){
	    $data['dept'] = $this->commodel->get_listmore('Department','dept_id,dept_name');
	    $data['subres'] = $this->commodel->get_listmore('subject','sub_id,sub_name');
	    $data['prgresult'] = $this->commodel->get_listspfic2('program','prg_name', '','','','prg_name');
        if(isset($_POST['mapsubsem'])) {
            /*Form Validation*/
            $this->form_validation->set_rules('subsem_deptid','Department','trim|required');
            $this->form_validation->set_rules('subsem_prgid','Program','trim|required');
            $this->form_validation->set_rules('subsem_semester','Semester','trim|required');
            $this->form_validation->set_rules('subsem_subid','Subject','trim|required');
            $this->form_validation->set_rules('subsem_subtype','Subject Type','trim|required');
            if($this->form_validation->run() == TRUE)
            {  
                //echo "this is prgid============";
                $prgid = $this->input->post('subsem_prgid', TRUE);
                $deptid = $this->input->post('subsem_deptid', TRUE);
                $subid = $this->input->post('subsem_subid', TRUE);
                $subtype = $this->input->post('subsem_subtype', TRUE);
                $semester = $this->input->post('subsem_semester', TRUE);

		$datawh=array('subsem_subid' => $subid, 'subsem_prgid' => $prgid, 'subsem_semester' => $semester);
        	$is_exist = $this->commodel->isduplicatemore('subject_semester',$datawh);

        	if($is_exist) {
			$this->form_validation->set_message('ischeck', 'Subject id-->'.$subid.'-->Program id-->'.$prgid . '-->semester -->'. $semester  .' is already exist with selected combintaion.' );
			redirect('map/mapsubsem');
            		return false;
        	}
        	else {
                $data = array(
                    'subsem_subid'=>$subid,
                    'subsem_prgid'=>$prgid,
                    'subsem_semester'=>$semester,
                    'subsem_subtype'=>$subtype,
                    'subsem_ext1'=>$deptid,
                    'creatorid'=>$this->session->userdata('username'),
                    'createdate'=>date('y-m-d'),
                    'modifierid'=>$this->session->userdata('username'),
                    'modifydate'=>date('y-m-d')
                );
           
                $mapscprg=$this->commodel->insertrec('subject_semester', $data);
                if(! $mapscprg )
                {
                    $this->logger->write_logmessage("error","Error  in maping subject semester with program dept", $subid.$prgid.$deptid);
                    $this->logger->write_dblogmessage("error","Error  in maping subject semester with program dept", $subid.$prgid.$deptid);
                    $this->session->set_flashdata('err_message','Error in maping subject semester with program dept - ' .$subid.$prgid.$deptid);
                    redirect('map/mapsubsem');
                }
                else{
			$this->logger->write_logmessage("insert","Map subject semester program seat", "Map Subject semester with program dept successfully.....".$subid.$prgid.$deptid);
			
                    $this->logger->write_dblogmessage("insert","Map subject semester program seat", "Map Subject semester with program dept successfully....." .$subid.$prgid.$deptid);
                    $this->session->set_flashdata("success", "Map Subject semester with program dept successfully...");
                    redirect("map/subjectsemester");
		}//database error check
	    	}//else duplicate exist
            }//if validation
        }//ifpost    
        $this->load->view('map/mapsubsem',$data);
	}

//==================  Map Subject and Paper with Teacher ===========================================================================

  /*
   * this function has been created for display the list of program subject and teacher record.
   */
   public function listsubjectteacher(){
        $this->result = $this->commodel->get_list('program_subject_teacher');
        $this->logger->write_logmessage("view"," View Subject and Paper with Teacher", "Map Subject and Paper with Teacher record display successfully." );
        $this->logger->write_dblogmessage("view"," View Subject and Paper with Teacher", "Map Subject and Paper with Teacher record display successfully." );
        $this->load->view('map/listsubjectteacher',$this->result);
   }

 /*
  * this function has been created for add the new program subject and teacher record.
  */
   public function subjectteacher(){
        $this->scresult = $this->commodel->get_listspfic2('study_center','sc_id', 'sc_name');
        $this->pnresult = $this->commodel->get_listspfic2('program','prg_name', '','','','prg_name');
	
       if(isset($_POST['subjectteacher'])) {
            $this->form_validation->set_rules('campusname','Campus Name','xss_clean|required');
            $this->form_validation->set_rules('deptname','Department Name','xss_clean|required');
            $this->form_validation->set_rules('academicyear','Academic Year','trim|xss_clean|required');
            $this->form_validation->set_rules('programname','Program Name','trim|xss_clean|required');
            $this->form_validation->set_rules('branchname','Branch Name','trim|xss_clean|required');
            $this->form_validation->set_rules('semester','Semester ','trim|xss_clean|required');
            $this->form_validation->set_rules('subjectname','Subject Name','trim|xss_clean|required');
            $this->form_validation->set_rules('papername','Paper Name','trim|xss_clean|required');
            $this->form_validation->set_rules('teachername','Teacher Name','trim|xss_clean|required');

        if($this->form_validation->run()==TRUE){
	  
	   $subid = $this->input->post("subjectname");
	   $paperid = $this->input->post("papername");
	   $teachid = $this->input->post("teachername");
	   $subname = $this->commodel->get_listspfic1('subject', 'sub_name', 'sub_id', $subid)->sub_name;
           $papername = $this->commodel->get_listspfic1('subject_paper', 'subp_name', 'subp_id', $paperid)->subp_name;
           $teacher = $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $teachid)->firstname . $this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $teachid)->lastname;

	$pstdatacheck = array('pstp_scid'=>$_POST['campusname'], 'pstp_prgid'=>$_POST['branchname'], 'pstp_subid'=>$_POST['subjectname'], 'pstp_papid'=>$_POST['papername'], 'pstp_teachid'=>$_POST['teachername'], 'pstp_acadyear'=>$_POST['academicyear'], 'pstp_sem'=>$_POST['semester'] );

        $datapst = array(
        'pstp_scid'=>$_POST['campusname'],
        'pstp_prgid'=>$_POST['branchname'],
        'pstp_subid'=>$_POST['subjectname'],
        'pstp_papid'=>$_POST['papername'],
        'pstp_teachid'=>$_POST['teachername'],
        'pstp_acadyear'=>$_POST['academicyear'],
        'pstp_sem'=>$_POST['semester'],
        'pstp_ext1'=>'NULL',
        'pstp_ext2'=>'NULL',
        'pstp_creatorid'=> $this->session->userdata('username'),
        'pstp_createdate'=> date('Y-m-d'),
        'pstp_modifierid'=> $this->session->userdata('username'),
        'pstp_modifydate'=> date('Y-m-d')
        );

     	$pstdatadup = $this->commodel->isduplicatemore('program_subject_teacher', $pstdatacheck);
	
        if($pstdatadup == 1 ){

		$this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Subject Name' = $subname  , 'Paper Name' = $papername , 'Teacher Name' = $teacher  .");
                redirect('map/subjectteacher');
		return;
	}
        else{	
      
        $pstflag = $this->commodel->insertrec('program_subject_teacher', $datapst) ;
        if(!$pstflag)
          {
        	$this->logger->write_logmessage("insert"," Error in adding subject paper teacher ", " Subject and Paper with teacher data insert error .'Subject Name' = $subname  , 'Paper Name' = $papername , 'Teacher Name' = $teacher  "  );
                $this->logger->write_dblogmessage("insert"," Error in adding subject paper teacher ", " Subject and Paper with teacher data insert error .'Subject Name' = $subname  , 'Paper Name' = $papername , 'Teacher Name' = $teacher  " );
                $this->session->set_flashdata('err_message','Error in adding subject paper teacher - ' . $teacher . '.', 'error');
                $this->load->view('map/subjectteacher');
	  }
          else{
                $this->logger->write_logmessage("insert"," map subject Paper teacher ", "map subject paper teacher record added successfully. 'Subject Name' = $subname  , 'Paper Name' = $papername , 'Teacher Name' = $teacher " );
                $this->logger->write_dblogmessage("insert"," map subject Paper teacher ", "map subject Paper teacher record added successfully. 'Subject Name' = $subname  , 'Paper Name' = $papername , 'Teacher Name' = $teacher " );
		$this->session->set_flashdata("success", "Record added successfully...'Subject Name' = $subname  , 'Paper Name' = $papername , 'Teacher Name' = $teacher ");
                redirect('map/listsubjectteacher');

	   }	
        }
	}
	}
	$this->load->view('map/subjectteacher');
   }
  /*
   * this function has been created for delete the program subject teacher record.
   */
   public function deletepsteacher($pstp_id){
	$pst_data=$this->commodel->get_listrow('program_subject_teacher','pstp_id', $pstp_id);
        $pst_data_d = $pst_data->row();
        $subname = $this->commodel->get_listspfic1('subject', 'sub_name', 'sub_id', $pst_data_d->pstp_subid)->sub_name;
        $papername = $this->commodel->get_listspfic1('subject_paper', 'subp_name', 'subp_id', $pst_data_d->pstp_papid)->subp_name;
        $teacher = $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $pst_data_d->pstp_teachid)->firstname . $this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $pst_data_d->pstp_teachid)->lastname;
        $pstflag=$this->commodel->deleterow('program_subject_teacher', 'pstp_id', $pstp_id);
        if(!$pstflag)
        {
            $this->logger->write_logmessage("delete", "Error in Deleting subject and Paper with teacher ", "Error in  Subject and paper with Teacher [pstp_id: $pstp_id , $teacher ] delete.. " );
            $this->logger->write_dblogmessage("delete", "Error in Deleting subject paper teacher ","Error in Subject and Paper with Teacher [pstp_id:  $pstp_id , $teacher ] delete.. " );
            $this->session->set_flashdata('err_message','Error in deleting subject paper teacher - ', 'error');
            redirect('map/listsubjectteacher');
        }
        else {

            $this->logger->write_logmessage("delete", "Deleted Subject and paper with Teacher ", "Subject and Paper with Teacher [pstp_id:  $pstp_id , $teacher  ] deleted successfully.. " );
            $this->logger->write_dblogmessage("delete", "Deleted Subject and paper with Teacher ","Subject and Paper with Teacher [pstp_id:  $pstp_id , $teacher ] deleted successfully.. " );
            $this->session->set_flashdata("success", "Record Deleted successfully..'Subject Name' = $subname , 'Paper Name' = $papername , 'Teacher Name' = $teacher ");
            redirect('map/listsubjectteacher');
        }
           $this->load->view('map/listsubjectteacher');
  }
 /*
  * this function has been created for update the program subject teacher record.
  */
  public function editsubjectteacher($pstp_id){
        $this->tresult = $this->commodel->get_listspfic2('user_role_type','userid', 'roleid');
	$pst_data_q=$this->commodel->get_listrow('program_subject_teacher','pstp_id', $pstp_id);
        if ($pst_data_q->num_rows() < 1)
        {
           redirect('map/editsubjectteacher');
        }
        $editpst_data = $pst_data_q->row();

        /* Form fields */

        $data['campusname']= array(
            'name' => 'campusname',
            'id' => 'campusname',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $editpst_data->pstp_scid)->sc_name,
            'readonly' => 'readonly'
        );

        $data['deptname']= array(
            'name' => 'deptname',
            'id' => 'deptname',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $this->commodel->get_listspfic1('user_role_type', 'deptid', 'userid', $editpst_data->pstp_teachid)->deptid)->dept_name,
            'readonly' => 'readonly'
        );

        $data['academicyear'] = array(
            'name' => 'academicyear',
            'id' => 'academicyear',
            'maxlength' => '40',
            'size' => '40',
            'value' => $editpst_data->pstp_acadyear,
           'readonly' => 'readonly'
        );

        $data['programname'] = array(
            'name' => 'programname',
            'id' => 'programname',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('program', 'prg_name', 'prg_id', $editpst_data->pstp_prgid)->prg_name,
            'readonly' => 'readonly'
	    	
        );

        $data['branchname'] = array(
            'name' => 'branchname',
            'id' => 'branchname',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('program', 'prg_branch', 'prg_id', $editpst_data->pstp_prgid)->prg_branch,
            'readonly' => 'readonly'
        );

        $data['semester'] = array(
            'name' => 'semester',
            'id' => 'semester',
            'maxlength' => '40',
            'size' => '40',
            'value' => $editpst_data->pstp_sem,
            'readonly' => 'readonly'
        );

        $data['subjectname'] = array(
            'name' => 'subjectname',
            'id' => 'subjectname',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('subject', 'sub_name', 'sub_id', $editpst_data->pstp_subid)->sub_name,
            'readonly' => 'readonly'  
        );

        $data['papername'] = array(
            'name' => 'papername',
            'id' => 'papername',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('subject_paper', 'subp_name', 'subp_id', $editpst_data->pstp_papid)->subp_name,
            'readonly' => 'readonly'
        );

        $data['teachername'] = array(
            'name' => 'teachername',
            'id' => 'teachername',
            'maxlength' => '40',
            'size' => '40',
            'value' => $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $editpst_data->pstp_teachid)->firstname,
            
        );

	$data['pstp_id'] = $pstp_id;

        $this->form_validation->set_rules('teachername','Teacher Name ','trim|xss_clean');

        if ($_POST)
        {
            $data['teachername']['value'] = $this->input->post('teachername', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('map/editsubjectteacher', $data);
            return;
        }
      else
        {
            $data_campusname = $this->input->post('campusname', TRUE);
            $data_deptname = $this->input->post('deptname', TRUE);
            $data_programname = $this->input->post('branchname', TRUE);
            $data_subjectname = $this->input->post('subjectname', TRUE);
            $data_papername = $this->input->post('papername', TRUE);
            $data_teachername = $this->input->post('teachername', TRUE);
            $data_academicyear = $this->input->post('academicyear', TRUE);
            $data_semester = $this->input->post('semester', TRUE);
            $data_pstpid = $pstp_id;
            $logmessage = "";
            if($editpst_data->pstp_scid != $data_campusname)
                $logmessage = "Campus Name " .$editpst_data->pstp_scid. " changed by " .$data_campusname;
            if($editpst_data->pstp_prgid != $data_programname)
                $logmessage = "Program Name " .$editpst_data->pstp_prgid. " changed by " .$data_programname;
            if($editpst_data->pstp_subid != $data_subjectname)
                $logmessage = "Subject Name " .$editpst_data->pstp_subid. " changed by " .$data_subjectname;
            if($editpst_data->pstp_papid != $data_papername)
                $logmessage = "Paper Name " .$editpst_data->pstp_papid. " changed by " .$data_papername;
            if($editpst_data->pstp_teachid != $data_teachername)
                $logmessage = "Teacher Name " .$this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $editpst_data->pstp_teachid)->firstname. " " .$this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $editpst_data->pstp_teachid)->lastname. " changed by " .$data_teachername;
            if($editpst_data->pstp_acadyear != $data_academicyear)
                $logmessage = "Academic Year " .$editpst_data->pstp_acadyear. " changed by " .$data_academicyear;
            if($editpst_data->pstp_sem != $data_semester)
                $logmessage = "Semester Name " .$editpst_data->pstp_sem. " changed by " .$data_semester;

	$pstdataedit = array('pstp_scid'=>$this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $data_campusname)->sc_id,
			     'pstp_prgid'=>$this->commodel->get_listspfic1('program', 'prg_id', 'prg_branch', $data_programname)->prg_id,
			     'pstp_subid'=>$this->commodel->get_listspfic1('subject', 'sub_id', 'sub_name', $data_subjectname)->sub_id,
			     'pstp_papid'=>$this->commodel->get_listspfic1('subject_paper', 'subp_id', 'subp_name', $data_papername)->subp_id, 
                             'pstp_teachid'=>$this->loginmodel->get_listspfic1('userprofile', 'userid', 'firstname', $data_teachername)->userid,
                             'pstp_acadyear'=>$data_academicyear, 'pstp_sem'=>$data_semester );
	
	$update_data = array(
               'pstp_scid' => $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', ($data_campusname))->sc_id,
               'pstp_prgid' => $this->commodel->get_listspfic1('program', 'prg_id', 'prg_branch', ($data_programname))->prg_id,
               'pstp_subid' =>  $this->commodel->get_listspfic1('subject', 'sub_id', 'sub_name', ($data_subjectname))->sub_id,
               'pstp_papid' => $this->commodel->get_listspfic1('subject_paper', 'subp_id', 'subp_name', ($data_papername))->subp_id,
               'pstp_teachid' => $this->loginmodel->get_listspfic1('userprofile', 'userid', 'firstname', ($data_teachername))->userid,
               'pstp_acadyear' => $data_academicyear,
               'pstp_sem' => $data_semester,
               'pstp_modifierid' =>$this->session->userdata('username'),
               'pstp_modifydate' =>date('Y-m-d')
            );

        $pstdatadup = $this->commodel->isduplicatemore('program_subject_teacher', $pstdataedit);
        if($pstdatadup == 1 ){
                $this->session->set_flashdata("err_message", "Record is already exist with this combination. Subject Name = '$data_subjectname' , Paper Name =' $data_papername' , Teacher Name =' $data_teachername' ");
                redirect('map/listsubjectteacher/');
        	return;
            }
        else{

	   $catflag=$this->commodel->updaterec('program_subject_teacher', $update_data, 'pstp_id', $data_pstpid);
           if(!$catflag)
            {
                $this->logger->write_logmessage("error","Error in updating Program Subject Teacher ", "Error in Map Subject, Paper with Teacher record updating. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating Program Subject Teacher ", "Error in Map Subject, Paper with Teacher record updating. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating Program Subject Teacher ' . $logmessage . '.', 'error');
                $this->load->view('map/editsubjectteacher', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Subject and Paper with Teacher", " Subject and Paper with Teacher record updated successfully. $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Subject Teacher", "Subject and Paper with Teacher record updated successfully. $logmessage ." );
                $this->session->set_flashdata('success',"Record updated successfully. The  $logmessage ." );
                redirect('map/listsubjectteacher/');
                }
         }//else
         $this->load->view('map/editsubjectteacher');
     }
  }


/* This function has been created for get list of Department on the basis of campus */
	public function getdeptlist(){
	    $scid = $this->input->post('campusname');
	    $this->depmodel->getdeptlist_model($scid);
	}

/*This function has been created for display teacher list on the basis of Department*/
	public function teacherlist(){
		$deptid = $this->input->post('deptname');
	    	$this->depmodel->get_teacherlist($deptid);
        }

/*This function has been created for display the list of branch on the basis of program*/
	public function branchlist(){
	$pgid = $this->input->post('programname');	
	    	$this->depmodel->get_branchlist($pgid);
        }

/*This function has been created for display subject on the basis of program and branch*/
	public function subjectlist(){
	$branchid = $this->input->post("branchname");
	    	$this->depmodel->get_subjectlist($branchid);
	}

/*This function has been created for display paper name on the basis of subject */
	public function paperlist(){
		$subid = $this->input->post("subjectname");
	    	$this->depmodel->get_paperlist($subid);
        }

 //==================  End of Map Subject and Paper with Teacher ==============================================================
  /**This function is used for view prerequite of subject  */

    public function prerequisite(){
        $data['subprerec']= $this->commodel->get_list('subject_prerequisite');
        $this->logger->write_logmessage("view","Map subject prerequisite program with dept", "view details...");
        $this->logger->write_dblogmessage("view"," Map subject prerequisite program with dept", "view details...");
        $this->load->view('map/prerequisite',$data);
    }

    /** This function is used for map subject prerequisite program and department */
    public function mapsubpre(){
	    $data['subpres'] = $this->commodel->get_listmore('subject_paper','subp_id,subp_name');
	    $data['subres'] = $this->commodel->get_listmore('subject','sub_id,sub_name');
	    $data['prgresult'] = $this->commodel->get_listspfic2('program','prg_name', '','','','prg_name');
        if(isset($_POST['mapsubpre'])) {
            /*Form Validation*/
            $this->form_validation->set_rules('spreq_prgid','Program','trim|required');
            $this->form_validation->set_rules('spreq_subid','Subject','trim|required');
            if($this->form_validation->run() == TRUE)
            {  
                //echo "this is prgid============";
                $prgid = $this->input->post('spreq_prgid', TRUE);
		$subid = $this->input->post('spreq_subid', TRUE);
		$subdepid = $this->input->post('spreq_subdepid', TRUE);
		$subpid = $this->input->post('spreq_subpid', TRUE);
		$subpdepid = $this->input->post('spreq_subpdepid', TRUE);

		$datawh=array('spreq_subid' => $subid, 'spreq_prgid' => $prgid,'spreq_depsubid' =>$subdepid);
        	$is_exist = $this->commodel->isduplicatemore('subject_prerequisite',$datawh);

        	if($is_exist) {
			$this->session->set_flashdata('err_message', 'Subject id-->'.$subid.'-->Program id-->'.$prgid . '-->subject prerequisite -->'. $subdepid  .' is already exist with selected combintaion.' );
			redirect('map/mapsubpre');
            		return false;
        	}
        	else {
                $data = array(
                    'spreq_prgid'=>$prgid,
                    'spreq_subid'=>$subid,
                    'spreq_depsubid'=>$subdepid,
                    'spreq_subpid'=>$subpid,
                    'spreq_depsubpid'=>$subpdepid,
                    'creatorid'=>$this->session->userdata('username'),
                    'createdate'=>date('y-m-d'),
                    'modifierid'=>$this->session->userdata('username'),
                    'modifydate'=>date('y-m-d')
                );
           
                $mapscprg=$this->commodel->insertrec('subject_prerequisite', $data);
                if(! $mapscprg )
                {
                    $this->logger->write_logmessage("error","Error  in maping subject Prerequisite", $subid.$prgid.$subdepid);
                    $this->logger->write_dblogmessage("error","Error  in maping subject Prerequisite", $subid.$prgid.$subdepid);
                    $this->session->set_flashdata('err_message','Error in maping subject Prerequisite - ' .$subid.$prgid.$subdepid);
                    redirect('map/mapsubpre');
                }
                else{
			$this->logger->write_logmessage("insert","Map subject Prerequisite", "Map Subject Prerequisite successfully.....".$subid.$prgid.$subdepid);
			
                    $this->logger->write_dblogmessage("insert","Map subject Prerequisite", "Map Subject Prerequisite successfully....." .$subid.$prgid.$subdepid);
                    $this->session->set_flashdata("success", "Map Subject Prerequisite  successfully...".$subid.$prgid.$subdepid);
                    redirect("map/prerequisite");
		}//database error check
	    	}//else duplicate exist
            }//if validation
        }//ifpost    
        $this->load->view('map/mapsubpre',$data);
 }

    /****************************************** Map user wirh Role ********************************************/

     /**This function is used for view details of map user with role */

    public function viewuserrole()
     {
        $this->result = $this->commodel->get_list('user_role_type');
        $this->logger->write_logmessage("view"," View map user with role setting", "user map setting details...");
        $this->logger->write_dblogmessage("view"," View map user with role setting", "Role setting details...");
        $this->load->view('map/viewuserrole',$this->result);
     }
   
    /** This function is for map user with role */

        public function userroletype()
        {
        $this->scresult   = $this->commodel->get_listspfic2('study_center','sc_id', 'sc_name');
        $this->roleresult = $this->commodel->get_listspfic2('role','role_id', 'role_name');
        $this->loginuser  = $this->loginmodel->get_userlist('edrpuser','id','username');

        if(isset($_POST['userroletype'])) {

        /*Form Validation*/
        $this->form_validation->set_rules('campus','Campus Name','trim|xss_clean|required');
        $this->form_validation->set_rules('dept_name','Departname','trim|xss_clean|required');
        $this->form_validation->set_rules('role_name','Role Name','trim|xss_clean|required');
        $this->form_validation->set_rules('usertype','Usertype','trim|xss_clean|required');
        $this->form_validation->set_rules('username','User Name','trim|xss_clean|required');

        if($this->form_validation->run() == TRUE)
        {
               $Campus = $this->input->post('campus',TRUE);
		//check for duplicate
	        $datadup = array('roleid' => $_POST['role_name'],'usertype'=>$_POST['usertype'],'userid'=>$_POST['username']);
               		$datauserrole = array(
                	//	'scid'=>$this->scid,
                		'scid'=>$Campus,
                		'deptid'=>$_POST['dept_name'],
                		'roleid'=>$_POST['role_name'],
                		'usertype'=>$_POST['usertype'],
                		'userid'=>$_POST['username'],
                		'ext1'=>'null',
          		);
       	$this->is_exist = $this->commodel->isduplicatemore('user_role_type',$datadup);
      	if ($this->is_exist == 1)
        	{
            		//$this->form_validation->set_message('isduplicateuserrole', 'Map user role already exit');
			$this->session->set_flashdata('err_message','Map user role already exits.');
			redirect('map/userroletype');
            		return false;
		}

          else{

            $userrole=$this->commodel->insertrec('user_role_type', $datauserrole);
          /**Geting value according to 'id' and using these values for maintaing logs*/
             $this->username = $this->commodel->loginmodel->get_listspfic1('edrpuser','username','id',$_POST['username'])->username;
             $this->rolename = $this->commodel->get_listspfic1('role','role_name', 'role_id', $_POST['role_name'])->role_name;
            if(! $userrole )
            {
                 $this->logger->write_logmessage("error","Error  in maping user with role"  .$username.$rolename.$usertype);
                 $this->logger->write_dblogmessage("error","Error  in maping user with role" .$username.$rolename.$usertype);
                 log_message('debug', ' Problem in maping user with role' .$username.$rolename.$usertype);
                 $this->session->set_flashdata('err_message','Error in maping user with role -' .$username.$rolename.$usertype);
                 redirect('map/userroletype');

            }
            else{

                 $this->logger->write_logmessage("insert","Map user with role", "Map user with role successfully.....".$username.$rolename.$usertype);
                 $this->logger->write_dblogmessage("insert","Map user with role", "Map user with role successfully....." .$username.$rolename.$usertype);
                 $this->session->set_flashdata("success", "Record added successfully "."["." UserName : "." ".$this->username.", "."RoleName : "." " .$this->rolename." ". ", "."UserType : "." " .$_POST['usertype']." ". "]");
                 redirect("map/viewuserrole");

            }

        }//is duplicate
     }//if
  
   }

        $this->load->view('map/userroletype');
 }

      public function deleteuserrole($id)
      {
           /**Geting value according to 'id' and using these values for maintaing logs*/
          $username = $this->input->post('username',TRUE);
          $this->username = $this->commodel->loginmodel->get_listspfic1('edrpuser','username','id',$username)->username;
          $rolename= $this->input->post('role_name',TRUE);
          $this->rolename = $this->commodel->get_listspfic1('role','role_name', 'role_id', $rolename)->role_name;
          $roledflag=$this->commodel->deleterow('user_role_type','id', $id);
          if(!$roledflag)
          {
            $this->logger->write_message("error", "Error in maping with user role deleting  " ."[role_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error in mapping with user roledeleting role "." [role_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in mapping with user role Deleting role - ', 'error');
            redirect('map/viewuserrole');
           return;
          }
        else{
             $this->logger->write_logmessage("delete", "Deleted map with user role " . "[role_id:" . $id . "] deleted successfully.. " );
             $this->logger->write_dblogmessage("delete", "Deleted map with user role" ." [role_id:" . $id . "] deleted successfully.. " );
             $this->session->set_flashdata("success", "Record deleted successfully "."["." UserName : "." ".$this->username." "."RoleName : "." " .$this->rolename." ". "]" );
            redirect('map/viewuserrole');
           }
        $this->load->view('map/viewuserrole',$data);
    }
   
   /**This function is used for update record of  maped user with role 
    * @param type $id
    */

        public function edituserrole($id){
	$this->roleresult = $this->commodel->get_listspfic2('role','role_id', 'role_name');
        $this->db->from('user_role_type')->where('id', $id);
        $eset_data_q = $this->db->get();
        $editeset_data = $eset_data_q->row();
   
        /* Form fields */
        $data['scid']= array(
            //'value' =>$editeset_data->scid,
            'value' =>$this->commodel->get_listspfic1('study_center','sc_name', 'sc_id',$editeset_data->scid)->sc_name,
            'size'  =>'35',
            'readonly'=>'true',
        );
               
        $data['username'] =array(
            'value' => $this->loginmodel->get_listspfic1('edrpuser','username','id',$editeset_data->userid)->username,
            'size'  =>'35',
            'readonly'=>'true',
        );
       
        $data['roleid'] = array(
           'size' => '35',
           'value' =>$this->commodel->get_listspfic1('role','role_name', 'role_id',$editeset_data->roleid)->role_name
        );
       
        $data['usertype'] = array(
            'value' => $editeset_data->usertype,
             'size' => '35',
            
        );
         
        $data['deptid'] = array(
            'value' =>$this->commodel->get_listspfic1('Department','dept_name', 'dept_id',$editeset_data->deptid)->dept_name,
            'size'=>'35',
            'readonly'=>'true',
        );
        
        $data['id'] = $id;
        
        /*Form Validation*/
        $this->form_validation->set_rules('roleid','Rolename','trim|xss_clean|required');
        $this->form_validation->set_rules('usertype','Usertype','trim|xss_clean|required');

         /* Re-populating form */
   
        if ($_POST)
        {
               $data['roleid']['value'] = $this->input->post('roleid', TRUE);
               $data['usertype']['value'] = $this->input->post('usertype', TRUE);
                 
        }
     
        if ($this->form_validation->run() == FALSE)
        {
            //echo "this is testing...2";
            $this->session->set_flashdata(validation_errors(), 'error');
            $this->load->view('map/edituserrole',$data);
        }
        else
        {    
            $data_roleid = $this->input->post('roleid', TRUE);
            $data_usertype = $this->input->post('usertype', TRUE);
             
            $data_eid = $id;
           
            $update_data = array(
            'roleid'=>$data_roleid,
            'usertype'=>$data_usertype,
          
            );
        $datadup = array('roleid' =>$data_roleid,'usertype'=>$data_usertype,'userid'=>$id);  
        $this->is_exist = $this->commodel->isduplicatemore('user_role_type',$update_data);
        if ($this->is_exist == 1)
                {
                        //$this->form_validation->set_message('isduplicateuserrole', 'Map user role already exit');
                        $this->session->set_flashdata('err_message','Map user role already exits.');
                        redirect('map/viewuserrole');
                        return false;
                }

          else{
            $result=$this->commodel->updaterec('user_role_type', $update_data,'id',$id);
             //$this->username = $this->commodel->loginmodel->get_listspfic1('edrpuser','username','id',$_POST['username'])->username;
            // $rolename= $this->input->post('role_name',TRUE);
            $this->rolename = $this->commodel->get_listspfic1('role','role_name', 'role_id', $data_roleid)->role_name;
            if(! $result)
            {
              $this->logger->write_logmessage("error", "Error in update study center with program seat " . $rolename."rolename--- "." changed by".$data_usertype);
              $this->logger->write_dblogmessage("error", "Error in update study center with program seat" . $rolename."rolename--- "." changed by".$data_usertype );
              log_message('debug', 'Problem in maping studycenter with program seat'. $rolename."rolename--- "." changed by".$data_usertype);
              $this->session->set_flashdata('err_message','Error in update study center with program seat - '. $rolename."rolename--- "." changed by".$data_usertype);
                redirect("map/edituserrole");
            }
            else {  
                  /*old role name*/ 
		$this->oldrole=$this->commodel->get_listspfic1('role','role_name', 'role_id',$editeset_data->roleid)->role_name;       
                $this->logger->write_logmessage("update", "updated user with role "  . $data_roleid."rolename--- "." changed by".$data_usertype);
                $this->logger->write_dblogmessage("update", "updated user with role "  . $data_roleid."rolename--- "." changed by".$data_usertype);
                $this->session->set_flashdata("success", 'Record updated successfully.... '." "."["." " ."Role:"." ".$this->oldrole." "."changed by" ." "."Role: ". $this->rolename." "."and"." "."Usertype:"." ".$editeset_data->usertype." "."changed by"." ".$data_usertype." "."]");
                redirect("map/viewuserrole");
                               
          
             }
	}	
      }  
                        
  }

 /****************************************** Map scheme with department ********************************************/


public function viewschemedept()
     {
        $this->result = $this->SIS_model->get_list('map_scheme_department');
        $this->logger->write_logmessage("view"," View map scheme with department setting", "map scheme with department details...");
        $this->logger->write_dblogmessage("view"," View map scheme with department setting", "map scheme with department setting details...");
        $this->load->view('map/viewschemedept',$this->result);
     }

public function schemedept(){
                  $this->scresult = $this->commodel->get_listspfic2('study_center','sc_id', 'sc_name');
        $this->schresult = $this->SIS_model->get_listspfic2('scheme_department','sd_id', 'sd_name');
   
        if(isset($_POST['schemedept'])) {
        $this->form_validation->set_rules('campus','Campus Name','trim|xss_clean|required');
        $this->form_validation->set_rules('dept_name','Departname','trim|xss_clean|required');
        $this->form_validation->set_rules('scheme','Scheme List','trim|xss_clean|required');


            if($this->form_validation->run()==TRUE){

            $data = array(
                'msd_scid'=>ucwords(strtolower($_POST['campus'])),
                'msd_deptid'=>strtoupper($_POST['dept_name']),
                'msd_schmid'=>$_POST['scheme']

            );
           $msdflag=$this->SIS_model->insertrec('map_scheme_department', $data) ;
           if(!$msdflag)
           {
                $this->logger->write_logmessage("insert"," Error in adding map with scheme department ", " map with scheme department data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding map with scheme department ", " map with scheme department data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding map with scheme department - ' . $_POST['msdname'] , 'error');
                $this->load->view('map/schemedept');
           }
          else{
                $this->logger->write_logmessage("insert"," add map with scheme department ", "map with scheme department record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add map with scheme department ", "map with scheme department record added successfully..." );
                $this->session->set_flashdata("success", "Map with Scheme Department added successfully...");
                redirect("map/viewschemedept", "refresh");
              }
           }

        }
      $this->load->view('map/schemedept');
   }

/**This function is used for update Map with Scheme Department records
     * @param type $id
     * @return type
     */


       public function editschemedept($msd_id) {
	$this->schresult = $this->SIS_model->get_listspfic2('scheme_department','sd_id', 'sd_name');
        $msd_data_q=$this->SIS_model->get_listrow('map_scheme_department','msd_id', $msd_id);
         
        if ($msd_data_q->num_rows() < 1)
        {
           redirect('setup/editschemedept');
        }
      $MapWithSchemeDepartment_data = $msd_data_q->row();
        
        /* Form fields */



        $data['msd_scid'] = array(
            'name' => 'msd_scid',
            'id' => 'msd_scid',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$MapWithSchemeDepartment_data->msd_scid)->sc_name, 
           'readonly' => 'readonly'
        );


        $data['msd_deptid'] = array(
           'name' => 'msd_deptid',
           'id' => 'msd_deptid',
           'maxlength' => '50',
           'size' => '40',
           'value' => $this->commodel->get_listspfic1('Department','dept_name', 'dept_id',$MapWithSchemeDepartment_data->msd_deptid)->dept_name,
            'readonly' => 'readonly'
        );


       $data['msd_schmid'] = array(
           'name' => 'msd_schmid',
           'id' => 'msd_schmid',
           'maxlength' => '50',
           'size' => '40',
           'value' => $this->SIS_model->get_listspfic1('scheme_department','sd_name', 'sd_id',$MapWithSchemeDepartment_data->msd_schmid)->sd_name, 
        );


    $data['msd_id'] = $msd_id;

        $this->form_validation->set_rules('scheme','Scheme List','trim');


        if ($_POST)
        {
            $data['msd_schmid']['value'] = $this->input->post('msd_schmid', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('map/editschemedept', $data);
            return;
        }
        else
        {
            $msd_schmid = strtoupper($this->input->post('msd_schmid', TRUE));
            $logmessage = "";

         
            if($MapWithSchemeDepartment_data->msd_schmid != $msd_schmid)
                $logmessage = "Map with Scheme Department " .$MapWithSchemeDepartment_data->msd_schmid. " changed by " .$msd_schmid;

            $update_data = array(
               'msd_schmid' =>$msd_schmid,

            );

           $msdflag=$this->SIS_model->updaterec('map_scheme_department', $update_data, 'msd_id', $msd_id);
           if(!$msdflag)
            {
                $this->logger->write_logmessage("error","Error in update Map with Scheme Department ", "Error in Map with Scheme Department record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Map with Scheme Department ", "Error in Map with Scheme Department record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating Map with Scheme Department - ' . $logmessage . '.', 'error');
                $this->load->view('map/editschemedept', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Map with Scheme Department", "Map with Scheme Department record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Map with Scheme Department", "Map with Scheme Department record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Map with Scheme Department record updated successfully...');
                redirect('map/viewschemedept/');
                }
        }//else
        redirect('map/editschemedept/');
    }







}
    
