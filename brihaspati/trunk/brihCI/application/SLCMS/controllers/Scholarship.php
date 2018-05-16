<?php

/* 
 * @name Scholarship.php
 * @author Krishna Sahu(krishnasahu2406@gmail.com)  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Scholarship extends CI_Controller
{
    function __construct() {
        parent::__construct();
	$this->load->model('common_model','commodel'); 
	$this->load->model("user_model","usermodel");
	$this->load->model('SIS_model','sismodel');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
}

 
/******************************* This function for  scholar type*******************************/

//This function display the Scholar
public function scholartype() {
		 $this->result = $this->commodel->get_list('scholarship');
		 $this->logger->write_logmessage("view"," View Announcement", " Announcement details...");
        	 $this->logger->write_dblogmessage("view"," View Announcement" , "Announcement record display successfully..." );
		 $this->load->view('scholarship/scholartype',$this->result);
                }

/******************************This function adds Scholarship type********************************/

public function addscholar() {
                 if(isset($_POST['addscholar'])) {
	    $this->form_validation->set_rules('sch_code','Scholarship code','trim|xss_clean|required|alpha_numeric');
            $this->form_validation->set_rules('sch_type','Scholarship Type','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('sch_name','Scholarship Name','trim|xss_clean|required|alpha_numeric_spaces|callback_isschExist');
            $this->form_validation->set_rules('sch_des','Scholarship Description','trim|xss_clean|required');
	    $this->form_validation->set_rules('sch_provider','Scholarship Provider','trim|xss_clean|required|alpha_numeric_spaces');	
	    $this->form_validation->set_rules('sch_startyear','Scholarship Start Year','trim|xss_clean|required');	
	    $this->form_validation->set_rules('sch_startdate','Scholarship Start Date','trim|xss_clean|required');	
	    $this->form_validation->set_rules('sch_enddate','Scholarship End Date','trim|xss_clean|required');	
            if($this->form_validation->run()==TRUE){

            $data = array(
                'sch_code'=>$_POST['sch_code'],
                'sch_type'=>ucwords(strtolower($_POST['sch_type'])),
                'sch_name'=>ucwords(strtolower($_POST['sch_name'])),
                'sch_des'=>ucwords(strtolower($_POST['sch_des'])),
		'sch_provider'=>ucwords(strtolower($_POST['sch_provider'])),
		'sch_startyear'=>$_POST['sch_startyear'],
		'sch_startdate'=>$_POST['sch_startdate'],
		'sch_enddate'=>$_POST['sch_enddate'],

            );

           $ltflag=$this->commodel->insertrec('scholarship', $data) ;
           if(!$ltflag)
           {
                $this->logger->write_logmessage("insert"," Error in adding  scholarship type ", " Scholarship data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding  scholarship type ", "Scholarship data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding scholarship type - ' . $_POST['name'] , 'error');
                $this->load->view('scholarship/addscholar');
           }
          else{
                $this->logger->write_logmessage("insert"," add scholarship type ", "Add Scholarship Type record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add scholarship type ", "Add Scholarship Type record added successfully..." );
                $this->session->set_flashdata("success", "Scholarship added successfully...");
                redirect("scholarship/scholartype", "refresh");
              
             }
           }

        }
      $this->load->view('scholarship/addscholar');
    }

public function isschExist($sch_name) {

        $is_exist = $this->commodel->isduplicate('scholarship','sch_name',$sch_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isschExist', 'scholarship Name [' .$sch_name. '] already exist.');
            return false;
        }
        else {
            return true;
        }
}
/********************** This function check for duplicate Scholar*********************/
/*
public function isScholarExist($sch_name) {

        $is_scholarist = $this->commodel->isduplicate('scholartype','sch_name',$sch_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isscholarExist', 'Scholarship is already exist.');
            return false;
        }
        else {
            return true;
        }
    }
*/



 /*******************This function is used for update Scholar records*****************/
    
public function editscholar($id) {

	$scholarrow=$this->commodel->get_listrow('scholarship','sch_id', $id);

 	if ($scholarrow->num_rows() < 1)
        {
            redirect('scholarship/editscholar');
        }
        $scholar_data = $scholarrow->row();
        /* Form fields */

               $data['sch_code'] = array(
               'name' => 'sch_code',
               'id' => 'sch_code',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_code,
               );
               $data['sch_type'] = array(
               'name' => 'sch_type',
               'id' => 'sch_type',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_type,
               );
               $data['sch_name'] = array(
               'name' => 'sch_name',
               'id' => 'sch_name',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_name,
               );
	       $data['sch_des'] = array(
               'name' => 'sch_des',
               'id' => 'sch_des',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_des,
               );
	       $data['sch_provider'] = array(
               'name' => 'sch_provider',
               'id' => 'sch_provider',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_provider,
               );
               $data['sch_startyear'] = array(
               'name' => 'sch_startyear',
               'id' => 'sch_startyear',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_startyear,
               );
      	       $data['sch_startdate'] = array(
               'name' => 'sch_startdate',
               'id' => 'sch_startdate',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_startdate,
	       );
      	       $data['sch_enddate'] = array(
               'name' => 'sch_enddate',
               'id' => 'sch_enddate',
               'maxlength' => '50',
               'size' => '40',
               'value' => $scholar_data->sch_enddate,
	       );
               $data['id'] = $id;
	/*Form Validation*/
        $this->form_validation->set_rules('sch_code','Scholar Code','trim|xss_clean|required|alpha_numeric');
        $this->form_validation->set_rules('sch_type','Scholar Type','trim|xss_clean|required|alpha_numeric_spaces');
	$this->form_validation->set_rules('sch_name','Scholar Name','trim|xss_clean|required|alpha_numeric_spaces');
	$this->form_validation->set_rules('sch_des','Scholar Description','trim|xss_clean|required');
	$this->form_validation->set_rules('sch_provider','Scholar Provider','trim|xss_clean|required|alpha_numeric_spaces');
	$this->form_validation->set_rules('sch_startyear','Scholar Start Year','trim|xss_clean|required');
	$this->form_validation->set_rules('sch_startdate','Scholar Start Date','trim|xss_clean|required');
	$this->form_validation->set_rules('sch_enddate','Scholar End Date','trim|xss_clean|required');

        /* Re-populating form */
        if ($this->form_validation->run() == TRUE)
        {
            $data['sch_code']['value'] = $this->input->post('sch_code', TRUE);
            $data['sch_type']['value'] = $this->input->post('sch_type', TRUE);
            $data['sch_name']['value'] = $this->input->post('sch_name', TRUE);
            $data['sch_des']['value'] = $this->input->post('sch_des', TRUE);
            $data['sch_provider']['value'] = $this->input->post('sch_provider', TRUE);
            $data['sch_startyear']['value'] = $this->input->post('sch_startyear', TRUE);
            $data['sch_startdate']['value'] = $this->input->post('sch_startdate', TRUE);
 	    $data['sch_enddate']['value'] = $this->input->post('sch_enddate', TRUE);      
 
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('scholarship/editscholar', $data);
            return;
        }
	else{
            $sch_code =$this->input->post('sch_code', TRUE);
	    $sch_type =ucwords(strtolower($this->input->post('sch_type', TRUE)));
	    $sch_name =ucwords(strtolower($this->input->post('sch_name', TRUE)));
	    $sch_des =ucwords(strtolower($this->input->post('sch_des', TRUE)));
	    $sch_provider =ucwords(strtolower($this->input->post('sch_provider', TRUE)));
	    $sch_startyear =$this->input->post('sch_startyear', TRUE);
	    $sch_startdate =$this->input->post('sch_startdate', TRUE);
            $sch_enddate = $this->input->post('sch_enddate', TRUE);
            $logmessage = "";

            if($scholar_data->sch_code != $scholar_data)
                $logmessage = "Add Scholar Code" .$scholar_data->sch_code. " changed by " .$sch_code;
            if($scholar_data->sch_type != $scholar_data)
                $logmessage = "Add Scholar Type" .$scholar_data->sch_type. " changed by " .$sch_type;
	    if($scholar_data->sch_name != $scholar_data)
                $logmessage = "Add Scholar Name" .$scholar_data->sch_name. " changed by " .$sch_name;
	    if($scholar_data->sch_des != $scholar_data)
                $logmessage = "Add Scholar Description" .$scholar_data->sch_des. " changed by " .$sch_des;
            if($scholar_data->sch_provider != $scholar_data)
                $logmessage = "Add Scholar Provider" .$scholar_data->sch_provider. " changed by " .$sch_provider;
            if($scholar_data->sch_startyear != $scholar_data)
                $logmessage = "Add Scholar Start Year" .$scholar_data->sch_startyear. " changed by " .$sch_startyear;
            if($scholar_data->sch_startdate != $scholar_data)
                $logmessage = "Add Scholar Start Date" .$scholar_data->sch_startdate. " changed by " .$sch_startdate;
            if($scholar_data->sch_enddate != $scholar_data)
                $logmessage = "Add Scholar End Date " .$scholar_data->sch_enddate. " changed by " .$sch_enddate;
            

            $update_data = array(
               'sch_code' => $sch_code,
	       'sch_type' => $sch_type,
               'sch_name' => $sch_name,
               'sch_des' => $sch_des,
               'sch_provider' => $sch_provider,
               'sch_startyear' => $sch_startyear,
               'sch_startdate' => $sch_startdate,
               'sch_enddate' => $sch_enddate,
               
            );

	    $scholardflag=$this->commodel->updaterec('scholarship', $update_data,'sch_id', $id);
            if(!$scholardflag)
            {
                $this->logger->write_logmessage("error","Edit scholar Setting error", "Edit scholar Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit scholar Setting error", "Edit scholar Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating scholar - ' . $logmessage . '.', 'error');
                $this->load->view('scholarship/editscholar', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Scholar Setting", "Edit Scholar Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit Scholar Setting", "Edit Scholar Setting details. $logmessage ");
                $this->session->set_flashdata('success','Scholarship details updated successfully..');
                redirect('scholarship/scholartype/');
                }
        }//else
   }//end edit scholar function

/**********************************Student Registration Form********************************/
public function schreg() {
		$this->schname=$this->commodel->get_listspfic2('scholarship','sch_id', 'sch_name,sch_code');
		$suid=$this->session->userdata('id_user');
		$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		$this->cacadyer = $this->usermodel->getcurrentAcadYear();
		$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
		$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
		$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
		$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;
		$this->email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
		$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
		$this->dobb=explode(" ",$this->dob);
		$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
		$this->religname=$this->commodel->get_listspfic1('student_master','sm_religion','sm_id',$Stuid)->sm_religion;
		//program name
		$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->progid = $this->commodel->get_listspfic1('program','prg_id','prg_name',$this->pname)->prg_id;
		//address
		$this->schadd=$this->commodel->get_listspfic1('student_parent','spar_caddress','spar_smid',$Stuid)->spar_caddress;
		$this->schcity=$this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$Stuid)->spar_ccity;
		
		$this->schstat=$this->commodel->get_listspfic1('student_parent','spar_cstate','spar_smid',$Stuid)->spar_cstate;
		$this->schpin=$this->commodel->get_listspfic1('student_parent','spar_cpincode','spar_smid',$Stuid)->spar_cpincode;
		$this->schcounname=$this->commodel->get_listspfic1('student_parent','spar_ccountry','spar_smid',$Stuid)->spar_ccountry;
		$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
		//department name
		$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
		$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;

		//branch name
		$this->brid=$this->commodel->get_listspfic1('student_program','sp_branch','sp_smid',$Stuid)->sp_branch;
		$this->brname=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$this->brid)->prg_branch;
	
		$this->cdate = date('d-m-Y');
		$this->pcity=$this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$Stuid)->spar_ccity;
		
		 if(isset($_POST['schreg'])) {
		// $this->form_validation->set_rules('sa_lastyerres','Last Year Result','trim|xss_clean|required|alpha_numeric_spaces');
	    	 $this->form_validation->set_rules('sa_bname','Bank Name','trim|xss_clean|required|alpha_numeric_spaces');
            	 $this->form_validation->set_rules('sa_ifscno','ifscno','trim|xss_clean|required|alpha_numeric');
            	 $this->form_validation->set_rules('sa_accno','Account Number','trim|xss_clean|required|numeric');
            	 $this->form_validation->set_rules('sa_branch','Bank Branch Name','trim|xss_clean|required|alpha_numeric_spaces');
		 $this->form_validation->set_rules('sa_name','Scholar Name','trim|xss_clean|required|alpha_numeric_spaces|callback_isschregExist');
	
           	 if($this->form_validation->run()==TRUE){

          	 $data = array(
		'sa_userid'=>$suid,
		//'sa_lastyerres'=>ucwords(strtoupper($_POST['sa_lastyerres'])),
                'sa_bname'=>ucwords(strtoupper($_POST['sa_bname'])),
                'sa_ifscno'=>ucwords(strtoupper($_POST['sa_ifscno'])),
                'sa_accno'=>$_POST['sa_accno'],
                'sa_branch'=>ucwords(strtoupper($_POST['sa_branch'])),
		'sa_name'=>ucwords(strtoupper($_POST['sa_name'])),
           	 );
		
		$schflag=$this->commodel->insertrec('schapply', $data) ;
		
		if(!$schflag)
           {
                $this->logger->write_logmessage("insert"," Error in registration ", " Scholarship Apply data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in registration ", "Scholarship Apply data insert error . " );
                $this->session->set_flashdata('err_message','Error in registration - ' . $_POST['sa_name'] , 'error');
                $this->load->view('scholarship/schreg');
           }
          else{
                $this->logger->write_logmessage("insert"," add schapply ", "Scholarship Apply record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add schapply ", "Scholarship Apply record added successfully..." );
                $this->session->set_flashdata("success", "Scholarship registration successfully done...");
                redirect("scholarship/schstatus");
              }
	  
          // $schflag=$this->commodel->insertrec('schapply', $data) ;
}
}

$this->load->view('scholarship/schreg');
    }


public function isschregExist($sa_name) {

        $is_exist = $this->commodel->isduplicate('schapply','sa_name',$sa_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isschregExist', 'This Scholarship Name is already exist.');
            return false;
        }
        else {
            return true;
        }
}



/****************************************** scholarship Apply Details********************************/

public function schapplydet() {
	
	$id=$this->session->userdata('id_user');
	$whdata = array('sa_status'=>0);
	$record= $this->commodel->get_listspficemore('schapply','sa_id,sa_userid,sa_rejres,sa_name,sa_bname,sa_ifscno,sa_accno,sa_branch,sa_status',$whdata);
	$i=0;
	//   get one by one record
	foreach ($record as $row)
	{
	
	//$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
	//$this->schname=$this->commodel->get_listspfic2('schapply','sa_id', 'sa_name');
	$suid=$this->commodel->get_listspfic1("schapply","sa_userid")->sa_userid;
	$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
	$this->cacadyer = $this->usermodel->getcurrentAcadYear();
	$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
	$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
	$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
	$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
	$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
	$dob=explode(" ",$this->dob);	
	$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
	$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;
	$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
	$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
	$this->email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
	$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
	$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;
	$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
	$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
	$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;//print_r($this->pname);die;
	$this->said=$this->commodel->get_listspfic1('schapply','sa_name','sa_id',$row->sa_id)->sa_name;
	$this->saname=$this->commodel->get_listspfic1('scholarship','sch_name','sch_id',$this->said)->sch_name;
	$ldata['cacadyer'] =  $this->cacadyer;
	$ldata['stname'] =  $this->name;
	$ldata['stfname'] =  $this->fathname;
	$ldata['stgender'] =  $this->gender;
	$ldata['stdob'] =  $dob[0];//$this->dob;
	$ldata['stcatename'] =  $this->catename;
	$ldata['stmobile'] =  $this->mobile;
	$ldata['stuid'] =  $this->uid;
	$ldata['stemail'] =  $this->email;
	$ldata['stdepname'] =  $this->depname;
	$ldata['stsem'] =  $this->sem;
	$ldata['stpname'] =  $this->pname;
	$ldata['stsaname'] =  $this->saname;
	$ldata['sid'] =  $row->sa_id;
	$this->fldata[$i] = $ldata;
	//$this->fldata=$this->commodel->get_list('schapply');
	$i++;
	}
	$this->load->view('scholarship/schapplydet');

  }

/****************************************************Scholarship Varification***************************************/

public function schvarify($id) {
	$suid=$this->commodel->get_listspfic1("schapply","sa_userid")->sa_userid;
	$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
	$applyrow=$this->commodel->get_listrow('schapply','sa_id',$id);
	$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
	$this->brid=$this->commodel->get_listspfic1('student_program','sp_branch','sp_smid',$Stuid)->sp_branch;
	$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
	$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
	$this->said=$this->commodel->get_listspfic1('schapply','sa_name','sa_id',$id)->sa_name;
	$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
	$dob=explode(" ",$this->dob);
 	if ($applyrow->num_rows() < 1)
        {
            redirect('scholarship/schvarify');
        }
	
        $apply_data_q = $applyrow->row();
		$data['sa_id'] = array(
               'name' => 'sa_id',
               'id' => 'sa_id',
               'maxlength' => '50',
               'size' => '40',
               'value' =>  $apply_data_q->sa_id,
		'readonly' => 'readonly'
               );

		$data['sm_fname'] = array(
               'name' => 'sm_fname',
               'id' => 'sm_fname',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname,
		'readonly' => 'readonly'
               );


		$data['spar_mothername'] = array(
               'name' => 'spar_mothername',
               'id' => 'spar_mothername',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername,
		'readonly' => 'readonly'
               );

		$data['spar_fathername'] = array(
               'name' => 'spar_fathername',
               'id' => 'spar_fathername',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername,
		'readonly' => 'readonly'
               );

		$data['sm_gender'] = array(
               'name' => 'sm_gender',
               'id' => 'sm_gender',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender,
		'readonly' => 'readonly'
               );

		$data['sm_dob'] = array(
               'name' => 'sm_dob',
               'id' => 'sm_dob',
               'maxlength' => '50',
               'size' => '40',
               'value' => $dob[0],
		'readonly' => 'readonly'
               );

		$data['cat_name'] = array(
               'name' => 'cat_name',
               'id' => 'cat_name',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name,
		'readonly' => 'readonly'
               );

		$data['sp_semester'] = array(
               'name' => 'sp_semester',
               'id' => 'sp_semester',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester,
		'readonly' => 'readonly'
               );	

		$data['spar_caddress'] = array(
               'name' => 'spar_caddress',
               'id' => 'spar_caddress',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_caddress','spar_smid',$Stuid)->spar_caddress,
		'readonly' => 'readonly'
               );

		$data['spar_ccity'] = array(
               'name' => 'spar_ccity',
               'id' => 'spar_ccity',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$Stuid)->spar_ccity,
		'readonly' => 'readonly'
               );

		$data['spar_cstate'] = array(
               'name' => 'spar_cstate',
               'id' => 'spar_cstate',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_cstate','spar_smid',$Stuid)->spar_cstate,
		'readonly' => 'readonly'
               );

		$data['spar_ccountry'] = array(
               'name' => 'spar_ccountry',
               'id' => 'spar_ccountry',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_ccountry','spar_smid',$Stuid)->spar_ccountry,
		'readonly' => 'readonly'
               );

		$data['spar_cpincode'] = array(
               'name' => 'spar_cpincode',
               'id' => 'spar_cpincode',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_parent','spar_cpincode','spar_smid',$Stuid)->spar_cpincode,
		'readonly' => 'readonly'
               );
	
		$data['sm_mobile'] = array(
               'name' => 'sm_mobile',
               'id' => 'sm_mobile',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile,
		'readonly' => 'readonly'
               );
		
		$data['sm_uid'] = array(
               'name' => 'sm_uid',
               'id' => 'sm_uid',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid,
		'readonly' => 'readonly'
               );

		$data['sm_email'] = array(
               'name' => 'sm_email',
               'id' => 'sm_email',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email,
		'readonly' => 'readonly'
               );

		$data['dept_name'] = array(
               'name' => 'dept_name',
               'id' => 'dept_name',
               'maxlength' => '50',
               'size' => '40',
               'value' =>$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name,
		'readonly' => 'readonly'
               );

		$data['sm_religion'] = array(
               'name' => 'sm_religion',
               'id' => 'sm_religion',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('student_master','sm_religion','sm_id',$Stuid)->sm_religion,
		'readonly' => 'readonly'
               );

		$data['prg_branch'] = array(
               'name' => 'prg_branch',
               'id' => 'prg_branch',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('program','prg_branch','prg_id',$this->brid)->prg_branch,
		'readonly' => 'readonly'
               );

		$data['prg_name'] = array(
               'name' => 'prg_name',
               'id' => 'prg_name',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name,
		'readonly' => 'readonly'
               );

		
		$data['sa_name'] = array(
               'name' => 'sa_name',
               'id' => 'sa_name',
               'maxlength' => '50',
               'size' => '40',
               'value' => $this->commodel->get_listspfic1('scholarship','sch_name','sch_id',$this->said)->sch_name,
		'readonly' => 'readonly'
               );

		$data['sa_lastyerres'] = array(
               'name' => 'sa_lastyerres',
               'id' => 'sa_lastyerres',
               'maxlength' => '50',
               'size' => '40',
               'value' => $apply_data_q->sa_lastyerres,
		'readonly' => 'readonly'
               );
               
               $data['sa_bname'] = array(
               'name' => 'sa_bname',
               'id' => 'sa_bname',
               'maxlength' => '50',
               'size' => '40',
               'value' => $apply_data_q->sa_bname,
		'readonly' => 'readonly'
               );
	       $data['sa_ifscno'] = array(
               'name' => 'sa_ifscno',
               'id' => 'sa_ifscno',
               'maxlength' => '50',
               'size' => '40',
               'value' => $apply_data_q->sa_ifscno,
		'readonly' => 'readonly'
               );
	       $data['sa_accno'] = array(
               'name' => 'sa_accno',
               'id' => 'sa_accno',
               'maxlength' => '50',
               'size' => '40',
               'value' => $apply_data_q->sa_accno,
		'readonly' => 'readonly'
               );
               $data['sa_branch'] = array(
               'name' => 'sa_branch',
               'id' => 'sa_branch',
               'maxlength' => '50',
               'size' => '40',
               'value' => $apply_data_q->sa_branch,
		'readonly' => 'readonly'
               );
	       $data['sa_status'] = array(
               'name' => 'sa_status',
               'id' => 'sa_status',
               'maxlength' => '50',
               'size' => '40',
               'value' => $apply_data_q->sa_status,
		'readonly' => 'readonly'
               );
               $data['id'] = $id;
		// Approved Button code
		if(isset($_POST['approved']))
	        {
                $data['sa_status']['value'] = $this->input->post('sa_status', TRUE);
        	 
 		$sa_status = $this->input->post('sa_status', TRUE);
		$logmessage = "";
		if($apply_data_q->sa_status != $sa_status)
                $logmessage = "Scholarship Status " .$apply_data_q->sa_status. " changed by " .$sa_status;
                $update_data = array(              
    		'sa_status' =>  1,
	        );
	        $saflag=$this->commodel->updaterec('schapply', $update_data, 'sa_id', $id);
	        if(!$saflag)
            	{
                $this->logger->write_logmessage("error","Error in updating Scholarship Approved ", "Error in Scholarship Approved. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating Scholarship Approved ", "Error in Scholarship Approved. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating Scholarship Approved - ' . $logmessage . '.', 'error');
                $this->load->view('scholarship/schapplydet', $data);
            	}
            	else{
                $this->logger->write_logmessage("update","Scholarship Approved", "Scholarship approved... $logmessage . " );
                $this->logger->write_dblogmessage("update","Scholarship Approved", "Scholarship approved... $logmessage ." );
                $this->session->set_flashdata('success','Scholarship approved');
		
		redirect('scholarship/approvedscholar/');
                }}
		// Rejected Button code
		if(isset($_POST['rej']))
	        {
		$this->commodel->get_listrow('schapply','sa_id', $id);
		redirect('scholarship/schrejres/' . $id);
		}

	$this->load->view('scholarship/schvarify', $data);
        }

/****************************************Scholarship Approved**************************************/

 public function approvedscholar(){
	$id=$this->session->userdata('id_user');
	$whdata = array('sa_status'=>1);
	$record= $this->commodel->get_listspficemore('schapply','sa_userid,sa_name,sa_status',$whdata);
	$i=0;
	//   get one by one record
	foreach ($record as $row)
	{
	
	$this->schname=$this->commodel->get_listspfic2('schapply','sa_id', 'sa_name');
	$suid=$this->commodel->get_listspfic1("schapply","sa_userid")->sa_userid;
	$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
	$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
	//$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
	$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
	$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
	$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
	$dob=explode(" ",$this->dob);
	$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
	$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;
	$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
	$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
	$this->email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
	$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
	$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;
	$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
	$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
	$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
	//print_r($Stuid);
	$this->said=$this->commodel->get_listspfic1('schapply','sa_name','sa_name',$row->sa_name)->sa_name;	
	$this->saname=$this->commodel->get_listspfic1('scholarship','sch_name','sch_id',$this->said)->sch_name;
	$ldata['stname'] =  $this->name;
	$ldata['stfname'] =  $this->fathname;
	$ldata['stgender'] =  $this->gender;
	$ldata['stdob'] =  $dob[0];
	$ldata['stcatename'] =  $this->catename;
	$ldata['stmobile'] =  $this->mobile;
	$ldata['stuid'] =  $this->uid;
	$ldata['stemail'] =  $this->email;
	$ldata['stdepname'] =  $this->depname;
	$ldata['stsem'] =  $this->sem;
	$ldata['stpname'] =  $this->pname;
	$ldata['stsaname'] =  $this->saname;
	//$ldata['sid'] =  $row->sa_id;
	$this->fldata[$i] = $ldata;
	//$this->fldata=$this->commodel->get_list('schapply');
		$i++;
		}
$this->load->view('scholarship/approvedscholar');

}

/**************************************************Scholarship Rejected*****************************/

public function rejectedscholar(){
	$whdata = array('sa_status'=>2);
	$record= $this->commodel->get_listspficemore('schapply','sa_userid,sa_name,sa_rejres,sa_status',$whdata);
	$i=0;
	//   get one by one record
	foreach ($record as $row)
	{
	$this->schname=$this->commodel->get_listspfic2('schapply','sa_id', 'sa_name');
	$suid=$this->commodel->get_listspfic1("schapply","sa_userid")->sa_userid;
	$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
	$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
	//$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
	$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
	$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
	$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
	$dob=explode(" ",$this->dob);
	$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
	$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;
	$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
	$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
	$this->email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
	$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
	$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;
	$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
	$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
	$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
	//print_r($Stuid);
	$this->said=$this->commodel->get_listspfic1('schapply','sa_name','sa_name',$row->sa_name)->sa_name;	
	$this->saname=$this->commodel->get_listspfic1('scholarship','sch_name','sch_id',$this->said)->sch_name;
	
	$ldata['stname'] =  $this->name;
	$ldata['stfname'] =  $this->fathname;
	$ldata['stgender'] =  $this->gender;
	$ldata['stdob'] =  $dob[0];
	$ldata['stcatename'] =  $this->catename;
	$ldata['stmobile'] =  $this->mobile;
	$ldata['stuid'] =  $this->uid;
	$ldata['stemail'] =  $this->email;
	$ldata['stdepname'] =  $this->depname;
	$ldata['stsem'] =  $this->sem;
	$ldata['stpname'] =  $this->pname;
	$ldata['stsaname'] =  $this->saname;
	$ldata['sarejres'] = $row->sa_rejres;
	//$ldata['sid'] =  $row->sa_id;
	$this->fldata[$i] = $ldata;
	//$this->fldata=$this->commodel->get_list('schapply');
	$i++;
	}
   $this->load->view('scholarship/rejectedscholar');

}

/*************************************************Student Scholarship Status******************************/

public function schstatus() {

		
		  $this->result = $this->commodel->get_list('schapply');
		  $this->load->view('scholarship/schstatus');
        return;
}

/**************************************Scholarship Reject Reason***************************************/

public function schrejres($sa_id) {

	 $sch=$this->commodel->get_listrow('schapply','sa_id', $sa_id);
       if ($sch->num_rows() < 1)
        {
           redirect('scholarship/schvarify');
        }
        $schdata = $sch->row();
                
		  $data['sa_rejres'] = array(
            'name' => 'sa_rejres',
            'id' => 'sa_rejres',
            'maxlength' => '50',
            'size' => '40',
            'value' => $schdata->sa_rejres,
        );

        $data['sa_status'] = array(
            'name' => 'sa_status',
            'id' => 'sa_status',
            'value' => $schdata->sa_status,
        );
		  $sa_rejres = $this->input->post('sa_rejres', TRUE);
    	  $data['sa_id'] = $sa_id;

		  $this->form_validation->set_rules('sa_rejres','Reason for Rejecting Scholarship is ','required');
		          
        if ($_POST)
        {
		      $data['sa_rejres']['value'] = $this->input->post('sa_rejres', TRUE);
				$data['sa_status']['value'] = $this->input->post('sa_status', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('scholarship/schrejres', $data);
            return;
        }
              
			   $logmessage = "";
				
            if($schdata->sa_rejres != $sa_rejres)
                $logmessage = "Reason for Rejecting Scholarship " .$schdata->sa_rejres. " changed by " .$sa_rejres;
			
				
            $update_data = array(              
               'sa_status' =>  2,
					'sa_rejres' => $sa_rejres
            );

           $ltflag=$this->commodel->updaterec('schapply', $update_data, 'sa_id', $sa_id);
					  
	 if(!$ltflag)
            {
                $this->logger->write_logmessage("error","Error in updating Scholarship ", "Error in Scholarship update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating Scholarship ", "Error in Scholarship update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating Scholarship - ' . $logmessage . '.', 'error');
                $this->load->view('scholarship/schrejres', $data);
            }
            else{
                $this->logger->write_logmessage("update","Scholarship Rejected", "Scholarship Rejected... $logmessage . " );
                $this->logger->write_dblogmessage("update","Scholarship Rejected", "Scholarship Rejected... $logmessage ." );
                $this->session->set_flashdata('err_message','Scholarship Rejected');
				    redirect('scholarship/rejectedscholar/');
                }
        
        redirect('scholarship/schrejres');     
}

}
