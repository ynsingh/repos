<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('balancesheet');
	
	$i = 0;
	if($id != '')
                $i = $id;

	$this->db->from('groups');
	$this->db->select('id, name')->where('parent_id', $i);
	$group = $this->db->get();
		
	foreach($group->result() as $row){

		echo "<br><br>";
		echo "<strong>" . $row->name . "</strong>";
		echo "<br><br>";
		echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
	      	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
		$object = new Balancesheet();
		$object->init($row->id);
		$object->schedule_five();
		$dr_total = $object->dr_total;
		$cr_total = $object->cr_total;
		$old_cr_total = $object->old_cr_total;
		$old_dr_total = $object->old_dr_total;

		echo "<tr>";
			echo "<td width=40%>";
				echo "<b>Total</b>";
			echo "</td>";

                	echo "<td width=15% align=\"right\">";
                        	echo  money_format('%!i', convert_cur($dr_total));
	                echo "</td>";
	
        	        echo "<td width=15% align=\"right\">";
                	        echo  money_format('%!i', convert_cur($cr_total));
	                echo "</td>";

        	        echo "<td width=15% align=\"right\">";
                	        echo  money_format('%!i', convert_cur($old_dr_total));
	                echo "</td>";

        	        echo "<td width=15% align=\"right\">";
                	        echo  money_format('%!i', convert_cur($old_cr_total));
	                echo "</td>";
		echo "</tr>";
		echo "</table>";
		echo "<br><br><br>";
	}

?>
