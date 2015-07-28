<?php
 	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('reportlist1');
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
        echo "<br>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"90%\">";
        echo "<thead><tr><th align=\"center\"><b>1.<br>S.No.</b></th><th align=\"center\" colsapn=\"4\"><b>2.<br>Name of Sponsor</b></th><th align=\"center\" colspan=\"4\"><b>Opening Balance <br>As On 01.04.".$fy_start[0]."</b></th><th align=\"center\" colspan=\"4\"><b>Transactions <br>During the Year <br>".$curr_year."</b></th><th align=\"center\" colspan=\"4\"><b>Closing Balance <br>As On 31.03.".$fy_end[0]."</b></th></tr></thead>";
	 echo "<tr><td colspan=\"2\"></td><td align=\"center\" colspan=\"2\"><b>3</b></td><td align=\"center\" colspan=\"2\"><b>4</b></td><td align=\"center\" colspan=\"2\"><b>5</b></td><td align=\"center\" colspan=\"2\"><b>6</b></td><td align=\"center\" colspan=\"2\"><b>7</b></td><td align=\"center\" colspan=\"2\"><b>8</b></td></tr>";

        echo "<tr><td></td><td></td><td align=\"center\" colspan=\"2\"><b>CR.</b></td><td align=\"center\" colspan=\"2\"><b>DR.</b></td><td align=\"center\" colspan=\"2\"><b>CR.</b></td><td align=\"center\" colspan=\"2\"><b>DR.</b></td><td align=\"center\" colspan=\"2\"><b>CR.</b></td><td align=\"center\" colspan=\"2\"><b>DR.</b></td></tr>";
		
	 	echo "<tr>";
                        echo "<td align=\"right\" width=\"10%\">";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"15%\">";
                        echo "<strong>TOTAL</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" colspan=\"2\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" colspan=\"2\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" colspan=\"2\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" colspan=\"2\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

			 echo "<td align=\"right\" colspan=\"2\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" colspan=\"2\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

		echo "</tr>";
		echo "</table>";



?>

