<?php

class Projection extends Controller {

	var $reappropriation = array();
	var $child_controller = array();
	var $parent_controller = array();
	var $username;
	//var $unallocated_child_controller = array();
	//var $unallocated_parent_controller = array();
	var $a = 2;
	var $counter = 0;

	function Projection()
	{
		parent::Controller();
		$this->load->model('Budget_model');
		$this->username = $this->config->item('account_name');
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
		
		//$username = $this->config->item('account_name');
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

               		$data_type = $this->input->post('projection_type', TRUE);
			$data_earned_amount = 0.00;

			/* Check if group incomes present */
			$this->db->select('id')->from('groups')->where('code', $my_values[0]);
			if ($this->db->get()->num_rows() < 1)
      			{
				//$this->db->select('id')->from('ledgers')->where('code', $my_values[0]);
				$this->db->select('id,op_balance')->from('ledgers')->where('code', $my_values[0]);
				//projection does not exist in ledgers
				if ($this->db->get()->num_rows() < 1)
				{
               				$this->messages->add('Invalid projection code.', 'error');
					$this->template->load('template', 'projection/add', $data);
              				return;
				}
				else{
					//projection exists in ledgers
					$this->db->select('id,op_balance')->from('ledgers')->where('code', $my_values[0]);
					$ledger_parent_q = $this->db->get();
			                foreach ($ledger_parent_q->result() as $row)
			                {
			                        $data_earned_amount = $row->op_balance;
			                }
					
					//update bd_balance and earned_amount for parent projection
					$this->updateParentProjection($my_values[0], $data_earned_amount, $data_amount);
				}
       			}
			else
			{
				//update bd_balance for parent projection
                                $this->updateParentProjection($my_values[0], 0, $data_amount);	
			}

			//update value for target projection
			$this->db->select('bd_balance, earned_amount')->from('projection')->where('code', '60');
                        $projection_q = $this->db->get();
                        foreach ($projection_q->result() as $row)
                        {
                                $earned_amount = $row->earned_amount;
                                $bd_balance = $row->bd_balance;
                        }
                        $earned_amount = $earned_amount +  $data_earned_amount;
                        $bd_balance = $bd_balance + $data_amount;

                        //Adding data to projection table for target projection
                        $this->db->trans_start();
                        $update_data = array(
                                'code' => '60',
                                'bd_balance' => $bd_balance,
                                'earned_amount' => $earned_amount,
                        );

                        if ( ! $this->db->where('code', '60')->update('projection', $update_data))
                        {
                             $this->db->trans_rollback();
                             $this->messages->add('Error updating earned_amount for Target Projection' . ' by user ' . $this->username . '.', 'error');
                             $this->logger->write_message("error", "Error updating earned_amount for Target Projection" . ' by user ' . $this->username);
                             redirect('projectionl');
                             return;
                        } else {
                             $this->db->trans_complete();
                             //$this->messages->add('Updated Projection - ' . $parent_code . ' by user ' . $username . '.', 'success');
                             $this->logger->write_message("success", "Updated Target Projection" . ' by user ' . $this->username);
                        }

			//Adding data to projection_allocate table for target projection
                        $today = date("Y-m-d H:i:s");
                        $this->db->trans_start();
                        $insert_data1 = array(
                                'code' => '60',
                                'allocation_amount' => $bd_balance,
                                'creation_date' => $today,
                        );

                        if ( ! $this->db->insert('projection_allocate', $insert_data1))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error addding projection amount for Target Projection' . ' by user ' . $this->username . '.', 'error');
                                $this->logger->write_message("error", "Error adding Projection amount for Target Projection" . ' by user ' . $this->username);
                                $this->template->load('template', 'projection/add', $data);
                                return;
                        } else {
                                $this->db->trans_complete();
                                //$this->messages->add('Added Projection amount- ' . $data_amount . ' by user ' . $username . '.', 'success');
                                $this->logger->write_message("success", "Added Projection amount for Target Projection" . ' by user ' . $this->username);
                                //redirect('projectionl');
                                //return;
                        }

			//Adding data to projection table
			$this->db->trans_start();
			$insert_data = array(
				'code' => $my_values[0],
				'group_id' => $data_parent_id,
				'projection_name' => $my_values[1],
               			'bd_balance' => $data_amount,
               			'type' => $data_type,
				'earned_amount' => $data_earned_amount,
 			);

