<?php
	$this->load->library('accountlist');
	$Dep_method=0;
	if ( ! $print_preview)
	{
		echo "<p>";
		echo "<span id=\"tooltip-target-2\">";
		$inputpreferences   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
				     'checked'     => $budget_over,
				                        
		                     );
		
		echo form_checkbox($inputpreferences) . " Straight Line Method"; 
		echo "</span>";
		echo "<span id=\"tooltip-content-2\">Calculate Depreciation With Staight Line Method.</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		echo "<span id=\"tooltip-target-3\">";
		$inputpreferences1   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
		                     'checked'     => false,
		                     'disabled'    => 'disable',
		                    );
		echo form_checkbox($inputpreferences1 ) . " Double Decline Method";
		echo "</span>";
		echo "<span id=\"tooltip-content-3\">Calculate Depreciation With Double Decline Method.</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "<span id=\"tooltip-target-3\">";
		 $inputpreferences2   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
		                     'checked'     => false,
		                     'disabled'    => 'disable',
		                    );
		echo form_checkbox($inputpreferences2) . " Sum-Of-Years-Digits Method";
		echo "</span>";
		echo "<span id=\"tooltip-content-3\">Calculate Depreciation With Sum-Of-Years-Digits Method.</span>";
		echo "</p>";
   	}
     	$gross_expense_total = 0;
	echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"80%\">";
	echo "<thead><tr><th>S.No</th><th>G.Ledger code</th><th>Asset Name</th><th>Date of Purchase</th><th>Cost</th><th>Dep.Amount</th><th>Current Value</th></tr></thead>";
	echo "<tbody>";
		$account_code = $this->Budget_model->get_account_code('Fixed Assets');
		$this->db->select('a.id, a.date, b.entry_id, b.ledger_id, b.amount, b.dc, c.id, c.name, c.group_id, c.code, d.id, d.parent_id, d.code, e.id, e.percentage');
		$this->db->from('entries a, entry_items b, ledgers c, groups d, dep_assets e')->where('a.id = b.entry_id')->where('b.ledger_id = c.id')->where('c.group_id = d.id')->where('c.id = e.asset_id')->where('c.code LIKE', $account_code.'%')->where('b.dc', 'D');
		$gross_expense_list_q = $this->db->get();
		$i=1;
		foreach ($gross_expense_list_q->result() as $row)
		{
			echo "<tr>";
			echo "<td>" . $i . '.' . "</td>";
		        echo "<td>" . $row->code . "</td>";
			echo "<td>" . $row->name . "</td>";
		        echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
		        echo "<td>" . $row->amount . "</td>";
			$date1=$row->date;
		        $date2=Date("d F Y");
		        $date3=date_create("$date1");
		        $date4=date_create("$date2");
		        $diff=date_diff($date3,$date4);
		        $day = $diff->format("%R%a days");
		        $per_value=$row->percentage;
		        $asset_amount=$row->amount;
		        $value=$asset_amount * $per_value/(100*365);
		        $tot_amount=$value * $day;
		        echo "<td>" .  $tot_amount . "</td>";
			$cur_value = $asset_amount - $tot_amount;
			echo "<td>" . $cur_value . "</td>";
			echo "</tr>";
			$i++;
		}
	echo "</tbody>";
        echo "</table>";
?>
