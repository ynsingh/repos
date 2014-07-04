<?php

class payrollsetup extends Controller {

        function payrollsetup()
        {
                parent::Controller();
		$this->load->model('Payrollsetup_model');
		return;

        }
	function index()
        {
                $this->load->library('session');
                $this->template->set('nav_links', array('payrollsetup/add' => ' Add Payroll Setup'));
		$this->template->set('page_title', 'Payroll Setup');
                $this->template->load('template', 'payrollsetup/index');
                return;
        }

	function add()
        {
		$this->load->library('validation');
                $this->template->set('page_title', 'Payroll Setup');
		$this->db->select('budgetname, code')->from('budgets')->where('group_id = ', '0');
		$main_budget = $this->db->get();
		$main_budget_code=0;
		$main_budget_name='';
		foreach($main_budget->result() as $row)
                {
                        $main_budget_code = $row->code;
                        $main_budget_name = $row->budgetname;
                }
		$data['main_budgetcode'] = array(
                        'name' => 'main_budget',
                        'id' => 'main_budget',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $main_budget_code,
			'readonly' => 'readonly',
                        
                );
		$data['salary_budgetcode'] = $this->Payrollsetup_model->get_selected_groups_withcode('Expenditure');
		$data['cash_bankcode'] =$this->Payrollsetup_model->get_all_ledgers_bankcash();

		/* Form validations */
		$this->form_validation->set_rules('main_budgetcode', 'main Code', 'trim|min_length[2]|max_length[100]');
                $this->form_validation->set_rules('salary_budgetcode', 'salarybudget Code', 'trim|required|min_length[2]|max_length[100]');
                $this->form_validation->set_rules('cash_bankcode', 'cashbank Code', 'trim|required|min_length[2]|max_length[100]');

		if ($_POST)
                {
			$data['main_budgetcode'] ['value'] = $this->input->post('main_budgetcode', TRUE);
                        $data['salary_budgetcode']['value'] = $this->input->post('salary_budgetcode', TRUE);
                        $data['cash_bankcode'] ['value']= $this->input->post('cash_bankcode', TRUE);
                }
		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payrollsetup/add', $data);
                        return;
                }
		else
                {
                        $data_Mcode = $this->input->post('main_budgetcode', TRUE);
			$this->db->select('budgetname')->from('payrollsetup');
               		$main_budget = $this->db->get();
			$entrysize=$main_budget->num_rows();
		//	$this->messages->add('code==up===123='.$entrysize);
			$data_type='0';
			$this->db->trans_start();
                        $insert_data = array(
                                'code' => $main_budget_code,
                                'budgetname' => $main_budget_name,
				'type' =>$data_type,

                        );
			if($entrysize == 0)
			{
				if ( ! $this->db->insert('payrollsetup', $insert_data))
                        	{
                                	$this->db->trans_rollback();
                                	$this->messages->add('Error in saving payroll setup - ' . $main_budget_name . '.', 'error');
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $main_budget_name);
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $main_budget_code);
                                	$this->template->load('template', 'payrollsetup/add', $data);
                                	return;
                        	}
				else {
                                $this->db->trans_complete();
                                $this->messages->add('Save payroll setup for salary Processing  ' . $main_budget_name . '.', 'success');
                                $this->logger->write_message("success", "Save payroll setup " . $main_budget_name);
                        	}
			}//if
			$this->db->from('payrollsetup')->where('type',2);
			$main_budget1 = $this->db->get();
			$entrysize1=$main_budget1->num_rows();
			//$this->messages->add('code==up===salary='.$entrysize1);
                        $data_salcode = $this->input->post('salary_budgetcode', TRUE);
                        $my_values = explode('#',$data_salcode);
			$data_code=$my_values[0];
			$data_name=$my_values[1];
			$data_type='2';
			$this->db->trans_start();
                        $insert_data1 = array(
                                'code' => $data_code,
                                'budgetname' => $data_name,
				'type' =>$data_type,
			
			);
			if($entrysize1!=0)
			{
				if ( ! $this->db->where('type', $data_type)->update('payrollsetup', $insert_data1))
                        	{
                                	$this->db->trans_rollback();
                               	 	$this->messages->add('Error in saving payroll setup - ' . $data_name . '.', 'error');
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $data_name);
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $data_code);
                                	$this->template->load('template', 'payrollsetup/add', $data);
                                	return;
                        	}else {
                                        $this->db->trans_complete();
                                        $this->messages->add('Save payroll setup for salary Processing  ' . $data_name . '.', 'success');
                                        $this->logger->write_message("success", "Save payroll setup " . $data_name);
                                        //redirect('payrollsetup/index');
                                        //return;
                                }
			}
			else{
                        	if ( ! $this->db->insert('payrollsetup', $insert_data1))
                        	{
                                	$this->db->trans_rollback();
                                	$this->messages->add('Error in saving payroll setup - ' . $data_name . '.', 'error');
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $data_name);
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $data_code);
                                	$this->template->load('template', 'payrollsetup/add', $data);
                                	return;
                        	}
			 	else {
                                	$this->db->trans_complete();
                                	$this->messages->add('Save payroll setup for salary Processing  ' . $data_name . '.', 'success');
                                	$this->logger->write_message("success", "Save payroll setup " . $data_name);
                        	}
			}//else
			$this->db->from('payrollsetup')->where('type',1);
                        $main_budget2 = $this->db->get();
                        $entrysize2=$main_budget2->num_rows();
                        //$this->messages->add('code==up===cash='.$entrysize2);
			$data_BCcode = $this->input->post('cash_bankcode', TRUE);
                        $my_values1 = explode('#',$data_BCcode);
                        $data_code=$my_values1[0];
                        $data_name=$my_values1[1];
			$data_type='1';

			$this->db->trans_start();
                        $insert_data2 = array(
                                'code' => $data_code,
                                'budgetname' => $data_name,
				'type' =>$data_type,
                        
                        );
			if($entrysize2!=0)
                        {
                                if ( ! $this->db->where('type', $data_type)->update('payrollsetup', $insert_data2))
                                {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error in saving payroll setup - ' . $data_name . '.', 'error');
                                        $this->logger->write_message("error", "Error in saving payroll setup " . $data_name);
                                        $this->logger->write_message("error", "Error in saving payroll setup " . $data_code);
                                        $this->template->load('template', 'payrollsetup/add', $data);
                                        return;
                                }
				else {
                                        $this->db->trans_complete();
                                        $this->messages->add('Save payroll setup for salary Processing  ' . $data_name . '.', 'success');
                                        $this->logger->write_message("success", "Save payroll setup " . $data_name);
                                        redirect('payrollsetup/index');
                                        return;
                                }
			}
			else{

                        	if ( ! $this->db->insert('payrollsetup', $insert_data2))
                        	{
                                	$this->db->trans_rollback();
                                	$this->messages->add('Error in saving payroll setup - ' . $data_name . '.', 'error');
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $data_name);
                                	$this->logger->write_message("error", "Error in saving payroll setup " . $data_code);
                                	$this->template->load('template', 'payrollsetup/add', $data);
                                	return;
                        	} else {
                                	$this->db->trans_complete();
                                	$this->messages->add('Save payroll setup for salary Processing  ' . $data_name . '.', 'success');
                                	$this->logger->write_message("success", "Save payroll setup " . $data_name);
                                	redirect('payrollsetup/index');
                                	return;
                        	}
			}//else
		}

	}


}
?>
