<?php

class Entry_model extends Model {

	function Entry_model()
	{
		parent::Model();
	}

	function next_entry_number($entry_type_id)
	{
		$this->db->select_max('number', 'lastno')->from('entries')->where('entry_type', $entry_type_id);
		$last_no_q = $this->db->get();
		if ($row = $last_no_q->row())
		{
			$last_no = (int)$row->lastno;
			if ($last_no<=0){
				$this->db->where('entry_type', $entry_type_id);
				$this->db->from('entries');
				$last_no=$this->db->count_all_results();
			}
			$last_no++;
			return $last_no;
		} else {
			return 1;
		}
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
