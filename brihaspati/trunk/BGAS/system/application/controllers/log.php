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
			"date"=> "Date",
                        "host_ip" => "Host IP",
                        "user"=> "User",
                        "message_title"=> "Message",
                );
                $data['search_by_active'] = '';

                $data['text'] = array(
                        'name' => 'text',
                        'id' => 'text',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

                /* Form validation */
                $this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
                $this->form_validation->set_rules('text', 'Text', 'trim|required');

		if ($_POST)
                {
                        $data['search_by_active']['value'] = $this->input->post('search_by', TRUE);
                        $data['text']['value'] = $this->input->post('text', TRUE);
                }

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
			//redirect('log/LogReport/'.$name);
                }
		//if searching host ip....
		$i=0;
		if($data_search_by == "host_ip") {
                       	$search_text = $data_text;
			$str_val=(explode(".",$search_text));
			$arr_len=count($str_val);
			for($i=0; $i< $arr_len-1; $i++){
				if($str_val[$i] != NULL){
					if(! ctype_digit($str_val[$i])) {
                                	$this->messages->add('Please enter a numeric value .', 'error');
                                	redirect('log/LogReport/'.$name);
                                	return;
                        		}
				}
			}

                }//end
	        /*if(gmp_sign($data_text) == -1) {
        		$this->messages->add('Text should be a positive value.', 'error');
                	redirect('log/LogReport/'.$name);
			return;
                } */
		//if searching date....	
		if($data_search_by == "date")
		{
			$search_text = $data_text;
			// if date and update date is single digit
			if(ctype_digit($data_text)) {
				 $this->messages->add('Please enter date in dd mm yy format.', 'error');
                                 redirect('log/LogReport/'.$name);

			}
			else {
				$date=explode(' ', $data_text);
				// if date and update format is dd mm yy only
				if(count($date)>1) {
				// if date and update date contain two digit
					if(count($date) == '2') {
						// if month is character
						if(ctype_alpha($date['1'])) {
							if(strtotime($date['1'])) {
								$month = $date['1'];
								$x = date('m', strtotime($month));
								$data_text = $x . "-" . $date[0];
								$field = $data_search_by . '      ' . 'LIKE';
							}
							else {
								$this->messages->add('Invalid Month.', 'error');
								redirect('log/LogReport/'.$name);
							}
						}
						// if month is digit
						else if(ctype_digit($date['1'])) {
						// if month is valid or not
							if("1" <= $date['1'] && $date['1'] <= "12") {
								$field = $data_search_by . '      ' . 'LIKE';
								$data_text = $date[1]. "-" . $date[0];
							}
							else {
								$this->messages->add('Invalid Month.', 'error');
								redirect('log/LogReport/'.$name);
							}
						}
						// if date is invalid
						else {
							$this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
							redirect('log/LogReport/'.$name);
						}
					}
					// if date contain three digit
					if(count($date) == '3') {
						$date0 = $date['0'];
						$x = $date['1'];
						$date2 = $date['2'];
						//it converts month name to digit
						if(ctype_alpha($date['1'])) {
							$month = $date['1'];
							$x = date('m', strtotime($month));
							$data_text = $date[2]. "-" . $x . "-" . $date[0];
						}
						$data_text = $date0. "-" . $x . "-" . $date2;
						//check for date is valid or not
						@$valid_date = checkdate($x,$date0,$date2);
						if($valid_date == 'true') {
							//check date is exist in financial year or not
							if($date2 == "2014" || $date2 == "2015") {
							$data_text = $date2."-". $x ."-".$date0;
						}
						else {
						$this->messages->add($data_text . ' does not exist in financial year.', 'error');
		//				redirect('log/LogReport/'.$name);
						}
					}
					else {
						$this->messages->add($data_text . ' is invalid date Please enter date in dd mm yyyy format.', 'error');
		//				redirect('log/LogReport/'.$name);
					}	
				}
			}
			else {
				$this->messages->add('Invalid date format. Please enter date in dd mm yyyy format.', 'error');
				redirect('log/LogReport/'.$name);							}
		}		
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
                $data['datetext'] = $data_text;
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
