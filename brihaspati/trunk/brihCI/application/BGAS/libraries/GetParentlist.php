<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class GetParentlist
{
	var $id = 0;
	var $parent_id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $lstatus = 0; 
	var $total = 0;
	var $optype = "";
	var $opbalance = 0;
	var $children_groups = array();
	var $parent_groups = array();
	//var $children_ledgers = array();
	var $counter = 0;
	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;

	function GetParentlist()
	{
		return;
	}

	function init($id,$data_amount)
	

	{
		//echo $id;
		$CI =& get_instance();
		if ($id == 0)
		{
			$this->id = 0;
			$this->name = "None";
			$this->total = 0;

		} else {
			$CI->db->from('groups')->where('id', $id)->limit(1);
			$group_q = $CI->db->get();
			$group = $group_q->row();
			$this->parent_id = $group->parent_id;
			$this->code = $group->code;
		}
			$this->add_sub_groups($id,$data_amount);
	}

	function add_sub_groups($id,$data_amount)
	{
		
		$CI =& get_instance();
		$CI->db->from('groups')->where('id', $id);
		$child_group_q = $CI->db->get();
		$counter = 0;
		foreach ($child_group_q->result() as $row)
		{
			//$this->children_groups[$counter] = new Accountlist();
			$this->parent_groups[$counter] = new GetParentlist();

			$row->parent_id;
			$row->code;
			$useamt=0;
                        $allow=0;
       			$CI->db->from('budgets')->where('code', $row->code);
                                        $query_l = $CI->db->get();
					//$row1= $query_l->result();
                                        //$query_l = $query_l->row();
                                        //$this->id = $query_l->id;
                                   /*     $this->amt = $query_l->bd_balance;
                                        $this->useamt = $query_l->consume_amount;
                                        $this->allow=$query_l->allowedover;
                                        $budgetamt=$this->amt;*/
					foreach($query_l->result() as $row1)
					{
					$this->amt = $row1->bd_balance;
                                        $this->useamt = $row1->consume_amount;
                                        $this->allow=$row1->allowedover;
                                        $useamt=$this->useamt;
                                        $allow=$this->allow;
                                        $available_amont=$useamt + $data_amount;
                                        $update_data1 = array('consume_amount' => $available_amont);
					$CI->db->trans_start();
                                        if ( ! $CI->db->where('code', $this->code)->update('budgets', $update_data1))
                                        {
                        	                $CI->db->trans_rollback();
                                                $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                $this->template->load('template', 'entry/add', $data);
                                                return;
                                        }
					$CI->db->trans_complete();
					



			$this->parent_groups[$counter]->init($row->parent_id,$data_amount);

			}
			$counter++;
		}
	return $row->parent_id;
	}

}

