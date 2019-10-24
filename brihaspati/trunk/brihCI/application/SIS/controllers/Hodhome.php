<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Hodhome.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Hodhome extends CI_Controller
{
 
    function __construct() {
	 parent::__construct();
        $this->load->model("Login_model", "logmodel");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usrmodel");
        $this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }

    }
 
    public function index() {
        /* set role id in session*/
//	$data = [ 'id_role' => 5 ];
  //      $this->session->set_userdata($data);
        /* get logged user detail from different tables (firstname, lastname, email, campus name, org name, department name)
         * using login model and common model
         */

//	$this->uri->segment('3');
	$deptid="";
	$deptid = $this->input->get('deptid', TRUE);
	if (empty($deptid)){
		$deptid=$this->session->userdata('id_dept');
	}
	$iduser = $this->session->userdata('id_user');
	$whdata = array('userid' => $this->session->userdata('id_user'),'deptid' =>$deptid);
        $roles=$this->sismodel->get_listspficemore('user_role_type','roleid',$whdata);	
	if(!empty($roles)){
		foreach($roles as $row){
			$rid=$row->roleid;
		}
		$data = [
                        'id_role' => $rid,
			'id_dept' =>$deptid 
                       ];
                $this->session->set_userdata($data);

		$this->currentlog=$this->session->userdata('username');
        	$this->roleid=$this->session->userdata('id_role');
        	$this->currentrole=$this->commodel->get_listspfic1('role','role_name','role_id',$this->roleid);
		$loggedrole=$this->commodel->get_listspfic1('role','role_name','role_id',$this->roleid)->role_name;
        	$userid = $this->session->userdata('id_user');
            //  echo $userid." " .$this->roleid." ".$loggedrole; die();
//	echo $deptid;
//	die();
        if (($loggedrole == "HoD") && ($this->roleid == "5")){
		$today = date("Y-m-d H:i:s"); 
                $empcode='';
		//$orq="(hl_dateto = '0000-00-00 00:00:00' or hl_dateto >= $today)";
//		$orq=hl_dateto = '0000-00-00 00:00:00';
               // $whdata=array($orq, 'hl_userid' =>$userid);
               // $whdata=array($orq, 'hl_deptid' =>$deptid);
                $whdata=array('hl_dateto' => '0000-00-00 00:00:00', 'hl_deptid' =>$deptid);
                //$whdata=array('hl_dateto' => '0000-00-00 00:00:00', 'hl_userid' =>$userid);
                $uodata = $this->sismodel->get_listspficemore('hod_list','hl_empcode',$whdata);
//	print_r( $uodata);	
//		if(empty($uodata)){
//                	$whdata=array('hl_dateto >=' => $today, 'hl_userid' =>$userid);
  //      	        $uodata = $this->sismodel->get_listspficemore('hod_list','hl_empcode',$whdata);
//		}
//
                foreach($uodata as $uorow){
                        $empcode = $uorow->hl_empcode;
                }
//		echo $empcode;
//		die();
                if(!empty($empcode)){
                        $data['name'] =$this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$empcode)->emp_name;
                        $deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_code',$empcode)->emp_dept_code;
                        $data['deptname'] = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name."(".$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$deptid)->dept_code.")";
                        $desigid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_code',$empcode)->emp_desig_code;
                        $data['designame']= $this->commodel->get_listspfic1('designation','desig_name','desig_id',$desigid)->desig_name;
                        $data['mobile']=$this->sismodel->get_listspfic1('employee_master','emp_phone','emp_code',$empcode)->emp_phone;
                        $scid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_code',$empcode)->emp_scid;
                        $data['scname']=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
                        $orgid=$this->sismodel->get_listspfic1('employee_master','emp_org_code','emp_code',$empcode)->emp_org_code;
                        if(!empty($orgid)){
                        $data['orgname']=$this->commodel->get_listspfic1('org_profile','org_name','org_code',$orgid)->org_name;
                        }else{
                        $data['orgname']='';
                        }
                }else{
                        $data['name']='';
                        $data['deptname'] ='';
                        $data['designame']='';
                        $data['mobile']='';
                        $data['scname']='';
                        $data['orgname']='';
                }
//              echo $userid." " .$this->roleid." ".$loggedrole." ".$empcode." " .$orgid; die();
        }else{
                $this->name=$this->logmodel->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'));
                $this->lastn=$this->logmodel->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'));
                $this->secmail=$this->logmodel->get_listspfic1('userprofile','secmail','userid',$this->session->userdata('id_user'));
                $this->mobile=$this->logmodel->get_listspfic1('userprofile','mobile','userid',$this->session->userdata('id_user'));
                $this->address=$this->logmodel->get_listspfic1('userprofile','address','userid',$this->session->userdata('id_user'));
        }

	        $this->email=$this->logmodel->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'));
//        	$this->campusid=$this->sismodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
		if(!empty($this->sismodel->get_listspfic1('employee_master','emp_scid','emp_userid',$this->session->userdata('id_user')))){
			$this->campusid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_userid',$this->session->userdata('id_user'))->emp_scid;
		}else{
			$this->campusid='';
		}
	        $this->campusname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid);
        	$this->orgcode=$this->commodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid);
	        $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_code',$this->orgcode->org_code);
        	$this->load->view('hodhome',$data);
    	}
	else{
		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            	redirect('welcome');
	}	
   }
 
}

