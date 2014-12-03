<html>
<head>
<script type = "text/javascript">

	var flag = '';
	//var ledger = '';

	$(document).ready(function()
		{
			$.ajax({
                                url: <?php echo '\'' . site_url('setting/account/get_account_flag').'\''; ?>,
                                success: function(flag) {
                                        //alert(flag);
                                        flag = $.trim(flag);
                                        /*if(flag == 'false'){
                                                alert('On the Account Settings page, set the \'Account Type\' and \'Ledger Name\', to which net profit/loss will be transferred');
                                        }*/
                                }
                	});
			
			$.ajax({
				url: <?php echo '\''. site_url('setting/account/get_ledger_name').'\'';?>,
				success: function(ledger_name) {
					var ledger = $.trim(ledger_name);
					if((flag == 'false') || (ledger == '')){
                                		alert('On the Account Settings page, set the \'Account Type\' and \'Ledger Name\', to which net profit/loss will be transferred');
                        		}
				}
			});
			
		}
	);
</script>
</head>
<body>

<br>
<?php
	if ( ! $print_preview)
	{
		echo form_open('report/new_balancesheet/');
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

	setlocale(LC_MONETARY, 'en_IN');
	
	$liability_total = 0;
	$old_liability_total = 0;
	$this->load->library('reportlist');
	$income = new Reportlist();
        $income->init(3);
        $expense = new Reportlist();
        $expense->init(4);
        $income_total = -$income->total;
        $old_income_total = -$income->total2;
        $expense_total = $expense->total;
        $old_expense_total = $expense->total2;
        $pandl = float_ops($income_total, $expense_total, '-');
        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
        if ($pandl != 0 || $old_pandl !=0)
        {
        	if($pandl > 0)
                	$liability_total = float_ops($liability_total, $pandl, '+');
                else
                        $liability_total = float_ops($liability_total, $pandl, '+');
                if($old_pandl > 0)
                        $old_liability_total = float_ops($old_liability_total, $old_pandl, '+');
                else
                        $old_liability_total = float_ops($old_liability_total, $old_pandl, '+');
        }

	$liability = new Reportlist();
	$liability->init(2);
	//print_r($liability);
	//$liability_total = float_ops($liability_total, -$liability->total, '+');
	//$old_liability_total = float_ops($old_liability_total, -$liability->total2, '+');

	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	echo "<thead><tr><th></th><th>Schedule</th><th>Current Year<br>$curr_year</th><th>Previous Year<br>$prev_year</th></tr></thead>";
	echo "<tr>";
        echo "<td colspan=\"4\" class=\"bold\">";
        echo "Sources Of Funds";
        echo "</td>";
	echo "</tr>";
	$count = $liability->new_balance_sheet(0);
	$liability_total = float_ops($liability_total, -$liability->total, '+');
	$old_liability_total = float_ops($old_liability_total, -$liability->total2, '+');
	echo "<tr>";
		echo "<td class=\"bold\">";
			echo "Total";
	        echo "</td>";
	
		echo "<td>";
		echo "</td>";

		echo "<td align=\"right\" class=\"bold\">";
			echo money_format('%!i', convert_cur($liability_total));
		echo "</td>";

		echo "<td align=\"right\" class=\"bold\">";
			echo money_format('%!i', convert_cur($old_liability_total));
	        echo "</td>";
	echo "</tr>";

	$asset = new Reportlist();
	$asset->init(1);
	//$old_asset_total = $asset->total2;
        //$asset_total = $asset->total;

	echo "<tr>";
		echo "<td colspan=\"4\" class=\"bold\">";
			echo "Application Of Funds";
	        echo "</td>";
	echo "</tr>";
        
	$count =  $asset->new_balance_sheet($count);
	$old_asset_total = $asset->total2;
        $asset_total = $asset->total;

	echo "<tr>";
        	echo "<td class=\"bold\">";
		        echo "Total";
	        echo "</td>";

        	echo "<td>";
	        echo "</td>";

        	echo "<td align=\"right\" class=\"bold\">";
	        	echo money_format('%!i', convert_cur($asset_total));
        	echo "</td>";

	        echo "<td align=\"right\" class=\"bold\">";
			echo money_format('%!i', convert_cur($old_asset_total));
        	echo "</td>";
        echo "</tr>";

	echo "<tr>";
	        echo "<td class=\"bold\">";
        		echo "Notes On Accounts";
	        echo "</td>";

        	echo "<td>";
		//	echo "&nbsp;" . anchor_popup('notes', '22', array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
			echo "&nbsp;" . anchor_popup('notes/display_notes', '22', array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
	        echo "</td>";

        	echo "<td>";
	        echo "</td>";

        	echo "<td>";
	        echo "</td>";
        echo "</tr>";
	echo "</table>";
?>
</body>
</html>
