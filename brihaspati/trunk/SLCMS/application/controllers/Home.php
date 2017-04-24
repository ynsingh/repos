<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Home.php
 * @author Nagendra Kumar Singh
 */
class Home extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
 	$this->load->model("university_model", "universitym");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
	$this->result = $this->universitym->get_udetails();
	$contcode=$this->result->org_countrycode;
	$this->contryname = $this->universitym->get_countryname($contcode);
        $this->load->view('home');
    }
 
    public function logout() {
        $data = ['id_user', 'id'];
        $this->session->unset_userdata($data);
 
        redirect('welcome');
    }
}
