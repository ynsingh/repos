<?php 
 	echo form_open('entry/printallentry/');

	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Bill/Voucher Date From', 'entry_date1');
	echo " ";
	echo form_input_date_restrict($entry_date1);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "<span id=\"tooltip-target-2\">";
	echo form_label('To Bill/Voucher Date', 'entry_date2');
	echo " ";
	echo form_input_date_restrict($entry_date2);
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_submit('submit', 'Get');
	echo " ";
	echo "</p>";	
	echo form_close();
?>

<table border=0 cellpadding=5 class="simple-table">
	<thead>
		<tr>
			<th>Fwd Ref Id</th>
                        <th>Bkwd Ref Id</th>
			<th>Date</th>
			<th>Update Date</th>
			<th>No</th>
			<th>Ledger Account</th>
			<th>Type</th>
			<th>DR Amount</th>
			<th>CR Amount</th>

		</tr>
	</thead>
	<tbody>
	<?php
	echo"</br>";
		foreach ($detail->result() as $id => $row)
		{
			$current_entry_type = entry_type_info($row->entry_type);
			echo "<tr>";
	
			echo "<td>" . $row->forward_refrence_id . "</td>";
                        echo "<td>" . $row->backward_refrence_id . "</td>";
			echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
			echo "<td>" . date_mysql_to_php_display($row->update_date) . "</td>";
			echo "<td>" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $row->id, full_entry_number($row->entry_type, $row->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";

			echo "<td>";
			echo $this->Tag_model->show_entry_tag($row->tag_id);
			echo $this->Ledger_model->get_entry_name1($row->id, $row->entry_type);
			echo "</td>";

			echo "<td>" . $current_entry_type['name'] . "</td>";
			echo "<td>" . $row->dr_total . "</td>";
			echo "<td>" . $row->cr_total . "</td>";

			echo "</tr>";
		}
	        echo "</table>";
	 	echo "<br />";
		echo "<br />";

?> 
	<input class="hide-print" type="button" onClick="window.print()" value="Print Voucher">
	<input class="hide-print" type="button" onClick="window.back()" value="Back">
