<?php

class Investment_model extends Model {

var $prevYearDB = "";
var $db_username = "";
var $db_password = "";
var $host_name = "";
var $port = "";


 function Investment_model()
        {
                parent::Model();
        }

	function getPreviousYearDetails()
        {
                $database = "";

                $this->db->from('settings')->where('id', 1);
                $settings_q = $this->db->get();
                $settings= $settings_q->row();
                $ins_name = $settings->ins_name;
                $uni_name = $settings->uni_name;
                $date1 = explode("-", $settings->fy_start);
                $old_year_start = $date1[0]-1;
                $date1 = explode("-", $settings->fy_end);
                $old_year_end = $date1[0]-1;
                $previous_year = $old_year_start . "-" . $old_year_end;

                $this->db->close();

                //fetch previous year details
                $db = $this->load->database('login', TRUE);
                $db->select('databasename, uname, dbpass, hostname, port');

                $db->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $previous_year);
                $login_q = $db->get();
                if($login_q->num_rows()>0){
                        $login = $login_q->row();
                        $this->prevYearDB = $login->databasename;
                        $this->db_username = $login->uname;
                        $this->db_password = $login->dbpass;
                        $this->host_name = $login->hostname;
                        $this->port = $login->port;
                }
                $this->db->close();
        }



 	function investment1($invest_id,$type)
	{//main
		$credit_total = 0;
		$credit_total1 = 0;
        	$debit_total = 0;
		$debit_total1 = 0;
        	$old_credit_total = 0;
		$old_credit_total1 = 0;
        	$old_debit_total = 0;
		$old_debit_total1 = 0;

                $this->db->select('id,name')->from('ledgers')->where('group_id',$invest_id);
                $ledgers_result=$this->db->get();
                $ledg = $ledgers_result->result();
                foreach($ledg as $row2)
                {//1
              		$debit_amount=0;
                        $credit_amount=0;
                        $old_debit_amount=0;
                        $old_credit_amount=0;
				
			if($type == 'Earmarked Funds')
			{//if earmarked

                        	$fund_id = "";
                                $this->db->select('id, amount,dc')->from('entry_items')->where('ledger_id', $row2->id);
                                $entry_items_q = $this->db->get();
                                $entry = $entry_items_q->result();
                                foreach($entry as $row3)
                                {//2
					$entry_id1 = $row3->id;
                                        if($entry_items_q->num_rows() > 0)
					{//3
                                                $this->db->select('fund_id')->from('fund_management')->where('entry_items_id',$entry_id1);
                                               	$fund_result = $this->db->get();
                                                $fund_q = $fund_result->result();
                                                foreach($fund_q as $row77)
                                                {//4
                                                	$fund_id = $row77->fund_id;
                                			$code = "";
                                			$this->db->select('code')->from('ledgers')->where('id', $fund_id);
                                			$r_query = $this->db->get();
							$code = "";
                                       			foreach($r_query->result() as $coderesult)
                                        		{//5
                                        			$code = $coderesult->code;
                                        			$code1 = substr($code , 0,6);
                                                		if($code1 == "100103")
                                                		{//6
                                                			$entry1= $r_query->row();
                                                        		if($row3 !='')
                                                        		{//7
                                                        			if($row3->dc == 'D')
                                                                		{
                                                                			$debit_amount = $debit_amount + $row3->amount;
                                                                			$debit_total = $debit_total + $row3->amount;
                                                                        	}else{
                                                                        		$credit_amount = $credit_amount + $row3->amount;
                                                                     			$credit_total = $credit_total + $row3->amount;
                                                                             	     }
								
                                                        		}//7
                                                 		}//6 
							}//5
						}//4
					}//3
				}//2

				$this->getPreviousYearDetails();
                                if($this->prevYearDB != "" )
				{//1
                                   /* database connectivity for getting previous year opening balance */
                                      $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                        if($con)
					{//2
                                        	$value = mysql_select_db($this->prevYearDB, $con);
                                                $id = mysql_real_escape_string($row2->id);
                                                $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                $val = mysql_query($cl);
                                                if($val != '')
						{//3
							$entry_id2 = "";
                                                        while($row4 = mysql_fetch_assoc($val))
                                                        {//4
                                                        	$entry_id2 = $row4['id'];
                                                               	$cl1 = "select fund_id from fund_management where entry_items_id='$entry_id2'";
                                                                $val_A = mysql_query($cl1);
                                                                if($val_A != '')
                                                                {//5
                                                                	$fund_id3 = "";
                                                                        while($rowY = mysql_fetch_assoc($val_A))
                                                                        {//6
                                                                        	$fund_id3 = $rowY['fund_id'];
                                                                		$cl2="select code,name from ledgers where id='$fund_id3'";
                                                                		$val1 = mysql_query($cl2);
                                                                		if($val1 != '')
                                                                		{//7
                                                                			$code2 ="";
                                                                        		while($row5 = mysql_fetch_assoc($val1))
                                                                        		{//8
                                                                        			$code2 = $row5['code'];
                                                                       				$code3 = substr($code2 , 0,6);
                                                                        			if($code3 == "100103")
                                                                         			{//9
                                                                                			$value1 = mysql_fetch_array($val1);
                                                                                			if($row4 != null)
                                                                                			{//10
                                                                               					if($row4['dc'] == 'D')
                                                                                				{
                                                                                					$old_debit_amount = $old_debit_amount + $row4['amount'];
                                                                                					$old_debit_total = $old_debit_total + $row4['amount'];
                                                                                				}else{
                                                                                					$old_credit_amount = $old_credit_amount + $row4['amount'];
                                                                                					$old_credit_total = $old_credit_total + $row4['amount'];
                                                                                		     		     }	
													}//10
												}//9	
                                                                           	        }//8
									      	}//7
									}//6
								}//5
							}//4
						}//3 
					}//2
				}//1   

			}//if earmarked
				 elseif($type !='Earmarked Funds')
                                 {
                                 	$fund_id = "";
                                 	$this->db->select('id, amount,dc')->from('entry_items')->where('ledger_id', $row2->id);
                                 	$entry_items_q = $this->db->get();
                                 	$entry = $entry_items_q->result();
                                	foreach($entry as $row3)
                                        { //a
						$entry_id1 = $row3->id;
                                        	if($entry_items_q->num_rows() > 0)
                                        	{//b
                                               		$this->db->select('fund_id')->from('fund_management')->where('entry_items_id',$entry_id1);
                                                	$fund_result = $this->db->get();
                                                	$fund_q = $fund_result->result();
                                                	foreach($fund_q as $row77)
                                                	{//c
                                                        	$fund_id = $row77->fund_id;
                                               			$this->db->select('code')->from('ledgers')->where('id', $fund_id);
                                                		$r_query = $this->db->get();
                                                		$code = "";
                                                		foreach($r_query->result() as $coderesult)
                                                		{//d
                                                			$code = $coderesult->code;
                                                        		$code1 = substr($code , 0,6);
                                                      			if($code1 != "100103")
                                                        		{//e
                                                        			$entry1 = $r_query->row();
                                                                		if($row3 != '')
                                                                		{//f 
                                                                			if($row3->dc == 'D')
                                                                     			{
                                                                        			$debit_amount = $debit_amount + $row3->amount;
                                                                                		$debit_total1 = $debit_total1 + $row3->amount;
                                                                              		}else{
                                                                                		$credit_amount = $credit_amount + $row3->amount;
                                                                              			$credit_total1 = $credit_total1 + $row3->amount;
                                                                                     	     }
                                                                 		}//f
									}//e
								}//d
							}//c
						}//b
					}//a

				        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" )
					{//1
                                             /* database connectivity for getting previous year opening balance */
                                        	$con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                if($con)
						{//2
                                                	$value = mysql_select_db($this->prevYearDB, $con);
                                                        $id = mysql_real_escape_string($row2->id);
                                                        $cl3 = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                        $val_a = mysql_query($cl3);
                                                        if($val_a != '')
							{//3
								$entry_id2 = "";
                                                        	while($rowA = mysql_fetch_assoc($val_a))
                                                                {//4
									$entry_id2 = $rowA['id'];
									$cl4 = "select fund_id from fund_management where entry_items_id='$entry_id2'";                                                                
									$val_AB = mysql_query($cl4);
									if($val_AB != '')
									{//5
										$fund_id4 = "";
										while($rowY = mysql_fetch_assoc($val_AB))
										{//6
											$fund_id4 = $rowY['fund_id'];
                                                                        		$cl5="select code,name from ledgers where id='$fund_id4'";
                                                                       			$val8 = mysql_query($cl5);
                                                                        		if($val8 != '')
                                                                        		{//7
                                                                        			$code6 ="";
                                                                                		while($rowB = mysql_fetch_assoc($val8))
                                                                                		{//8
                                                                                			$code6 = $rowB['code'];
                                                                                			$code7 = substr($code6 , 0,6);
                                                                                        		if($code7 !== "100103")
                                                                                        		{//9
                                                                                        			$value9 = mysql_fetch_array($val8);  
                                                                                                		if($rowA != null)
														{//10 
                                                                                                			if($rowA['dc'] == 'D')
                                                                                                        		{
                                                                                                        			$old_debit_amount = $old_debit_amount + $rowA['amount'];
                                                                                                                		$old_debit_total1 = $old_debit_total1 + $rowA['amount'];
                                                                                                         		}else{
                                                                                                                		$old_credit_amount = $old_credit_amount + $rowA['amount'];
													        		$old_credit_total = $old_credit_total + $rowA['amount'];
                                                                                                              	      	     }
														 }//10
													   }//9

                                                                                                     }//8
                                                                                            }//7
                                                                                   }//6
                                                                         }//5
                                                                 }//4
                                                          }//3
                                                   }//2
                                           }//1   

					}//else not earmarked				
				
			}//1
			
			$total = $debit_total . "#" . $credit_total . "#" .  $debit_total1 . "#" . $credit_total1 . "#" . $old_debit_total . "#" . $old_credit_total . "#" . $old_debit_total1 . "#" . $old_credit_total1;
			return $total;
	      }//main
	}
?>
