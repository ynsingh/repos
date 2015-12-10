<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	setlocale(LC_MONETARY, 'en_IN');

	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
	$start_date = $this->session->userdata('startdate');
	$end_date = $this->session->userdata('enddate');
	$this->load->model('Ledger_model');
	$this->load->model('Secunit_model');

	$from_date = '';
	$to_date = '';
	if ( ! $print_preview)
	{
		echo form_open('report2/sec_report/' . $sec_uni_id);
		echo "<p>";
		echo "<span id=\"tooltip-target-1\">";
		echo form_label('From', 'entry_date1');
		echo " ";
		echo form_input_date_restrict($entry_date1);
		echo "</span>";
		echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "<span id=\"tooltip-target-2\">";
		echo form_label('To ', 'entry_date2');
		echo " ";
		echo form_input_date_restrict($entry_date2);
		echo "</span>";
		echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_label('Party Name', 'sec_uni_id');
                echo " ";
		echo "&nbsp;&nbsp;&nbsp;";
		echo form_dropdown_secunit('sec_uni_id', $sec_uni_id);
		echo " ";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_submit('submit', 'Show');
		echo "</p>";
		echo form_close();
	}
		if($date1 == '' && $date2 == '')
		{
			$from_date = $start_date;
			$to_date = $end_date;
		}
		else {
			$from_date = $date1;
			$to_date = $date2;
		}	
	/* Pagination configuration */
	if ( ! $print_preview)
	{		
		$this->load->library('pagination');
		$page_count = (int)$this->uri->segment(4);
		$page_count = $this->input->xss_clean($page_count);
		if ( ! $page_count)
			$page_count = "0";
		$config['base_url'] = site_url('report2/sec_report/' . $sec_uni_id);
		$pagination_counter = $this->config->item('row_count');
		$config['num_links'] = 10;
		$config['per_page'] = $pagination_counter;
		$config['uri_segment'] = 4;
		$config['total_rows'] = (int)$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.secunitid', $sec_uni_id)->where('date >=', $from_date)->where('date <=', $to_date)->count_all_results();
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

	if($sec_uni_id != 0)
	{
		list ($opbalance, $optype) = $this->Secunit_model->get_sec_opbal($sec_uni_id); /* Opening Balance */
		$clbalance = $this->Secunit_model->get_secop_balance1($sec_uni_id); /* Final Closing Balance */

		/* Secondary unit Summary */
		echo "<table class=\"ledger-summary\">";
		echo "<tr>";
		echo "<td><b>Opening Balance</b>&nbsp;&nbsp;&nbsp;" . convert_opening($opbalance, $optype) . "</td>";
		echo "</tr>";
		echo "<tr>";
		echo "<td><b>Closing Balance</b>&nbsp;&nbsp;&nbsp;&nbsp;" . convert_amount_dc($clbalance) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "<br />";

		echo "<table border=0 cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";

        echo "<thead><tr><th>Date</th><th>No.</th><th>Ledger Name</th><th>Type</th><th>Dr Amount</th><th>Cr Amount</th><th>Balance</th></tr></thead>";
        $odd_even = "odd";
		$cur_balance = 0;

		if ($page_count <= 0)
		{
			/* Opening balance */
			if ($optype == "D")
			{
				echo "<tr class=\"tr-balance\"><td colspan=\"6\">Opening Balance</td><td>" . convert_opening($opbalance, $optype) . "</td></tr>";
				$cur_balance = float_ops($cur_balance, $opbalance, '+');
			} else {
				echo "<tr class=\"tr-balance\"><td colspan=\"6\">Opening Balance</td><td>" . convert_opening($opbalance, $optype) . "</td></tr>";
				$cur_balance = float_ops($cur_balance, $opbalance, '-');
			}
		} else {
			/* Opening balance */
			if ($optype == "D")
			{
				$cur_balance = float_ops($cur_balance, $opbalance, '+');
			} else {
				$cur_balance = float_ops($cur_balance, $opbalance, '-');
			}
			/* Calculating previous balance */

			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.entry_type as entries_entry_type, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc');
			$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.secunitid', $sec_uni_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc')->limit($page_count, 0);
			$this->db->where('date >=', $from_date);
			$this->db->where('date <=', $to_date);		
			$prevbal_q = $this->db->get();

			foreach ($prevbal_q->result() as $row )
			{
				if ($row->entry_items_dc == "D")
					$cur_balance = float_ops($cur_balance, $row->entry_items_amount, '+');
				else
					$cur_balance = float_ops($cur_balance, $row->entry_items_amount, '-');
			}
			/* Show new current total */
			echo "<tr class=\"tr-balance\"><td colspan=\"6\">Opening</td><td>" . convert_amount_dc($cur_balance) . "</td></tr>";
		}


        if( $from_date > $to_date )
		{
			$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
			redirect('report2/sec_report');
            return;
		}
		else if ( ! $print_preview){
			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.ledger_id as entry_itmes_ledger');
			$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.secunitid', $sec_uni_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
			$this->db->where('date >=', $from_date);
			$this->db->where('date <=', $to_date);
			$this->db->limit($pagination_counter, $page_count);		
			$ledgerst_q = $this->db->get();
			if( $ledgerst_q->num_rows() < 1 )
				{
					$this->messages->add('There is no ledger statement between ' . $from_date . ' and ' . $to_date . ' date.', 'success');
				}
		}
		else {
			$page_count = 0;
			$this->db->select('entries.id as entries_id, entries.number as entries_number, entries.date as entries_date, entries.narration as entries_narration, entries.entry_type as entries_entry_type, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, entry_items.ledger_id as entry_itmes_ledger');
			$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.secunitid', $sec_uni_id)->order_by('entries.date', 'asc')->order_by('entries.number', 'asc');
			$this->db->where('date >=', $from_date);
			$this->db->where('date <=', $to_date);
			$ledgerst_q = $this->db->get();
			//$ledgerst_q1 = $ledgerst_q->result();
		//	print_r($ledgerst_q1);
		}

		foreach ($ledgerst_q->result() as $row)
		{
			$current_entry_type = entry_type_info($row->entries_entry_type);
			$ledger = $row->entry_itmes_ledger;
			$ledger_name = $this->Ledger_model->get_name($ledger);
			echo "<tr class=\"tr-" . $odd_even . "\">";
			echo "<td>";
				echo date_mysql_to_php_display($row->entries_date);
			echo "</td>";
			echo "<td>";
				echo anchor('entry/view/' . $current_entry_type['label'] . '/' . $row->entries_id, full_entry_number($row->entries_entry_type, $row->entries_number), array('title' => 'View ' . ' Entry', 'class' => 'anchor-link-a'));
			echo "</td>";

			/* Getting opposite Ledger name */
			echo "<td>";
				//echo $this->Ledger_model->get_opp_ledger_name($row->entries_id, $current_entry_type['label'], $row->entry_items_dc, 'html');
				//echo $ledger_name;
				echo anchor('entry/view/' . $current_entry_type['label'] . '/' . $row->entries_id, $ledger_name, array('title' => 'View ' . ' Entry', 'class' => 'anchor-link-a'));
			if ($row->entries_narration)
				echo "<div>" . character_limiter($row->entries_narration, 50) . "</div>";
			echo "</td>";

			echo "<td>";
				echo $current_entry_type['name'];
			echo "</td>";
				if ($row->entry_items_dc == "D")
				{
					$cur_balance = float_ops($cur_balance, $row->entry_items_amount, '+');
					echo "<td>";
						echo convert_dc($row->entry_items_dc);
						echo " ";
						echo money_format('%!i', $row->entry_items_amount);
					echo "</td>";
					echo "<td></td>";
				} else {
					$cur_balance = float_ops($cur_balance, $row->entry_items_amount, '-');
					echo "<td></td>";
					echo "<td>";
						echo convert_dc($row->entry_items_dc);
						echo " ";
						echo money_format('%!i', $row->entry_items_amount);
					echo "</td>";
				}
			echo "<td>";
				//echo convert_amount_dc($cur_balance);
			echo "</td>";
			echo "</tr>";
			$odd_even = ($odd_even == "odd") ? "even" : "odd";
		}
		/* Current Page Closing Balance */
		echo "<tr class=\"tr-balance\"><td colspan=\"6\">Closing</td><td>" .  convert_amount_dc($cur_balance) . "</td></tr>";
		echo "</table>";
		if (!$print_preview){
		echo "<br>";
		echo form_open('report2/printpreview/sec_report/' . $sec_uni_id);
		echo form_submit('submit', 'Print Preview');
		echo form_close();
		}	
	}else{
		$this->messages->add('Please select the Party Name.', 'success');
	}
	/*if ($sec_uni_id != 0)
	{
		if($from_date > $to_date)
    	{
            $this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
    	}

		$current_enty_type=array();
		$this->db->select('id,entry_type,date,number,dr_total,cr_total')->from('entries');
		$this->db->where('date >=', $from_date);
	    $this->db->where('date <=', $to_date);          
	    $prevbal_q = $this->db->get();
	    $q = $prevbal_q->result();
	    echo "$sec_uni_id";
	    print_r($q);
		if ($prevbal_q->num_rows() < 1)
        {
        	$this->messages->add('No entry has been done with this Secondary unit name between ' . $from_date .' & ' .$to_date . ' date.', 'success');
        }
        if ($prevbal_q->num_rows() != 0)
        {
			echo "<br></br>";
	        echo "<table border=0 cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";

	        echo "<thead><tr><th>Date</th><th>No.</th><th>Ledger Name</th><th>Type</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>";
			foreach($prevbal_q->result() as $row)
			{

				$ledger_name = $this->Secunit_model->get_sec_unit_report($row->id, $row->entry_type, $sec_uni_id);
				if($ledger_name != "(Invalid)")
				{
					$current_entry_type = entry_type_info($row->entry_type);
					echo "<tr>";
					echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
					echo "<td>$row->number</td>";
					echo "<td>$ledger_name</td>";
					echo "<td>". $current_entry_type['name']."</td>";
					echo "<td>$row->dr_total</td>";
					echo "<td>$row->cr_total</td>";
					echo "</tr>";
				}
			}
            echo "</table>";
			if (!$print_preview){
                echo "<br>";
                echo form_open('report2/printpreview/sec_report/' . $sec_uni_id);
                echo form_submit('submit', 'Print Preview');
                echo form_close();
            }
		}
	}
	else{
        	$this->messages->add('Please select the Secondary Unit Name.', 'success');
    }*/


?>
<?php if (!$print_preview && $sec_uni_id != 0) { ?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
<?php } ?>
