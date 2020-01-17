<?php

/* 
 * @name Payrollreport.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Payrollreport extends CI_Controller
{
    function __construct() {
        parent::__construct();
        $this->load->model('common_model','commodel');
        $this->load->model('login_model','lgnmodel');
        $this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
                redirect('welcome');
        }
    }


    public function index () {
    }

    public function bulkSalaryslip(){
	$deptnme='';
        $deptid=$this->session->flashdata('fldeptid');
//              echo $deptid; //die();
        $month = $this->input->post('month', TRUE);
        $year = $this->input->post('year', TRUE);

        if(empty($deptid)){
                $deptid=$this->input->post('dept', TRUE);
        }
        $cmonth= date('M');
        $cyear= date("Y");
        if($month==''){
            $month=$cmonth;
        }
        if($year==''){
            $year=$cyear;
        }
        $datawh='';
	
	$ssiondeptid=$this->session->userdata('id_dept');
        $sessionroleid=$this->session->userdata('id_role');
        $data['sroleid']= $sessionroleid;
        if(!empty($ssiondeptid)){
                $deptid = $ssiondeptid;
                $datawh['dept_id']=$deptid;
        }
        $data['combdata'] = $this->commodel->get_orderlistspficemore('Department','dept_id,dept_name,dept_code',$datawh,'dept_name asc');
        $data['selmonth']=$month;
        $data['selyear']=$year;

	// get all employee id on the basis of dept

	//get head on the basis of employee id
	$selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
       // $whorder = " sh_name asc";
        $whdata = array('sh_type' =>'I');
        $spec_data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

        $whdata = array('sh_type' =>'D');
        $spec_data['ded'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

        $whdata = array('sh_type' =>'L');
        $spec_data['loans'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
        $spec_data['deduction']=array_merge( $spec_data['ded'],  $spec_data['loans']);


    $this->load->view('payrollreport/Salaryreport',$data);
    }
}
?>
