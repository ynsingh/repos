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
                echo form_open('report2/negative_trans_report');
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

                ?>

                <input type='submit' value="GET" class='loading'>
                <?php echo "</p>";
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


	/*Pagination configuration for income */
        /* Pagination configuration */
	 if ( ! $print_preview)
        {
                $this->load->library('pagination');
                $page_count = (int)$this->uri->segment(3);
                //$page_count = $this->input->xss_clean($page_count);
		$page_count = $this->security->xss_clean($page_count);
                if ( ! $page_count)
                        $page_count = "0";
                $config['base_url'] = site_url('report2/negative_trans_report');
                $pagination_counter = $this->config->item('row_count');
                $config['num_links'] = 10;
                $config['per_page'] = $pagination_counter;
                $config['uri_segment'] = 3;

		$config['total_rows'] = (int) $this->db->select('ledgers.name as name, ledgers.id as id, entry_items.entry_id as entry_id, entry_items.amount as amount,entries.narration as narration, entries.date as date,entries.number as voucher_numb, entries.entry_type as type, entries.number as number,entries.sanc_value as head_value, entries.sanc_type as head_type, entries.vendor_voucher_number as vendor_voucher_number,entries.dr_total as dr_total, entries.cr_total as cr_total, entries.verified_by as verified_by, entries.submitted_by as submitted_by, entries.status as status')->from('entries')->join('entry_items', ' entries.id = entry_items.entry_id')->join('ledgers','entry_items.ledger_id = ledgers.id')->where('entry_items.ledger_code LIKE','30%')->where( 'entry_items.dc','D')->where('entries.date >=', $from_date)->where('entries.date <=', $to_date)->count_all_results();

        //      print_r($config['total_rows'] );
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
        echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "Id";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "Date";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "Voucher No";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "Vendor/Voucher No";
	echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "Ledger Account";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\ align=\"center\">";
        echo "Type";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "DR Amount";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "CR Amount";
        echo "</td>";
        echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\" align=\"center\">";
        echo "Submitted By";
        echo "</td>";
       // echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
       // echo " ";
       // echo "</td>";
        echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td align=\"center\" style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"><b>INCOME NEGATIVE TRANSACTION</b></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."</tr>";

                $i = 1;
                $this->db->select('ledgers.name as name, ledgers.id as id, entry_items.entry_id as entry_id, entry_items.amount as amount,entries.narration as narration, entries.date as date,entries.number as voucher_numb, entries.entry_type as type, entries.number as number,entries.sanc_value as head_value, entries.sanc_type as head_type, entries.vendor_voucher_number as vendor_voucher_number,entries.dr_total as dr_total, entries.cr_total as cr_total, entries.verified_by as verified_by, entries.submitted_by as submitted_by, entries.status as status');
                $this->db->from('entries')->join('entry_items', ' entries.id = entry_items.entry_id');
                $this->db->join('ledgers','entry_items.ledger_id = ledgers.id');	
                $this->db->where('entry_items.ledger_code LIKE','30%');
		$this->db->where( 'entry_items.dc','D'); 
		$this->db->where('entries.date >=', $from_date);
		$this->db->where('entries.date <=', $to_date);
	
		//SELECT * FROM `table` GROUP BY id HAVING COUNT(id) = 1
                if ( ! $print_preview){
                $this->db->limit($pagination_counter, $page_count);
                }

                $entry_items_q = $this->db->get();
                foreach ($entry_items_q->result() as $entry)
                {
			$entryid = $entry->entry_id;	
				
                        $this->db->select('name,bank_name,ledger_id,update_cheque_no')->from('cheque_print')->where('entry_no',$entry->entry_id);
                        $ledger_q = $this->db->get();
                        $no_of_row=$ledger_q->num_rows();
					
                        $status = $entry->status;
                        $current_entry_type = entry_type_info($entry->type);
                        $value = $this->Ledger_model->get_entry_name($entry->entry_id, $entry->type);
                        if($i % 2 == 0)
                        echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
                        else
                        echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
                        echo "<td style=\"text-align:center;\">" .  $entry->entry_id . "</td>";
                        echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($entry->date) . "</td>";

                        echo "<td style=\"text-align:center;\">" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry->entry_id, full_entry_number($entry->type, $entry->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";
                        echo"<td style=\"text-align:center;\">".$entry->vendor_voucher_number."</td>";
                        echo "<td>".$value . "</td>";
                        echo "<td style=\"text-align:center;\">" . $current_entry_type['name'] . "</td>";
                        echo "<td style=\"text-align:center;\">" . money_format('%!i', $entry->dr_total) . "</td>";
                        echo "<td style=\"text-align:center;\">" . money_format('%!i', $entry->cr_total) . "</td>";
                        echo"<td style=\"text-align:center;\">".$entry->submitted_by."</td>";
			//echo "<td></td>";
                        echo"</tr>";
                        $i++;

                } 

echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td align=\"center\" style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"><b>EXPENSE NEGATIVE TRANSACTION</b></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\"></td>"."</tr>";

	/*Pagination configuration for expense */

		 /* Pagination configuration */
         if ( ! $print_preview)
        {
                $this->load->library('pagination');
                $page_count = (int)$this->uri->segment(3);
                //$page_count = $this->input->xss_clean($page_count);
		$page_count = $this->security->xss_clean($page_count);
                if ( ! $page_count)
                        $page_count = "0";
                $config['base_url'] = site_url('report2/negative_trans_report');
                $pagination_counter = $this->config->item('row_count');
                $config['num_links'] = 10;
                $config['per_page'] = $pagination_counter;
                $config['uri_segment'] = 3;

                $config['total_rows'] = (int) $this->db->select('ledgers.name as name, ledgers.id as id, entry_items.entry_id as entry_id, entry_items.amount as amount,entries.narration as narration, entries.date as date,entries.number as voucher_numb, entries.entry_type as type, entries.number as number,entries.sanc_value as head_value, entries.sanc_type as head_type, entries.vendor_voucher_number as vendor_voucher_number,entries.dr_total as dr_total, entries.cr_total as cr_total, entries.verified_by as verified_by, entries.submitted_by as submitted_by, entries.status as status')->from('entries')->join('entry_items', ' entries.id = entry_items.entry_id')->join('ledgers','entry_items.ledger_id = ledgers.id')->where('entry_items.ledger_code LIKE','40%')->where( 'entry_items.dc','C')->where('entries.date >=', $from_date)->where('entries.date <=', $to_date)->count_all_results();

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

                $j = 1;
                $this->db->select('ledgers.name as name, ledgers.id as id, entry_items.entry_id as entry_id, entry_items.amount as amount, entries.narration as  
narration, entries.date as date,entries.number as voucher_numb, entries.entry_type as type, entries.number as number,entries.sanc_value as head_value, entries.sanc_type as head_type, entries.vendor_voucher_number as vendor_voucher_number,entries.dr_total as dr_total, entries.cr_total as cr_total, entries.verified_by as verified_by, entries.submitted_by as submitted_by, entries.status as status');
		$this->db->from('entries')->join('entry_items', ' entries.id = entry_items.entry_id');
                $this->db->join('ledgers','entry_items.ledger_id = ledgers.id');
                $this->db->where('entry_items.ledger_code LIKE','40%');
                $this->db->where( 'entry_items.dc','C');
		$this->db->where('entries.date >=', $from_date); 
                $this->db->where('entries.date <=', $to_date);


                //SELECT * FROM `table` GROUP BY id HAVING COUNT(id) = 1
                if ( ! $print_preview){
                $this->db->limit($pagination_counter, $page_count);
                }


                $entry_items_q = $this->db->get();
                foreach ($entry_items_q->result() as $entry)
                {
                        $entryid = $entry->entry_id;
                        $this->db->select('name,bank_name,ledger_id,update_cheque_no')->from('cheque_print')->where('entry_no',$entry->entry_id);
                        $ledger_q = $this->db->get();
                        $no_of_row=$ledger_q->num_rows();

                        $status = $entry->status;
                        $current_entry_type = entry_type_info($entry->type);
                        $value = $this->Ledger_model->get_entry_name($entry->entry_id, $entry->type);

                        if($j % 2 == 0)
                        echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
                        else
                        echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
                        echo "<td style=\"text-align:center;\">" .  $entry->entry_id . "</td>";
                        echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($entry->date) . "</td>";
                        echo "<td style=\"text-align:center;\">" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry->entry_id, full_entry_number($entry->type, $entry->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";
                        echo"<td style=\"text-align:center;\">".$entry->vendor_voucher_number."</td>";
                        echo "<td>".$value . "</td>";
                        echo "<td style=\"text-align:center;\">" . $current_entry_type['name'] . "</td>"; 
                        echo "<td style=\"text-align:center;\">" . money_format('%!i', $entry->dr_total) . "</td>";
                        echo "<td style=\"text-align:center;\">" . money_format('%!i', $entry->cr_total) . "</td>";
                        //echo "<td>"." &nbsp;" . anchor('entry/verify/' . $current_entry_type['label'] . "/" . $entry->entry_id,'Verify' ,  array('title' => 'Verify ' . $current_entry_type['name']. ' Entry', 'class' => 'anchor-link-a')) . " "."</td>";
                        echo"<td style=\"text-align:center;\">".$entry->submitted_by."</td>";
			//echo "<td></td>";
                        echo"</tr>";
                        $j++;
		}
echo "</table>";

?>
<?php if ( ! $print_preview) { ?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
<?php } ?>

                                                      
		
