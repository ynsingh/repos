<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	
	$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

	$this->load->library('reportlist');
        $object = new Reportlist();
	if(! $print_preview)
	{
        	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	}else{
		echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"70%\" >";
	}
        echo "<thead><tr><th></th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";

        $object->get_IE_schedule($code,'view','NULL',$count);
        $total = $object->curr_total;
	$prev_year_total = $object->prev_total;
        echo "<tr>";
	echo"<td>Total</td>";
	echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($total));
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
	echo money_format('%!i', convert_cur($prev_year_total));
	echo "</td>";
        echo "</tr>";
        echo"</table>";

?>
