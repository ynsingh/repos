<?php

class Aggregation extends CI_Controller {
	function __construct() {
        parent::__construct();
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }


    function Aggregation()
    {
        parent::Controller();
        return;
    }

    /**** Aggregation of Income and Expence of Accounts ****/

	function aggregateincexp()
	{
		$this->load->library('session');
		$username = $this->session->userdata('user_name');
		$data['username'] = $username;
		$this->template->set('page_title', 'Aggregation of Income and Expenditure ');
		//$this->template->load('user_template', 'aggregation/aggregateincexp',$data);
		$this->template->load('template', 'aggregation/aggregateincexp',$data);
		return;
	}
    
    function aggregateincexp1()
    {
	   	$this->load->library('session');
		$username = $this->session->userdata('user_name');
		$this->template->set('page_title', 'Aggregation of Income and Expenditure ');
		if ($_POST)
        {
			$data['accounts'] = $this->input->post('accounts', TRUE);
			$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
			$mergeincfile = "";
			$mergeexpfile = "";
			$this->load->library('AggregateIncExp');
			$income_total ;
			$expense_total;
			$te = 0;
			$accarray = array();
			$inctemp = 0;
			$exptemp = 0;	
			$accarray1 = array();
			$tem = 0;
            foreach ($data['accounts'] as $key => $value)
            {
                $accname1 = $value;
                $accarray1[$tem] = $value;
                $tem++;
			}
            /* code for deletion of xml file  */
            
            for($i = 0 ; $i<sizeof($accarray1); $i++)
            {
                $accname1 = $accarray1[$i];
                $file_name1 = $username."_".$accname1."_inc.xml";
                $file_name2 = $username."_".$accname1."_exp.xml";
                $ttdel1 = $acctpath."/".$file_name1;
                $ttdel2 = $acctpath."/".$file_name2;

                //deletion of xml file .
                if (file_exists($ttdel1))
                    unlink($ttdel1);
                if (file_exists($ttdel2))
                    unlink($ttdel2);

            }

            $file_name1 = $username."_inc.xml";
            $ttdel1 = $acctpath."/".$file_name1;
            $file_name2 = $username."_exp.xml";
            $ttdel2 = $acctpath."/".$file_name2;
            
            if (file_exists($ttdel1))
                unlink($ttdel1);
            if (file_exists($ttdel2))
                unlink($ttdel2);


			foreach ($data['accounts'] as $key => $value) 
			{
				$accname = $value;
				$accarray[$te] = $value;
				$te++;
				$income = new AggregateIncExp();
				$income_total = $income->aggincomeexp(3, "view",$accname,$username);				
				$total_inc= $income->total;
				$inctemp = $income_total+$inctemp;

				$expense = new AggregateIncExp();
				$expense_total = $expense->aggincomeexp(4, "view",$accname,$username);
				$exptemp = $expense_total+$exptemp; 
			}

			/*** merging of income and expense accounts file ***/

	                // get max length value around all xml file
			$length2 = array();
        	for($i = 0 ; $i<sizeof($accarray); $i++)
            {
                $accname=$accarray[$i];

	            $file_name1=$username."_".$accname."_inc.xml";
        	    $tt1=$acctpath."/".$file_name1;
	            $file_name2=$username."_".$accname."_exp.xml";
				$tt2=$acctpath."/".$file_name2;

                $doc1 = new DomDocument;
                if (file_exists($tt1))
                {
                    $doc1->load($tt1);

                    // count all <Code/> elements
	                $len1 = $doc1->getElementsByTagName('Code')->length;

        	    }
                $doc1 = new DomDocument;
                if (file_exists($tt2))
                {
                    $doc1->load($tt2);

                    // count all <Code/> elements
                    $len2 = $doc1->getElementsByTagName('Code')->length;

                }

                    $length1[$i] = $len1;
                	$length2[$i] = $len2;
            }

                	//copy the fisrt account xml file in to merge accounts file
                	$max1 = 0;
                	if(sizeof($length1)!==0)
                	{
                        	$max1 = max($length1);
                	}
                        $max2 = 0;
                        if(sizeof($length2)!==0)
                        {
                                $max2 = max($length2);
                        }


			//storing all data of ist account in merge file.

	                $accist = "";
        	        $accist =$username."_".$accarray[0];
                	$accist1 = $accist."_inc.xml";
                	$accist2 = $accist."_exp.xml";

	                $mergeexpfile = $username."_exp.xml";
	                $mergeincfile = $username."_inc.xml";

			//copying ist account xml file data from accounts array into income merge xml file

        	        $ttt1 = $acctpath."/".$accist1;
                	$ttt2=$acctpath."/".$mergeincfile;
	                $file1 = fopen($ttt1, 'rb');
        	        $newfile1 = fopen($ttt2, 'wb');
                	while(($line1 = fgets($file1)) !== false) 
			{
                        	fputs($newfile1, $line1);
                	}
	                fclose($newfile1);
        	        fclose($file1);

			
			//copying ist account xml file data from accounts array into expense merge xml file

			$ttt3 = $acctpath."/".$accist2;
                        $ttt4 = $acctpath."/".$mergeexpfile;
                        $file2 = fopen($ttt3, 'rb');
                        $newfile2 = fopen($ttt4, 'wb');
                        while(($line2 = fgets($file2)) !== false) 
                        {
                                fputs($newfile2, $line2);
                        }
	                fclose($newfile2);
        	        fclose($file2);
			
        	}//end of post
		
		/****          Merging of accounts        ****/

	        /* Fisrt we will select 2nd account from accountlist(beacuse ist account is already copied in
        	 * megerd account file(example-username_inc.xml,username_exp.xml).Then we take ist node from 2nd 
		 * account and search it in merge account,
	         * Case 1st if its found so we just match accname if its match so accname is same otherwise 
        	 * accname will be combined and merge file will be update with all new attribute.
	         * Case 2nd if its not found append in merge file.
                 */
		
		/*** merge of income ***/
		
                for($j=1;$j<sizeof($accarray);$j++)
                {
                        $accname1 = $accarray[$j];
                        $file_name = $username."_".$accname1."_inc.xml";
                        $tt = $acctpath."/".$file_name;
                        $doc = new DomDocument('1.0', 'UTF-8');
                        $doc->formatOutput = true;
                        $doc->load($tt,LIBXML_PARSEHUGE);
                        $xpath = new DomXPath($doc);
			
                        for($i=0; $i<$max1; $i++)
                        {
				//echo "<br>";
				$total1 = 0;
                                $Income = $xpath->query("/IncExp/Code");
                                $accid1 = $Income->item($i)->getAttribute('id');
                                $acccode1 = $Income->item($i)->getAttribute('code');
                                $acc_name1 = $Income->item($i)->getAttribute('name');
                                $schedule1 = $Income->item($i)->getAttribute('schedule');
                                $total1 = $Income->item($i)->getAttribute('total');
				
				$flag1 = false;

                                $doc1 = new DOMDocument('1.0', 'UTF-8');
                                $doc1->formatOutput = true;
                                $doc1->load($ttt2,LIBXML_PARSEHUGE);

                                foreach ($doc1->getElementsByTagName('Code') as $node)
                                {
                                        //$node->getAttribute('code');
                                        if(!($node->getAttribute('code')== $acccode1))
                                        {
                                                $flag1 = true;
                                        }
                                        else
                                        {
                                                $flag1 = false;
                                                break;
                                        }
                                }
				//ini_set('max_execution_time', 120);

                                if($flag1 == false)
                                {
					//echo "Accname--->".$accname1;
                                        for($k = 0; $k < $max1; $k++)
                                        {
						$total2 = 0;
                                                $total3 = 0;
                                                $xpath1 = new DomXPath($doc1);
                                                $Income1 = $xpath1->query("/IncExp/Code");

						$accid2 = $Income1->item($k)->getAttribute('id');
		                                $acccode2 = $Income1->item($k)->getAttribute('code');
						if($acccode2 == $acccode1)
						{
							$acc_name2 = $Income1->item($k)->getAttribute('name');
							if($acc_name1 == $acc_name2)
								$acc_name2 = $acc_name1;
							else
								$acc_name2 = $acc_name1 ."+". $acc_name2;
						
                                		$schedule2 = $Income1->item($k)->getAttribute('schedule');
		                                $total2 = $Income1->item($k)->getAttribute('total');
											
						$total3 = $total1 + $total2;
						//echo "total3-->". $total3;
						//echo "<br>";
						//echo "Total--->".$total1;
						//echo "<br>";
						//echo "Name2--->".$acc_name2;	
						$Income1->item($k)->setAttribute('name', $acc_name2);
						$Income1->item($k)->setAttribute('total', $total3);
			
						

						//update merge income file
						
						$temp1 = $doc1->saveXML();
                                                $handle = fopen($ttt2, "w");
                                                fwrite($handle, $temp1);
                                                fclose($handle);

						break;
						}	
					}
					//echo "Total--->".$total1;
					//echo "<br>";
				}
			
			}		
		}//end of income merge

                /*** merge of expense ***/

                for($j=1;$j<sizeof($accarray);$j++)
                {
                        $accname1 = $accarray[$j];
                        $file_name = $username."_".$accname1."_exp.xml";
                        $tt = $acctpath."/".$file_name;
                        $doc = new DomDocument('1.0', 'UTF-8');
                        $doc->formatOutput = true;
                        $doc->load($tt,LIBXML_PARSEHUGE);
                        $xpath = new DomXPath($doc);

                        for($i=0; $i<$max2; $i++)
                        {
                                //echo "<br>";
                                $total1 = 0;
                                $Income = $xpath->query("/IncExp/Code");
                                $accid1 = $Income->item($i)->getAttribute('id');
                                $acccode1 = $Income->item($i)->getAttribute('code');
                                $acc_name1 = $Income->item($i)->getAttribute('name');
                                $schedule1 = $Income->item($i)->getAttribute('schedule');
                                $total1 = $Income->item($i)->getAttribute('total');

                                $flag1 = false;

                                $doc1 = new DOMDocument('1.0', 'UTF-8');
                                $doc1->formatOutput = true;
                                $doc1->load($ttt4,LIBXML_PARSEHUGE);

                                foreach ($doc1->getElementsByTagName('Code') as $node)
                                {
                                        //$node->getAttribute('code');
                                        if(!($node->getAttribute('code')== $acccode1))
                                        {
                                                $flag1 = true;
                                        }
                                        else
                                        {
                                                $flag1 = false;
                                                break;
                                        }
                                }
                                //ini_set('max_execution_time', 120);

                                if($flag1 == false)
                                {
                                        //echo "Accname--->".$accname1;
                                        for($k = 0; $k < $max2; $k++)
                                        {
                                                $total2 = 0;
                                                $total3 = 0;
                                                $xpath1 = new DomXPath($doc1);
                                                $Income1 = $xpath1->query("/IncExp/Code");

                                                $accid2 = $Income1->item($k)->getAttribute('id');
                                                $acccode2 = $Income1->item($k)->getAttribute('code');
                                                if($acccode2 == $acccode1)
                                                {
                                                        $acc_name2 = $Income1->item($k)->getAttribute('name');
                                                        if($acc_name1 == $acc_name2)
                                                                $acc_name2 = $acc_name1;
                                                        else
                                                                $acc_name2 = $acc_name1 ."+". $acc_name2;

                                                $schedule2 = $Income1->item($k)->getAttribute('schedule');
                                                $total2 = $Income1->item($k)->getAttribute('total');

                                                $total3 = $total1 + $total2;
                                                //echo "total3-->". $total3;
                                                //echo "<br>";
                                                //echo "Total--->".$total1;
                                                //echo "<br>";
                                                //echo "Name2--->".$acc_name2;
                                                $Income1->item($k)->setAttribute('name', $acc_name2);
                                                $Income1->item($k)->setAttribute('total', $total3);



                                                //update merge income file

                                                $temp1 = $doc1->saveXML();
                                                $handle = fopen($ttt4, "w");
                                                fwrite($handle, $temp1);
                                                fclose($handle);

                                                break;
                                                }     
                                        }
                                        //echo "Total--->".$total1;
                                        //echo "<br>";
                                }
                                 

                        }
                }//end of expense merge

        	$data['length1'] = $length1;
        	$data['length2'] = $length2;
		    $data['username'] = $username;
    		$data['mergeincfile'] = $mergeincfile;
	    	$data['mergeexpfile'] =	$mergeexpfile;
		    $data['username'] = $username;
    		$data['inctemp'] = $inctemp;
    		$data['exptemp'] = $exptemp;

	$this->template->load('template', 'aggregation/aggregateincexp1',$data);
	}		

