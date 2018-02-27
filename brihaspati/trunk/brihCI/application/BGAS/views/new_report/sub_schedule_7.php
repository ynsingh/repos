<?php
	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('reportlist1');
	$num = 1;
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"15\"></th><th>Amount in Rupees</th></tr></thead>";
	$object = new Reportlist1();
	$asset = new Reportlist1();
	$asset->sub_schedule7('2003');
	$current_total = $asset->curr_total;
	echo "<tr>";
        	echo "<td align=\"center\" colspan=\"15\">";
                echo "<strong> TOTAL</strong>";
                echo "</td>";

                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc($current_total) . "</strong>";
                echo "</td>";
	echo "</tr>";
	echo "</table>";
?>
