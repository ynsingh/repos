
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
            
            if($this->form_validation->run() == FALSE){
                $this->load->view('payrollprofile/payprofile');
                return;
            }    
            else{
                
                $bankname = $this->input->post('bname', '');
                $ifsccode = $this->input->post('ifsccode', '');
                $bbranch = $this->input->post('bbranch', '');
            /*    if(!empty($bankname)|| !empty($ifsccode) ||!empty($bbranch) ){
                    $bank_ifsc=$bankname."#".$ifsccode."#".$bbranch;
                    
                }
                else{
                    $bank_ifsc='##';
                }*/
                $dataems=array(
                    'emp_bank_accno'         =>$_POST['accno'],
                    'emp_bank_ifsc_code'     =>$ifsccode,
                    'emp_bankname'     =>$bankname,
                    'emp_bankbranch'     =>$bbranch,
                    
                );
                $datapp = array(
                    'ems_house_type'          =>$_POST['qtrtype'],
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
                    'ems_society'              =>$_POST['society'],
                    'ems_societymember'        =>$_POST['societymember'],
                );
                $emppfno = $this->input->post('emppfno', '');
                $getid= $this->sismodel->get_listspfic1('employee_master_support','ems_id','ems_code',$emppfno)->ems_id;
                if(!empty($getid)){
                  //  $entryid=$getid->ems_id;
                    $pprofileflag=$this->sismodel->updaterec('employee_master_support', $datapp, 'ems_id', $getid);
                    $emsflag=$this->sismodel->updaterec('employee_master', $dataems , 'emp_code', $emppfno);
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
                
  //              		$emppfno = $this->input->post('emppfno', '');
                		$empid = $this->input->post('empid', '');
//				echo "I".$emppfno."and id is ".$empid ; die();
                		$emppal = $this->input->post('pal', '');
                		$empeol = $this->input->post('eol', '');
                		$empmonth = $this->input->post('month', '');
                		$empyear = $this->input->post('year', '');
//				$empid= $this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$emppfno)->emp_id;
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

				$insflag=$this->sismodel->insertrec('salary_leave_entry', $empldata);	
				if (!$insflag)
		                    	{
                	        	$this->logger->write_logmessage("insert","Trying to add leave  values in payroll profile ", " payroll profile leave values are not inserted please try again");
                        		$this->logger->write_dblogmessage("insert","Trying to add leave values in payroll profile", " payroll profile leave values are not inserted please try again");
                        		$this->session->set_flashdata('err_message','Error in adding updated values in payroll profile - '  , 'error');
                        		redirect('payrollprofile/payleaveentry');
	                    	}
        	            	else{
                	        	$this->logger->write_logmessage("insert","Add leave entry  values in payroll profile", "payroll profile leave values inserted  successfully...");
                        		$this->logger->write_dblogmessage("insert","Add  leave entry values in payroll profile", " payroll profile values inserted  successfully...");
	                        	$this->session->set_flashdata("success", " Payroll Profile leave values inserted  successfully. PF No is  " ."[ "  .$emppfno. " ]");
 		       	                redirect('payrollprofile/viewpayleaveentry');
                	    	}


			}
		}
		$this->load->view('payrollprofile/payleaveentry');
	}

	public function viewpayleaveentry(){
		$cyear= date("Y");
		$cmonth= date("M");
		$whdata = array('sle_year' =>$cyear,'sle_month' =>$cmonth);
		$selectfield='sle_empid,sle_pal,sle_eol';
		$whorder ='sle_empid';
		$data['records'] = $this->sismodel->get_orderlistspficemore('salary_leave_entry',$selectfield,$whdata,$whorder);
		$this->load->view('payrollprofile/viewpayleaveentry',$data);
	}

       	public function paytransentry(){
	        if(isset($_POST['ptransent'])) {
            		$this->form_validation->set_rules('emppfno','Employee PF Number','trim|xss_clean');
	            	$this->form_validation->set_rules('days','Days','trim|xss_clean');
            		$this->form_validation->set_rules('transit','Transit','trim|xss_clean');
	            	$this->form_validation->set_rules('hrafrom','HRAfrom','trim|xss_clean');
	            	$this->form_validation->set_rules('hrato','HRAto','trim|xss_clean');
            		$this->form_validation->set_rules('month','Month','trim|xss_clean');
            		$this->form_validation->set_rules('year','Year','trim|xss_clean');
            		if($this->form_validation->run() == FALSE){
                        		$this->session->set_flashdata('err_message','Validation fails in payroll transfer profile - '  , 'error');
				$this->load->view('payrollprofile/paytransentry');
                		return;
            		}    
            		else{
                
  //              		$emppfno = $this->input->post('emppfno', '');
                		$empid = $this->input->post('empid', '');
//				echo "I".$emppfno."and id is ".$empid ; die();
                		$empdays = $this->input->post('days', '');
                		$emptransit = $this->input->post('transit', '');
                		$emphrafrom = $this->input->post('hrafrom', '');
                		$emphrato = $this->input->post('hrato', '');
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
		$this->load->view('payrollprofile/paytransentry');
	}

	public function viewpaytransentry(){
		$cyear= date("Y");
		$cmonth= date("M");
		$whdata = array('ste_year' =>$cyear,'ste_month' =>$cmonth);
		$selectfield='ste_empid,ste_days,ste_transit,ste_hrafrom,ste_hrato';
		$whorder ='ste_empid';
		$data['records'] = $this->sismodel->get_orderlistspficemore('salary_transfer_entry',$selectfield,$whdata,$whorder);
		$this->load->view('payrollprofile/viewpaytransentry',$data);
	}
 }
?>
