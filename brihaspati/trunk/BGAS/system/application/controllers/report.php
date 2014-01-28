<?php

class Report extends Controller {
	var $acc_array;
	var $account_counter;
	var $logndb;
	var $ledger_data;

	function Report()
	{
		parent::Controller();
		$this->load->model('Ledger_model');
		$this->load->model('Budget_model');
		/* Check access */
		if ( ! check_access('view reports'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}
	
	function index()
	{
		$this->template->set('page_title', 'Reports');
		$this->template->load('template', 'report/index');
		return;
	}
	
	function balancesheet($period = NULL )
	{
		$this->load->library('session');
		$this->template->set('page_title', 'Balance Sheet');
		$this->template->set('nav_links', array('report/download/balancesheet' => 'Download CSV', 'report/printpreview/balancesheet' => 'Print Preview'));
		$data['left_width'] = "550";
		$data['right_width'] = "550";
		$page_count = " ";

		/* Form fields */ 
		
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
			$default_end .= date('Y') + 1;

		} else {
			$default_start .= date('Y') - 1;
			$default_end .= date('Y');
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
                $data['print_preview'] =FALSE;
		
		$data_date1 = $default_start;
                $data_date2 = $default_end;

                $date=explode("/",$data_date1);
                $date1=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $date2=$date[2]."-".$date[1]."-".$date[0];

                $newdata = array(
                      'date1'  => $date1,
                      'date2'  => $date2
                     );
                $this->session->set_userdata($newdata);

		/* Repopulating form */

		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);		
		} 
		/* Form validations */

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report/balancesheet', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$_POST['entry_date1']);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$_POST['entry_date2']);
			$date2=$date[2]."-".$date[1]."-".$date[0];

			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2
	                );
			$this->session->set_userdata($newdata);
		}

		$this->template->load('template', 'report/balancesheet', $data);
		return;
	}
	 function depreciation($period = NULL)
        {
                $this->load->library('session');
                $this->template->set('nav_links', array('report/depreciation' => 'Depreciation As Today', 'report/update' => 'Update Depreciiation Rate', 'report/printpreview/depreciation' => 'Print Preview'));
                $this->template->set('page_title', 'Depreciation Of Assets');
                $data['left_width'] = "450";
                $data['right_width'] = "450";
                $data['print_preview'] =FALSE;
                $data['budget_over'] = TRUE;
		
                if($_POST)
                {
                	$data['budget_over'] = $this->input->post('budget_over', TRUE);
                }

                $newdata = array(
                        'budget_over'=>$data,
                        );
                $this->session->set_userdata($newdata);
                $this->template->load('template', 'report/depreciation', $data);
                return;
        }

        function addDep()
        {
                $this->template->set('nav_links', array( 'report/depreciation' => 'Depreciation As Today', 'report/update' => 'Update Depreciiation Rate', 'report/printpreview/depreciation' => 'Print Preview'));
                $this->template->set('page_title', 'Add Depreciation Rate');
		$account_code = $this->Budget_model->get_account_code('Fixed Assets');
		$options = array();
                $this->db->select('a.id, a.name, a.parent_id, a.code, b.group_id, b.name, b.code');
                $this->db->from('groups a,ledgers b')->where('a.id = b.group_id')->where('b.code LIKE', $account_code.'%');
                $query = $this->db->get();

                foreach($query->result() as $row)
                {
                	$new_id = "$row->code"."#"."$row->name";
                	$options[$new_id] = $row->name;
                }
                $data['group_name']=$options;
		
                $data['group_name_active'] = 0;
                $data['dep_amount'] = array(
                        'name' => 'dep_amount',
                        'id' => 'dep_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
                /* Form validations */
                $this->form_validation->set_rules('group_name', 'Assets name', 'trim|required');
                $this->form_validation->set_rules('dep_amount', 'Depreciation Amount', 'trim|required');

                /* Re-populating form */
                if ($_POST)
                {
                	$data['group_name_active'] = $this->input->post('group_name', TRUE);
                	$data['dep_amount']['value'] = $this->input->post('dep_amount', TRUE);
                }
                /* Validating form */
                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'report/addDep', $data);
                        return;
                }
                else
		{
                        $data_group_name = $this->input->post('group_name', TRUE);
                        $my_values = explode('#',$data_group_name);
                        $data_dep_amount = $this->input->post('dep_amount', TRUE);

                        /* insert data into the dep_asstes table */
                        $this->db->select('code, id,')->from('ledgers')->where('name ', $my_values[1] );
                        $main_data = $this->db->get();
                        $value=$main_data->num_rows();
                                foreach($main_data->result() as $row)
                                {
                                        $main_data_id = $row->id;
                                        $main_data_code = $row->code;
                                }
                           	$this->db->trans_start();
                                $insert_data = array(
                                'asset_id'=>$main_data_id,
                                'code'=> $main_data_code,
                                'name' => $my_values[1],
                                'percentage' => $data_dep_amount,
                                          );
                                 if ( ! $this->db->insert('dep_assets', $insert_data))
                                 {
                                 	$this->db->trans_rollback();
                                        $this->messages->add('Depreciation Percentage value is already added. ' . $my_values[1] . '.', 'error');
                                        $this->template->load('template', 'report/addDep', $data);
                                        return;
                                 } else {
                                        $this->db->trans_complete();
                                        $this->messages->add('Added Values - ' . $my_values[1] . '.', 'success');
                                        redirect('report/addDep');
                                }            
		}
                $this->template->load('template', 'report/addDep', $data);
                return;
        }

	function update()
        {
        	$this->template->set('page_title', 'Update Depreciation Rate');
	       	$this->template->set('nav_links', array( 'report/depreciation' => 'Depreciation As Today', 'report/update' => 'Update Depreciiation Rate', 'report/printpreview/depreciation' => 'Print Preview'));

		$account_code = $this->Budget_model->get_account_code('Fixed Assets');
                $this->db->select('name, code');
                $this->db->from('ledgers')->where('code LIKE', $account_code.'%');
                $gross_expense_list_q = $this->db->get();       
		$counter=0;
	  	foreach($gross_expense_list_q->result() as $row1){
                        $name=$row1->name;
                        $code=$row1->code;
			
                        /*load database pico*/
                        $logndb = $this->load->database('pico', TRUE);
                        $this->logndb =& $logndb;
                        $this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
                        $this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('a.ERPMIM_Item_Brief_Desc ',$name );
                        $this->logndb->group_by("ERPMIM_Item_Brief_Desc");
                        $user_data = $this->logndb->get();
	     
			if($user_data->num_rows() > 0){
				$key = 'value_'.$counter;
	       			$this->depreciation[$key] = $user_data;
        	        	foreach ($user_data->result() as $row)
	        	        {
        	        		$name = 'dep_value'. "_" . $row->ERPMIM_ID;
                			$this->depreciation[$name] = array(
		                                'name' =>$row->ERPMIM_Item_Brief_Desc,
                	        	        'id' => $row->ERPMIM_ID,
						'code' => $row1->code,
                        	        	'maxlength' => '50',
		                                'size' => '40',
                		                'value' => $row->ERPMIM_Depreciation_Percentage,
                                	);
        			}
				$counter++;
			}
		}
		$this->depreciation['counter'] = --$counter;
		$this->ledger_data = ++$counter;

        	/* Repopulating form*/
      	        if ($_POST)
       		{
			$counter = 0;
			$check = $this->ledger_data;
			if($check > 0){
				$key = 'value_'.$counter;
                		foreach ($this->depreciation[$key]->result() as $row)
               		 	{
                        		$value = 'dep_value'. "_" .$row->ERPMIM_ID;
				        $this->depreciation[$name]['value'] = $this->input->post($name, TRUE);
	                	}
				$counter++;
				$check--;
			}				
				
        	}
	
       		/*Form validations*/
		
		$counter = 0;	
		for($check = $this->ledger_data; $check > 0; $check--){
			$key = 'value_'.$counter;
       			foreach ($this->depreciation[$key]->result() as $row)
        		{
        			$name = 'dep_value'. "_" .$row->ERPMIM_ID;
			       	$this->form_validation->set_rules($name, 'Per_value1', 'trim|required');
        		}
			$counter++;
                        
		}

		/* vaildating form*/
        	if ($this->form_validation->run() == FALSE)
        	{
	                $this->messages->add(validation_errors(), 'error');
        	        $this->template->load('template', 'report/update', $this->depreciation);
                	return;
        	}
        	else
        	{
			$counter = 0;
			for($check = $this->ledger_data; $check > 0; $check--){
				$key = 'value_'.$counter;
                		foreach ($this->depreciation[$key]->result() as $row)
                		{
               				$name = 'dep_value'. "_" .$row->ERPMIM_ID;
	                		$data_Per_value1 = $this->input->post($name, TRUE);
				  	$this->logndb->trans_start();
                	         	$update_data = array(
                               			'ERPMIM_Depreciation_Percentage' =>  $data_Per_value1,
                                       		);
                        	         if ( ! $this->logndb->where('ERPMIM_ID', $row->ERPMIM_ID)->update('erpm_item_master', $update_data))
                                	 {
                                        	$this->logndb->trans_rollback();
	                                        $this->messages->add('Error updating value ' . $data_Per_value1 . '.', 'error');
        	                                $this->template->load('template', 'report/update', $this->depreciation);
                	                        return;
                        	       	} else {
					        $this->logndb->trans_complete();
                                	}	

				}
				$counter++;
			}
		}
		$value=$this->db->trans_complete();
                if($value==1)
                {
                    $this->messages->add('Update values.', 'success');
                }

		$this->logndb->close();
		redirect('report/update');
             	return;
        }
		
	function duplicate_entry($ERPMIM_Item_Brief_Desc)
        {
        	 $this->template->set('page_title', 'Purchase Detail');
		 /*load database pico*/
                 $logndb = $this->load->database('pico', TRUE);
                 $this->logndb =& $logndb;
                 $this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
                 $this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('a.ERPMIM_Item_Brief_Desc ',$ERPMIM_Item_Brief_Desc );
                 $user_data = $this->logndb->get();
		 $data['detail'] = $user_data ;
		 $this->template->load('template', 'report/duplicate_entry', $data);
                 return ;
	
        }


	function new_balancesheet($period = NULL)
	{
		$this->template->set('page_title', 'Balance Sheet MHRD Format');
		//$this->template->set('nav_links', array('report/download/new_balancesheet' => 'Download CSV', 'report/printpreview/new_balancesheet' => 'Print Preview'));
		$this->template->set('nav_links', array('report/printpreview/new_balancesheet' => 'Print Preview'));
		$data['left_width'] = "450";
		$data['right_width'] = "450";
		$this->template->load('template', 'report/new_balancesheet', $data);
		return;
	}

	function schedule($code)
	{
		$data = array();
		$id = '';
		$schedule = '';
		$name = '';
		$data['code'] = $code;
		$this->load->model('Group_model');
		$group_details = $this->Group_model->get_schedule($code);
		foreach ($group_details as $id => $group)
                {
			$id  = $group['id'];
                        $schedule = $group['schedule'];
			$name = $group['name'];
		}
		
		if($name != '' && $id != ''){
			$this->template->set('page_title', 'Schedule - ' . $schedule . ' ' . $name);
			$this->template->set('nav_links', array('report/download/schedule' => 'Download CSV', 'report/printpreview/schedule' => 'Print Preview'));
			$data['id'] = $id;
		}
		else{
			$this->template->set('page_title', 'Schedule - Notes on Accounts');
                        $this->template->set('nav_links', array('report/download/schedule' => 'Download CSV', 'report/printpreview/schedule' => 'Print Preview'));
			//$data['id'] = $id;
		}

		if($schedule == 1 || $schedule == 2){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 3 || $schedule == 4){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 5){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 6){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 7){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 8){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 9){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		elseif($schedule == 10){
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}
		else{
			//Schedule for Notes
			$this->template->load('template', 'report/schedule_template', $code);
                        return;
		}

		return;
	}

	function profitandloss($period = NULL)
	{
		$this->load->library('session');
		$this->template->set('page_title', 'Income And Expenditure Statement');
		$this->template->set('nav_links', array('report/download/profitandloss' => 'Download CSV', 'report/printpreview/profitandloss' => 'Print Preview'));
		$data['left_width'] = "550";
		$data['right_width'] = "550";

		/* Form fields */ 
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
			$default_end .= date('Y') + 1;

		} else {
			$default_start .= date('Y') - 1;
			$default_end .= date('Y');
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
                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end;

                $date=explode("/",$data_date1);
                $date1=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $date2=$date[2]."-".$date[1]."-".$date[0];

                $newdata = array(
                      'date1'  => $date1,
                      'date2'  => $date2
                     );
                $this->session->set_userdata($newdata);
		/* Form validations */

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Repopulating form */
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);		
		} 

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report/profitandloss', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2
	                );
			$this->session->set_userdata($newdata);
		}
		$this->template->load('template', 'report/profitandloss', $data);
		return;
	}

	function paymentreceipt($period = NULL)
	{
		$this->load->library('session');
		$this->template->set('page_title', 'Payment & Receipt');
		$this->template->set('nav_links', array('report/download/paymentreceipt' => 'Download CSV', 'report/printpreview/paymentreceipt' => 'Print Preview'));
		$data['left_width'] = "550";
		$data['right_width'] = "550";

		/* Form fields */ 
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
			$default_end .= date('Y') + 1;

		} else {
			$default_start .= date('Y') - 1;
			$default_end .= date('Y');
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
                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end;

                $date=explode("/",$data_date1);
                $date1=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $date2=$date[2]."-".$date[1]."-".$date[0];

                $newdata = array(
                      'date1'  => $date1,
                      'date2'  => $date2
                     );
                $this->session->set_userdata($newdata);

		/* Form validations */ 

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Repopulating form */ 
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);		
		} 

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report/paymentreceipt', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2
	                );
			$this->session->set_userdata($newdata);
		}
		$this->template->load('template', 'report/paymentreceipt', $data );
		return;
	}

	function trialbalance($period = NULL)
	{
		$this->load->library('session');
		$this->template->set('page_title', 'Trial Balance');
		$this->template->set('nav_links', array('report/download/trialbalance' => 'Download CSV', 'report/printpreview/trialbalance' => 'Print Preview'));

		/* Form fields */ 
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
			$default_end .= date('Y') + 1;

		} else {
			$default_start .= date('Y') - 1;
			$default_end .= date('Y');
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
		$data['date1'] = '';
		$data['date2'] = '';
		$data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end;

                $date=explode("/",$data_date1);
                $date1=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $date2=$date[2]."-".$date[1]."-".$date[0];

                $newdata = array(
                      'date1'  => $date1,
                      'date2'  => $date2
                     );
                $this->session->set_userdata($newdata);

		/* Form validations */ 

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Repopulating form */ 
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);		
		} 

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report/trialbalance', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
				
			$data['date1'] = $date1;
			$data['date2'] = $date2;
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2,
	                );
			$this->session->set_userdata($newdata);
		}
		$this->template->load('template', 'report/trialbalance', $data); 
		return;
	}

	function ledgerst($ledger_id = 0)
	{
		$this->load->library('session');
		$this->load->helper('text');
		/* Pagination setup */
		$this->load->library('pagination');

		$this->template->set('page_title', 'Ledger Statement');
		$this->template->set('nav_links', array('report/download/ledgerst/' . $ledger_id  => 'Download CSV', 'report/printpreview/ledgerst/' . $ledger_id => 'Print Preview'));

		if ($_POST)
		{
			$ledger_id = $this->input->post('ledger_id', TRUE);
		}
		$data['print_preview'] = FALSE;
		$data['ledger_id'] = $ledger_id;

		/* Checking for valid ledger id */
		if ($data['ledger_id'] > 0)
		{
			$this->db->from('ledgers')->where('id', $data['ledger_id'])->limit(1);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Ledger account.', 'error');
				redirect('report/ledgerst');
				return;
			}
		} else if ($data['ledger_id'] < 0) {
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('report/ledgerst');
			return;
		}
		/* Form fields */ 
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
			$default_end .= date('Y') + 1;

		} else {
			$default_start .= date('Y') - 1;
			$default_end .= date('Y');
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

		/* Repopulating form */
 
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);
		} 
		
		/* Form validations */ 

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report/ledgerst', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2,
	                );
			$this->session->set_userdata($newdata);
			redirect('report/ledgerst/' . $ledger_id);
		}
		$this->template->load('template', 'report/ledgerst', $data);
		return;
	}

	function reconciliation($reconciliation_type = '', $ledger_id = 0)
	{
		$this->load->helper('text');

		/* Pagination setup */
		$this->load->library('pagination');

		$this->template->set('page_title', 'Reconciliation');
		/* Check if path is 'all' or 'pending' */
		
		$data['show_all'] = FALSE;
		$data['print_preview'] = FALSE;
		$data['ledger_id'] = $ledger_id;
		if ($reconciliation_type == 'all')
		{
			$data['reconciliation_type'] = 'all';
			$data['show_all'] = TRUE;
			if ($ledger_id > 0)
				$this->template->set('nav_links', array('report/download/reconciliation/' . $ledger_id . '/all'  => 'Download CSV', 'report/printpreview/reconciliation/' . $ledger_id . '/all' => 'Print Preview'));
		} else if ($reconciliation_type == 'pending') {
			$data['reconciliation_type'] = 'pending';
			$data['show_all'] = FALSE;
			if ($ledger_id > 0)
				$this->template->set('nav_links', array('report/download/reconciliation/' . $ledger_id . '/pending'  => 'Download CSV', 'report/printpreview/reconciliation/' . $ledger_id . '/pending'  => 'Print Preview'));
		} else {
			$this->messages->add('Invalid path.', 'error');
			redirect('report/reconciliation/pending');
			return;
		}

		/* Checking for valid ledger id and reconciliation status */
		if ($data['ledger_id'] > 0)
		{
			$this->db->from('ledgers')->where('id', $data['ledger_id'])->where('reconciliation', 1)->limit(1);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Ledger account or Reconciliation is not enabled for the Ledger account.', 'error');
				redirect('report/reconciliation/' . $reconciliation_type);
				return;
			}
		} else if ($data['ledger_id'] < 0) {
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('report/reconciliation/' . $reconciliation_type);
			return;
		}

		if ($_POST)
		{
			/* Check if Ledger account is changed or reconciliation is updated */
			if ($_POST['submit'] == 'Submit')
			{
				$ledger_id = $this->input->post('ledger_id', TRUE);
				if ($this->input->post('show_all', TRUE))
				{
					redirect('report/reconciliation/all/' . $ledger_id);
					return;
				} else {
					redirect('report/reconciliation/pending/' . $ledger_id);
					return;
				}
			} else if ($_POST['submit'] == 'Update') {

				$data_reconciliation_date = $this->input->post('reconciliation_date', TRUE);

				/* Form validations */
				foreach ($data_reconciliation_date as $id => $row)
				{
					/* If reconciliation date is present then check for valid date else only trim */
					if ($row)
						$this->form_validation->set_rules('reconciliation_date[' . $id . ']', 'Reconciliation date', 'trim|required|is_date|is_date_within_range_reconcil');
					else
						$this->form_validation->set_rules('reconciliation_date[' . $id . ']', 'Reconciliation date', 'trim');
				}

				if ($this->form_validation->run() == FALSE)
				{
					$this->messages->add(validation_errors(), 'error');
					$this->template->load('template', 'report/reconciliation', $data);
					return;
				} else {
					/* Updating reconciliation date */
					foreach ($data_reconciliation_date as $id => $row)
					{
						$this->db->trans_start();
						if ($row)
						{
							$update_data = array(
								'reconciliation_date' => date_php_to_mysql($row),
							);
						} else {
							$update_data = array(
								'reconciliation_date' => NULL,
							);
						}
						if ( ! $this->db->where('id', $id)->update('entry_items', $update_data))
						{
							$this->db->trans_rollback();
							$this->messages->add('Error updating reconciliation.', 'error');
							$this->logger->write_message("error", "Error updating reconciliation for entry item [id:" . $id . "]");
						} else {
							$this->db->trans_complete();
						}
					}
					$this->messages->add('Updated reconciliation.', 'success');
					$this->logger->write_message("success", 'Updated reconciliation.');
				}
			}
		}
		$this->template->load('template', 'report/reconciliation', $data);
		return;
	}

	function download($statement, $id = NULL)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		/********************** TRIAL BALANCE *************************/
		if ($statement == "trialbalance")
		{
			$this->load->model('Ledger_model');
			$all_ledgers = $this->Ledger_model->get_all_ledgers1($date1, $date2);
			$counter = 0;
			$trialbalance = array();
			$temp_dr_total = 0;
			$temp_cr_total = 0;

			$trialbalance[$counter] = array ("TRIAL BALANCE", "", "", "", "", "", "", "", "");
			$counter++;
			$trialbalance[$counter] = array ("FY " . date_mysql_to_php($this->config->item('account_fy_start')) . " - " . date_mysql_to_php($this->config->item('account_fy_end')), "", "", "", "", "", "", "", "");
			$counter++;

			$trialbalance[$counter][0]= "Ledger";
			$trialbalance[$counter][1]= "";
			$trialbalance[$counter][2]= "Opening";
			$trialbalance[$counter][3]= "";
			$trialbalance[$counter][4]= "Closing";
			$trialbalance[$counter][5]= "";
			$trialbalance[$counter][6]= "Dr Total";
			$trialbalance[$counter][7]= "";
			$trialbalance[$counter][8]= "Cr Total";
			$counter++;

			foreach ($all_ledgers as $ledger_id => $ledger_name)
			{
				if ($ledger_id == 0) continue;

				$trialbalance[$counter][0] = $ledger_name;

				list ($opbal_amount, $opbal_type) = $this->Ledger_model->get_op_balance($ledger_id);
				if (float_ops($opbal_amount, 0, '=='))
				{
					$trialbalance[$counter][1] = "";
					$trialbalance[$counter][2] = 0;
				} else {
					$trialbalance[$counter][1] = convert_dc($opbal_type);
					$trialbalance[$counter][2] = $opbal_amount;
				}

				$clbal_amount = $this->Ledger_model->get_ledger_balance($ledger_id);

				if (float_ops($clbal_amount, 0, '=='))
				{
					$trialbalance[$counter][3] = "";
					$trialbalance[$counter][4] = 0;
				} else if ($clbal_amount < 0) {
					$trialbalance[$counter][3] = "Cr";
					$trialbalance[$counter][4] = convert_cur(-$clbal_amount);
				} else {
					$trialbalance[$counter][3] = "Dr";
					$trialbalance[$counter][4] = convert_cur($clbal_amount);
				}

				$dr_total = $this->Ledger_model->get_dr_total($ledger_id);
				if ($dr_total)
				{
					$trialbalance[$counter][5] = "Dr";
					$trialbalance[$counter][6] = convert_cur($dr_total);
					$temp_dr_total = float_ops($temp_dr_total, $dr_total, '+');
				} else {
					$trialbalance[$counter][5] = "";
					$trialbalance[$counter][6] = 0;
				}

				$cr_total = $this->Ledger_model->get_cr_total($ledger_id);
				if ($cr_total)
				{
					$trialbalance[$counter][7] = "Cr";
					$trialbalance[$counter][8] = convert_cur($cr_total);
					$temp_cr_total = float_ops($temp_cr_total, $cr_total, '+');
				} else {
					$trialbalance[$counter][7] = "";
					$trialbalance[$counter][8] = 0;
				}
				$counter++;
			}

			$trialbalance[$counter][0]= "";
			$trialbalance[$counter][1]= "";
			$trialbalance[$counter][2]= "";
			$trialbalance[$counter][3]= "";
			$trialbalance[$counter][4]= "";
			$trialbalance[$counter][5]= "";
			$trialbalance[$counter][6]= "";
			$trialbalance[$counter][7]= "";
			$trialbalance[$counter][8]= "";
			$counter++;

			$trialbalance[$counter][0]= "Total";
			$trialbalance[$counter][1]= "";
			$trialbalance[$counter][2]= "";
			$trialbalance[$counter][3]= "";
			$trialbalance[$counter][4]= "";
			$trialbalance[$counter][5]= "Dr";
			$trialbalance[$counter][6]= convert_cur($temp_dr_total);
			$trialbalance[$counter][7]= "Cr";
			$trialbalance[$counter][8]= convert_cur($temp_cr_total);

			$this->load->helper('csv');
			echo array_to_csv($trialbalance, "trialbalance.csv");
			return;
		}

		/********************** LEDGER STATEMENT **********************/
		if ($statement == "ledgerst")
		{
			$this->load->helper('text');
			$ledger_id = (int)$this->uri->segment(4);
			if ($ledger_id < 1)
				return;

			$this->load->model('Ledger_model');

			$cur_balance = 0;
			$counter = 0;
			$ledgerst = array();

			$ledgerst[$counter] = array ("", "", "LEDGER STATEMENT FOR " . strtoupper($this->Ledger_model->get_name($ledger_id)), "", "", "", "", "", "", "", "");
			$counter++;
			$ledgerst[$counter] = array ("", "", "FY " . date_mysql_to_php($this->config->item('account_fy_start')) . " - " . date_mysql_to_php($this->config->item('account_fy_end')), "", "", "", "", "", "", "", "");
			$counter++;

			$ledgerst[$counter][0]= "Date";
			$ledgerst[$counter][1]= "Number";
			$ledgerst[$counter][2]= "Ledger Name";
			$ledgerst[$counter][3]= "Narration";
			$ledgerst[$counter][4]= "Type";
			$ledgerst[$counter][5]= "";
			$ledgerst[$counter][6]= "Dr Amount";
			$ledgerst[$counter][7]= "";
			$ledgerst[$counter][8]= "Cr Amount";
			$ledgerst[$counter][9]= "";
			$ledgerst[$counter][10]= "Balance";
			$counter++;

			/* Opening Balance */
			list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($ledger_id);
			$ledgerst[$counter] = array ("Opening Balance", "", "", "", "", "", "", "", "", convert_dc($optype), $opbalance);
			if ($optype == "D")
				$cur_balance = float_ops($cur_balance, $opbalance, '+');
			else
				$cur_balance = float_ops($cur_balance, $opbalance, '-');
			$counter++;

			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc');
			$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
			$this->db->where('date >=', $date1);
			$this->db->where('date <=', $date2);
			$ledgerst_q = $this->db->get();
			foreach ($ledgerst_q->result() as $row)
			{
				/* Entry Type */
				$current_entry_type = entry_type_info($row->entries_entry_type);

				$ledgerst[$counter][0] = date_mysql_to_php($row->entries_date);
				$ledgerst[$counter][1] = full_entry_number($row->entries_entry_type, $row->entries_number);

				/* Opposite entry name */
				$ledgerst[$counter][2] = $this->Ledger_model->get_opp_ledger_name($row->entries_id, $current_entry_type['label'], $row->entry_items_dc, 'text');
				$ledgerst[$counter][3] = $row->entries_narration;
				$ledgerst[$counter][4] = $current_entry_type['name'];

				if ($row->entry_items_dc == "D")
				{
					$cur_balance = float_ops($cur_balance, $row->entry_items_amount, '+');
					$ledgerst[$counter][5] = convert_dc($row->entry_items_dc);
					$ledgerst[$counter][6] = $row->entry_items_amount;
					$ledgerst[$counter][7] = "";
					$ledgerst[$counter][8] = "";

				} else {
					$cur_balance = float_ops($cur_balance, $row->entry_items_amount, '-');
					$ledgerst[$counter][5] = "";
					$ledgerst[$counter][6] = "";
					$ledgerst[$counter][7] = convert_dc($row->entry_items_dc);
					$ledgerst[$counter][8] = $row->entry_items_amount;
				}

				if (float_ops($cur_balance, 0, '=='))
				{
					$ledgerst[$counter][9] = "";
					$ledgerst[$counter][10] = 0;
				} else if (float_ops($cur_balance, 0, '<')) {
					$ledgerst[$counter][9] = "Cr";
					$ledgerst[$counter][10] = convert_cur(-$cur_balance);
				} else {
					$ledgerst[$counter][9] = "Dr";
					$ledgerst[$counter][10] =  convert_cur($cur_balance);
				}
				$counter++;
			}

			$ledgerst[$counter][0]= "Closing Balance";
			$ledgerst[$counter][1]= "";
			$ledgerst[$counter][2]= "";
			$ledgerst[$counter][3]= "";
			$ledgerst[$counter][4]= "";
			$ledgerst[$counter][5]= "";
			$ledgerst[$counter][6]= "";
			$ledgerst[$counter][7]= "";
			$ledgerst[$counter][8]= "";
			if (float_ops($cur_balance, 0, '<'))
			{
				$ledgerst[$counter][9]= "Cr";
				$ledgerst[$counter][10]= convert_cur(-$cur_balance);
			} else {
				$ledgerst[$counter][9]= "Dr";
				$ledgerst[$counter][10]= convert_cur($cur_balance);
			}
			$counter++;

			$ledgerst[$counter] = array ("", "", "", "", "", "", "", "", "", "", "");
			$counter++;

			/* Final Opening and Closing Balance */
			$clbalance = $this->Ledger_model->get_ledger_balance($ledger_id);

			$ledgerst[$counter] = array ("Opening Balance", convert_dc($optype), $opbalance, "", "", "", "", "", "", "", "");
			$counter++;

			if (float_ops($clbalance, 0, '=='))
				$ledgerst[$counter] = array ("Closing Balance", "", 0, "", "", "", "", "", "", "", "");
			else if ($clbalance < 0)
				$ledgerst[$counter] = array ("Closing Balance", "Cr", convert_cur(-$clbalance), "", "", "", "", "", "", "", "");
			else
				$ledgerst[$counter] = array ("Closing Balance", "Dr", convert_cur($clbalance), "", "", "", "", "", "", "", "");

			$this->load->helper('csv');
			echo array_to_csv($ledgerst, "ledgerst.csv");
			return;
		}

		/********************** RECONCILIATION ************************/
		if ($statement == "reconciliation")
		{
			$ledger_id = (int)$this->uri->segment(4);
			$reconciliation_type = $this->uri->segment(5);

			if ($ledger_id < 1)
				return;
			if ( ! (($reconciliation_type == 'all') or ($reconciliation_type == 'pending')))
				return;

			$this->load->model('Ledger_model');
			$cur_balance = 0;
			$counter = 0;
			$ledgerst = array();

			$ledgerst[$counter] = array ("", "", "RECONCILIATION STATEMENT FOR " . strtoupper($this->Ledger_model->get_name($ledger_id)), "", "", "", "", "", "", "");
			$counter++;
			$ledgerst[$counter] = array ("", "", "FY " . date_mysql_to_php($this->config->item('account_fy_start')) . " - " . date_mysql_to_php($this->config->item('account_fy_end')), "", "", "", "", "", "", "");
			$counter++;

			$ledgerst[$counter][0]= "Date";
			$ledgerst[$counter][1]= "Number";
			$ledgerst[$counter][2]= "Ledger Name";
			$ledgerst[$counter][3]= "Narration";
			$ledgerst[$counter][4]= "Type";
			$ledgerst[$counter][5]= "";
			$ledgerst[$counter][6]= "Dr Amount";
			$ledgerst[$counter][7]= "";
			$ledgerst[$counter][8]= "Cr Amount";
			$ledgerst[$counter][9]= "Reconciliation Date";
			$counter++;

			/* Opening Balance */
			
			list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($ledger_id);

			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, entry_items.reconciliation_date as entry_items_reconciliation_date');
			if ($reconciliation_type == 'all')
				$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
			else
				$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.reconciliation_date', NULL)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
			$ledgerst_q = $this->db->get();
			foreach ($ledgerst_q->result() as $row)
			{
				/* Entry Type */
				$current_entry_type = entry_type_info($row->entries_entry_type);

				$ledgerst[$counter][0] = date_mysql_to_php($row->entries_date);
				$ledgerst[$counter][1] = full_entry_number($row->entries_entry_type, $row->entries_number);

				/* Opposite entry name */
				$ledgerst[$counter][2] = $this->Ledger_model->get_opp_ledger_name($row->entries_id, $current_entry_type['label'], $row->entry_items_dc, 'text');
				$ledgerst[$counter][3] = $row->entries_narration;
				$ledgerst[$counter][4] = $current_entry_type['name'];

				if ($row->entry_items_dc == "D")
				{
					$ledgerst[$counter][5] = convert_dc($row->entry_items_dc);
					$ledgerst[$counter][6] = $row->entry_items_amount;
					$ledgerst[$counter][7] = "";
					$ledgerst[$counter][8] = "";

				} else {
					$ledgerst[$counter][5] = "";
					$ledgerst[$counter][6] = "";
					$ledgerst[$counter][7] = convert_dc($row->entry_items_dc);
					$ledgerst[$counter][8] = $row->entry_items_amount;
				}

				if ($row->entry_items_reconciliation_date)
				{
					$ledgerst[$counter][9] = date_mysql_to_php($row->entry_items_reconciliation_date);
				} else {
					$ledgerst[$counter][9] = "";
				}
				$counter++;
			}

			$counter++;
			$ledgerst[$counter] = array ("", "", "", "", "", "", "", "", "", "");
			$counter++;

			/* Final Opening and Closing Balance */
			$clbalance = $this->Ledger_model->get_ledger_balance($ledger_id);

			$ledgerst[$counter] = array ("Opening Balance", convert_dc($optype), $opbalance, "", "", "", "", "", "", "");
			$counter++;

			if (float_ops($clbalance, 0, '=='))
				$ledgerst[$counter] = array ("Closing Balance", "", 0, "", "", "", "", "", "", "");
			else if (float_ops($clbalance, 0, '<'))
				$ledgerst[$counter] = array ("Closing Balance", "Cr", convert_cur(-$clbalance), "", "", "", "", "", "", "");
			else
				$ledgerst[$counter] = array ("Closing Balance", "Dr", convert_cur($clbalance), "", "", "", "", "", "", "");

			/************* Final Reconciliation Balance ***********/

			/* Reconciliation Balance - Dr */
			$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D')->where('entry_items.reconciliation_date IS NOT NULL');
			$dr_total_q = $this->db->get();
			if ($dr_total = $dr_total_q->row())
				$reconciliation_dr_total = $dr_total->drtotal;
			else
				$reconciliation_dr_total = 0;

			/* Reconciliation Balance - Cr */
			$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C')->where('entry_items.reconciliation_date IS NOT NULL');
			$cr_total_q = $this->db->get();
			if ($cr_total = $cr_total_q->row())
				$reconciliation_cr_total = $cr_total->crtotal;
			else
				$reconciliation_cr_total = 0;

			$reconciliation_total = float_ops($reconciliation_dr_total, $reconciliation_cr_total, '-');
			$reconciliation_pending = float_ops($clbalance, $reconciliation_total, '-');

			$counter++;
			if (float_ops($reconciliation_pending, 0, '=='))
				$ledgerst[$counter] = array ("Reconciliation Pending", "", 0, "", "", "", "", "", "", "");
			else if (float_ops($reconciliation_pending, 0, '<'))
				$ledgerst[$counter] = array ("Reconciliation Pending", "Cr", convert_cur(-$reconciliation_pending), "", "", "", "", "", "", "");
			else
				$ledgerst[$counter] = array ("Reconciliation Pending", "Dr", convert_cur($reconciliation_pending), "", "", "", "", "", "", "");

			$counter++;
			if (float_ops($reconciliation_total, 0, '=='))
				$ledgerst[$counter] = array ("Reconciliation Total", "", 0, "", "", "", "", "", "", "");
			else if (float_ops($reconciliation_total, 0, '<'))
				$ledgerst[$counter] = array ("Reconciliation Total", "Cr", convert_cur(-$reconciliation_total), "", "", "", "", "", "", "");
			else
				$ledgerst[$counter] = array ("Reconciliation Total", "Dr", convert_cur($reconciliation_total), "", "", "", "", "", "", "");

			$this->load->helper('csv');
			echo array_to_csv($ledgerst, "reconciliation.csv");
			return;
		}
		
		/************************ BALANCE SHEET ***********************/
		if ($statement == "balancesheet" || $statement == "new_balancesheet")
		{
			$this->load->library('accountlist');
			$this->load->model('Ledger_model');

			/* Liabilities and Owners Equity */
			$liability_total = 0;
			$liability = new Accountlist();
			$liability->init(2);
			$liability_total = -$liability->total;
			$liability_array = $liability->build_array();
			$liability->to_csv($liability_array);
			Accountlist::add_blank_csv();

			/* asset */
			$asset_total = 0;
			$asset = new Accountlist();
			$asset->init(1);
			$asset_total = $asset->total;
			$asset_array = $asset->build_array();
			$asset->to_csv($asset_array);
			
			Accountlist::add_blank_csv();
			
			$income = new Accountlist();
			$income->init(3);
			$expense = new Accountlist();
			$expense->init(4);
			$income_total = -$income->total;
			$expense_total = $expense->total;
			$pandl = float_ops($income_total, $expense_total, '-');
			$diffop = $this->Ledger_model->get_diff_op_balance();

			Accountlist::add_blank_csv();
			Accountlist::add_blank_csv();

			/* Liability side */
			$total = $liability_total;
			Accountlist::add_row_csv(array("Liabilities and Owners Equity Total", convert_cur($liability_total)));
		
			/* If Profit then Liability side, If Loss then Asset side */
			if (float_ops($pandl, 0, '!='))
			{
				if (float_ops($pandl, 0, '>'))
				{
					$total = float_ops($total, $pandl, '+');
					Accountlist::add_row_csv(array("Income & Expenditure Account (Net Income)", convert_cur($pandl)));
				}
			}

			/* If Op balance Dr then Liability side, If Op balance Cr then Asset side */
			if (float_ops($diffop, 0, '!='))
			{
				if (float_ops($diffop, 0, '>'))
				{
					$total = float_ops($total, $diffop, '+');
					Accountlist::add_row_csv(array("Diff in O/P Balance", "Dr " . convert_cur($diffop)));
				}
			}

			Accountlist::add_row_csv(array("Total - Liabilities and Owners Equity", convert_cur($total)));

			/* Asset side */
			$total = $asset_total;
			Accountlist::add_row_csv(array("Asset Total", convert_cur($asset_total)));
		
			/* If Profit then Liability side, If Loss then Asset side */
			if (float_ops($pandl, 0, '!='))
			{
				if (float_ops($pandl, 0, '<'))
				{
					$total = float_ops($total, -$pandl, '+');
					Accountlist::add_row_csv(array("Income And Expenditure Account (Net Expenditure)", convert_cur(-$pandl)));
				}
			}
		
			/* If Op balance Dr then Liability side, If Op balance Cr then Asset side */
			if (float_ops($diffop, 0, '!='))
			{
				if (float_ops($diffop, 0, '<'))
				{
					$total = float_ops($total, -$diffop, '+');
					Accountlist::add_row_csv(array("Diff in O/P Balance", "Cr " . convert_cur(-$diffop)));
				}
			}

			Accountlist::add_row_csv(array("Total - Assets", convert_cur($total)));

			$balancesheet = Accountlist::get_csv();
			$this->load->helper('csv');
			echo array_to_csv($balancesheet, "balancesheet.csv");
			return;
		}

		/********************** PROFIT AND LOSS ***********************/
		if ($statement == "profitandloss")
		{
			$this->load->library('accountlist');
			$this->load->model('Ledger_model');

			/***************** GROSS CALCULATION ******************/

			/* Gross P/L : Expenses */
			$gross_expense_total = 0;
			$this->db->from('groups')->where('parent_id', 4)->where('affects_gross', 1);
			$gross_expense_list_q = $this->db->get();
			foreach ($gross_expense_list_q->result() as $row)
			{
				$gross_expense = new Accountlist();
				$gross_expense->init($row->id);
				$gross_expense_total = float_ops($gross_expense_total, $gross_expense->total, '+');
				$gross_exp_array = $gross_expense->build_array();
				$gross_expense->to_csv($gross_exp_array);
			}
			Accountlist::add_blank_csv();

			/* Gross P/L : Incomes */
			$gross_income_total = 0;
			$this->db->from('groups')->where('parent_id', 3)->where('affects_gross', 1);
			$gross_income_list_q = $this->db->get();
			foreach ($gross_income_list_q->result() as $row)
			{
				$gross_income = new Accountlist();
				$gross_income->init($row->id);
				$gross_income_total = float_ops($gross_income_total, $gross_income->total, '+');
				$gross_inc_array = $gross_income->build_array();
				$gross_income->to_csv($gross_inc_array);
			}

			Accountlist::add_blank_csv();
			Accountlist::add_blank_csv();

			/* Converting to positive value since Cr */
			$gross_income_total = -$gross_income_total;

			/* Calculating Gross P/L */
			$grosspl = float_ops($gross_income_total, $gross_expense_total, '-');

			/* Showing Gross P/L : Expenses */
			$grosstotal = $gross_expense_total;
			Accountlist::add_row_csv(array("Total Gross Expenses", convert_cur($gross_expense_total)));
			if (float_ops($grosspl, 0, '>'))
			{
				$grosstotal = float_ops($grosstotal, $grosspl, '+');
				Accountlist::add_row_csv(array("Gross Income C/O", convert_cur($grosspl)));
			}
			Accountlist::add_row_csv(array("Total Expenses - Gross", convert_cur($grosstotal)));

			/* Showing Gross P/L : Incomes  */
			$grosstotal = $gross_income_total;
			Accountlist::add_row_csv(array("Total Gross Incomes", convert_cur($gross_income_total)));

			if (float_ops($grosspl, 0, '>'))
			{

			} else if (float_ops($grosspl, 0, '<')) {
				$grosstotal = float_ops($grosstotal, -$grosspl, '+');
				Accountlist::add_row_csv(array("Gross Loss C/O", convert_cur(-$grosspl)));
			}
			Accountlist::add_row_csv(array("Total Incomes - Gross", convert_cur($grosstotal)));

			/************************* NET CALCULATIONS ***************************/

			Accountlist::add_blank_csv();
			Accountlist::add_blank_csv();
			
			/* Net P/L : Expenses */
			$net_expense_total = 0;
			$this->db->from('groups')->where('parent_id', 4)->where('affects_gross !=', 1);	
			$net_expense_list_q = $this->db->get();
			foreach ($net_expense_list_q->result() as $row)
			{
				$net_expense = new Accountlist();
				$net_expense->init($row->id);
				$net_expense_total = float_ops($net_expense_total, $net_expense->total, '+');
				$net_exp_array = $net_expense->build_array();
				$net_expense->to_csv($net_exp_array);
			}
			Accountlist::add_blank_csv();

			/* Net P/L : Incomes */
			$net_income_total = 0;
			$this->db->from('groups')->where('parent_id', 3)->where('affects_gross !=', 1);	
			$net_income_list_q = $this->db->get();
			foreach ($net_income_list_q->result() as $row)
			{
				$net_income = new Accountlist();
				$net_income->init($row->id);
				$net_income_total = float_ops($net_income_total, $net_income->total, '+');
				$net_inc_array = $net_income->build_array();
				$net_income->to_csv($net_inc_array);
			}

			Accountlist::add_blank_csv();
			Accountlist::add_blank_csv();

			/* Converting to positive value since Cr */
			$net_income_total = -$net_income_total;

			/* Calculating Net P/L */
			$netpl = float_ops(float_ops($net_income_total, $net_expense_total, '-'), $grosspl, '+');

			/* Showing Net P/L : Expenses */
			$nettotal = $net_expense_total;
			Accountlist::add_row_csv(array("Total Expenses", convert_cur($nettotal)));

			if (float_ops($grosspl, 0, '>'))
			{
			} else if (float_ops($grosspl, 0, '<')) {
				$nettotal = float_ops($nettotal, -$grosspl, '+');
				Accountlist::add_row_csv(array("Gross Loss B/F", convert_cur(-$grosspl)));
			}
			if (float_ops($netpl, 0, '>'))
			{
				$nettotal = float_ops($nettotal, $netpl, '+');
				Accountlist::add_row_csv(array("Net Income ", convert_cur($netpl)));
			}
			Accountlist::add_row_csv(array("Total - Net Expenses", convert_cur($nettotal)));

			/* Showing Net P/L : Incomes */
			$nettotal = $net_income_total;
			Accountlist::add_row_csv(array("Total Incomes", convert_cur($nettotal)));

			if ($grosspl > 0)
			{
				$nettotal = float_ops($nettotal, $grosspl, '+');
				Accountlist::add_row_csv(array("Gross Income B/F", convert_cur($grosspl)));
			}

			if ($netpl > 0)
			{

			} else if ($netpl < 0) {
				$nettotal = float_ops($nettotal, -$netpl, '+');
				Accountlist::add_row_csv(array("Net Expenses", convert_cur(-$netpl)));
			}
			Accountlist::add_row_csv(array("Total - Net Incomes", convert_cur($nettotal)));

			$balancesheet = Accountlist::get_csv();
			$this->load->helper('csv');
			echo array_to_csv($balancesheet, "profitandloss.csv");
			return;
		}

		/********************** PAYMENT AND RECEIPT ***********************/
		if ($statement == "paymentreceipt")
		{
			$this->load->library('accountlist');
			$this->load->model('Ledger_model');

			/* Payment */
			$this->db->from('groups')->where('parent_id', 4)->where('affects_gross !=', 1);
			$net_expense_list_q = $this->db->get();
			foreach ($net_expense_list_q->result() as $row)
			{
				$net_expense = new Accountlist();
				$net_expense->init($row->id);
				$net_exp_array = $net_expense->build_array();
				$net_expense->to_csv($net_exp_array);
			}
			Accountlist::add_blank_csv();

			/* Receipt */
			$this->db->from('groups')->where('parent_id', 3)->where('affects_gross !=', 1);
			$net_income_list_q = $this->db->get();
			foreach ($net_income_list_q->result() as $row)
			{
				$net_income = new Accountlist();
				$net_income->init($row->id);
				$net_inc_array = $net_income->build_array();
				$net_income->to_csv($net_inc_array);
			}

			Accountlist::add_blank_csv();
			Accountlist::add_blank_csv();

			
			$balancesheet = Accountlist::get_csv();
			$this->load->helper('csv');
			echo array_to_csv($balancesheet, "paymentreceipt.csv");
			return;
		}

		return;
	}

	function printpreview($statement, $id = NULL)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		/********************** TRIAL BALANCE *************************/
		if ($statement == "trialbalance")
		{
			$data['report'] = "report/trialbalance";
			$data['title'] = "Trial Balance";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}

		if ($statement == "balancesheet")
		{
			$data['report'] = "report/balancesheet";
			$data['title'] = "Balance Sheet";
			$data['left_width'] = "";
			$data['right_width'] = "";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}

		if ($statement == "profitandloss")
		{
			$data['report'] = "report/profitandloss";
			$data['title'] = "Income And Expenditure Statement";
			$data['left_width'] = "";
			$data['right_width'] = "";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}

		if ($statement == "depreciation")
                {
                       $data['report'] = "report/depreciation";
                       $data['title'] = "Depreciation Of Assets";
                       $data['print_preview'] = TRUE;
                       $data['left_width'] = "";
                       $data['right_width'] = "";
                       $this->load->view('report/report_template', $data);
                       return;
                }

		if ($statement == "paymentreceipt")
		{
			$data['report'] = "report/paymentreceipt";
			$data['title'] = "Payment & Receipt";
			$data['left_width'] = "";
			$data['right_width'] = "";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}
		if ($statement == "ledgerst")
		{
			$this->load->helper('text');
			$page_count = 0;
			/* Pagination setup */
			$this->load->library('pagination');
			$data['ledger_id'] = $this->uri->segment(4);
			$data['page_count'] = $page_count;
			/* Checking for valid ledger id */ 
			if ($data['ledger_id'] < 1)
			{
				$this->messages->add('Invalid Ledger account.', 'error');
				redirect('report/ledgerst');
				return;
			}
			$this->db->from('ledgers')->where('id', $data['ledger_id'])->limit(1);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Ledger account.', 'error');
				redirect('report/ledgerst');
				return;
			}
			$data['report'] = "report/ledgerst";
			$data['title'] = "Ledger Statement for '" . $this->Ledger_model->get_name($data['ledger_id']) . "'";
			$data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}

		if ($statement == "reconciliation")
		{
			$this->load->helper('text');

			$data['show_all'] = FALSE;
			$data['ledger_id'] = $this->uri->segment(4);

			/* Check if path is 'all' or 'pending' */
			if ($this->uri->segment(5) == 'all')
			{
				$data['reconciliation_type'] = 'all';
				$data['show_all'] = TRUE;
			} else if ($this->uri->segment(5) == 'pending') {
				$data['reconciliation_type'] = 'pending';
				$data['show_all'] = FALSE;
			} else {
				$this->messages->add('Invalid path.', 'error');
				redirect('report/reconciliation/pending');
				return;
			}

			/* Checking for valid ledger id and reconciliation status */
			if ($data['ledger_id'] > 0)
			{
				$this->db->from('ledgers')->where('id', $data['ledger_id'])->where('reconciliation', 1)->limit(1);
				if ($this->db->get()->num_rows() < 1)
				{
					$this->messages->add('Invalid Ledger account or Reconciliation is not enabled for the Ledger account.', 'error');
					redirect('report/reconciliation/' . $reconciliation_type);
					return;
				}
			} else if ($data['ledger_id'] < 0) {
				$this->messages->add('Invalid Ledger account.', 'error');
				redirect('report/reconciliation/' . $reconciliation_type);
				return;
			}

			$data['report'] = "report/reconciliation";
			$data['title'] = "Reconciliation Statement for '" . $this->Ledger_model->get_name($data['ledger_id']) . "'";
			$data['print_preview'] = TRUE;
			$this->load->view('report/report_template', $data);
			return;
		}

		if ($statement == "new_balancesheet")
                {
                        $data['report'] = "report/new_balancesheet";
                        $data['title'] = "Balance Sheet MHRD Format";
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/report_template', $data);
                        return;
                }

		return;
	}
}

/* End of file report.php */
/* Location: ./system/application/controllers/report.php */
