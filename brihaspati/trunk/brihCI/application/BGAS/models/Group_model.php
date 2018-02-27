<?php

class Group_model extends CI_Model {

function __construct() {
        parent::__construct();
    }

	function Group_model()
	{
		parent::Model();
	}


	function get_all_groups($id = NULL)
	{
		$options = array();
		if ($id == NULL)
			$this->db->from('groups')->where('id >', 0)->order_by('name', 'asc');
		else
			$this->db->from('groups')->where('id >', 0)->where('id !=', $id)->order_by('name', 'asc');
		$group_parent_q = $this->db->get();

		$options[0] = "Please Select";

		foreach ($group_parent_q->result() as $row)
		{
			$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_ledger_groups()
	{
		$options = array();
		$arrayasset= array();
                $arraylblt= array();
                $arrayexpnd= array();
                $arrayincm= array();
		$this->db->from('groups')->where('id >', 4)->order_by('name', 'asc');
		$group_parent_q = $this->db->get();

		foreach ($group_parent_q->result() as $row)
		{
			$cd = $row->code;
                        $nm = $row->name;
			$id = $row->name . '#' . $row->id;
			//if(substr($cd,0,2)== 10){
			if(substr($cd,0,2) == $this->get_account_code('Liabilities and Owners Equity')){
                        	//$arraylblt[$row->name]=" L- ".$nm;
                        	$arraylblt[$id]=" L- ".$nm;
                        }
                        //if(substr($cd,0,2)== 20){
			if(substr($cd,0,2) == $this->get_account_code('Assets')){
                        	//$arrayasset[$row->name]="A- ".$nm;
                        	$arrayasset[$id]="A- ".$nm;
				
                        }
                        //if(substr($cd,0,2)== 30){
			if(substr($cd,0,2) == $this->get_account_code('Incomes')){
                        	//$arrayincm[$row->name]=" I- ".$nm;
                        	$arrayincm[$id]=" I- ".$nm;
                        }
                        //if(substr($cd,0,2)== 40){
			if(substr($cd,0,2) == $this->get_account_code('Expenses')){
                        	//$arrayexpnd[$row->name]=" E- ".$nm;
                        	$arrayexpnd[$id]=" E- ".$nm;
                        }
	
		}

		$arrayexpnd['Please Select']="Please Select";
		$options=array_merge($arraylblt,$arrayasset,$arrayincm,$arrayexpnd);
		return $options;
	}

	/** 
         * Returns code of the requested account, 
         * as specified in the 'groups' table
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
         */
        function get_account_code($account_name)
        {
                $this->db->from('groups');
                $this->db->select('code');
                $this->db->where('name =', $account_name);
                if($account_name == 'Expenses')
                        $this->db->or_where('name = ', 'Expenditure');
                if($account_name == 'Liabilities and Owners Equity')
                        $this->db->or_where('name = ', 'Sources of Funds');
                if($account_name == 'Assets')
                        $this->db->or_where('name = ', 'Application of Funds');
                $group = $this->db->get();
                foreach($group->result() as $row)
                        return $row->code;
        }

       function get_numOfChild($id)
        {
                $num = 0;
		$sql = "SELECT id FROM groups WHERE parent_id =?";
                $query = $this->db->query($sql, array($id));
		$num = $query->num_rows();
                return $num;
        }

	function get_group_code($id)
	{
		$g_code = 0;
		$this->db->from('groups');
		$this->db->select('code')->where('id =', $id);
		$group_q = $this->db->get();
		foreach($group_q->result() as $row)
		{
			$g_code = $row->code;
		}
		return $g_code;
	}

	function get_schedule($code)
        {
                $group = array();
		$counter = 0;
                $this->db->from('groups');
//		$this->db->select('id, schedule, name')->where('code', $code);
		$this->db->select('id, name')->where('code', $code);
                $group_schedule = $this->db->get();
                foreach ($group_schedule->result() as $row)
                {	
			$group[$counter]['id'] = $row->id;
//			$group[$counter]['schedule'] = $row->schedule;
			$group[$counter]['name'] = $row->name;
			$counter++;
                }
                return $group;
        }

	function get_group_id($code)
	{
		$id = 0;
		$this->db->from('groups');
		$this->db->select('id')->where('code', $code);
		$group = $this->db->get();
		foreach($group->result() as $row)
		{
			$id = $row->id;
		}
		
		return $id;
	}

	function get_id($name)
	{
		$id = 0;
		$this->db->from('groups');
		$this->db->select('id')->where('name', $name);
		$group = $this->db->get();
		foreach($group->result() as $row)
		{
			$id = $row->id;
		}
		
		return $id;
	}

	function get_group_name($id)
	{
		$name = 0;
		$this->db->from('groups');
		$this->db->select('name')->where('id', $id);
		$group = $this->db->get();
		foreach($group->result() as $row)
		{
			$name = $row->name;
		}
		
		return $name;
	}

	function get_group_description($code)
	{
		$this->db->from('groups');
		$this->db->select('group_description')->where('code', $code);
		$group = $this->db->get();
		foreach($group->result() as $row)
		{
			$description = $row->group_description;
		}
		
		return $description;
	}

        function get_group_description_agg($code, $accname)
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
                        $mgroup = "select * from groups where code=$code";
                        $stmt = $dbcon->query($mgroup);

                        if($stmt != false)
                        {
                                foreach ($stmt as $row)
                                {
					$description1 = $row['group_description'];
                                }
                        }
                }
                catch(PDOException $e)
                {
                        echo $e->getMessage();
                }

                return $description1;
        }


}

?>

     