    function aggregatepayrec()
    {
        $this->load->library('session');
        $username = $this->session->userdata('user_name');
        $data['username'] = $username;
        $this->template->set('page_title', 'Aggregation of Payment and Receipt');
        $this->template->load('template', 'aggregation/aggregatepayrec',$data);
        return;
    }
                
    function aggregatepayrec1()
    {
        $this->load->library('session');
        $this->load->model('Aggreegate_model');
        $username = $this->session->userdata('user_name');
        $this->template->set('page_title', 'Aggregation of Payment and Receipt');
        if ($_POST)
        {
            $data['accounts'] = $this->input->post('accounts', TRUE);
            $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
            $mergeincfile = "";
            $mergeexpfile = "";
            $this->load->library('AggregatePayRec');
            $income_total ;
            $expense_total;
            $te = 0;
            $accarray = array();
            $inctemp = 0;
            $exptemp = 0;
            $accarray1 = array();
            $tem = 0;
            foreach ($data['accounts'] as $key => $value)
            {
                $accname1 = $value;
                $accarray1[$tem] = $value;
                $tem++;
            }
                
            /* code for deletion of xml file  */
                
            for($i = 0 ; $i<sizeof($accarray1); $i++)
            {
                $accname1 = $accarray1[$i];
                $file_name1 = $username."_".$accname1."_pay.xml";
                $file_name2 = $username."_".$accname1."_rec.xml";
                $ttdel1 = $acctpath."/".$file_name1;
                $ttdel2 = $acctpath."/".$file_name2;

                    //deletion of xml file .
                if (file_exists($ttdel1))
                    unlink($ttdel1);
                if (file_exists($ttdel2))
                    unlink($ttdel2);
            }
            $file_name1 = $username."_pay.xml";
            $ttdel1 = $acctpath."/".$file_name1;
            $file_name2 = $username."_rec.xml";
            $ttdel2 = $acctpath."/".$file_name2;

            if (file_exists($ttdel1))
                unlink($ttdel1);
            if (file_exists($ttdel2))
                unlink($ttdel2);

            $openingbalance = '';
            $netpaymenttotal = 0.00;
            $netreceipttotal = 0.00;
                $tot_op_bal = 0.00;
                $total_cl_bal = 0.00;
                $aggtotalop = 0.00;
                $aggtotalcl = 0.00;

            foreach ($data['accounts'] as $key => $value)
            {
                $accname = $value;
                $accarray[$te] = $value;
                $te++;

                //get values of payment head.

                $payment = new AggregatePayRec();
                $payment_total = $payment->aggpayrec('Payment',$accname,$username);
                $netpaymenttotal = $netpaymenttotal + $payment->total;
            

                //get values of receipt head.                
                $receipt = new AggregatePayRec();
                $receipt_total = $receipt->aggpayrec('Receipt',$accname,$username);
                $netreceipttotal = $netreceipttotal + $receipt->total;

                
           
                  
                //get opening balance of accounts.
                $CI =& get_instance();                
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

                $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
                try
                {
                    $ledgeropid = "select * from ledgers where type='1'";
                    $stmt = $dbcon->query($ledgeropid);
                    if($stmt != false)  
                    {
                        foreach ($stmt as $row)
                        {
                            $ledger_opid = $row['id'];
                            list ($opbalance, $optype) = $this->Aggreegate_model->get_op_closing_balance_agg($ledger_opid,$accname);
                            $ledbalance = $this->Aggreegate_model->get_closing_balance_agg($ledger_opid,$accname);
                            if($optype == 'C')
                            {
                                $opbalance=-$opbalance;
                            }
                            $tot_op_bal=$tot_op_bal+$opbalance;
                            $total_cl_bal=$ledbalance+$total_cl_bal;

                        }

                    }
                }
                catch(PDOException $e)
                {
                    echo $e->getMessage();
                }
            }
            /*** merging of payment and reciept accounts file ***/

                // get max length value around all xml file
            $length1 = array();
            $length2 = array();
            for($i = 0 ; $i<sizeof($accarray); $i++)
            {
                $accname=$accarray[$i];
                $file_name1=$username."_".$accname."_pay.xml";
                $tt1=$acctpath."/".$file_name1;
                $file_name2=$username."_".$accname."_rec.xml";
                $tt2=$acctpath."/".$file_name2;
                $doc1 = new DomDocument;
                if (file_exists($tt1))
                {
                    $doc1->load($tt1);
                    // count all <Code/> elements
                    $len1 = $doc1->getElementsByTagName('Code')->length;
                }
                
                $doc1 = new DomDocument;
                if (file_exists($tt2))
                {
                    $doc1->load($tt2);
                    // count all <Code/> elements
                    $len2 = $doc1->getElementsByTagName('Code')->length;
                }

                $length1[$i] = $len1;
                $length2[$i] = $len2;
            }
            //copy the fisrt account xml file into merge accounts file
            $max1 = 0;
            if(sizeof($length1)!==0)
            {
                $max1 = max($length1);
            }
            $max2 = 0;
            if(sizeof($length2)!==0)
            {
                $max2 = max($length2);
            }
            //storing all data of ist account in merge file.

            $accist = "";
            $accist =$username."_".$accarray[0];
            $accist1 = $accist."_pay.xml";
            $accist2 = $accist."_rec.xml";

            $mergerecfile = $username."_rec.xml";
            $mergepayfile = $username."_pay.xml";

            //copying ist account xml file data from accounts array into payment merge xml file

            $ttt1 = $acctpath."/".$accist1;
            $ttt2=$acctpath."/".$mergepayfile;
            $file1 = fopen($ttt1, 'rb');
            $newfile1 = fopen($ttt2, 'wb');
            while(($line1 = fgets($file1)) !== false)
            {
                fputs($newfile1, $line1);
            }
            fclose($newfile1);
            fclose($file1);

            //copying ist account xml file data from accounts array into receipt merge xml file

            $ttt3 = $acctpath."/".$accist2;
            $ttt4 = $acctpath."/".$mergerecfile;
            $file2 = fopen($ttt3, 'rb');
            $newfile2 = fopen($ttt4, 'wb');
            while(($line2 = fgets($file2)) !== false)
            {
                fputs($newfile2, $line2);
            }
            fclose($newfile2);
            fclose($file2);
        }//end of post

        /****          Merging of accounts        ****/

        /* Fisrt we will select 2nd account from accountlist(beacuse ist account is already copied in
         * megerd account file(example-username_pay.xml,username_rec.xml).Then we take ist node from 2nd 
         * account and search it in merge account,
         * Case 1st if its found so we just match accname if its match so accname is same otherwise 
         * accname will be combined and merge file will be update with all new attribute.
         * Case 2nd if its not found append in merge file.
         */

        /*** merge of payment ***/

        for($j=1;$j<sizeof($accarray);$j++)
        {
            $accname1 = $accarray[$j];
            $file_name = $username."_".$accname1."_pay.xml";
            $tt = $acctpath."/".$file_name;
            $doc = new DomDocument('1.0', 'UTF-8');
            $doc->formatOutput = true;
            $doc->load($tt,LIBXML_PARSEHUGE);
            $xpath = new DomXPath($doc);

            for($i=0; $i<$max1; $i++)
            {
                $total1 = 0;
                $Income = $xpath->query("/Payment/Code");
                $accid1 = $Income->item($i)->getAttribute('id');
                $acccode1 = $Income->item($i)->getAttribute('code');
                $acc_name1 = $Income->item($i)->getAttribute('name');
                $total1 = $Income->item($i)->getAttribute('total');
                
                $flag1 = false;
                $doc1 = new DOMDocument('1.0', 'UTF-8');
                $doc1->formatOutput = true;
                $doc1->load($ttt2,LIBXML_PARSEHUGE);
                foreach ($doc1->getElementsByTagName('Code') as $node)
                {
                    //$node->getAttribute('code');
                    if(!($node->getAttribute('code')== $acccode1))
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
                    //echo "Accname--->".$accname1;
                    for($k = 0; $k < $max1; $k++)
                    {
                        $total2 = 0;
                        $total3 = 0;
                        $xpath1 = new DomXPath($doc1);
                        $Income1 = $xpath1->query("/Payment/Code");

                        $accid2 = $Income1->item($k)->getAttribute('id');
                        $acccode2 = $Income1->item($k)->getAttribute('code');

                        if($acccode2 == $acccode1)
                        {
                            $acc_name2 = $Income1->item($k)->getAttribute('name');
                            if($acc_name1 == $acc_name2)
                                $acc_name2 = $acc_name1;
                            else
                                $acc_name2 = $acc_name1 ."+". $acc_name2;

                            $total2 = $Income1->item($k)->getAttribute('total');

                            $total3 = $total1 + $total2;
                            $Income1->item($k)->setAttribute('name', $acc_name2);
                            $Income1->item($k)->setAttribute('total', $total3);

                            //update merge pay file

                            $temp1 = $doc1->saveXML();
                            $handle = fopen($ttt2, "w");
                            fwrite($handle, $temp1);
                            fclose($handle);

                            break;
                        }                
                    }
                }
            }
        }
        //end of payment merge

        /*** merge of receipt ***/

        for($j=1;$j<sizeof($accarray);$j++)
        {
            $accname1 = $accarray[$j];
            $file_name = $username."_".$accname1."_rec.xml";
            $tt = $acctpath."/".$file_name;
            $doc = new DomDocument('1.0', 'UTF-8');
            $doc->formatOutput = true;
            $doc->load($tt,LIBXML_PARSEHUGE);
            $xpath = new DomXPath($doc);

            for($i=0; $i<$max2; $i++)
            {
                $total1 = 0;
                $Income = $xpath->query("/Payment/Code");
                $accid1 = $Income->item($i)->getAttribute('id');
                $acccode1 = $Income->item($i)->getAttribute('code');
                $acc_name1 = $Income->item($i)->getAttribute('name');
                $total1 = $Income->item($i)->getAttribute('total');
                
                $flag1 = false;
                $doc1 = new DOMDocument('1.0', 'UTF-8');
                $doc1->formatOutput = true;
                $doc1->load($ttt4,LIBXML_PARSEHUGE);
                foreach ($doc1->getElementsByTagName('Code') as $node)
                {
                    if(!($node->getAttribute('code')== $acccode1))
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
                    for($k = 0; $k < $max1; $k++)
                    {
                        $total2 = 0;
                        $total3 = 0;
                        $xpath1 = new DomXPath($doc1);
                        $Income1 = $xpath1->query("/Payment/Code");

                        $accid2 = $Income1->item($k)->getAttribute('id');
                        $acccode2 = $Income1->item($k)->getAttribute('code');

                        if($acccode2 == $acccode1)
                        {
                            $acc_name2 = $Income1->item($k)->getAttribute('name');
                            if($acc_name1 == $acc_name2)
                                $acc_name2 = $acc_name1;
                            else
                                $acc_name2 = $acc_name1 ."+". $acc_name2;
                            $total2 = $Income1->item($k)->getAttribute('total');
                            
                            $total3 = $total1 + $total2;
                            $Income1->item($k)->setAttribute('name', $acc_name2);
                            $Income1->item($k)->setAttribute('total', $total3);

                            //update merge pay file

                            $temp1 = $doc1->saveXML();
                            $handle = fopen($ttt4, "w");
                            fwrite($handle, $temp1);
                            fclose($handle);
                            break;
                        }                
                    }
                }
            }
            $data['length1'] = $length1;
            $data['length2'] = $length2;
            $data['username'] = $username;
            $data['mergepayfile'] = $mergepayfile;
            $data['mergerecfile'] = $mergerecfile;
            $data['netreceipttotal'] = $netreceipttotal;
            $data['netpaymenttotal'] = $netpaymenttotal;
            $data['tot_op_bal'] = $tot_op_bal;
            $data['total_cl_bal'] = $total_cl_bal;

            $data['left_width'] = "50%";
            $data['right_width'] = "50%";

        }
        //end of payment merge
		$this->template->load('template', 'aggregation/aggregatepayrec1',$data);
	}
    /**** Aggregation of Traial Balance of Accounts ****/

