<?php

class Budget extends Controller {

	var $reappropriation = array();
	var $child_controller = array();
	var $parent_controller = array();
	var $unallocated_child_controller = array();
	var $unallocated_parent_controller = array();
	var $a = 2;
	var $counter = 0;

	function Budget()

	{
		parent::Controller();
		$this->load->model('Budget_model');
		//$this->load->model('Group_model');
		//$this->load->model('Ledger_model');
		return;
	}	

	function index()
	{
		redirect('budget/add');
		return;
	}

	function add()
        {
		$this->load->library('validation');
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));

                $this->template->set('page_title', 'New Budget');
	
		//check whether 'Main Budget' value is set
		$this->db->select('bd_balance')->from('budgets')->where('code = ', '50');
                $main_budget = $this->db->get();
                foreach($main_budget->result() as $row)
                {
                	$main_budget_amount = $row->bd_balance;
                }
		if($main_budget_amount == '0.00')
		{
			$this->messages->add('Please add budget amount for "Main Budget"', 'error');	
		}

		//get username of currently logged in user
		$child_budget = array();
		$username = $this->config->item('account_name');
                /* Check access */
                if ( ! check_access('create budget'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('budgetl');
                        return;
                }

                /* Check for account lock */
                if ($this->config->item('account_locked') == 1)
                {
                        $this->messages->add('Budget is locked.', 'error');
                        redirect('budgetl');
                        return;
                }

		/* Get code for account 'Expenses' */
                $account_code = $this->Budget_model->get_account_code('Expenses');   
                //$data['group_expenses'] = $this->Budget_model->get_selected_groups();
                $data['group_expenses'] = $this->Budget_model->get_selected_groups('Expenses');			
                $data['group_expenses_active'] = 0;


                $data['budget_type'] = array(
                        'name' => 'budget_type',
                        'id' => 'budget_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

                $data['budget_amount'] = array(
                        'name' => 'budget_amount',
                        'id' => 'budget_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

                $data['budget_over_expense'] = array(
                        'name' => 'budget_over_expense',
                        'id' => 'budget_over_expense',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
		
                $data['budget_over'] = FALSE;
	
		/* Form validations */
                $this->form_validation->set_rules('group_expenses', 'Budget name', 'trim|required');
		$this->form_validation->set_rules('budget_amount', 'Budget Amount', 'trim|required|min_length[1]|max_length[100]');
		$this->form_validation->set_rules('budget_type', 'Budget type', 'trim|min_length[2]|max_length[100]');
		$this->form_validation->set_rules('budget_over_expense', 'Expenses exceeding budget', 'trim|min_length[2]|max_length[100]');
		
		/* Re-populating form */
                if ($_POST)
                {
		$data['group_expenses_active'] = $this->input->post('group_expenses', TRUE);
		$data['budget_amount'] = $this->input->post('budget_amount', TRUE);
                $data['budget_type']['value'] = $this->input->post('budget_type', TRUE);
                $data['budget_over'] = $this->input->post('budget_over', TRUE);
                $data['budget_over_expense']['value'] = $this->input->post('budget_over_expense', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'budget/add', $data);
                        return;
                }
                else
		{
			$data_name = $this->input->post('group_expenses', TRUE);
			$my_values = explode('#',$data_name);
			
			
			if($my_values[1] != 'Main Budget')
			{
				//$this->db->select('bd_balance')->from('budgets')->where('code = ', '50');
				$this->db->select('bd_balance')->from('budgets')->where('budgetname = ', 'Main Budget');
                		$main_budget = $this->db->get();
                		foreach($main_budget->result() as $row)
                		{
                        		$main_budget_amount = $row->bd_balance;
                		}

				if($main_budget_amount == '0.00')
                		{
                        		$this->messages->add('Please add budget amount for "Main Budget". Only then you can continue further.', 'error');
					redirect('budget/add');
					return;
                		}
				 
			}
			$data_amount = $this->input->post('budget_amount', TRUE);
			$sum = $data_amount;
			$data_parent_code = $this->Budget_model->get_parent($my_values[1]);
			//if($data_parent_code == '40')
			if($data_parent_code == $account_code){
				//$data_parent_code = '50';
				$this->db->select('bd_balance')->from('budgets')->where('budgetname = ', 'Main Budget');
				$m_budget = $this->db->get();
                                foreach($m_budget->result() as $row)
                                {
                                        $data_parent_code = $row->code;
                                }
			}
                        //$data_parent_id = $this->Budget_model->get_groupid_budgetname($data_parent_code);
                        $data_parent_id = $this->Budget_model->get_groupid_budgetname($data_parent_code, 'budgets');
                        //Get amount allocated to parent budget
                        //$parent_amount = $this->Budget_model->get_allocation_amount($data_parent_code);
                        $parent_amount = $this->Budget_model->get_allocation_amount($data_parent_code, 'budgets');

			if($my_values[1] != 'Main Budget')
			{
				//$child_budget = $this->Budget_model->get_child_budgets($data_parent_id);
				$child_budget = $this->Budget_model->get_child_budgets($data_parent_id, 'budgets');
				$count = 0;
				foreach ($child_budget as $code => $chld)
		                {
					if($chld['code'] != $my_values[0]){
						$allocation_amount = $this->Budget_model->get_allocation_amount($chld['code'], 'budgets');
						$sum = $sum + $allocation_amount;
					}
					$count++;
                		}
				if($sum > $parent_amount)
				{
					//Error message
					//$this->messages->add('Budget amount cannot exceed from parent budget. So, please check parent budget amount.', 'error');
					$temp = $sum - $data_amount;
					$temp = $parent_amount - $temp;
					$this->messages->add('Budget amount cannot exceed from parent budget. Amount remaining with parent budget is  ' . $temp , 'error');
				//	$this->template->load('template', 'budget/add', $data);
					redirect('budget/add');
					return;
					
				}
			}//new if

               		$data_type = $this->input->post('budget_type', TRUE);
			$data_budget_over = $this->input->post('budget_over', TRUE);
			$data_budget_over_expense = $this->input->post('budget_over_expense', TRUE);

			if($my_values[1] != 'Main Budget')
			{
				/* Check if group expenses present */
				$this->db->select('id')->from('groups')->where('code', $my_values[0]);
				if ($this->db->get()->num_rows() < 1)
              			{
					$this->db->select('id')->from('ledgers')->where('code', $my_values[0]);
					if ($this->db->get()->num_rows() < 1)
					{
                       				$this->messages->add('Invalid budget code.', 'error');
						$this->template->load('template', 'budget/add', $data);
                       				return;
					}
               			}
					
               			/* Only if expenses over budget is allowed 
			   	   0 means check box not selected
			    	   -1 means no limit till the parent budget allowed
			   	   otherwise input is added to the budget
				*/
               			if ($data_budget_over == "1"){
					if ($data_budget_over_expense == '')
						$data_budget_over = -1;
					else
						$data_budget_over = $data_budget_over_expense;
                				//$data_budget_over = 1;
				} else{
                       			$data_budget_over = 0;
				}

				$this->db->trans_start();
				$insert_data = array(
					'code' => $my_values[0],
					'group_id' => $data_parent_id,
					'budgetname' => $my_values[1],
                       			'bd_balance' => $data_amount,
                       			'type' => $data_type,
                       			'allowedover' => $data_budget_over,
           	 			);

               			if ( ! $this->db->insert('budgets', $insert_data))
               			{
                      			$this->db->trans_rollback();
                       			$this->messages->add('Error addding Budget - ' . $my_values[1] . ' by user ' . $username . '.', 'error');
			
              		        	$this->logger->write_message("error", "Error adding Budget called " . $data_name  . ' by user ' . $username);
					$this->template->load('template', 'budget/add', $data);
                       			return;
               			} else {
                      			$this->db->trans_complete();
                       			$this->messages->add('Added Budget - ' . $my_values[1] . ' by user ' . $username . '.', 'success');
                      			$this->logger->write_message("success", "Added Budget called " . $data_name  . ' by user ' . $username);
//                                			redirect('budgetl');
//                                			return;
              			}

				//Adding data to budget_allocate table
				$today = date("Y-m-d H:i:s");
				$this->db->trans_start();
				$insert_data1 = array(
                       			'code' => $my_values[0],
                       			'allocation_amount' => $data_amount,
                      			'creation_date' => $today,
              			);

              			if ( ! $this->db->insert('budget_allocate', $insert_data1))
               			{
                      			$this->db->trans_rollback();
                       			$this->messages->add('Error addding budget amount - ' . $data_amount  . ' by user ' . $username . '.', 'error');
		                       	$this->logger->write_message("error", "Error adding Budget amount " . $data_amount  . ' by user ' . $username);
        	                 	$this->template->load('template', 'budget/add', $data);
                	         	return;
                        	} else {
                                	$this->db->trans_complete();
                                	$this->messages->add('Added Budget amount- ' . $data_amount . ' by user ' . $username . '.', 'success');
                                	$this->logger->write_message("success", "Added Budget amount " . $data_amount  . ' by user ' . $username);
                                	redirect('budgetl');
                                	return;
                        	}
			}
			else
			{
				/* Only if expenses over budget is allowed 
                                   0 means check box not selected
	                          -1 means no limit till the parent budget allowed
                                   otherwise input is added to the budget
                                */
                                /*if ($data_budget_over == "1"){
        	                        if ($data_budget_over_expense == '')
                	                        $data_budget_over = -1;
                                        else
                                                $data_budget_over = $data_budget_over_expense;
                                        //$data_budget_over = 1;
                                } else{
                                        $data_budget_over = 0;
                                }*/

				$this->db->trans_start();
                                $update_data = array(
	                                'code' => $my_values[0],
                                        'budgetname' => $my_values[1],
                                        'bd_balance' => $data_amount,
                                        'type' => $data_type,
                                        'allowedover' => $data_budget_over,
                                        );

				/**
				 * Here "budgets" table is being updated
				 * because, the value for 'Main Budget'
				 * is inserted by default at the time of 
				 * database creation.
				 */
                                if ( ! $this->db->where('code', $my_values[0])->update('budgets', $update_data))
                                {
                 	                $this->db->trans_rollback();
                                        $this->messages->add('Error addding Budget - ' . $my_values[1]  . ' by user ' . $username . '.', 'error');
	                                $this->logger->write_message("error", "Error adding Budget called " . $data_name  . ' by user ' . $username);
                                        $this->template->load('template', 'budget/add', $data);
                                        return;
                                } else {
                                        $this->db->trans_complete();
                                        $this->messages->add('Added Budget - ' . $my_values[1] . ' by user ' . $username . '.', 'success');
                                        $this->logger->write_message("success", "Added Budget called " . $data_name  . ' by user ' . $username);
//                                      redirect('budgetl');
//                                      return;
                                }
					
	 			//Adding data to budget_allocate table
                                $today = date("Y-m-d H:i:s");
                                $this->db->trans_start();
                                $update_data1 = array(
                 	               'code' => $my_values[0],
                                       'allocation_amount' => $data_amount,
                                       'creation_date' => $today,
                                       );

                                if ( ! $this->db->insert('budget_allocate', $update_data1))
                                {
                           		$this->db->trans_rollback();
                                        $this->messages->add('Error addding budget amount - ' . $data_amount  . ' by user ' . $username . '.', 'error');

                                        $this->logger->write_message("error", "Error adding Budget amount " . $data_amount  . ' by user ' . $username);
                                        $this->template->load('template', 'budget/add', $data);
                                        return;
                                } else {
					$this->db->trans_complete();
                                        $this->messages->add('Added Budget amount- ' . $data_amount . ' by user ' . $username . '.', 'success');
                                        $this->logger->write_message("success", "Added Budget amount " . $data_amount  . ' by user ' . $username);
                                        redirect('budgetl');
                                        return;
                                }
			}
		}
		return;
	}


	function edit($id)
	{
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));

		$this->template->set('page_title', 'Edit Budget');
		$username = $this->config->item('account_name');

		/* Check access */
		if ( ! check_access('edit budget'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('budgetl');
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('budgetl');
			return;
		}

		/* Checking for valid data */
		$id = $this->input->xss_clean($id);
		$id = (int)$id;
		if ($id < 1) {
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('budgetl');
			return;
		}

		/* Loading current budget */
		$this->db->from('budgets')->where('id', $id);
		$budget_data_q = $this->db->get();
		if ($budget_data_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('budgetl');
			return;
		}
		$budget_data = $budget_data_q->row();

		/* Form fields */
		

		$data['budget_code'] = array(
                        'name' => 'budget_code',
                        'id' => 'budget_code',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->code,
			'readonly'=>'true',
                );

                $data['budget_name'] = array(
                        'name' => 'budget_name',
                        'id' => 'budget_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->budgetname,
			'readonly'=>'true',
                );


                $data['budget_over'] = array(
                        'name' => 'budget_over',
                        'id' => 'budget_over',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->allowedover,
                );

		$data['budget_id'] = $id;


		/* Form validations */
                $this->form_validation->set_rules('budget_over', 'Over Expence Allowed', 'trim|required|min_length[1]|max_length[100]' . $id);

		/* Re-populating form */
		if ($_POST)
		{
			$data['budget_code']['value'] = $this->input->post('budget_code', TRUE);
                        $data['budget_name']['value'] = $this->input->post('budget_name', TRUE);
                        $data['budget_over']['value'] = $this->input->post('budget_over', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'budget/edit', $data);
			return;
		}
		else
		{

			$data_code = $this->input->post('budget_code', TRUE);
                        $data_name = $this->input->post('budget_name', TRUE);
                        $data_over = $this->input->post('budget_over', TRUE);
			$data_id = $id;
			/* Check if parent budget id present */
			
			$this->db->select('id')->from('budgets')->where('code', $data_code);
                        if ($this->db->get()->num_rows() < 1)
                        {
                                $this->messages->add('Invalid Budget name.', 'error');
                                $this->template->load('template', 'budget/edit', $data);
                                return;
                        }

			$this->db->trans_start();
			$update_data = array(
				'code' => $data_code,
				'budgetname' => $data_name,
				'allowedover' => $data_over, 
			);
			if ( ! $this->db->where('id', $data_id)->update('budgets', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Budget account - ' . $data_code . '.', 'error');
				$this->messages->add('Error updating Budget account - ' . $data_name . '.', 'error');
				$this->logger->write_message("error", "Error updating Budget account called " . $data_name . $data_code . " [id:" . $data_id . "]"  . ' by user ' . $username);
				$this->template->load('template', 'budget/edit', $data);
				return;
			} else {
				$this->db->trans_complete();
				$this->messages->add('Updated Budget account - ' . $data_name . '.', 'success');
				$this->logger->write_message("success", "Updated Budget account called " . $data_name . " [id:" . $data_id . "]"  . ' by user ' . $username);
				redirect('budgetl');
				return;
			}
		}
		return;
	}

	function delete($id)
	{
		$username = $this->config->item('account_name');
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));

		/* Check access */
		if ( ! check_access('delete budget'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('budgetl');
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('budgetl');
			return;
		}

		/* Checking for valid data */
		$id = $this->input->xss_clean($id);
		$id = (int)$id;
		if ($id < 1) {
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('budgetl');
			return;
		}
		$this->db->from('budgets')->where('id', $id);
		if ($this->db->get()->num_rows() < 0)
		{
			$this->messages->add('Cannot delete non-empty Budget account.', 'error');
			redirect('budgetl');
			return;
		}

		/* Get the budget details */
		$this->db->from('budgets')->where('id', $id);
		$budget_q = $this->db->get();
		if ($budget_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('budgetl');
			return;
		} else {
			$budget_data = $budget_q->row();
		}

		/* Deleting budget */
		$this->db->trans_start();
		if ( ! $this->db->delete('budgets', array('id' => $id)))
		{
			$this->db->trans_rollback();
			$this->messages->add('Error deleting Budget account - ' . $budget_data->budgetname . '.', 'error');
			$this->logger->write_message("error", "Error deleting budget account called " . $budget_data->budgetname . " [id:" . $id . "]"  . ' by user ' . $username);
			redirect('budgetl');
			return;
		} else {
			$this->db->trans_complete();
			$this->messages->add('Deleted budget account - ' . $budget_data->budgetname . '.', 'success');
			$this->logger->write_message("success", "Deleted Budget account called " . $budget_data->budgetname . " [id:" . $id . "]"  . ' by user ' . $username);
			redirect('budgetl');
			return;
		}

		return;
	}




	function reappro(){

		setlocale(LC_MONETARY, 'en_IN');

                $username = $this->config->item('account_name');
		//$parent_controller = array();
		//$child_controller = array();

		/* Check access */
                if ( ! check_access('reappropriate budget'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('budgetl');
                        return;
                }

                $this->load->model('Budget_model');
		$this->load->helper('array');
                $this->template->set('page_title', 'Budget Reappropriation');
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));

                $budget_arr = array();
		$counter = 0;
		$final_budget = array();

                $budget_arr = $this->Budget_model->get_budgets();
                $this->reappropriation['budget'] = $budget_arr;
		foreach ($budget_arr as $id => $bud)
                {
                        $name = 'budget_value'. "_" .$bud['id'];
                        $this->reappropriation[$name] = array(
                        	'name' => $name,
                                'id' => $bud['id'],
                                'maxlength' => '100',
                                'size' => '40',
                                //'value' => '',
                                'value' => $bud['bd_balance'],
                        );
                        $counter++;
                }
		
		$counter = 0;
		$count = 0;
		$main_budget_code = '';		
		$this->db->from('budgets')->where('budgetname =', 'Main Budget');
                $main_budget = $this->db->get();
                foreach($main_budget->result() as $row)
                {
    	            $main_budget_code = $row->code;
                }	

		//code for generating unallocated budget
		$sum = 0;
		foreach ($this->reappropriation['budget'] as $id => $bud)
                {
			//if($bud['code'] == '50')
			if($bud['code'] == $main_budget_code)
			{
				$main_budget_amount = $bud['bd_balance'];
			}
			else
			//if($bud['code'] != '50')
                        {
	                        $temp = $this->countDigits($bud['code']);
				if($temp == 4){
					$sum = $sum + $bud['bd_balance'];
					$this->unallocated_parent_controller[$counter]['id'] = $bud['id'];
					$this->unallocated_parent_controller[$counter]['code'] = $bud['code'];
                                        $this->unallocated_parent_controller[$counter]['amount'] = $bud['bd_balance'];
					$counter++;
				}
				else{
                                        $this->unallocated_child_controller[$count]['id'] = $bud['id'];
                                        $this->unallocated_child_controller[$count]['code'] = $bud['code'];
                                        $this->unallocated_child_controller[$count]['amount'] = $bud['bd_balance'];
                                        $count++;
                                }
			}
		}
		$temp_amount = $main_budget_amount - $sum;
		$this->reappropriation['unallocated_value_50'] = money_format('%!i', $temp_amount);

		foreach($this->unallocated_parent_controller as $id => $pb)
                {
	                $this->calculate_unallocated_budget(4, $pb['code'], $pb['amount']);
                }

		/*foreach($this->reappropriation['budget'] as $id => $bud)
		{
			$temp = $this->countDigits($bud['code']);
			if($temp == 4){
				$sum = $sum + $bud['bd_balance'];
				$this->unallocated_parent_controller[$counter]['id'] = $bud['id'];
				$this->unallocated_parent_controller[$counter]['code'] = $bud['code'];
				$this->unallocated_parent_controller[$counter]['amount'] = $bud['bd_balance'];
				$counter++;
			}
			else{
				
			}
		}*/

		// Form Validation 
             	foreach ($this->reappropriation['budget'] as $id => $bud)
                {
			$name = 'budget_value'. "_" .$bud['id'];
                        $this->form_validation->set_rules($name, 'Budget Value', 'trim|min_length[2]|max_length[15]');
                }		

		if ($_POST)
                {
                        foreach ($this->reappropriation['budget'] as $id => $bud)
                        {
                                $name = 'budget_value'. "_" .$bud['id'];
                                $this->reappropriation[$name]['value'] = $this->input->post($name, TRUE);
                        }
		}


		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
                        return;
                }
                else
                {
			
			$Sum = 0;
			$counter = 0;
			$count = 0;
			$count1 = 0;
			$expense_amount = 0;

			foreach($this->reappropriation['budget'] as $id => $bud)
			{
				$name = 'budget_value'. "_" .$bud['id'];
				$new_amount =  $this->input->post($name, TRUE);
				if($new_amount != $bud['bd_balance'])
				{
					$final_budget[$count1]['id'] = $bud['id'];
					$final_budget[$count1]['code'] = $bud['code'];
					$final_budget[$count1]['amount'] = $new_amount;
					$count1++;			
					//if($bud['code'] == '50')
					if($bud['code'] == $main_budget_code)
					{
						$expense_amount = $new_amount;	
					}
				}

				//if($bud['code'] != '50')
				if($bud['code'] != $main_budget_code)
				{
                                	$temp = $this->countDigits($bud['code']);
                                	if($temp == 4)
                                	{
						$this->parent_controller[$counter]['id'] = $bud['id'];
                                        	$this->parent_controller[$counter]['code'] = $bud['code'];
						$this->parent_controller[$counter]['amount'] = $new_amount;
                                        	$Sum = $Sum + $this->parent_controller[$counter]['amount'];
                                        	$counter++;
                                	}//if
                                	else
                                	{
						$this->child_controller[$count]['id'] = $bud['id'];
                                        	$this->child_controller[$count]['code'] = $bud['code'];
						$this->child_controller[$count]['amount'] = $new_amount;
                                        	$count++;
                                	}
				}//if
                        }//for

			if($expense_amount == '0')
			{
				//$expense_amount = $this->Budget_model->get_allocation_amount('50', 'budgets');
				$expense_amount = $this->Budget_model->get_allocation_amount($main_budget_code, 'budgets');
			}

                        if($Sum > $expense_amount)
                        {
                                //Error Msg
                                $this->messages->add('Sum of parent budgets does not match with value of "Main Budget"', 'error');
                                $this->logger->write_message("error", "Sum of budgets " . $Sum  . " does not match with value of budget 'Expenses'" . ' by user ' . $username);
                                $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
                                return;
                        } else{

				foreach($this->parent_controller as $id => $pb)
				{
					$this->calculate(4, $pb['code'], $pb['amount']);
				}
				
			
				$today = date("Y-m-d H:i:s");
				foreach($final_budget as $id => $cbud)
				{
					$this->db->trans_start();
                                        $insert_data = array(
                                        	'code' => $cbud['code'],
                                                'allocation_amount' => $cbud['amount'],
                                                'creation_date' => $today,
                                        );

                                        if ( ! $this->db->insert('budget_allocate', $insert_data))
                                        {
                                        	$this->db->trans_rollback();
                                                $this->messages->add('Error inserting value to Budget Allocate for budget- ' . $cbud['code'] . ' by user ' . $username .  '.', 'error');
                                                $this->logger->write_message("error", "Error inserting value for Budget code " . $cbud['code']  . ' by user ' . $username);
                                                //$this->template->load('template', 'budget/reappropriation', $this->reappropriation);
						redirect('budgetl');
                                               	return;
                                        } else {
                                                $this->db->trans_complete();
                                                $this->messages->add('Successfully inserted value to Budget Allocate- ' . ' by user ' . $username . '.', 'success');
                                                $this->logger->write_message("success","Successfully inserted value for Budget code" . $cbud['code']   . ' by user ' . $username);
                                       }

					$this->db->trans_start();
                                        $update_data = array(
                                        	'code' => $cbud['code'],
                                                'bd_balance' => $cbud['amount'],
                                        );
                                                                                
                                        if ( ! $this->db->where('code', $cbud['code'])->update('budgets', $update_data))
                                        {
                                        	$this->db->trans_rollback();
                                                $this->messages->add('Error updating value to Budget for budget- ' . $cbud['code'] . ' by user ' . $username .  '.', 'error');
                                                $this->logger->write_message("error", "Error updating value for Budget code " . $cbud['code']  . ' by user ' . $username);
                                               // $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
						redirect('budgetl');
                                                return;
                                        } else {
                                                $this->db->trans_complete();
                                                $this->messages->add('Successfully updated value to Budget- ' . ' by user ' . $username . '.', 'success');
                                                $this->logger->write_message("success","Successfully updated value for Budget code " . $cbud['code']   . ' by user ' . $username);
                                        }
		
				}//for db
				
			}//else

		}//else

		//$this->template->load('template', 'budget/reappropriation', $this->reappropriation);
		redirect('budgetl');
                return ;
	}

	function countDigits($str)
	{
    		//preg_match_all( "/[0-9]/", $str, $arr );
		$search = '1234567890';
		$count = strlen($str) - strlen(str_replace(str_split($search), '', $str));
		return $count;
	}

	function startsWith($str1, $str2)
	{
    		return !strncmp($str1, $str2, strlen($str2));
	}

	function calculate($i, $code, $amount)
	{
		$username = $this->config->item('account_name');
		$sum = 0;
		
		foreach($this->child_controller as $id => $cb)
		{
			if($cb['code'] != $code)
			{
				$temp = $this->startsWith($cb['code'], $code);
				$len = $this->countDigits($cb['code']);

				if($temp && ($len == $i + $this->a))
				{
					$sum = $sum + $cb['amount'];
					$this->calculate($i + $this->a, $cb['code'], $cb['amount']);
				}
			}		
		}

		if($sum > $amount)
		{
			//Error Msg
			$this->messages->add('An error has occured.', 'error');
                        $this->messages->add('Sum of child budgets does not match with unallocated value of budget ' . $code, 'error');
			$this->messages->add('Please try updating values again', 'error');
               	        $this->logger->write_message("error", "Sum of child budgets does not match with value of budget " . $code . ' by user ' . $username);
                      		// $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
			redirect('budget/reappro');
       	                return;
		}
	}

	function calculate_unallocated_budget($i, $code, $amount)
	{
		$sum = 0;
		$count = 0;
		$unallocated_budget_amount = '';
	
		foreach($this->unallocated_child_controller as $id => $cb)
		{
			if($cb['code'] != $code)
			{
				$temp = $this->startsWith($cb['code'], $code);
				$len = $this->countDigits($cb['code']);


				if($temp && ($len == $i + $this->a))
				{
					$count++;
					$sum = $sum + $cb['amount'];
					//$dump = $this->calculate_unallocated_budget($i + $this->a, $cb['code'], $cb['amount']);
					$this->calculate_unallocated_budget($i + $this->a, $cb['code'], $cb['amount']);
				}
			}		
		}
		
		if($count>0 || $this->countDigits($code)==4)
			$unallocated_budget_amount = $amount - $sum;
		//$this->logger->write_message("error","amount for " . $code . " = " . $unallocated_budget_amount);
                $name = 'unallocated_value_' . $code;
		if($unallocated_budget_amount != '')
                	$this->reappropriation[$name] = money_format('%!i', $unallocated_budget_amount);
		else
			$this->reappropriation[$name] = $unallocated_budget_amount;
		return;
	}

}//class

/* End of file budget.php */
/* Location: ./system/application/controllers/budget.php */
?>
