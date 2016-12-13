<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	
	$this->db->from('settings');
    	$detail = $this->db->get();
    	foreach ($detail->result() as $row)
    	{
        	$date1 = $row->fy_start;
        	$date2 = $row->fy_end;
    	}
    	$fy_start=explode("-",$date1);
    	$fy_end=explode("-",$date2);

    	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
    	$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

	$count = "";	
	$this->load->library('reportlist1');
    	$object = new Reportlist1();
//	if(! $print_preview)
//	{
// 	      echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
//	}else{
		echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
//	}
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"5\">Current Year<br>$curr_year</th><th align=\"center\" colspan=\"3\">Previous Year<br>$prev_year</th></tr></thead>";
        echo "<tr><td></td>
        <td align=\"center\" class=\"bold\">Plan</td><td align=\"center\" class=\"bold\">Non-Plan</td><td align=\"center\" class=\"bold\">Plan Specific Scheme</td>
        <td align=\"center\" class=\"bold\">Total</td>
        <td align=\"center\" class=\"bold\">Plan</td><td align=\"center\"class=\"bold\">Non Plan</td><td align=\"center\" class=\"bold\">Plan Specific Scheme</td>
        <td align=\"center\" class=\"bold\">Total</td></tr>";
        $object->get_exp_schedules($code,'view','NULL',$count);
        $curr_total = $object->curr_sum_total;    
        $curr_plan_total = $object->curr_plan_sum_total;
        $curr_non_plan_total = $object->curr_non_plan_sum_total;
	$curr_plan_sfc_total = $object->curr_plan_sfc_sum_total;
	$prev_total=$object->prev_total;
        $prev_plan_total=$object->prev_plan_total;
        $prev_nonplan_total=$object->prev_nonplan_total;
	$prev_plan_sfc_total=$object->prev_plan_sfc_total;
	$curr_plan_sfc_total = $object->curr_plan_sfc_sum_total;
        echo "<tr><td align=\"left\" class=\"bold\">Total</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_plan_total);
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_non_plan_total);
        echo"</td>";
	echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_plan_sfc_total);
        echo"</td>";

        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_total);
        echo "</td>";
        echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_plan_total))."</td>";
        echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_nonplan_total))."</td>";
	echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_plan_sfc_total))."</td>";
        echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_total))."</td>";
        echo"</tr>";
        echo"</table>";

?>
