<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('balancesheet');
	$count = 5;
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
        $liability = new Balancesheet(); 

		echo "<br><br>";
		echo "<strong>Secured</strong>";
                echo "<br><br>";
		echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
	      	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
		
		$liability->schedule_five('12',5,'100301','view','NULL');
		$dr_total1 = $liability->dr_total1;
		$cr_total1 = $liability->cr_total1;
		$prev_sum = $liability->prev_sum;
		$prev_sum1 = $liability->prev_sum1;
		echo "<tr>";
		echo "<td width=40%>";
			echo "<b>Total</b>";
		echo "</td>";

                echo "<td width=15% align=\"right\">";
                echo "<strong>".convert_amount_dc(+$dr_total1)."</strong>";
	        echo "</td>";
	
        	echo "<td width=15% align=\"right\">";
         	echo  "<strong>".convert_amount_dc(-$cr_total1)."</strong>";
	        echo "</td>";

        	echo "<td width=15% align=\"right\">";
           	echo "<strong>".convert_amount_dc(+$prev_sum)."</strong>";
	        echo "</td>";

        	echo "<td width=15% align=\"right\">";
              	echo "<strong>".convert_amount_dc(-$prev_sum1)."</strong>";
	        echo "</td>";
		echo "</tr>";
		echo "</table>";
		echo "<br><br><br>";  
		
		echo "<br><br>";
                echo "<strong>Unsecured</strong>";
		echo "<br><br>";
		echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
                echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
		
		 $liability->schedule_five('13',5,'100302','view','NULL');
		 $dr_total1 = $liability->dr_total1;
                 $cr_total1 = $liability->cr_total1;
                 $prev_sum = $liability->prev_sum;
                 $prev_sum1 = $liability->prev_sum1;

	
		 echo "<tr>";
                 echo "<td width=40%>";
                 	echo "<b>Total</b>";
                 echo "</td>";

                 echo "<td width=15% align=\"right\">";
                 echo "<strong>".convert_amount_dc(+$dr_total1)."</strong>";
                 echo "</td>";
        
                 echo "<td width=15% align=\"right\">";
                 echo "<strong>".convert_amount_dc(-$cr_total1)."</strong>";
                 echo "</td>";

                 echo "<td width=15% align=\"right\">";
                 echo "<strong>".convert_amount_dc(+$prev_sum)."</strong>";
                 echo "</td>";

                 echo "<td width=15% align=\"right\">";
                 echo "<strong>".convert_amount_dc(-$prev_sum1)."</strong>";
                 echo "</td>";
                 echo "</tr>";  
                 echo "</table>";
                 echo "<br><br><br>";
?>
