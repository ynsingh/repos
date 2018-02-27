<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/*
 * Class for creating Income and Expense of an account and stored in xml file 
 * @author Sharad Singh<sharad23nov@yahoo.com> 
 * @creation date 14-08-2015
 */
class AggregateIncExp
{
	function AggregateIncExp()
	{
		return;
	}

	function init($id,$accname)
	{
		$CI =& get_instance();

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
			try {
                        	$dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
                                $mgroup = "select * from groups where id=$id";
                                $stmt = $dbcon->query($mgroup);

                                if($stmt != false)
                                {
                                        foreach ($stmt as $row)
                                        {
	                                        $this->id = $row['id'];
                                                $this->name = $row['name'];
                                                $this->code = $row['code'];
                                                $this->total = 0;
						//$this->total2 =0;
					}
				}
                        }
                        catch(PDOException $e)
                        {
                                echo $e->getMessage();
                        }
		$this->add_sub_groups($accname);
		$this->add_income_expense_sub_ledgers($accname);
		
	}

	//get details of ledgers 

	function init_led($led_id,$accname)
	{
                $CI =& get_instance();
                $CI->db->from('ledgers')->where('id', $led_id)->limit(1);
                $ledger_q = $CI->db->get();
                if($ledger_q->num_rows() > 0)
		{
                      $ledger = $ledger_q->row();
                      $this->id = $ledger->id;
                      $this->name = $ledger->name;
                      $this->code = $ledger->code;
                      $this->total = $CI->Ledger_model->get_ledger_balance1($ledger->id);
                      list ($this->opbalance, $this->optype) = $CI->Ledger_model->get_op_balance($ledger->id);
                      $this->total2 = $CI->Ledger_model->get_balancesheet_old_ledger_balance($ledger->id);
                      list ($this->opbalance_prev, $this->optype) = $CI->Ledger_model->get_prev_year_op_balance($ledger->id);
                }

        }

	//add sub groups of groups in recurssive order.

        function add_sub_groups($accname)
        {
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
                try{
                        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
                        $mgroup = "select * from groups where parent_id=$this->id";
                        $stmt = $dbcon->query($mgroup);
                        if($stmt != false)
                        {
                                $counter = 0;
                                foreach ($stmt as $row)
                                {
                                        $id=$row['id'];
                                        $this->children_groups[$counter] = new AggregateIncExp();
                                        $this->children_groups[$counter]->init($id,$accname);
                                        $this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');
                                        $counter++;
                                }
                        }
                }
                catch(PDOException $e)
                {
                        echo $e->getMessage();
                }
		catch(Exception $e) 
		{
  			echo $e->getMessage();
		}
                $dbcon = null;
        }

	function add_balancesheet_sub_ledgers($accname)
	{
		$CI =& get_instance();
		$CI->load->model('Ledger_model');
		$CI->db->from('ledgers')->where('group_id', $this->id);
		$child_ledger_q = $CI->db->get();
		$counter = 0;
		foreach ($child_ledger_q->result() as $row)
		{
			$this->children_ledgers[$counter]['id'] = $row->id;
			$this->children_ledgers[$counter]['code'] = $row->code;
			$this->children_ledgers[$counter]['name'] = $row->name;

			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_balancesheet_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');

			$this->children_ledgers[$counter]['total2'] = $CI->Ledger_model->get_balancesheet_old_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_prev_year_op_balance($row->id);
			$this->total2 = float_ops($this->total2, $this->children_ledgers[$counter]['total2'], '+');

			$counter++;
		}
	}
	
	//get income and expense ledgers.

	function add_income_expense_sub_ledgers($accname)
	{
		$CI =& get_instance();
		$CI->load->model('Ledger_model');

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


                try{
                        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
                        $mledger = "select * from ledgers where group_id=$this->id";
                        $stmt = $dbcon->query($mledger);
			$counter = 0;

                        if($stmt != false)
                        {
                                foreach ($stmt as $row)
                                {
				
					if($row['name'] != 'Transfer Account')
					{
                                        	$this->children_ledgers[$counter]['id'] = $row['id'];
	                                        $this->children_ledgers[$counter]['code'] = $row['code'];
        	                                $this->children_ledgers[$counter]['name'] = $row['name'];
                	                        $this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_ledger_balance1_agg($row['id'],$accname);
                        	                list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance_agg($row['id'],$accname);
	                              	        $this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');
                                        	$counter++;
					}

                                }
			
                        }

                }
                catch(PDOException $e)
                {
                        $e->getMessage();
                }

	}

