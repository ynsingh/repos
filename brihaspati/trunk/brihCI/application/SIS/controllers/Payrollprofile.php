
<?php

/* 
 * @name Payrollprofile.php
 * @author Manorama Pal(palseema30@gmail.com) Salary head
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
       
 }
?>
