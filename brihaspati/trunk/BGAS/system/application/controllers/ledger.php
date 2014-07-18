<?php

class Ledger extends Controller {

var $ledger_code = 0;
var $username;

	function Ledger()
	{
		parent::Controller();
		$this->load->model('Ledger_model');
		$this->load->model('Group_model');
		$this->load->model('Budget_model');
		$this->username = $this->config->item('account_name');
		return;
	}

	function index()
	{
		redirect('ledger/add');
		return;
	}

	function add()
	{
		$this->template->set('page_title', 'New Ledger');
		$this->load->library('accountlist');
                $asset = new Accountlist();

		/* Check access */
		if ( ! check_access('create ledger'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('account');
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('account');
			return;
		}

		/* Form fields */
/*		$data['ledger_code'] = array(
			'name' => 'ledger_code',
			'id' => 'ledger-code',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		//	'readonly' => 'readonly',
		); 
*/	
	
		$data['ledger_name'] = array(
			'name' => 'ledger_name',
			'id' => 'ledger_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['ledger_group_id'] = $this->Group_model->get_ledger_groups();
		$data['op_balance'] = array(
			'name' => 'op_balance',
			'id' => 'op_balance',
			'maxlength' => '15',
			'size' => '15',
			'value' => '',
		);
		$data['ledger_group_active'] = 0;
		$data['op_balance_dc'] = "D";
		$data['ledger_type_cashbank'] = FALSE;
		$data['reconciliation'] = FALSE;

		/* Form validations */
//		$this->form_validation->set_rules('ledger_code', 'Ledger code', 'trim|required|min_length[2]|max_length[100]|unique[ledgers.code]');
		$this->form_validation->set_rules('ledger_name', 'Ledger name', 'trim|required|min_length[2]|max_length[100]|unique[ledgers.name]');
		$this->form_validation->set_rules('ledger_group_id', 'Parent group', 'trim|required');
		$this->form_validation->set_rules('op_balance', 'Opening balance', 'trim|currency');
		$this->form_validation->set_rules('op_balance_dc', 'Opening balance type', 'trim|required|is_dc');

		/* Re-populating form */
		if ($_POST)
		{
//			$data['ledger_code']['value'] = $this->input->post('ledger_code', TRUE);
			$data['ledger_name']['value'] = $this->input->post('ledger_name', TRUE);
			$data['op_balance']['value'] = $this->input->post('op_balance', TRUE);
			$data['ledger_group_active'] = $this->input->post('ledger_group_id', TRUE);
			$data['op_balance_dc'] = $this->input->post('op_balance_dc', TRUE);
			$data['ledger_type_cashbank'] = $this->input->post('ledger_type_cashbank', TRUE);
			$data['reconciliation'] = $this->input->post('reconciliation', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'ledger/add', $data);
			return;
		}
		else
		{
			$data_group_id=0;	
//			$data_code = $this->input->post('ledger_code', TRUE);
			$data_name = $this->input->post('ledger_name', TRUE);
			//$data_group_id = $this->input->post('ledger_group_id', TRUE);
			$data_group_name = $this->input->post('ledger_group_id', TRUE);

			/*$this->db->select('id')->from('groups')->where('name',$data_group_name);
                        $group_parent_qn = $this->db->get();
                        foreach ($group_parent_qn->result() as $row1)
                        {
                                $data_group_id=$row1->id;
                        }*/
			$Array = explode("#", $data_group_name);
			$data_group_id = $Array[1];

			$data_op_balance = $this->input->post('op_balance', TRUE);
			$data_op_balance_dc = $this->input->post('op_balance_dc', TRUE);
			$data_ledger_type_cashbank_value = $this->input->post('ledger_type_cashbank', TRUE);
			$data_reconciliation = $this->input->post('reconciliation', TRUE);

			if ($data_group_id < 5)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'ledger/add', $data);
				return;
			}

			/* Check if parent group id present */
			$this->db->select('id')->from('groups')->where('id', $data_group_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'ledger/add', $data);
				return;
			}

			if (!$data_op_balance) {
				$data_op_balance = "0.00";
			}

			if ($data_ledger_type_cashbank_value == "1")
			{
				$data_ledger_type = 1;
			} else {
				$data_ledger_type = 0;
			}

			if ($data_reconciliation == "1")
			{
				$data_reconciliation = 1;
			} else {
				$data_reconciliation = 0;
			}

			/* The following code has been moved to view */

			$num = $this->Ledger_model->get_numOfChild($data_group_id);
                        $l_code = $this->Group_model->get_group_code($data_group_id);
                      
			if($num == 0)
                        {
                                $data_code = $l_code . '01';
                        } else{
                                $data_code=$this->get_code($num, $l_code);
                        }
			$i=0;	
                        do{
                                if($i>0)
                                {
        	                        //$num=$num+$i;
					$num = $num + 1;
                                        $data_code=$this->get_code($num, $l_code);
                                }
                                $this->db->from('groups');
                                $this->db->select('id')->where('code =',$data_code);
                                $group_q = $this->db->get();
                                $i++;
                       }while($group_q->num_rows()>0);
		
			$this->db->trans_start();
			$insert_data = array(
				'code' => $data_code,
				'name' => $data_name,
				'group_id' => $data_group_id,
				'op_balance' => $data_op_balance,
				'op_balance_dc' => $data_op_balance_dc,
				'type' => $data_ledger_type,
				'reconciliation' => $data_reconciliation,
			);
			if ( ! $this->db->insert('ledgers', $insert_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error addding Ledger account - ' . $data_name . '.', 'error');
				$this->logger->write_message("error", "Error adding Ledger account called " . $data_name);
				$this->logger->write_message("error", "Error adding Ledger account called " . $data_code);
				$this->template->load('template', 'group/add', $data);
				return;
			} else {
				$this->db->trans_complete();
				$this->messages->add('Added Ledger account - ' . $data_name . '.', 'success');
				$this->logger->write_message("success", "Added Ledger account called " . $data_name);
				//redirect('account');
				//return;
			//}
				//get group code
        	                $this->db->select('code')->from('groups')->where('id', $data_group_id);
                	        $group_q = $this->db->get();
                        	$group = $group_q->row();
	                        $group_code = $group->code;
        	                $designated_code = $this->Budget_model->get_account_code('Designated-Earmarked Funds');
                	        $restricted_code = $this->Budget_model->get_account_code('Restricted Funds');
	
        	                if(($designated_code != '' && $this->startsWith($group_code, $designated_code)) || ($restricted_code != '' && $this->startsWith($group_code, $restricted_code))){
					$income_ledger_name = 'Interest on fund '.$data_name;
					$income_code = '';
	
					$num = $this->Ledger_model->get_numOfChild($data_group_id);
	        	                $l_code = $this->Group_model->get_group_code($data_group_id);
		
        		                if($num == 0)
                		        {
                        		        $income_code = $l_code . '01';
		                        } else{
        		                        $income_code=$this->get_code($num, $l_code);
                		        }
                        		$i=0;

	                        	do{
        	                        	if($i>0)
	                	                {
        	                	                //$num=$num+$i;
                	                	        $num = $num + 1;
                        	                	$income_code=$this->get_code($num, $l_code);
	                        	        }
        	                        	$this->db->from('groups');
	                	                $this->db->select('id')->where('code =',$income_code);
        	                	        $group_q = $this->db->get();
                	                	$i++;
	                	       }while($group_q->num_rows()>0);
				
					$this->db->trans_start();
		                        $insert_data = array(
        		                        'code' => $income_code,
                		                'name' => $income_ledger_name,
                        		        'group_id' => $data_group_id,
                                		'op_balance' => $data_op_balance,
	                                	'op_balance_dc' => $data_op_balance_dc,
	        	                        'type' => $data_ledger_type,
        	        	                'reconciliation' => $data_reconciliation,
                	        	);

	                	        if ( ! $this->db->insert('ledgers', $insert_data))
        	                	{
                	                	$this->db->trans_rollback();
	                                	$this->logger->write_message("error", "Error adding Interest on fund " . $data_name);
        	                	} else {
                	                	$this->db->trans_complete();
        	        	                $this->logger->write_message("success", "Added Interest on fund " . $data_name);
                        		}

					$fd_ledger_name = 'Fixed Deposit for '.$data_name;
        	                        $fd_code = '';
					$current_asset_code = $this->Budget_model->get_account_code('Current Assets');
					
					$this->db->select('id')->from('groups')->where('code', $current_asset_code);
		                        $group_q = $this->db->get();
        		                $group = $group_q->row();
                		        $current_asset_id = $group->id;
	
        	                        $num = $this->Ledger_model->get_numOfChild($current_asset_id);
                	                $l_code = $this->Group_model->get_group_code($current_asset_id);

                        	        if($num == 0)
                                	{
                                        	$fd_code = $l_code . '01';
	                                } else{
        	                                $fd_code=$this->get_code($num, $l_code);
                	                }
                        	        $i=0;

                                	do{
                                        	if($i>0)
	                                        {
        	                                        //$num=$num+$i;
                	                                $num = $num + 1;
                        	                        $fd_code=$this->get_code($num, $l_code);
                                	        }
                                        	$this->db->from('groups');
	                                        $this->db->select('id')->where('code =',$fd_code);
        	                                $group_q = $this->db->get();
                	                        $i++;
                        	       }while($group_q->num_rows()>0);
	
        	                        $this->db->trans_start();
                	                $insert_data = array(
                        	                'code' => $fd_code,
                                	        'name' => $fd_ledger_name,
                                        	'group_id' => $current_asset_id,
	                                        'op_balance' => $data_op_balance,
        	                                'op_balance_dc' => 'D',
                	                        'type' => $data_ledger_type,
                        	                'reconciliation' => $data_reconciliation,
                                	);

	                                if ( ! $this->db->insert('ledgers', $insert_data))
        	                        {
                	                        $this->db->trans_rollback();
                        	                $this->logger->write_message("error", "Error adding FD Ledger account for " . $data_name);
                                	} else {
                                        	$this->db->trans_complete();
	                                        $this->logger->write_message("success", "Added FD Ledger account for " . $data_name);
						//redirect('account');
	        	                        //return;
                        	        }
				}
					redirect('account');
                                        return;
				
                        }
		}
		return;
	}

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

/*	function get_numOfChild($parent_id)
 	{
    		$num = $this->Ledger_model->get_numOfChild($parent_id);
		$data = array("NUM"=>$num);
    		//return Json($num, JsonRequestBehavior.AllowGet);
		//return $num;
		echo json_encode ($data) ;
 	}

	function get_group_code($parent_id)
	{
		$l_code = $this->Group_model->get_group_code($parent_id);
		$data = array("LCODE"=>$l_code);
		echo json_encode ($data);
	}

	function set_ledgerCode($ledger_code)
	{
		$this->ledger_code = $ledger_code;
		return;
	}
*/
        function get_code($num, $code)
        {
	        if($num <= 9)
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

/*	function get_ledger_code($data_code)
	{
		$this->db->from('ledgers');
		$this->db->select('id')->where('code =',$data_code);
		$ledger_q = $this->db->get();
		$num = $ledger_q->num_rows();
		$data = array("ROWS"=>$num);
                echo json_encode ($data);
	}

	function get_groupCode($data_code)
	{
		$this->db->from('groups');
                $this->db->select('id')->where('code =',$data_code);
                $group_q = $this->db->get();
		$num = $group_q->num_rows();
                $data = array("ROWS"=>$num);
                echo json_encode ($data);
	}
*/
	function edit($id)
	{
		$this->template->set('page_title', 'Edit Ledger');

		/* Check access */
		if ( ! check_access('edit ledger'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('account');
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('account');
			return;
		}

		/* Checking for valid data */
		$id = $this->input->xss_clean($id);
		$id = (int)$id;
		if ($id < 1)
		{
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('account');
			return;
		}

		/* Loading current group */
		$this->db->from('ledgers')->where('id', $id);
		$ledger_data_q = $this->db->get();
		if ($ledger_data_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('account');
			return;
		}
		$ledger_data = $ledger_data_q->row();

		/* Form fields */

/*		$data['ledger_code'] = array(
			'name' => 'ledger_code',
			'id' => 'ledger_code',
			'maxlength' => '100',
			'size' => '40',
			'value' => $ledger_data->code,
			'readonly' => 'readonly',
		);
*/
		$data['ledger_name'] = array(
			'name' => 'ledger_name',
			'id' => 'ledger_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => $ledger_data->name,
		);
		$data['ledger_group_id'] = $this->Group_model->get_ledger_groups();
		$data['op_balance'] = array(
			'name' => 'op_balance',
			'id' => 'op_balance',
			'maxlength' => '15',
			'size' => '15',
			'value' => $ledger_data->op_balance,
		);
		
		$this->db->select('id, name');
		$this->db->from('groups')->where('id =', $ledger_data->group_id);
		$query_result = $this->db->get();
		$group = $query_result->row();
		
		//$data['ledger_group_active'] = $ledger_data->group_id;
		$data['ledger_group_active'] = $group->name . "#" . $group->id;

		$data['op_balance_dc'] = $ledger_data->op_balance_dc;
		$data['ledger_id'] = $id;

		$old_ledger_parent = $ledger_data->group_id;
		$data_code = $ledger_data->code;	

		// For projection module
		$old_op_balance = $ledger_data->op_balance;	

		if ($ledger_data->type == 1)
			$data['ledger_type_cashbank'] = TRUE;
		else
			$data['ledger_type_cashbank'] = FALSE;
		$data['reconciliation'] = $ledger_data->reconciliation;

		/* Form validations */
//		$this->form_validation->set_rules('ledger_code', 'Ledger code', 'trim|required|min_length[2]|max_length[100]|uniquewithid[ledgers.code.' . $id . ']');
		$this->form_validation->set_rules('ledger_name', 'Ledger name', 'trim|required|min_length[2]|max_length[100]|uniquewithid[ledgers.name.' . $id . ']');
		$this->form_validation->set_rules('ledger_group_id', 'Parent group', 'trim|required');
		$this->form_validation->set_rules('op_balance', 'Opening balance', 'trim|currency');
		$this->form_validation->set_rules('op_balance_dc', 'Opening balance type', 'trim|required|is_dc');

		/* Re-populating form */
		if ($_POST)
		{
//			$data['ledger_code']['value'] = $this->input->post('ledger_code', TRUE);
			$data['ledger_name']['value'] = $this->input->post('ledger_name', TRUE);
			$data['ledger_group_active'] = $this->input->post('ledger_group_id', TRUE);
			$data['op_balance']['value'] = $this->input->post('op_balance', TRUE);
			$data['op_balance_dc'] = $this->input->post('op_balance_dc', TRUE);
			$data['ledger_type_cashbank'] = $this->input->post('ledger_type_cashbank', TRUE);
			$data['reconciliation'] = $this->input->post('reconciliation', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'ledger/edit', $data);
			return;
		}
		else
		{
                        $data_group_id=0;
//			$data_code = $this->input->post('ledger_code', TRUE);
			$data_name = $this->input->post('ledger_name', TRUE);
			//echo $data_group_id = $this->input->post('ledger_group_id', TRUE);
			$data_group_name = $this->input->post('ledger_group_id', TRUE);
			
			/*$this->db->select('id')->from('groups')->where('name',$data_group_name);
                        $group_parent_qn = $this->db->get();
                        foreach ($group_parent_qn->result() as $row1)
                        {
                                $data_group_id=$row1->id;
                        }*/
	
			$Array = explode("#", $data_group_name);
                        $data_group_id = $Array[1];

			$data_op_balance = $this->input->post('op_balance', TRUE);
			$data_op_balance_dc = $this->input->post('op_balance_dc', TRUE);
			$data_id = $id;
			$data_ledger_type_cashbank_value = $this->input->post('ledger_type_cashbank', TRUE);
			$data_reconciliation = $this->input->post('reconciliation', TRUE);

			if ($data_group_id < 5)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'ledger/edit', $data);
				return;
			}

			/* Check if parent group id present */
			$this->db->select('id')->from('groups')->where('id', $data_group_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'ledger/edit', $data);
				return;
			}

			if (!$data_op_balance) {
				$data_op_balance = "0.00";
			}

			/* Check if bank_cash_ledger_restriction both entry present */
			if ($data_ledger_type_cashbank_value != "1")
			{
				$entry_type_all = $this->config->item('account_entry_types');
				foreach ($entry_type_all as $entry_type_id => $row)
				{
					/* Check for Entry types where bank_cash_ledger_restriction is for all ledgers */
					if ($row['bank_cash_ledger_restriction'] == 4)
					{
						$this->db->from('entry_items')->join('entries', 'entry_items.entry_id = entries.id')->where('entries.entry_type', $entry_type_id)->where('entry_items.ledger_id', $id);
						$all_ledger_bank_cash_count = $this->db->get()->num_rows();
						if ($all_ledger_bank_cash_count > 0)
						{
							$this->messages->add('Cannot remove the Bank or Cash Account status of this Ledger account since it is still linked with ' . $all_ledger_bank_cash_count . ' ' . $row['name'] . ' entries.', 'error');
							$this->template->load('template', 'ledger/edit', $data);
							return;
						}
					}
				}
			}

			if ($data_ledger_type_cashbank_value == "1")
			{
				$data_ledger_type = 1;
			} else {
				$data_ledger_type = 0;
			}

			if ($data_reconciliation == "1")
			{
				$data_reconciliation = 1;
			} else {
				$data_reconciliation = 0;
			}
                        
			if($old_ledger_parent != $data_group_id)
			{
				$num = $this->Ledger_model->get_numOfChild($data_group_id);
                        	$l_code = $this->Group_model->get_group_code($data_group_id);
	                        if($num == 0)
        	                {
                	                $data_code = $l_code . '01';
                        	} else{
                                	$data_code=$this->get_code($num, $l_code);
                        	}
                        	$i=0;
                        	do{
                                	if($i>0)
                               		{
                                        	//$num=$num+$i;
						$num = $num + 1;
                                       	 	$data_code=$this->get_code($num, $l_code);
                                	}
                                	$this->db->from('groups');
                                	$this->db->select('id')->where('code =',$data_code);
                                	$group_q = $this->db->get();
                                	$i++;
                       		}while($group_q->num_rows()>0);
			}

			$this->db->trans_start();
			$update_data = array(
				'code' => $data_code,
				'name' => $data_name,
				'group_id' => $data_group_id,
				'op_balance' => $data_op_balance,
				'op_balance_dc' => $data_op_balance_dc,
				'type' => $data_ledger_type,
				'reconciliation' => $data_reconciliation,
			);
			if ( ! $this->db->where('id', $data_id)->update('ledgers', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Ledger account - ' . $data_name . '.', 'error');
				$this->messages->add('Error updating Ledger account - ' . $data_code . '.', 'error');
				$this->logger->write_message("error", "Error updating Ledger account called " . $data_name . $data_code  . " [id:" . $data_id . "]");
				$this->template->load('template', 'ledger/edit', $data);
				return;
			} else {
				/* Deleting reconciliation data if reconciliation disabled */
				if (($ledger_data->reconciliation == 1) AND ($data_reconciliation == 0))
				{
					$this->Ledger_model->delete_reconciliation($data_id);
				}
				$this->db->trans_complete();
				$this->messages->add('Updated Ledger account - ' . $data_name . '.', 'success');
				$this->logger->write_message("success", "Updated Ledger account called " . $data_name . " [id:" . $data_id . "]");
				//redirect('account');
				//return;
			}

			/** 
			 * Check whether 'target_projection' has value 
			 * greater then '0', and the edited ledger code 
			 * exists in projection table. If both the conditions 
			 * are true, than update data in projection table. 
			 */
			$target_projection_amount = 0;
			$this->db->select('bd_balance')->from('projection')->where('id', 1);
			$target_projection = $this->db->get();
			foreach ($target_projection->result() as $row)
				$target_projection_amount = $row->bd_balance;
			if($target_projection_amount > 0)
			{
				$this->db->select('id')->from('projection')->where('code', $data_code);
				if($this->db->get()->num_rows() > 0){
        	                //if($projection_code > 1){
					/**
					 * Projection will be updated only if,
					 * value for op_balance has been updated
					 */
					if($old_op_balance != $data_op_balance){
						/**
						 * Here we are using difference in the previous
						 * and current op_balance of the ledger.
						 * Since, this difference needs to be added or 
						 * subtracted from the target and parent projections.
						 */
						$changed_op_balance = $data_op_balance - $old_op_balance;
						$this->update_projection($data_code, $data_op_balance, $changed_op_balance);
					}
				}
			}
		}
		redirect('account');
		return;
	}

