<?php

class Group extends Controller {

var $group_code = 0;
	function Group()
	{
		parent::Controller();
		$this->load->model('Group_model');
		$this->load->model('Ledger_model');
		return;
	}

	function index()
	{
		redirect('group/add');
		return;
	}

	function add()
	{
		$this->load->library('validation');
		$this->load->library('accountlist');
		$this->template->set('page_title', 'New Group');
		$asset = new Accountlist();
	       

		/* Check access */
		if ( ! check_access('create group'))
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
		
/*		$data['group_code'] = array(
			'name' => 'group_code',
			'id' => 'group-code',
			'maxlength' => '100',
			'size' => '40',
			'value' => '', 
//			'readonly' => 'readonly',
		);
*/		
		$data['group_name'] = array(
			'name' => 'group_name',
			'id' => 'group_name',
			'maxlength' => '100',
			'size' => '40',
			//'value' => 'group_name',
			'value' => '',
		);
		$data['group_parent'] = $this->Group_model->get_all_groups();
		$data['group_parent_active'] = 0;
		$data['group_description'] = array(
			'name' => 'group_description',
			'id' => 'group_description',
			'cols' => '50',
			'rows' => '3',
			'value' => '',
		);
		$data['affects_gross'] = 0;

		//schedule
	/*	$data['schedule'] = array(
                        'name' => 'schedule',
                        'id' => 'schedule',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
*/
//		$data['group_code'] = 0;

		/* Form validations */
//		$this->form_validation->set_rules('group_code', 'Group code', 'trim|required|min_length[2]|max_length[100]|unique[groups.code]');
		$this->form_validation->set_rules('group_name', 'Group name', 'trim|required|min_length[2]|max_length[100]|unique[groups.name]');
		$this->form_validation->set_rules('group_parent', 'Parent group', 'trim|required|is_natural_no_zero');
		$this->form_validation->set_rules('group_description', 'trim');
//		$this->form_validation->set_rules('schedule', 'Group schedule', 'trim');

		/* Re-populating form */
		if ($_POST)
		{
//			$data['group_code']['value'] = $this->input->post('group_code', TRUE);
			$data['group_name']['value'] = $this->input->post('group_name', TRUE);
			$data['group_parent_active'] = $this->input->post('group_parent', TRUE);
			$data['group_description']['value'] = $this->input->post('group_description', TRUE);
			$data['affects_gross'] = $this->input->post('affects_gross', TRUE);
//			$data['schedule']['value'] = $this->input->post('schedule', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'group/add', $data);
			return;
		}
		else
		{
//			$data_code = $this->input->post('group_code', TRUE);
//			$data_code = $this->group_code;
			$data_name = $this->input->post('group_name', TRUE);
			$data_group_description = $this->input->post('group_description', TRUE);
			$data_parent_id = $this->input->post('group_parent', TRUE);
//			$schedule = $this->input->post('schedule', TRUE);

			/* Check if parent group id present */
			$this->db->select('id')->from('groups')->where('id', $data_parent_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'group/add', $data);
				return;
			}

			/* Only if Income or Expense can affect gross profit loss calculation */
			$data_affects_gross = $this->input->post('affects_gross', TRUE);
			if ($data_parent_id == "3" || $data_parent_id == "4")
			{
				if ($data_affects_gross == "1")
					$data_affects_gross = 1;
				else
					$data_affects_gross = 0;
			} else {
				$data_affects_gross = 0;
			}

			/* This code has been moved to view */
			$num = $this->Group_model->get_numOfChild($data_parent_id);
			$g_code = $this->Group_model->get_group_code($data_parent_id);
			if($num == 0)
			{
				$data_code = $g_code . '01';
			} else{
				$data_code=$this->get_code($num, $g_code);
			}

			$i=0;
			do{
				if($i>0)
				{
					//$num=$num+$i;
					$num = $num + 1;
					$data_code=$this->get_code($num, $g_code);
				}			
				 $this->db->from('ledgers');
	                        $this->db->select('id')->where('code =',$data_code);
                                $ledger_q = $this->db->get();
				$i++;
			}while($ledger_q->num_rows()>0);
		
					
			$this->db->trans_start();
			$insert_data = array(
		      		'code' => $data_code,
				//'code' => '',
				'name' => $data_name,
				'parent_id' => $data_parent_id,
				'affects_gross' => $data_affects_gross,
				'group_description' => $data_group_description,
//				'schedule' => $schedule
			);
			if ( ! $this->db->insert('groups', $insert_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error addding Group account - ' . $data_name . '.', 'error');
				$this->template->load('template', 'group/add', $data);
				return;
			} else {
				$this->db->trans_complete();
				$this->messages->add('Added Group account - ' . $data_name . '.', 'success');
				redirect('account');
				return;
			}
		}
		return;
	}
	
/*	function get_numOfChild($parent_id)
        {
		$num = $this->Group_model->get_numOfChild($parent_id);
                $data = array("NUM"=>$num);
                //return Json($num, JsonRequestBehavior.AllowGet);
                //return $num;
                echo json_encode ($data) ;
        }

	function get_group_code($parent_id)
        {
		$g_code = $this->Group_model->get_group_code($parent_id);
                $data = array("GCODE"=>$g_code);
                echo json_encode ($data);
        }

	function get_Groupcode($data_code)
        {
                $this->db->from('groups');
                $this->db->select('id')->where('code =',$data_code);
                $group_q = $this->db->get();
                $num = $group_q->num_rows();
                $data = array("ROWS"=>$num);
                echo json_encode ($data);
        }

	function get_ledgerCode($data_code)
        {
		$this->db->from('ledgers');
                $this->db->select('id')->where('code =',$data_code);
                $ledger_q = $this->db->get();
                $num = $ledger_q->num_rows();
                $data = array("ROWS"=>$num);
                echo json_encode ($data);
        }

	function set_groupCode($group_code)
	{
		$this->group_code = $group_code;
		return;
	}*/

