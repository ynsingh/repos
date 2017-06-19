<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studenthome.php
 * @author Nagendra Kumar Singh
 */
class Studenthome extends CI_Controller
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
	    $suid=$this->session->userdata('id_user');

	    //set the student role in session
	    $data = [ 'id_role' => 3 ];
	    $this->session->set_userdata($data);
	    //get the student masterid
	    $this->result;
	   // if(!empty($this->result)) {
	//	    $smid=$this->result->sm_id;
		    // get the name of dept
	    	//get the value of current semester and academic year

	    	//check the registration in current academic session with semester
	    //if not then ask for the semester registeration
	    //if yes then open registration form
	    //after filling form redierect to fees payment
	    //after payment
	    //check the student program table for subject for current academic year and semester
	    //if subject exist
	    //go to dash board
	    //else go to subject selection page
	    //select the subject and paper
	    //update the student program table
	    //send mail to student
	    //go to dashboard
	  //  }else{
	//	$this->session->set_flashdata('flash_data', 'You do not have student role in this system!');
          //      redirect('welcome');
	   // }

//	    $this->result = $this->universitym->get_udetails();

//	$contcode=$this->result->org_countrycode;
//	$this->contryname = $this->universitym->get_countryname($contcode);
        $this->load->view('student/studenthome');
    }
 
}
