<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/** @author sharad singh<sharad23nov@yahoo.com> **/

class AggregatePayRec
{

	function __construct()
    {
        return;
    }
		
	function aggpayrec($type, $accname, $username)
	{
		//echo $accname;echo "<br>";
		$i=0;
		$c=0;
		$sum=0;
		$total=0;
		$total1=0;
		$total2=0;
		$pre_total=0;
		$type_total = 0;
		$paymentlist2 = "";
		$receiptlist2="";
		$CI =& get_instance();
		$this->counter = $c;

        //get database detail of account

        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }

		$CI->load->model('Payment_model');
		$CI->load->library('session');
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
		if($type == "Payment")//if 1
        {
            try 
            {
                $mgroup = "select name,code,id from groups where parent_id <= '4' and parent_id!='3' and parent_id!='0'";
                $stmt = $dbcon->query($mgroup);
	            if($stmt != false) //if 2 
        	    {
                    foreach ($stmt as $row)//foreach 1
                    {
                        $groupid = $row['id'];
                        $ledg_id = $row['id'];
                        $name = $row['name'];
                        $code = $row['code'];
		                $dr_sum = 0;
                		$cr_sum = 0;
                        $dr_sum_total= 0;
		                $cr_sum_total=0;
                        $total =0;
						if($name != 'Depreciation' && $name != 'Current Assets' && $name != 'Committed Fund') //if 3
						{
						    $mledger = "select * from ledgers where code LIKE '$code%' and type != '1'";
							$stmt1 = $dbcon->query($mledger);
							foreach ($stmt1 as $row1)//foreach 2
							{
			                    $ledger_code = $row1['code'];
        			            $ledger_code = substr($ledger_code, 0, 2);
								$id = $row1['id'];
								if($ledger_code  != '40')//if 4	
								{
							        $queryjoin = "select  sum(amount) from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = $id and entry_items.dc = 'D'";
									$dr_total_q = $dbcon->query($queryjoin);
									foreach ($dr_total_q as $row2)//foreach 3
									{
									    $dr_total = $row2['sum(amount)'];
									}//foreach 3
									$total = float_ops($total, $dr_total, '+');
                                  //
                                //echo $total;
								}//if 4
								elseif($ledger_code == '40')//if 5
								{
                                    $queryjoin = "select amount, dc from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = $id";
                                    $qresult = $dbcon->query($queryjoin);
                                    foreach ($qresult as $row3)//foreach 4
                                    {
                                        $dc = $row3['dc'];
                                        if($dc == "D")
                                        {
                                            $dr_sum = $row3['amount'];
                                            $dr_sum_total = $dr_sum_total + $dr_sum;
                                            //echo "<br>";
                                        }          
                                        else
                                        {
                                            $cr_sum = $row3['amount'];
                                            $cr_sum_total = $cr_sum_total + $cr_sum;
                                        }
                                        $total = $dr_sum_total - $cr_sum_total;
                                        
                                        //echo $total1=$total1+$total ;echo "<br>";
                                    }//foreach 4        
                                    
                                    //$total = $dr_sum_total - $cr_sum_total;
					   			}//if 5
							}//foreach 2
                            //echo $total1=$total1+$total ;echo "<br>";
                            //echo $total;echo "<br>";
                            /*** xml file handling for payment of accounts ***/

                            //payment and Receipt data will be stored in seperate xml files of accounts.

                            //path for storing xml file
                            
                            $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');                
                            $paymentfile = $username."_".$accname."_pay.xml";

                            $doc = new DOMDocument();
                            $doc->formatOutput = true;
                            $tt = $acctpath."/".$paymentfile;
                            if(file_exists($tt))
                            {
                                        $doc->preserveWhiteSpace = false;
                                        $doc->load($tt);
                                        $Budgets = $doc->firstChild;
                                        $Code = $doc->createElement('Code');
                                        $Code->setAttribute('id', $groupid);
                                        $Code->setAttribute('code', $code);
                                        $Code->setAttribute('name', $name);
                                        $Code->setAttribute('total', $total);

                                        $Budgets->appendChild($Code);

                                        $ttt=$doc->saveXML();
                                        $handle = fopen($tt, "w");
                                        fwrite($handle, $ttt);
                                        fclose($handle);
                            }
                            else
                            {
                                        $r = $doc->createElement( 'Payment' );
                                        $doc->appendChild($r);

                                        $Code = $doc->createElement('Code');
                                        $Code->setAttribute('id', $groupid);
                                        $Code->setAttribute('code', $code);
                                        $Code->setAttribute('name', $name);
                                        $Code->setAttribute('total', $total);

                                        $r->appendChild($Code);

                                        $doc->save($tt);
                                        $doc->saveXML();
                            }
                        
                            //end payment xml file creation
                                            
					    }//if 3	    
                        $total2=float_ops($total2, $total, '+');
                        $this->total=$total2;
                    }//foreach1 
                }//if 2
            }
        	catch(PDOException $e)
            {
                echo $e->getMessage();
            }
            //echo $total1;
		}//if 1 
                
