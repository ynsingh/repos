<html>
<body>
<?php
	setlocale(LC_MONETARY, 'en_IN');

        if ( ! $print_preview)
        {
                echo form_open('report2/profitandloss_mhrd/');
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
	$expense_total ="";
	$income_total = "";
	$old_income_total = "";
	$total7 = 0;
	$profit =0;
	echo "<br/>";
	
	if ( ! $print_preview)
        {
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><th></th><th align=\"center\">Schedule</th><th colspan=\"5\" align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
 	}else{
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><td></td><td align=\"center\">Schedule</td><td colspan=\"5\" align=\"center\">Current Year<br>$curr_year</td><td align=\"center\">Previous Year<br>$prev_year</td></tr></thead>";
	}
	echo"<tr><td></td><td></td><td colspan=\"3\" align=\"center\">General Funds </td><td align=\"center\">Capital Funds</td><td align=\"center\">Total</td><td align=\"center\">Total</td></tr>";
	echo"<tr><td></td><td></td><td align=\"center\">Corpus</td><td align=\"center\">Designated-Earmarked Funds</td><td align=\"center\">General Reserve</td><td></td><td></td><td></td></tr>";
	echo "<tr><td colspan=\"8\" class=\"bold\">Income</td></tr>";

	 $this->load->library('reportlist');
        $income = new Reportlist();
	$income_total = $income->income_exp_mhrd(3,"view","NULL");
	$income_total = 0-$income_total;
	$total1 = $income->total1;
        $total2 = $income->total2;
        $total3 = $income->total3;
        $total4 = $income->total4;
	$total5 = $income->total5;
	$total6 = $income->total6;
	$prev_income_total = $income->prev_total;
	$total7 =$total5 + $total3 + $total6;

        echo "<tr>";
                echo "<td class=\"bold\">";
                        echo "Total";
                echo "</td>";
	//	echo "<td></td><td></td><td></td><td></td><td></td>";
		echo "<td></td>";
                echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total1));
                echo "</td>";
                echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total2));
                echo "</td>";
                echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total7));
                echo "</td>";
                echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total4));
                echo "</td>";

                echo "<td align=\"right\" class=\"bold\">";
                      echo money_format('%!i', convert_cur($income_total));
                echo "</td>";

                echo "<td align=\"right\" class=\"bold\">";
                      echo money_format('%!i', convert_cur(-$prev_income_total));
                echo "</td>";
        echo "</tr>";

        echo "<tr><td colspan=\"8\" class=\"bold\">Expenditure</td></tr>";
	$this->load->library('reportlist');
	$expense = new Reportlist();
	$expense_total = $expense->income_exp_mhrd(4, "view","NULL");
	$total1 = $expense->total1;
	$total2 = $expense->total2;
        $total3 = $expense->total3;
        $total4 = $expense->total4;
        $prev_expense_total = $expense->prev_total;
        echo "<tr>";
                echo "<td class=\"bold\">";
                        echo "Total";
                echo "</td>";

                echo "<td></td>";
		echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total1));
                echo "</td>";
		echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total3));
                echo "</td>";
		echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total2));
                echo "</td>";
		echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($total4));
                echo "</td>";

                echo "<td align=\"right\" class=\"bold\">";
		echo money_format('%!i', convert_cur($expense_total));
   		echo "</td>";
                echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i',convert_cur($prev_expense_total))."</td>";
                echo "</td>";
        echo "</tr>";
	$profit = $income_total - $expense_total;
	$prev_profit = (-$prev_income_total) - $prev_expense_total;
 	echo "<tr><td>Balance being excess of Income over Expenditure</td><td></td><td></td><td></td>";
	echo "<td></td><td></td>";
	if($profit > 0){
	echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($profit));
        echo "</td>";
	}else{
	echo "<td></td>";
	}
	if($prev_profit > 0){
	echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($prev_profit));
        echo "</td>";
	}else{
	echo "<td></td>";
	}
	echo "</tr>";
	echo "<tr><td>Balance being Surplus(Deficit) carried to General Reserve</td><td></td><td></td><td></td>";
        echo "<td></td><td></td>";
	echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($profit));
        echo "</td>";
	echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($prev_profit));
        echo "</td>";
	echo "</tr>";
	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Notes On Accounts";
        echo "</td>";

        echo "<td>";
        echo "&nbsp;" . anchor_popup('notes/display_notes', '25', array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
        echo "</td>";
        echo "<td></td><td></td><td></td><td></td><td></td><td></td>";
	echo "</tr>";

        echo "</table>";
	if(! $print_preview)
        {
                echo form_open('report2/printpreview/profitandloss_mhrd/');
                echo form_submit('submit', 'Print Preview');
                echo form_close();
        }

?>
</body>
</html>

