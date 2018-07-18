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

	$count = 9;
    	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
    	$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

	$this->load->library('reportlist1');
    	$object = new Reportlist1();
//	if(! $print_preview)
//	{
//	      echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
//	}else{
		echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
//	}
        echo "<thead><tr><th></th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
        echo "<tr><td colspan=\"3\" align=\"left\" class=\"bold\">FEES FROM STUDENTS</td></tr>";

        echo "<tr><td colspan=\"3\" align=\"left\" class=\"bold\">Academic</td></tr>";
        $total1 = $object->schedule9($code,'view','NULL',$count,'A');
        $sum = 0-($object->sum);
	$prev_total1= 0-($object->total);
        echo "<tr><td align=\"center\" class=\"bold\">Total(A)</td>";
        echo"<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($sum));
        echo"</td>";
	echo"<td align=\"right\" class=\"bold\">".convert_cur($prev_total1)."</td>";
        echo"</tr>";

        echo "<tr><td colspan=\"3\" align=\"left\" class=\"bold\">Examinations</td></tr>";
        $total2 = $object->schedule9($code,'view','NULL',$count,'B');
        $sum = 0-($object->sum);
	$prev_total2= 0-($object->total);
        echo "<tr><td align=\"center\" class=\"bold\">Total(B)</td>";
        echo"<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($sum));
        echo"</td>";
	echo"<td align=\"right\" class=\"bold\">".convert_cur($prev_total2)."</td>";
        echo"</tr>";
        
        echo "<tr><td colspan=\"3\" align=\"left\" class=\"bold\">Other Fees</td></tr>";
        $total3 = $object->schedule9($code,'view','NULL',$count,'C');
        $sum = 0-($object->sum);
	$prev_total3= 0-($object->total);
        echo "<tr><td align=\"center\" class=\"bold\">Total(C)</td>";
        echo"<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($sum));
        echo"</td>";
	echo"<td align=\"right\" class=\"bold\">".convert_cur($prev_total3)."</td>";
        echo"</tr>";

        echo "<tr><td colspan=\"3\" align=\"left\" class=\"bold\">Sale of Publications</td></tr>";
        $total4 = $object->schedule9($code,'view','NULL',$count,'D');
        $sum = 0-($object->sum);
	$prev_total4= 0-($object->total);
        echo "<tr><td align=\"center\" class=\"bold\">Total(D)</td>";
        echo"<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($sum));
        echo"</td>";
	echo"<td align=\"right\" class=\"bold\">".convert_cur($prev_total4)."</td>";
        echo"</tr>";
        
        echo "<tr><td colspan=\"3\" align=\"left\" class=\"bold\">Other Academic Receipts</td></tr>";
        $total5 = $object->schedule9($code,'view','NULL',$count,'E');
        $sum = 0-($object->sum);
	$prev_total5= 0-($object->total);

        $total = 0 -($total1 + $total2 + $total3 + $total4 + $total5);
	$total1 = $prev_total1 + $prev_total2 + $prev_total3 + $prev_total4 + $prev_total5;
        echo "<tr><td align=\"center\" class=\"bold\">Total(E)</td>";
        echo"<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($sum));
        echo"</td>";
	echo"<td align=\"right\" class=\"bold\">".convert_cur($prev_total5)."</td>";
        echo"</tr>";
        echo "<tr>";
        echo"<td align=\"center\" class=\"bold\">GRAND TOTAL(A+B+C+D+E)</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($total));
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
	echo convert_cur($total1);
        echo"</td>";
        echo "</tr>";
        echo"</table>";

?>
