<?php

/* 
 * @name Setup.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  
 * @author Manorama Pal(palseema30@gmail.com)  add Email setting,Authority
 * @author Sharad Singh(sharad23nov@yahoo.com) add program, add subject
 * @author Om Prakash(omprakashkgp@gmail.com)=> Add Category, Add DDO, DDO Archive, Add Scheme
 * @author Kishore kr shukla(kishore.shukla@gmail.com) add role
 * @author Raju Kamal(kamalraju8@gmail.com)    add department
 * @author Vijay(vijay.pal428@gmail.com)       add program fees
 * @author Raju Kamal(kamalraju8@gmail.com)    category program 
 * @author Neha Khullar(nehukhullar@gmail.com) add bankdetails
 * @author Neha Khullar(nehukhullar@gmail.com) department Archive
 * @author Abhay Throne(kumar.abhay.4187@gmail.com)[bank detail archive]
 * @Modification : Om Prakash(omprakashkgp@gmail.com) Dec-2017, check for duplicate entry,
 * Scheme archive, salary grade master archive
 * @author Neha Khullar(nehukhullar@gmail.com) add Society  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Setup extends CI_Controller
{
    function __construct() {
        parent::__construct();
        $this->load->model('login_model','lgnmodel'); 
  	$this->load->model('common_model');
    	$this->load->model('SIS_model'); 
        $this->load->model('PICO_model');   //changed to PICO insted of 
        $this->load->model('dependrop_model','depmodel');
        $this->load->model('university_model','unimodel');
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
            $this->form_validation->set_rules('password','Password','trim|xss_clean|required');
            $this->form_validation->set_rules('sendername','Sendername','trim|xss_clean|required');
            $this->form_validation->set_rules('senderemail','Senderemail','trim|xss_clean|valid_email');
            $this->form_validation->set_rules('modulename','Modulename','trim|xss_clean');
            //if form validation true
            if($this->form_validation->run()==TRUE){
            
                $data = array(
                    'emailprotocol'=>$_POST['emailprotocol'],
                    'emailhost'=>$_POST['emailhost'],
                    'emailport'=>$_POST['emailport'],
                    'username'=>$_POST['username'],
                    'password'=>$_POST['password'],
                    'sendername'=>$_POST['sendername'],
                    'senderemail'=>$_POST['senderemail'],
                    'modulename'=>ucwords(strtolower($_POST['modulename'])),
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
                
        $data['senderemail'] = array(
           'name' => 'senderemail',
           'id' => 'senderemail',
           'maxlength' => '255',
           'size' => '40',
           'value' => $editeset_data->senderemail,
        );

        $data['modulename'] = array(
           'name' => 'modulename',
           'id' => 'modulename',
           'maxlength' => '255',
           'size' => '40',
           'value' => $editeset_data->modulename,
        );
        $data['id'] = $id;
           
        /*Form Validation*/
        $this->form_validation->set_rules('emailprotocol','Emailprotocol','trim|required|alpha|xss_clean');
        $this->form_validation->set_rules('emailhost','Emailhost','trim|required|xss_clean');
        $this->form_validation->set_rules('emailport','Emailport','trim|required|min_length[2]|max_length[5]|is_numeric|xss_clean',
        array ('required' => ' Email Port -- insert numeric value only'));
        $this->form_validation->set_rules('username','Username','trim|xss_clean|required');
        $this->form_validation->set_rules('password','Password','trim|required|xss_clean');
        $this->form_validation->set_rules('sendername','Sendername','trim|required|xss_clean');
        $this->form_validation->set_rules('senderemail','Senderemail','trim|valid_email|xss_clean');
        $this->form_validation->set_rules('modulename','modulename','trim|xss_clean');
                   
        /* Re-populating form */
        if ($_POST)
        {
            $data['emailprotocol']['value'] = $this->input->post('emailprotocol', TRUE);
            $data['emailhost']['value'] = $this->input->post('emailhost', TRUE);
            $data['emailport']['value'] = $this->input->post('emailport', TRUE);
            $data['username']['value'] = $this->input->post('username', TRUE);
            $data['password']['value'] = $this->input->post('password', TRUE);
            $data['sendername']['value'] = $this->input->post('sendername', TRUE);
            $data['senderemail']['value'] = $this->input->post('senderemail', TRUE);
            $data['modulename']['value'] = $this->input->post('modulename', TRUE);
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
            $data_esenderemail = $this->input->post('senderemail', TRUE);
            $data_emodulename = ucwords(strtolower($this->input->post('modulename', TRUE)));
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
            if($editeset_data->senderemail != $data_esenderemail)
                $logmessage = $logmessage ." Sender Email " .$editeset_data->senderemail. " changed by ". $data_esenderemail;
            if($editeset_data->modulename != $data_emodulename)
		    $logmessage = $logmessage ." Module Name " .$editeset_data->modulename. " changed by ". $data_emodulename;

            $update_data = array(
               'emailprotocol' => $data_eprotocol,
               'emailhost' => $data_ehost,
               'emailport' => $data_eport,
               'username'  => $data_eusername,
               'password'  => $data_epassword,
               'sendername' => $data_esendername,
               'senderemail' => $data_esenderemail,
               'modulename' => $data_emodulename,
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
        $this->scresult = $this->common_model->get_listspfic2('study_center','sc_id', 'sc_name');
        $this->deptresult = $this->common_model->get_listspfic2('Department','dept_id', 'dept_name');
        $data['title'] = 'Add program';
        if(isset($_POST['program'])) 
        {
            $this->form_validation->set_rules('prgcampus','Campus','trim|xss_clean|required');
            $this->form_validation->set_rules('prgdepartment','Department','trim|xss_clean|required');
            $this->form_validation->set_rules('prgcat','Program Category','trim|xss_clean|required');
            $this->form_validation->set_rules('prgname','Program Name','trim|xss_clean|required');
            $this->form_validation->set_rules('prgbranch','Program Branch','trim|xss_clean|required');
            $this->form_validation->set_rules('prgpattern','Program Pattern','trim|xss_clean|required');
            $this->form_validation->set_rules('prgseat','Seat Available','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('prgcredit','Program Credit','trim|xss_clean|numeric');
            $this->form_validation->set_rules('prgcode','Program Code','trim|xss_clean|required');
            $this->form_validation->set_rules('prgshort','Program Short','trim|xss_clean|required');
            $this->form_validation->set_rules('prgdesc','Program Description','trim|xss_clean');
            $this->form_validation->set_rules('prgmaxtime','Program Min Time','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('prgmintime','Program Max Time','trim|xss_clean|required|numeric');

            
            $prgcampus = $this->input->post('prgcampus');
            $prgdepartment = $this->input->post('prgdepartment');
            $prgcat = $this->input->post('prgcat');
            $prgname = $this->input->post('prgname');
            $prgseat = $this->input->post('prgseat');
            $prgcredit = $this->input->post('prgcredit');
            $prgbranch = $this->input->post('prgbranch');
            $prgpattern = $this->input->post('prgpattern');
            $prgcode = $this->input->post('prgcode');
            $prgshort = $this->input->post('prgshort');
            $prgdesc = $this->input->post('prgdesc');
            $prgmaxtime = $this->input->post('prgmaxtime');
            $prgmintime = $this->input->post('prgmintime');
            $prgcrtid = $this->session->userdata('username'); 
            $currdate = date("Y/m/d");
            $prgdate  = $currdate;

            /* check for duplicate record
            $result = $this->common_model->isduplicate('program','prg_category',$prgcat);
            if($result == 1)
            {
                $this->session->set_flashdata('error','Program category <b>' .$prgcat . '</b> already exist' );
                redirect('setup/program');
            }*/

        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/program');
            return;
        }
        else
        {
            $data = array(
                'prg_scid'=>$prgcampus,
                'prg_deptid'=>$prgdepartment,
                'prg_category'=>ucwords(strtolower($prgcat)),
                'prg_name'=>ucwords(strtolower($prgname)),
                'prg_branch'=>ucwords(strtolower($prgbranch)),
                'prg_pattern'=>ucwords(strtolower($prgpattern)),
                'prg_seat'=>$prgseat,
                'prg_credit'=>$prgcredit,
                'prg_code'=>strtoupper($prgcode),
                'prg_short'=>strtoupper($prgshort),
                'prg_desc'=>$prgdesc,
                'prg_mintime'=>$prgmintime,
                'prg_maxtime'=>$prgmaxtime,
                'creatorid'=>$prgcrtid,
                'createdate'=>$prgdate
            );
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
        
        $data['prgcampus'] = array(
            'name' => 'prgcampus',
            'id' => 'prgcampus',
            'size' => '35',
            'value' => $this->common_model->get_listspfic1('study_center','sc_name','sc_id',$program_data->prg_scid)->sc_name,
            'readonly'=>'true',
            );
        $data['prgdepartment'] = array(
            'name' => 'prgdepartment',
            'id' => 'prgdepartment',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('Department','dept_name','dept_id',$program_data->prg_deptid)->dept_name,
            'readonly'=>'true',
            );
        
        $data['prgcat'] = array('name' => 'prgcat','id' => 'prgcat','maxlength' => '100','size' => '40','value' => $program_data->prg_category,'readonly'=>'true');
        $data['prgname'] = array('name' => 'prgname','id' => 'prgname','maxlength' => '100','size' => '40','value' => $program_data->prg_name,'',);
        $data['prgbranch'] = array('name' => 'prgbranch','id' => '','maxlength' => '100','size' => '40','value' => $program_data->prg_branch,'',);
        $data['prgpattern'] = array('name' => 'prgpattern','id' => '','maxlength' => '100','size' => '40','value' => $program_data->prg_pattern,'',);
        $data['prgseat'] = array('name' => 'prgseat','id' => '','maxlength' => '100','size' => '40','value' => $program_data->prg_seat,'',);
        $data['prgcredit'] = array('name' => 'prgcredit','id' => '','maxlength' => '100','size' => '40','value' => $program_data->prg_credit,'',);
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
        $this->form_validation->set_rules('prgpattern','Program Pattern','trim|xss_clean|required');
        $this->form_validation->set_rules('prgseat','Seat Available','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('prgcredit','Program Credit','trim|xss_clean|numeric');
        $this->form_validation->set_rules('prgcode','Program Code','trim|xss_clean|required');
        $this->form_validation->set_rules('prgshort','Program Short','trim|xss_clean|required');
        $this->form_validation->set_rules('prgdesc','Program Description','trim|xss_clean');
        $this->form_validation->set_rules('prgmaxtime','Program Min Time','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('prgmintime','Program Max Time','trim|xss_clean|required|numeric');

        /* update Program Records */

        if($_POST)
        {
           // $data['prgcampus']['value'] = $this->input->post('prgcampus', TRUE);
            //$data['prgdepartment']['value'] = $this->input->post('prgdepartment', TRUE);
           // $data['prgcat']['value'] = $this->input->post('prgcat', TRUE);
            $data['prgname']['value'] = $this->input->post('prgname', TRUE);
            $data['prgbranch']['value'] = $this->input->post('prgbranch', TRUE);
            $data['prgpattern']['value'] = $this->input->post('prgpattern', TRUE);
            $data['prgseat']['value'] = $this->input->post('prgseat', TRUE);
            $data['prgcredit']['value'] = $this->input->post('prgcredit', TRUE);
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
            $data_prgcampus = $this->input->post('prgcampus', TRUE);
            $data_prgdepartment = $this->input->post('prgdepartment', TRUE);
            $data_prgcat = $this->input->post('prgcat', TRUE);
            $data_prgname = $this->input->post('prgname', TRUE);
            $data_prgbranch = $this->input->post('prgbranch', TRUE);
            $data_prgpattern = $this->input->post('prgpattern', TRUE);
            $data_prgseat = $this->input->post('prgseat', TRUE);
            $data_prgcredit = $this->input->post('prgcredit', TRUE);
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
                $logmessage = $logmessage ." Program Name " .$program_data->prg_name. " changed by " .$data_prgname;
            if($program_data->prg_branch != $data_prgbranch)
                $logmessage = $logmessage ." Program Branch " .$program_data->prg_branch. " changed by " .$data_prgbranch;
            if($program_data->prg_pattern != $data_prgpattern)
                $logmessage = $logmessage ." Program Pattern " .$program_data->prg_pattern. " changed by " .$data_prgpattern;
            if($program_data->prg_seat != $data_prgseat)
                $logmessage = $logmessage ." Seat Available " .$program_data->prg_seat. " changed by ". $data_prgseat;
            if($program_data->prg_short != $data_prgshort)
                $logmessage = $logmessage ." Program Short Name ".$program_data->prg_short. " changed by ".$data_prgshort;
            if($program_data->prg_code != $data_prgcode)
                $logmessage = $logmessage ." Program Code " .$program_data->prg_code ." changed by ".$data_prgcode;
            if($program_data->prg_desc != $data_prgdesc)
                $logmessage = $logmessage . "Program Desc " .$program_data->prg_desc. " changed by " .$data_prgdesc;
            if($program_data->prg_mintime != $data_prgmintime)
                $logmessage = $logmessage . "Program Min Time ".$program_data->prg_mintime ." changed by " .$data_prgmintime;
            if($program_data->prg_maxtime != $data_prgmaxtime)
                $logmessage = $logmessage . "Program Max Time " .$program_data->prg_maxtime. " changed by " .$data_prgmaxtime;
            if($program_data->prg_credit != $data_prgcredit)
                $logmessage = $logmessage . "Program Credit " .$program_data->prg_credit. " changed by " .$data_prgcredit;
              
                
                

            /* update program records*/

            $this->db->trans_start();
            $update_prgdata = array(
//                'prg_scid' => $program_data->prg_scid,
  //              'prg_deptid' => $program_data->prg_deptid,
               // 'prg_category' => ucwords(strtolower($data_prgcat)),
                'prg_name' => ucwords(strtolower($data_prgname)),
                'prg_branch' => ucwords(strtolower($data_prgbranch)),
                'prg_pattern' => $data_prgpattern,
                'prg_seat' => $data_prgseat,
                'prg_credit' => $data_prgcredit,
                'prg_code' => strtoupper($data_prgcode),
                'prg_short' => strtoupper($data_prgshort),
                'prg_desc' => $data_prgdesc,
                'prg_mintime' => $data_prgmintime,
                'prg_maxtime' => $data_prgmaxtime
            );

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
                redirect('setup/viewprogram');
            }
        }
        $this->load->view('setup/editprogram',$data);
    }

    /* deletion of program */

    public function deleteprogram($prg_id)
    {
       $program_q=$this->common_model->get_listrow('program','prg_id', $prg_id);
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
            $this->session->set_flashdata('Error in deleting program - ' . $program_data->prg_category.'and'.$program_data->prg_name . '.', 'error');
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
        $this->form_validation->set_rules('subdesc','Subject Description','trim|xss_clean');
        $this->form_validation->set_rules('subext1','Subject Ext1','trim|xss_clean');
        $this->form_validation->set_rules('subext2','Subject Ext2','trim|xss_clean');

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
        $this->form_validation->set_rules('subdesc','Subject Description','trim|xss_clean');
        $this->form_validation->set_rules('subext1','Subject Ext1','trim|xss_clean');
        $this->form_validation->set_rules('subext2','Subject Ext2','trim|xss_clean');

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
            $this->form_validation->set_rules('cname','Category Name','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('ccode','Category Code','trim|xss_clean|required|alpha_dash|callback_value_exists');
            $this->form_validation->set_rules('csname','Category Short Name','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('cdesc','Category Description','trim|xss_clean|alpha_numeric_spaces');

            if($this->form_validation->run()==TRUE){

	    $catname = $this->input->post("cname");
	    $catcode = $this->input->post("ccode");
	    $catshort = $this->input->post("csname");
	    
            $cdatacheck = array('cat_name'=>ucwords(strtolower($_POST['cname'])) , 'cat_code'=>strtoupper($_POST['ccode']), 'cat_short'=>strtoupper($_POST['csname']) );
            $data = array(
                'cat_name'=>ucwords(strtolower($_POST['cname'])),
                'cat_code'=>strtoupper($_POST['ccode']),
                'cat_short'=>strtoupper($_POST['csname']),
                'cat_desc'=>$_POST['cdesc']

            );
	   $catdatadup = $this->common_model->isduplicatemore('category', $cdatacheck);

                   if($catdatadup == 1 ){

                        $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Category Name' = $catname, 'Category code' = $catcode , 'Category Short Name' =$catshort .");
                        redirect('setup/category');
                        return;
                 }
           else{
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

           // $cdatacheck = array('cat_name'=>ucwords(strtolower($_POST['cname'])) , 'cat_code'=>strtoupper($_POST['ccode']), 'cat_short'=>strtoupper($_POST['csname']) );
            $update_data = array(
               'cat_name' => $data_cname,
               'cat_code' => $data_ccode,
               'cat_short' => $data_csname,
               'cat_desc'  => $data_cdesc
            );

	   $catdatadup = $this->common_model->isduplicatemore('category', $update_data);
               if($catdatadup == 1 ){

                        $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Category Name' = $data_cname, 'Category code' = $data_ccode , 'Category Short Name' =$data_csname .");
                        redirect('setup/displaycategory/');
                        return;
                }
           else{
	   if($category_data->cat_code != $data_ccode){
	           $categoryflag = $this->common_model->isduplicate('category','cat_code', $data_ccode);
                      if($categoryflag == 1)
                        {
                                $this->session->set_flashdata("err_message", "Category Code = $data_ccode , is already exist .");
                                $this->load->view('setup/editcategory', $data);  
				return;
                        }
                   else{
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
		 }
	       }
	   else{	
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
	   }	
	}
        }//else
        redirect('setup/editcategory/');
    }


/** This function check for duplicate entry
    * @return type
    */

 public function value_exists($key)
 {
     $is_exist = $this->common_model->isduplicate('category','cat_code',$key);

     if($is_exist) {
        $this->form_validation->set_message(
            'value_exists', 'Category code '. $key. '  is already exist.'
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
	        $this->authresult = $this->lgnmodel->get_listspfic2('authorities','id','name');

            
	   	if(isset($_POST['dept'])) { 
                	$this->form_validation->set_rules('authorities','Authorities Name','trim|xss_clean|required');
	                $this->form_validation->set_rules('orgprofile','University Name','trim|xss_clean|required');
        	        $this->form_validation->set_rules('studycenter','Campus Name','trim|xss_clean|required');
                	$this->form_validation->set_rules('dept_schoolcode','School Code','trim|xss_clean|alpha_numeric');
	                $this->form_validation->set_rules('dept_schoolname','School Name','trim|xss_clean');
        	        $this->form_validation->set_rules('dept_code','Department Code','trim|xss_clean|required|callback_value_existsDept');
                	$this->form_validation->set_rules('dept_name','Department Name','trim|xss_clean|required');
	                $this->form_validation->set_rules('dept_mail','Department Email','trim|xss_clean|valid_email');
        	        $this->form_validation->set_rules('dept_short','Department Nick','trim|xss_clean|required');
                	$this->form_validation->set_rules('dept_descripation','Department Description','trim|xss_clean');
                       
	                if($this->form_validation->run()==TRUE){

				$campcode = $this->input->post("studycenter");
				$campname = $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$campcode)->sc_name;
				$authid = $this->input->post("authorities");
				$authname = $this->lgnmodel->get_listspfic1('authorities','name','id',$authid)-> name;
		 		$deptbame = $this->input->post("dept_name");
				$deptcode = strtoupper($this->input->post("dept_name"));

                		if (($_POST['orgprofile'] != '') || ($_POST['studycenter'] != '')){  
               
					$ddatacheck = array('dept_uoid'=>strtoupper($_POST['authorities']), 'dept_orgcode'=>strtoupper($_POST['orgprofile']), 'dept_sccode'=>strtoupper($_POST['studycenter']), 'dept_code'=>strtoupper($_POST['dept_code']), 'dept_name'=>ucwords(strtolower($_POST['dept_name'])) );
               
				     	$data = array(
		                                'dept_uoid'=>strtoupper($_POST['authorities']),
                		                'dept_orgcode'=>strtoupper($_POST['orgprofile']),
                                		'dept_sccode'=>strtoupper($_POST['studycenter']),
		                                'dept_schoolcode'=>strtoupper($_POST['dept_schoolcode']),
                		                'dept_schoolname'=>ucwords(strtolower($_POST['dept_schoolname'])),
                                		'dept_code'=>strtoupper($_POST['dept_code']),
		                                'dept_name'=>ucwords(strtolower($_POST['dept_name'])),
                		                'dept_short'=>strtoupper($_POST['dept_short']),
                                		'dept_description'=>$_POST['dept_descripation'],
		                                'dept_email'=>strtolower($_POST['dept_mail']),
		                        );
		   			$deptdatadup = $this->common_model->isduplicatemore('Department', $ddatacheck);
			                if($deptdatadup == 1 ){

                                  		$this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Campus Name' = $campname , 'Authorities Name' = $authname , 'Department Name' =$deptbame .");
                                  		redirect('setup/dept');
                                  		return;
                       			}
                   			else{

                        			$deptflag=$this->common_model->insertrec('Department', $data) ;
                        			if(!$deptflag)
                        			{
                                			$this->logger->write_logmessage("insert"," Error in adding Department ", " Department data insert error . ".$dept_name  );
                                			$this->logger->write_dblogmessage("insert"," Error in adding Department ", " Department data insert error . ".$dept_name );
                              	  			$this->session->set_flashdata('err_message','Error in adding Department - ' . $dept_name . '.', 'error');
			                                redirect('setup/dispdepartment');
                        			}
                        			else{
							$deptemail = $this->input->post("dept_mail");
							if(!empty( $deptemail)){
								$isdup= $this->lgnmodel->isduplicate('edrpuser','username',$deptemail);
				                                $parts = explode("@", $deptemail);
                                				$ename = $parts[0];
				                                $passwd=md5($ename);
                                				if(!$isdup){
				                                    	$dataeu = array(
					                                        'username'=> $deptemail,
                                        					'password'=> $passwd,
					                                        'email'=> $deptemail,
                                        					'componentreg'=> '*',
					                                        'mobile'=>'',
                                        					'status'=>1,
					                                        'category_type'=>'HOD',
                                        					'is_verified'=>1
					                                );
                                    						/*insert record in edrpuser table*/
					                                $userflageu=$this->lgnmodel->insertrec('edrpuser', $dataeu);
                                    					$userid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$deptemail)->id;
					                                if($userflageu){

					                                       // insert into  user profile db1
                                        					$dataup = array(
					                                        	'userid' => $userid,
						                                        'firstname' => 'Head of the Department',
                                            						'lang' => 'english',
                                            						'mobile' => '',
                                            						'status' => 1
                                        					);
                                        					$userflagup=$this->lgnmodel->insertrec('userprofile', $dataup);
									}
								}//isdup email
								else{
                                        				$userid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$deptemail)->id;
                                				}
                                        			// check for duplicate in hod list table
								$campusid = $this->common_model->get_listspfic1('study_center','sc_id','sc_code',$campcode)->sc_id;
								$deptid = $this->common_model->get_listspfic1('Department','dept_id','dept_code',$deptcode)->dept_id;
                                        			$duphod = array('hl_userid' => $userid, 'hl_scid' => $campusid,'hl_deptid'=> $deptid);
                                        			$isduphod= $this->sismodel->isduplicatemore('hod_list',$duphod);
                                        			if(!$isduphod){
                                            				$usr =$this->session->userdata('username');
                                            				$datahod = array(
				                                                'hl_userid'=> $userid,
                                                				'hl_empcode'=> '',
                                                				'hl_deptid'=> $deptid,
                                                				'hl_scid'=> $campusid,
                                                				'hl_uopid' => $authid,
                                                				'hl_datefrom'=> date('y-m-d'),
                                                				'hl_dateto'=> '',
                                                				'hl_status'=> 'full time',
                                                				'hl_creatorid'=> $usr,
				                                                'hl_creatordate'=> date('y-m-d'),
                                				                'hl_modifierid'=> $usr,
				                                                'hl_modifydate'=> date('y-m-d'),
                                			            	);
                                            				$hodlistflag=$this->sismodel->insertrec('hod_list', $datahod) ;
                                            				if($hodlistflag){
				                                                /* insert into user_role_type */
                                				                $dataurt = array(
                                                    					'userid'=> $userid,
                                                    					'roleid'=> 5,
                                                    					'deptid'=> $deptid,
                                                    					'scid'=>  $campusid,
                                                   					'usertype'=>'HoD'
                                                				);
                                                				$userflagurt=$this->sismodel->insertrec('user_role_type', $dataurt) ;
                                                		//		if($userflagurt){
								//		}
									}
								}// end dup hod
							} //end check for empty email
                                //'dept_mail'=>strtolower($_POST['dept_mail']),
                                $this->logger->write_logmessage("insert"," add Department ", "Department record added successfully.".$dept_name  );
                                $this->logger->write_dblogmessage("insert"," add Department ", "Department record added successfully.".$dept_name );
                                $this->session->set_flashdata("success", "Department added successfully...");
                                redirect('setup/dispdepartment');
                           }
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
        $data['deptresult'] = $this->common_model->get_list('Department');
        $this->logger->write_logmessage("view"," View Department list", "department list display");
        $this->logger->write_dblogmessage("view"," View Department list", "department list display");
       $this->load->view('setup/dispdepartment',$data);
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

	$this->authresult = $this->lgnmodel->get_listspfic2('authorities','id','name');
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
  //          'maxlength' => '50',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('org_profile','org_name','org_code',$dept_data->dept_orgcode)->org_name,
	    'readonly' => 'readonly'
             );
         $data['deptsccode'] = array(
            'name' => 'deptsccode',
            'id' => 'deptsccode',
      //      'maxlength' => '50',
            'size' => '40',
           'value' => $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$dept_data->dept_sccode)->sc_name,
	    'readonly' => 'readonly'
        );


         $data['authorities'] = array(
            'name' => 'authorities',
            'id' => 'authorities',
    //        'maxlength' => '50',
            'size' => '40',
            'value' => $this->lgnmodel->get_listspfic1('authorities','name','id',$dept_data->dept_uoid)-> name,
          'readonly' => 'readonly'
        );


        $data['deptschoolcode'] = array(
            'name' => 'deptschoolcode',
            'id' => 'deptschoolcode',
        //    'maxlength' => '50',
            'size' => '40',
            'value' => $dept_data->dept_schoolcode,
        );
        $data['deptschoolname'] = array(
           'name' => 'deptschoolname',
           'id' => 'deptschoolname',
          // 'maxlength' => '50',
           'size' => '40',
           'value' => $dept_data->dept_schoolname,
        );
        $data['deptcode'] = array(
           'name' => 'deptcode',
           'id' => 'deptcode',
//           'maxlength' => '6',
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
	$data['deptmail'] = array(
           'name' => 'deptemail',
           'id' => 'deptemail',
           'maxlength' => '255',
           'size' => '40',
           'value' => $dept_data->dept_email,
		'readonly' => 'readonly',
        );
        $data['id'] = $id;

        /*Form Validation*/
        
       // $this->form_validation->set_rules('dept_orgcode','University','trim|xss_clean|required');
       // $this->form_validation->set_rules('dept_sccode','Campus','trim|xss_clean|required');
        $this->form_validation->set_rules('deptschoolcode','School code','trim|xss_clean');
        $this->form_validation->set_rules('deptschoolname','School name','trim|xss_clean');
        $this->form_validation->set_rules('deptcode','Department code','trim|xss_clean|required');
        $this->form_validation->set_rules('deptname','Department name','trim|xss_clean|required');
        $this->form_validation->set_rules('deptshort','Department nick','trim|xss_clean|required');
        $this->form_validation->set_rules('deptdescription','Department description','trim|xss_clean');
     
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
		
            $deptorgcode = strtoupper($this->input->post('deptorgcode', TRUE));
            $deptsccode = strtoupper($this->input->post('deptsccode', TRUE));
            $deptuoid = strtoupper($this->input->post('authorities', TRUE));
            $schoolcode = strtoupper($this->input->post('deptschoolcode', TRUE));
	    $schoolname = ucwords(strtolower($this->input->post('deptschoolname', TRUE)));
            $departmentcode = strtoupper($this->input->post('deptcode', TRUE));
            $departmentname = ucwords(strtolower($this->input->post('deptname', TRUE)));
            $departmentshort =strtoupper($this->input->post('deptshort', TRUE));
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
         // insert data into department archive table

	   $ddatachecke = array('dept_uoid'=>$deptuoid, 'dept_orgcode'=>$deptorgcode, 'dept_sccode'=>$deptsccode, 'dept_code'=>$departmentcode, 'dept_name'=>$departmentname );

        $insertdata= array(
                 'depta_deptid'=>$dept_data->dept_id,
                 'depta_name'=>$dept_data->dept_name,
                 'depta_code'=>$dept_data->dept_code,
                 'depta_uoid'=>$dept_data->dept_uoid,
                 'depta_short'=>$dept_data->dept_short,
                 'depta_description'=>$dept_data->dept_description,
                 'depta_schoolname'=>ucwords(strtolower($dept_data->dept_schoolname)),
                 'depta_schoolcode'=>$dept_data->dept_schoolcode,
                 'depta_sccode'=>$dept_data->dept_sccode,
                 'depta_orgcode'=>$dept_data->dept_orgcode,
                 'creatorid'=>'SIS - '. $this->session->userdata('username'),
                 'createdate'=>date('y-m-d'),
        );
            $deptaflag=$this->common_model->insertrec('Department_archive', $insertdata);
            if(!$deptalag)
            {
                      $this->logger->write_dblogmessage("error","Error in insert in Department archive ", "Error in Department archive record insert". $logmessage );
            }else{
                     $this->logger->write_dblogmessage("insert","Insert Department archive", "Department headwise record inserted in department archive successfully..". $logmessage );
            }

            $update_data = array(
	       'dept_uoid'=>$deptuoid,
               'dept_schoolcode' => $schoolcode,
               'dept_schoolname' => $schoolname,
               'dept_code' => $departmentcode,
               'dept_name'  => $departmentname,
               'dept_short'  => $departmentshort,
               'dept_description' => $departmentdescription,
              // 'dept_sccode' => $deptsccode,
               //'dept_orgcode' => $deptorgcode
            );
	   $deptdatadupe = $this->common_model->isduplicatemore('Department', $ddatachecke);

           if($deptdatadupe == 1 ){
                       $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Campus Name' = $deptsccode , 'Authorities Name' = $deptuoid , 'Department Name' =$departmentname .");
                       redirect('setup/dispdepartment');
                       return;
           }
      else{
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
          redirect('setup/editdepartment');
        }
       
  }

/** This function check for duplicate entry
    * @return type
    */

 public function value_existsDept($key)
 {
     $is_exist = $this->common_model->isduplicate('Department','dept_code',$key);

     if($is_exist) {
        $this->form_validation->set_message(
            'value_existsDept', 'Department code '. $key. '  is already exist.'
        );
        return false;
    } else {
    return true;
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
                'role_name'=>$_POST['role_name'],
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

                'size' => '40',
                'value' => $editeset_data->role_name,

                );
        $data['role_desc'] = array(
           'name' => 'role_desc',
            'id' => 'role_desc',
   
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

            $data_erole = $this->input->post('role_name', TRUE);
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

    }//Add role function end

		
	//Fees Program set up

     public function fees() {
               
        	$this->prgresult = $this->common_model->get_listspfic2('program','prg_id', 'prg_name');
                $this->catresult = $this->common_model->get_listspfic2('category','cat_id','cat_name');


    		if(isset($_POST['fees'])) {
                        $this->form_validation->set_rules('program','Program Name','trim|xss_clean|required');
                        $this->form_validation->set_rules('acadyear','Academic Year','trim|xss_clean|required');
                        $this->form_validation->set_rules('semester','Semester','trim|xss_clean|required');
                        $this->form_validation->set_rules('category','Category','trim|xss_clean|required');
                        $this->form_validation->set_rules('gender','Gender','trim|xss_clean|required');
                        $this->form_validation->set_rules('head','Head','trim|xss_clean|required|callback_isheadExist');
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
       /** This function check for duplicate fees master
     * @return type
     */

    public function isheadExist($fm_head) {
        $is_exist = $this->common_model->isduplicate('fees_master','fm_head',$fm_head);
        if ($is_exist)
        {
            $this->form_validation->set_message('isheadExist', 'fees master for ' . $fm_head . ' already exist.');
                return false;
            }
            else {
                 return true;
            }
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

          $fm_data_q=$this->common_model->get_listrow('fees_master','fm_id', $id);
      if ($fm_data_q->num_rows() < 1)
        {
            redirect('setup/displayfees');
        }
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
	     'name' => 'fm_semester',
             'id' => 'fm_semester',
             'maxlength' => '50',
             'size' => '40',
             'value' => $fm_data->fm_semester,
             'readonly' => 'readonly'

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
	  	//$this->form_validation->set_rules('program','Program Name','trim|xss_clean|required');
                $this->form_validation->set_rules('fm_acadyear','Academic Year','trim|xss_clean|required');
               // $this->form_validation->set_rules('fm_semester','Semester','trim|xss_clean|required');
               // $this->form_validation->set_rules('category','Category','trim|xss_clean|required');
                $this->form_validation->set_rules('fm_gender','Gender','trim|xss_clean|required');
                $this->form_validation->set_rules('fm_head','Head','trim|xss_clean|required');
                $this->form_validation->set_rules('fm_amount','Amount','trim|xss_clean|is_natural_no_zero|required');
               // $this->form_validation->set_rules('installment','Installment','trim|xss_clean|numeric');
                $this->form_validation->set_rules('fm_descripation','Description','trim|xss_clean');

        /* Re-populating form */
        if ($_POST)
        {
            //$data['fm_programid']['value'] = $this->input->post('programid', TRUE);
            $data['fm_acadyear']['value'] = $this->input->post('fm_acadyear', TRUE);
	    //$data['fm_semester']['value'] = $this->input->post('fm_semester', TRUE);
            //$data['fm_category']['value'] = $this->input->post('category', TRUE);
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
           // $programname1=$this->input->post('fm_programid', TRUE);
	    //$programname=$this->common_model->get_listspfic1('program','prg_id','prg_name',$programname1)->prg_id;
            $acadyear = $this->input->post('fm_acadyear', TRUE);
            //$semester = strtoupper($this->input->post('fm_semester', TRUE));
            //$category1 = $this->input->post('fm_category', TRUE);
	    //$category = $this->common_model->get_listspfic1('category','cat_id','cat_name',$category1)->cat_id;
            $gender = ucwords(strtolower($this->input->post('fm_gender', TRUE)));
            $head= ucwords(strtolower($this->input->post('fm_head', TRUE)));
            $amount = $this->input->post('fm_amount',TRUE);
           // $installment = $this->input->post('fm_installment', TRUE);
	    $description = $this->input->post('fm_desc', TRUE);

            $logmessage = "";
          //  if($fm_data->fm_programid != $programname)
            //    $logmessage = $logmessage ." update program name " .$fm_data->fm_programid. " changed by " .$programname;
            if($fm_data->fm_acadyear != $acadyear)
                $logmessage = $logmessage ." update academic year " .$fm_data->fm_acadyear. " changed by " .$acadyear;
            //if($fm_data->fm_semester != $semester)
              //  $logmessage = $logmessage ." update semester " .$fm_data->fm_semester. " changed by " .$semester;
            //if($fm_data->fm_category != $category)
              //  $logmessage = $logmessage ." update category " .$fm_data->fm_category. " changed by " .$category;
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
	// insert data into fee master archive table	
	$insertdata= array(
		 'fma_fmid'=>$fm_data->fm_id,
		 'fma_programid'=>$fm_data->fm_programid,
                 'fma_acadyear'=>$fm_data->fm_acadyear,
                 'fma_semester'=>$fm_data->fm_semester,
                 'fma_category'=>$fm_data->fm_category,
                 'fma_gender'=>$fm_data->fm_gender,
                 'fma_head'=>ucwords(strtolower($fm_data->fm_head)),
                 'fma_amount'=>$fm_data->fm_amount,
                 'fma_desc'=>$fm_data->fm_desc,
                 'fma_frmdate'=>$fm_data->fm_frmdate,
                 'fma_todate'=>$fm_data->fm_todate,
                 'fma_ext1'=>$fm_data->fm_ext1,
                 'fma_ext2'=>$fm_data->fm_ext2,
		 'creatorid'=>$this->session->userdata('username'),
		 'createdate'=>date('y-m-d'),
        );
	    $fmaflag=$this->common_model->insertrec('fees_master_archive', $insertdata);
	    if(!$fmflag)
            {
		      $this->logger->write_dblogmessage("error","Error in insert in Fees master archive ", "Error in Fees master archive record insert". $logmessage );
	    }else{
		     $this->logger->write_dblogmessage("insert","Insert Fees master archive", "Fees headwise record inserted in fees master archive successfully..". $logmessage );
	    }

	$update_data = array(
              // 'fm_programid' => $programname,
               'fm_acadyear' => $acadyear,
               //'fm_semester' => $semester,
               //'fm_category'  => $category,
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

                           /*******************programcategory***********************/
 public function programcat(){

        if(isset($_POST['programcat'])) {
            $this->form_validation->set_rules('procatname','Program Category Name','trim|xss_clean|required|alpha_numeric_spaces|callback_value_exists');
            $this->form_validation->set_rules('procatcode','Program Category Code','trim|xss_clean|required|alpha_dash');
            $this->form_validation->set_rules('proshrtname','Program Category Short Name','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('prodesc','Program Category Description','trim|xss_clean|alpha_numeric_spaces');

            if($this->form_validation->run()==TRUE){

            $data = array(
                'prgcat_name'=>ucwords(strtolower($_POST['procatname'])),
                'prgcat_code'=>strtoupper($_POST['procatcode']),
                'prgcat_short'=>strtoupper($_POST['proshrtname']),
                'prgcat_desc'=>$_POST['prodesc']

            );
	   $prgcatflag=$this->common_model->insertrec('programcategory', $data) ;
	   if(!$prgcatflag)
	   {
                $this->logger->write_logmessage("insert"," Error in adding program category ", " program Category data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding program category ", "  program Category data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding program Category - ' . $_POST['procatname'] , 'error');
                $this->load->view('setup/programcat');
	   }	
	  else{		
		$this->logger->write_logmessage("insert"," add Program category ", "Program Category record added successfully..."  );
		$this->logger->write_dblogmessage("insert"," add Program category ", "Program Category record added successfully..." );
            	$this->session->set_flashdata("success", "Program Category added successfully...");
            	redirect("setup/viewprogramcat", "refresh");
	      }
           }

        }
      $this->load->view('setup/programcat');
   }


  /* Display Category record */

 public function viewprogramcat(){

	$this->result = $this->common_model->get_list('programcategory');
        $this->logger->write_logmessage("view"," View Category", "Category record display successfully..." );
        $this->logger->write_dblogmessage("view"," View Category", "Category record display successfully..." );
        $this->load->view('setup/viewprogramcat',$this->result);
    }

  /* this function is used for delete category record */
  public function deletePrgcat($prgcat_id) {
        $prgcat_data_q=$this->common_model->get_listrow('programcategory','prgcat_id', $prgcat_id);
        if ($prgcat_data_q->num_rows() < 1)
        {
	    $this->session->set_flashdata('Invalid program category records.', 'error');
            redirect('setup/viewprogramcat');
        }
	$prgcatflag=$this->common_model->deleterow('programcategory', 'prgcat_id', $prgcat_id) ;
        if(!$prgcatflag)
        {
            $this->logger->write_logmessage("delete", "Deleted Program Category ", "Error in  Program Category  [prgcat_id:" . $prgcat_id . "] delete.. " );
            $this->logger->write_dblogmessage("delete", "Deleted Progrm Category ","Error in Program Category  [prgcat_id:" . $prgcat_id . "] delete.. " );
            $this->session->set_flashdata('err_message','Error in deleting program Category - ' , 'Error');
            redirect('setup/viewprogramcat');
        }
        else {

            $this->logger->write_logmessage("delete", "Deleted Program Category ", "Program Category [prgcat_id:" . $prgcat_id . "] deleted successfully. " );
            $this->logger->write_dblogmessage("delete", "Deleted  Program Category ","Program Category [prgcat_id:" . $prgcat_id . "] deleted successfully. " );
 	    $this->session->set_flashdata("success", 'Program Category Record Deleted successfully.' );
            redirect('setup/viewprogramcat');
        }
    }

 /**This function is used for update category details
     * @param type $cat_id
     * @return type
     */
    public function editprogramcat($prgcat_id) {
	$prgcat_data_q=$this->common_model->get_listrow('programcategory','prgcat_id', $prgcat_id);
        if ($prgcat_data_q->num_rows() < 1)
        {
           redirect('setup/editprogramcat');
        }
        $programcategory_data = $prgcat_data_q->row();

        /* Form fields */

        $data['procatname'] = array(
            'name' => 'procatname',
            'id' => 'procatname',
            'maxlength' => '50',
            'size' => '40',
            'value' => $programcategory_data->prgcat_name,
	    //'readonly' => 'readonly'	
        );
        $data['procatcode'] = array(
           'name' => 'procatcode',
           'id' => 'procatcode',
           'maxlength' => '50',
           'size' => '40',
           'value' => $programcategory_data->prgcat_code,

        );

        $data['proshrtname'] = array(
           'name' => 'proshrtname',
           'id' => 'proshrtname',
           'maxlength' => '6',
           'size' => '40',
           'value' => $programcategory_data->prgcat_short,

        );

        $data['prodesc'] = array(
           'name' => 'prodesc',
           'id' => 'prodesc',
           'maxlength' => '255',
           'size' => '40',
           'value' => $programcategory_data->prgcat_desc,

        );

        $data['prgcat_id'] = $prgcat_id;

        $this->form_validation->set_rules('procatname','Program Category Name ','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('procatcode','Program Category Code ','trim|xss_clean|required|alpha_dash');
        $this->form_validation->set_rules('proshrtname','Program Category Short Name ','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('prodesc','Program Category Description ','trim|xss_clean|alpha_numeric_spaces');

        if ($_POST)
        {
            $data['procatname']['value'] = $this->input->post('procatname', TRUE);
            $data['procatcode']['value'] = $this->input->post('procatcode', TRUE);
            $data['proshrtname']['value'] = $this->input->post('proshrtname', TRUE);
            $data['prodesc']['value'] = $this->input->post('prodesc', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editprogramcat', $data);
            return;
        }
	else
        {

            $data_procatname = ucwords(strtolower($this->input->post('procatname', TRUE)));
            $data_procatcode = strtoupper($this->input->post('procatcode', TRUE));
            $data_proshrtname = strtoupper($this->input->post('proshrtname', TRUE));
            $data_prodesc = $this->input->post('prodesc', TRUE);
            $data_pcid = $prgcat_id;
	    $logmessage = "";
            if($programcategory_data->cat_name != $data_procatname)
                $logmessage = "Add Category " .$programcategory_data->prgcat_name. " changed by " .$data_procatname;
            if($programcategory_data->cat_code != $data_procatcode)
                $logmessage = "Add Category " .$programcategory_data->prgcat_code. " changed by " .$data_procatcode;
            if($programcategory_data->prgcat_short != $data_proshrtname)
                $logmessage = "Add Category " .$programcategory_data->prgcat_short. " changed by " .$data_proshrtname;
            if($programcategory_data->prgcat_desc != $data_prodesc)
                $logmessage = "Add Category " .$programcategory_data->prgcat_desc. " changed by " .$data_prodesc;

            $update_data = array(
               'prgcat_name' => $data_procatname,
               'prgcat_code' => $data_procatcode,
               'prgcat_short' => $data_proshrtname,
               'prgcat_desc'  => $data_prodesc
            );

	   $prgcatflag=$this->common_model->updaterec('programcategory', $update_data, 'prgcat_id', $data_pcid);
	   if(!$prgcatflag)	
            {
                $this->logger->write_logmessage("error","Error in update Program Category ", "Error in  Program Category record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Program Category ", "Error in  Program Category record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating Program category - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editprogramcat', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Program Category", "Program Category record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Program Category", "Program Category record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Program Category record updated successfully...');
                redirect('setup/viewprogramcat/');
                }
        }//else
        redirect('setup/editprogramcat/');
    }


/****************************************** Add Study Center Module ********************************************/

    	public function sc(){
            	//$this->uresult = $this->common_model->get_listspfic('org_profile','org_code','org_name');
                //$this->cresult = $this->common_model->get_listspfic('countries','id','name');
            	$this->uresult = $this->common_model->get_listmore('org_profile','org_code,org_name');
                $this->cresult = $this->common_model->get_listmore('countries','id,name');

                $prefs =array(
                       'start_date' => 'monday',
                        'show_next_prev' => true,
                        'next_prev_url' => base_url()
                        );
                $this->load->library('calendar',$prefs  );  

           if(isset($_POST['sc']))
                {
                $this->form_validation->set_rules('orgprofile','University','trim|xss_clean|required');
                $this->form_validation->set_rules('institutecode','Campus code','trim|xss_clean|alpha_numeric|required|callback_isStudyCenterExist');
                $this->form_validation->set_rules('name','Campus Name','ucwords|trim|xss_clean|required|alpha_numeric_spaces');
                $this->form_validation->set_rules('nickname','Campus Nickname','ucwords|trim|xss_clean|alpha_numeric_spaces|required');
                $this->form_validation->set_rules('address','Address','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('country','Country','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('state','State','ucwords|trim|xss_clean');
                $this->form_validation->set_rules('city','City','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('district','District','ucwords|trim|xss_clean');
                $this->form_validation->set_rules('pincode','Pincode','trim|xss_clean|numeric|max_length[6]');
                $this->form_validation->set_rules('phone','Phone','trim|xss_clean|numeric|max_length[12]');
                $this->form_validation->set_rules('fax','Fax','trim|xss_clean|numeric|max_length[12]');
                $this->form_validation->set_rules('status','Status','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('startdate','Startdate','trim|xss_clean');
                $this->form_validation->set_rules('closedate','Closedate','trim|xss_clean');
                $this->form_validation->set_rules('website','Website','trim|xss_clean|valid_url');
                $this->form_validation->set_rules('incharge','Incharge','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('mobile','Mobile','trim|numeric|max_length[12]');

             }
                   


            //if form validation true
                if($this->form_validation->run()==TRUE){
                       // if (($_POST['orgprofile'] != ''))
	                $data = array(
        		           'org_code'=>$_POST['orgprofile'],
                         	   'sc_code'=>$_POST['institutecode'],
                   		   'sc_name'=>$_POST['name'],
		                   'sc_nickname'=>$_POST['nickname'],
		                   'sc_address'=>$_POST['address'],
		                   'sc_country'=>$_POST['country'],
		                   'sc_state'=>$_POST['state'],
		                   'sc_city'=>$_POST['city'],
		                   'sc_district'=>$_POST['district'],
		                   'sc_pincode'=>$_POST['pincode'],
		                   'sc_phone'=>$_POST['phone'],
		                   'sc_fax'=>$_POST['fax'],
		                   'sc_status'=>$_POST['status'],
		                   'sc_startdate'=>$_POST['startdate'],
		                   'sc_closedate '=>$_POST['closedate'],
		                   'sc_website'=>$_POST['website'],
		                   'sc_incharge'=>$_POST['incharge'],
		                   'sc_mobile'=>$_POST['mobile'],
                    );
                     	$scflag=$this->common_model->insertrec('study_center', $data) ;
                        if(!$scflag)
                        {
                                $this->logger->write_logmessage("insert"," Error in adding Study center ", " Study center data insert error . ".$data['sc_name']  );
                                $this->logger->write_dblogmessage("insert"," Error in adding Study center ", " Study center data insert error . ".$data['sc_name'] );
                                $this->session->set_flashdata('err_message','Error in adding Study center - ' . $data['sc_name'] . '.', 'error');
				 $this->load->view('setup/sc');
                        }
                        else{
                                $this->logger->write_logmessage("insert"," add Study center ", "Study center record added successfully.".$data['sc_name']  );
                                $this->logger->write_dblogmessage("insert"," add Study center ", "Study center record added successfully.".$data['sc_name'] );
                                $this->session->set_flashdata("success", "Study center added successfully...");
                                redirect('setup/viewsc');
			//	 $this->load->view('setup/sc');
    
				}
                        }
        $this->load->view('setup/sc');
    }

       
 /** This function check for duplicate entry of study center code
     * @return type
    */

    public function isStudyCenterExist($sc_code) {

        $is_exist = $this->common_model->isduplicate('study_center','sc_code',$sc_code);
        if ($is_exist)
        {
            $this->form_validation->set_message('isStudyCenterExist', 'Study Center ' . $sc_code .' already exist.');
            return false;
        }
        else {
            return true;
        }
    }

 

/** This function Display the study center list records
     * @return type
     */
    public function viewsc() {
        $this->result = $this->common_model->get_list('study_center');
        $this->logger->write_logmessage("view"," View Study Center", "Study Center list display");
        $this->logger->write_dblogmessage("view"," View Study Center", "study Center display");
	$this->load->view('setup/viewsc',$this->result);
       }
 /* this function is used for delete study Center record */

    public function delete ($sc_id) {

        $scflag=$this->common_model->deleterow('study_center', 'sc_id',  $sc_id) ;        
        if(!$scflag)
        {
            $this->logger->write_logmessage("delete", "Deleted Study center ", "Error in  Study center ". $sc_data->sc_name . " [sc_id:" . $scid . "] delete. " );
            $this->logger->write_dblogmessage("delete", "Deleted Study center ","Error in Study center ".  $sc_data->sc_name . "  [sc_id:" . $scid . "] delete. " );
            $this->session->set_flashdata('err_message','Error in deleting Study center - ' . $sc_data->sc_name . '.', 'error');
            redirect('setup/viewsc');
        }
        else {

            $this->logger->write_logmessage("delete", "Deleted Study center ", "Study center  ". $sc_data->sc_name . " [sc_id:" . $scid . "] deleted successfully. " );
            $this->logger->write_dblogmessage("delete", "Deleted Study center ","Study center ". $sc_data->sc_name . " [sc_id:" . $scid . "] deleted successfully. " );
            $this->session->set_flashdata("success", 'Study center Record Deleted successfully.' );
            redirect('setup/viewsc');
        }
        $this->load->view('setup/viewsc',$data);

    }

/* this function is used for update study center record */

    public function editsc($id) {
	//$this->cresult = $this->common_model->get_listspfic('countries','id','name');
        $this->uresult = $this->common_model->get_listmore('org_profile','org_code,org_name');
	$this->cresult = $this->common_model->get_listmore('countries','id,name');
	$sc_data_q=$this->common_model->get_listrow('study_center','sc_id', $id);

        if ($sc_data_q->num_rows() < 1)
        {
            redirect('setup/viewsc');
        }
        $sc_data = $sc_data_q->row();
           /* Form fields */
          
                $data['orgprofile'] = array(
             	'name' => 'orgprofile',
             	'id' => 'orgprofile',
             //	'maxlength' => '',
             	'size' => '40',
             	'value' => $this->common_model->get_listspfic1('org_profile','org_name','org_code',$sc_data->org_code)->org_name,
             	'readonly' => 'readonly'
             	);


                $data['institutecode'] = array(
                'name' => 'institutecode',
                'id' => 'institutecode',
                'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_code,
               // 'readonly' => 'readonly'
                );

               $data['name'] = array(
                'name' => 'name',
                'id' => 'name',
               // 'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_name,
                );


                $data['nickname'] = array(
                'name' => 'nickname',
                'id' => 'nickname',
                'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_nickname,
                );

               $data['address'] = array(
                'name' => 'address',
                'id' => 'address',
               // 'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_address,
                );
                
                $data['country'] = array(
                'name' => 'country',
                'id' => 'country',
                'maxlength' => '50',
                'size' => '40',
              	'value' => $sc_data->sc_country, 
                );
		//echo "this is seema".$data['country']['value'];
               $data['state'] = array(
                'name' => 'state',
                'id' => 'state',
                'maxlength' => '50',
                'size' => '40',
               'value' => $sc_data->sc_state,
                );


               $data['city'] = array(
                'name' => 'city',
                'id' => 'city',
                'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_city,
                );

               $data['district'] = array(
                'name' => 'district',
                'id' => 'district',
               // 'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_district,
                );

               $data['pincode'] = array(
                'name' => 'pincode',
                'id' => 'pincode',
                'maxlength' => '6',
                'size' => '40',
                'value' => $sc_data->sc_pincode,
                );

               $data['phone'] = array(
                'name' => 'phone',
                'id' => 'phone',
                'maxlength' => '13',
                'size' => '40',
                'value' => $sc_data->sc_phone,
                );

               $data['fax'] = array(
                'name' => 'fax',
                'id' => 'fax',
                'maxlength' => '15',
                'size' => '40',
                'value' => $sc_data->sc_fax,
                );

               $data['status'] = array(
                'name' => 'status',
                'id' => 'status',
              //  'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_status,
                );

                $data['startdate'] = array(
                'name' => 'startdate',
                'id' => 'startdate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_startdate,
                );

               $data['closedate'] = array(
                'name' => 'closedate',
                'id' => 'closedate',
                'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_closedate,
                );

               $data['website'] = array(
                'name' => 'website',
                'id' => 'website',
                //'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_website,
                );

              $data['incharge'] = array(
                'name' => 'incharge',
                'id' => 'incharge',
               // 'maxlength' => '50',
                'size' => '40',
                'value' => $sc_data->sc_incharge,
                );

              $data['mobile'] = array(
                'name' => 'mobile',
                'id' => 'mobile',
                'maxlength' => '13',
                'size' => '40',
                'value' => $sc_data->sc_mobile,
                );
          $data['id'] = $id;
         /*Form Validation*/

             	$this->form_validation->set_rules('orgprofile','University','trim|xss_clean|required');
                $this->form_validation->set_rules('institutecode','Campus code','trim|xss_clean|alpha_numeric_spaces|required');
                $this->form_validation->set_rules('name','Campus Name','ucwords|trim|xss_clean|required|alpha_numeric_spaces');
                $this->form_validation->set_rules('nickname','Campus Nickname','ucwords|trim|xss_clean|required|alpha_numeric_spaces');
                $this->form_validation->set_rules('address','Address','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('country','Country','ucwords|trim|xss_clean');
                $this->form_validation->set_rules('state','State','ucwords|trim|xss_clean');
                $this->form_validation->set_rules('city','City','ucwords|trim|xss_clean');
                $this->form_validation->set_rules('district','District','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('pincode','Pincode','trim|xss_clean|numeric|max_length[6]');
                $this->form_validation->set_rules('phone','Phone','trim|xss_clean|numeric|max_length[12]');
                $this->form_validation->set_rules('fax','Fax','trim|xss_clean|numeric|max_length[12]');
                $this->form_validation->set_rules('status','Status','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('startdate','Startdate','trim|xss_clean');
                $this->form_validation->set_rules('closedate','Closedate','trim|xss_clean');
                $this->form_validation->set_rules('website','Website','trim|xss_clean|valid_url');
                $this->form_validation->set_rules('incharge','Incharge','ucwords|trim|xss_clean|alpha_numeric_spaces');
                $this->form_validation->set_rules('mobile','Mobile','trim|numeric|max_length[12]');
               
             /* Re-populating form */

           if ($_POST)
                   {
                        $data['orgprofile']['value'] = $this->input->post('orgprofile', TRUE);
                        $data['institutecode']['value'] = $this->input->post('institutecode', TRUE);
			$data['name']['value'] = $this->input->post('name', TRUE);
			$data['nickname']['value'] = $this->input->post('nickname', TRUE);
			$data['address']['value'] = $this->input->post('address', TRUE);
			$data['country']['value'] = $this->input->post('country', TRUE);
			$data['state']['value'] = $this->input->post('state', TRUE);
			$data['city']['value'] = $this->input->post('city', TRUE);
			$data['district']['value'] = $this->input->post('district', TRUE);
			$data['pincode']['value'] = $this->input->post('pincode', TRUE);
			$data['phone']['value'] = $this->input->post('phone', TRUE);
			$data['fax']['value'] = $this->input->post('fax', TRUE);
			$data['status']['value'] = $this->input->post('status', TRUE);
			$data['startdate']['value'] = $this->input->post('startdate', TRUE);
			$data['closedate']['value'] = $this->input->post('closedate', TRUE);
			$data['website']['value'] = $this->input->post('website', TRUE);
			$data['incharge']['value'] = $this->input->post('incharge', TRUE);
			$data['mobile']['value'] = $this->input->post('mobile', TRUE);
 		   }
                  if ($this->form_validation->run() == FALSE)
                   {
                      $this->load->view('setup/editsc', $data);
                   }
                  else{
                       $data_orgprofile = $this->input->post('orgprofile', TRUE);
		       $data_institutecode = $this->input->post('institutecode', TRUE);
		       $data_name = $this->input->post('name', TRUE);
		       $data_nickname = $this->input->post('nickname', TRUE);
		       $data_address = $this->input->post('address', TRUE);
		       $data_country = $this->input->post('country', TRUE);
		       $data_state = $this->input->post('state', TRUE);
		       $data_city = $this->input->post('city', TRUE);
		       $data_district = $this->input->post('district', TRUE);
		       $data_pincode = $this->input->post('pincode', TRUE);
		       $data_phone = $this->input->post('phone', TRUE);
		       $data_fax = $this->input->post('fax', TRUE);
		       $data_status = $this->input->post('status', TRUE);
		       $data_startdate = $this->input->post('startdate', TRUE);
		       $data_closedate = $this->input->post('closedate', TRUE);
		       $data_website = $this->input->post('website', TRUE);
		       $data_incharge = $this->input->post('incharge', TRUE);
		       $data_mobile = $this->input->post('mobile', TRUE);
                       $data_scid = $id;  
                       $logmessage = "";
				if($sc_data->org_code != $data_orgprofile)
				$logmessage = $logmessage = "University Name " .$sc_data->orgprofile. " changed by " .$data_orgprofile;
 				if($sc_data->sc_code != $data_institutecode)
				$logmessage = $logmessage ." Campus Code " .$sc_data->institutecode. " changed by " .$data_institutecode;
        	               // if($sc_data->sc_name != $data_name)
		
                       $update_data = array(
				   'org_code'=>$this->common_model->get_listspfic1('org_profile','org_code','org_name',$data_orgprofile)->org_code, 
                                   'sc_code'=>$data_institutecode,
                                   'sc_name'=>$data_name,
                                   'sc_nickname'=>$data_nickname,
                                   'sc_address'=>$data_address,
                                   'sc_country'=>$data_country,
                                   'sc_state'=>$data_state,
                                   'sc_city'=>$data_city,
                                   'sc_district'=>$data_district,
                                   'sc_pincode'=>$data_pincode,
                                   'sc_phone'=>$data_phone,
                                   'sc_fax'=>$data_fax,
                                   'sc_status'=>$data_status,
                                   'sc_startdate'=>$data_startdate,
                                   'sc_closedate'=>$data_closedate,
                                   'sc_website'=>$data_website,
                                   'sc_incharge'=>$data_incharge,
                                   'sc_mobile'=>$data_mobile
                          );
			if($sc_data->sc_code != $data_institutecode){
			$sccodeflag = $this->common_model->isduplicate('study_center','sc_code', $data_institutecode);
			if($sccodeflag == 1)
			{
                                  $this->session->set_flashdata("err_message", "Record is already exist. 'SC Code' = $data_institutecode  .");
                                  redirect('setup/viewsc');
                                  return;
		
			}
			else{
			$scflag=$this->common_model->updaterec('study_center', $update_data, 'sc_id', $id);
                        if(!$scflag)
                                {
                                $this->logger->write_logmessage("error","Error in update Study center ", "Error in Study center record update". $logmessage );
                                $this->logger->write_dblogmessage("error","Error in update Study center ", "Error in Study center record update". $logmessage );
                                $this->session->set_flashdata('err_message','Error updating Study center - ' . $logmessage . '.', 'error');
                                $this->load->view('setup/editsc', $data);
                             }
                        else{
                                $this->logger->write_logmessage("update","Edit Study center", "Study center record updated successfully..". $logmessage );
                                $this->logger->write_dblogmessage("update","Edit Study center", "Study center record updated successfully..". $logmessage );
                                $this->session->set_flashdata('success','Study center record updated successfully...');
                                redirect('setup/viewsc');
                           }
			  }
			}
			else{
			$scflag=$this->common_model->updaterec('study_center', $update_data, 'sc_id', $id);
                        if(!$scflag)
		                {
                		$this->logger->write_logmessage("error","Error in update Study center ", "Error in Study center record update". $logmessage );
		        	$this->logger->write_dblogmessage("error","Error in update Study center ", "Error in Study center record update". $logmessage );
		                $this->session->set_flashdata('err_message','Error updating Study center - ' . $logmessage . '.', 'error');
		                $this->load->view('setup/editsc', $data);
           			}
	            	else{
        		        $this->logger->write_logmessage("update","Edit Study center", "Study center record updated successfully..". $logmessage );
            			$this->logger->write_dblogmessage("update","Edit Study center", "Study center record updated successfully..". $logmessage );
		                $this->session->set_flashdata('success','Study center record updated successfully...');
		                redirect('setup/viewsc');
                	}
    		 }
	    }	
    	}

	public function get_state(){
               $contid = $this->input->post('cid');   
               $this->depmodel->get_statelist($contid);
        } 


	public function get_city(){
               $statid = $this->input->post('sid');       
               $this->depmodel->get_citylist($statid);
        }

	/** This function Display the seat setting records
     	* @return type
     	*/
    	public function dispseatsetting() {
		$this->srresult = $this->common_model->get_list('seat_reservation');
        	$this->logger->write_logmessage("view"," View  Seat Setting list", " Seat reservation list display");
        	$this->logger->write_dblogmessage("view"," View Seat reservation list", "Seat reservation list display");
		$this->load->view('setup/dispseatsetting');
	}

	public function isCategoryExist($key)
 	{
     		$is_exist = $this->common_model->isduplicate('seat_reservation','category_id',$key);
     		if($is_exist) {
        		$this->form_validation->set_message('value_exists', 'Category  '. $key. '  is already exist.');
		}
	}

	public function seatsetting() {
             	$this->uresult=$this->common_model->get_listspfic2('org_profile','org_code','org_name');
    	     	$this->catresult = $this->common_model->get_listspfic2('category','cat_name','cat_id');
	     	$this->totalseat=$this->unimodel->totalnoofseat();
	     	if(isset($_POST['seatsetting'])) {
			$this->form_validation->set_rules('org_profile','University','trim|xss_clean|required');
			 $this->form_validation->set_rules('category','category','trim|xss_clean|required');
			 $this->form_validation->set_rules('percentage','percentage','trim|xss_clean|required|numeric');
			 if($this->form_validation->run()==TRUE){
				 $catname=$this->common_model->get_listspfic1('category','cat_name','cat_id',$_POST['category'])->cat_name;
				 $datawh = array('category_id' => $_POST['category']);
				 $is_exist = $this->common_model->isduplicatemore('seat_reservation',$datawh);
				 if($is_exist){
					  $this->session->set_flashdata('err_message','duplicate record in adding Seat reservation - ' . $catname . '.', 'error');
					  redirect('setup/dispseatsetting');
					  return;
				 }
				 $pert = $_POST['percentage'];
				 $srresult = $this->common_model->get_listmore('seat_reservation','percentage');
				 $ttpert=0;
				 foreach($srresult as $rw){
					 $ttpert =$ttpert + $rw->percentage;
				 }
				$rp = 100 - $ttpert;
				if($pert <= $rp){
	            			$data = array(
        	    				'org_code'=>$_POST['org_profile'],
            					'category_id'=>$_POST['category'],
						'percentage'=>$_POST['percentage'],
						'noofseat' =>$_POST['numberofseat'],
						'creatorid'=>$this->session->userdata('username'),
			                	'createdate'=>date('y-m-d'),
             				);
					$srflag=$this->common_model->insertrec('seat_reservation', $data) ;
                        		if(!$srflag){
						$this->logger->write_logmessage("insert"," Error in adding Seat reseravation ", " Seat reservation data insert error . ".$_POST['category']  );
	             		                $this->logger->write_dblogmessage("insert"," Error in adding Seat reservation ", " Seat reservation data insert error . ".$_POST['category'] );
        	                                $this->session->set_flashdata('err_message','Error in adding Seat reservation - ' . $catname . '.', 'error');	
						redirect('setup/seatsetting');
					//	return;
					}
                        		else{
                                		$this->logger->write_logmessage("insert","Add Seat reservation ", "Seat".$_POST['category']. $catname." added  successfully...");
	                                	$this->logger->write_dblogmessage("insert","Add Seat reservation ", "Seat ".$_POST['category'] .$catname."added  successfully...");
		                               	$this->session->set_flashdata("success", " Seat reservation add successfully... Category is ".$catname);
						redirect('setup/dispseatsetting');
					//	return;
					}
				}
				else{
					$this->session->set_flashdata('err_message','The availabel percentage is '.$rp . 'Kindly use this or less .', 'error');
					redirect('setup/dispseatsetting');
					//return;
				}
                	}//validation
            	}//end post
            	$this->load->view('setup/seatsetting');
    	}
					
	public function delete_eseat($id) {
		$eset_q=$this->common_model->get_listrow('seat_reservation','id', $id);
                if ($eset_q->num_rows() < 1)
        	{
                	$this->session->set_flashdata('Invalid Seat setting.', 'error');
                	redirect('setup/dispseatsetting');
        	}
            	else {
	                $eset_data = $eset_q->row();
		}
		$seatrflag=$this->common_model->deleterow('seat_reservation', 'id', $id) ;
        	if(!$seatrflag)
        	{
            		$this->logger->write_logmessage("delete", "Error in deleting seat reservation ", "Error in deleting seat reservation  [catid:" . $eset_data->category_id . "] delete.. " );
            		$this->logger->write_dblogmessage("delete", "Error in deleting seat reservation ","Error in deleting seat reservation  [catid:" . $eset_data->category_id . "] delete.. " );
            		$this->session->set_flashdata('err_message','Error in deleting seat reservation - cat_id:' . $eset_data->category_id , 'Error');
            		redirect('setup/dispseatsetting');
        	}
        	else {
            		$this->logger->write_logmessage("delete", "Deleted seat reservation ", "seat reservation [cat_id:" . $eset_data->category_id . "] deleted successfully. " );
            		$this->logger->write_dblogmessage("delete", "Deleted seat reservation ","seat reservation [cat_id:" . $eset_data->category_id . "] deleted successfully. " );
            		$this->session->set_flashdata("success", 'Seat reservation Record Deleted successfully. cat_id:' . $eset_data->category_id );
            		redirect('setup/dispseatsetting');
        	}
                $this->load->view('setup/dispseatsetting');
	}

	/**This function is used for update seat setting details
     	* @param type $id
     	* @return type
     	*/
	public function editseatsetting($id) {
//		$this->catresult = $this->common_model->get_listspfic2('category','cat_name','cat_id');
		$eset_data_q=$this->common_model->get_listrow('seat_reservation','id', $id);
                if ($eset_data_q->num_rows() < 1)
        	{
                	redirect('setup/editseatsetting');
        	}
		$editeset_data = $eset_data_q->row();
                $this->totalseat=$this->unimodel->totalnoofseat();

        /* Form fields */

                $data['org_code'] = array(
                'name' => 'university',
                'id' => 'university',
                'maxlength' => '50',
                'value' => $this->common_model->get_listspfic1('org_profile','org_name','org_code',$editeset_data->org_code)->org_name,
                'size' => '40',
                'readonly' => 'readonly'
        	);
                $data['category'] = array(
                'name' => 'category',
                'id' => 'category',
                'maxlength' => '50',
                'size' => '40',
		'value' => $this->common_model->get_listspfic1('category','cat_name','cat_id',$editeset_data->category_id)->cat_name,
		'readonly' => 'readonly'
        	);

                $data['percentage'] = array(
                'name' => 'percentage',
                'id' => 'percentage',
                'maxlength' => '6',
                'size' => '40',
                'value' => $editeset_data->percentage,
        	);

                $data['numberofseat'] = array(
                'name' => 'numberofseat',
                'id' => 'numberofseat',
                'maxlength' => '5',
                'size' => '40',
                'value' => $editeset_data->noofseat,
        	);

                $data['id'] = $id;

        /*Form Validation*/
               // $this->form_validation->set_rules('category','Category name','trim|xss_clean|required|alpha_numeric_spaces');
                $this->form_validation->set_rules('percentage','percentage','required|trim|xss_clean');
                $this->form_validation->set_rules('numberofseat','numberofseat','required|trim|xss_clean');

        /* Re-populating form */
                if ($_POST)
        	{
                //	$data['university']['value'] = $this->input->post('university', TRUE);
                //	$data['category']['value'] = $this->input->post('category', TRUE);
                	$data['percentage']['value'] = $this->input->post('percentage', TRUE);
                	$data['noofseat']['value'] =$this->input->post('noofseat',TRUE);
        	}

                if ($this->form_validation->run() == FALSE)
        	{
                	$this->load->view('setup/editseatsetting', $data);
                	return;
        	}
        	else{

              //  $data_university = $this->input->post('university', TRUE);
              //  $data_category = $this->input->post('category', TRUE);
                $data_percentage = $this->input->post('percentage', TRUE);
                $data_noofseat = $this->input->post('numberofseat', TRUE);
                $data_eid = $id;

		$srresult = $this->common_model->get_listmore('seat_reservation','id,percentage');
                $ttpert=0;
		foreach($srresult as $rw){
			if ($rw->id != $id){
				$ttpert =$ttpert + $rw->percentage;
			}
                }
                $rp = 100 - $ttpert;
                if($data_percentage <= $rp){
		
			$logmessage = "";
	          //      if($editeset_data->category_id != $data_category)
        	    //            $logmessage = "Edit Seat Setting " .$editeset_data->category_id. " changed by " .$data_category;
                	if($editeset_data->percentage != $data_percentage)
                        	$logmessage = "Edit Seat Setting " .$editeset_data->percentage. " changed by " .$data_percentage;
	                if($editeset_data->noofseat!= $data_noofseat)
        	                $logmessage = "Edit Seat Setting " .$editeset_data->noofseat. " changed by " .$data_noofseat;
                	$update_data = array(
                //        	'category_id' => $data_category,
	                        'percentage' => $data_percentage,
        	                'noofseat' =>$data_noofseat,
                	);
	                $prgcatflag=$this->common_model->updaterec('seat_reservation', $update_data, 'id', $data_eid);
        	        if(!$prgcatflag)
                	{
		                $this->logger->write_logmessage("error","Error in update Seat Reservation ", "Error in  Seat Reservation record update. $logmessage . " );
                		$this->logger->write_dblogmessage("error","Error in update Seat Reservation ", "Error in Seat Reservation record update. $logmessage ." );
		                $this->session->set_flashdata('err_message','Error seat reservation - ' . $logmessage . '.', 'error');
                		$this->load->view('setup/editseatsetting', $data);
                	}
                	else{
		                $this->logger->write_logmessage("update","Edit Seat Category", "Seat Setting record updated successfully... $logmessage . " );
                		$this->logger->write_dblogmessage("update","Edit Seat Category", "Seat Setting record updated successfully... $logmessage ." );
		                $this->session->set_flashdata('success','Updated seat setting record  details successfully...');
               		redirect('setup/dispseatsetting/');
	                }

		}//check for 100 percent*/
        }//else
    	redirect('setup/editseatsetting/');
    }//function end


/****************************************** Scheme Module ********************************************/

 public function scheme(){
        $this->deptresult = $this->common_model->get_list('Department');
        if(isset($_POST['scheme'])) {
            $this->form_validation->set_rules('dept_name','Department Name','trim|xss_clean|required');
            $this->form_validation->set_rules('sname','Scheme Name','trim|xss_clean');
            $this->form_validation->set_rules('scode','Scheme Code','trim|xss_clean|required|alpha_dash');
           // $this->form_validation->set_rules('scode','Scheme Code','trim|xss_clean|required|alpha_dash|callback_isSchemeExist');
            $this->form_validation->set_rules('ssname','Scheme Short Name','trim|xss_clean|required|alpha_numeric_spaces');
            $this->form_validation->set_rules('sdesc','Scheme Description','trim|xss_clean');

            if($this->form_validation->run()==TRUE){

	    $schd = $this->input->post("dept_name");
	    $schname= $this->input->post("sname");
            $schcode= $this->input->post("scode");
	    $schsn= $this->input->post("ssname");	
	    					
	   // $schdata = array('sd_deptid'=>ucwords(strtolower($_POST['dept_name'])), 'sd_name'=>ucwords(strtolower($_POST['sname'])), 'sd_code'=>strtoupper($_POST['scode']), 'sd_short'=>strtoupper($_POST['ssname']) );
	    $schdata = array('sd_deptid'=>ucwords(strtolower($_POST['dept_name'])), 'sd_code'=>strtoupper($_POST['scode']) );

            $data = array(
                'sd_deptid'=>ucwords(strtolower($_POST['dept_name'])),
                'sd_name'=>ucwords(strtolower($_POST['sname'])),
                'sd_code'=>strtoupper($_POST['scode']),
                'sd_short'=>strtoupper($_POST['ssname']),
                'sd_desc'=>$_POST['sdesc']

            );

	   $schdatadup = $this->SIS_model->isduplicatemore('scheme_department', $schdata);		
           if($schdatadup == 1 ){

                                  //$this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Department' = $schd  , 'Scheme Name' = $schname , 'Scheme Code' = $schcode, 'Scheme Short Name'=$schsn  .");
                                  $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Department' = $schd  , 'Scheme Code' = $schcode .");
                                  redirect('setup/scheme');
                                  return;
                  }
          else{

          $schflag=$this->SIS_model->insertrec('scheme_department', $data) ;
          if(!$schflag)
          {
                $this->logger->write_logmessage("insert"," Error in adding scheme ", " Scheme data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding scheme ", " Scheme data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding scheme - ' . $_POST['sname'] , 'error');
                $this->load->view('setup/scheme');
          }
          else{
                $this->logger->write_logmessage("insert"," add scheme ", "Scheme record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add scheme ", "Scheme record added successfully..." );
                $this->session->set_flashdata("success", "Scheme added successfully...");
                redirect("setup/displayscheme", "refresh");
          }
        }
      }
    }
    $this->load->view('setup/scheme');
 }


/** This function check for duplicate scheme
     * @return type
    */

    public function isSchemeExist($sd_code) {

        $is_exist = $this->SIS_model->isduplicate('scheme_department','sd_code',$sd_code);
        if ($is_exist)
        {
            $this->form_validation->set_message('isSchemeExist', 'Scheme code is already exist.');
            return false;
        }
        else {
            return true;
        }
    }



 /* Display Scheme record */

  public function displayscheme(){
        $this->result = $this->SIS_model->get_list('scheme_department');
        $this->logger->write_logmessage("view"," View ", "Scheme record display successfully..." );
        $this->logger->write_dblogmessage("view"," View Scheme", "Scheme record display successfully..." );
        $this->load->view('setup/displayscheme',$this->result);
    }

 /**This function is used for update scheme details
     * @param type $sd_id
     * @return type
     */
    public function editscheme($sd_id) {
        $this->deptresult = $this->common_model->get_list('Department');
	$sch_data_q=$this->SIS_model->get_listrow('scheme_department','sd_id', $sd_id);
        if ($sch_data_q->num_rows() < 1)
        {
           redirect('setup/editscheme');
        }
        $scheme_data = $sch_data_q->row();

        /* Form fields */

       $data['sd_deptid'] = array(
            'name' => 'sd_deptid',
            'id' => 'sd_deptid',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('Department','dept_name', 'dept_id',$scheme_data->sd_deptid)->dept_name,
            //'value' => $scheme_data->sd_name,
            
        );
        $data['sname'] = array(
            'name' => 'sname',
            'id' => 'sname',
            'maxlength' => '50',
            'size' => '40',
            'value' => $scheme_data->sd_name,
	    	
        );
        $data['scode'] = array(
           'name' => 'scode',
           'id' => 'scode',
           'maxlength' => '50',
           'size' => '40',
           'value' => $scheme_data->sd_code,
        );
        $data['ssname'] = array(
           'name' => 'ssname',
           'id' => 'ssname',
           'maxlength' => '50',
           'size' => '40',
           'value' => $scheme_data->sd_short,
        );
        $data['sdesc'] = array(
           'name' => 'sdesc',
           'id' => 'sdesc',
           'maxlength' => '255',
           'size' => '40',
           'value' => $scheme_data->sd_desc,
        );
        $data['sd_id'] = $sd_id;
          
        $this->form_validation->set_rules('sd_deptid','Department name','trim|xss_clean'); 
        $this->form_validation->set_rules('sname','Scheme Name ','trim|xss_clean|required');
        $this->form_validation->set_rules('scode','Scheme Code ','trim|xss_clean|required|alpha_dash');
        $this->form_validation->set_rules('ssname','Scheme Short Name ','trim|xss_clean|required');
        $this->form_validation->set_rules('sdesc','Scheme Description ','trim|xss_clean');

        if ($_POST)
        {
            $data['sd_deptid']['value'] = $this->input->post('sd_deptid', TRUE);
            $data['sname']['value'] = $this->input->post('sname', TRUE);
            $data['scode']['value'] = $this->input->post('scode', TRUE);
            $data['ssname']['value'] = $this->input->post('ssname', TRUE);
            $data['sdesc']['value'] = $this->input->post('sdesc', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editscheme', $data);
            return;
        }
	else
        {
            $sd_deptid = ucwords(strtolower($this->input->post('sd_deptid', TRUE)));
            $data_sname = ucwords(strtolower($this->input->post('sname', TRUE)));
            $data_scode = strtoupper($this->input->post('scode', TRUE));
            $data_ssname = strtoupper($this->input->post('ssname', TRUE));
            $data_sdesc = $this->input->post('sdesc', TRUE);
            $data_sid = $sd_id;
	    $logmessage = "";
            if($scheme_data->sd_deptid != $sd_deptid)
                $logmessage = "Add Scheme " .$scheme_data->sd_deptid. " changed by " .$sd_deptid;
            if($scheme_data->sd_name != $data_sname)
                $logmessage = "Add Scheme " .$scheme_data->sd_name. " changed by " .$data_sname;
            if($scheme_data->sd_code != $data_scode)
                $logmessage = "Add Scheme " .$scheme_data->sd_code. " changed by " .$data_scode;
            if($scheme_data->sd_short != $data_ssname)
                $logmessage = "Add Scheme " .$scheme_data->sd_short. " changed by " .$data_ssname;
            if($scheme_data->sd_desc != $data_sdesc)
                $logmessage = "Add Scheme " .$scheme_data->sd_desc. " changed by " .$data_sdesc;
	    
            $instdatasda = array(
               'sda_sdid' =>$data_sid,
	       'sda_deptid' =>$scheme_data->sd_deptid,
               'sda_name' => $scheme_data->sd_name,
               'sda_code' => $scheme_data->sd_code,
               'sda_short' => $scheme_data->sd_short,
               'sda_desc'  => $scheme_data->sd_desc,
	       'sda_archuserid'=>$this->session->userdata('id_user'),
               'sda_archdate'=>date('y-m-d')
            );

            $update_data = array(
               'sd_deptid' =>$this->common_model->get_listspfic1('Department','dept_id', 'dept_name', $sd_deptid)->dept_id,
               'sd_name' => $data_sname,
               'sd_code' => $data_scode,
               'sd_short' => $data_ssname,
               'sd_desc'  => $data_sdesc
            );

            $chdup_data = array(
               'sd_deptid' =>$this->common_model->get_listspfic1('Department','dept_id', 'dept_name', $sd_deptid)->dept_id,
               'sd_code' => $data_scode
            );

	   $schdatadupe = $this->SIS_model->isduplicatemore('scheme_department', $update_data);		
           if($schdatadupe == 1 ){

                   $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Department' = $sd_deptid, 'Scheme Name' = $data_sname , 'Scheme Code' = $data_scode, 'Scheme Short Name'=$data_ssname .");
                                  redirect('setup/displayscheme/');
                                  return;
                 }
            else{
	    $sdflag=$this->SIS_model->insertrec('scheme_department_archive', $instdatasda);
            if(!$sdflag)
            {
              $this->logger->write_dblogmessage("error","Error in insert scheme department archive", "Error in  scheme department archive record insert" .$data_sid );
            }else{
              $this->logger->write_dblogmessage("insert","Insert scheme department archive archive", "Record inserted in scheme department archive successfully.." .$data_sid );
          }
	 //if($scheme_data->sd_code != $data_scode)
	  if(($scheme_data->sd_code != $data_scode)&&($scheme_data->sd_deptid !=$sd_deptid))
          {
	      //$schemedupflag = $this->SIS_model->isduplicate('scheme_department','sd_code', $data_scode);
	      $schemedupflag = $this->SIS_model->isduplicatemore('scheme_department', $chdup_data);		
              if($schemedupflag ==1)
              {
                     // $this->session->set_flashdata("err_message", "Record is already exist. 'Scheme Code' = $data_scode  .");
                     $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Department' = $sd_deptid, 'Scheme Code' = $data_scode .");
		     $this->load->view('setup/editscheme', $data);
                     return;
              }
              else{
              $scflag=$this->SIS_model->updaterec('scheme_department', $update_data, 'sd_id', $data_sid);
              if(!scflag)
              {
                  $this->logger->write_logmessage("error","Error in update Scheme ", "Error in Scheme record update. $logmessage . " );
                  $this->logger->write_dblogmessage("error","Error in update Scheme ", "Error in Scheme record update. $logmessage ." );
                  $this->session->set_flashdata('err_message','Error updating scheme - ' . $logmessage . '.', 'error');
                  $this->load->view('setup/editscheme', $data);
              }
              else{
                   $this->logger->write_logmessage("update","Edit Scheme", "Scheme record updated successfully... $logmessage . " );
                   $this->logger->write_dblogmessage("update","Edit Scheme", "Scheme record updated successfully... $logmessage ." );
                   $this->session->set_flashdata('success','Scheme record updated successfully...');
                   redirect('setup/displayscheme/');
                 }
		}    	
	      }
          else{
	   $scflag=$this->SIS_model->updaterec('scheme_department', $update_data, 'sd_id', $data_sid);
	   if(!scflag)	
            {
                $this->logger->write_logmessage("error","Error in update Scheme ", "Error in Scheme record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Scheme ", "Error in Scheme record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating scheme - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editscheme', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Scheme", "Scheme record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Scheme", "Scheme record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Scheme record updated successfully...');
                redirect('setup/displayscheme/');
                }
	     }
	 }	
        }//else
        redirect('setup/editscheme/');
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
                'bank_name'	=>$_POST['bank_name'],
                'bank_address'	=>$_POST['bank_address'],
                'branch_name'	=>$_POST['branch_name'],
                'account_number'=>$_POST['account_number'],
                'account_name'	=>$_POST['account_name'],
                'account_type'	=>$_POST['account_type'],
                'ifsc_code'	=>strtoupper($_POST['ifsc_code']),
                'pan_number'	=>strtoupper($_POST['pan_number']),
                'tan_number'	=>strtoupper($_POST['tan_number']),
                'gst_number'	=>strtoupper($_POST['gst_number']),
                'aadhar_number'	=>$_POST['aadhar_number'],
                'org_id'	=>$orgid,
                'remark'	=>$_POST['remark'] 
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
			$uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $bankprofile_data->ucoid)->name; 
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

/******************************************Tax Slab Master ********************************************/

public function taxslab(){

        if(isset($_POST['taxslab'])) {
            $this->form_validation->set_rules('tsmfy','Financial Year','trim|xss_clean|required');
            $this->form_validation->set_rules('tsmname','Tax Slab Master Name','trim|xss_clean|required|alpha_numeric_spaces|callback_isTaxSlabMasterExist');
            $this->form_validation->set_rules('tsmstartvalue','Tax Slab Start Value','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('tsmendvalue','Tax Slab End Value','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('tsmtype','Tax Slab Type','trim|xss_clean|required');
            $this->form_validation->set_rules('tsmgender','Tax Slab Gender','trim|xss_clean|required');
            $this->form_validation->set_rules('tsmpercent','Tax Slab Percent','trim|xss_clean|numeric');

            if($this->form_validation->run()==TRUE){

            $data = array(
                'tsm_fy' =>$_POST['tsmfy'],
                'tsm_name'=>$_POST['tsmname'],
                'tsm_startvalue'=>$_POST['tsmstartvalue'],
                'tsm_endvalue'=>$_POST['tsmendvalue'],
                'tsm_type'=>$_POST['tsmtype'],
                'tsm_percent'=>$_POST['tsmpercent'],
                'tsm_gender'=>$_POST['tsmgender'],

            );
           $tsmflag=$this->SIS_model->insertrec('tax_slab_master', $data) ;
           if(!$tsmflag)
           {
                $this->logger->write_logmessage("insert"," Error in adding Tax slabmastername ", " Tax Slab Master Name data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding Tax slabmastername ", " Tax Slab Master Name data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding Tax slabmastername - ' . $_POST['tsmname'] , 'error');
                $this->load->view('setup/taxslab');
           }
          else{
                $this->logger->write_logmessage("insert"," add slabmastername ", "Tax Slab Master Name record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add slabmastername ", "Tax Slab Master Name record added successfully..." );
                $this->session->set_flashdata("success", "slabmastername added successfully...");
                redirect("setup/displaytaxslab", "refresh");
              }
           }

        }
      $this->load->view('setup/taxslab');
   }


/** This function check for duplicate Tax Slab Master
     * @return type
    */

    public function isTaxSlabMasterExist($tsm_name) {

        $is_exist = $this->SIS_model->isduplicate('tax_slab_master','tsm_name',$tsm_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isTax SlabMasterExist', 'Tax Slab Master is already exist.');
            return false;
        }
        else {
            return true;
        }
    }


/* Display Tax Slab Master record */

public function displaytaxslab(){

        $this->result = $this->SIS_model->get_list('tax_slab_master');
        $this->logger->write_logmessage("view"," View ", "Tax Slab Master display successfully..." );
        $this->logger->write_dblogmessage("view"," View Tax Slab Master", "Tax Slab Master successfully..." );
        $this->load->view('setup/displaytaxslab',$this->result);
    }

 /**This function is used for update Tax Slab Master details
     * @param type $tsm_id
     * @return type
     */
    public function edittaxslab($tsm_id) {
        $tsm_data_q=$this->SIS_model->get_listrow('tax_slab_master','tsm_id', $tsm_id);
        if ($tsm_data_q->num_rows() < 1)
        {
           redirect('setup/edittaxslab');
        }
        $TaxSlabMaster_data = $tsm_data_q->row();
        /* Form fields */


        $data['tsm_fy'] = array(
              'value' => $TaxSlabMaster_data->tsm_fy,
         );

        $data['tsm_name'] = array(
            'name' => 'tsm_name',
            'id' => 'tsm_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $TaxSlabMaster_data->tsm_name,
           // 'readonly' => 'readonly'
        );

        $data['tsm_startvalue'] = array(
           'name' => 'tsm_startvalue',
           'id' => 'tsm_startvalue',
           'maxlength' => '50',
           'size' => '40',
           'value' => $TaxSlabMaster_data->tsm_startvalue,

        );

        $data['tsm_endvalue'] = array(
           'name' => 'tsm_endvalue',
           'id' => 'tsm_endvalue',
           'maxlength' => '6',
           'size' => '40',
           'value' => $TaxSlabMaster_data->tsm_endvalue,

        );
         
        $data['tsm_type'] = array(
           'name' => 'tsm_type',
           'id' => 'tsm_type',
           'maxlength' => '6',
           'size' => '40',
           'value' => $TaxSlabMaster_data->tsm_type,

        );

       $data['tsm_gender'] = array(
           'name' => 'tsm_gender',
           'id' => 'tsm_gender',
           'maxlength' => '255',
           'size' => '40',
           'value' => $TaxSlabMaster_data->tsm_gender,

        );
  

        $data['tsm_percent'] = array(
           'name' => 'tsm_percent',
           'id' => 'tsm_percent',
           'maxlength' => '255',
           'size' => '40',
           'value' => $TaxSlabMaster_data->tsm_percent,
        );


        $data['tsm_id'] = $tsm_id;

        $this->form_validation->set_rules('tsm_fy','Financial Year','trim|xss_clean|required');
        $this->form_validation->set_rules('tsm_name','Tax Slab Name','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('tsm_startvalue','Tax Slab Start Value','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('tsm_endvalue','Tax Slab End Value ','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('tsm_type','Tax Slab Type','trim|xss_clean|alpha_numeric_spaces');
        $this->form_validation->set_rules('tsm_gender','Tax Slab Gender','trim|xss_clean|alpha_numeric_spaces');
        $this->form_validation->set_rules('tsm_percent','Tax Slab Percent','trim|xss_clean|numeric');

        if ($_POST)
        {
            $data['tsm_fy']['value'] = $this->input->post('tsm_fy', TRUE);
            $data['tsm_name']['value'] = $this->input->post('tsm_name', TRUE);
            $data['tsm_startvalue']['value'] = $this->input->post('tsm_startvalue', TRUE);
            $data['tsm_end']['valuevalue'] = $this->input->post('tsm_endvalue', TRUE);
            $data['tsm_type']['value'] = $this->input->post('tsm_type', TRUE);
            $data['tsm_gender']['value'] = $this->input->post('tsm_gender', TRUE);
            $data['tsm_percent']['value'] = $this->input->post('tsm_percent', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/edittaxslab', $data);
            return;
        }
        else
        {
            $tsm_fy = $this->input->post('tsm_fy', TRUE);   
            $tsm_name = ucwords(strtolower($this->input->post('tsm_name', TRUE)));
            $tsm_startvalue = strtoupper($this->input->post('tsm_startvalue', TRUE));
            $tsm_endvalue = strtoupper($this->input->post('tsm_endvalue', TRUE));
            $tsm_type = $this->input->post('tsm_type', TRUE);
            $tsm_gender = $this->input->post('tsm_gender', TRUE);
            $tsm_percent = $this->input->post('tsm_percent', TRUE);
            //$sgm_id = $sgm_id;
            $logmessage = "";

            if($TaxSlabMaster_data->tsm_fy != $tsm_fy)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_fy. " changed by " .$tsm_fy;
            if($TaxSlabMaster_data->tsm_name != $tsm_name)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_name. " changed by " .$tsm_name;
            if($TaxSlabMaster_data->tsm_startvalue != $tsm_startvalue)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_startvalue. " changed by " .$tsm_startvalue;

            if($TaxSlabMaster_data->tsm_endvalue != $tsm_endvalue)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_endvalue. " changed by " .$tsm_endvalue;
            if($TaxSlabMaster_data->tsm_type != $tsm_type)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_type. " changed by " .$tsm_type;
            if($TaxSlabMaster_data->tsm_type != $tsm_type)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_gender. " changed by " .$tsm_gender;

              
            if($TaxSlabMaster_data->tsm_percent != $tsm_percent)
                $logmessage = "Add Tax Slab Master " .$TaxSlabMaster_data->tsm_percent. " changed by " .$tsm_percent;

            $update_data = array(
               'tsm_fy' =>$tsm_fy,
               'tsm_name' => $tsm_name,
               'tsm_startvalue' => $tsm_startvalue,
               'tsm_endvalue' => $tsm_endvalue,
               'tsm_type'=>$tsm_type,
               'tsm_gender'=>$tsm_gender,
               'tsm_percent'=> $tsm_percent,

            );

           $tsmflag=$this->SIS_model->updaterec('tax_slab_master', $update_data, 'tsm_id', $tsm_id);
           if(!$tsmflag)
            {
                $this->logger->write_logmessage("error","Error in update Tax Slab Master ", "Error in Tax Slab Master record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Tax Slab Master ", "Error in Tax Slab Master record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating Tax Slab Master - ' . $logmessage . '.', 'error');
                $this->load->view('setup/edittaxslab', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Tax Slab Master", "Tax Slab Master record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Tax Slab Master", "Tax Slab Master record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Tax Slab Master record updated successfully...');
                redirect('setup/displaytaxslab/');
                }
        }//else
        redirect('setup/edittaxslab/');
    }


/****************************************** Salary Grade Master ********************************************/

public function salarygrademaster(){

	if(isset($_POST['salarygrademaster'])) {
        	$this->form_validation->set_rules('sgmname','Salary Grade Master Name','trim|xss_clean|alpha_numeric_spaces|callback_value_exists');
            	$this->form_validation->set_rules('sgmmax','Salary Grade Master Max','trim|xss_clean|required|is_natural_no_zero');
            	$this->form_validation->set_rules('sgmmin','Salary Grade Master Min','trim|xss_clean|required|is_natural_no_zero');
            	$this->form_validation->set_rules('sgmgradepay','Salary Grade Master Gradepay','trim|xss_clean');
            	$this->form_validation->set_rules('sgmlevel','salary Grade Master Level','trim|xss_clean');
            	$this->form_validation->set_rules('paycomm','Pay Commission','trim|xss_clean|required');
            	$this->form_validation->set_rules('workingtype','Working Type','trim|xss_clean|required');
            	$this->form_validation->set_rules('group','Group','trim|xss_clean|required');

            	if($this->form_validation->run()==TRUE){

	    		$sgpc = $this->input->post("paycomm");		
	    		$wt = $this->input->post("workingtype");		
			if($sgpc == "6th"){
				$sgmn = $this->input->post("sgmname");
			}else{
				$sgmn = '';
			}
	    		$group = $this->input->post("group");		
	    		$sgmmax = $this->input->post("sgmmax");
           		$sgmmin = $this->input->post("sgmmin");
            		$sgmgrade = $this->input->post("sgmgradepay");
            		$sgmlevel =$this->input->post("sgmlevel");
              
           		$data = array(
		                'sgm_pc'=>$sgpc,
		                'sgm_wt'=>$wt,
		                'sgm_group'=>$group,
                		'sgm_name'=>strtoupper($sgmn),
		                'sgm_max'=>strtoupper($sgmmax),
                		'sgm_min'=>strtoupper($sgmmin),
		                'sgm_gradepay'=>$sgmgrade,
                		'sgm_level'=>$sgmlevel
			);
			
           		$saldatadup = $this->SIS_model->isduplicatemore('salary_grade_master', $data);

           		if($saldatadup == 1 ){
                 		$this->session->set_flashdata("err_message", "Record already exists with this combination. 'Salary Grade Master Name' = $sgmn, 'Salary Grade Master Max' = $sgmmax , 'Salary Grade Master Min' =$sgmmin,'salary Grade Master Level=$sgmlevel .");
                 		redirect('setup/salarygrademaster');
            	 		return;
           		}
           		else{
	   			if($_POST['sgmmax'] < $_POST['sgmmin'] ){
					$this->session->set_flashdata("err_message", "Salary Grade min amount must be less than Salary Grade max amount");
                       			$this->load->view('setup/salarygrademaster');
                        		return;
				}
				else{		
           				$sgmflag=$this->SIS_model->insertrec('salary_grade_master', $data) ;
           				if(!$sgmflag)
           				{
		                		$this->logger->write_logmessage("insert"," Error in adding salarygrademaster ", " Salary Grade Master data insert error . "  );
                				$this->logger->write_dblogmessage("insert"," Error in adding salarygrademaster ", " Salary Grade Master data insert error . " );
                				$this->session->set_flashdata('err_message','Error in adding salarygrademaster - ' . $_POST['sgmname'] , 'error');
		                		$this->load->view('setup/salarygrademaster');
           				}
		          		else{
                				$this->logger->write_logmessage("insert"," add salarygrademaster ", "Salary Grade Master record added successfully..."  );
                				$this->logger->write_dblogmessage("insert"," add salarygrademaster ", "Salary Grade Master record added successfully..." );
		                		$this->session->set_flashdata("success", "Salary Grade Master added successfully...");
                				redirect("setup/displaysalarygrademaster", "refresh");
              				}
		           	}//else max grade cond		
	            	}//else
	  	}//form validation
        }//post button
      $this->load->view('setup/salarygrademaster');
}


/* Display Salary Grade Master record */

public function displaysalarygrademaster(){

     //   $this->result = $this->SIS_model->get_list('salary_grade_master');
	$whorder = 'sgm_pc ASC, sgm_wt asc,sgm_name ASC,sgm_level ASC,sgm_id ASC';
        $this->result = $this->SIS_model->get_orderlistspficemore('salary_grade_master','*','',$whorder);
        $this->logger->write_logmessage("view"," View ", "Salary Grade Master display successfully..." );
        $this->logger->write_dblogmessage("view"," View Salary Grade Master", "Salary Grade Master successfully..." );
        $this->load->view('setup/displaysalarygrademaster',$this->result);
    }

 /**This function is used for update Salary Grade Master details
     * @param type $sgm_id
     * @return type
     */
   public function editsalarygrademaster($sgm_id) {
      $sgm_data_q=$this->SIS_model->get_listrow('salary_grade_master','sgm_id', $sgm_id);
        if ($sgm_data_q->num_rows() < 1)
        {
           redirect('setup/editsalarygrademaster');
        }
        $SalaryGradeMaster_data = $sgm_data_q->row();
        /* Form fields */
	$data['sgm_pc'] = array(
            'name' => 'paycomm',
            'id' => 'paycomm',
            'maxlength' => '50',
            'size' => '40',
            'value' => $SalaryGradeMaster_data->sgm_pc,
        );

	$data['sgm_wt'] = array(
            'name' => 'workingtype',
            'id' => 'workngtypeid',
            'maxlength' => '50',
            'size' => '40',
            'value' => $SalaryGradeMaster_data->sgm_wt,
        );

	$data['sgm_group'] = array(
            'name' => 'group',
            'id' => 'group',
            'maxlength' => '50',
            'size' => '40',
            'value' => $SalaryGradeMaster_data->sgm_group,
        );
        $data['sgm_name'] = array(
            'name' => 'sgm_name',
            'id' => 'sgm_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $SalaryGradeMaster_data->sgm_name,
        );
        $data['sgm_max'] = array(
           'name' => 'sgm_max',
           'id' => 'sgm_max',
           'maxlength' => '50',
           'size' => '40',
           'value' => $SalaryGradeMaster_data->sgm_max,

        );

        $data['sgm_min'] = array(
           'name' => 'sgm_min',
           'id' => 'sgm_min',
           'maxlength' => '6',
           'size' => '40',
           'value' => $SalaryGradeMaster_data->sgm_min,

        );

        $data['sgm_gradepay'] = array(
           'name' => 'sgm_gradepay',
           'id' => 'sgm_gradepay',
           'maxlength' => '255',
           'size' => '40',
           'value' => $SalaryGradeMaster_data->sgm_gradepay,

        );
     
        $data['sgm_level']= array(
         'name' => 'sgm_level',
         'id' => 'sgm_level',
         'maxlength' => '255',
          'size' => '40',
          'value' => $SalaryGradeMaster_data->sgm_level,
          );

        $data['sgm_id'] = $sgm_id;

        $this->form_validation->set_rules('paycomm','Pay Commission  ','trim|xss_clean|required');
        $this->form_validation->set_rules('workingtype','Working Type  ','trim|xss_clean|required');
        $this->form_validation->set_rules('group','Group ','trim|xss_clean|required');
        $this->form_validation->set_rules('sgm_name','Salary Grade Master Name  ','trim|xss_clean|alpha_numeric_spaces');
        $this->form_validation->set_rules('sgm_max','Salary Grade Master Max ','trim|xss_clean|required|numeric|is_natural_no_zero');
        $this->form_validation->set_rules('sgm_min','Salary Grade Master Min ','trim|xss_clean|required|numeric|is_natural_no_zero');
        $this->form_validation->set_rules('sgm_gradepay','Salary Grade Master Gradepay ','trim|xss_clean');
        $this->form_validation->set_rules('sgm_level','Salary Grade Master Level ','trim|xss_clean');   

        if ($_POST)
        {
            $data['sgm_pc']['value'] = $this->input->post('paycomm', TRUE);
            $data['sgm_wt']['value'] = $this->input->post('workingtype', TRUE);
            $data['sgm_group']['value'] = $this->input->post('group', TRUE);
            $data['sgm_name']['value'] = $this->input->post('sgm_name', TRUE);
            $data['sgm_max']['value'] = $this->input->post('sgm_max', TRUE);
            $data['sgm_min']['value'] = $this->input->post('sgm_min', TRUE);
            $data['sgm_gradepay']['value'] = $this->input->post('sgm_gradepay', TRUE);
            $data['sgm_level']['value'] =$this->input->post('sgm_level',TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editsalarygrademaster', $data);
            return;
        }
        else
        {
            $sgm_pc = $this->input->post('paycomm', TRUE);
            $sgm_wt= $this->input->post('workingtype', TRUE);
            $sgm_group = $this->input->post('group', TRUE);
		if($sgm_pc == "6th"){
                                $sgm_name = $this->input->post("sgm_name",TRUE);
                        }else{
                                $sgm_name = '';
                        }

//            $sgm_name = strtoupper($this->input->post('sgm_name', TRUE));
            $sgm_max = strtoupper($this->input->post('sgm_max', TRUE));
            $sgm_min = strtoupper($this->input->post('sgm_min', TRUE));
            $sgm_gradepay = $this->input->post('sgm_gradepay', TRUE);
            $sgm_level =$this->input->post('sgm_level',TRUE);
            //$sgm_id = $sgm_id;
            $logmessage = "";
            if($SalaryGradeMaster_data->sgm_name != $sgm_name)
                $logmessage = "Add Salary Grade Master " .$SalaryGradeMaster_data->sgm_name. " changed by " .$sgm_name;
            if($SalaryGradeMaster_data->sgm_max != $sgm_max)
                $logmessage = "Add Salary Grade Master " .$SalaryGradeMaster_data->sgm_max. " changed by " .$sgm_max;
            if($SalaryGradeMaster_data->sgm_min != $sgm_min)
                $logmessage = "Add Salary Grade Master " .$SalaryGradeMaster_data->sgm_min. " changed by " .$sgm_min;
            if($SalaryGradeMaster_data->sgm_gradepay != $sgm_gradepay)
                $logmessage = "Add Salary Grade Master " .$SalaryGradeMaster_data->sgm_gradepay. " changed by " .$sgm_gradepay;
            if($SalaryGradeMaster_data->sgm_level !=$sgm_level)
                $logmessage = " Add Salary Grade Master " .$SalaryGradeMaster_data->sgm_level. "changed by ".$sgm_level;

            $instdatasgma = array(
	       'sgma_sgmid'=> $sgm_id,
		'sgma_pc' =>$SalaryGradeMaster_data->sgm_pc,	
		'sgma_wt' =>$SalaryGradeMaster_data->sgm_wt,	
		'sgma_group' =>$SalaryGradeMaster_data->sgm_group,	
               'sgma_name' => $SalaryGradeMaster_data->sgm_name,
               'sgma_max' => $SalaryGradeMaster_data->sgm_max,
               'sgma_min' => $SalaryGradeMaster_data->sgm_min,
               'sgma_gradepay'=> $SalaryGradeMaster_data->sgm_gradepay,
               'sgma_level' =>$SalaryGradeMaster_data->sgm_level,
               'sgma_archuserid'=>$this->session->userdata('id_user'),
               'sgma_archdate'=>date('y-m-d')
            );

            $update_data = array(
               'sgm_pc' => $sgm_pc,
               'sgm_wt' => $sgm_wt,
               'sgm_group' => $sgm_group,
               'sgm_name' => $sgm_name,
               'sgm_max' => $sgm_max,
               'sgm_min' => $sgm_min,
               'sgm_gradepay'=> $sgm_gradepay,
               'sgm_level' =>$sgm_level
            );

           $saldatadupe = $this->SIS_model->isduplicatemore('salary_grade_master', $update_data);

                   if($saldatadupe == 1 ){

                        $this->session->set_flashdata("err_message", "Record  already exist with this combination. 'Salary Grade Master Name' = $sgm_name, 'Salary Grade Master Max' = $sgm_max , 'Salary Grade Master Min' =$sgm_min .");
                           $this->load->view('setup/editsalarygrademaster', $data);
                        return;
                 }
         else{

          if($_POST['sgm_max'] < $_POST['sgm_min'] ){
		 $this->session->set_flashdata("err_message", "Salary Grade min amount must be less than Salary Grade max amount");
             $this->load->view('setup/editsalarygrademaster', $data);
            return;
                       

		}
             
            else{
	 $sgflag=$this->SIS_model->insertrec('salary_grade_master_archive', $instdatasgma);
         if(!$sgflag)
            {
              $this->logger->write_dblogmessage("error","Error in insert salary grade master archive ", "Error in  salary grade master archive record insert" .$sgm_id );
            }else{
              $this->logger->write_dblogmessage("insert","Insert salary grade master archive", "Record inserted in salary grade master archive successfully.." .$sgm_id );
            }

           $sgmflag=$this->SIS_model->updaterec('salary_grade_master', $update_data, 'sgm_id', $sgm_id);
           if(!$sgmflag)
            {
                $this->logger->write_logmessage("error","Error in update Salary Grade Master ", "Error in Salary Grade Master record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Salary Grade Master ", "Error in Salary Grade Master record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating Salary Grade Master - ' . $logmessage . '.', 'error');
                 $this->load->view('setup/editsalarygrademaster',$update_data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Salary Grade Master", "Salary Grade Master record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Salary Grade Master", "Salary Grade Master record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Salary Grade Master record updated successfully...');
                redirect('setup/displaysalarygrademaster/');
                }
              
              }
	    }
        }//else
        $this->load->view('setup/editsalarygrademaster');
    }



 // ########################## Add DDO Module ############################################################################ 

 /* this function has been created for add new ddo record */
  public function newddo(){
        $this->scresult = $this->common_model->get_listspfic2('study_center','sc_id', 'sc_name');

        if(isset($_POST['newddo'])) {
            $this->form_validation->set_rules('campusname','Campus Name','trim|xss_clean|required');
            $this->form_validation->set_rules('deptname','Department Name','trim|xss_clean|required');
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean|required');
            $this->form_validation->set_rules('ddocode','DDO Code','trim|xss_clean|required|alpha_dash');
            //$this->form_validation->set_rules('ddocode','DDO Code','trim|xss_clean|required|alpha_dash|callback_isDdoExist');
            $this->form_validation->set_rules('ddoname','DDO Name','trim|xss_clean|required');
            $this->form_validation->set_rules('remark','Remark','trim|xss_clean');

            if($this->form_validation->run()==TRUE){

	$campid = $this->input->post("campusname"); 
	$campname = $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $campid)->sc_name;
	$deptid = $this->input->post("deptname");
	$deptname = $this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $deptid)->dept_name;
	$schid = $this->input->post("schemecode");
	$schname = $this->SIS_model->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $schid)->sd_name;
	$ddocode = $this->input->post("ddocode");
	$ddoname = $this->input->post("ddoname");

	//$ddodata = array('ddo_scid'=>$campid, 'ddo_deptid'=>$deptid, 'ddo_schid'=>$schid, 'ddo_code'=>strtoupper($ddocode), 'ddo_name'=>strtoupper($ddoname) );
	$ddodata = array('ddo_scid'=>$campid, 'ddo_deptid'=>$deptid, 'ddo_schid'=>$schid, 'ddo_code'=>strtoupper($ddocode) );
          
	$data = array(
                'ddo_scid'=>$_POST['campusname'],
                'ddo_deptid'=>$_POST['deptname'],
                'ddo_schid'=>$_POST['schemecode'],
                'ddo_code'=>strtoupper($_POST['ddocode']),
                'ddo_name'=>strtoupper($_POST['ddoname']),
                'ddo_remark'=>$_POST['remark']
            );
 
         $ddodatadup = $this->SIS_model->isduplicatemore('ddo', $ddodata);

          if($ddodatadup == 1 ){
                   //$this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Campus Name' = $campname  , 'Department Name' = $deptname , 'Scheme Name' = $schname, 'ddo_code' = $ddocode, 'ddo_name' = $ddoname  .");
                   $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Campus Name' = $campname  , 'Department Name' = $deptname , 'Scheme Name' = $schname, 'ddo_code' = $ddocode  .");
                   redirect('setup/newddo');
                   return;
              }
         else{
	   $ddoflag=$this->SIS_model->insertrec('ddo', $data) ;
	   if(!$ddoflag)
	   {
                $this->logger->write_logmessage("insert"," Error in adding DDO ", " DDO data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding DDO ", " DDO data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding DDO - ' . $_POST['cname'] , 'error');
                $this->load->view('setup/newddo');
	   }	
	  else{		
		$this->logger->write_logmessage("insert","DDO added successfully ", "DDO record added successfully..."  );
		$this->logger->write_dblogmessage("insert","DDO added successfully ", "DDO record added successfully..." );
            	$this->session->set_flashdata("success", "DDO added successfully...");
            	redirect("setup/listddo", "refresh");
	      }
           }
	 }
        }
      $this->load->view('setup/newddo');
   }

 /* This function has been created for display DDO record */

  public function listddo(){

	$this->result = $this->SIS_model->get_list('ddo');
        $this->logger->write_logmessage("view"," View ddo", "DDO record display successfully..." );
        $this->logger->write_dblogmessage("view"," View ddo", "DDO record display successfully..." );
        $this->load->view('setup/listddo',$this->result);
    }

 /**This function is used for update ddo details
   * @param type $ddo_id
   * @return type
   */
    public function updateddo($ddo_id) {
	$this->scresult= $this->common_model->get_listspfic2('study_center', 'sc_id', 'sc_name');
	$ddo_data_q=$this->SIS_model->get_listrow('ddo','ddo_id', $ddo_id);
        if ($ddo_data_q->num_rows() < 1)
        {
           redirect('setup/editcategory');
        }
        $ddo_data = $ddo_data_q->row();

        /* Form fields */

        $data['campusname'] = array(
            'name' => 'campusname',
            'id' => 'campusname',
            'maxlength' => '50',
            'size' => '40',
            'value' => $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $ddo_data->ddo_scid)->sc_name,
	    'readonly' => 'readonly'	
        );
        $data['deptname'] = array(
           'name' => 'deptname',
           'id' => 'deptname',
           'maxlength' => '50',
           'size' => '40',
           'value' => $this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $ddo_data->ddo_deptid)->dept_name,
	    'readonly' => 'readonly'	

        );

        $data['schemecode'] = array(
           'name' => 'schemecode',
           'id' => 'schemecode',
           'maxlength' => '50',
           'size' => '40',
           'value' => $this->SIS_model->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $ddo_data->ddo_schid)->sd_name,
	    'readonly' => 'readonly'	

        );

        $data['ddocode'] = array(
           'name' => 'ddocode',
           'id' => 'ddocode',
           'maxlength' => '255',
           'size' => '40',
           'value' => $ddo_data->ddo_code,

        );

        $data['ddoname'] = array(
           'name' => 'ddoname',
           'id' => 'ddoname',
           'maxlength' => '255',
           'size' => '40',
           'value' => $ddo_data->ddo_name,

        );
        $data['remark'] = array(
           'name' => 'remark',
           'id' => 'remark',
           'maxlength' => '255',
           'size' => '40',
           'value' => $ddo_data->ddo_remark,

        );
        $data['ddo_id'] = $ddo_id;

            $this->form_validation->set_rules('campusname','Campus Name','trim|xss_clean|required');
            $this->form_validation->set_rules('deptname','Department Name','trim|xss_clean|required');
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean|required');
            $this->form_validation->set_rules('ddocode','DDO Code','trim|xss_clean|required');
            $this->form_validation->set_rules('ddoname','DDO Name','trim|xss_clean|required');
            $this->form_validation->set_rules('remark','Remark','trim|xss_clean');

        if ($_POST)
        {
            $data['campusname']['value'] = $this->input->post('campusname', TRUE);
            $data['deptname']['value'] = $this->input->post('deptname', TRUE);
            $data['schemecode']['value'] = $this->input->post('schemecode', TRUE);
            $data['ddocode']['value'] = $this->input->post('ddocode', TRUE);
            $data['ddoname']['value'] = $this->input->post('ddoname', TRUE);
            $data['remark']['value'] = $this->input->post('remark', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/updateddo', $data);
            return;
        }
	else
        {

            $data_scid = $this->input->post('campusname', TRUE);
	    $campid = $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $data_scid)->sc_name;	
            $data_deptid = $this->input->post('deptname', TRUE);
	    $deptid = $this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $data_deptid)->dept_name;	
            $data_schid = $this->input->post('schemecode', TRUE);
	    $schid = $this->SIS_model->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $data_schid)->sd_name;
            $data_code = strtoupper($this->input->post('ddocode', TRUE));
            $data_name = $this->input->post('ddoname', TRUE);
            $data_remark = $this->input->post('remark', TRUE);
            $data_ddoid = $ddo_id;
	    $logmessage = "";
            if($ddo_data->ddo_scid != $data_scid)
                $logmessage = "Add DDO " .$ddo_data->ddo_scid. " changed by " .$data_scid;
            if($ddo_data->ddo_deptid != $data_deptid)
                $logmessage = "Add DDO " .$ddo_data->ddo_deptid. " changed by " .$data_deptid;
            if($ddo_data->ddo_schid != $data_schid)
                $logmessage = "Add DDO " .$ddo_data->ddo_schid. " changed by " .$data_schid;
            if($ddo_data->ddo_code != $data_code)
                $logmessage = "Add DDO " .$ddo_data->ddo_code. " changed by " .$data_code;
            if($ddo_data->ddo_name != $data_name)
                $logmessage = "Add DDO " .$ddo_data->ddo_name. " changed by " .$data_name;
            if($ddo_data->ddo_remark != $data_remark)
                $logmessage = "Add DDO " .$ddo_data->ddo_remark. " changed by " .$data_remark;

	   //$dataeditddo = array('ddo_scid'=>$campid, 'ddo_deptid'=>$deptid, 'ddo_schid'=>$schid, 'ddo_code'=>$data_code, 'ddo_name'=>$data_name);
	   $dataeditddo = array('ddo_scid'=>$data_scid, 'ddo_deptid'=>$data_deptid, 'ddo_schid'=>$data_schid, 'ddo_code'=>$data_code, 'ddo_name'=>$data_name, 'ddo_remark'=>$data_remark);

            $updatea_data = array(
		'ddoa_ddoid'=>$ddo_id,
                'ddoa_scid'=> $ddo_data->ddo_scid,
                'ddoa_deptid'=> $ddo_data->ddo_deptid,
                'ddoa_schid'=>  $ddo_data->ddo_schid,
                'ddoa_code'=>strtoupper($ddo_data->ddo_code),
                'ddoa_name'=>strtoupper($ddo_data->ddo_name),
                'ddoa_remark'=>$ddo_data->ddo_remark,
                'ddoa_archuserid'=>$this->session->userdata('id_user'),
                'ddoa_archdate'=>date('y-m-d')
            );

	$ddodatadup = $this->SIS_model->isduplicatemore('ddo', $dataeditddo);
	if($ddodatadup == 1){

                   $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Campus Name' = $campid  , 'Department Name' = $deptid , 'Scheme Name' = $schid, 'ddo Code' = $data_code, 'ddo name' = $data_name  .");
		redirect('setup/listddo/');
 		return;
           }
	else{
	 $ddoflag=$this->SIS_model->insertrec('ddo_archive', $updatea_data);
         if(!$ddoflag)
         {
              $this->logger->write_dblogmessage("error","Error in insert ddo archive ", "Error in  ddo archive record insert" .$logmessage );
         }else{
              $this->logger->write_dblogmessage("insert","Insert ddo archive", "Record inserted in ddo archive successfully.." .$logmessage );
         }

            $update_data = array(
                //'ddo_scid'=> $this->common_model->get_listspfic1('study_center', 'sc_id', 'sc_name', $data_scid)->sc_id,
                'ddo_scid'=> $data_scid,
                //'ddo_deptid'=> $this->common_model->get_listspfic1('Department', 'dept_id', 'dept_name', $data_deptid)->dept_id,
                'ddo_deptid'=> $data_deptid,
                //'ddo_schid'=> $this->SIS_model->get_listspfic1('scheme_department', 'sd_id', 'sd_name', $data_schid)->sd_id,
                'ddo_schid'=> $data_schid,
                'ddo_code'=>strtoupper($data_code),
                'ddo_name'=>strtoupper($data_name),
                'ddo_remark'=>$data_remark
            );

            $datadup_data = array(
                //'ddo_scid'=> $this->common_model->get_listspfic1('study_center', 'sc_id', 'sc_name', $data_scid)->sc_id,
                'ddo_scid'=> $data_scid,
                //'ddo_deptid'=> $this->common_model->get_listspfic1('Department', 'dept_id', 'dept_name', $data_deptid)->dept_id,
                'ddo_deptid'=> $data_deptid,
                //'ddo_schid'=> $this->SIS_model->get_listspfic1('scheme_department', 'sd_id', 'sd_name', $data_schid)->sd_id,
                'ddo_schid'=> $data_schid,
                'ddo_code'=>strtoupper($data_code)
            );

            if(($ddo_data->ddo_code != $data_code)||($ddo_data->ddo_scid != $data_scid)||($ddo_data->ddo_deptid != $data_deptid)||($ddo_data->ddo_schid != $data_schid)){
	      //$ddodupflag = $this->SIS_model->isduplicate('ddo','ddo_code', $data_code);
	      $ddodupflag = $this->SIS_model->isduplicatemore('ddo', $datadup_data);
	      if($ddodupflag ==1)
	       {
                     $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Study Center' = $campid, 'Department' = $deptid , 'Scheme' = $schid, 'DDO Code' = $data_code  .");
                     redirect('setup/listddo');
                     return;
		}
		else{
	   	    $ddoflag=$this->SIS_model->updaterec('ddo', $update_data, 'ddo_id', $data_ddoid);
	   	    if(!$ddoflag)	
            	    {
                	$this->logger->write_logmessage("error","Error in update DDO ", "Error in DDO record update. $logmessage . " );
                	$this->logger->write_dblogmessage("error","Error in update DDO ", "Error in DDO record update. $logmessage ." );
                	$this->session->set_flashdata('err_message','Error updating DDO - ' . $logmessage . '.', 'error');
                	$this->load->view('setup/updateddo', $data);
               	    }
            	else{
                	$this->logger->write_logmessage("update","Edit DDO", "DDO record updated successfully... $logmessage . " );
                	$this->logger->write_dblogmessage("update","Edit DDO", "DDO record updated successfully... $logmessage ." );
                	$this->session->set_flashdata('success','DDO record updated successfully...');
                	redirect('setup/listddo/');
                   }
		  }	
		}
	   else{ 
	   $ddoflag=$this->SIS_model->updaterec('ddo', $update_data, 'ddo_id', $data_ddoid);
	   if(!$ddoflag)	
            {
                $this->logger->write_logmessage("error","Error in update DDO ", "Error in DDO record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update DDO ", "Error in DDO record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating DDO - ' . $logmessage . '.', 'error');
                $this->load->view('setup/updateddo', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit DDO", "DDO record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit DDO", "DDO record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','DDO record updated successfully...');
                redirect('setup/listddo/');
                }
	   }
	 }
        }//else
        redirect('setup/updateddo/');
    }

/** This function check for duplicate ddo code 
     * @return type
    */

    public function isDdoExist($ddo_code) {

        $is_exist = $this->SIS_model->isduplicate('ddo','ddo_code',$ddo_code);
        if ($is_exist)
        {
            $this->form_validation->set_message('isDdoExist', 'DDO code is already exist.');
            return false;
        }
        else {
            return true;
        }
    }
 // ########################### End of DDO Module  ########################################################

// =============== Add Society Module =========================================================================================================== 

 
public function addsociety(){

        if(isset($_POST['addsociety'])) {
            $this->form_validation->set_rules('soc_name','Society Name','trim|xss_clean|required');
            $this->form_validation->set_rules('soc_code','Society Code','trim|xss_clean|required');
            $this->form_validation->set_rules('soc_regdate','Society Registration','trim|xss_clean');
            $this->form_validation->set_rules('soc_regdate','Society Registration','trim|xss_clean');
            $this->form_validation->set_rules('soc_address','Society Address','trim|xss_clean');
	    $this->form_validation->set_rules('soc_phone','Society Phone ','trim|xss_clean');
	    $this->form_validation->set_rules('soc_mobile','Society Mobile','trim|xss_clean');
	    $this->form_validation->set_rules('soc_email','Society Email','trim|xss_clean');
	    $this->form_validation->set_rules('soc_pan','Society PAN NO','trim|xss_clean');
	    $this->form_validation->set_rules('soc_tan','Society TAN NO','trim|xss_clean');
	    $this->form_validation->set_rules('soc_gst','Society GST NO','trim|xss_clean');
	    $this->form_validation->set_rules('soc_bname','Society Bank Name','trim|xss_clean');
	    $this->form_validation->set_rules('soc_bacno','Society Bank account Number','trim|xss_clean');
	    $this->form_validation->set_rules('soc_bifsc','Society IFSC Code','trim|xss_clean');
	    $this->form_validation->set_rules('soc_bmicr','Society bank MICR','trim|xss_clean');
	    $this->form_validation->set_rules('soc_bbranch','Society bank branch','trim|xss_clean');
	    $this->form_validation->set_rules('soc_bactype','Society acount type ','trim|xss_clean');
	    $this->form_validation->set_rules('soc_remark','Society Remark','trim|xss_clean');
            


            if($this->form_validation->run()==TRUE){
            $data = array(
                'soc_scode'=>$_POST['soc_code'],    
                'soc_sname'=>ucwords(strtolower($_POST['soc_name'])),
                'soc_regno'=>$_POST['soc_regno'],
                'soc_regdate'=>$_POST['soc_regdate'],
                'soc_address'=>$_POST['soc_address'], 
		'soc_phone'=>$_POST['soc_phone'],
		'soc_mobile'=>$_POST['soc_mobile'],
		'soc_email'=>$_POST['soc_email'],
		'soc_pan'=>strtoupper($_POST['soc_pan']),
		'soc_tan'=>strtoupper($_POST['soc_tan']),
		'soc_gst'=>strtoupper($_POST['soc_gst']),
		'soc_bname'=>$_POST['soc_bname'],
		'soc_bacno'=>$_POST['soc_bacno'],
		'soc_bifsc'=>strtoupper($_POST['soc_bifsc']),
		'soc_bmicr'=>$_POST['soc_bmicr'],
		'soc_bbranch'=>$_POST['soc_bbranch'],
		'soc_bactype'=>$_POST['soc_bactype'],
		'soc_remarks'=>$_POST['soc_remark'],
                'soc_creatorid'=>$this->session->userdata('id_user'),
                'soc_creatordate'=>date('y-m-d'),
                'soc_modifierid'=>$this->session->userdata('id_user'),
                'soc_modifydate' => date('y-m-d'),
                                    
            );
               $data1 = array(
                'soc_sname'=>$_POST['soc_name'],
		'soc_scode'=>$_POST['soc_code'],
            );
             $socdatadupe = $this->SIS_model->isduplicatemore('society_master_list', $data1);
                   if($socdatadupe == 1 ){
                        $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Society Code' = $soc_code, 'Society Name' = $soc_name.");
                        redirect('setup/addsociety');
                        return;
                 }
           else{     
  
              $socflag=$this->SIS_model->insertrec('society_master_list', $data) ;
              if(!$socflag)
               {    

                $this->logger->write_logmessage("insert"," Error in adding societydetails ", " SocietyDetails data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding societydetails  ", " SocietyDetails data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding societydetails - ' . $_POST['soc_name'] , 'error');
                $this->load->view('setup/addsociety');

	      }

          else{ 
		$this->logger->write_logmessage("insert"," add societydetails ", "societydetails record added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add societydetails ", "societydetails record added successfully..." );
                $this->session->set_flashdata("success", "society details added successfully...");
                redirect("setup/displaysociety", "refresh");
               }             
            }
          }
        }    
     $this->load->view('setup/addsociety');
   }


	public function displaysociety(){

        	$this->result = $this->SIS_model->get_list('society_master_list');
	        $this->logger->write_logmessage("view"," View ", "society Master display successfully..." );
        	$this->logger->write_dblogmessage("view"," View society  Master", "Society Master successfully..." );
	        $this->load->view('setup/displaysociety',$this->result);
    	}

	public function delete_soc($id){
		$roleid=$this->session->userdata('id_role');
		if($roleid==1){
			$delflag=$this->SIS_model->deleterow('society_master_list','soc_id',$id);
		        if (! $delflag   )
        		{
	            		$this->logger->write_logmessage("delete", "Error in delete society detail  [ society id:" . $id . "]");
        	    		$this->logger->write_dblogmessage("delete", "Error in delete society detail  [society id:" . $id . "]");
	            		$this->session->set_flashdata('Error in deleting society details - ' . $id);
	                	redirect("setup/displaysociety", "refresh");
	        	}
        		else {

	            		$this->logger->write_logmessage("delete", " Deleted delete society Record  [society id:" . $id . "]");
        	    		$this->logger->write_dblogmessage("delete", "Deleted delete society Record  [society id:" . $id . "]");
	            		$this->session->set_flashdata("success", 'Deleted  society Record successfully by '.$this->session->userdata('username') );
	                	redirect("setup/displaysociety", "refresh");
	        	}
		}
		else{
			$this->logger->write_logmessage("delete"," tring to delete society details ", "tring to delete society details record by".$this->session->userdata('username')  );
                	$this->logger->write_dblogmessage("delete","tring to  delete society details ", "tring to delete society details record by".$this->session->userdata('username') );
        	        $this->session->set_flashdata("err_message", "You do not have the aright to delete society details. ");
	                redirect("setup/displaysociety", "refresh");
		}
	}


/**This function is used for update Society records
     * @param type $id
     * @return type
     */
         

    public function editsociety($soc_id) {
        $soc_data_q=$this->SIS_model->get_listrow('society_master_list','soc_id', $soc_id);
        if ($soc_data_q->num_rows() < 1)
        {
           redirect('setup/editsociety');
        }
        $society_data = $soc_data_q->row();
        /* Form fields */

	$data['soc_name'] = array(
            'name' => 'soc_name',
            'id' => 'soc_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $society_data->soc_sname,
	    'readonly' => 'readonly' 
        );

         $data['soc_code'] = array(
            'name' => 'soc_code',
            'id' => 'soc_code',
            'maxlength' => '50',
            'size' => '40',
            'value' => $society_data->soc_scode,
        );



        $data['soc_address'] = array(
           'name' => 'soc_address',
           'id' => 'soc_address',
           'maxlength' => '50',
           'size' => '40',
           'value' => $society_data->soc_address,

        );

	$data['soc_purpose'] = array(
           'name' => 'soc_purpose',
           'id' => 'soc_purpose',
           'maxlength' => '50',
           'size' => '40',
           'value' => $society_data->soc_purpose,

        );

	 $data['soc_remark'] = array(
           'name' => 'soc_remark',
           'id' => 'soc_remark',
           'maxlength' => '50',
           'size' => '40',
           'value' => $society_data->soc_sremark,
	);

         $data['soc_regdate'] = array(
           'name' => 'soc_regdate',
           'id' => 'soc_regdate',
           'maxlength' => '50',
           'size' => '40',
           'value' => $society_data->soc_regdate,
	        );
   

  $data['soc_id'] = $soc_id;

	$this->form_validation->set_rules('soc_name','Society Name','trim|xss_clean|required|alpha_dash');
        $this->form_validation->set_rules('soc_code','Society Code','trim|xss_clean|required|alpha_dash');
	$this->form_validation->set_rules('soc_address','Society Address','trim|xss_clean|required');
	$this->form_validation->set_rules('soc_purpose','Society Purpose','trim|xss_clean');
	$this->form_validation->set_rules('soc_remark','Society Remark','trim|xss_clean');


        if ($_POST)
        {
           // $data['soc_userid']['value'] = $this->input->post('soc_userid', TRUE);
            $data['soc_name']['value'] = $this->input->post('soc_name', TRUE);
            $data['soc_code']['value'] = $this->input->post('soc_code', TRUE);
            $data['soc_address']['value'] = $this->input->post('soc_address', TRUE);
	    $data['soc_purpose']['value'] = $this->input->post('soc_purpose', TRUE);
	    $data['soc_remark']['value'] = $this->input->post('soc_remark', TRUE);
            $data['soc_regdate']['value'] = $this->input->post('soc_regdate', TRUE);

        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/editsociety', $data);
            return;
        }
        else
        {
            $soc_name = strtoupper($this->input->post('soc_name', TRUE));
            $soc_code = strtoupper($this->input->post('soc_code', TRUE));
            $soc_address = strtoupper($this->input->post('soc_address', TRUE));
	    $soc_purpose = strtoupper($this->input->post('soc_purpose', TRUE));
	    $soc_remark = strtoupper($this->input->post('soc_remark', TRUE));
            $soc_regdate = strtoupper($this->input->post('soc_regdate', TRUE));
            $logmessage = "";

          

             if($society_data->soc_name != $soc_name)
                $logmessage = "Add Society " .$society_data->soc_sname. " changed by " .$soc_name;
		
	    if($society_data->soc_code != $soc_code)
                $logmessage = "Add Society " .$society_data->soc_scode. " changed by " .$soc_code;

             if($society_data->soc_address != $soc_address)
                $logmessage = "Add Society " .$society_data->soc_address. " changed by " .$soc_address;
		
	     if($society_data->soc_remark != $soc_purpose)
                $logmessage = "Add Society " .$society_data->soc_purpose. " changed by " .$soc_purpose;		

		 if($society_data->soc_remark != $soc_remark)
                $logmessage = "Add Society " .$society_data->soc_sremark. " changed by " .$soc_remark;
 
             if($society_data->soc_regdate != $soc_regdate)
                $logmessage = "Add Society " .$society_data->soc_regdate. " changed by " .$soc_regdate;



              $update_data = array(
               'soc_sname'=>ucfirst(strtolower($_POST['soc_name'])),
	       'soc_scode'=>($_POST['soc_code']),
               'soc_address'=>($_POST['soc_address']),
	       'soc_purpose'=>($_POST['soc_purpose']),
	       'soc_sremark'=>ucwords($_POST['soc_remark']),
               'soc_regdate'=>($_POST['soc_regdate']),


            );
               $socdatadupe = $this->SIS_model->isduplicatemore('society_master_list', $update_data);

                   if($socdatadupe == 1 ){

                        $this->session->set_flashdata("err_message", "Record is already exist with this combination. 'Society Name' = $soc_name, 'Society Code' = $soc_code , 'Society Address' = $soc_address,  'Society Purpose' =$soc_purpose, 'Society Remark' =$soc_remark, 'Society Registration Date' = $soc_regdate .");
                        redirect('setup/displaysociety/');
                        return;
                 }
         else{

           

           $socflag=$this->SIS_model->updaterec('society_master_list', $update_data, 'soc_id', $soc_id);
           if(!$socflag)
            {
                $this->logger->write_logmessage("error","Error in update Society ", "Error in Society record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in update Society ", "Error in Society record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error updating Society - ' . $logmessage . '.', 'error');
                $this->load->view('setup/editsociety', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Society", "Edit Society record updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Society", "Edit Society record updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Society record updated successfully...');
                redirect('setup/displaysociety/');
                }
        }//else

        redirect('setup/editsociety/');
    }
 }

 

}










