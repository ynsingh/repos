<?php

        setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('reportlist1');
	$this->db->from('settings');
        $detail = $this->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);

        $net_dr = 0.00;
        $net_opening_bal = 0.00;
        $net_cr = 0.00;
        $net_total = 0.00;
        $net_current_year = 0.00;
        $net_previous_year = 0.00;
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"5\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GROSS BLOCK</th><th align=\"center\" colspan=\"4\">DEPRECIATION FOR THE YEAR......</th><th align=\"center\" colspan=\"2\">NET BLOCK</th></tr></thead>";

        echo "<tr>";
                echo "<td>S. No.</td>";
		echo "<td>Assets Heads</td>";
                echo "<td>Op Balance <br>01.04.".$fy_start[0]."</td>";
                echo "<td>Addition</td>";
                echo "<td>Deductions</td>";
                echo "<td>CI Balance</td>";
                echo "<td>Dep Opening <br> Balance </td>";
                echo "<td>Depreciation <br> for the Year</td>";
		echo "<td> Deductions/ <br> Adjustment</td>";
		echo "<td> Total <br> Depreciation</td>";
		echo "<td> 31.03.".$fy_end[0]."</td>";
		echo "<td> 31.03.".$fy_end[0]."</td>";
	echo "</tr>";
	$object = new Reportlist1();
	$object->FixedAsset_A('2001',4);
	$opening_balanceA = $object->opening_balance;
	$debit_totalA = $object->debit_total;
	$credit_totalA = $object->credit_total;
	$closing_balanceA = $object->closing_balance;
	$dep_opening_balanceA = $object->dep_opening_balance;
	$current_depreciation_amountA = $object->current_depreciation_amount;
	$total_depreciationA = $object->total_depreciation;
	$current_yearA = $object->curr_amount; 

//	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";

	echo "<tr class=\"tr-group\">";
		echo "<td align=\"center\">";
		echo "</td>";
        	echo "<td class=\"td-group\" align=\"center\">";
                echo "<strong>TOTAL(A)</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($opening_balanceA) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($debit_totalA) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$credit_totalA) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($closing_balanceA) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($dep_opening_balanceA) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_depreciation_amountA) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
		echo "<strong>" . money_format('%!i', convert_cur($total_depreciationA)) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_yearA) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>"; 
		echo "</tr>";

	echo "</table>";
	echo "<br>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
	$object = new Reportlist1();
	$object->FixedAsset_B('2001',4);
	$opening_balanceB = $object->opening_balance;
        $debit_totalB = $object->debit_total;
        $credit_totalB = $object->credit_total;
        $closing_balanceB = $object->closing_balance;
        $dep_opening_balanceB = $object->dep_opening_balance;
        $current_depreciation_amountB = $object->current_depreciation_amount;
        $total_depreciationB = $object->total_depreciation;
	$current_yearB = $object->curr_amount;

	echo "</table>";
	echo "<br>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"5\">GROSS BLOCK</th><th align=\"center\" colspan=\"4\">DEPRECIATION FOR THE YEAR</th><th align=\"center\" colspan=\"2\">NET BLOCK</th></tr></thead>";
	 $id = 131;
         $this->db->select('id,name,code')->from('groups')->where('parent_id', $id);
         $group_detail = $this->db->get();
         $group_result = $group_detail->result();
        echo "<tr>";
                echo "<td>S. No.</td>";
                echo "<td align=\"center\">";
		//echo "<strong>" . "Intangible Assets" . "</strong>";
		foreach($group_result as $row)
	        {
        	        $group_name = $row->name;
                	$group_id = $row->id;
			if($group_name == 'Intangible Assets')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' .  $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));

        	}
		echo "</td>";
                echo "<td align=\"center\">Op Balance 01.04.".$fy_start[0]."</td>";
                echo "<td>Addition</td>";
                echo "<td>Deductions</td>";
                echo "<td>CI Balance</td>";
                echo "<td>Dep Opening <br> Balance </td>";
                echo "<td>Amortization <br> for the Year</td>";
                echo "<td> Deductions/ <br> Adjustment</td>";
                echo "<td> Total <br> Depreciation</td>";
                echo "<td> 31.03.".$fy_end[0]." </td>";
                echo "<td> 31.03.".$fy_end[0]."</td>";
        echo "</tr>";
	$object = new Reportlist1();
        $object->FixedAsset_C('2001',4);
	$opening_balanceC = $object->opening_balance;
        $debit_totalC = $object->debit_total;
        $credit_totalC = $object->credit_total;
        $closing_balanceC = $object->closing_balance;
        $dep_opening_balanceC = $object->dep_opening_balance;
        $current_depreciation_amountC = $object->current_depreciation_amount;
	$total_depreciationC = $object->total_depreciation;
	$current_yearC = $object->curr_amount;

	echo "<tr>";
		echo "<td>";
		echo "</td>";
        	echo "<td align=\"center\">";
		echo "<strong>TOTAL(C)</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($opening_balanceC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($debit_totalC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$credit_totalC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($closing_balanceC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($dep_opening_balanceC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_depreciation_amountC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
		echo "<strong>" . money_format('%!i', convert_cur($total_depreciationC)) . "</strong>";
                //echo "<strong>" . convert_amount_dc($total_depreciation1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_yearC) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
		echo "</td>";
	echo "</table>";

		echo "<br>";
        	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
		echo "<tr class=\"tr-group\">";
		echo "<td align=\"center\" class=\"td-group\" width=\"280\">";
                echo "<strong>GRAND TOTAL(A+B+C)</strong>";
                echo "</td>";
		$total_opening_balance = ($opening_balanceA + $opening_balanceB + $opening_balanceC);
		$total_debit_total = ($debit_totalA + $debit_totalB + $debit_totalC);
		$total_credit_total = ($credit_totalA + $credit_totalB + $credit_totalC);
		$total_closing_balance = ($closing_balanceA + $closing_balanceB + $closing_balanceC);
		$total_dep_opening_balance = ($dep_opening_balanceA + $dep_opening_balanceB + $dep_opening_balanceC);
		$total_current_depreciation_amount = ($current_depreciation_amountA + $current_depreciation_amountB + $current_depreciation_amountC);
		$grand_total_depreciation = ($total_depreciationA + $total_depreciationB + $total_depreciationC);
		$grand_current_year = ($current_yearA + $current_yearB + $current_yearC);
		
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_opening_balance) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_debit_total) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_credit_total) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_closing_balance) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_dep_opening_balance) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_current_depreciation_amount) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
		echo "<strong>" . money_format('%!i', convert_cur($grand_total_depreciation)) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($grand_current_year) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "</tr>";
		echo "</table>";
?>

