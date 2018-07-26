<?php
/******************************************************
* @name Admin.php(controller)    		      	  *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Admin extends CI_Controller {
	public function __construct(){
		parent::__construct();
		
		$this->load->model('Common_model',"commodel");
		
		
	}

	public function index()
	{
		
		$this->load->view('admin/admin_login');
	}
}