	function get_code($num, $code)
	{
			if($num <= 9)
                	{
                		$i = 0;
                       		do{
                        		$i++;
                                	$data_code = $code . '0' . $num+$i;
                                	$this->db->from('groups');
                                	$this->db->select('id')->where('code =',$data_code);
                                	$group_q = $this->db->get();
                         	}while($group_q->num_rows() > 0);
                	} else{
                        	 $i = 0;
                         	do{
                                	$i++;
                                	$data_code = $code . $num+$i;
                                	$this->db->from('groups');
                                	$this->db->select('id')->where('code =',$data_code);
                                	$group_q = $this->db->get();
                         	}while($group_q->num_rows() > 0);
        		}
		return $data_code;
	}

	function get_ledger_code($num, $code)
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

	function edit($id)
	{
		$this->template->set('page_title', 'Edit Group');

		/* Check access */
		if ( ! check_access('edit group'))
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
		if ($id < 1) {
			$this->messages->add('Invalid Group account.', 'error');
			redirect('account');
			return;
		}
		if ($id <= 4) {
			$this->messages->add('Cannot edit System Group account.', 'error');
			redirect('account');
			return;
		}

		/* Loading current group */
		$this->db->from('groups')->where('id', $id);
		$group_data_q = $this->db->get();
		if ($group_data_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Group account.', 'error');
			redirect('account');
			return;
		}
		$group_data = $group_data_q->row();
                 

//		$data['groupid'] = $id;
		/* Form fields */
/*		$data['group_code'] = array(
			'name' => 'group_code',
			'id' => 'group_code',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
			'readonly' => 'readonly',
		);
*/
		$data['group_name'] = array(
			'name' => 'group_name',
			'id' => 'group_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => $group_data->name,
			'readonly' => 'readonly',
		);

		$old_group_parent =  $group_data->parent_id;
		$data_code = $group_data->code;

		$data['group_parent'] = $this->Group_model->get_all_groups($id);
		$data['group_parent_active'] = $group_data->parent_id;
		$data['group_description'] = array(
			'name' => 'group_description',
			'id' => 'group_description',
			'cols' => '50',
			'rows' => '3',
			'value' => $group_data->group_description,
		);
		$data['group_id'] = $id;
		$data['affects_gross'] = $group_data->affects_gross;

/*		$data['schedule'] = array(
                        'name' => 'schedule',
                        'id' => 'schedule',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $group_data->schedule,
                );
*/
		/* Form validations */
//		$this->form_validation->set_rules('group_code', 'Group code', 'trim|required|min_length[2]|max_length[100]|uniquewithid[groups.code.' . $id . ']');
		$this->form_validation->set_rules('group_name', 'Group name', 'trim|required|min_length[2]|max_length[100]|uniquewithid[groups.name.' . $id . ']');
		$this->form_validation->set_rules('group_parent', 'Parent group', 'trim|required|is_natural_no_zero');
		$this->form_validation->set_rules('group_description', 'trim');
//		$this->form_validation->set_rules('schedule', 'Group schedule', 'trim');

		/* Re-populating form */
		if ($_POST)
		{
//			$data['group_code']['value'] = $this->input->post('group_code', TRUE);
			$data['group_name']['value'] = $this->input->post('group_name', TRUE);
			$data['group_parent_active'] = $this->input->post('group_parent', TRUE);
			$data['group_description']['value'] = $this->input->post('group_description', TRUE);
			$data['affects_gross'] = $this->input->post('affects_gross', TRUE);
//			$data['schedule']['value'] = $this->input->post('schedule', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'group/edit', $data);
			return;
		}
		else
		{
//			$data_code = $this->input->post('group_code', TRUE);
			$data_name = $this->input->post('group_name', TRUE);
			$data_parent_id = $this->input->post('group_parent', TRUE);
			$data_id = $id;
			$data_group_description = $this->input->post('group_description', TRUE);
//			$schedule = $this->input->post('schedule', TRUE);

			/* Check if parent group id present */
			$this->db->select('id')->from('groups')->where('id', $data_parent_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Parent group.', 'error');
				$this->template->load('template', 'group/edit', $data);
				return;
			}

			/* Check if parent group same as current group id */
			if ($data_parent_id == $id)
			{
				$this->messages->add('Invalid Parent group', 'error');
				$this->template->load('template', 'group/edit', $data);
				return;
			}

			/* Only if Income or Expense can affect gross profit loss calculation */
			$data_affects_gross = $this->input->post('affects_gross', TRUE);
			if ($data_parent_id == "3" || $data_parent_id == "4")
			{
				if ($data_affects_gross == "1")
					$data_affects_gross = 1;
				else
					$data_affects_gross = 0;
			} else {
				$data_affects_gross = 0;
			}

			if($old_group_parent != $data_parent_id){
                        	$num = $this->Group_model->get_numOfChild($data_parent_id);
                        	$g_code = $this->Group_model->get_group_code($data_parent_id);
                        	if($num == 0)
                        	{
                                	$data_code = $g_code . '01';
                        	} else{
                                	$data_code=$this->get_code($num, $g_code);
                        	}

                        	$i=0;
                        	do{
                                	if($i>0)
                                	{
                                        	//$num=$num+$i;
						$num = $num + 1;
                                        	$data_code=$this->get_code($num, $g_code);
                                	}
                                	$this->db->from('ledgers');
                                	$this->db->select('id')->where('code =',$data_code);
                                	$ledger_q = $this->db->get();
                                	$i++;
                        	}while($ledger_q->num_rows()>0);
			}

	
			$this->db->trans_start();
			$update_data = array(
				'code' => $data_code,
				'name' => $data_name,
				'parent_id' => $data_parent_id,
				'affects_gross' => $data_affects_gross,
				'group_description' => $data_group_description,
//				'schedule' => $schedule
			);
			if ( ! $this->db->where('id', $data_id)->update('groups', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Group account - ' . $data_code . '.', 'error');
				$this->messages->add('Error updating Group account - ' . $data_name . '.', 'error');
				$this->logger->write_message("error", "Error updating Group account called " . $data_name . $data_code . " [id:" . $data_id . "]");
				$this->template->load('template', 'group/edit', $data);
				return;
			} else {
				$this->db->trans_complete();
				$this->messages->add('Updated Group account - ' . $data_name . '.', 'success');
				$this->logger->write_message("success", "Updated Group account called " . $data_name . " [id:" . $data_id . "]");
				redirect('account');
				return;
			}
		}
		return;
	}

	function delete($id)
	{
		/* Check access */
		if ( ! check_access('delete group'))
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
		if ($id < 1) {
			$this->messages->add('Invalid Group account.', 'error');
			redirect('account');
			return;
		}
		if ($id <= 4) {
			$this->messages->add('Cannot delete System Group account.', 'error');
			redirect('account');
			return;
		}
		$this->db->from('groups')->where('parent_id', $id);
		if ($this->db->get()->num_rows() > 0)
		{
			$this->messages->add('Cannot delete non-empty Group account.', 'error');
			redirect('account');
			return;
		}
		$this->db->from('ledgers')->where('group_id', $id);
		if ($this->db->get()->num_rows() > 0)
		{
			$this->messages->add('Cannot delete non-empty Group account.', 'error');
			redirect('account');
			return;
		}

		/* Get the group details */
		$this->db->from('groups')->where('id', $id);
		$group_q = $this->db->get();
		if ($group_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Group account.', 'error');
			redirect('account');
			return;
		} else {
			$group_data = $group_q->row();
		}

		/* Deleting group */
		$this->db->trans_start();
		if ( ! $this->db->delete('groups', array('id' => $id)))
		{
			$this->db->trans_rollback();
			$this->messages->add('Error deleting Group account - ' . $group_data->name . '.', 'error');
			$this->logger->write_message("error", "Error deleting Group account called " . $group_data->name . " [id:" . $id . "]");
			redirect('account');
			return;
		} else {
			$this->db->trans_complete();
			$this->messages->add('Deleted Group account - ' . $group_data->name . '.', 'success');
			$this->logger->write_message("success", "Deleted Group account called " . $group_data->name . " [id:" . $id . "]");
			redirect('account');
			return;
		}
		return;
	}

			/** function for hide and unhide a group in chart of accounts **/
			/** created by sharad <sharad23nov@yahoo.com> date 20-05-2013 **/

	function enabledisable($id,$status)
	{
	/*	$id = $this->input->xss_clean($id);
                $id = (int)$id;
		$status = $this->input->xss_clean($status);
		$status = (int)$status;*/
		if ( ! check_access('administer'))
                {
                        $this->messages->add('You have no permission to hide and unhide a group.', 'error');
                        redirect('account');
                        return;
                }


		if($status == 0)
			$status = 1;
		else
			$status = 0;
		$this->db->trans_begin();
                $update_data = array('status' => $status);
		if ( ! $this->db->where('id', $id)->update('groups', $update_data))
                {
                	$this->db->trans_rollback();
                        $this->messages->add('Error in hiding/unhiding Group account - ' . $id . '.', 'error');
                        $this->template->load('template', 'account');
                        return;
                } else {
                        $this->db->trans_complete();
                        $this->messages->add('Operation successfull for Group Account - ' . $id . '.', 'success');
                        redirect('account');
                        return;
               	}
                


	}

	function set_group_id($id){
		$this->load->library('session');	
		$this->session->set_userdata('group_id', $id);
	}

	function add_fund_group(){

		/* Check for chart of account */
		if ($this->config->item('chart_account') != 'mhrd2015')
		{
			$this->messages->add('Permission Denied', 'error');
			redirect('account');
			return;
		}

		$chart_account = $this->config->item('chart_account');

		$this->load->library('validation');
		$this->load->model('Group_model');
		$this->template->set('page_title', 'New Fund Group');

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('account');
			return;
		}

		/* Form fields */
				
		$data['group_name'] = array(
			'name' => 'group_name',
			'id' => 'group_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['short_name'] = array(
			'name' => 'short_name',
			'id' => 'short_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['group_parent'] = array(
			'name' => 'group_parent',
			'id' => 'group_parent',
			'maxlength' => '100',
			'size' => '40',
			'value' => 'Designated-Earmarked/Endowment Funds',
			'readonly'=>'true',
		);
		//$data['group_parent_active'] = 0;
		$data['group_description'] = array(
			'name' => 'group_description',
			'id' => 'group_description',
			'cols' => '50',
			'rows' => '3',
			'value' => '',
		);

		/* Form validations */

		$this->form_validation->set_rules('group_name', 'Group Name', 'trim|required|min_length[2]|max_length[100]|unique[groups.name]');
		$this->form_validation->set_rules('short_name', 'Short Name', 'trim|required|min_length[2]|max_length[100]|unique[fund_group_table.short_name]');
		$this->form_validation->set_rules('group_description', 'trim');
		
		/* Re-populating form */
		if ($_POST)
		{
			$data['group_name']['value'] = $this->input->post('group_name', TRUE);
			$data['short_name']['value'] = $this->input->post('short_name', TRUE);
			$data['group_parent']['value'] = $this->input->post('group_parent', TRUE);
			$data['group_description']['value'] = $this->input->post('group_description', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'group/add_fund_group', $data);
			return;
		}
		else
		{
			$data_name = $this->input->post('group_name', TRUE);
			$data_short_name = $this->input->post('short_name', TRUE);
			$data_parent_name = $this->input->post('group_parent', TRUE);
			$data_group_description = $this->input->post('group_description', TRUE);

			$data_parent_id = $this->Group_model->get_id($data_parent_name);

			/* Check if parent group id present */

			$this->db->select('id')->from('groups')->where('id', $data_parent_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Parent group does not exists.', 'error');
				$this->template->load('template', 'group/add_fund_group', $data);
				return;
			}

			/* to create group */
			$num = $this->Group_model->get_numOfChild($data_parent_id);
			$g_code = $this->Group_model->get_group_code($data_parent_id);
			if($num == 0)
			{
				$data_code = $g_code . '01';
			} else{
				$data_code=$this->get_code($num, $g_code);
			}

			$i=0;
			do{
				if($i>0)
				{
					//$num=$num+$i;
					$num = $num + 1;
					$data_code=$this->get_code($num, $g_code);
				}			
				 $this->db->from('ledgers');
	                        $this->db->select('id')->where('code =',$data_code);
                                $ledger_q = $this->db->get();
				$i++;
			}while($ledger_q->num_rows()>0);
			
			$data_affects_gross = 0;
			$this->db->trans_start();
			$insert_data = array(
		      	'code' => $data_code,
				'name' => $data_name,
				'parent_id' => $data_parent_id,
				'affects_gross' => $data_affects_gross,
				'group_description' => $data_group_description,
			);

			if ( ! $this->db->insert('groups', $insert_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error addding Group account - ' . $data_name . '.', 'error');
				$this->template->load('template', 'group/add_fund_group', $data);
				return;
			} else {
				//$this->db->trans_complete();
				$new_parent_id = $this->db->insert_id();
				$this->db->trans_complete();
				$this->messages->add('Added Group account - ' . $data_name . '.entry_id='.$new_parent_id, 'success');
								
				/* add entry in fund_group_table */

				$this->db->trans_start();
				$insert_data = array(
			      	'code' => $data_code,
					'name' => $data_name,
					'short_name' => $data_short_name,
					'group_id' => $new_parent_id,
				);

				if ( ! $this->db->insert('fund_group_table', $insert_data))
				{
					$this->db->trans_rollback();
					$this->messages->add('Error addding Group info in fund group table .', 'error');
					$this->template->load('template', 'group/add_fund_group', $data);
					return;
				} else {
					$this->db->trans_complete();
					$this->messages->add('Added Group info in fund group table.' , 'success');
				}					

				/*$name_ar = explode(' ', $data_name);
				$st = "";
				foreach ($name_ar as $w) {
				  $st .= $w[0];
				}*/

				$name = array();
				$name[0] = "Opening Balance - ".$data_short_name;
				$name[1] = "Additions - ".$data_short_name;
				$name[2] = "Income From Investments - ".$data_short_name;
				$name[3] = "Interest On Saving A/C - ".$data_short_name;
				$name[4] = "Other Additions - ".$data_short_name;
				$name[5] = "Revenue Expenditure - ".$data_short_name;
				$name[6] = "Capital Expenditure - ".$data_short_name;

				$a = 0;
				do{

					$data_name = $name[$a];
					/* to create sub group of newly created group */
					$num = $this->Group_model->get_numOfChild($new_parent_id);
					$g_code = $this->Group_model->get_group_code($new_parent_id);
					if($num == 0)
					{
						$data_code = $g_code . '01';
					} else{
						$data_code=$this->get_code($num, $g_code);
					}

					$i=0;
					do{
						if($i>0)
						{
							$num = $num + 1;
							$data_code=$this->get_code($num, $g_code);
						}			
						$this->db->from('ledgers');
			            $this->db->select('id')->where('code =',$data_code);
		                $ledger_q = $this->db->get();
						$i++;
					}while($ledger_q->num_rows()>0);
									
					$data_affects_gross = 0;

					$this->db->trans_start();
					$insert_data = array(
				      	'code' => $data_code,
						'name' => $data_name,
						'parent_id' => $new_parent_id,
						'affects_gross' => $data_affects_gross,
						'group_description' => $data_group_description,
					);

					if ( ! $this->db->insert('groups', $insert_data))
					{
						$this->db->trans_rollback();
						$this->messages->add('Error addding Group account - ' . $data_name . ' .', 'error');
						$this->template->load('template', 'group/add_fund_group', $data);
						return;
					} else {
						$new_group_id = $this->db->insert_id();
						$this->db->trans_complete();
						$this->messages->add('Added Group account - ' . $data_name . '.', 'success');

						/* to add group info in fund_group_table */
						$this->db->trans_start();
						$insert_data = array(
					      	'code' => $data_code,
							'name' => $data_name,
							'short_name' => $data_short_name,
							'group_id' => $new_group_id,
						);

						if ( ! $this->db->insert('fund_group_table', $insert_data))
						{
							$this->db->trans_rollback();
							$this->messages->add('Error addding Group info in fund group table .', 'error');
							$this->template->load('template', 'group/add_fund_group', $data);
							return;
						} else {
							$this->db->trans_complete();
							$this->messages->add('Added Group info in fund group table.' , 'success');
						}				
					}
				$a++;
				}while($a<7);

				/* to create ledgers in earmarked fund investment of newly created group */

				$investment_id = $this->Group_model->get_id('Earmarked Fund Investments');

				$ledger_name[0] = $data_short_name." - Auto Sweep Investment";
				$ledger_name[1] = $data_short_name." - Investment";

				$a = 0;
				do{
					$ledg_name = $ledger_name[$a];

					$num = $this->Ledger_model->get_numOfChild($investment_id);
	                $l_code = $this->Group_model->get_group_code($investment_id);

	                if($num == 0)
			        {
	        		    $investment_code = $l_code . '01';
	                } else{
	                    $investment_code=$this->get_ledger_code($num, $l_code);
			        }
	        		$i=0;

                	do{
                    	if($i>0)
    	                {
                	        $num = $num + 1;
    	                	$investment_code=$this->get_ledger_code($num, $l_code);
            	        }
                    	$this->db->from('groups');
    	                $this->db->select('id')->where('code =',$investment_code);
            	        $group_q = $this->db->get();
	                	$i++;
        	        }while($group_q->num_rows()>0);
				
					$this->db->trans_start();
                    $insert_data = array(
                        'code' => $investment_code,
		                'name' => $ledg_name,
        		        'group_id' => $investment_id,
                		'op_balance' => '0.00',
                    	'op_balance_dc' => 'D',
                        'type' => '0',
    	                'reconciliation' => '0',
    	        	);

        	        if ( ! $this->db->insert('ledgers', $insert_data))
                	{
    	               	$this->db->trans_rollback();
                        $this->logger->write_message("error", "Error adding Interest on fund " . $data_name);
                	} else {
    	                $this->db->trans_complete();
        	            //$this->logger->write_message("success", "Added Interest on fund " . $data_name);
        	            $this->messages->add('Added Investment Ledger - ' . $ledg_name . '.', 'success');
            		}
            	$a++;
				}while($a<2);
				
				redirect('account');
				return;
			}
		}
		return;
	}

	function add_spons_project(){

		/* Check for chart of account */
		if ($this->config->item('chart_account') != 'mhrd2015')
		{
			$this->messages->add('Permission Denied', 'error');
			redirect('account');
			return;
		}

		$chart_account = $this->config->item('chart_account');

		$this->load->library('validation');
		$this->load->model('Group_model');
		$this->template->set('page_title', 'New Sponsored Project / Fellowship');

		/* Check for account lock */
		if ($this->config->item('account_locked') == 1)
		{
			$this->messages->add('Account is locked.', 'error');
			redirect('account');
			return;
		}

		/* Form fields */
				
		$data['group_name'] = array(
			'name' => 'group_name',
			'id' => 'group_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['short_name'] = array(
			'name' => 'short_name',
			'id' => 'short_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['group_parent'] = array(
			"select" => "Select",
            "project" => "Recipts Against Sponsored Projects",
            "fellowship"=> "UGC Sponsored Fellowship/Scholarships",
			
        );

		$data['group_parent_active'] = "select";
		$data['group_description'] = array(
			'name' => 'group_description',
			'id' => 'group_description',
			'cols' => '50',
			'rows' => '3',
			'value' => '',
		);

		/* Form validations */

		$this->form_validation->set_rules('group_name', 'Group Name', 'trim|required|min_length[2]|max_length[100]|unique[groups.name]');
		$this->form_validation->set_rules('short_name', 'Short Name', 'trim|required|min_length[2]|max_length[100]|unique[fund_group_table.short_name]');
		$this->form_validation->set_rules('group_description', 'trim');
		$this->form_validation->set_rules('group_parent', 'trim|required');
		
		/* Re-populating form */
		if ($_POST)
		{
			$data['group_name']['value'] = $this->input->post('group_name', TRUE);
			$data['short_name']['value'] = $this->input->post('short_name', TRUE);
			$data['group_parent_active'] = $this->input->post('group_parent', TRUE);
			$data['group_description']['value'] = $this->input->post('group_description', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'group/add_spons_project', $data);
			return;
		}
		else
		{
			$data_name = $this->input->post('group_name', TRUE);
			$data_short_name = $this->input->post('short_name', TRUE);
			$data_parent_name = $this->input->post('group_parent', TRUE);
			$data_group_description = $this->input->post('group_description', TRUE);

			if($data_parent_name == 'select'){
				$this->messages->add('Please Select a Parent Group.', 'error');
				$this->template->load('template', 'group/add_spons_project', $data);
				return;
			}

			//if($data_name != ""){
				$this->db->select('id')->from('groups')->where('name LIKE', $data_name . '%');
				$no_row = $this->db->get()->num_rows();
				if ( $no_row > 0)
				{
					$this->messages->add($data_name .' Project / Fellowship already exists.', 'error');
					$this->template->load('template', 'group/add_spons_project', $data);
					return;
				}
			//}

			if($data_parent_name == 'project')
				$data_parent_name ="Recipts Against Sponsored Projects";
			elseif($data_parent_name == 'fellowship')
				$data_parent_name ="UGC Sponsored Fellowship/Scholarships";

			$data_parent_id = $this->Group_model->get_id($data_parent_name);
			/* Check if parent group id present */

			$this->db->select('id')->from('groups')->where('id', $data_parent_id);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Parent group does not exists.'. $data_parent_id."name=".$data_parent_name, 'error');
				$this->template->load('template', 'group/add_spons_project', $data);
				return;
			}

			//$this->db->select('id')->from('groups')->where('parent_id', $data_parent_id);
			//$query_r = $this->db->get();
			$a = 0;
			$b = "";
			if($data_parent_name == "Recipts Against Sponsored Projects"){
				$b = 2;
			}
			elseif($data_parent_name == "UGC Sponsored Fellowship/Scholarships"){
				$b = 1;
			}
			//foreach ($query_r->result() as $row)
			$ledger_name = array();
			$name = array();
			$name[0] = $data_name." Receipts";
			$name[1] = $data_name." Expenses";
			$name[2] = $data_name." Fixed Assets";
			//{
			for($a = 0 ; $a <= $b ; $a++)
			{
				$num = $this->Group_model->get_numOfChild($data_parent_id);
				$g_code = $this->Group_model->get_group_code($data_parent_id);
				
				$data_group_name = $name[$a];
				//$a++;
				
				if($num == 0)
				{
					$data_code = $g_code . '01';
				} else{
					$data_code=$this->get_code($num, $g_code);
				}

				$i=0;
				do{
					if($i>0)
					{
						//$num=$num+$i;
						$num = $num + 1;
						$data_code=$this->get_code($num, $g_code);
					}			
					 $this->db->from('ledgers');
			                    $this->db->select('id')->where('code =',$data_code);
			                        $ledger_q = $this->db->get();
					$i++;
				}while($ledger_q->num_rows()>0);
				
				$data_affects_gross = 0;

				$this->db->trans_start();
				$insert_data = array(
			      	'code' => $data_code,
					'name' => $data_group_name,
					'parent_id' => $data_parent_id,
					'affects_gross' => $data_affects_gross,
					'group_description' => $data_group_description,
				);

				if( ! $this->db->insert('groups', $insert_data))
				{
					$this->db->trans_rollback();
					$this->messages->add('Error addding Group account - ' . $data_group_name . '.', 'error');
					$this->template->load('template', 'group/add_fund_group', $data);
					return;
				}else{
					//$this->db->trans_complete();
					$new_parent_id = $this->db->insert_id();
					$this->db->trans_complete();
					$this->messages->add('Added Group account - ' . $data_group_name . '.entry_id='.$new_parent_id, 'success');
				}
					/*code for adding ledgers under newly created groups*/

					
				if($data_parent_name == 'Recipts Against Sponsored Projects')
				{
					if (strpos($data_group_name,'Receipts') !== false)
					{
						$ledger_name[0] = $data_short_name." - Grants in Aids";
						$ledger_name[1] = $data_short_name." - Interest on Investments";
						$ledger_name[2] = $data_short_name." - Interest on Savings";
						$ledger_name[3] = $data_short_name." - Overhead Charges - Income";
						$ledger_name[4] = $data_short_name." - Seminars/Workshops-Income";

					}elseif(strpos($data_group_name,'Expenses') !== false){

						$ledger_name[0] = $data_short_name." - Consumables Exp";
						$ledger_name[1] = $data_short_name." - Interest of Deposit in EMF A/c";
						$ledger_name[2] = $data_short_name." - Man Power Exp";
						$ledger_name[3] = $data_short_name." - Other Expenditure";
						$ledger_name[4] = $data_short_name." - Overhead Expenses";
						$ledger_name[5] = $data_short_name." - Seminar - Workshops Exp";
						$ledger_name[6] = $data_short_name." - Temp Transfer to Maint. A/c";
						$ledger_name[7] = $data_short_name." - Travel Exp";
			
					}elseif(strpos($data_group_name,'Fixed Assets') !== false){

						$ledger_name[0] = $data_short_name." - Books";
						$ledger_name[1] = $data_short_name." - Computers& Peripherals";
						$ledger_name[2] = $data_short_name." - Furniture";
						$ledger_name[3] = $data_short_name." - General Equipments";
						$ledger_name[4] = $data_short_name." - Scientific & Laboratory Equipment";
					}
				}elseif($data_parent_name == 'UGC Sponsored Fellowship/Scholarships'){
					
					if (strpos($data_group_name,'Receipts') !== false)
					{
						$ledger_name[0] = $data_short_name." - Grants in Aids";
						$ledger_name[1] = $data_short_name." - Interest on Investments";
						$ledger_name[2] = $data_short_name." - Interest on Saving A/c";

					}elseif(strpos($data_group_name,'Expenses') !== false){

						$ledger_name[0] = $data_short_name." - Revenue Expenditure";
						$ledger_name[1] = $data_short_name." - Revenue Exp(SFIRE)";
					}
				}

				$c = 0;
				$counter = count($ledger_name);
				for($c = 0 ; $c <= $counter-1 ; $c++)
				{
					
					$ledg_name = $ledger_name[$c];

					$num = $this->Ledger_model->get_numOfChild($new_parent_id);
	                $l_code = $this->Group_model->get_group_code($new_parent_id);

	                if($num == 0)
			        {
	        		    $ledger_code = $l_code . '01';
	                } else{
	                    $ledger_code=$this->get_code($num, $l_code);
			        }
	        		$d=0;

                	do{
                    	if($d>0)
    	                {
                	        $num = $num + 1;
    	                	$ledger_code=$this->get_code($num, $l_code);
            	        }
                    	$this->db->from('groups');
    	                $this->db->select('id')->where('code =',$ledger_code);
            	        $group_q = $this->db->get();
	                	$d++;
        	        }while($group_q->num_rows()>0);
				
					$this->db->trans_start();
                    $insert_data1 = array(
                        'code' => $ledger_code,
		                'name' => $ledg_name,
        		        'group_id' => $new_parent_id,
                		'op_balance' => '0.00',
                    	'op_balance_dc' => 'C',
                        'type' => '0',
    	                'reconciliation' => '0',
    	        	);

        	        if ( ! $this->db->insert('ledgers', $insert_data1))
                	{
    	               	$this->db->trans_rollback();
                        $this->logger->write_message("error", "Error adding ledgers under group " . $data_parent_name);
                	} else {
    	                $this->db->trans_complete();
        	            //$this->logger->write_message("success", "Added Interest on fund " . $data_name);
        	            $this->messages->add('Added Ledger - ' . $ledg_name .  '.', 'success');
            		}
				}
				unset($ledger_name);
			}
			redirect('account');
			return;
		}
		return;
	}

}

/* End of file group.php */
/* Location: ./system/application/controllers/group.php */
