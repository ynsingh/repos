
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
            if(($record->sh_tnt == NULL)||($record->sh_tnt == 'Common')){
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
        $empid=$this->uri->segment(3);
        $this->emptnt=$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype;
        $selectfield ="sh_id, sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
        $whorder = " sh_name asc";
       // $whdata = array ('saq_empid' => $emp_id,'saq_dgree NOT LIKE ' => 'B%','saq_dgree NOT LIKE ' => 'M%');
	$whdata = array('sh_type' =>'I');// 'sh_tnt' => $this->emptnt,'sh_tnt' => NULL);
        $data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,$whorder);
        $whdata = array('sh_type' =>'D');
        $data['deduction'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,$whorder);
        
        $this->emppfno=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $this->emptype=$this->sismodel->get_listspfic1('employee_master','emp_type_code ','emp_id',$empid)->emp_type_code;
        $this->emptypeid=$this->sismodel->get_listspfic1('employee_type','empt_id','empt_name',$this->emptype)->empt_id;
        
        $strarray=$this->sismodel->get_listspfic1('salaryhead_configuration','shc_salheadid','shc_emptypeid',$this->emptypeid)->shc_salheadid;
        $data['allowedhead']=explode(", ",$strarray);
       // print_r($data['allowedhead']);
        if(isset($_POST['upsalhdval'])){
            $tcount = $this->input->post('totalcount', TRUE);
            $tded = $this->input->post('totalded', TRUE);
          //  $totalincome = $this->input->post('incometotal', TRUE);
           // $totaldeduction = $this->input->post('deductiontotal', TRUE);
            //$netpay = $this->input->post('netpay', TRUE);
            $month=$this->uri->segment(4);
            $year=$this->uri->segment(5);
            $totalincome=0;
            $totaldeduction = 0;
            $netpay = 0;
            /***************************Incomes************************************/
            for ($i=0; $i<$tcount ;$i++){
                $headidin = $this->input->post('sheadidin'.$i, TRUE);
               // $headid = $this->input->post('sheadid'.$i, TRUE);
                $headval = $this->input->post('headamtI'.$i, TRUE);
               // $headval = $this->input->post('headamt'.$i, TRUE);
               
              //  echo "totalcc===".$tcount;
              //  echo "===print===".$headidin."====".$headval;
               // echo"\n====="."ttincome===".$totalincome."tdeduction==--".$totaldeduction."=netpay===".$netpay;
              //  echo "\n====".$LIC1."===".$LIC2."===".$LIC3."===".$LIC4."====".$LIC5."===".$LIC5."====".$PRD1."===".$PRD2."====".$PRD3."====".$PLI1."===".$PLI2;
               // die;
                $saldata = array(
                
                    'sald_empid'   =>$empid,
                    'sald_sheadid'  =>$headidin,
                    'sald_shamount' =>$headval,
                    'sald_month'    =>$month,
                    'sald_year'     =>'2018',
                
                
                
                );
                $upsaldataflag = $this->sismodel->insertrec('salary_data', $saldata);
                $totalincome+=$headval;
            } //tcount
            /*******************************Deductions***********************************/
            for ($j=0; $j<$tded ;$j++){
                $headidD = $this->input->post('sheadidded'.$j, TRUE);
                
               
                $headvald = $this->input->post('headamtD'.$j, TRUE);
                $saldata = array(
                
                    'sald_empid'   =>$empid,
                    'sald_sheadid'  =>$headidD,
                    'sald_shamount' => $headvald,
                    'sald_month'    =>$month,
                    'sald_year'     =>'2018',
                
                
                
                );
                $upsaldataflag = $this->sismodel->insertrec('salary_data', $saldata);
               
                $totaldeduction+=$headvald;
                
            }//totalcount 
            $netpay=$totalincome - $totaldeduction;
            $scid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid;
            $uoccid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
            $deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
            $desigid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
            $sopost=$this->sismodel->get_listspfic1('employee_master','emp_post','emp_id',$empid)->emp_post;
            $ddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
            $schmid=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$empid)->emp_schemeid;
            $payscaleid=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$empid)->emp_salary_grade;
            $bankaccno=$this->sismodel->get_listspfic1('employee_master','emp_bank_accno','emp_id',$empid)->emp_bank_accno;
            $wtype=$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype;
            $emptype=$this->sismodel->get_listspfic1('employee_master','emp_type_code','emp_id',$empid)->emp_type_code;
            $group=$this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$empid)->emp_group;
            
            $saldata1 = array(
               'sal_empid'             =>$empid,
               'sal_scid'              =>$scid,
               'sal_uoid'              =>$uoccid,
               'sal_deptid'            =>$deptid,
               'sal_desigid'           =>$desigid,
               'sal_sapost'            =>$sopost,
               'sal_ddoid'             =>$ddoid,
               'sal_schemeid'          =>$schmid,
               'sal_payscaleid'        =>$payscaleid,
               'sal_bankaccno'         =>$bankaccno,
               'sal_worktype'          =>$wtype,
               'sal_emptype'           =>$emptype,
               'sal_group'             =>$group,
               'sal_month'             =>$month,
               'sal_year'              =>'2018',
               'sal_totalincome'       =>$totalincome,
               'sal_totaldeduction'    =>$totaldeduction,
               'sal_netsalary'         =>$netpay,
               'sal_status'            =>'paid',
               'sal_paiddate'          =>date('y-m-d'),
               'sal_creatorid'         =>$this->session->userdata('username'),
               'sal_creationdate'       =>date('y-m-d'),
               'sal_updatedate'        =>date('y-m-d'),    
               'sal_modifierid'        =>$this->session->userdata('username'),
           
            );
              
            if (!$upsaldataflag)
            {
                $this->logger->write_logmessage("insert","Trying to add  salary data head wise", "  salary data head wise value is not added ".$this->emppfno);
                $this->logger->write_dblogmessage("insert","Trying to add salary data head wise ", " salary data head wise value is not added ".$this->emppfno);
                $this->session->set_flashdata('err_message','Error in  salary data head wise value - '  , 'error');
                redirect('setup3/salaryslip',$data);
            }
            else{
            
                $upsalaryflag = $this->sismodel->insertrec('salary', $saldata1);
                $this->logger->write_logmessage("insert"," salary data head wise value  ", " salary data head wise value added  successfully...");
                $this->logger->write_dblogmessage("insert"," salary data head wise value ", "salary data head wise value added  successfully...");
                $this->session->set_flashdata("success", "   salary data head wise value updated successfully... PF NO [ " .$this->emppfno. " ]");
                redirect("setup3/salaryslip",$data);
            }
            
        }//for button
        $this->load->view('setup3/salaryslip',$data);
        
    }
    /********************* closer  Salary Slip  form *********************************************************/
    
    /*********************   HRA Places Grade *********************************************************/
    public function hra_placesgrade(){
        $data['hrarecord'] =$this->sismodel->get_list('hra_grade_city');
        $this->logger->write_logmessage("view"," view hra place grade list" );
        $this->logger->write_dblogmessage("view"," view hra place grade list");
        $this->load->view('setup3/hra_placegradelist',$data);
        
    }
    /*********************  closer HRA Places Grade *********************************************************/ 
    
    /*********************   HRA Grade List*********************************************************/
    public function hra_grade(){
        $data['hragrade'] =$this->sismodel->get_list('hra_grade');
        $this->logger->write_logmessage("view"," view hra grade list" );
        $this->logger->write_dblogmessage("view"," view hra grade list");
        $this->load->view('setup3/hragradelist',$data);
        
    }
    /*********************  closer HRA  Grade *********************************************************/ 
    /*********************  ADD HRA Grade *********************************************************/
    public function add_hragrade(){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->hragrade= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        if(isset($_POST['add_hragrade'])) {
            //form validation
            
            $this->form_validation->set_rules('worktype','Working Type','trim|required|xss_clean');
            $this->form_validation->set_rules('paycomm','Pay Commission','trim|xss_clean');
            $this->form_validation->set_rules('payscale','Pay Scale','trim|required|xss_clean');
            $this->form_validation->set_rules('payrange','Pay Range','trim|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA Grade','trim|required|xss_clean');
            $this->form_validation->set_rules('amount','Amount','trim|required|xss_clean|numeric');
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/add_hragrade');
                return;
            }//formvalidation
            else{
                $data = array(
                    'hg_workingtype'    =>$_POST['worktype'],
                    'hg_paycomm'     =>$_POST['paycomm'],
                    'hg_payscaleid'     =>$_POST['payscale'],
                    'hg_payrange'     =>$_POST['payrange'],
                    'hg_gradeid'        =>$_POST['hragrade'],
                    'hg_amount'         =>$_POST['amount'],
                    'hg_creatorid'      =>$this->session->userdata('username'),
                    'hg_creatordate'    =>date('Y-m-d'),
                    'hg_modifierid'     =>$this->session->userdata('username'),
                    'hg_modifydate'     =>date('Y-m-d'),
                );
                
                $dupcheck = array(
                    'hg_workingtype'    =>$_POST['worktype'],
                    'hg_payscaleid'     =>$_POST['payscale'],
                    'hg_gradeid'        =>$_POST['hragrade'],
                    'hg_amount'         =>$_POST['amount'],
                ); 
                $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$_POST['payscale'])->sgm_name;
                $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$_POST['payscale'])->sgm_min;
                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$_POST['payscale'])->sgm_max;
                $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$_POST['payscale'])->sgm_gradepay;
                $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$_POST['hragrade'])->hgc_gradename;
                
                $hragradedup = $this->sismodel->isduplicatemore('hra_grade', $dupcheck);
                if($hragradedup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this combination [ Working Type =".$_POST['worktype']
                            ." Payscale = ".$fullstr ." HRA Grade = ".$hragradename ." Amount = ".$_POST['amount'].' ]');
                    $this->load->view('setup3/add_hragrade');
                    return;
                }
                else{
                    $hragradeflag=$this->sismodel->insertrec('hra_grade', $data);
                    if(!$hragradeflag)
                    {
                        $this->logger->write_logmessage("insert","Trying to add HRA Grade ", " HRA Grade is not added  HRA Grade= ".$hragradename." with payscale ".$fullstr);
                        $this->logger->write_dblogmessage("insert","Trying to add HRA Grade ", " HRA Grade is not added HRA Grade= ".$hragradename." with payscale ".$fullstr);
                        $this->session->set_flashdata('err_message','Error in adding HRA Grade - '  , 'error');
                        redirect('setup3/add_hragrade');
                    }
                    else{
                        $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$_POST['hragrade'])->hgc_gradename;
                        $this->logger->write_logmessage("insert","Add HRA Grade ", " HRA Grade = ".$hragradename." added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add  HRA Grade ", " HRA Grade = ".$hragradename."added  successfully...");
                        $this->session->set_flashdata("success", "[ Working Type =".$_POST['worktype'] ." HRA Grade = ".$hragradename
                                ." Payscale = ".$fullstr ." Amount = ".$_POST['amount'].']'." record insert successfully...");
                        redirect("setup3/hra_grade");
                    }
                    
                }//else dupcheck
                
            }//else
        }//isset button    
        $this->load->view('setup3/add_hragrade');
        
    }
    /*********************  closer Add HRA  Grade *********************************************************/ 
    /********************* Add Employee type form  *******************************************/
    public function edit_hragrade($id){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->hragrade= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        $data['id'] = $id;
        $data['hragradedata'] = $this->sismodel->get_listrow('hra_grade','hg_id',$id)->row();
        if(isset($_POST['edithragrade'])) {
            
            //form validation
            $this->form_validation->set_rules('worktype','Working Type','trim|required|xss_clean');
            $this->form_validation->set_rules('paycomm','Pay Commission','trim|xss_clean');
            $this->form_validation->set_rules('payscale','Pay Scale','trim|required|xss_clean');
            $this->form_validation->set_rules('payrange','Pay Range','trim|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA GRADE','trim|required|xss_clean');
            $this->form_validation->set_rules('amount','Amount','trim|required|xss_clean|numeric');
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/edit_hragrade',$data);
                return;
            }//formvalidation
            else{
                $wtype = $this->input->post('worktype', TRUE);
                $paycomm = $this->input->post('paycomm', TRUE);
                $payscale = $this->input->post('payscale', TRUE);
                $payrange = $this->input->post('payrange', TRUE);
                $grade = $this->input->post('hragrade', TRUE);
                $amount = $this->input->post('amount', TRUE);
                
                $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$payscale)->sgm_name;
                $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$payscale)->sgm_min;
                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$payscale)->sgm_max;
                $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$payscale)->sgm_gradepay;
                $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id', $grade)->hgc_gradename;
                
                $logmessage = "";
                if($data['hragradedata']->hg_workingtype != $wtype)
                    $logmessage = "Edit HRA Grade Data " .$data['hragradedata']->hg_workingtype. " changed by " .$wtype;
                if($data['hragradedata']->hg_payscaleid != $payscale)
                    $logmessage = "Edit HRA Grade Data " .$data['hragradedata']->hg_payscaleid. " changed by " .$payscale;
                if($data['hragradedata']->hg_gradeid != $grade)
                    $logmessage = "Edit HRA Grade Data " .$data['hragradedata']->hg_gradeid . " changed by " .$grade;
                if($data['hragradedata']->hg_amount != $amount)
                    $logmessage = "Edit HRA Grade Data " .$data['hragradedata']->hg_amount . " changed by " .$amount;
                
                $updata = array(
                    'hg_workingtype'    =>$wtype,
                    'hg_paycomm'     =>$paycomm,
                    'hg_payscaleid'     =>$payscale,
                    'hg_payrange'     =>$payrange,
                    'hg_gradeid'        =>$grade,
                    'hg_amount'         =>$amount,
                    //'hg_creatorid'      =>$this->session->userdata('username'),
                    //'hg_creatordate'    =>date('Y-m-d'),
                    'hg_modifierid'     =>$this->session->userdata('username'),
                    'hg_modifydate'     =>date('Y-m-d'),
                );
                $dupcheck = array(
                    'hg_workingtype'    =>$wtype,
                    'hg_payscaleid'     =>$payscale,
                    'hg_gradeid'        =>$grade,
                    'hg_amount'         =>$amount,
                ); 
                $hragradedup = $this->sismodel->isduplicatemore('hra_grade', $dupcheck);
                if($hragradedup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this combination [ Working Type = ".$wtype 
                            ." Payscale = ".$fullstr ." HRA Grade = ".$hragradename ." Amount = ".$amount.' ]');
                    redirect('setup3/hra_grade');
                    return;
                }
                else{
                    $editetflag=$this->sismodel->updaterec('hra_grade', $updata, 'hg_id', $id);
                    if(!$editetflag){
                        $this->logger->write_logmessage("error","error in Edit HRA Grade ", "Edit HRA Grade details. $logmessage ");
                        $this->logger->write_dblogmessage("error","error in Edit HRA Grade", "Edit HRA Grade . $logmessage ");
                        $this->session->set_flashdata('err_message','Error in updating HRA Grade - ' . $logmessage . '.', 'error');
                        $this->load->view('setup3/edit_hragrade', $data);
                    
                    }
                    else{
                        $this->logger->write_logmessage("update","Edit  HRA Grade by  ".$this->session->userdata('username') , "Edit  HRA Grade details. $logmessage ");
                        $this->logger->write_dblogmessage("update","Edit HRA Grade by  ".$this->session->userdata('username') , "Edit HRA Grade details. $logmessage ");
                        $this->session->set_flashdata('success','Record updated successfully.'.'[ HRA Grade = '.$hragradename." Working Type =".$wtype. 
                            " Payscale = ".$fullstr."  Amount = ".$amount.']');
                        redirect('setup3/hra_grade');
                    
                    }
                } //else dupcheck              
            }//else formvalidation
            
        }//if issetbutton press
        $this->load->view('setup3/edit_hragrade',$data);
    }//function
    
    /**This function Delete records */
    public function delete_hragrade($id) {
        $this->roleid=$this->session->userdata('id_role');
       // $this->hgid=$id;
        /* Deleting academicprofile Record */
        $delflag=$this->sismodel->deleterow('hra_grade','hg_id',$id);
        if (! delflag   )
        {   
            $this->logger->write_logmessage("delete", "Error in deleting HRA Grade record" . " [Entry id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in deleting HRA Grade record" . " [Entry id:" . $id . "]");
            $this->session->set_flashdata('Error in deleting deleting HRA Grade record - ');
            redirect('setup3/hra_grade');
        }
        else{
         
            $this->logger->write_logmessage("delete", " Deleted HRA Grade Record  ". " [Entry id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted HRA Grade Record  " . " [Entry id:" . $id . "]");
            $this->session->set_flashdata("success", 'Record  Deleted successfully ...' );
            redirect('setup3/hra_grade');
        }
        $this->load->view('setup3/hragradelist');
        
    }//closer
    /*********************   Rent Recovery for government quarters List*********************************************************/
    public function rentrecovery(){
        $data['rentrecovery'] =$this->sismodel->get_list('rent_recovery');
        $this->logger->write_logmessage("view"," view Rent Recovery for government quarter  list" );
        $this->logger->write_dblogmessage("view"," view  Rent Recovery for government quarter list");
        $this->load->view('setup3/rentrecoverylist',$data);
        
    }
    /*********************  closer Rent Recovery for government quarters List *********************************************************/ 
    
    /*********************  ADD Rent Recovery for government quarters *********************************************************/
    public function add_rentrecovery(){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->hragrade= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        if(isset($_POST['add_rentrecovery'])) {
            //form validation
            
            $this->form_validation->set_rules('worktype','Working Type','trim|required|xss_clean');
            $this->form_validation->set_rules('payscale','Pay Scale','trim|required|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA Grade','trim|required|xss_clean');
            $this->form_validation->set_rules('percentage','Rent Recovery Percentage','trim|required|xss_clean|numeric');
            $this->form_validation->set_rules('Description','Description','trim|required|xss_clean');
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/add_rentrecovery');
                return;
            }//formvalidation
            else{
                $data = array(
                    'rr_payscaleid'     =>$_POST['payscale'],
                    'rr_gradeid'        =>$_POST['hragrade'],
                    'rr_workingtype'    =>$_POST['worktype'],
                    'rr_percentage'     =>$_POST['percentage'],
                    'rr_description'     =>$_POST['Description'],
                    'rr_creatorid'      =>$this->session->userdata('username'),
                    'rr_creatordate'    =>date('Y-m-d'),
                    'rr_modifierid'     =>$this->session->userdata('username'),
                    'rr_modifydate'     =>date('Y-m-d'),
                );
                
                $dupcheck = array(
                    'rr_payscaleid'     =>$_POST['payscale'],
                    'rr_gradeid'        =>$_POST['hragrade'],
                    'rr_workingtype'    =>$_POST['worktype'],
                    'rr_percentage'     =>$_POST['percentage'],
                ); 
                $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$_POST['payscale'])->sgm_name;
                $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$_POST['payscale'])->sgm_min;
                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$_POST['payscale'])->sgm_max;
                $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$_POST['payscale'])->sgm_gradepay;
                $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$_POST['hragrade'])->hgc_gradename;
                
                $hragradedup = $this->sismodel->isduplicatemore('rent_recovery', $dupcheck);
                if($hragradedup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this combination [ Working Type =".$_POST['worktype']
                            ." Payscale = ".$fullstr ." HRA Grade = ".$hragradename ." Percentage = ".$_POST['percentage'].' ]');
                    $this->load->view('setup3/add_rentrecovery');
                    return;
                }
                else{
                    $hragradeflag=$this->sismodel->insertrec('rent_recovery', $data);
                    if(!$hragradeflag)
                    {
                        $this->logger->write_logmessage("insert","Trying to add Rent Recovery Percentage ", " Rent Recovery Percentage is not added  HRA Grade= ".$hragradename." with payscale ".$fullstr);
                        $this->logger->write_dblogmessage("insert","Trying to add Rent Recovery Percentage ", " Rent Recovery Percentage is not added HRA Grade= ".$hragradename." with payscale ".$fullstr);
                        $this->session->set_flashdata('err_message','Error in adding Rent Recovery Percentage - '  , 'error');
                        redirect('setup3/add_rentrecovery');
                    }
                    else{
                        $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$_POST['hragrade'])->hgc_gradename;
                        $this->logger->write_logmessage("insert","Add Rent Recovery Percentage ", " Rent Recovery Percentage for HRA Grade = ".$hragradename." added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add  Rent Recovery Percentage ", " Rent Recovery Percentage for HRA Grade = ".$hragradename."added  successfully...");
                        $this->session->set_flashdata("success", "[ Working Type =".$_POST['worktype'] ." HRA Grade = ".$hragradename
                                ." Payscale = ".$fullstr ." Percentage = ".$_POST['percentage'].']'." record insert successfully...");
                        redirect("setup3/rentrecovery");
                    }
                    
                }//else dupcheck
                
            }//else
        }//isset button    
        $this->load->view('setup3/add_rentrecovery');
        
    }
    /*********************  closer Rent Recovery for government quarters *********************************************************/ 
    
    /********************* Edit Rent Recovery for government quarters form  *******************************************/
    public function edit_rentrecovery($id){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->hragrade= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        $data['id'] = $id;
        $data['rrdata'] = $this->sismodel->get_listrow('rent_recovery','rr_id',$id)->row();
        if(isset($_POST['edit_rentrecovery'])) {
            
            //form validation
            $this->form_validation->set_rules('worktype','Working Type','trim|required|xss_clean');
            $this->form_validation->set_rules('payscale','Pay Scale','trim|required|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA GRADE','trim|required|xss_clean');
            $this->form_validation->set_rules('percentage','Rent Recovery Percentage','trim|required|xss_clean|numeric');
            $this->form_validation->set_rules('Description','Description','trim|required|xss_clean');
            
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/edit_rentrecovery',$data);
                return;
            }//formvalidation
            else{
                $wtype = $this->input->post('worktype', TRUE);
                $payscale = $this->input->post('payscale', TRUE);
                $grade = $this->input->post('hragrade', TRUE);
                $pert = $this->input->post('percentage', TRUE);
                $description = $this->input->post('Description', TRUE);
                
                $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$payscale)->sgm_name;
                $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$payscale)->sgm_min;
                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$payscale)->sgm_max;
                $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$payscale)->sgm_gradepay;
                $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id', $grade)->hgc_gradename;
                
                $logmessage = "";
                if($data['rrdata']->rr_workingtype != $wtype)
                    $logmessage = "Edit Rent Recovery Data " .$data['rrdata']->hg_workingtype. " changed by " .$wtype;
                if($data['rrdata']->rr_payscaleid != $payscale)
                    $logmessage = "Edit Rent Recovery Data " .$data['rrdata']->hg_payscaleid. " changed by " .$payscale;
                if($data['rrdata']->rr_gradeid != $grade)
                    $logmessage = "Edit Rent Recovery Data " .$data['rrdata']->hg_gradeid . " changed by " .$grade;
                if($data['rrdata']->rr_percentage != $percentage)
                    $logmessage = "Edit Rent Recovery Data " .$data['rrdata']->hg_percentage . " changed by " .$pert;
                
                if($data['rrdata']->rr_description != $description)
                    $logmessage = "Edit Rent Recovery Data " .$data['rrdata']->rr_description . " changed by " .$description;
                
                $updata = array(
                    'rr_payscaleid'     =>$payscale,
                    'rr_gradeid'        =>$grade,
                    'rr_workingtype'    =>$wtype,
                    'rr_percentage'     =>$pert,
                    'rr_description'    =>$description,
                    'rr_modifierid'     =>$this->session->userdata('username'),
                    'rr_modifydate'     =>date('Y-m-d'),
                );
                $dupcheck = array(
                    'rr_payscaleid'     =>$payscale,
                    'rr_gradeid'        =>$grade,
                    'rr_workingtype'    =>$wtype,
                    'rr_percentage'     =>$pert,
                ); 
                $hragradedup = $this->sismodel->isduplicatemore('rent_recovery', $dupcheck);
                if($hragradedup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this combination [ Working Type = ".$wtype 
                            ." Payscale = ".$fullstr ." HRA Grade = ".$hragradename ." Percentage = ".$pert.' ]');
                    redirect('setup3/rentrecovery');
                    return;
                }
                else{
                    $editetflag=$this->sismodel->updaterec('rent_recovery', $updata, 'rr_id', $id);
                    if(!$editetflag){
                        $this->logger->write_logmessage("error","error in Edit Rent Recovery Percentage ", "Edit Rent Recovery Percentage details. $logmessage ");
                        $this->logger->write_dblogmessage("error","error in Edit Rent Recovery Percentage", "Edit Rent Recovery Percentage . $logmessage ");
                        $this->session->set_flashdata('err_message','Error in updating Rent Recovery Percentage - ' . $logmessage . '.', 'error');
                        $this->load->view('setup3/edit_rentrecovery', $data);
                    
                    }
                    else{
                        $this->logger->write_logmessage("update","Edit Rent Recovery Percentage by  ".$this->session->userdata('username') , "Edit  Rent Recovery Percentage details. $logmessage ");
                        $this->logger->write_dblogmessage("update","Edit Rent Recovery Percentage by  ".$this->session->userdata('username') , "Edit Rent Recovery Percentage details. $logmessage ");
                        $this->session->set_flashdata('success','Record updated successfully.'.'[ HRA Grade = '.$hragradename." Working Type =".$wtype. 
                            " Payscale = ".$fullstr."  Percentage = ".$pert.']');
                        redirect('setup3/rentrecovery');
                    
                    }
                } //else dupcheck              
            }//else formvalidation
            
        }//if issetbutton press
        $this->load->view('setup3/edit_rentrecovery',$data);
    }//function
    /*******************************************Edit Rent Recovery for government quarters form ***********************/
    
    /**This function Delete  Rent Recovery records */
    public function delete_rentrecovery($id) {
        $this->roleid=$this->session->userdata('id_role');
       // $this->hgid=$id;
        /* Deleting academicprofile Record */
        $delflag=$this->sismodel->deleterow('rent_recovery','rr_id',$id);
        if (! delflag   )
        {   
            $this->logger->write_logmessage("delete", "Error in deleting Rent Recovery Percentage record" . " [Entry id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in deleting Rent Recovery Percentage record" . " [Entry id:" . $id . "]");
            $this->session->set_flashdata('Error in deleting deleting Rent Recovery Percentage record - ');
            redirect('setup3/rentrecovery');
        }
        else{
         
            $this->logger->write_logmessage("delete", " Deleted Rent Recovery Percentage  ". " [Entry id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted Rent Recovery Percentage Record  " . " [Entry id:" . $id . "]");
            $this->session->set_flashdata("success", 'Record  Deleted successfully ...' );
            redirect('setup3/rentrecovery');
        }
        $this->load->view('setup3/rentrecovery');
        
    }//closer
    /********************* CLOSER  Rent Recovery for government quarters List*********************************************************/
        
    /*********************   Rent City Compensatory Allowance(CCA) List*********************************************************/
    public function cca_allowance(){
        $data['ccadata'] =$this->sismodel->get_list('ccaallowance_calculation');
        $this->logger->write_logmessage("view"," view CCA allowance data  list" );
        $this->logger->write_dblogmessage("view"," view view CCA allowance data list");
        $this->load->view('setup3/cca_allowancelist',$data);
        
    }
    /*********************  closer City Compensatory Allowance(CCA) List *********************************************************/ 
    
    /*********************  ADD City Compensatory Allowance(CCA) *********************************************************/
    public function add_ccaallowance(){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->hragrade= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        if(isset($_POST['add_ccaalowance'])) {
            //form validation
            
            $this->form_validation->set_rules('worktype','Working Type','trim|required|xss_clean');
            $this->form_validation->set_rules('payscale','Pay Scale','trim|required|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA Grade','trim|required|xss_clean');
            $this->form_validation->set_rules('amount','Amount','trim|required|xss_clean|numeric');
            $this->form_validation->set_rules('Description','Description','trim|required|xss_clean');
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/add_ccaallowance');
                return;
            }//formvalidation
            else{
                $data = array(
                    'cca_payscaleid'     =>$_POST['payscale'],
                    'cca_gradeid'        =>$_POST['hragrade'],
                    'cca_workingtype'    =>$_POST['worktype'],
                    'cca_amount'         =>$_POST['amount'],
                    'cca_description'     =>$_POST['Description'],
                    'cca_creatorid'      =>$this->session->userdata('username'),
                    'cca_creatordate'    =>date('Y-m-d'),
                    'cca_modifierid'     =>$this->session->userdata('username'),
                    'cca_modifydate'     =>date('Y-m-d'),
                );
                
                $dupcheck = array(
                    'cca_payscaleid'     =>$_POST['payscale'],
                    'cca_gradeid'        =>$_POST['hragrade'],
                    'cca_workingtype'    =>$_POST['worktype'],
                    'cca_amount'         =>$_POST['amount'],
                ); 
                $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$_POST['payscale'])->sgm_name;
                $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$_POST['payscale'])->sgm_min;
                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$_POST['payscale'])->sgm_max;
                $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$_POST['payscale'])->sgm_gradepay;
                $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$_POST['hragrade'])->hgc_gradename;
                
                $hragradedup = $this->sismodel->isduplicatemore('ccaallowance_calculation', $dupcheck);
                if($hragradedup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this combination [ Working Type =".$_POST['worktype']
                            ." Payscale = ".$fullstr ." HRA Grade = ".$hragradename ." Amount = ".$_POST['amount'].' ]');
                    $this->load->view('setup3/add_ccaallowance');
                    return;
                }
                else{
                    $hragradeflag=$this->sismodel->insertrec('ccaallowance_calculation', $data);
                    if(!$hragradeflag)
                    {
                        $this->logger->write_logmessage("insert","Trying to add City Compensatory Allowance(CCA) amount ", " City Compensatory Allowance(CCA) amount is not added  HRA Grade= ".$hragradename." with payscale ".$fullstr);
                        $this->logger->write_dblogmessage("insert","Trying to add City Compensatory Allowance(CCA) amount ", " City Compensatory Allowance(CCA) amount is not added HRA Grade= ".$hragradename." with payscale ".$fullstr);
                        $this->session->set_flashdata('err_message','Error in adding City Compensatory Allowance(CCA) amount - '  , 'error');
                        redirect('setup3/add_ccaallowance');
                    }
                    else{
                        $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$_POST['hragrade'])->hgc_gradename;
                        $this->logger->write_logmessage("insert","Add City Compensatory Allowance(CCA) amount ", " City Compensatory Allowance(CCA) amount for HRA Grade = ".$hragradename." added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add  City Compensatory Allowance(CCA) amount ", " City Compensatory Allowance(CCA) amount for HRA Grade = ".$hragradename."added  successfully...");
                        $this->session->set_flashdata("success", "[ Working Type =".$_POST['worktype'] ." HRA Grade = ".$hragradename
                                ." Payscale = ".$fullstr ." Amount = ".$_POST['amount'].']'." record insert successfully...");
                        redirect("setup3/cca_allowance");
                    }
                    
                }//else dupcheck
                
            }//else
        }//isset button    
        $this->load->view('setup3/add_ccaallowance');
        
    }
    /*********************  closer Add City Compensatory Allowance(CCA) *********************************************************/ 
    
    /********************* Edit City Compensatory Allowance(CCA) form  *******************************************/
    public function edit_ccaallowance($id){
        $this->salgrade= $this->sismodel->get_list('salary_grade_master');
        $this->hragrade= $this->sismodel->get_listspfic2('hra_grade_city','hgc_id','hgc_gradename');
        $data['id'] = $id;
        $data['ccadata'] = $this->sismodel->get_listrow('ccaallowance_calculation','cca_id',$id)->row();
        if(isset($_POST['edit_cca'])) {
            
            //form validation
            $this->form_validation->set_rules('worktype','Working Type','trim|required|xss_clean');
            $this->form_validation->set_rules('payscale','Pay Scale','trim|required|xss_clean');
            $this->form_validation->set_rules('hragrade','HRA GRADE','trim|required|xss_clean');
            $this->form_validation->set_rules('amount','Amount','trim|required|xss_clean|numeric');
            $this->form_validation->set_rules('Description','Description','trim|required|xss_clean');
            
            if($this->form_validation->run() == FALSE){
             
                $this->load->view('setup3/edit_ccaallowance',$data);
                return;
            }//formvalidation
            else{
                $wtype = $this->input->post('worktype', TRUE);
                $payscale = $this->input->post('payscale', TRUE);
                $grade = $this->input->post('hragrade', TRUE);
                $amount = $this->input->post('amount', TRUE);
                $description = $this->input->post('Description', TRUE);
                
                $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$payscale)->sgm_name;
                $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$payscale)->sgm_min;
                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$payscale)->sgm_max;
                $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$payscale)->sgm_gradepay;
                $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id', $grade)->hgc_gradename;
                
                $logmessage = "";
                if($data['ccadata']->cca_workingtype != $wtype)
                    $logmessage = "Edit City Compensatory Allowance(CCA) Data " .$data['ccadata']->cca_workingtype. " changed by " .$wtype;
                if($data['ccadata']->cca_payscaleid != $payscale)
                    $logmessage = "Edit City Compensatory Allowance(CCA) " .$data['ccadata']->cca_payscaleid. " changed by " .$payscale;
                if($data['ccadata']->cca_gradeid != $grade)
                    $logmessage = " Edit City Compensatory Allowance(CCA) Data " .$data['ccadata']->cca_gradeid . " changed by " .$grade;
                if($data['ccadata']->cca_amount != $amount)
                    $logmessage = "Edit City Compensatory Allowance(CCA) Data " .$data['ccadata']->cca_amount . " changed by " .$pert;
                
                if($data['ccadata']->cca_description != $description)
                    $logmessage = "Edit City Compensatory Allowance(CCA) Data " .$data['ccadata']->cca_description . " changed by " .$description;
                
                $updata = array(
                    'cca_payscaleid'     =>$payscale,
                    'cca_gradeid'        =>$grade,
                    'cca_workingtype'    =>$wtype,
                    'cca_amount'         =>$amount,
                    'cca_description'    =>$description,
                    'cca_modifierid'     =>$this->session->userdata('username'),
                    'cca_modifydate'     =>date('Y-m-d'),
                );
                $dupcheck = array(
                    'cca_payscaleid'     =>$payscale,
                    'cca_gradeid'        =>$grade,
                    'cca_workingtype'    =>$wtype,
                    'cca_amount'         =>$amount,
                ); 
                $hragradedup = $this->sismodel->isduplicatemore('ccaallowance_calculation', $dupcheck);
                if($hragradedup == 1 ){
                    
                    $this->session->set_flashdata("err_message", "Record is already exist with this combination [ Working Type = ".$wtype 
                            ." Payscale = ".$fullstr ." HRA Grade = ".$hragradename ." Amount = ".$amount.' ]');
                    redirect('setup3/cca_allowance');
                    return;
                }
                else{
                    $editetflag=$this->sismodel->updaterec('ccaallowance_calculation', $updata, 'cca_id', $id);
                    if(!$editetflag){
                        $this->logger->write_logmessage("error","error in Edit City Compensatory Allowance(CCA) ", "Edit City Compensatory Allowance(CCA) details. $logmessage ");
                        $this->logger->write_dblogmessage("error","error in Edit City Compensatory Allowance(CCA)", "Edit City Compensatory Allowance(CCA) . $logmessage ");
                        $this->session->set_flashdata('err_message','Error in updating City Compensatory Allowance(CCA) - ' . $logmessage . '.', 'error');
                        $this->load->view('setup3/edit_ccaallowance', $data);
                    
                    }
                    else{
                        $this->logger->write_logmessage("update","Edit City Compensatory Allowance(CCA) by  ".$this->session->userdata('username') , "Edit  City Compensatory Allowance(CCA) details. $logmessage ");
                        $this->logger->write_dblogmessage("update","Edit City Compensatory Allowance(CCA) by  ".$this->session->userdata('username') , "Edit City Compensatory Allowance(CCA) details. $logmessage ");
                        $this->session->set_flashdata('success','Record updated successfully.'.'[ HRA Grade = '.$hragradename." Working Type =".$wtype. 
                            " Payscale = ".$fullstr."  Amount = ".$amount.']');
                        redirect('setup3/cca_allowance');
                    
                    }
                } //else dupcheck              
            }//else formvalidation
            
        }//if issetbutton press
        $this->load->view('setup3/edit_ccaallowance',$data);
    }//function
    /*********************** closer Edit City Compensatory Allowance(CCA) form ***********************/
    
    /**This function Delete  City Compensatory Allowance(CCA) records */
    public function delete_ccaallowance($id) {
        $this->roleid=$this->session->userdata('id_role');
       // $this->hgid=$id;
        /* Deleting academicprofile Record */
        $delflag=$this->sismodel->deleterow('ccaallowance_calculation','cca_id',$id);
        if (! delflag   )
        {   
            $this->logger->write_logmessage("delete", "Error in deleting City Compensatory Allowance(CCA) record" . " [Entry id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in deleting City Compensatory Allowance(CCA) record" . " [Entry id:" . $id . "]");
            $this->session->set_flashdata('Error in deleting deleting City Compensatory Allowance(CCA) record - ');
            redirect('setup3/cca_allowance');
        }
        else{
         
            $this->logger->write_logmessage("delete", " Deleted City Compensatory Allowance(CCA) record ". " [Entry id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted City Compensatory Allowance(CCA) Record  " . " [Entry id:" . $id . "]");
            $this->session->set_flashdata("success", 'Record  Deleted successfully ...' );
            redirect('setup3/cca_allowance');
        }
        $this->load->view('setup3/cca_allowance');
        
    }//closer
    /********************* closer Delete City Compensatory Allowance(CCA)*********************************************************/
    
    
    
    /*********************  Salary Processing *******************************************/
    public function salaryprocess(){
        $selectfield ="emp_id,emp_code,emp_name,emp_scid,emp_uocid,emp_dept_code,emp_schemeid,emp_desig_code,emp_email,emp_phone,emp_aadhaar_no";
        $whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'));
        $whorder = "emp_name asc,emp_dept_code asc,emp_desig_code asc";
        $data['emplist'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield, $whdata,$whorder);
        $month = $this->input->post('month', TRUE);
        $year = $this->input->post('year', TRUE);
       // echo "999==".$month."--------".$year;
        $cmonth= date('M');
        $cyear= date("Y"); 
        $data['selmonth']=$cmonth;
        $data['selyear']=$cyear;
        if(isset($_POST['salpro'])){
            $data['selmonth']=$month;
            $data['selyear']=$year;
            if(!empty($data['emplist'])){
                /**********************************income and deduction head *********************/
                $selectfield ="sh_id, sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
                $whorder = " sh_name asc";
                $whdata = array('sh_type' =>'I');
                $data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,$whorder);
                $whdata = array('sh_type' =>'D');
                $data['deduction'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,$whorder);
            }
             
        } //form
        $this->load->view('setup3/empSalary',$data);
        
    }
    /*********************  closer Salary Processing  *******************************************/
    
    /*********************   Salary Slip  form *********************************************************/
   /*  public function salaryslip(){
        $this->load->view('setup3/salaryslip');
        
    }*/                                 
    /********************* closer  Salary Slip  form *********************************************************/
    
    
    /************get allowed salary heads sccording to selected information od employee**********************/
   /* public function getAllowedsalaryhead($emptype){
        
        $selectfield ="shc_salheadid";
        $whorder = "shc_id asc";
	$whdata = array('shc_emptypeid' =>$emptype);
        $strhead= $this->sismodel->get_orderlistspficemore('salaryhead_configuration',$selectfield,$whdata,$whorder);
        
        $Asalhead=explode(", ",$strhead->shc_salheadid);
        echo "uoiu00===".$strhead."hsdfgh===".$Asalhead;
        
        return $Asalhead;
      
    }*/
        
    /*********************************************closer **********************************************/
    
    /************get allowed salary heads default value and formula **********************************/
    public function getDefaultheadval($emp_id,$shid){
        $this->salgrd=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$emp_id)->emp_salary_grade;
        $selectfield='shdv_defaultvalue';
        $whdata=array ('shdv_paybandid' => $this->salgrd,'shdv_salheadid'=>$shid);
        $this->defval=$this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$selectfield,$whdata,'');
        $fvalue=$this->defval[0]->shdv_defaultvalue;
      //  echo "999=====".$fvalue;
        //die;
        
        return $fvalue;
    }
    /*********************************************closer ******************************************************/
    
    /************get formula value **********************************************************************/
    public function getformulaval($shid,$empid,$pbid,$worktype){
        $formula1=$this->sismodel->get_listspfic1('salary_formula','sf_formula','sf_salhead_id',$shid);
        if(!empty($formula1)){
            $formula=$formula1->sf_formula;
            preg_match('/(.*)\((.*?)\)(.*)/', $formula, $match);
            //echo "in parenthesis inside: " . $match[2];
            //echo "before and after inside: " . $match[1] . $match[3] . "\n";
            $strfmla=explode("+",$match[2]);
            $strfmla2=explode("*",$match[3]);
            
            $sfield ="shdv_defaultvalue";
            if(!empty($strfmla[0])){
                $tok1id=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code',$strfmla[0])->sh_id;
                $wdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' =>$tok1id);
                $headval1= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfield,$wdata,'');  
                $headval1=$headval1[0]->shdv_defaultvalue;
            }
            else{
                $headval1=0;
            }
            if(!empty($strfmla[1])){
                $tok2id=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code',$strfmla[1])->sh_id;
                $wdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' => $tok2id);
                $headval2= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfield,$wdata,''); 
                $headval2=$headval2[0]->shdv_defaultvalue;
            }
            else{
                $headval2=0; 
            }
            $rawfor=$headval1 + $headval2 ;
            //$rawfor=$headval1[0]->shdv_defaultvalue + $headval2[0]->shdv_defaultvalue ;
            $finalval=$rawfor * $strfmla2[1];
                            
        }//emptyformulacheck  
        else{
            $ccaid=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code','CCA')->sh_id;
            $hraid=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code','HRA')->sh_id;
            if($shid == $ccaid || $shid == $hraid){
                if($shid == $ccaid){
                    $ccagrade=$this->sismodel->get_listspfic1('employee_master_support','ems_ccagrade','ems_empid',$empid);
                    if(!empty($ccagrade)){
                        $ccagrade= $ccagrade->ems_ccagrade;
                        $sfield="cca_amount";
                        $wdata = array('cca_payscaleid' =>$pbid,'cca_workingtype' =>$worktype,'cca_gradeid' =>$ccagrade);
                       // echo $pbid,$worktype,$ccagrade;
                        $headvalc= $this->sismodel->get_orderlistspficemore('ccaallowance_calculation',$sfield,$wdata,'');  
                        if(!empty($headvalc)){
                            $headvalcca=$headvalc[0]->cca_amount;
                            $finalval=$headvalcca;
                        }
                        else{
                            $finalval=0;
                        }
                    }
                    else{
                        $finalval=0;
                    }
                }
                if($shid == $hraid){
                    $hragrade=$this->sismodel->get_listspfic1('employee_master_support','ems_hragrade','ems_empid',$empid);
                    if(!empty($hragrade)){
                        $hragrade= $hragrade->ems_hragrade;
                        $sfield="hg_amount";
                        $wdata = array('hg_payscaleid' =>$pbid,'hg_workingtype' =>$worktype,'hg_gradeid' =>$hragrade);
                        $headvalh= $this->sismodel->get_orderlistspficemore('hra_grade',$sfield,$wdata,'');  
                        if(!empty($headvalh)){
                            $headvalhra=$headvalh[0]->hg_amount;
                            $finalval=$headvalhra; 
                        }
                        else{
                            $finalval=0;    
                        }
                    }
                    else{
                        $finalval=0;
                    }
                }
            }
            else{
                $finalval=0;
            }    
        }
        return $finalval;
        
    }
    /************closer formula value *******************************************************************/
    public function getInsertSalarydata($empid,$salheadid,$salamnt,$month,$year){
        $saldata = array(
            'sald_empid'       =>$empid,   
            'sald_sheadid'     =>$salheadid,
            'sald_shamount'    =>$salamnt, 
            'sald_month'       =>$month,
            'sald_year'        =>$year,
        );
        
        $dupcheck = array(
            'sald_empid'       =>$empid,   
            'sald_sheadid'     =>$salheadid,
            'sald_month'       =>$month,
            'sald_year'        =>$year,
        ); 
        
        $emidexits= $this->sismodel->isduplicatemore('salary_data',$dupcheck);
        if(!$emidexits){
            
            /* insert record in  salary data */
            $this->sismodel->insertrec('salary_data', $saldata);
            $this->logger->write_logmessage("insert", "data insert in salary_data table.");
            $this->logger->write_dblogmessage("insert", "data insert in salary_data table." );
            
        }
        else{
            
            /* update record in  salary data */
            $selectfield ="sald_id";
            $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$salheadid,'sald_month' =>$month,'sald_year' =>$year);
            $saldataid= $this->sismodel->get_orderlistspficemore('salary_data',$selectfield,$whdata,'');
            
            $this->sismodel->updaterec('salary_data', $saldata,'sald_id',$saldataid[0]->sald_id);
            $this->logger->write_logmessage("update", "data update in salary_data table.");
            $this->logger->write_dblogmessage("update", "data update in salary_data table." ); 
            
        }
        
    }
    /************closer insert Salarydata *******************************************************************/
    
    /************ InsertSalary ******************************************************************************/
    public function getInsertSalary($empid,$scid,$uoid,$deptid,$desigid,$sapost,$ddoid,$schemeid,$payscaleid,
                    $bankaccno,$worktype,$emptype,$group,$month,$year,$tincome,$tdeduction,$netsal,$status){
       
        $salinst=array(
            'sal_empid'             =>$empid,
            'sal_scid'              =>$scid,
            'sal_uoid'              =>$uoid,
            'sal_deptid'            =>$deptid,
            'sal_desigid'           =>$desigid,
            'sal_sapost'            =>$sapost,
            'sal_ddoid'             =>$ddoid,
            'sal_schemeid'          =>$schemeid,
            'sal_payscaleid'        =>$payscaleid,
            'sal_bankaccno'         =>$bankaccno,
            'sal_worktype'          =>$worktype,
            'sal_emptype'           =>$emptype,
            'sal_group'             =>$group,
            'sal_month'             =>$month,
            'sal_year'              =>$year,
            'sal_totalincome'       =>$tincome,
            'sal_totaldeduction'    =>$tdeduction,
            'sal_netsalary'         =>$netsal,
            'sal_status'            =>$status,
            'sal_paiddate'          =>date('y-m-d'),
            'sal_creationdate'      =>date('y-m-d'),
            'sal_creatorid'         =>$this->session->userdata('username'),
            'sal_updatedate'        =>date('y-m-d'),
            'sal_modifierid'        =>$this->session->userdata('username'),
        );
        
        $dupcheck = array(
            'sal_empid'             =>$empid,
            'sal_month'             =>$month,
            'sal_year'              =>$year,
        );  
        
        $emidexits= $this->sismodel->isduplicatemore('salary',$dupcheck);
        
        if(!$emidexits){
            /* insert record in  salary detail */
           $this->sismodel->insertrec('salary',  $salinst);
            $this->logger->write_logmessage("insert", "data insert in salary table.");
            $this->logger->write_dblogmessage("insert", "data insert in salary table." );
            
        }
        else{
            
            $salinst=array(
                'sal_empid'             =>$empid,
                'sal_scid'              =>$scid,
                'sal_uoid'              =>$uoid,
                'sal_deptid'            =>$deptid,
                'sal_desigid'           =>$desigid,
                'sal_sapost'            =>$sapost,
                'sal_ddoid'             =>$ddoid,
                'sal_schemeid'          =>$schemeid,
                'sal_payscaleid'        =>$payscaleid,
                'sal_bankaccno'         =>$bankaccno,
                'sal_worktype'          =>$worktype,
                'sal_emptype'           =>$emptype,
                'sal_group'             =>$group,
                'sal_month'             =>$month,
                'sal_year'              =>$year,
                'sal_totalincome'       =>$tincome,
                'sal_totaldeduction'    =>$tdeduction,
                'sal_netsalary'         =>$netsal,
                'sal_status'            =>$status,
                'sal_paiddate'          =>$paiddate,
                'sal_updatedate'        =>date('y-m-d'),
                'sal_modifierid'        =>$this->session->userdata('username'),
            );
            
            /* update record in  salary detail */
            
            $selectfield ="sal_id";
            $whdata = array('sal_empid' =>$empid,'sal_month' =>$month,'sal_year' =>$year);
            $saldataid= $this->sismodel->get_orderlistspficemore('salary',$selectfield,$whdata,'');
            $this->sismodel->updaterec('salary',  $salinst,'sal_id',$saldataid[0]->sal_id);
            $this->logger->write_logmessage("update", "data update in salary table.");
            $this->logger->write_dblogmessage("update", "data update in salary table." ); 
        }   
        
    }
    /************ closer InsertSalary ******************************************************************************/
    /*public function getallowedhead($emptype){
        $selectfield ="shc_id, shc_emptypeid, shc_salheadid";
        $whorder = "shc_id asc";
	$whdata = array('shc_emptypeid' =>$emptype);
        $data['incomes'] = $this->sismodel->get_orderlistspficemore('salaryhead_configuration',$selectfield,$whdata,$whorder);
               
    }*/
    /******************************copy previous month salary to next month**************************************************************************/
    public function copysalary(){
        $selectfield ="emp_id,emp_code,emp_name,emp_scid,emp_uocid,emp_dept_code,emp_schemeid,emp_desig_code,emp_post,emp_worktype,emp_type_code,"
                . "emp_email,emp_phone,emp_salary_grade,emp_bank_accno,emp_ddoid,emp_group,emp_aadhaar_no";
        $whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'));
      //  $whorder = "emp_name asc,emp_dept_code asc,emp_desig_code asc";
        $data['emplist'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,'');
        $cmonth= date('M');
        $cyear= date("Y"); 
        $data['selmonth']=$cmonth;
        $data['selyear']=$cyear;
        if(isset($_POST['salcopy'])){
            foreach($data['emplist'] as $record){
                
                $empexist=$this->sismodel->isduplicate('salary_data','sald_empid',$record->emp_id);
               
                if(!$empexist){
                    /*********************************Default Salary***************************************************/
                    $this->DefalutSalaryPro($record->emp_id,$cmonth,$cyear);
                }
                else{
                    
                    //select sald_id,sald_empid,sald_sheadid,sald_shamount,sald_month from salary_data
                    // where sald_empid=15 && sald_month=(SELECT sald_month from salary_data 
                    // where sald_id=(select max(sald_id) from salary_data where sald_empid=15));
                    
                   // echo "problem in else loop 1";
                    $selectfield ="sald_id";
                    $whdata = array('sald_empid'=>$record->emp_id);
                    $salmaxid= $this->sismodel->get_maxvalue('salary_data',$selectfield,$whdata); 
                    $salmonth=$this->sismodel->get_listspfic1('salary_data','sald_month','sald_id',$salmaxid[0]->sald_id)->sald_month;
                    $salyear=$this->sismodel->get_listspfic1('salary_data','sald_year','sald_id',$salmaxid[0]->sald_id)->sald_year;
                    //print_r("valuesof max====".$salmaxid[0]->sald_id.$salmonth);
                  
                    //  if(!empty($headvalues)){
                    $selectfield ="sald_sheadid,sald_shamount";
                    $whdata = array ('sald_empid'=>$record->emp_id,'sald_month'=>$salmonth,'sald_year'=>$salyear);
                    $headvalues = $this->sismodel->get_orderlistspficemore('salary_data',$selectfield,$whdata,'');
                    
                  //  die;
                    foreach($headvalues as $saldata){
                        /******************insert in salary data******************/
                        $this->getInsertSalarydata($record->emp_id,$saldata->sald_sheadid,$saldata->sald_shamount,$cmonth,$cyear);
                         
                    }
                    //die;
                    /**************************insert in salary *********************/
                    $selectfield1 ="sal_id";
                    $whdata1 = array('sal_empid'=>$record->emp_id);
                    $salmaxid1= $this->sismodel->get_maxvalue('salary',$selectfield1,$whdata1); 
                    $salmonth1=$this->sismodel->get_listspfic1('salary','sal_month','sal_id',$salmaxid1[0]->sal_id)->sal_month;
                    $salyear1=$this->sismodel->get_listspfic1('salary','sal_year','sal_id',$salmaxid1[0]->sal_id)->sal_year;
                    
                    $selectfield1 ="sal_scid,sal_uoid,sal_deptid,sal_desigid,sal_sapost,sal_ddoid,sal_schemeid,sal_payscaleid,sal_bankaccno,
                        sal_worktype,sal_emptype,sal_group,sal_totalincome,sal_totaldeduction,sal_netsalary";
                    $whdata1 = array('sal_empid' =>$record->emp_id,'sal_month'=>$salmonth1,'sal_year'=>$salyear1);
                    $headvalues1= $this->sismodel->get_orderlistspficemore('salary',$selectfield1,$whdata1,''); 
                   // print_r("valuesof max=yyyy===".$headvalues1->sal_totalincome."\n"."===am---".$headvalues1->sal_totaldeduction); 
                   // die;
                    foreach($headvalues1 as $saldata2){
                        $this->getInsertSalary($record->emp_id,$saldata2->sal_scid,$saldata2->sal_uoid,$saldata2->sal_deptid,$saldata2->sal_desigid,
                        $saldata2->sal_sapost,$saldata2->sal_ddoid,$saldata2->sal_schemeid,$saldata2->sal_payscaleid,$saldata2->sal_bankaccno,$saldata2->sal_worktype,
                        $saldata2->sal_emptype,$saldata2->sal_group,$cmonth,$cyear,$saldata2->sal_totalincome,$saldata2->sal_totaldeduction,$saldata2->sal_netsalary,'process');
                    }
                }    
            
            }//emplistloop
            $this->logger->write_logmessage("insert", " Salary data copy "."Salary data copy");
            $this->logger->write_dblogmessage("insert"," Salary data copy "."Salary data copy");
            $this->session->set_flashdata("success", 'Salary data copy successfully ...' );
                 
        }
        $this->load->view('setup3/empSalary',$data);
    
    }
    public function DefalutSalaryPro($empid,$cmonth,$cyear){
        $sumincome=0;$sumdeduct=0;
        $selectfield ="sh_id,sh_code, sh_name, sh_tnt, sh_type, sh_calc_type";
       // $whorder = " sh_name asc";
        $whdata = array('sh_type' =>'I');
        $data['incomes'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
        $whdata = array('sh_type' =>'D');
        $data['deduction'] = $this->sismodel->get_orderlistspficemore('salary_head',$selectfield,$whdata,'');
        $emptype=$this->sismodel->get_listspfic1('employee_master','emp_type_code','emp_id',$empid)->emp_type_code;
        $this->emptypeid=$this->sismodel->get_listspfic1('employee_type','empt_id','empt_name',$emptype)->empt_id;
        $strarray=$this->sismodel->get_listspfic1('salaryhead_configuration','shc_salheadid','shc_emptypeid',$this->emptypeid)->shc_salheadid;
        $allowedhead=explode(", ",$strarray);
        $wtype=$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype;
        $payscaleid=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$empid)->emp_salary_grade;
        foreach($data['incomes'] as $record1){
            if($record1->sh_tnt == $wtype || $record1->sh_tnt == NULL){
                if(in_array($record1->sh_id,$allowedhead)){
                    if($record1->sh_calc_type == 'Y'){
                        $this->dheadval=$this->getformulaval($record1->sh_id,$empid,$payscaleid,$wtype);
                    }
                    else{
                        $this->dheadval=$this->getDefaultheadval($empid,$record1->sh_id);
                    }
                }
                else{
                    $this->dheadval=0;   
                }
            
            $this->getInsertSalarydata($empid,$record1->sh_id,$this->dheadval,$cmonth,$cyear);
            $sumincome+=$this->dheadval;
            }
        }
        foreach($data['deduction'] as $record2){
            if($record2->sh_tnt == $wtype || $record2->sh_tnt == NULL){
                if(in_array($record2->sh_id,$allowedhead)){
                    if($record2->sh_calc_type == 'Y'){
                        $this->dheadval=$this->getformulaval($record2->sh_id,$empid,$payscaleid,$wtype);
                    }
                    else{
                    
                        $this->dheadval=$this->getDefaultheadval($empid,$record2->sh_id);
                    }
                } 
                else{
                    $this->dheadval=0; 
                }
            //}    
            $this->getInsertSalarydata($empid,$record2->sh_id,$this->dheadval,$cmonth,$cyear);
            $sumdeduct+=$this->dheadval;
            }
            
        }
        $this->SalaryPolicies($empid,$cmonth,$cyear);
        $netsalary=$sumincome - $sumdeduct;
        
        $scid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid;
        $uoccid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
        $deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
        $desigid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
        $sopost=$this->sismodel->get_listspfic1('employee_master','emp_post','emp_id',$empid)->emp_post;
        $ddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
        $schmid=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$empid)->emp_schemeid;
        $bankaccno=$this->sismodel->get_listspfic1('employee_master','emp_bank_accno','emp_id',$empid)->emp_bank_accno;
        $group=$this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$empid)->emp_group;
        
        /*************************************insert in salary ********************************************************/
        $this->getInsertSalary($empid,$scid,$uoccid,$deptid,$desigid,$sopost,$ddoid,$schmid,$payscaleid,$bankaccno,$wtype,
        $emptype,$group,$cmonth,$cyear,$sumincome,$sumdeduct,$netsalary,'process');
        
    }
    public function SalaryPolicies($empid,$cmonth,$cyear){
        $licprdpli = array(
            "LIC1" => "LIC1",
            "LIC2" => "LIC2",
            "LIC3" => "LIC3",
            "LIC4" => "LIC4",
            "LIC5" => "LIC5",
            "PRD1" => "PRD1",
            "PRD2" => "PRD2",
            "PRD3" => "PRD3",
            "PLI1" => "PLI1",
            "PLI2" => "PLI2",
        );
        foreach ($licprdpli as $lpdpi) {
            $this->getInsertSalarydata($empid,$lpdpi,0,$cmonth,$cyear);
        }
    }
}//class    
