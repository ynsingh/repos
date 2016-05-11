<?php
	setlocale(LC_MONETARY, 'en_IN');

	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	//$start_date = $this->session->userdata('startdate');
	//$this->load->model('Ledger_model');
	if ( ! $print_preview)
	{
		echo form_open('report/dayst/');
		echo "<p>";
		echo "<span id=\"tooltip-target-1\">";
		echo form_label('Date', 'entry_date1');
		echo " ";
		echo form_input_date_restrict($entry_date1);
		echo "</span>";
		echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_submit('submit', 'Show');
		echo "</p>";
		echo form_close();
	}
	
	/* Pagination configuration */
	if ( ! $print_preview)
	{		
		$this->load->library('pagination');
		$page_count = (int)$this->uri->segment(4);
		$page_count = $this->input->xss_clean($page_count);
		if ( ! $page_count)
			$page_count = "0";
		$config['base_url'] = site_url('report/dayst/all');
		$pagination_counter = $this->config->item('row_count');
		$config['num_links'] = 10;
		$config['per_page'] = $pagination_counter;
		$config['uri_segment'] = 4;
		$config['total_rows'] = (int)$this->db->from('entry_items')->join('ledgers', 'ledgers.id = entry_items.entry_id')->where('entry_items.update_date', $date1)->count_all_results();
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
	//}
		echo "<table border=\"0\" cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";
		$odd_even = "odd";

		//if ( ! $print_preview){
		$this->db->select('entry_items.ledger_id as entry_items_ledger_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, ledgers.id as ledgers_id, ledgers.code as ledgers_code, ledgers.name as ledgers_name');
		$this->db->from('entry_items')->join('ledgers', 'ledgers.id = entry_items.ledger_id');
		$this->db->where('entry_items.update_date', $date1);
		//$this->db->not_like('ledgers.code', '10','after');
		//$this->db->not_like('ledgers.code','20', 'after');
		//$this->db->order_by('entry_items.id', 'asc');
		$this->db->limit($pagination_counter, $page_count);		
		$dayst_q = $this->db->get();
		if( $dayst_q->num_rows() < 1 )
		{
			$this->messages->add('There is no ledger entry on ' . $date1 . ' date.', 'success');
		}
		else 
		{
			echo "<thead><tr><th colspan=\"5\" align=\"center\"> The ledger entry on date ".$date1." </th></tr></thead>";
			echo "<thead><tr><th>Sr. No.</th><th>Ledger Name</th><th>Type</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>";

		}
		//}
		/*else 
		{
			$page_count = 0;
			$this->db->select('entry_items.ledger_id as entry_items_ledger_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, ledgers.id as ledgers_id, ledgers.code as ledgers_code, ledgers.name as ledgers_name');
			$this->db->from('entry_items')->join('ledgers', 'ledgers.id = entry_items.ledger_id');
                        $this->db->where('entry_items.update_date', $date1);
                        $this->db->not_like('ledgers.code', '10','after');
                        $this->db->not_like('ledgers.code','20', 'after');
                        //$this->db->order_by('entry_items.id', 'asc');
                        $this->db->limit($pagination_counter, $page_count);
			$dayst_q = $this->db->get();
			echo "<thead><tr><th colspan=\"5\" align=\"center\"> The ledger entry on date ".$date1." </th></tr></thead>";
			echo "<thead><tr><th>Sr. No.</th><th>Ledger Name</th><th>Type</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>";
		}*/
		//print_r($dayst_q);
		$i=1;
		foreach ($dayst_q->result() as $row)
		{
			//$current_entry_type = entry_type_info($row->entries_entry_type);
			echo "<tr class=\"tr-" . $odd_even . "\">";
			echo "<td>";
			echo $i;
			//echo anchor('entry/view/' . $current_entry_type['label'] . '/' . $row->entries_id, full_entry_number($row->entries_entry_type, $row->entries_number), array('title' => 'View ' . ' Entry', 'class' => 'anchor-link-a'));
			echo "</td>";
			echo "<td>";
			echo $row->ledgers_name;
			echo "(". $row->ledgers_code. ")";
			echo "</td>";
			echo "<td>";
			//echo $current_entry_type['name'];
			echo "</td>";
			if ($row->entry_items_dc == "D")
			{
				echo "<td>";
				echo convert_dc($row->entry_items_dc);
				echo " ";
				echo money_format('%!i', $row->entry_items_amount);
				echo "</td>";
				echo "<td></td>";
			} 
			else 
			{
				echo "<td></td>";
				echo "<td>";
				echo convert_dc($row->entry_items_dc);
				echo " ";
				echo money_format('%!i', $row->entry_items_amount);
				echo "</td>";
			}
			echo "</tr>";
			$odd_even = ($odd_even == "odd") ? "even" : "odd";
			$i++;
		}
		echo "</table>";
	}
	else
        {
                echo "<table border=\"0\" cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";
                $odd_even = "odd";

                $this->db->select('entry_items.ledger_id as entry_items_ledger_id, entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc, ledgers.id as ledgers_id, ledgers.code as ledgers_code, ledgers.name as ledgers_name');
                $this->db->from('entry_items')->join('ledgers', 'ledgers.id = entry_items.ledger_id');
                $this->db->where('entry_items.update_date', $date1);
                $dayst_q = $this->db->get();
                if( $dayst_q->num_rows() < 1 )
                {
                        $this->messages->add('There is no ledger entry on ' . $date1 . ' date.', 'success');
                }
                else
                {
                        echo "<thead><tr><th colspan=\"5\" align=\"center\"> The ledger entry on date ".$date1." </th></tr></thead>";
                        echo "<thead><tr><th>Sr. No.</th><th>Ledger Name</th><th>Type</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>";
                }

                $i=1;
                foreach ($dayst_q->result() as $row)
                {
                        echo "<tr class=\"tr-" . $odd_even . "\">";
                        echo "<td>";
                        echo $i;
                        echo "</td>";
                        echo "<td>";
                        echo $row->ledgers_name;
                        echo "(". $row->ledgers_code. ")";
                        echo "</td>";
                        echo "<td>";
                        echo "</td>";
                        if ($row->entry_items_dc == "D")
                        {
echo "<td>";
                                echo convert_dc($row->entry_items_dc);
                                echo " ";
                                echo money_format('%!i', $row->entry_items_amount);
                                echo "</td>";
                                echo "<td></td>";
                        }
                        else
                        {
                                echo "<td></td>";
                                echo "<td>";
                                echo convert_dc($row->entry_items_dc);
                                echo " ";
                                echo money_format('%!i', $row->entry_items_amount);
                                echo "</td>";
                        }
                        echo "</tr>";
                        $odd_even = ($odd_even == "odd") ? "even" : "odd";
                        $i++;
                }
                echo "</table>";
        }

	if (!$print_preview)
	{
		echo "<br>";
		echo form_open('report/printpreview/dayst/');
		echo form_submit('submit', 'Print Preview');
		echo form_close();
	}
?>
<?php if ( ! $print_preview) { ?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
<?php } ?>
