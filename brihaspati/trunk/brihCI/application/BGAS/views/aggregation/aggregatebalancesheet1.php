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

<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');

        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "Username :".$username;echo"</br></br>";
        echo "Aggregate Balancesheet of Accounts --".$mergefile;echo"</br></br></br>";
        echo "<thead><tr><th>Name</th><th>Schedule</th><th>Current Year<br>$fyear</th></tr></thead>";
        echo "<tr>";
        echo "<td colspan=\"4\" class=\"bold\">";
        echo "Sources Of Funds";
        echo "</td>";
        echo "</tr>";

        //Display merged Balancesheet.

            for($j=0;$j<3;$j++)
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

                        $liabiltynode1 = $xpath->query("/Liabilities/Liability_Name/Group_Name");
                        $liabiltynode2 = $xpath->query("/Liabilities/Liability_Name/Code_No");
                        $liabiltynode3 = $xpath->query("/Liabilities/Liability_Name/Amount");
                        $liabiltynode4 = $xpath->query("/Liabilities/Liability_Name/Code_Nu");

                        $liabiltylist1 = @$liabiltynode1->item($j)->nodeValue;
                        $liabiltylist2 = @$liabiltynode2->item($j)->nodeValue;
                        $liabiltylist3 = @$liabiltynode3->item($j)->nodeValue;
                        if($liabiltylist3<0)
                            $liabiltylist3 = 0-$liabiltylist3;
                        else
                            $liabiltylist3 = $liabiltylist3;
                        $liabiltylist4 = @$liabiltynode4->item($j)->nodeValue;
                echo "<tr class=\"tr-group\">";

                                echo "<td class=\"td-group\">";
                    echo $liabiltylist1;
                                echo "</td>";
                    echo "<td class=\"td-group\">";
                    echo $liabiltylist2;
                    echo "</td>";
                    echo "<td align=\"right\">";
                    echo money_format('%!i', convert_cur($liabiltylist3));
                    //echo "Cr ".$liabiltylist3;
                                    echo "</td>";
                echo "</tr>";
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

        for($j=0;$j<7;$j++)
        {
            $file_name=$mergefile."_Assets.xml";
            $tt=$acctpath."/".$file_name;
            $doc->load($tt);
            $xpath = new DomXPath($doc);

                $income = $xpath->query("/Liabilities/Assets_Name");
                $code = $income->item($j)->getAttribute('code');
                $name = $income->item($j)->getAttribute('name');
                $schedule = $income->item($j)->getAttribute('schedule');
                $itotal = $income->item($j)->getAttribute('amount');



                    echo "<tr class=\"tr-group\">";

                                echo "<td class=\"td-group\">";
                    echo $name;
                                echo "</td>";
                    if(($code!=200101) && ($code!=200102))
                    {
                    echo "<td class=\"td-group\">";
                    echo $schedule;
                    echo "</td>";
                    echo "<td align=\"right\">";
                    }
                    echo money_format('%!i', convert_cur($itotal));
                                    echo "</td>";
                echo "</tr>";
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

 
