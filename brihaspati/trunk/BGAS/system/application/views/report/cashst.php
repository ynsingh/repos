<?php if ( ! defined('BASEPATH')) exit('No direct script acces allowed');
	setlocale(LC_MONETARY, 'en_IN');
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
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_dropdown('search_by', $search_by, $search_by_active);
       	 	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		?>

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
		echo "<tr><td><h3>Receipt</td></tr>";
		echo "</table>";
		echo "<table border=\"0\" cellpadding=\"5\" class=\"simple-table ledgerst-table\" width=\"$width\">";
		$odd_even = "odd";
		$dr_total = 0;
        	$cr_total= 0;
		$i=0;
		$x=1;
		$counter=1;
		$bank_cash=0;
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$search_by_bank = $this->session->userdata('search');
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
			$this->db->select('id, name, code');
			$this->db->from('ledgers')->where('type', '1');
                	$op_balance = $this->db->get();
			$num_rows=$op_balance->num_rows();
			//Code will run if atleast ane bank_cash should exist....
			if($num_rows != 0){
                	foreach ($op_balance->result() as $row){
				$bank=$row->name;
				list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row->id); /* Opening Balance */
				if($optype == 'C'){
					$opbalance=-$opbalance;
				}
					$tot_op_bal=$tot_op_bal+$opbalance;
				$i++;
			}
				//In search variable both id and name are seperates by #...........	
				$expload_search=explode("#",$search);
				$cash_in_hand = $this->Tag_model->cash_in_hand_available('Cash');
				if($search == NULL || $search == "--Select--"){
					//Head Of cash in hand and name of banks................
					echo "<thead><tr><th width=\"10%\"><b>Date</th><th width=\"20%\"><b>Ledger Name</th><th width=\"20%\"><b>Voucher No.</th><th width=\"20%\"><b>Head of A/C</th>";
					//First Colomn of Cash in hand.............
					$this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . 'Cash' . '%')->where('type', '1');
                			$led_name = $this->db->get();
                			foreach ($led_name->result() as $row1)
                			{
						 echo"<th width=\"20%\" align=\"center\"><b>".$row1->name."</th>";
						$bank_cash++;	
                			}
					$this->db->from('ledgers')->where('name'. '  ' . 'NOT LIKE', '%' . 'Cash' . '%')->where('type', '1');
                                        $led_name1 = $this->db->get();
                                        foreach ($led_name1->result() as $row2){
						echo"<th width=\"20%\" align=\"center\"><b>".$row2->name."</th>";
					}
					echo"</tr></thead>";
					//Opening Balance Of bank........................
