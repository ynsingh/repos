<?php
	echo form_open('account/index');
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
	$this->load->model('Ledger_model');
	$this->load->library('accountlist');
	echo "<table>";
	echo "<tr valign=\"top\">";
	$asset = new Accountlist();
	echo "<td>";
	$asset->init(0);
	echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
	echo "<thead><tr><th>Account Code </th><th>Account Name</th><th>Type</th><th>O/P Balance</th><th>C/L Balance</th><th></th></tr></thead>";
	if($search == '')
	{
		$asset->account_st_main(-1);
	}
	else 
	{
		if($search == "Select")
		{
			$this->messages->add('Please select search type from drop down list.', 'error');
			redirect('account/index');
		}
		else {
			$check = 1;
			$field = $search . '      ' . 'LIKE';
			$query2;
			$gr_account;
			if($search != "cl_balance" && $search != "op_balance") {
				if($search != "type") {
					$a = trim($text['value']);
					$this->db->from('groups')->where($field, '%' . $a . '%');
					$query2 = $this->db->get();

				} else {
					$this->db->from('groups');
					$query2 = $this->db->get();
				}
				foreach ($query2->result() as $id => $row)
				{
					$op_balance = '-';
					$clbalance = '-';
					if($search == "type") {
						$b = trim($text['value']);
						$item = "group account";
						$str = strtolower($b);
						if (strstr($item, $str) !== false) {
							echo "<tr class=\"tr-group\">";
							echo "<td class=\"td-group\">";
							if ($row->id <= 4)
								echo "&nbsp;<strong>" . $row->code . "</strong>" . "</td>";
							else
								echo "&nbsp;" . $row->code . "</td>";
							echo "<td class=\"td-group\">";
							if ($row->id <= 4)
								echo "&nbsp;<strong>" . $row->name . "</strong>" . "</td>";
							else
								echo "&nbsp;" . $row->name . "</td>";
								echo "<td>" . "Group Account" . "</td>";
							echo "<td>" . $op_balance . "</td>";
							echo "<td>" . $clbalance . "</td>";
							if ($row->id <= 4)
							{
								echo "<td class=\"td-actions\"></tr>";
							} else {
								echo "<td class=\"td-actions\">" . anchor('group/edit/' . $row->id , "Edit", array('title' => 'Edit Group', 'class' => 'red-link'));
								$id1=$row->id;
								$status1=$row->status;
								if (  check_access('administer'))
								{
									if($row->status == 0)				
										echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Hide',  array('title' => 'Edit Group', 'class' => 'red-link')) ;
									else
										echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Unhide', array('title' => 'Edit Group', 'class' => 'red-link')) ;
								}
								echo " &nbsp;" . anchor('group/delete/' . $row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete group')), array('class' => "onClick", 'title' => "Delete Group")) . "</td>";
							}
						$check++;
						}
					}
					else {
						echo "<tr class=\"tr-group\">";
						echo "<td class=\"td-group\">";
						if ($row->id <= 4)
							echo "&nbsp;<strong>" . $row->code . "</strong>" . "</td>";
						else
							echo "&nbsp;" . $row->code . "</td>";
							echo "<td class=\"td-group\">";
						if ($row->id <= 4)
							echo "&nbsp;<strong>" . $row->name . "</strong>" . "</td>";
						else
							echo "&nbsp;" . $row->name . "</td>";
						echo "<td>" . "Group Account" . "</td>";
						echo "<td>" . $op_balance . "</td>";
						echo "<td>" . $clbalance . "</td>";
						if ($row->id <= 4)
						{
							echo "<td class=\"td-actions\"></tr>";
						} else {
							echo "<td class=\"td-actions\">" . anchor('group/edit/' . $row->id , "Edit", array('title' => 'Edit Group', 'class' => 'red-link'));
							$id1=$row->id;
							$status1=$row->status;
							if (  check_access('administer'))
							{
							if($row->status == 0)				
								echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Hide',  array('title' => 'Edit Group', 'class' => 'red-link')) ;
							else
								echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Unhide', array('title' => 'Edit Group', 'class' => 'red-link')) ;
							}
							echo " &nbsp;" . anchor('group/delete/' . $row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete group')), array('class' => "onClick", 'title' => "Delete Group")) . "</td>";
						}
						$check++;
					}
				}
			}
			$query;
			$text;
			$balance=explode(',', $text['value']);
			$text = implode("",$balance);
			if($search != "cl_balance" && $search != "type") {
				$a = trim($text);
				$this->db->from('ledgers')->where($field, '%' . $a . '%');
				$query = $this->db->get();
			}
			else {
				$this->db->from('ledgers');
				$query = $this->db->get();
			}
		
			foreach ($query->result() as $id => $row)
			{
				$cl_balance = '';
				$ld_account = '';
				list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row->id); /* Opening Balance */
				$clbalance = $this->Ledger_model->get_ledger_balance($row->id); /* Final Closing Balance */
				if($search != "type") {
					$ld_account = "Ledger Account";
				}
				else {
					$item = "ledger account";
					$b = trim($text);
					$str = strtolower($b);
					if (strstr($item, $str) !== false) {
						$ld_account = $item;
					}
				}
				if($search == "cl_balance") {
					$c = trim($text);
					$amount = $this->Ledger_model->get_ledger_balance($row->id);
					$amount1 = number_format((float)$amount, 2, '.', '');
					if(strstr($amount1, $c)) {
						echo "<tr class=\"tr-ledger\">";
						echo "<td class=\"td-ledger\">";
						echo "&nbsp;" . anchor('report/ledgerst/' . $row->id, $row->code, array('title' => $row->code . ' Ledger Statement', 'style' => 'color:#000000'));$row->code . "</td>";
						echo "<td>" . "&nbsp;" . anchor('report/ledgerst/' . $row->id, $row->name, array('title' => $row->name . ' Ledger Statement', 'style' => 'color:#000000')) . "</td>";
						echo "<td>" . "Ledger Account" . "</td>";
						echo "<td>" . convert_opening($opbalance, $optype) . "</td>";
						echo "<td>" . convert_amount_dc($amount) . "</td>";
						echo "<td class=\"td-actions\">" . anchor('ledger/edit/' . $row->id, 'Edit', array('title' => "Edit Ledger", 'class' => 'red-link'));
						echo " &nbsp;" . anchor('ledger/delete/' . $row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Ledger')), array('class' => "confirmClick", 'title' => "Delete Ledger")) . "</td>";
						echo "</tr>";
						$check++;
					}
				}
				else {
					if($ld_account != NULL) {
						echo "<tr class=\"tr-ledger\">";
						echo "<td class=\"td-ledger\">";
						echo "&nbsp;" . anchor('report/ledgerst/' . $row->id, $row->code, array('title' => $row->code . ' Ledger Statement', 'style' => 'color:#000000'));$row->code . "</td>";
						echo "<td>" . "&nbsp;" . anchor('report/ledgerst/' . $row->id, $row->name, array('title' => $row->name . ' Ledger Statement', 'style' => 'color:#000000')) . "</td>";
						echo "<td>" . "Ledger Account" . "</td>";
						echo "<td>" . convert_opening($opbalance, $optype) . "</td>";
						echo "<td>" . convert_amount_dc($clbalance) . "</td>";
						echo "<td class=\"td-actions\">" . anchor('ledger/edit/' . $row->id, 'Edit', array('title' => "Edit Ledger", 'class' => 'red-link'));
						echo " &nbsp;" . anchor('ledger/delete/' . $row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Ledger')), array('class' => "confirmClick", 'title' => "Delete Ledger")) . "</td>";
						echo "</tr>";
						$check++;
					}
				}
				
			}
			echo "</tr>";

			if($check == "1" && $search == "code")
			{
				$this->messages->add($text . ' is not found.', 'error');
				redirect('account/index');
			}
			if($check == "1" && $search == "name")
			{
				$this->messages->add($text . ' is not found.', 'error');
				redirect('account/index');
			}
			if($check == "1" && $search == "cl_balance"){
				$this->messages->add($text . ' is not found.', 'error');
				redirect('account/index');
			}
			if($check == "1" && $search == "op_balance"){
				$this->messages->add($text . ' is not found.', 'error');
				redirect('account/index');
			}
			if($check == "1" && $search == "type"){
				$this->messages->add($text . ' is not found.', 'error');
				redirect('account/index');
			}
		}
	}
	echo "</table>";
	echo "</td>";
	echo "</tr>";
	echo "</table>";
?>
