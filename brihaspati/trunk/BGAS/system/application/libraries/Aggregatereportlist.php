<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Aggregatereportlist
{
	var $id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $total = 0;
	var $total2 = 0;
	var $optype = "";
	var $opbalance = 0;
	var $schedule = 0;
	var $children_groups = array();
	var $children_ledgers = array();
	var $counter = 0;
	//var $check = 0;

	var $dr_total = 0;
    var $cr_total = 0;
    var $old_dr_total = 0;
    var $old_cr_total = 0;
    var $netpl = 0;
    var $netpl_old = 0;
	var $prevYearDB = "";
    var $old_total = 0;
	var $opening_balance = 0;
	var $opening_balance_prev = 0;
	//var $opening_balance_type = "";
	//var $db_username = "";
	//var $db_password = "";
	//var $host_name = "";
	//var $port = "";
	//var $new_link;
	//var $k=0;
	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;
	var $k=0;
	var $databasehost="";
	
	function Aggregatereportlist()
	{
		return;
	}
	public function init($id,$accname)
	{
		$CI =& get_instance();
		$db1=$CI->load->database('login', TRUE);
	    	
		//get database detail of account

		$db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $databasehost=$row->hostname;
	        $databasename= $row->databasename;
            $databaseport=$row->port;
            $databaseusername=$row->uname;
	        $databasepassword=$row->dbpass;
	        $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);

            if ($new_link)
            {
                $db_selected = mysql_select_db($databasename, $new_link);
               	    if ($db_selected) 
                    {
                		if ($id == 0)
		                {
        		        	$this->id = 0;
        		        	$this->name = "None";
                			$this->total = 0;
		                }
        		        else 
                        {
                            $query = sprintf("select * from groups where id=$id limit 1");
                            $result = mysql_query($query);
                            if (!$result) {
                                $message  = 'Invalid query: ' . mysql_error() . "\n";
                                $message .= 'Whole query: ' . $query;
                                die($message);
                        }
                        while ($row = mysql_fetch_assoc($result)) 
                        {
          				    $this->id=$row['id'];
                            $this->name=$row['name'];
                            $this->code=$row['code'];
            				$this->total = 0;
	                        $this->total2 = 0;
                        }
		            }
		            if($this->status==0)
		            {
        			    $new_code = substr($this->code, 0, $this->code < 0 ? 3 : 2);
    		        	if($new_code == 10 || $new_code == 20)
    			        {
            				$this->add_sub_groups($accname);
		            		$this->add_balancesheet_sub_ledgers($accname);
			            }
			            elseif($new_code == 30 || $new_code == 40)
			            {
				            $this->add_sub_groups($accname);
            				$this->add_income_expense_sub_ledgers($accname);
	    		        }
		            }
		        }
		    }
	    }
	}//end of init

	function add_sub_groups($accname)
	{
		$CI =& get_instance();
		$query1 = sprintf("SELECT * from groups where parent_id=$this->id");
        $result1 = mysql_query($query1);
        if (!$result1) 
        {
            $message  = 'Invalid query: ' . mysql_error() . "\n";
            $message .= 'Whole query: ' . $query1;
            die($message);
        }
		$counter = 0;
        while($row = mysql_fetch_assoc($result1)) 
        {
            $id=$row['id'];
            $this->children_groups[$counter] = new Aggregatereportlist();
            $this->children_groups[$counter]->init($id,$accname);
            $this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');
            $counter++;
        }

	}//end of add subgroup.

	function add_balancesheet_sub_ledgers($accname)
	{
		$CI =& get_instance();
		$CI->load->model('Ledger_model');
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $databasehost=$row->hostname;
            $databasename= $row->databasename;
            $databaseport=$row->port;
            $databaseusername=$row->uname;
            $databasepassword=$row->dbpass;
            $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
            if ($new_link)
            {
                $db_selected = mysql_select_db($databasename, $new_link);
                if ($db_selected) 
                {
				    $messages;
                }
            }
        }
		$queryleg = sprintf("SELECT * from ledgers where group_id=$this->id");
		$result2 = mysql_query($queryleg);
        if (!$result2) 
        {
	        $message  = 'Invalid query: ' . mysql_error() . "\n";
            $message .= 'Whole query: ' . $query;
            die($message);
        }
		$counter = 0;
		while ($row = mysql_fetch_assoc($result2)) 
        {
            $this->children_ledgers[$counter]['id']=$row['id'];
            $this->children_ledgers[$counter]['name']=$row['name'];
            $this->children_ledgers[$counter]['code']=$row['code'];
			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_balancesheet_ledger_balance_agg($row['id'],$accname);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance_agg($row['id'],$accname);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');
		}
		$counter++;
	}

	function add_income_expense_sub_ledgers($accname)
	{
	    $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $databasehost=$row->hostname;
            $databasename= $row->databasename;
            $databaseport=$row->port;
            $databaseusername=$row->uname;
            $databasepassword=$row->dbpass;
            $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
            if ($new_link)
            {
                $db_selected = mysql_select_db($databasename, $new_link);
                if ($db_selected) {
                }
            }
        }
		$CI->load->model('Ledger_model');
        $queryleg = sprintf("SELECT * from ledgers where group_id=$this->id");
        $result2 = mysql_query($queryleg);
        if (!$result2) 
        {
            $message  = 'Invalid query: ' . mysql_error() . "\n";
            $message .= 'Whole query: ' . $query;
            die($message);
        }
		$counter = 0;
		while ($row = mysql_fetch_assoc($result2)) 
		{
			if($row['name'] != 'Transfer Account'){
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

	/* Display Account list in Balance sheet and Profit and Loss st */
	function account_st_short($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
			echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
			echo "</tr>";
		}
		foreach ($this->children_groups as $id => $data)
		{
			$this->counter++;
			$data->account_st_short($this->counter);
			$this->counter--;
		}
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				echo "<tr class=\"tr-ledger\">";
				echo "<td class=\"td-ledger\">";
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td align=\"right\">" . convert_amount_dc($data['total']) . "</td>";
				echo "<td align=\"right\">" . convert_amount_dc($data['total2']) . "</td>";
				echo "</tr>";
			}
			$this->counter--;
		}
	}
    
	/* Display balancesheet in MHRD format */		
    
    function new_balance_sheet($c =0,$accname)
    {
        $check = 0;
        $this->counter = $c;
		$amt="";
        $CI =& get_instance();

        //get database detail of account

        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        //$CI->messages->add("Test");
        foreach ($accdetail->result() as $row)
        {
            $databasehost=$row->hostname;
            $databasename= $row->databasename;
            $databaseport=$row->port;
            $databaseusername=$row->uname;
            $databasepassword=$row->dbpass;
            $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
            if ($new_link)
            {
               	$db_selected = mysql_select_db($databasename, $new_link);
				if ($db_selected) 
                {
                }
            }
        }
		$CI =& get_instance();
        $CI->load->model('Setting_model');
        $ledger_name = $CI->Setting_model->get_from_settings_agg('ledger_name',$accname);
		$nodigit = $this->countDigits();
		$codedigit = $this->code;		
		if(($this->countDigits() == 4) && ($this->id != 0) && ($this->code > 100))
        {
		    if($this->name == 'Unrestricted Funds'){
			$check++;
		}
        else
        {
		    $check = 0;
	    }
	
		if($check == 0){
		$this->counter++;
		
		/* Get Balance of net income/(expenditure) for 'this' ledger head*/
	                        //if($c == 2){
				if($ledger_name == $this->name){
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
						//the change in sign is needed
				                if($pandl > 0)
							$this->total = float_ops($this->total, -$pandl, '+');
						else
							$this->total = float_ops($this->total, -$pandl, '+');
						if($old_pandl > 0)
				                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
						else
				                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
					}
	                        }
			}else{
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
                                      //the change in sign is needed
                                      if($pandl > 0)
                                              $this->total = float_ops($this->total, -$pandl, '+');
                                      else
                                              $this->total = float_ops($this->total, -$pandl, '+');
                                      if($old_pandl > 0)
                                              $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                      else
                                              $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                }
                        }


			$amt=convert_amount_dc($this->total);
		}elseif($this->countDigits() == 6 && $this->id != 0 && $this->code > 100){

			$getp = "select parent_id from groups where id = $this->id";
                        $val =mysql_query($getp);
                        while($row = mysql_fetch_assoc($val))
                        {
                                $parent_id=$row['parent_id'];
                        }
                        $getp1 = "select name from groups where id = $parent_id";
                        $val1 =mysql_query($getp1);
                        while($row1 = mysql_fetch_assoc($val1))
                        {
                                $name=$row1['name'];
                        }
			if($name  == 'Unrestricted Funds'){
			$this->counter++;

				//if($c == 2){
				if($ledger_name == $this->name){
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
						//the change in sign is needed
                                                if($pandl > 0)
                                                        $this->total = float_ops($this->total, -$pandl, '+');	
                                                else
                                                        $this->total = float_ops($this->total, -$pandl, '+');
                                                if($old_pandl > 0)
                                                        $this->total2 = float_ops($this->total2,-$old_pandl, '+');
                                                else
                                                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                        }
                        	}
			}
			$amt = convert_amount_dc($this->total);
		}
		$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
		$file_name="";
		
		/* 
		 * code for creating Source of funds Xml file.
		*/
		
		if($this->counter< 7)
		{
	                $doc = new DOMDocument();
        	        $doc->formatOutput = true;

			$file_name=$accname."_Liabilty.xml";
			$tt=$acctpath."/".$file_name;
			//$person = array("name" => $this->name,;
			if(file_exists($tt))
			{
				$doc->preserveWhiteSpace = false;
				$doc->load($tt);
				$Liabilities = $doc->firstChild;
	
				$Liability_Name = $doc->createElement('Liability_Name');

    			$group_name = $doc->createElement('Group_Name');
				$textNode = $doc->createTextNode($this->name);
    			$group_name->appendChild($textNode);
				$Liability_Name->appendChild($group_name);
			
				$code_no = $doc->createElement('Code_No');
				$textNode1 = $doc->createTextNode($this->counter);
				$code_no->appendChild($textNode1);
				$Liability_Name->appendChild($code_no);

                                //echo $amt;
                                $amount = $doc->createElement('Amount');
                                $textNode2 = $doc->createTextNode($amt);
                                $amount->appendChild($textNode2);
                                $Liability_Name->appendChild($amount);

                                $codenu = $doc->createElement('Code_Nu');
                                $textNode3 = $doc->createTextNode($this->code);
                                $codenu->appendChild($textNode3);
                                $Liability_Name->appendChild($codenu);

                                $counter = $doc->createElement('Counter');
                                $textNode4 = $doc->createTextNode($this->countDigits());
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
                                $group_name->appendChild($doc->createTextNode($this->name));
                                $b->appendChild( $group_name );
				
                                $code_no = $doc->createElement( "Code_No");
                                $code_no->appendChild($doc->createTextNode($this->counter));
                                $b->appendChild( $code_no );

                                $amount = $doc->createElement( "Amount");
                                $amount->appendChild($doc->createTextNode($amt));
                                $b->appendChild( $amount );

                                $codenu = $doc->createElement('Code_Nu');
                                $textNode3 = $doc->createTextNode($this->code);
                                $codenu->appendChild($textNode3);
				$b->appendChild( $codenu );

                                $counter = $doc->createElement('Counter');
                                $textNode4 = $doc->createTextNode($this->countDigits());
                                $counter->appendChild($textNode4);
				$b->appendChild( $counter );
			
                                $r->appendChild( $b );

                                $doc->save($tt);
                                $doc->saveXML();
			}
		}//end of if of Liabilty

		if(($this->counter > 6) && ($this->counter < 11)) 
		{
	                $doc = new DOMDocument();
        	        $doc->formatOutput = true;

			$file_name=$accname."_Assets.xml";
			$tt=$acctpath."/".$file_name;
			if(file_exists($tt))
			{
				$doc->preserveWhiteSpace = false;
				$doc->load($tt);
				$Liabilities = $doc->firstChild;
	
				$Liability_Name = $doc->createElement('Assets_Name');

    			$group_name = $doc->createElement('Group_Name');
				$textNode = $doc->createTextNode($this->name);
    			$group_name->appendChild($textNode);
				$Liability_Name->appendChild($group_name);
			
				$code_no = $doc->createElement('Code_No');
				$textNode1 = $doc->createTextNode($this->counter);
				$code_no->appendChild($textNode1);
				$Liability_Name->appendChild($code_no);

                $amount = $doc->createElement('Amount');
                $textNode2 = $doc->createTextNode($amt);
                $amount->appendChild($textNode2);
                $Liability_Name->appendChild($amount);

                $codenu = $doc->createElement('Code_Nu');
                $textNode3 = $doc->createTextNode($this->code);
                $codenu->appendChild($textNode3);
                $Liability_Name->appendChild($codenu);

                $counter = $doc->createElement('Counter');
                $textNode4 = $doc->createTextNode($this->countDigits());
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
                                $group_name->appendChild($doc->createTextNode($this->name));
                                $b->appendChild( $group_name );
				
                                $code_no = $doc->createElement( "Code_No");
                                $code_no->appendChild($doc->createTextNode($this->counter));
                                $b->appendChild( $code_no );
                    
                                $amount = $doc->createElement( "Amount");
                                $amount->appendChild($doc->createTextNode($amt));
                                $b->appendChild( $amount );

                                $codenu = $doc->createElement('Code_Nu');
                                $textNode3 = $doc->createTextNode($this->code);
                                $codenu->appendChild($textNode3);
                				$b->appendChild( $codenu );

                                $counter = $doc->createElement('Counter');
                                $textNode4 = $doc->createTextNode($this->countDigits());
                                $counter->appendChild($textNode4);
				                $b->appendChild( $counter );
//////
                                
////
                                $r->appendChild( $b );

                                $doc->save($tt);
                                $doc->saveXML();
			}
		}//end of if of Assets


		foreach ($this->children_groups as $id => $data)
                {
                        $len = $data->countDigits();
                        $this->counter = $data->new_balance_sheet($this->counter,$accname);
  
                }
		//}
                return $this->counter;
        }//end of new balancesheet

        	
        function countDigits()
        {
                //preg_match_all( "/[0-9]/", $str, $arr );
                $search = '1234567890';
                $count = strlen($this->code) - strlen(str_replace(str_split($search), '', $this->code));
                return $count;
        }	

	function callToOpBalance($year, $name){
                $credit_total = 0;
                $debit_total = 0;
		$old_credit_total = 0;
		$old_debit_total = 0;
		$op_balance = 0;
		$old_op_balance = 0;
		$total = 0;
		$total2 = 0;

		if($year == 'new'){
			if( $name == 'schedule'){
                		list($credit_total, $debit_total, $op_balance) = $this->calculate_op_balance($year, $name);
				$this->cr_total = $this->cr_total + $credit_total;
		                $this->dr_total = $this->dr_total + $debit_total;
				$this->opening_balance = $this->opening_balance + $op_balance;
			}
			else
			{
				list($total, $op_balance) = $this->calculate_op_balance($year, $name);
	                        $this->total = $this->total + $total;
        	                $this->opening_balance = $this->opening_balance + $op_balance;
			}
		}
		elseif($year == 'old'){ 
			if( $name == 'schedule'){
	                	list($old_credit_total, $old_debit_total, $old_op_balance) = $this->calculate_op_balance($year, $name);
				$this->old_cr_total = $this->old_cr_total + $old_credit_total;
		                $this->old_dr_total = $this->old_dr_total + $old_debit_total;
				$this->opening_balance_prev = $this->opening_balance_prev + $old_op_balance;
			}
			else
			{
                        	list($total2, $old_op_balance) = $this->calculate_op_balance($year, $name);
	                        $this->total2 = $this->total2 + $total2;
        	                $this->opening_balance = $this->opening_balance + $old_op_balance;
                	}
		}

        }

	function calculateOpBalance($year, $name)
        {
		$CI =& get_instance();
                $CI->load->model('Ledger_model');
                if($year == 'new'){
    	            //list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
                    list($opBal, $optype) = $CI->Ledger_model->get_op_balance($this->id);
                    $this->opening_balance = $this->opening_balance + $opBal;
                    if($name == 'schedule'){
	                    if($optype == 'C')
        	                    $this->cr_total = $this->cr_total + $opBal;
                            elseif($optype == 'D')
                                    $this->dr_total = $this->dr_total + $opBal;
                    }else
                    {
                            if($optype == 'C')
                                    $this->total = $this->total - $opBal;
                            elseif($optype == 'D')
                                    $this->total = $this->total + $opBal;
                    }

                }elseif($year == 'old'){
                    list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($this->id);
                    $this->opening_balance_prev = $this->opening_balance_prev + $opBal;
                    if($name == 'schedule'){
                   	 if($optype == 'C')
                        	 $this->old_cr_total = $this->old_cr_total + $opBal;
                         elseif($optype == 'D')
                                 $this->old_dr_total = $this->old_dr_total + $opBal;
                    }else{
                         if($optype == 'C')
                                 $this->total2 = $this->total2 - $opBal;
                         elseif($optype == 'D')
                                 $this->total2 = $this->total2 + $opBal;
                    }
                }
                foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        $data->calculate_op_balance($this->counter, $name);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        foreach ($this->children_ledgers as $id => $data)
                        {
                                //Get opening balance
                                $CI =& get_instance();
                                $CI->load->model('Ledger_model');
                                if($year == 'new'){
                                        list($opBal, $optype) = $CI->Ledger_model->get_op_balance($data['id']);
                                        $this->opening_balance = $this->opening_balance + $opBal;
                                        if($name == 'schedule'){
                                                if($optype == 'C')
                                                        $this->cr_total = $this->cr_total + $opBal;
                                                elseif($optype == 'D')
                                                        $this->dr_total = $this->dr_total + $opBal;
                                        }else
                                        {
                                                if($optype == 'C')
                                                        $this->total = $this->total - $opBal;
                                                elseif($optype == 'D')
                                                        $this->total = $this->total + $opBal;
                                        }

                                }
                                elseif($year == 'old'){
                                        list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
                                        $this->opening_balance_prev = $this->opening_balance_prev + $opBal;
                                        if($name == 'schedule'){
                                                if($optype == 'C')
                                                        $this->old_cr_total = $this->old_cr_total + $opBal;
                                                elseif($optype == 'D')
                                                        $this->old_dr_total = $this->old_dr_total + $opBal;
                                        }
                                        else
                                        {
                                                if($optype == 'C')
                                                        $this->total2 = $this->total2 - $opBal;
                                                elseif($optype == 'D')
                                                        $this->total2 = $this->total2 + $opBal;
                                        }
                                }
                        }
                }
        }

	function calculate_op_balance($year, $name)
	{
		static $credit_total = 0;
                static $debit_total = 0;
                static $old_credit_total = 0;
                static $old_debit_total = 0;
                static $total = 0;
                static $total2 = 0;
		static $op_balance = 0;
                static $old_op_balance = 0;

		if($year == null)
		{
			$credit_total = null;
	                $debit_total = null;
        	        $old_credit_total = null;
                	$old_debit_total = null;
	                $op_balance = null;	
        	        $old_op_balance = null;
			$total = null;
			$total2 = null;
		}
		else{
			foreach ($this->children_groups as $id => $data)
			{
                	        $this->counter++;
				$data->calculate_op_balance($year, $name);
        	                $this->counter--;
                	}
	                if (count($this->children_ledgers) > 0)
        	        {
                        	foreach ($this->children_ledgers as $id => $data)
				{
                	                $CI =& get_instance();
                        	        $CI->load->model('Ledger_model');
					if($year == 'new'){
		                                list($opBal, $optype) = $CI->Ledger_model->get_op_balance($data['id']);
        		                        $op_balance = $op_balance + $opBal;
						if($name == 'schedule'){
							if($optype == 'C')
								$credit_total = $credit_total + $opBal;
							elseif($optype == 'D')
                	        	        		$debit_total = $debit_total + $opBal;
						}else
						{
							if($optype == 'C')
                                                        	$total = $total - $opBal;
	                                                elseif($optype == 'D')
                	                                        $total = $total + $opBal;
						}
	
					}
					elseif($year == 'old'){
					        list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
                                        	$old_op_balance = $old_op_balance + $opBal;
						if($name == 'schedule'){
		                                        if($optype == 'C')
        	        	                                $old_credit_total = $old_credit_total + $opBal;
                	        	                elseif($optype == 'D')
                        	                	        $old_debit_total = $old_debit_total + $opBal;	
						}	
						else
						{
							if($optype == 'C')
                                        	                $total2 = $total2 - $opBal;
                                                	elseif($optype == 'D')
		                                                $total2 = $total2 + $opBal;
						}
                        	        }
				}
			}
		}//else null

		if($year == 'new'){
			if($name == 'schedule')
				return array($credit_total, $debit_total, $op_balance);
			else
				return array($total, $op_balance);
		}
		elseif($year == 'old'){
			if($name == 'schedule')
				return array($old_credit_total, $old_debit_total, $old_op_balance);
			else
				return array($total2, $old_op_balance);
		}
	}

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	function callToSchedule($c = 0){
		$credit_total = 0;
		$debit_total = 0;
		list($credit_total, $debit_total) = $this->schedule($c);
		$this->cr_total = $this->cr_total + $credit_total;
		$this->dr_total = $this->dr_total + $debit_total;
	}

	function callToOldSchedule($c = 0){
                $old_credit_total = 0;
                $old_debit_total = 0;
                list($old_credit_total, $old_debit_total) = $this->previous_year_data($c);
                $this->old_cr_total = $this->old_cr_total + $old_credit_total;
                $this->old_dr_total = $this->old_dr_total + $old_debit_total;
        }

	/* Displays schedule */
        function schedule($c = 1)
        {
		static $credit_total = 0;
		static $debit_total = 0;
		
		if($c == null)
		{
			$credit_total = null;
                	$debit_total = null;
		}else{

                $len = $this->countDigits();
		if($this->id != 0  && $len > 6)
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" . "<b>" . $this->name . "</b>";
                        echo "</td>";

                        echo "</tr>";

                }
                foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        $data->schedule($this->counter);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        foreach ($this->children_ledgers as $id => $data)
                        {
				$c_total = 0.00;
				$d_total = 0.00;
				
				echo "<tr class=\"tr-ledger\">";
	                                echo "<td class=\"td-ledger\">";
        	                                echo $this->print_space($this->counter);
                	                        echo "&nbsp;" . "<b>" . $data['name'] . "<b>";
                                        echo "</td>";

                                $CI =& get_instance();
                                $CI->db->select('entry_id, id, amount, dc');
                                $CI->db->from('entry_items')->where('ledger_id', $data['id']);
                                $entry_items_q = $CI->db->get();
                                if($entry_items_q->num_rows() > 0)
                                {
                                        $entry_items_result = $entry_items_q->result();
                                        //$counter = 0;
                                        foreach ($entry_items_result as $row)
                                        {
                                                if($row->dc == 'C'){
                                                        $CI =& get_instance();
                                                        $CI->db->select('narration');
                                                        $CI->db->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $CI->db->get();
                                                        //$entries = $entries_q->row();
                                                        foreach($entries_q->result() as $entries){
                                                                $narration = $entries->narration;
                                                        }                                                        
							$c_total = $c_total + $row->amount;
							$credit_total = $credit_total + $row->amount;
                                                }
                                                else{
                                                        $CI =& get_instance();
                                                        $CI->db->select('narration');
                                                        $CI->db->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $CI->db->get();
                                                        //$entries = $entries_q->row();
                                                        foreach($entries_q->result() as $entries){
                                                                $narration = $entries->narration;
                                                        }
		                                        $d_total = $d_total + $row->amount;
							$debit_total = $debit_total + $row->amount;                                               
                                                }
                                        }
					echo "<td align=\"right\">";
                        	                echo convert_amount_dc($d_total);
		                        echo "</td>";
	
                		        echo "<td align=\"right\">";
                	                        echo convert_amount_dc(-$c_total);
		                        echo "</td>";

					echo "<td>";
                                                echo "";
                                        echo "</td>";

                                        echo "<td>";
                                                echo "";
					echo "</td>";
                        		echo "</tr>";
                                }

                        }                        
                }

		}//else for null
                return array($credit_total, $debit_total);
        }

        function previous_year_data($c = 0)
        {
		$narration = '';
		$amount = 0;
		static $old_credit_total = 0;
		static $old_debit_total = 0;

		$c_total = 0;
		$d_total = 0;

		if($c == null)
                {
                        $old_credit_total = null;
                        $old_debit_total = null;
                }else{

                foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        //$data->schedule($this->counter);
			$data->previous_year_data($this->counter);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        foreach ($this->children_ledgers as $id => $data)
                        {
				$c_total = 0;
				$d_total = 0;			
	
                		if($this->prevYearDB != "" ){//3
                        		/* database connectivity for getting previous year opening balance */
	                        	$con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
	        	                $op_balance = array();
        	        	        if($con){
                	        	        $value = mysql_select_db($this->prevYearDB, $con);
                        	        	$id = mysql_real_escape_string($data['id']);
	                        	        $cl = "select entry_id, id, amount, dc from entry_items where ledger_id = '$id'";
        	                        	$val = mysql_query($cl);
	                	                if($val != ''){
        	                	                while($row = mysql_fetch_assoc($val))
                	                	        {
								if($row != null){
									$con1 = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                        		if($con1){
		                                                                $value = mysql_select_db($this->prevYearDB, $con1);
                		                                                $id1 = mysql_real_escape_string($row['entry_id']);
                                		                                $cl1 = "select narration from entries where id = '$id1'";
                                                		                $val1 = mysql_query($cl1);
                                                                		if($val1 != ''){
		                                                                        while($row1 = mysql_fetch_assoc($val1))
                		                                                        {
                                		                                                if($row1 != null){
                                                		                                        $narration = $row1['narration'];
	                                               							if($row['dc'] == 'C'){
														$old_credit_total = $old_credit_total + $row['amount'];
														$c_total = $c_total + $row['amount'];
					                                                		}
                                                							else{
														$old_debit_total = $old_debit_total + $row['amount'];
														$d_total = $d_total + $row['amount'];
                                                							}
												}
												mysql_close($con1);
                                                					}
                                                				}
                                        				}
                               					}
									echo "<tr>";
									echo "<td>";
                                                                                echo "";
                                                                        echo "</td>";

									echo "<td>";
										echo "";
									echo "</td>";

									echo "<td>";
										echo "";
									echo "</td>";

									echo "<td align=\"right\">";
                                                                        	echo convert_amount_dc($d_total);
                                                                        echo "</td>";

                                                                        echo "<td align=\"right\">";
                                                                                echo convert_amount_dc(-$c_total);
                                                                        echo "</td>";
                                                                        
									echo "</tr>";
                                                                        
                                                        }
		
                                                }
                                        }
					

                                }
                        }
                }
		}//else null
		return array($old_credit_total, $old_debit_total);
        }//method


	/* Display chart of accounts view */
	function account_st_main($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			if ($this->id <= 4)
				{echo "&nbsp;<strong>" .  $this->code . "</strong>";
				}
			else
				{echo "&nbsp;" .  $this->code;
				}
			echo "</td>";
			echo "<td class=\"td-group\">";
			echo $this->print_space($this->counter);
			if ($this->id <=4)
				echo "&nbsp;<strong>" .  $this->name. "</strong>";
			else
				echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td>Group Account</td>";
			echo "<td>-</td>";
			echo "<td>-</td>";
			if ($this->id <= 4)
			{
				echo "<td class=\"td-actions\"></tr>";
			} else {
				echo "<td class=\"td-actions\">" . anchor('group/edit/' . $this->id , "Edit", array('title' => 'Edit Group', 'class' => 'red-link'));
				$id1=$this->id;
				$status1=$this->status;
				if (  check_access('administer'))
				{
				if($this->status == 0)				
					echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Hide',  array('title' => 'Edit Group', 'class' => 'red-link')) ;
				else
					echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Unhide', array('title' => 'Edit Group', 'class' => 'red-link')) ;
				}
				echo " &nbsp;" . anchor('group/delete/' . $this->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete group')), array('class' => "confirmClick", 'title' => "Delete Group")) . "</td>";
			}
			echo "</tr>";
		}
		foreach ($this->children_groups as $id => $data)
		{
			"$this->counter++ ";
			$data->account_st_main($this->counter);
			$this->counter--;
		}
		//} 
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				echo "<tr class=\"tr-ledger\">";
				echo "<td class=\"td-ledger\">";
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td class=\"td-ledger\">";
				echo $this->print_space($this->counter);
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td>Ledger Account</td>";
				echo "<td>" . convert_opening($data['opbalance'], $data['optype']) . "</td>";
				echo "<td>" . convert_amount_dc($data['total']) . "</td>";
				echo "<td class=\"td-actions\">" . anchor('ledger/edit/' . $data['id'], 'Edit', array('title' => "Edit Ledger", 'class' => 'red-link'));
				echo " &nbsp;" . anchor('ledger/delete/' . $data['id'], img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Ledger')), array('class' => "confirmClick", 'title' => "Delete Ledger")) . "</td>";
				echo "</tr>";
			}
			$this->counter--;
		}
	}

	function print_space($count)
	{
		$html = "";
		for ($i = 1; $i <= $count; $i++)
		{
			$html .= "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		return $html;
	}
	
	/* Build a array of groups and ledgers */
	function build_array()
	{
		$item = array(
			'id' => $this->id,
			'name' => $this->name,
			'type' => "G",
			'total' => $this->total,
			'child_groups' => array(),
			'child_ledgers' => array(),
			'depth' => self::$temp_max,
		);
		$local_counter = 0;
		if (count($this->children_groups) > 0)
		{
			self::$temp_max++;
			if (self::$temp_max > self::$max_depth)
				self::$max_depth = self::$temp_max;
			foreach ($this->children_groups as $id => $data)
			{
				$item['child_groups'][$local_counter] = $data->build_array();
				$local_counter++;
			}
			self::$temp_max--;
		}
		$local_counter = 0;
		if (count($this->children_ledgers) > 0)
		{
			self::$temp_max++;
			foreach ($this->children_ledgers as $id => $data)
			{
				$item['child_ledgers'][$local_counter] = array(
					'id' => $data['id'],
					'name' => $data['name'],
					'type' => "L",
					'total' => $data['total'],
					'child_groups' => array(),
					'child_ledgers' => array(),
					'depth' => self::$temp_max,
				);
				$local_counter++;
			}
			self::$temp_max--;
		}
		return $item;
	}

	/* Show array of groups and ledgers as created by build_array() method */
	function show_array($data)
	{
		echo "<tr>";
		echo "<td>";
		echo $this->print_space($data['depth']);
		echo $data['depth'] . "-";
		echo $data['id'];
		echo $data['name'];
		echo $data['type'];
		echo $data['total'];
		if ($data['child_ledgers'])
		{
			foreach ($data['child_ledgers'] as $id => $ledger_data)
			{
				$this->show_array($ledger_data);
			}
		}
		if ($data['child_groups'])
		{
			foreach ($data['child_groups'] as $id => $group_data)
			{
				$this->show_array($group_data);
			}
		}
		echo "</td>";
		echo "</tr>";
	}

	function to_csv($data)
	{
		$counter = 0;
		while ($counter < $data['depth'])
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
		}

		self::$csv_data[self::$csv_row][$counter] = $data['name'];
		$counter++;

		while ($counter < self::$max_depth + 3)
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
		}
		self::$csv_data[self::$csv_row][$counter] = $data['type'];
		$counter++;

		if ($data['total'] == 0)
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = "";
		} else if ($data['total'] < 0) {
			self::$csv_data[self::$csv_row][$counter] = "Cr";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = -$data['total'];
		} else {
			self::$csv_data[self::$csv_row][$counter] = "Dr";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = $data['total'];
		}

		if ($data['child_ledgers'])
		{
			foreach ($data['child_ledgers'] as $id => $ledger_data)
			{
				self::$csv_row++;
				$this->to_csv($ledger_data);
			}
		}
		if ($data['child_groups'])
		{
			foreach ($data['child_groups'] as $id => $group_data)
			{
				self::$csv_row++;
				$this->to_csv($group_data);
			}
		}
	}

	public static function get_csv()
	{
		return self::$csv_data;
	}
	
	public static function add_blank_csv()
	{
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = array("", "");
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = array("", "");
		return;
	}
	
	public static function add_row_csv($row = array(""))
	{
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = $row;
		return;
	}

	public static function reset_max_depth()
	{
		self::$max_depth = 0;
		self::$temp_max = 0;
	}

	/*
	 * Return a array of sub ledgers with the object
	 * Used in CF ledgers of type Assets and Liabilities
	*/
	function get_ledger_ids()
	{
		$ledgers = array();
		if (count($this->children_ledgers) > 0)
		{
			foreach ($this->children_ledgers as $id => $data)
			{
				$ledgers[] = $data['id'];
			}
		}
		if (count($this->children_groups) > 0)
		{
			foreach ($this->children_groups as $id => $data)
			{
				foreach ($data->get_ledger_ids() as $row)
					$ledgers[] = $row;
			}
		}
		return $ledgers;
	}

	
	/* Display print preview of all schedules*/
        function print_all_schedules($c =0)
        {
		$check = 0;
                $this->counter = $c;
		
		$CI =& get_instance();
                $CI->load->model('Setting_model');
                $ledger_name = $CI->Setting_model->get_from_settings('ledger_name');

		if($this->countDigits() == 4 && $this->id != 0 && $this->code > 100){
				
			if($this->name == 'Unrestricted Funds'){
	                        $check++;
                        }else{
                                $check = 0;
                        }

			if($check == 0){
				/* Get Balance of net income/(expenditure) for 'this' ledger head*/
				if($ledger_name == $this->name){
					$income = new Aggregatereportlist();
			                $income->init(3);
			                $expense = new Aggregatereportlist();
			                $expense->init(4);
					$income_total = -$income->total;
			                $old_income_total = -$income->total2;
			                $expense_total = $expense->total;
			                $old_expense_total = $expense->total2;
					$pandl = float_ops($income_total, $expense_total, '-');
				        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
					if ($pandl != 0 || $old_pandl !=0)
				        {
				                if($pandl > 0)
							$this->total = float_ops($this->total, -$pandl, '+');
						else
							$this->total = float_ops($this->total, $pandl, '+');
						if($old_pandl > 0)
				                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
						else
				                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
					}
	                        }
			}

                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
		}elseif($this->countDigits() == 6 && $this->id != 0 && $this->code > 100){
		
			echo "<tr>";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";

			$CI =& get_instance();
                        $CI->db->select('parent_id');
                        $CI->db->from('groups')->where('id', $this->id);
                        $groups_q = $CI->db->get();
                        $groups= $groups_q->row();
                        $parent_id = $groups->parent_id;

                        $CI =& get_instance();
                        $CI->db->select('name');
                        $CI->db->from('groups')->where('id', $parent_id);
                        $groups_q = $CI->db->get();
                        $groups= $groups_q->row();
                        $name = $groups->name;

                        echo "<td class=\"td-group\">";
			if($name  == 'Unrestricted Funds'){
				if($ledger_name == $this->name){
                                        $income = new Aggregatereportlist();
                                        $income->init(3);
                                        $expense = new Aggregatereportlist();
                                        $expense->init(4);
                                        $income_total = -$income->total;
                                        $old_income_total = -$income->total2;
                                        $expense_total = $expense->total;
                                        $old_expense_total = $expense->total2;
                                        $pandl = float_ops($income_total, $expense_total, '-');
                                        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                                        if ($pandl != 0 || $old_pandl !=0)
                                        {
                                                if($pandl > 0)
                                                        $this->total = float_ops($this->total, -$pandl, '+');
                                                else
                                                        $this->total = float_ops($this->total, $pandl, '+');
                                                if($old_pandl > 0)
                                                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                                else
                                                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
                                        }
                        	}
			}
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
		}

		foreach ($this->children_groups as $id => $data)
                {
			$len = $data->countDigits();
                        $this->counter = $data->new_balance_sheet($this->counter);
                }

                return $this->counter;
	}
        
    function balancesheet($id,$accname)
    {
        $diff = $this->income_expense_diff($accname);
        $result1 = explode('#', $diff);
        $diff_total = -($result1[0]);
        $counter = 0;
        $sum = 0;
        $liability_total1 = 0;
        $CI =& get_instance();
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
            $selectrecord = "select name,code,id,parent_id from groups where parent_id=$id";
            $stmt = $dbcon->query($selectrecord);

            if($id == 2)
            {
                //$counter = 0;
                if($stmt != false)
                {
                    foreach ($stmt as $row)
                    {
                        $name = $row['name'];
                        $code = $row['code'];
                        $ledg_id = $row['id'];
                        $liability = new Aggregatereportlist();
                        $liability->init($row['id'],$accname);
                        $liability_total = $liability->total;
                        $sum = $sum + $liability_total;
                        $CI->load->model('investment_model');
                        $result = $CI->investment_model->mergingoffunds($accname);
                        $value = explode('#',$result);
                        $liability_totalA = $value[0];
                        $liability_total1 = ($liability_totalA + $diff_total);
                        //$temp;
                        if($name == 'Corpus')
                            $name = 'Corpus/Capital Funds';
                        if(($code!=  '1005') && ($code!= '1001') &&  ($code!= '1006'))
                        {
                            $counter = $counter+1;
                            if($name!= 'Corpus/Capital Funds')
                            {
                                $temp = $liability_total;
                            }
                            else
                            {
                                $temp = $liability_total1;
                            }
                       // }//ifcode 

                            $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
                            $file_name="";
                            $doc = new DOMDocument();
                            $doc->formatOutput = true;
                            $file_name=$accname."_Liabilty.xml";
                            $tt=$acctpath."/".$file_name;
                            if(file_exists($tt))
                            {
                                $doc->preserveWhiteSpace = false;
                                $doc->load($tt);
                                $Liabilities = $doc->firstChild;
                                $Liability_Name = $doc->createElement('Liability_Name');
    
                                $group_name = $doc->createElement('Group_Name');
                                $textNode = $doc->createTextNode($name);
                                $group_name->appendChild($textNode);
                                $Liability_Name->appendChild($group_name);
    
                                $code_no = $doc->createElement('Code_No');
                                $textNode1 = $doc->createTextNode($counter);
                                $code_no->appendChild($textNode1);
                                $Liability_Name->appendChild($code_no);
    
                                $amount = $doc->createElement('Amount');
                                $textNode2 = $doc->createTextNode($temp);
                                $amount->appendChild($textNode2);
                                $Liability_Name->appendChild($amount);
    
                                $codenu = $doc->createElement('Code_Nu');
                                $textNode3 = $doc->createTextNode($code);
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
                                $group_name->appendChild($doc->createTextNode($name));
                                $b->appendChild( $group_name );
    
                                $code_no = $doc->createElement( "Code_No");
                                $code_no->appendChild($doc->createTextNode($counter));
                                $b->appendChild( $code_no );
    
                                $amount = $doc->createElement( "Amount");
                                $amount->appendChild($doc->createTextNode($temp));
                                $b->appendChild( $amount );

                                $codenu = $doc->createElement('Code_Nu');
                                $textNode3 = $doc->createTextNode($code);
                                $codenu->appendChild($textNode3);
                                $b->appendChild( $codenu );
                                
                                $r->appendChild( $b );

                                $doc->save($tt);
                                $doc->saveXML();
                            }//end of accounts liabilty xml file creation
                        }
                    }
                }//foreach
            }//if(id==2)

            if($id == 1)
            {
                $counter = 3;
                if($stmt != false)
                {
                    foreach ($stmt as $row)
             
                    {
                        $name = $row['name'];
                        $code = $row['code'];
                        $ledg_id = $row['id'];
                        $parent_id = $row['parent_id'];
                        $asset = new Aggregatereportlist();
                        $asset->init($row['id'],$accname);
                        $asset_total = $asset->total;
                        $sum = $sum + $asset_total;

                        $acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
                        $file_name="";

                        $doc = new DOMDocument();
                        $doc->formatOutput = true;

                        $file_name=$accname."_Assets.xml";
                        $tt=$acctpath."/".$file_name;

                        //echo "Total--->".$asset_total;
                        if($name == 'Investments')
                            $name = 'Investments From Earmarked / Endowments Funds';
                        $counter = $counter +1;
                        $temp = $asset_total;

                                        if(file_exists($tt))
                                        {
                                            $doc->preserveWhiteSpace = false;
                                            $doc->load($tt);
                                            $Budgets = $doc->firstChild;
                                            $Assets_Name = $doc->createElement('Assets_Name');
                                            //$Code->setAttribute('id', $row['id']);
                                            $Assets_Name->setAttribute('code', $code);
                                            $Assets_Name->setAttribute('name', $name);
                                            $Assets_Name->setAttribute('schedule', $counter);
                                            $Assets_Name->setAttribute('amount', $temp);


                                            $Budgets->appendChild($Assets_Name);

                                            $ttt=$doc->saveXML();
                                            $handle = fopen($tt, "w");
                                            fwrite($handle, $ttt);
                                            fclose($handle);
                                        }
                                        else
                                        {
                                            $r = $doc->createElement( 'Liabilities' );
                                            $doc->appendChild( $r );

                                            $Assets_Name = $doc->createElement('Assets_Name');
                                            $Assets_Name->setAttribute('code', $code);
                                            $Assets_Name->setAttribute('name', $name);
                                            $Assets_Name->setAttribute('schedule', $counter);
                                            $Assets_Name->setAttribute('amount', $temp);
                                            $r->appendChild($Assets_Name);

                                            $doc->save($tt);
                                            $doc->saveXML();

                                        }//if




                        $selectrecord1 = "select id,name,code from groups where parent_id = $ledg_id";
                        $stmt1 = $dbcon->query($selectrecord1);
                        if($stmt1 != false)    
                        {
                            foreach ($stmt1 as $row1)
                            {
                                $group_name = $row1['name'];
                                $group_id = $row1['id'];
                                $group_code = $row1['code'];
                                $asset = new Aggregatereportlist();
                                $asset->init($row1['id'],$accname);
                                $asset_total = $asset->total;

                                if($name == 'Fixed Assets')
                                {
                                    $group_name;
                                        if(file_exists($tt))
                                        {
                                            $doc->preserveWhiteSpace = false;
                                            $doc->load($tt);
                                            $Budgets = $doc->firstChild;
                                            $Assets_Name = $doc->createElement('Assets_Name');
                                            //$Code->setAttribute('id', $row['id']);
                                            $Assets_Name->setAttribute('code', $group_code);
                                            $Assets_Name->setAttribute('name', $group_name);
                                            $Assets_Name->setAttribute('schedule', $counter);
                                            $Assets_Name->setAttribute('amount', '');


                                            $Budgets->appendChild($Assets_Name);

                                            $ttt=$doc->saveXML();
                                            $handle = fopen($tt, "w");
                                            fwrite($handle, $ttt);
                                            fclose($handle);
                                        }
                                        else
                                        {
                                            $r = $doc->createElement( 'Liabilities' );
                                            $doc->appendChild( $r );

                                            $Assets_Name = $doc->createElement('Assets_Name');
                                            $Assets_Name->setAttribute('code', $group_code);
                                            $Assets_Name->setAttribute('name', $group_name);
                                            $Assets_Name->setAttribute('schedule', $counter);
                                            $Assets_Name->setAttribute('amount', '');
                                            $r->appendChild($Assets_Name);

                                            $doc->save($tt);
                                            $doc->saveXML();

                                        }//if

                                }
                    
                                if(($name!= 'Fixed Assets') && ($name!= 'Current Assets') && ($name!= 'Loans Advances and Deposits'))
                                {
                                    if($group_name == 'Corpus Fund Investments')
                                    {
                                        $group_name = 'Investments Others';
                                        $counter = 6;
                                        $temp = 0;

                                        if(file_exists($tt))
                                        {
                                            $doc->preserveWhiteSpace = false;
                                            $doc->load($tt);
                                            $Budgets = $doc->firstChild;
                                            $Assets_Name = $doc->createElement('Assets_Name');
                                            //$Code->setAttribute('id', $row['id']);
                                            $Assets_Name->setAttribute('code', $code);
                                            $Assets_Name->setAttribute('name', $group_name);
                                            $Assets_Name->setAttribute('schedule', $counter);
                                            $Assets_Name->setAttribute('amount', $temp);


                                            $Budgets->appendChild($Assets_Name);

                                            $ttt=$doc->saveXML();
                                            $handle = fopen($tt, "w");
                                            fwrite($handle, $ttt);
                                            fclose($handle);
                                        }
                                        else
                                        {
                                            $r = $doc->createElement( 'Liabilities' );
                                            $doc->appendChild( $r );

                                            $Assets_Name = $doc->createElement('Assets_Name');
                                            $Assets_Name->setAttribute('code', $code);
                                            $Assets_Name->setAttribute('name', $name);
                                            $Assets_Name->setAttribute('schedule', $counter);
                                            $Assets_Name->setAttribute('amount', '');
                                            $r->appendChild($Assets_Name);

                                            $doc->save($tt);
                                            $doc->saveXML();

                                        }//if
                                    }//if(name!='')
                                }//if Fixed Assets
                            }//for each
                          }//if stmt   
//                        }
//                    }
                   }
                }
            }
        }//try
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }
        $this->curr_total = $sum;
    }

    function income_expense_diff($accname)
    {
        $income = new Aggregatereportlist();
        $income->init('3',$accname);
        $total = $income->total;
        $expense = new Aggregatereportlist();
        $expense->init('4',$accname);
        $total1 = $expense->total;
        $total = 0 - $total;
        $diff = $total - $total1;
        return $diff;
    }

}
