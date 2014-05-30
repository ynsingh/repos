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
	var $optype = "";
	var $opbalance = 0;
	var $consume = 0;
	var $children_groups = array();
	var $children_ledgers = array();
	var $counter = 0;
	var $sum = 0;
	var $main_budget_amount = 0;
	var $budget = array();
	var $projection = array();
	var $consumed_amount = 0;
	var $earned_amount = 0;
	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;

	function Budgetlist()
	{
		return;
	}

	/**
	 * reads data from database,
	 * if id = 0, then data from 'budgets' is read
	 * else, data from 'projections' is read
	 */
	function init($id)
	{
		$CI =& get_instance();
		if ($id == 0)
                {
			$CI->db->from('budgets');
		
			$budget_q = $CI->db->get();
			
			$counter = 0;
			foreach ($budget_q->result() as $row)
			{
				$this->budget[$counter]['id'] = $row->id;
				$this->budget[$counter]['code'] = $row->code;
                        	$this->budget[$counter]['name'] = $row->budgetname;
                        	$this->budget[$counter]['bd_balance'] = $row->bd_balance;
				$this->budget[$counter]['type'] = $row->type;
	                        $this->budget[$counter]['over'] = $row->allowedover;
	                        $this->budget[$counter]['consume'] = $row->consume_amount;
                        	$counter++;
			}//for
		}//if
		/*else {
                	$CI->db->from('budgets')->where('id', $id)->limit(1);
                        $budget_q = $CI->db->get();
                       	$budget_n = $budget_q->row();	
			$this->id = $budget_n->id;
			$this->code = $budget_n->code;
			$this->name = $budget_n->budgetname;
			$this->bd_balance = $budget_n->bd_balance;
			$this->type = $budget_n->type;
			$this->over = $budget_n->allowedover;
			$this->consume = $budget_n->consume_amount;
		}//else*/
		else{
			$CI->db->from('projection');

                        $projection_q = $CI->db->get();

                        $counter = 0;
                        foreach ($projection_q->result() as $row)
                        {
                                $this->projection[$counter]['id'] = $row->id;
                                $this->projection[$counter]['code'] = $row->code;
                                $this->projection[$counter]['name'] = $row->projection_name;
                                $this->projection[$counter]['bd_balance'] = $row->bd_balance;
                                $this->projection[$counter]['type'] = $row->type;
	                        $this->projection[$counter]['earned'] = $row->earned_amount;
                                $counter++;
                        }//for
		}
	}//function

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

	/* Display chart of budgets/projection view */
	//function budget_st_main($c = 0)
	function budget_st_main($c = 0, $account)
	{
		setlocale(LC_MONETARY, 'en_IN');
		$this->counter = $c;

		/*
		 * Displays value for a particular row
		 * in the budgets table.
		 /
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
			echo "<td>";
				echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $this->bd_balance;
			echo "</td>";
			echo "<td>";
				echo $this->print_space($this->counter);
				echo "&nbsp;" .  $this->over;
			echo " </td>";
			echo "<td>";
                                echo $this->print_space($this->counter);
                                echo "&nbsp;" .  $this->consume;
                        echo " </td>";

			if ($this->id <= 4)
			{
				echo "<td class=\"td-actions\"></tr>";
			} else {
				//echo "<td class=\"td-actions\">" . anchor('budget/edit/' . $this->id , "Edit", array('title' => 'Edit Budget', 'class' => 'red-link'));
				$id1=$this->id;
				echo " &nbsp;" . anchor('budget/delete/' . $this->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Budget')), array('class' => "confirmClick", 'title' => "Delete Budget")) . "</td>";
			}
			echo "</tr>";
		}*/

		if($account == 'budget'){
			/*
			 * Displays value for all the rows
			 * in the budgets table.
			 */
			$account_code = 0;
			$main_budget_code = 0;
			if (count($this->budget) > 0)
			{
				$this->counter++;
				foreach ($this->budget as $id => $data)
                		{
					$CI =& get_instance();
					$CI->db->from('groups');
					$CI->db->select('code')->where('name =', 'Expenses');
		                        $groups_q = $CI->db->get();
		                        foreach ($groups_q->result() as $row)
						$account_code = $row->code;

					//get code of 'Main Budget'
					$CI->db->from('budgets');
					$CI->db->select('code')->where('budgetname','Main Budget');
					$main_budget = $CI->db->get();
					foreach($main_budget->result() as $row)
						$main_budget_code = $row->code;	

					//if($data['code'] < 10000 && $data['code'] != '50' &&  $data['code'] != $account_code){
					if($data['code'] < 10000 && $data['code'] != $main_budget_code &&  $data['code'] != $account_code){
						$this->sum = $this->sum + $data['bd_balance'];
						$this->consumed_amount = $this->consumed_amount + $data['consume'];
					}
                        
					//if($data['code'] == '50')
					if($data['code'] == $main_budget_code)
					{
						$this->main_budget_amount = $data['bd_balance'];
					}
	
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
	                                echo "&nbsp;" .  money_format('%!i', $data['bd_balance']);
        	                	echo "</td>";
                		       	echo "<td>";
                                	echo $this->print_space($this->counter);
	                                echo "&nbsp;" .  $data['over'];
        	                	echo " </td>";
					$available_amount=$data['bd_balance'] - $data['consume'];
	
					echo "<td>";
					echo $this->print_space($this->counter);
                        	        echo "&nbsp;" .  money_format('%!i',$available_amount);
                                	echo " </td>";

					//if(!($data['code'] == '50'))
					if(!($data['code'] == $main_budget_code))
					{
                		                	echo "<td class=\"td-actions\">" . anchor('budget/edit/' . $data['id'] , "Edit", array('title' => 'Edit Budget', 'class' => 'red-link'));
					}
					//echo " &nbsp;" . anchor('budget/delete/' . $data['id'], img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Budget')), array('class' => "confirmClick", 'title' => "Delete Budget")) . "</td>";
        	                echo "</tr>";
				
                		}
				$this->counter--;

				echo "<tr>";
					/*
						Its the sum of amount allocated to child budgets
					 */ 
					echo "<td>";
        	        		        echo "&nbsp;<strong>" . "Allocated Amount" .  "</strong>";
                	        	        echo $this->print_space($this->counter);
                                        	echo "&nbsp;" .  money_format('%!i', $this->sum);
					echo " </td>";
	
					echo "<td>";
					echo "</td>";

					/*
						Its the difference between the bd_balance of Main Budget
						and the sum of amount allocated to its child budgets
					 */
			              	echo "<td align=\"right\">";
                	                        echo "&nbsp;<strong>" . "Unallocated Amount" .  "</strong>";
                        	                echo $this->print_space($this->counter);
						$temp = $this->main_budget_amount - $this->sum;
                                        	echo "&nbsp;" .  money_format('%!i', $temp);
	                                echo " </td>";
        	                       
					echo "<td>";
					echo "</td>";
	
					/*
						Its the sum of consumed_amount of child budgets
					 */
					echo "<td align=\"right\">";
                        	                echo "&nbsp;<strong>" . "Consumed Amount" .  "</strong>";
                                	        echo $this->print_space($this->counter);
                                        	echo "&nbsp;" .  money_format('%!i', $this->consumed_amount);
	                                echo " </td>";
	
					echo "<td>";
                	                echo "</td>";
					
					echo "<td>";
	                                echo "</td>";
				echo "</tr>";	
			}
		}//if account == 'budget'
		else{
			$target_projection_code = 0;
			if (count($this->projection) > 0)
			{
				$this->counter++;
				foreach ($this->projection as $id => $data)
                		{
					$CI =& get_instance();
					$CI->db->from('groups');
                                        $CI->db->select('code')->where('name', 'Incomes');
                                        $groups_q = $CI->db->get();
                                        foreach ($groups_q->result() as $row)
                                                $account_code = $row->code;

					/*if($data['code'] < 10000 && $data['code'] != $account_code){
						$this->sum = $this->sum + $data['bd_balance'];
						$this->earned_amount = $this->earned_amount + $data['earned'];
					}
                        
					if($data['code'] == $account_code)
					{
						$this->main_projection_amount = $data['bd_balance'];
					}*/

					$CI->db->from('projection');
					$CI->db->select('code')->where('projection_name', 'Target Projection');
					$target_proj = $CI->db->get();
					foreach($target_proj->result() as $row)
						$target_projection_code = $row->code;

					//if($data['code'] == '60')
					if($data['code'] == $target_projection_code)
                                        {
                                                $this->earned_amount = $data['earned'];
						$this->main_projection_amount = $data['bd_balance'];
                                        }

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
	                                echo "&nbsp;" .  money_format('%!i', $data['bd_balance']);
        	                	echo "</td>";
					$unearned_amount=$data['bd_balance'] - $data['earned'];
	
					echo "<td>";
					echo $this->print_space($this->counter);
                        	        echo "&nbsp;" .  money_format('%!i', $unearned_amount);
                                	echo " </td>";

                		        //echo "<td class=\"td-actions\">" . anchor('projection/edit/' . $data['id'] , "Edit", array('title' => 'Edit Projection', 'class' => 'red-link'));
					//echo " &nbsp;" . anchor('budget/delete/' . $data['id'], img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Budget')), array('class' => "confirmClick", 'title' => "Delete Budget")) . "</td>";
        	                echo "</tr>";
				
                		}
				$this->counter--;

				echo "<tr>";
 		                        /*
						Its the sum of bd_balance of child projections
						i.e., projections with code length '4'
					 */
					echo "<td>";
        	        		        echo "&nbsp;<strong>" . "Target Projection" .  "</strong>";
                	        	        echo $this->print_space($this->counter);
                                        	//echo "&nbsp;" .  $this->sum;
                                        	echo "&nbsp;" .  money_format('%!i', $this->main_projection_amount);
					echo " </td>";
	
					echo "<td>";
					echo "</td>";

					/*
						Its the sum of earned_amount of child projections
						i.e., projections with code length '4'
					 */
				        echo "<td align=\"right\">";
					        echo "&nbsp;<strong>" . "Achieved Projection" .  "</strong>";
                        	                echo $this->print_space($this->counter);
                                        	echo "&nbsp;" .  money_format('%!i', $this->earned_amount);
	                                echo " </td>";
        	                       
					echo "<td>";
					echo "</td>";
	
					/*
                                                Its the difference between the
						sum of bd_balance of child projections
						sum of earned_amount of child projections
                                                i.e., projections with code length '4'
                                         */
					echo "<td align=\"right\">";
                        	                echo "&nbsp;<strong>" . "Unachieved Projection" .  "</strong>";
                                	        echo $this->print_space($this->counter);
						//$temp = $this->sum - $this->earned_amount;
						$temp = $this->main_projection_amount - $this->earned_amount;
                                        	echo "&nbsp;" .  money_format('%!i', $temp);
	                                echo " </td>";
	
					echo "<td>";
                	                echo "</td>";
					
					echo "<td>";
	                                echo "</td>";
				echo "</tr>";	
			}
		}//else $account == 'projection'
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

}

