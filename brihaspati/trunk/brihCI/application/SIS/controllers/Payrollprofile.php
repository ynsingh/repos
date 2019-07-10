
<?php

/* 
 * @name Payrollprofile.php
 * @author Manorama Pal(palseema30@gmail.com) Salary head
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com) Salary leave, Salary transfer
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Payrollprofile extends CI_Controller
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
    
    /****************************************************************************/
    public function payprofile(){
               
        $this->hglist= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        $this->ccalist= $this->sismodel->get_listspfic2('cca_grade_city','cgc_id','cgc_gradename');
        $this->society= $this->sismodel->get_listspfic2('society_master_list','soc_id','soc_sname');
        if(isset($_POST['pprofile'])) {
           
            $this->form_validation->set_rules('accno','Account number','trim|xss_clean');
            $this->form_validation->set_rules('bname','Bank name','trim|xss_clean');
            $this->form_validation->set_rules('ifsccode','IFSC code','trim|xss_clean');
            $this->form_validation->set_rules('bbranch','Bank branch','trim|xss_clean');
            
            $this->form_validation->set_rules('pcontri','Pension Contribution','trim|xss_clean');
            $this->form_validation->set_rules('upfno','UPF No','trim|xss_clean');
            $this->form_validation->set_rules('qtrno','QTR No','trim|xss_clean');
            $this->form_validation->set_rules('qtrtype','Qtrtype','trim|xss_clean');
            $this->form_validation->set_rules('uniemp','University employee','trim|xss_clean');
            $this->form_validation->set_rules('washallw','Washing allowance','trim|xss_clean');
            $this->form_validation->set_rules('dedupf','DeductUPF','trim|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA grade','trim|xss_clean');
            $this->form_validation->set_rules('ccagrade','CCA grade','trim|xss_clean');
            $this->form_validation->set_rules('incsumm','includesummary','trim|xss_clean');
            
            $this->form_validation->set_rules('lic1no','lic1no','trim|xss_clean');
            $this->form_validation->set_rules('lic1amount','lic1amount','trim|xss_clean');
            $this->form_validation->set_rules('prdno1','prdno1','trim|xss_clean');
            $this->form_validation->set_rules('society','society','trim|xss_clean');
            
            $this->form_validation->set_rules('lic2no','lic2no','trim|xss_clean');
            $this->form_validation->set_rules('lic2amount','lic2amount','trim|xss_clean');
            $this->form_validation->set_rules('prdno2','prdno2','trim|xss_clean');
            $this->form_validation->set_rules('societymember','societymember','trim|xss_clean');
            
            $this->form_validation->set_rules('lic3no','lic3no','trim|xss_clean');
            $this->form_validation->set_rules('lic3amount','lic3amount','trim|xss_clean');
            $this->form_validation->set_rules('prdno3','prdno3','trim|xss_clean');
                        
            $this->form_validation->set_rules('lic4no','lic4no','trim|xss_clean');
            $this->form_validation->set_rules('lic4amount','lic4amount','trim|xss_clean');
            $this->form_validation->set_rules('plino1','plino1','trim|xss_clean');
                        
            $this->form_validation->set_rules('lic5no','lic5no','trim|xss_clean');
            $this->form_validation->set_rules('lic5amount','lic5amount','trim|xss_clean');
            $this->form_validation->set_rules('plino2','plino2','trim|xss_clean');

            $this->form_validation->set_rules('pcomm','Pay commission','trim|xss_clean');
            $this->form_validation->set_rules('pscale','Pay Band','trim|xss_clean');
            $this->form_validation->set_rules('pscale1','Academic Level of Pay','trim|xss_clean');
            $this->form_validation->set_rules('pscale2','Scale of Pay','trim|xss_clean');
            $this->form_validation->set_rules('pscale3','Academic Grade Pay','trim|xss_clean');

            $this->form_validation->set_rules('micrcode','MICR Code','trim|xss_clean');
            $this->form_validation->set_rules('acctype','Account Type','trim|xss_clean');
            $this->form_validation->set_rules('bbadd','Branch Address','trim|xss_clean');
            $this->form_validation->set_rules('bbphone','Branch Phone No','trim|xss_clean');
            $this->form_validation->set_rules('bbemail','Branch Email','trim|xss_clean');

            $this->form_validation->set_rules('rfqemp','Eligible for Rent Free Quarters','trim|xss_clean');
            $this->form_validation->set_rules('exhra','Rent Free HRA Grade','trim|xss_clean');
            $this->form_validation->set_rules('qoemp','Quarters Occupied','trim|xss_clean');
            $this->form_validation->set_rules('rentgrade','Rent Grade','trim|xss_clean');

            $this->form_validation->set_rules('panno','PAN No','trim|xss_clean');
            $this->form_validation->set_rules('nhisno','NHIS No','trim|xss_clean');
            $this->form_validation->set_rules('nhisamount','NHIS Amount','trim|xss_clean');
            $this->form_validation->set_rules('spfcgsno','SPF CGS No','trim|xss_clean');
            $this->form_validation->set_rules('spfcgsamount','SPF CGS Amount','trim|xss_clean');
            $this->form_validation->set_rules('spfcgs2no','SPF CGS 2000 No','trim|xss_clean');
            $this->form_validation->set_rules('spfcgs2amount','SPF CGS 2000 Amount','trim|xss_clean');
            $this->form_validation->set_rules('prd1amount','PRD1 Amount','trim|xss_clean');
            $this->form_validation->set_rules('prd2amount','PRD2 Amount','trim|xss_clean');
            $this->form_validation->set_rules('prd3amount','PRD3 Amount','trim|xss_clean');
            $this->form_validation->set_rules('pli1amount','PLI 1 Amount','trim|xss_clean');
            $this->form_validation->set_rules('pli2amount','PLI 2 Amount','trim|xss_clean');
            $this->form_validation->set_rules('socamount','Society Amount','trim|xss_clean');
            $this->form_validation->set_rules('fsfno','FSF No','trim|xss_clean');
            $this->form_validation->set_rules('fsfamount','FSF Amount','trim|xss_clean');
            //$this->form_validation->set_rules('','','trim|xss_clean');
            
            if($this->form_validation->run() == FALSE){
                $this->load->view('payrollprofile/payprofile');
                return;
            }    
            else{
                
		$bankname = $this->input->post('bname', '');
                $ifsccode = $this->input->post('ifsccode', '');
                $bbranch = $this->input->post('bbranch', '');

		$panno =$this->input->post('panno', '');
		$nhisno =$this->input->post('nhisno', '');
		$paycom=$this->input->post('pcomm', '');
		$payscle=$this->input->post('pscale', '');
		$payscle1=$this->input->post('pscale1', '');
		$payscle2=$this->input->post('pscale2', '');
		$payscle3=$this->input->post('pscale3', '');
	// get the sgid  on the basis of paycom payscale and payscale1 from salarygrademaster
		$salgrdid='';	
		$sel='sgm_id';
		$whdata=array('sgm_pc'=>$paycom,'sgm_name'=>$payscle,'sgm_level'=>$payscle1);
		$salgrres=$this->sismodel->get_orderlistspficemore('salary_grade_master',$sel,$whdata,'');
		foreach($salgrres as $rw){
			$salgrdid=$rw->sgm_id;
		}
                $bbmicr = $this->input->post('micrcode', '');
                $acctype = $this->input->post('acctype', '');
                $bbadd = $this->input->post('bbadd', '');
                $bbphone = $this->input->post('bbphone', '');
                $bbemail = $this->input->post('bbemail', '');
		
		$rfqemp = $this->input->post('rfqemp', '');
		$exhra=$this->input->post('exhra', '');
		$qoemp=$this->input->post('qoemp', '');
		$rentgrade=$this->input->post('rentgrade', '');

		$nhisamount =$this->input->post('nhisamount', '');
		$spfcgsno =$this->input->post('spfcgsno', '');
		$spfcgsamount =$this->input->post('spfcgsamount', '');
		$spfcgs2no =$this->input->post('spfcgs2no', '');
		$spfcgs2amount =$this->input->post('spfcgs2amount', '');
		$prd1amount =$this->input->post('prd1amount', '');
		$prd2amount =$this->input->post('prd2amount', '');
		$prd3amount =$this->input->post('prd3amount', '');
		$pli1amount =$this->input->post('pli1amount', '');
		$pli2amount =$this->input->post('pli2amount', '');
		$socamount =$this->input->post('socamount', '');
		$fsfno =$this->input->post('fsfno', '');
		$fsfamount =$this->input->post('fsfamount', '');
//		$ =$this->input->post('', '');
                    
                $dataems=array();
		if(!empty($_POST['accno']))	
	               	$dataems['emp_bank_accno']      =$_POST['accno'];
		if(!empty($ifsccode))
	               	$dataems['emp_bank_ifsc_code']  =$ifsccode;
		if(!empty($bankname))
        	       	$dataems['emp_bankname']        =$bankname;
		if(!empty($bbranch))
                	$dataems['emp_bankbranch']      =$bbranch;
		if(!empty($panno))
			$dataems['emp_pan_no']		=$panno;
		if(!empty($nhisno))
			$dataems['emp_nhisidno']	=$nhisno;
		if(!empty($paycom))
			$dataems['emp_paycomm']		=$paycom;
		if(!empty($salgrdid))
			$dataems['emp_salary_grade']	= $salgrdid;
               
		$qtrtype = $this->input->post('qtrtype', '');
		$society = $this->input->post('society', '');
		$socmem = $this->input->post('societymember', '');
		$upfno = $this->input->post('upfno', '');
		if(empty($upfno)){
			$cpsno = $this->input->post('cpsno', '');
			$upfno = $cpsno;
		}
                $datapp = array(
                    	'ems_house_type'          =>$qtrtype,
                    	'ems_house_no'            =>$_POST['qtrno'],
                    	'ems_pensioncontri'        =>$_POST['pcontri'],
                    	'ems_upfno'                =>$_POST['upfno'], 
                    	'ems_universityemp'        =>$_POST['uniemp'],
                    	'ems_washingallowance'     =>$_POST['washallw'],
                    	'ems_deductupf'            =>$_POST['dedupf'],
                    	'ems_hragrade'             =>$_POST['hragrade'], 
                    	'ems_ccagrade'             =>$_POST['ccagrade'], 
                    	'ems_inclsummary'          =>$_POST['incsumm'], 
                    	'ems_lic1no'               =>$_POST['lic1no'], 
                    	'ems_lic1amount'           =>$_POST['lic1amount'],
                    	'ems_lic2no'               =>$_POST['lic2no'],
                    	'ems_lic2amount'           =>$_POST['lic2amount'], 
                    'ems_lic3no'               =>$_POST['lic3no'], 
                    'ems_lic3amount'           =>$_POST['lic3amount'],
                    'ems_lic4no'               =>$_POST['lic4no'], 
                    'ems_lic4amount'           =>$_POST['lic4amount'], 
                    'ems_lic5no'               =>$_POST['lic5no'], 
                    'ems_lic5amount'           =>$_POST['lic5amount'], 
                    'ems_prdno1'               =>$_POST['prdno1'],
                    'ems_prdno2'               =>$_POST['prdno2'], 
                    'ems_prdno3'               =>$_POST['prdno3'],
                    'ems_plino1'               =>$_POST['plino1'],
                    'ems_plino2'               =>$_POST['plino2'],
                    'ems_society'              =>$society,
                    'ems_societymember'        =>$socmem,
		    'ems_prd1amount'		=> $prd1amount,
		    'ems_prd2amount'		=>$prd2amount, 
			'ems_prd3amount'	=>$prd3amount,
			'ems_pli1amount'	=>$pli1amount,
			'ems_pli2amount'	=>$pli2amount,
			'ems_socamount'		=>$socamount,
			'ems_erfq'		=>$rfqemp,
			'ems_erfqhra'		=>$exhra,
			'ems_qoccupai'		=>$qoemp,
			'ems_rentgrade'		=>$rentgrade,
			'ems_spfcgs'		=>$spfcgsno,
			'ems_spfcgsamount'	=>$spfcgsamount,
			'ems_spfcgs2000'	=>$spfcgs2no,
			'ems_spfcgs2amount'	=>$spfcgs2amount,
			'ems_fsfno'		=>$fsfno,
			'ems_fsfamount'		=>$fsfamount,
			'ems_bbmicr'		=>$bbmicr,
			'ems_acctype'		=>$acctype,
			'ems_bbadd'		=>$bbadd,
			'ems_bbphone'		=>$bbphone,
			'ems_bbemail'		=>$bbemail,
			'ems_nhisamount' 	=>$nhisamount,
                );

              //  $emppfno = $this->input->post('emppfno', '');
		$empid = $this->input->post('empid', '');
		//print_r($datapp);
                // echo $emppfno; echo "empid".$empid;die();
                //$getid= $this->sismodel->get_listspfic1('employee_master_support','ems_id','ems_code',$emppfno)->ems_id;
                $emppfno= $this->sismodel->get_listspfic1('employee_master_support','ems_code','ems_empid',$empid)->ems_code;
               // if(!empty($getid)){
                if(!empty($empid)){
                  //  $entryid=$getid->ems_id;
                    //$pprofileflag=$this->sismodel->updaterec('employee_master_support', $datapp, 'ems_id', $getid);
                    $pprofileflag=$this->sismodel->updaterec('employee_master_support', $datapp, 'ems_empid', $empid);
                    //$emsflag=$this->sismodel->updaterec('employee_master', $dataems , 'emp_code', $emppfno);
                    $emsflag=$this->sismodel->updaterec('employee_master', $dataems , 'emp_id', $empid);
                    if (!$pprofileflag)
                    {
                        $this->logger->write_logmessage("update","Trying to add updated values in payroll profile ", " payroll profile values are not updated please try again");
                        $this->logger->write_dblogmessage("update","Trying to updated values in payroll profile", " payroll profile values are not updated please try again");
                        $this->session->set_flashdata('err_message','Error in adding updated values in payroll profile - '  , 'error');
                        redirect('payrollprofile/payprofile');
                    }
                    else{
                        $this->logger->write_logmessage("update","Add updated values in payroll profile", "payroll profile values updated  successfully...");
                        $this->logger->write_dblogmessage("update","Add  updated values in payroll profile", " payroll profile values updated  successfully...");
                        $this->session->set_flashdata("success", " Payroll Profile values updated  successfully. PF No is  " ."[ "  .$emppfno. " ]");
                        redirect('payrollprofile/payprofile');
                    }
                } 
                              
             
            }//form flag
        }//button press
        $this->load->view('payrollprofile/payprofile');     
    }
	
       	public function payleaveentry(){
	        if(isset($_POST['pleaveent'])) {
            		$this->form_validation->set_rules('emppfno','Employee PF Number','trim|xss_clean');
	            	$this->form_validation->set_rules('pal','PAL','trim|xss_clean');
            		$this->form_validation->set_rules('eol','EOL','trim|xss_clean');
            		$this->form_validation->set_rules('month','Month','trim|xss_clean');
            		$this->form_validation->set_rules('year','Year','trim|xss_clean');
            		if($this->form_validation->run() == FALSE){
				$this->load->view('payrollprofile/payleaveentry');
                		return;
            		}    
            		else{
                
//                		$emppfno = $this->input->post('emppfno', '');
                		$empid = $this->input->post('empid', '');
	//			echo "I".$emppfno."and id is ".$empid ; die();
                		$emppal = $this->input->post('pal', '');
                		$empeol = $this->input->post('eol', '');
                		$empmonth = $this->input->post('month', '');
                		$empyear = $this->input->post('year', '');
	//			$empid= $this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$emppfno)->emp_id;
				$empldata = array(
					'sle_empid' => $empid,
					'sle_year' => $empyear,
					'sle_month' => $empmonth,
					'sle_pal' => $emppal,
					'sle_eol' => $empeol,
					'sle_creatorid' => $this->session->userdata('username'),
					'sle_creationdate' => date('Y-m-d H:i:s'),
					'sle_modifierid' => $this->session->userdata('username'),
					'sle_modifidate' => date('Y-m-d H:i:s')
				);
				if(!empty($empid)){
					$insflag=$this->sismodel->insertrec('salary_leave_entry', $empldata);	
				}else{
					$this->session->set_flashdata('err_message','Error in adding updated values in payroll profile - '  , 'error');
					redirect('payrollprofile/viewpayleaveentry');
					return;
				}
				if (!$insflag)
		                    	{
                	        	$this->logger->write_logmessage("insert","Trying to add leave  values in payroll profile ", " payroll profile leave values are not inserted please try again");
                        		$this->logger->write_dblogmessage("insert","Trying to add leave values in payroll profile", " payroll profile leave values are not inserted please try again");
                        		$this->session->set_flashdata('err_message','Error in adding updated values in payroll profile - '  , 'error');
                        		redirect('payrollprofile/payleaveentry');
					return;
	                    	}
        	            	else{
                	        	$this->logger->write_logmessage("insert","Add leave entry  values in payroll profile", "payroll profile leave values inserted  successfully...");
                        		$this->logger->write_dblogmessage("insert","Add  leave entry values in payroll profile", " payroll profile values inserted  successfully...");
	                        	$this->session->set_flashdata("success", " Payroll Profile leave values inserted  successfully. PF No is  " ."[ "  .$emppfno. " ]");
 		       	                redirect('payrollprofile/viewpayleaveentry');
					return;
                	    	}


			}
		}
		$this->load->view('payrollprofile/payleaveentry');
	}

	public function viewpayleaveentry(){
		$cyear=	$this->session->flashdata('empyear');
		$cmonth=$this->session->flashdata('empmonth');
		if(empty($cyear)){
			$cyear= date("Y");
		}
		if(empty($cmonth)){
			$cmonth= date("M");
		}
		if(isset($_POST['filter'])) {
                        $this->form_validation->set_rules('month','Month','trim|xss_clean');
                        $this->form_validation->set_rules('year','Year','trim|xss_clean');
			$cmonth = $this->input->post('month', '');
                        $cyear = $this->input->post('year', '');
		} 
		$data['cyer']=$cyear;
		$data['cmon']=$cmonth;
		$whdata = array('sle_year' =>$cyear,'sle_month' =>$cmonth);
		$selectfield='sle_id,sle_empid,sle_pal,sle_eol';
		$whorder ='sle_empid';
		$data['records'] = $this->sismodel->get_orderlistspficemore('salary_leave_entry',$selectfield,$whdata,$whorder);
		$this->load->view('payrollprofile/viewpayleaveentry',$data);
	}


       	public function editpayleaveentry($id){
		$data['id']=$id;
		$empid=$this->sismodel->get_listspfic1('salary_leave_entry','sle_empid','sle_id',$id)->sle_empid;
		$empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
		$data['empname']=$empname;
		$empcampid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid;
		$data['empcamp']=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$empcampid)->sc_name;
		$empuoid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
		$data['empuo']=$this->lgnmodel->get_listspfic1('authorities','name','id',$empuoid)->name;
		$empdeptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
		$data['empdept']=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$empdeptid)->dept_name;
		$empschid=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$empid)->emp_schemeid;
		$data['empsch']=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$empschid)->sd_name;
		$data['empschcode']=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$empschid)->sd_code;
		$empddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
		$data['empddo']=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$empddoid)->ddo_name;
		$data['empwtype']=$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype;
		$empdesigid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
		$data['empdesig']=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$empdesigid)->desig_name;
		$data['empeol']=$this->sismodel->get_listspfic1('salary_leave_entry','sle_eol','sle_id',$id)->sle_eol;
		$data['emppal']=$this->sismodel->get_listspfic1('salary_leave_entry','sle_pal','sle_id',$id)->sle_pal;
		$data['empmon']=$this->sismodel->get_listspfic1('salary_leave_entry','sle_month','sle_id',$id)->sle_month;
		$data['empyear']=$this->sismodel->get_listspfic1('salary_leave_entry','sle_year','sle_id',$id)->sle_year;

	        if(isset($_POST['epleaveent'])) {
	            	$this->form_validation->set_rules('pal','PAL','trim|xss_clean');
            		$this->form_validation->set_rules('eol','EOL','trim|xss_clean');
            		$this->form_validation->set_rules('month','Month','trim|xss_clean');
            		$this->form_validation->set_rules('year','Year','trim|xss_clean');
            		if($this->form_validation->run() == FALSE){
				$this->load->view('payrollprofile/editpayleaveentry',$data);
                		return;
            		}    
            		else{
                
                		$emppal = $this->input->post('pal', '');
                		$empeol = $this->input->post('eol', '');
                		$empmonth = $this->input->post('month', '');
                		$empyear = $this->input->post('year', '');
				$empldata = array(
					'sle_year' => $empyear,
					'sle_month' => $empmonth,
					'sle_pal' => $emppal,
					'sle_eol' => $empeol,
					'sle_modifierid' => $this->session->userdata('username'),
					'sle_modifidate' => date('Y-m-d H:i:s')
				);
				$insflag=$this->sismodel->updaterec('salary_leave_entry', $empldata,'sle_id',$id);	
				if (!$insflag)
		                    	{
                	        	$this->logger->write_logmessage("insert","Trying to update leave  values in payroll profile ", " payroll profile leave values are not updated please try again");
                        		$this->logger->write_dblogmessage("insert","Trying to update leave values in payroll profile", " payroll profile leave values are not updated please try again");
                        		$this->session->set_flashdata('err_message','Error in adding updated values in payroll profile - '  , 'error');
                        		redirect('payrollprofile/editpayleaveentry/'.$id);
					return;
	                    	}
        	            	else{
                	        	$this->logger->write_logmessage("update","Update leave entry  values in payroll profile", "payroll profile leave values updated  successfully...");
                        		$this->logger->write_dblogmessage("update","Update  leave entry values in payroll profile", " payroll profile values updateed  successfully...");
	                        	$this->session->set_flashdata("success", " Payroll Profile leave values updated  successfully. Name is  " ."[ "  .$empname. " ]");
					$this->session->set_flashdata('empyear', $empyear);
					$this->session->set_flashdata('empmonth', $empmonth);
 		       	                redirect('payrollprofile/viewpayleaveentry');
					return;
                	    	}
			}
		}
		$this->load->view('payrollprofile/editpayleaveentry',$data);
	}
	
	public function deletepayleaves($id){
                $roleid=$this->session->userdata('id_role');
                if($roleid == 1){
                        $delflag=$this->sismodel->deleterow('salary_leave_entry','sle_id',$id);
			$this->logger->write_logmessage("delete"," to delete sal leave  values in payroll profile ", " payroll profile sal leave values are  deleted");
                        $this->logger->write_dblogmessage("delete","to delete sal leave values in payroll profile", " payroll profile sal leave values are  deleted ");
                        $this->session->set_flashdata("success", " Payroll Profile transfer values removed  successfully. SLE No is  " ."[ "  .$id. " ]");
                        redirect('payrollprofile/viewpayleaveentry');
                }else{
                        $this->session->set_flashdata("err_message", " You do not have the privledge to remove the data");
                        redirect('payrollprofile/viewpayleaveentry');
                }
        }

       	public function paytransentry(){
		$data['hragrade']= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        	$data['ccagrade']= $this->sismodel->get_listspfic2('cca_grade_city','cgc_id','cgc_gradename');
//		$whorder='hg_gradeid';
//		$data['hragrade'] =$this->sismodel->get_orderlistspficemore('hra_grade','hg_gradeid,hg_amount','',$whorder);
//		$whorder='cca_gradeid';
//		$data['ccagrade'] =$this->sismodel->get_orderlistspficemore('ccaallowance_calculation','cca_gradeid,cca_amount','',$whorder);
	        if(isset($_POST['ptransent'])) {
            		$this->form_validation->set_rules('emppfno','Employee PF Number','trim|xss_clean');
	            	$this->form_validation->set_rules('days','Days','trim|xss_clean');
            		$this->form_validation->set_rules('transit','Transit','trim|xss_clean');
	            	$this->form_validation->set_rules('hrafrom','HRAfrom','trim|xss_clean');
	            	$this->form_validation->set_rules('hrato','HRAto','trim|xss_clean');
	            	$this->form_validation->set_rules('ccafrom','CCAfrom','trim|xss_clean');
	            	$this->form_validation->set_rules('ccato','CCAto','trim|xss_clean');
            		$this->form_validation->set_rules('month','Month','trim|xss_clean');
            		$this->form_validation->set_rules('year','Year','trim|xss_clean');
            		if($this->form_validation->run() == FALSE){
                        		$this->session->set_flashdata('err_message','Validation fails in payroll transfer profile - '  , 'error');
				$this->load->view('payrollprofile/paytransentry');
                		return;
            		}    
            		else{
                		$empid = $this->input->post('empid', '');
                		$empdays = $this->input->post('days', '');
                		$emptransit = $this->input->post('transit', '');
                		$emphrafrom = $this->input->post('hrafrom', '');
                		$emphrato = $this->input->post('hrato', '');
                		$empccafrom = $this->input->post('ccafrom', '');
                		$empccato = $this->input->post('ccato', '');
                		$empmonth = $this->input->post('month', '');
                		$empyear = $this->input->post('year', '');
//				$empid= $this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$emppfno)->emp_id;
				$empldata = array(
					'ste_empid' => $empid,
					'ste_year' => $empyear,
					'ste_month' => $empmonth,
					'ste_days' => $empdays,
					'ste_transit' => $emptransit,
					'ste_hrafrom' => $emphrafrom,
					'ste_hrato' => $emphrato,
					'ste_ccafrom' => $empccafrom,
					'ste_ccato' => $empccato,
					'ste_creatorid' => $this->session->userdata('username'),
					'ste_createdate' => date('Y-m-d H:i:s'),
					'ste_modifierid' => $this->session->userdata('username'),
					'ste_modifydate' => date('Y-m-d H:i:s')
				);
				$insflag=$this->sismodel->insertrec('salary_transfer_entry', $empldata);	
				if (!$insflag)
		                    	{
                	        	$this->logger->write_logmessage("insert","Trying to add transfer  values in payroll profile ", " payroll profile transfer values are not inserted please try again");
                        		$this->logger->write_dblogmessage("insert","Trying to add transfer values in payroll profile", " payroll profile transfer values are not inserted please try again");
                        		$this->session->set_flashdata('err_message','Error in adding updated values in payroll profile - '  , 'error');
                        		redirect('payrollprofile/paytransentry');
	                    	}
        	            	else{
                	        	$this->logger->write_logmessage("insert","Add transfer entry  values in payroll profile", "payroll profile transfer values inserted  successfully...");
                        		$this->logger->write_dblogmessage("insert","Add transfer entry values in payroll profile", " payroll profile values inserted  successfully...");
	                        	$this->session->set_flashdata("success", " Payroll Profile transfer values inserted  successfully. PF No is  " ."[ "  .$emppfno. " ]");
 		       	                redirect('payrollprofile/viewpaytransentry');
                	    	}


			}
		}
		$this->load->view('payrollprofile/paytransentry',$data);
	}

	public function viewpaytransentry(){
		$cyear= $this->session->flashdata('empyear');
                $cmonth=$this->session->flashdata('empmonth');
                if(empty($cyear)){
                        $cyear= date("Y");
                }
                if(empty($cmonth)){
                        $cmonth= date("M");
                }

		if(isset($_POST['filter'])) {
                        $this->form_validation->set_rules('month','Month','trim|xss_clean');
                        $this->form_validation->set_rules('year','Year','trim|xss_clean');
                        $cmonth = $this->input->post('month', '');
                        $cyear = $this->input->post('year', '');
                }

		$data['cyer']=$cyear;
		$data['cmon']=$cmonth;
		$whdata = array('ste_year' =>$cyear,'ste_month' =>$cmonth);
		$selectfield='ste_id,ste_empid,ste_days,ste_transit,ste_hrafrom,ste_hrato,ste_ccafrom,ste_ccato';
		$whorder ='ste_empid';
		$data['records'] = $this->sismodel->get_orderlistspficemore('salary_transfer_entry',$selectfield,$whdata,$whorder);
		$this->load->view('payrollprofile/viewpaytransentry',$data);
	}

       	public function editpaytransentry($id){
		$data['id']=$id;
		$whorder='hg_gradeid';
		$data['hragrade'] =$this->sismodel->get_orderlistspficemore('hra_grade','hg_gradeid,hg_amount','',$whorder);
		$whorder='cca_gradeid';
		$data['ccagrade'] =$this->sismodel->get_orderlistspficemore('ccaallowance_calculation','cca_gradeid,cca_amount','',$whorder);

		$empid=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_empid','ste_id',$id)->ste_empid;
                $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
                $data['empname']=$empname;
                $empcampid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid;
                $data['empcamp']=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$empcampid)->sc_name;
                $empuoid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
                $data['empuo']=$this->lgnmodel->get_listspfic1('authorities','name','id',$empuoid)->name;
                $empdeptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
                $data['empdept']=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$empdeptid)->dept_name;
                $empschid=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$empid)->emp_schemeid;
                $data['empsch']=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$empschid)->sd_name;
                $data['empschcode']=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$empschid)->sd_code;
                $empddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
                $data['empddo']=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$empddoid)->ddo_name;
                $data['empwtype']=$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype;
                $empdesigid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
                $data['empdesig']=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$empdesigid)->desig_name;
                $data['empdays']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_days','ste_id',$id)->ste_days;
                $data['emptransit']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_transit','ste_id',$id)->ste_transit;
                $data['empmon']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_month','ste_id',$id)->ste_month;
                $data['empyear']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_year','ste_id',$id)->ste_year;
                $data['emphrafrom']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_hrafrom','ste_id',$id)->ste_hrafrom;
                $data['emphrato']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_hrato','ste_id',$id)->ste_hrato;
                $data['empccafrom']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_ccafrom','ste_id',$id)->ste_ccafrom;
                $data['empccato']=$this->sismodel->get_listspfic1('salary_transfer_entry','ste_ccato','ste_id',$id)->ste_ccato;

	        if(isset($_POST['eptransent'])) {
	            	$this->form_validation->set_rules('days','Days','trim|xss_clean');
            		$this->form_validation->set_rules('transit','Transit','trim|xss_clean');
	            	$this->form_validation->set_rules('hrafrom','HRAfrom','trim|xss_clean');
	            	$this->form_validation->set_rules('hrato','HRAto','trim|xss_clean');
	            	$this->form_validation->set_rules('ccafrom','CCAfrom','trim|xss_clean');
	            	$this->form_validation->set_rules('ccato','CCAto','trim|xss_clean');
            		$this->form_validation->set_rules('month','Month','trim|xss_clean');
            		$this->form_validation->set_rules('year','Year','trim|xss_clean');
            		if($this->form_validation->run() == FALSE){
                        		$this->session->set_flashdata('err_message','Validation fails in payroll transfer profile - '  , 'error');
				$this->load->view('payrollprofile/editpaytransentry',$data);
                		return;
            		}    
            		else{
                		$empid = $this->input->post('empid', '');
                		$empdays = $this->input->post('days', '');
                		$emptransit = $this->input->post('transit', '');
                		$emphrafrom = $this->input->post('hrafrom', '');
                		$emphrato = $this->input->post('hrato', '');
                		$empccafrom = $this->input->post('ccafrom', '');
                		$empccato = $this->input->post('ccato', '');
                		$empmonth = $this->input->post('month', '');
                		$empyear = $this->input->post('year', '');
				$empldata = array(
					'ste_year' => $empyear,
					'ste_month' => $empmonth,
					'ste_days' => $empdays,
					'ste_transit' => $emptransit,
					'ste_hrafrom' => $emphrafrom,
					'ste_hrato' => $emphrato,
					'ste_ccafrom' => $empccafrom,
					'ste_ccato' => $empccato,
					'ste_modifierid' => $this->session->userdata('username'),
					'ste_modifydate' => date('Y-m-d H:i:s')
				);
				$insflag=$this->sismodel->updaterec('salary_transfer_entry', $empldata,'ste_id',$id);	
				if (!$insflag)
		                    	{
                	        	$this->logger->write_logmessage("insert","Trying to update transfer  values in payroll profile ", " payroll profile transfer values are not updated please try again");
                        		$this->logger->write_dblogmessage("insert","Trying to update transfer values in payroll profile", " payroll profile transfer values are not updated please try again");
                        		$this->session->set_flashdata('err_message','Error in  updated values in payroll profile - '  , 'error');
                        		redirect('payrollprofile/editpaytransentry/'.$id);
	                    	}
        	            	else{
                	        	$this->logger->write_logmessage("insert","Update transfer entry  values in payroll profile", "payroll profile transfer values updated  successfully...");
                        		$this->logger->write_dblogmessage("insert","Update transfer entry values in payroll profile", " payroll profile values updated  successfully...");
	                        	$this->session->set_flashdata("success", " Payroll Profile transfer values updated  successfully. Name is  " ."[ "  .$empname. " ]");
					$this->session->set_flashdata('empyear', $empyear);
                                        $this->session->set_flashdata('empmonth', $empmonth);
 		       	                redirect('payrollprofile/viewpaytransentry');
                	    	}
			}
		}
		$this->load->view('payrollprofile/editpaytransentry',$data);
	}

	public function deletepaytrans($id){
		$roleid=$this->session->userdata('id_role');
		if($roleid == 1){
			$delflag=$this->sismodel->deleterow('salary_transfer_entry','ste_id',$id);	
			$this->logger->write_logmessage("delete"," to delete sal trans  values in payroll profile ", " payroll profile sal trans values are  deleted");
                        $this->logger->write_dblogmessage("delete","to delete sal trans values in payroll profile", " payroll profile sal trans values are  deleted ");
			$this->session->set_flashdata("success", " Payroll Profile transfer values removed  successfully. STE No is  " ."[ "  .$id. " ]");
			redirect('payrollprofile/viewpaytransentry');
		}else{
			$this->session->set_flashdata("err_message", " You do not have the privledge to remove the data");
			redirect('payrollprofile/viewpaytransentry');
		}
	}
        public function empsalslip(){
            $currentuser=$this->session->userdata('username');
            $empid = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
            
            $month = $this->input->post('month', TRUE);
            $year = $this->input->post('year', TRUE);
            
            $cmonth= date('M');
            $cyear= date("Y"); 
       // echo "999==".$month."--------".$year;
            if($month=='' && $year==''){
                $month=$cmonth;
                $year=$cyear;
            }
            $spec_data['empid'] = $empid;
            $spec_data['month']=$cmonth;
            $spec_data['year']=$cyear;
            
            if(isset($_POST['salpro'])){
                $spec_data['month']=$month;
                $spec_data['year']=$year;
                
                $selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
                // $whorder = " sh_name asc";
                $whdata = array('sh_type' =>'I');
                $spec_data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
                $whdata = array('sh_type' =>'D');
                $spec_data['deduction'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,''); 
                
                $dupdata=array(
                    'ste_empid'                =>$empid,
                    'ste_month'                =>$month,
                    'ste_year'                 =>$year,   
                );
                $dupexists=$this->sismodel->isduplicatemore('salary_transfer_entry',$dupdata);
                
                $this->load->library('pdf');
                $this->pdf->set_paper("A4", "portrait");
                if(!$dupexists){
                    $this->pdf->load_view('setup3/salaryslipcopy',$spec_data);
                }
                else{
                    $this->pdf->load_view('setup3/salaryslipcopy2',$spec_data);  
                }
                $this->pdf->render();
                $this->pdf->stream("salaryslipcopy.pdf");
                
            } //if
            
            $this->load->view('payrollprofile/empsalaryslip',$spec_data);
        }
 }
?>
