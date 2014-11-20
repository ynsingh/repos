<?php

class Log extends Controller {
	function index()
	{
		$this->load->helper('text');
		$this->template->set('page_title', 'Logs');
		//$this->template->set('nav_links', array('log/clear' => 'Clear Log'));
		$this->template->load('template', 'log/index');

		/* Check access */
		if ( ! check_access('view log'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}
		return;
	}

	function clear()
	{
		/* Check access */
		if ( ! check_access('clear log'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('log');
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('log');
			return;
		}

		if ($this->db->truncate('logs'))
		{
			$this->messages->add('Log cleared.', 'success');
			redirect('log');
		} else {
			$this->messages->add('Error clearing Log.', 'error');
			redirect('log');
		}
		return;
	}
	
	function LogReport($name)
        {
                $this->load->helper('text');
                if($name == 'COA')
                        $this->template->set('page_title', 'Chart Of Account Logs');
                if($name == 'BugtLog')
                        $this->template->set('page_title', 'Budget Logs');
                if($name == 'TrnsLog')
                        $this->template->set('page_title', 'Transaction Logs');
                if($name == 'OtherLog')
                        $this->template->set('page_title', 'Other Logs');
		$data['name']=$name;
		// code for search 
                $data['search'] = '';
                $data['search_by'] = array(
                        "Select" => "Select",
                        "host_ip" => "Host IP",
                        "user"=> "User",
                        "message_title"=> "Message",
                        "date"=> "Date",
                );
                $data['search_by_active'] = '';

                $data['text'] = array(
                        'name' => 'text',
                        'id' => 'text',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
                if ($_POST)
                {
                        $data['search_by_active']['value'] = $this->input->post('search_by', TRUE);
                        $data['text']['value'] = $this->input->post('text', TRUE);
                }
                /* Form validation */

                $this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
                $this->form_validation->set_rules('text', 'Text', 'trim|required');
                /* Validating form */

                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'log/LogReport', $data);
                        return;
                }
                else
                {
                        $data_search_by = $this->input->post('search_by', TRUE);
                        $data_text = $this->input->post('text', TRUE);
			redirect('log/LogReport/'.$name);
                }
		echo "data_search_by=======in controller--$data_search_by";
		if($data_search_by == "host_ip") {
                       $search_text = $data_text;
 		       if(! ctype_digit($data_text)) {
                	        $this->messages->add('Please enter a number.', 'error');
        	                redirect('log/LogReport/'.$name);
	                }
                }
			
	        if(gmp_sign($data_text) == -1) {
        		$this->messages->add('Text should be a positive value.', 'error');
                	redirect('log/LogReport/'.$name);
			return;
                }		
                /*if($data_search_by == "host_ip")
                {
			$search_text = $data_text;
                        if(! ctype_digit($data_text)) {
	                        $this->messages->add('Please enter a number.', 'error');
        	                        redirect('entry/' . $data['entry_path']);
                        }
                        $text = '';
                        if($data_search_by == "host_ip")
                        {
                                $text = 'Host IP';
                        }
                        if(! is_numeric($data_text)) {
                                $this->messages->add($text . ' should be numeric.', 'error');
                                redirect('log/LogReport/'.$name);
				return;
                        }
		}*/
                $data['search'] = $data_search_by;
                $this->template->load('template', 'log/LogReport', $data);


                //$this->template->load('template', 'log/LogReport', $data);

		/* $this->load->library('pagination');

                $page_count = (int)$this->uri->segment(4);
                $page_count = $this->input->xss_clean($page_count);
                if ( ! $page_count)
                       $page_count = "0";
                $config['base_url'] = site_url('log/LogReport/'.$name);
                $pagination_counter = $this->config->item('row_count');
                $config['uri_segment'] = 4;
                $config['num_links'] = 10;
                $config['per_page'] = $pagination_counter;
	//	$config['total_rows'] = (int)$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('date >=', $from_date)->where('date <=', $to_date)->count_all_results();
                $config['full_tag_open'] = '<ul id="pagination-flickr">';
                $config['full_close_open'] = '</ul>';
                $config['num_tag_open'] = '<li>';
                $config['num_tag_close'] = '</li>';
                $config['cur_tag_open'] = '<li class="active">';
                $config['cur_tag_close'] = '</li>';
                $config['next_link'] = 'Next &#187;';
                $config['next_tag_open'] = '<li class="next">';
                $config['next_tag_close'] = '</li>';
                $config['prev_link'] = '&#171; Previous';
                $config['prev_tag_open'] = '<li class="previous">';
                $config['prev_tag_close'] = '</li>';
                $config['first_link'] = 'First';
                $config['first_tag_open'] = '<li class="first">';
                $config['first_tag_close'] = '</li>';
                $config['last_link'] = 'Last';
                $config['last_tag_open'] = '<li class="last">';
                $config['last_tag_close'] = '</li>';
               	// $data["links"] = $this->pagination->create_links();
		$this->pagination->initialize($config);*/
                //$data["bill_data"] = $this->Payment_model->bill_uploadvalues($pagination_counter,$page_count);
		//$config['total_rows'] = $this->Payment_model->record_count();

                //$this->template->load('template', 'log/LogReport', $data);
                /* Check access */
                if ( ! check_access('view log'))
                {
                        $this->messages->add('Permission denied.', 'error');
                       // redirect('');
                       // return;
                }
                return;

        }
}

/* End of file log.php */
/* Location: ./system/application/controllers/log.php */
