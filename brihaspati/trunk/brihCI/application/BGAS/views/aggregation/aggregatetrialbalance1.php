<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	setlocale(LC_MONETARY, 'en_IN');
//    echo 'Initial: ' . number_format(memory_get_usage(), 0, '.', ',') . " bytes\n";
    $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
    $ttt2=$acctpath."/".$mergetrbfile;
            /**** merging of xml files ****/

            /* Fisrt we will select 2nd account from accountlist(beacuse ist account is already copied in
             * megerd account file(example-username_trbal.xml).Then we take ist node from 2nd 
             * account and search it in merge account,
             * Case 1st if its found so we just match accname if its match so accname is same otherwise 
             * accname will be combined and merge file will be update with all new attribute.
             * Case 2nd if its not found append in merge file.
             */
        $tem=0;
        foreach ($accounts as $key => $value)
        {
                $accname = $value;
                $accarray[$tem] = $value;
                $tem = $tem + 1;
        }

        for($j=1;$j<sizeof($accarray);$j++)
        {
            $accname1 = $accarray[$j];
            $file_name = $username."_".$accname1."_trbal.xml";
            $tt = $acctpath."/".$file_name;
            $doc = new DomDocument('1.0', 'UTF-8');
            $doc->formatOutput = true;
            $doc->load($tt,LIBXML_PARSEHUGE);
            $els = $doc->getElementsByTagName('Code');
            for ($i = 0; $i < $els->length; $i++ )
            {
                $el = $els->item($i);
                $accid1 = $el->getAttribute('id');
                $acc_name1 =  $el->getAttribute('name');    
                $opbal =  $el->getAttribute('opbalance');    
                $clbal =  $el->getAttribute('clbalance');    
                $drtotal =  $el->getAttribute('drtotal');    
                $crtotal =  $el->getAttribute('crtotal');    
                
                $flag1 = false;

                $doc1 = new DOMDocument('1.0', 'UTF-8');
                $doc1->formatOutput = true;
                $doc1->load($ttt2,LIBXML_PARSEHUGE);
            
            

                foreach ($doc1->getElementsByTagName('Code') as $node)
                {
                    if(!($node->getAttribute('id') == $accid1))
                    {
                        $flag1 = true;
                    }
                    else
                    {
                        $flag1 = false;
                        break;
                    }
                }
                if($flag1 == false)
                {
                try{
                    for($k=0;$k<$max1;$k++)
                    {
                        $xpath1 = new DomXPath($doc1);
                        $trbal1 = $xpath1->query("/Tbalance/Code");
                        $accid2 = @$trbal1->item($k)->getAttribute('id');
                        if($accid2 == $accid1)
                        {
                            $acc_name2 = @$trbal1->item($k)->getAttribute('name');
                            if($acc_name1 == $acc_name2)
                                $acc_name2 = $acc_name1;
                            else
                                $acc_name2 = $acc_name1 ."+". $acc_name2;
                            $opbal1 = @$trbal1->item($k)->getAttribute('opbalance');
                               
                                //sum of opening balances                                
 
                            if ($opbal != '0')
                            {
                                $result_op = substr($opbal, 0, 2);
                                $resultrest_op = substr($opbal, 2);
                                $resultop = str_replace(',', '', $resultrest_op);

                                $result_op1 = substr($opbal1, 0, 2);
                                $resultrest_op1 = substr($opbal1, 2);
                                $resultop1 = str_replace(',', '', $resultrest_op1);
                                    
                                if ($result_op == $result_op1)
                                    $finalop = $resultop + $resultop1;
                                else
                                {
                                    if($resultop >$resultop1)
                                    {
                                        $finalop = $resultop - $resultop1;
                                        $finalop = $result_op.$finalop;
                                    }
                                    else
                                    {
                                        $finalop = $resultop1 - $resultop;
                                        $finalop = $result_op1.$finalop;
                                    }
                                }                    
                            }
                            else
                            {
                                $result_op1 = substr($opbal1, 0, 2);
                                $resultrest_op1 = substr($opbal1, 2);
                                $resultop1 = str_replace(',', '', $resultrest_op1);
                                $finalop = $opbal1;
                            }
                                //closing balance 
                            
                            $clbal1 = @$trbal1->item($k)->getAttribute('clbalance');
                            if ($clbal != '0')
                            {
                                $result_cl = substr($clbal, 0, 2);
                                $resultrest_cl = substr($clbal, 2);
                                $resultcl = str_replace(',', '', $resultrest_cl);

                                $result_cl1 = substr($clbal1, 0, 2);
                                $resultrest_cl1 = substr($clbal1, 2);
                                $resultcl1 = str_replace(',', '', $resultrest_cl1);
                                if ($result_cl == $result_cl1)
                                    $finalcl = $resultcl + $resultcl1;
                                else
                                {
                                    if($resultcl >$resultcl1)
                                    {
                                        $finalcl = $resultcl - $resultcl1;
                                        $finalcl = $result_cl.$finalcl;
                                    }
                                    else
                                    {
                                        $finalcl = $resultcl1 - $resultcl;
                                        $finalcl = $result_cl1.$finalcl;
                                    }
                                }                    
                            }
                            else
                            {
                                $result_cl1 = substr($clbal1, 0, 2);
                                $resultrest_cl1 = substr($clbal1, 2);
                                $resultcl1 = str_replace(',', '', $resultrest_cl1);
                                $finalcl = $clbal1;
                            }

                                //calculating drtotal

                            $drtotal1 = @$trbal1->item($k)->getAttribute('drtotal');
                            $resultdr = str_replace(',', '', $drtotal);       
                            $resultdr1 = str_replace(',', '', $drtotal1);
                            $finaldr = $resultdr + $resultdr1;
                            //calculating crtotal

                            $crtotal1 = @$trbal1->item($k)->getAttribute('crtotal');
                            $resultcr = str_replace(',', '', $crtotal);     
                            $resultcr1 = str_replace(',', '', $crtotal1);
                            $finalcr = $resultcr + $resultcr1;
                            $trbal1->item($k)->setAttribute('name', $acc_name2);
                            $trbal1->item($k)->setAttribute('opbalance', $finalop);
                            $trbal1->item($k)->setAttribute('clbalance', $finalcl);
                            $trbal1->item($k)->setAttribute('drtotal', $finaldr);
                            $trbal1->item($k)->setAttribute('crtotal', $finalcr);
                                
                            $temp1 = $doc1->saveXML();
                            $handle = fopen($ttt2, "w");
                            fwrite($handle, $temp1);
                            fclose($handle);
                            break;
                        $ttt2=$acctpath."/".$mergetrbfile;
                        }
                    }
                    }
                    catch (Exception $e)
                    {
                            echo "Exception is--->".$e->getMessage() ;
                            continue;
                    }

                }//flag1=flase
                else
                {
                    $result_cl = substr($clbal, 0, 2);
                    $resultrest_cl = substr($clbal, 2);
                    $resultcl = str_replace(',', '', $resultrest_cl);                
                    $result_op = substr($opbal, 0, 2);
                    $resultrest_op = substr($opbal, 2);
                    $resultop = str_replace(',', '', $resultrest_op);
                        //$max1=$max1+1;
                    $Tbalance = $doc1->firstChild;
                    $Code = $doc1->createElement('Code');
                    $Code->setAttribute('id', $accid1);
                    $Code->setAttribute('name', $acc_name1);
                    $Code->setAttribute('opbalance', $resultop);
                    $Code->setAttribute('clbalance', $resultcl);
                    $Code->setAttribute('drtotal', $drtotal);
                    $Code->setAttribute('crtotal', $crtotal);
                    $Tbalance->appendChild($Code);
                    $ttt=$doc1->saveXML();
                    $handle = fopen($ttt2, "w");
                    fwrite($handle, $ttt);
                    fclose($handle);
                }
                
            }

    
        }//end for main
        $accname2 = '';
        foreach ($accounts as $key => $value)
        {
            $accname2 = $accname2.",".$value;
                $tem++;
        }

    echo form_open();
        echo "<p>";
        echo "Username :";
        echo $username;
        echo "<br />";
        echo "</p>";

        echo "<p>";
        echo "Aggregation of accounts :";
        echo $accname2;
        echo "<br />";
        echo "</p>";


        //echo "Test1";
    echo "<table border=0 cellpadding=5 class=\"simple-table trial-balance-table\" width=\"$width\">";
    echo "<thead><tr><th>Ledger Account</th><th>O/P Balance</th><th>C/L Balance</th><th>Dr Total</th><th>Cr Total</th></tr></thead>";
        $doc = new DomDocument;
        $doc->formatOutput = true;
        $doc->load($ttt2);
        $xpath = new DomXPath($doc);

        //get length of merge budget file.
        $len1 = $doc->getElementsByTagName('Code')->length;
        for($j=0;$j<$len1;$j++)
        {
                $trbald = $xpath->query("/Tbalance/Code");
                $idd = $trbald->item($j)->getAttribute('id');
                $named = $trbald->item($j)->getAttribute('name');
                
                $opbald = $trbald->item($j)->getAttribute('opbalance');
                if ($opbald == '')
                    $opbald = '0';
                $clbald = $trbald->item($j)->getAttribute('clbalance');
                $drtotald = $trbald->item($j)->getAttribute('drtotal');
                $crtotald = $trbald->item($j)->getAttribute('crtotal');

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $named;
                echo "</td>";
               echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $opbald;
                echo "</td>";
               echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $clbald;
                echo "</td>";
               echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $drtotald;
                echo "</td>";
               echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $crtotald;
                echo "</td>";
                echo "</tr>";
        }
        
        echo "<tr class=\"tr-total\"><td colspan=\"1\">TOTAL ";
        echo "<img src=\"" . asset_url() . "assets/bgas/images/icons/match.png\">";
    echo "</td><td> " . $netopbal . "</td><td> " . $netclbal . "</td><td>Dr " .$netdrtotal  . "</td><td>Cr " .$netcrtotal . "</td></tr>";

    
echo "</table>";
	echo form_close();

?>
