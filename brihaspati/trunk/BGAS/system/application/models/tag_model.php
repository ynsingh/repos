<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Tag_model extends Model {

	function Tag_model()
	{
		parent::Model();
	}

	function get_all_tags($allow_none = TRUE)
	{
		$options = array();
		if ($allow_none)
			$options[0] = "(Please Select)";
		$this->db->from('tags')->order_by('title', 'asc');
		$tag_q = $this->db->get();
		foreach ($tag_q->result() as $row)
		{
			$options[$row->id] = $row->title;
		}
		return $options;
	}

	function show_entry_tag($tag_id)
	{
		if ($tag_id < 1)
			return "";
		$this->db->from('tags')->where('id', $tag_id)->limit(1);
		$tag_q = $this->db->get();
		if ($tag = $tag_q->row())
			return "<span class=\"tags\" style=\"color:#" . $tag->color . "; background-color:#" . $tag->background . "\">" . $tag->title . "</span>";
		else
			return "";
	}

	function show_entry_tag_link($tag_id)
	{
		if ($tag_id < 1)
			return "";
		$this->db->from('tags')->where('id', $tag_id)->limit(1);
		$tag_q = $this->db->get();
		if ($tag = $tag_q->row())
			return "<span class=\"tags\" style=\"color:#" . $tag->color . "; background-color:#" . $tag->background . "\">" . anchor("entry/show/tag/" . $tag->id , $tag->title, array('style' => 'text-decoration:none;color:#' . $tag->color . ';')) . "</span>";
		else
			return "";
	}
	
	function tag_name($tag_id)
	{
		if ($tag_id < 1)
			return "";
		$this->db->from('tags')->where('id', $tag_id)->limit(1);
		$tag_q = $this->db->get();
		if ($tag = $tag_q->row())
			return $tag->title;
		else
			return "";
	}

	 function get_entry_name_match($entry_id, $entry_type_id, $text)
        {
                /* Selecting whether to show debit side Ledger or credit side Ledger */
                $current_entry_type = entry_type_info($entry_type_id);
                $ledger_type = 'C';
                if ($current_entry_type['bank_cash_ledger_restriction'] == 3)
                        $ledger_type = 'D';
		if($ledger_type == 'D' ){

                	$this->db->select('ledgers.name as name');
                	$this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type)->where('ledgers.name LIKE', '%' . $text . '%');
                	$ledger_q = $this->db->get();
                	$html = '';
                	if( $ledger_q->num_rows() == 1 ) {
                        	foreach ($ledger_q->result() as $ledger)
                        	{
                                	$html .=  $ledger->name;
                        	}
                        	return $html;
                	}
		}

                return;
        }
	function get_entry_name_match1($entry_id, $entry_type_id, $text)
        {
                /* Selecting whether to show debit side Ledger or credit side Ledger */
                $current_entry_type = entry_type_info($entry_type_id);
                $ledger_type = 'C';
                if ($current_entry_type['bank_cash_ledger_restriction'] == 3)
                        $ledger_type = 'D';
                if($ledger_type == 'C'){

                        $this->db->select('ledgers.name as name');
                        $this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type)->where('ledgers.name LIKE', '%' . $text . '%');
                        $ledger_q = $this->db->get();
                        $html = '';
                        if( $ledger_q->num_rows() == 1 ) {
                                foreach ($ledger_q->result() as $ledger)
                                {
                                        $html .=  $ledger->name;
                                }
                                return $html;
                        }
                }
                return;
        }

	function cash_in_hand_available($name)
        {
                $options = '';
                $this->db->from('ledgers')->where('name'. '  ' . 'LIKE', '%' . $name . '%')->where('type', '1');
                $led_name = $this->db->get();
		if ($led_name->num_rows() >= 1)
                {
			return $name;
		}else{
			return 0;
		}
        }

	function get_all_bank_cash_ledgers($led_name)
        {
                $counter=1;
                $options = array();
                $this->db->from('ledgers')->where('type', '1')->where('name !=', 'Cash in Hand');
                $tag_q = $this->db->get();
                foreach ($tag_q->result() as $row)
                {
			if($row->name == $led_name){
                        	$options[$counter] = $row->name;
			}
                        $counter++;
                }
                return $options;
        }

	function get_closing_balance($bank_name)
        {
		$dr_total=0;
		$cr_total=0;
		$total='0';
                $counter=1;
                $options = array();
                $this->db->from('ledgers')->where('name', $bank_name);
                $led_detail = $this->db->get();
                foreach ($led_detail->result() as $row)
                {
			$this->db->from('entry_items')->where('ledger_id', $row->id)->where('dc', 'D');
                	$entry_items_detail = $this->db->get();
			foreach ($entry_items_detail->result() as $row1)
                	{
				$dr_total = $dr_total + $row1->amount;
			}
                }
		
		$this->db->from('ledgers')->where('name', $bank_name);
                $led_detail1 = $this->db->get();
                foreach ($led_detail1->result() as $row2)
                {
                        $this->db->from('entry_items')->where('ledger_id', $row2->id)->where('dc', 'C');
                        $entry_items_detail = $this->db->get();
                        foreach ($entry_items_detail->result() as $row3)
                        {
                                $cr_total = $cr_total + $row3->amount;
                        }
                }
		$total = $cr_total.'#'. $dr_total;	
                return $total;
        }
		
	function get_DC_led_id($entry_id)
        {
		$this->db->from('entry_items')->where('entry_id', $entry_id)->where('dc','C');
                $entry_items1 = $this->db->get();
                foreach ($entry_items1->result() as $row1){
                	$led_id=$row1->ledger_id;
                }
		$this->db->from('entry_items')->where('entry_id', $entry_id)->where('dc','D');
                $entry_items1 = $this->db->get();
                foreach ($entry_items1->result() as $row2){
                        $led_id1=$row2->ledger_id;
                }
		$val="C".$led_id."#"."D".$led_id1;
		return $val;


        }



}
	
