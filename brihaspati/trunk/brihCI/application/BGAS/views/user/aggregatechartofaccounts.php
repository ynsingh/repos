<?php
	echo form_open('user/aggregatechartofaccounts');
	//ini_set('memory_limit','1024M');
	ini_get('display_errors');
        $this->load->model('Ledger_model');
        $this->load->library('Aggregatecoflist');
	$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
	$accarray = explode(",", $mergeaccfile);
	$file_name1=$mergeaccfile."_cof.xml";
	$ttdel1=$acctpath."/".$file_name1;
	echo "Aggregate chart of accounts - ". $mergeaccfile;

                echo "<table>";
                echo "<tr valign=\"top\">";
                echo "<td>";
                echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
                echo "<thead><tr><th>Account Code </th><th>Account Name</th><th>Type</th><th>O/P Balance</th><th>C/L Balance</th><th></th></tr></thead>";

        for($i = 0; $i<sizeof($accarray); $i++)
        {
        	$accname = $accarray[$i];

        	$alllist = new Aggregatecoflist();
        	$alllist->init(0,$accname);

		$alllist->account_st_main(-1,$accname);
	}
	/************* Merging of accounts xml file *************/	
                // get max length of all xml file
                $length1 = array();
                for($i = 0 ; $i<sizeof($accarray); $i++)
                {
                        $accname=$accarray[$i];

                        $file_name1=$accname."_cof.xml";
                        $tt1=$acctpath."/".$file_name1;

                        $doc2 = new DomDocument;
                        if (file_exists($tt1))
                        {
                                $doc2->load($tt1);

                                // count all <Code/> elements
                                $len1 = $doc2->getElementsByTagName('Code')->length;

                        }
                        $length1[$i] = $len1;
                }

                //copy the fisrt account xml file in to merge accounts file
                $max1 = 0;
                if(sizeof($length1)!==0)
                {
                        $max1 = max($length1);
                }
		//storing all data of ist account in merge file.
                $accist = "";
                $accist =$accarray[0];
                $accist = $accist."_cof.xml";

                $mergefilename = $mergeaccfile."_cof.xml";
                $ttt1 = $acctpath."/".$accist;
                $ttt=$acctpath."/".$mergefilename;
                $file = fopen($ttt1, 'rb');
                $newfile = fopen($ttt, 'wb');
                while(($line = fgets($file)) !== false) {
                        fputs($newfile, $line);
                }

        /* Fisrt we will select 2nd account from accountlist(beacuse ist account is already stored in
         * megerd account list.Then we take ist node from 2nd account and search it in merge account,
         * Case ist if its found so we just match accname if its match so accname is same otherwise 
         * accname will be combined and merge file will be update with all new attribue.
         * Case2nd if its not found append in merge file.
         */
