<?php

class Budget extends Controller {

	function Budget()
	{
		parent::Controller();
		$this->load->model('Budget_model');
		$this->load->model('Group_model');
		$this->load->model('Ledger_model');
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
//		$this->load->library('session');
                $this->template->set('page_title', 'New Budget');
		
		//get username of currently logged in user
//		$username = $this->session->userdata('name');
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
			
		$data['group_expenses'] = $this->Budget_model->get_selected_groups();
                $data['group_expenses_active'] = 0;

               /* $data['group_parent'] = $this->Budget_model->get_groupid_budgetname();
                $data['group_parent_active'] = 0;*/
                //$data['affects_gross'] = 0;

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
		//$this->form_validation->set_rules('group_expenses', 'Budget name', 'trim|required|is_natural_no_zero');
                $this->form_validation->set_rules('group_expenses', 'Budget name', 'trim|required');
		//$this->form_validation->set_rules('group_parent', 'Parent Budget', 'trim|required|is_natural_no_zero');
             //  $this->form_validation->set_rules('group_parent', 'Parent Budget', 'trim');
		$this->form_validation->set_rules('budget_amount', 'Budget Amount', 'trim|required|min_length[1]|max_length[100]');
		$this->form_validation->set_rules('budget_type', 'Budget type', 'trim|min_length[2]|max_length[100]');
		$this->form_validation->set_rules('budget_over_expense', 'Expenses exceeding budget', 'trim|min_length[2]|max_length[100]');
		
		/* Re-populating form */
                if ($_POST)
                {
		$data['group_expenses_active'] = $this->input->post('group_expenses', TRUE);
               // $data['group_parent_active'] = $this->input->post('group_parent', TRUE);
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
                	//$my_values = array();
                        //$my_values[] = explode('#',$data_name);
			$my_values = explode('#',$data_name);
			//$data_code = $my_values[0];
                        //$data_name = $my_values[1];
			 
			$data_parent_name = $this->Budget_model->get_parent($my_values[1]);
			$data_parent_id = $this->Budget_model->get_groupid_budgetname($data_parent_name);
		 //       $data_parent_id = $this->input->post('group_parent', TRUE);
			$data_amount = $this->input->post('budget_amount', TRUE);
			//Get amount allocated to parent budget
			$parent_code = $this->Budget_model->get_parent_code($data_parent_name);
			$parent_amount = $this->Budget_model->get_allocation_amount($parent_code);
			$sum = $data_amount;
			if($my_values[1] != 'Expenses')
			{
				$child_budget = $this->Budget_model->get_child_budgets($data_parent_id);
				$count = 0;
				foreach ($child_budget as $code => $chld)
		                {
					if($chld['code'] != $my_values[0]){
						$allocation_amount = $this->Budget_model->get_allocation_amount($chld['code']);
						$sum = $sum + $allocation_amount;
					}
					$count++;
                		}
				
				if($sum > $parent_amount)
				{
					//Error message
					$this->messages->add('Invalid budget amount - ', 'error');
					$this->messages->add($data_amount, 'error');
					$this->template->load('template', 'budget/add', $data);
					return;
					
				} else{
                        		$data_type = $this->input->post('budget_type', TRUE);
					$data_budget_over = $this->input->post('budget_over', TRUE);
					$data_budget_over_expense = $this->input->post('budget_over_expense', TRUE);

					/* Check if group expenses present */
                        		//$this->db->select('id')->from('groups')->where('id', $data_name);
                        		//$this->db->select('id')->from('groups')->where('code', $data_code);
					$this->db->select('id')->from('groups')->where('code', $my_values[0]);
					if ($this->db->get()->num_rows() < 1)
                        		{
						$this->messages->add($my_values[0], 'error');
                                		$this->messages->add('Invalid budget code.', 'error');
						$this->template->load('template', 'budget/add', $data);
                                		return;
                        		}

                        		/* Check if group parent present 
                        		$this->db->select('id')->from('budgets')->where('id', $data_parent_id);
                        		if ($this->db->get()->num_rows() < 1)
                        		{
                                		$this->messages->add('Invalid Parent budget head.', 'error');
                                		$this->template->load('template', 'budget/add', $data);
                                		return;
                        		}*/

                        		/* Only if expenses over budget is allowed */
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
                                		//'code' => $data_code,
						'code' => $my_values[0],
						'group_id' => $data_parent_id,
                                		//'budgetname' => $data_name,
						'budgetname' => $my_values[1],
                                		'bd_balance' => $data_amount,
                                		'op_balance_dc' => NULL,
                                		'type' => $data_type,
                                		'allowedover' => $data_budget_over,
                       	 		);

                        		//if ( ! $this->db->insert('groups', $insert_data))
                        		if ( ! $this->db->insert('budgets', $insert_data))
                        		{
                                		$this->db->trans_rollback();
                                		$this->messages->add('Error addding Budget - ' . $my_values[0] . $data_parent_id . $my_values[1] . $data_type . $data_budget_over  . ' by user ' . $username . '.', 'error');
						//echo $this->session->userdata('name')
					
                        		        $this->logger->write_message("error", "Error adding Budget called " . $data_name  . ' by user ' . $username);
                                		//$this->template->load('template', 'group/add', $data);
						$this->template->load('template', 'budget/add', $data);
                                		return;
                        		} else {
                                		$this->db->trans_complete();
                                		$this->messages->add('Added Budget - ' . $my_values[0] . $data_parent_id . $my_values[1] . $data_type . $data_budget_over . ' by user ' . $username . '.', 'success');
                                		$this->logger->write_message("success", "Added Budget called " . $data_name  . ' by user ' . $username);
//                                		redirect('budgetl');
//                                		return;
                        		}

					//Adding data to budget_allocate table
					//$date = date_php_to_mysql(date_today_php());
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
				}//else
			}//new if
		}
		return;
	}


	function edit($id)
	{
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
		if ($id <= 4) {
			$this->messages->add('Cannot edit System Budget account.', 'error');
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
		
		/*$data['group_code'] = array(
			'name' => 'group_code',
			'id' => 'group_code',
			'maxlength' => '100',
			'size' => '40',
			'value' => $group_data->code,
		);

		$data['group_name'] = array(
			'name' => 'group_name',
			'id' => 'group_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => $group_data->name,
		);*/

		$data['budget_code'] = array(
                        'name' => 'budget_code',
                        'id' => 'budget_code',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->code,
                );

                $data['budget_name'] = array(
                        'name' => 'budget_name',
                        'id' => 'budget_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->budgetname,
			'readonly'=>'true',
                );

		/*$data['budget_type'] = array(
                        'name' => 'budget_type',
                        'id' => 'budget_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->type,
                );

                $data['budget_bd'] = array(
                        'name' => 'budget_bd',
                        'id' => 'budget_bd',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->bd_balance,
                );
		
		$data['budget_op'] = array(
                        'name' => 'budget_op',
                        'id' => 'budget_op',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->op_balance_dc,
                );*/

                $data['budget_over'] = array(
                        'name' => 'budget_over',
                        'id' => 'budget_over',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $budget_data->allowedover,
                );

		$data['budget_id'] = $id;

		/*$data['group_parent'] = $this->Group_model->get_all_groups($id);
		$data['group_parent_active'] = $group_data->parent_id;
		$data['group_id'] = $id;
		$data['affects_gross'] = $group_data->affects_gross;*/

		/* Form validations */
		//$this->form_validation->set_rules('budget_code', 'Budget Code', 'trim|required|min_length[2]|max_length[100]|uniquewithid[budgets.code.' . $id . ']');
		//$this->form_validation->set_rules('budget_name', 'Budget Name', 'trim|required|min_length[2]|max_length[100]|uniquewithid[budgets.budgetname.' . $id . ']');
		//$this->form_validation->set_rules('budget_type', 'Budget Type', 'trim|required|min_length[2]|max_length[100]|uniquewithid[budgets.type.' . $id . ']');
                //$this->form_validation->set_rules('budget_bd', 'B/D Balance', 'trim|min_length[2]|max_length[100]' . $id . ']');
		//$this->form_validation->set_rules('budget_op', 'O/P Balance', 'trim|min_length[2]|max_length[100]|uniquewithid[budgets.op_balance_dc.' . $id . ']');
                $this->form_validation->set_rules('budget_over', 'Over Expence Allowed', 'trim|required|min_length[1]|max_length[100]' . $id);
		//$this->form_validation->set_rules('group_parent', 'Parent group', 'trim|required|is_natural_no_zero');

		/* Re-populating form */
		if ($_POST)
		{
			/*$data['group_code']['value'] = $this->input->post('group_code', TRUE);
			$data['group_name']['value'] = $this->input->post('group_name', TRUE);
			$data['group_parent_active'] = $this->input->post('group_parent', TRUE);
			$data['affects_gross'] = $this->input->post('affects_gross', TRUE);*/
			$data['budget_code']['value'] = $this->input->post('budget_code', TRUE);
                        $data['budget_name']['value'] = $this->input->post('budget_name', TRUE);
		//	$data['budget_type']['value'] = $this->input->post('budget_type', TRUE);
                //        $data['budget_bd']['value'] = $this->input->post('budget_bd', TRUE);
		//	$data['budget_op']['value'] = $this->input->post('budget_op', TRUE);
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
			/*$data_code = $this->input->post('group_code', TRUE);
			$data_name = $this->input->post('group_name', TRUE);
			$data_parent_id = $this->input->post('group_parent', TRUE);
			$data_id = $id;*/

			$data_code = $this->input->post('budget_code', TRUE);
                        $data_name = $this->input->post('budget_name', TRUE);
		//	$data_type = $this->input->post('budget_type', TRUE);
                //        $data_bd = $this->input->post('budget_bd', TRUE);
		//	$data_op = $this->input->post('budget_op', TRUE);
                        $data_over = $this->input->post('budget_over', TRUE);
			$data_id = $id;

			/* Check if parent budget id present */
			
			/*$this->db->select('id')->from('groups')->where('id', $data_parent_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'budget/edit', $data);
				return;
			}*/

			$this->db->select('id')->from('budgets')->where('code', $data_code);
                        if ($this->db->get()->num_rows() < 1)
                        {
                                $this->messages->add('Invalid Budget name.', 'error');
                                $this->template->load('template', 'budget/edit', $data);
                                return;
                        }
			/* Check if parent group same as current group id */
			/*if ($data_parent_id == $id)
			{
				$this->messages->add('Invalid Parent group', 'error');
				$this->template->load('template', 'budget/edit', $data);
				return;
			}*/

			/* Only if Income or Expense can affect gross profit loss calculation */
			/*$data_affects_gross = $this->input->post('affects_gross', TRUE);
			if ($data_parent_id == "3" || $data_parent_id == "4")
			{
				if ($data_affects_gross == "1")
					$data_affects_gross = 1;
				else
					$data_affects_gross = 0;
			} else {
				$data_affects_gross = 0;
			}*/

			$this->db->trans_start();
			$update_data = array(
				'code' => $data_code,
		//		'budgetname' => $data_name,
		//		'bd_balance' => $data_bd,
		//		'op_balance_dc' => $data_op,
		//		'type' => $data_type,
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
		if ($id <= 4) {
			$this->messages->add('Cannot delete System Budget account.', 'error');
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
//		$this->db->from('ledgers')->where('group_id', $id);
//		if ($this->db->get()->num_rows() > 0)
//		{
//			$this->messages->add('Cannot delete non-empty Group account.', 'error');
//			redirect('account');
//			return;
//		}

		/* Get the budget details */
		$this->db->from('budgets')->where('id', $id);
		//$group_q = $this->db->get();
		//if ($group_q->num_rows() < 1)
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

		/* Deleting budget from budget_allocate table*/
		/*$this->db->trans_start();
                if ( ! $this->db->delete('budget_allocate', array('id' => $id)))
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
                //      redirect('budgetl');
                //      return;
                }*/
		return;
	}

	function allocation(){
		$username = $this->config->item('account_name');
                $this->load->model('Budget_model');
                $this->template->set('page_title', 'Budget Allocation');
                
		$budget_arr = array();
		$child_budget = array();
		$child1_budget = array();
		$budget_arr = $this->Budget_model->get_selected_budgets();
		$data['budget'] = $budget_arr;
		$child_budget = $this->Budget_model->get_sub_budget();
		//$data['sub_budget'] = FALSE;
		//$data['sub_budget'. "_" .$data['id']] = FALSE;
		
		// Getting names of child budgets
		foreach ($data['budget'] as $id => $bud)
                {
			$counter = 0;
			$child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
			foreach ($child1_budget as $id => $child)
			{
				$ccname = 'child' . $bud['id'] . $counter;
				$data[$ccname] = array(
					'name' => 'child_budget_value'. "_" .$child['id'],
                                	'id' => $child['id'],
                                	'maxlength' => '100',
                                	'size' => '40',
                                	'value' => '',	
				);
			$counter++;
			}
			$counter1 = 'counter' . $bud['id'];
			$data[$counter1] = $counter;
		}

		$data['child_budget'] = $child_budget;

		// Getting names of parent budgets
		$counter = 0;
		foreach ($data['budget'] as $id => $bud)
                {
			//$name = 'budget_value'. "_" .$bud['id'];
			$name = 'name' . $counter;
			$data[$name] = array(
        	         	'name' => 'budget_value'. "_" .$bud['id'],
                       		'id' => $bud['id'],
                	       	'maxlength' => '100',
                	       	'size' => '40',
                       		'value' => '',
                		);
			$counter++;
			$check_name = 'sub_budget'. "_" .$bud['id'];
			$data[$check_name] = FALSE;
		}

		/* Form Validation */
		foreach ($data['budget'] as $id => $bud)
                {
                        $counter=0;
			$child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
                        foreach ($child1_budget as $id => $child)
                        {
                                 $ccname = 'child' . $bud['id'] . $counter;
                                 //$data[$ccname]['value'] = $this->input->post($ccname, TRUE);
				 $this->form_validation->set_rules($ccname, 'Budget Value', 'trim|min_length[2]|max_length[15]');
                                 $counter++;
                        }
                }
		
		$counter = 0;
		foreach ($data['budget'] as $id => $bud)
                {
			//$name = 'budget_value'. "_" .$bud['id'];
			$name = 'name' . $counter;
			$this->form_validation->set_rules($name, 'Budget Value', 'trim|min_length[2]|max_length[15]');
			$counter++;
		}
 
		if ($_POST)
		{	
			$counter=0;
			foreach ($data['budget'] as $id => $bud)
			{
				$name = 'name' . $counter;
				$data[$name]['value'] = $this->input->post($name, TRUE);
				$counter++;
			}
	
			foreach ($data['budget'] as $id => $bud)
                	{
				$counter=0;
				$child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
				foreach ($child1_budget as $id => $child)
                        	{
                                	$ccname = 'child' . $bud['id'] . $counter;
					$data[$ccname]['value'] = $this->input->post($ccname, TRUE);
					$counter++;
				}
			}
			//$data['sub_budget'] = $this->input->post('sub_budget', TRUE);
			//$data['budget_value']['value'] = $this->input->post('budget_value', TRUE);	
			
		}

		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'budget/allocation', $data);
                        return;
                }
		else
                {
			/*$track = 1;
	  		foreach ($data['budget'] as $id => $bud)
                        {
                                $counter = 0;
				$count = 0;
				$sum = 0;
                                $child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
                                foreach ($child1_budget as $id => $child)
                                {
                                        $ccname = 'child' . $bud['id'] . $counter;
                                        $$ccname = $this->input->post($ccname, TRUE);
					$sum = $sum + $$ccname;
                                        $counter++;
                                }
				$name = 'name' . $count;
                                $$name = $this->input->post($name, TRUE);
				if($$name == $sum)
				{
					continue;
				} else{
					
					$this->messages->add('Sum of amount allocated to child budgets and ' . $bud['name'] . 'should be equal', 'error');
		                        $this->template->load('template', 'budget/allocation', $data);
                		        return;
				}
                        }*/

                        /*$data_budget_name = $this->input->post('budget', TRUE);
                        $data_child_budget = $this->input->post('sub_budget', TRUE);
                        $data_value = $this->input->post('budget_value', TRUE);
			*/
			
			//my code
			/*
			$counter=0;
			$count =0;
			$this->db->trans_start();
			foreach ($data['budget'] as $id => $bud)
			{
				$name = 'name' . $counter;
				$$name = $this->input->post($name, TRUE);
				$counter++;
			//}	
				$date = date_php_to_mysql(date_today_php());
				$insert_data = array(
                                //'code' => $data_code,
        	                        'code' => $bud['code'],
                	                'allocation_amount' => $$name,
                        	        //'reappropriation_amount' => '',
                                	'creation_date' => $date,
	                        );
				
                        	if ( ! $this->db->insert('budget_allocate', $insert_data))
                        	{
                                	$this->db->trans_rollback();
                                	$this->messages->add('Error adding ' . $$name .'to Budget Allocate.', 'error');
                                	$this->logger->write_message("error", "Error adding Budget called " . $$name  . ' by user ' . $username);
                                	//$this->template->load('template', 'group/add', $data);
                                	$this->template->load('template', 'budget/allocation', $data);
                                	return;
                        	} else {
                                	$this->db->trans_complete();
                                	$this->messages->add('Added to Budget Allocate- ' . '.', 'success');
                                	$this->logger->write_message("success", "Added Budget called " . $data_budget_name  . ' by user ' . $username);
                                	redirect('budgetl');
                                	return;
                        	}
				
			//foreach ($data['budget'] as $id => $bud)
                	//{
			//	$counter=0;
				$child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
				foreach ($child1_budget as $id => $child)
                        	{
                                	$ccname = 'child' . $bud['id'] . $count;
					$$ccname = $this->input->post($ccname, TRUE);
					$count++;
					$date = date_php_to_mysql(date_today_php());
					$insert_data = array(
						'code' => $child['code'],
						'allocation_amount' => $$cname,
						//'reappropriation_amount' => '',
						'creation_date' => $date,
					);
                        
					if ( ! $this->db->insert('budget_allocate', $insert_data))
                        		{
                                		$this->db->trans_rollback();
                                		$this->messages->add('Error adding ' . $$ccname 'to Budget Allocate.', 'error');
                                		$this->logger->write_message("error", "Error adding Budget called " . $$cname  . ' by user ' . $username);
                                		//$this->template->load('template', 'group/add', $data);
                                		$this->template->load('template', 'budget/allocation', $data);
                                		return;
                        		} else {
                                		$this->db->trans_complete();
                                		$this->messages->add('Added to Budget Allocate- ' . '.', 'success');
                                		$this->logger->write_message("success", "Added Budget called " . ''  . ' by user ' . $username);
                                		redirect('budgetl');
                                		return;
                        		}
				}
		//		}//else
			}*/
			//mmmmmy code
			$this->db->trans_complete();
			$this->messages->add('Added values to Budget Allocate- ' . '.', 'success');
                        $this->logger->write_message("success", "Added Budget " . ''  . ' by user ' . $username);
                        redirect('budgetl');
                        return;
			$this->db->trans_start();
                        $insert_data = array(
                                //'code' => $data_code,
                                'code' => '',
                                'allocation_amount' => '',
                                'reappropriation_amount' => '',
                                'creation_date' => '',
                        );

                        //if ( ! $this->db->insert('groups', $insert_data))
                        if ( ! $this->db->insert('budget_allocate', $insert_data))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error adding Budget Allocate- ' . '.', 'error');
                                $this->logger->write_message("error", "Error adding Budget called " . $data_budget_name  . ' by user ' . $username);
                                //$this->template->load('template', 'group/add', $data);
                                $this->template->load('template', 'budget/allocation', $data);
                                return;
                        } else {
                                $this->db->trans_complete();
                                $this->messages->add('Added to Budget Allocate- ' . '.', 'success');
                                $this->logger->write_message("success", "Added Budget called " . $data_budget_name  . ' by user ' . $username);
                                redirect('budgetl');
                                return;
                        }

		}//else
	//	$this->load->view('budget/allocation', $data);		
		$this->template->load('template', 'budget/allocation', $data);
                return ;
	}

	function reappro(){
                $username = $this->config->item('account_name');
                $this->load->model('Budget_model');
                $this->template->set('page_title', 'Budget Reappropriation');
                $budget_arr = array();
                $child_budget = array();
                $child1_budget = array();
                $budget_arr = $this->Budget_model->get_selected_budgets();
                $data['budget'] = $budget_arr;
                $child_budget = $this->Budget_model->get_sub_budget();
                //$data['sub_budget'] = FALSE;
                //$data['sub_budget'. "_" .$data['id']] = FALSE;

                // Getting names of child budgets
                foreach ($data['budget'] as $id => $bud)
                {
                        $counter = 0;
                        $child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
                        foreach ($child1_budget as $id => $child)
                        {
                                $ccname = 'child' . $bud['id'] . $counter;
                                $data[$ccname] = array(
                                        'name' => 'child_budget_value'. "_" .$child['id'],
                                        'id' => $child['id'],
                                        'maxlength' => '100',
                                        'size' => '40',
                                        'value' => '',
                                );
                        $counter++;
                        }
                        $counter1 = 'counter' . $bud['id'];
                        $data[$counter1] = $counter;
                }
		$data['child_budget'] = $child_budget;

                // Getting names of parent budgets
                $counter = 0;
                foreach ($data['budget'] as $id => $bud)
                {
                        //$name = 'budget_value'. "_" .$bud['id'];
                        $name = 'name' . $counter;
                        $data[$name] = array(
                                'name' => 'budget_value'. "_" .$bud['id'],
                                'id' => $bud['id'],
                                'maxlength' => '100',
                                'size' => '40',
                                'value' => '',
                                );
                        $counter++;
                        $check_name = 'sub_budget'. "_" .$bud['id'];
                        $data[$check_name] = FALSE;
                }

                /* Form Validation */
                foreach ($data['budget'] as $id => $bud)
                {
                        $counter=0;
                        $child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
                        foreach ($child1_budget as $id => $child)
                        {
                                 $ccname = 'child' . $bud['id'] . $counter;
                                 //$data[$ccname]['value'] = $this->input->post($ccname, TRUE);
                                 $this->form_validation->set_rules($ccname, 'Budget Value', 'trim|min_length[2]|max_length[15]');
                                 $counter++;
                        }
                }
		$counter = 0;
                foreach ($data['budget'] as $id => $bud)
                {
                        //$name = 'budget_value'. "_" .$bud['id'];
                        $name = 'name' . $counter;
                        $this->form_validation->set_rules($name, 'Budget Value', 'trim|min_length[2]|max_length[15]');
                        $counter++;
                }
 
                if ($_POST)
                {
                        $counter=0;
                        foreach ($data['budget'] as $id => $bud)
                        {
                                $name = 'name' . $counter;
                                $data[$name]['value'] = $this->input->post($name, TRUE);
                                $counter++;
                        }

                        foreach ($data['budget'] as $id => $bud)
                        {
                                $counter=0;
                                $child1_budget = $this->Budget_model->get_parent_budget($bud['id']);
                                foreach ($child1_budget as $id => $child)
                                {
                                        $ccname = 'child' . $bud['id'] . $counter;
                                        $data[$ccname]['value'] = $this->input->post($ccname, TRUE);
                                        $counter++;
                                }
                        }
                        //$data['sub_budget'] = $this->input->post('sub_budget', TRUE);
                        //$data['budget_value']['value'] = $this->input->post('budget_value', TRUE);    

                }

                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'budget/allocation', $data);
                        return;
                }
                else
                {
			$this->db->trans_complete();
                        $this->messages->add('Added values to Budget Allocate- ' . '.', 'success');
                        $this->logger->write_message("success", "Added Budget " . ''  . ' by user ' . $username);
                        redirect('budgetl');
                        return;
                        $this->db->trans_start();
                        $insert_data = array(
                                //'code' => $data_code,
                                'code' => '',
                                'allocation_amount' => '',
                                'reappropriation_amount' => '',
                                'creation_date' => '',
                        );

                        //if ( ! $this->db->insert('groups', $insert_data))
                        if ( ! $this->db->insert('budget_allocate', $insert_data))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error adding Budget Allocate- ' . '.', 'error');
                                $this->logger->write_message("error", "Error adding Budget called " . $data_budget_name  . ' by user ' . $username);
                                //$this->template->load('template', 'group/add', $data);
                                $this->template->load('template', 'budget/allocation', $data);
                                return;
                        } else {
                                $this->db->trans_complete();
                                $this->messages->add('Added to Budget Allocate- ' . '.', 'success');
                                $this->logger->write_message("success", "Added Budget called " . $data_budget_name  . ' by user ' . $username);
                                redirect('budgetl');
                                return;
                        }

                }//else
        //      $this->load->view('budget/allocation', $data);          
                $this->template->load('template', 'budget/allocation', $data);
                return ;
        }
}

/* End of file budget.php */
/* Location: ./system/application/controllers/budget.php */