    function aggregatetrialbalance()
    {
        $this->load->library('session');
        $username = $this->session->userdata('user_name');
        $data['username'] = $username;
        $this->template->set('page_title', 'Aggregation of Trial Balances ');
        //$this->template->load('user_template', 'aggregation/aggregateincexp',$data);
        $this->template->load('template', 'aggregation/aggregatetrialbalance',$data);
        return;
    }
    
    function aggregatetrialbalance1()
    {
        $this->load->library('session');
        $username = $this->session->userdata('user_name');
        $this->template->set('page_title', 'Aggregation of Trial Balances ');

        $this->db->from('settings');
        $detail = $this->db->get();
        foreach ($detail->result() as $row)
        {
            $date1 = $row->fy_start;
            $date2 = $row->fy_end;
        }
        $date=explode("-",$date1);
        $date2 = explode("-", $row->fy_end);
        $default_start = '01/04/'.$date[0];
        $default_end = '31/03/'.$date2[0];


        $date=explode("/",$default_start);
        $date1=$date[2]."-".$date[1]."-".$date[0];
        $date=explode("/",$default_end);
        $date2=$date[2]."-".$date[1]."-".$date[0];
        $accarray = array();
        $max1 = 0;  
        $mergetrbfile ;
        //$data = array();
        if ($_POST)
        {
            $data['accounts'] = $this->input->post('accounts', TRUE);
            $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
            $this->load->model('Ledger_model');
            //$mergefile = "";

            $accarray = array();
            $accarray1 = array();
            $tem = 0;
            $te = 0;
            $temp_dr_total = 0;
            $temp_cr_total = 0;
            $total_cl_bal = 0;
            $total_op_bal = 0;
            foreach ($data['accounts'] as $key => $value)
            {
                $accname = $value;
                $file_name1 = $username."_".$accname."_trbal.xml";
                $ttdel1 = $acctpath."/".$file_name1;

                    //deletion of xml file .
                if (file_exists($ttdel1))
                    unlink($ttdel1);


                $accarray[$tem] = $value;
                $tem = $tem + 1;
                $all_ledgers = $this->Ledger_model->get_all_ledgers1_agg($date1, $date2,$accname);
                $odd_even = "odd";
                foreach ($all_ledgers as $ledger_id => $ledger_name)
                {
                    if ($ledger_id == 0) continue;
                    
                    list ($opbal_amount, $opbal_type) = $this->Ledger_model->get_op_balance_agg($ledger_id,$accname);
                    if($opbal_type == 'D')
                        $new_opbal_amount = $opbal_amount;
                    else
                        $new_opbal_amount = -$opbal_amount;
                    $total_op_bal = float_ops($total_op_bal, $new_opbal_amount, '+');

                    $clbal_amount = $this->Ledger_model->get_ledger_balance_agg($ledger_id,$accname);
                    $total_cl_bal = float_ops($total_cl_bal, $clbal_amount, '+');

                    $dr_total = $this->Ledger_model->get_dr_total1_agg($ledger_id,$accname);
                    if ($dr_total)
                    {
                        money_format('%!i', $dr_total);
                        $temp_dr_total = float_ops($temp_dr_total, $dr_total, '+');
                    } 
                    else 
                    {
                        $dr_total = "0";
                    }
                    $cr_total = $this->Ledger_model->get_cr_total1_agg($ledger_id,$accname);
                    if ($cr_total)
                    {
                         money_format('%!i', $cr_total);
                        $temp_cr_total = float_ops($temp_cr_total, $cr_total, '+');
                    } 
                    else 
                    {
                        $cr_total = "0";
                    }
                    //$odd_even = ($odd_even == "odd") ? "even" : "odd";
    
                    //creation of trial balances xml file of accounts.
                
                    $this->load->library('session');
                    $username = $this->session->userdata('user_name');
                    
                    $trbalancefile = $username."_".$accname."_trbal.xml";
                    $doc = new DOMDocument();
                    $doc->formatOutput = true;
                    $tt = $acctpath."/".$trbalancefile;
                    if(file_exists($tt))
                    {
                        $doc->preserveWhiteSpace = false;
                        $doc->load($tt);
                        $Tbalance = $doc->firstChild;
                        $Code = $doc->createElement('Code');
                        $Code->setAttribute('id', $ledger_id);
                        $Code->setAttribute('name', $ledger_name);
                        $Code->setAttribute('opbalance', convert_opening($opbal_amount, $opbal_type));
                        $Code->setAttribute('clbalance', convert_amount_dc($clbal_amount));
                        $Code->setAttribute('drtotal', $dr_total);
                        $Code->setAttribute('crtotal', $cr_total);

                        $Tbalance->appendChild($Code);

                        $ttt=$doc->saveXML();
                        $handle = fopen($tt, "w");
                        fwrite($handle, $ttt);
                        fclose($handle);
                    }
                    else
                    {
                        $r = $doc->createElement( 'Tbalance' );
                        $doc->appendChild( $r );
                        $Code = $doc->createElement('Code');


                        $Code->setAttribute('id', $ledger_id);
                        $Code->setAttribute('name', $ledger_name);
                        $Code->setAttribute('opbalance', convert_opening($opbal_amount, $opbal_type));
                        $Code->setAttribute('clbalance', convert_amount_dc($clbal_amount));
                        $Code->setAttribute('drtotal', $dr_total);
                        $Code->setAttribute('crtotal', $cr_total);
                        
                        $r->appendChild($Code);
                        $doc->save($tt);
                        $doc->saveXML();
                    }
                }
            $odd_even = ($odd_even == "odd") ? "even" : "odd";
            }
            $mergedel = $username."_trbal.xml";
            $ttdel2 = $acctpath."/".$mergedel;

                    //deletion of xml file .
            if (file_exists($ttdel2))
                unlink($ttdel2);

            /**** Merger of accounts xml file ****/

                // get max length of accounts xml file  around all xml file

            $length1 = array();
            $len1 = 0;
            for($i = 0 ; $i<sizeof($accarray); $i++)
            {
                $accname=$accarray[$i];

                $file_name1=$username."_".$accname."_trbal.xml";
                $tt1=$acctpath."/".$file_name1;

                $doc1 = new DomDocument;
                if (file_exists($tt1))
                {
                    $doc1->load($tt1);

                    // count all <Code/> elements
                    $len1 = $doc1->getElementsByTagName('Code')->length;
                    //print_r($doc1->getElementsByTagName('Code'));

                }
                $length1[$i] = $len1;
            }   
            
            
            if(sizeof($length1)!==0)
            {
                $max1 = max($length1);
            }

            //storing all data of ist account in merge file.

            $accist = "";
            $accist =$username."_".$accarray[0];
            $accist1 = $accist."_trbal.xml";

            $mergetrbfile = $username."_trbal.xml";

            //copying ist account xml file data into a merge xml file

            $ttt1 = $acctpath."/".$accist1;
            $ttt2=$acctpath."/".$mergetrbfile;
            $file1 = fopen($ttt1, 'rb');
            $newfile1 = fopen($ttt2, 'wb');
            while(($line1 = fgets($file1)) !== false)
            {
                fputs($newfile1, $line1);
            }
            fclose($newfile1);
            fclose($file1);
        }
        $data['mergetrbfile'] = $mergetrbfile;
        $data['username'] = $username;
        $data['max1'] = $max1;
        $data['width'] = "80%";
        $data['netopbal'] = convert_amount_dc($total_op_bal);
        $data['netclbal'] = convert_amount_dc($total_cl_bal);
        $data['netdrtotal'] = money_format('%!i', convert_cur($temp_dr_total));
        $data['netcrtotal'] = money_format('%!i', convert_cur($temp_cr_total)); 
        $this->template->load('template', 'aggregation/aggregatetrialbalance1',$data);
    }

