<?php
class Investment_model extends CI_Model {

function __construct() {
        parent::__construct();
    }

function Investment_model()
{
	parent::Model();
}

/*	function getPreviousYearDetails()
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
        }    */

function investment1($invest_id,$type2)
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
		if($type2 == 'Earmarked Funds')
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
			
			

			/*	$this->getPreviousYearDetails();
                                if($this->prevYearDB != "" )
				{//1
                                   /* database connectivity for getting previous year opening balance */
                        /*              $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
				}//1    */

	}//if earmarked
		elseif($type2 !='Earmarked Funds')
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

				/*        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" )
					{//1
                                             /* database connectivity for getting previous year opening balance */
                                  /*      	$con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                                           }//1    */

					}//else not earmarked				
				
			}//1
			
			$total = $debit_total . "#" . $credit_total . "#" .  $debit_total1 . "#" . $credit_total1;
			return $total;
	      }//main
	
function schedule_five1($group_id)
{
	$total2 = "";
	$credit_amount = 0.00;
	$debit_amount = 0.00;
	$dr_total = 0.00;
	$cr_total = 0.00;
	$op_balance = "";
	$CI = & get_instance();
        $CI->db->select('name,code,id,op_balance')->from('ledgers')->where('group_id',$group_id);
        $ledger_detail = $CI->db->get();
        $ledger_result = $ledger_detail->result();
        foreach($ledger_result as $row1)
        {
        	$ledger_id = $row1->id;
		$ledger_name = $row1->name;
		$op_balance = $row1->op_balance;
		$CI = & get_instance();
        	$CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$ledger_id);
        	$entry = $CI->db->get();
        	$entry_q = $entry->result();
		foreach($entry_q as $row2)
		{
			if($row2->dc == 'D')
			{
			$debit_amount = $debit_amount+$row2->amount;
			$dr_total = $dr_total+$row2->amount;
			}else{
			$credit_amount = $credit_amount+$row2->amount;
			$cr_total = $cr_total+$row2->amount;
			}
		}
	}
	$total1 = $dr_total . "#" . $cr_total . "#" . $op_balance;
        return $total1;
		
}

function current_liabilities1($group_id)
{
	$dr_amount = 0.00;
	$cr_amount = 0.00;
	$dr_total = 0;
	$cr_total = 0;
	$op_balance = "";
	$CI = get_instance();
	$CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id',$group_id);
	$children_group = $CI->db->get();
	$children_group1 = $children_group->result();
	foreach($children_group1 as $row1)
	{
		$children_ledgerid = $row1->id;
		$children_ledgername = $row1->name;
		$op_balance = $row1->op_balance;
		$CI = & get_instance();
		$CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$row1->id);
		$entry = $CI->db->get();
		$entry_q = $entry->result();
		foreach($entry_q as $row2)
		{
		if($row2->dc == 'D')
		{
		$dr_amount = $dr_amount+$row2->amount;
		$dr_total = $dr_total + $row2->amount;
		}
		else{ 
		$cr_amount = $cr_amount+$row2->amount;
		$cr_total = $cr_total + $row2->amount;
		    }
		}
	}//foreach
	$total = $dr_total . "#" . $cr_total . "#" . $op_balance;
	return $total;
}
function current_liabilitieledg($children_groupid)
{
        $dr_amount = 0.00;
        $cr_amount = 0.00;
        $dr_total = 0;
        $cr_total = 0;
	$op_balance = "";
        $CI = get_instance();
        $CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id',$children_groupid);
        $children_group = $CI->db->get();
        $children_group1 = $children_group->result();
        foreach($children_group1 as $row1)
        {
                $children_ledgerid = $row1->id;
                $children_ledgername = $row1->name;
		$op_balance = $row1->op_balance;
                $CI = & get_instance();
                $CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$row1->id);
                $entry = $CI->db->get();
                $entry_q = $entry->result();
                foreach($entry_q as $row2)
                {
                if($row2->dc == 'D')
                {
                $dr_amount = $dr_amount+$row2->amount;
                $dr_total = $dr_total + $row2->amount;
                }
                else{
                $cr_amount = $cr_amount+$row2->amount;
                $cr_total = $cr_total + $row2->amount;
                    }
                }
        }//foreach
        $total = $dr_total . "#" . $cr_total . "#" . $op_balance;
        return $total;
}