//. money_format('%!i', convert_cur($amount)) .
					echo "<thead>";
						echo "<tr><td><b>Opening Balance</td><td></td><td></td><td></td>";
						$this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . 'Cash' . '%')->where('type', '1');
                                        	$led_name = $this->db->get();
                                        	foreach ($led_name->result() as $row1)
                                        	{
						list ($opbalance, $optype) = $this->Ledger_model->get_op_closing_balance($row1->id, $from_date, $to_date); /* Opening Balance */
                                                echo "<td align=\"center\">"."<b>". convert_dc($optype) ." " .money_format('%!i', convert_cur($opbalance))."</td>";
	
                                        	}
                                        	$this->db->from('ledgers')->where('name'. '  ' . 'NOT LIKE', '%' . 'Cash' . '%')->where('type', '1');
                                        	$led_name1 = $this->db->get();
                                        	foreach ($led_name1->result() as $row2){
                                        		list ($opbalance, $optype) = $this->Ledger_model->get_op_closing_balance($row2->id, $from_date, $to_date); /* Opening Balance */
                                                echo "<td align=\"center\">"."<b>". convert_dc($optype) ." " .money_format('%!i', convert_cur($opbalance))."</td>";

						}
					echo"</tr></thead>";
				}else{
					 echo "<thead><tr><th width=\"10%\"><b>Date</th><th width=\"20%\"><b>Ledger Name</th><th width=\"20%\"><b>Voucher No.</th><th width=\"20%\"><b>Head Of A/C</th>";
                                         echo"<th width=\"20%\" align=\"center\"><b>".$expload_search[1]."</th>";
                                	 echo "<tr><td><b>Opening Balance</td><td></td><td></td><td></td>";
					 list ($opbalance1, $optype) = $this->Ledger_model->get_op_closing_balance($expload_search[0], $from_date, $to_date); /* Opening Balance */
                                         echo "<td align=\"center\"><b>". convert_dc($optype) ." " .money_format('%!i', convert_cur($opbalance1))."</td>";
                                         echo"</tr></thead>";
				}
				////For Dr- entries......................				
		


					$this->db->select('ledgers.name as name, ledgers.id as id, entry_items.entry_id as entry_id, entry_items.amount as amount, entries.narration as narration, entries.date as date, entries.number as voucher_numb, entries.sanc_value as head_value, entries.sanc_type as head_type');
					$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.dc', 'D')->where('ledgers.type', '1')->order_by('entries.date', 'asc');	
					$this->db->where('entries.date >=', $from_date);
                                        $this->db->where('entries.date <=', $to_date);

 //                        		$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.dc', 'D')->where('ledgers.type', '1')->order_by('entries.date', 'asc');
                         		$entry_items_q = $this->db->get();
					foreach ($entry_items_q->result() as $entry)
                                	{
						$id=$entry->id;
                                                $amount=$entry->amount;
						$led_name=$this->Ledger_model->get_name($id);
                                                $opp_name=$this->Tag_model->get_ledger_of_multiple_entry($id, $entry->entry_id);
						$exp_opp_name=explode("#",$opp_name);
                                                $bank_type=$this->Tag_model->check_type_of_bank($led_name);
						if($entry->head_value=="select")
							$head_sanc_value="";
						else
							$head_sanc_value="  (".$entry->head_value.")";
						if($entry->head_type=="non_plan")
							$head_sanc_type="Non Plan";
						elseif($entry->head_type=="plan")
							$head_sanc_type="Plan";
						elseif($entry->head_type=="plan_sfc_scheme")
							$head_sanc_type="Plan Specific Schemes";
						elseif($entry->head_type=="plan_other_scheme")
							$head_sanc_type="Other Schemes";
                                                if($bank_type == '0'){
                                                	$bank_name=$this->Tag_model->get_all_bank_cash_ledgers($led_name);
                                                   }else{
                                                        $bank_name=$this->Tag_model-> get_bank_cash_index($led_name);
                                                 }
                                                        $key = array_search($led_name, $bank_name);
                                                        if($bank_type == '0'){
                                                        	$key=$bank_cash+$key;
                                                        }
                                                                echo"<tr>";
                                                                if($search == NULL || $search == "--Select--"){
                                                                	echo "<td>" . date_mysql_to_php_display($entry->date) . "</td>";
                                                                        echo "<td>";
                                                                        echo $exp_opp_name[0] . "<br>";
                                                                        echo "Narration: " . $entry->narration;
                                                                        echo "</td>";
                                                                        echo "<td>";
                                                                        echo $entry->voucher_numb;
                                                                        echo "</td>";
                                                                        echo "<td>";
									echo $head_sanc_type.$head_sanc_value;
                                                                        echo "</td>";
                                                                        for($counter=1; $counter <= $key; $counter++){
                                                                        	echo "<td align=\"center\">";
                                                                        }
                                                                                echo $amount;
                                                                                $val=$i-$key;
                                                                                for($y=1; $y <= $val; $y++){
                                                                                	echo "<td>";
                                                                                }
                                                                 }else{
                                                                 	if($expload_search[1] == $led_name){
                                                                        	echo "<td>" . date_mysql_to_php_display($entry->date) . "</td>";
                                                                                echo "<td>";
                                                                                echo  $exp_opp_name[0] . "<br>";
                                                                                echo "Narration: " . $entry->narration;
                                                                                echo "</td>";
									echo "<td>";
                                                                        echo $entry->voucher_numb;
                                                                        echo "</td>";
                                                                        echo "<td>";
									echo $head_sanc_type.$head_sanc_value;
                                                                        echo "</td>";
                                                                                echo "<td align=\"center\">" . $amount . "</td>";
                                                                          }
                                                                 }
                                                                                                echo"</tr>";
                                          }

				//Total Balance ........................
        			echo "<tr class=\"tr-total\"><td>TOTAL </td><td></td></td><td></td><td></td>";
        			if($search == NULL || $search == "--Select--"){
					$this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . 'Cash' . '%')->where('type', '1');
                                        $led_name = $this->db->get();
                                        foreach ($led_name->result() as $row1)
                                        {
						$amount = $this->Ledger_model->get_dr_total1($row1->id);
                                                echo "<td align=\"center\">Dr " . money_format('%!i', convert_cur($amount)) ."</td>";

                                        }
                                        $this->db->from('ledgers')->where('name'. '  ' . 'NOT LIKE', '%' . 'Cash' . '%')->where('type', '1');
                                        $led_name1 = $this->db->get();
                                        foreach ($led_name1->result() as $row2)
                                        {
						$amount = $this->Ledger_model->get_dr_total1($row2->id);
                                                echo "<td align=\"center\">Dr " . money_format('%!i', convert_cur($amount)) ."</td>";

                                        }            
        			}else{
                                	$amount = $this->Ledger_model->get_dr_total1($expload_search[0]);
                                        echo "<td  align=\"center\">Dr " . money_format('%!i', convert_cur($amount)) ."</td>";

        			}
				echo "<tr><td colspan=\"6\"><h3>Payment</td>";
		
				//// For Cr- entries ....................
				echo "<tr  class=\"tr-total\"><td width=\"10%\"><b>Date</td><td width=\"20%\"><b>Ledger Name</td><td width=\"20%\"><b>Voucher No.</td><td width=\"20%\"><b>Head Of A/C</td>";
				if($search == NULL || $search == "--Select--"){
					$this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . 'Cash' . '%')->where('type', '1');
				        $led_name = $this->db->get();
				        foreach ($led_name->result() as $row1)
        				{
						echo"<td width=\"20%\" align=\"center\"><b>".$row1->name."</td>";
        				}
        				$this->db->from('ledgers')->where('name'. '  ' . 'NOT LIKE', '%' . 'Cash' . '%')->where('type', '1');
        				$led_name1 = $this->db->get();
        				foreach ($led_name1->result() as $row2)
        				{
						echo"<td width=\"20%\" align=\"center\"><b>".$row2->name."</td>";
        				}

				}else{
				  		echo"<td width=\"20%\" align=\"center\"><b>".$expload_search[1]."</td>";

				}
				echo"</tr>";

                                //////////////////
			 	$this->db->select('ledgers.name as name, ledgers.id as id, entry_items.entry_id as entry_id, entry_items.amount as amount, entries.narration as narration, entries.date as date, entries.number as voucher_numb1, entries.sanc_value as head_value1, entries.sanc_type as head_type1');
                                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.dc', 'C')->where('ledgers.type', '1')->order_by('entries.date', 'asc');
				$this->db->where('entries.date >=', $from_date);
                                $this->db->where('entries.date <=', $to_date);

                                $entry_items_q = $this->db->get();
                                foreach ($entry_items_q->result() as $entry)
                                {
                                	$id=$entry->id;
                                        $amount=$entry->amount;
                                        $led_name=$this->Ledger_model->get_name($id);
					$opp_name=$this->Tag_model->get_ledger_Dr_multiple_entry($id, $entry->entry_id);	
					$exp_opp_name=explode("#",$opp_name);
					$bank_type=$this->Tag_model->check_type_of_bank($led_name);
					if(($entry->head_value1=="select") || ($entry->head_value1==""))
                                		$head_sanc_value1="";
                                   	else
                             			$head_sanc_value1="  (".$entry->head_value1.")";
					if($entry->head_type1=="select")
					{
						$head_sanc_type1="";
					}
					else
					{
                                 		if($entry->head_type1=="non_plan")
                                			$head_sanc_type1="Non Plan";
                                    		elseif($entry->head_type1=="plan")
                                 			$head_sanc_type1="Plan";
                               			elseif($entry->head_type1=="plan_sfc_scheme")
                                			$head_sanc_type1="Plan Specific Schemes";
                              			elseif($entry->head_type1=="plan_other_scheme")
                               				$head_sanc_type1="Other Schemes";
					}

                                        if($bank_type == '0'){
                                        	$bank_name=$this->Tag_model->get_all_bank_cash_ledgers($led_name);
                                        }else{
                                                $bank_name=$this->Tag_model-> get_bank_cash_index($led_name);
                                        }
                                                $key = array_search($led_name, $bank_name); 
						if($bank_type == '0'){
                                                	$key=$bank_cash+$key;
                                                 }
                                			echo"<tr>";
							if($search == NULL || $search == "--Select--"){
                                				echo "<td>" . date_mysql_to_php_display($entry->date) . "</td>";
                                				echo "<td>";
                                				echo $exp_opp_name[0] . "<br>";
                                				echo "Narration: " . $entry->narration;echo"<br>";
                                				echo "</td>";
								echo "<td>";
                                                         	echo $entry->voucher_numb1;
                                                           	echo "</td>";
                                                          	echo "<td>";
                                                           	echo $head_sanc_type1.$head_sanc_value1;
                                                            	echo "</td>";

                                                                for($counter=1; $counter <= $key; $counter++){
                                                                	echo "<td align=\"center\">";
                                                                }
                                                                        echo $amount;
									$val=$i-$key;
                                                                        for($y=1; $y <= $val; $y++){
                                                                        	echo "<td>";
                                                                        }
							}else{
	                                                	if($expload_search[1] == $led_name){
									echo "<td>" . date_mysql_to_php_display($entry->date) . "</td>";
                	        	                        	echo "<td>";
                                                			echo $exp_opp_name[0];echo"<br>";
                                                                	echo "Narration: " . $entry->narration;
                                 	        	        	echo "</td>";
									echo "<td>";
                                                                	echo $entry->voucher_numb1;
                                                                	echo "</td>";
                                                                	echo "<td>";
                                                                	echo $head_sanc_type1.$head_sanc_value1;
                                                                	echo "</td>";
                                                                	echo "<td align=\"center\">" .$entry->amount . "</td>";
								}
							}
                                                                				echo"</tr>";
				}

	////Total Of Dr amount........
       echo "<tr  class=\"tr-total\"><td>TOTAL </td><td></td><td></td><td></td>";
       if($search == NULL || $search == "--Select--"){
	$this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . 'Cash' . '%')->where('type', '1');
        $led_name = $this->db->get();
        foreach ($led_name->result() as $row1)
        {	
        	$amount = $this->Ledger_model->get_cr_total1($row1->id);
                echo "<td  align=\"center\">Cr " . money_format('%!i', convert_cur($amount)) ."</td>";
        }
	$this->db->from('ledgers')->where('name'. '  ' . 'NOT LIKE', '%' . 'Cash' . '%')->where('type', '1');
        $led_name1 = $this->db->get();
        foreach ($led_name1->result() as $row2)
        {
                $amount = $this->Ledger_model->get_cr_total1($row2->id);
                echo "<td  align=\"center\">Cr " . money_format('%!i', convert_cur($amount)) ."</td>";
        }

       }else{
       		$amount = $this->Ledger_model->get_cr_total1($expload_search[0]);
                echo "<td  align=\"center\">Cr " . money_format('%!i', convert_cur($amount)) ."</td>";
       }
       echo"</tr>";

	//Closing balance .............................
	echo "<tr class=\"tr-balance\"><td><b>Closing Balance</td><td></td><td></td><td></td>";
	if($search == NULL ||  $search == "--Select--"){
		  $this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . 'Cash' . '%')->where('type', '1');
        	$led_name = $this->db->get();
        	foreach ($led_name->result() as $row1)
        	{
                	$amount = $this->Ledger_model-> get_closing_balance($row1->id);
                	echo "<td  align=\"center\"><b>" . convert_amount_dc($amount) ."</td>";
        	}
        	$this->db->from('ledgers')->where('name'. '  ' . 'NOT LIKE', '%' . 'Cash' . '%')->where('type', '1');
       	 	$led_name1 = $this->db->get();
        	foreach ($led_name1->result() as $row2)
        	{
                	$amount = $this->Ledger_model->get_closing_balance($row2->id);
                	echo "<td  align=\"center\"><b>" . convert_amount_dc($amount) ."</td>";
        	}

       }
       else{
			$this->db->select('id, name, code');
                        $this->db->from('ledgers')->where('name', $expload_search[1]);
                        $op_balance = $this->db->get();
			foreach ($op_balance->result() as $row){
				$amount = $this->Ledger_model->get_closing_balance($row->id);
			}
			 	echo "<td align=\"center\"><b>". convert_amount_dc($amount) . "</td>";
	}
	}
	else{
		$this->messages->add('Either there is no any bank_cash account or there are no any entry.', 'error');
	}


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
