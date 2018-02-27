
<?php 
	setlocale(LC_MONETARY, 'en_IN');
	echo form_open('user/aggregatebudget');
        echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
	echo "<thead><tr><th>Budget Code </th><th>Budget Name </th><th>B/D Balance</th><th>Over expense allowed </th><th>Available Budget</th></tr></thead>";
	echo "<br>";
	//allocated amount and consume amount.
	//$totalconsume;	
	$unallocated = $totalbudget - $budgetopen;
	//$consumed = $totalbudget - $totalconsume;
        //path for storing xm file.
        $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');

        $file_name1=$accounts;
	$mergefile = $mergebudgetfile;
	$accountlist = str_replace("_budget.xml", "", $file_name1);
	echo "<b>Aggregate budget of Accounts--</b>". $mergefile;

	echo "</br></br>";


	$length1 = array();	
        //$tt1=$acctpath."/".$file_name1;
	$tt1 = $file_name1;
        $doc = new DomDocument;
	$doc->formatOutput = true;
	$doc->load($tt1);
	$xpath = new DomXPath($doc);
	
	//get length of merge budget file.
	$len1 = $doc->getElementsByTagName('Code')->length;

        for($j=0;$j<$len1;$j++)
        {
                $budgetcode = $xpath->query("/Budgets/Code");
                $Budgetcode = $budgetcode->item($j)->getAttribute('id');
                $Budgetname1 = $budgetcode->item($j)->getAttribute('Budgetname');
                $Budgetbdbalance = $budgetcode->item($j)->getAttribute('Bd_balance');
                $Budgetgroupid = $budgetcode->item($j)->getAttribute('Group_id');
                $Budgetallowedover = $budgetcode->item($j)->getAttribute('Allowedover');
                $Budgetconsumeamount = $budgetcode->item($j)->getAttribute('Consume_amount');


		if($Budgetconsumeamount==0)
		{
			$Budgetconsumeamount = $Budgetbdbalance;
		}
		else
		{
			$Budgetconsumeamount = $Budgetbdbalance - $Budgetconsumeamount;
		}

	 	if($Budgetcode!= "")	{
		echo "<tr>";
                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".$Budgetcode;
                echo "</td>";

                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".$Budgetname1;
                echo "</td>";

                echo "<td align=\"right\" class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".money_format('%!i', $Budgetbdbalance);
                echo "</td>";


                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".$Budgetallowedover;
                echo "</td>";

                echo "<td align=\"right\" class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;". money_format('%!i', $Budgetconsumeamount);
                echo "</td>";

                echo "</tr>";
		}
	}
	
	           echo "<tr>";
                                        echo "<td>";
                                                echo "&nbsp;<strong>" . "Allocated Amount" .  "</strong>";
                                                //echo $this->print_space($this->counter);
                                                echo "&nbsp;" .  money_format('%!i', $budgetopen);
                                        echo " </td>";

                                        echo "<td>";
                                        echo "</td>";

                                        echo "<td align=\"right\">";
                                                echo "&nbsp;<strong>" . "Unallocated Amount" .  "</strong>";
                                                //echo $this->print_space($this->counter);
                                                //$temp = $this->main_budget_amount - $this->sum;
                                                echo "&nbsp;" .  money_format('%!i', $unallocated);
					echo "<td>";
                                        echo " </td>";
                                        echo "<td align=\"right\">";
                                                echo "&nbsp;<strong>" . "Consumed Amount" .  "</strong>";
                                                //echo $this->print_space($this->counter);
                                                //$temp = $this->main_budget_amount - $this->sum;
                                                echo "&nbsp;" .  money_format('%!i', $totalconsume);
                                        echo " </td>";

		echo "<tr>";
        echo "</table>";
        echo "<p>";
        echo " ";
        echo "</p>";
        echo form_close();
?>

