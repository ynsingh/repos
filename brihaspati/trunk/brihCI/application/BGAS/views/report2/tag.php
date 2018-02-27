<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	setlocale(LC_MONETARY, 'en_IN');

	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
	$start_date = $this->session->userdata('startdate');
	$end_date = $this->session->userdata('enddate');
	$this->load->model('Ledger_model');
	$this->load->model('Tag_model');

	$from_date = '';
	$to_date = '';
	if ( ! $print_preview)
	{
		echo form_open('report2/tag/' . $ledger_id);
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
		echo form_label('Tag Account', 'ledger_id');
                echo " ";
		echo form_input_tag('ledger_id', $ledger_id);
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
		//$page_count = $this->input->xss_clean($page_count);
		$page_count = $this->security->xss_clean($page_count);
		if ( ! $page_count)
			$page_count = "0";
		$config['base_url'] = site_url('report/ledgerst/' . $ledger_id);
		$pagination_counter = $this->config->item('row_count');
		$config['num_links'] = 10;
		$config['per_page'] = $pagination_counter;
		$config['uri_segment'] = 4;
		$config['total_rows'] = (int)$this->db->from('entries')->where('tag_id', $ledger_id)->where('date >=', $from_date)->where('date <=', $to_date)->count_all_results();
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

		
		$current_enty_type=array();

		$this->db->from('entries')->where('tag_id', $ledger_id);
		$this->db->where('date >=', $from_date);
                $this->db->where('date <=', $to_date);          
                $prevbal_q = $this->db->get();
		if ($prevbal_q->num_rows() < 1)
                {
                	$this->messages->add('No entry has been done with this tag between ' . $from_date .' & ' .$to_date . ' date.', 'success');
                }
                if ($prevbal_q->num_rows() != 0)
                {
		echo "<br></br>";
                echo "<table border=0 cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";

                echo "<thead><tr><th>Date</th><th>No.</th><th>Ledger Name</th><th>Type</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>";
		foreach($prevbal_q->result() as $row) {
			$current_entry_type = entry_type_info($row->entry_type);
			echo "<tr>";
			echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
			echo "<td>$row->number</td>";
			$tag_name = $this->Ledger_model->get_tag_entry_name($row->id, $row->entry_type);
			echo "<td>$tag_name</td>";
			echo "<td>". $current_entry_type['name']."</td>";
			echo "<td>$row->dr_total</td>";
			echo "<td>$row->cr_total</td>";


			echo "</tr>";
		}
                echo "</table>";
		if (!$print_preview){
                echo "<br>";
                echo form_open('report2/printpreview/tag/' . $ledger_id);
                echo form_submit('submit', 'Print Preview');
                echo form_close();
                }
		}
	}
	else{
                $this->messages->add('Please select the tag account.', 'success');
        }


?>
<?php if ( ! $print_preview) { ?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
<?php } ?>
