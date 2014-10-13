<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	echo form_open('report2/fundlist');
	echo "<p>";
	echo form_label('Search By', 'search_by');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_dropdown('search_by', $search_by, $search_by_active);
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_label('Text', 'text');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_input($text);
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_submit('submit', 'Search');
	echo " ";
	echo "</p>";
	echo form_close();
?>
<?php
	$i=1;
	$clbalance = '';
	$query = '';
	$this->load->model('Ledger_model');
	echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
	echo "<thead><tr><th>S.No </th><th>Code</th><th>Fund Name</th><th>O/P Balance</th><th>C/L Balance</th><th></th></tr></thead>";
	$desinated_code=$this->Ledger_model->get_account_code('Designated-Earmarked Funds');
	$this->db->select('name, id, code, op_balance')->from('ledgers')->where('code LIKE', '%' . $desinated_code . '%');
	$query=$this->db->get();
	if($search == '')
	{
		foreach ($query->result() as $row)
		{
			$led_name = $row->name;
			$led_id=$row->id;
			list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($led_id); /* Opening Balance */
			$clbalance = $this->Ledger_model->get_ledger_balance($led_id); /* Final Closing Balance */
			echo"<tr>";

				echo"<td>";
				echo"$i";
				echo"</td>";
	
				echo"<td>";
                	        echo"$row->code";
                        	echo"</td>";
		
				
				echo "<td>" . "&nbsp;" . anchor('report2/fund_ledgerst/' . $row->id, $row->name, array('title' => $row->name . ' Ledger Statement', 'style' => 'color:#000000')) . "</td>";
						
				echo"<td>";
                        	echo convert_opening($opbalance, $optype);
                      		echo"</td>";

				echo"<td>";
                        	echo convert_amount_dc($clbalance);
                        	echo"</td>";
			echo"</tr>";
			$i++;
		}
	}
	else {
		if($search == "Select")
		{
			$this->messages->add('Please select search type from drop down list.', 'error');
			redirect('report2/fundlist');
		}
		else {
			$check = 1;
			$field = $search . '      ' . 'LIKE';
			$a = trim($text['value']);
			$clbalance = '';
			if($search != "cl_balance") {

		        $this->db->select('name, id, code, op_balance')->from('ledgers')->where('code LIKE', '%' . $desinated_code . '%')->where($field, '%' . $a . '%');
        		$query1=$this->db->get();
			}
			else {
				$this->db->select('name, id, code, op_balance')->from('ledgers')->where('code LIKE', '%' . $desinated_code . '%');
                        $query1=$this->db->get();

			}
			foreach ($query1->result() as $row1)
                	{
				list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row1->id); /* Opening Balance */
			//	if($search != "cl_balance") {
					$clbalance = $this->Ledger_model->get_ledger_balance($row1->id);
echo $clbalance;
			//	}
			/*	else {
					$amount = $this->Ledger_model->get_ledger_balance($row1->id);
					$amount1 = number_format((float)$amount, 2, '.', '');
					if(strstr($amount1, $text['value'])) {
                                 	       $clbalance = $amount1;
                                	}
                                	if($clbalance != NULL) {
                                        	$check++;
                                	}
				}*/
		//	if($clbalance != NULL || $clbalance == 0) {
				echo "<tr>";
					echo "<td>$i</td>";
					echo "<td>$row1->code";
					echo "</td>";
					echo "<td>" . "&nbsp;" . anchor('report/ledgerst/' . $row1->id, $row1->name, array('title' => $row1->name . ' Ledger Statement', 'style' => 'color:#000000'));
					echo "</td>";
					echo "<td>" . convert_opening($opbalance, $optype) . "</td>";
					echo "<td>" . convert_amount_dc($clbalance) . "</td>";
				echo "</tr>";
			//	$check++;
				$i++;
		//	}
}
/*			if($check == "1" && $search == "code")
                        {
                                $this->messages->add($text['value'] . ' is not found.', 'error');
                                redirect('report2/fundlist');
                        }
                        if($check == "1" && $search == "name")
                        {
                                $this->messages->add($text['value'] . ' is not found.', 'error');
                                redirect('report2/fundlist');
                        }
                        if($check == "1" && $search == "cl_balance"){
                                $this->messages->add($text['value'] . ' is not found.', 'error');
                                redirect('report2/fundlist');
                        }
                        if($check == "1" && $search == "op_balance"){
                                $this->messages->add($text['value'] . ' is not found.', 'error');
                                redirect('report2/fundlist');
                        }
                        if($check == "1" && $search == "type"){
                                $this->messages->add($text['value'] . ' is not found.', 'error');
                                redirect('report2/fundlist');
                        }
*/
			
		}
	}
	echo "</table>";
?>
