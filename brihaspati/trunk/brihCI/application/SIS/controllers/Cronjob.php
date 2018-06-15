
<?php

 defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Cronejob.php
 * @author Manorama Pal (palseema30@gmail.com) Autoretirement 
 */
 
class Cronjob extends CI_Controller
{

 
    function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index(){
       
    }
    /* this function is created to check staff retire date for the staff retirement process */
    public function autoretirement(){ 
        $today = date("Y-m-d H:i:s");//please check and change according to the database format $today = date("Y/m/d H:i:s")or $today = date("Y-m-d H:i:s")
       // echo $today;die;
        $selectfield ="emp_id,emp_code,emp_userid,emp_desig_code,emp_dob,emp_dor,emp_doj,emp_leaving,emp_email";
        $whdata = array ('emp_leaving' => NULL,'emp_dor <=' =>$today);
        $dordata = $this->sismodel->get_listspficemore('employee_master',$selectfield,$whdata);
        if(!empty($dordata)){ 
            foreach($dordata as $combdata){
                $empdata = array(
                    'sre_empid' => $combdata->emp_id,
                    'sre_empcode' => $combdata->emp_code,
                    'sre_empemailid' => $combdata->emp_email,
                    'sre_doj'  => $combdata->emp_doj,
                    'sre_dor'  => $combdata->emp_dor,
                    'sre_reason' =>'superannuation' ,
                    'sre_reasondate' =>$today,
                    'sre_remark' => '',	
                    'sre_creatorid' => $this->session->userdata('username'),
                    'sre_creatordate' => date('y-m-d'),
                    'sre_modifierid' => $this->session->userdata('username'), 
                    'sre_modifydate' => date('y-m-d')
                );
                $retupdateflag=$this->sismodel->insertrec('staff_retirement', $empdata);
                if(!$retupdateflag)
                {
                    $this->logger->write_logmessage("error"," Trying to insert in  staff retirement record", "staff retirement record is not added. Employee PF No' = $combdata->emp_code$empcode ");
                    $this->logger->write_dblogmessage("error","Trying to insert in  staff retirement record", "staff retirement record is not added. Employee PF No' = $combdata->emp_code$empcode ");
                }    
                $empup_data = array(
                  'emp_leaving' => 'superannuation' 
                );
                $empmasterflag=$this->sismodel->updaterec('employee_master', $empup_data, 'emp_id', $combdata->emp_id);
                if(!$empmasterflag){
                    $this->logger->write_logmessage("error","Error in update employee profile ", "Error in employee profile updation" );
                    $this->logger->write_dblogmessage("error","Error in update employee profile", "Error in employee profile updation");
                }    
            
            }//foreach
        }//ifdordata
        else{
            $this->logger->write_logmessage("error"," Trying to insert in  staff retirement record", "staff retirement record is not added may be list is empty.");
            $this->logger->write_dblogmessage("error","Trying to insert in  staff retirement record", "staff retirement record is not added may be list is empty.");
        }
        
    }
   
}    
    

