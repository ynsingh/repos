<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('balancesheet');

        $i = 0;
	if($id != '')
                $i = $id;

	$fy_start=explode("-",$start_date);
        $fy_end=explode("-",$end_date);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
	
	echo "<br>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" >HEAD OF ACCOUNT</th><th align=\"center\" colspan=\"2\">OPENING BALANCE <br>AS ON 01.04.".$fy_start[0]."</th><th align=\"center\" colspan=\"2\">TRANSACTIONS DURING THE YEAR <br>".$curr_year."</th><th align=\"center\" colspan=\"2\">CLOSING BALANCE <br>AS ON 31.03.".$fy_end[0]."</th></tr></thead>";
		echo "<tr><td colspan = 2></td><td align=\"center\" >DR.</td><td align=\"center\" >CR.</td><td align=\"center\" >DR.</td><td align=\"center\" >CR.</td><td align=\"center\" >DR.</td><td align=\"center\" >CR.</td></tr>";	

		$object = new Balancesheet();
		$object->init($i);	
		$object->sub_schedule();

	echo "</table>";

?>
