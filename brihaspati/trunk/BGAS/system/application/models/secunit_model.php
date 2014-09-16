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
		$secunitarray[0] = "selectuid";
		foreach ($detail_q->result() as $row)
		{
			$secunit = $row->partyname;
			$secunitarray[$row->sacunit] = $secunit;
		}
		return $secunitarray;
	}

	function get_secunitname($secunit_id)
        {
                $this->db->from('addsecondparty')->where('sacunit', $secunit_id)->limit(1);
                $sparty_q = $this->db->get();
                if ($pname = $sparty_q->row())
                        return $pname->partyname;
                else
                        //return "(Error)";
                        return;
        }
	
	function get_secunitaddress($secunit_id)
        {
                $this->db->from('addsecondparty')->where('sacunit', $secunit_id)->limit(1);
                $sparty_q = $this->db->get();
                if ($pname = $sparty_q->row())
                        return $pname->permanentaddress;
                else
                        //return "(Error)";
                        return;
        }
}
?>
