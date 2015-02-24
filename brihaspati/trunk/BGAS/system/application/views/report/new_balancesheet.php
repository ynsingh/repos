<html>
<head>
<script type = "text/javascript">
	var flag = '';
	//var ledger = '';
	$(document).ready(function()
		{
			$.ajax({
                                url:<?php echo '\'' . site_url('setting/account/get_account_flag').'\''; ?>,
                                success:function(flag) {
                                        //alert(flag);
                                        flag = $.trim(flag);
                                        /*if(flag == 'false'){
                                                alert('On the Account Settings page, set the \'Account Type\' and \'Ledger Name\', to which net profit/loss will be transferred');
                                        }*/
                                }
                	});
			$.ajax({
				url:<?php echo '\''. site_url('setting/account/get_ledger_name').'\'';?>,
				success:function(ledger_name) {
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
	$this->db->from('settings');
        $value = $this->db->get();
        foreach($value->result() as $row) 
	{
        $fy_start=explode("-",$row->fy_start);
        $fy_end=explode("-",$row->fy_end);
        }
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
        $expense_total = $expense->total;
	$old_income_total = -$income->income_total(3,"view","NULL");
        $old_expense_total = $expense->expense_total(4,"view","NULL");
        $pandl = float_ops($income_total, $expense_total, '-');
        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
	if ($pandl != 0 || $old_pandl !=0)
       	{
        //the change in sign is needed
    /*    if($pandl > 0)
        $this->total1 = float_ops($this->total1, -$pandl, '+');
        else
        $this->total1 = float_ops($this->total1, -$pandl, '+');
        if($old_pandl > 0)
        $mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
        else
      	$mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');*/
      	}

	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	echo "<thead><tr><th></th><th align=\"center\">Schedule</th><th align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
	echo "<tr>";
        echo "<td colspan=\"4\" class=\"bold\">";
        echo "Sources Of Funds";
        echo "</td>";
	echo "</tr>";
	$liability = new Reportlist();
	$liability->new_balance_sheet(0,2,"view","NULL",0);
        $curr_total = -$liability->curr_total;
        $curr_total = float_ops($curr_total, -$pandl, '-');
	$prev_total = -$liability->prev_total;
	$prev_total = float_ops($prev_total, -$old_pandl, '-');

	echo "<tr>";
		echo "<td class=\"bold\">";
			echo "Total";
	        echo "</td>";
	
		echo "<td>";
		echo "</td>";

		echo "<td align=\"right\" class=\"bold\">";
			echo money_format('%!i', convert_cur($curr_total));
		echo "</td>";

		echo "<td align=\"right\" class=\"bold\">";
			echo money_format('%!i', convert_cur($prev_total));
	        echo "</td>";
	echo "</tr>";

	$this->load->library('reportlist');
	$asset = new Reportlist();
	echo "<tr>";
		echo "<td colspan=\"4\" class=\"bold\">";
			echo "Application Of Funds";
	        echo "</td>";
	echo "</tr>";
	$asset->new_balance_sheet(6,1,"view","NULL",9);
        $curr_total = $asset->curr_total;
	$prev_total = $asset->prev_total;

	echo "<tr>";
        	echo "<td class=\"bold\">";
		        echo "Total";
	        echo "</td>";

        	echo "<td>";
	        echo "</td>";

        	echo "<td align=\"right\" class=\"bold\">";
	        	echo money_format('%!i', convert_cur($curr_total));
        	echo "</td>";

	        echo "<td align=\"right\" class=\"bold\">";
			echo money_format('%!i', convert_cur($prev_total));
        	echo "</td>";
        echo "</tr>";

	echo "<tr>";
	        echo "<td class=\"bold\">";
        		echo "Notes On Accounts";
	        echo "</td>";

        	echo "<td>";
		//	echo "&nbsp;" . anchor_popup('notes', '22', array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
			echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('notes/display_notes', '22', array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
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
