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
        //print_r($fy_end);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);

 /*       $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')'; */

        $net_dr = 0.00;
        $net_opening_bal = 0.00;
        $net_cr = 0.00;
        $net_total = 0.00;
        $net_current_year = 0.00;
        $net_previous_year = 0.00;
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"5\">GROSS BLOCK</th><th align=\"center\" colspan=\"4\">DEPRECIATION FOR THE YEAR</th><th align=\"center\" colspan=\"2\">NET BLOCK</th></tr></thead>";

        echo "<tr>";
                echo "<td>S. No.</td>";
                echo "<td>Assets Heads</td>";
                echo "<td>Op Balance 01.04....</td>";
                echo "<td>Addition</td>";
                echo "<td>Deductions</td>";
                echo "<td>CI Balance</td>";
                echo "<td>Dep Opening <br> Balance </td>";
                echo "<td>Depreciation <br> for the Year</td>";
		echo "<td> Deductions/ <br> Adjustment</td>";
                echo "<td> Total <br> Depreciation</td>";
                echo "<td> 31.03.".$fy_end[0]."</td>";
                echo "<td> 31.03.".$fy_start[0]."</td>";
        echo "</tr>";
        $object = new Reportlist1();
	$object->Fixed_OtherA('2001','4D');
	//$object->Fixed_Asset_OthersA('2001','4D');
	//$object->Nonplan_Fixed_Sub_ScheduleA('2001','4D');
	$op_bal1 = $object->opening_bal1;
	$dr_total1 = $object->dr_other_total1;
	$cr_total1 = $object->cr_other_total1;
	$cl_bal1 = $object->closing_sum1;
	//	print_r("closing balance-----------".$cl_bal1);
	$depreciation_opbal1 = $object->dep_opening;
	$current_dep_value1 = $object->current_dep_total ;
	$total_depreciation1 = $object->total_depreciation;
	$current_year_value1 = $object->curr_amount;

//        $object->Fixed_Sub_Schedule('2001',4);
        echo "</table>";
        echo "<br>";
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";

        echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\" align=\"center\" width=\"24%\">";
                echo "<strong>TOTAL</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($op_bal1) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(+$dr_total1) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(-$cr_total1) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($cl_bal1) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($depreciation_opbal1) . "</strong>";
		echo "</td>";
		echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($current_dep_value1) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_depreciation1) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($current_year_value1) . "</strong>";
                echo "</td>";
		echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

		echo "</tr>";

        echo "</table>";
        echo "<br>";
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
        $object = new Reportlist1();
	$object->Fixed_OtherB();

	//$object->Nonplan_Fixed_Sub_ScheduleB(149,'4D');

  	//$object-> Fixed_Asset_OthersB('149',"4D");
	$op_bal2 = $object->opening_bal2;
        $dr_total2 = $object->dr_other_total2;
        $cr_total2 = $object->cr_other_total2;
        $cl_bal2 = $object->closing_sum2;
        $depreciation_opbal2 = $object->dep_opening2;
        $current_dep_value2 = $object->current_dep_total2;
        $total_depreciation2 = $object->total_depreciation2;
	$current_year_value2 = $object->curr_amount2;

        echo "</table>";
	   echo "<br>";
                echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
                echo "<tr class=\"tr-group\">";
                echo "<td align=\"center\" class=\"td-group\" width=\"24%\">";
                echo "<strong>GRAND TOTAL</strong>";
                echo "</td>";
		
		$total_opening_balance = ($op_bal1 + $op_bal2);
		$total_debit_total = ($dr_total1 + $dr_total2);
		$total_credit_total = ($cr_total1 + $cr_total2);
		$total_closing_balance = ($cl_bal1 + $cl_bal2);
		$total_dep_opening_balance = ($depreciation_opbal1 + $depreciation_opbal2);
		$total_current_depreciation_amount = ($current_dep_value1 + $current_dep_value2);
		$grand_total_depreciation = ($total_depreciation1 + $total_depreciation2);
		$grand_current_year = ($current_year_value1 + $current_year_value2);
		

                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_opening_balance) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_debit_total) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_credit_total) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_closing_balance) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_dep_opening_balance) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($total_current_depreciation_amount) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($grand_total_depreciation) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
		echo "<strong>" . convert_amount_dc($grand_current_year) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
                echo "</table>";
?>

