<?php

/* 
 * @name Payrollreport.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
 * @author Manorama Pal (palseema30@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Payrollreport extends CI_Controller
{
    function __construct() {
        parent::__construct();
        $this->load->model('common_model','commodel');
        $this->load->model('login_model','lgnmodel');
        $this->load->model('SIS_model',"sismodel");
        $this->load->helper('download');
        
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
        $selemp='sal_empid';
        $whdemp = array('sal_deptid' =>$deptid,'sal_month' =>$month,'sal_year'=>$year);
        $data['emprecord']=$this->sismodel->get_orderlistspficemore('salary',$selemp,$whdemp,'');
        
        $selemplt='sallt_empid';
        $whdemplt = array('sallt_deptid' =>$deptid,'sallt_month' =>$month,'sallt_year'=>$year);
        $data['emprecordlt']=$this->sismodel->get_orderlistspficemore('salary_lt',$selemplt,$whdemplt,'');
        
        $data['totalemp']=array_merge($data['emprecord'],$data['emprecordlt']);
        
	//get head on the basis of employee id
	$selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
       // $whorder = " sh_name asc";
        $whdata = array('sh_type' =>'I');
        $data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

        $whdata = array('sh_type' =>'D');
        $data['ded'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

        $whdata = array('sh_type' =>'L');
        $data['loans'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
        $data['deduction']=array_merge( $data['ded'],  $data['loans']);
        

        $this->load->view('payrollreport/Salaryreport',$data);
    }
    public function salaryslipreport(){
        
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
     //   $selemp='sal_empid';
       // $whdemp = array('sal_deptid' =>$deptid,'sal_month' =>$month,'sal_year'=>$year);
        //$data['emprecord']=$this->sismodel->get_orderlistspficemore('salary',$selemp,$whdemp,'');
        
        
        $selemp='emp_id';
        $whdemp = array('sal_deptid' =>$deptid,'sal_month' =>$month,'sal_year'=>$year);
        $joincond2 = 'salary.sal_empid = employee_master.emp_id';
        $data['emprecord']=$this->sismodel->get_jointbrecord('employee_master',$selemp, 'salary',$joincond2, 'left',$whdemp);;
        
             
        //$data['totalemp']=json_decode(json_encode($data['emprecord']),true);
        // print_r( $data['emprecord']);
        
        //$selemplt='sallt_empid';
        $whdemplt = array('sallt_deptid' =>$deptid,'sallt_month' =>$month,'sallt_year'=>$year);
       // $data['emprecordlt']=$this->sismodel->get_orderlistspficemore('salary_lt',$selemplt,$whdemplt,'');
        $joincond = 'salary_lt.sallt_empid = employee_master.emp_id';
        $data['emprecordlt']=$this->sismodel->get_jointbrecord('employee_master',$selemp, 'salary_lt',$joincond, 'left',$whdemplt);;
        
        $data['totalemp']=array_merge($data['emprecord'],$data['emprecordlt']);
        
       // print_r($data['totalemp']); //die;
        
        //get head on the basis of employee id
                
	$selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
       // $whorder = " sh_name asc";
        $whdata = array('sh_type' =>'I');
        $data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

        $whdata = array('sh_type' =>'D');
        $data['ded'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

        $whdata = array('sh_type' =>'L');
        $data['loans'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
        $data['deduction']=array_merge( $data['ded'],  $data['loans']);
        
     //   $cmonth= date('M');
       // $cyear= date("Y");
      
        
       // $desired_dir = 'uploads/SIS/Payslip/'.$cyear.'/'.$cmonth;
        
        // Create directory if it does not exist
      /*  if(is_dir($desired_dir)==false){
            mkdir($desired_dir, 0777,true);
        }*/
       
       // $tmp=$this->load->view('payrollreport/salaryslipreport',$data,TRUE);
       	//add pdf code to store and view pdf file
	//$pth=$desired_dir.'/salaryslipreport.pdf';
      //  echo $cmonth.$cyear.$empid.$desired_dir.$pth;      
        //die();
