<?php

/* 
 * @name Announcement.php
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Announcement extends CI_Controller
    {
    function __construct() {
        parent::__construct();

                $this->load->model('Common_model',"commodel");
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }
/** This function add the announcement
     * @return type
     */
    public function addannouncement(){
	    if(isset($_POST['addannouncement'])) {
			$this->form_validation->set_rules('anou_cname','Announcement Component Name','trim|xss_clean|required');
			$this->form_validation->set_rules('anou_type','Announcement Type','trim|xss_clean|required');
			$this->form_validation->set_rules('anou_title','Announcement Title','trim|xss_clean|required');
			$this->form_validation->set_rules('anou_description','Announcement Description','trim|xss_clean|required');
			$this->form_validation->set_rules('userfile','Upload Attachment','trim|xss_clean');
                        $this->form_validation->set_rules('anou_publishdate','Announcement Publish Date','trim|xss_clean|required');
			$this->form_validation->set_rules('anou_expdate','Announcement Expiry Date','trim|xss_clean|required');
                        $this->form_validation->set_rules('anou_remark','Announcement Remark','trim|xss_clean|required');
			if($this->form_validation->run()==TRUE){
	 	 		//upload photo and signature in dir which is not accessble directly with size limit photo 200kb and signature 20kb
				$name = $_FILES['userfile']['name'];
				$data = array(
                                        'anou_cname'=>strtoupper($_POST['anou_cname']),
                                        'anou_type'=>strtoupper($_POST['anou_type']),
                                        'anou_title'=>$_POST['anou_title'],
                                        'anou_description'=>$_POST['anou_description'],
                                        'anou_attachment'=>$name,
                                        'anou_publishdate'=>$_POST['anou_publishdate'],
                                        'anou_expdate'=>$_POST['anou_expdate'],
                                        'anou_remark'=>$_POST['anou_remark'],
                                        );
                                $annoflag=$this->commodel->insertrec('announcement', $data);
				$uplflag=false;
				if(!empty($name)){
					$_FILES['userFile']['name'] = $_FILES['userfile']['name'];
        	       	 		$_FILES['userFile']['type'] = $_FILES['userfile']['type'];
                			$_FILES['userFile']['tmp_name'] = $_FILES['userfile']['tmp_name'];
                			$_FILES['userFile']['size'] = $_FILES['userfile']['size'];

					$config['upload_path'] = 'uploads/announcement/';
					$config['max_size'] = '2048000';
        	 	 		$config['allowed_types'] = 'jpg|jpeg|png|gif|pdf|doc|odt';
               				$config['file_name'] = $name;
					$config['overwrite'] = TRUE;

					$this->load->library('upload',$config);
                	                $this->upload->initialize($config);

					if($this->upload->do_upload('userfile')){
        	           	 		$uploadData = $this->upload->data();
                	   	 		$picture = $uploadData['file_name'];
						$this->logger->write_logmessage("insert","Announcement attachment upload", "Announcement attachment successfully Added");
						$this->logger->write_dblogmessage("insert","Announcement attachment upload", "Announcement attachment successfully Added");
						$uplflag=true;
                			}else{
                    				$picture = '';
						$error =  array('error' => $this->upload->display_errors());
						foreach ($error as $item => $value):
							$ferror = $ferror.$value;
						endforeach;
						$ferror=str_replace("\r\n","",$ferror);
						$simsg = "The permitted size of document is 200kb";
						$ferror = $simsg.$ferror;
						$this->logger->write_logmessage("insert","File upload error", $ferror);
						$this->logger->write_dblogmessage("insert","File upload error", $ferror);
						$uplflag=false;
					}
				}
				if($uplflag){
						$this->logger->write_logmessage("insert","Announcement added", "Announcement successfully Added with attachment.");
						$this->logger->write_dblogmessage("insert","Announcement added", "Announcement successfully Added with attachment.");
						$this->session->set_flashdata('success', 'Announcement successfully Added with attachment.');
                				redirect('announcement/viewannouncement');
				}else{
						$this->logger->write_logmessage("insert","Announcement added", "Announcement successfully Added without attachment.");
						$this->logger->write_dblogmessage("insert","Announcement added", "Announcement successfully Added without attachment.");
						$this->session->set_flashdata('success', 'Announcement successfully Added without attachment.');
                				redirect('announcement/viewannouncement');

				}
			}//end of validation
			else{	
				$this->session->set_flashdata('error','The invalid data in creating announcement');
                                redirect('announcement/addannouncement');				
			}
		}			
	$this->load->view('announcement/addannouncement');
       }//end function
