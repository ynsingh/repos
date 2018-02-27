<?php
	//$this->load->library('accountlist');
	echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"50%\">";
	echo "<thead><tr><th>S.No</th><th>Budget / Ledger Code</th><th> Budget / Ledger Name</th></tr></thead>";
	echo "<tbody>";
		$this->db->select('code,budgetname');
		$this->db->from('payrollsetup');
		$setupdetail = $this->db->get();
		$i=1;
		foreach ($setupdetail->result() as $row)
		{
			echo "<tr>";
			echo "<td>" . $i . '.' . "</td>";
		        echo "<td>" . $row->code . "</td>";
			echo "<td>" . $row->budgetname . "</td>";
			echo "</tr>";
			$i++;
		}
	echo "</tbody>";
        echo "</table>";
?>
