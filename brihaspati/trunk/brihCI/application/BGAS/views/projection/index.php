<?php
	echo form_open('projectionl/index');
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
	$asset->init(1);
	echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
        echo "<thead><tr><th>Projection Code </th><th>Projection Name</th><th>Projection Type</th><th>B/D Balance</th><th>Unearned Projection</th><th></th></tr></thead>";
	if($search == '')
	{
        	$asset->budget_st_main(-1, 'projection');
	}
	else {
		$balance=explode(',', $text['value']);
		$text = implode("",$balance);
		$check = 1;
		foreach ($query->result() as $id => $row)
		{
			$available_amount = '';
			if($search != "unearned_proj") {
				$available_amount=$row->bd_balance - $row->earned_amount;
			}
			else {
				$amount = convert_cur($row->bd_balance - $row->earned_amount);
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
		               	echo "&nbsp;" .  $row->projection_name . "</td>";
		               	echo "<td>" . "&nbsp;" .  $row->type . "</td>";
		               	echo "<td>" . "&nbsp;" .  money_format('%!i',$row->bd_balance) . "</td>";
		               	echo "<td>" . "&nbsp;" . money_format('%!i',$available_amount) . "</td>";
			}
		}
		if( $query->num_rows() < 1 )
		{
			$this->messages->add($text['value'] . ' is not found.', 'error');
		}
		if($check == "1" && $search == "unearned_proj"){
			$this->messages->add($text['value'] . ' is not found.', 'error');
			redirect('projectionl/index');
		}
	}
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
?>
