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
	
//	$count = 17;
	$this->load->library('reportlist1');
    	$object = new Reportlist1();
	//if(! $print_preview)
	//{
        //echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	//}else{
		echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	//}
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"6\">Current Year<br>$curr_year</th><th align=\"center\" colspan=\"3\">Previous Year<br>$prev_year</th></tr></thead>";
        echo "<tr><td></td>
        	<td align=\"center\" class=\"bold\">Plan</td><td align=\"center\" class=\"bold\">Non-Plan</td><td align=\"center\" class=\"bold\">Plan Specific Scheme</td>
            	<td align=\"center\" class=\"bold\">Total</td>
            	<td align=\"center\" class=\"bold\">Plan</td><td align=\"center\"class=\"bold\">Non Plan</td><td align=\"center\" class=\"bold\">Plan Specific Scheme</td>
            	<td align=\"center\" class=\"bold\">Total</td></tr>";

        echo "<tr><td class=\"bold\" colspan=\"7\">A Infrastructure</td></tr>";
	//$object->schedule17('400301','view','NULL',$count,'A');
        $object->schedule17($code,'view','NULL',$count,'A');
	$prev_total1=(float)$object->prev_total;
        $prev_plan_total1=(float)$object->prev_plan_total;
        $prev_nonplan_total1=(float)$object->prev_nonplan_total;
        $prev_plan_sfc_total1=(float)$object->prev_plan_sfc_total;
        echo "<tr><td class=\"bold\" colspan=\"7\">B Communication</td></tr>";
	//$object->schedule17('400302','view','NULL',$count,'B');
        $object->schedule17($code,'view','NULL',$count,'B');
	$prev_total2=(float)$object->prev_total;
        $prev_plan_total2=(float)$object->prev_plan_total;
        $prev_nonplan_total2=(float)$object->prev_nonplan_total;
        $prev_plan_sfc_total2=(float)$object->prev_plan_sfc_total;

        echo "<tr><td class=\"bold\" colspan=\"7\">C Others</td></tr>";
        $object->schedule17($code,'view','NULL',$count,'C');
	$curr_total = (float)$object->curr_sum_total;
        $curr_plan_total = (float)$object->curr_plan_sum_total;
        $curr_non_plan_total = (float)$object->curr_non_plan_sum_total;
	$curr_plan_sfc_total=(float)$object->curr_plan_sfc_sum_total;

	$prev_total3=(float)$object->prev_total;
	$prev_plan_total3=(float)$object->prev_plan_total;
	$prev_nonplan_total3=(float)$object->prev_nonplan_total;
	$prev_plan_sfc_total3=(float)$object->prev_plan_sfc_total;

	$prev_sum=$prev_total1+$prev_total2+$prev_total3;
	$prev_plan_sum=$prev_plan_total1+$prev_plan_total2+$prev_plan_total3;
	$prev_nonplan_sum=$prev_nonplan_total1+$prev_nonplan_total2+$prev_nonplan_total3;
	$prev_plan_sfc_sum=$prev_plan_sfc_total1+$prev_plan_sfc_total2+$prev_plan_sfc_total3;
        
        echo "<tr><td align=\"left\" class=\"bold\">Total</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_plan_total);
        //echo money_format('%!i', convert_cur($curr_plan_total));
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_non_plan_total);
       // echo money_format('%!i', convert_cur($curr_non_plan_total));
        echo"</td>";
	echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($curr_plan_sfc_total));
        echo"</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($curr_total);
       // echo money_format('%!i', convert_cur($curr_total));
        echo "</td>";
        echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_plan_sum))."</td>";
        echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_nonplan_sum))."</td>";
        echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_plan_sfc_sum))."</td>";
	echo"<td align=\"right\" class=\"bold\">".money_format('%!i', convert_cur($prev_sum))."</td>";
	
        echo"</tr>";
        echo"</table>";

?>