        /**** code for receipt ****/

        else
        {
            $mgroup = "select name,code,id from groups where parent_id <= '3' and parent_id!='0'";
            $stmt = $dbcon->query($mgroup);
            if($stmt != false) //if 2 
            {
                    foreach ($stmt as $row)//foreach 1
                    {
                        $groupid = $row['id'];
                        $ledg_id = $row['id'];
                        $name = $row['name'];
                        $code = $row['code'];
                        $dr_sum = 0;
                        $cr_sum = 0;
                        $dr_sum_total= 0;
                        $cr_sum_total=0;
                        $total =0;
                        if($name !=  'Depreciation' && $name !=  'Current Assets' && $name !=  'Committed Fund')
                        {
                            $mledger = "select * from ledgers where code LIKE '$code%' and type != '1'";
                            $stmt1 = $dbcon->query($mledger);
                            foreach ($stmt1 as $row1)//foreach 2
                            {
                                $ledger_code = $row1['code'];
                                $ledger_code = substr($ledger_code, 0, 2);
                                $id = $row1['id'];
                                if($ledger_code != "30")
                                {
                                    $queryjoin = "select  sum(amount) from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = $id and entry_items.dc = 'C'";
                                    $dr_total_q = $dbcon->query($queryjoin);
                                    foreach ($dr_total_q as $row2)//foreach 3
                                    {
                                        $cr_total = $row2['sum(amount)'];
                                    }//foreach 3
                                    $total = float_ops($total, $cr_total, '+');
                        
                                }
                                elseif($ledger_code == '30')//if 5
                                {
                                    $queryjoin = "select amount, dc from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = $id";
                                    $qresult = $dbcon->query($queryjoin);
                                    foreach ($qresult as $row3)//foreach 4
                                    {
                                        $dc = $row3['dc'];
                                        if($dc == "D")
                                        {
                                            $dr_sum = $row3['amount'];
                                            $dr_sum_total = $dr_sum_total + $dr_sum;
                                            //echo "<br>";
                                        }
                                        else
                                        {
                                            $cr_sum = $row3['amount'];
                                            $cr_sum_total = $cr_sum_total + $cr_sum;
                                        }
                                        $total = $dr_sum_total - $cr_sum_total;
                                        if ($total < 0)
                                            $total = 0.00 - $total;
                                        else
                                            $total = $total;
                                    }//foreach 4        
                                    //$total1=float_ops($total1, $total, '+');
                                    //$this->total=$total1;

                                    //$total = $dr_sum_total - $cr_sum_total;
                                    //echo $total;
                                }//if 5
                            }
                            
                            /*** xml file handling for receipt of accounts ***/

                            //path for storing xml file
                            
                            $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');                
                            $paymentfile = $username."_".$accname."_rec.xml";

                            $doc = new DOMDocument();
                            $doc->formatOutput = true;
                            $tt = $acctpath."/".$paymentfile;
                            if(file_exists($tt))
                            {
                                        $doc->preserveWhiteSpace = false;
                                        $doc->load($tt);
                                        $Budgets = $doc->firstChild;
                                        $Code = $doc->createElement('Code');
                                        $Code->setAttribute('id', $groupid);
                                        $Code->setAttribute('code', $code);
                                        $Code->setAttribute('name', $name);
                                        $Code->setAttribute('total', $total);

                                        $Budgets->appendChild($Code);

                                        $ttt=$doc->saveXML();
                                        $handle = fopen($tt, "w");
                                        fwrite($handle, $ttt);
                                        fclose($handle);
                            }
                            else
                            {
                                        $r = $doc->createElement( 'Payment' );
                                        $doc->appendChild($r);

                                        $Code = $doc->createElement('Code');
                                        $Code->setAttribute('id', $groupid);
                                        $Code->setAttribute('code', $code);
                                        $Code->setAttribute('name', $name);
                                        $Code->setAttribute('total', $total);

                                        $r->appendChild($Code);

                                        $doc->save($tt);
                                        $doc->saveXML();
                            }
                        }                    
                        $total1=float_ops($total1, $total, '+');
                        $this->total=$total1;

                    }                            
            }
        }
	}//end of function
}
?>
