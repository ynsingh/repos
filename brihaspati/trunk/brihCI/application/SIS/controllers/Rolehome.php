<?php

defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Rolehome.php
 * @author Manorama Pal(palseema30@gmail.com)
 */
class Rolehome extends CI_Controller {
    
    function __construct() {
        parent::__construct();
        $this->load->model("SIS_model", "sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
   
    public function index() {
        /*getting roles of logged user by using common model*/
	//$this->roles=$this->sismodel->get_listspficarry('user_role_type','roleid','userid',$this->session->userdata('id_user'));
//	get_listspficemore($tbname,$selectfield,$data)
	$whdata = array('userid' => $this->session->userdata('id_user'));
	$this->roles=$this->sismodel->get_listspficemore('user_role_type','roleid',$whdata);
        $this->load->view('rolehome');
    }
       
}
