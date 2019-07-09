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
        $data['result'] = $this->PICO_model->get_list('material_type');
        $this->logger->write_logmessage("view"," View store setting", "Store setting details...");
        $this->logger->write_dblogmessage("view"," View store setting", "Store setting details...");
        $this->load->view('setup/displaystore',$data);
       }


     /** This function for add store
     * @return type
     */

    public function insertstore()
        {
        		 if(isset($_POST['submitstore'])) {
                 $this->form_validation->set_rules('mt_name','Type of Store','required');
                 $this->form_validation->set_rules('mt_desc','Material Desc','required');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 
                //$tbdata=$_POST['store'];
                 $data = array(   
                'mt_name'=>$_POST['mt_name'],
                'mt_desc'=>$_POST['mt_desc'],
                 );
                
                $duplicate= $this->PICO_model->isduplicatemore('material_type',$data);
                if(!$duplicate){
                    $rflag=$this->PICO_model->insertrec('material_type', $data);
                    if (!$rflag){

                    $this->logger->write_logmessage("insert","Trying to add store type", "Store is not added ".$mt_name);
                    $this->logger->write_dblogmessage("insert","Trying to add store description", "Description is not added ".$mt_desc);
                    $this->session->set_flashdata('err_message','Error in adding store setting - '  , 'error');
                    redirect('picosetup/opentypeofstore');

                    }
                    else{

                    $this->logger->write_logmessage("insert","Add Store Setting", "Store".$_POST['mt_name']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add role Setting", "Store Description ".$_POST['mt_desc']."added  successfully...");
                    $this->session->set_flashdata("success", "Store added successfully...");
                    redirect("picosetup/displaystore");
                    } 
                }
                else{
                    $this->logger->write_logmessage("insert","Duplicate store exist", "Store is not added ".$mt_name);
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


    // /*this function opens view/setup/editstore*/
    // public function openeditstore($id){
        
    //     $data['id']=$id;
    //     $this->load->view("setup/editstore",$data);

    // }
    
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

    /*This function modify the Purchase Committee Formation Rules*/
    public function editstore($id)
    {
        
        $this->db4->from('material_type')->where('mt_id', $id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
           redirect('picosetup/displaystore');
        }
        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data[' mt_name'] = array(
                'name' => ' mt_name',
                'id' => '   mt_name',
                'size' => '40',
                'value' => $editeset_data->mt_name,

                );
                $data[' mt_desc'] = array(
                'name' => ' mt_desc',
                'id' => '   mt_desc',
                'size' => '40',
                'value' => $editeset_data->mt_desc,

                );
                
            $data['id'] = $id;
            /*Form Validation*/
            $this->form_validation->set_rules('mt_name','Type of Store','required');
            $this->form_validation->set_rules('mt_desc','Material Desc','required');
           
            /* Re-populating form */
        if ($_POST)
        {
            $data['mt_name']['value'] = $this->input->post('mt_name', TRUE);
            $data['mt_desc']['value'] = $this->input->post('mt_desc', TRUE);
            

            if ($this->form_validation->run() == FALSE)
            {
            $this->load->view('setup/editpurchcommitteeformrule', $data);
            return;
            }
            else{

            $data_name = $this->input->post('mt_name', TRUE);
            $data_desc = $this->input->post('mt_desc', TRUE);
           
            $data_eid = $id;
            $logmessage = "";
            if($editeset_data->mt_name != $data_name)
                $logmessage = "Type of Store" .$editeset_data->mt_name. " changed by " .$data_name;

            if($editeset_data->mt_desc!= $data_desc)
                $logmessage = "Material Description" .$editeset_data->mt_desc. " changed by " .$data_desc;


            $update_data = array(
               'mt_name' => $data_name,
               'mt_desc'=> $data_desc,
              
            );

        
                $roledflag=$this->PICO_model->updaterec('material_type', $update_data,' mt_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","updating selected Type of store error", "updating selected Type of store." . $logmessage . '.', 'error');
                $this->logger->write_dblogmessage("error","updating selected Type of store error", "updating selected Type of store.". $logmessage . '.', 'error');
                $this->session->set_flashdata('err_message','Error updating selected Type of store' . $logmessage . '.', 'error');
                $this->load->view('setup/displaystore', $data);
                }
                else{
                $this->logger->write_logmessage("update","Edit updating selected Type of store", "Edit updating selected Type of store." . $logmessage . '.', 'error');
                $this->logger->write_dblogmessage("update","Edit updating selected Type of store", "Edit updating selected Type of store.". $logmessage . '.', 'error');
                $this->session->set_flashdata('success','Selected Type of store updated successfully..');
                redirect("picosetup/displaystore");
                }
            
            }
        }
    }

    
    /****************************************************Financial Power form *********************************************************
    **/

    public function openfinancialpower(){
        $data['material']= $this->PICO_model->get_list('material_type');
        $data['authresult'] = $this->login_model->get_listspfic2('authorities','id','name');
        $this->load->view('setup/financialpowerform',$data);
    }

    /* Display Financial Power Details */

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
            $this->form_validation->set_rules('fp_typeofpurch','Type of Purchase','trim|xss_clean|required');
            $this->form_validation->set_rules('fp_subtypepurch','Sub Purchase Type','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('authority','Authority','required|callback_isAuthorityExist');
            $this->form_validation->set_rules('fp_limit','Limit','trim|required');
            $this->form_validation->set_rules('fp_desc','Item Description','trim|xss_clean|required');
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 
                
                 $data = array(   
                'fp_typeofpurch'=>$_POST['fp_typeofpurch'],
                'fp_subtypepurch'=>$_POST['fp_subtypepurch'],
                'fp_authority'=>$_POST['authority'],
                'fp_limit'=>$_POST['fp_limit'],
                'fp_desc'=>$_POST['fp_desc'],
                'fp_creatorid'=> $this->session->userdata('username'),
                'fp_creatordate'=>date('Y-m-d')
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
        $this->form_validation->set_rules('fp_typeofpurch','Type of Purchase','trim|xss_clean|required');
        $this->form_validation->set_rules('fp_subtypepurch','Sub Purchase Type','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('fp_authority','Authority','trim|xss_clean|required');
        $this->form_validation->set_rules('fp_limit','Limit','trim|required');
        $this->form_validation->set_rules('fp_desc','Item Description','trim|xss_clean|required');

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
                $logmessage = "Type of Purchase " .$editeset_data->fp_typeofpurch. " changed by " .$data_typeofpurch;

            if($editeset_data->fp_subtypepurch!= $data_subtypepurch)
                $logmessage = "Sub Purchase Type" .$editeset_data->fp_subtypepurch. " changed by " .$data_subtypepurch;

            if($editeset_data->fp_authority != $data_authority)
                $logmessage = "Authority " .$editeset_data->fp_authority. " changed by " .$data_authority;

            if($editeset_data->fp_limit != $data_limit)
                $logmessage = "Financial Limit " .$editeset_data->fp_limit. " changed by " .$data_limit;

            if($editeset_data->fp_desc != $data_desc)
                $logmessage = "Add Role " .$editeset_data->fp_desc. " changed by " .$data_desc;

            $update_data = array(
               'fp_typeofpurch' => $data_typeofpurch,
               'fp_subtypepurch'=> $data_subtypepurch,
               'fp_authority' => $data_authority,
               'fp_limit' => $data_limit,
               'fp_desc' => $data_desc,
               'fp_modifierid'=> $this->session->userdata('username'),
               'fp_modifierdate'=>date('Y-m-d')
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
            $select_box.='<option value=Without_Quotations>Purchase of Goods Without Quotations';
            $select_box.='<option value=Without_Quotation_Purchase_Committee>Purchase of Goods Through Purchase Committee Without Quotations';
            $select_box.='<option value=With_Quotation_Purchase_Committee>Through Purchase Committee With Quotation(s)';
            
        }
     else if($ptype=='Medium'){
            $select_box ='';
            $select_box.='<option selected disabled value= >-------Select Purchase Category--------';
            $select_box.='<option value=Memberlimit_Purchase_Committee_With_Quotation>Purchases Through Limited Tender Enquiry(CFA approved)';
            $select_box.='<option value=Purchase_Committee(CA_approved)>Purchase Committee(CFA approved)';
           
            
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
                 $this->form_validation->set_rules('amt_above','Amount Above','trim|numeric|xss_clean|required');
                 $this->form_validation->set_rules('amt_below','Amount Above','trim|numeric|xss_clean|required');
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

   /********************* Cover Type *************************************************************************/

   /*** This funtion opens cover type form ************/
   public function opencovertypeform(){

    $this->load->view('setup/covertypeform');

   }

   /*** This funtion Inserts cover type form details ************/

   public function insertcoverform(){

        if(isset($_POST['cov_type'])){

                        $this->form_validation->set_rules('ct_coverno','Cover Number','trim|xss_clean|required|alpha_numeric_spaces|callback_isCovertypeExist');
						$this->form_validation->set_rules('ct_cover1','Cover Type 1','trim|xss_clean|required');
						$this->form_validation->set_rules('ct_cover2','Cover Type 2','trim|xss_clean');
						$this->form_validation->set_rules('ct_cover3','Cover Type 3','trim|xss_clean');
						$this->form_validation->set_rules('ct_cover4','Cover Type 4','trim|xss_clean');
						$this->form_validation->set_rules('ct_coverfixed','Fixed Cover','trim|xss_clean');
						$this->form_validation->set_rules('ct_coveroptional','Optional Cover','trim|xss_clean');
						$this->form_validation->set_rules('ct_desc','Cover Description','trim|xss_clean');
			
            if($this->form_validation->run()==TRUE){

                $data = array(   
                'ct_coverno'=>$_POST['ct_coverno'],
                'ct_cover1'=>$_POST['ct_cover1'],
                'ct_cover2'=>$_POST['ct_cover2'],
                'ct_cover3'=>$_POST['ct_cover3'],
                'ct_cover4'=>$_POST['ct_cover4'],
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
	
	/*** This function updates cover details ****/
    public function editcoverdetails($id)
    {

        $this->db4->from('cover_type')->where('ct_id',$id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
            redirect('picosetup/opencovertypeform');
        }
        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data['ct_coverno'] = array(
                'name' => 'ct_coverno',
                'id' => 'ct_coverno',
                'size' => '40',
                'value' => $editeset_data->ct_coverno,
                );

                $data['ct_coverfixed'] = array(
                'name' => 'ct_coverfixed',
                'id' => 'ct_coverfixed',
                'size' => '40',
                'value' => $editeset_data->ct_coverfixed,
                );

                $data['ct_cover1'] = array(
                'name' => 'ct_cover1',
                'id' => 'ct_cover1',
                'size' => '40',
                'value' => $editeset_data->ct_cover1,
                );

                $data['ct_cover2'] = array(
                'name' => 'ct_cover2',
                'id' => 'ct_cover2',
                'size' => '40',
                'value' => $editeset_data->ct_cover2,
                );

                $data['ct_cover3'] = array(
                'name' => 'ct_cover3',
                'id' => 'ct_cover3',
                'size' => '40',
                'value' => $editeset_data->ct_cover3,
                );

                $data['ct_cover4'] = array(
                'name' => 'ct_cover4',
                'id' => 'ct_cover4',
                'size' => '40',
                'value' => $editeset_data->ct_cover4,
                );

                $data['ct_coveroptional'] = array(
                'name' => 'ct_coveroptional',
                'id' => 'ct_coveroptional',
                'size' => '40',
                'value' => $editeset_data->ct_coveroptional,
                );

                $data['ct_desc'] = array(
                'name' => 'ct_desc',
                'id' => 'ct_desc',
                'size' => '40',
                'value' => $editeset_data->ct_desc,
                );


        
        $data['id'] = $id;
        /*Form Validation*/
           $this->form_validation->set_rules('ct_coverno','Cover Number','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('ct_cover1','Cover Type 1','trim|xss_clean|required');
            $this->form_validation->set_rules('ct_cover2','Cover Type 2','trim|xss_clean');
            $this->form_validation->set_rules('ct_cover3','Cover Type 3','trim|xss_clean');
            $this->form_validation->set_rules('ct_cover4','Cover Type 4','trim|xss_clean');
            $this->form_validation->set_rules('ct_coverfixed','Fixed Cover','trim|xss_clean');
            $this->form_validation->set_rules('ct_coveroptional','Optional Cover','trim|xss_clean');
            $this->form_validation->set_rules('ct_desc','Cover Description','trim|xss_clean');


        /* Re-populating form */
        if ($_POST)
        {
            $data['ct_coverno']['value'] = $this->input->post('ct_coverno', TRUE);
            $data['ct_cover1']['value'] = $this->input->post('ct_cover1', TRUE);
            $data['ct_cover2']['value'] = $this->input->post('ct_cover2', TRUE);
            $data['ct_cover3']['value'] = $this->input->post('ct_cover3', TRUE);
            $data['ct_cover4']['value'] = $this->input->post('ct_cover4', TRUE);
            $data['ct_coverfixed']['value'] = $this->input->post('ct_coverfixed', TRUE);
            $data['ct_coveroptional']['value'] = $this->input->post('ct_coveroptional', TRUE);
            $data['ct_desc']['value'] = $this->input->post('ct_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editcoverdetails',$data);
            return;
        }

        else{

            $data_coverno = $this->input->post('ct_coverno', TRUE);
            $data_cover1 = $this->input->post('ct_cover1', TRUE);
            $data_cover2 = $this->input->post('ct_cover2', TRUE);
            $data_cover3 = $this->input->post('ct_cover3', TRUE);
            $data_cover4 = $this->input->post('ct_cover4', TRUE);
            $data_coverfixed = $this->input->post('ct_coverfixed', TRUE);
            $data_coveroptional = $this->input->post('ct_coveroptional', TRUE);
            $data_desc = $this->input->post('ct_desc', TRUE);
            $data_eid = $id;

            $logmessage = "";
            if($editeset_data->ct_coverno != $data_coverno)
                $logmessage = "Cover Number" .$editeset_data->ct_coverno. " changed by " .$data_coverno;

            if($editeset_data->ct_cover1!= $data_cover1)
                $logmessage = "Cover Type 1" .$editeset_data->ct_cover1. " changed by " .$data_cover1;
            if($editeset_data->ct_cover2!= $data_cover2)
                $logmessage = "Cover Type 2" .$editeset_data->ct_cover2. " changed by " .$data_cover2;
            if($editeset_data->ct_cover3!= $data_cover3)
                $logmessage = "Cover Type 3" .$editeset_data->ct_cover3. " changed by " .$data_cover3;
            if($editeset_data->ct_cover4!= $data_cover4)
                $logmessage = "Cover Type 4" .$editeset_data->ct_cover4. " changed by " .$data_cover4;

            if($editeset_data->ct_coverfixed != $data_coverfixed)
                $logmessage = "Fixed Cover" .$editeset_data->ct_coverfixed. " changed by " .$data_coverfixed;

            if($editeset_data->ct_coveroptional != $data_coveroptional)
                $logmessage = "Optional Cover" .$editeset_data->ct_coveroptional. " changed by " .$data_coveroptional;

            if($editeset_data->ct_desc != $data_desc)
                $logmessage = "Cover Description" .$editeset_data->ct_desc. " changed by " .$data_desc;

            $update_data = array(
               'ct_coverno' => $data_coverno,
               'ct_cover1'=> $data_cover1,
               'ct_cover2'=> $data_cover2,
               'ct_cover3'=> $data_cover3,
               'ct_cover4'=> $data_cover4,
               'ct_coverfixed' => $data_coverfixed,
               'ct_coveroptional' => $data_coveroptional,
               'ct_desc' => $data_desc,
            );

                $roledflag=$this->PICO_model->updaterec('cover_type', $update_data,'ct_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","Edit Cover details error", "Edit Cover details . $logmessage ");
                $this->logger->write_dblogmessage("error","Edit Cover details error", "Edit Cover details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating Cover details' . $logmessage . '.', 'error');
                $this->load->view('setup/editcoverdetails', $data);
                }
                else{
                $this->logger->write_logmessage("update","Edit Cover details  Setting", "Edit Cover details  Setting ". $logmessage );
                $this->logger->write_dblogmessage("update","Edit Cover details  Setting", "Edit Cover details  Setting" . $logmessage );
                $this->session->set_flashdata('success','Selected Cover details updated successfully..');
                redirect('picosetup/displaycovertypedetails');
                }
            }        
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

    /********************************** Item Details ********************************************************************************************/

    public function openitemtype(){

        $this->load->view('setup/itemtypeform');
    }

    /**** This function Insert Item type *********************/

    public function insertitemtype(){

        if(isset($_POST['item_type'])){

           // $this->form_validation->set_rules('item_id','Item ID','trim|xss_clean|required|alpha_numeric_spaces|callback_isItemIdExist');
            $this->form_validation->set_rules('item_mtid','Material ID','trim|xss_clean|required|callback_isMaterialIdExist');
            $this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required');
            $this->form_validation->set_rules('item_price','Item Price','trim|xss_clean|required');
            $this->form_validation->set_rules('item_stock','Item Stock','trim|xss_clean|required|alpha_numeric_spaces');


        if($this->form_validation->run() ==TRUE){

                $data = array(
                    //'item_id'=>$_POST['item_id'],
                    'item_mtid'=>$_POST['item_mtid'],
                    'item_name'=>$_POST['item_name'],
                    'item_price'=>$_POST['item_price'],
                    'item_stock'=>$_POST['item_stock'],
                );

                $rflag= $this->PICO_model->insertrec('items',$data);
                if(!$rflag){
                    //$this->logger->write_logmessage("insert","Trying to Add Item ID", "Item ID is not added ".$_POST['item_id']);
                    $this->logger->write_logmessage("insert","Trying to Add Material ID", "Material ID is not added ".$_POST['item_mtid']);
                    $this->logger->write_logmessage("insert","Trying to Add Item Name", "Item Name is not added ".$_POST['item_name']);
                    $this->logger->write_logmessage("insert","Trying to Add Item Price", "Item Price is not added ".$_POST['item_price']);
                    $this->logger->write_logmessage("insert","Trying to Add Item Stock ", "Item Stock is not added ".$_POST['item_stock']);

                    $this->session->set_flashdata('err_message','Error in adding Item details...'  , 'error');
                    redirect('picosetup/openitemtype');

                }
                else{
                   // $this->logger->write_logmessage("insert","Add Item ID", "Item ID".$_POST['item_id']." added  successfully...");
                    //$this->logger->write_dblogmessage("insert","Add Item ID", "Item ID".$_POST['item_id']." added  successfully...");

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
    // public function isItemIdExist($item_id) {

    //     $is_exist = $this->PICO_model->isduplicate('items','item_id',$item_id);
    //     if ($is_exist)
    //     {
    //         $this->form_validation->set_message('isItemIdExist', 'This Item ID already exist.');

    //         return false;
    //     }
    //     else 
    //     {
    //         return true;
    //     }
    // }

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

    /** This function is used to modify Item detail entries
     * @return type
    */
    public function itemtypedetails(){
        $data['result'] = $this->PICO_model->get_list('items');
        $this->logger->write_logmessage("view"," View Item List setting", "Item List setting details...");
        $this->logger->write_dblogmessage("view"," View Item List setting", "Item List setting details...");
        $this->load->view('setup/displayitemdetails',$data);
    }

    /*This function modify the Item Details*/
    public function edititemdetails($id) {

        $this->db4->from('items')->where('item_id',$id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
            redirect('picosetup/openitemtype');
        }
        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data['item_mtid'] = array(
                'name' => 'item_mtid',
                'id' => 'item_mtid',
                'size' => '40',
                'value' => $editeset_data->item_mtid,
                );

                $data['item_name'] = array(
                'name' => 'item_name',
                'id' => 'item_name',
                'size' => '40',
                'value' => $editeset_data->item_name,
                );

                $data['item_price'] = array(
                'name' => 'item_price',
                'id' => 'item_price',
                'size' => '40',
                'value' => $editeset_data->item_price,
                );

                $data['item_stock'] = array(
                'name' => 'item_stock',
                'id' => 'item_stock',
                'size' => '40',
                'value' => $editeset_data->item_stock,
                );
        
        $data['id'] = $id;
        /*Form Validation*/
            $this->form_validation->set_rules('item_mtid','Material ID','trim|xss_clean|required');
            $this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required');
            $this->form_validation->set_rules('item_price','Item Price','trim|xss_clean|required');
            $this->form_validation->set_rules('item_stock','Item Stock','trim|xss_clean|required|alpha_numeric_spaces');

        /* Re-populating form */
        if ($_POST)
        {
            $data['item_mtid']['value'] = $this->input->post('item_mtid', TRUE);
            $data['item_name']['value'] = $this->input->post('item_name', TRUE);
            $data['item_price']['value'] = $this->input->post('item_price', TRUE);
            $data['item_stock']['value'] = $this->input->post('item_stock', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/edititemdetails',$data);
            return;
        }

        else{

            $data_item_mtid = $this->input->post('item_mtid', TRUE);
            $data_item_name = $this->input->post('item_name', TRUE);
            $data_item_price = $this->input->post('item_price', TRUE);
            $data_item_stock = $this->input->post('item_stock', TRUE);
            $data_eid = $id;

            $logmessage = "";
            if($editeset_data->item_mtid != $data_item_mtid)
                $logmessage = "Material ID" .$editeset_data->item_mtid. " changed by " .$data_item_mtid;

            if($editeset_data->item_name!= $data_item_name)
                $logmessage = "Item Name" .$editeset_data->item_name. " changed by " .$data_item_name;

            if($editeset_data->item_price != $data_item_price)
                $logmessage = "Item Price" .$editeset_data->item_price. " changed by " .$data_item_price;

            if($editeset_data->item_stock != $data_item_stock)
                $logmessage = "Item Stock" .$editeset_data->item_stock. " changed by " .$data_item_stock;

            $update_data = array(
               'item_mtid' => $data_item_mtid,
               'item_name'=> $data_item_name,
               'item_price' => $data_item_price,
               'item_stock' => $data_item_stock,
            );

            $duplicate= $this->PICO_model->isduplicatemore('items',$update_data);
            if(!$duplicate){
                $roledflag=$this->PICO_model->updaterec('items', $update_data,'item_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","Edit Item details error", "Edit Item details . $logmessage ");
                $this->logger->write_dblogmessage("error","Edit Item details error", "Edit Item details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating Item details' . $logmessage . '.', 'error');
                $this->load->view('setup/displayitemdetails', $data);
                }
                else{
                $this->logger->write_logmessage("update","Edit Item details  Setting", "Edit Item details  Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit Item details  Setting", "Edit Item details  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Selected Item details updated successfully..');
                redirect('picosetup/itemtypedetails');
                }
            }
            else{
                $this->logger->write_logmessage("delete", "duplicate   Item details  record " . "[item_id:" .$id. "] deleted successfully.. ");
                $this->logger->write_dblogmessage("delete", "duplicate Item details  record" ." [item_id:" .$id. "] deleted successfully.. " );
                $this->session->set_flashdata("success", "Selected Item details already exists..." );
                redirect('picosetup/itemtypedetails');

            }
            
        }

    }

     /* This function is used to delete item record */
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
                    $this->logger->write_logmessage("delete", "Deleted   Item record " . "[item_id:" . $id . "] deleted successfully.. " );
                    $this->logger->write_dblogmessage("delete", "Deleted Item record" ." [item_id:" . $id . "] deleted successfully.. " );
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
            redirect("picosetup/purchasecommitteedetails");
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



/************************************************************************************************************************/


	//work started from here by (abhay831877@gmail.com)
	
	
	
	
	
	
	
	
	
	
	
	
	public function tender_type()
                {
                 if(isset($_POST['tender_type'])) {
                 $this->form_validation->set_rules('tt_name','tt Name','trim|xss_clean|required|alpha_numeric_spaces|callback_istender_typeExist');
                 $this->form_validation->set_rules('tt_desc','tt Desc','trim|xss_clean|required|alpha_numeric_spaces');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';

                 $data = array(
                'tt_name'=>$_POST['tt_name'],
                'tt_desc'=>$_POST['tt_desc'],
                 );
               
                $rflag=$this->PICO_model->insertrec('tender_type', $data);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add tender", "tender_type is not added ".$tt_name);
                    $this->logger->write_dblogmessage("insert","Trying to add tender", "tender_type is not added ".$tt_name);
                    $this->session->set_flashdata('err_message','Error in adding tender type setting - '  , 'error');
                    redirect('picosetup/tender_type');

                }
                else{
                    $this->logger->write_logmessage("insert","Add tender type Setting", "tender_type".$_POST['tt_name']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add tender type Setting", "tender_type ".$_POST['tt_name']."added  successfully...");
                    $this->session->set_flashdata("success", "tender add successfully...");
                    redirect("picosetup/displaytypeoftender");
        }
        }
        }
        $this->load->view('setup/tender_type');
    }

    /** This function check for duplicate tender
     * @return type
    */

    public function istender_typeExist($tt_name) {

        $is_exist = $this->PICO_model->isduplicate('tender_type','tt_name',$tt_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('istender_typeExist', 'tender type is already exist.');
            return false;
        }
        else {
            return true;
        }
        }
	
	
	
	     public function displaytypeoftender() {
        $data['result']= $this->PICO_model->get_list('tender_type');
        $this->logger->write_logmessage("view"," View tender type setting", "tender type setting details...");
        $this->logger->write_dblogmessage("view"," View tender type setting", "tender type setting details...");
        $this->load->view('setup/displaytypeoftender',$data);
        }

    /**This function Delete the tender records
     * @param type $id
     * @return type
     */

        public function deletetypeoftender($id) {
          $suname=$this->session->userdata['username'];
	      if((strcasecmp($suname,"admin"))==0)	
{
          $roledflag=$this->PICO_model->deleterow('tender_type','tt_id', $id);
          if(!$roledflag)
          {
          	$this->logger->write_message("error", "Error  in deleting tender type " ."[tt_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting tender type "." [tt_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in Deleting tender type - ', 'error');
            redirect('picosetup/displaytypeoftender');
           return;
          }
          else{
          $this->logger->write_logmessage("delete", "Deleted   tender type " . "[tt_id:" . $id . "] deleted successfully.. " );
           $this->logger->write_dblogmessage("delete", "Deleted tender type" ." [tt_id:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'tender type Deleted successfully...' );
            redirect('picosetup/displaytypeoftender');
          }       
 
          $this->load->view('setup/displaytypeoftender',$data);
}
else  redirect('picosetup/displaytypeoftender');
  }

    /**This function is used for update tender records
     * @param type $id
     * @return type
     */

    public function edittypeoftender($id) {

      $suname=$this->session->userdata['username'];
	   if((strcasecmp($suname,"admin"))==0)
			{	   
        $this->db4->from('tender_type')->where('tt_id', $id);
        $eset_data_q = $this->db4->get();
    
        $editeset_data = $eset_data_q->row();
        /* Form fields */

        $data['tt_name'] = array('name' => 'tt_name','id' => 'tt_name','size' => '40','value' => $editeset_data->tt_name,);
        $data['tt_desc'] = array('name' => 'tt_desc','id' => 'tt_desc','size' => '40','value' => $editeset_data->tt_desc,);
        $data['id'] = $id;
        
        /*Form Validation*/
        
        $this->form_validation->set_rules('tt_name','tt name','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('tt_desc','tt Desc','trim|xss_clean|required|alpha_numeric_spaces');
	
	    /* Re-populating form */
        if ($_POST)
        {
            $data['tt_name']['value'] = $this->input->post('tt_name', TRUE);
            $data['tt_desc']['value'] = $this->input->post('tt_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/edittypeoftender', $data);
            return;
        }
        else{

            $data_erole = $this->input->post('tt_name', TRUE);
            $data_eroledesc = $this->input->post('tt_desc', TRUE);
            $data_eid = $id;
            $logmessage = "";
            if($editeset_data->tt_name != $data_erole)
                $logmessage = "Add tender " .$editeset_data->tt_name. " changed by " .$data_erole;
            if($editeset_data->tt_desc != $data_eroledesc)
                $logmessage = "Add tender " .$editeset_data->tt_desc. " changed by " .$data_eroledesc;

            $update_data = array(
               'tt_name' => $data_erole,
               'tt_desc' => $data_eroledesc,
            );

        $roledflag=$this->PICO_model->updaterec('tender_type', $update_data,' tt_id', $data_eid);
        if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit tender Setting error", "Edit tender Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit tender Setting error", "Edit tender Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating tender - ' . $logmessage . '.', 'error');
                $this->load->view('setup/edittypeoftender', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit tender Setting", "Edit tender Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit tender Setting", "Edit tender Setting details. $logmessage ");
                $this->session->set_flashdata('success','tender  detail updated successfully..');
                redirect('picosetup/displaytypeoftender/');
                }
        }
		}
		else {
		redirect('picosetup/displaytypeoftender');
			}
    }//Add tender function end











//for vendor controller code is this......

    public function vendor() {


                 if(isset($_POST['vendor'])) {
                 	
               
                 $this->form_validation->set_rules('vendor_companyname','vendor company name','trim|xss_clean|required|alpha_numeric_spaces|callback_isvendorExist');
                 $this->form_validation->set_rules('vendor_address','vendor address','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_city','vendor city','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_pincode','vendor pincode','required|exact_length[6]');
                 $this->form_validation->set_rules('vendor_phone','vendor phone','required|numeric');
                 $this->form_validation->set_rules('vendor_type','vendor type','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_blackliststatus','vendor blacklist status','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_blacklistdate','vendor blacklist date','required');
                
                  	
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';

                 $data = array(
                'vendor_companyname'=>$_POST['vendor_companyname'],
                'vendor_address'=>$_POST['vendor_address'],
                'vendor_city'=>$_POST['vendor_city'],
                'vendor_pincode'=>$_POST['vendor_pincode'],
                'vendor_phone'=>$_POST['vendor_phone'],
                'vendor_type'=>$_POST['vendor_type'],
                'vendor_blackliststatus'=>$_POST['vendor_blackliststatus'],
                'vendor_blacklistdate'=>$_POST['vendor_blacklistdate'],
                 );
                $rflag=$this->PICO_model->insertrec('vendor', $data);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add vendor", "vendor is not added ".$vendor_companyname);
                    $this->logger->write_dblogmessage("insert","Trying to add vendor", "vendor is not added ".$vendor_companyname);
                    $this->session->set_flashdata('err_message','Error in adding vendor setting - '  , 'error');
                    redirect('picosetup/vendor');

                }
                else{
                    $this->logger->write_logmessage("insert","Add vendor Setting", "vendor".$_POST['vendor_companyname']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add vendor Setting", "vendor".$_POST['vendor_companyname']."added  successfully...");
                    $this->session->set_flashdata("success", "vendor add successfully...");
                    redirect("picosetup/displayvendor");
                }

            }
        }
        $this->load->view('setup/vendor');
    }

    /** This function check for duplicate vendor
     * @return type
    */

    public function isvendorExist($vendor_companyname) {

        $is_exist = $this->PICO_model->isduplicate('vendor','vendor_companyname',$vendor_companyname);
        if ($is_exist)
        {
            $this->form_validation->set_message('isvendorExist', 'vendor is already exist.');
            return false;
        }
        else {
            return true;
        }
    }
	
	
	
	   public function displayvendor() {
	   	
	   	
        $data['result'] = $this->PICO_model->get_list('vendor');
        $this->logger->write_logmessage("view"," View vendor setting", "vendor setting details...");
        $this->logger->write_dblogmessage("view"," View vendor setting", "vendor setting details...");
        $this->load->view('setup/displayvendor',$data);
       }

    /**This function Delete the vendor records
     * @param type $id
     * @return type
     */

         public function deletevendor($id) {


	   $suname=$this->session->userdata['username'];
	   if((strcasecmp($suname,"admin"))==0)
      
      
        {  $roledflag=$this->PICO_model->deleterow('vendor','vendor_id', $id);
          if(!$roledflag)
          {
          	$this->logger->write_message("error", "Error  in deleting vendor " ."[vendor_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting vendor "." [vendor_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in Deleting vendor - ', 'error');
            redirect('picosetup/displayvendor');
           return;
          }
        else{
          $this->logger->write_logmessage("delete", "Deleted   vendor_type " . "[vendor_id:" . $id . "] deleted successfully.. " );
           $this->logger->write_dblogmessage("delete", "Deleted vendor_type" ." [vendor_id:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'vendor type Deleted successfully...' );
            redirect('picosetup/displayvendor');
        }
        $this->load->view('setup/displayvendor',$data);
			}
else {
   redirect('picosetup/displayvendor');
}
	
        }

    /**This function is used for update vendor records
     * @param type $id
     * @return type
     */

    public function editvendor($id) {
          
         $suname=$this->session->userdata['username'];
	      if((strcasecmp($suname,"admin"))==0)  
        {  
        $this->db4->from('vendor')->where('vendor_id', $id);
        $eset_data_q = $this->db4->get();
     
        $editeset_data = $eset_data_q->row();
       
       /* Form fields */
   
   
   
            $data['vendor_companyname'] = array('name' => 'vendor_companyname','id' => 'vendor_companyname','size' => '40','value' => $editeset_data->vendor_companyname,);
            $data['vendor_address'] = array('name' => 'vendor_address','id' => 'vendor_address','size' => '40','value' => $editeset_data->vendor_address,);
            $data['vendor_city'] = array('name' => 'vendor_city','id' => 'vendor_city','size' => '40','value' => $editeset_data->vendor_city,);
            $data['vendor_pincode'] = array('name' => 'vendor_pincode','id' => 'vendor_pincode','size' => '40','value' => $editeset_data->vendor_pincode,);
            $data['vendor_phone'] = array('name' => 'vendor_phone','id' => 'vendor_phone','size' => '40','value' => $editeset_data->vendor_phone,);
            $data['vendor_type'] = array('name' => 'vendor_type','id' => 'vendor_type','size' => '40','value' => $editeset_data->vendor_type,);
            $data['vendor_blackliststatus'] = array('name' => 'vendor_blackliststatus','id' => 'vendor_blackliststatus','size' => '40','value' => $editeset_data->vendor_blackliststatus,);
            $data['vendor_blacklistdate'] = array('name' => 'vendor_blacklistdate','id' => 'vendor_blacklistdate','size' => '40','value' => $editeset_data->vendor_blacklistdate,);
                
                
                
                
                
     
     
      $data['id'] = $id;
        /*Form Validation*/
                 
                 $this->form_validation->set_rules('vendor_companyname','vendor company name','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_address','vendor address','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_city','vendor city','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_pincode','vendor pincode','required');
                 $this->form_validation->set_rules('vendor_phone','vendor phone','required|numeric');
                 $this->form_validation->set_rules('vendor_type','vendor type','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_blackliststatus','vendor blacklist status','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_blacklistdate','vendor blacklist date','required');
                
                
	    
	    
	    
	    
	    
	    
	    
	    /* Re-populating form */
        if ($_POST)
        {
        	
        	
            $data['vendor_companyname']['value'] = $this->input->post('vendor_companyname', TRUE);
            $data['vendor_address']['value'] = $this->input->post('vendor_address', TRUE);
       
            $data['vendor_city']['value'] = $this->input->post('vendor_city', TRUE);
            $data['vendor_pincode']['value'] = $this->input->post('vendor_pincode', TRUE);
            $data['vendor_phone']['value'] = $this->input->post('vendor_phone', TRUE);
            $data['vendor_type']['value'] = $this->input->post('vendor_type', TRUE);
            $data['vendor_blackliststatus']['value'] = $this->input->post('vendor_blackliststatus', TRUE);
            $data['vendor_blacklistdate']['value'] = $this->input->post('vendor_blacklistdate', TRUE);
       
       
       
       
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editvendor', $data);
            return;
        }
        else{

            $data_a = $this->input->post('vendor_companyname', TRUE);
            $data_b = $this->input->post('vendor_address', TRUE);
            $data_c = $this->input->post('vendor_city', TRUE);
            $data_d = $this->input->post('vendor_pincode', TRUE);
            $data_e = $this->input->post('vendor_phone', TRUE);
            $data_f = $this->input->post('vendor_type', TRUE);
            $data_g = $this->input->post('vendor_blackliststatus', TRUE);
            $data_h = $this->input->post('vendor_blacklistdate', TRUE);
            
            
            $data_eid = $id;
           
            $logmessage = "";
           
            if($editeset_data->vendor_companyname != $data_a)
                $logmessage = "Add vendor " .$editeset_data->vendor_companyname. " changed by " .$data_a;
            if($editeset_data->vendor_address != $data_b)
                $logmessage = "Add vendor " .$editeset_data->vendor_address. " changed by " .$data_b;
            if($editeset_data->vendor_city != $data_c)
                $logmessage = "Add vendor " .$editeset_data->vendor_city. " changed by " .$data_c;
            if($editeset_data->vendor_pincode != $data_d)
                $logmessage = "Add vendor " .$editeset_data->vendor_pincode. " changed by " .$data_d;
            if($editeset_data->vendor_phone != $data_e)
                $logmessage = "Add vendor " .$editeset_data->vendor_phone. " changed by " .$data_e;
            if($editeset_data->vendor_type != $data_f)
                $logmessage = "Add vendor " .$editeset_data->vendor_type. " changed by " .$data_f;
            if($editeset_data->vendor_blackliststatus != $data_g)
                $logmessage = "Add vendor " .$editeset_data->vendor_blackliststatus. " changed by " .$data_g;
            if($editeset_data->vendor_blacklistdate != $data_h)
                $logmessage = "Add vendor " .$editeset_data->vendor_blacklistdate. " changed by " .$data_h;

           
           
           
           
            $update_data = array(
              'vendor_companyname' => $data_a,
              'vendor_address' => $data_b,
              'vendor_city' => $data_c,
              'vendor_pincode' => $data_d,
              'vendor_phone' => $data_e,
              'vendor_type' => $data_f,
              'vendor_blackliststatus' => $data_g,
              'vendor_blacklistdate' => $data_h,
            );

        
        
        
        
        $roledflag=$this->PICO_model->updaterec('vendor', $update_data,'vendor_id', $data_eid);
        if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit vendor Setting error", "Edit vendor Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit vendor Setting error", "Edit vendor Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating vendor - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editvendor', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit vendor Setting", "Edit vendor Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit vendor Setting", "Edit vendor Setting details. $logmessage ");
                $this->session->set_flashdata('success','vendor  detail updated successfully..');
                redirect('picosetup/displayvendor/');
              
                }
        }
		}
   else redirect('picosetup/displayvendor');
  
  
    }
    
    
    
    
    
    
    
    
    
    
    
    //for rid controller code is this......

    public function rid() {


                 if(isset($_POST['rid'])) {
                 	
               
                 $this->form_validation->set_rules('rid_ppid','rid co name','trim|xss_clean|required|callback_isridExist');
                 $this->form_validation->set_rules('rid_itemdes','rid itemdes','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('rid_itemstock','rid itemstock','required');
                 $this->form_validation->set_rules('rid_itemqtyreq','rid itemqtyreq','required');
                 $this->form_validation->set_rules('rid_itemunitp','rid itemunitp','required|numeric');
                 $this->form_validation->set_rules('rid_itemgstapply','rid itemgstapply','required');
                 $this->form_validation->set_rules('rid_gst','rid gst','required|alpha_numeric|exact_length[15]');
                 $this->form_validation->set_rules('rid_itemtotcost','rid tt cost','required');
                
                  	
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';

                 $data = array(
                'rid_ppid'=>$_POST['rid_ppid'],
                'rid_itemdes'=>$_POST['rid_itemdes'],
                'rid_itemstock'=>$_POST['rid_itemstock'],
                'rid_itemqtyreq'=>$_POST['rid_itemqtyreq'],
                'rid_itemunitp'=>$_POST['rid_itemunitp'],
                'rid_itemgstapply'=>$_POST['rid_itemgstapply'],
                'rid_gst'=>$_POST['rid_gst'],
                'rid_itemtotcost'=>$_POST['rid_itemtotcost'],
                 );
                $rflag=$this->PICO_model->insertrec('required_item_details', $data);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add rid", "rid is not added ".$rid_ppid);
                    $this->logger->write_dblogmessage("insert","Trying to add rid", "rid is not added ".$rid_ppid);
                    $this->session->set_flashdata('err_message','Error in adding rid setting - '  , 'error');
                    redirect('picosetup/rid');

                }
                else{
                    $this->logger->write_logmessage("insert","Add rid Setting", "rid".$_POST['rid_ppid']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add rid Setting", "rid".$_POST['rid_ppid']."added  successfully...");
                    $this->session->set_flashdata("success", "rid add successfully...");
                    redirect("picosetup/displayrid");
                }

            }
        }
        $this->load->view('setup/rid');
    }

    /** This function check for duplicate rid
     * @return itemgstapply
    */

    public function isridExist($rid_ppid) {

        $is_exist = $this->PICO_model->isduplicate('required_item_details','rid_ppid',$rid_ppid);
        if ($is_exist)
        {
            $this->form_validation->set_message('isridExist', 'rid is already exist.');
            return false;
        }
        else {
            return true;
        }
    }
	
	
	
	   public function displayrid() {
	   	
	   	
        $data['result'] = $this->PICO_model->get_list('required_item_details');
        $this->logger->write_logmessage("view"," View rid setting", "rid setting details...");
        $this->logger->write_dblogmessage("view"," View rid setting", "rid setting details...");
        $this->load->view('setup/displayrid',$data);
       }

    /**This function Delete the rid records
     * @param itemgstapply $id
     * @return itemgstapply
     */

         public function deleterid($id) {


	   $suname=$this->session->userdata['username'];
	   if((strcasecmp($suname,"admin"))==0)
      
      
        {  $roledflag=$this->PICO_model->deleterow('required_item_details','rid_id',$id);//required_item_details
          if(!$roledflag)
          {
          	$this->logger->write_message("error", "Error  in deleting rid " ."[rid_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting rid "." [rid_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in Deleting rid - ', 'error');
            redirect('picosetup/displayrid');
           return;
          }
        else{
          $this->logger->write_logmessage("delete", "Deleted   rid  " . "[rid_id:" . $id . "] deleted successfully.. " );
           $this->logger->write_dblogmessage("delete", "Deleted rid " ." [rid_id:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'required items details Deleted successfully...' );
            redirect('picosetup/displayrid');
        }
        $this->load->view('setup/displayrid',$data);
			}
else {
   redirect('picosetup/displayrid');
}
	
        }

    /**This function is used for update rid records
     * @param itemgstapply $id
     * @return itemgstapply
     */

    public function editrid($id) {
          
         $suname=$this->session->userdata['username'];
	      if((strcasecmp($suname,"admin"))==0)  
        {  
        $this->db4->from('required_item_details')->where('rid_id', $id);
        $eset_data_q = $this->db4->get();
     
        $editeset_data = $eset_data_q->row();
       
       /* Form fields... */
   
   
   
            $data['rid_ppid'] = array('name' => 'rid_ppid','id' => 'rid_ppid','size' => '40','value' => $editeset_data->rid_ppid,);
            $data['rid_itemdes'] = array('name' => 'rid_itemdes','id' => 'rid_itemdes','size' => '40','value' => $editeset_data->rid_itemdes,);
            $data['rid_itemstock'] = array('name' => 'rid_itemstock','id' => 'rid_itemstock','size' => '40','value' => $editeset_data->rid_itemstock,);
            $data['rid_itemqtyreq'] = array('name' => 'rid_itemqtyreq','id' => 'rid_itemqtyreq','size' => '40','value' => $editeset_data->rid_itemqtyreq,);
            $data['rid_itemunitp'] = array('name' => 'rid_itemunitp','id' => 'rid_itemunitp','size' => '40','value' => $editeset_data->rid_itemunitp,);
            $data['rid_itemgstapply'] = array('name' => 'rid_itemgstapply','id' => 'rid_itemgstapply','size' => '40','value' => $editeset_data->rid_itemgstapply,);
            $data['rid_gst'] = array('name' => 'rid_gst','id' => 'rid_gst','size' => '40','value' => $editeset_data->rid_gst,);
            $data['rid_itemtotcost'] = array('name' => 'rid_itemtotcost','id' => 'rid_itemtotcost','size' => '40','value' => $editeset_data->rid_itemtotcost,);
                
                
                 
                
                
     
     
      $data['id'] = $id;
        /*Form Validation*/
                 
               $this->form_validation->set_rules('rid_ppid','rid company ','trim|xss_clean|required');
                 $this->form_validation->set_rules('rid_itemdes','rid itemdes','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('rid_itemstock','rid itemstock','required');
                 $this->form_validation->set_rules('rid_itemqtyreq','rid itemqtyreq','required');
                 $this->form_validation->set_rules('rid_itemunitp','rid itemunitp','required|numeric');
                 $this->form_validation->set_rules('rid_itemgstapply','rid itemgstapply','required');
                 $this->form_validation->set_rules('rid_gst','rid gst','required|alpha_numeric|exact_length[15]');
                 $this->form_validation->set_rules('rid_itemtotcost','rid tt cost','required');
                
                
	    
	    
	    
	    
	    
	    
	    
	    /* Re-populating form */
        if ($_POST)
        {
        	
        	
            $data['rid_ppid']['value'] = $this->input->post('rid_ppid', TRUE);
            $data['rid_itemdes']['value'] = $this->input->post('rid_itemdes', TRUE);
       
            $data['rid_itemstock']['value'] = $this->input->post('rid_itemstock', TRUE);
            $data['rid_itemqtyreq']['value'] = $this->input->post('rid_itemqtyreq', TRUE);
            $data['rid_itemunitp']['value'] = $this->input->post('rid_itemunitp', TRUE);
            $data['rid_itemgstapply']['value'] = $this->input->post('rid_itemgstapply', TRUE);
            $data['rid_gst']['value'] = $this->input->post('rid_gst', TRUE);
            $data['rid_itemtotcost']['value'] = $this->input->post('rid_itemtotcost', TRUE);
       
       
       
       
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editrid', $data);
            return;
        }
        else{

            $data_a= $this->input->post('rid_ppid', TRUE);
            $data_b= $this->input->post('rid_itemdes', TRUE);
            $data_c= $this->input->post('rid_itemstock', TRUE);
            $data_d= $this->input->post('rid_itemqtyreq', TRUE);
            $data_e= $this->input->post('rid_itemunitp', TRUE);
            $data_f= $this->input->post('rid_itemgstapply', TRUE);
            $data_g= $this->input->post('rid_gst', TRUE);
            $data_h= $this->input->post('rid_itemtotcost', TRUE);
            
            
            $data_eid = $id;
           
            $logmessage = "";
           
            if($editeset_data->rid_ppid != $data_a)
                $logmessage = "Add rid " .$editeset_data->rid_ppid. " changed by " .$data_a;
            if($editeset_data->rid_itemdes != $data_b)
                $logmessage = "Add rid " .$editeset_data->rid_itemdes. " changed by " .$data_b;
            if($editeset_data->rid_itemstock != $data_c)
                $logmessage = "Add rid " .$editeset_data->rid_itemstock. " changed by " .$data_c;
            if($editeset_data->rid_itemqtyreq != $data_d)
                $logmessage = "Add rid " .$editeset_data->rid_itemqtyreq. " changed by " .$data_d;
            if($editeset_data->rid_itemunitp != $data_e)
                $logmessage = "Add rid " .$editeset_data->rid_itemunitp. " changed by " .$data_e;
            if($editeset_data->rid_itemgstapply != $data_f)
                $logmessage = "Add rid " .$editeset_data->rid_itemgstapply. " changed by " .$data_f;
            if($editeset_data->rid_gst != $data_g)
                $logmessage = "Add rid " .$editeset_data->rid_gst. " changed by " .$data_g;
            if($editeset_data->rid_itemtotcost != $data_h)
                $logmessage = "Add rid " .$editeset_data->rid_itemtotcost. " changed by " .$data_h;

           
           
           
           
            $update_data = array(
              'rid_ppid' => $data_a,
              'rid_itemdes' => $data_b,
              'rid_itemstock' => $data_c,
              'rid_itemqtyreq' => $data_d,
              'rid_itemunitp' => $data_e,
              'rid_itemgstapply' => $data_f,
              'rid_gst' => $data_g,
              'rid_itemtotcost' => $data_h,
            );

        
        
        
        
        $roledflag=$this->PICO_model->updaterec('required_item_details', $update_data,'rid_id', $data_eid);
        if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit rid Setting error", "Edit rid Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit rid Setting error", "Edit rid Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating required items details - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editrid', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit rid Setting", "Edit rid Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit rid Setting", "Edit rid Setting details. $logmessage ");
                $this->session->set_flashdata('success','required item details updated successfully..');
                redirect('picosetup/displayrid/');
              
                }
        }
		}
   else redirect('picosetup/displayrid');
  
  
    }
    
  
    public function tenderform()
    {
     
        $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
        
        $this->load->view('setup/tenderform');
    }



    /************************************* Purchase Committee Selection *****************************************************/


    /*** This Function is used to open form ***/
    public function opencommitteeselection(){
        $data['dept']= $this->common_model->get_list('department');
        $data['result']= $this->PICO_model->get_list('purchase_com_form_rule');
        $this->load->view('setup/committeeselectionform',$data);
    }	

    /*** This Function is used to insert details ***/
    public function insertpurchselectioncomm(){

    if(isset($_POST['purch_selection'])){

            $this->form_validation->set_rules('pc_purchasethrough','Purchase Through','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_purchpricelimit','Estimated Price','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_dept','Department','required');
            $this->form_validation->set_rules('pc_convener','Convener','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_rep1','1st Representative','required');
            $this->form_validation->set_rules('pc_rep2','2nd Representative');
            $this->form_validation->set_rules('pc_rep3','3rd Representative');
            $this->form_validation->set_rules('pc_rep4','4th Representative');
            $this->form_validation->set_rules('pc_rep5','5th Representative');
            $this->form_validation->set_rules('pc_appauth','Authority','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_desc','Description','trim|xss_clean|required');

            if($this->form_validation->run()==TRUE){

                $data = array(
                'pc_purchasethrough'=>$_POST['pc_purchasethrough'],
                'pc_purchpricelimit'=>$_POST['pc_purchpricelimit'],
                'pc_dept'=>$_POST['pc_dept'],
                'pc_convener'=>$_POST['pc_convener'],
                'pc_rep1'=>$_POST['pc_rep1'],
                'pc_rep2'=>$_POST['pc_rep2'],
                'pc_rep3'=>$_POST['pc_rep3'],
                'pc_rep4'=>$_POST['pc_rep4'],
                'pc_rep5'=>$_POST['pc_rep5'],
                'pc_appauth'=>$_POST['pc_appauth'],
                'pc_desc'=>$_POST['pc_desc'],
                'pc_createdby'=>$this->session->userdata('username'),
                'pc_creationdate'=>date('Y-m-d')

                 );

                $rflag=$this->PICO_model->insertrec('purchase_committee', $data);
                if(!$rflag){
                    $this->logger->write_logmessage("insert","Trying to add purchase committee", "purchase committee is not added ".$pc_id);
                    $this->logger->write_dblogmessage("insert","Trying to add purchase committee", "purchase committee is not added ".$pc_id);
                    $this->session->set_flashdata('err_message','Error in adding purchase committee'  , 'error');
                    redirect('picosetup/opencommitteeselection');
                }
                else
                {
                    $this->logger->write_logmessage("insert","Add Purchase Committee Setting", "Purchase Committee".$_POST['pc_id']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Purchase Committee Setting", "Purchase Committee".$_POST['pc_id']."added  successfully...");
                    $this->session->set_flashdata("success", "Purchase Committee Added successfully...");
                    redirect("picosetup/displaycommitteeselection");
                }
            }
          
        }
    }

    /*** This Function is used to open detailed table ***/
    public function displaycommitteeselection(){
        $data['committee'] = $this->PICO_model->get_list('purchase_committee');
        $this->logger->write_logmessage("view"," View store setting", "Store setting details...");
        $this->logger->write_dblogmessage("view"," View store setting", "Store setting details...");
        $this->load->view('setup/displaycommitteeselection',$data);
    }

    /* this function is used for delete record */
    public function deletecommitteeselection($id) { 

        $mt_data=$this->PICO_model->get_listrow('purchase_committee','pc_id', $id);
        if ($mt_data->num_rows() < 1)
        {   

            redirect('setup/committeeselectionform');
        }
            $fmdflag=$this->PICO_model->deleterow('purchase_committee','pc_id', $id);
            if(!$fmdflag)
            {
                $this->logger->write_message("error", "Error  in deleting this record " ."[pc_id:" . $id . "]");
                    $this->logger->write_dbmessage("error", "Error  in deleting this record "." [pc_id:" . $id . "]");
                    $this->session->set_flashdata('err_message', 'Error in Deleting this record - ', 'error');
                    redirect('picosetup/displaycommitteeselection');
                //return;
              }
              else{
                    $this->logger->write_logmessage("delete", "Deleted   selected record " . "[pc_id:" . $id . "] successfully.. " );
                    $this->logger->write_dblogmessage("delete", "Deleted selected record" ." [pc_id:" . $id . "] successfully.. " );
                    $this->session->set_flashdata("success", "Selected record deleted successfully..." );
                    redirect('picosetup/displaycommitteeselection');
            }
          $this->load->view('setup/displaycommitteeselection',$data);
    }

     /**** This function is used to modify committee selection **********/   
     public function editcommitteeselection($id) {

        $this->db4->from('purchase_committee')->where('pc_id', $id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
            redirect('setup/editcommitteeselection');
        }

        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data['pc_purchasethrough'] = array(
                'name' => 'pc_purchasethrough',
                'id' => 'pc_purchasethrough',
                'size' => '40',
                'value' => $editeset_data->pc_purchasethrough,
                );

                $data['pc_purchpricelimit'] = array(
                'name' => 'pc_purchpricelimit',
                'id' => 'pc_purchpricelimit',
                'size' => '40',
                'value' => $editeset_data->pc_purchpricelimit,
                );

                $data['pc_dept'] = array(
                'name' => 'pc_dept',
                'id' => 'pc_dept',
                'size' => '40',
                'value' => $editeset_data->pc_dept,
                );

                $data['pc_convener'] = array(
                'name' => 'pc_convener',
                'id' => 'pc_convener',
                'size' => '20',
                'value' => $editeset_data->pc_convener,
                );

                $data['pc_rep1'] = array(
                'name' => 'pc_rep1',
                'id' => 'pc_rep1',
                'size' => '40',
                'value' => $editeset_data->pc_rep1,
                );

                $data['pc_rep2'] = array(
                'name' => 'pc_rep2',
                'id' => 'pc_rep2',
                'size' => '40',
                'value' => $editeset_data->pc_rep2,
                );

                $data['pc_rep3'] = array(
                'name' => 'pc_rep3',
                'id' => 'pc_rep3',
                'size' => '40',
                'value' => $editeset_data->pc_rep3,
                );

                $data['pc_rep4'] = array(
                'name' => 'pc_rep4',
                'id' => 'pc_rep4',
                'size' => '40',
                'value' => $editeset_data->pc_rep4,
                );

                $data['pc_rep5'] = array(
                'name' => 'pc_rep5',
                'id' => 'pc_rep5',
                'size' => '40',
                'value' => $editeset_data->pc_rep5,
                );

                 $data['pc_appauth'] = array(
                'name' => 'pc_appauth',
                'id' => 'pc_appauth',
                'size' => '40',
                'value' => $editeset_data->pc_appauth,
                );

                $data['pc_desc'] = array(
                'name' => 'pc_desc',
                'id' => 'pc_desc',
                'size' => '40',
                'value' => $editeset_data->pc_desc,
                );

        $data['id'] = $id;
        /*Form Validation*/
            $this->form_validation->set_rules('pc_purchasethrough','Purchase Through','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_purchpricelimit','Estimated Price','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_dept','Department','required');
            $this->form_validation->set_rules('pc_convener','Convener','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_rep1','1st Representative','required');
            $this->form_validation->set_rules('pc_rep2','2nd Representative');
            $this->form_validation->set_rules('pc_rep3','3rd Representative');
            $this->form_validation->set_rules('pc_rep4','4th Representative');
            $this->form_validation->set_rules('pc_rep5','5th Representative');
            $this->form_validation->set_rules('pc_appauth','Authority','trim|xss_clean|required');
            $this->form_validation->set_rules('pc_desc','Description','trim|xss_clean|required');

        /* Re-populating form */
        if ($_POST)
        {
            $data['pc_purchasethrough']['value'] = $this->input->post('pc_purchasethrough', TRUE);
            $data['pc_purchpricelimit']['value'] = $this->input->post('pc_purchpricelimit', TRUE);
            $data['pc_dept']['value'] = $this->input->post('pc_dept', TRUE);
            $data['pc_convener']['value'] = $this->input->post('pc_convener', TRUE);
            $data['pc_rep1']['value'] = $this->input->post('pc_rep1', TRUE);
            $data['pc_rep2']['value'] = $this->input->post('pc_rep2', TRUE);
            $data['pc_rep3']['value'] = $this->input->post('pc_rep3', TRUE);
            $data['pc_rep4']['value'] = $this->input->post('pc_rep4', TRUE);
            $data['pc_rep5']['value'] = $this->input->post('pc_rep5', TRUE);
            $data['pc_appauth']['value'] = $this->input->post('pc_appauth', TRUE);
            $data['pc_desc']['value'] = $this->input->post('pc_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editcommitteeselection', $data);
            return;
        }
        else{

            $data_purchasethrough = $this->input->post('pc_purchasethrough', TRUE);
            $data_purchpricelimit = $this->input->post('pc_purchpricelimit', TRUE);
            $data_dept = $this->input->post('pc_dept', TRUE);
            $data_convener = $this->input->post('pc_convener', TRUE);
            $data_rep1 = $this->input->post('pc_rep1', TRUE);
            $data_rep2 = $this->input->post('pc_rep2', TRUE);
            $data_rep3 = $this->input->post('pc_rep3', TRUE);
            $data_rep4 = $this->input->post('pc_rep4', TRUE);
            $data_rep5 = $this->input->post('pc_rep5', TRUE);
            $data_appauth = $this->input->post('pc_appauth', TRUE);
            $data_desc = $this->input->post('pc_desc', TRUE);
            $data_eid = $id;

            $logmessage = "";
            if($editeset_data->pc_purchasethrough != $data_purchasethrough)
                $logmessage = "Purchase Through" .$editeset_data->pc_purchasethrough. " changed by " .$data_purchasethrough;

            if($editeset_data->pc_purchpricelimit!= $data_purchpricelimit)
                $logmessage = "Estimated Price" .$editeset_data->pc_purchpricelimit. " changed by " .$data_purchpricelimit;

            if($editeset_data->pc_dept != $data_dept)
                $logmessage = "Department " .$editeset_data->pc_dept. " changed by " .$data_dept;

            if($editeset_data->pc_convener != $data_convener)
                $logmessage = "Convener" .$editeset_data->pc_convener. " changed by " .$data_convener;

            if($editeset_data->pc_rep1 != $data_rep1)
                $logmessage = "Representative 1" .$editeset_data->pc_rep1. " changed by " .$data_rep1;

            if($editeset_data->pc_rep2 != $data_rep2)
                $logmessage = "Representative 2" .$editeset_data->pc_rep2. " changed by " .$data_rep2;

            if($editeset_data->pc_rep3 != $data_rep3)
                $logmessage = "Representative 3" .$editeset_data->pc_rep3. " changed by " .$data_rep3;

            if($editeset_data->pc_rep4 != $data_rep4)
                $logmessage = "Representative 4" .$editeset_data->pc_rep4. " changed by " .$data_rep4;

            if($editeset_data->pc_rep5 != $data_rep5)
                $logmessage = "Representative 5" .$editeset_data->pc_rep5. " changed by " .$data_rep5;

            if($editeset_data->pc_appauth != $data_appauth)
                $logmessage = "Authority" .$editeset_data->pc_appauth. " changed by " .$data_appauth;

            if($editeset_data->pc_desc != $data_desc)
                $logmessage = "Description" .$editeset_data->pc_desc. " changed by " .$data_desc;

            $update_data = array(
               'pc_purchasethrough' => $data_purchasethrough,
               'pc_purchpricelimit'=> $data_purchpricelimit,
               'pc_dept' => $data_dept,
               'pc_convener' => $data_convener,
               'pc_rep1' => $data_rep1,
               'pc_rep2' => $data_rep2,
               'pc_rep3' => $data_rep3,
               'pc_rep4' => $data_rep4,
               'pc_rep5' => $data_rep5,
               'pc_appauth' => $data_appauth,
               'pc_desc' => $data_desc,
               'pc_modifiedby'=> $this->session->userdata('username'),
               'pc_modifieddate'=>date('Y-m-d')
            );

                $roledflag=$this->PICO_model->updaterec('purchase_committee', $update_data,' pc_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","Edit Committee Selection Setting error", "Edit Committee Selection Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit Committee Selection Setting error", "Edit Committee Selection Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating Committee Selection' . $logmessage . '.', 'error');
                $this->load->view('setup/displaycommitteeselection', $data);
                }
                else{
                $this->logger->write_logmessage("update","Edit Committee Selection Setting", "Edit Committee Selection Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit Committee SelectionSetting", "Edit Committee Selection Setting details. $logmessage ");
                $this->session->set_flashdata('success','Committee Selection details updated successfully..');
                redirect('picosetup/displaycommitteeselection');
                }
            
        }

    }
 

        
  
    
    
}

















