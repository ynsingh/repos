<?php

class Group_model extends Model {

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
			if(substr($cd,0,2)== 10){
                        	$arraylblt[$row->name]=" L- ".$nm;
                        }
                        if(substr($cd,0,2)== 20){
                        	$arrayasset[$row->name]="A- ".$nm;
                        }
                        if(substr($cd,0,2)== 30){
                        	$arrayincm[$row->name]=" I- ".$nm;
                        }
                        if(substr($cd,0,2)== 40){
                        	$arrayexpnd[$row->name]=" E- ".$nm;
                        }
	
		}
		$options=array_merge($arraylblt,$arrayasset,$arrayincm,$arrayexpnd);
		return $options;
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
}

?>

     
