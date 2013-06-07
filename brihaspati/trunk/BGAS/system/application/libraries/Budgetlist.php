<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Budgetlist
{
	var $id = 0;
	var $name = "";
	var $type = "";
	var $over = 0;
	var $code = "";
	var $status = "";
	var $total = 0;
	var $bd_balance = 0.00;
	var $op_balance = "";
	var $optype = "";
	var $opbalance = 0;
	var $children_groups = array();
	var $children_ledgers = array();
	var $counter = 0;
	var $budget = array();
	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;

	function Budgetlist()
	{
		return;
	}

	function init($id)
	{
		$CI =& get_instance();
/*		if ($id == 0)
		{
			$this->id = 0;
			$this->name = "None";
			$this->total = 0;

		} else {*/
		if ($id == 0)
                {
			//$CI->db->from('budgets')->where('id', 24)->limit(1);
			$CI->db->from('budgets');
//			$this->id = 0;
  //                      $this->name = "None";
    //                    $this->total = 0;
		//	$CI->db->from('budgets')->where('id', $this->id);
		
			$budget_q = $CI->db->get();
			
			$counter = 0;
			foreach ($budget_q->result() as $row)
			{
		//		$this->budget[$counter] = new  Budgetlist();
		//		$this->budget[$counter]->init($row->id);
				$this->budget[$counter]['id'] = $row->id;
				$this->budget[$counter]['code'] = $row->code;
                        	$this->budget[$counter]['name'] = $row->budgetname;
                        	$this->budget[$counter]['bd_balance'] = $row->bd_balance;
                        	$this->budget[$counter]['op_balance'] = $row->op_balance_dc;
				$this->budget[$counter]['type'] = $row->type;
	                        $this->budget[$counter]['over'] = $row->allowedover;
                       		// list ($this->budget[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
                 //       	$this->total = float_ops($this->total, $this->budget[$counter]->total, '+');
                        	$counter++;
			}//for
		}//if
		else {
                	$CI->db->from('budgets')->where('id', $id)->limit(1);
                        $budget_q = $CI->db->get();
                       	$budget_n = $budget_q->row();	
			$this->id = $budget_n->id;
			$this->code = $budget_n->code;
			$this->name = $budget_n->budgetname;
			$this->bd_balance = $budget_n->bd_balance;
			$this->op_balance = $budget_n->op_balance_dc;
			$this->type = $budget_n->type;
			$this->over = $budget_n->allowedover;
			//$this->status = $group->status ;
			//	$this->total = 0;
		}//else

//		if($this->status==0)
//		{
//			$this->add_sub_groups();
//			$this->add_sub_ledgers();
//		}
	}//function

/*	function add_sub_groups()
	{
		$CI =& get_instance();
		$CI->db->from('groups')->where('parent_id', $this->id);
		$child_group_q = $CI->db->get();
		$counter = 0;
		foreach ($child_group_q->result() as $row)
		{
			$this->children_groups[$counter] = new Accountlist();
			$this->children_groups[$counter]->init($row->id);
			$this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');
			$counter++;
		}
	}
	function add_sub_ledgers()
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
			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');
			$counter++;
		}
	}
*/
	/* Display Account list in Balance sheet and Profit and Loss st */
	function account_st_short($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			echo $this->print_space($this->counter);
			echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td align=\"right\">" . convert_amount_dc($this->total) . $this->print_space($this->counter) . "</td>";
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
				echo $this->print_space($this->counter);
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td align=\"right\">" . convert_amount_dc($data['total']) . $this->print_space($this->counter) . "</td>";
				echo "</tr>";
			}
			$this->counter--;
		}
	}

	/* Display chart of accounts view */
	function budget_st_main($c = 0)
	{
		$this->counter = $c;

		/*
		 * Displays value for a particular row
		 * in the budgets table.
		 */
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			//$this->messages->add('Value of status field==>');
			//echo $this->status;
			//echo "Test";	
			if ($this->id <= 4)
				{echo "&nbsp;<strong>" .  $this->code . "</strong>";
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			else
				{echo "&nbsp;" .  $this->code;
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
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
			echo "<td>";
				echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $this->bd_balance;
			echo "</td>";
			echo "<td>";
				echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $this->op_balance;
			echo "</td>";
			echo "<td>";
				echo $this->print_space($this->counter);
				echo "&nbsp;" .  $this->over;
			echo " </td>";
			if ($this->id <= 4)
			{
				echo "<td class=\"td-actions\"></tr>";
			} else {
				echo "<td class=\"td-actions\">" . anchor('budget/edit/' . $this->id , "Edit", array('title' => 'Edit Budget', 'class' => 'red-link'));
				$id1=$this->id;
	//			$status1=$this->status;
	//			if (  check_access('administer'))
	//			{
	//			if($this->status == 0)				
	//				echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Hide',  array('title' => 'Edit Group', 'class' => 'red-link')) ;
	//			else
	//				echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Unhide', array('title' => 'Edit Group', 'class' => 'red-link')) ;
	//			}
				echo " &nbsp;" . anchor('budget/delete/' . $this->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Budget')), array('class' => "confirmClick", 'title' => "Delete Budget")) . "</td>";
			}
			echo "</tr>";
		}
		/*foreach ($this->children_groups as $id => $data)
		{
			"$this->counter++ ";
			$data->budget_st_main($this->counter);
			$this->counter--;
		}*/
		//} 
		/*if (count($this->children_ledgers) > 0)
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
		}*/

		/*
		 * Displays value for all the rows
		 * in the budgets table.
		 */
		if (count($this->budget) > 0)
		{
			$this->counter++;
			foreach ($this->budget as $id => $data)
                	{
                        	//"$this->counter++ ";
				echo "<tr class=\"tr-ledger\">";
                                echo "<td class=\"td-ledger\">";
					echo "&nbsp;" .  $data['code'];
                                //echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
                        	echo "</td>";
                        	echo "<td class=\"td-group\">";
                        	echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $data['name'];
                        	echo "</td>";
                        	//echo "<td>Group Account</td>";
				echo "<td>";
                                echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $data['type'];
                                echo "</td>";
                        	echo "<td>";
                                echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $data['bd_balance'];
                        	echo "</td>";
                        	echo "<td>";
                                echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $data['op_balance'];
                        	echo "</td>";
                        	echo "<td>";
                                echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $data['over'];
                        	echo " </td>";
                     //   	if ($data['id'] <= 4)
                       // 	{
                         //       	echo "<td class=\"td-actions\"></tr>";
                        //	} else {
                                echo "<td class=\"td-actions\">" . anchor('budget/edit/' . $data['id'] , "Edit", array('title' => 'Edit Budget', 'class' => 'red-link'));
				echo " &nbsp;" . anchor('budget/delete/' . $data['id'], img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Budget')), array('class' => "confirmClick", 'title' => "Delete Budget")) . "</td>";
                        //	}
                        echo "</tr>";

				/*echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
				
                                echo "</td>";
                                echo "<td class=\"td-ledger\">";
                                echo $this->print_space($this->counter);
                                echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
                                echo "<td>Ledger Account</td>";
                                echo "<td>" . convert_opening($data['opbalance'], $data['optype']) . "</td>";
                                echo "<td>" . convert_amount_dc($data['total']) . "</td>";
                                echo "<td class=\"td-actions\">" . anchor('budget/edit/' . $data['id'], 'Edit', array('title' => "Edit Budget", 'class' => 'red-link'));
                                echo " &nbsp;" . anchor('budget/delete/' . $data['id'], img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Budget')), array('class' => "confirmClick", 'title' => "Delete Budget")) . "</td>";
                                echo "</tr>";*/
                        	//$data->budget_st_main($this->counter);
                        	//$this->counter--;
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

	/*
	 * Displays budget names along with 
	 * budget distribution from budgets table.
	 */
	/*function budget_allocation()
	{
		$CI =& get_instance();
		$CI->db->from('budgets');
		$CI->db->select('id, budgetname, bd_balance');
		$budget_q = $CI->db->get();
                //$budget = $budget_q->row();
		$counter = 0;
                foreach ($budget_q->result() as $row)
                {
                                $this->budget[$counter]['id'] = $row->id;
                                $this->budget[$counter]['name'] = $row->budgetname;
                                //$this->budget[$counter]['bd_balance'] = $row->bd_balance;
                                $counter++;
                }//for

		if (count($this->budget) > 0)
                {
                        $this->counter++;
                        foreach ($this->budget as $id => $data)
                        {
                                //"$this->counter++ ";
                                echo "<tr class=\"tr-ledger\">";
                                
				echo "<td class=\"td-ledger\">";
        			echo "<span id=\"tooltip-target-1\">";
        			echo form_checkbox('sub_budget', 1, FALSE) . "";
        			echo "</span>";
        			echo "<span id=\"tooltip-content-1\">Select to display list of sub budgets in this budget.</span>";
       	 			
				//echo "<td class=\"td-ledger\">";
                                	echo "&nbsp;" .  $data['name'];
                                echo "</td>";

                                echo "<td class=\"td-group\">";
	                                echo $this->print_space($this->counter);
					echo form_input('budget_distribution', '');
        	                        //echo "&nbsp;" .  $data['bd_balance'];
                                echo "</td>";

				echo "</tr>";
			}//for
                        $this->counter--;
                }//if
	}//function*/
}