       			if ( ! $this->db->insert('projection', $insert_data))
       			{
               			$this->db->trans_rollback();
            			$this->messages->add('Error addding Projection - ' . $my_values[1] . ' by user ' . $this->username . '.', 'error');
       		        	$this->logger->write_message("error", "Error adding Projection called " . $data_name  . ' by user ' . $this->username);
				$this->template->load('template', 'projection/add', $data);
               			return;
       			} else {
               			$this->db->trans_complete();
               			$this->messages->add('Added Projection - ' . $my_values[1] . ' by user ' . $this->username . '.', 'success');
             			$this->logger->write_message("success", "Added Projection called " . $data_name  . ' by user ' . $this->username);
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
              			$this->messages->add('Error addding projection amount - ' . $data_amount  . ' by user ' . $this->username . '.', 'error');
	                       	$this->logger->write_message("error", "Error adding Projection amount " . $data_amount  . ' by user ' . $this->username);
                         	$this->template->load('template', 'projection/add', $data);
               	         	return;
                       	} else {
                               	$this->db->trans_complete();
                               	$this->messages->add('Added Projection amount- ' . $data_amount . ' by user ' . $this->username . '.', 'success');
                               	$this->logger->write_message("success", "Added Projection amount " . $data_amount  . ' by user ' . $this->username);
                               	redirect('projectionl');
                               	return;
                       	}
			//}
		}
		return;
	}


	function updateParentProjection($child_code, $op_balance, $projection_amount)
	{
		//$username = $this->config->item('account_name');

		//calculate length of parent code
		$parent_code = substr($child_code, 0, -2);
		$earned_amount = 0.00;
		$bd_balance = 0.00;
		$len = $this->countDigits($parent_code);
		if($len > 0)
		{
			//update earned_amount and bd_balance of parent projection
			$this->db->select('bd_balance, earned_amount')->from('projection')->where('code', $parent_code);
			$projection_q = $this->db->get();
                        foreach ($projection_q->result() as $row)
                        {
                                 $earned_amount = $row->earned_amount;
				$bd_balance = $row->bd_balance;
                        }
			$earned_amount = $earned_amount + $op_balance;
			$bd_balance = $bd_balance + $projection_amount;

			//Adding data to projection table
			$this->db->trans_start();
			$update_data = array(
	                        'code' => $parent_code,
				'bd_balance' => $bd_balance,
                                'earned_amount' => $earned_amount,
                        );

                        if ( ! $this->db->where('code', $parent_code)->update('projection', $update_data))
                        {
           	             $this->db->trans_rollback();
                             $this->messages->add('Error updating earned_amount for Projection - ' . $parent_code . ' by user ' . $this->username . '.', 'error');
                             $this->logger->write_message("error", "Error updating earned_amount for Projection - " . $parent_code  . ' by user ' . $this->username);
                             redirect('projectionl');
                             return;
                        } else {
                             $this->db->trans_complete();
                             //$this->messages->add('Updated Projection - ' . $parent_code . ' by user ' . $username . '.', 'success');
                             $this->logger->write_message("success", "Updated Projection called " . $parent_code  . ' by user ' . $this->username);
                        }
		
			//Adding data to projection_allocate table
                        $today = date("Y-m-d H:i:s");
                        $this->db->trans_start();
                        $insert_data1 = array(
                                'code' => $parent_code,
                                'allocation_amount' => $bd_balance,
                                'creation_date' => $today,
                        );

                        if ( ! $this->db->insert('projection_allocate', $insert_data1))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error addding projection - ' . $parent_code  . ' by user ' . $this->username . '.', 'error');
                                $this->logger->write_message("error", "Error adding Projection " . $parent_code  . ' by user ' . $this->username);
                                //$this->template->load('template', 'projection/add', $data);
				redirect('projectionl');
                                return;
                        } else {
                                $this->db->trans_complete();
                                //$this->messages->add('Added Projection - ' . $ . ' by user ' . $username . '.', 'success');
                                $this->logger->write_message("success", "Added Projection " . $parent_code  . ' by user ' . $this->username);
                                //redirect('projectionl');
                                //return;
                        }

			//Update parent projection for given parent code
			$this->updateParentProjection($parent_code, $op_balance, $projection_amount);
		}
		return;
		
	}//method updateParentProjection

	/*function edit($id)
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

		/* Check access */
                if ( ! check_access('reappropriate projection'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('projectionl');
                        return;
                }

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

			// Making an array (final_projection) of updated values
			foreach($this->reappropriation['projection'] as $id => $proj)
			{
				$name = 'projection_value_' .$proj['id'];
				$new_amount =  $this->input->post($name, TRUE);
				//add value to final array only if its value has been changed
				if($new_amount != $proj['bd_balance'])
				{
					$final_projection[$count1]['id'] = $proj['id'];
					$final_projection[$count1]['code'] = $proj['code'];
					$final_projection[$count1]['amount'] = $new_amount;
					$count1++;			
					/*if($proj['code'] == $account_code)
					{
						$income_amount = $new_amount;	
					}*/
				}
                        }//for

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
                                                $this->messages->add('Error inserting value to Projection Allocate for projection- ' . $cbud['code'] . ' by user ' . $this->username .  '.', 'error');
                                                $this->logger->write_message("error", "Error inserting value for Projection code " . $cbud['code']  . ' by user ' . $this->username);
                                                //$this->template->load('template', 'budget/reappropriation', $this->reappropriation);
						redirect('projectionl');
                                               	return;
                                        } else {
                                                $this->db->trans_complete();
                                                $this->messages->add('Successfully inserted value to Projection Allocate- ' . ' by user ' . $this->username . '.', 'success');
                                                $this->logger->write_message("success","Successfully inserted value for Projection code" . $cbud['code']   . ' by user ' . $this->username);
                                       }

					$this->db->trans_start();
                                        $update_data = array(
                                        	'code' => $cbud['code'],
                                                'bd_balance' => $cbud['amount'],
                                        );
                                                                                
                                        if ( ! $this->db->where('code', $cbud['code'])->update('projection', $update_data))
                                        {
                                        	$this->db->trans_rollback();
                                                $this->messages->add('Error updating value to Projection for projection- ' . $cbud['code'] . ' by user ' . $this->username .  '.', 'error');
                                                $this->logger->write_message("error", "Error updating value for Projection code " . $cbud['code']  . ' by user ' . $this->username);
                                               // $this->template->load('template', 'budget/reappropriation', $this->reappropriation);
						redirect('projectionl');
                                                return;
                                        } else {
                                                $this->db->trans_complete();
                                                $this->messages->add('Successfully updated value for Projection- ' . ' by user ' . $this->username . '.', 'success');
                                                $this->logger->write_message("success","Successfully updated value for Projection code " . $cbud['code']   . ' by user ' . $this->username);
                                        }
		
				}//for db
				
	//		}//else

		}//else

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

}//class

/* End of file projection.php */
/* Location: ./system/application/controllers/projection.php */
?>
