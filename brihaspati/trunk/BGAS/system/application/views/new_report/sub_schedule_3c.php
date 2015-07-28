<?php
	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('reportlist1');

        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start = explode("-",$date1);
        $fy_end = explode("-",$date2);
        $curr_year = '('.$fy_start[0]."-".$fy_end[0].')';
        $prev_year = '('.($fy_end[0]-1)."-".($fy_end[0]-1).')';

        $dr_total = 0.00;
        $cr_total = 0.00;
        $old_dr_total = 0.00;
        $old_cr_total = 0.00;
        echo "<table border=0 class=\"simple-table balance-sheet-table\">";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
	$liability = new Reportlist1();
        $liability->sub_schedule_3c();

	echo "</table>";


?>
