<div id="tag-sidebar">
	<?php $this->load->view('sidebar/tag', $tag_id);?>
</div>
<?php
	echo "<li>" . anchor('entry/sort/'.$entry_sort , 'Sorting by Updatedate') . "</li>";
?>
<?php
	echo form_open('entry/' . $entry_path);
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
<table border=0 cellpadding=5 class="simple-table">
	<thead>
		<tr>
			<!--Ids added by Priyanka-->
			<th>Id</th>
			<th>Fwd Ref Id</th>
			<th>Bkwd Ref Id</th>
			<th>Date</th>
			<th>Update Date</th>
			<th>No</th>
			<th>Ledger Account</th>
			<th>Type</th>
			<th>DR Amount</th>
			<th>CR Amount</th>
			<th>Submitted By</th>
			<th>Verified By</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<?php
		$check = 1;
		foreach ($entry_data->result() as $row)
		{
			$status = $row->status;
			$current_entry_type = entry_type_info($row->entry_type);
			$value = '';
			if($search != "name") {
				$value = $this->Ledger_model->get_entry_name($row->id, $row->entry_type);
			}
			else {
				$value = $this->Ledger_model->get_entry_name_match($row->id, $row->entry_type, $text['value']);
				if($value != NULL) {
					$check++;
				}
			}
			if($value != NULL) {
				echo "<tr>";

				//Ids added by Priyanka
				echo "<td>" . $row->id . "</td>";
				echo "<td>" . $row->forward_refrence_id . "</td>";
				echo "<td>" . $row->backward_refrence_id . "</td>";

				echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
				echo "<td>" . date_mysql_to_php_display($row->update_date) . "</td>";
				echo "<td>" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $row->id, full_entry_number($row->entry_type, $row->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";

				echo "<td>";
				echo $this->Tag_model->show_entry_tag($row->tag_id);
				echo $value . "<br>";
				echo "Narration: " . $row->narration;
				echo "</td>";

				echo "<td>" . $current_entry_type['name'] . "</td>";
				echo "<td>" . $row->dr_total . "</td>";
				echo "<td>" . $row->cr_total . "</td>";
				echo "<td>" . $row->submitted_by . "</td>";
				echo "<td>" ;

	//			echo "<td>" . anchor('entry/edit/' . $current_entry_type['label'] . "/" . $row->id , "Edit", array('title' => 'Edit ' . $current_entry_type['name'] . ' Entry', 'class' => 'red-link')) . " ";
	//			echo " &nbsp;" . anchor('entry/delete/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete ' . $current_entry_type['name'] . ' Entry', 'class' => "confirmClick", 'title' => "Delete entry")), array('title' => 'Delete  ' . $current_entry_type['name'] . ' Entry')) . " ";
				if($status != 1){
					echo " &nbsp;" . anchor('entry/verify/' . $current_entry_type['label'] . "/" . $row->id,'Verify' ,  array('title' => 'Verify ' . $current_entry_type['name']. ' Entry', 'class' => 'anchor-link-a')) . " ";
				}else{
					echo $row->verified_by. " ";	
				}
				echo " &nbsp;" . anchor_popup('entry/printpreview/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/print.png", 'border' => '0', 'alt' => 'Print ' . $current_entry_type['name'] . ' Entry')), array('title' => 'Print ' . $current_entry_type['name']. ' Entry', 'width' => '600', 'height' => '600')) . " ";
				echo " &nbsp;" . anchor_popup('entry/email/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/email.png", 'border' => '0', 'alt' => 'Email ' . $current_entry_type['name'] . ' Entry')), array('title' => 'Email ' . $current_entry_type['name'] . ' Entry', 'width' => '500', 'height' => '300')) . " ";
				echo " &nbsp;" . anchor('entry/download/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/save.png", 'border' => '0', 'alt' => 'Download ' . $current_entry_type['name'] . ' Entry', 'title' => "Download entry")), array('title' => 'Download  ' . $current_entry_type['name'] . ' Entry')) . "</td>";

				echo "</tr>";
			}
		}	
		if($check == "1" && $search == "name"){
			$this->messages->add($text['value'] . ' is not found.', 'error');
			redirect('entry/' . $entry_path);
		}
	?>
	</tbody>
</table>

<div id="pagination-container"><?php 
if($search == "") {
echo $this->pagination->create_links();
}
 ?></div>

