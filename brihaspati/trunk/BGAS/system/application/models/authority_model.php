<?php

class Authority_model extends Model {

	function Authority_model()
	{
		parent::Model();
	}

	function get_authority_name($id)
	{
		$db1=$this->load->database('login', TRUE);
		$name = 0;
		$db1->from('authorities');
		$db1->select('name')->where('id', $id);
		$group = $db1->get();
		foreach($group->result() as $row)
		{
			$name = $row->name;
		}
		$db1->close();
		return $name;
	}

	function get_all_authorities()
	{
		$db1=$this->load->database('login', TRUE);
		$options = array();
	    $options[0] = 'Please Select';
	    $db1->from('authorities')->select('id,name');
	    $auth_q = $db1->get();
	    foreach ($auth_q->result() as $row)
	    {
	            $auth_id = $row->id;
	            $options[$auth_id]=$row->name;

	    }
	    return $options;
	}
}
?>