function current_liabgroup($group_id)
{
	$dr_amount = 0.00;
        $cr_amount = 0.00;
        $dr_total = 0;
        $cr_total = 0;
	$op_balance = "";
        $CI = get_instance();
	$CI->db->select('id,name,code')->from('groups')->where('parent_id', $group_id);
	$query_r = $CI->db->get();
	$r1 = $query_r->result();
	foreach($r1 as $row)
	{
	$children_id = $row->id;
	$children_name = $row->name;
        $CI->db->select('id,name,code')->from('ledgers')->where('group_id',$children_id);
        $children_group = $CI->db->get();
        $children_group1 = $children_group->result();
        foreach($children_group1 as $row1)
        {
                $children_ledgerid = $row1->id;
                $children_ledgername = $row1->name;
                $CI = & get_instance();
                $CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$row1->id);
                $entry = $CI->db->get();
                $entry_q = $entry->result();
                foreach($entry_q as $row2)
                {
                if($row2->dc == 'D')
                {
                $dr_amount = $dr_amount+$row2->amount;
                $dr_total = $dr_total + $row2->amount;
                }
                else{
                $cr_amount = $cr_amount+$row2->amount;
                $cr_total = $cr_total + $row2->amount;
                    }
                }
 	  }//foreach
	}
        $total1 = $dr_total . "#" . $cr_total;
        return $total1;
}

function provision1($group_id)
{
	$dr_amount = 0.00;
	$cr_amount = 0.00;
	$dr_total = 0;
	$cr_total = 0;
	$op_balance = "";
	$CI = & get_instance();
	$CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $group_id);
	$query = $CI->db->get();
	$query_1 = $query->result();
	foreach($query_1 as $row)
	{
		$ledger_id = $row->id;
		$ledger_name = $row->name;
		$op_balance = $row->op_balance;
		$CI = & get_instance();
                $CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$row->id);
                $entry = $CI->db->get();
                $entry_q = $entry->result();
                foreach($entry_q as $row1)
                {
                if($row1->dc == 'D')
                {
                $dr_amount = $dr_amount+$row1->amount;
                $dr_total = $dr_total + $row1->amount;
                }
                else{
                $cr_amount = $cr_amount+$row1->amount;
                $cr_total = $cr_total + $row1->amount;
                    }
		}//foreach
	}//foreach
	$total_2 = $dr_total . "#" . $cr_total . "#" . $op_balance;
	return $total_2;
}

function fixed_asset1($children_groupid)
{
	$debit_amount = 0.00;
	$credit_amount = 0.00;
	$dr_total = 0.00;
	$cr_total = 0.00;
	$net_cr = 0.00;
	$net_dr = 0.00;
	$op_total = 0.00;
	$net_opening_bal = 0.00;
	$year_end_value = 0.00;
	$net_total = 0.00;
	$op_balance = "";
	
	$CI = & get_instance();
        $CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $children_groupid);
        $ledgers_q = $CI->db->get();
        $ledger_result = $ledgers_q->result();
        //$is_ledger = false;
        foreach($ledger_result as $row2)
        {
                $debit_amount = 0.00;
                $credit_amount = 0.00;
                $cr_total = 0.00;
                $dr_total = 0.00;
                $net_dr = 0.00;
                $net_cr = 0.00;

                $ledger_id = $row2->id;
                $ledger_name = $row2->name;
                $op_balance = $row2->op_balance;
                $CI->db->select('id, amount, dc')->from('entry_items')->where('ledger_id', $ledger_id);
                $entry_items_q = $CI->db->get();
                $entry_items_result = $entry_items_q->result();
                foreach ($entry_items_result as $row3)
                {
                if($row3->dc == 'C')
                {
                $credit_amount = $credit_amount + $row3->amount;
                $net_cr = $net_cr + $row3->amount;
                }
                else
                {
                $debit_amount = $debit_amount + $row3->amount;
                $net_dr = $net_dr + $row3->amount;
                }
                }//foreach

		$cr_total = $cr_total + $credit_amount;
	        $dr_total = $dr_total + $debit_amount;

      //Adding opening balance for the ledger head.
	$op_balance = $row2->op_balance;
        $op_total = $op_total + $row2->op_balance;
        $net_opening_bal = $net_opening_bal + $row2->op_balance;
        $year_end_value = $year_end_value + ($row2->op_balance + $debit_amount) - $cr_total;
        $net_total = $net_total + ($row2->op_balance + $debit_amount) - $cr_total; 
	}//foreach 

	$total = $credit_amount . "#" . $debit_amount . "#" . $cr_total . "#" . $dr_total . "#" . $op_total . "#" . $net_opening_bal . "#" . $year_end_value . "#" . $net_total . "#" . $op_balance . "#" . $net_dr . "#" . $net_cr;
        return $total;

}

