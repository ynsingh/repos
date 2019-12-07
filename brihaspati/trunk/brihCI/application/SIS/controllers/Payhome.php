<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Payhome.php
 * @author Nagendra Kumar Singh
 * modified by Manorama Pal 06june2017  
 */
class Payhome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
	$this->load->model("university_model", "universitym");
	$this->load->model("common_model", "commodel");
	$this->load->model("SIS_model", "sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
	/* set user role in session, Role id 1 is for Administrator */
	$data = [ 'id_role' => 14 ];
	$this->session->set_userdata($data);
	// get the values of program and seat
	$selectfield = 'prg_scid,prg_category,prg_name,prg_branch,prg_seat';
	$this->prgseat=$this->commodel->get_listmore('program',$selectfield);
	// get the values of university 
	$this->result = $this->universitym->get_udetails();
	$contcode=$this->result->org_countrycode;
	$this->contryname = $this->universitym->get_countryname($contcode);
	//get values of fees record
        $this->load->view('payhome');
    }
 
    public function logout() {
	$data = ['id_user'=> '', 'id'=> '','id_role'=> '','username'=> '','id_dept' =>''];
        $this->session->unset_userdata($data);
        $this->session->sess_destroy();
        redirect('welcome');
    }
}
