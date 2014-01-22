<?php
	$this->load->library('accountlist');

	$liability = new Accountlist();
	$liability->init(2);
	//echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"100%\">";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
	echo "<thead><tr><th></th><th>Schedule</th><th>Current Year</th><th>Previous Year</th></tr></thead>";
	echo "<tr>";
        echo "<td colspan=4 class=\"bold\">";
        echo "Sources Of Funds";
        echo "</td>";
	$count = $liability->new_balance_sheet(0);

	echo "<tr>";
	echo "<td class=\"bold\">";
	echo "Total";
        echo "</td>";
	
	echo "<td>";
	echo "</td>";

	echo "<td align=\"right\">";
	$liability_total = -$liability->total;
	echo convert_cur($liability_total);
	echo "</td>";

	echo "<td>";
        echo "</td>";
	echo "</tr>";

	$asset = new Accountlist();
        $asset->init(1);
	echo "<tr>";
	echo "<td colspan=4 class=\"bold\">";
	echo "Application Of Funds";
        echo "</td>";
	//echo "<td class=\"simple-table balance-sheet-table\">";
//	echo "<td>";
  //      echo "";
    //    echo "</td>";
	//echo "<td class=\"simple-table balance-sheet-table\">";
//	echo "<td>";
  //      echo "";
    //    echo "</td>";
	//echo "<td class=\"simple-table balance-sheet-table\">";
//	echo "<td>";
  //      echo "";
    //    echo "</td>";
	//echo "<td class=\"simple-table balance-sheet-table\">";
	echo "</tr>";
        //$asset->new_balance_sheet(0);
        $count =  $asset->new_balance_sheet($count);

	//echo "</tr>";

	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total";
        echo "</td>";

        echo "<td>";
        echo "</td>";

        echo "<td align=\"right\">";
        $asset_total = $asset->total;
        echo convert_cur($asset_total);
        echo "</td>";

        echo "<td>";
        echo "</td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Notes On Accounts";
        echo "</td>";

        echo "<td>";
	echo "&nbsp;" . $count;
//	echo "&nbsp;" . anchor_popup('report/schedule/notes', $count, array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
        echo "</td>";

        echo "<td>";
        echo "</td>";

        echo "<td>";
        echo "</td>";
        echo "</tr>";
	echo "</table>";
?>
