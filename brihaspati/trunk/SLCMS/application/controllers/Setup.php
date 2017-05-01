<?php

/* 
 * @name Setup.php
 * @author Manorama Pal(palseema30@gmail.com)
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
}
