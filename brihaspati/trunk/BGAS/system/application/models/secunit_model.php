<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
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
	/* get entry name with its ledger type of selected date range */
	function get_sec_unit_report($entry_id, $entry_type_id, $sec_uni_id)
	{
		/* Selecting both to show debit side Ledger and credit side Ledger */
		$current_entry_type = entry_type_info($entry_type_id);
		$ledger_type = 'C';

		if ($current_entry_type['bank_cash_ledger_restriction'] > 1){
			$ledger_type = 'D';

		$this->db->select('ledgers.name as name');
		$this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type)->where('entry_items.secunitid', $sec_uni_id);
		$ledger_q = $this->db->get();
		if ( ! $ledger = $ledger_q->row())
		{
			return "(Invalid)";
		} else {
			$ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;
			$html = '';
			if ($ledger_multiple)
				{
					foreach($ledger_q->result() as $row)
					{
						$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $row->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
					}
				}
			else{
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			    }
				$ledger_type = 'D';
				if ($current_entry_type['bank_cash_ledger_restriction'] > 1)
	
					$ledger_type = 'C';
					$this->db->select('ledgers.name as name');
				        $this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type);
				        $ledger_q = $this->db->get();

				        if ( ! $ledger = $ledger_q->row())
				        {
				                return "(Invalid)";
				        }
					else {
						 $ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;

				                if ($ledger_multiple)
						{
							foreach($ledger_q->result() as $row)
							{
							$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $row->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
							}
						}
				                else
				                        $html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name . ' - ' . $ledger_type, array('title' => 'View ' .  $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
					     }
			      }
			return $html;
			}
		return;
	}
}
?>
