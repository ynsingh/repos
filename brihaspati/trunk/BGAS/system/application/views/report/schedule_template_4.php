<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('balancesheet');
		
	$id1 = '8';
	$id2 = '157';
		
//	$i = 0;
//	if($id != '')
//		$i = $id;
		
	$dr_total_A = 0.00;
	$cr_total_A = 0.00;
	$old_cr_total_A = 0.00;
	$old_dr_total_A = 0.00;
	$dr_total_B = 0.00;
	$cr_total_B = 0.00;
	$old_cr_total_B = 0.00;
	$old_dr_total_B = 0.00;
	$dr_total = 0.00;
	$cr_total = 0.00;
	$old_cr_total = 0.00;
	$old_dr_total = 0.00;

		echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
	      	echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";

		echo "<tr>";
			echo "<td><strong>A.  CURRENT LIABILITIES</strong><td>";
			echo "<td></td>";
			echo "<td></td>";
			echo "<td></td>";
			echo "<td></td>";
		echo "</tr>";

		$counter = 1;
		foreach($current_liabilities as $id1 => $row){
			$object = new Balancesheet();
			$object->init($row['id']);
			$object->current_liabilities($counter);
			$counter++;
			$dr_total_A = $dr_total_A + $object->dr_total;
			$cr_total_A = $cr_total_A + $object->cr_total;
			$old_cr_total_A = $old_cr_total_A + $object->old_cr_total;
			$old_dr_total_A = $old_dr_total_A + $object->old_dr_total;
		}

		echo "<tr>";
			echo "<td width=40%>";
				echo "<b>Total (A)</b>";
			echo "</td>";

                	echo "<td width=15% align=\"right\">";
				echo "<strong>" . convert_amount_dc($dr_total_A) . "</strong>";
       	                //	echo  money_format('%!i', convert_cur($dr_total_A));
        	        echo "</td>";
	
       	        	echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($cr_total_A) . "</strong>";
               	        //	echo  money_format('%!i', convert_cur($cr_total_A));
	                echo "</td>";

       		        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($old_dr_total_A) . "</strong>";
               		  //      echo  money_format('%!i', convert_cur($old_dr_total_A));
                	echo "</td>";

        	        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($old_cr_total_A) . "</strong>";
       	        	       // echo  money_format('%!i', convert_cur($old_cr_total_A));
        	        echo "</td>";
		echo "</tr>";

		$dr_total = $dr_total + $dr_total_A;
                $cr_total = $cr_total + $cr_total_A;
                $old_cr_total = $old_cr_total + $old_cr_total_A;
                $old_dr_total = $old_dr_total + $old_dr_total_A;

		echo "<tr>";
			echo "<td><strong>B.  PROVISIONS</strong></td>";
			echo "<td></td>";
			echo "<td></td>";
			echo "<td></td>";
			echo "<td></td>";
		echo "</tr>";

		$counter = 1;
		foreach($provisions as $id2 => $row1){
			
			$object = new Balancesheet();
			$object->init($row1['id']);
			$object->provisions($counter);
			$counter++;
			$dr_total_B = $dr_total_B + $object->dr_total;
                        $cr_total_B = $cr_total_B + $object->cr_total;
                        $old_cr_total_B = $old_cr_total_B + $object->old_cr_total;
                        $old_dr_total_B = $old_dr_total_B + $object->old_dr_total;
	
		}

		echo "<tr>";
	                echo "<td width=40%>";
         	               echo "<b>Total (B)</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
			        echo "<strong>" . convert_amount_dc($dr_total_B) . "</strong>";
                              // echo  money_format('%!i', convert_cur($dr_total_B));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($cr_total_B) . "</strong>";
                              // echo  money_format('%!i', convert_cur($cr_total_B));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($old_dr_total_B) . "</strong>";
                              // echo  money_format('%!i', convert_cur($old_dr_total_B));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($old_cr_total_B) . "</strong>";
                               //echo  money_format('%!i', convert_cur($old_cr_total_B));
                        echo "</td>";
                echo "</tr>";

		$dr_total = $dr_total + $dr_total_B;
                $cr_total = $cr_total + $cr_total_B;
                $old_cr_total = $old_cr_total + $old_cr_total_B;
                $old_dr_total = $old_dr_total + $old_dr_total_B;

		echo "<tr>";
                        echo "<td width=40%>";
                               echo "<b>Total (A+B)</b>";
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
			        echo "<strong>" . convert_amount_dc($dr_total) . "</strong>";
                               //echo  money_format('%!i', convert_cur($dr_total));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($cr_total) . "</strong>";
                              // echo  money_format('%!i', convert_cur($cr_total));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($old_dr_total) . "</strong>";
                               //echo  money_format('%!i', convert_cur($old_dr_total));
                        echo "</td>";

                        echo "<td width=15% align=\"right\">";
				 echo "<strong>" . convert_amount_dc($old_cr_total) . "</strong>";
                               //echo  money_format('%!i', convert_cur($old_cr_total));
                        echo "</td>";
                echo "</tr>";

		echo "</table>";
		echo "<br><br><br>";
//	}

?>