/** This function display the Announcement                                                                                                                                                 
  * @param type  
  * @return type
  */

     public function viewannouncement() {
	$cdate = date('Y-m-d');
        $wharray = array('anou_cname=' => 'SIS', 'anou_publishdate<=' => $cdate,'anou_expdate>=' => $cdate);
        $this->annoresult = $this->commodel->get_listarry('announcement','*',$wharray);	
        $this->logger->write_logmessage("view"," View Announcement", " Announcement details...");
        $this->logger->write_dblogmessage("view"," View Exam Center" , "Announcement record display successfully..." );
        $this->load->view('announcement/viewannouncement',$this->annoresult);
       }

/**This function is used for update exam center records
     */

   public function editannouncement($id){
        $announcementrow=$this->commodel->get_listrow('announcement','anou_id', $id);
        if ($announcementrow->num_rows() < 1)
        {
            redirect('announcement/editannouncement');
        }
        $anno_data_q = $announcementrow->row();

/* Form fields */

               $data['anou_cname'] = array(
               'name' => 'anou_cname',
               'id' => 'anou_cname',
               'size' => '40',
               'value' => $anno_data_q->anou_cname,
               );
	       $data['anou_type'] = array(
               'name' => 'anou_type',
               'id' => 'anou_type',
               'size' => '40',
               'value' => $anno_data_q->anou_type,
               );
	       $data['anou_title'] = array(
               'name' => 'anou_title',
               'id' => 'anou_title',
               'size' => '40',
               'value' => $anno_data_q->anou_title,
               );
	       $data['anou_description'] = array(
               'name' => 'anou_description',
               'id' => 'anou_description',
               'size' => '40',
               'value' => $anno_data_q->anou_description,
               );
	       $data['anou_publishdate'] = array(
               'name' => 'anou_publishdate',
               'id' => 'anou_publishdate',
               'size' => '40',
               'value' => $anno_data_q->anou_publishdate,
               );
	       $data['anou_expdate'] = array(
               'name' => 'anou_expdate',
               'id' => 'anou_expdate',
               'size' => '40',
               'value' => $anno_data_q->anou_expdate,
               );
	       $data['anou_remark'] = array(
               'name' => 'anou_remark',
               'id' => 'anou_remark',
               'size' => '40',
               'value' => $anno_data_q->anou_remark,
               );
	       $data['id'] = $id;
/*Form Validation*/
			$this->form_validation->set_rules('anou_cname','Announcement Component Name','trim|xss_clean|required');
                        $this->form_validation->set_rules('anou_type','Announcement Type','trim|xss_clean|required');
                        $this->form_validation->set_rules('anou_title','Announcement Title','trim|xss_clean|required');
                        $this->form_validation->set_rules('anou_description','Announcement Description','trim|xss_clean|required');
//                      $this->form_validation->set_rules('userfile','Upload Attachment','trim|xss_clean');
                        $this->form_validation->set_rules('anou_publishdate','Announcement Publish Date','trim|xss_clean|required');
                        $this->form_validation->set_rules('anou_expdate','Announcement Expiry Date','trim|xss_clean|required');
                        $this->form_validation->set_rules('anou_remark','Announcement Remark','trim|xss_clean|required');

/* Re-populating form */
                 if ($_POST){
                 $data['anou_cname']['value'] = $this->input->post('anou_cname', TRUE);
                 $data['anou_type']['value'] = $this->input->post('anou_type', TRUE);
                 $data['anou_title']['value'] = $this->input->post('anou_title', TRUE);
                 $data['anou_description']['value'] = $this->input->post('anou_description', TRUE);
                 $data['anou_publishdate']['value'] = $this->input->post('anou_publishdate', TRUE);
		 $data['anou_expdate']['value'] = $this->input->post('anou_expdate', TRUE);
                 $data['anou_remark']['value'] = $this->input->post('anou_remark', TRUE);
		 }
                 if ($this->form_validation->run() == FALSE){
                        $this->load->view('announcement/editannouncement', $data);
                        return;
			}else{
                        $anou_cname = $this->input->post('anou_cname', TRUE);
                        $anou_type = $this->input->post('anou_type', TRUE);
                        $anou_title = ucwords(strtolower($this->input->post('anou_title', TRUE)));
                        $anou_description = ucwords(strtolower($this->input->post('anou_description', TRUE)));
                        $anou_publishdate = $this->input->post('anou_publishdate', TRUE);
                        $anou_expdate = $this->input->post('anou_expdate', TRUE);
                        $anou_remark = $this->input->post('anou_remark', TRUE);
                        $logmessage = "";

		 if($anno_data_q->anou_cname != $anno_data_q)
                                $logmessage = "Add Announcement Component Name" .$anno_data_q->anou_cname. " changed by " .$anou_cname;
		 if($anno_data_q->anou_type != $anno_data_q)
                                $logmessage = "Add Announcement Type" .$anno_data_q->anou_type. " changed by " .$anou_type;
		 if($anno_data_q->anou_title != $anno_data_q)
                                $logmessage = "Add Announcement Title" .$anno_data_q->anou_title. " changed by " .$anou_title;
		 if($anno_data_q->anou_description != $anno_data_q)
                                $logmessage = "Add Announcement Description" .$anno_data_q->anou_description. " changed by " .$anou_description;
		 if($anno_data_q->anou_publishdate != $anno_data_q)
                                $logmessage = "Add Announcement Publish Date" .$anno_data_q->anou_publishdate. " changed by " .$anou_publishdate;
		 if($anno_data_q->anou_expdate != $anno_data_q)
                                $logmessage = "Add Announcement Expiry Date" .$anno_data_q->anou_expdate. " changed by " .$anou_expdate;
		 if($anno_data_q->anou_remark != $anno_data_q)
                                $logmessage = "Add Announcement Remark" .$anno_data_q->anou_remark. " changed by " .$anou_remark;

		 $arins_data = array(
					'annoa_anoid' => $id,
					'anoua_cname'=>$anno_data_q->anou_cname,
                                        'anoua_type'=>$anno_data_q->anou_type,
                                        'anoua_title'=>$anno_data_q->anou_title,
                                        'anoua_description'=>$anno_data_q->anou_description,
                                        'anoua_publishdate'=>$anno_data_q->anou_publishdate,
                                        'anoua_expdate'=>$anno_data_q->anou_expdate,
                                        'anoua_remark'=>$anno_data_q->anou_remark,
					'anoua_archivename' =>'SIS - '. $this->session->userdata('username'),
                                	'anoua_archivedate' => date('y-m-d')

                        );
                        $annoflaga=$this->commodel->insertrec('announcement_archive', $arins_data);
	
		 $update_data = array(
                                'anou_cname' => $anou_cname,
                                'anou_type' => $anou_type,
                                'anou_title' => $anou_title,
                                'anou_description' => $anou_description,
                                'anou_publishdate' => $anou_publishdate,
                                'anou_expdate' => $anou_expdate,
                                'anou_remark' => $anou_remark,
                        );
                        $announcementflag=$this->commodel->updaterec('announcement', $update_data,'anou_id', $id);
			if(!$announcementflag)
                        {
                                $this->logger->write_logmessage("error","Edit Announcement Setting error", "Edit Announcement Setting details. $logmessage ");
                                $this->logger->write_dblogmessage("error","Edit Announcement Setting error", "Edit Announcement Setting details. $logmessage ");
                                $this->session->set_flashdata('err_message','Error updating Announcement- ' . $logmessage . '.', 'error');
                                $this->load->view('announcement/editannouncement', $data);
                        }
                        else{
                                $this->logger->write_logmessage("update","Edit Announcement Setting", "Edit Announcement Setting details. $logmessage ");
                                $this->logger->write_dblogmessage("update","Edit Announcement Setting", "Edit Announcement Setting details. $logmessage ");
                                $this->session->set_flashdata('success','Announcement detail updated successfully..');
                                redirect('announcement/viewannouncement/');
                        }
                }//else
   }//end edit announcement function

}//end class

