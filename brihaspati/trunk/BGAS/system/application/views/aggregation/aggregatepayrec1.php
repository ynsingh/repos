<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	setlocale(LC_MONETARY, 'en_IN');
	form_open('aggregation/aggregatepayrec1');
	$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
	$accname1 = "";
	$tem =0;
        foreach ($accounts as $key => $value)
        {
	        $accname1 = $accname1.",".$value;
                $tem++;
        }
    $totalrec = $netreceipttotal + $tot_op_bal;

    $totalpay = $netpaymenttotal + $total_cl_bal;

	$mergepayfile = $mergepayfile;
	$mergerecfile = $mergerecfile;

	$tt1 = $acctpath."/".$mergepayfile;
	$tt2 = $acctpath."/".$mergerecfile; 

        echo "<p>";
        echo "Username :";
	    echo $username;
        echo "<br />";
    	echo "</p>";

        echo "<p>";
        echo "Aggregation of accounts :";
        echo $accname1;
        echo "<br />";
        echo "</p>";

        echo "<table>";
            echo "<tr valign=\"top\">";
        // for receipts side
        echo "<td>";

        echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
        echo "<thead><tr><th width=\"$left_width\">Receipt (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount</th></tr></thead>";
        echo "<tr class=\"tr-balance\"><td class=\"bold\" cellpadding=5>Bank Or Cash Opening Balance</td><td align=\"right\" class=\"bold\">" . convert_amount_dc($tot_op_bal) . "</td></tr>";

        $doc = new DomDocument;
        $doc->formatOutput = true;
        $doc->load($tt2);
        $xpath = new DomXPath($doc);

        //get length of merge budget file.
        $len1 = $doc->getElementsByTagName('Code')->length;
        $len1;
        for($j=0;$j<$len1;$j++)
        {
                $income = $xpath->query("/Payment/Code");
                $id = $income->item($j)->getAttribute('id');
                $code = $income->item($j)->getAttribute('code');
                $name = $income->item($j)->getAttribute('name');
                $itotal = $income->item($j)->getAttribute('total');

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $name;
                echo "</td>";
                echo "<td align=\"right\">"."Cr " .money_format('%!i', $itotal). "</td>";
/*                echo "<td class=\"td-group\" align=\"center\">";
                echo "&nbsp;" .  $itotal;
                echo "</td>";
*/
                echo "</tr>";
        }

        echo "</table>";
        echo "</td>";

        //for Payment side....
        echo "<td>";
        echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
        echo "<thead><tr><th width=\"$left_width\">Payment (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount</th></tr></thead>";

//        echo "<tr class=\"tr-balance\"><td class=\"bold\" cellpadding=5>Bank Or Cash Closing Balance</td><td align=\"right\" class=\"bold\">" . convert_amount_dc($total_cl_bal) . "</td></tr>";
        $doc = new DomDocument;
        $doc->formatOutput = true;
        $doc->load($tt1);
        $xpath = new DomXPath($doc);

        //get length of merge budget file.
        $len1 = $doc->getElementsByTagName('Code')->length;
        $len1;
        for($j=0;$j<$len1;$j++)
        {
                $income = $xpath->query("/Payment/Code");
                $id = $income->item($j)->getAttribute('id');
                $code = $income->item($j)->getAttribute('code');
                $name = $income->item($j)->getAttribute('name');
                $itotal = $income->item($j)->getAttribute('total');

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $name;
                echo "</td>";
                echo "<td align=\"right\">" .convert_amount_dc($itotal). "</td>";
/*                echo "<td class=\"td-group\" align=\"center\">";
                 echo "<td align=\"right\">" .convert_amount_dc($itotal). "</td>";
                echo "&nbsp;" .  $itotal;
                echo "</td>";
*/
                echo "</tr>";
        }


        echo "</table>";
        echo "</td>";//end of payment side....
        echo "</tr>";
        


        echo "<tr valign=\"top\" class=\"total-area\">";
        echo "<td>";
        echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
        echo "<tr valign=\"top\" class=\"tr-balance\">";
        echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($totalrec)) . "</td>";
        echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "<td>";
        echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
                echo "<tr class=\"tr-balance\"><td class=\"bold\" cellpadding=5>Bank Or Cash Closing Balance</td><td align=\"right\" class=\"bold\">" . convert_amount_dc($total_cl_bal) . "</td></tr>";

        echo "<tr valign=\"top\" class=\"tr-balance\">";
        echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($totalpay)) . "</td>";
        echo "</tr>";
        echo "</table>";


        echo "</td>";
        echo "</tr>";//end of Total balance....    */
        echo "</table>";


	echo form_close();

?>
