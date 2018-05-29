<html>
<body>
<?php
	setlocale(LC_MONETARY, 'en_IN');
	if ( ! $print_preview)
        {
                echo form_open('report/new_mhrd/');
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
	$this->db->from('settings');
        $value = $this->db->get();
        foreach($value->result() as $row)
        {
        $fy_start=explode("-",$row->fy_start);
        $fy_end=explode("-",$row->fy_end);
        }
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	echo "<br/>";
		
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	echo "<thead><tr><th align=\"center\" width=\"30%\">SOURCES OF FUNDS</th><th align=\"center\" width=\"30%\">Schedule</th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
	$this->load->library('reportlist1');
    $liability = new Reportlist1();
	$income = new Reportlist1();
	$diff_total = $income->income_expense_diff();
//        $liability->new_mhrd(2);
	$liability->new_mhrd(2, 'view', 'NULL');//add for previous year value
	$mhrd_total=-$liability->total_mhrd;//add for previous year total value
	$curr_total = -$liability->curr_total; 
	$liability_total1 = $curr_total + $diff_total;
	echo "<tr>";
       	echo "<td class=\"bold\">";
        	echo "Total";
        echo "</td>";
        echo "<td></td>";

        echo "<td align=\"right\" class=\"bold\">";
        //echo money_format('%!i', convert_cur($liability_total1));
        echo convert_amount_dc(-$liability_total1);
        echo "</td>";

        echo "<td align=\"right\" class=\"bold\">";
      //  echo money_format('%!i', convert_cur($mhrd_total));
        //echo convert_amount_dc(-$mhrd_total);//add (-) for convert amount to cr
        echo "</td>"; 
        echo "</tr>";
        echo "</table>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><th align=\"center\" width=\"30%\">APPLICATION OF FUNDS</th><th width=\"30%\" align=\"center\">Schedule</th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";

        $this->load->library('reportlist1');
        $asset = new Reportlist1();
//        $asset->new_mhrd(1);
	    $asset->new_mhrd(1, 'view', 'NULL');//add for previous year value
	    $mhrd_total=$asset->total_mhrd; // add for previous value total
	    $asset_total1 = $asset->curr_total;  
        echo "<tr>";
        echo "<td class=\"bold\">";
                echo "Total";
        echo "</td>";

        echo "<td></td>";

        echo "<td align=\"right\" class=\"bold\">";
        //echo money_format('%!i', convert_cur($asset_total1));
        echo convert_amount_dc($asset_total1);
        echo "</td>";

        echo "<td align=\"right\" class=\"bold\">";
       // echo money_format('%!i', convert_cur($mhrd_total));
        echo convert_amount_dc($mhrd_total);
        echo "</td>";
        echo "</tr>"; 

 	echo "<tr>";
        echo "<td class=\"bold\">";
	echo "Significant Accounting Policies";
        echo "</td>";
        echo "<td>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" .anchor_popup('notes/display_notes', '23', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo "</td>";
        echo "</tr>"; 
	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Contingent Liabilities And Notes To Accounts";
        echo "</td>";
        echo "<td>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;". anchor_popup('notes/display_notes', '24', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
        echo "</td>";
	echo "</tr>"; 
        echo "</table>"; 
?>
</body>
</html>
