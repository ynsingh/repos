<?php
/******************************************************
* @name Discussion.php(controller)    		      *
* @author Nagendra Kumar Singh(nksinghiitk@gmail.com) *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Discussion extends CI_Controller {

	/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->model('Common_model',"commodel");
		//$this->load->library('form_validation');
		//$this->load->library('session');
//		$this->lang->load('en_admin_lang', 'english');
		$this->load->library('curl');
		$this->load->helper(array('url','form'));
		$this->load->helper('string');
		if ($this->session->userdata('su_id') == FALSE) {
                        redirect('login/signin');
                }
	
	}

	public function index() {
		//if ($this->session->userdata('logged_in') == FALSE) {
		if ($this->session->userdata('su_id') == FALSE) {
			redirect('login/signin');
        	} 
        	redirect('discussion/dashboard');
		    return;
    	}

	public function dashboard() {
       // if ($this->session->userdata('logged_in') == FALSE) {
        	if ($this->session->userdata('su_id') == FALSE) {
			redirect('login/signin');
		} 
//		"SELECT * FROM 'comments', 'users'              WHERE 'comments'.'usr_id' = 'users'.'usr_id'              AND 'cm_is_active' = '0' "
		$whdata=array('cm_is_active' => 0);
		$whorder='';
		$page_data['comment_query'] = $this->commodel->get_orderlistspficemore('comments,sign_up','*',$whdata,$whorder);

		//$whdata="'discussions.ds_usrid' = 'sign_up.su_id' AND 'ds_is_active' = '0'";
		$whdata = array('ds_is_active' => 0);
		$whorder='';
		$page_data['discussion_query'] = $this->commodel->get_orderlistspficemore('discussions','*',$whdata,$whorder);

	        //$this->load->view('template/login_header');
//        	$this->load->view('nav/top_nav');
	        $this->load->view('discussion/dashboard',$page_data);
        	$this->load->view('template/footer');
    	}


	public function viewDiscussion() {
        	if ($this->uri->segment(3)) {
	            $filter = $this->uri->segment(4);
        	    $direction = $this->uri->segment(5);
	            $page_data['dir'] = $this->uri->segment(5);
        	} else {
	            $filter = null;
        	    $direction = null;
	            $page_data['dir'] = 'ASC';
        	}

		if ($filter != null) {
  			if ($filter == 'age') {
    				$filter = 'ds_created_at';
    				switch ($direction) {
      					case 'ASC':
        					$dir = 'ASC';
        					break;
      					case 'DESC':
        					$dir = 'DESC';
        					break;
      					default:
        					$dir = 'ASC';
    				}
  			}
		} else {
  			$dir = 'ASC';
		}

		 $whdata = array('ds_is_active' => 1);
        	 $whorder='ds_created_at '.$dir;
	         $page_data['query'] = $this->commodel->get_orderlistspficemore('discussions','*',$whdata,$whorder);
       		// $page_data['query'] = $this->Discussions_model->fetch_discussions($filter,$direction);
	
	       // $this->load->view('common/header');
		 //  $this->load->view('nav/top_nav');
	        $this->load->view('discussion/viewDiscussion', $page_data);
       		$this->load->view('template/footer');
    	}


    public function newDiscussion() {
//        $this->form_validation->set_rules('usr_name', $this->lang->line('discussion_usr_name'), 'required|min_length[1]|max_length[255]');
  //      $this->form_validation->set_rules('usr_email', $this->lang->line('discussion_usr_email'), 'required|min_length[1]|max_length[255]');
        $this->form_validation->set_rules('ds_title', 'Discussion Title', 'required|min_length[1]|max_length[255]');
        $this->form_validation->set_rules('ds_body', 'Discussion Description', 'required|min_length[1]|max_length[5000]');

        if ($this->form_validation->run() == FALSE) {
        //    $this->load->view('common/header');
          //  $this->load->view('nav/top_nav');
            $this->load->view('discussion/newDiscussion');
            $this->load->view('template/footer');
	} else {
		//get the user id and course id
		$userid=$this->session->userdata['su_id'];
		$crsid=$this->session->userdata['crs_id'];
		date_default_timezone_set("Asia/Calcutta");
	        $cdate = date("Y-m-d H:i:s");
            $data = array('ds_crsid' => $crsid,
                          'ds_usrid' => $userid,
                          'ds_title' => $this->input->post('ds_title'),
                          'ds_body' =>  $this->input->post('ds_body'),
                          'ds_created_at' =>  $cdate,
                          'ds_is_active' => 1 
		  );

            if ($ds_id = $this->commodel->insertrec('discussions',$data)) {
                redirect('discussion/index/'.$ds_id);
		    return;
            } else {
		$this->session->set_flashdata('error',"There is a problem to add discussion ");
                redirect('discussion/index/'.$ds_id);
		    return;
                // error
                // load view and flash sess error
            }
        }
    }

    public function dflag() {
	    $dsid = $this->uri->segment(3);
	    $udata=array('ds_is_active' => '0');
	    $uflag=$this->commodel->updaterec('discussions',$udata,"ds_id",$dsid);
            if ($uflag) {
		    redirect('discussion/viewDiscussion');
		    return;
	    } else {
		$this->session->set_flashdata('error',"There is a problem to update discussion flag");
            	redirect('discussion/viewDiscussion');
		    return;
            // error
           // load view and flash sess error
        }
    }

    	public function update_item() {
       // if ($this->session->userdata('logged_in') == FALSE) {
        	if ($this->session->userdata('su_name') != 'admin') {
			redirect('login/signin');
        	} else{

        		if ($this->uri->segment(4) == 'allow') {
            			$is_active = 1;
	        	} else {
        	    		$is_active = 0;
			}
			if ($is_active == 1) {
				$dsid=$this->uri->segment(5);
				if ($this->uri->segment(3) == 'ds') {
					$udata=array('ds_is_active' => $is_active);
            				$result=$this->commodel->updaterec('discussions',$udata,"ds_id",$dsid);
	//            			$result = $this->Admin_model->update_discussions($is_active, $this->uri->segment(5));
		        	} else {
					//$result = $this->Admin_model->update_comments($is_active, $this->uri->segment(5));
					$udata=array('cm_is_active' => $is_active);
		         		$uflag=$this->commodel->updaterec('comments',$udata,"cm_id",$dsid);
        			}
			}
        	redirect('discussion/dashboard');
		return;
		}
    	}

    	public function viewComment() {
	        if ($this->input->post()) {
        	    $ds_id = $this->input->post('ds_id');
	        } else {
        	    $ds_id = $this->uri->segment(3);
        	}
//		$query = "SELECT * FROM 'comments', 'discussions', 'users'               WHERE 'discussions'.'ds_id' = ?               AND 'comments'.'ds_id' = 'discussions'.'ds_id'                AND 'comments'.'usr_id' = 'users'.'usr_id'                AND 'comments'.'cm_is_active' = '1'                ORDER BY 'comments'.'cm_created_at' DESC " ;

		$whdata=array('cm_dsid'=>$ds_id, 'cm_is_active' => 1);
                $whorder='cm_created_at DESC';
                $page_data['comment_query'] = $this->commodel->get_orderlistspficemore('comments','*',$whdata,$whorder);

                //$whdata="SELECT * FROM 'discussions', 'users' WHERE 'ds_id' = ? AND 'discussions'.'usr_id' = 'users'.'usr_id'";
               $whdata = array('ds_id' =>$ds_id );
               $whorder='';
               $page_data['discussion_query'] = $this->commodel->get_orderlistspficemore('discussions','*',$whdata,$whorder);


//        $page_data['discussion_query'] = $this->Discussions_model->fetch_discussion($ds_id);
//        $page_data['comment_query'] = $this->Comments_model->fetch_comments($ds_id);
        $page_data['ds_id'] = $ds_id;

        $this->form_validation->set_rules('ds_id', $this->lang->line('comments_comment_hidden_id'), 'required|min_length[1]|max_length[11]');
  //      $this->form_validation->set_rules('comment_name', $this->lang->line('comments_comment_name'), 'required|min_length[1]|max_length[25]');
//        $this->form_validation->set_rules('comment_email', $this->lang->line('comments_comment_email'), 'required|min_length[1]|max_length[255]');
        $this->form_validation->set_rules('comment_body', $this->lang->line('comments_comment_body'), 'required|min_length[1]|max_length[5000]');

        if ($this->form_validation->run() == FALSE) {
           // $this->load->view('common/header');
           // $this->load->view('nav/top_nav');
            $this->load->view('discussion/viewComment', $page_data);
            $this->load->view('template/footer');
	} else {
		 $userid=$this->session->userdata['su_id'];
                $crsid=$this->session->userdata['crs_id'];
                date_default_timezone_set("Asia/Calcutta");
                $cdate = date("Y-m-d H:i:s");
		$data = array(
                          'cm_dsid' =>  $this->input->post('ds_id'),
                          'cm_tpid' =>  0,
			  'cm_body' => $this->input->post('comment_body'),
                          'cm_created_at' => $cdate,
                          'cm_usrid' => $userid,
                          'cm_is_active' => 1,
                          );

	//	if ($this->Comments_model->new_comment($data)) {
	if($ds_id = $this->commodel->insertrec('comments',$data)){
                redirect('discussion/viewComment/'.$ds_id);
		    return;
            } else {
		$this->session->set_flashdata('error',"There is a problem to add comments ");
                redirect('discussion/viewComment/'.$ds_id);
		    return;
                // error
                // load view and flash sess error
            }
        }
    }

    public function cflag() {
	    $cmid = $this->uri->segment(4);
	    $udata=array('cm_is_active' => '0');
            $uflag=$this->commodel->updaterec('comments',$udata,"cm_id",$cmid);
	    if($uflag) {
            	redirect('discussion/viewComment/'.$this->uri->segment(3));
		    return;
	    } else {
		$this->session->set_flashdata('error',"There is a problem to update comments flag");
                redirect('discussion/dashboard');
		    return;
            // error
            // load view and flash sess error
        }
    }
}
