<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	setlocale(LC_MONETARY, 'en_IN');
	$name='';
	$code='';
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	if ( ! $print_preview)
	{
		echo form_open('report/cashst/');
		echo "<p>";
		echo "<span id=\"tooltip-target-1\">";
		echo form_label('Entry Date From', 'entry_date1');
		echo " ";
		echo form_input_date_restrict($entry_date1);
		echo "</span>";
		echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		echo "<span id=\"tooltip-target-2\">";
		echo form_label('To Entry Date', 'entry_date2');
		echo " ";
		echo form_input_date_restrict($entry_date2);
		echo "</span>";
		echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?>
		<input type='submit' value="GET" class='loading'>
		<?php echo "</p>";
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
		$config['base_url'] = site_url('report/cashst/');
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

	}
		echo "<table border=\"0\" cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";
		$odd_even = "odd";
		$dr_total = 0;
        	$cr_total= 0;
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$from_date = '';
		$to_date = '';
		if($date1 == '' && $date2 == '')
		{
			$from_date = $start_date;
			$to_date = $end_date;
		}
		else {
			$from_date = $date1;
			$to_date = $date2;
		}
			$tot_op_bal='';
			$this->db->from('ledgers')->where('type', '1');
                	$op_balance = $this->db->get();
                	foreach ($op_balance->result() as $row){
			list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row->id); /* Opening Balance */
			 //$tot_op_bal = float_ops($tot_op_bal, $opbalance, '+');
			if($optype == 'C'){
				$opbalance=-$opbalance;
			}
			$tot_op_bal=$tot_op_bal+$opbalance;
			}	
			//$val=gmp_sign($tot_op_bal);
			echo "<thead><tr><th colspan=\"5\" align=\"center\"> The ledger entries between date ".$from_date." to ".$to_date." </th></tr></thead>";
                        echo "<thead><tr><th>Sr. No.</th><th>Date</th><th>Ledger Name</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>";
			echo "<tr class=\"tr-balance\"><td colspan=\"4\">Opening Balance</td><td>" . convert_amount_dc($tot_op_bal) . "</td></tr>";

			$i=1;
			$this->db->select('id, name, code');
			$this->db->from('ledgers')->where('type', '1')->order_by('name', 'asc');
			$ledgers_id = $this->db->get();
			foreach ($ledgers_id->result() as $row)
                	{
				$this->code=$row->code;
                        	$this->db->from('entry_items')->where('ledger_id', $row->id)->order_by('update_date', 'desc');
				$this->db->where('update_date >=', $from_date);
                		$this->db->where('update_date <=', $to_date);
				$entry_items_details = $this->db->get();
			/*	if ($entry_items_details->num_rows() == 0)
                                {
                                        $this->messages->add('No entry has been done with this tag between ' . $from_date .' & ' .$to_date . ' date.', 'success');
                                }
				*/
				foreach ($entry_items_details->result() as $row1)
				{
                        		echo "<tr class=\"tr-" . $odd_even . "\">";
                        		echo "<td>";
                                	echo $i;
                        		echo "</td>";
					echo "<td>";
                                        echo date_mysql_to_php($row1->update_date);
                                        echo "</td>";
                        		echo "<td>";
                                	echo $row->name." ";
                                	echo "(". $this->code. ")";
                        		echo "</td>";
                                	if ($row1->dc == "D")
                                	{
                                        	echo "<td>";
						$dr_total = float_ops($dr_total,  $row1->amount, '+');
                                                echo convert_dc($row1->dc);
                                                echo " ";
                                                echo money_format('%!i', $row1->amount);
                                        	echo "</td>";
                                        	echo "<td></td>";
                                	} else {
                                        	echo "<td></td>";
                                        	echo "<td>";
						$cr_total = float_ops($cr_total,  $row1->amount, '+');
                                                echo convert_dc($row1->dc);
                                                echo " ";
                                                echo money_format('%!i', $row1->amount);
                                        	echo "</td>";
                                	}	
						$total = float_ops($dr_total, $cr_total, '-');
                        			echo "</tr>";
                        			$odd_even = ($odd_even == "odd") ? "even" : "odd";
                        		$i++;
                	}	

		}
	 echo "<tr class=\"tr-balance\"><td colspan=\"4\">Closing Balance</td><td>" . convert_amount_dc($total) . "</td></tr>";

	echo "<tr class=\"tr-total\"><td colspan=\"1\">TOTAL ";
	echo"<td></td>";
	echo"<td></td>";
        echo "</td><td>Dr " . money_format('%!i', convert_cur($dr_total)) . "</td><td>Cr " . money_format('%!i', convert_cur($cr_total)) . "</td></tr>";

		echo "</table>";
		if (!$print_preview){
			echo "<br>";
			echo form_open('report/printpreview/cashst/');
			echo form_submit('submit', 'Print Preview');
			echo form_close();
		}
?>
<?php if ( ! $print_preview) { ?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
<?php } ?>
