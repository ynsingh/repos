<?php
	setlocale(LC_MONETARY, 'en_IN');
	form_open('aggregation/aggregateincexp1');
	$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
	$accname1 = "";
	$tem =0;
        foreach ($accounts as $key => $value)
        {
	        $accname1 = $accname1.$value;
                $tem++;
        }
	//echo $accname1;
	$mergeincfile = $mergeincfile;
	$mergeexpfile = $mergeexpfile;
//	echo $username;
	 //print_r($accounts);
	$tt1 = $acctpath."/".$mergeincfile;
	$tt2 = $acctpath."/".$mergeexpfile; 

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
	
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"80%\" >";
        echo "<thead><tr><th>Perticulars</th><th align=\"center\">Schedule</th><th align=\"center\">Current Year<br></tr></thead>";
        echo "<tr><td colspan=\"4\" class=\"bold\">Income</td></tr>";

        //$tt1 = $file_name1;
        $doc = new DomDocument;
        $doc->formatOutput = true;
        $doc->load($tt1);
        $xpath = new DomXPath($doc);

        //get length of merge budget file.
        $len1 = $doc->getElementsByTagName('Code')->length;
	$len1;
        for($j=0;$j<$len1;$j++)
        {
                $income = $xpath->query("/IncExp/Code");
                $id = $income->item($j)->getAttribute('id');
                $code = $income->item($j)->getAttribute('code');
                $name = $income->item($j)->getAttribute('name');
                $schedule = $income->item($j)->getAttribute('schedule');
                $itotal = $income->item($j)->getAttribute('total');

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
		echo "&nbsp;" .  $name;
		echo "</td>";
                echo "<td class=\"td-group\" align=\"center\">";
                echo "&nbsp;" .  $schedule;
                echo "</td>";
                echo "<td class=\"td-group\" align=\"center\">";
                echo "&nbsp;" .  $itotal;
                echo "</td>";
		echo "</tr>";



	}
	$inctemp = 0-$inctemp;
	echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total (A)";
        echo "</td>";
        echo "<td></td>";
        echo "<td align=\"right\" class=\"bold\">";
                      echo money_format('%!i', convert_cur($inctemp));
       echo "</tr>";

        $doc->load($tt2);
        $xpath = new DomXPath($doc);

        //get length of merge budget file.
        $len1 = $doc->getElementsByTagName('Code')->length;
        $len1;
	echo "<tr><td colspan=\"4\" class=\"bold\">Expenditure</td></tr>";
        for($j=0;$j<$len1;$j++)
        {
                $income = $xpath->query("/IncExp/Code");
                $id = $income->item($j)->getAttribute('id');
                $code = $income->item($j)->getAttribute('code');
                $name = $income->item($j)->getAttribute('name');
                $schedule = $income->item($j)->getAttribute('schedule');
                $itotal = $income->item($j)->getAttribute('total');

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $name;
                echo "</td>";
                echo "<td class=\"td-group\" align=\"center\">";
                echo "&nbsp;" .  $schedule;
                echo "</td>";
//		echo "<td align=\"right\">".money_format('%!i', convert_cur($itotal))."</td>";
                echo "<td class=\"td-group\" align=\"center\">";
                echo "&nbsp;" .  $itotal;
                echo "</td>";

                echo "</tr>";



        }
        echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total (B)";
        echo "</td>";
        echo "<td></td>";
        echo "<td align=\"right\" class=\"bold\">";
                      echo money_format('%!i', convert_cur($exptemp));
	echo "</tr>";
	$income_exp_diff = $inctemp - $exptemp;
	echo "<tr><td>Balance being excess of Income over Expenditure (A-B)</td><td></td>";
    	if($income_exp_diff > 0){
        echo "<td align=\"right\" class=\"bold\">";
        echo money_format('%!i', convert_cur($income_exp_diff));
        echo "</td>";
    	}else{
        echo "<td></td>";
    	}
    echo "<tr><td>Balance being Surplus(Deficit) carried to General Reserve</td><td></td>";
    echo "<td align=\"right\" class=\"bold\">";
    echo money_format('%!i', convert_cur($income_exp_diff));
    echo "</td>";
    echo "<td></td>";
    echo "</tr>";

    echo "<tr>";
    echo "<td class=\"bold\">";
    echo "Significant Accounting Policies";
    echo "</td>";
    echo "<td align=\"center\">";
    //echo anchor_popup('notes/display_notes', '23', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
    echo 23;
    echo "</td>";
    echo "<td></td><td></td>";
    echo "</tr>";

    echo "<tr>";
    echo "<td class=\"bold\">";
    echo "Contingent Liabilities and Notes to Accounts";
    echo "</td>";
    echo "<td align=\"center\">";
    echo 24;
    //echo anchor_popup('notes/display_notes', '24', array('title' => 'Notes On Accounts', 'style' => 'color:#000000;text-decoration:none;'));
    echo "</td>";
    echo "<td></td><td></td>";
    echo "</tr>";





	echo"</table>";
	echo form_close();

?>
