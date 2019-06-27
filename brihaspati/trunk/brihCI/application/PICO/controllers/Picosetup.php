<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Picosetup
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Picosetup extends CI_Controller
{
    function __construct() {
        parent::__construct();
        $this->load->model('login_model'); 
  		$this->load->model('common_model'); 
        $this->load->model('PICO_model');   //changed to PICO insted of
        $this->load->model('SIS_model'); 
       	$this->load->model('dependrop_model','depmodel');
        $this->load->model('university_model','unimodel');
        $this->db4=$this->load->database('pico', TRUE);
        if(empty($this->session->userdata('id_user'))) {
        $this->session->set_flashdata('flash_data', 'You don\'t have access!');
        
		redirect('welcome');
        }
    }

    /*** Type of Store ****************************************************************************************************************/
    /* This function redirect to View page of Type of Store.. onClick -> Add Store
    @display type*/
    public function opentypeofstore(){

        $this->load->view('setup/typeofstore');


    }

    /* This function displays the table in type of store link*/
    public function displaystore() {
        $this->result = $this->PICO_model->get_list('material_type');
        $this->logger->write_logmessage("view"," View store setting", "Store setting details...");
        $this->logger->write_dblogmessage("view"," View store setting", "Store setting details...");
        $this->load->view('setup/displaystore',$this->result);
       }


     /** This function for add store
     * @return type
     */

    public function insertstore()
        {
        		 if(isset($_POST['submitstore'])) {
                 $this->form_validation->set_rules('store','Type of Store','required');
                 $this->form_validation->set_rules('mt_desc','Material Desc','trim|xss_clean|required|alpha_numeric_spaces|required');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 
                //$tbdata=$_POST['store'];
                 $data = array(   
                'mt_name'=>$_POST['store'],
                'mt_desc'=>$_POST['mt_desc'],
                 );
                
                $duplicate= $this->PICO_model->isduplicatemore('material_type',$data);
                if(!$duplicate){
                    $rflag=$this->PICO_model->insertrec('material_type', $data);
                    if (!$rflag){

                    $this->logger->write_logmessage("insert","Trying to add store type", "Store is not added ".$store);
                    $this->logger->write_dblogmessage("insert","Trying to add store description", "Description is not added ".$mt_desc);
                    $this->session->set_flashdata('err_message','Error in adding store setting - '  , 'error');
                    redirect('picosetup/opentypeofstore');

                    }
                    else{

                    $this->logger->write_logmessage("insert","Add Store Setting", "Store".$_POST['store']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add role Setting", "Store Description ".$_POST['mt_desc']."added  successfully...");
                    $this->session->set_flashdata("success", "Store added successfully...");
                    redirect("picosetup/displaystore");
                    } 
                }
                else{
                    $this->logger->write_logmessage("insert","Duplicate store exist", "Store is not added ".$store);
                    $this->logger->write_dblogmessage("insert","Duplicate description exist", "Description is not added ".$mt_desc);
                    $this->session->set_flashdata('err_message','Store name already exist...'  , 'error');
                    redirect("picosetup/displaystore");
                }
                
            }
            
        }
          
        $this->load->view('setup/typeofstore');
    }


    /** This function check for duplicate store
     * @return type
    */
    public function isStoreExist($mt_name) {

        $is_exist = $this->PICO_model->isduplicate('material_type','mt_name',$mt_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isStoreExist', 'This store already exist.');
            return false;
        }
        else {
            return true;
        }
    }


    /*this function opens view/setup/editstore*/
    public function openeditstore($id){
        
        $data['id']=$id;
        $this->load->view("setup/editstore",$data);

    }
    
    /* this function is used for delete store record */
    public function deletestore($id) { 

        $mt_data=$this->PICO_model->get_listrow('material_type','mt_id', $id);
        if ($mt_data->num_rows() < 1)
        {
            redirect('setup/displaystore');
        }
            $fmdflag=$this->PICO_model->deleterow('material_type','mt_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in deleting store " ."[mt_id:" . $id . "]");
                    $this->logger->write_dbmessage("error", "Error  in deleting store "." [mt_id:" . $id . "]");
                    $this->session->set_flashdata('err_message', 'Error in Deleting store - ', 'error');
                    redirect('picosetup/displaystore');
                //return;
              }
              else{
                    $this->logger->write_logmessage("delete", "Deleted   store record " . "[mt_id:" . $id . "] deleted successfully.. " );
                    $this->logger->write_dblogmessage("delete", "Deleted store record" ." [mt_id:" . $id . "] deleted successfully.. " );
                    $this->session->set_flashdata("success", "Specific Store deleted successfully..." );
                    redirect('picosetup/displaystore');
            }
          $this->load->view('setup/displaystore',$data);
    }

    
    /***Financial Power form ***************************************************************************************************************
    **
    */

    public function openfinancialpower(){
        $data['authresult'] = $this->login_model->get_listspfic2('authorities','id','name');
        $this->load->view('setup/financialpowerform',$data);
    }

     /*Display Financial Power Details */

    public function financialpowerdetails(){
        $this->result = $this->PICO_model->get_list('financial_power');
        $this->logger->write_logmessage("view"," View Financial Authority setting", "Financial Authority setting details...");
        $this->logger->write_dblogmessage("view"," View Financial Authority setting", "Financial Authority setting details...");
        $this->load->view('setup/displayfinancialpower',$this->result);
    }

    /** This function insert data of Financial power form */
    public function insertfpform()
        {
            if(isset($_POST['fp_power'])) {
            $this->form_validation->set_rules('fp_typeofpurch','Type of Purchase','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('fp_subtypepurch','Sub Purchase Type','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('authority','Authority','required|alpha_numeric_spaces|callback_isAuthorityExist');
            $this->form_validation->set_rules('fp_limit','Limit','trim|required');
            $this->form_validation->set_rules('fp_desc','Item Description','trim|xss_clean|required|alpha_numeric_spaces');
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 
                
                 $data = array(   
                'fp_typeofpurch'=>$_POST['fp_typeofpurch'],
                'fp_subtypepurch'=>$_POST['fp_subtypepurch'],
                'fp_authority'=>$_POST['authority'],
                'fp_limit'=>$_POST['fp_limit'],
                'fp_desc'=>$_POST['fp_desc'],
                 );


                $duplicate= $this->PICO_model->isduplicatemore('financial_power',$data);
                if(!$duplicate){
                    $rflag=$this->PICO_model->insertrec('financial_power', $data);
                    if (!$rflag){

                    $this->logger->write_logmessage("insert","Trying to add Financial Authority type", "Financial Authority is not added ".$fp_authority);
                    $this->logger->write_dblogmessage("insert","Trying to add Financial Authority description", "Financial Authority is not added ".$fp_desc);
                    $this->session->set_flashdata('err_message','Error in adding Financial Authority setting - '  , 'error');
                    redirect('picosetup/openfinancialpower');

                    }
                    else{

                    $this->logger->write_logmessage("insert","Add Financial Authority Setting", "Financial Authority".$_POST['authority']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Financial Authority Setting", "Financial Authority Description ".$_POST['fp_desc']."added  successfully...");
                    $this->session->set_flashdata("success", "Financial Authority added successfully...");
                    redirect("picosetup/financialpowerdetails");
                    } 
                }
                else{
                    $this->logger->write_logmessage("insert","Duplicate Financial Authority exist","Financial Authority is not added ".$fp_authority);
                    $this->logger->write_dblogmessage("insert","Duplicate description exist", "Description is not added ".$fp_desc);
                    $this->session->set_flashdata('err_message','This authority already exist...'  , 'error');
                    redirect("picosetup/openfinancialpower");
                }
                
            }
            
        }
          
        $this->load->view('setup/financialpowerform');
    }

    /** This function check for duplicate authority
     * @return type
    */
    public function isAuthorityExist($fp_authority) {

        $is_exist = $this->PICO_model->isduplicate('financial_power','fp_authority',$fp_authority);
        if ($is_exist)
        {
            $this->form_validation->set_message('isAuthorityExist', 'This Authority already exist.');

            return false;
        }
        else {
            return true;
        }
    }

     /* this function is used to delete Authority record */
    public function deleteauthority($id) { 

        $mt_data=$this->PICO_model->get_listrow('financial_power','fp_id', $id);
        if ($mt_data->num_rows() < 1)
        {
            redirect('setup/financialpowerdetails');
        }
            $fmdflag=$this->PICO_model->deleterow('financial_power','fp_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in deleting Financial Authority " ."[fp_id:" . $id . "]");
                $this->logger->write_dbmessage("error", "Error  in deleting Financial Authority "." [fp_id:" . $id . "]");
                $this->session->set_flashdata('err_message', 'Error in Deleting Financial Authority - ', 'error');
                redirect('picosetup/financialpowerdetails');
                //return;
              }
              else{
                $this->logger->write_logmessage("delete", "Deleted   Financial Authority record " . "[fp_id:" . $id . "] deleted successfully.. ");
                $this->logger->write_dblogmessage("delete", "Deleted Financial Authority record" ." [fp_id:" . $id . "] deleted successfully.. " );
                $this->session->set_flashdata("success", "Specific Financial Authority deleted successfully..." );
                redirect('picosetup/financialpowerdetails');
            }
          $this->load->view('setup/financialpowerdetails',$data);
    }

    /*This function modify the Finanacial Power*/
    public function editfinancialpower($id) {

        $this->db4->from('financial_power')->where('fp_id', $id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
            redirect('setup/financialpowerform');
        }
        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data['fp_typeofpurch'] = array(
                'name' => 'fp_typeofpurch',
                'id' => 'fp_typeofpurch',
                'size' => '40',
                'value' => $editeset_data->fp_typeofpurch,

                );
                $data['fp_subtypepurch'] = array(
                'name' => 'fp_subtypepurch',
                'id' => 'fp_subtypepurch',
                'size' => '40',
                'value' => $editeset_data->fp_subtypepurch,

                );
                $data['authority'] = array(
                'name' => 'fp_authority',
                'id' => 'fp_authority',
                'size' => '40',
                'value' => $editeset_data->fp_authority,

                );
                $data['fp_limit'] = array(
                'name' => 'fp_limit',
                'id' => 'fp_limit',
                'size' => '20',
                'value' => $editeset_data->fp_limit,

                );
                $data['fp_desc'] = array(
                'name' => 'fp_desc',
                'id' => 'fp_desc',
                'size' => '40',
                'value' => $editeset_data->fp_desc,

        );
        $data['id'] = $id;
        /*Form Validation*/
        $this->form_validation->set_rules('fp_typeofpurch','Type of Purchase','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('fp_subtypepurch','Sub Purchase Type','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('fp_authority','Authority','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('fp_limit','Limit','trim|required');
        $this->form_validation->set_rules('fp_desc','Item Description','trim|xss_clean|required|alpha_numeric_spaces');

        /* Re-populating form */
        if ($_POST)
        {
            $data['fp_typeofpurch']['value'] = $this->input->post('fp_typeofpurch', TRUE);
            $data['fp_subtypepurch']['value'] = $this->input->post('fp_subtypepurch', TRUE);
            $data['fp_authority']['value'] = $this->input->post('fp_authority', TRUE);
            $data['fp_limit']['value'] = $this->input->post('fp_limit', TRUE);
            $data['fp_desc']['value'] = $this->input->post('fp_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editfinancialpower', $data);
            return;
        }
        else{

            $data_typeofpurch = $this->input->post('fp_typeofpurch', TRUE);
            $data_subtypepurch = $this->input->post('fp_subtypepurch', TRUE);
            $data_authority = $this->input->post('fp_authority', TRUE);
            $data_limit = $this->input->post('fp_limit', TRUE);
            $data_desc = $this->input->post('fp_desc', TRUE);
            $data_eid = $id;
            $logmessage = "";
            if($editeset_data->fp_typeofpurch != $data_typeofpurch)
                $logmessage = "Type of Purchase " .$editeset_data->fp_subtypepurch. " changed by " .$data_subtypepurch;

            if($editeset_data->fp_subtypepurch!= $data_subtypepurch)
                $logmessage = "Sub Purchase Type" .$editeset_data->fp_subtypepurch. " changed by " .$data_subtypepurch;

            if($editeset_data->fp_authority != $data_authority)
                $logmessage = "Authority " .$editeset_data->fp_authority. " changed by " .$data_authority;

            if($editeset_data->fp_limit != $data_limit)
                $logmessage = "Financial Limit " .$editeset_data->fp_limit. " changed by " .$data_limit;

            if($editeset_data->role_desc != $data_desc)
                $logmessage = "Add Role " .$editeset_data->role_desc. " changed by " .$data_desc;

            $update_data = array(
               'fp_typeofpurch' => $data_typeofpurch,
               'fp_subtypepurch'=> $data_subtypepurch,
               'fp_authority' => $data_authority,
               'fp_limit' => $data_limit,
               'fp_desc' => $data_desc,
            );

            $duplicate= $this->PICO_model->isduplicatemore('financial_power',$update_data);
            if(!$duplicate){
                $roledflag=$this->PICO_model->updaterec('financial_power', $update_data,' fp_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","Edit Financial Power Setting error", "Edit Financial Power Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit Financial Power Setting error", "Edit Financial Power Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating Financial Power - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editfinancialpower', $data);
                }
                else{
                $this->logger->write_logmessage("update","Edit Financial Power Setting", "Edit Financial Power Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit Financial Power Setting", "Edit Financial Power Setting details. $logmessage ");
                $this->session->set_flashdata('success','Financial Power details updated successfully..');
                redirect('picosetup/financialpowerdetails');
                }
            }
            else{
                $this->logger->write_logmessage("delete", "Deleted   Financial Power record " . "[fp_id:" . $id . "] deleted successfully.. ");
                $this->logger->write_dblogmessage("delete", "Deleted Financial Power record" ." [fp_id:" . $id . "] deleted successfully.. " );
                $this->session->set_flashdata("success", "Specific Financial Power deleted successfully..." );
                redirect('picosetup/financialpowerdetails');

            }
            
        }

    }

    /**** Purchase Type **************************************************************************************************************/

    public function purchasetype(){

        $this->load->view('setup/purchasetypeform');


    }

    /* This funtion fetch list of sub purchase type****/

    public function getsubpurchase(){
        $ptype = $this->input->post('purchase');
       
     if ($ptype=='Minor'){
            $select_box ='';
            $select_box.='<option selected disabled value=>-------Select Purchase Category--------';
            $select_box.='<option value=Without_Quotation> Without Quotation';
            $select_box.='<option value=Without_Quotation_Purchase_Committee> Without Quotation Purchase Committee';
            $select_box.='<option value=With_Quotation_Purchase_Committee> With Quotation Purchase Committee';
            
        }
     else if($ptype=='Medium'){
            $select_box ='';
            $select_box.='<option selected disabled value= >-------Select Purchase Category--------';
            $select_box.='<option value=Memberlimit_Purchase_Committee_With_Quotation> Memberlimit Purchase Committee With Quotation ';
            $select_box.='<option value=Purchase_Committee(CA_approved)>Purchase Committee(CA approved)';
           
            
        }
     else{
            $select_box ='';
            $select_box.='<option selected disabled value= >-------Select Purchase Category--------';
            $select_box.='<option value=Purchase_Committee_Approved_by_CA> Purchase Committee (CA Approved)';
            $select_box.='<option value=Technical_Evaluation_Committee> Technical Evaluation Committee (CA Approved)';       
        }

       echo json_encode($select_box);
    }


     /** This function submit purchase type form
     * @return type
     */                 

    public function addpurchasetype()
    {
        if(isset($_POST['submit_purch'])) {
                 
                 $this->form_validation->set_rules('purchtype','Purchase Type','required');
                 $this->form_validation->set_rules('subpurchtype','Sub Purchase Type','required|callback_isPurchasetypeExist');
                 $this->form_validation->set_rules('amt_above','Amount Above','trim|alpha_numeric_spaces|xss_clean|required');
                 $this->form_validation->set_rules('amt_below','Amount Above','trim|alpha_numeric_spaces|xss_clean|required');
                 $this->form_validation->set_rules('desc','Designation Description','trim|xss_clean');
            if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';

                $ptype = $this->input->post("purchtype");
                $subtype = $this->input->post("subpurchtype");

                $datacheck = array('purch_type'=>$_POST['purchtype'],'sub_purch_type'=>$_POST['subpurchtype'], 'amt_above'=>$_POST['amt_above'],'amt_below'=>$_POST['amt_below'], 'pt_desc'=>$_POST['desc'] );

                $data =array(
                    'purch_type'=>$_POST['purchtype'],
                    'sub_purch_type'=>$_POST['subpurchtype'], 
                    'amt_above'=>$_POST['amt_above'],
                    'amt_below'=>$_POST['amt_below'], 
                    'pt_desc'=>$_POST['desc'] 
                );


                $duplicate = $this->PICO_model->isduplicatemore('purchase_type',$datacheck);

                if($duplicate==1){
                    $this->logger->write_logmessage("insert","Record is already exist with this combination","Record is not added ".$ptype);
                      $this->logger->write_dblogmessage("insert","Record is already exist with this combination","Record is not added ".$ptype);
                    
                    $this->logger->write_logmessage("insert","Record is already exist with this combination","Record is not added ".$subtype);
                    $this->logger->write_dblogmessage("insert","Record is already exist with this combination","Record is not added ".$subtype);

                    $this->session->set_flashdata('err_message','Record is already exist with this combination...'  , 'error');
                    redirect('picosetup/purchasetype');
                    return;

                       }
                   else{
                         $rflag=$this->PICO_model->insertrec('purchase_type', $data);
                    if (!$rflag)
                        {
                        $this->logger->write_logmessage("insert","Trying to designation", "designation is not added ".$_POST['purchtype']);
                        $this->logger->write_dblogmessage("insert","Trying to designation", "designation is not added ".$_POST['purchtype']);
                        $this->session->set_flashdata('err_message','Error in adding Purchase type setting - '  , 'error');
                        redirect('picosetup/purchasetype');
                        }

                        else{
                        $this->logger->write_logmessage("insert","Add designation Setting", "Designation".$_POST['purch_type']." added  successfully...");
                        $this->logger->write_dblogmessage("insert","Add designation Setting", "Designation ".$_POST['purch_type']."added  successfully...");
                                $this->session->set_flashdata("success", "Purchase setting added successfully...");
                                redirect("picosetup/openpurchasetypedetails");
                        }
                  }//close else vallidation
              }
        }//

       $this->load->view('setup/purchasetypeform');
    }

     /** This function check for duplicate purchase type entry
     * @return type
    */
    public function isPurchasetypeExist($subpurchtype) {

        $is_exist = $this->PICO_model->isduplicate('purchase_type','sub_purch_type',$subpurchtype);
        if ($is_exist)
        {
            $this->form_validation->set_message('isPurchasetypeExist', 'This Purchase Category already exist.');

            return false;
        }
        else {
            return true;
        }
    }

    /** This function check for display purchase type entries
     * @return type
    */
      public function purchasetypedetails(){
        $data['result'] = $this->PICO_model->get_list('purchase_type');
        $this->logger->write_logmessage("view"," View Financial Authority setting", "Financial Authority setting details...");
        $this->logger->write_dblogmessage("view"," View Financial Authority setting", "Financial Authority setting details...");
        $this->load->view('setup/displaypurchasetypedetails',$data);
    }

    /** opens Purchase type detailed table */
    public function openpurchasetypedetails(){
        $data['result']=$this->result = $this->PICO_model->get_list('purchase_type');
        $this->logger->write_logmessage("view"," View Purchase Type setting", "View Purchase Type setting details...");
        $this->logger->write_dblogmessage("view"," View View Purchase Type setting", "View Purchase Type setting details...");
        $this->load->view('setup/displaypurchasetypedetails',$data);

    }

     /* this function is used to delete Purchase Type record */
    public function deletepurchasetype($id) { 

        $mt_data=$this->PICO_model->get_listrow('purchase_type','pt_id', $id);
        if ($mt_data->num_rows() < 1)
        {
            redirect("picosetup/purchasetype");
        }
            $fmdflag=$this->PICO_model->deleterow('purchase_type','pt_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in deleting Financial Authority " ."[pt_id:" . $id . "]");
                $this->logger->write_dbmessage("error", "Error  in deleting Financial Authority "." [pt_id:" . $id . "]");
                $this->session->set_flashdata('err_message', 'Error in Deleting Financial Authority - ', 'error');
                redirect('picosetup/openpurchasetypedetails');
                //return;
              }
              else{
                $this->logger->write_logmessage("delete", "Deleted   Financial Authority record " . "[pt_id:" . $id . "] deleted successfully.. ");
                $this->logger->write_dblogmessage("delete", "Deleted Financial Authority record" ." [pt_id:" . $id . "] deleted successfully.. " );
                $this->session->set_flashdata("success", "Specific Financial Authority deleted successfully..." );
                redirect('picosetup/openpurchasetypedetails');
            }
          $this->load->view('setup/displaypurchasetypedetails',$data);
    }

    /****************************************** bankdetails Module ********************************************/

 public function addbank(){
    
    $this->orgcode=$this->common_model->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
    $this->campus=$this->common_model->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);

        if(isset($_POST['addbank'])) {
            //$this->form_validation->set_rules('bank_name','Org Code','trim|xss_clean|required|alpha_numeric_spaces|callback_isBankdetailsExist');
    $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|xss_clean');
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean');

            $this->form_validation->set_rules('bank_name','Bank Name','trim|xss_clean|required');
            $this->form_validation->set_rules('bank_address','Bank Address','trim|xss_clean|required');
            $this->form_validation->set_rules('branch_name','Branch Name','trim|xss_clean|required');
            $this->form_validation->set_rules('account_number','Account Number','trim|xss_clean|numeric|required');
            $this->form_validation->set_rules('account_name','Account Name','trim|xss_clean|required');
            $this->form_validation->set_rules('account_type','Account Type','trim|xss_clean|alpha_numeric_spaces|required');
            $this->form_validation->set_rules('ifsc_code','Ifsc Code','trim|xss_clean|alpha_numeric_spaces|required');
            $this->form_validation->set_rules('pan_number','Pan Number','trim|xss_clean|alpha_numeric_spaces|required');
            $this->form_validation->set_rules('tan_number','Tan Number','trim|xss_clean|alpha_numeric_spaces|required');
            $this->form_validation->set_rules('gst_number','Gst Number','trim|xss_clean|alpha_numeric_spaces|required');
            $this->form_validation->set_rules('aadhar_number','Aadhar Number','trim|xss_clean|numeric');
            $this->form_validation->set_rules('org_id','Org Id','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('remark','Remark','trim|xss_clean');

            if($this->form_validation->run()==TRUE){
        $orgid=$_POST['org_id'];
        if($orgid == ""){
            $orgid =1;
        }
            
            $data = array( 
               // 'org_code'=>ucwords(strtolower($_POST['org_code'])),
        'campusid'      =>$_POST['campus'],
                'ucoid'         =>$_POST['uocontrol'],
                'deptid'        =>$_POST['department'],
                'schemeid'      =>$_POST['schemecode'],
                'bank_name' =>$_POST['bank_name'],
                'bank_address'  =>$_POST['bank_address'],
                'branch_name'   =>$_POST['branch_name'],
                'account_number'=>$_POST['account_number'],
                'account_name'  =>$_POST['account_name'],
                'account_type'  =>$_POST['account_type'],
                'ifsc_code' =>strtoupper($_POST['ifsc_code']),
                'pan_number'    =>strtoupper($_POST['pan_number']),
                'tan_number'    =>strtoupper($_POST['tan_number']),
                'gst_number'    =>strtoupper($_POST['gst_number']),
                'aadhar_number' =>$_POST['aadhar_number'],
                'org_id'    =>$orgid,
                'remark'    =>$_POST['remark'] 
           );
        
           $bpflag=$this->SIS_model->insertrec('bankprofile', $data) ;
           if(!$bpflag)

           {
                $this->logger->write_logmessage("insert"," Error in adding bankdetails ", " BankDetails data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding bankdetails  ", " BankDetails data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding bankdetails - ' . $_POST['bnkname'] , 'error');
                $this->load->view('setup/addbank');
           }
         else{
                $this->logger->write_logmessage("insert"," add bankdetails ", "bankdetails record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add bankdetails ", "bankdetails record added successfully..." );
                $this->session->set_flashdata("success", "bankdetails added successfully...");
                redirect("setup/displaybankdetails", "refresh");
              }
           }

        }        
      $this->load->view('setup/addbank');
   }


/** This function check for duplicate bankdetails
     * @return type
    */

    public function isbankdetailsExist($bank_name) { 

         
        $is_exist = $this->SIS_model->isduplicate('bankdetails','bd_name',$bd_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isBankdetailsExist', 'Bankdetails is already exist.');
            return false;
        }
        else {
            return true;
        }
    
      }
     

  /* Display Bankdetails record */ 

 public function displaybankdetails(){

        $this->result = $this->SIS_model->get_list('bankprofile');
        $this->logger->write_logmessage("view"," View ", "Bankdetails record display successfully..." );
        $this->logger->write_dblogmessage("view"," View Bankdetails", "Bankdetails record display successfully..." );
        $this->load->view('setup/bankdetails',$this->result);
    }

 /**This function is used for update bankdetails details
     * @param type $bank_id
     * @return type
    */ 
    public function editbankdetails($id) {
        $bank_data_q=$this->SIS_model->get_listrow('bankprofile','id', $id);
        if ($bank_data_q->num_rows() < 1)
        {
        redirect('setup/editbankdetails');
        }
        $bankprofile_data = $bank_data_q->row();

        /* Form fields */
         
            if ($bankprofile_data->campusid != 0) {      
                    $sc=$this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $bankprofile_data->campusid)->sc_name.
                    " "."(".$this->common_model->get_listspfic1('study_center', 'sc_code', 'sc_id', $bankprofile_data->campusid)->sc_code.")";
            }else{$sc="";}
        if ($bankprofile_data->ucoid != 0) {
            $uo=$this->login_model->get_listspfic1('authorities', 'name', 'id', $bankprofile_data->ucoid)->name; 
        }else{ $uo='';}

        if ($bankprofile_data->deptid != 0) {
            $dept=$this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $bankprofile_data->deptid)->dept_name;
        }else{$dept='';}
                if ( $bankprofile_data->schemeid != 0) {
            $schme=$this->SIS_model->get_listspfic1('scheme_department','sd_name','sd_id',$bankprofile_data->schemeid)->sd_name.
            " "."(".$this->SIS_model->get_listspfic1('scheme_department','sd_code','sd_id',$bankprofile_data->schemeid)->sd_code.")";
        }else{ $schme='';}
 
        $data['campus_name'] = array(
            'name' => 'campus_name',
            'id' => 'campus_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $sc,
            'readonly' => 'readonly'
        );
        $data['UCO'] = array(
            'name' => 'UCO',
            'id' => 'UCO',
            'maxlength' => '50',
            'size' => '40',
            'value' => $uo,
            'readonly' => 'readonly'
        );
        $data['dept_name'] = array(
            'name' => 'dept_name',
            'id' => 'dept_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $dept,
            'readonly' => 'readonly'
        );
        $data['scheme_name'] = array(
            'name' => 'scheme_name',
            'id' => 'scheme_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $schme,
            'readonly' => 'readonly'
        );
        $data['bank_name'] = array(
            'name' => 'bank_name',
            'id' => 'bank_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $bankprofile_data->bank_name,
        );
        $data['bank_address'] = array(
           'name' => 'bank_address',
           'id' => 'bank_address',
           'maxlength' => '50',
           'size' => '40',
           'value' => $bankprofile_data->bank_address,

        );

        $data['branch_name'] = array(
           'name' => 'branch_name',
           'id' => 'branch_name',
           'maxlength' => '50',
           'size' => '40',
           'value' => $bankprofile_data->branch_name,

        );

        $data['account_number'] = array(
           'name' => 'account_number',
           'id' => 'account_number',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->account_number,

        );

        $data['account_name'] = array(
           'name' => 'account_name',
           'id' => 'account_name',
           'maxlength' => '6',
           'size' => '40',
           'value' => $bankprofile_data->account_name,

    
        );

        $data['account_type'] = array(
           'name' => 'account_type',
           'id' => 'account_type',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->account_type,


        );

        $data['ifsc_code'] = array(
           'name' => 'ifsc_code',
           'id' => 'ifsc_code',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->ifsc_code,


        );

        $data['pan_number'] = array(
           'name' => 'pan_number',
           'id' => 'pan_number',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->pan_number,

        );

        $data['tan_number'] = array(
           'name' => 'tan_number',
           'id' => 'tan_number',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->tan_number,

        );
        
      $data['gst_number'] = array(
           'name' => 'gst_number',
           'id' => 'gst_number',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->gst_number,


         );

          $data['aadhar_number'] = array(
           'name' => 'aadhar_number',
           'id' => 'aadhar_number',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->aadhar_number,

      
         );

          $data['org_id'] = array(
           'name' => 'org_id',
           'id' => 'org_id',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->org_id,
           'readonly' => 'readonly'


          );

          $data['remark'] = array(
           'name' => 'remark',
           'id' => 'remark',
           'maxlength' => '255',
           'size' => '40',
           'value' => $bankprofile_data->remark,
           


        );


        $data['id'] = $id;

        $this->form_validation->set_rules('bank_name','Bankdetails BankName ','trim|xss_clean|required');
        $this->form_validation->set_rules('bank_address','Bankdetails BankAddress ','trim|xss_clean|required');
        $this->form_validation->set_rules('branch_name','Bankdetails BankBranch ','trim|xss_clean|required');
        $this->form_validation->set_rules('account_number','Bankdetails AccountNumber ','trim|xss_clean|numeric|required');
        $this->form_validation->set_rules('account_name','Bankdetails AccountName ','trim|xss_clean');
        $this->form_validation->set_rules('account_type','Bankdetails AccountType ','trim|xss_clean|alpha_numeric_spaces|required');
        $this->form_validation->set_rules('ifsc_code','Bankdetails ifscCode ','trim|xss_clean|alpha_numeric_spaces|required');
        $this->form_validation->set_rules('pan_number','Bankdetails PanNumber ','trim|xss_clean|alpha_numeric_spaces|required');
        $this->form_validation->set_rules('tan_number','Bankdetails TanNumber ','trim|xss_clean|required|alpha_dash');
        $this->form_validation->set_rules('gst_number','Bankdetails GstNumber ','trim|xss_clean|alpha_numeric_spaces|required');
        $this->form_validation->set_rules('aadhar_number','Bankdetails AadharNumber ','trim|xss_clean|numeric');
        $this->form_validation->set_rules('org_id','Bankdetails OrgId ','trim|xss_clean|alpha_numeric_spaces');
        $this->form_validation->set_rules('remark','Bankdetails Remark ','trim|xss_clean');

        if ($_POST)
        {
            $data['bank_name']['value'] = $this->input->post('bank_name', TRUE);
            $data['bank_address']['value'] = $this->input->post('bank_address', TRUE);
            $data['branch_name']['value'] = $this->input->post('branch_name', TRUE);
            $data['account_number']['value'] = $this->input->post('account_number', TRUE);
            $data['account_name']['value'] = $this->input->post('account_name', TRUE);
            $data['account_type']['value'] = $this->input->post('account_type', TRUE);
            $data['ifsc_code']['value'] = $this->input->post('ifsc_code', TRUE);
            $data['pan_number']['value'] = $this->input->post('pan_number', TRUE);
            $data['tan_number']['value'] = $this->input->post('tan_number', TRUE);
            $data['gst_number']['value'] = $this->input->post('gst_number', TRUE); 
            $data['aadhar_number']['value'] = $this->input->post('aadhar_number', TRUE);
            $data['org_id']['value'] = $this->input->post('org_id', TRUE);
            $data['remark']['value'] = $this->input->post('remark', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editbankdetails', $data);
            return;
        }
        else
        {

            $data_bankname = ucwords(strtolower($this->input->post('bank_name', TRUE)));
            $data_bankaddress = $this->input->post('bank_address', TRUE);
            $data_bankbranch = $this->input->post('bank_branch', TRUE);
            $data_accountnumber = $this->input->post('account_number', TRUE);
            $data_accountname = $this->input->post('account_name', TRUE);
            $data_accounttype = $this->input->post('account_type', TRUE);
            $data_ifsccode = strtoupper($this->input->post('ifsc_code', TRUE));
            $data_pannumber = strtoupper($this->input->post('pan_number', TRUE));
            $data_tannumber = strtoupper($this->input->post('tan_number', TRUE));
            $data_bankid = $id;
            $logmessage = "";
            if($bankprofile_data->bank_name != $data_bankname)
                $logmessage = "Add Bankdetails " .$bankprofile_data->bank_name. " changed by " .$data_bankname;
            if($bankprofile_data->bank_address != $data_bankaddress)
                $logmessage = "Add Bankdetails " .$bankprofile_data->bank_address. " changed by " .$data_bankaddress;
            if($bankprofile_data->branch_name != $data_bankbranch)
                $logmessage = "Add Bankdetails " .$bankprofile_data->branch_name. " changed by " .$data_bankbranch;
            if($bankprofile_data->account_number != $data_accountnumber)
                $logmessage = "Add Bankdetails " .$bankprofile_data->account_number. " changed by " .$data_accountnumber;
            if($bankprofile_data->account_name != $data_accountname)
                $logmessage = "Add Bankdetails " .$bankprofile_data->account_name. " changed by " .$data_accountname;
            if($bankprofile_data->account_type != $data_accounttype)
                $logmessage = "Add Bankdetails " .$bankprofile_data->account_type. " changed by " .$data_accounttype;
            if($bankprofile_data->ifsc_code != $data_ifsccode)
                $logmessage = "Add Bankdetails " .$bankprofile_data->ifsc_code. " changed by " .$data_ifsccode;
            if($bankprofile_data->pan_number != $data_pannumber)
                $logmessage = "Add Bankdetails " .$bankprofile_data->pan_number. " changed by " .$data_pannumber;
            if($bankprofile_data->tan_number != $data_tannumber)
                $logmessage = "Add Bankdetails " .$bankprofile_data->tan_number. " changed by " .$data_tanumber;
        
        $update_baarchive = array(
            'bpa_bpid'=>$id,
                    'bpa_bank_name' =>$bankprofile_data->bank_name,
            'bpa_branch_name' =>$bankprofile_data->branch_name,
                    'bpa_bank_address'=>$bankprofile_data->bank_address,
               //'bpa_branch_name '=>$bankprofile_data->bank_branch,
            'bpa_account_number'  =>$bankprofile_data->account_number,
                    'bpa_account_name'=>$bankprofile_data->account_name,
            'bpa_account_type'=>$bankprofile_data->account_type,
                    'bpa_ifsc_code'=>$bankprofile_data->ifsc_code,
                    'bpa_pan_number'  =>$bankprofile_data->pan_number, 
                    'bpa_tan_number'  =>$bankprofile_data->tan_number,
            'bpa_gst_number' =>$bankprofile_data->gst_number,
            'bpa_creatorid' => $this->session->userdata('id_user'),
                    'bpa_date' => date('y-m-d')
            );
     $baflag=$this->SIS_model->insertrec('bankprofile_archive', $update_baarchive);
         if(!$baflag)
         {
              $this->logger->write_dblogmessage("error","Error in insert bank profile archive ", "Error in  bank profile archive record insert" .$logmessage );
         }else{
              $this->logger->write_dblogmessage("insert","Insert bank profile archive", "Record inserted in bank profile archive successfully.." .$logmessage );
         }  

         $update_data = array(
               'bank_name' =>$this->input->post('bank_name'),
               'bank_address' =>$this->input->post('bank_address'),
               'branch_name' =>$this->input->post('branch_name'),
               'account_number'  =>$this->input->post('account_number'),
               'account_name'  =>$this->input->post('account_name'),
               'account_type'  =>$this->input->post('account_type'), 
               'ifsc_code'  =>strtoupper($this->input->post('ifsc_code')),
               'pan_number'  =>strtoupper($this->input->post('pan_number')), 
               'tan_number'  =>strtoupper($this->input->post('tan_number')),
               'gst_number'=>strtoupper($this->input->post('gst_number')),
               'aadhar_number'=>$this->input->post('aadhar_number'),
               'org_id'=>$this->input->post('org_id'),
               'remark'=>$this->input->post('remark')
              );                

           
           $bpflag=$this->SIS_model->updaterec('bankprofile', $update_data, 'id', $id); 
           if(!$bpflag) 
            {
                $this->logger->write_logmessage("error","Error in update bankdetails", "Error in BankDetails record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update bankdetails", "Error in BankDetails record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating bankdetails - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editbankdetails', $data);
            }
           else{
                $this->logger->write_logmessage("insert","Edit bankdetails", "BankDetails record inserted successfully... $logmessage . " );
                $this->logger->write_dblogmessage("insert","Edit BankDetails", "BankDetails record inserted successfully... $logmessage ." );
                $this->session->set_flashdata('success','BankDetails record updating successfully...');
                redirect('setup/displaybankdetails/');
                }
        }//else
        
    
        redirect('setup/editbankdetails/');
   }


   /********************* Cover Type *************************************************************************/

   /*** This funtion opens cover type form ************/
   public function opencovertypeform(){

    $this->load->view('setup/covertypeform');

   }

   /*** This funtion Inserts cover type form details ************/

   public function insertcoverform(){

        if(isset($_POST['cov_type'])){

            $this->form_validation->set_rules('ct_coverno','Cover Number','trim|xss_clean|required|alpha_numeric_spaces|callback_isCovertypeExist');
            $this->form_validation->set_rules('ct_coverfixed','Fixed Cover','trim|xss_clean|required');
            $this->form_validation->set_rules('ct_coveroptional','Optional Cover','trim|xss_clean|required');
            $this->form_validation->set_rules('ct_desc','Cover Description','trim|xss_clean|required');

            if($this->form_validation->run()==TRUE){

                $data = array(   
                'ct_coverno'=>$_POST['ct_coverno'],
                'ct_coverfixed'=>$_POST['ct_coverfixed'],
                'ct_coveroptional'=>$_POST['ct_coveroptional'],
                'ct_desc'=>$_POST['ct_desc']
                 );

                $rflag= $this->PICO_model->insertrec('cover_type',$data);

                if(!$rflag){
                    $this->logger->write_logmessage("insert","Trying to Add Cover Number", "Cover is not added ".$_POST['ct_coverno']);
                    $this->logger->write_logmessage("insert","Trying to Add Fixed Cover", "Fixed Cover is not added ".$_POST['ct_coverfixed']);
                    $this->logger->write_logmessage("insert","Trying to Add Optional Cover", "Optional Cover is not added ".$_POST['ct_coveroptional']);
                    $this->logger->write_logmessage("insert","Trying to Add Cover Description", "Cover Description is not added ".$_POST['ct_desc']);

                    $this->session->set_flashdata('err_message','Error in adding Cover Type setting...'  , 'error');
                    redirect('picosetup/opencovertypeform');
                    
                }
                else{
                    $this->logger->write_logmessage("insert","Add Cover Number", "Cover Number".$_POST['ct_coverno']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Cover Number", "Cover Number".$_POST['ct_coverno']." added  successfully...");
                    $this->logger->write_logmessage("insert","Add Fixed Cover", "Cover Number".$_POST['ct_coverfixed']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Fixed Cover", "Cover Number".$_POST['ct_coverfixed']." added  successfully...");
                    $this->logger->write_logmessage("insert","Add Optional Cover", "Cover Number".$_POST['ct_coveroptional']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Optional Cover", "Cover Number".$_POST['ct_coveroptional']." added  successfully...");
                    $this->logger->write_logmessage("insert","Add Cover Description", "Cover Description".$_POST['ct_desc']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Cover Description", "Cover Description".$_POST['ct_desc']." added  successfully...");

                    $this->session->set_flashdata("success", "Cover Type added successfully...");
                    redirect("picosetup/displaycovertypedetails"); 

                }
            }
        }
        $this->load->view('setup/covertypeform');
    }

     /** This function check for duplicate Cover type entry
     * @return type
    */
    public function isCovertypeExist($ct_coverno) {

        $is_exist = $this->PICO_model->isduplicate('cover_type','ct_coverno',$ct_coverno);
        if ($is_exist)
        {
            $this->form_validation->set_message('isCovertypeExist', 'This Cover Number already exist.');

            return false;
        }
        else 
        {
            return true;
        }
    }

     /** This function used to display cover type entries
     * @return type
    */
      public function displaycovertypedetails(){
        $data['result'] = $this->PICO_model->get_list('cover_type');
        $this->logger->write_logmessage("view"," View Cover Type setting", "Cover Type setting details...");
        $this->logger->write_dblogmessage("view"," View Cover Type setting", "Cover Type setting details...");
        $this->load->view('setup/displaycoverdetails',$data);
    } 

    /***** Delete Cover type ****/
    public function deletcovertype($id) { 

        $mt_data=$this->PICO_model->get_listrow('cover_type','ct_id', $id);
        if ($mt_data->num_rows() < 1)
        {
            redirect('picosetup/opencovertypeform');
        }
            $fmdflag=$this->PICO_model->deleterow('cover_type','ct_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in Deleting Selected Cover Type " ."[ct_id:" . $id . "]");
                    $this->logger->write_dbmessage("error", "Error  in Deleting Selected Cover Type"." [ct_id:" . $id . "]");
                    $this->session->set_flashdata('err_message', 'Error in Deleting Selected Cover Type', 'error');
                    redirect('picosetup/displaycovertypedetails');
                //return;
              }
              else{
                    $this->logger->write_logmessage("delete", "Deleted   Cover record " . "[ct_id:" . $id . "] deleted successfully.. " );
                    $this->logger->write_dblogmessage("delete", "Deleted Cover record" ." [ct_id:" . $id . "] deleted successfully.. " );
                    $this->session->set_flashdata("success", "Specific Cover deleted successfully..." );
                    redirect('picosetup/displaycovertypedetails');
            }
          $this->load->view('setup/displaycoverdetails',$data);
    }

    /************* Item Type ******************************************************************************/

    public function openitemtype(){

        $this->load->view('setup/itemtypeform');
    }

    /**** This function Insert Item type *********************/

    public function insertitemtype(){

        if(isset($_POST['item_type'])){

            $this->form_validation->set_rules('item_id','Item ID','trim|xss_clean|required|alpha_numeric_spaces|callback_isItemIdExist');
            $this->form_validation->set_rules('item_mtid','Material ID','trim|xss_clean|required|alpha_numeric_spaces|callback_isMaterialIdExist');
            $this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('item_price','Item Price','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('item_stock','Item Stock','trim|xss_clean|required|alpha_numeric_spaces');


        if($this->form_validation->run() ==TRUE){

                $data = array(
                    'item_id'=>$_POST['item_id'],
                    'item_mtid'=>$_POST['item_mtid'],
                    'item_name'=>$_POST['item_name'],
                    'item_price'=>$_POST['item_price'],
                    'item_stock'=>$_POST['item_stock'],
                );

                $rflag= $this->PICO_model->insertrec('items',$data);
                if(!$rflag){
                    $this->logger->write_logmessage("insert","Trying to Add Item ID", "Item ID is not added ".$_POST['item_id']);
                    $this->logger->write_logmessage("insert","Trying to Add Material ID", "Material ID is not added ".$_POST['item_mtid']);
                    $this->logger->write_logmessage("insert","Trying to Add Item Name", "Item Name is not added ".$_POST['item_name']);
                    $this->logger->write_logmessage("insert","Trying to Add Item Price", "Item Price is not added ".$_POST['item_price']);
                    $this->logger->write_logmessage("insert","Trying to Add Item Stock ", "Item Stock is not added ".$_POST['item_stock']);

                    $this->session->set_flashdata('err_message','Error in adding Item details...'  , 'error');
                    redirect('picosetup/openitemtype');

                }
                else{
                    $this->logger->write_logmessage("insert","Add Item ID", "Item ID".$_POST['item_id']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Item ID", "Item ID".$_POST['item_id']." added  successfully...");

                    $this->logger->write_logmessage("insert","Add Material ID", "Material ID".$_POST['item_mtid']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Material ID", "Material ID".$_POST['item_mtid']." added  successfully...");

                    $this->logger->write_logmessage("insert","Add Item Name", "Item Name".$_POST['item_name']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Item Name", "Item Name".$_POST['item_name']." added  successfully...");

                    $this->logger->write_logmessage("insert","Add Item Price", "Item Price".$_POST['item_price']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Item Price", "Item Price".$_POST['item_price']." added  successfully...");


                    $this->logger->write_logmessage("insert","Add Item Stock", "Item Stock".$_POST['item_stock']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Item Stock", "Item Stock".$_POST['item_stock']." added  successfully...");

                    $this->session->set_flashdata("success", "Item added successfully...");
                    redirect("picosetup/itemtypedetails"); 

                }




           }
        }
        $this->load->view('setup/itemtypeform');

    }

    /** This function check for duplicate Item ID entry
     * @return type
    */
    public function isItemIdExist($item_id) {

        $is_exist = $this->PICO_model->isduplicate('items','item_id',$item_id);
        if ($is_exist)
        {
            $this->form_validation->set_message('isItemIdExist', 'This Item ID already exist.');

            return false;
        }
        else 
        {
            return true;
        }
    }

    /** This function check for duplicate Material ID entry
     * @return type
    */
    public function isMaterialIdExist($item_mtid) {

        $is_exist = $this->PICO_model->isduplicate('items','item_mtid',$item_mtid);
        if ($is_exist)
        {
            $this->form_validation->set_message('isMaterialIdExist', 'This Material ID already exist.');

            return false;
        }
        else 
        {
            return true;
        }
    }

    /** This function check for display Item type entries
     * @return type
    */
      public function itemtypedetails(){
        $data['result'] = $this->PICO_model->get_list('items');
        $this->logger->write_logmessage("view"," View Item Type setting", "Item Type setting details...");
        $this->logger->write_dblogmessage("view"," View Item Type setting", "Item Type setting details...");
        $this->load->view('setup/displayitemdetails',$data);
    } 

     /* this function is used for item record */
    public function deleteitemtype($id) { 

        $mt_data=$this->PICO_model->get_listrow('items','item_id', $id);
        if ($mt_data->num_rows() < 1)
        {
            redirect('picosetup/itemtypedetails');
        }
            $fmdflag=$this->PICO_model->deleterow('items','item_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in Deleting Selected Item " ."[item_id:" . $id . "]");
                    $this->logger->write_dbmessage("error", "Error  in Deleting Selected Item "." [item_id:" . $id . "]");
                    $this->session->set_flashdata('err_message', 'Error in Deleting Selected Item - ', 'error');
                    redirect('picosetup/itemtypedetails');
                //return;
              }
              else{
                    $this->logger->write_logmessage("delete", "Deleted   Item record " . "[mt_id:" . $id . "] deleted successfully.. " );
                    $this->logger->write_dblogmessage("delete", "Deleted Item record" ." [mt_id:" . $id . "] deleted successfully.. " );
                    $this->session->set_flashdata("success", "Specific Item deleted successfully..." );
                    redirect('picosetup/itemtypedetails');
            }
          $this->load->view('setup/displayitemdetails',$data);
    }

    /************* Purchase Committee Formation Rules  ******************************************************************************/

    public function purchasecommitteedetails(){
         $data['result'] = $this->PICO_model->get_list('purchase_com_form_rule');
        $this->logger->write_logmessage("view"," View Purchase Committee Formation Rules", "Purchase Committee Formation Rules...");
        $this->logger->write_dblogmessage("view"," Purchase Committee Formation Rules", "Purchase Committee Formation Rules...");
        $this->load->view('setup/displaypurchasecommitteedetails',$data);
    }
    

    /******* Purchase Committee Form ***********/

    public function openpurchasecommitteeformrule(){

        $this->load->view('setup/purchasecommitteeformrule');
    }


    /*** Inserting Purchase committee formation rule ****/
     public function insertpurchasecommitteerule(){
        
            if(isset($_POST['committee_rule'])) {
            $this->form_validation->set_rules('pcfr_purchasethrough','Purchase Through','required');
            $this->form_validation->set_rules('pcfr_estpurchaseprice','Estimated Purchase Price','xss_clean|required');
            $this->form_validation->set_rules('pcfr_rep1','1st Representative','required');
            $this->form_validation->set_rules('pcfr_rep2','2nd Representative');
            $this->form_validation->set_rules('pcfr_rep3','3rd Representative');
            $this->form_validation->set_rules('pcfr_reference','Reference','');
            $this->form_validation->set_rules('pcfr_appauth','Approving Authority','required');
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 
                
                 $data = array(
                'pcfr_purchasethrough'=>$_POST['pcfr_purchasethrough'],
                'pcfr_estpurchaseprice'=>$_POST['pcfr_estpurchaseprice'],   
                'pcfr_rep1'=>$_POST['pcfr_rep1'],
                'pcfr_rep2'=>$_POST['pcfr_rep2'],
                'pcfr_rep3'=>$_POST['pcfr_rep3'],
                'pcfr_reference'=>$_POST['pcfr_reference'],
                'pcfr_appauth'=>$_POST['pcfr_appauth'],
                'pcfr_createrid'=>$this->session->userdata('username'),
                'pcfr_creatordate'=>date('Y-m-d')
                 );


                $duplicate= $this->PICO_model->isduplicatemore('purchase_com_form_rule',$data);
                if(!$duplicate){
                    $rflag=$this->PICO_model->insertrec('purchase_com_form_rule', $data);
                    if (!$rflag){

                    $this->logger->write_logmessage("insert","Trying to add Purchase Committee Formation Rules", "Purchase Committee Formation Rules is not added ");
                    $this->logger->write_dblogmessage("insert","Trying to add Purchase Committee Formation Rules", "Purchase Committee Formation Rules is not added ");
                    $this->session->set_flashdata('err_message','Error in adding Purchase Committee Formation Rules'  , 'error');
                    redirect('picosetup/openpurchasecommitteeformrule');

                    }
                    else{

                    $this->logger->write_logmessage("insert","Add Purchase Committee Formation Rules", "Purchase Committee Formation Rules added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Purchase Committee Formation Rules", "Purchase Committee Formation Rules added  successfully...");
                    $this->session->set_flashdata("success", "Purchase Committee Formation Rules added successfully...");
                    redirect("picosetup/purchasecommitteedetails");
                    } 
                }
                else{
                    // $this->logger->write_logmessage("insert","Duplicate Financial Authority exist","Financial Authority is not added ".$fp_authority);
                    // $this->logger->write_dblogmessage("insert","Duplicate description exist", "Description is not added ".$fp_desc);
                    // $this->session->set_flashdata('err_message','This authority already exist...'  , 'error');
                    redirect("picosetup/openpurchasecommitteeformrule");
                }
                
            }
            
        }
          
        $this->load->view('setup/purchasecommitteeformrule');
    }

    /***** Delete Purchase Committee Formation Rules ******************/
    public function deletepurchasecommitteerule($id) { 
        

        $mt_data=$this->PICO_model->get_listrow('purchase_com_form_rule','pcfr_id', $id);
        if ($mt_data->num_rows() < 1)
        {
            redirect('setup/displaystore');
        }
            $fmdflag=$this->PICO_model->deleterow('purchase_com_form_rule','pcfr_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in deleting this Rule " ."[pcfr_id:" . $id . "]");
                    $this->logger->write_dbmessage("error", "Error  in deleting this Rule"." [pcfr_id:" . $id . "]");
                    $this->session->set_flashdata('err_message', 'Error in Deleting this Rule', 'error');
                    redirect('picosetup/purchasecommitteedetails');
                //return;
              }
              else{
                    $this->logger->write_logmessage("delete", "Deleted   rule record " . "[pcfr_id:" . $id . "] deleted successfully.. " );
                    $this->logger->write_dblogmessage("delete", "Deleted rule record" ." [pcfr_id:" . $id . "] deleted successfully.. " );
                    $this->session->set_flashdata("success", "Specific Rule deleted successfully..." );
                    redirect('picosetup/purchasecommitteedetails');
            }
          $this->load->view('setup/displaypurchasecommitteedetails',$data);
    }


    /*This function modify the Purchase Committee Formation Rules*/
    public function editpurchasecommitteeformationrule($id) {

        $this->db4->from('purchase_com_form_rule')->where('pcfr_id', $id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
           redirect('picosetup/purchasecommitteedetails');
        }
        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data['pcfr_purchasethrough'] = array(
                'name' => 'pcfr_purchasethrough',
                'id' => 'pcfr_purchasethrough',
                'size' => '40',
                'value' => $editeset_data->pcfr_purchasethrough,

                );
                $data['pcfr_estpurchaseprice'] = array(
                'name' => 'pcfr_estpurchaseprice',
                'id' => 'pcfr_estpurchaseprice',
                'size' => '40',
                'value' => $editeset_data->pcfr_estpurchaseprice,

                );
                $data['pcfr_rep1'] = array(
                'name' => 'pcfr_rep1',
                'id' => 'pcfr_rep1',
                'size' => '40',
                'value' => $editeset_data->pcfr_rep1,

                );
                $data['pcfr_rep2'] = array(
                'name' => 'pcfr_rep2',
                'id' => 'pcfr_rep2',
                'size' => '40',
                'value' => $editeset_data->pcfr_rep2,

                );
                $data['pcfr_rep3'] = array(
                'name' => 'pcfr_rep3',
                'id' => 'pcfr_rep3',
                'size' => '40',
                'value' => $editeset_data->pcfr_rep3,

                );
                $data['pcfr_reference'] = array(
                'name' => 'pcfr_reference',
                'id' => 'pcfr_reference',
                'size' => '20',
                'value' => $editeset_data->pcfr_reference,

                );
                $data['pcfr_appauth'] = array(
                'name' => 'pcfr_appauth',
                'id' => 'pcfr_appauth',
                'size' => '40',
                'value' => $editeset_data->pcfr_appauth,

        );
        $data['id'] = $id;
        /*Form Validation*/
            $this->form_validation->set_rules('pcfr_purchasethrough','Purchase Through','required');
            $this->form_validation->set_rules('pcfr_estpurchaseprice','Estimated Purchase Price','xss_clean|required');
            $this->form_validation->set_rules('pcfr_rep1','1st Representative','required');
            $this->form_validation->set_rules('pcfr_rep2','2nd Representative');
            $this->form_validation->set_rules('pcfr_rep3','3rd Representative');
            $this->form_validation->set_rules('pcfr_reference','Reference','');
            $this->form_validation->set_rules('pcfr_appauth','Approving Authority','required');

        /* Re-populating form */
        if ($_POST)
        {
            $data['pcfr_purchasethrough']['value'] = $this->input->post('pcfr_purchasethrough', TRUE);
            $data['pcfr_estpurchaseprice']['value'] = $this->input->post('pcfr_estpurchaseprice', TRUE);
            $data['pcfr_rep1']['value'] = $this->input->post('pcfr_rep1', TRUE);
            $data['pcfr_rep2']['value'] = $this->input->post('pcfr_rep2', TRUE);
            $data['pcfr_rep3']['value'] = $this->input->post('pcfr_rep3', TRUE);
            $data['pcfr_reference']['value'] = $this->input->post('pcfr_reference', TRUE);
            $data['pcfr_appauth']['value'] = $this->input->post('pcfr_appauth', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editpurchcommitteeformrule', $data);
            return;
        }
        else{

            $data_purchasethrough = $this->input->post('pcfr_purchasethrough', TRUE);
            $data_estpurchaseprice = $this->input->post('pcfr_estpurchaseprice', TRUE);
            $data_rep1 = $this->input->post('pcfr_rep1', TRUE);
            $data_rep2 = $this->input->post('pcfr_rep2', TRUE);
            $data_rep3 = $this->input->post('pcfr_rep3', TRUE);
            $data_reference = $this->input->post('pcfr_reference', TRUE);
            $data_appauth = $this->input->post('pcfr_appauth', TRUE);
            $data_eid = $id;
            $logmessage = "";
            if($editeset_data->pcfr_purchasethrough != $data_purchasethrough)
                $logmessage = "Purchase Through" .$editeset_data->pcfr_purchasethrough. " changed by " .$data_purchasethrough;

            if($editeset_data->pcfr_estpurchaseprice!= $data_estpurchaseprice)
                $logmessage = "Estimated Purchase Price" .$editeset_data->pcfr_estpurchaseprice. " changed by " .$data_estpurchaseprice;

            if($editeset_data->pcfr_rep1 != $data_rep1)
                $logmessage = "1st Representative" .$editeset_data->pcfr_rep1. " changed by " .$data_rep1;

            if($editeset_data->pcfr_rep2 != $data_rep2)
                $logmessage = "2nd Representative" .$editeset_data->pcfr_rep2. " changed by " .$data_rep2;

            if($editeset_data->pcfr_rep3 != $data_rep3)
                $logmessage = "3rd Representative" .$editeset_data->pcfr_rep3. " changed by " .$data_rep3;

            if($editeset_data->pcfr_reference != $data_reference)
                $logmessage = "Reference " .$editeset_data->pcfr_reference. " changed by " .$data_reference;

            if($editeset_data->pcfr_appauth != $data_appauth)
                $logmessage = "Approving Authority" .$editeset_data->pcfr_appauth. " changed by " .$data_appauth;

            $update_data = array(
               'pcfr_purchasethrough' => $data_purchasethrough,
               'pcfr_estpurchaseprice'=> $data_estpurchaseprice,
               'pcfr_rep1' => $data_rep1,
               'pcfr_rep2' => $data_rep2,
               'pcfr_rep3' => $data_rep3,
               'pcfr_reference' => $data_reference,
               'pcfr_appauth' => $data_appauth,
               'pcfr_modifierid'=>$this->session->userdata('username'),
               'pcfr_modifydate'=>date('Y-m-d')
            );

        
                $roledflag=$this->PICO_model->updaterec('purchase_com_form_rule', $update_data,' pcfr_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","updating selected Purchase Committee Formation Rule error", "updating selected Purchase Committee Formation Rule." . $logmessage . '.', 'error');
                $this->logger->write_dblogmessage("error","updating selected Purchase Committee Formation Rule error", "updating selected Purchase Committee Formation Rule.". $logmessage . '.', 'error');
                $this->session->set_flashdata('err_message','Error updating selected Purchase Committee Formation Rule' . $logmessage . '.', 'error');
                $this->load->view('setup/purchasecommitteeformrule', $data);
                }
                else{
                $this->logger->write_logmessage("update","Edit updating selected Purchase Committee Formation Rule", "Edit updating selected Purchase Committee Formation Rule." . $logmessage . '.', 'error');
                $this->logger->write_dblogmessage("update","Edit updating selected Purchase Committee Formation Rule", "Edit updating selected Purchase Committee Formation Rule.". $logmessage . '.', 'error');
                $this->session->set_flashdata('success','Selected Purchase Committee Formation Rule updated successfully..');
                redirect("picosetup/purchasecommitteedetails");
                }
            
        }

    }




    /***************************************** PURCHASE PROPOSAL ********************************************************/

    /** This function opens purchase proposal form**/
    public function openpurchaseproposalform(){
        $typeofmat['material']=  $this->PICO_model->get_list('material_type');
        $typeofmat['dept']= $this->common_model->get_list('Department');
        $typeofmat['ven']= $this->PICO_model->get_list('vendor');
        $this->load->view('setup/purchaseproposalform',$typeofmat);
    }

}
