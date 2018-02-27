<?php

class Aggregator extends CI_Controller {
    function __construct() {
        parent::__construct();
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }

	function Aggregator()
	{
		parent::Controller();

		/* Check access */

		if ( ! check_access('administer'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}


	
	function index()
	{
		$this->template->set('page_title', 'Administer ');

		/* Check status report */
		$this->load->library('statuscheck');
		//$statuscheck = new Statuscheck(); 
		//$statuscheck->check_permissions();
		$this->messages->add('Administrator or Manager can view Aggregate reports and Aggregate budgets');

		$this->template->load('admin_template', 'admin/aggregator/aggregator');
		return;
	}
	function getallaccounts()
	{
	        $data['accounts'] = array('(All Accounts)' => '(All Accounts)');

                $db1=$this->load->database('login', TRUE);
                $db1->select('dblable')->from('bgasAccData');
                $query = $db1->get();
                if ($query->num_rows() < 1)
                {
                        $this->messages->add('No Account exists.', 'error');
                        $this->template->load('admin_template', 'admin/aggregator/', $data);
                        return;
                }
                else{
                        foreach($query->result() as $row){
                                $data['accounts'][$row -> dblable] = $row -> dblable;
                        }
                }
		$data_accounts = $this->input->post('accounts', TRUE);

/*		if ( ! $data_accounts)
                {
                                $this->messages->add('Please select account.', 'error');
                                $this->template->load('admin_template', 'admin/user/edit', $data);
                                return;
                } else {
                    /*            if (in_array('(All Accounts)', $data_accounts))
                                {
                                        $data_accounts_string = '*';
                                } else {
                                        /* Filtering out bogus accounts */
    /*                                    $data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
                                        $data_accounts_string = implode(",", $data_accounts_valid);*/
//                                }
		    
//			$this->template->load('admin_template', 'admin/aggregator/aggregatebalancesheet',$data);
			
 //                }
		

		//$this->messages->add("");
		$this->template->load('admin_template', 'admin/aggregator/getallaccounts',$data);	

	}
	function aggregatebalancesheet()
	{
	//echo"hsdj";
		/*$data['accounts'] = array('(All Accounts)' => '(All Accounts)');

                $db1=$this->load->database('login', TRUE);
                $db1->select('dblable')->from('bgasAccData');
                $query = $db1->get();
                if ($query->num_rows() < 1)
                {
                        $this->messages->add('No Account exists.', 'error');
                        $this->template->load('admin_template', 'admin/aggregator/', $data);
                        return;
                }
                else{
                        foreach($query->result() as $row){
                                $data['accounts'][$row -> dblable] = $row -> dblable;
                        }
                }
                $data_accounts = $this->input->post('accounts', TRUE);
		$data['values']=$data_accounts;
	//	echo"---->$data_accounts ";
		//print_r($data_accounts);*/

	$this->template->load('admin_template', 'admin/aggregator/aggregatebalancesheet');
	}
}

/* End of file aggregator.php */
/* Location: ./system/application/controllers/admin/welcome.php */
