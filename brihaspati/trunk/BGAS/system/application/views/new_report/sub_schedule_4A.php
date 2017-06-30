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

/*        $this->load->library('session');
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
                echo "<td align=\"center\">Assets Heads</td>";
                echo "<td align=\"center\">Op Balance 01.04.".$fy_start[0]."</td>";
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
        $object->Plan_Fixed_Sub_ScheduleA('2001','4A');
	$op_balance1 = $object->opening_bal1;
	$dr_total1 = $object->dr_plan_total1;
	$cr_total1 = $object->cr_plan_total1;
	$closing_total1 = $object->closing_sum1;
	$depreciation_opbal1 = $object->dep_opening;
	$current_dep_amount1 = $object->current_dep_total;
	$total_dep1 = $object->total_depreciation;
	$current_year_value1 = $object->curr_amount;
        //echo "</table>";
        //echo "<br>";
        //echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";

        echo "<tr class=\"tr-group\">";
		echo "<td>";
		echo "</td>";
                echo "<td class=\"td-group\" align=\"center\" width=\"280\">";
                echo "<strong>TOTAL(A)</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($op_balance1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$dr_total1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$cr_total1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($closing_total1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($depreciation_opbal1) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_dep_amount1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_dep1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_year_value1) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "</tr>";

        echo "</table>";
        echo "<br>";
	 echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
        $object = new Reportlist1();
        $object->Plan_Fixed_Sub_ScheduleB(149,'4A');
	$op_balance2 = $object->opening_bal2;
        $cr_total2 = $object->cr_plan_total2;
        $dr_total2 = $object->dr_plan_total2;
        $closing_total2 = $object->closing_sum2;
        $depreciation_opbal2 = $object->dep_opening2;
        $current_dep_amount2 = $object->current_dep_total2;
        $total_dep2 = $object->total_depreciation2;
	$current_year_value2 = $object->curr_amount2;
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
                echo "<td>";
                //echo "Intangible Assets";
                foreach($group_result as $row)
                {
                        $group_name = $row->name;
                        $group_id = $row->id;
                        if($group_name == 'Intangible Assets')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp; $group_name";
			// . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));

                }  
                echo "</td>";
                echo "<td>Op Balance 01.04....</td>";
                echo "<td>Addition</td>";
                echo "<td>Deductions</td>";
                echo "<td>CI Balance</td>";
                echo "<td>Dep Opening <br> Balance </td>";
		 echo "<td>Amortization <br> for the Year</td>";
                echo "<td> Deductions/ <br> Adjustment</td>";
                echo "<td> Total <br> Depreciation</td>";
                echo "<td> 31.03.".$fy_end[0]." </td>";
                echo "<td> 31.03.".$fy_start[0]."</td>";
        echo "</tr>";
		$object = new Reportlist1();
	        $object->Plan_Fixed_Sub_ScheduleC(152);
		$op_balance3 = $object->opening_bal3;
        	$dr_total3 = $object->dr_plan_total3;
        	$cr_total3 = $object->cr_plan_total3;
        	$closing_total3 = $object->closing_sum3;
        	$depreciation_opbal3 = $object->dep_opening3;
        	$current_dep_amount3 = $object->current_dep_total3;
        	$total_dep3 = $object->total_depreciation3;
        	$current_year_value3 = $object->curr_amount3;
		
		echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td align=\"center\">";
                echo "<strong>TOTAL(C)</strong>";
                echo "</td>";

		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($op_balance3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$dr_total3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$cr_total3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($closing_total3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($depreciation_opbal3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_dep_amount3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total_dep3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_year_value3) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

                echo "</tr>";
		echo "</table>";

                echo "<br>";
                echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
                echo "<tr class=\"tr-group\">";
                echo "<td align=\"center\" class=\"td-group\" width=\"24%\">";
                echo "<strong>GRAND TOTAL(A+B+C)</strong>";
                echo "</td>";

		$total_opening_balance = ($op_balance1 + $op_balance2 + $op_balance3);
		$total_debit_total = ($dr_total1 + $dr_total2 + $dr_total3);
		$total_credit_total = ($cr_total1 + $cr_total2 + $cr_total3);
		$total_closing_balance = ($closing_total1 + $closing_total2 + $closing_total3);
		$total_dep_opening_balance = ($depreciation_opbal1 + $depreciation_opbal2 + $depreciation_opbal3);
		$total_current_depreciation_amount = ($current_dep_amount1 + $current_dep_amount2 + $current_dep_amount3);
		$grand_total_depreciation = ($total_dep1 + $total_dep2 + $total_dep3);
		$grand_current_year = ($current_year_value1 + $current_year_value2 + $current_year_value3);
		
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
		echo "<strong>" . money_format('%!i', convert_cur($grand_total_depreciation)) . "</strong>";
                echo "</td>";

		echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc($grand_current_year) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\" width=\"7%\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";

                echo "</tr>";
                echo "</table>";
	echo "</table>";
?>
