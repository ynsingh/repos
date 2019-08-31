<?php

defined('BASEPATH') OR exit('No direct script access allowed');


class Iprocess extends CI_Controller
{
    function __construct() 
	{
        parent::__construct();
        $this->load->model('login_model','lgnmodel'); 
  		$this->load->model('common_model'); //,'commodel'
        $this->load->model('PICO_model');   //changed to PICO insted of
        $this->load->model('SIS_model'); 
       	$this->load->model('dependrop_model','depmodel');
        $this->load->model('university_model','unimodel');
        	$this->load->model("Mailsend_model","mailmodel");
        $this->db4=$this->load->database('pico', TRUE);
        if(empty($this->session->userdata('id_user'))) {
        $this->session->set_flashdata('flash_data', 'You don\'t have access!');
        
		redirect('welcome');
        }
    }
	
	public function specification()
	{
		
		$this->load->view('iprocess/specification',);
	}
	
	
	public function specificationsform()
	{

		if(isset($_POST['Ispecification']))
		{

            $this->form_validation->set_rules('enquiry_date','Enquiry Date:','trim|xss_clean|required');
            $this->form_validation->set_rules('enquiry_no','Enquiry No:','trim|xss_clean|required');
            $this->form_validation->set_rules('enquiry_lastdate','Enquiry Last Date:','required');
            $this->form_validation->set_rules('item_name','Item Name:','trim|xss_clean|required');
            $this->form_validation->set_rules('item_quantity','Item Quantity:','required');
            $this->form_validation->set_rules('description','Description');
            $this->form_validation->set_rules('description_upload_filename','Description Upload Filename:');
            $this->form_validation->set_rules('name','Name:');
            $this->form_validation->set_rules('dept_id','Dept Id.');
            $this->form_validation->set_rules('desig_id','Designation Id:','trim|xss_clean|required');
            $this->form_validation->set_rules('email','E-mail ID:','trim|xss_clean|required');
			$this->form_validation->set_rules('phone','Phone','required');
            $this->form_validation->set_rules('terms_condition_desc','Terms and Condition Description');
            $this->form_validation->set_rules('terms_condition_filename','Terms and Condition Filename');
 

            if($this->form_validation->run()==TRUE)
			{

                $data = array(
                'enquiry_date'=>$_POST['enquiry_date'],
                'enquiry_no'=>$_POST['enquiry_no'],
                'enquiry_lastdate'=>$_POST['enquiry_lastdate'],
                'item_name'=>$_POST['item_name'],
                'item_quantity'=>$_POST['item_quantity'],
                'name'=>$_POST['name'],
                'dept_id'=>$_POST['dept_id'],
                'desig_id'=>$_POST['desig_id'],
                'email'=>$_POST['email'],
				'phone'=>$_POST['phone'],
                
               // 'creator_id'=>$this->session->userdata('username'),
               // 'creator_date'=>date('Y-m-d')

                 );

                $rflag=$this->PICO_model->insertdata('specification', $data);
               
			   if(!$rflag){
                    $this->logger->write_logmessage("insert","Trying to add intender specification", "intender specification is not added ".$pc_id);
                    $this->logger->write_dblogmessage("insert","Trying to add intender specification", "intender specification is not added ".$pc_id);
                    $this->session->set_flashdata('err_message','Error in adding purchase committee'  , 'error');
                    redirect('iprocess/specification');
                }
                else
                {
					
               $id=$rflag; 
                       $desired_dir = './uploads/PICO/specification/';
                           if(is_dir($desired_dir)==false){
                           mkdir("$desired_dir",0777);
                                                          }
                       $desired_dir1 = './uploads/PICO/specification/'.$id.'/';
                           if(is_dir($desired_dir1)==false){
                           mkdir("$desired_dir1",0777);
                                                           }
                     
                       $target_dir = $desired_dir1;    // path computer/opt/lampp/htdocs/brihCI/uploads/supplier/id folder

			              $target_file1 = $target_dir . basename($_FILES["description_upload_filename"]["name"]);
			              $target_file2 = $target_dir . basename($_FILES["terms_condition_filename"]["name"]);
			             
			              $uploadOk = 1;
			              
			              $imageFileType1 = strtolower(pathinfo($target_file1,PATHINFO_EXTENSION));
			              $imageFileType2 = strtolower(pathinfo($target_file2,PATHINFO_EXTENSION));
			             
                       //name change
                       		   
		   
			
			 if ($_FILES["description_upload_filename"]["size"] > 500000 && $_FILES["terms_condition_filename"]["size"] > 500000  ) //5mb
		    { 
  	       $this->session->set_flashdata("success", "Sorry, your file is too large (must be below 500 kb ).");
  	       $this->PICO_model->deleterow('specification','id', $id);
  	 	    $this->load->view('iprocess/specification');
          $uploadOk = 0;
          return;}
          
          if($imageFileType1 != "jpg" && $imageFileType1 != "pdf" && $imageFileType2 != "jpg" && $imageFileType2 != "pdf" ) 
		    {
  			 $this->session->set_flashdata("success",  "Sorry, only JPG & pdf files are allowed (check your files format).");
  			 $this->PICO_model->deleterow('specification','id', $id);
  			 $this->load->view('iprocess/specification');
          $uploadOk = 0;
 			 return;
			 }
          
          if ($uploadOk == 0)
		    {
          $this->session->set_flashdata("success", "Sorry, your file was not uploaded.");
          $this->PICO_model->deleterow('specification','id', $id);
          $this->load->view('iprocess/specification');
          return;
          }
          else // if everything is ok, try to upload file
				{
          	
                      	
          	              $name1 = $target_dir .'description.'.$imageFileType1;
			              $name2 = $target_dir .'t and c.'.$imageFileType2 ;
						  
						   if (move_uploaded_file($_FILES["description_upload_filename"]["tmp_name"], $name1) && move_uploaded_file($_FILES["terms_condition_filename"]["tmp_name"], $name2)  )
						   {
						  
					/*$this->logger->write_logmessage("insert","Add intender specification", "intender specification".$_POST['id']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add intender specification", "intender specification".$_POST['id']."added  successfully...");
                    $this->session->set_flashdata("success", "Intender specification Added successfully...");
                   */

						  $this->session->set_flashdata("success", "The files has been uploaded....<br>");
						  $this->logger->write_logmessage("insert","Add specification", "specification".$_POST['name']." added  successfully...");
						  $this->logger->write_dblogmessage("insert","Add specification", "specification".$_POST['name']."added  successfully...");
						  $this->session->set_flashdata("success", "Specification and file ". basename( $_FILES["description_upload_filename"]["name1"]). "Added Successfully ".$mailmsg);
        
				   redirect("iprocess/displayspecification");
				   return;}
						  else 
						  {
						  $this->session->set_flashdata("success", "Sorry, there was an error uploading your file.");
						  $this->PICO_model->deleterow('specification','id', $id);
						  $this->load->view('iprocess/displayspecification');
						  return;
						  }
                }
				}
          
        }
	}}
	
	public function displayspecification()
	{
		$data['specs'] = $this->PICO_model->get_list('specification');
		$this->logger->write_logmessage("view"," View specification", "Specification details...");
        $this->logger->write_dblogmessage("view"," View specification", "Specification details...");
		$this->load->view('iprocess/displayspecification',$data);
	}
	
}