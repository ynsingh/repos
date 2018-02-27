<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	
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

	$this->load->library('reportlist1');
    $object = new Reportlist1();
	if(! $print_preview)
	{
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><th></th><th align=\"center\">Gratuity</th><th align=\"center\">Leave Encashment</th><th align=\"center\">Pension</th><th align=\"center\">DCRG</th><th align=\"center\">Commutation</th><th align=\"center\">Total</th></tr></thead>";
	}else{
		echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><th></th><th align=\"center\">Gratuity</th><th align=\"center\">Leave Encashment</th><th align=\"center\">Pension</th><th align=\"center\">DCRG</th><th align=\"center\">Commutation</th><th align=\"center\">Total</th></tr></thead>";
	}
    $object->schedule15A($code,$count);

	echo"</table>";
?>
