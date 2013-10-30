<?php

class Projection extends Controller {

	var $reappropriation = array();
	var $child_controller = array();
	var $parent_controller = array();
	var $unallocated_child_controller = array();
	var $unallocated_parent_controller = array();
	var $a = 2;
	var $counter = 0;

	function Projection()

	{
		parent::Controller();
		$this->load->model('Budget_model');
		return;
	}	

	function index()
	{
		redirect('projection/add');
		return;
	}

	function add()
        {
		$this->load->library('validation');
                $this->template->set('page_title', 'New Projection');

		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));
		
		$username = $this->config->item('account_name');
                /* Check access */
                if ( ! check_access('create projection'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('projectionl');
                        return;
                }

                /* Check for account lock */
                if ($this->config->item('account_locked') == 1)
                {
                        $this->messages->add('Projection is locked.', 'error');
                        redirect('projectionl');
                        return;
                }
		
		/* Get code for account 'Expenses' */
		$account_code = $this->Budget_model->get_account_code('Incomes');	
		$data['group_incomes'] = $this->Budget_model->get_selected_groups('Incomes');
                $data['group_incomes_active'] = 0;


                $data['projection_type'] = array(
                        'name' => 'projection_type',
                        'id' => 'projection_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

                $data['projection_amount'] = array(
                        'name' => 'projection_amount',
                        'id' => 'projection_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

		/* Form validations */
                $this->form_validation->set_rules('group_incomes', 'Projection name', 'trim|required');
		$this->form_validation->set_rules('projection_amount', 'Projection Amount', 'trim|required|min_length[1]|max_length[100]');
		$this->form_validation->set_rules('projection_type', 'Projection type', 'trim|min_length[2]|max_length[100]');
		
		/* Re-populating form */
                if ($_POST)
                {
			$data['group_incomes_active'] = $this->input->post('group_incomes', TRUE);
			$data['projection_amount'] = $this->input->post('projection_amount', TRUE);
                	$data['projection_type']['value'] = $this->input->post('projection_type', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'projection/add', $data);
                        return;
                }
                else
		{
			$data_name = $this->input->post('group_incomes', TRUE);
			$my_values = explode('#',$data_name);
			
			$data_amount = $this->input->post('projection_amount', TRUE);
			$sum = $data_amount;
			if($my_values[1] == 'Incomes')
				$data_parent_id = '0';
			else
			{
				$data_parent_code = $this->Budget_model->get_parent($my_values[1]);
                	        $data_parent_id = $this->Budget_model->get_groupid_budgetname($data_parent_code, 'projection');
                        	//Get amount allocated to parent projection
	                        $parent_amount = $this->Budget_model->get_allocation_amount($data_parent_code, 'projection');
			}

			if($my_values[1] != 'Incomes')
			{
				$child_budget = $this->Budget_model->get_child_budgets($data_parent_id, 'projection');
				$count = 0;
				foreach ($child_budget as $code => $chld)
		                {
					if($chld['code'] != $my_values[0]){
						$allocation_amount = $this->Budget_model->get_allocation_amount($chld['code'], 'projection');
						$sum = $sum + $allocation_amount;
					}
					$count++;
                		}
				if($sum > $parent_amount)
				{
					//Error message
					$temp = $sum - $data_amount;
					$temp = $parent_amount - $temp;
					$this->messages->add('Projection amount cannot exceed from parent projection. Unallocated amount remaining with parent projection is  ' . $temp, 'error');
					redirect('projection/add');
					return;
					
				}
			}//new if

               		$data_type = $this->input->post('projection_type', TRUE);
			$data_earned_amount = 0.00;

			/* Check if group incomes present */
			$this->db->select('id')->from('groups')->where('code', $my_values[0]);
			//projection does not exists in groups
			if ($this->db->get()->num_rows() < 1)
      			{
				$this->db->select('id')->from('ledgers')->where('code', $my_values[0]);
				//$this->db->select('id,op_balance')->from('ledgers')->where('code', $my_values[0]);
				//projection does not exist in ledgers
				if ($this->db->get()->num_rows() < 1)
				{
               				$this->messages->add('Invalid projection code.', 'error');
					$this->template->load('template', 'projection/add', $data);
              				return;
				}
				/*else{
					//projection exists in ledgers
					$ledger_parent_q = $this->db->get();
			                foreach ($ledger_parent_q->result() as $row)
			                {
			                        $data_earned_amount = $row->op_balance;
			                }
					
					//update earned_amount for parent projection
					$this->updateParentProjection($my_values[0], $row->op_balance);
				}*/
       			}
			/*else{
				//projection exists in groups
				$data_earned_amount = 0;
			}*/
					
				//Adding data to projection table
				$this->db->trans_start();
				$insert_data = array(
					'code' => $my_values[0],
					'group_id' => $data_parent_id,
					'projection_name' => $my_values[1],
                       			'bd_balance' => $data_amount,
                       			'type' => $data_type,
					//'earned_amount' => $data_earned_amount,
           	 			);

               			if ( ! $this->db->insert('projection', $insert_data))
               			{
                      			$this->db->trans_rollback();
                       			$this->messages->add('Error addding Projection - ' . $my_values[1] . ' by user ' . $username . '.', 'error');
			
              		        	$this->logger->write_message("error", "Error adding Projection called " . $data_name  . ' by user ' . $username);
					$this->template->load('template', 'projection/add', $data);
                       			return;
               			} else {
                      			$this->db->trans_complete();
                       			$this->messages->add('Added Projection - ' . $my_values[1] . ' by user ' . $username . '.', 'success');
                      			$this->logger->write_message("success", "Added Projection called " . $data_name  . ' by user ' . $username);
              			}

				//Adding data to projection_allocate table
				$today = date("Y-m-d H:i:s");
				$this->db->trans_start();
				$insert_data1 = array(
                       			'code' => $my_values[0],
                       			'allocation_amount' => $data_amount,
                      			'creation_date' => $today,
              			);

              			if ( ! $this->db->insert('projection_allocate', $insert_data1))
               			{
                      			$this->db->trans_rollback();
                       			$this->messages->add('Error addding projection amount - ' . $data_amount  . ' by user ' . $username . '.', 'error');
		                       	$this->logger->write_message("error", "Error adding Projection amount " . $data_amount  . ' by user ' . $username);
        	                 	$this->template->load('template', 'projection/add', $data);
                	         	return;
                        	} else {
                                	$this->db->trans_complete();
                                	$this->messages->add('Added Projection amount- ' . $data_amount . ' by user ' . $username . '.', 'success');
                                	$this->logger->write_message("success", "Added Projection amount " . $data_amount  . ' by user ' . $username);
                                	redirect('projectionl');
                                	return;
                        	}
			//}
		}
		return;
	}


	/*function updateParentProjection($code, $op_balance)
	{
		//calculate length of parent code
		$len = $this->countDigits($cb['code']);
	}

	function edit($id)
	{
		$this->template->set('page_title', 'Edit Budget');
		$username = $this->config->item('account_name');

		// Check access /
		if ( ! check_access('edit budget'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('budgetl');
			return;
		}

		// Check for account lock /
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('budgetl');
			return;
		}

		// Checking for valid data /
		$id = $this->input->xss_clean($id);
		$id = (int)$id;
		if ($id < 1) {
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('budgetl');
			return;
		}

		// Loading current budget /
		$this->db->from('budgets')->where('id', $id);
		$budget_data_q = $this->db->get();
		if ($budget_data_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('budgetl');
			return;
		}
		$budget_data = $budget_data_q->row();

		/* Form fields /
		

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


		/* Form validations /
                $this->form_validation->set_rules('budget_over', 'Over Expence Allowed', 'trim|required|min_length[1]|max_length[100]' . $id);

		/* Re-populating form /
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
			/* Check if parent budget id present /
			
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
		/* Check access /
		if ( ! check_access('delete budget'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('budgetl');
			return;
		}

		/* Check for account lock /
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('budgetl');
			return;
		}

		/* Checking for valid data /
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

		/* Get the budget details /
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

		/* Deleting budget /
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
	}*/

	function reappro(){
                $username = $this->config->item('account_name');
		//$parent_controller = array();
		//$child_controller = array();

		/* Check access */
                if ( ! check_access('reappropriate projection'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('projectionl');
                        return;
                }

                //$this->load->model('Budget_model');
		//$this->load->helper('array');
		$main_projection_amount = 0;
                $this->template->set('page_title', 'Projection Reappropriation');
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));

		$account_code = $this->Budget_model->get_account_code('Incomes');
                $projection_arr = array();
		$counter = 0;
		$final_projection = array();

                $projection_arr = $this->Budget_model->get_projection();
                $this->reappropriation['projection'] = $projection_arr;
		foreach ($projection_arr as $id => $proj)
                {
                        $name = 'projection_value_' .$proj['id'];
                        $this->reappropriation[$name] = array(
                        	'name' => $name,
                                'id' => $proj['id'],
                                'maxlength' => '100',
                                'size' => '40',
                                //'value' => '',
                                'value' => $proj['bd_balance'],
                        );
                        $counter++;
                }
		
		$counter = 0;
		$count = 0;
			
		//code for generating unallocated projection
		$sum = 0;
		foreach ($this->reappropriation['projection'] as $id => $proj)
                {	
			
			if($proj['code'] == $account_code)
                        {
                                $main_projection_amount = $proj['bd_balance'];
                        }

	        	$temp = $this->countDigits($proj['code']);
			if($temp == 4){
				$sum = $sum + $proj['bd_balance'];
				$this->unallocated_parent_controller[$counter]['id'] = $proj['id'];
				$this->unallocated_parent_controller[$counter]['code'] = $proj['code'];
                                $this->unallocated_parent_controller[$counter]['amount'] = $proj['bd_balance'];
				$counter++;
			}
			else{
                                $this->unallocated_child_controller[$count]['id'] = $proj['id'];
                                $this->unallocated_child_controller[$count]['code'] = $proj['code'];
                                $this->unallocated_child_controller[$count]['amount'] = $proj['bd_balance'];
                                $count++;
                        }
		}
		$temp_amount = $main_projection_amount - $sum;
		$temp_name = 'unallocated_value_' . $account_code;
		$this->reappropriation[$temp_name] = $temp_amount;

		foreach($this->unallocated_parent_controller as $id => $pb)
                {
	                $this->calculate_unallocated_projection(4, $pb['code'], $pb['amount']);
                }

		// Form Validation 
             	foreach ($this->reappropriation['projection'] as $id => $proj)
                {
			$name = 'projection_value_' .$proj['id'];
                        $this->form_validation->set_rules($name, 'Projection Value', 'trim|min_length[2]|max_length[15]');
                }		

		if ($_POST)
                {
                        foreach ($this->reappropriation['projection'] as $id => $proj)
                        {
                                $name = 'projection_value_' .$proj['id'];
                                $this->reappropriation[$name]['value'] = $this->input->post($name, TRUE);
                        }
		}


		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'projection/reappropriation', $this->reappropriation);
                        return;
                }
                else
                {
			
			$Sum = 0;
			$counter = 0;
			$count = 0;
			$count1 = 0;
			$income_amount = 0;

			foreach($this->reappropriation['projection'] as $id => $proj)
			{
				$name = 'projection_value_' .$proj['id'];
				$new_amount =  $this->input->post($name, TRUE);
				if($new_amount != $proj['bd_balance'])
				{
					$final_projection[$count1]['id'] = $proj['id'];
					$final_projection[$count1]['code'] = $proj['code'];
					$final_projection[$count1]['amount'] = $new_amount;
					$count1++;			
					if($proj['code'] == $account_code)
					{
						$income_amount = $new_amount;	
					}
				}

				if($bud['code'] != $account_code)
				{
                                	$temp = $this->countDigits($proj['code']);
                                	if($temp == 4)
                                	{
						$this->parent_controller[$counter]['id'] = $proj['id'];
                                        	$this->parent_controller[$counter]['code'] = $proj['code'];
						$this->parent_controller[$counter]['amount'] = $new_amount;
                                        	$Sum = $Sum + $this->parent_controller[$counter]['amount'];
                                        	$counter++;
                                	}//if
                                	else
                                	{
						$this->child_controller[$count]['id'] = $proj['id'];
                                        	$this->child_controller[$count]['code'] = $proj['code'];
						$this->child_controller[$count]['amount'] = $new_amount;
                                        	$count++;
                                	}
				}//if
                        }//for

			if($income_amount == '0')
			{
				$income_amount = $this->Budget_model->get_allocation_amount($account_code, 'projection');
			}

                        if($Sum > $income_amount)
                        {
                                //Error Msg
                                $this->messages->add('Sum of parent projection does not match with value of "Incomes"', 'error');
                                $this->logger->write_message("error", "Sum of projection " . $Sum  . " does not match with value of projection 'Incomes'" . ' by user ' . $username);
                                $this->template->load('template', 'projection/reappropriation', $this->reappropriation);
                                return;
                        } else{

				foreach($this->parent_controller as $id => $pb)
				{
					$this->calculate(4, $pb['code'], $pb['amount']);
				}
				
			
				$today = date("Y-m-d H:i:s");
				foreach($final_projection as $id => $cbud)
				{
					$this->db->trans_start();
                                        $insert_data = array(
                                        	'code' => $cbud['code'],
                                                'allocation_amount' => $cbud['amount'],
                                                'creation_date' => $today,
                                        );

                                        if ( ! $this->db->insert('projection_allocate', $insert_data))
                                        {
                                        	$this->db->trans_rollback();
                                                $this->messages->add('Error inserting value to Projection Allocate for projection- ' . $cbud['code'] . ' by user ' . $username .  '.', 'error');
                                                $this->logger->write_message("error", "Error inserting value for Projection code " . $cbud['code']  . ' by user ' . $username);
                                                //$this->template->load('template', 'budget/reappropriation', $this->reappropriation);
						redirect('projectionl');
                                               	return;
                                        } else {
                                                $this->db->trans_complete();
                                                $this->messages->add('Successfully inserted value to Projection Allocate- ' . ' by user ' . $username . '.', 'success');
                                                $this->logger->write_message("success","Successfully inserted value for Projection code" . $cbud['code']   . ' by user ' . $username);
                                       }

					$this->db->trans_start();
                                        $update_data = array(
                                        	'code' => $cbud['code'],
                                                'bd_balance' => $cbud['amount'],
                                        );
                                                                                
                                        if ( ! $this->db->where('code', $cbud['code'])->update('projection', $update_data))
                                        {
                                        	$this->db->trans_rollback();
                                                $this->messages->add('Error updating value to Projection for projection- ' . $cbud['code'] . ' by user ' . $username .  '.', 'error');
                                                $this->logger->write_message("error", "Error updating value for Projection code " . $cbud['code']  . ' by user ' . $username);
                                               // $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
						redirect('projectionl');
                                                return;
                                        } else {
                                                $this->db->trans_complete();
                                                $this->messages->add('Successfully updated value for Projection- ' . ' by user ' . $username . '.', 'success');
                                                $this->logger->write_message("success","Successfully updated value for Projection code " . $cbud['code']   . ' by user ' . $username);
                                        }
		
				}//for db
				
			}//else

		}//else

		//$this->template->load('template', 'budget/reappropriation', $this->reappropriation);
		redirect('projectionl');
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
                        $this->messages->add('Sum of child projection does not match with value of unallocated projection ' . $code, 'error');
			$this->messages->add('Please try updating values again', 'error');
               	        $this->logger->write_message("error", "Sum of child projection does not match with value of projection " . $code . ' by user ' . $username);
                      		// $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
			redirect('projection/reappro');
       	                return;
		}
	}

	function calculate_unallocated_projection($i, $code, $amount)
	{
		$sum = 0;
		$count = 0;
		$unallocated_projection_amount = '';
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
					$this->calculate_unallocated_projection($i + $this->a, $cb['code'], $cb['amount']);
				}
			}		
		}
		
		if($count>0 || $this->countDigits($code)==4)
			$unallocated_projection_amount = $amount - $sum;
                $name = 'unallocated_value_' . $code;
		$this->reappropriation[$name] = $unallocated_projection_amount;
		
		return;
	}

}//class

/* End of file projection.php */
/* Location: ./system/application/controllers/projection.php */
?>
