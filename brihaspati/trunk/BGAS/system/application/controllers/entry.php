<?php

class Entry extends Controller {

	function Entry()
	{
		parent::Controller();
		$this->load->model('Entry_model');
		$this->load->model('Ledger_model');
		$this->load->model('Group_model');
		$this->load->model('Tag_model');
		$this->load->model('Budget_model');
		$this->load->library('GetParentlist');
		return;
	}

	function index()
	{
		redirect('entry/show/all');
		return;
	}

	function show($entry_type)
	{

		$this->load->model('Tag_model');
		//$this->messages->add('Test==>'.$entry_type);
		$data['tag_id'] = 0;
		$entry_type_id = 0;
		if ($entry_type == 'tag')
		{
			$tag_id = (int)$this->uri->segment(4);
			if ($tag_id < 1)
			{
				$this->messages->add('Invalid Tag.', 'error');
				redirect('entry/show/all');
				return;
			}
			$data['tag_id'] = $tag_id;
			$tag_name = $this->Tag_model->tag_name($tag_id);
			$this->template->set('page_title', 'Entries Tagged "' . $tag_name . '"');
		} else if ($entry_type == 'all') {
			$entry_type_id = 0;
			$this->template->set('page_title', 'All Entries');
			$this->template->set('nav_links', array('entry/printallentry/'=> 'PRINT ALL ENTRY'));
		} else {
			$entry_type_id = entry_type_name_to_id($entry_type);
			if ( ! $entry_type_id)
			{
				$this->messages->add('Invalid Entry type specified. Showing all Entries.', 'error');
				redirect('entry/show/all');
				return;
			} else {
				$current_entry_type = entry_type_info($entry_type_id);
				$this->template->set('page_title', $current_entry_type['name'] . ' Entries');
				$this->template->set('nav_links', array('entry/add/' . $current_entry_type['label'] => 'New ' . $current_entry_type['name'] . ' Entry', 'entry/printentry/' . $current_entry_type['label'] => 'Print ' . $current_entry_type['name'] . ' Entry'));

			}
		}
		$entry_q = NULL;


		/**
		 * Following code checks, whether 'Transit Income' account exists or not.
		 * And if does not exist, it creates one in Income head.
		 */
		$this->db->from('ledgers')->where('name =', 'Transit Income');
                $query_result = $this->db->get();

                if($query_result->num_rows() == 0)
                {
	                $this->db->from('groups');
                        $this->db->select('id');
                        $this->db->where('name =', 'Incomes');
                        $group = $this->db->get();
                        foreach($group->result() as $row){
         	               $group_id = $row->id;
                        }
                                        
			$num = $this->Ledger_model->get_numOfChild($group_id);
                        $l_code = $this->Group_model->get_group_code($group_id);

                        if($num == 0){
	                        $ledger_code = $l_code . '01';
        	        } else{
                                $ledger_code=$this->get_code($num, $l_code);
                        }

	                $i=0;
        
                        do{
    	                    if($i>0){
	                            $num = $num + 1;
                                    $ledger_code=$this->get_code($num, $l_code);
                            }
                            $this->db->from('groups');
                            $this->db->select('id')->where('code =',$ledger_code);
                            $group_q = $this->db->get();
                            $i++;
                        }while($group_q->num_rows()>0);
                      
                        /* Adding ledger 'Transit Income' in Income head */
                        $this->db->trans_start();
                        $insert_data = array(
         	               'code' => $ledger_code,
                               'name' => 'Transit Income',
                               'group_id' => $group_id,
                               'op_balance' => '0.00',
                               'op_balance_dc' => 'C',
                               'type' => '0',
                               'reconciliation' => '0',
                        );
                 
	                if ( ! $this->db->insert('ledgers', $insert_data)){
	                        $this->db->trans_rollback();
                                $this->messages->add('Error addding Ledger account - Transit Income in Income head.', 'error');
                                $this->logger->write_message("error", "Error adding Ledger account: Transit Income");
                         } else {
                                $this->db->trans_complete();
                                $this->messages->add('Added Ledger account - Transit Income.', 'success');
                                $this->logger->write_message("success", "Added Ledger account called Transit Income");
                         }
		}

		/* Pagination setup */
		$this->load->library('pagination');

		if ($entry_type == "tag")
			$page_count = (int)$this->uri->segment(5);
		else
			$page_count = (int)$this->uri->segment(4);

		$page_count = $this->input->xss_clean($page_count);
		if ( ! $page_count)
			$page_count = "0";

		/* Pagination configuration */
		if ($entry_type == 'tag')
		{
			$config['base_url'] = site_url('entry/show/tag' . $tag_id);
			$config['uri_segment'] = 5;
		} else if ($entry_type == 'all') {
			$config['base_url'] = site_url('entry/show/all');
			$config['uri_segment'] = 4;
		} else {
			$config['base_url'] = site_url('entry/show/' . $current_entry_type['label']);
			$config['uri_segment'] = 4;
		}
		$pagination_counter = $this->config->item('row_count');
		$config['num_links'] = 10;
		$config['per_page'] = $pagination_counter;
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

		if ($entry_type == "tag") {
			$this->db->from('entries')->where('tag_id', $tag_id)->order_by('date', 'desc')->order_by('number', 'desc')->limit($pagination_counter, $page_count);
			$entry_q = $this->db->get();
			$config['total_rows'] = $this->db->from('entries')->where('tag_id', $tag_id)->get()->num_rows();
		} else if ($entry_type_id > 0) {
			$this->db->from('entries')->order_by('date', 'desc')->order_by('id', 'desc')->limit($pagination_counter, $page_count);
			$entry_q = $this->db->get();
			$config['total_rows'] = $this->db->from('entries')->get()->num_rows();
			
		} else {
			$this->db->from('entries')->order_by('date', 'desc')->order_by('number', 'desc')->limit($pagination_counter, $page_count);
			$entry_q = $this->db->get();
			$config['total_rows'] = $this->db->count_all('entries');
		}

		/* Pagination initializing */
		$this->pagination->initialize($config);
		
		/* Show entry add actions */
		if ($this->session->userdata('entry_added_show_action'))
		{
			$entry_added_id_temp = $this->session->userdata('entry_added_id');
			$entry_added_type_id_temp = $this->session->userdata('entry_added_type_id');
			$entry_added_type_label_temp = $this->session->userdata('entry_added_type_label');
			$entry_added_type_name_temp = $this->session->userdata('entry_added_type_name');
			$entry_added_number_temp = $this->session->userdata('entry_added_number');
			$entry_added_message = 'Added ' . $entry_added_type_name_temp . ' Bill/Voucher number ' . full_entry_number($entry_added_type_id_temp, $entry_added_number_temp) . ".";
			$entry_added_message .= " You can [ ";
			$entry_added_message .= anchor('entry/view/' . $entry_added_type_label_temp . "/" . $entry_added_id_temp, 'View', array('class' => 'anchor-link-a')) . " | ";
//			$entry_added_message .= anchor('entry/edit/' . $entry_added_type_label_temp . "/" . $entry_added_id_temp, 'Edit', array('class' => 'anchor-link-a')) . " | ";
			$entry_added_message .= anchor_popup('entry/printpreview/' . $entry_added_type_label_temp . "/" . $entry_added_id_temp , 'Print', array('class' => 'anchor-link-a', 'width' => '600', 'height' => '600')) . " | ";
			$entry_added_message .= anchor_popup('entry/email/' . $entry_added_type_label_temp . "/" . $entry_added_id_temp, 'Email', array('class' => 'anchor-link-a', 'width' => '500', 'height' => '300')) . " | ";
			$entry_added_message .= anchor('entry/download/' . $entry_added_type_label_temp . "/" . $entry_added_id_temp, 'Download', array('class' => 'anchor-link-a'));
			$entry_added_message .= " ] it.";
			$this->messages->add($entry_added_message, 'success');
			$this->session->unset_userdata('entry_added_show_action');
			$this->session->unset_userdata('entry_added_id');
			$this->session->unset_userdata('entry_added_type_id');
			$this->session->unset_userdata('entry_added_type_label');
			$this->session->unset_userdata('entry_added_type_name');
			$this->session->unset_userdata('entry_added_number');
		}

		/* Show entry edit actions */
		if ($this->session->userdata('entry_updated_show_action'))
		{
			$entry_updated_id_temp = $this->session->userdata('entry_updated_id');
			$entry_updated_type_id_temp = $this->session->userdata('entry_updated_type_id');
			$entry_updated_type_label_temp = $this->session->userdata('entry_updated_type_label');
			$entry_updated_type_name_temp = $this->session->userdata('entry_updated_type_name');
			$entry_updated_number_temp = $this->session->userdata('entry_updated_number');
			$entry_updated_message = 'Updated ' . $entry_updated_type_name_temp . ' Bill/Voucher number ' . full_entry_number($entry_updated_type_id_temp, $entry_updated_number_temp) . ".";
			$entry_updated_message .= " You can [ ";
			$entry_updated_message .= anchor('entry/view/' . $entry_updated_type_label_temp . "/" . $entry_updated_id_temp, 'View', array('class' => 'anchor-link-a')) . " | ";
		//	$entry_updated_message .= anchor('entry/edit/' . $entry_updated_type_label_temp . "/" . $entry_updated_id_temp, 'Edit', array('class' => 'anchor-link-a')) . " | ";
			$entry_updated_message .= anchor_popup('entry/printpreview/' . $entry_updated_type_label_temp . "/" . $entry_updated_id_temp , 'Print', array('class' => 'anchor-link-a', 'width' => '600', 'height' => '600')) . " | ";
			$entry_updated_message .= anchor_popup('entry/email/' . $entry_updated_type_label_temp . "/" . $entry_updated_id_temp, 'Email', array('class' => 'anchor-link-a', 'width' => '500', 'height' => '300')) . " | ";
			$entry_updated_message .= anchor('entry/download/' . $entry_updated_type_label_temp . "/" . $entry_updated_id_temp, 'Download', array('class' => 'anchor-link-a'));
			$entry_updated_message .= " ] it.";
			$this->messages->add($entry_updated_message, 'success');

			if ($this->session->userdata('entry_updated_has_reconciliation'))
				$this->messages->add('Previous reconciliations for this entry are no longer valid. You need to redo the reconciliations for this entry.', 'success');

			$this->session->unset_userdata('entry_updated_show_action');
			$this->session->unset_userdata('entry_updated_id');
			$this->session->unset_userdata('entry_updated_type_id');
			$this->session->unset_userdata('entry_updated_type_label');
			$this->session->unset_userdata('entry_updated_type_name');
			$this->session->unset_userdata('entry_updated_number');
			$this->session->unset_userdata('entry_updated_has_reconciliation');
		}

		$data['entry_data'] = $entry_q;
		$data['entry_sort'] = $entry_type;
		// code to search user defined text 
		$text = '';
		$search_text = '';
		$fy_start = '';
		$fy_end = '';
		if (date('n') > 3)
		{
			$fy_start = date('Y');
			$fy_end = date('Y') + 1;
		} else {
			$fy_start = date('Y') - 1;
			$fy_end = date('Y');
		}
		$data['search'] = '';
		$data['entry_path'] = "show/" . $entry_type;
		if ($entry_type) {
			$data['search_by'] = array(
				"Select" => "Select",
		                "id" => "Id",
		                "forward_refrence_id"=> "Fwd Ref Id",
				"backward_refrence_id"=> "Bkwd Ref Id",
		                "date"=> "Date",
		                "update_date"=> "Update Date",
		                "number"=> "No",
		                "name"=> "Ledger Account",
		                "narration"=> "Narration",
		                "entry_type"=> "Type",
		                "dr_total"=> "DR Amount",
		                "cr_total"=> "CR Amount",
		                "submitted_by"=> "Submitted By",
		                "verified_by"=> "Verified By",
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
			
			/* Form Validation */
			$this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
			$this->form_validation->set_rules('text', 'Text', 'trim|required');
			/* Validating form */

			if ($this->form_validation->run() == FALSE)
			{
				$this->messages->add(validation_errors(), 'error');
				$this->template->load('template', 'entry/index', $data);
				return;
			}
			else
			{
				$data_search_by = $this->input->post('search_by', TRUE);
				$data_text = $this->input->post('text', TRUE);
			}
			if($data_search_by == "Select")
			{
				$this->messages->add('Please select search type from dropdown list.', 'error');
			}
			else {
				// id, forward ref.id, backward ref.id and number should be a number
				if($data_search_by == "id" || $data_search_by == "forward_refrence_id" || $data_search_by == "backward_refrence_id" || $data_search_by == "number") {
					$search_text = $data_text;
					if(! ctype_digit($data_text)) {
						$this->messages->add('Please enter a number.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				if($data_search_by == "entry_type")
				{
					$search_text = $data_text;
					// entry type should be alphabatic
					if(ctype_alpha($data_text)) {
						$str = strtolower($data_text);
						$data_text = entry_type_name_to_id($str);
						//if entry type is not a valid name
						if( $data_text == "") {
							$this->messages->add("Invalid entry type.", 'error');
							redirect('entry/' . $data['entry_path']);
						}
					}
					else {
						$this->messages->add('Please enter a valid entry type.', 'error');
						redirect('entry/' . $data['entry_path']);
					}				
				}
				if($data_search_by == "dr_total" || $data_search_by == "cr_total") {
					$search_text = $data_text;
					// debit and credit total should be number or decimal
					if(! is_numeric($data_text)) {
						$this->messages->add('Please enter a number.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				if($data_search_by == "submitted_by" || $data_search_by == "verified_by") {
					$search_text = $data_text;
					// submitter and verifier should be alphanumeric
					if(! ctype_alpha($data_text)) {
						$this->messages->add('Please enter alphanumeric value.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				if($data_search_by == "date" || $data_search_by == "update_date")
				{
					$search_text = $data_text;
					// if date and update date is single digit
					if(ctype_digit($data_text)) {
						$field = $data_search_by . '      ' . 'LIKE';
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
										redirect('entry/' . $data['entry_path']);
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
										redirect('entry/' . $data['entry_path']);
									}
								}
								// if date is unvalid
								else {
									$this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
									redirect('entry/' . $data['entry_path']);
								}
							}
							// if date and update date contain three digit
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
								$valid_date = checkdate($x,$date0,$date2);
								if($valid_date == 'true') {
									//check date is exist in financial year or not
									if($date2 == $fy_start || $date2 == $fy_end) {
										$data_text = $date2."-". $x ."-".$date0;
									}
									else {
										$this->messages->add($data_text . ' does not exist in financial year.', 'error');
										redirect('entry/' . $data['entry_path']);
									}
								}
								else {
									$this->messages->add($data_text . ' is invalid date.', 'error');
									redirect('entry/' . $data['entry_path']);
								}	
							}
						}
						else {
							$this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
							redirect('entry/' . $data['entry_path']);
						}
					}		
				}
				$field = $data_search_by . '      ' . 'LIKE';
				$text = $data_text;
				// if search type is not name
				if($data_search_by != "name") {
					$this->db->from('entries')->where($field, '%' . $text . '%')->order_by('date', 'desc')->order_by('id', 'desc');
					$entry_q = $this->db->get();
					if( $entry_q->num_rows() < 1 )
					{
						$this->messages->add($search_text . ' is not found.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				else {
					$this->db->from('entries');
                                        $entry_q = $this->db->get();
				}
			}
		}
		$data['search'] = $data_search_by;	
		$data['entry_data'] = $entry_q;
		$data['entry_sort'] = $entry_type;
		$data['entry_path'] = "show/" . $entry_type;
		$this->template->load('template', 'entry/index', $data);
		return;

	}

	function get_code($num, $code)
        {
                if($num < 9)
                {
                        $i = 0;
                        do{
                                $i++;
                                $data_code = $code . '0' . $num+$i;
                                $this->db->from('ledgers');
                                $this->db->select('id')->where('code =',$data_code);
                                $ledger_q = $this->db->get();
                        }while($ledger_q->num_rows() > 0);
                } else{
                        $i = 0;
                        do{
                                $i++;
                                $data_code = $code . $num+$i;
                                $this->db->from('ledgers');
                                $this->db->select('id')->where('code =',$data_code);
                                $ledger_q = $this->db->get();
                        }while($ledger_q->num_rows() > 0);
               }
               return $data_code;
        }

	function view($entry_type, $entry_id = 0)
	
	{
		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		$this->template->set('page_title', 'View ' . $current_entry_type['name'] . ' Entry');

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}

		//get 'Transit Income' id
                $this->db->select('id');
                $this->db->from('ledgers');
                $this->db->where('name', 'Transit Income');
                $query = $this->db->get();
                $income = $query->row();
                $income_id = $income->id;

		/* Load current entry details */
		$this->db->from('entry_items')->where('entry_id', $entry_id)->order_by('id', 'asc');
		//exclude Transit Income account
		$this->db->where('ledger_id !=', $income_id);
		$cur_entry_ledgers = $this->db->get();
		if ($cur_entry_ledgers->num_rows() < 1)
		{
			$this->messages->add('Entry has no associated Ledger accounts.', 'error');
		}
		$data['cur_entry'] = $cur_entry;
		$data['cur_entry_ledgers'] = $cur_entry_ledgers;
		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;

		/**
                 * Get reference ids from entries table
                 * Lines added by Priyanka
                 */

                $data['forward_reference_id'] = '';
                $data['backward_reference_id'] = '';
                $this->db->select('forward_refrence_id, backward_refrence_id, submitted_by, verified_by');
                $this->db->from('entries')->where('id', $entry_id)->order_by('id', 'asc');
                $reference_ids = $this->db->get();
                if ($reference_ids->num_rows() >0)
                {
                        foreach($reference_ids->result() as $ref)
                        {
                                $data['forward_reference_id'] = $ref->forward_refrence_id;
                                $data['backward_reference_id'] = $ref->backward_refrence_id;
				$data['submitted_by'] = $ref->submitted_by;
				$data['verified_by'] = $ref->verified_by;
                        }
                }

		$this->template->load('template', 'entry/view', $data);
		return;
	}

	function add($entry_type)
	{
		/* Check access */
		if ( ! check_access('create entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Message for entries related to asset purchase. */
		//$this->messages->add('If asset is being purchased. Then, make an additional entry related to corresponding fund.', 'success');

		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		//$this->messages->add('Line No 241==>.' .$entry_type_id );
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		$this->template->set('page_title', 'New ' . $current_entry_type['name'] . ' Entry');


		/* Form fields */
		$data['entry_number'] = array(
			'name' => 'entry_number',
			'id' => 'entry_number',
			'maxlength' => '11',
			'size' => '11',
			'value' => '',
		);

		/**
		 * Refrence Ids have been added for reconciliation purpose.
		 * The forward refrence id consists of entry_id of the related 
		 * forward dated transaction and backward refrence id consists 
		 * of entry_id of the backward dated transaction.
		 */
		/*$data['forward_refrence_id'] = array(
			'name' => 'forward_refrence_id',
			'id' => 'forward_refrence_id',
			'maxlength' => '11',
			'size' => '11',
			'value' => '',
		);
		*/
		$data['backward_refrence_id'] = array(
			'name' => 'backward_refrence_id',
			'id' => 'backward_refrence_id',
			'maxlength' => '11',
			'size' => '11',
			'value' => '',
		);
		$data['bank_name'] = array(
                        'name' => 'bank_name',
                        'id' => 'bank_name',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => '',
                );
		$data['banif_name'] = array(
                        'name' => 'banif_name',
                        'id' => 'banif_name',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => '',
                );


		$data['entry_date'] = array(
			'name' => 'entry_date',
			'id' => 'entry_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_today_php(),
		);
		$data['entry_narration'] = array(
			'name' => 'entry_narration',
			'id' => 'entry_narration',
			'cols' => '50',
			'rows' => '4',
			'value' => '',
		);
		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;
		$data['entry_tags'] = $this->Tag_model->get_all_tags();
		$data['entry_tag'] = 0;

		$data['fund_list'] = $this->Ledger_model->get_ledgers();
                $data['fund_list_active'] = 0;

		$options = array();
                $this->db->select('name, label');
                $this->db->from('entry_types');
                $query = $this->db->get();
                $new_id = '--Please Select--';
                $options[$new_id] = '--Please Select--';
                foreach($query->result() as $row)
                {
                        $new_id = $row->name;
                        $options[$new_id] = $row->name;
                }
                $data['entry_name']=$options;

                $data['active_entry_name'] = 'Please Select';

		/* Form validations */
		if ($current_entry_type['numbering'] == '2')
			$this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|required|is_natural_no_zero|uniqueentryno[' . $entry_type_id . ']');
		else if ($current_entry_type['numbering'] == '3')
			$this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|is_natural_no_zero|uniqueentryno[' . $entry_type_id . ']');
		else
			$this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|is_natural_no_zero|uniqueentryno[' . $entry_type_id . ']');
		//$this->form_validation->set_rules('forward_refrence_id', 'Forward Refrence Id', 'trim|is_natural_no_zero');
		$this->form_validation->set_rules('backward_refrence_id', 'Backward Refrence Id', 'trim|is_natural_no_zero');
		$this->form_validation->set_rules('entry_date', 'Bill/Voucher Date', 'trim|required|is_date|is_date_within_range');
		$this->form_validation->set_rules('entry_narration', 'trim');
		$this->form_validation->set_rules('entry_tag', 'Tag', 'trim|is_natural');
		$this->form_validation->set_rules('entry_name', 'Entry Type', 'trim|required');
		$this->form_validation->set_rules('bank_name', 'Bank name', 'trim');
		$this->form_validation->set_rules('banif_name', 'Beneficiary name', 'trim');
		/* Debit and Credit amount validation */
			
		
		
		if ($_POST)
		{
			foreach ($this->input->post('ledger_dc', TRUE) as $id => $ledger_data)
			{
					
				$this->form_validation->set_rules('dr_amount[' . $id . ']', 'Debit Amount', 'trim|currency');
				$this->form_validation->set_rules('cr_amount[' . $id . ']', 'Credit Amount', 'trim|currency');

			}
		}
	
		/* Repopulating form */
		if ($_POST)
		{
			$data['entry_number']['value'] = $this->input->post('entry_number', TRUE);
			$data['entry_date']['value'] = $this->input->post('entry_date', TRUE);
			$data['entry_narration']['value'] = $this->input->post('entry_narration', TRUE);
			$data['entry_tag'] = $this->input->post('entry_tag', TRUE);
			//$data['forward_refrence_id'] = $this->input->post('forward_refrence_id', TRUE);
			$data['backward_refrence_id'] = $this->input->post('backward_refrence_id', TRUE);
			$data['active_entry_name'] = $this->input->post('entry_name', TRUE);
			$data['ledger_dc'] = $this->input->post('ledger_dc', TRUE);
			$data['ledger_id'] = $this->input->post('ledger_id', TRUE);
			$data['dr_amount'] = $this->input->post('dr_amount', TRUE);
			$data['cr_amount'] = $this->input->post('cr_amount', TRUE);
			$data['bank_name']['value'] = $this->input->post('bank_name', TRUE);
                        $data['banif_name']['value'] = $this->input->post('banif_name', TRUE);
                        $data['cheque'] = $this->input->post('cheque', TRUE);
			$data['fund_list_active'] = $this->input->post('fund_list', TRUE);

		} 
		else {
			for ($count = 0; $count <= 3; $count++)
			{
				// these lines existed earlier
				/*if ($count == 0 && $entry_type == "payment")
					$data['ledger_dc'][$count] = "C";
				else if ($count == 1 && $entry_type != "payment")
					$data['ledger_dc'][$count] = "C";
				else
					$data['ledger_dc'][$count] = "D";
				*/
				// line added by Priyanka
				if ($count == 0 )
					$data['ledger_dc'][$count] = "C";
				else
	                                $data['ledger_dc'][$count] = "D";

				$data['ledger_id'][$count] = 0;
				$data['dr_amount'][$count] = "";
				$data['cr_amount'][$count] = "";
			}
		}
		
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'entry/add', $data);
			return;
		}
		else
		{
			/* Checking for Valid Ledgers account and Debit and Credit Total */
			$data_all_ledger_id = $this->input->post('ledger_id', TRUE);
			$data_all_ledger_dc = $this->input->post('ledger_dc', TRUE);
			$data_all_dr_amount = $this->input->post('dr_amount', TRUE);
			$data_all_cr_amount = $this->input->post('cr_amount', TRUE);
			$data_banif_name = $this->input->post('banif_name', TRUE);
                        $data_bank_name = $this->input->post('bank_name', TRUE);
                        $data_entry_name = $this->input->post('entry_name', TRUE);
                        $data_date = $this->input->post('entry_date', TRUE);
                        $data_cheque = $this->input->post('cheque', TRUE);
                        $data_date = date_php_to_mysql($data_date); // Converting date to MySQL
			$bank_cash_global = '';

			if($data_entry_name == 'Payment' || $data_entry_name == 'Receipt' || $data_entry_name == 'Contra' )
                        {

			foreach ($data_all_ledger_dc as $id => $ledger_data)
                        {
                                if($data_all_ledger_id[$id] < 1)
					continue;

				$bank_cash = $this->Ledger_model->get_ledgers_bankcash($data_all_ledger_id[$id]);

				if($data_all_ledger_dc[$id] == 'C' && $bank_cash == '1')
					$data_entry_name = 'Payment';
                                elseif($data_all_ledger_dc[$id] == 'D' && $bank_cash == '1')
					$data_entry_name = 'Receipt';

				if($bank_cash_global== '1' && $bank_cash == '1')
                        		$data_entry_name = 'Contra';
                                        
								
				if($bank_cash == '1')
					$bank_cash_global = '1';
				else
					$bank_cash_global = '0';
                        }
			}
			// get the correct id

			$entry_type_id=entry_type_name_to_id(strtolower($data_entry_name));
			$dr_total = 0;
			$cr_total = 0;
			$bank_cash_present = FALSE; /* Whether atleast one Ledger account is Bank or Cash account */
			$non_bank_cash_present = FALSE;  /* Whether atleast one Ledger account is NOT a Bank or Cash account */
			/* Adding main entry */
                        if ($current_entry_type['numbering'] == '2')
                        {
                                $data_number = $this->input->post('entry_number', TRUE);
                        } else if ($current_entry_type['numbering'] == '3') {
                                $data_number = $this->input->post('entry_number', TRUE);
                                if ( ! $data_number)
                                        $data_number = NULL;
                        } else {
                                if ($this->input->post('entry_number', TRUE))
                                        $data_number = $this->input->post('entry_number', TRUE);
                                else
                                        $data_number = $this->Entry_model->next_entry_number($entry_type_id);
                        }
		
			foreach ($data_all_ledger_dc as $id => $ledger_data)
			{
				
				//these lines existed earlier
				if ($data_all_ledger_id[$id] < 1)
					continue;
				
                                       
				/* Check for valid ledger id */
				$this->db->from('ledgers')->where('id', $data_all_ledger_id[$id]);
				
				//lines added by Priyanka
                                //$ledger_array = explode('#', $data_all_ledger_id[$id]);
                                //$ledger_id = $ledger_array[0];
                                //$ledger_code = $ledger_array[1];
                                //if ($ledger_id < 1)
                                  //      continue;
                                //$ledger_code = $ledger_array[1];
				//$this->db->from('ledgers')->where('id', $ledger_id);
				//...
					
				$valid_ledger_q = $this->db->get();
				if ($valid_ledger_q->num_rows() < 1)
				{
					$this->messages->add('Invalid Ledger account.', 'error');
					$this->template->load('template', 'entry/add', $data);
					return;
				}
				// these checks existed earlier 
				/*else {
					/* Check for valid ledger type /
					$valid_ledger = $valid_ledger_q->row();
					if ($current_entry_type['bank_cash_ledger_restriction'] == '2')
					{
						if ($data_all_ledger_dc[$id] == 'D' && $valid_ledger->type == 1)
						{
						
							$bank_cash_present = TRUE;
						}
						if ($valid_ledger->type != 1)
							$non_bank_cash_present = TRUE;

					} else if ($current_entry_type['bank_cash_ledger_restriction'] == '3')
					{
						if ($data_all_ledger_dc[$id] == 'C' && $valid_ledger->type == 1)
						{
							$bank_cash_present = TRUE;
						}
						if ($valid_ledger->type != 1)
							$non_bank_cash_present = TRUE;
					} else if ($current_entry_type['bank_cash_ledger_restriction'] == '4')
					{
						if ($valid_ledger->type != 1)
						{
							$this->messages->add('Invalid Ledger account. ' . $current_entry_type['name'] . ' Entries can have only Bank and Cash Ledgers accounts.', 'error');
							$this->template->load('template', 'entry/add', $data);
							return;
						}
					} else if ($current_entry_type['bank_cash_ledger_restriction'] == '5')
					{
						if ($valid_ledger->type == 1)
						{
							$this->messages->add('Invalid Ledger account. ' . $current_entry_type['name'] . ' Entries cannot have Bank and Cash Ledgers accounts.', 'error');
							$this->template->load('template', 'entry/add', $data);
							return;
						}
					}
				}*/
				if ($data_all_ledger_dc[$id] == "D")
				{
					$dr_total = float_ops($data_all_dr_amount[$id], $dr_total, '+');
				} else {
					$cr_total = float_ops($data_all_cr_amount[$id], $cr_total, '+');
				}
			 }

			if (float_ops($dr_total, $cr_total, '!='))
			{
				$this->messages->add('Debit and Credit Total does not match!', 'error');
				$this->template->load('template', 'entry/add', $data);
				return;
			} else if (float_ops($dr_total, 0, '==') && float_ops($cr_total, 0, '==')) {
				$this->messages->add('Cannot save empty Entry.', 'error');
				$this->template->load('template', 'entry/add', $data);
				return;
			}

			// these checks existed earlier
			/* Check if atleast one Bank or Cash Ledger account is present */
			/*if ($current_entry_type['bank_cash_ledger_restriction'] == '2')
			{
				if ( ! $bank_cash_present)
				{
					$this->messages->add('Need to Debit atleast one Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/add', $data);
					return;
				}
				if ( ! $non_bank_cash_present)
				{
					$this->messages->add('Need to Debit or Credit atleast one NON - Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/add', $data);
					return;
					}
			} else if ($current_entry_type['bank_cash_ledger_restriction'] == '3')
			{
				if ( ! $bank_cash_present)
				{
					$this->messages->add('Need to Credit atleast one Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/add', $data);
					return;
				}
				if ( ! $non_bank_cash_present)
				{
					$this->messages->add('Need to Debit or Credit atleast one NON - Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/add', $data);
					return;
				}
			}*/


			$data_date = $this->input->post('entry_date', TRUE);
			//$data_forw_refrence = $this->input->post('forward_refrence_id', TRUE);
			$data_back_refrence = $this->input->post('backward_refrence_id', TRUE);
			$data_narration = $this->input->post('entry_narration', TRUE);
			$data_tag = $this->input->post('entry_tag', TRUE);
			if ($data_tag < 1)
				$data_tag = NULL;
				$this->db->select('id')->from('entry_types')->where('name', $data_entry_name);
                                $query1 = $this->db->get();
                                foreach($query1->result() as $row1)
                                {
                                        $id = $row1->id;
                                }

			
			$data_date = date_php_to_mysql($data_date); // Converting date to MySQL
			$entry_id = NULL;
			$uname=$this->session->userdata('user_name');
			$today = date("Y-m-d H:i:s");
			$this->db->trans_start();
			$insert_data = array(
				'number' => $data_number,
				'date' => $data_date,
				'narration' => $data_narration,
				'entry_type' => $id,
				'tag_id' => $data_tag,
				'update_date' => $today,
				'submitted_by' => $uname,
				'forward_refrence_id' => '0',
				'backward_refrence_id' => $data_back_refrence
			);

			 //echo random_element($insert_data);

			if ( ! $this->db->insert('entries', $insert_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error addding Entry.', 'error');
				$this->logger->write_message("error", "Error adding " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " since failed inserting entry");
				$this->template->load('template', 'entry/add', $data);
				return;
			} else {
				$entry_id = $this->db->insert_id();
			}


			/* Adding ledger accounts */
			$data_all_ledger_dc = $this->input->post('ledger_dc', TRUE);
			$data_all_ledger_id = $this->input->post('ledger_id', TRUE);
			$data_all_dr_amount = $this->input->post('dr_amount', TRUE);
			$data_all_cr_amount = $this->input->post('cr_amount', TRUE);
			$data_all_fund_ledger = $this->input->post('fund_list', TRUE);

			$dr_total = 0;
			$cr_total = 0;
			$ledg_code = 0;
			$budgetamt = 0;
			$data_amount = 0;
			$useamt = 0;
			$allow = 0;


			foreach ($data_all_ledger_dc as $id => $ledger_data)
			{

				$data_ledger_dc = $data_all_ledger_dc[$id];
				$data_ledger_id = $data_all_ledger_id[$id];
				//lines added by Priyanka
                                //$ledger_array = explode('#', $data_ledger_id);
                                //$ledger_id = $ledger_array[0];
                                //$ledger_code = $ledger_array[1];

                                //if ($ledger_id < 1)
                                 //       continue;
				//...
				
                                //this line existed earlier
				if ($data_ledger_id < 1)
					continue;

				//$data_amount = 0;
				if ($data_all_ledger_dc[$id] == "D")
				{
					$data_amount = $data_all_dr_amount[$id];
					$dr_total = float_ops($data_all_dr_amount[$id], $dr_total, '+');
					
				} else {
					
					$data_amount = $data_all_cr_amount[$id];
					$cr_total = float_ops($data_all_cr_amount[$id], $cr_total, '+');
				}	
					 $data_ledger_id;

				if($data_ledger_dc == "D")
				{//001
					//these lines existed earlier
					//if($entry_type_id == '2'){//01
				 	$this->db->from('ledgers')->where('id', $data_ledger_id);
                                        //$this->db->from('ledgers')->where('id', $ledger_id);	
					$query_q = $this->db->get();
		                        $query_n = $query_q->row();
                		        $this->id = $query_n->id;
                        		$this->code = $query_n->code;
					$this->group_id = $query_n->group_id;
					$ledg_code=$this->code;
					$groupid=$this->group_id;
					/**
                                         * To identify "expense" entry, code of the selected ledger head
                                         * is being fetched. If the ledger head has "Expenses" as its 
                                         * super parent and the account is being debited, then it must
                                         * be a "expense" entry.
					 * Then, the corresponding budget amount is updated. Since,
					 * the budget amount is set for "expense" only.
                                         * Lines added by Priyanka
                                         */
					$this->load->model('Budget_model');
                                        $account_code = $this->Budget_model->get_account_code('Expenses');
					$temp = $this->startsWith($ledg_code, $account_code);
                                        if($temp){//01
					
					//get budget amnt 
					$parents;
					$query1=$this->db->from('budgets')->where('code', $ledg_code)->get();
					
					/**
					* code for if particular head is not in budget,
					* then made payment from parent which is present
					* in budget.
					*/
					if($query1->num_rows() > 0)
		                        {
					$this->db->from('budgets')->where('code', $ledg_code);
                                        $query_l = $this->db->get();
                                        $query_l = $query_l->row();
                                        $this->amt = $query_l->bd_balance;
					$this->useamt = $query_l->consume_amount;
					$this->allow=$query_l->allowedover;
                                        $budgetamt=$this->amt;
					$useamt=$this->useamt;
					$allow=$this->allow;

					if($budgetamt > $useamt)

					{//if1
						$available_amount=$budgetamt - $useamt ;//its wrong
						/**  payment amount is greater than or equal to available amount **/
						if($data_amount > $available_amount)
						{
							/* check for allowed over expense*/

							if(($allow == -1) || ($allow == 0))
                                                	{
                                                        	$this->messages->add('Budget is not sufficient to make this payment.','error');
                                                       // 	$this->template->load('template', 'entry/add',$data);
                                                        	return;
                                                	}
							else
							{
								/* check for payment amount by adding allowd over amount + consume amount */
								$available_amount = $budgetamt - $useamt + $allow;
								if($data_amount >= $available_amount)
								{
	                                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
        	                                          //              $this->template->load('template', 'entry/add',$data);
                	                                                return;

								}
								else
								{
									/* Update budget table */
									$sumamt=$data_amount + $useamt;
									$allow_left = $available_amount - $data_amount ;
									$update_data1 = array('consume_amount' => $sumamt, 'allowedover' => $allow_left);
						                        if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                        						{
                                						$this->db->trans_rollback();
                                						$this->messages->add('Error updating total expenses amount in budget.', 'error');
                                						$this->template->load('template', 'entry/add', $data);
                                						return;
                        						}
									$parents = new GetParentlist();
				                                        $parents->init($groupid,$data_amount);

									//$this->db->from('budgets')->where('code', '50');
									$this->db->from('budgets')->where('budgetname', 'Main Budget');
				                                        $query_ll = $this->db->get();
                                				        $query_ll = $query_ll->row();
                                        //$this->id = $query_l->id;
				                                        $this->amt1 = $query_ll->bd_balance;
                                				        $this->useamt1 = $query_ll->consume_amount;
									$update_data2 = $this->useamt1 + $data_amount;
									$update_data3 = array('consume_amount' => $update_data2);
									//if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
									if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                                        {
                                                                                $this->db->trans_rollback();
                                                                                $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                                $this->template->load('template', 'entry/add', $data);
                                                                                return;
                                                                        }

								}
								
							}

						}
						else
						{
							$sumamt=$data_amount + $useamt;
							$update_data1 = array('consume_amount' => $sumamt );
                                                        if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                        {
                                                                     $this->db->trans_rollback();
                                                                     $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                     $this->template->load('template', 'entry/add', $data);
                                                                     return;
                                                        }
							$parents = new GetParentlist();
		                                        $parents->init($groupid,$data_amount);
							
							//$this->db->from('budgets')->where('code', '50');
							$this->db->from('budgets')->where('budgetname = ', 'Main Budget');
                                                        $query_ll = $this->db->get();
                                                        $query_ll = $query_ll->row();
                                                        $this->amt1 = $query_ll->bd_balance;
                                                        $this->useamt1 = $query_ll->consume_amount;
                                                        $update_data2 = $this->useamt1 + $data_amount;
                                                        $update_data3 = array('consume_amount' => $update_data2);


							//if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
							if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                         		{
                                                                                $this->db->trans_rollback();
                                                                                $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                                $this->template->load('template', 'entry/add', $data);
                                                                                return;
                                                        }

						}
					}//1	
					/* consume amount is greater than allocated budget amount*/ 
					if($useamt >= $budgetamt)
					{//2
						/* check for allowed over expenses */
						if(($allow == -1) || ($allow == 0))
	                                        {
                                                	$this->messages->add('Budget is not sufficient to make this payment.','error');
                                                	return;
						}
						/** get over consume amount and check with allowed left **/
				
						
						$overconsume_amount = $useamt - $budgetamt ;
						/* payment amount is greater than allowed over amount*/
						if($data_amount > $allow)
						{
	                                                $this->template->load('template', 'entry/add',$data);
							return;
						}
						 /* payment amount is less than allowed over amount*/
						if($data_amount <= $allow)
						{
							$overconsume_amount = $useamt - $budgetamt ;
							$available_amount = $allow ;
							$allowed_left = $allow - $data_amount;
							$consume_amount = $useamt + $data_amount;
							$update_data1 = array('consume_amount' => $consume_amount, 'allowedover' => $allowed_left);
                                                        if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                        {
                                                                $this->db->trans_rollback();
                                                                $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                $this->template->load('template', 'entry/add', $data);
                                                                return;
                                                        }
							$parents = new GetParentlist();
		                                        $parents->init($groupid,$data_amount);
							//$this->db->from('budgets')->where('code', '50');
							$this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                        $query_ll = $this->db->get();
                                                        $query_ll = $query_ll->row();
                                                        $this->amt1 = $query_ll->bd_balance;
                                                        $this->useamt1 = $query_ll->consume_amount;
                                                        $update_data2 = $this->useamt1 + $data_amount;
                                                        $update_data3 = array('consume_amount' => $update_data2);


							//if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
							if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
        	                                        {
                 	                                       $this->db->trans_rollback();
                                                               $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                               $this->template->load('template', 'entry/add', $data);
                                                               return;
                                                        }
						}	
					}//2

				//	}//01
					}
					else
					{
						$parents_get ="";
                                		$parents_get=$this->init1l($groupid,$data_amount,$data);
					}
					}//01	
				
				}//001
				
				/* Code for making entry in Fund and Transit Income account. */
				$fund_ledger = $data_all_fund_ledger[$id];
				if($fund_ledger > 0 && $data_ledger_dc == 'D'){
					$insert_fund_data = array(
                                       		'entry_id' => $entry_id,
	                                        'ledger_id' => $fund_ledger,
               		                        'amount' => $data_amount,
                               		        'dc' => 'D',
	                                        'update_date' => $data_date,
               		                        'forward_refrence_id' => '0',
                               		        'backward_refrence_id' => $data_back_refrence
	                                );

        	                        if ( ! $this->db->insert('entry_items', $insert_fund_data))
		                        {
                	                        $this->db->trans_rollback();
	                                        $this->logger->write_message("error", "Error adding fund id:" . $fund_ledger);
                        	        }

					$this->db->select('id');
					$this->db->from('ledgers')->where('name', 'Transit Income');
					$query = $this->db->get();
					$income = $query->row();
					$income_id = $income->id;
									
					$insert_income_data = array(
	                                        'entry_id' => $entry_id,
        	                                'ledger_id' => $income_id,
                	                        'amount' => $data_amount,
                        	                'dc' => 'C',
                                		'update_date' => $data_date,
	                                        'forward_refrence_id' => '0',
        	                                'backward_refrence_id' => $data_back_refrence
                	                );

	                                if ( ! $this->db->insert('entry_items', $insert_income_data))
        	                        {
                	                        $this->db->trans_rollback();
                                	        $this->logger->write_message("error", "Error adding transit income");
        	                        }
				}//....

				$insert_ledger_data = array(
					'entry_id' => $entry_id,
					'ledger_id' => $data_ledger_id,
					'amount' => $data_amount,
					'dc' => $data_ledger_dc,
					'update_date' => $data_date,
					'forward_refrence_id' => '0',
	                                'backward_refrence_id' => $data_back_refrence
				);
				if ( ! $this->db->insert('entry_items', $insert_ledger_data))
				{
					$this->db->trans_rollback();
					$this->messages->add('Error adding Ledger account - ' . $data_ledger_id . ' to Entry.', 'error');
					$this->logger->write_message("error", "Error adding " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " since failed inserting entry ledger item " . "[id:" . $data_ledger_id . "]");
					$this->template->load('template', 'entry/add', $data);
					return;
				}
			}

			/* Updating Debit and Credit Total in entries table */
			$update_data = array(
				'dr_total' => $dr_total,
				'cr_total' => $cr_total,
			);
			if ( ! $this->db->where('id', $entry_id)->update('entries', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Entry total.', 'error');
				$this->logger->write_message("error", "Error adding " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " since failed updating debit and credit total");
				$this->template->load('template', 'entry/add', $data);
				return;
			}
			/*lines added by manshi*/
                        foreach ($data_all_ledger_dc as $id => $ledger_data)
                        {
                                if ($data_all_ledger_id[$id] < 1)
                                        continue;
				$this->db->select('id')->from('entries')->order_by('id', 'desc')->limit (1);
	                        $ledger_q = $this->db->get();
                        	if ($ledger = $ledger_q->row())
                       				
                                $this->db->from('ledgers')->where('id', $data_all_ledger_id[$id]);
                                $valid_ledger_q = $this->db->get();
                                $valid_ledger = $valid_ledger_q->row();
                                if ($valid_ledger_q->num_rows() < 1)
                                {
                                        $this->messages->add('Invalid Ledger account.', 'error');
                                        $this->template->load('template', 'entry/add', $data);
                                        return;
                                }
						$val=$data_cheque[$id];
						
						if($val != NULL)
						{
                                  		$valid_ledger = $valid_ledger_q->row();
                                                if ($data_all_ledger_dc[$id] == 'D' && $valid_ledger->type == 1)
                                                {
						
                                                         $bank_cash_present = TRUE;
                                                         $this->db->trans_start();
                                                         $insert_data = array(
                                                                'Bank_name'=>$data_bank_name,
                                                                'amount' => $data_amount,
                                                                'name' => $data_banif_name,
                                                                'dc'=>'D',
								'entry_no'=>$ledger->id,
                                                                'cheque_date'=>$data_date,
                                                                'cheque_no' => $val,
                                                                );

                                                        if ( ! $this->db->insert('reconcilation', $insert_data))
                                                        {
                                                                $this->db->trans_rollback();
                                                                $this->template->load('template', 'entry/add', $data);
                                                                return;
                                                                } else {
                                                                $this->db->trans_complete();
                                                                }

                                                }
					 	if ($valid_ledger->type != 1)
                                                $non_bank_cash_present = TRUE;
                                                if ($data_all_ledger_dc[$id] == 'C' && $valid_ledger->type == 1)
                                                {
                                                         $bank_cash_present = TRUE;
                                                         $this->db->trans_start();
                                                         $insert_data = array(
                                                                'Bank_name'=>$data_bank_name,
                                                                'amount' => $data_amount,
                                                                'name' => $data_banif_name,
                                                                'dc'=>'C',
                                                                'entry_no'=>$ledger->id,
                                                                'cheque_date'=>$data_date,
                                                                'cheque_no' => $val,
                                                                );

                                                        if ( ! $this->db->insert('reconcilation', $insert_data))
                                                        {
                                                           	$this->db->trans_rollback();
                                                                $this->template->load('template', 'entry/add', $data);
                                                                return;
                                                                } else {
                                                                $this->db->trans_complete();
                                                                }
                                                }
                        		}

				}

			/* Success */
			$this->db->trans_complete();
			$this->session->set_userdata('entry_added_show_action', TRUE);
			$this->session->set_userdata('entry_added_id', $entry_id);
			$this->session->set_userdata('entry_added_type_id', $entry_type_id);
			$this->session->set_userdata('entry_added_type_label', $current_entry_type['label']);
			$this->session->set_userdata('entry_added_type_name', $current_entry_type['name']);
			$this->session->set_userdata('entry_added_number', $data_number);
			/* Showing success message in show() method since message is too long for storing it in session */
			$this->logger->write_message("success", "Added " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " [id:" . $entry_id . "]");
			redirect('entry/show/' . $current_entry_type['label']);
			$this->template->load('template', 'entry/add', $data);
			return;
		}
		return;
	}
//end of Payment 

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	function ledger_code($ledger_id = 0)
        {
		$account_code = 0;
                if ($ledger_id > 0){
                        $code = $this->Ledger_model->get_ledger_code($ledger_id);
			$account_code = $this->Budget_model->get_account_code('Expenses');
			$temp = $this->startsWith($code, $account_code);
                        if($temp)
	                        echo "Expense";

			$account_code = $this->Budget_model->get_account_code('Incomes');
                        $temp = $this->startsWith($code, $account_code);
                        if($temp)
                                echo "Income";
                                        
			$account_code = $this->Budget_model->get_account_code('Assets');
                        $temp = $this->startsWith($code, $account_code);
                        if($temp)
                                echo "Asset";
                }else{
                        echo "";
		}
                return;
        }
	
	 function check_acc($ledger_id = 0)
        {
        if ($ledger_id > 0)
                        echo $this->Ledger_model->get_ledgers_bankcash($ledger_id);
                else
                        echo "";
                return;


        }


	function edit($entry_type, $entry_id = 0)
	{
		
		/* Check access */
		if ( ! check_access('verify entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Message for entries related to asset purchase. */
                $this->messages->add('If asset is being purchased. Then, make an additional entry related to corresponding fund.', 'success');	
	
		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		$this->template->set('page_title', 'Edit ' . $current_entry_type['name'] . ' Entry');

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}
		$loginname=$this->session->userdata('user_name');
                $submittername=$cur_entry->submitted_by;
		$bank_name='';
		$name='';
		$this->db->select('name,bank_name,cheque_no')->from('reconcilation')->where('entry_no',$entry_id);
                $ledger_q = $this->db->get();
               // if ($ledger = $ledger_q->row())
		foreach($ledger_q->result() as $row)
                                {
                                $bank_name = $row->bank_name;
                                $name= $row->name;
                                }

		

                if($loginname==$submittername){
                        $this->messages->add('Submitter can not verify own entry');
                        redirect('entry/show/' . $current_entry_type['label']);
                        return;
                }
                else{

		/* Form fields - Entry */
		$data['entry_number'] = array(
			'name' => 'entry_number',
			'id' => 'entry_number',
			'maxlength' => '11',
			'size' => '11',
			'value' => $cur_entry->number,
		);
		$data['entry_date'] = array(
			'name' => 'entry_date',
			'id' => 'entry_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_mysql_to_php($cur_entry->date),
		);
		$data['bank_name'] = array(
                        'name' => 'bank_name',
                        'id' => 'bank_name',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => $bank_name,
                );
                $data['banif_name'] = array(
                        'name' => 'banif_name',
                        'id' => 'banif_name',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => $name,
                );

		$data['entry_narration'] = array(
			'name' => 'entry_narration',
			'id' => 'entry_narration',
			'cols' => '50',
			'rows' => '4',
			'value' => $cur_entry->narration,
		);
		$data['entry_id'] = $entry_id;
		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;
		$data['entry_tag'] = $cur_entry->tag_id;
		$data['entry_tags'] = $this->Tag_model->get_all_tags();
		$data['has_reconciliation'] = FALSE;
		 $options = array();
                $this->db->select('name, label');
                $this->db->from('entry_types');
                $query = $this->db->get();
            	$current_entry_type = entry_type_info($cur_entry->entry_type);
		$new_id = $current_entry_type['name'];
                $options[$new_id] = $current_entry_type['name'];
                foreach($query->result() as $row)
                {
                        $new_id = $row->name;
                        $options[$new_id] = $row->name;
                }
                $data['entry_name']=$options;

                $data['active_entry_name'] = 'Please Select';


		/**
                 * Refrence Ids have been added for reconciliation purpose.
                 * The forward refrence id consists of entry_id of the related 
                 * forward dated transaction and backward refrence id consists 
                 * of entry_id of the backward dated transaction.
                 */
                $data['forward_refrence_id'] = array(
                        'name' => 'forward_refrence_id',
                        'id' => 'forward_refrence_id',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => $cur_entry->forward_refrence_id,
                );
                
                $data['backward_refrence_id'] = array(
                        'name' => 'backward_refrence_id',
                        'id' => 'backward_refrence_id',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => $cur_entry->backward_refrence_id,
                );

		//$data['fund_list'] = $this->Ledger_model->get_ledgers();
                //$data['fund_list_active'] = 0;

		$debitled="";
		$debitid="";
		$creditled="";
		$creditid="";
		/* Load current ledger details if not $_POST */
		//if ( ! $_POST)
		//{
			$this->db->from('entry_items')->where('entry_id', $entry_id);
			$cur_ledgers_q = $this->db->get();
			if ($cur_ledgers_q->num_rows <= 0)
			{
				$this->messages->add('No Ledger accounts found!', 'error');
			}
			$counter = 0;

			$flag = 0;
			$fund_id = '';

			//get 'Transit Income' id
			$this->db->select('id');
                        $this->db->from('ledgers');
                        $this->db->where('name', 'Transit Income');
                        $query = $this->db->get();
                        $income = $query->row();
			$income_id = $income->id;

			foreach ($cur_ledgers_q->result() as $row)
			{
				$temp = null;
				$account_code = null;
				$bank_cash = null;
				$index = -1;

				//check whether ledger is 'Transit Income'
                                if($row->ledger_id != $income_id){

					/* code for fund list*/
                                        $ledger_code = $this->Ledger_model->get_ledger_code($row->ledger_id);
                                        $account_code = $this->Budget_model->get_account_code('Liabilities and Owners Equity');
                                        $temp = $this->startsWith($ledger_code, $account_code);

                                        //if ledger is a liability account
                                        if($temp && $flag == 0 && $row->dc == 'D'){
 	                                       $fund_id = $row->ledger_id;
                                               $flag = 1;
                                        }else{
						$flag = 0;
					}

					if($flag == 0){
						$data['ledger_dc'][$counter] = $row->dc;
						$data['ledger_id'][$counter] = $row->ledger_id;
	
						if ($row->dc == "D")
						{
							$debitled=$row->dc;
							$debitid= $row->ledger_id;

							$account_code = $this->Budget_model->get_account_code('Assets');
        	        	                        $temp = $this->startsWith($ledger_code, $account_code);
							//if ledger is an asset account					
							if($temp){
								$bank_cash = $this->Ledger_model->get_ledgers_bankcash($debitid);
								if($bank_cash == 0){
                                                                //$data['fund_list_active'.$counter][$counter]= $fund_id;
	                                                                $data['fund_list'][$counter] = $fund_id;
                                                                	$flag = 0;
	                                                                $fund_id = null;
        	                                                        $bank_cash = null;
                	                                        }
							}


							if($temp == null){
								$account_code = $this->Budget_model->get_account_code('Expenses');
	                                                        $temp = $this->startsWith($ledger_code, $account_code);
								if($temp){
                                                                //$data['fund_list_active'.$counter][$counter]= $fund_id;
                                                                	$data['fund_list'][$counter] = $fund_id;
	                                                                $flag = 0;
        	                                                        $fund_id = null;
                	                                                $bank_cash = null;
                        	                                }
							}

	
							//if ledger is an asset non-bank/cash account
							//if($temp && $bank_cash == 0 && $flag = 1){
						/*	if($temp && $bank_cash == 0){
								//$data['fund_list_active'.$counter][$counter]= $fund_id;
								$data['fund_list'][$counter] = $fund_id;
								$flag = 0;
								$fund_id = null;
								$bank_cash = null;
							}*/
	
							$data['dr_amount'][$counter] = $row->amount;
							$data['cr_amount'][$counter] = "";
						} else {
							$creditled=$row->dc;
							$creditid=$row->ledger_id;
							$data['dr_amount'][$counter] = "";
							$data['cr_amount'][$counter] = $row->amount;
							$data['fund_list_active'.$counter][$counter]= 0;
						}
						if ($row->reconciliation_date)
							$data['has_reconciliation'] = TRUE;
						$counter++;
					}
				}
			}
			
			/* Two extra rows */
			$data['ledger_dc'][$counter] = 'D';
			$data['ledger_id'][$counter] = 0;
			$data['dr_amount'][$counter] = "";
			$data['cr_amount'][$counter] = "";
			$counter++;
			$data['ledger_dc'][$counter] = 'D';
			$data['ledger_id'][$counter] = 0;
			$data['dr_amount'][$counter] = "";
			$data['cr_amount'][$counter] = "";
			$counter++;
		//}
		$creditledgername = $this->Ledger_model->get_name($creditid);
		$debitledgername = $this->Ledger_model->get_name($debitid);
		$cramount= $cur_entry->cr_total;
		$dramount= $cur_entry->dr_total;
		$narrat= $cur_entry->narration;
		$previousvalue="'Credit ledger name'"." ". $creditledgername .',' ."'Debited ledger name'"." ". $debitledgername.','."'Cr Amount'"." " . $cramount.','."'Dr Amount'"."  " . $dramount.','."'Narration' " . $narrat;
		/* Form validations */
		if ($current_entry_type['numbering'] == '3')
			$this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|is_natural_no_zero|uniqueentrynowithid[' . $entry_type_id . '.' . $entry_id . ']');
		else
			$this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|required|is_natural_no_zero|uniqueentrynowithid[' . $entry_type_id . '.' . $entry_id . ']');
		$this->form_validation->set_rules('entry_date', 'Bill/Voucher Date', 'trim|required|is_date|is_date_within_range');
		$this->form_validation->set_rules('entry_narration', 'trim');
		$this->form_validation->set_rules('entry_tag', 'Tag', 'trim|is_natural');
		$this->form_validation->set_rules('forward_refrence_id', 'Forward Refrence Id', 'trim');
                $this->form_validation->set_rules('backward_refrence_id', 'Backward Refrence Id', 'trim');
		$this->form_validation->set_rules('entry_name', 'Entry Type', 'trim|required');
		/* Debit and Credit amount validation */
		if ($_POST)
		{
			foreach ($this->input->post('ledger_dc', TRUE) as $id => $ledger_data)
			{
				$this->form_validation->set_rules('dr_amount[' . $id . ']', 'Debit Amount', 'trim|currency');
				$this->form_validation->set_rules('cr_amount[' . $id . ']', 'Credit Amount', 'trim|currency');
			}
		}

		/* Repopulating form */
		if ($_POST)
		{
			$data['entry_number']['value'] = $this->input->post('entry_number', TRUE);
			$data['entry_date']['value'] = $this->input->post('entry_date', TRUE);
			$data['entry_narration']['value'] = $this->input->post('entry_narration', TRUE);
			$data['entry_tag'] = $this->input->post('entry_tag', TRUE);
			$data['has_reconciliation'] = $this->input->post('has_reconciliation', TRUE);
			$data['active_entry_name'] = $this->input->post('entry_name', TRUE);
			$data['ledger_dc'] = $this->input->post('ledger_dc', TRUE);
			$data['ledger_id'] = $this->input->post('ledger_id', TRUE);
			$data['dr_amount'] = $this->input->post('dr_amount', TRUE);
			$data['cr_amount'] = $this->input->post('cr_amount', TRUE);
			$data['forward_refrence_id']['value'] = $this->input->post('forward_refrence_id', TRUE);
                        $data['backward_refrence_id']['value'] = $this->input->post('backward_refrence_id', TRUE);
			$data['bank_name']['value'] = $this->input->post('bank_name', TRUE);
                        $data['banif_name']['value'] = $this->input->post('banif_name', TRUE);
                        $data['cheque'] = $this->input->post('cheque', TRUE);
			$data['fund_list_active'] = $this->input->post('fund_list', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'entry/edit', $data);
		} else	{
			/* Checking for Valid Ledgers account and Debit and Credit Total */
			$data_all_ledger_id = $this->input->post('ledger_id', TRUE);
			$data_all_ledger_dc = $this->input->post('ledger_dc', TRUE);
			$data_all_dr_amount = $this->input->post('dr_amount', TRUE);
			$data_all_cr_amount = $this->input->post('cr_amount', TRUE);
			$data_banif_name = $this->input->post('banif_name', TRUE);
                        $data_bank_name = $this->input->post('bank_name', TRUE);
                        $data_entry_name = $this->input->post('entry_name', TRUE);
                        $data_date = $this->input->post('entry_date', TRUE);
                        $data_cheque = $this->input->post('cheque', TRUE);
                        $data_date = date_php_to_mysql($data_date); // Converting date to MySQL


			$dr_total = 0;
			$cr_total = 0;
			$bank_cash_present = FALSE; /* Whether atleast one Ledger account is Bank or Cash account */
			$non_bank_cash_present = FALSE;  /* Whether atleast one Ledger account is NOT a Bank or Cash account */
			/* Updating main entry */
                        if ($current_entry_type['numbering'] == '3') {
                                $data_number = $this->input->post('entry_number', TRUE);
                                if ( ! $data_number)
                                        $data_number = NULL;
                        } else {
                                $data_number = $this->input->post('entry_number', TRUE);
                        }

			foreach ($data_all_ledger_dc as $id => $ledger_data)
			{
				if ($data_all_ledger_id[$id] < 1)
					continue;

				/* Check for valid ledger id */
				$this->db->from('ledgers')->where('id', $data_all_ledger_id[$id]);

				//lines added by Priyanka
                                //$ledger_array = explode('#', $data_all_ledger_id[$id]);
                                //$ledger_id = $ledger_array[0];
                                //$ledger_code = $ledger_array[1];
                                //if ($ledger_id < 1)
                                 //       continue;
                                //$ledger_code = $ledger_array[1];
                                //$this->db->from('ledgers')->where('id', $ledger_id);

				$valid_ledger_q = $this->db->get();
				if ($valid_ledger_q->num_rows() < 1)
				{
					$this->messages->add('Invalid Ledger account.', 'error');
					$this->template->load('template', 'entry/edit', $data);
					return;
				}
				// these checks existed earlier 
				/*else {
					/* Check for valid ledger type 
					$valid_ledger = $valid_ledger_q->row();
					if ($current_entry_type['bank_cash_ledger_restriction'] == '2')
					{
						if ($data_all_ledger_dc[$id] == 'D' && $valid_ledger->type == 1)
						{
							$bank_cash_present = TRUE;
						}
						if ($valid_ledger->type != 1)
							$non_bank_cash_present = TRUE;
					} else if ($current_entry_type['bank_cash_ledger_restriction'] == '3')
					{
						if ($data_all_ledger_dc[$id] == 'C' && $valid_ledger->type == 1)
						{
							$bank_cash_present = TRUE;
						}
						if ($valid_ledger->type != 1)
							$non_bank_cash_present = TRUE;
					} else if ($current_entry_type['bank_cash_ledger_restriction'] == '4')
					{
						if ($valid_ledger->type != 1)
						{
							$this->messages->add('Invalid Ledger account. ' . $current_entry_type['name'] . ' Entries can have only Bank and Cash Ledgers accounts.', 'error');
							$this->template->load('template', 'entry/edit', $data);
							return;
						}
					} else if ($current_entry_type['bank_cash_ledger_restriction'] == '5')
					{
						if ($valid_ledger->type == 1)
						{
							$this->messages->add('Invalid Ledger account. ' . $current_entry_type['name'] . ' Entries cannot have Bank and Cash Ledgers accounts.', 'error');
							$this->template->load('template', 'entry/edit', $data);
							return;
						}
					}
				}*/
				if ($data_all_ledger_dc[$id] == "D")
				{
					$dr_total = float_ops($data_all_dr_amount[$id], $dr_total, '+');
				} else {
					$cr_total = float_ops($data_all_cr_amount[$id], $cr_total, '+');
				}
			}
			if (float_ops($dr_total, $cr_total, '!='))
			{
				$this->messages->add('Debit and Credit Total does not match!', 'error');
				$this->template->load('template', 'entry/edit', $data);
				return;
			} else if (float_ops($dr_total, 0, '==') || float_ops($cr_total, 0, '==')) {
				$this->messages->add('Cannot save empty Entry.', 'error');
				$this->template->load('template', 'entry/edit', $data);
				return;
			}

			// these checks existed earlier
			/* Check if atleast one Bank or Cash Ledger account is present */
			/*if ($current_entry_type['bank_cash_ledger_restriction'] == '2')
			{
				if ( ! $bank_cash_present)
				{
					$this->messages->add('Need to Debit atleast one Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/edit', $data);
					return;
				}
				if ( ! $non_bank_cash_present)
				{
					$this->messages->add('Need to Debit or Credit atleast one NON - Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/edit', $data);
					return;
				}
			} else if ($current_entry_type['bank_cash_ledger_restriction'] == '3')
			{
				if ( ! $bank_cash_present)
				{
					$this->messages->add('Need to Credit atleast one Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/edit', $data);
					return;
				}
				if ( ! $non_bank_cash_present)
				{
					$this->messages->add('Need to Debit or Credit atleast one NON - Bank or Cash account.', 'error');
					$this->template->load('template', 'entry/edit', $data);
						return;
				}
			}*/


			$data_forw_refrence = $this->input->post('forward_refrence_id', TRUE);
                        $data_back_refrence = $this->input->post('backward_refrence_id', TRUE);
			$data_date = $this->input->post('entry_date', TRUE);
			$data_narration = $this->input->post('entry_narration', TRUE);
			$data_tag = $this->input->post('entry_tag', TRUE);
			if ($data_tag < 1)
				$data_tag = NULL;
			$this->db->select('id')->from('entry_types')->where('name', $data_entry_name);
                                $query1 = $this->db->get();
                                foreach($query1->result() as $row1)
                                {
                                        $id = $row1->id;
                                }

		//	$data_type = $entry_type_id;
			$data_date = date_php_to_mysql($data_date); // Converting date to MySQL
			$data_has_reconciliation = $this->input->post('has_reconciliation', TRUE);
			
//			$dateTime = new DateTime();
//        		$updatedate = $dateTime->format("Y-m-d 00:00:00");
			$updatedate = date_php_to_mysql(date_today_php());
                        $this->db->trans_start();
			$update_data = array(
				'number' => $data_number,
				'date' => $data_date,
				'entry_type' => $id,
				'narration' => $data_narration,
				'tag_id' => $data_tag,
				'update_date' => $updatedate,
				'modifiedvalue'=> $previousvalue,
				'forward_refrence_id' => $data_forw_refrence,
                                'backward_refrence_id' => $data_back_refrence
			);
			if ( ! $this->db->where('id', $entry_id)->update('entries', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Entry account.', 'error');
				$this->logger->write_message("error", "Error updating entry details for " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " [id:" . $entry_id . "]");
				$this->template->load('template', 'entry/edit', $data);
				return;
			}

			/* TODO : Deleting all old ledger data, Bad solution */
			if ( ! $this->db->delete('entry_items', array('entry_id' => $entry_id)))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error deleting previous Ledger accounts from Entry.', 'error');
				$this->logger->write_message("error", "Error deleting previous entry items for " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " [id:" . $entry_id . "]");
				$this->template->load('template', 'entry/edit', $data);
				return;
			}
			
			/* Adding ledger accounts */
			$data_all_ledger_dc = $this->input->post('ledger_dc', TRUE);
			$data_all_ledger_id = $this->input->post('ledger_id', TRUE);
			$data_all_dr_amount = $this->input->post('dr_amount', TRUE);
			$data_all_cr_amount = $this->input->post('cr_amount', TRUE);
			$data_all_fund_ledger = $this->input->post('fund_list', TRUE);

			$dr_total = 0;
			$cr_total = 0;
			foreach ($data_all_ledger_dc as $id => $ledger_data)
			{
				$data_ledger_dc = $data_all_ledger_dc[$id];
				$data_ledger_id = $data_all_ledger_id[$id];

				//lines added by Priyanka
                                //$ledger_array = explode('#', $data_ledger_id);
                                //$ledger_id = $ledger_array[0];
                                //$ledger_code = $ledger_array[1];

                                //if ($ledger_id < 1)
                                  //      continue;

				if ($data_ledger_id < 1)
					continue;

				$data_amount = 0;
				if ($data_all_ledger_dc[$id] == "D")
				{
					$data_amount = $data_all_dr_amount[$id];
					$dr_total = float_ops($data_all_dr_amount[$id], $dr_total, '+');
				} else {
					$data_amount = $data_all_cr_amount[$id];
					$cr_total = float_ops($data_all_cr_amount[$id], $cr_total, '+');
				}

				/* Code for making entry in Fund and Transit Income account. */
                                $fund_ledger = $data_all_fund_ledger[$id];
                                if($fund_ledger > 0 && $data_ledger_dc == 'D'){
                                        $insert_fund_data = array(
                                                'entry_id' => $entry_id,
                                                'ledger_id' => $fund_ledger,
                                                'amount' => $data_amount,
                                                'dc' => 'D',
                                                'update_date' => $updatedate,
                                                'forward_refrence_id' => $data_forw_refrence,
                                                'backward_refrence_id' => $data_back_refrence
                                        );

                                        if ( ! $this->db->insert('entry_items', $insert_fund_data))
                                        {
                                                $this->db->trans_rollback();
                                                $this->logger->write_message("error", "Error adding fund id:" . $fund_ledger);
                                        }

                                        $this->db->select('id');
                                        $this->db->from('ledgers')->where('name', 'Transit Income');
                                        $query = $this->db->get();
                                        $income = $query->row();
                                        $income_id = $income->id;

                                        $insert_income_data = array(
                                                'entry_id' => $entry_id,
                                                'ledger_id' => $income_id,
                                                'amount' => $data_amount,
                                                'dc' => 'C',
                                                'update_date' => $updatedate,
                                                'forward_refrence_id' => $data_forw_refrence,
                                                'backward_refrence_id' => $data_back_refrence
                                        );

                                        if ( ! $this->db->insert('entry_items', $insert_income_data))
                                        {
                                                $this->db->trans_rollback();
                                                $this->logger->write_message("error", "Error adding transit income");
                                        }
                                }//....

				$insert_ledger_data = array(
					'entry_id' => $entry_id,	
					'ledger_id' => $data_ledger_id,
					'forward_refrence_id' => $data_forw_refrence,
                                        'backward_refrence_id' => $data_back_refrence,
					'amount' => $data_amount,
					'dc' => $data_ledger_dc,
					'update_date' => $updatedate,
				);
				if ( ! $this->db->insert('entry_items', $insert_ledger_data))
				{
					$this->db->trans_rollback();
					$this->messages->add('Error adding Ledger account - ' . $data_ledger_id . ' to Entry.', 'error');
					$this->logger->write_message("error", "Error adding Ledger account item [id:" . $data_ledger_id . "] for " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " [id:" . $entry_id . "]");
					$this->template->load('template', 'entry/edit', $data);
					return;
				}
			}

			/* Updating Debit and Credit Total in entries table */
			$update_data = array(
				'dr_total' => $dr_total,
				'cr_total' => $cr_total,
			);
			//print_r($update_data);
			if ( ! $this->db->where('id', $entry_id)->update('entries', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Entry total.', 'error');
				$this->logger->write_message("error", "Error updating entry total for " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " [id:" . $entry_id . "]");
				$this->template->load('template', 'entry/edit', $data);
				return;
			}
			//$uname=$this->session->userdata('user_name');
			$verifyuser = array(
                                'verified_by' => $loginname,
                                'status' => 1,
                        );
                        if ( ! $this->db->where('id', $entry_id)->update('entries', $verifyuser))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error verify Entry .', 'error');
				redirect('entry/show/' . $entry_type);
                                return;
                        }
			foreach ($data_all_ledger_dc as $id => $ledger_data)
                        {
                                if ($data_all_ledger_id[$id] < 1)
                                        continue;

                                $this->db->from('ledgers')->where('id', $data_all_ledger_id[$id]);
                                $valid_ledger_q = $this->db->get();
                                $valid_ledger = $valid_ledger_q->row();
                                if ($valid_ledger_q->num_rows() < 1)
                                {
                                        $this->messages->add('Invalid Ledger account.', 'error');
                                        $this->template->load('template', 'entry/add', $data);
                                        return;
                                }
                                                $val=$data_cheque[$id];
                                                $valid_ledger = $valid_ledger_q->row();
                                                if ($data_all_ledger_dc[$id] == 'D' && $valid_ledger->type == 1)
                                                {

                                                        $bank_cash_present = TRUE;
                                                        $this->db->trans_start();
                                                        $update_data1 = array(
                                                                'Bank_name'=>$data_bank_name,
                                                                'amount' => $data_amount,
                                                                'name' => $data_banif_name,
                                                                'dc'=>'D',
                                                                'cheque_date'=>$data_date,
                                                                'cheque_no' => $val,
                                                                );

                                                        if ( ! $this->db->where('entry_no', $entry_id)->update('reconcilation', $update_data1))
                                                        {
                                                                $this->db->trans_rollback();
                                                                $this->messages->add('Error addding Entry.', 'error');
								$this->template->load('template', 'entry/add', $data);
                                                                return;
                                                                } else {
                                                                $this->db->trans_complete();
                                                                }

                                                }
                                         if ($valid_ledger->type != 1)
                                                $non_bank_cash_present = TRUE;
                                                if ($data_all_ledger_dc[$id] == 'C' && $valid_ledger->type == 1)
                                                {
                                                        $bank_cash_present = TRUE;
                                                        $this->db->trans_start();
                                                        $update_data2 = array(
                                                                'Bank_name'=>$data_bank_name,
                                                                'amount' => $data_amount,
                                                                'name' => $data_banif_name,
                                                                'dc'=>'C',
                                                                'cheque_date'=>$data_date,
                                                                'cheque_no' => $val,
                                                                );

                                                        if ( ! $this->db->where('entry_no', $entry_id)->update('reconcilation', $update_data2))
                                                        {
                                                           	$this->db->trans_rollback();
                                                                $this->messages->add('Error addding Entry', 'error');
                                                                $this->template->load('template', 'entry/add', $data);
                                                                return;
                                                                } else {
                                                                $this->db->trans_complete();
                                                                }

                                                }

				}
			/* Success */
			$this->db->trans_complete();

			$this->session->set_userdata('entry_updated_show_action', TRUE);
			$this->session->set_userdata('entry_updated_id', $entry_id);
			$this->session->set_userdata('entry_updated_type_id', $entry_type_id);
			$this->session->set_userdata('entry_updated_type_label', $current_entry_type['label']);
			$this->session->set_userdata('entry_updated_type_name', $current_entry_type['name']);
			$this->session->set_userdata('entry_updated_number', $data_number);
			if ($data_has_reconciliation)
				$this->session->set_userdata('entry_updated_has_reconciliation', TRUE);
			else
				$this->session->set_userdata('entry_updated_has_reconciliation', FALSE);

			/* Showing success message in show() method since message is too long for storing it in session */
			$this->logger->write_message("success", "Updated " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $data_number) . " [id:" . $entry_id . "]");

			redirect('entry/show/' . $current_entry_type['label']);
		//		$this->template->load('template', 'entry/edit', $data);
			return;
		}
		}
		return;
	}
	
	function delete($entry_type, $entry_id = 0)
	{
		/* Check access */
		if ( ! check_access('delete entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}

		$this->db->trans_start();
		if ( ! $this->db->delete('entry_items', array('entry_id' => $entry_id)))
		{
			$this->db->trans_rollback();
			$this->messages->add('Error deleting Entry - Ledger accounts.', 'error');
			$this->logger->write_message("error", "Error deleting ledger entries for " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $cur_entry->number) . " [id:" . $entry_id . "]");
			redirect('entry/view/' . $current_entry_type['label'] . '/' . $entry_id);
			return;
		}
		if ( ! $this->db->delete('entries', array('id' => $entry_id)))
		{
			$this->db->trans_rollback();
			$this->messages->add('Error deleting Entry entry.', 'error');
			$this->logger->write_message("error", "Error deleting Entry entry for " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $cur_entry->number) . " [id:" . $entry_id . "]");
			redirect('entry/view/' . $current_entry_type['label'] . '/' . $entry_id);
			return;
		}
		$this->db->trans_complete();
		$this->messages->add('Deleted ' . $current_entry_type['name'] . ' Entry.', 'success');
		$this->logger->write_message("success", "Deleted " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $cur_entry->number) . " [id:" . $entry_id . "]");
		redirect('entry/show/' . $current_entry_type['label']);
		return;
	}

	function download($entry_type, $entry_id = 0)
	{
		$this->load->helper('download');
		$this->load->model('Setting_model');
		$this->load->model('Ledger_model');

		/* Check access */
		if ( ! check_access('download entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}

		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;
		$data['entry_number'] =  $cur_entry->number;
		$data['entry_date'] = date_mysql_to_php_display($cur_entry->date);
		$data['entry_dr_total'] =  $cur_entry->dr_total;
		$data['entry_cr_total'] =  $cur_entry->cr_total;
		$data['entry_narration'] = $cur_entry->narration;
		$data['forward_ref_id'] = $cur_entry->forward_refrence_id;
		$data['back_ref_id'] = $cur_entry->backward_refrence_id;
		$data['submitted_by'] = $cur_entry->submitted_by;
                $data['verified_by'] = $cur_entry->verified_by;
		

		/* Getting Ledger details */
		$this->db->from('entry_items')->where('entry_id', $entry_id)->order_by('dc', 'desc');
		$ledger_q = $this->db->get();
		$counter = 0;
		$data['ledger_data'] = array();
		if ($ledger_q->num_rows() > 0)
		{
			foreach ($ledger_q->result() as $row)
			{
				$data['ledger_data'][$counter] = array(
					'id' => $row->ledger_id,
					'name' => $this->Ledger_model->get_name($row->ledger_id),
					'dc' => $row->dc,
					'amount' => $row->amount,
					'id'=>$entry_id,
				);
				$counter++;
			}
		}

		/* Download Entry */
		$file_name = $current_entry_type['name'] . '_entry_' . $cur_entry->number . ".html";
		$download_data = $this->load->view('entry/downloadpreview', $data, TRUE);
		force_download($file_name, $download_data);
		return;
	}

	function printpreview($entry_type, $entry_id = 0)
	{
		$this->load->model('Setting_model');
		$this->load->model('Ledger_model');

		/* Check access */
		if ( ! check_access('print entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}

		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;
		$data['entry_number'] =  $cur_entry->number;
		$data['entry_date'] = date_mysql_to_php_display($cur_entry->date);
		$data['entry_dr_total'] =  $cur_entry->dr_total;
		$data['entry_cr_total'] =  $cur_entry->cr_total;
		$data['entry_narration'] = $cur_entry->narration;
		$data['forward_ref_id'] = $cur_entry->forward_refrence_id;
                $data['back_ref_id'] = $cur_entry->backward_refrence_id;
		$data['submitted_by'] = $cur_entry->submitted_by;
                $data['verified_by'] = $cur_entry->verified_by;

		/* Getting Ledger details */
		$this->db->from('entry_items')->where('entry_id', $entry_id)->order_by('dc', 'desc');
		$ledger_q = $this->db->get();
		$counter = 0;
		$data['ledger_data'] = array();
		if ($ledger_q->num_rows() > 0)
		{
			foreach ($ledger_q->result() as $row)
			{
				$data['ledger_data'][$counter] = array(
					'id' => $row->ledger_id,
					'name' => $this->Ledger_model->get_name($row->ledger_id),
					'dc' => $row->dc,
					'amount' => $row->amount,
					'id'=>$entry_id,
				);
				$counter++;
			}
		}

		$this->load->view('entry/printpreview', $data);
		return;
	}

	function cheque_print($entry_type, $entry_id = 0, $cheque_type)
        {
                $data['cheque_type'] = $cheque_type;
                $data['entry_id'] = $entry_id;
                $data['entry_type'] = $entry_type;
                /* Getting Ledger details */
                $this->db->from('entry_items')->where('entry_id', $entry_id)->order_by('dc', 'desc');
                $ledger_q = $this->db->get();
                $counter = 0;
                $data['ledger_data'] = array();
                if ($ledger_q->num_rows() > 0)
                {
                        foreach ($ledger_q->result() as $row)
                        {
                                $data['ledger_data'][$counter] = array(
                                        'id' => $row->ledger_id,
                                        'name' => $this->Ledger_model->get_name($row->ledger_id),
                                        'dc' => $row->dc,
                                        'amount' => $row->amount,
                                        'id'=>$entry_id,
                                        'cheque_type'=>$cheque_type,
                                );
                                $counter++;
                        }
                }

                $this->load->view('entry/cheque_print', $data);
                return;
        }


	function email($entry_type, $entry_id = 0)
	{
		$this->load->model('Setting_model');
		$this->load->model('Ledger_model');
		$this->load->library('email');

		/* Check access */
		if ( ! check_access('email entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Entry Type */
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		$account_data = $this->Setting_model->get_current();

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}

		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;
		$data['entry_id'] = $entry_id;
		$data['entry_number'] = $cur_entry->number;
		$data['forward_ref_id'] = $cur_entry->forward_refrence_id;
                $data['back_ref_id'] = $cur_entry->backward_refrence_id;
		$data['email_to'] = array(
			'name' => 'email_to',
			'id' => 'email_to',
			'size' => '40',
			'value' => '',
		);

		/* Form validations */
		$this->form_validation->set_rules('email_to', 'Email to', 'trim|valid_emails|required');

		/* Repopulating form */
		if ($_POST)
		{
			$data['email_to']['value'] = $this->input->post('email_to', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$data['error'] = validation_errors();
			$this->load->view('entry/email', $data);
			return;
		}
		else
		{
			$entry_data['entry_type_id'] = $entry_type_id;
			$entry_data['current_entry_type'] = $current_entry_type;
			$entry_data['entry_number'] =  $cur_entry->number;
			$entry_data['entry_date'] = date_mysql_to_php_display($cur_entry->date);
			$entry_data['entry_dr_total'] =  $cur_entry->dr_total;
			$entry_data['entry_cr_total'] =  $cur_entry->cr_total;
			$entry_data['entry_narration'] = $cur_entry->narration;
			$entry_data['forward_ref_id'] = $cur_entry->forward_refrence_id;
	                $entry_data['back_ref_id'] = $cur_entry->backward_refrence_id;
	
			/* Getting Ledger details */
			$this->db->from('entry_items')->where('entry_id', $entry_id)->order_by('dc', 'desc');
			$ledger_q = $this->db->get();
			$counter = 0;
			$entry_data['ledger_data'] = array();
			if ($ledger_q->num_rows() > 0)
			{
				foreach ($ledger_q->result() as $row)
				{
					$entry_data['ledger_data'][$counter] = array(
						'id' => $row->ledger_id,
						'name' => $this->Ledger_model->get_name($row->ledger_id),
						'dc' => $row->dc,
						'amount' => $row->amount,
					);
					$counter++;
				}
			}

			/* Preparing message */
			$message = $this->load->view('entry/emailpreview', $entry_data, TRUE);

			/* Getting email configuration */
			$config['smtp_timeout'] = '30';
			$config['charset'] = 'utf-8';
			$config['newline'] = "\r\n";
			$config['mailtype'] = "html";
			if ($account_data)
			{
				$config['protocol'] = $account_data->email_protocol;
				$config['smtp_host'] = $account_data->email_host;
				$config['smtp_port'] = $account_data->email_port;
				$config['smtp_user'] = $account_data->email_username;
				$config['smtp_pass'] = $account_data->email_password;
			} else {
				$data['error'] = 'Invalid account settings.';
			}
			$this->email->initialize($config);

			/* Sending email */
			$this->email->from('', 'Webzash');
			$this->email->to($this->input->post('email_to', TRUE));
			$this->email->subject($current_entry_type['name'] . ' Bill/Voucher No. ' . full_entry_number($entry_type_id, $cur_entry->number));
			$this->email->message($message);
			if ($this->email->send())
			{
				$data['message'] = "Email sent.";
				$this->logger->write_message("success", "Emailed " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $cur_entry->number) . " [id:" . $entry_id . "]");
			} else {
				$data['error'] = "Error sending email. Check you email settings.";
				$this->logger->write_message("error", "Error emailing " . $current_entry_type['name'] . " Bill/Voucher number " . full_entry_number($entry_type_id, $cur_entry->number) . " [id:" . $entry_id . "]");
			}
			$this->load->view('entry/email', $data);
			return;
		}
		return;
	}

	function addrow($add_type = 'all')
	{
		
		$i = time() + rand  (0, time()) + rand  (0, time()) + rand  (0, time());
		$dr_amount = array(
			'name' => 'dr_amount[' . $i . ']',
			'id' => 'dr_amount[' . $i . ']',
			'maxlength' => '15',
			'size' => '15',
			'value' => '',
			'class' => 'dr-item',
			'disabled' => 'disabled',
		);
		$cr_amount = array(
			'name' => 'cr_amount[' . $i . ']',
			'id' => 'cr_amount[' . $i . ']',
			'maxlength' => '15',
			'size' => '15',
			'value' => '',
			'class' => 'cr-item',
			'disabled' => 'disabled',
		);
		$cheque = array(
                        'name' => 'cheque[' . $i . ']',
                        'id' => 'cheque[' . $i . ']',
                        'maxlength' => '15',
                        'size' => '15',
                        'disabled'    => 'disable',
                        'value' => '',
                        'class' => 'cheque-item',
                );


		echo '<tr class="new-row">';
		echo '<td>';
		echo form_dropdown_dc('ledger_dc[' . $i . ']');
		echo '</td>';

		echo '<td>';
	//	if ($add_type == 'bankcash')
	//		echo form_input_ledger('ledger_id[' . $i . ']', 0, '', $type = 'bankcash');
	//	else if ($add_type == 'nobankcash')
	//		echo form_input_ledger('ledger_id[' . $i . ']', 0, '', $type = 'nobankcash');
	//	else
			echo form_input_ledger('ledger_id[' . $i . ']');
		echo '</td>';

		echo '<td>';
		echo form_input($dr_amount);
		echo '</td>';
		echo '<td>';
		echo form_input($cr_amount);
		echo '</td>';
		echo '<td>';
              	echo form_input($cheque);
              	echo '</td>';

		echo '<td>';
		echo img(array('src' => asset_url() . "images/icons/add.png", 'border' => '0', 'alt' => 'Add Ledger', 'class' => 'addrow'));
		echo '</td>';
		echo '<td>';
		echo img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Remove Ledger', 'class' => 'deleterow'));
		echo '</td>';
		echo '<td class="ledger-balance"><div></div>';
		echo '</td>';
		echo '</tr>';
		return;
	}
	function sort($entry_sort)
	{
		$this->load->model('Tag_model');
		$data['tag_id'] = 0;
		$entry_type_id = 0;
		if ($entry_sort == 'all') {
                        $entry_type_id = 0;
			$this->template->set('page_title', 'All Entries');
			$this->template->set('nav_links', array('entry/printallentry' => 'Print All Entry'));
		}else{	
		$entry_type_id = entry_type_name_to_id($entry_sort);
		}
		if ($entry_sort != 'all') {
		$current_entry_type = entry_type_info($entry_type_id);
		$this->template->set('page_title', $current_entry_type['name'] . ' Entries');
		$this->template->set('nav_links', array('entry/add/' . $current_entry_type['label'] => 'New ' . $current_entry_type['name'] . ' Entry', 'entry/printentry/' . $current_entry_type['label'] => 'Print ' . $current_entry_type['name'] . ' Entry'));
		}
		/* Pagination setup */
		
		$this->load->library('pagination');
		$page_count = (int)$this->uri->segment(4);
		$page_count = $this->input->xss_clean($page_count);
		if ( ! $page_count)
			$page_count = "0";

		/* Pagination configuration */
		if ($entry_sort == 'all') {
                        $config['base_url'] = site_url('entry/sort/all');
                        $config['uri_segment'] = 4;
		}else{
		$config['base_url'] = site_url('entry/sort/' . $current_entry_type['label']);
		$config['uri_segment'] = 4;
		}
		$pagination_counter = $this->config->item('row_count');
		$config['num_links'] = 10;
		$config['per_page'] = $pagination_counter;
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
		if($entry_sort == 'all'){
			$this->db->from('entries')->order_by('update_date', 'desc')->limit($pagination_counter, $page_count);
			$entry_q = $this->db->get();
			$config['total_rows'] = $this->db->count_all('entries');
		}
		else{
			$this->db->from('entries')->where('entry_type', $entry_type_id)->order_by('update_date', 'desc')->limit($pagination_counter, $page_count);
			$entry_q = $this->db->get();
			$config['total_rows'] = $this->db->from('entries')->where('entry_type', $entry_type_id)->get()->num_rows();
		}
		$data['entry_data'] = $entry_q;
		$data['entry_sort'] = $entry_sort;
		$this->pagination->initialize($config);

		$text = '';
		$fy_start = '';
		$fy_end = '';
		if (date('n') > 3)
		{
			$fy_start = date('Y');
			$fy_end = date('Y') + 1;
		} else {
			$fy_start = date('Y') - 1;
			$fy_end = date('Y');
		}
		$data['search'] = '';
		$data['entry_path'] = "sort/" . $entry_sort;
		if ($entry_sort) {
			$data['search_by'] = array(
				"Select" => "Select",
		                "id" => "Id",
		                "forward_refrence_id"=> "Fwd Ref Id",
				"backward_refrence_id"=> "Bkwd Ref Id",
		                "date"=> "Date",
		                "update_date"=> "Update Date",
		                "number"=> "No",
		                "name"=> "Ledger Account",
		                "narration"=> "Narration",
		                "entry_type"=> "Type",
		                "dr_total"=> "DR Amount",
		                "cr_total"=> "CR Amount",
		                "submitted_by"=> "Submitted By",
		                "verified_by"=> "Verified By",
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
			
			/* Form Validation */
			$this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
			$this->form_validation->set_rules('text', 'Text', 'trim|required');
			/* Validating form */

			if ($this->form_validation->run() == FALSE)
			{
				$this->messages->add(validation_errors(), 'error');
				$this->template->load('template', 'entry/index', $data);
				return;
			}
			else
			{
				$data_search_by = $this->input->post('search_by', TRUE);
				$data_text = $this->input->post('text', TRUE);
			}
			if($data_search_by == "Select")
			{
				$this->messages->add('Please select search type from dropdown list.', 'error');
			}
			else {
				if($data_search_by == "id" || $data_search_by == "forward_refrence_id" || $data_search_by == "backward_refrence_id" || $data_search_by == "number") {
					// id, forward ref.id, backward ref.id and number should be a number
					if(! ctype_digit($data_text)) {
						$this->messages->add('Please enter a number.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				if($data_search_by == "entry_type")
				{
					// entry type should be alphabatic
					if(ctype_alpha($data_text)) {
						$str = strtolower($data_text);
						$data_text = entry_type_name_to_id($str);
						// if entry type is not a valid name
						if( $data_text == "") {
							$this->messages->add("Invalid entry type.", 'error');
							redirect('entry/' . $data['entry_path']);
						}
					}
					else {
						$this->messages->add('Please enter a valid entry type.', 'error');
						redirect('entry/' . $data['entry_path']);
					}				
				}
				if($data_search_by == "dr_total" || $data_search_by == "cr_total") {
					// if debit and credit total is not digit and decimal
					if(! is_numeric($data_text)) {
						$this->messages->add('Please enter a number.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				if($data_search_by == "submitted_by" || $data_search_by == "verified_by") {
					// if submitter and verifier is not alphanumeric
					if(! ctype_alnum($data_text)) {
						$this->messages->add('Please enter alphanumeric value.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				if($data_search_by == "date" || $data_search_by == "update_date")
				{
					// if date and update date is digit
					if(ctype_digit($data_text)) {
						$field = $data_search_by . '      ' . 'LIKE';
					}
					else {
						$date=explode(' ', $data_text);
						// if date and update format is dd mm yy only
						if(count($date)>1) {
						// if date and update date are two digit
							if(count($date) == '2') {
								// if month of date and update date is alphabatic
								if(ctype_alpha($date['1'])) {
									if(strtotime($date['1'])) {
										$month = $date['1'];
										$x = date('m', strtotime($month));
										$data_text = $x . "-" . $date[0];
										$field = $data_search_by . '      ' . 'LIKE';
									}
									else {
										$this->messages->add('Invalid Month.', 'error');
										redirect('entry/' . $data['entry_path']);
									}
								}
								// if month of date and update date is numeric
								else if(ctype_digit($date['1'])) {
									// if month of date and update date is valid month
									if("1" <= $date['1'] && $date['1'] <= "12") {
										$field = $data_search_by . '      ' . 'LIKE';
										$data_text = $date[1]. "-" . $date[0];
									}
									else {
										$this->messages->add('Invalid Month.', 'error');
										redirect('entry/' . $data['entry_path']);
									}
								}
								else {
									$this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
									redirect('entry/' . $data['entry_path']);
								}
							}
							// if date and update date are three digit
							if(count($date) == '3') {
								$date0 = $date['0'];
								$x = $date['1'];
								$date2 = $date['2'];
								// if month of date and update date is alphabatic
								if(ctype_alpha($date['1'])) {
									$month = $date['1'];
									$x = date('m', strtotime($month));
									$data_text = $date[2]. "-" . $x . "-" . $date[0];
								}
								$data_text = $date0. "-" . $x . "-" . $date2;
								// if date and update date are a valid date
								$valid_date = checkdate($x,$date0,$date2);
								if($valid_date == 'true') {
									// if date and update date are exist in financial year
									if($date2 == $fy_start || $date2 == $fy_end) {
										$data_text = $date2."-". $x ."-".$date0;
									}
									else {
										$this->messages->add($data_text . ' does not exist in financial year.', 'error');
										redirect('entry/' . $data['entry_path']);
									}
								}
								else {
									$this->messages->add($data_text . ' is invalid date.', 'error');
									redirect('entry/' . $data['entry_path']);
								}	
							}
						}
						else {
							$this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
							redirect('entry/' . $data['entry_path']);
						}
					}		
				}
				$field = $data_search_by . '      ' . 'LIKE';
				$text = $data_text;
				// if search type is not name
				if($data_search_by != "name") {
					$this->db->from('entries')->where($field, '%' . $text . '%')->order_by('date', 'desc')->order_by('id', 'desc');
					$entry_q = $this->db->get();
					if( $entry_q->num_rows() < 1 )
					{
						$this->messages->add($text . ' is not found.', 'error');
						redirect('entry/' . $data['entry_path']);
					}
				}
				else {
					$this->db->from('entries');
                                        $entry_q = $this->db->get();
				}
			}
		}
		$data['search'] = $data_search_by;
		$data['entry_data'] = $entry_q;
		$data['entry_sort'] = $entry_sort;
		$data['entry_path'] = "sort/" . $entry_sort;
                $this->template->load('template', 'entry/index', $data);
                return;
	}

        function init1l($id,$data_amount,$data)
        {
		
        	$parent_id = 0;
	        $code = "";
		$this->load->library('GetParentlist');
                if ($id == 0)
                {
					
			$id = 0;
			$this->messages->add('Please Add atleast one parent group for this ledger entry for Payment','error');
			redirect('/entry/add/payment');
			return $id;
			
                } else {
                        $this->db->from('groups')->where('id', $id);
	                $group_q = $this->db->get();
                        $group = $group_q->row();
                        $this->parent_id = $group->parent_id;
                        $this->code = $group->code;
                        $this->db->from('budgets')->where('code', $this->code);
                        $query_l = $this->db->get();
                        $query_l = $query_l->num_rows();
                        if($query_l>0)
                        {

                                $budgetamt = 0;
                                $useamt = 0;
                                $allow = 0;
                                $ledg_code=$this->code;
                                $this->db->from('budgets')->where('code', $this->code);
                                $query_l = $this->db->get();
                                $query_l = $query_l->row();
                                $this->amt = $query_l->bd_balance;
                                $this->useamt = $query_l->consume_amount;
                                $this->allow=$query_l->allowedover;
                                $budgetamt=$this->amt;
                                $useamt=$this->useamt;
                                $allow=$this->allow;

                                /* if alloted budget amount is more than consume amount*/

                                if($budgetamt > $useamt)

                                {//if1
                                        $available_amount=$budgetamt - $useamt ;//its wrong

                                        /**  payment amount is greater than or equal to available amount **/
                                      if($data_amount > $available_amount)	
                                        {	
                                        	/* check for allowed over expense*/
                                                if(($allow == -1) || ($allow == 0))
                                                {
                                                        $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                        //$this->template->load('template','entry/add',$data);
                                                        redirect('entry/add/payment');
                                                        //redirect
                                                        return ;
                                                 }

                                                else
                                                 {
                                                 	/* check for payment amount by adding allowd over amount + consume amount */
                                                      $available_amount = $budgetamt - $useamt + $allow;
                                                        if($data_amount >= $available_amount)
                                                        {
                                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                                //$this->template->load('template', 'entry/add',$data);
								redirect('entry/add');
                                                                return ;

                                                         }
                                                         else
                                                         {
                                                                /* Update budget table */
                                                                $sumamt=$data_amount + $useamt;
                                                                $allow_left = $available_amount - $data_amount ;
                                                                $update_data1 = array('consume_amount' => $sumamt, 'allowedover' => $allow_left);
                                                                if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                                {
                                                                        $this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        //$this->template->load('template', 'entry/add', $data);
									redirect('entry/add/payment');
                                                                        return ;
                                                                }
                                                                $parents = new GetParentlist();
                                                                $parents->init($groupid,$data_amount);
                                                                //$this->db->from('budgets')->where('code', '50');
                                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                                $query_ll = $this->db->get();
                                                                $query_ll = $query_ll->row();
                                                                $this->amt1 = $query_ll->bd_balance;
                                                                $this->useamt1 = $query_ll->consume_amount;
                                                                $update_data2 = $this->useamt1 + $data_amount;
                                                                $update_data3 = array('consume_amount' => $update_data2);
                                                                //if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
                                                                if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                                {
                                                                        $this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        $this->template->load('template', 'entry/add', $data);
									redirect('entry/add/payment');
                                                                        return;
                                                                 }
								return;
							}
						}

					}
                                        else
                                        {
                                                $sumamt=$data_amount + $useamt;
                                                $update_data1 = array('consume_amount' => $sumamt );
                                               /* if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                	$this->messages->add("Test in Getparent 7==>");
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
							redirect('entry/add');
                                                        return;
                                                }*/
						//$this->messages->add('Line 1786 ===>'.$groupid.'===>'.$data_amount);
						//echo "1789";
                                                $parents = new GetParentlist();
                                                $parents->init($id,$data_amount);
						
                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                       		//$this->id = $query_l->id;
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);
						//echo "$update_data2";	
                                                //if (!$this->db->where('code', '50')->update('budgets', $update_data3))
                                                if (!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                	//$this->messages->add("Test in Getparent 8==>");
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
							redirect('entry/add/payment');
                                                        return;
                                                }

                                       	}
					//$this->template->load('template', 'entry/add', $data);
					//return $id;
				}//1    


				/* consume amount is greater than allocated budget amount*/
                              if($useamt >= $budgetamt)
                                {//2
                                        /* check for allowed over expenses */
                                      if(($allow == -1) || ($allow == 0))
                                        {
                                        	$this->messages->add('Budget is not sufficient to make this payment.','error');
                                        	//$this->template->load('template', 'entry/add',$data);
						redirect('entry/add/payment');
                                                return;
                                        }
                                        /** get over consume amount and check with allowed left **/


                                      $overconsume_amount = $useamt - $budgetamt ;
                                        /* payment amount is greater than allowed over amount*/
                                      if($data_amount > $allow)
                                        {


                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                //$this->template->load('template', 'entry/add',$data);
						redirect('entry/add/payment');
                                                return;
                                        }
                                        /* payment amount is less than allowed over amount*/
                                      	if($data_amount <= $allow)
                           	      	{
					
                                                $overconsume_amount = $useamt - $budgetamt ;
                                                $available_amount = $allow ;
                                                $allowed_left = $allow - $data_amount;
                                                $consume_amount = $useamt + $data_amount;
                                                $update_data1 = array('consume_amount' => $consume_amount, 'allowedover' => $allowed_left);
                                                if (!$this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
							redirect('entry/add/payment');
                                                        return;
                                                }
                                                $parents = new GetParentlist();
                                                $parents->init($groupid,$data_amount);
                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                        	//$this->id = $query_l->id;
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);

                                                
                                                //if(!$this->db->where('code', '50')->update('budgets', $update_data3))
                                                if(!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
							redirect('entry/add/payment');
                                                        return;
                                                }


					}


				}//2*/
		//	return $id;
                        }
                        else{

				$this->get_parent_groups($id,$data_amount,$data);
				
                        }
		return $id;
                }
	}//function

        function get_parent_groups($id1,$data_amount,$data)
        {	

		$parent_groups = array();
                $this->db->from('groups')->where('id', $id1);
                $parent_group_q = $this->db->get();
		
		
                foreach ($parent_group_q->result() as $row)
                {
                        $row->parent_id;
                        $row->code;
                       	$this->init1l($row->parent_id,$data_amount,$data);
                }
		//return $row->parent_id;
        }

	function printentry($entry_type)
	{
		/* Check access */
		if ( ! check_access('print selected entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Entry Type*/ 
		$entry_type_id = entry_type_name_to_id($entry_type);

		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type', 'error');
			redirect('entry/show/all');
			return;
		}else {

			$current_entry_type = entry_type_info($entry_type_id);
		}

		$this->template->set('page_title', 'Print ' . $current_entry_type['name'] . ' Entry');

		/* Form fields */
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
		} else {
			$default_start .= date('Y') - 1;
		}
		$data['entry_date1'] = array(
			'name' => 'entry_date1',
			'id' => 'entry_date1',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_start,
		);
		$data['entry_date2'] = array(
			'name' => 'entry_date2',
			'id' => 'entry_date2',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_today_php(),
		);

		$data['current_entry_type'] = $current_entry_type;
	  
		/* displaying entries of selected entry type */
 
		$data['detail'] = array();
		$this->db->select('id,tag_id,entry_type,number,date,dr_total,cr_total,narration,update_date')->from('entries')->where('entry_type', $entry_type_id)->order_by('date', 'desc')->order_by('number', 'desc');
		$query = $this->db->get();
                $data['detail']= $query;
		
		/* Repopulating form */
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);		
		} 
		/* Form validations */

                $this->form_validation->set_rules('entry_date1', 'Bill/Voucher Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Bill/Voucher Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'entry/printentry', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			/* converting date format(dd/mm/yy)to data format(y-m-d) */
	
			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];

			/* check for entry date */

			if( $date1 > $date2)
			{
				$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'error');
			}
				/* displaying values of selected date range */ 
			else
			{ 
				$data['detail'] = array();
				$this->db->from('entries');
				$this->db->select('id,tag_id,entry_type,number,date,dr_total,cr_total,narration,update_date');
				$this->db->where('entry_type', $entry_type_id)->order_by('date', 'desc');		
				$this->db->where('date >=', $date1);
				$this->db->where('date <=', $date2);
				$query = $this->db->get();
			        $data['detail']= $query;

				/* check entry of selected date range is available or not */
	  		
				if( $query->num_rows() < 1 )
				{
					$this->messages->add('There is no entry between ' . $date1 . ' and ' . $date2 . ' date.', 'success');
					$this->template->load('template', 'entry/printentry', $data);
					return;	
				}
			}
		$this->template->load('template', 'entry/printentry', $data);
		return;
		}
	}

	function printallentry($entry_type = 'all')
	{
		/* Check access */
		if ( ! check_access('print all entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('entry/show/' . $entry_type);
			return;
		}

	        $this->template->set('page_title', 'Print All Entry');

		/* Form fields */ 
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
		} else {
			$default_start .= date('Y') - 1;
		}
		$data['entry_date1'] = array(
			'name' => 'entry_date1',
			'id' => 'entry_date1',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_start,
		);
		$data['entry_date2'] = array(
			'name' => 'entry_date2',
			'id' => 'entry_date2',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_today_php(),
		);
 
		/* displaying entries of all entry type */ 
 
		$data['detail'] = array();
		$this->db->select('id,tag_id,entry_type,number,date,dr_total,cr_total,narration,update_date, forward_refrence_id, backward_refrence_id')->from('entries')->order_by('date', 'desc')->order_by('number', 'desc');
		$query = $this->db->get();
                $data['detail']= $query;

		/* Repopulating form */  
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);		
		} 
		/* Form validations */  

                $this->form_validation->set_rules('entry_date1', 'Bill/Voucher Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Bill/Voucher Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'entry/printallentry', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			/* converting date format(dd/mm/yy)to data format(y-m-d) */ 
	
			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			/* check for entry date */

			if( $date1 > $date2)
			{
				$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'error');
			}
			/* displaying values of selected date range */    
			else
			{ 
				$data['detail'] = array();
				$this->db->from('entries');
				$this->db->select('id,tag_id,entry_type,number,date,dr_total,cr_total,narration,update_date, forward_refrence_id, backward_refrence_id')->order_by('date', 'desc');
				$this->db->where('date >=', $date1);
				$this->db->where('date <=', $date2);
				$query = $this->db->get();
				$data['detail']= $query;
	  		
				if( $query->num_rows() < 1 )
				{
					$this->messages->add('There is no entry between ' . $date1 . ' and ' . $date2 . ' date.', 'success');
					$this->template->load('template', 'entry/printallentry', $data);
					return;	
				}
			}
		$this->template->load('template', 'entry/printallentry', $data);
		return;
		}
	}	
	function verify($entry_type, $entry_id = 0)
	{
		$entry_type_id = entry_type_name_to_id($entry_type);
		if ( ! $entry_type_id)
		{
			$this->messages->add('Invalid Entry type.', 'error');
			redirect('entry/show/all');
			return;
		} else {
			$current_entry_type = entry_type_info($entry_type_id);
		}

		$this->template->set('page_title', 'Verify ' . $current_entry_type['name'] . ' Entry');

		/* Load current entry details */
		if ( ! $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id))
		{
			$this->messages->add('Invalid Entry.', 'error');
			redirect('entry/show/' . $current_entry_type['label']);
			return;
		}

		//get 'Transit Income' id
                $this->db->select('id');
                $this->db->from('ledgers');
                $this->db->where('name', 'Transit Income');
                $query = $this->db->get();
                $income = $query->row();
                $income_id = $income->id;

		/* Load current entry details */
		$this->db->from('entry_items')->where('entry_id', $entry_id)->order_by('id', 'asc');
		$this->db->where('ledger_id !=', $income_id);
		$cur_entry_ledgers = $this->db->get();
		if ($cur_entry_ledgers->num_rows() < 1)
		{
			$this->messages->add('Entry has no associated Ledger accounts.', 'error');
		}
		$data['cur_entry'] = $cur_entry;
		$data['cur_entry_ledgers'] = $cur_entry_ledgers;
		$data['entry_type_id'] = $entry_type_id;
		$data['current_entry_type'] = $current_entry_type;

		/**
		 * Get reference ids from entries table
		 * Lines added by Priyanka
		 */
		
		$data['forward_reference_id'] = '';
		$data['backward_reference_id'] = '';

		$this->db->select('forward_refrence_id, backward_refrence_id');
		$this->db->from('entries')->where('id', $entry_id)->order_by('id', 'asc');
                $reference_ids = $this->db->get();
		if ($reference_ids->num_rows() >0)
                {
	                foreach($reference_ids->result() as $ref)
        	        {
				$data['forward_reference_id'] = $ref->forward_refrence_id;
	                	$data['backward_reference_id'] = $ref->backward_refrence_id;
	                }
		}           
		$this->template->load('template', 'entry/verify', $data);
		return;
	}

	function verifyentry($entry_type, $entry_id)
	{
			if ( ! check_access('verify entry'))
                	{
                        	$this->messages->add('Permission denied.', 'error');
                        	redirect('entry/show/' . $entry_type);
                        	return;
                	}
			$entry_type_id = entry_type_name_to_id($entry_type);
                        $cur_entry = $this->Entry_model->get_entry($entry_id, $entry_type_id);
                        $submittername=$cur_entry->submitted_by;	
			$verifydate = date_php_to_mysql(date_today_php());
			$uname=$this->session->userdata('user_name');
			if($uname==$submittername){
                                $this->messages->add('Submitter can not verify own entry');
                        }
                        else{	
			$update_data = array(
                                'verified_by' => $uname,
                                'status' => 1,
				'update_date'=> $verifydate,
                        );
                        if ( ! $this->db->where('id', $entry_id)->update('entries', $update_data))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error verify Entry .', 'error');
				redirect('entry/show/' . $entry_type);
                                return;
                        }
			
                        /* Success */
                        $this->db->trans_complete();
			}
			redirect('entry/show/' . $entry_type);
			return;
	}

	function checkentry($entry_type)
        {
                /* Check access */
                if ( ! check_access('create entry'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('entry/show/' . $entry_type);
                        return;
                }

                /* Check for account lock */
                if ($this->config->item('account_locked') == 1)
                {
                        $this->messages->add('Account is locked.', 'error');
                        redirect('entry/show/' . $entry_type);
                        return;
                }

                /* Entry Type */
                $entry_type_id = entry_type_name_to_id($entry_type);
                if ( ! $entry_type_id)
                {
                        $this->messages->add('Invalid Entry type.', 'error');
                        redirect('entry/show/all');
                        return;
                } else {
                        $current_entry_type = entry_type_info($entry_type_id);
                }

                $this->template->set('page_title', 'New ' . $current_entry_type['name'] . ' Entry');


                /* Form fields */
		$data['backward_refrence_id'] = array(
                        'name' => 'backward_refrence_id',
                        'id' => 'backward_refrence_id',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => '',
                );
                $data['entry_number'] = array(
                        'name' => 'entry_number',
                        'id' => 'entry_number',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => '',
                );
		$data['bank_name'] = array(
                        'name' => 'bank_name',
                        'id' => 'bank_name',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => '',
                );
                $data['banif_name'] = array(
                        'name' => 'banif_name',
                        'id' => 'banif_name',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => '',
                );

                $data['entry_date'] = array(
                        'name' => 'entry_date',
                        'id' => 'entry_date',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => date_today_php(),
                );
		$data['entry_narration'] = array(
                        'name' => 'entry_narration',
                        'id' => 'entry_narration',
                        'cols' => '50',
                        'rows' => '4',
                        'value' => '',
                );
                $data['entry_type_id'] = $entry_type_id;
                $data['current_entry_type'] = $current_entry_type;
                $data['entry_tags'] = $this->Tag_model->get_all_tags();
		$options = array();
                $this->db->select('name, label');
                $this->db->from('entry_types');
                $query = $this->db->get();
		$new_id = '--Please Select--';
		$options[$new_id] = '--Please Select--';
                foreach($query->result() as $row)
                {
                        $new_id = $row->name;
                        $options[$new_id] = $row->name;
                }
                $data['entry_name']=$options;

                $data['active_entry_name'] = 0;

                $data['entry_tag'] = 0;

		$data['fund_list'] = $this->Ledger_model->get_ledgers();
                $data['fund_list_active'] = 0;

                /* Form validations */
                if ($current_entry_type['numbering'] == '2')
                        $this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|required|is_natural_no_zero|uniqueentryno[' . $entry_type_id . ']');
                else if ($current_entry_type['numbering'] == '3')
                        $this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|is_natural_no_zero|uniqueentryno[' . $entry_type_id . ']');
                else
                        $this->form_validation->set_rules('entry_number', 'Bill/Voucher Number', 'trim|is_natural_no_zero|uniqueentryno[' . $entry_type_id . ']');
			$this->form_validation->set_rules('backward_refrence_id', 'Backward Refrence Id', 'trim|is_natural_no_zero');
                	$this->form_validation->set_rules('entry_date', 'Bill/Voucher Date', 'trim|required|is_date|is_date_within_range');
                	$this->form_validation->set_rules('entry_narration', 'trim');
                	$this->form_validation->set_rules('entry_tag', 'Tag', 'trim|is_natural');
			$this->form_validation->set_rules('entry_name', 'Entry Type', 'trim|required');
                	$this->form_validation->set_rules('bank_name', 'Bank name', 'trim');
			$this->form_validation->set_rules('banif_name', 'Beneficiary Name', 'trim');
                /* Debit and Credit amount validation */
                if ($_POST)
                {
                        foreach ($this->input->post('ledger_dc', TRUE) as $id => $ledger_data)
                        {

                                $this->form_validation->set_rules('dr_amount[' . $id . ']', 'Debit Amount', 'trim|currency');
                                $this->form_validation->set_rules('cr_amount[' . $id . ']', 'Credit Amount', 'trim|currency');
                                $this->form_validation->set_rules('cheque[' . $id . ']', 'Cheque No', 'trim|currency');
			 }
                }

                /* Repopulating form */
                if ($_POST)
                {
                        $data['entry_number']['value'] = $this->input->post('entry_number', TRUE);
                        $data['entry_date']['value'] = $this->input->post('entry_date', TRUE);
                        $data['entry_narration']['value'] = $this->input->post('entry_narration', TRUE);
                        $data['entry_tag'] = $this->input->post('entry_tag', TRUE);
			$data['backward_refrence_id'] = $this->input->post('backward_refrence_id', TRUE);
			$data['active_entry_name'] = $this->input->post('entry_name', TRUE);
                        $data['ledger_dc'] = $this->input->post('ledger_dc', TRUE);
                        $data['ledger_id'] = $this->input->post('ledger_id', TRUE);
                        $data['dr_amount'] = $this->input->post('dr_amount', TRUE);
                        $data['cr_amount'] = $this->input->post('cr_amount', TRUE);
			$data['bank_name']['value'] = $this->input->post('bank_name', TRUE);
                        $data['banif_name']['value'] = $this->input->post('banif_name', TRUE);
			$data['fund_list_active'] = $this->input->post('fund_list', TRUE);
                }
		else {
                        for ($count = 0; $count <= 3; $count++)
                        {

                                /*if ($count == 0 && $entry_type == "payment")
                                        $data['ledger_dc'][$count] = "C";
                                else if ($count == 1 && $entry_type != "payment")
                                        $data['ledger_dc'][$count] = "C";
                                else*/
                                        $data['ledger_dc'][$count] = "D";
                                $data['ledger_id'][$count] = 0;
                                $data['dr_amount'][$count] = "";
                                $data['cr_amount'][$count] = "";
                        }
                }
                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'entry/add', $data);
                        return;
                }
                else
                {
                        /* Checking for Valid Ledgers account and Debit and Credit Total */
                        $data_all_ledger_id = $this->input->post('ledger_id', TRUE);
                        $data_all_ledger_dc = $this->input->post('ledger_dc', TRUE);
                        $data_all_dr_amount = $this->input->post('dr_amount', TRUE);
                        $data_all_cr_amount = $this->input->post('cr_amount', TRUE);
			$data_banif_name = $this->input->post('banif_name', TRUE);
                        $data_bank_name = $this->input->post('bank_name', TRUE);
                        $data_entry_name = $this->input->post('entry_name', TRUE);
			$data_all_fund_ledger = $this->input->post('fund_list', TRUE);

                        $dr_total = 0;
                        $cr_total = 0;
			$det='';
                        $bank_cash_present = FALSE; /* Whether atleast one Ledger account is Bank or Cash account */
                        $non_bank_cash_present = FALSE;  /* Whether atleast one Ledger account is NOT a Bank or Cash account */
                        $data_narration = $this->input->post('entry_narration', TRUE);
			$ledidarray=array();
			$leddcarray=array();
                        foreach ($data_all_ledger_dc as $id => $ledger_data)
                        {
                                if ($data_all_ledger_id[$id] < 1)
                                        continue;

                                /* Check for valid ledger id */
                                $this->db->from('ledgers')->where('id', $data_all_ledger_id[$id]);
                                $valid_ledger_q = $this->db->get();
                                if ($valid_ledger_q->num_rows() < 1)
                                {
                                        $this->messages->add('Invalid Ledger account.', 'error');
                                        $this->template->load('template', 'entry/add', $data);
                                        return;
				} else {
                                if ($data_all_ledger_dc[$id] == "D")
                                {
                                	$dr_total = float_ops($data_all_dr_amount[$id], $dr_total, '+');
                                } else {
                                        $cr_total = float_ops($data_all_cr_amount[$id], $cr_total, '+');
                                }
                                /* Check for valid ledger type */
                                $valid_ledger = $valid_ledger_q->row();
                                $ledid= $valid_ledger-> id;
                                $ledname= $valid_ledger-> name;
				$ledidarray[]=$ledid;
                                $dc=$data_all_ledger_dc[$id];
				$leddcarray[]=$dc;
                                }
                        }
			if (float_ops($dr_total, $cr_total, '!='))
                        {
                                $this->messages->add('Debit and Credit Total does not match!', 'error');
                                $this->template->load('template', 'entry/add', $data);
                                return;
                        } else if (float_ops($dr_total, 0, '==') && float_ops($cr_total, 0, '==')) {
                                $this->messages->add('Cannot save empty Entry.', 'error');
                                $this->template->load('template', 'entry/add', $data);
                                return;
                        }
                        $det=$this->Ledger_model->get_other_ledger_name($ledidarray, $entry_type, $leddcarray, $dr_total);

			if($det){
                        	$this->messages->add('The entry with same parameter exist, if you want to submit, click Create ', 'error');
                                //$this->template->load('template', 'entry/checkentry', $data);
                                //return;
                        }
                        else{
                                $this->add($entry_type);
                        //        redirect('entry/show/' . $entry_type);
			//	return;
                        }
                }
                $this->template->load('template', 'entry/checkentry', $data);
                return;
        }

	function ledger_fund($id){
		$ledger_amount = $this->Ledger_model->get_ledger_balance($id);
		$ledger_amount = 0 - $ledger_amount;
		echo $ledger_amount;
		return;
	}

}

/* End of file entry.php */
/* Location: ./system/application/controllers/entry.php */
//check the id of expense in last 
?>