	function update_projection($code, $new_amount, $changed_amount){
		//$username = $this->config->item('account_name');
		$earned_amount = 0;
		$proj_code = '';
		//update value for target projection
                //$this->db->select('earned_amount')->from('projection')->where('code', '60');
                $this->db->select('code, earned_amount')->from('projection')->where('projection_name', 'Target Projection');
                $projection_q = $this->db->get();
                foreach ($projection_q->result() as $row){
                        $earned_amount = $row->earned_amount;
			$proj_code = $row->code;
		}
        
                $earned_amount = $earned_amount +  $changed_amount;

                //Adding data to projection table for target projection
                $this->db->trans_start();
                $update_data = array(
         	       //'code' => '60',
			'code' => $proj_code,
                       'earned_amount' => $earned_amount
                );

                //if ( ! $this->db->where('code', '60')->update('projection', $update_data))
                if ( ! $this->db->where('projection_name', 'Target Projection')->update('projection', $update_data))
                {
                	$this->db->trans_rollback();
                        $this->messages->add('Error updating earned_amount for Target Projection' . ' by user ' . $this->username . '.', 'error');
                        $this->logger->write_message("error", "Error updating earned_amount for Target Projection" . ' by user ' . $this->username);
                        redirect('projectionl');
                        return;
                } else {
                        $this->db->trans_complete();
                        $this->logger->write_message("success", "Updated Target Projection" . ' by user ' . $this->username);
                }	

		//Adding data to projection table for edited ledger
		$this->db->trans_start();
                $update_data1 = array(
              	  'code' => $code,
                  'earned_amount' => $new_amount
                );

                if ( ! $this->db->where('code', $code)->update('projection', $update_data1))
                {
                	$this->db->trans_rollback();
                        $this->messages->add('Error updating value to Projection for projection- ' . $code . ' by user ' . $this->username .  '.', 'error');
                        $this->logger->write_message("error", "Error updating value for Projection code " . $code  . ' by user ' . $this->username);
                        redirect('projectionl');
                        return;
                } else {
                        $this->db->trans_complete();
                        $this->logger->write_message("success","Successfully updated value for Projection code " . $code  . ' by user ' . $this->username);
                }

		//update earned_amount for parent projection
                $this->updateParentProjection($code, $changed_amount);

	}//method update_projection

