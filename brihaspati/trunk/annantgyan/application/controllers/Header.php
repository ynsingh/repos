<?php
/******************************************************
* @name Header.php(controller)    		      	  	  *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/
defined('BASEPATH') OR exit('No direct script access allowed');

class Header extends CI_Controller {

	/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->load->model('Common_model',"commodel");

		$this->load->helper(array('url','form'));
		
	}
/******************************************************************************/	
	public function about()
	{
		$this->load->view('about');
	}

	public function vission()
	{
		$this->load->view('vission');
	}

	public function mission()
	{
		$this->load->view('mission');
	}

	public function contactus()
	{

		$this->load->view('contactus');
	}

	public function ongoingworkshop()
	{
		$data['course_data'] = $this->commodel->get_list('courseannouncement');
		$this->load->view('ongoing',$data);
	}

	public function signin()
	{ 
		$this->load->view('signin');
	}

	public function signup()
	{ 
		$this->load->view('signup');
	}
	public function forgotpass()
	{ 
		$this->load->view('forgotpass');
	}
	public function foundermsg(){
		$this->load->view('founder_msg');
	}
	public function crashcourse(){
		$this->load->view('cert_crashcrouse');
	}
	public function certworkshop(){
		$this->load->view('cert_workshop');
	}
	public function certfuncourse(){
		$this->load->view('cert_funcourse');
	}
	public function certsubmaterial(){
		$this->load->view('cert_submaterial');
	}
	public function inno_basicproject(){
		$this->load->view('inno_basicproject');
	}
	public function inno_advanproject(){
		$this->load->view('inno_advaproject');
	}
	public function inno_graduproject(){
		$this->load->view('inno_underproject');
	}
	public function inno_postgrproject(){
		$this->load->view('inno_postgraduate');
	}
	public function inno_ruralurban(){
		$this->load->view('inno_ruralurban');
	}
	public function skillstressmgt(){
		$this->load->view('skill_streemgt');
	}
	public function skillpubicspeaker(){
		$this->load->view('skill_publicspe');
	}
	public function skilleffectivecomm(){
		$this->load->view('skill_effectivecomm');
	}
	public function skillinterview(){
		$this->load->view('skill_interviewprep');
	}
	public function skillreport(){
		$this->load->view('skill_reportwrite');
	}
}