	function income_expense_diff()
	{
        	$income = new AggregateIncExp();
	        $income->init('3');
        	$total = $income->total;
	        $expense = new AggregateIncExp();
        	$expense->init('4');
	        $total1 = $expense->total;
        	$total = 0 - $total;
	        $diff = $total - $total1;
        	return $diff;
	}
 

	function numberToRoman($num)
	{
        $n = intval($num);
        $result = '';
        $lookup = array('M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
                     'C' => 100, 'XC' => 90, 'L' => 50, 'XL' => 40,
                     'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1);

        foreach ($lookup as $roman => $value)
        {
                $matches = intval($n / $value);
                $result .= str_repeat($roman, $matches);
                $n = $n % $value;
        }
        return $result;
	}

	//get income and expense head and store in xml file.

	function aggincomeexp($id, $type, $accname, $username)
    	{
		$username;
	        $c =14;
        	$counter=8;
	
        	$total = "";
	        $sum = "";
        	$CI =& get_instance();
		
        	$db1=$CI->load->database('login', TRUE);
	        $db1->from('bgasAccData')->where('dblable', $accname);
        	$accdetail = $db1->get();

		//get details of accounts for bgasAccData

	        foreach ($accdetail->result() as $row)
        	{
	        	$db_name = $row->databasename;
	                $db_username = $row->uname;
        	        $db_password = $row->dbpass;
                	$host_name = $row->hostname;
	                $port = $row->port;
        	}
        	try 
		{
			//mysql connection for accounts database.

	        	$dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
	                $mgroup = "select name,code,id from groups where parent_id=$id";
        	        $stmt = $dbcon->query($mgroup);

                	if($stmt != false)
                	{
        	        	foreach ($stmt as $row)
                        	{
	                	        $this->id = $row['id'];
        	                        $this->name = $row['name'];
                	                $this->code = $row['code'];
                        	        $this->total = 0;
					if($id == 3 && $type == "view")
					{
						$cnt = $counter++;
						$income = new AggregateIncExp();
						$income->init($this->id,$accname);
						$total = $income->total;
						$sum = $sum + $total;
						$total = 0 - $total;
						$sch = $cnt;
					}

					if($id == 4 && $type == "view")
					{
						if($this->name == 'Depreciation')
						{
							//$c = 4;
							$sch = 4;
						}
						else
						{
							//$c = $c+1;
							$c = $c + 1;
							$sch = $c;
						}
	                                        $income = new AggregateIncExp();
        	                                $income->init($this->id,$accname);
                	                        $total = $income->total;
                        	                $sum = $sum + $total;
						//$sch = $c;
					}
                        		//create accounts xml file for Imcome and Expense .

                        		//path for storing xml file

		                        $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
	
					//income and expense data will be stored in seperate xml files of accounts.

	                	        $incomefile = $username."_".$accname."_inc.xml";
					$expensefile = $username."_".$accname."_exp.xml";

                		        $doc = new DOMDocument();
		                        $doc->formatOutput = true;

					if(substr($row['code'],0,1) == 3) 
						$tt = $acctpath."/".$incomefile;
					else
						$tt = $acctpath."/".$expensefile;

		                        if(file_exists($tt))
                 		        {
		                                $doc->preserveWhiteSpace = false;
                		                $doc->load($tt);
                                		$Budgets = $doc->firstChild;
		                                $Code = $doc->createElement('Code');
                		                $Code->setAttribute('id', $row['id']);
                                		$Code->setAttribute('code', $row['code']);
		                                $Code->setAttribute('name', $row['name']);
                		                $Code->setAttribute('schedule', $sch);
                                		$Code->setAttribute('total', $total);

		                                $Budgets->appendChild($Code);

                		                $ttt=$doc->saveXML();
                                		$handle = fopen($tt, "w");
		                                fwrite($handle, $ttt);
                		                fclose($handle);
                        		}
		                        else
                		        {
                                		$r = $doc->createElement( 'IncExp' );
		                                $doc->appendChild( $r );

                		                $Code = $doc->createElement('Code');
                                		$Code->setAttribute('id', $row['id']);
		                                $Code->setAttribute('code', $row['code']);
                		                $Code->setAttribute('name', $row['name']);
                                		$Code->setAttribute('schedule', $sch);
		                                $Code->setAttribute('total', $total);

                		                $r->appendChild($Code);

                                		$doc->save($tt);
		                                $doc->saveXML();
                		        }
				}
                	}
        	}
        	catch(PDOException $e)
        	{
         		echo $e->getMessage();
        	}
		$dbcon = null;
		//echo $sum;
    		return $sum;
    	}
}

