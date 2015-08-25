<?php
        setlocale(LC_MONETARY, 'en_IN');
        $i = 0;
        $old_cr_total = 0;
        $old_dr_total = 0;
        $old_total = 0;
        $opening_balance = 0;
	$debit_total = "";
	$credit_total = "";
	$this->load->library('reportlist1');
	$this->load->library('reportlist');
	$count = 1;
	$liability1 = new Reportlist1();

	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">Current Year</th><th align=\"center\" colspan=\"2\">Previous Year</th></tr></thead>";
	$id = 6;
	$id = 5;
        $i = $id;
	if($id == 6)
	{
     	$liability1->init($i);
	}else{
	$liability1->init($i);
	}
        //get opening balance
	$liability = new Reportlist();
        $liability->callToOpBalance('new', 'schedule');
        $opening_balance = $liability->opening_balance;
        $dr_total1 = $liability->dr_total;
        $cr_total1 = $liability->cr_total;

        $liability->callToOpBalance('old', 'schedule');
        $opening_balance_prev = $liability->opening_balance_prev;
        $old_dr_total1 = $liability->old_dr_total;
        $old_cr_total1 = $liability->old_cr_total;

        //Fetch opening balance from db
        echo "<tr>";
        echo "<td width=40%>";
        echo "Balance as at the beginning of the year";
        echo "</td>";
        if($cr_total1 > $dr_total1)
        {
                echo "<td width=15%>";
                echo "</td>";
                echo "<td width=15% align=\"right\">";
                echo convert_amount_dc(+-$opening_balance);
                echo "</td>";
	}
	else
        {
                echo "<td width=15% align=\"right\">";
                echo convert_amount_dc(+-$opening_balance);
                echo "</td>";
                echo "<td width=15%>";
                echo "</td>";
        }
        if($old_cr_total1 > $old_dr_total1)
        {
                echo "<td width=15%>";
                echo "</td>";
                echo "<td width=15% align=\"right\">";
                echo convert_amount_dc(+-$opening_balance_prev);
                echo "</td>";
        }
        else
        {
                echo "<td width=15% align=\"right\">";
                echo convert_amount_dc(+-$opening_balance_prev);
                echo "</td>";
                echo "<td width=15%>";
                echo "</td>";
        }  
        $liability1->get_liabilityschedule('1001',$count);
	$dr_total_1 = $liability1->dr_total1;
        $cr_total_2 = $liability1->cr_total1;

	$liability1->get_liabilityschedule('1002',$count);
	$dr_total_3 = $liability1->dr_total1;
	$cr_total_4 = $liability1->cr_total1;
	$profit = $liability1->profit1;
	if($profit < 0){
	$dr_total_3 = $dr_total_3 + (-$profit);
	}else{
	$cr_total_4 = $cr_total_4 + ($profit);
	}
	$debit_total = ($dr_total_1 + $dr_total_3);
	$credit_total = ($cr_total_2 + $cr_total_4);
	$total = $credit_total-$debit_total;

	//Display total for the given schedule
        echo "<tr>";
        echo "<td width=40% class=\"bold\">";
        echo "TOTAL";
        echo "</td>";

        echo "<td width=15% align=\"right\">";
        echo "<strong>" . convert_amount_dc(+$debit_total) . "</strong>";
        echo "</td>";

        echo "<td width=15% align=\"right\">";
        echo "<strong>" . convert_amount_dc(-$credit_total) . "</strong>";
        echo "</td>";

        echo "<td width=15% align=\"right\">";
        echo "<strong>" . convert_amount_dc(0) . "</strong>";
        echo "</td>";

        echo "<td width=15% align=\"right\">";
        echo "<strong>" . convert_amount_dc(0) . "</strong>";
       	echo "</td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td width=40% class=\"bold\">";
        echo "BALANCE AT THE YEAR-END";
        echo "</td>";

        echo "<td colspan =2 width=30% align=\"right\">";
        echo "<strong>" . convert_amount_dc(+-$total) . "</strong>";
        echo "</td>";

        echo "<td  colspan = 2 width=30% align=\"right\">";
        echo "<strong>" . convert_amount_dc(0) . "</strong>";
        echo "</td>";
        echo "</tr>";
	echo "</table>";


?>
