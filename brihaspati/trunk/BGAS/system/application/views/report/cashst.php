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
		$odd_even = "odd";
		$dr_total = 0;
        	$cr_total= 0;
		$i=0;
		$x=1;
		$counter=1;
		$check = 1;
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
				$expload_search=explode("#",$search);
				$cash_in_hand = $this->Tag_model->cash_in_hand_available('Cash in Hand');
				if($search == NULL || $search == "--Select--"){
					echo "<thead><tr><th colspan=\"5\" align=\"center\"> The ledger entries between date ".$from_date." to ".$to_date." </th></tr></thead>";
					//Head Of cash in hand and name of banks................
					echo "<thead><tr><th width=\"10%\">Date</th><th  width=\"20%\">Ledger Name</th>";
					//First Colomn of Cash in hand.............
					if($cash_in_hand != '0'){
						//echo"===";
						echo"<th width=\"20%\" align=\"center\">".$cash_in_hand."</th>";
					}
					foreach ($op_balance->result() as $row1){
						if($row1->name != 'Cash in Hand'){
							echo"<th width=\"20%\" align=\"center\">".$row1->name."</th>";
						}
					}
					echo"</tr></thead>";
					//Opening Balance Of bank........................
					echo "<thead><tr><th width=\"10%\"></th><th width=\"20%\"></th>";
						for($count=1; $count<=$i; $count++ ){
						echo"<th  width=\"20%\" align=\"center\">"."Dr"."</th>"; 
						}
						echo"</tr>";
						echo "<tr class=\"tr-balance\"><td >Opening Balance</td><td></td>";
						foreach ($op_balance->result() as $row){
                                                if($row->name == 'Cash in Hand'){
                                                list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row->id); /* Opening Balance */
                                                echo "<td align=\"center\">". convert_dc($optype) ." " .$opbalance."</td>";
                                                }
                                                }

						foreach ($op_balance->result() as $row){
						if($row->name != 'Cash in Hand'){
						list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row->id); /* Opening Balance */
				 		echo "<td align=\"center\">". convert_dc($optype) ." " .$opbalance."</td>";
						}
						}
					echo"</tr></thead>";
				}else{
					 echo "<thead><tr><th colspan=\"3\" align=\"center\"> The ledger entries between date ".$from_date." to ".$to_date." </th></tr></thead>";
					 echo "<thead><tr><th width=\"10%\">Date</th><th  width=\"20%\">Ledger Name</th>";
                               		 if($search == 'Cash in Hand'){
                                		if($cash_in_hand == 'Cash in Hand'){
                                        		echo"<th width=\"20%\" align=\"center\">".$cash_in_hand."</th>";
                                		}
                                	}else{
                                                echo"<th width=\"20%\" align=\"center\">".$expload_search[1]."</th>";
					}
                                        	echo"</tr></thead>";

                                		echo "<thead><tr><th width=\"10%\"></th><th width=\"20%\"></th>";
                               	 		echo"<th  width=\"20%\" align=\"center\">"."Dr"."</th>";
                                		echo "<tr class=\"tr-balance\"><td >Opening Balance</td><td>";

						list ($opbalance1, $optype) = $this->Ledger_model->get_op_balance($expload_search[0]); /* Opening Balance */
                                                echo "<td align=\"center\">". convert_dc($optype) ." " .$opbalance1."</td>";

                                        	echo"</tr></thead>";
	
				}
				////For Dr- entries......................				
		
				//For Contra entries..........
				$is_bank_cash1=0;
				$this->db->from('entries')->order_by('date', 'asc');
                                $this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
				$entry = $this->db->get();
                                foreach ($entry->result() as $row3){
					$this->db->from('entry_items')->where('entry_id', $row3->id);
					$entry_items = $this->db->get();
                                        foreach ($entry_items->result() as $row4){
                                   	     	$ledger_id=$row4->ledger_id;
						$is_bank_cash = $this->Ledger_model->get_ledgers_bankcash( $ledger_id);
						//If Both ledger if an entry are bank cash..................
						if($is_bank_cash1==1 && $is_bank_cash==1){
							//Get both ledger id of Debit and credit entries.........
							$val = $this->Tag_model->get_DC_led_id($row3->id);	
							$exp_val=explode("#",$val);
							$Cr_val=explode("C",$exp_val[0]);
							$Dr_val=explode("D",$exp_val[1]);
							$name=$this->Ledger_model->get_name($Dr_val[1]);
							$bank_name1=$this->Tag_model->get_all_bank_cash_ledgers($name);
							//Get key value by the name of bank for know in which no of colomn ...
							$key = array_search($name, $bank_name1);
							$led_name=$this->Ledger_model->get_name($Cr_val[1]);
							if($search == NULL || $search == "--Select--"){
								echo "<td width=\"10%\">" . date_mysql_to_php_display($row3->date) . "</td>";
                                                        	echo "<td width=\"20%\">";
                                                        	echo $led_name . "<br>";
                                                        	echo "Narration: " . $row3->narration;
                                                        	echo "</td>";
								if( $name == $cash_in_hand){
									$key=1;
                                                         	}else{
                                                                        $key = $key + 1;
                                                         	}
                                                         	for($counter=1; $counter <= $key; $counter++){
                                                         		echo "<td align=\"center\">";
                                                         	}
                                                                	echo $row3->dr_total;
                                                                	$val=$i-$key;	
                                                                	for($y=1; $y <= $val; $y++){
                                                                	echo "<td>";
                                                                	}
                                                	}
						}
								//Set flag if both ledger are bank_cash........
								if($is_bank_cash==1){
									$is_bank_cash1=1;
								}
					}
					 			$is_bank_cash1=0;
				
				}

				//////////////////
				$this->db->from('entries')->order_by('date', 'asc');
				$this->db->where('date >=', $date1);
            			$this->db->where('date <=', $date2);

                        	$entry_q = $this->db->get();
				foreach ($entry_q->result() as $row){	
			 		$current_entry_type = entry_type_info($row->entry_type);
					$value = '';
                                	$value = $this->Tag_model->get_entry_name_match1($row->id, $row->entry_type, NULL);
					//Get bank name by the entry has been made.............
					if($value != NULL){
						$get_led_id= $this->Ledger_model->get_id($value);
						$is_led_bank_cash = $this->Ledger_model->get_ledgers_bankcash($get_led_id);
						//not run code for contra entry...............
						if($is_led_bank_cash != '1'){
							$this->db->from('ledgers')->where('name', $value);
							$led_id = $this->db->get();
                                			foreach ($led_id->result() as $row1){
								$ledger_id=$row1->id;
							}
							$this->db->from('entry_items')->where('entry_id', $row->id)->where('ledger_id !=', $ledger_id);
							$entry_items_detail = $this->db->get();
                                			foreach ($entry_items_detail->result() as $row2){
                                        			$entry_items_ledger_id=$row2->ledger_id;
                                			}
								$led_name=$this->Ledger_model->get_name($entry_items_ledger_id);
								$bank_name=$this->Tag_model->get_all_bank_cash_ledgers($led_name);
								$key = array_search($led_name, $bank_name);
								echo"<br>";
                                				if($value != NULL) {
                                        				$check++;
                                				}
								echo"<tr>";	
								$get_led_id= $this->Ledger_model->get_id($led_name);
								$is_led_bank_cash = $this->Ledger_model->get_ledgers_bankcash($get_led_id);
								//if Opposite ledger name is not bank cash
								if($is_led_bank_cash != '0'){
									if($search == NULL || $search == "--Select--"){
                                						echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                                						echo "<td>";
                                						echo $value . "<br>";
                                						echo "Narration: " . $row->narration;
                                						echo "</td>";
										if($cash_in_hand != '0' ){
											echo "<td align=\"center\">" ;
										}
										for($counter=1; $counter <= $key; $counter++){
                                                                                	echo "<td align=\"center\">";
                                                                		}
										echo $row->dr_total;
										$val=$i-$key;
                                                                                $val=$val-1;
                                                                                for($y=1; $y == $val; $y++){
                                                                                echo "<td>";
                                                                                }
	
									}else{
										//Search bank name is eual to the bank envolve in making entry...................
										if($expload_search[1] == $led_name){

											echo "<td width=\"10%\">" . date_mysql_to_php_display($row->date) . "</td>";
                                                                			echo "<td width=\"20%\">";
                                                                			echo $value . "<br>";
                                                                			echo "Narration: " . $row->narration;
                                                                			echo "</td>";
                                                                                        echo "<td align=\"center\">" ;
                                                                                	echo $row->dr_total;

										}
									}
								}
						}
					}
				}
				//Total Balance ........................
        			echo "<tr class=\"tr-total\"><td>TOTAL </td><td></td></td>";
        			if($search == NULL || $search == "--Select--"){
                        		foreach ($op_balance->result() as $row){
                                		if($row->name == 'Cash in Hand'){
                                        		$amount = $this->Ledger_model->get_dr_total1($row->id);
                                        		echo "<td  align=\"center\">Dr " . money_format('%!i', convert_cur($amount)) ."</td>";
                                		}
                                	}

                                 			foreach ($op_balance->result() as $row){

                                 				$amount = $this->Ledger_model->get_dr_total1($row->id);
                                				if($row->name != 'Cash in Hand'){
                                 					echo "<td  align=\"center\">Dr " . money_format('%!i', convert_cur($amount)) ."</td>";
                                				}
                					}
        			}else{
                                	$amount = $this->Ledger_model->get_dr_total1($expload_search[0]);
                                        echo "<td  align=\"center\">Dr " . money_format('%!i', convert_cur($amount)) ."</td>";

        			}
				echo "<tr><td></td><td></td></td><td></td>";
		
				//// For Cr- entries ....................
				echo "<tr  class=\"tr-total\"><td width=\"10%\">Date</td><td  width=\"20%\">Ledger Name</td>";
				if($search == NULL || $search == "--Select--"){

                                	if($cash_in_hand != '0'){
                                        	echo"<td width=\"20%\" align=\"center\">".$cash_in_hand."</td>";
                                	}
                                		foreach ($op_balance->result() as $row1){
                                			if($row1->name != 'Cash in Hand'){
                                        			echo"<td width=\"20%\" align=\"center\">".$row1->name."</td>";
                                			}
                                		}

                                				echo"</tr>";
								echo"<tr  class=\"tr-total\">";
								echo "<td><td width=\"20%\"></td>";
                                				for($count=1; $count<=$i; $count++ ){
                                					echo"<td  width=\"20%\" align=\"center\">"."Cr"."</td>";
                                				}}else{
				 					if($search == 'Cash in Hand'){
                                                				if($cash_in_hand == 'Cash in Hand'){
                                                        				echo"<th width=\"20%\" align=\"center\">".$cash_in_hand."</th>";
                                                				}
                                        				}
				  							echo"<td width=\"20%\" align=\"center\">".$expload_search[1]."</td>";

		

						}
				echo"</tr>";

				//For Contra entries..........

                                $is_bank_cash1=0;
                                $this->db->from('entries')->order_by('date', 'asc');
                                $this->db->where('date >=', $date1);
                                $this->db->where('date <=', $date2);
                                $entry = $this->db->get();
                                foreach ($entry->result() as $row3){
                                        $this->db->from('entry_items')->where('entry_id', $row3->id);
                                        $entry_items = $this->db->get();
                                        foreach ($entry_items->result() as $row4){
                                        	$ledger_id=$row4->ledger_id;
                                                $is_bank_cash = $this->Ledger_model->get_ledgers_bankcash( $ledger_id);
                                                if($is_bank_cash1==1 && $is_bank_cash==1){
							$val = $this->Tag_model->get_DC_led_id($row3->id);
                                                        $exp_val=explode("#",$val);
                                                        $Cr_val=explode("C",$exp_val[0]);
                                                        $Dr_val=explode("D",$exp_val[1]);
                                                        $name=$this->Ledger_model->get_name($Cr_val[1]);
                                                        $bank_name1=$this->Tag_model->get_all_bank_cash_ledgers($name);
                                                        $key = array_search($name, $bank_name1);
                                                        $led_name=$this->Ledger_model->get_name($Dr_val[1]);
							if($search == NULL || $search == "--Select--"){
	
                                                               	echo "<td width=\"10%\">" . date_mysql_to_php_display($row3->date) . "</td>";
                                                               	echo "<td width=\"20%\">";
                                                               	echo $led_name . "<br>";
                                                               	echo "Narration: " . $row3->narration;
                                                               	echo "</td>";
								if( $name == $cash_in_hand){
                                                             		echo "<td align=\"center\">" .$row3->dr_total . "</td>";
                                                               	}else{
                                                               		$key = $key + 1;
                                                               	}
                                                               	for($counter=1; $counter <= $key; $counter++){
                                                               		echo "<td align=\"center\">";
                                                               	}
                                                                	echo $row3->dr_total;
								 	$val=$i-$key;
								 	for($y=1; $y == $val; $y++){
                                                                                echo "<td>";
                                                                        }
                                                 	}else{
							//		if($expload_search[1] == $name){
										echo "<td width=\"10%\">" . date_mysql_to_php_display($row3->date) . "</td>";
                                                                        	echo "<td width=\"20%\">";
                                                                        	echo $led_name . "<br>";
                                                                        	echo "Narration: " . $row3->narration;
                                                                        	echo "</td>";
                                                                                echo "<td align=\"center\">";
                                                                        	echo $row3->dr_total;
							//		}

							}
						}	
        
                                                                if($is_bank_cash==1){
                                                                        $is_bank_cash1=1;
                                                                }
                                                                
                                    }
                                         			$is_bank_cash1=0;
                                
                                }
                                //////////////////
				$this->db->from('entries')->order_by('date', 'asc')->order_by('number', 'desc');
				$this->db->where('date >=', $date1);
            			$this->db->where('date <=', $date2);
                                $entry_q = $this->db->get();
                                foreach ($entry_q->result() as $row){

                                        $current_entry_type = entry_type_info($row->entry_type);
                                        $value = '';
                                        $value = $this->Tag_model->get_entry_name_match($row->id, $row->entry_type, NULL);
					if($value != NULL){
                                        	$this->db->from('ledgers')->where('name', $value);
                                        	$led_id = $this->db->get();
                                        	foreach ($led_id->result() as $row1){
                                                	$ledger_id=$row1->id;
                                        	}
                                                	$this->db->from('entry_items')->where('entry_id', $row->id)->where('ledger_id !=', $ledger_id);
                                                	$entry_items_detail = $this->db->get();
                                                	foreach ($entry_items_detail->result() as $row2){
                                                        	$entry_items_ledger_id=$row2->ledger_id;
                                                	}
								$led_name=$this->Ledger_model->get_name($entry_items_ledger_id);
                                                                $bank_name=$this->Tag_model->get_all_bank_cash_ledgers($led_name);
								$key = array_search($led_name, $bank_name);	
								$get_led_id= $this->Ledger_model->get_id($led_name);
                                                                $is_led_bank_cash = $this->Ledger_model->get_ledgers_bankcash($get_led_id);
                                                                //if Opposite ledger name is not bank cash
                                				if($value != NULL) {
                                        				$check++;
                                				}
                                				echo"<tr>";
								if($is_led_bank_cash != '0'){
									if($search == NULL || $search == "--Select--"){
                                						echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                                						echo "<td>";
                                						echo $value . "<br>";
                                						echo "Narration: " . $row->narration;
                                						echo "</td>";
										 if($cash_in_hand != '0' ){
                                                                                        echo "<td align=\"center\">" ;
                                                                                }
                                                                                for($counter=1; $counter <= $key; $counter++){
                                                                                        echo "<td align=\"center\">";
                                                                                }
                                                                                echo $row->cr_total;
										 $val=$i-$key;
                                                                                $val=$val-1;
                                                                        	for($y=1; $y == $val; $y++){
                                                                              	echo "<td>";
                                                                        	}

									}else{
	                                                                		if($expload_search[1] == $led_name){
												echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                	        	                                        		echo "<td>";
                                                		                		echo $value . "<br>";
                                                                				echo "Narration: " . $row->narration;
                                 	        	                        		echo "</td>";
                                                                	        		echo "<td align=\"center\">" .$row->cr_total . "</td>";
											}
									}
                                                                				echo"</tr>";
							}
					}
                                }

	////Total Of Dr amount........
       echo "<tr class=\"tr-total\"><td>TOTAL </td><td></td>";
       if($search == NULL || $search == "--Select--"){
       		foreach ($op_balance->result() as $row){
                	if($row->name == 'Cash in Hand'){
                        	$amount = $this->Ledger_model->get_cr_total1($row->id);
                                echo "<td  align=\"center\">Cr " . money_format('%!i', convert_cur($amount)) ."</td>";
                         }
                                }
       foreach ($op_balance->result() as $row){

       		$amount = $this->Ledger_model->get_cr_total1($row->id);
                if($row->name != 'Cash in Hand'){

                	echo "<td  align=\"center\">Cr " . money_format('%!i', convert_cur($amount)) ."</td>";

                  }
       }}else{
       		$amount = $this->Ledger_model->get_cr_total1($expload_search[0]);
                echo "<td  align=\"center\">Cr " . money_format('%!i', convert_cur($amount)) ."</td>";
       }
       echo"</tr>";

	//Closing balance .............................
	echo "<tr class=\"tr-balance\"><td >Closing Balance</td><td></td>";
	if($search == NULL ||  $search == "--Select--"){
		 foreach ($op_balance->result() as $row){
			if($row->name == 'Cash in Hand'){
                        $amount = $this->Ledger_model->get_ledger_balance($row->id);
                        echo "<td align=\"center\">". convert_amount_dc($amount) . "</td>";
			}
         	}
		foreach ($op_balance->result() as $row){
			if($row->name != 'Cash in Hand'){	
			$amount = $this->Ledger_model->get_ledger_balance($row->id);
        		echo "<td align=\"center\">". convert_amount_dc($amount) . "</td>";
			}
         }}else{
			$this->db->select('id, name, code');
                        $this->db->from('ledgers')->where('name', $expload_search[1]);
                        $op_balance = $this->db->get();
			foreach ($op_balance->result() as $row){
				$amount = $this->Ledger_model->get_ledger_balance($row->id);
			}
			 	echo "<td align=\"center\">". convert_amount_dc($amount) . "</td>";
	}
	}else{
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
