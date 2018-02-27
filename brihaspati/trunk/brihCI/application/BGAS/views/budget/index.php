<?php
	echo form_open('budgetl/index');
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
	$this->load->library('budgetlist');
	setlocale(LC_MONETARY, 'en_IN');
	echo "<table>";
	echo "<tr valign=\"top\">";
	$asset = new Budgetlist();
	echo "<td>";
	$asset->init(0);
	echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
	//echo "<thead><tr><th>Budget Code </th><th>Budget Name</th><th>Budget Type</th><th>O/P Balance</th><th>C/L Balance</th><th>Over Expence Allowed</th> <th></th></tr></thead>";
	//echo "<thead><tr><th>Budget Code </th><th>Budget Name</th><th>Budget Type</th><th>B/D Balance</th><th>O/P Balance</th><th>Over Expence Allowed</th> <th></th></tr></thead>";
	echo "<thead><tr><th>Budget Code </th><th>Budget Name</th><th>Budget Type</th><th>B/D Balance</th><th>Over Expence Allowed</th> <th>Available Budget</th><th></th></tr></thead>";
	if($search == '')
	{
		$asset->budget_st_main(-1,'budget');
	}
	else {
		$balance=explode(',', $text['value']);
		$text = implode("",$balance);
		$check = 1;
		foreach ($query->result() as $id => $row)
		{
			$available_amount = '';
			if($search != "available_amt") {
				$available_amount=convert_cur($row->bd_balance - $row->consume_amount);
			}
			else {
				$amount=convert_cur($row->bd_balance - $row->consume_amount);
				if(strstr($amount, $text)) {
					$available_amount = $amount;
				}
				if($available_amount != NULL) {
					$check++;
				}			
			}
			if($available_amount != NULL) {
				echo "<tr class=\"tr-ledger\">";
		               	echo "<td class=\"td-ledger\">";
				echo "&nbsp;" .  $row->code . "</td>";
		       	        echo "<td class=\"td-group\">";
		               	echo "&nbsp;" .  $row->budgetname . "</td>";
		               	echo "<td>" . "&nbsp;" .  $row->type . "</td>";
		               	echo "<td>" . "&nbsp;" .  money_format('%!i',$row->bd_balance) . "</td>";
		               	echo "<td>" . "&nbsp;" .  $row->allowedover . "</td>";
		               	echo "<td>" . "&nbsp;" .  money_format('%!i',$available_amount) . "</td>";
			}
		}
		if( $query->num_rows() < 1 )
		{
			$this->messages->add($text['value'] . ' is not found.', 'error');
		}
		if($check == "1" && $search == "available_amt"){
			$this->messages->add($text['value'] . ' is not found.', 'error');
			redirect('budgetl/index');
		}
	}
	echo "</table>";
	echo "</td>";
	echo "</tr>";
	echo "</table>";
?>
