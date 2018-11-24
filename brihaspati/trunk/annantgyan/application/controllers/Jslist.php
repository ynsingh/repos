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
//
//		if(isset($this->session->userdata['firstName'])){
//			$this->session->set_flashdata('flash_data', 'You don\'t have access!');
//			$this->load->view('admin/admin_login');
//		}

        }

	public function index(){

	}

	 /* This function has been created for get list of student  on the basis of  selected course */
        public function getcstulist(){
                $crsid = $this->input->post('scrsid');
//                $datawh=array('uct_courseid' => $crsid);
//		$whorder = ("desig_name asc");
		$joincond = 'user_course_type.uct_userid=sign_up.su_id';
                $whdata = array('user_course_type.uct_courseid' => $crsid);;

                $grp_data = $this->commodel->get_jointbrecord('sign_up','uct_userid,su_name','user_course_type',$joincond,$whdata);
                $desig_select_box ='';
                $desig_select_box.='<option value="">--Select Student Name--';
                foreach($grp_data as $grprecord){
                        $desig_select_box.='<option value='.$grprecord->uct_userid.'>'.$grprecord->su_name.' ';
                }
                echo json_encode($desig_select_box);
        }
}
