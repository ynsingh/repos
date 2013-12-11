<?php
	$i = 0;
	$this->load->library('accountlist');
	$liability = new Accountlist();

	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">Current Year</th><th align=\"center\" colspan=\"2\">Previous Year</th></tr></thead>";
	//Fetch opening balance from db
	echo "<tr>";
		echo "<td width=40%>";
			echo "Balance as at the beginning of the year";
        	echo "</td>";
	
		echo "<td width=15% align=\"right\">";
			echo "0";
		echo "</td>";
	
		echo "<td width=15%>";
	        echo "</td>";

		echo "<td width=15%>";
	        echo "</td>";

		echo "<td width=15%>";
	        echo "</td>";
	echo "</tr>";
	
	if($id != '')
		$i = $id;
	$liability->init($i); 
	//$total = $liability->schedule(0);
	$liability->schedule(0);

	$cr_total = $liability->cr_total;
        $dr_total = $liability->dr_total;
        $total = $cr_total - $dr_total;
	
	$liability->previous_year_data();

	$old_cr_total = $liability->old_cr_total;
        $old_dr_total = $liability->old_dr_total;
        $old_total = $old_cr_total - $old_dr_total;

	//Display total for the given schedule
	echo "<tr>";
                echo "<td width=40% class=\"bold\">";
                        echo "TOTAL";
                echo "</td>";

                echo "<td width=15% align=\"right\">";
                        //echo $total;
			echo $dr_total;
                echo "</td>";

                echo "<td width=15% align=\"right\">";
			echo $cr_total;
                echo "</td>";

                echo "<td width=15%>";
			echo $old_dr_total;
                echo "</td>";

                echo "<td width=15%>";
			echo $old_cr_total;
                echo "</td>";
        echo "</tr>";
	//$liability->schedule();

	echo "<tr>";
                echo "<td width=40% class=\"bold\">";
                        echo "BALANCE AT THE YEAR-END";
                echo "</td>";

                echo "<td colspan =2 width=30% align=\"right\">";
		        echo $total;
                        //echo $dr_total;
                echo "</td>";

               // echo "<td width=15% align=\"right\">";
                        //echo $cr_total;
               // echo "</td>";

                echo "<td  colspan = 2 width=30% align=\"right\">";
			echo $old_total;
                echo "</td>";

                //echo "<td width=15%>";
                //echo "</td>";
        echo "</tr>";

	echo "</table>";
?>
