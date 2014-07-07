<?php

class Secunit_model extends Model {

	function secunit_model()
	{
		parent::Model();
	}

	function get_all_secunitid()
	{
		$secunitarray = array();
		$this->db->from('addsecondparty')->order_by('id', 'asc');
		$detail_q = $this->db->get();
		foreach ($detail_q->result() as $row)
		{
			$secunit = $row->partyname;
			$secunitarray[$row->sacunit] = $secunit;
		}
		return $secunitarray;
	}
}
?>