    /**** Aggregation of Balancesheet of Accounts ****/
    function aggregatebalancesheet()
    {
        $this->load->library('session');
        $username = $this->session->userdata('user_name');
        $data['username'] = $username;
        $this->template->set('page_title', 'Aggregation of Balancesheet ');
        //$this->template->load('user_template', 'aggregation/aggregateincexp',$data);
        $this->template->load('template', 'aggregation/aggregatebalancesheet',$data);
        return;
    }
    
    function aggregatebalancesheet1()
    {
        $this->load->library('session');
        $username = $this->session->userdata('user_name');
        $this->template->set('page_title', 'Aggregation of Balancesheet ');
        if ($_POST)
        {
            $mergeliability_total = 0;
            $mergeassets_total = 0;
            $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');

            $data['accounts'] = $this->input->post('accounts', TRUE);
            $accarray = array();
            $tem = 0;
            
            foreach ($data['accounts'] as $key => $value)
            {
                $accname = $value;
                $accarray[$tem] = $value;
                $tem++;
            }
            $acctname = $accarray[0];
            $db1=$this->load->database('login', TRUE);
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
                    unlink($tt1);
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
                $liability->balancesheet(2,$accname);
                //$count = $liability->new_balance_sheet(0,$accname);
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
                $asset->balancesheet(1,$accname);
                $asset_total = $asset->curr_total;
                //$count =  $asset->new_balance_sheet($count,$accname);

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

                    $liabiltynode1 = $xpath->query("/Liabilities/Liability_Name/Group_Name");
                    $liabiltynode2 = $xpath->query("/Liabilities/Liability_Name/Code_No");
                    $liabiltynode3 = $xpath->query("/Liabilities/Liability_Name/Amount");
                    $liabiltynode4 = $xpath->query("/Liabilities/Liability_Name/Code_Nu");

                    $liabiltylist1 = @$liabiltynode1->item($i)->nodeValue;
                    $liabiltylist2 = @$liabiltynode2->item($i)->nodeValue;
                    $liabiltylist3 = @$liabiltynode3->item($i)->nodeValue;
                    $liabiltylist4 = @$liabiltynode4->item($i)->nodeValue;
                    $sumamt = $sumamt+$liabiltylist3;
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

                    $r->appendChild( $b );
                    $doc->save($tt);
                    $doc->saveXML();
                }
            }//end of Libality  