//	$this->sismodel->genpdf($tmp,$pth);
        //}
       
        $this->load->library('pdf');
        $this->pdf->set_paper("A4", "portrait");
      //  $this->pdf->set_option('enable_html5_parser', true);
        $this->pdf->load_view('payrollreport/salaryslipreport',$data);
        $this->pdf->render();
        $this->pdf->stream("salaryslipreport.pdf");
        
    	$this->load->view('payrollreport/Salaryreport',$data);
    }

    public function deptAbstract(){
	$deptnme='';
	$pdsubmit = false;
        $deptid=$this->session->flashdata('fldeptid');
        $month = $this->input->post('month', TRUE);
        $year = $this->input->post('year', TRUE);

        if(empty($deptid)){
                $deptid=$this->input->post('dept', TRUE);
        }
	$type=$this->uri->segment(3,0);
	if((strcasecmp($type,"pdf" )) == 0){
		$deptid=$this->uri->segment(4,0);
		if($deptid < 1){
			$message="Please select the dept first and serach Then use pdf button";
			$this->session->set_flashdata('err_message',$message  , 'error');
    			redirect("payrollreport/deptAbstract");
			return;
		}
		$month = $this->uri->segment(6,0);
		$year = $this->uri->segment(5,0);
		$pdsubmit = true; 
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
	$data['empcount']=0;
        $data['selmonth']=$month;
        $data['selyear']=$year;
	$data['deptid']=0;

	if((isset($_POST['deptabs']))||($pdsubmit)){
	
		$data['deptsel']=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
		$data['deptid']=$deptid;

		// get all employee id on the basis of dept
		$whdata=array('emp_dept_code'=>$deptid);
		$emplist= $this->sismodel->get_orderlistspficemore('employee_master','emp_id',$whdata,'emp_id asc');
		$data['empcount']=count($emplist);
		//get the total number of heads
		$tothdres= $this->sismodel->get_maxvalue('salary_head','sh_id','');
		$tothdno= $tothdres[0]->sh_id;
		for($i=1;$i<=$tothdno;$i++){
			${"d".$i}=0;
		}
		//get head on the basis of employee id
		foreach($emplist as $row){
			$lempid=$row->emp_id;
			$whdata1=array('sald_empid'=>$lempid,'sald_month'=>$month, 'sald_year'=>$year);
			$lsald=$this->sismodel->get_orderlistspficemore('salary_data','sald_sheadid,sald_shamount',$whdata1,'sald_sheadid asc');
			if(!empty($lsald)){
				foreach($lsald as $row1){
					$ir=$row1->sald_sheadid;
					${"d".$ir}=${"d".$ir} + $row1->sald_shamount;
					$data['d'.$ir]=${"d".$ir};
				}
			}else{
				$whdata2=array('saldlt_empid'=>$lempid,'saldlt_month'=>$month, 'saldlt_year'=>$year);
				$lsald2=$this->sismodel->get_orderlistspficemore('salarydata_lt','saldlt_sheadid,saldlt_shamount',$whdata2,'saldlt_sheadid asc');
				if(!empty($lsald2)){
					foreach($lsald2 as $row2){
						$ir=$row2->saldlt_sheadid;
						${"d".$ir}=${"d".$ir} + $row2->saldlt_shamount;
						$data['d'.$ir]=${"d".$ir};
					}
				}
			}
		}
		$selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
       // $whorder = " sh_name asc";
	        $whdata = array('sh_type' =>'I');
        	$data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

	        $whdata = array('sh_type' =>'D');
        	$data['ded'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

	        $whdata = array('sh_type' =>'L');
        	$data['loans'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
	        $data['deduction']=array_merge( $data['ded'],  $data['loans']);
	}
	if((strcasecmp($type,"pdf" )) == 0){
		$this->load->library('pdf');
	        $this->pdf->set_paper("A4", "portrait");
        	$this->pdf->set_option('enable_html5_parser', true);
        	$this->pdf->load_view('payrollreport/DeptAbstractReportPdf',$data);
        	$this->pdf->render();
		$canvas = $this->pdf->get_canvas();
		$font = Font_Metrics::get_font("helvetica", "bold");
		$canvas->page_text(72, 18, "Page: {PAGE_NUM} of {PAGE_COUNT}               Department Abstract Report ", $font, 10, array(0,0,0));
        	$this->pdf->stream("deptabstract.pdf");	
	}else{
    		$this->load->view('payrollreport/DeptAbstractReport',$data);
	}
    }


    public function ddoAbstract(){
	$deptnme='';
	$pdsubmit = false;
        $deptid=$this->session->flashdata('fldeptid');
        $month = $this->input->post('month', TRUE);
        $year = $this->input->post('year', TRUE);

        if(empty($ddocode)){
                $ddocode=$this->input->post('ddo', TRUE);
        }
	$type=$this->uri->segment(3,0);
	if((strcasecmp($type,"pdf" )) == 0){
		$ddocode=$this->uri->segment(4,0);
		$pos=strpos($ddocode,'D');
		if($pos !== 0){
			$message="Please select the ddo first and serach Then use pdf button";
			$this->session->set_flashdata('err_message',$message  , 'error');
    			redirect("payrollreport/ddoAbstract");
			return;
		}
		$month = $this->uri->segment(6,0);
		$year = $this->uri->segment(5,0);
		$pdsubmit = true; 
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
                $datawh['ddo_deptid']=$deptid;
        }
        $data['combdata'] = $this->sismodel->get_orderdistinctrecord('ddo','ddo_code,ddo_name',$datawh,'ddo_name asc');
	$data['empcount']=0;
        $data['selmonth']=$month;
        $data['selyear']=$year;
	$data['ddocode']='';

	if((isset($_POST['ddoabs']))||($pdsubmit)){

		$data['deptid']=$deptid;
		$data['ddocode']=$ddocode;
		$data['ddosel']=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_code',$ddocode)->ddo_name;
		//get the total number of heads
		$tothdres= $this->sismodel->get_maxvalue('salary_head','sh_id','');
		$tothdno= $tothdres[0]->sh_id;
		for($i=1;$i<=$tothdno;$i++){
			${"d".$i}=0;
		}
		$totemp=0;
		//get all dept on the basis of ddo code
		$dowhdata=array('ddo_code'=>$ddocode);
		$deptlist=$this->sismodel->get_orderdistinctrecord('ddo','ddo_deptid',$dowhdata,'ddo_deptid asc');
		foreach($deptlist as $deptrow){
			$deptid=$deptrow->ddo_deptid;
			// get all employee id on the basis of dept
			$whdata=array('emp_dept_code'=>$deptid);
			$emplist= $this->sismodel->get_orderlistspficemore('employee_master','emp_id',$whdata,'emp_id asc');
			$totemp=$totemp + count($emplist);
			//get head on the basis of employee id
			foreach($emplist as $row){
				$lempid=$row->emp_id;
				$whdata1=array('sald_empid'=>$lempid,'sald_month'=>$month, 'sald_year'=>$year);
				$lsald=$this->sismodel->get_orderlistspficemore('salary_data','sald_sheadid,sald_shamount',$whdata1,'sald_sheadid asc');
				if(!empty($lsald)){
				foreach($lsald as $row1){
					$ir=$row1->sald_sheadid;
					${"d".$ir}=${"d".$ir} + $row1->sald_shamount;
					$data['d'.$ir]=${"d".$ir};
				}
				}else{
					$whdata2=array('saldlt_empid'=>$lempid,'saldlt_month'=>$month, 'saldlt_year'=>$year);
					$lsald2=$this->sismodel->get_orderlistspficemore('salarydata_lt','saldlt_sheadid,saldlt_shamount',$whdata2,'saldlt_sheadid asc');
					if(!empty($lsald2)){
						foreach($lsald2 as $row2){
							$ir=$row2->saldlt_sheadid;
							${"d".$ir}=${"d".$ir} + $row2->saldlt_shamount;
							$data['d'.$ir]=${"d".$ir};
						}	
					}
				}
			}
		}
		$data['empcount']=$totemp;
		$selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
       		// $whorder = " sh_name asc";
	        $whdata = array('sh_type' =>'I');
        	$data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

	        $whdata = array('sh_type' =>'D');
        	$data['ded'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');

	        $whdata = array('sh_type' =>'L');
        	$data['loans'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
	        $data['deduction']=array_merge( $data['ded'],  $data['loans']);
	}
	if((strcasecmp($type,"pdf" )) == 0){
                $this->load->library('pdf');
                $this->pdf->set_paper("A4", "portrait");
                $this->pdf->set_option('enable_html5_parser', true);
                $this->pdf->load_view('payrollreport/DdoAbstractReportPdf',$data);
                $this->pdf->render();
		$canvas = $this->pdf->get_canvas();
		$font = Font_Metrics::get_font("helvetica", "bold");
		$canvas->page_text(72, 18, "Page: {PAGE_NUM} of {PAGE_COUNT}               DDO Abstract Report ", $font, 10, array(0,0,0));
                $this->pdf->stream("ddoabstract.pdf");
        }else{
    		$this->load->view('payrollreport/DdoAbstractReport',$data);
        }

    }

    public function bankStmtReport(){
	$deptnme='';
	$pdsubmit = false;
        $deptid=$this->session->flashdata('fldeptid');
        $month = $this->input->post('month', TRUE);
        $year = $this->input->post('year', TRUE);

        if(empty($ddocode)){
                $ddocode=$this->input->post('ddo', TRUE);
        }
	$type=$this->uri->segment(3,0);
	if((strcasecmp($type,"pdf" )) == 0){
		$ddocode=$this->uri->segment(4,0);
		$pos=strpos($ddocode,'D');
		if($pos !== 0){
			$message="Please select the ddo first and serach Then use pdf button";
			$this->session->set_flashdata('err_message',$message  , 'error');
    			redirect("payrollreport/bankStmtReport");
			return;
		}
		$month = $this->uri->segment(6,0);
		$year = $this->uri->segment(5,0);
		$pdsubmit = true; 
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
                $datawh['ddo_deptid']=$deptid;
        }
        $data['combdata'] = $this->sismodel->get_orderdistinctrecord('ddo','ddo_code,ddo_name',$datawh,'ddo_name asc');
	$data['empcount']=0;
        $data['selmonth']=$month;
        $data['selyear']=$year;
	 $data['ddocode']='';

	if((isset($_POST['ddoabs']))||($pdsubmit)){

	$data['deptid']=$deptid;
	$data['ddocode']=$ddocode;
	$data['ddosel']=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_code',$ddocode)->ddo_name;
	$totemp=0;
	$totamt=0;
	//get all dept on the basis of ddo code
	$dowhdata=array('ddo_code'=>$ddocode);
	$deptlist=$this->sismodel->get_orderdistinctrecord('ddo','ddo_deptid',$dowhdata,'ddo_deptid asc');
	$i=0;
	foreach($deptlist as $deptrow){
		$deptid=$deptrow->ddo_deptid;
		// get all employee id on the basis of dept
		$whdata=array('emp_dept_code'=>$deptid);
		$selectfield='emp_id,emp_code,emp_name,emp_bankname,emp_bank_accno';
		$emplist= $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,'emp_desig_code asc,emp_code asc');
		$totemp=$totemp + count($emplist);
		//get head on the basis of employee id
		foreach($emplist as $row){
			$ir=0;
			$lempid=$row->emp_id;
			$whdata1=array('sal_empid'=>$lempid,'sal_month'=>$month, 'sal_year'=>$year);
			$lsald=$this->sismodel->get_orderlistspficemore('salary','sal_netsalary,sal_id',$whdata1,'');
			if(!empty($lsald)){
				foreach($lsald as $row1){
					$ir=$row1->sal_netsalary;
				}
			}else{
				$whdata2=array('sallt_empid'=>$lempid,'sallt_month'=>$month, 'sallt_year'=>$year);
				$lsald2=$this->sismodel->get_orderlistspficemore('salary_lt','sallt_id,sallt_netsalary',$whdata2,'');
				if(!empty($lsald2)){
					foreach($lsald2 as $row2){
						$ir=$row2->sallt_netsalary;
					}
				}
			}
			if($ir>0){
			$lsdata=array('id'=>$lempid,'code'=>$row->emp_code,'name'=>$row->emp_name,'bankname'=>$row->emp_bankname,'bankacc'=>$row->emp_bank_accno,'amount'=>$ir,'dept'=>$deptid);
			$fdata[$i]=$lsdata;
			$i++;
			}
		}
	}
	$data['listd']=$fdata;
	//$data['empcount']=$totemp;
	$data['empcount']=$i;
	}
	if((strcasecmp($type,"pdf" )) == 0){
                $this->load->library('pdf');
                $this->pdf->set_paper("A4", "portrait");
                $this->pdf->set_option('enable_html5_parser', true);
                $this->pdf->load_view('payrollreport/BankstmtReportPdf',$data);
                $this->pdf->render();
		$canvas = $this->pdf->get_canvas();
		$font = Font_Metrics::get_font("helvetica", "bold");
		$canvas->page_text(72, 18, "Page: {PAGE_NUM} of {PAGE_COUNT}               DDO Bank Statement Report ", $font, 10, array(0,0,0));
                $this->pdf->stream("bankstatement.pdf");
        }else{
    		$this->load->view('payrollreport/BankstmtReport',$data);
        }

    }

    public function allSchedule(){
	$deptnme='';
	$pdsubmit = false;
        $deptid=$this->session->flashdata('fldeptid');
        $month = $this->input->post('month', TRUE);
        $year = $this->input->post('year', TRUE);

        if(empty($ddocode)){
                $ddocode=$this->input->post('ddo', TRUE);
        }
	$type=$this->uri->segment(3,0);
	if((strcasecmp($type,"pdf" )) == 0){
		$ddocode=$this->uri->segment(4,0);
		$pos=strpos($ddocode,'D');
		if($pos !== 0){
			$message="Please select the ddo first and serach Then use pdf button";
			$this->session->set_flashdata('err_message',$message  , 'error');
    			redirect("payrollreport/allSchedule");
			return;
		}
		$month = $this->uri->segment(6,0);
		$year = $this->uri->segment(5,0);
		$pdsubmit = true; 
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
                $datawh['ddo_deptid']=$deptid;
        }
        $data['combdata'] = $this->sismodel->get_orderdistinctrecord('ddo','ddo_code,ddo_name',$datawh,'ddo_name asc');
	$data['empcount']=0;
        $data['selmonth']=$month;
        $data['selyear']=$year;
	$data['ddocode']='';

	if((isset($_POST['ddosch']))||($pdsubmit)){

	$data['ddocode']=$ddocode;
	$data['deptid']=$deptid;
	$data['ddosel']=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_code',$ddocode)->ddo_name;
	$fdata='';
	$totemp=0;
	$totamt=0;
	//get all dept on the basis of ddo code
	$dowhdata=array('ddo_code'=>$ddocode);
	$deptlist=$this->sismodel->get_orderdistinctrecord('ddo','ddo_deptid',$dowhdata,'ddo_deptid asc');
	// get the list of deduction head
	$selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
	$dlhead=$this->sismodel->get_orderlistspficemoreorwh('salary_head','sh_id,sh_name','','sh_type',array('D','L'),'');
	$i=0;
	foreach($dlhead as $hdrow){
		$shid=$hdrow->sh_id;
		$shname=$hdrow->sh_name;
	foreach($deptlist as $deptrow){
		$deptid=$deptrow->ddo_deptid;
		// get all employee id on the basis of dept
		$whdata=array('emp_dept_code'=>$deptid);
		$selectfield='emp_id,emp_code,emp_name';
		$emplist= $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,'emp_desig_code asc, emp_code asc');
		$totemp=$totemp + count($emplist);
		//get head value on the basis of employee id
		foreach($emplist as $row){
			$ir=0;
			$lempid=$row->emp_id;

			$whdata1=array('sald_empid'=>$lempid,'sald_month'=>$month, 'sald_year'=>$year,'sald_sheadid'=>$shid,'sald_shamount >'=> 0);
			$lsald=$this->sismodel->get_orderlistspficemore('salary_data','sald_shamount',$whdata1,'');
			if(!empty($lsald)){
				foreach($lsald as $row1){
					$ir=$row1->sald_shamount;
				}
			}else{
				$whdata2=array('saldlt_empid'=>$lempid,'saldlt_month'=>$month, 'saldlt_year'=>$year,'saldlt_sheadid'=>$shid,'saldlt_shamount >'=> 0);
				$lsald2=$this->sismodel->get_orderlistspficemore('salarydata_lt','saldlt_shamount',$whdata2,'');
				if(!empty($lsald2)){
					foreach($lsald2 as $row2){
						$ir=$ir+$row2->saldlt_shamount;
					}
				}
			}
			if($ir >0){
			$lsdata=array('shid'=>$shid,'shnme'=>$shname,'id'=>$lempid,'code'=>$row->emp_code,'name'=>$row->emp_name,'amount'=>$ir,'dept'=>$deptid);
			$fdata[$i]=$lsdata;
			$i++;
			}
		}//close of emplist
	}//close of dept list
	}//close of head list
	$data['listd']=$fdata;
	$data['empcount']=$totemp;
	}

	if((strcasecmp($type,"pdf" )) == 0){
                $this->load->library('pdf');
                $this->pdf->set_paper("A4", "portrait");
                $this->pdf->set_option('enable_html5_parser', true);
                $this->pdf->load_view('payrollreport/ScheduleReportPdf',$data);
                $this->pdf->render();
		$canvas = $this->pdf->get_canvas();
		$font = Font_Metrics::get_font("helvetica", "bold");
		$canvas->page_text(72, 18, "Page: {PAGE_NUM} of {PAGE_COUNT}               DDO Schedule Report ", $font, 10, array(0,0,0));
                $this->pdf->stream("AllSchedule.pdf");
        }else{
    		$this->load->view('payrollreport/ScheduleReport',$data);
        }
    }


}
?>
