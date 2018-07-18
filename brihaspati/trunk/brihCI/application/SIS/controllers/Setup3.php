
<?php

/* 
 * @name Setup3.php
 * @author Manorama Pal(palseema30@gmail.com) Salary head
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Setup3 extends CI_Controller
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
        //$this->grademaster();
    }
    /********************* Add salary head form  *******************************************/
    public function salaryhead(){
        
        if(isset($_POST['addsalaryhead'])) {
            //form validation
            
            $this->form_validation->set_rules('salh_code','Salary Head Code','trim|required|xss_clean|alpha_numeric_spaces|callback_issalheadcode_Exist');
            $this->form_validation->set_rules('salh_name','Salary Head Name','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('salh_type','Salary Head Type','required|xss_clean');
            $this->form_validation->set_rules('salh_caltype','Calculation Type','trim|required|xss_clean');
            $this->form_validation->set_rules('salh_tax','Taxable','trim|required|xss_clean');
            $this->form_validation->set_rules('salh_cat','Category','trim|xss_clean');
            $this->form_validation->set_rules('salh_nickname','Salary Head Short Name','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('salh_desc','Salary Head Description','trim|xss_clean');
           
            if($this->form_validation->run() == FALSE){
                $this->load->view('setup3/salaryhead');
                return;
            }//formvalidation
            else{
               
                $data = array(
                    'sh_code'                  =>$_POST['salh_code'],
                    'sh_name'                  =>$_POST['salh_name'],
                    'sh_tnt'                   =>$_POST['salhtnt'], 
                    'sh_shortname'             =>$_POST['salh_nickname'],
                    
                    'sh_type'                  =>$_POST['salh_type'],
                    'sh_calc_type'             =>$_POST['salh_caltype'],
                    'sh_taxable'               =>$_POST['salh_tax'],
                    'sh_category'              =>$_POST['salh_cat'],
                    'sh_ledgercode'            =>'',
                    'sh_description'           =>$_POST['salh_desc'], 
                    'sh_creatorid'             =>$this->session->userdata('username'),
                    'sh_creatordate'            =>date('y-m-d'),
                    'sh_modifierid'           =>$this->session->userdata('username'),
                    'sh_modifydate'             =>date('y-m-d'),
                ); 
                $dupcheck = array(
                   // 'sh_code'                  =>$_POST['salh_code'],
                    'sh_name'                  =>$_POST['salh_name'],
                    'sh_tnt'                   =>$_POST['salhtnt'],
                    //'sh_shortname'             =>$_POST['salh_nickname'],
                                                    
                ); 
                //$shcode = $this->input->post('salh_code', TRUE);
                $shname = $this->input->post('salh_name', TRUE);
               
                
                $salhdup = $this->sismodel->isduplicatemore('salary_head', $dupcheck);
                if($salhdup == 1 ){

                      $this->session->set_flashdata("err_message", "Record is already exist with this 'Head Name = $shname' ");
                      $this->load->view('setup3/salaryhead');
                      return;
                }
                else{
                    $salheadflag=$this->sismodel->insertrec('salary_head', $data);
                    if (!$salheadflag)
                    {
                        $this->logger->write_logmessage("insert","Trying to add salary head ", "salary head is not added ".$_POST['salh_name']);
                        $this->logger->write_dblogmessage("insert","Trying to add salary head ", "salary head is not added ".$_POST['salh_name']);
                        $this->session->set_flashdata('err_message','Error in adding salary head - '  , 'error');
                        redirect('setup3/salaryhead');
                    }
                    else{
                        $this->logger->write_logmessage("insert","Add salary head", "salary head".$_POST['salh_name']." added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add  salary head", "salary head  ".$_POST['salh_name']."added  successfully...");
                        $this->session->set_flashdata("success", " Salary Head = "."[" .$_POST['salh_name']. "]" ." added successfully...");
                        redirect("setup3/salaryhead_list");
                    }
                    
                }
                          
            }//closer else form run true
            
        }
        $this->load->view('setup3/salaryhead');
    }
    
    /********************* closer salary head form  *******************************************/
    public function issalheadcode_Exist(){
        
        $salhcode = $this->input->post('salh_code', TRUE);
        if(!empty($salhcode)){
            $is_exist= $this->sismodel->isduplicate('salary_head','sh_code',$salhcode);
            if ($is_exist)
            {
                $this->form_validation->set_message('issalheadcode_Exist', 'Head Code =  ' . $salhcode .' is already exist.');
                return false;
            }
            else {
                return true;
            } 
        }    
        
       
    }
    
    /************************************** Display Salary Head records **************************/

    public function salaryhead_list(){
        $array_items = array('success' => '', 'err_message' => '', 'warning' =>'');
	$data['records']= $this->sismodel->get_list('salary_head');
        $data['teach']= array();
        $data['nonteach']=array();
        $data['tntboth']=array();
        foreach($data['records'] as $record){
            if($record->sh_tnt == NULL){
                array_push($data['tntboth'],$record->sh_id);
            }
            if($record->sh_tnt =='Teaching'){
                array_push($data['teach'],$record->sh_id);
            
            }
            if($record->sh_tnt =='Non Teaching'){
            array_push($data['nonteach'],$record->sh_id);
            
            }
        }
        $this->logger->write_logmessage("view"," view Salary head list" );
        $this->logger->write_dblogmessage("view"," view Salary head list");
        $this->load->view('setup3/salaryhead_list',$data);
    }
    /********************* closer salary head list  *******************************************/
    
    /************************************** Edit Salary Head records **************************/
    public function edit_salaryhead($id){
        $array_items = array('success' => '', 'err_message' => '', 'warning' =>'');
        $data['id'] = $id;
        $data['salhdata'] = $this->sismodel->get_listrow('salary_head','sh_id',$id)->row();
        $this->load->view('setup3/edit_salaryhead',$data);
    }
    /********************* closer edit salary head list  *******************************************/
    
    /****************************  START UPDATE DATA **********************************************************/
    public function update_salheaddata($id){
        $array_items = array('success' => '', 'err_message' => '', 'warning' =>'');
        $salh_dataquery=$this->sismodel->get_listrow('salary_head','sh_id', $id);
        $salh_data['salhdata'] = $salh_dataquery->row();
        $salh_data['id'] = $id;
        if(isset($_POST['updatesalhead'])) {
            //form validation
            $this->form_validation->set_rules('salh_code','Head Code','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('salh_name','Head Name','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('salh_type','Head Type','required|xss_clean');
            $this->form_validation->set_rules('salh_caltype','Calculation Type','trim|required|xss_clean');
            $this->form_validation->set_rules('salh_tax','Taxable','trim|required|xss_clean');
            $this->form_validation->set_rules('salh_cat','Category','trim|xss_clean');
            $this->form_validation->set_rules('salh_nickname','Head Short Name','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('salh_desc','Head Description','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                $this->load->view('setup3/edit_salaryhead',$salh_data);
                return;
            }//formvalidation
            else{
                $shcode = $this->input->post('salh_code', TRUE);
                $shtnt= $this->input->post('salhtnt', TRUE);
                $shname = $this->input->post('salh_name', TRUE);
                $shnickname = $this->input->post('salh_nickname', TRUE);
                $salhtype = $this->input->post('salh_type', TRUE);
                $salhcaltype = $this->input->post('salh_caltype', TRUE);
                $salhtax = $this->input->post('salh_tax', TRUE);
                $salhcategory = $this->input->post('salh_cat', TRUE);
                $salhdesc = $this->input->post('salh_desc', TRUE);
                
                $logmessage = "";
                if($salh_data['salhdata']->sh_code != $shcode)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_code. " changed by " .$shcode;
                if($salh_data['salhdata']->sh_name != $shname)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_name. " changed by " .$shname;
                if($salh_data['salhdata']->sh_shortname != $shnickname)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_shortname . " changed by " .$shnickname;
                if($salh_data['salhdata']->sh_type != $salhtype)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_type . " changed by " .$salhtype;
                if($salh_data['salhdata']->sh_calc_type != $salhcaltype)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_calc_type . " changed by " .$salhcaltype;
                if($salh_data['salhdata']->sh_taxable != $salhtax)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_taxable . " changed by " .$salhtax;
                if($salh_data['salhdata']->sh_category != $salhcategory)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_category . " changed by " .$salhcategory;
                if($salh_data['salhdata']->sh_description != $salhdesc)
                    $logmessage = "Edit Salary Head Data " .$salh_data['salhdata']->sh_description . " changed by " .$salhdesc;
                
                $edit_data = array(
                    'sh_code'                  =>$_POST['salh_code'],
                    'sh_name'                  =>$_POST['salh_name'],
                    'sh_tnt'                   =>$_POST['salhtnt'],
                    'sh_shortname'             =>$_POST['salh_nickname'],
                    'sh_type'                  =>$_POST['salh_type'],
                    'sh_calc_type'             =>$_POST['salh_caltype'],
                    'sh_taxable'               =>$_POST['salh_tax'],
                    'sh_category'              =>$_POST['salh_cat'],
                    'sh_ledgercode'            =>'',
                    'sh_description'           =>$_POST['salh_desc'], 
                    'sh_creatorid'             =>$this->session->userdata('username'),
                    'sh_creatordate'            =>date('y-m-d'),
                    'sh_modifierid'           =>$this->session->userdata('username'),
                    'sh_modifydate'             =>date('y-m-d'),
                );
               
                if($salh_data['salhdata']->sh_code != $shcode){
                    
                    $dupcheck = array(
                        'sh_code'                  =>$_POST['salh_code'],
                        //'sh_name'                  =>$_POST['salh_name'],
                
                    ); 
                    $salhdup = $this->sismodel->isduplicatemore('salary_head', $dupcheck);
                    if($salhdup == 1 ){

                      $this->session->set_flashdata("err_message", "Record is already exist with this ' Head Code = $shcode ' so please assign any other code. ");
                      $this->load->view('setup3/edit_salaryhead',$salh_data);
                      return;
                    }
               
                }//if dupcondiotion 
                if($salh_data['salhdata']->sh_name !=$shname){
                     $dupcheck = array(
                       // 'sh_code'                  =>$_POST['salh_code'],
                        'sh_name'                  =>$_POST['salh_name'],
                        'sh_tnt'                   =>$_POST['salhtnt'],
                
                    ); 
                    $salhdup = $this->sismodel->isduplicatemore('salary_head', $dupcheck);
                    if($salhdup == 1 ){

                      $this->session->set_flashdata("err_message", "Record is already exist with this  ' Head Name = $shname ' so please change it. ");
                      $this->load->view('setup3/edit_salaryhead',$salh_data);
                      return;
                    }
                 
                }//ifdupsalhname
                $editshflag=$this->sismodel->updaterec('salary_head', $edit_data, 'sh_id', $id);
                if(!$editshflag){
                    $this->logger->write_logmessage("error","Edit salary head error", "Edit salary head details. $logmessage ");
                    $this->logger->write_dblogmessage("error","Edit salary head error", "Edit salary head details. $logmessage ");
                    $this->session->set_flashdata('err_message','Error in updating salary head - ' . $logmessage . '.', 'error');
                    $this->load->view('setup3/edit_salaryhead', $edit_data);
                    
                }
                else{
                    $this->logger->write_logmessage("update","Edit Salary Head by".$this->session->userdata('username') , "Edit Salary head details. $logmessage ");
                    $this->logger->write_dblogmessage("update","Edit salary head by".$this->session->userdata('username') , "Edit salary head details. $logmessage ");
                    $this->session->set_flashdata('success','Salary Head details updated successfully.');
                    redirect('setup3/salaryhead_list');
                    
                }
               
            }//else form validation
        }// isset button
    }
    /****************************  closer update data salary head **********************************************/
    
    /************************************** Display salary heads for formula  **************************/

    public function salaryformula_list(){
	$selectfield="salary_head.sh_id, salary_head.sh_code, salary_head.sh_name, salary_head.sh_tnt,salary_formula.sf_id, salary_formula.sf_formula";
	$joincond = 'salary_formula.sf_salhead_id = salary_head.sh_id';
	$whdata = array('salary_head.sh_calc_type'=> 'Y');
	$data['formulrecord'] =$this->sismodel->get_jointbrecord('salary_head',$selectfield,'salary_formula',$joincond,'left',$whdata);
//        $data['formulrecord'] =$this->sismodel->salhead_formula();
        $this->logger->write_logmessage("view"," view Salary head  formula list" );
        $this->logger->write_dblogmessage("view"," view Salary head formula list");
        $this->load->view('setup3/salformula_list',$data);
    }
    /********************* closer Display salary heads for formula  *******************************************/
    
    /************************************** Add salary head formula  **************************/

    public function add_salaryformula($id){
        $data['id'] = $id;
        $data['salhdata'] = $this->sismodel->get_listrow('salary_head','sh_id',$id)->row();
        if(isset($_POST['add_salhformula'])) {
            
            $this->form_validation->set_rules('salh_formula','Apply Formula','trim|xss_clean');
            $this->form_validation->set_rules('salh_desc','Head Description','trim|xss_clean');
            
            if($this->form_validation->run() == FALSE){
                
                $this->load->view('setup3/add_salaryformula',$data);
                return;
            }//formvalidation
            else{
                              
                $data = array(
                    'sf_salhead_id'            =>$id,
                    'sf_formula'               =>$_POST['salh_formula'],
                    'sf_description'           =>$_POST['salh_desc'],
                    'sf_creatorid'             =>$this->session->userdata('username'),
                    'sf_creatordate'            =>date('y-m-d'),
                    'sf_modifierid'           =>$this->session->userdata('username'),
                    'sf_modifydate'             =>date('y-m-d'),
                );
                
                $salhformulaflag=$this->sismodel->insertrec('salary_formula', $data);
                
                if (!$salhformulaflag)
                {
                        $this->logger->write_logmessage("insert","Trying to add formula for salary head ", "Formula for salary head is not added ".$_POST['salh_name']);
                        $this->logger->write_dblogmessage("insert","Trying to add formula for salary head ", "Formula for salary head is not added ".$_POST['salh_name']);
                        $this->session->set_flashdata('err_message','Error in adding formula for salary head - '  , 'error');
                        $this->load->view('setup3/add_salaryformula', $data);
                }
                else{
                        $this->logger->write_logmessage("insert","Add formula for salary head", " formula for salary head [".$_POST['salh_name']." ] is added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add formula for salary head", " formula for salary head [ ".$_POST['salh_name']." ] is added  successfully...");
                        $this->session->set_flashdata("success", " Formula for Salary Head = "."[" .$_POST['salh_name']. "]" ." is added successfully...");
                        redirect("setup3/salaryformula_list");
                }
                    
            }//else true form cond
            
        }//post button
        $this->load->view('setup3/add_salaryformula',$data);
    }
    /********************* closer Display salary heads for formula aaply records  *******************************************/
    
    /************************************** Edit salary head formula  **************************/

    public function edit_salaryformula($id){
        $data['id'] = $id;
        $data['salhdata'] = $this->sismodel->get_listrow('salary_formula','sf_id',$id)->row();
        if(isset($_POST['edit_salhformula'])) {
            
            $this->form_validation->set_rules('salh_formula','Apply Formula','trim|xss_clean');
            $this->form_validation->set_rules('salh_desc','Head Description','trim|xss_clean');
            
            if($this->form_validation->run() == FALSE){
                
                $this->load->view('setup3/edit_salaryformula',$data);
                return;
            }//formvalidation
            else{
                
                $edit_data = array(
                    'sf_salhead_id'            =>$data['salhdata']->sf_salhead_id,
                    'sf_formula'               =>$_POST['salh_formula'],
                    'sf_description'           =>$_POST['salh_desc'],
                    'sf_creatorid'             =>$this->session->userdata('username'),
                    'sf_creatordate'            =>date('y-m-d'),
                    'sf_modifierid'           =>$this->session->userdata('username'),
                    'sf_modifydate'             =>date('y-m-d'),
                );
                              
                $editshforflag=$this->sismodel->updaterec('salary_formula', $edit_data, 'sf_id', $id);
                if (!$editshforflag)
                {   $this->logger->write_logmessage("error","Edit Formula error", "Edit Salary formula. $logmessage ");
                    $this->logger->write_dblogmessage("error","Edit Formula error", "Edit salary formula. $logmessage ");
                    $this->session->set_flashdata('err_message','Error in updating Formula - ' . $logmessage . '.', 'error');
                    $this->load->view('setup3/edit_salaryhead', $edit_data);
                }
                else{
                    
                    $this->logger->write_logmessage("update","Edit salary formula by".$this->session->userdata('username') , "Edit Salary formula. $logmessage ");
                    $this->logger->write_dblogmessage("update","Edit salary formula by".$this->session->userdata('username') , "Edit salary formula. $logmessage ");
                    $this->session->set_flashdata('success','Salary formula details updated successfully.'." Salary Head = "."[ " .$_POST['salh_name']. " ]");
                    redirect("setup3/salaryformula_list");
                }
                    
            }//else true form cond
            
        }//post button
        $this->load->view('setup3/edit_salaryformula',$data);
    }
    /********************* closer Edit salary heads  formula  *******************************************/
       
    /********************* Add Employee type form  *******************************************/
    public function employeetype(){
        if(isset($_POST['addemptype'])) {
            //form validation
            
            $this->form_validation->set_rules('emptype_code','Employee Type Code','trim|required|xss_clean|alpha_numeric_spaces|callback_isemptypecode_Exist');
            $this->form_validation->set_rules('emptype_name','Employee Type Name','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('pfapplies','PF applies','trim|xss_clean');
            $this->form_validation->set_rules('maxpf_limit','Max PF Limit','trim|xss_clean|numeric');
            $this->form_validation->set_rules('emptype_sname','Employee Short name','trim|xss_clean|alpha_numeric_spaces');
                                  
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/employeetype');
                return;
            }//formvalidation
            else{
               
                $data = array(
                    'empt_code'                  =>$_POST['emptype_code'],
                    'empt_name'                  =>$_POST['emptype_name'],
                    'empt_tnt'                  =>$_POST['emptnt'],
                    'empt_shortname'             =>$_POST['emptype_sname'],
                    'empt_pfapplies'             =>$_POST['pfapplies'],
                    'empt_maxpflimit'            =>$_POST['maxpf_limit'],
                    'empt_creatorid'              =>$this->session->userdata('username'),
                    'empt_creatordate'            =>date('y-m-d'),
                    'empt_modifierid'             =>$this->session->userdata('username'),
                    'empt_modifydate'             =>date('y-m-d'),
                ); 
                $dupcheck = array(
                    'empt_code'                  =>$_POST['emptype_code'],
                    'empt_name'                  =>$_POST['emptype_name'],
                    'empt_tnt'                  =>$_POST['emptnt'],
                               
                ); 
              
                $etname = $this->input->post('empt_name', TRUE);
                $emptdup = $this->sismodel->isduplicatemore('employee_type', $dupcheck);
                if($emptdup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this 'Employee Type Name = $etname' ");
                    $this->load->view('setup3/employeetype');
                    return;
                }
                else{
                    $emptyeflag=$this->sismodel->insertrec('employee_type', $data);
                    if (!$emptyeflag)
                    {
                        $this->logger->write_logmessage("insert","Trying to add employee type ", " employee type is not added ".$etname);
                        $this->logger->write_dblogmessage("insert","Trying to add employee type ", " employee type is not added ".$etname);
                        $this->session->set_flashdata('err_message','Error in adding employee type - '  , 'error');
                        redirect('setup3/employeetype');
                    }
                    else{
                        $this->logger->write_logmessage("insert","Add employee type ", "employee type ".$_POST['emptype_name'] ." added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add  employee type ", "employee type  ".$_POST['emptype_name'] ."added  successfully...");
                        $this->session->set_flashdata("success", " Employee Type = "."[" .$_POST['emptype_name'] . "]" ." record insert successfully...");
                        redirect("setup3/employeetype_list");
                    }
                    
                }
                          
            }//closer else form run true
            
        }
        $this->load->view('setup3/employeetype');
    }
       
    /*********************  closer Add Employee type form  *******************************************/
    
    /********************* check for duplicate employee type code  *******************************************/
    public function isemptypecode_Exist(){
        
        $etcode = $this->input->post('emptype_code', TRUE);
        if(!empty($etcode)){
            $is_exist= $this->sismodel->isduplicate('employee_type','empt_code',$etcode);
            if ($is_exist)
            {
                $this->form_validation->set_message('isemptypecode_Exist', 'Employee Type Code =  ' . $etcode .' is already exist. so please insert any other code.');
                return false;
            }
            else {
                return true;
            } 
        }    
        
       
    }
    
    /************************************** closer check for duplicate employee type code  **************************/
    /************************************** Display employee type  **************************/

    public function employeetype_list(){
        $data['emptype_record'] =$this->sismodel->get_list('employee_type');
        $this->logger->write_logmessage("view"," view employee type list" );
        $this->logger->write_dblogmessage("view"," view employee type list");
        $this->load->view('setup3/emptype_list',$data);
    }
     /**************************************closer  Display employee type  **************************/
    /********************* Add Employee type form  *******************************************/
    public function edit_employeetype($id){
       
        $data['id'] = $id;
        $data['emptypedata'] = $this->sismodel->get_listrow('employee_type','empt_id',$id)->row();
        if(isset($_POST['updateemptype'])) {
            //form validation
            
            $this->form_validation->set_rules('emptype_code','Employee Type Code','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('emptype_name','Employee Type Name','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('pfapplies','PF applies','trim|xss_clean');
            $this->form_validation->set_rules('maxpf_limit','Max PF Limit','trim|xss_clean|numeric');
            $this->form_validation->set_rules('emptype_sname','Employee Short name','trim|xss_clean|alpha_numeric_spaces');
                                  
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/edit_emptype',$data);
                return;
            }//formvalidation
            else{
                $etcode = $this->input->post('emptype_code', TRUE);
                $etname = $this->input->post('emptype_name', TRUE);
                $etype = $this->input->post('emptnt', TRUE);
                $etnickname = $this->input->post('emptype_sname', TRUE);
                $etpfaply = $this->input->post('pfapplies', TRUE);
                $etpfmaxlimit = $this->input->post('maxpf_limit', TRUE);
                
                
                $logmessage = "";
                if($data['emptypedata']->empt_code != $etcode)
                    $logmessage = "Edit Employee Type Data " .$data['emptypedata']->empt_code. " changed by " .$etcode;
                if($data['emptypedata']->empt_name != $etname)
                    $logmessage = "Edit Employee Type Data " .$data['emptypedata']->empt_name. " changed by " .$etname;
                if($data['emptypedata']->empt_shortname != $etnickname)
                    $logmessage = "Edit Employee Type Data " .$data['emptypedata']->empt_shortname . " changed by " .$etnickname;
                if($data['emptypedata']->empt_pfapplies != $etpfaply)
                    $logmessage = "Edit Employee Type Data " .$data['emptypedata']->empt_pfapplies . " changed by " .$etpfaply;
                if($data['emptypedata']->empt_maxpflimit != $etpfmaxlimit)
                    $logmessage = "Edit Employee Type Data " .$data['emptypedata']->empt_maxpflimit . " changed by " .$etpfmaxlimit;
                
                $editdata = array(
                    'empt_code'                  =>$_POST['emptype_code'],
                    'empt_name'                  =>$_POST['emptype_name'],
                    'empt_tnt'                  =>$_POST['emptnt'],
                    'empt_shortname'             =>$_POST['emptype_sname'],
                    'empt_pfapplies'             =>$_POST['pfapplies'],
                    'empt_maxpflimit'            =>$_POST['maxpf_limit'],
                    'empt_modifierid'             =>$this->session->userdata('username'),
                    'empt_modifydate'             =>date('y-m-d'),
                ); 
               
                if($data['emptypedata']->empt_code != $etcode){
                    
                    $dupcheck = array(
                        'empt_code'                  =>$_POST['emptype_code'],
                        //'empt_name'                  =>$_POST['emptype_name'],
                
                    ); 
                    $emptypedup = $this->sismodel->isduplicatemore('employee_type', $dupcheck);
                    if($emptypedup == 1 ){
                      
                      $this->session->set_flashdata("err_message", "Record is already exist with this ' Code = $etcode so please assign any other code. ");
                      $this->load->view('setup3/edit_emptype',$data);
                      return;
                    }
                                        
                }//if dupcondiotion 
                if($data['emptypedata']->empt_name != $etname){
                     $dupcheck = array(
                    
                        'empt_name'                  =>$_POST['emptype_name'],
                                        
                    ); 
                    $emptypedup = $this->sismodel->isduplicatemore('employee_type', $dupcheck);
                    if($emptypedup == 1 ){

                      $this->session->set_flashdata("err_message", "Record is already exist with this  ' Name = $etname ' so please change it. ");
                      $this->load->view('setup3/edit_emptype',$data);
                      return;
                    }
                    
                }//ifdupcondition
                $editetflag=$this->sismodel->updaterec('employee_type', $editdata, 'empt_id', $id);
                if(!$editetflag){
                      
                    $this->logger->write_logmessage("error","Edit employee type error", "Edit employee type details. $logmessage ");
                    $this->logger->write_dblogmessage("error","Edit employee type error", "Edit employee type. $logmessage ");
                    $this->session->set_flashdata('err_message','Error in updating employee type - ' . $logmessage . '.', 'error');
                    $this->load->view('setup3/edit_emptype', $data);
                    
                }
                else{
                    $this->logger->write_logmessage("update","Edit employee type by  ".$this->session->userdata('username') , "Edit employee type details. $logmessage ");
                    $this->logger->write_dblogmessage("update","Edit employee type by  ".$this->session->userdata('username') , "Edit employee type details. $logmessage ");
                    $this->session->set_flashdata('success','Record updated successfully.'.'[ Employee Type is = ' .$_POST['emptype_name'] .' ]');
                    redirect('setup3/employeetype_list');
                    
                }
                
                           
            }//closer else form run true
            
        }
        $this->load->view('setup3/edit_emptype',$data);
    }
       
    /*********************  closer Add Employee type form  *******************************************/
    
    
    /*********************  Salary Head Configuration form  *******************************************/
    public function salhead_config(){
        
        $this->emptype= $this->sismodel->get_list('employee_type');
        $this->salhead =$this->sismodel->get_list('salary_head');
        $data=array();
        $shconf=array();
        $emptype = $this->input->post('emptype', TRUE);
        if(!empty($emptype)){
            $data['seloption'] = $emptype;
            //$data['seloption'] = $emptype;
            $this->confval=$this->sismodel->get_listspfic1('salaryhead_configuration','shc_salheadid ','shc_emptypeid',$emptype);
            if(!empty($this->confval)){
                $data['shconf']=$this->confval->shc_salheadid;
            }
                       
        }
        if(isset($_POST['update'])){
                      
            $checklist = $this->input->post('check_list', TRUE);
            $shlist=(join(", ", $checklist));
                     
            $dupexists=$this->sismodel->isduplicate('salaryhead_configuration','shc_emptypeid',$emptype);
            if(!$dupexists){
                $updata = array(
                    'shc_emptypeid'                 =>$emptype,
                    'shc_salheadid'                 =>$shlist,
                    'shc_scid'                      =>NULL,
                    'shc_creatorid'                 =>$this->session->userdata('username'),
                    'shc_creatordate'               =>date('y-m-d'),
                    'shc_modifierid'                =>$this->session->userdata('username'),
                    'shc_modifydate'                =>date('y-m-d'),
                 );
                $shconfigflag = $this->sismodel->insertrec('salaryhead_configuration', $updata);
            }
            else{
               
                $updata = array(
                    'shc_emptypeid'                 =>$emptype,
                    'shc_salheadid'                 =>$shlist,
                    'shc_scid'                      =>NULL,
                   // 'shc_creatorid'                 =>$this->session->userdata('username'),
                    //'shc_creatordate'               =>date('y-m-d'),
                    'shc_modifierid'                =>$this->session->userdata('username'),
                    'shc_modifydate'                =>date('y-m-d'),
                );
                $shconfigflag = $this->sismodel->updaterec('salaryhead_configuration',$updata,'shc_emptypeid',$emptype);
              //  $emptype = $this->input->post('seloption', TRUE);
                //$data['seloption'] = $emptype;
            }
            
            if (!$shconfigflag)
            {
                $this->logger->write_logmessage("insert","Trying to add Employee type wise salary head configuration ", " employee type wise salary head configuration is not added ".$etname);
                $this->logger->write_dblogmessage("insert","Trying to add Employee type wise salary head configuration ", "employee type wise salary head configuration is not added ".$etname);
                $this->session->set_flashdata('err_message','Error in Employee type wise salary head configuration - '  , 'error');
                redirect('setup3/salhead_config');
            }
            else{
               
                $this->logger->write_logmessage("insert","Employee type wise salary head configuration ", "Employee type wise salary head configuration added  successfully...");
                $this->logger->write_dblogmessage("insert","Employee type wise salary head configuration ", "Employee type wise salary head configuration added  successfully...");
                $this->session->set_flashdata("success", "  Employee type wise salary head configuration updated successfully...");
                redirect('setup3/salhead_config',$data);
               // $this->load->view('setup3/add_salheadconfig',$data);
                //return;
                
            }
                          
            
        }
        $this->load->view('setup3/add_salheadconfig',$data);
         
    }
    /*********************  closer Salary Head Configuration form  *******************************************/
    
    /*********************  Salary Head  Default value  form *******************************************/
    public function shdefaultvalue(){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->salhead =$this->sismodel->get_list('salary_head');
        $data=array();
        $pband = $this->input->post('payband', TRUE);
       if(isset($_POST['load'])){
            if(!empty($pband)){
                $selectfield ="shdv_paybandid, shdv_salheadid,shdv_defaultvalue";
                $whdata = array ('shdv_paybandid' => $pband,'shdv_defaultvalue != ' => NULL);
                $is_exist= $this->sismodel->isduplicate('salaryhead_defaultvalue','shdv_paybandid',$pband);
                //echo "exitt=====".$is_exist;
                //die;
                if($is_exist){
                    $data['seloption'] = $pband;
                    //echo "exitt==if===";
                    $cdata = $this->sismodel->get_listspficemore('salaryhead_defaultvalue',$selectfield,$whdata);
                    $data['shdvalue']=$cdata;
                }
                else{
                    $data['seloption'] = $pband;
                    //echo "exitt==else===";
                    $cdata='';
                    $data['shdvalue']=$cdata;
                    redirect('setup3/shdefaultvalue/'.$data['seloption']);
                    //return;
                }
           
           
            }
        }
        if(isset($_POST['update'])){
            $tsize = $this->input->post('totalsize', TRUE);
          //  echo "tsize====".$tsize;
            for ($i=0; $i<$tsize ;$i++){                        
                $shid = $this->input->post('sheadid'.$i, TRUE);
                $dfvalue= $this->input->post('defaultval'.$i, TRUE); 
                // echo "shid====".$shid."dval====".$dfvalue,"pb===".$pband;
                $dupdata=array(
                    'shdv_paybandid'                 =>$pband,
                    'shdv_salheadid'                 =>$shid,   
                );
                $dupexists=$this->sismodel->isduplicatemore('salaryhead_defaultvalue',$dupdata);
                if(!$dupexists){
                    
                    $updata = array(
                        'shdv_paybandid'                 =>$pband,
                        'shdv_salheadid'                 =>$shid,
                        'shdv_defaultvalue'              =>$dfvalue,
                        'shdv_remarks'                   =>NULL,
                        'shdv_creatorid'                 =>$this->session->userdata('username'),
                        'shdv_creatordate'               =>date('y-m-d'),
                        'shdv_modifierid'                =>$this->session->userdata('username'),
                        'shdv_modifydate'                =>date('y-m-d'),
                    ); 
               
                    $shdvalflag = $this->sismodel->insertrec('salaryhead_defaultvalue', $updata);
             
                }
                else{
                
              
                    $updata = array(
                        'shdv_paybandid'                 =>$pband,
                        'shdv_salheadid'                 =>$shid,
                        'shdv_defaultvalue'              =>$dfvalue,
                        'shdv_remarks'                   =>NULL,
                        //'shdv_creatorid'                 =>$this->session->userdata('username'),
                        //'shdv_creatordate'               =>date('y-m-d'),
                        'shdv_modifierid'                =>$this->session->userdata('username'),
                        'shdv_modifydate'                =>date('y-m-d'),
                    ); 
                    $datawh=array('shdv_paybandid' => $pband,'shdv_salheadid' => $shid);
                    $cdata = $this->sismodel->get_listspficemore('salaryhead_defaultvalue','shdv_id',$datawh);
                    $sdid=$cdata[0]->shdv_id;
                    $shdvalflag = $this->sismodel->updaterec('salaryhead_defaultvalue',$updata,'shdv_id',$sdid);
                }
                
            
            }//totalsize  
            if (!$shdvalflag)
            {
                $this->logger->write_logmessage("insert","Trying to add Payband wise salary head default value ", " Payband wise salary head default value is not added ".$etname);
                $this->logger->write_dblogmessage("insert","Trying to add Payband wise salary head default value ", " Payband wise salary head default value is not added ".$etname);
                $this->session->set_flashdata('err_message','Error in  Payband wise salary head default value - '  , 'error');
                redirect('setup3/shdefaultvalue',$data);
            }
            else{
                $this->logger->write_logmessage("insert"," Payband wise salary head default value ", "Payband wise salary head default value added  successfully...");
                $this->logger->write_dblogmessage("insert","  Payband wise salary head default value ", "Payband wise salary head default value added  successfully...");
                $this->session->set_flashdata("success", "   Payband wise salary head default value updated successfully...");
                redirect("setup3/shdefaultvalue",$data);
            }
        }
        $this->load->view('setup3/shdefaultvalue',$data);
        
    }
    /*********************  closer Salary Head Default value  *******************************************/
    
    /*********************   Salary Slip  form *********************************************************/
     public function salaryslip(){
        $this->load->view('setup3/salaryslip');
        
    }
    /********************* closer  Salary Slip  form *********************************************************/
    
}//class    
