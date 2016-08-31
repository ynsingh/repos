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
		$this->db->from('addsecondparty')->order_by('partyname', 'asc');
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
	// get the array of all secondary unit id with opening balance
	function get_allsecid()
 	{
		$this->db->select('id,sacunit,opbal,dc');
                $this->db->from('addsecondparty')->order_by('id', 'asc');
                $secid_q = $this->db->get();
		return $secid_q;
	}

	// get the opening balance for respective secondary unit 
	function get_sec_opbal($secunit_id)
	{
		$this->db->select('opbal, dc')->from('addsecondparty')->where('sacunit',$secunit_id)->limit(1);
		$sparty_q = $this->db->get();
        if ($pname = $sparty_q->row())
           return array($pname->opbal, $pname->dc);
		else
			return array(0, "D");

	}


	function get_secop_balance1($sec_unit_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_sec_opbal($sec_unit_id);

		$dr_total = $this->get_secdr_total1($sec_unit_id);
		$cr_total = $this->get_seccr_total1($sec_unit_id);
		//echo $cr_total."==";
		$total = float_ops($dr_total, $cr_total, '-');

		if ($op_bal_type == "D")
			$total = float_ops($total, $op_bal, '+');
		else
			$total = float_ops($total, $op_bal, '-');
		return $total;
	}

	function get_secdr_total1($sec_unit_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.secunitid', $sec_unit_id)->where('entry_items.dc', 'D');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$dr_total_q = $this->db->get();
		if ($dr_total = $dr_total_q->row())
			return $dr_total->drtotal;
		else
			return 0;
	}

	function get_seccr_total1($sec_unit_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.secunitid', $sec_unit_id)->where('entry_items.dc', 'C');
		$this->db->where('date >=', $date1);
		$this->db->where('date <=', $date2);
		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
			return $cr_total->crtotal;
		else
			return 0;
	}

	function get_sundry_seccr_secdr_total($sec_id,$dr_cr,$ledg_code)
	{
		$this->db->select_sum('amount', 'total')->from('entry_items')->where('secunitid',$sec_id)->where('dc',$dr_cr)->like('ledger_code',$ledg_code,'after');
		$total_q = $this->db->get();
		if( $total_q->num_rows() < 1 )
		{
			return "0";
		}
		else
		{
			$total_amnt = $total_q->row();
			return $total_amnt->total;
		}
	}
	// get the clossing balance for respective secondary unit 
	function gel_secclsbal($secunit_id)
	{
		$this->db->select('amount');
		$this->db->from('entry_items')->where('secunitid', $secunit_id)->where('dc', 'D');
                $entry_q = $this->db->get();
		$numrow = $entry_q->num_rows();
		$damnt = 0;
		$camnt = 0;
		if($numrow > 0){
			foreach($entry_q->result() as $row)
			{
				$damnt = $damnt + $row->amount;
			}
		}
		else{
			$damnt = 0;
		} 
		$this->db->select('amount');
		$this->db->from('entry_items')->where('secunitid', $secunit_id)->where('dc', 'C');
                $entry_q = $this->db->get();
                $numrow = $entry_q->num_rows();
		if($numrow > 0){
                        foreach($entry_q->result() as $row)
                        {
                                $camnt = $camnt + $row->amount;
                        }
                }
                else{
                        $camnt = 0;
                }
		$dif= $damnt-$camnt;
	return $dif;
	}
	// get array of seconary unit clossing balance
	function get_all_secclsbal()
	{
		$secbalarray = array();
		$lstsecunit=$this->get_allsecid();
		foreach($lstsecunit->result() as $item)
		{
			$sid = $item->id;
			$secid = $item->sacunit;
			$secopbal = $item->opbal;
			$secoptyp = $item->dc;
			$subal = $this->gel_secclsbal($secid);
			if ($secoptyp == 'D'){
				$subal=$subal + $secopbal;
			}
			else {
				$subal=$subal - $secopbal;
			}
			$subal=money_format('%!i', convert_cur($subal));
			if($subal<0){
				$secbalarray[$secid]='C '. str_replace('-','',$subal);
			}
			else{
				$secbalarray[$secid]='D '.$subal;
			}
		}
	return $secbalarray;
	}

	
	/* get entry name with its ledger type of selected date range */
	/*function get_sec_unit_report($entry_id, $entry_type_id, $sec_uni_id)
	{
		/* Selecting both to show debit side Ledger and credit side Ledger */
		/*$current_entry_type = entry_type_info($entry_type_id);
		$ledger_type = 'C';

		if ($current_entry_type['bank_cash_ledger_restriction'] > 1)
		{
			$ledger_type = 'D';
	
			$this->db->select('ledgers.name as name');
			$this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type)->where('entry_items.secunitid', $sec_uni_id);
			$ledger_q = $this->db->get();
			if ( ! $ledger = $ledger_q->row())
			{
				return "(Invalid)";
			}else{
				$ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;
				$html = '';
				if($ledger_multiple)
				{
					foreach($ledger_q->result() as $row)
					{
						$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $row->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
					}
				}else{
					$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			    }

				$ledger_type = 'D';

				if ($current_entry_type['bank_cash_ledger_restriction'] > 1){
					$ledger_type = 'C';
				}

				$this->db->select('ledgers.name as name');
		        $this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type);
		        $ledger_q = $this->db->get();

		        if ( ! $ledger = $ledger_q->row())
		        {
		                return "(Invalid)";
		        }else {
					$ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;
				    if ($ledger_multiple)
					{
						foreach($ledger_q->result() as $row)
						{
							$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $row->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
						}
					}else
				        $html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name . ' - ' . $ledger_type, array('title' => 'View ' .  $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
					}
			}
			return $html;
		}
		return;
	}*/
}
?>