//		$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
                for($j=1;$j<sizeof($accarray);$j++)
                {
                        $accname1=$accarray[$j];
                        $file_name=$accname1."_cof.xml";
                        $tt=$acctpath."/".$file_name;
                        $doc = new DomDocument('1.0', 'UTF-8');
                        $doc->formatOutput = true;
                        $doc->load($tt,LIBXML_PARSEHUGE);
                        $xpath = new DomXPath($doc);

                        for($i=0;$i<$max1;$i++)
                        {

        //Below two lines are creating problem..find the solution
        //I have put 0 in place $i ,change it if test not succeed.

                                $acccode = $xpath->query("/Accounts/Code");
                                $accid = $acccode->item($i)->getAttribute('id');
                                $acc_name = $acccode->item($i)->getAttribute('accname');
                                $type1 = $acccode->item($i)->getAttribute('type');
                                $opbalance = $acccode->item($i)->getAttribute('op_balance');
                                $clbalance = $acccode->item($i)->getAttribute('cl_balance');
                                $accgroupid = $acccode->item($i)->getAttribute('Group_id');

                                $flag = false;
                                //echo $ttt;
                                $doc1 = new DOMDocument('1.0', 'UTF-8');
                                $doc1->formatOutput = true;
                                //$doc1->load($tt1);
                                $doc1->load($ttdel1,LIBXML_PARSEHUGE);
                                //print_r($doc1->getElementsByTagName('id'));
                                //$t=0;
                                foreach ($doc1->getElementsByTagName('Code') as $node)
                                {
                                        $node->getAttribute('id');
                                        if(!($node->getAttribute('id')== $accid))
                                        {
                                                $flag=true;
                                        }
                                        else
                                        {
                                                $flag=false;
                                                break;
                                        }

                                }
				//echo 'Initial: ' . number_format(memory_get_usage(), 0, '.', ',') . " bytes\n";
				//ini_set('memory_limit','1024M');
				ini_set('max_execution_time', 120);
                                if($flag==false)
                                {	
					try{
                                        for($k=0; $k<=$max1; $k++)
                                        {
                                                $sumopbalance = 0.00;
                                                $sumclbalance = 0.00;

                                                $xpath1 = new DomXPath($doc1);
                                                $acccode2 = $xpath1->query("/Accounts/Code");
						
                                                $accid2 = $acccode2->item($k)->getAttribute('id');
                                                if($accid == $accid2)
                                                {
                                                        $acc_name2 = $acccode2->item($k)->getAttribute('accname');
							
                                                        if($acc_name == $acc_name2)
                                                        {
                                                                $acc_name = $acc_name;
                                                        }
                                                        else
                                                        {
                                                                $acc_name = $acc_name. "+" .$acc_name2;
                                                        }
                                                        $opbalance2 = $acccode2->item($k)->getAttribute('op_balance');
                                                        $clbalance2 = $acccode2->item($k)->getAttribute('cl_balance');
							$type2 = $acccode->item($k)->getAttribute('type');

				                        $opbalance2 = substr($opbalance2, 2);
				                        $opbalance = substr($opbalance, 2);
							$opbalance2 = str_replace(',', '',$opbalance2);
							$opbalance = str_replace(',', '',$opbalance);
//							echo "op1--->".$opbalance2."=op2--->".$opbalance."<br>";
//				                        $cord = substr($opbalance2, 0, 2);
//				                        $cord1 = substr($opbalance, 0, 2);

							$clbalance2 = substr($clbalance2, 2);
                                                        $clbalance = substr($clbalance, 2);
							$clbalance = str_replace(',', '',$clbalance);
							$clbalance2 = str_replace(',', '',$clbalance2);
//							echo "cp1--->".$clbalance2."=cp2--->".$clbalance."<br>";
				                        /*$lib = str_replace(',', '', $lib);
				                        $sumamt = $sumamt+$lib;
				                        $sumamt = $sumamt .".00";
							*/

					/** I have to find out the solution if one entry is cr and other one is dr
					 *  so what will be + or -.discuss with someone.
					 **/
							

							//echo "Value **** ";
							if($type1 == "Group Account")
							{
								$sumopbalance="-";
								$sumclbalance="-";
							}
							else
							{
                                                        $sumopbalance = $opbalance + $opbalance2;
							//$sumopbalance = "Dr "
							//echo "****";
                                                        $sumclbalance = $clbalance + $clbalance2;
							}

							$acccode2->item($k)->setAttribute('id', $accid);
							$acccode2->item($k)->setAttribute('accname', $acc_name);
							$acccode2->item($k)->setAttribute('type', $type2);
                                                        $acccode2->item($k)->setAttribute('op_balance', $sumopbalance);
                                                        $acccode2->item($k)->setAttribute('cl_balance', $sumclbalance);
							$acccode2->item($k)->setAttribute('Group_id', $accgroupid);

                                                        $tttt = $doc1->saveXML();

                                                        $handle = fopen($ttdel1, "w");
                                                        fwrite($handle, $tttt);
                                                        fclose($handle);
							//eho $acc_name ."||".$acc_name		
                                                        break;
                                                }

                                        }
					}
					catch (Exception $e)
					{ 
					        echo "Exception is--->".$e->getMessage() ;
				                //continue;
					}
				
                                }
                                else
                                {
                                        $Accounts = $doc1->firstChild;
                                        $Code = $doc1->createElement('Code');
                                        $Code->setAttribute('id', $acccode);

                                        $Code->setAttribute('accname', $acc_name1);
                                        $Code->setAttribute('type', $type1);
                                        $Code->setAttribute('op_balance', $opbalance);
                                        $Code->setAttribute('cl_balance', $clbalance);
                                        $Code->setAttribute('Group_id', $accgroupid);

                                        $Accounts->appendChild($Code);

                                        $ttt=$doc1->saveXML();
                                        $handle = fopen($tt, "w");
                                        fwrite($handle, $ttt);
                                        fclose($handle);
                                }
				//unset($doc1);
			//	echo 'final: ' . number_format( memory_get_peak_usage(), 0, '.', ',') . " bytes\n";
                        }
			//echo 'End: ' . number_format(memory_get_usage(), 0, '.', ',') . " bytes\n";
			//__destruct();
		//echo "Test3";
	//	__destruct();
                }

	

	// Display of Aggregate Chart of accounts.

	$reader = new XMLReader();
        $docd = new DomDocument;
        $docd->formatOutput = true;
        $docd->load($ttdel1);
	$xpath = new DomXPath($docd);		
        for($p=0;$p<$max1;$p++)
        {
		$acccodem = $xpath->query("/Accounts/Code");
                $accidm = $acccodem->item($p)->getAttribute('id');
                $acc_namem = $acccodem->item($p)->getAttribute('accname');
                $type1m = $acccodem->item($p)->getAttribute('type');
                $opbalancem = $acccodem->item($p)->getAttribute('op_balance');
                $clbalancem = $acccodem->item($p)->getAttribute('cl_balance');
                $accgroupidm = $acccodem->item($p)->getAttribute('Group_id');

		$opbalancem;
/*		if($type1 == "Group Account")
//                echo "<tr>";
                echo "<tr class=\"tr-group\">";

		else
		echo "<tr class=\"tr-ledger\">";
*/		
		echo "<tr>";
		
                echo "<td class=\"td-group\">";
                if (($accgroupidm <= 4) && ($type1m == "Group Account"))
		echo "&nbsp;&nbsp;&nbsp;<strong>".$accidm. "</strong>";
		else 
		echo "&nbsp;&nbsp;&nbsp;".$accidm ;
                echo "</td>";

                echo "<td class=\"td-group\">";
		if (($accgroupidm <= 4) && ($type1m == "Group Account"))
                echo "&nbsp;&nbsp;&nbsp;<strong>".$acc_namem."</strong>";
		else
                echo "&nbsp;&nbsp;&nbsp;".$acc_namem;
                echo "</td>";

                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".$type1m;
                echo "</td>";

		if($type1m == "Group Account")
		{
                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp; -";
                echo "</td>";
		
		echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp; -";
                echo "</td>";
		}
		else
		{
                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".money_format('%!i',$opbalancem);
                echo "</td>";

                echo "<td class=\"td-group\">";
                echo "&nbsp;&nbsp;&nbsp;".money_format('%!i',$clbalancem);
                echo "</td>";
		echo "</tr>";
		}
	}

	echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";

	echo form_close();
?>
