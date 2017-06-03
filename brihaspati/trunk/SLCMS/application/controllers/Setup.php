<?php

/* 
 * @name Setup.php
 * @author Manorama Pal(palseema30@gmail.com)  add email setting
 * @author Sharad Singh(sharad23nov@yahoo.com) add program, add subject
 * @author Om Prakash(omprakashkgp@gmail.com)  add category
 * @author Kishore kr shukla(kishore.shukla@gmail.com) add role
 * @author Raju Kamal(kamalraju8@gmail.com)    add department
 * @author Vijay(vijay.pal428@gmail.com)    add program fees
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Setup extends CI_Controller
{
    function __construct() {
        parent::__construct();
	$this->load->model('common_model'); 
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
                $emailflag=$this->common_model->insertrec('email_setting', $data) ;
                if ( ! $emailflag)
                {
                    $this->logger->write_logmessage("insert", "Error in adding email configuration detail  " .$data->emailprotocol);
                    $this->logger->write_dblogmessage("insert", "Error in adding email configuration detail  " .$data->emailprotocol);
                    $this->session->set_flashdata("err_message",'Error in adding email configuration detail - ' .$data->emailprotocol );
                    redirect('setup/emailsetting');

                }
                else{
                    $this->logger->write_logmessage("insert","Email setting record insert successfully " .$data->emailprotocol);
                    $this->logger->write_dblogmessage("insert", "Add Email Setting record " .$data->emailprotocol);
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
        
	$this->result = $this->common_model->get_list('email_setting');
        $this->logger->write_logmessage("view"," View Email Setting", "Email setting details...");
        $this->load->view('setup/dispemailsetting',$this->result);
    }
    
    /**This function Delete the Eamil setting records
     * @param type $id
     * @return type
     */
    public function delete_eset($id,$emailprotocol) {
        
        /* Deleting emailsetting */
        $delflag=$this->common_model->deleterow('email_setting','id',$id);
        if (! delflag   )
        {   
            $this->logger->write_logmessage("delete", "Error in adding email configuration detail  " .$emailprotocol . " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in adding email configuration detail  " .$emailprotocol . " [id:" . $id . "]");
            $this->session->set_flashdata('Error in deleting email setting - ' . $emailprotocol);
            redirect('setup/dispemailsetting');
        }
        else {
         
            $this->logger->write_logmessage("delete", " Deleted email setting Record  " .$emailprotocol . " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted email setting Record  " .$emailprotocol . " [id:" . $id . "]");
            $this->session->set_flashdata("success", 'Deleted email setting Record successfully ...' );
            redirect('setup/dispemailsetting');
        }
        $this->load->view('setup/dispemailsetting',$data);
        
    }
    
    /**This function is used for update email setting details
     * @param type $id
     * @return type
     */
    public function editemailsetting($id) {
               
        $this->db->from('email_setting')->where('id', $id);
        $eset_data_q = $this->db->get();
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
            
            /* check and store updated values for log */

            $logmessage = "";
            if($editeset_data->emailprotocol != $data_eprotocol)
                $logmessage = "Email Protocol " .$editeset_data->emailprotocol. " changed by " .$data_eprotocol;
            if($editeset_data->emailhost != $data_ehost)
                $logmessage = $logmessage ." Email Host " .$editeset_data->emailhost. " changed by " .$data_ehost;
            if($editeset_data->emailport != $data_eport)
                $logmessage = $logmessage ." Email Port " .$editeset_data->emailport. " changed by " .$data_eport;
            if($editeset_data->sendername != $data_esendername)
                $logmessage = $logmessage ." Sender Name " .$editeset_data->sendername. " changed by ". $data_esendername;

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
            $editflag=$this->common_model->updaterec('email_setting', $update_data,'id',$data_eid);
            if(! $editflag)
            {
                $this->logger->write_logmessage("update", "Error in updating email configuration detail  ", $logmessage );
                $this->logger->write_dblogmessage("update", "Error in updating email configuration detail  ",$logmessage);
                $this->session->set_flashdata('err_message','Error updating emailsetting - ' . $data_eusername);
                $this->load->view('setup/editemailsetting', $data);
            }  
            else{
                $this->logger->write_logmessage("update", "Edit Email Setting details  ", $logmessage );
                $this->logger->write_dblogmessage("update", "Edit Email Setting details ",$logmessage);
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

            /* check for duplicate record*/
            $result = $this->common_model->isduplicate('program','prg_category',$prgcat);
            if($result == 1)
            {
                $this->session->set_flashdata('error','Program category <b>' .$prgcat . '</b> already exist' );
                redirect('setup/program');
            }

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
                redirect('setup/viewprogram');
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
                $logmessage = $logmessage . "Program Desc " .$program_data->prgdesc. " changed by " .$data_prgdesc;
            if($program_data->prg_mintime != $data_prgmintime)
                $logmessage = $logmessage . "Program Min Time ".$program_data->prgmintime ." changed by " .$data_prgmintime;
            if($program_data->prg_maxtime != $data_prgmaxtime)
                $logmessage = $logmessage . "Program Max Time " .$program_data->prgmaxtime. " changed by " .$data_prgmaxtime;
              
                
                

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
                $this->logger->write_dblogmessage("update","Program Detail Updated", $logmessage);
                $this->session->set_flashdata('success', 'Program Name ' .$data_prgname. ' Successfully Updated.');
                redirect('setup/viewprogram/');
            }
        }
        $this->load->view('setup/editprogram',$data);
    }

    /* deletion of program */

    public function deleteprogram($prg_id)
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

    /** Subject Module **/


    public function subject()
    {
        
        $data['subname'] = array('name' => 'subname','id' => 'subname','maxlength' => '100','size' => '40','value' => '',);
        $data['subcode'] = array('name' => 'subcode','id' => 'subcode','maxlength' => '100','size' => '40','value' => '',);
        $data['subshort'] = array('name' => 'subshort','id' => 'subshort','maxlength' => '100','size' => '40','value' => '',);
        $data['subdesc'] = array('name' => 'subdesc','id' => 'subdesc','maxlength' => '100','size' => '40','value' => '',);
        $data['subext1'] = array('name' => 'subext1','id' => 'subext1','maxlength' => '100','size' => '40','value' => '',);
        $data['subext2'] = array('name' => 'subext2','id' => 'subext2','maxlength' => '100','size' => '40','value' => '',);
       
        $this->form_validation->set_rules('subname','Subject Name','trim|xss_clean|required');
        $this->form_validation->set_rules('subcode','Subject Code','trim|xss_clean|required');
        $this->form_validation->set_rules('subshort','Subject Short','trim|xss_clean|required');
        $this->form_validation->set_rules('subdesc','Subject Description','trim|xss_clean|required');
        $this->form_validation->set_rules('subext1','Subject Ext1','trim|xss_clean|required');
        $this->form_validation->set_rules('subext2','Subject Ext2','trim|xss_clean|required');

        if($this->form_validation->run() == TRUE)
        {
        
            $this->load->helper('form');
            $this->load->helper('html');
            $this->load->model('setup_model','setupmod');
            if( $this->input->post('submit')) 
            {
                $this->setupmod->addsubjectrecords();
            }
        }
    $this->load->view('setup/subject',$data);
    return;
    }
    /* method to view subject detail */

    public function viewsubject()
    {
        $data = array();
        $this->load->model('setup_model','getsubjectlist');
        $this->data['subjectlists'] = $this->getsubjectlist->viewsubject();
        $this->load->view('setup/viewsubject',$this->data);
    }
    
    /* method to update subject detail */
    
    public function editsubject($subid)
    {
        /* get record to be updated */
        $username = $this->session->userdata('username');
        $this->load->model('setup_model','setupmod');
        if($subid != "")
            $subresult = $this->setupmod->getsubject_byid($subid);
        foreach ($subresult as $value) 
        {
            $subname = $value->sub_name;
            $subcode = $value->sub_code;
            $subshort = $value->sub_short;
            $subdesc = $value->sub_desc;
            $subext1 = $value->sub_ext1;
            $subext2 = $value->sub_ext2;
        }

        /* Form Field */
                 
        $data['subname'] = array('name' => 'subname','id' => 'subname','maxlength' => '100','size' => '40','value' => $subname,);
        $data['subcode'] = array('name' => 'subcode','id' => 'subcode','maxlength' => '100','size' => '40','value' => $subcode,);
        $data['subshort'] = array('name' => 'subshort','id' => 'subshort','maxlength' => '100','size' => '40','value' => $subshort,);
        $data['subdesc'] = array('name' => 'subdesc','id' => 'subdesc','maxlength' => '100','size' => '40','value' => $subdesc,);
        $data['subext1'] = array('name' => 'subext1','id' => 'subext1','maxlength' => '100','size' => '40','value' => $subext1,);
        $data['subext2'] = array('name' => 'subext2','id' => 'subext2','maxlength' => '100','size' => '40','value' => $subext2,);
        
        $data['subid'] = $subid;
        
        /* form validation */

        $this->form_validation->set_rules('subname','Subject Name','trim|xss_clean|required');
        $this->form_validation->set_rules('subcode','Subject Code','trim|xss_clean|required');
        $this->form_validation->set_rules('subshort','Subject Short','trim|xss_clean|required');
        $this->form_validation->set_rules('subdesc','Subject Description','trim|xss_clean|required');
        $this->form_validation->set_rules('subext1','Subject Ext1','trim|xss_clean|required');
        $this->form_validation->set_rules('subext2','Subject Ext2','trim|xss_clean|required');

        if($_POST)
        {
            $data['subname'] = $this->input->post('subname', TRUE);
            $data['subcode'] = $this->input->post('subcode', TRUE);
            $data['subshort'] = $this->input->post('subshort', TRUE);
            $data['subdesc'] = $this->input->post('subdesc', TRUE);
            $data['subext1'] = $this->input->post('subext1', TRUE);
            $data['subext2'] = $this->input->post('subext2', TRUE);
        }
        
        if ($this->form_validation->run() == TRUE)
        {
            $data_subname = $this->input->post('subname', TRUE);
            $data_subcode = $this->input->post('subcode', TRUE);
            $data_subshort = $this->input->post('subshort', TRUE);
            $data_subdesc = $this->input->post('subdesc', TRUE);
            $data_subext1 = $this->input->post('subext1', TRUE);
            $data_subext2 = $this->input->post('subext2', TRUE);
            $data_subid = $subid;

            /* check and store updated values for log */

            $logmessage = "";
            if($subname != $data_subname)
                $logmessage = "Subject Name " .$subname. " changed by " .$data_subname;
            if($subcode != $data_subcode)
                $logmessage = $logmessage ." Subject Code " .$subcode. " changed by " .$data_subcode;
            if($subshort != $data_subshort)
                $logmessage = $logmessage ." Subject Short Name " .$subshort. " changed by " .$data_subshort;
            if($subdesc != $data_subdesc)
                $logmessage = $logmessage ." Subject Description " .$subdesc. " changed by ". $data_subdesc;
            if($subext1 != $data_subext1)
                $logmessage = $logmessage ." Subject Extra ".$subext1. " changed by ".$data_subext1;
            if($subext2 != $data_subext2)
                $logmessage = $logmessage ." Subject Extra " .$subext2 ." changed by ".$data_subext2;

            $update_subdata = array('sub_name' => $data_subname,'sub_code' => $data_subcode,'sub_short' => $data_subshort,'sub_desc' => $data_subdesc,'sub_ext1' => $data_subext1,'sub_ext2' => $data_subext2);
            $this->db->trans_start();

            /* update records */
                
            $subresult = $this->setupmod->update_subject_byid($subid,$update_subdata);    
            if($subresult != 1)
            {
                $this->db->trans_rollback();
                /* log for file and db */
                $this->logger->write_logmessage("update"," Try to update Subject Detail - ",  $logmessage . ' by '. $username);
                $this->logger->write_dblogmessage("update"," Try to update subject Detail - ", $logmessage . ' by '. $username);
                $this->session->set_flashdata('flash_data', 'subject name' .$subname. ' cannot be Updated '. ' by '.$username);
                $this->load->view('setup/editsubject', $data);                
            }
            else
            {
                $this->db->trans_complete();
                /* log for file and db */
                $this->logger->write_logmessage("update"," Subject Detail Updated - ",  $logmessage . ' by '. $username);
                $this->logger->write_dblogmessage("update"," Subject Detail Updated - ", $logmessage . ' by '. $username);
                $this->session->set_flashdata('success', 'Subject Record <b>' .$subname. '</b> Successfully Updated '.' by '.$username);
                redirect('setup/viewsubject/');
            }
        }   
    $this->load->view('setup/editsubject',$data);       
    }

    /* method to delete subject detail */

    function deletesubject($subid,$subname)
    {
        $result = $this->common_model->deleterow('subject','sub_id',$subid);

        if ($result != 1 )
        {        
            $this->session->set_flashdata('error','Error in deleting subject - ' . $subname . '.' );
            $this->logger->write_logmessage("update", "Error in Deleting subject  " . $subname, " by ".  $subname );
            $this->logger->write_dblogmessage("update", "Error in Deleting subject  " , $subname. " by ".  $subname );
            redirect('setup/viewsubject');
        }
        else
        {
            $this->session->set_flashdata("success", "Subject record <b>". $subname ."</b> deleted successfully");
            $this->logger->write_logmessage("update", "Deleted subject record " . $subname , " by". $subname);
            $this->logger->write_dblogmessage("update", "Deleted subject record " , $subname . " by". $subname);
            redirect('setup/viewsubject');
        }

    }
        
        
 // =============== Add Category Module =========================================================================================================== 

 /* this function for add category record */
  public function category(){

        if(isset($_POST['category'])) {
            $this->form_validation->set_rules('cname','Category Name','trim|xss_clean|required|alpha_numeric_spaces|callback_value_exists');
            $this->form_validation->set_rules('ccode','Category Code','trim|xss_clean|required|alpha_dash');
            $this->form_validation->set_rules('csname','Category Short Name','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('cdesc','Category Description','trim|xss_clean|alpha_numeric_spaces');

            if($this->form_validation->run()==TRUE){

            $data = array(
                'cat_name'=>ucwords(strtolower($_POST['cname'])),
                'cat_code'=>strtoupper($_POST['ccode']),
                'cat_short'=>strtoupper($_POST['csname']),
                'cat_desc'=>$_POST['cdesc']

            );
	   $catflag=$this->common_model->insertrec('category', $data) ;
	   if(!$catflag)
	   {
                $this->logger->write_logmessage("insert"," Error in adding category ", " Category data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding category ", " Category data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding Category - ' . $_POST['cname'] , 'error');
                $this->load->view('setup/category');
	   }	
	  else{		
		$this->logger->write_logmessage("insert"," add category ", "Category record added successfully..."  );
		$this->logger->write_dblogmessage("insert"," add category ", "Category record added successfully..." );
            	$this->session->set_flashdata("success", "Category added successfully...");
            	redirect("setup/displaycategory", "refresh");
	      }
           }

        }
      $this->load->view('setup/category');
   }


  /* Display Category record */

  public function displaycategory(){

	$this->result = $this->common_model->get_list('category');
        $this->logger->write_logmessage("view"," View Category", "Category record display successfully..." );
        $this->logger->write_dblogmessage("view"," View Category", "Category record display successfully..." );
        $this->load->view('setup/displaycategory',$this->result);
    }

  /* this function is used for delete category record */
  public function deletecategory($cat_id) {

	$catflag=$this->common_model->deleterow('category', 'cat_id', $cat_id) ;
        if(!$catflag)
        {
            $this->logger->write_logmessage("delete", "Deleted Category ", "Error in  Category  [cat_id:" . $cat_id . "] delete.. " );
            $this->logger->write_dblogmessage("delete", "Deleted Category ","Error in Category  [cat_id:" . $cat_id . "] delete.. " );
            $this->session->set_flashdata('err_message','Error in deleting Category - ' , 'Error');
            redirect('setup/displaycategory');
        }
        else {

            $this->logger->write_logmessage("delete", "Deleted Category ", "Category [cat_id:" . $cat_id . "] deleted successfully. " );
            $this->logger->write_dblogmessage("delete", "Deleted Category ","Category [cat_id:" . $cat_id . "] deleted successfully. " );
 	    $this->session->set_flashdata("success", 'Category Record Deleted successfully.' );
            redirect('setup/displaycategory');
        }

    }

 /**This function is used for update category details
     * @param type $cat_id
     * @return type
     */
    public function editcategory($cat_id) {
	$cat_data_q=$this->common_model->get_listrow('category','cat_id', $cat_id);
        if ($cat_data_q->num_rows() < 1)
        {
           redirect('setup/editcategory');
        }
        $category_data = $cat_data_q->row();

        /* Form fields */

        $data['cname'] = array(
            'name' => 'cname',
            'id' => 'cname',
            'maxlength' => '50',
            'size' => '40',
            'value' => $category_data->cat_name,
	    'readonly' => 'readonly'	
        );
        $data['ccode'] = array(
           'name' => 'ccode',
           'id' => 'ccode',
           'maxlength' => '50',
           'size' => '40',
           'value' => $category_data->cat_code,

        );

        $data['csname'] = array(
           'name' => 'csname',
           'id' => 'csname',
           'maxlength' => '6',
           'size' => '40',
           'value' => $category_data->cat_short,

        );

        $data['cdesc'] = array(
           'name' => 'cdesc',
           'id' => 'cdesc',
           'maxlength' => '255',
           'size' => '40',
           'value' => $category_data->cat_desc,

        );

        $data['cat_id'] = $cat_id;

        $this->form_validation->set_rules('cname','Category Name ','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('ccode','Category Code ','trim|xss_clean|required|alpha_dash');
        $this->form_validation->set_rules('csname','Category Short Name ','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('cdesc','Category Description ','trim|xss_clean|alpha_numeric_spaces');

        if ($_POST)
        {
            $data['cname']['value'] = $this->input->post('cname', TRUE);
            $data['ccode']['value'] = $this->input->post('ccode', TRUE);
            $data['csname']['value'] = $this->input->post('csname', TRUE);
            $data['cdesc']['value'] = $this->input->post('cdesc', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editcategory', $data);
            return;
        }
	else
        {

            $data_cname = ucwords(strtolower($this->input->post('cname', TRUE)));
            $data_ccode = strtoupper($this->input->post('ccode', TRUE));
            $data_csname = strtoupper($this->input->post('csname', TRUE));
            $data_cdesc = $this->input->post('cdesc', TRUE);
            $data_cid = $cat_id;
	    $logmessage = "";
            if($category_data->cat_name != $data_cname)
                $logmessage = "Add Category " .$category_data->cat_name. " changed by " .$data_cname;
            if($category_data->cat_code != $data_ccode)
                $logmessage = "Add Category " .$category_data->cat_code. " changed by " .$data_ccode;
            if($category_data->cat_short != $data_csname)
                $logmessage = "Add Category " .$category_data->cat_short. " changed by " .$data_csname;
            if($category_data->cat_desc != $data_cdesc)
                $logmessage = "Add Category " .$category_data->cat_desc. " changed by " .$data_cdesc;

            $update_data = array(
               'cat_name' => $data_cname,
               'cat_code' => $data_ccode,
               'cat_short' => $data_csname,
               'cat_desc'  => $data_cdesc
            );

	   $catflag=$this->common_model->updaterec('category', $update_data, 'cat_id', $data_cid);
	   if(!$catflag)	
            {
                $this->logger->write_logmessage("error","Error in update Category ", "Error in Category record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Category ", "Error in Category record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating category - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editcategory', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Category", "Category record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Category", "Category record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Category record updated successfully...');
                redirect('setup/displaycategory/');
                }
        }//else
        redirect('setup/editcategory/');
    }


/** This function check for duplicate entry
    * @return type
    */

 public function value_exists($key)
 {
     $is_exist = $this->common_model->isduplicate('category','cat_name',$key);

     if($is_exist) {
        $this->form_validation->set_message(
            'value_exists', 'Category  '. $key. '  is already exist.'
        );
        return false;
    } else {
    return true;
  }

}



 //====================End of Add Category Module ============================================
//*************************Start Department**************************************//
 	  public function dept(){
        	$this->scresult = $this->common_model->get_listspfic2('study_center','sc_code', 'sc_name');
   	        $this->uresult = $this->common_model->get_listspfic2('org_profile','org_code','org_name');

            
	   if(isset($_POST['dept'])) { 
               
                $this->form_validation->set_rules('orgprofile','University','trim|xss_clean|required');
                $this->form_validation->set_rules('studycenter','Campus','trim|xss_clean|required');
                $this->form_validation->set_rules('dept_schoolcode','School code','trim|xss_clean|alpha_numeric|required');
                $this->form_validation->set_rules('dept_schoolname','School name','trim|xss_clean|required');
                $this->form_validation->set_rules('dept_code','Department code','trim|xss_clean|required');
                $this->form_validation->set_rules('dept_name','Department name','trim|xss_clean|required');
                $this->form_validation->set_rules('dept_short','Department nick','trim|xss_clean|required|alpha_numeric');
                $this->form_validation->set_rules('dept_descripation','Department description','trim|xss_clean|required');
                       
                if($this->form_validation->run()==TRUE){

                 if (($_POST['orgprofile'] != '') || ($_POST['studycenter'] != '')){  
                 $data = array(
                                'dept_orgcode'=>strtoupper($_POST['orgprofile']),
                                'dept_sccode'=>strtoupper($_POST['studycenter']),
                                'dept_schoolcode'=>strtoupper($_POST['dept_schoolcode']),
                                'dept_schoolname'=>ucwords(strtolower($_POST['dept_schoolname'])),
                                'dept_code'=>strtoupper($_POST['dept_code']),
                                'dept_name'=>ucwords(strtolower($_POST['dept_name'])),
                                'dept_short'=>$_POST['dept_short'],
                                'dept_description'=>$_POST['dept_descripation'],
                        );
                        $deptflag=$this->common_model->insertrec('Department', $data) ;
                        if(!$deptflag)
                        {
                                $this->logger->write_logmessage("insert"," Error in adding Department ", " Department data insert error . ".$dept_name  );
                                $this->logger->write_dblogmessage("insert"," Error in adding Department ", " Department data insert error . ".$dept_name );
                                $this->session->set_flashdata('err_message','Error in adding Department - ' . $dept_name . '.', 'error');
                                redirect('setup/dispdepartment');
                        }
                        else{
                                $this->logger->write_logmessage("insert"," add Department ", "Department record added successfully.".$dept_name  );
                                $this->logger->write_dblogmessage("insert"," add Deaprtment ", "Department record added successfully.".$dept_name );
                                $this->session->set_flashdata("success", "Department added successfully...");
                                redirect('setup/dispdepartment');
                        }
                        }
			return;
                }
        }
        $this->load->view('setup/dept');
    }
    /** This function Display the Department list records
     * @return type
     */
    public function dispdepartment() {
        $this->deptresult = $this->common_model->get_list('Department');
        $this->logger->write_logmessage("view"," View Department list", "department list display");
        $this->logger->write_dblogmessage("view"," View Department list", "department list display");
        $this->load->view('setup/dispdepartment');
       }
    /* this function is used for delete department record */
    public function deletedept($deptid) {

        $deptflag=$this->common_model->deleterow('Department', 'dept_id', $deptid) ;
        if(!$deptflag)
        {
            $this->logger->write_logmessage("delete", "Deleted Department ", "Error in  Department  [dept_id:" . $deptid . "] delete. " );
            $this->logger->write_dblogmessage("delete", "Deleted Department ","Error in Department  [dept_id:" . $deptid . "] delete. " );
            $this->session->set_flashdata('err_message','Error in deleting Department - ' , 'error');
            redirect('setup/dispdepartment');
        }
        else {

            $this->logger->write_logmessage("delete", "Deleted Department ", "Department  [deptid:" . $deptid . "] deleted successfully. " );
            $this->logger->write_dblogmessage("delete", "Deleted Department ","Department [deptid:" . $deptid . "] deleted successfully. " );
            $this->session->set_flashdata("success", 'Department Record Deleted successfully.' );
            redirect('setup/dispdepartment');
        }
          $this->load->view('setup/dept',$data);

    }
     /* this function is used for update department record */
    public function editdepartment($id) {

	$deptrow=$this->common_model->get_listrow('Department','dept_id', $id);
        if ($deptrow->num_rows() < 1)
        {
            redirect('setup/dispdepartment');
        }

        $dept_data = $deptrow->row();

        /* Form fields */
         $data['deptorgcode'] = array(
            'name' => 'deptorgcode',
            'id' => 'deptorgcode',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('org_profile','org_name','org_code',$dept_data->dept_orgcode)->org_name,
	    'readonly' => 'readonly'
             );
         $data['deptsccode'] = array(
            'name' => 'deptsccode',
            'id' => 'deptsccode',
            'maxlength' => '50',
            'size' => '40',
           'value' => $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$dept_data->dept_sccode)->sc_name,
	    'readonly' => 'readonly'
        );
        $data['deptschoolcode'] = array(
            'name' => 'deptschoolcode',
            'id' => 'deptschoolcode',
            'maxlength' => '50',
            'size' => '40',
            'value' => $dept_data->dept_schoolcode,
        );
        $data['deptschoolname'] = array(
           'name' => 'deptschoolname',
           'id' => 'deptschoolname',
           'maxlength' => '50',
           'size' => '40',
           'value' => $dept_data->dept_schoolname,
        );
        $data['deptcode'] = array(
           'name' => 'deptcode',
           'id' => 'deptcode',
           'maxlength' => '6',
           'size' => '40',
           'value' => $dept_data->dept_code,
        );
	$data['deptname'] = array(
           'name' => 'deptname',
           'id' => 'deptname',
           'maxlength' => '255',
           'size' => '40',
           'value' => $dept_data->dept_name,
        );
        $data['deptshort'] = array(
           'name' => ' deptshort',
           'id' => 'deptshort',
           'maxlength' => '255',
           'size' => '40',
           'value' => $dept_data->dept_short,
        );
        $data['deptdescription'] = array(
           'name' => 'deptdescription',
           'id' => 'deptdescription',
           'maxlength' => '255',
           'size' => '40',
           'value' => $dept_data->dept_description,
        );
        $data['id'] = $id;

        /*Form Validation*/
        
       // $this->form_validation->set_rules('dept_orgcode','University','trim|xss_clean|required');
       // $this->form_validation->set_rules('dept_sccode','Campus','trim|xss_clean|required');
        $this->form_validation->set_rules('deptschoolcode','School code','trim|xss_clean|required');
        $this->form_validation->set_rules('deptschoolname','School name','trim|xss_clean|required');
        $this->form_validation->set_rules('deptcode','Department code','trim|xss_clean|required');
        $this->form_validation->set_rules('deptname','Department name','trim|xss_clean|required');
        $this->form_validation->set_rules('deptshort','Department nick','trim|xss_clean|required');
        $this->form_validation->set_rules('deptdescription','Department description','trim|xss_clean|required');
     
        /* Re-populating form */
        if ($_POST)
        {
    //         $this->input->post('deptorgcode', TRUE);
           // $data['deptorgcode']['value'] = $this->input->post('deptorgcode', TRUE);
      //      $data['deptsccode']['value'] = $this->input->post('deptsccode', TRUE);
            $data['deptschoolcode']['value'] = $this->input->post('deptschoolcode', TRUE);
            $data['deptschoolname']['value'] = $this->input->post('deptschoolname', TRUE);
            $data['deptcode']['value'] = $this->input->post('deptcode', TRUE);
            $data['deptname']['value'] = $this->input->post('deptname', TRUE);
            $data['deptshort']['value'] = $this->input->post('deptshort', TRUE);
            $data['deptdescription']['value'] = $this->input->post('deptdescription', TRUE);
        }

	if ($this->form_validation->run() ==FALSE )
       {            
                $this->session->set_flashdata(validation_errors(), 'error');
                $this->load->view('setup/editdepartment', $data);
        }
        else{
            $schoolcode=strtoupper($this->input->post('deptschoolcode', TRUE));
            $schoolname = ucwords(strtolower($this->input->post('deptschoolname', TRUE)));
            $departmentcode = strtoupper($this->input->post('deptcode', TRUE));
            $departmentname = ucwords(strtolower($this->input->post('deptname', TRUE)));
            $departmentshort = $this->input->post('deptshort', TRUE);
            $departmentdescription = $this->input->post('deptdescription', TRUE);
//            $deptsccode = strtoupper($this->input->post('deptsccode',TRUE));
  //          $deptorgcode = strtoupper($this->input->post('deptorgcode', TRUE));

	    $logmessage = "";
            if($dept_data->dept_schoolcode != $schoolcode)
                $logmessage = $logmessage ." update school code " .$dept_data->dept_schoolcode. " changed by " .$schoolcode;
            if($dept_data->dept_schoolname != $schoolname)
                $logmessage = $logmessage ." update school name " .$dept_data->dept_schoolname. " changed by " .$schoolname;
            if($dept_data->dept_code != $departmentcode)
                $logmessage = $logmessage ." update dept code " .$dept_data->dept_code. " changed by " .$departmentcode;
            if($dept_data->dept_name != $departmentname)
                $logmessage = $logmessage ." update dept name " .$dept_data->dept_name. " changed by " .$departmentname;
            if($dept_data->dept_short != $departmentshort)
                $logmessage = $logmessage ." update dept short " .$dept_data->dept_short. " changed by " .$departmentshort;
            if($dept_data->dept_description != $departmentdescription)
                $logmessage = $logmessage ." update dept description " .$dept_data->dept_description. " changed by " .$departmentdescription;

            $update_data = array(
               'dept_schoolcode' => $schoolcode,
               'dept_schoolname' => $schoolname,
               'dept_code' => $departmentcode,
               'dept_name'  => $departmentname,
               'dept_short'  => $departmentshort,
               'dept_description' => $departmentdescription,
              // 'dept_sccode' => $deptsccode,
               //'dept_orgcode' => $deptorgcode
            );
           $deptflag=$this->common_model->updaterec('Department', $update_data, 'dept_id', $id);
           if(!$deptflag)
            {
                $this->logger->write_logmessage("error","Error in update Department ", "Error in Department record update". $logmessage );
                $this->logger->write_dblogmessage("error","Error in update Department ", "Error in Department record update". $logmessage );
                $this->session->set_flashdata('err_message','Error updating Department - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editdepartment', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Department", "Department record updated successfully..". $logmessage );
                $this->logger->write_dblogmessage("update","Edit Department", "Department record updated successfully..". $logmessage );
                $this->session->set_flashdata('success','Department record updated successfully...');
                redirect('setup/dispdepartment');
                }
            }
        }
  
 /****************************************** Add Role Module ********************************************/

    /** This function for add role
     * @return type
     */

    public function role()
                {
                 if(isset($_POST['role'])) {
                 $this->form_validation->set_rules('role_name','Role Name','trim|xss_clean|required|alpha_numeric_spaces|callback_isRoleExist');
                 $this->form_validation->set_rules('role_desc','Role Desc','trim|xss_clean|required|alpha_numeric_spaces');
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';

                 $data = array(
                'role_name'=>ucwords(strtolower($_POST['role_name'])),
                'role_desc'=>$_POST['role_desc'],
                 );
                $rflag=$this->common_model->insertrec('role', $data);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add role", "Role is not added ".$role_name);
                    $this->logger->write_dblogmessage("insert","Trying to add role", "Role is not added ".$role_name);
                    $this->session->set_flashdata('err_message','Error in adding role setting - '  , 'error');
                    redirect('setup/role');

                }
                else{
                    $this->logger->write_logmessage("insert","Add role Setting", "Role".$_POST['role_name']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add role Setting", "Role ".$_POST['role_name']."added  successfully...");
                    $this->session->set_flashdata("success", "Role add successfully...");
                    redirect("setup/displayrole");
                }

            }
        }
        $this->load->view('setup/role');
    }

    /** This function check for duplicate role
     * @return type
    */

    public function isRoleExist($role_name) {

        $is_exist = $this->common_model->isduplicate('role','role_name',$role_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isRoleExist', 'Role is already exist.');
            return false;
        }
        else {
            return true;
        }
    }

    /** This function Display the role records
     * @return type
    */

    public function displayrole() {
        $this->result = $this->common_model->get_list('role');
        $this->logger->write_logmessage("view"," View role setting", "Role setting details...");
        $this->logger->write_dblogmessage("view"," View role setting", "Role setting details...");
        $this->load->view('setup/displayrole',$this->result);
       }

    /**This function Delete the role records
     * @param type $id
     * @return type
     */

         public function delete_role($id) {

          $roledflag=$this->common_model->deleterow('role','role_id', $id);
          if(!$roledflag)
          {
            $this->logger->write_message("error", "Error  in deleting role " ."[role_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting role "." [role_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error in Deleting role - ', 'error');
            redirect('setup/displayrole');
           return;
          }
        else{
            $this->logger->write_logmessage("delete", "Deleted   role " . "[role_id:" . $id . "] deleted successfully.. " );
            $this->logger->write_dblogmessage("delete", "Deleted role" ." [role_id:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'Role Deleted successfully...' );
            redirect('setup/displayrole');
        }
        $this->load->view('setup/displayrole',$data);

}

    /**This function is used for update role records
     * @param type $id
     * @return type
     */

    public function editrole($id) {

        $this->db->from('role')->where('role_id', $id);
        $eset_data_q = $this->db->get();
        if ($eset_data_q->num_rows() < 1)
        {
            redirect('setup/editrole');
        }
        $editeset_data = $eset_data_q->row();

        /* Form fields */

                $data['role_name'] = array(
                'name' => 'role_name',
                'id' => 'role_name',
                'maxlength' => '50',
                'size' => '40',
                'value' => $editeset_data->role_name,

                );
        $data['role_desc'] = array(
           'name' => 'role_desc',
            'id' => 'role_desc',
           'maxlength' => '50',
           'size' => '40',
           'value' => $editeset_data->role_desc,

        );
        $data['id'] = $id;
        /*Form Validation*/
        $this->form_validation->set_rules('role_name','Role name','trim|xss_clean|required|alpha_numeric_spaces|callback_isRoleExist');
        $this->form_validation->set_rules('role_desc','Role Desc','trim|xss_clean|required|alpha_numeric_spaces');

        /* Re-populating form */
        if ($_POST)
        {
            $data['role_name']['value'] = $this->input->post('role_name', TRUE);
            $data['role_desc']['value'] = $this->input->post('role_desc', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editrole', $data);
            return;
        }
        else{

            $data_erole = ucwords(strtolower($this->input->post('role_name', TRUE)));
            $data_eroledesc = $this->input->post('role_desc', TRUE);
            $data_eid = $id;
            $logmessage = "";
            if($editeset_data->role_name != $data_erole)
                $logmessage = "Add Role " .$editeset_data->role_name. " changed by " .$data_erole;
            if($editeset_data->role_desc != $data_eroledesc)
                $logmessage = "Add Role " .$editeset_data->role_desc. " changed by " .$data_eroledesc;

            $update_data = array(
               'role_name' => $data_erole,
               'role_desc' => $data_eroledesc,
            );

        $roledflag=$this->common_model->updaterec('role', $update_data,' role_id', $data_eid);
        if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit role Setting error", "Edit role Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit role Setting error", "Edit role Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating role - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editrole', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit role Setting", "Edit role Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit role Setting", "Edit role Setting details. $logmessage ");
                $this->session->set_flashdata('success','Role  detail updated successfully..');
                redirect('setup/displayrole/');
                }
        }//else
        redirect('setup/editrole/');

    }//Add role function end

		
	//Fees Program set up

     public function fees() {

		$this->prgresult = $this->common_model->get_listspfic2('program','prg_id', 'prg_name');
                $this->catresult = $this->common_model->get_listspfic2('category','cat_id','cat_name');


    		if(isset($_POST['fees'])) {
                        $this->form_validation->set_rules('program','Program Name','trim|xss_clean|required');
                        $this->form_validation->set_rules('acadyear','Academic Year','trim|xss_clean');
                        $this->form_validation->set_rules('semester','Semester','trim|xss_clean|required');
                        $this->form_validation->set_rules('category','Category','trim|xss_clean|required');
                        $this->form_validation->set_rules('gender','Gender','trim|xss_clean|required');
                        $this->form_validation->set_rules('head','Head','trim|xss_clean|required');
                        $this->form_validation->set_rules('amount','Amount','trim|xss_clean|required|is_natural_no_zero');
                        //$this->form_validation->set_rules('installment','Installment','trim|xss_clean|required|numeric');
                        $this->form_validation->set_rules('descripation','Description','trim|xss_clean');
                        //$this->form_validation->set_rules('frmdate','From Date','trim|xss_clean|required');
                        //$this->form_validation->set_rules('todate','To Date','trim|xss_clean|required');
		}

                //if form validation true

                if($this->form_validation->run()==TRUE){
              		$data = array(
                        	'fm_programid'=>$_POST['program'],
                                'fm_acadyear'=>$_POST['acadyear'],
                                'fm_semester'=>$_POST['semester'],
                                'fm_category'=>$_POST['category'],
                                'fm_gender'=>$_POST['gender'],
                                'fm_head'=>ucwords(strtolower($_POST['head'])),
                                'fm_amount'=>$_POST['amount'],
                                //'fm_installment'=>ucwords(strtolower($_POST['installment'])),
                                'fm_desc'=>$_POST['description'],
                             // 'fm_frmdate'=>$_POST['frmdate'],
                              //'fm_todate'=>$_POST['todate'],
                        );
                        $fmflag=$this->common_model->insertrec('fees_master', $data);
                	if (!$fmflag)
                	{
                    		$this->logger->write_logmessage("insert","Trying to add fees with head  ", "fees is not added ".$_POST['head']);
                    		$this->logger->write_dblogmessage("insert","Trying to add fees with head", "Fees is not added ".$_POST['head']);
                    		$this->session->set_flashdata('err_message','Error in adding fees with head - '.$_POST['head']  , 'error');
                    		redirect('setup/fees');

                	}
                	else{
                    		$this->logger->write_logmessage("insert","Add fees with head ", "Fees".$_POST['head']." added  successfully...");
                    		$this->logger->write_dblogmessage("insert","Add fees with head ", "Fees ".$_POST['head']."added  successfully...");
                    		$this->session->set_flashdata("success", " Program fees add successfully... head is ".$_POST['head']);
                    		redirect("setup/displayfees");
                	}
		}
  		$this->load->view('setup/fees');  
		
	}
	/** This function Display the fees with headwise list records */
        public function displayfees() {
        	$this->fmresult = $this->common_model->get_list('fees_master');
	        $this->logger->write_logmessage("view"," View fees list head wise", "Fees setting details...");
        	$this->logger->write_dblogmessage("view"," View fees list head wise", "Fees setting details...");
	        $this->load->view('setup/displayfees');
        }
	/* this function is used for delete fees with headwise record */
        public function delete_fees($id) {

	        $fmdflag=$this->common_model->deleterow('fees_master','fm_id', $id);
          	if(!$fmdflag)
          	{
           		$this->logger->write_message("error", "Error  in deleting role " ."[fm_id:" . $id . "]");
	            	$this->logger->write_dbmessage("error", "Error  in deleting role "." [fm_id:" . $id . "]");
        	    	$this->session->set_flashdata('err_message', 'Error in Deleting role - ', 'error');
            		redirect('setup/displayfees');
	           	//return;
        	  }
	          else{
        	    	$this->logger->write_logmessage("delete", "Deleted   fees " . "[fm_id:" . $id . "] deleted successfully.. " );
            		$this->logger->write_dblogmessage("delete", "Deleted fees" ." [fm_id:" . $id . "] deleted successfully.. " );
	            	$this->session->set_flashdata("success", "Program Fees record deleted successfully..." );
        	    	redirect('setup/displayfees');
          	}
          $this->load->view('setup/displayfees',$data);
	}

 
	public function editfees($id) {

	$fmrow=$this->common_model->get_listrow('fees_master','fm_id', $id);
        if ($fmrow->num_rows() < 1)
        {
            redirect('setup/editfees');
        }
        $fm_data = $fmrow->row();

        /* Form fields */
          $data['fm_programid'] = array(
            'name' => 'fm_programid',
            'id' => 'prgcode',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('program','prg_name','prg_id',$fm_data->fm_programid)->prg_name,
	    'readonly' => 'readonly'
          );
		
	  $data['fm_acadyear'] = array(
              'value' => $fm_data->fm_acadyear,
	  );
          $data['fm_semester'] = array(
            'value' => $fm_data->fm_semester,
       	  );
          $data['fm_category'] = array(
            'name' => 'fm_category',
            'id' => 'fm_category',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('category','cat_name','cat_id',$fm_data->fm_category)->cat_name,
	    'readonly' => 'readonly'
          );
	  $data['fm_gender'] = array(
           'value' => $fm_data->fm_gender,
	  );
	  $data['fm_head'] = array(
           'name' => 'fm_head',
            'id' => 'fm_head',
           'maxlength' => '50',
           'size' => '40',
           'value' => $fm_data->fm_head,
       	  );
	  $data['fm_amount'] = array(
           'name' => 'fm_amount',
            'id' => 'fm_amount',
           'maxlength' => '50',
           'size' => '40',
           'value' => $fm_data->fm_amount,
      	  );
	  /*$data['fm_installment'] = array(
           'name' => 'fm_installment',
            'id' => 'fm_installment',
           'maxlength' => '50',
           'size' => '40',
           'value' => $fm_data->fm_installment,
          );*/
	   $data['fm_desc'] = array(
           'name' => 'fm_desc',
            'id' => 'fm_desc',
           'maxlength' => '50',
           'size' => '40',
           'value' => $fm_data->fm_desc,
          );
/*	  $data['fm_frmdate'] = array(
           'name' => 'fm_frmdate',
            'id' => 'fm_frmdate',
           'maxlength' => '50',
           'size' => '40',
           'value' => $editeset_data->fm_frmdate,
       	   );
    	   $data['fm_todate'] = array(
           'name' => 'fm_todate',
            'id' => 'fm_todate',
           'maxlength' => '50',
           'size' => '40',
           'value' => $editeset_data->fm_todate,

        );
*/
	
	$data['id'] = $id;

         /*Form Validation*/


	  	$this->form_validation->set_rules('program','Program Name','trim|xss_clean');
                $this->form_validation->set_rules('acadyear','Academic Year','trim|alpha_numeric_spaces|xss_clean');
                $this->form_validation->set_rules('semester','Semester','trim|xss_clean');
                $this->form_validation->set_rules('category','Category','trim|xss_clean');
                $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
                $this->form_validation->set_rules('head','Head','trim|xss_clean');
                $this->form_validation->set_rules('amount','Amount','trim|xss_clean|is_natural_no_zero');
               // $this->form_validation->set_rules('installment','Installment','trim|xss_clean|numeric');
                $this->form_validation->set_rules('descripation','Description','trim|xss_clean');

        /* Re-populating form */
        if ($_POST)
        {
	    print_r($_POST);
            $data['fm_programid']['value'] = $this->input->post('programid', TRUE);
            $data['fm_acadyear']['value'] = $this->input->post('fm_acadyear', TRUE);
	    $data['fm_semester']['value'] = $this->input->post('fm_semester', TRUE);
            $data['fm_category']['value'] = $this->input->post('category', TRUE);
            $data['fm_gender']['value'] = $this->input->post('fm_gender', TRUE);
            $data['fm_head']['value'] = $this->input->post('fm_head', TRUE);
            $data['fm_amount']['value'] = $this->input->post('fm_amount', TRUE);
          //$data['fm_installment']['value'] = $this->input->post('fm_installment', TRUE);
	    $data['fm_desc']['value'] = $this->input->post('fm_desc', TRUE);

        }
        if ($this->form_validation->run() ==FALSE )
        {
                $this->session->set_flashdata(validation_errors(), 'error');
                $this->load->view('setup/editfees', $data);
        }
        else{
            $programname1=$this->input->post('fm_programid', TRUE);
	    $programname=$this->common_model->get_listspfic1('program','prg_id','prg_name',$programname1)->prg_id;
            $acadyear = $this->input->post('fm_acadyear', TRUE);
            $semester = strtoupper($this->input->post('fm_semester', TRUE));
            $category1 = $this->input->post('fm_category', TRUE);
	    $category = $this->common_model->get_listspfic1('category','cat_id','cat_name',$category1)->cat_id;
            $gender = ucwords(strtolower($this->input->post('fm_gender', TRUE)));
            $head= ucwords(strtolower($this->input->post('fm_head', TRUE)));
            $amount = $this->input->post('fm_amount',TRUE);
           // $installment = $this->input->post('fm_installment', TRUE);
	    $description = $this->input->post('fm_desc', TRUE);

            $logmessage = "";
            if($fm_data->fm_programid != $programname)
                $logmessage = $logmessage ." update program name " .$fm_data->fm_programid. " changed by " .$programname;
            if($fm_data->fm_acadyear != $acadyear)
                $logmessage = $logmessage ." update academic year " .$fm_data->fm_acadyear. " changed by " .$acadyear;
            if($fm_data->fm_semester != $semester)
                $logmessage = $logmessage ." update semester " .$fm_data->fm_semester. " changed by " .$semester;
            if($fm_data->fm_category != $category)
                $logmessage = $logmessage ." update category " .$fm_data->fm_category. " changed by " .$category;
            if($fm_data->fm_gender != $gender)
                $logmessage = $logmessage ." update gender " .$fm_data->fm_gender. " changed by " .$gender;
            if($fm_data->fm_head != $head)
                $logmessage = $logmessage ." update head " .$fm_data->fm_head. " changed by " .$head;
	    if($fm_data->fm_amount != $head)
                $logmessage = $logmessage ." update amount " .$fm_data->fm_amount. " changed by " .$amount;
	    //if($fm_data->fm_installment != $installment)
              //  $logmessage = $logmessage ." update installment " .$fm_data->fm_installment. " changed by " .$installment;
	    if($fm_data->fm_desc != $description)
                $logmessage = $logmessage ." update description " .$fm_data->fm_desc. " changed by " .$description;
	

	$update_data = array(
               'fm_programid' => $programname,
               'fm_acadyear' => $acadyear,
               'fm_semester' => $semester,
               'fm_category'  => $category,
               'fm_gender'  => $gender,
               'fm_head' => $head,
               'fm_amount' => $amount,
              // 'fm_installment' => $installment,
               'fm_desc' => $description
             );
           $fmflag=$this->common_model->updaterec('fees_master', $update_data, 'fm_id', $id);
           if(!$fmflag)
              {
                $this->logger->write_logmessage("error","Error in update Fees ", "Error in Fees record update". $logmessage );
                $this->logger->write_dblogmessage("error","Error in update Fees ", "Error in Fees record update". $logmessage );
                $this->session->set_flashdata('err_message','Error updating Fees - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editfees', $data);
              }
            else{
                $this->logger->write_logmessage("update","Edit Fees", "Fees headwise record updated successfully..". $logmessage );
                $this->logger->write_dblogmessage("update","Edit Fees", "Fees headwise record updated successfully..". $logmessage );
                $this->session->set_flashdata('success', "Program fees record updated successfully..." );
                redirect('setup/displayfees');
                }
	  }
    }
}

