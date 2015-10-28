<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class eguru extends CI_Controller {

    public function __construct() {
        parent::__construct();
        session_start();
	$this->load->helper('url');
    }

    public function index() {
        if (!isset($_SESSION['email']))
            $this->load->view('index');
        else {
            $email=$_SESSION['email'];
            $this->load->model('user');
          $data_dash =  $this->user->user_details($email);
          $this->load->view('index',array('data'=>$data_dash));
        }
    }

    public function about() {
        if (!isset($_SESSION['email']))
            $this->load->view('about');
        else {
            $email=$_SESSION['email'];
            $this->load->model('user');
          $data_dash =  $this->user->user_details($email);
          $this->load->view('about',array('data'=>$data_dash));
        }
    }

    public function contact() {
        if (!isset($_SESSION['email']))
            $this->load->view('contact');
        else {
            $email=$_SESSION['email'];
            $this->load->model('user');
          $data_dash =  $this->user->user_details($email);
          $this->load->view('contact',array('data'=>$data_dash));
        }
    }

 public function common_profile(){        
        $this->load->model('user');
        $user_type =$this->user->recognize_user();
        $this->load->view('switch_between_user',array('data'=>$user_type));
    }
    public function logout() {
        $this->load->view('logout');
    }

    public function forgot() {
        $this->load->view('forgot');
    }

    public function forgot_submit() {
        $email_password = $this->input->post();
        $this->load->model('user');
        $this->user->forgot_password_submit($email_password);
        header("Location:/eguru/forgot/");
    }

}

?>
