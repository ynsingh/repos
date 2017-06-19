<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Home.php
 * @author Nagendra Kumar Singh
 * modified by Manorama Pal 06june2017  
 */
class Home extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
	$this->load->model("university_model", "universitym");
	$this->load->model("common_model", "commodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
	/* set user role in session, Role id 1 is for Administrator */
	$data = [ 'id_role' => 1 ];
	$this->session->set_userdata($data);
	//	$whdata= [];
	$selectfield = 'prg_scid,prg_category,prg_name,prg_seat';
	// get the values of program and seat
	$this->prgseat=$this->commodel->get_listmore('program',$selectfield);
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
