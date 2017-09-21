<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Faculty.php
 * @author Sharad Singh (sharad23nov@gmail.com)
 */

class Faculty extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "login");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usermodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function studentmarks() {
        /* set role id in session*/
	    $facultydata = [ 'id_role' => 2 ];
        $userid = $this->session->userdata('id_user');
        $facname = $this->session->userdata('username');
        $this->session->set_userdata($facultydata);

        //get current academic year

        $acadyear = $this->usermodel->getcurrentAcadYear();

        //get study center list in which faculty is register.

        $campusid=$this->commodel->get_listspfic1('user_role_type','scid','userid',$userid)->scid;
    
        //get faculty record

        $selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        $whrdata=array('pstp_scid' =>$campusid,'pstp_teachid' => $userid,'pstp_acadyear' => $acadyear);
        $facultyrec=$this->commodel->get_listspficemore('program_subject_teacher',$selectfield,$whrdata);
        $facultydata['facprgsublist']=$facultyrec;
        $stud_prg_rec ;
        $this->mst = 0;

        if(isset($_POST)){
            $prgname=$this->input->post('programname',TRUE);
            $prgsem=$this->input->post('semester',TRUE);
            $subpap=$this->input->post('subjectpaper',TRUE);

            //get faculty's subject/paper student list.

            $search = $this->input->post('search');
            if (isset($search)) 
            {
                $subfields=array('sp_smid','sp_deptid','sp_subid1','sp_subid2','sp_subid3','sp_subid4','sp_subid5','sp_subid6','sp_subid7','sp_subid8','sp_subid9','sp_subid10');
            //get campus code
                $campucode=$this->commodel->get_listspfic1('study_center','sc_code','sc_id',$campusid)->sc_code;
                $stdntdata=array('sp_sccode' => $campucode,'sp_programid' => $prgname,'sp_acadyear' => $acadyear,'sp_semester' => $prgsem);
                $stud_prg_rec = $this->commodel->get_listspficemore('student_program',$subfields,$stdntdata);
                //print_r($stud_prg_rec);

                $facultydata['studentdetail'] = $stud_prg_rec;
                //select exam type .

                $exmtype = $this->commodel->get_list('examtype');
                $facultydata['exmtype'] = $exmtype;
            }
            $facultydata['prgname'] = $prgname;
            $facultydata['acadyear'] = $acadyear;
            $facultydata['subpap'] = $subpap;

            $wrongmarks = array();
            $insertmarks;
            $uploadmarks = $this->input->post('Submit');

            if (isset($uploadmarks))
            {
                $prgid1=$this->input->post('prgid1',TRUE);
                $sizesmid = $this->input->post('studsize',TRUE);   
                $subid = $this->input->post('subid',TRUE);
                $papid = $this->input->post('papid',TRUE);
                $mmarks = $this->input->post('mmarks',TRUE);
                $typeexam = $this->input->post('examtype',TRUE);

                for ($i=0; $i<$sizesmid ;$i++)
                {
                    $studmid=  $this->input->post('stumid'.$i,TRUE);
                    $studmarksobtain =  $this->input->post('marks'.$i,TRUE);
                    $studgradesobtain = $this->input->post('grade'.$i,TRUE);
                    $studgradesobtain = ucwords(strtolower($studgradesobtain));
                    $fullname = "";
                    $wrmarks = $studmarksobtain;
                    if($studmarksobtain > $mmarks)
                    {
                        $studmarksobtain = 0;
                    }    
                    $insertmarksdata = array(
                        'smr_smid' => $studmid,
                        'smr_scid' => $campusid,
                        'smr_deptid' => '',
                        'smr_prgid' => $prgid1,
                        'smr_subid' => $subid,
                        'smr_papid' => $papid,
                        'smr_acadyear' => $acadyear,
                        'smr_sem' => $prgsem,
                        'smr_extyid' => $typeexam,
                        'smr_mmmarks' => $mmarks,
                        'smr_marks' => $studmarksobtain,
                        'smr_grade' => $studgradesobtain,
                        'smr_creatorid' => $facname,
                        'smr_createdate' => date('y-m-d'),
                        'smr_modifierid' => $facname,
                        'smr_modifydate' => date('y-m-d')
                    );           

                    $cwhere = array( 'smr_smid' => $studmid,
                                     'smr_prgid' => $prgid1,
                                     'smr_subid' => $subid,
                                     'smr_papid' =>$papid,
                                     'smr_sem' => $prgsem,
                                     'smr_extyid' => $typeexam
                    );
                    $cstudfield = array('smr_marks','smr_grade');
                    $cstudsubpap = $this->commodel->get_listspficemore('student_marks',$cstudfield,$cwhere);
                    
                    if(!empty($cstudsubpap))
                    {
                        $this->mst = 1;
                        $this->session->set_flashdata("msg", "Marks already uploaded,Please go 'View Student Marks' to view and update. ");
                    }
                    else{
                        $this->mst = 0;
                        $insertmarks = $this->commodel->insertrec('student_marks', $insertmarksdata); 
                        if(!$insertmarks)
                        {
                            $this->logger->write_logmessage("insert",$studmarksobtain."Marks can't not inserted in subject/paper - ".$subid."/".$papid, "of student (student id) ".$studmid." by ".$facname);
                            $this->logger->write_dblogmessage("insert",$studmarksobtain."Marks can't inserted in subject/paper - ".$subid."/".$papid, "of student (student id)".$studmid." by ".$facname);
                        }
                        else{
                            $this->logger->write_logmessage("insert",$studmarksobtain."Marks inserted in subject/paper - ".$subid."/".$papid, "of student ".$studmid." by ".$facname);
                            $this->logger->write_dblogmessage("insert",$studmarksobtain."Marks inserted in subject/paper - ".$subid."/".$papid, "of student ".$studmid." by ".$facname);
                            $this->session->set_flashdata("success", "Marks uploaded succesfully Please verify it ->View Student Marks");
                        }
                    }
                }
                //redirect('faculty/studentmarks');
            }//upload
        }//POST
        $this->load->view('faculty/studentmarks',$facultydata);
    }

    public function studentviewmarks() {
        /* set role id in session*/
        $facultydata = [ 'id_role' => 2 ];
        $userid = $this->session->userdata('id_user');
        $facname = $this->session->userdata('username');
        $this->session->set_userdata($facultydata);
        //get current academic year
        $acadyear = $this->usermodel->getcurrentAcadYear();
        //get study center list in which faculty is register.
        $campusid=$this->commodel->get_listspfic1('user_role_type','scid','userid',$userid)->scid;

        //get faculty record
        $selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        $whrdata=array('pstp_scid' =>$campusid,'pstp_teachid' => $userid,'pstp_acadyear' => $acadyear);
        //$whrdata=array('pstp_teachid' => $userid);
        $facultyrec=$this->commodel->get_listspficemore('program_subject_teacher',$selectfield,$whrdata);
        //print_r(sizeof($facultyrec));
        $facultydata['facprgsublist']=$facultyrec;
        $stud_prg_rec ;
        if(isset($_POST)){
            $prgname=$this->input->post('programname',TRUE);
            $prgsem=$this->input->post('semester',TRUE);
            $subpap=$this->input->post('subjectpaper',TRUE);
            $etype = $this->input->post('examtype',TRUE);

            //get faculty's subject/paper student list.

            $search = $this->input->post('search');
            $uploadmarks = $this->input->post('Submit');
            
            //select exam type .
            $exmtype = $this->commodel->get_list('examtype');
            $facultydata['exmtype'] = $exmtype;

            if (isset($search))
            {
                $subfields=array('sp_smid','sp_deptid','sp_subid1','sp_subid2','sp_subid3','sp_subid4','sp_subid5','sp_subid6','sp_subid7','sp_subid8','sp_subid9','sp_subid10');
            //get campus code
                $campucode=$this->commodel->get_listspfic1('study_center','sc_code','sc_id',$campusid)->sc_code;
                $stdntdata=array('sp_sccode' => $campucode,'sp_programid' => $prgname,'sp_acadyear' => $acadyear,'sp_semester' => $prgsem);
                $stud_prg_rec = $this->commodel->get_listspficemore('student_program',$subfields,$stdntdata);
                $facultydata['studentdetail'] = $stud_prg_rec;

            }
            $facultydata['prgname'] = $prgname;
            $facultydata['acadyear'] = $acadyear;
            $facultydata['prgsem'] = $prgsem;
            $facultydata['subpap'] = $subpap;
            $facultydata['etype'] = $etype;
        }
        //$facultydata['faculty_rec'] = $facultyrec;
        $this->load->view('faculty/studentviewmarks',$facultydata);
    }
}

