<?php

/* 
 * @name Upl.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * 
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Upl extends CI_Controller
{
    function __construct() {
        parent::__construct();
	$this->load->model("common_model"); 
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
        
        $this->uploadlogo();
        
    }
    // This function is used for upload logo
    public function uploadlogo(){
	if(isset($_POST['uploadlogo'])) {
	$config = array(
		'upload_path' => "./uploads/logo/",
		'allowed_types' => "gif|jpg|png|jpeg|pdf",
		'overwrite' => TRUE,
		'max_size' => "2048000", // Can be set to particular file size , here it is 2 MB(2048 Kb)
		'max_height' => "768",
		'max_width' => "1024"
	);
	$this->load->library('upload', $config);

	// for clearing the previous sucess/error flashdata
	$array_items = array('success' => '', 'error' => '');
	$this->session->set_flashdata($array_items);


	if($this->upload->do_upload())
	{
		$data = array('upload_data' => $this->upload->data());
		$this->logger->write_logmessage("update","logo updated", "logo updated sucessfully");
		$this->logger->write_dblogmessage("update","logo updated", "logo updated sucessfully");
		$this->session->set_flashdata('success', 'Logo Successfully Updated.');
		//$this->load->view('upl/uploadlogo',$data);
	}
	else
	{
		$error =  array('error' => $this->upload->display_errors());
		foreach ($error as $item => $value):
			$ferror = $item .":". $value;
		endforeach;
		$this->logger->write_logmessage("update","logo update error", $ferror);
		$this->logger->write_dblogmessage("update","logo update error", $ferror);
		$this->session->set_flashdata('error', $ferror);
		//$this->load->view('upl/uploadlogo', $error);
	}
	}
	$this->load->view('upl/uploadlogo');	
    }
    // This function is used for upload student list in the system
    public function uploadstulist(){
    	$config = array(
		'upload_path' => "./uploads/tmp/",
		'allowed_types' => "txt",
		'overwrite' => TRUE,
		'max_size' => "4048000" // Can be set to particular file size , here it is 4 MB(4096 Kb)
	);
	$this->load->library('upload', $config);
	
    }

  
}
