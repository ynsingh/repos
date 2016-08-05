<?php
	setlocale(LC_MONETARY, 'en_IN');

        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $start_date = $this->session->userdata('startdate');
        $end_date = $this->session->userdata('enddate');
        $this->load->model('Ledger_model');

        $from_date = '';
        $to_date = '';
	echo form_open_multipart('entry/monthwise');
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
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_submit('submit', 'Search');
        echo "</p>";
        echo form_close();
	if($date1 == '' && $date2 == '')
     	{
     		$from_date = $start_date;
      		$to_date = $end_date;
    	}
    	else 
	{
  		$from_date = $date1;
       		$to_date = $date2;
  	}

        $this->load->library('pagination');
        $page_count = (int)$this->uri->segment(4);
        $page_count = $this->input->xss_clean($page_count);
        if ( ! $page_count)
        	$page_count = "0";
        $config['base_url'] = site_url('entry/monthwise/all/');
        $pagination_counter = $this->config->item('row_count');
        $config['num_links'] = 10;
        $config['per_page'] = $pagination_counter;
        $config['uri_segment'] = 4;
        $config['total_rows'] = (int)$this->db->from('entries')->where('date >=', $from_date)->where('date <=', $to_date)->count_all_results();
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

	$this->db->from('entries')->where('date >=', $from_date)->where('date <=', $to_date)->order_by('id','desc');
	$this->db->limit($pagination_counter, $page_count);
	$month_entry = $this->db->get();
	if($month_entry->num_rows() < 1){
		echo "<table width=\"100%\" border=\"0\">";
        	echo "<tr valign=\"top\">";

		echo "<td>";
		echo "<table width=\"100%\" border=\"0\">";

 		echo "<tr style = \"text-align:center;\">";
		echo "<td style=\"padding: 15px 15px 15px 15px;background-color:#87CEEB;\">";
		echo "<h3>There is no voucher between the selected date.</h3>";
		echo "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";
		echo "</tr>";
		echo "</table>";}
	else{
	echo "<table width=\"100%\" border=\"0\">";
        echo "<tr valign=\"top\">";

	echo "<td>";
	echo "<table width=\"100%\" border=\"0\">";

 	echo "<tr style = \"text-align:center;\">";
        echo "<td>";
        echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
	echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Id";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Fwd Ref Id";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Bkwd Ref Id";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Date";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Update Date";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Voucher No";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Vendor/Voucher No";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Ledger Account";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Type";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "DR Amount";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "CR Amount";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Submitted By";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Verified By";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Payment/Receipt By";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo " ";
	echo "</td>";
	echo "</tr>";

	setlocale(LC_MONETARY, 'en_IN');
	$check = 1;
	$i = 1;
 	foreach ($month_entry->result() as $row)
     	{
    		$status = $row->status;
    		$current_entry_type = entry_type_info($row->entry_type);
          	$value = '';
              	$value = $this->Ledger_model->get_entry_name($row->id, $row->entry_type);
       		if($value != NULL) 
		{
			if($i % 2 == 0)
                    	echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
                      	else
                   	echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
              		$this->db->select('name,bank_name,ledger_id,update_cheque_no')->from('cheque_print')->where('entry_no',$row->id);
                 	$ledger_q = $this->db->get();
                	$no_of_row=$ledger_q->num_rows();
                 	echo "<td style=\"text-align:center;\">" . $row->id . "</td>";
                 	echo "<td style=\"text-align:center;\">" . $row->forward_refrence_id . "</td>";
               		echo "<td style=\"text-align:center;\">" . $row->backward_refrence_id . "</td>";
                     	echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($row->date) . "</td>";
                    	echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($row->update_date) . "</td>";
                     	echo "<td style=\"text-align:center;\">" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $row->id, full_entry_number($row->entry_type, $row->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";
                   	echo"<td style=\"text-align:center;\">".$row->vendor_voucher_number."</td>";
                      	echo "<td style=\"text-align:center;\">";
                    	echo $this->Tag_model->show_entry_tag($row->tag_id);
                  	echo $value . "<br>";
                      	echo "Narration: " . $row->narration;
			echo "</td>";
			echo "<td style=\"text-align:center;\">" . $current_entry_type['name'] . "</td>";
                     	echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->dr_total) . "</td>";
                      	echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->cr_total) . "</td>";
                   	echo "<td style=\"text-align:center;\">" . $row->submitted_by . "</td>";
                   	echo "<td style=\"text-align:center;\">" ;
                	if($status != 1)
			{
                     		echo " &nbsp;" . anchor('entry/verify/' . $current_entry_type['label'] . "/" . $row->id,'Verify' ,  array('title' => 'Verify ' . $current_entry_type['name']. ' Entry', 'class' => 'anchor-link-a')) . " ";
                   	}
			else
			{
                    		echo $row->verified_by. " ";
                 	}
                 	echo "<td style=\"text-align:center;\">" ;
                	if( $no_of_row == 1)
                  	{
                 		foreach($ledger_q->result() as $row1)
                       		{
                            		$bank_name = $row1->bank_name;
                                        $name= $row1->name;
                                        $ledger_id= $row1->ledger_id;
                                        $update_chequ_no=$row1->update_cheque_no;
                                   	$this->db->select('cheque_print_status, cheque_bounce_status, No_of_bounce_cheque')->from('cheque_print')->where('ledger_id', $ledger_id)->where('entry_no',$row->id);
                                 	$cheque_status = $this->db->get();
                                  	foreach($cheque_status->result() as $row2)
                               		{
                                		$cheque_print_status = $row2->cheque_print_status;
                                        	$cheque_bounce_status = $row2->cheque_bounce_status;
                                      		$No_of_bounce_cheque = $row2->No_of_bounce_cheque;
                                 	}
					if($cheque_print_status == 0 && $cheque_bounce_status == 0)
                                    	{
                                    		echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $row->id, 'Cheque/DD/BT', array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                                            	echo"<br>";
                                   	}
                                	//Print cheque if bounced..........
                               		if($cheque_print_status == 1 && $cheque_bounce_status == 0 || $cheque_print_status == 1 && $cheque_bounce_status == 1)
                             		{
                               			echo anchor('entry/cheque_bounce/' .  $current_entry_type['label'] . "/" . $row->id, 'Cancle Cheque/DD/BT', array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                                	}
               			}
			}
			elseif( $no_of_row > 1)
			{
                		foreach($ledger_q->result() as $row1)
                		{
                        		$ledger_id= $row1->ledger_id;
                        		$update_chequ_no=$row1->update_cheque_no;
                		}
                		if($ledger_id != NULL &&  $update_chequ_no != NULL)
                		{
                        		if($update_chequ_no != '0')
					{
                                		echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $row->id, 'Cheque/DD/BT', array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                        		}
                		}
        		}
			else
			{
                      		echo "Cash";
        		}
                    	echo "<td style=\"text-align:center;\">" ;
			echo " &nbsp;" . anchor_popup('entry/printpreview/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/print.png", 'border' => '0', 'alt' => 'Print ' . $current_entry_type['name'] . ' Entry')), array('title' => 'Print ' . $current_entry_type['name']. ' Entry', 'width' => '600', 'height' => '600')) . " ";
                     	echo " &nbsp;" . anchor_popup('entry/email/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/email.png", 'border' => '0', 'alt' => 'Email ' . $current_entry_type['name'] . ' Entry')), array('title' => 'Email ' . $current_entry_type['name'] . ' Entry', 'width' => '500', 'height' => '300')) . " ";
                      	echo " &nbsp;" . anchor('entry/download/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/save.png", 'border' => '0', 'alt' => 'Download ' . $current_entry_type['name'] . ' Entry', 'title' => "Download entry")), array('title' => 'Download  ' . $current_entry_type['name'] . ' Entry')) . " ";
                   	echo " &nbsp;" . anchor('entry/pdf/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/pdf.jpeg", 'border' => '0', 'alt' => 'Download Pdf ' . $current_entry_type['name'] . ' Entry', 'title' => "Download entry in pdf")), array('title' => 'Download  ' . $current_entry_type['name'] . ' Entry')) . "</td>";
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
	echo "</table>";}


?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>

