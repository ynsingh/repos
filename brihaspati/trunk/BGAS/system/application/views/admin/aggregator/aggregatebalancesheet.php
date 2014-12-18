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

<?php
	if ( ! defined('BASEPATH')) exit('No direct script access allowed');

	$username=$this->session->userdata('user_name');
        $db1=$this->load->database('login', TRUE);
        $db1->from('aggregateaccounts')->where('username', $username);
        $userrec = $db1->get();
	
	$mergeliability_total = 0;
	$mergeassets_total = 0;
        $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');


        foreach($userrec->result() as $row)
        {
		$acountlist=$row->accounts;

        }
        $accarray = array();
        $accarray = explode(",", $acountlist);
	//print_r($accarray);
	$acctname = $accarray[0];
        $db1->from('bgasAccData')->where('dblable', $acctname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
	        $fyear = $row->fyear;
	}

	//Create a merge file name.

	$length1 = array();
	$length2 = array();
	$mergefile="";
        //$doc = new DomDocument;
        for($i = 0 ; $i<sizeof($accarray); $i++)
        {
                $accname=$accarray[$i];
		
		$mergefile = $mergefile.$accname;

                $file_name1=$accname."_Liabilty.xml";
                $tt1=$acctpath."/".$file_name1;
                $file_name2=$accname."_Assets.xml";
                $tt2=$acctpath."/".$file_name2;

		//deletion of xml file .
		if (file_exists($tt1)) 
		{
        		unlink($tt1);	
		}
		if (file_exists($tt2)) 
                        unlink($tt2);
		
	}

                $file_name1=$mergefile."_Liabilty.xml";
                $tt1=$acctpath."/".$file_name1;
                $file_name2=$mergefile."_Assets.xml";
                $tt2=$acctpath."/".$file_name2;

                if (file_exists($tt1)) 
                        unlink($tt1);   
                if (file_exists($tt2))
                        unlink($tt2);

	
	
	//echo $mergefile;
        for($i = 0; $i<sizeof($accarray); $i++)
        {
        	$accname = $accarray[$i];
		setlocale(LC_MONETARY, 'en_IN');
	
		$liability_total = 0;
		$old_liability_total = 0;
		$this->load->library('aggregatereportlist');
		$income = new Aggregatereportlist();
        	$income->init(3,$accname);
	        $expense = new Aggregatereportlist();
	        $expense->init(4,$accname);
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

		$liability = new Aggregatereportlist();
		$liability->init(2,$accname);

		$count = $liability->new_balance_sheet(0,$accname);
	
		$liability_total = float_ops($liability_total, -$liability->total, '+');
	
		$mergeliability_total= $liability_total+$mergeliability_total;

		//Adding Liabilty Total in Account liabilty xml.

	        $file_name="";
		$file_name=$accname."_Liabilty.xml";
	 	$tt=$acctpath."/".$file_name;
                $doc = new DOMDocument();
                $doc->formatOutput = true;

                if(file_exists($tt))
                {
                	$doc->preserveWhiteSpace = false;
                        $doc->load($tt);
                        $Liabilities = $doc->firstChild;
                        $Liability_Name = $doc->createElement('Liability_Name');			

                        $group_name = $doc->createElement('Total');
                        $textNode = $doc->createTextNode($liability_total);
                        $group_name->appendChild($textNode);
                        $Liability_Name->appendChild($group_name);
			$Liabilities->appendChild($Liability_Name);

                        $ttt=$doc->saveXML();
                        $handle = fopen($tt, "w");
                        fwrite($handle, $ttt);
                        fclose($handle);
		}
	
		$asset = new Aggregatereportlist();
		$asset->init(1,$accname);
        	$asset_total = $asset->total;

		$count =  $asset->new_balance_sheet($count,$accname);
		$old_asset_total = $asset->total2;
	        $asset_total = $asset->total;

		$mergeassets_total= $asset_total+$mergeassets_total;
		
                //Adding Assets Total in Account Assets xml.

                $file_name=$accname."_Assets.xml";
                $tt=$acctpath."/".$file_name;
                $doc = new DOMDocument();
                $doc->formatOutput = true;

                if(file_exists($tt))
                {
                        $doc->preserveWhiteSpace = false;
                        $doc->load($tt);
                        $Liabilities = $doc->firstChild;
                        $Liability_Name = $doc->createElement('Assets_Name');

                        $group_name = $doc->createElement('Total');
                        $textNode = $doc->createTextNode($asset_total);
                        $group_name->appendChild($textNode);
                        $Liability_Name->appendChild($group_name);
                        $Liabilities->appendChild($Liability_Name);

                        $ttt=$doc->saveXML();
                        $handle = fopen($tt, "w");
                        fwrite($handle, $ttt);
                        fclose($handle);
                }


	}//end of for

        for($i = 0 ; $i<sizeof($accarray); $i++)
        {
                $accname=$accarray[$i];


                $file_name1=$accname."_Liabilty.xml";
                $tt1=$acctpath."/".$file_name1;
                $file_name2=$accname."_Assets.xml";
                $tt2=$acctpath."/".$file_name2;

                $doc = new DomDocument;
                if (file_exists($tt1))
                {
                        $doc->load($tt1);

                // count all Liability_Name elements
                $len1 = $doc->getElementsByTagName('Liability_Name')->length;//, PHP_EOL; 
                $length1[$i] = $len1;
                }
                if (file_exists($tt2))
                {
                        $doc->load($tt2);

                // count all Assets_Name elements
                $len2 = $doc->getElementsByTagName('Assets_Name')->length;//, PHP_EOL; 
                $length2[$i] = $len2;
                }
	}
        if(sizeof($length1)!==0)
	{
        	$max1 = max($length1);
                $max2 = max($length2);
        }


	for($i=0; $i<=$max1; $i++)
	{
		$file_name="";
		$sumamt=0;

	        for($j=0;$j<sizeof($accarray);$j++)
        	{
                	$accname1=$accarray[$j];

			$file_name=$accname1."_Liabilty.xml";
			$tt=$acctpath."/".$file_name;
		        $doc = new DomDocument();
        		$doc->formatOutput = true;
			$doc->load($tt);
			$xpath = new DomXPath($doc);

			$xpath->query("/Liabilities/Liability_Name/Group_Name");
			$xpath->query("/Liabilities/Liability_Name/Code_No");
			$xpath->query("/Liabilities/Liability_Name/Amount");
			$xpath->query("/Liabilities/Liability_Name/Code_Nu");
			$xpath->query("/Liabilities/Liability_Name/Counter");

			$liabiltynode1 = $xpath->query("/Liabilities/Liability_Name/Group_Name");
			$liabiltynode2 = $xpath->query("/Liabilities/Liability_Name/Code_No");
			$liabiltynode3 = $xpath->query("/Liabilities/Liability_Name/Amount");
			$liabiltynode4 = $xpath->query("/Liabilities/Liability_Name/Code_Nu");
			$liabiltynode5 = $xpath->query("/Liabilities/Liability_Name/Counter");
			
			

        	        $liabiltylist1 = @$liabiltynode1->item($i)->nodeValue;
                	$liabiltylist2 = @$liabiltynode2->item($i)->nodeValue;
	                $liabiltylist3 = @$liabiltynode3->item($i)->nodeValue;
	                $liabiltylist4 = @$liabiltynode4->item($i)->nodeValue;
	                $liabiltylist5 = @$liabiltynode5->item($i)->nodeValue;
			$lib = substr($liabiltylist3, 2); 
			$lib = str_replace(',', '', $lib);
			$sumamt = $sumamt+$lib; 
			$sumamt = $sumamt .".00";
		}

		//Storing merged data in a merge file

		$doc = new DOMDocument();
                $doc->formatOutput = true;

                $file_name=$mergefile."_Liabilty.xml";
                $tt=$acctpath."/".$file_name;
                if(file_exists($tt))
                {
			$doc->preserveWhiteSpace = false;
                        $doc->load($tt);
                        $Liabilities = $doc->firstChild;

                        $Liability_Name = $doc->createElement('Liability_Name');

                        $group_name = $doc->createElement('Group_Name');
                        $textNode = $doc->createTextNode($liabiltylist1);
                        $group_name->appendChild($textNode);
                        $Liability_Name->appendChild($group_name);

                        $code_no = $doc->createElement('Code_No');
                        $textNode1 = $doc->createTextNode($liabiltylist2);
                        $code_no->appendChild($textNode1);
                        $Liability_Name->appendChild($code_no);

                        $amount = $doc->createElement('Amount');
                        $textNode2 = $doc->createTextNode($sumamt);
                        $amount->appendChild($textNode2);
                        $Liability_Name->appendChild($amount);

                        $codenu = $doc->createElement('Code_Nu');
                        $textNode3 = $doc->createTextNode($liabiltylist4);
                        $codenu->appendChild($textNode3);
                        $Liability_Name->appendChild($codenu);

                        $counter = $doc->createElement('Counter');
                        $textNode4 = $doc->createTextNode($liabiltylist5);
                        $counter->appendChild($textNode4);
                        $Liability_Name->appendChild($counter);

                        $Liabilities->appendChild($Liability_Name);
                        $ttt=$doc->saveXML();
                        $handle = fopen($tt, "w");
                        fwrite($handle, $ttt);
                        fclose($handle);
		}
                else
                {
                	$r = $doc->createElement( "Liabilities" );
                        $doc->appendChild( $r );
                        $b = $doc->createElement( "Liability_Name" );

                        $group_name = $doc->createElement( "Group_Name" );
                        $group_name->appendChild($doc->createTextNode($liabiltylist1));
                        $b->appendChild( $group_name );

                        $code_no = $doc->createElement( "Code_No");
                        $code_no->appendChild($doc->createTextNode($liabiltylist2));
                        $b->appendChild( $code_no );

                        $amount = $doc->createElement( "Amount");
                        $amount->appendChild($doc->createTextNode($sumamt));
                        $b->appendChild( $amount );

                        $codenu = $doc->createElement('Code_Nu');
			$codenu->appendChild($doc->createTextNode($liabiltylist4));
                        $b->appendChild( $codenu );

                        $counter = $doc->createElement('Counter');
			$counter->appendChild($doc->createTextNode($liabiltylist5));
                        $b->appendChild( $counter );


                        $r->appendChild( $b );
                        $doc->save($tt);
                        $doc->saveXML();
		}
	}//end of Libality Display 
	
	//Display of Assets

