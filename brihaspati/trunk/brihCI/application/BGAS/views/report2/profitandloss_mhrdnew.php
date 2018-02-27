<html>
<body>
<?php
	setlocale(LC_MONETARY, 'en_IN');
    	if ( ! $print_preview)
    	{
        	echo form_open('report2/profitandloss_mhrdnew/');
            	echo "<p>";
            	echo "<span id=\"tooltip-target-1\">";
            	echo form_label('Entry Date From', 'entry_date1');
            	echo " ";
            	echo form_input_date_restrict($entry_date1);
            	echo "</span>";
            	echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
            	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

            	echo "<span id=\"tooltip-target-2\">";
            	echo form_label('To Entry Date', 'entry_date2');
            	echo " ";
            	echo form_input_date_restrict($entry_date2);
            	echo "</span>";
            	echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
            	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?>
            	<input type='submit' value="GET" class='loading'>
            	<?php echo "</p>";
            	echo form_close();
    	}
    
    	$this->load->library('session');
    	$date1 = $this->session->userdata('date1');
    	$date2 = $this->session->userdata('date2');
    	$fy_start=explode("-",$date1);
    	$fy_end=explode("-",$date2);
    	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
    	$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

    	echo "<br/>";
    
    	if ( ! $print_preview)
    	{
        	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"80%\" >";
        	echo "<thead><tr><th>Perticulars</th><th align=\"center\">Schedule</th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
    	}else{
    		echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        	echo "<thead><tr><td>Perticulars</td><td align=\"center\">Schedule</td><td align=\"center\">Current Year<br>$curr_year</td><td align=\"center\">Previous Year<br>$prev_year</td></tr></thead>";
    	}
    	$this->load->library('Reportlist1');
    	/* code for income view */
    	echo "<tr><td colspan=\"4\" class=\"bold\">Income</td></tr>";
    	$income = new Reportlist1();
    	$income_total = $income->income_exp_mhrdnew(3, "view","NULL");
	$prev_total1 = 0-$income->total; //add for previous year value
    	$income_total = 0-$income_total;
    	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total (A)";
        echo "</td>";
        echo "<td></td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc(-$income_total);
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc(-$prev_total1);
        echo "</td>";
    	echo "</tr>";

	/* code for expenditure view */
    	echo "<tr><td colspan=\"4\" class=\"bold\">Expenditure</td></tr>";
    	$expense = new Reportlist1();
    	$expense_total = $expense->income_exp_mhrdnew(4, "view","NULL");
	$prev_total2 = $expense->total; //add for previous total
    	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total (B)";
        echo "</td>";
        echo "<td></td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($expense_total);
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc($prev_total2);
        echo "</td>";
    	echo "</tr>";

 	/*************************/

    	$income_exp_diff = $income_total - $expense_total;
    	echo "<tr><td>Balance being excess of Income over Expenditure (A-B)</td><td></td>";
    	if($income_exp_diff > 0){
        echo "<td align=\"right\" class=\"bold\">";
        echo  convert_amount_dc(-$income_exp_diff);
        echo "</td>";
    	}else{
        echo "<td></td>";
    	}
	if(($prev_total1-$prev_total2 )>0){
        echo "<td align=\"right\" class=\"bold\">";
        echo convert_amount_dc(-($prev_total1-$prev_total2));
        echo "</td>";
    	}else{
        echo "<td></td>";
    	}
    	echo "</tr>";
    
    	echo "<tr><td>Balance being Surplus(Deficit) carried to General Reserve</td><td></td>";
    	echo "<td align=\"right\" class=\"bold\">";
//    	echo money_format('%!i', convert_cur($income_exp_diff));
   	echo  convert_amount_dc(-$income_exp_diff);
    	echo "</td>";
    	echo "<td align=\"right\" class=\"bold\">";
//    	echo money_format('%!i', convert_cur($prev_total1-$prev_total2));
    	echo convert_amount_dc(-($prev_total1-$prev_total2));
    	echo "</td>";
    	//echo "<td></td>";
    	echo "</tr>";

    	echo "<tr>";
    	echo "<td class=\"bold\">";
    	echo "Significant Accounting Policies";
    	echo "</td>";
//    	echo "<td align=\"center\">";
    //	echo anchor_popup('notes/display_notes', '23', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
  //  	echo 23;
	echo "<td>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" .anchor_popup('notes/display_notes', '23', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
    	echo "</td>";
    //echo "</td>";
	echo "<td></td><td></td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td class=\"bold\">";
	echo "Contingent Liabilities and Notes to Accounts";
    	echo "</td>";
   // 	echo "<td align=\"center\">";
    //	echo 24;
    //	echo anchor_popup('notes/display_notes', '24', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
	echo "<td>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;". anchor_popup('notes/display_notes', '24', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
	echo "</td>";
//    	echo "</td>";
    	echo "<td></td><td></td>";
    	echo "</tr>";

    	echo"</table>";
?>
</body>
</html>
