<?php

defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Rolehome.php
 * @author Manorama Pal(palseema30@gmail.com)
 */
class Rolehome extends CI_Controller {
    
    function __construct() {
        parent::__construct();
        $this->load->model("Common_model", "commodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
   
    public function index() {
        /*getting roles of logged user by using common model*/
	$this->roles=$this->commodel->get_listspficarry('user_role_type','roleid','userid',$this->session->userdata('id_user'));
        $this->load->view('rolehome');
    }
       
}
