<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studentrecord.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Studentrecord extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "logmodel");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usrmodel");
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
        	$whdata= array('sfee_smid' => $smid);
        	$this->result = $this->commodel->get_listarry('student_fees','*',$whdata);
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
	if (empty ($sfee_id)){
		$sfeeid=$this->commodel->get_listspficarry('student_fees','sfee_id','sfee_smid ',$smid);
                foreach($sfeeid as $row1){
                	$sfee_id= $row1->sfee_id;
                }
	}

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
	$prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_id',$spid)->sp_programid;
        $this->prgname =$this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
        $this->branch =$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
       
       //get program(semesters) of a student 
  /*      $stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$smid);
        $degree_id = $stud_prg_rec->row()->sp_programid;
        $this->noofsemester = sizeof($stud_prg_rec->result());
        if($this->noofsemester == 1)
        {
            $this->acadyear = $this->usrmodel->getcurrentAcadYear();
        }
        else
            $this->acadyear  = $this->usrmodel->getcurrentAcadYear();
*/
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
   }


 
}

