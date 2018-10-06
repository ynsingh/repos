<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Jslist.php
 * @author Nagendra Kumar Singh nksinghiitk@gmail.com
 */

class Jslist extends CI_Controller
{
 
    	function __construct() {
        	parent::__construct();
        	$this->load->model('Common_model',"commodel");
        	$this->load->model('Login_model',"lgnmodel"); 
        	$this->load->model('SIS_model',"sismodel");
        	$this->load->model('Dependrop_model',"depmodel");

        	if(empty($this->session->userdata('id_user'))) {
            		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('welcome');
        	}
    	}
 
    	public function index(){
    	}

	/* This function has been created for get list of Designation on the basis of  selected Working type */
        public function getwdesiglist(){
                $groups = $this->input->post('wtype');

                $datawh=array('desig_type' => $groups);
		$rlid=$this->session->userdata('id_role');
       	        $usrid=$this->session->userdata('id_user');
//	        if ($rlid == 5){
//	                $deptid = '';
  //              	$whdatad = array('userid' => $usrid,'roleid' => $rlid);
    //    	        $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
//	                foreach($resu as $rw){
  //                      	$deptid=$rw->deptid;
    //            	}
        	//        $datawh['emp_dept_code'] = $deptid;
//	        }

		$whorder = "desig_name asc";
//		echo $deptid; die();
        	$grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
                //$grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
                $desig_select_box ='';
                $desig_select_box.='<option value="">--Select Designation--';
		$desig_select_box.='<option value='.All.'>'.All. ' ';
	        if(count($grp_data)>0){
	                foreach($grp_data as $grprecord){
                        	$desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
                	}
        	}
                echo json_encode($desig_select_box);
        }

    	/* This function has been created for get list of Designation on the basis of  selected Group */
    	public function getgdesiglist(){
        	$groups = $this->input->post('group');
        	$datawh=array('desig_group' => $groups);
		$whorder = ("desig_name asc");
                $grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
//	        $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
        	$desig_select_box ='';
	        $desig_select_box.='<option value="">--Select Designation--';
        	foreach($grp_data as $grprecord){
	        	$desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
        	}
        	echo json_encode($desig_select_box);
    	}

    	/* This function has been created for get list of Designation on the basis of  selected Group and working type*/
    	public function getgwdesiglist(){
		$combid= $this->input->post('gwt');
		$parts = explode(',',$combid);
	        $datawh=array('desig_group' => $parts[0],'desig_type' => $parts[1]);
		$whorder = ("desig_name asc");
                $grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
//        	$grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
	        $desig_select_box ='';
        	$desig_select_box.='<option value="">--Select Designation--';
	        foreach($grp_data as $grprecord){
        		$desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
        	}
        	echo json_encode($desig_select_box);
	}    

	 /* This function has been created for get list of Pay scales on the basis of  selected Group, working type and designation*/
        public function getgwdesigpaylist(){
                $combid= $this->input->post('gwtdesig');
                $parts = explode(',',$combid);
                $datawh=array('desig_group' => $parts[0],'desig_type' => $parts[1],'desig_id'=> $parts[2]);
                $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_payscale',$datawh);
                $desig_select_box ='';
                $desig_select_box.='<option value="">--Select Payscale--';
                foreach($grp_data as $grprecord){
                        $desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_payscale.'' ;
                }
                echo json_encode($desig_select_box);
        }
	
    /* This function has been created for get the plan, non plan, ugc, ICAR, GOI shown against position */
    public function getemppnp(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],'sp_emppost' => $parts[3], 'sp_tnt' => $parts[4]);
        $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_plan_nonplan',$datawh);
        $emptype_select_box ='';
        $emptype_select_box.='<option value="">------ Select Plan  Non Plan -----------';
        if(!empty($emptype_data)){
            foreach($emptype_data as $empdata){
                $emptype_select_box.='<option value='.$empdata->sp_plan_nonplan.'>'.$empdata->sp_plan_nonplan.' ';
            }//foreach
        } //if close   
        echo json_encode($emptype_select_box);
    }
    
    /***** This function has been created for get the employee detail by pf no ********************************/
    public function getempdata(){
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $pfno= $this->input->post('emplypfno');
        $emp_data=$this->sismodel->get_listrow('employee_master','emp_code',$pfno);
        $empdetail = $emp_data->result();
        if(count($empdetail)>0){
            foreach($empdetail as $detail){
                $campus=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id',$detail->emp_scid)->sc_name;
                $campusbox='<option value='.$detail->emp_scid.'>'.$campus.' ';
                foreach($this->campus as $camdata){	
                 $campusbox.='<option  value='.$camdata->sc_id.'>'.$camdata->sc_name.''; 
                }            
                $uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                $uocbox='<option value='.$detail->emp_uocid.'>'.$uocname.' ';
                foreach($this->uoc as $ucodata){	
                    $uocbox.='<option  value='.$ucodata->id.'>'.$ucodata->name.''; 
                }            
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->emp_dept_code)->dept_code;
                $deptbox='<option value='.$detail->emp_dept_code.'>'.$deptname."(".$deptcode.")".' ';
                
                $schme=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$detail->emp_schemeid)->sd_name;
                $schmecd=$this->sismodel->get_listspfic1('scheme_department', 'sd_code', 'sd_id',$detail->emp_schemeid)->sd_code;
                $schmbox='<option value='.$detail->emp_schemeid.'>'.$schme."(".$schmecd.")".' ';
                
                /*$workbox='<option value='.$detail->emp_worktype.'>'.$detail->emp_worktype.' ';
                $workbox.='<option value='.'Teaching'.'>'.'Teaching'.' ';
                $workbox.='<option value='."Non Teaching".'>'."Non Teaching".' ';*/
                
                $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
                $desigcd=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->emp_desig_code)->desig_code;
                $desigbox='<option value='.$detail->emp_desig_code.'>'.$designame."(".$desigcd.")".' ';
                
                $empname=$detail->emp_name;
                $empbox='<option value='.$detail->emp_id.'>'.$detail->emp_name.' ';
                
               // array_push($values,$campusbox,$uocbox,$deptbox,$schmbox,$detail->emp_worktype,$desigbox,
                $values=$campusbox."^".$uocbox."^".$deptbox."^".$schmbox."^".$detail->emp_worktype."^".$desigbox.
                "^".$empbox."^".$detail->emp_post."^".$detail->emp_type_code;
               
                                
            }
            echo json_encode($values);
                       
        }            
              
    }
    /****************************************closer employee detail by pf no *********************************/
}    

