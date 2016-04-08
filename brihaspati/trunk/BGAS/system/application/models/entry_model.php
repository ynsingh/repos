<?php

class Entry_model extends Model {

	function Entry_model()
	{
		parent::Model();
	}

	function next_entry_number($entry_type_id)
	{

		$this->db->select_max('id')->from('entries')->where('entry_type', $entry_type_id);
		$max_id_q = $this->db->get();
	//	print_r($max_id_q);
		$row = $max_id_q->row();
		$max_id = $row->id;
	//	print_r($max_id);

		if($max_id != NULL){
			$this->db->select('number')->from('entries')->where('id', $max_id);
			$number_q = $this->db->get();
			$row1 = $number_q->row();
			$number = $row1->number;
			//echo "$number";
			if(ctype_digit($number)){
				$total_result = $this->db->count_all_results();
				$number = max($number,$total_result);
				$number++;
			}
			else{
				//$this->db->select('id')->from('entries')->where('entry_type', $entry_type_id);
				$this->db->where('entry_type', $entry_type_id);
				$this->db->from('entries');
				$max_number = $this->db->count_all_results();
				$max_number++;
				$number = $max_number;
			}
			
		}else{
			$number = 1;
		}

/*		$this->db->select_max('number', 'lastno')->from('entries')->where('entry_type', $entry_type_id);
		$last_no_q = $this->db->get();
		if ($row = $last_no_q->row())
		{
			$last_no = (int)$row->lastno;
			$this->logger->write_message("success", "voucherno from db=".$last_no);
			if ($last_no<=0){
				$this->db->where('entry_type', $entry_type_id);
				$this->db->from('entries');
				$last_no=$this->db->count_all_results();
			}
			$last_no++;
			return $last_no;
		} else {
			return 1;
		}*/
		return $number;
	}

	function check_duplicacy($v_number , $entry_type)
	{
		$this->db->from('entries')->where('number',$v_number)->where('entry_type', $entry_type);
		$number_q = $this->db->get();
		$rows = $number_q->num_rows();
		if ($rows > 0) 
			return true;
		
		else
			return  false;
	}

	/**
	*Code to check duplicacy of Vendor Voucher Number
	*/
	//added by @RAHUL
	function check_vendor_no($vendor_no,$purchase_order_no)
        {
                $this->db->from('entries')->where('vendor_voucher_number',$vendor_no)->where('purchase_order_no',$purchase_order_no);
                $number_q = $this->db->get();
                $rows = $number_q->num_rows();
                if ($rows > 0)
                        return true;

                else
                        return  false;
        }


	function get_entry($entry_id, $entry_type_id)
	{
		$this->db->from('entries')->where('id', $entry_id)->where('entry_type', $entry_type_id)->limit(1);
		$entry_q = $this->db->get();
		return $entry_q->row();
	}
	
	function get_all_entry_types()
        {
                $options = array();
            //    if ($allow_none)
              //          $options[0] = "(None)";
                $this->db->from('entry_types')->order_by('id', 'asc');
                $tag_q = $this->db->get();
                foreach ($tag_q->result() as $row)
                {
                        $options[$row->id] = $row->name;
                }
                return $options;
        }

}
