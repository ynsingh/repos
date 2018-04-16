<?php
	$this->load->model('Ledger_model');
	$this->load->model('Payment_model');
	$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
	setlocale(LC_MONETARY, 'en_IN');
	@$val=$value;
	$num_rows = 0;

	if ( ! $print_preview)
        {
                echo form_open('report/reconciliation/' . $reconciliation_type . '/' . $ledger_id);
                echo "<p>";
                echo "<span id=\"tooltip-target-1\">";
                echo form_label('From Date', 'entry_date1');
                echo " ";
                echo form_input_date_restrict($entry_date1);
                echo "</span>";
                echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
                echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                echo "<span id=\"tooltip-target-2\">";
                echo form_label('To Date', 'entry_date2');
                echo " ";
                echo form_input_date_restrict($entry_date2);
                echo "</span>";
                echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
                echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                echo"OR";
                echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                echo form_label('Cheque No', 'text');
                echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                echo form_input($text);
		echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                echo form_input_ledger('ledger_id', $ledger_id, '', $type = 'reconciliation');
                echo "</p>";
                echo "<p>";
                echo form_checkbox('show_all', 1, $show_all) . " Show All Entries";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                echo form_submit('submit', 'Get');

 		echo "</p>";
                echo form_close();
        }



	/* Pagination configuration */
	if ( ! $print_preview)
	{
//$config['total_rows'] = 200;
//$config['per_page'] = 20;
		$pagination_counter = $this->config->item('row_count');
		$page_count = (int)$this->uri->segment(5);
		//$page_count = $this->input->xss_clean($page_count);
                 $page_count = $this->security->xss_clean($page_count);
		if ( ! $page_count)
			$page_count = "0";
		$config['base_url'] = site_url('report/reconciliation/' . $reconciliation_type . '/' . $ledger_id);
		$config['num_links'] = 10;
		$config['per_page'] = $pagination_counter;
		$config['uri_segment'] = 5;
		if ($reconciliation_type == 'all'){
//			echo " IN all";
			$config['total_rows'] = $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('date >=', $date1)->where('date <=', $date2)->count_all();}
		else if($ledger_id !=0){
//			echo "not null ldis";
			$config['total_rows'] = $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.reconciliation_date', NULL)->where('date >=', $date1)->where('date <=', $date2)->count_all();}
		else{
//			echo "ld is 0";
			$result =  $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.reconciliation_date', NULL)->where('date >=', $date1)->where('date <=', $date2);
//			print_r($result);
			$config['total_rows'] = $this->db->count_all();}
//		echo $config['total_rows'];echo "I"; die;
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
		$this->pagination->initialize($config);

	}
	if ($ledger_id != 0)
	{
		list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($ledger_id); /* Opening Balance */
		$clbalance = $this->Ledger_model->get_ledger_balance($ledger_id); /* Final Closing Balance */

		/* Reconciliation Balance - Dr */
		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D')->where('entry_items.reconciliation_date IS NOT NULL');
		$this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);

		$dr_total_q = $this->db->get();
		if ($dr_total = $dr_total_q->row())
			$reconciliation_dr_total = $dr_total->drtotal;
		else
			$reconciliation_dr_total = 0;

		/* Reconciliation Balance - Cr */
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C')->where('entry_items.reconciliation_date IS NOT NULL');
		$this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);

		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
			$reconciliation_cr_total = $cr_total->crtotal;
		else
			$reconciliation_cr_total = 0;

		$reconciliation_total = float_ops($reconciliation_dr_total, $reconciliation_cr_total, '-');
		$reconciliation_pending = float_ops($clbalance, $reconciliation_total, '-');

		/* Ledger and Reconciliation Summary */
		echo "<table class=\"reconciliation-summary\">";
		echo "<tr>";
		echo "<td><b>Opening Balance</b></td><td>" . convert_opening($opbalance, $optype) . "</td>";
		echo "<td width=\"20px\"></td>";
		echo "<td><b>Reconciliation Pending</b></td><td>" . convert_amount_dc($reconciliation_pending) . "</td>";
		echo "</tr>";
		echo "<tr>";
		echo "<td><b>Closing Balance</b></td><td>" . convert_amount_dc($clbalance) . "</td>";
		echo "<td width=\"20px\"></td>";
		echo "<td><b>Reconciliation Total</b></td><td>" . convert_amount_dc($reconciliation_total) . "</td>";
		echo "</tr>";
		echo "</table>";

		echo "<br />";
		if($val == NULL){
		if ( ! $print_preview)
		{
			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.id as entry_items_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, entry_items.reconciliation_date as entry_items_reconciliation_date');
			if ($reconciliation_type == 'all'){
				$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc')->limit($pagination_counter, $page_count);
				$this->db->where('date >=', $date1);
                        	$this->db->where('date <=', $date2);

			}else{
				$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.reconciliation_date', NULL)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc')->limit($pagination_counter, $page_count);
				$this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
			}
				$ledgerst_q = $this->db->get();
				$num_rows=$ledgerst_q->num_rows();
                                if($num_rows==0){
                                        $this->messages->add('Entry not Found between dates.', 'error');
                                        redirect('report/reconciliation/' . $reconciliation_type);
                                        return;
                                }

		}else{
			$page_count = 0;
			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.id as entry_items_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, entry_items.reconciliation_date as entry_items_reconciliation_date');
			if ($reconciliation_type == 'all'){
				$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
				$this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
			}else{
				$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.reconciliation_date', NULL)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
				$this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
			}
				$ledgerst_q = $this->db->get();
		}
		}else{
		if ( ! $print_preview)
                {
                        $this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.id as entry_items_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, entry_items.reconciliation_date as entry_items_reconciliation_date');
                        if ($reconciliation_type == 'all'){
                                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('cheque_print', 'entries.id = cheque_print.entry_no')->where('entry_items.ledger_id', $ledger_id)->where('cheque_print.update_cheque_no LIKE','%'.$val.'%')->order_by('entries.date', 'asc')->order_by('entries.number', 'asc')->limit($pagination_counter, $page_count);

                        }else{
                                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('cheque_print', 'entries.id = cheque_print.entry_no')->where('entry_items.ledger_id', $ledger_id)->where('cheque_print.update_cheque_no LIKE', '%'.$val.'%')->where('entry_items.reconciliation_date', NULL)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc')->limit($pagination_counter, $page_count);
                        }
                                $ledgerst_q = $this->db->get();
				$num_rows=$ledgerst_q->num_rows();
                		if($num_rows==0){
                			$this->messages->add('Either Cheque no invalid or Entry not found.', 'error');
                			redirect('report/reconciliation/' . $reconciliation_type);
                			return;
                		}

                }else{
                        $page_count = 0;
                        $this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.id as entry_items_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, entry_items.reconciliation_date as entry_items_reconciliation_date');
                        if ($reconciliation_type == 'all'){
                                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('cheque_print', 'entries.id = cheque_print.entry_no')->where('entry_items.ledger_id', $ledger_id)->where('cheque_print.update_cheque_no LIKE', '%' . $val . '%')->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
                                $this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
                        }else{
                                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('cheque_print', 'entries.id = cheque_print.entry_no')->where('entry_items.ledger_id', $ledger_id)->where('cheque_print.update_cheque_no LIKE', '%' . $val . '%')->where('entry_items.reconciliation_date', NULL)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
                                $this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
                        }
                                $ledgerst_q = $this->db->get();
                }

		}	

		if ( ! $print_preview)
		{
			echo form_open('report/reconciliation/' . $reconciliation_type . '/' . $ledger_id . "/" . $page_count);
		}
		echo "<table border=0 cellpadding=5 class=\"simple-table reconciliation-table\">";

		echo "<thead><tr><th>Date</th><th>No.</th><th>Ledger Name</th><th>Type</th><th>Cheque No</th><th>Dr Amount</th><th>Cr Amount</th><th>Reconciliation Date</th></tr></thead>";
		$odd_even = "odd";
		foreach ($ledgerst_q->result() as $row)
		{
			$current_entry_type = entry_type_info($row->entries_entry_type);

			echo "<tr class=\"tr-" . $odd_even;
			if ($row->entry_items_reconciliation_date)
				echo " tr-reconciled";
			echo "\">";
			echo "<td>";
			echo date_mysql_to_php_display($row->entries_date);
			echo "</td>";
			echo "<td>";
			echo anchor('entry/view/' . $current_entry_type['label'] . '/' . $row->entries_id, full_entry_number($row->entries_entry_type, $row->entries_number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			echo "</td>";

			/* Getting opposite Ledger name */
			echo "<td>";
			echo $this->Ledger_model->get_opp_ledger_name($row->entries_id, $current_entry_type['label'], $row->entry_items_dc, 'html');
			$cheque_no=$this->Payment_model->get_cheque_no($row->entries_id);
			if($cheque_no == 1)
				$cheque_no=NULL;
			
			if ($row->entries_narration)
				echo "<div>" . character_limiter($row->entries_narration, 50) . "</div>";
			echo "</td>";

			echo "<td>";
			echo $current_entry_type['name'];
			echo "</td>";
			echo "<td>";
                        echo "$cheque_no";
                        echo "</td>";

			if ($row->entry_items_dc == "D")
			{
				echo "<td>";
				echo convert_dc($row->entry_items_dc);
				echo " ";
				echo money_format('%!i', $row->entry_items_amount);
				echo "</td>";
				echo "<td></td>";
			} else {
				echo "<td></td>";
				echo "<td>";
				echo convert_dc($row->entry_items_dc);
				echo " ";
				echo money_format('%!i', $row->entry_items_amount);
				echo "</td>";
			}

			echo "<td>";
			if ( ! $print_preview)
			{
				$reconciliation_date = array(
					'name' => 'reconciliation_date[' . $row->entry_items_id . ']',
					'id' => 'reconciliation_date',
					'maxlength' => '11',
					'size' => '11',
					'value' => '',
				);
				if ($row->entry_items_reconciliation_date)
					$reconciliation_date['value'] = date_mysql_to_php($row->entry_items_reconciliation_date);
				echo form_input_date_restrict($reconciliation_date);
			} else {
				if ($row->entry_items_reconciliation_date)
					echo date_mysql_to_php($row->entry_items_reconciliation_date);
				else
					echo "-";
			}
			echo "</td>";
			echo "</tr>";
			$odd_even = ($odd_even == "odd") ? "even" : "odd";
		}

		echo "</table>";
		if ( ! $print_preview)
		{
			echo "<p>";
			echo form_submit('submit', 'Update');
			echo "</p>";
			echo form_close();
		}
	}
?>
<?php if ( ! $print_preview && $val == NULL) { ?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
<?php } ?>