function loan_advances1($group_id)
{
	$dr_amount = 0.00;
        $cr_amount = 0.00;
        $dr_total = 0;
        $cr_total = 0;
        $CI = get_instance();
        $CI->db->select('id,name,code')->from('ledgers')->where('group_id',$group_id);
        $children_group = $CI->db->get();
        $children_group1 = $children_group->result();
        foreach($children_group1 as $row1)
        {
        	$children_ledgerid = $row1->id;
                $children_ledgername = $row1->name;
                $CI = & get_instance();
                $CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$row1->id);
                $entry = $CI->db->get();
                $entry_q = $entry->result();
                foreach($entry_q as $row2)
                {
                if($row2->dc == 'D')
                {
                $dr_amount = $dr_amount+$row2->amount;
                $dr_total = $dr_total + $row2->amount;
                }
                else{
                $cr_amount = $cr_amount+$row2->amount;
                $cr_total = $cr_total + $row2->amount;
                    }
                }
        }//foreach
        $total = $dr_total . "#" . $cr_total;
        return $total;
}

function current_asset($group_id)
{
	$dr_amount = 0.00;
        $cr_amount = 0.00;
        $dr_total = 0;
        $cr_total = 0;
	$op_balance = "";
        $CI = get_instance();
        $CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id',$group_id);
        $children_group = $CI->db->get();
        $children_group1 = $children_group->result();
        foreach($children_group1 as $row1)
        {
                $children_ledgerid = $row1->id;
                $children_ledgername = $row1->name;
		$op_balance = $row1->op_balance;
                $CI = & get_instance();
                $CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$row1->id);
                $entry = $CI->db->get();
                $entry_q = $entry->result();
                foreach($entry_q as $row2)
                {
                if($row2->dc == 'D')
                {
                $dr_amount = $dr_amount+$row2->amount;
                $dr_total = $dr_total + $row2->amount;
                }
                else{
                $cr_amount = $cr_amount+$row2->amount;
                $cr_total = $cr_total + $row2->amount;
                    }
                }
        }//foreach
        $total = $dr_total . "#" . $cr_total;
        return $total;
}

function current_asset_A($led_id)
{
	$credit_amount = 0.00;
	$debit_amount = 0.00;
	$dr_total = 0.00;
	$cr_total = 0.00;
	$CI = & get_instance();
	$CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$led_id);
       	$entry = $CI->db->get();
        $entry_q = $entry->result();
        foreach($entry_q as $entry1)
        {
        if($entry1->dc == 'D')
       	{
        $debit_amount = $debit_amount+$entry1->amount;
        $dr_total = $dr_total+$entry1->amount;
     	}else{  
        $credit_amount = $credit_amount+$entry1->amount;
        $cr_total = $cr_total+$entry1->amount;
            }
	}//foreach
	$total1 = $dr_total . "#" . $cr_total;
	return $total1;
}

function schedule_1($ledger_id)
{
	$c_total = 0.00;	
	$credit_total = 0.00;
	$d_total = 0.00;
	$debit_total = 0.00;
	$CI =& get_instance();
        $CI->db->select('entry_id, id, amount, dc');
        $CI->db->from('entry_items')->where('ledger_id', $ledger_id);
        $entry_items_q = $CI->db->get();
        if($entry_items_q->num_rows() > 0)
        {
        	$entry_items_result = $entry_items_q->result();
                foreach ($entry_items_result as $row)
                {
                if($row->dc == 'C')
                {
		$c_total = $c_total + $row->amount;
                $credit_total = $credit_total + $row->amount;
                }
                else
                {
		$d_total = $d_total + $row->amount;
                $debit_total = $debit_total + $row->amount;
                }
                }//foreach
       }//if
	$total_a = $c_total . "#" . $d_total;
	return $total_a;
}

	function merge_Funds()
	{
		$capital = new Reportlist1();
        	$capital->init(5);
        	$capital_total = $capital->total;
        	$corpus = new Reportlist1();
        	$corpus->init(6);
         	$corpus_total = $corpus->total;
	 	$liability_total1 = ($capital_total + $corpus_total);
	 	$total = $liability_total1;
	 	return $total;
	} 
	
	function newschedule1($ledg_id)
	{
                $credit_amount = 0.00;
                $debit_amount = 0.00;
                $dr_total = 0.00;
                $cr_total = 0.00;
                $CI =& get_instance();
                $CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id',$ledg_id);
                $entry = $CI->db->get();
                $entry_q = $entry->result();
                foreach($entry_q as $entry1)
                {
                if($entry1->dc == 'D')
                {
                $debit_amount = $debit_amount+$entry1->amount;
                $dr_total = $dr_total+$entry1->amount;
                }else{
                $credit_amount = $credit_amount+$entry1->amount;
                $cr_total = $cr_total+$entry1->amount;
                }
                }//foreach
                $total = $dr_total . "#" . $cr_total;
                return $total;
    }

    function mergingoffunds($accname)
    {
        $capital = new Aggregatereportlist();
        $capital->init(5,$accname);
        $capital_total = $capital->total;
        $corpus = new Aggregatereportlist();
        $corpus->init(6,$accname);
        $corpus_total = $corpus->total;
        $liability_total1 = ($capital_total + $corpus_total);
        $total = $liability_total1;
        return $total;
    }

}//main
?>
