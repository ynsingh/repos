<?php

class Aggregation extends Controller {
        function Aggregation()
        {
                parent::Controller();
                return;
        }


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


	                $length1 = array();
			$length2 = array();
        	        for($i = 0 ; $i<sizeof($accarray); $i++)
                	{
                        	$accname=$accarray[$i];

	                        $file_name1=$username."_".$accname."_inc.xml";
        	                $tt1=$acctpath."/".$file_name1;
				//echo "size-->". $file_name1;
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
		//echo $mergeincfile;
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
}

/* End of file aggregation.php */
/* Location: ./system/application/controllers/aggregation.php */
