<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class contact extends CI_Controller {

    public function index() {
        $this->load->view('contact');
        $this->load->helper('url');
}

    }

?>
