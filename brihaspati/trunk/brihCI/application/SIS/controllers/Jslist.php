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
                $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
                $desig_select_box ='';
                $desig_select_box.='<option value="">--Select Designation--';
                foreach($grp_data as $grprecord){
                        $desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
                }
                echo json_encode($desig_select_box);
        }

    	/* This function has been created for get list of Designation on the basis of  selected Group */
    	public function getgdesiglist(){
        	$groups = $this->input->post('group');
        	$datawh=array('desig_group' => $groups);
	        $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
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
        	$grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
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


}    

