<?php
	setlocale(LC_MONETARY, 'en_IN');
	$i = 0;
	$old_cr_total = 0.00;
        $old_dr_total = 0.00;
        $old_total = 0.00;
	$opening_balance = 0.00;

	$opening_balance_prev = 0.00;
	$this->load->library('reportlist');
        $liability = new Reportlist();
	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">Current Year</th><th align=\"center\" colspan=\"2\">Previous Year</th></tr></thead>";
	if($id != '')
        $i = $id;
	$count = 1;
	$liability->init($i);
        //get opening balance

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
	if($id == "9")
	{
	$liability->callschedule(9,'100101',1,'view','NULL');
	$dr_total = $liability->dr_total;
	$cr_total = $liability->cr_total;
	$old_dr_total = $liability->old_dr_total;
	$old_cr_total = $liability->old_cr_total; 
	}
	
	if($id == "10")
	{
	$liability->callschedule(10,'100102',2,'view','NULL');
        $dr_total = $liability->dr_total;
        $cr_total = $liability->cr_total;
        $old_dr_total = $liability->old_dr_total;
        $old_cr_total = $liability->old_cr_total; 
	} 

	if($id == "6")
        {
        $liability->callschedule(6,'1002',4,'view','NULL');
        $dr_total = $liability->dr_total;
        $cr_total = $liability->cr_total;
        $old_dr_total = $liability->old_dr_total;
        $old_cr_total = $liability->old_cr_total; 
        }


	$dr_total = $dr_total+$dr_total1;
	$cr_total = $cr_total+$cr_total1;
	$old_dr_total = $old_dr_total+$old_dr_total1;
	$old_cr_total = $old_cr_total+$old_cr_total1;

	if(!strncmp($liability->code, '10', strlen('10')))
        $total = $cr_total - $dr_total;
        else
        $total = $dr_total - $cr_total;

	if(!strncmp($liability->code, '10', strlen('10')))
        $old_total = $old_cr_total - $old_dr_total;
        else
        $old_total = $old_dr_total - $old_cr_total;


	//Display total for the given schedule
                echo "<tr>";
                echo "<td width=40% class=\"bold\">";
                        echo "TOTAL";
                echo "</td>";

                echo "<td width=15% align=\"right\">";
                echo "<strong>" . convert_amount_dc($dr_total) . "</strong>";
                echo "</td>";

                echo "<td width=15% align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$cr_total) . "</strong>";
                echo "</td>";

                echo "<td width=15% align=\"right\">";
                echo "<strong>" . convert_amount_dc($old_dr_total) . "</strong>";
                echo "</td>";

                echo "<td width=15% align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$old_cr_total) . "</strong>";
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
                echo "<strong>" . convert_amount_dc(+-$old_total) . "</strong>";
                echo "</td>";

        echo "</tr>";
	echo "</table>";

	//unset schedule() method's static values
	$liability->schedule(null,$id,$code,$count,'view','NULL');
	$liability->previous_year_data(null,$id,$code,'view','NULL');
	$liability->calculate_op_balance(null,'schedule');
?>
