<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Aggregatecoflist
{
	var $id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $total = 0;
	var $optype = "";
	var $opbalance = 0;
	var $schedule = "";
	var $children_groups = array();
	var $children_ledgers = array();
	var $counter = 0;
	var $dr_total = 0;
	var $cr_total = 0;

	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;
	
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


		if ($id == 0)
		{
			$this->id = 0;
			$this->name = "None";
			$this->total = 0;

		}
		else {
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
					}
				}	
			}
	                catch(PDOException $e)
        	        {
                	        echo $e->getMessage();
                	}

		}
		$dbcon = null;
		$this->add_sub_groups($accname);
		$this->add_sub_ledgers($accname);
	}

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
		                        $this->children_groups[$counter] = new Aggregatecoflist();
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
		$dbcon = null;
	}

	function add_sub_ledgers($accname)
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

                        if($stmt != false)
                        {
                                $counter = 0;
                                foreach ($stmt as $row)
                                {
		                        $this->children_ledgers[$counter]['id'] = $row['id'];
                		        $this->children_ledgers[$counter]['code'] = $row['code'];
		                        $this->children_ledgers[$counter]['name'] = $row['name'];
                		        $this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_ledger_balance_agg($row['id'],$accname);
		                        list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance_agg($row['id'],$accname);
//                		        $this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');
                        		$counter++;


                                }
                        }
                }
                catch(PDOException $e)
                {
                        $e->getMessage();
                }

	}

	/* Actual code of chart of account*/
	function account_st_main($c = 0,$accname)
	{
		//creation of xml file
        	$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');
                $file_name1=$accname."_cof.xml";
                $tt=$acctpath."/".$file_name1;
                $doc = new DOMDocument();
                $doc->formatOutput = true;

		$this->counter = $c;
		if ($this->id != 0)
		{
			$this->code;
			$CI = & get_instance();
			$CI->load->model('group_model');
			$description = $CI->group_model->get_group_description_agg($this->code, $accname);
			$this->print_space($this->counter);
				$id1=$this->id;
			//code for creating chart of account xml file of account

                        //append node with attribute in accounts xml file if file already exist
                        if(file_exists($tt))
                        {
				$this->name=htmlspecialchars($this->name, ENT_QUOTES);
	                        $doc->preserveWhiteSpace = false;
                                $doc->load($tt);
                                $Budgets = $doc->firstChild;
                                $Code = $doc->createElement('Code');
                                $Code->setAttribute('id', $this->code);
                                $Code->setAttribute('accname', $this->name);
                                $Code->setAttribute('type', 'Group Account');
                                $Code->setAttribute('op_balance', '-');
                                $Code->setAttribute('cl_balance', '-');
                                $Code->setAttribute('Group_id', $this->id);

                                $Budgets->appendChild($Code);

                                $ttt=$doc->saveXML();
                                $handle = fopen($tt, "w");
                                fwrite($handle, $ttt);
                                fclose($handle);

			}
                        //create account xml file and insert node and attribute in xml .
                       else
                       {
				$this->name=htmlspecialchars($this->name, ENT_QUOTES);

	                       $r = $doc->createElement( 'Accounts' );
                               $doc->appendChild( $r );

                               $Code = $doc->createElement('Code');
                               $Code->setAttribute('id', $this->code);
                               $Code->setAttribute('accname', $this->name);
                               $Code->setAttribute('type', 'Group Account');
                               $Code->setAttribute('op_balance', '-');
                               $Code->setAttribute('cl_balance', '-');
                               $Code->setAttribute('Group_id', $this->id);

                               $r->appendChild($Code);

                               $doc->save($tt);
                               $doc->saveXML();
			}

		}



		foreach ($this->children_groups as $id => $data)
		{
			$this->counter++ ;
			$data->account_st_main($this->counter, $accname);
			$this->counter--;
		}
		//} 
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				$i=0;
				$CI = & get_instance();
				$CI->load->model('ledger_model');
				$description = $CI->ledger_model->get_ledger_description_agg($data['code'], $accname);
				$this->print_space($this->counter);
				$opb = convert_opening($data['opbalance'], $data['optype']);
 				$clb =convert_amount_dc($data['total']);
                        	if(file_exists($tt))
                        	{
				$data['name']=htmlspecialchars($data['name'], ENT_QUOTES);
                                $doc->preserveWhiteSpace = false;
                                $doc->load($tt);
                                $Budgets = $doc->firstChild;
                                $Code = $doc->createElement('Code');
                                $Code->setAttribute('id', $data['code']);
                                $Code->setAttribute('accname', $data['name']);
                                $Code->setAttribute('type', 'Ledger Account');
                                $Code->setAttribute('op_balance', $opb);
                                $Code->setAttribute('cl_balance', $clb);
                                $Code->setAttribute('Group_id', $data['id']);

                                $Budgets->appendChild($Code);

                                $ttt=$doc->saveXML();
                                $handle = fopen($tt, "w");
                                fwrite($handle, $ttt);
                                fclose($handle);
				}

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
		//echo $data['total'];
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

	/*
	 * Return a array of sub ledgers with the object
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
}

