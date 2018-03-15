<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Accountlist
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
	var $old_dr_total = 0;
	var $old_cr_total = 0;

	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;

	function __construct()
	{
		return;
	}

	function init($id)
	{
		$CI =& get_instance();
		if ($id == 0)
		{
			$this->id = 0;
			$this->name = "None";
			$this->total = 0;

		}

		 else {
			$CI->db->from('groups')->where('id', $id)->limit(1);
			$group_q = $CI->db->get();
			$group = $group_q->row();
			$this->id = $group->id;
			$this->name = $group->name;
			$this->code = $group->code;
			$this->status = $group->status;
			$this->total = 0;
			//$this->schedule = $group->schedule;
		}
		if($this->status==0)
		{
				$this->add_sub_groups();
				$this->add_sub_ledgers();
		}
	}

	function add_sub_groups()
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

	/* Display new Balance Sheet*/
	function new_balance_sheet($c =0)
        {
                $this->counter = $c;
                if ($this->id != 0 && $this->code > 100 )
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
			//echo $this->print_space($this->counter);
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";
			echo "<td class=\"td-group\">";
                        //echo "&nbsp;" .  $this->schedule;
			echo "&nbsp;" . $this->counter;
			//echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . 0 . "</td>";
                        echo "</tr>";
                }
                foreach ($this->children_groups as $id => $data)
                {
			$len = $data->countDigits();
			if($len == 4){
                        	$this->counter++;
                        	$data->new_balance_sheet($this->counter);
			}
                        //$this->counter--;
                }
		$this->counter = $this->counter + 1;
		return $this->counter;
        }

	function countDigits()
        {
                //preg_match_all( "/[0-9]/", $str, $arr );
                $search = '1234567890';
                $count = strlen($this->code) - strlen(str_replace(str_split($search), '', $this->code));
                return $count;
        }

	/* Displays schedule */
	/*function schedule($c = 0)
	//function schedule()
        { 

		// total will have opening balance
		//$total = 0;
		$len = $this->countDigits();
                if ($this->id != 0  && $len > 4)
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        //echo $this->print_space($this->counter);
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";
                        //echo "<td align=\"right\">" . convert_amount_dc($this->total) . $this->print_space($this->counter) . "</td>";

			echo "<td>";
                        echo "</td>";
			
			echo "<td>";
	                echo "</td>";

                	echo "<td>";
        	        echo "</td>";
			echo "<td>";
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
                        //$this->counter++;
                        foreach ($this->children_ledgers as $id => $data)
                        {
				$CI =& get_instance();
                        	$CI->db->select('entry_id, id, amount, dc');
	                        $CI->db->from('entry_items')->where('ledger_id', $data['id']);
        	                $entry_items_q = $CI->db->get();
				if($entry_items_q->num_rows() > 0)
				{
					$entry_items_result = $entry_items_q->result();
					echo "<tr class=\"tr-ledger\">";
					echo "<td class=\"td-ledger\">";
					echo $this->print_space($this->counter);
					echo "&nbsp;" . $data['name'];			
					echo "</td>";

					echo "<td>";
		                        echo "</td>";
		
                		        echo "<td>";
	        	                echo "</td>";
		
		                        echo "<td>";
                		        echo "</td>";

		                        echo "<td>";
        		                echo "</td>";
	
                        		echo "</tr>";
	
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
							
								echo "<tr class=\"tr-ledger\">";
		                	                        echo "<td class=\"td-ledger\">";
        			                                echo $this->print_space($this->counter);
                        	        	        	echo "&nbsp;" . "Add: " . $narration;
	                        	                	echo "</td>";
	
								echo "<td>";
				                                echo "</td>";
			
               	        				        echo "<td>";
								echo convert_amount_dc($row->amount);
			        	                        echo "</td>";
	
							        echo "<td>";
								echo "0";
        				                        echo "</td>";
	
								echo "<td>";
                                                	        echo "0";
                                                        	echo "</td>";

				                                echo "</tr>";
							}
							$this->cr_total = $this->cr_total + $row->amount;
						}
						else{
							$CI =& get_instance();
                                                        $CI->db->select('narration');
                                                        $CI->db->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $CI->db->get();
                                                        //$entries = $entries_q->row();
							foreach($entries_q->result() as $entries){
	                                                        $narration = $entries->narration;

        	                                                echo "<tr class=\"tr-ledger\">";
                	                                        echo "<td class=\"td-ledger\">";
                        	                                echo $this->print_space($this->counter);
                                	                        echo "&nbsp;" . "Deduct: " . $narration;
                                        	                echo "</td>";
                                                                
                                                	        echo "<td>"; 
								echo $row->amount;
	                                                        echo "</td>";
        	        
                	                                        echo "<td>"; 
                        	                                echo "</td>";
	
        	                                                echo "<td>";
                	                                        echo "0";
                        	                                echo "</td>";
        
                                	                        echo "<td>";
                                        	                echo "0";
                                                	        echo "</td>";

                                                        	echo "</tr>";
							}
                                                        $this->dr_total = $this->dr_total - $row->amount;							
						}
                	        	}

				}
                        }
                        //$this->counter--;
                }
		
		//return $total;
        }

	function previous_year_data()
	{
		foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        $data->schedule($this->counter);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        //$this->counter++;
                        foreach ($this->children_ledgers as $id => $data)
                        {

				$CI =   &get_instance();
		                $db1=$CI->load->database('bgasNew', TRUE);
                		$db1->select('entry_id, id, amount, dc');
		                $db1->from('entry_items')->where('ledger_id', $data['id']);
                		$entry_items_q = $db1->get();
                                if($entry_items_q->num_rows() > 0)
                                {
                                        $entry_items_result = $entry_items_q->result();
                                        /*echo "<tr class=\"tr-ledger\">";
                                        echo "<td class=\"td-ledger\">";
                                        echo $this->print_space($this->counter);
                                        echo "&nbsp;" . $data['name'];
                                        echo "</td>";

                                        echo "<td>";
                                        echo "</td>";

                                        echo "<td>";
                                        echo "</td>";

                                        echo "<td>";
                                        echo "</td>";

                                        echo "<td>";
                                        echo "</td>";

                                        echo "</tr>";*/
                                        /*foreach ($entry_items_result as $row)
                                        {
                                                if($row->dc == 'C'){
                                                        //$CI =& get_instance();
                                                        $db1->select('narration');
                                                        $db1->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $db1->get();
                                                        //$entries = $entries_q->row();
                                                        foreach($entries_q->result() as $entries){
                                                                $narration = $entries->narration;

                                                                echo "<tr class=\"tr-ledger\">";
                                                                echo "<td class=\"td-ledger\">";
                                                                echo $this->print_space($this->counter);
                                                                echo "&nbsp;" . "Add: " . $narration;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "</td>";

                                                                echo "<td>";
                                                                //echo convert_amount_dc($row->amount);
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "0";
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo convert_amount_dc($row->amount);
                                                                echo "</td>";

                                                                echo "</tr>";
                                                        }
                                                        $this->old_cr_total = $this->old_cr_total + $row->amount;
                                                }
                                                else{
                                                        //$CI =& get_instance();
                                                        $db1->select('narration');
                                                        $db1->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $db1->get();
                                                        //$entries = $entries_q->row();
                                                        foreach($entries_q->result() as $entries){
                                                                $narration = $entries->narration;

                                                                echo "<tr class=\"tr-ledger\">";
                                                                echo "<td class=\"td-ledger\">";
                                                                echo $this->print_space($this->counter);
                                                                echo "&nbsp;" . "Deduct: " . $narration;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                //echo $row->amount;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo convert_amount_dc($row->amount);
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "0";
                                                                echo "</td>";

                                                                echo "</tr>";
                                                        }
                                                        $this->old_dr_total = $this->old_dr_total - $row->amount;                                               
                                                }
                                        }

                                }
                        }
		}
	}*/

	/* Display chart of accounts view */
	function account_st_main($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			//$this->messages->add('Value of status field==>');
			//echo $this->status;
			//echo "Test";	
			if ($this->id <= 4)
				{
					echo "&nbsp;<strong>" .  $this->code . "</strong>";
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			else
				{echo "&nbsp;" .  $this->code;
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			echo "</td>";
			$CI = & get_instance();
			$CI->load->model('group_model');
			$description = $CI->group_model->get_group_description($this->code);
			echo "<td class=\"td-group\">";
			echo $this->print_space($this->counter);
			
			if ($this->id <=4){
				//echo "&nbsp;<strong>" .  $this->name . "</strong>";
				echo "&nbsp" . anchor('group/edit/' . $this->id,$this->name, array('title' => $description, 'style' => 'color:#000000; text-decoration:none; cursor:auto;font-weight:bold; '));
			}
			else{
				//echo "&nbsp;" .  $this->name;
				echo "&nbsp" . anchor('group/edit/' . $this->id,$this->name, array('title' => $description, 'style' => 'color:#000000; text-decoration:none; cursor:auto; '));
			}
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
			//	echo " &nbsp;" . anchor('group/delete/' . $this->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete group')), array('class' => "confirmClick", 'title' => "Delete Group")) . "</td>";
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
				$i=0;
				$CI = & get_instance();
				$CI->load->model('ledger_model');
				$description = $CI->ledger_model->get_ledger_description($data['code']);
				echo "<tr class=\"tr-ledger\">";
				echo "<td class=\"td-ledger\">";
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td class=\"td-ledger\">";
				echo $this->print_space($this->counter);
				echo "&nbsp&nbsp&nbsp&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $description, 'style' => 'color:#000000'));	
				echo "</td>";
				echo "<td>Ledger Account</td>";
				echo "<td>" . convert_opening($data['opbalance'], $data['optype']) . "</td>";
				echo "<td>" . convert_amount_dc($data['total']) . "</td>";
				echo "<td class=\"td-actions\">" . anchor('ledger/edit/' . $data['id'], 'Edit', array('title' => "Edit Ledger", 'class' => 'red-link'));
				echo " &nbsp;" . anchor('ledger/delete/' . $data['id'], img(array('src' => asset_url() . "assets/bgas/images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Ledger')), array('class' => "confirmClick", 'title' => "Delete Ledger")) . "</td>";
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
}

