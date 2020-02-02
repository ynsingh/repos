<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Pjslist.php
 * @author Nagendra Kumar Singh nksinghiitk@gmail.com
 */

class Pjslist extends CI_Controller
{
 
    	function __construct() {
        	parent::__construct();

        	$this->load->model('Common_model',"commodel");
        	$this->load->model('Login_model',"lgnmodel"); 
        	$this->load->model('SIS_model',"sismodel");
        	$this->load->model('PICO_model',"picomodel");
        	$this->load->model('Dependrop_model',"depmodel");

        	if(empty($this->session->userdata('id_user'))) {
            		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('welcome');
        	}
    	}
 
    	public function index(){
    	}

	public function getuoclist(){
        $scid = $this->input->post('campusname');
        $auco_data = $this->sismodel->get_listrow('map_sc_uo','scuo_scid',$scid);
        $aucolist = $auco_data->result();
        $uco_select_box ='';
        $uco_select_box.='<option value="">-------University Officer Control--------';
        foreach($aucolist as $aucoid){
            $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$aucoid->scuo_uoid)->name;
            $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$aucoid->scuo_uoid)->code;
            /*$auouserid=$this->lgnmodel->get_listspfic1('authority_map', 'user_id', 'authority_id',$aucoid->cudsd_auoid)->user_id;
            $auofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$auouserid)->firstname;
            $auolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$auouserid)->lastname;
            $auoflname=$auofname." ".$auolname;
            $uco_select_box.='<option value='.$aucoid->cudsd_auoid.'>'.$auoflname.' ';*/
         json_encode("in controller====".$aucoid->scuo_uoid);
            $uco_select_box.='<option value='.$aucoid->scuo_uoid.'>'.$auoname." (".$auocode.") ".' ';
        }
        echo json_encode($uco_select_box);
    }

	public function getnewdeptlist(){
        $combid = $this->input->post('campuoc');
        //echo json_encode("combination===".$combid);
        $parts = explode(',',$combid);
       // echo json_encode("this is test===".$parts[0].",".$parts[1]);
        $sccode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id',$parts[0])->sc_code;
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1]);
        $datawh=array('dept_uoid' => $parts[1],'dept_sccode' => $sccode);
        //$comb_data = $this->sismodel->get_listspficemore('cudsdmap','cudsd_deptid',$datawh);
        $comb_data = $this->commodel->get_listspficemore('Department','dept_id,dept_name,dept_code',$datawh);
        //$comblist = $comb_data->result();
        $dept_select_box ='';
        $dept_select_box.='<option value="">-------Select Department--------';
        foreach($comb_data as $combdataid){
           // $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$combdataid->cudsd_deptid)->dept_name;
            //$dept_select_box.='<option value='.$combdataid->cudsd_deptid.'>'.$deptname.' ';
            $dept_select_box.='<option value='.$combdataid->dept_id.'>'.$combdataid->dept_name.'('.$combdataid->dept_code.')'.' ';

        }
        echo json_encode($dept_select_box);

    }

	 /* This function has been created for get list of schemes on the basis of  selected campus, uco and department */
    public function getnewdeptschemelist(){
        //$campuocdept = $this->input->post('combdept');
        //$parts = explode(',',$campuocdept);
        $campdept = $this->input->post('combdept');
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1],'cudsd_deptid' => $parts[2]);
        $datawh=array('sd_deptid' => $campdept);
        $comb_data = $this->sismodel->get_listspficemore('scheme_department','sd_id,sd_name,sd_code',$datawh);
        $schm_select_box ='';
        $schm_select_box.='<option value="">-------Select Scheme Name--------';
        foreach($comb_data as $combdataid){
            //$schmname=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$aucoid->cudsd_schid)->sd_name;
            $schm_select_box.='<option value='.$combdataid->sd_id.'>'.$combdataid->sd_name.'('.$combdataid->sd_code.')'.' ';

        }
        echo json_encode($schm_select_box);
       // $this->depmodel->get_sschemelist($deptid);
    }


	public function getcoverdetail(){
	        
        	$coverno= $this->input->post('noc');
        	
        	$whdata =array('ct_id' =>$coverno);
        	$covrdata=$this->picomodel->get_orderlistspficemore('cover_type','*',$whdata,''); 
	      $values=array();
        	$covern='';
        	if(count($covrdata)>0){
			 foreach($covrdata as $detail){
		                $coverno=$detail->ct_coverno;
		                $covern=$detail->ct_cover1;
		                $covern2=$detail->ct_cover2;
		                if(!empty($covern2)){
		                	$covern="(a)".$covern." "." (b)".$covern2." ";
		                }
		               $covern3=$detail->ct_cover3;
		               if(!empty($covern3)){
		                	$covern=$covern." (c)".$covern3." ";
		                }
							$covern4=$detail->ct_cover4;
							 if(!empty($covern4)){
		                	$covern=$covern." (d)".$covern4;
		                }
							
                		
				array_push($values, $coverno,$covern);
			}
		}
		else{
			$mess="Please select the valid  Number";
			array_push($values,$mess);
		}
		//implode("+",$values);
		echo json_encode( $values);
	}
    /*********************************************************************************/
}    
