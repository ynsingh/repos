<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Report extends Controller {
	var $acc_array;
	var $account_counter;
	var $logndb;
	var $ledger_data;
	var $id;
	var $name;
	var $code;
	var $children_groups = array();
	var $counter = 1;

	function Report()
	{
		parent::Controller();
		$this->load->model('Ledger_model');
		$this->load->model('Budget_model');
		$this->load->model('Group_model');
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
		$this->template->set('nav_links', array('report/download/balancesheet' => 'Download CSV', 'report/printpreview/balancesheet' => 'Print Preview', 'report/pdf/balancesheet' => 'Download PDF'));
		$data['left_width'] = "300";
		$data['right_width'] = "125";
                $data['print_preview'] =FALSE;
		$page_count = " ";

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);

                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

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
			$this->template->load('template', 'report/balancesheet', $data);
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
		$this->template->load('template', 'report/balancesheet', $data);
		return;
	}

	function pdf($statement, $id = NULL)
	{
	        $this->load->helper('pdf_helper');
		$this->load->library('session');
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');
		$code = $this->session->userdata('code');
                $search = $this->session->userdata('search');
                $text = $this->session->userdata('text');
                $count = $id;
		if($statement == "balancesheet")
		{
		$data['report'] = "report/balancesheet";
                $data['statement'] = "Balance Sheet";
                $data['left_width'] = "100";
                $data['right_width'] = "75";
                $data['print_preview'] = TRUE;
                $data['entry_date1'] = $date1;
                $data['entry_date2'] = $date2;
	        $this->load->view('report/pdfreport',$data);
		return;
		}
		 
		if ($statement == "trialbalance")
                {
                        $data['report'] = "report/trialbalance";
                        $data['statement'] = "Trial Balance";
			$data['width'] = "100%";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }

		if ($statement == "new_balancesheet")
                {
                        $data['report'] = "report/new_balancesheet";
                        $data['statement'] = "Balance Sheet MHRD Format";
                        $data['left_width'] = "100";
                        $data['right_width'] = "75";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
		}
		
		if($statement == "new_mhrd")
                {
                $data['report'] = "report/new_mhrd";
                $data['statement'] = "Balance Sheet MHRD Format-NEW";
                $data['left_width'] = "100";
                $data['right_width'] = "75";
                $data['print_preview'] = TRUE;
                $data['entry_date1'] = $date1;
                $data['entry_date2'] = $date2;
                $this->load->view('report/pdfreport',$data);
                return;
                }
		
		if ($statement == "profitandloss")
                {
                        $data['report'] = "report/profitandloss";
                        $data['statement'] = "Income And Expenditure Statement";
                        $data['left_width'] = "100";
                        $data['right_width'] = "75";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }

		if ($statement == "paymentreceipt")
                {
                        $data['report'] = "report/paymentreceipt";
                        $data['statement'] = "Payment & Receipt";
                        $data['left_width'] = "100";
                        $data['right_width'] = "75";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }
		if($statement == "dayst")
                {
                 $this->load->helper('text');
                        $data['width'] = "100%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['page_count'] = $page_count;
                        $data['report'] = "report/dayst";
                        $data['statement'] = "Ledger Statement for the day :".$date1;
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $this->load->view('report/pdfreport', $data);
                        $this->session->unset_userdata('date1');
                        return;
                }

		if($statement == "cashst")
                {
                 	$this->load->helper('text');
                        $data['width'] = "100%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['page_count'] = $page_count;
                        $data['report'] = "report/cashst";
                        $data['print_preview'] = TRUE;
			$data['statement'] = "Cash Statement";
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }

	
		if($statement == "ledgerst")
		{
		 $this->load->helper('text');
			$data['width'] = "100%";
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
                        $data['statement'] = "Ledger Statement for '" . $this->Ledger_model->get_name($data['ledger_id']) . "'";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        $this->session->unset_userdata('date1');
                        $this->session->unset_userdata('date2');
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
                        $data['statement'] = "Reconciliation Statement for '" . $this->Ledger_model->get_name($data['ledger_id']) . "'";
                        $data['print_preview'] = TRUE;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }

	
		return;
	}
	 
	function depreciation($period = NULL)
        {
                $this->load->library('session');
                $this->template->set('nav_links', array('report/depreciation' => 'Depreciation As Today', 'report/update' => 'Depreciiation Rate', 'report/printpreview/depreciation' => 'Print Preview'));
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
		// code for searching a given text
		$text = '';
		$data['search'] = '';
		$data['gross_expense_list_q'] = '';
		$data['user_data'] = '';
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
		$data['search_by'] = array(
			"Select" => "Select",
                        "ERPMIM_Item_Brief_Desc#asset_name" => "Asset Name",
                        "IRD_WEF_Date#date_of_purchase"=> "Date of Purchase",
			"total_cost#cost" => "Total Cost",
			"dep_amount" => "Dep.Amount",
			"curr_value" => "Current Value",
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
		/* Form validation */

		$this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
		$this->form_validation->set_rules('text', 'Text', 'trim|required');
		/* Validating form */

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report/depreciation', $data);
			return;
		}
		else
		{
			$data_search_by = $this->input->post('search_by', TRUE);
			$data_text = $this->input->post('text', TRUE);
		}
		//if text is negative
                if(abs($data_text) == -1) {
                        $this->messages->add('Text should be a positive value.', 'error');
                        redirect('report/depreciation');
                }
	
		if($data_search_by == "total_cost" || $data_search_by == "dep_amount" || $data_search_by == "curr_value") {
			if(! is_numeric($data_text)) {
				$this->messages->add('Please enter numeric value.', 'error');
				redirect('report/depreciation');
			}
		}
		if($data_search_by == "IRD_WEF_Date") {
			$search_text = $data_text;
			// if date is single digit
			if(ctype_digit($data_text)) {
				$field = $data_search_by . '      ' . 'LIKE';
			}
			else {
				$date=explode('-', $data_text);
				// if date format is yy-mm-dd only
				if(count($date)>1) {
				// if date contain two digit
					if(count($date) == '2') {
						//text is in mm-dd 
						if(strlen($date['0']) == 2) {
							// if month is character
							if(ctype_alpha($date[0])) {
								if(strtotime($date[0])) {
									$month = $date[0];
									$x = date('m', strtotime($month));
									$data_text = $x . "-" . $date[1];
									$field = $data_search_by . '      ' . 'LIKE';
								}
								else {
									$this->messages->add('Invalid Month.', 'error');
									redirect('report/depreciation');
								}
							}
							// if month is digit
							else if(ctype_digit($date['0'])) {
							// if month is valid or not
								if("1" <= $date['0'] && $date['0'] <= "12") {
									$field = $data_search_by . '      ' . 'LIKE';
									$data_text = $date[0]. "-" . $date[1];
								}
								else {
									$this->messages->add('Invalid Month.', 'error');
									redirect('report/depreciation');
								}
							}
							// if date is invalid
							else {
								$this->messages->add('Invalid date format. Please enter date in yy-mm-dd format.', 'error');
								redirect('report/depreciation');
							}
						}
						//text is in yy-mm 
						if(strlen($date['0']) == 4) {
							if($date['0'] == $fy_start || $date['0'] == $fy_end) {
								$data_text = $date['0']."-".$date['1'];
							}
							else {
								$this->messages->add($data_text . ' does not exist in financial year.', 'error');
								redirect('report/depreciation');
							}
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
							$data_text = $date[0]. "-" . $x . "-" . $date[2];
						}
						$data_text = $date0. "-" . $x . "-" . $date2;
						//check for date is valid or not
						$valid_date = checkdate($x,$date2,$date0);
						if($valid_date == 'true') {
						//check date is exist in financial year or not
							if($date0 == $fy_start || $date0 == $fy_end) {
								$data_text = $date2."-". $x ."-".$date0;
							}
							else {
								$this->messages->add($data_text . ' does not exist in financial year.', 'error');
								redirect('report/depreciation');
							}
						}
						else {
							$this->messages->add($data_text . ' is invalid date.', 'error');
							redirect('report/depreciation');
						}	
					}
				}
				else {
					$this->messages->add('Invalid date format. Please enter date in yy-mm-dd format.', 'error');
					redirect('report/depreciation');
				}
			}
		}
		$newrange = array(
                        'search'=>$data_search_by,
                        'text'=>$data_text
                        );
                $this->session->set_userdata($newrange);	
		$data['search'] = $data_search_by;
		//$data['text'] = $data_text;
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
	       	$this->template->set('nav_links', array( 'report/depreciation' => 'Depreciation As Today'));
		$this->load->model('Depreciation_model');
		$account_code = $this->Budget_model->get_account_code('Fixed Assets');
		$check_asset_register = $this->Depreciation_model->dep_master_details();
		$counter=0;
		$counter1=0;
		$this->dep_master['dep'] = $check_asset_register;
                foreach ($check_asset_register as $id => $bud)
                {
                        $name = 'budget_value'. "_" .$bud['id'];
                        $this->dep_master[$name] = array(
                                'name' => $name,
                                'id' => $bud['id'],
                                'maxlength' => '100',
                                'size' => '40',
                                //'value' => '',
                                'value' => $bud['percentage'],
                        );
                        $counter++;
                }
		$this->dep_master1['dep'] = $check_asset_register;
		foreach ($check_asset_register as $id => $bud1)
                {
                        $name1 = 'budget_value'. "_" .$bud1['id'];
                        $this->dep_master1[$name1] = array(
                                'name' => $name1,
                                'id' => $bud1['id'],
                                'maxlength' => '100',
                                'size' => '40',
                                //'value' => '',
                                'value' => $bud1['life_time'],
                        );
                        $counter1++;
                }
	

               /*this->db->select('name, code');
                $this->db->from('ledgers')->where('code LIKE', $account_code.'%');
                $gross_expense_list_q = $this->db->get();       
		$counter=0;
	  	foreach($gross_expense_list_q->result() as $row1){
                        $name=$row1->name;
                        $code=$row1->code;
			
                        /*load database pico
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

        	/* Repopulating form
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
				
        	}*/
		foreach ($this->dep_master['dep'] as $id => $bud)
                {
                        $name = 'budget_value'. "_" .$bud['id'];
                        $this->form_validation->set_rules($name, 'Budget Value', 'trim');
                }
		foreach ($this->dep_master1['dep'] as $id => $bud1)
                {
                        $name1 = 'budget_value1'. "_" .$bud1['id'];
                        $this->form_validation->set_rules($name1, 'Budget Value', 'trim');
                }


		 if ($_POST)
                {
                        foreach ($this->dep_master['dep'] as $id => $bud)
                        {
                                $name = 'budget_value'. "_" .$bud['id'];
                                $this->dep_master[$name]['value'] = $this->input->post($name, TRUE);
                        }
			foreach ($this->dep_master1['dep'] as $id => $bud1)
                        {
                                $name1 = 'budget_value1'. "_" .$bud1['id'];
                                $this->dep_master1[$name1]['value'] = $this->input->post($name1, TRUE);
                        }

                }
		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'report/update', $this->dep_master);
                        return;
                }

		else{
		foreach ($this->dep_master1['dep'] as $id => $bud1)
                {
			  $name1 = 'budget_value1'. "_" .$bud1['id'];
			$life_time= $this->input->post($name1, TRUE);
                                echo"---->".$life_time;
			 $this->db->trans_start();
                                        $update_data = array(
                                                'life_time' => $life_time,
                                                );
                                         if ( ! $this->db->where('id', $bud1['id'])->update('depreciation_master', $update_data))
                                         {
                                                $this->db->trans_rollback();
                                                $this->messages->add('Percentage of Asset is updated ', 'error');
                                                $this->template->load('template', 'report/update', $this->dep_master);
                                                return;
                                        } else {
                                                $this->db->trans_complete();
                                        }

			

		}
		foreach ($this->dep_master['dep'] as $id => $bud)
                        {
                                $name = 'budget_value'. "_" .$bud['id'];
                                $new_percentage= $this->input->post($name, TRUE);
			//	echo"!!!!!!!!!!!".$new_percentage;
				$this->db->trans_start();
                                        $update_data = array(
                                                'percentage' => $new_percentage,
                                                );
                                         if ( ! $this->db->where('id', $bud['id'])->update('depreciation_master', $update_data))
                                         {
                                                $this->db->trans_rollback();
                                                $this->messages->add('Percentage of Asset is updated ', 'error');
                                                $this->template->load('template', 'report/update', $this->dep_master);
                                                return;
                                        } else {
                                                $this->db->trans_complete();
                                        }       

                        }

		}

	
       		/*Form validations
		
		$counter = 0;	
		for($check = $this->ledger_data; $check > 0; $check--){
			$key = 'value_'.$counter;
       			foreach ($this->depreciation[$key]->result() as $row)
        		{
        			$name = 'dep_value'. "_" .$row->ERPMIM_ID;
			       	$this->form_validation->set_rules($name, 'Per_value1', 'trim|required');
        		}
			$counter++;
                        
		}*/
		

		/* vaildating form
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

		$this->logndb->close();*/
		redirect('report/update');
	//	$this->template->load('template', 'report/update', $this->dep_master);
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
		 $data['detail'] = $user_data;
		 if($user_data->num_rows() == 0){
	                $data['pico'] = '1';
                        $this->db->from('new_asset_register')->where('asset_name', $ERPMIM_Item_Brief_Desc);
                        $led_details = $this->db->get();
			foreach($led_details->result() as $row){
				$data['led_name'] = $row->asset_name;
			}
				$data['detail'] = $led_details;
	
			}
		 $this->template->load('template', 'report/duplicate_entry', $data);
                 return ;
	
        }

	/**
	 * Method for displaying MHRD format balancesheet
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
	 */
	function new_balancesheet($period = NULL)
	{
	/*	$this->db->select('id')->from('groups');
		$this->db->where('name', 'Advances Received');
		$group1 = $this->db->get(); */ 

		$this->db->select('id')->from('groups');
		$this->db->where('name', 'Other liabilities');
		$group2 = $this->db->get();
		//$group3 = $group2->row();
		//$group2_name = $group1->name;

		$this->db->select('chart_account')->from('settings');
		$setting_result = $this->db->get();
		$setting = $setting_result->row();
		$chart = $setting->chart_account;

	//	if($group1->num_rows() < 1 &&
		if($group2->num_rows() < 1 && $chart == 'mhrd'){
			//Edit group names
			$old_names = array('For Goods abd Service', 'Satutory Libilities', 'Other Libilities', 'Receipts against sponsored fellowship and scholarship', 'Grants in Advances');
			$new_names = array('For Goods and Services', 'Statutory Liabilities', 'Other current Liabilities', 'Receipts against sponsored fellowships and scholarships', 'Grants in advance');
			$ids = array('50', '8', '8', '53', '53');
			$counter = 0;
			
			$check = sizeof($old_names);
			while($check > 0){
				$this->db->select('id')->from('groups')->where('name', $old_names[$counter]);
				$this->db->where('parent_id', $ids[$counter]);
				$group_result = $this->db->get();
				$group = $group_result->row();
				$group_id = $group->id;

				$this->db->trans_start();
        	                $update_data = array('name' => $new_names[$counter]);
                	        if ( ! $this->db->where('id', $group_id)->update('groups', $update_data))
                        	{
                                	$this->db->trans_rollback();
	                                $this->logger->write_message("error", "Error updating name of Group account - ". $old_names[$counter]);
        	                } else {
                	                $this->db->trans_complete();
                        	        $this->logger->write_message("success", "Updated Group account name - ". $new_names[$counter]);
                        	}

				$counter++;
				$check--;
			}

			//Add groups
			$parent_id = array('53', '8', '13');
			$group_names = array('Other liabilities', 'Advances Received', 'Fixed Deposits');
			$counter = 0;
			$check = sizeof($parent_id);
			$data_affects_gross = "0";

			while($check > 0){
				$num = 0;
				$g_code = 0;

				/* Only Income or Expense can affect gross profit loss calculation */
	                        if ($parent_id[$counter] == "3" || $parent_id[$counter] == "4")
                	        {
                        	        $data_affects_gross = 1;
	                        } else {
        	                        $data_affects_gross = 0;
                	        }

				$num = $this->Group_model->get_numOfChild($parent_id[$counter]);
        	                $g_code = $this->Group_model->get_group_code($parent_id[$counter]);
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
					$ledger = $ledger_q->result();
        	                        $i++;
                	        }while($ledger_q->num_rows()>0);

	                        $this->db->trans_start();
        	                $insert_data = array(
                	                'code' => $data_code,
                        	        'name' => $group_names[$counter],
                                	'parent_id' => $parent_id[$counter],
	                                'affects_gross' => $data_affects_gross
	                        );
        	                if ( ! $this->db->insert('groups', $insert_data))
                	        {
                        	        $this->db->trans_rollback();
                                	$this->logger->write_message("error", "Error addding Group account - " . $group_names[$counter]);
                	        } else {
                        	        $this->db->trans_complete();
                                	$this->logger->write_message("success", "Added Group account - " . $group_names[$counter]);
                	        }	

				$counter++;
				$check--;		
			}

		}


		$this->load->library('session');
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
                $newdata = array(
                      'date1'  => $date1,
                      'date2'  => $date2
                     );
                $this->session->set_userdata($newdata);

		$this->template->set('page_title', 'Balance Sheet MHRD Format');
                //$this->template->set('nav_links', array('report/download/new_balancesheet' => 'Download CSV', 'report/printpreview/new_balancesheet' => 'Print Preview'));
                $this->template->set('nav_links', array('report/printpreview/new_balancesheet' => 'Print Preview', 'report/printPreview_schedules/1' => 'Print All Schedules', 'report/pdf/new_balancesheet' => 'Download PDF'));
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);

                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

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
			$this->template->load('template', 'report/new_balancesheet', $data);
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

		$this->template->load('template', 'report/new_balancesheet',$data);
		return;
	}

