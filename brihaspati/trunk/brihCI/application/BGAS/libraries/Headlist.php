<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Headlist
{
	var $id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $total = 0;
	var $optype = "";
	var $opbalance = 0;
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
	public static $new_link;

	function __construct()
	{
		//$CI->messages->add('Account Name in Head List==>'.$accountname);
		return;
	}
	function init($accountname,$user_name,$id)
	{
		$this->user_name=$user_name;
                $CI =& get_instance();
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accountname);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $databasename= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
                $new_link = @mysqli_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                if ($new_link)
                {
                        $db_selected = mysqli_select_db($new_link,$databasename);
                        if ($db_selected) {

                        }
                }

		$this->accountname=$accountname;
		if ($id == 0)
                {
                        $this->id = 0;
                        $this->name = "None";
                        $this->total = 0;

                }
		else{
			$query = sprintf("SELECT * from groups where id=$id limit 1");
			$result = mysqli_query($new_link,$query);	
			if (!$result) {
				$message  = 'Invalid query: ' . mysqli_error() . "\n";
				$message .= 'Whole query: ' . $query;
				die($message);
			}
			while ($row = mysqli_fetch_assoc($result)) {
				
				$id=$row['id'];
				$this->id=$row['id'];
    			    	$this->name=$row['name'];
    			    	$this->code=$row['code'];

			   
			}
	
		}
		$this->add_sub_groups($accountname);
		$this->add_sub_ledgers();
	}

	function add_sub_groups($accountname)
	{
		$CI =& get_instance();
		$query1 = sprintf("SELECT * from groups where parent_id=$this->id");

		$result1 = mysqli_query($new_link,$query1);
                        if (!$result1) {
				
                                $message  = 'Invalid query: ' . mysqli_error() . "\n";
                                $message .= 'Whole query: ' . $query1;
                                die($message);
                        }

		$counter = 0;
		while($row = mysqli_fetch_assoc($result1)) {
	                $id=$row['id'];
			$this->children_groups[$counter] = new Headlist();
				$this->children_groups[$counter]->init($accountname,$this->user_name,$id);
			 $counter++;
		//echo "<br>";
		}

	}

	function add_sub_ledgers()
	{
		$CI =& get_instance();
		$counter=0;
		$query1 = sprintf("SELECT * from ledgers where group_id=$this->id");
                $result1 = mysqli_query($new_link,$query1);
		while($row = mysqli_fetch_assoc($result1)) {
			$this->children_ledgers[$counter]['id'] = $row['id'];
                        $this->children_ledgers[$counter]['code'] = $row['code'];
                        $this->children_ledgers[$counter]['name'] = $row['name'];
			$counter++;
		}
	}



	/* Display all  accounts heads */
	function account_st_main($c = 0)
	{
		$this->counter = $c;
		echo form_open('admin/user/addpermission');
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			/*echo "<td class=\"td-group\">";
			echo form_checkbox();
			echo "</td>";*/
			echo "<td class=\"td-group\">";
			if ($this->id <= 4)
			{
				echo "&nbsp;<strong>" .  $this->code . "</strong>";
			}
			else
			{
				echo "&nbsp;" .  $this->code;
			}
			echo "</td>";
			echo "<td class=\"td-group\">";
			echo $this->print_space($this->counter);
			if ($this->id <=4)
				echo "&nbsp;<strong>" .  $this->name. "</strong>";
			else
				echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td class=\"td-group\">";
			$htype = "grp";
			//echo $this->user_name;
			//echo $this->id;
			$query1 = sprintf("SELECT * from bgas_acl where username='$this->user_name' and headid='$this->id'");
			$result1 = mysqli_query($new_link,$query1);
			$perm = mysqli_num_rows($result1);
			//echo $row = mysql_fetch_assoc($result1);
		
			$remove="Remove";	
			//echo " &nbsp;" . anchor('admin/user/addpermission/' .$this->user_name."/".$this->id."/".$this->name."/".$this->accountname."/".$htype."/" , 'Assign Permission',array('title' => 'Edit Group', 'class' => 'red-link'));
			if($perm == "1")
				echo " &nbsp;" . anchor('admin/user/removepermission/' .$this->user_name."/".$this->id."/".$this->accountname."/".$this->code."/" , 'Remove Permission',array('title' => 'Edit Group', 'class' => 'blue-link'));		
			else
				echo " &nbsp;" . anchor('admin/user/addpermission/' .$this->user_name."/".$this->id."/".$this->accountname."/".$htype."/" , 'Assign Permission',array('title' => 'Edit Group', 'class' => 'red-link'));
						echo "</td>";
			echo "</tr>";
		}
		foreach ($this->children_groups as $id => $data)
		{
			"$this->counter++ ";
			$data->account_st_main($this->counter);
			$this->counter--;
		}
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				echo "<tr class=\"tr-ledger\">";
				
				echo "<td class=\"td-ledger\">";
				//echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "&nbsp;".$data['code'];
				echo "</td>";
				echo "<td class=\"td-ledger\">";
				echo $this->print_space($this->counter);
				//echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "&nbsp;".$data['name'];
				echo "</td>";
				echo "<td class=\"td-ledger\">";
				echo "</td>";
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
}

