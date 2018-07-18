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

	//$count = "";
	$count = "";
	$this->load->library('reportlist1');
    	$object = new Reportlist1();
	//if(!($print_preview))
	//{
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\">";
        echo "<thead><tr><th>Particulars</th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
	//}else{
	//	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\">";
        //echo "<thead><tr><th>Particulars</th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
	//}
        if($count == 14){
            $curr_total = $object->get_schedule14($code,'view','NULL',$count);
            $prev_amount = $object->prev_amount;
            if($curr_total < 0)
                $curr_total = 0 - $curr_total;
        }else{
		$object->get_income_schedule($code,'view','NULL',$count);
		$curr_total = $object->curr_total;
		$prev_amount = $object->prev_total;
		if($curr_total < 0)
                $curr_total = 0 - $curr_total;
        }
        echo "<tr>";
        echo"<td align=\"left\" class=\"bold\">TOTAL</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($curr_total));
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur(-$prev_amount));
        echo"</td>";
        echo "</tr>";
        echo"</table>";
?>