// made by @kanchan
	function new_mhrd()
	{
	
		$this->load->library('session');
                $this->template->set('page_title', 'Balance Sheet MHRD Format-2015');
		$this->template->set('nav_links', array('report/printpreview/new_mhrd' => 'Print Preview', 'report/printPreview_schedules/1' => 'Print All Schedules', 'report/pdf/new_mhrd' => 'Download PDF'));
		$data['left_width'] = "300";
                $data['right_width'] = "125";
                $data['print_preview'] =FALSE;
                $page_count = " ";

                //$default_end_date;

                /* Form fields */
                $this->db->from('settings');
                $detail = $this->db->get();
                foreach ($detail->result() as $row)
                {
                        $date1 = $row->fy_start;
                        $date2 = $row->fy_end;
                }
                $date=explode("-",$date1);
                $date2 = explode("-", $row->fy_end);
                $default_start = '01/04/'.$date[0];
                $default_end = '31/03/'.$date2[0];

                $curr_date = date_today_php();
                if($curr_date >= $default_end) 
		{
                       $default_end_date = $default_end;
                }
                else 
		{
                        $default_end_date = $curr_date;
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
                        'value' => $default_end_date,
                );

                $data['print_preview'] =FALSE;

                $data_date1 = $default_start;
                $data_date2 = $default_end_date;

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
                        $this->template->load('template', 'report/new_mhrd', $data);
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
		$this->template->load('template', 'report/new_mhrd',$data);
	return;
	}


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

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	/**
	 * Method for displaying print preview
	 * of all the schedules in one page.
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
	 */
	function printPreview_schedules($c = 1)
	{
 	        $this->load->library('session');
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');

		$this->counter = 1;
		if($c == 2){
	                $this->template->set('page_title', 'Print All Schedules');
        	        $this->template->set('nav_links', array('report/download/all_schedules' => 'Download CSV', 'report/printPreview_schedules/2' => 'Print Preview'));
			//$this->template->load('template', 'report/print_all_schedules');
		}
		elseif($c == 1){

		$check = 2;
		//$count = 0;
		$counter = 0;
		$data = array();
		$arr = array();
		$arr1 = array();
		$main = array();
                $id = '';
                $schedule = '';
                $name = '';

		while($check>0)
		{
			$this->init($check);
			foreach ($this->children_groups as $id => $row)
			{
				$count = 0;
				$this->id = $row['id'];
				$this->code = $row['code'];
				if(($this->countDigits($row['code']) == 4) && ($this->id != 0) && ($this->code > 100)  && ($this->code!= '1006') && ($this->code!= '1005') && ($this->code!= '1001')){
			
		/*			print_r($this->id);
					$this->db->from('groups')->where('parent_id',$this->id);
	        	        	$child_group_q = $this->db->get();
					$child = $child_group_q->row();
	//				print_r($child);
	        	        	$counter1 = 0;
					$children_sub_groups = array();
        			        foreach ($child_group_q->result() as $child)
			                {
						
                        			$children_sub_groups[$counter1]['id'] = $child->id;
			                        $children_sub_groups[$counter1]['name'] = $child->name;
                        			$children_sub_groups[$counter1]['code'] = $child->code;
						$counter1++;
						$count++;
			                }  */

				if($count == 0)
				{

					$data['code'] = $this->code;
				//	print_r("lll====>$this->code");
				        $group_details = $this->Group_model->get_schedule($this->code);
			        	foreach ($group_details as $id => $group)
			                {
			                	$data['id']  = $group['id'];
	                        		$data['name'] = $group['name'];
				        }
						$main['arr'][$counter] = $data;
                                                $counter++;
				}//if count

				else
				{
				foreach($children_sub_groups as $id => $child)
				{
					$data['code'] = $child['code'];
		                        $group_details = $this->Group_model->get_schedule($child['code']);
                		        foreach ($group_details as $id => $group)
                                	{
                                        	$data['id']  = $group['id'];
			                        $data['name'] = $group['name'];
                        		}
						$main['arr'][$counter] = $data;
			                        $counter++;
				}
				}
				
				}

				$this->db->from('groups')->where('parent_id',$this->id);
                                $child_group = $this->db->get();
                                $counter1 = 0;
                                $children_sub_groups = array();
                                foreach ($child_group->result() as  $child)
                                {
                                	$children_sub_groups[$counter1]['id'] = $child->id;
                                 	$children_sub_groups[$counter1]['name'] = $child->name;
                                  	$children_sub_groups[$counter1]['code'] = $child->code;
                                  	$counter1++;
                                       	$count++;
					if($child->code <= 100103)
				  	{
						if($count == 0)
						{
                                         		$data['code'] = $this->code;
                                         		$group_details = $this->Group_model->get_schedule($this->code);
                                         		foreach ($group_details as $id => $group)
                                         		{
                                         			$data['id']  = $group['id'];
                                         			$data['name'] = $group['name'];
                                         		}
                                                		$main['arr'][$counter1] = $data;
                                                		$counter++;
                                        	}//if count
                                        	else
						{
                                                foreach($children_sub_groups as $id => $child1)
                                                {
                                                        $data['code'] = $child1['code'];
                                                        $group_details = $this->Group_model->get_schedule($child1['code']);
                                                        foreach ($group_details as $id => $group)
                                                        {
                                                        	$data['id']  = $group['id'];
                                                                $data['name'] = $group['name'];
                                                        }
                                                        	$main['arr'][$counter1] = $data;
                                                        	$counter++;
                                                }
                                        	}//else

						}//if 
					}
						
				}//for

			$check--;
		}//while
		
	}//if

	if($c == 1)
	{
		$this->template->load('template', 'report/printPreview', $main);
		}elseif($c == 2)
		{
			$this->load->view('report/print_schedules', $main);
		}

                return;
	}
