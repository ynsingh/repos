<?php

/* 
 * @name Map.php
 * @author Manorama Pal(palseema30@gmail.com)
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Map extends CI_Controller
{
    function __construct() {
        parent::__construct();
        /*Loading model calsses*/
        $this->load->model('Map_model',"mapmodel");
        $this->load->model('Common_model',"commodel"); 
        
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

        $data['papername'] = array('name' => 'papername','id' => 'papername','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectno'] = array('name' => 'subjectno','id' => 'subjectno','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectcode'] = array('name' => 'subjectcode','id' => 'subjectcode','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectshrname'] = array('name' => 'subjectshrname','id' => 'subjectshrname','maxlength' => '100','size' => '30','value' => '',);
        $data['subjectdesc'] = array('name' => 'subjectdesc','id' => 'subjectdesc','maxlength' => '100','size' => '30','value' => '',);
        $this->form_validation->set_rules('subjectname','Subject Name','trim|required');
        $this->form_validation->set_rules('subjecttype','Paper Category','trim|required');
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
                $insertdata_paper = array('subp_sub_id' => $data_sub[1],'subp_subtype' => $subject_type,'subp_paperno' => $subject_no,'subp_name' => ucwords(strtolower($paper_name)),'subp_code' => strtoupper($subject_code),'subp_short' => ucwords(strtolower($subject_shrname)),'subp_desp' => $subject_desc, 'subp_degree' => $data_prg[1],'subp_acadyear' => $acadyear,'creatorid' => $username,'createdate' => $currdate,'modifierid' => $username,'modifydate' => $currdate);

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


        if($_POST)
        {
            $papname = $this->input->post('papername',TRUE);
            $subjshrname = $this->input->post('subjectshrname',TRUE);
            $subjdesc = $this->input->post('subjectdesc',TRUE);
        }
        if($this->form_validation->run() == TRUE)
        {
            $currdate = date("y-m-d");
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
            
        }        
    
        $this->load->view('map/editprogramsubject',$data);    
    }
}    
