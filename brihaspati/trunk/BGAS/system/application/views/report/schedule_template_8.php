<?php
	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('balancesheet');
			
		echo "<br><br>";  
		echo "<strong> Long Term Investments </strong>";
		echo "<br><br><br>";		
		echo "Investments From Designated Earmarked Fund:";
		echo "<br><br>";

                echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
		$object = new Balancesheet();
		$object->Investments(21,'Earmarked Funds');
		$dr_total1 = $object->dr_total1;
		$cr_total1 = $object->cr_total1;
		$old_dr_total1 = $object->old_dr_total1;
		$old_cr_total1 = $object->old_cr_total1;

		echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($dr_total1));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($cr_total1));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_dr_total1));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_cr_total1));
                        echo "</td>";
                        echo "</tr>";

			echo "</table>";
			echo "<br><br>";    
                	echo "<br><br><br>";        


			echo "Investments Others:";
			echo "<br><br>";
                	echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
			$object = new Balancesheet();
                	$object->Investments(21,'others');
                	$dr_total2 = $object->dr_total2;
			$cr_total2 = $object->cr_total2;
                	$old_dr_total2 = $object->old_dr_total2;
			$old_cr_total2 = $object->old_cr_total2;

			echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($dr_total2));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($cr_total2));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_dr_total2));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_cr_total2));
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
			$object = new Balancesheet();
                	$object->Investments(22,'Earmarked Funds');
			$dr_total1 = $object->dr_total1;
			$cr_total1 = $object->cr_total1;
                	$old_dr_total1 = $object->old_dr_total1;
			$old_cr_total1 = $object->old_cr_total1;

			echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($dr_total1));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($cr_total1));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_dr_total1));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_cr_total1));
                        echo "</td>";
                        echo "</tr>";


		        echo "</table>";
			echo "<br><br>";    
                	echo "<br><br><br>";   

			echo "Investments Others:";
			echo "<br><br>";
        	        echo "<table border=0 class=\"simple-table balance-sheet-table\">";
                	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
			$object = new Balancesheet();
                	$object->Investments(22,'others');
                	$dr_total2 = $object->dr_total2;
			$cr_total2 = $object->cr_total2;
                	$old_dr_total2 = $object->old_dr_total2;
			$old_cr_total2 = $object->old_cr_total2;

			echo "<tr>";
                        echo "<td width=40%>";
                        echo "<b>Total</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($dr_total2));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($cr_total2));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_dr_total2));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
                        echo  money_format('%!i', convert_cur($old_cr_total2));
                        echo "</td>";
                        echo "</tr>";


			echo "</table>";


?>



