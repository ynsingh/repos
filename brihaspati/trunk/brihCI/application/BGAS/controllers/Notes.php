<?php
class Notes extends CI_Controller {

function __construct() {
        parent::__construct();
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }

	function Notes()
        {
                parent::Controller();
                /* Check access */
                if ( ! check_access('view reports'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('');
                        return;
                }

                return;
        }


	function index()
        {
                $this->template->set('page_title', 'Notes to Accounts');
                $this->template->load('template', 'report/notesToAccount');
                return;
        }

	function display_notes(){
		$this->template->set('page_title', 'Notes to Accounts');
                $this->template->load('template', 'notes');
                return;
	}
}
