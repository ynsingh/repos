<?php
	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('balancesheet');
	$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
		echo "<br><br>";  
		echo "<strong> Long Term Investments </strong>";
		echo "<br><br><br>";		
		echo "Investments From Designated Earmarked Fund:";
		echo "<br><br>";
                echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
		$asset = new Balancesheet();
		$asset->Investments(21,'Earmarked Funds',8,'200201',"NULL","view",0);
		$dr_total1 = $asset->dr_total1;
		$cr_total1 = $asset->cr_total1;
		$old_dr_total1 = $asset->old_dr_total1;
		$old_cr_total1 = $asset->old_cr_total1;

		echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$dr_total1) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$cr_total1) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$old_dr_total1) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$old_cr_total1) . "</strong>";
                        echo "</td>";
                        echo "</tr>";
			echo "</table>";
			echo "<br><br>";    
                	echo "<br><br><br>";        

			echo "Investments Others:";
			echo "<br><br>";
                	echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
			$asset = new Balancesheet();
                	$asset->Investments(21,'others',8,'200201',"NULL","view",6);
                	$dr_total2 = $asset->dr_total2;
			$cr_total2 = $asset->cr_total2;
                	$old_dr_total2 = $asset->old_dr_total2;
	      		$old_cr_total2 = $asset->old_cr_total2;

			echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$dr_total2) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$cr_total2) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$old_dr_total2) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$old_cr_total2) . "</strong>";
                        echo "</td>";
                        echo "</tr>";
			echo "</table>";
			echo "<br><br>";    

	                echo "<strong> Short Term Investments </strong>";
        	        echo "<br><br><br>";    
			echo "Investments From Designated Earmarked Fund:";    
			echo "<br><br>";    
                	echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
			$asset = new Balancesheet();
                	$asset->Investments(22,'Earmarked Funds',8,'200202',"NULL","view",12);
			$dr_total1 = $asset->dr_total1;
			$cr_total1 = $asset->cr_total1;
           	    	$old_dr_total1 = $asset->old_dr_total1;
	   		$old_cr_total1 = $asset->old_cr_total1;

			echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$dr_total1) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$cr_total1) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$old_dr_total1) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$old_cr_total1) . "</strong>";
                        echo "</td>";
                        echo "</tr>";

		        echo "</table>";
			echo "<br><br>";    
                	echo "<br><br><br>";   

			echo "Investments Others:";
			echo "<br><br>";
        	        echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
			$asset = new Balancesheet();
                	$asset->Investments(22,'others',8,'200202',"NULL","view",18);
                	$dr_total2 = $asset->dr_total2;
			$cr_total2 = $asset->cr_total2;
                	$old_dr_total2 = $asset->old_dr_total2;
	      		$old_cr_total2 = $asset->old_cr_total2;

			echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$dr_total2) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$cr_total2) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(+$old_dr_total2) . "</strong>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc(-$old_cr_total2) . "</strong>";
                        echo "</td>";
                        echo "</tr>";
			echo "</table>";
?>



