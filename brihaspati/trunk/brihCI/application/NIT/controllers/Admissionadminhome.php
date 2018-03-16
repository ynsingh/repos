<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Admissionadminhome.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Admissionadminhome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
        /* set role id in session*/
	$data = [ 'id_role' => 9 ];
        $this->session->set_userdata($data);
        redirect('report/admission_student');
    }
 
}