	function updateParentProjection($child_code, $changed_amount)
        {
		//$username = $this->config->item('account_name');

                //calculate length of parent code
                $parent_code = substr($child_code, 0, -2);
                $earned_amount = 0.00;
                //$bd_balance = 0.00;
                $len = $this->countDigits($parent_code);
                if($len > 0)
                {
                        //update earned_amount and bd_balance of parent projection
                        $this->db->select('bd_balance, earned_amount')->from('projection')->where('code', $parent_code);
                        $projection_q = $this->db->get();
                        foreach ($projection_q->result() as $row)
                        {
                                 $earned_amount = $row->earned_amount;
                                //$bd_balance = $row->bd_balance;
                        }
                        $earned_amount = $earned_amount + $changed_amount;
                        //$bd_balance = $bd_balance + $projection_amount;
                        //Adding data to projection table
                        $this->db->trans_start();
                        $update_data = array(
                                'code' => $parent_code,
                                //'bd_balance' => $bd_balance,
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

                        //Update parent projection for given parent code
                        $this->updateParentProjection($parent_code, $changed_amount);
                }
                return;

        }//method updateParentProjection

	function countDigits($str)
        {
                $search = '1234567890';
                $count = strlen($str) - strlen(str_replace(str_split($search), '', $str));
                return $count;
        }
		
	function delete($id)
	{
		/* Check access */
		if ( ! check_access('delete ledger'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('account');
			return;
		}

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('account');
			return;
		}

		/* Checking for valid data */
		$id = $this->input->xss_clean($id);
		$id = (int)$id;
		if ($id < 1)
		{
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('account');
			return;
		}
		$this->db->from('entry_items')->where('ledger_id', $id);
		if ($this->db->get()->num_rows() > 0)
		{
			$this->messages->add('Cannot delete non-empty Ledger account.', 'error');
			redirect('account');
			return;
		}

		/* Get the ledger details */
		$this->db->from('ledgers')->where('id', $id);
		$ledger_q = $this->db->get();
		if ($ledger_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('account');
			return;
		} else {
			$ledger_data = $ledger_q->row();
		}

		/* Deleting ledger */
		$this->db->trans_start();
		if ( ! $this->db->delete('ledgers', array('id' => $id)))
		{
			$this->db->trans_rollback();
			$this->messages->add('Error deleting Ledger account - ' . $ledger_data->name . '.', 'error');
			$this->logger->write_message("error", "Error deleting Ledger account called " . $ledger_data->name . " [id:" . $id . "]");
			redirect('account');
			return;
		} else {
			/* Deleting reconciliation data if present */
			$this->Ledger_model->delete_reconciliation($id);
			$this->db->trans_complete();
			$this->messages->add('Deleted Ledger account - ' . $ledger_data->name . '.', 'success');
			$this->logger->write_message("success", "Deleted Ledger account called " . $ledger_data->name . " [id:" . $id . "]");
			redirect('account');
			return;
		}
		return;
	}

	function balance($ledger_id = 0)
	{
		if ($ledger_id > 0)
			echo $this->Ledger_model->get_ledger_balance($ledger_id);
		else
			echo "";
		return;
	}

	function set_group_id($id){

		/*$this->db->select('id');
		$this->db->from('groups')->where('name =', $name);
		$result = $this->db->get();
		$group = $result->row();
		$group_id = $group->id;*/
		
		$this->load->library('session');
                $this->session->set_userdata('ledger_group_id', $id);		
	}
}

/* End of file ledger.php */
/* Location: ./system/application/controllers/ledger.php */
