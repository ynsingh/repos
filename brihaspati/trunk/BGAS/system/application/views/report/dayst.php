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
	
	$this->db->from('entries');
	$this->db->where('date', $date1);
	$da_yst_q = $this->db->get();
	$total_row_entr = $da_yst_q->num_rows(); 
	
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
		$config['total_rows'] = (int)$this->db->from('entries')->where('date', $date1)->count_all_results();
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
		
		echo "<table width=\"100%\" border=\"0\">";
        	echo "<tr valign=\"top\">";

        	echo "<td>";
        	echo "<table width=\"100%\" border=\"0\">";

		echo "<tr style = \"text-align:center;\">";
        	echo "<td>";
        	echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
		echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "The ledger entry on date ".$date1;
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "(Total no. of entry :".$total_row_entr.")";
                echo "</td>";
                echo "</tr>";

        	echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Voucher No.";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Ledger Name";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Type";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Dr Amount";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Cr Amount";
        	echo "</td>";
        	echo "</tr>";
		$this->db->from('entries');
		$this->db->where('date', $date1);
		$this->db->limit($pagination_counter, $page_count);		
		$dayst_q = $this->db->get();
		if( $dayst_q->num_rows() < 1 )
		{
			$this->messages->add('There is no ledger entry on ' . $date1 . ' date.', 'success');
		}
		else
		{
			$i=1;
			foreach ($dayst_q->result() as $row)
			{
				if($i % 2 == 0)
				echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
				else
				echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
                        	echo "<td style=\"text-align:center;\">" . $row->number . "</td>";
                        	echo "<td style=\"text-align:center;\">" . $this->Ledger_model->get_entry_name($row->id, $row->entry_type). "</td>";
                        	echo "<td style=\"text-align:center;\">" . $this->entry_model->get_name_of_entry_type($row->entry_type) . "</td>";
                                echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->dr_total) ."</td>";
                                	
				echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->cr_total) ."</td>";
                        	echo "</tr>";
				$i++;
                        }
        	}
        	echo "</table>";
        	echo "</td>";
        	echo "</tr>";

        	echo "</table>";
        	echo "</td>";

        	echo "</tr>";
        	echo "</table>";
	}
	else
	{
		echo "<table width=\"100%\" border=\"0\">";
        	echo "<tr valign=\"top\">";

        	echo "<td>";
        	echo "<table width=\"100%\" border=\"0\">";

		echo "<tr style = \"text-align:center;\">";
        	echo "<td>";
        	echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
		echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "The ledger entry on date ".$date1;
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "</td>";
                echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                echo "(Total no. of entry :".$total_row_entr.")";
                echo "</td>";
                echo "</tr>";
        	echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Voucher No.";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Ledger Name";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Type";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Dr Amount";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
        	echo "Cr Amount";
        	echo "</td>";
        	echo "</tr>";
		$this->db->from('entries');
		$this->db->where('date', $date1);
		$dayst_q = $this->db->get();
		if( $dayst_q->num_rows() < 1 )
		{
			$this->messages->add('There is no ledger entry on ' . $date1 . ' date.', 'success');
		}
		else
		{
			foreach ($dayst_q->result() as $row)
			{
				echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
                        	echo "<td style=\"text-align:center;\">" . $row->number . "</td>";
                        	echo "<td style=\"text-align:center;\">" . $this->Ledger_model->get_entry_name($row->id, $row->entry_type). "</td>";
                        	echo "<td style=\"text-align:center;\">" . $this->entry_model->get_name_of_entry_type($row->entry_type) . "</td>";
                                echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->dr_total) ."</td>";
                                	
				echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->cr_total) ."</td>";
                        	echo "</tr>";
                        }
        	}
        	echo "</table>";
        	echo "</td>";
        	echo "</tr>";

        	echo "</table>";
        	echo "</td>";

        	echo "</tr>";
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