/*        $accname1=$accarray[0];
        $file_name=$accname1."_Assets.xml";
        $tt=$acctpath."/".$file_name;
        $doc = new DomDocument();
        $doc->formatOutput = true;
        $doc->load($tt);
        $doc->getElementsByTagName('Assets_Name')->length;
*/
	for($i=0; $i<=$max2; $i++)
	{
		$file_name="";
		$sumamt=0;

	        for($j=0;$j<sizeof($accarray);$j++)
        	{
                	$accname1=$accarray[$j];

			$file_name=$accname1."_Assets.xml";
			$tt=$acctpath."/".$file_name;
		        $doc = new DomDocument();
        		$doc->formatOutput = true;
			$doc->load($tt);
			$xpath = new DomXPath($doc);

			$xpath->query("/Liabilities/Assets_Name/Group_Name");
			$xpath->query("/Liabilities/Assets_Name/Code_No");
			$xpath->query("/Liabilities/Assets_Name/Amount");
			$xpath->query("/Liabilities/Assets_Name/Code_Nu");
			$xpath->query("/Liabilities/Assets_Name/Counter");

			$liabiltynode1 = $xpath->query("/Liabilities/Assets_Name/Group_Name");
			$liabiltynode2 = $xpath->query("/Liabilities/Assets_Name/Code_No");
			$liabiltynode3 = $xpath->query("/Liabilities/Assets_Name/Amount");
			$liabiltynode4 = $xpath->query("/Liabilities/Assets_Name/Code_Nu");
			$liabiltynode5 = $xpath->query("/Liabilities/Assets_Name/Counter");

        	        $liabiltylist1 = @$liabiltynode1->item($i)->nodeValue;
                	$liabiltylist2 = @$liabiltynode2->item($i)->nodeValue;
	                $liabiltylist3 = @$liabiltynode3->item($i)->nodeValue;
	                $liabiltylist4 = @$liabiltynode4->item($i)->nodeValue;
	                $liabiltylist5 = @$liabiltynode5->item($i)->nodeValue;
			$lib = substr($liabiltylist3, 2); 
			$lib = str_replace(',', '', $lib);
			$sumamt = $sumamt+$lib; 
			//$sumamt = $sumamt .".00";
			//$sumamt = $sumamt ;
		}

		//Storing merged data in a merge xml file

		$doc = new DOMDocument();
                $doc->formatOutput = true;

                $file_name=$mergefile."_Assets.xml";
                $tt=$acctpath."/".$file_name;
                if(file_exists($tt))
                {
			$doc->preserveWhiteSpace = false;
                        $doc->load($tt);
                        $Liabilities = $doc->firstChild;

                        $Liability_Name = $doc->createElement('Assets_Name');

                        $group_name = $doc->createElement('Group_Name');
                        $textNode = $doc->createTextNode($liabiltylist1);
                        $group_name->appendChild($textNode);
                        $Liability_Name->appendChild($group_name);

                        $code_no = $doc->createElement('Code_No');
                        $textNode1 = $doc->createTextNode($liabiltylist2);
                        $code_no->appendChild($textNode1);
                        $Liability_Name->appendChild($code_no);

                        $amount = $doc->createElement('Amount');
                        $textNode2 = $doc->createTextNode($sumamt);
                        $amount->appendChild($textNode2);
                        $Liability_Name->appendChild($amount);

                        $codenu = $doc->createElement('Code_Nu');
                        $textNode3 = $doc->createTextNode($liabiltylist4);
                        $codenu->appendChild($textNode3);
                        $Liability_Name->appendChild($codenu);

                        $counter = $doc->createElement('Counter');
                        $textNode4 = $doc->createTextNode($liabiltylist5);
                        $counter->appendChild($textNode4);
                        $Liability_Name->appendChild($counter);

                        $Liabilities->appendChild($Liability_Name);
                        $ttt=$doc->saveXML();
                        $handle = fopen($tt, "w");
                        fwrite($handle, $ttt);
                        fclose($handle);
		}
                else
                {
                	$r = $doc->createElement( "Liabilities" );
                        $doc->appendChild( $r );
                        $b = $doc->createElement( "Assets_Name" );

                        $group_name = $doc->createElement( "Group_Name" );
                        $group_name->appendChild($doc->createTextNode($liabiltylist1));
                        $b->appendChild( $group_name );

                        $code_no = $doc->createElement( "Code_No");
                        $code_no->appendChild($doc->createTextNode($liabiltylist2));
                        $b->appendChild( $code_no );

                        $amount = $doc->createElement( "Amount");
                        $amount->appendChild($doc->createTextNode($sumamt));
                        $b->appendChild( $amount );

                        $codenu = $doc->createElement('Code_Nu');
			$codenu->appendChild($doc->createTextNode($liabiltylist4));
                        $b->appendChild( $codenu );

                        $counter = $doc->createElement('Counter');
			$counter->appendChild($doc->createTextNode($liabiltylist5));
                        $b->appendChild( $counter );


                        $r->appendChild( $b );
                        $doc->save($tt);
                        $doc->saveXML();
		}
	}//end

	//code for displaying merged balancesheet.

        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');

        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';

        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
	echo "                                               Aggregate Balancesheet of Accounts --".$acountlist;echo"</br></br></br>";
        echo "<thead><tr><th></th><th>Schedule</th><th>Current Year<br>$fyear</th></tr></thead>";
        echo "<tr>";
        echo "<td colspan=\"4\" class=\"bold\">";
        echo "Sources Of Funds";
        echo "</td>";
        echo "</tr>";

		//Display merged Balancesheet.

                for($j=0;$j<56;$j++)
                {
                        $file_name=$mergefile."_Liabilty.xml";
                        $tt=$acctpath."/".$file_name;
                        $doc = new DomDocument();
                        $doc->formatOutput = true;
                        $doc->load($tt);
                        $xpath = new DomXPath($doc);

                        $xpath->query("/Liabilities/Liability_Name/Group_Name");
                        $xpath->query("/Liabilities/Liability_Name/Code_No");
                        $xpath->query("/Liabilities/Liability_Name/Amount");
                        $xpath->query("/Liabilities/Liability_Name/Code_Nu");
                        $xpath->query("/Liabilities/Liability_Name/Counter");

                        $liabiltynode1 = $xpath->query("/Liabilities/Liability_Name/Group_Name");
                        $liabiltynode2 = $xpath->query("/Liabilities/Liability_Name/Code_No");
                        $liabiltynode3 = $xpath->query("/Liabilities/Liability_Name/Amount");
                        $liabiltynode4 = $xpath->query("/Liabilities/Liability_Name/Code_Nu");
                        $liabiltynode5 = $xpath->query("/Liabilities/Liability_Name/Counter");

                        $liabiltylist1 = @$liabiltynode1->item($j)->nodeValue;
                        $liabiltylist2 = @$liabiltynode2->item($j)->nodeValue;
                        $liabiltylist3 = @$liabiltynode3->item($j)->nodeValue;
                        $liabiltylist4 = @$liabiltynode4->item($j)->nodeValue;
                        $liabiltylist5 = @$liabiltynode5->item($j)->nodeValue;
			if($liabiltylist5 == 4)
			{
				echo "<tr class=\"tr-group\">";
	
		                        echo "<td class=\"td-group\">";
					echo $liabiltylist1;
               			        echo "</td>";
					echo "<td class=\"td-group\">";
					echo $liabiltylist2;
					echo "</td>";
					echo "<td align=\"right\">";
					echo "Cr ".money_format('%!i', convert_cur($liabiltylist3));
					//echo "Cr ".$liabiltylist3;
                                	echo "</td>";
				echo "</tr>";
			}
                        if($liabiltylist5 == 6)
                        {
                                echo "<tr>";
                                        echo "<td class=\"td-group\">";
                                        echo "&nbsp;&nbsp;&nbsp;".$liabiltylist1;
                                        echo "</td>";
                                        echo "<td class=\"td-group\">";
                                        echo "</td>";
					echo "<td align=\"right\">";
					echo "Cr ".money_format('%!i', convert_cur($liabiltylist3));
					//echo "Cr ".$liabiltylist3;
                                        echo "</td>";
                                echo "</tr>";
                        }
                }
        echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total";
        echo "</td>";
        echo "<td>"; 
        echo "</td>";
        echo "<td align=\"right\" class=\"bold\">";
                echo money_format('%!i', convert_cur($mergeliability_total));
        echo "</td>";

        echo "</tr>";


        //Display Source of funds.      

        echo "<tr>";
        echo "<td colspan=\"4\" class=\"bold\">";
        echo "Application Of Funds";
        echo "</td>";
        echo "</tr>";

                for($j=0;$j<50;$j++)
                {
                        $file_name=$mergefile."_Assets.xml";
                        $tt=$acctpath."/".$file_name;
                        $doc = new DomDocument();
                        $doc->formatOutput = true;
                        $doc->load($tt);
                        $xpath = new DomXPath($doc);

                        $xpath->query("/Liabilities/Assets_Name/Group_Name");
                        $xpath->query("/Liabilities/Assets_Name/Code_No");
                        $xpath->query("/Liabilities/Assets_Name/Amount");
                        $xpath->query("/Liabilities/Assets_Name/Code_Nu");
                        $xpath->query("/Liabilities/Assets_Name/Counter");

                        $liabiltynode1 = $xpath->query("/Liabilities/Assets_Name/Group_Name");
                        $liabiltynode2 = $xpath->query("/Liabilities/Assets_Name/Code_No");
                        $liabiltynode3 = $xpath->query("/Liabilities/Assets_Name/Amount");
                        $liabiltynode4 = $xpath->query("/Liabilities/Assets_Name/Code_Nu");
                        $liabiltynode5 = $xpath->query("/Liabilities/Assets_Name/Counter");

                        $liabiltylist1 = @$liabiltynode1->item($j)->nodeValue;
                        $liabiltylist2 = @$liabiltynode2->item($j)->nodeValue;
                        $liabiltylist3 = @$liabiltynode3->item($j)->nodeValue;
                        $liabiltylist4 = @$liabiltynode4->item($j)->nodeValue;
                        $liabiltylist5 = @$liabiltynode5->item($j)->nodeValue;
			if($liabiltylist5 == 4)
			{
				echo "<tr class=\"tr-group\">";
	
		                        echo "<td class=\"td-group\">";
					echo $liabiltylist1;
               			        echo "</td>";
					echo "<td class=\"td-group\">";
					echo $liabiltylist2;
					echo "</td>";
                	                //echo "<td class=\"td-group\">";
					echo "<td align=\"right\">";
					//echo "Cr ".$liabiltylist3;
					echo "Dr ".money_format('%!i', convert_cur($liabiltylist3));
                                	echo "</td>";
				echo "</tr>";
			}
                        if($liabiltylist5 == 6)
                        {
                                echo "<tr>";
                                        echo "<td class=\"td-group\">";
                                        echo "&nbsp;&nbsp;&nbsp;".$liabiltylist1;
                                        echo "</td>";
                                        echo "<td class=\"td-group\">";
                                        echo "</td>";
					echo "<td align=\"right\">";
					echo "Dr ".money_format('%!i', convert_cur($liabiltylist3));
                                        echo "</td>";
                                echo "</tr>";
                        }
                }
        echo "<tr>";
        echo "<td class=\"bold\">";
        echo "Total";
        echo "</td>";
	echo "<td>"; 
	echo "</td>";
	echo "<td align=\"right\" class=\"bold\">";
		echo money_format('%!i', convert_cur($mergeassets_total));
	echo "</td>";
	
        echo "</tr>";

        echo "<tr>";
                echo "<td class=\"bold\">";
                        echo "Notes On Accounts";
                echo "</td>";

                echo "<td>";
                        echo "&nbsp;" . anchor_popup('notes/display_notes', '22', array('title' => 'Notes On Accounts', 'style' => 'color:#000000'));
                echo "</td>";

                echo "<td>";
                echo "</td>";

                echo "<td>";
                echo "</td>";
        echo "</tr>";


	
        echo "</table>";

?>
