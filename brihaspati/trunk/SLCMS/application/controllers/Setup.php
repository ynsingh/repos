<?php

/* 
 * @name Setup.php
 * @author Manorama Pal(palseema30@gmail.com)
 * @author Sharad Singh(sharad23nov@yahoo.com)
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Setup extends CI_Controller
{
    function __construct() {
        parent::__construct();
 
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
        
//        $this->load->helper('url');
        $this->dispemailsetting();
        
    }
    /** This function add email setting
     * @return type
     */
    
    public function emailsetting() {
              
 //       $this->load->library('form_validation'); 
        
        if(isset($_POST['emailsetting'])) {
        
            $this->form_validation->set_rules('emailprotocol','Emailprotocol','trim|xss_clean|required|alpha');
            $this->form_validation->set_rules('emailhost','Emailhost','trim|xss_clean|required');
            $this->form_validation->set_rules('emailport','Emailport','trim|xss_clean|required|min_length[2]|max_length[5]|is_numeric',
                array ('required' => 'insert numeric value only'));
            $this->form_validation->set_rules('username','Username','trim|xss_clean|required');
            $this->form_validation->set_rules('password','Password','trim|xss_clean|required|alpha_numeric');
            $this->form_validation->set_rules('sendername','Sendername','trim|xss_clean|required');
            //if form validation true
            if($this->form_validation->run()==TRUE){
            
                $data = array(
                    'emailprotocol'=>$_POST['emailprotocol'],
                    'emailhost'=>$_POST['emailhost'],
                    'emailport'=>$_POST['emailport'],
                    'username'=>$_POST['username'],
                    'password'=>$_POST['password'],
                    'sendername'=>$_POST['sendername'],
                    'creatorid'=> $this->session->userdata('username'),  
                    'createdate'=>date('y-m-d'),
                    'modifierid'=>$this->session->userdata('username'),
                    'modifidate'=>date('y-m-d')
                );
                $this->db->trans_start();
                if ( ! $this->db->insert('email_setting', $data))
                {
                    $this->db->trans_rollback();
                    log_message('error', "Error  in adding  Email Setting record called " . $eset_data->username . " [id:" . $id . "]");
                    log_message('debug', ' Problem in adding Email setting' . $eset_data->username );
                    $this->session->set_flashdata('Error in adding email setting - ' . $eset_data->username . '.', 'error');
                    log_message("info", "Error  in adding  Email Setting record called " . $eset_data->username . " [id:" . $id . "]");
                    redirect('setup/emailsetting');

                }
                else{
                    $this->db->trans_complete();
                    $this->logger->write_logmessage("insert","Add Email Setting", "Email setting record insert successfully...");
                    $this->session->set_flashdata("success", "Email setting add successfully...");
                    redirect("setup/dispemailsetting");
                }
          
            }
        }
        $this->load->view('setup/emailsetting');
    }
    
    /** This function Display the Email setting records
     * @return type
     */
    public function dispemailsetting() {
        
        //$this->template->set('nav_links', array('setup' => 'emailsetting', 'setup/emailsetting' => 'Add Email-setting'));
        $this->db->from('email_setting');
        $query = $this->db->get();
        $data['query'] = $query;
        $this->logger->write_logmessage("view"," View Email Setting", "Email setting details...");
        $this->load->view('setup/dispemailsetting',$data);
    }
    
    /**This function Delete the Eamil setting records
     * @param type $id
     * @return type
     */
    public function delete_eset($id) {
        
        //$this->template->set('nav_links', array('setup' => 'emailsetting', 'setup/emailsetting' => 'Add Email-setting'));
        /* Deleting emailsetting */
        $this->db->from('email_setting')->where('id', $id);
        $eset_q = $this->db->get();
        if ($eset_q->num_rows() < 1)
        {
            $this->session->set_flashdata('Invalid Email setting.', 'error');
            redirect('setup/dispemailsetting');
        }
        else {
            $eset_data = $eset_q->row();
        }
        $this->db->trans_start();
        if ( ! $this->db->delete('email_setting', array('id' => $id)))
        {
            $this->db->trans_rollback();
            log_message('error', 'Error in deleting email setting record - ' .$eset_data->username . '.' );

            log_message('debug', 'Email Setting info ');

            $this->session->set_flashdata('Error in deleting email setting - ' . $eset_data->username . '.', 'error');
            log_message("info", "Error  in deleting Email Setting called " . $eset_data->username . " [id:" . $id . "]");
            redirect('setup/dispemailsetting');
        }
        else {
            
        //    $this->db->where('id', $id);
          //  $this->db->delete('email_setting');
            $this->db->trans_complete();
            $this->session->set_flashdata("success", 'Deleted email setting Record ...' );
            $this->logger->write_logmessage("update", "Deleted Email setting called " . $eset_data->username . " [id:" . $id . "]");
            redirect('setup/dispemailsetting');
        }
        $this->load->view('setup/dispemailsetting',$data);
        
    }
    
    /**This function is used for update email setting details
     * @param type $id
     * @return type
     */
    public function editemailsetting($id) {
        
        //$this->template->set('nav_links', array('setup' => 'emailsetting', 'setup/emailsetting' => 'Add Email-setting'));
        
        $this->db->from('email_setting')->where('id', $id);
        $eset_data_q = $this->db->get();
        if ($eset_data_q->num_rows() < 1)
        {
           // $this->messages->add('Invalid Email Setting.', 'error');
            redirect('setup/editemailsetting');
        }
        $editeset_data = $eset_data_q->row();
                
        /* Form fields */

        $data['emailprotocol'] = array(
            'name' => 'emailprotocol',
            'id' => 'emailprotocol',
            'maxlength' => '50',
            'size' => '40',
            'value' => $editeset_data->emailprotocol,
                       
        );
        $data['emailhost'] = array(
           'name' => 'emailhost',
           'id' => 'emailhost',
           'maxlength' => '50',
           'size' => '40',
           'value' => $editeset_data->emailhost,
                       
        );
                
        $data['emailport'] = array(
           'name' => 'emailport',
           'id' => 'emailport',
           'maxlength' => '6',
           'size' => '40',
           'value' => $editeset_data->emailport,
                        
        );
        
        $data['username'] = array(
           'name' => 'username',
           'id' => 'username',
           'maxlength' => '255',
           'size' => '40',
           'value' => $editeset_data->username,
                        
        );
        
        $data['password'] = array(
           'name' => 'password',
           'id' => 'password',
           'maxlength' => '255',
           'size' => '40',
           'value' => $editeset_data->password,
                        
        );
        
        $data['sendername'] = array(
           'name' => 'sendername',
           'id' => 'sendername',
           'maxlength' => '255',
           'size' => '40',
           'value' => $editeset_data->sendername,
                        
        );
                
        $data['id'] = $id;
           
        /*Form Validation*/
        $this->form_validation->set_rules('emailprotocol','Emailprotocol','trim|required|alpha');
        $this->form_validation->set_rules('emailhost','Emailhost','trim|required');
        $this->form_validation->set_rules('emailport','Emailport','trim|required|min_length[2]|max_length[5]|is_numeric',
        array ('required' => ' Email Port -- insert numeric value only'));
        $this->form_validation->set_rules('username','Username','trim|xss_clean|required');
        $this->form_validation->set_rules('password','Password','trim|required|alpha_numeric');
        $this->form_validation->set_rules('sendername','Sendername','trim|required');
                   
        /* Re-populating form */
        if ($_POST)
        {
            $data['emailprotocol']['value'] = $this->input->post('emailprotocol', TRUE);
            $data['emailhost']['value'] = $this->input->post('emailhost', TRUE);
            $data['emailport']['value'] = $this->input->post('emailport', TRUE);
            $data['username']['value'] = $this->input->post('username', TRUE);
            $data['password']['value'] = $this->input->post('password', TRUE);
            $data['sendername']['value'] = $this->input->post('sendername', TRUE);
            $data['modifidate']['value'] = $this->input->post('modifidate', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editemailsetting', $data);
            return;
        }
        else{
                   
            $data_eprotocol = $this->input->post('emailprotocol', TRUE);
            $data_ehost = $this->input->post('emailhost', TRUE);
            $data_eport = $this->input->post('emailport', TRUE);
            $data_eusername = $this->input->post('username', TRUE);
            $data_epassword = $this->input->post('password', TRUE);
            $data_esendername = $this->input->post('sendername', TRUE);
            $data_emodfid = $this->input->post('modifidate', TRUE);
            $data_eid = $id;
                    
            $this->db->select('id')->from('email_setting')->where('id', $data_eid);
            $this->db->trans_start();
            $update_data = array(
               'emailprotocol' => $data_eprotocol,
               'emailhost' => $data_ehost,
               'emailport' => $data_eport,
               'username'  => $data_eusername,
               'password'  => $data_epassword,
               'sendername' => $data_esendername,
               'modifierid' => $this->session->userdata('username'), 
               'modifidate' => date('y-m-d')
            ); 
                    
            if ( ! $this->db->where('id', $data_eid)->update('email_setting', $update_data))
            {
                $this->db->trans_rollback();
                log_message('error', "Error in updating email setting details " . $data_eusername . $data_esendername . " [id:" . $data_eid . "]"  . ' by user ' . $username);

                log_message('debug', 'Edit Email Setting ');
                $this->session->set_flashdata('Error updating emailsetting - ' . $data_eusername . '.', 'error');
                $this->load->view('setup/editemailsetting', $data);
            }  
            else{
                $this->db->trans_complete();
                $this->logger->write_logmessage("update","Edit Email Setting", "Edit Email Setting details..");
                $this->session->set_flashdata('success','Updated email setting details successfully...');
                redirect('setup/dispemailsetting/');
            }
        }//else
	redirect('setup/editemailsetting/');

    }//function end

    /*********** Program Module ***************/

    public function program() 
    {
        $data['title'] = 'Add program';
        if(isset($_POST['program'])) 
        {
            $this->form_validation->set_rules('prgcat','Program Category','trim|xss_clean|required');
            $this->form_validation->set_rules('prgname','Program Name','trim|xss_clean|required');
            $this->form_validation->set_rules('prgbranch','Program Branch','trim|xss_clean|required');
            $this->form_validation->set_rules('prgseat','Seat Available','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('prgcode','Program Code','trim|xss_clean|required');
            $this->form_validation->set_rules('prgshort','Program Short','trim|xss_clean|required');
            $this->form_validation->set_rules('prgdesc','Program Description','trim|xss_clean|required');
            $this->form_validation->set_rules('prgmaxtime','Program Min Time','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('prgmintime','Program Max Time','trim|xss_clean|required|numeric');

            $prgcat = $this->input->post('prgcat');
            $prgname = $this->input->post('prgname');
            $prgseat = $this->input->post('prgseat');
            $prgbranch = $this->input->post('prgbranch');
            $prgcode = $this->input->post('prgcode');
            $prgshort = $this->input->post('prgshort');
            $prgdesc = $this->input->post('prgdesc');
            $prgmaxtime = $this->input->post('prgmaxtime');
            $prgmintime = $this->input->post('prgmintime');
            $prgcrtid = $this->session->userdata('username'); 
            $currdate = date("Y/m/d");
            $prgdate  = $currdate;

        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/program');
            return;
        }
        else
        {
            $data = array('prg_category'=>$prgcat,'prg_name'=>$prgname,'prg_branch'=>$prgbranch,'prg_seat'=>$prgseat,'prg_code'=>$prgcode,'prg_short'=>$prgshort,'prg_desc'=>$prgdesc,'prg_mintime'=>$prgmintime,'prg_maxtime'=>$prgmaxtime,'creatorid'=>$prgcrtid,'createdate'=>$prgdate);
            $this->db->trans_start();
            if(!$this->db->insert('program',$data))
            {
                $this->db->trans_rollback();
                log_message('error', "Error  in adding program data " . $prgcrtid . " [Program id:" . $prg_id . "]");
                log_message('debug', "Problem in adding program" . $prgcrtid );
                $this->session->set_flashdata('Error in adding Program - ' . $prgcrtid . '.', 'error');
                log_message('info', "Error  in adding  Program record " . $prgcrtid . " [Program_id:" . $prg_id . "]");
                $this->load->view('setup/program');
            }
            else
            {
                $this->db->trans_complete();
                $this->logger->write_logmessage("Added","Program Setting Added", "Program details added successfully");
                $this->session->set_flashdata("success", "Program added successfully");
                $this->load->view('setup/program');
            }    
        }
    }

    /* method to view program detail */

    public function viewprogram()
    {
        $this->db->from('program');
        $getprg = $this->db->get();
        $getres = $getprg->result();
        $data['prgres'] = $getres;
        $msg="";
        $data['msg'] = $msg;
        $this->load->view('setup/viewprogram', $data);
        $this->logger->write_logmessage("view"," View Program Details", "View Program details...");
    }

    /* method to modify program detail */

    public function editprogram($prgid){
        $this->db->from('program')->where('prg_id', $prgid);
        $program_data_q = $this->db->get();
        if ($program_data_q->num_rows() < 1)
        {
            redirect('setup/viewporgram/');
        }
        $program_data = $program_data_q->row();

        /* Form Field */

        $data['prgcat'] = array('name' => 'prgcat','id' => 'prgcat','maxlength' => '100','size' => '40','value' => $program_data->prg_category,'',);
        $data['prgname'] = array('name' => 'prgname','id' => 'prgname','maxlength' => '100','size' => '40','value' => $program_data->prg_name,'',);
        $data['prgbranch'] = array('name' => 'prgbranch','id' => '','maxlength' => '100','size' => '40','value' => $program_data->prg_branch,'',);
        $data['prgseat'] = array('name' => 'prgseat','id' => '','maxlength' => '100','size' => '40','value' => $program_data->prg_seat,'',);
        $data['prgcode'] = array('name' => 'prgcode','id' => 'prgcode','maxlength' => '100','size' => '40','value' => $program_data->prg_code,'',);
        $data['prgshort'] = array('name' => 'prgshort','id' => 'prgshort','maxlength' => '100','size' => '40','value' => $program_data->prg_short,'',);
        $data['prgdesc'] = array('name' => 'prgdesc','id' => 'prgdesc','maxlength' => '100','size' => '40','value' => $program_data->prg_desc,'',);
        $data['prgmintime'] = array('name' => 'prgmintime','id' => 'prgmintime','maxlength' => '100','size' => '40','value' => $program_data->prg_mintime,'',);
        $data['prgmaxtime'] = array('name' => 'prgmaxtime','id' => 'prgmaxtime','maxlength' => '100','size' => '40','value' => $program_data->prg_maxtime,'',);
        $data['prgcreatorid'] = array('name' => 'prgcrtid','id' => 'prgcrtid','maxlength' => '100','size' => '40','value' => $program_data->creatorid,'readonly'=>'true',);
        $data['prgid'] = $prgid;

        print_r($program_data->prg_category);
        /* form validation */

        $this->form_validation->set_rules('prgcat','Program Category','trim|xss_clean|required');
        $this->form_validation->set_rules('prgname','Program Name','trim|xss_clean|required');
        $this->form_validation->set_rules('prgbranch','Program Branch','trim|xss_clean|required');
        $this->form_validation->set_rules('prgseat','Seat Available','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('prgcode','Program Code','trim|xss_clean|required');
        $this->form_validation->set_rules('prgshort','Program Short','trim|xss_clean|required');
        $this->form_validation->set_rules('prgdesc','Program Description','trim|xss_clean|required');
        $this->form_validation->set_rules('prgmaxtime','Program Min Time','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('prgmintime','Program Max Time','trim|xss_clean|required|numeric');

        /* update Program Records */

        if($_POST)
        {
            $data['prgcat']['value'] = $this->input->post('prgcat', TRUE);
            $data['prgname']['value'] = $this->input->post('prgname', TRUE);
            $data['prgbranch']['value'] = $this->input->post('prgbranch', TRUE);
            $data['prgseat']['value'] = $this->input->post('prgseat', TRUE);
            $data['prgcode']['value'] = $this->input->post('prgcode', TRUE);
            $data['prgshort']['value'] = $this->input->post('prgshort', TRUE);
            $data['prgdesc']['value'] = $this->input->post('prgdesc', TRUE);
            $data['prgmintime']['value'] = $this->input->post('prgmintime', TRUE);
            $data['prgmaxtime']['value'] = $this->input->post('prgmaxtime', TRUE);
            $data['prgcreatorid']['value'] = $this->input->post('prgcreatorid', TRUE);

        }

        if ($this->form_validation->run() == TRUE)
        {
            //echo $data['prgcat'];
            //echo $data_prgcat;
            $data_prgcat = $this->input->post('prgcat', TRUE);
            $data_prgname = $this->input->post('prgname', TRUE);
            $data_prgbranch = $this->input->post('prgbranch', TRUE);
            $data_prgseat = $this->input->post('prgseat', TRUE);
            $data_prgcode = $this->input->post('prgcode', TRUE);
            $data_prgshort = $this->input->post('prgshort', TRUE);
            $data_prgdesc = $this->input->post('prgdesc', TRUE);
            $data_prgmintime = $this->input->post('prgmintime', TRUE);
            $data_prgmaxtime = $this->input->post('prgmaxtime', TRUE);
            $data_prgcreatorid = $this->input->post('prgcreatorid', TRUE);
            $data_prgid = $prgid;
            $logmessage = "";
            if($program_data->prg_category != $data_prgcat)
                $logmessage = "Program Category " .$program_data->prg_category. " changed by " .$data_prgcat;
            if($program_data->prg_name != $data_prgname)
                $logmessage = $logmessage ." Program Name " .$program_data->prgname. " changed by " .$data_prgname;
            if($program_data->prg_branch != $data_prgbranch)
                $logmessage = $logmessage ." Program Branch " .$program_data->prgbranch. " changed by " .$data_prgbranch;
            if($program_data->prg_seat != $data_prgseat)
                $logmessage = $logmessage ." Seat Available " .$program_data->prgseat. " changed by ". $data_prgseat;
            if($program_data->prg_short != $data_prgshort)
                $logmessage = $logmessage ." Program Short Name ".$program_data->prgshort. " changed by ".$data_prgshort;
            if($program_data->prg_code != $data_prgcode)
                $logmessage = $logmessage ." Program Code " .$program_data->prgcode ." changed by ".$data_prgcode;
            if($program_data->prg_desc != $data_prgdesc)
                $logmessage = $logmessage . "Program Desc " .$program_data->prgdesc. " changed by" .$data_prgdesc;
            if($program_data->prg_mintime != $data_prgmintime)
                $logmessage = $logmessage . "Program Min Time ".$program_data->prgmintime ." changed by".$data_prgmintime;
            if($program_data->prg_maxtime != $data_prgmaxtime)
                $logmessage = $logmessage . "Program Max Time " .$program_data->prgmaxtime. " changed by".$data_prgmaxtime;
              
                
                

            /* update program records*/

            $this->db->trans_start();
            $update_prgdata = array('prg_category' => $data_prgcat,'prg_name' => $data_prgname,'prg_branch' => $data_prgbranch,'prg_seat' => $data_prgseat,'prg_code' => $data_prgcode,'prg_short' => $data_prgshort,'prg_desc' => $data_prgdesc,'prg_mintime' => $data_prgmintime, 'prg_maxtime' => $data_prgmaxtime);

            if (!$this->db->where('prg_id', $data_prgid)->update('program', $update_prgdata))
            {
                $this->db->trans_rollback();
                log_message('error', "Error  in updating program data " . $prgcrtid . " [Program id:" . $prg_id . "]");

                $this->session->set_flashdata('flash_data', 'Program Name' .$data_prgname. 'cannot Updated.');
                log_message('info', "Error  in updating  Program record " . $prgcrtid . " [Program_id:" . $prg_id . "]");
                $this->load->view('setup/editprogram', $data);
            } 
            else 
            {
                $this->db->trans_complete();
                $this->logger->write_logmessage("update","Program Detail Updated", $logmessage);
                $this->session->set_flashdata('success', 'Program Name ' .$data_prgname. ' Successfully Updated.');
                redirect('setup/viewprogram/');
            }
        }
        $this->load->view('setup/editprogram',$data);
    }

    /* deletion of program */

    function deleteprogram($prg_id)
    {
        $this->db->from('program')->where('prg_id', $prg_id);
        $program_q = $this->db->get();
        if ($program_q->num_rows() < 1)
        {
            $this->session->set_flashdata('Invalid program records.', 'error');
            redirect('setup/viewprogram');
        } 
        else 
        {
            $program_data = $program_q->row();
        }
        $this->db->trans_start();
        if ( ! $this->db->delete('program', array('prg_id' => $prg_id)))
        {        
            $this->db->trans_rollback();
            log_message('error', 'Error in deleting program  record - '. $program_data->prg_category . '.' );
            log_message('debug', 'Program Information.');
            $this->session->set_flashdata('Error in deleting program - ' . $program_data->prg_category . '.', 'error');
            log_message("info", "Error  in deleting program records " . $program_data->prg_category . " [prg_id:" . $prg_id . "]");
            redirect('setup/viewprogram');
        }
        else
        {
            $this->db->trans_complete();
            $this->session->set_flashdata("success", 'Deleted Program Record '. $program_data->prg_category .' Successfully');
            $this->logger->write_logmessage("update", "Deleted Program called " . $program_data->prg_category . " [prg_id:" . $prg_did . "]");
            redirect('setup/viewprogram');
        }
        $this->load->view('setup/viewprogram');
    }
    
    /** Program Module End **/

}