            $mergeassetsfile = $mergefile."_Assets.xml";

            $accist = $accarray[0];
            $accist1 = $accist."_Assets.xml";

            //copying ist account xml file data from accounts array into income merge xml file

            $ttt1 = $acctpath."/".$accist1;
            $ttt2=$acctpath."/".$mergeassetsfile;
            $file1 = fopen($ttt1, 'rb');
            $newfile1 = fopen($ttt2, 'wb');
            while(($line1 = fgets($file1)) !== false)
            {
                fputs($newfile1, $line1);
            }
            fclose($newfile1);
            fclose($file1);

            /****          Merging of assets        ****/

            for($j=1;$j<sizeof($accarray);$j++)
            {
                $accname1 = $accarray[$j];
                $file_name = $accname1."_Assets.xml";
                $tt = $acctpath."/".$file_name;
                $doc = new DomDocument('1.0', 'UTF-8');
                $doc->formatOutput = true;
                $doc->load($tt,LIBXML_PARSEHUGE);
                $xpath = new DomXPath($doc);
                $sumasst;
                for($i=0; $i<$max2; $i++)
                {
                    $total1 = 0;
                    $Income = $xpath->query("/Liabilities/Assets_Name");
                    $acccode1 = $Income->item($i)->getAttribute('code');
                    $acc_name1 = $Income->item($i)->getAttribute('name');
                    $schedule1 = $Income->item($i)->getAttribute('schedule');
                    $total1 = $Income->item($i)->getAttribute('amount');

                    $flag1 = false;

                    $doc1 = new DOMDocument('1.0', 'UTF-8');
                    $doc1->formatOutput = true;
                    $doc1->load($ttt2,LIBXML_PARSEHUGE);

                    foreach ($doc1->getElementsByTagName('Assets_Name') as $node)
                    {
                        //$node->getAttribute('code');
                        if(!($node->getAttribute('code')== $acccode1))
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
                        //echo "Accname--->".$accname1;
                        for($k = 0; $k < $max2; $k++)
                        {
                            $total2 = 0;
                            $total3 = 0;
                            $xpath1 = new DomXPath($doc1);
                            $Income1 = $xpath1->query("/Liabilities/Assets_Name");

                            $acccode2 = $Income1->item($k)->getAttribute('code');
                            if($acccode2 == $acccode1)
                            {
                                $acc_name2 = $Income1->item($k)->getAttribute('name');
                                $schedule2 = $Income1->item($k)->getAttribute('schedule');
                                $total2 = $Income1->item($k)->getAttribute('amount');

                                $total3 = $total1 + $total2;

                                $Income1->item($k)->setAttribute('name', $acc_name2);
                                $Income1->item($k)->setAttribute('amount', $total3);

                                //update merge income file

                                $temp1 = $doc1->saveXML();
                                $handle = fopen($ttt2, "w");
                                fwrite($handle, $temp1);
                                fclose($handle);

                            }
                        }
                    }//flag1 end
                }
            }//end of Assets merge
        }//end of post
        $data['username'] = $username;
        $data['mergefile'] = $mergefile;
        $data['mergeassets_total'] = $mergeassets_total;               
        $data['mergeliability_total'] = $mergeliability_total;
        $data['fyear'] = $fyear;
        $this->template->load('template', 'aggregation/aggregatebalancesheet1',$data);
    }//end of method

}

/* End of file aggregation.php */
/* Location: ./system/application/controllers/aggregation.php */