////////////////////////////////////////////////////////

	function init($i)
	{
		
                $this->db->from('groups');
                $this->db->select('name, code, status')->where('id', $i);
                $group_q = $this->db->get();
                $group = $group_q->row();
		$this->id = $i;
                $this->name = $group->name;
                $this->code = $group->code;
		if($group->status==0)
	                $this->sub_groups();
	}

	function sub_groups()
	{
		$this->db->from('groups')->where('parent_id', $this->id);
                $child_group_q = $this->db->get();
                $counter = 0;
                foreach ($child_group_q->result() as $row)
                {
                        $this->children_groups[$counter]['id'] = $row->id;
                        $this->children_groups[$counter]['name'] = $row->name;
                        $this->children_groups[$counter]['code'] = $row->code;
                        //$this->init($row->id);
                        $counter++;
                }
	}

	function countDigits($code=null)
        {
                //preg_match_all( "/[0-9]/", $str, $arr );
                $search = '1234567890';
		if($code != null)
			$count = strlen($code) - strlen(str_replace(str_split($search), '', $code));
		else
	                $count = strlen($this->code) - strlen(str_replace(str_split($search), '', $this->code));
                return $count;
        }

	/**
	 * Method for printing schedules
	 * for balancesheet MHRD format.
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
	 */
	// $code is chart of account code 
	// $count is schedule number
	function schedule($code, $count)
	{
		$this->template->set('schedule', 'true');
		//$design_earm_funds = array();
		$design_earm_funds_group = array();
		$design_earm_funds_ledger = array();
		$current_liabilities = array();
		$provisions = array();
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
                        //$schedule = $group['schedule'];
			$name = $group['name'];
		}
	
		if($name == 'Current Liabilities')
		{
                $name = 'Current Liabilities And Provisions';
		}
			
	
		if($name != '' && $id != ''){
			$this->template->set('page_title', 'Schedule - ' . $count . ' ' . $name);
	                $this->session->set_userdata('code', $code);
			$this->template->set('nav_links', array('report/download/schedule/'.$count => 'Download CSV', 'report/printpreview/schedule/'. $count => 'Print Preview'));
			$data['id'] = $id;
		}
		else{
			$this->template->set('page_title', 'Schedule - Notes on Accounts');
                        $this->template->set('nav_links', array('report/download/schedule' => 'Download CSV', 'report/printpreview/schedule' => 'Print Preview'));
		}

		$this->load->model('Setting_model');
		$ledger_name = $this->Setting_model->get_from_settings('ledger_name');
	//	if($name == 'General Funds' || $name == 'Reserves and Surplus'){
		if($name == $ledger_name){
			$this->template->load('template', 'report/schedule_template_1', $data);
                        return;
		}
		/*	elseif($name == 'General Reserve'){
			$this->template->load('template', 'report/schedule_template_9', $data);
			return;
			}  */
			elseif($name == 'Designated-Earmarked Funds'){
			//add child groups and ledgers for the fund
			$num_of_childs = $this->Group_model->get_numOfChild($id);
			$count = 0;

			if($num_of_childs > 0){
				//get child id, name, code
				$this->db->select('id, name, code');
				$this->db->from('groups')->where('parent_id', $id);
				$group_result = $this->db->get();

				foreach($group_result->result() as $row){
					$design_earm_funds_group[$count]['id'] = $row->id;
					$design_earm_funds_group[$count]['name'] = $row->name;
					$design_earm_funds_group[$count]['code'] = $row->code;
					$count++;
				}
			}

			$num_of_childs = $this->Ledger_model->get_numOfChild($id);
			
			if($num_of_childs > 0){
                                //get child id, name, code
       	                        $this->db->select('id, name, code');
               	                $this->db->from('ledgers')->where('group_id', $id);
                       	        $ledger_result = $this->db->get();

                                foreach($ledger_result->result() as $row){
       	                                $design_earm_funds_ledger[$count]['id']= $row->id;
					$design_earm_funds_ledger[$count]['name'] = $row->name;
					$design_earm_funds_ledger[$count]['code'] = $row->code;
               	                        $count++;
                       	        }
                       	}	


			//$data['designated_earmarked_funds'] = $design_earm_funds;
			$data['designated_earmarked_funds_group'] = $design_earm_funds_group;
			$data['designated_earmarked_funds_ledger'] = $design_earm_funds_ledger;
			
			$this->template->load('template', 'report/schedule_template_2', $data);
                        return;
		}elseif($name == 'Loan/Borrowings'){
			$this->template->load('template', 'report/schedule_template_3', $data);
                        return;
		     }	elseif($name == 'Current Liabilities And Provisions'){
			$count = 0;
			$this->db->select('id, name, code')->from('groups');
			$this->db->where('parent_id', $id);
			$query_result = $this->db->get();
			
			foreach($query_result->result() as $row){
			/*	if($row->name == 'Provisions'){
					$this->db->select('id, name, code')->from('groups');
					$this->db->where('parent_id', $row->id);
					$group_result = $this->db->get();
				
					$counter = 0;
					foreach($group_result->result() as $row1){
						$provisions[$counter]['id'] = $row1->id;
						$provisions[$counter]['name'] = $row1->name;
						$provisions[$counter]['code'] = $row1->code;
						$counter++;
					}
				}else{  */
					$current_liabilities[$count]['id'] = $row->id;
					$current_liabilities[$count]['name'] = $row->name;
					$current_liabilities[$count]['code'] = $row->code;
					$count++;
				}
				
				$this->db->select('id, name, code')->from('groups');
                        	$this->db->where('parent_id', 157);
                        	$query_result1 = $this->db->get();

				 	$counter = 0;
                                        foreach($query_result1->result() as $row1){
                                                $provisions[$counter]['id'] = $row1->id;
                                                $provisions[$counter]['name'] = $row1->name;
                                                $provisions[$counter]['code'] = $row1->code;
                                                $counter++;
						}

			$data['current_liabilities'] = $current_liabilities;
			$data['provisions'] = $provisions;
			$this->template->load('template', 'report/schedule_template_4', $data);
			return;
		}elseif($name == 'Fixed Assets'){
			$fixed_assets = array();
			$count = 0;

			$this->db->select('id')->from('groups');	
			$this->db->where('parent_id', $id);
			$group_query = $this->db->get();
			foreach($group_query->result() as $row){
				$this->db->select('id')->from('groups');
				$this->db->where('parent_id', $row->id);
				$child_group_query = $this->db->get();
				foreach($child_group_query->result() as $row1){
					$fixed_assets[$count]['id'] = $row1->id;
					$count++; 
				}

				$this->db->select('id,name')->from('ledgers');
                                $this->db->where('group_id', $row->id);
                                $child_ledger_query = $this->db->get();
                                foreach($child_ledger_query->result() as $row1){
                                        $fixed_assets[$count]['id'] = $row1->id;
                                        $count++;
                                }
			}
			
			$data['fixed_assets'] = $fixed_assets;			
			$this->template->load('template', 'report/schedule_template_5', $data);
                        return;

		}  elseif($name == 'Investments'){
                        $investments = array();
                        $count = 0;

                        $this->db->select('id')->from('groups');
                        $this->db->where('parent_id', $id);
                        $group_query = $this->db->get();
                        foreach($group_query->result() as $row){
                                $this->db->select('id')->from('groups');
                                $this->db->where('parent_id', $row->id);
                                $child_group_query = $this->db->get();
                                foreach($child_group_query->result() as $row1){
                                        $investments[$count]['id'] = $row1->id;
                                        $count++;
                                }

                                $this->db->select('id')->from('ledgers');
                                $this->db->where('group_id', $row->id);
                                $child_ledger_query = $this->db->get();
                                foreach($child_ledger_query->result() as $row1){
                                        $investments[$count]['id'] = $row1->id;
                                        $count++;
                                } 
                        }

                        $data['investments'] = $investments;
                        $this->template->load('template', 'report/schedule_template_8',$data);
                        return;


		} elseif($name == 'Current Assets'){
			$current_assets_group = array();
			$current_assets_ledger = array();
			$count = 0;

			$this->db->select('id');
			$this->db->from('groups')->where('parent_id', $id);
			$group_query = $this->db->get();
			foreach($group_query->result() as $row){
				$current_assets_group[$count]['id'] = $row->id;
				$count++;
			}
	
			$count = 0;
			$this->db->select('id');
			$this->db->from('ledgers')->where('group_id', $id);
			$ledger_query = $this->db->get();
			foreach($ledger_query->result() as $row1){
				$current_assets_ledger[$count]['id'] = $row1->id;
				$count++;
			}

			$data['current_assets_group'] = $current_assets_group;
			$data['current_assets_ledger'] = $current_assets_ledger;
			$this->template->load('template', 'report/schedule_template_6', $data);
			return;
		}elseif($name == 'Loans Advances and Deposits'){
			$loans_advances = array();
			$count = 0;

			$this->db->select('id');
                        $this->db->from('groups')->where('parent_id', $id);
                        $group_query = $this->db->get();
                        foreach($group_query->result() as $row){
                                $loans_advances[$count]['id'] = $row->id;
                                $count++;
                        }

			$data['loans_advances'] = $loans_advances;
			$this->template->load('template', 'report/schedule_template_7', $data);
			return;
		}
		else{
			$this->template->load('template', 'report/schedule_template', $data);
                        return;
		}			

		return;
	}

	function sub_schedule($ledger_id, $ledger_name){
		$this->template->set('schedule', 'true');
		$this->template->set('page_title', 'Schedule - ' . $ledger_name);
		$this->template->set('nav_links', array('report/download/schedule' => 'Download CSV', 'report/printpreview/schedule/' => 'Print Preview'));
                $data['id'] = $ledger_id;
		$this->load->model('Setting_model');
                $data['start_date'] = $this->Setting_model->get_from_settings('fy_start');
                $data['end_date'] = $this->Setting_model->get_from_settings('fy_end');
		$this->template->load('template', 'report/sub_schedule_template', $data);
                return;
	}

	function profitandloss($period = NULL)
	{
		$this->load->library('session');
		$this->template->set('page_title', 'Income And Expenditure Statement');
		$this->template->set('nav_links', array('report/download/profitandloss' => 'Download CSV', 'report/printpreview/profitandloss' => 'Print Preview', 'report/pdf/profitandloss' => 'Download PDF'));
		$data['left_width'] = "300";
		$data['right_width'] = "125";
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);

                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

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
		$this->template->set('nav_links', array('report/download/paymentreceipt' => 'Download CSV', 'report/printpreview/paymentreceipt' => 'Print Preview', 'report/pdf/paymentreceipt' => 'Download PDF'));
		$data['left_width'] = "300";
		$data['right_width'] = "125";
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);
                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

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
		$this->template->set('nav_links', array('report/download/trialbalance' => 'Download CSV', 'report/printpreview/trialbalance' => 'Print Preview', 'report/pdf/trialbalance' => 'Download PDF'));
		
		$data['width'] = "70%";
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);
		$data['date1'] = '';
		$data['date2'] = '';
		$data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

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

	function dayst(){
		$this->load->library('session');
                $this->load->helper('text');
                /* Pagination setup */
                $this->load->library('pagination');

                $this->template->set('page_title', 'Day Statement');
                $this->template->set('nav_links', array('report/printpreview/dayst/' => 'Print Preview','report/pdf/dayst/'=> 'Download PDF'));
                //$this->template->set('nav_links', array('report/download/dayst/'  => 'Download CSV', 'report/printpreview/dayst/' => 'Print Preview', 'report/pdf/dayst/' => 'Download PDF'));
                $data['width'] = "70%";
		$data['print_preview'] = FALSE;
		 $curr_date = date_today_php();
		 $data['entry_date1'] = array(
                        'name' => 'entry_date1',
                        'id' => 'entry_date1',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => $curr_date,
                );
                /* Repopulating form */

                if ($_POST)
                {
                        $data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
                }

                /* Form validations */

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');

		 /* Validating form */
                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'report/dayst', $data);
                        return;
                }
                else
                {
                        $data_date1 = $this->input->post('entry_date1', TRUE);

                        $date=explode("/",$data_date1);
                        $date1=$date[2]."-".$date[1]."-".$date[0];

                        $newdata = array(
                           'date1'  => $date1,
                        );
                        $this->session->set_userdata($newdata);
                        redirect('report/dayst/');
                }
                $this->template->load('template', 'report/dayst', $data);
	return;
	}
	function ledgerst($ledger_id = 0)
	{
		$this->load->library('session');
		$this->load->helper('text');
		/* Pagination setup */
		$this->load->library('pagination');

		$this->template->set('page_title', 'Ledger Statement');
		$this->template->set('nav_links', array('report/download/ledgerst/' . $ledger_id  => 'Download CSV', 'report/printpreview/ledgerst/' . $ledger_id => 'Print Preview', 'report/pdf/ledgerst/' . $ledger_id => 'Download PDF'));
		$data['width'] = "70%";

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
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);
		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

                $date=explode("/",$data_date1);
                $start_date=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $end_date=$date[2]."-".$date[1]."-".$date[0];

                $newrange = array(
                      'startdate'  => $start_date,
                      'enddate'  => $end_date
                     );
                $this->session->set_userdata($newrange);
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
				$this->template->set('nav_links', array('report/download/reconciliation/' . $ledger_id . '/all'  => 'Download CSV', 'report/printpreview/reconciliation/' . $ledger_id . '/all' => 'Print Preview','report/pdf/reconciliation/'. $ledger_id . '/all' => 'Download PDF'));
		} else if ($reconciliation_type == 'pending') {
			$data['reconciliation_type'] = 'pending';
			$data['show_all'] = FALSE;
			if ($ledger_id > 0)
				$this->template->set('nav_links', array('report/download/reconciliation/' . $ledger_id . '/pending'  => 'Download CSV', 'report/printpreview/reconciliation/' . $ledger_id . '/pending'  => 'Print Preview','report/pdf/reconciliation/'. $ledger_id . '/pending' => 'Download PDF'));
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
		if ($statement == "balancesheet" || $statement == "new_balancesheet" || $statement == "new_mhrd")
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
		$code = $this->session->userdata('code');
		$search = $this->session->userdata('search');
		$text = $this->session->userdata('text');
		$count = $id;
		/********************** TRIAL BALANCE *************************/
		if ($statement == "trialbalance")
		{
			$data['report'] = "report/trialbalance";
			$data['title'] = "Trial Balance";
			$data['width'] = "70%";
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
			$data['left_width'] = "300";
			$data['right_width'] = "125";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}
		
		  if ($statement == "new_mhrd")
  		  {
                        $curr_date = date_today_php();
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['page_count'] = $page_count;
                        $data['report'] = "report/new_mhrd";
                        $data['title'] = "Balance Sheet As At ".$date2;
                        $data['left_width'] = "300";
                        $data['right_width'] = "125";
                        $data['print_preview'] = TRUE;
                        $data['date'] = $curr_date;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/report_template', $data);
                        return;
                }//if
		if ($statement == "profitandloss")
		{
			$data['report'] = "report/profitandloss";
			$data['title'] = "Income And Expenditure Statement";
			$data['left_width'] = "300";
			$data['right_width'] = "125";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}

		if ($statement == "depreciation")
                {
			$field = $search . '      ' . 'LIKE';
                        $data['report'] = "report/depreciation";
                        $data['title'] = "Depreciation Of Assets";
                        $data['print_preview'] = TRUE;
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['search'] = $search;	
                        $data['text'] = $text;			
                        $data['field'] = $field;					
                        $this->load->view('report/report_template', $data);
			$this->session->unset_userdata('search');
			$this->session->unset_userdata('text');
                        return;
                }

		if ($statement == "paymentreceipt")
		{
			$data['report'] = "report/paymentreceipt";
			$data['title'] = "Payment & Receipt";
			$data['left_width'] = "300";
			$data['right_width'] = "125";
                        $data['print_preview'] = TRUE;
			$data['entry_date1'] = $date1;
			$data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
			return;
		}
		if($statement == "dayst")
                {
                 $this->load->helper('text');
                        $data['width'] = "70%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['page_count'] = $page_count;
                        $data['report'] = "report/dayst";
                        $data['print_preview'] = TRUE;
                        //$data['title'] = "Ledger Statement for the day :".$date1;
                        $data['title'] = "Day Statement";
                        $data['entry_date1'] = $date1;
                        $this->load->view('report/report_template', $data);
                        	$this->session->unset_userdata('date1');
                        return;
                }
		if($statement == "cashst")
                {
                 	$this->load->helper('text');
                        $data['width'] = "70%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['page_count'] = $page_count;
                        $data['report'] = "report/cashst";
                        $data['print_preview'] = TRUE;
                        $data['title'] = "Cash Statement";
			$data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/report_template', $data);
                        return;
                }

		if ($statement == "ledgerst")
		{
			$this->load->helper('text');
			$data['width'] = "70%";
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
			$this->session->unset_userdata('date1');
			$this->session->unset_userdata('date2');
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
			$curr_date = date_today_php();
                        $data['report'] = "report/new_balancesheet";
                        $data['title'] = "Balance Sheet As At ".$date2;
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['print_preview'] = TRUE;
			$data['date'] = $curr_date;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/report_template', $data);
                        return;
                }
	/*	if ($statement == "new_balancesheet_2015")
                {
                        $curr_date = date_today_php();
                        $data['report'] = "report/new_balancesheet_2015";
                        $data['title'] = "Balance Sheet As At ".$date2;
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['print_preview'] = TRUE;
                        $data['date'] = $curr_date;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/report_template', $data);
                        return;
                } */

///////////////////////////////////////////////////////////
		if ($statement == "schedule")
                {
			$arr = array();
			$design_earm_funds_group = array();
			$design_earm_funds_ledger = array();

	                $group_id = '';
        	        $title = '';
                	$name = '';
	                $arr['code'] = $code;

        	        $this->load->model('Group_model');
                	$group_details = $this->Group_model->get_schedule($code);
	                foreach ($group_details as $id => $group)
        	        {
                	        $id  = $group['id'];
	                        $name = $group['name'];
        	        }
			
			if($name == 'Current Liabilities')
	                {
        	        	$name = 'Current Liabilities And Provisions';
                	}



			 if($name != '' && $id != ''){
	                        $title =  'Schedule - ' . $count . ' ' . $name;
                        	$arr['id'] = $id;
				$arr['name'] = $group['name'];
				$arr['code'] = $code;
                	}
	                else{
        	                $title = 'Schedule - Notes on Accounts';
        	        }

			//if($name == 'General Funds' || $name == 'Reserves and Surplus')
			$this->load->model('Setting_model');
	                $ledger_name = $this->Setting_model->get_from_settings('ledger_name');
        	        if($name == $ledger_name)
				$data['report'] = "report/schedule_template_1";
				
			elseif($name == 'Designated-Earmarked Funds'){
                        //add child groups and ledgers for the fund
                        $num_of_childs = $this->Group_model->get_numOfChild($id);
                        $count = 0;

                        if($num_of_childs > 0){
                                //get child id, name, code
                                $this->db->select('id, name, code');
                                $this->db->from('groups')->where('parent_id', $id);
                                $group_result = $this->db->get();

                                foreach($group_result->result() as $row){
                                        $design_earm_funds_group[$count]['id'] = $row->id;
                                        $design_earm_funds_group[$count]['name'] = $row->name;
                                        $design_earm_funds_group[$count]['code'] = $row->code;
                                        $count++;
                                }
                        }

                        $num_of_childs = $this->Ledger_model->get_numOfChild($id);

                        if($num_of_childs > 0){
                                //get child id, name, code
                                $this->db->select('id, name, code');
                                $this->db->from('ledgers')->where('group_id', $id);
                                $ledger_result = $this->db->get();

                                foreach($ledger_result->result() as $row){
                                        $design_earm_funds_ledger[$count]['id']= $row->id;
                                        $design_earm_funds_ledger[$count]['name'] = $row->name;
                                        $design_earm_funds_ledger[$count]['code'] = $row->code;
                                        $count++;
                                }
                        }
				 $data['designated_earmarked_funds_group'] = $design_earm_funds_group;
                        	 $data['designated_earmarked_funds_ledger'] = $design_earm_funds_ledger;

				 $data['report'] = "report/schedule_template_2";
                        	 $data['title'] = $title;
                        	 $data['left_width'] = "";
                        	 $data['right_width'] = "";
                        	 $data['print_preview'] = TRUE;
                        	 $data['entry_date1'] = $date1;
                        	 $data['entry_date2'] = $date2;
                        	 $data['isSchedule'] = "true";
                        	 $data['arr'] = $arr;
                        	 $this->load->view('report/report_template', $data);
                        	 return;
                        	}

			elseif($name == 'Current Liabilities And Provisions'){
                        $count = 0;
                        $this->db->select('id, name, code')->from('groups');
                        $this->db->where('parent_id', $id);
                        $query_result = $this->db->get();

                        foreach($query_result->result() as $row){
                                        $current_liabilities[$count]['id'] = $row->id;
                                        $current_liabilities[$count]['name'] = $row->name;
                                        $current_liabilities[$count]['code'] = $row->code;
                                        $count++;
                                }

                                $this->db->select('id, name, code')->from('groups');
                                $this->db->where('parent_id', 157);
                                $query_result1 = $this->db->get();

                                        $counter = 0;
                                        foreach($query_result1->result() as $row1){
                                                $provisions[$counter]['id'] = $row1->id;
                                                $provisions[$counter]['name'] = $row1->name;
                                                $provisions[$counter]['code'] = $row1->code;
                                                $counter++;
                              	                }
				$data['current_liabilities'] = $current_liabilities;
                        	$data['provisions'] = $provisions;

				 $data['report'] = "report/schedule_template_4";
  	                      	 $data['title'] = $title;
        	                 $data['left_width'] = "";
                	         $data['right_width'] = "";
                        	 $data['print_preview'] = TRUE;
                      		 $data['entry_date1'] = $date1;
                       		 $data['entry_date2'] = $date2;
                       		 $data['isSchedule'] = "true";
                        	 $data['arr'] = $arr;
                        	 $this->load->view('report/report_template', $data);
                        	 return;
				}
				
			elseif($name == 'Investments')
			{
			$data['report'] = "report/schedule_template_8";
			}
			

			elseif($name == 'Loan/Borrowings')
			{
			$data['report'] = "report/schedule_template_3";
			}

			elseif($name == 'Fixed Assets')
			{
			$fixed_assets = array();
                        $count = 0;

                        $this->db->select('id')->from('groups');
                        $this->db->where('parent_id', $id);
                        $group_query = $this->db->get();
                        foreach($group_query->result() as $row){
                                $this->db->select('id')->from('groups');
                                $this->db->where('parent_id', $row->id);
                                $child_group_query = $this->db->get();
                                foreach($child_group_query->result() as $row1){
                                        $fixed_assets[$count]['id'] = $row1->id;
                                        $count++;
                                }

                                $this->db->select('id,name')->from('ledgers');
                                $this->db->where('group_id', $row->id);
                                $child_ledger_query = $this->db->get();
                                foreach($child_ledger_query->result() as $row1){
                                        $fixed_assets[$count]['id'] = $row1->id;
                                        $count++;
                                }

			}
			$data['fixed_assets'] = $fixed_assets;
//			print_r($data['fixed_assets']);
			$data['report'] = "report/schedule_template_5";

			$data['title'] = $title;
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $data['isSchedule'] = "true";
                        $data['arr'] = $arr;
			$this->load->view('report/report_template', $data);
                        return;
			} 
				
			elseif($name == 'Current Assets')	
			{
				$current_assets_group = array();
                        	$current_assets_ledger = array();
                        	$count = 0;

                        	$this->db->select('id');
                        	$this->db->from('groups')->where('parent_id', $id);
                        	$group_query = $this->db->get();
                        	foreach($group_query->result() as $row){
                                $current_assets_group[$count]['id'] = $row->id;
                                $count++;
                        	}	

                        	$count = 0;
                        	$this->db->select('id');
                        	$this->db->from('ledgers')->where('group_id', $id);
                        	$ledger_query = $this->db->get();
                        	foreach($ledger_query->result() as $row1){
                                $current_assets_ledger[$count]['id'] = $row1->id;
                                $count++;
                        	}

                        	$data['current_assets_group'] = $current_assets_group;
                        	$data['current_assets_ledger'] = $current_assets_ledger;

				$data['report'] = "report/schedule_template_6";
				$data['title'] = $title;
              	          	$data['left_width'] = "";
                        	$data['right_width'] = "";
                        	$data['print_preview'] = TRUE;
                        	$data['entry_date1'] = $date1;
                        	$data['entry_date2'] = $date2;
                        	$data['isSchedule'] = "true";
                        	$data['arr'] = $arr;
                        	$this->load->view('report/report_template', $data);
                        	return;
                        }
			
			elseif($name == 'Loans Advances and Deposits')
			{
                        	$loans_advances = array();
                        	$count = 0;

                        	$this->db->select('id');
                        	$this->db->from('groups')->where('parent_id', $id);
                        	$group_query = $this->db->get();
                        	foreach($group_query->result() as $row){
                                $loans_advances[$count]['id'] = $row->id;
                                $count++;
                        	}

                        	$data['loans_advances'] = $loans_advances;
				$data['report'] = "report/schedule_template_7";
	
        	                $data['title'] = $title;
                	        $data['left_width'] = "";
                        	$data['right_width'] = "";
                        	$data['print_preview'] = TRUE;
                        	$data['entry_date1'] = $date1;
                        	$data['entry_date2'] = $date2;
                        	$data['isSchedule'] = "true";
                        	$data['arr'] = $arr;
				$this->load->view('report/report_template', $data);
                        	return;
			}


/////////////////////////////////
			
			else
				$data['report'] = "report/schedule_template";

                        $data['title'] = $title;
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
			$data['isSchedule'] = "true";
			$data['arr'] = $arr;
			$data['designated_earmarked_funds_group'] = $design_earm_funds_group;
			$data['designated_earmarked_funds_ledger'] = $design_earm_funds_ledger;
                        $this->load->view('report/report_template', $data);
                        return;
                }

		return;
	}

	function cashst(){
		$this->load->library('session');
		/* Pagination setup */
                $this->load->library('pagination');
		$this->template->set('page_title', 'Cash Reports');
		$this->template->set('nav_links', array('report/printpreview/cashst/' => 'Print Preview','report/pdf/cashst/'=> 'Download PDF'));

		$data['width'] = "70%";
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
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
			'value' => $default_end_date,
		);

                $data['print_preview'] =FALSE;

		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

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
			$this->template->load('template', 'report/cashst', $data);
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
		$this->template->load('template', 'report/cashst', $data);
        	return;

	}

	function new_schedule($code,$count)
	{
		$this->template->set('schedule', 'true');
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
                        $name = $group['name'];
                }
		if($name == 'Corpus')
		$name = 'Corpus/Capital Funds';
		if($name != '' && $id != ''){
			if($name == 'Investments'){
			$this->template->set('page_title', 'Schedule - ' . $count . ' ' . 'Investments From Earmarked/Endowments Funds');	
			}
			elseif($name == 'Corpus Fund Investments'){
			$this->template->set('page_title', 'Schedule - ' . 6 . ' ' . 'Investments Others'); 
			}else{
                        $this->template->set('page_title', 'Schedule - ' . $count . ' ' . $name);
			}
                        $this->session->set_userdata('code', $code);
			$this->template->set('nav_links', array('report/printpreview1/new_schedule/'. $count => 'Print Preview'));
	
                        //$this->template->set('nav_links', array('report/download/schedule/'.$count => 'Download CSV', 'report/printpreview/schedule/'. $count => 'Print Preview'));
                        $data['id'] = $id;
                }
                else{
                        $this->template->set('page_title', 'Schedule - Notes on Accounts');
			$this->template->set('nav_links', array('report/printpreview1/schedule' => 'Print Preview'));
                        //$this->template->set('nav_links', array('report/download/schedule' => 'Download CSV', 'report/printpreview/schedule' => 'Print Preview'));
                }
		$data['print_preview'] = 'FALSE';
                if($name == 'Corpus/Capital Funds'){
                $this->template->load('template', 'new_report/schedule_template_1', $data);
                return;
                }
		elseif($name == 'Designated-Earmarked/Endowment Funds')
		{
		 //add child groups and ledgers for the fund
                        $num_of_childs = $this->Group_model->get_numOfChild($id);
                        $count = 0;

                        if($num_of_childs > 0){
                                //get child id, name, code
                                $this->db->select('id, name, code');
                                $this->db->from('groups')->where('parent_id', $id);
                                $group_result = $this->db->get();

                                foreach($group_result->result() as $row){
                                        $design_earm_funds_group[$count]['id'] = $row->id;
                                        $design_earm_funds_group[$count]['name'] = $row->name;
                                        $design_earm_funds_group[$count]['code'] = $row->code;
                                        $count++;
                                }
                        }

                        $num_of_childs = $this->Ledger_model->get_numOfChild($id);

                        if($num_of_childs > 0){
                                //get child id, name, code
                                $this->db->select('id, name, code');
                                $this->db->from('ledgers')->where('group_id', $id);
                                $ledger_result = $this->db->get();

                                foreach($ledger_result->result() as $row){
                                        $design_earm_funds_ledger[$count]['id']= $row->id;
                                        $design_earm_funds_ledger[$count]['name'] = $row->name;
                                        $design_earm_funds_ledger[$count]['code'] = $row->code;
					 $count++;
                                }
                        }


                        //$data['designated_earmarked_funds'] = $design_earm_funds;
                        $data['designated_earmarked_funds_group'] = $design_earm_funds_group;
                        $data['designated_earmarked_funds_ledger'] = $design_earm_funds_ledger;
			$this->template->load('template', 'new_report/schedule_template_2', $data);
		}elseif($name == 'Current Liabilities & Provisions')
		{
		$this->template->load('template', 'new_report/schedule_template_3', $data);
		}elseif($name == 'Fixed Assets')
		{
		$this->template->load('template', 'new_report/schedule_template_4', $data);
		}  
		elseif($name == 'Investments')
		{
		$this->template->load('template', 'new_report/schedule_template_5', $data);
		}
		elseif($name == 'Corpus Fund Investments')
		{
		$this->template->load('template', 'new_report/schedule_template_6', $data);
		} 
		elseif($name == 'Current Assets')
		{
		$this->template->load('template', 'new_report/schedule_template_7', $data);
		}elseif($name == 'Loans Advances and Deposits')
		{
		$this->template->load('template', 'new_report/schedule_template_8', $data);
		}
		return;
	}

	function new_sub_schedule($ledger_id, $ledger_name)
	{
                $this->template->set('schedule', 'true');
               // $this->template->set('page_title', 'ANNEXURE A');
                $data['id'] = $ledger_id;
		if($ledger_name == 'Cash in Hand')
		{
		$this->template->set('page_title', 'ANNEXURE A');
                $this->template->load('template', 'new_report/sub_schedule_7', $data);
		return;
		}elseif($ledger_name == 'Others Fixed Assets'){
		$this->template->set('page_title', '<b>Schedule - ' . '4D' . ' ' . 'OTHERS</b>');
		$this->template->load('template', 'new_report/sub_schedule_4D', $data);
		return;
		}elseif($ledger_name == 'Intangible Assets'){
		$this->template->set('page_title', '<u><b>Schedule - ' . '4C' . ' ' . 'INTANGIBLE ASSETS</b></u>');
		$this->template->load('template', 'new_report/sub_schedule_4C', $data);
		return;
		}elseif($ledger_name == 'Recipts Against Sponsored Projects'){
		$this->template->set('page_title', '<u><b>Schedule - ' . '3(a)' . ' ' . 'SPONSORED PROJECTS</b></u>');
                $this->template->load('template', 'new_report/sub_schedule_3a', $data);
		return;
		}
		elseif($ledger_name == 'UGC Sponsored Fellowship'){
                $this->template->set('page_title', '<u><b>Schedule - ' . '3(b)' . ' ' . 'SPONSORED FELLOWSHIPS AND SCHOLARSHIPS</b></u>');
                $this->template->load('template', 'new_report/sub_schedule_3b', $data);
                return;
		} 
		elseif($ledger_name == 'Unutilized Grants'){
                $this->template->set('page_title', '<u><b>Schedule - ' . '3(c)' . ' ' . 'UNUTILISED GRANTS FROM UGC, GOVERNMENT OF INDIA AND STATE GOVERNMENTS</b></u>');
                $this->template->load('template', 'new_report/sub_schedule_3c', $data);
                return;
                }elseif($ledger_name == 'Corpus Fund Investments'){
                $this->template->set('page_title', '<u><b>Schedule - ' . '5(A)' . ' ' . 'INVESTMENTS FROM EARMARKED/ENDOWMENT FUNDS (FUND WISE)</b></u>');
                $this->template->load('template', 'new_report/sub_schedule_5', $data);
                return;
                }

		return;
        } 
	
	//made by @kanchan
        function printpreview1($statement, $id = NULL)
        {
                $this->load->library('session');
                $this->load->model('Tag_model');
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');
                $code = $this->session->userdata('code');
                $count = $id;

                if ($statement == "new_schedule")
                {
                        $arr = array();
                        $arr['code'] = $code;
                        //print_r($code);

                        $this->load->model('Group_model');
                        $group_details = $this->Group_model->get_schedule($code);
                        //print_r($group_details);
                        foreach ($group_details as $id => $group)
                        {
                                $id  = $group['id'];
                                $name = $group['name'];
                        }
			if($name == 'Corpus')
	                $name = 'Corpus/Capital Funds';
			if($name == 'Corpus Fund Investments')
			$name = 'Investments Others';
			if($name == 'Investments')
			$name = 'Investments From Earmarked/Endowments Funds';

                        if($name != '' && $id != ''){
                        $title =  'Schedule - ' . $count . ' ' . $name;
                        $arr['id'] = $id;
                        $arr['name'] = $group['name'];
                        $arr['code'] = $code;
                        $arr['count'] = $count;
                        }
                        else{
                        $title = 'Schedule - Notes on Accounts';
                        }
			echo "name===$name";
                        if($count == 1)
                        $data['report'] = "new_report/schedule_template_1";
                        elseif($count == 3)
			 $data['report'] = "new_report/schedule_template_3";
                        elseif($count == 4)
                        $data['report'] = "new_report/schedule_template_4";
                        elseif($count == 5)
                        $data['report'] = "new_report/schedule_template_5";
                        elseif($count == 6)
                        $data['report'] = "new_report/schedule_template_6";
                        elseif($count == 7)
                        $data['report'] = "new_report/schedule_template_7";
                        elseif($count == 8)
                        $data['report'] = "new_report/schedule_template_8";
                        $data['title'] = $title;
                        $data['left_width'] = "";
                        $data['right_width'] = "";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $data['isSchedule'] = "true";
                        $data['arr'] = $arr;
                        $this->load->view('report/report_template', $data);
                        return;


                        //echo "name=====$name";
                }//if
        }
	
	


/*	function inner_sub_schedule($ledger_id,$ledger_name)
	{
		echo "kanchan===$ledger_id====$ledger_name";
		$this->template->set('schedule', 'true');
		$this->template->set('page_title', '<b>Schedule - ' . '4C(i)' . ' ' . 'PATENTS AND COPYRIGHTS</b>');
                $data['id'] = $ledger_id;

		if($ledger_name == 'Patents and Copyrights(Patents Granted)')
		//{
                //$this->template->set('page_title', '<b>Schedule - ' . '4C(i)' . ' ' . 'PATENTS AND COPYRIGHTS</b>');
                $this->template->load('template', 'new_report/sub_schedule_4Ci', $data);
                //return;
		//}
	return;

	} */
}//main

/* End of file report.php */
/* Location: ./system/application/controllers/report.php */
